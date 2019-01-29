head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����c���Ɖ�T�[�r�XImpl(WEB3MarginBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 �򑺁@@�m�m(SRA) �V�K�쐬
                 : 2005/03/31 ��(FLJ)�@@���\�`���[�j���O�C��
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginBalanceReferenceComparator;
import webbroker3.equity.WEB3MarginClientRequestService;
import webbroker3.equity.WEB3MarginCloseStatus;
import webbroker3.equity.define.WEB3EquitySettlementStateDef;
import webbroker3.equity.define.WEB3MarginContractTypeDef;
import webbroker3.equity.message.WEB3MarginBalanceReferenceDetailUnit;
import webbroker3.equity.message.WEB3MarginBalanceReferenceRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceResponse;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalRequest;
import webbroker3.equity.message.WEB3MarginBalanceReferenceTotalResponse;
import webbroker3.equity.message.WEB3MarginProductCodeNameUnit;
import webbroker3.equity.message.WEB3MarginSortKey;
import webbroker3.equity.service.delegate.WEB3MarginBalanceReferenceService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;


/**
 * �i�M�p����c���Ɖ�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �M�p����c���Ɖ�T�[�r�X�����N���X<BR>
 */
public class WEB3MarginBalanceReferenceServiceImpl
extends WEB3MarginClientRequestService
implements WEB3MarginBalanceReferenceService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginBalanceReferenceServiceImpl.class);

    /**
     * @@roseuid 4206CDD500A8<BR>
     */
    public WEB3MarginBalanceReferenceServiceImpl()
    {

    }

    /**
     * �M�p����c���Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���M�p����c���Ɖ�c�����v���N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c�����v()���\�b�h���R�[������B<BR>
     * <BR>
     * ���M�p����c���Ɖ�N�G�X�g�̏ꍇ<BR>
     * �@@this.get�c���Ɖ�()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41BFCFC30159<BR>
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "WEB3MarginBalanceReferenceServiceImpl.execute()";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3MarginBalanceReferenceRequest)
        {
            l_response =
                this.getBalanceReference((WEB3MarginBalanceReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3MarginBalanceReferenceTotalRequest)
        {
            l_response =
                this.getBalanceTotal((WEB3MarginBalanceReferenceTotalRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�c���Ɖ�)<BR>
     * <BR>
     * �M�p����c���Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����c���Ɖ�T�[�r�X)get�c���Ɖ�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �M�p����c���Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MarginBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BFCFBA00CD<BR>
     */
    protected WEB3MarginBalanceReferenceResponse getBalanceReference(WEB3MarginBalanceReferenceRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3MarginBalanceReferenceRequest)";
        log.entering(STR_METHOD_NAME);

        // validate()
        l_request.validate();

        // validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get�⏕����()
        WEB3GentradeSubAccount l_subAccount =
            (WEB3GentradeSubAccount)this.getSubAccount();

        // �،���ЃI�u�W�F�N�g���擾
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();

        // �����R�[�h�w��̏ꍇ
        String l_strProductCode = l_request.productCode;
        if (l_strProductCode != null)
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_tradingModule.getProductManager();

            try {
                // �����R�[�h�̎擾�`�F�b�N���s��
                l_productManager.getProduct(l_institution, l_strProductCode);
            } catch (NotFoundException l_ex) {
                // �擾�ł��Ȃ������ꍇ�́A��O���X���[
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage());
            }
        }

        // ���X�|���X�f�[�^�𐶐�
        WEB3MarginBalanceReferenceResponse l_response =
            (WEB3MarginBalanceReferenceResponse)l_request.createResponse();

        // create�����E�s��v���_�E��()
        //���\�`���[�j���O�C�� ��(FLJ)
        //�����R�[�h���̂̈ꗗ�쐬�@@�\�폜
        //�s��R�[�h=������戵�\�s��
        //delete begin
//>>>>>
//        this.createProductMarketPullDown(l_subAccount, l_response);
//
//        if (l_response.productCodeNames == null)
//        {
//            return l_response;
//        }
//>>>>>
        String[] l_strHandlingMarketCodes =
            WEB3GentradeBranchMarketRepayDealtCond.getHandlingPossibleMarket(
            l_subAccount.getWeb3GenBranch(), WEB3GentradeRepaymentDivDef.DEFAULT, 0D);

        l_response.marketList = l_strHandlingMarketCodes;
        //delete end


        // create��������������
        String l_strMarketCode = l_request.marketCode;
        String l_strQueryString = this.createQueryString(l_strProductCode, l_strMarketCode);

        // create���������f�[�^�R���e�i
        String[] l_strQueryDataContainer =
            this.createQueryContainer(l_institution, l_strProductCode, l_strMarketCode);

        // create�c���Ɖ�׈ꗗ
        WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceUnits =
            this.createBalanceReferenceDetailUnitList(l_subAccount, l_request.settlementState, l_strQueryString, l_strQueryDataContainer);

        // create�c���Ɖ�׈ꗗ�̖߂�l == null�̏ꍇ
        if (l_balanceReferenceUnits == null || l_balanceReferenceUnits.length == 0)
        {
            l_response.balanceReference = null;
            return l_response;
        }

        // sort�c���Ɖ��
        this.sortBalanceReferenceDetailUnit(l_balanceReferenceUnits, l_request.sortKeys);

        // �\���Ώۍs�̎c���Ɖ�ׂ̒��o
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        WEB3PageIndexInfo l_pageIndexInfo =
            new WEB3PageIndexInfo(l_balanceReferenceUnits, l_intPageIndex, l_intPageSize);

        l_balanceReferenceUnits =
            (WEB3MarginBalanceReferenceDetailUnit[])l_pageIndexInfo.getArrayReturned(WEB3MarginBalanceReferenceDetailUnit.class);

        // set����\�t���O
        this.setTradingFlag(l_subAccount, l_balanceReferenceUnits);

        // ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        // �c���Ɖ��
        l_response.balanceReference = l_balanceReferenceUnits;
        // �\���y�[�W�ԍ�
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        // ���y�[�W��
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        // �����R�[�h��
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        return l_response;
    }

    /**
     * (get�c�����v)<BR>
     * <BR>
     * �M�p����c�����v�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����c���Ɖ�T�[�r�X)get�c�����v�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^) �M�p����c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3MarginBalanceReferenceTotalResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BFCFBA00EC<BR>
     */
    protected WEB3MarginBalanceReferenceTotalResponse getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest l_request)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3MarginBalanceReferenceTotalRequest) ";
        log.entering(STR_METHOD_NAME);

        // validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        // get�⏕����()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        // create�c���Ɖ�׈ꗗ()
        WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnits =
            this.createBalanceReferenceDetailUnitList(l_subAccount, null, null, null);

        // ���X�|���X�f�[�^�𐶐�
        WEB3MarginBalanceReferenceTotalResponse l_response =
            (WEB3MarginBalanceReferenceTotalResponse)l_request.createResponse();

        // �Y���f�[�^�Ȃ��̏ꍇ
        if (l_balanceReferenceDetailUnits == null || l_balanceReferenceDetailUnits.length == 0)
        {
            // ���X�|���X�ɏ����l���Z�b�g���ďI��
			l_response.buyTotalPrice = "0";
			l_response.sellTotalPrice = "0";
			l_response.capitalGainTotalPrice = "0";
			l_response.normalAccountTotalPrice = "0";
			l_response.totalPrice = "0";
			l_response.buyTotalAssetProfitLoss = "0";
			l_response.sellTotalAssetProfitLoss = "0";
			l_response.capitalGainTotalAssetProfitLoss = "0";
			l_response.normalAccountTotalAssetProfitLoss = "0";
			l_response.totalAssetProfitLoss = "0";
			l_response.buyTotalAssetProfitLossCost = "0";
			l_response.sellTotalAssetProfitLossCost = "0";
			l_response.capitalGainTotalAssetProfitLossCost = "0";
			l_response.normalAccountTotalAssetProfitLossCost = "0";
			l_response.totalAssetProfitLossCost = "0";

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        double l_dblContractAmountLong = 0.0D;
        double l_dblContractAmountShort = 0.0D;
        double l_dblContractAmountNormal = 0.0D;
        double l_dblContractAmountSpecial = 0.0D;
        double l_dblContractAmountTotal = 0.0D;
        double l_dblProfitLossLong = 0.0D;
        double l_dblProfitLossShort = 0.0D;
        double l_dblProfitLossNormal = 0.0D;
        double l_dblProfitLossSpecial = 0.0D;
        double l_dblProfitLossTotal = 0.0D;
        double l_dblProfitLossCostLong = 0.0D;
        double l_dblProfitLossCostShort = 0.0D;
        double l_dblProfitLossCostNormal = 0.0D;
        double l_dblProfitLossCostSpecial = 0.0D;
        double l_dblProfitLossCostTotal = 0.0D;

        // create�c���Ɖ�׈ꗗ()�̖߂�l�̗v�f����Loop����
        for (int i =0; i < l_balanceReferenceDetailUnits.length; i++)
        {
            // ������A�]�����v�A�]�����v�i���o��l���j���擾
            double l_dblContractAmount = Double.parseDouble(l_balanceReferenceDetailUnits[i].contractExecPrice);
            double l_dblProfitLoss = 0.0D;
            if (l_balanceReferenceDetailUnits[i].appraisalProfitLoss != null)
            {
                l_dblProfitLoss = Double.parseDouble(l_balanceReferenceDetailUnits[i].appraisalProfitLoss);
            }
            double l_dblProfitLossCost = 0.0D;
            if (l_balanceReferenceDetailUnits[i].appraisalProfitLossCost != null)
            {
                l_dblProfitLossCost = Double.parseDouble(l_balanceReferenceDetailUnits[i].appraisalProfitLossCost);
            }

            // �M�p����c���Ɖ��.���敪 == "����"�̏ꍇ
            if (l_balanceReferenceDetailUnits[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
            {
                // ���������z�A�������]�����v���v�A�������]�����v���v�i���o��l���j�ɉ��Z����
                l_dblContractAmountLong += l_dblContractAmount;
                l_dblProfitLossLong += l_dblProfitLoss;
                l_dblProfitLossCostLong += l_dblProfitLossCost;
            }
            // �M�p����c���Ɖ��.���敪 == "����"�̏ꍇ
            else if (l_balanceReferenceDetailUnits[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_SELL))
            {
                // ���������z�A�������]�����v���v�A�������]�����v���v�i���o��l���j�ɉ��Z����
                l_dblContractAmountShort += l_dblContractAmount;
                l_dblProfitLossShort += l_dblProfitLoss;
                l_dblProfitLossCostShort += l_dblProfitLossCost;
            }

            // �M�p����c���Ɖ��.�����敪 == "���"�̏ꍇ
            if (l_balanceReferenceDetailUnits[i].taxType.equals(WEB3TaxTypeDef.NORMAL))
            {
                // ��ʌ����������z�A��ʌ����]�����v���v�A��ʌ����]�����v���v�i���o��l���j
                l_dblContractAmountNormal += l_dblContractAmount;
                l_dblProfitLossNormal += l_dblProfitLoss;
                l_dblProfitLossCostNormal += l_dblProfitLossCost;
            }
            // �M�p����c���Ɖ��.�����敪 == "����"�̏ꍇ
            else if (l_balanceReferenceDetailUnits[i].taxType.equals(WEB3TaxTypeDef.SPECIAL))
            {
                // ��������������z�A��������]�����v���v�A��������]�����v���v�i���o��l���j
                l_dblContractAmountSpecial += l_dblContractAmount;
                l_dblProfitLossSpecial += l_dblProfitLoss;
                l_dblProfitLossCostSpecial += l_dblProfitLossCost;
            }
        }

        // �������z���v�A�����]�����v���v�A�����]�����v���v�i���o��l���j���Z�b�g����B
        l_dblContractAmountTotal = l_dblContractAmountLong + l_dblContractAmountShort;
        l_dblProfitLossTotal = l_dblProfitLossLong + l_dblProfitLossShort;
        l_dblProfitLossCostTotal = l_dblProfitLossCostLong + l_dblProfitLossCostShort;

        log.debug("�������z���v:" + l_dblContractAmountTotal);
        log.debug("�����]�����v���v:" + l_dblProfitLossTotal);
        log.debug("�����]�����v���v�i���o��l���j:" + l_dblProfitLossCostTotal);

        // ���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        // ���������z
        l_response.buyTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountLong);
        // ���������z
        l_response.sellTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountShort);
        // ��������������z
        l_response.capitalGainTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountSpecial);
        // ��ʌ����������z
        l_response.normalAccountTotalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountNormal);
        // �������z���v
        l_response.totalPrice = WEB3StringTypeUtility.formatNumber(l_dblContractAmountTotal);

        // �������]�����v���v
        l_response.buyTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossLong);
        // �������]�����v���v
        l_response.sellTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossShort);
        // ��������]�����v���v
        l_response.capitalGainTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossSpecial);
        // ��ʌ����]�����v���v
        l_response.normalAccountTotalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossNormal);
        // �����]�����v���v
        l_response.totalAssetProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblProfitLossTotal);

        // �������]�����v���v�i���o��l���j
        l_response.buyTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostLong);
        // �������]�����v���v�i���o��l���j
        l_response.sellTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostShort);
        // ��������]�����v���v�i���o��l���j
        l_response.capitalGainTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostSpecial);
        // ��ʌ����]�����v���v�i���o��l���j
        l_response.normalAccountTotalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostNormal);
        // �����]�����v���v�i���o��l���j
        l_response.totalAssetProfitLossCost = WEB3StringTypeUtility.formatNumber(l_dblProfitLossCostTotal);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create�����E�s��v���_�E��)<BR>
     * <BR>
     * �ڋq�̕ێ����錚�����A�����E�s��̈ꗗ���쐬���A<BR>
     * ���X�|���X�f�[�^�ɐݒ肵�ĕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����c���Ɖ�T�[�r�X)create�����E�s��v���_�E���v<BR>
     * �Q��<BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_response - (���X�|���X�f�[�^) �M�p����c���Ɖ�X�|���X�I�u�W�F�N�g<BR>
     * @@return WEB3MarginBalanceReferenceResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C11B7601D8<BR>
     */
    protected WEB3MarginBalanceReferenceResponse createProductMarketPullDown(WEB3GentradeSubAccount l_subAccount, WEB3MarginBalanceReferenceResponse l_response)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createProductMarketPullDown(WEB3GentradeSubAccount, WEB3MarginBalanceReferenceResponse)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        // �g�����Z�I�u�W�F�N�g�}�l�[�W���擾
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �|�W�V�����}�l�[�W���擾
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        // get�����ꗗ()
        List l_lstContract =
            l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, null, "product_id asc", null);

        // �Y���̌������擾�ł��Ȃ������ꍇ
        if (l_lstContract == null || l_lstContract.size() == 0)
        {
            // ���X�|���X�f�[�^�ɏ����l���Z�b�g���ďI������B
            l_response.productCodeNames = null;
            l_response.marketList = null;
            l_response.balanceReference = null;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        // �����R�[�h���́A�s��R�[�h�i�[���X�g�𐶐�����B
        TreeMap l_tmProcutCode = new TreeMap();
        TreeMap l_tmMarketCode = new TreeMap();

        WEB3EquityContract l_contract = null;
        // get�����ꗗ()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lstContract.size(); i++)
        {
            l_contract =
                new WEB3EquityContract((EqtypeContractRow)l_lstContract.get(i));

            // get���Ϗ��()
            WEB3MarginCloseStatus l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);

            // (�����Ώۃ`�F�b�N)
            // get���Ϗ��()�̖߂�l.���ϒ��t���O == false ���� �����σt���O == false�̏ꍇ�A
            // ���̗v�f�֏������ڍs����B
            if (l_closeStatus.closingMarginFlag == false
                && l_closeStatus.closeMarginFlag == false)
            {
                continue;
            }

            // getProduct()
            WEB3EquityProduct l_eqtypeProduct = (WEB3EquityProduct)l_contract.getProduct();

            // �����ꗗ���X�g�ɖ��ǉ��̏ꍇ
            if (l_tmProcutCode.get(l_eqtypeProduct.getProductCode()) == null)
            {
                // �M�p��������R�[�h���̃C���X�^���X�𐶐�
                WEB3MarginProductCodeNameUnit l_productCodeNameUnit = new WEB3MarginProductCodeNameUnit();

                // �v���p�e�B�Z�b�g
                // �����R�[�h
                l_productCodeNameUnit.productCode = l_eqtypeProduct.getProductCode();
                // ������
                EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_eqtypeProduct.getDataSourceObject();
                l_productCodeNameUnit.productName = l_eqtypeProductRow.getStandardName();

                // �����R�[�h���̊i�[���X�g�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�
                l_tmProcutCode.put(l_eqtypeProduct.getProductCode(), l_productCodeNameUnit);
            }

            // �s��I�u�W�F�N�g�擾
            Market l_market = null;
            try
            {
	            l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
            } catch (NotFoundException l_ex) {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂���");
            }

            // �s��R�[�h�i�[���X�g�Ɏs��R�[�h��ǉ�
            l_tmMarketCode.put(new Long(l_market.getMarketId()), l_market.getMarketCode());
        }

        // �v���p�e�B�Z�b�g
        // �����ꗗ
        l_response.productCodeNames =
            (WEB3MarginProductCodeNameUnit[])l_tmProcutCode.values().toArray(new WEB3MarginProductCodeNameUnit[0]);
        // �����ꗗ
        l_response.marketList =
            (String[])l_tmMarketCode.values().toArray(new String[0]);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create��������������)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�����ƂɁA���������iwhere�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����R�[�h == null�@@����<BR>
     * �@@�@@�p�����[�^.�s��R�[�h == null�̏ꍇ�Anull��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�@@�߂�l�ƂȂ镶����̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�����R�[�h != null�i�����R�[�h�w��j�̏ꍇ�A<BR>
     * �@@����ID�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@" and product_id = ?"<BR>
     * <BR>
     * �S�j�@@�p�����[�^.�s��R�[�h != null�i�s��R�[�h�w��j�̏ꍇ�A<BR>
     * �@@�s��ID�w��𕶎���C���X�^���X�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@" and market_id = ?"<BR>
     * <BR>
     * �T�j�@@������C���X�^���X��ԋp����B<BR>
     * <BR>
     * @@param l_strProductCode - (�����R�[�h) �����R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h) �s��R�[�h<BR>
     * @@return String<BR>
     * @@roseuid 41C12BEE00FE<BR>
     */
    protected String createQueryString(String l_strProductCode, String l_strMarketCode)
    {
        final String STR_METHOD_NAME = "createQueryString(String, String)";
        log.entering(STR_METHOD_NAME);

        // �p�����[�^.�����R�[�h == null ���� �p�����[�^.�s��R�[�h == null�̏ꍇ
        if (l_strProductCode == null
            && l_strMarketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        String l_strQueryString = "";

        // �����R�[�h�ɊY���������ID����������������ɒǉ�
        if (l_strProductCode != null)
        {
            l_strQueryString = " and product_id = ? ";
        }

        // �s��R�[�h�ɊY������s��ID����������������������ɒǉ�
        if (l_strMarketCode != null)
        {
            l_strQueryString += " and market_id = ? ";
        }

        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create���������f�[�^�R���e�i)<BR>
     * <BR>
     * ���N�G�X�g�f�[�^����A���������iwhere�ȉ��w��̕�����j�̃p�����[�^���X�g���쐬?
<BR>
     * ��B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����R�[�h == null�@@����<BR>
     * �@@�@@�p�����[�^.�s��R�[�h == null�̏ꍇ�Anull��ԋp���ďI������B<BR>
     * <BR>
     * �Q�j�@@������z����쐬����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�����R�[�h != null�i�����R�[�h�w��j�̏ꍇ�A����ID��<BR>
     * �@@�@@������z��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@����ID = �g���v���_�N�g�}�l�[�W��.get����(�p�����[�^.�،����, <BR>
     * �p�����[�^.�����R�[�h).����ID<BR>
     * <BR>
     * �S�j�@@�p�����[�^.�s��R�[�h != null�i�s��R�[�h�w��j�̏ꍇ�A�s��ID��<BR>
     * �@@�@@������z��ɃZ�b�g����B<BR>
     * <BR>
     * �@@�@@�@@�s��ID �� �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket(�p�����[�^.�،����, <BR>
     * �p�����[�^.�s��R�[�h).�s��ID<BR>
     * <BR>
     * �T�j�@@������z���ԋp����B<BR>
     * <BR>
     * @@param l_institution - (�،����) �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h) �����R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h) �s��R�[�h<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C12BEE0101<BR>
     */
    protected String[] createQueryContainer(WEB3GentradeInstitution l_institution, String l_strProductCode, String l_strMarketCode)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createQueryContainer(WEB3GentradeInstitution, String, String)";
        log.entering(STR_METHOD_NAME);

        // �p�����[�^.�����R�[�h == null ���� �p�����[�^.�s��R�[�h == null�̏ꍇ
        if (l_strProductCode == null
            && l_strMarketCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // �g���v���_�N�g�}�l�[�W���A�g�����Z�I�u�W�F�N�g�}�l�[�W���̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        // ���������A�s��I�u�W�F�N�g���擾
        EqTypeProduct l_eqtypeProduct = null;
        Market l_market = null;
        ArrayList l_lstQueryDataContainer = new ArrayList();
        try
        {
            if (l_strProductCode != null)
            {
		        l_eqtypeProduct =
		            l_productManager.getProduct(l_institution, l_strProductCode);
		        l_lstQueryDataContainer.add(String.valueOf(l_eqtypeProduct.getProductId()));
            }
            if (l_strMarketCode != null)
            {
                l_market =
	                l_finObjectManager.getMarket(l_institution, l_strMarketCode);
		        l_lstQueryDataContainer.add(String.valueOf(l_market.getMarketId()));
            }
        } catch (NotFoundException l_ex) {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������܂��́A�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂���");
        }

        log.exiting(STR_METHOD_NAME);
        return (String[])l_lstQueryDataContainer.toArray(new String[0]);
    }

    /**
     * (create�c���Ɖ�׈ꗗ)<BR>
     * <BR>
     * �M�p����c���Ɖ�ׂ̈ꗗ���쐬����B<BR>
     * �ȉ��̂����ꂩ�̌��Ϗ�Ԃɓ��Ă͂܂錚�����𒊏o����B<BR>
     * (���Ϗ�Ԃ̎w�肪����ꍇ�ɂ́A�w�茈�Ϗ�Ԃ݂̂̌������Ƃ���)<BR>
     * �E������<BR>
     * �E���ϒ�<BR>
     * <BR>
     * ���Y���f�[�^�����݂��Ȃ��ꍇ�ɂ�null��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����c���Ɖ�T�[�r�X)create�c���Ɖ�׈ꗗ�v<BR>
     * �Q��<BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strSpecifiedSettleDiv - (�w�茈�Ϗ��) ���L�̂����ꂩ�B<BR>
     * <BR>
     * null�F�w��Ȃ� <BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     * <BR>
     * @@param l_strQueryString - (��������������) ��������������<BR>
     * @@param l_strQueryDataContainer - (���������f�[�^�R���e�i) ���������f�[�^�R���e�i<BR>
     * @@return WEB3MarginBalanceReferenceDetailUnit[]<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C12E080275<BR>
     */
    protected WEB3MarginBalanceReferenceDetailUnit[] createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount l_subAccount, String l_strSpecifiedSettleDiv, String l_strQueryString, String[] l_strQueryDataContainer)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createBalanceReferenceDetailUnitList(WEB3GentradeSubAccount, String, String, String[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            (TradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // �g�����Z�I�u�W�F�N�g�}�l�[�W��
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        // �g���|�W�V�����}�l�[�W���擾
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

        // get�����ꗗ()
        List l_lstContract =
            l_positionManager.getContracts(l_subAccount, ProductTypeEnum.EQUITY, l_strQueryString, "product_id asc", l_strQueryDataContainer);

        // get�����ꗗ�ɂ�null���ԋp���ꂽ�ꍇ
        if (l_lstContract == null || l_lstContract.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        // �c���Ɖ�׊i�[���X�g�𐶐�����B
        ArrayList l_lstBalanceReferenceUnit = new ArrayList();

        WEB3EquityContract l_contract = null;
        WEB3MarginCloseStatus l_closeStatus = null;
        // get�����ꗗ()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_lstContract.size(); i++)
        {
            l_contract =
                new WEB3EquityContract((EqtypeContractRow)l_lstContract.get(i));

            // �s��I�u�W�F�N�g���擾
            Market l_market = null;
            try
            {
	            l_market = l_finObjectManager.getMarket(l_contract.getMarketId());
            } catch (NotFoundException l_ex) {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂���");
            }

            // reset�s��R�[�h()
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_market.getMarketCode());

            // get���Ϗ��()
            l_closeStatus = l_positionManager.getMarginCloseStatus(l_contract);

            // (�����Ώۃ`�F�b�N)
            // �ȉ��̏����̂��Âꂩ�ɊY������ꍇ�́A���̗v�f�֏������ڍs����B
            // �@@get���Ϗ��()�̖߂�l.���ϒ��t���O == false ���� �����σt���O == false�̏ꍇ
            if (l_closeStatus.closingMarginFlag == false
                && l_closeStatus.closeMarginFlag == false)
            {
                continue;
            }
            // �A�p�����[�^.�w�茈�Ϗ�Ԃ�
            // �@@�E"������" ���� get���Ϗ��()�̖߂�l.�����σt���O == false�̏ꍇ
            // �@@�E"���ϒ�" ���� get���Ϗ��()�̖߂�l.���ϒ��t���O == false�̏ꍇ
            if (l_strSpecifiedSettleDiv != null)
            {
	            if ((l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED)
	                    && l_closeStatus.closeMarginFlag == false)
	                 || l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.SETTLING)
	                    && l_closeStatus.closingMarginFlag == false)
	            {
	                continue;
	            }
            }

            // �����ό����̏ꍇ
            // (�p�����[�^.�w�茈�Ϗ�� == null or �p�����[�^.�w�茈�Ϗ�� == "������")
            // ����
            // get���Ϗ��()�̖߂�l.�����σt���O == true
            if ((l_strSpecifiedSettleDiv == null
                    || l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED))
                && l_closeStatus.closeMarginFlag == true)
            {
                // create�����ώc���Ɖ��
                l_lstBalanceReferenceUnit.add(
                    l_positionManager.createUnCloseMarginBalanceReferenceDetailUnit(l_subAccount, l_contract));
            }

            // ���ϒ������̏ꍇ
            // (�p�����[�^.�w�茈�Ϗ�� == null or �p�����[�^.�w�茈�Ϗ�� == "���ϒ�")
            // ����
            // get���Ϗ��()�̖߂�l.���ϒ��t���O == true
            if ((l_strSpecifiedSettleDiv == null
                    || l_strSpecifiedSettleDiv.equals(WEB3EquitySettlementStateDef.SETTLING))
                && l_closeStatus.closingMarginFlag == true)
            {
                // create���ϒ��c���Ɖ��
                l_lstBalanceReferenceUnit.add(
                    l_positionManager.createClosingMarginBalanceReferenceDetailUnit(l_subAccount, l_contract));
            }
        }

        // �c���Ɖ�ׂ̔z��𐶐����A�ԋp
        log.exiting(STR_METHOD_NAME);
        return (WEB3MarginBalanceReferenceDetailUnit[])l_lstBalanceReferenceUnit.toArray(new WEB3MarginBalanceReferenceDetailUnit[0]);
    }

    /**
     * (sort�c���Ɖ��)<BR>
     * <BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���ɂ��ǂ��Ďc���Ɖ�ׂ̃\�[�g���s���B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�Comparator�𐶐����A<BR>
     * �@@�@@�@@ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@�@@�M�p����c���Ɖ�Comparator�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��<BR>
     * �@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[����<BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B<BR>
     * <BR>
     * �R�jWEB3ArraysUtility.sort()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@obj�F�@@�p�����[�^.�c���Ɖ��<BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l<BR>
     * <BR>
     * @@param l_balanceReferenceDetailUnit - (�c���Ɖ��) �M�p����c���Ɖ�ׂ̔z��<BR>
     * @@param l_sortKey - (�\�[�g�L�[) �M�p����\�[�g�L�[�̔z��<BR>
     * @@roseuid 41C298710100<BR>
     */
    protected void sortBalanceReferenceDetailUnit(WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnit, WEB3MarginSortKey[] l_sortKey)
    {
        final String STR_METHOD_NAME = "sortBalanceReferenceDetailUnit(WEB3MarginBalanceReferenceDetailUnit, WEB3MarginSortKey)";
        log.entering(STR_METHOD_NAME);

        // �p�����[�^.�c���Ɖ�� == null�̏ꍇ�A�����I��
        if (l_balanceReferenceDetailUnit == null || l_balanceReferenceDetailUnit.length == 0)
        {
            return;
        }

        ArrayList l_lstComparators = new ArrayList();

        // �p�����[�^.�\�[�g�L�[�̗v�f����Loop����
        WEB3MarginBalanceReferenceComparator l_comparator = null;
        String l_strOrderBy = null;
        String l_strKeyItem = null;
        for (int i = 0; i < l_sortKey.length; i++)
        {
            l_strOrderBy = l_sortKey[i].ascDesc;
            l_strKeyItem = l_sortKey[i].keyItem;
            log.debug("��" + (i + 1) + "�\�[�g�L�[�F" + l_strKeyItem + " " + l_strOrderBy);

            // ���������c���Ɖ�Comparator�𐶐�
            l_comparator = new WEB3MarginBalanceReferenceComparator(l_strOrderBy, l_strKeyItem);
            // ArrayList��Comparator��ǉ�
            l_lstComparators.add(l_comparator);
        }

        // �\�[�g
        WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit,
            (WEB3MarginBalanceReferenceComparator[])l_lstComparators.toArray(new WEB3MarginBalanceReferenceComparator[0]));
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set����\�t���O)<BR>
     * <BR>
     * �����̎c���Ɖ�ׂ�<BR>
     * �@@�E�V�K���\�t���O<BR>
     * �@@�E�ԍω\�t���O<BR>
     * �@@�E�������n�\�t���O<BR>
     * �̐ݒ���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�M�p����c���Ɖ�T�[�r�X)set����\�t���O�v<BR>
     * �Q��<BR>
     * <BR>
     * <BR>
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g<BR>
     * @@param l_balanceReferenceDetailUnitList - (�c���Ɖ�׈ꗗ) �M�p����c���Ɖ�ׂ̔z��<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41C29B690357<BR>
     */
    protected void setTradingFlag(WEB3GentradeSubAccount l_subAccount, WEB3MarginBalanceReferenceDetailUnit[] l_balanceReferenceDetailUnitList)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingFlag(WEB3GentradeSubAccount, WEB3MarginBalanceReferenceDetailUnit[]) ";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        // �����`�F�b�N�T�[�r�X�擾�@@
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        // ���������}�l�[�W���擾
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        // �g���v���_�N�g�}�l�[�W���擾
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        // �ڋq�����擾
        WEB3GentradeInstitution l_institution =
            (WEB3GentradeInstitution)l_subAccount.getInstitution();
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();

        // create�����E�s��v���_�E��()
        //���\�`���[�j���O�C�� ��(FLJ)
        //����\�ڋq�`�F�b�NLOOP�O��
        //begin
        // �V�K���A�ԍρA�������n���ʃ`�F�b�N
        // ����\�ڋq�`�F�b�N
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);
        //end
        // �p�����[�^.�c���Ɖ�׈ꗗ�̗v�f����Loop����
        for (int i = 0; i < l_balanceReferenceDetailUnitList.length; i++)
        {
            // reset�s��R�[�h
            String l_strMarketCode = l_balanceReferenceDetailUnitList[i].marketCode;
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);


            // �`�F�b�NOK�łȂ��ꍇ
            if (!l_orderValidationResult.equals(OrderValidationResult.VALIDATION_OK_RESULT))
            {
                log.debug("validate����\�ڋq()����O���X���[�����ׁA�V�K���E�ԍρE�������n�t���O == false");
                // �V�K���\�t���O�A�ԍω\�t���O�A�������n�\�t���O��false���Z�b�g
                l_balanceReferenceDetailUnitList[i].tradingFlag = false;
                l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                l_balanceReferenceDetailUnitList[i].swapFlag = false;
                continue;
            }

            String l_strProductCode = l_balanceReferenceDetailUnitList[i].productCode;
            String l_strRepaymentType = l_balanceReferenceDetailUnitList[i].repayment.repaymentDiv;
            double l_dblRepaymentNum = Double.parseDouble(l_balanceReferenceDetailUnitList[i].repayment.repaymentTimeLimit);
            WEB3EquityProduct l_eqtypeProduct = null;
            WEB3EquityTradedProduct l_tradedProduct = null;

            try
            {
                // validate�����R�[�h(�M�p)
                l_eqtypeProduct =
                    (WEB3EquityProduct)l_orderManager.validateProductCode(l_strProductCode, l_strInstitutionCode, l_strRepaymentType);

                // get�������
                try {
	                l_tradedProduct =
	                    (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
                } catch (NotFoundException l_ex) {
                    log.debug("��������Ȃ� �،���ЃR�[�h�F[" +
                        l_institution.getInstitutionCode() +
                        "] �����R�[�h:[" + l_strProductCode +
                        "] �s��R�[�h:[" + l_strMarketCode + "]");
                    // �V�K���\�t���O�A�ԍω\�t���O�A�������n�\�t���O��false���Z�b�g
                    l_balanceReferenceDetailUnitList[i].tradingFlag = false;
                    l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                    l_balanceReferenceDetailUnitList[i].swapFlag = false;
                    continue;
                }
                // validate�戵�\�s��()
                l_orderManager.validateHandlingMarket(l_branch, l_tradedProduct, l_strMarketCode, l_strRepaymentType, l_dblRepaymentNum);
                // validate�C���T�C�_�[()
                l_orderManager.validateInsider(l_subAccount, l_eqtypeProduct);

            } catch (WEB3BusinessLayerException l_wbex) {
                log.debug("�V�K���E�ԍρE�������n���ʃ`�F�b�NNG�ׁ̈A�V�K���E�ԍρE�������n�t���O = false���Z�b�g");
                // �V�K���\�t���O�A�ԍω\�t���O�A�������n�\�t���O��false���Z�b�g
                l_balanceReferenceDetailUnitList[i].tradingFlag = false;
                l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                l_balanceReferenceDetailUnitList[i].swapFlag = false;
                continue;
            }

            // �������ǂ����̃t���O
            boolean l_blnIsShort;
            if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_SELL))
            {
                l_blnIsShort = true;
            }
            else
            {
                l_blnIsShort = false;
            }

            WEB3GentradeMarket l_market = null;
            OrderTypeEnum l_orderTypeEnum = null;
            // �����ώc���Ɖ�ׂ̏ꍇ
            if (l_balanceReferenceDetailUnitList[i].settlementState.equals(WEB3EquitySettlementStateDef.HAVE_NOT_SETTLED))
            {
                // �ԍω\�`�F�b�N���s��
                // ������t�g�����U�N�V���� = "�ԍ�"���Z�b�g
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.CLOSE_MARGIN);
                try
                {
	                // validate������t�\
	                WEB3GentradeTradingTimeManagement.validateOrderAccept();
	                // validate�s��R�[�h
	                l_market = (WEB3GentradeMarket)l_orderManager.validateMarket(l_strMarketCode, l_strInstitutionCode);
	                // validate��������i�M�p�j
                    l_orderManager.validateTradedProductForMarginTrading(l_subAccount, l_eqtypeProduct, l_market, l_branch, l_strRepaymentType, OrderCategEnum.CLOSE_MARGIN, l_blnIsShort);

                    // ������ʂ̔���
                    if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
                    {
                        // �������ԍς��Z�b�g
                        l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
                    }
                    else
                    {
                        // �������ԍς��Z�b�g
                        l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
                    }
                    // validate�ڋq�����ʎ����~
                    l_orderManager.validateAccountProductOrderStop(l_subAccount, l_eqtypeProduct.getProductId(), l_orderTypeEnum);

                } catch (WEB3BusinessLayerException l_wbex) {
	                log.debug("�ԍω\�`�F�b�NNG�ׁ̈A�ԍσt���O = false");
                    // �ԍω\�t���O��false���Z�b�g
	                l_balanceReferenceDetailUnitList[i].closeMarginFlag = false;
                }

                // �������n�\�`�F�b�N
                // ������t�g�����U�N�V���� = "�����E���n"���Z�b�g
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.SWAP_MARGIN);
                // ��t���ԋ敪 = "�����E���n"���Z�b�g
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.SWAP);

                try
                {
	                // validate������t�\
	                WEB3GentradeTradingTimeManagement.validateOrderAccept();
	                
					// �g�����Z�I�u�W�F�N�g�}�l�[�W��
					WEB3GentradeFinObjectManager l_finObjectManager =
						(WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
						
					try
					{
						l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
					}
					catch (NotFoundException l_nfe)
					{
						throw new WEB3SystemLayerException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80005,
						this.getClass().getName() + "." + STR_METHOD_NAME,
						"�s��I�u�W�F�N�g�̎擾�Ɏ��s���܂���");
					}
	                // validate��������i�M�p�j
                    l_orderManager.validateTradedProductForMarginTrading(
                        l_subAccount, l_eqtypeProduct, l_market, l_branch, l_strRepaymentType, OrderCategEnum.SWAP_MARGIN, l_blnIsShort);

                    // ������ʂ̔���
                    if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
                    {
                        // �������Z�b�g
                        l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
                    }
                    else
                    {
                        // ���n���Z�b�g
                        l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
                    }
                    // validate�ڋq�����ʎ����~
                    l_orderManager.validateAccountProductOrderStop(l_subAccount, l_eqtypeProduct.getProductId(), l_orderTypeEnum);

                } catch (WEB3BusinessLayerException l_wbex) {
                    log.debug("�������n�\�`�F�b�NNG�ׁ̈A�������n�t���O = false���Z�b�g");
                    // �������n�\�t���O��false���Z�b�g
                    l_balanceReferenceDetailUnitList[i].swapFlag = false;
                }
            }

            // �V�K���\�`�F�b�N
            // ������t�g�����U�N�V���� = "���t(�V�K����)" or "���t(�V�K����)"���Z�b�g
            if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
            {
                // ���t(�V�K����)���Z�b�g
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
            }
            else
            {
                // ���t(�V�K����)���Z�b�g
                WEB3GentradeTradingTimeManagement.resetOrderAcceptTransaction(WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
            }
            // ��t���ԋ敪 = "�����E�M�p"���Z�b�g
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);

            try
            {
	            // validate������t�\
	            WEB3GentradeTradingTimeManagement.validateOrderAccept();
	            // validate�s��R�[�h
                l_market =
                    (WEB3GentradeMarket)l_orderManager.validateMarket(l_strMarketCode, l_strInstitutionCode);
                // validate��������i�M�p�j
                l_orderManager.validateTradedProductForMarginTrading(
                    l_subAccount, l_eqtypeProduct, l_market, l_branch, l_strRepaymentType, OrderCategEnum.OPEN_MARGIN, l_blnIsShort);

                // ������ʂ̔���
                if (l_balanceReferenceDetailUnitList[i].contractType.equals(WEB3MarginContractTypeDef.OPEN_BUY))
                {
                    // �V�K�������Z�b�g
                    l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
                }
                else
                {
                    // �V�K�������Z�b�g
                    l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
                }
                // validate�ڋq�����ʎ����~
                l_orderManager.validateAccountProductOrderStop(l_subAccount, l_eqtypeProduct.getProductId(), l_orderTypeEnum);

            } catch (WEB3BusinessLayerException l_wbex) {
                log.debug("�V�K���\�`�F�b�NNG�ׁ̈A�V�K���\�t���O = false���Z�b�g");
                // �V�K���\�t���O��false���Z�b�g
                l_balanceReferenceDetailUnitList[i].tradingFlag = false;
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
