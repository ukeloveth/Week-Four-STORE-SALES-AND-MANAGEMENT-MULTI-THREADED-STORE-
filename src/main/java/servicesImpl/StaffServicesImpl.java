package servicesImpl;

import models.Staff;
import services.StaffServices;

public class StaffServicesImpl implements StaffServices {

    @Override
    public void work(Staff staff) {
        System.out.println(staff.getName() + "is working");
    }
}
