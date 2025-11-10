package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

@RunWith(Parameterized.class)
public class BurgerGetPriceTest {

    Bun bun;
    Burger burger;
    Burger testingBurger;
    Float burgerCoast;
    Float testingBurgerCoast;


    // переменные для параметризации
    private final Float bunPrice;
    private final List<Float> ingredientPrice;

    public BurgerGetPriceTest(float bunPrice, List<Float> ingredientPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters (name = "Calculating burger coast. Set {index}")
    public static Object[][] getPriceData() {
            return new Object[][] {
                    {17f, List.of(36f, 14f, 0f)},
                    {45f, List.of(19f, 78f, 87f, 87f, 99f)},
                    {31f, List.of(9f, 21f, 27f, 18f, 19f)},
            };
    }

    @Before
    public void setUp() {

        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);

        // устанавливаем возвращаемые значения методов `getPrice()` и наполняем объект объектами
        Mockito.when(burger.bun.getPrice()).thenReturn(bunPrice);

        for (Float price : ingredientPrice) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getPrice()).thenReturn(price);
            burger.addIngredient(ingredient);
        }

        // считаем стоимость бургера вручную
        testingBurgerCoast = bunPrice * 2;

        for (Float price : ingredientPrice) {
            testingBurgerCoast += price;
        }

    }

    @Test
    public void getPriceParamTest() {

        burgerCoast = burger.getPrice();
        assertThat("Coast not equal", burgerCoast, equalTo(testingBurgerCoast));

    }

}
