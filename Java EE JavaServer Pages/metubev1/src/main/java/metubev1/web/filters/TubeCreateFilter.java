package metubev1.web.filters;

import metubev1.domain.models.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().toLowerCase().equals("post")) {
            TubeCreateBindingModel bindingModel = new TubeCreateBindingModel();
            bindingModel.setName(req.getParameter("name"));
            bindingModel.setDescription(req.getParameter("description"));
            bindingModel.setYouTubeLink(req.getParameter("youtubeLink"));
            bindingModel.setUploader(req.getParameter("uploader"));

            req.setAttribute("tubeCreateBindingModel", bindingModel);
        }

        filterChain.doFilter(req, resp);
    }
}
