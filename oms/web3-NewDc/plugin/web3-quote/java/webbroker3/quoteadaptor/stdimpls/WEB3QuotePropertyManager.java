head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuotePropertyManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

/**
 * 時価情報関連のプロパティを管理するためのクラス。
 *
 * @@author Yoshihara Tadafumi
 * @@version 1.0
 */
class WEB3QuotePropertyManager
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QuotePropertyManager.class);

    private static final boolean DBG = log.ison();
    
    /**
     * コンストラクタ。
     *
     */
    protected WEB3QuotePropertyManager()
    {
    }

    /**
     * プロパティを取得する。
     *
     * @@param l_strKey プロパティ・キー。
     * @@param l_strDefaultValue デフォルト値。
     * @@return 指定されたプロパティの値。
     */
    public static String getProperty(String l_strKey, String l_strDefaultValue)
    {
        String l_strReturnValue = l_strDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue  != null)
        {
            l_strReturnValue = l_strValue;
        }
        return l_strReturnValue;
    }

    /**
     * int型のプロパティを取得する。
     *
     * @@param key プロパティ・キー。
     * @@param def デフォルト値。
     * @@return 指定されたプロパティの値。
     */
    public static int getProperty(String l_strKey, int l_intDefaultValue)
    {
        int l_intReturnValue = l_intDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_intReturnValue = Integer.parseInt(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.error("Illegal property name=" + l_strKey +", value=" + l_strValue);
            }
        }
        return l_intReturnValue;
    }

    /**
     * long型のプロパティを取得する。
     *
     * @@param key プロパティ・キー。
     * @@param def デフォルト値。
     * @@return 指定されたプロパティの値。
     */
    public static long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        long l_lngReturnValue = l_lngDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_lngReturnValue = Long.parseLong(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.error("Illegal property name=" + l_strKey +", value=" + l_strValue);
            }
        }
        return l_lngReturnValue;
    }
    
    /**
     * boolean型のプロパティを取得する。
     * <br>
     * true/falseの判定は、Boolean.valueOf(String)の仕様に従う。
     * 
     * @@param l_strKey プロパティ・キー
     * @@param l_blnDefaultValue デフォルト値
     * @@return 指定されたプロパティの値
     */
    public static boolean getProperty(String l_strKey, boolean l_blnDefaultValue)
    {
        boolean l_blnReturnValue = l_blnDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            l_blnReturnValue = Boolean.valueOf(l_strValue).booleanValue();
        }
        return l_blnReturnValue;
    }
    
    protected static String getProperty(String l_strKey)
    {
        return GtlUtils.getTradingSystem().getPreference(l_strKey);
    }
    
    /**
     * (getプロパティセット)<BR>
     * <BR>
     * 指定された接頭辞で始まるキーで登録されているプロパティを検索する。<BR>
     * <BR>
     * @@param l_strPrefix 接頭辞
     * @@return 指定された接頭辞で始まるキーとプロパティが設定されたマップ
     */
    public static Map getProperties(String l_strPrefix)
    {
        Map l_values = new HashMap();

        Map l_tmpProps = GtlUtils.getTradingSystem().getPreferences();

        if(l_tmpProps != null)
        {
            Iterator l_iter = l_tmpProps.keySet().iterator();
            while(l_iter.hasNext())
            {
                String l_strKey = (String)l_iter.next();
                if (l_strKey != null && l_strKey.startsWith(l_strPrefix))
                {
                    Object l_value = l_tmpProps.get(l_strKey);
                    l_values.put(l_strKey, l_value);
                }
            }
        }

        return l_values;
    }

}
@
