package dwl.config.plugins;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(* dwl.controller.*..*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();

        String requestArgs = objectMapper.writeValueAsString(proceedingJoinPoint.getArgs());
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error("Request URL : [{}], HTTP Method : [{}]\nRequest Args : {}\nTime-Consuming : {} ms",
                    request.getRequestURI(),
                    request.getMethod(),
                    requestArgs,
                    System.currentTimeMillis() - startTime, e);
            throw e;
        }
        String responseArgs = objectMapper.writeValueAsString(result);
        logger.info("Request URL : [{}], HTTP Method : [{}]\nRequest Args : {}\nResponse Args  : {}\nTime-Consuming : {} ms",
                request.getRequestURI(),
                request.getMethod(),
                requestArgs,
                responseArgs,
                System.currentTimeMillis() - startTime);
        return result;
    }

}
