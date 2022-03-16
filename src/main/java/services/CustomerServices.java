package services;

import models.Customer;
import models.Staff;
import models.Store;
import servicesImpl.CashierServicesImpl;

public interface CustomerServices extends ViewProductByCategoryImpl{
    void addProductToCart(Customer customer, Store store, int id,int quantity);
    void removeProductFromCart(Customer customer, int productId, int quantity);
    void checkout(Customer customer, Staff staff, Store store, CashierServicesImpl cashier) throws InterruptedException;
}
