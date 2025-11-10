package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
        assertThat("Buns is not the same", burger.bun, equalTo(bun));
    }

    @Test
    public void addIngredientTest() {

        Ingredient ingredient = Mockito.mock(Ingredient.class);

        // добавляем ингредиент в объект методом класса
        burger.addIngredient(ingredient);

        // добавляем ингредиент в эталонный объект вручную
        testingBurger.ingredients.add(ingredient);

        // проверяем, что объекты состоят их одинаковых ингредиентов
        assertThat("List of Ingredients are not equal after adding Ingredient", burger.ingredients, equalTo(testingBurger.ingredients));

        // проверяем, что добавленный элемент в конце списка
        assertThat("Last item of List of Ingredients are not the same as added", burger.ingredients.get(burger.ingredients.size() - 1), equalTo(ingredient));

    }

    @Test
    public void removeIngredientTest() {

        // запоминаем удаляемый объект
        Ingredient removedIngredient = burger.ingredients.get(removedIngredientIndex);

        // удаляем его из объекта
        burger.removeIngredient(removedIngredientIndex);

        // удаляем его вручную
        Ingredient testingRemovedIngredient = testingBurger.ingredients.remove(removedIngredientIndex);

        // проверяем, что удаленные объекты одинаковые
        assertThat("Removed Ingredients are not the same", removedIngredient, equalTo(testingRemovedIngredient));

        // проверяем, что после удаления эталонный и тестируемый списки объектов одинаковые
        assertThat("List of Ingredients are not equal after removing Ingredient", burger.ingredients, equalTo(testingBurger.ingredients));
    }

}
