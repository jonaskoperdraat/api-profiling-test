package hello;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfiguration {

  @Bean
  public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
    BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
    beanNameAutoProxyCreator.setBeanNames("dataAccessObject", "greetingController", "responseBuilder");
    beanNameAutoProxyCreator.setInterceptorNames("performanceInterceptor");
    return beanNameAutoProxyCreator;
  }



}
