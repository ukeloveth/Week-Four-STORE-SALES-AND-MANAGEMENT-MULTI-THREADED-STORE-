package servicesImpl;

import enums.Gender;
import enums.Role;
import exceptions.StaffNotAuthorizedException;
import models.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class CashierServicesImplTest {
    Store store1;
    Staff staff1;
    Staff staff2;
    CashierServicesImpl cashierServicesImpl;
    CustomerServicesImpl customerServicesImpl;
    Customer customer1;
    @Before
    public void setUp() throws Exception {
        store1 = new Store();
        staff1 =  new Staff(1,"johnny", Gender.MALE,"johnny@live.com","DEC-JAV-1", Role.CASHIER,"BSC");
        staff2 =  new Staff(2,"dennis", Gender.MALE,"dennis@live.com","DEC-JAV-2", Role.MANAGER,"BSC");
        cashierServicesImpl = new CashierServicesImpl();
        customerServicesImpl = new CustomerServicesImpl();
        customer1 = new Customer(3,"loveth",Gender.FEMALE,"loveth@decagon.com");
        customer1.setWallet(1_000_000.00);

    }

    @Test
    public void shouldThrowStaffNotAuthorizedExceptionIfStaffRoleIsNotCashier(){
        assertThrows(StaffNotAuthorizedException.class,()->{
            cashierServicesImpl.fetchProductFromStore(staff2,store1,"productData.xlsx");
        });
    }

    @Test
    public void fetchProductFromStore() throws IOException {
        cashierServicesImpl.fetchProductFromStore(staff1,store1,"productData.xlsx");
        assertEquals(1,store1.getProductList()[0].getId());
    }

    @Test
    public void shouldAssertThatStoreTransactionHistoryIsNotNullIfCustomerSuccessfullyCheckedOut() throws IOException, InterruptedException {
        cashierServicesImpl.fetchProductFromStore(staff1,store1,"productData.xlsx");
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        customerServicesImpl.checkout(customer1,staff1,store1,cashierServicesImpl);
        cashierServicesImpl.printReceipt(3,store1);
        assertNotNull(store1.getTransactionHistory());

    }

    @Test
    public void shouldAssertThatStoreTransactionHistoryIsNotNullIfCustomerSuccessfullyCheckedOutAndCustomerHasTransactionHistory() throws IOException, InterruptedException {
        cashierServicesImpl.fetchProductFromStore(staff1,store1,"productData.xlsx");
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        customerServicesImpl.checkout(customer1,staff1,store1,cashierServicesImpl);
        cashierServicesImpl.fetchProductFromStore(staff1,store1,"productData.xlsx");
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        customerServicesImpl.checkout(customer1,staff1,store1,cashierServicesImpl);
        cashierServicesImpl.printReceipt(3,store1);
        assertNotNull(store1.getTransactionHistory());
    }

    @Test
    public void cashierCanViewProduct() throws IOException {
        cashierServicesImpl.fetchProductFromStore(staff1,store1,"productData.xlsx");
        List<Product> electronicGoods = cashierServicesImpl.viewProductByCategory(store1, "CLOTHING");
        assertTrue(electronicGoods.get(0).getCategory().getName().equals("CLOTHING"));
        assertTrue(electronicGoods.get(1).getCategory().getName().equals("CLOTHING"));
        assertTrue(electronicGoods.get(2).getCategory().getName().equals("CLOTHING"));
    }
}