package atmospherePotentialBug.repeatingZeros;

import java.io.IOException;

import javax.ws.rs.Path;

import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.config.service.AtmosphereHandlerService;
import org.atmosphere.cpr.AtmosphereResponse;
import org.atmosphere.handler.OnMessage;
import org.atmosphere.interceptor.AtmosphereResourceLifecycleInterceptor;
import org.atmosphere.interceptor.BroadcastOnPostAtmosphereInterceptor;
import org.atmosphere.interceptor.HeartbeatInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Atmosphere Handler.
 * 
 * @author Stefan babel
 *
 */
@Path("/")
@AtmosphereHandlerService(path = "/chat", interceptors = {
		AtmosphereResourceLifecycleInterceptor.class,
		BroadcastOnPostAtmosphereInterceptor.class,
		TrackMessageSizeInterceptor.class, HeartbeatInterceptor.class })
public class ChatService extends OnMessage<String> {
	private final static Logger log = LoggerFactory
			.getLogger(ChatService.class);

	/**
	 * Gets called when a message was sent by client via atmosphere js.
	 */
	@Override
	public void onMessage(AtmosphereResponse response, String message)
			throws IOException {
		log.info("Received a message: {}", message);
		/* Write back message to client. */
		response.write("Hey client, did you send this message?: " + message);
	}
}