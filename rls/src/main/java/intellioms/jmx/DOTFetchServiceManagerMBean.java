/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3FetchServiceManagerMBeanクラス(DOTFetchServiceManagerMBean.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 齋藤　栄三(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx;

import com.fitechlabs.fin.intellioms.util.InitializationException;


/**
 * FetchサービスをJMXで管理するためのMBeanインタフェース
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTFetchServiceManagerMBean
{
    
    public void start() throws InitializationException;
    
    public void stop();

    public String getStatus();
}
