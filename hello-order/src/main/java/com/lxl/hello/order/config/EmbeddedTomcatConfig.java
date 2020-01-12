/**
 * 
 */
package com.lxl.hello.order.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component
public class EmbeddedTomcatConfig implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>, Ordered {

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		TomcatServletWebServerFactory tomcatServletWebServerFactory = (TomcatServletWebServerFactory) factory;
		tomcatServletWebServerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {
				Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
				protocol.setMaxConnections(200);
				protocol.setMaxThreads(200);
				protocol.setSelectorTimeout(3000);
				protocol.setSessionTimeout(3000);
				protocol.setConnectionTimeout(3000);
				protocol.setAcceptCount(100);
//				protocol.setMinSpareThreads(minSpareThreads);
			}
		});
	}

	@Override
	public int getOrder() {
		return 0;
	}

}
