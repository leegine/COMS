/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SystemPreferencesAdaptorクラス(DOTSystemPreferencesAdaptor.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/01/27 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import com.fitechlabs.fin.intellioms.persist.PersistException;
import com.fitechlabs.fin.intellioms.persist.db.AbstractDbStore;
import com.fitechlabs.fin.intellioms.tx.TxManager;
import com.fitechlabs.fin.intellioms.tx.TxManagerException;
import com.fitechlabs.fin.intellioms.util.InitializationException;
import com.fitechlabs.fin.intellioms.util.Log;
import com.fitechlabs.fin.intellioms.util.Startable;

/**
 * (SYSTEM_PREFERENCESアダプタ)<BR>
 * <BR>
 * SYSTEM_PREFERENCESテーブルに設定された設定値にアクセスするためのアダプタ。<BR>
 * SYSTEM_PREFERENCESアダプタのインスタンスを生成時するときに、
 * SYSTEM_PREFERENCESテーブルの内容をデータベースからロードする。<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTSystemPreferencesAdaptor extends AbstractDbStore implements
    Startable
{
    
    /** ロガー */
    private final Log log = Log.getLogger(getClass());

    /** Propertiesアダプタ */
    private DOTPropertiesAdaptor properties;

    /**
     * コンストラクタ<BR>
     * <BR>
     * 【シーケンス図】
     * ・(SYSTEM_PREFERENCESアダプタ)初期化処理
     * <BR>
     * @throws PersistException
     */
    public DOTSystemPreferencesAdaptor() throws PersistException
    {
    }

    /**
     * コンストラクタ<BR>
     * <BR>
     * @param l_properties Properties
     */
    public DOTSystemPreferencesAdaptor(Properties l_properties)
    {
        setProperties(l_properties);
    }

    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
     * 指定したキーが存在しない場合は<code>null</code>を返す。
     * <BR>
     * @param l_strKey キー
     * @return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
     */
    public String getProperty(String l_strKey)
    {
        return properties.getProperty(l_strKey);
    }

    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
     * 指定したキーが存在しない場合はデフォルト値を返す。
     * <BR>
     * @param l_strKey キー
     * @param l_intDefaultValue デフォルト値
     * @return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        return properties.getProperty(l_strKey, l_intDefaultValue);
    }

    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
     * 指定したキーが存在しない場合はデフォルト値を返す。
     * <BR>
     * @param l_strKey キー
     * @param l_intDefaultValue デフォルト値
     * @return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
     */
    public long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        return properties.getProperty(l_strKey, l_lngDefaultValue);
    }

    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定したキーでSYSTEM_PREFERENCESテーブルの設定値を検索する。
     * 指定したキーが存在しない場合はデフォルト値を返す。
     * <BR>
     * @param l_strKey キー
     * @param l_intDefaultValue デフォルト値
     * @return 指定したキーでSYSTEM_PREFERENCESテーブルに保存されている設定値
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return properties.getProperty(l_strKey, l_strDefaultValue);
    }

    /**
     * (getプロパティセット)<BR>
     * <BR>
     * 指定した接頭辞で始まるキーで登録されている
     * SYSTEM_PREFERENCESテーブルの設定値を検索する。
     * <BR>
     * @param l_strPrefix キーの接頭辞
     * @return 指定した接頭辞で始まるキーと設定値が設定された<code>Map</code>
     */
    public Map getProperties(String l_strPrefix)
    {
        return properties.getProperties(l_strPrefix);
    }
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#start()
     */
    public void start() throws InitializationException
    {
        try
        {
            setProperties(load());
        } catch (PersistException l_pe)
        {
            throw new InitializationException(l_pe);
        }
    }
    
    /**
     * @see com.fitechlabs.fin.intellioms.util.Startable#stop()
     */
    public void stop()
    {
    }
    
    /**
     * (setプロパティセット)<BR>
     * <BR>
     * 指定したPropertiesのPropertiesアダプタを生成し、設定する。
     * <BR>
     * 【シーケンス図】
     * ・(SYSTEM_PREFERENCESアダプタ)初期化処理
     * <BR>
     * @param l_properties
     */
    private void setProperties(Properties l_properties)
    {
        this.properties = new DOTPropertiesAdaptor(l_properties);
    }

    /**
     * (load)<BR>
     * <BR>
     * SYSTEM_PREFERECESテーブルの内容をデータベースからロードする。
     * <BR>
     * 【シーケンス図】
     * ・(SYSTEM_PREFERENCESアダプタ)初期化処理
     * <BR>
     * @return SYSTEM_PREFERENCESからロードした内容を
     *   設定した<code>Properties</code>
     * @throws PersistException
     */
    private Properties load() throws PersistException
    {

        Properties l_properties = new Properties();

        Connection l_conn = null;
        PreparedStatement l_st = null;
        ResultSet l_rs = null;

        try
        {

            TxManager.begin();

            l_conn = getConnection();
            l_st = l_conn
                .prepareStatement("select name, value from system_preferences");

            for (l_rs = l_st.executeQuery(); l_rs.next();)
            {
                
                String l_strKey = l_rs.getString("name");
                String l_strValue = l_rs.getString("value");
                l_properties.setProperty(l_strKey, l_strValue);
                
            }

            TxManager.commit();

        } catch (SQLException l_sqle)
        {
            log.error("Unexpected exception occured while loading 'SYSTEM_PREFERENCES'.", l_sqle);
            rollback();
        } catch (TxManagerException l_txe)
        {
            log.error("Unexpected exception occured while loading 'SYSTEM_PREFERENCES'.", l_txe);
            rollback();
        } finally
        {
            close(l_rs);
            close(l_st);
        }

        return l_properties;

    }
    
}