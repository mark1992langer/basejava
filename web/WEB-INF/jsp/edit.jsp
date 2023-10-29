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
<div class="header">
    <a href="resume">Управление резюме</a>
</div>
<form method="post" action="resume" enctype="application/x-www-form-urlencoded">
    <input type="hidden" name="uuid" value="${resume.uuid}">
    <div class="section-list">
        <div class="form-wrapper">
            <div class="section-edit">ФИО</div>
            <input class="field" type="text" name="fullName" placeholder="ФИО" value="${resume.fullName}" required>
            <div class="section-edit">Контакты:</div>
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <input class="field" type="text" name="${type.name()}" placeholder="${type.title}"
                       value="${resume.getContact(type)}">
            </c:forEach>
            <div class="section-edit">Секции:</div>
            <c:forEach var="type" items="<%=SectionType.values()%>">
                <c:set var="section" value="${resume.getSection(type)}"/>
                <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>
                <div class="field-label">${type.title}</div>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                        <textarea class="field" name='${type}'><%=section%></textarea>
                    </c:when>
                    <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                        <textarea class="field"
                                  name='${type}'><%=String.join("\n", ((ListSection) section).getList())%></textarea>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <c:forEach var="org" items="<%=((OrganizationSection) section).getExperienceList()%>"
                                   varStatus="counter">
                            <input class="field" type="text" name='${type}' value="${org.name}" placeholder="Название">
                            <input type="text" name='${type}url' value="${org.website}" placeholder="Ссылка">
                            <c:forEach var="per" items="${org.period}">
                                <jsp:useBean id="per" type="com.urise.webapp.model.Period"/>
                                <div class="date-section">
                                    <input class="field-date-l" type="text" name="${type}${counter.index}startDate"
                                           value="<%=DateUtil.format(per.getStartDate())%>"
                                           placeholder="Начало, yyyy/mm/dd">
                                    <div class="field-date-c"></div>
                                    <input class="field-date-r" type="text" name="${type}${counter.index}endDate"
                                           value="<%=DateUtil.format(per.getEndDate())%>"
                                           placeholder="Оконьчание, yyyy/mm/dd">
                                </div>
                                <input type="text" name='${type}${counter.index}title'
                                       value="${per.title}" placeholder="Должность">
                                <textarea class="textarea-description"
                                          name="${type}${counter.index}description"
                                          placeholder="Описание">${per.description}</textarea>
                            </c:forEach>
                        </c:forEach>
                    </c:when>
                </c:choose>
            </c:forEach>
            <div class="button-section">
                <button class="button-edit" type="submit">Сохранить</button>
                <div class="field-date-c"></div>
                <button class="button-edit-escape" type="button" onclick="window.history.back()">Отменить</button>
            </div>
        </div>
    </div>
</form>
<div class="footer">
    <a href="https://javaops.ru/reg/basejava">Разработка Web приложения База данных резюме</a>
</div>
</body>
</html>