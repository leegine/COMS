head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP������t�X�V�C���^�Z�v�^(WEB3IfoAcceptedUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 Ḗ@@�� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (�����w���敨��������ʒm���N�G�X�g)<BR>
 * �����w���敨��������ʒm���N�G�X�g�N���X<BR>
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyRequest extends WEB3BackRequest 
{
    /**
       * PTYPE<BR>
       */
      public final static String PTYPE ="futures_changeCancelNotify";
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407221030L;      
    /**
     * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * �X���b�hNo<BR>
     */
    public Long threadNo;
    /**
     * @@roseuid 40F7AE1A037A
     */
    public WEB3FuturesChangeCancelNotifyRequest() 
    {
     
    }
    
    /**
     * @@return WEB3BackResponse
     * @@roseuid 40F7AE1A038A
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3FuturesChangeCancelNotifyResponse(this);
    }
}
@
