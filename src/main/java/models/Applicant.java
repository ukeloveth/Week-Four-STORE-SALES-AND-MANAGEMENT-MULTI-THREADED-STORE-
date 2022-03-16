package models;

import enums.Gender;
import enums.Role;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Applicant extends  Human{
    private String qualification;
    private Role positionAppliedTo;

    public Applicant(int id, String name, Gender gender, String email, String qualification, Role positionAppliedTo) {
        super(id, name, gender, email);
        this.qualification = qualification;
        this.positionAppliedTo = positionAppliedTo;
    }
}
