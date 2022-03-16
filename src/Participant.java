import java.util.Date;

public class Participant {

    String phone;
    private String email;
    private Date receiptDate;
    private int receiptId;

    public Participant(String phone, String email, Date receiptDate, int receiptId) {
        this.phone = phone;
        this.email = email;
        this.receiptDate = receiptDate;
        this.receiptId = receiptId;
    }
    public Participant() {}


    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        
        this.email = email;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", receiptDate=" + receiptDate +
                ", receiptId=" + receiptId +
                '}';
    }
}
