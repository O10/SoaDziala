package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Wojtek on 18/05/15.
 */
@Entity
@Table(name = "category", schema = "public", catalog = "soa")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "select c from CategoryEntity c")
})
public class CategoryEntity {
    private int categoryId;
    private String categoryName;
    private int sizeProperty;
    private CategoryGroupEntity categoryGroupByCategoryGroupId;
    private Collection<ElementEntity> elementsByCategoryId;

    @Id
    @Column(name = "category_id", nullable = false, insertable = true, updatable = true)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "category_name", nullable = false, insertable = true, updatable = true, length = 20)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "size_property", nullable = false, insertable = true, updatable = true)
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

    @ManyToOne
    @JoinColumn(name = "category_group_id", referencedColumnName = "category_group_id")
    public CategoryGroupEntity getCategoryGroupByCategoryGroupId() {
        return categoryGroupByCategoryGroupId;
    }

    public void setCategoryGroupByCategoryGroupId(CategoryGroupEntity categoryGroupByCategoryGroupId) {
        this.categoryGroupByCategoryGroupId = categoryGroupByCategoryGroupId;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryByCategoryId")
    public Collection<ElementEntity> getElementsByCategoryId() {
        return elementsByCategoryId;
    }

    public void setElementsByCategoryId(Collection<ElementEntity> elementsByCategoryId) {
        this.elementsByCategoryId = elementsByCategoryId;
    }
}
