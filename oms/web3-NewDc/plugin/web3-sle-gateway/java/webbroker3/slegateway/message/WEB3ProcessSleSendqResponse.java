head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleSendqResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ProcessSleSendqResponse�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 �� �V�K�쐬
*/
package webbroker3.slegateway.message;

import java.util.Date;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * ProcessSleSendqRequest���b�Z�[�W�̃��X�|���X�N���X�ł��B
 */
public class WEB3ProcessSleSendqResponse extends WEB3GenResponse {

	/** �ŏ�ʃ��x���̃^�O�ł��B */
	public static final String TAGNAME = "response";

	/** ���̃��b�Z�[�W��PTYPE�ł��B */
	public static final String PTYPE = WEB3ProcessSleSendqRequest.PTYPE;
	
	/**
	 * ���X�|���X�ԐM����
	 */
	public Date date;
	
	/**
	 * �R���X�g���N�^
	 */
	public WEB3ProcessSleSendqResponse()
	{ 
	}
	
	/**
	 * �R���X�g���N�^�B<BR>
	 * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
	 * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
	 */
	public WEB3ProcessSleSendqResponse(WEB3GenRequest  request)
	{ 
		super(request);
	}	

}
@
