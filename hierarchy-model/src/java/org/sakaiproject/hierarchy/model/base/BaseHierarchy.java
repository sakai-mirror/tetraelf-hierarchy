package org.sakaiproject.hierarchy.model.base;

// BaseValueObjectImports

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.sakaiproject.hierarchy.model.api.Hierarchy;
import org.sakaiproject.hierarchy.model.api.HierarchyProperty;

//BaseValueObjectClassComments

/**
 * This is an object that contains data related to the hierarchy_nodes table. Do
 * not modify this class because it will be overwritten if the configuration
 * file related to this class is modified.
 * 
 * @hibernate.class table="hierarchy_nodes"
 */

// BaseValueObjectClassDefinitions
public abstract class BaseHierarchy implements Serializable, Comparable
{

	// Custom BaseValueObjectStaticProperties

	public static String REF = "Hierarchy";

	public static String PROP_REALM = "realm";

	public static String PROP_NODEID = "nodeid";

	public static String PROP_PARENT = "parent";

	public static String PROP_NAME = "name";

	public static String PROP_ID = "id";

	// BaseValueObjectConstructor

	// constructors
	public BaseHierarchy()
	{
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseHierarchy(String id)
	{
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseHierarchy(String id, String nodeid, String name, String realm)
	{

		this.setId(id);
		this.setNodeid(nodeid);
		this.setName(name);
		this.setRealm(realm);
		initialize();
	}

	protected void initialize()
	{
	}

	// Custom BaseValueObjectVariableDefinitions

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private String id;

	Date version;

	// fields
	private java.lang.String nodeid;

	private java.lang.String name;

	private java.lang.String realm;

	// many to one
	private org.sakaiproject.hierarchy.model.Hierarchy parent;

	// collections
	private java.util.Set children;

	private java.util.Set properties;

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
		this.version = version;
	}

	/**
	 * Return the value associated with the column: nodeid
	 */
	public java.lang.String getNodeid()
	{
		return nodeid;
	}

	/**
	 * Set the value related to the column: nodeid
	 * 
	 * @param nodeid
	 *        the nodeid value
	 */
	public void setNodeid(String nodeid)
	{
		this.nodeid = nodeid;
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
		this.name = name;
	}

	/**
	 * Return the value associated with the column: realm
	 */
	public String getRealm()
	{
		return realm;
	}

	/**
	 * Set the value related to the column: realm
	 * 
	 * @param realm
	 *        the realm value
	 */
	public void setRealm(String realm)
	{
		this.realm = realm;
	}

	/**
	 * Return the value associated with the column: parent_id
	 */
	public Hierarchy getParent()
	{
		return parent;
	}

	/**
	 * Set the value related to the column: parent_id
	 * 
	 * @param parent
	 *        the parent_id value
	 */
	public void setParent(Hierarchy parent)
	{
		this.parent = (org.sakaiproject.hierarchy.model.Hierarchy) parent;
	}

	/**
	 * Return the value associated with the column: children
	 */
	public Set getChildren()
	{
		return children;
	}

	/**
	 * Set the value related to the column: children
	 * 
	 * @param children
	 *        the children value
	 */
	public void setChildren(Set children)
	{
		this.children = children;
	}

	public void addTochildren(Hierarchy hierarchy)
	{
		if (null == getChildren()) setChildren(new TreeSet());
		getChildren().add(hierarchy);
	}

	/**
	 * Return the value associated with the column: properties
	 */
	public Set getProperties()
	{
		return properties;
	}

	/**
	 * Set the value related to the column: properties
	 * 
	 * @param properties
	 *        the properties value
	 */
	public void setProperties(Set properties)
	{
		this.properties = properties;
	}

	public void addToproperties(HierarchyProperty hierarchyProperty)
	{
		if (null == getProperties()) setProperties(new TreeSet());
		getProperties().add(hierarchyProperty);
	}

	// BaseValueObjectEqualityMethods

	/*
	 * public boolean equals (Object obj) { if (null == obj) return false; if
	 * (!(obj instanceof org.sakaiproject.hierarchy.model.Hierarchy)) return
	 * false; else { org.sakaiproject.hierarchy.model.Hierarchy hierarchy =
	 * (org.sakaiproject.hierarchy.model.Hierarchy) obj; if (null ==
	 * this.getId() || null == hierarchy.getId()) return false; else return
	 * (this.getId().equals(hierarchy.getId())); } } public int hashCode () { if
	 * (Integer.MIN_VALUE == this.hashCode) { if (null == this.getId()) return
	 * super.hashCode(); else { String hashStr = this.getClass().getName() + ":" +
	 * this.getId().hashCode(); this.hashCode = hashStr.hashCode(); } } return
	 * this.hashCode; }
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

	// BaseValueObjectCustomContents
}