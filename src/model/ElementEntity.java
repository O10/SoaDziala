package model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Wojtek on 18/05/15.
 */
@Entity
@Table(name = "element", schema = "public", catalog = "soa")
@NamedQueries({
        @NamedQuery(name = "Element.findAll", query = "select e from ElementEntity e"),
        @NamedQuery(name = "Element.findTop", query = "select e from ElementEntity e order by chargeProperty desc ")
})
public class ElementEntity {
    private int elementId;
    private String nameProperty;
    private int chargeProperty;
    private String attributeProperty;
    private CategoryEntity categoryByCategoryId;
    private ElementGroupEntity elementGroupEntity;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "element_id", nullable = false, insertable = true, updatable = true)
    public int getElementId() {
        return elementId;
    }

    public void setElementId(int elementId) {
        this.elementId = elementId;
    }

    @Basic
    @Column(name = "name_property", nullable = false, insertable = true, updatable = true, length = 20)
    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    @Basic
    @Column(name = "charge_property", nullable = false, insertable = true, updatable = true)
    public int getChargeProperty() {
        return chargeProperty;
    }

    public void setChargeProperty(int chargeProperty) {
        this.chargeProperty = chargeProperty;
    }

    @Basic
    @Column(name = "attribute_property", nullable = false, insertable = true, updatable = true, length = 20)
    public String getAttributeProperty() {
        return attributeProperty;
    }

    public void setAttributeProperty(String attributeProperty) {
        this.attributeProperty = attributeProperty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementEntity that = (ElementEntity) o;

        if (chargeProperty != that.chargeProperty) return false;
        if (elementId != that.elementId) return false;
        if (attributeProperty != null ? !attributeProperty.equals(that.attributeProperty) : that.attributeProperty != null)
            return false;
        if (nameProperty != null ? !nameProperty.equals(that.nameProperty) : that.nameProperty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = elementId;
        result = 31 * result + (nameProperty != null ? nameProperty.hashCode() : 0);
        result = 31 * result + chargeProperty;
        result = 31 * result + (attributeProperty != null ? attributeProperty.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    @ManyToOne
    @JoinColumn(name = "element_group_id", referencedColumnName = "element_group_id")
    public ElementGroupEntity getElementGroupEntity() {
        return elementGroupEntity;
    }

    public void setElementGroupEntity(ElementGroupEntity elementGroupEntity) {
        this.elementGroupEntity = elementGroupEntity;
    }
}
