head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPFirstAdddepositInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ꐅ���Ǐ؏��(WEB3TPFirstAdddepositInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 �И��� (���u) �V�K�쐬 �d�l�ύX���f��307,315
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
 * (��ꐅ���Ǐ؏��)<BR>
 * (��ꐅ���Ǐ؏��)<BR>
 * <BR>
 * ��ꐅ���Ǐ؏����i�[����N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3TPFirstAdddepositInfo implements WEB3TPAdddepositInfo
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPFirstAdddepositInfo.class);

    /**
     * (�o�ߓ���)<BR>
     * (�o�ߓ���)<BR>
     */
    public int firstDepositPassDay = 0;

    /**
     * (�L���o�ߓ���)<BR>
     * (�L���o�ߓ���)<BR>
     */
    public int firstDepositPassDayValid = 0;

    /**
     * (������)<BR>
     * (������)<BR>
     */
    public Date firstDepositOccurredDate = null;

    /**
     * (�ۏ؋���)<BR>
     * (�ۏ؋���)<BR>
     */
    public double firstMarginDepositRate = 0;

    /**
     * (�ۏ؋��ێ���)<BR>
     * (�ۏ؋��ێ���)<BR>
     */
    public double firstDepositRate = 0;

    /**
     * (�Ǐ؋��z)<BR>
     * (�Ǐ؋��z)<BR>
     */
    public double firstDepositAmount = 0;

    /**
     * (�Ǐ،��ϕK�v�z)<BR>
     * (�Ǐ،��ϕK�v�z)<BR>
     */
    public double firstSettlement = 0;

    /**
     * (�ۏ؋�����)<BR>
     * (�ۏ؋�����)<BR>
     */
    public double firstMarginDepositInDe = 0;

    /**
     * (�ۏ؋�����(�������z))<BR>
     * (�ۏ؋�����(�������z))<BR>
     */
    public double firstMarginDepositInDeExpect = 0;

    /**
     * (���ύό���)<BR>
     * (���ύό���)<BR>
     */
    public double firstSettledContract = 0;

    /**
     * (���������z)<BR>
     * (���������z)<BR>
     */
    public double firstUncancelAmt = 0;

    /**
     * (���������ϕK�v�z)<BR>
     * (���������ϕK�v�z)<BR>
     */
    public double firstUncancelSettleRequiredAmt = 0;

    /**
     * @@roseuid 48F585630261
     */
    public WEB3TPFirstAdddepositInfo()
    {

    }

    /**
     * (create��ꐅ���Ǐ؏��)<BR>
     * (static���\�b�h)(create��ꐅ���Ǐ؏��)<BR>
     * <BR>
     * ��ꐅ���Ǐ؏��C���X�^���X���쐬���� <BR>
     * <BR>
     * ���V�[�P���X�}�u(��ꐅ���Ǐ؏��)create��ꐅ���Ǐ؏��v�Q��<BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F((��ꐅ���Ǐ؏��)create��ꐅ���Ǐ؏��) <BR>
     * ��̈ʒu�F((*)����t���[get�M�p��������t���O()�̖߂�l�@@==�@@"0"(�����ڋq) �̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_paymentRequisitionManagement - (���������Ǘ�)<BR>
     * (���������Ǘ�)<BR>
     * @@return WEB3TPFirstAdddepositInfo
     * @@roseuid 48E183900232
     * @@throws WEB3BaseException
     */
    public static WEB3TPFirstAdddepositInfo createFirstAdddepositInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createFirstAdddepositInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPFirstAdddepositInfo." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //get�M�p��������t���O
        String l_strMarginEquityFlag = l_paymentRequisitionManagement.getMarginEquityJudgeFlag();

        //��ꐅ���Ǐ؏��𐶐�����B
        WEB3TPFirstAdddepositInfo l_firstAdddepositInfo = null;

        //(*)����t���|
        //get�M�p��������t���O()�̖߂�l�@@==�@@"0"(�����ڋq) �̏ꍇ
        if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(l_strMarginEquityFlag))
        {
            log.exiting(STR_METHOD_NAME);
            return l_firstAdddepositInfo;
        }

        //is��ꐅ���Ǐؔ���
        boolean l_blnIsFirstAdddeposit = l_paymentRequisitionManagement.isFirstAdddeposit();

        //(*)����t���|
        //is��ꐅ���Ǐؔ���( )�̖߂�l�@@==�@@TRUE �̏ꍇ
        if (l_blnIsFirstAdddeposit)
        {
            l_firstAdddepositInfo = new WEB3TPFirstAdddepositInfo();
            //��ꐅ���Ǐ؏��̃v���p�e�B���ȉ��̂悤�ɃZ�b�g����B
            //�E�o�ߓ����@@=�@@get��ꐅ���Ǐ،o�ߓ���()
            l_firstAdddepositInfo.firstDepositPassDay =
                l_paymentRequisitionManagement.getFirstAdddepositPassDay();
            //�E�L���o�ߓ����@@=�@@get��ꐅ���ǏؗL���o�ߓ���()
            l_firstAdddepositInfo.firstDepositPassDayValid =
                l_paymentRequisitionManagement.getFirstAdddepositPassDayValid();
            //�E�������@@=�@@get��ꐅ���Ǐؔ�����()
            l_firstAdddepositInfo.firstDepositOccurredDate =
                l_paymentRequisitionManagement.getFirstAdddepositOccurredDate();
            //�E�ۏ؋����@@=�@@get��ꐅ���Ǐؕۏ؋���()
            l_firstAdddepositInfo.firstMarginDepositRate =
                l_paymentRequisitionManagement.getFirstAdddepositMarginDepositRate();
            //�E�ۏ؋��ێ����@@=�@@get��ꐅ���Ǐؕۏ؋��ێ���()
            l_firstAdddepositInfo.firstDepositRate =
                l_paymentRequisitionManagement.getFirstAdddepositDepositRate();
            //�E�Ǐ؋��z�@@=�@@get��ꐅ���Ǐ؋��z()
            l_firstAdddepositInfo.firstDepositAmount =
                l_paymentRequisitionManagement.getFirstAdddepositAmount();
            //�E�Ǐ،��ϕK�v�z�@@=�@@get��ꐅ���Ǐ،��ϕK�v�z()
            l_firstAdddepositInfo.firstSettlement =
                l_paymentRequisitionManagement.getFirstAdddepositSettlement();
            //�E�ۏ؋������@@=�@@get��ꐅ���Ǐؕۏ؋�����()
            l_firstAdddepositInfo.firstMarginDepositInDe =
                l_paymentRequisitionManagement.getFirstAdddepositMarginDepositInDe();
            //�E�ۏ؋�����(�������z)�@@=�@@get��ꐅ���Ǐؕۏ؋�����(�������z)()
            l_firstAdddepositInfo.firstMarginDepositInDeExpect =
                l_paymentRequisitionManagement.getFirstAdddepositMarginDepositInDeExpect();
            //�E���ύό��ʁ@@=�@@get��ꐅ���Ǐ،��ύό���()
            l_firstAdddepositInfo.firstSettledContract =
                l_paymentRequisitionManagement.getFirstAdddepositSettledContract();
            //�E���������z�@@=�@@get��ꐅ���Ǐؖ��������z()
            l_firstAdddepositInfo.firstUncancelAmt =
                l_paymentRequisitionManagement.getFirstAdddepositUncancelAmt();
            //�E���������ϕK�v�z�@@=�@@get��ꐅ���Ǐؖ��������ϕK�v�z()
            l_firstAdddepositInfo.firstUncancelSettleRequiredAmt =
                l_paymentRequisitionManagement.getFirstAdddepositUncancelSettleRequiredAmt();
        }

        log.exiting(STR_METHOD_NAME);
        return l_firstAdddepositInfo;
    }
}
@
