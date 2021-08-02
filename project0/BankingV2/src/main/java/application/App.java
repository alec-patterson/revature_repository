package application;

import org.apache.logging.log4j.*;


/*
 * Main driver for the project
 * User will start here and start from ApplicationHandler wil be called to start the User Interface
 */
public class App {
	private static final Logger logger = LogManager.getLogger(App.class.getName());
	private static ApplicationHandler app;
	
	public static void main(String[] args) {
		app = new ApplicationHandler();
		logger.info("Starting application....");
		app.start();
		logger.info("Exiting application");
	}
}
