package com.jkbff.saca;

import java.net.URI;
import java.net.URISyntaxException;

public class TestApp {
    public static void main(String[] args) {
        try {
            // open websocket
            final URI uri = new URI("");

            ExampleClient c = new ExampleClient(uri); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
            c.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
