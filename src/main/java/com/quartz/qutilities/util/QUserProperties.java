/*
 * Copyright (c) 2005 9095-2458 Quebec Inc. All Rights Reserved.
 *
 * Althought this code is consider of good quality and has been tested, it is
 * provided to you WITHOUT guaranty of any kind.
 */
package com.quartz.qutilities.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * A 'user properties' support class, where "Preferences" are stored to a file in user's home directory.  The filename
 * is driven from the {@link QUserProperties}'s {@link #name}.
 *
 * @author Christian
 * @since Quartz...
 */
public class QUserProperties extends AbstractProperties implements IProperties
{
    ///////////////////////////////////////
    ////    STATIC ATTRIBUTES

    static final private boolean DEBUG_ON = false;
    static final private com.quartz.qutilities.debug.Debug   DEBUG = (DEBUG_ON ? new com.quartz.qutilities.debug.Debug() : null);


    ///////////////////////////////////////
    ////    STATIC METHODS

    ///////////////////////////////////////
    ////    INSTANCE ATTRIBUTES                  

    final private String    name;
    final private boolean   loadAndSaveAutomatically;
    final private File      userPropertiesLocation;

    private QProperties properties = null;

    ///////////////////////////////////////
    ////    CONSTRUCTORS

    /**
     * Constructor
     *
     * @param pName Unique name for this set of user properties, e.g. the applicatin's unique name
     * @param pLoadAndSaveAutomatically True if user properties should be loaded and saved automicatlly.
     * @throws QPropertiesException If {@link #loadAndSaveAutomatically} is true and there is a failure loading the user properties (other than file not found).
     */
    public QUserProperties(String pName, boolean pLoadAndSaveAutomatically) throws QPropertiesException
    {
        name = pName;
        loadAndSaveAutomatically = pLoadAndSaveAutomatically;
        userPropertiesLocation = new File(System.getProperty("user.home"), name + ".properties");

        if (this.loadAndSaveAutomatically) load();
    }

    ///////////////////////////////////////
    ////    INSTANCE METHODS

    /**
     * Loads the user properties
     * <p>
     * You dont need to call this directly if {@link #loadAndSaveAutomatically} is true.
     *
     * @throws QPropertiesException If an error occurs (other than file not found).
     */
    public void load() throws QPropertiesException
    {


        //  check if it exists
        if (this.userPropertiesLocation.exists() == false)
        {
            //  create empty only
            if (DEBUG_ON) DEBUG.debug("No UserProperties found from: " + this.userPropertiesLocation.getAbsolutePath());
            this.properties = new QProperties();
            return;
        }

        if (DEBUG_ON) DEBUG.debug("Loading User Properties from: " + this.userPropertiesLocation.getAbsolutePath());

        this.properties = QProperties.load(this.userPropertiesLocation);
    }

    /**
     * Sves the user properties.
     * <p>
     * You dont need to call this method (although it doesnt hurt if you do), if {@link #loadAndSaveAutomatically) is true.
     * @throws QPropertiesException If saving fails.
     */
    public void save() throws QPropertiesException
    {
        if (this.properties == null)
        {
            this.properties = new QProperties();
        }

        if (DEBUG_ON) DEBUG.debug("Saving User Properties to: " + this.userPropertiesLocation.getAbsolutePath());

        FileOutputStream out = null;

        try
        {
            out = new FileOutputStream(this.userPropertiesLocation);
            this.properties.store(out, "");
        }
        catch (IOException e)
        {
            throw new QPropertiesException("Could not save UserProperties to " + this.userPropertiesLocation.getAbsolutePath(), e);
        }
        finally
        {
            try
            {
                if (out != null) out.close();
            }
            catch (IOException e)
            {
                //  ignored
            }
        }
    }

    /**
     * Sets the user propertiy
     * @param pName Name for property.  If null, does nothing
     * @param pValue Value for the property.  If null, changed to "".
     */
    public Object setProperty(String pName, String pValue)
    {
        if (pName == null) return null;

        final Object old = getProperty(pName, null);

        final String theValue = (pValue != null ? pValue : "");

        if (DEBUG_ON) DEBUG.debug("Setting " + pName + " --> '" + theValue + "'.");

        if (properties == null) properties = new QProperties();

        this.properties.put(pName, theValue);

        if (this.loadAndSaveAutomatically) save();

        return old;
    }

    /**
     * Gets the user property.  If not found, return the default value provided.
     *
     * @param pName Name for uiser property.  If null, returns null
     * @param pDefaultValue Default value if user property is not found
     * @return Value for user property, default value if value is not found, null if name is null.
     */
    public String getProperty(String pName, String pDefaultValue)
    {
        if (pName == null) return null;
        return this.properties.getProperty(pName, pDefaultValue);
    }

    public void removeProperty(String pUserPropName)
    {
        this.properties.remove(pUserPropName);
    }

}
