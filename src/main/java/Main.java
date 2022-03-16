import enums.Gender;
import enums.Role;
import models.*;
import servicesImpl.CashierServicesImpl;
import servicesImpl.CustomerServicesImpl;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Store store1 = new Store();
        Staff staff1  = new Staff(1,"johnny", Gender.MALE,"JOHNNY@live","DEC-JAV-1", Role.CASHIER,"BSC");
        CashierServicesImpl cashierServices = new CashierServicesImpl();
        cashierServices.fetchProductFromStore(staff1,store1,"productData.xlsx");
        Customer customer1 = new Customer(2,"dennis",Gender.MALE,"dennis@live.com");
        Customer customer2 = new Customer(3,"ebube",Gender.MALE,"ebube@live.com");
        customer1.setWallet(1_000_000);
        customer2.setWallet(1_000_000);
        CustomerServicesImpl customerServicesImpl = new CustomerServicesImpl();
        Category category = new Category("electronics","Contains phones laptops etc");
        Product product1 = new Product(3,"Phone","Samsung","Note 10 Plus",800.00,10,category);
        customerServicesImpl.addProductToCart(customer1,store1,store1.getProductList()[1].getId(),1);
        customerServicesImpl.checkout(customer1,staff1,store1,cashierServices);
        customerServicesImpl.addProductToCart(customer2,store1,store1.getProductList()[1].getId(),1);
        customerServicesImpl.checkout(customer2,staff1,store1,cashierServices);
        System.out.println(Arrays.toString(new Product[]{store1.getProductList()[1]}));
        //System.out.println(Arrays.toString(new Product[]{store1.getProductList()[2]}));

    }
}
