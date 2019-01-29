head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3AdminAdaptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3AdminAdaptorクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/01/15 新規作成 
 */
package com.fitechlabs.xbconnector.sleconnector.impl;

import com.fitechlabs.xbconnector.glbase.admin.AdminAdaptor;
import com.fitechlabs.xbconnector.sleconnector.GlSleConnectorException;

 /**
 * SEHK香港市場へ向けのカスタマイズAdminAdaptor
 */
public class WEB3AdminAdaptor extends AdminAdaptor
{
	
	/**
	 * SLEコネクタインスタンスメンバー
	 */
	private GlConnectorImpl impl;

	/**
	 * カスタマイズAdminAdaptorコンストラクタ
	 * @@param impl SLEコネクタインスタンス
	 */
	public WEB3AdminAdaptor(GlConnectorImpl impl){
		
		super("gl-sle-connector", impl,impl.getAdminServerDescriptor(), "gl-sle-connector");
		this.impl=impl;	
	}
	
	/**
	 * SLEコネクタを起動する
	 */
	public void onStart()
	{
	}

	/**
	 * SLEコネクタを停止する
	 */
	public void onStop()
	{
		try
		{
			impl.stop();
		}
		catch(GlSleConnectorException e)
		{
			WEB3GlConnectorServer.logger.error("Error stopping Gl SLE Connector.", e);
		}
	}
            

}
@
