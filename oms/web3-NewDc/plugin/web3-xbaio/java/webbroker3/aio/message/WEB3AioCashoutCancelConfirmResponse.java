head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.05.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������m�F���X�|���X(WEB3AioCashoutCancelConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 ���E (���u) �V�K�쐬 
                   2004/10/22 ���� (���u) ���r���[                      
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�o������m�F���X�|���X)<BR>
 * �o������m�F���X�|���X�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashoutCancelConfirmResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L;    
    /**
     * (����ΏۂƂȂ��Ă��钍����ID)
     */
    public String orderId;
    
    /**
     * (�o���]��)<BR>
     * �ڋq�̌��݂̏o���]��
     */
    public String paymentPower;
    
    /**
     * (�o�����z)<BR>
     * ����ΏۂƂȂ��Ă��钍���̏o���z
     */
    public String cashoutAmt;
    
    /**
     * (�U���\���)<BR>
     * ����ΏۂƂȂ��Ă��钍���̐U���\���
     */
    public Date transScheduledDate;
    
    /**
     * (�m�F��������)<BR>
     * �m�F�������̔�����<BR>
     * �i��ʕ\���Ȃ��j
     */
    public Date checkDate;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4158EB5F0391
     */
    public WEB3AioCashoutCancelConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashoutCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@