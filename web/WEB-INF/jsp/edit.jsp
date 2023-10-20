<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page import="com.urise.webapp.model.SectionType" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<div class="header-div">
    <div class="header-div-left">
        <a href="resume">Управление резюме</a>
    </div>
    <div class="header-div-right">
        <a href="resume?action=add"><img src="img/person-add.png"></a>
    </div>
</div>

<div class="">
    <section>
        <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
            <input type="hidden" name="uuid" value="${resume.uuid}">
            <div>
                <div>Имя:</div>
                <div>
                    <input type="text" name="fullName" placeholder="ФИО" value="${resume.fullName}">
                </div>
                <div>Контакты:</div>
                <c:forEach var="type" items="<%=ContactType.values()%>">
                    <div>
                            ${type.title}
                    </div>
                    <div>
                        <input type="text" name="${type.name()}" value="${resume.getContact(type)}">
                    </div>
                </c:forEach>
            </div>

            <div>
                <div>Секции:</div>
                <c:forEach var="type" items="<%=SectionType.values()%>">
                    <c:set var="section" value="${resume.getSection(type)}"/>
                    <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
                    <div>${type.title}</div>
                    <c:choose>
                        <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                            <textarea name='${type}'><%=section%></textarea>
                        </c:when>
                        <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                            <textarea class="field"
                              name='${type}'><%=String.join("\n", ((ListSection) section).getList())%>
                    </textarea>
                        </c:when>
                        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                            <c:forEach var="org" items="<%=((OrganizationSection) section).getExperienceList()%>"
                                       varStatus="counter">

                                <div>Название учереждения:</div>
                                <div><input type="text" name='${type}' value="${org.name}"></div>

                                <div>Сайт учереждения:</div>
                                <div><input type="text" name='${type}url' value="${org.website}"></div>

                                <div>
                                    <c:forEach var="per" items="${org.period}">
                                        <jsp:useBean id="per" type="com.urise.webapp.model.Period"/>

                                        <div>Начальная дата:</div>
                                        <div>
                                            <input type="text" name="${type}${counter.index}startDate"
                                                   value="<%=DateUtil.format(per.getStartDate())%>"
                                                   placeholder="yyyy/MM/dd">
                                        </div>

                                        <div>Конечная дата:</div>
                                        <div>
                                            <input type="text" name="${type}${counter.index}endDate"
                                                   value="<%=DateUtil.format(per.getEndDate())%>"
                                                   placeholder="yyyy/MM/dd">
                                        </div>

                                        <div>Должность:</div>
                                        <div>
                                            <input type="text" name='${type}${counter.index}title'
                                                   value="${per.title}">
                                        </div>

                                        <div>Описание:</div>
                                        <div>
                                        <textarea
                                                name="${type}${counter.index}description">${per.description}</textarea>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </c:forEach>
                <button type="submit">Сохранить</button>
                <button type="button" onclick="window.history.back()">Отменить</button>
            </div>
        </form>
    </section>
</div>
<div class="footer-div">
    <a href="https://javaops.ru/reg/basejava">Разработка Web приложения База данных резюме</a>
</div>
</body>
</html>