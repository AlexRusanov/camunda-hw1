package ua.com.integrity.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigDecimal;

public class FixedAmountWithdrawing implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        BigDecimal purchaseAmount = (BigDecimal) delegateExecution.getVariable(ProcessInstanceVariables.PURCHASE_AMOUNT);
        delegateExecution.setVariable(ProcessInstanceVariables.BONUS_AMOUNT, purchaseAmount);
    }
}
