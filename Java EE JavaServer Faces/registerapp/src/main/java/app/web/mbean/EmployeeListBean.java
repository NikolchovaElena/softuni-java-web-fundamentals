package app.web.mbean;

import app.domain.entities.Employee;
import app.domain.models.view.EmployeeListViewModel;
import app.service.EmployeeService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {

    private List<EmployeeListViewModel> employees;
    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListBean() {
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<EmployeeListViewModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }

    public BigDecimal getSumOfAllSalaries() {
        return this.employees.stream().map(EmployeeListViewModel::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAverageSalary() {
        return this.getSumOfAllSalaries().divide(BigDecimal.valueOf(this.employees.size()), 2);
    }
}
