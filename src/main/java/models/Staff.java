package models;

import enums.Gender;
import enums.Role;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Staff extends Human{
    private String staffId;
    private Role role;
    private String qualification;
    private int performance;

    public Staff(int id, String name, Gender gender, String email, String staffId, Role role, String qualification) {
        super(id, name, gender, email);
        this.staffId = staffId;
        this.role = role;
        this.qualification = qualification;
        this.performance = 0;
    }
}
