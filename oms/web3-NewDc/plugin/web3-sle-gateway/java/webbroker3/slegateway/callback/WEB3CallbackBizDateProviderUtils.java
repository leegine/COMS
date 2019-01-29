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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CallbackBizDateProviderUtils(WEBCallbackBizDateProviderUtils.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/12/07 �� �j (FLJ) �V�K�쐬
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
	
	/**�V���O���g���C���X�^���X*/
	private static WEB3CallbackBizDateProviderUtils m_bizDateProvider = null;

    /**
     * �R���X�g���N�^
     * @@param WEB3CallbackSystemPreferencesAdaptorUtils�C���X�^���X
     */
    private WEB3CallbackBizDateProviderUtils(WEB3CallbackSystemPreferencesAdaptorUtils l_systempreferences)
    {
        m_properties = l_systempreferences;
        start();
    }
    /**
     * �V���O���g���C���X�^���X���擾
     * @@param l_systempreferences WEB3CallbackSystemPreferencesAdaptorUtils�C���X�^���X
     * @@return �V���O���g���C���X�^���X��Ԃ�
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
     * Parse����bizDate���擾
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
     * Shift�^�C�����l������CurrentTimeMillis��Ԃ�
     */
    public long getCurrentTimeMillis()
    {
        return System.currentTimeMillis() + getTimeShift();
    }

    /**
     * Shift�^�C����Ԃ�
     * m_isShift = true�̏ꍇ�F'shift.system.timestamp.millisecs'�ɐݒ肵���l��Ԃ�
     * m_isShift = false�̏ꍇ: 0L�Ԃ�
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
     * bizDate���擾
     */
    public String getBizDate()
    {
        return m_bizDate;
    }
    
	/**
	 * isShift�t���O���Z�b�g
	 */
	public void setIsShift(boolean l_isShift)
	{
		m_isShift = l_isShift;
	}
	
    
	/**
	 * isShift�t���O���擾
	 */
	public boolean getIsShift()
	{
		return m_isShift;
	}
	
    /**
     * toString()���\�b�h
     */
    public String toString()
    {
        return "WEB3 biz date provider [current-date=" + getCurrentDate() + ", time-shift=" + getTimeShift() + "]";
    }

    /**
     * �C���X�^���X�����̃����o�[����������
     */
    public void start()
    {
        String l_strIsShift = m_properties.getProperty(SHIFT_SYSTEM_TIMESTAMP, "false");

        m_isShift = "true".equals(l_strIsShift);

        m_bizDate = m_properties.getProperty(SYSTEM_BIZDATE);
    }

    /**
     * �C���X�^���X�I������(������)
     */
    public void stop()
    {

    }
    
	/**
	 * �A�_�v�^���擾
	 */
	public WEB3CallbackSystemPreferencesAdaptorUtils getAdaptor()
	{
		return m_properties;
	}	
}
@
