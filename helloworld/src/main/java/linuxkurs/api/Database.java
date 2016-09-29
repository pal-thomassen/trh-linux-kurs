package linuxkurs.api;

import javax.xml.crypto.Data;

public class Database {
    private String status;

    public Database(){}

    public Database(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
