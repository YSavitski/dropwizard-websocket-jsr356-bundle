package be.tomcools.dropwizard.websocket.handling;

import io.dropwizard.jetty.MutableServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class WebsocketContainerInitializer {
    public void initialize(MutableServletContextHandler contextHandler,
                           WebSocketServerContainerInitializer.Configurator configurator) {
        try {
            WebSocketServerContainerInitializer.configure(contextHandler, configurator);
        } catch (Exception e) {
            throw new IllegalStateException("Could not initialize context handler to enable Websockets", e);
        }
    }
}
