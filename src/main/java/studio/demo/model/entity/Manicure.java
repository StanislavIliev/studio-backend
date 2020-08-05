package studio.demo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "manicures")
public class Manicure extends BaseEntity{

    private ManicureType manicureType;
    private String description;

    public Manicure() {
    }
    public Manicure(ManicureType manicureType , String description) {
        this.manicureType=manicureType;
        this.description=description;
    }

    @Column(name = "description",columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Enumerated(EnumType.ORDINAL)
    public ManicureType getManicureType() {
        return manicureType;
    }

    public void setManicureType(ManicureType manicureType) {
        this.manicureType = manicureType;
    }
}
