package br.com.invillia.gateway.config;

import static org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
@Primary
public class SwaggerConfiguration implements SwaggerResourcesProvider {

    @Value("${swagger.suffix}")
    private String suffix;

    @Autowired
    private ZuulProperties zuulProperties;

    @Override
    public List<SwaggerResource> get() {

        final List<SwaggerResource> resources = new ArrayList<>();
        final Map<String, ZuulRoute> routes = zuulProperties.getRoutes();

        routes.keySet().stream()
            .forEach(key -> resources.add(newSwaggerResource(routes.get(key))));

        return resources;
    }

    private SwaggerResource newSwaggerResource(final ZuulRoute route) {

        final SwaggerResource resource = new SwaggerResource();
        resource.setName(route.getId());
        resource.setLocation(zuulProperties.getPrefix() + "/" + route.getId() + suffix);
        resource.setSwaggerVersion("2.0");
        return resource;
    }
}
