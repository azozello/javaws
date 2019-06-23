package com.cassoviacode.javaws;

import com.cassoviacode.javaws.converter.MyCustomMessageConverter;
import com.cassoviacode.javaws.payload.PayloadList;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JavaWsApplication.class)
public abstract class BaseTestCase {
    @LocalServerPort
    private int port;

    private SockJsClient sockJsClient;
    private MyCustomMessageConverter converter;

    private WebSocketStompClient stompClient;

    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

    protected TestSessionHandler sessionHandler;
    protected StompSession stompSession;

    @Before
    public void setup() throws Exception {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        this.sockJsClient = new SockJsClient(transports);

        this.stompClient = new WebSocketStompClient(sockJsClient);
        this.converter = new MyCustomMessageConverter();
        this.stompClient.setMessageConverter(converter);

        sessionHandler = new TestSessionHandler();
        stompSession = this.stompClient
                .connect("ws://localhost:{port}/ws", this.headers, sessionHandler, this.port)
                .get(5, TimeUnit.MINUTES);

        initDatabase();
    }

    abstract void initDatabase();

    protected <T> List<T> getPayloadRecords(String topic, Class<T> payloadType, String mapping, Object message) throws InterruptedException, ExecutionException, TimeoutException {

        TestSessionHandler<PayloadList<T>> testSessionHandler = new TestSessionHandler<>(PayloadList.class);
        this.converter.setPayloadType(payloadType);
        stompSession.subscribe(topic, testSessionHandler);
        stompSession.send(mapping, message);

        return testSessionHandler.getCompletableFuture().get(15, TimeUnit.MINUTES).getRecords();
    }
}
