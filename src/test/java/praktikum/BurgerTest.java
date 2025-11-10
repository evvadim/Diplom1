package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;

public class BurgerTest {

    Bun bun;
    Burger burger;
    Burger testingBurger;
    int countOfIngredients = 8;
    int removedIngredientIndex = 5;

    @Before
    public void setUp() throws Exception {
        burger = new Burger();
        testingBurger = new Burger();
        bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);
        for (int i = 0; i < countOfIngredients; i++) {

            Ingredient ingredient = Mockito.mock(Ingredient.class);

            // наполняем объект тестируемого класса методами класса
            burger.addIngredient(ingredient);

            // наполняем эталонный объект вручную
            testingBurger.ingredients.add(ingredient);

        }
    }

    @Test
    public void setBunsTest() {
        MatcherAssert.assertThat("Buns is not the same", burger.bun, equalTo(bun));
    }

    @Test
    public void addIngredientTest() {

        Ingredient ingredient = Mockito.mock(Ingredient.class);

        // добавляем ингредиент в объект методом класса
        burger.addIngredient(ingredient);

        // добавляем ингредиент в эталонный объект вручную
        testingBurger.ingredients.add(ingredient);

        // проверяем, что объекты состоят их одинаковых ингредиентов
        MatcherAssert.assertThat("List of Ingredients are not equal", burger.ingredients, equalTo(testingBurger.ingredients));

        // проверяем, что добавленный элемент в конце списка
        MatcherAssert.assertThat("Last item of List of Ingredients are not the same as added", burger.ingredients.get(burger.ingredients.size() - 1), equalTo(ingredient));

    }

    @Test
    public void removeIngredientTest() {
        Ingredient removedIngredient = burger.ingredients.get(removedIngredientIndex);
        burger.removeIngredient(removedIngredientIndex);
        MatcherAssert.assertThat("Ingredient isn't remove", burger.ingredients, not(hasItem(removedIngredient)));
    }

}
