head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇������m�F���N�G�X�g�N���X(WEB3AdminAioCashoutInqCancelConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�o���\���⍇������m�F���N�G�X�g)<BR>
 * �o���\���⍇������m�F���N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCancelConfirmRequest extends WEB3AdminAioCashoutInqCommonRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_cancel_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131400L;
        
    /**
     * @@roseuid 4158EB64017C
     */
    public WEB3AdminAioCashoutInqCancelConfirmRequest() 
    {
     
    }
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �o���\���⍇������m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqCancelConfirmResponse(this);
    }
}
@
