package ait.cochort49.shop_g49_1.lodding;


import ait.cochort49.shop_g49_1.model.dto.ProductDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;



// AspectJ - фреймворк


@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // JoinPoint - точка соединение (где будет выполняться)

    @Pointcut("execution(* ait.cochort49.shop_g49_1.service.ProductServiceImpl.saveProduct(..))")
    public void saveProductPoint(){
        // Метод без тела, используется для создания PointCut
    }

//    @Before("saveProductPoint()")
//    public void beforeSavingProduct() {
//        logger.info("Method saveProduct in class ProductServiceImpl was called");
//    }

    @Before("saveProductPoint()")
    public void beforeSavingProductWithArgs(JoinPoint joinPoint) {
        // Извлекаем параметры
        Object[] params = joinPoint.getArgs();
        // Логируем информацию о вызове метода
        logger.info("Method saveProduct in class ProductServiceImpl was called with parameter: {}", params[0]);
//        logger.info("Method saveProduct in class ProductServiceImpl (arrays): {}", Arrays.toString(params));

    }

    @After("saveProductPoint()")
    public void afterSavingProduct(){
        logger.info("Method saveProduct in class ProductServiceImpl finished its execution");
    }

//    @AfterReturning
//    @AfterThrowing
//    public void afterReturning(JoinPoint joinPoint, Exception ex) {}

    @Pointcut("execution(* ait.cochort49.shop_g49_1.service.ProductServiceImpl.getAllActiveProducts(..))")
    public void getAllProducts() {
        // Пустой
    }

    @Around("getAllProducts()")
    public Object aroundGetAllProducts(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = null;

        try {
            // Логирую до выполнения основного метода
            logger.info("Method `getAllActiveProducts` of the class ProductServiceImpl was called");

            // Выполняю основной метож
            result = joinPoint.proceed();

            //Логирование после успешного выполнения метод
            logger.info("Method `getAllActiveProducts` successfully returned with result: {}", result);

            // Изменяем результат, добавляя доп.логику

            result = ((List<ProductDTO>) result).stream()
                    .filter(product -> product.getPrice().doubleValue() > 1)
                    .toList();

        } catch (Throwable ex){
            // Логируем, если произошло исключение
            logger.error("Method `getAllActiveProducts` threw an exception: {}", ex.getMessage());

        }

        // Возвращаю модифицированный результат
        return  result == null ? Collections.emptyList() : result;

    }
    // Pointcut для всех методов всех классов в пакете `service`
    @Pointcut("execution(* ait.cochort49.shop_g49_1.service..*(..))")
    public void allServiceMethods() {
        // Метод без тела для обозначения Pointcut
    }

    // Логирование перед вызовом метода
    @Before("allServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Method {} of class {} called with arguments: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    // Логирование после успешного выполнения метода
    @AfterReturning(pointcut = "allServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method {} of class {} successfully returned with result: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                result);
    }

    // Логирование, если метод выбросил исключение
    @AfterThrowing(pointcut = "allServiceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        logger.error("Method {} of class {} threw an exception: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName(),
                exception.getMessage());
    }

    // Логирование после завершения выполнения метода (всегда, независимо от результата)
    @After("allServiceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Method {} of class {} finished execution.",
                joinPoint.getSignature().getName(),
                joinPoint.getTarget().getClass().getSimpleName());
    }


}
