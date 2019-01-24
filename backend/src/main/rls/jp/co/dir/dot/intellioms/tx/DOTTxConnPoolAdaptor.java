/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コネクションプールアダプター(DOTTxConnPoolAdaptor.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 齋藤　栄三(FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.tx;

import java.sql.Connection;

/**
 * コネクションプールアダプター
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTTxConnPoolAdaptor
{
    /**
     * コネクションプールからコネクションを取得する。
     * 
     */
    public Connection getConnection() throws DOTTxConnPoolAdaptorException;
    
    /**
     * コネクションをコネクションプールに返却する。
     * 
     * @param Connection
     */
    public void release(Connection connection);
}
