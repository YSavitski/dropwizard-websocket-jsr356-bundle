package be.tomcools.dropwizard.websocket;

import io.dropwizard.core.setup.Environment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class WebsocketHandlerFactoryTest {
    private final Environment environment = mock(Environment.class, RETURNS_DEEP_STUBS);
    private final WebsocketConfiguration configuration = mock(WebsocketConfiguration.class);


    @InjectMocks
    private WebsocketHandlerFactory sut;

    @Test
    public void whenCreatingHandlerForEnvironmentReturnsWebsocketHandler() {
        WebsocketHandler websocketHandler = sut.forEnvironment(configuration, environment);

        assertThat(websocketHandler, notNullValue());
    }
}
