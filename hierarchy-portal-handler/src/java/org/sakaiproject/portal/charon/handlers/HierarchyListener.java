package org.sakaiproject.portal.charon.handlers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.sakaiproject.portal.api.PortalHandler;
import org.sakaiproject.portal.api.PortalService;
import org.sakaiproject.site.api.SiteService;

public class HierarchyListener implements ServletContextListener {

	private PortalHandler hierarchyHandler;
	private PortalHandler magicHandler;
	private PortalHandler hierarchyToolHandler;
	
	public void contextDestroyed(ServletContextEvent arg0) {
		PortalService ps = (PortalService) org.sakaiproject.portal.api.cover.PortalService.getInstance();
		ps.removeHandler("charon", hierarchyHandler.getUrlFragment());
		ps.removeHandler("charon", magicHandler.getUrlFragment());
		ps.removeHandler("charon", hierarchyToolHandler.getUrlFragment());
	}

	public void contextInitialized(ServletContextEvent event) {
		PortalService ps = (PortalService) org.sakaiproject.portal.api.cover.PortalService.getInstance();
		SiteService siteService = (SiteService) org.sakaiproject.site.cover.SiteService.getInstance();
		hierarchyHandler = new HierarchyHandler(siteService);
		magicHandler = new MagicHandler();
		hierarchyToolHandler = new HierarchyToolHandler();
		ps.addHandler("charon", hierarchyHandler);
		ps.addHandler("charon", magicHandler);
		ps.addHandler("charon", hierarchyToolHandler);
	}

}