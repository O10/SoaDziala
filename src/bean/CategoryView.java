package bean;

import model.CategoryEntity;
import model.CategoryGroupEntity;
import service.DAOService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by O10 on 18.05.15.
 */
@ManagedBean(name = "categoryView")
@RequestScoped
public class CategoryView implements Serializable {
    @EJB
    DAOService DAOService;

    private CategoryGroupEntity[] categoryGroupEntities;

    private CategoryEntity category;

    private int categoryGroupId;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String parameterOne = params.get("categoryID");

        if (parameterOne != null) {
            category.setCategoryId(Integer.parseInt(parameterOne));

            List<CategoryEntity> allCategories = DAOService.getAllCategories();
            for (CategoryEntity cat : allCategories) {
                if (cat.getCategoryId() == category.getCategoryId()) {
                    category.setCategoryName(cat.getCategoryName());
                    category.setSizeProperty(cat.getSizeProperty());

                    return;
                }
            }
        }
    }

    public int getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(int categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
    }

    public CategoryView() {
        category = new CategoryEntity();
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public CategoryGroupEntity[] getCategoryGroupEntities() {
        List<CategoryGroupEntity> allCategoryGroups = DAOService.getAllCategoryGroups();
        categoryGroupEntities = new CategoryGroupEntity[allCategoryGroups.size()];

        for (int i = 0; i < allCategoryGroups.size(); i++) {
            categoryGroupEntities[i] = allCategoryGroups.get(i);
        }

        return categoryGroupEntities;
    }

    public String save() {
        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String parameterOne = params.get("categoryID");

        for (CategoryGroupEntity entity : categoryGroupEntities) {
            if (entity.getCategoryGroupId() == categoryGroupId) {
                category.setCategoryGroupByCategoryGroupId(entity);
            }
        }

        if (parameterOne != null) {
            DAOService.updateCategory(category);
            return "listview";
        }

        DAOService.saveCategory(category);
        return "listView";
    }


}
