package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionData {
    private String customerName;
    private LocalDateTime dateOfPurchase;
    double totalPrice;

}
