package munchkin.web.servlets;

import munchkin.domain.entities.Cat;
import munchkin.utils.htmlReader.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet {
    private static final String ALL_CATS_HTML_FILE_PATH = "/home/luoni/IdeaProjects/munchkin/src/main/resources/views/cat-all.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatAllServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlFileContent = this.htmlReader.readHtmlFile(ALL_CATS_HTML_FILE_PATH);

        if (req.getSession().getAttribute("cats") == null) {
            htmlFileContent = htmlFileContent
                    .replace("{{catAll}}", "There are no cats! <a href=\"/cats/create\">Create some!</a><br>");
        } else {
            StringBuilder sb = new StringBuilder();

            ((Map<String, Cat>) req.getSession().getAttribute("cats")).values()
                    .stream().collect(Collectors.toList()).forEach(cat -> {
                sb.append(String.format("<a href=\"/cats/profile?catName=%s\">%s</a><br>", cat.getName(), cat.getName()))
                        .append(System.lineSeparator());
            });

            htmlFileContent = htmlFileContent
                    .replace("{{catAll}}", sb.toString().trim());
        }

        resp.getWriter().println(htmlFileContent);
    }
}
