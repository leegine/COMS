head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������������̓��X�|���X(WEB3BondDomesticApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 ���f��No.227
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.message.WEB3GentradeProspectusResult;


/**
 * (������������̓��X�|���X)<BR>
 * ������������̓��X�|���X<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3BondDomesticApplyInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (���t�\�z)<BR>
     * ���t�\�z<BR>
     */
    public String tradingPower;

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
    public String couponAfterTax;

    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����<BR>
     */
    public String yearlyInterestPayments;

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
     * (�ژ_�����{���`�F�b�N����)<BR>
     * �ژ_�����{���`�F�b�N����<BR>
     */
    public WEB3GentradeProspectusResult prospectusResult;

    /**
     * (������������̓��X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46665EAA005B
     */
    public WEB3BondDomesticApplyInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondDomesticApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
