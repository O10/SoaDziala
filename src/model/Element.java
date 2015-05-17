package model;

import javax.persistence.*;

/**
 * Created by Wojtek on 16/05/15.
 */
@Entity
@Table(name="element")
public class Element {

    @Id
    @GeneratedValue
    @Column(name="element_id")
    private int id;

    @Column(name="element_type")
    private String elementName;

    @Column(name="name_property")
    private String nameProperty;

    @Column(name="charge_property")
    private Integer chargeProperty;

    //@Enumerated(EnumType.STRING)
    @Column(name="attribute_property")
    private String attributeProperty;

    public Element() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty) {
        this.nameProperty = nameProperty;
    }

    public Integer getChargeProperty() {
        return chargeProperty;
    }

    public void setChargeProperty(Integer chargeProperty) {
        this.chargeProperty = chargeProperty;
    }

    public String getAttributeProperty() {
        return attributeProperty;
    }

    public void setAttributeProperty(String attributeProperty) {
        this.attributeProperty = attributeProperty;
    }
}
