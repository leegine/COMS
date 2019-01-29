head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleSendqRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ProcessSleSendqRequest�N���X
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/24 �� �V�K�쐬
*/
package webbroker3.slegateway.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * send q�ɂ��郁�b�Z�[�W���������邽��sle market adapter�֒ʒm���邽�߂ɗ��p����郁�b�Z�[�W�ł��B

 */
public class WEB3ProcessSleSendqRequest extends WEB3GenRequest
{

	/** �ŏ�ʃ^�O���ł��B */
	public static final String TAGNAME = "request";

	/** ���̃��b�Z�[�W��PTYPE�ł��B */
	public static final String PTYPE = "process_sendq";//��2006/10/11�@@request���b�Z�[�W��PTYPE��ύX
	
	/**
	 * �X���b�hNO 
	 */
	public	Long threadNo;
	
	/**
	 * �����J�nID
	 */
	public long fromAccountId;
	
	/**
	 * �����I��ID
	 */
	public long toAccountId;
	
	/**
	 * �s��R�[�h
	 */
	public String[] marketCode;//��2007/10/23 �[�Z���s��Ή�
	
	/**
	 * ���N�G�X�g���M����
	 */
	public Date date;

    /* (�� Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
		return new WEB3ProcessSleSendqResponse(this);
    }
}
@
