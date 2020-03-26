import java.util.UUID;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction {
    private Double value;
    private Date date;
    private UUID uniqueId;

    Transaction(double val){
        this.value = val;
        this.date = Date.from(Instant.now());
        this.uniqueId = UUID.randomUUID();
    }
    
    @Override
    public String toString() {
        return (value + ", " + date + ", " + uniqueId);
    }
}
