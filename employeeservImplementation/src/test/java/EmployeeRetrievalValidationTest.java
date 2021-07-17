import com.paypal.bfs.test.employeeserv.EmployeeservApplication;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmployeeservApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeRetrievalValidationTest {
    @Autowired
    EmployeeResource employeeResource;

    @Test
    public void testEmployeeRetrievalWithInvalidId() {
        ResponseEntity<Employee> response =employeeResource.employeeGetById("employeeId");
        Assert.assertEquals(400, response.getStatusCodeValue());
    }
}
