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
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">

        <div>Имя:</div>
        <div><input type="text" name="fullName" placeholder="ФИО" size=50 value="${resume.fullName}"></div>

        <div>Контакты:</div>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <div>${type.title}</div>
            <div><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></div>
        </c:forEach>

        <hr>

        <div>Секции:</div>
        <div></div>

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
                              name='${type}'><%=String.join("\n", ((ListSection) section).getList())%></textarea>
                </c:when>

                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((OrganizationSection) section).getExperienceList()%>"
                               varStatus="counter">

                        <div>Название учереждения:</div>
                        <div><input type="text" name='${type}' size=100 value="${org.name}"></div>

                        <div>Сайт учереждения:</div>
                        <div><input type="text" name='${type}url' size=100 value="${org.website}"></div>

                        <div>
                            <c:forEach var="per" items="${org.period}">
                                <jsp:useBean id="per" type="com.urise.webapp.model.Period"/>

                                <div>Начальная дата:</div>
                                <div>
                                    <input type="text" name="${type}${counter.index}startDate" size=10
                                           value="<%=DateUtil.format(per.getStartDate())%>"
                                           placeholder="yyyy/MM/dd">
                                </div>

                                <div>Конечная дата:</div>
                                <div>
                                    <input type="text" name="${type}${counter.index}endDate" size=10
                                           value="<%=DateUtil.format(per.getEndDate())%>" placeholder="yyyy/MM/dd">
                                </div>

                                <div>Должность:</div>
                                <div>
                                    <input type="text" name='${type}${counter.index}title' size=75
                                           value="${per.title}">
                                </div>

                                <div>Описание:</div>
                                <div>
                                     <textarea name="${type}${counter.index}description" rows=5
                                                  cols=75>${per.description}</textarea>
                                </div>
                                <br>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>

        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>

    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>