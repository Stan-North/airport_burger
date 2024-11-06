package it;

import com.javaacademy.burger.*;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class SteakHouseIT {

    @Test
    @DisplayName("работа ресторана")
    public void steakHouseWorkSuccess() {
        Waitress waitress = Mockito.spy(Waitress.class);
        Kitchen kitchen = Mockito.spy(Kitchen.class);
        PayTerminal payTerminal = Mockito.spy(PayTerminal.class);
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);

        Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, Currency.RUB);
        Dish dish = steakhouse.takeOrder(paycheck);

        Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount());
        Assertions.assertEquals(DishType.BURGER, dish.getDishType());
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency());
        Assertions.assertEquals(DishType.BURGER, paycheck.getDishType());
    }

    @Test
    @DisplayName("проверка качества еды")
    public void foodQualitySuccess() {
        Waitress waitress = Mockito.spy(Waitress.class);
        Kitchen kitchen = Mockito.spy(Kitchen.class);
        PayTerminal payTerminalMock = Mockito.mock(PayTerminal.class);
        Mockito.when(payTerminalMock.pay(DishType.RIBS, Currency.RUB)).thenReturn(
                new Paycheck(BigDecimal.valueOf(700), Currency.RUB, DishType.RIBS));

        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminalMock);

        Paycheck paycheck = steakhouse.makeOrder(DishType.RIBS, Currency.RUB);
        Dish dish = steakhouse.takeOrder(paycheck);

        Assertions.assertEquals(BigDecimal.valueOf(700), paycheck.getTotalAmount());
        Assertions.assertEquals(DishType.RIBS, paycheck.getDishType());
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency());
        Assertions.assertEquals(DishType.RIBS, dish.getDishType());
    }

    @Test
    @DisplayName("Покупка ребер за рубли")
    public void buyRibsByRubles() {
        PayTerminal payTerminal = Mockito.spy(PayTerminal.class);
        BigDecimal price = DishType.RIBS.getPrice().multiply(BigDecimal.valueOf(1));
        Mockito.doReturn(new Paycheck(price, Currency.RUB, DishType.RIBS))
                .when(payTerminal).pay(DishType.RIBS, Currency.RUB);

        Waitress waitress = Mockito.mock(Waitress.class);
        Mockito.when(waitress.giveOrderToKitchen(Mockito.any(), Mockito.any())).thenReturn(true);
        Kitchen kitchen = Mockito.mock(Kitchen.class);
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck paycheck = steakhouse.makeOrder(DishType.RIBS, Currency.RUB);
        Assertions.assertEquals(BigDecimal.valueOf(700), paycheck.getTotalAmount());
        Assertions.assertEquals(DishType.RIBS, paycheck.getDishType());
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency());
    }

    @Test
    @DisplayName("Покупка картошки за доллары")
    public void buyFriedPotatoByUsd() {
        PayTerminal payTerminal = Mockito.spy(PayTerminal.class);
        Mockito.doReturn(new Paycheck(BigDecimal.valueOf(1), Currency.USD, DishType.FRIED_POTATO))
                .when(payTerminal).pay(DishType.FRIED_POTATO, Currency.USD);

        Waitress waitress = Mockito.mock(Waitress.class);
        Mockito.when(waitress.giveOrderToKitchen(Mockito.any(), Mockito.any())).thenReturn(true);
        Kitchen kitchen = Mockito.mock(Kitchen.class);
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck paycheck = steakhouse.makeOrder(DishType.FRIED_POTATO, Currency.USD);
        Assertions.assertEquals(BigDecimal.valueOf(1), paycheck.getTotalAmount());
        Assertions.assertEquals(DishType.FRIED_POTATO, paycheck.getDishType());
        Assertions.assertEquals(Currency.USD, paycheck.getCurrency());
    }

    @Test
    @DisplayName("Покупка бургера за мозамбикские доллары")
    public void buyBurgerByMozambiqueUsd() {
        PayTerminal payTerminal = Mockito.spy(PayTerminal.class);
        Mockito.doReturn(new Paycheck(BigDecimal.valueOf(1), Currency.MOZAMBICAN_DOLLARS, DishType.BURGER))
                .when(payTerminal).pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS);

        Waitress waitress = Mockito.mock(Waitress.class);
        Mockito.when(waitress.giveOrderToKitchen(Mockito.any(), Mockito.any())).thenReturn(true);
        Kitchen kitchen = Mockito.mock(Kitchen.class);
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS);
        Assertions.assertEquals(BigDecimal.valueOf(1), paycheck.getTotalAmount());
        Assertions.assertEquals(DishType.BURGER, paycheck.getDishType());
        Assertions.assertEquals(Currency.MOZAMBICAN_DOLLARS, paycheck.getCurrency());
    }
}
