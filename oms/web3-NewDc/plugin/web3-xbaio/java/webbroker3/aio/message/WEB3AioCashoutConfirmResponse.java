head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���\���m�F���X�|���X(WEB3AioCashoutConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 ���� (���u) �V�K�쐬
                   2004/10/25 ���E(���u) ���r���[
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�o���\���m�F���X�|���X)<BR>
 * �o���\���m�F���X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3AioCashoutConfirmResponse extends WEB3AioCashoutCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashout_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410101030L;  
      
    /**
     * (�o�����z)<BR>
     * ��ʂɂē��͂��ꂽ�o���z
     */
    public String cashoutAmt;
    
    /**
     * (�U���\���)<BR>
     * ��ʂɂđI�����ꂽ�U���\���
     */
    public Date transScheduledDate;
    
    /**
     * (�m�F��������)<BR>
     * �m�F�������̔�����<BR>
     * �i��ʕ\���Ȃ��j
     */
    public Date checkDate;
    
    /**
     * (�m�F������ID)<BR>
     * �m�F�������̒���ID<BR>
     * �i��ʕ\���Ȃ��j
     */
    public long checkOrderID;
    
    /**
     * @@roseuid 4158EB6200E3
     */
    public WEB3AioCashoutConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioCashoutConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
