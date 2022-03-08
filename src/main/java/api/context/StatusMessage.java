package api.context;

public enum StatusMessage {

    DUPLICATE("E1000", "Entity with primary key already exist"),
    PERSISTANCE("P1000", "Could not update/save entity")
    ;



    private String code;
    private String description;

    StatusMessage(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
