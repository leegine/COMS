head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AccTransChangeFromIfoDepositInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋�������U�֓��̓��X�|���X�N���X(WEB3AccTransChangeFromIfoDepositInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ������ (���u) �V�K�쐬
                   2004/10/22 ���z (���u) ���r���[
Revesion History : 2007/08/23 ���g (���u) �d�l�ύX�E���f��753
*/
package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�؋�������U�֓��̓��X�|���X)<BR>
 * �؋�������U�֓��̓��X�|���X�N���X<BR>
 * 
 * @@author ������(���u)
 * @@version 1.0
 */
public class WEB3AccTransChangeFromIfoDepositInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "acc_trans_change_from_ifo_deposit_input";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;
        
    /**
     * (�U�֏����)<BR>
     * 1���̐U�֏����<BR>
     */
    public String changeCountUpper;
    
    /**
     * (�U�։�)<BR>
     * �ڋq�̌����_�ł�1���̐U�։�<BR>
     */
    public String changeCount;
    
    /**
     * (�U�։\�z)<BR>
     * �ڋq�̌����_�ł̐U�։\�z<BR>
     * <BR>
     */
    public String changePossAmt;
    
    /**
     * (�؋����c��)<BR>
     * �ڋq�̌����_�ł̏؋����c��<BR>
     */
    public String ifoDepositBal;
    
    /**
     * (���a������c��)<BR>
     * �ڋq�̌����_�ł̗a����c��<BR>
     */
    public String depositBal;

    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4158E8E30170
     */
    public WEB3AccTransChangeFromIfoDepositInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AccTransChangeFromIfoDepositInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
