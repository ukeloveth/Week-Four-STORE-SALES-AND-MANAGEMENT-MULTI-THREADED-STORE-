package servicesImpl;

import enums.Role;
import exceptions.ApplicantAlreadyExistsException;
import models.Applicant;
import models.Store;
import services.ApplicantServices;

import java.util.List;

public class ApplicantServicesImpl implements ApplicantServices {
    @Override
    public void apply(Applicant applicant, Store store, Role positionAppliedTo) {
        List<Applicant> applicantList = store.getApplicantList();
        if (applicantList.contains(applicant)){
            throw new ApplicantAlreadyExistsException("Applicant already in database");
        }
        applicantList.add(applicant);
        store.setApplicantList(applicantList);
    }
}
