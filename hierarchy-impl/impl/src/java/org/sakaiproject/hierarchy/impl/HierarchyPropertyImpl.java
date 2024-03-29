package org.sakaiproject.hierarchy.impl;

// BaseValueObjectImports

import java.io.Serializable;
import java.util.Date;

import org.sakaiproject.hierarchy.api.model.Hierarchy;
import org.sakaiproject.hierarchy.api.model.HierarchyProperty;

//BaseValueObjectClassComments

/**
 * This is an object that contains data related to the hierarchy_property table.
 * Do not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="hierarchy_property"
 */

// BaseValueObjectClassDefinitions
public class HierarchyPropertyImpl implements Serializable, Comparable, HierarchyProperty
{

	// Custom BaseValueObjectStaticProperties

	public static String REF = "HierarchyProperty";

	public static String PROP_NODE = "node";

	public static String PROP_PROPVALUE = "propvalue";

	public static String PROP_NAME = "name";

	public static String PROP_ID = "id";

	// BaseValueObjectConstructor

	// constructors
	public HierarchyPropertyImpl()
	{
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public HierarchyPropertyImpl(java.lang.String id)
	{
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public HierarchyPropertyImpl(java.lang.String id, java.lang.String name,
			java.lang.String propvalue)
	{

		this.setId(id);
		this.setName(name);
		this.setPropvalue(propvalue);
		initialize();
	}

	protected void initialize()
	{
	}

	// Custom BaseValueObjectVariableDefinitions

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private String id;

	private Date version;

	// fields
	private String name;

	private String propvalue;

	// many to one
	private Hierarchy node;
	
	private String nodeId;

	private boolean modified = false;

	// BaseValueObjectGetterIdGetterSetter

	/**
	 * Return the unique identifier of this class
	 * 
	 * @hibernate.id generator-class="uuid.hex" column="id"
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * 
	 * @param id
	 *        the new ID
	 */
	public void setId(String id)
	{
		modified = true;
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	// BaseValueObjectGetterSetter

	/**
	 * Return the value associated with the column: version
	 */
	public Date getVersion()
	{
		return version;
	}

	/**
	 * Set the value related to the column: version
	 * 
	 * @param version
	 *        the version value
	 */
	public void setVersion(Date version)
	{
		modified = true;
		this.version = version;
	}

	/**
	 * Return the value associated with the column: name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Set the value related to the column: name
	 * 
	 * @param name
	 *        the name value
	 */
	public void setName(String name)
	{
		modified = true;
		this.name = name;
	}

	/**
	 * Return the value associated with the column: propvalue
	 */
	public String getPropvalue()
	{
		return propvalue;
	}

	/**
	 * Set the value related to the column: propvalue
	 * 
	 * @param propvalue
	 *        the propvalue value
	 */
	public void setPropvalue(String propvalue)
	{
		modified = true;
		this.propvalue = propvalue;
	}

	/**
	 * Return the value associated with the column: node_id
	 */
	public Hierarchy getNode()
	{
		return node;
	}

	/**
	 * Set the value related to the column: node_id
	 * 
	 * @param node
	 *        the node_id value
	 */
	public void setNode(Hierarchy node)
	{
		modified = true;
		this.node =  node;
	}

	// BaseValueObjectEqualityMethods

	/*
	 * public boolean equals (Object obj) { if (null == obj) return false; if
	 * (!(obj instanceof org.sakaiproject.hierarchy.model.HierarchyProperty))
	 * return false; else { org.sakaiproject.hierarchy.model.HierarchyProperty
	 * hierarchyProperty = (org.sakaiproject.hierarchy.model.HierarchyProperty)
	 * obj; if (null == this.getId() || null == hierarchyProperty.getId())
	 * return false; else return
	 * (this.getId().equals(hierarchyProperty.getId())); } } public int hashCode () {
	 * if (Integer.MIN_VALUE == this.hashCode) { if (null == this.getId())
	 * return super.hashCode(); else { String hashStr =
	 * this.getClass().getName() + ":" + this.getId().hashCode(); this.hashCode =
	 * hashStr.hashCode(); } } return this.hashCode; }
	 */

	public int compareTo(Object obj)
	{
		int hashCmp = hashCode() - obj.hashCode();
		if (hashCmp == 0) return 0;
		if (hashCmp < 0) return -1;
		return 1;
	}

	// CustomBaseValueObjectToString

	public String toString()
	{
		return super.toString();
	}
	public void setModified(boolean modified ) {
		this.modified = modified;
	}
	public boolean isModified() {
		return modified ;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	// BaseValueObjectCustomContents
}