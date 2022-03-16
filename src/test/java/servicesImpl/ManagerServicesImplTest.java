package servicesImpl;

import enums.Gender;
import enums.Role;
import exceptions.NoStaffToFireException;
import exceptions.StaffCouldNotBeFiredException;
import exceptions.StaffNotAuthorizedException;
import models.Applicant;
import models.Staff;
import models.Store;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ManagerServicesImplTest {
    ManagerServicesImpl managerServices;
    Applicant applicant1;
    Applicant applicant2;
    Staff staff1;
    Staff staff2;
    Store store1;
    @Before
    public void setUp() throws Exception {
        store1 = new Store();
        managerServices = new ManagerServicesImpl();
        applicant1 = new Applicant(1,"Tolu", Gender.MALE,"tolu@live.com","BSC", Role.CASHIER);
        applicant2 = new Applicant(2,"Mary", Gender.MALE,"mary@live.com","HND", Role.CASHIER);
        staff1 = new Staff(3,"dennis",Gender.MALE,"dennis@live.com","DEC-JAV-3",Role.MANAGER,"MSC");
        staff2 = new Staff(4,"Sammy",Gender.MALE,"sammy@live.com","DEC-JAV-4",Role.CASHIER,"MSC");

    }

    @Test
    public void shouldHireApplicantIfHeOrSheHasTheRightQualification() {
        managerServices.hireApplicant(applicant1,staff1,store1);
        assertEquals(1,store1.getStaffList().size());
        //assertNotNull(store1.getStaffList());
    }

    @Test
    public void shouldThrowStaffNotAuthorizedExceptionIfStaffHiringDoesNotHaveARoleOfCashier() {
        assertThrows(StaffNotAuthorizedException.class,()->{
            managerServices.hireApplicant(applicant1,staff2,store1);

        });
    }

    @Test
    public void shouldFireStaffIsInTheStaffListAndCanBeFired() {
        managerServices.hireApplicant(applicant1,staff1,store1);
        managerServices.fireStaff(store1,staff1,1);
        //store1.getStaffList().get(0).setPerformance(70);
        assertEquals(0,store1.getStaffList().size());
    }

    @Test
    public void shouldThrowNoStaffToFireExceptionIfStaffListIsEmptyOrStaffIsNotInStaffList() {
        assertThrows(NoStaffToFireException.class,()->{
            managerServices.fireStaff(store1,staff1,1);
        });
        managerServices.hireApplicant(applicant1,staff1,store1);
        assertThrows(NoStaffToFireException.class,()->{
            managerServices.fireStaff(store1,staff1,4);
        });

    }

    @Test
    public void shouldThrowStaffCouldNotBeFiredExceptionIfStaffHasGoodPerformance() {
        managerServices.hireApplicant(applicant1,staff1,store1);
        store1.getStaffList().get(0).setPerformance(70);//Good performance is 50 and above
        assertThrows(StaffCouldNotBeFiredException.class,()->{
            managerServices.fireStaff(store1,staff1,1);
        });
    }

    @Test
    public void shouldThrowStaffNotAuthorizedExceptionIfStaffThatFiresHasRoleThatIsNotManager () {
        managerServices.hireApplicant(applicant1,staff1,store1);
        managerServices.hireApplicant(applicant2,staff1,store1);
        assertThrows(StaffNotAuthorizedException.class,()->{
            managerServices.fireStaff(store1,store1.getStaffList().get(0),2 );
        });
    }



    @Test
    public void work() {
    }
}