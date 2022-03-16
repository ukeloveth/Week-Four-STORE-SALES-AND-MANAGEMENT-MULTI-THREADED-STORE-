package services;

import models.Applicant;
import models.Staff;
import models.Store;

import java.util.List;

public interface ManagerServices {
    void hireApplicant(Applicant applicant, Staff staff, Store store);
    void fireStaff(Store store, Staff staff, int staffUniqueId);
}
