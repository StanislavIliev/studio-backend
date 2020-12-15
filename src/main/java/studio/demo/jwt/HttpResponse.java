package studio.demo.jwt;

import java.util.Date;

public class HttpResponse {
    private Date date;
    private String name;

    public HttpResponse() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

