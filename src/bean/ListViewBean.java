package bean;

import model.CategoryEntity;
import model.CategoryGroupEntity;
import model.ElementEntity;
import service.DAOService;

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
    DAOService DAOService;

    public ListViewBean() {
    }

    public List<CategoryGroupEntity> getCategoryGroupList() {
        if (this.categoryGroupList == null) {
            categoryGroupList = DAOService.getAllCategoryGroups();
        }
        return categoryGroupList;
    }

    public String deleteCategoryEntity(int id) {
        System.out.println("Deleting id " + id);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(id);
        DAOService.deleteCategory(categoryEntity);
        return "listView";
    }

    public String deleteElementEntity(int elementID) {
        System.out.println("Deleting id " + elementID);
        ElementEntity elementEntity = new ElementEntity();
        elementEntity.setElementId(elementID);
        DAOService.deleteElement(elementEntity);
        return "listView";
    }


}
