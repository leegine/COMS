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
filename	WEB3EquityPTSChangeOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����������������T�[�r�XImpl(WEB3EquityPTSChangeOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 �����F (���u) �V�K�쐬 ���f��1241 1255 1265 1269
Revision History : 2007/12/28 �����F (���u) ���f��1277
Revision History : 2008/01/08 �����F (���u) ���f��1278,1279
Revision History : 2008/01/22 ��іQ (���u) ���f�� No.1289
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityChangeOrderSpec;
import webbroker3.equity.WEB3EquityChangeOrderUnitEntry;
import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManagerChangeOrderEventInterceptor;
import webbroker3.equity.WEB3EquityPTSOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;
import webbroker3.equity.message.WEB3EquityCommissionInfoUnit;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderRequestAdapter;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderService;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.define.WEB3TPResultAttentionObjectionTypeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ((PTS)�����������������T�[�r�XImpl)<BR>
 * (PTS)�����������������T�[�r�X�����N���X<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3EquityPTSChangeOrderServiceImpl
    extends WEB3EquityClientRequestService
    implements WEB3EquityPTSChangeOrderService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderServiceImpl.class);

    /**
     * @@roseuid 476607200064
     */
    public WEB3EquityPTSChangeOrderServiceImpl()
    {

    }

    /**
     * (validate��������)<BR>
     * (PTS)���������m�F���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(PTS)���������m�F�v �Q��<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �N���C�A���g����̓��̓f�[�^<BR>
     * @@return WEB3EquityChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 474A634400AC
     */
    protected WEB3EquityChangeConfirmResponse validateChangeOrder(
        WEB3EquityChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateChangeOrder(WEB3EquityChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //validate������t�\( )
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //create(WEB3GenRequest)
        WEB3EquityChangeOrderRequestAdapter l_changeOrderRequestAdapter =
            WEB3EquityPTSChangeOrderRequestAdapter.create(l_request);

        //create�������������l�ڍ�(���������������N�G�X�g�A�_�v�^)
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            WEB3EquityChangeOrderUnitEntry.createChangeOrderUnitEntry(l_changeOrderRequestAdapter);

        //���������������e(long, �������������l�ڍ�, String, String, ����)
        //�����h�c : (PTS)�������������������N�G�X�g�A�_�v�^.get����ID( )
        //�������������l�ڍ� : ���������������������l�ڍ�
        //�،���ЃR�[�h : this.get�⏕����( ).getInstitution( ).�،���ЃR�[�h
        //�����`���l�� : this.get���O�C���`���l��( )
        //���� : this.get�㗝���͎�( )
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_changeOrderRequestAdapter.getRequestOrderId(),
                l_changeOrderUnitEntry,
                this.getSubAccount().getInstitution().getInstitutionCode(),
                this.getLoginChannel(),
                this.getTrader());

        //createPTS�����������e
        WEB3EquityNewCashBasedOrderSpec l_orderSpec = l_changeOrderSpec.createPTSOrderSpec();

        //validatePTS��������(�⏕����, ���������������e)
        //�⏕���� : this.get�⏕����( )
        //���������������e : �����������������������e
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPTSOrderManager l_equityPTSOrderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderValidationResult l_validationResult =
            l_equityPTSOrderManager.validatePTSChangeOrder(
                this.getSubAccount(),
                l_changeOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_validationResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        //���������������e.get�����������P��( )
        OrderUnit l_orderUnit = l_changeOrderSpec.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3EquityTradedProduct l_tradedProduct = null;
        Market l_market = null;
        try
        {
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(l_orderUnitRow.getProductId());
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            //getTradedProduct
            //�،���� : this.get�⏕����( ).getInstitution( )
            //�����R�[�h : ���������������e.get�����������P��( ).����ID�ɊY�����銔������.�����R�[�h
            //�s��R�[�h : ���������������e.get�����������P��( ).�s��ID�ɊY������s��}�X�^.�s��R�[�h
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    this.getSubAccount().getInstitution(),
                    l_product.getProductCode(),
                    l_market.getMarketCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�萔�� : �����������e.get�萔��( )
        WEB3GentradeCommission l_commission = l_orderSpec.getCommission();

        //calc�T�Z��n���
        //�萔�� : �����������e.get�萔��( )
        //�w�l : �����������e.getLimitPrice( )
        //�iW�w�l�j�����w�l : �����������e.get�iW�w�l�j�����w�l( )
        //�t�w�l��l : �����������e.get�t�w�l��l( )
        //���s���� : �����������e.getExecConditionType( )
        //�iW�w�l�j���s���� : �����������e.get�iW�w�l�j���s����( )
        //�l�i���� : �����������e.get�l�i����( )
        //�������� : �����������e.get��������( )
        //�m�F���擾���� : null
        //is�X�g�b�v�����L�� : �������������l�ڍ�.is�X�g�b�v�����L��( )
        //�⏕���� : this.get�⏕����( )
        //������� : �g���v���_�N�g�}�l�[�W��.getTradedProduct( )
        //���� : �����������e.getQuantity( )
        //is������ : �����������e.isSellOrder( )
        //��萔�� : ���������������e.get�����������P��( ).��萔��
        //���v�����z : ���������������e.get�����������P��( ).���v�����z
        //isSkip���z�`�F�b�N : false�i�Œ�j
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_equityPTSOrderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                null,
                l_changeOrderUnitEntry.isStopOrderEnable(),
                this.getSubAccount(),
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                l_orderUnitRow.getExecutedQuantity(),
                l_orderUnitRow.getExecutedAmount(),
                false);

        //set�����P��(double)
        //�����P�� : �T�Z��n����v�Z����.get�v�Z�P��( )
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //set�T�Z��n���(double)
        //�T�Z���z : �T�Z��n����v�Z����.get�T�Z��n���( )
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //validatePTS�s��ʎ���\������z(���X, �s��, double)
        //���X : this.get�⏕����( ).get����X( )
        //�s�� : �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket( )
        //�@@�mgetMarket( )�̈����n
        //�@@�@@�s��ID : �����Ώۂ̒����P��.�s��ID
        //�S��������� : �萔��.get���o��v�Z�p���( )
        l_equityPTSOrderManager.validatePTSMarketMaxHandlingPrice(
            this.getSubAccount().getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //�������������C���^�Z�v�^(String, ����)
        //�����o�H�敪 : ���O�C���Z�b�V�������擾���������ڒl
        //�㗝���͎� : this.get�㗝���͎�( )
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_changeOrderEventInterceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //set�����������e(�����������e)
        //�����������e : ���������������e.createPTS�����������e( )
        l_changeOrderEventInterceptor.setEquityOrderSpec(l_orderSpec);

        //validate����]��
        //�⏕���� : this.get�⏕����( )
        //�������e�C���^�Z�v�^ : ���������������������C���^�Z�v�^
        //�������e : ���������������e
        //������� : �����Ώۂ̒����P��.�������
        //�]�͍X�V�t���O : false�i�m�F���j
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_changeOrderEventInterceptors = {l_changeOrderEventInterceptor};
        Object[] l_orderSpecs = {l_changeOrderSpec};

        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                this.getSubAccount(),
                l_changeOrderEventInterceptors,
                l_orderSpecs,
                l_orderUnit.getOrderType(),
                false);

        //throw���������]�̓G���[�ڍ׏��
        //����]�͌��ʁF�@@����]�̓T�[�r�X.validate����]��( )
        //������ʁF�@@�������i�����������e.isSellOrder() == true�j�̏ꍇ�A�h�����������h���Z�b�g�B
        //            �������i�����������e.isSellOrder() == false�j�̏ꍇ�A�h�����������h���Z�b�g����B
        //���������F�@@�����������e.getQuantity( )
        OrderTypeEnum l_orderType = null;
        boolean l_blnIsSellOrder = l_orderSpec.isSellOrder();
        if (l_blnIsSellOrder)
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        l_equityPTSOrderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //createResponse
        WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
            (WEB3EquityChangeConfirmResponse)l_request.createResponse();

        //get�s��ǌx���s��(���X, ProductTypeEnum, String)
        //���X : this.get�⏕����( ).get����X( )
        //�����^�C�v : "����"
        //�M�p����敪 : "DEFAULT"
        String[] l_strTradeCloseMarkets =
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                this.getSubAccount().getWeb3GenBranch(),
                ProductTypeEnum.EQUITY,
                WEB3MarginTradingDivDef.DEFAULT);

        //is�C���T�C�_�[�x���\��(�⏕����, long)
        boolean l_blnIsInsiderMessageSuspension =
            l_equityPTSOrderManager.isInsiderMessageSuspension(
            this.getSubAccount(),
            l_orderUnitRow.getProductId());

        //���蒍���̏ꍇ�̂ݎ��s
        if (l_blnIsSellOrder)
        {
            //calc�T�Z�뉿�P��(long, SubAccount, TaxTypeEnum)
            //����ID : �����Ώۂ̒����P��.����ID
            //�⏕���� : this.get�⏕����( )
            //�ŋ敪 : �����Ώۂ̒����P��.�ŋ敪
            WEB3EquityBizLogicProvider l_bizLogicProvider =
                (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            double l_dblEstimatedBookPrice = l_bizLogicProvider.calcEstimatedBookPrice(
                l_orderUnitRow.getProductId(),
                this.getSubAccount(),
                l_orderUnitRow.getTaxType());

            // ���X�|���X.�T�Z�뉿�P��
            l_changeConfirmResponse.estimatedBookPrice =
                WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
        }

        // ���X�|���X.�m�F��������
        Date l_datOrderBizDate = WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        l_changeConfirmResponse.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);

        // ���X�|���X.�T�Z��n���
        l_changeConfirmResponse.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_orderSpec.getEstimateDeliveryAmount());

        // ���X�|���X.����I���x���s��R�[�h�ꗗ
        l_changeConfirmResponse.messageSuspension = l_strTradeCloseMarkets;

        // ���X�|���X.���o������
        double l_dblExecutedQuantity = l_orderUnitRow.getExecutedQuantity();
        if (l_dblExecutedQuantity == 0.0D)
        {
            l_changeConfirmResponse.partContQuantity = null;
        }
        else
        {
            l_changeConfirmResponse.partContQuantity =
                WEB3StringTypeUtility.formatNumber(l_dblExecutedQuantity);
        }

        WEB3EquityCommissionInfoUnit l_commissionInfo = new WEB3EquityCommissionInfoUnit();

        //���X�|���X.�萔�����.�萔���R�[�X
        l_commissionInfo.commissionCourse = l_commission.getCommissionCourseDiv();
        // ���X�|���X.�萔�����.�萔��
        l_commissionInfo.commission =
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFee());
        // ���X�|���X.�萔�����.�萔�������
        l_commissionInfo.commissionConsumptionTax =
            WEB3StringTypeUtility.formatNumber(l_estimatedDeliveryPrice.getCommissionFeeTax());
        l_changeConfirmResponse.commissionInfo = l_commissionInfo;

        // ���X�|���X.�m�F���P��
        l_changeConfirmResponse.checkPrice = l_estimatedDeliveryPrice.getCheckGetCurrentPrice();

        // ���X�|���X.�C���T�C�_�[�x���\���t���O
        l_changeConfirmResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;

        //���X�|���X.���ӕ����\���敪
        l_changeConfirmResponse.attentionObjectionType = l_tpResult.getAttentionObjectionType();

        //���X�|���X.�a����s���z
        //����]�͌���.get���ӕ����\���敪() == "1�F�����s�����ӕ����\��" �܂���
        //����]�͌���.get���ӕ����\���敪() == "3�F�a����s�����ӕ����\��"�̏ꍇ
        //����]�͌��ʂ̓�����
        if (WEB3TPResultAttentionObjectionTypeDef.LACK_CASH_AMT_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType())
            || WEB3TPResultAttentionObjectionTypeDef.LACK_ACC_BAL_ATTENTION.equals(
                l_tpResult.getAttentionObjectionType()))
        {
            l_changeConfirmResponse.accountBalanceInsufficiency =
                WEB3StringTypeUtility.formatNumber(l_tpResult.getLackAccountBalance());
        }

        // ���X�|���X.�����L������
        l_changeConfirmResponse.expirationDate = l_changeOrderRequestAdapter.getOrderExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_changeConfirmResponse;
    }

    /**
     * (submit��������)<BR>
     * (PTS)���������̓o�^���s���B<BR>
     * <BR>
     * �V�[�P���X�}�u(PTS)���������X�V�v �Q��<BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * �N���C�A���g����̓��̓f�[�^<BR>
     * @@return WEB3EquityChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 474A760001C6
     */
    protected WEB3EquityChangeCompleteResponse submitChangeOrder(
        WEB3EquityChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitChangeOrder(WEB3EquityChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //validate( )
        l_request.validate();

        //get������(Date)
        //�m�F�������� : ���N�G�X�g.�m�F��������
        if (l_request.checkDate == null)
        {
            l_request.checkDate =
                WEB3EquityPTSTradingTimeManagement.getOrderBizDate();
        }
        WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //validate������t�\( )
        WEB3EquityPTSTradingTimeManagement.validateOrderAccept();

        //create(WEB3GenRequest)
        WEB3EquityChangeOrderRequestAdapter l_changeOrderRequestAdapter =
            WEB3EquityPTSChangeOrderRequestAdapter.create(l_request);

        //create�������������l�ڍ�(���������������N�G�X�g�A�_�v�^)
        WEB3EquityChangeOrderUnitEntry l_changeOrderUnitEntry =
            WEB3EquityChangeOrderUnitEntry.createChangeOrderUnitEntry(l_changeOrderRequestAdapter);

        //���������������e(long, �������������l�ڍ�, String, String, ����)
        //�����h�c : (PTS)�������������������N�G�X�g�A�_�v�^.get����ID( )
        //�������������l�ڍ� : ���������������������l�ڍ�
        //�،���ЃR�[�h : this.get�⏕����( ).getInstitution( ).�،���ЃR�[�h
        //�����`���l�� : this.get���O�C���`���l��( )
        //���� : this.get�㗝���͎�( )
        WEB3EquityChangeOrderSpec l_changeOrderSpec =
            new WEB3EquityChangeOrderSpec(
                l_changeOrderRequestAdapter.getRequestOrderId(),
                l_changeOrderUnitEntry,
                this.getSubAccount().getInstitution().getInstitutionCode(),
                this.getLoginChannel(),
                this.getTrader());

        //createPTS�����������e( )
        WEB3EquityNewCashBasedOrderSpec l_orderSpec = l_changeOrderSpec.createPTSOrderSpec();

        //validatePTS��������(�⏕����, ���������������e)
        //�⏕���� : this.get�⏕����( )
        //���������������e : �����������������������e
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPTSOrderManager l_equityPTSOrderManager =
            (WEB3EquityPTSOrderManager)l_tradingModule.getOrderManager();
        EqTypeOrderValidationResult l_validationResult =
            l_equityPTSOrderManager.validatePTSChangeOrder(
                this.getSubAccount(),
                l_changeOrderSpec);
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_validationResult.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_validationResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        //���������������e.get�����������P��( )
        OrderUnit l_orderUnit = l_changeOrderSpec.getOrgChangeOrderUnit();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3EquityTradedProduct l_tradedProduct = null;
        Market l_market = null;
        try
        {
            WEB3EquityProduct l_product =
                (WEB3EquityProduct)l_productManager.getProduct(l_orderUnitRow.getProductId());
            l_market = l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            //getTradedProduct
            //�،���� : this.get�⏕����( ).getInstitution( )
            //�����R�[�h : ���������������e.get�����������P��( ).����ID�ɊY�����銔������.�����R�[�h
            //�s��R�[�h : ���������������e.get�����������P��( ).�s��ID�ɊY������s��}�X�^.�s��R�[�h
            l_tradedProduct =
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(
                    this.getSubAccount().getInstitution(),
                    l_product.getProductCode(),
                    l_market.getMarketCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�萔�� : �����������e.get�萔��( )
        WEB3GentradeCommission l_commission = l_orderSpec.getCommission();

        //calc�T�Z��n���
        //�萔�� : �����������e.get�萔��( )
        //�w�l : �����������e.getLimitPrice( )
        //�iW�w�l�j�����w�l : �����������e.get�iW�w�l�j�����w�l( )
        //�t�w�l��l : �����������e.get�t�w�l��l( )
        //���s���� : �����������e.getExecConditionType( )
        //�iW�w�l�j���s���� : �����������e.get�iW�w�l�j���s����( )
        //�l�i���� : �����������e.get�l�i����( )
        //�������� : �����������e.get��������( )
        //�m�F���擾���� : ���N�G�X�g.�m�F���P�� 
        //is�X�g�b�v�����L�� : �������������l�ڍ�.is�X�g�b�v�����L��( )
        //�⏕���� : this.get�⏕����( )
        //������� : �g���v���_�N�g�}�l�[�W��.getTradedProduct( )
        //���� : �����������e.getQuantity( )
        //is������ : �����������e.isSellOrder( )
        //��萔�� : ���������������e.get�����������P��( ).��萔��
        //���v�����z : ���������������e.get�����������P��( ).���v�����z
        //isSkip���z�`�F�b�N : false�i�Œ�j
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice =
            l_equityPTSOrderManager.calcEstimateDeliveryAmount(
                l_commission,
                l_orderSpec.getLimitPrice(),
                l_orderSpec.getWLimitPriceChange(),
                l_orderSpec.getStopLimitPriceBasePrice(),
                l_orderSpec.getExecConditionType(),
                l_orderSpec.getWlimitExecCondType(),
                l_orderSpec.getPriceConditionType(),
                l_orderSpec.getOrderCond(),
                l_request.checkPrice,
                l_changeOrderUnitEntry.isStopOrderEnable(),
                this.getSubAccount(),
                l_tradedProduct,
                l_orderSpec.getQuantity(),
                l_orderSpec.isSellOrder(),
                l_orderUnitRow.getExecutedQuantity(),
                l_orderUnitRow.getExecutedAmount(),
                false);

        //set�����P��(double)
        //�����P�� : �T�Z��n����v�Z����.get�v�Z�P��( )
        l_orderSpec.setOrderUnitPrice(l_estimatedDeliveryPrice.getCalcUnitPrice());

        //set�T�Z��n���(double)
        //�T�Z���z : �T�Z��n����v�Z����.get�T�Z��n���( )
        l_orderSpec.setEstimateDeliveryAmount(l_estimatedDeliveryPrice.getEstimateDeliveryAmount());

        //validatePTS�s��ʎ���\������z(���X, �s��, double)
        //���X : this.get�⏕����( ).get����X( )
        //�s�� : �g�����Z�I�u�W�F�N�g�}�l�[�W��.getMarket( )
        //�@@�mgetMarket( )�̈����n
        //�@@�@@�s��ID : �����Ώۂ̒����P��.�s��ID
        //�S��������� : �萔��.get���o��v�Z�p���( )
        l_equityPTSOrderManager.validatePTSMarketMaxHandlingPrice(
            this.getSubAccount().getWeb3GenBranch(),
            l_market,
            l_commission.getExpensesCalcAmount());

        //�������������C���^�Z�v�^(String, ����)
        //�����o�H�敪 : ���O�C���Z�b�V�������擾���������ڒl
        //�㗝���͎� : this.get�㗝���͎�( )
        OpLoginSecurityService l_securityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        String l_strOrderRootDiv =
            l_securityService.getSessionProperty(
                WEB3SessionAttributeDef.ORDER_ROOT_DIV);
        WEB3EquityOrderManagerChangeOrderEventInterceptor l_changeOrderEventInterceptor =
            new WEB3EquityOrderManagerChangeOrderEventInterceptor(
                l_strOrderRootDiv,
                (WEB3GentradeTrader)this.getTrader());

        //set�����������e(�����������e)
        //�����������e : ���������������e.createPTS�����������e( )
        l_changeOrderEventInterceptor.setEquityOrderSpec(l_orderSpec);

        //validate����]��
        //�⏕���� : this.get�⏕����( )
        //�������e�C���^�Z�v�^ : ���������������������C���^�Z�v�^
        //�������e : ���������������e
        //������� : �����Ώۂ̒����P��.�������
        //�]�͍X�V�t���O : true�i�������j
        WEB3TPTradingPowerService l_tradingpowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        Object[] l_changeOrderEventInterceptors = {l_changeOrderEventInterceptor};
        Object[] l_orderSpecs = {l_changeOrderSpec};

        WEB3TPTradingPowerResult l_tpResult =
            l_tradingpowerService.validateTradingPower(
                this.getSubAccount(),
                l_changeOrderEventInterceptors,
                l_orderSpecs,
                l_orderUnit.getOrderType(),
                true);

        //throw���������]�̓G���[�ڍ׏��
        //����]�͌��ʁF�@@����]�̓T�[�r�X.validate����]��( )
        //������ʁF�@@�������i�����������e.isSellOrder() == true�j�̏ꍇ�A�h�����������h���Z�b�g�B
        //            �������i�����������e.isSellOrder() == false�j�̏ꍇ�A�h�����������h���Z�b�g����B
        //���������F�@@�����������e.getQuantity( )
        OrderTypeEnum l_orderType = null;
        boolean l_blnIsSellOrder = l_orderSpec.isSellOrder();
        if (l_blnIsSellOrder)
        {
            l_orderType = OrderTypeEnum.EQUITY_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.EQUITY_BUY;
        }
        l_equityPTSOrderManager.throwEquityTpErrorDetailInfo(
            l_tpResult,
            l_orderType,
            l_orderSpec.getQuantity());

        //setThreadLocalPersistenceEventInterceptor
        l_equityPTSOrderManager.setThreadLocalPersistenceEventInterceptor(l_changeOrderEventInterceptor);

        //submit����������������
        //�⏕���� : this.get�⏕����( )
        //���������������e : ���������������e
        //����p�X���[�h : ���N�G�X�g.�Ïؔԍ�
        //isSkip�����R�� : true�i�X�L�b�v����j
        EqTypeOrderSubmissionResult l_submitResult =
            l_equityPTSOrderManager.submitChangeOrder(
                this.getSubAccount(),
                l_changeOrderSpec,
                l_request.password,
                true);
        if (l_submitResult.getProcessingResult().isFailedResult())
        {
            log.debug(STR_METHOD_NAME + " __Error[���������X�V]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                l_submitResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_submitResult.getProcessingResult().getErrorInfo().getErrorMessage());
        }

        //createResponse
        WEB3EquityChangeCompleteResponse l_changeCompleteResponse =
            (WEB3EquityChangeCompleteResponse)l_request.createResponse();

        //is�C���T�C�_�[�x���\��(�⏕����, long)
        //�⏕�����F�@@this.get�⏕����( )
        //����ID�F�@@�����Ώۂ̒����P��.����ID
        boolean l_blnIsInsiderMessageSuspension =
            l_equityPTSOrderManager.isInsiderMessageSuspension(
                this.getSubAccount(),
                l_orderUnitRow.getProductId());

        //�X�V����
        l_changeCompleteResponse.lastUpdatedTimestamp = l_orderUnitRow.getLastUpdatedTimestamp();

        //���ʔԍ�
        l_changeCompleteResponse.orderActionId = String.valueOf(l_orderUnitRow.getOrderId());

        //�C���T�C�_�[�x���\���t���O
        l_changeCompleteResponse.insiderWarningFlag = l_blnIsInsiderMessageSuspension;

        //�A�������ݒ�t���O
        l_changeCompleteResponse.succSettingFlag = false;

        //�����L������
        l_changeCompleteResponse.expirationDate = l_changeOrderRequestAdapter.getOrderExpirationDate();

        log.exiting(STR_METHOD_NAME);
        return l_changeCompleteResponse;
    }

    /**
     * PTS�������������������������s����B <BR>
     * <BR>
     * �i�V�X�e���������j�K�C�h 4.4.�Ɩ����W�b�N�@@�Q�Ɓj <BR>
     * <BR>
     * �P�j�@@���{���\�b�h���� <BR>
     * �@@�|���N�G�X�g�f�[�^�̌^���u�����������������m�F���N�G�X�g�v�̏ꍇ�́A<BR>
     * �@@�@@this.validate��������( )���R�[������B  <BR>
     * �@@�|���N�G�X�g�f�[�^�̌^���u�����������������������N�G�X�g�v�̏ꍇ�́A<BR>
     * �@@�@@this.submit��������( )���R�[������B  <BR>
     * <BR>
     * �Q�j�@@���\�b�h�̖߂�l��ԋp����B <BR>
     * @@param l_request - (���̓f�[�^)<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 475D0E65034E
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //���N�G�X�g�f�[�^�̌^���u�����������������m�F���N�G�X�g�v�̏ꍇ
        if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            l_response = this.validateChangeOrder((WEB3EquityChangeConfirmRequest)l_request);
        }
        //���N�G�X�g�f�[�^�̌^���u�����������������������N�G�X�g�v�̏ꍇ
        else if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            l_response = this.submitChangeOrder((WEB3EquityChangeCompleteRequest)l_request);
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
}
@
