/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ClusterQuoteCacheStoreAdaptorクラス(DOTClusterQuoteCacheStoreAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/07 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx.rmi;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtier.jmxrmi.JmxRmiClientException;


/**
 * クラスタ構成されたルールエンジンの他ノードから時価情報を取得するアダプタクラス。
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTClusterQuoteCacheStoreAdaptor
{
    
    /**
     * クラスタ構成されたルールエンジンの他ノードから時価情報を取得する。
     * 
     * @param l_from 更新時間（FROM）
     * @param l_to 更新時間（TO）
     * @return <code>DOTQuoteData</code>のリスト
     * @throws JmxRmiClientException JMX-RMI接続時に発生した例外
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to) throws JmxRmiClientException;

}
