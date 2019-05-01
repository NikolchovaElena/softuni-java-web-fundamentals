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
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
    private static final String CAT_PROFILE_HTML_FILE_PATH = "/home/luoni/IdeaProjects/munchkin/src/main/resources/views/cat-profile.html";
    private static final String NON_EXISTANT_CAT_PROFILE_HTML_FILE_PATH = "/home/luoni/IdeaProjects/munchkin/src/main/resources/views/non-existent-cat.html";

    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                .get(req.getQueryString().split("=")[1]);

        String htmlFileContent = "";
        if (cat == null) {
            htmlFileContent = this.htmlReader.readHtmlFile(NON_EXISTANT_CAT_PROFILE_HTML_FILE_PATH)
                    .replace("{{catName}}", req.getQueryString().split("=")[1]);
        } else {
            htmlFileContent = this.htmlReader.readHtmlFile(CAT_PROFILE_HTML_FILE_PATH)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", cat.getAge().toString());
        }

        resp.getWriter().println(htmlFileContent);
    }
}
