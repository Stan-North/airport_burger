package unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KitchenTest {

    @Test
    @DisplayName("Создание бургера")
    public void createBurgerSuccess() {
        Kitchen kitchen = new Kitchen();
        kitchen.cook(DishType.BURGER);
        Assertions.assertTrue(kitchen.getCompletedDishes().containsKey(DishType.BURGER));
    }

    @Test
    @DisplayName("Запрос на создание бургера, когда на кухне нет газа")
    public void createBurgerFailure() {
        Kitchen kitchen = new Kitchen();
        kitchen.setHasGas(false);
        Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(DishType.BURGER));
    }

    @Test
    @DisplayName("Запрос на создание фуагра")
    public void createFuagraFailure() {
        Kitchen kitchen = new Kitchen();
        Assertions.assertThrows(UnsupportedDishTypeException.class, () -> kitchen.cook(DishType.FUAGRA));
    }
}
