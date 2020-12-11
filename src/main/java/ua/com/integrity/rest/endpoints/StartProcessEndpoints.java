package ua.com.integrity.rest.endpoints;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.integrity.model.PaymentDetails;
import ua.com.integrity.process.ProcessInstanceVariables;

import java.util.Arrays;

@RestController
@RequestMapping("api/process")
public class StartProcessEndpoints {

    private ProcessEngine processEngine;
    @Autowired
    public void setProcessEngine(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @PostMapping("/message")
    public void startProcessInstanceByMessage(PaymentDetails paymentDetails) {
        VariableMap variableMap = Variables
                .putValue(ProcessInstanceVariables.CARD_NUMBER, paymentDetails.getCardNumber())
                .putValue(ProcessInstanceVariables.PURCHASE_AMOUNT, paymentDetails.getCardNumber());
        try {
            processEngine.getRuntimeService().startProcessInstanceByMessage(ProcessInstanceVariables.START_MESSAGE_EVENT, variableMap);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }
}
