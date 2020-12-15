package studio.demo.model.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import static studio.demo.contants.GlobalConstants.*;


public class PromotionServiceModel extends BaseServiceModel {
    private String name;
    private String description;
    private BigDecimal price;

    public PromotionServiceModel() {
    }

    @Length(min = 2 ,message = "Name must be more than two characters.")
    @Pattern(regexp = PROMOTION_NAME_REGEX , message = PROMOTION_NAME_NOT_CORRECT)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Length(min = 3, message = "Description must be more than 3 characters.")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0", message = "Enter valid price.")
    @NotNull(message = PRICE_NOT_NULL)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
