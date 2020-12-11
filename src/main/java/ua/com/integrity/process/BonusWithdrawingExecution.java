package ua.com.integrity.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BonusWithdrawingExecution implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url
                = "https://7566dfb5-bf83-4a31-bdc5-5a411462c84b.mock.pstmn.io/products/";
        ResponseEntity<String> response = restTemplate.getForEntity(url + "/1", String.class);
        delegateExecution.setVariable(ProcessInstanceVariables.BONUS_WITHDRAWING_STATUS, response.getStatusCode());
    }
}
