head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondRecruitBuyServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�T�[�r�XImpl(WEB3BondRecruitBuyServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/7 �s�p (���u) �V�K�쐬
                      : 2006/09/29 �����F (���u) ���f�� 094,098
                      : 2006/10/09 �����F (���u) ���f�� 105
                        2006/10/12 �ęԍg (���u)  WEB�V�J���W���̌������̑Ή��inew BigDecimal�����j
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondOrderUpdateInterceptor;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteRequest;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmRequest;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeOrderUnitIntroduceDiv;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (������/���t�T�[�r�XImpl)<BR>
 * ������/���t�T�[�r�XImpl<BR>
 *
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3BondRecruitBuyServiceImpl
    extends WEB3BondClientRequestService implements WEB3BondRecruitBuyService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A01D4
     */
    public WEB3BondRecruitBuyServiceImpl()
    {

    }

    /**
     * ������/���t�T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * validate����/���t�����Asubmit����/���t����<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C6C25402C6
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3BondApplyBuyConfirmRequest)
        {
            //validate����/���t����
            l_response =
                this.validateRecruitBuyOrder((WEB3BondApplyBuyConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3BondApplyBuyCompleteRequest)
        {
            //submit����/���t����
            l_response =
                this.submitRecruitBuyOrder((WEB3BondApplyBuyCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "�p�����[�^�[�̃^�C�v���s���ł��B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate����/���t����)<BR>
     * ������/���t���������R�����s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �uvalidate����/���t�����v�Q�ƁB <BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�uvalidate����/���t�����v)<BR>
     * �@@�@@:  1.17.3 �����򏈗���is����t���O()�̖߂�l == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C9BF8200DA
     */
    protected WEB3BondApplyBuyConfirmResponse validateRecruitBuyOrder(
        WEB3BondApplyBuyConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRecruitBuyOrder(WEB3BondApplyBuyConfirmRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1: validate( )
        l_request.validate();

        //1.2: get������(long)
        FinApp l_finapp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productMgr =
            (WEB3BondProductManager) l_tradingModule.getProductManager();

        WEB3BondProduct l_product =
            (WEB3BondProduct) l_productMgr.getBondProduct(Long.parseLong(l_request.productId));

        //1.3: validate������t�\(String)
        WEB3BondTradingTimeManagement.validateOrderAccept(l_product);

        //1.4: get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.5:validate����\�ڋq(�⏕���� : SubAccount)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //1.6:validate�i����/���t�j����(SubAccount, ������, String, String, double)
        WEB3BondOrderManager l_orderMgr = (WEB3BondOrderManager) l_tradingModule.getOrderManager();

        String l_strTradeDiv = l_request.tradeDiv;
        double l_dblFaceAmount = Double.parseDouble(l_request.faceAmount);
        l_orderMgr.validateRecruitOrBuyOrder(
            l_subAccount,
            l_product,
            l_strTradeDiv,
            l_request.settleDiv,
            l_dblFaceAmount);

        //1.7: ��������ʔ���(�������, String)
        //�@@����F�@@���N�G�X�g�f�[�^.����敪 == "���t"�̏ꍇ�A92�F�����d�؎���B
        //�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.����敪 == "����"�̏ꍇ�A35�F��W����B
        String l_strTrade = null;
        if (WEB3BondDealDivDef.BUY.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        }
        else if (WEB3BondDealDivDef.RECRUIT.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.RECRUIT_TRADING;
        }
        WEB3BondOrderTypeJudge l_orderTypeJudge = new WEB3BondOrderTypeJudge(
            OrderTypeEnum.BOND_BUY,
            l_strTrade);

        //1.8:is�O�݌�( )
        boolean l_blnIsForeignCurrency = l_product.isForeignCurrency();

        //1.9:�����򏈗���is�O�݌�()�̖߂�l == true �̏ꍇ
        BigDecimal l_bdFxRate = null;
        if (l_blnIsForeignCurrency)
        {
            BigDecimal l_bigDecimal = new BigDecimal("0");
            ////1.9.1:get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            l_bdFxRate = l_orderMgr.getFxRate(l_product, l_orderTypeJudge, l_request.settleDiv, l_bigDecimal, false);
        }

        //1.10: get������( )
        Date l_datOrderBizDate = WEB3BondTradingTimeManagement.getOrderBizDate();



        //1.11: create���������(java.util.Date, ��������ʔ���, ������,String, Branch)
        WEB3BondExecuteDateInfo l_execDateInfo = l_orderMgr.createBondExecutionDateInfo(
            l_datOrderBizDate,
            l_orderTypeJudge,
            l_product,
            l_request.settleDiv,
            l_subAccount.getMainAccount().getBranch());

        //1.12: calc��n���(��������ʔ���, BigDecimal, BigDecimal, BigDecimal, ������, ���������)
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        //�@@���ʁF�@@���N�G�X�g�f�[�^.�z�ʋ��z
        BigDecimal l_bdFaceAmt = new BigDecimal(l_request.faceAmount);

        //�@@�����P���F�@@������.���t�P��
        BigDecimal l_bdBuyPrice = new BigDecimal(String.valueOf(l_product.getBuyPrice()));

        //�@@�בփ��[�g�F�@@get�בփ��[�g()�̖߂�l
        //�i��is�O�݌�()�̖߂�l == false�̏ꍇ�Anull���Z�b�g����B�j
        WEB3BondEstimatedPriceCalcResult l_estimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge,
                l_bdFaceAmt,
                l_bdBuyPrice,
                l_bdFxRate,
                l_product,
                l_execDateInfo);

        //1.13:  get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //1.14:  create�g�����V�K�������e(Trader, ��������ʔ���, String,
        //double, double, TaxTypeEnum, java.util.Date, String)
        WEB3BondNewOrderSpec l_orderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
            l_trader,
            l_orderTypeJudge,
            l_product.getProductCode(),
            l_dblFaceAmount,
            l_product.getBuyPrice(),
            TaxTypeEnum.NORMAL,
            l_execDateInfo.getDeliveryDate(),
            l_request.settleDiv);

        //1.15: �������X�V�C���^�Z�v�^( )
        WEB3BondOrderUpdateInterceptor l_updateInterceptor = new WEB3BondOrderUpdateInterceptor();

        //1.16: �v���p�e�B�E�Z�b�g
        l_updateInterceptor.setBondExecuteDateInfo(l_execDateInfo);
        l_updateInterceptor.setBondEstimatedPriceCalcResult(l_estimatedPriceCalcResult);
        l_updateInterceptor.setBondNewOrderSpec(l_orderSpec);

        //1.17:�����򏈗������N�G�X�g�f�[�^.���ϋ敪 == �h�~�݁h �̏ꍇ
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
        {
            //1.17.1: validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
            //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount) l_subAccount,
                    new Object[]{l_updateInterceptor},
                    new Object[]{l_orderSpec},
                    OrderTypeEnum.BOND_BUY,
                    false);

            //1.17.2:  is����t���O( )
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //1.17.3: �����򏈗���is����t���O()�̖߂�l == false �̏ꍇ�A��O���X���[����B
            if (!l_blnIsResultFlg)
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����]�̓`�F�b�N�G���[�B");
            }
        }

        //1.18:  createNewOrderId( )
        long l_lngOrderId = l_orderMgr.createNewOrderId();

        //1.19: createResponse( )
        WEB3BondApplyBuyConfirmResponse l_response =
            (WEB3BondApplyBuyConfirmResponse) l_request.createResponse();

        //1.20: �v���p�e�B�E�Z�b�g
        //����ID          = createNewOrderId()�̖߂�l
        l_response.id = WEB3StringTypeUtility.formatNumber(l_lngOrderId);

        //��������i�O�݁j      = ����n����v�Z����.��������i�O�݁j
        BigDecimal l_dbForeignTradePrice = l_estimatedPriceCalcResult.getForeignTradePrice();

        if (l_dbForeignTradePrice != null)
        {
            l_response.foreignTradePrice = WEB3StringTypeUtility.formatNumber(
                l_dbForeignTradePrice.doubleValue());
        }

        //��������i�~�݁j      = ����n����v�Z����.��������i�~�݁j
        BigDecimal l_dbTradingPrice = l_estimatedPriceCalcResult.getTradingPrice();

        if (l_dbTradingPrice != null)
        {
            l_response.yenTradePrice = WEB3StringTypeUtility.formatNumber(
                l_dbTradingPrice.doubleValue());
        }

        //�o�ߗ��q�i�O�݁j      = ����n����v�Z����.�o�ߗ��q�i�O�݁j
        BigDecimal l_dbForeignAccruedInterest = l_estimatedPriceCalcResult.getForeignAccruedInterest();

        if (l_dbForeignAccruedInterest != null)
        {
            l_response.foreignAccruedInterest = WEB3StringTypeUtility.formatNumber(
                l_dbForeignAccruedInterest.doubleValue());
        }

        //�o�ߗ��q�i�~�݁j      = ����n����v�Z����.�o�ߗ��q�i�~�݁j
        BigDecimal l_bdAccruedInterest = l_estimatedPriceCalcResult.getAccruedInterest();

        if (l_bdAccruedInterest != null)
        {
            l_response.yenAccruedInterest = WEB3StringTypeUtility.formatNumber(
                l_bdAccruedInterest.doubleValue());
        }

        //��n����i�O�݁j      = ����n����v�Z����.��n����i�O�݁j
        BigDecimal l_bdForeignEstimatedPrice = l_estimatedPriceCalcResult.getForeignEstimatedPrice();

        if (l_bdForeignEstimatedPrice != null)
        {
            l_response.foreignDeliveryPrice = WEB3StringTypeUtility.formatNumber(
                l_bdForeignEstimatedPrice.doubleValue());
        }

        //��n����i�O�݁j      = ����n����v�Z����.��n����i�~�݁j
        BigDecimal l_bdEstimatedPrice = l_estimatedPriceCalcResult.getEstimatedPrice();

        if (l_bdEstimatedPrice != null)
        {
            l_response.yenDeliveryPrice = WEB3StringTypeUtility.formatNumber(
                l_bdEstimatedPrice.doubleValue());
        }

        //�m�F��������        = get������()�̖߂�l
        l_response.checkDate = l_datOrderBizDate;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����/���t����)<BR>
     * ������/���t�����o�^���s���B <BR>
     * <BR>
     * �V�[�P���X�} <BR>
     * �usubmit����/���t�����v�Q�ƁB <BR>
     * ========================================================<BR>
     *  �V�[�P���X�}(�usubmit����/���t�����v)<BR>
     * �@@�@@:  1.17.3 �����򏈗���is����t���O()�̖߂�l == false �̏ꍇ�A<BR>
     * �@@�@@�@@�@@��O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44C9BF9D0271
     */
    protected WEB3BondApplyBuyCompleteResponse submitRecruitBuyOrder(
        WEB3BondApplyBuyCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitRecruitBuyOrder(WEB3BondApplyBuyCompleteRequest) ";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "���N�G�X�g�����w��(null)�ł��B");
        }

        //1.1: validate( )
        l_request.validate();

        //1.2: get������(long)
        FinApp l_finapp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finapp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productMgr =
            (WEB3BondProductManager) l_tradingModule.getProductManager();

        WEB3BondProduct l_product =
            (WEB3BondProduct) l_productMgr.getBondProduct(Long.parseLong(l_request.productId));

        //1.3: validate������t�\(String)
        WEB3BondTradingTimeManagement.validateOrderAccept(l_product);

        //1.4: get������( )
        Date l_datOrderBizDate =
            WEB3BondTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.5: get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6:validate����\�ڋq(�⏕���� : SubAccount)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator) l_finApp.getCommonOrderValidator();

        OrderValidationResult l_validationResult =
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //1.7:validate�i����/���t�j����(SubAccount, ������, String, String, double)
        WEB3BondOrderManager l_orderMgr = (WEB3BondOrderManager) l_tradingModule.getOrderManager();

        String l_strTradeDiv = l_request.tradeDiv;
        double l_dblFaceAmount = Double.parseDouble(l_request.faceAmount);
        l_orderMgr.validateRecruitOrBuyOrder(
            l_subAccount,
            l_product,
            l_strTradeDiv,
            l_request.settleDiv,
            l_dblFaceAmount);

        //1.8: ��������ʔ���(�������, String)
        //�@@����F�@@���N�G�X�g�f�[�^.����敪 == "���t"�̏ꍇ�A92�F�����d�؎���B
        //�@@�@@�@@�@@�@@�@@���N�G�X�g�f�[�^.����敪 == "����"�̏ꍇ�A35�F��W����B
        String l_strTrade = null;
        if (WEB3BondDealDivDef.BUY.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING;
        }
        else if (WEB3BondDealDivDef.RECRUIT.equals(l_strTradeDiv))
        {
            l_strTrade = WEB3DealTypeDef.RECRUIT_TRADING;
        }
        WEB3BondOrderTypeJudge l_orderTypeJudge = new WEB3BondOrderTypeJudge(
            OrderTypeEnum.BOND_BUY,
            l_strTrade);

        //1.9:is�O�݌�( )
        boolean l_blnIsForeignCurrency = l_product.isForeignCurrency();

        //1.10:�����򏈗���is�O�݌�()�̖߂�l == true �̏ꍇ
        BigDecimal l_bdFxRate = null;
        if (l_blnIsForeignCurrency)
        {
            BigDecimal l_bigDecimal = new BigDecimal("0");
            //1.10.1:get�בփ��[�g(������, ��������ʔ���, String, BigDecimal, boolean)
            l_bdFxRate = l_orderMgr.getFxRate(l_product, l_orderTypeJudge, l_request.settleDiv, l_bigDecimal, false);
        }

        //1.11: create���������(java.util.Date, ��������ʔ���, ������, String, Branch)
        WEB3BondExecuteDateInfo l_execDateInfo = l_orderMgr.createBondExecutionDateInfo(
            l_datOrderBizDate,
            l_orderTypeJudge,
            l_product,
            l_request.settleDiv,
            l_subAccount.getMainAccount().getBranch());

        //1.12: calc��n���(��������ʔ���, BigDecimal, BigDecimal, BigDecimal, ������, ���������)
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        //�@@���ʁF�@@���N�G�X�g�f�[�^.�z�ʋ��z
        BigDecimal l_bdFaceAmt = new BigDecimal(l_request.faceAmount);

        //�@@�����P���F�@@������.���t�P��
        BigDecimal l_bdBuyPrice = new BigDecimal(String.valueOf(l_product.getBuyPrice()));

        WEB3BondEstimatedPriceCalcResult l_estimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_orderTypeJudge,
                l_bdFaceAmt,
                l_bdBuyPrice,
                l_bdFxRate,
                l_product,
                l_execDateInfo);

        //1.13:  get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //1.14:  create�g�����V�K�������e(Trader, ��������ʔ���, String,
        //double, double, TaxTypeEnum, java.util.Date, String)
        WEB3BondNewOrderSpec l_orderSpec = WEB3BondNewOrderSpec.createBondNewOrderSpec(
            l_trader,
            l_orderTypeJudge,
            l_product.getProductCode(),
            l_dblFaceAmount,
            l_product.getBuyPrice(),
            TaxTypeEnum.NORMAL,
            l_execDateInfo.getDeliveryDate(),
            l_request.settleDiv);

        //1.15: �������X�V�C���^�Z�v�^( )
        WEB3BondOrderUpdateInterceptor l_updateInterceptor = new WEB3BondOrderUpdateInterceptor();

        //1.16: �v���p�e�B�E�Z�b�g
        l_updateInterceptor.setBondExecuteDateInfo(l_execDateInfo);
        l_updateInterceptor.setBondEstimatedPriceCalcResult(l_estimatedPriceCalcResult);
        l_updateInterceptor.setBondNewOrderSpec(l_orderSpec);

        //1.17:�����򏈗������N�G�X�g�f�[�^.���ϋ敪 == �h�~�݁h �̏ꍇ
        if (WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_request.settleDiv))
        {
            //1.17.1: validate����]��(�⏕���� : �⏕����, �������e�C���^�Z�v�^ : Object[],
            //�������e : Object[], ������� : OrderTypeEnum, �]�͍X�V�t���O : boolean)
            WEB3TPTradingPowerService l_tpTradingPowerService =
                (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);

            WEB3TPTradingPowerResult l_tpResult =
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount) l_subAccount,
                    new Object[]{l_updateInterceptor},
                    new Object[]{l_orderSpec},
                    OrderTypeEnum.BOND_BUY,
                    true);

            //1.17.2:  is����t���O( )
            boolean l_blnIsResultFlg = l_tpResult.isResultFlg();

            //1.17.3: �����򏈗���is����t���O()�̖߂�l == false �̏ꍇ�A��O���X���[����B
            if (!l_blnIsResultFlg)
            {
                log.debug("����]�̓`�F�b�N�G���[�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "����]�̓`�F�b�N�G���[�B");
            }
        }

        //1.18: setThreadLocalPersistenceEventInterceptor(
        //arg0 : BondOrderManagerPersistenceEventInterceptor)
        l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);

        //1.19:  submitNewOrder(arg0 : SubAccount, arg1 : ProductTypeEnum, arg2 : NewOrderSpec, arg3 : long, arg4 : String, arg5 : boolean)
        long l_lngOrderId = Long.parseLong(l_request.id);
        OrderSubmissionResult l_submitNewOrderResult = l_orderMgr.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_orderSpec,
            l_lngOrderId,
            l_request.password,
            true);

        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.20: �����򏈗������N�G�X�g�f�[�^.�Љ�敪 �� null �̏ꍇ
        if (l_request.introduceStoreDiv != null)
        {
            //1.20.1: get�������P��By����ID(long)
            WEB3BondOrderUnit l_orderUnit = l_orderMgr.getBondOrderUnitByOrderId(l_lngOrderId);

            //1.20.2: �����P�ʏЉ�敪( )
            WEB3GentradeOrderUnitIntroduceDiv l_orderUnitIntroduceDiv =
                new WEB3GentradeOrderUnitIntroduceDiv();

            //1.20.3: �v���p�e�B�E�Z�b�g
            //�����P�ʂh�c�F�@@�������P��.�����P��ID
            l_orderUnitIntroduceDiv.setOrderUnitId(l_orderUnit.getOrderUnitId());

            //�@@�����h�c�F�@@�������P��.����ID
            l_orderUnitIntroduceDiv.setAccountId(l_orderUnit.getAccountId());

            //�@@�����^�C�v�F�@@�������P��.�����^�C�v
            l_orderUnitIntroduceDiv.setProductType(l_orderUnit.getProductType());

            //�@@�Љ�敪�F�@@���N�G�X�g�f�[�^.�Љ�敪
            l_orderUnitIntroduceDiv.setIntroduceBranchDiv(l_request.introduceStoreDiv);

            //�@@�Љ�X�R�[�h�F�@@���N�G�X�g�f�[�^.�Љ�X�R�[�h
            l_orderUnitIntroduceDiv.setIntroduceBranchCode(l_request.introduceStoreCode);

            //�@@�X�V�҃R�[�h�F�@@get�㗝���͎�()�̖߂�l �� null �̏ꍇ�A����.���҃R�[�h
            //����ȊO�̏ꍇ�A�ڋq.�ڋq�R�[�h
            if (l_trader != null)
            {
                l_orderUnitIntroduceDiv.setLastUpdater(l_trader.getTraderCode());
            }
            else
            {
                l_orderUnitIntroduceDiv.setLastUpdater(
                    l_subAccount.getMainAccount().getAccountCode());
            }

            //1.20.4:  saveNew�����P�ʏЉ�敪( )
            l_orderUnitIntroduceDiv.saveNewOrderUnitIntroduceDivRow();
        }

        //1.21: createResponse( )
        WEB3BondApplyBuyCompleteResponse l_response =
            (WEB3BondApplyBuyCompleteResponse) l_request.createResponse();

        //1.22: �v���p�e�B�E�Z�b�g
        //�m�F��������        = get������()�̖߂�l
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
