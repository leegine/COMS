head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationDetailServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�XImpl(WEB3PvInfoPrivateInformationDetailServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/22 ������(���u) �쐬
Revesion History : 2005/10/07 杊��](���u) �d�l�ύXNo.058
Revesion History : 2006/5/22 ������(���u) �d�l�ύXNo.063�C��
Revesion History : 2006/09/12 �����F(���u) �d�l�ύX���f��070,071
Revesion History : 2006/09/27 �����F(���u) �d�l�ύX���f��072
Revesion History : 2007/02/26 �֔�(���u) �d�l�ύX���f��073
Revesion History : 2007/03/07 �֔�(���u) �d�l�ύX���f��074
Revesion History : 2007/03/16 �֔�(���u) �d�l�ύX���f��076
Revision History : 2008/10/07 �đo�g(���u) �d�l�ύX���f��108�A���f��112
*/
package webbroker3.pvinfo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.accountinfo.data.CommCampaignAccHistoryParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.SecurityShortageAccountParams;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.define.WEB3PvInfoTPBizDateCheckNumDef;
import webbroker3.pvinfo.message.WEB3PvInfoAdvanceUnit;
import webbroker3.pvinfo.message.WEB3PvInfoCashoutStopUnit;
import webbroker3.pvinfo.message.WEB3PvInfoCommissionCampaignUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteRequest;
import webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse;
import webbroker3.pvinfo.message.WEB3PvInfoFirstAdditionalInfo;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailRequest;
import webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse;
import webbroker3.pvinfo.message.WEB3PvInfoSecondAdditionalInfo;
import webbroker3.pvinfo.message.WEB3PvInfoSettleContractUnit;
import webbroker3.pvinfo.message.WEB3PvInfoShortfallGenerationInfo;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationDetailService;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPAdddepositInfo;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPSecondAdddepositInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�XImpl)<BR>
 * ��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�X�����N���X<BR>
 * @@author �����F(���u)
 */
public class WEB3PvInfoPrivateInformationDetailServiceImpl extends WEB3ClientRequestService implements WEB3PvInfoPrivateInformationDetailService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationDetailServiceImpl.class);

    /**
     * ��ײ�ްĲ�̫Ұ��ݏڍ׏������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̏������Ăѕ�����B<BR>
     * ����ײ�ްĲ�̫Ұ��ݏڍ׃��N�G�X�g�̏ꍇ<BR>
     * �@@�Eget�ڍ׉��()���\�b�h���R�[������B<BR>
     * <BR>
     * ���_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g�̏ꍇ<BR>
     * �@@�Edelete�_�C���N�g�w�胁�b�Z�[�W()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41479D0D00A8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if(l_request instanceof WEB3PvInfoPrivateInformationDetailRequest)
        {
            l_response = this.getDetailedScreen((WEB3PvInfoPrivateInformationDetailRequest)l_request);
        }
        else if(l_request instanceof WEB3PvInfoDirectMessageDeleteRequest)
        {
            l_response = this.deleteDirectMessage((WEB3PvInfoDirectMessageDeleteRequest)l_request);
        }
        else
        {
            String l_strErrorInfo =
                "�p�����[�^�̗ތ^���s���A�Y������WEB3PvInfoPrivateInformationDetailRequest," +
                "WEB3PvInfoDirectMessageDeleteRequest�ތ^�B";
            log.error(l_strErrorInfo);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�ڍ׉��)<BR>
     * �ڍ׉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�X)get�ڍ׉�ʁv�Q��<BR>
     * ========================================================<BR>
     * get�\�����eParams(�\�����eID:long)<BR>
     * <BR>
     * �߂�l��null�̏ꍇ�́A<BR>
     * �u�\�����b�Z�[�W�����݂��܂���v<BR>
     * �̗�O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01039<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ��ײ�ްĲ�̫Ұ��ݏڍ׃��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoPrivateInformationDetailResponse
     * @@roseuid 41479EE201A2
     */
    protected WEB3PvInfoPrivateInformationDetailResponse getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDetailedScreen(WEB3PvInfoPrivateInformationDetailRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1.validate()
        l_request.validate();
        log.debug("validate()�����s���܂�");

        //1.2.get�⏕����(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        log.debug("get�⏕����(SubAccountTypeEnum)�����s���܂�");

        //1.3.getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        log.debug("getMainAccount()�����s���܂�");

        //1.4.validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        log.debug("validate������t�\()�����s���܂�");

        //1.5.get�\�����eParams(long)
        long l_lngDisplayContentsId = Long.parseLong(l_request.displayContentsId);
        log.debug("�\�����eID: " + l_lngDisplayContentsId);
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        DisplayContentsParams l_displayContentsParams =
            l_dataManager.getDisplayContentsParams(l_lngDisplayContentsId);
        log.debug("get�\�����eParams(long)�����s���܂�");
        log.debug("�\�����eParams: " + l_displayContentsParams);

        if (l_displayContentsParams == null)
        {
            log.debug("�\�����eParams == null�̏ꍇ");
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01039.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01039,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //1.6.���b�Z�[�W (*)����t���[
        WEB3PvInfoAdvanceUnit[] l_AdvanceUnits = null;
        if (WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_displayContentsParams.getConditionNo())
                ||WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_displayContentsParams.getConditionNo())
                ||WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_displayContentsParams.getConditionNo()))
        {
            l_AdvanceUnits = this.createAdvanceUnitList((WEB3GentradeSubAccount)l_subAccount);
        }

        //1.7.2 ���ϊ����ԋ߂̌��ʏ�� ArrayList()
        List l_lisSettleContractUnits = new ArrayList();

        //1.7���b�Z�[�W (*)����t���[
        boolean l_blnIsBeforeAWeek = WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(l_displayContentsParams.condition_no);
        log.debug("l_blnIsBeforeAWeek =" + l_blnIsBeforeAWeek);
        if (l_blnIsBeforeAWeek)
        {
            //1.7.1 get���ϊ����ԋߌ��ʈꗗ(�ڋq, boolean)
            List l_lisSettleContracts = l_dataManager.getSettleContractList(l_mainAccount, true);
            log.debug("get���ϊ����ԋߌ��ʈꗗ(�ڋq, boolean)�����s���܂�");

            if (l_lisSettleContracts == null)
            {
                log.debug("���ϊ����ԋߌ��ʈꗗ == null�̏ꍇ");
                String l_strErrorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80006.getErrorMessage();
                log.error(STR_METHOD_NAME + l_strErrorInfo);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorInfo);
            }

            //1.7.3 get���ϊ����ԋߌ��ʈꗗ()�̖߂�l(=EqTypeContractParams)�̐���Loop����
            int l_intSettleContractCnt = l_lisSettleContracts.size();
            for (int i = 0; i < l_intSettleContractCnt; i++)
            {
                EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow)l_lisSettleContracts.get(i);

                //1.7.3.1 get��������Params(long)
                long l_lngProductId = l_eqtypeContractRow.getProductId();
                log.debug("����ID: " + l_lngProductId);
                EqtypeProductParams l_eqtypeParams = l_dataManager.getEqtypeProductParams(l_lngProductId);
                log.debug("get��������Params(long)�����s���܂�");

                //1.7.3.2 ���ϊ����ԋ߂̌��ʏ��()
                WEB3PvInfoSettleContractUnit l_pvInfoSettleContractUnit = new WEB3PvInfoSettleContractUnit();

                //1.7.3.3���b�Z�[�W �v���p�e�B�Z�b�g
                Timestamp l_tsCloseDate = l_eqtypeContractRow.getCloseDate();
                WEB3GentradeBizDate l_gentradeBizDate = new WEB3GentradeBizDate(l_tsCloseDate);
                Timestamp l_tsBeforeCloseDate = l_gentradeBizDate.roll(-1);

                l_pvInfoSettleContractUnit.beforeBizDate = l_tsBeforeCloseDate;
                l_pvInfoSettleContractUnit.productCode = l_eqtypeParams.getProductCode();
                l_pvInfoSettleContractUnit.productName = l_eqtypeParams.getStandardName();

                log.debug("������1�c�Ɠ��O: " + l_pvInfoSettleContractUnit.beforeBizDate);
                log.debug("�����R�[�h: " + l_pvInfoSettleContractUnit.productCode);
                log.debug("������: " + l_pvInfoSettleContractUnit.productName);

                //1.7.3.4 add(���ϊ����ԋ߂̌��ʏ�� : Object)
                l_lisSettleContractUnits.add(l_pvInfoSettleContractUnit);
                log.debug("add(���ϊ����ԋ߂̌��ʏ�� : Object)�����s���܂�");
            }
        }

        //1.8 toArray()
        WEB3PvInfoSettleContractUnit[] l_arrlisSettleContractUnits = new WEB3PvInfoSettleContractUnit[l_lisSettleContractUnits.size()];
        l_lisSettleContractUnits.toArray(l_arrlisSettleContractUnits);
        log.debug("toArray()�����s���܂�");
        
        //1.9(*)����t���[
        //�\��������"�ꕔ�o����~"�̏ꍇ
        //(�\�����eParams.�\�������ԍ� == "1049�F�@@�ꕔ�o����~")
        //�ȉ��̏��������{
        boolean l_blnPart = WEB3PvInfoConditionDef.PART_PAYMENT_STOP.equals(l_displayContentsParams.condition_no);
       
        
        WEB3PvInfoCashoutStopUnit l_pvInfoCashoutStopUnit = null;
        
        if (l_blnPart)
        {
            //1.9.1get�S�ەs���ڋq�f�[�^Params(�ڋq, String)
            SecurityShortageAccountParams l_securityShortageAccountParams = 
                l_dataManager.getSecurityShortageAccountParams(l_mainAccount,WEB3PaymentStopDivDef.PART);
           
            //1.9.2�o����~���( )
            l_pvInfoCashoutStopUnit = new WEB3PvInfoCashoutStopUnit();
            
            //1.9.3 �v���p�e�B�Z�b�g
            if (l_securityShortageAccountParams == null)
            {
                l_pvInfoCashoutStopUnit = null;
            }
            else 
            {
                l_pvInfoCashoutStopUnit.cashoutStopDate = l_securityShortageAccountParams.getProcDate();
                l_pvInfoCashoutStopUnit.cashoutStopAmount = l_securityShortageAccountParams.getPaymentStopAmount();
            }
        }

        //1.10 (*)����t���[
        // (*)����t���[
        // �\��������"�萔�������L�����y�[��"�̏ꍇ
        // (�\�����eParams.�\�������ԍ� == "1051�F�@@�萔�������L�����y�[��")
        // �ȉ��̏��������{����B
        boolean l_blnIsCommission = WEB3PvInfoConditionDef.COMMISSION_DISCOUNT_CAMPAIGN.equals(l_displayContentsParams.condition_no);

        List l_lisCommissionCampaignUnit = new ArrayList();
        if (l_blnIsCommission)
        {
            // get�����L�����y�[���ڋqParams(�ڋq)
            CommCampaignAccHistoryParams l_commCampaignAccHistoryParams[] = 
                l_dataManager.getCommCampaignAccHistoryParams(l_mainAccount);

            // �v���p�e�B�Z�b�g
            if (l_commCampaignAccHistoryParams != null)
            {
                for (int i = 0; i < l_commCampaignAccHistoryParams.length; i++)
                {
                    // �萔�������L�����y�[�����( )
                    WEB3PvInfoCommissionCampaignUnit l_pvInfoCommissionCampaignUnit = new WEB3PvInfoCommissionCampaignUnit();
                    // get���i�R�[�h(long)
                    String[] l_strCommProductCodes =
                        l_dataManager.getCommProductCodes(l_commCampaignAccHistoryParams[i].getCampaignId());

                    BigDecimal l_bdAccountChargeRatio = new BigDecimal("" + l_commCampaignAccHistoryParams[i].getAccountChargeRatio());
                    BigDecimal l_bdRate = new BigDecimal("" + 100D);
                    l_pvInfoCommissionCampaignUnit.campaignName = l_commCampaignAccHistoryParams[i].getCommCampaignName();
                    l_pvInfoCommissionCampaignUnit.commodityCodeList = l_strCommProductCodes;
                    l_pvInfoCommissionCampaignUnit.discountRate = WEB3StringTypeUtility.formatNumber(l_bdRate.subtract(l_bdAccountChargeRatio).doubleValue());
                    l_pvInfoCommissionCampaignUnit.applyStartDate = l_commCampaignAccHistoryParams[i].getAppliStartDate();
                    l_pvInfoCommissionCampaignUnit.applyEndDate = l_commCampaignAccHistoryParams[i].getAppliEndDate();
                    l_lisCommissionCampaignUnit.add(l_pvInfoCommissionCampaignUnit);
                }
            }
        }

        String l_strConditionNo = l_displayContentsParams.getConditionNo();
        //(*)����t���[
        //�\��������"�s��������&�M�p�������J��"��������"�s���������M�p�����J�݂̏ꍇ"
        //(�\�����eParams.�\�������ԍ� == "1054�F�@@�s�����������M�p�������J��" or
        // �\�����eParams.�\�������ԍ� == "1055�F�@@�s�����������M�p�����J��")
        //�ȉ��̏��������{����B
        WEB3PvInfoShortfallGenerationInfo l_shortfallGenerationInfo = null;
        if (WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE.equals(l_strConditionNo)
            || WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN.equals(l_strConditionNo))
        {
            //�s�����������
            l_shortfallGenerationInfo = new WEB3PvInfoShortfallGenerationInfo();

            //get�]�͎����~�敪(�ڋq : �ڋq)
            String l_strTPTradingStop = l_dataManager.getTPTradingStop(l_mainAccount);

            //get�s�����������(�ڋq : �ڋq)
            WEB3TPShortfallOccurInfo l_tPShortfallGenerationInfo =
                l_dataManager.getShortfallGenerationInfo(l_mainAccount);

            //�v���p�e�B�Z�b�g
            //�s���������\�����I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
            //�����~�敪 = get�]�͎����~�敪()�̖߂�l
            l_shortfallGenerationInfo.tradeStopDiv = l_strTPTradingStop;

            //�ۏ؋������U�֌㔻��t���O = get�s�����������()�̖߂�l.�ۏ؋������U�֌㔻��t���O
            l_shortfallGenerationInfo.autoTransferAfterJudgeFlag =
                l_tPShortfallGenerationInfo.depositAutoTransferDivFlag;

            //����(T+0) = get�s�����������()�̖߂�l.����(T+0)
            l_shortfallGenerationInfo.closeDate0 =
                l_tPShortfallGenerationInfo.closeDate0;

            //����(T+1) = get�s�����������()�̖߂�l.����(T+1)
            l_shortfallGenerationInfo.closeDate1 =
                l_tPShortfallGenerationInfo.closeDate1;

            //����(T+2) = get�s�����������()�̖߂�l.����(T+2)
            l_shortfallGenerationInfo.closeDate2 =
                l_tPShortfallGenerationInfo.closeDate2;

            //����(T+3) = get�s�����������()�̖߂�l.����(T+3)
            l_shortfallGenerationInfo.closeDate3 =
                l_tPShortfallGenerationInfo.closeDate3;

            //����(T+4) = get�s�����������()�̖߂�l.����(T+4)
            l_shortfallGenerationInfo.closeDate4 =
                l_tPShortfallGenerationInfo.closeDate4;

            //����(T+5) = get�s�����������()�̖߂�l.����(T+5)
            l_shortfallGenerationInfo.closeDate5 =
                l_tPShortfallGenerationInfo.closeDate5;

            //�K�v�����z(T+0) = get�s�����������()�̖߂�l.�K�v�����z(T+0)
            l_shortfallGenerationInfo.requiredPayAmt0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt0);

            //�K�v�����z(T+1) = get�s�����������()�̖߂�l.�K�v�����z(T+1)
            l_shortfallGenerationInfo.requiredPayAmt1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt1);

            //�K�v�����z(T+2) = get�s�����������()�̖߂�l.�K�v�����z(T+2)
            l_shortfallGenerationInfo.requiredPayAmt2 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt2);

            //�K�v�����z(T+3) = get�s�����������()�̖߂�l.�K�v�����z(T+3)
            l_shortfallGenerationInfo.requiredPayAmt3 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt3);

            //�K�v�����z(T+4) = get�s�����������()�̖߂�l.�K�v�����z(T+4)
            l_shortfallGenerationInfo.requiredPayAmt4 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt4);

            //�K�v�����z(T+5) = get�s�����������()�̖߂�l.�K�v�����z(T+5)
            l_shortfallGenerationInfo.requiredPayAmt5 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.requiredPayAmt5);

            //���Z�z(T+0) = get�s�����������()�̖߂�l.���Z�z(T+0)
            l_shortfallGenerationInfo.adjustedAmt0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.adjustedAmt0);

            //���Z�z(T+1) = get�s�����������()�̖߂�l.���Z�z(T+1)
            l_shortfallGenerationInfo.adjustedAmt1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.adjustedAmt1);

            //���v��S����(T+0) = get�s�����������()�̖߂�l.���v��S����(T+0)
            l_shortfallGenerationInfo.dayTradeRestraint0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.dayTradeRestraint0);

            //���v��S����(T+1) = get�s�����������()�̖߂�l.���v��S����(T+1)
            l_shortfallGenerationInfo.dayTradeRestraint1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.dayTradeRestraint1);

            //�ۏ؋�����̐U�֊z(T+0) = get�s�����������()�̖߂�l.�ۏ؋�����̐U�֊z(T+0)
            l_shortfallGenerationInfo.transferFromMarginDeposit0 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.transferFromMarginDeposit0);

            //�ۏ؋�����̐U�֊z(T+1) = get�s�����������()�̖߂�l.�ۏ؋�����̐U�֊z(T+1)
            l_shortfallGenerationInfo.transferFromMarginDeposit1 =
                WEB3StringTypeUtility.formatNumber(
                    l_tPShortfallGenerationInfo.transferFromMarginDeposit1);
        }

        //(*)����t���[
        //�\��������"��ꐅ���Ǐؔ���"�̏ꍇ
        //(�\�����eParams.�\�������ԍ� == "1056�F�@@��ꐅ���Ǐؔ���")
        //�ȉ��̏��������{����B
        WEB3PvInfoFirstAdditionalInfo l_firstAdditionalInfo = null;
        if (WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo))
        {
            //get�Ǐؖ������敪(�ڋq : �ڋq)
            String l_strAdditionalDepositStop =
                l_dataManager.getAdditionalDepositStop(l_mainAccount);

            //get�Ǐؔ������(�ڋq : �ڋq)
            WEB3TPAdddepositGenerationInfo l_tPAdddepositGenerationInfo =
                l_dataManager.getAdddepositGenerationInfo(l_mainAccount);

            //(*)����t���[
            //get�Ǐؔ������()�̖߂�l.get�Ǐ؏��@@!= null ����
            //get�Ǐؔ������()�̖߂�l.get�Ǐ؏��  itanceof  ���������Ǘ�<��ꐅ���Ǐ؏��>�^
            //�ł���ꍇ
            WEB3TPAdddepositInfo l_adddepositInfo =
                l_tPAdddepositGenerationInfo.getAdddepositInfo();
            if (l_adddepositInfo != null
                && l_adddepositInfo instanceof WEB3TPFirstAdddepositInfo)
            {
                WEB3TPFirstAdddepositInfo l_firstAdddepositInfo =
                    (WEB3TPFirstAdddepositInfo)l_adddepositInfo;
                //��ꐅ���Ǐؔ����\�����( )
                l_firstAdditionalInfo = new WEB3PvInfoFirstAdditionalInfo();

                //�v���p�e�B�Z�b�g
                //��ꐅ���Ǐؕ\�����I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
                //���ȉ��̒Ǐ؏��Ƃ́Aget�Ǐؔ������()�̖߂�l.get�Ǐ؏��̖߂�l��
                //�@@���������Ǘ�<��ꐅ���Ǐ؏��>�^�ɃL���X�g�����I�u�W�F�N�g
                //�����~�敪 = get�Ǐؖ������敪()�̖߂�l
                l_firstAdditionalInfo.firstTradeStopDiv = l_strAdditionalDepositStop;

                //�ۏ؋������U�֌㔻��t���O = get�Ǐؔ������()�̖߂�l.get�ۏ؋������U�֌㔻��t���O
                l_firstAdditionalInfo.firstAutoTransferAfterJudgeFlag =
                    l_tPAdddepositGenerationInfo.getDepositAutoTransferDivFlag();

                //�o�ߓ��� = �Ǐ؏��.�o�ߓ���
                l_firstAdditionalInfo.firstDepositPassDay =
                    l_firstAdddepositInfo.firstDepositPassDay + "";

                //�L���o�ߓ��� = �Ǐ؏��.�L���o�ߓ���
                l_firstAdditionalInfo.firstDepositPassDayValid =
                    l_firstAdddepositInfo.firstDepositPassDayValid + "";

                //������ = �Ǐ؏��.������
                l_firstAdditionalInfo.firstDepositOccurredDate =
                    l_firstAdddepositInfo.firstDepositOccurredDate;

                //�ۏ؋��� = �Ǐ؏��.�ۏ؋���
                l_firstAdditionalInfo.firstMarginDepositRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstMarginDepositRate);

                //�ۏ؋��ێ��� = �Ǐ؏��.�ۏ؋��ێ���
                l_firstAdditionalInfo.firstDepositRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstDepositRate);

                //�Ǐ؋��z = �Ǐ؏��.�Ǐ؋��z
                l_firstAdditionalInfo.firstDepositAmount =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstDepositAmount);

                //�Ǐ،��ϕK�v�z = �Ǐ؏��.�Ǐ،��ϕK�v�z
                l_firstAdditionalInfo.firstSettlement =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstSettlement);

                //�ۏ؋����� = �Ǐ؏��.�ۏ؋�����
                l_firstAdditionalInfo.firstMarginDepositInDe =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstMarginDepositInDe);

                //�ۏ؋�����(�������z) = �Ǐ؏��.�ۏ؋�����(�������z)
                l_firstAdditionalInfo.firstMarginDepositInDeExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstMarginDepositInDeExpect);

                //���ύό��� = �Ǐ؏��.���ύό���
                l_firstAdditionalInfo.firstSettledContract =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstSettledContract);

                //���������z = �Ǐ؏��.���������z
                l_firstAdditionalInfo.firstUncancelAmt =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstUncancelAmt);

                //���������ϕK�v�z = �Ǐ؏��.���������ϕK�v�z
                l_firstAdditionalInfo.firstUncancelSettleRequiredAmt =
                    WEB3StringTypeUtility.formatNumber(
                        l_firstAdddepositInfo.firstUncancelSettleRequiredAmt);
            }
        }

        //(*)����t���[
        //�\��������"��񐅏��Ǐؔ���"�̏ꍇ
        //(�\�����eParams.�\�������ԍ� == "1057�F�@@��񐅏��Ǐؔ���")
        //�ȉ��̏��������{����B
        WEB3PvInfoSecondAdditionalInfo l_secondAdditionalInfo = null;
        if (WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR.equals(l_strConditionNo))
        {
            //get�Ǐؖ������敪(�ڋq : �ڋq)
            String l_strAdditionalDepositStop =
                l_dataManager.getAdditionalDepositStop(l_mainAccount);

            //get�Ǐؔ������(�ڋq : �ڋq)
            WEB3TPAdddepositGenerationInfo l_tPAdddepositGenerationInfo =
                l_dataManager.getAdddepositGenerationInfo(l_mainAccount);

            //(*)����t���[
            //get�Ǐؔ������()�̖߂�l.get�Ǐ؏��@@!= null ����
            //get�Ǐؔ������()�̖߂�l.get�Ǐ؏��  itanceof  ���������Ǘ�<��񐅏��Ǐ؏��>�^
            //�ł���ꍇ
            WEB3TPAdddepositInfo l_adddepositInfo = l_tPAdddepositGenerationInfo.getAdddepositInfo();
            if (l_adddepositInfo != null
                && l_adddepositInfo instanceof WEB3TPSecondAdddepositInfo)
            {
                WEB3TPSecondAdddepositInfo l_secondAdddepositInfo =
                    (WEB3TPSecondAdddepositInfo)l_adddepositInfo;
                //��񐅏��Ǐؔ����\�����( )
                l_secondAdditionalInfo = new WEB3PvInfoSecondAdditionalInfo();

                //�v���p�e�B�Z�b�g
                //��񐅏��Ǐؕ\�����I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
                //���ȉ��̒Ǐ؏��Ƃ́Aget�Ǐؔ������()�̖߂�l.get�Ǐ؏��̖߂�l��
                //�@@���������Ǘ�<��񐅏��Ǐ؏��>�^�ɃL���X�g�����I�u�W�F�N�g
                //�����~�敪 = get�Ǐؖ������敪()�̖߂�l
                l_secondAdditionalInfo.secondTradeStopDiv = l_strAdditionalDepositStop;

                //�ۏ؋������U�֌㔻��t���O = get�Ǐؔ������()�̖߂�l.get�ۏ؋������U�֌㔻��t���O
                l_secondAdditionalInfo.secondAutoTransferAfterJudgeFlag =
                    l_tPAdddepositGenerationInfo.getDepositAutoTransferDivFlag();

                //����(����2) = �Ǐ؏��.����(����2)
                l_secondAdditionalInfo.secondCloseDate2 =
                    l_secondAdddepositInfo.secondCloseDate2;

                //����(����1) = �Ǐ؏��.����(����1)
                l_secondAdditionalInfo.secondCloseDate1 =
                    l_secondAdddepositInfo.secondCloseDate1;

                //����(��������) = �Ǐ؏��.����(��������)
                l_secondAdditionalInfo.secondCloseDateExpect =
                    l_secondAdddepositInfo.secondCloseDateExpect;

                //������(����2) = �Ǐ؏��.������(����2)
                l_secondAdditionalInfo.secondDepositOccurredDate2 =
                    l_secondAdddepositInfo.secondDepositOccurredDate2;

                //������(����1) = �Ǐ؏��.������(����1)
                l_secondAdditionalInfo.secondDepositOccurredDate1 =
                    l_secondAdddepositInfo.secondDepositOccurredDate1;

                //������(��������) = �Ǐ؏��.������(��������)
                l_secondAdditionalInfo.secondDepositOccurredDateExpect =
                    l_secondAdddepositInfo.secondDepositOccurredDateExpect;

                //�ۏ؋��ێ��� = �Ǐ؏��.�ۏ؋��ێ���
                l_secondAdditionalInfo.secondDepositRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDepositRate);

                //�ۏ؋��߂��ێ��� = �Ǐ؏��.�ۏ؋��߂��ێ���
                l_secondAdditionalInfo.secondDepositBackRate =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDepositBackRate);

                //�ۏ؋����i����2�j = �Ǐ؏��.�ۏ؋����i����2�j
                l_secondAdditionalInfo.secondMarginDepositRate2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositRate2);

                //�ۏ؋����i����1�j = �Ǐ؏��.�ۏ؋����i����1�j
                l_secondAdditionalInfo.secondMarginDepositRate1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositRate1);

                //�ۏ؋����i���������j = �Ǐ؏��.�ۏ؋����i���������j
                l_secondAdditionalInfo.secondMarginDepositRateExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositRateExpect);

                //�Ǐ؋��z�i�������j = �Ǐ؏��.�Ǐ؋��z�i�������j
                l_secondAdditionalInfo.secondDepositNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDepositNonPay);

                //�Ǐ؋��z�i����2�j = �Ǐ؏��.�Ǐ؋��z�i����2�j
                l_secondAdditionalInfo.secondDeposit2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDeposit2);

                //�Ǐ؋��z�i����1�j = �Ǐ؏��.�Ǐ؋��z�i����1�j
                l_secondAdditionalInfo.secondDeposit1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondDeposit1);

                //�Ǐ،��ϕK�v�z�i�������j = �Ǐ؏��.�Ǐ،��ϕK�v�z�i�������j
                l_secondAdditionalInfo.secondSettlementNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettlementNonPay);

                //�Ǐ،��ϕK�v�z�i����2�j = �Ǐ؏��.�Ǐ،��ϕK�v�z�i����2�j
                l_secondAdditionalInfo.secondSettlement2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettlement2);

                //�Ǐ،��ϕK�v�z�i����1�j = �Ǐ؏��.�Ǐ،��ϕK�v�z�i����1�j
                l_secondAdditionalInfo.secondSettlement1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettlement1);

                //�ۏ؋����� = �Ǐ؏��.�ۏ؋�����
                l_secondAdditionalInfo.secondMarginDepositInDe =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositInDe);

                //�ۏ؋������i�������z�j = �Ǐ؏��.�ۏ؋������i�������z�j
                l_secondAdditionalInfo.secondMarginDepositInDeExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondMarginDepositInDeExpect);

                //���ύό��� = �Ǐ؏��.���ύό���
                l_secondAdditionalInfo.secondSettledContract =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondSettledContract);

                //���������z�i�������j = �Ǐ؏��.���������z�i�������j
                l_secondAdditionalInfo.secondUncancelAmtNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmtNonPay);

                //���������z�i����2�j = �Ǐ؏��.���������z�i����2�j
                l_secondAdditionalInfo.secondUncancelAmt2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmt2);

                //���������z�i����1�j = �Ǐ؏��.���������z�i����1�j
                l_secondAdditionalInfo.secondUncancelAmt1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmt1);

                //���������z�i���������j = �Ǐ؏��.���������z�i���������j
                l_secondAdditionalInfo.secondUncancelAmtExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelAmtExpect);

                //���������ϕK�v�z�i�������j = �Ǐ؏��.���������ϕK�v�z�i�������j
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmtNonPay =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmtNonPay);

                //���������ϕK�v�z�i����2�j = �Ǐ؏��.���������ϕK�v�z�i����2�j
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmt2 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmt2);

                //���������ϕK�v�z�i����1�j = �Ǐ؏��.���������ϕK�v�z�i����1�j
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmt1 =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmt1);

                //���������ϕK�v�z�i���������j = �Ǐ؏��.���������ϕK�v�z�i���������j
                l_secondAdditionalInfo.secondUncancelSettleRequiredAmtExpect =
                    WEB3StringTypeUtility.formatNumber(
                        l_secondAdddepositInfo.secondUncancelSettleRequiredAmtExpect);
            }
        }

        //1.10 ���b�Z�[�W (*)����t���[
		//  (*)����t���[
		//  �ڋq���͂̏ꍇ(this.get�㗝���͎�()==null)
		//  ���A
		//  ���Ǌ��ǊǗ��Ώۃ��b�Z�[�W�̏ꍇ
		//  (�\�����eParams.�\�������ԍ� == ("0000�F�_�C���N�g�w��" or
		//  "1001�F���������������M�p�����J��" or "1002�F���������������M�p�������J��" or
		//  "1007�F���ϊ����ԋ�(��T�ԑO)�̌��ʕۗL" or "1003�F���֋�����" or "1005�F�؋����s��" or
		//  "1041�F20������1����30������5���ȉ�" or�@@"1042�F20������1����30������6��" or
		//  "1043�F20������2����30������6���ȉ�" or�@@"1044�F20������3���ȏ�" or
		//  "1045�F30������2�`4��" or "1046�F30������5��" or "1047�F30������6��" or "1048�F30������7���ȏ�" or
        //  "1054�F�s�����������M�p�������J��" or "1055�F�s�����������M�p�����J��" or
        //  "1056�F��ꐅ���Ǐؔ���" or "1057�F��񐅏��Ǐؔ���")
		//  �ȉ��̏��������{����B
        if ((this.getTrader() == null) 
            && (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE.equals(l_displayContentsParams.condition_no) 
            || WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_5DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_6DAY.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE.equals(
                l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN.equals(
                l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR.equals(
                l_displayContentsParams.condition_no)
            || WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR.equals(
                l_displayContentsParams.condition_no)))
        {
            log.debug("�ڋq���͂̏ꍇ�����Ǌ��ǊǗ��Ώۃ��b�Z�[�W�̏ꍇ");
            //1.10.1 get�{������Params(�ڋq, long)
            BrowseHistoryParams l_browseHistoryParams =
                l_dataManager.getBrowseHistoryParams(l_mainAccount, l_lngDisplayContentsId);
            log.debug("get�{������Params(�ڋq, long)�����s���܂�");

            //1.10.2 ���b�Z�[�W (*)����t���[
            if (l_browseHistoryParams == null)
            {
                log.debug("�{�������f�[�^�𑶍݂��Ȃ��ꍇ");
                //1.10.2.1 insert�{������(String, String, String, long, boolean)
                String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
                String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
                String l_strAccountCode = l_mainAccount.getAccountCode().substring(0, 6);
                log.debug("�،���ЃR�[�h: " + l_strInstitutionCode);
                log.debug("���X�R�[�h: " + l_strBranchCode);
                log.debug("�ڋq�R�[�h: " + l_strAccountCode);
                log.debug("�\�����eID: " + l_lngDisplayContentsId);
                log.debug("is����: " + true);


                //is���ǁF�@@true(���ǃf�[�^)
                l_dataManager.insertBrowseHistory(l_strInstitutionCode, l_strBranchCode, l_strAccountCode, l_lngDisplayContentsId, true);
                log.debug("insert�{������(String, String, String, long, boolean)�����s���܂�");
            }
            //1.10.3 (l_browseHistoryParams != null)
            else
            {
                log.debug("�{�������f�[�^�𑶍݂���ꍇ");
                //1.10.3.1  clone�{������Params(�{������Params)
                BrowseHistoryParams l_cloneBrowseHistoryParams = l_dataManager.cloneBrowseHistoryParams(l_browseHistoryParams);
                log.debug("clone�{������Params(�{������Params)�����s���܂�");

                //1.10.3.2 ���b�Z�[�W �v���p�e�B�Z�b�g
                l_cloneBrowseHistoryParams.read_flag = WEB3PvInfoReadFlagDef.READ_YES;
                //ThreadLocalSystemAttributesRegistry.getAttribute(������ԊǗ�.TIMESTAMP_TAG)�ɂĎ擾�������ݎ���
                Timestamp l_tsCurrent = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                l_cloneBrowseHistoryParams.setLastReadTimestamp(l_tsCurrent);
                l_cloneBrowseHistoryParams.last_updated_timestamp = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

                //1.10.3.3 update�{������(�{������Params)
                l_dataManager.updateBrowseHistory(l_cloneBrowseHistoryParams);
                log.debug("update�{������(�{������Params)�����s���܂�");
            }
        }

        //1.11 createResponse()
        WEB3PvInfoPrivateInformationDetailResponse l_response = (WEB3PvInfoPrivateInformationDetailResponse)l_request.createResponse();
        log.debug("createResponse()�����s���܂�");

        //1.12 �v���p�e�B�Z�b�g
        l_response.displayContentsId = new Long(l_displayContentsParams.getDisplayContentsId()).toString();
        l_response.displayTitle = l_displayContentsParams.display_title;
        l_response.lastUpdatedTimestamp = l_displayContentsParams.last_updated_timestamp;
        l_response.urlLink = l_displayContentsParams.display_url;
        l_response.displayMessage = l_displayContentsParams.display_message;
        log.debug("�\�����e�h�c: " + l_response.displayContentsId);
        log.debug("�\���^�C�g��: " + l_response.displayTitle);
        log.debug("�ŏI�X�V����: " + l_response.settleContractUnits);
        log.debug("URL�����N: " + l_response.settleContractUnits);
        log.debug("�\������" + l_response.displayMessage);
        l_response.advanceUnits = l_AdvanceUnits;

        if (l_arrlisSettleContractUnits.length == 0)
        {
            log.debug("toArray()�̖߂�l.length == 0�̏ꍇ");
            l_response.settleContractUnits = null;
            log.debug("���ʏ��ꗗ: " + l_response.settleContractUnits);
        }
        else
        {
            log.debug("toArray()�̖߂�l.length != 0�̏ꍇ");
            l_response.settleContractUnits = l_arrlisSettleContractUnits;
            log.debug("���ʏ��ꗗ: " + l_response.settleContractUnits);
        }
        
            l_response.cashoutStopUnit = l_pvInfoCashoutStopUnit;

        if(l_lisCommissionCampaignUnit == null || l_lisCommissionCampaignUnit.size() == 0)
        {
            l_response.commissionCampaignUnits = null; 
        }
        else
        {
            WEB3PvInfoCommissionCampaignUnit[] l_pvInfoCCUs = new WEB3PvInfoCommissionCampaignUnit[l_lisCommissionCampaignUnit.size()];
            l_lisCommissionCampaignUnit.toArray(l_pvInfoCCUs);
            l_response.commissionCampaignUnits = l_pvInfoCCUs;
        }

        //�s���������\����� ���@@�v���p�e�B�Z�b�g�����s���������\�����
        //      �@@�@@���v���p�e�B�Z�b�g���ĂȂ��ꍇ��null���Z�b�g�B
        l_response.shortfallGenerationInfo = l_shortfallGenerationInfo;

        //��ꐅ���Ǐؕ\�����    ���@@�v���p�e�B�Z�b�g������ꐅ���Ǐؕ\�����
        //      �@@�@@���v���p�e�B�Z�b�g���ĂȂ��ꍇ��null���Z�b�g�B
        l_response.firstAdditionalInfo = l_firstAdditionalInfo;

        //��񐅏��Ǐؕ\�����    ���@@�v���p�e�B�Z�b�g������񐅏��Ǐؕ\�����
        //      �@@�@@���v���p�e�B�Z�b�g���ĂȂ��ꍇ��null���Z�b�g�B
        l_response.secondAdditionalInfo = l_secondAdditionalInfo;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (delete�_�C���N�g�w�胁�b�Z�[�W)<BR>
     * �_�C���N�g�w�胁�b�Z�[�W�폜�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�X)delete�_�C���N�g�w�胁�b�Z�[�W�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoDirectMessageDeleteResponse
     * @@roseuid 41479F3E004A
     */
    protected WEB3PvInfoDirectMessageDeleteResponse deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " deleteDirectMessage(WEB3PvInfoDirectMessageDeleteRequest)";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);

        //1.1 validate( )(�_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g)
        l_request.validate();
        log.debug("validate( )�����s���܂�");

        //1.2 get�⏕����(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        log.debug("get�⏕����(SubAccountTypeEnum)�����s���܂�");

        //1.3 getMainAccount()
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        log.debug("getMainAccount()�����s���܂�");

        //1.4  validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        log.debug("validate������t�\()�����s���܂�");

        //1.5 get�{������Params(�ڋq, long)
        WEB3PvInfoDataManager l_pvInfoDataMgr = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        long l_lngDIsplayContentsId = Long.parseLong(l_request.displayContentsId);
        BrowseHistoryParams l_browseHistoryParams
            = l_pvInfoDataMgr.getBrowseHistoryParams(l_mainAccount, l_lngDIsplayContentsId);
        log.debug("get�{������Params(�ڋq, long)�����s���܂�");

        //1.6 clone�{������Params(�{������Params)
        BrowseHistoryParams l_cloneBrowseHistoryParams = l_pvInfoDataMgr.cloneBrowseHistoryParams(l_browseHistoryParams);
        log.debug("clone�{������Params(�{������Params)�����s���܂�");

        //1.7 ���b�Z�[�W �v���p�e�B�Z�b�g
        l_cloneBrowseHistoryParams.delete_flag = WEB3PvInfoDeleteFlagDef.DELETE_YES;
        l_cloneBrowseHistoryParams.last_updated_timestamp = (Timestamp) ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //1.8 update�{������(�{������Params)
        l_pvInfoDataMgr.updateBrowseHistory(l_cloneBrowseHistoryParams);
        log.debug("update�{������(�{������Params)�����s���܂�");

        //1.9 createResponse()
        WEB3PvInfoDirectMessageDeleteResponse l_response = (WEB3PvInfoDirectMessageDeleteResponse)l_request.createResponse();
        log.debug("createResponse()�����s���܂�");

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create���֋����ꗗ)<BR>
     * ���֋����̈ꗗ���쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(��ײ�ްĲ�̫Ұ��ݏڍ׃T�[�r�X)create���֋����ꗗ�v�Q��<BR>
     * @@param l_subAccount<BR>
     * @@return WEB3PvInfoAdvanceUnit[]<BR>
     */
    protected WEB3PvInfoAdvanceUnit[] createAdvanceUnitList(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAdvanceUnitList(WEB3GentradeMainAccount L_subAccount)";
        log.entering(STR_METHOD_NAME);
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        //1.1���Y�]�͏����擾����B
        Object l_objPowerInfo = l_dataManager.getTradingPowerInfo(l_subAccount);
        //1.2���֋������i�[����ArrayList�𐶐�����B
        List l_lstAdvanceUnit = new ArrayList();
        //1.3(*)�C���f�b�N�X�͈͓̔�(0�`5)�ɂ���Loop����
        //�p�����[�^.�⏕����.getMainAccount()���\�b�h�ɂČڋq�I�u�W�F�N�g���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        //�ڋq�I�u�W�F�N�g.is�M�p�����J��()���\�b�h���R�[������B       
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        for (int i = 0; i <= WEB3PvInfoTPBizDateCheckNumDef.MAX_DAYS ; i++)
        {
            //���������z
            double l_dblDemanDamount = 0D;
            //�c�Ɠ�
            Date l_datBizDate = null;
            //���o�\����
            double l_dblCallPower = 0D;
            //���o�\����
            double l_dblBalance = 0D;
            //(�����ڋq�̏ꍇ)�p�����[�^.�⏕����.getMainAccount().is�M�p�����J��() == false�̏ꍇ�A�ȉ��̏��������{����
            if (!l_blnIsMarginAccount)
            {
                WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
                //1.3.1.1�]�͌v�Z�������擾����B
                WEB3TPCalcCondition l_calcCondition = l_powerCalcEquity.getCalcCondition();
                //1.3.1.2�c�Ɠ����擾����B
                l_datBizDate = l_calcCondition.getBizDate(i);
                //1.3.1.3���������z���Z�o����B
                l_dblDemanDamount = l_powerCalcEquity.calcDemandamount(i);
            }
            else if (l_blnIsMarginAccount)
            {
                WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
                //1.3.2.1�]�͌v�Z�������擾����B
                WEB3TPCalcCondition l_calcCondition = l_powerCalcMargin.getCalcCondition();
                //1.3.2.2�c�Ɠ����擾����B
                l_datBizDate = l_calcCondition.getBizDate(i);
                //1.3.2.3�Ǐؗ]�͂��Z�o����B
                l_dblCallPower = l_powerCalcMargin.calcMarginCallPower(i);
                	//1.3.2.4���o�\�������Z�o����B
                	//l_dblBalance = Math.abs(Math.min(l_powerCalcMargin.calcActualPaymentBalance(i), 0D));
				//2006/03/13 �C��
				//1.3.2.4���ʗ��֋����Z�o����B
				l_dblBalance = l_powerCalcMargin.calcSpecialDebitAmount(i);
                //1.3.2.5���������z���Z�o����B
                l_dblDemanDamount = l_powerCalcMargin.calcDemandamount(i);
            }
            
            //1.3.3(*)���������z�`�F�b�N
            //�����Ώۂ̗]�͐��ږ��׃��j�b�g.���������z == 0�̏ꍇ�A���̗v�f�֏������ڍs����B(continue;)
            if (l_dblDemanDamount == 0D)
            {
                continue;
            }
            
            //1.3.4���֋����C���X�^���X�𐶐�����B
            WEB3PvInfoAdvanceUnit l_advanceUnit = new WEB3PvInfoAdvanceUnit();
            //1.3.5(*)�v���p�e�B�Z�b�g(*)���֋����Ɉȉ��̃v���p�e�B���Z�b�g����B
            //���֋�������    ���@@calc�c�Ɠ�()�̖߂�l
            l_advanceUnit.advanceGenDate = WEB3DateUtility.toDay(l_datBizDate);
            if (l_blnIsMarginAccount)
            {
                //�Ǐؗ]��(*1)  ���@@calc�Ǐؗ]��()�̖߂�l
                l_advanceUnit.additionalTradingPower = WEB3StringTypeUtility.formatNumber(l_dblCallPower);
				//�a����s���z(*1)    ���@@calc���ʗ��֋�()�̖߂�l
				l_advanceUnit.accountBalanceShortfall = WEB3StringTypeUtility.formatNumber(l_dblBalance);
            }
            //���������z    ���@@calc���������z()�̖߂�l
            l_advanceUnit.payClaimAmount = WEB3StringTypeUtility.formatNumber(l_dblDemanDamount);
            
            //1.3.6ArrayList�ɗ��֋�����ǉ�����B
            l_lstAdvanceUnit.add(l_advanceUnit);
        }
        
        //1.3.7ArrayList���֋����̔z����擾����B
        WEB3PvInfoAdvanceUnit[] l_AdvanceUnits = new WEB3PvInfoAdvanceUnit[l_lstAdvanceUnit.size()];
        l_lstAdvanceUnit.toArray(l_AdvanceUnits);
        log.exiting(STR_METHOD_NAME);
        return l_AdvanceUnits;
    }
}
@
