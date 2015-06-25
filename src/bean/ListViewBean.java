package bean;

import model.CategoryEntity;
import model.CategoryGroupEntity;
import model.ElementEntity;
import service.DAOService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * Created by O10 on 18.05.15.
 */
@ManagedBean(name = "listView")
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

    public void deleteElementEntity(int elementID) {
        System.out.println("Deleting element id " + elementID);
        DAOService.deleteElement(elementID);
        for(CategoryGroupEntity categoryGroupEntity:categoryGroupList){
            for(CategoryEntity categoryEntity:categoryGroupEntity.getCategoriesByCategoryGroupId()){
                for(ElementEntity elementEntity:categoryEntity.getElementsByCategoryId()){
                    if(elementEntity.getElementId()==elementID){
                        categoryEntity.getElementsByCategoryId().remove(elementEntity);
                        return;
                    }
                }
            }
        }
    }

    public void justtest(ActionEvent actionEvent){

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tesscik",  null);
        FacesContext.getCurrentInstance().addMessage(null, message);

        System.out.println("Test passed");
    }


}
