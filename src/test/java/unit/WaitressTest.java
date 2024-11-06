package unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaitressTest {
    @Test
    @DisplayName("Принятие заказа бургера")
    public void waitressGetOrderSuccess() {
        Kitchen kitchen = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        Assertions.assertDoesNotThrow(() -> waitress.giveOrderToKitchen(DishType.BURGER, kitchen));
    }

    @Test
    @DisplayName("Принятие заказа фуагра")
    public void waitressGetOrderSFailure() {
        Kitchen kitchen = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        Assertions.assertFalse(() -> waitress.giveOrderToKitchen(DishType.FUAGRA, kitchen));
    }
}
