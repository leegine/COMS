head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.19.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o������������X�|���X(WEB3AioCashoutCancelCompleteResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�o������������X�|���X)<BR>
 * �o������������X�|���X�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutCancelCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_cancel_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410100905L;    
    
    /**
     * (����ΏۂƂȂ��Ă��钍���̏o���z)
     */
    public String cashoutAmt;
    
    /**
     * (�U���\���)<BR>
     * ����ΏۂƂȂ��Ă��钍���̐U���\���
     */
    public Date transScheduledDate;
    
    /**
     * (DB�X�V����)
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (�������������̒���ID)
     */
    public String orderId;
    
    /**
     * @@roseuid 4158EB5F00A2
     */
    public WEB3AioCashoutCancelCompleteResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashoutCancelCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
