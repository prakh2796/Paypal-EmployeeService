import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeCreationValidationTest {
    @Autowired
    private EmployeeResource employeeResource;

    @Test
    public void testEmployeeCreationWithInvalidFirstName() {
        Employee employee = getEmployee();
        employee.setFirstName(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testEmployeeCreationWithInvalidLastName() {
        Employee employee = getEmployee();
        employee.setLastName(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testEmployeeCreationWithInvalidDOB() {
        Employee employee = getEmployee();
        employee.setDateOfBirth(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testEmployeeCreationWithInvalidAddress() {
        Employee employee = getEmployee();
        employee.setAddress(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testEmployeeCreationWithInvalidAddressLine() {
        Employee employee = getEmployee();
        employee.getAddress().setLine1(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testEmployeeCreationWithInvalidCity(){
        Employee employee = getEmployee();
        employee.getAddress().setCity(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testEmployeeCreationWithInvalidCountry(){
        Employee employee = getEmployee();
        employee.getAddress().setCountry(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void testEmployeeCreationWithInvalidZipCode(){
        Employee employee = getEmployee();
        employee.getAddress().setZipCode(null);
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

    public Employee getEmployee(){
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2021");
        } catch (ParseException ignore) {}

        Employee employee = new Employee();
        employee.setFirstName("validation");
        employee.setLastName("test");
        employee.setDateOfBirth(date);

        Address address = new Address();
        address.setLine1("line1");
        address.setLine2("line2");
        address.setCity("city");
        address.setState("state");
        address.setCountry("country");
        address.setZipCode(12345);

        employee.setAddress(address);

        return employee;
    }
}
