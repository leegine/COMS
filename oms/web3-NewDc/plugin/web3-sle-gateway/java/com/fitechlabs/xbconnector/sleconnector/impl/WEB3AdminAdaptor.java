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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3AdminAdaptor�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/01/15 �V�K�쐬 
 */
package com.fitechlabs.xbconnector.sleconnector.impl;

import com.fitechlabs.xbconnector.glbase.admin.AdminAdaptor;
import com.fitechlabs.xbconnector.sleconnector.GlSleConnectorException;

 /**
 * SEHK���`�s��֌����̃J�X�^�}�C�YAdminAdaptor
 */
public class WEB3AdminAdaptor extends AdminAdaptor
{
	
	/**
	 * SLE�R�l�N�^�C���X�^���X�����o�[
	 */
	private GlConnectorImpl impl;

	/**
	 * �J�X�^�}�C�YAdminAdaptor�R���X�g���N�^
	 * @@param impl SLE�R�l�N�^�C���X�^���X
	 */
	public WEB3AdminAdaptor(GlConnectorImpl impl){
		
		super("gl-sle-connector", impl,impl.getAdminServerDescriptor(), "gl-sle-connector");
		this.impl=impl;	
	}
	
	/**
	 * SLE�R�l�N�^���N������
	 */
	public void onStart()
	{
	}

	/**
	 * SLE�R�l�N�^���~����
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
