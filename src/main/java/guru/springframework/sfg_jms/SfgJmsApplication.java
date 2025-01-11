package guru.springframework.sfg_jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

	public static void main(String[] args) {
		
		/*
		 * ActiveMQServer server; try { server = ActiveMQServers.newActiveMQServer(new
		 * org.apache.activemq.artemis.core.config.impl.ConfigurationImpl()
		 * .setPersistenceEnabled(false) .setSecurityEnabled(false)
		 * .setJournalDirectory("target/data/journal") .addAcceptorConfiguration("invm",
		 * "vm://0"));
		 * 
		 * server.start(); } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		
        SpringApplication.run(SfgJmsApplication.class, args);
	}

}
