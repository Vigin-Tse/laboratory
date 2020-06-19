package com.vg.basic.spring.aop.aspect;

import com.vg.basic.spring.aop.model.UserModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 资料来源参考：https://my.oschina.net/mengyuankan/blog/2993187
 * 定义 AOP 切面
 * @Aspect  注解标识的类就是一个切面，然后在切面中定义切点（pointcut）和 增强（advice）
 * */
@Component
@Aspect
public class UserAspect {

    /**@poinCut
     *声明一个可重用的切点表达式，之后在每个方法的上方引用这个切点表达式即可
     * */
    @Pointcut("execution(* com.vg.basic.spring.aop.service.*Service.*(..))")
    public void pointcut_1(){}

    /**增强类型
     * 如果想要获取目标方法执行的参数等信息呢，我们可在 切点的方法中添参数 JoinPoint ，通过它了获取目标对象的相关信息
     * @Before()，前置增强-在目标方法执行前执行。
     * @After()，后置增强-在目标方法执行之后执行，无论是正常退出还是抛异常，都会执行。
     * @AfterReturning()，返回增强-在目标方法正常返回后执行，出现异常则不会执行，可以获取到返回值。(After先执行，再执行AfterReturning)
     * @AfterThrowing()，异常增强-在抛出异常的时候执行，不抛异常不执行。(After先执行，再执行AfterReturning)
     * @Around()，环绕增强-在目标方法执行之前和之后执行。
     * */


    /**@Before()
     * 前置增强-在目标方法执行前执行
     * */
    @Before("pointcut_1()")
    public void before_1(){
        System.out.println("@Before，目标方法执行前");
    }

    /**@After()
     * 后置增强-在目标方法执行之后执行，无论是正常退出还是抛异常，都会执行(After先执行，再执行AfterReturning)
     * */
    @After("pointcut_1()")
    public void after_1(JoinPoint joinpoint){
        Object[] args = joinpoint.getArgs();
        UserModel user = new UserModel();
        for(Object o : args){
            if (user instanceof UserModel){
                user = (UserModel) o;
            }
        }
        System.out.println("@After，目标方法执行后，参数= " + user.toString());
    }

    /**@AfterReturning()
     * 返回增强-在目标方法正常返回后执行，出现异常则不会执行，可以获取到返回值(After先执行，再执行AfterReturning)
     * 获取返回值：
     * */
    @AfterReturning(pointcut = "pointcut_1()", returning = "o")
    public void afterReturning_1(Object o){
        System.out.println("@AfterReturning，在目标方法正常返回后执行,返回值 o = " + o.toString());
    }

    /** @AfterThrowing()
     * 异常增强-在抛出异常的时候执行，不抛异常不执行。(After先执行，再执行AfterReturning)
     */
    @AfterThrowing(pointcut = "pointcut_1()", throwing = "ex")
    public void AfterThrowing_1(Exception ex){
        System.out.println("@AfterThrowing,抛出异常,ex = " + ex);
    }

    /** @Around()
     * 环绕增强-在目标方法执行之前和之后执行
     *  */
    @Around("pointcut_1()")
    public Object around_1(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        System.out.println("@Around，执行之前");
        Object object = pjp.proceed(args);
        System.out.println("@Around，执行之后");
        return object;
    }
}
