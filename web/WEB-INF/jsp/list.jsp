<%@ page import="com.urise.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Список всех резюме</title>
</head>

<body>
<div class="header">
    <a href="resume">Управление резюме</a>
</div>
<div class="section-list">
    <div class="table-wrapper">
        <div class="add-resume">
            <a href="resume?action=add"><img src="img/add-person.svg"></a>
            <a class="add-resume-title" href="resume?action=add"><p>Добавить резюме</p></a>
        </div>
        <div class="resume-list">
            <table>
                <tr class="t-header">
                    <th>Имя</th>
                    <th>Email</th>
                    <th>Телефон</th>
                    <th>Редактировать</th>
                    <th>Удалить</th>
                </tr>
                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                    <tr>
                        <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                        <td><%=ContactType.MAIL.toHtml(resume.getContact(ContactType.MAIL))%>
                        </td>
                        <td><%=ContactType.TELEPHONE.toHtml(resume.getContact(ContactType.TELEPHONE))%>
                        </td>
                        <td class="edit-td"><a href="resume?uuid=${resume.uuid}&action=edit"><img
                                src="img/edit.svg"></a></td>
                        <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/remove.svg"></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<div class="footer">
    <a href="https://javaops.ru/reg/basejava">Разработка Web приложения База данных резюме</a>
</div>
</body>
</html>