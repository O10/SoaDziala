package rest;

/**
 * Created by O10 on 16.06.15.
 */

import model.CategoryEntity;
import model.CategoryGroupEntity;
import model.ElementEntity;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by O10 on 16.06.15.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/categoriesGroups")
public class MessageRestService {
    @EJB
    service.DAOService DAOService;

    @GET
    @Produces("application/json")
    public List<ExportedCategoryGroup> getAllCategoriesGroups() {
        return getAll();
    }

    @GET
    @Produces("application/xml")
    public List<ExportedCategoryGroup> getAllCategoriesInXml() {
        return getAll();
    }

    private List<ExportedCategoryGroup> getAll() {
        List<CategoryGroupEntity> allCategoryGroups = DAOService.getAllCategoryGroups();
        List<ExportedCategoryGroup> exportedCategoryGroups = new ArrayList<>();

        for (int i = 0; i < allCategoryGroups.size(); i++) {
            exportedCategoryGroups.add(generateExported(allCategoryGroups.get(i)));
        }

        return exportedCategoryGroups;
    }

    @POST
    @Path("/{category}/{cat_id}")
    public Response addElement(@PathParam("category") String category, @PathParam("cat_id") int catId, ExportedElement element) {
        CategoryGroupEntity ca = DAOService.getByCategoryName(category);
        System.out.println("name is "+element.getNameProperty()+"  id "+catId);

        if (ca == null) {
            return Response.status(404).build();
        }

        for (CategoryEntity categoryEntity : ca.getCategoriesByCategoryGroupId()) {
            if (categoryEntity.getCategoryId() == catId) {
                ElementEntity elementEntity = new ElementEntity();
                elementEntity.setAttributeProperty(element.getAttributeProperty());
                elementEntity.setCategoryByCategoryId(categoryEntity);
                elementEntity.setChargeProperty(element.getChargeProperty());
                elementEntity.setNameProperty(element.getNameProperty());
                DAOService.saveElement(elementEntity);
                return Response.status(201).build();

            }
        }
//        return Response.status(404).build();
        return Response.status(404).build();



    }


    @GET
    @Path("/{category}")
    @Produces("application/json")
    public ExportedCategoryGroup getCategoryGroup(@PathParam("category") String category) {
        return getByName(category);

    }


    @GET
    @Path("/{category}")
    @Produces("application/xml")
    public ExportedCategoryGroup getCategoryGroupinXML(@PathParam("category") String category) {
        return getByName(category);
    }

    private ExportedCategoryGroup getByName(String category) {
        CategoryGroupEntity categoryName = DAOService.getByCategoryName(category);

        if (categoryName == null) {
            return null;
        } else {
            return generateExported(categoryName);
        }
    }


    private ExportedCategoryGroup generateExported(CategoryGroupEntity categoryGroupEntity) {
        ExportedCategoryGroup exportedCategoryGroup = new ExportedCategoryGroup();
        exportedCategoryGroup.setCategoryGroupName(categoryGroupEntity.getCategoryGroupName());
        exportedCategoryGroup.setId(categoryGroupEntity.getCategoryGroupId());
        exportedCategoryGroup.setCategories(new ArrayList<>());
        for (CategoryEntity categoryEntity : categoryGroupEntity.getCategoriesByCategoryGroupId()) {
            ExportedCategory exportedCategory = new ExportedCategory();
            exportedCategory.setCategoryId(categoryEntity.getCategoryId());
            exportedCategory.setCategoryName(categoryEntity.getCategoryName());
            exportedCategory.setSizeProperty(categoryEntity.getSizeProperty());
            exportedCategory.setElements(new ArrayList<>());
            for (ElementEntity elementEntity : categoryEntity.getElementsByCategoryId()) {
                ExportedElement exportedElement = new ExportedElement();
                exportedElement.setAttributeProperty(elementEntity.getAttributeProperty());
                exportedElement.setChargeProperty(elementEntity.getChargeProperty());
                exportedElement.setElementId(elementEntity.getElementId());
                exportedElement.setNameProperty(elementEntity.getNameProperty());

                exportedCategory.getElements().add(exportedElement);
            }

            exportedCategoryGroup.getCategories().add(exportedCategory);
        }


        return exportedCategoryGroup;
    }

    @XmlRootElement(name = "categorygroup")
    private static class ExportedCategoryGroup {
        private String categoryGroupName;
        private int id;
        private List<ExportedCategory> categories;

        @XmlElement
        public String getCategoryGroupName() {
            return categoryGroupName;
        }

        public void setCategoryGroupName(String categoryGroupName) {
            this.categoryGroupName = categoryGroupName;
        }

        @XmlElement
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @XmlElement
        public List<ExportedCategory> getCategories() {
            return categories;
        }

        public void setCategories(List<ExportedCategory> categories) {
            this.categories = categories;
        }
    }

    @XmlRootElement(name = "category")
    private static class ExportedCategory {
        private int categoryId;
        private String categoryName;
        private int sizeProperty;
        private List<ExportedElement> elements;

        @XmlElement
        public List<ExportedElement> getElements() {
            return elements;
        }

        public void setElements(List<ExportedElement> elements) {
            this.elements = elements;
        }

        @XmlElement
        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        @XmlElement
        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        @XmlElement
        public int getSizeProperty() {
            return sizeProperty;
        }

        public void setSizeProperty(int sizeProperty) {
            this.sizeProperty = sizeProperty;
        }
    }

    @XmlRootElement(name = "element")
    private static class ExportedElement {
        private int elementId;
        private String nameProperty;
        private int chargeProperty;
        private String attributeProperty;

        @XmlElement
        public String getAttributeProperty() {
            return attributeProperty;
        }

        public void setAttributeProperty(String attributeProperty) {
            this.attributeProperty = attributeProperty;
        }

        @XmlElement
        public int getChargeProperty() {
            return chargeProperty;
        }

        public void setChargeProperty(int chargeProperty) {
            this.chargeProperty = chargeProperty;
        }

        @XmlElement
        public int getElementId() {
            return elementId;
        }

        public void setElementId(int elementId) {
            this.elementId = elementId;
        }

        public String getNameProperty() {
            return nameProperty;
        }

        public void setNameProperty(String nameProperty) {
            this.nameProperty = nameProperty;
        }
    }

}