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
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3StatusFlagクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/01/15  FLJ(李) 新規作成
 Revision History : 2007/10/23  FLJ(李) 他市場対応改修
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
 * SEHK香港市場へ向けのカスタマイズWEB3StatusFlag
 * 他市場対応には、改修 2007/10/24
 */
public class WEB3StatusFlag extends StatusFlag
{
    /**
     * ログ出力オブジェクト
     */
    private static final Category m_log = Category.getInstance(WEB3StatusFlag.class);
    private static final boolean DBG = m_log.isDebugEnabled();
    
    /**
     * リカバリー処理用のPVフラグ
     * false:未処理 true:処理中
     */
	private boolean isProcessing=false;
	/**
	 * GL SLEコネクタインスタンス
	 */
	private GlConnectorImpl m_connImpl = null;
	
	/**
	 * GL SLE市場コード
	 * 他市場対応には、市場コードのメンバー変数を追加
	 */
	private String m_marketCode = null;

    
	/**
	 * DBアクセス用ツール
	 */
	private static WEB3CallBackDataAccessUtil m_dao;
	
	/**
	 * 注文メッセージリカバリー Recoverier
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
		
		//SLEコネクタインスタンスの市場コードを指定
		m_marketCode = l_marketCode;

    }

    public  void setFlag(boolean flag)
    {
			
		//Reconnection時でリカバリーを行う
        if (flag == true)
        {
			m_log.info("try recovery orders when WEB3StatusFlag.setFlag(true)");
			
			//リカバリー処理中の場合、リカバリー動作しない
			if(isProcessing==true &&  super.getFlag() == false &&  flag==true){
				m_log.info("skip for isProcessing ==true");		
				return;
			}	

			
            try
            {	
				 //リカバリー処理PVフラグをONに
				 isProcessing=true;	
				 boolean isStarted =false;
				 Field f1;			
				 //GL SLEコネクタインスタンスからisStartedフラグを取得
				 f1 = GlConnectorImpl.class.getDeclaredField("isStarted");
				 f1.setAccessible(true);
				 isStarted =f1.getBoolean(m_connImpl);
				 //GL SLEコネクタが未起動の場合、例え：ListenerからsetFlagを設定する時
				 //リカバリー動作しない
				 if(isStarted==false){
					m_log.info("skip for isStarted ==false");		
				 	return;
				 }

								
				 //HashMap h_lstConn =(HashMap) getLastestSLEConnStatus(0,"N1");
				 HashMap h_lstConn =(HashMap) getLastestSLEConnStatus(0,m_marketCode);
				  //他市場対応するためには、SLEコネクタ起動コマンドのコマンドより市場コードを取得するように改修 2007/10/24
				 
				  if ( h_lstConn == null)
				  {
				  	m_log.warn("recovery operation stopped because SLE connection is not available for marke code=" + m_marketCode);
				  	return;
				  }
        		  //再接続成功のSLE接続レコードのQUE_IDを取得
				  long l_qid = ((BigDecimal)(h_lstConn.get("QUE_ID"))).longValue();
				  String s_marketcode = (String)h_lstConn.get("MARKET_CODE");
				  
				  //リカバリー動作開始
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
				//リカバリー処理PVフラグを開放
				isProcessing=false;
			}
        }
        
		super.setFlag(flag);
    }
    
	/**
	 * 指定した対象処理区分の最新SLE接続状態データを取得する
	 * 
	 * @@param l_iProcstatus 対象処理区分
	 * 
	 * @@return List 指定した対象処理区分の最新SLE接続状態データ(size = 0)
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
