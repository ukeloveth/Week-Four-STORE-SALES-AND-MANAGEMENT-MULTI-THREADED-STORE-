package models;

import servicesImpl.CashierServicesImpl;

public class SellerThread {
    private Customer customer;
    private Staff staff;
    private Store store;
    private CashierServicesImpl cashier;
    private int threadNumber;

    public SellerThread(Customer customer, Staff staff, Store store, CashierServicesImpl cashier,int threadNumber) {
        this.customer = customer;
        this.staff = staff;
        this.store = store;
        this.cashier = cashier;
        this.threadNumber = threadNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public CashierServicesImpl getCashier() {
        return cashier;
    }

    public void setCashier(CashierServicesImpl cashier) {
        this.cashier = cashier;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public void setThreadNumber(int threadNumber) {
        this.threadNumber = threadNumber;
    }
}
