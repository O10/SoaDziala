package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Wojtek on 18/05/15.
 */
@Entity
@Table(name = "category_group", schema = "public", catalog = "soa")
@NamedQueries({
        @NamedQuery(name = "CategoryGroup.findAll", query = "select c from CategoryGroupEntity c"),
        @NamedQuery(name = "CategoryGroup.findByName", query = "select c from CategoryGroupEntity c where categoryGroupName like :categoryName")
})
public class CategoryGroupEntity implements Serializable {
    private int categoryGroupId;
    private String categoryGroupName;
    private Collection<CategoryEntity> categoriesByCategoryGroupId;

    @Id
    @Column(name = "category_group_id", nullable = false, insertable = true, updatable = true)
    public int getCategoryGroupId() {
        return categoryGroupId;
    }

    public void setCategoryGroupId(int categoryGroupId) {
        this.categoryGroupId = categoryGroupId;
    }

    @Basic
    @Column(name = "category_group_name", nullable = false, insertable = true, updatable = true, length = 20)
    public String getCategoryGroupName() {
        return categoryGroupName;
    }

    public void setCategoryGroupName(String categoryGroupName) {
        this.categoryGroupName = categoryGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryGroupEntity that = (CategoryGroupEntity) o;

        if (categoryGroupId != that.categoryGroupId) return false;
        if (categoryGroupName != null ? !categoryGroupName.equals(that.categoryGroupName) : that.categoryGroupName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryGroupId;
        result = 31 * result + (categoryGroupName != null ? categoryGroupName.hashCode() : 0);
        return result;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "categoryGroupByCategoryGroupId")
    public Collection<CategoryEntity> getCategoriesByCategoryGroupId() {
        return categoriesByCategoryGroupId;
    }

    public void setCategoriesByCategoryGroupId(Collection<CategoryEntity> categoriesByCategoryGroupId) {
        this.categoriesByCategoryGroupId = categoriesByCategoryGroupId;
    }
}
