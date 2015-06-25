package bean;

import javax.faces.bean.ManagedBean;

/**
 * Created by O10 on 22.06.15.
 */
@ManagedBean
public class TestBean {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void beanTest(){
        System.out.println("Invoking on action");
    }
}
