package bean;

import model.ElementEntity;
import service.DAOService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by O10 on 25.06.15.
 */
@ManagedBean
@ApplicationScoped
public class TopElements {
    private List<ElementEntity> topElements;

    @EJB
    DAOService daoService;

    @PostConstruct
    public void init(){
        topElements=daoService.getTopElements();
    }

    public List<ElementEntity> getTopElements() {
        return topElements;
    }

    public void setTopElements(List<ElementEntity> topElements) {
        this.topElements = topElements;
    }
}
