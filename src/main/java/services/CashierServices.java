package services;

import models.Customer;
import models.Staff;
import models.Store;

import java.io.IOException;

public interface CashierServices extends ViewProductByCategoryImpl {
    void fetchProductFromStore(Staff staff, Store store, String filename) throws IOException;
    void printReceipt(Integer customerId, Store store);
    void sellProducts(Store store, Staff staff, Customer customer);
}
