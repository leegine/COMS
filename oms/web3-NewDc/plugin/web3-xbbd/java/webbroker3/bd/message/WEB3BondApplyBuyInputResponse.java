head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.43.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t���̓��X�|���X(WEB3BondApplyBuyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
                       : 2006/09/29 �����F (���u) ���f�� 098
*/

package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (������/���t���̓��X�|���X)<BR>
 * ������/���t���̓��X�|���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_applyBuyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609051106L;

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
     * (��ʃR�[�h)<BR>
     * ��ʃR�[�h<BR>
     */
    public String bondCategCode;

    /**
     * (S&P)<BR>
     * S&P<BR>
     */
    public String sAndP;

    /**
     * (Moody's)<BR>
     * Moody's<BR>
     */
    public String moodys;

    /**
     * (����)<BR>
     * ����<BR>
     */
    public String coupon;

    /**
     * (�N�ԗ�����)<BR>
     * �N�ԗ�����<BR>
     */
    public String yearlyInterestPayments;

    /**
     * (������1)<BR>
     * ������1<BR>
     */
    public String interestPaymentDay1;

    /**
     * (������2)<BR>
     * ������2<BR>
     */
    public String interestPaymentDay2;

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;

    /**
     * (�\���P��)<BR>
     * �\���P��<BR>
     */
    public String tradeUnit;

    /**
     * (�Œ�\������)<BR>
     * �Œ�\������<BR>
     */
    public String minOrderQuantity;

    /**
     * (�ō��\������)<BR>
     * �ō��\������<BR>
     */
    public String maxOrderQuantity;

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
     * (���t�P��)<BR>
     * ���t�P��<BR>
     */
    public String buyPrice;

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
     * (���t��בփ��[�g)<BR>
     * ���t��בփ��[�g<BR>
     */
    public String buyBaseFxRate;

    /**
     * (���ϋ敪�ꗗ)<BR>
     * ���ϋ敪�ꗗ<BR>
     * <BR>
     * 1�F�~��<BR>
     * 2�F�O��<BR>
     */
    public String[] settleDivList;

    /**
     * (�d�����̈בփ��[�g)<BR>
     * �d�����̈בփ��[�g<BR>
     */
    public String fxRateAtStock;

    /**
     * (������/���t���̓��X�|���X)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 44C59B670129
     */
    public WEB3BondApplyBuyInputResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BondApplyBuyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
