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
filename	WEB3BondSellServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p�T�[�r�XImpl(WEB3BondSellServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/06 ������ (���u) �V�K�쐬
                 : 2006/09/29 ��іQ (���u) ���f�� 094.105 �c�a�X�V�d�lNo.013
                   2006/10/12 �ęԍg (���u)  WEB�V�J���W���̌������̑Ή��inew BigDecimal�����j
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.define.WEB3DealTypeDef;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondClientRequestService;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUpdateInterceptor;
import webbroker3.bd.WEB3BondPositionManager;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.WEB3BondTradingTimeManagement;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.message.WEB3BondSellCompleteRequest;
import webbroker3.bd.message.WEB3BondSellCompleteResponse;
import webbroker3.bd.message.WEB3BondSellConfirmRequest;
import webbroker3.bd.message.WEB3BondSellConfirmResponse;
import webbroker3.bd.service.delegate.WEB3BondSellService;

/**
 * (�����p�T�[�r�XImpl)<BR>
 * �����p�T�[�r�X�����N���X<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellServiceImpl extends WEB3BondClientRequestService implements WEB3BondSellService
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellServiceImpl.class);

    /**
     * @@roseuid 44FBFD3A0128
     */
    public WEB3BondSellServiceImpl()
    {

    }

    /**
     * �����p���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * validate���p�����Asubmit���p�t����<BR>
     * �̂����ꂩ�̃��\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E93E610353
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GenResponse l_response = null;

        //validate���p����
        if (l_request instanceof WEB3BondSellConfirmRequest)
        {
            l_response = this.validateSellOrder(
                (WEB3BondSellConfirmRequest) l_request);
        }
        //submit���p����
        else if (l_request instanceof WEB3BondSellCompleteRequest)
        {
            l_response = this.submitSellOrder(
                (WEB3BondSellCompleteRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s��");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (validate���p����)<BR>
     * �����p���������R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uvalidate���p�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.bd.message.WEB3BondSellConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E9470401FC
     */
    protected WEB3BondSellConfirmResponse validateSellOrder(WEB3BondSellConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSellOrder(WEB3BondSellConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getAsset(ID : long)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = null;
        try
        {
            l_asset = l_bondPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 get������(long)
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_asset.getProduct().getProductId());

        //1.4 validate������t�\(String)
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.5 get�⏕����( )
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate����\�ڋq(�⏕���� : SubAccount)
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //1.7 get�㗝���͎�( )
        Trader l_trader = this.getTrader();

        //1.8 ��������ʔ���(�������, String)
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_SELL,
                WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);

        //1.9  get������( )
        Date l_datBizDate = WEB3BondTradingTimeManagement.getOrderBizDate();

        //1.10 create���������(java.util.Date, ��������ʔ���, ������, String, Branch)
        //�������F �擾����������
        //��������ʔ���F�@@�쐬������������ʔ���
        //�������F �擾����������
        //���ϋ敪�F�@@���N�G�X�g.���ϋ敪
        //���X�F�⏕����.getMainAccount().getBranch()�̖߂�l
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        WEB3BondExecuteDateInfo l_bondExecutDateInfo =
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.settleDiv,
                l_subAccount.getMainAccount().getBranch());

        //1.11 create�g�����V�K�������e(Trader, ��������ʔ���, String, double,
        //                              double, TaxTypeEnum, java.util.Date, String)
        //[����]
        //�I�y���[�^�F get�㗝���͎�()�̖߂�l
        //��������ʔ���F �쐬������������ʔ���
        //�����R�[�h(WEB3)�F �擾����������.�����R�[�h(WEB3)
        //���ʁF ���N�G�X�g�f�[�^.�z�ʋ��z
        //�P���F �擾����������.���t�P��
        //�ŋ敪�F �擾�����ۗL���Y.�ŋ敪
        //��n���F �����������������.get��n��()�̖߂�l
        //���ϋ敪�F ���N�G�X�g�f�[�^.���ϋ敪
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpec(
                l_trader,
                l_bondOrderTypeJudge,
                l_bondProduct.getProductCode(),
                Double.parseDouble(l_request.faceAmount),
                l_bondProduct.getSellPrice(),
                l_asset.getTaxType(),
                l_bondExecutDateInfo.getDeliveryDate(),
                l_request.settleDiv);

        //1.12 validate���p����(SubAccount, ������, �g�����V�K�������e)
        l_bondOrderManager.validateSellOrder(
            l_subAccount,
            l_bondProduct,
            l_bondNewOrderSpec);

        //1.13 is�O�݌�( )
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.14  �����򏈗���is�O�݌�()�̖߂�l == true �̏ꍇ
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.14.1 get�ʉ�( )
            WEB3GentradeCurrency l_gentradeCurrency = l_bondProduct.getCurrency();

            //1.14.2 get�בփ��[�g(is���t : boolean, is���v�Z : boolean, ���͈בփ��[�g : double)
            l_dblExchangeRate = l_gentradeCurrency.getExchangeRate(false, false, 0);
        }


        //1.15  calc��n���(��������ʔ���, BigDecimal, BigDecimal, BigDecimal, ������, ���������)
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdFxRate = null;

        if (l_blnIsForeignCurrency)
        {
            l_bdFxRate = new BigDecimal(String.valueOf(l_dblExchangeRate));
        }
        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.faceAmount),
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())),
                l_bdFxRate,
                l_bondProduct,
                l_bondExecutDateInfo);

        //1.16 createNewOrderId( )
        long l_lngnewOrderId = l_bondOrderManager.createNewOrderId();

        //1.17 createResponse( )
        WEB3BondSellConfirmResponse l_response =
            (WEB3BondSellConfirmResponse)l_request.createResponse();

        //1.18 �v���p�e�B�Z�b�g
        //����ID�@@�@@�@@�@@�@@�@@���@@createNewOrderId()�̖߂�l
        l_response.orderId = WEB3StringTypeUtility.formatNumber(l_lngnewOrderId);

        //��������i�O�݁j�@@���@@����n����v�Z����.��������i�O�݁j
        if (l_bondEstimatedPriceCalcResult.getForeignTradePrice() != null)
        {
            l_response.foreignTradePrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getForeignTradePrice().doubleValue());
        }

        //��������i�~�݁j�@@���@@����n����v�Z����.��������i�~�݁j
        if (l_bondEstimatedPriceCalcResult.getTradingPrice() != null)
        {
            l_response.yenTradePrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getTradingPrice().doubleValue());
        }

        //�o�ߗ��q�i�O�݁j�@@���@@����n����v�Z����.�o�ߗ��q�i�O�݁j
        if (l_bondEstimatedPriceCalcResult.getForeignAccruedInterest() != null)
        {
            l_response.foreignAccruedInterest =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getForeignAccruedInterest().doubleValue());
        }

        //�o�ߗ��q�i�~�݁j�@@���@@����n����v�Z����.�o�ߗ��q�i�~�݁j
        if (l_bondEstimatedPriceCalcResult.getAccruedInterest() != null)
        {
            l_response.yenAccruedInterest =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getAccruedInterest().doubleValue());
        }

        //��n����i�O�݁j�@@���@@����n����v�Z����.��n����i�O�݁j
        if (l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice() != null)
        {
            l_response.foreignEstDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getForeignEstimatedPrice().doubleValue());
        }

        //��n����i�~�݁j�@@���@@����n����v�Z����.��n����i�~�݁j
        if (l_bondEstimatedPriceCalcResult.getEstimatedPrice() != null)
        {
            l_response.yenEstDeliveryPrice =
                WEB3StringTypeUtility.formatNumber(
                    l_bondEstimatedPriceCalcResult.getEstimatedPrice().doubleValue());
        }

        //�m�F���������@@�@@�@@���@@get������()�̖߂�l
        l_response.checkDate = l_datBizDate;

        //1.19  ���X�|���X�f�[�^

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit���p����)<BR>
     * �����p�����o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �usubmit���p�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.bd.message.WEB3BondSellCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 44E947700315
     */
    protected WEB3BondSellCompleteResponse submitSellOrder(WEB3BondSellCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitSellOrder(WEB3BondSellCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();

        //1.2 getAsset(ID : long)
        //�ۗL���Y���擾����B
        //[����]
        //ID�F ���N�G�X�g�f�[�^.ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();

        Asset l_asset = null;

        try
        {
            l_asset = l_bondPositionManager.getAsset(Long.parseLong(l_request.id));
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //1.3 get������(long)
        //���������擾����B
        //[����]
        //����ID�F �擾�����ۗL���Y.����ID
        WEB3BondProductManager l_bondProductManager =
            (WEB3BondProductManager)l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct =
            (WEB3BondProduct)l_bondProductManager.getBondProduct(
                l_asset.getProduct().getProductId());

        //1.4 validate������t�\(String)
        //��t���ԃ`�F�b�N�A�ً}��~�`�F�b�N�A�o�b�`�������`�F�b�N���s�Ȃ��B
        //[����]
        //�������F�@@�擾����������
        WEB3BondTradingTimeManagement.validateOrderAccept(l_bondProduct);

        //1.5 get�⏕����( )
        //�⏕�������擾����B
        SubAccount l_subAccount = this.getSubAccount();

        //1.6 validate����\�ڋq(�⏕���� : SubAccount)
        //�ڋq�ʎ����~�����`�F�b�N���s�Ȃ��B
        //[����]
        //�⏕�����F �擾�����⏕����
        WEB3GentradeOrderValidator l_gentradeOrderValidator =
            (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();
        OrderValidationResult l_validationResult =
            l_gentradeOrderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("����\�ڋq�`�F�b�N�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + STR_METHOD_NAME,
                "����\�ڋq�`�F�b�N�G���[");
        }

        //1.7 get�㗝���͎�( )
        //�㗝���͎҂��擾����B
        Trader l_trader = this.getTrader();

        //1.8 ��������ʔ���(�������, String)
        //��������ʔ���I�u�W�F�N�g���쐬����B
        //[����]
        //������ʁF OrderTypeEnum."�����蒍��"
        //����F "�����d�؎��"
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge =
            new WEB3BondOrderTypeJudge(
                OrderTypeEnum.BOND_SELL,
                WEB3DealTypeDef.DOMESTIC_STATISTICS_TRADING);

        //1.9 get������(�m�F�������� : Date)
        //���������擾����B
        //[����]
        //�m�F���������F ���N�G�X�g�f�[�^.�m�F��������
        Date l_orderBizDate =
            WEB3BondTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //1.10 create���������(java.util.Date, ��������ʔ���, ������, String, Branch)
        //�����������쐬����B
        //[����]
        //�������F �擾����������
        //��������ʔ���F�@@�쐬������������ʔ���
        //�������F �擾����������
        //���ϋ敪�F�@@���N�G�X�g.���ϋ敪
        //���X�F�@@�⏕����.getMainAccount().getBranch()�̖߂�l
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        WEB3BondExecuteDateInfo l_bondExecutDateInfo =
            l_bondOrderManager.createBondExecutionDateInfo(
                l_orderBizDate,
                l_bondOrderTypeJudge,
                l_bondProduct,
                l_request.settleDiv,
                l_subAccount.getMainAccount().getBranch());

        //1.11 create�g�����V�K�������e(Trader, ��������ʔ���, String, double,
        //                               double, TaxTypeEnum, java.util.Date, String)
        //�I�y���[�^�F get�㗝���͎�()�̖߂�l
        //��������ʔ���F �쐬������������ʔ���
        //�����R�[�h(WEB3)�F �擾����������.�����R�[�h(WEB3)
        //���ʁF ���N�G�X�g�f�[�^.�z�ʋ��z
        //�P���F �擾����������.���t�P��
        //�ŋ敪�F �擾�����ۗL���Y.�ŋ敪
        //��n���F �����������������.get��n��()�̖߂�l
        //���ϋ敪�F ���N�G�X�g�f�[�^.���ϋ敪
        WEB3BondNewOrderSpec l_bondNewOrderSpec =
            WEB3BondNewOrderSpec.createBondNewOrderSpec(
                l_trader,
                l_bondOrderTypeJudge,
                l_bondProduct.getProductCode(),
                Double.parseDouble(l_request.faceAmount),
                l_bondProduct.getSellPrice(),
                l_asset.getTaxType(),
                l_bondExecutDateInfo.getDeliveryDate(),
                l_request.settleDiv);

        //1.12 validate���p����(SubAccount, ������, �g�����V�K�������e)
        //�������e���`�F�b�N����B
        //[����]
        //�⏕�����F �擾�����⏕����
        //�������F �擾����������
        //�g�����V�K�������e�F ���������g�����V�K�������e
        l_bondOrderManager.validateSellOrder(
            l_subAccount,
            l_bondProduct,
            l_bondNewOrderSpec);

        //1.13 is�O�݌�( )
        //�O�݌��������ǂ����𔻒肷��B
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();

        //1.14 �����򏈗���is�O�݌�()�̖߂�l == true �̏ꍇ
        double l_dblExchangeRate = 0.0D;
        if (l_blnIsForeignCurrency)
        {
            //1.14.1  get�ʉ�( )
            //�i���ʁj�ʉ݂��擾����B
            WEB3GentradeCurrency l_currency = l_bondProduct.getCurrency();

            //1.14.2 get�בփ��[�g(is���t : boolean, is���v�Z : boolean, ���͈בփ��[�g : double)
            //�בփ��[�g���擾����B
            //[����]
            //is���t�F false
            //is���v�Z�F false
            //���͈בփ��[�g�F 0
            l_dblExchangeRate = l_currency.getExchangeRate(false, false, 0);
        }

        //1.15 calc��n���(��������ʔ���, BigDecimal, BigDecimal, BigDecimal, ������, ���������)
        //����n����v�Z���ʂ��擾����B
        //[����]
        //��������ʔ���F �쐬������������ʔ���
        //�����P���F ������.���t�P��
        //�בփ��[�g�F is�O�݌�()�̖߂�l == true �̏ꍇ�͎擾�����בփ��[�g
        //�@@�@@�@@�@@�@@�@@ is�O�݌�()�̖߂�l == false �̏ꍇ��null
        //�������F �擾����������
        //���������F �����������������
        //���ʁF ���N�G�X�g�f�[�^.�z�ʋ��z
        WEB3BondBizLogicProvider l_bizLogicProvider =
            (WEB3BondBizLogicProvider) l_tradingModule.getBizLogicProvider();

        BigDecimal l_bdFxRate = null;

        if (l_blnIsForeignCurrency)
        {
            l_bdFxRate = new BigDecimal(String.valueOf(l_dblExchangeRate));
        }
        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge,
                new BigDecimal(l_request.faceAmount),
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())),
                l_bdFxRate,
                l_bondProduct,
                l_bondExecutDateInfo);

        //1.16 �������X�V�C���^�Z�v�^( )
        //�������X�V�C���^�Z�v�^�𐶐�����B
        WEB3BondOrderUpdateInterceptor l_bondOrderUpdateInterceptor =
            new WEB3BondOrderUpdateInterceptor();

        //1.17 �v���p�e�B�Z�b�g
        //�g�����V�K�������e�F ���������g�����V�K�������e
        l_bondOrderUpdateInterceptor.setBondNewOrderSpec(l_bondNewOrderSpec);

        //���������F �����������������
        l_bondOrderUpdateInterceptor.setBondExecuteDateInfo(l_bondExecutDateInfo);

        //����n����v�Z���ʁF calc��n���()�̖߂�l
        l_bondOrderUpdateInterceptor.setBondEstimatedPriceCalcResult(l_bondEstimatedPriceCalcResult);

        //1.18 setThreadLocalPersistenceEventInterceptor(�X�V�C���^�Z�v�^ : BondOrderManagerPersistenceEventInterceptor)
        //�X�V�C���^�Z�v�^�̐ݒ���s���B
        //[����]
        //�X�V�C���^�Z�v�^�F ���������������X�V�C���^�Z�v�^
        l_bondOrderManager.setThreadLocalPersistenceEventInterceptor(l_bondOrderUpdateInterceptor);

        //1.19 submitNewOrder(�⏕���� : SubAccount, �����^�C�v : ProductTypeEnum, �������e : NewOrderSpec,
        //                    ����ID : long, ����p�X���[�h : String, isSkip�����R�� : boolean)
        //��������o�^����B
        //[����]
        //�⏕�����F �擾�����⏕����
        //�����^�C�v�F ProductTypeEnum.��
        //�������e�F ���������g�����V�K�������e
        //����ID�F ���N�G�X�g�f�[�^.����ID
        //����p�X���[�h�F ���N�G�X�g�f�[�^.�Ïؔԍ�
        //isSkip�����R���F true
        OrderSubmissionResult l_submitNewOrderResult = l_bondOrderManager.submitNewOrder(
            l_subAccount,
            ProductTypeEnum.BOND,
            l_bondNewOrderSpec,
            Long.parseLong(l_request.orderId),
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

        //1.20 createResponse( )
        WEB3BondSellCompleteResponse l_response =
            (WEB3BondSellCompleteResponse)l_request.createResponse();

        //1.21  �v���p�e�B�Z�b�g
        //�ȉ��̒ʂ�A�v���p�e�B���Z�b�g����B
        //�X�V�����@@���@@���ݓ���
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        //1.22  ���X�|���X�f�[�^

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
