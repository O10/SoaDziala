package model;

import javax.persistence.*;

/**
 * Created by Wojtek on 16/05/15.
 */
@Entity
@Table(name="category")
@NamedQueries({
        @NamedQuery(name="Category.findByName", query="select c from Category c where c.categoryName = :name"),
        @NamedQuery(name="Category.findAll", query = "select c from Category c"),
        @NamedQuery(name="Category.findNames", query = "select distinct c.categoryName from Category c")
})
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="size_property")
    private Integer sizeProperty;

    public Category() {}

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSizeProperty() {
        return sizeProperty;
    }

    public void setSizeProperty(Integer sizeProperty) {
        this.sizeProperty = sizeProperty;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
