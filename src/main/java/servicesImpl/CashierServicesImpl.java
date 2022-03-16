package servicesImpl;

import enums.Role;
import exceptions.CartIsEmptyException;
import exceptions.PurchaseCouldNotBeValidatedException;
import exceptions.StaffNotAuthorizedException;
import models.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.CashierServices;
import services.StaffServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CashierServicesImpl implements CashierServices,StaffServices{
    @Override
    public void fetchProductFromStore(Staff staff, Store store,String fileName) throws IOException {
        if (!Role.CASHIER.equals(staff.getRole())){
            throw new StaffNotAuthorizedException("You are not authorized to perform this action");
        }
        String fileToReadFrom = "src/productData/"+fileName;

        try {
            File file = new File(fileToReadFrom);   //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
            //creating Workbook instance that refers to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
            //iterating over Excel file
            Product[] productsList = new Product[sheet.getLastRowNum()];
            for (int i = 1; i <= sheet.getLastRowNum() ; i++) {
                XSSFRow row = sheet.getRow(i);
                Product product = new Product();
                Category category = new Category();
                //System.out.println(row.getSheet());
                //if(row.getRowNum() != 0){
                    for (int j = 1; j < row.getLastCellNum(); j++) {
                        XSSFCell cell = row.getCell(j);
                        switch (cell.getCellType()){
                            case STRING:
                                switch (cell.getColumnIndex()) {
                                    case 2 :
                                        product.setName(cell.getStringCellValue());
                                        break;
                                    case 3 :
                                        product.setBrand(cell.getStringCellValue());
                                    case 4 :
                                        product.setModelName(cell.getStringCellValue());
                                    case 7 :
                                        category.setName(cell.getStringCellValue());
                                    case 8 :
                                        category.setDescription(cell.getStringCellValue());
                                        product.setCategory(category);
                                        productsList[i-1] = product;
                                        store.setProductList(productsList);
                                        break;
                                    default :
                                };
                                break;
                            case NUMERIC:
                                //System.out.println(cell.getNumericCellValue());
                                switch (cell.getColumnIndex()) {
                                    case 1 :
                                        //System.out.println(cell.getNumericCellValue());
                                        product.setId((int) cell.getNumericCellValue());
                                        break;
                                    case 5 :
                                        product.setPrice(cell.getNumericCellValue());
                                        break;
                                    case 6 :
                                        product.setQuantity((int) cell.getNumericCellValue());
                                        break;
                                    default :
                                }
                                break;
                            default: throw new RuntimeException("Exception");
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printReceipt(Integer customerId, Store store) {
        List<TransactionData> transactionData = store.getTransactionHistory().get(customerId);
        System.out.println();
    }

    @Override
    public void sellProducts(Store store, Staff staff, Customer customer) {
        if (staff.getRole().equals(Role.CASHIER)) {
            Map<Product,Integer> cartItems = customer.getCart();
            if (cartItems.isEmpty()) {
                throw new CartIsEmptyException("Cart is empty");
            }
            double totalPrice = 0.00;
            for (Map.Entry<Product,Integer> item : cartItems.entrySet()) {
                totalPrice += item.getKey().getPrice() * item.getValue();
            }
            if (!(customer.getWallet() >= totalPrice)){
                throw new PurchaseCouldNotBeValidatedException("Purchase could not be validated, please check your balance and try again");
            }
            store.setAccount(store.getAccount() + totalPrice);
            customer.setWallet(customer.getWallet() - totalPrice);
            for (Product gadget : store.getProductList()) {
                if (cartItems.containsKey(gadget)){
                    int initialQuantity = cartItems.get(gadget);
                    gadget.setQuantity(gadget.getQuantity() - initialQuantity);
                }
            }
            cartItems.clear();
            Map<Integer,List<TransactionData>> transactionData = store.getTransactionHistory();

            if (transactionData.get(customer.getId()) != null){
                List<TransactionData> listOfTransactions = transactionData.get(customer.getId());
                TransactionData transaction = new TransactionData(customer.getName(), LocalDateTime.now(),totalPrice);
                listOfTransactions.add(transaction);
                transactionData.put(customer.getId(),listOfTransactions);
                store.setTransactionHistory(transactionData);
            }else{
                List<TransactionData> listOfTransactions = new ArrayList<>();
                TransactionData transaction = new TransactionData(customer.getName(), LocalDateTime.now(),totalPrice);
                listOfTransactions.add(transaction);
                transactionData.put(customer.getId(),listOfTransactions);
                store.setTransactionHistory(transactionData);
            }
            printReceipt(customer.getId(),store);
        }else {
            throw new StaffNotAuthorizedException("You are not authorized to perform this action");
        }

    }


    @Override
    public void work(Staff staff) {
        System.out.println(staff.getName() + "is working");
    }
}
