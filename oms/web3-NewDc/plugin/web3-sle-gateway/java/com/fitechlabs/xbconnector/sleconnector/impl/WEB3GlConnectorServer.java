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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3GlConnectorServer�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/01/15  �V�K�쐬
 */
package com.fitechlabs.xbconnector.sleconnector.impl;

import java.lang.reflect.Field;
import com.fitechlabs.xbconnector.sleconnector.GlSleConnectorException;
import com.fitechlabs.xbconnector.glbase.admin.AdminAdaptor;
import com.fitechlabs.xbconnector.glbase.admin.AdminException;
import com.fitechlabs.xbconnector.glbase.utils.GlLogger;
import com.fitechlabs.xbconnector.glbase.utils.StatusFlag;

/**
 * SEHK���`�s��֌����̃J�X�^�}�C�YWEB3GlConnectorServer
 */
public class WEB3GlConnectorServer
{

    public static final GlLogger logger = GlLogger.getLogger("gl-sle-connector");

    public String xmlPath;

    /**
     * Server�����R�l�N�^�����C���X�^���X
     */
    public static GlConnectorImpl m_connImpl = null;
    
    /**
     * �s��R�[�h:DEFAULT(N1���`�s��)
     * SLE�R�l�N�^�C���X�^���X�̑Ή��s��̎s��R�[�h
     * ���s��Ή�����ɂ́A�s��R�[�h�̃����o�[�ϐ���ǉ� 2007/10/24
     */
    public String m_marketCode = "N1";


    /**
     * �R�l�N�^�C���X�^���X��Ԃ�
     * @@return m_connImpl �����o�[��Ԃ�
     *
     */
    public static GlConnectorImpl getGlConnectorInstance()
    {
        return m_connImpl ;
    }


	/**
     * �J�X�^�}�C�Y��WEB3GlConnectorServer�̃R���X�g���N�^
     * @@param xmlPath Config�ݒ��XML�t�@@�C���p�X
     */
    public WEB3GlConnectorServer(String xmlPath)
    {
        this.xmlPath = xmlPath;
    }

    /**
     * �J�X�^�}�C�Y��WEB3GlConnectorServer�N�����Ń��O���
     */
    public static void logo()
    {
        if(logger.isInfoEnabled())
            logger.info("Gl SLE Connector server.\nCopyright (C) Fitech Laboratories, Inc. 2000-2006. All Rights Reserved.\n");
    }

    /**
     * �J�X�^�}�C�Y��WEB3GlConnectorServer�N�����Ńp�����[�^�G���[���̃��O���
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
        WEB3GlConnectorServer server = new WEB3GlConnectorServer(path);
        server.start();
    }

    /**
     * �J�X�^�}�C�Y��WEB3GlConnectorServer�̋N������
     * @@throws GlSleConnectorException SLE�R�l�N�^�C���X�^���X�N�����ŃG���[���������鎞�X���[�����
     */
    public void start()
        throws GlSleConnectorException
    {
        // SLE�R�l�N�^�C���X�^���X�𐶐�
        GlConnectorImpl impl = xmlPath != null ? new GlConnectorImpl(xmlPath) : new GlConnectorImpl();

        /** m_connImpl�֐��������R�l�N�^�C���X�^���X����*/
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
            logger.error("SLE�R�l�N�^�C���X�^���X��StatusFlag::ready�ݒ�reset���s",e);
        }
        
        // SLE�R�l�N�^�C���X�^���X���N��
        impl.start();

        // �J�X�^�}�C�Y���� AdminAdaptor�C���X�^���X�𐶐�
        AdminAdaptor mgmtServer = new WEB3AdminAdaptor(impl);

        // AdminAdaptor����J�X�^�}�C�Y����Server���N������
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
