head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.59.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3CallbackBizDateProviderUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CallbackBizDateProviderUtils(WEBCallbackBizDateProviderUtils.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/12/07 李 瀚 (FLJ) 新規作成
*/
package webbroker3.slegateway.callback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import webbroker3.slegateway.callback.WEB3CallbackSystemPreferencesAdaptorUtils;

/**
 *  WEB3CallbackBizDateProviderUtils 
 *
 * @@author Han.Li (FLJ)
 * @@version 1.0
 */
public class WEB3CallbackBizDateProviderUtils
{	
    private static final String YYYYMMDD_FORMAT = "yyyyMMdd";

    private boolean m_isShift;

    private String m_bizDate;

    private final WEB3CallbackSystemPreferencesAdaptorUtils m_properties;
	
	private static final String SHIFT_SYSTEM_TIMESTAMP = "shift.system.timestamp";
	
	private static final String SHIFT_SYSTEM_TIMESTAMP_MILLISECS = "shift.system.timestamp.millisecs";
	
	private static final String SYSTEM_BIZDATE = "system.bizdate";
	
	/**シングルトンインスタンス*/
	private static WEB3CallbackBizDateProviderUtils m_bizDateProvider = null;

    /**
     * コンストラクタ
     * @@param WEB3CallbackSystemPreferencesAdaptorUtilsインスタンス
     */
    private WEB3CallbackBizDateProviderUtils(WEB3CallbackSystemPreferencesAdaptorUtils l_systempreferences)
    {
        m_properties = l_systempreferences;
        start();
    }
    /**
     * シングルトンインスタンスを取得
     * @@param l_systempreferences WEB3CallbackSystemPreferencesAdaptorUtilsインスタンス
     * @@return シングルトンインスタンスを返す
     */
    public static WEB3CallbackBizDateProviderUtils getInstance(WEB3CallbackSystemPreferencesAdaptorUtils l_systempreferences)
    {
    	if (m_bizDateProvider == null)
		{ 
    		m_bizDateProvider = new WEB3CallbackBizDateProviderUtils(l_systempreferences);
		}
		return m_bizDateProvider;
    }

    /**
     * ParseしたbizDateを取得
     */
    public Date getCurrentDate()
    {
        Date l_datBizDate = null;

        try
        {
            l_datBizDate = new SimpleDateFormat(YYYYMMDD_FORMAT).parse(m_bizDate);
        }
        catch (ParseException e)
        {
            l_datBizDate = new Date(getCurrentTimeMillis());
        }

        return l_datBizDate;
    }

    /**
     * Shiftタイムを考慮したCurrentTimeMillisを返す
     */
    public long getCurrentTimeMillis()
    {
        return System.currentTimeMillis() + getTimeShift();
    }

    /**
     * Shiftタイムを返す
     * m_isShift = trueの場合：'shift.system.timestamp.millisecs'に設定した値を返す
     * m_isShift = falseの場合: 0L返す
     */
    public long getTimeShift()
    {
        long l_shift = 0;

        if(m_isShift)
        {
            l_shift = m_properties.getProperty(SHIFT_SYSTEM_TIMESTAMP_MILLISECS, 0L);
        }

        return l_shift;
    }

    /**
     * bizDateを取得
     */
    public String getBizDate()
    {
        return m_bizDate;
    }
    
	/**
	 * isShiftフラグをセット
	 */
	public void setIsShift(boolean l_isShift)
	{
		m_isShift = l_isShift;
	}
	
    
	/**
	 * isShiftフラグを取得
	 */
	public boolean getIsShift()
	{
		return m_isShift;
	}
	
    /**
     * toString()メソッド
     */
    public String toString()
    {
        return "WEB3 biz date provider [current-date=" + getCurrentDate() + ", time-shift=" + getTimeShift() + "]";
    }

    /**
     * インスタンス化時のメンバー初期化処理
     */
    public void start()
    {
        String l_strIsShift = m_properties.getProperty(SHIFT_SYSTEM_TIMESTAMP, "false");

        m_isShift = "true".equals(l_strIsShift);

        m_bizDate = m_properties.getProperty(SYSTEM_BIZDATE);
    }

    /**
     * インスタンス終了処理(未実装)
     */
    public void stop()
    {

    }
    
	/**
	 * アダプタを取得
	 */
	public WEB3CallbackSystemPreferencesAdaptorUtils getAdaptor()
	{
		return m_properties;
	}	
}
@
