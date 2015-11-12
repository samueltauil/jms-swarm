package com.redhat.gpe.swarm;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.messaging.EnhancedServer;
import org.wildfly.swarm.messaging.EnhancedServerConsumer;
import org.wildfly.swarm.messaging.MessagingFraction;

/**
 * Created by samueltauil on 11/11/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        Container container = new Container();


        EnhancedServerConsumer configurator = new EnhancedServerConsumer() {

            @Override
            public void accept(EnhancedServer enhancedServer) {
                enhancedServer.jmsQueue("my-queue");
                enhancedServer.jmsTopic("my-topic");
            }
        };

        container.fraction(MessagingFraction.createDefaultFraction().defaultServer(configurator));

        container.start();

        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addPackage(Main.class.getPackage());

        container.deploy(deployment);

    }
}
