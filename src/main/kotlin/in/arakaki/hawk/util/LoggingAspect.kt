package `in`.arakaki.hawk.util

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {

    private val logger = KotlinLogging.logger {}

    @Around("execution(* in.arakaki.hawk.service.HawkServiceImp.*(..))")
    fun test(pjp: ProceedingJoinPoint): Any {
        logger.info("Initing method test tweet API")
        val result =  pjp.proceed()
        logger.info("Ending method test tweet API")
        return result
    }

}
