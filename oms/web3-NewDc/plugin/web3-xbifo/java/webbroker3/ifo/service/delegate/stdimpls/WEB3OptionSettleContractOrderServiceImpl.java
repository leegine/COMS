head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionSettleContractOrderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : OP�ԍϒ����T�[�r�XImpl(WEB3OptionSettleContractOrderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/24 ����� (���u) �V�K�쐬
              001: 2004/07/22 ���Ō� (���u) WEB3OrderPriceDivDef��WEB3IfoOrderPriceDivDef�������ւ���
              002: 2004/07/22 ���Ō� (���u) WEB3ExecutionConditionDef��WEB3IfoExecCondTypeDef�������ւ���
              003: 2004/07/22 ���Ō� (���u) WEB3OrderingConditionDef��WEB3OrderingConditionDef�������ւ���
              004: 2004/08/09 ���Ō� (Sinocom) �Ή�����:�yWEB3-XBIFO-A-CD-0082�z
              005: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000079)��Ή�
              006: 2004/08/13 ������@@(���u) STBUG(IFO_ST-000114)��Ή�
              007: 2004/08/15 ������@@(���u) STBUG(IFO_ST-000083)��Ή�
              008: 2006/07/13 鰁@@(���u) �d�l�ύX ���f�� NO.460��Ή�
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.655
Revesion History : 2007/06/21 �Ј��� (���u) �d�l�ύX���f��No.739
Revesion History : 2007/11/20 �����q (���u) �d�l�ύX���f��No.806,815,819
Revesion History : 2008/04/10 �����F�@@(���u) ���f�� 849 873 876 878 880
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoSettleContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderRequestAdapter;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractOrderService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (OP�ԍϒ����T�[�r�XImpl)<BR>
 * <BR>
 * �����w���I�v�V�����ԍϒ����T�[�r�X�����N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3OptionSettleContractOrderServiceImpl extends WEB3OptionClientRequestService implements WEB3OptionSettleContractOrderService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionSettleContractOrderServiceImpl.class);

    /**
     * �����w���I�v�V�����ԍϒ����T�[�r�X���������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��Avalidate����()�܂��́Asubmit����()���\�b�h���R�[������B<BR>
     * @@param l_inRequest - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40551369036A
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3OptionsCloseMarginConfirmRequest)  //validate����
        {
            l_response = validateOrder((WEB3OptionsCloseMarginConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3OptionsCloseMarginCompleteRequest)    //submit����
        {
            l_response = submitOrder((WEB3OptionsCloseMarginCompleteRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
    }

    /**
     * (validate����)<BR>
     * <BR>
     * �����w���I�v�V�����̕ԍϔ����R�����s���B<BR>
     * <BR>
     * �u�iOP�ԍσT�[�r�X�jvalidate�����v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40551384003D
     */
    protected WEB3OptionsCloseMarginConfirmResponse validateOrder(WEB3OptionsCloseMarginConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3OptionsCloseMarginConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3OptionsCloseMarginConfirmResponse l_response = (WEB3OptionsCloseMarginConfirmResponse)l_request.createResponse();

        //�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
        log.debug(l_subAccount.getInstitution().getInstitutionCode());
        //�㗝���͎҂̈��҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();

        //create���N�G�X�g�A�_�v�^(WEB3GenRequest)
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //create�ԍό��ʃG���g��(OP�ԍϒ������N�G�X�g�A�_�v�^, �ԍό���[])
        SettleContractEntry[] l_settleContractOrderEntries =
            this.createSettleContractEntry(l_requestAdapter, l_request.closeMarginContractUnits);

        //get����( )
        WEB3IfoContractImpl l_contractImpl = l_requestAdapter.getContract();

        //getProduct( )
        IfoProduct l_product = (IfoProduct)l_contractImpl.getProduct();

        //reset�����R�[�h(�����R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //�敨OP��������I�u�W�F�N�g���擾����B
        TradedProduct l_tradecProduct = l_contractImpl.getTradedProduct();

        //���s����: �敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)
        IfoOrderExecutionConditionType l_execCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
        //set limitPrice
        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice = 0D;
        
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv) 
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }

        //���񒍕��̒����P��ID�F�@@
        //�敨OP�f�[�^�A�_�v�^.get���񒍕��̒����P��ID(���N�G�X�g�f�[�^.���������敪)

        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);
        // �����������F�@@OP�����}�l�[�W��.get�����L������(���N�G�X�g�f�[�^.�����L������,
        // �敨OP����.�����R�[�h,�s��.getMarketCode(),�h�I�v�V�����h)�̖߂�l
        // �����I�u�W�F�N�g���擾����B
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Market l_market = l_ifoProductImpl.getPrimaryMarket();
        String l_strMarketCode = l_market.getMarketCode();
        Date l_datExpirationDate = l_orderMgr.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_strMarketCode,
            WEB3FuturesOptionDivDef.OPTION);

        //�ԍϒ������e�I�u�W�F�N�g�𐶐�����B
        double l_dblStopOrderPrice = 0D;
        //���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        //���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }
        // 1.6 create�ԍϒ������e
        IfoOrderExecutionConditionType l_wLimitExecCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //�[��O�J�z�Ώۃt���O�F�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(
        //      ���N�G�X�g�f�[�^.���������敪,
        //      �⏕�����ɊY�����镔�X.���XID)
        boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType,
            l_subAccount.getWeb3GenBranch().getBranchId());

        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(), //�،���ЃR�[�h
                l_trader,                                           //����
                l_dblLimitPrice,                                    //�w�l
                l_execCondType,                                     //���s����
                l_datExpirationDate,                                //����������
                l_settleContractOrderEntries,                       //�ԍό��ʃG���g��
                l_request.orderCondType,                            //��������
                l_dblStopOrderPrice,                                //�t�w�l��l
                l_dblwLimitPrice,                                   //W�w�l�p�����P��
                l_wLimitExecCondType,                               //(W�w�l)���s����
                l_request.expirationDateType,                       //���������敪
                l_lngFirstOrderUnitId,                              //���񒍕��̒����P��ID
                l_blnEveningSessionCarryoverFlag);                  //�[��O�J�z�Ώۃt���O

        //�敨OP�ԍϒ����̔����R�������{����B
        NewOrderValidationResult l_result = null;
        l_result = this.validateOptionsSettleContractOrder(
            l_subAccount, l_settleContractOrderSpec, l_requestAdapter);

        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }

        //���ʂ̍��v���ʂ��擾����B
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
        if (Double.isNaN(l_dblTotalQuantity))
        {
            l_dblTotalQuantity = 0D;
        }

        //t������
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        Timestamp l_tsOrderBizDate = new Timestamp(l_datOrderBizDate.getTime());


        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_commission.setOrderChannel(this.getLoginChannel());

        //�萔��.�،����ID = �⏕����.get�،����()
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //�萔��.���XID = �⏕����.get����X()
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        //�萔��.������ = ������ԊǗ�.get������()
        l_commission.setOrderBizDate(l_tsOrderBizDate);

        //�萔��.����R�[�h(SONAR) = �h52�F�ԍρh
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //�萔��.�萔�����i�R�[�h = �h51�F�����w��OP�h
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);

        //�ٍϋ敪���Z�b�g����B
        l_commission.setPayType(WEB3PayTypeDef.OTHER);
        //�萔��.is�w�l���Z�b�g����B
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());

        //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImpl.getProduct()).getUnderlyingProductCode());

        //�萔��.���v��敪 = this.get���v��敪()�̖߂�l
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractOrderEntries, l_requestAdapter));

        //�萔��.���� = �ԍϒ������e.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        //����
        SideEnum l_sellToBuy = null;
        if (l_contractImpl.isLong())
        {
            l_sellToBuy = SideEnum.SELL;
        }
        else
        {
            l_sellToBuy = SideEnum.BUY;
        }

        //�T�Z��n������v�Z����B

        WEB3IfoEstimateDeliveryAmountCalcResult l_EstimateDeliveryAmount =
            l_orderMgr.calcEstimateDeliveryAmount(
                l_commission,                               //�萔��
                l_dblLimitPrice,   //�w�l
                (WEB3GentradeSubAccount)l_subAccount,       //�⏕����
                (WEB3IfoTradedProductImpl)l_tradecProduct,  //�敨OP�������
                l_dblTotalQuantity,                         //����
                l_sellToBuy,                                //����
                true,                                       //is�ԍϒ���
                false                                       //isSkip���z�`�F�b�N
                );

        //[����]
        //�ԍό��ʃG���g���F�@@create�ԍό��ʃG���g��()�̖߂�l
        //���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g
        WEB3FuturesOptionsContractUnit[] l_contractUnits = this.createContractUnit(
            l_settleContractOrderEntries, l_requestAdapter, (WEB3IfoTradedProductImpl)l_tradecProduct);

        //�V�K�����ԍ����̔Ԃ���B
        long l_lngOrderId = l_orderMgr.createNewOrderId();

        //set�P��(OP�ԍϒ������N�G�X�g�A�_�v�^, WEB3GenResponse)
        this.setPrice(l_requestAdapter, l_response);

        //���X�|���X.����ID = OP�����}�l�[�W��.createNewOrderId()�̖߂�l 
        l_response.orderId = "" + l_lngOrderId;
        //���X�|���X.���ʖ��� = �i*��L�ŕҏW�������ʖ��׃I�u�W�F�N�g�̔z��j
        l_response.contractUnits = l_contractUnits;

        //���X�|���X.�T�Z��n��� = �T�Z��n����v�Z����.�T�Z��n���
        double l_dblEstimateDeliveryAmount = l_EstimateDeliveryAmount.getEstimateDeliveryAmount();
        if (Double.isNaN(l_dblEstimateDeliveryAmount))
        {
            l_dblEstimateDeliveryAmount = 0D;
        }
        l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmount);

        //���X�|���X.�萔���R�[�X = �T�Z��n����v�Z����.�萔���R�[�X
        l_response.commissionCourse = l_EstimateDeliveryAmount.getCommissionCourse();

        //���X�|���X.�萔�� = �T�Z��n����v�Z����.�萔��
        l_response.commission = WEB3StringTypeUtility.formatNumber(l_EstimateDeliveryAmount.getCommission());

        //���X�|���X.�萔������� = �T�Z��n����v�Z����.�萔�������
        l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_EstimateDeliveryAmount.getCommissionTax());           

        //���X�|���X.����I���x������ = ������ԊǗ�.get�s��ǌx���w��()�̖߂�l
        l_response.messageSuspension = WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(l_subAccount.getWeb3GenBranch(), WEB3FuturesOptionDivDef.OPTION);

        //���X�|���X.�m�F���P�� = �T�Z��n����v�Z����.�v�Z�P��
        double l_dblCalcUnitPrice = l_EstimateDeliveryAmount.getCalcUnitPrice();
        if (Double.isNaN(l_dblCalcUnitPrice))
        {
            l_dblCalcUnitPrice = 0D;
        }
        l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);

        //���X�|���X.�m�F�������� = ������ԊǗ�.get������()�̖߂�l
        l_response.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);

        //���X�|���X.�����L������ = �V�K���������e.�ԍϒ������e
        l_response.expirationDate = l_settleContractOrderSpec.getOrderExpDate();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (submit����)<BR>
     * <BR>
     * �����w���I�v�V�����̕ԍϒ�����o�^����B<BR>
     * <BR>
     * �u�iOP�ԍσT�[�r�X�jsubmit�����v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 405513960166
     */
    protected WEB3OptionsCloseMarginCompleteResponse submitOrder(WEB3OptionsCloseMarginCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3OptionsCloseMarginCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();

        WEB3OptionsCloseMarginCompleteResponse l_response = (WEB3OptionsCloseMarginCompleteResponse)l_request.createResponse();

        //�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

        //�㗝���͎҂̈��҃I�u�W�F�N�g���擾����B
        Trader l_trader = this.getTrader();

        //create���N�G�X�g�A�_�v�^(WEB3GenRequest)
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter = this.createRequestAdapter(l_request);

        //create�ԍό��ʃG���g��(OP�ԍϒ������N�G�X�g�A�_�v�^, �ԍό���[])
        SettleContractEntry[] l_settleContractOrderEntries =
            this.createSettleContractEntry(l_requestAdapter, l_request.closeMarginContractUnits);

        //get����( )
        WEB3IfoContractImpl l_contractImpl = l_requestAdapter.getContract();

        //getProduct( )
        IfoProduct l_product = (IfoProduct)l_contractImpl.getProduct();

        //reset�����R�[�h(�����R�[�h : String)
        WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //���s����: �敨OP�f�[�^�A�_�v�^.get���s����(���N�G�X�g�f�[�^.���s����)
        IfoOrderExecutionConditionType l_execCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

        //�敨OP��������I�u�W�F�N�g���擾����B
        TradedProduct l_tradecProduct = l_contractImpl.getTradedProduct();

        //������
        Date l_datBizDate = null;
        if (l_request.checkDate != null)
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
        }

        double l_dblLimitPrice = l_requestAdapter.getPrice();
        double l_dblwLimitPrice = 0D;
        
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
            && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblwLimitPrice = Double.parseDouble(l_request.wLimitPrice);
        }

        //���񒍕��̒����P��ID�F 
        //�敨OP�f�[�^�A�_�v�^.get���񒍕��̒����P��ID(���N�G�X�g�f�[�^.���������敪)
        Long l_lngFirstOrderUnitId = WEB3IfoDataAdapter.getFirstOrderUnitId(l_request.expirationDateType);
        // �����������F�@@OP�����}�l�[�W��.get�����L������(���N�G�X�g�f�[�^.�����L������,
        // �敨OP����.�����R�[�h,���N�G�X�g�f�[�^.����s��,�h�I�v�V�����h)�̖߂�l
        // �����I�u�W�F�N�g���擾����B
        WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_contractImpl.getProduct();
        String l_strProductCode = l_ifoProductImpl.getProductCode();
        Market l_market = l_ifoProductImpl.getPrimaryMarket();
        String l_strMarketCode = l_market.getMarketCode();
        Date l_datExpirationDate = l_orderMgr.getExpirationDate(
            l_request.expirationDate,
            l_strProductCode,
            l_strMarketCode,
            WEB3FuturesOptionDivDef.OPTION);

        //�ԍϒ������e�I�u�W�F�N�g�𐶐�����B
        double l_dblStopOrderPrice = 0D;
        //���N�G�X�g�f�[�^.���������敪 == �h�t�w�l�h�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        //���N�G�X�g�f�[�^.���������敪 == �hW�w�l�h�̏ꍇ
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
        }

        IfoOrderExecutionConditionType l_wLimitExecCondType = WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);

        //�[��O�J�z�Ώۃt���O�F�敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O(
        //      ���N�G�X�g�f�[�^.���������敪,
        //      �⏕�����ɊY�����镔�X.���XID)
        boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
            l_request.expirationDateType,
            l_subAccount.getWeb3GenBranch().getBranchId());

        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
            WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                l_subAccount.getInstitution().getInstitutionCode(), //�،���ЃR�[�h
                l_trader,                                           //����
                l_dblLimitPrice,                                    //�w�l
                l_execCondType,                                     //���s����
                l_datExpirationDate,                                //����������
                l_settleContractOrderEntries,                       //�ԍό��ʃG���g��
                l_request.orderCondType,                            //��������
                l_dblStopOrderPrice,                                //�t�w�l��l
                l_dblwLimitPrice,                                   //W�w�l�p�����P��
                l_wLimitExecCondType,                               //(W�w�l)���s����
                l_request.expirationDateType,                       //���������敪
                l_lngFirstOrderUnitId,                              //���񒍕��̒����P��ID
                l_blnEveningSessionCarryoverFlag);                  //�[��O�J�z�Ώۃt���O

        //�敨OP�ԍϒ����̔����R�������{����B
        //[����]
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //�ԍϒ������e�F�@@create�ԍϒ������e()�̖߂�l
        //���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        NewOrderValidationResult l_newOrderValidationResult =
            this.validateOptionsSettleContractOrder(l_subAccount, l_settleContractOrderSpec, l_requestAdapter);

        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_newOrderValidationResult.getProcessingResult());
            throw new WEB3BaseException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                STR_METHOD_NAME);
        }

        //���ʂ̍��v���ʂ��擾����B
        double l_dblTotalQuantity = l_settleContractOrderSpec.getTotalQuantity();
        //START 2004/08/13 ������  �Ή��o�b�O BUG79
        if (Double.isNaN(l_dblTotalQuantity))
        {
            l_dblTotalQuantity = 0D;
        }

        //�萔���I�u�W�F�N�g�𐶐�����B
        WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

        //�萔��.�����`���l�� = this.get���O�C���`���l��()
        l_commission.setOrderChannel(this.getLoginChannel());

        //�萔��.�،����ID = �⏕����.get�،����()
        l_commission.setInstitutionCode(l_subAccount.getInstitution().getInstitutionCode());

        //�萔��.���XID = �⏕����.get����X()
        l_commission.setBranchId(l_subAccount.getWeb3GenBranch().getBranchId());

        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        //�萔��.������ = ������ԊǗ�.get������()
        l_commission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));

        //�萔��.����R�[�h(SONAR) = �h52�F�ԍρh
        l_commission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);

        //2004/07/19 ���Ō�  �Ή��ύX  �y�敨�I�v�V�����z�d�l�ύX�Ǘ��䒠.xls  No.046
        //�ٍϋ敪���Z�b�g����B
        //l_commission.setPayType("00");
        l_commission.setPayType(WEB3PayTypeDef.OTHER);

        //�萔��.�萔�����i�R�[�h = �h51�F�����w��OP�h
        l_commission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
        //�萔��.is�w�l���Z�b�g����B
        l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());

        //�萔��.�����Y�����R�[�h = ����.get����().get�����Y�����R�[�h(*2)
        l_commission.setUnderlyingProductCode(
            ((WEB3IfoProductImpl)l_contractImpl.getProduct()).getUnderlyingProductCode());

        //�萔��.���v��敪 = this.get���v��敪()�̖߂�l
        l_commission.setDayTradeType(this.getDayTradeType(l_settleContractOrderEntries, l_requestAdapter));

        //�萔��.���� = �ԍϒ������e.getTotalQuantity()
        l_commission.setQuantity(l_dblTotalQuantity);

        //����
        SideEnum l_sellToBuy = null;
        if (l_contractImpl.isLong())
        {
            l_sellToBuy = SideEnum.SELL;
        }
        else
        {
            l_sellToBuy = SideEnum.BUY;
        }

        double l_dblPrice = 0D;
        if (l_request.checkPrice != null)
        {
            l_dblPrice = Double.parseDouble(l_request.checkPrice); 
        }
        else
        {

            l_dblPrice = l_requestAdapter.getPrice(); 

        }

        //�T�Z��n������v�Z����B
        //[calc�T�Z��n���()�Ɏw�肷�����]
        //�萔���F�@@�萔���I�u�W�F�N�g
        //�w�l�F
        //�@@���N�G�X�g�f�[�^.�m�F���P��!=null�̏ꍇ�A���N�G�X�g�f�[�^.�m�F���P����ݒ�B
        //�@@���N�G�X�g�f�[�^.�m�F���P��==null�̏ꍇ�Acreate���N�G�X�g�A�_�v�^()�̖߂�l.get�P��()��ݒ�
        //�⏕�����F�@@�⏕�����I�u�W�F�N�g
        //�敨OP��������F�@@�敨OP��������I�u�W�F�N�g
        //���ʁF �ԍϒ������e.getTotalQuantity()
        //�����F
        //�@@�p�����[�^.���N�G�X�g�A�_�v�^.get����().isLong() == true�̏ꍇSideEnum.SELL
        //�@@�p�����[�^.���N�G�X�g�A�_�v�^.get����().isLong() == false�̏ꍇSideEnum.BUY 
        //is�ԍϒ����F�@@true
        //isSkip���z�`�F�b�N�F�@@false
        WEB3IfoEstimateDeliveryAmountCalcResult l_dblEstimateDeliveryAmount =
            l_orderMgr.calcEstimateDeliveryAmount(
                l_commission,
                l_dblPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                (WEB3IfoTradedProductImpl)l_tradecProduct,
                l_dblTotalQuantity,
                l_sellToBuy,
                true,
                false);

        long l_lngOrderId = 0;
        if (l_request.orderId != null)
        {
            l_lngOrderId = Long.parseLong(l_request.orderId);
        }
        else
        {
            l_lngOrderId = l_orderMgr.createNewOrderId();
        }

        //[����]
        //���N�G�X�g�A�_�v�^�F�@@create���N�G�X�g�A�_�v�^()�̖߂�l
        //�⏕�����F�@@get�⏕����()�̖߂�l
        //�ԍϒ������e�F�@@create�ԍϒ������e()�Ő��������������e�B
        //����ID�F�@@���N�G�X�g�f�[�^.����ID�@@(*1)
        //�萔���F�@@calc�T�Z��n���()�����̎萔���I�u�W�F�N�g
        //�T�Z��n����v�Z���ʁF�@@calc�T�Z��n���()�̖߂�l
        //(*1)�@@���N�G�X�g�f�[�^.����ID == null�̏ꍇ�A
        //�@@�@@�@@�@@createNewOrderId()�̖߂�l
        this.submitSettleContractOrder(
            l_requestAdapter,
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngOrderId,
            l_commission,
            l_dblEstimateDeliveryAmount);

        this.execReCalcTradingPower(l_subAccount);

        //���X�|���X.�X�V���� = ���ݓ���(GtlUtils.getSystemTimestamp())
        l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
        //���N�G�X�g�f�[�^.����ID!=null�̏ꍇ�A���N�G�X�g�f�[�^.����ID��ݒ�B 
        //���N�G�X�g�f�[�^.����ID==null�̏ꍇ�AOP�����}�l�[�W��.createNewOrderId()�̖߂�l��ݒ�B 
        l_response.orderActionId = "" + l_lngOrderId;

        log.debug("l_response.lastUpdateTimestamp = " + l_response.lastUpdatedTimestamp);
        log.debug("l_response.orderActionId = "+ l_response.orderActionId);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^�̃C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍρjvalidate�����P�v<BR>
     * �u�iOP�ԍρjsubmit�����P�v�Q��<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@return WEB3OptionSettleContractOrderRequestAdapter
     * @@throws WEB3BaseException
     */
    protected WEB3OptionSettleContractOrderRequestAdapter createRequestAdapter(WEB3GenRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�A�_�v�^���쐬����B
        //���N�G�X�g �F ����.���N�G�X�g�f�[�^
        WEB3OptionSettleContractOrderRequestAdapter l_adapter =
            WEB3OptionSettleContractOrderRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (create�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g�����쐬����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍρjvalidate�����P�v<BR>
     * �u�iOP�ԍρjsubmit�����P�v�Q��<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�f�[�^�B<BR>
     * @@param l_closeMarginContractUnits - (�ԍό��ʃI�u�W�F�N�g�̔z��)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     */
    protected SettleContractEntry[] createSettleContractEntry(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3FuturesOptionsCloseMarginContractUnit[] l_closeMarginContractUnits) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry(" +
            "WEB3OptionSettleContractOrderRequestAdapter, WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        //���Ϗ��ʂŃ\�[�g���A�ԍό��ʃG���g���̔z����쐬����B
        //[����]
        //�����P��ID = 0
        //�������ʁF�@@�p�����[�^.���N�G�X�g�A�_�v�^.get��������()�߂�l
        //�ԍό���[] = �p�����[�^.�ԍό���
        SettleContractEntry[] l_settleContractOrderEntries = l_orderMgr.createSettleContractEntry(
            0, l_requestAdapter.getOrderQuantity(), l_closeMarginContractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }

    /**
     * (validateOP�ԍϒ���)<BR>
     * OP�ԍϒ����̔����R�������{����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍρjvalidate�����P�v<BR>
     * �u�iOP�ԍρjsubmit�����P�v�Q�� <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_settleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e�I�u�W�F�N�g<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@return NewOrderValidationResult
     * @@throws WEB3BaseException
     */
    protected NewOrderValidationResult validateOptionsSettleContractOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOptionsSettleContractOrder(" +
            "SubAccount, IfoSettleContractOrderSpec, WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        NewOrderValidationResult l_result = null;
        //[����]
        //arg0�i�⏕�����j�F�@@�p�����[�^.�⏕����
        //arg1�i�ԍϒ������e�j�F�@@�p�����[�^.�ԍϒ������e
        l_result = l_orderMgr.validateSettleContractOrder(
            (WEB3GentradeSubAccount)l_subAccount, l_settleContractOrderSpec);

        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * (create���ʖ���)<BR>
     * �ԍό��ʃG���g����茚�ʖ��ׂ̈ꗗ���쐬����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍσT�[�r�X�jvalidate�����Q�v�Q��<BR>
     * @@param l_settleContractEntries - (�ԍό��ʃG���g��)<BR>
     * �ԍό��ʃG���g��<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_ifoTradedProduct - (�敨OP�������)<BR>
     * �敨OP�������<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    protected WEB3FuturesOptionsContractUnit[] createContractUnit(
        SettleContractEntry[] l_settleContractEntries,
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3IfoTradedProductImpl l_ifoTradedProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnit(" +
            "SettleContractEntry[], WEB3OptionSettleContractOrderRequestAdapter, WEB3IfoTradedProductImpl)";

        List l_lisContractUnits = new ArrayList();

        for (int i = 0; i < l_settleContractEntries.length; i++)
        {
            //���������擾����
            WEB3IfoProductQuote l_currentInfo =
                ((WEB3IfoTradedProductImpl)l_ifoTradedProduct).getCurrentInfo(null);

            log.debug("i = " + i);
            SettleContractEntry l_settleContractEntry = l_settleContractEntries[i];

            //���ʖ��׃I�u�W�F�N�g�𐶐�����B
            WEB3FuturesOptionsContractUnit l_contractUnit = new WEB3FuturesOptionsContractUnit();

            //�ԍϐ���
            double l_dblSettleContractQuantity = l_settleContractEntry.getQuantity();

            if (Double.isNaN(l_dblSettleContractQuantity))
            {
                l_dblSettleContractQuantity = 0D;
            }

            //���ʃI�u�W�F�N�g���擾����B
            WEB3IfoContractImpl l_contract = null;
            try
            {
                l_contract = new WEB3IfoContractImpl(l_settleContractEntry.getContractId());
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //���ʖ���.ID = ���ʂh�c
            l_contractUnit.id = "" + l_contract.getContractId();

            //���ʖ���.���N���� = ����.getOpenDate()
            l_contractUnit.openDate = l_contract.getOpenDate();

            //���ʖ���.���P�� = ����.getContractPrice()
            l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());

            //���ʖ���.���ʐ� = ����.getQuantity() - ����.getLockedQuantity()
            BigDecimal l_bdContractQuantity = new BigDecimal(l_contract.getQuantity() + "");
            BigDecimal l_bdLockedQuantity = new BigDecimal(l_contract.getLockedQuantity() + "");
            BigDecimal l_bdQuantity = l_bdContractQuantity.subtract(l_bdLockedQuantity);
            double l_dblQuantity = l_bdQuantity.doubleValue();
            l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);                

            //���ʖ���.�������z = ����.get�������z(�ԍϐ���)
            l_contractUnit.contractExecPrice =
                WEB3StringTypeUtility.formatNumber(l_contract.getContractExecutedAmount(
                    l_dblSettleContractQuantity));

            //���ʖ���.���萔��
            BigDecimal l_bdCommission =
                new BigDecimal(l_contract.getContractCommission(l_dblSettleContractQuantity) + "");
            BigDecimal l_bdCommissionConsumptionTax =
                new BigDecimal(l_contract.getContractCommissionConsumptionTax(l_dblSettleContractQuantity) + "");
            double l_dblContractCommission =
                l_bdCommission.add(l_bdCommissionConsumptionTax).doubleValue();
            l_contractUnit.contractCommission = WEB3StringTypeUtility.formatNumber(l_dblContractCommission);

            //�������擾����B
            double l_dblPrice = 0D;
            if (l_currentInfo != null)
            {
                l_dblPrice = l_currentInfo.getCurrentPrice();
            }

            //���ʏڍ�.���v = ����.get�]�����v()
            double l_dblIncome = l_contract.getEvaluateIncome(
                l_dblPrice,
                l_dblSettleContractQuantity);
            l_contractUnit.income = WEB3StringTypeUtility.formatNumber(l_dblIncome);

            //���ʏڍ�.���v�i���o��j
            BigDecimal l_bdIncome = new BigDecimal(l_dblIncome + "");
            BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
            l_contractUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                l_bdIncome.subtract(l_bdContractCommission).doubleValue());

            //���ʖ���.�ԍϐ��� = SettleContractEntry.getQuantity()�i*�ԍϐ��ʁj
            l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblSettleContractQuantity);

            //���ʖ���.���Ϗ��� = �iSettleContractEntry��index+1�j
            l_contractUnit.settlePriority = "" + (i + 1);                

            //���ʖ���.����敪 = ����.����敪
            l_contractUnit.sessionType =
                ((IfoContractRow)l_contract.getDataSourceObject()).getSessionType();

            l_lisContractUnits.add(l_contractUnit);
        }

        WEB3FuturesOptionsContractUnit[] l_contractUnits =
            new WEB3FuturesOptionsContractUnit[l_lisContractUnits.size()];
        l_lisContractUnits.toArray(l_contractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }

    /**
     * (submit�ԍϒ���)<BR>
     * OP�ԍϒ�����o�^����B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍρjsubmit�����Q�v�Q��<BR>
     * @@param l_requestAdapter - (���N�G�X�g�A�_�v�^)<BR>
     * ���N�G�X�g�A�_�v�^<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@param l_settleContractOrderSpec - (�ԍϒ������e)<BR>
     * �ԍϒ������e<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_amountCalcResult - (�T�Z��n����v�Z����)<BR>
     * �T�Z��n����v�Z����<BR>
     * @@throws WEB3BaseException
     */
    protected void submitSettleContractOrder(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec,
        long l_lngOrderId,
        WEB3GentradeCommission l_commission,
        WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitSettleContractOrder(" +
            "WEB3OptionSettleContractOrderRequestAdapter, " +
            "SubAccount, " +
            "WEB3IfoSettleContractOrderSpec, " +
            "long" +
            "WEB3GentradeCommission, " +
            "WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        WEB3OptionsCloseMarginCompleteRequest l_request =
            (WEB3OptionsCloseMarginCompleteRequest)l_requestAdapter.request;

        //�C���^�Z�v�^�𐶐�����B
        WEB3IfoSettleContractUpdateInterceptor l_settleContractUpdateInterceptor =
            new WEB3IfoSettleContractUpdateInterceptor(l_settleContractOrderSpec);

        //�C���^�Z�v�^.�萔��
        l_settleContractUpdateInterceptor.setCommision(l_commission);

        //�C���^�Z�v�^.�T�Z��n����v�Z����
        l_settleContractUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

        //�C���^�Z�v�^.��������
        l_settleContractUpdateInterceptor.setOrderCond(l_request.orderCondType);

        double l_dblstopOrderCondPrice = 0D;
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            l_dblstopOrderCondPrice = Double.parseDouble(l_request.stopOrderCondPrice);
        }
        //�i*���������敪�ŋt�w�l/W�w�l�𔻒�j
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //�C���^�Z�v�^.�����������Z�q = ���N�G�X�g�f�[�^.�����������Z�q
            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);

            //�C���^�Z�v�^.�t�w�l��l�^�C�v = ���N�G�X�g�f�[�^.�v���~�A���^�����Y���i
            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);

            //�C���^�Z�v�^.�t�w�l��l = ���N�G�X�g�f�[�^.���������P��
            l_settleContractUpdateInterceptor.setStopOrderBasePrice(l_dblstopOrderCondPrice);
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
        {
            //�C���^�Z�v�^.�����������Z�q = ���N�G�X�g�f�[�^.�����������Z�q
            l_settleContractUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
            
            //�C���^�Z�v�^.�t�w�l��l�^�C�v = ���N�G�X�g�f�[�^.�v���~�A���^�����Y���i
            l_settleContractUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);

            double l_dblWLimitOrderCondPrice = 0D;

            if (WEB3StringTypeUtility.isNumber(l_request.wlimitOrderCondPrice))
            {
                l_dblWLimitOrderCondPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }

            //�C���^�Z�v�^.�t�w�l��l = ���N�G�X�g�f�[�^.���������P��
            l_settleContractUpdateInterceptor.setStopOrderBasePrice(l_dblWLimitOrderCondPrice);

            //�C���^�Z�v�^.(W�w�l)�����w�l = �ԍϒ������e.(W�w�l)�����w�l
            log.debug("setWLimitPriceChange");
            l_settleContractUpdateInterceptor.setWLimitPriceChange(l_settleContractOrderSpec.getWLimitPriceChange());
        }

        //�C���^�Z�v�^.���Ϗ��� = ���N�G�X�g�f�[�^.���Ϗ���
        l_settleContractUpdateInterceptor.setSettleSequence(l_request.closingOrder);

        //�C���^�Z�v�^.����敪 = ������ԊǗ�.get����敪()�̖߂�l
        l_settleContractUpdateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //�C���^�Z�v�^�𒍕��}�l�[�W���ɃZ�b�g����B
        l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_settleContractUpdateInterceptor);

        //�ԍϒ����o�^�������s���B
        l_settleContractOrderSpec.getTaxType();

        OrderSubmissionResult l_result = l_orderMgr.submitSettleContractOrder(
            l_subAccount,
            l_settleContractOrderSpec,
            l_lngOrderId,
            l_request.password,
            true);

        if (l_result.getProcessingResult().isFailedResult())
        {
            log.debug("ProcessingResult() = " + l_result.getProcessingResult());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_result.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�P��)<BR>
     * ����������return����B�i�J�������j<BR>
     * @@param l_requestAdapter - (OP�ԍϒ������N�G�X�g�A�_�v�^)<BR>
     * OP�ԍϒ������N�G�X�g�A�_�v�^�B<BR>
     * @@param l_response - (���X�|���X)<BR>
     * ���X�|���X�B<BR>
     * @@throws WEB3BaseException
     */
    protected void setPrice(
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        return;
    }

    /**
     * (exec�]�͍Čv�Z)<BR>
     * �]�͍Čv�Z���s���B <BR>
     * <BR>
     * �������̏ڍׂ̓V�[�P���X�}�u�iOP�ԍρjsubmit�����Q�v�Q��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@throws WEB3BaseException
     */
    protected void execReCalcTradingPower(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execReCalcTradingPower(SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
        {
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            //�]�͍Čv�Z���s���B
            l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get���v��敪)<BR>
     * ���v��敪���擾����B <BR>
     * <BR>
     * OP�����}�l�[�W��.get���v��敪()��call����B <BR>
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
        WEB3OptionSettleContractOrderRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDayTradeType(SettleContractEntry[], WEB3OptionSettleContractOrderRequestAdapter)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        //OP�����}�l�[�W��.get���v��敪()��call����B
        String l_strDayTradeType = l_orderMgr.getDayTradeType(l_settleContractOrderEntries);

        log.exiting(STR_METHOD_NAME);
        return l_strDayTradeType;
    }
}
@
