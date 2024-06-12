package club.libridge.libridgebackend.app.filters;

import static club.libridge.libridgebackend.logging.LibridgeLogger.LOGGER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import club.libridge.libridgebackend.app.persistence.ClientRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOGGER.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException {
        // HttpServletRequest req = (HttpServletRequest) request;
        // HttpServletResponse res = (HttpServletResponse) response;
        // LOGGER.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
        // LOGGER.info("Logging Response :{}", res.getContentType());
        String addressFromRequest = request.getRemoteAddr();
        clientRepository.addOrIncrease(addressFromRequest);
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void destroy() {
        LOGGER.warn("Destructing filter :{}", this);
    }

}
