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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張株式注文マネージャ(WEB3EquityOrderManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/04 中尾 (SRA)
Revesion History : 2006/08/02 栄イ (中訊) 仕様変更 モデルNo.959を対応
Revesion History : 2006/08/30 張騰宇(中訊) DB更新仕様 164
Revesion History : 2006/11/01 周捷(中訊) モデルNo.945、No.946、No.950、No.988、No.995、No.1003、No.1006、
         　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@No.1008、No.1014、No.1018、No.1020、No.1028、No.1026、No.1034、
         　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@No.1035、No.1036、No.1037、No.1038、No.1039、No.1045、
         　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@No.1050、No.1054 計算式書 No.019
Revesion History : 2006/11/21 柴双紅(中訊) モデルNo.1052、No.1057、No.1058,【トリガー注文】DB更新仕様No.025、No.026
Revesion History : 2006/11/28 唐性峰(中訊) モデルNo.1061.1074,1075,No.1080
Revesion History : 2006/11/30 齊珂(中訊) 【トリガー注文】DB更新仕様No.028 
Revesion History : 2006/12/26 張騰宇 (中訊) モデル 1096  
Revesion History : 2007/01/15 唐性峰(中訊) モデルNo.1101.1102,1103,No.1104
Revesion History : 2007/01/18 唐性峰(中訊) モデルNo.1108
Revesion History : 2007/01/31 趙林鵬 (中訊) 仕様変更 モデルNo.1118
Revesion History : 2007/02/08 唐性峰(中訊) モデルNo.1126
Revesion History : 2007/04/25 謝旋(中訊) モデル1138
Revesion History : 2007/05/15 張騰宇(中訊) モデルNo.1156、1157、1158
Revesion History : 2007/05/21 張騰宇(中訊)　@モデル 1163
Revesion History : 2007/07/27 望月　@研志(SRA) 仕様変更モデル 1183,1185,1186
Revesion History : 2007/06/05 何文敏(中訊)　@モデル 1149,モデル 1159
Revesion History : 2007/06/15 武波(中訊)　@モデルNo.1176
Revesion History : 2007/06/20 武波(中訊)　@モデルNo.1180
Revesion History : 2007/07/26 何文敏 (中訊) 仕様変更モデルNo.1189
Revesion History : 2007/08/07 韓斌（中訊）仕様変更 モデルNo.1191
Revesion History : 2007/12/10 趙林鵬 (中訊) 仕様変更モデルNo.1239
Revesion History : 2007/12/17 趙林鵬 (中訊) 仕様変更モデルNo.1218,1236
Revesion History : 2008/10/06 劉剣 (中訊) モデルNo.1324
Revesion History : 2008/12/16 李キョウ (中訊) モデルNo.1328
Revesion History : 2009/09/28 武波 (中訊) モデル No.1345
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
 * （拡張株式注文マネージャ）。<BR>
 * <BR>
 * 株式注文の手続きを記述するクラス。 <BR>
 * <BR>
 * （参考）com.fitechlabs.xtrade.plugin.tc.eqtype.stdimples.EqTypeOrderManagerImpl
 * @@version 1.0
 */
public class WEB3EquityOrderManager extends EqTypeOrderManagerImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderManager.class);

    /**
     * デフォルトコンストラクタ。<BR>
     * @@roseuid 409F7154007D
     */
    public WEB3EquityOrderManager()
    {
        super();
    }

    /**
     * (validate現物株式注文) <BR>
     * <BR>
     * 注文入力内容のチェックを実施する。 <BR>
     * （validateNewCashBasedOrder） <BR>
     * <BR>
     * シーケンス図「（注文）現物株式発注審査」参照。 <BR>
     * シーケンス図中のメソッド例外処理（以下の番号、シーケンス図より）<BR>
     * <BR>
     * 　@1.7.4 申込株数上限チェック<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@tag   : BUSINESS_ERROR_01347<BR>
     * @@param l_subAccount (補助口座)
     * @@param l_eqNewCashBasedOrderSpec (株式注文内容)
     * @@param l_blnIsReverseOrder (is連続反対売買)<BR>
     * 予約注文設定で、親注文に対する反対売買の場合はtrueを指定。<BR>
     * 以外、false指定。<BR>
     * （trueの場合、確定残に対する残数チェック実行をスキップする）
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
            // validate注文受付可能()
            WEB3GentradeTradingTimeManagement.validateOrderAccept();

            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            //commonFirstValidationsForAllOperations()
            l_orderMgrResVal.commonFirstValidationsForAllOperations(
                l_gentradeSubAccount);
            
            //getProductCode()
            String l_strProductCode = l_equityNewCashBasedOrderSpec.getProductCode();
            
            //validate銘柄コード()
            String l_strInstitutionCode =
                l_gentradeSubAccount.getInstitution().getInstitutionCode();
            WEB3EquityProduct l_equityProduct = 
                l_orderMgrResVal.validateProductCode(l_strProductCode, l_strInstitutionCode);
            
            //getMarketCode()
            String l_strMarketCode = l_equityNewCashBasedOrderSpec.getMarketCode();
            
            //現物注文の場合（手数料.取引コード（SONAR）≠"立会外分売"の場合）のみ実行
            String l_strSonarTradedCode =
                l_equityNewCashBasedOrderSpec.getCommission().getSonarTradedCode();
            boolean l_isSalesOutsideMarket =
                WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_strSonarTradedCode);
            Market l_market = null;
            if (!l_isSalesOutsideMarket)
            {
                //validate市場コード()
                l_market = 
                    l_orderMgrResVal.validateMarket(l_strMarketCode,l_strInstitutionCode);
            }
            
            //立会外分売注文の場合（手数料.取引コード（SONAR）＝"立会外分売"の場合）のみ実行
            if (l_isSalesOutsideMarket)
            {
                //validateMarket()
                l_market = l_orderMgrResVal.validateMarket(
                    l_subAccount.getInstitution(),
                    l_equityNewCashBasedOrderSpec.getMarketCode());
                
                //validate立会外分売受付可能()
                OffFloorOrderProductParams l_offFloorOrderProductParams =
                    l_orderMgrResVal.validateOffFloorOrderPossible(
                        l_equityProduct.getProductId(),
                        l_market.getMarketId(),
                        l_subAccount);
                //validate立会外分売複数注文()
                l_orderMgrResVal.validateOffFloorDuplicateOrder(
                    l_equityProduct.getProductId(),
                    l_market.getMarketId(),
                    l_subAccount);
                //申込株数上限チェック
                if (l_equityNewCashBasedOrderSpec.getQuantity() > l_offFloorOrderProductParams.getMaxApplyQuantity())
                {
                    return new EqTypeNewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_01371));
                }
            }
            
            //validateインサイダー()
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
            
            //validate顧客銘柄別取引停止()
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_gentradeSubAccount,
                l_equityProduct.getProductId(),
                (isSellOrder ? OrderTypeEnum.EQUITY_SELL : OrderTypeEnum.EQUITY_BUY));
            
            //validate取引銘柄()
            WEB3EquityTradedProduct l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(
                    l_gentradeSubAccount,
                    l_equityProduct,
                    l_market,
                    isSellOrder);
            
            //validate成行指定規制()
            l_orderMgrResVal.validateMarketOrderDesignateCtrl(
                l_equityTradedProduct,
                l_equityNewCashBasedOrderSpec.isMarketOrder(),
                isSellOrder,
			    l_equityNewCashBasedOrderSpec.getExecConditionType());

            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();

            //validate取扱可能市場()
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct);
            
            //getTaxType()
            TaxTypeEnum l_taxType = l_equityNewCashBasedOrderSpec.getTaxType();
            
            //is特定口座開設
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
            
            //validate特定口座取扱規制()
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_equityNewCashBasedOrderSpec.getTaxType(),
                l_equityProduct,
                !isSellOrder);
            
            //validate注文条件
            Date l_datFirstOrderBizDate = null;
            if ((l_equityNewCashBasedOrderSpec.getFirstOrderUnitId() != null) &&
                (l_equityNewCashBasedOrderSpec.getFirstOrderUnitId().longValue() > 0))
            {
				//注文繰越時の発注審査
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
            
            //validate株数
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_equityNewCashBasedOrderSpec.getQuantity());
            
            //現物注文の場合（手数料.取引コード（SONAR）≠"立会外分売"の場合）のみ実行
            if (!l_isSalesOutsideMarket)
            {
                //validate注文単価()
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
            
            //1.30.validateW指値注文(補助口座, long, double, String, double, String, EqTypeExecutionConditionType,
            //String, 取引銘柄, boolean, String, OrderCategEnum, double, String, OrderTypeEnum)
            //引数は以下の通りに設定する。 
            //補助口座　@：　@引数の補助口座オブジェクト 
            //注文単位ＩＤ　@：　@0(：新規注文) 
            //指値　@：　@getLimitPriceの戻り値 
            //発注条件　@：　@株式注文内容.get発注条件( ) 
            //発注条件単価　@：　@株式注文内容.get逆指値基準値( ) 
            //（W指値）訂正指値　@：　@株式注文内容.get（W指値）訂正指値( ) 
            //（W指値）執行条件　@：　@株式注文内容.get（Ｗ指値）執行条件( ) 
            //（W指値）有効状態区分　@：　@null（固定） 
            //取引銘柄　@：　@validate取引銘柄( )の戻り値の取引銘柄オブジェクト 
            //is買注文　@：　@株式注文内容.isBuyOrder( ) 
            //弁済区分　@：　@null（固定） 
            //注文カテゴリ　@：　@OrderCategEnum.ASSET（現物注文） 
            //株数　@：　@株式注文内容.getQuantity() 
            //値段条件　@：　@株式注文内容.get値段条件() 
            //注文種別　@：　@株式注文内容.isSellOrder() == falseの場合、現物買注文 
            //　@　@　@　@　@　@　@　@　@株式注文内容.isSellOrder() == trueの場合、現物売注文
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

            //売り注文の場合のみ
            if (isSellOrder && !l_blnIsReverseOrder)
            {
                //validate売付可能数量()
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
     * （calc概算受渡代金）<BR>
     * <BR>
     * 概算受渡金額を算出して返却する。<BR>
     * シーケンス図「（注文）calc概算受渡代金」参照。
     * @@param l_commission (手数料)
     * @@param l_dblCalcUnitPrice (計算単価)
     * @@param l_subAccount (補助口座)
     * @@param l_tradedProduct (取引銘柄) 
     * @@param l_dblQuantity (株数)
     * @@param l_isSellOrder (is売注文)
     * @@param l_dblExecutedQuantity (約定数量)
     * @@param l_dblExecutedAmount (合計約定金額)
     * @@param l_isSkipPriceCheck (isSkip金額チェック)
     * @@param l_isRestraintConsideration (is拘束考慮)
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

        // 1.1. 概算受渡代金計算結果()
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryPrice = 
            new WEB3EquityEstimatedDeliveryPrice();
        
        // 1.2. getInstance()
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal = 
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                    
        // 1.4. set計算単価(double)
        l_estimatedDeliveryPrice.setCalcUnitPrice(l_dblCalcUnitPrice);
        
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        //拘束売買代金（合計値）または売買代金（合計値）、または諸経費計算用代金
        double l_dblRestraintTurnover;
        //拘束売買代金または売買代金
        double l_dblAmount;
             
        // 1.5. 拘束売買代金を算出する場合
        if (l_isSellOrder == false && l_isRestraintConsideration == true)
        {
            // 1.5.2. calc拘束売買代金(株数, 計算単価, 部店ID, 手数料商品コード, is指値)
            l_dblAmount = l_bizLogic.calcRestraintTurnover(
                l_dblQuantity - l_dblExecutedQuantity,
                l_dblCalcUnitPrice,
                l_subAccountRow.getBranchId(),
                l_commission.getCommissionProductCode(),
                l_commission.isLimitPrice());
        }
        // 1.6. 売買代金を算出する場合
        else
        {
            // 1.6.1. calc売買代金(株数, 計算単価)
            l_dblAmount = l_bizLogic.calcTurnover(l_dblQuantity - l_dblExecutedQuantity, l_dblCalcUnitPrice);
        }

        // 1.7. 拘束売買代金（合計値）または売買代金（合計値）を計算する
        l_dblRestraintTurnover = l_dblAmount + l_dblExecutedAmount;

        // 1.8. set拘束売買代金(double)
        if (l_isSellOrder == false)
        {
            l_estimatedDeliveryPrice.setRestraintTurnover(l_dblRestraintTurnover);
        }
        else
        {
            l_estimatedDeliveryPrice.setRestraintTurnover(0.0D);
        }

        // 1.9. set諸経費計算用代金(double)
        l_commission.setExpensesCalcAmount(l_dblRestraintTurnover);

        // 1.10. validate取引可能上限金額(部店, 市場, 諸経費計算用代金, 口座タイプ)
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
        
        // 1.11. calc委託手数料(手数料, 補助口座)
        l_bizLogic.calcCommission(l_commission, l_subAccount);
        double l_bdlCalcCommission = l_commission.getCommission();
        
        // set委託手数料
        l_estimatedDeliveryPrice.setCommissionFee(l_bdlCalcCommission);

        // 1.12. calc消費税(手数料金額, 基準日, 補助口座)
        double l_dblCalcSalesTax = l_bizLogic.calcSalesTax(
            l_bdlCalcCommission,
            l_commission.getOrderBizDate(),
            l_subAccount);
            
        // set委託手数料消費税
        l_estimatedDeliveryPrice.setCommissionFeeTax(l_dblCalcSalesTax);
        
        // 1.13. set概算受渡代金(double)
        BigDecimal l_bdEstimateDeliveryRestraintTurnover;
        BigDecimal l_bdRestraintTurnover = new BigDecimal(l_dblRestraintTurnover);
        BigDecimal l_bdCalcCommission = new BigDecimal(l_bdlCalcCommission);
        BigDecimal l_bdCalcSalesTax = new BigDecimal(l_dblCalcSalesTax);
        if (l_isSellOrder == false)
        {
            // 買付の場合
            l_bdEstimateDeliveryRestraintTurnover =
                l_bdRestraintTurnover.add(l_bdCalcCommission).add(l_bdCalcSalesTax);
            log.debug(
                "****** 諸経費計算用代金(買の場合 ： 拘束売買代金) ： ["
                    + l_bdRestraintTurnover.doubleValue()
                    + "]");
            log.debug(
                "****** 手数料 ： [" + l_bdCalcCommission.doubleValue() + "]");
            log.debug(
                "****** 消費税 ： [" + l_bdCalcSalesTax.doubleValue() + "]");
            log.debug(
                "****** 概算受渡代金 = 諸経費計算用代金 ＋（手数料 ＋ 消費税） ： ["
                    + l_bdEstimateDeliveryRestraintTurnover.doubleValue()
                    + "]");
        }
        else
        {
            // 売付の場合
            l_bdEstimateDeliveryRestraintTurnover = l_bdRestraintTurnover.subtract(l_bdCalcCommission).subtract(l_bdCalcSalesTax);
            log.debug(
                "****** 諸経費計算用代金(売の場合 ： 売買代金) ： ["
                    + l_bdRestraintTurnover.doubleValue()
                    + "]");
            log.debug(
                "****** 手数料 ： [" + l_bdCalcCommission.doubleValue() + "]");
            log.debug(
                "****** 消費税 ： [" + l_bdCalcSalesTax.doubleValue() + "]");
            log.debug(
                "****** 概算受渡代金 = 諸経費計算用代金 －（手数料 ＋ 消費税） ： ["
                    + l_bdEstimateDeliveryRestraintTurnover.doubleValue()
                    + "]");
        }
        l_estimatedDeliveryPrice.setEstimateDeliveryAmount(l_bdEstimateDeliveryRestraintTurnover.doubleValue());
        
        log.exiting(STR_METHOD_NAME);
        return l_estimatedDeliveryPrice;       
    }
    
    /**
     * (calc概算受渡代金)<BR>
     * 概算受渡金額を算出して返却する。<BR>  
     * <BR>
     * W指値&&買注文の場合は、切替未完了であれば<BR>  
     * リミット注文／ストップ注文の両方で概算受渡代金を計算し、<BR>  
     * 高いほうの金額（拘束する金額）を戻り値オブジェクトに設定し返却する。<BR>  
     * <BR>
     * シーケンス図「（注文）calc概算受渡代金（W指値考慮）１」「（注文）calc概算受渡代金（W指値考慮）２」参照。<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 注文の指値。<BR>
     * （成行指定の場合は、0）<BR>
     * @@param l_dblWLimitPrice - (（W指値）訂正指値)<BR>
     * ストップ注文の訂正指値。<BR> 
     * （ストップ注文が成行指定の場合は、0）<BR>
     * @@param l_dblStopOrderBasePrice - (逆指値基準値)<BR>
     * ストップ注文への切替を行う基準値。<BR>
     * @@param l_execConditionType - (執行条件)<BR>
     * 注文の執行条件。<BR>
     * @@param l_execWConditionType - (（W指値）執行条件)<BR>
     * ストップ注文の執行条件。<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件。<BR>
     * @@param l_strCheckCurrentPrice - (確認時取得時価)<BR>
     * 確認時に取得した時価。<BR>
     * （上り処理で確認処理時に時価を取得済の場合、<BR>  
     * 　@完了処理で取得済の時価を引き継ぎたい場合にセット。<BR>
     * 　@引き継ぎたくない場合は、nullまたは0をセット。）<BR>
     * @@param l_blnIsStopOrderValid - (isストップ注文有効)<BR>
     * W指値注文で、ストップ注文に切替が完了しているかどうかを設定する。<BR>
     * （true：　@ストップ注文への切替が完了。<BR>
     * 　@false：　@ストップ注文への切替が未完了 または notW指値注文。）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_tradedProduct - (取引銘柄))<BR>
     * 取引銘柄<BR>
     * @@param l_dblQuantity - (株数)<BR>
     * 株数<BR>
     * @@param l_blnIsSellOrder - (is売注文)<BR>
     * 売注文の場合はtrue、買注文の場合はfalseを指定する。<BR>
     * @@param l_dblExecQuantity - (約定数量)<BR>
     * 注文単位.約定数量<BR>
     * @@param l_bdlExecutedAmount - (合計約定金額)<BR>
     * 注文単位.合計約定金額<BR>
     * @@param l_blnIsSkipAmountRange - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
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
        // 事前処理
        //===================================================================
        // 引数のオブジェクトのNotNullチェック        
        if (l_commission == null || l_subAccount == null || l_tradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        // 発注審査個別チェックOBJの取得
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                
        // 株式計算サービスOBJの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        // isSTOP高拘束方式部店の取得
        long l_lngEstimatePriceCalcForm = l_orderManagerReusableValidations.
            getEstimatePriceCalcForm(l_commission.getCommissionProductCode(), l_subAccount);
        boolean l_blnIsStopQuantityRestraintBranch = 
            (l_lngEstimatePriceCalcForm == WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT);
        
        // 確認時時価のdouble化（nullの場合0とする）
        double l_dblCheckPrice = 0.0D;
        if (l_strCheckCurrentPrice != null)
        {
            l_dblCheckPrice = Double.parseDouble(l_strCheckCurrentPrice);
        }
        

        //===================================================================
        // リミット注文での概算受渡代金計算
        // （ストップ有効時はストップ注文での概算受渡代金計算も兼ねる）
        //===================================================================
        // 計算単価（リミット注文用）算出
        // (1)①@成行注文 or ②買注文 ＆ STOP高拘束部店 ＆ 不出来引成
        // 　@(1-1)確認時時価が未設定時　@：時価
        // 　@(1-2)確認時時価が設定時　@　@：確認時時価
        // (2)上記以外　@　@　@　@　@　@　@　@　@：指値
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
        
        // 手数料オブジェクト（リミット注文用）の生成
        WEB3GentradeCommission l_commission1 = copyCommission(l_commission);
        l_commission1.setIsLimitPrice(l_dblLimitPrice != 0.0D);
        
        // 概算受渡代金計算（リミット注文用）
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryAmount1 =
            this.calcEstimateDeliveryAmount(
                l_commission1, l_dblCalcPrice1, l_subAccount, l_tradedProduct, l_dblQuantity,
                l_blnIsSellOrder, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountRange, true);


        //===================================================================
        // 1回計算パターン時のリターン
        // （リミット注文の概算受渡代金を返値とする）
        // ①@売注文の場合
        // ②Ｗ指値（リミット／ストップともに有効）でない場合
        // ③Ｗ指値注文だが、ストップ注文有効として算出する場合
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
        // ストップ注文での概算受渡代金計算
        //===================================================================
        // 計算単価（ストップ注文用）算出
        // (1)①@成行注文 or ②STOP高拘束部店 ＆ 不出来引成
        // 　@(1-1)確認時時価が未設定時　@：時価
        // 　@　@　@（ただし、リミット計算時に取得していた場合は同じ値を使用する）
        // 　@(1-2)確認時時価が設定時　@　@：確認時時価
        // (2)上記以外　@　@　@　@　@　@　@　@　@：訂正指値
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
        
        // 手数料オブジェクト（ストップ注文用）の生成
        WEB3GentradeCommission l_commission2 = copyCommission(l_commission);
        l_commission2.setIsLimitPrice(l_dblWLimitPrice != 0.0D);
        
        // 概算受渡代金計算（ストップ注文用）
        WEB3EquityEstimatedDeliveryPrice l_estimatedDeliveryAmount2 =
            this.calcEstimateDeliveryAmount(
                l_commission2, l_dblCalcPrice2, l_subAccount, l_tradedProduct, l_dblQuantity,
                l_blnIsSellOrder, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountRange, true);
        

        //===================================================================
        // 2回計算パターン時のリターン
        // （リミット／ストップの内、高い方の概算受渡代金を返値とする）
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
     * (validate現物株式訂正注文) <BR>
     * 注文訂正内容のチェックを実施する。 <BR>
     * （validateChangeOrder） <BR>
     * <BR>
     * this.validate現物株式訂正注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate現物株式訂正注文()に指定する引数] <BR>
     * 補助口座：　@パラメータ.補助口座 <BR>
     * 株式注文訂正内容：　@パラメータ.株式注文訂正内容 <BR>
     * isSkip遅延状況チェック：　@false（固定）<BR>
     * <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * @@param l_eqChangeOrderSpec - (株式注文訂正内容) <BR>
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
        
        //this.validate現物株式訂正注文()に処理を委譲（delegate）する。
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            this.validateChangeOrder(l_subAccount, l_eqChangeOrderSpec, false);
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderValidationResult;            
    }
    
    /**
     * (validate現物株式訂正注文)<BR>
     * 注文訂正内容のチェックを実施する。<BR>
     * <BR>
     * シーケンス図「（注文）訂正発注審査」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_eqTypeChangeOrderSpec - (株式注文訂正内容)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
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
            
            //getOrderUnits(注文ID : long)
            OrderUnit[] l_orderUnits = getOrderUnits(l_lngOrderId);
            EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            
            //getOrderCateg( )
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            
            //OrderCateg != ”現物注文”
            if (!OrderCategEnum.ASSET.equals(l_orderCateg))
            {
                //validate新規建注文訂正(補助口座, EqTypeChangeOrderSpec, boolean)
                return validateChangeOrder(
                    l_gentradeSubAccount,
                    (WEB3MarginChangeOrderSpec)l_eqTypeChangeOrderSpec,
                    l_blnIsSkipDelayStatusCheck);
            }
            
            //訂正可能確認(補助口座, long, boolean)
            validateEnableChangeOrder(
                l_gentradeSubAccount,
                l_lngOrderId,
                l_blnIsSkipDelayStatusCheck);
            
            //create株式注文内容
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
            
            //validate成行指定規制
            l_orderMgrResVal.validateMarketOrderDesignateCtrl(
                l_equityTradedProduct,
                l_equityNewCashBasedOrderSpec.isMarketOrder(),
                l_equityNewCashBasedOrderSpec.isSellOrder(),
                l_equityNewCashBasedOrderSpec.getExecConditionType());
                
            //get初回注文の注文単位(注文単位)
            OrderUnit l_firstOrderUnit = this.getFirstOrderUnit(l_orderUnit);
            EqtypeOrderUnitRow l_firstOrderUnitRow = (EqtypeOrderUnitRow) l_firstOrderUnit.getDataSourceObject();
            
            //getTradedProduct( )

            //validate注文条件
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
            //is特定口座開設
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
            
            //get取引店( )
            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
            
            //validate株数
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_equityNewCashBasedOrderSpec.getQuantity());

            //getLimitPrice( )
            double l_dbLimitPrice =
                l_equityNewCashBasedOrderSpec.getLimitPrice();
            
            //validate注文単価
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
            
            //get発注条件( )
            String l_strOrderCond =
                l_equityNewCashBasedOrderSpec.getOrderCond();
            
            //get逆指値基準値( )
            double l_dbStopLimitPriceBasePrice =
                l_equityNewCashBasedOrderSpec.getStopLimitPriceBasePrice();
            
            //get（Ｗ指値）執行条件( )
            EqTypeExecutionConditionType l_WlimitExecCondType =
                l_equityNewCashBasedOrderSpec.getWlimitExecCondType();
            
            //get（W指値）訂正指値( )
            double l_dbWLimitPriceChange =
                l_equityNewCashBasedOrderSpec.getWLimitPriceChange();
            String l_strWLimitPriceChange =
                WEB3StringTypeUtility.formatNumber(l_dbWLimitPriceChange);
            
            WEB3EquityChangeOrderUnitEntry l_equityChangeOrderUnitEntry =
                (WEB3EquityChangeOrderUnitEntry)l_equityChangeOrderSpec.getChangeOrderUnitEntry();
            
            //validateW指値注文()
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

            //validate訂正項目
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
            
            //validate訂正時注文Rev上限
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
     * (訂正可能確認) <BR>
     * 指定注文の訂正が実施可能かをチェックし、原注文の注文単位オブジェクトを返却する。 <BR>
     * （validateEnableChangeOrder） <BR>
     * <BR>
     * シーケンス図「（注文）訂正可能確認」参照。 <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * <BR>
     * 補助口座オブジェクト <BR>
     * @@param l_lngOrderId - (注文ID) <BR>
     * <BR>
     * 訂正対象注文ID <BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
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

            //commonFirstValidationsForAllOperations(補助口座 : SubAccount)
            l_orderMgrResVal.commonFirstValidationsForAllOperations(
                l_subAccount);
            
            //validateOrderIdForExistence(注文ID : long)
            Order l_order = l_orderMgrResVal.validateOrderIdForExistence(l_lngOrderId);
            
            //validate注文訂正可能状態(注文,isSkip遅延状況チェック)
            l_orderMgrResVal.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);
            
            OrderUnit[] l_orderUnits = l_order.getOrderUnits();
            l_orderUnit  = l_orderUnits[0];
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            EqTypeProduct l_eqTypeProduct =(EqTypeProduct)l_orderUnit.getProduct();
            
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();
            
            //validate市場コード
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_eqtypeOrderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_market = l_orderMgrResVal.validateMarket(l_strMarketCode,l_strInstitutionCode);
            
            //validateインサイダー
            l_orderMgrResVal.validateInsider(l_subAccount, l_eqTypeProduct);
            
            //validate顧客銘柄別取引停止
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
            
            //validate取引銘柄
            WEB3EquityTradedProduct l_equityTradedProduct =
                (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(
                    l_eqTypeProduct,
                    l_market);
           
            SubAccountRow l_subAccountRow = 
                (SubAccountRow)l_subAccount.getDataSourceObject();
            WEB3GentradeSubAccount l_gentradeSubAccount = new WEB3GentradeSubAccount(l_subAccountRow);
            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
            
            //validate取扱可能市場
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct);
            
			//validate閉局後訂正取消受付可能
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
     * (submit現物株式注文訂正) <BR>
     * 現物株式注文訂正を登録する。 <BR>
     * （submitChangeOrderのオーバーライド） <BR>
     * シーケンス図「（注文）注文訂正更新」参照。 <BR>
     * <BR>
     * (*) xTradeの標準実装では、 <BR>
     * 訂正の代理入力が考慮されていないため当該手続きが必要。 <BR>
     * <BR>
     * １）　@取引パスワードチェック <BR>
     * 　@株式注文チェック.validate取引パスワード( )をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@代理入力者：　@株式注文訂正内容.get扱者( ) <BR>
     * 　@補助口座：　@（引数より編集） <BR>
     * 　@パスワード：　@（引数より編集） <BR>
     * <BR>
     * ２）　@標準実装メソッドをコールする <BR>
     * <BR>
     * 　@super.submitChangeOrder( )をコールする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@補助口座：　@（引数より編集） <BR>
     * 　@株式注文訂正内容：　@（引数より編集） <BR>
     * 　@取引パスワード：　@(*1) <BR>
     * 　@isSkip発注審査：　@true <BR>
     * <BR>
     * 　@(*1)　@取引パスワードの指定 <BR>
     * 　@－代理入力の場合（株式注文訂正内容.get扱者( )≠null）、<BR>
     *       顧客の取引パスワードをDBより取得し、指定する。<BR>
     * 　@－以外、引数の取引パスワードを指定する。 <BR>
     * <BR>
     * @@param l_subAccount - 補助口座
     * @@param l_eqtypeChangeOrderSpec - 株式注文訂正内容
     * @@param l_strPassword - 取引パスワード
     * @@param l_isSkipValidateOrder - (isSkip発注審査) <BR>
     * 発注審査を実施するかどうかのフラグ。 <BR>
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
        //2. 株式注文訂正内容::get扱者
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

        //3. validate取引パスワード(Trader, SubAccount, String)(注文チェック::validate取引パスワード)
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
            //代理入力の場合
            //顧客オブジェクトを取得する
            MainAccount l_mainAccount = l_subAccount.getMainAccount();

            //5. getTradingPassword( )
            //顧客の取引パスワードを取得する
            l_strTradingPassword = l_crypt.decrypt(l_mainAccount.getTradingPassword());
        }
        else
        {
            //以外、引数の取引パスワードを指定する
            l_strTradingPassword = l_strPassword;
        }

        //6. submitChangeOrder
        //(補助口座 : SubAccount, 株式注文訂正内容 : EqTypeChangeOrderSpec, 取引パスワード : String, isSkip発注審査 : boolean)
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
     * (validate現物株式取消) <BR>
     * <BR>
     * 指定注文の取消が実施可能かをチェックし、<BR>
     * 取消不可能な場合は例外をthrowする。<BR>
     * （validateCancelOrderのオーバーライド）<BR>
     * <BR>
     * 取消対象注文が信用取引の場合、validate信用注文取消メソッド( )をコールする。<BR>
     * <BR>
     * シーケンス図「（注文）取消可能確認」参照。<BR>
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
            
            //1.5. [信用取引注文の場合(注文カテゴリ != ”現物注文”）]
            //・this.validate信用注文取消（引数.補助口座、引数.株式注文取消内容）
            // をコール、戻り値を返却し、処理を終了する。
            if (!OrderCategEnum.ASSET.equals(l_orderUnit.getOrderCateg()))
            {
                //1.5.1. validate信用注文取消()
                return this.validateMarginOrderCancel(l_gentradeSubAccount, l_equityCancelOrderSpec);                  
            }
            
            //1.6. commonFirstValidationsForAllOperations()
            l_equityTypeOrderManagerReusableValidations.commonFirstValidationsForAllOperations(l_subAccount);            
            
            //1.7. validateOrderIdForExistence()
            Order l_order = l_equityTypeOrderManagerReusableValidations.validateOrderIdForExistence(
                l_equityCancelOrderSpec.getOrderId());
            
            //1.9. validate注文取消可能状態()
            l_equityTypeOrderManagerReusableValidations.validateOrderForCancellation(l_order);
  
            //1.10. get取引店()
            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();
            
            //1.11. getMarket()
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            Market l_market = l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
            
            //1.12. 立会外分売注文以外の場合（＝注文単位.取引コード（SONAR）≠"立会外分売"の場合）のみ実行
            if (!WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                //1.12.1. validate市場コード()
                String l_strMarketCode = l_market.getMarketCode();
                String l_strInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
                l_market = l_equityTypeOrderManagerReusableValidations.validateMarket(
                    l_strMarketCode,
                    l_strInstitutionCode);
            }
            
            //1.13. getProduct()
            WEB3EquityProduct l_equityProduct = (WEB3EquityProduct)l_orderUnit.getProduct();
            
            //1.14. 立会外分売注文の場合（＝注文単位.取引コード（SONAR）=="立会外分売"の場合）のみ実行
            if (WEB3TransactionTypeSONARDef.SALES_OUTSIDE_MARKET.equals(l_orderUnitRow.getSonarTradedCode()))
            {
                //1.14.1. validate立会外分売受付可能()
                l_equityTypeOrderManagerReusableValidations.validateOffFloorOrderPossible(
                    l_equityProduct.getProductId(),
                    l_market.getMarketId(),
                    l_subAccount);
            }
            
            //1.15. validate取引銘柄()
            WEB3EquityTradedProduct l_tradedProduct =
                (WEB3EquityTradedProduct)l_equityTypeOrderManagerReusableValidations.validateTradedProduct(
                    l_equityProduct,
                    l_market);
            
            //1.16. validate取扱可能市場()
            l_equityTypeOrderManagerReusableValidations.validateHandlingMarket(
                l_gentradeBranch,
                l_tradedProduct);

			//1.17. validate閉局後訂正取消受付可能()
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
     * (submit現物株式注文取消) <BR>
     * 現物株式注文取消を登録する。<BR>
     * （submitCancelOrderのオーバーライド）<BR>
     * シーケンス図「（注文）注文取消更新」参照。<BR>
     * <BR>
     * (*) xTradeの標準実装では、<BR>
     * 取消の代理入力が考慮されていないため当該手続きが必要。<BR>
     * <BR>
     * １）　@取引パスワードチェック<BR>
     * 　@注文チェック.validate取引パスワード( )をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@代理入力者：　@株式注文取消内容.get扱者( )<BR>
     * 　@補助口座：　@（引数より編集）<BR>
     * 　@パスワード：　@（引数より編集）<BR>
     * <BR>
     * ２）　@標準実装メソッドをコールする<BR>
     * <BR>
     * 　@super.submitCancelOrder( )をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@補助口座：　@（引数より編集）<BR>
     * 　@株式注文取消内容：　@（引数より編集）<BR>
     * 　@取引パスワード：　@(*1)<BR>
     * 　@isSkip発注審査：　@true<BR>
     * <BR>
     * 　@(*1)　@取引パスワードの指定<BR>
     * 　@－代理入力の場合（株式注文取消内容.get扱者( )≠null）、<BR>
     * 顧客の取引パスワードをDBより取得し、指定する。<BR>
     * 　@－以外、引数の取引パスワードを指定する。<BR>
     * 　@
     * @@param l_subAccount - 補助口座
     * @@param l_cancelOrderSpec - 株式注文取消内容
     * @@param l_strPassword - 取引パスワード
     * @@param l_isSkipValidateOrder - isSkip発注審査<BR>
     *    発注審査を実施するかどうかのフラグ。
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
        //2. get扱者( )(株式注文取消内容::get扱者
        WEB3EquityCancelOrderSpec l_equityCancelOrderSpec = (WEB3EquityCancelOrderSpec)l_cancelOrderSpec;
        Trader l_trader = l_equityCancelOrderSpec.getTrader();

        //3. validate取引パスワード(Trader, SubAccount, String)(注文チェック::validate取引パスワード)
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
            //代理入力の場合
            //顧客オブジェクトを取得する
            MainAccount l_mainAccount = l_subAccount.getMainAccount();

            //5. getTradingPassword( )
            //顧客の取引パスワードを取得する
            l_strTradingPassword = l_crypt.decrypt(l_mainAccount.getTradingPassword());
        }
        else
        {
            //以外、引数の取引パスワードを指定する
            l_strTradingPassword = l_strPassword;
        }

        //6. submitCancelOrder(補助口座 : SubAccount, 株式注文取消内容 : CancelOrderSpec, 取引パスワード : String, is発注審査Skip(＝false) : boolean)
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
     * (get注文ID) <BR>
     * 指定条件に一致する注文の注文IDを返却する。<BR>
     * （SONARからのリクエストキューに該当する行を取得する場合に利用）<BR>
     * <BR>
     * １）　@証券会社コード、部店コードより部店ＩＤを取得する。<BR>
     * ２）　@以下の条件で注文単位テーブルを検索し、<BR>
     * 　@一致した行の注文ＩＤを返却する。<BR>
     * <BR>
     * [検索条件]<BR>
     * 　@注文単位.部店ID == （取得した部店ID）<BR>
     * 　@注文単位.銘柄タイプ == パラメータ.商品タイプ<BR>
     * 　@注文単位.識別コード == パラメータ.識別コード<BR>
     * <BR>
     * 該当行が存在した場合、複数行一致した場合は<BR>
     * 例外をスローする。<BR>
     * class: WEB3BusinessLayerException <BR>
     * tag:   BUSINESS_ERROR_00295 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード<BR>
     * @@param l_strBranchCode - 部店コード<BR>
     * @@param l_productTypeEnum - (商品タイプ) <BR>
     * （ProductTypeEnumにて定義） <BR>
     * @@param l_strOrderRequestNumber - 識別コード <BR>
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

        //証券会社
        Institution l_institution = null;
        //部店
        Branch l_branch = null;
        try
        {
            //証券会社
            l_institution =
                l_accountManager.getInstitution(l_strInstitutionCode);
            //部店
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
            //部店コード --> 部店ID
            Long l_lngBranchId = new Long(l_branch.getBranchId());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" branch_id = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ(QA: WEB3-EQTYPE-A-CD-0038)
            l_sbWhere.append(" and order_request_number = ? "); //識別コード

            Object[] l_objEqtypeOrderUnitWhere = { 
                l_lngBranchId,                 //部店ID
                l_productTypeEnum,         //商品タイプ
                l_strOrderRequestNumber  //識別コード
                }; 

            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    l_objEqtypeOrderUnitWhere);

            if (l_lisRecords.size() == 0)
            {
                log.error("該当する注文IDデータがありません。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当する注文IDデータがありません。");
            }
            if (l_lisRecords.size() > 1)
            {
                log.error("該当する注文IDデータは複数行ある。");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当する注文IDデータは複数行ある。");
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
     * (get注文単位) <BR>
     * 指定条件に一致する注文の注文単位オブジェクトを返却する。 <BR>
     * （SONARからのリクエストキューに該当する行を取得する場合に利用） <BR>
     *  <BR>
     * １）　@証券会社コード、部店コードより部店ＩＤを取得する。 <BR>
     * ２）　@以下の条件で注文単位テーブルを検索し、 <BR>
     * 　@一致した行で注文単位オブジェクトを生成し返却する。 <BR>
     *  <BR>
     * [検索条件] <BR>
     * 　@注文単位.部店ID == （取得した部店ID） <BR>
     * 　@注文単位.銘柄タイプ == パラメータ.商品タイプ <BR>
     * 　@注文単位.識別コード == パラメータ.識別コード <BR>
     *  <BR>
     * 該当行が存在した場合、複数行一致した場合は <BR>
     * 例外をスローする。 <BR>
     * class: WEB3SystemLayerException <BR>
     * tag:   BUSINESS_ERROR_00295 <BR>
     * <BR>
     * @@param l_strInstitutionCode - 証券会社コード <BR>
     * @@param l_strBranchCode - 部店コード <BR>
     * @@param l_productTypeEnum - 商品タイプ <BR>
     *    （ProductTypeEnumにて定義） <BR>
     * @@param l_strOrderRequestNumber - 識別コード <BR>
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

        //証券会社
        Institution l_institution = null;
        //部店
        Branch l_branch = null;
        try
        {
            //証券会社
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            //部店
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
            //部店コード --> 部店ID
            Long l_lngBranchId = new Long(l_branch.getBranchId());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" branch_id = ? "); //部店ID
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ(QA: WEB3-EQTYPE-A-CD-0038)
            l_sbWhere.append(" and order_request_number = ? "); //識別コード         
            
            Object[] l_objEqtypeOrderUnitWhere = { 
                l_lngBranchId,            //部店ID
                l_productTypeEnum,        //商品タイプ
                l_strOrderRequestNumber   //識別コード
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
                    "該当する注文IDデータがありません。");
            }
            if (l_lisRecords.size() > 1)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00295,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当する注文IDデータは複数行がある。");
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
     * (get注文単位一覧) <BR>
     * 指定条件に一致する注文の注文単位オブジェクトの一覧を返却する。<BR>
     * （getOrderUnitsのオーバーロード）<BR>
     * <BR>
     * １）　@戻り値オブジェクトのインスタンスを生成する。<BR>
     * <BR>
     * ２）　@検索条件を追加する。<BR>
     * <BR>
     * ２－１）　@引数.検索条件文字列の先頭に、<BR>
     * 　@　@　@　@　@"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ?"<BR>
     * 　@　@　@　@　@を付加する。<BR>
     * <BR>
     * ２－２）　@引数.検索条件データコンテナの先頭に、<BR>
     * 　@　@　@　@　@検索条件文字列先頭に付加したパラメータリストを追加する。<BR>
     * 　@　@　@　@　@※引数の補助口座オブジェクト、及び引数の銘柄タイプより設定する。<BR>
     * <BR>
     * ３）　@QueryProcessor.doFindAllQuery( )により、注文単位オブジェクトのListを取得する。<BR>
     * <BR>
     * 　@　@　@doFindAllQuery(,注文単位Row.TYPE<BR>
     *                                      ２－１）の検索条件文字列,<BR>
     *                                      引数のソート条件,<BR>
     *                                      null,<BR>
     *                                      ２－２）の検索条件データコンテナ)<BR>
     * <BR>
     * ４）　@ArrayListを生成する。<BR>
     * <BR>
     * ５）　@取得した注文単位オブジェクトのListの要素数分以下の処理をLoopする。<BR>
     * 　@　@　@①@　@株式注文マネージャ.toOrderUnit((*)注文単位ROW)メソッドをコールする。<BR>
     * 　@　@　@②　@①@の戻り値をArrayListに追加する。<BR>
     * <BR>
     * 　@　@　@(*)注文単位ROW・・・取得した注文単位オブジェクトを<BR>
     * EqTypeOrderUnitRowにキャストする。<BR>
     * <BR>
     * ６）　@ArrayListを返却する。<BR>
     * <BR>
     * @@param l_genSubAccount - (補助口座) <BR>
     * 補助口座オブジェクト <BR>
     * @@param l_productTypeEnum - (銘柄タイプ) <BR>
     * 銘柄タイプ（ProductTypeEnumオブジェクト） <BR>
     * @@param l_srtSearchCond - 検索条件 文字列<BR>
     * @@param l_searchCondContainers - 検索条件データコンテナ <BR>
     * 検索条件 <BR>
     * @@param l_srtSortCond - ソート条件<BR>
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
            //口座IDを取得する。
            Long l_lngAccountId = new Long(l_genSubAccount.getAccountId());
            //補助口座IDを取得する。
            Long l_lngSubAccountId = new Long(l_genSubAccount.getSubAccountId());

            //２）　@検索条件を追加する。
            //２－１）　@引数.検索条件文字列の先頭に、
            //"口座ID = ?　@and　@補助口座ID = ? and 銘柄タイプ = ?"を付加する。
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" account_id = ? "); //口座ID = ?
            l_sbWhere.append(" and sub_account_id = ? "); //補助口座ID = ?
            l_sbWhere.append(" and product_type = ? "); //銘柄タイプ = ?

            //Object[] l_objEqtypeOrderUnitWhere = new Object[3 + l_searchCondContainers.length];
            //※引数の補助口座オブジェクト、及び引数の銘柄タイプより設定する。
            Object[] l_objEqtypeOrderUnitWhere = { 
                l_lngAccountId, //口座ID 
                l_lngSubAccountId, //補助口座ID
                new Integer(l_productTypeEnum.intValue())  //銘柄タイプ
                };

            //２－２）　@引数.検索条件データコンテナの先頭に、
            //検索条件文字列先頭に付加したパラメータリストを追加する。
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
            
            // ４）　@ArrayListを生成する。
            l_lisResult = new ArrayList();
            // ５）　@取得した注文単位オブジェクトのListの要素数分以下の処理をLoopする。
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
     * (is出来るまで注文単位) <BR>
     * （is出来るまで注文単位(注文単位)のオーバーロードメソッド）<BR>
     * <BR>
     * 「出来るまで注文」の注文かどうかを判定する。<BR>
     * 「出来るまで注文」の場合はtrueを、「出来るまで注文」ではない場合はfalseを、<BR>
     * それぞれ返却する。<BR>
     * <BR>
     * １）　@this.getOrderUnit(引数.注文単位ID)で、注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@this.is出来るまで注文単位(注文単位)にdelegateする。<BR>
     * @@param l_lngOrderUnitId - (注文単位ID) <BR>
     * 注文単位オブジェクト.注文単位ID。 <BR>
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
	 * (is出来るまで注文単位) <BR>
	 * <BR>
	 * 「出来るまで注文」の注文かどうかを判定する。<BR>
	 * 「出来るまで注文」の場合はtrueを、「出来るまで注文」ではない場合はfalseを、<BR>
	 * それぞれ返却する。<BR>
	 * <BR>
	 * １）　@this.getOrderUnit(引数.注文単位ID)で、注文単位オブジェクトを取得する。<BR>
	 * <BR>
	 * ２）　@取得した注文単位.初回注文の注文単位ID≠nullの場合は、trueを返す。 <BR>
	 * 　@　@　@取得した注文単位.初回注文の注文単位ID＝nullの場合は、falseを返す。<BR>
	 * @@param l_orderUnit - (注文単位オブジェクト) <BR>
	 * 注文単位オブジェクト。 <BR>
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
     * (is（W指値）訂正成行) <BR>
     * （W指値）訂正単価が、成行指定なのか、 <BR>
     * 指値指定なのかを判定する。 <BR>
     * 成行指定の場合はtrueを、指値指定の場合はfalseを、 <BR>
     * それぞれ返却する。 <BR>
     * <BR>
     * １）　@this.getOrderUnit(引数.注文単位ID)で、 <BR>
     * 注文単位オブジェクトを取得する。 <BR>
     * <BR>
     * ２）　@取得した注文単位.（W指値）訂正指値＝null <BR>
     * または０の場合は、trueを返す。 <BR>
     * 　@　@　@取得した注文単位.（W指値）訂正指値が上記以外の場合は、 <BR>
     * falseを返す。 <BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID) <BR>
     * 注文単位オブジェクト.注文単位ID。 <BR>
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

        //取得した注文単位.（W指値）訂正指値
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
     * (validate指値) <BR>
     * 引数の指値が適切であるかどうかのチェックを行う。 <BR>
     * （* 株式発注審査チェック.validate注文単価( )に委譲する。）<BR>
     * <BR>
     * @@param l_dblLimitPrice - 指値。
     * @@param l_tradedProduct - (取引銘柄) <BR>
     * 商品・市場関連の各種エンティティから <BR>
     * データを取得する際に使用する。 <BR>
     * @@param l_subAccount - (補助口座) <BR>
     * 【会社部店商品テーブル】から <BR>
     * 概算金額計算方式を取得する際に使用する。 <BR>
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
        //株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        //株式発注審査チェック.validate注文単価( )に委譲する
        return l_orderMgrResVal.validatePrice(
            l_dblLimitPrice,
            l_tradedProduct,
            l_subAccount);
    }

    /**
     * (validate銘柄コード) <BR>
     * 取引可能銘柄チェック中、 <BR>
     * 取引所規制、会社・銘柄規制のチェックを行う。 <BR>
     * （* 株式発注審査チェック.validate銘柄コード( )に委譲する。） <BR>
     * @@param l_strProductCode - 銘柄コード
     * @@param l_strInstitutionCode - 証券会社コード
     * @@return EqTypeProduct
     * @@throws WEB3BaseException
     * @@roseuid 40839DA903C5
     */
    public WEB3EquityProduct validateProductCode(
        String l_strProductCode,
        String l_strInstitutionCode)
        throws WEB3BaseException
    {
        //株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        //株式発注審査チェック.validate銘柄コード()に委譲する 
        return l_orderMgrResVal.validateProductCode(l_strProductCode, l_strInstitutionCode); 
             
    }

    /**
     * （validate取引銘柄）<BR>
     * <BR>
     * チェック結果がOKの場合は、取引銘柄オブジェクトを返却する。<BR>
     * （* 株式発注審査チェック.validate取引銘柄(補助口座, 株式銘柄, 市場, is売注文)に委譲する。）<BR>
     * <BR>
     * @@param SubAccount l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param EqTypeProduct l_eqTypeProduct - (株式銘柄)<BR>
     * 株式銘柄オブジェクト。<BR>
     * @@param Market l_market - (市場)<BR>
     * 市場オブジェクト。<BR>
     * @@param boolean l_isSellOrder - (is売注文)<BR>
     * 売注文、買注文のフラグ。<BR>
     * 売注文の場合true、買注文の場合falseを指定する。<BR>
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
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.validate取引銘柄(補助口座, 株式銘柄, 市場, is売注文)に委譲する 
        return l_orderMgrResVal.validateTradedProduct(
            l_subAccount,
            l_eqTypeProduct,
            l_market,
            l_isSellOrder);
    }

    /**
     * (is訂正市場通知要) <BR>
     * 引数で指定された注文単位オブジェクトの訂正内容が、市場に通知が必要かどうかを判定する。<BR>
     * 市場への訂正の通知が必要な場合はtrueを、不要な場合はfalseを、それぞれ返却する。<BR>
     * <BR>
     * ・引数の注文単位オブジェクトのプロパティより、以下の判定を行う。<BR>
     * 　@以下の条件に合致する場合、市場への訂正の通知が不要と判定し、falseを返す。<BR>
     * <BR>
     * 　@注文単位.注文数量＝注文単位.市場から確認済みの数量<BR>
     * 　@かつ　@注文単位.指値＝注文単位.市場から確認済みの指値<BR>
     * 　@かつ　@注文単位.執行条件＝注文単位.市場から確認済みの執行条件<BR>
     * 　@かつ　@注文単位.値段条件＝注文単位.市場から確認済みの値段条件<BR>
     * <BR>
     * 　@以外、trueを返す。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位) <BR>
     * 注文単位オブジェクト。 <BR>
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
     * (validate注文取消可能状態) <BR>
     * <BR>
     * 注文の取消が可能か注文状態であるかどうかをチェックする。<BR>
     * （* 株式発注審査チェック.validate注文取消可能状態( )に委譲する。）
     * @@param l_order (注文)
     * @@return void
     * @@throws WEB3BaseException
     * @@roseuid 4073914A0348
     */
    public void validateOrderForCancellation(Order l_order)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrderForCancellation(Order)";

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.validate注文取消可能状態()に委譲する 
        try
        {
            l_orderMgrResVal.validateOrderForCancellation(l_order);
        }
        catch (OrderValidationException l_ove)
        {
            ProcessingResult l_processingResult = l_ove.getValidationResult().getProcessingResult();
            //例外をスローする
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }
    }
    
    /**
     * (validate注文訂正可能状態) <BR>
     * 注文の訂正が可能か注文状態であるかどうかをチェックする。 <BR>
     * <BR>
     * this.validate注文訂正可能状態()に処理を委譲（delegate）する。  <BR>
     * <BR>
     * [validate注文訂正可能状態()に指定する引数]  <BR>
     * 注文：　@パラメータ.注文 <BR>
     * isSkip遅延状況チェック：　@false（固定）<BR>
     * @@param l_order (注文)
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
     * (validate注文訂正可能状態)<BR>
     * 注文の訂正が可能か注文状態であるかどうかをチェックする。<BR>
     * <BR>
     * 株式発注審査チェック.validate注文訂正可能状態()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate注文訂正可能状態()に指定する引数]<BR>
     * 注文：　@パラメータ.注文<BR>
     * isSkip遅延状況チェック：　@パラメータ.isSkip遅延状況チェック<BR>
     * @@param l_order - (注文)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)
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
        
        //株式発注審査チェック.validate注文訂正可能状態()に処理を委譲（delegate）する。
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
            //例外をスローする
            throw new WEB3BaseException(
                l_processingResult.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_processingResult.getErrorInfo().getErrorMessage());
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate新規建注文)<BR>
     * 指定新規建注文の発注審査を行う。<BR>
     * （validateOpenContractOrderのオーバーライド）<BR>
     * <BR>
     * this.validate新規建注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate新規建注文()に指定する引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@信用新規建注文内容：　@パラメータ.信用新規建注文内容<BR>
     * 　@注文単位：　@null<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_openContractOrderSpec - (信用新規建注文内容)<BR>
     * 信用新規建注文内容オブジェクト。<BR>
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
     * (validate新規建注文)<BR>
     * 指定新規建注文の発注審査を行う。<BR>
     * （validateOpenContractOrderのオーバーライド）<BR>
     * <BR>
     * シーケンス図「（信用注文）validate新規建注文」参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_openContractOrderSpec - (信用新規建注文内容)<BR>
     * 信用新規建注文内容オブジェクト。<BR>
     * @@param l_eqtypeOrderUnit - (注文単位)<BR>
     * 株式注文単位オブジェクト。<BR>
     * <BR>
     * ※逆指値発注処理からコールされる場合のみ、<BR>
     * 　@セットされる。以外、null。<BR>
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
            //get 信用新規建注文内容
            WEB3MarginOpenContractOrderSpec l_marginOpenContractOrderSpec =
                (WEB3MarginOpenContractOrderSpec)l_openContractOrderSpec;

            //株式発注審査個別チェックオブジェクトの生成
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

            //get弁済区分
            String l_strRepaymentType =
                l_marginOpenContractOrderSpec.getRepaymentType();
            log.debug("弁済区分 = " + l_strRepaymentType);
            
            //validate信用注文(補助口座, 弁済区分)
            validateMarginOrder((WEB3GentradeSubAccount)l_subAccount, l_strRepaymentType);
            log.debug("validate信用注文(補助口座, 弁済区分)を実行");            
            
            //証券会社コードを取得する
            String l_strInstitutionCode =
                l_subAccount.getInstitution().getInstitutionCode();
            log.debug("証券会社コード = " + l_strInstitutionCode);
            
            //入力市場コード取得
            String l_strMarketCode =
                l_marginOpenContractOrderSpec.getMarketCode();
            log.debug("入力市場コード = " + l_strMarketCode);

            //validate市場コード
            WEB3GentradeMarket l_market =
                (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                    l_strMarketCode,
                    l_strInstitutionCode);
            log.debug("validate市場コードを実行");
            
            //入力銘柄コード取得
            String l_strProductCode =
                l_marginOpenContractOrderSpec.getProductCode();
            log.debug("入力銘柄コード = " + l_strProductCode);
            
            //validate銘柄コード（信用）
            WEB3EquityProduct l_equityProduct =
                l_orderMgrResVal.validateProductCode(
                    l_strProductCode,
                    l_strInstitutionCode,
                    l_strRepaymentType);
            log.debug("validate銘柄コード（信用）を実行");
            
            //validateインサイダー
            l_orderMgrResVal.validateInsider(l_gentradeSubAccount, l_equityProduct);
            log.debug("validateインサイダーを実行");
            
            // get 注文種別
            OrderTypeEnum l_orderTypeEnum;
            if (l_marginOpenContractOrderSpec.isLongOrder())
            {
                l_orderTypeEnum = OrderTypeEnum.MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.MARGIN_SHORT;
            }
            log.debug("注文種別 = " + l_orderTypeEnum);
            
            //validate顧客銘柄別取引停止
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_gentradeSubAccount,
                l_equityProduct.getProductId(),
                l_orderTypeEnum);
            log.debug("validate顧客銘柄別取引停止を実行");
                
            //isShortOrder
            boolean l_isShortOrder =
                l_marginOpenContractOrderSpec.isShortOrder();
            log.debug("l_isShortOrder = " + l_isShortOrder);

            WEB3GentradeBranch l_gentradeBranch = l_gentradeSubAccount.getWeb3GenBranch();

            //validate取引銘柄（信用）(株式銘柄, 市場, 部店, String, OrderCategEnum, boolean)
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderMgrResVal.validateTradedProductForMarginTrading(
                    l_gentradeSubAccount,
                    l_equityProduct,
                    l_market,
                    l_gentradeBranch,
                    l_strRepaymentType,
                    OrderCategEnum.OPEN_MARGIN,
                    l_isShortOrder);
            log.debug("validate取引銘柄（信用）を実行");
            
            //validate成行指定規制（信用）(取引銘柄, String, OrderCategEnum, 
            //    boolean, boolean, EqTypeExecutionConditionType)
            //取引銘柄：　@validate取引銘柄（信用）( )の戻り値の、取引銘柄オブジェクト
            //弁済区分：　@信用新規建注文内容.get弁済区分( )
            //注文カテゴリ：　@OrderCategEnum.”新規建注文”（OPEN_MARGIN）
            //is成行：　@信用新規建注文内容.isMarketOrder( )
            //is売建：　@信用新規建注文内容.isShortOrder( )
            //執行条件：　@信用新規建注文内容.getExecConditionType( )
            l_orderMgrResVal.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                OrderCategEnum.OPEN_MARGIN,
                l_marginOpenContractOrderSpec.isMarketOrder(),
                l_isShortOrder,
			    l_marginOpenContractOrderSpec.getExecConditionType());
            log.debug("validate成行指定規制（信用）を実行");
            
            //validate特定口座開設（信用）(補助口座, TaxTypeEnum, Date)
            //補助口座：　@引数の補助口座オブジェクト 
            //税区分：　@信用新規建注文内容.getTaxType( ) 
            //受渡日：　@取引銘柄.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                (WEB3GentradeSubAccount)l_subAccount,
                l_marginOpenContractOrderSpec.getTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());
            log.debug("validate特定口座開設（信用）を実行");
            
            //get弁済期限値( )
            double l_dblRepaymentNum =
                l_marginOpenContractOrderSpec.getRepaymentNum();
            log.debug("弁済期限値 = " + l_dblRepaymentNum);

            //validate取扱可能市場（信用）
            //部店：　@補助口座.get取引店( ) 
            //取引銘柄：　@validate取引銘柄( )の戻り値の取引銘柄オブジェクト 
            //市場コード：　@信用新規建注文内容.getMarketCode( ) 
            //弁済区分：　@信用新規建注文内容.get弁済区分( ) 
            //弁済期限値：　@信用新規建注文内容.get弁済期限値( ) 
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_strMarketCode,
                l_strRepaymentType,
                l_dblRepaymentNum);
            log.debug("validate取扱可能市場（信用）を実行");

            //validate特定口座取扱規制(TaxTypeEnum, 株式銘柄, boolean)
            //税区分：　@信用新規建注文内容.getTaxType( ) 
            //株式銘柄：　@validate銘柄コード( )の戻り値の株式銘柄オブジェクト 
            //is買注文：　@信用新規建注文内容.isLongOrder( ) 
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_marginOpenContractOrderSpec.getTaxType(),
                l_equityProduct,
                l_marginOpenContractOrderSpec.isLongOrder());
            log.debug("validate特定口座取扱規制を実行");

            // get 注文失効日
            Date l_orderExpDate =
                l_marginOpenContractOrderSpec.getOrderExpDate();
            log.debug("注文失効日 = " + l_orderExpDate);
            
			Date l_datFirstOrderBizDate = null;
			if ((l_marginOpenContractOrderSpec.getFirstOrderUnitId() != null) &&
			    (l_marginOpenContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
			{
				//注文繰越時の発注審査
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
            //validate注文条件
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
            log.debug("validate注文条件を実行");
            
            //validate株数（信用）
            //引数は以下の通りに設定する。 
            //取引銘柄：　@validate取引銘柄( )の戻り値の取引銘柄オブジェクト 
            //部店：　@補助口座.get取引店( ) 
            //株数：　@信用新規建注文内容.getQuantity( ) 
            //注文種別：　@信用新規建注文内容.isLongOrder( )==trueの場合、OrderTypeEnum.信用買建注文 
            //　@　@　@          信用新規建注文内容.isLongOrder( )==falseの場合、OrderTypeEnum.信用売建注文 
            //弁済区分：　@信用新規建注文内容.get弁済区分( ) 
            //弁済期限値：　@信用新規建注文内容.get弁済期限値( ) 
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_marginOpenContractOrderSpec.getQuantity(),
                l_orderTypeEnum,
                l_strRepaymentType,
                l_dblRepaymentNum);
            log.debug("validate株数（信用）を実行");
            
            //validate注文単価
            //引数は以下の通りに設定する。 
            //指値：　@信用新規建注文内容.getLimitPrice( ) 
            //取引銘柄：　@validate取引銘柄（信用）( )の戻り値の取引銘柄オブジェクト 
            //補助口座：　@引数の補助口座オブジェクト 
            if (l_marginOpenContractOrderSpec.isLimitOrder())
            {
                boolean l_isValidatePrice =
                    l_orderMgrResVal.validatePrice(
                        l_marginOpenContractOrderSpec.getLimitPrice(),
                        l_equityTradedProduct,
                        l_subAccount);
                log.debug("validate注文単価を実行");
            
                if (l_isValidatePrice == false)
                {
                    log.debug("l_isValidatePrice = " + l_isValidatePrice);
                    return new WEB3MarginNewOrderValidationResult(
                        ProcessingResult.newFailedResultInstance(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00293),
                        l_isShortSellingRestraint);
                }                
            }
            
            //1.26.validateW指値注文(補助口座, long, double, String, double, 
            //String, EqTypeExecutionConditionType, String, 取引銘柄, boolean, 
            //String, OrderCategEnum, double, String, OrderTypeEnum)
            //引数は以下の通りに設定する。 
            //補助口座 : 引数の補助口座オブジェクト 
            //注文単位ＩＤ : 0(：新規注文)  
            //指値 : 信用新規建注文内容.getLimitPrice( ) 
            //発注条件 : 信用新規建注文内容.get発注条件( )  
            //発注条件単価 : 信用新規建注文内容.get逆指値基準値( ) 
            //（W指値）訂正指値 : 信用新規建注文内容.get（W指値）訂正指値( ) 
            //（W指値）執行条件 : 信用新規建注文内容.get（Ｗ指値）執行条件( )  
            //（W指値）有効状態区分 : null（固定） 
            //取引銘柄 : validate取引銘柄( )の戻り値の取引銘柄オブジェクト 
            //is買注文 : 信用新規建注文内容.isLongOrder( ) 
            //弁済区分：　@信用新規建注文内容.get弁済区分( ) 
            //注文カテゴリ：　@OrderCategEnum.OPEN_MARGIN（新規建注文） 
            //株数： 信用新規建注文内容.getQuantity() 
            //値段条件： 信用新規建注文内容.get値段条件( ) 
            //注文種別： 
            // [isLongOrder()の戻り値 == trueの場合] 
            //　@　@”新規買建注文”をセット。 
            // [isLongOrder()の戻り値 == falseの場合] 
            //　@　@”新規売建注文”をセット。 
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
 
            //validate新規建代金上限
            //引数は以下の通りに設定する。 
            //補助口座：　@引数の補助口座オブジェクト 
            //建代金：　@信用新規建注文内容.get建代金( ) 
            //注文種別：　@信用新規建注文内容.isLongOrder( )==trueの場合、OrderTypeEnum.信用買建注文 
            //      信用新規建注文内容.isLongOrder( )==falseの場合、OrderTypeEnum.信用売建注文 
            //取引銘柄：　@株式発注審査個別チェック.validate取引銘柄（信用）の戻り値
			//注文単位：　@null
            if (l_eqtypeOrderUnit == null)
            {
                l_orderMgrResVal.validateMaxOpenMarginAmount(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_marginOpenContractOrderSpec.getContractAmount(),
                    l_orderTypeEnum,
                    l_equityTradedProduct,
                    null);
                log.debug("validate新規建代金上限を実行");
                //validate新規建株数上限
                //引数は以下の通りに設定する。 
                //補助口座：　@引数の補助口座オブジェクト 
                //注文数量：　@信用新規建注文内容.getQuantity( )  
                //注文種別：　@信用新規建注文内容.isLongOrder( )==trueの場合、OrderTypeEnum.信用買建注文 
                //      信用新規建注文内容.isLongOrder( )==falseの場合、OrderTypeEnum.信用売建注文 
                //取引銘柄：　@株式発注審査個別チェック.validate取引銘柄（信用）の戻り値
                //注文単位：　@null
                l_orderMgrResVal.validateMaxOpenMarginQuantity(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_marginOpenContractOrderSpec.getQuantity(),
                    l_orderTypeEnum,
                    l_equityTradedProduct,
                    null);
                log.debug("validate新規建株数上限を実行");
            }
            
            //is空売り規制
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
            log.debug("validate空売り規制を実行");

            //validate機@構預託同意(補助口座)
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
     * (validate信用注文)<BR>
     * 信用取引注文の共通チェックを実施する。<BR>
     * <BR>
     * 以下のチェックを行う。<BR>
     * 　@－受付時間チェック<BR>
     * 　@－システム停止中チェック<BR>
     * 　@－顧客のチェック（Ｙ客、管理ロック等）<BR>
     * 　@－信用実施会社チェック<BR>
     * 　@－信用客チェック（信用口座開設チェック）<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用注文）validate信用注文」参照。<BR>
     * <BR>
     * 部店.is信用取扱実施( )<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00746<BR>
     * 顧客.is信用口座開設( )<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00747<BR>
     * のいずれかがfalseを返した場合は、<BR>
     * エラー事由に応じた例外をthrowする。<BR>
     * @@param l_genSubAccount - 補助口座オブジェクト。
     * @@param l_strRepaymentType - 弁済区分<BR>
     * <BR>
     * 0：指定なし<BR>
     * 1：制度信用<BR>
     * 2：一般信用<BR>
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

            //1) 株式発注審査個別チェックオブジェクトの生成
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

            //2) validate注文受付可能
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.debug("validate注文受付可能を執行します");
            
            //3) commonFirstValidationsForAllOperations()    
            l_orderMgrResVal.commonFirstValidationsForAllOperations(
                l_genSubAccount);
            log.debug("commonFirstValidationsForAllOperationsを執行します");
            
            //get 部店
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();

            //4)  is信用取扱実施
            boolean l_isMarginTradeEnforcement =
                l_gentradeBranch.isMarginTradeEnforcement(l_strRepaymentType);
            log.debug("is信用取扱実施 = " + l_isMarginTradeEnforcement);
            
            if (!l_isMarginTradeEnforcement)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00746,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //get 顧客
            WEB3GentradeMainAccount l_genMainAccount =
                new WEB3GentradeMainAccount(l_genSubAccount.getAccountId());

            //5) is信用口座開設
            boolean l_isMarginAccountEstablished =
                l_genMainAccount.isMarginAccountEstablished(l_strRepaymentType);            
            log.debug("is信用口座開設 = " + l_isMarginAccountEstablished);
            
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
     * (validate返済注文)<BR>
     * 指定返済注文の発注審査を行う。<BR>
     * （validateSettleContractOrderのオーバーライド）<BR>
     * <BR>
     * シーケンス図「（信用注文）validate返済注文」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_settleContractOrderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。
     * @@param l_contract - (建株)<BR>
     * 建株オブジェクト。
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
                //返済注文内容に関連する決済建株エントリの配列を取得する。
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntrys = 
                    l_marginSettleContractOrderSpec.getSettleContractOrderEntries();            
                
                if (l_eqTypeSettleContractOrderEntrys == null || l_eqTypeSettleContractOrderEntrys.length == 0)
                {
                    throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
                }
                
                //取得した決済建株エントリ配列の１件目より建株ＩＤを取得する。
                long l_lngContractId = l_eqTypeSettleContractOrderEntrys[0].getContractId();
                log.debug("l_lngContractId = " + l_lngContractId);
                
                WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getPositionManager();
                            
                //get建株(long)    
                l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
            
            EqtypeContractRow l_eqtypeContractRow = 
                (EqtypeContractRow)l_equityContract.getDataSourceObject();
                
            //get 弁済区分
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();
            //get 弁済期限値
            int l_intRepaymentNum = l_eqtypeContractRow.getRepaymentNum();
            //get 市場ＩＤ
            long l_lngMarketId = l_equityContract.getMarketId();
            //get 建区分
            ContractTypeEnum l_contractType = l_eqtypeContractRow.getContractType();
            
            //validate信用注文(補助口座, String)
            validateMarginOrder(l_genSubAccount,l_strRepaymentType);
            
            //get 市場
            WEB3GentradeMarket l_genMarket = (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
            
            //get 証券会社コード
            String  l_strInstitutionCode = l_genSubAccount.getInstitution().getInstitutionCode();
            
            //validate市場コード
            l_genMarket = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                l_genMarket.getMarketCode(),
                l_strInstitutionCode);
            
            //get 銘柄
            EqTypeProduct l_eqTypeProduct = (EqTypeProduct)l_equityContract.getProduct();
            
            //validate銘柄コード（信用）
            WEB3EquityProduct l_equityProduct =
                l_orderMgrResVal.validateProductCode(
                l_eqTypeProduct.getProductCode(),
                l_strInstitutionCode,
                l_strRepaymentType);
            
            //validateインサイダー
            l_orderMgrResVal.validateInsider(l_genSubAccount, l_equityProduct);

            //get 注文種別
            OrderTypeEnum l_orderTypeEnum;
            if (l_equityContract.isLong())
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            }
            
            //validate顧客銘柄別取引停止
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_genSubAccount,
                l_equityProduct.getProductId(),
                l_orderTypeEnum);

            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();
            
            //validate取引銘柄（信用）
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderMgrResVal.validateTradedProductForMarginTrading(
                    l_genSubAccount,
                    l_equityProduct,
                    l_genMarket,
                    l_gentradeBranch,
                    l_strRepaymentType,
                    OrderCategEnum.CLOSE_MARGIN,
                    l_equityContract.isShort());
            
            //validate成行指定規制（信用）(取引銘柄, String, OrderCategEnum,
            //    boolean, boolean, EqTypeExecutionConditionType)
            //取引銘柄：　@validate取引銘柄（信用）( )の戻り値の、取引銘柄オブジェクト
            //弁済区分：　@建株.弁済区分
            //注文カテゴリ：　@OrderCategEnum.”返済注文”（CLOSE_MARGIN）
            //is成行：　@信用返済注文内容.isMarketOrder( )
            ///is売建：　@建株.isShort( )
            //執行条件：　@信用返済注文内容.getExecConditionType( )
            l_orderMgrResVal.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                OrderCategEnum.CLOSE_MARGIN,
                l_marginSettleContractOrderSpec.isMarketOrder(),
                l_equityContract.isShort(),
			    l_marginSettleContractOrderSpec.getExecConditionType());
            
            //validate取扱可能市場（信用）
            //引数は以下の通りに設定する。 
            //部店：　@補助口座.get取引店( ) 
            //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト 
            //市場コード：　@市場.getMarketCode() 
            //弁済区分：　@建株.弁済区分 
            //弁済期限値：　@建株.弁済期限値 
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);
            
            //validate特定口座開設（信用）
            //引数は以下の通りに設定する。 
            //補助口座：　@引数の補助口座 
            //税区分：　@信用返済注文内容.getTaxType( ) 
            //受渡日：　@取引銘柄.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                l_genSubAccount,
                l_marginSettleContractOrderSpec.getTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());
            
            //validate特定口座取扱規制
            //引数は以下の通りに設定する。 
            //税区分：　@信用返済注文内容.getTaxType( ) 
            //株式銘柄：　@validate銘柄コード（信用）( )の戻り値の株式銘柄オブジェクト 
            //is買注文：　@建株.isShort( )がtrueならtrueを指定、以外falseを指定 
            //※市場に対して売買どちらの注文なのかを指定するため、建区分と逆になる(反対売買)
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_marginSettleContractOrderSpec.getTaxType(),
                l_equityProduct,
                l_equityContract.isShort());
            
			Date l_datFirstOrderBizDate = null;
			if ((l_marginSettleContractOrderSpec.getFirstOrderUnitId() != null) &&
			    (l_marginSettleContractOrderSpec.getFirstOrderUnitId().longValue() > 0))
			{
				//注文繰越時の発注審査
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
            //validate注文条件
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
            
           //validate株数（信用）
           //引数は以下の通りに設定する。 
           //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト 
           //部店：　@補助口座.get取引店( ) 
           //株数：　@信用返済注文内容.getTotalQuantity( ) 
           //注文種別：　@ 
           //建株.isLong( )==trueの場合、OrderTypeEnum.信用買建返済注文 
           //建株.isLong( )==falseの場合、OrderTypeEnum.信用売建返済注文 
           //弁済区分：　@建株.弁済区分 
           //弁済期限値：　@建株.弁済期限値 
           l_orderMgrResVal.validateQuantity(
               l_equityTradedProduct,
               l_gentradeBranch,
               l_marginSettleContractOrderSpec.getTotalQuantity(),
               l_orderTypeEnum,
               l_strRepaymentType,
               l_intRepaymentNum);

            //validate決済建株エントリ毎売買単位
            //引数は以下の通りに設定する。 
            //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト
            //決済建株エントリ一覧：　@信用返済注文内容．get決済建株エントリ一覧() 
            l_orderMgrResVal.validateEverySettleContractOrderEntryLotSize(
                l_equityTradedProduct,
                l_marginSettleContractOrderSpec.getSettleContractOrderEntries()); 
                          
           // validate決済総建株数
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
           //validate注文単価
           //引数は以下の通りに設定する。 
           //指値：　@信用返済注文内容.getLimitPrice( ) 
           //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト 
           //補助口座：　@引数の補助口座オブジェクト
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
           
           //validateW指値注文(補助口座, long, double, String, double, String,
           //EqTypeExecutionConditionType, String, 取引銘柄, boolean, String,
           //OrderCategEnum, double, String, OrderTypeEnum)
            //   引数は以下の通りに設定する。 
            //   補助口座：　@引数の補助口座オブジェクト 
            //   注文単位ＩＤ：　@0(：新規注文) 
            //   指値：　@信用返済注文内容.getLimitPrice( ) 
            //   発注条件：　@信用返済注文内容.get発注条件( )  
            //   発注条件単価：　@信用返済注文内容.get逆指値基準値( ) 
            //   （W指値）訂正指値：　@信用返済注文内容.get（W指値）訂正指値( ) 
            //   （W指値）執行条件：　@信用返済注文内容.get（Ｗ指値）執行条件( )  
            //   （W指値）有効状態区分：　@null（固定） 
            //   取引銘柄：　@validate取引銘柄( )の戻り値の取引銘柄オブジェクト 
            //   is買注文：　@建株.isShort( )
            //   弁済区分：　@建株.弁済区分 
            //   注文カテゴリ：　@OrderCategEnum.CLOSE_MARGIN（返済注文） 
            //   株数：　@信用返済注文内容.getTotalQuantity( ) 
            //   値段条件：　@信用返済注文内容.get値段条件( ) 
            //   注文種別：　@ 
            //   　@建株.isLong( )==trueの場合、OrderTypeEnum.信用買建返済注文 
            //   　@建株.isLong( )==falseの場合、OrderTypeEnum.信用売建返済注文 
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

           //validate機@構預託同意(補助口座)
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
     * (calc注文時建代金)<BR>
     * 注文時建代金を算出して返却する。<BR>
     * <BR>
     * シーケンス図「（信用注文）calc注文時建代金」も合わせて参照。<BR>
     * <BR>
     * １）　@注文時建代金を計算する。<BR>
     * <BR>
     * １－１）　@株式計算サービス.calc拘束売買代金()にて、<BR>
     *     拘束売買代金（未出来分）を計算する。<BR>
     * <BR>
     * 　@[calc拘束売買代金 引数]<BR>
     * 　@株数：　@（引数.株数－引数.約定数量）の結果<BR>
     * 　@計算単価：　@計算単価<BR>
     * 　@部店ID：　@補助口座.部店ID<BR>
     * 　@手数料商品コード：　@手数料.get手数料商品コード()<BR>
     * 　@is指値：　@手数料.is指値( )<BR>
     * <BR>
     * １－２）　@拘束売買代金（合計値）を計算する。<BR>
     * <BR>
     * 　@拘束売買代金（合計値） ＝ 株式計算サービス.calc拘束売買代金()の戻り値<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@＋ 引数.合計約定金額<BR>
     * <BR>
     * ２）　@上限金額値チェック<BR>
     * 　@－（パラメータ.isSkip金額チェック == false）の場合のみ。<BR>
     * 　@発注審査個別チェック.validate取引可能上限金額()にて注文時建代金の金額チェックを行う。<BR>
     * 　@<BR>
     * 　@[validate取引可能上限金額 引数]<BR>
     * 　@部店：　@パラメータ.補助口座.get取引店()<BR>
     * 　@市場：　@パラメータ.取引銘柄.get市場()<BR>
     * 　@金額：　@１－２）の計算結果（拘束売買代金（合計値））<BR>
     * 　@口座タイプ：　@パラメータ.補助口座.get顧客().口座タイプ<BR>
     * <BR>
     * 　@※例外が発生した場合は、その例外をそのままthrowする。<BR>
     * <BR>
     * ３）　@手数料オブジェクトに、１－２）の計算結果の拘束売買代金（合計値）を<BR>
     * 　@　@　@　@　@「諸経費計算用代金」としてセットする。<BR>
     * <BR>
     * 　@引数の手数料オブジェクト.set.諸経費計算用代金(拘束売買代金（合計値）) でセット。<BR>
     * <BR>
     * ４）　@１－２）で計算した拘束売買代金（合計値）を、<BR>
     * 　@　@　@注文時建代金として返却する。<BR>
     * <BR>
     * @@param l_genCommission - 手数料オブジェクト
     * @@param l_dblCalcUnitPrice - (計算単価)<BR>
     *    諸経費計算用代金（拘束売買代金／売買代金）を計算するための計算単価。
     * @@param l_genSubAccount - 補助口座オブジェクト。
     * @@param l_equityTradedProduct - 取引銘柄オブジェクト。
     * @@param l_dblQuantity - 株数
     * @@param l_dblExecutedQuantity - (約定数量)<BR>
     *    注文単位.約定数量
     * @@param l_dblExecutedAmount - (合計約定金額)<BR>
     *    注文単位.合計約定金額
     * @@param l_isSkipAmountRange - (isSkip金額チェック)<BR>
     *    計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     *    チェックを行う場合はfalse、チェックを行わない（スキップする）<BR>
     *    場合はtrueを指定する。<BR>
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
        //get 株式計算サービス
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
        
        //株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);
        BigDecimal l_bdExecutedQuantity = new BigDecimal(l_dblExecutedQuantity);
        BigDecimal l_bdExecutedAmount = new BigDecimal(l_dblExecutedAmount);
           
        //１－１）　@株式計算サービス.calc拘束売買代金()にて、
        //  拘束売買代金（未出来分）を計算する。
        // [calc拘束売買代金 引数]
        // 株数：　@（引数.株数－引数.約定数量）の結果
        // 計算単価：　@計算単価
        // 部店ID：　@補助口座.部店ID
        // 手数料商品コード：　@手数料.get手数料商品コード()
        // is指値：　@手数料.is指値( )
        log.debug("株数：" + l_bdQuantity.subtract(l_bdExecutedQuantity).doubleValue());
        log.debug("計算単価：" + l_dblCalcUnitPrice);
        log.debug("部店ID：" + l_genSubAccount.getWeb3GenBranch().getBranchId());
        log.debug("手数料商品コード：" + l_genCommission.getCommissionProductCode());
        log.debug("is指値：" + l_genCommission.isLimitPrice());
        double l_dblRestraintTurnover = 
            l_bizLogic.calcRestraintTurnover(
                l_bdQuantity.subtract(l_bdExecutedQuantity).doubleValue(),
                l_dblCalcUnitPrice,
                l_genSubAccount.getWeb3GenBranch().getBranchId(),
                l_genCommission.getCommissionProductCode(),
                l_genCommission.isLimitPrice());
                
        BigDecimal l_bdRestraintTurnover = new BigDecimal(l_dblRestraintTurnover);
        
        //１－２）　@拘束売買代金（合計値）を計算する。
        //拘束売買代金（合計値） ＝ 
       //     株式計算サービス.calc拘束売買代金()の戻り値
       //     ＋ 引数.合計約定金額
       log.debug("合計約定金額：" + l_bdExecutedAmount);
       BigDecimal l_bdTotalAmount = l_bdRestraintTurnover.add(l_bdExecutedAmount);
       
       //２）　@上限金額値チェック
       // －（パラメータ.isSkip金額チェック == false）の場合のみ。
       //発注審査個別チェック.validate取引可能上限金額()にて
       //注文時建代金の金額チェックを行う。
       // [validate取引可能上限金額 引数]
       // 部店：　@パラメータ.補助口座.get取引店()
       // 市場：　@パラメータ.取引銘柄.get市場()
       // 金額：　@１－２）の計算結果（拘束売買代金（合計値））
       // 口座タイプ：　@パラメータ.補助口座.get顧客().口座タイプ
       if(l_isSkipAmountRange == false)
       {
           //get顧客
           MainAccount l_mainAccount = l_genSubAccount.getMainAccount();
           MainAccountRow l_mainAccountRow = 
               (MainAccountRow)l_mainAccount.getDataSourceObject();
               
           l_orderMgrResVal.validateMaxHandlingPrice(
               l_genSubAccount.getWeb3GenBranch(),
               l_equityTradedProduct.getMarket(),
               l_bdTotalAmount.doubleValue(),
               l_mainAccountRow.getAccountType());
       }
       
       //３）　@手数料オブジェクトに、１－２）の計算結果の
       // 拘束売買代金（合計値）を「諸経費計算用代金」としてセットする。
       l_genCommission.setExpensesCalcAmount(l_bdTotalAmount.doubleValue());
       
       //４）　@１－２）で計算した拘束売買代金（合計値）を、
       //  注文時建代金として返却する。
       log.exiting(STR_METHOD_NAME);
       return l_bdTotalAmount.doubleValue();
       
    }
    
    /**
     * (calc注文時建代金)<BR>
     * 注文時建代金を算出して返却する。<BR>
     * <BR>
     * W指値注文の場合は、切替未完了であれば<BR>
     * リミット注文／ストップ注文の両方で概算建代金を計算し、<BR>
     * 高いほうの金額（拘束する金額）を戻り値オブジェクトに設定し返却する。<BR>
     * <BR>
     * シーケンス図「（信用注文）calc注文時建代金（W指値考慮）１」<BR>
     * 「（信用注文）calc注文時建代金（W指値考慮）２」を参照。<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料オブジェクト<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 注文の指値。<BR>
     * （成行指定の場合は、0）<BR>
     * @@param l_dblWLimitPrice - (（W指値）訂正指値)<BR>
     * ストップ注文の訂正指値。<BR>
     * （ストップ注文が成行指定の場合は、0）<BR>
     * @@param l_dblStopOrderBasePrice - (逆指値基準値)<BR>
     * ストップ注文への切替を行う基準値。<BR>
     * @@param l_execConditionType - (執行条件)<BR>
     * 注文の執行条件。<BR>
     * @@param l_execWConditionType - (（W指値）執行条件)<BR>
     * ストップ注文の執行条件。<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件。<BR>
     * @@param l_strCheckCurrentPrice - (確認時取得時価)<BR>
     * 確認時に取得した時価。<BR>
     * （上り処理で確認処理時に時価を取得済の場合、<BR>
     * 　@完了処理で取得済の時価を引き継ぎたい場合にセット。<BR>
     * 　@引き継ぎたくない場合は、nullまたは0をセット。）<BR>
     * @@param l_blnIsStopOrderValid - (isストップ注文有効)<BR>
     * W指値注文で、ストップ注文に切替が完了しているかどうかを設定する。<BR>
     * （true：　@ストップ注文への切替が完了。<BR>
     * 　@false：　@ストップ注文への切替が未完了 または notW指値注文。）<BR>
     * @@param l_blnIsShort - (is売建)<BR>
     * 売建かどうかのフラグ。<BR>
     * （true：　@売建、false：　@買建）<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_eqTypeTradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。<BR>
     * @@param l_dblQuantity - (株数)<BR>
     * 株数<BR>
     * @@param l_dblExecQuantity - (約定数量)<BR>
     * 注文単位.約定数量<BR>
     * @@param l_bdlExecutedAmount - (合計約定金額)<BR>
     * 注文単位.合計約定金額<BR>
     * @@param l_blnIsSkipAmountCheck - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。<BR>
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
        // 事前処理
        //===================================================================
        // 引数のオブジェクトのNotNullチェック        
        if (l_commission == null || l_subAccount == null || l_eqTypeTradedProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        // 発注審査個別チェックOBJの取得
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations =
            (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                
        // 株式計算サービスOBJの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
            
        // isSTOP高拘束方式部店の取得
        long l_lngEstimatePriceCalcForm = l_orderManagerReusableValidations.
            getEstimatePriceCalcForm(l_commission.getCommissionProductCode(), l_subAccount);
        boolean l_blnIsStopQuantityRestraintBranch = 
            (l_lngEstimatePriceCalcForm == WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT);
        
        // 確認時時価のdouble化（nullの場合0とする）
        double l_dblCheckPrice = 0.0D;
        if (l_strCheckCurrentPrice != null)
        {
            l_dblCheckPrice = Double.parseDouble(l_strCheckCurrentPrice);
        }
        
        // 取引銘柄の型をWEB3EquityTradedProduct型に変換
        WEB3EquityTradedProduct l_tradedProduct = (WEB3EquityTradedProduct) l_eqTypeTradedProduct;
        
        // 注文時建代金計算結果OBJの初期設定
        // STOP高時価の初期設定（値取得は後）
        // 時価／確認時時価（not STOP高）の設定
        // 確認時時価の設定
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
        // リミット注文での注文時建代金計算
        // （ストップ有効時はストップ注文での注文時建代金計算も兼ねる）
        //===================================================================
        // 計算単価（リミット注文用）算出
        // (1)STOP高拘束部店 ＆ （成行注文 or 不出来引成）：STOP高時価
        // (2)割増拘束部店 ＆ 成行注文　@　@　@　@　@　@　@　@　@　@：時価／確認時時価
        // (3)上記以外
        // 　@(3-1)売建注文 ＆ 指値＜時価／確認時時価　@　@　@：時価／確認時時価
        // 　@(3-2)売建注文 ＆ 指値≧時価／確認時時価　@　@　@：指値
        // 　@(3-3)買建　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@：指値
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
        
        // 手数料オブジェクト（リミット注文用）の生成
        WEB3GentradeCommission l_commission1 = copyCommission(l_commission);
        l_commission1.setIsLimitPrice(l_dblLimitPrice != 0.0D);
        
        // 注文時建代金計算（リミット注文用）
        double l_dblEstimatedContractAmount1 = this.calcContractAmountAtOrder(
            l_commission1, l_dblCalcPrice1, (WEB3GentradeSubAccount)l_subAccount, l_tradedProduct,
            l_dblQuantity, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountCheck);


        //===================================================================
        // 1回計算パターン時のリターン
        // （リミット注文の注文時建代金を返値とする）
        // ①@Ｗ指値（リミット／ストップともに有効）でない場合
        // ②Ｗ指値注文だが、ストップ注文有効として算出する場合
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
        // ストップ注文での注文時建代金計算
        //===================================================================
        // 計算単価（ストップ注文用）算出
        // (1)STOP高拘束部店 ＆ （成行注文 or 不出来引成）：STOP高時価
        // 　@（ただし、リミット計算時に取得していた場合は同じ値を使用する）
        // (2)割増拘束部店 ＆ 成行注文　@　@　@　@　@　@　@　@　@　@：時価／確認時時価
        // (3)上記以外
        // 　@(3-1)売建注文 ＆ 訂正指値＜時価／確認時時価　@：時価／確認時時価
        // 　@(3-2)売建注文 ＆ 訂正指値≧時価／確認時時価　@：訂正指値
        // 　@(3-3)買建　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@：訂正指値
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
        
        // 手数料オブジェクト（ストップ注文用）の生成
        WEB3GentradeCommission l_commission2 = copyCommission(l_commission);
        l_commission2.setIsLimitPrice(l_dblWLimitPrice != 0.0D);
        
        // 注文時建代金計算（ストップ注文用）
        double l_dblEstimatedContractAmount2 = this.calcContractAmountAtOrder(
            l_commission2, l_dblCalcPrice2, (WEB3GentradeSubAccount)l_subAccount, l_tradedProduct,
            l_dblQuantity, l_dblExecQuantity, l_bdlExecutedAmount, l_blnIsSkipAmountCheck);
        

        //===================================================================
        // 2回計算パターン時のリターン
        // （リミット／ストップの内、高い方の注文時建代金を返値とする）
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
     * (create決済建株エントリ)<BR>
     * 注文株数を優先順位に従って各建株に配分し、決済建株エントリの配列を作成する。<BR>
     * <BR>
     * this.create決済建株エントリ(注文単位ID, 注文株数, 決済建株明細一覧, false)にdelegateする。<BR>
     * ※期日チェックを行う指定で、delegateを行う。 <BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 対象注文単位ＩＤ。<BR>
     * （訂正注文の場合のみ仕様）<BR>
     * @@param l_dblOrderQuantity - 注文株数。<BR>
     * @@param CloseMarginContractUnit - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
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
     * (create決済建株エントリ)<BR>
     * 注文株数を優先順位に従って各建株に配分し、決済建株エントリの配列を作成する。<BR>
     * <BR>
     * (*) 画面より「ﾗﾝﾀﾞﾑ」指定された場合（注文株数==0）はオーバーロードメソッドに委譲する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用注文）create決済建株エントリ」参照。<BR>
     * <BR>
     * （建株決済期日チェック）<BR>
     *  該当建株が決済期日を越えている場合、例外をスローする。<BR>
     *  (発注日(*) > 決済期日(getCloseDate()の場合)<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00748<BR>
     *  <BR>
     *  (返済可能株数チェック)<BR>
     *  注文株数を各建株の返済可能株数に配分した後(Loop処理後)も残数があれば(*)、<BR>
     *  返済可能株数オーバーとして例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00304<BR>
     * <BR>
     *  (*)残数があるかの判定<BR>
     * (注文株数(残数).longValue() > 0)の場合、例外とする<BR>
     * <BR>
     * @@param l_lngOrderUnitId - (注文単位ID)<BR>
     * 対象注文単位ＩＤ。<BR>
     * （訂正注文の場合のみ仕様）<BR>
     * @@param l_dblOrderQuantity - 注文株数。<BR>
     * @@param CloseMarginContractUnit - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
     * @@param l_isSkipCloseDateCheck - sSkip期日チェック。<BR>
     * 建株の期日チェックをスキップするかどうかのフラグ。 <BR>
     * （false：　@期日チェックをスキップしない（==期日チェックをする）<BR>
     * 　@true：　@期日チェックをスキップする（==期日チェックをしない）） <BR>
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
        log.debug("注文単位ID = " + l_lngOrderUnitId);
        log.debug("注文株数 = " + l_dblOrderQuantity);
        if (Double.isNaN(l_dblOrderQuantity))
        {
            l_dblOrderQuantity = 0D;
        }
              
        //1) 注文株数 == 0 の場合
        if(l_dblOrderQuantity == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return createClosingContractEntry(l_lngOrderUnitId, l_closeMarginContractUnits, l_isSkipCloseDateCheck);
        }
        
        //訂正注文（注文単位ID > 0）の場合
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

            //一部約定の場合、合計返済約定数量を取得する。
            if (l_isPartiallyExecuted)
            {
                //(*1)合計返済約定数量
                //引数の注文単位IDに該当する建株返済指定情報の一覧の内、
                //処理対象の決済順位（index + 1）～最下位までの建株返済指定情報.返済約定数量のSUM値
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
        
        //決済建株明細一覧[]要素毎のLoop処理
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
            //2) 新規注文&&注文株数（残数） == 0 の場合
            if((l_lngOrderUnitId == 0) && (Double.compare(l_bdOrderQuantity.doubleValue(),0) == 0))
            {
                break;
            }
            
            //3) get 建株ＩＤ
            long l_lngContractId = Long.parseLong(l_closeMarginContractUnits[i].id);
      
            try
            {
                //4) get建株(long)    
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

            //5) （建株決済期日チェック）
            // 該当建株が決済期日を越えている場合
            if (!l_isSkipCloseDateCheck)
            {
                // 株式発注審査個別チェックオブジェクトの生成
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations) 
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                // validate決済期日超過(建株)
                l_orderMgrResVal.validateCloseDateExcess(l_equityContract);
            }

            //6) get 返済可能建株残高
            // = getQuantity() - getLockedQuantity() + getロック中数量(注文単位ＩＤ) + get返済約定数済数量（注文単位ID）
            BigDecimal l_bdQuantity = new BigDecimal(l_equityContract.getQuantity());
            BigDecimal l_bdLockedQuantity = new BigDecimal(l_equityContract.getLockedQuantity());
            BigDecimal l_bdLockedOrderQuantity = new BigDecimal(l_equityContract.getLockedQuantity(l_lngOrderUnitId));
            BigDecimal l_dbClosingExecutedQuantity = new BigDecimal(l_equityContract.getClosingExecutedQuantity(l_lngOrderUnitId));
            BigDecimal l_bdRemainderQuantity = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedOrderQuantity).add(l_dbClosingExecutedQuantity);

            //7) 決済建株エントリを生成する。
            //返済可能建株残高と注文株数（残数）を比較する
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
            
            //一部約定の訂正注文（注文単位ID > 0 && isPartiallyExecuted() == true）の場合、
            //下位の返済指定情報の約定数量の考慮を行う
            if (l_lngOrderUnitId > 0 && l_isPartiallyExecuted)
            {
                //(*)下位の返済指定情報の約定数量の考慮
                //　@①@未約定数量（残数）を算出する。
                //　@　@未約定数量（残数） = 注文株数（残数） - 合計返済約定数量(*1)
                BigDecimal l_bdUnExecutedQuantity = 
                    l_bdOrderQuantity.subtract(l_bdTotalClosingExecuteQuantity);
                
                //　@②未約定数量（残数） + get返済約定数量()の戻り値 < 上記フローにて決定した【返済注文数量】の場合、
                //　@　@上記フローにて決定した【返済注文数量】 = 未約定数量（残数） + get返済約定数量()の戻り値とする。
                if ((l_bdUnExecutedQuantity.add(l_dbClosingExecutedQuantity)).compareTo(l_bdClosingQuantity) < 0)
                {
                    l_bdClosingQuantity = l_bdUnExecutedQuantity.add(l_dbClosingExecutedQuantity);
                }
                
                //合計返済約定数量(*1)より、get返済約定数量()の戻り値を減算する。
                l_bdTotalClosingExecuteQuantity = 
                    l_bdTotalClosingExecuteQuantity.subtract(l_dbClosingExecutedQuantity);
            }

            l_lstSettleContractOrderEntrys.add(
                new EqTypeSettleContractOrderEntry(
                    l_lngContractId,
                    l_bdClosingQuantity.doubleValue()));
            
            //8) 注文株数（残数）より、上記フローにて決定した【返済注文数量】を減算する。 
            l_bdOrderQuantity = l_bdOrderQuantity.subtract(l_bdClosingQuantity);
            if(l_bdOrderQuantity.doubleValue() < 0)
            {
                l_bdOrderQuantity = new BigDecimal(0);
            }            

        }
        
        //9) 注文株数を各建株の返済可能株数に配分した後(Loop処理後)も
        //残数があれば、返済可能株数オーバーとして例外をスローする
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
     * (create決済建株エントリ)<BR>
     * 決済順位／返済可能数量をチェックし、決済建株エントリの配列を作成する。<BR>
     * <BR>
     * (*) 画面より「ﾗﾝﾀﾞﾑ」指定された場合にオーバーロードメソッドより委譲される。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用注文）create決済建株エントリ（ﾗﾝﾀﾞﾑ指定）」参照。<BR>
     * <BR>
     * <BR>
     * （建株決済期日チェック）<BR>
     *   該当建株が決済期日を超えている場合、例外をスローする。<BR>
     *   (発注日(*) > 決済期日(getCloseDate()の場合)<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00748<BR>
     * <BR>
     * （決済順序チェック）<BR>
     * －getの戻り値がnullでない場合、決済順位が重複していると判定し、例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00332<BR>
     *<BR>
     * （返済可能残高チェック）<BR>
     * 決済建株明細一覧[index].株数と返済可能建株残高(*)を比較し、<BR>
     * 決済建株明細一覧[index].株数が返済可能建株残高を超えていれば例外をスローする。<BR>
     *  class: WEB3BusinessLayerException<BR>
     *  tag:   BUSINESS_ERROR_00299<BR>
     * 
     * @@param l_lngOrderUnitId - (注文単位ＩＤ)<BR>
     * 対象注文単位ＩＤ。<BR>
     * （訂正注文の場合のみ仕様）<BR>
     * @@param l_closeMarginContractUnits - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
     * @@param l_isSkipCloseDateCheck - sSkip期日チェック。<BR>
     * 建株の期日チェックをスキップするかどうかのフラグ。 <BR>
     * （false：　@期日チェックをスキップしない（==期日チェックをする）<BR>
     * 　@true：　@期日チェックをスキップする（==期日チェックをしない）） <BR>
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
        
        //決済建株明細一覧[]要素毎のLoop処理
        int l_intSize = 0;
        if (l_closeMarginContractUnits != null)
        {
            l_intSize = l_closeMarginContractUnits.length;
        }
        for (int i = 0 ; i < l_intSize ; i ++)
        {
            //get 決済建株明細一覧[index].株数
            String l_strOrderQuantity = l_closeMarginContractUnits[i].orderQuantity;
            //get 決済建株明細一覧[index].決済順位
            String l_strSettlePriority = l_closeMarginContractUnits[i].settlePriority;

            double l_dbOrderQuantity;
                        
            //パラメータチェック(新規注文の場合)
            if ((l_lngOrderUnitId == 0)
                && ((l_strOrderQuantity == null) || "0".equals(l_strOrderQuantity)))
            {
                //新規注文(注文単位ID==0)の場合は、株数0もしくはnullの決済建株エントリを作成しない。
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
            
            //3) get 建株
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

            //5) （建株決済期日チェック）
            // 該当建株が決済期日を越えている場合
            if (!l_isSkipCloseDateCheck)
            {
                // 株式発注審査個別チェックオブジェクトの生成
                WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                    (WEB3EquityTypeOrderManagerReusableValidations) 
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
                // validate決済期日超過(建株)
                l_orderMgrResVal.validateCloseDateExcess(l_equityContract);
            }

            //6) get返済約定済数量
            double l_dbClosingExecutedQuantity = 
                l_equityContract.getClosingExecutedQuantity(l_lngOrderUnitId);          
            
            //8) 返済約定済数量チェック      
            if(Double.compare(l_dbOrderQuantity, l_dbClosingExecutedQuantity) < 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        " 決済建株明細一覧[index].株数 < 返済約定済数量");
            }
            
            if (l_strSettlePriority == null || "0".equals(l_strSettlePriority))
            {
                continue;
            }
            Long l_lngSettlePriority = new Long(l_strSettlePriority);
            
            //9) 決済順位チェック
            if(l_treeMap.get(l_lngSettlePriority) != null)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00182,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //10) get 返済可能建株残高
            // = getQuantity() - getLockedQuantity() + getロック中数量(注文単位ＩＤ)
            BigDecimal l_bdQuantity = new BigDecimal(l_equityContract.getQuantity());
            BigDecimal l_bdLockedQuantity = new BigDecimal(l_equityContract.getLockedQuantity());
            BigDecimal l_bdLockedOrderQuantity = new BigDecimal(l_equityContract.getLockedQuantity(l_lngOrderUnitId));
            BigDecimal l_bdClosingExecutedQuantity = new BigDecimal(l_dbClosingExecutedQuantity);
            BigDecimal l_bdRemainderQuantity = l_bdQuantity.subtract(l_bdLockedQuantity).add(l_bdLockedOrderQuantity).add(l_bdClosingExecutedQuantity);
            
            //11) 返済可能残高チェック
            // 決済建株明細一覧[index].株数と返済可能建株残高を
            //比較し、決済建株明細一覧[index].株数が返済可能建株残高
            //を超えていれば例外をスローする。
            BigDecimal l_bdOrderQuantity = new BigDecimal(l_dbOrderQuantity);
            if(l_bdOrderQuantity.compareTo(l_bdRemainderQuantity) > 0)
            {
                throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
            }           
          
            //12)  put(決済順位 : Object, 決済建株エントリ : Object)
            // 決済順位を示すKeyに、決済建株エントリをセットする。
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
     * （calc新規建計算単価）。<BR>
     * <BR>
     * 新規建注文の計算単価を計算し返す。<BR>
     * 時価を返す場合は、<BR>
     *   ?成行指定の場合：STOP高拘束を考慮した時価を返す。<BR>
     *   ?指値指定の場合（売建のみ）：STOP高拘束を考慮しない時価を返す。<BR> 
     * <BR>
     * ○引数のis成行＝true（成行）の場合<BR>
     * 　@－this.calc時価(引数の手数料商品コード, 引数の取引銘柄, 引数の補助口座, true)に<BR>
     * 　@　@　@委譲する。<BR>
     * <BR>
     * ○引数のis成行＝false（指値）、かつ引数のis買建＝false（売建）の場合<BR>
     * 　@－this.calc時価(引数の手数料商品コード, 引数の取引銘柄, 引数の補助口座, true)により、<BR>
     * 　@　@　@時価を取得する。<BR>
     * 　@－（calc時価( )の戻り値 > 引数の指値）の場合、calc時価( )の戻り値を返す。<BR>
     * 　@　@　@上記以外の場合は、引数の指値をそのまま返す。<BR>
     * <BR>
     * ○上記以外の場合（＝指値、かつ買建の場合）<BR>
     * 　@－引数の指値をそのまま返す。
     * @@param l_isOpenMargin （is新規建）<BR>
     * 　@　@　@新規建注文かどうかを判別するフラグ。<BR>
     * 　@　@　@新規建の場合はtrue、返済の場合はfalse。
     * @@param l_isLong （is買建）<BR>
     * 　@　@　@買建／売建を判別するフラグ。<BR>
     * 　@　@　@買建の場合はtrue、売建の場合はfalse。
     * @@param l_marketOrder （is成行）<BR>
     * 　@　@　@成行注文の場合、true。以外、false。
     * @@param l_dblLimitPrice （指値）<BR>
     * 　@　@　@is成行＝false（指値）の場合のみセット。
     * @@param l_genSubAccount （補助口座）<BR>
     * 　@　@　@補助口座オブジェクト（信用取引口座）
     * @@param l_equityTradedProduct （取引銘柄オブジェクト）
     * @@param l_strCommisionProductCode （手数料商品コード）<BR>
     * 　@　@　@（10:上場株式 11:店頭株式 12:ミニ株式 20:投資信託<BR>
     * 　@　@　@40:外国株式 50:株価指数先物 51:株価指数OP）
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
        // 引数のis成行＝true（成行）の場合
        // this.calc時価(引数の手数料商品コード, 引数の取引銘柄, 
        // 引数の補助口座, true)に委譲する。
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
            //○引数のis成行＝false（指値）、かつ引数のis買建＝false（売建）の場合
            //this.calc時価(引数の手数料商品コード, 引数の取引銘柄, 
            //引数の補助口座, false)により、時価を取得する。
            //（calc時価( )の戻り値 > 引数の指値）の場合、calc時価( )の戻り値を返す。
            //上記以外の場合は、引数の指値をそのまま返す。
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
            //上記以外の場合（＝指値、かつ買建の場合）
            //引数の指値をそのまま返す
            l_dblOpenMarginCalcUnitPrice = l_dblLimitPrice;
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblOpenMarginCalcUnitPrice;
    }
    
    /**
     * （validate現引現渡注文）。<BR>
     * <BR>
     * 指定現引現渡注文の発注審査を行う。<BR>
     * （validateSwapContractOrderのオーバーライド）<BR>
     * <BR>
     * シーケンス図「（信用注文）validate現引現渡注文」参照。
     * @@param l_subAccount 補助口座オブジェクト
     * @@param l_swapContractOrderSpec 信用現引現渡注文内容オブジェクト。
     * @@param l_contract 建株オブジェクト
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

            //1) 株式発注審査個別チェックオブジェクトの生成
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
                            
                //4) get 建株
                l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_lngContractId);
            }
                
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow) l_equityContract.getDataSourceObject();

            //5) 建株から プロパティを取得
            // get 市場ＩＤ
            long l_lngMarketId = l_equityContract.getMarketId();

            //get 弁済区分
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();

            //get 弁済期限値
            int l_intRepaymentNum = l_eqtypeContractRow.getRepaymentNum();
            
            //建区分
            ContractTypeEnum l_contractType = l_eqtypeContractRow.getContractType();
            
            //get 証券会社コード 
            String l_strInstitutionCode =
                l_genSubAccount.getInstitution().getInstitutionCode();
            //get 市場
            WEB3GentradeMarket l_genMarket = 
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
                            
            //補助口座.get取引店
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();

            //6) validate信用注文(補助口座, String)
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);

            // get 銘柄
            EqTypeProduct l_eqTypeProduct =
                (EqTypeProduct) l_equityContract.getProduct();

            //7) validate銘柄コード（信用）
            //銘柄コード：　@取得した建株.銘柄ID に該当する株式銘柄オブジェクト.銘柄コード 
            //証券会社コード：　@補助口座.証券会社コード 
            //弁済区分：　@取得した建株.弁済区分
            WEB3EquityProduct l_equityProduct =
                l_orderMgrResVal.validateProductCode(
                    l_eqTypeProduct.getProductCode(),
                    l_strInstitutionCode,
                    l_strRepaymentType);
            
            //8) validateインサイダー
            l_orderMgrResVal.validateInsider(l_genSubAccount, l_equityProduct);

            //get 注文種別
            OrderTypeEnum l_orderTypeEnum;
            if (l_equityContract.isLong())
            {
                l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.SWAP_MARGIN_SHORT;
            }
            
            //9) validate顧客銘柄別取引停止
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_genSubAccount,
                l_equityProduct.getProductId(),
                l_orderTypeEnum);
            
            //is売建
            boolean l_blnIsSellContract = false;
            if (ContractTypeEnum.SHORT.equals(l_contractType))
            {
                l_blnIsSellContract = true;
            }
            
            //10)  validate取引銘柄（信用）
            WEB3EquityTradedProduct l_equityTradedProduct =
                l_orderMgrResVal.validateTradedProductForMarginTrading(
                    l_genSubAccount,
                    l_equityProduct,
                    l_genMarket,
                    l_gentradeBranch,
                    l_strRepaymentType,
                    OrderCategEnum.SWAP_MARGIN,
                    l_blnIsSellContract);

            //11) validate取扱可能市場（信用）
            //部店：　@補助口座.get取引店( ) 
            //取引銘柄：　@validate取引銘柄( )の戻り値の取引銘柄オブジェクト 
            //市場コード：　@validate市場コード( )の戻り値の市場オブジェクト.市場コード 
            //弁済区分：　@取得した建株.弁済区分 
            //弁済区分：　@取得した建株.弁済期限値
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);

            //14) validate特定口座開設（信用）
            //引数は以下の通りに設定する。 
            //補助口座：　@引数の補助口座 
            //税区分：　@信用現引現渡注文内容.getTaxType( ) 
            //受渡日：　@取引銘柄.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                l_genSubAccount,
                l_marginSwapContractOrderSpec.getTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());

            //16) validate特定口座開設
            //引数は以下の通りに設定する。 
            //補助口座：　@引数の補助口座 
            //税区分：　@信用現引現渡注文内容.get税区分（現引現渡）
            //受渡日：　@取引銘柄.getDailyDeliveryDate( )
            l_orderMgrResVal.validateSpecialAccountEstablish(
                l_genSubAccount,
                l_marginSwapContractOrderSpec.getSwapTaxType(),
                l_equityTradedProduct.getDailyDeliveryDate());
            
            //17) validate税区分（現引現渡）
            l_orderMgrResVal.validateSwapTaxType(
                l_marginSwapContractOrderSpec.getTaxType(),
                l_marginSwapContractOrderSpec.getSwapTaxType(),
                !l_blnIsSellContract);
            
            //18) validate特定口座取扱規制
            //引数は以下の通りに設定する。 
            //税区分：　@信用現引現渡注文内容.getTaxType( ) 
            //株式銘柄：　@validate銘柄コード（信用）( )の戻り値の株式銘柄オブジェクト 
            //is買注文：　@取得した建株.isLong( )
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_marginSwapContractOrderSpec.getTaxType(),
                l_equityProduct,
                l_equityContract.isLong());
            
            //19) validate株数（信用）
            l_orderMgrResVal.validateQuantity(
                l_equityTradedProduct,
                l_gentradeBranch,
                l_marginSwapContractOrderSpec.getTotalQuantity(),
                l_orderTypeEnum,
                l_strRepaymentType,
                l_intRepaymentNum);
            
            //validate決済建株エントリ毎売買単位
            //引数は以下の通りに設定する。 
            //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト
            //決済建株エントリ一覧：　@信用返済注文内容．get決済建株エントリ一覧() 
            l_orderMgrResVal.validateEverySettleContractOrderEntryLotSize(
                l_equityTradedProduct,
                l_marginSwapContractOrderSpec.getSettleContractOrderEntries());

            //20) 現渡（建株.isShort( )==true）の場合のみ
            if (l_equityContract.isShort())
            {
                //20.1)  validate現渡可能株数
                //補助口座：　@引数の補助口座 
                //取引銘柄：　@validate取引銘柄（信用）( )の戻り値の取引銘柄オブジェクト 
                //株数：　@信用現引現渡注文内容.getTotalQuantity( ) 
                //税区分（現引現渡）：　@信用現引現渡注文内容.get税区分（現引現渡）( )
                l_orderMgrResVal.validateSwappableAssetQuantity(
                    l_genSubAccount,
                    l_equityTradedProduct,
                    l_marginSwapContractOrderSpec.getTotalQuantity(),
                    l_marginSwapContractOrderSpec.getSwapTaxType());                
            }
            
            //21) validate決済総建株数
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
     * （calc概算決済損益代金）。<BR>
     * <BR>
     * 概算決済損益代金を算出して返却する。<BR>
     * <BR>
     * シーケンス図「（信用注文）calc概算決済損益代金」参照。<BR>
     * @@param l_genCommission （手数料オブジェクト）<BR>
     * @@param l_dblLimitPrice （指値）<BR>
     * 　@　@　@成行の場合は0をセット<BR>
     * @@param l_genSubAccount （補助口座オブジェクト）<BR>
     * @@param l_equityTradedProduct （取引銘柄オブジェクト）<BR>
     * @@param l_settleContractOrderEntrys （決済建株エントリの配列）<BR>
     * @@param l_dblQuantity （数量）<BR>
     * @@param l_orderUnit （注文単位）<BR>
     * 　@　@　@訂正元／約定対象／約定取消対象注文の注文単位オブジェクト <BR>
     *      （新規の注文登録時はnullをセット）<BR>
     * @@param l_dblNowExecQuantity （今回約定数量）<BR>
     * 　@　@　@今回約定数量 <BR>
     *       （約定／約定取消の場合に編集） <BR>
     * @@param l_dblNowExecPrice （今回約定単価）<BR>
     * 　@　@　@今回約定単価 <BR>
     *       （約定／約定取消の場合に編集） <BR>
     * @@param l_isSkipAmountRange （isSkip金額チェック）<BR>
     * 　@　@　@計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * 　@　@　@チェックを行う場合はfalse、チェックを行わない（スキップする）場合 <BR>
     * 　@　@　@はtrueを指定する。<BR>
     * @@param l_contract (建株オブジェクト)<BR>
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
        //1) 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        //get 株式計算サービス
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
            
        //get 拡張ポジションマネージャ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityPositionManager l_positionManager = (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        
        //2) 手数料商品コードを取得する。
        String l_strCommissionProductCode = l_genCommission.getCommissionProductCode();
        log.debug("手数料商品コード = " + l_strCommissionProductCode);
              
        //3) create 概算決済損益代金計算結果
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice = 
            new WEB3EquityRealizedProfitAndLossPrice();
        
        // 建株取得
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
                    "建株IDに該当する建株が取得出来ませんでした。ID:[" + l_lngContractId + "]");
            }
        }
        boolean l_isShort = l_equityContract.isShort();
        
        //4) 計算単価をセットする。 
        //引数は以下の通り指定する。  
        //－引数の指値 == 0の場合、株式発注審査個別チェック.calc時価()の戻り値 
        //－引数の指値 != 0の場合、引数.指値
        log.debug("引数の指値 = " + l_dblLimitPrice);
        if(l_dblLimitPrice == 0)
        {
            double l_dbCurrentPrice = l_orderMgrResVal.calcCurrentPrice(
                l_strCommissionProductCode,
                l_equityTradedProduct,
                l_genSubAccount,
                false);
            log.debug("時価 = " + l_dbCurrentPrice);
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(l_dbCurrentPrice);
        }
        else
        {
            log.debug("引数.指値 = " + l_dblLimitPrice);
            l_equityRealizedProfitAndLossPrice.setCalcUnitPrice(l_dblLimitPrice);
        }
        
        //5) calc売買代金  ： 概算決済損益代金(*1)
        double l_dbTurnover;
        //引数の注文単位 == nullの場合
        if(l_orderUnit == null)
        {
            log.debug("引数の注文単位 == nullの場合");
            l_dbTurnover = 
                l_bizLogic.calcTurnover(
                    l_dblQuantity,
                    l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());
            
            log.debug("売買代金 = " + l_dbTurnover);
            //6) set売買代金
            l_equityRealizedProfitAndLossPrice.setTurnover(l_dbTurnover);
            
            //7) set諸経費計算用代金
            l_genCommission.setExpensesCalcAmount(l_dbTurnover);
        }
        else
        {
            log.debug("引数の注文単位 != nullの場合");
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
            log.debug("売買代金 = " + l_dbTurnover);

            double l_dblExecutedAmount = 0D;   
            if (l_orderUnitRow.getExecutedAmountIsNull() == false)
            {
                l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            }
                        
            //6) set売買代金
            l_dbTurnover = l_dbTurnover + l_dblExecutedAmount + (l_dblNowExecQuantity * l_dblNowExecPrice);
            l_equityRealizedProfitAndLossPrice.setTurnover(l_dbTurnover);
                
            log.debug("諸経費計算用代金 = " + l_equityRealizedProfitAndLossPrice.getTurnover());        
            //7) set諸経費計算用代金
            l_genCommission.setExpensesCalcAmount(
                l_equityRealizedProfitAndLossPrice.getTurnover());            
        }
        
        //isSkip金額チェック == false
        log.debug("isSkip金額チェック = " + l_isSkipAmountRange);
        BranchRow l_branch = (BranchRow) l_genSubAccount.getWeb3GenBranch().getDataSourceObject();
        if(l_isSkipAmountRange == false &&
            WEB3EnforcementDef.ENFORCEMENT.equals(l_branch.getMaxHandlingPriceCloseDiv()))
        {
            MainAccountRow l_mainAccountRow = 
                (MainAccountRow)l_genSubAccount.getMainAccount().getDataSourceObject();
                
            //8) 取引可能上限金額チェックを行う。 
            //引数は以下の通り指定する。 
            //部店：　@引数の補助口座.部店IDの部店オブジェクト 
            //市場：　@引数の取引銘柄.市場IDの市場オブジェクト 
            //拘束売買代金：　@手数料.set諸経費計算用代金( )の引数「諸経費計算用代金」設定値に同じ。 
            //口座タイプ：　@引数の補助口座.口座IDの顧客オブジェクト.口座タイプ
            l_orderMgrResVal.validateMaxHandlingPrice(
                l_genSubAccount.getWeb3GenBranch(),
                l_equityTradedProduct.getMarket(),
                l_equityRealizedProfitAndLossPrice.getTurnover(),
                l_mainAccountRow.getAccountType());

        }
        
        //9) calc委託手数料
        l_bizLogic.calcCommission(l_genCommission , l_genSubAccount);
        log.debug("委託手数料 = " + l_genCommission.getCommission());
        
        //10) 委託手数料消費税を算出する。 
        //引数は以下の通り指定する。 
        //金額：　@手数料.get手数料金額( ) 
        //基準日：　@手数料.get発注日( ) 
        //補助口座：　@引数.補助口座
        double l_dblSalesTax =
            l_bizLogic.calcSalesTax(
                l_genCommission.getCommission(),
                l_genCommission.getOrderBizDate(),
                l_genSubAccount);
        log.debug("委託手数料消費税 = " + l_dblSalesTax);
        
        //決済手数料 ： 概算決済損益代金(*2)
        double l_dblCommission =  l_genCommission.getCommission() + l_dblSalesTax;
        log.debug("概算決済損益代金 = " + l_dblCommission);
                
        //建代金 ： 概算決済損益代金(*3)
        double l_dblContractAmount = 0;
        
        //建株諸経費 ： 概算決済損益代金(*4)
        double l_dblExpensesSum = 0;
        
        //決済建株エントリ（EqtypeSettleContractOrderEntry[]）要素ごとのLoop処理
        int l_intSize = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intSize = l_settleContractOrderEntrys.length;
        }
        for (int i = 0 ; i < l_intSize ; i ++)
        {
            //11) get 建株
            if (l_contract == null)
            {
                try
                {
                    l_equityContract = (WEB3EquityContract)l_positionManager.getContract(l_settleContractOrderEntrys[i].getContractId());
                    log.debug("建株を取得 = " + l_equityContract.getContractId());
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
            
            //12)  get建代金 SUM
            l_dblContractAmount = l_dblContractAmount + l_equityContract.getContractAmount(l_dblContractQuantity);
            
            //13) get 建株諸経費 SUM
            //[calc諸経費( )：引数設定仕様]
            //委託手数料、委託手数料消費税  ＝　@0
            //建手数料～その他            ＝　@
            //決済建株エントリの各要素の同項目を使用して取得した値
            //   ex. 建手数料         ＝　@建株.get建手数料(数量)
            //       建手数料消費税        ＝　@建株.get建手数料消費税(数量)
            //建区分             ＝　@建株.建区
            long l_lngOrderUnitId = 0L;
            if (l_orderUnit != null)
            {
                l_lngOrderUnitId = l_orderUnit.getOrderUnitId();
            }
            l_dblExpensesSum = l_dblExpensesSum + l_bizLogic.calcExpenses(
                0, //委託手数料
                0, //委託手数料消費税
                l_equityContract.getSetupFee(l_dblContractQuantity, l_lngOrderUnitId),//建手数料
                l_equityContract.getSetupFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//建手数料消費税
                l_equityContract.getNameTransferFee(l_dblContractQuantity, l_lngOrderUnitId),//名義書換料
                l_equityContract.getNameTransferFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//名義書換料消費税
                l_equityContract.getManagementFee(l_dblContractQuantity, l_lngOrderUnitId),//管理費
                l_equityContract.getManagementFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//管理費消費税
                l_equityContract.getInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//順日歩
                l_equityContract.getPayInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//逆日歩
                l_equityContract.getLoanEquityFee(l_dblContractQuantity, l_lngOrderUnitId),//貸株料
                l_equityContract.getOther(l_dblContractQuantity, l_lngOrderUnitId),//その他
                l_eqtypeContractRow.getContractType());//建区分
        }
        
        //15) set概算決済損益代金
        BigDecimal l_bdQuantitySum = new BigDecimal(l_dblContractAmount);
        BigDecimal l_bdTurnover = new BigDecimal(l_dbTurnover);
        BigDecimal l_bdExpensesSum = new BigDecimal(l_dblExpensesSum);
        BigDecimal l_bdCommission = new BigDecimal(l_dblCommission);
        BigDecimal l_bdEstimatedRealizedProfitAndLossAmount;
        if(l_isShort == true)
        {
            //買返済の場合 ： 概算決済損益代金 = 建代金 - 売買代金 - (建株諸経費 + 決済手数料)
            l_bdEstimatedRealizedProfitAndLossAmount =
                l_bdQuantitySum
                    .subtract(l_bdTurnover)
                    .subtract(l_bdExpensesSum)
                    .subtract(l_bdCommission);
                    
            log.debug("****** 建代金：[" + l_dblContractAmount + "]");
            log.debug("****** 売買代金：[" + l_dbTurnover + "]");
            log.debug("****** 建株諸経費：[" + l_dblExpensesSum + "]");
            log.debug("******  決済手数料：[" + l_dblCommission + "]");
            log.debug("******  概算決済損益代金(買返済の場合) = 建代金 - 売買代金 - (建株諸経費 " 
                + "+ 決済手数料) ：[" 
                + l_bdEstimatedRealizedProfitAndLossAmount.doubleValue() + "]");
        }
        else
        {
            //売返済の場合 ： 概算決済損益代金 = 売買代金 - 建代金  - (建株諸経費 + 決済手数料)
            l_bdEstimatedRealizedProfitAndLossAmount =
            l_bdTurnover
                    .subtract(l_bdQuantitySum)
                    .subtract(l_bdExpensesSum)
                    .subtract(l_bdCommission);
                    
            log.debug("****** 売買代金：[" + l_dbTurnover + "]");
            log.debug("****** 建代金：[" + l_dblContractAmount + "]");
            log.debug("****** 建株諸経費：[" + l_dblExpensesSum + "]");
            log.debug("******  決済手数料：[" + l_dblCommission + "]");
            log.debug("******  概算決済損益代金(売返済の場合) = 売買代金 - 建代金 - (建株諸経費 " 
                + "+ 決済手数料) ：[" 
                + l_bdEstimatedRealizedProfitAndLossAmount.doubleValue() + "]");
        }
        l_equityRealizedProfitAndLossPrice.setEstimatedRealizedProfitAndLossAmount(
            l_bdEstimatedRealizedProfitAndLossAmount.doubleValue());
        
        log.exiting(STR_METHOD_NAME);
        return l_equityRealizedProfitAndLossPrice;
        
    }
    
    /**
     * （calc概算受渡代金（現引現渡)）。<BR>
     * <BR>
     * <BR>
     * 現引現渡時の概算受渡代金を算出して返却する。<BR>
     * <BR>
     * シーケンス図「（信用注文）calc概算受渡代金（現引現渡）」参照。<BR>
     * @@param l_settleContractOrderEntrys （決済建株エントリ）<BR>
     *       決済建株エントリの配列<BR>
     * @@param l_dblQuantity （数量）<BR>
     *       数量<BR>
     * @@param l_eqtypeOrderUnit (注文単位)<BR>
     * 　@　@　@訂正元／約定対象／約定取消対象注文の注文単位オブジェクト <BR>
     *      （新規の注文登録時はnullをセット）<BR>
     * @@param l_contract (建株)<BR>
     * 建株オブジェクト
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
        //get 株式計算サービス
        WEB3EquityBizLogicProvider l_bizLogic = 
            (WEB3EquityBizLogicProvider)
            GtlUtils.getTradingModule(ProductTypeEnum.EQUITY).getBizLogicProvider();
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager = 
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
           
        //建代金 
        double l_dblContractAmountSum = 0;
        
        //建株諸経費
        double l_dblExpensesSum = 0;

        //管理費
        double l_dblManagementFee = 0;
        //管理費消費税
        double l_dblManagementFeeTax = 0;

        // 建株を取得
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
                    "建株IDに該当する建株が取得出来ませんでした。ID:[" + l_lngContractId + "]");
            }
        }
        boolean l_isShort = l_equityContract.isShort();
        
        //決済建株エントリ（EqtypeSettleContractOrderEntry[]）要素ごとのLoop処理
        int l_intSize = 0;
        if (l_settleContractOrderEntrys != null)
        {
            l_intSize = l_settleContractOrderEntrys.length;
        }
        for (int i = 0; i < l_intSize; i ++)
        {
            //11) get 建株
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
                
            //12)  get建代金 SUM
            l_dblContractAmountSum = l_dblContractAmountSum + l_equityContract.getContractAmount(l_dblContractQuantity);
            
            long l_lngOrderUnitId = 0L;
            // 引数.注文単位 != nullの場合
            if (l_eqtypeOrderUnit != null)
            {
                l_lngOrderUnitId = l_eqtypeOrderUnit.getOrderUnitId();
            }

            //分岐フロー：現渡注文の場合（建株.isShort == true）
            if (l_isShort)
            {
                //get管理費(double, long)
                l_dblManagementFee =
                    l_equityContract.getManagementFee(l_dblContractQuantity, l_lngOrderUnitId);

                //get管理費消費税(double, long)
                l_dblManagementFeeTax =
                    l_equityContract.getManagementFeeTax(l_dblContractQuantity, l_lngOrderUnitId);
            }
            //分岐フロー：現引注文の場合（建株.isShort == false）
            else
            {
                //getManagementFee(long)
                l_dblManagementFee = l_equityContract.getManagementFee(l_lngOrderUnitId);

                //getManagementFeeTax(long)
                l_dblManagementFeeTax = l_equityContract.getManagementFeeTax(l_lngOrderUnitId);
            }

            //13) get 建株諸経費 SUM
            l_dblExpensesSum = l_dblExpensesSum + l_bizLogic.calcExpenses(
                0, //委託手数料
                0, //委託手数料消費税
                l_equityContract.getSetupFee(l_dblContractQuantity, l_lngOrderUnitId),//建手数料
                l_equityContract.getSetupFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//建手数料消費税
                l_equityContract.getNameTransferFee(l_dblContractQuantity, l_lngOrderUnitId),//名義書換料
                l_equityContract.getNameTransferFeeTax(l_dblContractQuantity, l_lngOrderUnitId),//名義書換料消費税
                l_dblManagementFee,//管理費
                l_dblManagementFeeTax,//管理費消費税
                l_equityContract.getInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//順日歩
                l_equityContract.getPayInterestFee(l_dblContractQuantity, l_lngOrderUnitId),//逆日歩
                l_equityContract.getLoanEquityFee(l_dblContractQuantity, l_lngOrderUnitId),//貸株料
                l_equityContract.getOther(l_dblContractQuantity, l_lngOrderUnitId),//その他
                l_eqtypeContractRow.getContractType());//建区分
        }
        
        BigDecimal l_bdQuantitySum = new BigDecimal(l_dblContractAmountSum);
        BigDecimal l_bdExpensesSum = new BigDecimal(l_dblExpensesSum);
        double l_dbEstimatedSwapPrice;
        if(l_isShort == true)
        {
            //現渡の場合 ： 概算受渡代金 = 建代金  - 建株諸経費
            l_dbEstimatedSwapPrice =
                l_bdQuantitySum.subtract(l_bdExpensesSum).doubleValue();
            log.debug("****** 建代金：[" + l_dblContractAmountSum + "]");
            log.debug("****** 建株諸経費：[" + l_dblExpensesSum + "]");
            log.debug("****** 概算受渡代金(現渡の場合) = 建代金  - 建株諸経費 ：[" + l_dbEstimatedSwapPrice + "]");
        }
        else
        {
            //現引の場合 ： 概算受渡代金 = 建代金  + 建株諸経費
            l_dbEstimatedSwapPrice =
                -l_bdQuantitySum.add(l_bdExpensesSum).doubleValue();
            log.debug("****** 建代金：[" + l_dblContractAmountSum + "]");
            log.debug("****** 建株諸経費：[" + l_dblExpensesSum + "]");
            log.debug("****** 概算受渡代金(現引の場合) = 建代金  + 建株諸経費 ：[" + l_dbEstimatedSwapPrice + "]");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dbEstimatedSwapPrice;
    }
    
    /**
     * （calc時価）。<BR>
     * <BR>
     * 時価を算出する。<BR>
     * （* 株式発注審査個別チェック.calc時価( )に委譲する。）<BR>
     * @@param l_strCommisionProductCode - (手数料商品コード)<BR>
     *    【会社部店商品テーブル】検索時に使用する。<BR>
     *  <BR>
     *    引数の「isSTOP高考慮」＝falseの場合は、【会社部店商品テーブル】<BR>
     *    を参照しないため、null設定も可。
     * @@param l_equityTradedProduct （取引銘柄）<BR>
     * 　@　@　@【株式取引銘柄テーブル】からの時価取得に使用する。
     * @@param l_genSubAccount （補助口座）<BR>
     * 　@　@　@【会社部店商品テーブル】からの「概算金額計算方式」取得時の<BR>
     * 　@　@　@「部店ID」の<BR>指定に使用する。
     * @@param l_isStopPriceConsideration （isSTOP高考慮）<BR>
     * 　@　@　@【会社部店商品テーブル】概算金額計算方式＝STOP高拘束 の<BR>
     * 　@　@　@設定を考慮するかどうかのフラグ。<BR>
     * 　@　@　@概算金額計算方式 の設定をそのまま使用する場合はtrueを、<BR>
     * 　@　@　@無視する場合はfalseを、それぞれ設定する。
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
        // 株式発注審査個別チェックオブジェクトの生成
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
     * （validate返済注文訂正）。<BR>
     * 指定返済訂正注文の発注審査を行う。 <BR>
     * （validateChangeSettleContractOrderのオーバーライド） <BR>
     * <BR>
     * this.validate返済注文訂正()に処理を委譲（delegade）する。 <BR>
     * <BR>
     * [validate返済注文訂正()の引数設定] <BR>
     * 補助口座：　@パラメータ.補助口座 <BR>
     * 信用新規建注文訂正内容：　@パラメータ.信用新規建注文訂正内容 <BR>
     * isSkip遅延状況チェック：　@false（固定） <BR>
     * @@param l_genSubAccount 補助口座
     * @@param l_marginChangeSettleContractOrderSpec 信用返済訂正注文内容
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
        
        //this.validate返済注文訂正()に処理を委譲（delegade）する。
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            this.validateChangeSettleContractOrder(
                l_subAccount, l_changeSettleContractOrderSpec, false);
        
        log.exiting(STR_METHOD_NAME);
        return l_eqTypeOrderValidationResult;
    }
    
    /**
     * (validate返済注文訂正)<BR>
     * 指定返済訂正注文の発注審査を行う。<BR>
     * <BR>
     * シーケンス図「（信用注文）validate返済注文訂正」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_changeSettleContractOrderSpec - (信用返済訂正注文内容)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
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

            //validate注文訂正可能状態(注文, boolean)
            l_orderMgrResVal.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);

            //get注文訂正詳細( )
            EqTypeContractSettleChangeOrderUnitEntry l_contractSettleChangeOrderUnitEntry =
                l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry();

            //getAfterChangeSettleContractOrderEntries( )
            EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys =
                l_contractSettleChangeOrderUnitEntry.getAfterChangeSettleContractOrderEntries();
            if (l_settleContractOrderEntrys == null || l_settleContractOrderEntrys.length == 0)
            {
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
            }

            //get建株(long)
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

            // 建株から プロパティを取得
            // get 市場ＩＤ
            long l_lngMarketId = l_equityContract.getMarketId();
            //get 弁済区分
            String l_strRepaymentType = l_eqtypeContractRow.getRepaymentType();
            //get 弁済期限値
            int l_intRepaymentNum = l_eqtypeContractRow.getRepaymentNum();
            //get 税区分
            TaxTypeEnum l_taxType = l_eqtypeContractRow.getTaxType();
            //get 建区分
            ContractTypeEnum l_contractType = l_eqtypeContractRow.getContractType();

            //get 証券会社コード
            String l_strInstitutionCode =
                l_genSubAccount.getInstitution().getInstitutionCode();
            //get 市場
            WEB3GentradeMarket l_genMarket =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);

            //補助口座.get取引店
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();

            //validate信用注文(補助口座, String)
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);

            //validate市場コード
            l_genMarket = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                l_genMarket.getMarketCode(),
                l_strInstitutionCode);

            //getProduct( )
            EqTypeProduct l_eqTypeProduct =
                (EqTypeProduct) l_equityContract.getProduct();

            //validateインサイダー
            l_orderMgrResVal.validateInsider(l_genSubAccount, l_eqTypeProduct);

            //get 注文種別
            OrderTypeEnum l_orderTypeEnum;
            if (l_equityContract.isLong())
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_LONG;
            }
            else
            {
                l_orderTypeEnum = OrderTypeEnum.CLOSE_MARGIN_SHORT;
            }

            //validate顧客銘柄別取引停止
            l_orderMgrResVal.validateAccountProductOrderStop(
                l_genSubAccount,
                l_eqTypeProduct.getProductId(),
                l_orderTypeEnum);

            //isShort( )
            boolean l_blnIsShort = l_equityContract.isShort();

            //validate取引銘柄（信用）
            //引数は以下の通りに設定する。
            //補助口座：　@引数の補助口座 
            //株式銘柄：　@建株.getProduct( )の戻り値の株式銘柄オブジェクト 
            //市場：　@validate市場コード( )の戻り値の市場オブジェクト 
            //部店：　@補助口座.get取引店( ) 
            //弁済区分：　@建株.弁済区分 
            //注文カテゴリ：　@OrderCategEnum.信用返済注文（CLOSE_MARGIN）固定 
            //is売建：　@建株.isShort( ) 
            //is売買停止チェック：　@false（＝売買停止チェックをしない） 

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

            //get訂正後執行条件( )
            EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
                l_marginChangeSettleContractOrderSpec.getModifiedExecutionCondition();

            //validate成行指定規制（信用）
            //取引銘柄：　@validate取引銘柄（信用）( )の戻り値の、取引銘柄オブジェクト 
            //弁済区分：　@建株.弁済区分 
            //注文カテゴリ：　@OrderCategEnum.”返済注文”（CLOSE_MARGIN） 
            //is成行：　@注文訂正詳細.isAfterChangePriceMarket( ) 
            //is売建：　@建株.isShort( ) 
            //執行条件：　@信用返済訂正注文内容.get訂正後執行条件( )
            l_orderMgrResVal.validateMarketOrderRestraint(
                l_equityTradedProduct,
                l_strRepaymentType,
                OrderCategEnum.CLOSE_MARGIN,
                l_blnIsAfterChangePriceMarket,
                l_equityContract.isShort(),
                l_eqTypeExecutionConditionType);

            //validate取扱可能市場（信用）
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);

            //validate特定口座開設（信用）
            //引数は以下の通りに設定する。
            //補助口座：　@引数の補助口座
            //税区分：　@建株.税区分
            //受渡日：　@取引銘柄.getDailyDeliveryDate( )
            l_orderMgrResVal.validateMarginSpecialAccountOpen(
                l_genSubAccount,
                l_taxType,
                l_equityTradedProduct.getDailyDeliveryDate());

            //validate特定口座取扱規制
            //税区分：　@建株.税区分 
            //株式銘柄：　@建株.getProduct( )の戻り値の株式銘柄オブジェクト 
            //is買注文：　@建株.isShort( )がtrueならtrueを指定、以外falseを指定 
            //※市場に対して売買どちらの注文なのかを指定するため、建区分と逆になる(反対売買)
            l_orderMgrResVal.validateCapitalGainTaxDealingsReg(
                l_taxType,
                l_eqTypeProduct,
                l_equityContract.isShort());

            //getOrderUnits(注文ID : long)
            OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);

            //getOrderUnit(arg0 : long)
            OrderUnit l_orderUnit  = l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            EqTypeOrderUnit l_firstOrderUnit = this.getFirstOrderUnit((EqTypeOrderUnit)l_orderUnit);
            EqtypeOrderUnitRow l_firstOrderUnitRow = (EqtypeOrderUnitRow)l_firstOrderUnit.getDataSourceObject();

            //get初回注文の注文単位( )の戻り値の注文単位.発注日
            Date l_bizDate = WEB3DateUtility.getDate(l_firstOrderUnitRow.getBizDate(),"yyyyMMdd");
            //get訂正後注文失効日( )
            Date l_datModifiedExpirationDate =
                l_marginChangeSettleContractOrderSpec.getModifiedExpirationDate();
            //get訂正後値段条件( )
            String l_strModifiedExecutionCondition =
                l_marginChangeSettleContractOrderSpec.getModifiedPriceConditionType();
            //get訂正後発注条件( )
            String l_strModifiedOrderConditionType =
                l_marginChangeSettleContractOrderSpec.getModifiedOrderConditionType();
            //is訂正後出来るまで注文( )
            boolean l_blnIsModifiedIsCarriedOrder =
                l_marginChangeSettleContractOrderSpec.isModifiedIsCarriedOrder();

            //getOrderUnitId( )
            long l_lngOrderUnitId = l_orderUnitRow.getOrderUnitId();

            //validate注文条件
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

           //validate株数（信用）
           //引数は以下の通りに設定する。
           //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト
           //部店：　@補助口座.get取引店( )
           //株数：　@注文訂正詳細.getAfterChangeTotalQuantity( )
           //注文種別：　@
           //建株.isLong( )==trueの場合、OrderTypeEnum.信用買建返済注文
           //建株.isLong( )==falseの場合、OrderTypeEnum.信用売建返済注文
           //弁済区分：　@建株.弁済区分
           //弁済期限値：　@建株.弁済期限値
           l_orderMgrResVal.validateQuantity(
               l_equityTradedProduct,
               l_gentradeBranch,
               l_dbAfterChangeTotalQuantity,
               l_orderTypeEnum,
               l_strRepaymentType,
               l_intRepaymentNum);

            //validate決済建株エントリ毎売買単位
            //引数は以下の通りに設定する。
            //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト 
            //決済建株エントリ一覧：　@訂正注文エントリ.getSettleContractOrderEntries() 
            //（※）訂正注文エントリ： 
            //信用返済訂正注文内容．get訂正注文エントリ一覧()の0番目の要素
            l_orderMgrResVal.validateEverySettleContractOrderEntryLotSize(
                l_equityTradedProduct,
                l_contractSettleChangeOrderUnitEntry.getAfterChangeSettleContractOrderEntries());

            //validate決済総建株数
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
            //validate注文単価
            //引数は以下の通りに設定する。
            //指値：　@注文訂正詳細.getAfterChangePrice( )
            //取引銘柄：　@validate取引銘柄(信用)( )の戻り値の取引銘柄オブジェクト
            //補助口座：　@引数の補助口座オブジェクト
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

            //get訂正後逆指値基準値( )
            double l_dbModifiedStopOrderPrice =
                l_marginChangeSettleContractOrderSpec.getModifiedStopOrderPrice();

            //get訂正後（W指値）訂正指値( )
            double l_dbModifiedWLimitPrice =
                l_marginChangeSettleContractOrderSpec.getModifiedWLimitPrice();
            String l_strModifiedWLimitPrice =
                WEB3StringTypeUtility.formatNumber(l_dbModifiedWLimitPrice);

            //get訂正後（W指値）執行条件( )
            EqTypeExecutionConditionType l_ChangeAfterWlimitExecCondType =
                l_marginChangeSettleContractOrderSpec.getModifiedWlimitExecCondType();

            //get（W指値）有効状態区分( )
            String l_strWlimitEnableStatusDiv =
                l_marginChangeSettleContractOrderSpec.getWlimitEnableStatusDiv();

            //is買注文(EqTypeOrderUnit)
            boolean l_blnIsBuyOrder = this.isBuyOrder((EqTypeOrderUnit)l_orderUnit);

            //validateW指値注文()
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

            //訂正元注文の発注日が現在日時より算出した発注日より前の場合のみ実施する。
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            //取引時間.get発注日 > 注文單位発注日
            if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
            {
                //validate閉局後訂正取消受付可能
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.EQUITY);
            }

            //get訂正後発注条件演算子( )
            String l_strModifiedOrderCondOperator =
                l_marginChangeSettleContractOrderSpec.getModifiedOrderCondOperator();

            //validate訂正項目
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

            //validate訂正時注文Rev上限
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
     * （validate信用注文取消）。<BR>
     * <BR>
     * 信用取引の発注審査処理を行う内部メソッド。<BR>
     * 取消対象注文が信用取引の場合に、validate現物株式取消メソッドより処理を委譲される。<BR>
     * <BR>
     * 指定の信用取引の注文取消が実施可能かをチェックする。<BR>
     * <BR>
     * シーケンス図「（信用注文）validate信用注文取消」参照。
     * @@param l_genSubAccount 補助口座オブジェクト
     * @@param l_equityCancelOrderSpec 株式注文取消内容オブジェクト
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
                
            //1) 株式発注審査個別チェックオブジェクトの生成
            WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
                (WEB3EquityTypeOrderManagerReusableValidations) 
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            //2) 注文IDを取得する。
            long l_lngOrderId = l_equityCancelOrderSpec.getOrderId();
            
            //3) get 注文
            Order l_order = getOrder(l_lngOrderId);
            
            //4) validate注文取消可能状態(注文)
            l_orderMgrResVal.validateOrderForCancellation(l_order);
            
            //注文単位オブジェクトを取得する
            OrderUnit[] l_orderUnits = this.getOrderUnits(l_lngOrderId);

            OrderUnit l_orderUnit  = l_orderUnits[0];
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //注文単位.get弁済区分( ) 
            String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
            //注文単位.弁済期限値()
            int l_intRepaymentNum = l_orderUnitRow.getRepaymentNum();
            
            //6) validate信用注文(補助口座, String)
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);
            
            //get 証券会社コード 
            String l_strInstitutionCode =
                l_genSubAccount.getInstitution().getInstitutionCode();
                
            //get 市場
            WEB3GentradeMarket l_genMarket =
                (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(l_orderUnitRow.getMarketId());
                
            //補助口座.get取引店
            WEB3GentradeBranch l_gentradeBranch =
                l_genSubAccount.getWeb3GenBranch();
            
            //8) validate市場コード
            l_genMarket = (WEB3GentradeMarket)l_orderMgrResVal.validateMarket(
                l_genMarket.getMarketCode(),
                l_strInstitutionCode);
            
            //9) get 銘柄
            WEB3EquityProduct l_equityProduct =
                (WEB3EquityProduct)l_tradingModule.getProductManager().getProduct(l_orderUnitRow.getProductId());
           
            //10)  validate取引銘柄
            WEB3EquityTradedProduct l_equityTradedProduct = 
                (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(l_equityProduct, l_genMarket);
            
            //11) validate取扱可能市場（信用）
            l_orderMgrResVal.validateHandlingMarket(
                l_gentradeBranch,
                l_equityTradedProduct,
                l_genMarket.getMarketCode(),
                l_strRepaymentType,
                l_intRepaymentNum);
            
			//13) 取消対象注文の発注日が現在日時より算出した発注日より前の場合のみ実施。
			Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
			Date l_datOrderBizDate =
				WEB3DateUtility.getDate(
					l_orderUnitRow.getBizDate(),
					WEB3GentradeTimeDef.DATE_FORMAT_YMD);

			if(WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
			{
				//13.1) validate閉局後訂正取消受付可能
				WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
					ProductTypeEnum.EQUITY);
			}
            
            //15) validate売付可能数量
            //引数は以下の通りに設定する。 
            //補助口座：　@引数の補助口座オブジェクト 
            //取引銘柄：　@validate取引銘柄（信用）( )の戻り値の取引銘柄オブジェクト 
            //株数：　@ 注文単位.約定数量（現引注文取消により、約定取消されることになる数量）
            //税区分（現引現渡）：　@注文単位.get税区分（現引現渡）
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
     * （create建株明細ByOrder）。<BR>
     * <BR>
     * 注文に関連した信用取引建株明細（照会用）を配列で取得する。<BR>
     * 指定注文が新規建注文の場合は、nullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（信用注文）create建株明細ByOrder」参照。<BR>
     * <BR>
     * 注文単位IDに該当する建株返済指定情報オブジェクトを<BR>
     * 取得する。取得できなかった場合は、「該当データなし」の例外をスローする。<BR>
     * 　@class: WEB3SystemLayerException<BR>
     * 　@tag:   SYSTEM_ERROR_80005
     * @@param l_lngOrderUnitId 注文単位ID
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
            //1) 注文単位IDにひもづく注文単位オブジェクトを取得する。 
            OrderUnit l_orderUnit = this.getOrderUnit(l_lngOrderUnitId);
            
            //2) 注文カテゴリを取得する
            OrderCategEnum l_orderCateg = l_orderUnit.getOrderCateg();
            if(OrderCategEnum.OPEN_MARGIN.equals(l_orderCateg))
            {
                //新規建注文(注文単位.getOrderCateg( ) == ”新規建注文”）
                //の場合は、nullを返却し、処理を終了する。
                return null;
            }
            
            EqTypeClosingContractSpec[] l_closingContractSpecs = null;
            
            //[注文単位.注文カテゴリ == ”返済注文”の場合
            //  EqTypeContractSettleOrderUnitにキャスト。
            //[注文単位.注文カテゴリ == ”現引現渡注文”の場合]
            //  EqTypeContractSwapOrderUnitにキャスト。
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
                
                //信用取引建株明細オブジェクトを生成する。
                WEB3MarginContractUnit l_contractUnit = new WEB3MarginContractUnit();
                
                // (*3)プロパティセット
                // ID：　@返済指定情報.getContractId( )
                // 建日： 建株.getOpenDate( )
                // 建単価：　@建株.getContractPrice( )
                // 建株数：　@建株.getQuantity( )
                // 建代金：　@建株.get建代金( )
                // 評価損益：　@建株.get評価損益( )
                // 注文株数：　@返済指定情報.getQuantity( )
                // 内出来株数：　@返済指定情報.getExecutedQuantity( )
                // 決済順位：　@返済指定情報.getClosingSerialNo( )

                //ID：　@返済指定情報.getContractId( )
                l_contractUnit.id = "" + l_closingContractSpec.getContractId();
                //建日： 建株.getOpenDate( )         
                l_contractUnit.openDate = WEB3DateUtility.toDay(l_contract.getOpenDate());
                //建単価：　@建株.getContractPrice( )                
                l_contractUnit.contractPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
                //建株数：　@建株.getQuantity( )
                l_contractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_contract.getQuantity());
                //建代金：　@建株.get建代金( )
                //建代金を取得する。引数は以下の通りに設定する。
                //数量：　@返済指定情報.getQuantity( )の戻り値
                l_contractUnit.contractExecPrice = WEB3StringTypeUtility.formatNumber(l_contract.getContractAmount(l_closingContractSpec.getQuantity()));
                
                // 計算単価
                if (l_dblCalcUnitPrice == 0.0D)
                {
	                l_dblCalcUnitPrice =
	                    l_productManager.getCurrentPrice(
	                        (WEB3EquityTradedProduct)l_orderUnit.getTradedProduct());
                }
                //評価損益：　@建株.get評価損益（建株諸経費考慮）()
                double l_dblAppraisalProfitLoss = l_contract.getAppraisalProfitOrLossExpenses(l_dblCalcUnitPrice, l_closingContractSpec.getQuantity(), l_lngOrderUnitId);
                
                l_contractUnit.appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitLoss);
                
                //注文株数：　@返済指定情報.getQuantity( )
                l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_closingContractSpec.getQuantity());
                //内出来株数：　@返済指定情報.getExecutedQuantity( )
                if(l_closingContractSpec.getExecutedQuantity() == 0 )
                {
                    l_contractUnit.partContQuantity = null;
                }
                else
                {
                    l_contractUnit.partContQuantity = WEB3StringTypeUtility.formatNumber(l_closingContractSpec.getExecutedQuantity());
                }
                
                //決済順位：　@返済指定情報.getClosingSerialNo( )
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
     * （validate新規建注文訂正）。<BR>
     * 信用取引の発注審査処理を行う内部メソッド。 <BR>
     * <BR>
     * this.validate新規建注文訂正()に処理を委譲（delegade）する。 <BR>
     * <BR>
     * [validate新規建注文訂正()の引数設定] <BR>
     * 補助口座：　@パラメータ.補助口座 <BR>
     * 信用新規建注文訂正内容：　@パラメータ.信用新規建注文訂正内容 <BR>
     * isSkip遅延状況チェック：　@false（固定） <BR>
     * @@param l_genSubAccount 補助口座オブジェクト
     * @@param l_marginChangeOrderSpec 信用新規建注文訂正内容オブジェクト。
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
        
        //this.validate新規建注文訂正()に処理を委譲（delegade）する。
        EqTypeOrderValidationResult l_eqTypeOrderValidationResult =
            this.validateChangeOrder(l_genSubAccount, l_marginChangeOrderSpec, false);
        
        log.exiting(STR_METHOD_NAME);   
        return l_eqTypeOrderValidationResult;
    }
    
    /**
     * (validate新規建注文訂正)<BR>
     * 信用取引の発注審査処理を行う内部メソッド。<BR>
     * 訂正対象注文が信用取引の場合に、validate現物株式訂正注文メソッドより処理を委譲される。<BR>
     * <BR>
     * 指定の信用取引の注文訂正が実施可能かをチェックする。<BR>
     * <BR>
     * シーケンス図「（信用注文）validate新規建注文訂正」参照。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_marginChangeOrderSpec - (信用新規建注文訂正内容)<BR>
     * @@param l_blnIsSkipDelayStatusCheck - (isSkip遅延状況チェック)<BR>
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
                "パラメータ値不正。");
        }
        
        //getOrderId( )
        long l_lngOrderId = l_marginChangeOrderSpec.getOrderId();
        
        //getOrder(arg0 : long)
        //注文オブジェクトを取得する。 
        //引数は以下の通り指定する。 
        //注文ID：　@getOrderId()の戻り値
        Order l_order = null;
        OrderUnit l_orderUnit = null;
        Market l_market = null;
        Product l_product = null;
        WEB3GentradeMarket l_gentradeMarket = null;
        boolean l_isShortSellingRestraint = false;
        try
        {
            l_order = this.getOrder(l_lngOrderId);
        
            //validate注文訂正可能状態(注文, boolean)
            //注文の訂正が可能か注文状態であるかどうかをチェックする。 
            //引数は以下の通り指定する。 
            //注文：　@getOrder()の戻り値 
            //isSkip遅延状況チェック：　@パラメータ.isSkip遅延状況チェック
            this.validateOrderForChangeability(l_order, l_blnIsSkipDelayStatusCheck);
            
            //get新規建注文訂正内容詳細( )
            EqTypeChangeOrderUnitEntry l_eqChangeOrderUnitEntry = 
                l_marginChangeOrderSpec.getChangeOrderUnitEntry();
            
            //getOrderUnitId( )
            long l_lngOrderUnitId = l_eqChangeOrderUnitEntry.getOrderUnitId();
            
            //getOrderUnit(arg0 : long)
            //注文単位オブジェクトを取得する。 
            //引数は以下の通り指定する。 
            //注文単位ID：　@getOrderUnitId()の戻り値
            l_orderUnit = this.getOrderUnit(l_lngOrderUnitId);
            EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit)l_orderUnit;
            
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();
            
            //注文単位.get弁済区分( ) 
            String l_strRepaymentType = l_orderUnitRow.getRepaymentType();
            
            //validate信用注文(補助口座, String)
            //信用取引注文の共通チェックを実施する。 
            //以下のチェックを行う。 
            //　@－受付時間チェック 
            //　@－システム停止中チェック 
            //　@－顧客のチェック（Ｙ客、管理ロック等） 
            //　@－信用実施会社チェック 
            //　@－信用客チェック（信用口座開設チェック） 
            //引数は以下の通り指定する。 
            //補助口座：　@パラメータ.補助口座 
            //弁済区分：　@注文単位.弁済区分
            validateMarginOrder(l_genSubAccount, l_strRepaymentType);
            
            //getInstance( )
            WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
                = (WEB3EquityTypeOrderManagerReusableValidations) 
                    WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
            //getTaxType( )
            TaxTypeEnum l_taxTypeEnum = l_orderUnitRow.getTaxType();
            
            Timestamp l_tsDeliveryDate = l_orderUnitRow.getDeliveryDate();
    
            //validate特定口座開設（信用）(補助口座, TaxTypeEnum, Date)
            //一般／特定口座チェックを実施する。 
            //引数は以下の通り指定する。 
            //補助口座：　@パラメータ.補助口座 
            //税区分：　@　@getTaxType()の戻り値 
            //受渡日：　@getDeliveryDate()の戻り値
            l_orderManagerReusableValidations.validateMarginSpecialAccountOpen(
                l_genSubAccount, l_taxTypeEnum, l_tsDeliveryDate);
            
            //getMarket(arg0 : long)
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
    
            l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
    
            //validate市場コード(String, String)
            //引数は以下の通り指定する。 
            //市場コード：　@市場.getMarketCode() 
            //証券会社コード：　@補助口座.証券会社コード
            l_gentradeMarket = 
                (WEB3GentradeMarket) l_orderManagerReusableValidations.validateMarket(
                l_market.getMarketCode(), l_genSubAccount.getInstitution().getInstitutionCode());
            
            //getProduct(arg0 : long)
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
    
            //拡張プロダクトマネージャを取得 
            WEB3EquityProductManager l_equityProductManager
                       = (WEB3EquityProductManager) l_tradingModule.getProductManager();
            l_product = l_equityProductManager.getProduct(l_orderUnitRow.getProductId());
            
            //getSide( )
            SideEnum l_sideEnum = l_orderUnit.getSide();
            
            //validateインサイダー(補助口座, 株式銘柄)
            //引数は以下の通りに設定する。 
            //補助口座：　@引数の補助口座 
            //株式銘柄：　@注文単位.getProduct( )の戻り値の株式銘柄オブジェクト
            WEB3EquityProduct l_eqProduct = (WEB3EquityProduct)l_product;
            l_orderManagerReusableValidations.validateInsider(
                l_genSubAccount, l_eqProduct);
            
            //validate顧客銘柄別取引停止(補助口座, long, OrderTypeEnum)
            //引数は以下の通りに設定する。 
            //補助口座：　@引数の補助口座 
            //銘柄ID：　@validate銘柄コード（信用）( )の戻り値の株式銘柄オブジェクト.銘柄ID 
            //注文種別：　@注文単位.注文種別（＝訂正元注文の値）
            l_orderManagerReusableValidations.validateAccountProductOrderStop(
                l_genSubAccount, l_eqProduct.getProductId(), l_orderUnitRow.getOrderType());
            
            //validate取引銘柄（信用）(補助口座, 株式銘柄, 市場, 
            //      部店, String, OrderCategEnum, boolean, boolean)
            //引数は以下の通り指定する。 
            //補助口座：　@引数の補助口座 
            //株式銘柄：　@注文単位.getProduct( )の戻り値の株式銘柄オブジェクト 
            //市場：　@validate市場コード()の戻り値 
            //部店：　@補助口座.get取引店() 
            //弁済区分：　@注文単位.弁済区分 
            //注文カテゴリ：　@OrderCategEnum.新規建注文（OPEN_MARGIN）固定 
            //is売建： 
            //　@getSide()の戻り値 ＝ SideEnum.売りの場合 
            //　@　@trueをセット。 
            //　@以外、falseをセット。 
            //is売買停止チェック：　@false（＝売買停止チェックをしない）
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
            
            //get訂正後執行条件( )
            EqTypeExecutionConditionType l_executionConditionType =
                l_marginChangeOrderSpec.getModifiedExecutionType();
            
            //validate成行指定規制（信用）(取引銘柄, String, OrderCategEnum, 
            //      boolean, boolean, EqTypeExecutionConditionType)
            l_orderManagerReusableValidations.validateMarketOrderRestraint(
                l_tradedProduct,
                l_orderUnitRow.getRepaymentType(),
                OrderCategEnum.OPEN_MARGIN,
                l_blnIsAfterChangePriceMarket,
                l_blnIsSell,
                l_executionConditionType);
            
            //validate取扱可能市場（信用）(部店, 取引銘柄, String, String, double)
            //引数は以下の通り指定する。 
            //部店：　@パラメータ.補助口座.get取引店() 
            //取引銘柄：　@validate取引銘柄（信用）()の戻り値 
            //市場コード：　@validate市場コード()の戻り値.getMarketCode() 
            //弁済区分：　@注文単位.弁済区分 
            //弁済期限値：　@注文単位.弁済期限値
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
            
            //get訂正後注文失効日( )
            Date l_datModifiedExpirationDate = 
                l_marginChangeOrderSpec.getModifiedExpirationDate();
            
            //get訂正後値段条件( )
            String l_strModifiedPriceConditionType = 
                l_marginChangeOrderSpec.getModifiedPriceConditionType();
            
            //get訂正後発注条件( )
            String l_strModifiedOrderConditionType = 
                l_marginChangeOrderSpec.getModifiedOrderConditionType();
            
            //is訂正後出来るまで注文( )
            boolean l_blnIsModifiedIsCarriedOrder =
                l_marginChangeOrderSpec.isModifiedIsCarriedOrder();
            
            Date l_bizDate = WEB3DateUtility.getDate(l_firstFrderUnitRow.getBizDate(), "yyyyMMdd");
            
            //validate注文条件(補助口座, long, 取引銘柄, Date, Date, 
            //       String, EqTypeExecutionConditionType, boolean, String, String, String)
            //引数は以下の通り指定する。 
            //補助口座：　@パラメータ.補助口座 
            //注文単位ID：　@注文単位.注文単位ID 
            //取引銘柄：　@validate取引銘柄()の戻り値 
            //原注文発注日：　@get初回注文の注文単位()の戻り値の注文単位.発注日 
            //注文失効日：　@get訂正後注文失効日()の戻り値 
            //発注条件：　@get訂正後発注条件()の戻り値 
            //執行条件：　@get訂正後執行条件()の戻り値 
            //is出来るまで注文：　@is訂正後is出来るまで注文()の戻り値 
            //信用取引区分：　@注文単位.弁済区分 
            //値段条件：　@get訂正後値段条件()の戻り値 
            //市場コード：　@市場.getMarketCode()
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
            
            //get訂正後注文株数( )
            double l_dblModifiedOrderQuantity = 
                l_marginChangeOrderSpec.getModifiedOrderQuantity();
            
            //validate株数（信用）(取引銘柄, 部店, double, OrderTypeEnum, String, double)
            //引数は以下の通り指定する。 
            //取引銘柄：　@validate取引銘柄()の戻り値 
            //部店：　@パラメータ.補助口座.get取引店() 
            //株数：　@get訂正後注文株数()の戻り値 
            //注文種別：　@注文単位.注文種別 
            //弁済区分：　@注文単位.弁済区分 
            //弁済期限値：　@注文単位.弁済期限値 
            l_orderManagerReusableValidations.validateQuantity(
                l_tradedProduct,
                l_genSubAccount.getWeb3GenBranch(),
                l_dblModifiedOrderQuantity,
                l_orderUnitRow.getOrderType(),
                l_orderUnitRow.getRepaymentType(),
                l_orderUnitRow.getRepaymentNum());
            
            //getAfterChangePrice( )
            double l_dblAfterChangePrice = l_eqChangeOrderUnitEntry.getAfterChangePrice();
            
            //validate注文単価(double, 取引銘柄, 補助口座)
            //引数は以下の通り指定する。 
            //指値：　@信用新規建訂正内容詳細.getAfterChangePrice()の戻り値 
            //取引銘柄：　@validate取引銘柄()の戻り値 
            //補助口座：　@パラメータ.補助口座
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

            //get訂正後逆指値基準値( )
            double l_dblModifiedStopOrderPrice = l_marginChangeOrderSpec.getModifiedStopOrderPrice();
            
            //get訂正後（W指値）訂正指値( )
            double l_dblModifiedWLimitPrice = l_marginChangeOrderSpec.getModifiedWLimitPrice();
            
            //get訂正後（W指値）執行条件( )
            EqTypeExecutionConditionType l_changeAfterWlimitExecCondType = 
                l_marginChangeOrderSpec.getModifiedWlimitExecCondType();
            
            //get（W指値）有効状態区分( )
            String l_strWlimitEnableStatusDiv = l_marginChangeOrderSpec.getWlimitEnableStatusDiv();
            
            //is買注文(EqTypeOrderUnit)
            boolean l_blnIsBuyOrder = this.isBuyOrder(l_eqOrderUnit);
            
            //validateW指値注文(補助口座, long, double, String, double, String, EqTypeExecutionConditionType, 
            //      String, 取引銘柄, boolean, String, OrderCategEnum, double, String, OrderTypeEnum)
            //[引数] 
            // 補助口座：　@パラメータ.補助口座 
            // 注文単位ＩＤ：　@注文単位.注文単位ID 
            // 指値：　@getAfterChangePrice()の戻り値 
            // 発注条件：　@get訂正後発注条件()の戻り値 
            // 発注条件単価：　@get訂正後逆指値基準値()の戻り値 
            // （W指値）訂正指値：　@get訂正後（W指値）訂正指値()の戻り値 
            // （W指値）執行条件：　@get訂正後（W指値）執行条件()の戻り値 
            // （W指値）有効状態区分：　@get（W指値）有効状態区分()の戻り値 
            // 取引銘柄：　@validate取引銘柄()の戻り値 
            // is買注文：　@is買注文()の戻り値 
            // 弁済区分：　@注文単位.弁済区分 
            // 注文カテゴリ：　@注文単位.注文カテゴリ 
            // 株数：　@get訂正後注文株数()の戻り値 
            // 値段条件：　@get訂正後値段条件( ) 
            // 注文種別：　@注文単位.注文種別 
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
            
            //get訂正後建代金( )
            double l_dblModifiedContractAmount = 
                l_marginChangeOrderSpec.getModifiedContractAmount();
            
            //validate新規建代金上限(補助口座, double, OrderTypeEnum, 取引銘柄, 注文単位)
            //引数は以下の通り指定する。 
            //補助口座：　@パラメータ.補助口座 
            //建代金：　@get訂正後建代金()の戻り値 
            //注文種別：　@注文単位.注文種別 
            //取引銘柄：　@株式発注審査個別チェック.validate取引銘柄（信用）()の戻り値
            //注文単位：　@注文単位
            l_orderManagerReusableValidations.validateMaxOpenMarginAmount(
                l_genSubAccount,
                l_dblModifiedContractAmount,
                l_orderUnitRow.getOrderType(),
                l_tradedProduct,
                l_orderUnitRow);

            //validate新規建株数上限(補助口座, double, OrderTypeEnum, 取引銘柄, 注文単位)
            //引数は以下の通り指定する。 
            //補助口座：　@パラメータ.補助口座 
            //注文数量：　@信用新規建注文訂正内容.get訂正後注文株数()の戻り値 
            //注文種別：　@注文単位.注文種別 
            //取引銘柄：　@株式発注審査個別チェック.validate取引銘柄（信用）()の戻り値
            //注文単位：　@注文単位
            l_orderManagerReusableValidations.validateMaxOpenMarginQuantity(
                l_genSubAccount,
                l_marginChangeOrderSpec.getModifiedOrderQuantity(),
                l_orderUnitRow.getOrderType(),
                l_tradedProduct,
                l_orderUnitRow);
            
            //is空売り規制(補助口座, 取引銘柄, double, OrderTypeEnum, boolean, 
            //EqTypeExecutionConditionType, String, 注文単位)
            //引数は以下の通りに設定する。 
            //補助口座：　@パラメータ.補助口座 
            //取引銘柄：　@validate取引銘柄（信用）()の戻り値 
            //株数：　@信用新規建注文訂正内容.get訂正後注文株数()の戻り値 
            //注文種別：　@注文単位.注文種別 
            //is成行：　@isAfterChangePriceMarket()の戻り値 
            //執行条件：　@信用新規建注文訂正内容.get訂正後執行条件( ) 
            //値段条件：　@信用新規建注文訂正内容.get訂正後値段条件( ) 
            //注文単位：　@注文単位
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
        
            //(*)分岐フロー
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            Date l_datOrderBizDate =
                WEB3DateUtility.getDate(
                    l_orderUnitRow.getBizDate(),
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            if (WEB3DateUtility.compare(l_datBizDate, l_datOrderBizDate) > 0)
            {
                //validate閉局後訂正取消受付可能(銘柄タイプ : ProductTypeEnum)
                //引数は以下の通りに設定する。 
                //銘柄タイプ：　@”株式”
                WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.EQUITY);
    
            }
            //get訂正後発注条件演算子
            String l_strModifiedOrderCondOperator = 
                l_marginChangeOrderSpec.getModifiedOrderCondOperator();
            
            //validate訂正項目(注文単位, double, double, EqTypeExecutionConditionType, 
            //      String, String, String, double, double, EqTypeExecutionConditionType, boolean, 
            //      Date, EqTypeSettleContractOrderEntry[],EqTypeSettleContractOrderEntry)
            //[引数] 
            // 注文単位：　@getOrderUnit()の戻り値 
            // 訂正後株数：　@get訂正後注文株数()の戻り値 
            // 訂正後指値：　@getAfterChangePrice()の戻り値 
            // 訂正後執行条件：　@get訂正後執行条件()の戻り値 
            // 訂正後値段条件：　@get訂正後値段条件()の戻り値 
            // 訂正後発注条件：　@get訂正後発注条件()の戻り値 
            // 訂正後発注条件演算子：　@get訂正後発注条件演算子()の戻り値 
            // 訂正後逆指値基準値：　@get訂正後逆指値基準値()の戻り値 
            // 訂正後（W指値）訂正指値：　@get訂正後（W指値）訂正指値()の戻り値 
            // 訂正後（W指値）執行条件：　@get訂正後（W指値）執行条件()の戻り値 
            // 訂正後is出来るまで注文：　@is訂正後出来るまで注文()の戻り値 
            // 訂正後注文失効日：　@get訂正後注文失効日()の戻り値 
            // 訂正後決済指定エントリ：　@null
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
            
            //validate訂正時注文Rev上限(注文単位, double, double, 
            //      EqTypeExecutionConditionType, String)
            //引数は以下の通りにセットする。 
            //訂正後株数：　@get訂正後注文株数()の戻り値 
            //訂正後指値：　@getAfterChangePrice()の戻り値 
            //訂正後執行条件：　@get訂正後執行条件()の戻り値 
            //訂正後値段条件：　@get訂正後値段条件()の戻り値
            l_orderManagerReusableValidations.validateChangeOrderRevUpperLimit(
                l_eqOrderUnit,
                l_dblModifiedOrderQuantity,
                l_dblAfterChangePrice,
                l_executionConditionType,
                l_strModifiedPriceConditionType);
        
            //株式発注審査結果(ProcessingResult, boolean)
            //引数は以下の通りに設定する。 
            //発注審査結果：　@ProcessingResult.SUCCESS_RESULT（発注審査OK） 
            //空売り規制対象フラグ：　@is空売り規制( )の戻り値
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
     * （validate銘柄コード（信用)）。<BR>
     * <BR>
     * 銘柄コードの存在チェック及び売買停止チェック（信用）を実施する。<BR>
     * チェック結果がOKの場合は、株式銘柄オブジェクトを返却する。<BR>
     * （* 株式発注審査チェック.validate銘柄コード（信用）( )に委譲する。）
     * @@param l_strProductCode 銘柄コード
     * @@param l_strInstitutionCode 証券会社コード
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 　@　@　@0：DEFAULT（指定なし）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
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
     * （validate取引銘柄（信用））。<BR>
     * <BR>
     * 取扱可能チェック、及び弁済区分別の売買停止チェック（信用）を実施する。<BR>
     * チェック結果がOKの場合は、取引銘柄オブジェクトを返却する。<BR>
     * （* 株式発注審査チェック.validate取引銘柄（信用）( )に委譲する。）
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座オブジェクト。
     * @@param l_product （株式銘柄）<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@param l_market （市場）<BR>
     * 　@　@　@市場オブジェクト。
     * @@param l_branch （部店）<BR>
     * 　@　@　@部店オブジェクト。
     * @@param l_strRepaymentType （弁済区分）<BR>
     * 　@　@　@弁済区分。<BR>
     * 　@　@　@0：DEFAULT（指定なし）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_orderCateg （注文カテゴリ）<BR>
     * 　@　@　@注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_isShort （is売建）<BR>
     * 　@　@　@売建／買建のフラグ。<BR>
     * 　@　@　@建株＝売建の場合true、買建の場合falseを指定する。
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

        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderManagerReusableValidations
            = (WEB3EquityTypeOrderManagerReusableValidations)WEB3EquityTypeOrderManagerReusableValidations.getInstance();

        log.exiting(STR_METHOD_NAME);
        // 株式発注審査チェック.validate取引銘柄（信用）()に委譲する。
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
     * （validate取扱可能市場（信用））。<BR>
     * <BR>
     * 会社部店が、信用取引の指定弁済区分・弁済期限の<BR>
     * 取扱可能市場かをチェックする。<BR>
     * （* 株式発注審査チェック.validate取扱可能市場（信用）( )に委譲する。）
     * @@param l_branch （部店）<BR>
     * 　@　@　@部店オブジェクト（顧客の取引店）
     * @@param l_tradedProduct （取引銘柄）<BR>
     * 　@　@　@株式取引銘柄オブジェクト
     * @@param l_strMarketCode 市場コード
     * @@param l_strRepaymentType 弁済区分。<BR>
     * 　@　@　@0：DEFAULT（指定なし）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_dblRepaymentNum 弁済期限値
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
     * （validate市場コード）。<BR>
     * <BR>
     * 市場コードのチェックを実施する。<BR>
     * 存在する場合は市場オブジェクトを返却する。<BR>
     * （* 株式発注審査チェック.validate市場コード( )に委譲する。）
     * @@param l_strMarketCode 市場コード
     * @@param l_strInstitutionCode 証券会社コード
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
     * （validate特定口座開設（信用））。<BR>
     * <BR>
     * 特定口座を開設しているかどうかのチェックを行う。<BR>
     * （* 株式発注審査チェック.validate特定口座開設（信用）( )に委譲する。）<BR>
     * @@param l_genSubAccount - 補助口座オブジェクト。
     * @@param l_taxType 税区分。<BR>
     * 　@　@　@（* 顧客マスタ.信用取引税区分）
     * @@param l_datDeliveryDate 受渡日。<BR>
     * 　@　@　@（取引銘柄.受渡日）
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
     * （is繰越注文単位）。<BR>
     * （is繰越注文単位(注文単位)のオーバーロードメソッド）<BR>
     * <BR>
     * 注文繰越で登録された注文かどうかを判定する。<BR>
     * 「繰越注文」の場合はtrueを、「繰越注文」ではない場合はfalseを、<BR>
     * それぞれ返却する。<BR>
     * <BR>
     * １）　@this.getOrderUnit(引数.注文単位ID)で、注文単位オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@this.is繰越注文単位(注文単位)にdelegateする。<BR>
     * @@param l_lngOrderUnitId （注文単位ID）<BR>
     * 　@　@　@注文単位オブジェクト.注文単位ID。
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
	 * （is繰越注文単位）。<BR>
	 * <BR>
	 * 注文繰越で登録された注文かどうかを判定する。<BR>
	 * 「繰越注文」の場合はtrueを、「繰越注文」ではない場合はfalseを、<BR>
	 * それぞれ返却する。<BR>
	 * <BR>
	 * １）　@引数の注文単位.初回注文の注文単位ID > 0の場合は、trueを返す。<BR>
	 * 　@　@　@引数の注文単位.初回注文の注文単位ID＝（null or 0）の場合は、falseを返す。
	 * @@param l_orderUnit （注文単位オブジェクト）<BR>
	 * 　@　@　@注文単位オブジェクト。
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
			//引数の注文単位.初回注文の注文単位ID＝（null or 0）の場合は、falseを返す。
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
     * （validate特定口座開設）。<BR>
     * <BR>
     * 特定口座を開設しているかどうかのチェックを行う。<BR>
     * （* 株式発注審査チェック.validate特定口座開設( )に委譲する。）<BR>
     * @@param l_subAccount 補助口座オブジェクト。
     * @@param l_taxType 税区分。<BR>
     * 　@　@　@（* 顧客マスタ.信用取引税区分）
     * @@param l_datDeliveryDate 受渡日。<BR>
     * 　@　@　@（取引銘柄.受渡日）
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
     * （get初回注文の注文単位）。<BR>
     * <BR>
     * 引数で指定された注文単位オブジェクトの、初回注文の注文単位オブジェクトを返す。<BR>
     * <BR>
     * １）　@引数の注文単位.初回注文の注文単位ID＝（null または 0）の場合<BR>
     * <BR>
     * 　@　@引数の注文単位オブジェクトを返却する。<BR>
     * <BR>
     * ２）　@上記以外の場合、<BR>
     * <BR>
     * 　@　@this.getOrderUnit(引数の注文単位.初回注文の注文単位ID)で取得した<BR>
     * 　@　@注文単位オブジェクトを返却する。
     * @@param l_orderUnit 注文単位オブジェクト。
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
                //引数の注文単位.初回注文の注文単位ID＝（null または 0）の場合
                //引数の注文単位オブジェクトを返却する。
                log.exiting(STR_METHOD_NAME);
                return l_orderUnit;
            }
            else
            {
                //上記以外の場合
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
     * （get取消対象約定）。<BR>
     * <BR>
     * 取消対象の約定データを取得し返却する。<BR>
     * <BR>
     * １）　@引数の注文単位.getExecutions( )で、対象注文に紐付く約定データを全て取得する。<BR>
     * <BR>
     * 　@　@　@該当する約定データが存在しない場合、「約定なし」の例外をthrowする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00676<BR>
     * <BR>
     * ２）　@１）で取得した約定データの中から、以下の条件に合致するデータを検索し、<BR>
     * 　@　@　@合致する約定データを返却する。<BR>
     * <BR>
     * 　@　@　@-----------------------------------------------------<BR>
     * 　@　@　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@約定.約定数量 ＝ 引数.約定株数<BR>
     * 　@　@　@かつ　@約定.約定単価 ＝ 引数.約定単価<BR>
     * 　@　@　@-----------------------------------------------------<BR>
     * <BR>
     * 　@　@　@・検索条件に合致するデータが複数件存在する場合、<BR>
     * 　@　@　@　@合致する約定データのうち、約定.約定日時が最も過去の日時のデータを、<BR>
     * 　@　@　@　@取消対象の約定データとして返却する。<BR>
     * 　@　@　@　@※約定.約定日時が最も過去の日時のデータが複数件存在する場合は、<BR>
     * 　@　@　@　@※約定.約定順番号が一番小さいデータ（＝最も過去の約定として扱う）を、<BR>
     * 　@　@　@　@※取消対象約定データとして返却する。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 約定単位。
     * @@param l_dblExecQuantity - (約定株数)<BR>
     * 約定株数。
     * @@param l_dblExecPrice - (約定単価)<BR>
     * 約定単価。
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
        
        //該当する約定データが存在しない場合、「約定なし」の例外をthrowする。 
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
     * （validateミニ株注文）。<BR>
     * <BR>
     * ミニ株取引注文の共通チェックを実施する。<BR>                                                                                              
     *  <BR>                                                                                                                                     
     * 以下のチェックを行う。 <BR>                                                                                                               
     * 　@－顧客のチェック（Ｙ客、管理ロック等）<BR>                                                                                              
     * 　@－受付時間チェック <BR>                                                                                                                 
     * 　@－システム停止中チェック  <BR>                                                                                                          
     * <BR>                                                                                                                                     
     * シーケンス図 <BR>                                                                                                                        
     * 「（ミニ株注文）validateミニ株注文」参照。
     * @@param l_subAccount （補助口座）
     * 　@　@　@補助口座オブジェクト
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
        //5) validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validate取引銘柄（ミニ株））。<BR>
     * <BR>
     * ミニ株銘柄，ミニ株市場をチェックし、取引銘柄オブジェクトを返却する。<BR>                                                             
     * 　@－株式銘柄存在チェック  <BR>                                                                                                       
     * 　@－ミニ株市場チェック  <BR>                                                                                                         
     * 　@－ミニ株売買が停止されていないことのチェック  <BR>                                                                                 
     * 　@－インサイダーチェック <BR>                                                                                                        
     * 　@－顧客銘柄別取引停止チェック <BR>                                                                                                  
     *  <BR>                                                                                                                               
     * シーケンス図 <BR>                                                                                                                   
     * 「（ミニ株注文）validate取引銘柄（ミニ株）」参照。 <BR>
     * <BR>
     * 　@5 取得した取引銘柄.上場区分＝”外国部上場”の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_01341
     * @@param l_subAccount （補助口座）。
     * @@param l_strProductCode （銘柄コード）。
     * @@param l_blnIsSellOrder （is売注文）。<BR>
     * 　@　@　@売注文かどうかを判定するフラグ <BR>
     * <BR>
     * 　@　@　@true：　@売注文<BR>
     * 　@　@　@false：　@買注文
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
        
        //3) validate銘柄コード（ミニ株）
        WEB3EquityProduct l_equityProduct = l_equityTypeOrderManagerReusableValidations.validateMiniStockProductCode(
            l_strProductCode, l_subAccount.getInstitution().getInstitutionCode());
        
        //4) validate取引銘柄（ミニ株）(株式銘柄, boolean)
        WEB3EquityTradedProduct l_tradedProduct = 
            l_equityTypeOrderManagerReusableValidations.validateMiniStockTradedProduct(l_equityProduct, l_blnIsSellOrder);
        
        //5) 取得した取引銘柄.上場区分＝”外国部上場”の場合のみ
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
        
        //6) validateインサイダー
        l_equityTypeOrderManagerReusableValidations.validateInsider(l_subAccount, l_equityProduct);
        
        //7) validate顧客銘柄別取引停止
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
     * （validateミニ株買付注文）。<BR>
     * <BR>
     * （validateMiniStockBuyOrder） <BR>
     * ミニ株買付注文発注審査を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ミニ株注文）validateミニ株買付注文」参照。
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座オブジェクト
     * @@param l_mstkOrderSpec （ミニ株注文内容）<BR>
     * 　@　@　@ミニ株注文内容オブジェクト
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
        //2) validateミニ株注文(補助口座)
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
        //7) getTradedProduct(arg0（=証券会社） : Institution, arg1（=銘柄コード） : String, arg2（=市場コード） : String)
        try
        {
            WEB3EquityTradedProduct l_tradedProduct = 
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
            //8) getQuantity()
            double l_dblQuantity = l_mstkOrderSpec.getQuantity();
            //9) validateミニ株買付株数(取引銘柄, double)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockBuyQuantity(l_tradedProduct, l_dblQuantity);
            //10) validateミニ株重複注文(補助口座, 取引銘柄)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
            //11) validateミニ株買付制限期間注文株数(補助口座, 取引銘柄, double)
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
     * （validateミニ株売付注文）<BR>
     * <BR>
     * （validateMiniStockSellOrder）<BR>                                                   
     * ミニ株売付注文発注審査を実施する。<BR>                                               
     *  <BR>                                                                                
     * シーケンス図 <BR>                                                                    
     * 「（ミニ株注文）validateミニ株売付注文」参照。<BR>   
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座オブジェクト
     * @@param l_mstkOrderSpec （ミニ株注文内容）<BR>
     * 　@　@　@ミニ株注文内容オブジェクト
     * @@return NewOrderValidationResult
     */
    public NewOrderValidationResult validateMiniStockSellOrder(
        WEB3GentradeSubAccount l_subAccount, WEB3NewMiniStockOrderSpec l_mstkOrderSpec) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMiniStockSellOrder(WEB3GentradeSubAccount, WEB3NewMiniStockOrderSpec)";
        log.entering(STR_METHOD_NAME);
        
        //2) validateミニ株注文(補助口座)
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
        //7) getTradedProduct(arg0（=証券会社） : Institution, arg1（=銘柄コード） : String, arg2（=市場コード） : String)
        try
        {
            WEB3EquityTradedProduct l_tradedProduct = 
                (WEB3EquityTradedProduct)l_productManager.getTradedProduct(l_institution, l_strProductCode, l_strMarketCode);
            //8) getQuantity()
            double l_dblQuantity = l_mstkOrderSpec.getQuantity();
            //9) validateミニ株売付株数(取引銘柄, double)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockSellQuantity(l_subAccount, l_tradedProduct, l_dblQuantity);
            //10) validateミニ株重複注文(補助口座, 取引銘柄)
            l_equityTypeOrderManagerReusableValidations.validateMiniStockDuplicateOrder(l_subAccount, l_tradedProduct);
            //11) validateミニ株売付制限期間注文株数(補助口座, 取引銘柄, double)
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
     * （validateミニ株注文取消）。<BR>
     * <BR>
     * （validateMiniStockCancelOrder）<BR>                                 
     *  <BR>                                                                
     * シーケンス図 <BR>                                                    
     * 「（ミニ株注文）validateミニ株注文取消」参照。<BR>
     * <BR>
     *  ========================================================<BR>
     * シーケンス図(注文・約定エンティティ / （ミニ株注文）validateミニ株注文取消): <BR>
     * 取消可能な注文でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00155<BR>
     * ==========================================================
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座オブジェクト
     * @@param l_cancelOrderSpec （取消注文内容）<BR>
     * 　@　@　@取消注文内容オブジェクト
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
        //3) validateミニ株注文(補助口座)
        try
        {
            this.validateMiniStockOrder(l_subAccount);

            //4) is取消可能注文単位（ミニ株）(注文単位)
            boolean l_blnMiniStockCancelOrderUnit = this.isMiniStockCancelOrderUnit(l_orderUnits[0]);
            //5) 取消可能な注文でない場合、例外をスローする
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
     * （validateミニ株重複注文）。<BR>
     * <BR>
     * 同一日に同一銘柄のミニ注文がされていないかチェックを行う。 <BR>            
     *  <BR>                                                                       
     * 発注審査個別チェック.validateミニ株重複注文()に処理を委譲する。<BR>  
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座オブジェクト
     * @@param l_tradedProduct （取引銘柄）<BR>
     * 　@　@　@取引銘柄オブジェクト
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
     * （calc概算受渡代金（ミニ株））。<BR>
     * <BR>
     * ミニ株概算受渡金額を算出して返却する。<BR>
     * <BR>                                               
     * シーケンス図「（ミニ株注文）calc概算受渡代金（ミニ株）」参照。 
     * @@param l_commission （手数料）
     * @@param l_subAccount （補助口座）
     * @@param l_tradedProduct （取引銘柄）
     * @@param l_dblQuantity （株数）
     * @@param l_blnIsSellOrder （is売注文）<BR>
     * 　@　@　@売注文の場合はtrue、買注文の場合はfalseを指定する。
     * @@param l_dblCalcUnitPrice （計算単価）
     * @@param l_blnIsRestraint （is拘束考慮）
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
        
        //1.1. 概算受渡代金計算結果()
        WEB3EquityEstimatedDeliveryPrice l_equityEstimatedDeliveryPrice = 
            new WEB3EquityEstimatedDeliveryPrice();
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        WEB3EquityBizLogicProvider l_equityBizLogicProvider = 
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        //1.2. 計算単価が指定されなかった（引数の計算単価 == null）場合のみ実施
        if(Double.isNaN(l_dblCalcUnitPrice) || l_dblCalcUnitPrice == 0.0D)
        {
            //1.2.1. get時価()
            l_dblCalcUnitPrice = l_productManager.getCurrentPrice(l_tradedProduct, true);
        }

        //1.3. setIs指値()
        l_commission.setIsLimitPrice(false);
        
        //1.4. set計算単価()
        l_equityEstimatedDeliveryPrice.setCalcUnitPrice(l_dblCalcUnitPrice);

        //1.5. get取引店()
        WEB3GentradeBranch l_branch = l_subAccount.getWeb3GenBranch();
        
        //1.6. get手数料商品コード()
        String l_strCommissionProductCode =
            l_commission.getCommissionProductCode();
        
        //1.7. get売買単位()
        double l_dblLotSize = l_tradedProduct.getLotSize();
        
        //1.8. 拘束売買代金を算出する場合
        if (!l_blnIsSellOrder && l_blnIsRestraint)
        {
            //1.8.1. calc拘束売買代金()
            double l_dblRestraintTurnover = l_equityBizLogicProvider.calcRestraintTurnover(
                l_dblLotSize,
                l_equityEstimatedDeliveryPrice.getCalcUnitPrice(),
                l_branch.getBranchId(),
                l_strCommissionProductCode,
                false);
            
            //1.9. set諸経費計算用代金()
            l_commission.setExpensesCalcAmount(l_dblRestraintTurnover);
        }
        else
        {
            //1.9. set諸経費計算用代金()
            l_commission.setExpensesCalcAmount(
                l_dblLotSize * l_equityEstimatedDeliveryPrice.getCalcUnitPrice());
        }
        //1.10. calc委託手数料()
        l_equityBizLogicProvider.calcCommission(l_commission, l_subAccount);
        
        //1.11. get手数料金額()
        double l_dblCommissionAmount = l_commission.getCommission();
        //1.12. get最低手数料()
        double l_dblLowCommAmount = l_commission.getMinCommission();
        
        //1.13. set委託手数料()
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
        
        // 委託手数料の小数点以下の切り捨て
		l_dblAmount = Math.floor(l_dblAmount);
        log.debug("委託手数料 = " + l_dblAmount);
        l_equityEstimatedDeliveryPrice.setCommissionFee(l_dblAmount);
        
        //1.14. get発注日()
        Timestamp l_tsBizDate = l_commission.getOrderBizDate();
        
        //1.15. calc消費税()
        double l_dblSalesTax =
            l_equityBizLogicProvider.calcSalesTax(
                l_dblAmount,
                l_tsBizDate,
                l_subAccount);
        log.debug("calc消費税 = " + l_dblSalesTax);
        
        //1.16. set委託手数料消費税()
        l_equityEstimatedDeliveryPrice.setCommissionFeeTax(l_dblSalesTax);
        
        //1.17. set概算受渡代金()
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
        
        // 概算受渡代金の小数点以下の切り捨て
        l_dblResults = Math.floor(l_dblResults);   
        log.debug("概算受渡代金 = " + l_dblResults);
        l_equityEstimatedDeliveryPrice.setEstimateDeliveryAmount(l_dblResults);
        
        return l_equityEstimatedDeliveryPrice;
    }
    /**
     * （getミニ株注文中株数）。<BR>
     * <BR>
     * （getMiniStockOrderingQuantity）<BR>                                                          
     *  <BR>                                                                                         
     * １）　@注文単位取得 <BR>                                                                                 
     * 　@【注文単位テーブル】から、以下の条件を指定し該当する注文単位を取得する。<BR>                                         
     *   <BR>                                                                                                                                              
     * 　@[条件]  <BR>                                                                                                                                             
     * 　@口座ＩＤ = 口座ＩＤ And <BR>                                                                                                                            
     * 　@補助口座ＩＤ = 補助口座ＩＤ And  <BR>                                                                                                                   
     * 　@銘柄ＩＤ = 銘柄ＩＤ And <BR>                                                                                                                              
     * 　@注文種別 = ※ And <BR>                                                                                                                                                                   
     * 　@注文有効状態 = OrderOpenStatusEnum.OPEN  <BR>                                                                                                                                            
     *   <BR>                                                                                                                                                                                     
     * 　@※ 注文種別  <BR>                                                                                                               
     * 　@　@売指定（is売注文 == true）の場合、<BR>
     *     OrderTypeEnum.MINI_STOCK_SELL（株式ミニ投資売注文） <BR>                                        
     * 　@　@買指定（is売注文 == false）の場合、<BR>
     *     OrderTypeEnum.MINI_STOCK_BUY（株式ミニ投資買注文）<BR>                                                                                              
     *   <BR>                                                                                                                            
     * ２）　@株数合計 <BR>                                                                                                                                                
     * 　@取得したすべての株数（注文単位.getQuantity()）の合計値を返却する。  
     * @@param l_lngMainAccountId （口座ID）
     * @@param l_lngSubAccountId （補助口座ID）
     * @@param l_lngProductId （銘柄ID）
     * @@param l_blnIsSellOrder (is売注文)<BR>
     * 　@　@　@売注文かどうかを判定するフラグ <BR>
     * <BR>
     * 　@　@　@true：　@売注文 <BR>
     * 　@　@　@false：　@買注文
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
     * （is取消可能注文単位（ミニ株））。<BR>
     * <BR>
     * 指定の注文単位が取消可能かを判定する。<BR>                                                    
     * <BR>                                                                                          
     * １）　@発注日のチェック                                                                              
     * 　@取消元注文の発注日と本日注文の発注日が違う場合※false、<BR>
     *   以外はtrueを返却する。<BR>                                    
     * <BR>                                                                                                                                                
     * ※ 取消元注文の発注日と本日注文の発注日が違う場合の判定  <BR>                                                                                              
     * 取引時間管理.get発注日() != 注文単位.発注日 <BR>                                                                                                          
     *  <BR>                                                                                                                                                     
     * ２）　@取消済みのチェック  <BR>                                                                                                                              
     * 　@注文単位.getOrderStatus() == OrderStatusEnum.CANCELLED <BR>                                                                                                                              
     * の場合、既に取消済みの注文と判定し、例外をスローする。<BR> 
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag:   BUSINESS_ERROR_00675
     * @@param l_orderUnit （注文単位）<BR>
     * 　@　@　@取消元注文単位オブジェクト
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
     * （get値段条件）。<BR>
     * <BR>
     * 引数の値段条件（SONAR）より、WebⅢにおける値段条件を取得し返却する。<BR>
     * <BR>
     * ○引数の値段条件（SONAR）＝" "（半角ブランク(指定なし)）の場合<BR>
     * 　@　@"0"を返す。<BR>
     * <BR>
     * ○引数の値段条件（SONAR）＝"A"（現在値指値） の場合<BR>
     * 　@　@"1"を返す。<BR>
     * <BR>
     * ○引数の値段条件（SONAR）＝"B"（優先指値） の場合<BR>
     * 　@　@"3"を返す。<BR>
     * <BR>
     * ○引数の値段条件（SONAR）＝"C"（成残指値） の場合<BR>
     * 　@　@"5"を返す。<BR>
     * <BR>
     * ○引数の値段条件（SONAR）＝"D"（成残取消） の場合<BR>
     * 　@　@"7"を返す。<BR>
     * <BR>
     * ○引数の値段条件（SONAR）が（" ", "A", "B", "C", "D"）以外の場合<BR>
     * 　@　@例外をthrowする。<BR>
     * 　@　@　@class: WEB3SystemLayerException<BR>
     * 　@　@　@tag:   SYSTEM_ERROR_80002
     * @@param l_strPriceConditionTypeSonar （値段条件（SONAR））<BR>
     * 　@　@　@SONARの値段条件。<BR>
     * <BR>
     * 　@　@　@※WebⅢにおける値段条件は、取引所のコード体系と同一である。<BR>
     * <BR>
     * 　@　@　@" "（半角ブランク）：　@指定なし<BR>
     * 　@　@　@"A"：　@現在値指値<BR>
     * 　@　@　@"B"：　@優先指値<BR>
     * 　@　@　@"C"：　@成残指値<BR>
     * 　@　@　@"D"：　@成残取消<BR>
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
            log.error("不正なコード：[" + l_strPriceConditionTypeSonar + "]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionType;
    }

    /**
     * （get値段条件（SONAR））。<BR>
     * <BR>
     * 引数の値段条件より、SONARの値段条件を取得し返却する。<BR>
     * <BR>
     * ○引数の値段条件＝"0"（DEFAULT(条件指定なし)） の場合<BR>
     * 　@　@" "（半角ブランク）を返す。<BR>
     * <BR>
     * ○引数の値段条件＝"1"（現在値指値注文） の場合<BR>
     * 　@　@"A"を返す。<BR>
     * <BR>
     * ○引数の値段条件＝"3"（優先指値注文） の場合<BR>
     * 　@　@"B"を返す。<BR>
     * <BR>
     * ○引数の値段条件＝"5"（成行残数指値注文） の場合<BR>
     * 　@　@"C"を返す。<BR>
     * <BR>
     * ○引数の値段条件＝"7"（成行残数取消注文） の場合<BR>
     * 　@　@"D"を返す。<BR>
     * <BR>
     * ○引数の値段条件が（"0", "1", "3", "5", "7"）以外の場合<BR>
     * 　@　@例外をthrowする。<BR>
     * 　@　@　@class: WEB3SystemLayerException<BR>
     * 　@　@　@tag:   SYSTEM_ERROR_80002
     * @@param l_strPriceConditionType （値段条件）<BR>
     * <BR>
     * 　@　@　@WebⅢの値段条件。（取引所のコード体系と同一）<BR>
     * <BR>
     * 　@　@　@0：　@DEFAULT(条件指定なし)<BR>
     * 　@　@　@1：　@現在値指値注文<BR>
     * 　@　@　@3：　@優先指値注文<BR>
     * 　@　@　@5：　@成行残数指値注文<BR>
     * 　@　@　@7：　@成行残数取消注文<BR>
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
            log.error("不正なコード：[" + l_strPriceConditionType + "]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionTypeSonar;
    }

    /**
     * （get執行条件）。<BR>
     * <BR>
     * 引数の執行条件（SONAR）より、WebⅢにおける執行条件を取得し返却する。<BR>
     * <BR>
     * ○引数の執行条件（SONAR）＝nullの場合 <BR>
     * 　@　@nullを返す。 <BR>
     * <BR>
     * ○引数の執行条件（SONAR）＝"1"（無条件） の場合<BR>
     * 　@　@EqTypeExecutionConditionType.NONE（条件なし）を返す。<BR>
     * <BR>
     * ○引数の執行条件（SONAR）＝"3"（寄付） の場合<BR>
     * 　@　@EqTypeExecutionConditionType.AT_MARKET_OPEN（寄り）を返す。<BR>
     * <BR>
     * ○引数の執行条件（SONAR）＝"4"（引け） の場合<BR>
     * 　@　@EqTypeExecutionConditionType.AT_MARKET_CLOSE（引け）を返す。<BR>
     * <BR>
     * ○引数の執行条件（SONAR）＝"7"（出来ずば引成(不成)） の場合<BR>
     * 　@　@EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED（不出来引け成行）<BR>
     * 　@　@を返す。<BR>
     * <BR>
     * ○引数の執行条件（SONAR）が上記以外の場合<BR>
     * 　@　@例外をthrowする。<BR>
     * 　@　@　@class: WEB3SystemLayerException<BR>
     * 　@　@　@tag:   SYSTEM_ERROR_80002
     * @@param l_strExecutionConditionTypeSonar (執行条件（SONAR）)<BR>
     * 　@　@　@SONARの執行条件。<BR>
     * <BR>
     * 　@　@　@"1"：　@無条件<BR>
     * 　@　@　@"3"：　@寄付<BR>
     * 　@　@　@"4"：　@引け<BR>
     * 　@　@　@"7"：　@出来ずば引成(不成)<BR>
     * <BR>
     * 　@　@　@※"2"：　@出合　@については、PR層からは受領しないこと、<BR>
     * 　@　@　@※SONAR入力通知キューにも含まれないことから、対象外とする。
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
			log.error("不正なコード：[" + l_strExecutionConditionTypeSonar + "]");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80002,
				this.getClass().getName() + STR_METHOD_NAME);
		}
       
        log.exiting(STR_METHOD_NAME);
        return l_executionConditionType;
    }

    /**
     * （get執行条件（SONAR））。<BR>
     * <BR>
     * 引数の値段条件より、SONARの執行条件を取得し返却する。<BR>
     * <BR>
     * ○引数の執行条件＝EqTypeExecutionConditionType.NONE（条件なし） の場合<BR>
     * 　@　@"1"（無条件）を返す。<BR>
     * <BR>
     * ○引数の執行条件＝EqTypeExecutionConditionType.AT_MARKET_OPEN（寄り） の場合<BR>
     * 　@　@"3"（寄付）を返す。<BR>
     * <BR>
     * ○引数の執行条件＝EqTypeExecutionConditionType.AT_MARKET_CLOSE（引け） の場合<BR>
     * 　@　@"4"（引け）を返す。<BR>
     * <BR>
     * ○引数の執行条件＝EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED<BR>
     * 　@（不出来引け成行） の場合<BR>
     * 　@　@"7"（出来ずば引成(不成)） を返す。<BR>
     * <BR>
     * ○引数の執行条件が上記以外の場合<BR>
     * 　@　@例外をthrowする。<BR>
     * 　@　@　@class: WEB3SystemLayerException<BR>
     * 　@　@　@tag:   SYSTEM_ERROR_80002
     * @@param l_executionConditionType (執行条件)<BR>
     * 　@　@　@xTradeの執行条件。<BR>
     * <BR>
     * 　@　@　@EqTypeExecutionConditionType.NONE：　@条件なし<BR>
     * 　@　@　@EqTypeExecutionConditionType.AT_MARKET_OPEN：　@寄り<BR>
     * 　@　@　@EqTypeExecutionConditionType.AT_MARKET_CLOSE：　@引け<BR>
     * 　@　@　@EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED：　@不出来引け成行
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
            log.error("不正なコード：[" + l_executionConditionType + "]");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_executionConditionTypeSonar;
    }

    /**
     * （isインサイダー警告表示）。<BR>
     * <BR>
     * インサイダーチェックを行う。<BR>
     * －"警告のみ"の場合はtrueを返却する。<BR>
     * －該当データなし、または"チェックしない"の場合はfalseを返却する。<BR>
     * －"注文停止"の場合は例外をthrowする。<BR>
     * <BR>
     * １）　@内部者.get内部者(顧客, 株式銘柄)により、内部者オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜get内部者( )：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@顧客：　@引数の補助口座.getMainAccount( )<BR>
     * 　@　@　@株式銘柄：　@拡張プロダクトマネージャ.getProduct(引数の銘柄ID)の<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@戻り値の銘柄オブジェクト<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２）　@取得した内部者オブジェクト.登録状況区分＝"警告のみ"の場合は、trueを返却する。<BR>
     * <BR>
     * 　@　@　@取得した内部者オブジェクト.登録状況区分＝"注文停止"の場合は、<BR>
     * 　@　@　@例外をthrowする。<BR>
     * <BR>
     * 　@　@　@上記以外の場合（該当データなしも含む）は、falseを返却する。
     * @@param l_subAccount （補助口座）
     * @@param l_productId （銘柄ID）
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

        // １）　@内部者.get内部者(顧客, 株式銘柄)により、内部者オブジェクトを取得する。
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
            // 該当データ無しの場合は、そのままリターンする。
            return false;
        }

        // ２）　@取得した内部者オブジェクト.登録状況区分＝"警告のみ"の場合は、trueを返却する。
        // 　@　@　@取得した内部者オブジェクト.登録状況区分＝"注文停止"の場合は、
        // 　@　@　@例外をthrowする。
        // 　@　@　@上記以外の場合（該当データなしも含む）は、falseを返却する。
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
     * （validateインサイダー）。<BR>
     * <BR>
     * インサイダーチェックを行う。<BR>
     * （* 株式発注審査チェック.validateインサイダー( )に委譲する。）<BR>
     * <BR>
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座。
     * @@param l_eqtyprProduct （株式銘柄）<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@throws WEB3BaseException
     */
    public void validateInsider(
        SubAccount l_subAccount,
        EqTypeProduct l_eqtyprProduct)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.validateインサイダー()に委譲する。
        l_orderMgrResVal.validateInsider(l_subAccount, l_eqtyprProduct);
    }

    /**
     * （validate顧客銘柄別取引停止）。<BR>
     * <BR>
     * 顧客銘柄別取引停止チェックを行う。<BR>
     * （* 株式発注審査チェック.validate顧客銘柄別取引停止( )に委譲する。）
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座。
     * @@param l_lngProductId （銘柄ID）<BR>
     * 　@　@　@銘柄ID。
     * @@param l_orderType （注文種別）<BR>
     * 　@　@　@注文種別。
     * @@throws WEB3BaseException
     */
    public void validateAccountProductOrderStop(
        SubAccount l_subAccount,
        long l_lngProductId,
        OrderTypeEnum l_orderType)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.validate顧客銘柄別取引停止()に委譲する。
        l_orderMgrResVal.validateAccountProductOrderStop(l_subAccount, l_lngProductId, l_orderType);
    }

    /**
     * （validate取引可能上限金額）。<BR>
     * <BR>
     * 概算金額値が、会社・部店で一度に取引可能な上限金額を超えていないかチェックを行う。<BR>
     * （* 株式発注審査チェック.validate取引可能上限金額( )に委譲する。）
     * @@param l_branch （部店）<BR>
     * 　@　@　@部店オブジェクト
     * @@param l_market （市場）<BR>
     * 　@　@　@市場オブジェクト
     * @@param l_dblRestraintTurnover （拘束売買代金）<BR>
     * 　@　@　@拘束売買代金を指定する。
     * @@param l_mainAccountTypeEnum （口座タイプ）<BR>
     * 　@　@　@口座タイプ。
     * @@throws WEB3BaseException
     */
    public void validateMaxHandlingPrice(
        Branch l_branch,
        Market l_market,
        double l_dblRestraintTurnover,
        MainAccountTypeEnum l_mainAccountTypeEnum)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.株式発注審査チェック.validate取引可能上限金額()に委譲する。
        l_orderMgrResVal.validateMaxHandlingPrice(l_branch, l_market, l_dblRestraintTurnover, l_mainAccountTypeEnum);
    }

    /**
     * （get注文単価区分一覧）。<BR>
     * 注文単価区分一覧を取得する。 <BR>
     * <BR>
     * １）　@引数の取引銘柄.市場IDに該当する市場オブジェクト.市場コード==JASDAQ　@<BR>
     * 　@　@　@かつ　@引数の取引銘柄.店頭公開区分==マーケットメイクの場合 <BR>
     * <BR>
     *  　@１：指値 <BR>
     * 　@ 　@　@を返却する。 <BR>
     * <BR>
     * ２）　@引数の取引銘柄.市場IDに該当する市場オブジェクト.isPTS市場( )がtrueの場合<BR>
     *  　@１：指値<BR>
     * 　@ 　@　@を返却する。<BR>
     * <BR>
     * ３）　@上記以外の場合<BR>
     * 　@　@0：成行 <BR>
     * 　@　@1：指値 <BR>
     * 　@の配列を返却する。<BR>
     * @@param l_branch （部店）<BR>
     * 　@　@　@部店オブジェクト。
     * @@param l_tradedProduct （取引銘柄）<BR>
     * 　@　@　@取引銘柄。
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
            //１：指値 
            l_strResults = new String[1];
            l_strResults[0] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //２）　@引数の取引銘柄.市場IDに該当する市場オブジェクト.isPTS市場( )がtrueの場合
        else if (((WEB3GentradeMarket)l_tradedProduct.getMarket()).isPTSMarket())
        {
            //１：指値 
            l_strResults = new String[1];
            l_strResults[0] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        //３）　@上記以外の場合
        else
        {
            // 0：成行 
            // 1：指値 
            l_strResults = new String[2];
            l_strResults[0] = WEB3OrderPriceDivDef.MARKET_PRICE;
            l_strResults[1] = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strResults;
    }

    /**
     * （validate取扱可能市場）。<BR>
     * <BR>
     * 会社部店の取扱可能市場かをチェックする。<BR>
     * （* 株式発注審査チェック.validate取扱可能市場( )に委譲する。）
     * @@param l_branch （部店）<BR>
     * 　@　@　@部店オブジェクト（顧客の取引店）。
     * @@param l_tradedProduct （取引銘柄）<BR>
     * 　@　@　@株式取引銘柄オブジェクト。
     * @@throws WEB3BaseException
     */
    public void validateHandlingMarket(
        WEB3GentradeBranch l_branch,
        TradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.株式発注審査チェック.validate取扱可能市場()に委譲する。
        l_orderMgrResVal.validateHandlingMarket(l_branch, l_tradedProduct);
    }

    /**
     * （get値幅上限値）。<BR>
     * <BR>
     * 値幅上限値を取得する。<BR>
     * <BR>
     * 1)　@基準値取得<BR>
     * －株式発注審査個別チェック.calc基準値(取引銘柄)をコールする。<BR>
     * 　@※引数設定<BR>
     * 　@　@・取引銘柄：　@引数の取引銘柄<BR>
     * <BR>
     * 2)　@値幅取得<BR>
     * －株式発注審査個別チェック.calc値幅(取引銘柄,基準値,上限/下限区分)をコールする。<BR>
     * 　@※引数設定<BR>
     * 　@　@・取引銘柄：　@引数の取引銘柄<BR>
     * 　@　@・基準値：　@1)calc基準値（）の戻り値<BR>
     * 　@　@・上限/下限区分：　@上限<BR>
     * <BR>
     * 3)　@指値単位取得<BR>
     * －拡張プロダクトマネージャ.get刻み値（取引銘柄,基準値）をコールする。<BR>
     * 　@※引数設定<BR>
     * 　@　@・取引銘柄：　@引数の取引銘柄<BR>
     * 　@　@・基準値：　@1)calc基準値（）の戻り値　@＋　@2)calc値幅()の戻り値<BR>
     * <BR>
     * 4)　@値幅上限値取得<BR>
     * －株式発注審査個別チェック.calc値幅上限(基準値,値幅,指値単位)をコールする。<BR>
     * 　@※引数設定<BR>
     * 　@　@・基準値：　@1)calc基準値()の戻り値<BR>
     * 　@　@・値幅：　@2)calc値幅()の戻り値<BR>
     * 　@　@・指値単位：　@3)get刻み値()の戻り値<BR>
     * <BR>
     * 5)　@4)calc値幅上限()の戻り値を返却する。
     * @@param l_tradedProduct （取引銘柄）<BR>
     * 　@　@　@取引銘柄オブジェクト
     * @@return double
     */
    public double getStopHighPrice(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopHighPrice(TradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        // 1)　@基準値取得
        double l_dblBasePrice = l_orderMgrResVal.calcBasePrice(l_tradedProduct);
        
        // 2)　@値幅取得
        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MAXIMUM);
        
        // 3)　@指値単位取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityProductManager l_productManager =
            (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
        double l_dblTickValue = l_productManager.getTickValue(
            l_tradedProduct,
            (l_dblBasePrice + l_dblPriceRange));
        
        // 4)　@値幅上限値取得
        double l_dblStopHighPrice = l_orderMgrResVal.calcStopHighPrice(
            l_dblBasePrice,
            l_dblPriceRange,
            l_dblTickValue);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblStopHighPrice;
    }

    /**
     * （get値幅下限値）。<BR>
     * <BR>
     * 値幅下限値を取得する。<BR>
     * <BR>
     * 1)　@基準値取得<BR>
     * －株式発注審査個別チェック.calc基準値(取引銘柄)をコールする。<BR>
     * 　@※引数設定<BR>
     * 　@　@・取引銘柄：　@引数の取引銘柄<BR>
     * <BR>
     * 2)　@値幅取得<BR>
     * －株式発注審査個別チェック.calc値幅(取引銘柄,基準値,上限/下限区分)をコールする。<BR>
     * 　@※引数設定<BR>
     * 　@　@・取引銘柄：　@引数の取引銘柄<BR>
     * 　@　@・基準値：　@1)calc基準値（）の戻り値<BR>
     * 　@　@・上限/下限区分：　@下限<BR>
     * <BR>
     * 3)　@値幅下限値取得<BR>
     * 　@3-1)（基準値－値幅）≦0の場合、1を返却する。<BR>
     * <BR>
     * 　@3-2)　@3-1)以外<BR>
     * 　@　@　@　@（基準値－値幅）を返却する。<BR>
     * <BR>
     * 　@※基準値：　@1)calc基準値（）の戻り値<BR>
     * 　@※値幅：　@2)calc値幅()の戻り値<BR>
     * 　@※少数点以下切り捨てとする。
     * @@param l_tradedProduct （取引銘柄）<BR>
     * 　@　@　@取引銘柄オブジェクト
     * @@return double
     */
    public double getStopLowPrice(
        WEB3EquityTradedProduct l_tradedProduct)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getStopLowPrice(TradedProduct)";
        log.entering(STR_METHOD_NAME);
        
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
        
        // 1)　@基準値取得
        double l_dblBasePrice = l_orderMgrResVal.calcBasePrice(l_tradedProduct);
        
        // 2)　@値幅取得
        double l_dblPriceRange = l_orderMgrResVal.calcPriceRange(
            l_tradedProduct,
            l_dblBasePrice,
            WEB3MaxMinFlagDef.MINIMUM);
        
        // 3)　@値幅下限値取得
        double l_dblStopLowPrice = Math.floor(l_dblBasePrice - l_dblPriceRange);
        if (l_dblStopLowPrice <= 0.0D)
        {
            l_dblStopLowPrice = 1.0D;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblStopLowPrice;
    }

    /**
     * （is権利落ち日）。<BR>
     * <BR>
     * 発注日が権利落ち日であるかどうかの判定を行う。<BR>
     * 発注日＝権利落ち日の場合はtrueを、<BR>
     * 上記以外の場合はfalseを返す。<BR>
     * （* 株式発注審査個別チェック.is権利落ち日( )に委譲する。）
     * @@param String l_strOrderBizDate （発注日）<BR>
     * 　@　@　@発注日。
     * @@param Timestamp l_tsYearlyBooksClosingDate （権利確定日）<BR>
     * 　@　@　@権利確定日を指定する。通常は、【株式銘柄テーブル】決算日が指定される。
     * @@throws WEB3BaseException
     * @@return boolean
     */
    public boolean isDevidendRightDate(
    Timestamp l_tsOrderBizDate,
        Timestamp l_tsYearlyBooksClosingDate)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査個別チェック.is権利落ち日()に委譲する 
        return l_orderMgrResVal.isDevidendRightDate(
            l_tsOrderBizDate,
            l_tsYearlyBooksClosingDate);
    }

    /**
     * （validate取引銘柄）。<BR>
     * <BR>
     * 取扱可能チェックを実施する。<BR>
     * チェック結果がOKの場合は、取引銘柄オブジェクトを返却する。<BR>
     * （* 株式発注審査チェック.validate取引銘柄(株式銘柄, 市場)に委譲する。）
     * @@param EqTypeProduct l_eqTypeProduct （株式銘柄）<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@param Market l_market （市場）<BR>
     * 　@　@　@市場オブジェクト。
     * @@return TradedProduct
     * @@throws WEB3BaseException
     */
    public TradedProduct validateTradedProduct(
        EqTypeProduct l_eqTypeProduct,
        Market l_market)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.validate取引銘柄(株式銘柄, 市場)に委譲する 
        return l_orderMgrResVal.validateTradedProduct(
            l_eqTypeProduct,
            l_market);
    }

    /**
     * （get立会外分売注文単位一覧）。<BR>
     * <BR>
     * 指定条件に合致する、立会外分売の注文単位オブジェクトの一覧を取得する。<BR>
     * <BR>
     * this.get注文単位一覧( )にdelegateする。（戻り値：　@ArrayList）<BR>
     * ----------------------------------------------------------<BR>
     * ＜this.get注文単位一覧( )：引数設定仕様＞<BR>
     * <BR>
     * 補助口座：　@引数の補助口座<BR>
     * 銘柄タイプ：　@"株式"<BR>
     * 検索条件文字列：<BR>
     * 　@"銘柄ID = ? and 市場ID = ? and 取引コード（SONAR） = "立会外分売" and 発注日 = ? and 注文有効状態 = "オープン""<BR>
     * 　@※取引コード（SONAR）、注文有効状態には、意味に該当するコード値をセットする。<BR>
     * 検索条件データコンテナ：　@以下の値をString[]にしてセット。<BR>
     * 　@銘柄ID：　@引数の銘柄ID<BR>
     * 　@市場ID：　@引数の市場ID<BR>
     * 　@発注日：　@取引時間管理.get発注日(void)<BR>
     * ソート条件：　@null（指定なし）<BR>
     * ----------------------------------------------------------
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座。
     * @@param l_lngProductId （銘柄ID）<BR>
     * 　@　@　@銘柄ID。
     * @@param l_lngMarketId （市場ID）<BR>
     * 　@　@　@市場ID。
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
     * （validate立会外分売受付可能）。<BR>
     * <BR>
     * 指定された立会外分売銘柄が注文可能かチェックする。<BR>
     * （* 株式発注審査チェック.validate立会外分売受付可能( )に委譲する。）
     * @@param l_lngProductId （銘柄ID）<BR>
     * 　@　@　@銘柄ID。
     * @@param l_lngMarketId （市場ID）<BR>
     * 　@　@　@市場ID。
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座。
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public OffFloorOrderProductParams validateOffFloorOrderPossible(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.validate立会外分売受付可能()に委譲する 
        return l_orderMgrResVal.validateOffFloorOrderPossible(
            l_lngProductId,
            l_lngMarketId,
            l_subAccount);
    }

    /**
     * （validate立会外分売複数注文）。<BR>
     * <BR>
     * 指定された立会外分売銘柄に対し、既に注文が登録済かどうかをチェックする。<BR>
     * （* 株式発注審査チェック.validate立会外分売複数注文( )に委譲する。）
     * @@param l_lngProductId （銘柄ID）<BR>
     * 　@　@　@銘柄ID。
     * @@param l_lngMarketId （市場ID）<BR>
     * 　@　@　@市場ID。
     * @@param l_subAccount （補助口座）<BR>
     * 　@　@　@補助口座。
     * @@throws WEB3BaseException
     * @@return OffFloorOrderProductParams
     */
    public void validateOffFloorDuplicateOrder(
        long l_lngProductId,
        long l_lngMarketId,
        SubAccount l_subAccount)
        throws WEB3BaseException
    {
        // 株式発注審査個別チェックオブジェクトの生成
        WEB3EquityTypeOrderManagerReusableValidations l_orderMgrResVal =
            (WEB3EquityTypeOrderManagerReusableValidations) 
            WEB3EquityTypeOrderManagerReusableValidations.getInstance();
            
        // 株式発注審査チェック.validate立会外分売複数注文()に委譲する 
        l_orderMgrResVal.validateOffFloorDuplicateOrder(
            l_lngProductId,
            l_lngMarketId,
            l_subAccount);
    }
    
    /**
     * （validate立会外分売銘柄）。<BR>
     * <BR>
     * 立会外分売の市場チェック、銘柄チェック、申込株数上限チェックを行い、<BR>
     * 取引銘柄オブジェクトを返却する。<BR>
     * 　@－取扱可能市場チェック<BR>
     * 　@－銘柄、取扱市場チェック<BR>
     * 　@－取引可能銘柄チェック<BR>
     * 　@－売買単位チェック<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（立会外分売注文）validate立会外分売銘柄」参照。
     * @@param l_strInstitutionCode （証券会社コード）<BR>
     * 　@　@　@証券会社コード。
     * @@param l_strBranchCode （部店コード）<BR>
     * 　@　@　@部店コード。<BR>
     * 　@　@　@（全部店対象の場合はnullをセット）
     * @@param l_strProductCode （銘柄コード）<BR>
     * 　@　@　@銘柄コード。
     * @@param l_strMarketCode （市場コード）<BR>
     * 　@　@　@市場コード。
     * @@param l_strMaxApplyQuantity （申込株数上限）<BR>
     * 　@　@　@申込株数上限。
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
        
        //1.1. is取扱可能市場()
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
        
        //1.3. validate銘柄コード()
        WEB3EquityProduct l_product = 
            l_orderMgrResVal.validateProductCode(l_strProductCode, l_strInstitutionCode);
        
        //1.4. get市場()
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
        
        //1.5. validate取引銘柄()
        l_tradedProduct =
            (WEB3EquityTradedProduct)l_orderMgrResVal.validateTradedProduct(l_product, l_market);
        
        //1.6. 申込株数上限が指定されている場合（＝申込株数上限≠null）のみ実行
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
     * （get預り金不足情報（買付））<BR>
     * <BR>
     * 引数の取引余力結果オブジェクトから、 <BR>
     * 買付／現引時の預り金不足情報の文字列を編集し返す。 <BR>
     * <BR>
     * １）　@引数の取引余力結果.is判定フラグ( )==true（余力チェック結果OK）の場合は <BR>
     * 　@　@　@例外をthrowする。 <BR>
     * <BR>
     * ２）　@引数の取引余力結果.is判定フラグ( )==false（余力チェック結果NG）の場合は、 <BR>
     * 　@　@　@以下のフォーマットで預り金不足情報の文字列を編集し、 <BR>
     * 　@　@　@作成した文字列をreturnする。 <BR>
     * <BR>
     * 　@　@　@フォーマット：　@"%1,%2"（カンマ区切り） <BR>
     * 　@　@　@　@　@%1：　@引数の取引余力結果.取引余力エラー情報.差金決済買付可能額 <BR>
     * 　@　@　@　@　@%2：　@引数の取引余力結果.取引余力エラー情報.預り金不足額<BR>
     * <BR>
     * @@param l_tpTradingPowerResult （取引余力結果）<BR>
     * 　@　@　@取引余力結果オブジェクト。<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     */ 
    public String getLackAccountBalanceInfoBuy(WEB3TPTradingPowerResult l_tpTradingPowerResult)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountBalanceShortageInfoBuy(WEB3TPTradingPowerResult)";
        log.entering(STR_METHOD_NAME);
        
        // １）判定フラグチェック
        if (l_tpTradingPowerResult.isResultFlg() == true)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "預り金不足は発生していません。");
        }
        
        // ２）預り金不足情報を編集
        String l_strLackAccountBalanceInfo = 
		WEB3StringTypeUtility.formatNumber(l_tpTradingPowerResult.getTpErrorInfo().buyOrderPossibleAmount) +
            "," +
		WEB3StringTypeUtility.formatNumber(l_tpTradingPowerResult.getTpErrorInfo().lackAccountBalance);
        
        log.exiting(STR_METHOD_NAME);
        return l_strLackAccountBalanceInfo;
    }
    
    /**
     * （get預り金不足情報（売付））<BR>
     * <BR>
     * 引数の取引余力結果オブジェクト、注文株数から、 <BR>
     * 売付／現渡時の預り金不足情報の文字列を編集し返す。 <BR>
     * <BR>
     * １）　@引数の取引余力結果.is判定フラグ( )==true（余力チェック結果OK）の場合は <BR>
     * 　@　@　@例外をthrowする。 <BR>
     * <BR>
     * ２）　@引数の取引余力結果.is判定フラグ( )==false（余力チェック結果NG）の場合は、 <BR>
     * 　@　@　@以下のフォーマットで預り金不足情報の文字列を編集し、 <BR>
     * 　@　@　@作成した文字列をreturnする。 <BR>
     * <BR>
     * 　@　@　@フォーマット：　@"%1,%2,%3"（カンマ区切り） <BR>
     * 　@　@　@　@　@%1：　@引数の取引余力結果.取引余力エラー情報.預り金不足額 <BR>
     * 　@　@　@　@　@%2：　@引数の取引余力結果.取引余力エラー情報.差金決済売付可能数量 <BR>
     * 　@　@　@　@　@%3：　@引数の注文株数<BR>
     * <BR>
     * @@param l_tpTradingPowerResult （取引余力結果）<BR>
     * 　@　@　@取引余力結果オブジェクト。<BR>
     * @@param l_dblOrderQuantity （注文株数）<BR>
     * 　@　@　@注文株数。<BR>
     * @@return String<BR>
     * @@throws WEB3BaseException<BR>
     */
    public String getLackAccountBalanceInfoSell(WEB3TPTradingPowerResult l_tpTradingPowerResult, double l_dblOrderQuantity)
    throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountBalanceShortageInfoBuy(WEB3TPTradingPowerResult)";
        log.entering(STR_METHOD_NAME);
        
        // １）判定フラグチェック
        if (l_tpTradingPowerResult.isResultFlg() == true)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "預り金不足は発生していません。");
        }
        
        // ２）預り金不足情報を編集
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
     * （get未約定数量）<BR>
     * <BR>
     * 指定された注文単位オブジェクトの未約定数量を返す。 <BR>
     * <BR>
     * 注文単位.市場から確認済の数量 == null（注文受付前）の場合： <BR>
     * 　@　@（注文単位.注文数量 － 注文単位.約定数量）の結果を、未約定数量として返す。 <BR>
     * <BR>
     * 注文単位.市場から確認済の数量 != null（注文受付後）の場合： <BR>
     * 　@　@（注文単位.市場から確認済の数量 － 注文単位.約定数量）の結果を、未約定数量として返す。 <BR>
     * <BR>
     * ※注文単位.約定数量 == nullの場合、注文単位.約定数量 == 0として計算を行う。 <BR>
     * <BR>
     * @@param l_eqtypeOrderUnit （注文単位）<BR>
     * 　@　@　@注文単位オブジェクト。<BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getUnExecutedQuantity(EqTypeOrderUnit l_eqtypeOrderUnit)
    {
        final String STR_METHOD_NAME = "getUnExecutedQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
            
        // 注文単位.市場から確認済の数量 == nullの場合
        double l_dblUnExecutedQuantity = 0.0D;
        if (l_eqtypeOrderUnitRow.getConfirmedQuantity() == 0.0D)
        {
            l_dblUnExecutedQuantity = l_eqtypeOrderUnitRow.getQuantity() - l_eqtypeOrderUnitRow.getExecutedQuantity();
        }
        // 注文単位.市場から確認済の数量 != nullの場合
        else
        {
            l_dblUnExecutedQuantity = l_eqtypeOrderUnitRow.getConfirmedQuantity() - l_eqtypeOrderUnitRow.getExecutedQuantity();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblUnExecutedQuantity;
    }
    
	/**
	 * （get失効時受渡代金）<BR>
	 * <BR>
	 * 引数で指定された注文単位に対する株式顧客勘定明細.受渡代金の合計値を取得し、<BR>
	 * 失効時の注文単位テーブルの概算受渡代金への設定用金額を返す。<BR>
	 * <BR>
	 * １）　@引数の注文単位.注文カテゴリ == "新規建注文"の場合は、 注文単位.合計約定金額を返す。 <BR>
	 * <BR>
	 * 　@　@　@引数の注文単位.注文カテゴリ != "新規建注文"の場合は、以下の処理を行う。<BR>
	 * <BR>
	 * ２）　@拡張トランザクションマネージャ.get受渡代金合計(引数の注文単位)により、 <BR>
	 * 　@　@　@受渡代金合計を取得する。 <BR>
	 * <BR>
	 * ３）　@注文単位テーブルの概算受渡代金への設定用金額を算出する。 <BR>
	 * <BR>
	 * ３－１）　@引数の注文単位.注文種別が以下に該当する場合は、 <BR>
	 * 　@　@　@　@　@１）で取得した値の符号を反転した値を返す。（約定時に負の値を設定しているため） <BR>
	 * <BR>
	 * 　@　@　@　@　@　@現物買注文（EQUITY_BUY）<BR>
	 * <BR>
	 * ３－２）　@引数の注文単位.注文種別が３－１）以外の場合は、<BR>
	 * 　@　@　@　@　@２）で取得した値をそのまま返す。<BR>
	 * <BR>
	 * @@param l_eqtypeOrderUnit （注文単位）<BR>
	 * 　@　@　@注文単位オブジェクト。<BR>
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
     * （get失効時受渡代金(注文単位,株式顧客勘定明細)）<BR>
     * <BR>
     * 引数.株式顧客勘定明細.受渡代金の合計値を取得し、<BR>
     * 失効時の注文単位テーブルの概算受渡代金への設定用金額を返す。<BR>
     * <BR>
     * １）　@引数の注文単位.注文カテゴリ == "新規建注文"の場合は、 注文単位.合計約定金額を返す。 <BR>
     * <BR>
     * 　@　@　@引数の注文単位.注文カテゴリ != "新規建注文"の場合は、以下の処理を行う。<BR>
     * <BR>
     * ２）　@合計金額計算 <BR>
     * 　@　@　@拡張トランザクションマネージャ.get受渡代金計算()にて計算する。 <BR>
     * 　@　@　@引数設定 <BR>
     * 　@　@　@　@株式顧客勘定明細：　@引数.株式顧客勘定明細 <BR>
     * <BR>
     * ３）　@注文単位テーブルの概算受渡代金への設定用金額を算出する。 <BR>
     * <BR>
     * ３－１）　@引数の注文単位.注文種別が以下に該当する場合は、 <BR>
     * 　@　@　@　@　@１）で取得した値の符号を反転した値を返す。（約定時に負の値を設定しているため） <BR>
     * <BR>
     * 　@　@　@　@　@　@現物買注文（EQUITY_BUY）<BR>
     * <BR>
     * ３－２）　@引数の注文単位.注文種別が３－１）以外の場合は、<BR>
     * 　@　@　@　@　@２）で取得した値をそのまま返す。<BR>
     * <BR>
     * @@param l_eqtypeOrderUnit （注文単位）<BR>
     * 　@　@　@注文単位オブジェクト。<BR>
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
     * (update注文データ)<BR>
     * 指定された注文単位オブジェクトを使用し、QueryProcessorにより注文データ類の更新を行う。<BR>
     * －注文（ヘッダ）テーブル.更新日付のupdate<BR>
     * －注文単位テーブルを、引数の注文単位オブジェクトの内容でupdate<BR>
     * －注文履歴テーブルにレコードをinsert（引数.is履歴作成==trueの場合のみ）<BR>
     * <BR>
     * １）　@注文（ヘッダ）テーブルをupdateする。<BR>
     * <BR>
     * 　@　@注文ID==引数の注文単位.注文ID に該当する注文（ヘッダ）レコードをupdateする。<BR>
     * <BR>
     * 　@　@更新日付に、引数の注文単位オブジェクトの同項目の内容をセットしてupdateする。<BR>
     * <BR>
     * ２）　@注文単位テーブルをupdateする。<BR>
     * <BR>
     * 　@　@引数の注文単位オブジェクトの内容でupdateする。<BR>
     * <BR>
     * ３）　@引数のis履歴作成==trueの場合のみ、引数の注文単位オブジェクトを使用し<BR>
     * 　@　@注文履歴テーブルへ１レコードinsertする。<BR>
     * <BR>
     * 　@　@３－１）　@発注OK<BR>
     * 　@　@　@[引数の注文単位.注文状態 == "発注中（新規注文）" かつ<BR>
     * 　@　@　@　@引数の注文単位.リクエストタイプ == "時価サーバ"の場合]<BR>
     * 　@　@　@　@DB更新仕様<BR>
     * 　@　@　@　@「逆指値注文発注(OK)_株式注文履歴テーブル.xls」参照。<BR>
     * <BR>
     * @@param l_orderUnitRow - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@param l_blnIsCreateOrderAction - (is履歴作成)<BR>
     * 注文履歴テーブルにデータを登録するかどうかのフラグ。<BR>
     * （true：登録する、false：登録しない）
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
     * (validate取引銘柄（信用）)<BR>
     * 取扱可能チェック、及び弁済区分別の売買停止チェック（信用）を実施する。<BR>
     * チェック結果がOKの場合は、取引銘柄オブジェクトを返却する。<BR>
     * （* 株式発注審査チェック.validate取引銘柄（信用）(同じシグネチャ…)に委譲する。）<BR>
     * @@param l_subAccount (補助口座)<BR>
     * 　@　@　@補助口座オブジェクト。
     * @@param l_product (株式銘柄)<BR>
     * 　@　@　@株式銘柄オブジェクト。
     * @@param l_market (市場)<BR>
     * 　@　@　@市場オブジェクト。
     * @@param l_branch (部店)<BR>
     * 　@　@　@部店オブジェクト。
     * @@param l_strRepaymentType (弁済区分)<BR>
     * 　@　@　@弁済区分。<BR>
     * 　@　@　@0：DEFAULT（指定なし）<BR>
     * 　@　@　@1：制度信用<BR>
     * 　@　@　@2：一般信用
     * @@param l_orderCateg (注文カテゴリ)<BR>
     * 　@　@　@注文カテゴリ。（xTradeのOrderCategEnumにて定義）
     * @@param l_isShort (is売建)<BR>
     * 　@　@　@売建／買建のフラグ。<BR>
     * 　@　@　@建株＝売建の場合true、買建の場合falseを指定する。
     * @@param l_isTradeStopCheck (is売買停止チェック)<BR>
     * 　@　@　@売買停止チェック実施有無フラグ。<BR>
     * 　@　@　@（true：チェックする、false：チェックしない）
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
     * (validate現物株式注文)<BR>
     * 注文入力内容のチェックを実施する。<BR>
     * （validateNewCashBasedOrder）<BR>
     * <BR>
     * this.validate現物株式注文(補助口座, 株式注文内容, is連続反対売買)へdelegateする。<BR>
     * ※is連続反対売買には、false（＝反対売買ではない）を指定する。<BR>
     * @@param l_subAccount (補助口座)
     * @@param l_eqNewCashBasedOrderSpec (株式注文内容)
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
     * (validate返済注文)<BR>
     * 定返済注文の発注審査を行う。<BR>
     * （validateSettleContractOrderのオーバーライド）<BR>
     * <BR>
     * this.validate返済注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate返済注文()に指定する引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@信用返済注文内容：　@パラメータ.信用返済注文内容<BR>
     * 　@建株：　@null<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_settleContractOrderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。
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
     * (calc概算決済損益代金)<BR>
     * 概算決済損益代金を算出して返却する。<BR>
     * <BR>
     * this.calc概算決済損益代金()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [calc概算決済損益代金()にセットするパラメータ]<BR>
     * 　@手数料：　@パラメータの同項目<BR>
     * 　@指値：　@パラメータの同項目<BR>
     * 　@補助口座：　@パラメータの同項目<BR>
     * 　@取引銘柄：　@パラメータの同項目<BR>
     * 　@決済建株エントリ： 　@パラメータの同項目<BR>
     * 　@数量：　@パラメータの同項目<BR>
     * 　@注文単位：　@パラメータの同項目<BR>
     * 　@今回約定数量：　@パラメータの同項目<BR>
     * 　@今回約定単価：　@パラメータの同項目<BR>
     * 　@isSkip金額チェック：　@パラメータの同項目<BR>
     * 　@建株：　@null<BR>
     * @@param l_genCommission - (手数料)<BR>
     * 手数料オブジェクト。
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値。<BR>
     * 成行の場合は0をセット。
     * @@param l_genSubAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_equityTradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。
     * @@param l_settleContractOrderEntrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。
     * @@param l_dblQuantity - (数量)<BR>
     * 数量。
     * @@param l_orderUnit - (注文単位)<BR>
     * 訂正元／約定対象／約定取消対象注文の注文単位オブジェクト<BR>
     * （新規の注文登録時はnullをセット）
     * @@param l_dblNowExecQuantity - (今回約定数量)<BR>
     * 今回約定数量<BR>
     * （約定／約定取消の場合に編集）
     * @@param l_dblNowExecPrice - (今回約定単価)<BR>
     * 今回約定単価<BR>
     * （約定／約定取消の場合に編集）
     * @@param l_isSkipAmountRange - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）場合はtrueを指定する。
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
     * (validate現引現渡注文)<BR>
     * 定現引現渡注文の発注審査を行う。<BR>
     * （validateSwapContractOrderのオーバーライド）<BR>
     * <BR>
     * this.validate現引現渡注文()に処理を委譲（delegate）する。<BR>
     * <BR>
     * [validate現引現渡注文()に指定する引数]<BR>
     * 　@補助口座：　@パラメータ.補助口座<BR>
     * 　@信用現引現渡注文内容：　@パラメータ.信用現引現渡注文内容<BR>
     * 　@建株：　@null<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_swapContractOrderSpec - (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。
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
     * (calc概算受渡代金（現引現渡）)<BR>
     * @@param l_settleContractOrderEntrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。
     * @@param l_dblQuantity - (数量)<BR>
     * 数量。
     * @@param l_eqtypeOrderUnit - (注文単位)<BR>
     * 訂正元／約定対象／約定取消対象注文の注文単位オブジェクト<BR>
     * （新規の注文登録時はnullをセット）
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
     * (is予約注文確認要)<BR>
     * 引数で指定された注文に予約注文が設定されている可能性があるか<BR>
     * （＝親注文の可能性があるか）を判定する。<BR>
     * <BR>
     * 予約注文が設定されている可能性がある場合trueを、<BR>
     * ない場合falseを、それぞれ返却する。<BR>
     * <BR>
     * １）　@予約注文確認要否の判定　@<BR>
     * 引数の注文単位.予約注文設定フラグ ≠ "1：設定の可能性あり"の場合は、<BR>
     * 予約注文の設定なしのため、falseを返却する。以外、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
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
     * (insert現物株式注文キュー)<BR>
     * 株式注文取引キューテーブルに現物株式注文の<BR>
     * データを登録する。<BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.getOrderUnits(注文ID)にて<BR>
     * 　@注文単位を取得する。<BR>
     * 　@※戻り値の0番目の要素を注文単位として使用する。<BR>
     * <BR>
     * ２）　@株式注文取引キューにデータを登録する。<BR>
     * 　@注文単位.getSide() == SideEnum.BUYの場合、<BR>
     * 　@　@DB更新仕様<BR>
     * 　@　@「現物株式買付_株式注文取引キューテーブル.xls」参照<BR>
     * 　@以外、<BR>
     * 　@　@DB更新仕様<BR>
     * 　@　@「現物株式売付_株式注文取引キューテーブル.xls」参照<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。
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
            //株式注文キューテーブルへデータ準備
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                new HostEqtypeOrderAllParams();

            //(1) setデータコード ： ”AI801”
            l_hostEqtypeOrderAllParams.setRequestCode(
                WEB3HostRequestCodeDef.EQUITY_ORDER);

            //部店を取得する
            Branch l_banch =
                l_accountManager.getBranch(
                    l_eqtypeOderUnitRow.getBranchId());
            //証券会社コードを取得する
            String l_strInstitutionCode =
                l_banch.getInstitution().getInstitutionCode();
                    
            //顧客ID
            l_hostEqtypeOrderAllParams.setAccountId(l_eqtypeOderUnitRow.getAccountId());

            //(2) set証券会社コード：株式注文単位.部店ＩＤに該当する証券会社コード
            l_hostEqtypeOrderAllParams.setInstitutionCode(
                l_strInstitutionCode);

            //部店コードを取得する
            String l_strBranchCode = l_banch.getBranchCode();

            //(3) set部店コード ： 株式注文単位.部店ＩＤに該当する部店コード
            l_hostEqtypeOrderAllParams.setBranchCode(l_strBranchCode);

            //顧客コードを取得する
            MainAccount l_mainAccount =
                l_accountManager.getMainAccount(l_eqtypeOderUnitRow.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            String l_strAccountCode = l_mainAccount.getAccountCode();

            //(4) set顧客コード ： 株式注文単位.口座ＩＤに該当する口座コード
            l_hostEqtypeOrderAllParams.setAccountCode(l_strAccountCode);

            //(5) set扱者コード（SONAR） ： 株式注文単位.扱者コード（SONAR）
            l_hostEqtypeOrderAllParams.setSonarTraderCode(
                l_eqtypeOderUnitRow.getSonarTraderCode());

            //(6) set識別コード ： 株式注文単位.識別コード
            l_hostEqtypeOrderAllParams.setOrderRequestNumber(
                l_eqtypeOderUnitRow.getOrderRequestNumber());
                    
            // set注文履歴番号 ： 株式注文単位.注文履歴最終通番
            l_hostEqtypeOrderAllParams.setOrderActionSerialNo(l_eqtypeOderUnitRow.getLastOrderActionSerialNo());

            //(7) set銘柄コード ： 株式注文単位.銘柄ＩＤに該当する銘柄コード
            EqTypeProduct l_product = (EqTypeProduct)l_orderUnit.getProduct();
            String l_strProductCode = l_product.getProductCode();
            l_hostEqtypeOrderAllParams.setProductCode(l_strProductCode);

            //買付/売付数量をセット
            double l_dblOrderQuantity = l_orderUnit.getQuantity();
            if(SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                //買付の場合
                log.debug(" ===>買付の場合");

                //(8) 売付数量 ： 0
                l_hostEqtypeOrderAllParams.setSellOrderQuantity(0);
                //(9) 買付数量 ： 株式注文単位.注文数量
                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(
                    l_dblOrderQuantity);
                //(20) 譲渡益税区分 ： 0：なし
                l_hostEqtypeOrderAllParams.setCapitalGainTaxType(
                    WEB3CapitalGainTaxTypeDef.NOTHING);
            }
            else if (
                l_eqtypeOderUnitRow.getOrderType()
                    == OrderTypeEnum.EQUITY_SELL)
            {
                //売付の場合
                log.debug(" ===>売付の場合");

                //(8) 売付数量 ： 株式注文単位.注文数量
                l_hostEqtypeOrderAllParams.setSellOrderQuantity(
                    l_dblOrderQuantity);
                //(9) 買付数量 ： 0
                l_hostEqtypeOrderAllParams.setBuyOrderQuantity(0);
                        
				//(20) 譲渡益税区分 ：
				// 個人客（顧客.口座タイプ==("個人アカウント", "共用アカウント")）でかつ、
				//　@　@居住者、特別非居住者の場合：　@1：申告
				//　@　@非居住者の場合：　@　@　@　@　@　@　@0：なし
				// 法@人客（顧客.口座タイプ=="法@人アカウント"）の場合：　@0：なし
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

            //指値を取得
            double l_dblLimitPrice = l_orderUnit.getLimitPrice();

            //(10) set指値 ： 株式注文単位.指値
            l_hostEqtypeOrderAllParams.setLimitPrice(l_dblLimitPrice);

            //執行条件を取得
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

            //(11) set執行条件
            l_hostEqtypeOrderAllParams.setExecutionCondition(
                l_strExecutionConditionSonar);

            //値段条件を取得
            String l_strPriceConditionTypeSonar =
                this.getPriceConditionTypeSonar(
                    l_eqtypeOderUnitRow.getPriceConditionType());

            //(12) set値段条件
            l_hostEqtypeOrderAllParams.setPriceConditionType(
                l_strPriceConditionTypeSonar);

            //(13) set取引コード（SONAR） ： 株式注文単位.取引コード（SONAR）
            l_hostEqtypeOrderAllParams.setSonarTradedCode(
                l_eqtypeOderUnitRow.getSonarTradedCode());

            //(14) set弁済区分：０：DEFAULT（現物）
            l_hostEqtypeOrderAllParams.setSonarRepaymentType(
                WEB3GentradeRepaymentDivDef.DEFAULT);

            //(15) set市場コード（SONAR） ： 株式注文単位.市場コード（SONAR）
            l_hostEqtypeOrderAllParams.setSonarMarketCode(
                l_eqtypeOderUnitRow.getSonarMarketCode());

            //(16) set伝票№ ： 株式注文単位.伝票No.
            l_hostEqtypeOrderAllParams.setTicketNumber(
                l_eqtypeOderUnitRow.getVoucherNo());

            //(17) set受注日時 ： 注文単位.受注日時
            Timestamp l_tsReceivedDateTime = l_eqtypeOderUnitRow.getReceivedDateTime();
            l_hostEqtypeOrderAllParams.setReceivedDateTime(
                l_tsReceivedDateTime);

            //(18) set受注日区分
            //(株式注文単位.発注日==受注日（受注日時の日付部分）の場合は　@0：当日。以外、1：前日。)
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

            //(19) set税区分（特定口座区分）
            if (TaxTypeEnum.NORMAL.equals(l_orderUnit.getTaxType()))
            {
                log.debug("税区分 ===>一般の場合は");
                //”一般”の場合は0
                l_hostEqtypeOrderAllParams.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else if (TaxTypeEnum.STOCK_OPTION.equals(l_orderUnit.getTaxType())
                && OrderTypeEnum.EQUITY_SELL.equals(l_eqtypeOderUnitRow.getOrderType()))
            {
                //”ストックオプション”の場合は「5：ストックオプション」
                log.debug("税区分 ===>ストックオプションの場合は");
                l_hostEqtypeOrderAllParams.setTaxType(WEB3TaxTypeDef.STOCK_OPTION);
            }
            else
            {
                log.debug("税区分 ===>特定、特定口座かつ源泉徴収の場合");
                //”特定”、”特定口座かつ源泉徴収”の場合1
                l_hostEqtypeOrderAllParams.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }

            //(21) set強制 ： 1：預りチェック要
            l_hostEqtypeOrderAllParams.setCheckType(
                WEB3CheckTypeDef.PRE_CHECK);

            //(22) set注文チャネル ： 株式注文単位.初回注文の注文チャネル
            l_hostEqtypeOrderAllParams.setOrderChanel(
                l_eqtypeOderUnitRow.getOrderChanel());

            //(23) setファ@クター ： ブランク：現物
            String l_strFactor = " ";
            l_hostEqtypeOrderAllParams.setFactor(l_strFactor);

            //(24) set手数料№ ： 株式注文単位.手数料No.
            l_hostEqtypeOrderAllParams.setCommisionNumber(
                l_eqtypeOderUnitRow.getCommTblNo());

            //(25) set手数料№枝番 ： 株式注文単位.手数料No.枝番
            l_hostEqtypeOrderAllParams.setCommisionBranchNumber(
                l_eqtypeOderUnitRow.getCommTblSubNo());

            //(26) 手数料商品コード ： 株式注文単位.手数料商品コード
            l_hostEqtypeOrderAllParams.setCommisionProductCode(
                l_eqtypeOderUnitRow.getCommProductCode());

            //(27) set空売フラグ ： ブランク：対象外
            l_hostEqtypeOrderAllParams.setShortSellOrderFlag(
                WEB3ShortSellOrderFlagDef.EXCEPT_OBJECT);

            //(28) 処理区分 ： 0：未処理
            l_hostEqtypeOrderAllParams.setStatus(
                WEB3FrontOrderStatusDef.NOT_DEAL);

            // 発注経路区分
            String l_strSubmitOrderRouteDiv =
                l_eqtypeOderUnitRow.getSubmitOrderRouteDiv();
            l_hostEqtypeOrderAllParams.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
                    
            // 取消区分
            l_hostEqtypeOrderAllParams.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
                    
            // フロント発注取引所区分コード
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_eqtypeOderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_hostEqtypeOrderAllParams.setFrontOrderExchangeCode(
                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
                    
            // フロント発注システム区分
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
                    
            // フロント発注取引区分コード
            l_hostEqtypeOrderAllParams.setFrontOrderTradeCode(
                WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
                    
            // 自己委託区分
            l_hostEqtypeOrderAllParams.setTradeauditCode(
                WEB3TradeauditCodeDef.COMMISSION);
                    
            // 社内処理項目
            l_hostEqtypeOrderAllParams.setCorpCode(
                l_frontOrderService.getCorpCode(l_eqtypeOrderUnit));
                    
            // 全訂正処理区分
            l_hostEqtypeOrderAllParams.setAllOrderChangeDiv(
                WEB3AllOrderChangeDivDef.EXCEPT_ALL_ORDER_CHANGE);
                    
            //データ挿入する
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
     * (insert信用新規建注文キュー)<BR>
     * 株式注文取引キューテーブルに現物株式注文の<BR>
     * データを登録する。<BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.getOrderUnits(注文ID)にて<BR>
     * 　@注文単位を取得する。<BR>
     * 　@※戻り値の0番目の要素を注文単位として使用する。<BR>
     * <BR>
     * ２）　@株式注文取引キューにデータを登録する。<BR>
     * 　@DB更新仕様<BR>
     * 　@「信用新規建_株式注文取引キューテーブル.xls」参照<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。
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
            
            //14.(*1) キューテーブルに行を挿入する。
            //挿入する行の内容は、DB更新仕様
            //「信用新規建_株式注文キューテーブル.xls」参照。
            HostEqtypeOrderAllParams l_params = new HostEqtypeOrderAllParams();
            //1   データコード  ”AI801”
            l_params.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER);
            //口座ID
            l_params.setAccountId(l_orderUnitRow.getAccountId());
            //2   証券会社コード       株式注文単位.部店ＩＤに該当する証券会社コード

            l_params.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //3   部店コード      "株式注文単位.部店ＩＤに該当する部店.部店コード（* 顧客の取引店）"  
            l_params.setBranchCode(l_branch.getBranchCode());
            //4   顧客コード   株式注文単位.口座ＩＤに該当する顧客.口座コード
            l_params.setAccountCode(
                l_accountManager
                    .getMainAccount(l_orderUnitRow.getAccountId())
                    .getAccountCode());
            //5   扱者コード（SONAR）   株式注文単位.扱者コード（SONAR）
            l_params.setSonarTraderCode(l_orderUnitRow.getSonarTraderCode());
            //6   識別コード      株式注文単位.識別コード
            l_params.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //注文履歴番号 ： 株式注文単位.注文履歴最終通番
            l_params.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
            //7   銘柄コード     株式注文単位.銘柄ＩＤに該当する株式銘柄.銘柄コード
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            l_params.setProductCode(l_product.getProductCode());
            //8   売付数量     株式注文単位.注文種別＝”新規売建注文”の場合、株式注文単位.注文数量を設定。以外、0を設定                      
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

            // 9   買付数量  株式注文単位.注文種別＝”新規買建注文”の場合、株式注文単位.注文数量を設定。以外、0を設定 
            if (OrderTypeEnum.MARGIN_LONG
                .equals(l_orderUnitRow.getOrderType()))
            {
                l_params.setBuyOrderQuantity(l_orderUnitRow.getQuantity());
            }
            else
            {
                l_params.setBuyOrderQuantity(0);
            }
            //10  指値     株式注文単位.指値。（成行の場合0）
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
            //11  執行条件（SONAR）
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
            //12  値段条件（SONAR）
            l_params.setPriceConditionType(
                this.getPriceConditionTypeSonar(
                    l_orderUnitRow.getPriceConditionType()));
            //13  取引コード（SONAR）    
            l_params.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //14  弁済区分（SONAR）   
            l_params.setSonarRepaymentType(
                l_orderUnitRow.getSonarRepaymentType());
            //15  市場コード（SONAR）   
            l_params.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
            //16  伝票№   
            l_params.setTicketNumber(l_orderUnitRow.getVoucherNo());
            //17  受注日時       
            Timestamp l_tsCurTime =
                l_orderUnitRow.getReceivedDateTime();
            l_params.setReceivedDateTime(l_tsCurTime);
            //18  受注日区分        "株式注文単位.発注日==（this.受注日時（＝現在日時）のYYYYMMDD部分）の場合は　@0：当日。
            //以外、1：前日。"      
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

            //19  税区分（特定口座区分）     
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_params.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }
            //20  譲渡益税区分 
            l_params.setCapitalGainTaxType(WEB3CapitalGainTaxTypeDef.NOTHING);
            //21  強制      
            l_params.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
            //22  注文チャネル  
            l_params.setOrderChanel(l_orderUnitRow.getOrderChanel());
            //23  ファ@クター        
            l_params.setFactor(" ");
            //24  手数料№   
            l_params.setCommisionNumber(l_orderUnitRow.getCommTblNo());
            //25  手数料№枝番      
            l_params.setCommisionBranchNumber(l_orderUnitRow.getCommTblSubNo());
            //26  手数料商品コード 
            l_params.setCommisionProductCode(
                l_orderUnitRow.getCommProductCode());
            //27  空売フラグ
            l_params.setShortSellOrderFlag(
                l_orderUnitRow.getShortSellOrderFlag());
            //28  処理区分                                                          
            l_params.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            // 発注経路区分
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            l_params.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv);
            // 取消区分
            l_params.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
            // フロント発注取引所区分コード
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_params.setFrontOrderExchangeCode(
                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
            // フロント発注システム区分
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
            // フロント発注取引区分コード
            l_params.setFrontOrderTradeCode(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            // 自己委託区分
            l_params.setTradeauditCode(WEB3TradeauditCodeDef.COMMISSION);
            // 社内処理項目
            l_params.setCorpCode(
                l_frontOrderService.getCorpCode(l_orderUnit));
            // 全訂正処理区分
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
     * (insert信用返済注文キュー)<BR>
     * 株式注文取引キューテーブルに現物株式注文の<BR>
     * データを登録する。<BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.getOrderUnits(注文ID)にて<BR>
     * 　@注文単位を取得する。<BR>
     * 　@※戻り値の0番目の要素を注文単位として使用する。<BR>
     * <BR>
     * ２）　@株式注文取引キューにデータを登録する。<BR>
     * 　@DB更新仕様<BR>
     * 　@　@「信用返済_株式注文取引キューテーブル.xls」参照<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。
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
            
            //(*1) キューテーブルに行を挿入する。
            //挿入する行の内容は、DB更新仕様
            //「信用返済_株式注文キューテーブル.xls」参照。
            HostEqtypeOrderAllParams l_params = new HostEqtypeOrderAllParams();
            //1   データコード  
            l_params.setRequestCode(WEB3HostRequestCodeDef.EQUITY_ORDER);
            //口座ID
            l_params.setAccountId(l_orderUnitRow.getAccountId());
            //2   証券会社コード    
            l_params.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //3   部店コード          
            l_params.setBranchCode(l_branch.getBranchCode());
            //4   顧客コード
            MainAccount l_mainAccount = l_mainAccount = l_accountManager
                .getMainAccount(l_orderUnitRow.getAccountId());
            MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
            l_params.setAccountCode(l_mainAccount.getAccountCode());
            //5   扱者コード（SONAR）
            l_params.setSonarTraderCode(l_orderUnitRow.getSonarTraderCode());

            //6   識別コード 
            l_params.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //注文履歴番号 ： 株式注文単位.注文履歴最終通番
            l_params.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
            //7   銘柄コード   
            WEB3EquityProduct l_product = (WEB3EquityProduct)l_orderUnit.getProduct();
            l_params.setProductCode(l_product.getProductCode());
            //8   売付数量                           
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

            // 9   買付数量         
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
            //10  指値        
            l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());
            //11  執行条件（SONAR）
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
            //12  値段条件（SONAR）
            l_params.setPriceConditionType(
                this.getPriceConditionTypeSonar(
                    l_orderUnitRow.getPriceConditionType()));
            //13  取引コード（SONAR）              
            l_params.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //14  弁済区分（SONAR）
            l_params.setSonarRepaymentType(
                l_orderUnitRow.getSonarRepaymentType());
            //15  市場コード（SONAR） 
            l_params.setSonarMarketCode(l_orderUnitRow.getSonarMarketCode());
            //16  伝票№      
            l_params.setTicketNumber(l_orderUnitRow.getVoucherNo());
            //17  受注日時      
            Timestamp l_tsCurTime =
                l_orderUnitRow.getReceivedDateTime();
            l_params.setReceivedDateTime(l_tsCurTime);
            //18  受注日区分       
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

            //19  税区分（特定口座区分）      
            if (TaxTypeEnum.NORMAL.equals(l_orderUnitRow.getTaxType()))
            {
                l_params.setTaxType(WEB3TaxTypeDef.NORMAL);
            }
            else
            {
                l_params.setTaxType(WEB3TaxTypeDef.SPECIAL);
            }
            
			//20 譲渡益税区分 ：
			// 個人客（顧客.口座タイプ==("個人アカウント", "共用アカウント")）でかつ、
			//　@　@居住者、特別非居住者の場合：　@1：申告
			//　@　@非居住者の場合：　@　@　@　@　@　@　@0：なし
			// 法@人客（顧客.口座タイプ=="法@人アカウント"）の場合：　@0：なし
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
            
            //21  強制              
            l_params.setCheckType(WEB3CheckTypeDef.PRE_CHECK);
            //22  注文チャネル  
            l_params.setOrderChanel(l_orderUnitRow.getOrderChanel());
            //23  ファ@クター     
            l_params.setFactor(" ");
            //24  手数料№      
            l_params.setCommisionNumber(l_orderUnitRow.getCommTblNo());
            //25  手数料№枝番          
            l_params.setCommisionBranchNumber(l_orderUnitRow.getCommTblSubNo());
            //26  手数料商品コード      
            l_params.setCommisionProductCode(
                l_orderUnitRow.getCommProductCode());
            //27  空売フラグ     
            l_params.setShortSellOrderFlag(
                l_orderUnitRow.getShortSellOrderFlag());
            //28  処理区分                                                                                  
            l_params.setStatus(WEB3FrontOrderStatusDef.NOT_DEAL);
            // 発注経路区分
            String l_strSubmitOrderRouteDiv =
                l_orderUnitRow.getSubmitOrderRouteDiv();
            l_params.setSubmitOrderRouteDiv(l_strSubmitOrderRouteDiv); 
            // 取消区分
            l_params.setCancelDiv(WEB3CancelDivDef.EXCEPT_CANCEL);
            // フロント発注取引所区分コード
            WEB3EquityFrontOrderService l_frontOrderService =
                (WEB3EquityFrontOrderService)Services.getService(WEB3EquityFrontOrderService.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            Market l_market =
                l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();
            l_params.setFrontOrderExchangeCode(
                l_frontOrderService.getFrontOrderExchangeCode(l_strMarketCode));
            // フロント発注システム区分
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
            // フロント発注取引区分コード
            l_params.setFrontOrderTradeCode(WEB3FrontOrderTradeCodeDef.STOCK_BOND_TRADE);
            // 自己委託区分
            l_params.setTradeauditCode(WEB3TradeauditCodeDef.COMMISSION);
            // 社内処理項目
            l_params.setCorpCode(
                l_frontOrderService.getCorpCode(l_orderUnit));
            // 全訂正処理区分
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
     * (notifyルールエンジンサーバ)<BR>
     * （notifyRLS）<BR>
     * 条件付注文の執行、登録、訂正、取消を<BR>
     * ルールエンジンサーバに通知する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文）notifyルールエンジンサーバ」参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
     * @@param l_context - (処理)<BR>
     * 処理。<BR>
     * （OrderManagerPersistenceContextにて定数定義）
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
        
        //1.1.(*)約定処理（パラメータ.処理 == "FILL_ORDER"）の場合
        if (OrderManagerPersistenceContext.FILL_ORDER.equals(l_context))
        {
            //1.1.1.notify親注文全部約定(EqTypeOrderUnit)
            this.notifyParentOrderFullyExecuted(l_orderUnit);
        }
        //1.2.(*)約定処理以外の場合
        else
        {
            //1.2.1.notify連続注文(EqTypeOrderUnit)
            this.notifySuccOrder(l_orderUnit);
        }

        //パラメータ．注文単位.getDataSourceObject()が株式予約注文単位Rowの場合
        if (l_orderUnit.getDataSourceObject() instanceof RsvEqOrderUnitRow)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //1.3.get発注条件(String, String)
        //[引数] 
        // 発注条件：　@注文単位.発注条件 
        // 元発注条件：　@注文単位.元発注条件 
        EqtypeOrderUnitRow l_eqOrderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        String l_strOrderConditionType = 
            WEB3EquityDataAdapter.getOrderConditionType(
                l_eqOrderUnitRow.getOrderConditionType(),
                l_eqOrderUnitRow.getOrgOrderConditionType());
        
        //1.4.逆指値注文（get発注条件 == "逆指値"）の場合
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //1.5.notify逆指値注文(EqTypeOrderUnit, OrderManagerPersistenceContext)
            this.notifyStopOrder(l_orderUnit, l_context);
        }
        
        //1.6.W指値注文（get発注条件 == "W指値"）の場合
        if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            //1.6.1notifyW指値注文(EqTypeOrderUnit, OrderManagerPersistenceContext)
            this.notifyWLimitOrder(l_orderUnit, l_context);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (notify親注文全部約定)<BR>
     * 引数で指定された注文が全部約定しており、かつ、<BR>
     * 有効な予約注文が設定されている親注文である場合に、<BR>
     * ルールエンジンサーバに親注文の全部約定を通知する。<BR>
     * ※全部約定かどうかは呼び出し元で判別すること。<BR>
     * <BR>
     * １）　@予約注文有無の判定　@<BR>
     * 引数で指定された注文に有効な予約注文が設定されているか<BR>
     * （＝親注文であるか）を判定する。<BR>
     * <BR>
     * 　@１－１）　@予約注文確認要否の判定<BR>
     * 　@this.is予約注文確認要()をコールする。<BR>
     * <BR>
     * 　@[引数の設定]<BR>
     * 　@　@注文単位：　@引数.注文単位<BR>
     * <BR>
     * 　@戻り値がfalseの場合、予約注文が設定されていないため処理を終了する（return)<BR>
     * <BR>
     * ２）　@全部約定かどうかの判定<BR>
     * 　@引数の注文単位.isFullyExecuted()をコールする。<BR>
     * <BR>
     * 　@戻り値がfalseの場合、全部約定でないため処理を終了する（return)<BR>
     * <BR>
     * ３）　@有効予約注文の確認<BR>
     * 　@有効な予約注文の一覧を取得する。<BR>
     * <BR>
     * 　@株式予約注文更新サービスImpl.get有効予約注文単位一覧()をコールする。<BR>
     * <BR>
     * 　@[引数の設定]<BR>
     * 　@　@親注文の注文ID：　@引数.注文単位.注文ID<BR>
     * <BR>
     * 　@戻り値がnullの場合、有効な予約注文が存在しないため処理を終了する(return)<BR>
     * <BR>
     * ４）　@全部約定の通知<BR>
     * ルールエンジンサーバに親注文の全部約定を通知する。<BR>
     * <BR>
     * WEB3RlsRequestSenderService.sendConOrderExecuteMessage()をコールする。<BR>
     * <BR>
     * [引数の設定]<BR>
     * 補助口座：　@引数.注文単位.口座ID、補助口座IDに該当する補助口座<BR>
     * 親注文の注文ID：　@引数.注文単位.注文ID<BR>
     * 銘柄タイプ：　@"株式"<BR>
     * 親注文の識別コード：　@引数.注文単位.識別コード<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
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
     * (notify逆指値注文)<BR>
     * 逆指値注文の登録、訂正、取消を <BR>
     * ルールエンジンサーバに通知する。 <BR>
     * <BR>
     * １）　@株式注文単位かどうかの判定　@ <BR>
     * 　@引数の注文単位.getDataSourceObject()をコールする。　@ <BR>
     * <BR>
     * 　@メソッドの戻り値の型が、 <BR>
     * 　@株式注文単位Rowでない場合、 <BR>
     * 　@処理対象外である為、処理を終了する。（return） <BR>
     * 　@※予約注文単位に対して、逆指値は設定不可。 <BR>
     * <BR>
     * ２）ルールエンジンサーバへの通知要否チェック <BR>
     * <BR>
     * 　@２－１）　@未発注の逆指値注文の判定 <BR>
     * 　@　@　@１）の戻り値.発注条件 != "逆指値" または <BR>
     * 　@　@　@１）の戻り値.リクエストタイプ != "DEFAULT"の場合、 <BR>
     * 　@　@　@処理対象外である為、処理を終了する。（return） <BR>
     * <BR>
     * 　@２－２）　@未発注の発注遅延注文の判定 <BR>
     * 　@　@　@拡張株式注文マネージャ.is未発注遅延注文() == trueの場合、 <BR>
     * 　@　@　@処理対象外である為、処理を終了する。（return） <BR>
     * <BR>
     * 　@　@　@[is未発注遅延注文()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@１）の戻り値 <BR>
     * <BR>
     * ３）　@補助口座を取得する。 <BR>
     * 　@拡張アカウントマネージャ.getSubAccount()をコールする。 <BR>
     * <BR>
     * 　@[getSubAccount()に指定する引数] <BR>
     * 　@　@arg0：　@１）の戻り値.口座ID <BR>
     * 　@　@arg1：　@１）の戻り値.補助口座ID <BR>
     * <BR>
     * ４）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService） <BR>
     * 　@を取得する。 <BR>
     * <BR>
     * ５）　@ルールエンジンサーバに通知を行う。 <BR>
     * 　@引数の注文単位.注文状態によって下記分岐を行う。 <BR>
     * <BR>
     * 　@５－１）　@取消完了（注文有効状態="クローズ"）<BR>
     * 　@　@　@または失効、注文繰越スキップ（処理 == "ORDER_INVALIDATED_BY_MKT"）の場合<BR>
     * 　@　@　@取得したサービス.sendCancelConOrderMessage()メソッドを <BR>
     * 　@　@　@コールする。 <BR>
     * <BR>
     * 　@　@　@[sendCancelConOrderMessage()に指定する引数] <BR>
     * 　@　@　@　@補助口座：　@取得した補助口座 <BR>
     * 　@　@　@　@条件付注文タイプ：　@"逆指値" <BR>
     * 　@　@　@　@銘柄タイプ：　@"株式" <BR>
     * 　@　@　@　@注文ID：　@１）の戻り値.注文ID <BR>
     * <BR>
     * 　@５－２）　@訂正完了（="発注済（変更注文）"）の場合 <BR>
     * 　@　@　@取得したサービス.sendModifyConOrderMessage()メソッドを <BR>
     * 　@　@　@コールする。 <BR>
     * <BR>
     * 　@　@　@[sendModifyConOrderMessage()に指定する引数] <BR>
     * 　@　@　@　@補助口座：　@取得した補助口座 <BR>
     * 　@　@　@　@条件付注文タイプ：　@"逆指値" <BR>
     * 　@　@　@　@親注文の銘柄タイプ：　@"株式" <BR>
     * 　@　@　@　@親注文の注文ID：　@１）の戻り値.注文ID <BR>
     * 　@　@　@　@子注文の銘柄タイプ一覧：　@null <BR>
     * 　@　@　@　@子注文の注文ID一覧：　@null <BR>
     * <BR>
     * 　@５－３）　@新規登録（="受付済（新規注文）"）の場合] <BR>
     * 　@　@　@取得したサービス.sendRegisterConOrderMessage()メソッドを <BR>
     * 　@　@　@コールする。 <BR>
     * <BR>
     * 　@　@　@[sendRegisterConOrderMessage()に指定する引数] <BR>
     * 　@　@　@　@補助口座：　@取得した補助口座 <BR>
     * 　@　@　@　@条件付注文タイプ：　@"逆指値" <BR>
     * 　@　@　@　@親注文の銘柄タイプ：　@"株式" <BR>
     * 　@　@　@　@親注文の注文ID：　@１）の戻り値.注文ID <BR>
     * 　@　@　@　@子注文の銘柄タイプ一覧：　@null <BR>
     * 　@　@　@　@子注文の注文ID一覧：　@null <BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@param l_context - (処理)<BR>
     * 処理。<BR>
     * （OrderManagerPersistenceContextにて定数定義）<BR>
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
        
        //   　@２－１）　@未発注の逆指値注文の判定 
        // 　@　@　@１）の戻り値.発注条件 != "逆指値" または 
        // 　@　@　@１）の戻り値.リクエストタイプ != "DEFAULT"の場合、 
        // 　@　@　@処理対象外である為、処理を終了する。（return） 
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_row;
        try
        {
            l_orderUnitRow = (EqtypeOrderUnitRow)getOrderUnit(l_orderUnitRow.getOrderUnitId()).getDataSourceObject();
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
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
        
        //　@２－２）　@未発注の発注遅延注文の判定 
        //　@　@　@拡張株式注文マネージャ.is未発注遅延注文() == trueの場合、 
        //　@　@　@処理対象外である為、処理を終了する。（return） 
        //
        //　@　@　@[is未発注遅延注文()に設定する引数] 
        //　@　@　@　@注文単位：　@１）の戻り値 
        if (this.isNotOrderedDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //３）　@補助口座を取得する。 
        //　@拡張アカウントマネージャ.getSubAccount()をコールする。 
        //　@[getSubAccount()に指定する引数] 
        //　@　@arg0：　@１）の戻り値.口座ID 
        //　@　@arg1：　@１）の戻り値.補助口座ID 
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
        
        // ４）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService） 
        //を取得する。 
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        
        //５）　@ルールエンジンサーバに通知を行う。 
        //　@引数の注文単位.注文状態によって下記分岐を行う。 
        //　@５－１）　@取消完了（注文有効状態="クローズ"）
        //     または失効、注文繰越スキップ（処理 == "ORDER_INVALIDATED_BY_MKT"）の場合
        //　@　@　@取得したサービス.sendCancelConOrderMessage()メソッドを 
        //　@　@　@コールする。 
        //　@　@　@[sendCancelConOrderMessage()に指定する引数] 
        //　@　@　@　@補助口座：　@取得した補助口座 
        //　@　@　@　@条件付注文タイプ：　@"逆指値" 
        //　@　@　@　@銘柄タイプ：　@"株式" 
        //　@　@　@　@注文ID：　@１）の戻り値.注文ID 
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
        //   　@５－２）　@訂正完了（="発注済（変更注文）"）の場合 
        // 　@　@　@取得したサービス.sendModifyConOrderMessage()メソッドを 
        // 　@　@　@コールする。 
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
        //   　@５－３）　@新規登録（="受付済（新規注文）"）の場合] 
        // 　@　@　@取得したサービス.sendRegisterConOrderMessage()メソッドを 
        // 　@　@　@コールする。 
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
     * (notify連続注文)<BR>
     * 連続注文の登録をルールエンジンサーバに通知する。<BR>
     * <BR>
     * １）　@予約注文単位かどうかの判定　@<BR>
     * 　@引数の注文単位.getDataSourceObject()をコールする。　@<BR>
     * <BR>
     * 　@メソッドの戻り値の型が、<BR>
     * 　@株式予約注文単位Rowでない場合、<BR>
     * 　@処理対象外である為、処理を終了する。（return）<BR>
     * <BR>
     * ２）　@補助口座を取得する。<BR>
     * 　@拡張アカウントマネージャ.getSubAccount()をコールする。<BR>
     * <BR>
     * 　@[getSubAccount()に指定する引数]<BR>
     * 　@　@arg0：　@１）の戻り値.口座ID<BR>
     * 　@　@arg1：　@１）の戻り値.補助口座ID<BR>
     * <BR>
     * ３）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService）<BR>
     * 　@を取得する。<BR>
     * <BR>
     * ４）ルールエンジンサーバに通知を行う。<BR>
     * 　@[新規登録（１）の戻り値.注文状態 == "受付済（新規注文）"）の場合]<BR>
     * 　@　@取得したサービス.sendRegisterConOrderMessage()メソッドを<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[sendRegisterConOrderMessage()に指定する引数]<BR>
     * 　@　@　@補助口座：　@取得した補助口座<BR>
     * 　@　@　@条件付注文タイプ：　@"連続注文"<BR>
     * 　@　@　@親注文の銘柄タイプ：　@"株式"<BR>
     * 　@　@　@親注文の注文ID：　@１）の戻り値.親注文の注文ID<BR>
     * 　@　@　@子注文の銘柄タイプ一覧：　@１）の戻り値.銘柄タイプのみを要素とする配列<BR>
     * 　@　@　@子注文の注文ID一覧：　@１）の戻り値.注文IDのみを要素とする配列<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。
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
     * (throw余力エラー詳細情報)<BR>
     * 取引余力エラー区分に応じて、埋め込みエラーメッセージの編集、<BR>
     * 及び該当するエラーコードを設定して例外をthrowをする。<BR>
     * ※余力チェックエラー時のみ使用する。<BR>
     * <BR>
     * シーケンス図「（注文）throw余力エラー詳細情報」参照。<BR>
     * @@param l_tpResult - (取引余力結果)<BR>
     * 取引余力結果オブジェクト。
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別。<BR>
     * （預り金不足時の売買判定等に使用する）
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
            //取引余力結果.取引余力エラー情報.取引余力エラー区分=="受入保証金占有率超過エラー"の場合
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
                //TODO: 現物買、現引の預り金不足の埋め込みエラーメッセージ設定は未対応。預り金不足額の画面表示追加時に修正する。
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType) ||
                OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
            {
                //TODO: 現物売、現渡の預り金不足の埋め込みエラーメッセージ設定は未対応。預り金不足額の画面表示追加時に修正する。
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01930,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }
    
    /**
     * (get注文エラー理由コード)<BR>
     * <BR>
     * 引数のエラーコードから、<BR>
     * 繰越失敗の注文／発注失敗の逆指値注文／発注失敗の予約注文 に設定するための<BR>
     * 注文エラー理由コードへの変換を行う。<BR>
     * （注文エラー理由コードのコード体系は、株式注文単位テーブルのDBレイアウトを参照）<BR>
     * <BR>
     * 変換仕様は以下の通り。<BR>
     * ---------------------------------------------------------------------<BR>
     * "正常"<BR>
     * 引数のエラーコード==null<BR>
     * <BR>
     * "値幅エラー"<BR>
     * （BUSINESS_ERROR_00031 or<BR>
     * 　@BUSINESS_ERROR_02298）<BR>
     * 
     * "預り金不足エラー"（※新規建以外）<BR>
     * （BUSINESS_ERROR_01306 or<BR>
     * 　@BUSINESS_ERROR_01929 or<BR>
     * 　@BUSINESS_ERROR_01930 or<BR>
     * 　@BUSINESS_ERROR_02299 or）<BR>
     * <BR>
     * "株式残高不足エラー"<BR>
     * BUSINESS_ERROR_00167<BR>
     * <BR>
     * "保証金不足エラー"（※新規建で二階建エラー以外）<BR>
     * （BUSINESS_ERROR_01935 or<BR>
     * 　@BUSINESS_ERROR_02300）<BR>
     * <BR>
     * "建株残高不足エラー"<BR>
     * （BUSINESS_ERROR_00808 or<BR>
     * 　@BUSINESS_ERROR_02339）<BR>
     * <BR>
     * "売買停止銘柄エラー"<BR>
     * （BUSINESS_ERROR_00014 or<BR>
     * 　@BUSINESS_ERROR_00015 or<BR>
     * 　@BUSINESS_ERROR_00692 or<BR>
     * 　@BUSINESS_ERROR_00693 or<BR>
     * 　@BUSINESS_ERROR_00694 or<BR>
     * 　@BUSINESS_ERROR_00695 or<BR>
     * 　@BUSINESS_ERROR_00696 or<BR>
     * 　@BUSINESS_ERROR_00697 or<BR>
     * 　@BUSINESS_ERROR_00700）<BR>
     * <BR>
     * "市場変更銘柄エラー"<BR>
     * （BUSINESS_ERROR_01966 or<BR>
     * BUSINESS_ERROR_01307）<BR>
     * <BR>
     * "特定口座エラー"<BR>
     * （BUSINESS_ERROR_00026 or<BR>
     * BUSINESS_ERROR_01703）<BR>
     * <BR>
     * "注文繰越スキップ銘柄エラー"<BR>
     * BUSINESS_ERROR_00684<BR>
     * <BR>
     * "二階建チェックエラー"<BR>
     * BUSINESS_ERROR_01928<BR>
     * <BR>
     * "発注日チェックエラー"<BR>
     * （BUSINESS_ERROR_00205 or<BR>
     * 　@BUSINESS_ERROR_02288）<BR>
     * <BR>
     * "呼値チェックエラー"<BR>
     * BUSINESS_ERROR_00030<BR>
     * <BR>
     * "空売りチェックエラー"<BR>
     * BUSINESS_ERROR_00734<BR>
     * <BR>
     * "決済期日到来済エラー"<BR>
     * BUSINESS_ERROR_00748<BR>
     * <BR>
     * "その他エラー"<BR>
     * 上記以外<BR>
     * @@param l_strErrorCode - (エラーコード)<BR>
     * WEB3ErrorCatalogに定義されているエラーコード。<BR>
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
            //"0000"（正常）
            return WEB3ErrorReasonCodeDef.NORMAL;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00031.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02298.getErrorCode().equals(l_strErrorCode))
        {
            //"0001"（値幅エラー）
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01929.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01930.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02299.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0002"（預り金不足エラー）
            return WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00167.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0003"（株式残高不足エラー）
            return WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01935.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02300.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0004"（保証金不足エラー）
            return WEB3ErrorReasonCodeDef.GUARANTY_MONEY_SHORT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00808.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02339.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0005"（建株残高不足エラー）
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
            //"0006"（売買停止銘柄エラー）
            return WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
        }                                
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01966.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01307.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0007"（市場変更銘柄エラー）
            return WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
        } 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00026.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_01703.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            // BUSINESS_ERROR_00026 : 現物株式
            // BUSINESS_ERROR_01703 : 信用取引
            //"0010"（特定口座エラー）
            return WEB3ErrorReasonCodeDef.SPEC_ACCOUNT_ERROR;
        } 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00684.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0011"（注文繰越スキップ銘柄エラー）
            return WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01928.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0012"（二階建チェックエラー）
            return WEB3ErrorReasonCodeDef.MARGIN_SEC_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00205.getErrorCode().equals(l_strErrorCode) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02288.getErrorCode().equals(l_strErrorCode))
        {
            //"0013"（発注日チェックエラー）
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorReasonCodeDef.BIZ_DATE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00030.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0014"（呼値チェックエラー）
            return WEB3ErrorReasonCodeDef.TICK_VALUE_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00734.getErrorCode().equals(l_strErrorCode))
        {
            log.exiting(STR_METHOD_NAME);
            //"0015"（空売りチェックエラー）
            return WEB3ErrorReasonCodeDef.SHORT_SELLING_ERROR;
        }
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_00748.getErrorCode().equals(l_strErrorCode)) 
        {
            log.exiting(STR_METHOD_NAME);
            //"0016"（決済期日到来済エラー）
            return WEB3ErrorReasonCodeDef.SETTLEDAY_CAME_ERROR;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            //上記以外の発注審査でエラーが発生した場合は、"9001"（その他エラー）を設定する。
            return WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }
    }
    
    /**
     * （update発注遅延）<BR>
     * <BR>
     * 指定された注文IDに紐付く注文データ類を発注遅延のステータスに更新する。<BR>
     * <BR>
     * １）　@パラメータ.注文IDに紐付く注文単位を取得する。<BR>
     * <BR>
     * ２）　@１）の注文単位のcloneを作成する。<BR>
     * <BR>
     * ３）　@２）にて作成したcloneに対し、更新値をセットする。<BR>
     * 　@DB更新仕様<BR>
     * 　@「逆指値注文発注（発注遅延）_株式注文単位テーブル.xls」参照。<BR>
     * <BR>
     * ４）　@注文履歴オブジェクトを取得する。<BR>
     * 　@this.create注文履歴()をコールする。<BR>
     * <BR>
     * 　@[create注文履歴()に指定する引数]<BR>
     * 　@　@注文単位：　@３）にて作成した注文単位<BR>
     * 　@　@イベントタイプ：　@"21：発注遅延"<BR>
     * <BR>
     * 　@※DB更新仕様（参考）<BR>
     * 　@「逆指値注文発注(発注遅延)_株式注文履歴テーブル.xls」参照。<BR>
     * <BR>
     * ５）　@注文データをupdateする。<BR>
     * 　@this.update注文データ()をコールする。<BR>
     * <BR>
     * 　@[update注文データ()に指定する引数]<BR>
     * 　@　@注文単位：　@３）にて作成した注文単位<BR>
     * 　@　@注文履歴：　@create注文履歴()の戻り値<BR>
     * @@param l_orderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@throws WEB3BaseException
     */
    public void updateOrderDelay(long l_orderId) throws WEB3BaseException
    {
    	final String STR_METHOD_NAME = "updateOrderDelay(long)";
    	log.entering(STR_METHOD_NAME);
    	
    	//１）　@パラメータ.注文IDに紐付く注文単位を取得する。
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
		
		//２）　@１）の注文単位のcloneを作成する。
		EqtypeOrderUnitParams l_orderUnitParams =
			new EqtypeOrderUnitParams(l_orderUnitRow);
			
		//３）　@２）にて作成したcloneに対し、更新値をセットする。
		//注文履歴最終通番
		l_orderUnitParams.setLastOrderActionSerialNo(
			l_orderUnitParams.getLastOrderActionSerialNo() + 1);

		//更新日付
		Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
		l_orderUnitParams.setLastUpdatedTimestamp(l_tsSysTime);

		//発注遅延フラグ
        l_orderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);
		
        //４）　@注文履歴オブジェクトを取得する。
        //　@[create注文履歴()に指定する引数]
        //　@　@注文単位：　@３）にて作成した注文単位
        //　@　@イベントタイプ：　@"21：発注遅延"
        EqTypeOrderUnit l_afterOrderUnit = 
            (EqTypeOrderUnit)this.toOrderUnit(l_orderUnitParams);
        EqTypeOrderAction l_orderAction =
            this.createOrderAction(l_afterOrderUnit, OrderEventTypeEnum.ORDER_DELAY);

        //５）　@注文データをupdateする。
        //　@[update注文データ()に指定する引数]
        //　@注文単位：　@３）にて作成した注文単位
        //　@注文履歴：　@create注文履歴()の戻り値
        this.updateOrderData(l_afterOrderUnit, l_orderAction);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc拘束金額計算単価)<BR>
     * <BR>
     * 拘束売買代金／売買代金計算に使用する計算単価を計算し返却する。<BR>
     * また、引数の手数料オブジェクトに、計算単価の指値／成行の別を設定する。<BR>
     * －現物売買、新規建、返済に対応。<BR>
     * －不出来引成注文を"成行"（STOP高）で拘束する場合であっても、手数料は"指値"で計算する。<BR>
     * <BR>
     * １）　@引数チェック<BR>
     * <BR>
     * １－１）　@引数の注文種別が以下のいずれにも該当しない場合は、<BR>
     * 　@　@　@　@　@「パラメータ不正」の例外をthrowする。<BR>
     * <BR>
     * 　@　@　@現物買注文<BR>
     * 　@　@　@現物売注文<BR>
     * 　@　@　@新規買建注文<BR>
     * 　@　@　@新規売建注文<BR>
     * 　@　@　@売返済（買建返済）注文<BR>
     * 　@　@　@買返済（売建返済）注文<BR>
     * <BR>
     * 注文種別毎に拘束用の計算単価、及び手数料計算条件（指値／成行）を取得・セットする。<BR>
     * =================================================================================<BR>
     * ２）　@引数の注文種別==現物買注文の場合<BR>
     * <BR>
     * ２－１）　@以下の①@～③のいずれかに合致する場合は、this.calc時価()により時価を取得する。<BR>
     * 　@　@　@※ただし、引数の確認時単価≠nullの場合は、引数の確認時単価を使用する。<BR>
     * <BR>
     * 　@　@①@成行注文の場合（引数の指値==0）<BR>
     * 　@　@②W指値注文で、訂正指値==成行指定の場合<BR>
     * 　@　@　@（引数の発注条件=="W指値" かつ （W指値)訂正指値==0）<BR>
     * 　@　@③STOP高拘束部店(*1)で不出来引成注文指定の場合（引数の執行条件=="不出来引成"）<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc時価()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@手数料商品コード：　@"上場株式"<BR>
     * 　@　@　@取引銘柄：　@引数の同項目<BR>
     * 　@　@　@補助口座：　@引数の同項目<BR>
     * 　@　@　@isSTOP高考慮：　@true（考慮する）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ２－２）　@戻り値を決定する。<BR>
     * <BR>
     * 　@　@　@　@　@取得した時価<BR>
     * 　@　@　@　@　@引数の指値<BR>
     * 　@　@　@　@　@引数の（W指値)訂正指値<BR>
     * 　@　@　@を比較し、一番高い単価を戻り値とする。<BR>
     * <BR>
     * ２－３）　@引数の手数料オブジェクトのis指値プロパティに、<BR>
     * 　@　@　@　@－成行注文の場合（引数の指値==0）：　@false（成行）<BR>
     * 　@　@　@　@－上記以外：　@true（指値）<BR>
     * 　@　@　@をセットする。<BR>
     * <BR>
     * =================================================================================<BR>
     * ３）　@引数の注文種別==現物売注文の場合<BR>
     * <BR>
     * ３－１）　@成行注文の場合（引数の指値==0）は、this.calc時価()により時価を取得する。<BR>
     * 　@　@　@※ただし、引数の確認時単価≠nullの場合は、引数の確認時単価を使用する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc時価()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@手数料商品コード：　@"上場株式"<BR>
     * 　@　@　@取引銘柄：　@引数の同項目<BR>
     * 　@　@　@補助口座：　@引数の同項目<BR>
     * 　@　@　@isSTOP高考慮：　@false（考慮しない）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@拘束用計算単価として、取得した時価を戻り値とする。<BR>
     * 　@　@　@また、引数の手数料オブジェクトのis指値プロパティにfalse（成行）をセットする。<BR>
     * <BR>
     * ３－２）　@上記「３－１）」以外の場合は、引数の指値を戻り値とする。<BR>
     * 　@　@　@また、引数の手数料オブジェクトのis指値プロパティにtrue（指値）をセットする。<BR>
     * <BR>
     * =================================================================================<BR>
     * ４）　@引数の注文種別==（新規買建注文 or 新規売建注文）の場合<BR>
     * <BR>
     * ４－１）　@以下の①@～③のいずれかに合致する場合は、this.calc時価()により時価を取得する。<BR>
     * 　@　@　@※ただし、引数の確認時単価≠nullの場合は、引数の確認時単価を使用する。<BR>
     * <BR>
     * 　@　@①@成行注文の場合（引数の指値==0）<BR>
     * 　@　@②W指値注文で、訂正指値==成行指定の場合<BR>
     * 　@　@　@（引数の発注条件=="W指値" かつ （W指値)訂正指値==0）<BR>
     * 　@　@③STOP高拘束部店(*1)で不出来引成注文指定の場合（引数の執行条件=="不出来引成"）<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc時価()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@手数料商品コード：　@"上場株式"<BR>
     * 　@　@　@取引銘柄：　@引数の同項目<BR>
     * 　@　@　@補助口座：　@引数の同項目<BR>
     * 　@　@　@isSTOP高考慮：　@true（考慮する）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４－２）　@売建指値注文(*2)の場合、時価（非STOP高）を取得する。<BR>
     * 　@　@　@※ただし、引数の確認時単価≠nullの場合は、引数の確認時単価を使用する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc時価()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@手数料商品コード：　@"上場株式"<BR>
     * 　@　@　@取引銘柄：　@引数の同項目<BR>
     * 　@　@　@補助口座：　@引数の同項目<BR>
     * 　@　@　@isSTOP高考慮：　@false（考慮しない）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * ４－３）　@戻り値を決定する。<BR>
     * <BR>
     * 　@　@　@　@　@４－１）で取得した時価<BR>
     * 　@　@　@　@　@４－２）で取得した時価<BR>
     * 　@　@　@　@　@引数の指値<BR>
     * 　@　@　@　@　@引数の（W指値)訂正指値<BR>
     * 　@　@　@を比較し、一番高い単価を戻り値とする。<BR>
     * <BR>
     * ４－４）　@引数の手数料オブジェクトのis指値プロパティに、<BR>
     * 　@　@　@　@－成行注文の場合（引数の指値==0）：　@false（成行）<BR>
     * 　@　@　@　@－上記以外：　@true（指値）<BR>
     * 　@　@　@をセットする。<BR>
     * <BR>
     * =================================================================================<BR>
     * ５）　@引数の注文種別==（売返済（買建返済）注文 or 買返済（売建返済）注文）の場合<BR>
     * <BR>
     * ５－１）　@成行注文の場合（引数の指値==0）は、this.calc時価()により時価を取得する。<BR>
     * 　@　@　@※ただし、引数の確認時単価≠nullの場合は、引数の確認時単価を使用する。<BR>
     * <BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * 　@　@　@＜calc時価()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@　@手数料商品コード：　@"上場株式"<BR>
     * 　@　@　@取引銘柄：　@引数の同項目<BR>
     * 　@　@　@補助口座：　@引数の同項目<BR>
     * 　@　@　@isSTOP高考慮：　@false（考慮しない）<BR>
     * 　@　@　@----------------------------------------------------------<BR>
     * <BR>
     * 　@　@　@拘束用計算単価として、取得した時価を戻り値とする。<BR>
     * 　@　@　@また、引数の手数料オブジェクトのis指値プロパティにfalse（成行）をセットする。<BR>
     * <BR>
     * ５－２）　@上記「５－１）」以外の場合は、引数の指値を戻り値とする。<BR>
     * 　@　@　@また、引数の手数料オブジェクトのis指値プロパティにtrue（指値）をセットする。<BR>
     * =================================================================================<BR>
     * <BR>
     * (*1)STOP高拘束部店<BR>
     * 　@顧客の部店（引数の補助口座.部店ID）、手数料商品コード=="上場株式"を検索条件に指定し、<BR>
     * 　@【会社部店商品テーブル】から取得した「概算金額計算方式」=="STOP高拘束方式"の場合は、<BR>
     * 　@STOP高拘束部店であると判定する。<BR>
     * <BR>
     * (*2)売建指値注文<BR>
     * 　@指値注文（引数の指値 > 0）、かつ 引数の注文種別=="新規売建注文"の場合、売建指値注文。<BR>
     * <BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料。<BR>
     * （当メソッド内で、is指値プロパティを再セットする）<BR>
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別。<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値。<BR>
     * （指値指定の場合は、その値。成行指定の場合は、0）<BR>
     * @@param l_dblWLimitPrice - (（W指値）訂正指値)<BR>
     * （W指値）訂正指値。<BR>
     * （訂正指値==指値指定の場合は、その値。訂正指値==成行指定の場合は、0）<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件。<BR>
     * @@param l_execConditionType - (執行条件)<BR>
     * 執行条件。<BR>
     * @@param l_strPriceConditionType - (値段条件)<BR>
     * 値段条件。<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 株式取引銘柄。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座。<BR>
     * @@param l_strCheckPrice - (確認時単価)<BR>
     * 確認時単価。<BR>
     * （上り処理の場合、リクエスト.確認時単価をそのままセット。<BR>
     * 　@下り処理の場合、取得済の時価を使い回したい際にセット。）<BR>
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
        
        // 引数チェック
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
                "引数:注文種別が不正な値です。　@値：[" + l_orderType + "]");
        }
        
        // 引数出力
        log.debug("<メソッド引数>");
        log.debug("注文種別         :[" + l_orderType + "]");
        log.debug("指値             :[" + l_dblLimitPrice + "]");
        log.debug("（W指値）訂正指値 :[" + l_dblWLimitPrice + "]");
        log.debug("発注条件         :[" + l_strOrderConditionType + "]");
        log.debug("執行条件         :[" + l_execConditionType + "]");
        log.debug("値段条件         :[" + l_strPriceConditionType + "]");
        log.debug("確認時単価       :[" + l_strCheckPrice + "]");
        
        // (*)STOP高拘束部店かどうかの判別
        // －現物買／新規建注文の場合は、部店の概算金額計算方式を取得する
        int l_intEstimatePriceCalcForm = 0;
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_orderType) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            log.debug("【概算金額計算方式の取得】");
            // 概算金額計算方式の取得
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
                    "会社部店商品テーブルに該当データなし");
            }
            catch (DataException l_de)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_de.getMessage(),
                    l_de);
            }
            log.debug("概算金額計算方式 = [" + l_intEstimatePriceCalcForm + "] (1:STOP高方式,2:割増拘束方式)");
        }
        
        double l_dblReturnCalcPrice = 0.0D;
        // 現物買注文の場合
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType))
        {
            double l_dblCurrentPrice = 0.0D;
            // 時価を取得する場合
            if (l_dblLimitPrice == 0 ||
                (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType) &&
                    l_dblWLimitPrice == 0) ||
                (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_intEstimatePriceCalcForm &&
                    EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execConditionType))
               )
            {
                log.debug("時価を取得する場合");
	            // 引数の確認時単価≠nullの場合は時価を取得しない
	            if (l_strCheckPrice != null)
	            {
	                log.debug("　@⇒引数.確認時単価を時価として使用。");
	                l_dblCurrentPrice = Double.parseDouble(l_strCheckPrice);
	            }
	            else
                {
	                log.debug("　@⇒時価の再取得を実施。");
	                l_dblCurrentPrice = this.calcCurrentPrice(
	                    WEB3CommisionProductCodeDef.LISTING_STOCK,
	                    l_tradedProduct,
	                    l_subAccount,
	                    true);
                }
            }
            
            // 計算単価の決定
            l_dblReturnCalcPrice = l_dblCurrentPrice;
            log.debug("①@取得した時価          = [" + l_dblReturnCalcPrice + "] ※取得していない場合は0");
            log.debug("②引数.指値             = [" + l_dblLimitPrice + "]");
            log.debug("③引数.（W指値）訂正指値 = [" + l_dblWLimitPrice + "]");
            log.debug("①@～③の内、一番高い単価を計算単価とする。");
            
            if (Double.compare(l_dblLimitPrice, l_dblReturnCalcPrice) > 0)
            {
                l_dblReturnCalcPrice = l_dblLimitPrice;
            }
            if (Double.compare(l_dblWLimitPrice, l_dblReturnCalcPrice) > 0)
            {
                l_dblReturnCalcPrice = l_dblLimitPrice;
            }
            
            log.debug("【決定した計算単価】 = [" + l_dblReturnCalcPrice + "]");
            
            // 引数.手数料.is指値プロパティのセット
            if (l_dblLimitPrice == 0)
            {
                l_commission.setIsLimitPrice(false);
            }
            else
            {
                l_commission.setIsLimitPrice(true);
            }
            
            log.debug("【引数.手数料.is指値】 = [" + l_commission.isLimitPrice() + "]");
        }
        // 現物売注文の場合 or 売返済（買建返済）注文 or 買返済（売建返済）注文の場合
        // ※現物売と信用返済で同一の仕様である為、一箇所に纏めた。
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType) ||
            OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType) ||
            OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
        {
            // 時価を取得する場合
            if (l_dblLimitPrice == 0)
            {
                log.debug("成行注文の場合");
                // 引数の確認時単価≠nullの場合は時価を取得しない
                if (l_strCheckPrice != null)
                {
                    log.debug("　@⇒引数.確認時単価を計算単価として使用。");
                    l_dblReturnCalcPrice = Double.parseDouble(l_strCheckPrice);
                }
                else
                {
                    log.debug("　@⇒時価の再取得を実施し、取得した時価を計算単価として使用。");
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
                log.debug("指値注文の場合、引数.指値を計算単価として使用。");
                l_dblReturnCalcPrice = l_dblLimitPrice;
                l_commission.setIsLimitPrice(true);
            }
            
            log.debug("【決定した計算単価】 = [" + l_dblReturnCalcPrice + "]");
            log.debug("【引数.手数料.is指値】 = [" + l_commission.isLimitPrice() + "]");
        }
        // 新規買建注文 or 新規売建注文の場合
        else if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
        {
            double l_dblCurrentPrice = 0.0D;
            // 時価を取得する場合
            if (l_dblLimitPrice == 0 ||
                (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType) &&
                    l_dblWLimitPrice == 0) ||
                (WEB3PremiumRestraintRateDef.STOP_QUANTITY_RESTRANT == l_intEstimatePriceCalcForm &&
                    EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_execConditionType))
               )
            {
                log.debug("時価を取得する場合");
                // 引数の確認時単価≠nullの場合は時価を取得しない
                if (l_strCheckPrice != null)
                {
                    log.debug("　@⇒引数.確認時単価を時価として使用。");
                    l_dblCurrentPrice = Double.parseDouble(l_strCheckPrice);
                }
                else
                {
                    log.debug("　@⇒時価の再取得を実施。");
                    l_dblCurrentPrice = this.calcCurrentPrice(
                        WEB3CommisionProductCodeDef.LISTING_STOCK,
                        l_tradedProduct,
                        l_subAccount,
                        true);
                }
            }
            
            double l_dblCurPriceForMarginShort = 0.0D;
            // 売建指値の場合
            if (l_dblLimitPrice > 0 && OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
            {
                log.debug("売建指値注文の場合、非STOP高の時価を取得する。");
                // 引数の確認時単価≠nullの場合は時価を取得しない
                if (l_strCheckPrice != null)
                {
                    log.debug("　@⇒引数.確認時単価を時価として使用。");
                    l_dblCurPriceForMarginShort = Double.parseDouble(l_strCheckPrice);
                }
                else
                {
                    log.debug("　@⇒時価の再取得を実施。");
                    l_dblCurPriceForMarginShort = this.calcCurrentPrice(
                        WEB3CommisionProductCodeDef.LISTING_STOCK,
                        l_tradedProduct,
                        l_subAccount,
                        false);
                }
            }
            // 計算単価の決定
            l_dblReturnCalcPrice = l_dblCurrentPrice;
            log.debug("①@取得した時価          = [" + l_dblReturnCalcPrice + "] ※取得していない場合は0");
            log.debug("②売建指値比較用時価     = [" + l_dblCurPriceForMarginShort + "] ※取得していない場合は0");
            log.debug("③引数.指値             = [" + l_dblLimitPrice + "]");
            log.debug("④引数.（W指値）訂正指値 = [" + l_dblWLimitPrice + "]");
            log.debug("①@～④の内、一番高い単価を計算単価とする。");
            
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
            
            log.debug("【決定した計算単価】 = [" + l_dblReturnCalcPrice + "]");
            
            // 引数.手数料.is指値プロパティのセット
            if (l_dblLimitPrice == 0)
            {
                l_commission.setIsLimitPrice(false);
            }
            else
            {
                l_commission.setIsLimitPrice(true);
            }
            
            log.debug("【引数.手数料.is指値】 = [" + l_commission.isLimitPrice() + "]");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblReturnCalcPrice;
     }
     
    /**
     * (remove繰越元注文単位)<BR>
     * 引数の注文単位オブジェクトのリストから、繰越元の注文単位オブジェクトを除去し、除去後のリストを返却する。<BR>
     * <BR>
     * (1)除去対象の判定<BR>
     * <BR>
     * 　@　@　@以下、パラメータ.注文単位一覧の要素数分のLoop処理。<BR>
     * <BR>
     * 　@　@　@[株式注文マネージャ.is出来るまで注文単位(注文単位) == falseの場合]<BR>
     * 　@　@　@(当日限り注文の場合)<BR>
     * 　@　@　@　@　@リストにそのまま残す。<BR>
     * <BR>
     * 　@　@　@[株式注文マネージャ.is出来るまで注文単位(注文単位) == trueの場合]<BR>
     * 　@　@　@(出来るまで注文の場合)<BR>
     * 　@　@　@　@[初回注文の場合]<BR>
     * 　@　@　@　@(注文単位.初回注文の注文単位ID == 0の場合)<BR>
     * 　@　@　@　@　@リスト中(パラメータ.注文単位一覧)を検索し、<BR>
     * 　@　@　@　@　@　@　@注文単位.注文単位ID == リスト中の注文単位.初回注文の注文単位ID<BR>
     * 　@　@　@　@　@となるデータが存在した場合は、自身を除去対象とする。<BR>
     * 　@　@　@　@　@※繰越後の注文が存在する為。<BR>
     * <BR>
     * 　@　@　@　@[繰越済注文の場合]<BR>
     * 　@　@　@　@(注文単位.初回注文の注文単位ID != 0の場合)<BR>
     * 　@　@　@　@　@リスト中(パラメータ.注文単位一覧)を検索し、<BR>
     * 　@　@　@　@　@　@　@注文単位.初回注文の注文単位ID == リスト中の注文単位.初回注文の注文単位ID<BR>
     * 　@　@　@　@　@となるデータが存在した場合は、作成日を比較し、最新の注文単位以外を全て除去対象とする。<BR>
     * 　@　@　@　@　@※最新の繰越注文のみを表示する為。<BR>
     * <BR>
     * (2)リストからの除去対象と判定された繰越元の注文単位オブジェクトを、注文単位一覧から全て除去する。<BR>
     * 　@　@　@※パラメータ.注文単位オブジェクトの並び順は顧客指定のソート条件によるため、除去は最後に纏めて行う必要がある。<BR>
     * <BR>
     * (3)除去済の注文単位一覧を返却する。<BR>
     * ※注文単位一覧の要素数が0になった場合はNULLを返却する。<BR>
     * <BR>
     * @@param l_orderUnits - (注文単位一覧)<BR>
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
	 * (get空売り規制時間)<BR>
	 * <BR>
	 * 空売り規制時間テーブルから、<BR>
	 * 取引カレンダコンテキストの設定内容に合致するレコードを取得し返却する。<BR>
	 * <BR>
	 * 以下条件で空売り規制時間テーブルレコードを取得する。<BR>
	 * <BR>
	 * 　@[条件]<BR>
	 * 　@証券会社コード　@　@＝　@　@取引時間コンテキスト．証券会社コード　@and<BR>
	 * 　@部店コード　@　@＝　@　@取引時間コンテキスト．部店コード　@and<BR>
	 * 　@市場コード　@＝　@取引時間コンテキスト．市場コード　@and<BR>
	 * 　@営業日区分　@＝　@取引時間管理．get営業日区分　@and<BR>
	 * 　@開始時間　@≦　@現在時刻（HHMMSS）　@and<BR>
	 * 　@終了時間　@≧　@現在時刻（HHMMSS）<BR>
	 * <BR>
	 * 取得したレコードを返却する。<BR>
	 * ※該当データなしの場合は例外をthrowする。<BR>
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
		// 取引カレンダコンテキスト取得
		WEB3GentradeTradingClendarContext l_tradingClendarContext =
			(WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
				WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
		// 現在時刻取得
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
				"空売り規制時間テーブルに該当データがありません");
		}
        
		if (l_lisShortSellingResTime.size() > 1)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80004,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"空売り規制時間テーブルに重複するレコードが存在します");
		}
        
		// 空売り規制時間取得
		ShortSellingRestraintTimeRow l_shortSellingResTimeRow =
			(ShortSellingRestraintTimeRow)l_lisShortSellingResTime.get(0);
        
		log.debug("【現在時刻】="+WEB3DateUtility.formatDate(l_tsSystemTime, "HHmmss")
			+"【空売り規制時間】="+l_shortSellingResTimeRow.toString());
            
		log.exiting(STR_METHOD_NAME);
		return l_shortSellingResTimeRow;
	}
    
    /**
     * (notifyW指値注文)<BR>
     * （notifyWLimitOrder）<BR>
     * W指値注文（ストップ注文）の登録、訂正、取消を<BR>  
     * ルールエンジンサーバに通知する。<BR>
     * <BR>
     * １）　@株式注文単位かどうかの判定<BR>
     * 　@引数の注文単位.getDataSourceObject()をコールする。<BR> 
     * <BR>
     * 　@[メソッドの戻り値の型が株式注文単位Rowでない場合]<BR>  
     * 　@　@処理対象外である為、処理を終了する。（return）<BR>
     * 　@　@※予約注文単位に対して、W指値は設定不可。<BR>
     * <BR>
     * 　@[株式注文単位Rowである場合]<BR>  
     * 　@　@getOrderUnit().getDataSourceObject()をコールし、<BR>  
     * 　@　@注文単位を取得し直す。<BR>
     * <BR>
     * ２）　@ルールエンジンサーバへの通知要否チェック<BR> 
     * <BR>
     * 　@２－１）　@切替未済のW指値注文の判定<BR> 
     * 　@　@切替未済のW指値注文でない場合、  <BR>
     *　@　@（（１）の戻り値.発注条件 = "W指値" かつ<BR>
     *　@　@　@拡張株式注文マネージャ.is未発注注文() = true ）以外<BR>
     * 　@　@処理対象外である為、処理を終了する。（return）  <BR>
     * <BR>
     *　@　@[is未発注注文()に指定する引数] <BR>
     *　@　@　@注文単位：　@引数.注文単位 <BR>
     * <BR>
     * 　@２－２）　@未発注の発注遅延注文の判定<BR> 
     * 　@　@拡張株式注文マネージャ.is未発注遅延注文() == trueの場合、<BR> 
     * 　@　@処理対象外である為、処理を終了する。（return） <BR>
     * <BR>
     * 　@　@[is未発注遅延注文()に指定する引数]<BR> 
     * 　@　@　@注文単位：　@引数.注文単位 <BR>
     * <BR>
     * ３）　@補助口座を取得する。<BR>  
     * 　@拡張アカウントマネージャ.getSubAccount()をコールする。<BR>  
     * <BR>
     * 　@[getSubAccount()に指定する引数]<BR>  
     * 　@　@arg0：　@１）の戻り値.口座ID  <BR>
     * 　@　@arg1：　@１）の戻り値.補助口座ID  <BR>
     * <BR>
     * ４）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService）<BR>  
     * 　@を取得する。  <BR>
     * <BR>
     * ５）　@ルールエンジンサーバに通知を行う。<BR>  
     * 　@引数の処理、注文単位.注文状態によって下記処理分岐を行う。<BR>  
     * <BR>
     * 　@５－１）取消<BR> 
     * 　@　@ストップ注文失効（処理 == "ORDER_EXPIRED"）、<BR>
     * 　@　@またはSONARからの取消失敗（処理 == "CANCEL_ORDER_REJECTED_BY_MKT"）<BR>
     * 　@　@または失効、注文繰越スキップ（処理 == "ORDER_INVALIDATED_BY_MKT"）<BR>
     * 　@　@または取消受付済（注文状態="受付済（取消注文）"）の場合、 <BR>
     * 　@　@または取消完了、全部約定、注文受付エラー（注文有効状態="クローズ"）の場合<BR> 
     * 　@　@取得したサービス.sendCancelConOrderMessage()メソッドを  <BR>
     * 　@　@コールする。 <BR>
     * <BR>
     * 　@　@[sendCancelConOrderMessage()に指定する引数]<BR>  
     * 　@　@　@補助口座：　@取得した補助口座  <BR>
     * 　@　@　@条件付注文タイプ：　@"W指値"  <BR>
     * 　@　@　@銘柄タイプ：　@"株式"  <BR>
     * 　@　@　@注文ID：　@１）の戻り値.注文ID<BR>  
     * <BR>
     * 　@５－２）登録<BR> 
     * 　@　@新規登録（注文状態="受付済（新規注文）"）の場合、<BR> 
     * 　@　@取得したサービス.sendRegisterConOrdersMessage()メソッドを<BR>  
     * 　@　@コールする。 <BR>
     * <BR>
     * 　@　@[sendRegisterConOrdersMessage()に指定する引数]<BR>  
     * 　@　@　@補助口座：　@取得した補助口座  <BR>
     * 　@　@　@条件付注文タイプ：　@"W指値"  <BR>
     * 　@　@　@親注文の銘柄タイプ：　@"株式"  <BR>
     * 　@　@　@親注文の注文ID：　@１）の戻り値.注文ID<BR>  
     * 　@　@　@子注文の銘柄タイプ一覧：　@null  <BR>
     * 　@　@　@子注文の注文ID一覧：　@null  <BR>
     * <BR>
     * 　@５－３）訂正<BR> 
     * 　@　@（注文状態="受付済（変更注文）" or "発注済（変更注文）"）の場合<BR>  
     * 　@　@取得したサービス.sendModifyConOrdersMessage()メソッドを  <BR>
     * 　@　@コールする。 <BR>
     * 　@　@※市場送信済注文／市場未送信注文の訂正を考慮。<BR>  
     * <BR>
     * 　@　@[sendModifyConOrdersMessage()に指定する引数]<BR>  
     * 　@　@　@補助口座：　@取得した補助口座  <BR>
     * 　@　@　@条件付注文タイプ：　@"W指値"  <BR>
     * 　@　@　@親注文の銘柄タイプ：　@"株式"  <BR>
     * 　@　@　@親注文の注文ID：　@１）の戻り値.注文ID<BR>  
     * 　@　@　@子注文の銘柄タイプ一覧：　@null  <BR>
     * 　@　@　@子注文の注文ID一覧：　@null  <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@param l_persistenceContext - (処理)<BR>
     * 処理。  <BR>
     * （OrderManagerPersistenceContextにて定数定義）
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
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        //１）　@株式注文単位かどうかの判定　@  
        //　@引数の注文単位.getDataSourceObject()をコールする。 
        //　@[メソッドの戻り値の型が株式注文単位Rowでない場合]  
        //　@　@処理対象外である為、処理を終了する。（return）  
        //　@　@※予約注文単位に対して、W指値は設定不可。  
        //　@[株式注文単位Rowである場合]  
        //　@　@getOrderUnit().getDataSourceObject()をコールし、  
        //　@　@注文単位を取得し直す。  
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
            log.error("テーブルに該当するデータがありません。");
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
        
        //　@２－１）　@切替未済のW指値注文の判定 
        //　@　@切替未済のW指値注文でない場合、  
        //　@　@（（１）の戻り値.発注条件 = "W指値" かつ
        //　@　@　@拡張株式注文マネージャ.is未発注注文() = true ）以外
        //　@　@処理対象外である為、処理を終了する。（return）
        //
        //　@　@[is未発注注文()に指定する引数]
        //　@　@　@注文単位：　@引数.注文単位
        if (!(WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_orderUnitRow.getOrderConditionType())
            && this.isNotOrderedOrder(l_orderUnit)))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        //　@２－２）　@未発注の発注遅延注文の判定 
        //　@　@拡張株式注文マネージャ.is未発注遅延注文() == trueの場合、 
        //　@　@処理対象外である為、処理を終了する。（return） 
        //　@　@[is未発注遅延注文()に指定する引数] 
        //　@　@　@注文単位：　@　@引数.注文単位
        if (this.isNotOrderedDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //３）　@補助口座を取得する。  
        //　@拡張アカウントマネージャ.getSubAccount()をコールする。  
        //　@[getSubAccount()に指定する引数]  
        //　@　@arg0：　@１）の戻り値.口座ID  
        //　@　@arg1：　@１）の戻り値.補助口座ID  
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
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //４）　@ルールエンジン通知サービス（WEB3RlsRequestSenderService）  
        //　@を取得する。  
        WEB3RlsRequestSenderService l_rlsRequestSenderService =
            (WEB3RlsRequestSenderService)Services.getService(WEB3RlsRequestSenderService.class);
        
        //５）　@ルールエンジンサーバに通知を行う。  
        //　@引数の処理、注文単位.注文状態によって下記処理分岐を行う。  
        //　@５－１）取消 
        //　@　@ストップ注文失効（処理 == "ORDER_EXPIRED"）、
        //　@　@またはSONARからの取消失敗（処理 == "CANCEL_ORDER_REJECTED_BY_MKT"）
        //　@　@または失効、注文繰越スキップ（処理 == "ORDER_INVALIDATED_BY_MKT"）
        //　@　@または取消受付済（注文状態="受付済（取消注文）"）の場合、 
        //　@　@または取消完了、全部約定、注文受付エラー（注文有効状態="クローズ"）の場合 
        //　@　@取得したサービス.sendCancelConOrderMessage()メソッドを  
        //　@　@コールする。 
        //　@　@[sendCancelConOrderMessage()に指定する引数]  
        //　@　@　@補助口座：　@取得した補助口座  
        //　@　@　@条件付注文タイプ：　@"W指値"  
        //　@　@　@銘柄タイプ：　@"株式"  
        //　@　@　@注文ID：　@１）の戻り値.注文ID  
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
        
        //　@５－２）登録 
        //　@　@新規登録（注文状態="受付済（新規注文）"）の場合、 
        //　@　@取得したサービス.sendRegisterConOrdersMessage()メソッドを  
        //　@　@コールする。 
        //　@　@[sendRegisterConOrdersMessage()に指定する引数]  
        //　@　@　@補助口座：　@取得した補助口座  
        //　@　@　@条件付注文タイプ：　@"W指値"  
        //　@　@　@親注文の銘柄タイプ：　@"株式"  
        //　@　@　@親注文の注文ID：　@１）の戻り値.注文ID  
        //　@　@　@子注文の銘柄タイプ一覧：　@null  
        //　@　@　@子注文の注文ID一覧：　@null  
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
        
        //　@５－３）訂正 
        //　@　@（注文状態="受付済（変更注文）" or "発注済（変更注文）"）の場合  
        //　@　@取得したサービス.sendModifyConOrdersMessage()メソッドを  
        //　@　@コールする。 
        //　@　@※市場送信済注文／市場未送信注文の訂正を考慮。  
        //　@　@[sendModifyConOrdersMessage()に指定する引数]  
        //　@　@　@補助口座：　@取得した補助口座  
        //　@　@　@条件付注文タイプ：　@"W指値"  
        //　@　@　@親注文の銘柄タイプ：　@"株式"  
        //　@　@　@親注文の注文ID：　@１）の戻り値.注文ID  
        //　@　@　@子注文の銘柄タイプ一覧：　@null  
        //　@　@　@子注文の注文ID一覧：　@null  
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
     * (isストップ注文有効)<BR>
     * OCO（W指値）注文のストップ注文が有効かどうか判別する。<BR>
     * <BR>
     * this.isストップ注文有効()に処理を委譲する。<BR>
     * <BR>
     * [isストップ注文有効()に指定する引数]<BR>
     * 　@発注条件：　@発注条件(*1)<BR>
     * 　@リクエストタイプ：　@パラメータ.注文単位.リクエストタイプ<BR>
     * <BR>
     * (*1)発注条件は、株式データアダプタ.get発注条件()により取得する。<BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]<BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件<BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        EqtypeOrderUnitRow l_orderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //(*1)発注条件は、株式データアダプタ.get発注条件()により取得する。
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_orderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
        
    }
    
    /**
     * (isストップ注文有効)<BR>
     * OCO（W指値）注文のストップ注文が有効かどうか判別する。 <BR> 
     * <BR>
     * this.isストップ注文有効()に処理を委譲する。  <BR>
     * <BR>
     * [isストップ注文有効()に指定する引数]  <BR>
     * 　@発注条件：　@発注条件(*1)  <BR>
     * 　@リクエストタイプ：　@パラメータ.注文履歴.リクエストタイプ  <BR>
     * <BR>
     * (*1)発注条件は、株式データアダプタ.get発注条件()により取得する。  <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件  <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * @@param l_orderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderValid(EqTypeOrderAction l_orderAction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderValid(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        EqtypeOrderActionRow l_orderActionRow = 
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        
        // (*1)発注条件は、株式データアダプタ.get発注条件()により取得する
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderOpen = 
            this.isStopOrderValid(
                l_strOrderConditionType, l_orderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderOpen;
        
    }
    
    /**
     * (isストップ注文有効)<BR>
     * OCO（W指値）注文のストップ注文が有効かどうか判別する。  <BR>
     * ※発注条件 == "W指値"でない場合は、  <BR>
     * 　@一律falseを返却する。  <BR>
     * <BR>
     * １）　@W指値かどうかのチェック  <BR>
     * 　@パラメータ.発注条件 != "W指値"の場合、  <BR>
     * 　@falseを返却する。  <BR>
     * <BR>
     * ２）　@ストップ注文が有効かどうかのチェック  <BR>
     * 　@パラメータ.リクエストタイプ == "切替完了"の場合、  <BR>
     * 　@trueを返却する。  <BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strRequestType - (リクエストタイプ)<BR>
     * リクエストタイプ<BR>
     * @@return boolean
     */
    private boolean isStopOrderValid(String l_strOrderConditionType, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderValid(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@W指値かどうかのチェック
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@ストップ注文が有効かどうかのチェック 
        if (WEB3RequestTypeDef.TRANSFERED.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
        
    }
    
    /**
     * (isストップ注文切替中)<BR>
     * OCO（W指値）注文がストップ注文に切替中かどうかを判別する。  <BR>
     * <BR>
     * this.isストップ注文切替中()に処理を委譲する。  <BR>
     * <BR>
     * [isストップ注文切替中()に指定する引数]  <BR>
     * 　@発注条件：　@発注条件(*1)  <BR>
     * 　@リクエストタイプ：　@パラメータ.注文単位.リクエストタイプ  <BR>
     * <BR>
     * (*1)発注条件は、株式データアダプタ.get発注条件()により取得する。  <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件  <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //(*1)発注条件は、株式データアダプタ.get発注条件()により取得する。
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_orderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
    
    /**
     * (isストップ注文切替中)<BR>
     * OCO（W指値）注文がストップ注文に切替中かどうかを判別する。  <BR>
     * <BR>
     * this.isストップ注文切替中()に処理を委譲する。  <BR>
     * <BR>
     * [isストップ注文切替中()に指定する引数]  <BR>
     * 　@発注条件：　@発注条件(*1)  <BR>
     * 　@リクエストタイプ：　@パラメータ.注文履歴.リクエストタイプ  <BR>
     * <BR>
     * (*1)発注条件は、株式データアダプタ.get発注条件()により取得する。  <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件  <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件<BR>
     * @@param l_orderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderSwitching(EqTypeOrderAction l_orderAction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        EqtypeOrderActionRow l_orderActionRow = 
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        
        // (*1)発注条件は、株式データアダプタ.get発注条件()により取得する
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderChanging = 
            this.isStopOrderSwitching(
                l_strOrderConditionType, l_orderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderChanging;
    }
    
    /**
     * (isストップ注文切替中)<BR>
     * OCO（W指値）注文がストップ注文に切替中かどうかを判別する。  <BR>
     * ※発注条件 == "W指値"でない場合は、  <BR>
     * 　@一律falseを返却する。  <BR>
     * <BR>
     * １）　@W指値かどうかのチェック  <BR>
     * 　@パラメータ.発注条件 != "W指値"の場合、  <BR>
     * 　@falseを返却する。  <BR>
     * <BR>
     * ２）　@ストップ注文に切替中かどうかのチェック  <BR>
     * 　@パラメータ.注文単位.リクエストタイプ == "時価サーバ"の場合、  <BR>
     * 　@trueを返却する。  <BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strRequestType - (リクエストタイプ)<BR>
     * リクエストタイプ<BR>
     * @@return boolean
     */
    private boolean isStopOrderSwitching(String l_strOrderConditionType, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderSwitching(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@W指値かどうかのチェック
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）　@ストップ注文が有効かどうかのチェック 
        if (WEB3RequestTypeDef.QUOTE_SERVER.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        //以外、falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (isストップ注文失効済)<BR>
     * OCO（W指値）注文のストップ注文が失効済かどうか判別する。  <BR>
     * <BR>
     * this.isストップ注文失効済()に処理を委譲する。  <BR>
     * <BR>
     * [isストップ注文失効済()に指定する引数]  <BR>
     * 　@発注条件：　@発注条件(*1)  <BR>
     * 　@リクエストタイプ：　@パラメータ.注文単位.リクエストタイプ  <BR>
     * <BR>
     * (*1)発注条件は、株式データアダプタ.get発注条件()により取得する。  <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文単位.発注条件  <BR>
     * 　@　@元発注条件：　@パラメータ.注文単位.元発注条件 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //(*1)発注条件は、株式データアダプタ.get発注条件()により取得する。
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderUnitRow.getOrderConditionType(),
                l_orderUnitRow.getOrgOrderConditionType());
        
        //this.isストップ注文有効()に処理を委譲する。
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_orderUnitRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    }
    
    /**
     * (isストップ注文失効済)<BR>
     * OCO（W指値）注文のストップ注文が失効済かどうか判別する。  <BR>
     * <BR>
     * this.isストップ注文失効済()に処理を委譲する。  <BR>
     * <BR>
     * [isストップ注文失効済()に指定する引数]  <BR>
     * 　@発注条件：　@発注条件(*1)  <BR>
     * 　@リクエストタイプ：　@パラメータ.注文履歴.リクエストタイプ  <BR>
     * <BR>
     * (*1)発注条件は、株式データアダプタ.get発注条件()により取得する。  <BR>
     * <BR>
     * 　@[get発注条件()に指定する引数]  <BR>
     * 　@　@発注条件：　@パラメータ.注文履歴.発注条件  <BR>
     * 　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 <BR>
     * @@param l_orderAction - (注文履歴)<BR>
     * 注文履歴オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isStopOrderExpired(EqTypeOrderAction l_orderAction) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderAction == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        EqtypeOrderActionRow l_orderActionRow = 
            (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
        
        // (*1)発注条件は、株式データアダプタ.get発注条件()により取得する
        //　@[get発注条件()に指定する引数]  
        //　@　@発注条件：　@パラメータ.注文履歴.発注条件  
        //　@　@元発注条件：　@パラメータ.注文履歴.元発注条件 
        String l_strOrderConditionType =
            WEB3EquityDataAdapter.getOrderConditionType(
                l_orderActionRow.getOrderConditionType(),
                l_orderActionRow.getOrgOrderConditionType());
        
        //[isストップ注文失効済()に指定する引数]  
        //　@発注条件：　@発注条件(*1)  
        //　@リクエストタイプ：　@パラメータ.注文履歴.リクエストタイプ  
        //(*1)発注条件は、株式データアダプタ.get発注条件()により取得する。  
        boolean l_blnIsStopOrderExpired = 
            this.isStopOrderExpired(
                l_strOrderConditionType, l_orderActionRow.getRequestType());
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsStopOrderExpired;
    }
    
    /**
     * (isストップ注文失効済)<BR>
     * OCO（W指値）注文のストップ注文が失効済かどうか判別する。  <BR>
     * ※発注条件 == "W指値"でない場合は、  <BR>
     * 　@一律falseを返却する。  <BR>
     * <BR>
     * １）　@W指値かどうかのチェック  <BR>
     * 　@パラメータ.発注条件 != "W指値"の場合、  <BR>
     * 　@falseを返却する。  <BR>
     * <BR>
     * ２）　@ストップ注文が失効済かどうかのチェック  <BR>
     * 　@パラメータ.リクエストタイプ == "失効"の場合、  <BR>
     * 　@trueを返却する。  <BR>
     * 　@以外、falseを返却する。  <BR>
     * @@param l_strOrderConditionType - (発注条件)<BR>
     * 発注条件<BR>
     * @@param l_strRequestType - (リクエストタイプ)<BR>
     * リクエストタイプ<BR>
     * @@return boolean
     */
    private boolean isStopOrderExpired(String l_strOrderConditionType, String l_strRequestType)
    {
        final String STR_METHOD_NAME = "isStopOrderExpired(String, String)";
        log.entering(STR_METHOD_NAME);
        //１）　@W指値かどうかのチェック  
        //　@パラメータ.発注条件 != "W指値"の場合、  
        //　@falseを返却する。  
        if (!WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_strOrderConditionType))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //２）　@ストップ注文が失効済かどうかのチェック  
        //　@パラメータ.リクエストタイプ == "失効"の場合、  
        //　@trueを返却する。  
        //　@以外、falseを返却する。  
        if (WEB3RequestTypeDef.INVALIDATE.equals(l_strRequestType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is手動発注可能)<BR>
     * 注文が手動発注可能であるかを判別する。  <BR>
     * ※発注条件指定なしの場合は、一律falseを返却する。  <BR>
     * <BR>
     * １）トリガー注文かどうかのチェック  <BR>
     * <BR>
     * 　@パラメータ.注文単位.発注条件 == "DEFAULT(条件指定なし)"の場合、  <BR>
     * 　@falseを返却する。  <BR>
     * 　@(手動発注対象外)  <BR>
     * <BR>
     * ２）逆指値注文の場合(パラメータ.注文単位.発注条件 == "逆指値")  <BR>
     * <BR>
     * 　@【発注待ち／発注遅延】 <BR>
     * 　@　@パラメータ.注文単位.リクエストタイプ == "DEFAULT"、かつ、  <BR>
     * 　@　@パラメータ.注文単位.注文単位.注文有効状態 == "オープン"、かつ、  <BR>
     * 　@　@取引時間管理.is市場開局時間帯() == trueの場合、trueを返却する。 <BR>
     * 　@　@以外、falseを返却する。 <BR>
     * <BR>
     * 　@※発注遅延（発注遅延無視でない会社）の場合も、上記状態に該当する。 <BR>
     * 　@ 　@発注遅延無視の会社は発注遅延時には手動発注不可。 <BR>
     * 　@　@ （発注済である為） <BR>
     * <BR>
     * ３）W指値注文の場合(パラメータ.注文単位.発注条件 == "W指値")  <BR>
     * <BR>
     * 　@【切替遅延】 <BR>
     * 　@　@this.is未発注遅延注文(パラメータ.注文単位) == true、かつ <BR>
     * 　@　@パラメータ.注文単位.注文単位.注文有効状態 == "オープン"、かつ、  <BR>
     * 　@　@パラメータ.注文単位.注文状態 != （"受付済（取消注文）"、"発注中（取消注文）")、かつ、  <BR>
     * 　@　@取引時間管理.is市場開局時間帯() == trueの場合、trueを返却する。  <BR>
     * 　@　@以外、fasleを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isManualOrderPossible(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isManualOrderPossible(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        boolean l_blnRetrun = false;
        //取引時間管理.is市場開局時間帯()
        boolean l_blnOpenTimeZone = WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();
        
        //１）トリガー注文かどうかのチェック  
        //　@パラメータ.注文単位.発注条件 == "DEFAULT(条件指定なし)"の場合、  
        //　@falseを返却する。        
        //　@(手動発注対象外) 
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        if (WEB3OrderingConditionDef.DEFAULT.equals(l_orderUnitRow.getOrderConditionType()))
        {
            l_blnRetrun = false;
        }
        
        //２）逆指値注文の場合(パラメータ.注文単位.発注条件 == "逆指値")  
        //　@【発注待ち／発注遅延】   
        //　@　@パラメータ.注文単位.リクエストタイプ == "DEFAULT"、かつ、  
        //　@　@パラメータ.注文単位.注文単位.注文有効状態 == "オープン"、かつ、  
        //　@　@取引時間管理.is市場開局時間帯() == trueの場合、trueを返却する。 
        //　@　@以外、falseを返却する。 
        //　@※発注遅延（発注遅延無視でない会社）の場合も、上記状態に該当する。 
        //　@ 　@発注遅延無視の会社は発注遅延時には手動発注不可。 
        //　@　@ （発注済である為）    
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
        
        //３）W指値注文の場合(パラメータ.注文単位.発注条件 == "W指値")  
        //　@【切替遅延】             
        //　@　@this.is未発注遅延注文(パラメータ.注文単位) == true、かつ 
        //　@　@パラメータ.注文単位.注文単位.注文有効状態 == "オープン"、かつ、  
        //　@　@パラメータ.注文単位.注文状態 != （"受付済（取消注文）"、"発注中（取消注文）")、かつ、  
        //　@　@取引時間管理.is市場開局時間帯() == trueの場合、trueを返却する。  
        //　@　@以外、fasleを返却する。
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
     * (update切替遅延)<BR>
     * （updateSwitchDelay）  <BR>
     * 指定された注文IDに紐付く注文データ類を切替遅延のステータスに更新する。  <BR>
     * <BR>
     * １）　@パラメータ.注文IDに紐付く注文単位を取得する。  <BR>
     * <BR>
     * ２）　@注文状態チェック <BR>
     * 　@　@切替処理未済（this.is未発注注文(注文単位) == true）かつ <BR>
     * 　@　@（市場未送信(*)の場合、または、 <BR>
     * 　@　@取得した注文単位.注文状態が以下いずれかに該当する場合）、<BR>
     * 　@　@「受付中／訂正中／取消中の注文は切替処理不可」の例外をスローする。 <BR>
     * <BR>
     * 　@　@　@　@・"受付済（変更注文）" <BR>
     * 　@　@　@　@・"発注中（変更注文）" <BR>
     * 　@　@　@　@・"受付済（取消注文）" <BR>
     * 　@　@　@　@・"発注中（取消注文）" <BR>
     *　@　@　@ class:WEB3BusinessLayerException<BR>
     * 　@　@　@tag  :BUSINESS_ERROR_02521<BR>
     * <BR>
     * 　@　@(*)注文単位.市場から確認済みの数量 == NaNの場合、 <BR>
     * 　@　@　@　@市場未送信の注文と判定する。 <BR>
     * <BR>
     * ３）　@１）の注文単位のcloneを作成する。  <BR>
     * <BR>
     * ４）　@３）にて作成したcloneに対し、更新値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様  <BR>
     * 　@「W指値注文切替（切替遅延）_株式注文単位テーブル.xls」参照。  <BR>
     * <BR>
     * ５）　@this.create注文履歴()をコールする。<BR>
     * <BR>
     * [引数] <BR>
     * 　@注文単位：　@４）にて作成した注文単位<BR>
     * 　@イベントタイプ：　@"22：切替遅延"<BR> 
     * <BR>
     * 　@※DB更新仕様（参考）<BR>
     * 　@「W指値注文切替（切替遅延）_注文履歴テーブル仕様.xls」参照。<BR>
     * <BR>
     * ６）　@this.update注文データ()をコールする。<BR>
     * <BR>
     * [引数]<BR>
     * 　@注文単位：　@４）にて作成した注文単位<BR>
     * 　@注文履歴：　@create注文履歴()の戻り値<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID
     * @@throws WEB3BaseException
     */
    public void updateSwitchDelay(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateSwitchDelay(long)";
        log.entering(STR_METHOD_NAME);
        //１）　@パラメータ.注文IDに紐付く注文単位を取得する。
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
        //２）　@注文状態チェック
        OrderStatusEnum l_orderStatusEnum = l_eqtypeOrderUnit.getOrderStatus();
        if (this.isNotOrderedOrder(l_eqtypeOrderUnit)
            && (Double.isNaN(l_eqtypeOrderUnit.getConfirmedQuantity())
            || OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatusEnum)
            || OrderStatusEnum.MODIFYING.equals(l_orderStatusEnum)
            || OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderStatusEnum)
            || OrderStatusEnum.CANCELLING.equals(l_orderStatusEnum)))
        {
            log.debug("受付中／訂正中／取消中の注文は切替処理不可。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02521,
                getClass().getName() + STR_METHOD_NAME,
                "受付中／訂正中／取消中の注文は切替処理不可。");
        }

        //３）　@１）の注文単位のcloneを作成する。
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqtypeOrderUnit.getDataSourceObject();
        EqtypeOrderUnitParams l_orderUnitParams = new EqtypeOrderUnitParams(l_orderUnitRow);

        //４）　@３）にて作成したcloneに対し、DB更新仕様にそって更新値をセットする。
        Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
        l_orderUnitParams.setLastOrderActionSerialNo(
            l_orderUnitParams.getLastOrderActionSerialNo() + 1);
        l_orderUnitParams.setLastUpdatedTimestamp(l_tsSysTime);
        l_orderUnitParams.setSubmitOrderDelayFlag(BooleanEnum.TRUE);

        //５）　@this.create注文履歴()をコールする。
        EqTypeOrderUnit l_orderUnit = (EqTypeOrderUnit)this.toOrderUnit(l_orderUnitParams);
        EqTypeOrderAction l_orderAction =
            (EqTypeOrderAction)this.createOrderAction(l_orderUnit, OrderEventTypeEnum.SWITCH_DELAY);

        //６）　@this.update注文データ()をコールする。
        this.updateOrderData(l_orderUnit, l_orderAction);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isSONAR取消)<BR>
     * SONARからの取消通知の場合はtrueを、  <BR>
     * 以外、falseを返却する。  <BR>
     * <BR>
     * パラメータ.注文単位.注文状態が以下のいずれかに該当する  <BR>
     * 場合、falseを返却する。  <BR>
     * 　@・"受付済（取消注文）"  <BR>
     * 　@・"発注中（取消注文）"  <BR>
     * <BR>
     * 上記以外、trueを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isSONARCancel(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSONARCancel(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //パラメータ.注文単位.注文状態が以下のいずれかに該当する  
        //場合、falseを返却する。  
        //　@・"受付済（取消注文）"  
        //　@・"発注中（取消注文）"  
        if ((OrderStatusEnum.CANCEL_ACCEPTED.equals(l_orderUnitRow.getOrderStatus()))
            || (OrderStatusEnum.CANCELLING.equals(l_orderUnitRow.getOrderStatus())))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //上記以外、trueを返却する。
        log.exiting(STR_METHOD_NAME);
        return true;
    }
    
    /**
     * (is遅延注文)<BR>
     * 遅延注文かどうか判別する。 <BR>
     * <BR>
     * true：　@発注遅延あり <BR>
     * false：　@発注遅延なし <BR>
     * <BR>
     * パラメータ.注文単位.発注遅延フラグ == "遅延あり"の場合、 <BR>
     * trueを返却する。以外、falseを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isDelayOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDelayOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //パラメータ.注文単位.発注遅延フラグ == "遅延あり"の場合、 
        //trueを返却する。以外、falseを返却する。
        if (BooleanEnum.TRUE.equals(l_orderUnitRow.getSubmitOrderDelayFlag()))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is未発注遅延注文)<BR>
     * 未発注（逆指値発注処理／W指値切替処理未済）の <BR>
     * 発注遅延注文かどうか判別する。 <BR>
     * <BR>
     * true：　@未発注の発注遅延注文 <BR>
     * false：　@未発注の発注遅延注文以外 <BR>
     * <BR>
     * this.is未発注注文(パラメータ.注文単位) == true　@かつ <BR>
     * this.is遅延注文(パラメータ.注文単位) == true <BR>
     * の場合、trueを返却する。 <BR>
     * 以外、falseを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isNotOrderedDelayOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotOrderedDelayOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        //this.is未発注注文(パラメータ.注文単位) == true　@かつ 
        //this.is遅延注文(パラメータ.注文単位) == true 
        //の場合、trueを返却する。 
        //以外、falseを返却する。
        if (this.isNotOrderedOrder(l_orderUnit) && this.isDelayOrder(l_orderUnit))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (is買注文)<BR>
     * 注文が買注文かどうかを判定する。 <BR>
     * <BR>
     * 注文が買注文の場合、trueを返却し、 <BR>
     * 注文が売注文の場合、falseを返却する。 <BR>
     * <BR>
     * １）　@買注文かどうかのチェック <BR>
     * 　@パラメータ.注文単位.getSide()の戻り値 == SideEnum.BUYの場合、 <BR>
     * 　@trueを返却する。 <BR>
     * <BR>
     * 　@パラメータ.注文単位.getSide()の戻り値 == SideEnum.SELLの場合、 <BR>
     * 　@falseを返却する。 <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isBuyOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isBuyOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        
        boolean l_blnIsBuyOrder = false;
        
        //１）　@買注文かどうかのチェック 
        //　@パラメータ.注文単位.getSide()の戻り値 == SideEnum.BUYの場合、 
        //　@trueを返却する。 
        if (SideEnum.BUY.equals(l_orderUnit.getSide()))
        {
            l_blnIsBuyOrder = true;
        }
        
        //　@パラメータ.注文単位.getSide()の戻り値 == SideEnum.SELLの場合、 
        //　@falseを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_blnIsBuyOrder;
    }
    
    /**
     * (is売注文)<BR>
     * 注文が売注文かどうかを判定する。 <BR>
     * <BR>
     * 注文が売注文の場合、trueを返却し、 <BR>
     * 注文が買注文の場合、falseを返却する。 <BR>
     * <BR>
     * １）　@売注文かどうかのチェック <BR>
     * 　@this.is買注文(注文単位)をcallする。 <BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@　@パラメータ.注文単位 <BR>
     * <BR>
     * ２）　@戻り値の判定 <BR>
     * 　@[１）の戻り値がfalseの場合] <BR>
     * 　@　@trueを返却する。 <BR>
     * <BR>
     * 　@[１）の戻り値がtrueの場合] <BR>
     * 　@　@falseを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isSellOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSellOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsSellOrder = true; 
        //１）　@売注文かどうかのチェック 
        //this.is買注文(注文単位)をcallする。
        if (this.isBuyOrder(l_orderUnit))
        {
            //２）　@戻り値の判定 
            //　@[１）の戻り値がtrueの場合] 
            //　@　@falseを返却する。
            l_blnIsSellOrder = false;
        }
        else
        {
            //　@[１）の戻り値がfalseの場合] 
            //　@　@trueを返却する。 
            l_blnIsSellOrder = true;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnIsSellOrder;
    }
    
    /**
     * (is未発注注文)<BR>
     * 未発注（逆指値発注処理／W指値切替処理未済）の注文か判定する。 <BR>
     * <BR>
     * true：　@未発注の注文 <BR>
     * false：　@発注済の注文 <BR>
     * <BR>
     * パラメータ.注文単位.リクエストタイプが <BR>
     * 以下に該当する場合、trueを返却する。 <BR>
     * 　@　@・"DEFAULT" <BR>
     * <BR>
     * 上記以外の場合、falseを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     */
    public boolean isNotOrderedOrder(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isNotOrderedOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               getClass().getName() + "." + STR_METHOD_NAME,
               "パラメータ値不正。");
        }
        //パラメータ.注文単位.リクエストタイプが 
        //以下に該当する場合、trueを返却する。
        //　@　@・"DEFAULT"
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
     * (create注文履歴)<BR>
     * パラメータ.注文単位オブジェクトを使用し、注文履歴オブジェクトを生成する。<BR>
     * <BR>
     * １）　@注文履歴Paramsを生成し、以下更新内容を設定する。<BR>
     * <BR>
     * 　@　@注文イベントタイプ：　@パラメータ.イベントタイプ<BR>
     * 　@　@約定数量：　@null<BR>
     * 　@　@約定単価：　@null<BR>
     * 　@　@注文単価：　@注文単位.指値<BR>
     * 　@　@注文失効ステータス：　@注文単位.失効区分<BR>
     * 　@　@注文履歴番号：　@注文単位.注文履歴最終通番<BR>
     * 　@　@注文エラー理由コード：　@"0000"（正常）<BR>
     * 　@　@IPアドレス：　@null<BR>
     * 　@　@作成日付：　@現在日時<BR>
     * 　@　@更新日付：　@現在日時<BR>
     * <BR>
     * 　@　@※上記以外の項目については、<BR>
     * 　@　@注文単位オブジェクトの同名項目の値を設定。<BR>
     * <BR>
     * ２）　@拡張株式注文マネージャ.toOrderAction()をコールし、<BR>
     * 　@　@　@注文履歴オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@[toOrderAction()の引数]<BR>
     * 　@　@Row：　@注文履歴Params<BR>
     * <BR>
     * ３）　@注文履歴オブジェクトを返却する。<BR>
     * @@param l_eqTypeOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_orderEventType - (イベントタイプ)<BR>
     * イベントタイプ<BR>
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
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@注文履歴Paramsを生成し、以下更新内容を設定する。
        EqtypeOrderActionParams l_orderActionParams = new EqtypeOrderActionParams();
        EqtypeOrderUnitRow  l_orderUnitRow =
            (EqtypeOrderUnitRow)l_eqTypeOrderUnit.getDataSourceObject();
        //注文イベントタイプ：　@パラメータ.イベントタイプ
        l_orderActionParams.setOrderEventType(l_orderEventType);
        //約定数量：　@null
        l_orderActionParams.setExecutedQuantity(null);
        //　@約定単価：　@null
        l_orderActionParams.setExecutedPrice(null);
        //　@注文単価：　@注文単位.指値
        if (l_orderUnitRow.getLimitPriceIsNull())
        {
            l_orderActionParams.setPrice(null);
        }
        else
        {
            l_orderActionParams.setPrice(l_orderUnitRow.getLimitPrice());
        }
        //　@注文失効ステータス：　@注文単位.失効区分
        l_orderActionParams.setExpirationStatus(l_orderUnitRow.getExpirationStatus());
        //　@注文履歴番号：　@注文単位.注文履歴最終通番
        l_orderActionParams.setOrderActionSerialNo(l_orderUnitRow.getLastOrderActionSerialNo());
        //　@注文エラー理由コード：　@"0000"（正常）
        l_orderActionParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);
        //　@IPアドレス：　@null
        l_orderActionParams.setIpAddress(null);
        Timestamp l_tsSysTime = GtlUtils.getSystemTimestamp();
        //　@作成日付：　@現在日時
        l_orderActionParams.setCreatedTimestamp(l_tsSysTime);
        //　@更新日付：　@現在日時
        l_orderActionParams.setLastUpdatedTimestamp(l_tsSysTime);

        //上記以外の項目については、注文単位オブジェクトの同名項目の値を設定。
        //注文履歴ＩＤ
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
        //口座ＩＤ
        l_orderActionParams.setAccountId(l_orderUnitRow.getAccountId());
        //補助口座ＩＤ
        l_orderActionParams.setSubAccountId(l_orderUnitRow.getSubAccountId());
        //取引者ＩＤ
        if (l_orderUnitRow.getTraderIdIsNull())
        {
            l_orderActionParams.setTraderId(null);
        }
        else
        {
            l_orderActionParams.setTraderId(l_orderUnitRow.getTraderId());
        }
        //注文ＩＤ
        l_orderActionParams.setOrderId(l_orderUnitRow.getOrderId());
        //注文単位ＩＤ
        l_orderActionParams.setOrderUnitId(l_orderUnitRow.getOrderUnitId());
        //市場ＩＤ
        if (l_orderUnitRow.getMarketIdIsNull())
        {
            l_orderActionParams.setMarketId(null);
        }
        else
        {
            l_orderActionParams.setMarketId(l_orderUnitRow.getMarketId());
        }
        //注文種別
        l_orderActionParams.setOrderType(l_orderUnitRow.getOrderType());
        //執行条件
        l_orderActionParams.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType());
        //値段条件
        l_orderActionParams.setPriceConditionType(l_orderUnitRow.getPriceConditionType());
        //発注条件
        l_orderActionParams.setOrderConditionType(l_orderUnitRow.getOrderConditionType());
        //発注条件演算子
        l_orderActionParams.setOrderCondOperator(l_orderUnitRow.getOrderCondOperator());
        //逆指値基準値
        if (l_orderUnitRow.getStopOrderPriceIsNull())
        {
            l_orderActionParams.setStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setStopOrderPrice(l_orderUnitRow.getStopOrderPrice());
        }
        //（W指値）訂正指値
        if (l_orderUnitRow.getWLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitPrice(l_orderUnitRow.getWLimitPrice());
        }
        //注文失効日付
        l_orderActionParams.setExpirationDate(l_orderUnitRow.getExpirationDate());
        //注文数量
        l_orderActionParams.setQuantity(l_orderUnitRow.getQuantity());
        //市場と確認済みの指値
        if (l_orderUnitRow.getConfirmedPriceIsNull())
        {
            l_orderActionParams.setConfirmedPrice(null);
        }
        else
        {
            l_orderActionParams.setConfirmedPrice(l_orderUnitRow.getConfirmedPrice());
        }
        //市場と確認済みの数量
        if (l_orderUnitRow.getConfirmedQuantityIsNull())
        {
            l_orderActionParams.setConfirmedQuantity(null);
        }
        else
        {
            l_orderActionParams.setConfirmedQuantity(l_orderUnitRow.getConfirmedQuantity());
        }
        //注文状態
        l_orderActionParams.setOrderStatus(l_orderUnitRow.getOrderStatus());
        //銘柄タイプ
        l_orderActionParams.setProductType(l_orderUnitRow.getProductType());
        //銘柄ＩＤ
        l_orderActionParams.setProductId(l_orderUnitRow.getProductId());
        //注文数量タイプ
        l_orderActionParams.setQuantityType(l_orderUnitRow.getQuantityType());
        //概算受渡代金
        if (l_orderUnitRow.getEstimatedPriceIsNull())
        {
            l_orderActionParams.setEstimatedPrice(null);
        }
        else
        {
            l_orderActionParams.setEstimatedPrice(l_orderUnitRow.getEstimatedPrice());
        }
        //注文訂正・取消区分
        l_orderActionParams.setModifyCancelType(l_orderUnitRow.getModifyCancelType());
        //注文経路区分
        l_orderActionParams.setOrderRootDiv(l_orderUnitRow.getOrderRootDiv());
        //決済順序区分
        l_orderActionParams.setClosingOrderType(l_orderUnitRow.getClosingOrderType());
        //リクエストタイプ
        l_orderActionParams.setRequestType(l_orderUnitRow.getRequestType());
        //元発注条件
        l_orderActionParams.setOrgOrderConditionType(l_orderUnitRow.getOrgOrderConditionType());
        //元発注条件演算子
        l_orderActionParams.setOrgOrderCondOperator(l_orderUnitRow.getOrgOrderCondOperator());
        //元逆指値基準値
        if (l_orderUnitRow.getOrgStopOrderPriceIsNull())
        {
            l_orderActionParams.setOrgStopOrderPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgStopOrderPrice(l_orderUnitRow.getOrgStopOrderPrice());
        }
        //元（W指値）訂正指値
        if (l_orderUnitRow.getOrgWLimitPriceIsNull())
        {
            l_orderActionParams.setOrgWLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setOrgWLimitPrice(l_orderUnitRow.getOrgWLimitPrice());
        }
        //元（W指値）執行条件
        l_orderActionParams.setOrgWLimitExecCondType(l_orderUnitRow.getOrgWLimitExecCondType());
        //（W指値）執行条件
        l_orderActionParams.setWLimitExecCondType(l_orderUnitRow.getWLimitExecCondType());
        //（W指値）切替前指値
        if (l_orderUnitRow.getWLimitBeforeLimitPriceIsNull())
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(null);
        }
        else
        {
            l_orderActionParams.setWLimitBeforeLimitPrice(l_orderUnitRow.getWLimitBeforeLimitPrice());
        }
        //（W指値）切替前執行条件
        l_orderActionParams.setWLimitBeforeExecCondType(l_orderUnitRow.getWLimitBeforeExecCondType());
        //市場から確認済みの執行条件
        l_orderActionParams.setConfirmedExecConditionType(l_orderUnitRow.getConfirmedExecConditionType());

        //２）　@拡張株式注文マネージャ.toOrderAction()をコールし、
        //　@注文履歴オブジェクトを生成する。
        EqTypeOrderAction l_orderAction = (EqTypeOrderAction)this.toOrderAction(l_orderActionParams);
        log.exiting(STR_METHOD_NAME);
        return l_orderAction;
    }

    /**
     * (update注文データ)<BR>
     * 指定された注文オブジェクトを使用し、QueryProcessorにより注文データ類の更新を行う。<BR>
     * ※注文履歴を作成する場合は、<BR>
     * 　@注文単位.注文履歴最終通番を呼び出し側でカウントアップすること。<BR>
     * <BR>
     * １）　@注文ID == 引数の注文単位.注文IDに該当する注文（ヘッダ）テーブルをupdateする。<BR>
     * <BR>
     * 　@　@更新日付に、引数の注文単位オブジェクトの同項目の内容をセットしてupdateする。<BR>
     * <BR>
     * ２）　@引数の注文単位オブジェクトの内容で注文単位テーブルをupdateする。<BR>
     * <BR>
     * ３）　@引数の注文履歴が"null"でない場合のみ、<BR>
     * 　@　@引数の注文履歴オブジェクトの内容で注文履歴テーブルにinsertする。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_orderAction - (注文履歴)<BR>
     * 注文履歴<BR>
     * @@throws WEB3BaseException
     */
    public void updateOrderData(EqTypeOrderUnit l_orderUnit, EqTypeOrderAction l_orderAction)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderData(EqTypeOrderUnit, EqTypeOrderAction)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }
        try
        {
            //１）　@注文ID == 引数の注文単位.注文IDに該当する注文（ヘッダ）テーブルをupdateする。
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
            //２）引数の注文単位オブジェクトの内容で注文単位テーブルをupdateする
            l_processor.doUpdateQuery(l_orderUnitRow);
            //３）　@引数の注文履歴が"null"でない場合のみ、
            //引数の注文履歴オブジェクトの内容で注文履歴テーブルにinsertする。
            if (l_orderAction != null)
            {
                EqtypeOrderActionRow l_orderActionRow =
                    (EqtypeOrderActionRow)l_orderAction.getDataSourceObject();
                l_processor.doInsertQuery(l_orderActionRow);
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
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
     * (getストップ注文失効時概算代金計算結果)<BR>
     * ストップ注文失効時に、リミット注文の概算代金再計算を行う。<BR>
     *（現物買付、信用新規建注文のみ再計算を行う。）<BR>
     * ※W指値注文でリミット注文有効の場合のみ再計算可能。<BR>
     * <BR>
     * １）　@以下の分岐で再計算処理を行う。<BR>
     *　@　@　@１－１）パラメータ.注文単位.注文カテゴリ = "現物注文"の場合 <BR>
     *　@　@　@　@getストップ注文失効時概算代金計算結果（現物）（）をコールし、 <BR>
     *　@　@　@　@取得した概算受渡代金計算結果を概算代金計算結果に設定する。 <BR>
     *　@　@　@　@[引数] <BR>
     *　@　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     *　@　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * <BR>
     *　@　@　@１－２）パラメータ.注文単位.注文カテゴリ = "新規建注文"の場合 <BR>
     *　@　@　@　@getストップ注文失効時概算代金計算結果（信用新規建）（）をコールし、<BR>
     *　@　@　@　@取得した概算建代金計算結果を概算代金計算結果に設定する。 <BR>
     *　@　@　@　@[引数] <BR>
     *　@　@　@　@注文単位：　@パラメータ.注文単位 <BR>
     *　@　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     * <BR>
     *　@　@　@１－３）上記以外の場合 <BR>
     *　@　@　@概算代金計算結果にnullを設定する。<BR>
     * <BR>
     * ２）概算代金計算結果オブジェクトを返却する<BR>
     * @@param l_eqOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
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
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }
        WEB3EquityEstimatedPrice l_equityEstimatedPrice = null;
        //１）　@以下の分岐で再計算処理を行う。
        //　@　@　@１－１）パラメータ.注文単位.注文カテゴリ = "現物注文"の場合
        //　@　@　@　@getストップ注文失効時概算代金計算結果（現物）（）をコールし、
        //　@　@　@　@取得した概算受渡代金計算結果を概算代金計算結果に設定する。
        //　@　@　@　@[引数]
        //　@　@　@　@注文単位：　@パラメータ.注文単位
        //　@　@　@　@補助口座：　@パラメータ.補助口座
        if (OrderCategEnum.ASSET.equals(l_eqOrderUnit.getOrderCateg()))
        {
            l_equityEstimatedPrice =
                this.getStopOrderExpireEstimatedPriceForEquity(
                    l_eqOrderUnit,
                    l_subAccount);
        }

        //　@　@　@１－２）パラメータ.注文単位.注文カテゴリ = "新規建注文"の場合
        //　@　@　@　@getストップ注文失効時概算代金計算結果（信用新規建）（）をコールし、
        //　@　@　@　@取得した概算建代金計算結果を概算代金計算結果に設定する。
        //　@　@　@　@[引数]
        //　@　@　@　@注文単位：　@パラメータ.注文単位
        //　@　@　@　@補助口座：　@パラメータ.補助口座
        else if (OrderCategEnum.OPEN_MARGIN.equals(l_eqOrderUnit.getOrderCateg()))
        {
            l_equityEstimatedPrice =
                this.getStopOrderExpireEstimatedPriceForMargin(
                    l_eqOrderUnit,
                    l_subAccount);
        }

        //　@　@　@１－３）上記以外の場合
        //　@　@ 　@概算代金計算結果にnullを設定する。
        //
        //２）概算代金計算結果オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_equityEstimatedPrice;
    }

    /**
     * (getストップ注文失効時概算代金計算結果（現物）)<BR>
     * リミット注文の概算受渡代金再計算を行う。<BR>
     * <BR>
     * １）　@パラメータ.注文単位.注文種別が"現物売注文"の場合<BR>
     *　@　@　@nullを返却する。<BR>
     * <BR>
     * ２）　@１）の条件に該当しない場合<BR>
     *　@　@　@以下手順で概算代金計算結果を取得する。<BR>
     * <BR>
     *　@２-１）　@パラメータ.注文単位.getDataSourceObject()をコールする。<BR>
     * <BR>
     *　@２-２）　@株式計算サービス.create手数料()をコールする。<BR>
     * <BR>
     *　@　@　@　@　@[引数]<BR>
     *　@　@　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     *　@２-３）　@拡張株式注文マネージャ.calc概算受渡代金()をコールする。<BR>
     * <BR>
     *　@　@　@　@　@[引数]<BR>
     *　@　@　@　@　@手数料：　@手数料オブジェクト<BR>
     *　@　@　@　@　@指値：　@<BR>
     *　@　@　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値 != NULLの場合<BR>
     *　@　@　@　@　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値<BR>
     *　@　@　@　@　@　@　@　@上記以外の場合<BR>
     *　@　@　@　@　@　@　@　@　@　@パラメータ.注文単位.指値 <BR>
     *　@　@　@　@　@（W指値)訂正指値：　@注文単位Row.（W指値）訂正指値 <BR>
     *　@　@　@　@　@逆指値基準値：　@注文単位Row.逆指値基準値 <BR>
     *　@　@　@　@　@執行条件：<BR>
     *　@　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件 != NULLの場合<BR>
     *　@　@　@　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件 <BR>
     *　@　@　@　@　@　@　@　@上記以外の場合 <BR>
     *　@　@　@　@　@　@　@　@　@　@注文単位Row.執行条件 <BR>
     *　@　@　@　@　@（W指値）執行条件：　@注文単位Row.（W指値）執行条件<BR>
     *　@　@　@　@　@値段条件：　@注文単位Row.値段条件 <BR>
     *　@　@　@　@　@発注条件：　@"DEFAULT"（固定） <BR>
     *　@　@　@　@　@確認時取得時価：　@0（固定） <BR>
     *　@　@　@　@　@isストップ注文有効：　@false（固定） <BR>
     *　@　@　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     *　@　@　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct()<BR>
     *　@　@　@　@　@株数：　@パラメータ.注文単位.注文数量<BR>
     *　@　@　@　@　@is売注文：　@false（固定）<BR>
     *　@　@　@　@　@約定数量：　@パラメータ.注文単位.約定数量<BR>
     *　@　@　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額<BR>
     *　@　@　@　@　@isSkip金額チェック：　@true（固定）<BR>
     * <BR>
     * ３）　@概算受渡代金計算結果オブジェクトを返却する。<BR>
     * @@param l_eqOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
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
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }

        //１）　@パラメータ.注文単位.注文種別が"現物売注文"の場合
        //　@　@　@nullを返却する。
        if (OrderTypeEnum.EQUITY_SELL.equals(l_eqOrderUnit.getOrderType()))
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //２）　@１）の条件に該当しない場合
        //　@　@　@以下手順で概算代金計算結果を取得する。
        //　@２-１）　@パラメータ.注文単位.getDataSourceObject()をコールする。
        EqtypeOrderUnitRow l_eqTypeOrderUntiRow =
            (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        //　@２-２）　@株式計算サービス.create手数料()をコールする。
        //　@　@　@　@　@[引数]
        //　@　@　@　@　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqOrderUnit);

        double l_dblLimitPrice = 0D;
        EqTypeExecutionConditionType l_executionConditionType = null;
        //パラメータ.注文単位.市場から確認済みの指値 != NULLの場合
        //　@　@パラメータ.注文単位.市場から確認済みの指値
        //上記以外の場合
        //　@　@パラメータ.注文単位.指値
        if (!l_eqTypeOrderUntiRow.getConfirmedPriceIsNull())
        {
            l_dblLimitPrice = l_eqTypeOrderUntiRow.getConfirmedPrice();
        }
        else
        {
            l_dblLimitPrice = l_eqTypeOrderUntiRow.getLimitPrice();
        }
        //注文単位Row.市場から確認済みの執行条件 != NULLの場合
        //　@　@注文単位Row.市場から確認済みの執行条件
        //上記以外の場合
        //　@　@注文単位Row.執行条件
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
        //　@２-３）　@拡張株式注文マネージャ.calc概算受渡代金()をコールする。
        //　@　@　@　@　@[引数]
        //　@　@　@　@　@手数料：　@手数料オブジェクト
        //　@　@　@　@　@指値：
        //　@　@　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値 != NULLの場合
        //　@　@　@　@　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値
        //　@　@　@　@　@　@　@　@上記以外の場合
        //　@　@　@　@　@　@　@　@　@　@パラメータ.注文単位.指値
        //　@　@　@　@　@（W指値)訂正指値：　@注文単位Row.（W指値）訂正指値
        //　@　@　@　@　@逆指値基準値：　@注文単位Row.逆指値基準値
        //　@　@　@　@　@執行条件：
        //　@　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件 != NULLの場合
        //　@　@　@　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件
        //　@　@　@　@　@　@　@　@上記以外の場合
        //　@　@　@　@　@　@　@　@　@　@注文単位Row.執行条件
        //　@　@　@　@　@（W指値）執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@　@　@値段条件：　@注文単位Row.値段条件
        //　@　@　@　@　@発注条件：　@"DEFAULT"（固定）
        //　@　@　@　@　@確認時取得時価：　@0（固定）
        //　@　@　@　@　@isストップ注文有効：　@false（固定）
        //　@　@　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct()
        //　@　@　@　@　@株数：　@パラメータ.注文単位.注文数量
        //　@　@　@　@　@is売注文：　@false（固定）
        //　@　@　@　@　@約定数量：　@パラメータ.注文単位.約定数量
        //　@　@　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額
        //　@　@　@　@　@isSkip金額チェック：　@true（固定）
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

        //３）　@概算受渡代金計算結果オブジェクトを返却する。  
        log.exiting(STR_METHOD_NAME);
        return l_calcEstimateDeliveryAmount;
    }

    /**
     * (getストップ注文失効時概算代金計算結果（信用新規建）)<BR>
     * リミット注文の概算建代金再計算を行う。<BR>
     * <BR>
     * １）　@パラメータ.注文単位.getDataSourceObject()をコールする。<BR>
     * <BR>
     * ２）　@株式計算サービス.create手数料()をコールする。<BR>
     * <BR>
     *　@　@　@[引数]<BR>
     *　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ３）　@拡張株式注文マネージャ.is売注文()をコールする。<BR>
     * <BR>
     *　@　@　@[引数]<BR>
     *　@　@　@注文単位：　@パラメータ.注文単位<BR>
     * <BR>
     * ４）　@拡張株式注文マネージャ.calc注文時建代金()をコールする。<BR>
     * <BR>
     *　@　@　@[引数]<BR>
     *　@　@　@手数料：　@手数料オブジェクト<BR>
     *　@　@　@指値： <BR>
     *　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値 != NULLの場合<BR>
     *　@　@　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値<BR>
     *　@　@　@　@　@　@上記以外の場合 <BR>
     *　@　@　@　@　@　@　@　@パラメータ.注文単位.指値<BR>
     *　@　@　@（W指値）訂正指値：　@注文単位Row.（W指値）訂正指値<BR>
     *　@　@　@逆指値基準値：　@注文単位Row.逆指値基準値<BR>
     *　@　@　@執行条件： <BR>
     *　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件 != NULLの場合  <BR>
     *　@　@　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件 <BR>
     *　@　@　@　@　@　@　@上記以外の場合 <BR>
     *　@　@　@　@　@　@　@　@　@注文単位Row.執行条件  <BR>
     *　@　@　@（W指値）執行条件：　@注文単位Row.（W指値）執行条件 <BR>
     *　@　@　@値段条件：　@注文単位Row.値段条件 <BR>
     *　@　@　@発注条件：　@"DEFAULT"（固定）<BR>
     *　@　@　@確認時取得時価：　@0（固定）<BR>
     *　@　@　@isストップ注文有効：　@false（固定）<BR>
     *　@　@　@is売建：　@is売注文()の戻り値 <BR>
     *　@　@　@補助口座：　@パラメータ.補助口座 <BR>
     *　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct() <BR>
     *　@　@　@株数：　@パラメータ.注文単位.注文数量 <BR>
     *　@　@　@約定数量：　@パラメータ.注文単位.約定数量 <BR>
     *　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額 <BR>
     *　@　@　@isSkip金額チェック：　@true（固定） <BR>
     * <BR>
     * ５）　@概算建代金計算結果オブジェクトを返却する。<BR>
     * @@param l_eqOrderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
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
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3EquityOrderManager." + STR_METHOD_NAME ,
                "パラメータ値不正。");
        }
        //１）　@パラメータ.注文単位.getDataSourceObject()をコールする。
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow =
            (EqtypeOrderUnitRow)l_eqOrderUnit.getDataSourceObject();

        //２）　@株式計算サービス.create手数料()をコールする。
        //　@　@　@[引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_bizLogicProvider.createCommission((EqTypeOrderUnitImpl)l_eqOrderUnit);

        //３）　@拡張株式注文マネージャ.is売注文()をコールする。
        //　@　@　@[引数]
        //　@　@　@注文単位：　@パラメータ.注文単位
        boolean l_blnSellOrder = this.isSellOrder(l_eqOrderUnit);

        double l_dblLimitPrice = 0D;
        EqTypeExecutionConditionType l_executionConditionType = null;
        //パラメータ.注文単位.市場から確認済みの指値 != NULLの場合
        //　@　@パラメータ.注文単位.市場から確認済みの指値
        //上記以外の場合
        //　@　@パラメータ.注文単位.指値
        if (!l_eqtypeOrderUnitRow.getConfirmedPriceIsNull())
        {
            l_dblLimitPrice = l_eqtypeOrderUnitRow.getConfirmedPrice();
        }
        else
        {
            l_dblLimitPrice = l_eqtypeOrderUnitRow.getLimitPrice();
        }
        //注文単位Row.市場から確認済みの執行条件 != NULLの場合
        //　@　@注文単位Row.市場から確認済みの執行条件
        //上記以外の場合
        //　@　@注文単位Row.執行条件
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
        //４）　@拡張株式注文マネージャ.calc注文時建代金()をコールする。
        //　@　@　@[引数]
        //　@　@　@手数料：　@手数料オブジェクト
        //　@　@　@指値：
        //　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値 != NULLの場合
        //　@　@　@　@　@　@　@　@パラメータ.注文単位.市場から確認済みの指値
        //　@　@　@　@　@　@上記以外の場合
        //　@　@　@　@　@　@　@　@パラメータ.注文単位.指値
        //　@　@　@（W指値）訂正指値：　@注文単位Row.（W指値）訂正指値
        //　@　@　@逆指値基準値：　@注文単位Row.逆指値基準値
        //　@　@　@執行条件：
        //　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件 != NULLの場合
        //　@　@　@　@　@　@　@　@　@注文単位Row.市場から確認済みの執行条件
        //　@　@　@　@　@　@　@上記以外の場合
        //　@　@　@　@　@　@　@　@　@注文単位Row.執行条件
        //　@　@　@（W指値）執行条件：　@注文単位Row.（W指値）執行条件
        //　@　@　@値段条件：　@注文単位Row.値段条件
        //　@　@　@発注条件：　@"DEFAULT"（固定）
        //　@　@　@確認時取得時価：　@0（固定）
        //　@　@　@isストップ注文有効：　@false（固定）
        //　@　@　@is売建：　@is売注文()の戻り値
        //　@　@　@補助口座：　@パラメータ.補助口座
        //　@　@　@取引銘柄：　@パラメータ.注文単位.getTradedProduct()
        //　@　@　@株数：　@パラメータ.注文単位.注文数量
        //　@　@　@約定数量：　@パラメータ.注文単位.約定数量
        //　@　@　@合計約定金額：　@パラメータ.注文単位.合計約定金額
        //　@　@　@isSkip金額チェック：　@true（固定）
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

        //５）　@概算建代金計算結果オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_calcContractAmountAtOrder;
    }
    
    /**
     * (is強制決済注文)<BR>
     * 引数の注文単位が強制決済注文か否かを判定する。 <BR>
     * <BR>
     * true：　@強制決済注文 <BR>
     * false：　@強制決済注文ではない <BR>
     * <BR>
     * 引数.注文単位.強制決済理由区分 != nullの場合、 <BR>
     * trueを返却する。 <BR>
     * <BR>
     * 以外の場合、falseを返却する。<BR>
     * @@param l_orderUnit - 注文単位
     * @@return boolean
     */
    public boolean isForcedSettleOrder(EqTypeOrderUnit l_orderUnit)
    {
        String STR_METHOD_NAME = "isForcedSettleOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnFlag = false;

        //引数.注文単位.強制決済理由区分 != nullの場合、
        //trueを返却する。
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
     * (is未承認強制決済注文)<BR>
     * 引数の注文単位が未承認の強制決済注文か否かを判定する。 <BR>
     * <BR>
     * true：　@未承認の強制決済注文 <BR>
     * false：　@強制決済注文ではない<BR>
     * 　@　@　@　@　@または、承認済の強制決済注文<BR>
     * <BR>
     * 　@this.is強制決済注文() == trueかつ、 <BR>
     * 　@引数.注文単位.承認状態区分 != "承認済"の場合、trueを返却する。 <BR>
     * <BR>
     * 　@[is強制決済注文()の引数] <BR>
     * 　@　@引数.注文単位 <BR>
     * <BR>
     * 　@以外の場合、falseを返却する。 <BR>
     * @@param l_orderUnit - 注文単位
     * @@return boolean
     */
    public boolean isApproveForcedSettleOrder(EqTypeOrderUnit l_orderUnit)
    {
        String STR_METHOD_NAME = "isApproveForcedSettleOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);

        boolean l_blnFlag = false;

        //this.is強制決済注文() == trueかつ、
        //引数.注文単位.承認状態区分 != "承認済"の場合、trueを返却する。
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
     * (get注文有効期限)<BR>
     * 注文有効期限最終日のみ指定可会社の場合、<BR>
     * 引数に設定された注文有効期限と銘柄の権利付き最終日を比較して<BR>
     * 小さい方の値を返却する。<BR>
     * <BR>
     * ※上り処理限定で使用すること。<BR>
     * <BR>
     * １）　@部店.is権利付き最終日チェック()の戻り値がfalseの場合、 <BR>
     * 　@　@パラメータ.注文有効期限を返却する。 <BR>
     * 　@　@以外の場合、以下を実行する。 <BR>
     * <BR>
     * ２）　@拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。<BR>
     * 　@　@　@[getProduct()に設定する引数] <BR>
     * 　@　@　@　@証券会社：　@ログインセッションより取得した証券会社 <BR>
     * 　@　@　@　@銘柄コード　@　@：　@パラメータ.銘柄コード  <BR>
     *  <BR>
     * ３）　@取扱可能注文条件を生成する。 <BR>
     * 　@　@　@[コンストラクタに設定する引数] <BR>
     *  　@　@　@　@証券会社コード：　@ログインセッションより取得した証券会社コード <BR>
     *  　@　@　@　@銘柄タイプ：　@”株式”  <BR>
     *  　@　@　@　@先物／オプション区分：　@”DEFAULT”  <BR>
     *  　@　@　@　@信用取引区分：　@”DEFAULT”(固定)  <BR>
     *  　@　@　@　@市場コード：　@パラメータ.市場コード <BR>
     * <BR>
     * ４）　@銘柄の権利付き最終日が、現在日時より算出した発注日より後の場合のみ以下を実施。<BR>
     * 　@　@　@（取引時間管理.get発注日 <= 株式銘柄.get権利付き最終日）<BR>
     * <BR>
     * 　@４－１）　@取扱可能注文条件.set取引最終日()をコールする。<BR>
     * 　@　@　@　@　@　@[set取引最終日()に設定する引数]<BR>
     * 　@　@　@　@　@　@取引最終日：　@株式銘柄.get権利付き最終日<BR>
     * <BR>
     * ５）　@取扱可能注文条件.get出来るまで注文失効日指定にて最終日指定区分を取得する。<BR>
     * <BR>
     * ６）　@最終日指定区分が『1：最終日のみ指定可』の場合、<BR>
     * 　@　@取扱可能注文条件.get出来るまで注文最終日<取引最終日考慮>()の戻り値を返却する。<BR>
     * 　@　@［get出来るまで注文最終日<取引最終日考慮>()］  <BR>
     * 　@　@　@原注文発注日：　@nullをセット <BR>
     * <BR>
     * ７）　@最終日指定区分が『0：期間内失効日ユーザ指定可』の場合、<BR>
     * 　@　@パラメータ.注文有効期限を返却する。 <BR>
     * <BR>
     * @@param l_datExpirationDate - 注文有効期限 <BR>
     * 注文有効期限 <BR>
     * <BR>
     * @@param l_strProductCode - 銘柄コード <BR>
     * 銘柄コード <BR>
     * <BR>
     * @@param l_strMarketCode - 市場コード <BR>
     * 市場コード <BR>
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

        // ログインセッションより取得した証券会社
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(
                OpLoginSecurityService.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        // 拡張プロダクトマネージャ
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

        // １）　@部店.is権利付き最終日チェック()の戻り値がfalseの場合、
        if (!l_branch.isEqtypeFinalDayWithRight())
        {
            // パラメータ.注文有効期限を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }

        //2) 拡張プロダクトマネージャ.getProduct()をコールし、株式銘柄を取得する。
        // [getProduct()に設定する引数]
        // 証券会社：　@ログインセッションより取得した証券会社
        // 銘柄コード　@　@：　@パラメータ.銘柄コード
        try
        {
            l_strInstitutionCode = l_institution.getInstitutionCode();

            // 株式銘柄を取得
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

        //３）　@取扱可能注文条件を生成する。
        //[コンストラクタに設定する引数]
        //証券会社コード：　@ログインセッションより取得した証券会社コード
        //銘柄タイプ：　@”株式”
        //先物／オプション区分：　@”DEFAULT”
        //信用取引区分：　@”DEFAULT”(固定)
        //市場コード：　@パラメータ.市場コード
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                ProductTypeEnum.EQUITY,
                WEB3FuturesOptionDivDef.DEFAULT,
                WEB3MarginTradingDivDef.DEFAULT,
                l_strMarketCode);

        //４）　@銘柄の権利付き最終日が、現在日時より算出した発注日より後の場合のみ以下を実施。
        //（取引時間管理.get発注日 <= 株式銘柄.get権利付き最終日）
        //取引最終日：　@株式銘柄.get権利付き最終日
        Date l_datRightCondOrderEndDay = l_equityProduct.getRightCondOrderEndDay();
        Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        if (WEB3DateUtility.compare(l_datOrderBizDate, l_datRightCondOrderEndDay) <= 0)
        {
            //取扱可能注文条件.set取引最終日()をコールする。
            l_handlingOrderCond.setTradingEndDate(l_datRightCondOrderEndDay);
        }

        // ５）　@取扱可能注文条件.get出来るまで注文失効日指定にて最終日指定区分を取得する。
        String l_strOrderUntilDeadLineExp = l_handlingOrderCond.getOrderUntilDeadLineExpDay();

        //６）　@最終日指定区分が『1：最終日のみ指定可』の場合、
        //取扱可能注文条件.get出来るまで注文最終日<取引最終日考慮>()の戻り値を返却する。
        //［get出来るまで注文最終日<取引最終日考慮>()］
        //原注文発注日：　@nullをセット
        if (WEB3CarriedOrderLapseDateSpecDef.FINAL_DATE_DESIGNATA.equals(l_strOrderUntilDeadLineExp))
        {
            log.exiting(STR_METHOD_NAME);
            return l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);
        }
        else
        {
            // ７）　@最終日指定区分が『0：期間内失効日ユーザ指定可』の場合、
            // パラメータ.注文有効期限を返却する。
            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }

    /**
     * Commissionのクローン
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
     * (throw発注審査結果エラー情報) <BR>
     * 発注審査の結果が失敗の場合、例外をthrowする。<BR>
     * <BR>
     * 「権利付き最終日後の失効日指定不可。」エラーの場合、<BR>
     * 権利付き最終日を取得して追加メッセージにセットし、例外をthrowする。<BR>
     * <BR>
     * <BR>
     * １）　@発注審査が正常終了した場合<BR>
     * 　@引数.発注審査結果.getProcessingResult().isSuccessfulResult() ＝ trueの場合、<BR>
     * 　@何もしないで処理を終了する。（return;）<BR>
     * <BR>
     * ２）　@発注審査が失敗した場合<BR>
     * 　@２－１）　@権利付き最終日チェックでエラーとなった場合<BR>
     * 　@　@引数.発注審査結果.getProcessingResult().getErrorInfo()の戻り値が、<BR>
     * 　@　@"権利付き最終日後の失効日指定不可。"（BUSINESS_ERROR_02836）の場合、<BR>
     * 　@　@次の処理を行う。<BR>
     * <BR>
     * 　@　@２－１－１）　@株式銘柄の取得<BR>
     * 　@　@　@拡張プロダクトマネージャ.getProduct()をcallする。<BR>
     * <BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@　@証券会社：　@引数.証券会社<BR>
     * 　@　@　@　@銘柄コード：　@引数.銘柄コード<BR>
     * <BR>
     * 　@　@２－１－２）　@権利付き最終日の取得<BR>
     * 　@　@　@２－１－１）で取得した株式銘柄.get権利付き最終日()をcallする。<BR>
     * <BR>
     * 　@　@２－１－３）　@例外処理<BR>
     * 　@　@　@発注審査結果からWEB3BusinessLayerExceptionを生成し、throwする。<BR>
     * <BR>
     * 　@　@　@[コンストラクタの引数]<BR>
     * 　@　@　@　@エラー情報：　@引数.発注審査結果.getProcessingResult().getErrorInfo()<BR>
     * 　@　@　@　@エラーメソッド：　@当該メソッド名<BR>
     * 　@　@　@　@エラーメッセージ：　@２－１－２）で取得した権利付き最終日を編集(*1)した文字列<BR>
     * <BR>
     * 　@　@　@　@　@(*1)以下の形式に編集する。<BR>
     * 　@　@　@　@　@　@○○○○年○月○日（●）<BR>
     * <BR>
     * 　@　@　@　@　@　@●には、曜日(日,月,火,水,木,金,土)を設定する。<BR>
     * 　@　@　@　@　@　@例）2007年8月31日（金）<BR>
     * <BR>
     * 　@２－２）　@以外の場合<BR>
     * 　@　@　@発注審査結果からWEB3BusinessLayerExceptionを生成し、throwする。<BR>
     * <BR>
     * 　@　@　@[コンストラクタの引数]<BR>
     * 　@　@　@　@エラー情報：　@引数.発注審査結果.getProcessingResult().getErrorInfo()<BR>
     * 　@　@　@　@エラーメソッド：　@当該メソッド名<BR>
     * <BR>
     * @@param l_orderValidationResult - (発注審査結果)<BR>
     * 発注審査結果<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社コード<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
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

        //１）　@発注審査が正常終了した場合
        //  引数.発注審査結果.getProcessingResult().isSuccessfulResult() ＝ trueの場合、
        //     何もしないで処理を終了する。（return;）
        if (l_orderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //２）　@発注審査が失敗した場合
            // ２－１）　@権利付き最終日チェックでエラーとなった場合
            //　@引数.発注審査結果.getProcessingResult().getErrorInfo()の戻り値が、
            //    "権利付き最終日後の失効日指定不可。"（BUSINESS_ERROR_02836）の場合、
            //　@  次の処理を行う。
            if (WEB3ErrorCatalog.BUSINESS_ERROR_02836.equals(
                l_orderValidationResult.getProcessingResult().getErrorInfo()))
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

                //拡張プロダクトマネージャを取得
                try
                {
                    //２－１－１）　@株式銘柄の取得
                    //　@拡張プロダクトマネージャ.getProduct()をcallする。
                    WEB3EquityProductManager l_equityProductManager =
                        (WEB3EquityProductManager)l_tradingModule.getProductManager();

                    WEB3EquityProduct l_eqtypeProduct =
                        (WEB3EquityProduct)l_equityProductManager.getProduct(
                            l_institution, l_strProductCode);
                    //２－１－２）　@権利付き最終日の取得
                    //　@　@２－１－１）で取得した株式銘柄.get権利付き最終日()をcallする。
                    Date l_datRightCondOrderEndDay = l_eqtypeProduct.getRightCondOrderEndDay();

                    //２－１－３）　@例外処理
                    //　@発注審査結果からWEB3BusinessLayerExceptionを生成し、throwする。
                    //　@[コンストラクタの引数]
                    //　@　@エラー情報：　@引数.発注審査結果.getProcessingResult().getErrorInfo()
                    //　@　@エラーメソッド：　@当該メソッド名
                    //　@　@エラーメッセージ：　@２－１－２）で取得した権利付き最終日を編集(*1)した文字列
                    //　@　@　@(*1)以下の形式に編集する。
                    //　@　@　@　@○○○○年○月○日（●）
                    //　@　@　@　@●には、曜日(日,月,火,水,木,金,土)を設定する。
                    //　@　@　@　@例）2007年8月31日（金）
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
                    log.error("テーブルに該当するデータがありません。", l_ex);
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
                //２－２）　@以外の場合
                //　@　@発注審査結果からWEB3BusinessLayerExceptionを生成し、throwする。
                // 　@[コンストラクタの引数]
                //　@　@エラー情報：　@引数.発注審査結果.getProcessingResult().getErrorInfo()
                //　@　@エラーメソッド：　@当該メソッド名
                log.debug(l_orderValidationResult.getProcessingResult().getErrorInfo().getErrorMessage());
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
                }

            }

    }

    /**
     * (validate機@構預託同意)<BR>
     * （* 株式発注審査チェック.validate機@構預託同意(補助口座)に委譲する。）<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@throws WEB3BaseException
     */
    public void validateMechanismDepositAgree(SubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMechanismDepositAgree(SubAccount)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityTypeOrderManagerReusableValidations l_reusableValidations =
            (WEB3EquityTypeOrderManagerReusableValidations)
                WEB3EquityTypeOrderManagerReusableValidations.getInstance();

        //株式発注審査チェック.validate機@構預託同意(補助口座)に委譲する。
        l_reusableValidations.validateMechanismDepositAgree(l_subAccount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (throw現物株式余力エラー詳細情報)<BR>
     * 引余力エラー区分に応じて、埋め込みエラーメッセージの編集、<BR>
     * 及び該当するエラーコードを設定して例外をthrowをする。<BR>
     * ※余力チェックエラー時のみ使用する。<BR>
     * <BR>
     * シーケンス図「throw現物株式余力エラー詳細情報」参照。<BR>
     * <BR>
     * ========================================================== <BR>
     * シーケンス図 ：(throw現物株式余力エラー詳細情報) <BR>
     * 具体位置：(二階建エラー（取引余力結果.取引余力エラー情報.取引余力エラー区分 ==<BR>
     * 　@　@　@　@　@ "二階建エラー"）の場合)<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01928 <BR>
     * ========================================================== <BR>
     * <BR>
     * ========================================================== <BR>
     * シーケンス図 ：(throw現物株式余力エラー詳細情報) <BR>
     * 具体位置：get預り金不足情報（買付）<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01929 <BR>
     * ========================================================== <BR>
     * <BR>
     * ========================================================== <BR>
     * シーケンス図 ：(throw現物株式余力エラー詳細情報) <BR>
     * 具体位置：get預り金不足情報（売付）<BR>
     * class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_01930 <BR>
     * ========================================================== <BR>
     * <BR>
     * @@param l_tPTradingPowerResult - (取引余力結果)<BR>
     * 取引余力結果<BR>
     * @@param l_orderTypeEnum - (注文種別)<BR>
     * 注文種別<BR>
     * @@param l_dblOrderQuantity - (注文株数)<BR>
     * 注文株数<BR>
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
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //取引余力結果.is判定フラグ() == true の場合
        //何もせずreturnする。
        if (l_tPTradingPowerResult.isResultFlg())
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //取引余力結果.is判定フラグ() == false の場合
        //取引余力結果.取引余力エラー情報.取引余力エラー区分
        String l_strTradinPowerErrorDiv = l_tPTradingPowerResult.getTpErrorInfo().tradinPowerErrorDiv;

        //二階建エラー（取引余力結果.取引余力エラー情報.取引余力エラー区分 == "二階建エラー"）の場合
        if (WEB3TPTradingPowerErrorDivDef.MARGIN_SEC_ERROR.equals(l_strTradinPowerErrorDiv))
        {
            log.debug("二階建チェックエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01928,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "二階建チェックエラー。");
        }
        //二階建エラーでない場合
        else
        {
            //買注文の場合
            //get預り金不足情報（買付）取得した文字列は例外オブジェクトに設定
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderTypeEnum))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01929,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    this.getLackAccountBalanceInfoBuy(l_tPTradingPowerResult));
            }
            //売注文の場合
            //get預り金不足情報（売付）取得した文字列は例外オブジェクトに設定
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
