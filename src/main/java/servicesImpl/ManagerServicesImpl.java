package servicesImpl;

import enums.Role;
import exceptions.NoStaffToFireException;
import exceptions.StaffCouldNotBeFiredException;
import exceptions.StaffNotAuthorizedException;
import models.Applicant;
import models.Staff;
import models.Store;
import services.ManagerServices;
import services.StaffServices;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerServicesImpl implements ManagerServices, StaffServices {
    @Override
    public void hireApplicant(Applicant applicant, Staff staff, Store store) {
        if(Role.MANAGER.equals(staff.getRole())){
            if(applicant.getQualification().equals("MSC") || applicant.getQualification().equals("BSC")){
                convertApplicantToStaffAndAddToCompany(store, applicant);
            }
        }
        else {
            throw new StaffNotAuthorizedException("You are not authorized to perform this transaction");
        }
    }

    @Override
    public void fireStaff(Store store, Staff staff, int staffUniqueId) {
        if (Role.MANAGER.equals(staff.getRole())){
            List<Staff> staffList = store.getStaffList();
            Staff staffToFire = null;
            for(Staff employee : staffList) {
                if (staffUniqueId == employee.getId()) {
                    staffToFire = employee;
                    break;
                }
            }
            if (staffToFire != null){
                if (staffToFire.getPerformance() <= 50) {
                    staffList.remove(staffToFire);
                    store.setStaffList(staffList);
                    System.out.println("Staff has been fired");
                }else{
                    throw new StaffCouldNotBeFiredException("Staff could not be fired. Their performance is okay");
                }
            }else {
                throw new NoStaffToFireException("No staff to fire");
            }
        }else {
            throw new StaffNotAuthorizedException("You are not authorized to perform this transaction");
        }

    }

    private void convertApplicantToStaffAndAddToCompany(Store store, Applicant applicant) {
        Staff newStaff = new Staff();
        newStaff.setId(applicant.getId());
        newStaff.setName(applicant.getName());
        newStaff.setGender(applicant.getGender());
        newStaff.setEmail((applicant.getEmail()));
        int randomNum = (int) (Math.random() * 1_000);
        String randomStaffId = "DEC-" + "JAV-" + randomNum;
        newStaff.setStaffId(randomStaffId);
        newStaff.setRole(applicant.getPositionAppliedTo());
        newStaff.setQualification(applicant.getQualification());
        store.getStaffList().add(newStaff);;
        store.getApplicantList().remove(applicant);
    }

    @Override
    public void work(Staff staff) {
        System.out.println(staff.getName() + "is working");
    }
}
