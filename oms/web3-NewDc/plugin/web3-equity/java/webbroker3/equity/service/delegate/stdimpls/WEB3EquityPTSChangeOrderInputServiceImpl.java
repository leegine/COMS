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
filename	WEB3EquityPTSChangeOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�������������������̓T�[�r�XImpl�iWEB3EquityPTSChangeOrderInputServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/17 �g�E�N�| (���u) �V�K�쐬
Revision History : 2007/12/20 �g�E�N�| (���u) �d�l�ύX�@@���f��1217�A1253�A1265�A1270
Revision History : 2008/02/04 �g�E�N�| (���u) ��Q�Ή��@@U3064
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BuySellTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3EquityTradingModule;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.message.WEB3EquityChangeInputResponse;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderInputService;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)�������������������̓T�[�r�XImpl)<BR>
 * (PTS)�������������������̓T�[�r�X�����N���X<BR>
 *
 * @@author �g�E�N�|
 * @@version 1.0
 */
public class WEB3EquityPTSChangeOrderInputServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSChangeOrderInputService
{

    /**
     * ���O���[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderInputServiceImpl.class);

    /**
     * @@roseuid 4766072000E1
     */
    public WEB3EquityPTSChangeOrderInputServiceImpl()
    {

    }

    /**
     * (PTS)���������������̓T�[�r�X�����{����B<BR>
     * <BR>
     * this.get�������͉��()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 473BD7E60078
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
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

        if (l_request instanceof WEB3EquityChangeInputRequest)
        {
            l_response = getChangeInputScreen((WEB3EquityChangeInputRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get�������͉��)<BR>
     * (PTS)���������������͉�ʕ\�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}�u(PTS)�����������́v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3EquityChangeInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 473BD95A0002
     */
    protected WEB3EquityChangeInputResponse getChangeInputScreen(
        WEB3EquityChangeInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getChangeInputScreen(WEB3EquityChangeInputRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3EquityTradingModule l_tradingModule =
            (WEB3EquityTradingModule)l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityPTSOrderManager l_orderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //���N�G�X�g.ID
        long l_lngOrderId = Long.parseLong(l_request.id);

        //�����Ώۂ̒����P�ʃI�u�W�F�N�g���擾����B
        OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);
        //�����P��
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];

        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        WEB3GentradeMarket l_genMarket = null;
        try
        {
            l_genMarket = (WEB3GentradeMarket)l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�e�[�u���ɊY������f�[�^������܂���B");
        }

        //reset�s��R�[�h(�s��R�[�h : String)
        WEB3EquityPTSTradingTimeManagement.resetMarketCode(l_genMarket.getMarketCode());

        //validate������t�\( )
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //getOrderValidator( )
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

        //�⏕����
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();

        //�ڋq : this.get�⏕����().getMainAccount( )
        WEB3GentradeMainAccount l_mainAccount =
            (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //������ : PTS������ԊǗ�.get������( )
        Timestamp l_tsOrderBizDate =
            new Timestamp(WEB3EquityPTSTradingTimeManagement.getOrderBizDate().getTime());

        //validate����\�ڋq(�ڋq : �ڋq, ������ : Timestamp)
        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateAccountForTrading(l_mainAccount, l_tsOrderBizDate);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_orderValidationResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        //get�s��ǌx���s��(���X, ProductTypeEnum, String)
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        String[] l_strTradeCloseMarkets =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_branch, ProductTypeEnum.EQUITY, WEB3MarginTradingDivDef.DEFAULT);

        //�������� : �����Ώۂ̒����P��.getProduct( )
        WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_orderUnit.getProduct();

        //�s�� : PTS�����}�l�[�W��.validate�s��R�[�h( )
        String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
        Market l_market = l_orderManager.validateMarket(l_genMarket.getMarketCode(), l_strInstitutionCode);

        //validate�������(��������, �s��)
        WEB3EquityTradedProduct l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderManager.validateTradedProduct(l_equityProduct, l_market);

        //validate�戵�\PTS�s��(���X, �������)
        l_orderManager.validateHandlingPossiblePTSMarket(l_branch, l_tradedProduct);

        //get�����P���敪�ꗗ(���X, �������)
        String[] l_orderPriceDivs = l_orderManager.getOrderPriceDivs(l_branch, l_tradedProduct);

        //validatePTS���������\���(����)
        l_orderManager.validatePTSOrderForChangeability(l_orderUnit.getOrder());

        //is�C���T�C�_�[�x���\��(�⏕����, long)
        boolean l_blnInsiderMessageSuspension =
            l_orderManager.isInsiderMessageSuspension(l_subAccount, l_orderUnitRow.getProductId());

        //validate�ڋq�����ʎ����~�iPTS�j(�⏕����, long, OrderTypeEnum)
        l_orderManager.validatePTSAccountProductOrderStop(
            l_subAccount, l_orderUnitRow.getProductId(), l_orderUnitRow.getOrderType());

        //�戵�\���������I�u�W�F�N�g�𐶐�����B
        WEB3GentradeHandlingOrderCond l_gentradeHandlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_genMarket.getMarketCode());

        //�戵�\���s�����擾( )
        String[] l_handlingPossibleExecConds = l_gentradeHandlingOrderCond.getHandlingPossibleExecCond();

        SideEnum l_sideEnum = l_orderUnit.getSide();

        double l_dblTradingPower = 0;

        double l_dblEstimatedBookPrice = 0;

        //�����Ώۂ̒����P��.getSide()=="��"�̏ꍇ
        if (SideEnum.BUY.equals(l_sideEnum))
        {
            //����]�̓T�[�r�X�擾
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);

            //get�������t�\�z(�⏕���� : �⏕����)
            l_dblTradingPower = l_tradingPowerService.getEquityTradingPower(l_subAccount);
        }
        //�����Ώۂ̒����P��.getSide()=="��"�̏ꍇ
        else if (SideEnum.SELL.equals(l_sideEnum))
        {
            //�����v�Z�T�[�r�X�擾
            WEB3EquityBizLogicProvider l_equityBizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();

            //calc�T�Z�뉿�P��(long, SubAccount, TaxTypeEnum)
            l_dblEstimatedBookPrice = l_equityBizLogicProvider.calcEstimatedBookPrice(
                l_orderUnitRow.getProductId(),
                l_subAccount,
                l_orderUnitRow.getTaxType());
        }

        //�g���v���_�N�g�}�l�[�W���擾
        WEB3EquityProductManager l_equityProductManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();

        //get�\���p�������(EqTypeTradedProduct, �⏕����)
        WEB3EquityProductQuote l_equityProductQuote =
            l_equityProductManager.getDisplayEquityProductQuote(l_tradedProduct, l_subAccount);

        //���X�|���X�N���X�𐶐�����B
        WEB3EquityChangeInputResponse l_response =
            (WEB3EquityChangeInputResponse)l_request.createResponse();

        //�������������͋��ʃ��X�|���X��

        //�����P���敪�ꗗ�F�@@PTS�����}�l�[�W��.get�����P���敪�ꗗ( )
        l_response.orderPriceDivList = l_orderPriceDivs;

        //�l�i�����ꗗ�F�@@"0:�w��Ȃ�"
        l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};

        //���s�����ꗗ�F�@@�戵�\��������.�戵�\���s�����擾( )
        l_response.execCondList = l_handlingPossibleExecConds;

        //W�w�l�p���s�����ꗗ�F�@@Null
        l_response.wlimitExecCondList = null;

        //�L�������J�n���F�@@Null
        l_response.expirationStartDate = null;

        //�L�������ŏI���F�@@Null
        l_response.expirationEndDate = null;

        //�L���������j���ꗗ�F�@@Null
        l_response.holidayList = null;

        //����I���x���s��R�[�h�ꗗ�F�@@PTS������ԊǗ�.get�s��ǌx���s��( )
        l_response.messageSuspension = l_strTradeCloseMarkets;

        //�C���T�C�_�[�x���\���t���O�F�@@PTS�����}�l�[�W��.is�C���T�C�_�[�x���\��( )
        l_response.insiderWarningFlag = l_blnInsiderMessageSuspension;

        //�����敪�F�@@�擾�������������������.get�����敪( )
        l_response.currentPriceDiv = l_equityProductQuote.getQuoteTypeDiv();

        //�����i���ݒl�j�F�@@�擾�������������������.get����( )
        l_response.currentPrice =
            WEB3StringTypeUtility.formatNumber(l_equityProductQuote.getQuote());

        //�O����F�@@�擾�������������������.get�O����( )
        l_response.comparedPreviousDay =
            WEB3StringTypeUtility.formatNumber(l_equityProductQuote.getComparedPreviousDay());

        //�������(�������\����)�F�@@�擾�������������������.get�������\����( )
        l_response.currentPriceTime = l_equityProductQuote.getQuoteTime();

        //��񍀖ڂ̐ݒ�d�l�͈ȉ��̒ʂ�B
        //�@@���ݒl�F�@@�擾�������������������.get���ݒl( )
        l_response.boardCurrentPrice = l_equityProductQuote.getBoardCurrentPrice();

        //�@@���ݒl�����F�@@�擾�������������������.get���ݒl����( )
        l_response.boardCurrentPriceTime = l_equityProductQuote.getBoardCurrentPriceTime();

        //�@@���ݒl�敪�F�@@�擾�������������������.get���ݒl�敪( )
        l_response.boardCurrentPriceDiv = l_equityProductQuote.getBoardCurrentPriceDiv();

        //�@@���ݒl�O����F�@@�擾�������������������.get���ݒl�O����( )
        l_response.boardChange = l_equityProductQuote.getBoardChange();

        //�@@�o�����F�@@�擾�������������������.get�o����( )
        l_response.volume = l_equityProductQuote.getVolume();

        //�@@�o���������F�@@�擾�������������������.get�o��������( )
        l_response.volumeTime = l_equityProductQuote.getVolumeTime();

        //�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪( )
        l_response.askPriceTitle = l_equityProductQuote.getAskPriceTitle();

        //�@@���C�z�l�F�@@�擾�������������������.get���C�z�l( )
        l_response.askPrice = l_equityProductQuote.getAskPrice();

        //�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����( )
        l_response.askPriceTime = l_equityProductQuote.getAskPriceTime();

        //�@@���C�z�l�^�C�g���敪�F�@@�擾�������������������.get���C�z�l�^�C�g���敪( )
        l_response.bidPriceTitle = l_equityProductQuote.getBidPriceTitle();

        //�@@���C�z�l�F�@@�擾�������������������.get���C�z�l( )
        l_response.bidPrice = l_equityProductQuote.getBidPrice();

        //�@@���C�z�l�����F�@@�擾�������������������.get���C�z�l����( )
        l_response.bidPriceTime = l_equityProductQuote.getBidPriceTime();

        //�@@��l�i�F�@@�擾�������������������.get��l�i( )
        l_response.basePrice = l_equityProductQuote.getBasePrice();

        //���������������������̓��X�|���X��
        //�����P��.������ʁ��h1�F�����������h�̏ꍇ
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnitRow.getOrderType()))
        {
            //���t�\���z�F����]�̓T�[�r�X.get�������t�\�z( )
            l_response.tradingPower = WEB3StringTypeUtility.formatNumber(l_dblTradingPower);

            //�����敪�F"1�F����"
            l_response.dealingType = WEB3BuySellTypeDef.BUY;
        }
        //�����P��.������ʁ��h2�F�����������h�̏ꍇ
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderUnitRow.getOrderType()))
        {
            //�����敪�F"2�F����"
            l_response.dealingType = WEB3BuySellTypeDef.SELL;

            //�T�Z�뉿�P���F�����v�Z�T�[�r�X.calc�T�Z�뉿�P��( )
            l_response.estimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }

        //�����R�[�h�F�@@�����P��.����ID�ɊY�����銔������.�����R�[�h
        l_response.productCode = l_equityProduct.getProductCode();

        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)l_equityProduct.getDataSourceObject();

        //�������F�@@�����P��.����ID�ɊY�����銔������.������
        l_response.productName = l_eqtypeProductRow.getStandardName();

        //�s��R�[�h�F�@@�����P��.�s��ID�ɊY������s��}�X�^.�s��R�[�h
        l_response.marketCode = l_genMarket.getMarketCode();

        //�����敪�F�@@�����f�[�^�A�_�v�^.get�����敪(�����P��.getTaxType)
        l_response.taxType = WEB3EquityDataAdapter.getTaxType(l_orderUnit.getTaxType());

        //���������F�@@�����P��.��������
        l_response.orderQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());

        //���o�������F�@@�����P��.��萔��
        l_response.partContQuantity =
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());

        //�����P��.isMarketOrder( )��true�̏ꍇ
        if (l_orderUnit.isMarketOrder())
        {
            //�����P���敪�F"0�F���s"
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        //�����P��.isMarketOrder( )��false�̏ꍇ
        else
        {
            //�����P���敪�F"1�F�w�l"
            l_response.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

            //�����P���F�����P��.�w�l
            l_response.limitPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        }

        //�l�i�����F�@@�����P��.�l�i����
        l_response.priceCondType = l_orderUnitRow.getPriceConditionType();

        //���s�����F�@@PTS�����}�l�[�W��.get���s�����iSONAR�j(�����P��.���s����)
        l_response.execCondType =
            l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnitRow.getExecutionConditionType());

        //PTS�����}�l�[�W��.is�o����܂Œ����P��(�����P��)��true�̏ꍇ
        if (l_orderManager.isCarriedOrderUnit(l_orderUnit))
        {
            //���������敪�F"2�F�o����܂Œ���"
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.CARRIED_ORDER;

            //�����L�������F�����P��.����������
            l_response.expirationDate = WEB3DateUtility.toDay(l_orderUnit.getExpirationTimestamp());
        }
        //PTS�����}�l�[�W��.is�o����܂Œ����P��(�����P��)��false�̏ꍇ
        else
        {
            //���������敪�F"1�F��������"
            l_response.expirationDateType = WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        }

        //���������敪�F�@@�����P��.��������
        String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();
        l_response.orderCondType = l_strOrderConditionType;

        //�����P��.����������1�i�t�w�l�j�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //�t�w�l�p���������P���F�����P��.�t�w�l
            if (!l_orderUnitRow.getStopOrderPriceIsNull())
            {
                l_response.stopOrderCondPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            }

            //�t�w�l�p�����������Z�q: �����P��.�����������Z�q
            l_response.stopOrderCondOperator = l_orderUnitRow.getOrderCondOperator();
        }
        //�����P��.����������2�iW�w�l�j�̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //W�w�l�p���������P���F�����P��.�t�w�l
            if (!l_orderUnitRow.getStopOrderPriceIsNull())
            {
                l_response.wlimitOrderCondPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getStopOrderPrice());
            }
            //W�w�l�p�����������Z�q�F�����P��.�����������Z�q
            l_response.wlimitOrderCondOperator = l_orderUnitRow.getOrderCondOperator();

            if (l_orderUnitRow.getWLimitPrice() == 0)
            {
                //W�w�l�p�����P���敪�F"0�F���s"
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                //W�w�l�p�����P���敪�F"1�F�w�l"
                l_response.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                //W�w�l�p�����P���F�����P��.�iW�w�l�j�����w�l
                l_response.wLimitPrice =
                    WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getWLimitPrice());
            }

            //W�w�l�p���s�����F�g�����������}�l�[�W��.get���s�����iSONAR�j(�����P��.�iW�w�l�j���s�����j
            l_response.wlimitExecCondType =
                l_orderManager.getExecutionConditionTypeSonar(l_orderUnitRow.getWLimitExecCondType());
        }

        //W�w�l�p�L����ԋ敪�F�@@�����f�[�^�A�_�v�^.getW�w�l�p�L����ԋ敪(�����P��)
        l_response.wlimitEnableStatusDiv =
            WEB3EquityDataAdapter.getWLimitEnableStatusDiv(l_orderUnit);

        //W�w�l�p�֑ؑO�����P���F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��(�����P��)
        l_response.wlimitBefChgLimitPrice =
            WEB3EquityDataAdapter.getWLimitBefSwitchPrice(l_orderUnit);

        //W�w�l�p�֑ؑO���s�����F�@@�����f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����(�����P��)
        l_response.wlimitBefChgExecCondType =
            WEB3EquityDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnit);

        //�����������敪�F�@@�����P��.����������
        l_response.orgOrderCondType = l_orderUnitRow.getOrgOrderConditionType();

        //�����������P���F�@@�����P��.���t�w�l��l
        if (!l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_response.orgOrderCondPrice =
                WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getOrgStopOrderPrice());
        }

        //�������������Z�q�F�@@�����P��.�������������Z�q
        l_response.orgOrderCondOperator = l_orderUnitRow.getOrgOrderCondOperator();

        //��W�w�l�p�����P���敪�F�@@�����f�[�^�A�_�v�^.get��W�w�l�p�����P���敪�i�����P�ʁj
        String l_strOrgWLimitOrderPriceDiv =
            WEB3EquityDataAdapter.getOrgWLimitOrderPriceDiv(l_orderUnit);

        l_response.orgWlimitOrderPriceDiv = l_strOrgWLimitOrderPriceDiv;

        //��W�w�l�p�����P���F�@@��W�w�l�p�����P���敪��"1�F�w�l"�̏ꍇ�A
        //   �����f�[�^�A�_�v�^.get��W�w�l�p�����P���i�����P�ʁj
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrgWLimitOrderPriceDiv))
        {
            l_response.orgWlimitPrice = WEB3EquityDataAdapter.getOrgWLimitOrderPrice(l_orderUnit);
        }

        //��W�w�l�p���s�����F�@@�����f�[�^�A�_�v�^.get��W�w�l�p���s�����i�����P�ʁj
        l_response.orgWlimitExecCondType = WEB3EquityDataAdapter.getOrgWLimitExecCondType(l_orderUnit);

        //�T�Z��n����F�@@�����P��.�T�Z��n���
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderUnitRow.getEstimatedPrice());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
