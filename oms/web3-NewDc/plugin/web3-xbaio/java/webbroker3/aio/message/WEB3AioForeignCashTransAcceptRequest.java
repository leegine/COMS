head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.59.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioForeignCashTransAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�ݓ��o����t���N�G�X�g(WEB3AioForeignCashTransAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/03 ���G�� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�O�ݓ��o����t���N�G�X�g)<BR>
 * �O�ݓ��o����t���N�G�X�g�N���X<BR>
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3AioForeignCashTransAcceptRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "aio_foreign_cash_trans_accept";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609031518L;    
        
    public WEB3AioForeignCashTransAcceptRequest() 
    {
     
    }

    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �O�ݓ��o����t���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioForeignCashTransAcceptResponse(this);
    }
}
@
