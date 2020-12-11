package ua.com.integrity.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class PaymentDetails {
    @NotBlank
    public String cardNumber;
    @NotBlank
    public BigDecimal purchaseAmount;
}
