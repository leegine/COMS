head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositTransitionReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋������ږ��׃N���X(WEB3IfoDepositTransitionReference)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/02 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�؋������ږ���)<BR>
 * �؋������ږ��׃N���X�B<BR>
 * �؋����̐��ڎQ�Ɖ�ʂ̈���\������B<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoDepositTransitionReferenceUnit extends Message
{

    /**
     * (���t)<BR>
     * T+0�A�܂��́AT+1�A�܂��́AT+2�̓��t�B<BR>
     */
    public java.util.Date bizDate;

    /**
     * (�؋����c)<BR>
     * �؋����c���B<BR>
     */
    public String ifoDepositBal;

    /**
     * (�{���U�֊z)<BR>
     * �{���U�֊z�B<BR>
     */
    public String todayCahangeAmt;

    /**
     * (�{���敨���ϑ��v)<BR>
     * �{���敨���ϑ��v�B<BR>
     * <BR>
     * T+1�AT+2�̂ݐݒ�B<BR>
     */
    public String todayFutSettleProfitLoss;

    /**
     * (�{���I�v�V������n���)<BR>
     * �{���I�v�V������n����B<BR>
     * <BR>
     * T+1�AT+2�̂ݐݒ�B<BR>
     */
    public String todayOpNetAmt;

    /**
     * (�敨�]�����v)<BR>
     * �敨�]�����v�B<BR>
     */
    public String futEvalProfitLoss;

    /**
     * (�����敨�]�����v)<BR>
     * �����敨�]�����v�B<BR>
     */
    public String lfEvalProfitLoss;

    /**
     * (�����敨�]�����v)<BR>
     * �����敨�]�����v�B<BR>
     */
    public String sfEvalProfitLoss;

    /**
     * (����؋����c)<BR>
     * ����؋����c���B<BR>
     */
    public String acceptanceIfoDepositBal;

    /**
     * (���|�W�V��������)<BR>
     * ���|�W�V�������ʁB<BR>
     */
    public String longPositionContract;

    /**
     * (���|�W�V��������(��������))<BR>
     * ���������|�W�V�������ʁB<BR>
     */
    public String partLongPositionContract;

    /**
     * (���|�W�V��������)<BR>
     * ���|�W�V�������ʁB<BR>
     */
    public String shortPositionContract;

    /**
     * (���|�W�V��������(��������))<BR>
     * ���������|�W�V�������ʁB<BR>
     */
    public String partShortPositionContract;

    /**
     * (�|�W�V�����o�����X)<BR>
     * �|�W�V�����o�����X�B<BR>
     */
    public String positionBalance;

    /**
     * (�|�W�V�����o�����X�敪)<BR>
     * 0�F�j���[�g����<BR>
     * 1�F������<BR>
     * 2�F������<BR>
     */
    public String positionBalanceDiv;

    /**
     * (SPAN�؋���)<BR>
     * SPAN�؋����B<BR>
     * <BR>
     * SPAN�̗p��Ђ̂ݐݒ�B<BR>
     */
    public String spanIfoDeposit;

    /**
     * (�l�b�g�I�v�V�������l���z)<BR>
     * �l�b�g�I�v�V�������l���z�B<BR>
     */
    public String netOptionlValueTotalAmt;

    /**
     * (�؋������v�z)<BR>
     * �؋������v�z�B<BR>
     */
    public String ifoDepositNecessaryAmt;

	/**
	 * (�؋����]�͊z)<BR>
	 * �؋����]�͊z�B<BR>
	 * <BR>
	 * T+1�AT+2�̂ݐݒ�B<BR>
	 */
	public String ifoDepositPower;
    
	/**
	 * (�؋����U�֗]�͊z)<BR>
	 * �؋����U�֗]�͊z�B<BR>
	 */
	public String depositChangePower;

    /**
     * (�I�v�V�����]�����v)<BR>
     * �I�v�V�����]�����v�B<BR>
     */
    public String opEvalProfitLoss;

    /**
     * (�����I�v�V�����]�����v)<BR>
     * �����I�v�V�����]�����v�B<BR>
     */
    public String loEvalProfitLoss;

    /**
     * (�����I�v�V�����]�����v)<BR>
     * �����I�v�V�����]�����v�B<BR>
     */
    public String soEvalProfitLoss;
    
    /**
     * (�w���ʏ؋������ږ���)<BR>
     * �w���ʏ؋������ږ��ׂ̈ꗗ�B<BR>
     * <BR>
     * SPAN�g�p�s�̏ꍇ�ݒ�B<BR>
     */
    public WEB3IfoDepositTranRefPerIndexUnit[] ifoDepositTranRefPerIndexUnit;
    
	/**
	 * (�{�������z)<BR>
	 * �{�������z�B<BR>
	 */
	public String todayCashinAmt;
    
    /**
     * @@roseuid 4186177701C4
     */
    public WEB3IfoDepositTransitionReferenceUnit()
    {

    }
}
@
