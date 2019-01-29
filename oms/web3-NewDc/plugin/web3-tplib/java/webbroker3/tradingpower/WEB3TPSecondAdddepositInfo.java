head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.55.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecondAdddepositInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��񐅏��Ǐ؏��(WEB3TPSecondAdddepositInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 �И��� (���u) �d�l�ύX���f��307,315
Revision History : 2008/11/11 �O���~��Y (SCS) �d�l�ύX���f��365
*/
package webbroker3.tradingpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (��񐅏��Ǐ؏��)<BR>
 * (��񐅏��Ǐ؏��)<BR>
 * <BR>
 * ��񐅏��Ǐ؏����i�[����N���X<BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3TPSecondAdddepositInfo implements WEB3TPAdddepositInfo
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPSecondAdddepositInfo.class);

    /**
     * (����(����2))<BR>
     * (����(����2))<BR>
     */
    public Date secondCloseDate2 = null;

    /**
     * (����(����1))<BR>
     * (����(����1))<BR>
     */
    public Date secondCloseDate1 = null;

    /**
     * (����(��������))<BR>
     * (����(��������))<BR>
     */
    public Date secondCloseDateExpect = null;

    /**
     * (������(����2))<BR>
     * (������(����2))<BR>
     */
    public Date secondDepositOccurredDate2 = null;

    /**
     * (������(����1))<BR>
     * (������(����1))<BR>
     */
    public Date secondDepositOccurredDate1 = null;

    /**
     * (������(��������))<BR>
     * (������(��������))<BR>
     */
    public Date secondDepositOccurredDateExpect = null;

    /**
     * (�ۏ؋��ێ���)<BR>
     * (�ۏ؋��ێ���)<BR>
     */
    public double secondDepositRate = 0;

    /**
     * (�ۏ؋��߂��ێ���)<BR>
     * (�ۏ؋��߂��ێ���)<BR>
     */
    public double secondDepositBackRate = 0;

    /**
     * (�ۏ؋���(����2))<BR>
     * (�ۏ؋���(����2))<BR>
     */
    public double secondMarginDepositRate2 = 0;

    /**
     * (�ۏ؋���(����1))<BR>
     * (�ۏ؋���(����1))<BR>
     */
    public double secondMarginDepositRate1 = 0;

    /**
     * (�ۏ؋���(��������))<BR>
     * (�ۏ؋���(��������))<BR>
     */
    public double secondMarginDepositRateExpect = 0;

    /**
     * (�Ǐ؋��z(������))<BR>
     * (�Ǐ؋��z(������))<BR>
     */
    public double secondDepositNonPay = 0;

    /**
     * (�Ǐ؋��z(����2))<BR>
     * (�Ǐ؋��z(����2))<BR>
     */
    public double secondDeposit2 = 0;

    /**
     * (�Ǐ؋��z(����1))<BR>
     * (�Ǐ؋��z(����1))<BR>
     */
    public double secondDeposit1 = 0;

    /**
     * (�Ǐ،��ϕK�v�z(������))<BR>
     * (�Ǐ،��ϕK�v�z(������))<BR>
     */
    public double secondSettlementNonPay = 0;

    /**
     * (�Ǐ،��ϕK�v�z(����2))<BR>
     * (�Ǐ،��ϕK�v�z(����2))<BR>
     */
    public double secondSettlement2 = 0;

    /**
     * (�Ǐ،��ϕK�v�z(����1))<BR>
     * (�Ǐ،��ϕK�v�z(����1))<BR>
     */
    public double secondSettlement1 = 0;

    /**
     * (�ۏ؋�����)<BR>
     * (�ۏ؋�����)<BR>
     */
    public double secondMarginDepositInDe = 0;

    /**
     * (�ۏ؋�����(�������z))<BR>
     * (�ۏ؋�����(�������z))<BR>
     */
    public double secondMarginDepositInDeExpect = 0;

    /**
     * (���ύό���)<BR>
     * (���ύό���)<BR>
     */
    public double secondSettledContract = 0;

    /**
     * (���������z(������))<BR>
     * (���������z(������))<BR>
     */
    public double secondUncancelAmtNonPay = 0;

    /**
     * (���������z(����2))<BR>
     * (���������z(����2))<BR>
     */
    public double secondUncancelAmt2 = 0;

    /**
     * (���������z(����1))<BR>
     * (���������z(����1))<BR>
     */
    public double secondUncancelAmt1 = 0;

    /**
     * (���������z(��������))<BR>
     * (���������z(��������))<BR>
     */
    public double secondUncancelAmtExpect = 0;

    /**
     * (���������ϕK�v�z(������))<BR>
     * (���������ϕK�v�z(������))<BR>
     */
    public double secondUncancelSettleRequiredAmtNonPay = 0;

    /**
     * (���������ϕK�v�z(����2))<BR>
     * (���������ϕK�v�z(����2))<BR>
     */
    public double secondUncancelSettleRequiredAmt2 = 0;

    /**
     * (���������ϕK�v�z(����1))<BR>
     * (���������ϕK�v�z(����1))<BR>
     */
    public double secondUncancelSettleRequiredAmt1 = 0;

    /**
     * (���������ϕK�v�z(��������))<BR>
     * (���������ϕK�v�z(��������))<BR>
     */
    public double secondUncancelSettleRequiredAmtExpect = 0;

    /**
     * @@roseuid 48F585630147
     */
    public WEB3TPSecondAdddepositInfo()
    {

    }

    /**
     * (create��񐅏��Ǐ؏��)<BR>
     * (static���\�b�h)(create��񐅏��Ǐ؏��)<BR>
     * <BR>
     * ��񐅏��Ǐ؏��C���X�^���X���쐬���� <BR>
     * <BR>
     * ���V�[�P���X�}�u(��񐅏��Ǐ؏��)create��񐅏��Ǐ؏��v�Q��<BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F((��񐅏��Ǐ؏��)create��񐅏��Ǐ؏��) <BR>
     * ��̈ʒu�F((*)����t���[get�M�p��������t���O()�̖߂�l�@@==�@@"0"(�����ڋq) �̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_paymentRequisitionManagement - (���������Ǘ�)<BR>
     * (���������Ǘ�)<BR>
     * @@return WEB3TPSecondAdddepositInfo
     * @@roseuid 48E183CF015A
     * @@throws WEB3BaseException
     */
    public static WEB3TPSecondAdddepositInfo createSecondAdddepositInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSecondAdddepositInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPSecondAdddepositInfo." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //get�M�p��������t���O
        String l_strMarginEquityFlag = l_paymentRequisitionManagement.getMarginEquityJudgeFlag();

        //��񐅏��Ǐ؏��𐶐�����B
        WEB3TPSecondAdddepositInfo l_secondAdddepositInfo = null;

        //(*)����t���|
        //get�M�p��������t���O()�̖߂�l�@@==�@@"0"(�����ڋq) �̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(l_strMarginEquityFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_secondAdddepositInfo;
        }

        //is��񐅏��Ǐؔ���
        boolean l_blnSecondAdddeposit = l_paymentRequisitionManagement.isSecondAdddeposit();

        //(*)����t���|
        //is��񐅏��Ǐؔ���( )�̖߂�l�@@==�@@TRUE �̏ꍇ
        if (l_blnSecondAdddeposit)
        {
            l_secondAdddepositInfo = new WEB3TPSecondAdddepositInfo();
            //��񐅏��Ǐ؏��̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
            //�@@�E����(����2)�@@=�@@get��񐅏��Ǐ؊���(����2)()
            l_secondAdddepositInfo.secondCloseDate2 =
                l_paymentRequisitionManagement.getSecondAdddepositCloseDate2();
            //�@@�E����(����1)�@@=�@@get��񐅏��Ǐ؊���(����1)()
            l_secondAdddepositInfo.secondCloseDate1 =
                l_paymentRequisitionManagement.getSecondAdddepositCloseDate1();
            //�@@�E������(����2)�@@=�@@get��񐅏��Ǐؔ�����(����2)()
            l_secondAdddepositInfo.secondDepositOccurredDate2 =
                l_paymentRequisitionManagement.getSecondAdddepositDepositOccurredDate2();
            //�@@�E������(����1)�@@=�@@get��񐅏��Ǐؔ�����(����1)()
            l_secondAdddepositInfo.secondDepositOccurredDate1 =
                l_paymentRequisitionManagement.getSecondAdddepositDepositOccurredDate1();
            //�@@�E�ۏ؋��ێ����@@=�@@get��񐅏��Ǐؕۏ؋��ێ���()
            l_secondAdddepositInfo.secondDepositRate =
                l_paymentRequisitionManagement.getSecondAdddepositDepositRate();
            //�@@�E�ۏ؋��߂��ێ����@@=�@@get��񐅏��Ǐؕۏ؋��߂��ێ���()
            l_secondAdddepositInfo.secondDepositBackRate =
                l_paymentRequisitionManagement.getSecondAdddepositDepositBackRate();
            //�@@�E�ۏ؋���(����2)�@@=�@@get��񐅏��Ǐؕۏ؋���(����2)()
            l_secondAdddepositInfo.secondMarginDepositRate2 =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositRate2();
            //�@@�E�ۏ؋���(����1)�@@=�@@get��񐅏��Ǐؕۏ؋���(����1)()
            l_secondAdddepositInfo.secondMarginDepositRate1 =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositRate1();
            //�@@�E���z(������)�@@=�@@get��񐅏��Ǐ؋��z(������)()
            l_secondAdddepositInfo.secondDepositNonPay =
                l_paymentRequisitionManagement.getSecondAdddepositDepositNonPay();
            //�@@�E���z(����2)�@@=�@@get��񐅏��Ǐ؋��z(����2)()
            l_secondAdddepositInfo.secondDeposit2 =
                l_paymentRequisitionManagement.getSecondAdddepositDeposit2();
            //�@@�E���z(����1)�@@=�@@get��񐅏��Ǐ؋��z(����1)()
            l_secondAdddepositInfo.secondDeposit1 =
                l_paymentRequisitionManagement.getSecondAdddepositDeposit1();
            //�@@�E���ϕK�v�z(������)�@@=�@@get��񐅏��Ǐ،��ϕK�v�z(������)()
            l_secondAdddepositInfo.secondSettlementNonPay =
                l_paymentRequisitionManagement.getSecondAdddepositSettlementNonPay();
            //�@@�E���ϕK�v�z(����2)�@@=�@@get��񐅏��Ǐ،��ϕK�v�z(����2)()
            l_secondAdddepositInfo.secondSettlement2 =
                l_paymentRequisitionManagement.getSecondAdddepositSettlement2();
            //�@@�E���ϕK�v�z(����1)�@@=�@@get��񐅏��Ǐ،��ϕK�v�z(����1)()
            l_secondAdddepositInfo.secondSettlement1 =
                l_paymentRequisitionManagement.getSecondAdddepositSettlement1();
            //�@@�E�ۏ؋������@@=�@@get��񐅏��Ǐؕۏ؋�����()
            l_secondAdddepositInfo.secondMarginDepositInDe =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositInDe();
            //�@@�E���ύό��ʁ@@=�@@get��񐅏��Ǐ،��ύό���()
            l_secondAdddepositInfo.secondSettledContract =
                l_paymentRequisitionManagement.getSecondAdddepositSettledContract();

            //��񐅏��Ǐؖ��������I�u�W�F�N�g�𐶐�����B
            WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
                l_paymentRequisitionManagement.createSecondAdddepositNotClearInfo();

            //�@@�E���������z(������)�@@=�@@��񐅏��Ǐؖ��������.�Ǐ؋��z(������)
            l_secondAdddepositInfo.secondUncancelAmtNonPay =
                l_secondAdddepositNotClearInfo.secondDepositNonPay;
            //�@@�E���������z(����2)�@@=�@@��񐅏��Ǐؖ��������.�Ǐ؋��z(����2)
            l_secondAdddepositInfo.secondUncancelAmt2 =
                l_secondAdddepositNotClearInfo.secondDeposit2;
            //�@@�E���������z(����1)�@@=�@@��񐅏��Ǐؖ��������.�Ǐ؋��z(����1)
            l_secondAdddepositInfo.secondUncancelAmt1 =
                l_secondAdddepositNotClearInfo.secondDeposit1;
            //�@@�E���������ϕK�v�z(������)�@@=�@@��񐅏��Ǐؖ��������.���ϕK�v�z(������)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay =
                l_secondAdddepositNotClearInfo.secondSettlementNonPay;
            //�@@�E���������ϕK�v�z(����2)�@@=�@@��񐅏��Ǐؖ��������.���ϕK�v�z(����2)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2 =
                l_secondAdddepositNotClearInfo.secondSettlement2;
            //�@@�E���������ϕK�v�z(����1)�@@=�@@��񐅏��Ǐؖ��������.���ϕK�v�z(����1)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1 =
                l_secondAdddepositNotClearInfo.secondSettlement1;
            //�@@�E����(��������)�@@=�@@get��񐅏��Ǐ؊���(�������z)()
            l_secondAdddepositInfo.secondCloseDateExpect =
                l_paymentRequisitionManagement.getSecondAdddepositCloseDateExpect();
            //�@@�E������(��������)�@@=�@@get��񐅏��Ǐؔ�����(�������z)()
            l_secondAdddepositInfo.secondDepositOccurredDateExpect =
                l_paymentRequisitionManagement.getSecondAdddepositDepositOccurredDateExpect();
            //�@@�E�ۏ؋���(��������)�@@=�@@get��񐅏��Ǐؕۏ؋���(�������z)()
            l_secondAdddepositInfo.secondMarginDepositRateExpect =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositRateExpect();
            //�@@�E�ۏ؋�����(�������z)�@@=�@@get��񐅏��Ǐؕۏ؋�����(�������z)()
            l_secondAdddepositInfo.secondMarginDepositInDeExpect =
                l_paymentRequisitionManagement.getSecondAdddepositMarginDepositInDeExpect();
            //�@@�E���������z(��������)�@@=�@@��񐅏��Ǐؖ��������.�Ǐ؋��z(�������z)
            l_secondAdddepositInfo.secondUncancelAmtExpect =
                l_secondAdddepositNotClearInfo.secondDepositExpect;
            //�@@�E���������ϕK�v�z(��������)�@@=�@@��񐅏��Ǐؖ��������.���ϕK�v�z(�������z)
            l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect =
                l_secondAdddepositNotClearInfo.secondSettlementExpect;
        }

        log.exiting(STR_METHOD_NAME);
        return l_secondAdddepositInfo;
    }
}
@
