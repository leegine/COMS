head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���⍇������������N�G�X�g�N���X(WEB3AdminAioCashoutInqCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 ��O�� (���u) �V�K�쐬
                   2004/10/27 ���E(���u) ���r���[
                   2005/01/07 ���E (���u) �c�Ή� 
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o���\���⍇������������N�G�X�g)<BR>
 * �o���\���⍇������������N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCancelCompleteRequest extends WEB3AdminAioCashoutInqCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_cancel_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131402L;        
    
    /**
     * (��ʂɂē��͂��ꂽ�Ïؔԍ�)
     */
    public String password;
    
    /**
     * @@roseuid 4158EB640095
     */
    public WEB3AdminAioCashoutInqCancelCompleteRequest() 
    {
        
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �o���\���⍇������������X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqCancelCompleteResponse(this);
    }
}
@
