head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g�����������}�l�[�W��(WEB3EquityOrderManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 ���� (SRA)
Revesion History : 2006/08/02 �h�C (���u) �d�l�ύX ���f��No.959��Ή�
Revesion History : 2006/08/30 �����F(���u) DB�X�V�d�l 164
Revesion History : 2006/11/01 ����(���u) ���f��No.945�ANo.946�ANo.950�ANo.988�ANo.995�ANo.1003�ANo.1006�A
         �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@No.1008�ANo.1014�ANo.1018�ANo.1020�ANo.1028�ANo.1026�ANo.1034�A
         �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@No.1035�ANo.1036�ANo.1037�ANo.1038�ANo.1039�ANo.1045�A
         �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@No.1050�ANo.1054 �v�Z���� No.019
Revesion History : 2006/11/21 �đo�g(���u) ���f��No.1052�ANo.1057�ANo.1058,�y�g���K�[�����zDB�X�V�d�lNo.025�ANo.026
Revesion History : 2006/11/28 ������(���u) ���f��No.1061.1074,1075,No.1080
Revesion History : 2006/11/30 ꎉ�(���u) �y�g���K�[�����zDB�X�V�d�lNo.028 
Revesion History : 2006/12/26 �����F (���u) ���f�� 1096  
Revesion History : 2007/01/15 ������(���u) ���f��No.1101.1102,1103,No.1104
Revesion History : 2007/01/18 ������(���u) ���f��No.1108
Revesion History : 2007/01/31 ��іQ (���u) �d�l�ύX ���f��No.1118
Revesion History : 2007/02/08 ������(���u) ���f��No.1126
Revesion History : 2007/04/25 �Ӑ�(���u) ���f��1138
Revesion History : 2007/05/15 �����F(���u) ���f��No.1156�A1157�A1158
Revesion History : 2007/05/21 �����F(���u)�@@���f�� 1163
Revesion History : 2007/07/27 �]���@@���u(SRA) �d�l�ύX���f�� 1183,1185,1186
Revesion History : 2007/06/05 �����q(���u)�@@���f�� 1149,���f�� 1159
Revesion History : 2007/06/15 ���g(���u)�@@���f��No.1176
Revesion History : 2007/06/20 ���g(���u)�@@���f��No.1180
Revesion History : 2007/07/26 �����q (���u) �d�l�ύX���f��No.1189
Revesion History : 2007/08/07 �ؕk�i���u�j�d�l�ύX ���f��No.1191
Revesion History : 2007/12/10 ��іQ (���u) �d�l�ύX���f��No.1239
Revesion History : 2007/12/17 ��іQ (���u) �d�l�ύX���f��No.1218,1236
Revesion History : 2008/10/06 ���� (���u) ���f��No.1324
Revesion History : 2008/12/16 ���L���E (���u) ���f��No.1328
Revesion History : 2009/09/28 ���g (���u) ���f�� No.1345
*/
package webbroker3.equity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeContractSwapOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderAction;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderExecution;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionDao;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeCancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSwapContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSettleOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeContractSwapOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderManagerImpl;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.RuntimeSystemException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AllOrderChangeDivDef;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3CapitalGainTaxTypeDef;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3CheckTypeDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FrontOrderStatusDef;
import webbroker3.common.define.WEB3FrontOrderTradeCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3InsiderRegistDivDef;
import webbroker3.common.define.WEB3ListTypeDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MaxMinFlagDef;
import webbroker3.common.define.WEB3OpenOtcDivDef;
import webbroker3.common.define.WEB3OrderDateDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PremiumRestraintRateDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3PriceConditionSONARDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.common.define.WEB3TradeauditCodeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.equity.data.OffFloorOrderProductParams;
import webbroker3.equity.data.ShortSellingRestraintTimeRow;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInsider;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.InsiderRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.rlsgateway.define.WEB3RlsNotifyOrderTypeDef;
import webbroker3.rlsgateway.service.WEB3RlsRequestSenderService;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.define.WEB3TPTradingPowerErrorDivDef;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�g�����������}�l�[�W���j�B<BR>
 * <BR>
 * ���������̎葱�����L�q����N���X�B <BR>
 * <BR>
 * �i�Q�l�jcom.fitechlabs.xtrade.plugin.tc.eqtype.stdimples.EqTypeOrderManagerImpl
 * @@version 1.0
 */
public class WEB3EquityOrderManager extends EqTypeOrderManagerImpl
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderManager.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     * @@roseuid 409F7154007D
     */
    public WEB3EquityOrderManager()
    {
        super();
    }

    /**
     * (validate������������) <BR>
     * <BR>
     * �������͓��e�̃`�F�b�N�����{����B <BR>
     * �ivalidateNewCashBasedOrder�j <BR>
     * <BR>
     * �V�[�P���X�}�u�i�����j�������������R���v�Q�ƁB <BR>
     * �V�[�P���X�}���̃��\�b�h��O�����i�ȉ��̔ԍ��A�V�[�P���X�}���j<BR>
     * <BR>
     * �@@1.7.4 �\����������`�F�b�N<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@tag   : BUSINESS_ERROR_01347<BR>
     * @@param l_subAccount (�⏕����)
     * @@param l_eqNewCashBasedOrderSpec (�����������e)
     * @@param l_blnIsReverseOrder (is�A�����Δ���)<BR>
     * �\�񒍕��ݒ�ŁA�e�����ɑ΂��锽�Δ����̏ꍇ��true���w��B<BR>
     * �ȊO�Afalse�w��B<BR>
     * �itrue�̏ꍇ�A�m��c�ɑ΂���c���`�F�b�N���s���X�L�b�v����j
     * @@return EqTypeNewOrderValidationResult
     * @@roseuid 3FFCCD60031C
     */
    public EqTypeNewOrderValidationResult validateNewCashBasedOrder(
        SubAccount l_subAccount,
        EqTypeNewCashBasedOrderSpec l_eqNewCashBasedOrderSpec,
        boolean l_blnIsReverseOrder)
    {
        String STR_METHOD_NAME =
            "validateNewCashBasedOrder(SubAccount, EqTypeNewCashBasedOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_eqNewCashBasedOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        SubAccountRow l_subAccountRow = 
            (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_subAccountRow);
        WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec = 
            (WEB3EquityNewCashBasedOrderSpec)l_eqNewCashBasedOrderSpec;
        
        try
        {
            // validate������t�\()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            //commonFirstValidationsForAllOperations()
            l_orderMgrResVal.commonFirstValidationsForAllOperations(
                l_gentradeSubAccount);
            
            //getProductCode()
            String l_strProductCode = l_equityNewCashBasedOrderSpec.getProductCode();
            
            //validate�����R�[�h()
            String l_strInstitutionCode =
                l_gentradeSubAccount.getInstitution().getInstitutionCode();
            WEB3EquityProduct l_equityProduct = 
                l_orderMgrResVal.validateProductCode(l_strProductCode, l_strInstitutionCode);
            
            //getMarketCode()
            String l_strMarketCode = l_equityNewCashBasedOrderSpec.getMarketCode();
            
            //���������̏ꍇ�i�萔��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�j�̂ݎ��s
            String l_strSonarTradedCode =
                l_equityNewCashBasedOrderSpec.getCommission().getSonarTradedCode();
            boolean l_isSalesOutsideMarket =
                WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode);
            Market l_market = null;
            if (!l_isSalesOutsideMarket)
            {
                //validate�s��R�[�h()
                l_market = 
                    l_orderMgrResVal.validateMarket(l_strMarketCode,l_strInstitutionCode);
            }
            
            //����O���������̏ꍇ�i�萔��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�j�̂ݎ��s
            if (l_isSalesOutsideMarket)
            {
                //validateMarket()
                l_market = l_orderMgrResVal.validateMarket(
                    l_subAccount.getInstitution(),
                    l_equityNewCashBasedOrderSpec.getMarketCode());
                
                //validate����O������t�\()
                OffFloorOrderProductParams l_offFloorOrderProductParams =
                    l_orderMgrResVal.validateOffFloorOrderPossible(
                        l_equityProduct.getProductId(),
                        l_market.getMarketId(),
                        l_subAccount);
                //validate����O������������()
                l_orderMgrResVal.validateOffFloorDuplicateOrder(
                    l_equityProduct.getProductId(),
                    l_market.getMarketId(),
                    l_subAccount);
                //�\����������`�F�b�N
                if (l_equityNewCashBasedOrderSpec.getQuantity() > l_offFloorOrderProductParams.getMaxApplyQuantity())
                {
                    return new EqTypeNewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_01371));
                }
            }
            
            //validate�C���T�C�_�[()
            l_orderMgrResVal.validateInsider(l_gentradeSubAccount, l_equityProduct);
            
            //isSellOrder()
            boolean isSellOrder = l_equityNewCashBasedOrderSpec.isSellOrder();
            OrderTypeEnum l_orderTypeEnum = null;
            if (isSellOrder)
            {
                l_orderTypeEnum = OrderTypeEnum.EQUITY_SELL;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.EQUITY_BUY;
            }
            
            //validate�ڋq�����ʎ����~()
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_gentradeSubAccount,
                l_equityProduct.getProductId(),
                (isSellOrder ? OrderTypeEnum.EQUITY_SELL : OrderTypeEnum.EQUITY_BUY));
            
            //validate�������()
            WEB3EquityTradedProduct l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(
                    l_gentradeSubAccount,
                    l_equityProduct,
                    l_market,
                    isSellOrder);
            
            //validate���s�w��K��()
            l_orderMgrResVal.validateMarketOrderDesignateCtrl(
                l_equityTradedProduct,
                l_equityNewCashBasedOrderSpec.isMarketOrder(),
                isSellOrder,
			    l_equityNewCashBasedOrderSpec.getExecConditionType());

            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();

            //validate�戵�\�s��()
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct);
            
            //getTaxType()
            TaxTypeEnum l_taxType = l_equityNewCashBasedOrderSpec.getTaxType();
            
            //is��������J��
            if (TaxTypeEnum.SPECIAL.equals(l_taxType) || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
            {
                WEB3GentradeMainAccount l_account =
                    new WEB3GentradeMainAccount((MainAccountRow)l_gentradeSubAccount.getMainAccount().getDataSourceObject());
                boolean isSpecialAccountEstablished = l_account.isSpecialAccountEstablished(
                    l_equityTradedProduct.getDailyDeliveryDate(),
                    l_gentradeSubAccount);
                if (!isSpecialAccountEstablished)
                {
                    return new EqTypeNewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00026));
                }
            }
            
            //validate��������戵�K��()
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_equityNewCashBasedOrderSpec.getTaxType(),
                l_equityProduct,
                !isSellOrder);
            
            //validate��������
            Date l_datFirstOrderBizDate = null;
            if ((l_equityNewCashBasedOrderSpec.getFirstOrderUnitId() != null) &&
                (l_equityNewCashBasedOrderSpec.getFirstOrderUnitId().longValue() > 0))
            {
				//�����J�z���̔����R��
				EqTypeOrderUnit l_orderUnit = null;
            	try
            	{
					l_orderUnit = (EqTypeOrderUnit)getOrderUnit(
						l_equityNewCashBasedOrderSpec.getFirstOrderUnitId().longValue());
            	}
            	catch (NotFoundException l_nfe)
            	{
					return new EqTypeNewOrderValidationResult(
						ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
            	}
				EqtypeOrderUnitRow l_orderUnitRow =
				    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
				l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
            }
            l_orderMgrResVal.validateOrderCondition(
                l_gentradeSubAccount,
                0,
                l_equityTradedProduct,
			    l_datFirstOrderBizDate,
                l_equityNewCashBasedOrderSpec.getOrderExpDate(),
                l_equityNewCashBasedOrderSpec.getOrderCond(),
                l_equityNewCashBasedOrderSpec.getExecConditionType(),
                l_equityNewCashBasedOrderSpec.isOrderUntilDeadLine(),
                WEB3MarginTradingDivDef.DEFAULT,
                l_equityNewCashBasedOrderSpec.getPriceConditionType(),
                l_equityNewCashBasedOrderSpec.getMarketCode());
            
            //validate����
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_equityNewCashBasedOrderSpec.getQuantity());
            
            //���������̏ꍇ�i�萔��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�j�̂ݎ��s
            if (!l_isSalesOutsideMarket)
            {
                //validate�����P��()
                boolean l_isValidatePrice =
                    l_orderMgrResVal.validatePrice(
                        l_equityNewCashBasedOrderSpec.getLimitPrice(),
                        l_equityTradedProduct,
                        l_gentradeSubAccount);
                if (l_isValidatePrice == false)
                {
                    return new EqTypeNewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00293));               
                }
            }            
            
            //1.30.validateW�w�l����(�⏕����, long, double, String, double, String, EqTypeExecutionConditionType,
            //String, �������, boolean, String, OrderCategEnum, double, String, OrderTypeEnum)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����@@�F�@@�����̕⏕�����I�u�W�F�N�g 
            //�����P�ʂh�c�@@�F�@@0(�F�V�K����) 
            //�w�l�@@�F�@@getLimitPrice�̖߂�l 
            //���������@@�F�@@�����������e.get��������( ) 
            //���������P���@@�F�@@�����������e.get�t�w�l��l( ) 
            //�iW�w�l�j�����w�l�@@�F�@@�����������e.get�iW�w�l�j�����w�l( ) 
            //�iW�w�l�j���s�����@@�F�@@�����������e.get�i�v�w�l�j���s����( ) 
            //�iW�w�l�j�L����ԋ敪�@@�F�@@null�i�Œ�j 
            //��������@@�F�@@validate�������( )�̖߂�l�̎�������I�u�W�F�N�g 
            //is�������@@�F�@@�����������e.isBuyOrder( ) 
            //�ٍϋ敪�@@�F�@@null�i�Œ�j 
            //�����J�e�S���@@�F�@@OrderCategEnum.ASSET�i���������j 
            //�����@@�F�@@�����������e.getQuantity() 
            //�l�i�����@@�F�@@�����������e.get�l�i����() 
            //������ʁ@@�F�@@�����������e.isSellOrder() == false�̏ꍇ�A���������� 
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�����������e.isSellOrder() == true�̏ꍇ�A����������
            String l_strWLimitPriceChange = 
                WEB3StringTypeUtility.formatNumber(
                    l_equityNewCashBasedOrderSpec.getWLimitPriceChange());
            l_orderMgrResVal.validateWLimitPriceOrder(
                (WEB3GentradeSubAccount)l_subAccount,
                0L,
                l_equityNewCashBasedOrderSpec.getLimitPrice(),
                l_equityNewCashBasedOrderSpec.getOrderCond(),
                l_equityNewCashBasedOrderSpec.getStopLimitPriceBasePrice(),
                l_strWLimitPriceChange,
                l_equityNewCashBasedOrderSpec.getWlimitExecCondType(),
                null,
                l_equityTradedProduct,
                l_equityNewCashBasedOrderSpec.isBuyOrder(),
                null,
                OrderCategEnum.ASSET,
                l_equityNewCashBasedOrderSpec.getQuantity(),
                l_equityNewCashBasedOrderSpec.getPriceConditionType(),
                l_orderTypeEnum);

            //���蒍���̏ꍇ�̂�
            if (isSellOrder && !l_blnIsReverseOrder)
            {
                //validate���t�\����()
                l_orderMgrResVal.validateSellableAssetQuantity(
                    l_gentradeSubAccount,
                    l_equityTradedProduct,
                    l_equityNewCashBasedOrderSpec.getQuantity(),
                    l_equityNewCashBasedOrderSpec.getTaxType());
            }
            
            //validateExpirationDate()
            l_orderMgrResVal.validateExpirationDate(
                l_equityTradedProduct,
                l_equityNewCashBasedOrderSpec.getOrderExpDate());
            
            log.exiting(STR_METHOD_NAME);
            return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);             
        }
        catch (OrderValidationException ove)
        {
            return new EqTypeNewOrderValidationResult(
                ove.getValidationResult().getProcessingResult());
        }
        catch (WEB3BaseException l_wbe)
        {
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()));
        }      
    }

    /**
     * �icalc�T�Z��n����j<BR>
     * <BR>
     * �T�Z��n���z���Z�o���ĕԋp����B<BR>
     * �V�[�P���X�}�u�i�����jcalc�T�Z��n����v�Q�ƁB
     * @@param l_commission (�萔��)
     * @@param l_dblCalcUnitPrice (�v�Z�P��)
     * @@param l_subAccount (�⏕����)
     * @@param l_tradedProduct (�������) 
     * @@param l_dblQuantity (����)
     * @@param l_isSellOrder (is������)
     * @@param l_dblExecutedQuantity (��萔��)
     * @@param l_dblExecutedAmount (���v�����z)
     * @@param l_isSkipPriceCheck (isSkip���z�`�F�b�N)
     * @@param l_isRestraintConsideration (is�S���l��)
     * @@throws WEB3BaseException
     * @@return WEB3EquityEstimatedDeliveryPrice<BR>
     */
    public WEB3EquityEstimatedDeliveryPrice calcEstimateDeliveryAmount(
        WEB3GentradeCommission l_commission,
        double l_dblCalcUnitPrice,
        SubAccount l_subAccount,
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblQuantity,
        boolean l_isSellOrder,
        double l_dblExecutedQuantity,
        double l_dblExecutedAmount,
        boolean l_isSkipPriceCheck,
        boolean l_isRestraintConsideration)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcEstimateDeliveryAmount(WEB3GentradeCommission, double, SubAccount, " 
                + "WEB3EquityTradedProduct, double, double, double, boolean, boolean, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_tradedProduct == null || l_commission == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
    
        if (Double.isNaN(l_dblCalcUnitPrice))
        {
            l_dblCalcUnitPrice = 0.0D;
        }
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0.0D;
        }
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0.0D;
        }    
        if (Double.isNaN(l_dblExecutedAmount))
        {
            l_dblExecutedAmount = 0.0D;
        }

        // 1.1. �T�Z��n����v�Z����()
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = 
            new WEB3EquityEstimatedDeliveryPrice();
        
        // 1.2. getInstance()
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal = 
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                    
        // 1.4. set�v�Z�P��(double)
        l_estimatedDeliveryPrice.setCalcUnitPrice(l_dblCalcUnitPrice);
        
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        //�S����������i���v�l�j�܂��͔�������i���v�l�j�A�܂��͏��o��v�Z�p���
        double l_dblRestraintTurnover;
        //�S����������܂��͔������
        double l_dblAmount;
             
        // 1.5. �S������������Z�o����ꍇ
        if (l_isSellOrder == false && l_isRestraintConsideration == true)
        {
            // 1.5.2. calc�S���������(����, �v�Z�P��, ���XID, �萔�����i�R�[�h, is�w�l)
            l_dblAmount = l_bizLogic.calcRestraintTurnover(
                l_dblQuantity - l_dblExecutedQuantity,
                l_dblCalcUnitPrice,
                l_subAccountRow.getBranchId(),
                l_commission.getCommissionProductCode(),
                l_commission.isLimitPrice());
        }
        // 1.6. ����������Z�o����ꍇ
        else
        {
            // 1.6.1. calc�������(����, �v�Z�P��)
            l_dblAmount = l_bizLogic.calcTurnover(l_dblQuantity - l_dblExecutedQuantity, l_dblCalcUnitPrice);
        }

        // 1.7. �S����������i���v�l�j�܂��͔�������i���v�l�j���v�Z����
        l_dblRestraintTurnover = l_dblAmount + l_dblExecutedAmount;

        // 1.8. set�S���������(double)
        if (l_isSellOrder == false)
        {
            l_estimatedDeliveryPrice.setRestraintTurnover(l_dblRestraintTurnover);
        }
        else
        {
            l_estimatedDeliveryPrice.setRestraintTurnover(0.0D);
        }

        // 1.9. set���o��v�Z�p���(double)
        l_commission.setExpensesCalcAmount(l_dblRestraintTurnover);

        // 1.10. validate����\������z(���X, �s��, ���o��v�Z�p���, �����^�C�v)
        if (l_isSkipPriceCheck == false)
        {
            Branch l_branch = l_subAccount.getMainAccount().getBranch();
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();
            MainAccountTypeEnum l_mainAccountTypeEnum =
                l_mainAccountRow.getAccountType();
            Market l_market = l_tradedProduct.getMarket();
            l_orderMgrResVal.validateMaxHandlingPrice(
                    l_branch,
                    l_market,
                    l_dblRestraintTurnover,
                    l_mainAccountTypeEnum);
        }
        
        // 1.11. calc�ϑ��萔��(�萔��, �⏕����)
        l_bizLogic.calcCommission(l_commission, l_subAccount);
        double l_bdlCalcCommission = l_commission.getCommission();
        
        // set�ϑ��萔��
        l_estimatedDeliveryPrice.setCommissionFee(l_bdlCalcCommission);

        // 1.12. calc�����(�萔�����z, ���, �⏕����)
        double l_dblCalcSalesTax = l_bizLogic.calcSalesTax(
            l_bdlCalcCommission,
            l_commission.getOrderBizDate(),
            l_subAccount);
            
        // set�ϑ��萔�������
        l_estimatedDeliveryPrice.setCommissionFeeTax(l_dblCalcSalesTax);
        
        // 1.13. set�T�Z��n���(double)
        BigDecimal l_bdEstimateDeliveryRestraintTurnover;
        BigDecimal l_bdRestraintTurnover = new BigDecimal(l_dblRestraintTurnover);
        BigDecimal l_bdCalcCommission = new BigDecimal(l_bdlCalcCommission);
        BigDecimal l_bdCalcSalesTax = new BigDecimal(l_dblCalcSalesTax);
        if (l_isSellOrder == false)
        {
            // ���t�̏ꍇ
            l_bdEstimateDeliveryRestraintTurnover =
                l_bdRestraintTurnover.add(l_bdCalcCommission).add(l_bdCalcSalesTax);
            log.debug(
                "****** ���o��v�Z�p���(���̏ꍇ �F �S���������) �F ["
                    + l_bdRestraintTurnover.doubleValue()
                    + "]");
            log.debug(
                "****** �萔�� �F [" + l_bdCalcCommission.doubleValue() + "]");
            log.debug(
                "****** ����� �F [" + l_bdCalcSalesTax.doubleValue() + "]");
            log.debug(
                "****** �T�Z��n��� = ���o��v�Z�p��� �{�i�萔�� �{ ����Łj �F ["
                    + l_bdEstimateDeliveryRestraintTurnover.doubleValue()
                    + "]");
        }
        else
        {
            // ���t�̏ꍇ
            l_bdEstimateDeliveryRestraintTurnover = l_bdRestraintTurnover.subtract(l_bdCalcCommission).subtract(l_bdCalcSalesTax);
            log.debug(
                "****** ���o��v�Z�p���(���̏ꍇ �F �������) �F ["
                    + l_bdRestraintTurnover.doubleValue()
                    + "]");
            log.debug(
                "****** �萔�� �F [" + l_bdCalcCommission.doubleValue() + "]");
            log.debug(
                "****** ����� �F [" + l_bdCalcSalesTax.doubleValue() + "]");
            log.debug(
                "****** �T�Z��n��� = ���o��v�Z�p��� �|�i�萔�� �{ ����Łj �F ["
                    + l_bdEstimateDeliveryRestraintTurnover.doubleValue()
                    + "]");
        }
        l_estimatedDeliveryPrice.setEstimateDeliveryAmount(l_bdEstimateDeliveryRestraintTurnover.doubleValue());
        
        log.exiting(STR_METHOD_NAME);
        return l_estimatedDeliveryPrice;       
    }
    
    /**
     * (calc�T�Z��n���)<BR>
     * �T�Z��n���z���Z�o���ĕԋp����B<BR>  
     * <BR>
     * W�w�l&&�������̏ꍇ�́A�֖ؑ������ł����<BR>  
     * ���~�b�g�����^�X�g�b�v�����̗����ŊT�Z��n������v�Z���A<BR>  
     * �����ق��̋��z�i�S��������z�j��߂�l�I�u�W�F�N�g�ɐݒ肵�ԋp����B<BR>  
     * <BR>
     * �V�[�P���X�}�u�i�����jcalc�T�Z��n����iW�w�l�l���j�P�v�u�i�����jcalc�T�Z��n����iW�w�l�l���j�Q�v�Q�ƁB<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔��<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �����̎w�l�B<BR>
     * �i���s�w��̏ꍇ�́A0�j<BR>
     * @@param l_dblWLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * �X�g�b�v�����̒����w�l�B<BR> 
     * �i�X�g�b�v���������s�w��̏ꍇ�́A0�j<BR>
     * @@param l_dblStopOrderBasePrice - (�t�w�l��l)<BR>
     * �X�g�b�v�����ւ̐ؑւ��s����l�B<BR>
     * @@param l_execConditionType - (���s����)<BR>
     * �����̎��s�����B<BR>
     * @@param l_execWConditionType - (�iW�w�l�j���s����)<BR>
     * �X�g�b�v�����̎��s�����B<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i�����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ���������B<BR>
     * @@param l_strCheckCurrentPrice - (�m�F���擾����)<BR>
     * �m�F���Ɏ擾���������B<BR>
     * �i��菈���Ŋm�F�������Ɏ������擾�ς̏ꍇ�A<BR>  
     * �@@���������Ŏ擾�ς̎����������p�������ꍇ�ɃZ�b�g�B<BR>
     * �@@�����p�������Ȃ��ꍇ�́Anull�܂���0���Z�b�g�B�j<BR>
     * @@param l_blnIsStopOrderValid - (is�X�g�b�v�����L��)<BR>
     * W�w�l�����ŁA�X�g�b�v�����ɐؑւ��������Ă��邩�ǂ�����ݒ肷��B<BR>
     * �itrue�F�@@�X�g�b�v�����ւ̐ؑւ������B<BR>
     * �@@false�F�@@�X�g�b�v�����ւ̐ؑւ������� �܂��� notW�w�l�����B�j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_tradedProduct - (�������))<BR>
     * �������<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_blnIsSellOrder - (is������)<BR>
     * �������̏ꍇ��true�A�������̏ꍇ��false���w�肷��B<BR>
     * @@param l_dblExecQuantity - (��萔��)<BR>
     * �����P��.��萔��<BR>
     * @@param l_bdlExecutedAmount - (���v�����z)<BR>
     * �����P��.���v�����z<BR>
     * @@param l_blnIsSkipAmountRange - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * @@return WEB3EquityEstimatedDeliveryPrice
     * @@throws WEB3BaseException 
     */
    public WEB3EquityEstimatedDeliveryPrice calcEstimateDeliveryAmount(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        double l_dblWLimitPrice,
        double l_dblStopOrderBasePrice,
        EqTypeExecutionConditionType l_execConditionType,
        EqTypeExecutionConditionType l_execWConditionType,
        String l_strPriceConditionType,
        String l_strOrderConditionType,
        String l_strCheckCurrentPrice,
        boolean l_blnIsStopOrderValid,
        SubAccount l_subAccount,
        WEB3EquityTradedProduct l_tradedProduct,
        double l_dblQuantity,
        boolean l_blnIsSellOrder,
        double l_dblExecQuantity,
        double l_bdlExecutedAmount,
        boolean l_blnIsSkipAmountRange) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "calcEstimateDeliveryAmount(WEB3GentradeCommission, double, double, double,"
            + "EqTypeExecutionConditionType, EqTypeExecutionConditionType, String,"
            + "String, String, boolean, SubAccount, WEB3EquityTradedProduct, double,"
            + "boolean, double, double, double)";
        log.entering(STR_METHOD_NAME);
        
        //===================================================================
        // ���O����
        //===================================================================
        // �����̃I�u�W�F�N�g��NotNull�`�F�b�N        
        if (l_commission == null || l_subAccount == null || l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        // �����R���ʃ`�F�b�NOBJ�̎擾
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                
        // �����v�Z�T�[�r�XOBJ�̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        // isSTOP���S���������X�̎擾
        long l_lngEstimatePriceCalcForm = l_orderManagerReusableValidations.
            getEstimatePriceCalcForm(l_commission.getCommissionProductCode(), l_subAccount);
        boolean l_blnIsStopQuantityRestraintBranch = 
            (l_lngEstimatePriceCalcForm == WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT);
        
        // �m�F��������double���inull�̏ꍇ0�Ƃ���j
        double l_dblCheckPrice = 0.0D;
        if (l_strCheckCurrentPrice != null)
        {
            l_dblCheckPrice = Double.parseDouble(l_strCheckCurrentPrice);
        }
        

        //===================================================================
        // ���~�b�g�����ł̊T�Z��n����v�Z
        // �i�X�g�b�v�L�����̓X�g�b�v�����ł̊T�Z��n����v�Z�����˂�j
        //===================================================================
        // �v�Z�P���i���~�b�g�����p�j�Z�o
        // (1)�@@���s���� or �A������ �� STOP���S�����X �� �s�o������
        // �@@(1-1)�m�F�����������ݒ莞�@@�F����
        // �@@(1-2)�m�F���������ݒ莞�@@�@@�F�m�F������
        // (2)��L�ȊO�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�w�l
        double l_dblCalcPrice1 = 0.0D;
        double l_dblCurrentPrice1 = 0.0D;
        if (l_dblLimitPrice == 0.0D || !l_blnIsSellOrder && l_blnIsStopQuantityRestraintBranch && 
            EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execConditionType))
        {
            if (l_dblCheckPrice == 0.0D)
            {
                l_dblCurrentPrice1 = l_orderManagerReusableValidations.calcCurrentPrice(
                    l_commission.getCommissionProductCode(), l_tradedProduct,
                    (WEB3GentradeSubAccount)l_subAccount, !l_blnIsSellOrder);
                l_dblCalcPrice1 = l_dblCurrentPrice1;
            }
            else
            {
                l_dblCalcPrice1 = l_dblCheckPrice;
            }
        }
        else
        {
            l_dblCalcPrice1 = l_dblLimitPrice;
        }
        
        // �萔���I�u�W�F�N�g�i���~�b�g�����p�j�̐���
        WEB3GentradeCommission l_commission1 = copyCommission(l_commission);
        l_commission1.setIsLimitPrice(l_dblLimitPrice != 0.0D);
        
        // �T�Z��n����v�Z�i���~�b�g�����p�j
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryAmount1 =
            this.calcEstimateDeliveryAmount(
                l_commission1, l_dblCalcPrice1, l_subAccount, l_tradedProduct, l_dblQuantity,
                l_blnIsSellOrder, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountRange, true);


        //===================================================================
        // 1��v�Z�p�^�[�����̃��^�[��
        // �i���~�b�g�����̊T�Z��n�����Ԓl�Ƃ���j
        // �@@�������̏ꍇ
        // �A�v�w�l�i���~�b�g�^�X�g�b�v�Ƃ��ɗL���j�łȂ��ꍇ
        // �B�v�w�l���������A�X�g�b�v�����L���Ƃ��ĎZ�o����ꍇ
        //===================================================================
        if (l_blnIsSellOrder || 
            !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType) ||
            l_blnIsStopOrderValid)
        {
            l_bizLogicProvider.setCommissionCalcResult(l_commission, l_commission1);
            l_estimatedDeliveryAmount1.setCheckGetCurrentPrice(l_dblCurrentPrice1);
            log.exiting(STR_METHOD_NAME);
            return l_estimatedDeliveryAmount1;
        }
        

        //===================================================================
        // �X�g�b�v�����ł̊T�Z��n����v�Z
        //===================================================================
        // �v�Z�P���i�X�g�b�v�����p�j�Z�o
        // (1)�@@���s���� or �ASTOP���S�����X �� �s�o������
        // �@@(1-1)�m�F�����������ݒ莞�@@�F����
        // �@@�@@�@@�i�������A���~�b�g�v�Z���Ɏ擾���Ă����ꍇ�͓����l���g�p����j
        // �@@(1-2)�m�F���������ݒ莞�@@�@@�F�m�F������
        // (2)��L�ȊO�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�����w�l
        double l_dblCalcPrice2 = 0.0D;
        double l_dblCurrentPrice2 = 0.0D;
        if (l_dblWLimitPrice == 0.0D || l_blnIsStopQuantityRestraintBranch && 
            EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execWConditionType))
        {
            if (l_dblCheckPrice == 0.0D)
            {
                if (l_dblCurrentPrice1 == 0.0D)
                {
                    l_dblCurrentPrice2 = l_orderManagerReusableValidations.calcCurrentPrice(
                        l_commission.getCommissionProductCode(), l_tradedProduct,
                        (WEB3GentradeSubAccount)l_subAccount, !l_blnIsSellOrder);
                }
                else
                {
                    l_dblCurrentPrice2 = l_dblCurrentPrice1;
                }
                l_dblCalcPrice2 = l_dblCurrentPrice2;
            }
            else
            {
                l_dblCalcPrice2 = l_dblCheckPrice;
            }
        }
        else
        {
            l_dblCalcPrice2 = l_dblWLimitPrice;
        }
        
        // �萔���I�u�W�F�N�g�i�X�g�b�v�����p�j�̐���
        WEB3GentradeCommission l_commission2 = copyCommission(l_commission);
        l_commission2.setIsLimitPrice(l_dblWLimitPrice != 0.0D);
        
        // �T�Z��n����v�Z�i�X�g�b�v�����p�j
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryAmount2 =
            this.calcEstimateDeliveryAmount(
                l_commission2, l_dblCalcPrice2, l_subAccount, l_tradedProduct, l_dblQuantity,
                l_blnIsSellOrder, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountRange, true);
        

        //===================================================================
        // 2��v�Z�p�^�[�����̃��^�[��
        // �i���~�b�g�^�X�g�b�v�̓��A�������̊T�Z��n�����Ԓl�Ƃ���j
        //===================================================================
        if (l_estimatedDeliveryAmount1.getEstimateDeliveryAmount() >=
            l_estimatedDeliveryAmount2.getEstimateDeliveryAmount())
        {
            l_bizLogicProvider.setCommissionCalcResult(l_commission, l_commission1);
            l_estimatedDeliveryAmount1.setCheckGetCurrentPrice(l_dblCurrentPrice1);
            log.exiting(STR_METHOD_NAME);
            return l_estimatedDeliveryAmount1;
        }
        else
        {
            l_bizLogicProvider.setCommissionCalcResult(l_commission, l_commission2);
            l_estimatedDeliveryAmount2.setCheckGetCurrentPrice(l_dblCurrentPrice2);
            log.exiting(STR_METHOD_NAME);
            return l_estimatedDeliveryAmount2;
        }
    }

    /**
     * (validate����������������) <BR>
     * �����������e�̃`�F�b�N�����{����B <BR>
     * �ivalidateChangeOrder�j <BR>
     * <BR>
     * this.validate����������������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate����������������()�Ɏw�肷�����] <BR>
     * �⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * ���������������e�F�@@�p�����[�^.���������������e <BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * @@param l_eqChangeOrderSpec - (���������������e) <BR>
     * @@return 
     * com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult
     * @@roseuid 401781770085
     */
    public EqTypeOrderValidationResult validateChangeOrder(
        SubAccount l_subAccount,
        EqTypeChangeOrderSpec l_eqChangeOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateChangeOrder(SubAccount, EqTypeChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate����������������()�ɏ������Ϗ��idelegate�j����B
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            this.validateChangeOrder(l_subAccount, l_eqChangeOrderSpec, false);
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderValidationResult;            
    }
    
    /**
     * (validate����������������)<BR>
     * �����������e�̃`�F�b�N�����{����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�����j���������R���v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_eqTypeChangeOrderSpec - (���������������e)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * @@return EqTypeOrderValidationResult
     */
    public EqTypeOrderValidationResult validateChangeOrder(
        SubAccount l_subAccount,
        EqTypeChangeOrderSpec l_eqTypeChangeOrderSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME =
            "validateChangeOrder(SubAccount, EqTypeChangeOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_eqTypeChangeOrderSpec == null)
        {
            log.debug(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        SubAccountRow l_subAccountRow = 
            (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_subAccountRow);
        
        try
        {
            //getOrderId( )
            long l_lngOrderId = l_eqTypeChangeOrderSpec.getOrderId();
            
            //getOrderUnits(����ID : long)
            OrderUnit[] l_orderUnits = getOrderUnits(l_lngOrderId);
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            
            //getOrderCateg( )
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            
            //OrderCateg != �h���������h
            if (!OrderCategEnum.ASSET.equals(l_orderCateg))
            {
                //validate�V�K����������(�⏕����, EqTypeChangeOrderSpec, boolean)
                return validateChangeOrder(
                    l_gentradeSubAccount,
                    (WEB3MarginChangeOrderSpec)l_eqTypeChangeOrderSpec,
                    l_blnIsSkipDelayStatusCheck);
            }
            
            //�����\�m�F(�⏕����, long, boolean)
            validateEnableChangeOrder(
                l_gentradeSubAccount,
                l_lngOrderId,
                l_blnIsSkipDelayStatusCheck);
            
            //create�����������e
            WEB3EquityChangeOrderSpec l_equityChangeOrderSpec = 
                (WEB3EquityChangeOrderSpec)l_eqTypeChangeOrderSpec;
            WEB3EquityNewCashBasedOrderSpec l_equityNewCashBasedOrderSpec 
                = l_equityChangeOrderSpec.createOrderSpec();
           
            //getInstance( )
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                 
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            WEB3EquityTradedProduct l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct();
            
            //validate���s�w��K��
            l_orderMgrResVal.validateMarketOrderDesignateCtrl(
                l_equityTradedProduct,
                l_equityNewCashBasedOrderSpec.isMarketOrder(),
                l_equityNewCashBasedOrderSpec.isSellOrder(),
                l_equityNewCashBasedOrderSpec.getExecConditionType());
                
            //get���񒍕��̒����P��(�����P��)
            OrderUnit l_firstOrderUnit = this.getFirstOrderUnit(l_orderUnit);
            EqtypeOrderUnitRow l_firstOrderUnitRow = (EqtypeOrderUnitRow) l_firstOrderUnit.getDataSourceObject();
            
            //getTradedProduct( )

            //validate��������
            l_orderMgrResVal.validateOrderCondition(
                l_gentradeSubAccount, 
                l_orderUnit.getOrderUnitId(), 
                l_equityTradedProduct,
                WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(), "yyyyMMdd"),
                l_equityNewCashBasedOrderSpec.getOrderExpDate(),
                l_equityNewCashBasedOrderSpec.getOrderCond(),
                l_equityNewCashBasedOrderSpec.getExecConditionType(),
                l_equityNewCashBasedOrderSpec.isOrderUntilDeadLine(),
                WEB3MarginTradingDivDef.DEFAULT,
                l_equityNewCashBasedOrderSpec.getPriceConditionType(),
                l_equityNewCashBasedOrderSpec.getMarketCode());
            
            //getTaxType( )
            TaxTypeEnum l_taxType =
                l_equityNewCashBasedOrderSpec.getTaxType();
            
            WEB3GentradeMainAccount l_account =
                new WEB3GentradeMainAccount(
                    (MainAccountRow)l_gentradeSubAccount.getMainAccount().getDataSourceObject());
            //is��������J��
            if (TaxTypeEnum.SPECIAL.equals(l_taxType) ||
                TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxType))
            {
                boolean isSpecialAccountEstablished = l_account.isSpecialAccountEstablished(
                    l_equityTradedProduct.getDailyDeliveryDate(),
                    l_gentradeSubAccount);
                if (!isSpecialAccountEstablished)
                {
                    return new EqTypeOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00026));
                }
            }
            
            //get����X( )
            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
            
            //validate����
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_equityNewCashBasedOrderSpec.getQuantity());

            //getLimitPrice( )
            double l_dbLimitPrice =
                l_equityNewCashBasedOrderSpec.getLimitPrice();
            
            //validate�����P��
            boolean l_isValidatePrice =
                l_orderMgrResVal.validatePrice(
                    l_equityNewCashBasedOrderSpec.getLimitPrice(),
                    l_equityTradedProduct,
                    l_gentradeSubAccount);
            
            if (l_isValidatePrice == false)
            {
                return new EqTypeOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00293));               
            }
            
            //get��������( )
            String l_strOrderCond =
                l_equityNewCashBasedOrderSpec.getOrderCond();
            
            //get�t�w�l��l( )
            double l_dbStopLimitPriceBasePrice =
                l_equityNewCashBasedOrderSpec.getStopLimitPriceBasePrice();
            
            //get�i�v�w�l�j���s����( )
            EqTypeExecutionConditionType l_WlimitExecCondType =
                l_equityNewCashBasedOrderSpec.getWlimitExecCondType();
            
            //get�iW�w�l�j�����w�l( )
            double l_dbWLimitPriceChange =
                l_equityNewCashBasedOrderSpec.getWLimitPriceChange();
            String l_strWLimitPriceChange =
                WEB3StringTypeUtility.formatNumber(l_dbWLimitPriceChange);
            
            WEB3EquityChangeOrderUnitEntry l_equityChangeOrderUnitEntry =
                (WEB3EquityChangeOrderUnitEntry)l_equityChangeOrderSpec.getChangeOrderUnitEntry();
            
            //validateW�w�l����()
            l_orderMgrResVal.validateWLimitPriceOrder(
                (WEB3GentradeSubAccount)l_subAccount,
                l_orderUnitRow.getOrderUnitId(),
                l_dbLimitPrice,
                l_strOrderCond,
                l_dbStopLimitPriceBasePrice,
                l_strWLimitPriceChange,
                l_WlimitExecCondType,
                l_equityChangeOrderUnitEntry.getWlimitEnableStatusDiv(),
                l_equityTradedProduct,
                l_equityNewCashBasedOrderSpec.isBuyOrder(),
                null,
                l_orderUnitRow.getOrderCateg(),
                l_equityChangeOrderUnitEntry.getAfterChangeOriginalQuantity(),
                l_equityChangeOrderUnitEntry.getChangeAfterPriceConditionType(),
                l_orderUnitRow.getOrderType());
            
            //getOrderExpDate( )
            Date l_datOrderExpDate = l_equityNewCashBasedOrderSpec.getOrderExpDate();
            
            //validateExpirationDate
            l_orderMgrResVal.validateExpirationDate(
                l_equityTradedProduct,
                l_datOrderExpDate);

            //validate��������
            l_orderMgrResVal.validateChangeItem(
                l_orderUnit,
                l_equityChangeOrderUnitEntry.getAfterChangeOriginalQuantity(),
                l_equityChangeOrderUnitEntry.getAfterChangePrice(),
                l_equityChangeOrderUnitEntry.getAfterChangeExecutionConditionType(),
                l_equityChangeOrderUnitEntry.getChangeAfterPriceConditionType(),
                l_equityChangeOrderUnitEntry.getChangeAfterOrderCondType(),
                l_equityChangeOrderUnitEntry.getChangeAfterOrderCondOperator(),
                l_equityChangeOrderUnitEntry.getChangeAfterStopOrderCondPriceBasePrice(),
                l_equityChangeOrderUnitEntry.getChangeAfterWlimitOrderCondPrice(),
                l_equityChangeOrderUnitEntry.getModifiedWlimitExecCondType(),
                l_equityChangeOrderUnitEntry.getChangeAfterIsOrderUntilDeadLine(),
                l_equityChangeOrderUnitEntry.getModifiedExpirationDate(),
                null);     
            
            //validate����������Rev���
            l_orderMgrResVal.validateChangeOrderRevUpperLimit(
                l_orderUnit,
                l_equityChangeOrderUnitEntry.getAfterChangeOriginalQuantity(),
                l_equityChangeOrderUnitEntry.getAfterChangePrice(),
                l_equityChangeOrderUnitEntry.getAfterChangeExecutionConditionType(),
                l_equityChangeOrderUnitEntry.getChangeAfterPriceConditionType());
        }
        catch (OrderValidationException l_ove)
        {
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                l_ove.getValidationResult().getProcessingResult());
        }
        catch (WEB3BaseException l_wbe)
        {
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()));
        }
        
        log.exiting(STR_METHOD_NAME);
        return new WEB3EquityOrderValidationResult(ProcessingResult.SUCCESS_RESULT,false);  
    } 

    /**
     * (�����\�m�F) <BR>
     * �w�蒍���̒��������{�\�����`�F�b�N���A�������̒����P�ʃI�u�W�F�N�g��ԋp����B <BR>
     * �ivalidateEnableChangeOrder�j <BR>
     * <BR>
     * �V�[�P���X�}�u�i�����j�����\�m�F�v�Q�ƁB <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * <BR>
     * �⏕�����I�u�W�F�N�g <BR>
     * @@param l_lngOrderId - (����ID) <BR>
     * <BR>
     * �����Ώے���ID <BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 41407CBF03CA
     */
    public EqTypeOrderUnit validateEnableChangeOrder(
        WEB3GentradeSubAccount l_subAccount,
        long l_lngOrderId,
        boolean l_blnIsSkipDelayStatusCheck)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEnableChangeOrder(WEB3GentradeSubAccount, long, boolean)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        OrderUnit l_orderUnit;
        
        try
        {
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

            //commonFirstValidationsForAllOperations(�⏕���� : SubAccount)
            l_orderMgrResVal.commonFirstValidationsForAllOperations(
                l_subAccount);
            
            //validateOrderIdForExistence(����ID : long)
            Order l_order = l_orderMgrResVal.validateOrderIdForExistence(l_lngOrderId);
            
            //validate���������\���(����,isSkip�x���󋵃`�F�b�N)
            l_orderMgrResVal.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);
            
            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            l_orderUnit  = l_orderUnits[0];
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            EqTypeProduct l_eqTypeProduct =(EqTypeProduct)l_orderUnit.getProduct();
            
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();
            
            //validate�s��R�[�h
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_eqtypeOrderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_market = l_orderMgrResVal.validateMarket(l_strMarketCode,l_strInstitutionCode);
            
            //validate�C���T�C�_�[
            l_orderMgrResVal.validateInsider(l_subAccount, l_eqTypeProduct);
            
            //validate�ڋq�����ʎ����~
            OrderTypeEnum l_orderType;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_orderType = OrderTypeEnum.EQUITY_BUY;
            }
            else
            {
                l_orderType = OrderTypeEnum.EQUITY_SELL;
            }
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_subAccount,
                l_eqTypeProduct.getProductId(),
                l_orderType);
            
            //validate�������
            WEB3EquityTradedProduct l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(
                    l_eqTypeProduct,
                    l_market);
           
            SubAccountRow l_subAccountRow = 
                (SubAccountRow)l_subAccount.getDataSourceObject();
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_subAccountRow);
            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
            
            //validate�戵�\�s��
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct);
            
			//validate�ǌ���������t�\
			Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_datOrderBizDate =
				WEB3DateUtility.getDate(
					l_eqtypeOrderUnitRow.getBizDate(),
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);
                    
			if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
			{
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
			}
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME, nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        catch (OrderValidationException ove)
        {
            ProcessingResult l_processingResult =
                ove.getValidationResult().getProcessingResult();
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
        return (EqTypeOrderUnit)l_orderUnit;
    }

    /**
     * (submit����������������) <BR>
     * ������������������o�^����B <BR>
     * �isubmitChangeOrder�̃I�[�o�[���C�h�j <BR>
     * �V�[�P���X�}�u�i�����j���������X�V�v�Q�ƁB <BR>
     * <BR>
     * (*) xTrade�̕W�������ł́A <BR>
     * �����̑㗝���͂��l������Ă��Ȃ����ߓ��Y�葱�����K�v�B <BR>
     * <BR>
     * �P�j�@@����p�X���[�h�`�F�b�N <BR>
     * �@@���������`�F�b�N.validate����p�X���[�h( )���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�㗝���͎ҁF�@@���������������e.get����( ) <BR>
     * �@@�⏕�����F�@@�i�������ҏW�j <BR>
     * �@@�p�X���[�h�F�@@�i�������ҏW�j <BR>
     * <BR>
     * �Q�j�@@�W���������\�b�h���R�[������ <BR>
     * <BR>
     * �@@super.submitChangeOrder( )���R�[������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�⏕�����F�@@�i�������ҏW�j <BR>
     * �@@���������������e�F�@@�i�������ҏW�j <BR>
     * �@@����p�X���[�h�F�@@(*1) <BR>
     * �@@isSkip�����R���F�@@true <BR>
     * <BR>
     * �@@(*1)�@@����p�X���[�h�̎w�� <BR>
     * �@@�|�㗝���͂̏ꍇ�i���������������e.get����( )��null�j�A<BR>
     *       �ڋq�̎���p�X���[�h��DB���擾���A�w�肷��B<BR>
     * �@@�|�ȊO�A�����̎���p�X���[�h���w�肷��B <BR>
     * <BR>
     * @@param l_subAccount - �⏕����
     * @@param l_eqtypeChangeOrderSpec - ���������������e
     * @@param l_strPassword - ����p�X���[�h
     * @@param l_isSkipValidateOrder - (isSkip�����R��) <BR>
     * �����R�������{���邩�ǂ����̃t���O�B <BR>
     * @@return EqTypeOrderSubmissionResult <BR> 
     * @@roseuid 402211400125
     */
    public EqTypeOrderSubmissionResult submitChangeOrder(
        SubAccount l_subAccount,
        EqTypeChangeOrderSpec l_changeOrderSpec,
        String l_strPassword,
        boolean l_isSkipValidateOrder)
    {
        final String STR_METHOD_NAME = 
            "submitChangeOrder(SubAccount, EqTypeChangeOrderSpec, String, boolean)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_changeOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        Trader l_trader = null;
        EqTypeChangeOrderSpec l_realChangeOrderSpec = null;
        //2. ���������������e::get����
        if (l_changeOrderSpec instanceof WEB3EquityChangeOrderSpec)
        {
            WEB3EquityChangeOrderSpec l_equityChangeOrderSpec = 
                (WEB3EquityChangeOrderSpec) l_changeOrderSpec;
            l_realChangeOrderSpec = l_equityChangeOrderSpec;
            l_trader = l_equityChangeOrderSpec.getTrader();            
        }
        else if (l_changeOrderSpec instanceof WEB3MarginChangeOrderSpec)
        {
            WEB3MarginChangeOrderSpec l_marginChangeOrderSpec = 
                (WEB3MarginChangeOrderSpec) l_changeOrderSpec;
            l_realChangeOrderSpec = l_marginChangeOrderSpec;
            l_trader = l_marginChangeOrderSpec.getTrader();              
        }
        else
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }

        //3. validate����p�X���[�h(Trader, SubAccount, String)(�����`�F�b�N::validate����p�X���[�h)
        WEB3GentradeOrderValidator orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        OrderValidationResult l_result = orderValidator.validateTradingPassword(
            l_trader,
            l_subAccount,
            l_strPassword);
            
        if (l_result.getProcessingResult().isFailedResult())
        {
            return new EqTypeOrderSubmissionResult(l_result.getProcessingResult());
        }

        String l_strTradingPassword = null;
        WEB3Crypt l_crypt = new WEB3Crypt();
        if (l_trader != null)
        {
            //4. getMainAccount( )
            //�㗝���͂̏ꍇ
            //�ڋq�I�u�W�F�N�g���擾����
            MainAccount l_mainAccount = l_subAccount.getMainAccount();

            //5. getTradingPassword( )
            //�ڋq�̎���p�X���[�h���擾����
            l_strTradingPassword = l_crypt.decrypt(l_mainAccount.getTradingPassword());
        }
        else
        {
            //�ȊO�A�����̎���p�X���[�h���w�肷��
            l_strTradingPassword = l_strPassword;
        }

        //6. submitChangeOrder
        //(�⏕���� : SubAccount, ���������������e : EqTypeChangeOrderSpec, ����p�X���[�h : String, isSkip�����R�� : boolean)
        OrderSubmissionResult l_eqTypeOrderSubmissionResult = 
            super.submitChangeOrder(
                l_subAccount,
                l_realChangeOrderSpec,
                l_strTradingPassword,
                l_isSkipValidateOrder);
        
        log.exiting(STR_METHOD_NAME);
        return (EqTypeOrderSubmissionResult)l_eqTypeOrderSubmissionResult;
    }

    /**
     * (validate�����������) <BR>
     * <BR>
     * �w�蒍���̎�������{�\�����`�F�b�N���A<BR>
     * ����s�\�ȏꍇ�͗�O��throw����B<BR>
     * �ivalidateCancelOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * ����Ώے������M�p����̏ꍇ�Avalidate�M�p����������\�b�h( )���R�[������B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�����j����\�m�F�v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount<BR>
     * @@param l_cancelOrderSpec<BR>
     * @@return OrderValidationResult<BR>
     * @@roseuid 41407CC000BF
     */
    public EqTypeOrderValidationResult validateCancelOrder(
        SubAccount l_subAccount,
        EqTypeCancelOrderSpec l_cancelOrderSpec)
    {
        final String STR_METHOD_NAME =
            "validateCancelOrder(SubAccount,EqTypeCancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        
        WEB3GentradeSubAccount l_gentradeSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        
        try
        {
            WEB3EquityCancelOrderSpec l_equityCancelOrderSpec = (WEB3EquityCancelOrderSpec)l_cancelOrderSpec;
  
            
            //1.1. getInstance()
            WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations =
                (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            //1.3. getOrderUnits()
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)this.getOrderUnits(l_cancelOrderSpec.getOrderId())[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.5. [�M�p��������̏ꍇ(�����J�e�S�� != �h���������h�j]
            //�Ethis.validate�M�p��������i����.�⏕�����A����.��������������e�j
            // ���R�[���A�߂�l��ԋp���A�������I������B
            if (!OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                //1.5.1. validate�M�p�������()
                return this.validateMarginOrderCancel(l_gentradeSubAccount, l_equityCancelOrderSpec);                  
            }
            
            //1.6. commonFirstValidationsForAllOperations()
            l_equityTypeOrderManagerReusableValidations.commonFirstValidationsForAllOperations(l_subAccount);            
            
            //1.7. validateOrderIdForExistence()
            Order l_order = l_equityTypeOrderManagerReusableValidations.validateOrderIdForExistence(
                l_equityCancelOrderSpec.getOrderId());
            
            //1.9. validate��������\���()
            l_equityTypeOrderManagerReusableValidations.validateOrderForCancellation(l_order);
  
            //1.10. get����X()
            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
            
            //1.11. getMarket()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
            
            //1.12. ����O���������ȊO�̏ꍇ�i�������P��.����R�[�h�iSONAR�j��"����O����"�̏ꍇ�j�̂ݎ��s
            if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                //1.12.1. validate�s��R�[�h()
                String l_strMarketCode = l_market.getMarketCode();
                String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
                l_market = l_equityTypeOrderManagerReusableValidations.validateMarket(
                    l_strMarketCode,
                    l_strInstitutionCode);
            }
            
            //1.13. getProduct()
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_orderUnit.getProduct();
            
            //1.14. ����O���������̏ꍇ�i�������P��.����R�[�h�iSONAR�j=="����O����"�̏ꍇ�j�̂ݎ��s
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                //1.14.1. validate����O������t�\()
                l_equityTypeOrderManagerReusableValidations.validateOffFloorOrderPossible(
                    l_equityProduct.getProductId(),
                    l_market.getMarketId(),
                    l_subAccount);
            }
            
            //1.15. validate�������()
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_equityTypeOrderManagerReusableValidations.validateTradedProduct(
                    l_equityProduct,
                    l_market);
            
            //1.16. validate�戵�\�s��()
            l_equityTypeOrderManagerReusableValidations.validateHandlingMarket(
                l_gentradeBranch,
                l_tradedProduct);

			//1.17. validate�ǌ���������t�\()
			Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_datOrderBizDate =
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(),
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);

			if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
			{
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(ProductTypeEnum.EQUITY);
			}
        }
        catch (NotFoundException l_nfe)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            log.error(STR_METHOD_NAME, l_sysException);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()));
        }
        catch (OrderValidationException l_ove)
        {
            log.exiting(STR_METHOD_NAME);
            return new EqTypeOrderValidationResult(
                l_ove.getValidationResult().getProcessingResult());
        }
        catch (WEB3BaseException l_wbe)
        {
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()));
        }       
        log.exiting(STR_METHOD_NAME);
        return new EqTypeOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }

    /**
     * (submit���������������) <BR>
     * �����������������o�^����B<BR>
     * �isubmitCancelOrder�̃I�[�o�[���C�h�j<BR>
     * �V�[�P���X�}�u�i�����j��������X�V�v�Q�ƁB<BR>
     * <BR>
     * (*) xTrade�̕W�������ł́A<BR>
     * ����̑㗝���͂��l������Ă��Ȃ����ߓ��Y�葱�����K�v�B<BR>
     * <BR>
     * �P�j�@@����p�X���[�h�`�F�b�N<BR>
     * �@@�����`�F�b�N.validate����p�X���[�h( )���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�㗝���͎ҁF�@@��������������e.get����( )<BR>
     * �@@�⏕�����F�@@�i�������ҏW�j<BR>
     * �@@�p�X���[�h�F�@@�i�������ҏW�j<BR>
     * <BR>
     * �Q�j�@@�W���������\�b�h���R�[������<BR>
     * <BR>
     * �@@super.submitCancelOrder( )���R�[������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�⏕�����F�@@�i�������ҏW�j<BR>
     * �@@��������������e�F�@@�i�������ҏW�j<BR>
     * �@@����p�X���[�h�F�@@(*1)<BR>
     * �@@isSkip�����R���F�@@true<BR>
     * <BR>
     * �@@(*1)�@@����p�X���[�h�̎w��<BR>
     * �@@�|�㗝���͂̏ꍇ�i��������������e.get����( )��null�j�A<BR>
     * �ڋq�̎���p�X���[�h��DB���擾���A�w�肷��B<BR>
     * �@@�|�ȊO�A�����̎���p�X���[�h���w�肷��B<BR>
     * �@@
     * @@param l_subAccount - �⏕����
     * @@param l_cancelOrderSpec - ��������������e
     * @@param l_strPassword - ����p�X���[�h
     * @@param l_isSkipValidateOrder - isSkip�����R��<BR>
     *    �����R�������{���邩�ǂ����̃t���O�B
     * @@return OrderSubmissionResult
     * @@roseuid 41407CC00119
     */
    public EqTypeOrderSubmissionResult submitCancelOrder(
        SubAccount l_subAccount,
        EqTypeCancelOrderSpec l_cancelOrderSpec,
        String l_strPassword,
        boolean l_isSkipValidateOrder)
    {
        final String STR_METHOD_NAME = "submitCancelOrder(SubAccount, EqTypeChangeOrderSpec, String, boolean)";
        log.entering(STR_METHOD_NAME);       
        if (l_subAccount == null || l_cancelOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //2. get����( )(��������������e::get����
        WEB3EquityCancelOrderSpec l_equityCancelOrderSpec = (WEB3EquityCancelOrderSpec)l_cancelOrderSpec;
        Trader l_trader = l_equityCancelOrderSpec.getTrader();

        //3. validate����p�X���[�h(Trader, SubAccount, String)(�����`�F�b�N::validate����p�X���[�h)
        WEB3GentradeOrderValidator orderValidator = new WEB3GentradeOrderValidator();
        OrderValidationResult l_result =
            orderValidator.validateTradingPassword(
                l_trader,
                l_subAccount,
                l_strPassword);
                
        if (l_result.getProcessingResult().isFailedResult())
        {
            return new EqTypeOrderSubmissionResult(l_result.getProcessingResult());
        }

        String l_strTradingPassword = null;
        WEB3Crypt l_crypt = new WEB3Crypt();
        if (l_trader != null)
        {
            //4. getMainAccount( )
            //�㗝���͂̏ꍇ
            //�ڋq�I�u�W�F�N�g���擾����
            MainAccount l_mainAccount = l_subAccount.getMainAccount();

            //5. getTradingPassword( )
            //�ڋq�̎���p�X���[�h���擾����
            l_strTradingPassword = l_crypt.decrypt(l_mainAccount.getTradingPassword());
        }
        else
        {
            //�ȊO�A�����̎���p�X���[�h���w�肷��
            l_strTradingPassword = l_strPassword;
        }

        //6. submitCancelOrder(�⏕���� : SubAccount, ��������������e : CancelOrderSpec, ����p�X���[�h : String, is�����R��Skip(��false) : boolean)
        EqTypeOrderSubmissionResult l_eqTypeOrderSubmissionResult = 
            (EqTypeOrderSubmissionResult)super.submitCancelOrder(
                l_subAccount,
                l_cancelOrderSpec,
                l_strTradingPassword,
                l_isSkipValidateOrder);

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderSubmissionResult;  
    }

    /**
     * (get����ID) <BR>
     * �w������Ɉ�v���钍���̒���ID��ԋp����B<BR>
     * �iSONAR����̃��N�G�X�g�L���[�ɊY������s���擾����ꍇ�ɗ��p�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�A���X�R�[�h��蕔�X�h�c���擾����B<BR>
     * �Q�j�@@�ȉ��̏����Œ����P�ʃe�[�u�����������A<BR>
     * �@@��v�����s�̒����h�c��ԋp����B<BR>
     * <BR>
     * [��������]<BR>
     * �@@�����P��.���XID == �i�擾�������XID�j<BR>
     * �@@�����P��.�����^�C�v == �p�����[�^.���i�^�C�v<BR>
     * �@@�����P��.���ʃR�[�h == �p�����[�^.���ʃR�[�h<BR>
     * <BR>
     * �Y���s�����݂����ꍇ�A�����s��v�����ꍇ��<BR>
     * ��O���X���[����B<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00295 <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h<BR>
     * @@param l_strBranchCode - ���X�R�[�h<BR>
     * @@param l_productTypeEnum - (���i�^�C�v) <BR>
     * �iProductTypeEnum�ɂĒ�`�j <BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h <BR>
     * @@throws WEB3BaseException
     * @@return java.lang.long
     * @@throws WEB3BaseException
     * @@roseuid 41407CC00173
     */
    public long getOrderID(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_productTypeEnum,
        String l_strOrderRequestNumber)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getOrderID(ProductTypeEnum,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        OrderManager l_orderManager =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        //�،����
        Institution l_institution = null;
        //���X
        Branch l_branch = null;
        try
        {
            //�،����
            l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);
            //���X
            l_branch =
                l_accountManager.getBranch(l_institution, l_strBranchCode);
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME,nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        OrderUnit l_orderUnit = null;
        List l_lisRecords;
        try
        {
            //���X�R�[�h --> ���XID
            Long l_lngBranchId = new Long(l_branch.getBranchId());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" branch_id = ? "); //���XID
            l_sbWhere.append(" and product_type = ? "); //�����^�C�v(QA: WEB3-EQTYPE-A-CD-0038)
            l_sbWhere.append(" and order_request_number = ? "); //���ʃR�[�h

            Object[] l_objEqtypeOrderUnitWhere = { 
                l_lngBranchId,                 //���XID
                l_productTypeEnum,         //���i�^�C�v
                l_strOrderRequestNumber  //���ʃR�[�h
                }; 

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_objEqtypeOrderUnitWhere);

            if (l_lisRecords.size() == 0)
            {
                log.error("�Y�����钍��ID�f�[�^������܂���B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����钍��ID�f�[�^������܂���B");
            }
            if (l_lisRecords.size() > 1)
            {
                log.error("�Y�����钍��ID�f�[�^�͕����s����B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����钍��ID�f�[�^�͕����s����B");
            }

            l_orderUnit =
                l_orderManager.toOrderUnit(
                    (EqtypeOrderUnitRow) l_lisRecords.get(0));
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit.getOrderId();
    }

    /**
     * (get�����P��) <BR>
     * �w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g��ԋp����B <BR>
     * �iSONAR����̃��N�G�X�g�L���[�ɊY������s���擾����ꍇ�ɗ��p�j <BR>
     *  <BR>
     * �P�j�@@�،���ЃR�[�h�A���X�R�[�h��蕔�X�h�c���擾����B <BR>
     * �Q�j�@@�ȉ��̏����Œ����P�ʃe�[�u�����������A <BR>
     * �@@��v�����s�Œ����P�ʃI�u�W�F�N�g�𐶐����ԋp����B <BR>
     *  <BR>
     * [��������] <BR>
     * �@@�����P��.���XID == �i�擾�������XID�j <BR>
     * �@@�����P��.�����^�C�v == �p�����[�^.���i�^�C�v <BR>
     * �@@�����P��.���ʃR�[�h == �p�����[�^.���ʃR�[�h <BR>
     *  <BR>
     * �Y���s�����݂����ꍇ�A�����s��v�����ꍇ�� <BR>
     * ��O���X���[����B <BR>
     * class: WEB3SystemLayerException <BR>
     * tag:   BUSINESS_ERROR_00295 <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@param l_strBranchCode - ���X�R�[�h <BR>
     * @@param l_productTypeEnum - ���i�^�C�v <BR>
     *    �iProductTypeEnum�ɂĒ�`�j <BR>
     * @@param l_strOrderRequestNumber - ���ʃR�[�h <BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 41407CC00213
     */
    public EqTypeOrderUnit getOrderUnit(
        String l_strInstitutionCode,
        String l_strBranchCode,
        ProductTypeEnum l_productTypeEnum,
        String l_strOrderRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getOrderUnit(String, String, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        OrderManager l_orderManager =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        //�،����
        Institution l_institution = null;
        //���X
        Branch l_branch = null;
        try
        {
            //�،����
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            //���X
            l_branch = l_accountManager.getBranch(l_institution, l_strBranchCode);
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME,nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        EqTypeOrderUnit l_orderUnit = null;
        List l_lisRecords = null;
        try
        {
            //���X�R�[�h --> ���XID
            Long l_lngBranchId = new Long(l_branch.getBranchId());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" branch_id = ? "); //���XID
            l_sbWhere.append(" and product_type = ? "); //�����^�C�v(QA: WEB3-EQTYPE-A-CD-0038)
            l_sbWhere.append(" and order_request_number = ? "); //���ʃR�[�h         
            
            Object[] l_objEqtypeOrderUnitWhere = { 
                l_lngBranchId,            //���XID
                l_productTypeEnum,        //���i�^�C�v
                l_strOrderRequestNumber   //���ʃR�[�h
                }; 

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                l_sbWhere.toString(),
                l_objEqtypeOrderUnitWhere);
             
            if (l_lisRecords.size() == 0)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����钍��ID�f�[�^������܂���B");
            }
            if (l_lisRecords.size() > 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00295,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Y�����钍��ID�f�[�^�͕����s������B");
            }
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_lisRecords.get(0);
            l_orderUnit = (EqTypeOrderUnit)l_orderManager.toOrderUnit(l_eqtypeOrderUnitRow);
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderUnit;
    }

    /**
     * (get�����P�ʈꗗ) <BR>
     * �w������Ɉ�v���钍���̒����P�ʃI�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * �igetOrderUnits�̃I�[�o�[���[�h�j<BR>
     * <BR>
     * �P�j�@@�߂�l�I�u�W�F�N�g�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@����������ǉ�����B<BR>
     * <BR>
     * �Q�|�P�j�@@����.��������������̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ?"<BR>
     * �@@�@@�@@�@@�@@��t������B<BR>
     * <BR>
     * �Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA<BR>
     * �@@�@@�@@�@@�@@��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B<BR>
     * �@@�@@�@@�@@�@@�������̕⏕�����I�u�W�F�N�g�A�y�ш����̖����^�C�v���ݒ肷��B<BR>
     * <BR>
     * �R�j�@@QueryProcessor.doFindAllQuery( )�ɂ��A�����P�ʃI�u�W�F�N�g��List���擾����B<BR>
     * <BR>
     * �@@�@@�@@doFindAllQuery(,�����P��Row.TYPE<BR>
     *                                      �Q�|�P�j�̌�������������,<BR>
     *                                      �����̃\�[�g����,<BR>
     *                                      null,<BR>
     *                                      �Q�|�Q�j�̌��������f�[�^�R���e�i)<BR>
     * <BR>
     * �S�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �T�j�@@�擾���������P�ʃI�u�W�F�N�g��List�̗v�f�����ȉ��̏�����Loop����B<BR>
     * �@@�@@�@@�@@�@@���������}�l�[�W��.toOrderUnit((*)�����P��ROW)���\�b�h���R�[������B<BR>
     * �@@�@@�@@�A�@@�@@�̖߂�l��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@(*)�����P��ROW�E�E�E�擾���������P�ʃI�u�W�F�N�g��<BR>
     * EqTypeOrderUnitRow�ɃL���X�g����B<BR>
     * <BR>
     * �U�j�@@ArrayList��ԋp����B<BR>
     * <BR>
     * @@param l_genSubAccount - (�⏕����) <BR>
     * �⏕�����I�u�W�F�N�g <BR>
     * @@param l_productTypeEnum - (�����^�C�v) <BR>
     * �����^�C�v�iProductTypeEnum�I�u�W�F�N�g�j <BR>
     * @@param l_srtSearchCond - �������� ������<BR>
     * @@param l_searchCondContainers - ���������f�[�^�R���e�i <BR>
     * �������� <BR>
     * @@param l_srtSortCond - �\�[�g����<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41407CC00368
     */
    public List getOrderUnits(
        WEB3GentradeSubAccount l_genSubAccount,
        ProductTypeEnum l_productTypeEnum,
        String l_strSearchCond,
        Object[] l_searchCondContainers,
        String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getOrderUnitList(SubAccount, ProductTypeEnum, String, Object[],String)";
        log.entering(STR_METHOD_NAME);
        if (l_genSubAccount == null || l_searchCondContainers == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }

        List l_lisResult = null;
        
        try
        {
            //����ID���擾����B
            Long l_lngAccountId = new Long(l_genSubAccount.getAccountId());
            //�⏕����ID���擾����B
            Long l_lngSubAccountId = new Long(l_genSubAccount.getSubAccountId());

            //�Q�j�@@����������ǉ�����B
            //�Q�|�P�j�@@����.��������������̐擪�ɁA
            //"����ID = ?�@@and�@@�⏕����ID = ? and �����^�C�v = ?"��t������B
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? "); //����ID = ?
            l_sbWhere.append(" and sub_account_id = ? "); //�⏕����ID = ?
            l_sbWhere.append(" and product_type = ? "); //�����^�C�v = ?

            //Object[] l_objEqtypeOrderUnitWhere = new Object[3 + l_searchCondContainers.length];
            //�������̕⏕�����I�u�W�F�N�g�A�y�ш����̖����^�C�v���ݒ肷��B
            Object[] l_objEqtypeOrderUnitWhere = { 
                l_lngAccountId, //����ID 
                l_lngSubAccountId, //�⏕����ID
                new Integer(l_productTypeEnum.intValue())  //�����^�C�v
                };

            //�Q�|�Q�j�@@����.���������f�[�^�R���e�i�̐擪�ɁA
            //��������������擪�ɕt�������p�����[�^���X�g��ǉ�����B
            List l_lisRecords;
            if (l_searchCondContainers == null || l_strSearchCond == null)
            { 
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
 
                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_strSortCond,
                    null,
                    l_objEqtypeOrderUnitWhere
                    );
            }
            else
            {       
                int l_size = l_objEqtypeOrderUnitWhere.length + l_searchCondContainers.length;
                    Object[] l_objWhere = new Object[l_size];
            
                int l_intWhereLength = 0;
                if (l_objEqtypeOrderUnitWhere != null)
                {
                    l_intWhereLength = l_objEqtypeOrderUnitWhere.length; 
                }
                
                for (int l_iLoop = 0; l_iLoop < l_intWhereLength; l_iLoop++)
                {
                    l_objWhere[l_iLoop] = l_objEqtypeOrderUnitWhere[l_iLoop];
                }            
                int l_intCondLength = 0;
                if (l_searchCondContainers != null)
                {
                    l_intCondLength = l_searchCondContainers.length;
                }
                for (int l_jLoop = 0; l_jLoop < l_intCondLength; l_jLoop++)
                {
                    l_objWhere[l_objEqtypeOrderUnitWhere.length + l_jLoop ] = l_searchCondContainers[l_jLoop];
                }

                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
 
                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString()  + " and " + l_strSearchCond,
                    l_strSortCond,
                    null,
                    l_objWhere
                    );
            }
            
            // �S�j�@@ArrayList�𐶐�����B
            l_lisResult = new ArrayList();
            // �T�j�@@�擾���������P�ʃI�u�W�F�N�g��List�̗v�f�����ȉ��̏�����Loop����B
            int l_intSize = l_lisRecords.size();
            for (int i = 0;i < l_intSize;i++)
            {
                l_lisResult.add(this.toOrderUnit((EqtypeOrderUnitRow)l_lisRecords.get(i)));
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME,de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);  
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisResult;
    }

    /**
     * (is�o����܂Œ����P��) <BR>
     * �iis�o����܂Œ����P��(�����P��)�̃I�[�o�[���[�h���\�b�h�j<BR>
     * <BR>
     * �u�o����܂Œ����v�̒������ǂ����𔻒肷��B<BR>
     * �u�o����܂Œ����v�̏ꍇ��true���A�u�o����܂Œ����v�ł͂Ȃ��ꍇ��false���A<BR>
     * ���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.getOrderUnit(����.�����P��ID)�ŁA�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@this.is�o����܂Œ����P��(�����P��)��delegate����B<BR>
     * @@param l_lngOrderUnitId - (�����P��ID) <BR>
     * �����P�ʃI�u�W�F�N�g.�����P��ID�B <BR>
     * @@return boolean<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 41407CC10034
     */
    public boolean isCarriedOrderUnit(long l_lngOrderUnitId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isCarriedOrderUnit(long l_lngOrderUnitId)";
        log.entering(STR_METHOD_NAME);
        
		EqTypeOrderUnit l_orderUnit;
        try
        {
            l_orderUnit = (EqTypeOrderUnit)getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME,nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
		log.exiting(STR_METHOD_NAME);
        return this.isCarriedOrderUnit(l_orderUnit);
    }

	/**
	 * (is�o����܂Œ����P��) <BR>
	 * <BR>
	 * �u�o����܂Œ����v�̒������ǂ����𔻒肷��B<BR>
	 * �u�o����܂Œ����v�̏ꍇ��true���A�u�o����܂Œ����v�ł͂Ȃ��ꍇ��false���A<BR>
	 * ���ꂼ��ԋp����B<BR>
	 * <BR>
	 * �P�j�@@this.getOrderUnit(����.�����P��ID)�ŁA�����P�ʃI�u�W�F�N�g���擾����B<BR>
	 * <BR>
	 * �Q�j�@@�擾���������P��.���񒍕��̒����P��ID��null�̏ꍇ�́Atrue��Ԃ��B <BR>
	 * �@@�@@�@@�擾���������P��.���񒍕��̒����P��ID��null�̏ꍇ�́Afalse��Ԃ��B<BR>
	 * @@param l_orderUnit - (�����P�ʃI�u�W�F�N�g) <BR>
	 * �����P�ʃI�u�W�F�N�g�B <BR>
	 * @@return boolean<BR>
	 * @@throws WEB3SystemLayerException
	 * @@roseuid 41407CC10034
	 */
	public boolean isCarriedOrderUnit(EqTypeOrderUnit l_orderUnit)
		throws WEB3SystemLayerException
	{
		final String STR_METHOD_NAME = "isCarriedOrderUnit(EqTypeOrderUnit l_orderUnit)";
		log.entering(STR_METHOD_NAME);
        
		EqtypeOrderUnitRow l_orderUnitRow = 
			(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
		boolean l_blnResult;
		if ((l_orderUnitRow.getFirstOrderUnitIdIsNull()))
		{
			l_blnResult = false;
		}
		else
		{
			l_blnResult = true;
		}
		log.exiting(STR_METHOD_NAME);
		return l_blnResult;
	}
	
    /**
     * (is�iW�w�l�j�������s) <BR>
     * �iW�w�l�j�����P�����A���s�w��Ȃ̂��A <BR>
     * �w�l�w��Ȃ̂��𔻒肷��B <BR>
     * ���s�w��̏ꍇ��true���A�w�l�w��̏ꍇ��false���A <BR>
     * ���ꂼ��ԋp����B <BR>
     * <BR>
     * �P�j�@@this.getOrderUnit(����.�����P��ID)�ŁA <BR>
     * �����P�ʃI�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �Q�j�@@�擾���������P��.�iW�w�l�j�����w�l��null <BR>
     * �܂��͂O�̏ꍇ�́Atrue��Ԃ��B <BR>
     * �@@�@@�@@�擾���������P��.�iW�w�l�j�����w�l����L�ȊO�̏ꍇ�́A <BR>
     * false��Ԃ��B <BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID) <BR>
     * �����P�ʃI�u�W�F�N�g.�����P��ID�B <BR>
     * @@return boolean
     * @@throws WEB3SystemLayerException
     * @@roseuid 41407CC1008E
     */
    public boolean isWLimitChangeMarketOrder(long l_lngOrderUnitId)
    throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "isWLimitChangeMarketOrder(long)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnReturn = false;
        OrderUnit l_orderUnit = null;

        try
        {
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME,nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }

        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

        //�擾���������P��.�iW�w�l�j�����w�l
        if ((l_orderUnitRow.getWLimitPriceIsNull()) ||
            (l_orderUnitRow.getWLimitPrice() == 0))
        {
            l_blnReturn = true;
        }
        else
        {
            l_blnReturn = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * (validate�w�l) <BR>
     * �����̎w�l���K�؂ł��邩�ǂ����̃`�F�b�N���s���B <BR>
     * �i* ���������R���`�F�b�N.validate�����P��( )�ɈϏ�����B�j<BR>
     * <BR>
     * @@param l_dblLimitPrice - �w�l�B
     * @@param l_tradedProduct - (�������) <BR>
     * ���i�E�s��֘A�̊e��G���e�B�e�B���� <BR>
     * �f�[�^���擾����ۂɎg�p����B <BR>
     * @@param l_subAccount - (�⏕����) <BR>
     * �y��Е��X���i�e�[�u���z���� <BR>
     * �T�Z���z�v�Z�������擾����ۂɎg�p����B <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41407CC100F2
     */
    public boolean validateLimitPrice(
        double l_dblLimitPrice, WEB3EquityTradedProduct l_tradedProduct, WEB3GentradeSubAccount l_subAccount)
        throws WEB3BaseException
    {
        if (Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0D;
        }
        //���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        //���������R���`�F�b�N.validate�����P��( )�ɈϏ�����
        return l_orderMgrResVal.validatePrice(
            l_dblLimitPrice,
            l_tradedProduct,
            l_subAccount);
    }

    /**
     * (validate�����R�[�h) <BR>
     * ����\�����`�F�b�N���A <BR>
     * ������K���A��ЁE�����K���̃`�F�b�N���s���B <BR>
     * �i* ���������R���`�F�b�N.validate�����R�[�h( )�ɈϏ�����B�j <BR>
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@return EqTypeProduct
     * @@throws WEB3BaseException
     * @@roseuid 40839DA903C5
     */
    public WEB3EquityProduct validateProductCode(
        String l_strProductCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        //���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        //���������R���`�F�b�N.validate�����R�[�h()�ɈϏ����� 
        return l_orderMgrResVal.validateProductCode(l_strProductCode, l_strInstitutionCode); 
             
    }

    /**
     * �ivalidate��������j<BR>
     * <BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A��������I�u�W�F�N�g��ԋp����B<BR>
     * �i* ���������R���`�F�b�N.validate�������(�⏕����, ��������, �s��, is������)�ɈϏ�����B�j<BR>
     * <BR>
     * @@param SubAccount l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param EqTypeProduct l_eqTypeProduct - (��������)<BR>
     * ���������I�u�W�F�N�g�B<BR>
     * @@param Market l_market - (�s��)<BR>
     * �s��I�u�W�F�N�g�B<BR>
     * @@param boolean l_isSellOrder - (is������)<BR>
     * �������A�������̃t���O�B<BR>
     * �������̏ꍇtrue�A�������̏ꍇfalse���w�肷��B<BR>
     * @@return TradedProduct
     * @@throws WEB3BaseException
     */
    public TradedProduct validateTradedProduct(
        SubAccount l_subAccount,
        EqTypeProduct l_eqTypeProduct,
        Market l_market,
        boolean l_isSellOrder)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.validate�������(�⏕����, ��������, �s��, is������)�ɈϏ����� 
        return l_orderMgrResVal.validateTradedProduct(
            l_subAccount,
            l_eqTypeProduct,
            l_market,
            l_isSellOrder);
    }

    /**
     * (is�����s��ʒm�v) <BR>
     * �����Ŏw�肳�ꂽ�����P�ʃI�u�W�F�N�g�̒������e���A�s��ɒʒm���K�v���ǂ����𔻒肷��B<BR>
     * �s��ւ̒����̒ʒm���K�v�ȏꍇ��true���A�s�v�ȏꍇ��false���A���ꂼ��ԋp����B<BR>
     * <BR>
     * �E�����̒����P�ʃI�u�W�F�N�g�̃v���p�e�B���A�ȉ��̔�����s���B<BR>
     * �@@�ȉ��̏����ɍ��v����ꍇ�A�s��ւ̒����̒ʒm���s�v�Ɣ��肵�Afalse��Ԃ��B<BR>
     * <BR>
     * �@@�����P��.�������ʁ������P��.�s�ꂩ��m�F�ς݂̐���<BR>
     * �@@���@@�����P��.�w�l�������P��.�s�ꂩ��m�F�ς݂̎w�l<BR>
     * �@@���@@�����P��.���s�����������P��.�s�ꂩ��m�F�ς݂̎��s����<BR>
     * �@@���@@�����P��.�l�i�����������P��.�s�ꂩ��m�F�ς݂̒l�i����<BR>
     * <BR>
     * �@@�ȊO�Atrue��Ԃ��B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��) <BR>
     * �����P�ʃI�u�W�F�N�g�B <BR>
     * @@return boolean@@throws WEB3BaseException
     * @@roseuid 41407CC1020B
     */
    public boolean isChangeMarketNotify(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if ((l_orderUnitRow.getQuantity() == l_orderUnitRow.getConfirmedQuantity()) 
            && (l_orderUnitRow.getLimitPrice() == l_orderUnitRow.getConfirmedPrice()) 
            && (l_orderUnitRow.getExecutionConditionType().equals(l_orderUnitRow.getConfirmedExecConditionType()))
            && (l_orderUnitRow.getPriceConditionType().equals(l_orderUnitRow.getConfirmedPriceConditionType())))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * (validate��������\���) <BR>
     * <BR>
     * �����̎�����\��������Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * �i* ���������R���`�F�b�N.validate��������\���( )�ɈϏ�����B�j
     * @@param l_order (����)
     * @@return void
     * @@throws WEB3BaseException
     * @@roseuid 4073914A0348
     */
    public void validateOrderForCancellation(Order l_order)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(Order)";

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.validate��������\���()�ɈϏ����� 
        try
        {
            l_orderMgrResVal.validateOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ove)
        {
            ProcessingResult l_processingResult = l_ove.getValidationResult().getProcessingResult();
            //��O���X���[����
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }
    }
    
    /**
     * (validate���������\���) <BR>
     * �����̒������\��������Ԃł��邩�ǂ������`�F�b�N����B <BR>
     * <BR>
     * this.validate���������\���()�ɏ������Ϗ��idelegate�j����B  <BR>
     * <BR>
     * [validate���������\���()�Ɏw�肷�����]  <BR>
     * �����F�@@�p�����[�^.���� <BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j<BR>
     * @@param l_order (����)
     * @@throws WEB3BaseException
     * @@roseuid 4073914A0348
     */
    public void validateOrderForChangeability(Order l_order)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order)";
        log.entering(STR_METHOD_NAME);
        
        this.validateOrderForChangeability(l_order, false);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���������\���)<BR>
     * �����̒������\��������Ԃł��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * ���������R���`�F�b�N.validate���������\���()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate���������\���()�Ɏw�肷�����]<BR>
     * �����F�@@�p�����[�^.����<BR>
     * isSkip�x���󋵃`�F�b�N�F�@@�p�����[�^.isSkip�x���󋵃`�F�b�N<BR>
     * @@param l_order - (����)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)
     * @@throws WEB3BaseException 
     */
    public void validateOrderForChangeability(
        Order l_order,
        boolean l_blnIsSkipDelayStatusCheck) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForChangeability(Order, boolean)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        //���������R���`�F�b�N.validate���������\���()�ɏ������Ϗ��idelegate�j����B
        try
        {
            l_orderManagerReusableValidations.validateOrderForChangeability(
                l_order,
                l_blnIsSkipDelayStatusCheck);
        }
        catch (OrderValidationException l_ovex)
        {
            log.exiting(STR_METHOD_NAME);
            ProcessingResult l_processingResult = l_ovex.getValidationResult().getProcessingResult();
            //��O���X���[����
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�V�K������)<BR>
     * �w��V�K�������̔����R�����s���B<BR>
     * �ivalidateOpenContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.validate�V�K������()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�V�K������()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�M�p�V�K���������e�F�@@�p�����[�^.�M�p�V�K���������e<BR>
     * �@@�����P�ʁF�@@null<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_openContractOrderSpec - (�M�p�V�K���������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B<BR>
     * @@return EqTypeNewOrderValidationResult
     */
    public EqTypeNewOrderValidationResult validateOpenContractOrder(
        SubAccount l_subAccount,
        EqTypeOpenContractOrderSpec l_openContractOrderSpec) 
    {
        final String STR_METHOD_NAME =
            "validateOpenContractOrder(SubAccount, EqTypeOpenContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeNewOrderValidationResult l_result =
            this.validateOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate�V�K������)<BR>
     * �w��V�K�������̔����R�����s���B<BR>
     * �ivalidateOpenContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jvalidate�V�K�������v�Q�ƁB<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_openContractOrderSpec - (�M�p�V�K���������e)<BR>
     * �M�p�V�K���������e�I�u�W�F�N�g�B<BR>
     * @@param l_eqtypeOrderUnit - (�����P��)<BR>
     * ���������P�ʃI�u�W�F�N�g�B<BR>
     * <BR>
     * ���t�w�l������������R�[�������ꍇ�̂݁A<BR>
     * �@@�Z�b�g�����B�ȊO�Anull�B<BR>
     * @@return EqTypeNewOrderValidationResult
     * @@roseuid 40AAC6800108
     */
    public EqTypeNewOrderValidationResult validateOpenContractOrder(
        SubAccount l_subAccount,
        EqTypeOpenContractOrderSpec l_openContractOrderSpec,
        EqTypeOrderUnit l_eqtypeOrderUnit) 
    {
        final String STR_METHOD_NAME =
            "validateOpenContractOrder(SubAccount, EqTypeOpenContractOrderSpec, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_openContractOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME);
        }
        
        SubAccountRow l_subAccountRow = 
            (SubAccountRow)l_subAccount.getDataSourceObject();
        WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_subAccountRow);
        boolean l_isShortSellingRestraint = false;
        
        try
        {
            //get �M�p�V�K���������e
            WEB3MarginOpenContractOrderSpec l_marginOpenContractOrderSpec =
                (WEB3MarginOpenContractOrderSpec)l_openContractOrderSpec;

            //���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

            //get�ٍϋ敪
            String l_strRepaymentType =
                l_marginOpenContractOrderSpec.getRepaymentType();
            log.debug("�ٍϋ敪 = " + l_strRepaymentType);
            
            //validate�M�p����(�⏕����, �ٍϋ敪)
            validateMarginOrder((WEB3GentradeSubAccount)l_subAccount, l_strRepaymentType);
            log.debug("validate�M�p����(�⏕����, �ٍϋ敪)�����s");            
            
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("�،���ЃR�[�h = " + l_strInstitutionCode);
            
            //���͎s��R�[�h�擾
            String l_strMarketCode =
                l_marginOpenContractOrderSpec.getMarketCode();
            log.debug("���͎s��R�[�h = " + l_strMarketCode);

            //validate�s��R�[�h
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                    l_strMarketCode,
                    l_strInstitutionCode);
            log.debug("validate�s��R�[�h�����s");
            
            //���͖����R�[�h�擾
            String l_strProductCode =
                l_marginOpenContractOrderSpec.getProductCode();
            log.debug("���͖����R�[�h = " + l_strProductCode);
            
            //validate�����R�[�h�i�M�p�j
            WEB3EquityProduct l_equityProduct =
                l_orderMgrResVal.validateProductCode(
                    l_strProductCode,
                    l_strInstitutionCode,
                    l_strRepaymentType);
            log.debug("validate�����R�[�h�i�M�p�j�����s");
            
            //validate�C���T�C�_�[
            l_orderMgrResVal.validateInsider(l_gentradeSubAccount, l_equityProduct);
            log.debug("validate�C���T�C�_�[�����s");
            
            // get �������
            OrderTypeEnum l_orderTypeEnum;
            if (l_marginOpenContractOrderSpec.isLongOrder())
            {
                l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            }
            log.debug("������� = " + l_orderTypeEnum);
            
            //validate�ڋq�����ʎ����~
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_gentradeSubAccount,
                l_equityProduct.getProductId(),
                l_orderTypeEnum);
            log.debug("validate�ڋq�����ʎ����~�����s");
                
            //isShortOrder
            boolean l_isShortOrder =
                l_marginOpenContractOrderSpec.isShortOrder();
            log.debug("l_isShortOrder = " + l_isShortOrder);

            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();

            //validate��������i�M�p�j(��������, �s��, ���X, String, OrderCategEnum, boolean)
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderMgrResVal.validateTradedProductForMarginTrading(
                    l_gentradeSubAccount,
                    l_equityProduct,
                    l_market,
                    l_gentradeBranch,
                    l_strRepaymentType,
                    OrderCategEnum.OPEN_MARGIN,
                    l_isShortOrder);
            log.debug("validate��������i�M�p�j�����s");
            
            //validate���s�w��K���i�M�p�j(�������, String, OrderCategEnum, 
            //    boolean, boolean, EqTypeExecutionConditionType)
            //��������F�@@validate��������i�M�p�j( )�̖߂�l�́A��������I�u�W�F�N�g
            //�ٍϋ敪�F�@@�M�p�V�K���������e.get�ٍϋ敪( )
            //�����J�e�S���F�@@OrderCategEnum.�h�V�K�������h�iOPEN_MARGIN�j
            //is���s�F�@@�M�p�V�K���������e.isMarketOrder( )
            //is�����F�@@�M�p�V�K���������e.isShortOrder( )
            //���s�����F�@@�M�p�V�K���������e.getExecConditionType( )
            l_orderMgrResVal.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                OrderCategEnum.OPEN_MARGIN,
                l_marginOpenContractOrderSpec.isMarketOrder(),
                l_isShortOrder,
			    l_marginOpenContractOrderSpec.getExecConditionType());
            log.debug("validate���s�w��K���i�M�p�j�����s");
            
            //validate��������J�݁i�M�p�j(�⏕����, TaxTypeEnum, Date)
            //�⏕�����F�@@�����̕⏕�����I�u�W�F�N�g 
            //�ŋ敪�F�@@�M�p�V�K���������e.getTaxType( ) 
            //��n���F�@@�������.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                (WEB3GentradeSubAccount)l_subAccount,
                l_marginOpenContractOrderSpec.getTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());
            log.debug("validate��������J�݁i�M�p�j�����s");
            
            //get�ٍϊ����l( )
            double l_dblRepaymentNum =
                l_marginOpenContractOrderSpec.getRepaymentNum();
            log.debug("�ٍϊ����l = " + l_dblRepaymentNum);

            //validate�戵�\�s��i�M�p�j
            //���X�F�@@�⏕����.get����X( ) 
            //��������F�@@validate�������( )�̖߂�l�̎�������I�u�W�F�N�g 
            //�s��R�[�h�F�@@�M�p�V�K���������e.getMarketCode( ) 
            //�ٍϋ敪�F�@@�M�p�V�K���������e.get�ٍϋ敪( ) 
            //�ٍϊ����l�F�@@�M�p�V�K���������e.get�ٍϊ����l( ) 
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_strMarketCode,
                l_strRepaymentType,
                l_dblRepaymentNum);
            log.debug("validate�戵�\�s��i�M�p�j�����s");

            //validate��������戵�K��(TaxTypeEnum, ��������, boolean)
            //�ŋ敪�F�@@�M�p�V�K���������e.getTaxType( ) 
            //���������F�@@validate�����R�[�h( )�̖߂�l�̊��������I�u�W�F�N�g 
            //is�������F�@@�M�p�V�K���������e.isLongOrder( ) 
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_marginOpenContractOrderSpec.getTaxType(),
                l_equityProduct,
                l_marginOpenContractOrderSpec.isLongOrder());
            log.debug("validate��������戵�K�������s");

            // get ����������
            Date l_orderExpDate =
                l_marginOpenContractOrderSpec.getOrderExpDate();
            log.debug("���������� = " + l_orderExpDate);
            
			Date l_datFirstOrderBizDate = null;
			if ((l_marginOpenContractOrderSpec.getFirstOrderUnitId() != null) &&
			    (l_marginOpenContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
			{
				//�����J�z���̔����R��
				EqTypeOrderUnit l_orderUnit = null;
				try
				{
					l_orderUnit = (EqTypeOrderUnit)getOrderUnit(
					l_marginOpenContractOrderSpec.getFirstOrderUnitId().longValue());
				}
				catch (NotFoundException l_nfe)
				{
					return new EqTypeNewOrderValidationResult(
					    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
				}
				EqtypeOrderUnitRow l_orderUnitRow =
				    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
				l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
			}
            //validate��������
            l_orderMgrResVal.validateOrderCondition(
                l_gentradeSubAccount,
                0,
                l_equityTradedProduct,
			    l_datFirstOrderBizDate,
                l_orderExpDate,
                l_marginOpenContractOrderSpec.getOrderConditionType(),
                l_marginOpenContractOrderSpec.getExecConditionType(),
                l_marginOpenContractOrderSpec.isCarriedOrder(),
                l_strRepaymentType,
                l_marginOpenContractOrderSpec.getPriceConditionType(),
                l_marginOpenContractOrderSpec.getMarketCode());
            log.debug("validate�������������s");
            
            //validate�����i�M�p�j
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //��������F�@@validate�������( )�̖߂�l�̎�������I�u�W�F�N�g 
            //���X�F�@@�⏕����.get����X( ) 
            //�����F�@@�M�p�V�K���������e.getQuantity( ) 
            //������ʁF�@@�M�p�V�K���������e.isLongOrder( )==true�̏ꍇ�AOrderTypeEnum.�M�p�������� 
            //�@@�@@�@@          �M�p�V�K���������e.isLongOrder( )==false�̏ꍇ�AOrderTypeEnum.�M�p�������� 
            //�ٍϋ敪�F�@@�M�p�V�K���������e.get�ٍϋ敪( ) 
            //�ٍϊ����l�F�@@�M�p�V�K���������e.get�ٍϊ����l( ) 
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_marginOpenContractOrderSpec.getQuantity(),
                l_orderTypeEnum,
                l_strRepaymentType,
                l_dblRepaymentNum);
            log.debug("validate�����i�M�p�j�����s");
            
            //validate�����P��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�w�l�F�@@�M�p�V�K���������e.getLimitPrice( ) 
            //��������F�@@validate��������i�M�p�j( )�̖߂�l�̎�������I�u�W�F�N�g 
            //�⏕�����F�@@�����̕⏕�����I�u�W�F�N�g 
            if (l_marginOpenContractOrderSpec.isLimitOrder())
            {
                boolean l_isValidatePrice =
                    l_orderMgrResVal.validatePrice(
                        l_marginOpenContractOrderSpec.getLimitPrice(),
                        l_equityTradedProduct,
                        l_subAccount);
                log.debug("validate�����P�������s");
            
                if (l_isValidatePrice == false)
                {
                    log.debug("l_isValidatePrice = " + l_isValidatePrice);
                    return new WEB3MarginNewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00293),
                        l_isShortSellingRestraint);
                }                
            }
            
            //1.26.validateW�w�l����(�⏕����, long, double, String, double, 
            //String, EqTypeExecutionConditionType, String, �������, boolean, 
            //String, OrderCategEnum, double, String, OrderTypeEnum)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕���� : �����̕⏕�����I�u�W�F�N�g 
            //�����P�ʂh�c : 0(�F�V�K����)  
            //�w�l : �M�p�V�K���������e.getLimitPrice( ) 
            //�������� : �M�p�V�K���������e.get��������( )  
            //���������P�� : �M�p�V�K���������e.get�t�w�l��l( ) 
            //�iW�w�l�j�����w�l : �M�p�V�K���������e.get�iW�w�l�j�����w�l( ) 
            //�iW�w�l�j���s���� : �M�p�V�K���������e.get�i�v�w�l�j���s����( )  
            //�iW�w�l�j�L����ԋ敪 : null�i�Œ�j 
            //������� : validate�������( )�̖߂�l�̎�������I�u�W�F�N�g 
            //is������ : �M�p�V�K���������e.isLongOrder( ) 
            //�ٍϋ敪�F�@@�M�p�V�K���������e.get�ٍϋ敪( ) 
            //�����J�e�S���F�@@OrderCategEnum.OPEN_MARGIN�i�V�K�������j 
            //�����F �M�p�V�K���������e.getQuantity() 
            //�l�i�����F �M�p�V�K���������e.get�l�i����( ) 
            //������ʁF 
            // [isLongOrder()�̖߂�l == true�̏ꍇ] 
            //�@@�@@�h�V�K���������h���Z�b�g�B 
            // [isLongOrder()�̖߂�l == false�̏ꍇ] 
            //�@@�@@�h�V�K���������h���Z�b�g�B 
            l_orderMgrResVal.validateWLimitPriceOrder(
                (WEB3GentradeSubAccount)l_subAccount,
                0L,
                l_marginOpenContractOrderSpec.getLimitPrice(),
                l_marginOpenContractOrderSpec.getOrderConditionType(),
                l_marginOpenContractOrderSpec.getStopOrderPrice(),
                WEB3StringTypeUtility.formatNumber(l_marginOpenContractOrderSpec.getWLimitPrice()),
                l_marginOpenContractOrderSpec.getWlimitExecCondType(),
                null,
                l_equityTradedProduct,
                l_marginOpenContractOrderSpec.isLongOrder(),
                l_marginOpenContractOrderSpec.getRepaymentType(),
                OrderCategEnum.OPEN_MARGIN,
                l_marginOpenContractOrderSpec.getQuantity(),
                l_marginOpenContractOrderSpec.getPriceConditionType(),
                l_orderTypeEnum);
 
            //validate�V�K��������
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�����̕⏕�����I�u�W�F�N�g 
            //������F�@@�M�p�V�K���������e.get�����( ) 
            //������ʁF�@@�M�p�V�K���������e.isLongOrder( )==true�̏ꍇ�AOrderTypeEnum.�M�p�������� 
            //      �M�p�V�K���������e.isLongOrder( )==false�̏ꍇ�AOrderTypeEnum.�M�p�������� 
            //��������F�@@���������R���ʃ`�F�b�N.validate��������i�M�p�j�̖߂�l
			//�����P�ʁF�@@null
            if (l_eqtypeOrderUnit == null)
            {
                l_orderMgrResVal.validateMaxOpenMarginAmount(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_marginOpenContractOrderSpec.getContractAmount(),
                    l_orderTypeEnum,
                    l_equityTradedProduct,
                    null);
                log.debug("validate�V�K�������������s");
                //validate�V�K���������
                //�����͈ȉ��̒ʂ�ɐݒ肷��B 
                //�⏕�����F�@@�����̕⏕�����I�u�W�F�N�g 
                //�������ʁF�@@�M�p�V�K���������e.getQuantity( )  
                //������ʁF�@@�M�p�V�K���������e.isLongOrder( )==true�̏ꍇ�AOrderTypeEnum.�M�p�������� 
                //      �M�p�V�K���������e.isLongOrder( )==false�̏ꍇ�AOrderTypeEnum.�M�p�������� 
                //��������F�@@���������R���ʃ`�F�b�N.validate��������i�M�p�j�̖߂�l
                //�����P�ʁF�@@null
                l_orderMgrResVal.validateMaxOpenMarginQuantity(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_marginOpenContractOrderSpec.getQuantity(),
                    l_orderTypeEnum,
                    l_equityTradedProduct,
                    null);
                log.debug("validate�V�K��������������s");
            }
            
            //is�󔄂�K��
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
            if (l_eqtypeOrderUnit != null)
            {
                l_eqtypeOrderUnitRow =
                    (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
            }
            l_isShortSellingRestraint = l_orderMgrResVal.isShortSellingRestraint(
                l_gentradeSubAccount,
                l_equityTradedProduct,
                l_marginOpenContractOrderSpec.getQuantity(),
                l_orderTypeEnum,
                l_marginOpenContractOrderSpec.isMarketOrder(),
                l_marginOpenContractOrderSpec.getExecConditionType(),
                l_marginOpenContractOrderSpec.getPriceConditionType(),
                l_eqtypeOrderUnitRow);
            log.debug("validate�󔄂�K�������s");

            //validate�@@�\�a������(�⏕����)
            l_orderMgrResVal.validateMechanismDepositAgree(l_subAccount);
        }
        catch (WEB3BaseException l_wbe)
        {
            return new WEB3MarginNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()),
                l_isShortSellingRestraint);
        }
        
        log.exiting(STR_METHOD_NAME);
        return new WEB3MarginNewOrderValidationResult(
            ProcessingResult.SUCCESS_RESULT,
            l_isShortSellingRestraint);        
    }
    
    /**
     * (validate�M�p����)<BR>
     * �M�p��������̋��ʃ`�F�b�N�����{����B<BR>
     * <BR>
     * �ȉ��̃`�F�b�N���s���B<BR>
     * �@@�|��t���ԃ`�F�b�N<BR>
     * �@@�|�V�X�e����~���`�F�b�N<BR>
     * �@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j<BR>
     * �@@�|�M�p���{��Ѓ`�F�b�N<BR>
     * �@@�|�M�p�q�`�F�b�N�i�M�p�����J�݃`�F�b�N�j<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�����jvalidate�M�p�����v�Q�ƁB<BR>
     * <BR>
     * ���X.is�M�p�戵���{( )<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00746<BR>
     * �ڋq.is�M�p�����J��( )<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00747<BR>
     * �̂����ꂩ��false��Ԃ����ꍇ�́A<BR>
     * �G���[���R�ɉ�������O��throw����B<BR>
     * @@param l_genSubAccount - �⏕�����I�u�W�F�N�g�B
     * @@param l_strRepaymentType - �ٍϋ敪<BR>
     * <BR>
     * 0�F�w��Ȃ�<BR>
     * 1�F���x�M�p<BR>
     * 2�F��ʐM�p<BR>
     * @@roseuid 40AACA870127
     */
    public void validateMarginOrder(WEB3GentradeSubAccount l_genSubAccount, String l_strRepaymentType) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "validateMarginOrder(WEB3GentradeSubAccount, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_genSubAccount == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {

            //1) ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

            //2) validate������t�\
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("validate������t�\�����s���܂�");
            
            //3) commonFirstValidationsForAllOperations()    
            l_orderMgrResVal.commonFirstValidationsForAllOperations(
                l_genSubAccount);
            log.debug("commonFirstValidationsForAllOperations�����s���܂�");
            
            //get ���X
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();

            //4)  is�M�p�戵���{
            boolean l_isMarginTradeEnforcement =
                l_gentradeBranch.isMarginTradeEnforcement(l_strRepaymentType);
            log.debug("is�M�p�戵���{ = " + l_isMarginTradeEnforcement);
            
            if (!l_isMarginTradeEnforcement)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00746,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get �ڋq
            WEB3GentradeMainAccount l_genMainAccount =
                new WEB3GentradeMainAccount(l_genSubAccount.getAccountId());

            //5) is�M�p�����J��
            boolean l_isMarginAccountEstablished =
                l_genMainAccount.isMarginAccountEstablished(l_strRepaymentType);            
            log.debug("is�M�p�����J�� = " + l_isMarginAccountEstablished);
            
            if (!l_isMarginAccountEstablished)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00747,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (OrderValidationException ove)
        {
            throw new WEB3BusinessLayerException(
                ove.getValidationResult().getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                ove.getMessage());
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�ԍϒ���)<BR>
     * �w��ԍϒ����̔����R�����s���B<BR>
     * �ivalidateSettleContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jvalidate�ԍϒ����v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_settleContractOrderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B
     * @@param l_contract - (����)<BR>
     * �����I�u�W�F�N�g�B
     * @@return EqTypeNewOrderValidationResult
     * @@roseuid 40BA933203B1
     */
    public EqTypeNewOrderValidationResult validateSettleContractOrder(
        SubAccount l_subAccount, 
        EqTypeSettleContractOrderSpec l_settleContractOrderSpec,
        WEB3EquityContract l_contract) 
    {
        String STR_METHOD_NAME = 
            "validateSettleContractOrder(WEB3GentradeSubAccount, EqTypeSettleContractOrderSpec, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_settleContractOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        
        try
        {            
            WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
            WEB3MarginSettleContractOrderSpec l_marginSettleContractOrderSpec = (WEB3MarginSettleContractOrderSpec)l_settleContractOrderSpec;
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations)
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            WEB3EquityContract l_equityContract = l_contract;
            if (l_contract == null)
            {
                //�ԍϒ������e�Ɋ֘A���錈�ό����G���g���̔z����擾����B
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys = 
                    l_marginSettleContractOrderSpec.getSettleContractOrderEntries();            
                
                if (l_eqTypeSettleContractOrderEntrys == null || l_eqTypeSettleContractOrderEntrys.length == 0)
                {
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
                }
                
                //�擾�������ό����G���g���z��̂P���ڂ�茚���h�c���擾����B
                long l_lngContractId = l_eqTypeSettleContractOrderEntrys[0].getContractId();
                log.debug("l_lngContractId = " + l_lngContractId);
                
                WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
                            
                //get����(long)    
                l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
            
            EqtypeContractRow l_eqtypeContractRow = 
                (EqtypeContractRow)l_equityContract.getDataSourceObject();
                
            //get �ٍϋ敪
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();
            //get �ٍϊ����l
            int l_intRepaymentNum = l_eqtypeContractRow.getRepaymentNum();
            //get �s��h�c
            long l_lngMarketId = l_equityContract.getMarketId();
            //get ���敪
            ContractTypeEnum l_contractType = l_eqtypeContractRow.getContractType();
            
            //validate�M�p����(�⏕����, String)
            validateMarginOrder(l_genSubAccount,l_strRepaymentType);
            
            //get �s��
            WEB3GentradeMarket l_genMarket = (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
            
            //get �،���ЃR�[�h
            String  l_strInstitutionCode = l_genSubAccount.getInstitution().getInstitutionCode();
            
            //validate�s��R�[�h
            l_genMarket = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                l_genMarket.getMarketCode(),
                l_strInstitutionCode);
            
            //get ����
            EqTypeProduct l_eqTypeProduct = (EqTypeProduct)l_equityContract.getProduct();
            
            //validate�����R�[�h�i�M�p�j
            WEB3EquityProduct l_equityProduct =
                l_orderMgrResVal.validateProductCode(
                l_eqTypeProduct.getProductCode(),
                l_strInstitutionCode,
                l_strRepaymentType);
            
            //validate�C���T�C�_�[
            l_orderMgrResVal.validateInsider(l_genSubAccount, l_equityProduct);

            //get �������
            OrderTypeEnum l_orderTypeEnum;
            if (l_equityContract.isLong())
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            }
            
            //validate�ڋq�����ʎ����~
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_genSubAccount,
                l_equityProduct.getProductId(),
                l_orderTypeEnum);

            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();
            
            //validate��������i�M�p�j
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderMgrResVal.validateTradedProductForMarginTrading(
                    l_genSubAccount,
                    l_equityProduct,
                    l_genMarket,
                    l_gentradeBranch,
                    l_strRepaymentType,
                    OrderCategEnum.CLOSE_MARGIN,
                    l_equityContract.isShort());
            
            //validate���s�w��K���i�M�p�j(�������, String, OrderCategEnum,
            //    boolean, boolean, EqTypeExecutionConditionType)
            //��������F�@@validate��������i�M�p�j( )�̖߂�l�́A��������I�u�W�F�N�g
            //�ٍϋ敪�F�@@����.�ٍϋ敪
            //�����J�e�S���F�@@OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j
            //is���s�F�@@�M�p�ԍϒ������e.isMarketOrder( )
            ///is�����F�@@����.isShort( )
            //���s�����F�@@�M�p�ԍϒ������e.getExecConditionType( )
            l_orderMgrResVal.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                OrderCategEnum.CLOSE_MARGIN,
                l_marginSettleContractOrderSpec.isMarketOrder(),
                l_equityContract.isShort(),
			    l_marginSettleContractOrderSpec.getExecConditionType());
            
            //validate�戵�\�s��i�M�p�j
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //���X�F�@@�⏕����.get����X( ) 
            //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g 
            //�s��R�[�h�F�@@�s��.getMarketCode() 
            //�ٍϋ敪�F�@@����.�ٍϋ敪 
            //�ٍϊ����l�F�@@����.�ٍϊ����l 
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);
            
            //validate��������J�݁i�M�p�j
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�����̕⏕���� 
            //�ŋ敪�F�@@�M�p�ԍϒ������e.getTaxType( ) 
            //��n���F�@@�������.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                l_genSubAccount,
                l_marginSettleContractOrderSpec.getTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());
            
            //validate��������戵�K��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�ŋ敪�F�@@�M�p�ԍϒ������e.getTaxType( ) 
            //���������F�@@validate�����R�[�h�i�M�p�j( )�̖߂�l�̊��������I�u�W�F�N�g 
            //is�������F�@@����.isShort( )��true�Ȃ�true���w��A�ȊOfalse���w�� 
            //���s��ɑ΂��Ĕ����ǂ���̒����Ȃ̂����w�肷�邽�߁A���敪�Ƌt�ɂȂ�(���Δ���)
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_marginSettleContractOrderSpec.getTaxType(),
                l_equityProduct,
                l_equityContract.isShort());
            
			Date l_datFirstOrderBizDate = null;
			if ((l_marginSettleContractOrderSpec.getFirstOrderUnitId() != null) &&
			    (l_marginSettleContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
			{
				//�����J�z���̔����R��
				EqTypeOrderUnit l_orderUnit = null;
				try
				{
					l_orderUnit = (EqTypeOrderUnit)getOrderUnit(
					    l_marginSettleContractOrderSpec.getFirstOrderUnitId().longValue());
				}
				catch (NotFoundException l_nfe)
				{
					return new EqTypeNewOrderValidationResult(
					    ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.SYSTEM_ERROR_80005));
				}
				EqtypeOrderUnitRow l_orderUnitRow =
				(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
				l_datFirstOrderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(),"yyyyMMdd");
			}
            //validate��������
            l_orderMgrResVal.validateOrderCondition(
                l_genSubAccount,
                0,
                l_equityTradedProduct,
			    l_datFirstOrderBizDate,
                l_marginSettleContractOrderSpec.getOrderExpDate(),
                l_marginSettleContractOrderSpec.getOrderConditionType(),
                l_marginSettleContractOrderSpec.getExecConditionType(),
                l_marginSettleContractOrderSpec.isCarriedOrder(),
                l_strRepaymentType,
                l_marginSettleContractOrderSpec.getPriceConditionType(),
                l_genMarket.getMarketCode());
            
           //validate�����i�M�p�j
           //�����͈ȉ��̒ʂ�ɐݒ肷��B 
           //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g 
           //���X�F�@@�⏕����.get����X( ) 
           //�����F�@@�M�p�ԍϒ������e.getTotalQuantity( ) 
           //������ʁF�@@ 
           //����.isLong( )==true�̏ꍇ�AOrderTypeEnum.�M�p�����ԍϒ��� 
           //����.isLong( )==false�̏ꍇ�AOrderTypeEnum.�M�p�����ԍϒ��� 
           //�ٍϋ敪�F�@@����.�ٍϋ敪 
           //�ٍϊ����l�F�@@����.�ٍϊ����l 
           l_orderMgrResVal.validateQuantity(
               l_equityTradedProduct,
               l_gentradeBranch,
               l_marginSettleContractOrderSpec.getTotalQuantity(),
               l_orderTypeEnum,
               l_strRepaymentType,
               l_intRepaymentNum);

            //validate���ό����G���g���������P��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g
            //���ό����G���g���ꗗ�F�@@�M�p�ԍϒ������e�Dget���ό����G���g���ꗗ() 
            l_orderMgrResVal.validateEverySettleContractOrderEntryLotSize(
                l_equityTradedProduct,
                l_marginSettleContractOrderSpec.getSettleContractOrderEntries()); 
                          
           // validate���ϑ�������
           if (l_contract == null)
           {
               l_orderMgrResVal.validateSettleContractTotalQuantity(
                   l_genSubAccount,
                   0,
                   l_equityTradedProduct,
                   l_marginSettleContractOrderSpec.getTaxType(),
                   l_strRepaymentType,
                   l_intRepaymentNum,
                   l_marginSettleContractOrderSpec.getTotalQuantity(),
                   l_contractType);
           }
           
           boolean l_isValidatePrice = false;
           //validate�����P��
           //�����͈ȉ��̒ʂ�ɐݒ肷��B 
           //�w�l�F�@@�M�p�ԍϒ������e.getLimitPrice( ) 
           //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g 
           //�⏕�����F�@@�����̕⏕�����I�u�W�F�N�g
           if (l_marginSettleContractOrderSpec.isLimitOrder())
           {
               l_isValidatePrice =
                   l_orderMgrResVal.validatePrice(
                       l_marginSettleContractOrderSpec.getLimitPrice(),
                       l_equityTradedProduct,
                       l_genSubAccount);
               if (l_isValidatePrice == false)
               {
                   return new EqTypeNewOrderValidationResult(
                       ProcessingResult.newFailedResultInstance(
                           WEB3ErrorCatalog.BUSINESS_ERROR_00293));
               }                
           }
           
           //validateW�w�l����(�⏕����, long, double, String, double, String,
           //EqTypeExecutionConditionType, String, �������, boolean, String,
           //OrderCategEnum, double, String, OrderTypeEnum)
            //   �����͈ȉ��̒ʂ�ɐݒ肷��B 
            //   �⏕�����F�@@�����̕⏕�����I�u�W�F�N�g 
            //   �����P�ʂh�c�F�@@0(�F�V�K����) 
            //   �w�l�F�@@�M�p�ԍϒ������e.getLimitPrice( ) 
            //   ���������F�@@�M�p�ԍϒ������e.get��������( )  
            //   ���������P���F�@@�M�p�ԍϒ������e.get�t�w�l��l( ) 
            //   �iW�w�l�j�����w�l�F�@@�M�p�ԍϒ������e.get�iW�w�l�j�����w�l( ) 
            //   �iW�w�l�j���s�����F�@@�M�p�ԍϒ������e.get�i�v�w�l�j���s����( )  
            //   �iW�w�l�j�L����ԋ敪�F�@@null�i�Œ�j 
            //   ��������F�@@validate�������( )�̖߂�l�̎�������I�u�W�F�N�g 
            //   is�������F�@@����.isShort( )
            //   �ٍϋ敪�F�@@����.�ٍϋ敪 
            //   �����J�e�S���F�@@OrderCategEnum.CLOSE_MARGIN�i�ԍϒ����j 
            //   �����F�@@�M�p�ԍϒ������e.getTotalQuantity( ) 
            //   �l�i�����F�@@�M�p�ԍϒ������e.get�l�i����( ) 
            //   ������ʁF�@@ 
            //   �@@����.isLong( )==true�̏ꍇ�AOrderTypeEnum.�M�p�����ԍϒ��� 
            //   �@@����.isLong( )==false�̏ꍇ�AOrderTypeEnum.�M�p�����ԍϒ��� 
           l_orderMgrResVal.validateWLimitPriceOrder(
               l_genSubAccount,
               0L,
               l_marginSettleContractOrderSpec.getLimitPrice(),
               l_marginSettleContractOrderSpec.getOrderConditionType(),
               l_marginSettleContractOrderSpec.getStopOrderPrice(),
               WEB3StringTypeUtility.formatNumber(l_marginSettleContractOrderSpec.getWLimitPrice()),
               l_marginSettleContractOrderSpec.getWlimitExecCondType(),
               null,
               l_equityTradedProduct,
               l_equityContract.isShort(),
               l_strRepaymentType,
               OrderCategEnum.CLOSE_MARGIN,
               l_marginSettleContractOrderSpec.getTotalQuantity(),
               l_marginSettleContractOrderSpec.getPriceConditionType(),
               l_orderTypeEnum);

           //validate�@@�\�a������(�⏕����)
           l_orderMgrResVal.validateMechanismDepositAgree(l_subAccount);
        }
        catch (NotFoundException nfe)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            log.error(STR_METHOD_NAME, l_sysException);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()));
        }
        catch (WEB3BaseException l_wbe)
        {
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()));
        }
        
        log.exiting(STR_METHOD_NAME);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }
    
    /**
     * (calc�����������)<BR>
     * ��������������Z�o���ĕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jcalc������������v�����킹�ĎQ�ƁB<BR>
     * <BR>
     * �P�j�@@��������������v�Z����B<BR>
     * <BR>
     * �P�|�P�j�@@�����v�Z�T�[�r�X.calc�S���������()�ɂāA<BR>
     *     �S����������i���o�����j���v�Z����B<BR>
     * <BR>
     * �@@[calc�S��������� ����]<BR>
     * �@@�����F�@@�i����.�����|����.��萔�ʁj�̌���<BR>
     * �@@�v�Z�P���F�@@�v�Z�P��<BR>
     * �@@���XID�F�@@�⏕����.���XID<BR>
     * �@@�萔�����i�R�[�h�F�@@�萔��.get�萔�����i�R�[�h()<BR>
     * �@@is�w�l�F�@@�萔��.is�w�l( )<BR>
     * <BR>
     * �P�|�Q�j�@@�S����������i���v�l�j���v�Z����B<BR>
     * <BR>
     * �@@�S����������i���v�l�j �� �����v�Z�T�[�r�X.calc�S���������()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�{ ����.���v�����z<BR>
     * <BR>
     * �Q�j�@@������z�l�`�F�b�N<BR>
     * �@@�|�i�p�����[�^.isSkip���z�`�F�b�N == false�j�̏ꍇ�̂݁B<BR>
     * �@@�����R���ʃ`�F�b�N.validate����\������z()�ɂĒ�����������̋��z�`�F�b�N���s���B<BR>
     * �@@<BR>
     * �@@[validate����\������z ����]<BR>
     * �@@���X�F�@@�p�����[�^.�⏕����.get����X()<BR>
     * �@@�s��F�@@�p�����[�^.�������.get�s��()<BR>
     * �@@���z�F�@@�P�|�Q�j�̌v�Z���ʁi�S����������i���v�l�j�j<BR>
     * �@@�����^�C�v�F�@@�p�����[�^.�⏕����.get�ڋq().�����^�C�v<BR>
     * <BR>
     * �@@����O�����������ꍇ�́A���̗�O�����̂܂�throw����B<BR>
     * <BR>
     * �R�j�@@�萔���I�u�W�F�N�g�ɁA�P�|�Q�j�̌v�Z���ʂ̍S����������i���v�l�j��<BR>
     * �@@�@@�@@�@@�@@�u���o��v�Z�p����v�Ƃ��ăZ�b�g����B<BR>
     * <BR>
     * �@@�����̎萔���I�u�W�F�N�g.set.���o��v�Z�p���(�S����������i���v�l�j) �ŃZ�b�g�B<BR>
     * <BR>
     * �S�j�@@�P�|�Q�j�Ōv�Z�����S����������i���v�l�j���A<BR>
     * �@@�@@�@@������������Ƃ��ĕԋp����B<BR>
     * <BR>
     * @@param l_genCommission - �萔���I�u�W�F�N�g
     * @@param l_dblCalcUnitPrice - (�v�Z�P��)<BR>
     *    ���o��v�Z�p����i�S����������^��������j���v�Z���邽�߂̌v�Z�P���B
     * @@param l_genSubAccount - �⏕�����I�u�W�F�N�g�B
     * @@param l_equityTradedProduct - ��������I�u�W�F�N�g�B
     * @@param l_dblQuantity - ����
     * @@param l_dblExecutedQuantity - (��萔��)<BR>
     *    �����P��.��萔��
     * @@param l_dblExecutedAmount - (���v�����z)<BR>
     *    �����P��.���v�����z
     * @@param l_isSkipAmountRange - (isSkip���z�`�F�b�N)<BR>
     *    �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     *    �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j<BR>
     *    �ꍇ��true���w�肷��B<BR>
     * @@return double
     * @@roseuid 40AC81CF006F
     */
    public double calcContractAmountAtOrder(
        WEB3GentradeCommission l_genCommission,
        double l_dblCalcUnitPrice,
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3EquityTradedProduct l_equityTradedProduct,
        double l_dblQuantity,
        double l_dblExecutedQuantity,
        double l_dblExecutedAmount,
        boolean l_isSkipAmountRange)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcContractAmountAtOrder(WEB3GentradeCommission, double, " +
            "WEB3GentradeSubAccount, WEB3EquityTradedProduct, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        if (l_genCommission == null || l_genSubAccount == null || l_equityTradedProduct == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        if (Double.isNaN(l_dblCalcUnitPrice))
        {
            l_dblCalcUnitPrice = 0D;
        }
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }        
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0D;
        }          
        if (Double.isNaN(l_dblExecutedAmount))
        {
            l_dblExecutedAmount = 0D;
        }  
        //get �����v�Z�T�[�r�X
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        
        //���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);
        BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity);
        BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount);
           
        //�P�|�P�j�@@�����v�Z�T�[�r�X.calc�S���������()�ɂāA
        //  �S����������i���o�����j���v�Z����B
        // [calc�S��������� ����]
        // �����F�@@�i����.�����|����.��萔�ʁj�̌���
        // �v�Z�P���F�@@�v�Z�P��
        // ���XID�F�@@�⏕����.���XID
        // �萔�����i�R�[�h�F�@@�萔��.get�萔�����i�R�[�h()
        // is�w�l�F�@@�萔��.is�w�l( )
        log.debug("�����F" + l_bdQuantity.subtract(l_bdExecutedQuantity).doubleValue());
        log.debug("�v�Z�P���F" + l_dblCalcUnitPrice);
        log.debug("���XID�F" + l_genSubAccount.getWeb3GenBranch().getBranchId());
        log.debug("�萔�����i�R�[�h�F" + l_genCommission.getCommissionProductCode());
        log.debug("is�w�l�F" + l_genCommission.isLimitPrice());
        double l_dblRestraintTurnover = 
            l_bizLogic.calcRestraintTurnover(
                l_bdQuantity.subtract(l_bdExecutedQuantity).doubleValue(),
                l_dblCalcUnitPrice,
                l_genSubAccount.getWeb3GenBranch().getBranchId(),
                l_genCommission.getCommissionProductCode(),
                l_genCommission.isLimitPrice());
                
        BigDecimal l_bdRestraintTurnover = new BigDecimal(l_dblRestraintTurnover);
        
        //�P�|�Q�j�@@�S����������i���v�l�j���v�Z����B
        //�S����������i���v�l�j �� 
       //     �����v�Z�T�[�r�X.calc�S���������()�̖߂�l
       //     �{ ����.���v�����z
       log.debug("���v�����z�F" + l_bdExecutedAmount);
       BigDecimal l_bdTotalAmount = l_bdRestraintTurnover.add(l_bdExecutedAmount);
       
       //�Q�j�@@������z�l�`�F�b�N
       // �|�i�p�����[�^.isSkip���z�`�F�b�N == false�j�̏ꍇ�̂݁B
       //�����R���ʃ`�F�b�N.validate����\������z()�ɂ�
       //������������̋��z�`�F�b�N���s���B
       // [validate����\������z ����]
       // ���X�F�@@�p�����[�^.�⏕����.get����X()
       // �s��F�@@�p�����[�^.�������.get�s��()
       // ���z�F�@@�P�|�Q�j�̌v�Z���ʁi�S����������i���v�l�j�j
       // �����^�C�v�F�@@�p�����[�^.�⏕����.get�ڋq().�����^�C�v
       if(l_isSkipAmountRange == false)
       {
           //get�ڋq
           MainAccount l_mainAccount = l_genSubAccount.getMainAccount();
           MainAccountRow l_mainAccountRow = 
               (MainAccountRow)l_mainAccount.getDataSourceObject();
               
           l_orderMgrResVal.validateMaxHandlingPrice(
               l_genSubAccount.getWeb3GenBranch(),
               l_equityTradedProduct.getMarket(),
               l_bdTotalAmount.doubleValue(),
               l_mainAccountRow.getAccountType());
       }
       
       //�R�j�@@�萔���I�u�W�F�N�g�ɁA�P�|�Q�j�̌v�Z���ʂ�
       // �S����������i���v�l�j���u���o��v�Z�p����v�Ƃ��ăZ�b�g����B
       l_genCommission.setExpensesCalcAmount(l_bdTotalAmount.doubleValue());
       
       //�S�j�@@�P�|�Q�j�Ōv�Z�����S����������i���v�l�j���A
       //  ������������Ƃ��ĕԋp����B
       log.exiting(STR_METHOD_NAME);
       return l_bdTotalAmount.doubleValue();
       
    }
    
    /**
     * (calc�����������)<BR>
     * ��������������Z�o���ĕԋp����B<BR>
     * <BR>
     * W�w�l�����̏ꍇ�́A�֖ؑ������ł����<BR>
     * ���~�b�g�����^�X�g�b�v�����̗����ŊT�Z��������v�Z���A<BR>
     * �����ق��̋��z�i�S��������z�j��߂�l�I�u�W�F�N�g�ɐݒ肵�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jcalc������������iW�w�l�l���j�P�v<BR>
     * �u�i�M�p�����jcalc������������iW�w�l�l���j�Q�v���Q�ƁB<BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �����̎w�l�B<BR>
     * �i���s�w��̏ꍇ�́A0�j<BR>
     * @@param l_dblWLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * �X�g�b�v�����̒����w�l�B<BR>
     * �i�X�g�b�v���������s�w��̏ꍇ�́A0�j<BR>
     * @@param l_dblStopOrderBasePrice - (�t�w�l��l)<BR>
     * �X�g�b�v�����ւ̐ؑւ��s����l�B<BR>
     * @@param l_execConditionType - (���s����)<BR>
     * �����̎��s�����B<BR>
     * @@param l_execWConditionType - (�iW�w�l�j���s����)<BR>
     * �X�g�b�v�����̎��s�����B<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i�����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ���������B<BR>
     * @@param l_strCheckCurrentPrice - (�m�F���擾����)<BR>
     * �m�F���Ɏ擾���������B<BR>
     * �i��菈���Ŋm�F�������Ɏ������擾�ς̏ꍇ�A<BR>
     * �@@���������Ŏ擾�ς̎����������p�������ꍇ�ɃZ�b�g�B<BR>
     * �@@�����p�������Ȃ��ꍇ�́Anull�܂���0���Z�b�g�B�j<BR>
     * @@param l_blnIsStopOrderValid - (is�X�g�b�v�����L��)<BR>
     * W�w�l�����ŁA�X�g�b�v�����ɐؑւ��������Ă��邩�ǂ�����ݒ肷��B<BR>
     * �itrue�F�@@�X�g�b�v�����ւ̐ؑւ������B<BR>
     * �@@false�F�@@�X�g�b�v�����ւ̐ؑւ������� �܂��� notW�w�l�����B�j<BR>
     * @@param l_blnIsShort - (is����)<BR>
     * �������ǂ����̃t���O�B<BR>
     * �itrue�F�@@�����Afalse�F�@@�����j<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B<BR>
     * @@param l_eqTypeTradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B<BR>
     * @@param l_dblQuantity - (����)<BR>
     * ����<BR>
     * @@param l_dblExecQuantity - (��萔��)<BR>
     * �����P��.��萔��<BR>
     * @@param l_bdlExecutedAmount - (���v�����z)<BR>
     * �����P��.���v�����z<BR>
     * @@param l_blnIsSkipAmountCheck - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B<BR>
     * @@return WEB3EquityEstimatedContractPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedContractPrice calcContractAmountAtOrder(
        WEB3GentradeCommission l_commission,
        double l_dblLimitPrice,
        double l_dblWLimitPrice,
        double l_dblStopOrderBasePrice,
        EqTypeExecutionConditionType l_execConditionType,
        EqTypeExecutionConditionType l_execWConditionType,
        String l_strPriceConditionType,
        String l_strOrderConditionType,
        String l_strCheckCurrentPrice,
        boolean l_blnIsStopOrderValid,
        boolean l_blnIsShort,
        SubAccount l_subAccount,
        EqTypeTradedProduct l_eqTypeTradedProduct,
        double l_dblQuantity,
        double l_dblExecQuantity,
        double l_bdlExecutedAmount,
        boolean l_blnIsSkipAmountCheck) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcContractAmountAtOrder(WEB3GentradeCommission, double, double, double,"
            + " EqTypeExecutionConditionType, EqTypeExecutionConditionType, String, String, String,"
            + "boolean, boolean, SubAccount, EqTypeTradedProduct, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //===================================================================
        // ���O����
        //===================================================================
        // �����̃I�u�W�F�N�g��NotNull�`�F�b�N        
        if (l_commission == null || l_subAccount == null || l_eqTypeTradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        // �����R���ʃ`�F�b�NOBJ�̎擾
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                
        // �����v�Z�T�[�r�XOBJ�̎擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        // isSTOP���S���������X�̎擾
        long l_lngEstimatePriceCalcForm = l_orderManagerReusableValidations.
            getEstimatePriceCalcForm(l_commission.getCommissionProductCode(), l_subAccount);
        boolean l_blnIsStopQuantityRestraintBranch = 
            (l_lngEstimatePriceCalcForm == WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT);
        
        // �m�F��������double���inull�̏ꍇ0�Ƃ���j
        double l_dblCheckPrice = 0.0D;
        if (l_strCheckCurrentPrice != null)
        {
            l_dblCheckPrice = Double.parseDouble(l_strCheckCurrentPrice);
        }
        
        // ��������̌^��WEB3EquityTradedProduct�^�ɕϊ�
        WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_eqTypeTradedProduct;
        
        // ������������v�Z����OBJ�̏����ݒ�
        // STOP�������̏����ݒ�i�l�擾�͌�j
        // �����^�m�F�������inot STOP���j�̐ݒ�
        // �m�F�������̐ݒ�
        WEB3EquityEstimatedContractPrice 
            l_estimatedContractAmount = new WEB3EquityEstimatedContractPrice();

        double l_dblStopHighPrice = 0.0D;
        double l_dblCurrentPrice = 0.0D;
        if (l_dblCheckPrice == 0.0D)
        {
            l_dblCurrentPrice =  l_orderManagerReusableValidations.calcCurrentPrice(
                l_commission.getCommissionProductCode(), l_tradedProduct,
                (WEB3GentradeSubAccount)l_subAccount, false);
            l_estimatedContractAmount.setCheckGetCurrentPrice(l_dblCurrentPrice);
        }
        else
        {
            l_dblCurrentPrice =  l_dblCheckPrice;
        }
        

        //===================================================================
        // ���~�b�g�����ł̒�����������v�Z
        // �i�X�g�b�v�L�����̓X�g�b�v�����ł̒�����������v�Z�����˂�j
        //===================================================================
        // �v�Z�P���i���~�b�g�����p�j�Z�o
        // (1)STOP���S�����X �� �i���s���� or �s�o�������j�FSTOP������
        // (2)�����S�����X �� ���s�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�����^�m�F������
        // (3)��L�ȊO
        // �@@(3-1)�������� �� �w�l�������^�m�F�������@@�@@�@@�F�����^�m�F������
        // �@@(3-2)�������� �� �w�l�������^�m�F�������@@�@@�@@�F�w�l
        // �@@(3-3)�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�w�l
        double l_dblCalcPrice1 = 0.0D;
        if (l_blnIsStopQuantityRestraintBranch && (l_dblLimitPrice == 0.0D ||
                EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execConditionType)))
        {
            l_dblStopHighPrice =  l_orderManagerReusableValidations.calcCurrentPrice(
                l_commission.getCommissionProductCode(), l_tradedProduct,
                (WEB3GentradeSubAccount)l_subAccount, true);
            l_dblCalcPrice1 = l_dblStopHighPrice;
        }
        else if (!l_blnIsStopQuantityRestraintBranch && l_dblLimitPrice == 0.0D ||
            l_blnIsShort && l_dblLimitPrice < l_dblCurrentPrice)
        {
            l_dblCalcPrice1 = l_dblCurrentPrice;
        }
        else
        {
            l_dblCalcPrice1 = l_dblLimitPrice;
        }
        
        // �萔���I�u�W�F�N�g�i���~�b�g�����p�j�̐���
        WEB3GentradeCommission l_commission1 = copyCommission(l_commission);
        l_commission1.setIsLimitPrice(l_dblLimitPrice != 0.0D);
        
        // ������������v�Z�i���~�b�g�����p�j
        double l_dblEstimatedContractAmount1 = this.calcContractAmountAtOrder(
            l_commission1, l_dblCalcPrice1, (WEB3GentradeSubAccount)l_subAccount, l_tradedProduct,
            l_dblQuantity, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountCheck);


        //===================================================================
        // 1��v�Z�p�^�[�����̃��^�[��
        // �i���~�b�g�����̒������������Ԓl�Ƃ���j
        // �@@�v�w�l�i���~�b�g�^�X�g�b�v�Ƃ��ɗL���j�łȂ��ꍇ
        // �A�v�w�l���������A�X�g�b�v�����L���Ƃ��ĎZ�o����ꍇ
        //===================================================================
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType) ||
            l_blnIsStopOrderValid)
        {
            l_bizLogicProvider.setCommissionCalcResult(l_commission, l_commission1);
            l_estimatedContractAmount.setCalcUnitPrice(l_dblCalcPrice1);
            l_estimatedContractAmount.setEstimatedContractPrice(l_dblEstimatedContractAmount1);
            log.exiting(STR_METHOD_NAME);
            return l_estimatedContractAmount;
        }
        

        //===================================================================
        // �X�g�b�v�����ł̒�����������v�Z
        //===================================================================
        // �v�Z�P���i�X�g�b�v�����p�j�Z�o
        // (1)STOP���S�����X �� �i���s���� or �s�o�������j�FSTOP������
        // �@@�i�������A���~�b�g�v�Z���Ɏ擾���Ă����ꍇ�͓����l���g�p����j
        // (2)�����S�����X �� ���s�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�����^�m�F������
        // (3)��L�ȊO
        // �@@(3-1)�������� �� �����w�l�������^�m�F�������@@�F�����^�m�F������
        // �@@(3-2)�������� �� �����w�l�������^�m�F�������@@�F�����w�l
        // �@@(3-3)�����@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�F�����w�l
        double l_dblCalcPrice2 = 0.0D;
        if (l_blnIsStopQuantityRestraintBranch && (l_dblWLimitPrice == 0.0D ||
                EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execWConditionType)))
        {
            if (l_dblStopHighPrice == 0.0D)
            {
                l_dblStopHighPrice =  l_orderManagerReusableValidations.calcCurrentPrice(
                    l_commission.getCommissionProductCode(), l_tradedProduct,
                    (WEB3GentradeSubAccount)l_subAccount, true);
            }
            l_dblCalcPrice2 = l_dblStopHighPrice;
        }
        else if (!l_blnIsStopQuantityRestraintBranch && l_dblWLimitPrice == 0.0D ||
            l_blnIsShort && l_dblWLimitPrice < l_dblCurrentPrice)
        {
            l_dblCalcPrice2 = l_dblCurrentPrice;
        }
        else
        {
            l_dblCalcPrice2 = l_dblWLimitPrice;
        }
        
        // �萔���I�u�W�F�N�g�i�X�g�b�v�����p�j�̐���
        WEB3GentradeCommission l_commission2 = copyCommission(l_commission);
        l_commission2.setIsLimitPrice(l_dblWLimitPrice != 0.0D);
        
        // ������������v�Z�i�X�g�b�v�����p�j
        double l_dblEstimatedContractAmount2 = this.calcContractAmountAtOrder(
            l_commission2, l_dblCalcPrice2, (WEB3GentradeSubAccount)l_subAccount, l_tradedProduct,
            l_dblQuantity, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountCheck);
        

        //===================================================================
        // 2��v�Z�p�^�[�����̃��^�[��
        // �i���~�b�g�^�X�g�b�v�̓��A�������̒������������Ԓl�Ƃ���j
        //===================================================================
        if (l_dblEstimatedContractAmount1 >= l_dblEstimatedContractAmount2)
        {
            l_bizLogicProvider.setCommissionCalcResult(l_commission, l_commission1);
            l_estimatedContractAmount.setCalcUnitPrice(l_dblCalcPrice1);
            l_estimatedContractAmount.setEstimatedContractPrice(l_dblEstimatedContractAmount1);
        }
        else
        {
            l_bizLogicProvider.setCommissionCalcResult(l_commission, l_commission2);
            l_estimatedContractAmount.setCalcUnitPrice(l_dblCalcPrice2);
            l_estimatedContractAmount.setEstimatedContractPrice(l_dblEstimatedContractAmount2);
        }
        log.exiting(STR_METHOD_NAME);
        return l_estimatedContractAmount;
    }
    
    /**
     * (create���ό����G���g��)<BR>
     * ����������D�揇�ʂɏ]���Ċe�����ɔz�����A���ό����G���g���̔z����쐬����B<BR>
     * <BR>
     * this.create���ό����G���g��(�����P��ID, ��������, ���ό������׈ꗗ, false)��delegate����B<BR>
     * �������`�F�b�N���s���w��ŁAdelegate���s���B <BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �Ώے����P�ʂh�c�B<BR>
     * �i���������̏ꍇ�̂ݎd�l�j<BR>
     * @@param l_dblOrderQuantity - ���������B<BR>
     * @@param CloseMarginContractUnit - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@return EqTypeSettleContractOrderEntry[]
     */
    public EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        long l_lngOrderUnitId,
        double l_dblOrderQuantity,
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits)
    throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "createClosingContractEntry(long, double, WEB3MarginCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
        return this.createClosingContractEntry(
            l_lngOrderUnitId, l_dblOrderQuantity, l_closeMarginContractUnits, false);
    }
    
    /**
     * (create���ό����G���g��)<BR>
     * ����������D�揇�ʂɏ]���Ċe�����ɔz�����A���ό����G���g���̔z����쐬����B<BR>
     * <BR>
     * (*) ��ʂ��u����сv�w�肳�ꂽ�ꍇ�i��������==0�j�̓I�[�o�[���[�h���\�b�h�ɈϏ�����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�����jcreate���ό����G���g���v�Q�ƁB<BR>
     * <BR>
     * �i�������ϊ����`�F�b�N�j<BR>
     *  �Y�����������ϊ������z���Ă���ꍇ�A��O���X���[����B<BR>
     *  (������(*) > ���ϊ���(getCloseDate()�̏ꍇ)<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00748<BR>
     *  <BR>
     *  (�ԍω\�����`�F�b�N)<BR>
     *  �����������e�����̕ԍω\�����ɔz��������(Loop������)���c���������(*)�A<BR>
     *  �ԍω\�����I�[�o�[�Ƃ��ė�O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00304<BR>
     * <BR>
     *  (*)�c�������邩�̔���<BR>
     * (��������(�c��).longValue() > 0)�̏ꍇ�A��O�Ƃ���<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (�����P��ID)<BR>
     * �Ώے����P�ʂh�c�B<BR>
     * �i���������̏ꍇ�̂ݎd�l�j<BR>
     * @@param l_dblOrderQuantity - ���������B<BR>
     * @@param CloseMarginContractUnit - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@param l_isSkipCloseDateCheck - sSkip�����`�F�b�N�B<BR>
     * �����̊����`�F�b�N���X�L�b�v���邩�ǂ����̃t���O�B <BR>
     * �ifalse�F�@@�����`�F�b�N���X�L�b�v���Ȃ��i==�����`�F�b�N������j<BR>
     * �@@true�F�@@�����`�F�b�N���X�L�b�v����i==�����`�F�b�N�����Ȃ��j�j <BR>
     * @@return EqTypeSettleContractOrderEntry[]
     * @@roseuid 40B3001C0355
     */
    public EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        long l_lngOrderUnitId,
        double l_dblOrderQuantity,
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits,
        boolean l_isSkipCloseDateCheck)
    throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "createClosingContractEntry(long, double, WEB3MarginCloseMarginContractUnit[], boolean)";
        log.entering(STR_METHOD_NAME);
        if (l_closeMarginContractUnits == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.debug("�����P��ID = " + l_lngOrderUnitId);
        log.debug("�������� = " + l_dblOrderQuantity);
        if (Double.isNaN(l_dblOrderQuantity))
        {
            l_dblOrderQuantity = 0D;
        }
              
        //1) �������� == 0 �̏ꍇ
        if(l_dblOrderQuantity == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return createClosingContractEntry(l_lngOrderUnitId, l_closeMarginContractUnits, l_isSkipCloseDateCheck);
        }
        
        //���������i�����P��ID > 0�j�̏ꍇ
        OrderUnit l_orderUnit = null;
        boolean l_isPartiallyExecuted = false;
        BigDecimal l_bdTotalClosingExecuteQuantity = new BigDecimal("0");
        if (l_lngOrderUnitId > 0)
        {
            try 
            {
                l_orderUnit = getOrderUnit(l_lngOrderUnitId);
            } 
            catch (NotFoundException e) 
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            l_isPartiallyExecuted = l_orderUnit.isPartiallyExecuted();

            //�ꕔ���̏ꍇ�A���v�ԍϖ�萔�ʂ��擾����B
            if (l_isPartiallyExecuted)
            {
                //(*1)���v�ԍϖ�萔��
                //�����̒����P��ID�ɊY�����錚���ԍώw����̈ꗗ�̓��A
                //�����Ώۂ̌��Ϗ��ʁiindex + 1�j�`�ŉ��ʂ܂ł̌����ԍώw����.�ԍϖ�萔�ʂ�SUM�l
                int l_intIndex = 0;
                
                if (l_orderUnit instanceof EqTypeContractSettleOrderUnitImpl)
                {
                    EqTypeContractSettleOrderUnitImpl l_contractOrderUnit =
                        (EqTypeContractSettleOrderUnitImpl) l_orderUnit;
                    
                    EqTypeClosingContractSpec[] l_closingContractSpecs = 
                        l_contractOrderUnit.getContractsToClose();
                    
                    if (l_closingContractSpecs != null)
                    {
                        l_intIndex = l_closingContractSpecs.length;
                    }
                    
                    for (int i = 0; i < l_intIndex; i++)
                    {
                        l_bdTotalClosingExecuteQuantity =
                            l_bdTotalClosingExecuteQuantity.add(
                                new BigDecimal(
                                    String.valueOf(
                                        l_closingContractSpecs[i].getExecutedQuantity())));
                    }
                }
                else if (l_orderUnit instanceof EqTypeContractSwapOrderUnitImpl)
                {
                    EqTypeContractSwapOrderUnitImpl l_contractSwapOrderUnit =
                        (EqTypeContractSwapOrderUnitImpl) l_orderUnit;
                    
                    EqTypeClosingContractSpec[] l_closingContractSpecs = 
                        l_contractSwapOrderUnit.getContractsToClose();
                    
                    if (l_closingContractSpecs != null)
                    {
                        l_intIndex = l_closingContractSpecs.length;
                    }
                    
                    for (int i = 0; i < l_intIndex; i++)
                    {
                        l_bdTotalClosingExecuteQuantity =
                            l_bdTotalClosingExecuteQuantity.add(
                                new BigDecimal(
                                    String.valueOf(
                                        l_closingContractSpecs[i].getExecutedQuantity())));
                    }
                }
            }            
        }
        
        
        ArrayList l_lstSettleContractOrderEntrys = new ArrayList();
        BigDecimal l_bdOrderQuantity = new BigDecimal(l_dblOrderQuantity);        
        
        //���ό������׈ꗗ[]�v�f����Loop����
        int l_intSize = 0;
        if (l_closeMarginContractUnits != null)
        {
            l_intSize = l_closeMarginContractUnits.length;
        }
        WEB3EquityContract l_equityContract;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
                  
        for (int i = 0 ; i < l_intSize ; i ++)
        {
            //2) �V�K����&&���������i�c���j == 0 �̏ꍇ
            if((l_lngOrderUnitId == 0) && (Double.compare(l_bdOrderQuantity.doubleValue(),0) == 0))
            {
                break;
            }
            
            //3) get �����h�c
            long l_lngContractId = Long.parseLong(l_closeMarginContractUnits[i].id);
      
            try
            {
                //4) get����(long)    
                l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
            catch (NotFoundException l_nfex)
            {
                log.error(STR_METHOD_NAME,l_nfex);
                throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_nfex.getMessage(),
                        l_nfex);
            }

            //5) �i�������ϊ����`�F�b�N�j
            // �Y�����������ϊ������z���Ă���ꍇ
            if (!l_isSkipCloseDateCheck)
            {
                // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations) 
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                // validate���ϊ�������(����)
                l_orderMgrResVal.validateCloseDateExcess(l_equityContract);
            }

            //6) get �ԍω\�����c��
            // = getQuantity() - getLockedQuantity() + get���b�N������(�����P�ʂh�c) + get�ԍϖ�萔�ϐ��ʁi�����P��ID�j
            BigDecimal l_bdQuantity = new BigDecimal(l_equityContract.getQuantity());
            BigDecimal l_bdLockedQuantity = new BigDecimal(l_equityContract.getLockedQuantity());
            BigDecimal l_bdLockedOrderQuantity = new BigDecimal(l_equityContract.getLockedQuantity(l_lngOrderUnitId));
            BigDecimal l_dbClosingExecutedQuantity = new BigDecimal(l_equityContract.getClosingExecutedQuantity(l_lngOrderUnitId));
            BigDecimal l_bdRemainderQuantity = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedOrderQuantity).add(l_dbClosingExecutedQuantity);

            //7) ���ό����G���g���𐶐�����B
            //�ԍω\�����c���ƒ��������i�c���j���r����
            BigDecimal l_bdClosingQuantity = null;
            if(l_bdOrderQuantity.compareTo(l_bdRemainderQuantity) > 0)
            {
                l_bdClosingQuantity = 
                    new BigDecimal(
                        String.valueOf(l_bdRemainderQuantity.doubleValue()));
            }
            else
            {
                l_bdClosingQuantity = 
                    new BigDecimal(
                        String.valueOf(l_bdOrderQuantity.doubleValue()));
            }
            
            //�ꕔ���̒��������i�����P��ID > 0 && isPartiallyExecuted() == true�j�̏ꍇ�A
            //���ʂ̕ԍώw����̖�萔�ʂ̍l�����s��
            if (l_lngOrderUnitId > 0 && l_isPartiallyExecuted)
            {
                //(*)���ʂ̕ԍώw����̖�萔�ʂ̍l��
                //�@@�@@����萔�ʁi�c���j���Z�o����B
                //�@@�@@����萔�ʁi�c���j = ���������i�c���j - ���v�ԍϖ�萔��(*1)
                BigDecimal l_bdUnExecutedQuantity = 
                    l_bdOrderQuantity.subtract(l_bdTotalClosingExecuteQuantity);
                
                //�@@�A����萔�ʁi�c���j + get�ԍϖ�萔��()�̖߂�l < ��L�t���[�ɂČ��肵���y�ԍϒ������ʁz�̏ꍇ�A
                //�@@�@@��L�t���[�ɂČ��肵���y�ԍϒ������ʁz = ����萔�ʁi�c���j + get�ԍϖ�萔��()�̖߂�l�Ƃ���B
                if ((l_bdUnExecutedQuantity.add(l_dbClosingExecutedQuantity)).compareTo(l_bdClosingQuantity) < 0)
                {
                    l_bdClosingQuantity = l_bdUnExecutedQuantity.add(l_dbClosingExecutedQuantity);
                }
                
                //���v�ԍϖ�萔��(*1)���Aget�ԍϖ�萔��()�̖߂�l�����Z����B
                l_bdTotalClosingExecuteQuantity = 
                    l_bdTotalClosingExecuteQuantity.subtract(l_dbClosingExecutedQuantity);
            }

            l_lstSettleContractOrderEntrys.add(
                new EqTypeSettleContractOrderEntry(
                    l_lngContractId,
                    l_bdClosingQuantity.doubleValue()));
            
            //8) ���������i�c���j���A��L�t���[�ɂČ��肵���y�ԍϒ������ʁz�����Z����B 
            l_bdOrderQuantity = l_bdOrderQuantity.subtract(l_bdClosingQuantity);
            if(l_bdOrderQuantity.doubleValue() < 0)
            {
                l_bdOrderQuantity = new BigDecimal(0);
            }            

        }
        
        //9) �����������e�����̕ԍω\�����ɔz��������(Loop������)��
        //�c��������΁A�ԍω\�����I�[�o�[�Ƃ��ė�O���X���[����
        if(l_bdOrderQuantity.doubleValue() > 0)
        {
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00304,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        int l_intLength = l_lstSettleContractOrderEntrys.size();
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys = 
            new EqTypeSettleContractOrderEntry[l_intLength];
            
        l_lstSettleContractOrderEntrys.toArray(l_settleContractOrderEntrys);
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }
    
    /**
     * (create���ό����G���g��)<BR>
     * ���Ϗ��ʁ^�ԍω\���ʂ��`�F�b�N���A���ό����G���g���̔z����쐬����B<BR>
     * <BR>
     * (*) ��ʂ��u����сv�w�肳�ꂽ�ꍇ�ɃI�[�o�[���[�h���\�b�h���Ϗ������B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�����jcreate���ό����G���g���i����юw��j�v�Q�ƁB<BR>
     * <BR>
     * <BR>
     * �i�������ϊ����`�F�b�N�j<BR>
     *   �Y�����������ϊ����𒴂��Ă���ꍇ�A��O���X���[����B<BR>
     *   (������(*) > ���ϊ���(getCloseDate()�̏ꍇ)<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00748<BR>
     * <BR>
     * �i���Ϗ����`�F�b�N�j<BR>
     * �|get�̖߂�l��null�łȂ��ꍇ�A���Ϗ��ʂ��d�����Ă���Ɣ��肵�A��O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00332<BR>
     *<BR>
     * �i�ԍω\�c���`�F�b�N�j<BR>
     * ���ό������׈ꗗ[index].�����ƕԍω\�����c��(*)���r���A<BR>
     * ���ό������׈ꗗ[index].�������ԍω\�����c���𒴂��Ă���Η�O���X���[����B<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00299<BR>
     * 
     * @@param l_lngOrderUnitId - (�����P�ʂh�c)<BR>
     * �Ώے����P�ʂh�c�B<BR>
     * �i���������̏ꍇ�̂ݎd�l�j<BR>
     * @@param l_closeMarginContractUnits - (���ό������׈ꗗ)<BR>
     * �M�p������ό����I�u�W�F�N�g�̔z��B<BR>
     * �i���N�G�X�g�f�[�^�j<BR>
     * @@param l_isSkipCloseDateCheck - sSkip�����`�F�b�N�B<BR>
     * �����̊����`�F�b�N���X�L�b�v���邩�ǂ����̃t���O�B <BR>
     * �ifalse�F�@@�����`�F�b�N���X�L�b�v���Ȃ��i==�����`�F�b�N������j<BR>
     * �@@true�F�@@�����`�F�b�N���X�L�b�v����i==�����`�F�b�N�����Ȃ��j�j <BR>
     * @@return com.fitechlabs.xtrade.pluginEqTypeSettleContractOrderEntry
     * @@roseuid 40B3006C02F8
     */
    public EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        long l_lngOrderUnitId,
        WEB3MarginCloseMarginContractUnit[]  l_closeMarginContractUnits,
        boolean l_isSkipCloseDateCheck)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "createClosingContractEntry(long, WEB3MarginCloseMarginContractUnit[], boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_closeMarginContractUnits == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        
        TreeMap l_treeMap = new TreeMap();
        
        //���ό������׈ꗗ[]�v�f����Loop����
        int l_intSize = 0;
        if (l_closeMarginContractUnits != null)
        {
            l_intSize = l_closeMarginContractUnits.length;
        }
        for (int i = 0 ; i < l_intSize ; i ++)
        {
            //get ���ό������׈ꗗ[index].����
            String l_strOrderQuantity = l_closeMarginContractUnits[i].orderQuantity;
            //get ���ό������׈ꗗ[index].���Ϗ���
            String l_strSettlePriority = l_closeMarginContractUnits[i].settlePriority;

            double l_dbOrderQuantity;
                        
            //�p�����[�^�`�F�b�N(�V�K�����̏ꍇ)
            if ((l_lngOrderUnitId == 0)
                && ((l_strOrderQuantity == null) || "0".equals(l_strOrderQuantity)))
            {
                //�V�K����(�����P��ID==0)�̏ꍇ�́A����0��������null�̌��ό����G���g�����쐬���Ȃ��B
                continue;
            }            
            else 
            {
                if(l_strOrderQuantity == null )
                {
                    l_dbOrderQuantity = 0;
                }
                else
                {
                    l_dbOrderQuantity = Double.parseDouble(l_strOrderQuantity);
                }
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
            
            //3) get ����
            long l_lngContractId = Long.parseLong(l_closeMarginContractUnits[i].id);
            WEB3EquityContract l_equityContract;
            try
            {
                l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
            catch (NotFoundException l_nfex)
            {
                log.error(STR_METHOD_NAME,l_nfex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfex.getMessage(),
                    l_nfex);
            }

            //5) �i�������ϊ����`�F�b�N�j
            // �Y�����������ϊ������z���Ă���ꍇ
            if (!l_isSkipCloseDateCheck)
            {
                // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations) 
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                // validate���ϊ�������(����)
                l_orderMgrResVal.validateCloseDateExcess(l_equityContract);
            }

            //6) get�ԍϖ��ϐ���
            double l_dbClosingExecutedQuantity = 
                l_equityContract.getClosingExecutedQuantity(l_lngOrderUnitId);          
            
            //8) �ԍϖ��ϐ��ʃ`�F�b�N      
            if(Double.compare(l_dbOrderQuantity, l_dbClosingExecutedQuantity) < 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " ���ό������׈ꗗ[index].���� < �ԍϖ��ϐ���");
            }
            
            if (l_strSettlePriority == null || "0".equals(l_strSettlePriority))
            {
                continue;
            }
            Long l_lngSettlePriority = new Long(l_strSettlePriority);
            
            //9) ���Ϗ��ʃ`�F�b�N
            if(l_treeMap.get(l_lngSettlePriority) != null)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00182,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //10) get �ԍω\�����c��
            // = getQuantity() - getLockedQuantity() + get���b�N������(�����P�ʂh�c)
            BigDecimal l_bdQuantity = new BigDecimal(l_equityContract.getQuantity());
            BigDecimal l_bdLockedQuantity = new BigDecimal(l_equityContract.getLockedQuantity());
            BigDecimal l_bdLockedOrderQuantity = new BigDecimal(l_equityContract.getLockedQuantity(l_lngOrderUnitId));
            BigDecimal l_bdClosingExecutedQuantity = new BigDecimal(l_dbClosingExecutedQuantity);
            BigDecimal l_bdRemainderQuantity = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedOrderQuantity).add(l_bdClosingExecutedQuantity);
            
            //11) �ԍω\�c���`�F�b�N
            // ���ό������׈ꗗ[index].�����ƕԍω\�����c����
            //��r���A���ό������׈ꗗ[index].�������ԍω\�����c��
            //�𒴂��Ă���Η�O���X���[����B
            BigDecimal l_bdOrderQuantity = new BigDecimal(l_dbOrderQuantity);
            if(l_bdOrderQuantity.compareTo(l_bdRemainderQuantity) > 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }           
          
            //12)  put(���Ϗ��� : Object, ���ό����G���g�� : Object)
            // ���Ϗ��ʂ�����Key�ɁA���ό����G���g�����Z�b�g����B
            EqTypeSettleContractOrderEntry l_eqTypeSettleContractOrderEntry = 
                new EqTypeSettleContractOrderEntry(l_lngContractId,l_dbOrderQuantity);                
            l_treeMap.put(l_lngSettlePriority,l_eqTypeSettleContractOrderEntry);            
        }
        
        int l_intLength = l_treeMap.size();
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys = 
            new EqTypeSettleContractOrderEntry[l_intLength];
        
        l_treeMap.values().toArray(l_settleContractOrderEntrys);
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntrys;
    }
    
    /**
     * �icalc�V�K���v�Z�P���j�B<BR>
     * <BR>
     * �V�K�������̌v�Z�P�����v�Z���Ԃ��B<BR>
     * ������Ԃ��ꍇ�́A<BR>
     *   ?���s�w��̏ꍇ�FSTOP���S�����l������������Ԃ��B<BR>
     *   ?�w�l�w��̏ꍇ�i�����̂݁j�FSTOP���S�����l�����Ȃ�������Ԃ��B<BR> 
     * <BR>
     * ��������is���s��true�i���s�j�̏ꍇ<BR>
     * �@@�|this.calc����(�����̎萔�����i�R�[�h, �����̎������, �����̕⏕����, true)��<BR>
     * �@@�@@�@@�Ϗ�����B<BR>
     * <BR>
     * ��������is���s��false�i�w�l�j�A��������is������false�i�����j�̏ꍇ<BR>
     * �@@�|this.calc����(�����̎萔�����i�R�[�h, �����̎������, �����̕⏕����, true)�ɂ��A<BR>
     * �@@�@@�@@�������擾����B<BR>
     * �@@�|�icalc����( )�̖߂�l > �����̎w�l�j�̏ꍇ�Acalc����( )�̖߂�l��Ԃ��B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A�����̎w�l�����̂܂ܕԂ��B<BR>
     * <BR>
     * ����L�ȊO�̏ꍇ�i���w�l�A�������̏ꍇ�j<BR>
     * �@@�|�����̎w�l�����̂܂ܕԂ��B
     * @@param l_isOpenMargin �iis�V�K���j<BR>
     * �@@�@@�@@�V�K���������ǂ����𔻕ʂ���t���O�B<BR>
     * �@@�@@�@@�V�K���̏ꍇ��true�A�ԍς̏ꍇ��false�B
     * @@param l_isLong �iis�����j<BR>
     * �@@�@@�@@�����^�����𔻕ʂ���t���O�B<BR>
     * �@@�@@�@@�����̏ꍇ��true�A�����̏ꍇ��false�B
     * @@param l_marketOrder �iis���s�j<BR>
     * �@@�@@�@@���s�����̏ꍇ�Atrue�B�ȊO�Afalse�B
     * @@param l_dblLimitPrice �i�w�l�j<BR>
     * �@@�@@�@@is���s��false�i�w�l�j�̏ꍇ�̂݃Z�b�g�B
     * @@param l_genSubAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�i�M�p��������j
     * @@param l_equityTradedProduct �i��������I�u�W�F�N�g�j
     * @@param l_strCommisionProductCode �i�萔�����i�R�[�h�j<BR>
     * �@@�@@�@@�i10:��ꊔ�� 11:�X������ 12:�~�j���� 20:�����M��<BR>
     * �@@�@@�@@40:�O������ 50:�����w���敨 51:�����w��OP�j
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40B3F6630130
     */
    public double calcOpenMarginCalcUnitPrice(
        boolean l_isOpenMargin,
        boolean l_isLong,
        boolean l_marketOrder,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3EquityTradedProduct l_equityTradedProduct,
        String l_strCommisionProductCode)
        throws WEB3BaseException
    {        
        final String STR_METHOD_NAME = "calcOpenMarginCalcUnitPrice()";
        log.entering(STR_METHOD_NAME);
        if (l_genSubAccount == null || l_equityTradedProduct == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        if (Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0D;
        }
        double l_dblOpenMarginCalcUnitPrice;
        // ������is���s��true�i���s�j�̏ꍇ
        // this.calc����(�����̎萔�����i�R�[�h, �����̎������, 
        // �����̕⏕����, true)�ɈϏ�����B
        if(l_marketOrder == true)
        {
            l_dblOpenMarginCalcUnitPrice = this.calcCurrentPrice(
                l_strCommisionProductCode,
                l_equityTradedProduct,
                l_genSubAccount,
                true);
        }
        else if((l_marketOrder) == false && (l_isLong == false))
        {
            //��������is���s��false�i�w�l�j�A��������is������false�i�����j�̏ꍇ
            //this.calc����(�����̎萔�����i�R�[�h, �����̎������, 
            //�����̕⏕����, false)�ɂ��A�������擾����B
            //�icalc����( )�̖߂�l > �����̎w�l�j�̏ꍇ�Acalc����( )�̖߂�l��Ԃ��B
            //��L�ȊO�̏ꍇ�́A�����̎w�l�����̂܂ܕԂ��B
            l_dblOpenMarginCalcUnitPrice = this.calcCurrentPrice(
                l_strCommisionProductCode,
                l_equityTradedProduct,
                l_genSubAccount,
                false);
            if(Double.compare(l_dblLimitPrice,l_dblOpenMarginCalcUnitPrice) > 0)
            {
                l_dblOpenMarginCalcUnitPrice = l_dblLimitPrice;
            }
        }
        else
        {
            //��L�ȊO�̏ꍇ�i���w�l�A�������̏ꍇ�j
            //�����̎w�l�����̂܂ܕԂ�
            l_dblOpenMarginCalcUnitPrice = l_dblLimitPrice;
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblOpenMarginCalcUnitPrice;
    }
    
    /**
     * �ivalidate�������n�����j�B<BR>
     * <BR>
     * �w�茻�����n�����̔����R�����s���B<BR>
     * �ivalidateSwapContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jvalidate�������n�����v�Q�ƁB
     * @@param l_subAccount �⏕�����I�u�W�F�N�g
     * @@param l_swapContractOrderSpec �M�p�������n�������e�I�u�W�F�N�g�B
     * @@param l_contract �����I�u�W�F�N�g
     * @@return EqTypeNewOrderValidationResult
     * @@roseuid 40B53FE4005A
     */
    public EqTypeNewOrderValidationResult validateSwapContractOrder(
        SubAccount l_subAccount, 
        EqTypeSwapContractOrderSpec l_swapContractOrderSpec,
        WEB3EquityContract l_contract)
    {
        String STR_METHOD_NAME = 
            "validateSwapContractOrder(WEB3GentradeSubAccount, EqTypeSwapContractOrderSpec, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_swapContractOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);    
        }
        
        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;
        WEB3MarginSwapContractOrderSpec l_marginSwapContractOrderSpec = (WEB3MarginSwapContractOrderSpec)l_swapContractOrderSpec;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        try
        {

            //1) ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

            WEB3EquityContract l_equityContract = l_contract;
            if (l_contract == null)
            {
                //2) getSettleContractOrderEntries( )
                EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
                    l_marginSwapContractOrderSpec.getSettleContractOrderEntries();
                if (l_settleContractOrderEntrys == null || l_settleContractOrderEntrys.length == 0)
                {
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
                }
                
                //3) getContractId( )
                long l_lngContractId =
                    l_settleContractOrderEntrys[0].getContractId();
                WEB3EquityPositionManager l_positionManager = 
                    (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
                            
                //4) get ����
                l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
                
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow) l_equityContract.getDataSourceObject();

            //5) �������� �v���p�e�B���擾
            // get �s��h�c
            long l_lngMarketId = l_equityContract.getMarketId();

            //get �ٍϋ敪
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();

            //get �ٍϊ����l
            int l_intRepaymentNum = l_eqtypeContractRow.getRepaymentNum();
            
            //���敪
            ContractTypeEnum l_contractType = l_eqtypeContractRow.getContractType();
            
            //get �،���ЃR�[�h 
            String l_strInstitutionCode =
                l_genSubAccount.getInstitution().getInstitutionCode();
            //get �s��
            WEB3GentradeMarket l_genMarket = 
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
                            
            //�⏕����.get����X
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();

            //6) validate�M�p����(�⏕����, String)
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);

            // get ����
            EqTypeProduct l_eqTypeProduct =
                (EqTypeProduct) l_equityContract.getProduct();

            //7) validate�����R�[�h�i�M�p�j
            //�����R�[�h�F�@@�擾��������.����ID �ɊY�����銔�������I�u�W�F�N�g.�����R�[�h 
            //�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h 
            //�ٍϋ敪�F�@@�擾��������.�ٍϋ敪
            WEB3EquityProduct l_equityProduct =
                l_orderMgrResVal.validateProductCode(
                    l_eqTypeProduct.getProductCode(),
                    l_strInstitutionCode,
                    l_strRepaymentType);
            
            //8) validate�C���T�C�_�[
            l_orderMgrResVal.validateInsider(l_genSubAccount, l_equityProduct);

            //get �������
            OrderTypeEnum l_orderTypeEnum;
            if (l_equityContract.isLong())
            {
                l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
            }
            
            //9) validate�ڋq�����ʎ����~
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_genSubAccount,
                l_equityProduct.getProductId(),
                l_orderTypeEnum);
            
            //is����
            boolean l_blnIsSellContract = false;
            if (ContractTypeEnum.SHORT.equals(l_contractType))
            {
                l_blnIsSellContract = true;
            }
            
            //10)  validate��������i�M�p�j
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderMgrResVal.validateTradedProductForMarginTrading(
                    l_genSubAccount,
                    l_equityProduct,
                    l_genMarket,
                    l_gentradeBranch,
                    l_strRepaymentType,
                    OrderCategEnum.SWAP_MARGIN,
                    l_blnIsSellContract);

            //11) validate�戵�\�s��i�M�p�j
            //���X�F�@@�⏕����.get����X( ) 
            //��������F�@@validate�������( )�̖߂�l�̎�������I�u�W�F�N�g 
            //�s��R�[�h�F�@@validate�s��R�[�h( )�̖߂�l�̎s��I�u�W�F�N�g.�s��R�[�h 
            //�ٍϋ敪�F�@@�擾��������.�ٍϋ敪 
            //�ٍϋ敪�F�@@�擾��������.�ٍϊ����l
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);

            //14) validate��������J�݁i�M�p�j
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�����̕⏕���� 
            //�ŋ敪�F�@@�M�p�������n�������e.getTaxType( ) 
            //��n���F�@@�������.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                l_genSubAccount,
                l_marginSwapContractOrderSpec.getTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());

            //16) validate��������J��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�����̕⏕���� 
            //�ŋ敪�F�@@�M�p�������n�������e.get�ŋ敪�i�������n�j
            //��n���F�@@�������.getDailyDeliveryDate( )
            l_orderMgrResVal.validateSpecialAccountEstablish(
                l_genSubAccount,
                l_marginSwapContractOrderSpec.getSwapTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());
            
            //17) validate�ŋ敪�i�������n�j
            l_orderMgrResVal.validateSwapTaxType(
                l_marginSwapContractOrderSpec.getTaxType(),
                l_marginSwapContractOrderSpec.getSwapTaxType(),
                !l_blnIsSellContract);
            
            //18) validate��������戵�K��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�ŋ敪�F�@@�M�p�������n�������e.getTaxType( ) 
            //���������F�@@validate�����R�[�h�i�M�p�j( )�̖߂�l�̊��������I�u�W�F�N�g 
            //is�������F�@@�擾��������.isLong( )
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_marginSwapContractOrderSpec.getTaxType(),
                l_equityProduct,
                l_equityContract.isLong());
            
            //19) validate�����i�M�p�j
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_marginSwapContractOrderSpec.getTotalQuantity(),
                l_orderTypeEnum,
                l_strRepaymentType,
                l_intRepaymentNum);
            
            //validate���ό����G���g���������P��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g
            //���ό����G���g���ꗗ�F�@@�M�p�ԍϒ������e�Dget���ό����G���g���ꗗ() 
            l_orderMgrResVal.validateEverySettleContractOrderEntryLotSize(
                l_equityTradedProduct,
                l_marginSwapContractOrderSpec.getSettleContractOrderEntries());

            //20) ���n�i����.isShort( )==true�j�̏ꍇ�̂�
            if (l_equityContract.isShort())
            {
                //20.1)  validate���n�\����
                //�⏕�����F�@@�����̕⏕���� 
                //��������F�@@validate��������i�M�p�j( )�̖߂�l�̎�������I�u�W�F�N�g 
                //�����F�@@�M�p�������n�������e.getTotalQuantity( ) 
                //�ŋ敪�i�������n�j�F�@@�M�p�������n�������e.get�ŋ敪�i�������n�j( )
                l_orderMgrResVal.validateSwappableAssetQuantity(
                    l_genSubAccount,
                    l_equityTradedProduct,
                    l_marginSwapContractOrderSpec.getTotalQuantity(),
                    l_marginSwapContractOrderSpec.getSwapTaxType());                
            }
            
            //21) validate���ϑ�������
            if (l_contract == null)
            {
                l_orderMgrResVal.validateSettleContractTotalQuantity(
                    l_genSubAccount,
                    0,
                    l_equityTradedProduct,
                    l_marginSwapContractOrderSpec.getTaxType(),
                    l_strRepaymentType,
                    l_intRepaymentNum,
                    l_marginSwapContractOrderSpec.getTotalQuantity(),
                    l_contractType);
            }
        }
        catch (NotFoundException l_nfex)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfex.getMessage(),
                    l_nfex);
                    
            log.error(STR_METHOD_NAME, l_sysException);
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()));
        }
        catch (WEB3BaseException l_wbe)
        {
            return new EqTypeNewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()));
        }
        
        log.exiting(STR_METHOD_NAME);
        return new EqTypeNewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);          
    }
    
    /**
     * �icalc�T�Z���ϑ��v����j�B<BR>
     * <BR>
     * �T�Z���ϑ��v������Z�o���ĕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jcalc�T�Z���ϑ��v����v�Q�ƁB<BR>
     * @@param l_genCommission �i�萔���I�u�W�F�N�g�j<BR>
     * @@param l_dblLimitPrice �i�w�l�j<BR>
     * �@@�@@�@@���s�̏ꍇ��0���Z�b�g<BR>
     * @@param l_genSubAccount �i�⏕�����I�u�W�F�N�g�j<BR>
     * @@param l_equityTradedProduct �i��������I�u�W�F�N�g�j<BR>
     * @@param l_settleContractOrderEntrys �i���ό����G���g���̔z��j<BR>
     * @@param l_dblQuantity �i���ʁj<BR>
     * @@param l_orderUnit �i�����P�ʁj<BR>
     * �@@�@@�@@�������^���Ώہ^������Ώے����̒����P�ʃI�u�W�F�N�g <BR>
     *      �i�V�K�̒����o�^����null���Z�b�g�j<BR>
     * @@param l_dblNowExecQuantity �i�����萔�ʁj<BR>
     * �@@�@@�@@�����萔�� <BR>
     *       �i���^������̏ꍇ�ɕҏW�j <BR>
     * @@param l_dblNowExecPrice �i������P���j<BR>
     * �@@�@@�@@������P�� <BR>
     *       �i���^������̏ꍇ�ɕҏW�j <BR>
     * @@param l_isSkipAmountRange �iisSkip���z�`�F�b�N�j<BR>
     * �@@�@@�@@�v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * �@@�@@�@@�`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ <BR>
     * �@@�@@�@@��true���w�肷��B<BR>
     * @@param l_contract (�����I�u�W�F�N�g)<BR>
     * @@return WEB3EquityEstimatedCloseIncomeAmountDeliveryPrice
     * @@roseuid 40B597860186
     */
    public WEB3EquityRealizedProfitAndLossPrice calcEstimatedRealizedProfitAndLossAmount(
        WEB3GentradeCommission l_genCommission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3EquityTradedProduct l_equityTradedProduct,
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblQuantity,
        EqTypeOrderUnit l_orderUnit,
        double l_dblNowExecQuantity,
        double l_dblNowExecPrice,
        boolean l_isSkipAmountRange,
        WEB3EquityContract l_contract)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcEstimatedRealizedProfitAndLossAmount(WEB3GentradeCommission, " +
            "double, WEB3GentradeSubAccount, WEB3EquityTradedProduct, " +
            "EqTypeSettleContractOrderEntry[], double, EqTypeOrderUnit, double, double, boolean, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        if (l_genSubAccount == null || l_equityTradedProduct == null || l_settleContractOrderEntrys == null || l_genCommission == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        if (Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0D;
        }
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }        
        //1) ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        //get �����v�Z�T�[�r�X
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
            
        //get �g���|�W�V�����}�l�[�W��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        //2) �萔�����i�R�[�h���擾����B
        String l_strCommissionProductCode = l_genCommission.getCommissionProductCode();
        log.debug("�萔�����i�R�[�h = " + l_strCommissionProductCode);
              
        //3) create �T�Z���ϑ��v����v�Z����
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = 
            new WEB3EquityRealizedProfitAndLossPrice();
        
        // �����擾
        WEB3EquityContract l_equityContract = l_contract;
        if (l_contract == null)
        {
            long l_lngContractId = l_settleContractOrderEntrys[0].getContractId();
            try
            {
                l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID�ɊY�����錚�����擾�o���܂���ł����BID:[" + l_lngContractId + "]");
            }
        }
        boolean l_isShort = l_equityContract.isShort();
        
        //4) �v�Z�P�����Z�b�g����B 
        //�����͈ȉ��̒ʂ�w�肷��B  
        //�|�����̎w�l == 0�̏ꍇ�A���������R���ʃ`�F�b�N.calc����()�̖߂�l 
        //�|�����̎w�l != 0�̏ꍇ�A����.�w�l
        log.debug("�����̎w�l = " + l_dblLimitPrice);
        if(l_dblLimitPrice == 0)
        {
            double l_dbCurrentPrice = l_orderMgrResVal.calcCurrentPrice(
                l_strCommissionProductCode,
                l_equityTradedProduct,
                l_genSubAccount,
                false);
            log.debug("���� = " + l_dbCurrentPrice);
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(l_dbCurrentPrice);
        }
        else
        {
            log.debug("����.�w�l = " + l_dblLimitPrice);
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(l_dblLimitPrice);
        }
        
        //5) calc�������  �F �T�Z���ϑ��v���(*1)
        double l_dbTurnover;
        //�����̒����P�� == null�̏ꍇ
        if(l_orderUnit == null)
        {
            log.debug("�����̒����P�� == null�̏ꍇ");
            l_dbTurnover = 
                l_bizLogic.calcTurnover(
                    l_dblQuantity,
                    l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());
            
            log.debug("������� = " + l_dbTurnover);
            //6) set�������
            l_equityRealizedProfitAndLossPrice.setTurnover(l_dbTurnover);
            
            //7) set���o��v�Z�p���
            l_genCommission.setExpensesCalcAmount(l_dbTurnover);
        }
        else
        {
            log.debug("�����̒����P�� != null�̏ꍇ");
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            double l_dblExecutedQuantity = 0D;
            if (l_orderUnitRow.getExecutedQuantityIsNull() == false)
            {
                l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            }
            
            l_dbTurnover = 
                l_bizLogic.calcTurnover(
                    l_dblQuantity - l_dblExecutedQuantity - l_dblNowExecQuantity,
                    l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());            
            log.debug("������� = " + l_dbTurnover);

            double l_dblExecutedAmount = 0D;   
            if (l_orderUnitRow.getExecutedAmountIsNull() == false)
            {
                l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            }
                        
            //6) set�������
            l_dbTurnover = l_dbTurnover + l_dblExecutedAmount + (l_dblNowExecQuantity * l_dblNowExecPrice);
            l_equityRealizedProfitAndLossPrice.setTurnover(l_dbTurnover);
                
            log.debug("���o��v�Z�p��� = " + l_equityRealizedProfitAndLossPrice.getTurnover());        
            //7) set���o��v�Z�p���
            l_genCommission.setExpensesCalcAmount(
                l_equityRealizedProfitAndLossPrice.getTurnover());            
        }
        
        //isSkip���z�`�F�b�N == false
        log.debug("isSkip���z�`�F�b�N = " + l_isSkipAmountRange);
        BranchRow l_branch = (BranchRow) l_genSubAccount.getWeb3GenBranch().getDataSourceObject();
        if(l_isSkipAmountRange == false &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_branch.getMaxHandlingPriceCloseDiv()))
        {
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_genSubAccount.getMainAccount().getDataSourceObject();
                
            //8) ����\������z�`�F�b�N���s���B 
            //�����͈ȉ��̒ʂ�w�肷��B 
            //���X�F�@@�����̕⏕����.���XID�̕��X�I�u�W�F�N�g 
            //�s��F�@@�����̎������.�s��ID�̎s��I�u�W�F�N�g 
            //�S����������F�@@�萔��.set���o��v�Z�p���( )�̈����u���o��v�Z�p����v�ݒ�l�ɓ����B 
            //�����^�C�v�F�@@�����̕⏕����.����ID�̌ڋq�I�u�W�F�N�g.�����^�C�v
            l_orderMgrResVal.validateMaxHandlingPrice(
                l_genSubAccount.getWeb3GenBranch(),
                l_equityTradedProduct.getMarket(),
                l_equityRealizedProfitAndLossPrice.getTurnover(),
                l_mainAccountRow.getAccountType());

        }
        
        //9) calc�ϑ��萔��
        l_bizLogic.calcCommission(l_genCommission , l_genSubAccount);
        log.debug("�ϑ��萔�� = " + l_genCommission.getCommission());
        
        //10) �ϑ��萔������ł��Z�o����B 
        //�����͈ȉ��̒ʂ�w�肷��B 
        //���z�F�@@�萔��.get�萔�����z( ) 
        //����F�@@�萔��.get������( ) 
        //�⏕�����F�@@����.�⏕����
        double l_dblSalesTax =
            l_bizLogic.calcSalesTax(
                l_genCommission.getCommission(),
                l_genCommission.getOrderBizDate(),
                l_genSubAccount);
        log.debug("�ϑ��萔������� = " + l_dblSalesTax);
        
        //���ώ萔�� �F �T�Z���ϑ��v���(*2)
        double l_dblCommission =  l_genCommission.getCommission() + l_dblSalesTax;
        log.debug("�T�Z���ϑ��v��� = " + l_dblCommission);
                
        //����� �F �T�Z���ϑ��v���(*3)
        double l_dblContractAmount = 0;
        
        //�������o�� �F �T�Z���ϑ��v���(*4)
        double l_dblExpensesSum = 0;
        
        //���ό����G���g���iEqtypeSettleContractOrderEntry[]�j�v�f���Ƃ�Loop����
        int l_intSize = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intSize = l_settleContractOrderEntrys.length;
        }
        for (int i = 0 ; i < l_intSize ; i ++)
        {
            //11) get ����
            if (l_contract == null)
            {
                try
                {
                    l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_settleContractOrderEntrys[i].getContractId());
                    log.debug("�������擾 = " + l_equityContract.getContractId());
                }
                catch (NotFoundException l_nfex)
                {
                    log.error(STR_METHOD_NAME, l_nfex);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_nfex.getMessage(),
                            l_nfex);
                }
            }
            double l_dblContractQuantity = 0D;
            l_dblContractQuantity = l_settleContractOrderEntrys[i].getQuantity();
            EqtypeContractRow l_eqtypeContractRow =             
                (EqtypeContractRow)l_equityContract.getDataSourceObject();
            
            //12)  get����� SUM
            l_dblContractAmount = l_dblContractAmount + l_equityContract.getContractAmount(l_dblContractQuantity);
            
            //13) get �������o�� SUM
            //[calc���o��( )�F�����ݒ�d�l]
            //�ϑ��萔���A�ϑ��萔�������  ���@@0
            //���萔���`���̑�            ���@@
            //���ό����G���g���̊e�v�f�̓����ڂ��g�p���Ď擾�����l
            //   ex. ���萔��         ���@@����.get���萔��(����)
            //       ���萔�������        ���@@����.get���萔�������(����)
            //���敪             ���@@����.����
            long l_lngOrderUnitId = 0L;
            if (l_orderUnit != null)
            {
                l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
            }
            l_dblExpensesSum = l_dblExpensesSum + l_bizLogic.calcExpenses(
                0, //�ϑ��萔��
                0, //�ϑ��萔�������
                l_equityContract.getSetupFee(l_dblContractQuantity, l_lngOrderUnitId),//���萔��
                l_equityContract.getSetupFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//���萔�������
                l_equityContract.getNameTransferFee(l_dblContractQuantity, l_lngOrderUnitId),//���`������
                l_equityContract.getNameTransferFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//���`�����������
                l_equityContract.getManagementFee(l_dblContractQuantity, l_lngOrderUnitId),//�Ǘ���
                l_equityContract.getManagementFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//�Ǘ�������
                l_equityContract.getInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//������
                l_equityContract.getPayInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//�t����
                l_equityContract.getLoanEquityFee(l_dblContractQuantity, l_lngOrderUnitId),//�݊���
                l_equityContract.getOther(l_dblContractQuantity, l_lngOrderUnitId),//���̑�
                l_eqtypeContractRow.getContractType());//���敪
        }
        
        //15) set�T�Z���ϑ��v���
        BigDecimal l_bdQuantitySum = new BigDecimal(l_dblContractAmount);
        BigDecimal l_bdTurnover = new BigDecimal(l_dbTurnover);
        BigDecimal l_bdExpensesSum = new BigDecimal(l_dblExpensesSum);
        BigDecimal l_bdCommission = new BigDecimal(l_dblCommission);
        BigDecimal l_bdEstimatedRealizedProfitAndLossAmount;
        if(l_isShort == true)
        {
            //���ԍς̏ꍇ �F �T�Z���ϑ��v��� = ����� - ������� - (�������o�� + ���ώ萔��)
            l_bdEstimatedRealizedProfitAndLossAmount =
                l_bdQuantitySum
                    .subtract(l_bdTurnover)
                    .subtract(l_bdExpensesSum)
                    .subtract(l_bdCommission);
                    
            log.debug("****** ������F[" + l_dblContractAmount + "]");
            log.debug("****** ��������F[" + l_dbTurnover + "]");
            log.debug("****** �������o��F[" + l_dblExpensesSum + "]");
            log.debug("******  ���ώ萔���F[" + l_dblCommission + "]");
            log.debug("******  �T�Z���ϑ��v���(���ԍς̏ꍇ) = ����� - ������� - (�������o�� " 
                + "+ ���ώ萔��) �F[" 
                + l_bdEstimatedRealizedProfitAndLossAmount.doubleValue() + "]");
        }
        else
        {
            //���ԍς̏ꍇ �F �T�Z���ϑ��v��� = ������� - �����  - (�������o�� + ���ώ萔��)
            l_bdEstimatedRealizedProfitAndLossAmount =
            l_bdTurnover
                    .subtract(l_bdQuantitySum)
                    .subtract(l_bdExpensesSum)
                    .subtract(l_bdCommission);
                    
            log.debug("****** ��������F[" + l_dbTurnover + "]");
            log.debug("****** ������F[" + l_dblContractAmount + "]");
            log.debug("****** �������o��F[" + l_dblExpensesSum + "]");
            log.debug("******  ���ώ萔���F[" + l_dblCommission + "]");
            log.debug("******  �T�Z���ϑ��v���(���ԍς̏ꍇ) = ������� - ����� - (�������o�� " 
                + "+ ���ώ萔��) �F[" 
                + l_bdEstimatedRealizedProfitAndLossAmount.doubleValue() + "]");
        }
        l_equityRealizedProfitAndLossPrice.setEstimatedRealizedProfitAndLossAmount(
            l_bdEstimatedRealizedProfitAndLossAmount.doubleValue());
        
        log.exiting(STR_METHOD_NAME);
        return l_equityRealizedProfitAndLossPrice;
        
    }
    
    /**
     * �icalc�T�Z��n����i�������n)�j�B<BR>
     * <BR>
     * <BR>
     * �������n���̊T�Z��n������Z�o���ĕԋp����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jcalc�T�Z��n����i�������n�j�v�Q�ƁB<BR>
     * @@param l_settleContractOrderEntrys �i���ό����G���g���j<BR>
     *       ���ό����G���g���̔z��<BR>
     * @@param l_dblQuantity �i���ʁj<BR>
     *       ����<BR>
     * @@param l_eqtypeOrderUnit (�����P��)<BR>
     * �@@�@@�@@�������^���Ώہ^������Ώے����̒����P�ʃI�u�W�F�N�g <BR>
     *      �i�V�K�̒����o�^����null���Z�b�g�j<BR>
     * @@param l_contract (����)<BR>
     * �����I�u�W�F�N�g
     * @@return double<BR>
     * @@roseuid 40BEF12600B6
     */
    public double calcEstimatedSwapPrice(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblQuantity,
        EqTypeOrderUnit l_eqtypeOrderUnit,
        WEB3EquityContract l_contract)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcEstimatedSwapPrice(EqTypeSettleContractOrderEntry[], double, EqtypeOrderUnit, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        if (l_settleContractOrderEntrys == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        //get �����v�Z�T�[�r�X
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = 
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
           
        //����� 
        double l_dblContractAmountSum = 0;
        
        //�������o��
        double l_dblExpensesSum = 0;

        //�Ǘ���
        double l_dblManagementFee = 0;
        //�Ǘ�������
        double l_dblManagementFeeTax = 0;

        // �������擾
        WEB3EquityContract l_equityContract = l_contract;
        if (l_contract == null)
        {
            long l_lngContractId = l_settleContractOrderEntrys[0].getContractId();
            try
            {
                l_equityContract =
                    (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
            catch (NotFoundException l_ex)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID�ɊY�����錚�����擾�o���܂���ł����BID:[" + l_lngContractId + "]");
            }
        }
        boolean l_isShort = l_equityContract.isShort();
        
        //���ό����G���g���iEqtypeSettleContractOrderEntry[]�j�v�f���Ƃ�Loop����
        int l_intSize = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intSize = l_settleContractOrderEntrys.length;
        }
        for (int i = 0; i < l_intSize; i ++)
        {
            //11) get ����
            if (l_contract == null)
            {
                try
                {
                    l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_settleContractOrderEntrys[i].getContractId());
                }
                catch (NotFoundException l_nfex)
                {
                    log.error(STR_METHOD_NAME, l_nfex);
                    throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_nfex.getMessage(),
                            l_nfex);
                }
            }
            double l_dblContractQuantity = l_settleContractOrderEntrys[i].getQuantity();
            
            EqtypeContractRow l_eqtypeContractRow = 
                (EqtypeContractRow)l_equityContract.getDataSourceObject();
                
            //12)  get����� SUM
            l_dblContractAmountSum = l_dblContractAmountSum + l_equityContract.getContractAmount(l_dblContractQuantity);
            
            long l_lngOrderUnitId = 0L;
            // ����.�����P�� != null�̏ꍇ
            if (l_eqtypeOrderUnit != null)
            {
                l_lngOrderUnitId = l_eqtypeOrderUnit.getOrderUnitId();
            }

            //����t���[�F���n�����̏ꍇ�i����.isShort == true�j
            if (l_isShort)
            {
                //get�Ǘ���(double, long)
                l_dblManagementFee =
                    l_equityContract.getManagementFee(l_dblContractQuantity, l_lngOrderUnitId);

                //get�Ǘ�������(double, long)
                l_dblManagementFeeTax =
                    l_equityContract.getManagementFeeTax(l_dblContractQuantity, l_lngOrderUnitId);
            }
            //����t���[�F���������̏ꍇ�i����.isShort == false�j
            else
            {
                //getManagementFee(long)
                l_dblManagementFee = l_equityContract.getManagementFee(l_lngOrderUnitId);

                //getManagementFeeTax(long)
                l_dblManagementFeeTax = l_equityContract.getManagementFeeTax(l_lngOrderUnitId);
            }

            //13) get �������o�� SUM
            l_dblExpensesSum = l_dblExpensesSum + l_bizLogic.calcExpenses(
                0, //�ϑ��萔��
                0, //�ϑ��萔�������
                l_equityContract.getSetupFee(l_dblContractQuantity, l_lngOrderUnitId),//���萔��
                l_equityContract.getSetupFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//���萔�������
                l_equityContract.getNameTransferFee(l_dblContractQuantity, l_lngOrderUnitId),//���`������
                l_equityContract.getNameTransferFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//���`�����������
                l_dblManagementFee,//�Ǘ���
                l_dblManagementFeeTax,//�Ǘ�������
                l_equityContract.getInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//������
                l_equityContract.getPayInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//�t����
                l_equityContract.getLoanEquityFee(l_dblContractQuantity, l_lngOrderUnitId),//�݊���
                l_equityContract.getOther(l_dblContractQuantity, l_lngOrderUnitId),//���̑�
                l_eqtypeContractRow.getContractType());//���敪
        }
        
        BigDecimal l_bdQuantitySum = new BigDecimal(l_dblContractAmountSum);
        BigDecimal l_bdExpensesSum = new BigDecimal(l_dblExpensesSum);
        double l_dbEstimatedSwapPrice;
        if(l_isShort == true)
        {
            //���n�̏ꍇ �F �T�Z��n��� = �����  - �������o��
            l_dbEstimatedSwapPrice =
                l_bdQuantitySum.subtract(l_bdExpensesSum).doubleValue();
            log.debug("****** ������F[" + l_dblContractAmountSum + "]");
            log.debug("****** �������o��F[" + l_dblExpensesSum + "]");
            log.debug("****** �T�Z��n���(���n�̏ꍇ) = �����  - �������o�� �F[" + l_dbEstimatedSwapPrice + "]");
        }
        else
        {
            //�����̏ꍇ �F �T�Z��n��� = �����  + �������o��
            l_dbEstimatedSwapPrice =
                -l_bdQuantitySum.add(l_bdExpensesSum).doubleValue();
            log.debug("****** ������F[" + l_dblContractAmountSum + "]");
            log.debug("****** �������o��F[" + l_dblExpensesSum + "]");
            log.debug("****** �T�Z��n���(�����̏ꍇ) = �����  + �������o�� �F[" + l_dbEstimatedSwapPrice + "]");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dbEstimatedSwapPrice;
    }
    
    /**
     * �icalc�����j�B<BR>
     * <BR>
     * �������Z�o����B<BR>
     * �i* ���������R���ʃ`�F�b�N.calc����( )�ɈϏ�����B�j<BR>
     * @@param l_strCommisionProductCode - (�萔�����i�R�[�h)<BR>
     *    �y��Е��X���i�e�[�u���z�������Ɏg�p����B<BR>
     *  <BR>
     *    �����́uisSTOP���l���v��false�̏ꍇ�́A�y��Е��X���i�e�[�u���z<BR>
     *    ���Q�Ƃ��Ȃ����߁Anull�ݒ���B
     * @@param l_equityTradedProduct �i��������j<BR>
     * �@@�@@�@@�y������������e�[�u���z����̎����擾�Ɏg�p����B
     * @@param l_genSubAccount �i�⏕�����j<BR>
     * �@@�@@�@@�y��Е��X���i�e�[�u���z����́u�T�Z���z�v�Z�����v�擾����<BR>
     * �@@�@@�@@�u���XID�v��<BR>�w��Ɏg�p����B
     * @@param l_isStopPriceConsideration �iisSTOP���l���j<BR>
     * �@@�@@�@@�y��Е��X���i�e�[�u���z�T�Z���z�v�Z������STOP���S�� ��<BR>
     * �@@�@@�@@�ݒ���l�����邩�ǂ����̃t���O�B<BR>
     * �@@�@@�@@�T�Z���z�v�Z���� �̐ݒ�����̂܂܎g�p����ꍇ��true���A<BR>
     * �@@�@@�@@��������ꍇ��false���A���ꂼ��ݒ肷��B
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 40C3C0700069
     */
    public double calcCurrentPrice(
        String l_strCommisionProductCode,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3GentradeSubAccount l_subAccount,
        boolean l_isStopPriceConsideration)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        return l_orderMgrResVal.calcCurrentPrice(
            l_strCommisionProductCode,
            l_tradedProduct,
            l_subAccount,
            l_isStopPriceConsideration);
    }
    
    /**
     * �ivalidate�ԍϒ��������j�B<BR>
     * �w��ԍϒ��������̔����R�����s���B <BR>
     * �ivalidateChangeSettleContractOrder�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * this.validate�ԍϒ�������()�ɏ������Ϗ��idelegade�j����B <BR>
     * <BR>
     * [validate�ԍϒ�������()�̈����ݒ�] <BR>
     * �⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �M�p�V�K�������������e�F�@@�p�����[�^.�M�p�V�K�������������e <BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j <BR>
     * @@param l_genSubAccount �⏕����
     * @@param l_marginChangeSettleContractOrderSpec �M�p�ԍϒ����������e
     * @@return EqTypeOrderValidationResult
     * @@roseuid 40C4744F01A0
     */
    public EqTypeOrderValidationResult validateChangeSettleContractOrder(
        SubAccount l_subAccount,
        EqTypeChangeSettleContractOrderSpec l_changeSettleContractOrderSpec)
    {
        String STR_METHOD_NAME = 
            "validateChangeSettleContractOrder(WEB3GentradeSubAccount, WEB3MarginChangeSettleContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate�ԍϒ�������()�ɏ������Ϗ��idelegade�j����B
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            this.validateChangeSettleContractOrder(
                l_subAccount, l_changeSettleContractOrderSpec, false);
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderValidationResult;
    }
    
    /**
     * (validate�ԍϒ�������)<BR>
     * �w��ԍϒ��������̔����R�����s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jvalidate�ԍϒ��������v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_changeSettleContractOrderSpec - (�M�p�ԍϒ����������e)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * @@return EqTypeOrderValidationResult
     */
    public EqTypeOrderValidationResult validateChangeSettleContractOrder(
        SubAccount l_subAccount,
        EqTypeChangeSettleContractOrderSpec l_changeSettleContractOrderSpec,
        boolean l_blnIsSkipDelayStatusCheck)
    {
        final String STR_METHOD_NAME = "validateChangeSettleContractOrder(" +
                "SubAccount, EqTypeChangeSettleContractOrderSpec," +
                "boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null || l_changeSettleContractOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        WEB3MarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec =
            (WEB3MarginChangeSettleContractOrderSpec)l_changeSettleContractOrderSpec;
        WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount)l_subAccount;

        try
        {
            //getInstance( )
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations)
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

            //getOrderId( )
            long l_lngOrderId = l_marginChangeSettleContractOrderSpec.getOrderId();

            //getOrder(arg0 : long)
            Order l_order = getOrder(l_lngOrderId);

            //validate���������\���(����, boolean)
            l_orderMgrResVal.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

            //get���������ڍ�( )
            EqTypeContractSettleChangeOrderUnitEntry l_contractSettleChangeOrderUnitEntry =
                l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry();

            //getAfterChangeSettleContractOrderEntries( )
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
                l_contractSettleChangeOrderUnitEntry.getAfterChangeSettleContractOrderEntries();
            if (l_settleContractOrderEntrys == null || l_settleContractOrderEntrys.length == 0)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //get����(long)
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();

            WEB3EquityContract l_equityContract =
                (WEB3EquityContract)l_positionManager.getContract(
                    l_settleContractOrderEntrys[0].getContractId());

            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow) l_equityContract.getDataSourceObject();

            // �������� �v���p�e�B���擾
            // get �s��h�c
            long l_lngMarketId = l_equityContract.getMarketId();
            //get �ٍϋ敪
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();
            //get �ٍϊ����l
            int l_intRepaymentNum = l_eqtypeContractRow.getRepaymentNum();
            //get �ŋ敪
            TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
            //get ���敪
            ContractTypeEnum l_contractType = l_eqtypeContractRow.getContractType();

            //get �،���ЃR�[�h
            String l_strInstitutionCode =
                l_genSubAccount.getInstitution().getInstitutionCode();
            //get �s��
            WEB3GentradeMarket l_genMarket =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);

            //�⏕����.get����X
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();

            //validate�M�p����(�⏕����, String)
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);

            //validate�s��R�[�h
            l_genMarket = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                l_genMarket.getMarketCode(),
                l_strInstitutionCode);

            //getProduct( )
            EqTypeProduct l_eqTypeProduct =
                (EqTypeProduct) l_equityContract.getProduct();

            //validate�C���T�C�_�[
            l_orderMgrResVal.validateInsider(l_genSubAccount, l_eqTypeProduct);

            //get �������
            OrderTypeEnum l_orderTypeEnum;
            if (l_equityContract.isLong())
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            }

            //validate�ڋq�����ʎ����~
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_genSubAccount,
                l_eqTypeProduct.getProductId(),
                l_orderTypeEnum);

            //isShort( )
            boolean l_blnIsShort = l_equityContract.isShort();

            //validate��������i�M�p�j
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�⏕�����F�@@�����̕⏕���� 
            //���������F�@@����.getProduct( )�̖߂�l�̊��������I�u�W�F�N�g 
            //�s��F�@@validate�s��R�[�h( )�̖߂�l�̎s��I�u�W�F�N�g 
            //���X�F�@@�⏕����.get����X( ) 
            //�ٍϋ敪�F�@@����.�ٍϋ敪 
            //�����J�e�S���F�@@OrderCategEnum.�M�p�ԍϒ����iCLOSE_MARGIN�j�Œ� 
            //is�����F�@@����.isShort( ) 
            //is������~�`�F�b�N�F�@@false�i��������~�`�F�b�N�����Ȃ��j 

            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderMgrResVal.validateTradedProductForMarginTrading(
                    l_genSubAccount,
                    (WEB3EquityProduct)l_eqTypeProduct,
                    l_genMarket,
                    l_gentradeBranch,
                    l_strRepaymentType,
                    OrderCategEnum.CLOSE_MARGIN,
                    l_blnIsShort,
                    false);

            //isAfterChangePriceMarket( )
            boolean l_blnIsAfterChangePriceMarket =
                l_contractSettleChangeOrderUnitEntry.isAfterChangePriceMarket();

            //get�����㎷�s����( )
            EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
                l_marginChangeSettleContractOrderSpec.getModifiedExecutionCondition();

            //validate���s�w��K���i�M�p�j
            //��������F�@@validate��������i�M�p�j( )�̖߂�l�́A��������I�u�W�F�N�g 
            //�ٍϋ敪�F�@@����.�ٍϋ敪 
            //�����J�e�S���F�@@OrderCategEnum.�h�ԍϒ����h�iCLOSE_MARGIN�j 
            //is���s�F�@@���������ڍ�.isAfterChangePriceMarket( ) 
            //is�����F�@@����.isShort( ) 
            //���s�����F�@@�M�p�ԍϒ����������e.get�����㎷�s����( )
            l_orderMgrResVal.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                OrderCategEnum.CLOSE_MARGIN,
                l_blnIsAfterChangePriceMarket,
                l_equityContract.isShort(),
                l_eqTypeExecutionConditionType);

            //validate�戵�\�s��i�M�p�j
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);

            //validate��������J�݁i�M�p�j
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�⏕�����F�@@�����̕⏕����
            //�ŋ敪�F�@@����.�ŋ敪
            //��n���F�@@�������.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                l_genSubAccount,
                l_taxType,
                l_equityTradedProduct.getDailyDeliveryDate());

            //validate��������戵�K��
            //�ŋ敪�F�@@����.�ŋ敪 
            //���������F�@@����.getProduct( )�̖߂�l�̊��������I�u�W�F�N�g 
            //is�������F�@@����.isShort( )��true�Ȃ�true���w��A�ȊOfalse���w�� 
            //���s��ɑ΂��Ĕ����ǂ���̒����Ȃ̂����w�肷�邽�߁A���敪�Ƌt�ɂȂ�(���Δ���)
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_taxType,
                l_eqTypeProduct,
                l_equityContract.isShort());

            //getOrderUnits(����ID : long)
            OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);

            //getOrderUnit(arg0 : long)
            OrderUnit l_orderUnit  = l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            EqTypeOrderUnit l_firstOrderUnit = this.getFirstOrderUnit((EqTypeOrderUnit)l_orderUnit);
            EqtypeOrderUnitRow l_firstOrderUnitRow = (EqtypeOrderUnitRow)l_firstOrderUnit.getDataSourceObject();

            //get���񒍕��̒����P��( )�̖߂�l�̒����P��.������
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
            //get�����㒍��������( )
            Date l_datModifiedExpirationDate =
                l_marginChangeSettleContractOrderSpec.getModifiedExpirationDate();
            //get������l�i����( )
            String l_strModifiedExecutionCondition =
                l_marginChangeSettleContractOrderSpec.getModifiedPriceConditionType();
            //get�����㔭������( )
            String l_strModifiedOrderConditionType =
                l_marginChangeSettleContractOrderSpec.getModifiedOrderConditionType();
            //is������o����܂Œ���( )
            boolean l_blnIsModifiedIsCarriedOrder =
                l_marginChangeSettleContractOrderSpec.isModifiedIsCarriedOrder();

            //getOrderUnitId( )
            long l_lngOrderUnitId = l_orderUnitRow.getOrderUnitId();

            //validate��������
            l_orderMgrResVal.validateOrderCondition(
                l_genSubAccount,
                l_lngOrderUnitId,
                l_equityTradedProduct,
                l_bizDate,
                l_datModifiedExpirationDate,
                l_strModifiedOrderConditionType,
                l_marginChangeSettleContractOrderSpec.getModifiedExecutionCondition(),
                l_blnIsModifiedIsCarriedOrder,
                l_strRepaymentType,
                l_strModifiedExecutionCondition,
                l_genMarket.getMarketCode());

            //getAfterChangeTotalQuantity( )
            double l_dbAfterChangeTotalQuantity =
                l_contractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity();

           //validate�����i�M�p�j
           //�����͈ȉ��̒ʂ�ɐݒ肷��B
           //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g
           //���X�F�@@�⏕����.get����X( )
           //�����F�@@���������ڍ�.getAfterChangeTotalQuantity( )
           //������ʁF�@@
           //����.isLong( )==true�̏ꍇ�AOrderTypeEnum.�M�p�����ԍϒ���
           //����.isLong( )==false�̏ꍇ�AOrderTypeEnum.�M�p�����ԍϒ���
           //�ٍϋ敪�F�@@����.�ٍϋ敪
           //�ٍϊ����l�F�@@����.�ٍϊ����l
           l_orderMgrResVal.validateQuantity(
               l_equityTradedProduct,
               l_gentradeBranch,
               l_dbAfterChangeTotalQuantity,
               l_orderTypeEnum,
               l_strRepaymentType,
               l_intRepaymentNum);

            //validate���ό����G���g���������P��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g 
            //���ό����G���g���ꗗ�F�@@���������G���g��.getSettleContractOrderEntries() 
            //�i���j���������G���g���F 
            //�M�p�ԍϒ����������e�Dget���������G���g���ꗗ()��0�Ԗڂ̗v�f
            l_orderMgrResVal.validateEverySettleContractOrderEntryLotSize(
                l_equityTradedProduct,
                l_contractSettleChangeOrderUnitEntry.getAfterChangeSettleContractOrderEntries());

            //validate���ϑ�������
            l_orderMgrResVal.validateSettleContractTotalQuantity(
                l_genSubAccount,
                l_orderUnit.getOrderUnitId(),
                l_equityTradedProduct,
                l_taxType,
                l_strRepaymentType,
                l_intRepaymentNum,
                l_contractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity(),
                l_contractType);

            //getAfterChangePrice( )
            double l_dbAfterChangePrice =
                l_contractSettleChangeOrderUnitEntry.getAfterChangePrice();

            boolean l_isValidatePrice = false;
            //validate�����P��
            //�����͈ȉ��̒ʂ�ɐݒ肷��B
            //�w�l�F�@@���������ڍ�.getAfterChangePrice( )
            //��������F�@@validate�������(�M�p)( )�̖߂�l�̎�������I�u�W�F�N�g
            //�⏕�����F�@@�����̕⏕�����I�u�W�F�N�g
            l_isValidatePrice =
                l_orderMgrResVal.validatePrice(
                    l_dbAfterChangePrice,
                    l_equityTradedProduct,
                    l_genSubAccount);
            if (l_isValidatePrice == false)
            {
                return new EqTypeOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00293));
            }

            //get������t�w�l��l( )
            double l_dbModifiedStopOrderPrice =
                l_marginChangeSettleContractOrderSpec.getModifiedStopOrderPrice();

            //get������iW�w�l�j�����w�l( )
            double l_dbModifiedWLimitPrice =
                l_marginChangeSettleContractOrderSpec.getModifiedWLimitPrice();
            String l_strModifiedWLimitPrice =
                WEB3StringTypeUtility.formatNumber(l_dbModifiedWLimitPrice);

            //get������iW�w�l�j���s����( )
            EqTypeExecutionConditionType l_ChangeAfterWlimitExecCondType =
                l_marginChangeSettleContractOrderSpec.getModifiedWlimitExecCondType();

            //get�iW�w�l�j�L����ԋ敪( )
            String l_strWlimitEnableStatusDiv =
                l_marginChangeSettleContractOrderSpec.getWlimitEnableStatusDiv();

            //is������(EqTypeOrderUnit)
            boolean l_blnIsBuyOrder = this.isBuyOrder((EqTypeOrderUnit)l_orderUnit);

            //validateW�w�l����()
            l_orderMgrResVal.validateWLimitPriceOrder(
                l_genSubAccount,
                l_orderUnitRow.getOrderUnitId(),
                l_dbAfterChangePrice,
                l_strModifiedOrderConditionType,
                l_dbModifiedStopOrderPrice,
                l_strModifiedWLimitPrice,
                l_ChangeAfterWlimitExecCondType,
                l_strWlimitEnableStatusDiv,
                l_equityTradedProduct,
                l_blnIsBuyOrder,
                l_orderUnitRow.getRepaymentType(),
                l_orderUnitRow.getOrderCateg(),
                l_dbAfterChangeTotalQuantity,
                l_strModifiedExecutionCondition,
                l_orderUnitRow.getOrderType());

            //�����������̔����������ݓ������Z�o�������������O�̏ꍇ�̂ݎ��{����B
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            //�������.get������ > �����d�ʔ�����
            if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
            {
                //validate�ǌ���������t�\
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.EQUITY);
            }

            //get�����㔭���������Z�q( )
            String l_strModifiedOrderCondOperator =
                l_marginChangeSettleContractOrderSpec.getModifiedOrderCondOperator();

            //validate��������
            l_orderMgrResVal.validateChangeItem(
                (EqTypeOrderUnit)l_orderUnit,
                l_dbAfterChangeTotalQuantity,
                l_dbAfterChangePrice,
                l_eqTypeExecutionConditionType,
                l_strModifiedExecutionCondition,
                l_strModifiedOrderConditionType,
                l_strModifiedOrderCondOperator,
                l_dbModifiedStopOrderPrice,
                l_dbModifiedWLimitPrice,
                l_ChangeAfterWlimitExecCondType,
                l_marginChangeSettleContractOrderSpec.isModifiedIsCarriedOrder(),
                l_marginChangeSettleContractOrderSpec.getModifiedExpirationDate(),
                l_settleContractOrderEntrys);

            //validate����������Rev���
            l_orderMgrResVal.validateChangeOrderRevUpperLimit(
                (EqTypeOrderUnit)l_orderUnit,
                l_contractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity(),
                l_contractSettleChangeOrderUnitEntry.getAfterChangePrice(),
                l_marginChangeSettleContractOrderSpec.getModifiedExecutionCondition(),
                l_marginChangeSettleContractOrderSpec.getModifiedPriceConditionType());
        }
        catch (NotFoundException nfe)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            log.error(STR_METHOD_NAME, l_sysException);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()));
        }
        catch (OrderValidationException ove)
        {
            return new EqTypeOrderValidationResult(
                ove.getValidationResult().getProcessingResult());
        }
        catch (WEB3BaseException l_wbe)
        {
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()));
        }

        log.exiting(STR_METHOD_NAME);
        //EqTypeOrderValidationResult(arg0 : ProcessingResult)
        return new EqTypeOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }
    
    /**
     * �ivalidate�M�p��������j�B<BR>
     * <BR>
     * �M�p����̔����R���������s���������\�b�h�B<BR>
     * ����Ώے������M�p����̏ꍇ�ɁAvalidate��������������\�b�h��菈�����Ϗ������B<BR>
     * <BR>
     * �w��̐M�p����̒�����������{�\�����`�F�b�N����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jvalidate�M�p��������v�Q�ƁB
     * @@param l_genSubAccount �⏕�����I�u�W�F�N�g
     * @@param l_equityCancelOrderSpec ��������������e�I�u�W�F�N�g
     * @@return EqTypeOrderValidationResult
     * @@roseuid 40D021AA02E8
     */
    protected EqTypeOrderValidationResult validateMarginOrderCancel(
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3EquityCancelOrderSpec l_equityCancelOrderSpec)
    {
        String STR_METHOD_NAME = 
            "validateMarginOrderCancel(WEB3GentradeSubAccount, WEB3EquityCancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                
            //1) ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            //2) ����ID���擾����B
            long l_lngOrderId = l_equityCancelOrderSpec.getOrderId();
            
            //3) get ����
            Order l_order = getOrder(l_lngOrderId);
            
            //4) validate��������\���(����)
            l_orderMgrResVal.validateOrderForCancellation(l_order);
            
            //�����P�ʃI�u�W�F�N�g���擾����
            OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);

            OrderUnit l_orderUnit  = l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //�����P��.get�ٍϋ敪( ) 
            String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
            //�����P��.�ٍϊ����l()
            int l_intRepaymentNum = l_orderUnitRow.getRepaymentNum();
            
            //6) validate�M�p����(�⏕����, String)
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);
            
            //get �،���ЃR�[�h 
            String l_strInstitutionCode =
                l_genSubAccount.getInstitution().getInstitutionCode();
                
            //get �s��
            WEB3GentradeMarket l_genMarket =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
                
            //�⏕����.get����X
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();
            
            //8) validate�s��R�[�h
            l_genMarket = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                l_genMarket.getMarketCode(),
                l_strInstitutionCode);
            
            //9) get ����
            WEB3EquityProduct l_equityProduct =
                (WEB3EquityProduct)l_tradingModule.getProductManager().getProduct(l_orderUnitRow.getProductId());
           
            //10)  validate�������
            WEB3EquityTradedProduct l_equityTradedProduct = 
                (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(l_equityProduct, l_genMarket);
            
            //11) validate�戵�\�s��i�M�p�j
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);
            
			//13) ����Ώے����̔����������ݓ������Z�o�������������O�̏ꍇ�̂ݎ��{�B
			Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_datOrderBizDate =
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(),
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);

			if(WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
			{
				//13.1) validate�ǌ���������t�\
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
					ProductTypeEnum.EQUITY);
			}
            
            //15) validate���t�\����
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�����̕⏕�����I�u�W�F�N�g 
            //��������F�@@validate��������i�M�p�j( )�̖߂�l�̎�������I�u�W�F�N�g 
            //�����F�@@ �����P��.��萔�ʁi������������ɂ��A���������邱�ƂɂȂ鐔�ʁj
            //�ŋ敪�i�������n�j�F�@@�����P��.get�ŋ敪�i�������n�j
			if ((OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()) &&
				(l_orderUnit.isUnexecuted() == false)))
            {
                double l_dbOrderQuantity = l_orderUnitRow.getExecutedQuantity();
                l_orderMgrResVal.validateSellableAssetQuantity(
                    l_genSubAccount,
                    l_equityTradedProduct,
                    l_dbOrderQuantity,
                    l_orderUnitRow.getSwapTaxType());
            }
        }
        catch (NotFoundException nfe)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            log.error(STR_METHOD_NAME, l_sysException);
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()));
        }
        catch (OrderValidationException ove)
        {
            return new EqTypeOrderValidationResult(
                ove.getValidationResult().getProcessingResult());
        }
        catch (WEB3BaseException l_wbe)
        {
            return new EqTypeOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()));
        }
        
        log.exiting(STR_METHOD_NAME);
        return new EqTypeOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
    }
    
    /**
     * �icreate��������ByOrder�j�B<BR>
     * <BR>
     * �����Ɋ֘A�����M�p����������ׁi�Ɖ�p�j��z��Ŏ擾����B<BR>
     * �w�蒍�����V�K�������̏ꍇ�́Anull��ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�M�p�����jcreate��������ByOrder�v�Q�ƁB<BR>
     * <BR>
     * �����P��ID�ɊY�����錚���ԍώw����I�u�W�F�N�g��<BR>
     * �擾����B�擾�ł��Ȃ������ꍇ�́A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B<BR>
     * �@@class: WEB3SystemLayerException<BR>
     * �@@tag:   SYSTEM_ERROR_80005
     * @@param l_lngOrderUnitId �����P��ID
     * @@return WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 40D14977003D
     */
    public WEB3MarginContractUnit[] createContractUnitByOrder(long l_lngOrderUnitId) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "createContractUnitByOrder(long)";
        log.entering(STR_METHOD_NAME);
        try
        {
            //1) �����P��ID�ɂЂ��Â������P�ʃI�u�W�F�N�g���擾����B 
            OrderUnit l_orderUnit = this.getOrderUnit(l_lngOrderUnitId);
            
            //2) �����J�e�S�����擾����
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if(OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
            {
                //�V�K������(�����P��.getOrderCateg( ) == �h�V�K�������h�j
                //�̏ꍇ�́Anull��ԋp���A�������I������B
                return null;
            }
            
            EqTypeClosingContractSpec[] l_closingContractSpecs = null;
            
            //[�����P��.�����J�e�S�� == �h�ԍϒ����h�̏ꍇ
            //  EqTypeContractSettleOrderUnit�ɃL���X�g�B
            //[�����P��.�����J�e�S�� == �h�������n�����h�̏ꍇ]
            //  EqTypeContractSwapOrderUnit�ɃL���X�g�B
            if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderCateg))
            {
                l_closingContractSpecs = ((EqTypeContractSettleOrderUnit)l_orderUnit).getContractsToClose();
            }
            else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderCateg))
            {
                l_closingContractSpecs = ((EqTypeContractSwapOrderUnit)l_orderUnit).getContractsToClose();
            }
            
            if (l_closingContractSpecs == null || l_closingContractSpecs.length == 0)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005,STR_METHOD_NAME);
            }

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
            WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            
            List l_lisContractUnits = new ArrayList();
                             
            int l_intLen = 0;
            if (l_closingContractSpecs != null)
            {
                l_intLen = l_closingContractSpecs.length;
            }
            
            double l_dblCalcUnitPrice = 0.0D;
            for (int i = 0; i < l_intLen; i ++)
            {
                EqTypeClosingContractSpec l_closingContractSpec = l_closingContractSpecs[i];
                
                WEB3EquityContract l_contract = (WEB3EquityContract)l_positionManager.getContract(l_closingContractSpec.getContractId());
                
                //�M�p����������׃I�u�W�F�N�g�𐶐�����B
                WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
                
                // (*3)�v���p�e�B�Z�b�g
                // ID�F�@@�ԍώw����.getContractId( )
                // �����F ����.getOpenDate( )
                // ���P���F�@@����.getContractPrice( )
                // �������F�@@����.getQuantity( )
                // ������F�@@����.get�����( )
                // �]�����v�F�@@����.get�]�����v( )
                // ���������F�@@�ԍώw����.getQuantity( )
                // ���o�������F�@@�ԍώw����.getExecutedQuantity( )
                // ���Ϗ��ʁF�@@�ԍώw����.getClosingSerialNo( )

                //ID�F�@@�ԍώw����.getContractId( )
                l_contractUnit.id = "" + l_closingContractSpec.getContractId();
                //�����F ����.getOpenDate( )         
                l_contractUnit.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
                //���P���F�@@����.getContractPrice( )                
                l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
                //�������F�@@����.getQuantity( )
                l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_contract.getQuantity());
                //������F�@@����.get�����( )
                //��������擾����B�����͈ȉ��̒ʂ�ɐݒ肷��B
                //���ʁF�@@�ԍώw����.getQuantity( )�̖߂�l
                l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractAmount(l_closingContractSpec.getQuantity()));
                
                // �v�Z�P��
                if (l_dblCalcUnitPrice == 0.0D)
                {
	                l_dblCalcUnitPrice =
	                    l_productManager.getCurrentPrice(
	                        (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct());
                }
                //�]�����v�F�@@����.get�]�����v�i�������o��l���j()
                double l_dblAppraisalProfitLoss = l_contract.getAppraisalProfitOrLossExpenses(l_dblCalcUnitPrice, l_closingContractSpec.getQuantity(), l_lngOrderUnitId);
                
                l_contractUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitLoss);
                
                //���������F�@@�ԍώw����.getQuantity( )
                l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_closingContractSpec.getQuantity());
                //���o�������F�@@�ԍώw����.getExecutedQuantity( )
                if(l_closingContractSpec.getExecutedQuantity() == 0 )
                {
                    l_contractUnit.partContQuantity = null;
                }
                else
                {
                    l_contractUnit.partContQuantity = WEB3StringTypeUtility.formatNumber(l_closingContractSpec.getExecutedQuantity());
                }
                
                //���Ϗ��ʁF�@@�ԍώw����.getClosingSerialNo( )
                l_contractUnit.settlePriority = "" + l_closingContractSpec.getClosingSerialNo();
                
                l_lisContractUnits.add(l_contractUnit);
            }
            
            WEB3MarginContractUnit[] l_contractUnits = new WEB3MarginContractUnit[l_lisContractUnits.size()];
            l_lisContractUnits.toArray(l_contractUnits);
            
            return l_contractUnits;
            
        }
        catch (NotFoundException nfe)
        {
            log.error(STR_METHOD_NAME, nfe);
            throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
        }     
    }
    
    /**
     * �ivalidate�V�K�����������j�B<BR>
     * �M�p����̔����R���������s���������\�b�h�B <BR>
     * <BR>
     * this.validate�V�K����������()�ɏ������Ϗ��idelegade�j����B <BR>
     * <BR>
     * [validate�V�K����������()�̈����ݒ�] <BR>
     * �⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * �M�p�V�K�������������e�F�@@�p�����[�^.�M�p�V�K�������������e <BR>
     * isSkip�x���󋵃`�F�b�N�F�@@false�i�Œ�j <BR>
     * @@param l_genSubAccount �⏕�����I�u�W�F�N�g
     * @@param l_marginChangeOrderSpec �M�p�V�K�������������e�I�u�W�F�N�g�B
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException 
     * @@roseuid 40E256A60118
     */
    protected EqTypeOrderValidationResult validateChangeOrder(
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec) throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "validateChangeOrder(WEB3GentradeSubAccount, WEB3MarginChangeOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //this.validate�V�K����������()�ɏ������Ϗ��idelegade�j����B
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            this.validateChangeOrder(l_genSubAccount, l_marginChangeOrderSpec, false);
        
        log.exiting(STR_METHOD_NAME);   
        return l_eqTypeOrderValidationResult;
    }
    
    /**
     * (validate�V�K����������)<BR>
     * �M�p����̔����R���������s���������\�b�h�B<BR>
     * �����Ώے������M�p����̏ꍇ�ɁAvalidate�������������������\�b�h��菈�����Ϗ������B<BR>
     * <BR>
     * �w��̐M�p����̒������������{�\�����`�F�b�N����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�M�p�����jvalidate�V�K�����������v�Q�ƁB<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@param l_marginChangeOrderSpec - (�M�p�V�K�������������e)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip�x���󋵃`�F�b�N)<BR>
     * @@throws WEB3BaseException 
     */
    public EqTypeOrderValidationResult validateChangeOrder(
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3MarginChangeOrderSpec l_marginChangeOrderSpec,
        boolean l_blnIsSkipDelayStatusCheck) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateChangeOrder(WEB3GentradeSubAccount, WEB3MarginChangeOrderSpec, boolean)";
        log.entering(STR_METHOD_NAME);
        
        if (l_genSubAccount == null || l_marginChangeOrderSpec == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }
        
        //getOrderId( )
        long l_lngOrderId = l_marginChangeOrderSpec.getOrderId();
        
        //getOrder(arg0 : long)
        //�����I�u�W�F�N�g���擾����B 
        //�����͈ȉ��̒ʂ�w�肷��B 
        //����ID�F�@@getOrderId()�̖߂�l
        Order l_order = null;
        OrderUnit l_orderUnit = null;
        Market l_market = null;
        Product l_product = null;
        WEB3GentradeMarket l_gentradeMarket = null;
        boolean l_isShortSellingRestraint = false;
        try
        {
            l_order = this.getOrder(l_lngOrderId);
        
            //validate���������\���(����, boolean)
            //�����̒������\��������Ԃł��邩�ǂ������`�F�b�N����B 
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�����F�@@getOrder()�̖߂�l 
            //isSkip�x���󋵃`�F�b�N�F�@@�p�����[�^.isSkip�x���󋵃`�F�b�N
            this.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);
            
            //get�V�K�������������e�ڍ�( )
            EqTypeChangeOrderUnitEntry l_eqChangeOrderUnitEntry = 
                l_marginChangeOrderSpec.getChangeOrderUnitEntry();
            
            //getOrderUnitId( )
            long l_lngOrderUnitId = l_eqChangeOrderUnitEntry.getOrderUnitId();
            
            //getOrderUnit(arg0 : long)
            //�����P�ʃI�u�W�F�N�g���擾����B 
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�����P��ID�F�@@getOrderUnitId()�̖߂�l
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitId);
            EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            
            //�����P��.get�ٍϋ敪( ) 
            String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
            
            //validate�M�p����(�⏕����, String)
            //�M�p��������̋��ʃ`�F�b�N�����{����B 
            //�ȉ��̃`�F�b�N���s���B 
            //�@@�|��t���ԃ`�F�b�N 
            //�@@�|�V�X�e����~���`�F�b�N 
            //�@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j 
            //�@@�|�M�p���{��Ѓ`�F�b�N 
            //�@@�|�M�p�q�`�F�b�N�i�M�p�����J�݃`�F�b�N�j 
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�⏕�����F�@@�p�����[�^.�⏕���� 
            //�ٍϋ敪�F�@@�����P��.�ٍϋ敪
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);
            
            //getInstance( )
            WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
                = (WEB3EquityTypeOrderManagerReusableValidations) 
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            //getTaxType( )
            TaxTypeEnum l_taxTypeEnum = l_orderUnitRow.getTaxType();
            
            Timestamp l_tsDeliveryDate = l_orderUnitRow.getDeliveryDate();
    
            //validate��������J�݁i�M�p�j(�⏕����, TaxTypeEnum, Date)
            //��ʁ^��������`�F�b�N�����{����B 
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�⏕�����F�@@�p�����[�^.�⏕���� 
            //�ŋ敪�F�@@�@@getTaxType()�̖߂�l 
            //��n���F�@@getDeliveryDate()�̖߂�l
            l_orderManagerReusableValidations.validateMarginSpecialAccountOpen(
                l_genSubAccount, l_taxTypeEnum, l_tsDeliveryDate);
            
            //getMarket(arg0 : long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
    
            l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
    
            //validate�s��R�[�h(String, String)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�s��R�[�h�F�@@�s��.getMarketCode() 
            //�،���ЃR�[�h�F�@@�⏕����.�،���ЃR�[�h
            l_gentradeMarket = 
                (WEB3GentradeMarket) l_orderManagerReusableValidations.validateMarket(
                l_market.getMarketCode(), l_genSubAccount.getInstitution().getInstitutionCode());
            
            //getProduct(arg0 : long)
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
    
            //�g���v���_�N�g�}�l�[�W�����擾 
            WEB3EquityProductManager l_equityProductManager
                       = (WEB3EquityProductManager) l_tradingModule.getProductManager();
            l_product = l_equityProductManager.getProduct(l_orderUnitRow.getProductId());
            
            //getSide( )
            SideEnum l_sideEnum = l_orderUnit.getSide();
            
            //validate�C���T�C�_�[(�⏕����, ��������)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�����̕⏕���� 
            //���������F�@@�����P��.getProduct( )�̖߂�l�̊��������I�u�W�F�N�g
            WEB3EquityProduct l_eqProduct = (WEB3EquityProduct)l_product;
            l_orderManagerReusableValidations.validateInsider(
                l_genSubAccount, l_eqProduct);
            
            //validate�ڋq�����ʎ����~(�⏕����, long, OrderTypeEnum)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�����̕⏕���� 
            //����ID�F�@@validate�����R�[�h�i�M�p�j( )�̖߂�l�̊��������I�u�W�F�N�g.����ID 
            //������ʁF�@@�����P��.������ʁi�������������̒l�j
            l_orderManagerReusableValidations.validateAccountProductOrderStop(
                l_genSubAccount, l_eqProduct.getProductId(), l_orderUnitRow.getOrderType());
            
            //validate��������i�M�p�j(�⏕����, ��������, �s��, 
            //      ���X, String, OrderCategEnum, boolean, boolean)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�⏕�����F�@@�����̕⏕���� 
            //���������F�@@�����P��.getProduct( )�̖߂�l�̊��������I�u�W�F�N�g 
            //�s��F�@@validate�s��R�[�h()�̖߂�l 
            //���X�F�@@�⏕����.get����X() 
            //�ٍϋ敪�F�@@�����P��.�ٍϋ敪 
            //�����J�e�S���F�@@OrderCategEnum.�V�K�������iOPEN_MARGIN�j�Œ� 
            //is�����F 
            //�@@getSide()�̖߂�l �� SideEnum.����̏ꍇ 
            //�@@�@@true���Z�b�g�B 
            //�@@�ȊO�Afalse���Z�b�g�B 
            //is������~�`�F�b�N�F�@@false�i��������~�`�F�b�N�����Ȃ��j
            boolean l_blnIsSell = false;
            if (SideEnum.SELL.equals(l_sideEnum))
            {
                l_blnIsSell = true;
            }
            WEB3EquityTradedProduct l_tradedProduct =
                l_orderManagerReusableValidations.validateTradedProductForMarginTrading(
                    l_genSubAccount,
                    l_eqProduct,
                    l_gentradeMarket,
                    l_genSubAccount.getWeb3GenBranch(),
                    l_orderUnitRow.getRepaymentType(),
                    OrderCategEnum.OPEN_MARGIN,
                    l_blnIsSell,
                    false);
            
            //isAfterChangePriceMarket( )
            boolean l_blnIsAfterChangePriceMarket = 
                l_eqChangeOrderUnitEntry.isAfterChangePriceMarket();
            
            //get�����㎷�s����( )
            EqTypeExecutionConditionType l_executionConditionType =
                l_marginChangeOrderSpec.getModifiedExecutionType();
            
            //validate���s�w��K���i�M�p�j(�������, String, OrderCategEnum, 
            //      boolean, boolean, EqTypeExecutionConditionType)
            l_orderManagerReusableValidations.validateMarketOrderRestraint(
                l_tradedProduct,
                l_orderUnitRow.getRepaymentType(),
                OrderCategEnum.OPEN_MARGIN,
                l_blnIsAfterChangePriceMarket,
                l_blnIsSell,
                l_executionConditionType);
            
            //validate�戵�\�s��i�M�p�j(���X, �������, String, String, double)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //���X�F�@@�p�����[�^.�⏕����.get����X() 
            //��������F�@@validate��������i�M�p�j()�̖߂�l 
            //�s��R�[�h�F�@@validate�s��R�[�h()�̖߂�l.getMarketCode() 
            //�ٍϋ敪�F�@@�����P��.�ٍϋ敪 
            //�ٍϊ����l�F�@@�����P��.�ٍϊ����l
            l_orderManagerReusableValidations.validateHandlingMarket(
                l_genSubAccount.getWeb3GenBranch(),
                l_tradedProduct,
                l_gentradeMarket.getMarketCode(),
                l_orderUnitRow.getRepaymentType(),
                l_orderUnitRow.getRepaymentNum());
            
            //getOrderUnit(arg0 : long)
            OrderUnit l_firstOrderUnit = this.getFirstOrderUnit(l_eqOrderUnit);
            
            EqtypeOrderUnitRow l_firstFrderUnitRow =
                (EqtypeOrderUnitRow)l_firstOrderUnit.getDataSourceObject();
            
            //get�����㒍��������( )
            Date l_datModifiedExpirationDate = 
                l_marginChangeOrderSpec.getModifiedExpirationDate();
            
            //get������l�i����( )
            String l_strModifiedPriceConditionType = 
                l_marginChangeOrderSpec.getModifiedPriceConditionType();
            
            //get�����㔭������( )
            String l_strModifiedOrderConditionType = 
                l_marginChangeOrderSpec.getModifiedOrderConditionType();
            
            //is������o����܂Œ���( )
            boolean l_blnIsModifiedIsCarriedOrder =
                l_marginChangeOrderSpec.isModifiedIsCarriedOrder();
            
            Date l_bizDate = WEB3DateUtility.getDate(l_firstFrderUnitRow.getBizDate(), "yyyyMMdd");
            
            //validate��������(�⏕����, long, �������, Date, Date, 
            //       String, EqTypeExecutionConditionType, boolean, String, String, String)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�⏕�����F�@@�p�����[�^.�⏕���� 
            //�����P��ID�F�@@�����P��.�����P��ID 
            //��������F�@@validate�������()�̖߂�l 
            //�������������F�@@get���񒍕��̒����P��()�̖߂�l�̒����P��.������ 
            //�����������F�@@get�����㒍��������()�̖߂�l 
            //���������F�@@get�����㔭������()�̖߂�l 
            //���s�����F�@@get�����㎷�s����()�̖߂�l 
            //is�o����܂Œ����F�@@is������is�o����܂Œ���()�̖߂�l 
            //�M�p����敪�F�@@�����P��.�ٍϋ敪 
            //�l�i�����F�@@get������l�i����()�̖߂�l 
            //�s��R�[�h�F�@@�s��.getMarketCode()
            l_orderManagerReusableValidations.validateOrderCondition(
                l_genSubAccount,
                l_orderUnit.getOrderUnitId(),
                l_tradedProduct,
                l_bizDate,
                l_datModifiedExpirationDate,
                l_strModifiedOrderConditionType,
                l_executionConditionType,
                l_blnIsModifiedIsCarriedOrder,
                l_orderUnitRow.getRepaymentType(),
                l_strModifiedPriceConditionType,
                l_gentradeMarket.getMarketCode());
            
            //get�����㒍������( )
            double l_dblModifiedOrderQuantity = 
                l_marginChangeOrderSpec.getModifiedOrderQuantity();
            
            //validate�����i�M�p�j(�������, ���X, double, OrderTypeEnum, String, double)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //��������F�@@validate�������()�̖߂�l 
            //���X�F�@@�p�����[�^.�⏕����.get����X() 
            //�����F�@@get�����㒍������()�̖߂�l 
            //������ʁF�@@�����P��.������� 
            //�ٍϋ敪�F�@@�����P��.�ٍϋ敪 
            //�ٍϊ����l�F�@@�����P��.�ٍϊ����l 
            l_orderManagerReusableValidations.validateQuantity(
                l_tradedProduct,
                l_genSubAccount.getWeb3GenBranch(),
                l_dblModifiedOrderQuantity,
                l_orderUnitRow.getOrderType(),
                l_orderUnitRow.getRepaymentType(),
                l_orderUnitRow.getRepaymentNum());
            
            //getAfterChangePrice( )
            double l_dblAfterChangePrice = l_eqChangeOrderUnitEntry.getAfterChangePrice();
            
            //validate�����P��(double, �������, �⏕����)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�w�l�F�@@�M�p�V�K���������e�ڍ�.getAfterChangePrice()�̖߂�l 
            //��������F�@@validate�������()�̖߂�l 
            //�⏕�����F�@@�p�����[�^.�⏕����
            boolean l_isValidatePrice =
                l_orderManagerReusableValidations.validatePrice(
                    l_dblAfterChangePrice,
                    l_tradedProduct,
                    l_genSubAccount);
            if (l_isValidatePrice == false)
            {
                return new WEB3EquityOrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00293),
                        l_isShortSellingRestraint);
            }

            //get������t�w�l��l( )
            double l_dblModifiedStopOrderPrice = l_marginChangeOrderSpec.getModifiedStopOrderPrice();
            
            //get������iW�w�l�j�����w�l( )
            double l_dblModifiedWLimitPrice = l_marginChangeOrderSpec.getModifiedWLimitPrice();
            
            //get������iW�w�l�j���s����( )
            EqTypeExecutionConditionType l_changeAfterWlimitExecCondType = 
                l_marginChangeOrderSpec.getModifiedWlimitExecCondType();
            
            //get�iW�w�l�j�L����ԋ敪( )
            String l_strWlimitEnableStatusDiv = l_marginChangeOrderSpec.getWlimitEnableStatusDiv();
            
            //is������(EqTypeOrderUnit)
            boolean l_blnIsBuyOrder = this.isBuyOrder(l_eqOrderUnit);
            
            //validateW�w�l����(�⏕����, long, double, String, double, String, EqTypeExecutionConditionType, 
            //      String, �������, boolean, String, OrderCategEnum, double, String, OrderTypeEnum)
            //[����] 
            // �⏕�����F�@@�p�����[�^.�⏕���� 
            // �����P�ʂh�c�F�@@�����P��.�����P��ID 
            // �w�l�F�@@getAfterChangePrice()�̖߂�l 
            // ���������F�@@get�����㔭������()�̖߂�l 
            // ���������P���F�@@get������t�w�l��l()�̖߂�l 
            // �iW�w�l�j�����w�l�F�@@get������iW�w�l�j�����w�l()�̖߂�l 
            // �iW�w�l�j���s�����F�@@get������iW�w�l�j���s����()�̖߂�l 
            // �iW�w�l�j�L����ԋ敪�F�@@get�iW�w�l�j�L����ԋ敪()�̖߂�l 
            // ��������F�@@validate�������()�̖߂�l 
            // is�������F�@@is������()�̖߂�l 
            // �ٍϋ敪�F�@@�����P��.�ٍϋ敪 
            // �����J�e�S���F�@@�����P��.�����J�e�S�� 
            // �����F�@@get�����㒍������()�̖߂�l 
            // �l�i�����F�@@get������l�i����( ) 
            // ������ʁF�@@�����P��.������� 
            l_orderManagerReusableValidations.validateWLimitPriceOrder(
                l_genSubAccount,
                l_orderUnitRow.getOrderUnitId(),
                l_dblAfterChangePrice,
                l_strModifiedOrderConditionType,
                l_dblModifiedStopOrderPrice,
                WEB3StringTypeUtility.formatNumber(l_dblModifiedWLimitPrice),
                l_changeAfterWlimitExecCondType,
                l_strWlimitEnableStatusDiv,
                l_tradedProduct,
                l_blnIsBuyOrder,
                l_orderUnitRow.getRepaymentType(),
                l_orderUnitRow.getOrderCateg(),
                l_dblModifiedOrderQuantity,
                l_strModifiedPriceConditionType,
                l_orderUnitRow.getOrderType());
            
            //get�����㌚���( )
            double l_dblModifiedContractAmount = 
                l_marginChangeOrderSpec.getModifiedContractAmount();
            
            //validate�V�K��������(�⏕����, double, OrderTypeEnum, �������, �����P��)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�⏕�����F�@@�p�����[�^.�⏕���� 
            //������F�@@get�����㌚���()�̖߂�l 
            //������ʁF�@@�����P��.������� 
            //��������F�@@���������R���ʃ`�F�b�N.validate��������i�M�p�j()�̖߂�l
            //�����P�ʁF�@@�����P��
            l_orderManagerReusableValidations.validateMaxOpenMarginAmount(
                l_genSubAccount,
                l_dblModifiedContractAmount,
                l_orderUnitRow.getOrderType(),
                l_tradedProduct,
                l_orderUnitRow);

            //validate�V�K���������(�⏕����, double, OrderTypeEnum, �������, �����P��)
            //�����͈ȉ��̒ʂ�w�肷��B 
            //�⏕�����F�@@�p�����[�^.�⏕���� 
            //�������ʁF�@@�M�p�V�K�������������e.get�����㒍������()�̖߂�l 
            //������ʁF�@@�����P��.������� 
            //��������F�@@���������R���ʃ`�F�b�N.validate��������i�M�p�j()�̖߂�l
            //�����P�ʁF�@@�����P��
            l_orderManagerReusableValidations.validateMaxOpenMarginQuantity(
                l_genSubAccount,
                l_marginChangeOrderSpec.getModifiedOrderQuantity(),
                l_orderUnitRow.getOrderType(),
                l_tradedProduct,
                l_orderUnitRow);
            
            //is�󔄂�K��(�⏕����, �������, double, OrderTypeEnum, boolean, 
            //EqTypeExecutionConditionType, String, �����P��)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�⏕�����F�@@�p�����[�^.�⏕���� 
            //��������F�@@validate��������i�M�p�j()�̖߂�l 
            //�����F�@@�M�p�V�K�������������e.get�����㒍������()�̖߂�l 
            //������ʁF�@@�����P��.������� 
            //is���s�F�@@isAfterChangePriceMarket()�̖߂�l 
            //���s�����F�@@�M�p�V�K�������������e.get�����㎷�s����( ) 
            //�l�i�����F�@@�M�p�V�K�������������e.get������l�i����( ) 
            //�����P�ʁF�@@�����P��
            l_isShortSellingRestraint =
                l_orderManagerReusableValidations.isShortSellingRestraint(
                    l_genSubAccount,
                    l_tradedProduct,
                    l_dblModifiedOrderQuantity,
                    l_orderUnitRow.getOrderType(),
                    l_blnIsAfterChangePriceMarket,
                    l_executionConditionType,
                    l_strModifiedPriceConditionType,
                    l_orderUnitRow);
        
            //(*)����t���[
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
            {
                //validate�ǌ���������t�\(�����^�C�v : ProductTypeEnum)
                //�����͈ȉ��̒ʂ�ɐݒ肷��B 
                //�����^�C�v�F�@@�h�����h
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.EQUITY);
    
            }
            //get�����㔭���������Z�q
            String l_strModifiedOrderCondOperator = 
                l_marginChangeOrderSpec.getModifiedOrderCondOperator();
            
            //validate��������(�����P��, double, double, EqTypeExecutionConditionType, 
            //      String, String, String, double, double, EqTypeExecutionConditionType, boolean, 
            //      Date, EqTypeSettleContractOrderEntry[],EqTypeSettleContractOrderEntry)
            //[����] 
            // �����P�ʁF�@@getOrderUnit()�̖߂�l 
            // �����㊔���F�@@get�����㒍������()�̖߂�l 
            // ������w�l�F�@@getAfterChangePrice()�̖߂�l 
            // �����㎷�s�����F�@@get�����㎷�s����()�̖߂�l 
            // ������l�i�����F�@@get������l�i����()�̖߂�l 
            // �����㔭�������F�@@get�����㔭������()�̖߂�l 
            // �����㔭���������Z�q�F�@@get�����㔭���������Z�q()�̖߂�l 
            // ������t�w�l��l�F�@@get������t�w�l��l()�̖߂�l 
            // ������iW�w�l�j�����w�l�F�@@get������iW�w�l�j�����w�l()�̖߂�l 
            // ������iW�w�l�j���s�����F�@@get������iW�w�l�j���s����()�̖߂�l 
            // ������is�o����܂Œ����F�@@is������o����܂Œ���()�̖߂�l 
            // �����㒍���������F�@@get�����㒍��������()�̖߂�l 
            // �����㌈�ώw��G���g���F�@@null
            l_orderManagerReusableValidations.validateChangeItem(
                l_eqOrderUnit,
                l_dblModifiedOrderQuantity,
                l_dblAfterChangePrice,
                l_executionConditionType,
                l_strModifiedPriceConditionType,
                l_strModifiedOrderConditionType,
                l_strModifiedOrderCondOperator,
                l_dblModifiedStopOrderPrice,
                l_dblModifiedWLimitPrice,
                l_changeAfterWlimitExecCondType,
                l_blnIsModifiedIsCarriedOrder,
                l_datModifiedExpirationDate,
                null);
            
            //validate����������Rev���(�����P��, double, double, 
            //      EqTypeExecutionConditionType, String)
            //�����͈ȉ��̒ʂ�ɃZ�b�g����B 
            //�����㊔���F�@@get�����㒍������()�̖߂�l 
            //������w�l�F�@@getAfterChangePrice()�̖߂�l 
            //�����㎷�s�����F�@@get�����㎷�s����()�̖߂�l 
            //������l�i�����F�@@get������l�i����()�̖߂�l
            l_orderManagerReusableValidations.validateChangeOrderRevUpperLimit(
                l_eqOrderUnit,
                l_dblModifiedOrderQuantity,
                l_dblAfterChangePrice,
                l_executionConditionType,
                l_strModifiedPriceConditionType);
        
            //���������R������(ProcessingResult, boolean)
            //�����͈ȉ��̒ʂ�ɐݒ肷��B 
            //�����R�����ʁF�@@ProcessingResult.SUCCESS_RESULT�i�����R��OK�j 
            //�󔄂�K���Ώۃt���O�F�@@is�󔄂�K��( )�̖߂�l
            WEB3EquityOrderValidationResult l_orderValidationResult =
                new WEB3EquityOrderValidationResult(
                    ProcessingResult.SUCCESS_RESULT,
                    l_isShortSellingRestraint);

            log.exiting(STR_METHOD_NAME);
            return l_orderValidationResult;
        }
        catch (NotFoundException nfe)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    nfe.getMessage(),
                    nfe);
            log.error(STR_METHOD_NAME, l_sysException);
            return new WEB3EquityOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()),
                    l_isShortSellingRestraint);
        }
        catch (WEB3BaseException l_wbe)
        {
            return new WEB3EquityOrderValidationResult(
                ProcessingResult.newFailedResultInstance(l_wbe.getErrorInfo()),
                l_isShortSellingRestraint);
        }
    }
    
    /**
     * �ivalidate�����R�[�h�i�M�p)�j�B<BR>
     * <BR>
     * �����R�[�h�̑��݃`�F�b�N�y�є�����~�`�F�b�N�i�M�p�j�����{����B<BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A���������I�u�W�F�N�g��ԋp����B<BR>
     * �i* ���������R���`�F�b�N.validate�����R�[�h�i�M�p�j( )�ɈϏ�����B�j
     * @@param l_strProductCode �����R�[�h
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@return WEB3EquityProduct
     * @@throws WEB3BaseException
     * @@roseuid 4100F4260097
     */
    public WEB3EquityProduct validateProductCode(
        String l_strProductCode,
        String l_strInstitutionCode,
        String l_strRepaymentType)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateProductCode(l_strProductCode,l_strInstitutionCode,l_strRepaymentType)"; 
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        log.exiting(STR_METHOD_NAME);
        return l_orderManagerReusableValidations.validateProductCode(
            l_strProductCode, 
            l_strInstitutionCode, 
            l_strRepaymentType);
    }
    
    /**
     * �ivalidate��������i�M�p�j�j�B<BR>
     * <BR>
     * �戵�\�`�F�b�N�A�y�ѕٍϋ敪�ʂ̔�����~�`�F�b�N�i�M�p�j�����{����B<BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A��������I�u�W�F�N�g��ԋp����B<BR>
     * �i* ���������R���`�F�b�N.validate��������i�M�p�j( )�ɈϏ�����B�j
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�B
     * @@param l_product �i���������j<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@param l_market �i�s��j<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�B
     * @@param l_branch �i���X�j<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�B
     * @@param l_strRepaymentType �i�ٍϋ敪�j<BR>
     * �@@�@@�@@�ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_orderCateg �i�����J�e�S���j<BR>
     * �@@�@@�@@�����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_isShort �iis�����j<BR>
     * �@@�@@�@@�����^�����̃t���O�B<BR>
     * �@@�@@�@@�����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B
     * @@return WEB3EquityTradedProduct
     * @@throws WEB3BaseException
     * @@roseuid 4100F4CB0358
     */
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
        SubAccount l_subAccount,
        WEB3EquityProduct l_product, 
        WEB3GentradeMarket l_market, 
        WEB3GentradeBranch l_branch, 
        String l_strRepaymentType, 
        OrderCategEnum l_orderCateg, 
        boolean l_isShort)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProductForMarginTrading(SubAccount, WEB3EquityProduct, WEB3GentradeMarket, WEB3GentradeBranch, String, OrderCategEnum, boolean)"; 
        log.entering(STR_METHOD_NAME);

        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();

        log.exiting(STR_METHOD_NAME);
        // ���������R���`�F�b�N.validate��������i�M�p�j()�ɈϏ�����B
        return l_orderManagerReusableValidations.validateTradedProductForMarginTrading(
            l_subAccount,
            l_product, 
            l_market, 
            l_branch, 
            l_strRepaymentType, 
            l_orderCateg, 
            l_isShort);
    }
    
    /**
     * �ivalidate�戵�\�s��i�M�p�j�j�B<BR>
     * <BR>
     * ��Е��X���A�M�p����̎w��ٍϋ敪�E�ٍϊ�����<BR>
     * �戵�\�s�ꂩ���`�F�b�N����B<BR>
     * �i* ���������R���`�F�b�N.validate�戵�\�s��i�M�p�j( )�ɈϏ�����B�j
     * @@param l_branch �i���X�j<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�i�ڋq�̎���X�j
     * @@param l_tradedProduct �i��������j<BR>
     * �@@�@@�@@������������I�u�W�F�N�g
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_strRepaymentType �ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_dblRepaymentNum �ٍϊ����l
     * @@throws WEB3BaseException
     * @@roseuid 4104B2960138
     */
    public void validateHandlingMarket(
        WEB3GentradeBranch l_branch, 
        WEB3EquityTradedProduct l_tradedProduct, 
        String l_strMarketCode, 
        String l_strRepaymentType, 
        double l_dblRepaymentNum)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateHandlingMarket(l_branch,l_tradedProduct,l_strMarketCode," +
            "l_strRepaymentType,l_dblRepaymentNum)"; 
        log.entering(STR_METHOD_NAME);
        if (Double.isNaN(l_dblRepaymentNum))
        {
            l_dblRepaymentNum = 0D;
        }
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        l_orderManagerReusableValidations.validateHandlingMarket(
            l_branch, 
            l_tradedProduct, 
            l_strMarketCode, 
            l_strRepaymentType, 
            l_dblRepaymentNum);
            
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     * �ivalidate�s��R�[�h�j�B<BR>
     * <BR>
     * �s��R�[�h�̃`�F�b�N�����{����B<BR>
     * ���݂���ꍇ�͎s��I�u�W�F�N�g��ԋp����B<BR>
     * �i* ���������R���`�F�b�N.validate�s��R�[�h( )�ɈϏ�����B�j
     * @@param l_strMarketCode �s��R�[�h
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@return Market
     * @@throws WEB3BaseException
     * @@roseuid 4100F7300285
     */
    public Market validateMarket(String l_strMarketCode, String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMarket(String l_strMarketCode, String l_strInstitutionCode)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        log.exiting(STR_METHOD_NAME);
        
        return l_orderManagerReusableValidations.validateMarket(l_strMarketCode, l_strInstitutionCode);
    }
    
    /**
     * �ivalidate��������J�݁i�M�p�j�j�B<BR>
     * <BR>
     * ����������J�݂��Ă��邩�ǂ����̃`�F�b�N���s���B<BR>
     * �i* ���������R���`�F�b�N.validate��������J�݁i�M�p�j( )�ɈϏ�����B�j<BR>
     * @@param l_genSubAccount - �⏕�����I�u�W�F�N�g�B
     * @@param l_taxType �ŋ敪�B<BR>
     * �@@�@@�@@�i* �ڋq�}�X�^.�M�p����ŋ敪�j
     * @@param l_datDeliveryDate ��n���B<BR>
     * �@@�@@�@@�i�������.��n���j
     * @@throws WEB3BaseException
     * @@roseuid 4105BE8E015D
     */
    public void validateMarginSpecialAccountOpen(
        WEB3GentradeSubAccount l_genSubAccount,
        TaxTypeEnum l_taxType,
        Date l_datDeliveryDate)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMarginSpecialAccountOpen(l_genSubAccount, l_taxType, l_datDeliveryDate)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        log.exiting(STR_METHOD_NAME);
        l_orderManagerReusableValidations.validateMarginSpecialAccountOpen(l_genSubAccount, l_taxType, l_datDeliveryDate);
    }
    
    /**
     * �iis�J�z�����P�ʁj�B<BR>
     * �iis�J�z�����P��(�����P��)�̃I�[�o�[���[�h���\�b�h�j<BR>
     * <BR>
     * �����J�z�œo�^���ꂽ�������ǂ����𔻒肷��B<BR>
     * �u�J�z�����v�̏ꍇ��true���A�u�J�z�����v�ł͂Ȃ��ꍇ��false���A<BR>
     * ���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@this.getOrderUnit(����.�����P��ID)�ŁA�����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@this.is�J�z�����P��(�����P��)��delegate����B<BR>
     * @@param l_lngOrderUnitId �i�����P��ID�j<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g.�����P��ID�B
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4111C850039F
     */
    public boolean isCarryOverOrderUnit(long l_lngOrderUnitId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCarryOverOrderUnit(long l_dblOrderUnitId)";        
        log.entering(STR_METHOD_NAME);     
		EqTypeOrderUnit l_orderUnit;
        
        try
        {
            l_orderUnit = (EqTypeOrderUnit)this.getOrderUnit(l_lngOrderUnitId);
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return this.isCarryOverOrderUnit(l_orderUnit);
    }
    
	/**
	 * �iis�J�z�����P�ʁj�B<BR>
	 * <BR>
	 * �����J�z�œo�^���ꂽ�������ǂ����𔻒肷��B<BR>
	 * �u�J�z�����v�̏ꍇ��true���A�u�J�z�����v�ł͂Ȃ��ꍇ��false���A<BR>
	 * ���ꂼ��ԋp����B<BR>
	 * <BR>
	 * �P�j�@@�����̒����P��.���񒍕��̒����P��ID > 0�̏ꍇ�́Atrue��Ԃ��B<BR>
	 * �@@�@@�@@�����̒����P��.���񒍕��̒����P��ID���inull or 0�j�̏ꍇ�́Afalse��Ԃ��B
	 * @@param l_orderUnit �i�����P�ʃI�u�W�F�N�g�j<BR>
	 * �@@�@@�@@�����P�ʃI�u�W�F�N�g�B
	 * @@return boolean
	 * @@throws WEB3BaseException
	 * @@roseuid 4111C850039F
	 */
	public boolean isCarryOverOrderUnit(EqTypeOrderUnit l_orderUnit) 
		throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "isCarryOverOrderUnit(EqTypeOrderUnit l_orderUnit)";        
		log.entering(STR_METHOD_NAME);     
		boolean l_blnIsCarryOverOrderUnit = false;
        
		EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
		if (l_orderUnitRow.getFirstOrderUnitIdIsNull() || l_orderUnitRow.getFirstOrderUnitId() == 0)
		{
			//�����̒����P��.���񒍕��̒����P��ID���inull or 0�j�̏ꍇ�́Afalse��Ԃ��B
			l_blnIsCarryOverOrderUnit = false;
		}
		else if (l_orderUnitRow.getFirstOrderUnitId() > 0)
		{
			l_blnIsCarryOverOrderUnit = true;
		}
		log.exiting(STR_METHOD_NAME);
		return l_blnIsCarryOverOrderUnit;
	}
	
    /**
     * �ivalidate��������J�݁j�B<BR>
     * <BR>
     * ����������J�݂��Ă��邩�ǂ����̃`�F�b�N���s���B<BR>
     * �i* ���������R���`�F�b�N.validate��������J��( )�ɈϏ�����B�j<BR>
     * @@param l_subAccount �⏕�����I�u�W�F�N�g�B
     * @@param l_taxType �ŋ敪�B<BR>
     * �@@�@@�@@�i* �ڋq�}�X�^.�M�p����ŋ敪�j
     * @@param l_datDeliveryDate ��n���B<BR>
     * �@@�@@�@@�i�������.��n���j
     * @@throws WEB3BaseException
     * @@roseuid 411705A30064
     */
    public void validateSpecialAccountEstablish(
        WEB3GentradeSubAccount l_subAccount, 
        TaxTypeEnum l_taxType, 
        Date l_datDeliveryDate) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSpecialAccountEstablish(l_genSubAccount, l_taxType, l_datDeliveryDate)";
        
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        l_orderManagerReusableValidations.validateSpecialAccountEstablish(l_subAccount, l_taxType, l_datDeliveryDate);
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * �iget���񒍕��̒����P�ʁj�B<BR>
     * <BR>
     * �����Ŏw�肳�ꂽ�����P�ʃI�u�W�F�N�g�́A���񒍕��̒����P�ʃI�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.���񒍕��̒����P��ID���inull �܂��� 0�j�̏ꍇ<BR>
     * <BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g��ԋp����B<BR>
     * <BR>
     * �Q�j�@@��L�ȊO�̏ꍇ�A<BR>
     * <BR>
     * �@@�@@this.getOrderUnit(�����̒����P��.���񒍕��̒����P��ID)�Ŏ擾����<BR>
     * �@@�@@�����P�ʃI�u�W�F�N�g��ԋp����B
     * @@param l_orderUnit �����P�ʃI�u�W�F�N�g�B
     * @@return OrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 4120245802C9
     */
    public EqTypeOrderUnit getFirstOrderUnit(EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getFirstOrderUnit(EqTypeOrderUnit l_orderUnit)";
        
        log.entering(STR_METHOD_NAME);
        if (l_orderUnit == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        try
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
            if (l_orderUnitRow.getFirstOrderUnitIdIsNull() || l_orderUnitRow.getFirstOrderUnitId() == 0)
            {
                //�����̒����P��.���񒍕��̒����P��ID���inull �܂��� 0�j�̏ꍇ
                //�����̒����P�ʃI�u�W�F�N�g��ԋp����B
                log.exiting(STR_METHOD_NAME);
                return l_orderUnit;
            }
            else
            {
                //��L�ȊO�̏ꍇ
                log.exiting(STR_METHOD_NAME);
                return (EqTypeOrderUnit)this.getOrderUnit(l_orderUnitRow.getFirstOrderUnitId());
            }            
        }
        catch (NotFoundException l_nfex)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, STR_METHOD_NAME);
        }

        
    }
    
    /**
     * �iget����Ώۖ��j�B<BR>
     * <BR>
     * ����Ώۂ̖��f�[�^���擾���ԋp����B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.getExecutions( )�ŁA�Ώے����ɕR�t�����f�[�^��S�Ď擾����B<BR>
     * <BR>
     * �@@�@@�@@�Y��������f�[�^�����݂��Ȃ��ꍇ�A�u���Ȃ��v�̗�O��throw����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00676<BR>
     * <BR>
     * �Q�j�@@�P�j�Ŏ擾�������f�[�^�̒�����A�ȉ��̏����ɍ��v����f�[�^���������A<BR>
     * �@@�@@�@@���v������f�[�^��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@-----------------------------------------------------<BR>
     * �@@�@@�@@������������<BR>
     * <BR>
     * �@@�@@�@@���.��萔�� �� ����.��芔��<BR>
     * �@@�@@�@@���@@���.���P�� �� ����.���P��<BR>
     * �@@�@@�@@-----------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�E���������ɍ��v����f�[�^�����������݂���ꍇ�A<BR>
     * �@@�@@�@@�@@���v������f�[�^�̂����A���.���������ł��ߋ��̓����̃f�[�^���A<BR>
     * �@@�@@�@@�@@����Ώۂ̖��f�[�^�Ƃ��ĕԋp����B<BR>
     * �@@�@@�@@�@@�����.���������ł��ߋ��̓����̃f�[�^�����������݂���ꍇ�́A<BR>
     * �@@�@@�@@�@@�����.��菇�ԍ�����ԏ������f�[�^�i���ł��ߋ��̖��Ƃ��Ĉ����j���A<BR>
     * �@@�@@�@@�@@������Ώۖ��f�[�^�Ƃ��ĕԋp����B<BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * ���P�ʁB
     * @@param l_dblExecQuantity - (��芔��)<BR>
     * ��芔���B
     * @@param l_dblExecPrice - (���P��)<BR>
     * ���P���B
     * @@return EqTypeOrderExecution
     * @@throws WEB3BaseException
     */
    public EqTypeOrderExecution getCancelOrderExecution(
        EqTypeOrderUnit l_orderUnit, 
        double l_dblExecQuantity, 
        double l_dblExecPrice)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getCancelOrderExecution(EqTypeOrderUnit, double, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                STR_METHOD_NAME);
        }
        
        OrderExecution[] l_orderExecutions = l_orderUnit.getExecutions();
        
        //�Y��������f�[�^�����݂��Ȃ��ꍇ�A�u���Ȃ��v�̗�O��throw����B 
        if (l_orderExecutions == null || l_orderExecutions.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00676,
                STR_METHOD_NAME);
        }
        
        int l_intLen = l_orderExecutions.length;
        EqTypeOrderExecution l_resultOrderExecution = null; 
        Timestamp l_executionTimestamp = null;
        int l_intMinExecNumber = Integer.MAX_VALUE;
        for (int i = 0; i < l_intLen; i ++)
        {            
            EqTypeOrderExecution l_orderExecution =
                (EqTypeOrderExecution)l_orderExecutions[i];
            EqtypeOrderExecutionRow l_orderExecutionRow =
                (EqtypeOrderExecutionRow)l_orderExecution.getDataSourceObject();
              
            if (l_orderExecutionRow.getExecPrice() == l_dblExecPrice &&            
                l_orderExecution.getExecutionQuantity() == l_dblExecQuantity)
            {
                if (l_executionTimestamp == null || WEB3DateUtility.compareToSecond(l_executionTimestamp, l_orderExecution.getExecutionTimestamp()) > 0)
                {
                    l_executionTimestamp = l_orderExecution.getExecutionTimestamp();
                    l_intMinExecNumber = l_orderExecutionRow.getExecSerialNo();
                    l_resultOrderExecution = l_orderExecution;
                }
                else if (WEB3DateUtility.compareToSecond(l_executionTimestamp, l_orderExecution.getExecutionTimestamp()) == 0)
                {
                    if (l_intMinExecNumber > l_orderExecutionRow.getExecSerialNo())
                    {
                        l_resultOrderExecution = l_orderExecution;
                        l_intMinExecNumber = l_orderExecutionRow.getExecSerialNo();
                    }
                }             
            }
        }
                
        log.exiting(STR_METHOD_NAME);
        return l_resultOrderExecution;
    }
    /**
     * �ivalidate�~�j�������j�B<BR>
     * <BR>
     * �~�j����������̋��ʃ`�F�b�N�����{����B<BR>                                                                                              
     *  <BR>                                                                                                                                     
     * �ȉ��̃`�F�b�N���s���B <BR>                                                                                                               
     * �@@�|�ڋq�̃`�F�b�N�i�x�q�A�Ǘ����b�N���j<BR>                                                                                              
     * �@@�|��t���ԃ`�F�b�N <BR>                                                                                                                 
     * �@@�|�V�X�e����~���`�F�b�N  <BR>                                                                                                          
     * <BR>                                                                                                                                     
     * �V�[�P���X�} <BR>                                                                                                                        
     * �u�i�~�j�������jvalidate�~�j�������v�Q�ƁB
     * @@param l_subAccount �i�⏕�����j
     * �@@�@@�@@�⏕�����I�u�W�F�N�g
     */
    public void validateMiniStockOrder(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMiniStockOrder(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        //2) getInstance()
        WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations = 
            (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        //3) commonFirstValidationsForAllOperations(SubAccount)
        try
        {
            l_equityTypeOrderManagerReusableValidations.commonFirstValidationsForAllOperations(l_subAccount);    
        }
        catch(OrderValidationException l_ex)
        {
        	log.exiting(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				l_ex.getValidationResult().getProcessingResult().getErrorInfo(),
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage());
        }
        //5) validate������t�\()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * �ivalidate��������i�~�j���j�j�B<BR>
     * <BR>
     * �~�j�������C�~�j���s����`�F�b�N���A��������I�u�W�F�N�g��ԋp����B<BR>                                                             
     * �@@�|�����������݃`�F�b�N  <BR>                                                                                                       
     * �@@�|�~�j���s��`�F�b�N  <BR>                                                                                                         
     * �@@�|�~�j����������~����Ă��Ȃ����Ƃ̃`�F�b�N  <BR>                                                                                 
     * �@@�|�C���T�C�_�[�`�F�b�N <BR>                                                                                                        
     * �@@�|�ڋq�����ʎ����~�`�F�b�N <BR>                                                                                                  
     *  <BR>                                                                                                                               
     * �V�[�P���X�} <BR>                                                                                                                   
     * �u�i�~�j�������jvalidate��������i�~�j���j�v�Q�ƁB <BR>
     * <BR>
     * �@@5 �擾�����������.���敪���h�O�������h�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_01341
     * @@param l_subAccount �i�⏕�����j�B
     * @@param l_strProductCode �i�����R�[�h�j�B
     * @@param l_blnIsSellOrder �iis�������j�B<BR>
     * �@@�@@�@@���������ǂ����𔻒肷��t���O <BR>
     * <BR>
     * �@@�@@�@@true�F�@@������<BR>
     * �@@�@@�@@false�F�@@������
     * @@return WEB3EquityTradedProduct
     */
    public WEB3EquityTradedProduct validateMiniStockTradedProduct(
        WEB3GentradeSubAccount l_subAccount, 
        String l_strProductCode, 
        boolean l_blnIsSellOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMiniStockTradedProduct(WEB3GentradeSubAccount, String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //1) getInstance()
        WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations = 
            (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        //3) validate�����R�[�h�i�~�j���j
        WEB3EquityProduct l_equityProduct = l_equityTypeOrderManagerReusableValidations.validateMiniStockProductCode(
            l_strProductCode, l_subAccount.getInstitution().getInstitutionCode());
        
        //4) validate��������i�~�j���j(��������, boolean)
        WEB3EquityTradedProduct l_tradedProduct = 
            l_equityTypeOrderManagerReusableValidations.validateMiniStockTradedProduct(l_equityProduct, l_blnIsSellOrder);
        
        //5) �擾�����������.���敪���h�O�������h�̏ꍇ�̂�
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (WEB3ListTypeDef.FOREIGN_SECITION.equals(l_tradedProductRow.getListType()))
        {
            WEB3GentradeMainAccount l_account = new WEB3GentradeMainAccount(
                (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject());
            if (l_account.isForeignAccountOpen() == false)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01341,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        //6) validate�C���T�C�_�[
        l_equityTypeOrderManagerReusableValidations.validateInsider(l_subAccount, l_equityProduct);
        
        //7) validate�ڋq�����ʎ����~
        OrderTypeEnum l_orderType;
        if (l_blnIsSellOrder)
        {
            l_orderType = OrderTypeEnum.MINI_STOCK_SELL;
        }
        else
        {
            l_orderType = OrderTypeEnum.MINI_STOCK_BUY;
        }
        l_equityTypeOrderManagerReusableValidations.validateAccountProductOrderStop(
            l_subAccount,
            l_equityProduct.getProductId(),
            l_orderType);
        
        return l_tradedProduct;
    }

    /**
     * �ivalidate�~�j�����t�����j�B<BR>
     * <BR>
     * �ivalidateMiniStockBuyOrder�j <BR>
     * �~�j�����t���������R�������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�~�j�������jvalidate�~�j�����t�����v�Q�ƁB
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g
     * @@param l_mstkOrderSpec �i�~�j���������e�j<BR>
     * �@@�@@�@@�~�j���������e�I�u�W�F�N�g
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateMiniStockBuyOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3NewMiniStockOrderSpec l_mstkOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMiniStockBuyOrder(WEB3GentradeSubAccount, WEB3NewMiniStockOrderSpec)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_mstkOrderSpec == null)
        {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        //2) validate�~�j������(�⏕����)
        try
        {
            this.validateMiniStockOrder(l_subAccount);    
        }
        catch(WEB3BaseException l_ex)
        {
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                l_ex.getErrorInfo()));
        }
        
        //3) getInstance()
        WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations = 
            (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        //4) getInstitution()
        Institution l_institution = l_subAccount.getInstitution();
        
        //5) getProductCode()
        String l_strProductCode = l_mstkOrderSpec.getProductCode();
        
        //6) getMarketCode()
        String l_strMarketCode = l_mstkOrderSpec.getMarketCode();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        //7) getTradedProduct(arg0�i=�،���Ёj : Institution, arg1�i=�����R�[�h�j : String, arg2�i=�s��R�[�h�j : String)
        try
        {
            WEB3EquityTradedProduct l_tradedProduct = 
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
            //8) getQuantity()
            double l_dblQuantity = l_mstkOrderSpec.getQuantity();
            //9) validate�~�j�����t����(�������, double)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockBuyQuantity(l_tradedProduct, l_dblQuantity);
            //10) validate�~�j���d������(�⏕����, �������)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
            //11) validate�~�j�����t�������Ԓ�������(�⏕����, �������, double)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockBuyDeregTermQuantity(
                l_subAccount, l_tradedProduct, l_dblQuantity);
            //12) NewOrderValidationResult
            NewOrderValidationResult l_newOrderValidationResult = new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            
            return l_newOrderValidationResult;                
        }
        catch(NotFoundException l_ex)
        {
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            log.error(STR_METHOD_NAME, l_sysException);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()));
        } 
    }
    /**
     * �ivalidate�~�j�����t�����j<BR>
     * <BR>
     * �ivalidateMiniStockSellOrder�j<BR>                                                   
     * �~�j�����t���������R�������{����B<BR>                                               
     *  <BR>                                                                                
     * �V�[�P���X�} <BR>                                                                    
     * �u�i�~�j�������jvalidate�~�j�����t�����v�Q�ƁB<BR>   
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g
     * @@param l_mstkOrderSpec �i�~�j���������e�j<BR>
     * �@@�@@�@@�~�j���������e�I�u�W�F�N�g
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateMiniStockSellOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3NewMiniStockOrderSpec l_mstkOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMiniStockSellOrder(WEB3GentradeSubAccount, WEB3NewMiniStockOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //2) validate�~�j������(�⏕����)
        try
        {
            this.validateMiniStockOrder(l_subAccount);    
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                l_ex.getErrorInfo()));
        }

        //3) getInstance()
        WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations = 
            (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        //4) getInstitution()
        Institution l_institution = l_subAccount.getInstitution();
        
        //5) getProductCode()
        String l_strProductCode = l_mstkOrderSpec.getProductCode();
        
        //6) getMarketCode()
        String l_strMarketCode = l_mstkOrderSpec.getMarketCode();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager = (WEB3EquityProductManager)l_tradingModule.getProductManager();
        //7) getTradedProduct(arg0�i=�،���Ёj : Institution, arg1�i=�����R�[�h�j : String, arg2�i=�s��R�[�h�j : String)
        try
        {
            WEB3EquityTradedProduct l_tradedProduct = 
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
            //8) getQuantity()
            double l_dblQuantity = l_mstkOrderSpec.getQuantity();
            //9) validate�~�j�����t����(�������, double)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockSellQuantity(l_subAccount, l_tradedProduct, l_dblQuantity);
            //10) validate�~�j���d������(�⏕����, �������)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
            //11) validate�~�j�����t�������Ԓ�������(�⏕����, �������, double)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockSellDeregTermQuantity(
                l_subAccount, l_tradedProduct, l_dblQuantity);
            //12) NewOrderValidationResult
            NewOrderValidationResult l_newOrderValidationResult = new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            
            log.exiting(STR_METHOD_NAME);
            return l_newOrderValidationResult;                
        }
        catch(NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            WEB3SystemLayerException l_sysException =
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            log.error(STR_METHOD_NAME, l_sysException);
            return new NewOrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                    l_sysException.getErrorInfo()));
        } 
    }
    /**
     * �ivalidate�~�j����������j�B<BR>
     * <BR>
     * �ivalidateMiniStockCancelOrder�j<BR>                                 
     *  <BR>                                                                
     * �V�[�P���X�} <BR>                                                    
     * �u�i�~�j�������jvalidate�~�j����������v�Q�ƁB<BR>
     * <BR>
     *  ========================================================<BR>
     * �V�[�P���X�}(�����E���G���e�B�e�B / �i�~�j�������jvalidate�~�j���������): <BR>
     * ����\�Ȓ����łȂ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00155<BR>
     * ==========================================================
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g
     * @@param l_cancelOrderSpec �i����������e�j<BR>
     * �@@�@@�@@����������e�I�u�W�F�N�g
     * @@return OrderValidationResult
     */
    public OrderValidationResult validateMiniStockCancelOrder(
        WEB3GentradeSubAccount l_subAccount, CancelOrderSpec l_cancelOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMiniStockSellOrder(WEB3GentradeSubAccount, CancelOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //2) getOrderUnits(long)
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_cancelOrderSpec.getOrderId());
        if (l_orderUnits.length == 0)
        {
        	throw new WEB3SystemLayerException(
        				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
        				this.getClass().getName() + "." + STR_METHOD_NAME
        	);
        }
        //3) validate�~�j������(�⏕����)
        try
        {
            this.validateMiniStockOrder(l_subAccount);

            //4) is����\�����P�ʁi�~�j���j(�����P��)
            boolean l_blnMiniStockCancelOrderUnit = this.isMiniStockCancelOrderUnit(l_orderUnits[0]);
            //5) ����\�Ȓ����łȂ��ꍇ�A��O���X���[����
            if(!l_blnMiniStockCancelOrderUnit)
            {
                log.exiting(STR_METHOD_NAME);

                return new OrderValidationResult(
                    ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00155));
            }
            //6) OrderValidationResult
            OrderValidationResult l_orderValidationResult = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            log.exiting(STR_METHOD_NAME);
            return l_orderValidationResult;  
        }
        catch(WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return new OrderValidationResult(
                ProcessingResult.newFailedResultInstance(
                l_ex.getErrorInfo()));
        }
    }
    /**
     * �ivalidate�~�j���d�������j�B<BR>
     * <BR>
     * ������ɓ�������̃~�j����������Ă��Ȃ����`�F�b�N���s���B <BR>            
     *  <BR>                                                                       
     * �����R���ʃ`�F�b�N.validate�~�j���d������()�ɏ������Ϗ�����B<BR>  
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g
     * @@param l_tradedProduct �i��������j<BR>
     * �@@�@@�@@��������I�u�W�F�N�g
     */
    public void validateMiniStockDuplicateOrder(WEB3GentradeSubAccount l_subAccount, WEB3EquityTradedProduct l_tradedProduct) 
        throws WEB3BaseException
    {
        WEB3EquityTypeOrderManagerReusableValidations l_equityTypeOrderManagerReusableValidations = 
            (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        l_equityTypeOrderManagerReusableValidations.validateMiniStockDuplicateOrder(
            l_subAccount, l_tradedProduct);
    }
    /**
     * �icalc�T�Z��n����i�~�j���j�j�B<BR>
     * <BR>
     * �~�j���T�Z��n���z���Z�o���ĕԋp����B<BR>
     * <BR>                                               
     * �V�[�P���X�}�u�i�~�j�������jcalc�T�Z��n����i�~�j���j�v�Q�ƁB 
     * @@param l_commission �i�萔���j
     * @@param l_subAccount �i�⏕�����j
     * @@param l_tradedProduct �i��������j
     * @@param l_dblQuantity �i�����j
     * @@param l_blnIsSellOrder �iis�������j<BR>
     * �@@�@@�@@�������̏ꍇ��true�A�������̏ꍇ��false���w�肷��B
     * @@param l_dblCalcUnitPrice �i�v�Z�P���j
     * @@param l_blnIsRestraint �iis�S���l���j
     * @@return WEB3EquityEstimatedDeliveryPrice
     */
    public WEB3EquityEstimatedDeliveryPrice calcMiniStockEstimatedDeliveryAmount(
        WEB3GentradeCommission l_commission, 
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityTradedProduct l_tradedProduct, 
        double l_dblQuantity, 
        boolean l_blnIsSellOrder, 
        double l_dblCalcUnitPrice,
        boolean l_blnIsRestraint) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "calcMiniStockEstimatedDeliveryAmount(WEB3GentradeCommission, WEB3GentradeSubAccount, WEB3EquityTradedProduct, double, boolean, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //1.1. �T�Z��n����v�Z����()
        WEB3EquityEstimatedDeliveryPrice l_equityEstimatedDeliveryPrice = 
            new WEB3EquityEstimatedDeliveryPrice();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityBizLogicProvider l_equityBizLogicProvider = 
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        //1.2. �v�Z�P�����w�肳��Ȃ������i�����̌v�Z�P�� == null�j�ꍇ�̂ݎ��{
        if(Double.isNaN(l_dblCalcUnitPrice) || l_dblCalcUnitPrice == 0.0D)
        {
            //1.2.1. get����()
            l_dblCalcUnitPrice = l_productManager.getCurrentPrice(l_tradedProduct, true);
        }

        //1.3. setIs�w�l()
        l_commission.setIsLimitPrice(false);
        
        //1.4. set�v�Z�P��()
        l_equityEstimatedDeliveryPrice.setCalcUnitPrice(l_dblCalcUnitPrice);

        //1.5. get����X()
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        
        //1.6. get�萔�����i�R�[�h()
        String l_strCommissionProductCode =
            l_commission.getCommissionProductCode();
        
        //1.7. get�����P��()
        double l_dblLotSize = l_tradedProduct.getLotSize();
        
        //1.8. �S������������Z�o����ꍇ
        if (!l_blnIsSellOrder && l_blnIsRestraint)
        {
            //1.8.1. calc�S���������()
            double l_dblRestraintTurnover = l_equityBizLogicProvider.calcRestraintTurnover(
                l_dblLotSize,
                l_equityEstimatedDeliveryPrice.getCalcUnitPrice(),
                l_branch.getBranchId(),
                l_strCommissionProductCode,
                false);
            
            //1.9. set���o��v�Z�p���()
            l_commission.setExpensesCalcAmount(l_dblRestraintTurnover);
        }
        else
        {
            //1.9. set���o��v�Z�p���()
            l_commission.setExpensesCalcAmount(
                l_dblLotSize * l_equityEstimatedDeliveryPrice.getCalcUnitPrice());
        }
        //1.10. calc�ϑ��萔��()
        l_equityBizLogicProvider.calcCommission(l_commission, l_subAccount);
        
        //1.11. get�萔�����z()
        double l_dblCommissionAmount = l_commission.getCommission();
        //1.12. get�Œ�萔��()
        double l_dblLowCommAmount = l_commission.getMinCommission();
        
        //1.13. set�ϑ��萔��()
        double l_dblResult = l_dblCommissionAmount * l_dblQuantity / l_dblLotSize;
        double l_dblAmount;     
        if (l_dblResult < l_dblLowCommAmount)
        {
            l_dblAmount = l_dblLowCommAmount;
        }
        else
        {
            l_dblAmount = l_dblResult;
        }
        
        // �ϑ��萔���̏����_�ȉ��̐؂�̂�
		l_dblAmount = Math.floor(l_dblAmount);
        log.debug("�ϑ��萔�� = " + l_dblAmount);
        l_equityEstimatedDeliveryPrice.setCommissionFee(l_dblAmount);
        
        //1.14. get������()
        Timestamp l_tsBizDate = l_commission.getOrderBizDate();
        
        //1.15. calc�����()
        double l_dblSalesTax =
            l_equityBizLogicProvider.calcSalesTax(
                l_dblAmount,
                l_tsBizDate,
                l_subAccount);
        log.debug("calc����� = " + l_dblSalesTax);
        
        //1.16. set�ϑ��萔�������()
        l_equityEstimatedDeliveryPrice.setCommissionFeeTax(l_dblSalesTax);
        
        //1.17. set�T�Z��n���()
        double l_dblResults;
        if (!l_blnIsSellOrder)
        {
            l_dblResults = 
                l_commission.getExpensesCalcAmount() * l_dblQuantity / l_dblLotSize + l_dblAmount + l_equityEstimatedDeliveryPrice.getCommissionFeeTax();
        }
        else
        {
            l_dblResults = 
            l_commission.getExpensesCalcAmount() * l_dblQuantity / l_dblLotSize - l_dblAmount - l_equityEstimatedDeliveryPrice.getCommissionFeeTax();
        }
        
        // �T�Z��n����̏����_�ȉ��̐؂�̂�
        l_dblResults = Math.floor(l_dblResults);   
        log.debug("�T�Z��n��� = " + l_dblResults);
        l_equityEstimatedDeliveryPrice.setEstimateDeliveryAmount(l_dblResults);
        
        return l_equityEstimatedDeliveryPrice;
    }
    /**
     * �iget�~�j�������������j�B<BR>
     * <BR>
     * �igetMiniStockOrderingQuantity�j<BR>                                                          
     *  <BR>                                                                                         
     * �P�j�@@�����P�ʎ擾 <BR>                                                                                 
     * �@@�y�����P�ʃe�[�u���z����A�ȉ��̏������w�肵�Y�����钍���P�ʂ��擾����B<BR>                                         
     *   <BR>                                                                                                                                              
     * �@@[����]  <BR>                                                                                                                                             
     * �@@�����h�c = �����h�c And <BR>                                                                                                                            
     * �@@�⏕�����h�c = �⏕�����h�c And  <BR>                                                                                                                   
     * �@@�����h�c = �����h�c And <BR>                                                                                                                              
     * �@@������� = �� And <BR>                                                                                                                                                                   
     * �@@�����L����� = OrderOpenStatusEnum.OPEN  <BR>                                                                                                                                            
     *   <BR>                                                                                                                                                                                     
     * �@@�� �������  <BR>                                                                                                               
     * �@@�@@���w��iis������ == true�j�̏ꍇ�A<BR>
     *     OrderTypeEnum.MINI_STOCK_SELL�i�����~�j�����������j <BR>                                        
     * �@@�@@���w��iis������ == false�j�̏ꍇ�A<BR>
     *     OrderTypeEnum.MINI_STOCK_BUY�i�����~�j�����������j<BR>                                                                                              
     *   <BR>                                                                                                                            
     * �Q�j�@@�������v <BR>                                                                                                                                                
     * �@@�擾�������ׂĂ̊����i�����P��.getQuantity()�j�̍��v�l��ԋp����B  
     * @@param l_lngMainAccountId �i����ID�j
     * @@param l_lngSubAccountId �i�⏕����ID�j
     * @@param l_lngProductId �i����ID�j
     * @@param l_blnIsSellOrder (is������)<BR>
     * �@@�@@�@@���������ǂ����𔻒肷��t���O <BR>
     * <BR>
     * �@@�@@�@@true�F�@@������ <BR>
     * �@@�@@�@@false�F�@@������
     * @@return double 
     */
    public double getMiniStockOrderingQuantity(
        long l_lngMainAccountId, 
        long l_lngSubAccountId, 
        long l_lngProductId,
        boolean l_blnIsSellOrder)
    {
        final String STR_METHOD_NAME = " getMiniStockOrderingQuantity(long, long, long, boolean)";
        log.entering(STR_METHOD_NAME);

        List l_list = null;
        try
        {
            String l_strWhere = 
                "(account_id = ?) and (sub_account_id = ?) and (product_id = ?) and (order_type = ?) and (order_open_status = ?)";
            String l_strIsSellOrder;
            if(l_blnIsSellOrder)
            {
                l_strIsSellOrder = "" + OrderTypeEnum.MINI_STOCK_SELL.intValue();
            }
            else
            {
                l_strIsSellOrder = "" + OrderTypeEnum.MINI_STOCK_BUY.intValue();
            }
            Object l_objWhere[] = 
            {
                new Long(l_lngMainAccountId),
                new Long(l_lngSubAccountId),
                new Long(l_lngProductId),
                l_strIsSellOrder,
                OrderOpenStatusEnum.OPEN
            };
            l_list = Processors.getDefaultProcessor().doFindAllQuery(
                EqtypeOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhere);
            
            int l_intLength =0;
            if (l_list != null)
            {
                l_intLength = l_list.size();
            }
            double l_dblTotal = 0;
            
            for (int i = 0; i < l_intLength; i++)
            {
                EqtypeOrderUnitParams l_eqtypeOrderUnitParams = 
                    new EqtypeOrderUnitParams((EqtypeOrderUnitRow)l_list.get(i));
                l_dblTotal = l_dblTotal + l_eqtypeOrderUnitParams.getQuantity();
            }
            
            return l_dblTotal;
        }
        catch(DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new RuntimeSystemException(
                "System exception while searching product with market id :",
                l_ex);
        }
    }
    /**
     * �iis����\�����P�ʁi�~�j���j�j�B<BR>
     * <BR>
     * �w��̒����P�ʂ�����\���𔻒肷��B<BR>                                                    
     * <BR>                                                                                          
     * �P�j�@@�������̃`�F�b�N                                                                              
     * �@@����������̔������Ɩ{�������̔��������Ⴄ�ꍇ��false�A<BR>
     *   �ȊO��true��ԋp����B<BR>                                    
     * <BR>                                                                                                                                                
     * �� ����������̔������Ɩ{�������̔��������Ⴄ�ꍇ�̔���  <BR>                                                                                              
     * ������ԊǗ�.get������() != �����P��.������ <BR>                                                                                                          
     *  <BR>                                                                                                                                                     
     * �Q�j�@@����ς݂̃`�F�b�N  <BR>                                                                                                                              
     * �@@�����P��.getOrderStatus() == OrderStatusEnum.CANCELLED <BR>                                                                                                                              
     * �̏ꍇ�A���Ɏ���ς݂̒����Ɣ��肵�A��O���X���[����B<BR> 
     * �@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:   BUSINESS_ERROR_00675
     * @@param l_orderUnit �i�����P�ʁj<BR>
     * �@@�@@�@@����������P�ʃI�u�W�F�N�g
     * @@return boolean 
     */
    public boolean isMiniStockCancelOrderUnit(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isMiniStockCancelOrderUnit(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_eqtypeOrderUnit = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        Date l_datBizDate = WEB3DateUtility.getDate(l_eqtypeOrderUnit.getBizDate(), "yyyyMMdd");

        if(WEB3DateUtility.compareToDay(WEB3GentradeTradingTimeManagement.getOrderBizDate(), l_datBizDate) != 0)
        {
            return false;
        }
        else
        {
            if(OrderStatusEnum.CANCELLED.equals(l_eqtypeOrderUnit.getOrderStatus()))
            {
                return false;
            }
            else
            {
                return true;
            }
        }

    }

    /**
     * �iget�l�i�����j�B<BR>
     * <BR>
     * �����̒l�i�����iSONAR�j���AWeb�V�ɂ�����l�i�������擾���ԋp����B<BR>
     * <BR>
     * �������̒l�i�����iSONAR�j��" "�i���p�u�����N(�w��Ȃ�)�j�̏ꍇ<BR>
     * �@@�@@"0"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i�����iSONAR�j��"A"�i���ݒl�w�l�j �̏ꍇ<BR>
     * �@@�@@"1"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i�����iSONAR�j��"B"�i�D��w�l�j �̏ꍇ<BR>
     * �@@�@@"3"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i�����iSONAR�j��"C"�i���c�w�l�j �̏ꍇ<BR>
     * �@@�@@"5"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i�����iSONAR�j��"D"�i���c����j �̏ꍇ<BR>
     * �@@�@@"7"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i�����iSONAR�j���i" ", "A", "B", "C", "D"�j�ȊO�̏ꍇ<BR>
     * �@@�@@��O��throw����B<BR>
     * �@@�@@�@@class: WEB3SystemLayerException<BR>
     * �@@�@@�@@tag:   SYSTEM_ERROR_80002
     * @@param l_strPriceConditionTypeSonar �i�l�i�����iSONAR�j�j<BR>
     * �@@�@@�@@SONAR�̒l�i�����B<BR>
     * <BR>
     * �@@�@@�@@��Web�V�ɂ�����l�i�����́A������̃R�[�h�̌n�Ɠ���ł���B<BR>
     * <BR>
     * �@@�@@�@@" "�i���p�u�����N�j�F�@@�w��Ȃ�<BR>
     * �@@�@@�@@"A"�F�@@���ݒl�w�l<BR>
     * �@@�@@�@@"B"�F�@@�D��w�l<BR>
     * �@@�@@�@@"C"�F�@@���c�w�l<BR>
     * �@@�@@�@@"D"�F�@@���c���<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
    public String getPriceConditionType(String l_strPriceConditionTypeSonar) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionType(String)";
        log.entering(STR_METHOD_NAME);

        String l_strPriceConditionType = null;

        if (WEB3PriceConditionSONARDef.DEFAULT.equals(l_strPriceConditionTypeSonar))
        {
            l_strPriceConditionType = WEB3PriceConditionDef.DEFAULT;
        }
        else if (WEB3PriceConditionSONARDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_strPriceConditionTypeSonar))
        {
            l_strPriceConditionType = WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER;
        }
        else if (WEB3PriceConditionSONARDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionTypeSonar))
        {
            l_strPriceConditionType = WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER;
        }
        else if (WEB3PriceConditionSONARDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionTypeSonar))
        {
            l_strPriceConditionType = WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER;
        }
        else if (WEB3PriceConditionSONARDef.PARTIALLY_CANCEL_ORDER.equals(l_strPriceConditionTypeSonar))
        {
            l_strPriceConditionType = WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER;
        }
        else
        {
            log.error("�s���ȃR�[�h�F[" + l_strPriceConditionTypeSonar + "]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionType;
    }

    /**
     * �iget�l�i�����iSONAR�j�j�B<BR>
     * <BR>
     * �����̒l�i�������ASONAR�̒l�i�������擾���ԋp����B<BR>
     * <BR>
     * �������̒l�i������"0"�iDEFAULT(�����w��Ȃ�)�j �̏ꍇ<BR>
     * �@@�@@" "�i���p�u�����N�j��Ԃ��B<BR>
     * <BR>
     * �������̒l�i������"1"�i���ݒl�w�l�����j �̏ꍇ<BR>
     * �@@�@@"A"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i������"3"�i�D��w�l�����j �̏ꍇ<BR>
     * �@@�@@"B"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i������"5"�i���s�c���w�l�����j �̏ꍇ<BR>
     * �@@�@@"C"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i������"7"�i���s�c����������j �̏ꍇ<BR>
     * �@@�@@"D"��Ԃ��B<BR>
     * <BR>
     * �������̒l�i�������i"0", "1", "3", "5", "7"�j�ȊO�̏ꍇ<BR>
     * �@@�@@��O��throw����B<BR>
     * �@@�@@�@@class: WEB3SystemLayerException<BR>
     * �@@�@@�@@tag:   SYSTEM_ERROR_80002
     * @@param l_strPriceConditionType �i�l�i�����j<BR>
     * <BR>
     * �@@�@@�@@Web�V�̒l�i�����B�i������̃R�[�h�̌n�Ɠ���j<BR>
     * <BR>
     * �@@�@@�@@0�F�@@DEFAULT(�����w��Ȃ�)<BR>
     * �@@�@@�@@1�F�@@���ݒl�w�l����<BR>
     * �@@�@@�@@3�F�@@�D��w�l����<BR>
     * �@@�@@�@@5�F�@@���s�c���w�l����<BR>
     * �@@�@@�@@7�F�@@���s�c���������<BR>
     * @@throws WEB3BaseException
     * @@return String
     */
    public String getPriceConditionTypeSonar(String l_strPriceConditionType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionTypeSonar(String)";
        log.entering(STR_METHOD_NAME);

        String l_strPriceConditionTypeSonar = null;

        if (WEB3PriceConditionDef.DEFAULT.equals(l_strPriceConditionType))
        {
            l_strPriceConditionTypeSonar = WEB3PriceConditionSONARDef.DEFAULT;
        }
        else if (WEB3PriceConditionDef.PRESENT_VALUE_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType))
        {
            l_strPriceConditionTypeSonar = WEB3PriceConditionSONARDef.PRESENT_VALUE_LIMIT_PRICE_ORDER;
        }
        else if (WEB3PriceConditionDef.PRIORITY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType))
        {
            l_strPriceConditionTypeSonar = WEB3PriceConditionSONARDef.PRIORITY_LIMIT_PRICE_ORDER;
        }
        else if (WEB3PriceConditionDef.PARTIALLY_LIMIT_PRICE_ORDER.equals(l_strPriceConditionType))
        {
            l_strPriceConditionTypeSonar = WEB3PriceConditionSONARDef.PARTIALLY_LIMIT_PRICE_ORDER;
        }
        else if (WEB3PriceConditionDef.PARTIALLY_CANCEL_ORDER.equals(l_strPriceConditionType))
        {
            l_strPriceConditionTypeSonar = WEB3PriceConditionSONARDef.PARTIALLY_CANCEL_ORDER;
        }
        else
        {
            log.error("�s���ȃR�[�h�F[" + l_strPriceConditionType + "]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionTypeSonar;
    }

    /**
     * �iget���s�����j�B<BR>
     * <BR>
     * �����̎��s�����iSONAR�j���AWeb�V�ɂ����鎷�s�������擾���ԋp����B<BR>
     * <BR>
     * �������̎��s�����iSONAR�j��null�̏ꍇ <BR>
     * �@@�@@null��Ԃ��B <BR>
     * <BR>
     * �������̎��s�����iSONAR�j��"1"�i�������j �̏ꍇ<BR>
     * �@@�@@EqTypeExecutionConditionType.NONE�i�����Ȃ��j��Ԃ��B<BR>
     * <BR>
     * �������̎��s�����iSONAR�j��"3"�i��t�j �̏ꍇ<BR>
     * �@@�@@EqTypeExecutionConditionType.AT_MARKET_OPEN�i���j��Ԃ��B<BR>
     * <BR>
     * �������̎��s�����iSONAR�j��"4"�i�����j �̏ꍇ<BR>
     * �@@�@@EqTypeExecutionConditionType.AT_MARKET_CLOSE�i�����j��Ԃ��B<BR>
     * <BR>
     * �������̎��s�����iSONAR�j��"7"�i�o�����Έ���(�s��)�j �̏ꍇ<BR>
     * �@@�@@EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED�i�s�o���������s�j<BR>
     * �@@�@@��Ԃ��B<BR>
     * <BR>
     * �������̎��s�����iSONAR�j����L�ȊO�̏ꍇ<BR>
     * �@@�@@��O��throw����B<BR>
     * �@@�@@�@@class: WEB3SystemLayerException<BR>
     * �@@�@@�@@tag:   SYSTEM_ERROR_80002
     * @@param l_strExecutionConditionTypeSonar (���s�����iSONAR�j)<BR>
     * �@@�@@�@@SONAR�̎��s�����B<BR>
     * <BR>
     * �@@�@@�@@"1"�F�@@������<BR>
     * �@@�@@�@@"3"�F�@@��t<BR>
     * �@@�@@�@@"4"�F�@@����<BR>
     * �@@�@@�@@"7"�F�@@�o�����Έ���(�s��)<BR>
     * <BR>
     * �@@�@@�@@��"2"�F�@@�o���@@�ɂ��ẮAPR�w����͎�̂��Ȃ����ƁA<BR>
     * �@@�@@�@@��SONAR���͒ʒm�L���[�ɂ��܂܂�Ȃ����Ƃ���A�ΏۊO�Ƃ���B
     * @@throws WEB3BaseException
     * @@return EqTypeExecutionConditionType
     */
    public EqTypeExecutionConditionType getExecutionConditionType(
        String l_strExecutionConditionTypeSonar)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionType(String)";
        log.entering(STR_METHOD_NAME);

        EqTypeExecutionConditionType l_executionConditionType = null;

        if (l_strExecutionConditionTypeSonar == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else if (WEB3SonarExecutionConditionDef.UNCONDITIONDNESS.equals(l_strExecutionConditionTypeSonar))
		{
			l_executionConditionType = EqTypeExecutionConditionType.NONE;
		}
		else if (WEB3SonarExecutionConditionDef.AT_MARKET_OPEN.equals(l_strExecutionConditionTypeSonar))
		{
			l_executionConditionType = EqTypeExecutionConditionType.AT_MARKET_OPEN;
		}
		else if (WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE.equals(l_strExecutionConditionTypeSonar))
		{
			l_executionConditionType = EqTypeExecutionConditionType.AT_MARKET_CLOSE;
		}
		else if (WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER.equals(l_strExecutionConditionTypeSonar))
		{
			l_executionConditionType = EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED;
		}
		else
		{
			log.error("�s���ȃR�[�h�F[" + l_strExecutionConditionTypeSonar + "]");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80002,
				this.getClass().getName() + STR_METHOD_NAME);
		}
       
        log.exiting(STR_METHOD_NAME);
        return l_executionConditionType;
    }

    /**
     * �iget���s�����iSONAR�j�j�B<BR>
     * <BR>
     * �����̒l�i�������ASONAR�̎��s�������擾���ԋp����B<BR>
     * <BR>
     * �������̎��s������EqTypeExecutionConditionType.NONE�i�����Ȃ��j �̏ꍇ<BR>
     * �@@�@@"1"�i�������j��Ԃ��B<BR>
     * <BR>
     * �������̎��s������EqTypeExecutionConditionType.AT_MARKET_OPEN�i���j �̏ꍇ<BR>
     * �@@�@@"3"�i��t�j��Ԃ��B<BR>
     * <BR>
     * �������̎��s������EqTypeExecutionConditionType.AT_MARKET_CLOSE�i�����j �̏ꍇ<BR>
     * �@@�@@"4"�i�����j��Ԃ��B<BR>
     * <BR>
     * �������̎��s������EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * �@@�i�s�o���������s�j �̏ꍇ<BR>
     * �@@�@@"7"�i�o�����Έ���(�s��)�j ��Ԃ��B<BR>
     * <BR>
     * �������̎��s��������L�ȊO�̏ꍇ<BR>
     * �@@�@@��O��throw����B<BR>
     * �@@�@@�@@class: WEB3SystemLayerException<BR>
     * �@@�@@�@@tag:   SYSTEM_ERROR_80002
     * @@param l_executionConditionType (���s����)<BR>
     * �@@�@@�@@xTrade�̎��s�����B<BR>
     * <BR>
     * �@@�@@�@@EqTypeExecutionConditionType.NONE�F�@@�����Ȃ�<BR>
     * �@@�@@�@@EqTypeExecutionConditionType.AT_MARKET_OPEN�F�@@���<BR>
     * �@@�@@�@@EqTypeExecutionConditionType.AT_MARKET_CLOSE�F�@@����<BR>
     * �@@�@@�@@EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED�F�@@�s�o���������s
     * @@throws WEB3BaseException
     * @@return EqTypeExecutionConditionType
     */
    public String getExecutionConditionTypeSonar(
        EqTypeExecutionConditionType l_executionConditionType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionConditionTypeSonar(EqTypeExecutionConditionType)";
        log.entering(STR_METHOD_NAME);

        String l_executionConditionTypeSonar = null;

        if (EqTypeExecutionConditionType.NONE.equals(l_executionConditionType))
        {
            l_executionConditionTypeSonar = WEB3SonarExecutionConditionDef.UNCONDITIONDNESS;
        }
        else if (EqTypeExecutionConditionType.AT_MARKET_OPEN.equals(l_executionConditionType))
        {
            l_executionConditionTypeSonar = WEB3SonarExecutionConditionDef.AT_MARKET_OPEN;
        }
        else if (EqTypeExecutionConditionType.AT_MARKET_CLOSE.equals(l_executionConditionType))
        {
            l_executionConditionTypeSonar = WEB3SonarExecutionConditionDef.AT_MARKET_CLOSE;
        }
        else if (EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_executionConditionType))
        {
            l_executionConditionTypeSonar = WEB3SonarExecutionConditionDef.NO_EXECUTED_MARKET_ORDER;
        }
        else
        {
            log.error("�s���ȃR�[�h�F[" + l_executionConditionType + "]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_executionConditionTypeSonar;
    }

    /**
     * �iis�C���T�C�_�[�x���\���j�B<BR>
     * <BR>
     * �C���T�C�_�[�`�F�b�N���s���B<BR>
     * �|"�x���̂�"�̏ꍇ��true��ԋp����B<BR>
     * �|�Y���f�[�^�Ȃ��A�܂���"�`�F�b�N���Ȃ�"�̏ꍇ��false��ԋp����B<BR>
     * �|"������~"�̏ꍇ�͗�O��throw����B<BR>
     * <BR>
     * �P�j�@@������.get������(�ڋq, ��������)�ɂ��A�����҃I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��get������( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�ڋq�F�@@�����̕⏕����.getMainAccount( )<BR>
     * �@@�@@�@@���������F�@@�g���v���_�N�g�}�l�[�W��.getProduct(�����̖���ID)��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�߂�l�̖����I�u�W�F�N�g<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�j�@@�擾���������҃I�u�W�F�N�g.�o�^�󋵋敪��"�x���̂�"�̏ꍇ�́Atrue��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@�擾���������҃I�u�W�F�N�g.�o�^�󋵋敪��"������~"�̏ꍇ�́A<BR>
     * �@@�@@�@@��O��throw����B<BR>
     * <BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�i�Y���f�[�^�Ȃ����܂ށj�́Afalse��ԋp����B
     * @@param l_subAccount �i�⏕�����j
     * @@param l_productId �i����ID�j
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isInsiderMessageSuspension(
        SubAccount l_subAccount,
        long l_lngProductId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isInsiderMessageSuspension(SubAccount, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@������.get������(�ڋq, ��������)�ɂ��A�����҃I�u�W�F�N�g���擾����B
        WEB3GentradeInsider l_insider = null;
        try
        {
            WEB3GentradeMainAccount l_account =
                new WEB3GentradeMainAccount((MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject());
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3EquityProductManager l_productManager =
                (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
            l_insider =
                WEB3GentradeInsider.getInsider(
                    l_account,
                    l_equityProduct);
        }
        catch (NotFoundException l_nexp)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_bexp)
        {
            // �Y���f�[�^�����̏ꍇ�́A���̂܂܃��^�[������B
            return false;
        }

        // �Q�j�@@�擾���������҃I�u�W�F�N�g.�o�^�󋵋敪��"�x���̂�"�̏ꍇ�́Atrue��ԋp����B
        // �@@�@@�@@�擾���������҃I�u�W�F�N�g.�o�^�󋵋敪��"������~"�̏ꍇ�́A
        // �@@�@@�@@��O��throw����B
        // �@@�@@�@@��L�ȊO�̏ꍇ�i�Y���f�[�^�Ȃ����܂ށj�́Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        InsiderRow l_insiderRow = (InsiderRow)l_insider.getDataSourceObject();
        if (WEB3InsiderRegistDivDef.WARNING.equals(l_insiderRow.getRegistDiv()))
        {
            return true;
        }
        else if (WEB3InsiderRegistDivDef.ORDER_STOP.equals(l_insiderRow.getRegistDiv()))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01356,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        else
        {
            return false;
        }
    }

    /**
     * �ivalidate�C���T�C�_�[�j�B<BR>
     * <BR>
     * �C���T�C�_�[�`�F�b�N���s���B<BR>
     * �i* ���������R���`�F�b�N.validate�C���T�C�_�[( )�ɈϏ�����B�j<BR>
     * <BR>
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����B
     * @@param l_eqtyprProduct �i���������j<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    public void validateInsider(
        SubAccount l_subAccount,
        EqTypeProduct l_eqtyprProduct)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.validate�C���T�C�_�[()�ɈϏ�����B
        l_orderMgrResVal.validateInsider(l_subAccount, l_eqtyprProduct);
    }

    /**
     * �ivalidate�ڋq�����ʎ����~�j�B<BR>
     * <BR>
     * �ڋq�����ʎ����~�`�F�b�N���s���B<BR>
     * �i* ���������R���`�F�b�N.validate�ڋq�����ʎ����~( )�ɈϏ�����B�j
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����B
     * @@param l_lngProductId �i����ID�j<BR>
     * �@@�@@�@@����ID�B
     * @@param l_orderType �i������ʁj<BR>
     * �@@�@@�@@������ʁB
     * @@throws WEB3BaseException
     */
    public void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.validate�ڋq�����ʎ����~()�ɈϏ�����B
        l_orderMgrResVal.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderType);
    }

    /**
     * �ivalidate����\������z�j�B<BR>
     * <BR>
     * �T�Z���z�l���A��ЁE���X�ň�x�Ɏ���\�ȏ�����z�𒴂��Ă��Ȃ����`�F�b�N���s���B<BR>
     * �i* ���������R���`�F�b�N.validate����\������z( )�ɈϏ�����B�j
     * @@param l_branch �i���X�j<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g
     * @@param l_market �i�s��j<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g
     * @@param l_dblRestraintTurnover �i�S����������j<BR>
     * �@@�@@�@@�S������������w�肷��B
     * @@param l_mainAccountTypeEnum �i�����^�C�v�j<BR>
     * �@@�@@�@@�����^�C�v�B
     * @@throws WEB3BaseException
     */
    public void validateMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover,
        MainAccountTypeEnum l_mainAccountTypeEnum)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.���������R���`�F�b�N.validate����\������z()�ɈϏ�����B
        l_orderMgrResVal.validateMaxHandlingPrice(l_branch, l_market, l_dblRestraintTurnover, l_mainAccountTypeEnum);
    }

    /**
     * �iget�����P���敪�ꗗ�j�B<BR>
     * �����P���敪�ꗗ���擾����B <BR>
     * <BR>
     * �P�j�@@�����̎������.�s��ID�ɊY������s��I�u�W�F�N�g.�s��R�[�h==JASDAQ�@@<BR>
     * �@@�@@�@@���@@�����̎������.�X�����J�敪==�}�[�P�b�g���C�N�̏ꍇ <BR>
     * <BR>
     *  �@@�P�F�w�l <BR>
     * �@@ �@@�@@��ԋp����B <BR>
     * <BR>
     * �Q�j�@@�����̎������.�s��ID�ɊY������s��I�u�W�F�N�g.isPTS�s��( )��true�̏ꍇ<BR>
     *  �@@�P�F�w�l<BR>
     * �@@ �@@�@@��ԋp����B<BR>
     * <BR>
     * �R�j�@@��L�ȊO�̏ꍇ<BR>
     * �@@�@@0�F���s <BR>
     * �@@�@@1�F�w�l <BR>
     * �@@�̔z���ԋp����B<BR>
     * @@param l_branch �i���X�j<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�B
     * @@param l_tradedProduct �i��������j<BR>
     * �@@�@@�@@��������B
     * @@return String[]
     */
    public String[] getOrderPriceDivs(
        Branch l_branch,
        EqTypeTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderPriceDivs(Branch, EqTypeTradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strResults = null;
        EqtypeTradedProductRow l_tradedProductRow = (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
        if (WEB3MarketCodeDef.JASDAQ.equals(l_tradedProduct.getMarket().getMarketCode()) &&
            WEB3OpenOtcDivDef.MARKET_MAKE_PRODUCT.equals(l_tradedProductRow.getOpenOtcDiv()))
        {
            //�P�F�w�l 
            l_strResults = new String[1];
            l_strResults[0] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //�Q�j�@@�����̎������.�s��ID�ɊY������s��I�u�W�F�N�g.isPTS�s��( )��true�̏ꍇ
        else if (((WEB3GentradeMarket)l_tradedProduct.getMarket()).isPTSMarket())
        {
            //�P�F�w�l 
            l_strResults = new String[1];
            l_strResults[0] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //�R�j�@@��L�ȊO�̏ꍇ
        else
        {
            // 0�F���s 
            // 1�F�w�l 
            l_strResults = new String[2];
            l_strResults[0] = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_strResults[1] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strResults;
    }

    /**
     * �ivalidate�戵�\�s��j�B<BR>
     * <BR>
     * ��Е��X�̎戵�\�s�ꂩ���`�F�b�N����B<BR>
     * �i* ���������R���`�F�b�N.validate�戵�\�s��( )�ɈϏ�����B�j
     * @@param l_branch �i���X�j<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�i�ڋq�̎���X�j�B
     * @@param l_tradedProduct �i��������j<BR>
     * �@@�@@�@@������������I�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    public void validateHandlingMarket(
        WEB3GentradeBranch l_branch,
        TradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.���������R���`�F�b�N.validate�戵�\�s��()�ɈϏ�����B
        l_orderMgrResVal.validateHandlingMarket(l_branch, l_tradedProduct);
    }

    /**
     * �iget�l������l�j�B<BR>
     * <BR>
     * �l������l���擾����B<BR>
     * <BR>
     * 1)�@@��l�擾<BR>
     * �|���������R���ʃ`�F�b�N.calc��l(�������)���R�[������B<BR>
     * �@@�������ݒ�<BR>
     * �@@�@@�E��������F�@@�����̎������<BR>
     * <BR>
     * 2)�@@�l���擾<BR>
     * �|���������R���ʃ`�F�b�N.calc�l��(�������,��l,���/�����敪)���R�[������B<BR>
     * �@@�������ݒ�<BR>
     * �@@�@@�E��������F�@@�����̎������<BR>
     * �@@�@@�E��l�F�@@1)calc��l�i�j�̖߂�l<BR>
     * �@@�@@�E���/�����敪�F�@@���<BR>
     * <BR>
     * 3)�@@�w�l�P�ʎ擾<BR>
     * �|�g���v���_�N�g�}�l�[�W��.get���ݒl�i�������,��l�j���R�[������B<BR>
     * �@@�������ݒ�<BR>
     * �@@�@@�E��������F�@@�����̎������<BR>
     * �@@�@@�E��l�F�@@1)calc��l�i�j�̖߂�l�@@�{�@@2)calc�l��()�̖߂�l<BR>
     * <BR>
     * 4)�@@�l������l�擾<BR>
     * �|���������R���ʃ`�F�b�N.calc�l�����(��l,�l��,�w�l�P��)���R�[������B<BR>
     * �@@�������ݒ�<BR>
     * �@@�@@�E��l�F�@@1)calc��l()�̖߂�l<BR>
     * �@@�@@�E�l���F�@@2)calc�l��()�̖߂�l<BR>
     * �@@�@@�E�w�l�P�ʁF�@@3)get���ݒl()�̖߂�l<BR>
     * <BR>
     * 5)�@@4)calc�l�����()�̖߂�l��ԋp����B
     * @@param l_tradedProduct �i��������j<BR>
     * �@@�@@�@@��������I�u�W�F�N�g
     * @@return double
     */
    public double getStopHighPrice(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopHighPrice(TradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        // 1)�@@��l�擾
        double l_dblBasePrice = l_orderMgrResVal.calcBasePrice(l_tradedProduct);
        
        // 2)�@@�l���擾
        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MAXIMUM);
        
        // 3)�@@�w�l�P�ʎ擾
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        double l_dblTickValue = l_productManager.getTickValue(
            l_tradedProduct,
            (l_dblBasePrice + l_dblPriceRange));
        
        // 4)�@@�l������l�擾
        double l_dblStopHighPrice = l_orderMgrResVal.calcStopHighPrice(
            l_dblBasePrice,
            l_dblPriceRange,
            l_dblTickValue);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblStopHighPrice;
    }

    /**
     * �iget�l�������l�j�B<BR>
     * <BR>
     * �l�������l���擾����B<BR>
     * <BR>
     * 1)�@@��l�擾<BR>
     * �|���������R���ʃ`�F�b�N.calc��l(�������)���R�[������B<BR>
     * �@@�������ݒ�<BR>
     * �@@�@@�E��������F�@@�����̎������<BR>
     * <BR>
     * 2)�@@�l���擾<BR>
     * �|���������R���ʃ`�F�b�N.calc�l��(�������,��l,���/�����敪)���R�[������B<BR>
     * �@@�������ݒ�<BR>
     * �@@�@@�E��������F�@@�����̎������<BR>
     * �@@�@@�E��l�F�@@1)calc��l�i�j�̖߂�l<BR>
     * �@@�@@�E���/�����敪�F�@@����<BR>
     * <BR>
     * 3)�@@�l�������l�擾<BR>
     * �@@3-1)�i��l�|�l���j��0�̏ꍇ�A1��ԋp����B<BR>
     * <BR>
     * �@@3-2)�@@3-1)�ȊO<BR>
     * �@@�@@�@@�@@�i��l�|�l���j��ԋp����B<BR>
     * <BR>
     * �@@����l�F�@@1)calc��l�i�j�̖߂�l<BR>
     * �@@���l���F�@@2)calc�l��()�̖߂�l<BR>
     * �@@�������_�ȉ��؂�̂ĂƂ���B
     * @@param l_tradedProduct �i��������j<BR>
     * �@@�@@�@@��������I�u�W�F�N�g
     * @@return double
     */
    public double getStopLowPrice(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopLowPrice(TradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        // 1)�@@��l�擾
        double l_dblBasePrice = l_orderMgrResVal.calcBasePrice(l_tradedProduct);
        
        // 2)�@@�l���擾
        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MINIMUM);
        
        // 3)�@@�l�������l�擾
        double l_dblStopLowPrice = Math.floor(l_dblBasePrice - l_dblPriceRange);
        if (l_dblStopLowPrice <= 0.0D)
        {
            l_dblStopLowPrice = 1.0D;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblStopLowPrice;
    }

    /**
     * �iis�����������j�B<BR>
     * <BR>
     * �������������������ł��邩�ǂ����̔�����s���B<BR>
     * �������������������̏ꍇ��true���A<BR>
     * ��L�ȊO�̏ꍇ��false��Ԃ��B<BR>
     * �i* ���������R���ʃ`�F�b�N.is����������( )�ɈϏ�����B�j
     * @@param String l_strOrderBizDate �i�������j<BR>
     * �@@�@@�@@�������B
     * @@param Timestamp l_tsYearlyBooksClosingDate �i�����m����j<BR>
     * �@@�@@�@@�����m������w�肷��B�ʏ�́A�y���������e�[�u���z���Z�����w�肳���B
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isDevidendRightDate(
    Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���ʃ`�F�b�N.is����������()�ɈϏ����� 
        return l_orderMgrResVal.isDevidendRightDate(
            l_tsOrderBizDate,
            l_tsYearlyBooksClosingDate);
    }

    /**
     * �ivalidate��������j�B<BR>
     * <BR>
     * �戵�\�`�F�b�N�����{����B<BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A��������I�u�W�F�N�g��ԋp����B<BR>
     * �i* ���������R���`�F�b�N.validate�������(��������, �s��)�ɈϏ�����B�j
     * @@param EqTypeProduct l_eqTypeProduct �i���������j<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@param Market l_market �i�s��j<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�B
     * @@return TradedProduct
     * @@throws WEB3BaseException
     */
    public TradedProduct validateTradedProduct(
        EqTypeProduct l_eqTypeProduct,
        Market l_market)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.validate�������(��������, �s��)�ɈϏ����� 
        return l_orderMgrResVal.validateTradedProduct(
            l_eqTypeProduct,
            l_market);
    }

    /**
     * �iget����O���������P�ʈꗗ�j�B<BR>
     * <BR>
     * �w������ɍ��v����A����O�����̒����P�ʃI�u�W�F�N�g�̈ꗗ���擾����B<BR>
     * <BR>
     * this.get�����P�ʈꗗ( )��delegate����B�i�߂�l�F�@@ArrayList�j<BR>
     * ----------------------------------------------------------<BR>
     * ��this.get�����P�ʈꗗ( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �⏕�����F�@@�����̕⏕����<BR>
     * �����^�C�v�F�@@"����"<BR>
     * ��������������F<BR>
     * �@@"����ID = ? and �s��ID = ? and ����R�[�h�iSONAR�j = "����O����" and ������ = ? and �����L����� = "�I�[�v��""<BR>
     * �@@������R�[�h�iSONAR�j�A�����L����Ԃɂ́A�Ӗ��ɊY������R�[�h�l���Z�b�g����B<BR>
     * ���������f�[�^�R���e�i�F�@@�ȉ��̒l��String[]�ɂ��ăZ�b�g�B<BR>
     * �@@����ID�F�@@�����̖���ID<BR>
     * �@@�s��ID�F�@@�����̎s��ID<BR>
     * �@@�������F�@@������ԊǗ�.get������(void)<BR>
     * �\�[�g�����F�@@null�i�w��Ȃ��j<BR>
     * ----------------------------------------------------------
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����B
     * @@param l_lngProductId �i����ID�j<BR>
     * �@@�@@�@@����ID�B
     * @@param l_lngMarketId �i�s��ID�j<BR>
     * �@@�@@�@@�s��ID�B
     * @@throws WEB3BaseException
     * @@return List<BR>
     * <BR>
     */
    public List getOffFloorOrderUnits(
        SubAccount l_subAccount,
        long l_lngProductId,
        long l_lngMarketId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOffFloorOrderUnits(SubAccount, long, long)";
        log.entering(STR_METHOD_NAME);
        
        String l_strSearchCond = "product_id = ? and market_id = ? and sonar_traded_code = '"
            + WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET + "' and biz_date = ? and order_open_status = "
            + OrderOpenStatusEnum.OPEN.intValue();
        Object[] l_searchCondContainers = new Object[3];
        l_searchCondContainers[0] = String.valueOf(l_lngProductId);
        l_searchCondContainers[1] = String.valueOf(l_lngMarketId);
        l_searchCondContainers[2] = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(
            WEB3GentradeTradingTimeManagement.getOrderBizDate());
        
        return this.getOrderUnits(
            new WEB3GentradeSubAccount((SubAccountRow)l_subAccount.getDataSourceObject()),
            ProductTypeEnum.EQUITY,
            l_strSearchCond,
            l_searchCondContainers,
            null);
    }

    /**
     * �ivalidate����O������t�\�j�B<BR>
     * <BR>
     * �w�肳�ꂽ����O���������������\���`�F�b�N����B<BR>
     * �i* ���������R���`�F�b�N.validate����O������t�\( )�ɈϏ�����B�j
     * @@param l_lngProductId �i����ID�j<BR>
     * �@@�@@�@@����ID�B
     * @@param l_lngMarketId �i�s��ID�j<BR>
     * �@@�@@�@@�s��ID�B
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����B
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams validateOffFloorOrderPossible(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.validate����O������t�\()�ɈϏ����� 
        return l_orderMgrResVal.validateOffFloorOrderPossible(
            l_lngProductId,
            l_lngMarketId,
            l_subAccount);
    }

    /**
     * �ivalidate����O�������������j�B<BR>
     * <BR>
     * �w�肳�ꂽ����O���������ɑ΂��A���ɒ������o�^�ς��ǂ������`�F�b�N����B<BR>
     * �i* ���������R���`�F�b�N.validate����O������������( )�ɈϏ�����B�j
     * @@param l_lngProductId �i����ID�j<BR>
     * �@@�@@�@@����ID�B
     * @@param l_lngMarketId �i�s��ID�j<BR>
     * �@@�@@�@@�s��ID�B
     * @@param l_subAccount �i�⏕�����j<BR>
     * �@@�@@�@@�⏕�����B
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public void validateOffFloorDuplicateOrder(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        // ���������R���ʃ`�F�b�N�I�u�W�F�N�g�̐���
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // ���������R���`�F�b�N.validate����O������������()�ɈϏ����� 
        l_orderMgrResVal.validateOffFloorDuplicateOrder(
            l_lngProductId,
            l_lngMarketId,
            l_subAccount);
    }
    
    /**
     * �ivalidate����O���������j�B<BR>
     * <BR>
     * ����O�����̎s��`�F�b�N�A�����`�F�b�N�A�\����������`�F�b�N���s���A<BR>
     * ��������I�u�W�F�N�g��ԋp����B<BR>
     * �@@�|�戵�\�s��`�F�b�N<BR>
     * �@@�|�����A�戵�s��`�F�b�N<BR>
     * �@@�|����\�����`�F�b�N<BR>
     * �@@�|�����P�ʃ`�F�b�N<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i����O���������jvalidate����O���������v�Q�ƁB
     * @@param l_strInstitutionCode �i�،���ЃR�[�h�j<BR>
     * �@@�@@�@@�،���ЃR�[�h�B
     * @@param l_strBranchCode �i���X�R�[�h�j<BR>
     * �@@�@@�@@���X�R�[�h�B<BR>
     * �@@�@@�@@�i�S���X�Ώۂ̏ꍇ��null���Z�b�g�j
     * @@param l_strProductCode �i�����R�[�h�j<BR>
     * �@@�@@�@@�����R�[�h�B
     * @@param l_strMarketCode �i�s��R�[�h�j<BR>
     * �@@�@@�@@�s��R�[�h�B
     * @@param l_strMaxApplyQuantity �i�\����������j<BR>
     * �@@�@@�@@�\����������B
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public WEB3EquityTradedProduct validateOffFloorOrderProduct(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strProductCode,
        String l_strMarketCode,
        String l_strMaxApplyQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOffFloorOrderProduct(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTradedProduct l_tradedProduct = null;
        
        //1.1. is�戵�\�s��()
        boolean l_isHandlingPossibleMarket =
            WEB3GentradeBranchMarketDealtCond.isHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                l_strBranchCode,
                l_strMarketCode);
        if (!l_isHandlingPossibleMarket)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00158,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.2. getInstance()
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        //1.3. validate�����R�[�h()
        WEB3EquityProduct l_product = 
            l_orderMgrResVal.validateProductCode(l_strProductCode, l_strInstitutionCode);
        
        //1.4. get�s��()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        Market l_market = null;
        try
        {
            l_market = l_finObjectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //1.5. validate�������()
        l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(l_product, l_market);
        
        //1.6. �\������������w�肳��Ă���ꍇ�i���\�����������null�j�̂ݎ��s
        if (l_strMaxApplyQuantity != null)
        {
            //1.6.1. checkLotSize()
            try
            {
                l_orderMgrResVal.checkLotSize(
                    l_tradedProduct.getLotSize(),
                    Double.parseDouble(l_strMaxApplyQuantity));
            }
            catch (OrderValidationException l_ove)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00708,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ove.getMessage(),
                    l_ove);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_tradedProduct;
    }
    
    /**
     * �iget�a����s�����i���t�j�j<BR>
     * <BR>
     * �����̎���]�͌��ʃI�u�W�F�N�g����A <BR>
     * ���t�^�������̗a����s�����̕������ҏW���Ԃ��B <BR>
     * <BR>
     * �P�j�@@�����̎���]�͌���.is����t���O( )==true�i�]�̓`�F�b�N����OK�j�̏ꍇ�� <BR>
     * �@@�@@�@@��O��throw����B <BR>
     * <BR>
     * �Q�j�@@�����̎���]�͌���.is����t���O( )==false�i�]�̓`�F�b�N����NG�j�̏ꍇ�́A <BR>
     * �@@�@@�@@�ȉ��̃t�H�[�}�b�g�ŗa����s�����̕������ҏW���A <BR>
     * �@@�@@�@@�쐬�����������return����B <BR>
     * <BR>
     * �@@�@@�@@�t�H�[�}�b�g�F�@@"%1,%2"�i�J���}��؂�j <BR>
     * �@@�@@�@@�@@�@@%1�F�@@�����̎���]�͌���.����]�̓G���[���.�������ϔ��t�\�z <BR>
     * �@@�@@�@@�@@�@@%2�F�@@�����̎���]�͌���.����]�̓G���[���.�a����s���z<BR>
     * <BR>
     * @@param l_tpTradingPowerResult �i����]�͌��ʁj<BR>
     * �@@�@@�@@����]�͌��ʃI�u�W�F�N�g�B<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     */ 
    public String getLackAccountBalanceInfoBuy(WEB3TPTradingPowerResult l_tpTradingPowerResult)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountBalanceShortageInfoBuy(WEB3TPTradingPowerResult)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j����t���O�`�F�b�N
        if (l_tpTradingPowerResult.isResultFlg() == true)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�a����s���͔������Ă��܂���B");
        }
        
        // �Q�j�a����s������ҏW
        String l_strLackAccountBalanceInfo = 
		WEB3StringTypeUtility.formatNumber(l_tpTradingPowerResult.getTpErrorInfo().buyOrderPossibleAmount) +
            "," +
		WEB3StringTypeUtility.formatNumber(l_tpTradingPowerResult.getTpErrorInfo().lackAccountBalance);
        
        log.exiting(STR_METHOD_NAME);
        return l_strLackAccountBalanceInfo;
    }
    
    /**
     * �iget�a����s�����i���t�j�j<BR>
     * <BR>
     * �����̎���]�͌��ʃI�u�W�F�N�g�A������������A <BR>
     * ���t�^���n���̗a����s�����̕������ҏW���Ԃ��B <BR>
     * <BR>
     * �P�j�@@�����̎���]�͌���.is����t���O( )==true�i�]�̓`�F�b�N����OK�j�̏ꍇ�� <BR>
     * �@@�@@�@@��O��throw����B <BR>
     * <BR>
     * �Q�j�@@�����̎���]�͌���.is����t���O( )==false�i�]�̓`�F�b�N����NG�j�̏ꍇ�́A <BR>
     * �@@�@@�@@�ȉ��̃t�H�[�}�b�g�ŗa����s�����̕������ҏW���A <BR>
     * �@@�@@�@@�쐬�����������return����B <BR>
     * <BR>
     * �@@�@@�@@�t�H�[�}�b�g�F�@@"%1,%2,%3"�i�J���}��؂�j <BR>
     * �@@�@@�@@�@@�@@%1�F�@@�����̎���]�͌���.����]�̓G���[���.�a����s���z <BR>
     * �@@�@@�@@�@@�@@%2�F�@@�����̎���]�͌���.����]�̓G���[���.�������ϔ��t�\���� <BR>
     * �@@�@@�@@�@@�@@%3�F�@@�����̒�������<BR>
     * <BR>
     * @@param l_tpTradingPowerResult �i����]�͌��ʁj<BR>
     * �@@�@@�@@����]�͌��ʃI�u�W�F�N�g�B<BR>
     * @@param l_dblOrderQuantity �i���������j<BR>
     * �@@�@@�@@���������B<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     */
    public String getLackAccountBalanceInfoSell(WEB3TPTradingPowerResult l_tpTradingPowerResult, double l_dblOrderQuantity)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountBalanceShortageInfoBuy(WEB3TPTradingPowerResult)";
        log.entering(STR_METHOD_NAME);
        
        // �P�j����t���O�`�F�b�N
        if (l_tpTradingPowerResult.isResultFlg() == true)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�a����s���͔������Ă��܂���B");
        }
        
        // �Q�j�a����s������ҏW
        String l_strLackAccountBalanceInfo = 
		WEB3StringTypeUtility.formatNumber(l_tpTradingPowerResult.getTpErrorInfo().lackAccountBalance) +
            "," +
		WEB3StringTypeUtility.formatNumber(l_tpTradingPowerResult.getTpErrorInfo().sellOrderPossibleQuantity) +
            "," +
		WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity);
        
        log.exiting(STR_METHOD_NAME);
        return l_strLackAccountBalanceInfo;
    }
    
    /**
     * �iget����萔�ʁj<BR>
     * <BR>
     * �w�肳�ꂽ�����P�ʃI�u�W�F�N�g�̖���萔�ʂ�Ԃ��B <BR>
     * <BR>
     * �����P��.�s�ꂩ��m�F�ς̐��� == null�i������t�O�j�̏ꍇ�F <BR>
     * �@@�@@�i�����P��.�������� �| �����P��.��萔�ʁj�̌��ʂ��A����萔�ʂƂ��ĕԂ��B <BR>
     * <BR>
     * �����P��.�s�ꂩ��m�F�ς̐��� != null�i������t��j�̏ꍇ�F <BR>
     * �@@�@@�i�����P��.�s�ꂩ��m�F�ς̐��� �| �����P��.��萔�ʁj�̌��ʂ��A����萔�ʂƂ��ĕԂ��B <BR>
     * <BR>
     * �������P��.��萔�� == null�̏ꍇ�A�����P��.��萔�� == 0�Ƃ��Čv�Z���s���B <BR>
     * <BR>
     * @@param l_eqtypeOrderUnit �i�����P�ʁj<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g�B<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getUnExecutedQuantity(EqTypeOrderUnit l_eqtypeOrderUnit)
    {
        final String STR_METHOD_NAME = "getUnExecutedQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
            
        // �����P��.�s�ꂩ��m�F�ς̐��� == null�̏ꍇ
        double l_dblUnExecutedQuantity = 0.0D;
        if (l_eqtypeOrderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            l_dblUnExecutedQuantity = l_eqtypeOrderUnitRow.getQuantity() - l_eqtypeOrderUnitRow.getExecutedQuantity();
        }
        // �����P��.�s�ꂩ��m�F�ς̐��� != null�̏ꍇ
        else
        {
            l_dblUnExecutedQuantity = l_eqtypeOrderUnitRow.getConfirmedQuantity() - l_eqtypeOrderUnitRow.getExecutedQuantity();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblUnExecutedQuantity;
    }
    
	/**
	 * �iget��������n����j<BR>
	 * <BR>
	 * �����Ŏw�肳�ꂽ�����P�ʂɑ΂��銔���ڋq���薾��.��n����̍��v�l���擾���A<BR>
	 * �������̒����P�ʃe�[�u���̊T�Z��n����ւ̐ݒ�p���z��Ԃ��B<BR>
	 * <BR>
	 * �P�j�@@�����̒����P��.�����J�e�S�� == "�V�K������"�̏ꍇ�́A �����P��.���v�����z��Ԃ��B <BR>
	 * <BR>
	 * �@@�@@�@@�����̒����P��.�����J�e�S�� != "�V�K������"�̏ꍇ�́A�ȉ��̏������s���B<BR>
	 * <BR>
	 * �Q�j�@@�g���g�����U�N�V�����}�l�[�W��.get��n������v(�����̒����P��)�ɂ��A <BR>
	 * �@@�@@�@@��n������v���擾����B <BR>
	 * <BR>
	 * �R�j�@@�����P�ʃe�[�u���̊T�Z��n����ւ̐ݒ�p���z���Z�o����B <BR>
	 * <BR>
	 * �R�|�P�j�@@�����̒����P��.������ʂ��ȉ��ɊY������ꍇ�́A <BR>
	 * �@@�@@�@@�@@�@@�P�j�Ŏ擾�����l�̕����𔽓]�����l��Ԃ��B�i��莞�ɕ��̒l��ݒ肵�Ă��邽�߁j <BR>
	 * <BR>
	 * �@@�@@�@@�@@�@@�@@�����������iEQUITY_BUY�j<BR>
	 * <BR>
	 * �R�|�Q�j�@@�����̒����P��.������ʂ��R�|�P�j�ȊO�̏ꍇ�́A<BR>
	 * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�����l�����̂܂ܕԂ��B<BR>
	 * <BR>
	 * @@param l_eqtypeOrderUnit �i�����P�ʁj<BR>
	 * �@@�@@�@@�����P�ʃI�u�W�F�N�g�B<BR>
	 * @@return double<BR>
	 * @@throws WEB3BaseException<BR>
	 */
	public double getEstimateDeliveryAmountForClose(EqTypeOrderUnit l_eqtypeOrderUnit) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getEstimateDeliveryAmountForClose(EqTypeOrderUnit)";
		log.entering(STR_METHOD_NAME);
		
		if (l_eqtypeOrderUnit.getOrderCateg().equals(OrderCategEnum.OPEN_MARGIN)== true)
		{
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
			return l_eqtypeOrderUnitRow.getExecutedAmount();
		}
		
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
		TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
		WEB3EquityFinTransactionManager l_finTransactionMgr
			= (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
		
		double l_dblDeliveryAmount = l_finTransactionMgr.getNetAmountTotal(l_eqtypeOrderUnit);
		
		if (l_eqtypeOrderUnit.getOrderType().equals(OrderTypeEnum.EQUITY_BUY))
		{
			l_dblDeliveryAmount = l_dblDeliveryAmount * (-1);
		}
        
		log.exiting(STR_METHOD_NAME);
		return l_dblDeliveryAmount;
	}

    /**
     * �iget��������n���(�����P��,�����ڋq���薾��)�j<BR>
     * <BR>
     * ����.�����ڋq���薾��.��n����̍��v�l���擾���A<BR>
     * �������̒����P�ʃe�[�u���̊T�Z��n����ւ̐ݒ�p���z��Ԃ��B<BR>
     * <BR>
     * �P�j�@@�����̒����P��.�����J�e�S�� == "�V�K������"�̏ꍇ�́A �����P��.���v�����z��Ԃ��B <BR>
     * <BR>
     * �@@�@@�@@�����̒����P��.�����J�e�S�� != "�V�K������"�̏ꍇ�́A�ȉ��̏������s���B<BR>
     * <BR>
     * �Q�j�@@���v���z�v�Z <BR>
     * �@@�@@�@@�g���g�����U�N�V�����}�l�[�W��.get��n����v�Z()�ɂČv�Z����B <BR>
     * �@@�@@�@@�����ݒ� <BR>
     * �@@�@@�@@�@@�����ڋq���薾�ׁF�@@����.�����ڋq���薾�� <BR>
     * <BR>
     * �R�j�@@�����P�ʃe�[�u���̊T�Z��n����ւ̐ݒ�p���z���Z�o����B <BR>
     * <BR>
     * �R�|�P�j�@@�����̒����P��.������ʂ��ȉ��ɊY������ꍇ�́A <BR>
     * �@@�@@�@@�@@�@@�P�j�Ŏ擾�����l�̕����𔽓]�����l��Ԃ��B�i��莞�ɕ��̒l��ݒ肵�Ă��邽�߁j <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�����������iEQUITY_BUY�j<BR>
     * <BR>
     * �R�|�Q�j�@@�����̒����P��.������ʂ��R�|�P�j�ȊO�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�Q�j�Ŏ擾�����l�����̂܂ܕԂ��B<BR>
     * <BR>
     * @@param l_eqtypeOrderUnit �i�����P�ʁj<BR>
     * �@@�@@�@@�����P�ʃI�u�W�F�N�g�B<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getEstimateDeliveryAmountForClose(EqTypeOrderUnit l_eqtypeOrderUnit,List l_lisTransactions) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getEstimateDeliveryAmountForClose(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_eqtypeOrderUnit.getOrderCateg().equals(OrderCategEnum.OPEN_MARGIN)== true)
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
                (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
            return l_eqtypeOrderUnitRow.getExecutedAmount();
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityFinTransactionManager l_finTransactionMgr
            = (WEB3EquityFinTransactionManager)l_tradingModule.getFinTransactionManager();
        
        double l_dblDeliveryAmount = l_finTransactionMgr.getNetAmountTotal(l_lisTransactions);
        
        if (l_eqtypeOrderUnit.getOrderType().equals(OrderTypeEnum.EQUITY_BUY))
        {
            l_dblDeliveryAmount = l_dblDeliveryAmount * (-1);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblDeliveryAmount;
    }

    
    /**
     * (update�����f�[�^)<BR>
     * �w�肳�ꂽ�����P�ʃI�u�W�F�N�g���g�p���AQueryProcessor�ɂ�蒍���f�[�^�ނ̍X�V���s���B<BR>
     * �|�����i�w�b�_�j�e�[�u��.�X�V���t��update<BR>
     * �|�����P�ʃe�[�u�����A�����̒����P�ʃI�u�W�F�N�g�̓��e��update<BR>
     * �|���������e�[�u���Ƀ��R�[�h��insert�i����.is�����쐬==true�̏ꍇ�̂݁j<BR>
     * <BR>
     * �P�j�@@�����i�w�b�_�j�e�[�u����update����B<BR>
     * <BR>
     * �@@�@@����ID==�����̒����P��.����ID �ɊY�����钍���i�w�b�_�j���R�[�h��update����B<BR>
     * <BR>
     * �@@�@@�X�V���t�ɁA�����̒����P�ʃI�u�W�F�N�g�̓����ڂ̓��e���Z�b�g����update����B<BR>
     * <BR>
     * �Q�j�@@�����P�ʃe�[�u����update����B<BR>
     * <BR>
     * �@@�@@�����̒����P�ʃI�u�W�F�N�g�̓��e��update����B<BR>
     * <BR>
     * �R�j�@@������is�����쐬==true�̏ꍇ�̂݁A�����̒����P�ʃI�u�W�F�N�g���g�p��<BR>
     * �@@�@@���������e�[�u���ւP���R�[�hinsert����B<BR>
     * <BR>
     * �@@�@@�R�|�P�j�@@����OK<BR>
     * �@@�@@�@@[�����̒����P��.������� == "�������i�V�K�����j" ����<BR>
     * �@@�@@�@@�@@�����̒����P��.���N�G�X�g�^�C�v == "�����T�[�o"�̏ꍇ]<BR>
     * �@@�@@�@@�@@DB�X�V�d�l<BR>
     * �@@�@@�@@�@@�u�t�w�l��������(OK)_�������������e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * @@param l_orderUnitRow - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@param l_blnIsCreateOrderAction - (is�����쐬)<BR>
     * ���������e�[�u���Ƀf�[�^��o�^���邩�ǂ����̃t���O�B<BR>
     * �itrue�F�o�^����Afalse�F�o�^���Ȃ��j
     * @@throws WEB3BaseException
     */
    public void updateOrderData(
        EqTypeOrderUnit l_orderUnit,
        boolean l_blnIsCreateOrderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderData(EqTypeOrderUnit, boolean)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "order_id=?";
            Object[] l_objWhere =
            {
                new Long(l_orderUnitRow.getOrderId())
            };
            HashMap l_map = new HashMap();
            l_map.put("last_updated_timestamp", l_orderUnitRow.getLastUpdatedTimestamp());
            l_processor.doUpdateAllQuery(
                EqtypeOrderRow.TYPE,
                l_strWhere,
                l_objWhere,
                l_map);
            
            l_processor.doUpdateQuery(l_orderUnitRow);
            
            if (l_blnIsCreateOrderAction)
            {
				EqtypeOrderActionParams l_orderActionParams =
					new EqtypeOrderActionParams();

                if (OrderStatusEnum.ORDERING.equals(l_orderUnitRow.getOrderStatus()) &&
                    WEB3RequestTypeDef.QUOTE_SERVER.equals(l_orderUnitRow.getRequestType()))
                {
					long l_lngOrderActionId = EqtypeOrderActionDao.newPkValue();
					Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
                    l_orderActionParams.setOrderActionId(l_lngOrderActionId);
                    l_orderActionParams.setAccountId(l_orderUnitRow.getAccountId());
                    l_orderActionParams.setSubAccountId(l_orderUnitRow.getSubAccountId());
                    if (!l_orderUnitRow.getTraderIdIsNull())
                    {
                        l_orderActionParams.setTraderId(l_orderUnitRow.getTraderId());
                    }
                    l_orderActionParams.setOrderId(l_orderUnitRow.getOrderId());
                    l_orderActionParams.setOrderUnitId(l_orderUnitRow.getOrderUnitId());
                    if (!l_orderUnitRow.getMarketIdIsNull())
                    {
                        l_orderActionParams.setMarketId(l_orderUnitRow.getMarketId());
                    }
                    l_orderActionParams.setOrderType(l_orderUnitRow.getOrderType());
                    l_orderActionParams.setOrderEventType(OrderEventTypeEnum.SEND_TO_MKT);
                    if (!l_orderUnitRow.getLimitPriceIsNull())
                    {
                        l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
                    }
                    l_orderActionParams.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType());
                    l_orderActionParams.setPriceConditionType(l_orderUnitRow.getPriceConditionType());
                    l_orderActionParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
                    l_orderActionParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
                    if (!l_orderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_orderActionParams.setStopOrderPrice(l_orderUnitRow.getStopOrderPrice());
                    }
                    if (!l_orderUnitRow.getWLimitPriceIsNull())
                    {
                        l_orderActionParams.setWLimitPrice(l_orderUnitRow.getWLimitPrice());
                    }
                    l_orderActionParams.setExpirationDate(l_orderUnitRow.getExpirationDate());
                    l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
                    if (!l_orderUnitRow.getConfirmedPriceIsNull())
                    {
                        l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
                    }
                    if (!l_orderUnitRow.getConfirmedQuantityIsNull())
                    {
                        l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
                    }
                    l_orderActionParams.setExecutedQuantity(null);
                    l_orderActionParams.setOrderStatus(l_orderUnitRow.getOrderStatus());
                    l_orderActionParams.setExpirationStatus(l_orderUnitRow.getExpirationStatus());
                    l_orderActionParams.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
                    l_orderActionParams.setExecutedPrice(null);
                    l_orderActionParams.setProductType(l_orderUnitRow.getProductType());
                    l_orderActionParams.setProductId(l_orderUnitRow.getProductId());
                    l_orderActionParams.setQuantityType(l_orderUnitRow.getQuantityType());
                    if (!l_orderUnitRow.getEstimatedPriceIsNull())
                    {
                        l_orderActionParams.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
                    }
                    l_orderActionParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());
                    l_orderActionParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
                    l_orderActionParams.setClosingOrderType(l_orderUnitRow.getClosingOrderType());
                    l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
                    l_orderActionParams.setRequestType(l_orderUnitRow.getRequestType());
                    l_orderActionParams.setCreatedTimestamp(l_tsSysTime);
                    l_orderActionParams.setLastUpdatedTimestamp(l_tsSysTime);

					l_processor.doInsertQuery(l_orderActionParams);
                }
            }
        }
        catch (DataException l_de)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate��������i�M�p�j)<BR>
     * �戵�\�`�F�b�N�A�y�ѕٍϋ敪�ʂ̔�����~�`�F�b�N�i�M�p�j�����{����B<BR>
     * �`�F�b�N���ʂ�OK�̏ꍇ�́A��������I�u�W�F�N�g��ԋp����B<BR>
     * �i* ���������R���`�F�b�N.validate��������i�M�p�j(�����V�O�l�`���c)�ɈϏ�����B�j<BR>
     * @@param l_subAccount (�⏕����)<BR>
     * �@@�@@�@@�⏕�����I�u�W�F�N�g�B
     * @@param l_product (��������)<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�B
     * @@param l_market (�s��)<BR>
     * �@@�@@�@@�s��I�u�W�F�N�g�B
     * @@param l_branch (���X)<BR>
     * �@@�@@�@@���X�I�u�W�F�N�g�B
     * @@param l_strRepaymentType (�ٍϋ敪)<BR>
     * �@@�@@�@@�ٍϋ敪�B<BR>
     * �@@�@@�@@0�FDEFAULT�i�w��Ȃ��j<BR>
     * �@@�@@�@@1�F���x�M�p<BR>
     * �@@�@@�@@2�F��ʐM�p
     * @@param l_orderCateg (�����J�e�S��)<BR>
     * �@@�@@�@@�����J�e�S���B�ixTrade��OrderCategEnum�ɂĒ�`�j
     * @@param l_isShort (is����)<BR>
     * �@@�@@�@@�����^�����̃t���O�B<BR>
     * �@@�@@�@@�����������̏ꍇtrue�A�����̏ꍇfalse���w�肷��B
     * @@param l_isTradeStopCheck (is������~�`�F�b�N)<BR>
     * �@@�@@�@@������~�`�F�b�N���{�L���t���O�B<BR>
     * �@@�@@�@@�itrue�F�`�F�b�N����Afalse�F�`�F�b�N���Ȃ��j
     * @@return WEB3EquityTradedProduct
     * @@throws WEB3BaseException
     */
    public WEB3EquityTradedProduct validateTradedProductForMarginTrading(
        SubAccount l_subAccount,
        WEB3EquityProduct l_product, 
        WEB3GentradeMarket l_market, 
        WEB3GentradeBranch l_branch, 
        String l_strRepaymentType, 
        OrderCategEnum l_orderCateg, 
        boolean l_isShort,
        boolean l_isTradeStopCheck)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTradedProductForMarginTrading(SubAccount, WEB3EquityProduct, WEB3GentradeMarket, WEB3GentradeBranch, String, OrderCategEnum, boolean, boolean)"; 
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        log.exiting(STR_METHOD_NAME);
        return l_orderManagerReusableValidations.validateTradedProductForMarginTrading(
            l_subAccount,
            l_product,
            l_market,
            l_branch,
            l_strRepaymentType,
            l_orderCateg,
            l_isShort,
            l_isTradeStopCheck);
    }
    
    /**
     * (validate������������)<BR>
     * �������͓��e�̃`�F�b�N�����{����B<BR>
     * �ivalidateNewCashBasedOrder�j<BR>
     * <BR>
     * this.validate������������(�⏕����, �����������e, is�A�����Δ���)��delegate����B<BR>
     * ��is�A�����Δ����ɂ́Afalse�i�����Δ����ł͂Ȃ��j���w�肷��B<BR>
     * @@param l_subAccount (�⏕����)
     * @@param l_eqNewCashBasedOrderSpec (�����������e)
     * @@return EqTypeNewOrderValidationResult
     */
    public EqTypeNewOrderValidationResult validateNewCashBasedOrder(
        SubAccount l_subAccount,
        EqTypeNewCashBasedOrderSpec l_eqNewCashBasedOrderSpec)
    {
        String STR_METHOD_NAME =
            "validateNewCashBasedOrder(SubAccount, EqTypeNewCashBasedOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeNewOrderValidationResult l_result =
            this.validateNewCashBasedOrder(
                l_subAccount,
                l_eqNewCashBasedOrderSpec,
                false);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (validate�ԍϒ���)<BR>
     * ��ԍϒ����̔����R�����s���B<BR>
     * �ivalidateSettleContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.validate�ԍϒ���()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�ԍϒ���()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�M�p�ԍϒ������e�F�@@�p�����[�^.�M�p�ԍϒ������e<BR>
     * �@@�����F�@@null<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_settleContractOrderSpec - (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B
     * @@return EqTypeNewOrderValidationResult
     */
    public EqTypeNewOrderValidationResult validateSettleContractOrder(
        SubAccount l_subAccount, 
        EqTypeSettleContractOrderSpec l_settleContractOrderSpec) 
    {
        String STR_METHOD_NAME = 
            "validateSettleContractOrder(WEB3GentradeSubAccount, EqTypeSettleContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeNewOrderValidationResult l_result =
            this.validateSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (calc�T�Z���ϑ��v���)<BR>
     * �T�Z���ϑ��v������Z�o���ĕԋp����B<BR>
     * <BR>
     * this.calc�T�Z���ϑ��v���()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [calc�T�Z���ϑ��v���()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�萔���F�@@�p�����[�^�̓�����<BR>
     * �@@�w�l�F�@@�p�����[�^�̓�����<BR>
     * �@@�⏕�����F�@@�p�����[�^�̓�����<BR>
     * �@@��������F�@@�p�����[�^�̓�����<BR>
     * �@@���ό����G���g���F �@@�p�����[�^�̓�����<BR>
     * �@@���ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�����P�ʁF�@@�p�����[�^�̓�����<BR>
     * �@@�����萔�ʁF�@@�p�����[�^�̓�����<BR>
     * �@@������P���F�@@�p�����[�^�̓�����<BR>
     * �@@isSkip���z�`�F�b�N�F�@@�p�����[�^�̓�����<BR>
     * �@@�����F�@@null<BR>
     * @@param l_genCommission - (�萔��)<BR>
     * �萔���I�u�W�F�N�g�B
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l�B<BR>
     * ���s�̏ꍇ��0���Z�b�g�B
     * @@param l_genSubAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g�B
     * @@param l_equityTradedProduct - (�������)<BR>
     * ��������I�u�W�F�N�g�B
     * @@param l_settleContractOrderEntrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B
     * @@param l_dblQuantity - (����)<BR>
     * ���ʁB
     * @@param l_orderUnit - (�����P��)<BR>
     * �������^���Ώہ^������Ώے����̒����P�ʃI�u�W�F�N�g<BR>
     * �i�V�K�̒����o�^����null���Z�b�g�j
     * @@param l_dblNowExecQuantity - (�����萔��)<BR>
     * �����萔��<BR>
     * �i���^������̏ꍇ�ɕҏW�j
     * @@param l_dblNowExecPrice - (������P��)<BR>
     * ������P��<BR>
     * �i���^������̏ꍇ�ɕҏW�j
     * @@param l_isSkipAmountRange - (isSkip���z�`�F�b�N)<BR>
     * �v�Z���ʂ̑���ɂ��āA�Ó����`�F�b�N���X�L�b�v���邩�̃t���O�B<BR>
     * <BR>
     * �`�F�b�N���s���ꍇ��false�A�`�F�b�N���s��Ȃ��i�X�L�b�v����j�ꍇ��true���w�肷��B
     * @@return WEB3EquityRealizedProfitAndLossPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityRealizedProfitAndLossPrice calcEstimatedRealizedProfitAndLossAmount(
        WEB3GentradeCommission l_genCommission,
        double l_dblLimitPrice,
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3EquityTradedProduct l_equityTradedProduct,
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblQuantity,
        EqTypeOrderUnit l_orderUnit,
        double l_dblNowExecQuantity,
        double l_dblNowExecPrice,
        boolean l_isSkipAmountRange)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcEstimatedRealizedProfitAndLossAmount(WEB3GentradeCommission, " +
            "double, WEB3GentradeSubAccount, WEB3EquityTradedProduct, " +
            "EqTypeSettleContractOrderEntry[], double, EqTypeOrderUnit, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityRealizedProfitAndLossPrice l_realizedProfitAndLossPrice =
            this.calcEstimatedRealizedProfitAndLossAmount(
                l_genCommission,
                l_dblLimitPrice,
                l_genSubAccount,
                l_equityTradedProduct,
                l_settleContractOrderEntrys,
                l_dblQuantity,
                l_orderUnit,
                l_dblNowExecQuantity,
                l_dblNowExecPrice,
                l_isSkipAmountRange,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_realizedProfitAndLossPrice;
    }
    
    /**
     * (validate�������n����)<BR>
     * �茻�����n�����̔����R�����s���B<BR>
     * �ivalidateSwapContractOrder�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * this.validate�������n����()�ɏ������Ϗ��idelegate�j����B<BR>
     * <BR>
     * [validate�������n����()�Ɏw�肷�����]<BR>
     * �@@�⏕�����F�@@�p�����[�^.�⏕����<BR>
     * �@@�M�p�������n�������e�F�@@�p�����[�^.�M�p�������n�������e<BR>
     * �@@�����F�@@null<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g
     * @@param l_swapContractOrderSpec - (�M�p�������n�������e)<BR>
     * �M�p�������n�������e�I�u�W�F�N�g�B
     * @@return EqTypeNewOrderValidationResult
     */
    public EqTypeNewOrderValidationResult validateSwapContractOrder(
        SubAccount l_subAccount, 
        EqTypeSwapContractOrderSpec l_swapContractOrderSpec)
    {
        String STR_METHOD_NAME = 
            "validateSwapContractOrder(WEB3GentradeSubAccount, EqTypeSwapContractOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeNewOrderValidationResult l_result =
            this.validateSwapContractOrder(
                l_subAccount,
                l_swapContractOrderSpec,
                null);
        
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }
    
    /**
     * (calc�T�Z��n����i�������n�j)<BR>
     * @@param l_settleContractOrderEntrys - (���ό����G���g��)<BR>
     * ���ό����G���g���̔z��B
     * @@param l_dblQuantity - (����)<BR>
     * ���ʁB
     * @@param l_eqtypeOrderUnit - (�����P��)<BR>
     * �������^���Ώہ^������Ώے����̒����P�ʃI�u�W�F�N�g<BR>
     * �i�V�K�̒����o�^����null���Z�b�g�j
     * @@return double
     * @@throws WEB3BaseException
     */
    public double calcEstimatedSwapPrice(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblQuantity,
        EqTypeOrderUnit l_eqtypeOrderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "calcEstimatedSwapPrice(EqTypeSettleContractOrderEntry[], double, EqtypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblEstimatedSwapPrice =
            this.calcEstimatedSwapPrice(
                l_settleContractOrderEntrys,
                l_dblQuantity,
                l_eqtypeOrderUnit,
                null);
                
        log.exiting(STR_METHOD_NAME);
        return l_dblEstimatedSwapPrice;
    }
    
    /**
     * (is�\�񒍕��m�F�v)<BR>
     * �����Ŏw�肳�ꂽ�����ɗ\�񒍕����ݒ肳��Ă���\�������邩<BR>
     * �i���e�����̉\�������邩�j�𔻒肷��B<BR>
     * <BR>
     * �\�񒍕����ݒ肳��Ă���\��������ꍇtrue���A<BR>
     * �Ȃ��ꍇfalse���A���ꂼ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�\�񒍕��m�F�v�ۂ̔���@@<BR>
     * �����̒����P��.�\�񒍕��ݒ�t���O �� "1�F�ݒ�̉\������"�̏ꍇ�́A<BR>
     * �\�񒍕��̐ݒ�Ȃ��̂��߁Afalse��ԋp����B�ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@return boolean
     */
    public boolean isReserveOrderConfirmRequire(EqTypeOrderUnit l_orderUnit)
    {
        String STR_METHOD_NAME = 
            "isReserveOrderConfirmRequire(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsReserveOrderConfirmRequire = true;
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        if (!WEB3ReserveOrderExistFlagDef.SET_POSSIBLE.equals(
                l_orderUnitRow.getReserveOrderExistFlag()))
        {
            l_blnIsReserveOrderConfirmRequire = false;
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnIsReserveOrderConfirmRequire;
    }
    
    /**
     * (insert�������������L���[)<BR>
     * ������������L���[�e�[�u���Ɍ�������������<BR>
     * �f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.getOrderUnits(����ID)�ɂ�<BR>
     * �@@�����P�ʂ��擾����B<BR>
     * �@@���߂�l��0�Ԗڂ̗v�f�𒍕��P�ʂƂ��Ďg�p����B<BR>
     * <BR>
     * �Q�j�@@������������L���[�Ƀf�[�^��o�^����B<BR>
     * �@@�����P��.getSide() == SideEnum.BUY�̏ꍇ�A<BR>
     * �@@�@@DB�X�V�d�l<BR>
     * �@@�@@�u�����������t_������������L���[�e�[�u��.xls�v�Q��<BR>
     * �@@�ȊO�A<BR>
     * �@@�@@DB�X�V�d�l<BR>
     * �@@�@@�u�����������t_������������L���[�e�[�u��.xls�v�Q��<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B
     * @@throws WEB3BaseException
     */
    public void insertEquityHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertEquityHostOrder(long)";
        log.entering(STR_METHOD_NAME);
        
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_eqtypeOderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        try
        {
            //���������L���[�e�[�u���փf�[�^����
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                new HostEqtypeOrderAllParams();

            //(1) set�f�[�^�R�[�h �F �hAI801�h
            l_hostEqtypeOrderAllParams.setRequestCode(
                WEB3HostRequestCodeDef.EQUITY_ORDER);

            //���X���擾����
            Branch l_banch =
                l_accountManager.getBranch(
                    l_eqtypeOderUnitRow.getBranchId());
            //�،���ЃR�[�h���擾����
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
                    
            //�ڋqID
            l_hostEqtypeOrderAllParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());

            //(2) set�،���ЃR�[�h�F���������P��.���X�h�c�ɊY������،���ЃR�[�h
            l_hostEqtypeOrderAllParams.setInstitutionCode(
                l_strInstitutionCode);

            //���X�R�[�h���擾����
            String l_strBranchCode = l_banch.getBranchCode();

            //(3) set���X�R�[�h �F ���������P��.���X�h�c�ɊY�����镔�X�R�[�h
            l_hostEqtypeOrderAllParams.setBranchCode(l_strBranchCode);

            //�ڋq�R�[�h���擾����
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(l_eqtypeOderUnitRow.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            String l_strAccountCode = l_mainAccount.getAccountCode();

            //(4) set�ڋq�R�[�h �F ���������P��.�����h�c�ɊY����������R�[�h
            l_hostEqtypeOrderAllParams.setAccountCode(l_strAccountCode);

            //(5) set���҃R�[�h�iSONAR�j �F ���������P��.���҃R�[�h�iSONAR�j
            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                l_eqtypeOderUnitRow.getSonarTraderCode());

            //(6) set���ʃR�[�h �F ���������P��.���ʃR�[�h
            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                l_eqtypeOderUnitRow.getOrderRequestNumber());
                    
            // set��������ԍ� �F ���������P��.���������ŏI�ʔ�
            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(l_eqtypeOderUnitRow.getLastOrderActionSerialNo());

            //(7) set�����R�[�h �F ���������P��.�����h�c�ɊY����������R�[�h
            EqTypeProduct l_product = (EqTypeProduct)l_orderUnit.getProduct();
            String l_strProductCode = l_product.getProductCode();
            l_hostEqtypeOrderAllParams.setProductCode(l_strProductCode);

            //���t/���t���ʂ��Z�b�g
            double l_dblOrderQuantity = l_orderUnit.getQuantity();
            if(SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                //���t�̏ꍇ
                log.debug(" ===>���t�̏ꍇ");

                //(8) ���t���� �F 0
                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0);
                //(9) ���t���� �F ���������P��.��������
                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                    l_dblOrderQuantity);
                //(20) ���n�v�ŋ敪 �F 0�F�Ȃ�
                l_hostEqtypeOrderAllParams.setCapitalGainTaxType(
                    WEB3CapitalGainTaxTypeDef.NOTHING);
            }
            else if (
                l_eqtypeOderUnitRow.getOrderType()
                    == OrderTypeEnum.EQUITY_SELL)
            {
                //���t�̏ꍇ
                log.debug(" ===>���t�̏ꍇ");

                //(8) ���t���� �F ���������P��.��������
                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                    l_dblOrderQuantity);
                //(9) ���t���� �F 0
                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0);
                        
				//(20) ���n�v�ŋ敪 �F
				// �l�q�i�ڋq.�����^�C�v==("�l�A�J�E���g", "���p�A�J�E���g")�j�ł��A
				//�@@�@@���Z�ҁA���ʔ񋏏Z�҂̏ꍇ�F�@@1�F�\��
				//�@@�@@�񋏏Z�҂̏ꍇ�F�@@�@@�@@�@@�@@�@@�@@0�F�Ȃ�
				// �@@�l�q�i�ڋq.�����^�C�v=="�@@�l�A�J�E���g"�j�̏ꍇ�F�@@0�F�Ȃ�
				if (
					MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountRow.getAccountType()) ||
					MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountRow.getAccountType()))
				{
					if (WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()) ||
						WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()))
					{
						l_hostEqtypeOrderAllParams.setCapitalGainTaxType(
							WEB3CapitalGainTaxTypeDef.REPORT);
					}
					else if (WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
					{
						l_hostEqtypeOrderAllParams.setCapitalGainTaxType(
							WEB3CapitalGainTaxTypeDef.NOTHING);
					}
				}
                else if(
                    MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
                {
                    l_hostEqtypeOrderAllParams.setCapitalGainTaxType(
                        WEB3CapitalGainTaxTypeDef.NOTHING);
                }
            }

            //�w�l���擾
            double l_dblLimitPrice = l_orderUnit.getLimitPrice();

            //(10) set�w�l �F ���������P��.�w�l
            l_hostEqtypeOrderAllParams.setLimitPrice(l_dblLimitPrice);

            //���s�������擾
            String l_strExecutionConditionSonar = null;
            if (this.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
            {
                l_strExecutionConditionSonar =
                    WEB3ExecutionConditionDef.COME_TO_TERMS;
            }
            else
            {
                l_strExecutionConditionSonar = 
                    this.getExecutionConditionTypeSonar(
                        l_eqtypeOderUnitRow.getExecutionConditionType());
            }

            //(11) set���s����
            l_hostEqtypeOrderAllParams.setExecutionCondition(
                l_strExecutionConditionSonar);

            //�l�i�������擾
            String l_strPriceConditionTypeSonar =
                this.getPriceConditionTypeSonar(
                    l_eqtypeOderUnitRow.getPriceConditionType());

            //(12) set�l�i����
            l_hostEqtypeOrderAllParams.setPriceConditionType(
                l_strPriceConditionTypeSonar);

            //(13) set����R�[�h�iSONAR�j �F ���������P��.����R�[�h�iSONAR�j
            l_hostEqtypeOrderAllParams.setSonarTradedCode(
                l_eqtypeOderUnitRow.getSonarTradedCode());

            //(14) set�ٍϋ敪�F�O�FDEFAULT�i�����j
            l_hostEqtypeOrderAllParams.setSonarRepaymentType(
                WEB3GentradeRepaymentDivDef.DEFAULT);

            //(15) set�s��R�[�h�iSONAR�j �F ���������P��.�s��R�[�h�iSONAR�j
            l_hostEqtypeOrderAllParams.setSonarMarketCode(
                l_eqtypeOderUnitRow.getSonarMarketCode());

            //(16) set�`�[�� �F ���������P��.�`�[No.
            l_hostEqtypeOrderAllParams.setTicketNumber(
                l_eqtypeOderUnitRow.getVoucherNo());

            //(17) set�󒍓��� �F �����P��.�󒍓���
            Timestamp l_tsReceivedDateTime = l_eqtypeOderUnitRow.getReceivedDateTime();
            l_hostEqtypeOrderAllParams.setReceivedDateTime(
                l_tsReceivedDateTime);

            //(18) set�󒍓��敪
            //(���������P��.������==�󒍓��i�󒍓����̓��t�����j�̏ꍇ�́@@0�F�����B�ȊO�A1�F�O���B)
            String l_strReceivedDateTime =
                WEB3DateUtility.formatDate(
                    l_tsReceivedDateTime,
                    "yyyyMMdd");
            if (l_strReceivedDateTime
                .equals(l_eqtypeOderUnitRow.getBizDate()))
            {
                l_hostEqtypeOrderAllParams.setReceivedDateTimeDiv(
                    WEB3OrderDateDivDef.TODAY);
            }
            else
            {
                l_hostEqtypeOrderAllParams.setReceivedDateTimeDiv(
                    WEB3OrderDateDivDef.YESTERDAY);
            }

            //(19) set�ŋ敪�i��������敪�j
            if (TaxTypeEnum.NORMAL.equals(l_orderUnit.getTaxType()))
            {
                log.debug("�ŋ敪 ===>��ʂ̏ꍇ��");
                //�h��ʁh�̏ꍇ��0
                l_hostEqtypeOrderAllParams.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else if (TaxTypeEnum.STOCK_OPTION.equals(l_orderUnit.getTaxType())
                && OrderTypeEnum.EQUITY_SELL.equals(l_eqtypeOderUnitRow.getOrderType()))
            {
                //�h�X�g�b�N�I�v�V�����h�̏ꍇ�́u5�F�X�g�b�N�I�v�V�����v
                log.debug("�ŋ敪 ===>�X�g�b�N�I�v�V�����̏ꍇ��");
                l_hostEqtypeOrderAllParams.setTaxType(WEB3TaxTypeDef.STOCK_OPTION);
            }
            else
            {
                log.debug("�ŋ敪 ===>����A������������򒥎��̏ꍇ");
                //�h����h�A�h������������򒥎��h�̏ꍇ1
                l_hostEqtypeOrderAllParams.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }

            //(21) set���� �F 1�F�a��`�F�b�N�v
            l_hostEqtypeOrderAllParams.setCheckType(
                WEB3CheckTypeDef.PRE_CHECK);

            //(22) set�����`���l�� �F ���������P��.���񒍕��̒����`���l��
            l_hostEqtypeOrderAllParams.setOrderChanel(
                l_eqtypeOderUnitRow.getOrderChanel());

            //(23) set�t�@@�N�^�[ �F �u�����N�F����
            String l_strFactor = " ";
            l_hostEqtypeOrderAllParams.setFactor(l_strFactor);

            //(24) set�萔���� �F ���������P��.�萔��No.
            l_hostEqtypeOrderAllParams.setCommisionNumber(
                l_eqtypeOderUnitRow.getCommTblNo());

            //(25) set�萔�����}�� �F ���������P��.�萔��No.�}��
            l_hostEqtypeOrderAllParams.setCommisionBranchNumber(
                l_eqtypeOderUnitRow.getCommTblSubNo());

            //(26) �萔�����i�R�[�h �F ���������P��.�萔�����i�R�[�h
            l_hostEqtypeOrderAllParams.setCommisionProductCode(
                l_eqtypeOderUnitRow.getCommProductCode());

            //(27) set�󔄃t���O �F �u�����N�F�ΏۊO
            l_hostEqtypeOrderAllParams.setShortSellOrderFlag(
                WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);

            //(28) �����敪 �F 0�F������
            l_hostEqtypeOrderAllParams.setStatus(
                WEB3FrontOrderStatusDef.NOT_DEAL);

            // �����o�H�敪
            String l_strSubmitOrderRouteDiv =
                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv();
            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
                    
            // ����敪
            l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
                    
            // �t�����g����������敪�R�[�h
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                    
            // �t�����g�����V�X�e���敪
            EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            EqTypeTradedProduct l_tradedProduct =
                (EqTypeTradedProduct)l_eqtypeOrderUnit.getTradedProduct();
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            String l_strFrontOrderSystemCode =
                l_frontOrderService.getFrontOrderSystemCode(
                    l_strMarketCode,
                    l_tradedProductRow.getOpenOtcDiv());
            l_hostEqtypeOrderAllParams.setFrontOrderSystemCode(
                l_strFrontOrderSystemCode);
                    
            // �t�����g��������敪�R�[�h
            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    
            // ���Ȉϑ��敪
            l_hostEqtypeOrderAllParams.setTradeauditCode(
                WEB3TradeauditCodeDef.COMMISSION);
                    
            // �Г���������
            l_hostEqtypeOrderAllParams.setCorpCode(
                l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                    
            // �S���������敪
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    
            //�f�[�^�}������
            Processors.getDefaultProcessor().doInsertQuery(
                l_hostEqtypeOrderAllParams);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insert�M�p�V�K�������L���[)<BR>
     * ������������L���[�e�[�u���Ɍ�������������<BR>
     * �f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.getOrderUnits(����ID)�ɂ�<BR>
     * �@@�����P�ʂ��擾����B<BR>
     * �@@���߂�l��0�Ԗڂ̗v�f�𒍕��P�ʂƂ��Ďg�p����B<BR>
     * <BR>
     * �Q�j�@@������������L���[�Ƀf�[�^��o�^����B<BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�M�p�V�K��_������������L���[�e�[�u��.xls�v�Q��<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B
     * @@throws WEB3BaseException
     */
    public void insertMarginOpenHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertMarginOpenHostOrder(long)";
        log.entering(STR_METHOD_NAME);
        
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        try
        {
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            
            //14.(*1) �L���[�e�[�u���ɍs��}������B
            //�}������s�̓��e�́ADB�X�V�d�l
            //�u�M�p�V�K��_���������L���[�e�[�u��.xls�v�Q�ƁB
            HostEqtypeOrderAllParams l_params = new HostEqtypeOrderAllParams();
            //1   �f�[�^�R�[�h  �hAI801�h
            l_params.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER);
            //����ID
            l_params.setAccountId(l_orderUnitRow.getAccountId());
            //2   �،���ЃR�[�h       ���������P��.���X�h�c�ɊY������،���ЃR�[�h

            l_params.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //3   ���X�R�[�h      "���������P��.���X�h�c�ɊY�����镔�X.���X�R�[�h�i* �ڋq�̎���X�j"  
            l_params.setBranchCode(l_branch.getBranchCode());
            //4   �ڋq�R�[�h   ���������P��.�����h�c�ɊY������ڋq.�����R�[�h
            l_params.setAccountCode(
                l_accountManager
                    .getMainAccount(l_orderUnitRow.getAccountId())
                    .getAccountCode());
            //5   ���҃R�[�h�iSONAR�j   ���������P��.���҃R�[�h�iSONAR�j
            l_params.setSonarTraderCode(l_orderUnitRow.getSonarTraderCode());
            //6   ���ʃR�[�h      ���������P��.���ʃR�[�h
            l_params.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //��������ԍ� �F ���������P��.���������ŏI�ʔ�
            l_params.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
            //7   �����R�[�h     ���������P��.�����h�c�ɊY�����銔������.�����R�[�h
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            l_params.setProductCode(l_product.getProductCode());
            //8   ���t����     ���������P��.������ʁ��h�V�K���������h�̏ꍇ�A���������P��.�������ʂ�ݒ�B�ȊO�A0��ݒ�                      
            if (OrderTypeEnum
                .MARGIN_SHORT
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setSellOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setSellOrderQuantity(0);
            }

            // 9   ���t����  ���������P��.������ʁ��h�V�K���������h�̏ꍇ�A���������P��.�������ʂ�ݒ�B�ȊO�A0��ݒ� 
            if (OrderTypeEnum.MARGIN_LONG
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setBuyOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setBuyOrderQuantity(0);
            }
            //10  �w�l     ���������P��.�w�l�B�i���s�̏ꍇ0�j
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
            //11  ���s�����iSONAR�j
            if (this.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
            {
                l_params.setExecutionCondition(
                    WEB3ExecutionConditionDef.COME_TO_TERMS);
            }
            else
            {
                l_params.setExecutionCondition(
                    this.getExecutionConditionTypeSonar(
                        l_orderUnitRow.getExecutionConditionType()));
            }
            //12  �l�i�����iSONAR�j
            l_params.setPriceConditionType(
                this.getPriceConditionTypeSonar(
                    l_orderUnitRow.getPriceConditionType()));
            //13  ����R�[�h�iSONAR�j    
            l_params.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //14  �ٍϋ敪�iSONAR�j   
            l_params.setSonarRepaymentType(
                l_orderUnitRow.getSonarRepaymentType());
            //15  �s��R�[�h�iSONAR�j   
            l_params.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
            //16  �`�[��   
            l_params.setTicketNumber(l_orderUnitRow.getVoucherNo());
            //17  �󒍓���       
            Timestamp l_tsCurTime =
                l_orderUnitRow.getReceivedDateTime();
            l_params.setReceivedDateTime(l_tsCurTime);
            //18  �󒍓��敪        "���������P��.������==�ithis.�󒍓����i�����ݓ����j��YYYYMMDD�����j�̏ꍇ�́@@0�F�����B
            //�ȊO�A1�F�O���B"      
            String l_strReceivedDateTime =
                WEB3DateUtility.formatDate(l_tsCurTime, "yyyyMMdd");
            if (l_strReceivedDateTime.equals(l_orderUnitRow.getBizDate()))
            {
                l_params.setReceivedDateTimeDiv(WEB3OrderDateDivDef.TODAY);
            }
            else
            {
                l_params.setReceivedDateTimeDiv(WEB3OrderDateDivDef.YESTERDAY);
            }

            //19  �ŋ敪�i��������敪�j     
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_params.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }
            //20  ���n�v�ŋ敪 
            l_params.setCapitalGainTaxType(WEB3CapitalGainTaxTypeDef.NOTHING);
            //21  ����      
            l_params.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
            //22  �����`���l��  
            l_params.setOrderChanel(l_orderUnitRow.getOrderChanel());
            //23  �t�@@�N�^�[        
            l_params.setFactor(" ");
            //24  �萔����   
            l_params.setCommisionNumber(l_orderUnitRow.getCommTblNo());
            //25  �萔�����}��      
            l_params.setCommisionBranchNumber(l_orderUnitRow.getCommTblSubNo());
            //26  �萔�����i�R�[�h 
            l_params.setCommisionProductCode(
                l_orderUnitRow.getCommProductCode());
            //27  �󔄃t���O
            l_params.setShortSellOrderFlag(
                l_orderUnitRow.getShortSellOrderFlag());
            //28  �����敪                                                          
            l_params.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            // �����o�H�敪
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            l_params.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
            // ����敪
            l_params.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
            // �t�����g����������敪�R�[�h
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_params.setFrontOrderExchangeCode(
                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
            // �t�����g�����V�X�e���敪
            EqTypeTradedProduct l_tradedProduct =
                (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            String l_strFrontOrderSystemCode =
                l_frontOrderService.getFrontOrderSystemCode(
                    l_strMarketCode,
                    l_tradedProductRow.getOpenOtcDiv());
            l_params.setFrontOrderSystemCode(
                l_strFrontOrderSystemCode);
            // �t�����g��������敪�R�[�h
            l_params.setFrontOrderTradeCode(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            // ���Ȉϑ��敪
            l_params.setTradeauditCode(WEB3TradeauditCodeDef.COMMISSION);
            // �Г���������
            l_params.setCorpCode(
                l_frontOrderService.getCorpCode(l_orderUnit));
            // �S���������敪
            l_params.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
            
            Processors.getDefaultProcessor().doInsertQuery(l_params);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
           
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (insert�M�p�ԍϒ����L���[)<BR>
     * ������������L���[�e�[�u���Ɍ�������������<BR>
     * �f�[�^��o�^����B<BR>
     * <BR>
     * �P�j�@@�g�����������}�l�[�W��.getOrderUnits(����ID)�ɂ�<BR>
     * �@@�����P�ʂ��擾����B<BR>
     * �@@���߂�l��0�Ԗڂ̗v�f�𒍕��P�ʂƂ��Ďg�p����B<BR>
     * <BR>
     * �Q�j�@@������������L���[�Ƀf�[�^��o�^����B<BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�@@�u�M�p�ԍ�_������������L���[�e�[�u��.xls�v�Q��<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID�B
     * @@throws WEB3BaseException
     */
    public void insertMarginCloseHostOrder(
        long l_lngOrderId)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "insertMarginCloseHostOrder(long)";
        log.entering(STR_METHOD_NAME);
        
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        try
        {
            Branch l_branch =
                l_accountManager.getBranch(l_orderUnitRow.getBranchId());
            
            //(*1) �L���[�e�[�u���ɍs��}������B
            //�}������s�̓��e�́ADB�X�V�d�l
            //�u�M�p�ԍ�_���������L���[�e�[�u��.xls�v�Q�ƁB
            HostEqtypeOrderAllParams l_params = new HostEqtypeOrderAllParams();
            //1   �f�[�^�R�[�h  
            l_params.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER);
            //����ID
            l_params.setAccountId(l_orderUnitRow.getAccountId());
            //2   �،���ЃR�[�h    
            l_params.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //3   ���X�R�[�h          
            l_params.setBranchCode(l_branch.getBranchCode());
            //4   �ڋq�R�[�h
            MainAccount l_mainAccount = l_mainAccount = l_accountManager
                .getMainAccount(l_orderUnitRow.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            l_params.setAccountCode(l_mainAccount.getAccountCode());
            //5   ���҃R�[�h�iSONAR�j
            l_params.setSonarTraderCode(l_orderUnitRow.getSonarTraderCode());

            //6   ���ʃR�[�h 
            l_params.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //��������ԍ� �F ���������P��.���������ŏI�ʔ�
            l_params.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
            //7   �����R�[�h   
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            l_params.setProductCode(l_product.getProductCode());
            //8   ���t����                           
            if (OrderTypeEnum
                .CLOSE_MARGIN_LONG
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setSellOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setSellOrderQuantity(0);
            }

            // 9   ���t����         
            if (OrderTypeEnum
                .CLOSE_MARGIN_SHORT
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setBuyOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setBuyOrderQuantity(0);
            }
            //10  �w�l        
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
            //11  ���s�����iSONAR�j
            if (this.isCarriedOrderUnit((EqTypeOrderUnit)l_orderUnit))
            {
                l_params.setExecutionCondition(
                    WEB3ExecutionConditionDef.COME_TO_TERMS);
            }
            else
            {
                l_params.setExecutionCondition(
                    this.getExecutionConditionTypeSonar(
                        l_orderUnitRow.getExecutionConditionType()));
            }
            //12  �l�i�����iSONAR�j
            l_params.setPriceConditionType(
                this.getPriceConditionTypeSonar(
                    l_orderUnitRow.getPriceConditionType()));
            //13  ����R�[�h�iSONAR�j              
            l_params.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //14  �ٍϋ敪�iSONAR�j
            l_params.setSonarRepaymentType(
                l_orderUnitRow.getSonarRepaymentType());
            //15  �s��R�[�h�iSONAR�j 
            l_params.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
            //16  �`�[��      
            l_params.setTicketNumber(l_orderUnitRow.getVoucherNo());
            //17  �󒍓���      
            Timestamp l_tsCurTime =
                l_orderUnitRow.getReceivedDateTime();
            l_params.setReceivedDateTime(l_tsCurTime);
            //18  �󒍓��敪       
            String l_strReceivedDateTime =
                WEB3DateUtility.formatDate(l_tsCurTime, "yyyyMMdd");
            if (l_strReceivedDateTime.equals(l_orderUnitRow.getBizDate()))
            {
                l_params.setReceivedDateTimeDiv(WEB3OrderDateDivDef.TODAY);
            }
            else
            {
                l_params.setReceivedDateTimeDiv(WEB3OrderDateDivDef.YESTERDAY);
            }

            //19  �ŋ敪�i��������敪�j      
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_params.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }
            
			//20 ���n�v�ŋ敪 �F
			// �l�q�i�ڋq.�����^�C�v==("�l�A�J�E���g", "���p�A�J�E���g")�j�ł��A
			//�@@�@@���Z�ҁA���ʔ񋏏Z�҂̏ꍇ�F�@@1�F�\��
			//�@@�@@�񋏏Z�҂̏ꍇ�F�@@�@@�@@�@@�@@�@@�@@0�F�Ȃ�
			// �@@�l�q�i�ڋq.�����^�C�v=="�@@�l�A�J�E���g"�j�̏ꍇ�F�@@0�F�Ȃ�
			if (
				MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.equals(l_mainAccountRow.getAccountType()) ||
				MainAccountTypeEnum.JOINT_OWNERSHIP.equals(l_mainAccountRow.getAccountType()))
			{
				if (WEB3ResidentDef.RESIDENT.equals(l_mainAccountRow.getResident()) ||
					WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_mainAccountRow.getResident()))
				{
					l_params.setCapitalGainTaxType(
						WEB3CapitalGainTaxTypeDef.REPORT);
				}
				else if (WEB3ResidentDef.NON_RESIDENT.equals(l_mainAccountRow.getResident()))
				{
					l_params.setCapitalGainTaxType(
						WEB3CapitalGainTaxTypeDef.NOTHING);
				}
			}
            else if(
                MainAccountTypeEnum.CORPORATE_ACCOUNT.equals(l_mainAccountRow.getAccountType()))
            {
                l_params.setCapitalGainTaxType(
                    WEB3CapitalGainTaxTypeDef.NOTHING);
            }
            
            //21  ����              
            l_params.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
            //22  �����`���l��  
            l_params.setOrderChanel(l_orderUnitRow.getOrderChanel());
            //23  �t�@@�N�^�[     
            l_params.setFactor(" ");
            //24  �萔����      
            l_params.setCommisionNumber(l_orderUnitRow.getCommTblNo());
            //25  �萔�����}��          
            l_params.setCommisionBranchNumber(l_orderUnitRow.getCommTblSubNo());
            //26  �萔�����i�R�[�h      
            l_params.setCommisionProductCode(
                l_orderUnitRow.getCommProductCode());
            //27  �󔄃t���O     
            l_params.setShortSellOrderFlag(
                l_orderUnitRow.getShortSellOrderFlag());
            //28  �����敪                                                                                  
            l_params.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            // �����o�H�敪
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            l_params.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv); 
            // ����敪
            l_params.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
            // �t�����g����������敪�R�[�h
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_params.setFrontOrderExchangeCode(
                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
            // �t�����g�����V�X�e���敪
            EqTypeTradedProduct l_tradedProduct =
                (EqTypeTradedProduct)l_orderUnit.getTradedProduct();
            EqtypeTradedProductRow l_tradedProductRow =
                (EqtypeTradedProductRow)l_tradedProduct.getDataSourceObject();
            String l_strFrontOrderSystemCode =
                l_frontOrderService.getFrontOrderSystemCode(
                    l_strMarketCode,
                    l_tradedProductRow.getOpenOtcDiv());
            l_params.setFrontOrderSystemCode(
                l_strFrontOrderSystemCode);
            // �t�����g��������敪�R�[�h
            l_params.setFrontOrderTradeCode(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            // ���Ȉϑ��敪
            l_params.setTradeauditCode(WEB3TradeauditCodeDef.COMMISSION);
            // �Г���������
            l_params.setCorpCode(
                l_frontOrderService.getCorpCode(l_orderUnit));
            // �S���������敪
            l_params.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
            
            Processors.getDefaultProcessor().doInsertQuery(l_params);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        catch (DataException l_de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_de.getMessage(),
                l_de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify���[���G���W���T�[�o)<BR>
     * �inotifyRLS�j<BR>
     * �����t�����̎��s�A�o�^�A�����A�����<BR>
     * ���[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����jnotify���[���G���W���T�[�o�v�Q�ƁB<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@param l_context - (����)<BR>
     * �����B<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@throws WEB3BaseException
     */
    public void notifyRLS(
        EqTypeOrderUnit l_orderUnit,
        OrderManagerPersistenceContext l_context)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "notifyRLS(EqTypeOrderUnit, OrderManagerPersistenceContext)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.(*)��菈���i�p�����[�^.���� == "FILL_ORDER"�j�̏ꍇ
        if (OrderManagerPersistenceContext.FILL_ORDER.equals(l_context))
        {
            //1.1.1.notify�e�����S�����(EqTypeOrderUnit)
            this.notifyParentOrderFullyExecuted(l_orderUnit);
        }
        //1.2.(*)��菈���ȊO�̏ꍇ
        else
        {
            //1.2.1.notify�A������(EqTypeOrderUnit)
            this.notifySuccOrder(l_orderUnit);
        }

        //�p�����[�^�D�����P��.getDataSourceObject()�������\�񒍕��P��Row�̏ꍇ
        if (l_orderUnit.getDataSourceObject() instanceof RsvEqOrderUnitRow)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //1.3.get��������(String, String)
        //[����] 
        // ���������F�@@�����P��.�������� 
        // �����������F�@@�����P��.���������� 
        EqtypeOrderUnitRow l_eqOrderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = 
            WEB3EquityDataAdapter.getOrderConditionType(
                l_eqOrderUnitRow.getOrderConditionType(),
                l_eqOrderUnitRow.getOrgOrderConditionType());
        
        //1.4.�t�w�l�����iget�������� == "�t�w�l"�j�̏ꍇ
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //1.5.notify�t�w�l����(EqTypeOrderUnit, OrderManagerPersistenceContext)
            this.notifyStopOrder(l_orderUnit, l_context);
        }
        
        //1.6.W�w�l�����iget�������� == "W�w�l"�j�̏ꍇ
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //1.6.1notifyW�w�l����(EqTypeOrderUnit, OrderManagerPersistenceContext)
            this.notifyWLimitOrder(l_orderUnit, l_context);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify�e�����S�����)<BR>
     * �����Ŏw�肳�ꂽ�������S����肵�Ă���A���A<BR>
     * �L���ȗ\�񒍕����ݒ肳��Ă���e�����ł���ꍇ�ɁA<BR>
     * ���[���G���W���T�[�o�ɐe�����̑S������ʒm����B<BR>
     * ���S����肩�ǂ����͌Ăяo�����Ŕ��ʂ��邱�ƁB<BR>
     * <BR>
     * �P�j�@@�\�񒍕��L���̔���@@<BR>
     * �����Ŏw�肳�ꂽ�����ɗL���ȗ\�񒍕����ݒ肳��Ă��邩<BR>
     * �i���e�����ł��邩�j�𔻒肷��B<BR>
     * <BR>
     * �@@�P�|�P�j�@@�\�񒍕��m�F�v�ۂ̔���<BR>
     * �@@this.is�\�񒍕��m�F�v()���R�[������B<BR>
     * <BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�@@�����P�ʁF�@@����.�����P��<BR>
     * <BR>
     * �@@�߂�l��false�̏ꍇ�A�\�񒍕����ݒ肳��Ă��Ȃ����ߏ������I������ireturn)<BR>
     * <BR>
     * �Q�j�@@�S����肩�ǂ����̔���<BR>
     * �@@�����̒����P��.isFullyExecuted()���R�[������B<BR>
     * <BR>
     * �@@�߂�l��false�̏ꍇ�A�S�����łȂ����ߏ������I������ireturn)<BR>
     * <BR>
     * �R�j�@@�L���\�񒍕��̊m�F<BR>
     * �@@�L���ȗ\�񒍕��̈ꗗ���擾����B<BR>
     * <BR>
     * �@@�����\�񒍕��X�V�T�[�r�XImpl.get�L���\�񒍕��P�ʈꗗ()���R�[������B<BR>
     * <BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�@@�e�����̒���ID�F�@@����.�����P��.����ID<BR>
     * <BR>
     * �@@�߂�l��null�̏ꍇ�A�L���ȗ\�񒍕������݂��Ȃ����ߏ������I������(return)<BR>
     * <BR>
     * �S�j�@@�S�����̒ʒm<BR>
     * ���[���G���W���T�[�o�ɐe�����̑S������ʒm����B<BR>
     * <BR>
     * WEB3RlsRequestSenderService.sendConOrderExecuteMessage()���R�[������B<BR>
     * <BR>
     * [�����̐ݒ�]<BR>
     * �⏕�����F�@@����.�����P��.����ID�A�⏕����ID�ɊY������⏕����<BR>
     * �e�����̒���ID�F�@@����.�����P��.����ID<BR>
     * �����^�C�v�F�@@"����"<BR>
     * �e�����̎��ʃR�[�h�F�@@����.�����P��.���ʃR�[�h<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    private void notifyParentOrderFullyExecuted(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "notifyParentOrderFullyExecuted(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (!this.isReserveOrderConfirmRequire(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        if (!l_orderUnit.isFullyExecuted())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        WEB3ToSuccReservationEqTypeOrderUpdateService l_updateService =
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        List l_lisOrderUnits =
            l_updateService.getOpenReserveEqtypeOrderUnits(l_orderUnit.getOrderId());
        if (l_lisOrderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_orderUnit.getAccountId(),
                    l_orderUnit.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        l_rlsRequestSenderService.sendConOrderExecuteMessage(
            l_subAccount,
            new Long(l_orderUnitRow.getOrderId()),
            ProductTypeEnum.EQUITY,
            l_orderUnitRow.getOrderRequestNumber());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify�t�w�l����)<BR>
     * �t�w�l�����̓o�^�A�����A����� <BR>
     * ���[���G���W���T�[�o�ɒʒm����B <BR>
     * <BR>
     * �P�j�@@���������P�ʂ��ǂ����̔���@@ <BR>
     * �@@�����̒����P��.getDataSourceObject()���R�[������B�@@ <BR>
     * <BR>
     * �@@���\�b�h�̖߂�l�̌^���A <BR>
     * �@@���������P��Row�łȂ��ꍇ�A <BR>
     * �@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j <BR>
     * �@@���\�񒍕��P�ʂɑ΂��āA�t�w�l�͐ݒ�s�B <BR>
     * <BR>
     * �Q�j���[���G���W���T�[�o�ւ̒ʒm�v�ۃ`�F�b�N <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�������̋t�w�l�����̔��� <BR>
     * �@@�@@�@@�P�j�̖߂�l.�������� != "�t�w�l" �܂��� <BR>
     * �@@�@@�@@�P�j�̖߂�l.���N�G�X�g�^�C�v != "DEFAULT"�̏ꍇ�A <BR>
     * �@@�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�������̔����x�������̔��� <BR>
     * �@@�@@�@@�g�����������}�l�[�W��.is�������x������() == true�̏ꍇ�A <BR>
     * �@@�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j <BR>
     * <BR>
     * �@@�@@�@@[is�������x������()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�����P�ʁF�@@�P�j�̖߂�l <BR>
     * <BR>
     * �R�j�@@�⏕�������擾����B <BR>
     * �@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B <BR>
     * <BR>
     * �@@[getSubAccount()�Ɏw�肷�����] <BR>
     * �@@�@@arg0�F�@@�P�j�̖߂�l.����ID <BR>
     * �@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID <BR>
     * <BR>
     * �S�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j <BR>
     * �@@���擾����B <BR>
     * <BR>
     * �T�j�@@���[���G���W���T�[�o�ɒʒm���s���B <BR>
     * �@@�����̒����P��.������Ԃɂ���ĉ��L������s���B <BR>
     * <BR>
     * �@@�T�|�P�j�@@��������i�����L�����="�N���[�Y"�j<BR>
     * �@@�@@�@@�܂��͎����A�����J�z�X�L�b�v�i���� == "ORDER_INVALIDATED_BY_MKT"�j�̏ꍇ<BR>
     * �@@�@@�@@�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h�� <BR>
     * �@@�@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@�@@[sendCancelConOrderMessage()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�⏕�����F�@@�擾�����⏕���� <BR>
     * �@@�@@�@@�@@�����t�����^�C�v�F�@@"�t�w�l" <BR>
     * �@@�@@�@@�@@�����^�C�v�F�@@"����" <BR>
     * �@@�@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID <BR>
     * <BR>
     * �@@�T�|�Q�j�@@���������i="�����ρi�ύX�����j"�j�̏ꍇ <BR>
     * �@@�@@�@@�擾�����T�[�r�X.sendModifyConOrderMessage()���\�b�h�� <BR>
     * �@@�@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@�@@[sendModifyConOrderMessage()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�⏕�����F�@@�擾�����⏕���� <BR>
     * �@@�@@�@@�@@�����t�����^�C�v�F�@@"�t�w�l" <BR>
     * �@@�@@�@@�@@�e�����̖����^�C�v�F�@@"����" <BR>
     * �@@�@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID <BR>
     * �@@�@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null <BR>
     * �@@�@@�@@�@@�q�����̒���ID�ꗗ�F�@@null <BR>
     * <BR>
     * �@@�T�|�R�j�@@�V�K�o�^�i="��t�ρi�V�K�����j"�j�̏ꍇ] <BR>
     * �@@�@@�@@�擾�����T�[�r�X.sendRegisterConOrderMessage()���\�b�h�� <BR>
     * �@@�@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@�@@[sendRegisterConOrderMessage()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�@@�⏕�����F�@@�擾�����⏕���� <BR>
     * �@@�@@�@@�@@�����t�����^�C�v�F�@@"�t�w�l" <BR>
     * �@@�@@�@@�@@�e�����̖����^�C�v�F�@@"����" <BR>
     * �@@�@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID <BR>
     * �@@�@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null <BR>
     * �@@�@@�@@�@@�q�����̒���ID�ꗗ�F�@@null <BR>
     * <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_context - (����)<BR>
     * �����B<BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j<BR>
     * @@throws WEB3BaseException
     */
    private void notifyStopOrder(
        EqTypeOrderUnit l_orderUnit,
        OrderManagerPersistenceContext l_context)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "notifyStopOrder(EqTypeOrderUnit, OrderManagerPersistenceContext)";
        log.entering(STR_METHOD_NAME);
        
        Object l_row = l_orderUnit.getDataSourceObject();
        if (!(l_row instanceof EqtypeOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //   �@@�Q�|�P�j�@@�������̋t�w�l�����̔��� 
        // �@@�@@�@@�P�j�̖߂�l.�������� != "�t�w�l" �܂��� 
        // �@@�@@�@@�P�j�̖߂�l.���N�G�X�g�^�C�v != "DEFAULT"�̏ꍇ�A 
        // �@@�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j 
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_row;
        try
        {
            l_orderUnitRow = (EqtypeOrderUnitRow)getOrderUnit(l_orderUnitRow.getOrderUnitId()).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (!WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()) ||
            !WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //�@@�Q�|�Q�j�@@�������̔����x�������̔��� 
        //�@@�@@�@@�g�����������}�l�[�W��.is�������x������() == true�̏ꍇ�A 
        //�@@�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j 
        //
        //�@@�@@�@@[is�������x������()�ɐݒ肷�����] 
        //�@@�@@�@@�@@�����P�ʁF�@@�P�j�̖߂�l 
        if (this.isNotOrderedDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //�R�j�@@�⏕�������擾����B 
        //�@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B 
        //�@@[getSubAccount()�Ɏw�肷�����] 
        //�@@�@@arg0�F�@@�P�j�̖߂�l.����ID 
        //�@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        // �S�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j 
        //���擾����B 
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        
        //�T�j�@@���[���G���W���T�[�o�ɒʒm���s���B 
        //�@@�����̒����P��.������Ԃɂ���ĉ��L������s���B 
        //�@@�T�|�P�j�@@��������i�����L�����="�N���[�Y"�j
        //     �܂��͎����A�����J�z�X�L�b�v�i���� == "ORDER_INVALIDATED_BY_MKT"�j�̏ꍇ
        //�@@�@@�@@�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h�� 
        //�@@�@@�@@�R�[������B 
        //�@@�@@�@@[sendCancelConOrderMessage()�Ɏw�肷�����] 
        //�@@�@@�@@�@@�⏕�����F�@@�擾�����⏕���� 
        //�@@�@@�@@�@@�����t�����^�C�v�F�@@"�t�w�l" 
        //�@@�@@�@@�@@�����^�C�v�F�@@"����" 
        //�@@�@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID 
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        OrderOpenStatusEnum l_orderOpenStatus = l_orderUnitRow.getOrderOpenStatus();
        
        if (OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus)
            || OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT.equals(l_context))
        {
            l_rlsRequestSenderService.sendCancelConOrderMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.EQUITY,
                new Long(l_orderUnitRow.getOrderId()));
        }
        //   �@@�T�|�Q�j�@@���������i="�����ρi�ύX�����j"�j�̏ꍇ 
        // �@@�@@�@@�擾�����T�[�r�X.sendModifyConOrderMessage()���\�b�h�� 
        // �@@�@@�@@�R�[������B 
        else if (OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendModifyConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.EQUITY,
                new Long(l_orderUnitRow.getOrderId()),
                null,
                null);
        }
        //   �@@�T�|�R�j�@@�V�K�o�^�i="��t�ρi�V�K�����j"�j�̏ꍇ] 
        // �@@�@@�@@�擾�����T�[�r�X.sendRegisterConOrderMessage()���\�b�h�� 
        // �@@�@@�@@�R�[������B 
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.STOP_LlIMIT,
                ProductTypeEnum.EQUITY,
                new Long(l_orderUnitRow.getOrderId()),
                null,
                null);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify�A������)<BR>
     * �A�������̓o�^�����[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �P�j�@@�\�񒍕��P�ʂ��ǂ����̔���@@<BR>
     * �@@�����̒����P��.getDataSourceObject()���R�[������B�@@<BR>
     * <BR>
     * �@@���\�b�h�̖߂�l�̌^���A<BR>
     * �@@�����\�񒍕��P��Row�łȂ��ꍇ�A<BR>
     * �@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j<BR>
     * <BR>
     * �Q�j�@@�⏕�������擾����B<BR>
     * �@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B<BR>
     * <BR>
     * �@@[getSubAccount()�Ɏw�肷�����]<BR>
     * �@@�@@arg0�F�@@�P�j�̖߂�l.����ID<BR>
     * �@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID<BR>
     * <BR>
     * �R�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j<BR>
     * �@@���擾����B<BR>
     * <BR>
     * �S�j���[���G���W���T�[�o�ɒʒm���s���B<BR>
     * �@@[�V�K�o�^�i�P�j�̖߂�l.������� == "��t�ρi�V�K�����j"�j�̏ꍇ]<BR>
     * �@@�@@�擾�����T�[�r�X.sendRegisterConOrderMessage()���\�b�h��<BR>
     * �@@�@@�R�[������B<BR>
     * <BR>
     * �@@�@@[sendRegisterConOrderMessage()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕����<BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"�A������"<BR>
     * �@@�@@�@@�e�����̖����^�C�v�F�@@"����"<BR>
     * �@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.�e�����̒���ID<BR>
     * �@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@�P�j�̖߂�l.�����^�C�v�݂̂�v�f�Ƃ���z��<BR>
     * �@@�@@�@@�q�����̒���ID�ꗗ�F�@@�P�j�̖߂�l.����ID�݂̂�v�f�Ƃ���z��<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B
     * @@throws WEB3BaseException
     */
    private void notifySuccOrder(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "notifySuccOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        Object l_row = l_orderUnit.getDataSourceObject();
        if (!(l_row instanceof RsvEqOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        RsvEqOrderUnitRow l_rsvOrderUnitRow = (RsvEqOrderUnitRow)l_row;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_rsvOrderUnitRow.getAccountId(),
                    l_rsvOrderUnitRow.getSubAccountId());
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        
        OrderStatusEnum l_orderStatus = l_rsvOrderUnitRow.getOrderStatus();
        if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.EXECUTE,
                ProductTypeEnum.EQUITY,
                new Long(l_rsvOrderUnitRow.getParentOrderId()),
                new ProductTypeEnum[] { l_rsvOrderUnitRow.getProductType() },
                new Long[] { new Long(l_rsvOrderUnitRow.getOrderId()) });
        }
        
        log.exiting(STR_METHOD_NAME);
    }   
    
    /**
     * (throw�]�̓G���[�ڍ׏��)<BR>
     * ����]�̓G���[�敪�ɉ����āA���ߍ��݃G���[���b�Z�[�W�̕ҏW�A<BR>
     * �y�ъY������G���[�R�[�h��ݒ肵�ė�O��throw������B<BR>
     * ���]�̓`�F�b�N�G���[���̂ݎg�p����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�����jthrow�]�̓G���[�ڍ׏��v�Q�ƁB<BR>
     * @@param l_tpResult - (����]�͌���)<BR>
     * ����]�͌��ʃI�u�W�F�N�g�B
     * @@param l_orderType - (�������)<BR>
     * ������ʁB<BR>
     * �i�a����s�����̔������蓙�Ɏg�p����j
     * @@throws WEB3BaseException
     */
    public void throwTpErrorInfo(
        WEB3TPTradingPowerResult l_tpResult,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "throwTpErrorInfo(WEB3TPTradingPowerResult, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_tpResult.isResultFlg())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            String l_strTradinPowerErrorDiv = l_tpResult.getTpErrorInfo().tradinPowerErrorDiv;
            if (WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR.equals(l_strTradinPowerErrorDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if (WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_ACCOUNT_BALANCE.equals(l_strTradinPowerErrorDiv))
            {
                String l_strErrorMessage =
                    WEB3StringTypeUtility.formatNumber(l_tpResult.getTpErrorInfo().marginTradingPowerIncDeposit)
                    + ","
                    + WEB3StringTypeUtility.formatNumber(l_tpResult.getTpErrorInfo().lackAccountBalance);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02299,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage);
            }
            else if (WEB3TPTradingPowerErrorDivDef.INC_DEPOSIT_LACK_MARGIN_POWER.equals(l_strTradinPowerErrorDiv))
            {
                String l_strErrorMessage =
                    WEB3StringTypeUtility.formatNumber(l_tpResult.getTpErrorInfo().marginTradingPowerIncDeposit);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02300,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_strErrorMessage);
            }
            //����]�͌���.����]�̓G���[���.����]�̓G���[�敪=="����ۏ؋���L�����߃G���["�̏ꍇ
            else if (WEB3TPTradingPowerErrorDivDef.RECEIPT_DEPOSIT_RATE_OVER_ERROR.equals(l_strTradinPowerErrorDiv))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03143,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) ||
                OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01935,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType) ||
                OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
            {
                //TODO: �������A�����̗a����s���̖��ߍ��݃G���[���b�Z�[�W�ݒ�͖��Ή��B�a����s���z�̉�ʕ\���ǉ����ɏC������B
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType) ||
                OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
            {
                //TODO: �������A���n�̗a����s���̖��ߍ��݃G���[���b�Z�[�W�ݒ�͖��Ή��B�a����s���z�̉�ʕ\���ǉ����ɏC������B
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * (get�����G���[���R�R�[�h)<BR>
     * <BR>
     * �����̃G���[�R�[�h����A<BR>
     * �J�z���s�̒����^�������s�̋t�w�l�����^�������s�̗\�񒍕� �ɐݒ肷�邽�߂�<BR>
     * �����G���[���R�R�[�h�ւ̕ϊ����s���B<BR>
     * �i�����G���[���R�R�[�h�̃R�[�h�̌n�́A���������P�ʃe�[�u����DB���C�A�E�g���Q�Ɓj<BR>
     * <BR>
     * �ϊ��d�l�͈ȉ��̒ʂ�B<BR>
     * ---------------------------------------------------------------------<BR>
     * "����"<BR>
     * �����̃G���[�R�[�h==null<BR>
     * <BR>
     * "�l���G���["<BR>
     * �iBUSINESS_ERROR_00031 or<BR>
     * �@@BUSINESS_ERROR_02298�j<BR>
     * 
     * "�a����s���G���["�i���V�K���ȊO�j<BR>
     * �iBUSINESS_ERROR_01306 or<BR>
     * �@@BUSINESS_ERROR_01929 or<BR>
     * �@@BUSINESS_ERROR_01930 or<BR>
     * �@@BUSINESS_ERROR_02299 or�j<BR>
     * <BR>
     * "�����c���s���G���["<BR>
     * BUSINESS_ERROR_00167<BR>
     * <BR>
     * "�ۏ؋��s���G���["�i���V�K���œ�K���G���[�ȊO�j<BR>
     * �iBUSINESS_ERROR_01935 or<BR>
     * �@@BUSINESS_ERROR_02300�j<BR>
     * <BR>
     * "�����c���s���G���["<BR>
     * �iBUSINESS_ERROR_00808 or<BR>
     * �@@BUSINESS_ERROR_02339�j<BR>
     * <BR>
     * "������~�����G���["<BR>
     * �iBUSINESS_ERROR_00014 or<BR>
     * �@@BUSINESS_ERROR_00015 or<BR>
     * �@@BUSINESS_ERROR_00692 or<BR>
     * �@@BUSINESS_ERROR_00693 or<BR>
     * �@@BUSINESS_ERROR_00694 or<BR>
     * �@@BUSINESS_ERROR_00695 or<BR>
     * �@@BUSINESS_ERROR_00696 or<BR>
     * �@@BUSINESS_ERROR_00697 or<BR>
     * �@@BUSINESS_ERROR_00700�j<BR>
     * <BR>
     * "�s��ύX�����G���["<BR>
     * �iBUSINESS_ERROR_01966 or<BR>
     * BUSINESS_ERROR_01307�j<BR>
     * <BR>
     * "��������G���["<BR>
     * �iBUSINESS_ERROR_00026 or<BR>
     * BUSINESS_ERROR_01703�j<BR>
     * <BR>
     * "�����J�z�X�L�b�v�����G���["<BR>
     * BUSINESS_ERROR_00684<BR>
     * <BR>
     * "��K���`�F�b�N�G���["<BR>
     * BUSINESS_ERROR_01928<BR>
     * <BR>
     * "�������`�F�b�N�G���["<BR>
     * �iBUSINESS_ERROR_00205 or<BR>
     * �@@BUSINESS_ERROR_02288�j<BR>
     * <BR>
     * "�Ēl�`�F�b�N�G���["<BR>
     * BUSINESS_ERROR_00030<BR>
     * <BR>
     * "�󔄂�`�F�b�N�G���["<BR>
     * BUSINESS_ERROR_00734<BR>
     * <BR>
     * "���ϊ��������σG���["<BR>
     * BUSINESS_ERROR_00748<BR>
     * <BR>
     * "���̑��G���["<BR>
     * ��L�ȊO<BR>
     * @@param l_strErrorCode - (�G���[�R�[�h)<BR>
     * WEB3ErrorCatalog�ɒ�`����Ă���G���[�R�[�h�B<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getErrorReasonCode(
        String l_strErrorCode)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getErrorReasonCode(String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strErrorCode == null)
        {
            log.exiting(STR_METHOD_NAME);
            //"0000"�i����j
            return WEB3ErrorReasonCodeDef.NORMAL;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00031.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02298.getErrorCode().equals(l_strErrorCode))
        {
            //"0001"�i�l���G���[�j
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01929.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01930.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02299.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0002"�i�a����s���G���[�j
            return WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00167.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0003"�i�����c���s���G���[�j
            return WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01935.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02300.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0004"�i�ۏ؋��s���G���[�j
            return WEB3ErrorReasonCodeDef.GUARANTY_MONEY_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00808.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02339.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0005"�i�����c���s���G���[�j
            return WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00014.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00015.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00692.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00693.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00694.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00695.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00696.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00697.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_00700.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0006"�i������~�����G���[�j
            return WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
        }                                
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01966.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01307.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0007"�i�s��ύX�����G���[�j
            return WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
        } 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00026.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01703.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            // BUSINESS_ERROR_00026 : ��������
            // BUSINESS_ERROR_01703 : �M�p���
            //"0010"�i��������G���[�j
            return WEB3ErrorReasonCodeDef.SPEC_ACCOUNT_ERROR;
        } 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00684.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0011"�i�����J�z�X�L�b�v�����G���[�j
            return WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01928.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0012"�i��K���`�F�b�N�G���[�j
            return WEB3ErrorReasonCodeDef.MARGIN_SEC_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00205.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02288.getErrorCode().equals(l_strErrorCode))
        {
            //"0013"�i�������`�F�b�N�G���[�j
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.BIZ_DATE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00030.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0014"�i�Ēl�`�F�b�N�G���[�j
            return WEB3ErrorReasonCodeDef.TICK_VALUE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00734.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0015"�i�󔄂�`�F�b�N�G���[�j
            return WEB3ErrorReasonCodeDef.SHORT_SELLING_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00748.getErrorCode().equals(l_strErrorCode)) 
        {
            log.exiting(STR_METHOD_NAME);
            //"0016"�i���ϊ��������σG���[�j
            return WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            //��L�ȊO�̔����R���ŃG���[�����������ꍇ�́A"9001"�i���̑��G���[�j��ݒ肷��B
            return WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }
    }
    
    /**
     * �iupdate�����x���j<BR>
     * <BR>
     * �w�肳�ꂽ����ID�ɕR�t�������f�[�^�ނ𔭒��x���̃X�e�[�^�X�ɍX�V����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.����ID�ɕR�t�������P�ʂ��擾����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̒����P�ʂ�clone���쐬����B<BR>
     * <BR>
     * �R�j�@@�Q�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B<BR>
     * �@@DB�X�V�d�l<BR>
     * �@@�u�t�w�l���������i�����x���j_���������P�ʃe�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �S�j�@@���������I�u�W�F�N�g���擾����B<BR>
     * �@@this.create��������()���R�[������B<BR>
     * <BR>
     * �@@[create��������()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�R�j�ɂč쐬���������P��<BR>
     * �@@�@@�C�x���g�^�C�v�F�@@"21�F�����x��"<BR>
     * <BR>
     * �@@��DB�X�V�d�l�i�Q�l�j<BR>
     * �@@�u�t�w�l��������(�����x��)_�������������e�[�u��.xls�v�Q�ƁB<BR>
     * <BR>
     * �T�j�@@�����f�[�^��update����B<BR>
     * �@@this.update�����f�[�^()���R�[������B<BR>
     * <BR>
     * �@@[update�����f�[�^()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�R�j�ɂč쐬���������P��<BR>
     * �@@�@@���������F�@@create��������()�̖߂�l<BR>
     * @@param l_orderId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateOrderDelay(long l_orderId) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "updateOrderDelay(long)";
    	log.entering(STR_METHOD_NAME);
    	
    	//�P�j�@@�p�����[�^.����ID�ɕR�t�������P�ʂ��擾����B
		OrderUnit[] l_orderUnits = getOrderUnits(l_orderId);
        
		if (l_orderUnits.length == 0) 
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME);
		}
        else if (l_orderUnits.length > 1) 
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        EqTypeOrderUnit l_eqtypeOrderUnits = null;        
        l_eqtypeOrderUnits = (EqTypeOrderUnit)l_orderUnits[0];
                
		EqtypeOrderUnitRow l_orderUnitRow = 
			(EqtypeOrderUnitRow)l_eqtypeOrderUnits.getDataSourceObject();
		
		//�Q�j�@@�P�j�̒����P�ʂ�clone���쐬����B
		EqtypeOrderUnitParams l_orderUnitParams =
			new EqtypeOrderUnitParams(l_orderUnitRow);
			
		//�R�j�@@�Q�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B
		//���������ŏI�ʔ�
		l_orderUnitParams.setLastOrderActionSerialNo(
			l_orderUnitParams.getLastOrderActionSerialNo() + 1);

		//�X�V���t
		Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
		l_orderUnitParams.setLastUpdatedTimestamp(l_tsSysTime);

		//�����x���t���O
        l_orderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
		
        //�S�j�@@���������I�u�W�F�N�g���擾����B
        //�@@[create��������()�Ɏw�肷�����]
        //�@@�@@�����P�ʁF�@@�R�j�ɂč쐬���������P��
        //�@@�@@�C�x���g�^�C�v�F�@@"21�F�����x��"
        EqTypeOrderUnit l_afterOrderUnit = 
            (EqTypeOrderUnit)this.toOrderUnit(l_orderUnitParams);
        EqTypeOrderAction l_orderAction =
            this.createOrderAction(l_afterOrderUnit, OrderEventTypeEnum.ORDER_DELAY);

        //�T�j�@@�����f�[�^��update����B
        //�@@[update�����f�[�^()�Ɏw�肷�����]
        //�@@�����P�ʁF�@@�R�j�ɂč쐬���������P��
        //�@@���������F�@@create��������()�̖߂�l
        this.updateOrderData(l_afterOrderUnit, l_orderAction);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc�S�����z�v�Z�P��)<BR>
     * <BR>
     * �S����������^��������v�Z�Ɏg�p����v�Z�P�����v�Z���ԋp����B<BR>
     * �܂��A�����̎萔���I�u�W�F�N�g�ɁA�v�Z�P���̎w�l�^���s�̕ʂ�ݒ肷��B<BR>
     * �|���������A�V�K���A�ԍςɑΉ��B<BR>
     * �|�s�o������������"���s"�iSTOP���j�ōS������ꍇ�ł����Ă��A�萔����"�w�l"�Ōv�Z����B<BR>
     * <BR>
     * �P�j�@@�����`�F�b�N<BR>
     * <BR>
     * �P�|�P�j�@@�����̒�����ʂ��ȉ��̂�����ɂ��Y�����Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�p�����[�^�s���v�̗�O��throw����B<BR>
     * <BR>
     * �@@�@@�@@����������<BR>
     * �@@�@@�@@����������<BR>
     * �@@�@@�@@�V�K��������<BR>
     * �@@�@@�@@�V�K��������<BR>
     * �@@�@@�@@���ԍρi�����ԍρj����<BR>
     * �@@�@@�@@���ԍρi�����ԍρj����<BR>
     * <BR>
     * ������ʖ��ɍS���p�̌v�Z�P���A�y�ю萔���v�Z�����i�w�l�^���s�j���擾�E�Z�b�g����B<BR>
     * =================================================================================<BR>
     * �Q�j�@@�����̒������==�����������̏ꍇ<BR>
     * <BR>
     * �Q�|�P�j�@@�ȉ��̇@@�`�B�̂����ꂩ�ɍ��v����ꍇ�́Athis.calc����()�ɂ�莞�����擾����B<BR>
     * �@@�@@�@@���������A�����̊m�F���P����null�̏ꍇ�́A�����̊m�F���P�����g�p����B<BR>
     * <BR>
     * �@@�@@�@@���s�����̏ꍇ�i�����̎w�l==0�j<BR>
     * �@@�@@�AW�w�l�����ŁA�����w�l==���s�w��̏ꍇ<BR>
     * �@@�@@�@@�i�����̔�������=="W�w�l" ���� �iW�w�l)�����w�l==0�j<BR>
     * �@@�@@�BSTOP���S�����X(*1)�ŕs�o�����������w��̏ꍇ�i�����̎��s����=="�s�o������"�j<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc����()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��"<BR>
     * �@@�@@�@@��������F�@@�����̓�����<BR>
     * �@@�@@�@@�⏕�����F�@@�����̓�����<BR>
     * �@@�@@�@@isSTOP���l���F�@@true�i�l������j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �Q�|�Q�j�@@�߂�l�����肷��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�擾��������<BR>
     * �@@�@@�@@�@@�@@�����̎w�l<BR>
     * �@@�@@�@@�@@�@@�����́iW�w�l)�����w�l<BR>
     * �@@�@@�@@���r���A��ԍ����P����߂�l�Ƃ���B<BR>
     * <BR>
     * �Q�|�R�j�@@�����̎萔���I�u�W�F�N�g��is�w�l�v���p�e�B�ɁA<BR>
     * �@@�@@�@@�@@�|���s�����̏ꍇ�i�����̎w�l==0�j�F�@@false�i���s�j<BR>
     * �@@�@@�@@�@@�|��L�ȊO�F�@@true�i�w�l�j<BR>
     * �@@�@@�@@���Z�b�g����B<BR>
     * <BR>
     * =================================================================================<BR>
     * �R�j�@@�����̒������==�����������̏ꍇ<BR>
     * <BR>
     * �R�|�P�j�@@���s�����̏ꍇ�i�����̎w�l==0�j�́Athis.calc����()�ɂ�莞�����擾����B<BR>
     * �@@�@@�@@���������A�����̊m�F���P����null�̏ꍇ�́A�����̊m�F���P�����g�p����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc����()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��"<BR>
     * �@@�@@�@@��������F�@@�����̓�����<BR>
     * �@@�@@�@@�⏕�����F�@@�����̓�����<BR>
     * �@@�@@�@@isSTOP���l���F�@@false�i�l�����Ȃ��j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�S���p�v�Z�P���Ƃ��āA�擾����������߂�l�Ƃ���B<BR>
     * �@@�@@�@@�܂��A�����̎萔���I�u�W�F�N�g��is�w�l�v���p�e�B��false�i���s�j���Z�b�g����B<BR>
     * <BR>
     * �R�|�Q�j�@@��L�u�R�|�P�j�v�ȊO�̏ꍇ�́A�����̎w�l��߂�l�Ƃ���B<BR>
     * �@@�@@�@@�܂��A�����̎萔���I�u�W�F�N�g��is�w�l�v���p�e�B��true�i�w�l�j���Z�b�g����B<BR>
     * <BR>
     * =================================================================================<BR>
     * �S�j�@@�����̒������==�i�V�K�������� or �V�K���������j�̏ꍇ<BR>
     * <BR>
     * �S�|�P�j�@@�ȉ��̇@@�`�B�̂����ꂩ�ɍ��v����ꍇ�́Athis.calc����()�ɂ�莞�����擾����B<BR>
     * �@@�@@�@@���������A�����̊m�F���P����null�̏ꍇ�́A�����̊m�F���P�����g�p����B<BR>
     * <BR>
     * �@@�@@�@@���s�����̏ꍇ�i�����̎w�l==0�j<BR>
     * �@@�@@�AW�w�l�����ŁA�����w�l==���s�w��̏ꍇ<BR>
     * �@@�@@�@@�i�����̔�������=="W�w�l" ���� �iW�w�l)�����w�l==0�j<BR>
     * �@@�@@�BSTOP���S�����X(*1)�ŕs�o�����������w��̏ꍇ�i�����̎��s����=="�s�o������"�j<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc����()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��"<BR>
     * �@@�@@�@@��������F�@@�����̓�����<BR>
     * �@@�@@�@@�⏕�����F�@@�����̓�����<BR>
     * �@@�@@�@@isSTOP���l���F�@@true�i�l������j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�|�Q�j�@@�����w�l����(*2)�̏ꍇ�A�����i��STOP���j���擾����B<BR>
     * �@@�@@�@@���������A�����̊m�F���P����null�̏ꍇ�́A�����̊m�F���P�����g�p����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc����()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��"<BR>
     * �@@�@@�@@��������F�@@�����̓�����<BR>
     * �@@�@@�@@�⏕�����F�@@�����̓�����<BR>
     * �@@�@@�@@isSTOP���l���F�@@false�i�l�����Ȃ��j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �S�|�R�j�@@�߂�l�����肷��B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�S�|�P�j�Ŏ擾��������<BR>
     * �@@�@@�@@�@@�@@�S�|�Q�j�Ŏ擾��������<BR>
     * �@@�@@�@@�@@�@@�����̎w�l<BR>
     * �@@�@@�@@�@@�@@�����́iW�w�l)�����w�l<BR>
     * �@@�@@�@@���r���A��ԍ����P����߂�l�Ƃ���B<BR>
     * <BR>
     * �S�|�S�j�@@�����̎萔���I�u�W�F�N�g��is�w�l�v���p�e�B�ɁA<BR>
     * �@@�@@�@@�@@�|���s�����̏ꍇ�i�����̎w�l==0�j�F�@@false�i���s�j<BR>
     * �@@�@@�@@�@@�|��L�ȊO�F�@@true�i�w�l�j<BR>
     * �@@�@@�@@���Z�b�g����B<BR>
     * <BR>
     * =================================================================================<BR>
     * �T�j�@@�����̒������==�i���ԍρi�����ԍρj���� or ���ԍρi�����ԍρj�����j�̏ꍇ<BR>
     * <BR>
     * �T�|�P�j�@@���s�����̏ꍇ�i�����̎w�l==0�j�́Athis.calc����()�ɂ�莞�����擾����B<BR>
     * �@@�@@�@@���������A�����̊m�F���P����null�̏ꍇ�́A�����̊m�F���P�����g�p����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��calc����()�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�萔�����i�R�[�h�F�@@"��ꊔ��"<BR>
     * �@@�@@�@@��������F�@@�����̓�����<BR>
     * �@@�@@�@@�⏕�����F�@@�����̓�����<BR>
     * �@@�@@�@@isSTOP���l���F�@@false�i�l�����Ȃ��j<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �@@�@@�@@�S���p�v�Z�P���Ƃ��āA�擾����������߂�l�Ƃ���B<BR>
     * �@@�@@�@@�܂��A�����̎萔���I�u�W�F�N�g��is�w�l�v���p�e�B��false�i���s�j���Z�b�g����B<BR>
     * <BR>
     * �T�|�Q�j�@@��L�u�T�|�P�j�v�ȊO�̏ꍇ�́A�����̎w�l��߂�l�Ƃ���B<BR>
     * �@@�@@�@@�܂��A�����̎萔���I�u�W�F�N�g��is�w�l�v���p�e�B��true�i�w�l�j���Z�b�g����B<BR>
     * =================================================================================<BR>
     * <BR>
     * (*1)STOP���S�����X<BR>
     * �@@�ڋq�̕��X�i�����̕⏕����.���XID�j�A�萔�����i�R�[�h=="��ꊔ��"�����������Ɏw�肵�A<BR>
     * �@@�y��Е��X���i�e�[�u���z����擾�����u�T�Z���z�v�Z�����v=="STOP���S������"�̏ꍇ�́A<BR>
     * �@@STOP���S�����X�ł���Ɣ��肷��B<BR>
     * <BR>
     * (*2)�����w�l����<BR>
     * �@@�w�l�����i�����̎w�l > 0�j�A���� �����̒������=="�V�K��������"�̏ꍇ�A�����w�l�����B<BR>
     * <BR>
     * @@param l_commission - (�萔��)<BR>
     * �萔���B<BR>
     * �i�����\�b�h���ŁAis�w�l�v���p�e�B���ăZ�b�g����j<BR>
     * @@param l_orderType - (�������)<BR>
     * ������ʁB<BR>
     * @@param l_dblLimitPrice - (�w�l)<BR>
     * �w�l�B<BR>
     * �i�w�l�w��̏ꍇ�́A���̒l�B���s�w��̏ꍇ�́A0�j<BR>
     * @@param l_dblWLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l�B<BR>
     * �i�����w�l==�w�l�w��̏ꍇ�́A���̒l�B�����w�l==���s�w��̏ꍇ�́A0�j<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ���������B<BR>
     * @@param l_execConditionType - (���s����)<BR>
     * ���s�����B<BR>
     * @@param l_strPriceConditionType - (�l�i����)<BR>
     * �l�i�����B<BR>
     * @@param l_tradedProduct - (�������)<BR>
     * ������������B<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����B<BR>
     * @@param l_strCheckPrice - (�m�F���P��)<BR>
     * �m�F���P���B<BR>
     * �i��菈���̏ꍇ�A���N�G�X�g.�m�F���P�������̂܂܃Z�b�g�B<BR>
     * �@@���菈���̏ꍇ�A�擾�ς̎������g���񂵂����ۂɃZ�b�g�B�j<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
     public double calcPriceForRestraintAmount(
        WEB3GentradeCommission l_commission,
        OrderTypeEnum l_orderType,
        double l_dblLimitPrice,
        double l_dblWLimitPrice,
        String l_strOrderConditionType,
        EqTypeExecutionConditionType l_execConditionType,
        String l_strPriceConditionType,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3GentradeSubAccount l_subAccount,
        String l_strCheckPrice
        ) throws WEB3BaseException
     {
        final String STR_METHOD_NAME = "calcPriceForRestraintAmount("
            + "WEB3GentradeCommission, "
            + "OrderTypeEnum, "
            + "double, "
            + "double, "
            + "String, "
            + "EqTypeExecutionConditionType, "
            + "String, "
            + "WEB3EquityTradedProduct, "
            + "WEB3GentradeSubAccount, "
            + "String)";
        log.entering(STR_METHOD_NAME);
        
        // �����`�F�b�N
        if (!OrderTypeEnum.EQUITY_BUY.equals(l_orderType) &&
            !OrderTypeEnum.EQUITY_SELL.equals(l_orderType) &&
            !OrderTypeEnum.MARGIN_LONG.equals(l_orderType) &&
            !OrderTypeEnum.MARGIN_SHORT.equals(l_orderType) &&
            !OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType) &&
            !OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����:������ʂ��s���Ȓl�ł��B�@@�l�F[" + l_orderType + "]");
        }
        
        // �����o��
        log.debug("<���\�b�h����>");
        log.debug("�������         :[" + l_orderType + "]");
        log.debug("�w�l             :[" + l_dblLimitPrice + "]");
        log.debug("�iW�w�l�j�����w�l :[" + l_dblWLimitPrice + "]");
        log.debug("��������         :[" + l_strOrderConditionType + "]");
        log.debug("���s����         :[" + l_execConditionType + "]");
        log.debug("�l�i����         :[" + l_strPriceConditionType + "]");
        log.debug("�m�F���P��       :[" + l_strCheckPrice + "]");
        
        // (*)STOP���S�����X���ǂ����̔���
        // �|�������^�V�K�������̏ꍇ�́A���X�̊T�Z���z�v�Z�������擾����
        int l_intEstimatePriceCalcForm = 0;
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_orderType) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            log.debug("�y�T�Z���z�v�Z�����̎擾�z");
            // �T�Z���z�v�Z�����̎擾
            try
            {
                InstBranchProductRow l_instBranchProductRow =
                    InstBranchProductDao.findRowByPk(
                        l_subAccount.getWeb3GenBranch().getBranchId(),
                        WEB3CommisionProductCodeDef.LISTING_STOCK);
                        
	            l_intEstimatePriceCalcForm =
	                l_instBranchProductRow.getEstimatePriceCalcForm();
            }
            catch (DataFindException l_dfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��Е��X���i�e�[�u���ɊY���f�[�^�Ȃ�");
            }
            catch (DataException l_de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            log.debug("�T�Z���z�v�Z���� = [" + l_intEstimatePriceCalcForm + "] (1:STOP������,2:�����S������)");
        }
        
        double l_dblReturnCalcPrice = 0.0D;
        // �����������̏ꍇ
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            double l_dblCurrentPrice = 0.0D;
            // �������擾����ꍇ
            if (l_dblLimitPrice == 0 ||
                (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType) &&
                    l_dblWLimitPrice == 0) ||
                (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_intEstimatePriceCalcForm &&
                    EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execConditionType))
               )
            {
                log.debug("�������擾����ꍇ");
	            // �����̊m�F���P����null�̏ꍇ�͎������擾���Ȃ�
	            if (l_strCheckPrice != null)
	            {
	                log.debug("�@@�ˈ���.�m�F���P���������Ƃ��Ďg�p�B");
	                l_dblCurrentPrice = Double.parseDouble(l_strCheckPrice);
	            }
	            else
                {
	                log.debug("�@@�ˎ����̍Ď擾�����{�B");
	                l_dblCurrentPrice = this.calcCurrentPrice(
	                    WEB3CommisionProductCodeDef.LISTING_STOCK,
	                    l_tradedProduct,
	                    l_subAccount,
	                    true);
                }
            }
            
            // �v�Z�P���̌���
            l_dblReturnCalcPrice = l_dblCurrentPrice;
            log.debug("�@@�擾��������          = [" + l_dblReturnCalcPrice + "] ���擾���Ă��Ȃ��ꍇ��0");
            log.debug("�A����.�w�l             = [" + l_dblLimitPrice + "]");
            log.debug("�B����.�iW�w�l�j�����w�l = [" + l_dblWLimitPrice + "]");
            log.debug("�@@�`�B�̓��A��ԍ����P�����v�Z�P���Ƃ���B");
            
            if (Double.compare(l_dblLimitPrice, l_dblReturnCalcPrice) > 0)
            {
                l_dblReturnCalcPrice = l_dblLimitPrice;
            }
            if (Double.compare(l_dblWLimitPrice, l_dblReturnCalcPrice) > 0)
            {
                l_dblReturnCalcPrice = l_dblLimitPrice;
            }
            
            log.debug("�y���肵���v�Z�P���z = [" + l_dblReturnCalcPrice + "]");
            
            // ����.�萔��.is�w�l�v���p�e�B�̃Z�b�g
            if (l_dblLimitPrice == 0)
            {
                l_commission.setIsLimitPrice(false);
            }
            else
            {
                l_commission.setIsLimitPrice(true);
            }
            
            log.debug("�y����.�萔��.is�w�l�z = [" + l_commission.isLimitPrice() + "]");
        }
        // �����������̏ꍇ or ���ԍρi�����ԍρj���� or ���ԍρi�����ԍρj�����̏ꍇ
        // ���������ƐM�p�ԍςœ���̎d�l�ł���ׁA��ӏ��ɓZ�߂��B
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType) ||
            OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType) ||
            OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            // �������擾����ꍇ
            if (l_dblLimitPrice == 0)
            {
                log.debug("���s�����̏ꍇ");
                // �����̊m�F���P����null�̏ꍇ�͎������擾���Ȃ�
                if (l_strCheckPrice != null)
                {
                    log.debug("�@@�ˈ���.�m�F���P�����v�Z�P���Ƃ��Ďg�p�B");
                    l_dblReturnCalcPrice = Double.parseDouble(l_strCheckPrice);
                }
                else
                {
                    log.debug("�@@�ˎ����̍Ď擾�����{���A�擾�����������v�Z�P���Ƃ��Ďg�p�B");
                    l_dblReturnCalcPrice = this.calcCurrentPrice(
                        WEB3CommisionProductCodeDef.LISTING_STOCK,
                        l_tradedProduct,
                        l_subAccount,
                        false);
                }
                l_commission.setIsLimitPrice(false);
            }
            else
            {
                log.debug("�w�l�����̏ꍇ�A����.�w�l���v�Z�P���Ƃ��Ďg�p�B");
                l_dblReturnCalcPrice = l_dblLimitPrice;
                l_commission.setIsLimitPrice(true);
            }
            
            log.debug("�y���肵���v�Z�P���z = [" + l_dblReturnCalcPrice + "]");
            log.debug("�y����.�萔��.is�w�l�z = [" + l_commission.isLimitPrice() + "]");
        }
        // �V�K�������� or �V�K���������̏ꍇ
        else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            double l_dblCurrentPrice = 0.0D;
            // �������擾����ꍇ
            if (l_dblLimitPrice == 0 ||
                (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType) &&
                    l_dblWLimitPrice == 0) ||
                (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_intEstimatePriceCalcForm &&
                    EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execConditionType))
               )
            {
                log.debug("�������擾����ꍇ");
                // �����̊m�F���P����null�̏ꍇ�͎������擾���Ȃ�
                if (l_strCheckPrice != null)
                {
                    log.debug("�@@�ˈ���.�m�F���P���������Ƃ��Ďg�p�B");
                    l_dblCurrentPrice = Double.parseDouble(l_strCheckPrice);
                }
                else
                {
                    log.debug("�@@�ˎ����̍Ď擾�����{�B");
                    l_dblCurrentPrice = this.calcCurrentPrice(
                        WEB3CommisionProductCodeDef.LISTING_STOCK,
                        l_tradedProduct,
                        l_subAccount,
                        true);
                }
            }
            
            double l_dblCurPriceForMarginShort = 0.0D;
            // �����w�l�̏ꍇ
            if (l_dblLimitPrice > 0 && OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
            {
                log.debug("�����w�l�����̏ꍇ�A��STOP���̎������擾����B");
                // �����̊m�F���P����null�̏ꍇ�͎������擾���Ȃ�
                if (l_strCheckPrice != null)
                {
                    log.debug("�@@�ˈ���.�m�F���P���������Ƃ��Ďg�p�B");
                    l_dblCurPriceForMarginShort = Double.parseDouble(l_strCheckPrice);
                }
                else
                {
                    log.debug("�@@�ˎ����̍Ď擾�����{�B");
                    l_dblCurPriceForMarginShort = this.calcCurrentPrice(
                        WEB3CommisionProductCodeDef.LISTING_STOCK,
                        l_tradedProduct,
                        l_subAccount,
                        false);
                }
            }
            // �v�Z�P���̌���
            l_dblReturnCalcPrice = l_dblCurrentPrice;
            log.debug("�@@�擾��������          = [" + l_dblReturnCalcPrice + "] ���擾���Ă��Ȃ��ꍇ��0");
            log.debug("�A�����w�l��r�p����     = [" + l_dblCurPriceForMarginShort + "] ���擾���Ă��Ȃ��ꍇ��0");
            log.debug("�B����.�w�l             = [" + l_dblLimitPrice + "]");
            log.debug("�C����.�iW�w�l�j�����w�l = [" + l_dblWLimitPrice + "]");
            log.debug("�@@�`�C�̓��A��ԍ����P�����v�Z�P���Ƃ���B");
            
            if (Double.compare(l_dblCurPriceForMarginShort, l_dblReturnCalcPrice) > 0)
            {
                l_dblReturnCalcPrice = l_dblCurPriceForMarginShort;
            }
            if (Double.compare(l_dblLimitPrice, l_dblReturnCalcPrice) > 0)
            {
                l_dblReturnCalcPrice = l_dblLimitPrice;
            }
            if (Double.compare(l_dblWLimitPrice, l_dblReturnCalcPrice) > 0)
            {
                l_dblReturnCalcPrice = l_dblLimitPrice;
            }
            
            log.debug("�y���肵���v�Z�P���z = [" + l_dblReturnCalcPrice + "]");
            
            // ����.�萔��.is�w�l�v���p�e�B�̃Z�b�g
            if (l_dblLimitPrice == 0)
            {
                l_commission.setIsLimitPrice(false);
            }
            else
            {
                l_commission.setIsLimitPrice(true);
            }
            
            log.debug("�y����.�萔��.is�w�l�z = [" + l_commission.isLimitPrice() + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblReturnCalcPrice;
     }
     
    /**
     * (remove�J�z�������P��)<BR>
     * �����̒����P�ʃI�u�W�F�N�g�̃��X�g����A�J�z���̒����P�ʃI�u�W�F�N�g���������A������̃��X�g��ԋp����B<BR>
     * <BR>
     * (1)�����Ώۂ̔���<BR>
     * <BR>
     * �@@�@@�@@�ȉ��A�p�����[�^.�����P�ʈꗗ�̗v�f������Loop�����B<BR>
     * <BR>
     * �@@�@@�@@[���������}�l�[�W��.is�o����܂Œ����P��(�����P��) == false�̏ꍇ]<BR>
     * �@@�@@�@@(�������蒍���̏ꍇ)<BR>
     * �@@�@@�@@�@@�@@���X�g�ɂ��̂܂܎c���B<BR>
     * <BR>
     * �@@�@@�@@[���������}�l�[�W��.is�o����܂Œ����P��(�����P��) == true�̏ꍇ]<BR>
     * �@@�@@�@@(�o����܂Œ����̏ꍇ)<BR>
     * �@@�@@�@@�@@[���񒍕��̏ꍇ]<BR>
     * �@@�@@�@@�@@(�����P��.���񒍕��̒����P��ID == 0�̏ꍇ)<BR>
     * �@@�@@�@@�@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����P��.�����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID<BR>
     * �@@�@@�@@�@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A���g�������ΏۂƂ���B<BR>
     * �@@�@@�@@�@@�@@���J�z��̒��������݂���ׁB<BR>
     * <BR>
     * �@@�@@�@@�@@[�J�z�ϒ����̏ꍇ]<BR>
     * �@@�@@�@@�@@(�����P��.���񒍕��̒����P��ID != 0�̏ꍇ)<BR>
     * �@@�@@�@@�@@�@@���X�g��(�p�����[�^.�����P�ʈꗗ)���������A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�����P��.���񒍕��̒����P��ID == ���X�g���̒����P��.���񒍕��̒����P��ID<BR>
     * �@@�@@�@@�@@�@@�ƂȂ�f�[�^�����݂����ꍇ�́A�쐬�����r���A�ŐV�̒����P�ʈȊO��S�ď����ΏۂƂ���B<BR>
     * �@@�@@�@@�@@�@@���ŐV�̌J�z�����݂̂�\������ׁB<BR>
     * <BR>
     * (2)���X�g����̏����ΏۂƔ��肳�ꂽ�J�z���̒����P�ʃI�u�W�F�N�g���A�����P�ʈꗗ����S�ď�������B<BR>
     * �@@�@@�@@���p�����[�^.�����P�ʃI�u�W�F�N�g�̕��я��͌ڋq�w��̃\�[�g�����ɂ�邽�߁A�����͍Ō�ɓZ�߂čs���K�v������B<BR>
     * <BR>
     * (3)�����ς̒����P�ʈꗗ��ԋp����B<BR>
     * �������P�ʈꗗ�̗v�f����0�ɂȂ����ꍇ��NULL��ԋp����B<BR>
     * <BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * @@return EqTypeOrderUnit[]
     * @@roseuid 40FCA49A022F
     */
    public EqTypeOrderUnit[] removeCarryOverOriginalOrderUnit(
        EqTypeOrderUnit[] l_orderUnitList)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "removeCarryOverOriginalOrderUnit(EqTypeOrderUnit[])";
        log.entering(STR_METHOD_NAME);
        
        int l_intCarryoverOrderUnitCount = 0;
        boolean[] l_blnCarryoverOrderUnit = new boolean[l_orderUnitList.length];
        EqTypeOrderUnit[] l_arrOrderUnit = null;
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        
        for (int i = 0;i < l_orderUnitList.length;i++)
        {
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnitList[i].getDataSourceObject();
            l_blnCarryoverOrderUnit[i] = true;

            if (l_orderManager.isCarriedOrderUnit(l_orderUnitList[i]))
            {
                if (l_orderUnitRow.getFirstOrderUnitId() == 0L)
                {
                    for (int j = 0;j < l_orderUnitList.length;j++)
                    {
                        EqtypeOrderUnitRow l_orderUnitRow1 =
                            (EqtypeOrderUnitRow)l_orderUnitList[j].getDataSourceObject();
                        if (l_orderUnitRow.getOrderUnitId() == l_orderUnitRow1.getFirstOrderUnitId())
                        {
                            l_blnCarryoverOrderUnit[i] = false;
                            l_intCarryoverOrderUnitCount++;
                            break;
                        }
                    }
                }
                else
                {
                    for (int j = 0;j < l_orderUnitList.length;j++)
                    {
                        EqtypeOrderUnitRow l_orderUnitRow1 =
                            (EqtypeOrderUnitRow)l_orderUnitList[j].getDataSourceObject();       
                        if (l_orderUnitRow.getFirstOrderUnitId() == l_orderUnitRow1.getFirstOrderUnitId())
                        {
                            if (l_orderUnitRow1.getCreatedTimestamp().compareTo(l_orderUnitRow.getCreatedTimestamp()) > 0)
                            {
                                l_blnCarryoverOrderUnit[i] = false;
                                l_intCarryoverOrderUnitCount++;
                                break;
                            }                           
                        }
                    }
                }
            }
        }
        int l_intLength = l_orderUnitList.length - l_intCarryoverOrderUnitCount;
        if (l_intLength > 0)
        {
            l_arrOrderUnit =
                new EqTypeOrderUnit[l_intLength];
            int j = 0;
            for (int i = 0; i < l_orderUnitList.length; i++)
            {
                if (l_blnCarryoverOrderUnit[i] == true)
                {
                    l_arrOrderUnit[j] = l_orderUnitList[i];
                    j++;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_arrOrderUnit;

    }

	/**
	 * (get�󔄂�K������)<BR>
	 * <BR>
	 * �󔄂�K�����ԃe�[�u������A<BR>
	 * ����J�����_�R���e�L�X�g�̐ݒ���e�ɍ��v���郌�R�[�h���擾���ԋp����B<BR>
	 * <BR>
	 * �ȉ������ŋ󔄂�K�����ԃe�[�u�����R�[�h���擾����B<BR>
	 * <BR>
	 * �@@[����]<BR>
	 * �@@�،���ЃR�[�h�@@�@@���@@�@@������ԃR���e�L�X�g�D�،���ЃR�[�h�@@and<BR>
	 * �@@���X�R�[�h�@@�@@���@@�@@������ԃR���e�L�X�g�D���X�R�[�h�@@and<BR>
	 * �@@�s��R�[�h�@@���@@������ԃR���e�L�X�g�D�s��R�[�h�@@and<BR>
	 * �@@�c�Ɠ��敪�@@���@@������ԊǗ��Dget�c�Ɠ��敪�@@and<BR>
	 * �@@�J�n���ԁ@@���@@���ݎ����iHHMMSS�j�@@and<BR>
	 * �@@�I�����ԁ@@���@@���ݎ����iHHMMSS�j<BR>
	 * <BR>
	 * �擾�������R�[�h��ԋp����B<BR>
	 * ���Y���f�[�^�Ȃ��̏ꍇ�͗�O��throw����B<BR>
	 * <BR>
	 * @@return ShortSellingRestraintTimeRow
	 * @@throws WEB3BaseException
	 */
	public ShortSellingRestraintTimeRow getShortSellingRestraintTime()
	throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getShortSellingRestraintTime()";
		log.entering(STR_METHOD_NAME);
        
		StringBuffer l_sbWhere = new StringBuffer(" institution_code = ?");
		l_sbWhere.append(" and branch_code = ?");
		l_sbWhere.append(" and market_code = ?");
		l_sbWhere.append(" and biz_date_type = ?");
		l_sbWhere.append(" and start_time <= ?");
		l_sbWhere.append(" and end_time >= ? ");
        
		Object[] l_objWheres = null;
		List l_lisWheres = new ArrayList();
		// ����J�����_�R���e�L�X�g�擾
		WEB3GentradeTradingClendarContext l_tradingClendarContext =
			(WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
		// ���ݎ����擾
		Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();              
        
		l_lisWheres.add(l_tradingClendarContext.getInstitutionCode());
		l_lisWheres.add(l_tradingClendarContext.getBranchCode());
		l_lisWheres.add(l_tradingClendarContext.getMarketCode());
		l_lisWheres.add(WEB3GentradeTradingTimeManagement.getBizDateType(l_tsSystemTime));
		l_lisWheres.add(WEB3DateUtility.formatDate(l_tsSystemTime, "HHmmss"));
		l_lisWheres.add(WEB3DateUtility.formatDate(l_tsSystemTime, "HHmmss"));
		l_objWheres = l_lisWheres.toArray();
            
		List l_lisShortSellingResTime = null;
		try
		{
			QueryProcessor l_queryProc = Processors.getDefaultProcessor();
			l_lisShortSellingResTime = l_queryProc.doFindAllQuery(
				ShortSellingRestraintTimeRow.TYPE,
				l_sbWhere.toString(),
				l_objWheres);
		}
		catch (DataException l_de)
		{
			log.error(l_de.getMessage());
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_de.getMessage());
		}
        
		if (l_lisShortSellingResTime == null || l_lisShortSellingResTime.size() == 0)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80005,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�󔄂�K�����ԃe�[�u���ɊY���f�[�^������܂���");
		}
        
		if (l_lisShortSellingResTime.size() > 1)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80004,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�󔄂�K�����ԃe�[�u���ɏd�����郌�R�[�h�����݂��܂�");
		}
        
		// �󔄂�K�����Ԏ擾
		ShortSellingRestraintTimeRow l_shortSellingResTimeRow =
			(ShortSellingRestraintTimeRow)l_lisShortSellingResTime.get(0);
        
		log.debug("�y���ݎ����z="+WEB3DateUtility.formatDate(l_tsSystemTime, "HHmmss")
			+"�y�󔄂�K�����ԁz="+l_shortSellingResTimeRow.toString());
            
		log.exiting(STR_METHOD_NAME);
		return l_shortSellingResTimeRow;
	}
    
    /**
     * (notifyW�w�l����)<BR>
     * �inotifyWLimitOrder�j<BR>
     * W�w�l�����i�X�g�b�v�����j�̓o�^�A�����A�����<BR>  
     * ���[���G���W���T�[�o�ɒʒm����B<BR>
     * <BR>
     * �P�j�@@���������P�ʂ��ǂ����̔���<BR>
     * �@@�����̒����P��.getDataSourceObject()���R�[������B<BR> 
     * <BR>
     * �@@[���\�b�h�̖߂�l�̌^�����������P��Row�łȂ��ꍇ]<BR>  
     * �@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j<BR>
     * �@@�@@���\�񒍕��P�ʂɑ΂��āAW�w�l�͐ݒ�s�B<BR>
     * <BR>
     * �@@[���������P��Row�ł���ꍇ]<BR>  
     * �@@�@@getOrderUnit().getDataSourceObject()���R�[�����A<BR>  
     * �@@�@@�����P�ʂ��擾�������B<BR>
     * <BR>
     * �Q�j�@@���[���G���W���T�[�o�ւ̒ʒm�v�ۃ`�F�b�N<BR> 
     * <BR>
     * �@@�Q�|�P�j�@@�֖ؑ��ς�W�w�l�����̔���<BR> 
     * �@@�@@�֖ؑ��ς�W�w�l�����łȂ��ꍇ�A  <BR>
     *�@@�@@�i�i�P�j�̖߂�l.�������� = "W�w�l" ����<BR>
     *�@@�@@�@@�g�����������}�l�[�W��.is����������() = true �j�ȊO<BR>
     * �@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j  <BR>
     * <BR>
     *�@@�@@[is����������()�Ɏw�肷�����] <BR>
     *�@@�@@�@@�����P�ʁF�@@����.�����P�� <BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�������̔����x�������̔���<BR> 
     * �@@�@@�g�����������}�l�[�W��.is�������x������() == true�̏ꍇ�A<BR> 
     * �@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j <BR>
     * <BR>
     * �@@�@@[is�������x������()�Ɏw�肷�����]<BR> 
     * �@@�@@�@@�����P�ʁF�@@����.�����P�� <BR>
     * <BR>
     * �R�j�@@�⏕�������擾����B<BR>  
     * �@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B<BR>  
     * <BR>
     * �@@[getSubAccount()�Ɏw�肷�����]<BR>  
     * �@@�@@arg0�F�@@�P�j�̖߂�l.����ID  <BR>
     * �@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID  <BR>
     * <BR>
     * �S�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j<BR>  
     * �@@���擾����B  <BR>
     * <BR>
     * �T�j�@@���[���G���W���T�[�o�ɒʒm���s���B<BR>  
     * �@@�����̏����A�����P��.������Ԃɂ���ĉ��L����������s���B<BR>  
     * <BR>
     * �@@�T�|�P�j���<BR> 
     * �@@�@@�X�g�b�v���������i���� == "ORDER_EXPIRED"�j�A<BR>
     * �@@�@@�܂���SONAR����̎�����s�i���� == "CANCEL_ORDER_REJECTED_BY_MKT"�j<BR>
     * �@@�@@�܂��͎����A�����J�z�X�L�b�v�i���� == "ORDER_INVALIDATED_BY_MKT"�j<BR>
     * �@@�@@�܂��͎����t�ρi�������="��t�ρi��������j"�j�̏ꍇ�A <BR>
     * �@@�@@�܂��͎�������A�S�����A������t�G���[�i�����L�����="�N���[�Y"�j�̏ꍇ<BR> 
     * �@@�@@�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h��  <BR>
     * �@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@[sendCancelConOrderMessage()�Ɏw�肷�����]<BR>  
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕����  <BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"  <BR>
     * �@@�@@�@@�����^�C�v�F�@@"����"  <BR>
     * �@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID<BR>  
     * <BR>
     * �@@�T�|�Q�j�o�^<BR> 
     * �@@�@@�V�K�o�^�i�������="��t�ρi�V�K�����j"�j�̏ꍇ�A<BR> 
     * �@@�@@�擾�����T�[�r�X.sendRegisterConOrdersMessage()���\�b�h��<BR>  
     * �@@�@@�R�[������B <BR>
     * <BR>
     * �@@�@@[sendRegisterConOrdersMessage()�Ɏw�肷�����]<BR>  
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕����  <BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"  <BR>
     * �@@�@@�@@�e�����̖����^�C�v�F�@@"����"  <BR>
     * �@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID<BR>  
     * �@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null  <BR>
     * �@@�@@�@@�q�����̒���ID�ꗗ�F�@@null  <BR>
     * <BR>
     * �@@�T�|�R�j����<BR> 
     * �@@�@@�i�������="��t�ρi�ύX�����j" or "�����ρi�ύX�����j"�j�̏ꍇ<BR>  
     * �@@�@@�擾�����T�[�r�X.sendModifyConOrdersMessage()���\�b�h��  <BR>
     * �@@�@@�R�[������B <BR>
     * �@@�@@���s�ꑗ�M�ϒ����^�s�ꖢ���M�����̒������l���B<BR>  
     * <BR>
     * �@@�@@[sendModifyConOrdersMessage()�Ɏw�肷�����]<BR>  
     * �@@�@@�@@�⏕�����F�@@�擾�����⏕����  <BR>
     * �@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"  <BR>
     * �@@�@@�@@�e�����̖����^�C�v�F�@@"����"  <BR>
     * �@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID<BR>  
     * �@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null  <BR>
     * �@@�@@�@@�q�����̒���ID�ꗗ�F�@@null  <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g�B<BR>
     * @@param l_persistenceContext - (����)<BR>
     * �����B  <BR>
     * �iOrderManagerPersistenceContext�ɂĒ萔��`�j
     * @@throws WEB3BaseException 
     * 
     */
    private void notifyWLimitOrder(
        EqTypeOrderUnit l_orderUnit, 
        OrderManagerPersistenceContext l_persistenceContext) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyWLimitOrder(EqTypeOrderUnit, OrderManagerPersistenceContext)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        //�P�j�@@���������P�ʂ��ǂ����̔���@@  
        //�@@�����̒����P��.getDataSourceObject()���R�[������B 
        //�@@[���\�b�h�̖߂�l�̌^�����������P��Row�łȂ��ꍇ]  
        //�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j  
        //�@@�@@���\�񒍕��P�ʂɑ΂��āAW�w�l�͐ݒ�s�B  
        //�@@[���������P��Row�ł���ꍇ]  
        //�@@�@@getOrderUnit().getDataSourceObject()���R�[�����A  
        //�@@�@@�����P�ʂ��擾�������B  
        Object l_objRow = l_orderUnit.getDataSourceObject();
        if (!(l_objRow instanceof EqtypeOrderUnitRow))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        EqtypeOrderUnitRow l_orderUnitRow = null;
        try 
        {
            l_orderUnitRow = 
                (EqtypeOrderUnitRow)getOrderUnit(
                    l_orderUnit.getOrderUnitId()).getDataSourceObject();
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

        Long l_lngOrderId = new Long(l_orderUnitRow.getOrderId());
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        OrderOpenStatusEnum l_orderOpenStatus = l_orderUnitRow.getOrderOpenStatus();
        
        //�@@�Q�|�P�j�@@�֖ؑ��ς�W�w�l�����̔��� 
        //�@@�@@�֖ؑ��ς�W�w�l�����łȂ��ꍇ�A  
        //�@@�@@�i�i�P�j�̖߂�l.�������� = "W�w�l" ����
        //�@@�@@�@@�g�����������}�l�[�W��.is����������() = true �j�ȊO
        //�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j
        //
        //�@@�@@[is����������()�Ɏw�肷�����]
        //�@@�@@�@@�����P�ʁF�@@����.�����P��
        if (!(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())
            && this.isNotOrderedOrder(l_orderUnit)))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        //�@@�Q�|�Q�j�@@�������̔����x�������̔��� 
        //�@@�@@�g�����������}�l�[�W��.is�������x������() == true�̏ꍇ�A 
        //�@@�@@�����ΏۊO�ł���ׁA�������I������B�ireturn�j 
        //�@@�@@[is�������x������()�Ɏw�肷�����] 
        //�@@�@@�@@�����P�ʁF�@@�@@����.�����P��
        if (this.isNotOrderedDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //�R�j�@@�⏕�������擾����B  
        //�@@�g���A�J�E���g�}�l�[�W��.getSubAccount()���R�[������B  
        //�@@[getSubAccount()�Ɏw�肷�����]  
        //�@@�@@arg0�F�@@�P�j�̖߂�l.����ID  
        //�@@�@@arg1�F�@@�P�j�̖߂�l.�⏕����ID  
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_accountManager.getSubAccount(
                    l_orderUnitRow.getAccountId(),
                    l_orderUnitRow.getSubAccountId());
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
        
        //�S�j�@@���[���G���W���ʒm�T�[�r�X�iWEB3RlsRequestSenderService�j  
        //�@@���擾����B  
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        
        //�T�j�@@���[���G���W���T�[�o�ɒʒm���s���B  
        //�@@�����̏����A�����P��.������Ԃɂ���ĉ��L����������s���B  
        //�@@�T�|�P�j��� 
        //�@@�@@�X�g�b�v���������i���� == "ORDER_EXPIRED"�j�A
        //�@@�@@�܂���SONAR����̎�����s�i���� == "CANCEL_ORDER_REJECTED_BY_MKT"�j
        //�@@�@@�܂��͎����A�����J�z�X�L�b�v�i���� == "ORDER_INVALIDATED_BY_MKT"�j
        //�@@�@@�܂��͎����t�ρi�������="��t�ρi��������j"�j�̏ꍇ�A 
        //�@@�@@�܂��͎�������A�S�����A������t�G���[�i�����L�����="�N���[�Y"�j�̏ꍇ 
        //�@@�@@�擾�����T�[�r�X.sendCancelConOrderMessage()���\�b�h��  
        //�@@�@@�R�[������B 
        //�@@�@@[sendCancelConOrderMessage()�Ɏw�肷�����]  
        //�@@�@@�@@�⏕�����F�@@�擾�����⏕����  
        //�@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"  
        //�@@�@@�@@�����^�C�v�F�@@"����"  
        //�@@�@@�@@����ID�F�@@�P�j�̖߂�l.����ID  
        if (OrderManagerPersistenceContext.ORDER_EXPIRED.equals(l_persistenceContext) 
            || OrderManagerPersistenceContext.CANCEL_ORDER_REJECTED_BY_MKT.equals(l_persistenceContext)
            || OrderManagerPersistenceContext.ORDER_INVALIDATED_BY_MKT.equals(l_persistenceContext)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
            || OrderOpenStatusEnum.CLOSED.equals(l_orderOpenStatus))
        {
            l_rlsRequestSenderService.sendCancelConOrderMessage(
                l_subAccount, 
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.EQUITY,
                l_lngOrderId);
        }
        
        //�@@�T�|�Q�j�o�^ 
        //�@@�@@�V�K�o�^�i�������="��t�ρi�V�K�����j"�j�̏ꍇ�A 
        //�@@�@@�擾�����T�[�r�X.sendRegisterConOrdersMessage()���\�b�h��  
        //�@@�@@�R�[������B 
        //�@@�@@[sendRegisterConOrdersMessage()�Ɏw�肷�����]  
        //�@@�@@�@@�⏕�����F�@@�擾�����⏕����  
        //�@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"  
        //�@@�@@�@@�e�����̖����^�C�v�F�@@"����"  
        //�@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID  
        //�@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null  
        //�@@�@@�@@�q�����̒���ID�ꗗ�F�@@null  
        else if (OrderStatusEnum.ACCEPTED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendRegisterConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.EQUITY,
                l_lngOrderId,
                null,
                null);
        }
        
        //�@@�T�|�R�j���� 
        //�@@�@@�i�������="��t�ρi�ύX�����j" or "�����ρi�ύX�����j"�j�̏ꍇ  
        //�@@�@@�擾�����T�[�r�X.sendModifyConOrdersMessage()���\�b�h��  
        //�@@�@@�R�[������B 
        //�@@�@@���s�ꑗ�M�ϒ����^�s�ꖢ���M�����̒������l���B  
        //�@@�@@[sendModifyConOrdersMessage()�Ɏw�肷�����]  
        //�@@�@@�@@�⏕�����F�@@�擾�����⏕����  
        //�@@�@@�@@�����t�����^�C�v�F�@@"W�w�l"  
        //�@@�@@�@@�e�����̖����^�C�v�F�@@"����"  
        //�@@�@@�@@�e�����̒���ID�F�@@�P�j�̖߂�l.����ID  
        //�@@�@@�@@�q�����̖����^�C�v�ꗗ�F�@@null  
        //�@@�@@�@@�q�����̒���ID�ꗗ�F�@@null  
        else if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) 
            || OrderStatusEnum.MODIFIED.equals(l_orderStatus))
        {
            l_rlsRequestSenderService.sendModifyConOrdersMessage(
                l_subAccount,
                WEB3RlsNotifyOrderTypeDef.W_LlIMIT,
                ProductTypeEnum.EQUITY,
                l_lngOrderId,
                null,
                null);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is�X�g�b�v�����L��)<BR>
     * OCO�iW�w�l�j�����̃X�g�b�v�������L�����ǂ������ʂ���B<BR>
     * <BR>
     * this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B<BR>
     * <BR>
     * [is�X�g�b�v�����L��()�Ɏw�肷�����]<BR>
     * �@@���������F�@@��������(*1)<BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v<BR>
     * <BR>
     * (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B<BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������<BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        EqtypeOrderUnitRow l_orderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //(*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_orderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
        
    }
    
    /**
     * (is�X�g�b�v�����L��)<BR>
     * OCO�iW�w�l�j�����̃X�g�b�v�������L�����ǂ������ʂ���B <BR> 
     * <BR>
     * this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B  <BR>
     * <BR>
     * [is�X�g�b�v�����L��()�Ɏw�肷�����]  <BR>
     * �@@���������F�@@��������(*1)  <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.��������.���N�G�X�g�^�C�v  <BR>
     * <BR>
     * (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B  <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������  <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * @@param l_orderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(EqTypeOrderAction l_orderAction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderActionRow l_orderActionRow = 
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        
        // (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_orderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
        
    }
    
    /**
     * (is�X�g�b�v�����L��)<BR>
     * OCO�iW�w�l�j�����̃X�g�b�v�������L�����ǂ������ʂ���B  <BR>
     * ���������� == "W�w�l"�łȂ��ꍇ�́A  <BR>
     * �@@�ꗥfalse��ԋp����B  <BR>
     * <BR>
     * �P�j�@@W�w�l���ǂ����̃`�F�b�N  <BR>
     * �@@�p�����[�^.�������� != "W�w�l"�̏ꍇ�A  <BR>
     * �@@false��ԋp����B  <BR>
     * <BR>
     * �Q�j�@@�X�g�b�v�������L�����ǂ����̃`�F�b�N  <BR>
     * �@@�p�����[�^.���N�G�X�g�^�C�v == "�ؑ֊���"�̏ꍇ�A  <BR>
     * �@@true��ԋp����B  <BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * @@param l_strRequestType - (���N�G�X�g�^�C�v)<BR>
     * ���N�G�X�g�^�C�v<BR>
     * @@return boolean
     */
    private boolean isStopOrderValid(String l_strOrderConditionType, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderValid(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@W�w�l���ǂ����̃`�F�b�N
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�X�g�b�v�������L�����ǂ����̃`�F�b�N 
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
        
    }
    
    /**
     * (is�X�g�b�v�����ؑ֒�)<BR>
     * OCO�iW�w�l�j�������X�g�b�v�����ɐؑ֒����ǂ����𔻕ʂ���B  <BR>
     * <BR>
     * this.is�X�g�b�v�����ؑ֒�()�ɏ������Ϗ�����B  <BR>
     * <BR>
     * [is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]  <BR>
     * �@@���������F�@@��������(*1)  <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v  <BR>
     * <BR>
     * (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B  <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������  <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.����������<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //(*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_orderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
    
    /**
     * (is�X�g�b�v�����ؑ֒�)<BR>
     * OCO�iW�w�l�j�������X�g�b�v�����ɐؑ֒����ǂ����𔻕ʂ���B  <BR>
     * <BR>
     * this.is�X�g�b�v�����ؑ֒�()�ɏ������Ϗ�����B  <BR>
     * <BR>
     * [is�X�g�b�v�����ؑ֒�()�Ɏw�肷�����]  <BR>
     * �@@���������F�@@��������(*1)  <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.��������.���N�G�X�g�^�C�v  <BR>
     * <BR>
     * (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B  <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������  <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.����������<BR>
     * @@param l_orderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(EqTypeOrderAction l_orderAction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderActionRow l_orderActionRow = 
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        
        // (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_orderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
    
    /**
     * (is�X�g�b�v�����ؑ֒�)<BR>
     * OCO�iW�w�l�j�������X�g�b�v�����ɐؑ֒����ǂ����𔻕ʂ���B  <BR>
     * ���������� == "W�w�l"�łȂ��ꍇ�́A  <BR>
     * �@@�ꗥfalse��ԋp����B  <BR>
     * <BR>
     * �P�j�@@W�w�l���ǂ����̃`�F�b�N  <BR>
     * �@@�p�����[�^.�������� != "W�w�l"�̏ꍇ�A  <BR>
     * �@@false��ԋp����B  <BR>
     * <BR>
     * �Q�j�@@�X�g�b�v�����ɐؑ֒����ǂ����̃`�F�b�N  <BR>
     * �@@�p�����[�^.�����P��.���N�G�X�g�^�C�v == "�����T�[�o"�̏ꍇ�A  <BR>
     * �@@true��ԋp����B  <BR>
     * �@@�ȊO�Afalse��ԋp����B<BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * @@param l_strRequestType - (���N�G�X�g�^�C�v)<BR>
     * ���N�G�X�g�^�C�v<BR>
     * @@return boolean
     */
    private boolean isStopOrderSwitching(String l_strOrderConditionType, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@W�w�l���ǂ����̃`�F�b�N
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j�@@�X�g�b�v�������L�����ǂ����̃`�F�b�N 
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //�ȊO�Afalse��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is�X�g�b�v����������)<BR>
     * OCO�iW�w�l�j�����̃X�g�b�v�����������ς��ǂ������ʂ���B  <BR>
     * <BR>
     * this.is�X�g�b�v����������()�ɏ������Ϗ�����B  <BR>
     * <BR>
     * [is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@���������F�@@��������(*1)  <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v  <BR>
     * <BR>
     * (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B  <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.�����P��.��������  <BR>
     * �@@�@@�����������F�@@�p�����[�^.�����P��.���������� <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //(*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        
        //this.is�X�g�b�v�����L��()�ɏ������Ϗ�����B
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_orderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    }
    
    /**
     * (is�X�g�b�v����������)<BR>
     * OCO�iW�w�l�j�����̃X�g�b�v�����������ς��ǂ������ʂ���B  <BR>
     * <BR>
     * this.is�X�g�b�v����������()�ɏ������Ϗ�����B  <BR>
     * <BR>
     * [is�X�g�b�v����������()�Ɏw�肷�����]  <BR>
     * �@@���������F�@@��������(*1)  <BR>
     * �@@���N�G�X�g�^�C�v�F�@@�p�����[�^.��������.���N�G�X�g�^�C�v  <BR>
     * <BR>
     * (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B  <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]  <BR>
     * �@@�@@���������F�@@�p�����[�^.��������.��������  <BR>
     * �@@�@@�����������F�@@�p�����[�^.��������.���������� <BR>
     * @@param l_orderAction - (��������)<BR>
     * ���������I�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(EqTypeOrderAction l_orderAction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderAction == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderActionRow l_orderActionRow = 
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        
        // (*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����
        //�@@[get��������()�Ɏw�肷�����]  
        //�@@�@@���������F�@@�p�����[�^.��������.��������  
        //�@@�@@�����������F�@@�p�����[�^.��������.���������� 
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        
        //[is�X�g�b�v����������()�Ɏw�肷�����]  
        //�@@���������F�@@��������(*1)  
        //�@@���N�G�X�g�^�C�v�F�@@�p�����[�^.��������.���N�G�X�g�^�C�v  
        //(*1)���������́A�����f�[�^�A�_�v�^.get��������()�ɂ��擾����B  
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_orderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    }
    
    /**
     * (is�X�g�b�v����������)<BR>
     * OCO�iW�w�l�j�����̃X�g�b�v�����������ς��ǂ������ʂ���B  <BR>
     * ���������� == "W�w�l"�łȂ��ꍇ�́A  <BR>
     * �@@�ꗥfalse��ԋp����B  <BR>
     * <BR>
     * �P�j�@@W�w�l���ǂ����̃`�F�b�N  <BR>
     * �@@�p�����[�^.�������� != "W�w�l"�̏ꍇ�A  <BR>
     * �@@false��ԋp����B  <BR>
     * <BR>
     * �Q�j�@@�X�g�b�v�����������ς��ǂ����̃`�F�b�N  <BR>
     * �@@�p�����[�^.���N�G�X�g�^�C�v == "����"�̏ꍇ�A  <BR>
     * �@@true��ԋp����B  <BR>
     * �@@�ȊO�Afalse��ԋp����B  <BR>
     * @@param l_strOrderConditionType - (��������)<BR>
     * ��������<BR>
     * @@param l_strRequestType - (���N�G�X�g�^�C�v)<BR>
     * ���N�G�X�g�^�C�v<BR>
     * @@return boolean
     */
    private boolean isStopOrderExpired(String l_strOrderConditionType, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(String, String)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@W�w�l���ǂ����̃`�F�b�N  
        //�@@�p�����[�^.�������� != "W�w�l"�̏ꍇ�A  
        //�@@false��ԋp����B  
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //�Q�j�@@�X�g�b�v�����������ς��ǂ����̃`�F�b�N  
        //�@@�p�����[�^.���N�G�X�g�^�C�v == "����"�̏ꍇ�A  
        //�@@true��ԋp����B  
        //�@@�ȊO�Afalse��ԋp����B  
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is�蓮�����\)<BR>
     * �������蓮�����\�ł��邩�𔻕ʂ���B  <BR>
     * �����������w��Ȃ��̏ꍇ�́A�ꗥfalse��ԋp����B  <BR>
     * <BR>
     * �P�j�g���K�[�������ǂ����̃`�F�b�N  <BR>
     * <BR>
     * �@@�p�����[�^.�����P��.�������� == "DEFAULT(�����w��Ȃ�)"�̏ꍇ�A  <BR>
     * �@@false��ԋp����B  <BR>
     * �@@(�蓮�����ΏۊO)  <BR>
     * <BR>
     * �Q�j�t�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "�t�w�l")  <BR>
     * <BR>
     * �@@�y�����҂��^�����x���z <BR>
     * �@@�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v == "DEFAULT"�A���A  <BR>
     * �@@�@@�p�����[�^.�����P��.�����P��.�����L����� == "�I�[�v��"�A���A  <BR>
     * �@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�() == true�̏ꍇ�Atrue��ԋp����B <BR>
     * �@@�@@�ȊO�Afalse��ԋp����B <BR>
     * <BR>
     * �@@�������x���i�����x�������łȂ���Ёj�̏ꍇ���A��L��ԂɊY������B <BR>
     * �@@ �@@�����x�������̉�Ђ͔����x�����ɂ͎蓮�����s�B <BR>
     * �@@�@@ �i�����ςł���ׁj <BR>
     * <BR>
     * �R�jW�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "W�w�l")  <BR>
     * <BR>
     * �@@�y�ؑ֒x���z <BR>
     * �@@�@@this.is�������x������(�p�����[�^.�����P��) == true�A���� <BR>
     * �@@�@@�p�����[�^.�����P��.�����P��.�����L����� == "�I�[�v��"�A���A  <BR>
     * �@@�@@�p�����[�^.�����P��.������� != �i"��t�ρi��������j"�A"�������i��������j")�A���A  <BR>
     * �@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�() == true�̏ꍇ�Atrue��ԋp����B  <BR>
     * �@@�@@�ȊO�Afasle��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isManualOrderPossible(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isManualOrderPossible(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        boolean l_blnRetrun = false;
        //������ԊǗ�.is�s��J�ǎ��ԑ�()
        boolean l_blnOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        
        //�P�j�g���K�[�������ǂ����̃`�F�b�N  
        //�@@�p�����[�^.�����P��.�������� == "DEFAULT(�����w��Ȃ�)"�̏ꍇ�A  
        //�@@false��ԋp����B        
        //�@@(�蓮�����ΏۊO) 
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_blnRetrun = false;
        }
        
        //�Q�j�t�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "�t�w�l")  
        //�@@�y�����҂��^�����x���z   
        //�@@�@@�p�����[�^.�����P��.���N�G�X�g�^�C�v == "DEFAULT"�A���A  
        //�@@�@@�p�����[�^.�����P��.�����P��.�����L����� == "�I�[�v��"�A���A  
        //�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�() == true�̏ꍇ�Atrue��ԋp����B 
        //�@@�@@�ȊO�Afalse��ԋp����B 
        //�@@�������x���i�����x�������łȂ���Ёj�̏ꍇ���A��L��ԂɊY������B 
        //�@@ �@@�����x�������̉�Ђ͔����x�����ɂ͎蓮�����s�B 
        //�@@�@@ �i�����ςł���ׁj    
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            if (WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType())
                && OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus())
                && l_blnOpenTimeZone)
            {
                l_blnRetrun = true;
            }
            else
            {
                l_blnRetrun = false;
            }

        }
        
        //�R�jW�w�l�����̏ꍇ(�p�����[�^.�����P��.�������� == "W�w�l")  
        //�@@�y�ؑ֒x���z             
        //�@@�@@this.is�������x������(�p�����[�^.�����P��) == true�A���� 
        //�@@�@@�p�����[�^.�����P��.�����P��.�����L����� == "�I�[�v��"�A���A  
        //�@@�@@�p�����[�^.�����P��.������� != �i"��t�ρi��������j"�A"�������i��������j")�A���A  
        //�@@�@@������ԊǗ�.is�s��J�ǎ��ԑ�() == true�̏ꍇ�Atrue��ԋp����B  
        //�@@�@@�ȊO�Afasle��ԋp����B
        OrderStatusEnum l_orderStatus = l_orderUnitRow.getOrderStatus();
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType()))
        {
            if (this.isNotOrderedDelayOrder(l_orderUnit)
                && OrderOpenStatusEnum.OPEN.equals(l_orderUnitRow.getOrderOpenStatus())
                && !(OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatus)
                || OrderStatusEnum.CANCELLING.equals(l_orderStatus))
                && l_blnOpenTimeZone)
            {
                l_blnRetrun = true;
            }
            else
            {
                l_blnRetrun = false;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnRetrun;
    }

    /**
     * (update�ؑ֒x��)<BR>
     * �iupdateSwitchDelay�j  <BR>
     * �w�肳�ꂽ����ID�ɕR�t�������f�[�^�ނ�ؑ֒x���̃X�e�[�^�X�ɍX�V����B  <BR>
     * <BR>
     * �P�j�@@�p�����[�^.����ID�ɕR�t�������P�ʂ��擾����B  <BR>
     * <BR>
     * �Q�j�@@������ԃ`�F�b�N <BR>
     * �@@�@@�ؑ֏������ρithis.is����������(�����P��) == true�j���� <BR>
     * �@@�@@�i�s�ꖢ���M(*)�̏ꍇ�A�܂��́A <BR>
     * �@@�@@�擾���������P��.������Ԃ��ȉ������ꂩ�ɊY������ꍇ�j�A<BR>
     * �@@�@@�u��t���^�������^������̒����͐ؑ֏����s�v�̗�O���X���[����B <BR>
     * <BR>
     * �@@�@@�@@�@@�E"��t�ρi�ύX�����j" <BR>
     * �@@�@@�@@�@@�E"�������i�ύX�����j" <BR>
     * �@@�@@�@@�@@�E"��t�ρi��������j" <BR>
     * �@@�@@�@@�@@�E"�������i��������j" <BR>
     *�@@�@@�@@ class:WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag  :BUSINESS_ERROR_02521<BR>
     * <BR>
     * �@@�@@(*)�����P��.�s�ꂩ��m�F�ς݂̐��� == NaN�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�s�ꖢ���M�̒����Ɣ��肷��B <BR>
     * <BR>
     * �R�j�@@�P�j�̒����P�ʂ�clone���쐬����B  <BR>
     * <BR>
     * �S�j�@@�R�j�ɂč쐬����clone�ɑ΂��A�X�V�l���Z�b�g����B <BR>
     * <BR>
     * �@@DB�X�V�d�l  <BR>
     * �@@�uW�w�l�����ؑցi�ؑ֒x���j_���������P�ʃe�[�u��.xls�v�Q�ƁB  <BR>
     * <BR>
     * �T�j�@@this.create��������()���R�[������B<BR>
     * <BR>
     * [����] <BR>
     * �@@�����P�ʁF�@@�S�j�ɂč쐬���������P��<BR>
     * �@@�C�x���g�^�C�v�F�@@"22�F�ؑ֒x��"<BR> 
     * <BR>
     * �@@��DB�X�V�d�l�i�Q�l�j<BR>
     * �@@�uW�w�l�����ؑցi�ؑ֒x���j_���������e�[�u���d�l.xls�v�Q�ƁB<BR>
     * <BR>
     * �U�j�@@this.update�����f�[�^()���R�[������B<BR>
     * <BR>
     * [����]<BR>
     * �@@�����P�ʁF�@@�S�j�ɂč쐬���������P��<BR>
     * �@@���������F�@@create��������()�̖߂�l<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID
     * @@throws WEB3BaseException
     */
    public void updateSwitchDelay(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSwitchDelay(long)";
        log.entering(STR_METHOD_NAME);
        //�P�j�@@�p�����[�^.����ID�ɕR�t�������P�ʂ��擾����B
        OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);
        if (l_orderUnits.length == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if (l_orderUnits.length > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        EqTypeOrderUnit l_eqtypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
        //�Q�j�@@������ԃ`�F�b�N
        OrderStatusEnum l_orderStatusEnum = l_eqtypeOrderUnit.getOrderStatus();
        if (this.isNotOrderedOrder(l_eqtypeOrderUnit)
            && (Double.isNaN(l_eqtypeOrderUnit.getConfirmedQuantity())
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatusEnum)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatusEnum)))
        {
            log.debug("��t���^�������^������̒����͐ؑ֏����s�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                getClass().getName() + STR_METHOD_NAME,
                "��t���^�������^������̒����͐ؑ֏����s�B");
        }

        //�R�j�@@�P�j�̒����P�ʂ�clone���쐬����B
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams = new EqtypeOrderUnitParams(l_orderUnitRow);

        //�S�j�@@�R�j�ɂč쐬����clone�ɑ΂��ADB�X�V�d�l�ɂ����čX�V�l���Z�b�g����B
        Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
        l_orderUnitParams.setLastOrderActionSerialNo(
            l_orderUnitParams.getLastOrderActionSerialNo() + 1);
        l_orderUnitParams.setLastUpdatedTimestamp(l_tsSysTime);
        l_orderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);

        //�T�j�@@this.create��������()���R�[������B
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)this.toOrderUnit(l_orderUnitParams);
        EqTypeOrderAction l_orderAction =
            (EqTypeOrderAction)this.createOrderAction(l_orderUnit, OrderEventTypeEnum.SWITCH_DELAY);

        //�U�j�@@this.update�����f�[�^()���R�[������B
        this.updateOrderData(l_orderUnit, l_orderAction);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isSONAR���)<BR>
     * SONAR����̎���ʒm�̏ꍇ��true���A  <BR>
     * �ȊO�Afalse��ԋp����B  <BR>
     * <BR>
     * �p�����[�^.�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������  <BR>
     * �ꍇ�Afalse��ԋp����B  <BR>
     * �@@�E"��t�ρi��������j"  <BR>
     * �@@�E"�������i��������j"  <BR>
     * <BR>
     * ��L�ȊO�Atrue��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isSONARCancel(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSONARCancel(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //�p�����[�^.�����P��.������Ԃ��ȉ��̂����ꂩ�ɊY������  
        //�ꍇ�Afalse��ԋp����B  
        //�@@�E"��t�ρi��������j"  
        //�@@�E"�������i��������j"  
        if ((OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()))
            || (OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus())))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //��L�ȊO�Atrue��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (is�x������)<BR>
     * �x���������ǂ������ʂ���B <BR>
     * <BR>
     * true�F�@@�����x������ <BR>
     * false�F�@@�����x���Ȃ� <BR>
     * <BR>
     * �p�����[�^.�����P��.�����x���t���O == "�x������"�̏ꍇ�A <BR>
     * true��ԋp����B�ȊO�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isDelayOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDelayOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //�p�����[�^.�����P��.�����x���t���O == "�x������"�̏ꍇ�A 
        //true��ԋp����B�ȊO�Afalse��ԋp����B
        if (BooleanEnum.TRUE.equals(l_orderUnitRow.getSubmitOrderDelayFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is�������x������)<BR>
     * �������i�t�w�l���������^W�w�l�ؑ֏������ρj�� <BR>
     * �����x���������ǂ������ʂ���B <BR>
     * <BR>
     * true�F�@@�������̔����x������ <BR>
     * false�F�@@�������̔����x�������ȊO <BR>
     * <BR>
     * this.is����������(�p�����[�^.�����P��) == true�@@���� <BR>
     * this.is�x������(�p�����[�^.�����P��) == true <BR>
     * �̏ꍇ�Atrue��ԋp����B <BR>
     * �ȊO�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isNotOrderedDelayOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotOrderedDelayOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //this.is����������(�p�����[�^.�����P��) == true�@@���� 
        //this.is�x������(�p�����[�^.�����P��) == true 
        //�̏ꍇ�Atrue��ԋp����B 
        //�ȊO�Afalse��ԋp����B
        if (this.isNotOrderedOrder(l_orderUnit) && this.isDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is������)<BR>
     * ���������������ǂ����𔻒肷��B <BR>
     * <BR>
     * �������������̏ꍇ�Atrue��ԋp���A <BR>
     * �������������̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �P�j�@@���������ǂ����̃`�F�b�N <BR>
     * �@@�p�����[�^.�����P��.getSide()�̖߂�l == SideEnum.BUY�̏ꍇ�A <BR>
     * �@@true��ԋp����B <BR>
     * <BR>
     * �@@�p�����[�^.�����P��.getSide()�̖߂�l == SideEnum.SELL�̏ꍇ�A <BR>
     * �@@false��ԋp����B <BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isBuyOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isBuyOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        
        boolean l_blnIsBuyOrder = false;
        
        //�P�j�@@���������ǂ����̃`�F�b�N 
        //�@@�p�����[�^.�����P��.getSide()�̖߂�l == SideEnum.BUY�̏ꍇ�A 
        //�@@true��ԋp����B 
        if (SideEnum.BUY.equals(l_orderUnit.getSide()))
        {
            l_blnIsBuyOrder = true;
        }
        
        //�@@�p�����[�^.�����P��.getSide()�̖߂�l == SideEnum.SELL�̏ꍇ�A 
        //�@@false��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_blnIsBuyOrder;
    }
    
    /**
     * (is������)<BR>
     * ���������������ǂ����𔻒肷��B <BR>
     * <BR>
     * �������������̏ꍇ�Atrue��ԋp���A <BR>
     * �������������̏ꍇ�Afalse��ԋp����B <BR>
     * <BR>
     * �P�j�@@���������ǂ����̃`�F�b�N <BR>
     * �@@this.is������(�����P��)��call����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�p�����[�^.�����P�� <BR>
     * <BR>
     * �Q�j�@@�߂�l�̔��� <BR>
     * �@@[�P�j�̖߂�l��false�̏ꍇ] <BR>
     * �@@�@@true��ԋp����B <BR>
     * <BR>
     * �@@[�P�j�̖߂�l��true�̏ꍇ] <BR>
     * �@@�@@false��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isSellOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSellOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsSellOrder = true; 
        //�P�j�@@���������ǂ����̃`�F�b�N 
        //this.is������(�����P��)��call����B
        if (this.isBuyOrder(l_orderUnit))
        {
            //�Q�j�@@�߂�l�̔��� 
            //�@@[�P�j�̖߂�l��true�̏ꍇ] 
            //�@@�@@false��ԋp����B
            l_blnIsSellOrder = false;
        }
        else
        {
            //�@@[�P�j�̖߂�l��false�̏ꍇ] 
            //�@@�@@true��ԋp����B 
            l_blnIsSellOrder = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsSellOrder;
    }
    
    /**
     * (is����������)<BR>
     * �������i�t�w�l���������^W�w�l�ؑ֏������ρj�̒��������肷��B <BR>
     * <BR>
     * true�F�@@�������̒��� <BR>
     * false�F�@@�����ς̒��� <BR>
     * <BR>
     * �p�����[�^.�����P��.���N�G�X�g�^�C�v�� <BR>
     * �ȉ��ɊY������ꍇ�Atrue��ԋp����B <BR>
     * �@@�@@�E"DEFAULT" <BR>
     * <BR>
     * ��L�ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P�ʃI�u�W�F�N�g<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isNotOrderedOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotOrderedOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "�p�����[�^�l�s���B");
        }
        //�p�����[�^.�����P��.���N�G�X�g�^�C�v�� 
        //�ȉ��ɊY������ꍇ�Atrue��ԋp����B
        //�@@�@@�E"DEFAULT"
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        if (WEB3RequestTypeDef.DEFAULT.equals(l_orderUnitRow.getRequestType()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (create��������)<BR>
     * �p�����[�^.�����P�ʃI�u�W�F�N�g���g�p���A���������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �P�j�@@��������Params�𐶐����A�ȉ��X�V���e��ݒ肷��B<BR>
     * <BR>
     * �@@�@@�����C�x���g�^�C�v�F�@@�p�����[�^.�C�x���g�^�C�v<BR>
     * �@@�@@��萔�ʁF�@@null<BR>
     * �@@�@@���P���F�@@null<BR>
     * �@@�@@�����P���F�@@�����P��.�w�l<BR>
     * �@@�@@���������X�e�[�^�X�F�@@�����P��.�����敪<BR>
     * �@@�@@��������ԍ��F�@@�����P��.���������ŏI�ʔ�<BR>
     * �@@�@@�����G���[���R�R�[�h�F�@@"0000"�i����j<BR>
     * �@@�@@IP�A�h���X�F�@@null<BR>
     * �@@�@@�쐬���t�F�@@���ݓ���<BR>
     * �@@�@@�X�V���t�F�@@���ݓ���<BR>
     * <BR>
     * �@@�@@����L�ȊO�̍��ڂɂ��ẮA<BR>
     * �@@�@@�����P�ʃI�u�W�F�N�g�̓������ڂ̒l��ݒ�B<BR>
     * <BR>
     * �Q�j�@@�g�����������}�l�[�W��.toOrderAction()���R�[�����A<BR>
     * �@@�@@�@@���������I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * �@@�@@[toOrderAction()�̈���]<BR>
     * �@@�@@Row�F�@@��������Params<BR>
     * <BR>
     * �R�j�@@���������I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_eqTypeOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_orderEventType - (�C�x���g�^�C�v)<BR>
     * �C�x���g�^�C�v<BR>
     * @@return EqTypeOrderAction
     * @@throws WEB3BaseException
     */
    public EqTypeOrderAction createOrderAction(
        EqTypeOrderUnit l_eqTypeOrderUnit,
        OrderEventTypeEnum l_orderEventType)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOrderAction(EqTypeOrderUnit, OrderEventTypeEnum)";
        log.entering(STR_METHOD_NAME);
        if (l_eqTypeOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@��������Params�𐶐����A�ȉ��X�V���e��ݒ肷��B
        EqtypeOrderActionParams l_orderActionParams = new EqtypeOrderActionParams();
        EqtypeOrderUnitRow  l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        //�����C�x���g�^�C�v�F�@@�p�����[�^.�C�x���g�^�C�v
        l_orderActionParams.setOrderEventType(l_orderEventType);
        //��萔�ʁF�@@null
        l_orderActionParams.setExecutedQuantity(null);
        //�@@���P���F�@@null
        l_orderActionParams.setExecutedPrice(null);
        //�@@�����P���F�@@�����P��.�w�l
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_orderActionParams.setPrice(null);
        }
        else
        {
            l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
        }
        //�@@���������X�e�[�^�X�F�@@�����P��.�����敪
        l_orderActionParams.setExpirationStatus(l_orderUnitRow.getExpirationStatus());
        //�@@��������ԍ��F�@@�����P��.���������ŏI�ʔ�
        l_orderActionParams.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
        //�@@�����G���[���R�R�[�h�F�@@"0000"�i����j
        l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        //�@@IP�A�h���X�F�@@null
        l_orderActionParams.setIpAddress(null);
        Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
        //�@@�쐬���t�F�@@���ݓ���
        l_orderActionParams.setCreatedTimestamp(l_tsSysTime);
        //�@@�X�V���t�F�@@���ݓ���
        l_orderActionParams.setLastUpdatedTimestamp(l_tsSysTime);

        //��L�ȊO�̍��ڂɂ��ẮA�����P�ʃI�u�W�F�N�g�̓������ڂ̒l��ݒ�B
        //���������h�c
        try
        {
            long l_lngOrderActionId = EqtypeOrderActionDao.newPkValue();
            l_orderActionParams.setOrderActionId(l_lngOrderActionId);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.toString(),
                l_ex);
        }
        //�����h�c
        l_orderActionParams.setAccountId(l_orderUnitRow.getAccountId());
        //�⏕�����h�c
        l_orderActionParams.setSubAccountId(l_orderUnitRow.getSubAccountId());
        //����҂h�c
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_orderActionParams.setTraderId(null);
        }
        else
        {
            l_orderActionParams.setTraderId(l_orderUnitRow.getTraderId());
        }
        //�����h�c
        l_orderActionParams.setOrderId(l_orderUnitRow.getOrderId());
        //�����P�ʂh�c
        l_orderActionParams.setOrderUnitId(l_orderUnitRow.getOrderUnitId());
        //�s��h�c
        if (l_orderUnitRow.getMarketIdIsNull())
        {
            l_orderActionParams.setMarketId(null);
        }
        else
        {
            l_orderActionParams.setMarketId(l_orderUnitRow.getMarketId());
        }
        //�������
        l_orderActionParams.setOrderType(l_orderUnitRow.getOrderType());
        //���s����
        l_orderActionParams.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType());
        //�l�i����
        l_orderActionParams.setPriceConditionType(l_orderUnitRow.getPriceConditionType());
        //��������
        l_orderActionParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
        //�����������Z�q
        l_orderActionParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
        //�t�w�l��l
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_orderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setStopOrderPrice(l_orderUnitRow.getStopOrderPrice());
        }
        //�iW�w�l�j�����w�l
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitPrice(l_orderUnitRow.getWLimitPrice());
        }
        //�����������t
        l_orderActionParams.setExpirationDate(l_orderUnitRow.getExpirationDate());
        //��������
        l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
        //�s��Ɗm�F�ς݂̎w�l
        if (l_orderUnitRow.getConfirmedPriceIsNull())
        {
            l_orderActionParams.setConfirmedPrice(null);
        }
        else
        {
            l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
        }
        //�s��Ɗm�F�ς݂̐���
        if (l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            l_orderActionParams.setConfirmedQuantity(null);
        }
        else
        {
            l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
        }
        //�������
        l_orderActionParams.setOrderStatus(l_orderUnitRow.getOrderStatus());
        //�����^�C�v
        l_orderActionParams.setProductType(l_orderUnitRow.getProductType());
        //�����h�c
        l_orderActionParams.setProductId(l_orderUnitRow.getProductId());
        //�������ʃ^�C�v
        l_orderActionParams.setQuantityType(l_orderUnitRow.getQuantityType());
        //�T�Z��n���
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_orderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_orderActionParams.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }
        //���������E����敪
        l_orderActionParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());
        //�����o�H�敪
        l_orderActionParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
        //���Ϗ����敪
        l_orderActionParams.setClosingOrderType(l_orderUnitRow.getClosingOrderType());
        //���N�G�X�g�^�C�v
        l_orderActionParams.setRequestType(l_orderUnitRow.getRequestType());
        //����������
        l_orderActionParams.setOrgOrderConditionType(l_orderUnitRow.getOrgOrderConditionType());
        //�������������Z�q
        l_orderActionParams.setOrgOrderCondOperator(l_orderUnitRow.getOrgOrderCondOperator());
        //���t�w�l��l
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_orderActionParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgStopOrderPrice(l_orderUnitRow.getOrgStopOrderPrice());
        }
        //���iW�w�l�j�����w�l
        if (l_orderUnitRow.getOrgWLimitPriceIsNull())
        {
            l_orderActionParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgWLimitPrice(l_orderUnitRow.getOrgWLimitPrice());
        }
        //���iW�w�l�j���s����
        l_orderActionParams.setOrgWLimitExecCondType(l_orderUnitRow.getOrgWLimitExecCondType());
        //�iW�w�l�j���s����
        l_orderActionParams.setWLimitExecCondType(l_orderUnitRow.getWLimitExecCondType());
        //�iW�w�l�j�֑ؑO�w�l
        if (l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(l_orderUnitRow.getWLimitBeforeLimitPrice());
        }
        //�iW�w�l�j�֑ؑO���s����
        l_orderActionParams.setWLimitBeforeExecCondType(l_orderUnitRow.getWLimitBeforeExecCondType());
        //�s�ꂩ��m�F�ς݂̎��s����
        l_orderActionParams.setConfirmedExecConditionType(l_orderUnitRow.getConfirmedExecConditionType());

        //�Q�j�@@�g�����������}�l�[�W��.toOrderAction()���R�[�����A
        //�@@���������I�u�W�F�N�g�𐶐�����B
        EqTypeOrderAction l_orderAction = (EqTypeOrderAction)this.toOrderAction(l_orderActionParams);
        log.exiting(STR_METHOD_NAME);
        return l_orderAction;
    }

    /**
     * (update�����f�[�^)<BR>
     * �w�肳�ꂽ�����I�u�W�F�N�g���g�p���AQueryProcessor�ɂ�蒍���f�[�^�ނ̍X�V���s���B<BR>
     * �������������쐬����ꍇ�́A<BR>
     * �@@�����P��.���������ŏI�ʔԂ��Ăяo�����ŃJ�E���g�A�b�v���邱�ƁB<BR>
     * <BR>
     * �P�j�@@����ID == �����̒����P��.����ID�ɊY�����钍���i�w�b�_�j�e�[�u����update����B<BR>
     * <BR>
     * �@@�@@�X�V���t�ɁA�����̒����P�ʃI�u�W�F�N�g�̓����ڂ̓��e���Z�b�g����update����B<BR>
     * <BR>
     * �Q�j�@@�����̒����P�ʃI�u�W�F�N�g�̓��e�Œ����P�ʃe�[�u����update����B<BR>
     * <BR>
     * �R�j�@@�����̒���������"null"�łȂ��ꍇ�̂݁A<BR>
     * �@@�@@�����̒��������I�u�W�F�N�g�̓��e�Œ��������e�[�u����insert����B<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_orderAction - (��������)<BR>
     * ��������<BR>
     * @@throws WEB3BaseException
     */
    public void updateOrderData(EqTypeOrderUnit l_orderUnit, EqTypeOrderAction l_orderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderData(EqTypeOrderUnit, EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }
        try
        {
            //�P�j�@@����ID == �����̒����P��.����ID�ɊY�����钍���i�w�b�_�j�e�[�u����update����B
            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "order_id = ? ";
            Object[] l_objWheres =
            {
                new Long(l_orderUnitRow.getOrderId())
            };
            HashMap l_map = new HashMap();
            l_map.put("last_updated_timestamp", l_orderUnitRow.getLastUpdatedTimestamp());
            l_processor.doUpdateAllQuery(
                EqtypeOrderRow.TYPE,
                l_strWhere,
                l_objWheres,
                l_map);
            //�Q�j�����̒����P�ʃI�u�W�F�N�g�̓��e�Œ����P�ʃe�[�u����update����
            l_processor.doUpdateQuery(l_orderUnitRow);
            //�R�j�@@�����̒���������"null"�łȂ��ꍇ�̂݁A
            //�����̒��������I�u�W�F�N�g�̓��e�Œ��������e�[�u����insert����B
            if (l_orderAction != null)
            {
                EqtypeOrderActionRow l_orderActionRow =
                    (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
                l_processor.doInsertQuery(l_orderActionRow);
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�X�g�b�v�����������T�Z����v�Z����)<BR>
     * �X�g�b�v�����������ɁA���~�b�g�����̊T�Z����Čv�Z���s���B<BR>
     *�i�������t�A�M�p�V�K�������̂ݍČv�Z���s���B�j<BR>
     * ��W�w�l�����Ń��~�b�g�����L���̏ꍇ�̂ݍČv�Z�\�B<BR>
     * <BR>
     * �P�j�@@�ȉ��̕���ōČv�Z�������s���B<BR>
     *�@@�@@�@@�P�|�P�j�p�����[�^.�����P��.�����J�e�S�� = "��������"�̏ꍇ <BR>
     *�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z���ʁi�����j�i�j���R�[�����A <BR>
     *�@@�@@�@@�@@�擾�����T�Z��n����v�Z���ʂ��T�Z����v�Z���ʂɐݒ肷��B <BR>
     *�@@�@@�@@�@@[����] <BR>
     *�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     *�@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * <BR>
     *�@@�@@�@@�P�|�Q�j�p�����[�^.�����P��.�����J�e�S�� = "�V�K������"�̏ꍇ <BR>
     *�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z���ʁi�M�p�V�K���j�i�j���R�[�����A<BR>
     *�@@�@@�@@�@@�擾�����T�Z������v�Z���ʂ��T�Z����v�Z���ʂɐݒ肷��B <BR>
     *�@@�@@�@@�@@[����] <BR>
     *�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P�� <BR>
     *�@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     * <BR>
     *�@@�@@�@@�P�|�R�j��L�ȊO�̏ꍇ <BR>
     *�@@�@@�@@�T�Z����v�Z���ʂ�null��ݒ肷��B<BR>
     * <BR>
     * �Q�j�T�Z����v�Z���ʃI�u�W�F�N�g��ԋp����<BR>
     * @@param l_eqOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3EquityEstimatedPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedPrice getStopOrderExpireEstimatedPrice(
        EqTypeOrderUnit l_eqOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getStopOrderExpireEstimatedPrice(EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
        //�P�j�@@�ȉ��̕���ōČv�Z�������s���B
        //�@@�@@�@@�P�|�P�j�p�����[�^.�����P��.�����J�e�S�� = "��������"�̏ꍇ
        //�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z���ʁi�����j�i�j���R�[�����A
        //�@@�@@�@@�@@�擾�����T�Z��n����v�Z���ʂ��T�Z����v�Z���ʂɐݒ肷��B
        //�@@�@@�@@�@@[����]
        //�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        //�@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        if (OrderCategEnum.ASSET.equals(l_eqOrderUnit.getOrderCateg()))
        {
            l_equityEstimatedPrice =
                this.getStopOrderExpireEstimatedPriceForEquity(
                    l_eqOrderUnit,
                    l_subAccount);
        }

        //�@@�@@�@@�P�|�Q�j�p�����[�^.�����P��.�����J�e�S�� = "�V�K������"�̏ꍇ
        //�@@�@@�@@�@@get�X�g�b�v�����������T�Z����v�Z���ʁi�M�p�V�K���j�i�j���R�[�����A
        //�@@�@@�@@�@@�擾�����T�Z������v�Z���ʂ��T�Z����v�Z���ʂɐݒ肷��B
        //�@@�@@�@@�@@[����]
        //�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        //�@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_eqOrderUnit.getOrderCateg()))
        {
            l_equityEstimatedPrice =
                this.getStopOrderExpireEstimatedPriceForMargin(
                    l_eqOrderUnit,
                    l_subAccount);
        }

        //�@@�@@�@@�P�|�R�j��L�ȊO�̏ꍇ
        //�@@�@@ �@@�T�Z����v�Z���ʂ�null��ݒ肷��B
        //
        //�Q�j�T�Z����v�Z���ʃI�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_equityEstimatedPrice;
    }

    /**
     * (get�X�g�b�v�����������T�Z����v�Z���ʁi�����j)<BR>
     * ���~�b�g�����̊T�Z��n����Čv�Z���s���B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P��.������ʂ�"����������"�̏ꍇ<BR>
     *�@@�@@�@@null��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�P�j�̏����ɊY�����Ȃ��ꍇ<BR>
     *�@@�@@�@@�ȉ��菇�ŊT�Z����v�Z���ʂ��擾����B<BR>
     * <BR>
     *�@@�Q-�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B<BR>
     * <BR>
     *�@@�Q-�Q�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     *�@@�Q-�R�j�@@�g�����������}�l�[�W��.calc�T�Z��n���()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@�@@�@@[����]<BR>
     *�@@�@@�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g<BR>
     *�@@�@@�@@�@@�@@�w�l�F�@@<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l != NULL�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�w�l <BR>
     *�@@�@@�@@�@@�@@�iW�w�l)�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l <BR>
     *�@@�@@�@@�@@�@@�t�w�l��l�F�@@�����P��Row.�t�w�l��l <BR>
     *�@@�@@�@@�@@�@@���s�����F<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� != NULL�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.���s���� <BR>
     *�@@�@@�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����<BR>
     *�@@�@@�@@�@@�@@�l�i�����F�@@�����P��Row.�l�i���� <BR>
     *�@@�@@�@@�@@�@@���������F�@@"DEFAULT"�i�Œ�j <BR>
     *�@@�@@�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j <BR>
     *�@@�@@�@@�@@�@@is�X�g�b�v�����L���F�@@false�i�Œ�j <BR>
     *�@@�@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     *�@@�@@�@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct()<BR>
     *�@@�@@�@@�@@�@@�����F�@@�p�����[�^.�����P��.��������<BR>
     *�@@�@@�@@�@@�@@is�������F�@@false�i�Œ�j<BR>
     *�@@�@@�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��<BR>
     *�@@�@@�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z<BR>
     *�@@�@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i�Œ�j<BR>
     * <BR>
     * �R�j�@@�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_eqOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3EquityEstimatedDeliveryPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedDeliveryPrice getStopOrderExpireEstimatedPriceForEquity(
        EqTypeOrderUnit l_eqOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getStopOrderExpireEstimatedPriceForEquity(EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }

        //�P�j�@@�p�����[�^.�����P��.������ʂ�"����������"�̏ꍇ
        //�@@�@@�@@null��ԋp����B
        if (OrderTypeEnum.EQUITY_SELL.equals(l_eqOrderUnit.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //�Q�j�@@�P�j�̏����ɊY�����Ȃ��ꍇ
        //�@@�@@�@@�ȉ��菇�ŊT�Z����v�Z���ʂ��擾����B
        //�@@�Q-�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B
        EqtypeOrderUnitRow l_eqTypeOrderUntiRow =
            (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        //�@@�Q-�Q�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B
        //�@@�@@�@@�@@�@@[����]
        //�@@�@@�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqOrderUnit);

        double l_dblLimitPrice = 0D;
        EqTypeExecutionConditionType l_executionConditionType = null;
        //�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l != NULL�̏ꍇ
        //�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l
        //��L�ȊO�̏ꍇ
        //�@@�@@�p�����[�^.�����P��.�w�l
        if (!l_eqTypeOrderUntiRow.getConfirmedPriceIsNull())
        {
            l_dblLimitPrice = l_eqTypeOrderUntiRow.getConfirmedPrice();
        }
        else
        {
            l_dblLimitPrice = l_eqTypeOrderUntiRow.getLimitPrice();
        }
        //�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� != NULL�̏ꍇ
        //�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s����
        //��L�ȊO�̏ꍇ
        //�@@�@@�����P��Row.���s����
        if (l_eqTypeOrderUntiRow.getConfirmedExecConditionType() != null)
        {
            l_executionConditionType =
                l_eqTypeOrderUntiRow.getConfirmedExecConditionType();
        }
        else
        {
            l_executionConditionType =
                l_eqTypeOrderUntiRow.getExecutionConditionType();
        }
        //�@@�Q-�R�j�@@�g�����������}�l�[�W��.calc�T�Z��n���()���R�[������B
        //�@@�@@�@@�@@�@@[����]
        //�@@�@@�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g
        //�@@�@@�@@�@@�@@�w�l�F
        //�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l != NULL�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l
        //�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�w�l
        //�@@�@@�@@�@@�@@�iW�w�l)�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l
        //�@@�@@�@@�@@�@@�t�w�l��l�F�@@�����P��Row.�t�w�l��l
        //�@@�@@�@@�@@�@@���s�����F
        //�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� != NULL�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s����
        //�@@�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.���s����
        //�@@�@@�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@�@@�@@�l�i�����F�@@�����P��Row.�l�i����
        //�@@�@@�@@�@@�@@���������F�@@"DEFAULT"�i�Œ�j
        //�@@�@@�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j
        //�@@�@@�@@�@@�@@is�X�g�b�v�����L���F�@@false�i�Œ�j
        //�@@�@@�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct()
        //�@@�@@�@@�@@�@@�����F�@@�p�����[�^.�����P��.��������
        //�@@�@@�@@�@@�@@is�������F�@@false�i�Œ�j
        //�@@�@@�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��
        //�@@�@@�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z
        //�@@�@@�@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i�Œ�j
        WEB3EquityTradedProduct l_tradedProduct = null;
        TradedProduct l_getTradedProduct = l_eqOrderUnit.getTradedProduct();
        if (l_getTradedProduct != null)
        {
            l_tradedProduct = (WEB3EquityTradedProduct)l_getTradedProduct;
        }
        WEB3EquityEstimatedDeliveryPrice l_calcEstimateDeliveryAmount =
            this.calcEstimateDeliveryAmount(
                l_commission,
                l_dblLimitPrice,
                l_eqTypeOrderUntiRow.getWLimitPrice(),
                l_eqTypeOrderUntiRow.getStopOrderPrice(),
                l_executionConditionType,
                l_eqTypeOrderUntiRow.getWLimitExecCondType(),
                l_eqTypeOrderUntiRow.getPriceConditionType(),
                WEB3OrderingConditionDef.DEFAULT,
                "0",
                false,
                l_subAccount,
                l_tradedProduct,
                l_eqOrderUnit.getQuantity(),
                false,
                l_eqOrderUnit.getExecutedQuantity(),
                l_eqOrderUnit.getExecutedAmount(),
                true);

        //�R�j�@@�T�Z��n����v�Z���ʃI�u�W�F�N�g��ԋp����B  
        log.exiting(STR_METHOD_NAME);
        return l_calcEstimateDeliveryAmount;
    }

    /**
     * (get�X�g�b�v�����������T�Z����v�Z���ʁi�M�p�V�K���j)<BR>
     * ���~�b�g�����̊T�Z������Čv�Z���s���B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �Q�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@[����]<BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �R�j�@@�g�����������}�l�[�W��.is������()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@[����]<BR>
     *�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��<BR>
     * <BR>
     * �S�j�@@�g�����������}�l�[�W��.calc�����������()���R�[������B<BR>
     * <BR>
     *�@@�@@�@@[����]<BR>
     *�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g<BR>
     *�@@�@@�@@�w�l�F <BR>
     *�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l != NULL�̏ꍇ<BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l<BR>
     *�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�w�l<BR>
     *�@@�@@�@@�iW�w�l�j�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l<BR>
     *�@@�@@�@@�t�w�l��l�F�@@�����P��Row.�t�w�l��l<BR>
     *�@@�@@�@@���s�����F <BR>
     *�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� != NULL�̏ꍇ  <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� <BR>
     *�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ <BR>
     *�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.���s����  <BR>
     *�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s���� <BR>
     *�@@�@@�@@�l�i�����F�@@�����P��Row.�l�i���� <BR>
     *�@@�@@�@@���������F�@@"DEFAULT"�i�Œ�j<BR>
     *�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j<BR>
     *�@@�@@�@@is�X�g�b�v�����L���F�@@false�i�Œ�j<BR>
     *�@@�@@�@@is�����F�@@is������()�̖߂�l <BR>
     *�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕���� <BR>
     *�@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct() <BR>
     *�@@�@@�@@�����F�@@�p�����[�^.�����P��.�������� <BR>
     *�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔�� <BR>
     *�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z <BR>
     *�@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i�Œ�j <BR>
     * <BR>
     * �T�j�@@�T�Z������v�Z���ʃI�u�W�F�N�g��ԋp����B<BR>
     * @@param l_eqOrderUnit - (�����P��)<BR>
     * �����P��<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return WEB3EquityEstimatedContractPrice
     * @@throws WEB3BaseException
     */
    public WEB3EquityEstimatedContractPrice getStopOrderExpireEstimatedPriceForMargin(
        EqTypeOrderUnit l_eqOrderUnit,
        SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getStopOrderExpireEstimatedPriceForMargin(EqTypeOrderUnit, SubAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_eqOrderUnit == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "�p�����[�^�l�s���B");
        }
        //�P�j�@@�p�����[�^.�����P��.getDataSourceObject()���R�[������B
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        //�Q�j�@@�����v�Z�T�[�r�X.create�萔��()���R�[������B
        //�@@�@@�@@[����]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqOrderUnit);

        //�R�j�@@�g�����������}�l�[�W��.is������()���R�[������B
        //�@@�@@�@@[����]
        //�@@�@@�@@�����P�ʁF�@@�p�����[�^.�����P��
        boolean l_blnSellOrder = this.isSellOrder(l_eqOrderUnit);

        double l_dblLimitPrice = 0D;
        EqTypeExecutionConditionType l_executionConditionType = null;
        //�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l != NULL�̏ꍇ
        //�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l
        //��L�ȊO�̏ꍇ
        //�@@�@@�p�����[�^.�����P��.�w�l
        if (!l_eqtypeOrderUnitRow.getConfirmedPriceIsNull())
        {
            l_dblLimitPrice = l_eqtypeOrderUnitRow.getConfirmedPrice();
        }
        else
        {
            l_dblLimitPrice = l_eqtypeOrderUnitRow.getLimitPrice();
        }
        //�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� != NULL�̏ꍇ
        //�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s����
        //��L�ȊO�̏ꍇ
        //�@@�@@�����P��Row.���s����
        if (l_eqtypeOrderUnitRow.getConfirmedExecConditionType() != null)
        {
            l_executionConditionType =
                l_eqtypeOrderUnitRow.getConfirmedExecConditionType();
        }
        else
        {
            l_executionConditionType =
                l_eqtypeOrderUnitRow.getExecutionConditionType();
        }
        //�S�j�@@�g�����������}�l�[�W��.calc�����������()���R�[������B
        //�@@�@@�@@[����]
        //�@@�@@�@@�萔���F�@@�萔���I�u�W�F�N�g
        //�@@�@@�@@�w�l�F
        //�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l != NULL�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�s�ꂩ��m�F�ς݂̎w�l
        //�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�p�����[�^.�����P��.�w�l
        //�@@�@@�@@�iW�w�l�j�����w�l�F�@@�����P��Row.�iW�w�l�j�����w�l
        //�@@�@@�@@�t�w�l��l�F�@@�����P��Row.�t�w�l��l
        //�@@�@@�@@���s�����F
        //�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s���� != NULL�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.�s�ꂩ��m�F�ς݂̎��s����
        //�@@�@@�@@�@@�@@�@@�@@��L�ȊO�̏ꍇ
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�����P��Row.���s����
        //�@@�@@�@@�iW�w�l�j���s�����F�@@�����P��Row.�iW�w�l�j���s����
        //�@@�@@�@@�l�i�����F�@@�����P��Row.�l�i����
        //�@@�@@�@@���������F�@@"DEFAULT"�i�Œ�j
        //�@@�@@�@@�m�F���擾�����F�@@0�i�Œ�j
        //�@@�@@�@@is�X�g�b�v�����L���F�@@false�i�Œ�j
        //�@@�@@�@@is�����F�@@is������()�̖߂�l
        //�@@�@@�@@�⏕�����F�@@�p�����[�^.�⏕����
        //�@@�@@�@@��������F�@@�p�����[�^.�����P��.getTradedProduct()
        //�@@�@@�@@�����F�@@�p�����[�^.�����P��.��������
        //�@@�@@�@@��萔�ʁF�@@�p�����[�^.�����P��.��萔��
        //�@@�@@�@@���v�����z�F�@@�p�����[�^.�����P��.���v�����z
        //�@@�@@�@@isSkip���z�`�F�b�N�F�@@true�i�Œ�j
        EqTypeTradedProduct l_tradedProduct = null;
        TradedProduct l_getTradedProduct = l_eqOrderUnit.getTradedProduct();
        if (l_getTradedProduct != null)
        {
            l_tradedProduct = (EqTypeTradedProduct)l_getTradedProduct;
        }
        WEB3EquityEstimatedContractPrice l_calcContractAmountAtOrder =
            this.calcContractAmountAtOrder(
                l_commission,
                l_dblLimitPrice,
                l_eqtypeOrderUnitRow.getWLimitPrice(),
                l_eqtypeOrderUnitRow.getStopOrderPrice(),
                l_executionConditionType,
                l_eqtypeOrderUnitRow.getWLimitExecCondType(),
                l_eqtypeOrderUnitRow.getPriceConditionType(),
                WEB3OrderingConditionDef.DEFAULT,
                "0",
                false,
                l_blnSellOrder,
                l_subAccount,
                l_tradedProduct,
                l_eqOrderUnit.getQuantity(),
                l_eqOrderUnit.getExecutedQuantity(),
                l_eqOrderUnit.getExecutedAmount(),
                true);

        //�T�j�@@�T�Z������v�Z���ʃI�u�W�F�N�g��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_calcContractAmountAtOrder;
    }
    
    /**
     * (is�������ϒ���)<BR>
     * �����̒����P�ʂ��������ϒ������ۂ��𔻒肷��B <BR>
     * <BR>
     * true�F�@@�������ϒ��� <BR>
     * false�F�@@�������ϒ����ł͂Ȃ� <BR>
     * <BR>
     * ����.�����P��.�������ϗ��R�敪 != null�̏ꍇ�A <BR>
     * true��ԋp����B <BR>
     * <BR>
     * �ȊO�̏ꍇ�Afalse��ԋp����B<BR>
     * @@param l_orderUnit - �����P��
     * @@return boolean
     */
    public boolean isForcedSettleOrder(EqTypeOrderUnit l_orderUnit)
    {
        String STR_METHOD_NAME = "isForcedSettleOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnFlag = false;

        //����.�����P��.�������ϗ��R�敪 != null�̏ꍇ�A
        //true��ԋp����B
        if (l_orderUnit != null)
        {
            EqtypeOrderUnitRow l_eqTypeOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (l_eqTypeOrderUnitRow.getForcedSettleReasonType() != null)
            {
                l_blnFlag = true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnFlag;
    }

    /**
     * (is�����F�������ϒ���)<BR>
     * �����̒����P�ʂ������F�̋������ϒ������ۂ��𔻒肷��B <BR>
     * <BR>
     * true�F�@@�����F�̋������ϒ��� <BR>
     * false�F�@@�������ϒ����ł͂Ȃ�<BR>
     * �@@�@@�@@�@@�@@�܂��́A���F�ς̋������ϒ���<BR>
     * <BR>
     * �@@this.is�������ϒ���() == true���A <BR>
     * �@@����.�����P��.���F��ԋ敪 != "���F��"�̏ꍇ�Atrue��ԋp����B <BR>
     * <BR>
     * �@@[is�������ϒ���()�̈���] <BR>
     * �@@�@@����.�����P�� <BR>
     * <BR>
     * �@@�ȊO�̏ꍇ�Afalse��ԋp����B <BR>
     * @@param l_orderUnit - �����P��
     * @@return boolean
     */
    public boolean isApproveForcedSettleOrder(EqTypeOrderUnit l_orderUnit)
    {
        String STR_METHOD_NAME = "isApproveForcedSettleOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnFlag = false;

        //this.is�������ϒ���() == true���A
        //����.�����P��.���F��ԋ敪 != "���F��"�̏ꍇ�Atrue��ԋp����B
        if (this.isForcedSettleOrder(l_orderUnit))
        {
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            if (!WEB3ApproveStatusType.APPROVED.equals(l_eqtypeOrderUnitRow.getApproveStatusType()))
            {
                l_blnFlag = true;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnFlag;
    }

    /**
     * (get�����L������)<BR>
     * �����L�������ŏI���̂ݎw���Ђ̏ꍇ�A<BR>
     * �����ɐݒ肳�ꂽ�����L�������Ɩ����̌����t���ŏI�����r����<BR>
     * ���������̒l��ԋp����B<BR>
     * <BR>
     * ����菈������Ŏg�p���邱�ƁB<BR>
     * <BR>
     * �P�j�@@���X.is�����t���ŏI���`�F�b�N()�̖߂�l��false�̏ꍇ�A <BR>
     * �@@�@@�p�����[�^.�����L��������ԋp����B <BR>
     * �@@�@@�ȊO�̏ꍇ�A�ȉ������s����B <BR>
     * <BR>
     * �Q�j�@@�g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B<BR>
     * �@@�@@�@@[getProduct()�ɐݒ肷�����] <BR>
     * �@@�@@�@@�@@�،���ЁF�@@���O�C���Z�b�V�������擾�����،���� <BR>
     * �@@�@@�@@�@@�����R�[�h�@@�@@�F�@@�p�����[�^.�����R�[�h  <BR>
     *  <BR>
     * �R�j�@@�戵�\���������𐶐�����B <BR>
     * �@@�@@�@@[�R���X�g���N�^�ɐݒ肷�����] <BR>
     *  �@@�@@�@@�@@�،���ЃR�[�h�F�@@���O�C���Z�b�V�������擾�����،���ЃR�[�h <BR>
     *  �@@�@@�@@�@@�����^�C�v�F�@@�h�����h  <BR>
     *  �@@�@@�@@�@@�敨�^�I�v�V�����敪�F�@@�hDEFAULT�h  <BR>
     *  �@@�@@�@@�@@�M�p����敪�F�@@�hDEFAULT�h(�Œ�)  <BR>
     *  �@@�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h <BR>
     * <BR>
     * �S�j�@@�����̌����t���ŏI�����A���ݓ������Z�o��������������̏ꍇ�݈̂ȉ������{�B<BR>
     * �@@�@@�@@�i������ԊǗ�.get������ <= ��������.get�����t���ŏI���j<BR>
     * <BR>
     * �@@�S�|�P�j�@@�戵�\��������.set����ŏI��()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@[set����ŏI��()�ɐݒ肷�����]<BR>
     * �@@�@@�@@�@@�@@�@@����ŏI���F�@@��������.get�����t���ŏI��<BR>
     * <BR>
     * �T�j�@@�戵�\��������.get�o����܂Œ����������w��ɂčŏI���w��敪���擾����B<BR>
     * <BR>
     * �U�j�@@�ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ�A<BR>
     * �@@�@@�戵�\��������.get�o����܂Œ����ŏI��<����ŏI���l��>()�̖߂�l��ԋp����B<BR>
     * �@@�@@�mget�o����܂Œ����ŏI��<����ŏI���l��>()�n  <BR>
     * �@@�@@�@@�������������F�@@null���Z�b�g <BR>
     * <BR>
     * �V�j�@@�ŏI���w��敪���w0�F���ԓ����������[�U�w��x�̏ꍇ�A<BR>
     * �@@�@@�p�����[�^.�����L��������ԋp����B <BR>
     * <BR>
     * @@param l_datExpirationDate - �����L������ <BR>
     * �����L������ <BR>
     * <BR>
     * @@param l_strProductCode - �����R�[�h <BR>
     * �����R�[�h <BR>
     * <BR>
     * @@param l_strMarketCode - �s��R�[�h <BR>
     * �s��R�[�h <BR>
     * <BR>
     * @@return Date <BR>
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate(
        Date l_datExpirationDate,
        String l_strProductCode,
        String l_strMarketCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getExpirationDate(Date, String, String)";
        log.entering(STR_METHOD_NAME);
        Institution l_institution = null;
        String l_strInstitutionCode = null;
        WEB3EquityProduct l_equityProduct = null;
        WEB3GentradeBranch l_branch = null;

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager =
            (AccountManager)l_finApp.getAccountManager();

        // ���O�C���Z�b�V�������擾�����،����
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        // �g���v���_�N�g�}�l�[�W��
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        try
        {
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(l_opLoginSecurityService.getAccountId());
            l_institution = l_mainAccount.getInstitution();

            l_branch = (WEB3GentradeBranch)l_mainAccount.getBranch();
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        // �P�j�@@���X.is�����t���ŏI���`�F�b�N()�̖߂�l��false�̏ꍇ�A
        if (!l_branch.isEqtypeFinalDayWithRight())
        {
            // �p�����[�^.�����L��������ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }

        //2) �g���v���_�N�g�}�l�[�W��.getProduct()���R�[�����A�����������擾����B
        // [getProduct()�ɐݒ肷�����]
        // �،���ЁF�@@���O�C���Z�b�V�������擾�����،����
        // �����R�[�h�@@�@@�F�@@�p�����[�^.�����R�[�h
        try
        {
            l_strInstitutionCode = l_institution.getInstitutionCode();

            // �����������擾
            l_equityProduct =
                (WEB3EquityProduct)l_productManager.getProduct(l_institution, l_strProductCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�R�j�@@�戵�\���������𐶐�����B
        //[�R���X�g���N�^�ɐݒ肷�����]
        //�،���ЃR�[�h�F�@@���O�C���Z�b�V�������擾�����،���ЃR�[�h
        //�����^�C�v�F�@@�h�����h
        //�敨�^�I�v�V�����敪�F�@@�hDEFAULT�h
        //�M�p����敪�F�@@�hDEFAULT�h(�Œ�)
        //�s��R�[�h�F�@@�p�����[�^.�s��R�[�h
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);

        //�S�j�@@�����̌����t���ŏI�����A���ݓ������Z�o��������������̏ꍇ�݈̂ȉ������{�B
        //�i������ԊǗ�.get������ <= ��������.get�����t���ŏI���j
        //����ŏI���F�@@��������.get�����t���ŏI��
        Date l_datRightCondOrderEndDay = l_equityProduct.getRightCondOrderEndDay();
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if (WEB3DateUtility.compare(l_datOrderBizDate, l_datRightCondOrderEndDay) <= 0)
        {
            //�戵�\��������.set����ŏI��()���R�[������B
            l_handlingOrderCond.setTradingEndDate(l_datRightCondOrderEndDay);
        }

        // �T�j�@@�戵�\��������.get�o����܂Œ����������w��ɂčŏI���w��敪���擾����B
        String l_strOrderUntilDeadLineExp = l_handlingOrderCond.getOrderUntilDeadLineExpDay();

        //�U�j�@@�ŏI���w��敪���w1�F�ŏI���̂ݎw��x�̏ꍇ�A
        //�戵�\��������.get�o����܂Œ����ŏI��<����ŏI���l��>()�̖߂�l��ԋp����B
        //�mget�o����܂Œ����ŏI��<����ŏI���l��>()�n
        //�������������F�@@null���Z�b�g
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strOrderUntilDeadLineExp))
        {
            log.exiting(STR_METHOD_NAME);
            return l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);
        }
        else
        {
            // �V�j�@@�ŏI���w��敪���w0�F���ԓ����������[�U�w��x�̏ꍇ�A
            // �p�����[�^.�����L��������ԋp����B
            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }

    /**
     * Commission�̃N���[��
     * @@param l_commission
     * @@return WEB3GentradeCommission
     */
    private WEB3GentradeCommission copyCommission(WEB3GentradeCommission l_commission)
    {
        WEB3GentradeCommission l_cloneCommission = new WEB3GentradeCommission();
        l_cloneCommission.setBranchId(l_commission.getBranchId());
        l_cloneCommission.setCommission(l_commission.getCommission());
        l_cloneCommission.setCommissionCourseDiv(l_commission.getCommissionCourseDiv());
        l_cloneCommission.setCommissionNo(l_commission.getCommissionNo());
        l_cloneCommission.setCommissionProductCode(l_commission.getCommissionProductCode());
        l_cloneCommission.setCommissionRevNo(l_commission.getCommissionRevNo());
        l_cloneCommission.setCommitionPerUnit(l_commission.getCommitionPerUnit());
        l_cloneCommission.setDayTradeType(l_commission.getDayTradeType());
        l_cloneCommission.setExpensesCalcAmount(l_commission.getExpensesCalcAmount());
        l_cloneCommission.setInstitutionCode(l_commission.getInstitutionCode());
        l_cloneCommission.setIsLimitPrice(l_commission.isLimitPrice());
        l_cloneCommission.setMinCommission(l_commission.getMinCommission());
        l_cloneCommission.setOrderBizDate(l_commission.getOrderBizDate());
        l_cloneCommission.setOrderChannel(l_commission.getOrderChannel());
        l_cloneCommission.setOrgCommissionNo(l_commission.getOrgCommissionNo());
        l_cloneCommission.setOrgCommissionRevNo(l_commission.getOrgCommissionRevNo());
        l_cloneCommission.setOrgOrderChannel(l_commission.getOrgOrderChannel());
        l_cloneCommission.setPayType(l_commission.getPayType());
        l_cloneCommission.setQuantity(l_commission.getQuantity());
        l_cloneCommission.setSonarMarketCode(l_commission.getSonarMarketCode());
        l_cloneCommission.setSonarTradedCode(l_commission.getSonarTradedCode());
        l_cloneCommission.setUnderlyingProductCode(l_cloneCommission.getUnderlyingProductCode());

        return l_cloneCommission;
    }
    /**
     * (throw�����R�����ʃG���[���) <BR>
     * �����R���̌��ʂ����s�̏ꍇ�A��O��throw����B<BR>
     * <BR>
     * �u�����t���ŏI����̎������w��s�B�v�G���[�̏ꍇ�A<BR>
     * �����t���ŏI�����擾���Ēǉ����b�Z�[�W�ɃZ�b�g���A��O��throw����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�����R��������I�������ꍇ<BR>
     * �@@����.�����R������.getProcessingResult().isSuccessfulResult() �� true�̏ꍇ�A<BR>
     * �@@�������Ȃ��ŏ������I������B�ireturn;�j<BR>
     * <BR>
     * �Q�j�@@�����R�������s�����ꍇ<BR>
     * �@@�Q�|�P�j�@@�����t���ŏI���`�F�b�N�ŃG���[�ƂȂ����ꍇ<BR>
     * �@@�@@����.�����R������.getProcessingResult().getErrorInfo()�̖߂�l���A<BR>
     * �@@�@@"�����t���ŏI����̎������w��s�B"�iBUSINESS_ERROR_02836�j�̏ꍇ�A<BR>
     * �@@�@@���̏������s���B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�P�j�@@���������̎擾<BR>
     * �@@�@@�@@�g���v���_�N�g�}�l�[�W��.getProduct()��call����B<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�،���ЁF�@@����.�،����<BR>
     * �@@�@@�@@�@@�����R�[�h�F�@@����.�����R�[�h<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j�@@�����t���ŏI���̎擾<BR>
     * �@@�@@�@@�Q�|�P�|�P�j�Ŏ擾������������.get�����t���ŏI��()��call����B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�R�j�@@��O����<BR>
     * �@@�@@�@@�����R�����ʂ���WEB3BusinessLayerException�𐶐����Athrow����B<BR>
     * <BR>
     * �@@�@@�@@[�R���X�g���N�^�̈���]<BR>
     * �@@�@@�@@�@@�G���[���F�@@����.�����R������.getProcessingResult().getErrorInfo()<BR>
     * �@@�@@�@@�@@�G���[���\�b�h�F�@@���Y���\�b�h��<BR>
     * �@@�@@�@@�@@�G���[���b�Z�[�W�F�@@�Q�|�P�|�Q�j�Ŏ擾���������t���ŏI����ҏW(*1)����������<BR>
     * <BR>
     * �@@�@@�@@�@@�@@(*1)�ȉ��̌`���ɕҏW����B<BR>
     * �@@�@@�@@�@@�@@�@@���������N���������i���j<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@���ɂ́A�j��(��,��,��,��,��,��,�y)��ݒ肷��B<BR>
     * �@@�@@�@@�@@�@@�@@��j2007�N8��31���i���j<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�ȊO�̏ꍇ<BR>
     * �@@�@@�@@�����R�����ʂ���WEB3BusinessLayerException�𐶐����Athrow����B<BR>
     * <BR>
     * �@@�@@�@@[�R���X�g���N�^�̈���]<BR>
     * �@@�@@�@@�@@�G���[���F�@@����.�����R������.getProcessingResult().getErrorInfo()<BR>
     * �@@�@@�@@�@@�G���[���\�b�h�F�@@���Y���\�b�h��<BR>
     * <BR>
     * @@param l_orderValidationResult - (�����R������)<BR>
     * �����R������<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void throwOrderValidationResultErrorInfo(
        OrderValidationResult l_orderValidationResult,
        Institution l_institution,
        String  l_strProductCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " throwOrderValidationResultErrorInfo(OrderValidationResult, Institution, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�����R��������I�������ꍇ
        //  ����.�����R������.getProcessingResult().isSuccessfulResult() �� true�̏ꍇ�A
        //     �������Ȃ��ŏ������I������B�ireturn;�j
        if (l_orderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //�Q�j�@@�����R�������s�����ꍇ
            // �Q�|�P�j�@@�����t���ŏI���`�F�b�N�ŃG���[�ƂȂ����ꍇ
            //�@@����.�����R������.getProcessingResult().getErrorInfo()�̖߂�l���A
            //    "�����t���ŏI����̎������w��s�B"�iBUSINESS_ERROR_02836�j�̏ꍇ�A
            //�@@  ���̏������s���B
            if (WEB3ErrorCatalog.BUSINESS_ERROR_02836.equals(
                l_orderValidationResult.getProcessingResult().getErrorInfo()))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

                //�g���v���_�N�g�}�l�[�W�����擾
                try
                {
                    //�Q�|�P�|�P�j�@@���������̎擾
                    //�@@�g���v���_�N�g�}�l�[�W��.getProduct()��call����B
                    WEB3EquityProductManager l_equityProductManager =
                        (WEB3EquityProductManager)l_tradingModule.getProductManager();

                    WEB3EquityProduct l_eqtypeProduct =
                        (WEB3EquityProduct)l_equityProductManager.getProduct(
                            l_institution, l_strProductCode);
                    //�Q�|�P�|�Q�j�@@�����t���ŏI���̎擾
                    //�@@�@@�Q�|�P�|�P�j�Ŏ擾������������.get�����t���ŏI��()��call����B
                    Date l_datRightCondOrderEndDay = l_eqtypeProduct.getRightCondOrderEndDay();

                    //�Q�|�P�|�R�j�@@��O����
                    //�@@�����R�����ʂ���WEB3BusinessLayerException�𐶐����Athrow����B
                    //�@@[�R���X�g���N�^�̈���]
                    //�@@�@@�G���[���F�@@����.�����R������.getProcessingResult().getErrorInfo()
                    //�@@�@@�G���[���\�b�h�F�@@���Y���\�b�h��
                    //�@@�@@�G���[���b�Z�[�W�F�@@�Q�|�P�|�Q�j�Ŏ擾���������t���ŏI����ҏW(*1)����������
                    //�@@�@@�@@(*1)�ȉ��̌`���ɕҏW����B
                    //�@@�@@�@@�@@���������N���������i���j
                    //�@@�@@�@@�@@���ɂ́A�j��(��,��,��,��,��,��,�y)��ݒ肷��B
                    //�@@�@@�@@�@@��j2007�N8��31���i���j
                    String l_strFormatDate =
                        WEB3DateUtility.formatDate(
                            l_datRightCondOrderEndDay, WEB3GentradeTimeDef.DATE_PARSE_YMDE);

                    log.debug(l_strFormatDate);
                    log.debug(l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
                    throw new WEB3BusinessLayerException(
                        l_orderValidationResult.getProcessingResult().getErrorInfo(),
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strFormatDate);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
            else
            {
                //�Q�|�Q�j�@@�ȊO�̏ꍇ
                //�@@�@@�����R�����ʂ���WEB3BusinessLayerException�𐶐����Athrow����B
                // �@@[�R���X�g���N�^�̈���]
                //�@@�@@�G���[���F�@@����.�����R������.getProcessingResult().getErrorInfo()
                //�@@�@@�G���[���\�b�h�F�@@���Y���\�b�h��
                log.debug(l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
                }

            }

    }

    /**
     * (validate�@@�\�a������)<BR>
     * �i* ���������R���`�F�b�N.validate�@@�\�a������(�⏕����)�ɈϏ�����B�j<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@throws WEB3BaseException
     */
    public void validateMechanismDepositAgree(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMechanismDepositAgree(SubAccount)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityTypeOrderManagerReusableValidations l_reusableValidations =
            (WEB3EquityTypeOrderManagerReusableValidations)
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

        //���������R���`�F�b�N.validate�@@�\�a������(�⏕����)�ɈϏ�����B
        l_reusableValidations.validateMechanismDepositAgree(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (throw���������]�̓G���[�ڍ׏��)<BR>
     * ���]�̓G���[�敪�ɉ����āA���ߍ��݃G���[���b�Z�[�W�̕ҏW�A<BR>
     * �y�ъY������G���[�R�[�h��ݒ肵�ė�O��throw������B<BR>
     * ���]�̓`�F�b�N�G���[���̂ݎg�p����B<BR>
     * <BR>
     * �V�[�P���X�}�uthrow���������]�̓G���[�ڍ׏��v�Q�ƁB<BR>
     * <BR>
     * ========================================================== <BR>
     * �V�[�P���X�} �F(throw���������]�̓G���[�ڍ׏��) <BR>
     * ��̈ʒu�F(��K���G���[�i����]�͌���.����]�̓G���[���.����]�̓G���[�敪 ==<BR>
     * �@@�@@�@@�@@�@@ "��K���G���["�j�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01928 <BR>
     * ========================================================== <BR>
     * <BR>
     * ========================================================== <BR>
     * �V�[�P���X�} �F(throw���������]�̓G���[�ڍ׏��) <BR>
     * ��̈ʒu�Fget�a����s�����i���t�j<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01929 <BR>
     * ========================================================== <BR>
     * <BR>
     * ========================================================== <BR>
     * �V�[�P���X�} �F(throw���������]�̓G���[�ڍ׏��) <BR>
     * ��̈ʒu�Fget�a����s�����i���t�j<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_01930 <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_tPTradingPowerResult - (����]�͌���)<BR>
     * ����]�͌���<BR>
     * @@param l_orderTypeEnum - (�������)<BR>
     * �������<BR>
     * @@param l_dblOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@throws WEB3BaseException
     */
    public void throwEquityTpErrorDetailInfo(
        WEB3TPTradingPowerResult l_tPTradingPowerResult,
        OrderTypeEnum l_orderTypeEnum,
        double l_dblOrderQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "throwEquityTpErrorDetailInfo(WEB3TPTradingPowerResult, OrderTypeEnum, double)";
        log.entering(STR_METHOD_NAME);

        if (l_tPTradingPowerResult == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //����]�͌���.is����t���O() == true �̏ꍇ
        //��������return����B
        if (l_tPTradingPowerResult.isResultFlg())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //����]�͌���.is����t���O() == false �̏ꍇ
        //����]�͌���.����]�̓G���[���.����]�̓G���[�敪
        String l_strTradinPowerErrorDiv = l_tPTradingPowerResult.getTpErrorInfo().tradinPowerErrorDiv;

        //��K���G���[�i����]�͌���.����]�̓G���[���.����]�̓G���[�敪 == "��K���G���["�j�̏ꍇ
        if (WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR.equals(l_strTradinPowerErrorDiv))
        {
            log.debug("��K���`�F�b�N�G���[�B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��K���`�F�b�N�G���[�B");
        }
        //��K���G���[�łȂ��ꍇ
        else
        {
            //�������̏ꍇ
            //get�a����s�����i���t�j�擾����������͗�O�I�u�W�F�N�g�ɐݒ�
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.getLackAccountBalanceInfoBuy(l_tPTradingPowerResult));
            }
            //�������̏ꍇ
            //get�a����s�����i���t�j�擾����������͗�O�I�u�W�F�N�g�ɐݒ�
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderTypeEnum))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.getLackAccountBalanceInfoSell(l_tPTradingPowerResult, l_dblOrderQuantity));
            }
        }
    }
}
@
