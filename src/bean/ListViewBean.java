package bean;

import model.CategoryEntity;
import model.CategoryGroupEntity;
import service.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Created by O10 on 18.05.15.
 */
@ManagedBean(name = "listView")
@RequestScoped
public class ListViewBean {
    List<CategoryGroupEntity> categoryGroupList;

    @EJB
    CategoryService categoryService;

    public ListViewBean() {
    }

    public List<CategoryGroupEntity> getCategoryGroupList() {
        if (this.categoryGroupList == null) {
            categoryGroupList = categoryService.getAllCategoryGroups();
        }
        return categoryGroupList;
    }

    public String save(){

        return "good";
    }


}
