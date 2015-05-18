package model;

import service.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by O10 on 18.05.15.
 */
@ManagedBean(name = "categoryView")
@RequestScoped
public class CategoryView {
    @EJB
    CategoryService categoryService;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String save(){
        this.categoryService.save(category);
        return "good";
    }


}
