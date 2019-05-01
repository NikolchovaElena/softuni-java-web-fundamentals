package metubev1.web.servlets;

import metubev1.domain.models.view.AllTubesViewModel;
import metubev1.service.TubeService;
import metubev1.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/tubes/all")
public class TubesAllServlet extends HttpServlet {
    private ModelMapper modelMapper;
    private TubeService tubeService;

    @Inject
    public TubesAllServlet(ModelMapper modelMapper, TubeService tubeService) {
        this.modelMapper = modelMapper;
        this.tubeService = tubeService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allTubes", this.tubeService.findAllTubes()
                .stream()
                .map(t -> this.modelMapper.map(t, AllTubesViewModel.class)).collect(Collectors.toList()));

        req.getRequestDispatcher("/jsps/all-tubes.jsp").forward(req, resp);
    }
}
