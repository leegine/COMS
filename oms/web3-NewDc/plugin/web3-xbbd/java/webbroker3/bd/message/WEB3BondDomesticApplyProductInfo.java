head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyProductInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������������(WEB3BondDomesticApplyProductInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 ���f��No.227
*/
package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (����������������)<BR>
 * ����������������<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductInfo extends Message
{

    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String productId;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (����J�n��)<BR>
     * ����J�n��<BR>
     */
    public Date recruitStartDate;

    /**
     * (����I����)<BR>
     * ����I����<BR>
     */
    public Date recruitEndDate;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public String coupon;

    /**
     * (����(�ېŌ�))<BR>
     * ����(�ېŌ�)<BR>
     */
    public String rateAfterTax;

    /**
     * (����P��)<BR>
     * ����P��<BR>
     */
    public String applyPrice;

    /**
     * (�\���P��)<BR>
     * �\���P��<BR>
     */
    public String applyUnit;

    /**
     * (���s��)<BR>
     * ���s��<BR>
     */
    public Date issueDate;

    /**
     * (���ғ�)<BR>
     * ���ғ�<BR>
     */
    public Date maturityDate;

    /**
     * (������1)<BR>
     * ������1<BR>
     */
    public String couponPaymentDate1;

    /**
     * (������2)<BR>
     * ������2<BR>
     */
    public String couponPaymentDate2;

    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����<BR>
     */
    public String yearlyInterestPayments;

    /**
     * (�戵�J�n����)<BR>
     * �戵�J�n����<BR>
     */
    public Date tradeStartDate;

    /**
     * (�戵�I������)<BR>
     * �戵�I������<BR>
     */
    public Date tradeEndDate;

    /**
     * (����\�敪)<BR>
     * ����\�敪<BR>
     * <BR>
     * 0�F�s��<BR>
     * 1�F�\<BR>
     * 2�F����g����<BR>
     */
    public String tradingPossDiv;

    /**
     * (����������������)<BR>
     * �R���X�g���N�^
     * @@roseuid 466645BA01F1
     */
    public WEB3BondDomesticApplyProductInfo()
    {

    }
}
@
