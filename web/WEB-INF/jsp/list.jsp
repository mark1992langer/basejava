<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
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
    <div>
        <div>
            <table cellpadding="8" cellspacing="0">
                <tr>
                    <th>Имя</th>
                    <th>Email</th>
                    <th>Телефон</th>
                    <th></th>
                    <th></th>
                </tr>

                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                    <tr>
                        <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                        <td><%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%>
                        </td>
                        <td><%=ContactType.TELEPHONE.toHtml(resume.getContact(ContactType.TELEPHONE))%>
                        </td>
                        <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
                        <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
    </div>
    </section>
</div>
<div class="footer-div">
    <a href="https://javaops.ru/reg/basejava">Разработка Web приложения База данных резюме</a>
</div>
</body>
</html>