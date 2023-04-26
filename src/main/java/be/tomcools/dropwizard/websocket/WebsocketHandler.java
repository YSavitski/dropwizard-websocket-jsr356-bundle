package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.WebsocketContainer;
import be.tomcools.dropwizard.websocket.handling.WebsocketContainerInitializer;
import be.tomcools.dropwizard.websocket.registration.EndpointRegistration;
import io.dropwizard.core.setup.Environment;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

import javax.servlet.ServletContext;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerEndpointConfig;

public class WebsocketHandler implements WebSocketServerContainerInitializer.Configurator {
    private final EndpointRegistration endpointRegistration;
    private final WebsocketConfiguration configuration;
    private final Environment environment;
    private final WebsocketContainerInitializer containerInitializer;

    public WebsocketHandler(WebsocketConfiguration configuration, Environment environment) {
        this(configuration, environment, new EndpointRegistration(), new WebsocketContainerInitializer());
    }

    public WebsocketHandler(WebsocketConfiguration configuration, Environment environment, EndpointRegistration endpointRegistration, WebsocketContainerInitializer containerInitializer) {
        this.configuration = configuration;
        this.environment = environment;
        this.endpointRegistration = endpointRegistration;
        this.containerInitializer = containerInitializer;
    }

    public void addEndpoint(Class<?> endpointClass) {
        this.endpointRegistration.add(endpointClass);
    }

    public void addEndpoint(ServerEndpointConfig serverEndpointConfig) {
        this.endpointRegistration.add(serverEndpointConfig);
    }

    public void initialize() {
        containerInitializer.initialize(environment.getApplicationContext(), this);
    }

    @Override
    public void accept(ServletContext servletContext, ServerContainer serverContainer) throws DeploymentException {
        WebsocketContainer container = new WebsocketContainer(configuration, serverContainer);
        container.registerEndpoints(endpointRegistration.getRegisteredEndpoints());
    }
}
