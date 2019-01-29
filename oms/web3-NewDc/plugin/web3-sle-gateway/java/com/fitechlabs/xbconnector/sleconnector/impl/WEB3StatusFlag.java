head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3StatusFlag.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3StatusFlag�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/01/15  FLJ(��) �V�K�쐬
 Revision History : 2007/10/23  FLJ(��) ���s��Ή����C
 */
package com.fitechlabs.xbconnector.sleconnector.impl;

import java.util.*;
import java.sql.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import com.fitechlabs.xbconnector.glbase.utils.StatusFlag;
import org.apache.log4j.Category;
import webbroker3.slegateway.callback.*;


/**
 * SEHK���`�s��֌����̃J�X�^�}�C�YWEB3StatusFlag
 * ���s��Ή��ɂ́A���C 2007/10/24
 */
public class WEB3StatusFlag extends StatusFlag
{
    /**
     * ���O�o�̓I�u�W�F�N�g
     */
    private static final Category m_log = Category.getInstance(WEB3StatusFlag.class);
    private static final boolean DBG = m_log.isDebugEnabled();
    
    /**
     * ���J�o���[�����p��PV�t���O
     * false:������ true:������
     */
	private boolean isProcessing=false;
	/**
	 * GL SLE�R�l�N�^�C���X�^���X
	 */
	private GlConnectorImpl m_connImpl = null;
	
	/**
	 * GL SLE�s��R�[�h
	 * ���s��Ή��ɂ́A�s��R�[�h�̃����o�[�ϐ���ǉ�
	 */
	private String m_marketCode = null;

    
	/**
	 * DB�A�N�Z�X�p�c�[��
	 */
	private static WEB3CallBackDataAccessUtil m_dao;
	
	/**
	 * �������b�Z�[�W���J�o���[ Recoverier
	 */
	private static WEB3RecoveryOrdersAtConnectionCallback m_recoverier;


	public void setGlConnImpl(GlConnectorImpl m_connImpl ){
		this.m_connImpl=m_connImpl;
	}


    public WEB3StatusFlag(String  l_marketCode)
    {
        super();

		try {
			m_dao =
				new WEB3CallBackDataAccessUtil(
					WEB3SleConnectionCallback.m_dao.m_driver,
					WEB3SleConnectionCallback.m_dao.m_url,
					WEB3SleConnectionCallback.m_dao.m_user,
					WEB3SleConnectionCallback.m_dao.m_password);

			WEB3CallbackSystemPreferencesAdaptorUtils l_adaptor=
			WEB3CallbackSystemPreferencesAdaptorUtils.getInstance(m_dao); 

			WEB3CallbackBizDateProviderUtils l_DateProvider =
				WEB3CallbackBizDateProviderUtils.getInstance(l_adaptor);
		
			m_recoverier =
				WEB3RecoveryOrdersAtConnectionCallback.getInstance(
					m_dao,
					l_adaptor,
					l_DateProvider);

		} catch (Exception e) {

			e.printStackTrace();
			m_log.error(e.getMessage(),e);
		}
		
		//SLE�R�l�N�^�C���X�^���X�̎s��R�[�h���w��
		m_marketCode = l_marketCode;

    }

    public  void setFlag(boolean flag)
    {
			
		//Reconnection���Ń��J�o���[���s��
        if (flag == true)
        {
			m_log.info("try recovery orders when WEB3StatusFlag.setFlag(true)");
			
			//���J�o���[�������̏ꍇ�A���J�o���[���삵�Ȃ�
			if(isProcessing==true &&  super.getFlag() == false &&  flag==true){
				m_log.info("skip for isProcessing ==true");		
				return;
			}	

			
            try
            {	
				 //���J�o���[����PV�t���O��ON��
				 isProcessing=true;	
				 boolean isStarted =false;
				 Field f1;			
				 //GL SLE�R�l�N�^�C���X�^���X����isStarted�t���O���擾
				 f1 = GlConnectorImpl.class.getDeclaredField("isStarted");
				 f1.setAccessible(true);
				 isStarted =f1.getBoolean(m_connImpl);
				 //GL SLE�R�l�N�^�����N���̏ꍇ�A�Ⴆ�FListener����setFlag��ݒ肷�鎞
				 //���J�o���[���삵�Ȃ�
				 if(isStarted==false){
					m_log.info("skip for isStarted ==false");		
				 	return;
				 }

								
				 //HashMap h_lstConn =(HashMap) getLastestSLEConnStatus(0,"N1");
				 HashMap h_lstConn =(HashMap) getLastestSLEConnStatus(0,m_marketCode);
				  //���s��Ή����邽�߂ɂ́ASLE�R�l�N�^�N���R�}���h�̃R�}���h���s��R�[�h���擾����悤�ɉ��C 2007/10/24
				 
				  if ( h_lstConn == null)
				  {
				  	m_log.warn("recovery operation stopped because SLE connection is not available for marke code=" + m_marketCode);
				  	return;
				  }
        		  //�Đڑ�������SLE�ڑ����R�[�h��QUE_ID���擾
				  long l_qid = ((BigDecimal)(h_lstConn.get("QUE_ID"))).longValue();
				  String s_marketcode = (String)h_lstConn.get("MARKET_CODE");
				  
				  //���J�o���[����J�n
				  m_recoverier.reSetProductsOrderBookMap();
				  m_recoverier.reSetRecoveryCheckDoneProductCodesMap();
				  m_recoverier.doRecoveryOrdersAtConnectorCallback(s_marketcode,l_qid);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                m_log.error("doRecoveryOrdersAtConnectorCallback :" + e);
            }
			finally 
			{
				//���J�o���[����PV�t���O���J��
				isProcessing=false;
			}
        }
        
		super.setFlag(flag);
    }
    
	/**
	 * �w�肵���Ώۏ����敪�̍ŐVSLE�ڑ���ԃf�[�^���擾����
	 * 
	 * @@param l_iProcstatus �Ώۏ����敪
	 * 
	 * @@return List �w�肵���Ώۏ����敪�̍ŐVSLE�ڑ���ԃf�[�^(size = 0)
	 */
	private Map getLastestSLEConnStatus(final int l_iProcstatus,final String s_marketCode)
	{
//		final String l_strSql = "select que_id,market_code,new_status,sle_conn_div from sle_conn_status_changes where proc_status = ? and market_code = ? order by created_timestamp desc";
		final String l_strSql = "select que_id,market_code,new_status,sle_conn_div from sle_conn_status_changes where proc_status = ? and market_code = ? order by que_id desc";
		
		List l_lisParams = new ArrayList(1);
		List l_lisQueryResult = new ArrayList(1);
		try{
			
			l_lisParams.add(new Integer(l_iProcstatus));
			l_lisParams.add( s_marketCode);
			
			l_lisQueryResult = m_dao.executeQuery(l_strSql, l_lisParams);
			
			if (l_lisQueryResult.size() > 0)
			{
			
				return (HashMap) l_lisQueryResult.get(0);
			}
			else
			{
				return null;
			}
		}
		catch (SQLException e)
		{
			final String msg = "SQLException Catched! when excuting getLastestSLEConnStatus";
			m_log.error(msg , e);
			throw new RuntimeException (msg,e);
		}
		finally
		{
			try
			{
				m_dao.releaseResource(false);
			}
			catch(SQLException sqle)
			{
				m_log.error("DB Error while closing ResultSet,Statement or Connection.");
			}
		 }
	}


}
@
