package Task10.app.test;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by inna.pshenychna on 7/2/2017.
 */
public class CartOperationAddRemove extends TestBase{
    Integer numberInCart;

    @Test
    public void cartOperationAddRemove() {
        app.add3itemsToCartRemoveAll();
        numberInCart = app.numberOfItemsInShoppingCart();
        assertTrue(numberInCart == 0);
    }

    @Test
    public void cartOperationAddRemove2() {
        app.navigateToCatalog();
        for(int i=1; i<=3; i++){
            app.addItemToCart();
        }
        app.removeAllItemsFromCart();
        numberInCart = app.numberOfItemsInShoppingCart();
        assertTrue(numberInCart == 0);
    }


}
