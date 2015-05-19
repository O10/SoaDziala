package bean;

import model.CategoryEntity;
import model.ElementEntity;
import model.ElementGroupEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by O10 on 19.05.15.
 */
@ManagedBean(name = "elementView")
@RequestScoped
public class ElementView implements Serializable {
    @EJB
    service.DAOService DAOService;

    private CategoryEntity[] categoryEntities;
    private ElementGroupEntity[] elementGroupEntities;

    private ElementEntity elementEntity;

    private int categoryId;
    private int elementGroupId;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getElementGroupId() {
        return elementGroupId;
    }

    public void setElementGroupId(int elementGroupId) {
        this.elementGroupId = elementGroupId;
    }

    public ElementView() {
        elementEntity = new ElementEntity();
    }

    public CategoryEntity[] getCategoryEntities() {
        List<CategoryEntity> allCategoryGroups = DAOService.getAllCategories();
        categoryEntities = new CategoryEntity[allCategoryGroups.size()];

        for (int i = 0; i < allCategoryGroups.size(); i++) {
            categoryEntities[i] = allCategoryGroups.get(i);
        }

        return categoryEntities;
    }

    public ElementGroupEntity[] getElementGroupEntities() {
        List<ElementGroupEntity> allElementGroups = DAOService.getAllElementGroups();
        elementGroupEntities = new ElementGroupEntity[allElementGroups.size()];

        for (int i = 0; i < allElementGroups.size(); i++) {
            elementGroupEntities[i] = allElementGroups.get(i);
        }
        return elementGroupEntities;
    }

    public ElementEntity getElementEntity() {
        return elementEntity;
    }

    public void setElementEntity(ElementEntity elementEntity) {
        this.elementEntity = elementEntity;
    }


    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String parameterOne = params.get("elementID");

        if (parameterOne != null) {
            elementEntity.setElementId(Integer.parseInt(parameterOne));

            List<ElementEntity> allElements = DAOService.getAllElements();
            for (ElementEntity el : allElements) {
                if (elementEntity.getElementId() == el.getElementId()) {
                    elementEntity.setChargeProperty(el.getChargeProperty());
                    elementEntity.setAttributeProperty(el.getAttributeProperty());
                    elementEntity.setNameProperty(el.getNameProperty());
                    return;
                }

            }
        }
    }

    public String save() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String parameterOne = params.get("elementID");

        for (CategoryEntity entity : categoryEntities) {
            if (entity.getCategoryId() == categoryId) {
                elementEntity.setCategoryByCategoryId(entity);
            }
        }

        for (ElementGroupEntity entity : elementGroupEntities) {
            if (entity.getElementGroupId() == elementGroupId) {
                elementEntity.setElementGroupEntity(entity);
            }
        }


        if (parameterOne != null) {
            DAOService.updateElement(elementEntity);
            return "listView";
        }

        DAOService.saveElement(elementEntity);
        return "listView";
    }
}
