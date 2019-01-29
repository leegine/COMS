head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋������ڎQ�Ɖ�ʕ\�����X�|���X(WEB3IfoDepositTransitionReferenceResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 �R�c�@@��i(FLJ) �V�K�쐬
 Revesion History : 2007/06/27 hijikata(SRA) �[��Ή� ���f��No.055, No.085
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�؋������ڎQ�Ɖ�ʕ\�����X�|���X)<BR>
 * �؋������ڎQ�Ɖ�ʕ\�����X�|���X�N���X�B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositTransitionReferenceResponse extends WEB3GenResponse
{
    
    public static final String PTYPE = "ifodeposit_transition_reference";

    /**
     * (�؋������ږ���)<BR>
     * �؋������ڂ̖��׈ꗗ�B<BR>
     */
    public WEB3IfoDepositTransitionReferenceUnit[] ifoDepositUnit;

    /**
     * (�������z)<BR>
     * �������z�B<BR>
     */
    public String nonPayAmt;

    /**
     * (�{�������z)<BR>
     * �{�������z�B<BR>
     */
    public String todayClaimAmt;

    /**
     * (���������z)<BR>
     * ���������z�B<BR>
     */
    public String tomorrowClaimAmt;

    /**
     * (���X�������z)<BR>
     * ���X�������z�B<BR>
     */
    public String dayAfterTomorrowClaimAmt;

    /**
     * (���������z���[�ꁄ)<BR>
     * ���������z���[�ꁄ�B<BR>
     */
    public String tomorrowClaimAmtEve;

    /**
     * (�؋����U�֗]�͊z)<BR>
     * �؋����U�֗]�͊z�B<BR>
     */
    public String depositChangePower;

    /**
     * (��n��)<BR>
     * ��n���B<BR>
     * �؋����v�Z�̊����ݒ�B<BR>
     */
    public java.util.Date deliveryDate;

    /**
     * (SPAN�敪)<BR>
     * 0�FSPAN��̗p<BR>
     * 1�FSPAN�̗p<BR>
     * @@see webbroker3.ifodeposit.define.WEB3IfoDepositSPANDivDef
     */
    public String spanDiv;

    /**
     * (�؋����s���m��FLAG)<BR>
     * T+0�̏؋����s�����m�肵�Ă��邩(�����������z���m��l�ł��邩)��\���t���O�B<BR>
     * 0�F���m�� <BR>
     * 1�F�m��<BR>
     * <BR>
     * @@see webbroker3.ifodeposit.define.WEB3IfoDepositFixedIfoDepositFlgDiv
     */
    public String fixedIfoDepositFlg;
    
    /**
     * (�w���ʏ؋���)<BR>
     * �w���ʏ؋����̈ꗗ�B<BR>
     * <BR>
     * SPAN�g�p�s�̏ꍇ�ݒ�B<BR>
     */
    public WEB3IfoDepositPerIndexUnit[] ifoDepositPerIndexUnit;

    /**
     * @@roseuid 4186176E0166
     */
    public WEB3IfoDepositTransitionReferenceResponse()
    {

    }
}
@
