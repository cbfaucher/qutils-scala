package com.quartz.qutilities.unittests.sql;

import com.mockobjects.MockObject;
import com.mockobjects.ReturnObjectList;
import com.quartz.qutilities.unittests.QExpectationCounter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * A simple Mock for {@link DataSource}.
 * <p>
 * Note that not all methods are ready for use.
 *
 * @author Christian
 * @since Quartz...
 */
public class MockDataSource extends MockObject implements DataSource
{
	///////////////////////////////////////
	////    STATIC ATTRIBUTES

	///////////////////////////////////////
	////    STATIC METHODS

	///////////////////////////////////////
	////    INSTANCE ATTRIBUTES

	final private ReturnObjectList connectionsToReturn = new ReturnObjectList("connections");

	///////////////////////////////////////
	////    CONSTRUCTORS

	public MockDataSource()
	{
	}

	///////////////////////////////////////
	////    INSTANCE METHODS


    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setupConnectionToReturn(Connection pConnection)
	{
		connectionsToReturn.addObjectToReturn(pConnection);
	}

	/**
	 * Counter for {@link #getConnection()}
	 */
	final public QExpectationCounter getConnection_counter = new QExpectationCounter("getConnection()");

	public Connection getConnection() throws SQLException
	{
		getConnection_counter.inc();
		return (Connection) connectionsToReturn.nextReturnObject();
	}

	public Connection getConnection(String username, String password)
	        throws SQLException
	{
		fail("No call expected (yet)");
		return null;
	}

	public PrintWriter getLogWriter() throws SQLException
	{
		return null;
	}

	public void setLogWriter(PrintWriter out) throws SQLException
	{
		fail("No call expected");
	}

	public void setLoginTimeout(int seconds) throws SQLException
	{
		fail("No call expected");
	}

	public int getLoginTimeout() throws SQLException
	{
		return 0;
	}

	///////////////////////////////////////
	////    INNER CLASSES
}
