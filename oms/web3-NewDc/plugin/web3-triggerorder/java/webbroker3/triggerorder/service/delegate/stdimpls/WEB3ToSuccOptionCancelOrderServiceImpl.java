head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOptionCancelOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :�i�A���jOP��������T�[�rImpl�iWEB3ToSuccOptionCancelOrderServiceImpl.java�j
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/10 ���� (���u) �V�K�쐬 ���f��No.280
Revision History : 2008/05/15 ���z (���u) �d�l�ύX ���f��No.351
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.quoteadaptor.WEB3QuoteDataSupplierService;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccOpProductTypeDef;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsCancelConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionCancelOrderService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�i�A���jOP��������T�[�rImpl)<BR>
 * �i�A���jOP��������T�[�r�X�����N���X<BR>
 *
 * @@author ����
 * @@version 1.0
 */
public class WEB3ToSuccOptionCancelOrderServiceImpl extends WEB3OptionClientRequestService
    implements WEB3ToSuccOptionCancelOrderService
{

    /**
     * (���O�o�̓��[�e�B���e�B�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccOptionCancelOrderServiceImpl.class);

    /**
     * @@roseuid 47FDBE3F039A
     */
    public WEB3ToSuccOptionCancelOrderServiceImpl()
    {

    }

    /**
     * �i�A���jOP����T�[�r�X���������{����B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * �@@�@@[�i�A���j�����w���I�v�V������������m�F���N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.validate����()���R�[������B<BR>
     * �@@�@@[�i�A���j�����w���I�v�V������������������N�G�X�g�̏ꍇ] <BR>
     * �@@�@@�@@this.submit����()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A911B50395
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

        //[�i�A���j�����w���I�v�V������������m�F���N�G�X�g�̏ꍇ]
        //this.validate����()���R�[������B
        if (l_request instanceof WEB3SuccOptionsCancelConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccOptionsCancelConfirmRequest)l_request);
        }
        //[�i�A���j�����w���I�v�V������������������N�G�X�g�̏ꍇ] <BR>
        //this.submit����()���R�[������B
        else if (l_request instanceof WEB3SuccOptionsCancelCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccOptionsCancelCompleteRequest)l_request);
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
     * (validate����)<BR>
     * �i�A���jOP����̔����R�����s���B<BR>
     * <BR>
     * �u�i�i�A���jOP����T�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3SuccOptionsCancelConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A9167E00FB
     */
    protected WEB3SuccOptionsCancelConfirmResponse validateOrder(
        WEB3SuccOptionsCancelConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3SuccOptionsCancelConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̃`�F�b�N
        l_request.validate();

        //�⏕�������擾����
        SubAccount l_subAccount = this.getSubAccount();

        //�\�񒍕��P�ʂ��擾����
        //�y�����z
        //����ID�F�@@���N�G�X�g.ID
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManagerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);

        WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnit =
            l_toSuccOrderManagerImpl.getReserveIfoOrderUnit(l_lngOrderId);

        //�敨OP������������R�����s��
        //�y�����z
        //�⏕�����@@�@@�@@�F�@@get�⏕����()�̖߂�l
        //�\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
        l_toSuccOrderManagerImpl.validateIfoCancelOrder(
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoOrderUnit);

        //�\�񒍕��P�ʂ�茚�ʖ��ׂ��쐬����
        //�y�����z
        //�\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
        WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnit =
            l_toSuccOrderManagerImpl.createIfoContractUnitByOrder(l_ifoOrderUnit);

        //�s��ǌx���w�����擾����
        //�y�����z
        //���X�F�⏕����.get����X()
        //�敨�^�I�v�V�����敪�F�@@�h�I�v�V�����h
        String[] l_strTradeCloseSuspension =
            WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                ((WEB3GentradeSubAccount)l_subAccount).getWeb3GenBranch(),
                WEB3FuturesOptionDivDef.OPTION);

        //�敨OP�����I�u�W�F�N�g���擾����
        IfoProduct l_ifoProduct = (IfoProduct)l_ifoOrderUnit.getProduct();

        //�敨OP��������I�u�W�F�N�g���擾����B
        TradedProduct l_tradedProduct = l_ifoOrderUnit.getTradedProduct();

        //���������擾����
        //�y�����z
        //tradedProduct(��������j�F�@@get�������()�̖߂�l�̎�������I�u�W�F�N�g
        //realType�F
        //�ڋq = �⏕����.getMainAccount()
        //�ڋq.is���A���ڋq()==true�̏ꍇ�́h���A���h�A  false�̏ꍇ�́h20���f�B���C�h���Z�b�g�B
        WEB3IfoQuoteData l_quoteData = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3QuoteDataSupplierService l_supplierProvide =
            (WEB3QuoteDataSupplierService)l_finApp.getTradingModule(
                ProductTypeEnum.IFO).getQuoteDataSupplierService();
        RealType l_realType = null;
        WEB3GentradeMainAccount  l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        if (l_mainAccount.isRealCustomer())
        {
            l_realType = RealType.REAL;
        }
        else
        {
            l_realType = RealType.DELAY;
        }
        try
        {
            l_quoteData = (WEB3IfoQuoteData)l_supplierProvide.getQuote(
                l_tradedProduct,
                l_realType);
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

        //���ݒl���擾����
        double l_dblCurrentPrice = l_quoteData.getCurrentPrice();

        //�O������擾����
        double l_dblChange = l_quoteData.getChange();

        //������Ԃ��擾����
        Timestamp l_tsCurrentPriceTime = l_quoteData.getCurrentPriceTime();

        //���������擾����
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        //���X�|���X�f�[�^����
        WEB3SuccOptionsCancelConfirmResponse l_response =
            (WEB3SuccOptionsCancelConfirmResponse)l_request.createResponse();

        IfoProductRow l_ifoProductRow = (IfoProductRow)l_ifoProduct.getDataSourceObject();

        //���X�|���X.����敪 = �敨OP�\�񒍕��P��.get���b�Z�[�W�p����敪()
        l_response.tradingType = l_ifoOrderUnit.getMsgTradingType();

        //���X�|���X.����s�� = �敨OP�\�񒍕��P��.get�s��()�̖߂�l.�s��R�[�h(SONAR)
        MarketRow l_marketRow = (MarketRow)l_ifoOrderUnit.getMarket().getDataSourceObject();
        l_response.marketCode = l_marketRow.getSonarMarketCode();

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        //���X�|���X.�w����� = get����()�̖߂�l.�����Y�����R�[�h
        l_response.targetProductCode = l_ifoProduct.getUnderlyingProductCode();

        //���X�|���X.���� = get����()�̖߂�l.����
        l_response.delivaryMonth = l_ifoProduct.getMonthOfDelivery();

        // ���X�|���X.�I�v�V�������i�敪 = get����()�̖߂�l.�I�v�V�������i�敪
        if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_ifoProductRow.getDerivativeType()))
        {
            l_response.opProductType = WEB3ToSuccOpProductTypeDef.PUT_OPTIONS;
        }
        else if ((IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_ifoProductRow.getDerivativeType())))
        {
            l_response.opProductType = WEB3ToSuccOpProductTypeDef.CALL_OPTIONS;
        }

        // ���X�|���X.�s�g���i = get����()�̖߂�l.�s�g���i
        l_response.strikePrice =  WEB3StringTypeUtility.formatNumber(l_ifoProductRow.getStrikePrice());

        //���X�|���X.�������� = �敨OP�\�񒍕��P��.��������
        double l_dblOrderQuantity = l_ifoOrderUnit.getQuantity();
        l_response.opOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);

        //���X�|���X.����萔�� = null
        l_response.partExecQuantity = null;

        //���X�|���X.�����P���敪 = �敨OP�\�񒍕��P��.get���b�Z�[�W�p�����P���敪()
        l_response.orderPriceDiv = l_ifoOrderUnit.getMsgOrderPriceDiv();

        //���X�|���X.�����P�� = �敨OP�\�񒍕��P��.get���b�Z�[�W�p�����P��()
        l_response.limitPrice = l_ifoOrderUnit.getMsgLimitPrice();

        //���X�|���X.���s���� = �敨OP�\�񒍕��P��.get���b�Z�[�W�p���s����()�i�����������ݒ肳���j
        l_response.execCondType = l_ifoOrderUnit.getMsgExecCondType();

        //���X�|���X.���������敪 = �敨OP�\�񒍕��P��.get���������敪()
        l_response.expirationDateType = l_ifoOrderUnit.getExpirationDateType();

        //���X�|���X.�����L������ = �敨OP�\�񒍕��P��.get���b�Z�[�W�p�����L������()
        l_response.expirationDate = l_ifoOrderUnit.getMsgExpirationDate();

        //���X�|���X.���������敪 = �敨OP�\�񒍕��P��.get���b�Z�[�W�p���������敪() �@@(�������Ȃ����ݒ肳���)
        l_response.orderCondType = l_ifoOrderUnit.getMsgOrderCondType();

        // ���X�|���X.�t�w�l�v���~�A��/�����Y���i = null
        l_response.stopPremium_underlyingAssets = null;
        //���X�|���X.�t�w�l�p���������P��  = null
        l_response.stopOrderCondPrice = null;
        //���X�|���X.�t�w�l�p�����������Z�q   = null
        l_response.stopOrderCondOperator = null;
        //���X�|���X.W�w�l�p���������P��    = null
        l_response.wlimitOrderCondPrice = null;
        //���X�|���X.W�w�l�p�����������Z�q   = null
        l_response.wlimitOrderCondOperator = null;
        // ���X�|���X.W�w�l�p�v���~�A��/�����Y���i
        l_response.wlimitPremium_underlyingAssets = null;
        //���X�|���X.W�w�l�p�����P���敪    = null
        l_response.wLimitOrderPriceDiv = null;
        //���X�|���X.W�w�l�p�����P��      = null
        l_response.wLimitPrice = null;
        //���X�|���X.W�w�l�p���s���� = null
        l_response.wlimitExecCondType = null;
        //���X�|���X.W�w�l�p�L����ԋ敪 = null
        l_response.wlimitEnableStatusDiv = null;
        //���X�|���X.W�w�l�p�֑ؑO�����P�� = null
        l_response.wlimitBefChgLimitPrice = null;
        //���X�|���X.W�w�l�p�֑ؑO���s���� = null
        l_response.wlimitBefChgExecCondType = null;
        //���X�|���X.�����������敪 = null
        l_response.orgOrderCondType = null;
        // ���X�|���X.���v���~�A��/�����Y���i   = null
        l_response.orgPremium_underlyingAssets = null;
        //���X�|���X.�����������P�� = null
        l_response.orgOrderCondPrice = null;
        //���X�|���X.�������������Z�q  = null
        l_response.orgCondOperator = null;
        //���X�|���X.���v�w�l�p�����P���敪 = null
        l_response.orgWLimitOrderPriceDiv = null;
        //���X�|���X.���v�w�l�p�����P�� = null
        l_response.orgWLimitPrice = null;
        //���X�|���X.���v�w�l�p���s���� = null
        l_response.orgWlimitExecCondType = null;

        //���X�|���X.�T�Z��n��� = �敨OP�\�񒍕��P��.�T�Z��n���
        l_response.estimatedPrice =
            WEB3StringTypeUtility.formatNumber(l_rsvIfoOrderUnitRow.getEstimatedPrice());

        //���X�|���X.����I���x������ = ������ԊǗ�.get�s��ǌx���w��()�̖߂�l
        l_response.messageSuspension = l_strTradeCloseSuspension;

        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_response.checkDate =  WEB3DateUtility.toDay(l_datOrderBizDate);

        //���X�|���X.���Ϗ��� = �敨OP�\�񒍕��P��.���Ϗ���
        l_response.closingOrder = l_rsvIfoOrderUnitRow.getClosingOrder();

        //���X�|���X.���ʖ���  = �i�A���jcreate���ʖ���ByOrder()�̖߂�l
        l_response.contractUnits = l_futuresOptionsContractUnit;

        //���X�|���X.���ݒl  = getCurrentPrice()�̖߂�l
        if (GtlUtils.Double.isZero(l_dblCurrentPrice))
        {
            l_response.currentPrice = null;
        }
        else
        {
            l_response.currentPrice = WEB3StringTypeUtility.formatNumber(l_dblCurrentPrice);
        }
        //���X�|���X.�O���� = getChange()�̖߂�l
        l_response.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(l_dblChange);

        //���X�|���X.������� = getCurrentPriceTime()�̖߂�l
        l_response.currentPriceTime = l_tsCurrentPriceTime;

        //���X�|���X.����敪 = �敨OP�\�񒍕��P��.����敪
        l_response.sessionType = l_rsvIfoOrderUnitRow.getSessionType();

        //���X�|���X.�P�������l��� = �敨OP�\�񒍕��P��.create�P�������l���()
        l_response.priceAdjustmentValueInfo = l_ifoOrderUnit.createSuccPriceAdjustmentValueInfo();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * �i�A���jOP����̒�����o�^����B<BR>
     * <BR>
     * �u�i�i�A���jOP����T�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g<BR>
     * @@return WEB3SuccOptionsCancelCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 47A916A203A9
     */
    protected WEB3SuccOptionsCancelCompleteResponse submitOrder(
        WEB3SuccOptionsCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder(WEB3SuccOptionsCancelCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����
        l_request.validate();

        //�������̃`�F�b�N���s��
        //�y�����z
        //�m�F���������F�@@���N�G�X�g�f�[�^.�m�F��������
        WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);

        //�⏕�������擾����
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //�敨OP�\�񒍕��P�ʂ��擾����
        //�y�����z
        //����ID�F�@@���N�G�X�g�f�[�^.ID
        WEB3ToSuccOrderManagerImpl l_toSuccOrderManagerImpl =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngOrderId = Long.parseLong(l_request.id);

        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            l_toSuccOrderManagerImpl.getReserveIfoOrderUnit(l_lngOrderId);

        //��������R�����s��
        //�y�����z
        //�⏕�����Fget�⏕����()�̖߂�l
        //�\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
        l_toSuccOrderManagerImpl.validateIfoCancelOrder(
            l_subAccount,
            l_toSuccIfoOrderUnitImpl);

        //�\�񒍕�����������s��
        //�y�����z
        //�⏕�����@@�@@�@@�F�@@get�⏕����()�̖߂�l
        //�\�񒍕��P�ʁF�@@get�敨OP�\�񒍕��P��()�̖߂�l
        //����p�X���[�h�F�@@���N�G�X�g�f�[�^.�Ïؔԍ�
        l_toSuccOrderManagerImpl.submitIfoCancelOrder(
            l_subAccount,
            l_toSuccIfoOrderUnitImpl,
            l_request.password);

        //���X�|���X�f�[�^�𐶐�����
        WEB3SuccOptionsCancelCompleteResponse l_response =
            (WEB3SuccOptionsCancelCompleteResponse)l_request.createResponse();

        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B

        //�X�V����    ���@@���ݓ����iGtlUtils.getSystemTimestamp()�j
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //���ʔԍ�    ���@@���N�G�X�g.ID�i���敨OP�\�񒍕��P��.����ID�j
        l_response.orderActionId = l_request.id;
        //�A�������ݒ�t���O   ���@@false�i�Œ�j
        l_response.succSettingFlag = false;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}


@
