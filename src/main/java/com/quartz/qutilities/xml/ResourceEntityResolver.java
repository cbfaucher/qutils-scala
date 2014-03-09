package com.quartz.qutilities.xml;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * A {@link EntityResolver} that tries to resolve the DTD or XSD, in a specified
 * resoruce directory (defaults is "/DTDs").
 * <p>
 * It only looks at the system-id.
 *
 * @see MappedEntityResolver
 *
 * @author fauchc02
 * @since 2005-03-01 09:09:49
 */
public class ResourceEntityResolver implements EntityResolver
{
    /**
     * Default supported extensions, which are ".dtd" and ".xsd".
     */
    public static final String[] DEFAULT_SUPPORTED_EXTENSIONS = {
        ".xsd",
        ".dtd"
    };

    /**
     * Default directory <b>in CLASSPATH</b>: "/DTDs".
     */
    public static final String DEFAULT_RESOURCE_DIRECTORY = "/DTDs";

    final private Set supportedExtensions = new HashSet();
    final private String directory;

    /**
     * Constructor with default values
     *
     * @see #DEFAULT_SUPPORTED_EXTENSIONS
     * @see #DEFAULT_RESOURCE_DIRECTORY
     */
    public ResourceEntityResolver()
    {
        this(DEFAULT_SUPPORTED_EXTENSIONS, DEFAULT_RESOURCE_DIRECTORY);
    }

    public ResourceEntityResolver(String pDirectory)
    {
        this(DEFAULT_SUPPORTED_EXTENSIONS, pDirectory);
    }

    /**
     * Constructor
     *
     * @param pSupportedExtensions Supported extensions (".abc" format), null for default
     * @param pResourceDirectory Resource Directory to look at in the CLASSPATH, null for default.
     *
     * @see #DEFAULT_SUPPORTED_EXTENSIONS
     * @see #DEFAULT_RESOURCE_DIRECTORY
     */
    public ResourceEntityResolver(String [] pSupportedExtensions, String pResourceDirectory)
    {
        final String[] extensions = (pSupportedExtensions != null ? pSupportedExtensions : DEFAULT_SUPPORTED_EXTENSIONS);
        for (int i = 0; i < extensions.length; i++)
        {
            supportedExtensions.add(extensions[i]);
        }

        this.directory = pResourceDirectory;
    }


    /**
     * This implementation looks into the "/DTDs" resource directory (in classpath) if there
     * is a DTD with the wanted name.
     *
     * @return Input source to the DTD (if found), false otherwise.
     */
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException
    {
        if (systemId == null) return null;

        final String extension = getExtension(systemId);

        if ( !supportedExtensions.contains(extension)) return null;

        //  get the DTD name.
        final int lastSlashIndex = systemId.lastIndexOf('/');
        final String entityName = (lastSlashIndex >= 0 ? systemId.substring(lastSlashIndex + 1) : systemId);

        //  check in resource directoryif the dtd is there.
        final String location = buildLocation(entityName);
        final InputStream is = this.getClass().getResourceAsStream(location);
        if (is == null) return null;    //  not found

        return new InputSource(is);
    }

    private String buildLocation(String pEntityName)
    {
        final StringBuffer buffer = new StringBuffer(this.directory);

        if ( !this.directory.endsWith("/")) buffer.append("/");

        buffer.append(pEntityName);

        return buffer.toString();
    }

    private String getExtension(String pSystemId)
    {
        final int lastDotIndex = pSystemId.lastIndexOf('.');
        if (lastDotIndex == -1) return null;

        return pSystemId.substring(lastDotIndex);
    }
}
