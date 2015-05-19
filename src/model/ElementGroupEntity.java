package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by O10 on 19.05.15.
 */

@Entity
@Table(name = "element_group", schema = "public", catalog = "soa")
@NamedQueries({
        @NamedQuery(name = "ElementGroup.findAll", query = "select e from ElementGroupEntity e")
})
public class ElementGroupEntity {
    private int elementGroupId;
    private String elementGroupName;
    private Collection<ElementEntity> elementEntities;

    @Id
    @Column(name = "element_group_id", nullable = false, insertable = true, updatable = true)
    public int getElementGroupId() {
        return elementGroupId;
    }

    public void setElementGroupId(int elementGroupId) {
        this.elementGroupId = elementGroupId;
    }

    @Basic
    @Column(name = "element_group_name", nullable = false, insertable = true, updatable = true, length = 20)
    public String getElementGroupName() {
        return elementGroupName;
    }

    public void setElementGroupName(String elementGroupName) {
        this.elementGroupName = elementGroupName;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "elementGroupEntity")
    public Collection<ElementEntity> getElementEntities() {
        return elementEntities;
    }

    public void setElementEntities(Collection<ElementEntity> elementEntities) {
        this.elementEntities = elementEntities;
    }
}
