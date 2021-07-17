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
public class EmployeeCreationTest {
    @Autowired
    private EmployeeResource employeeResource;

    /**
     * Test employee creation for the first time
     */
    @Test
    public void testEmployeeCreation_1() {
        Employee employee = getEmployee();
        ResponseEntity<Employee> response = employeeResource.createEmployee(employee);
        Assert.assertEquals(201, response.getStatusCodeValue());
    }

    /**
     * Test the same employee creation again for duplicate check
     */
    @Test
    public void testEmployeeCreation_2() {
        Employee employee = getEmployee();
        ResponseEntity<Employee> response =employeeResource.createEmployee(employee);
        Assert.assertEquals(409, response.getStatusCodeValue());
    }

    public Employee getEmployee(){
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2021");
        } catch (ParseException ignore) {}

        Employee employee = new Employee();
        employee.setFirstName("creation");
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
