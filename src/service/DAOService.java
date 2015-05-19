package service;

import model.CategoryEntity;
import model.CategoryGroupEntity;
import model.ElementEntity;
import model.ElementGroupEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Wojtek on 18/05/15.
 */

@Stateless
public class DAOService {
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

    public List<CategoryGroupEntity> getAllCategoryGroups() {
        return this.em.createNamedQuery("CategoryGroup.findAll").getResultList();
    }

    public List<CategoryEntity> getAllCategories() {
        return this.em.createNamedQuery("Category.findAll").getResultList();
    }

    public List<ElementGroupEntity> getAllElementGroups() {
        return this.em.createNamedQuery("ElementGroup.findAll").getResultList();
    }
}
