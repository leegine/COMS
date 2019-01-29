head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionDetailResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq�����ڍ׃��X�|���X(WEB3AdminTPPaymentRequisitionDetailResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
Revision History : 2008/10/15 �I�O (���u) ���f��No.035
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���������ڋq�����ڍ׃��X�|���X)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionDetailResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_detail";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081730L;

    /**
     * (�v�Z��)<BR>
     */
    public Date calcDate;

    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;

    /**
     * (�ڋq��)<BR>
     */
    public String accountName;

    /**
     * (���҃R�[�h)<BR>
     */
    public String traderCode = null;

    /**
     * (����)<BR>
     */
    public String attribute;

    /**
     * (�����~�敪)<BR>
     */
    public String tradeStopDiv;

    /**
     * (�Ǐؖ������敪)<BR>
     */
    public String additionalDepositStop;

    /**
     * (�M�p�V�K���]�͋敪)<BR>
     */
    public String marginOpenPositionStop;

    /**
     * (�敨OP�V�K���]�͋敪)<BR>
     */
    public String ifoOpenPositionStop;

    /**
     * (�o���]�͋敪)<BR>
     */
    public String paymentStop;

    /**
     * (���̑����i���t�]�͋敪)<BR>
     */
    public String otherTradingStop;

    /**
     * (���֋�)<BR>
     */
    public String debitAmount = "0";

    /**
     * (���ʗ��֋�)<BR>
     */
    public String specialDebitAmount = "0";

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     */
    public boolean autoTransferAfterJudgeFlag;

    /**
     * (�s����������)<BR>
     */
    public String shortfallGenerationStateDiv = "0";

    /**
     * (�Ǐؔ�����)<BR>
     */
    public String additionalGenerationStateDiv = "0";

    /**
     * (�s�����������)<BR>
     */
    public WEB3AdminTPShortfallGenerationInfo shortfallGenerationInfo;

    /**
     * (��ꐅ���Ǐ؏��)<BR>
     */
    public WEB3AdminTPFirstAdditionalInfo firstAdditionalInfo = null;

    /**
     * (��񐅏��Ǐ؏��)<BR>
     */
    public WEB3AdminTPSecondAdditionalInfo secondAdditionalInfo = null;

    /**
     * @@roseuid 48EC70340047
     */
    public WEB3AdminTPPaymentRequisitionDetailResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminTPPaymentRequisitionDetailResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}@
