package ua.com.integrity;

import io.restassured.RestAssured;
import org.camunda.bpm.engine.RuntimeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.integrity.process.ProcessInstanceVariables;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyMap;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "camunda.bpm.webapp.enabled=false"
        }
)
public class CamundaApplicationSpringBootTest {

    @LocalServerPort
    int serverPort;

    @MockBean
    RuntimeService runtimeService;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = serverPort;
    }

    @After
    public void tearDown() throws Exception {
        RestAssured.reset();
    }

    @Test
    public void test_that_process_instance_start_rest_endpoint_will_return_204_code_if_runtimeservice_will_not_throw_exception() {
        RestAssured.with()
                    .contentType("application/json")
                    .body("{'cardNumber':'1111222233334444','purchaseAmount':'11.23'}")
                .when()
                    .post("/api/process/message")
                .then()
                    .statusCode(200);
    }

    @Test
    public void test_that_process_instance_start_rest_endpoint_will_return_500_code_if_runtimeservice_will_throw_exception() {
        when(runtimeService.startProcessInstanceByMessage(eq(ProcessInstanceVariables.START_MESSAGE_EVENT), anyMap())).thenThrow(new RuntimeException());

        RestAssured.with()
                    .contentType("application/json")
                    .body("{'cardNumber':'1111222233334444','purchaseAmount':'11.23'}")
                .when()
                    .post("/api/process/message")
                .then()
                    .statusCode(500);
    }
}