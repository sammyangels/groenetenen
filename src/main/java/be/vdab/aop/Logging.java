package be.vdab.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
class Logging {
    private final static Logger LOGGER = Logger.getLogger(Logging.class.getName());

    @AfterThrowing(pointcut = be.vdab.aop.PointcutExpressions.servicesEnTransacties(), throwing = "ex")
    void schrijfException(JoinPoint joinPoint, Throwable ex) {
        StringBuilder builder = new StringBuilder("\nTijdstip\t").append(new Date());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !"anonymousUser".equals(authentication.getName())) {
            builder.append("\nGebruiker\t").append(authentication.getName());
        }
        builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
        for (Object object : joinPoint.getArgs()) {
            builder.append("\nParameter\t").append(object);
        }
        LOGGER.log(Level.SEVERE, builder.toString(), ex);
    }
}
