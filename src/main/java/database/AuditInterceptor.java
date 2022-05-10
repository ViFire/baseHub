package database;


import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.InvocationContext;

public class AuditInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        System.out.println("SimpleInterceptor - Logging BEFORE calling method :"+context.getMethod().getName() );

        Object result = context.proceed();

        System.out.println("SimpleInterceptor - Logging AFTER calling method :"+context.getMethod().getName() );

        return result;
    }
}