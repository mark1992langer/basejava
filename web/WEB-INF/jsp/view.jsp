<%@ page import="com.urise.webapp.model.TextSection" %>
<%@ page import="com.urise.webapp.model.ListSection" %>
<%@ page import="com.urise.webapp.model.OrganizationSection" %>
<%@ page import="com.urise.webapp.util.HtmlUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    <div>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></div>
    <div>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.urise.webapp.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </div>

    <div></div><br><br>

    <c:forEach var="sectionEntry" items="${resume.sections}">
        <jsp:useBean id="sectionEntry"
                     type="java.util.Map.Entry<com.urise.webapp.model.SectionType, com.urise.webapp.model.Section>"/>
        <c:set var="type" value="${sectionEntry.key}"/>
        <c:set var="section" value="${sectionEntry.value}"/>
        <jsp:useBean id="section" type="com.urise.webapp.model.Section"/>

        <div>${type.title}</div>
        <c:choose>
            <c:when test="${type=='OBJECTIVE'}">
                <div>
                    <%=((TextSection) section).getContent()%>
                </div>
            </c:when>
            <c:when test="${type=='PERSONAL'}">
                <div>
                    <%=((TextSection) section).getContent()%>
                </div>
            </c:when>
            <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                <ul>
                    <c:forEach var="item" items="<%=((ListSection) section).getList()%>">
                        <li>${item}</li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                <c:forEach var="org" items="<%= ((OrganizationSection) section).getExperienceList()%>">
                    <div>
                        <c:choose>
                            <c:when test="${empty org.website}">
                                <div>${org.name}</div>
                            </c:when>
                            <c:otherwise>
                                <div><a href="${org.website}">${org.name}</a></div>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="per" items="${org.period}">
                            <jsp:useBean id="per" type="com.urise.webapp.model.Period"/>
                            <div>${per.title}</div>
                            <div>${per.description}</div>

                            <div><%=HtmlUtil.formatDates(per)%></div><br>
                        </c:forEach>
                    </div>
                </c:forEach>
            </c:when>
        </c:choose>
    </c:forEach>

    <button onclick="window.history.back()">ОК</button>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>