package models;

import enums.Gender;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer extends Human{
    private Map<Product,Integer> cart;
    private double wallet;

    public Customer(int id, String name, Gender gender, String email) {
        super(id, name, gender, email);
        this.cart = new HashMap<>();
        this.wallet = 0;
    }
}
