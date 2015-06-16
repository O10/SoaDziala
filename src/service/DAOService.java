package service;

import com.google.common.collect.ImmutableMap;
import model.CategoryEntity;
import model.CategoryGroupEntity;
import model.ElementEntity;
import model.ElementGroupEntity;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Wojtek on 18/05/15.
 */

@Stateless
public class DAOService {

    @Resource
    SessionContext sctx;

    @PersistenceContext
    EntityManager em;

    public void saveCategory(CategoryEntity category) {
        this.em.persist(category);
    }

    public void deleteCategory(CategoryEntity category) {
        this.em.remove(category);
    }

    public void updateCategory(CategoryEntity category) {
        this.em.merge(category);
    }

    public void saveElement(ElementEntity elementEntity) {
        this.em.persist(elementEntity);
    }

    public void updateElement(ElementEntity elementEntity) {
        this.em.merge(elementEntity);
    }

    public void deleteElement(ElementEntity elementEntity) {
        this.em.remove(elementEntity);
    }

    public List<ElementEntity> getAllElements() {
        return this.em.createNamedQuery("Element.findAll").getResultList();
    }



    public CategoryGroupEntity getByCategoryName(String name){
        try{
            CategoryGroupEntity categoryGroupEntity=(CategoryGroupEntity) this.em.createNamedQuery("CategoryGroup.findByName").setParameter("categoryName", name).getSingleResult();
            return categoryGroupEntity;
        }
        catch (Exception e){
            return null;
        }
    }

    public List<CategoryGroupEntity> getAllCategoryGroups() {

        List<CategoryGroupEntity> resultList = this.em.createNamedQuery("CategoryGroup.findAll").getResultList();

        if (sctx.isCallerInRole("USERA")) {
            resultList = resultList.stream().filter((CategoryGroupEntity c) ->
                    c.getCategoryGroupName().equals("CAVE")).collect(Collectors.toList());
        } else if (sctx.isCallerInRole("USERB")) {
            resultList = resultList.stream().filter((CategoryGroupEntity c) ->
                    c.getCategoryGroupName().equals("FOREST")).collect(Collectors.toList());
        } else if (sctx.isCallerInRole("USERC")) {
            resultList = resultList.stream().filter((CategoryGroupEntity c) ->
                    c.getCategoryGroupName().equals("TOWER")).collect(Collectors.toList());
        }
        return resultList;
    }

    public List<CategoryEntity> getAllCategories() {
        return this.em.createNamedQuery("Category.findAll").getResultList();
    }

    public List<ElementGroupEntity> getAllElementGroups() {
        return this.em.createNamedQuery("ElementGroup.findAll").getResultList();
    }
}
