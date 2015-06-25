package filters;

import model.ElementEntity;
import service.DAOService;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.List;

/**
 * Created by O10 on 25.06.15.
 */
@Interceptor
@CheckIntercept
public class CheckMaxInterceptor {
    @EJB
    DAOService daoService;

    @AroundInvoke
    public Object logMethodEntry(InvocationContext ctx) throws Exception {
        System.out.println("Entering method: " + ctx.getMethod().getName());
        Object[] parameters = ctx.getParameters();

        ElementEntity elementEntity = (ElementEntity) parameters[0];

        List<ElementEntity> topElements = daoService.getTopElements();

        if (topElements == null || topElements.size() == 0) {
            return ctx.proceed();
        }

        ElementEntity currentTop = topElements.get(0);

        if (elementEntity.getChargeProperty() > currentTop.getChargeProperty()) {
            elementEntity.setChargeProperty(currentTop.getChargeProperty());
        }


        return ctx.proceed();
    }
}
