package com.niit.MiddleWare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import niit.backendfinal.model.Employee;
import niit.backendfinal.service.EmplService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/emp")


public class EmpController 
{
	@Autowired
	private EmplService EmployeeService;

	@GetMapping
	public List<Employee> listALLEmployeeDetails()
	{
		List<Employee> emp = EmployeeService.getemplist();
		return emp;
	}
	
	@PostMapping
	public ResponseEntity<Void> addEmployeeDetails(@RequestBody Employee emp)
	{

		if(EmployeeService.getemp(emp.getEmpid())==null)
		{
			EmployeeService.addemp(emp);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
	}

@PutMapping

public ResponseEntity<Void> updateEmployeeDetails(@RequestBody Employee emp)
{
	if(EmployeeService.getemp(emp.getEmpid())!=null)
	{
		EmployeeService. updateemp(emp);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
@DeleteMapping
public ResponseEntity<Void> deleteEmployeeDetails(@RequestBody Employee emp)
{
	if(EmployeeService.getemp(emp.getEmpid())!=null)
	{
		EmployeeService.deleteemp(0);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}

@GetMapping("/{EmpId}")
public ResponseEntity<Employee> getById(@PathVariable("EmpId") int EmpId)
{
	Employee emp = null;
	return new ResponseEntity<Employee>(EmployeeService.getemp(EmpId),HttpStatus.OK);
}

}
