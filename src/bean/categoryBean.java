package bean;

import service.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by O10 on 18.05.15.
 */

@ManagedBean(name ="categoryBean")
@RequestScoped
public class CategoryBean {
    @EJB
    CategoryService categoryService;

    private String categoryName;

    private int size;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}