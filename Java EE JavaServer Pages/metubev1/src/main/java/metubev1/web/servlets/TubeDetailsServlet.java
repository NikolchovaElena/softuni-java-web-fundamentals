package metubev1.web.servlets;

import metubev1.domain.models.view.TubeDetailsViewModel;
import metubev1.service.TubeService;
import metubev1.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {

    private ModelMapper modelMapper;
    private TubeService tubeService;

    @Inject
    public TubeDetailsServlet(ModelMapper modelMapper, TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = URLDecoder.decode(req.getQueryString().split("=")[1], StandardCharsets.UTF_8);

        req.setAttribute("tubeDetailsViewModel",
                this.modelMapper.map(this.tubeService.findTubeByName(name), TubeDetailsViewModel.class));

        req.getRequestDispatcher("/jsps/details-tube.jsp").forward(req, resp);
    }
}

