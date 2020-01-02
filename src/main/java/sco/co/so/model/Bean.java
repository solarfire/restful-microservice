package sco.co.so.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created on 01/12/2017.
 */
@ApiModel(description = "My description of the Bean")  //For SWAGGER api docs
public class Bean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@Size(min=2, message = "'name' must be at least 2 characters")
    @NotNull(message= "'name' cannot be null")
    @ApiModelProperty(value="cannot be null and must be at least 2 characters")//For SWAGGER api docs , enhance bean docs
    private String name;

	@Past(message = "'date' must be in the past")
    @ApiModelProperty(value="must be in the past")//For SWAGGER api docs , enhance bean docs
	private Date date;

    public Bean() {

    }

    public Bean(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bean bean = (Bean) o;
        return Objects.equals(id, bean.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
