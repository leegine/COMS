head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeToIfoDepositInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����ւ̐U�֓��̓��N�G�X�g�N���X(WEB3AccTransChangeToIfoDepositInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�؋����ւ̐U�֓��̓��N�G�X�g)<BR>
 * �؋����ւ̐U�֓��̓��N�G�X�g�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeToIfoDepositInputRequest extends WEB3GenRequest 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_to_ifo_deposit_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;    
        
    /**
     * @@roseuid 4158E99201AE
     */
    public WEB3AccTransChangeToIfoDepositInputRequest() 
    {
     
    }
    
    /**
     * �icreateResponse�̎����j <BR>
     * <BR>
     * �؋����ւ̐U�֓��̓��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158E99201C2
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AccTransChangeToIfoDepositInputResponse(this);
    }
}
@
