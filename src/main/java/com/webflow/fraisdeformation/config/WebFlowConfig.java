package com.webflow.fraisdeformation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import java.util.Collections;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {
    @Bean
    public FlowDefinitionRegistry flowRegistry() {
        return getFlowDefinitionRegistryBuilder()
                .setBasePath("classpath:/flows/")
                .addFlowLocationPattern("*.xml")
                .build();
    }

    // Configuration du FlowBuilderServices en injectant le MvcViewFactoryCreator basé sur Thymeleaf
    @Bean
    public FlowBuilderServices flowBuilderServices() {
        return getFlowBuilderServicesBuilder()
                .setViewFactoryCreator(mvcViewFactoryCreator(null))
                .build();
    }

    // Définissez le FlowExecutor qui configure en interne le FlowExecutionRepository
    @Bean
    public FlowExecutor flowExecutor() {
        return getFlowExecutorBuilder(flowRegistry()).build();
    }

    // Configuration du MvcViewFactoryCreator en injectant le ThymeleafViewResolver auto-configuré par Spring Boot
    @Bean
    public MvcViewFactoryCreator mvcViewFactoryCreator(ThymeleafViewResolver thymeleafViewResolver) {
        MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
        factoryCreator.setViewResolvers(Collections.singletonList(thymeleafViewResolver));
        factoryCreator.setUseSpringBeanBinding(true);
        return factoryCreator;
    }
}
