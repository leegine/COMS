head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋�������U�֊m�F���X�|���X�N���X(WEB3AccTransChangeFromIfoDepositConfirmResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/10/22 ���z (���u) ���r���[
Revesion History : 2007/08/23 ���g (���u) �d�l�ύX�E���f��753
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�؋�������U�֊m�F���X�|���X)<BR>
 * �؋�������U�֊m�F���X�|���X�N���X
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositConfirmResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_from_ifo_deposit_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;
        
    /**
     * (�U�֌�U�։\�z)<BR>
     * �ڋq�̐U�֌�̐U�։\�z
     */
    public String aftChangePossAmt;
    
    /**
     * (�U�֌�؋���)<BR>
     * �ڋq�̐U�֌�̏؋����c��
     */
    public String aftIfoDeposit;
    
    /**
     * (�U�֌エ�a������c��)<BR>
     * �ڋq�̐U�֌�̗a����c��<BR>
     */
    public String aftDepositBal;

    /**
     * (�m�F��������)<BR>
     */
    public Date checkDate;
    
    /**
     * (����ID)<BR>
     * �m�F�����Ŏ擾��������ID
     */
    public String orderId;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4158E8E2039F
     */
    public WEB3AccTransChangeFromIfoDepositConfirmResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AccTransChangeFromIfoDepositConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
