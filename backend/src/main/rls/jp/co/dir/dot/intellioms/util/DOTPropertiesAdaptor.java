/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PropertiesManagerクラス(DOTPropertiesAdaptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/27 山田　卓司(FLJ) 新規作成
 */
package jp.co.dir.dot.intellioms.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fitechlabs.fin.intellioms.util.Log;


/**
 * (Propertiesアダプタ)<BR>
 * <BR>
 * <code>java.util.Properties</code>のアダプタクラス。<BR>
 * <BR>
 * @author Takuji Yamada (FLJ)
 * @version 1.0
 */
public class DOTPropertiesAdaptor
{
    
    /** ロガー */
    private final Log log = Log.getLogger(getClass());
    
    /** Properties */
    private final Properties properties;
    
    /**
     * コンストラクタ<BR>
     * <BR>
     * @param l_properties アダプタに設定するプロパティリスト
     */
    public DOTPropertiesAdaptor(Properties l_properties)
    {
        this.properties = l_properties;
    }
    
    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定されたキーを持つプロパティを取得する。<BR>
     * そのプロパティが存在しない場合<code>null</code>を返す。<BR>
     * <BR>
     * @param l_strKey キー
     * @return 指定されたキー値をもつプロパティの値
     */
    public String getProperty(String l_strKey)
    {
        return properties.getProperty(l_strKey);
    }
    
    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定されたキーを持つプロパティを取得する。<BR>
     * そのプロパティが存在しない場合デフォルト値を返す。<BR>
     * <BR>
     * @param l_strKey キー
     * @param l_strDefaultValue デフォルト値
     * @return 指定されたキー値をもつプロパティの値
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return properties.getProperty(l_strKey, l_strDefaultValue);
    }
    
    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定されたキーを持つプロパティを取得する。<BR>
     * そのプロパティが存在しない場合デフォルト値を返す。<BR>
     * <BR>
     * @param l_strKey キー
     * @param l_intDefaultValue デフォルト値
     * @return 指定されたキー値をもつプロパティの値
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        int l_intValue = l_intDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_intValue = Integer.parseInt(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.warn("This property should be number. [key=" + l_strKey + ", value=" + l_strValue + "]");
            }
        }
        return l_intValue;
    }
    
    /**
     * (getプロパティ)<BR>
     * <BR>
     * 指定されたキーを持つプロパティを取得する。<BR>
     * そのプロパティが存在しない場合デフォルト値を返す。<BR>
     * <BR>
     * @param l_strKey キー
     * @param l_lngDefaultValue デフォルト値
     * @return 指定されたキー値をもつプロパティの値
     */
    public long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        long l_lngValue = l_lngDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_lngValue = Long.parseLong(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.warn("This property should be number. [key=" + l_strKey + ", value=" + l_strValue + "]");
            }
        }
       return l_lngValue;
    }
    
    /**
     * (getプロパティセット)<BR>
     * <BR>
     * 指定された接頭辞で始まるキーで登録されているプロパティを検索する。<BR>
     * <BR>
     * @param l_strPrefix 接頭辞
     * @return 指定された接頭辞で始まるキーとプロパティが設定されたマップ
     */
    public Map getProperties(String l_strPrefix)
    {
        Map l_values = null;
        for (Enumeration l_e = properties.keys(); l_e.hasMoreElements();)
        {
            String l_strKey = (String) l_e.nextElement();
            if (l_strKey != null && l_strKey.startsWith(l_strPrefix))
            {
                if (l_values == null)
                {
                    l_values = new HashMap();
                }
                l_values.put(l_strKey, getProperty(l_strKey));
            }
        }
        return l_values;
    }
    
}
