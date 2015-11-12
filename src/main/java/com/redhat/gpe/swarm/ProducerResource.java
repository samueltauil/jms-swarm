package com.redhat.gpe.swarm;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by samueltauil on 11/11/15.
 */
@ApplicationScoped
@Path("/send")
public class ProducerResource {
    public static final String MY_TOPIC = "/jms/topic/my-topic";

    @Inject
    private JMSContext context;

    @Resource(lookup = MY_TOPIC)
    private Topic topic;

    @GET
    @Produces("text/plain")
    public String get() {
        context.createProducer().send(topic, "Hi from GPE!");
        return "Sent!";
    }

}
