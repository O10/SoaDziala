package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by O10 on 19.05.15.
 */
@WebFilter(filterName = "BrowserFilter", urlPatterns = {"/*"})
public class BrowserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest httpRequest = (HttpServletRequest) req;
        String userAgentHeader = httpRequest.getHeader("User-Agent");
        System.out.println("User agent header is " + userAgentHeader);

        if (userAgentHeader != null && userAgentHeader.toLowerCase().contains("chrome")) {
            //return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
