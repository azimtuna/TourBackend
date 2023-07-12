package com.example.TourProject.Controller;

import com.example.TourProject.Dto.Employee.EmployeeDto;
import com.example.TourProject.Dto.Employee.EmployeeRequestDto;
import com.example.TourProject.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/employee")
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeDto>> getALl(){
        return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getOneEmployee(id));
    }
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        return new ResponseEntity(employeeService.createEmployee(employeeRequestDto),HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,@RequestBody EmployeeRequestDto employeeRequestDto){
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeRequestDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/getSortedFirstName")
    public ResponseEntity<List<EmployeeDto>> getEmployeesSortedByFirstName(){
        return ResponseEntity.ok(employeeService.sortedEmployeeByFirstName());
    }




}
