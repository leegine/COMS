head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.02.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SHGlConnectorServer.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3SHGlConnectorServerクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/09/01  新規作成
 */
package com.fitechlabs.xbconnector.sleconnector.impl;

import com.fitechlabs.xbconnector.sleconnector.GlSleConnectorException;

/**
 * SESH中国本土上海市場へ向けのカスタマイズWEB3SHGlConnectorServer
 */
public class WEB3SHGlConnectorServer extends WEB3GlConnectorServer
{


    /**
     * カスタマイズのWEB3SHGlConnectorServerのコンストラクタ
     * @@param xmlPath Config設定のXMLファ@イルパス
     */
    private WEB3SHGlConnectorServer(String xmlPath)
    {
        super(xmlPath);
        this.m_marketCode="X1";
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
        WEB3SHGlConnectorServer server = new WEB3SHGlConnectorServer(path);
        server.start();
    }


}

@
