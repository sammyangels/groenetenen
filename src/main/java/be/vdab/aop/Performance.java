package be.vdab.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
class Performance {
    private final static Logger LOGGER = Logger.getLogger(Performance.class.getName());

    @Around("be.vdab.aop.PointcutExpressions.services()")
    Object schrijfPerformance(ProceedingJoinPoint joinPoint)
            throws Throwable {
        long voor = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long duurtijd = System.nanoTime() - voor;
            LOGGER.info(joinPoint.getSignature().toLongString() +
                    " duurde " + duurtijd + " nanoseconden");
        }
    }
}
