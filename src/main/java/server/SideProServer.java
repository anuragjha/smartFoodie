/**
 * 
 */
package server;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import handlers.IndexServlet;
import handlers.RecipeSearchServlet;
import handlers.RecipesSearchServlet;

/**
 * @author anuragjha
 *
 */
public class SideProServer {

	private Server jettyServer;
	private boolean shouldRun;
	
	private ServletHandler handler;
	
	private final int PORT;

	/**
	 * constructor
	 * @param port
	 */
	public SideProServer(int port) {

		this.PORT = port;
		
	}
	
	
	/**
	 * @return the jettyServer
	 */
	public Server getJettyServer() {
		return jettyServer;
	}
	
	

	public void initialize(int minThreads, int maxThreads, int timeout) {

		this.shouldRun = true;

		QueuedThreadPool threadpool = getThreadpool(minThreads, maxThreads, timeout);

		this.jettyServer = new Server(threadpool);
		
		ServerConnector connector = new ServerConnector(this.jettyServer);
		connector.setPort(this.PORT);
		this.jettyServer.setConnectors(new Connector[] {connector});

		
		this.handler = new ServletHandler();
		this.jettyServer.setHandler(this.handler);

	}
	
	
	private QueuedThreadPool getThreadpool(int minThreads, int maxThreads, int idleTimeout) {
		return new QueuedThreadPool(minThreads, maxThreads, idleTimeout);
	}
	
	public void addMapping(Class clazz, String path) {
		//this.handler.addServletWithMapping(BlockingServlet.class, "/status");
		//((ServletHandler)this.jettyServer.getHandler()).addServletWithMapping(BlockingServlet.class, "/status");
		((ServletHandler)this.jettyServer.getHandler()).addServletWithMapping(clazz, path);
	}

	
	public void start() {
		try {
			this.jettyServer.start();
			this.jettyServer.join();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {

		SideProServer ps = new SideProServer(9090);
		ps.initialize(10,100, 120);
		ps.addMapping(IndexServlet.class, "/");
		ps.addMapping(RecipesSearchServlet.class, "/recipessearch");
		ps.addMapping(RecipeSearchServlet.class, "/recipesearch/*");
		ps.start();
	}

}
