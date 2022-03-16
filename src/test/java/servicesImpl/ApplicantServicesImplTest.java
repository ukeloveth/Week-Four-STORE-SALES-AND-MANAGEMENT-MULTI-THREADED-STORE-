package servicesImpl;

import enums.Role;
import exceptions.ApplicantAlreadyExistsException;
import models.Applicant;
import models.Store;

import static org.junit.Assert.*;

public class ApplicantServicesImplTest {
    Applicant applicant1;
    Applicant applicant2;
    ApplicantServicesImpl applicantServicesImpl1;
    Store store1;
    @org.junit.Before
    public void setUp() throws Exception {
        applicant1 = new Applicant();
        applicant2 = new Applicant();
        applicantServicesImpl1 = new ApplicantServicesImpl();
        store1 = new Store();
    }

    @org.junit.Test
    public void shouldHireApplicantIfApplicantListIsNull() {
        applicantServicesImpl1.apply(applicant1,store1, Role.CASHIER);
        assertEquals(1,store1.getApplicantList().size());
    }

    @org.junit.Test
    public void shouldThrowApplicantAlreadyExistsExceptionIfApplicantAlreadyInTheList() {
        applicantServicesImpl1.apply(applicant1,store1, Role.CASHIER);
        assertThrows(ApplicantAlreadyExistsException.class,()->{
            applicantServicesImpl1.apply(applicant1,store1, Role.CASHIER);
        });
    }
}