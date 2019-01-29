head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3GlConnectorServer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3GlConnectorServerクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/01/15  新規作成
 */
package com.fitechlabs.xbconnector.sleconnector.impl;

import java.lang.reflect.Field;
import com.fitechlabs.xbconnector.sleconnector.GlSleConnectorException;
import com.fitechlabs.xbconnector.glbase.admin.AdminAdaptor;
import com.fitechlabs.xbconnector.glbase.admin.AdminException;
import com.fitechlabs.xbconnector.glbase.utils.GlLogger;
import com.fitechlabs.xbconnector.glbase.utils.StatusFlag;

/**
 * SEHK香港市場へ向けのカスタマイズWEB3GlConnectorServer
 */
public class WEB3GlConnectorServer
{

    public static final GlLogger logger = GlLogger.getLogger("gl-sle-connector");

    public String xmlPath;

    /**
     * Serverが持つコネクタ実装インスタンス
     */
    public static GlConnectorImpl m_connImpl = null;
    
    /**
     * 市場コード:DEFAULT(N1香港市場)
     * SLEコネクタインスタンスの対応市場の市場コード
     * 他市場対応するには、市場コードのメンバー変数を追加 2007/10/24
     */
    public String m_marketCode = "N1";


    /**
     * コネクタインスタンスを返す
     * @@return m_connImpl メンバーを返す
     *
     */
    public static GlConnectorImpl getGlConnectorInstance()
    {
        return m_connImpl ;
    }


	/**
     * カスタマイズのWEB3GlConnectorServerのコンストラクタ
     * @@param xmlPath Config設定のXMLファ@イルパス
     */
    public WEB3GlConnectorServer(String xmlPath)
    {
        this.xmlPath = xmlPath;
    }

    /**
     * カスタマイズのWEB3GlConnectorServer起動時でログ情報
     */
    public static void logo()
    {
        if(logger.isInfoEnabled())
            logger.info("Gl SLE Connector server.\nCopyright (C) Fitech Laboratories, Inc. 2000-2006. All Rights Reserved.\n");
    }

    /**
     * カスタマイズのWEB3GlConnectorServer起動時でパラメータエラー時のログ情報
     */
    public static void showUsage(String errMsg)
    {
        if(errMsg != null)
            logger.error(errMsg);
        logger.error("Usage: without parameters or [path to SLE Connector's config file] or [?] or [help]");
        logger.error("\tExample: c:/config.xml");
        logger.error("\tExample: ?\n");
        System.exit(errMsg == null ? 0 : -1);
    }

    /**
     * カスタマイズのWEB3GlConnectorServerを起動するためのMain処理
     */
    public static void main(String args[])
        throws GlSleConnectorException
    {
        logo();
        if(args.length > 1)
            showUsage("Wrong parameters count.");
        String path = null;
        if(args.length == 1)
        {
            String arg = args[0];
            if(arg.equals("?") || arg.equalsIgnoreCase("help"))
                showUsage(null);
            else
                path = arg;
        }
        WEB3GlConnectorServer server = new WEB3GlConnectorServer(path);
        server.start();
    }

    /**
     * カスタマイズのWEB3GlConnectorServerの起動処理
     * @@throws GlSleConnectorException SLEコネクタインスタンス起動時でエラーが発生する時スローされる
     */
    public void start()
        throws GlSleConnectorException
    {
        // SLEコネクタインスタンスを生成
        GlConnectorImpl impl = xmlPath != null ? new GlConnectorImpl(xmlPath) : new GlConnectorImpl();

        /** m_connImplへ生成したコネクタインスタンスを代入*/
        m_connImpl = impl;

        Field f1;
        try {
            f1 = GlConnectorImpl.class.getDeclaredField("ready");
            f1.setAccessible(true);
            StatusFlag pcf1=(StatusFlag)f1.get(impl);
            WEB3StatusFlag wb3pcf1=new WEB3StatusFlag(m_marketCode);
            f1.set(impl, wb3pcf1);
            wb3pcf1.setGlConnImpl(impl);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("SLEコネクタインスタンスのStatusFlag::ready設定reset失敗",e);
        }
        
        // SLEコネクタインスタンスを起動
        impl.start();

        // カスタマイズした AdminAdaptorインスタンスを生成
        AdminAdaptor mgmtServer = new WEB3AdminAdaptor(impl);

        // AdminAdaptorからカスタマイズしたServerを起動する
        try
        {
            mgmtServer.startServer();
        }
        catch(AdminException e)
        {
            throw new GlSleConnectorException("Error starting administration server for Gl SLE Connector.", e);
        }
    }


}

@
