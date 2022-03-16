package services;

import enums.Role;
import models.Applicant;
import models.Store;

public interface ApplicantServices {
    void apply(Applicant applicant, Store store, Role positionAppliedTo);
}
