package org.sakaiproject.hierarchy.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.sakaiproject.hierarchy.api.HierarchyService;
import org.sakaiproject.hierarchy.api.HierarchyServiceException;
import org.sakaiproject.hierarchy.api.model.Hierarchy;
import org.sakaiproject.hierarchy.api.model.HierarchyProperty;

public class PortalHierarchyServiceTest
{
	private static final Log log = LogFactory
			.getLog(PortalHierarchyServiceTest.class);

	private HierarchyService hierarchyService = null;

	public void init()
	{
	}

	private void printList(String indent, Iterator i)
	{
		while (i.hasNext())
		{
			Object o = i.next();
			if (o instanceof Hierarchy)
			{
				print(indent, (Hierarchy) o);
			}
			else if (o instanceof HierarchyProperty)
			{
				print(indent, (HierarchyProperty) o);
			}
			else
			{
				log.info(indent + "Unrecognised Node :" + o);
			}
		}

	}

	private void print(String indent, HierarchyProperty property)
	{
		log.debug(indent + "Property " + property.getName() + "("
				+ property.getVersion() + "):" + property.getPropvalue());

	}

	private void print(String indent, Hierarchy hierarchy)
	{
		log.debug("Node " + hierarchy.getPath() + "(" + hierarchy.getVersion()
				+ ")");
		printList("      ", hierarchy.getProperties().values().iterator());
		printList("", hierarchy.getChildren().values().iterator());
	}

	private void checkNodes(Hierarchy h) throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			String testPath = h.getPath() + "/" + i;
			Hierarchy child1 = h.getChild(testPath);
			assertNotNull("Missing node path  " + testPath, child1);
			assertEquals("Path name is not correct ", testPath, child1
					.getPath());
			HierarchyProperty hp = child1.getProperty("propertyA" + i);
			assertNotNull("No property " + testPath + "/propertyA" + i
					+ " node found ", hp);
			assertEquals("Property value of " + testPath + "/propertyA" + i
					+ " is ", "propertyvalueA" + i, hp.getPropvalue());
			hp = child1.getProperty("propertyB" + i);
			assertNotNull("No property " + testPath + "/propertyB" + i
					+ " node found ", hp);
			assertEquals("Property value of " + testPath + "/propertyB" + i
					+ " is ", "propertyvalueB" + i, hp.getPropvalue());

		}
	}

	private void assertEquals(String message, String expected, String actual)
			throws Exception
	{
		if (!expected.equals(actual))
		{
			throw new Exception(message + ":" + expected + "!=" + actual);
		}

	}

	private void assertNotNull(String message, Object o) throws Exception
	{
		if (o == null)
		{
			throw new Exception(message + ": is null ");
		}
	}

	private void addNodes(Hierarchy h, int depth)
			throws HierarchyServiceException
	{
		if (depth == 0) return;
		log.debug("Adding nodes to " + h.getPath() + " at depth " + depth);
		for (int i = 0; i < 5; i++)
		{
			Hierarchy child1 = hierarchyService.newHierarchy(h.getPath() + "/"
					+ i);
			h.addTochildren(child1);

			HierarchyProperty hp = hierarchyService.newHierachyProperty();
			hp.setName("propertyA" + i);
			hp.setPropvalue("propertyvalueA" + i);
			child1.addToproperties(hp);

			hp = hierarchyService.newHierachyProperty();
			hp.setName("propertyB" + i);
			hp.setPropvalue("propertyvalueB" + i);
			child1.addToproperties(hp);
			addNodes(child1, depth - 1);
		}
	}

	public HierarchyService getHierarchyService()
	{
		return hierarchyService;
	}

	public void setHierarchyService(HierarchyService hierarchyService)
	{
		this.hierarchyService = hierarchyService;
	}

}
