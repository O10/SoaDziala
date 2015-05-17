package bean;

import javax.faces.bean.ManagedBean;

/**
 * Created by Wojtek on 16/05/15.
 */

@ManagedBean(name = "categoryBean")
public class categoryBean {
    private Integer size;

    public void addCategory() {
        System.out.println("");
    }

    public void editCategory() {
        System.out.println("");
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
