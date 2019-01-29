head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�ԍϒ����T�[�r�X����(WEB3FuturesSettleContractOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 羉s (���u) �V�K�쐬
                 : 2006/07/28 �юu�� (���u) �d�l�ύX�@@���f��486
Revesion History : 2007/06/21 �����F(���u) ���f��726 743
Revesion History : 2007/11/20 ��іQ (���u)�d�l�ύX ���f��810
Revesion History : 2007/11/28 ��іQ (���u)Java�\�[�X�i��{�݌v�ƍ����Ă��Ȃ������j011
Revesion History : 2008/03/13 ���� (���u)�d�l�ύX ���f��840,841,842
Revesion History : 2008/03/14 ���� (���u)�d�l�ύX ���f��853
Revesion History : 2008/03/17 ���� (���u)�d�l�ύX ���f��854
Revesion History : 2008/04/14 �����F (���u)�d�l�ύX ���f��873 876 878
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�敨�ԍϒ����T�[�r�XImpl)<BR>
 * �����w���敨�ԍϒ����T�[�r�X�����N���X
 * @@author 羉s
 * @@version 1.0
 */
public class WEB3FuturesSettleContractOrderServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesSettleContractOrderService
{

    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesSettleContractOrderServiceImpl.class);

    /**
     * @@roseuid 40F7A2CE0213
     */
    public WEB3FuturesSettleContractOrderServiceImpl()
    {

    }

    /**
     * �����w���敨�ԍϒ����T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́Asubmit����()<BR>
     * ���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8C21D03D4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3FuturesCloseMarginConfirmRequest) //validate����
        {
            l_response = validateOrder((WEB3FuturesCloseMarginConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3FuturesCloseMarginCompleteRequest) //submit����
        {
            l_response = submitOrder((WEB3FuturesCloseMarginCompleteRequest) l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        //log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate����)<BR>
     * �����w���敨�̕ԍϔ����R�����s���B<BR>
     * <BR>
     * �u�i�敨�ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3FuturesCloseMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8C21E000C
     */
    protected WEB3FuturesCloseMarginConfirmResponse validateOrder(WEB3FuturesCloseMarginConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3FuturesCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3FuturesCloseMarginConfirmResponse l_response = (WEB3FuturesCloseMarginConfirmResponse) l_request.createResponse();

        //�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();
        //�㗝���͎҂̈��҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();

        // create���N�G�X�g�A�_�v�^
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter = createRequestAdapter(l_request);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();

        // create�ԍό��ʃG���g��
        SettleContractEntry[] l_settleContractEntries = createSettleContractEntry(
            l_requestAdapter, l_request.closeMarginContractUnits);
        // ���ʃI�u�W�F�N�g���擾����B
        WEB3IfoContractImpl l_contractImpl = l_requestAdapter.getContract();

        //�敨OP�������擾����
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();

        // reset�����R�[�h
        WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProductImpl.getUnderlyingProductCode());

        //�敨OP��������I�u�W�F�N�g���擾����B
        TradedProduct l_tradecProduct = l_contractImpl.getTradedProduct();

        //���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        
        //set limitPrice
        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice = 0D;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //�w�l
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblwLimitPrice = 0D;
        }
        
        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("l_dblwLimitPrice = " + l_dblwLimitPrice);
        
        //�t�w�l��l�F
        double l_dblStopOrderBasePrice = 0D;
        
        //[���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.�t�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //�@@[���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.W�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //�iW�w�l�j���s�����F�@@
        //  �敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.W�w�l�p���s����)�̖߂�l
        IfoOrderExecutionConditionType l_wLimitExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //���񒍕��̒����P��ID�F�@@�敨OP�f�[�^�A�_�v�^.get���񒍕���
        //  �����P��ID(���N�G�X�g�f�[�^.���������敪)
        Long l_firstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώ�
        //   �t���O(���N�G�X�g�f�[�^.���������敪, �⏕�����ɊY�����镔�X.���XID)
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getWeb3GenBranch().getBranchId());

        //OP�����}�l�[�W��
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�s��
        Market l_market = l_ifoProductImpl.getPrimaryMarket();

        //�����������F�@@OP�����}�l�[�W��.get�����L������(
        //���N�G�X�g�f�[�^.�����L������,�敨OP����.�����R�[�h,�s��.getMarketCode(),�h�敨�h)�̖߂�l
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_market.getMarketCode(),
            WEB3FuturesOptionDivDef.FUTURES);

        //�ԍϒ������e�I�u�W�F�N�g�𐶐�����B
        log.debug("createSettleContractOrderSpec");
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_dblLimitPrice,
                l_executionConditionType,
                l_datExpirationDate,
                l_settleContractEntries,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblwLimitPrice,
                l_wLimitExecutionConditionType,
                l_request.expirationDateType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //�敨OP�ԍϒ����̔����R�������{����B
        // validate�敨�ԍϒ���
        // [����]
        // �⏕�����F�@@get�⏕����()�̖߂�l
        // �ԍϒ������e�F�@@create�ԍϒ������e()�̖߂�l
        // ���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        NewOrderValidationResult l_newOrderValidationResult = validateFuturesSettleContractOrder(
            l_subAccount, l_settleContractOrderSpec, l_requestAdapter);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            //��O���X���[����
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //���ʂ̍��v���ʂ��擾����B
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
        log.debug("l_dblTotalQuantity = " + l_dblTotalQuantity);

        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_commission.setOrderChannel(this.getLoginChannel());

        //�萔��.�،����ID = �⏕����.get�،����()
        log.debug("setInstitutionCode: " + l_subAccount.getInstitution().getInstitutionCode());
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //�萔��.���XID = �⏕����.get����X()
        log.debug("setBranchId: " + l_subAccount.getWeb3GenBranch().getBranchId());
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //������
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());
        log.debug("l_tsOrderBizDate = " + l_tsOrderBizDate);

        //�萔��.������ = ������ԊǗ�.get������()
        l_commission.setOrderBizDate(l_tsOrderBizDate);

        //�萔��.����R�[�h(SONAR) = �h52�F�ԍρh
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //�萔��.�萔�����i�R�[�h = �h50�F�����w���敨�h
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);

        //�ٍϋ敪���Z�b�g����B
        l_commission.setPayType(WEB3PayTypeDef.OTHER);
        
        //is�w�l���Z�b�g����B
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());
        
        //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImpl.getProduct()).getUnderlyingProductCode());

        //�萔��.���v��敪 = this.get���v��敪�̖߂�l
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractEntries, l_requestAdapter));

        //�萔��.���� = �ԍϒ������e.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        //�T�Z���ϑ��v���v�Z����B
        // [����] 
        // �萔���F�@@�萔���I�u�W�F�N�g
        // �w�l�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l.get�P��()
        // �⏕�����F�@@�⏕�����I�u�W�F�N�g
        // �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
        // �ԍό��ʃG���g��[]�F  create�ԍό��ʃG���g���̖߂�l
        // ���ʁF �ԍϒ������e.getTotalQuantity()
        // ���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmount = getEstimateSettlementIncome(
            l_commission,
            l_requestAdapter.getPrice(),
            l_subAccount,
            (WEB3IfoTradedProductImpl)l_tradecProduct,
            l_settleContractEntries,
            l_settleContractOrderSpec.getTotalQuantity(),
            l_requestAdapter);

        // create���ʖ���
        //[����]
        // �ԍό��ʃG���g���F�@@create�ԍό��ʃG���g��()�̖߂�l
        // ���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        // �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
        WEB3FuturesOptionsContractUnit[] l_contractUnits = createContractUnit(
            l_settleContractEntries, l_requestAdapter, (WEB3IfoTradedProductImpl)l_tradecProduct);

        //�V�K�����ԍ����̔Ԃ���B
        long l_lngOrderId = l_orderMgr.createNewOrderId();

        //set�P��(�敨�ԍϒ������N�G�X�g�A�_�v�^, WEB3GenResponse)
        setPrice(l_requestAdapter, l_response);

        //���X�|���X.����ID = OP�����}�l�[�W��.createNewOrderId()�̖߂�l
        l_response.orderId = "" + l_lngOrderId;

        //���X�|���X.���ʖ��� = create���ʖ��ׂ̖߂�l
        l_response.contractUnits = l_contractUnits;
        log.debug("l_response.contractUnits = " + l_contractUnits[0].contractExecPrice);
        //���X�|���X.�T�Z���ϑ��v = �T�Z���ϑ��v�v�Z����.�T�Z��n���
        l_response.estimatedSettleIncome = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmount.getEstimateDeliveryAmount());
        log.debug("l_response.estimatedSettleIncome = " + l_estimateDeliveryAmount.getEstimateDeliveryAmount());

        //���X�|���X.�萔���R�[�X = �T�Z��n����v�Z����.�萔���R�[�X
        l_response.commissionCourse = l_estimateDeliveryAmount.getCommissionCourse();
        
        //���X�|���X.�萔�� = �T�Z��n����v�Z����.�萔��
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmount.getCommission());
        
        //���X�|���X.�萔������� = �T�Z��n����v�Z����.�萔�������
        l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_estimateDeliveryAmount.getCommissionTax());
        
        //���X�|���X.����I���x������ = ������ԊǗ�.get�s��ǌx���w��()�̖߂�l
        l_response.messageSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES);
        log.debug("l_response.messageSuspension = " + WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.FUTURES));
        //���X�|���X.�m�F���P�� = �T�Z��n����v�Z����.�v�Z�P��
        double l_dblCalcUnitPrice = l_estimateDeliveryAmount.getCalcUnitPrice();
        if (Double.isNaN(l_dblCalcUnitPrice))
        {
            l_dblCalcUnitPrice = 0D;
        }
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
        log.debug("l_response.checkPrice = " + l_estimateDeliveryAmount.getEstimateDeliveryAmount());
        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_response.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);
        log.debug("l_response.checkDate = " + l_datOrderBizDate);

        //���X�|���X.�����L������ = �ԍϒ������e.����������
        l_response.expirationDate = l_settleContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (submit����)<BR>
     * �����w���敨�̕ԍϒ�����o�^����B<BR>
     * <BR>
     * �u�i�敨�ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * @@return WEB3FuturesCloseMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8C21E002B
     */
    protected WEB3FuturesCloseMarginCompleteResponse submitOrder(WEB3FuturesCloseMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3FuturesCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        WEB3FuturesCloseMarginCompleteResponse l_response;

        //�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount) this.getSubAccount();

        //�㗝���͎҂̈��҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();

        // create���N�G�X�g�A�_�v�^
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter = createRequestAdapter(l_request);

        // create�ԍό��ʃG���g��
        SettleContractEntry[] l_settleContractOrderEntries = createSettleContractEntry(
            l_requestAdapter, l_request.closeMarginContractUnits);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl) l_tradingModule.getOrderManager();

        //���ʃI�u�W�F�N�g���擾����B
        WEB3IfoContractImpl l_contractImp = l_requestAdapter.getContract();

        //�敨OP�������擾����
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImp.getProduct();

        // reset�����R�[�h
        WEB3GentradeTradingTimeManagement.resetProductCode(l_ifoProductImpl.getUnderlyingProductCode());

        //�敨��������I�u�W�F�N�g���擾����B
        TradedProduct l_tradecProduct = l_contractImp.getTradedProduct();

        //get������
        //���N�G�X�g�f�[�^.�m�F��������!=null�̏ꍇ�A�R�[���B
        Date l_datOrderBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        //���s�����F�@@�敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)�̖߂�l
        IfoOrderExecutionConditionType l_executionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        
        //set limitPrice
        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice;

        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {                
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }
        else
        {
            l_dblwLimitPrice = 0D;
        }            
        
        log.debug("l_dblLimitPrice = " + l_dblLimitPrice);
        log.debug("l_dblwLimitPrice = " + l_dblwLimitPrice);

        //�t�w�l��l�F
        double l_dblStopOrderBasePrice = 0D;
        
        //[���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.�t�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        
        //�@@[���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ]
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //���N�G�X�g�f�[�^.W�w�l�p���������P��
            l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        
        //�iW�w�l�j���s�����F�@@
        //  �敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.W�w�l�p���s����)�̖߂�l
        IfoOrderExecutionConditionType l_wLimitExecutionConditionType = 
            WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
        
        //���񒍕��̒����P��ID�F�@@�敨OP�f�[�^�A�_�v�^.get���񒍕���
        //  �����P��ID(���N�G�X�g�f�[�^.���������敪)
        Long l_firstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);

        //�[��O�J�z�Ώۃt���O�F�@@�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώ�
        //   �t���O(���N�G�X�g�f�[�^.���������敪, �⏕�����ɊY�����镔�X.���XID)
        boolean l_blnEveningSessionCarryOverFlag =
            WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_subAccount.getWeb3GenBranch().getBranchId());

        //OP�����}�l�[�W��
        WEB3OptionOrderManagerImpl l_orderManager =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�s��
        Market l_market = l_ifoProductImpl.getPrimaryMarket();

        //�����������F�@@OP�����}�l�[�W��.get�����L������(
        //���N�G�X�g�f�[�^.�����L������,�敨OP����.�����R�[�h,�s��.getMarketCode(),�h�敨�h)�̖߂�l
        Date l_datExpirationDate = l_orderManager.getExpirationDate(
            l_request.expirationDate,
            l_ifoProductImpl.getProductCode(),
            l_market.getMarketCode(),
            WEB3FuturesOptionDivDef.FUTURES);

        //�ԍϒ������e�I�u�W�F�N�g�𐶐�����B
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_trader,
                l_dblLimitPrice,
                l_executionConditionType,
                l_datExpirationDate,
                (SettleContractEntry[]) l_settleContractOrderEntries,
                l_request.orderCondType,
                l_dblStopOrderBasePrice,
                l_dblwLimitPrice,
                l_wLimitExecutionConditionType,
                l_request.expirationDateType,
                l_firstOrderUnitId,
                l_blnEveningSessionCarryOverFlag);

        //�敨�ԍϒ����̔����R�������{����B
        // validate�敨�ԍϒ���
        // [����]
        // �⏕�����F�@@get�⏕����()�̖߂�l
        // �ԍϒ������e�F�@@create�ԍϒ������e()�̖߂�l
        // ���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        NewOrderValidationResult l_newOrderValidationResult = validateFuturesSettleContractOrder(
            l_subAccount, l_settleContractOrderSpec, l_requestAdapter);
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            //��O���X���[����
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(), 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        //���ʂ̍��v���ʂ��擾����B
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();

        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_commission.setOrderChannel(this.getLoginChannel());

        //�萔��.�،����ID = �⏕����.get�،����()
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //�萔��.���XID = �⏕����.get����X()
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //�萔��.������ = ������ԊǗ�.get������()
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        l_commission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));

        //�萔��.����R�[�h(SONAR) = �h52�F�ԍρh
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //�萔��.�萔�����i�R�[�h = �h50�F�敨�h
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);

        //�ٍϋ敪���Z�b�g����B
        l_commission.setPayType(WEB3PayTypeDef.OTHER);

        //is�w�l���Z�b�g����B
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());
        
        //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImp.getProduct()).getUnderlyingProductCode());

        //�萔��.���v��敪 = this.get���v��敪�̖߂�l
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractOrderEntries, l_requestAdapter));

        //�萔��.���� = �ԍϒ������e.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        // get�T�Z���ϑ��v
        // [����]
        // �萔���F�@@�萔���I�u�W�F�N�g
        // �w�l�F�@@���N�G�X�g�f�[�^.�m�F���P���@@(*1)
        // �⏕�����F�@@�⏕�����I�u�W�F�N�g
        // �敨OP��������F�@@�敨OP��������I�u�W�F�N�g
        // �ԍό��ʃG���g��[]�F   create�ԍό��ʃG���g���̖߂�l
        // ���ʁF �ԍϒ������e.getTotalQuantity()
        // ���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        // (*1)���N�G�X�g.�m�F���P��==null�̏ꍇ�A��Ɉȉ��̏������s���B
        // ���N�G�X�g.�m�F���P���Fcreate���N�G�X�g�A�_�v�^()�̖߂�l.get�P��()
        double l_dblPrice = 0;
        if (l_request.checkPrice == null)
        {
            l_dblPrice = l_requestAdapter.getPrice();
        }
        else
        {
            l_dblPrice = Double.parseDouble(l_request.checkPrice);
        }
        WEB3IfoEstimateDeliveryAmountCalcResult l_dblEstimateDeliveryAmount =
            getEstimateSettlementIncome(
                l_commission,
                l_dblPrice,
                l_subAccount,
                (WEB3IfoTradedProductImpl)l_tradecProduct,
                l_settleContractOrderEntries,
                l_settleContractOrderSpec.getTotalQuantity(),
                l_requestAdapter);

        // submit�ԍϒ���
        //[����]
        // ���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        // �⏕�����F�@@get�⏕����()�̖߂�l
        // �ԍϒ������e�F�@@create�ԍϒ������e()�Ő��������������e�B
        // ����ID�F�@@���N�G�X�g�f�[�^.����ID�@@(*1)
        // �萔���F�@@calc�T�Z���ϑ��v()�����̎萔���I�u�W�F�N�g
        // �T�Z��n����v�Z���ʁF�@@calc�T�Z���ϑ��v()�̖߂�l
        // (*1)�@@���N�G�X�g�f�[�^.����ID == null�̏ꍇ
        // �@@�@@�@@�@@createNewOrderId()�̖߂�l
        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_orderMgr.createNewOrderId();
        }
        submitSettleContractOrder(
            l_requestAdapter,
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngOrderId,
            l_commission,
            l_dblEstimateDeliveryAmount);

        l_response = (WEB3FuturesCloseMarginCompleteResponse) l_request.createResponse();
        //���X�|���X.�X�V���� = ���ݓ���(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();

        //���X�|���X.���ʔԍ� = 
        //�@@���N�G�X�g�f�[�^.����ID==null�̏ꍇ�A�敨�����}�l�[�W��.createNewOrderID()�̖߂�l�B
        //  ���N�G�X�g�f�[�^.����ID!=null�̏ꍇ�A���N�G�X�g�f�[�^.����ID
        l_response.orderActionId = "" + l_lngOrderId;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍρjvalidate�����P�v<BR>
     * �u�i�敨�ԍρjsubmit�����P�v�Q��<BR>
     * <BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesSettleContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        return WEB3FuturesSettleContractOrderRequestAdapter.create(l_request);
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g�����쐬����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍρjvalidate�����P�v<BR>
     * �u�i�敨�ԍρjsubmit�����P�v�Q��<BR>
     * <BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_closeMarginContractUnits - (�ԍό��ʃI�u�W�F�N�g�̔z��)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return WEB3FuturesSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry(" +
            "WEB3FuturesSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        // [����]
        // �����P��ID = 0
        // �������ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.get��������()�߂�l
        // �ԍό���[] = �p�����[�^.�ԍό���
        SettleContractEntry[] l_settleContractOrderEntries = l_orderMgr.createSettleContractEntry(
            0, l_requestAdapter.getOrderQuantity(), l_closeMarginContractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }

    /**
     * (validate�敨�ԍϒ���)<BR>
     * �敨�ԍϒ����̔����R�������{����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍρjvalidate�����P�v<BR>
     * �u�i�敨�ԍρjsubmit�����P�v�Q��<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_settleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected NewOrderValidationResult validateFuturesSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateFuturesSettleContractOrder(" +
            "SubAccount, WEB3IfoSettleContractOrderSpec, WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        // [����]
        // �⏕�����F�@@get�⏕����()�̖߂�l
        // �ԍϒ������e�F�@@create�ԍϒ������e()�̖߂�l
        NewOrderValidationResult l_validateResult = l_orderMgr.validateFuturesSettleContractOrder(
            (WEB3GentradeSubAccount)l_subAccount, l_settleContractOrderSpec);

        log.exiting(STR_METHOD_NAME);
        return l_validateResult;
    }

    /**
     * (get�T�Z���ϑ��v)<BR>
     * �T�Z���ϑ��v���擾����B<BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍρjvalidate�����P�v<BR>
     *�u�i�敨�ԍρjsubmit�����P�v�Q��<BR>
     * <BR>
     * @@param l_commision - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP�������<BR>
     * @@param l_settleContractEntry - (�ԍό��ʃG���g���̔z��)<BR>
     * �ԍό��ʃG���g���̔z��<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return WEB3IfoEstimateDeliveryAmountCalcResult
     * @@throws WEB3BaseException
     */
    protected WEB3IfoEstimateDeliveryAmountCalcResult getEstimateSettlementIncome(
        WEB3GentradeCommission l_commision,
        double l_dblLimitPrice,
        SubAccount l_subAccount,
        WEB3IfoTradedProductImpl l_ifoTradedProduct,
        SettleContractEntry[] l_settleContractEntry,
        double l_dblQuantity,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimateSettlementIncome(" +
            "WEB3GentradeCommission, double, SubAccount, WEB3IfoTradedProductImpl," +
            "SettleContractEntry[], double, WEB3FuturesSettleContractOrderRequestAdapter)";

        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();

        // [����]
        // �萔���F�@@�p�����[�^.�萔��
        // �w�l�F�@@�p�����[�^.�w�l
        // �⏕�����F�@@�p�����[�^.�⏕����
        // �敨OP��������F�@@�p�����[�^.�敨OP�������
        // �ԍό��ʃG���g��[]�F�@@�p�����[�^.�ԍό��ʃG���g��[]
        // ���ʁF �p�����[�^.����
        // �����F
        // �@@[���N�G�X�g�A�_�v�^.get����()�̖߂�l.isLong() == true�̏ꍇ]
        // �@@�@@SideEnum.BUY
        // �@@[�ȊO]
        // �@@�@@SideEnum.SELL
        // isSkip���z�`�F�b�N�F  false
        SideEnum l_dealing = null;
        if (l_requestAdapter.getContract().isLong())
        {
            l_dealing = SideEnum.BUY;
        }
        else
        {
            l_dealing = SideEnum.SELL;
        }

        WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult = l_orderMgr.calcEstimateSettlementIncome(
            l_commision,
            l_dblLimitPrice,
            (WEB3GentradeSubAccount)l_subAccount,
            l_ifoTradedProduct,
            l_settleContractEntry,
            l_dblQuantity,
            l_dealing,
            false);

        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }

    /**
     * (create���ʖ���)<BR>
     * �ԍό��ʃG���g����茚�ʖ��ׂ̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * �Q�j�@@�������擾����B<BR>
     * �@@�Q�|�P�j�@@�敨OP���������擾����B<BR>
     * �@@�@@�p�����[�^.�敨OP�������.get�������(null)��call����B<BR>
     * �@@�Q�|�Q�j�@@�������擾����B<BR>
     * �@@�@@�Q�|�P�j�̖߂�l��null�̏ꍇ�Aget����()��call����B<BR>
     * �@@�@@�@@��null�̏ꍇ�A������0�Ƃ���B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�ԍό��ʃG���g���̗v�f�����A<BR>
     * �@@�ȉ��̏��������{����B<BR>
     * �@@�R�|�P�j�@@�����Ώۂ̗v�f.getContractId()�̖߂�l�ɂ��A<BR>
     * �@@�@@�@@�@@�@@�@@�敨OP���ʃC���X�^���X�𐶐�����B<BR>
     * <BR>
     * �@@�R�|�Q�j�@@���ʖ��׃C���X�^���X�𐶐�����B<BR>
     * �R�|�R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@ID = ���ʂh�c<BR>
     * �@@���N���� = ����.getOpenDate()<BR>
     * �@@���P�� = ����.getContractPrice()<BR>
     * �@@���ʐ� = ����.getQuantity() - ����.getLockedQuantity()<BR>
     * �@@�������z = ����.get�������z(����(*1))<BR>
     * �@@���萔�� = ����.get���萔��(����(*1)) + ����.get���萔�������(����(*1))<BR>
     * �@@���v = ����.get�]�����v(�ԍϒP��(*2), ����(*1))<BR>
     * �@@���v�i���o��j = ����.get�]�����v() - (����.get���萔��() + ����.get���萔�������())<BR>
     * �@@�ԍϐ��� = SettleContractEntry.getQuantity()�i*�ԍϐ��ʁj<BR>
     * �@@�ԍϖ�萔�� = NULL<BR>
     * �@@���Ϗ��� = �iSettleContractEntry��index+1�j<BR>
     * �@@����敪 = ����.����敪 <BR>
     * <BR>
     * @@param l_settleContractEntry - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP�������<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntry,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_ifoTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnit(" +
            "SettleContractEntry[], WEB3FuturesSettleContractOrderRequestAdapter, WEB3IfoTradedProductImpl)";

        log.entering(STR_METHOD_NAME);

        // �P�j�@@ArrayList�𐶐�����B
        List l_lisContractUnits = new ArrayList();

        // �Q�j�@@�������擾����B
        //�@@�Q�|�P�j�@@�敨OP���������擾����B
        //�@@�p�����[�^.�敨OP�������.get�������(null)��call����B
        WEB3IfoProductQuote l_productQuote = l_ifoTradedProduct.getCurrentInfo(null);
        double l_dblCurrentPrice = 0;
        // �Q�|�Q�j�@@�������擾����B
        // �@@�Q�|�P�j�̖߂�l��null�̏ꍇ�Aget����()��call����B
        // �@@�@@��null�̏ꍇ�A������0�Ƃ���B
        if (l_productQuote != null)
        {
            l_dblCurrentPrice = l_productQuote.getCurrentPrice();
        }

        // �R�j�@@�p�����[�^.�ԍό��ʃG���g���̗v�f�����A
        int l_intSettleContractEntry = l_settleContractEntry.length;
        for (int i = 0; i < l_intSettleContractEntry; i++)
        {
            try
            {
                // �R�|�P�j�@@�����Ώۂ̗v�f.getContractId()�̖߂�l�ɂ��A
                //  �@@�@@�@@�@@�@@�敨OP���ʃC���X�^���X�𐶐�����B
                WEB3IfoContractImpl l_ifoContract = new WEB3IfoContractImpl(
                    l_settleContractEntry[i].getContractId());

                // �R�|�Q�j�@@���ʖ��׃C���X�^���X�𐶐�����B
                //���ʖ��׃I�u�W�F�N�g�𐶐�����B
                WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

                // �R�|�R�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
                // ID = ���ʂh�c
                l_contractUnit.id = WEB3StringTypeUtility.formatNumber(l_ifoContract.getContractId());

                // ���N���� = ����.getOpenDate()
                l_contractUnit.openDate = l_ifoContract.getOpenDate();

                // ���P�� = ����.getContractPrice()
                l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoContract.getContractPrice());

                // ���ʐ� = ����.getQuantity() - ����.getLockedQuantity()
                l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(
                    l_ifoContract.getQuantity() - l_ifoContract.getLockedQuantity());

                // �������z = ����.get�������z(����(*1))
                double l_dblQuantity = l_settleContractEntry[i].getQuantity();
                l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(
                    l_ifoContract.getContractExecutedAmount(l_dblQuantity));

                // ���萔�� = ����.get���萔��(����(*1)) + ����.get���萔�������(����(*1))
                double l_dblContractCommission = l_ifoContract.getContractCommission(l_dblQuantity);
                double l_dblContractCommissionTax = l_ifoContract.getContractCommissionConsumptionTax(
                    l_dblQuantity);
                BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
                BigDecimal l_bdContractCommissionTax = new BigDecimal(l_dblContractCommissionTax + "");
                l_contractUnit.contractCommission = WEB3StringTypeUtility.formatNumber(
                    l_bdContractCommission.add(l_bdContractCommissionTax).doubleValue());

                // ���v = ����.get�]�����v(�ԍϒP��(*2), ����(*1))
                double l_dblEvaluateIncome = l_ifoContract.getEvaluateIncome(l_dblCurrentPrice, l_dblQuantity);
                l_contractUnit.income = WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);

                // ���v�i���o��j = ����.get�]�����v() - (����.get���萔��() + ����.get���萔�������())
                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
                l_contractUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                    l_bdEvaluateIncome.subtract(l_bdContractCommission.add(l_bdContractCommissionTax)).doubleValue());

                // �ԍϐ��� = SettleContractEntry.getQuantity()�i*�ԍϐ��ʁj
                l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);

                // �ԍϖ�萔�� = NULL
                l_contractUnit.contractExecQuantity = null;

                // ���Ϗ��� = �iSettleContractEntry��index+1�j
                l_contractUnit.settlePriority = String.valueOf(i + 1);

                // ����敪 = ����.����敪
                IfoContractRow l_ifoContractRow = (IfoContractRow)l_ifoContract.getDataSourceObject();
                l_contractUnit.sessionType = l_ifoContractRow.getSessionType();

                // �R�|�S�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                l_lisContractUnits.add(l_contractUnit);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        log.exiting(STR_METHOD_NAME);

        return (WEB3FuturesOptionsContractUnit[])l_lisContractUnits.toArray(
            new WEB3FuturesOptionsContractUnit[l_lisContractUnits.size()]);
    }

    /**
     * (submit�ԍϒ���)<BR>
     * �敨�ԍϒ�����o�^����B<BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�i�敨�ԍρjsubmit�����Q�v�Q��<BR>
     * <BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_orderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_commision - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_amountCalcResult - (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z����<BR>
     * @@throws WEB3BaseException
     */
    protected void submitSettleContractOrder(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_orderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commision,
        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSettleContractOrder(" +
            "WEB3FuturesSettleContractOrderRequestAdapter, " +
            "SubAccount, " +
            "WEB3IfoSettleContractOrderSpec, " +
            "long, " +
            "WEB3GentradeCommission, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr = (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        //�C���^�Z�v�^�𐶐�����B
        WEB3IfoSettleContractUpdateInterceptor l_settleContractUpdateInterceptor =
            new WEB3IfoSettleContractUpdateInterceptor(l_orderSpec);

        //�C���^�Z�v�^.�萔�� = �icalc�T�Z���ϑ��v()�����̒ʏ�萔���I�u�W�F�N�g�j
        l_settleContractUpdateInterceptor.setCommision(l_commision);

        //�C���^�Z�v�^.�T�Z���ϑ��v�v�Z���� = �icalc�T�Z���ϑ��v()�̖߂�l�j
        l_settleContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

        WEB3FuturesCloseMarginCompleteRequest l_request = (WEB3FuturesCloseMarginCompleteRequest)
            l_requestAdapter.request;
        //�C���^�Z�v�^.�������� = ���N�G�X�g�f�[�^.���������敪
        l_settleContractUpdateInterceptor.setOrderCond(l_request.orderCondType);

        //�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderSpec.getOrderCond()))
        {
            //�C���^�Z�v�^.�����������Z�q = ���N�G�X�g�f�[�^.�����������Z�q�@@
            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);

            //�C���^�Z�v�^.�t�w�l��l�^�C�v = 0
            //l_settleContractUpdateInterceptor.setStopOrderBasePriceType("0");
            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);

            //�C���^�Z�v�^.�t�w�l��l = ���N�G�X�g�f�[�^.���������P��
            l_settleContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderSpec.getOrderCond()))
        {
            //�C���^�Z�v�^.�����������Z�q = ���N�G�X�g�f�[�^.�����������Z�q

            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);

            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);

            l_settleContractUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            //�C���^�Z�v�^.(W�w�l)�����w�l = �ԍϒ������e.(W�w�l)�����w�l
            l_settleContractUpdateInterceptor.setWLimitPriceChange(l_orderSpec.getWLimitPriceChange());
        }

        l_settleContractUpdateInterceptor.setSettleSequence(l_request.closingOrder);

        //�C���^�Z�v�^.����敪 = ������ԊǗ�.get����敪�̖߂�l
        l_settleContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_settleContractUpdateInterceptor);

        // [����]
        // �⏕�����F�@@�p�����[�^.�⏕����
        // �ԍϒ������e�F�@@�p�����[�^.�ԍϒ������e
        // �����h�c�F�@@�p�����[�^.����ID
        // ����p�X���[�h�F�@@�p�����[�^.���N�G�X�g�A�_�v�^.���N�G�X�g�f�[�^.�Ïؔԍ�
        // isSkip�����R���F�@@true
        OrderSubmissionResult l_result = l_orderMgr.submitSettleContractOrder(
            l_subAccount, //�⏕����
            l_orderSpec, //�ԍϒ������e
            l_lngOrderId, //�����h�c
            l_request.password, //����p�X���[�h
            true);

        if (l_result.getProcessingResult().isFailedResult())
        {
            //��O���X���[����
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�P��)<BR>
     * ����������return����B�i�J�������j<BR>
     *
     * @@param l_requestAdapter - (�敨�ԍϒ������N�G�X�g�A�_�v�^)<BR>
     * �敨�ԍϒ������N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�B<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        return;
    }

    /**
     * (get���v��敪)<BR>
     * ���v��敪���擾����B <BR>
     * <BR>
     * �敨�����}�l�[�W��.get���v��敪()��call����B<BR>
     * <BR>
     * [����] <BR>
     * �ԍό��ʃG���g���F�@@�p�����[�^.�ԍό��ʃG���g�� <BR>
     * @@param l_settleContractOrderEntries - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    protected String getDayTradeType(
        SettleContractEntry[] l_settleContractOrderEntries,
        WEB3FuturesSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3FuturesSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3FuturesOrderManagerImpl l_orderMgr =
            (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
        //�敨�����}�l�[�W��.get���v��敪()��call����B
        String l_strDayTradeType = l_orderMgr.getDayTradeType(l_settleContractOrderEntries);

        log.exiting(STR_METHOD_NAME);
        return l_strDayTradeType;
    }
}
@
