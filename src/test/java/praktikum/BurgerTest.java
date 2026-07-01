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
    int moveIngredientFromIndex = 6;
    int toNewIndex = 2;

    @Before
    public void setUp() {
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

        // проверяем, что добавленный элемент в конце списка
        assertThat("Last item of List of Ingredients are not the same as added", burger.ingredients.get(burger.ingredients.size() - 1), equalTo(ingredient));

    }

    @Test
    public void removeIngredientTest() {

        // удаляем его из объекта
        burger.removeIngredient(removedIngredientIndex);

        // удаляем его вручную
        testingBurger.ingredients.remove(removedIngredientIndex);

        // проверяем, что после удаления эталонный и тестируемый списки объектов одинаковые
        assertThat("List of Ingredients are not equal after removing Ingredient", burger.ingredients, equalTo(testingBurger.ingredients));
    }

    @Test
    public void moveIngredientTest() {

        // перемещаем объект списка методом класса
        burger.moveIngredient(moveIngredientFromIndex, toNewIndex);

        // перемещаем объект списка вручную
        testingBurger.ingredients.add(toNewIndex, testingBurger.ingredients.remove(moveIngredientFromIndex));

        // проверяем получившиеся списки
        assertThat("Lists of Ingredients are not equal", burger.ingredients, equalTo(testingBurger.ingredients));

    }

}
