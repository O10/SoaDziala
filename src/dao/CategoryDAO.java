package dao;

import model.Category;

import javax.ejb.Stateless;

/**
 * Created by Wojtek on 16/05/15.
 */

@Stateless
public class CategoryDAO extends GenericDAO<Category> {

    public CategoryDAO() {
        super(Category.class);
    }
}
