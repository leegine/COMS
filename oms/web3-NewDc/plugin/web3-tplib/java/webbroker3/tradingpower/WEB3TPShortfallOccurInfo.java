head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPShortfallOccurInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s�����������(WEB3TPShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 �И��� (���u) �d�l�ύX���f��307,315,317
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�s�����������)<BR>
 * (�s�����������)<BR>
 * <BR>
 * �s�������������i�[����N���X<BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3TPShortfallOccurInfo
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPShortfallOccurInfo.class);

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * false�F�ۏ؋������U�֑O<BR>
     * true �F�ۏ؋������U�֌�<BR>
     */
    public boolean depositAutoTransferDivFlag = false;

    /**
     * (���֋�)<BR>
     * (���֋�)<BR>
     */
    public double debitAmount = 0;

    /**
     * (���ʗ��֋�)<BR>
     * (���ʗ��֋�)<BR>
     */
    public double specialDebitAmount = 0;

    /**
     * (����(T+0))<BR>
     * (����(T+0))<BR>
     */
    public Date closeDate0 = null;

    /**
     * (����(T+1))<BR>
     * (����(T+1))<BR>
     */
    public Date closeDate1 = null;

    /**
     * (����(T+2))<BR>
     * (����(T+2))<BR>
     */
    public Date closeDate2 = null;

    /**
     * (����(T+3))<BR>
     * (����(T+3))<BR>
     */
    public Date closeDate3 = null;

    /**
     * (����(T+4))<BR>
     * (����(T+4))<BR>
     */
    public Date closeDate4 = null;

    /**
     * (����(T+5))<BR>
     * (����(T+5))<BR>
     */
    public Date closeDate5 = null;

    /**
     * (�K�v�����z(T+0))<BR>
     * (�K�v�����z(T+0))<BR>
     */
    public double requiredPayAmt0 = 0;

    /**
     * (�K�v�����z(T+1))<BR>
     * (�K�v�����z(T+1))<BR>
     */
    public double requiredPayAmt1 = 0;

    /**
     * (�K�v�����z(T+2))<BR>
     * (�K�v�����z(T+2))<BR>
     */
    public double requiredPayAmt2 = 0;

    /**
     * (�K�v�����z(T+3))<BR>
     * (�K�v�����z(T+3))<BR>
     */
    public double requiredPayAmt3 = 0;

    /**
     * (�K�v�����z(T+4))<BR>
     * (�K�v�����z(T+4))<BR>
     */
    public double requiredPayAmt4 = 0;

    /**
     * (�K�v�����z(T+5))<BR>
     * (�K�v�����z(T+5))<BR>
     */
    public double requiredPayAmt5 = 0;

    /**
     * (���Z�z(T+0))<BR>
     * (���Z�z(T+0))<BR>
     */
    public double adjustedAmt0 = 0;

    /**
     * (���Z�z(T+1))<BR>
     * (���Z�z(T+1))<BR>
     */
    public double adjustedAmt1 = 0;

    /**
     * (���v��S����(T+0))<BR>
     * (���v��S����(T+0))<BR>
     */
    public double dayTradeRestraint0 = 0;

    /**
     * (���v��S����(T+1))<BR>
     * (���v��S����(T+1))<BR>
     */
    public double dayTradeRestraint1 = 0;

    /**
     * (�ۏ؋�����̐U�֊z(T+0))<BR>
     * (�ۏ؋�����̐U�֊z(T+0))<BR>
     */
    public double transferFromMarginDeposit0 = 0;

    /**
     * (�ۏ؋�����̐U�֊z(T+1))<BR>
     * (�ۏ؋�����̐U�֊z(T+1))<BR>
     */
    public double transferFromMarginDeposit1 = 0;

    /**
     * @@roseuid 48F585630389
     */
    public WEB3TPShortfallOccurInfo()
    {

    }

    /**
     * (create�s�����������)<BR>
     * (static���\�b�h)(create�s�����������)<BR>
     * <BR>
     * �s�����������C���X�^���X���쐬���� <BR>
     * <BR>
     * ���V�[�P���X�}�u(�s�����������)create�s�����������v�Q��<BR>
     * @@param l_paymentRequisitionManagement - (���������Ǘ�)<BR>
     * (���������Ǘ�)<BR>
     * @@return WEB3TPShortfallGenerationInfo
     * @@roseuid 48E17E6D03E7
     * @@throws WEB3BaseException
     */
    public static WEB3TPShortfallOccurInfo createShortfallGenerationInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createShortfallGenerationInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPShortfallGenerationInfo." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�s�����������𐶐�����B
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo = new WEB3TPShortfallOccurInfo();

        //�ۏ؋������U�֌㔻��t���O = ����.���������Ǘ�.is�ۏ؋������U�֌㔻��t���O()
        l_shortfallGenerationInfo.depositAutoTransferDivFlag =
            l_paymentRequisitionManagement.isDepositAutoTransferDivFlag();
        //���֋� = get���֋�()
        l_shortfallGenerationInfo.debitAmount =
            l_paymentRequisitionManagement.getDebitAmount();
        //���ʗ��֋� = get���ʗ��֋�()
        l_shortfallGenerationInfo.specialDebitAmount =
            l_paymentRequisitionManagement.getSpecialDebitAmount();
        //�w���(T+0) = get�w���(T+0)
        l_shortfallGenerationInfo.closeDate0 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_0);
        //�w���(T+1) = get�w���(T+1)
        l_shortfallGenerationInfo.closeDate1 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_1);
        //�w���(T+2) = get�w���(T+2)
        l_shortfallGenerationInfo.closeDate2 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_2);
        //�w���(T+3) = get�w���(T+3)
        l_shortfallGenerationInfo.closeDate3 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_3);
        //�w���(T+4) = get�w���(T+4)
        l_shortfallGenerationInfo.closeDate4 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_4);
        //�w���(T+5) = get�w���(T+5)
        l_shortfallGenerationInfo.closeDate5 =
            l_paymentRequisitionManagement.getDate(WEB3TPSpecifiedPointDef.T_5);
        //�K�v�����z(T+0) = get�a����s���z(T+0)
        l_shortfallGenerationInfo.requiredPayAmt0 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_0);
        //�K�v�����z(T+1) = get�a����s���z(T+1)
        l_shortfallGenerationInfo.requiredPayAmt1 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_1);
        //�K�v�����z(T+2) = get�a����s���z(T+2)
        l_shortfallGenerationInfo.requiredPayAmt2 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_2);
        //�K�v�����z(T+3) = get�a����s���z(T+3)
        l_shortfallGenerationInfo.requiredPayAmt3 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_3);
        //�K�v�����z(T+4) = get�a����s���z(T+4)
        l_shortfallGenerationInfo.requiredPayAmt4 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_4);
        //�K�v�����z(T+5) = get�a����s���z(T+5)
        l_shortfallGenerationInfo.requiredPayAmt5 =
            l_paymentRequisitionManagement.getLackAccountBalance(WEB3TPSpecifiedPointDef.T_5);
        //���Z�z(T+0) = calc���Z�z(T+0)
        l_shortfallGenerationInfo.adjustedAmt0 =
            l_paymentRequisitionManagement.calcAdjustedAmt(WEB3TPSpecifiedPointDef.T_0);
        //���Z�z(T+1) = calc���Z�z(T+1)
        l_shortfallGenerationInfo.adjustedAmt1 =
            l_paymentRequisitionManagement.calcAdjustedAmt(WEB3TPSpecifiedPointDef.T_1);
        //���v��S����(T+0) = calc���v��S����(T+0)
        l_shortfallGenerationInfo.dayTradeRestraint0 =
            l_paymentRequisitionManagement.calcDayTradeRestraint(WEB3TPSpecifiedPointDef.T_0);
        //���v��S����(T+1) = calc���v��S����(T+1)
        l_shortfallGenerationInfo.dayTradeRestraint1 =
            l_paymentRequisitionManagement.calcDayTradeRestraint(WEB3TPSpecifiedPointDef.T_1);
        //�ۏ؋�����̐U�֊z(T+0) = get�ۏ؋�����̐U�֊z(T+0)
        l_shortfallGenerationInfo.transferFromMarginDeposit0 =
            l_paymentRequisitionManagement.getTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_0);
        //�ۏ؋�����̐U�֊z(T+1) = get�ۏ؋�����̐U�֊z(T+1)
        l_shortfallGenerationInfo.transferFromMarginDeposit1 =
            l_paymentRequisitionManagement.getTransferFromMarginDeposit(WEB3TPSpecifiedPointDef.T_1);

        log.exiting(STR_METHOD_NAME);
        return l_shortfallGenerationInfo;
    }
}
@
