package ua.com.integrity.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.com.integrity.model.ProductDetails;

@Component
public class GetProductDetails implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String cardNumber = (String) delegateExecution.getVariable("card_number");
        String url = "https://7566dfb5-bf83-4a31-bdc5-5a411462c84b.mock.pstmn.io/products/" + cardNumber.charAt(0);
        RestTemplate restTemplate = new RestTemplate();
        ProductDetails productDetails = restTemplate.getForObject(url, ProductDetails.class);
        delegateExecution.setVariable(ProcessInstanceVariables.PRODUCT_ID, productDetails.getId());
    }
}
