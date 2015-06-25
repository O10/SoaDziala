package bean;

import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * Created by O10 on 25.06.15.
 */

@PushEndpoint("/push")
public class TopResource {
    @Inject
    TopElements topElements;

    @OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(String count) {
        System.out.println("Message received "+count);
        topElements.init();
        return count;
    }
}
