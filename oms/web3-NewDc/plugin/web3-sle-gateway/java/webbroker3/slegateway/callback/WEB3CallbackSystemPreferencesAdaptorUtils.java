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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3CallbackSystemPreferencesAdaptorUtils( WEB3CallbackSystemPreferencesAdaptorUtils.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/12/07 ���@@�j(FLJ) �V�K�쐬
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
 * (SYSTEM_PREFERENCES�A�_�v�^)<BR>
 * <BR>
 * SYSTEM_PREFERENCES�e�[�u���ɐݒ肳�ꂽ�ݒ�l�ɃA�N�Z�X���邽�߂̃A�_�v�^�B<BR>
 * SYSTEM_PREFERENCES�A�_�v�^�̃C���X�^���X�𐶐�������Ƃ��ɁA
 * SYSTEM_PREFERENCES�e�[�u���̓��e���f�[�^�x�[�X���烍�[�h����B<BR>
 * <BR>
 * @@author Han.Li (FLJ)
 * @@version 1.0
 */
public class WEB3CallbackSystemPreferencesAdaptorUtils
{

    /** ���K�[ */
	private static final Category m_log =  Category.getInstance(  WEB3CallbackSystemPreferencesAdaptorUtils.class);

    /** Properties�A�_�v�^ */
    private Properties m_properties = new Properties();

	/**�V���O���g���C���X�^���X */
	private static WEB3CallbackSystemPreferencesAdaptorUtils m_adaptor = null;

    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �E(SYSTEM_PREFERENCES�A�_�v�^)����������
     * <BR>
     * @@param l_web3CallBackAccessUtil DB�A�N�Z�T�C���X�^���X
     * @@throws PersistException
     */
    private WEB3CallbackSystemPreferencesAdaptorUtils(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil)
    {
    	loadProperties(l_web3CallBackDataAccessUtil);
    }
    
    /**
     * �R���X�g���N�^
     * ��UT�Ń_�~�[�C���X�^���X�������g�p�����
     *
     */
    public WEB3CallbackSystemPreferencesAdaptorUtils()
    {
    }
    
    /**
     * �V���O���g���C���X�^���X��Ԃ�
     * @@param l_web3CallBackDataAccessUtil DB�A�N�Z�T�C���X�^���X
     * @@return �V���O���g���C���X�^���X
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
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * �w�肵���L�[�����݂��Ȃ��ꍇ��<code>null</code>��Ԃ��B
     * <BR>
     * @@param l_strKey �L�[
     * @@return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
     */
    public String getProperty(String l_strKey)
    {
        return m_properties.getProperty(l_strKey);
    }

    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * �w�肵���L�[�����݂��Ȃ��ꍇ�̓f�t�H���g�l��Ԃ��B
     * <BR>
     * @@param l_strKey �L�[
     * @@param l_intDefaultValue �f�t�H���g�l
     * @@return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
     */
    public int getProperty(String l_strKey, int l_intDefaultValue)
    {
        return m_properties.getProperty(l_strKey) == null ? 
        	l_intDefaultValue:
        		Integer.parseInt(m_properties.getProperty(l_strKey));
    }

	/**
	 * (get�v���p�e�B)<BR>
	 * <BR>
	 * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
	 * �w�肵���L�[�����݂��Ȃ��ꍇ�̓f�t�H���g�l��Ԃ��B
	 * <BR>
	 * @@param l_strKey �L�[
	 * @@param l_intDefaultValue �f�t�H���g�l
	 * @@return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
	 */
	public long getProperty(String l_strKey, long l_lngDefaultValue)
	{
		return m_properties.getProperty(l_strKey) == null?
			l_lngDefaultValue:
				Long.parseLong(m_properties.getProperty(l_strKey));
	}

    /**
     * (get�v���p�e�B)<BR>
     * <BR>
     * �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���̐ݒ�l����������B
     * �w�肵���L�[�����݂��Ȃ��ꍇ�̓f�t�H���g�l��Ԃ��B
     * <BR>
     * @@param l_strKey �L�[
     * @@param l_intDefaultValue �f�t�H���g�l
     * @@return �w�肵���L�[��SYSTEM_PREFERENCES�e�[�u���ɕۑ�����Ă���ݒ�l
     */
    public String getProperty(String l_strKey, String l_strDefaultValue)
    {
        return m_properties.getProperty(l_strKey) == null ?
        	l_strDefaultValue:
        		m_properties.getProperty(l_strKey);
    }


    /**
     * (�v���p�e�B�Q�b�g)<BR>
     * <BR>
     * �A�_�v�^�ɕێ�����Properties��Ԃ��B
     * @@return m_properties
     */
    public Properties getProperties()
    {
        return m_properties;
    }

    /**
     * (load)<BR>
     * <BR>
     * SYSTEM_PREFERECES�e�[�u���̓��e���f�[�^�x�[�X���烍�[�h����B
     * @@return SYSTEM_PREFERENCES���烍�[�h�������e��
     *   �ݒ肵��<code>Properties</code>
     * @@throws PersistException
     */
    public void loadProperties(WEB3CallBackDataAccessUtil l_web3CallBackDataAccessUtil)
    {
    	
		final String l_strSql = "select name, value from system_preferences";
		
		//�v���p�e�B�����o�[��������
		if ( m_properties == null)
		{
			m_properties = new Properties();
		}
		
		//�v���p�e�B���N���A����
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
