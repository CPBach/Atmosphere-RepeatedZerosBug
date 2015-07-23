package atmospherePotentialBug.repeatingZeros;

import javax.servlet.ServletRegistration;

import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<DropwizardConfiguration> {
	private static final Logger log = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		String builtInArgs[] = { "server", "mainConfig.yml" };
		new App().run(builtInArgs);
	}

	@Override
	public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
		log.info("Loading AssetsBundle.");
		/* This is needed to have access to static files, like .html, .css, .js */
		bootstrap.addBundle(new AssetsBundle("/content", "/",
				"websocket_client.html", "static"));
		log.info("Done loading AssetsBundle.");
	}

	@Override
	public void run(DropwizardConfiguration configuration,
			Environment environment) throws Exception {

		/* Add support for websockets with the Atmosphere framework */
		AtmosphereServlet servlet = new AtmosphereServlet();
		servlet.framework().addInitParameter(
				"com.sun.jersey.config.property.packages",
				"atmospherePotentialBug.repeatingZeros");
		servlet.framework().addInitParameter(
				ApplicationConfig.WEBSOCKET_CONTENT_TYPE, "application/json");
		servlet.framework().addInitParameter(
				ApplicationConfig.WEBSOCKET_SUPPORT, "true");

		ServletRegistration.Dynamic servletHolder = environment.servlets()
				.addServlet("Chat", servlet);
		servletHolder.addMapping("/chat/*");
	}
}
