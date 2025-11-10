package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;

public class BurgerTest {

    Bun bun;
    Burger burger;
    int countOfIngredients = 8;

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

}
