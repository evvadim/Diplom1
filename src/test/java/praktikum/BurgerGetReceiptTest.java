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
public class BurgerGetReceiptTest {

    Bun bun;
    Burger burger;

    // переменные для параметризации
    private final String bunName;
    private final List<IngredientType> ingredientType;
    private final List<String> ingredientName;
    private final Float price;

    public BurgerGetReceiptTest(String bunName, List<IngredientType> ingredientType, List<String> ingredientName, Float price) {
        this.bunName = bunName;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.price = price;
    }

    @Parameterized.Parameters (name = "Make burger receipt. Set {index}")
    public static Object[][] getNameData() {
            return new Object[][] {
                    {"dust shadow", List.of(IngredientType.SAUCE, IngredientType.FILLING, IngredientType.SAUCE, IngredientType.FILLING, IngredientType.SAUCE), List.of("mucus", "bone", "chip", "mushrooms", "green-way"), 149.94f},
                    {"dust shadow", List.of(IngredientType.SAUCE, IngredientType.FILLING, IngredientType.SAUCE, IngredientType.FILLING, IngredientType.SAUCE), List.of("green-way", "bone", "mucus", "mushrooms", "chip"), 19.04f},
            };
    }

    @Before
    public void setUp() {

        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);

        // назначаем возвращаемые значения методов `getName()` и наполняем объект объектами
        Mockito.when(burger.bun.getName()).thenReturn(bunName);

        for (int i = 0; i < ingredientName.size(); i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            Mockito.when(ingredient.getName()).thenReturn(ingredientName.get(i));
            Mockito.when(ingredient.getType()).thenReturn(ingredientType.get(i));
            burger.addIngredient(ingredient);
        }

        Mockito.when(burger.getPrice()).thenReturn(price);

    }

    @Test
    public void getReceiptParamTest() {

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bunName));

        for (int i = 0; i < ingredientName.size(); i++) {
            receipt.append(String.format("= %s %s =%n", ingredientType.get(i).toString().toLowerCase(), ingredientName.get(i)));
        }

        receipt.append(String.format("(==== %s ====)%n", bunName));
        receipt.append(String.format("%nPrice: %f%n", price));

        // сравниваем полученный рецепт со значением из метода
        assertThat("Receipt not equal", burger.getReceipt(), equalTo(receipt.toString()));

    }

}
