package ua.com.integrity.rest.endpoints;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.integrity.model.PaymentDetails;
import ua.com.integrity.process.ProcessInstanceVariables;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/process")
public class StartProcessEndpoints {

    private final RuntimeService runtimeService;

    @PostMapping("/message")
    public void startProcessInstanceByMessage(PaymentDetails paymentDetails) {
        VariableMap variableMap = Variables
                .putValue(ProcessInstanceVariables.CARD_NUMBER, paymentDetails.getCardNumber())
                .putValue(ProcessInstanceVariables.PURCHASE_AMOUNT, paymentDetails.getCardNumber());
        try {
            runtimeService.startProcessInstanceByMessage(ProcessInstanceVariables.START_MESSAGE_EVENT, variableMap);
        } catch (RuntimeException e) {
            log.error("Error occured while starting process isntance: {}", e);
            throw e;
        }
    }
}
