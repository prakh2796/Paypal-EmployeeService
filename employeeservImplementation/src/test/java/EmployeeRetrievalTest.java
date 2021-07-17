import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.persistence.EmployeeDto;
import com.paypal.bfs.test.employeeserv.persistence.EmployeeRepository;
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
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRetrievalTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeResource employeeResource;

    @Test
    public void testEmployeeRetrieval() {
        EmployeeDto employeeDto = getEmployeeDto();
        employeeRepository.save(employeeDto);
        Optional<EmployeeDto> employeeByHashCode = employeeRepository.findEmployeeByHashCode(employeeDto.getHashCode());
        String employeeId = String.valueOf(employeeByHashCode.get().getId());

        ResponseEntity<Employee> response = employeeResource.employeeGetById(employeeId);
        Employee employee = response.getBody();
        Assert.assertEquals("retrieval", employee.getFirstName());

    }

    public EmployeeDto getEmployeeDto(){
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2021");
        } catch (ParseException ignore) {}

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("retrieval");
        employeeDto.setLastName("test");
        employeeDto.setDateOfBirth(date);
        employeeDto.setAddressLine1("line1");
        employeeDto.setAddressLine2("line2");
        employeeDto.setCity("city");
        employeeDto.setState("state");
        employeeDto.setCountry("country");
        employeeDto.setZipCode(12345);
        employeeDto.setHashCode(employeeDto.getHashCode());

        return employeeDto;
    }
}
