/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3QuoteCacheManagerMBeanクラス(WEB3QuoteCacheManagerMBean.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/27 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.jmx;

import java.sql.Timestamp;
import java.util.List;


/**
 * 時価情報管理テーブルをJMXで管理するためのMBeanインタフェース
 *
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public interface DOTQuoteCacheStoreManagerMBean
{
    
    /**
     * 時価情報管理テーブルが保持する時価情報を取得する。
     * 
     * @param l_from 更新時間（FROM）
     * @param l_to 更新時間（TO）
     * @return <code>WEB3Quotes</code>のリスト
     */
    public List getQuoteData(Timestamp l_from, Timestamp l_to);
    
    /**
     * 時価情報を取得する。
     * 
     * @param l_strFrom 更新時間（FROM）【フォーマット：yyyy/MM/dd HH:mm:ss】
     * @param l_strTo 更新時間（TO）【フォーマット：yyyy/MM/dd HH:mm:ss】
     * @return <code>DOTQuoteData</code>のリスト
     */
    public List getQuoteData(String l_strFrom, String l_strTo);

}
