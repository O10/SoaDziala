package bean;

import javax.faces.bean.ManagedBean;

/**
 * Created by Wojtek on 16/05/15.
 */
@ManagedBean(name = "elementBean")
public class elementBean {
    private String name;
    private Integer charge;
    private String attribute;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
