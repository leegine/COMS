/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コネクションプールアダプター実装(DOTTxConnPoolAdaptorImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 齋藤　栄三(FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.tx;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.xtier.kernel.XtierKernel;

/**
 * コネクションプールアダプター実装
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTTxConnPoolAdaptorImpl implements DOTTxConnPoolAdaptor
{
    /**
     * ロガー
     */
    private static final Log log = Log.getLogger(DOTTxConnPoolAdaptorImpl.class);
    
    /**
     * プール
     */
    private final DataSource poolDs;

    /**
     * 
     */
    public DOTTxConnPoolAdaptorImpl(String poolName) throws Exception
    {
        poolDs = XtierKernel.getInstance().db().getDs(poolName);
    }

    public Connection getConnection() throws DOTTxConnPoolAdaptorException
    {
        try
        {
            Connection connection = poolDs.getConnection();
            connection.setAutoCommit(false);
            return connection;
        }
        catch(SQLException e)
        {
            throw new DOTTxConnPoolAdaptorException("Unable to set autocommit mode to false: " + e.getMessage(), e);
        }
    }
    
    public void release(Connection connection)
    {
        if(connection != null)
        {
            try
            {
                connection.close();
            }
            catch(SQLException e)
            {
                log.warn("Unable to release connection back to pool.", e);
            }
        }
    }
}
