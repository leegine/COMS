head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3CallbackSystemPreferencesAdaptorUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3CallbackSystemPreferencesAdaptorUtils( WEB3CallbackSystemPreferencesAdaptorUtils.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/12/07 李　@瀚(FLJ) 新規作成
 */
package webbroker3.slegateway.callback;

import java.sql.SQLException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import org.apache.log4j.Category;

/**
 * (SYSTEM_PREFERENCESアダプタ)<BR>
 * <BR>
 * SYSTEM_PREFERENCESテーブルに設定された設定値にアクセスするためのアダプタ。<BR>
 * SYSTEM_PREFERENCESアダプタのインスタンスを生成時するときに、
 * SYSTEM_PREFERENCESテーブルの内容をデータベースからロードする。<BR>
 * <BR>
 * @@author Han.Li (FLJ)
 * @@version 1.0
 */
public class WEB3CallbackSystemPreferencesAdaptorUtils
{

    /** ロガー */
	private static final Category m_log =  Category.getInstance(  WEB3CallbackSystemPreferencesAdaptorUtils.class);

    /** Propertiesアダプタ */
    private Properties m_properties = new Properties();

	/**シングルトンインスタンス */
	private static WEB3CallbackSystemPreferencesAdaptorUtils m_adaptor = null;

    /**
     * コンストラクタ<BR>
     * <BR>
     * ・(SYSTEM_PREFERENCESアダプタ)初期化処理
     * <BR>
     * @@param l_web3CallBackAccessUtil DBアクセサインスタンス
     * @@throws PersistException
     */
    private WEB3CallbackSystemPreferencesAdaptorUtils(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil)
    {
    	loadProperties(l_web3CallBackDataAccessUtil);
    }
    
    /**
     * コンストラクタ
     * ↑UTでダミーインスタンス生成時使用される
     *
     */
    public WEB3CallbackSystemPreferencesAdaptorUtils()
    {
    }
    
    /**
     * シングルトンインスタンスを返す
     * @@param l_web3CallBackDataAccessUtil DBアクセサインスタンス
     * @@return シングルトンインスタンス
     */
	public static  WEB3CallbackSystemPreferencesAdaptorUtils getInstance(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil)
	{
		if (m_adaptor == null)
		{
			m_adaptor = new WEB3CallbackSystemPreferencesAdaptorUtils(l_web3CallBackDataAccessUtil);
		}
		return m_adaptor;
	}


    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
     * 指定したキーが存在しない場合は<code>null</code>を返す。
     * <BR>
     * @@param l_strKey キー
     * @@return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
     */
    public String getProperty(String l_strKey)
    {
        return m_properties.getProperty(l_strKey);
    }

    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
     * 指定したキーが存在しない場合はデフォルト値を返す。
     * <BR>
     * @@param l_strKey キー
     * @@param l_intDefaultValue デフォルト値
     * @@return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        return m_properties.getProperty(l_strKey) == null ? 
        	l_intDefaultValue:
        		Integer.parseInt(m_properties.getProperty(l_strKey));
    }

	/**
	 * (getプロパティ)<BR>
	 * <BR>
	 * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
	 * 指定したキーが存在しない場合はデフォルト値を返す。
	 * <BR>
	 * @@param l_strKey キー
	 * @@param l_intDefaultValue デフォルト値
	 * @@return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
	 */
	public long getProperty(String l_strKey, long l_lngDefaultValue)
	{
		return m_properties.getProperty(l_strKey) == null?
			l_lngDefaultValue:
				Long.parseLong(m_properties.getProperty(l_strKey));
	}

    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
     * 指定したキーが存在しない場合はデフォルト値を返す。
     * <BR>
     * @@param l_strKey キー
     * @@param l_intDefaultValue デフォルト値
     * @@return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return m_properties.getProperty(l_strKey) == null ?
        	l_strDefaultValue:
        		m_properties.getProperty(l_strKey);
    }


    /**
     * (プロパティゲット)<BR>
     * <BR>
     * アダプタに保持したPropertiesを返す。
     * @@return m_properties
     */
    public Properties getProperties()
    {
        return m_properties;
    }

    /**
     * (load)<BR>
     * <BR>
     * SYSTEM_PREFERECESテーブルの内容をデータベースからロードする。
     * @@return SYSTEM_PREFERENCESからロードした内容を
     *   設定した<code>Properties</code>
     * @@throws PersistException
     */
    public void loadProperties(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil)
    {
    	
		final String l_strSql = "select name, value from system_preferences";
		
		//プロパティメンバーを初期化
		if ( m_properties == null)
		{
			m_properties = new Properties();
		}
		
		//プロパティをクリアする
		if ( m_properties.size() > 0)
		{
			m_properties.clear();
		}


		List l_lisQueryResult = new ArrayList(1);
		try
		{
			l_lisQueryResult = l_web3CallBackDataAccessUtil.executeQuery(l_strSql, null);
		}
		catch (SQLException e)
		{
			m_log.debug("SQLException Catched! when excute loadSystemPreferences() :" + e);
			
			final String msg = "SQLException Catched! when excute loadSystemPreferences().";
			m_log.error(msg, e);
			throw new RuntimeException(msg, e);
		}
		finally
		{
			try
			{
				l_web3CallBackDataAccessUtil.releaseResource(false);
			}
			catch(SQLException sqle)
			{
				m_log.error("DB Error while closing ResultSet,Statement or Connection.");
			}
		}		
		

		if ( l_lisQueryResult.size()  == 0){
			m_log.warn("no Config settings at system_preferences table.");
			return;
		}
		
		for ( int i=0; i < l_lisQueryResult.size() ; i ++)
		{	
			final Map map = (HashMap) l_lisQueryResult.get(i);
			final Iterator it = map.entrySet().iterator();
			
			String l_strName = null;
			String l_strValue = null;
			
			while (it.hasNext())
			{

				final Map.Entry e = (Map.Entry) it.next();
				final String key = (String) e.getKey();
            	final String value = (String)e.getValue();
				
				if ( key.toLowerCase().equals("name"))
				{
					l_strName = value;		
				}
				if ( key.toLowerCase().equals("value"))
				{
					l_strValue = value;
				}
			}
			
			if ( l_strValue == null){//add 2006/10/23
				m_log.warn("no config setting about system_preferences:" + l_strName);
				continue;
			}
			m_properties.setProperty(l_strName,l_strValue);
			
		}

    }

}@
