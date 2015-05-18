package bean;

import model.CategoryEntity;
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

    private CategoryEntity category;

    public CategoryView() {
        category = new CategoryEntity();
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String save(){
        return "good";
    }


}
