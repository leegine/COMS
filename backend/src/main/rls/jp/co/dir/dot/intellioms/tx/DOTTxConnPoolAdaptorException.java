/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : コネクションプールアダプターException(DOTTxConnPoolAdaptorException.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/24 齋藤　栄三(FLJ) 新規作成
*/
package jp.co.dir.dot.intellioms.tx;

/**
 * コネクションプールアダプターException
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public class DOTTxConnPoolAdaptorException extends Exception
{

    /**
     * 
     */
    public DOTTxConnPoolAdaptorException()
    {
        super();
    }

    /**
     * @param message
     */
    public DOTTxConnPoolAdaptorException(String message)
    {
        super(message);
    }

    /**
     * @param cause
     */
    public DOTTxConnPoolAdaptorException(Throwable cause)
    {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public DOTTxConnPoolAdaptorException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
