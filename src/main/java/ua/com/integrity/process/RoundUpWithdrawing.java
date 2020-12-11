package ua.com.integrity.process;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundUpWithdrawing implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        BigDecimal purchaseAmount = (BigDecimal) delegateExecution.getVariable(ProcessInstanceVariables.PURCHASE_AMOUNT);
        BigDecimal bonusAmount = purchaseAmount.setScale(0, RoundingMode.UP).subtract(purchaseAmount);
        delegateExecution.setVariable(ProcessInstanceVariables.BONUS_AMOUNT, bonusAmount);
    }
}
