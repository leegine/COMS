/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteFeederAdaptorManagerMBeanクラス(DOTQuoteFeederAdaptorManagerMBean.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/09 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx;



/**
 * 時価アダプタをJMXで管理するためのMBeanインターフェース
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteFeederAdaptorManagerMBean
{
    
    public void connect();
    
    public void disconnect();
    
    public int getStatus();

}
