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

public class SellingThreadImpl implements Runnable{

    @Override
    public void run() {
        System.out.println();
    }


}
