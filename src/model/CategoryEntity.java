package model;

import javax.persistence.*;

/**
 * Created by Wojtek on 18/05/15.
 */
@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name="Category.findByName", query="select c from CategoryEntity c where c.categoryName = :name"),
        @NamedQuery(name="Category.findAll", query = "select c from CategoryEntity c"),
        @NamedQuery(name="Category.findNames", query = "select distinct c.categoryName from CategoryEntity c")
})
public class CategoryEntity {
    private int categoryId;
    private String categoryName;
    private int sizeProperty;

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "size_property")
    public int getSizeProperty() {
        return sizeProperty;
    }

    public void setSizeProperty(int sizeProperty) {
        this.sizeProperty = sizeProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (categoryId != that.categoryId) return false;
        if (sizeProperty != that.sizeProperty) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + sizeProperty;
        return result;
    }
}
