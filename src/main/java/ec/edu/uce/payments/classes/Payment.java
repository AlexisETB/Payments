package ec.edu.uce.payments.classes;

public abstract class Payment implements IPay {
    protected String detail;
    protected String description;

    public Payment(String detail, String description) {
        this.detail = detail;
        this.description = description;
    }

    public Payment() {
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("detail='" + detail + '\'' +
                ", description=" + description);
    }
}
