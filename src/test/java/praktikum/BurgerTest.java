package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.*;

public class BurgerTest {

    Bun bun;
    Burger burger;
    int countOfIngredients = 8;
    int removedIngredientIndex = 5;

    @Before
    public void setUp() throws Exception {
        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        burger.setBuns(bun);
        for (int i = 0; i < countOfIngredients; i++) {
            burger.addIngredient(Mockito.mock(Ingredient.class));
        }
    }

    @Test
    public void setBunsTest() {
        MatcherAssert.assertThat("Buns is not the same", burger.bun, equalTo(bun));
    }

    @Test
    public void addIngredientTest() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(ingredient);
        MatcherAssert.assertThat("Object is not added", burger.ingredients, hasItem(ingredient));
        MatcherAssert.assertThat("Object is not last item", burger.ingredients.get(burger.ingredients.size() - 1), equalTo(ingredient));
    }

    @Test
    public void removeIngredientTest() {
        Ingredient removedIngredient = burger.ingredients.get(removedIngredientIndex);
        burger.removeIngredient(removedIngredientIndex);
        MatcherAssert.assertThat("Ingredient isn't remove", burger.ingredients, not(hasItem(removedIngredient)));
    }

}
