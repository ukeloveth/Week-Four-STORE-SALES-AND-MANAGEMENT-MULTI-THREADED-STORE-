package servicesImpl;

import enums.Gender;
import enums.Role;
import exceptions.CartIsEmptyException;
import exceptions.ProductDoesNotExistException;
import exceptions.QuantityIsGreaterException;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CustomerServicesImplTest {
    Store store1;
    double initialWalletAmount;
    double storeNewAccountBalance;
    CashierServicesImpl cashierServicesImpl;
    CustomerServicesImpl customerServicesImpl;
    Staff staff1;
    Customer customer1;
    Product product1;
    Category category;

    @Before
    public void setUp() throws Exception {
        store1 = new Store();
        initialWalletAmount = 1_000_000.00;
        storeNewAccountBalance = 4_000.00;
        staff1 =  new Staff(1,"johnny", Gender.MALE,"johnny@live.com","DEC-JAV-1", Role.CASHIER,"BSC");
        customerServicesImpl = new CustomerServicesImpl();
        cashierServicesImpl = new CashierServicesImpl();
        customer1 = new Customer(2,"dennis",Gender.MALE,"dennis@live.com");
        customer1.setWallet(initialWalletAmount);
        cashierServicesImpl.fetchProductFromStore(staff1,store1,"productData.xlsx");
        category = new Category("electronics","Contains phones laptops etc");
        product1 = new Product(3,"Phone","Samsung","Note 10 Plus",800.00,10,category);


    }

    @Test
    public void shouldThrowProductDoesNotExistExceptionIfProductDoesNotExistInCart() {
        assertThrows(ProductDoesNotExistException.class,()->{
            customerServicesImpl.addProductToCart(customer1,store1,11,1);
        });
    }

    @Test
    public void shouldAddProductToCartIfCartIsEmptyAndProductExistsInStore() {
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        assertEquals(1,customer1.getCart().size());
    }

    @Test
    public void shouldAddProductToCartIfCartIsNotEmptyButDoesNotContainProduct() {
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[4].getId(),1);
        assertEquals(2,customer1.getCart().size());
    }

    @Test
    public void shouldIncreaseProductQuantityInCartIfCartAlreadyContainsProduct() {
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        assertEquals(2,customer1.getCart().get(store1.getProductList()[1]).intValue());
    }

    @Test
    public void shouldThrowCartIsEmptyExceptionWhenRemovingFromEmptyCart() {
        assertThrows(CartIsEmptyException.class,()->{
            customerServicesImpl.removeProductFromCart(customer1,3,1);
        });
    }

    @Test
    public void shouldRemoveProductFromCartBySpecifiedQuantity() {
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),2);
        customerServicesImpl.removeProductFromCart(customer1,store1.getProductList()[1].getId(),1);
        assertEquals(1,customer1.getCart().get(store1.getProductList()[1]).intValue());
    }

    @Test
    public void shouldThrowQuantityIsGreaterExceptionWhenSpecifiedQuantityIsGreaterThanQuantityInCart() {
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),2);
        assertThrows(QuantityIsGreaterException.class,()->{
            customerServicesImpl.removeProductFromCart(customer1,store1.getProductList()[1].getId(),3);
        });
    }

    @Test
    public void shouldRemoveProductFromCart() {
    }

    @Test
    public void checkout() throws InterruptedException {
        customerServicesImpl.addProductToCart(customer1,store1,5,1);
        customerServicesImpl.checkout(customer1,staff1,store1,cashierServicesImpl);
        assertEquals(500.00,store1.getAccount(),0.001);
    }

    @Test
    public void printReceipt() {
        customerServicesImpl.addProductToCart(customer1,store1,5,1);
        //assertTrue(customerServicesImpl.checkout(customer1,store1));
    }

    @Test
    public void customerCanViewProduct(){
        List<Product> electronicGoods = customerServicesImpl.viewProductByCategory(store1, "ELECTRONICS");
        assertEquals("ELECTRONICS", electronicGoods.get(0).getCategory().getName());
        assertEquals("ELECTRONICS", electronicGoods.get(1).getCategory().getName());
        assertEquals("ELECTRONICS", electronicGoods.get(2).getCategory().getName());
    }
}