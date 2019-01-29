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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3SHGlConnectorServer�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/09/01  �V�K�쐬
 */
package com.fitechlabs.xbconnector.sleconnector.impl;

import com.fitechlabs.xbconnector.sleconnector.GlSleConnectorException;

/**
 * SESH�����{�y��C�s��֌����̃J�X�^�}�C�YWEB3SHGlConnectorServer
 */
public class WEB3SHGlConnectorServer extends WEB3GlConnectorServer
{


    /**
     * �J�X�^�}�C�Y��WEB3SHGlConnectorServer�̃R���X�g���N�^
     * @@param xmlPath Config�ݒ��XML�t�@@�C���p�X
     */
    private WEB3SHGlConnectorServer(String xmlPath)
    {
        super(xmlPath);
        this.m_marketCode="X1";
    }

    /**
     * �J�X�^�}�C�Y��WEB3GlConnectorServer���N�����邽�߂�Main����
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
