package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("resumes", storage.getAllSorted());
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=UTF-8");
//        Writer writer = response.getWriter();
//        writer.write(
//                """
//                        <html>
//                        <head>
//                            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
//                            <title>Список всех резюме</title>
//                            <style>
//                               .center {
//                                margin: auto;
//                                border: 3px solid #006400;
//                              }
//                              </style>
//                        </head>
//                        <body>
//                        <section>
//                        <table class="center" border="2" cellpadding="8" cellspacing="2">
//                            <tr>
//                                <th>NAME</th>
//                                <th>TELEPHONE</th>
//                                <th>SKYPE</th>
//                                <th>Email</th>
//                            </tr>
//                        """);
//        for (Resume resume : storage.getAllSorted()) {
//            writer.write(
//                    "<tr>\n" +
//                    "     <td>" + resume.getFullName() + "</td>\n" +
//                    "     <td>" + resume.getContact(ContactType.TELEPHONE) + "</td>\n" +
//                    "     <td>" + resume.getContact(ContactType.SKYPE) + "</td>\n" +
//                    "     <td>" + resume.getContact(ContactType.MAIL) + "</td>\n" +
//                    "</tr>\n");
//        }
//        writer.write("""
//                </table>
//                </section>
//                </body>
//                </html>
//                """);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
