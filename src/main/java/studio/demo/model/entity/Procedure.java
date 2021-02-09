package studio.demo.model.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "procedures")
@ApiModel(description = "Details about the order.")
public class Procedure extends Service {

    private LocalDateTime date;

    public Procedure() {
    }

    public Procedure(String name, String description, BigDecimal price) {
        super(name, description, price);
        this.date = null;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Description: %s, Price: %s%n",
                this.getName(), this.getDescription(), this.getPrice());
    }

    @Column(name = "date")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

