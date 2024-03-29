package org.sakaiproject.hierarchy.tool.vm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.component.cover.ServerConfigurationService;
import org.sakaiproject.hierarchy.api.PortalHierarchyService;
import org.sakaiproject.hierarchy.api.model.PortalNode;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class DeleteSiteController extends SimpleFormController {

	public DeleteSiteController() {
		setCommandClass(Object.class);
	}

	public void init() {
		
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		PortalHierarchyService phs = org.sakaiproject.hierarchy.cover.PortalHierarchyService.getInstance();
		PortalNode node = phs.getCurrentPortalNode();
		List<PortalNode> nodes = phs.getNodesFromRoot(node.getId());
		String parentPath = nodes.get(nodes.size()-1).getPath();
		phs.deleteNode(node.getId());
				
		Map model = referenceData(request, command, errors);
		
		model.put("siteUrl", ServerConfigurationService.getPortalUrl()+"/hierarchy"+ parentPath);
		
		return new ModelAndView(getSuccessView(), model);
	}
	
	@Override
	protected Map referenceData(HttpServletRequest request, Object command,
			Errors errors) throws Exception {
		return VelocityControllerUtils.referenceData(request, command, errors);
	}
	
}
