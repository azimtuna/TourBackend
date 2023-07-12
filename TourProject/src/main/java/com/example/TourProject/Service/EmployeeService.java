package com.example.TourProject.Service;

import com.example.TourProject.Dto.Employee.EmployeeDto;
import com.example.TourProject.Dto.Employee.EmployeeRequestDto;
import com.example.TourProject.Exceptions.NotFoundException;
import com.example.TourProject.Model.Employee;
import com.example.TourProject.Repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    protected Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(()->new NotFoundException("Employee"));
    }

    public List<EmployeeDto> getAll(){
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(employee -> modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
    }

    public EmployeeDto createEmployee(EmployeeRequestDto employeeRequestDto){
        Employee employee = modelMapper.map(employeeRequestDto,Employee.class);
        employee.setId(null);
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }
    public EmployeeDto updateEmployee(Long id,EmployeeRequestDto employeeRequestDto){
        Employee employee = getEmployeeById(id);
        employee=modelMapper.map(employeeRequestDto,Employee.class);
        employee.setId(null);
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }
    public void deleteEmployee(Long id){
       Employee employee= getEmployeeById(id);
        employeeRepository.delete(employee);
    }
    public List<EmployeeDto> sortedEmployeeByFirstName(){
     Sort sort = Sort.by(Sort.Direction.ASC,"firstName");
     List<Employee> employees = employeeRepository.findAll(sort);
     return employees.stream().map(employee -> modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());
    }
    public EmployeeDto getOneEmployee(Long id){
        return modelMapper.map(getEmployeeById(id),EmployeeDto.class);
    }



}
