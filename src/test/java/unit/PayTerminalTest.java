package unit;

import com.javaacademy.burger.Currency;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PayTerminalTest {

    @Test
    @DisplayName("Проверка оплаты бургера рублями")

    public void burgerRublesPaymentSuccess() {
        PayTerminal payTerminal = new PayTerminal();
        Paycheck paycheck = payTerminal.pay(DishType.BURGER, Currency.RUB);
        Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount());
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency());
        Assertions.assertEquals(DishType.BURGER, paycheck.getDishType());
    }

    @Test
    @DisplayName("Оплата в мозамбикских долларах")
    public void paymentMozambiqueFailure() {
        PayTerminal payTerminal = new PayTerminal();
        Assertions.assertThrows(NotAcceptedCurrencyException.class, () ->
                payTerminal.pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS));
    }
}
