head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.56.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderManagerImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文マネージャImpl(WEB3ToSuccOrderManagerImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 郭英 (中訊) 新規作成 
Revesion History : 2007/06/05 柴双紅 (中訊) 仕様変更モデル234
Revesion History : 2008/03/20 崔遠鵬 (中訊) 仕様変更モデル250、251、260、292、299、300、301
Revesion History : 2008/04/07 崔遠鵬 (中訊) DB更新仕様No.044、046、047、048、057 對應
Revesion History : 2008/04/14 趙林鵬 (中訊) モデルNo.279,322
Revesion History : 2008/04/22 趙林鵬 (中訊) モデルNo.324,336
Revesion History : 2008/04/22 趙林鵬 (中訊) モデルNo.342
Revesion History : 2008/04/28 崔遠鵬 (中訊) モデルNo.345
Revesion History : 2008/05/15 安陽 (中訊) モデルNo.350
Revesion History : 2008/05/19 劉剣(中訊) 仕様変更モデルNo.318
Revesion History : 2008/08/18 劉剣 (中訊) IFO小数点対応
*/

package webbroker3.triggerorder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeContractSettleChangeOrderUnitEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3MiniStockDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SessionTypeDef;
import webbroker3.common.define.WEB3SucOrderTradingPowerCheckDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityAsset;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginOpenContractOrderSpec;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.WEB3IfoBizLogicProvider;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (連続注文マネージャImpl)<BR>
 * 連続注文用の注文マネージャ。<BR>
 * （OrderManagerの実装クラス）
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3ToSuccOrderManagerImpl implements OrderManager 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccOrderManagerImpl.class);
        
    /**
     * @@roseuid 4348CDDF008C
     */
    public WEB3ToSuccOrderManagerImpl() 
    {
     
    }
    
    /**
     * （getOrderの実装：未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param arg0
     * @@return Order
     * @@throws NotFoundException
     * @@roseuid 431D5C6E035A
     */
    public Order getOrder(long arg0) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * （toOrderの実装：未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param arg0
     * @@return Order
     * @@roseuid 431E8661026D
     */
    public Order toOrder(Row arg0) 
    {
        return null;
    }
    
    /**
     * （getOrderUnitの実装：未使用）<BR>
     * <BR>
     * nullを返却する。
     * @@param l_lngOrderUnitId - 注文単位ID。
     * @@return OrderUnit
     * @@throws NotFoundException
     * @@roseuid 431E867B01D1
     */
    public OrderUnit getOrderUnit(long l_lngOrderUnitId) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * （getOrderAction（未実装））
     * @@param arg0
     * @@return OrderAction
     * @@throws NotFoundException
     * @@roseuid 43328EC00205
     */
    public OrderAction getOrderAction(long arg0) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * （toOrderAction（未実装））
     * @@param arg0
     * @@return OrderAction
     * @@roseuid 43328EC00234
     */
    public OrderAction toOrderAction(Row arg0) 
    {
        return null;
    }
    
    /**
     * （getOrderExecution（未実装））
     * @@param arg0
     * @@return OrderExecution
     * @@throws NotFoundException
     * @@roseuid 43328EC00262
     */
    public OrderExecution getOrderExecution(long arg0) throws NotFoundException 
    {
        return null;
    }
    
    /**
     * （toOrderExecution（未実装））
     * @@param arg0
     * @@return OrderExecution
     * @@roseuid 43328EC00282
     */
    public OrderExecution toOrderExecution(Row arg0) 
    {
        return null;
    }
    
    /**
     * （validateNewOrder（未実装））
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@return NewOrderValidationResult
     * @@roseuid 43328F3F01B7
     */
    public NewOrderValidationResult validateNewOrder(SubAccount arg0, ProductTypeEnum arg1, NewOrderSpec arg2) 
    {
        return null;
    }
    
    /**
     * （submitNewOrder（未実装））
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容オブジェクト。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 登録用の注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@param l_blnIsSkipOrderCheck - (isSkip発注審査)<BR>
     * 発注審査をスキップするかどうかのフラグ。<BR>
     * （未使用。常にtrueがされた場合と同じ動作となる）
     * @@return OrderSubmissionResult
     * @@roseuid 432918AD015F
     */
    public OrderSubmissionResult submitNewOrder(
        SubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        NewOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        boolean l_blnIsSkipOrderCheck) 
    {
        return null;
    }
    
    /**
     * （validateChangeOrder（未実装））
     * @@param arg0
     * @@param arg1
     * @@return OrderValidationResult
     * @@roseuid 43328F3F01D6
     */
    public OrderValidationResult validateChangeOrder(SubAccount arg0, ChangeOrderSpec arg1) 
    {
        return null;
    }
    
    /**
     * （submitChangeOrder（未実装））
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@return OrderSubmissionResult
     * @@roseuid 43328F3F0205
     */
    public OrderSubmissionResult submitChangeOrder(
        SubAccount arg0, 
        ChangeOrderSpec arg1, 
        String arg2, 
        boolean arg3) 
    {
        return null;
    }
    
    /**
     * （validateCancelOrder（未実装））
     * @@param arg0
     * @@param arg1
     * @@return OrderValidationResult
     * @@roseuid 43328F3F0234
     */
    public OrderValidationResult validateCancelOrder(SubAccount arg0, CancelOrderSpec arg1) 
    {
        return null;
    }
    
    /**
     * （submitCancelOrder（未実装））
     * @@param arg0
     * @@param arg1
     * @@param arg2
     * @@param arg3
     * @@return OrderSubmissionResult
     * @@roseuid 43328F3F0253
     */
    public OrderSubmissionResult submitCancelOrder(
        SubAccount arg0, 
        CancelOrderSpec arg1, 
        String arg2, 
        boolean arg3) 
    {
        return null;
    }
    
    /**
     * （getOrderValidator（未実装）） <BR>
     * <BR>
     * @@return OrderValidator
     * @@roseuid 43328F3F0282
     */
    public OrderValidator getOrderValidator() 
    {
        return null;
    }
    
    /**
     * （overrideOrderValidator（未実装））
     * @@param arg0
     * @@roseuid 43328F3F02B1
     */
    public void overrideOrderValidator(OrderValidator arg0) 
    {     
    }
    
    /**
     * （getOrderUnit）<BR>
     * <BR>
     * 指定された予約注文単位オブジェクトを返却する。<BR>
     * <BR>
     * １）　@引数の銘柄タイプ=="株式"の場合、<BR>
     * 　@　@　@this.get株式予約注文単位(引数の注文ID)にdelegateする。<BR>
     * <BR>
     * ２）　@上記以外の場合、<BR>
     * 　@　@　@this.get先物OP予約注文単位(引数の注文ID)にdelegateする。
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。
     * @@return OrderUnit
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 431E95F20319
     */
    public OrderUnit getOrderUnit(
        ProductTypeEnum l_productType, 
        long l_lngOrderId) throws NotFoundException,WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnit(ProductTypeEnum, long) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数の銘柄タイプ=="株式"の場合、
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            //this.get株式予約注文単位(引数の注文ID)にdelegateする。
            log.exiting(STR_METHOD_NAME);
            return this.getReserveEqtypeOrderUnit(l_lngOrderId);
        }
        //上記以外の場合、
        else
        {
            //this.get先物OP予約注文単位(引数の注文ID)にdelegateする。
            log.exiting(STR_METHOD_NAME);
            return this.getReserveIfoOrderUnit(l_lngOrderId);
        }
    }
    
    /**
     * (get株式予約注文単位)<BR>
     * （getReserveEqtypeOrderUnit）<BR>
     * <BR>
     * 指定された株式予約注文単位オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【株式予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@　@注文ID = 引数の注文ID <BR>
     * <BR>
     * ２）　@this.toOrderUnit(取得した株式予約注文単位行オブジェクト)により、<BR>
     * 　@　@　@株式予約注文単位オブジェクトを生成する。<BR>
     * 　@　@　@生成したオブジェクトを返却する。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。
     * @@return webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl
     * @@throws NotFoundException,WEB3BaseException
     * @@roseuid 431E97670348
     */
    public WEB3ToSuccEqTypeOrderUnitImpl getReserveEqtypeOrderUnit(long l_lngOrderId) 
        throws NotFoundException, WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getReserveEqtypeOrderUnit(long) ";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@DB検索
            //以下の条件を指定して、【株式予約注文単位テーブル】を検索する。
            //[条件]
            //注文ID = 引数の注文ID
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = RsvEqOrderUnitDao.findRowByPk(l_lngOrderId);
            //DataFindException, DataNetworkException, DataQueryException
              
            //２）　@this.toOrderUnit(取得した株式予約注文単位行オブジェクト)により、
            //株式予約注文単位オブジェクトを生成する。
            //生成したオブジェクトを返却する。 
            WEB3ToSuccEqTypeOrderUnitImpl l_eqTypeOrderUnitImpl = 
                (WEB3ToSuccEqTypeOrderUnitImpl)this.toOrderUnit(l_rsvEqOrderUnitRow);
                
            log.exiting(STR_METHOD_NAME);
                
            return l_eqTypeOrderUnitImpl;
        }
        catch (DataFindException l_ex)
        {
            log.exiting(STR_METHOD_NAME);      
            
            log.error("検索結果に一致する行が存在しない。", l_ex);      
            throw new NotFoundException("検索結果に一致する行が存在しない: OrderId = " + l_lngOrderId);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get先物OP予約注文単位)<BR>
     * 指定された先物OP予約注文単位オブジェクトを返却する。<BR>
     * （getReserveIfoOrderUnit）<BR>
     * <BR>
     * 指定された先物OP予約注文単位オブジェクトを返却する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@　@　@注文ID = 引数の注文ID<BR>
     * <BR>
     * ２）　@this.toOrderUnit(取得した先物OP予約注文単位行オブジェクト)により、<BR>
     * 　@　@　@先物OP予約注文単位オブジェクトを生成する。<BR>
     * 　@　@　@生成したオブジェクトを返却する。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 予約注文の注文ID。
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 431E97E5025D
     */
    public WEB3ToSuccIfoOrderUnitImpl getReserveIfoOrderUnit(long l_lngOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getReserveIfoOrderUnit(long) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１）　@DB検索
            //以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。
            //[条件]
            //注文ID = 引数の注文ID
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(l_lngOrderId);

            //２）this.toOrderUnit(取得した先物OP予約注文単位行オブジェクト)により、
            //先物OP予約注文単位オブジェクトを生成する。
            //生成したオブジェクトを返却する。
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
                (WEB3ToSuccIfoOrderUnitImpl)this.toOrderUnit(l_rsvIfoOrderUnitRow);

            log.exiting(STR_METHOD_NAME);

            return l_ifoOrderUnitImpl;
        }
        catch (DataFindException l_ex)
        {
            log.error("検索結果に一致する行が存在しない。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * （toOrderUnitの実装）<BR>
     * <BR>
     * 指定の注文単位Rowオブジェクトから、<BR>
     * 予約注文単位オブジェクト（株式／先物OP）を生成する。<BR>
     * <BR>
     * １）　@引数の注文単位Rowの型が株式予約注文単位Rowの場合<BR>
     * <BR>
     * 　@　@引数の注文単位Rowオブジェクトを引数に指定して、<BR>
     * 　@　@株式予約注文単位オブジェクトを生成する。<BR>
     * 　@　@生成したオブジェクトを返却する。<BR>
     * <BR>
     * ２）　@引数の注文単位Rowの型が先物OP予約注文単位Rowの場合<BR>
     * <BR>
     * 　@　@引数の注文単位Rowオブジェクトを引数に指定して、<BR>
     * 　@　@先物OP予約注文単位オブジェクトを生成する。<BR>
     * 　@　@生成したオブジェクトを返却する。<BR>
     * @@param l_orderUnitRow - (注文単位Row)<BR>
     * 注文単位Row。
     * @@return OrderUnit
     * @@roseuid 431E8719025D
     */
    public OrderUnit toOrderUnit(Row l_orderUnitRow) 
    {
        final String STR_METHOD_NAME = " toOrderUnit(Row) ";
        log.entering(STR_METHOD_NAME);
        
        //１）　@引数の注文単位Rowの型が株式予約注文単位Rowの場合
        if (l_orderUnitRow instanceof RsvEqOrderUnitRow)
        {                        
            //引数の注文単位Rowオブジェクトを引数に指定して、    
            //株式予約注文単位オブジェクトを生成する。
            //生成したオブジェクトを返却する。
            log.exiting(STR_METHOD_NAME);
            return new WEB3ToSuccEqTypeOrderUnitImpl((RsvEqOrderUnitRow)l_orderUnitRow);
        }
        else if (l_orderUnitRow instanceof RsvIfoOrderUnitRow)
        {
            //２）　@引数の注文単位Rowの型が先物OP予約注文単位Rowの場合
            //引数の注文単位Rowオブジェクトを引数に指定して、
            //先物OP予約注文単位オブジェクトを生成する。
            //生成したオブジェクトを返却する。
            log.exiting(STR_METHOD_NAME);
            return new WEB3ToSuccIfoOrderUnitImpl((RsvIfoOrderUnitRow)l_orderUnitRow);
        }
        else
        {   
            log.debug("パラメータの値が株式予約注文単位Row'以外です。");
            log.exiting(STR_METHOD_NAME);            
            throw new IllegalArgumentException(
                "パラメータの値が株式予約注文単位Row'以外です。");
        }
    }
    
    /**
     * (get有効株式子注文単位一覧)<BR>
     * （getOpenReserveEqtypeOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、有効な株式予約注文単位オブジェクトの配列を返却する。<BR>
     * <BR>
     * １）　@有効な予約注文単位を取得する。<BR>
     * 　@　@　@株式予約注文更新サービス.get有効予約注文単位一覧(引数.親注文の注文ID)<BR>
     * をコールする。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、<BR>
     * 　@　@　@this.toOrderUnit(取得した株式予約注文単位行オブジェクト)により、<BR>
     * 　@　@　@株式予約注文単位オブジェクトを生成する。<BR>
     * 　@　@　@生成したオブジェクトのArrayListを配列にして返却する。
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl[]
     * @@throws NotFoundException, WEB3BaseException
     * @@roseuid 432163F0035A
     */
    public WEB3ToSuccEqTypeOrderUnitImpl[] getOpenReserveEqtypeOrderUnits(
        long l_lngParentOrderId) throws NotFoundException, WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenReserveEqtypeOrderUnits(long) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
        
        //１）　@有効な予約注文単位を取得する。
        //株式予約注文更新サービス.get有効予約注文単位一覧(引数.親注文の注文ID)
        //をコールする。
        List l_lisRsvEqOrderUnitRow = 
            l_rsvEqOrderUnitUpdateService.getOpenReserveEqtypeOrderUnits(l_lngParentOrderId);
        
        //２）　@１）の戻り値の要素数分、
        //this.toOrderUnit(取得した株式予約注文単位行オブジェクト)により、
        //株式予約注文単位オブジェクトを生成する。
        //生成したオブジェクトのArrayListを配列にして返却する。
        int l_intListSize = 0;
        if (l_lisRsvEqOrderUnitRow != null && !l_lisRsvEqOrderUnitRow.isEmpty())
        {
            l_intListSize = l_lisRsvEqOrderUnitRow.size();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);            
            return null;
        }
        
        WEB3ToSuccEqTypeOrderUnitImpl[] l_eqOrderUnitImpls = new WEB3ToSuccEqTypeOrderUnitImpl[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_eqOrderUnitImpls[i] = (WEB3ToSuccEqTypeOrderUnitImpl)
                this.toOrderUnit((RsvEqOrderUnitRow)l_lisRsvEqOrderUnitRow.get(i));
        }

        log.exiting(STR_METHOD_NAME);
            
        return l_eqOrderUnitImpls;
    }
    
    /**
     * (get有効先物OP子注文単位一覧)<BR>
     * （getOpenReserveIfoOrderUnits）<BR>
     * <BR>
     * 指定された親注文に紐付く、有効な先物OP予約注文単位オブジェクトの配列を返却する。<BR>
     * <BR>
     * １）　@有効な予約注文単位を取得する。<BR>
     * 　@　@　@先物OP予約注文更新サービス.get有効予約注文単位一覧(引数.親注文の注文ID)をコールする。<BR>
     * <BR>
     * ２）　@１）の戻り値の要素数分、<BR>
     * 　@　@　@this.toOrderUnit(取得した先物OP予約注文単位行オブジェクト)により、<BR>
     * 　@　@　@先物OP予約注文単位オブジェクトを生成する。<BR>
     * 　@　@　@生成したオブジェクトのArrayListを配列にして返却する。<BR>
     * @@param l_lngParentOrderId - (親注文の注文ID)<BR>
     * 親注文の注文ID。
     * @@return WEB3ToSuccIfoOrderUnitImpl[]
     * @@throws WEB3BaseException
     */
    public WEB3ToSuccIfoOrderUnitImpl[] getOpenReserveIfoOrderUnits(
        long l_lngParentOrderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOpenReserveIfoOrderUnits(long) ";
        log.entering(STR_METHOD_NAME);

        //１）　@有効な予約注文単位を取得する。
        //先物OP予約注文更新サービス.get有効予約注文単位一覧(引数.親注文の注文ID)をコールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);
        List l_lisRsvIfoOrderUnitRow =
            l_rsvIfoOrderUnitUpdateService.getOpenReserveIfoOrderUnits(l_lngParentOrderId);

        //２）　@１）の戻り値の要素数分、
        //this.toOrderUnit(取得した先物OP予約注文単位行オブジェクト)により、
        //先物OP予約注文単位オブジェクトを生成する。
        //生成したオブジェクトのArrayListを配列にして返却する。
        int l_intListSize = 0;
        if (l_lisRsvIfoOrderUnitRow != null && !l_lisRsvIfoOrderUnitRow.isEmpty())
        {
            l_intListSize = l_lisRsvIfoOrderUnitRow.size();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3ToSuccIfoOrderUnitImpl[] l_ifoOrderUnitImpls = new WEB3ToSuccIfoOrderUnitImpl[l_intListSize];
        for (int i = 0; i < l_intListSize; i++)
        {
            l_ifoOrderUnitImpls[i] = (WEB3ToSuccIfoOrderUnitImpl)
                this.toOrderUnit((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRow.get(i));
        }

        log.exiting(STR_METHOD_NAME);

        return l_ifoOrderUnitImpls;
    }

    /**
     * (get株式予約注文執行単価)<BR>
     * 予約注文（子注文）の執行単価を取得し返却する。<BR>
     * －指値注文／成行注文の場合（±指値指定なしの場合）、指値または0を返却する。<BR>
     * －±指値指定注文の場合は、親注文の指値／時価に単価調整値を加味した単価を<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@引数の単価調整値 == null の場合は、引数の指値をそのまま返却する。<BR>
     * 　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@基準となる約定単価を取得する。<BR>
     * 　@　@　@以下、親注文の約定の状態により、分岐する。<BR>
     * <BR>
     * ２－１）　@親注文が全部約定している場合<BR>
     * （親注文の注文単位.isFullyExecuted()==trueの場合）<BR>
     * <BR>
     * ２－１－１）　@親注文の約定オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@　@親注文の注文単位.getExecutions()をコールする。<BR>
     * <BR>
     * ２－１－２）　@基準の約定単価を決定する。<BR>
     * <BR>
     * 　@　@○子注文がマイナス指定の場合（引数の単価調整値の符号==マイナスの場合）<BR>
     * <BR>
     * 　@　@　@取得した親注文の約定オブジェクト.約定単価 の中で最も安い単価を、<BR>
     * 　@　@　@基準の約定単価とする。<BR>
     * <BR>
     * 　@　@○子注文がプラス指定の場合（引数の単価調整値の符号≠マイナスの場合）<BR>
     * <BR>
     * 　@　@　@取得した親注文の約定オブジェクト.約定単価 の中で最も高い単価を、<BR>
     * 　@　@　@基準の約定単価とする。<BR>
     * <BR>
     * ２－２）　@親注文が全部約定以外の場合<BR>
     * （親注文の注文単位.isFullyExecuted()==falseの場合）<BR>
     * <BR>
     * ２－２－１）　@親注文が指値注文の場合<BR>
     * （親注文の注文単位.isLimitPrice()==trueの場合）<BR>
     * <BR>
     * 　@　@　@親注文の注文単位.指値 を基準の約定単価とする。<BR>
     * <BR>
     * ２－２－２）　@親注文が成行注文の場合<BR>
     * （親注文の注文単位.isLimitPrice()==falseの場合）<BR>
     * <BR>
     * 　@　@　@EQTYPEの拡張プロダクトマネージャ.get時価(引数の株式取引銘柄)により、<BR>
     * 　@　@　@時価を取得する。<BR>
     * 　@　@　@求めた時価を、基準の約定単価とする。<BR>
     * <BR>
     * ３）　@執行単価を求める。<BR>
     * <BR>
     * 　@　@２）で求めた基準の約定単価 に、引数の単価調整値を加算した値を返却する。<BR>
     * <BR>
     * 　@　@※求めた執行単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。<BR>
     * 　@ 　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@ 　@ 　@tag:   BUSINESS_ERROR_02298<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。
     * @@param l_dblLimitPrice - (指値)<BR>
     * 子注文の指値。
     * @@param l_priceAdjustValue - (単価調整値)<BR>
     * 単価調整値。<BR>
     * 株式予約注文単位テーブルの同項目、もしくは左記に準ずる値を設定する。<BR>
     * （単価調整値の指定がない場合は、nullが設定される。）
     * @@param l_equityTradedProduct - (株式取引銘柄)<BR>
     * 親注文の銘柄＋市場に該当する株式取引銘柄オブジェクト。
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4324EE5300E1
     */
    public double getReserveEqtypeOrderExecPrice(
        EqTypeOrderUnit l_parentOrderUnit, 
        double l_dblLimitPrice, 
        Double l_priceAdjustValue, 
        WEB3EquityTradedProduct l_equityTradedProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getReserveEqtypeOrderExecPrice(EqTypeOrderUnit, double, Double, WEB3EquityTradedProduct)";
        log.entering(STR_METHOD_NAME);            
            
        //１）　@引数の単価調整値 == null の場合は、引数の指値をそのまま返却する。 
        if (l_priceAdjustValue == null)
        {
            log.exiting(STR_METHOD_NAME);
             return l_dblLimitPrice;
        }

        //以外、以下の処理を行う。
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        double l_dblPriceAdjustValue = l_priceAdjustValue.doubleValue();
        double l_dblBasePrice = 0.0D; 
        
        //２）　@基準となる約定単価を取得する。 
        //以下、親注文の約定の状態により、分岐する。 
        //２－１）　@親注文が全部約定している場合（親注文の注文単位.isFullyExecuted()==trueの場合）
        if (l_parentOrderUnit.isFullyExecuted())
        {
            //２－１－１）　@親注文の約定オブジェクトを取得する。 
            //親注文の注文単位.getExecutions()をコールする。
            OrderExecution[] l_orderExecs = l_parentOrderUnit.getExecutions();
            
            int l_intCnt = 0;
            
            double l_dblMaxPrice = 0.0D; 
            double l_dblMinPrice = 0.0D;
            
            if (l_orderExecs != null && l_orderExecs.length != 0)
            {
                l_intCnt = l_orderExecs.length;
                l_dblMaxPrice = l_orderExecs[0].getExecutionPrice(); 
                l_dblMinPrice = l_orderExecs[0].getExecutionPrice();
            }
            else
            {
                log.debug("親注文の約定オブジェクトが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "親注文の約定オブジェクトが存在しない。");
            }
                              
            for (int i = 0; i < l_intCnt; i++)
            {
                double l_dblExecPrice = l_orderExecs[i].getExecutionPrice();
                if (l_dblExecPrice > l_dblMaxPrice)
                {
                    l_dblMaxPrice = l_dblExecPrice;
                }
                if (l_dblExecPrice < l_dblMinPrice)
                {
                    l_dblMinPrice = l_dblExecPrice;
                }
            }

            //２－１－２）　@基準の約定単価を決定する。 
            //○子注文がマイナス指定の場合（引数の単価調整値の符号==マイナスの場合）
            if (l_dblPriceAdjustValue < 0) 
            {
                //取得した親注文の約定オブジェクト.約定単価 の中で最も安い単価を、 
                //基準の約定単価とする。
                l_dblBasePrice = l_dblMinPrice;

            }
            //○子注文がプラス指定の場合（引数の単価調整値の符号≠マイナスの場合）
            else 
            {
                //取得した親注文の約定オブジェクト.約定単価 の中で最も高い単価を、 
                //基準の約定単価とする。
                l_dblBasePrice = l_dblMaxPrice;
            }                  
        }            
        //２－２）　@親注文が全部約定以外の場合（親注文の注文単位.isFullyExecuted()==falseの場合） 
        else
        {
            //２－２－１）　@親注文が指値注文の場合（親注文の注文単位.isLimitPrice()==trueの場合）                
            if (!l_parentOrderUnit.isMarketOrder())
            {
                //親注文の注文単位.指値 を基準の約定単価とする。
                l_dblBasePrice = l_parentOrderUnit.getLimitPrice();
            }
            //２－２－２）親注文が成行注文の場合（親注文の注文単位.isLimitPrice()==falseの場合） 
            else
            {
                //EQTYPEの拡張プロダクトマネージャ.get時価(引数の株式取引銘柄)により、 
                //時価を取得する。 
                //求めた時価を、基準の約定単価とする。
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3EquityProductManager l_productMgr = 
                    (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                l_dblBasePrice = l_productMgr.getCurrentPrice(l_equityTradedProduct);                    
            } 
        }

        //３）　@執行単価を求める。 
        //２）で求めた基準の約定単価 に、引数の単価調整値を加算した値を返却する。 
        double l_dblExecPrice = l_dblBasePrice + l_dblPriceAdjustValue;             
        
        //※求めた執行単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。 
        if (l_dblExecPrice <= 0)
        {
            log.debug("執行単価が0以下。 " + l_dblExecPrice);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                this.getClass().getName() + STR_METHOD_NAME,
                "執行単価が0以下。 " + l_dblExecPrice);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblExecPrice;
    }
    
    /**
     * (get先物OP予約注文執行単価)<BR>
     * （getReserveIfoOrderExecPrice）<BR>
     * <BR>
     * 予約注文（子注文）の執行単価を取得し返却する。<BR>
     * 　@・指値注文／成行注文の場合（±指値指定なしの場合）、指値または0を返却する。<BR>
     * 　@・±指値指定注文の場合は、親注文の指値／時価に単価調整値を加味した単価を返却する。<BR>
     * <BR>
     * １）　@引数の単価調整値 == null の場合は、引数の指値をそのまま返却する。<BR>
     * 　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@基準となる約定単価を取得する。<BR>
     * 　@　@以下、親注文の約定の状態により、分岐する。<BR>
     * <BR>
     * 　@２－１）　@親注文が全部約定している場合<BR>
     * （親注文の注文単位.isFullyExecuted()==trueの場合）<BR>
     * <BR>
     * 　@　@２－１－１）　@親注文の約定オブジェクトを取得する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@親注文の注文単位.getExecutions()をコールする。<BR>
     * <BR>
     * 　@　@２－１－２）　@基準の約定単価を決定する。<BR>
     * <BR>
     * 　@　@　@○子注文がマイナス指定の場合（引数の単価調整値の符号==マイナスの場合）<BR>
     * <BR>
     * 　@　@　@　@　@取得した親注文の約定オブジェクト.約定単価 の中で最も安い単価を、<BR>
     * 　@　@　@　@　@基準の約定単価とする。<BR>
     * <BR>
     * 　@　@　@○子注文がプラス指定の場合（引数の単価調整値の符号≠マイナスの場合）<BR>
     * <BR>
     * 　@　@　@　@　@取得した親注文の約定オブジェクト.約定単価 の中で最も高い単価を、<BR>
     * 　@　@　@　@　@基準の約定単価とする。<BR>
     * <BR>
     * 　@２－２）　@親注文が全部約定以外の場合<BR>
     * （親注文の注文単位.isFullyExecuted()==falseの場合）<BR>
     * <BR>
     * 　@　@２－２－１）　@親注文が指値注文の場合（親注文の注文単位.isLimitPrice()==trueの場合）<BR>
     * 　@　@　@　@　@　@　@　@　@　@親注文の注文単位.指値 を基準の約定単価とする。<BR>
     * <BR>
     * 　@　@２－２－２）　@親注文が成行注文の場合（親注文の注文単位.isLimitPrice()==falseの場合）<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@先物OPプロダクトマネージャ.get時価(引数の先物OP取引銘柄)により、<BR>
     * 　@　@　@　@　@　@　@　@　@　@時価を取得する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@求めた時価を、基準の約定単価とする。<BR>
     * <BR>
     * ３）　@執行単価を求める。<BR>
     * <BR>
     * 　@２）で求めた基準の約定単価 に、引数の単価調整値を加算した値を返却する。<BR>
     * <BR>
     * 　@※求めた執行単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException <BR>
     * 　@tag: BUSINESS_ERROR_02298 <BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 子注文の指値。<BR>
     * @@param l_priceAdjustValue - (単価調整値)<BR>
     * 単価調整値。 <BR>
     * 先物OP予約注文単位テーブルの同項目、もしくは左記に準ずる値を設定する。<BR>
     * （単価調整値の指定がない場合は、nullが設定される。）<BR>
     * @@param l_ifoTradedProductImpl - (先物OP取引銘柄)<BR>
     * 親注文の銘柄＋市場に該当する先物OP取引銘柄オブジェクト。
     * @@return double
     * @@throws WEB3BaseException
     */
    public double getReserveIfoOrderExecPrice(
        IfoOrderUnit l_parentOrderUnit,
        double l_dblLimitPrice,
        Double l_priceAdjustValue,
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReserveIfoOrderExecPrice(IfoOrderUnit, double, Double, WEB3IfoTradedProductImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "親注文の注文単位 = null。");
        }

        //１）　@引数の単価調整値 == null の場合は、引数の指値をそのまま返却する。
        //以外、以下の処理を行う。
        if (l_priceAdjustValue == null)
        {
             log.exiting(STR_METHOD_NAME);
             return l_dblLimitPrice;
        }

        double l_dblPriceAdjustValue = l_priceAdjustValue.doubleValue();
        double l_dblBasePrice = 0.0D;

        //２）　@基準となる約定単価を取得する。
        //以下、親注文の約定の状態により、分岐する。
        //２－１）　@親注文が全部約定している場合（親注文の注文単位.isFullyExecuted()==trueの場合）
        if (l_parentOrderUnit.isFullyExecuted())
        {
            //２－１－１）　@親注文の約定オブジェクトを取得する。
            //親注文の注文単位.getExecutions()をコールする。
            OrderExecution[] l_orderExecs = l_parentOrderUnit.getExecutions();

            int l_intCnt = 0;

            double l_dblMaxPrice = 0.0D;
            double l_dblMinPrice = 0.0D;

            if (l_orderExecs != null && l_orderExecs.length != 0)
            {
                l_intCnt = l_orderExecs.length;
                l_dblMaxPrice = l_orderExecs[0].getExecutionPrice();
                l_dblMinPrice = l_orderExecs[0].getExecutionPrice();
            }
            else
            {
                log.debug("親注文の約定オブジェクトが存在しない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "親注文の約定オブジェクトが存在しない。");
            }

            for (int i = 0; i < l_intCnt; i++)
            {
                double l_dblExecPrice = l_orderExecs[i].getExecutionPrice();
                if (l_dblExecPrice > l_dblMaxPrice)
                {
                    l_dblMaxPrice = l_dblExecPrice;
                }
                if (l_dblExecPrice < l_dblMinPrice)
                {
                    l_dblMinPrice = l_dblExecPrice;
                }
            }

            //２－１－２）　@基準の約定単価を決定する。
            //○子注文がマイナス指定の場合（引数の単価調整値の符号==マイナスの場合）
            if (l_dblPriceAdjustValue < 0)
            {
                //取得した親注文の約定オブジェクト.約定単価 の中で最も安い単価を、
                //基準の約定単価とする。
                l_dblBasePrice = l_dblMinPrice;

            }
            //○子注文がプラス指定の場合（引数の単価調整値の符号≠マイナスの場合）
            else
            {
                //取得した親注文の約定オブジェクト.約定単価 の中で最も高い単価を、
                //基準の約定単価とする。
                l_dblBasePrice = l_dblMaxPrice;
            }
        }
        //２－２）　@親注文が全部約定以外の場合（親注文の注文単位.isFullyExecuted()==falseの場合）
        else
        {
            //２－２－１）　@親注文が指値注文の場合（親注文の注文単位.isLimitPrice()==trueの場合）
            if (!l_parentOrderUnit.isMarketOrder())
            {
                //親注文の注文単位.指値 を基準の約定単価とする。
                l_dblBasePrice = l_parentOrderUnit.getLimitPrice();
            }
            //２－２－２）親注文が成行注文の場合（親注文の注文単位.isLimitPrice()==falseの場合）
            else
            {
                //先物OPプロダクトマネージャ.get時価(引数の先物OP取引銘柄)により、
                //時価を取得する。
                //求めた時価を、基準の約定単価とする。
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
                l_dblBasePrice = l_productMgr.getCurrentPrice(l_ifoTradedProductImpl);
            }
        }

        //３）　@執行単価を求める。
        //２）で求めた基準の約定単価 に、引数の単価調整値を加算した値を返却する。
        BigDecimal l_bdBasePrice = new BigDecimal(l_dblBasePrice + "");
        BigDecimal l_bdPriceAdjustValue = new BigDecimal(l_dblPriceAdjustValue + "");
        double l_dblExecPrice = l_bdBasePrice.add(l_bdPriceAdjustValue).doubleValue();

        //※求めた執行単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。
        if (l_dblExecPrice <= 0)
        {
            log.debug("執行単価が0以下。 ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "執行単価が0以下。 ");
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblExecPrice;
    }

    /**
     * (is反対売買取引)<BR>
     * 指定された連続注文取引区分が、親注文に対する反対売買取引かどうかを判定する。<BR>
     * <BR>
     * １）　@this.get反対売買取引()をコールする。<BR>
     * 　@[get反対売買取引()に指定する引数]<BR>
     * 　@　@顧客：　@パラメータ.親注文の注文単位.口座IDに該当する顧客<BR>
     * 　@　@注文種別：　@パラメータ.親注文の注文単位.注文種別<BR>
     * <BR>
     * 　@メソッドの戻り値がnullの場合、falseを返却する。<BR>
     * <BR>
     * ２）　@１）の戻り値にパラメータ.連続注文取引区分が<BR>
     * 　@含まれる場合true、以外falseを返却する。
     * @@param l_strRsvOrderTradingDiv - (連続注文取引区分)<BR>
     * 連続注文取引区分。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 43279E7B002B
     */
    public boolean isReversingTrade(
        String l_strRsvOrderTradingDiv, 
        OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " isReversingTrade(String, OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        WEB3GentradeMainAccount l_mainAccount = null;
        
        try
        {
            //１）　@this.get反対売買取引()をコールする。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                    l_parentOrderUnit.getAccountId());//NotFoundException        
        }
        catch (NotFoundException l_ex)
        {
            log.error("顧客テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        String[] l_strReturns = this.getReversingTrades(l_mainAccount, l_parentOrderUnit.getOrderType());
        
        //メソッドの戻り値がnullの場合、falseを返却する。
        if (l_strReturns == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
                
        //２）　@１）の戻り値にパラメータ.連続注文取引区分が
        //含まれる場合true、以外falseを返却する。
        if (WEB3Toolkit.contain(l_strReturns, l_strRsvOrderTradingDiv))
        {                            
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * (validate連続注文取引)<BR>
     * 指定の連続注文取引が、指定可能かどうかをチェックする。<BR>
     * 指定不可の場合は「指定の連続注文取引は、当該注文に設定不可」の例外をスローする。<BR>
     * <BR>
     * １）　@this.get連続注文取引一覧()をコールする。<BR>
     * 　@[get連続注文取引一覧()に指定する引数]<BR>
     * 　@　@顧客：　@パラメータ.親注文の注文単位.口座IDに該当する顧客<BR>
     * 　@　@注文種別：　@パラメータ.親注文の注文単位.注文種別<BR>
     * 　@　@商品区分一覧：<BR>
     * 　@　@　@パラメータ.親注文の注文単位の型が、<BR>
     * 　@　@　@[株式注文単位の場合]<BR>
     * 　@　@　@　@"現物株式"、"信用取引"の配列をセット。<BR>
     * <BR>
     * 　@　@　@[先物OP注文単位の場合]<BR>
     * 　@　@　@　@"先物"、"オプション"の配列をセット。<BR>
     * <BR>
     * 　@メソッドの戻り値がnullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02245<BR>
     * <BR>
     * ２）　@this.get反対売買取引()をコールする。<BR>
     * 　@[get反対売買取引()に指定する引数]<BR>
     * 　@　@顧客：　@パラメータ.親注文の注文単位.口座IDに該当する顧客<BR>
     * 　@　@注文種別：　@パラメータ.親注文の注文単位.注文種別<BR>
     * <BR>
     * 　@メソッドの戻り値がnullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02245<BR>
     * <BR>
     * ３）　@１）、２）の戻り値のどちらにもパラメータ.連続注文取引区分が<BR>
     * 　@含まれない場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02245<BR>
     * 　@
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。
     * @@throws WEB3BaseException
     * @@roseuid 4327A3960154
     */
    public void validateSuccOrderTrade(
        String l_strRsvOrderTradingType, 
        OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSuccOrderTrade(String, OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        //１）　@this.get連続注文取引一覧()をコールする。
        WEB3GentradeMainAccount l_mainAccount = null;
        
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accountManager = l_finApp.getAccountManager();
            
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                    l_parentOrderUnit.getAccountId());//NotFoundException        
        }
        catch (NotFoundException l_ex)
        {
            log.error("顧客テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //商品区分一覧：
        String[] l_strCommodityDivs = new String[2];
        
        //パラメータ.親注文の注文単位の型が、[株式注文単位の場合]
        //　@"現物株式"、"信用取引"の配列をセット。
        if (l_parentOrderUnit instanceof EqTypeOrderUnit)
        {            
            l_strCommodityDivs[0] = WEB3CommodityDivDef.EQUITY;
            l_strCommodityDivs[1] = WEB3CommodityDivDef.MARGIN;
        }
        else if (l_parentOrderUnit instanceof IfoOrderUnit)
        //[先物OP注文単位の場合]
        //"先物"、"オプション"の配列をセット。
        {  
            l_strCommodityDivs[0] = WEB3CommodityDivDef.FUTURE;
            l_strCommodityDivs[1] = WEB3CommodityDivDef.OPTION;
        }
        else
        {
            log.debug("パラメータの値が株式／先物OP注文単位'以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータの値が株式／先物OP注文単位'以外です。");          
        }
        
        String[] l_strReturns1 = this.getSuccOrderTradeList(
            l_mainAccount, 
            l_parentOrderUnit.getOrderType(), 
            l_strCommodityDivs);

        //メソッドの戻り値がnullの場合、例外をスローする。
        //含まれない場合、例外をスローする。
        if (l_strReturns1 == null)
        {
            log.debug("メソッドの戻り値がnullの場合、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02245,
                this.getClass().getName() + STR_METHOD_NAME,
                "指定の連続注文取引は、当該注文に設定不可です。");
        }
        
        //２）　@this.get反対売買取引()をコールする。
        String[] l_strReturns2 = this.getReversingTrades(l_mainAccount, l_parentOrderUnit.getOrderType());
        
        //メソッドの戻り値がnullの場合、例外をスローする。
        if (l_strReturns2 == null)
        {
            log.debug("メソッドの戻り値がnullの場合、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02245,
                this.getClass().getName() + STR_METHOD_NAME,
                "指定の連続注文取引は、当該注文に設定不可です。");
        }
        
        //３）　@１）、２）の戻り値のどちらにもパラメータ.連続注文取引区分が
        //含まれない場合、例外をスローする。
        if (!(WEB3Toolkit.contain(l_strReturns1, l_strRsvOrderTradingType) || 
            WEB3Toolkit.contain(l_strReturns2, l_strRsvOrderTradingType)))
        {
            log.debug("指定の連続注文取引は、当該注文に設定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02245,
                this.getClass().getName() + STR_METHOD_NAME,
                "指定の連続注文取引は、当該注文に設定不可です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文最大設定数を超過していないかどうか判別する。<BR>
     * 超過している場合は、<BR>
     * 「連続注文最大設定数を超過」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02247<BR>
     * <BR>
     * １）　@現在有効な予約注文を取得し、その件数をカウントする。<BR>
     * <BR>
     * 　@パラメータ.親注文の注文単位の型が、<BR>
     * 　@[株式注文単位の場合]<BR>
     * 　@　@this.get有効株式子注文単位一覧()をコールする。<BR>
     * <BR>
     * 　@　@[get有効株式子注文単位一覧()に指定する引数]<BR>
     * 　@　@　@親注文の注文ID：<BR>
     * 　@　@　@　@パラメータ.親注文の注文単位.注文ID<BR>
     * <BR>
     * 　@[先物OP注文単位の場合]<BR>
     * 　@　@this.get有効先物OP子注文単位一覧()をコールする。<BR>
     * <BR>
     * 　@　@[get有効先物OP子注文単位一覧()に指定する引数]<BR>
     * 　@　@　@親注文の注文ID：<BR>
     * 　@　@　@　@パラメータ.親注文の注文単位.注文ID<BR>
     * ２）　@最大設定可能件数の取得<BR>
     * 　@this.get連続注文最大設定件数()をコールする。<BR>
     * <BR>
     * 　@[get連続注文最大設定件数()に指定する引数]<BR>
     * 　@　@部店ID：　@パラメータ.親注文の注文単位.部店ID<BR>
     * <BR>
     * ３）　@連続注文設定数超過チェック<BR>
     * 　@１）にて取得した件数 + 1 > ２）の戻り値の場合、<BR>
     * 　@「連続注文最大設定数を超過」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02247<BR>
     * <BR>
     * 　@※最大設定数には、１つの親注文に対し予約注文（子注文）<BR>
     *     がいくつまで設定可能かが<BR>
     * 　@※設定されている。<BR>
     * 　@※＋１は、今回追加で設定しようとしている予約注文の分の加算。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * @@throws WEB3BaseException
     * 親注文の注文単位オブジェクト。
     * @@roseuid 4327A87C0007
     */
    public void validateSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateSuccOrderMaxQuantity(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        int l_intCnt = 0;
        
        //１）　@現在有効な予約注文を取得し、その件数をカウントする。 
        //パラメータ.親注文の注文単位の型が、 [株式注文単位の場合] 
        if (l_parentOrderUnit instanceof EqTypeOrderUnit)
        {
            //this.get有効株式子注文単位一覧()をコールする。 
            WEB3ToSuccEqTypeOrderUnitImpl[] l_eqOrderUnitImpls = null;
            
            try
            {
                l_eqOrderUnitImpls = 
                    this.getOpenReserveEqtypeOrderUnits(l_parentOrderUnit.getOrderId());//NotFoundException
            }
            catch (NotFoundException l_ex)
            {
                //do nothing
            }

            if (l_eqOrderUnitImpls != null && l_eqOrderUnitImpls.length != 0)
            {
                l_intCnt = l_eqOrderUnitImpls.length;
            }            
        }
        else if (l_parentOrderUnit instanceof IfoOrderUnit)
        //[先物OP注文単位の場合]
        {
             //this.get有効先物OP子注文単位一覧()をコールする。
            WEB3ToSuccIfoOrderUnitImpl[] l_ifoOrderUnitImpls = null;

            l_ifoOrderUnitImpls = this.getOpenReserveIfoOrderUnits(l_parentOrderUnit.getOrderId());

            if (l_ifoOrderUnitImpls != null && l_ifoOrderUnitImpls.length != 0)
            {
                l_intCnt = l_ifoOrderUnitImpls.length;
            }
        }
        else
        {
            log.debug("パラメータの値が株式／先物OP注文単位'以外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータの値が株式／先物OP注文単位'以外です。");
        }

        //２）　@最大設定可能件数の取得 
        //this.get連続注文最大設定件数()をコールする。 
        double l_dblSuccOrderMaxQuantity = this.getSuccOrderMaxQuantity(l_parentOrderUnit);


        //３）　@連続注文設定数超過チェック 
        //１）にて取得した件数 + 1 > ２）の戻り値の場合、 
        //連続注文最大設定数を超過」の例外をスローする。 
        if (l_intCnt + 1 > l_dblSuccOrderMaxQuantity)
        {
            log.debug("連続注文最大設定数を超過です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02247,
                this.getClass().getName() + STR_METHOD_NAME,
                "連続注文最大設定数を超過です。" + l_intCnt + " + 1 > " + l_dblSuccOrderMaxQuantity);
        }        
    }
    
    /**
     * (get連続注文最大設定件数)<BR>
     * 連続注文最大設定件数を取得する。<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@部店プリファ@レンステーブルを以下の条件で<BR>
     * 　@検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID = パラメータ.部店ID And<BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.連続注文最大設定数 And <BR>
     * 　@　@プリファ@レンス名の連番 =<BR>
     * 　@　@　@パラメータ.親注文の注文単位の型が、株式注文単位の場合、"1"<BR>
     * 　@　@　@パラメータ.親注文の注文単位の型が、先物OP注文単位の場合、"2"<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、<BR>
     * 　@該当データなしの例外をスローする。<BR>
     * 　@class: WEB3SystemLayerException<BR>
     * 　@tag:   SYSTEM_ERROR_80005<BR>
     * <BR>
     * ２）　@検索結果.プリファ@レンスの値を返却する。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4327AD680372
     */
    protected double getSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = ".getSuccOrderMaxQuantity(OrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１）　@DB検索
            //部店プリファ@レンステーブルを以下の条件で検索する。
            //[条件]
            //部店ID = パラメータ.部店ID And
            //プリファ@レンス名 = プリファ@レンス名.連続注文最大設定数 And 
            //プリファ@レンス名の連番 =
            //パラメータ.親注文の注文単位の型が、株式注文単位の場合、"1"
            //パラメータ.親注文の注文単位の型が、先物OP注文単位の場合、"2"
            int l_intNameSerialNo = 0;
            if (l_parentOrderUnit instanceof EqTypeOrderUnit)
            {
                l_intNameSerialNo = 1;
            }
            else if (l_parentOrderUnit instanceof IfoOrderUnit)
            {
                l_intNameSerialNo = 2;
            }
            BranchPreferencesRow l_branchReferencesRow =
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_parentOrderUnit.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_SUC_ORDER_MAX_ORDER_COUNT,
                    l_intNameSerialNo);
            
            if (l_branchReferencesRow != null)
            {
                //２）　@検索結果.プリファ@レンスの値を返却する。
                log.exiting(STR_METHOD_NAME);
                return Double.parseDouble(l_branchReferencesRow.getValue());
            }
            //検索結果が取得できなかった場合、該当データなしの例外をスローする。
            else
            {
                String l_strErrorMsg = "部店プリファ@レンステーブルに、プリファ@レンス名 "+ 
                    "= \"" + WEB3BranchPreferencesNameDef.TRIGGERORDER_SUC_ORDER_MAX_ORDER_COUNT + "\"の設定なし";                    
                
                log.debug(l_strErrorMsg);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strErrorMsg);
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate連続注文取扱可能)<BR>
     * 連続注文が取扱可能かどうか判別する。<BR>
     * 取扱不可の場合、「連続注文取扱不可」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02248<BR>
     * <BR>
     * １）　@取扱可能注文条件インスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@銘柄タイプ：　@パラメータ.銘柄タイプ<BR>
     * 　@　@先物／オプション区分：　@パラメータ.先物／オプション区分<BR>
     * 　@　@信用取引区分：　@"DEFAULT"（固定）<BR>
     * 　@　@市場コード：　@"DEFAULT"（固定）<BR>
     * <BR>
     * ２）　@１）にて生成したインスタンスより取得した<BR>
     * 　@取扱可能注文条件行.連続注文 == "取扱不可"の場合、<BR>
     * 　@「連続注文取扱不可」の例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02248<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ
     * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
     * 0： DEFAULT（先物OP以外）<BR>
     * 1： 先物<BR>
     * 2： オプション
     * @@throws WEB3BaseException
     * @@roseuid 4327E92501A5
     */
    public void validateSuccOrderHandling(
        String l_strInstitutionCode, 
        ProductTypeEnum l_productType, 
        String l_strFutureOptionDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSuccOrderHandling(String, ProductTypeEnum, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@取扱可能注文条件インスタンスを生成する。
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_productType,
                l_strFutureOptionDiv,
                WEB3MarginTradingDivDef.DEFAULT,
                WEB3MarketCodeDef.DEFAULT);
        
        //２）　@１）にて生成したインスタンスより取得した
        //取扱可能注文条件行.連続注文 == "取扱不可"の場合、
        //「連続注文取扱不可」の例外をスローする。
        EnableOrderConditionRow l_enableOrderConditionRow = 
            (EnableOrderConditionRow)l_handlingOrderCond.getDataSourceObject();
        
        if (WEB3DealtDef.CAN_NOT_DEALT.equals(l_enableOrderConditionRow.getChainOrder()))
        {
            log.debug("連続注文取扱不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02248,
                this.getClass().getName() + STR_METHOD_NAME,
                "連続注文取扱不可です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get連続注文取扱商品一覧)<BR>
     * 引数の証券会社が取扱っている連続注文の商品一覧を返却する。<BR>
     * <BR>
     * １）　@ArrayListを生成する。<BR>
     * <BR>
     * ２）　@商品区分(*1)の要素数分以下を繰り返す。<BR>
     * <BR>
     * 　@２－１）　@this.validate連続注文取扱可能()をcallする。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@証券会社コード：　@パラメータ.証券会社コード<BR>
     * 　@　@　@銘柄タイプ：<BR>
     * 　@　@　@　@　@要素が"現物株式"or"信用取引"の場合、"株式"を設定する。<BR>
     * 　@　@　@　@　@要素が"先物"or"オプション"の場合、"先物オプション"を設定する。<BR>
     * 　@　@　@先物／オプション区分：<BR>
     * 　@　@　@　@　@要素が"現物株式"or"信用取引"の場合、"DEFAULT"を設定する。<BR>
     * 　@　@　@　@　@要素が"先物"の場合、"先物"を設定する。<BR>
     * 　@　@　@　@　@要素が"オプション"の場合、"オプション"を設定する。<BR>
     * <BR>
     * 　@２－２）　@this.validate連続注文取扱可能()が例外をスローする場合、次の要素へ移行する。(continue;)<BR>
     * <BR>
     * 　@２－３）　@該当要素の商品区分をArrayListに追加する。<BR>
     * 　@　@　@　@　@　@（this.validate連続注文取扱可能()が例外をスローしない場合）<BR>
     * <BR>
     * ３）作成したArrayListを返す。<BR>
     * <BR>
     * (*1)WEB3CommodityDivDef<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@return String[]<BR>
     * @@throws WEB3BaseException
     */
    public String[] getToSuccOrderDealtCommodityList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getToSuccOrderDealtCommodityList(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@ArrayListを生成する。
        List l_lisOrderDealtCommodityList = new ArrayList();

        //２）　@商品区分(*1)の要素数分以下を繰り返す。
        //(*1)WEB3CommodityDivDef
        String[] l_strCommodityDivs = {
            WEB3CommodityDivDef.EQUITY,
            WEB3CommodityDivDef.MARGIN,
            WEB3CommodityDivDef.FUTURE,
            WEB3CommodityDivDef.OPTION};
        int l_intCommodityDivsSize = l_strCommodityDivs.length;
        for (int i = 0; i < l_intCommodityDivsSize; i++)
        {
            //２－１）　@this.validate連続注文取扱可能()をcallする。
            //[引数]
            //証券会社コード：　@パラメータ.証券会社コード

            //銘柄タイプ：
            //要素が"現物株式"or"信用取引"の場合、"株式"を設定する。
            //要素が"先物"or"オプション"の場合、"先物オプション"を設定する。
            ProductTypeEnum l_productType = null;
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDivs[i])
                || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDivs[i]))
            {
                l_productType = ProductTypeEnum.EQUITY;
            }
            else
            {
                l_productType = ProductTypeEnum.IFO;
            }

            //先物／オプション区分：
            //要素が"現物株式"or"信用取引"の場合、"DEFAULT"を設定する。
            //要素が"先物"の場合、"先物"を設定する。
            //要素が"オプション"の場合、"オプション"を設定する。
            String l_strFutureOptionDiv = null;
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDivs[i])
                || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDivs[i]))
            {
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
            }
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDivs[i]))
            {
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
            }
            else
            {
                l_strFutureOptionDiv = WEB3FuturesOptionDivDef.OPTION;
            }

            try
            {
                this.validateSuccOrderHandling(l_strInstitutionCode, l_productType, l_strFutureOptionDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                //２－２）　@this.validate連続注文取扱可能()が例外をスローする場合、次の要素へ移行する。(continue;)
                continue;
            }

            //２－３）　@該当要素の商品区分をArrayListに追加する。<BR>
            //（this.validate連続注文取扱可能()が例外をスローしない場合）
            l_lisOrderDealtCommodityList.add(l_strCommodityDivs[i]);
        }

        //３）作成したArrayListを返す。
        String[] l_strOrderDealtCommodityLists = new String[l_lisOrderDealtCommodityList.size()];
        l_lisOrderDealtCommodityList.toArray(l_strOrderDealtCommodityLists);

        log.exiting(STR_METHOD_NAME);
        return l_strOrderDealtCommodityLists;
    }
    
    /**
     * (get反対売買取引)<BR>
     * 引数の注文種別の反対売買取引を返却する。<BR>
     * <BR>
     * １）　@this.get連続注文取扱可能一覧()をcallして、<BR>
     * 　@引数の証券会社が取り扱っている連続注文の商品一覧を取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@証券会社コード：　@パラメータ.顧客.証券会社コード<BR>
     * <BR>
     * 　@※顧客属性だけでは、先物／オプションの取扱を判別できない為、<BR>
     * 　@※先物／オプションの場合のみ、同メソッドの戻り値を使用する。<BR>
     * <BR>
     * ２）　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@反対売買取引区分をArrayListに追加する。<BR>
     * 　@　@パラメータ.注文種別が、<BR>
     * 　@　@["現物買注文"の場合]<BR>
     * 　@　@　@・"売付（前提注文）<BR>
     * 　@　@["現物売注文"の場合]<BR>
     * 　@　@　@・"買付（前提注文）"<BR>
     * <BR>
     * 　@　@３－１）　@信用客(*1)の場合、<BR>
     * 　@　@　@以下の分岐により取引を追加する。<BR>
     * 　@　@　@["信用新規買建注文" or<BR>
     * 　@　@　@　@"信用新規売建注文"の場合]<BR>
     * 　@　@　@　@・"信用返済（前提注文）"<BR>
     * 　@　@　@　@・"現引現渡（前提注文）"<BR>
     * 　@　@　@["信用買建返済注文（売返済）" or<BR>
     * 　@　@　@　@"信用売建返済注文（買返済）"の場合]<BR>
     * 　@　@　@　@・"信用新規建（前提注文）<BR>
     * 　@　@　@["信用現引注文"の場合]<BR>
     * 　@　@　@　@・"売付（前提注文）"<BR>
     * 　@　@　@　@・"信用新規建（前提注文）"<BR>
     * 　@　@　@["信用現渡注文"の場合]<BR>
     * 　@　@　@　@・"買付（前提注文）"<BR>
     * 　@　@　@　@・"信用新規建（前提注文）"<BR>
     * <BR>
     * 　@　@３－２）　@証拠金口座開設済(*2)の場合 かつ<BR>
     * 　@　@　@１）の戻り値に"先物"が含まれている場合<BR>
     * 　@　@　@以下の分岐により取引を追加する。<BR>
     * 　@　@　@["先物新規買建注文" or<BR>
     * 　@　@　@　@"先物新規売建注文"の場合]<BR>
     * 　@　@　@　@・"先物返済（前提注文）"<BR>
     * 　@　@　@["先物売建買返済注文（買返済）" or<BR>
     * 　@　@　@　@"先物買建売返済注文（売返済）"の場合]<BR>
     * 　@　@　@　@・"先物新規建（前提注文）<BR>
     * <BR>
     * 　@　@３－３）　@OP口座開設済(*3)の場合 かつ<BR>
     * 　@　@　@１）の戻り値に"オプション"が含まれている場合<BR>
     * 　@　@　@以下の分岐により取引を追加する。<BR>
     * 　@　@　@["OP新規買建注文" or<BR>
     * 　@　@　@　@"OP新規売建注文"の場合]<BR>
     * 　@　@　@　@・"OP返済（前提注文）"<BR>
     * 　@　@　@["OP売建買返済注文（買返済）" or<BR>
     * 　@　@　@　@"OP買建売返済注文（売返済）"の場合]<BR>
     * 　@　@　@　@・"OP新規建（前提注文）<BR>
     * <BR>
     * 　@(*1)信用客<BR>
     * 　@　@パラメータ.顧客.is信用口座開設("指定なし") == trueの場合<BR>
     * 　@(*2)証拠金口座開設済<BR>
     * 　@　@パラメータ.顧客.is先物OP口座開設("先物") == trueの場合<BR>
     * 　@(*3)OP口座開設済<BR>
     * 　@　@パラメータ.顧客.is先物OP口座開設("オプション") == trueの場合<BR>
     * <BR>
     * ４）　@生成したArrayList.size() == 0の場合、nullを返却する。<BR>
     * 　@以外、生成したArrayList.toArray()の戻り値を返却する。
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4327AFED0372
     */
    public String[] getReversingTrades(
        WEB3GentradeMainAccount l_mainAccount, 
        OrderTypeEnum l_orderType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReversingTrades(WEB3GentradeMainAccount, OrderTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("顧客 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "顧客 = null。");
        }
        
        //１）　@this.get連続注文取扱可能一覧()をcallして、
        //引数の証券会社が取り扱っている連続注文の商品一覧を取得する。
        //[引数]
        //証券会社コード：　@パラメータ.顧客.証券会社コード
        String[] l_strOrderDealtCommodityList =
            this.getToSuccOrderDealtCommodityList(l_mainAccount.getInstitution().getInstitutionCode());

        //１）　@ArrayListを生成する。 
        List l_lisStrs = new ArrayList();

        //２）　@反対売買取引区分をArrayListに追加する。 
        //パラメータ.注文種別が、 
        //  ["現物買注文"の場合] 
        //・"売付（前提注文）
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderType)) 
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER);
        }
        // ["現物売注文"の場合] 
        //・"買付（前提注文）" 
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderType)) 
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER);
        }
        
        //２－１－１）　@信用客(*1)の場合、 
        //　@　@　@以下の分岐により取引を追加する。 
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            //["信用新規買建注文" or 
            //"信用新規売建注文"の場合] 
            //・"信用返済（前提注文）" 
            //・"現引現渡（前提注文）" 
            if (OrderTypeEnum.MARGIN_LONG.equals(l_orderType) || 
                OrderTypeEnum.MARGIN_SHORT.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER);
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER);
            }
            //["信用買建返済注文（売返済）" or 
            //"信用売建返済注文（買返済）"の場合] 
            //・"信用新規建（前提注文） 
            else if (OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_orderType) || 
                OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER);
            }
            // ["信用現引注文"の場合] 
            //・"売付（前提注文）" 
            //・"信用新規建（前提注文）" 
            else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SELL_ASSUMPTION_ORDER);
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER);
            }
            //["信用現渡注文"の場合] 
            //・"買付（前提注文）" 
            //・"信用新規建（前提注文）" 
            else if (OrderTypeEnum.SWAP_MARGIN_SHORT.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.BUY_ASSUMPTION_ORDER);
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT_ASSUMPTION_ORDER);
            }
        }

        //２－１－２）　@証拠金口座開設済(*2)の場合、 かつ １）の戻り値に"先物"が含まれている場合
        //以下の分岐により取引を追加する。
        if (l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.FUTURE))
        {
            //["先物新規買建注文" or 
            //"先物新規売建注文"の場合] 
            //・"先物返済（前提注文）" 
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) || 
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER);
            }
            
            //["先物売建買返済注文（買返済）" or 
            //"先物買建売返済注文（売返済）"の場合] 
            //・"先物新規建（前提注文） 
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType) || 
                OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER);
            }
        }

        //２－１－３）　@OP口座開設済(*3)の場合、 かつ １）の戻り値に"オプション"が含まれている場合
        //以下の分岐により取引を追加する。
        if (l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.OPTION))
        {
            //["OP新規買建注文" or 
            // "OP新規売建注文"の場合] 
            //・"OP返済（前提注文）"
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) || 
                OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER);
            }
             
            //["OP売建買返済注文（買返済）" or 
            // "OP買建売返済注文（売返済）"の場合] 
            //・"OP新規建（前提注文） 
            if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) || 
                OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType))
            {
                l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER);
            }
        }
        
        //３）　@生成したArrayList.size() == 0の場合、nullを返却する。 
        if (l_lisStrs.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //以外、生成したArrayList.toArray()の戻り値を返却する。
        else
        {
            String[] l_strs = new String[l_lisStrs.size()];
            l_lisStrs.toArray(l_strs);
            
            log.exiting(STR_METHOD_NAME);
            return l_strs;
        }        
    }
    
    /**
     * (get連続注文取引一覧)<BR>
     * 顧客が選択可能な連続注文取引の一覧を<BR>
     * 返却する。<BR>
     * <BR>
     * １）　@this.get連続注文取扱可能一覧()をcallして、<BR>
     * 　@引数の証券会社が取り扱っている連続注文の商品一覧を取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@証券会社コード：　@パラメータ.顧客.証券会社コード<BR>
     * <BR>
     * 　@※顧客属性だけでは、先物／オプションの取扱を判別できない為、<BR>
     * 　@※先物／オプションの場合のみ、同メソッドの戻り値を使用する。<BR>
     * <BR>
     * ２）　@ArrayListを生成する。<BR>
     * <BR>
     * ３）　@各商品の連続注文取引区分を追加する。<BR>
     * 　@３－１）　@パラメータ.商品区分一覧に"現物株式"が<BR>
     * 　@　@含まれる場合、現物株式の取引区分をArrayListに追加する。<BR>
     * 　@　@・"買付"<BR>
     * 　@　@・"売付（既存残）"<BR>
     * <BR>
     * 　@３－２）　@パラメータ.商品区分一覧に"信用取引"が<BR>
     * 　@　@含まれる場合 かつ 信用客(*1)の場合、<BR>
     * 　@　@信用取引の取引区分をArrayListに追加する。<BR>
     * 　@　@・"信用新規建"<BR>
     * 　@　@・"信用返済（既存残）"<BR>
     * 　@　@・"現引現渡（既存残）"<BR>
     * <BR>
     * 　@３－３）　@パラメータ.商品区分一覧に"先物"が<BR>
     * 　@　@含まれる場合 かつ 先物口座開設済(*2)の場合、かつ<BR>
     * 　@　@１）の戻り値に"先物"が含まれている場合、<BR>
     * 　@　@先物の取引区分をArrayListに追加する。<BR>
     * 　@　@・"先物新規建"<BR>
     * 　@　@・"先物返済（既存残）"<BR>
     * <BR>
     * 　@３－４）　@パラメータ.商品区分一覧に"オプション"が<BR>
     * 　@　@含まれる場合 かつ OP口座開設済(*3)の場合、かつ<BR>
     * 　@　@１）の戻り値に"オプション"が含まれている場合、<BR>
     * 　@　@先物の取引区分をArrayListに追加する。<BR>
     * 　@　@・"OP新規建"<BR>
     * 　@　@・"OP返済（既存残）"<BR>
     * <BR>
     * 　@(*1)信用客<BR>
     * 　@　@パラメータ.顧客.is信用口座開設("指定なし") == trueの場合<BR>
     * 　@(*2)先物口座開設済<BR>
     * 　@　@パラメータ.顧客.is先物OP口座開設("先物") == trueの場合<BR>
     * 　@(*3)OP口座開設済<BR>
     * 　@　@パラメータ.顧客.is先物OP口座開設("オプション") == trueの場合<BR>
     * <BR>
     * ４）　@生成したArrayList.size() == 0の場合、nullを返却する。<BR>
     * 　@以外、生成したArrayList.toArray()の戻り値を返却する。
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@param l_orderType - (注文種別)<BR>
     * 注文種別
     * @@param l_strProductDivList - (商品区分一覧)<BR>
     * 商品区分一覧<BR>
     * 以下の値の配列<BR>
     * <BR>
     * 1：　@現物株式<BR>
     * 2：　@信用取引<BR>
     * 3：　@先物<BR>
     * 4：　@オプション
     * 
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4327B9CB0074
     */
    public String[] getSuccOrderTradeList(
        WEB3GentradeMainAccount l_mainAccount, 
        OrderTypeEnum l_orderType, 
        String[] l_strProductDivList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getSuccOrderTradeList(WEB3GentradeMainAccount, OrderTypeEnum, String[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("顧客 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "顧客 = null。");
        }
        
        //１）　@this.get連続注文取扱可能一覧()をcallして、
        //引数の証券会社が取り扱っている連続注文の商品一覧を取得する。
        //[引数]
        //証券会社コード：　@パラメータ.顧客.証券会社コード
        String[] l_strOrderDealtCommodityList =
            this.getToSuccOrderDealtCommodityList(l_mainAccount.getInstitution().getInstitutionCode());

        //１）　@ArrayListを生成する。 
        List l_lisStrs = new ArrayList();
        
        //２）　@各商品の連続注文取引区分を追加する。 
        //２－１）　@パラメータ.商品区分一覧に"現物株式"が 
        //含まれる場合、現物株式の取引区分をArrayListに追加する。 
        //・"買付" 
        //・"売付（既存残）" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.EQUITY))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.BUY);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SELL_EXISTING_REMAINDER);
        }        

        //２－２）　@パラメータ.商品区分一覧に"信用取引"が 
        //含まれる場合 かつ 信用客(*1)の場合、 
        //信用取引の取引区分をArrayListに追加する。 
        //・"信用新規建" 
        //・"信用返済（既存残）" 
        //・"現引現渡（既存残）" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.MARGIN) &&
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_CONTRACT);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_EXISTING_REMAINDER);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_EXISTING_REMAINDER);
        }    

        //２－３）　@パラメータ.商品区分一覧に"先物"が 
        //含まれる場合 かつ 先物口座開設済(*2)の場合、かつ
        //１）の戻り値に"先物"が含まれている場合、
        //先物の取引区分をArrayListに追加する。 
        //・"先物新規建" 
        //・"先物返済（既存残）" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.FUTURE) &&
            l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.FUTURE))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER);
        } 

        //２－４）　@パラメータ.商品区分一覧に"オプション"が 
        //含まれる場合 かつ OP口座開設済(*3)の場合、かつ
        //１）の戻り値に"オプション"が含まれている場合、
        //先物の取引区分をArrayListに追加する。 
        //・"OP新規建" 
        //・"OP返済（既存残）" 
        if (WEB3Toolkit.contain(l_strProductDivList, WEB3CommodityDivDef.OPTION) &&
            l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION)
            && WEB3Toolkit.contain(l_strOrderDealtCommodityList, WEB3CommodityDivDef.OPTION))
        {
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.OPEN_OP);
            l_lisStrs.add(WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER);
        }         

        //３）　@生成したArrayList.size() == 0の場合、nullを返却する。 
        if (l_lisStrs.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        //以外、生成したArrayList.toArray()の戻り値を返却する。
        else
        {
            String[] l_strs = new String[l_lisStrs.size()];
            l_lisStrs.toArray(l_strs);
            
            log.exiting(STR_METHOD_NAME);
            return l_strs;
        }   
    }
    
    /**
     * (validate連続注文)<BR>
     * 連続注文の入力／確認／完了に共通のチェックを行う。<BR>
     * シーケンス図「（連続注文）validate連続注文」を参照。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。
     * @@param l_strFutureOptionDiv - (先物／オプション区分)<BR>
     * 先物／オプション区分。
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。
     * @@throws WEB3BaseException
     * @@roseuid 4327C8BC0154
     */
    public void validateSuccOrder(
        WEB3GentradeSubAccount l_subAccount, 
        ProductTypeEnum l_productType, 
        String l_strFutureOptionDiv, 
        String l_strRsvOrderTradingType, 
        OrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateSuccOrder(WEB3GentradeSubAccount, ProductTypeEnum, String, String, OrderUnit)";
            
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        //1.1: validate連続注文取扱可能(String, ProductTypeEnum, String)
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_subAccount.getInstitution();    
        
        if (l_institution == null)       
        {
            log.error("証券会社テーブルに該当するデータがありません。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券会社テーブルに該当するデータがありません。");
        }
        
        this.validateSuccOrderHandling(
            l_institution.getInstitutionCode(), 
            l_productType, 
            l_strFutureOptionDiv);
            
        //出来終了区分：
        String l_strOrderExecutionEndType = "";

        //引数の銘柄タイプ == "株"の場合、"出来終了（最終）"
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            l_strOrderExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
        }
        //引数の銘柄タイプ == "先物OP"の場合、
        else if (ProductTypeEnum.IFO.equals(l_productType))
        {
            //引数の補助口座.get取引店().is夕場実施() == true and
            //引数の親注文の注文単位.立会区分 == "その他"の場合、"夕場前出来終了"
            boolean l_blnIsEveningSessionEnforcemented =
                l_subAccount.getWeb3GenBranch().isEveningSessionEnforcemented(l_productType);
            IfoOrderUnitRow l_ifoParentOrderUnit = (IfoOrderUnitRow)l_parentOrderUnit.getDataSourceObject();
            String l_strSessionType = l_ifoParentOrderUnit.getSessionType();

            if (l_blnIsEveningSessionEnforcemented && WEB3StringTypeUtility.isEmpty(l_strSessionType))
            {
                l_strOrderExecutionEndType = WEB3OrderexecutionEndTypeDef.BEFORE_EVENING_SESSION_ORDEREXECUTION_END;
            }
            //以外、"出来終了（最終）"
            else
            {
                l_strOrderExecutionEndType = WEB3OrderexecutionEndTypeDef.DEFAULT;
            }
        }

        //1.2: validate連続注文受付可能
        //(証券会社 : 証券会社, 銘柄タイプ : ProductTypeEnum,
        //先物／オプション区分 : String, 出来終了区分 : String)
        WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
            l_institution,
            l_productType,
            l_strFutureOptionDiv,
            l_strOrderExecutionEndType);
            
        //1.3: validate連続注文取引(String, OrderUnit)
        this.validateSuccOrderTrade(l_strRsvOrderTradingType, l_parentOrderUnit);
        
        //1.4: validateトリガー注文設定To親注文(OrderUnit)
        this.validateTriggerOrderSettingToParentOrder(l_parentOrderUnit);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateトリガー注文設定To親注文)<BR>
     * 指定された親注文がトリガー注文を設定可能な状態かどうかを判定する。<BR>
     * （※親注文が訂正中の場合は、設定可能とする）<BR>
     * <BR>
     * １）　@親注文がクローズ済(*1)の場合、<BR>
     * 　@　@「親注文がクローズ済のためトリガー注文設定不可」の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02249<BR>
     * <BR>
     * ２）　@親注文が取消中(*2)の場合、<BR>
     * 　@　@「親注文が取消中のためトリガー注文設定不可」の例外をthrowする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_02343<BR>
     * <BR>
     * ３）　@親注文の注文単位の型が、株式注文単位の場合<BR>
     * 　@　@３－１）　@親注文が強制決済注文(*3)の場合、<BR>
     * 　@　@　@　@　@　@　@「親注文が強制決済注文のためトリガー注文設定不可」の<BR>
     * 　@　@　@　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@class:WEB3BusinessLayerException<BR>
     * 　@tag:BUSINESS_ERROR_02808<BR>
     * <BR>
     * (*1)親注文がクローズ済<BR>
     * 　@　@引数の親注文の注文単位.注文有効状態 == クローズ の場合、<BR>
     * 　@　@親注文がクローズ済と判定する。
     * 
     * (*2)親注文が取消中<BR>
     * 　@　@引数の親注文の注文単位.注文状態 == （"受付済（取消注文）" or "発注中（取消注文）"）の場合、<BR>
     * 　@　@親注文が取消中と判定する。<BR>
     * <BR>
     * (*3)親注文が強制決済注文<BR>
     * 　@拡張株式注文マネージャ.is強制決済注文(引数の親注文の注文単位) == trueの場合、<BR>
     * 　@強制決済注文と判定する。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。
     * @@throws WEB3BaseException
     * @@roseuid 432FB2C702F0
     */
    public void validateTriggerOrderSettingToParentOrder(OrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateTriggerOrderSettingToParentOrder(OrderUnit)";
        log.entering(STR_METHOD_NAME);
            
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }            

        if (OrderOpenStatusEnum.CLOSED.equals(l_parentOrderUnit.getOrderOpenStatus()))
        {
            log.debug("親注文がクローズ済のためトリガー注文設定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02249,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_parentOrderUnit.getOrderStatus()) ||
            OrderStatusEnum.CANCELLING.equals(l_parentOrderUnit.getOrderStatus()))
        {
            log.debug("親注文が取消中のためトリガー注文設定不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02343,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        if (l_parentOrderUnit instanceof EqTypeOrderUnit)
        {
            //親注文が強制決済注文
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_parentOrderUnit;
            boolean l_blnIsForcedSettleOrder =
                l_orderManager.isForcedSettleOrder(l_eqTypeOrderUnit);

            if (l_blnIsForcedSettleOrder)
            {
                log.debug("親注文が強制決済注文のためトリガー注文設定不可");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02808,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "親注文が強制決済注文のためトリガー注文設定不可");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * １）　@引数の銘柄タイプ=="株式"の場合、<BR>
     * 　@　@　@this.get株式親注文の注文単位(引数の（親注文）注文ID)にdelegateする。<BR>
     * <BR>
     * ２）　@上記以外の場合、<BR>
     * 　@　@　@this.get先物OP親注文の注文単位(引数の（親注文）注文ID)にdelegateする。
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。
     * @@param l_lngParentOrderId - (（親注文）注文ID)<BR>
     * 親注文の注文ID。
     * @@return OrderUnit
     * @@roseuid 4327E1490069
     */
    public OrderUnit getParentOrderUnit(ProductTypeEnum l_productType, long l_lngParentOrderId) 
    {
        final String STR_METHOD_NAME = 
            " getParentOrderUnit(ProductTypeEnum, long)";
        log.entering(STR_METHOD_NAME);
            
        //１）　@引数の銘柄タイプ=="株式"の場合、
        //this.get株式親注文の注文単位(引数の（親注文）注文ID)にdelegateする。
        if (ProductTypeEnum.EQUITY.equals(l_productType))
        {
            log.exiting(STR_METHOD_NAME);
            return this.getEqtypeParentOrderUnit(l_lngParentOrderId);
        }
        //２）　@上記以外の場合、
        //this.get先物OP親注文の注文単位(引数の（親注文）注文ID)にdelegateする。
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.getIfoParentOrderUnit(l_lngParentOrderId);
        }        
    }
    
    /**
     * (get株式親注文の注文単位)<BR>
     * EQTYPEの親注文の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * EQTYPEの拡張株式注文マネージャ.getOrderUnits(引数の（親注文）注文ID)<BR>
     * をコールする。<BR>
     * 戻り値の最初の要素を親注文の注文単位オブジェクトとして返却する。
     * @@param l_lngParentOrderId - (（親注文）注文ID)<BR>
     * 親注文の注文ID。
     * @@return EqTypeOrderUnit
     * @@roseuid 4327E2120134
     */
    public EqTypeOrderUnit getEqtypeParentOrderUnit(long l_lngParentOrderId) 
    {
        final String STR_METHOD_NAME = 
            " getEqtypeParentOrderUnit(long)";
        log.entering(STR_METHOD_NAME);    
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        //EQTYPEの拡張株式注文マネージャ.getOrderUnits(引数の（親注文）注文ID)
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_lngParentOrderId);
        
        //戻り値の最初の要素を親注文の注文単位オブジェクトとして返却する。
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            log.exiting(STR_METHOD_NAME);    
            return (EqTypeOrderUnit)l_orderUnits[0];
        }
        else
        {
            log.exiting(STR_METHOD_NAME);    
            return null;
        }
    }
    
    /**
     * (get先物OP親注文の注文単位)<BR>
     * IFOの親注文の注文単位オブジェクトを取得する。<BR>
     * <BR>
     * OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。<BR>
     * 戻り値の最初の要素を親注文の注文単位オブジェクトとして返却する。<BR>
     * @@param l_parentOrderId - (（親注文）注文ID)<BR>
     * 親注文の注文ID。
     * @@return IfoOrderUnit
     * @@roseuid 4327E33302CB
     */
    public IfoOrderUnit getIfoParentOrderUnit(long l_parentOrderId)
    {
        final String STR_METHOD_NAME = "getIfoParentOrderUnit(long)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);

        //OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_parentOrderId);

        //戻り値の最初の要素を親注文の注文単位オブジェクトとして返却する。
        if (l_orderUnits != null && l_orderUnits.length != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return (IfoOrderUnit)l_orderUnits[0];
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (submit現物株式新規予約注文)<BR>
     * （submitEqtypeNewOrder）<BR>
     * <BR>
     * 株式予約注文単位テーブルに、現物株式の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null<BR>
     *               （validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@株式予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@登録の仕様は、DB更新仕様<BR>
     * 　@「（連続）現物株式注文登録_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。<BR>
     * <BR>
     * ４）　@親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * 　@this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_orderSpc - (注文内容)<BR>
     * 株式注文内容オブジェクト。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 登録用の注文ID。
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。
     * @@param l_priceAdjustValue - (単価調整値)<BR>
     * 単価調整値。<BR>
     * （指定なしの場合はnullをセット）
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。
     * @@throws WEB3BaseException
     * @@roseuid 4327FE2901FE
     */
    public void submitEqtypeNewOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3EquityNewCashBasedOrderSpec l_orderSpc, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        Double l_priceAdjustValue, 
        EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeNewOrder(WEB3GentradeSubAccount, WEB3EquityNewCashBasedOrderSpec," +
            " long, String, String, Double, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_orderSpc == null)
        {
            log.debug("注文内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文内容 = null。");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        //１）　@取引パスワードをチェックする。
        //注文チェック.validate取引パスワード()をコールする。
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワードをチェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワードをチェックエラー");
        }
        
        //２）　@株式予約注文単位テーブルにレコードを登録する。
        //登録の仕様は、DB更新仕様
        //「（連続）現物株式注文登録_株式予約注文単位テーブル.xls」を参照。
        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
        
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
        
        //注文単位ＩＤ  (   order_unit_id   ):  null
                                                                                
        //口座ＩＤ    (   account_id  ):  引数の補助口座.口座ID
        l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                
        //補助口座ＩＤ  (   sub_account_id  ):  引数の補助口座.補助口座ID 
        l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                             
        //部店ＩＤ    (   branch_id   ):  引数の補助口座.部店ID      
        l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                          
        //取引者ＩＤ
        //引数の注文内容.getTraderIdAsObject( )
        //※引数の注文内容.getTrader()の戻り値==nullの場合は、nullをセット
        Trader l_trader = l_orderSpc.getTrader();
        if (l_trader != null)
        {
            l_rsvEqOrderUnitParams.setTraderId(l_orderSpc.getTraderIdAsObject());
        }        
                                                                             
        //注文ＩＤ    (   order_id    ):  引数の注文ID
        l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                             
        //注文種別    (   order_type  ):  "引数の注文内容.isBuyOrder()==trueの場合は、""現物買注文""。
        //以外、""現物売注文""。"
        if (l_orderSpc.isBuyOrder())
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
        }
        else
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_SELL);
        }
                                                                                   
        //注文カテゴリ  (   order_categ ):   "現物注文"        
        l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
                                                                     
        //注文履歴最終通番    (   last_order_action_serial_no ):  1     
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                          
        //連続注文取引区分    (   reserve_order_trading_type  ):  引数の連続注文取引区分    
        l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                         
        //銘柄タイプ   (   product_type    ):  1：株式                                                                        
        l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
        
        //市場ＩＤ    (   market_id   ):  引数の注文内容.市場コードに該当する市場.市場ID   
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        Market l_market = null;
        Institution l_institution = l_subAccount.getInstitution();
        try
        {
            l_market = l_finObjectManager.getMarket(l_institution, l_orderSpc.getMarketCode());
            //NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.error("市場テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                
        l_rsvEqOrderUnitParams.setMarketId(l_market.getMarketId());
        
        //注文数量    (   quantity    ):  引数の注文内容.getQuantity()
        l_rsvEqOrderUnitParams.setQuantity(l_orderSpc.getQuantity());
                                                                               
        //指値  (   limit_price ):  引数の単価調整値 != nullの場合、nullをセット。
        //以外、引数の注文内容.getLimitPrice()        
        if (l_priceAdjustValue != null)
        {
            l_rsvEqOrderUnitParams.setLimitPrice(null);                                                 
        }
        else
        {
            l_rsvEqOrderUnitParams.setLimitPrice(l_orderSpc.getLimitPrice());
        }        
        
        //単価調整値   (   price_adjust_value  ):  "引数の単価調整値
        //※nullの場合はnullをセット" 
        if (l_priceAdjustValue != null)
        {
            l_rsvEqOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);                                                       
        }
        else
        {
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
        }
        
        
        //注文失効日付  (   expiration_date ):  引数の注文内容.getOrderExpDate()                                                                       
        l_rsvEqOrderUnitParams.setExpirationDate(l_orderSpc.getOrderExpDate());
        
        //注文状態    (   order_status    ):  1:受付済（新規注文）                                                                     
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        
        //注文有効状態  (   order_open_status   ):  1:オープン                                                                      
        l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        
        //失効区分    (   expiration_status   ):  1:オープン 
        l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                             
        //税区分 (   tax_type    ):  引数の注文内容.getTaxType()                                                                        
        l_rsvEqOrderUnitParams.setTaxType(l_orderSpc.getTaxType());
        
        //税区分（現引現渡）   (   swap_tax_type   ):  0：その他
        l_rsvEqOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                                                                               
        //弁済区分    (   repayment_type  ):  null                                                                                
        //弁済期限値   (   repayment_num   ):  null                                                                        
        
        //発注日 (   biz_date    ):  取引時間管理.get発注日()                                                                     
        l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
        
        //銘柄ＩＤ    (   product_id  ):  引数の注文内容.getProductCode()に該当する株式銘柄.銘柄ID 
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        
        EqTypeProduct l_product = null;
        try
        {
            l_product = l_productMgr.getProduct(l_institution, l_orderSpc.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("銘柄テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                 
        l_rsvEqOrderUnitParams.setProductId(l_product.getProductId());
        
        //初回注文の注文チャネル (   order_chanel    ):  ログインセッションより取得してセット    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                          
        l_rsvEqOrderUnitParams.setOrderChanel(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
        
        //受注日時    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
        l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //注文単価    (   price   ):  引数の注文内容.注文単価                                                                        
        l_rsvEqOrderUnitParams.setPrice(l_orderSpc.getOrderUnitPrice());
        
        //概算受渡代金  (   estimated_price ):  引数の注文内容.概算受渡代金                                                                      
        l_rsvEqOrderUnitParams.setEstimatedPrice(l_orderSpc.getEstimateDeliveryAmount());
        
        //譲渡益金額   (   capital_gain    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGain(0);
        
        //譲渡益税額   (   capital_gain_tax    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGainTax(0);
        
        //注文経路区分  (   order_root_div  ):  ログインセッションより取得してセット                                                                      
        l_rsvEqOrderUnitParams.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
        
        //決済順序区分  (   closing_order_type  ):  null                                                                        
        
        //初回注文の注文単位ＩＤ (   first_order_unit_id ):  引数の注文内容.初回注文の注文単位ID                                                                     
        l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_orderSpc.getFirstOrderUnitId());
        
        //発注エラーコード    (   order_error_code    ):  null                                                                        
        
        //親注文の注文ＩＤ    (   parent_order_id ):  引数の親注文の注文単位.注文ID                                                                        
        l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
        
        //親注文の注文単位ＩＤ  (   parent_order_unit_id    ):  引数の親注文の注文単位.注文単位ID                                                                      
        l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
        
        //親注文内連番  (   serial_no_in_parent ):  連続注文マネージャ.get株式親注文内連番(引数の親注文の注文単位.注文単位ID)                                                                       
        l_rsvEqOrderUnitParams.setSerialNoInParent(
            (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
        
        //作成日付    (   created_timestamp   ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
        l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //更新日付    (   last_updated_timestamp  ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //３）　@予約注文履歴を作成する。
        //株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //４）　@親注文の注文単位に、予約注文登録を記録する。
        //this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用新規建新規予約注文)<BR>
     * （submitEqtypeOpenContractNewOrder）<BR>
     * <BR>
     * 株式予約注文単位テーブルに、信用新規建の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@株式予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@登録の仕様は、DB更新仕様<BR>
     * 　@「（連続）信用新規建注文登録_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。<BR>
     * <BR>
     * ４）　@親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * 　@this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_marginOpenContractOrderSpec - (注文内容)<BR>
     * 信用新規建注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 登録用の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。<BR>
     * @@param l_priceAdjustValue - (単価調整値)<BR>
     * 単価調整値。<BR>
     * （指定なしの場合はnullをセット）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4330EB480031
     */
    public void submitEqtypeOpenContractNewOrder(
        SubAccount l_subAccount, 
        WEB3MarginOpenContractOrderSpec l_marginOpenContractOrderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        Double l_priceAdjustValue, 
        EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeOpenContractNewOrder(SubAccount, WEB3MarginOpenContractOrderSpec," +
            " long, String, String, Double, EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_marginOpenContractOrderSpec == null)
        {
            log.debug("注文内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文内容 = null。");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        //１）　@取引パスワードをチェックする。
        //注文チェック.validate取引パスワード()をコールする。 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
    
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
    
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワードをチェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワードをチェックエラー");
        }
        
        //２）　@株式予約注文単位テーブルにレコードを登録する。 
        //登録の仕様は、DB更新仕様
        //「（連続）信用新規建注文登録_株式予約注文単位テーブル.xls」を参照。
        SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
        
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
        
        //注文単位ＩＤ  (   order_unit_id   ):  null
        l_rsvEqOrderUnitParams.setOrderUnitId(null);          
                                                                                
        //口座ＩＤ    (   account_id  ):  引数の補助口座.口座ID
        l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                
        //補助口座ＩＤ  (   sub_account_id  ):  引数の補助口座.補助口座ID 
        l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                             
        //部店ＩＤ    (   branch_id   ):  引数の補助口座.部店ID      
        l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                          
        //取引者ＩＤ
        //クライアントリクエストサービス.get代理入力者().取引者ID
        //※get代理入力者()の戻り値==nullの場合は、nullをセット
        WEB3ToSuccClientRequestService l_clientRequestService = new WEB3ToSuccClientRequestService();
        Trader l_trader = l_clientRequestService.getTrader();
        if (l_trader != null)
        {
            l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
        }  
        else
        {
            l_rsvEqOrderUnitParams.setTraderId(null);    
        }
                                                                             
        //注文ＩＤ    (   order_id    ):  引数の注文ID
        l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                             
        //注文種別    (   order_type  ):  引数の注文内容.isLongOrder()==trueの場合は、"新規買建注文"。
        //以外、"新規売建注文"。
        if (l_marginOpenContractOrderSpec.isLongOrder())
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
        }
        else
        {
            l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_SHORT);
        }
                                                                                   
        //注文カテゴリ  (   order_categ ):    "新規建注文"       
        l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
                                                                     
        //注文履歴最終通番    (   last_order_action_serial_no ):  1     
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                          
        //連続注文取引区分    (   reserve_order_trading_type  ):  引数の連続注文取引区分    
        l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                         
        //銘柄タイプ   (   product_type    ):  1：株式                                                                        
        l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
        
        //市場ＩＤ    (   market_id   ):  引数の補助口座.証券会社コード、
        //及び引数の注文内容.市場コードに該当する市場.市場ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
        Market l_market = null;
        Institution l_institution = l_subAccount.getInstitution();
        try
        {
            l_market = l_finObjectManager.getMarket(
                l_institution.getInstitutionCode(), 
                l_marginOpenContractOrderSpec.getMarketCode());//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.error("市場テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                
        l_rsvEqOrderUnitParams.setMarketId(l_market.getMarketId());
        
        //注文数量    (   quantity    ):  引数の注文内容.getQuantity()
        l_rsvEqOrderUnitParams.setQuantity(l_marginOpenContractOrderSpec.getQuantity());
                                                                               
        //指値  (   limit_price ):  引数の単価調整値 != nullの場合、nullをセット。
        //以外、引数の注文内容.getLimitPrice()  
        if (l_priceAdjustValue != null)
        {
            l_rsvEqOrderUnitParams.setLimitPrice(null);                                
        }
        else
        {
            l_rsvEqOrderUnitParams.setLimitPrice(l_marginOpenContractOrderSpec.getLimitPrice());
        }       
        
        //単価調整値   (   price_adjust_value  ):  "引数の単価調整値
        //※nullの場合はnullをセット" 
        l_rsvEqOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);       
        
        //注文失効日付  (   expiration_date ):  引数の注文内容.getOrderExpDate()                                                                       
        l_rsvEqOrderUnitParams.setExpirationDate(l_marginOpenContractOrderSpec.getOrderExpDate());
        
        //注文状態    (   order_status    ):  1:受付済（新規注文）                                                                     
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        
        //注文有効状態  (   order_open_status   ):  1:オープン                                                                      
        l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        
        //失効区分    (   expiration_status   ):  1:オープン 
        l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                             
        //税区分 (   tax_type    ):  引数の注文内容.getTaxType()                                                                        
        l_rsvEqOrderUnitParams.setTaxType(l_marginOpenContractOrderSpec.getTaxType());
        
        //税区分（現引現渡）   (   swap_tax_type   ):  0：その他
        l_rsvEqOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                                                                               
        //弁済区分    (   repayment_type  ):  引数の注文内容.get弁済区分() 
        l_rsvEqOrderUnitParams.setRepaymentType(
            l_marginOpenContractOrderSpec.getRepaymentType());
                                                                                       
        //弁済期限値   (   repayment_num   ):  引数の注文内容.get弁済期限値()    
        l_rsvEqOrderUnitParams.setRepaymentNum(
            (int)l_marginOpenContractOrderSpec.getRepaymentNum());                                                                    
        
        //発注日 (   biz_date    ):  取引時間管理.get発注日()                                                                     
        l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
        
        //銘柄ＩＤ    (   product_id  ):  引数の補助口座.証券会社コード、
        //及び引数の注文内容.getProductCode()に該当する株式銘柄.銘柄ID
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityProductManager l_productMgr =
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
        
        EqTypeProduct l_product = null;
        try
        {
            l_product = l_productMgr.getProduct(
                l_institution, 
                l_marginOpenContractOrderSpec.getProductCode());
        }
        catch (NotFoundException l_ex)
        {
            log.error("銘柄テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
                                                                                 
        l_rsvEqOrderUnitParams.setProductId(l_product.getProductId());
        
        //初回注文の注文チャネル (   order_chanel    ):  ログインセッションより取得してセット    
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                          
        l_rsvEqOrderUnitParams.setOrderChanel(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
        
        //受注日時    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
        l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
        
        //注文単価    (   price   ):  引数の注文内容.get計算単価()                                                                        
        l_rsvEqOrderUnitParams.setPrice(l_marginOpenContractOrderSpec.getCalcUnitPrice());
        
        //概算受渡代金  (   estimated_price ):  引数の注文内容.get建代金()                                                                      
        l_rsvEqOrderUnitParams.setEstimatedPrice(l_marginOpenContractOrderSpec.getContractAmount());
        
        //譲渡益金額   (   capital_gain    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGain(0);
        
        //譲渡益税額   (   capital_gain_tax    ):  0                                                                       
        l_rsvEqOrderUnitParams.setCapitalGainTax(0);
        
        //注文経路区分  (   order_root_div  ):  ログインセッションより取得してセット                                                                      
        l_rsvEqOrderUnitParams.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
        
        //決済順序区分  (   closing_order_type  ):  null 
        l_rsvEqOrderUnitParams.setClosingOrderType(null);                                                                       
        
        //初回注文の注文単位ＩＤ (   first_order_unit_id ):  引数の注文内容.初回注文の注文単位ID                                                                     
        l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_marginOpenContractOrderSpec.getFirstOrderUnitId());
        
        //発注エラーコード    (   order_error_code    ):  null     
        l_rsvEqOrderUnitParams.setOrderErrorCode(null);                                                                   
        
        //親注文の注文ＩＤ    (   parent_order_id ):  引数の親注文の注文単位.注文ID                                                                        
        l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
        
        //親注文の注文単位ＩＤ  (   parent_order_unit_id    ):  引数の親注文の注文単位.注文単位ID                                                                      
        l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
        
        //親注文内連番  (   serial_no_in_parent ):  連続注文マネージャ.get株式親注文内連番(引数の親注文の注文単位.注文単位ID)                                                                       
        l_rsvEqOrderUnitParams.setSerialNoInParent(
            (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
        
        //作成日付    (   created_timestamp   ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
        l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //更新日付    (   last_updated_timestamp  ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //３）　@予約注文履歴を作成する。
        //株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //４）　@親注文の注文単位に、予約注文登録を記録する。
        //this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用返済新規予約注文)<BR>
     * （submitEqtypeCloseContractNewOrder）<BR>
     * <BR>
     * 株式予約注文単位テーブルに、信用返済の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@株式予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@登録の仕様は、DB更新仕様<BR>
     * 　@「（連続）信用返済注文登録_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@反対売買でない場合(*1)、<BR>
     * 　@パラメータ.注文内容.getSettleContractOrderEntries()の<BR>
     * 　@戻り値の要素数分、株式予約建株返済指定情報テーブルに<BR>
     * 　@レコードを登録する。<BR>
     * <BR>
     * 　@登録の仕様は、DB更新仕様<BR>
     * 　@「（連続）信用返済注文登録_株式予約建株返済指定情報テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ４）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。<BR>
     * <BR>
     * ５）　@親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * 　@this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。<BR>
     * <BR>
     * (*1)反対売買でない場合・・・<BR>
     * 　@連続注文マネージャImpl.is反対売買取引() == false。<BR>
     * 　@[is反対売買取引()に指定する引数]<BR>
     * 　@　@連続注文取引区分：　@引数の同項目<BR>
     * 　@　@親注文の注文単位：　@引数の同項目<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_marginSettleContractOrderSpec - (注文内容)<BR>
     * 信用返済注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 登録用の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。<BR>
     * @@param l_priceAdjustValue - (単価調整値)<BR>
     * 単価調整値。<BR>
     * （指定なしの場合はnullをセット）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@param l_equityRealizedProfitAndLossPrice - (計算結果)<BR>
     * 概算決済損益代金計算結果オブジェクト。<BR>
     * @@param l_equityContract - (建株)<BR>
     * 建株オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43325BCB01CE
     */
    public void submitEqtypeCloseContractNewOrder(
        SubAccount l_subAccount, 
        WEB3MarginSettleContractOrderSpec l_marginSettleContractOrderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        Double l_priceAdjustValue, 
        EqTypeOrderUnit l_parentOrderUnit, 
        WEB3EquityRealizedProfitAndLossPrice l_equityRealizedProfitAndLossPrice, 
        WEB3EquityContract l_equityContract) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeCloseContractNewOrder(SubAccount, WEB3MarginSettleContractOrderSpec," +
            " long, String, String, Double, EqTypeOrderUnit, WEB3EquityRealizedProfitAndLossPrice, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_marginSettleContractOrderSpec == null)
        {
            log.debug("注文内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文内容 = null。");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        if (l_equityRealizedProfitAndLossPrice == null)
        {
            log.debug("計算結果 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "計算結果 = null。");
        }
        
        if (l_equityContract == null)
        {
            log.debug("建株 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "建株 = null。");
        }
        
        try
        {
            //１）　@取引パスワードをチェックする。
            //注文チェック.validate取引パスワード()をコールする。 
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードをチェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードをチェックエラー");
            }
            
            //２）　@株式予約注文単位テーブルにレコードを登録する。 
            //登録の仕様は、DB更新仕様
            //「（連続）信用返済注文登録_株式予約注文単位テーブル.xls」を参照。
            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
            
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
            
            //注文単位ＩＤ  (   order_unit_id   ):  null
                                                                                    
            //口座ＩＤ    (   account_id  ):  引数の補助口座.口座ID
            l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                    
            //補助口座ＩＤ  (   sub_account_id  ):  引数の補助口座.補助口座ID 
            l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                 
            //部店ＩＤ    (   branch_id   ):  引数の補助口座.部店ID      
            l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                              
            //取引者ＩＤ
            //クライアントリクエストサービス.get代理入力者().取引者ID
            //※get代理入力者()の戻り値==nullの場合は、nullをセット
            WEB3ToSuccClientRequestService l_clientRequestService = new WEB3ToSuccClientRequestService();
            Trader l_trader = l_clientRequestService.getTrader();
            if (l_trader != null)
            {
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }      
                                                                                         
            //注文ＩＤ    (   order_id    ):  引数の注文ID
            l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                                 
            //注文種別    (   order_type  ):  引数の建株.isLong()==trueの場合は、"買建返済注文（売返済）"。
            //以外、"売建返済注文（買返済）"。
            if (l_equityContract.isLong())
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
            }
            else
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_SHORT);
            }
                                                                                       
            //注文カテゴリ  (   order_categ ):     "返済注文"       
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
                                                                         
            //注文履歴最終通番    (   last_order_action_serial_no ):  1     
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                              
            //連続注文取引区分    (   reserve_order_trading_type  ):  引数の連続注文取引区分    
            l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                             
            //銘柄タイプ   (   product_type    ):  1：株式                                                                        
            l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            
            //市場ＩＤ    (   market_id   ):  引数の建株.市場ID                                                                                
            l_rsvEqOrderUnitParams.setMarketId(l_equityContract.getMarketId());
            
            //注文数量    (   quantity    ):  引数の注文内容.getTotalQuantity()
            //(* 返済建株数量の合計)
            l_rsvEqOrderUnitParams.setQuantity(l_marginSettleContractOrderSpec.getTotalQuantity());
                                                                                   
            //指値  (   limit_price ):  引数の単価調整値 != nullの場合、nullをセット。
            //以外、引数の注文内容.getLimitPrice() 
            if (l_priceAdjustValue != null)
            {
                l_rsvEqOrderUnitParams.setLimitPrice(null);                                                               
            }
            else
            {
                l_rsvEqOrderUnitParams.setLimitPrice(l_marginSettleContractOrderSpec.getLimitPrice());
            }            
            
            //単価調整値   (   price_adjust_value  ):  "引数の単価調整値
            //※nullの場合はnullをセット" 
            if (l_priceAdjustValue != null)
            {
                l_rsvEqOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);                                                       
            }        
            
            //注文失効日付  (   expiration_date ):  引数の注文内容.getOrderExpDate()                                                                       
            l_rsvEqOrderUnitParams.setExpirationDate(l_marginSettleContractOrderSpec.getOrderExpDate());
            
            //注文状態    (   order_status    ):  1:受付済（新規注文）                                                                     
            l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            //注文有効状態  (   order_open_status   ):  1:オープン                                                                      
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            //失効区分    (   expiration_status   ):  1:オープン 
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                                 
            //税区分 (   tax_type    ):  引数の注文内容.getTaxType()                                                                        
            l_rsvEqOrderUnitParams.setTaxType(l_marginSettleContractOrderSpec.getTaxType());
            
            //税区分（現引現渡）   (   swap_tax_type   ):  0：その他
            l_rsvEqOrderUnitParams.setSwapTaxType(TaxTypeEnum.UNDEFINED);
                                                                                   
            //弁済区分    (   repayment_type  ):  引数の建株.弁済区分
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_equityContract.getDataSourceObject();
            
            l_rsvEqOrderUnitParams.setRepaymentType(
                l_eqtypeContractRow.getRepaymentType());
                                                                                           
            //弁済期限値   (   repayment_num   ): 引数の建株.弁済期限値()    
            l_rsvEqOrderUnitParams.setRepaymentNum(
                l_eqtypeContractRow.getRepaymentNum());                                                                    
            
            //発注日 (   biz_date    ):  取引時間管理.get発注日()                                                                     
            l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
            
            //銘柄ＩＤ    (   product_id  ):  引数の建株.銘柄ID                                                                                
            l_rsvEqOrderUnitParams.setProductId(l_eqtypeContractRow.getProductId());
            
            //初回注文の注文チャネル (   order_chanel    ):  ログインセッションより取得してセット    
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                              
            l_rsvEqOrderUnitParams.setOrderChanel(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
            
            //受注日時    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
            l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //注文単価    (   price   ):  引数の計算結果.get計算単価()                                                                 
            l_rsvEqOrderUnitParams.setPrice(l_equityRealizedProfitAndLossPrice.getCalcUnitPrice());
            
            //概算受渡代金  (   estimated_price ):  引数の計算結果.get概算決済損益代金()                                                               
            l_rsvEqOrderUnitParams.setEstimatedPrice(
                l_equityRealizedProfitAndLossPrice.getEstimatedRealizedProfitAndLossAmount());
            
            //譲渡益金額   (   capital_gain    ):  0                                                                       
            l_rsvEqOrderUnitParams.setCapitalGain(0);
            
            //譲渡益税額   (   capital_gain_tax    ):  0                                                                       
            l_rsvEqOrderUnitParams.setCapitalGainTax(0);
            
            //注文経路区分  (   order_root_div  ):  ログインセッションより取得してセット                                                                      
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
            
            //決済順序区分  (   closing_order_type  ):  引数の注文内容.get決済順序区分                                                                       
            l_rsvEqOrderUnitParams.setClosingOrderType(
                l_marginSettleContractOrderSpec.getClosingOrderType());
                       
            //初回注文の注文単位ＩＤ (   first_order_unit_id ):  引数の注文内容.初回注文の注文単位ID                                                                     
            l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_marginSettleContractOrderSpec.getFirstOrderUnitId());
            
            //発注エラーコード    (   order_error_code    ):  null                                                                        
            
            //親注文の注文ＩＤ    (   parent_order_id ):  引数の親注文の注文単位.注文ID                                                                        
            l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
            
            //親注文の注文単位ＩＤ  (   parent_order_unit_id    ):  引数の親注文の注文単位.注文単位ID                                                                      
            l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
            
            //親注文内連番  (   serial_no_in_parent ):  連続注文マネージャ.get株式親注文内連番(引数の親注文の注文単位.注文単位ID)                                                                       
            l_rsvEqOrderUnitParams.setSerialNoInParent(
                (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
            
            //作成日付    (   created_timestamp   ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
            l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //更新日付    (   last_updated_timestamp  ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        
            //３）　@反対売買でない場合(*1)、 
            //パラメータ.注文内容.getSettleContractOrderEntries()の 
            //戻り値の要素数分、株式予約建株返済指定情報テーブルに 
            //  レコードを登録する。 
            boolean l_blnIsReverseTrade = 
                this.isReversingTrade(l_strRsvOrderTradingType, l_parentOrderUnit);
        
            if (!l_blnIsReverseTrade)
            {
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries = 
                    l_marginSettleContractOrderSpec.getSettleContractOrderEntries();
                
                int l_intCnt = 0;
                if (l_eqTypeSettleContractOrderEntries != null && 
                    l_eqTypeSettleContractOrderEntries.length > 0)
                {
                    l_intCnt = l_eqTypeSettleContractOrderEntries.length;
                }
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                        new RsvEqClosingContractSpecParams();
                    
                    //口座ＩＤ(account_id): 引数の補助口座.口座ID
                    l_rsvEqClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());
                                                                                          
                    //補助口座ＩＤ(sub_account_id): 引数の補助口座.補助口座ID 
                    l_rsvEqClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                         
                    //注文ＩＤ(order_id): 引数の注文ID 
                    l_rsvEqClosingContractSpecParams.setOrderId(l_lngOrderId);
                                                                                        
                    //建株ID(contract_id): getSettleContractOrderEntries()の戻り値[index].getContractId() 
                    l_rsvEqClosingContractSpecParams.setContractId(
                        l_eqTypeSettleContractOrderEntries[i].getContractId());
                                                                                          
                    //返済連番(closing_serial_no): index + 1          
                    l_rsvEqClosingContractSpecParams.setClosingSerialNo(i + 1);
                                                                                
                    //返済注文数量(quantity): getSettleContractOrderEntries()の戻り値[index].getQuantity()   
                    l_rsvEqClosingContractSpecParams.setQuantity(
                        l_eqTypeSettleContractOrderEntries[i].getQuantity());
                                                                                       
                    //作成日付(created_timestamp): 現在日時（GtlUtils.getSystemTimestamp()） 
                    l_rsvEqClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                                                                                           
                    //更新日付(last_updated_timestamp): 現在日時（GtlUtils.getSystemTimestamp()）
                    l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doInsertQuery(l_rsvEqClosingContractSpecParams); //DataNetworkException,DataQueryException      
                }
            }        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //４）　@予約注文履歴を作成する。
        //株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //５）　@親注文の注文単位に、予約注文登録を記録する。
        //this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit信用現引現渡新規予約注文)<BR>
     * （submitEqtypeSwapContractNewOrder）<BR>
     * <BR>
     * 株式予約注文単位テーブルに、信用現引現渡の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@株式予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@登録の仕様は、DB更新仕様<BR>
     * 　@「（連続）信用現引現渡注文登録_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@反対売買でない場合(*1)、<BR>
     * 　@パラメータ.注文内容.getSettleContractOrderEntries()の<BR>
     * 　@戻り値の要素数分、株式予約建株返済指定情報テーブルに<BR>
     * 　@レコードを登録する。<BR>
     * <BR>
     * 　@登録の仕様は、DB更新仕様<BR>
     * 　@「（連続）信用現引現渡注文登録_株式予約建株返済指定情報テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ４）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。<BR>
     * <BR>
     * ５）　@親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * 　@this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。<BR>
     * <BR>
     * (*1)反対売買でない場合・・・<BR>
     * 　@連続注文マネージャImpl.is反対売買取引() == false。<BR>
     * 　@[is反対売買取引()に指定する引数]<BR>
     * 　@　@連続注文取引区分：　@引数の同項目<BR>
     * 　@　@親注文の注文単位：　@引数の同項目<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_marginSwapContractOrderSpec - (注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 登録用の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。<BR>
     * @@param l_dblCapitalGain - (譲渡益金額)<BR>
     * 譲渡益金額。<BR>
     * @@param l_dblCapitalGainTax - (譲渡益税額)<BR>
     * 譲渡益税額。<BR>
     * @@param l_equityContract - (建株)<BR>
     * 建株オブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4340E44D0302
     */
    public void submitEqtypeSwapContractNewOrder(
        SubAccount l_subAccount, 
        WEB3MarginSwapContractOrderSpec l_marginSwapContractOrderSpec, 
        long l_lngOrderId,         
        String l_strTradingPassword, 
        String l_strRsvOrderTradingType, 
        EqTypeOrderUnit l_parentOrderUnit, 
        double l_dblEstimatedPrice, 
        double l_dblCapitalGain, 
        double l_dblCapitalGainTax, 
        WEB3EquityContract l_equityContract) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeSwapContractNewOrder(SubAccount, WEB3MarginSwapContractOrderSpec," +
            " long, String, String, EqTypeOrderUnit, double, double, double, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_marginSwapContractOrderSpec == null)
        {
            log.debug("注文内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文内容 = null。");
        }
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }        
        
        if (l_equityContract == null)
        {
            log.debug("建株 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "建株 = null。");
        }
        
        try
        {
            //１）　@取引パスワードをチェックする。
            //注文チェック.validate取引パスワード()をコールする。 
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードをチェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードをチェックエラー");
            }
            
            //２）　@株式予約注文単位テーブルにレコードを登録する。 
            //登録の仕様は、DB更新仕様
            //「（連続）信用現引現渡注文登録_株式予約注文単位テーブル.xls」を参照。
            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
            
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams();
            
            //注文単位ＩＤ  (   order_unit_id   ):  null
                                                                                    
            //口座ＩＤ    (   account_id  ):  引数の補助口座.口座ID
            l_rsvEqOrderUnitParams.setAccountId(l_subAccount.getAccountId());
                                                                                    
            //補助口座ＩＤ  (   sub_account_id  ):  引数の補助口座.補助口座ID 
            l_rsvEqOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                 
            //部店ＩＤ    (   branch_id   ):  引数の補助口座.部店ID      
            l_rsvEqOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());
                                                                              
            //取引者ＩＤ
            //クライアントリクエストサービス.get代理入力者().取引者ID
            //※get代理入力者()の戻り値==nullの場合は、nullをセット
            WEB3ToSuccClientRequestService l_clientRequestService = new WEB3ToSuccClientRequestService();
            Trader l_trader = l_clientRequestService.getTrader();
            if (l_trader != null)
            {
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }      
                                                                                         
            //注文ＩＤ    (   order_id    ):  引数の注文ID
            l_rsvEqOrderUnitParams.setOrderId(l_lngOrderId);
                                                                                 
            //注文種別    (   order_type  ):  引数の建株.isLong()==trueの場合は、"現引注文"。
            //以外、"現渡注文"。
            if (l_equityContract.isLong())
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            }
            else
            {
                l_rsvEqOrderUnitParams.setOrderType(OrderTypeEnum.SWAP_MARGIN_SHORT);
            }
                                                                                       
            //注文カテゴリ  (   order_categ ):      "現引・現渡注文"       
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
                                                                         
            //注文履歴最終通番    (   last_order_action_serial_no ):  1     
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(1);
                                                                              
            //連続注文取引区分    (   reserve_order_trading_type  ):  引数の連続注文取引区分    
            l_rsvEqOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);
                                                                             
            //銘柄タイプ   (   product_type    ):  1：株式                                                                        
            l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            
            //市場ＩＤ    (   market_id   ):  引数の建株.市場ID                                                                                
            l_rsvEqOrderUnitParams.setMarketId(l_equityContract.getMarketId());
            
            //注文数量    (   quantity    ):  引数の注文内容.getTotalQuantity()
            //((* 決済建株数量の合計))
            l_rsvEqOrderUnitParams.setQuantity(l_marginSwapContractOrderSpec.getTotalQuantity());
                                                                                   
            //指値  (   limit_price ):  null                                                                                
            //単価調整値   (   price_adjust_value  ): null
            
            //注文失効日付  (   expiration_date ):  this.発注日と同じ値
            //（* 取引時間管理.get発注日()）                                                                       
            l_rsvEqOrderUnitParams.setExpirationDate(WEB3GentradeTradingTimeManagement.getOrderBizDate());
            
            //注文状態    (   order_status    ):  1:受付済（新規注文）                                                                     
            l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            
            //注文有効状態  (   order_open_status   ):  1:オープン                                                                      
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            
            //失効区分    (   expiration_status   ):  1:オープン 
            l_rsvEqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
                                                                                 
            //税区分 (   tax_type    ):  引数の注文内容.getTaxType()                                                                        
            l_rsvEqOrderUnitParams.setTaxType(l_marginSwapContractOrderSpec.getTaxType());
            
            //税区分（現引現渡）   (   swap_tax_type   ):  引数の注文内容.get税区分（現引現渡）()
            l_rsvEqOrderUnitParams.setSwapTaxType(l_marginSwapContractOrderSpec.getSwapTaxType());
                                                                                   
            //弁済区分    (   repayment_type  ):  引数の建株.弁済区分
            EqtypeContractRow l_eqtypeContractRow = (EqtypeContractRow) l_equityContract.getDataSourceObject();
            
            l_rsvEqOrderUnitParams.setRepaymentType(
                l_eqtypeContractRow.getRepaymentType());
                                                                                           
            //弁済期限値   (   repayment_num   ): 引数の建株.弁済期限値()    
            l_rsvEqOrderUnitParams.setRepaymentNum(
                l_eqtypeContractRow.getRepaymentNum());                                                                    
            
            //発注日 (   biz_date    ):  取引時間管理.get発注日()                                                                     
            l_rsvEqOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd"));
            
            //銘柄ＩＤ    (   product_id  ):  引数の建株.銘柄ID                                                                                
            l_rsvEqOrderUnitParams.setProductId(l_eqtypeContractRow.getProductId());
            
            //初回注文の注文チャネル (   order_chanel    ):  ログインセッションより取得してセット    
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
                                                                              
            l_rsvEqOrderUnitParams.setOrderChanel(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));
            
            //受注日時    (   received_date_time  ):  GtlUtils.getSystemTimestamp()                                                                       
            l_rsvEqOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());
            
            //注文単価    (   price   ):  null                                                                
            
            //概算受渡代金  (   estimated_price ):  引数の概算受渡代金                                                              
            l_rsvEqOrderUnitParams.setEstimatedPrice(l_dblEstimatedPrice);
            
            //譲渡益金額   (   capital_gain    ):  引数の譲渡益金額                                                                       
            l_rsvEqOrderUnitParams.setCapitalGain(l_dblCapitalGain);
            
            //譲渡益税額   (   capital_gain_tax    ):  引数の譲渡益税額                                                                       
            l_rsvEqOrderUnitParams.setCapitalGainTax(l_dblCapitalGainTax);
            
            //注文経路区分  (   order_root_div  ):  ログインセッションより取得してセット                                                                      
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));
            
            //決済順序区分  (   closing_order_type  ):  引数の注文内容.get決済順序区分                                                                       
            l_rsvEqOrderUnitParams.setClosingOrderType(
                l_marginSwapContractOrderSpec.getClosingOrderType());
                       
            //初回注文の注文単位ＩＤ (   first_order_unit_id ):  null                          
            //発注エラーコード    (   order_error_code    ):  null                                                                        
            
            //親注文の注文ＩＤ    (   parent_order_id ):  引数の親注文の注文単位.注文ID                                                                        
            l_rsvEqOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());
            
            //親注文の注文単位ＩＤ  (   parent_order_unit_id    ):  引数の親注文の注文単位.注文単位ID                                                                      
            l_rsvEqOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());
            
            //親注文内連番  (   serial_no_in_parent ):  連続注文マネージャ.get株式親注文内連番(引数の親注文の注文単位.注文単位ID)                                                                       
            l_rsvEqOrderUnitParams.setSerialNoInParent(
                (int)this.getEqtypeSerialNoInParent(l_parentOrderUnit.getOrderUnitId()));
            
            //作成日付    (   created_timestamp   ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
            l_rsvEqOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            
            //更新日付    (   last_updated_timestamp  ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doInsertQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        
            //３）　@反対売買でない場合(*1)、 
            //パラメータ.注文内容.getSettleContractOrderEntries()の 
            //戻り値の要素数分、株式予約建株返済指定情報テーブルに 
            //  レコードを登録する。 
            boolean l_blnIsReverseTrade = 
                this.isReversingTrade(l_strRsvOrderTradingType, l_parentOrderUnit);
        
            if (!l_blnIsReverseTrade)
            {
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries = 
                    l_marginSwapContractOrderSpec.getSettleContractOrderEntries();
                
                int l_intCnt = 0;
                if (l_eqTypeSettleContractOrderEntries != null && 
                    l_eqTypeSettleContractOrderEntries.length > 0)
                {
                    l_intCnt = l_eqTypeSettleContractOrderEntries.length;
                }
                
                for (int i = 0; i < l_intCnt; i++)
                {
                    RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = 
                        new RsvEqClosingContractSpecParams();
                    
                    //口座ＩＤ(account_id): 引数の補助口座.口座ID
                    l_rsvEqClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());
                                                                                          
                    //補助口座ＩＤ(sub_account_id): 引数の補助口座.補助口座ID 
                    l_rsvEqClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());
                                                                                         
                    //注文ＩＤ(order_id): 引数の注文ID 
                    l_rsvEqClosingContractSpecParams.setOrderId(l_lngOrderId);
                                                                                        
                    //建株ID(contract_id): getSettleContractOrderEntries()の戻り値[index].getContractId() 
                    l_rsvEqClosingContractSpecParams.setContractId(
                        l_eqTypeSettleContractOrderEntries[i].getContractId());
                                                                                          
                    //返済連番(closing_serial_no): index + 1          
                    l_rsvEqClosingContractSpecParams.setClosingSerialNo(i + 1);
                                                                                
                    //返済注文数量(quantity): getSettleContractOrderEntries()の戻り値[index].getQuantity()   
                    l_rsvEqClosingContractSpecParams.setQuantity(
                        l_eqTypeSettleContractOrderEntries[i].getQuantity());
                                                                                       
                    //作成日付(created_timestamp): 現在日時（GtlUtils.getSystemTimestamp()） 
                    l_rsvEqClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                                                                                           
                    //更新日付(last_updated_timestamp): 現在日時（GtlUtils.getSystemTimestamp()）
                    l_rsvEqClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doInsertQuery(l_rsvEqClosingContractSpecParams); //DataNetworkException,DataQueryException      
                }
            }        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //４）　@予約注文履歴を作成する。
        //株式予約注文更新サービス.insert株式予約注文履歴(引数の注文ID)をコールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);        
        
        //５）　@親注文の注文単位に、予約注文登録を記録する。
        //this.set予約注文設定To株式親注文(引数の親注文の注文単位)をコールする。
        this.setReserveOrderSettingToEqtypeParentOrder(l_parentOrderUnit);   
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit先物OP新規建新規予約注文)<BR>
     * （submitIfoOpenContractNewOrder）<BR>
     * <BR>
     * 先物OP予約注文単位テーブルに、先物OP新規建の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@先物OP予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@２－１）　@パラメータ.連続注文取引区分が<BR>
     * 　@　@　@　@　@　@"先物新規建（前提注文）" 又は "先物新規建" の場合<BR>
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）先物新規建注文登録_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * 　@２－２）　@パラメータ.連続注文取引区分が<BR>
     * 　@　@　@　@　@　@"OP新規建（前提注文）" 又は "OP新規建" の場合<BR>
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）OP新規建注文登録_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.insert予約注文履歴(引数の注文ID)をコールする。<BR>
     * <BR>
     * ４）　@親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * 　@this.set予約注文設定To先物OP親注文(引数の親注文の注文単位)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_ifoOpenContractOrderSpec - (注文内容)<BR>
     * 新規建注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 登録用の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。<BR>
     * @@param l_priceAdjustValue - (単価調整値)<BR>
     * 単価調整値。 <BR>
     * （指定なしの場合はnullをセット）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@param l_ifoEstimateDeliveryAmountCalcResult - (計算結果)<BR>
     * 概算受渡代金計算結果オブジェクト。
     * @@throws WEB3BaseException
     */
    public void submitIfoOpenContractNewOrder(
        SubAccount l_subAccount,
        WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        String l_strRsvOrderTradingType,
        Double l_priceAdjustValue,
        IfoOrderUnit l_parentOrderUnit,
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoOpenContractNewOrder(SubAccount, WEB3IfoOpenContractOrderSpec," +
            " long, String, String, Double, IfoOrderUnit, WEB3IfoEstimateDeliveryAmountCalcResult)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "補助口座 = null。");
        }

        if (l_ifoOpenContractOrderSpec == null)
        {
            log.debug("注文内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "注文内容 = null。");
        }

        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }

        if (l_ifoEstimateDeliveryAmountCalcResult == null)
        {
            log.debug("計算結果 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                 "計算結果 = null。");
        }

        try
        {
            //１）　@取引パスワードをチェックする。
            //注文チェック.validate取引パスワード()をコールする。
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードをチェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードをチェックエラー");
            }

            //２）　@先物OP予約注文単位テーブルにレコードを登録する。
            //２－１）　@パラメータ.連続注文取引区分が "先物新規建（前提注文）" 又は "先物新規建" の場合
            //登録の仕様は、DB更新仕様
            //「（連続）先物新規建注文登録_先物OP予約注文単位テーブル.xls」を参照。
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();

            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)(l_finApp.getTradingModule(ProductTypeEnum.IFO)).getProductManager();
            Market l_market = null;
            IfoProduct l_ifoProduct = null;
            try
            {
                l_market = l_finObjectManager.getMarket(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_ifoOpenContractOrderSpec.getMarketCode());
                l_ifoProduct = l_productManager.getIfoProduct(
                    l_subAccount.getInstitution(), l_ifoOpenContractOrderSpec.getProductCode());
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

            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

            if (WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.OPEN_FUTURE.equals(l_strRsvOrderTradingType))
            {
                //口座ＩＤ(account_id):引数の補助口座.口座ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //補助口座ＩＤ(sub_account_id):引数の補助口座.補助口座ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //部店ＩＤ(branch_id):引数の補助口座.部店ID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //取引者ＩＤ(trader_id):引数の注文内容.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoOpenContractOrderSpec.getTraderIdAsObject());

                //注文ＩＤ(order_id):引数の注文ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //注文種別(order_type):引数の注文内容.isBuyToOpenOrder() == trueの場合、"先物新規買建注文"
                //以外、"先物新規売建注文"
                if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
                }

                //注文カテゴリ(order_categ):"先物新規建注文"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);

                //注文履歴最終通番(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //連続注文取引区分(reserve_order_trading_type):引数の連続注文取引区分
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //銘柄タイプ(product_type):6：先物オプション
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //先物／オプション区分(future_option_div):1：先物
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);

                //市場ＩＤ(market_id):引数の注文内容.市場コードに該当する市場.市場ID
                l_rsvIfoOrderUnitParams.setMarketId(l_market.getMarketId());

                //注文数量(quantity):引数の注文内容.getQuantity()
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoOpenContractOrderSpec.getQuantity());

                //指値(limit_price):引数の単価調整値 != nullの場合、nullをセット。
                //以外、引数の注文内容.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoOpenContractOrderSpec.getLimitPrice());
                }

                //単価調整値(price_adjust_value):引数の単価調整値
                //※nullの場合はnullをセット
                l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);

                //注文失効日付(expiration_date):引数の注文内容.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoOpenContractOrderSpec.getOrderExpDate());

                //注文状態(order_status):1:受付済（新規注文）
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //注文有効状態(order_open_status):1:オープン
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //失効区分(expiration_status):1:オープン
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //税区分(tax_type):0：その他
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //発注日(biz_date):取引時間管理.get発注日()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //銘柄ＩＤ(product_id):引数の注文内容.get銘柄コード( )に対応する銘柄ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoProduct.getProductId());

                //初回注文の注文チャネル(order_chanel):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderChanel(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //受注日時(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //扱者コード（SONAR）(sonar_trader_code):顧客.扱者コード（SONAR）
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //注文単価(price):計算結果.get計算単価()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //概算受渡代金(estimated_price):計算結果.get概算受渡代金()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //注文経路区分(order_root_div):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //注文エラー理由コード(error_reason_code):0000：正常
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //初回注文の注文単位ＩＤ(first_order_unit_id):引数の注文内容.get初回注文の注文単位ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoOpenContractOrderSpec.getFirstOrderUnitId());

                //親注文の注文ＩＤ(parent_order_id):引数の親注文の注文単位.注文ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //親注文の注文単位ＩＤ(parent_order_unit_id):引数の親注文の注文単位.注文単位ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //親注文内連番(serial_no_in_parent):連続注文マネージャ.get先物OP親注文内連番(引数の親注文の注文単位.注文単位ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //夕場前繰越対象フラグ(evening_session_carryover_flag):引数の注文内容.get夕場前繰越対象フラグ()
                if (l_ifoOpenContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
                }

                //立会区分(session_type):取引時間管理.get立会区分()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //注文期限区分(expiration_date_type):引数の注文内容.get注文期限区分()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoOpenContractOrderSpec.getExpirationDateType());

                //作成日付(created_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //更新日付(last_updated_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            //２－２）　@パラメータ.連続注文取引区分が"OP新規建（前提注文）" 又は "OP新規建" の場合
            //登録の仕様は、DB更新仕様
            //「（連続）OP新規建注文登録_先物OP予約注文単位テーブル.xls」を参照。
            else if (WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.OPEN_OP.equals(l_strRsvOrderTradingType))
            {
                //口座ＩＤ(account_id):引数の補助口座.口座ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //補助口座ＩＤ(sub_account_id):引数の補助口座.補助口座ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //部店ＩＤ(branch_id):引数の補助口座.部店ID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //取引者ＩＤ(trader_id):引数の注文内容.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoOpenContractOrderSpec.getTraderIdAsObject());

                //注文ＩＤ(order_id):引数の注文ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //注文種別(order_type):引数の注文内容.isBuyToOpenOrder() == trueの場合、
                //"OP新規買建注文" 以外、"OP新規売建注文"
                if (l_ifoOpenContractOrderSpec.isBuyToOpenOrder())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);
                }

                //注文カテゴリ(order_categ): "OP新規建注文"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);

                //注文履歴最終通番(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //連続注文取引区分(reserve_order_trading_type):引数の連続注文取引区分
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //銘柄タイプ(product_type):6：先物オプション
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //先物／オプション区分(future_option_div):2：オプション
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);

                //市場ＩＤ(market_id):引数の注文内容.市場コードに該当する市場.市場ID
                l_rsvIfoOrderUnitParams.setMarketId(l_market.getMarketId());

                //注文数量(quantity):引数の注文内容.getQuantity()
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoOpenContractOrderSpec.getQuantity());

                //指値(limit_price):引数の単価調整値 != nullの場合、nullをセット。
                //以外、引数の注文内容.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoOpenContractOrderSpec.getLimitPrice());
                }

                //単価調整値(price_adjust_value):引数の単価調整値
                //※nullの場合はnullをセット
                l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);

                //注文失効日付(expiration_date):引数の注文内容.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoOpenContractOrderSpec.getOrderExpDate());

                //注文状態(order_status):1:受付済（新規注文）
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //注文有効状態(order_open_status):1:オープン
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //失効区分(expiration_status):1:オープン
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //税区分(tax_type):0：その他
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //発注日(biz_date):取引時間管理.get発注日()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //銘柄ＩＤ(product_id):引数の注文内容.get銘柄コード( )に対応する銘柄ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoProduct.getProductId());

                //初回注文の注文チャネル(order_chanel):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderChanel(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //受注日時(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //扱者コード（SONAR）(sonar_trader_code):顧客.扱者コード（SONAR）
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //注文単価(price):計算結果.get計算単価()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //概算受渡代金(estimated_price):計算結果.get概算受渡代金()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //注文経路区分(order_root_div):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //注文エラー理由コード(error_reason_code):0000：正常
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //初回注文の注文単位ＩＤ(first_order_unit_id):引数の注文内容.get初回注文の注文単位ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoOpenContractOrderSpec.getFirstOrderUnitId());

                //親注文の注文ＩＤ(parent_order_id):引数の親注文の注文単位.注文ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //親注文の注文単位ＩＤ(parent_order_unit_id):引数の親注文の注文単位.注文単位ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //親注文内連番(serial_no_in_parent):連続注文マネージャ.get先物OP親
                //注文内連番(引数の親注文の注文単位.注文単位ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //夕場前繰越対象フラグ(evening_session_carryover_flag):引数の注文内容.get夕場前繰越対象フラグ()
                if (l_ifoOpenContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
                }

                //立会区分(session_type):取引時間管理.get立会区分()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //注文期限区分(expiration_date_type):引数の注文内容.get注文期限区分()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoOpenContractOrderSpec.getExpirationDateType());

                //作成日付(created_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //更新日付(last_updated_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）予約注文履歴を作成する。
        //先物OP予約注文更新サービス.insert予約注文履歴(引数の注文ID)をコールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        //４）親注文の注文単位に、予約注文登録を記録する。
        //this.set予約注文設定To先物OP親注文(引数の親注文の注文単位)をコールする。
        this.setReserveOrderSettingToIfoParentOrder(l_parentOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit先物OP返済新規予約注文)<BR>
     * （submitIfoCloseContractNewOrder）<BR>
     * <BR>
     * 先物OP予約注文単位テーブルに、先物OP返済の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@先物OP予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@２－１）　@パラメータ.連続注文取引区分が<BR>
     * 　@　@　@　@　@　@"先物返済（前提注文）" 又は "先物返済（既存残）" の場合<BR>
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）先物返済注文登録_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * 　@２－２）　@パラメータ.連続注文取引区分が<BR>
　@　@　@　@　@　@"OP返済（前提注文）" 又は "OP返済（既存残）" の場合<BR>
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）OP返済注文登録_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * ３）　@反対売買でない場合(*1)、<BR>
     * 　@パラメータ.注文内容.getSettleContractEntries()の<BR>
     * 　@戻り値の要素数分、先物OP予約建玉返済指定情報テーブルに<BR>
     * 　@レコードを登録する。<BR>
     * <BR>
     * 　@３－１）　@パラメータ.連続注文取引区分が"先物返済（既存残）"の場合
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）先物返済注文登録_先物OP予約建玉返済指定情報テーブル仕様.xls」を参照。<BR>
     * <BR>
     * 　@３－２）　@パラメータ.連続注文取引区分が"OP返済（既存残）"の場合
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）OP返済注文登録_先物OP予約建玉返済指定情報テーブル仕様.xls」を参照。<BR>
     * <BR>
     * ４）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.insert予約注文履歴(引数の注文ID)をコールする。<BR>
     * <BR>
     * ５）　@親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * 　@this.set予約注文設定To先物OP親注文(引数の親注文の注文単位)をコールする。<BR>
     * <BR>
     * (*1)反対売買でない場合・・・<BR>
     * 　@連続注文マネージャImpl.is反対売買取引() == false。<BR>
     * 　@[is反対売買取引()に指定する引数]<BR>
     * 　@　@連続注文取引区分：　@引数の同項目<BR>
     * 　@　@親注文の注文単位：　@引数の同項目<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_ifoSettleContractOrderSpec - (注文内容)<BR>
     * 返済注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 登録用の注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_strRsvOrderTradingType - (連続注文取引区分)<BR>
     * 連続注文取引区分。<BR>
     * @@param l_priceAdjustValue - (単価調整値)<BR>
     * 単価調整値。<BR>
     * （指定なしの場合はnullをセット）<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@param l_ifoEstimateDeliveryAmountCalcResult - (計算結果)<BR>
     * 概算受渡代金計算結果オブジェクト。<BR>
     * @@param l_ifoContractImpl - (建玉)<BR>
     * 建玉オブジェクト<BR>
     * @@param l_strClosingOrder - (決済順序)<BR>
     * 決済順序
     * @@throws WEB3BaseException
     */
    public void submitIfoCloseContractNewOrder(
        SubAccount l_subAccount,
        WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec,
        long l_lngOrderId,
        String l_strTradingPassword,
        String l_strRsvOrderTradingType,
        Double l_priceAdjustValue,
        IfoOrderUnit l_parentOrderUnit,
        WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult,
        WEB3IfoContractImpl l_ifoContractImpl,
        String l_strClosingOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoCloseContractNewOrder(SubAccount, WEB3IfoSettleContractOrderSpec," +
            " long, String, String, Double, IfoOrderUnit, WEB3IfoEstimateDeliveryAmountCalcResult," +
            "WEB3IfoContractImpl, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "補助口座 = null。");
        }

        if (l_ifoSettleContractOrderSpec == null)
        {
            log.debug("注文内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文内容 = null。");
        }

        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "親注文の注文単位 = null。");
        }

        if (l_ifoEstimateDeliveryAmountCalcResult == null)
        {
            log.debug("計算結果 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "計算結果 = null。");
        }

        if (l_ifoContractImpl == null)
        {
            log.debug("建玉 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "建玉 = null。");
        }

        try
        {
            //１）　@取引パスワードをチェックする。
            //注文チェック.validate取引パスワード()をコールする。
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードをチェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードをチェックエラー");
            }

            //２）先物OP予約注文単位テーブルにレコードを登録する。
            //２－１）　@パラメータ.連続注文取引区分が"先物返済（前提注文）" 又は "先物返済（既存残）" の場合
            //登録の仕様は、DB更新仕様
            //「（連続）先物返済注文登録_先物OP予約注文単位テーブル.xls」を参照。
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();

            SubAccountRow l_subAccountRow = (SubAccountRow)l_subAccount.getDataSourceObject();

            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_subAccount.getMainAccount().getDataSourceObject();

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(l_strRsvOrderTradingType))
            {
                //口座ＩＤ(account_id):引数の補助口座.口座ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //補助口座ＩＤ(sub_account_id):引数の補助口座.補助口座ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //部店ＩＤ(branch_id):引数の補助口座.部店ID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //取引者ＩＤ(trader_id):引数の注文内容.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoSettleContractOrderSpec.getTraderIdAsObject());

                //注文ＩＤ(order_id):引数の注文ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //注文種別(order_type):引数の建玉.isLong()==trueの場合は、"先物買建売返済注文（売返済）"。
                //以外、"先物売建買返済注文（買返済）"。
                if (l_ifoContractImpl.isLong())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
                }

                //注文カテゴリ(order_categ): "先物返済注文"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);

                //注文履歴最終通番(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //連続注文取引区分(reserve_order_trading_type):引数の連続注文取引区分
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //銘柄タイプ(product_type):6：先物オプション
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //先物／オプション区分(future_option_div):1：先物
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.FUTURES);

                //市場ＩＤ(market_id):引数の建玉.市場ID
                l_rsvIfoOrderUnitParams.setMarketId(l_ifoContractImpl.getMarketId());

                //注文数量(quantity):引数の注文内容.getTotalQuantity()
                //(* 返済建玉数量の合計)
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoSettleContractOrderSpec.getTotalQuantity());

                //指値(limit_price):引数の単価調整値 != nullの場合、nullをセット。
                //以外、引数の注文内容.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoSettleContractOrderSpec.getLimitPrice());
                }

                //単価調整値(price_adjust_value):引数の単価調整値
                //※nullの場合はnullをセット
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);
                }

                //注文失効日付(expiration_date):引数の注文内容.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoSettleContractOrderSpec.getOrderExpDate());

                //注文状態(order_status):1:受付済（新規注文）
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //注文有効状態(order_open_status):1:オープン
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //失効区分(expiration_status):1:オープン
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //税区分(tax_type):0：その他
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //発注日(biz_date):取引時間管理.get発注日()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //銘柄ＩＤ(product_id):引数の建玉.銘柄ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoContractImpl.getProduct().getProductId());

                //初回注文の注文チャネル(order_chanel):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderChanel(
                        l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //受注日時(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //扱者コード（SONAR）(sonar_trader_code):顧客.扱者コード（SONAR）
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //注文単価(price):計算結果.get計算単価()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //概算受渡代金(estimated_price):計算結果.get概算受渡代金()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //注文経路区分(order_root_div):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //決済順序(closing_order):引数の決済順序
                l_rsvIfoOrderUnitParams.setClosingOrder(l_strClosingOrder);

                //注文エラー理由コード(error_reason_code):0000：正常
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //初回注文の注文単位ＩＤ(first_order_unit_id):引数の注文内容.get初回注文の注文単位ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoSettleContractOrderSpec.getFirstOrderUnitId());

                //親注文の注文ＩＤ(parent_order_id):引数の親注文の注文単位.注文ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //親注文の注文単位ＩＤ(parent_order_unit_id):引数の親注文の注文単位.注文単位ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //親注文内連番(serial_no_in_parent):連続注文マネージャ.get先物OP親注文内連番(引数の親注文の注文単位.注文単位ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //夕場前繰越対象フラグ(evening_session_carryover_flag):引数の注文内容.get夕場前繰越対象フラグ()
                if (l_ifoSettleContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.FALSE);
                }

                //立会区分(session_type):取引時間管理.get立会区分()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //注文期限区分(expiration_date_type):引数の注文内容.get注文期限区分()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoSettleContractOrderSpec.getExpirationDateType());

                //作成日付(created_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //更新日付(last_updated_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }
            //２－２）　@パラメータ.連続注文取引区分が"OP返済（前提注文）" 又は "OP返済（既存残）" の場合
            //登録の仕様は、DB更新仕様
            //「（連続）OP返済注文登録_先物OP予約注文単位テーブル.xls」を参照。
            else if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_ASSUMPTION_ORDER.equals(l_strRsvOrderTradingType)
                || WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(l_strRsvOrderTradingType))
            {
                //口座ＩＤ(account_id):引数の補助口座.口座ID
                l_rsvIfoOrderUnitParams.setAccountId(l_subAccount.getAccountId());

                //補助口座ＩＤ(sub_account_id):引数の補助口座.補助口座ID
                l_rsvIfoOrderUnitParams.setSubAccountId(l_subAccount.getSubAccountId());

                //部店ＩＤ(branch_id):引数の補助口座.部店ID
                l_rsvIfoOrderUnitParams.setBranchId(l_subAccountRow.getBranchId());

                //取引者ＩＤ(trader_id):引数の注文内容.getTraderIdAsObject( )
                l_rsvIfoOrderUnitParams.setTraderId(l_ifoSettleContractOrderSpec.getTraderIdAsObject());

                //注文ＩＤ(order_id):引数の注文ID
                l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

                //引数の建玉.isLong()==trueの場合は、"OP買建売返済注文（売返済）"。
                //以外、"OP売建買返済注文（買返済）"。
                if (l_ifoContractImpl.isLong())
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);
                }

                //注文カテゴリ(order_categ):"OP返済注文"
                l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);

                //注文履歴最終通番(last_order_action_serial_no):1
                l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

                //連続注文取引区分(reserve_order_trading_type):引数の連続注文取引区分
                l_rsvIfoOrderUnitParams.setReserveOrderTradingType(l_strRsvOrderTradingType);

                //銘柄タイプ(product_type):6：先物オプション
                l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);

                //先物／オプション区分(future_option_div):2：オプション
                l_rsvIfoOrderUnitParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.OPTION);

                //市場ＩＤ(market_id):引数の建玉.市場ID
                l_rsvIfoOrderUnitParams.setMarketId(l_ifoContractImpl.getMarketId());

                //注文数量(quantity):引数の注文内容.getTotalQuantity()
                //(* 返済建玉数量の合計)
                l_rsvIfoOrderUnitParams.setQuantity(l_ifoSettleContractOrderSpec.getTotalQuantity());

                //指値(limit_price):引数の単価調整値 != nullの場合、nullをセット。
                //以外、引数の注文内容.getLimitPrice()
                if (l_priceAdjustValue != null)
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(null);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoSettleContractOrderSpec.getLimitPrice());
                }

                //単価調整値(price_adjust_value):引数の単価調整値
                //※nullの場合はnullをセット
                l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_priceAdjustValue);

                //注文失効日付(expiration_date):引数の注文内容.getOrderExpDate()
                l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoSettleContractOrderSpec.getOrderExpDate());

                //注文状態(order_status):1:受付済（新規注文）
                l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

                //注文有効状態(order_open_status):1:オープン
                l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

                //失効区分(expiration_status):1:オープン
                l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

                //税区分(tax_type):0：その他
                l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);

                //発注日(biz_date):取引時間管理.get発注日()
                l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

                //銘柄ＩＤ(product_id):引数の建玉.銘柄ID
                l_rsvIfoOrderUnitParams.setProductId(l_ifoContractImpl.getProduct().getProductId());

                //初回注文の注文チャネル(order_chanel):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderChanel(
                        l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL));

                //受注日時(received_date_time):GtlUtils.getSystemTimestamp()
                l_rsvIfoOrderUnitParams.setReceivedDateTime(GtlUtils.getSystemTimestamp());

                //扱者コード（SONAR）(sonar_trader_code):顧客.扱者コード（SONAR）
                l_rsvIfoOrderUnitParams.setSonarTraderCode(l_mainAccountRow.getSonarTraderCode());

                //注文単価(price):計算結果.get計算単価()
                l_rsvIfoOrderUnitParams.setPrice(l_ifoEstimateDeliveryAmountCalcResult.getCalcUnitPrice());

                //概算受渡代金(estimated_price):計算結果.get概算受渡代金()
                l_rsvIfoOrderUnitParams.setEstimatedPrice(
                    l_ifoEstimateDeliveryAmountCalcResult.getEstimateDeliveryAmount());

                //注文経路区分(order_root_div):ログインセッションより取得してセット
                l_rsvIfoOrderUnitParams.setOrderRootDiv(
                    l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

                //決済順序(closing_order):引数の決済順序
                l_rsvIfoOrderUnitParams.setClosingOrder(l_strClosingOrder);

                //注文エラー理由コード(error_reason_code):0000：正常
                l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

                //初回注文の注文単位ＩＤ(first_order_unit_id):引数の注文内容.get初回注文の注文単位ID()
                l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoSettleContractOrderSpec.getFirstOrderUnitId());

                //親注文の注文ＩＤ(parent_order_id):引数の親注文の注文単位.注文ID
                l_rsvIfoOrderUnitParams.setParentOrderId(l_parentOrderUnit.getOrderId());

                //親注文の注文単位ＩＤ(parent_order_unit_id):引数の親注文の注文単位.注文単位ID
                l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_parentOrderUnit.getOrderUnitId());

                //親注文内連番(serial_no_in_parent):連続注文マネージャ.get先物OP親注文内連番(引数の親注文の注文単位.注文単位ID)
                l_rsvIfoOrderUnitParams.setSerialNoInParent((int)this.getIfoSerialNoInParent(
                    l_parentOrderUnit.getOrderUnitId()));

                //夕場前繰越対象フラグ(evening_session_carryover_flag):引数の注文内容.get夕場前繰越対象フラグ()
                if (l_ifoSettleContractOrderSpec.getEveningSessionCarryoverFlag())
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.TRUE);
                }
                else
                {
                    l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(
                        BooleanEnum.FALSE);
                }

                //立会区分(session_type):取引時間管理.get立会区分()
                l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

                //注文期限区分(expiration_date_type):引数の注文内容.get注文期限区分()
                l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoSettleContractOrderSpec.getExpirationDateType());

                //作成日付(created_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //更新日付(last_updated_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
            }

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);

            //３）　@反対売買でない場合(*1)、
            //パラメータ.注文内容.getSettleContractEntries()の
            //戻り値の要素数分、先物OP予約建玉返済指定情報テーブルに
            //レコードを登録する。
            boolean l_blnIsReverseTrade =
                this.isReversingTrade(l_strRsvOrderTradingType, l_parentOrderUnit);

            if (!l_blnIsReverseTrade)
            {
                SettleContractEntry[] l_settleContractEntry =
                    l_ifoSettleContractOrderSpec.getSettleContractEntries();

                int l_intCnt = 0;
                if (l_settleContractEntry != null && l_settleContractEntry.length > 0)
                {
                    l_intCnt = l_settleContractEntry.length;
                }

                //３－１）　@パラメータ.連続注文取引区分が"先物返済（既存残）"の場合
                //登録の仕様は、DB更新仕様
                //「（連続）先物返済注文登録_先物OP予約建玉返済指定情報テーブル仕様.xls」を参照。
                if (WEB3ReserveOrderTradingTypeDef.SETTLE_FUTURE_EXISTING_REMAINDER.equals(l_strRsvOrderTradingType))
                {
                    for (int i = 0; i < l_intCnt; i++)
                    {
                        RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                            new RsvIfoClosingContractSpecParams();

                        //口座ＩＤ(account_id): 引数の補助口座.口座ID
                        l_rsvIfoClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());

                        //補助口座ＩＤ(sub_account_id): 引数の補助口座.補助口座ID 
                        l_rsvIfoClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());

                        //注文ＩＤ(order_id): 引数の注文ID 
                        l_rsvIfoClosingContractSpecParams.setOrderId(l_lngOrderId);

                        //建玉ＩＤ(contract_id): getSettleContractOrderEntries()の戻り値[index].getContractId()
                        l_rsvIfoClosingContractSpecParams.setContractId(
                            l_settleContractEntry[i].getContractId());

                        //返済連番(closing_serial_no): index + 1
                        l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                        //返済注文数量(quantity): getSettleContractOrderEntries()の戻り値[index].getQuantity()
                        l_rsvIfoClosingContractSpecParams.setQuantity(
                            l_settleContractEntry[i].getQuantity());

                        //作成日付(created_timestamp): 現在日時（GtlUtils.getSystemTimestamp()）
                        l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                        //更新日付(last_updated_timestamp): 現在日時（GtlUtils.getSystemTimestamp()）
                        l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        l_queryProcessor.doInsertQuery(l_rsvIfoClosingContractSpecParams);
                    }
                }
                //３－２）　@パラメータ.連続注文取引区分が"OP返済（既存残）"の場合
                //登録の仕様は、DB更新仕様
                //「（連続）OP返済注文登録_先物OP予約建玉返済指定情報テーブル仕様.xls」を参照。
                else if (WEB3ReserveOrderTradingTypeDef.SETTLE_OP_EXISTING_REMAINDER.equals(
                    l_strRsvOrderTradingType))
                {
                    for (int i = 0; i < l_intCnt; i++)
                    {
                        RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                            new RsvIfoClosingContractSpecParams();

                        //口座ＩＤ(account_id):引数の補助口座.口座ID
                        l_rsvIfoClosingContractSpecParams.setAccountId(l_subAccount.getAccountId());

                        //補助口座ＩＤ(sub_account_id):引数の補助口座.補助口座ID
                        l_rsvIfoClosingContractSpecParams.setSubAccountId(l_subAccount.getSubAccountId());

                        //注文ＩＤ(order_id):引数の注文ID
                        l_rsvIfoClosingContractSpecParams.setOrderId(l_lngOrderId);

                        //建玉ＩＤ(contract_id):getSettleContractOrderEntries()の戻り値[index].getContractId()
                        l_rsvIfoClosingContractSpecParams.setContractId(l_settleContractEntry[i].getContractId());

                        //返済連番(closing_serial_no):index + 1
                        l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                        //返済注文数量(quantity):getSettleContractOrderEntries()の戻り値[index].getQuantity()
                        l_rsvIfoClosingContractSpecParams.setQuantity(l_settleContractEntry[i].getQuantity());

                        //作成日付(created_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                        l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                        //更新日付(last_updated_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
                        l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                        l_queryProcessor.doInsertQuery(l_rsvIfoClosingContractSpecParams);
                    }
                }
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //４）　@予約注文履歴を作成する。
        //先物OP予約注文更新サービス.insert予約注文履歴(引数の注文ID)をコールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService) Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        //５）　@親注文の注文単位に、予約注文登録を記録する。
        //this.set予約注文設定To先物OP親注文(引数の親注文の注文単位)をコールする。
        this.setReserveOrderSettingToIfoParentOrder(l_parentOrderUnit);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit株式訂正予約注文)<BR>
     * （submitEqtypeChangeOrder）<BR>
     * <BR>
     * 株式予約注文単位テーブルに、予約注文に対する訂正を反映する。<BR>
     * 対象：　@現物株式注文、信用新規建注文<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@株式予約注文単位テーブルのレコードを更新する。<BR>
     * <BR>
     * 　@更新の仕様は、DB更新仕様<BR>
     * 　@「（連続）現物株式注文訂正_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.insert株式予約注文履歴<BR>
     * 　@(引数の訂正前予約注文単位.注文ID)をコールする。<BR>
     * <BR>
     * ４）　@returnする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_eqtypeChangeOrderSpec - (予約注文訂正内容)<BR>
     * 予約注文訂正内容オブジェクト。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_changingBeforeRsvEqOrderUnit - (訂正前予約注文単位)<BR>
     * 訂正前の予約注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B8C570102
     */
    public void submitEqtypeChangeOrder(
        SubAccount l_subAccount, 
        WEB3ToSuccEqtypeChangeOrderSpec l_eqtypeChangeOrderSpec, 
        String l_strTradingPassword, 
        WEB3ToSuccEqTypeOrderUnitImpl l_changingBeforeRsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeChangeOrder(SubAccount, WEB3ToSuccEqtypeChangeOrderSpec," +
            " String, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_eqtypeChangeOrderSpec == null)
        {
            log.debug("予約注文訂正内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約注文訂正内容 = null。");
        }
        
        if (l_changingBeforeRsvEqOrderUnit == null)
        {
            log.debug("訂正前予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "訂正前予約注文単位 = null。");
        }
        
        //１）　@取引パスワードをチェックする。
        //注文チェック.validate取引パスワード()をコールする。
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワードをチェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワードをチェックエラー");
        }
        
        //２）　@株式予約注文単位テーブルのレコードを更新する。
        //更新の仕様は、DB更新仕様 
        //「（連続）現物株式注文訂正_株式予約注文単位テーブル.xls」を参照。
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(
            (RsvEqOrderUnitRow)l_changingBeforeRsvEqOrderUnit.getDataSourceObject());
                                                                          
        //取引者ＩＤ
        //引数の予約注文訂正内容.get扱者().取引者ID
        //※get扱者()の戻り値==nullの場合は、nullをセット
        Trader l_trader = l_eqtypeChangeOrderSpec.getTrader();
        if (l_trader != null)
        {
            l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
        }
        else
        {
            l_rsvEqOrderUnitParams.setTraderId(null);
        }
                                                                             
        //注文履歴最終通番    (   last_order_action_serial_no ):  （既存値）＋１     
        l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(
            l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);

        //注文数量    (   quantity    ):  注文訂正内容詳細.getAfterChangeOriginalQuantity()
        EqTypeChangeOrderUnitEntry l_eqTypeChangeOrderUnitEntry = l_eqtypeChangeOrderSpec.getChangeOrderUnitEntry();
        l_rsvEqOrderUnitParams.setQuantity(
            l_eqTypeChangeOrderUnitEntry.getAfterChangeOriginalQuantity());
                                                                               
        //指値  (   limit_price ):  引数の予約注文訂正内容.get訂正後単価調整値()==nullの場合：
        //注文訂正内容詳細.getAfterChangePrice()
        //上記以外の場合：　@null 
        if (l_eqtypeChangeOrderSpec.getModifiedPriceAdjustValue() == null)
        {
            l_rsvEqOrderUnitParams.setLimitPrice(l_eqTypeChangeOrderUnitEntry.getAfterChangePrice());                                      
        }
        else
        {
            l_rsvEqOrderUnitParams.setLimitPrice(null);    
        }
        
        //単価調整値   (   price_adjust_value  ):  引数の予約注文訂正内容.get訂正後単価調整値()
        //※nullの場合はnullをセット
        l_rsvEqOrderUnitParams.setPriceAdjustValue(l_eqtypeChangeOrderSpec.getModifiedPriceAdjustValue());        
        
        //注文失効日付  (   expiration_date ):  引数の予約注文訂正内容.get訂正後注文失効日()                                                                  
        l_rsvEqOrderUnitParams.setExpirationDate(l_eqtypeChangeOrderSpec.getModifiedExpirationDate());
        
        //注文状態    (   order_status    ):  10:発注済（変更注文）                                                                 
        l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        
        //注文単価    (   price   ):  引数の予約注文訂正内容.get訂正後計算単価()                                                                      
        l_rsvEqOrderUnitParams.setPrice(l_eqtypeChangeOrderSpec.getModifiedCalcUnitPrice());
        
        //概算受渡代金  (   estimated_price ):引数の予約注文訂正内容.get訂正後概算受渡代金()                                                                      
        l_rsvEqOrderUnitParams.setEstimatedPrice(l_eqtypeChangeOrderSpec.getModifiedEstimatedPrice());
        
        //注文経路区分  (   order_root_div  ):  ログインセッションより取得してセット     
        OpLoginSecurityService l_opLoginSecurityService =
            (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);                                                                 
        l_rsvEqOrderUnitParams.setOrderRootDiv(
            l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

        //初回注文の注文単位ＩＤ
        Long l_firstOrderUnitId = null;
        if (l_eqtypeChangeOrderSpec.isModifiedCarriedOrder())
        {
            l_firstOrderUnitId = new Long(0);
        }
        l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_firstOrderUnitId);
        
        //更新日付    (   last_updated_timestamp  ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
        l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //３）　@予約注文履歴を作成する。
        //株式予約注文更新サービス.insert株式予約注文履歴(引数の訂正前予約注文単位.注文ID)をコールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvEqOrderUnit.getOrderId());     
        
        //４）　@returnする。
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (submit株式訂正予約返済注文)<BR>
     * （submitEqtypeChangeSettleContractOrder）<BR>
     * <BR>
     * 株式予約注文単位テーブルに、予約注文に対する訂正を反映する。<BR>
     * 対象：　@信用返済注文<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@株式予約注文単位テーブルのレコードを更新する。<BR>
     * <BR>
     * 　@更新の仕様は、DB更新仕様<BR>
     * 　@「（連続）信用返済注文訂正_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@既存残に対する返済の場合、<BR>
     * 　@（パラメータ.訂正前予約注文単位.is反対売買取引() == false）<BR>
     * 　@株式予約建株返済指定情報テーブルのレコードを更新する。<BR>
     * 　@※パラメータ.予約返済注文訂正内容.get株式予約返済注文訂正内容詳細().<BR>
     * 　@　@getAfterChangeSettleContractOrderEntries()の要素数分、更新する。<BR>
     * 　@<BR>
     * 　@更新の仕様は、DB更新仕様<BR>
     * 　@「（連続）信用返済注文訂正_株式予約建株返済指定情報テーブル仕様.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ４）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.insert株式予約注文履歴<BR>
     * 　@(引数の訂正前予約注文単位.注文ID)をコールする。<BR>
     * <BR>
     * ５）　@returnする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_marginChangeSettleContractOrderSpec - (予約返済注文訂正内容)<BR>
     * 予約返済注文訂正内容オブジェクト。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_changingBeforeRsvEqOrderUnit - (訂正前予約注文単位)<BR>
     * 訂正前の予約注文単位。
     * @@throws WEB3BaseException
     * @@roseuid 4342586D0249
     */
    public void submitEqtypeChangeSettleContractOrder(
        SubAccount l_subAccount, 
        WEB3ToSuccMarginChangeSettleContractOrderSpec l_marginChangeSettleContractOrderSpec, 
        String l_strTradingPassword, 
        WEB3ToSuccEqTypeOrderUnitImpl l_changingBeforeRsvEqOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeChangeSettleContractOrder(SubAccount, WEB3ToSuccMarginChangeSettleContractOrderSpec," +
            " String, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_marginChangeSettleContractOrderSpec == null)
        {
            log.debug("予約返済注文訂正内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約返済注文訂正内容 = null。");
        }
        
        if (l_changingBeforeRsvEqOrderUnit == null)
        {
            log.debug("訂正前予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "訂正前予約注文単位 = null。");
        }        
        
        try
        {
            //１）　@取引パスワードをチェックする。
            //注文チェック.validate取引パスワード()をコールする。 
            WEB3GentradeOrderValidator l_orderValidator = 
                (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
        
            OrderValidationResult l_orderValidationResult = 
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
        
            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードをチェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードをチェックエラー");
            }
            
            //２）　@株式予約注文単位テーブルにレコードを登録する。 
            //登録の仕様は、DB更新仕様
            //「（連続）信用返済注文訂正_株式予約注文単位テーブル.xls」を参照。
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = new RsvEqOrderUnitParams(
                (RsvEqOrderUnitRow)l_changingBeforeRsvEqOrderUnit.getDataSourceObject());
            
            //取引者ＩＤ
            //引数の予約返済注文訂正内容.get扱者().取引者ID
            //※get扱者()の戻り値==nullの場合は、nullをセット
            Trader l_trader = l_marginChangeSettleContractOrderSpec.getTrader();
            if (l_trader != null)
            {
                l_rsvEqOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            else
            {
                l_rsvEqOrderUnitParams.setTraderId(null);
            }
                                                                             
            //注文履歴最終通番    (   last_order_action_serial_no ):  （既存値）＋１     
            l_rsvEqOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvEqOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //注文数量    (   quantity    ):  注文訂正詳細.AfterChangeTotalQuantity()
            EqTypeContractSettleChangeOrderUnitEntry l_eqTypeContractSettleChangeOrderUnitEntry = 
                l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry();
            l_rsvEqOrderUnitParams.setQuantity(
                l_eqTypeContractSettleChangeOrderUnitEntry.getAfterChangeTotalQuantity());
                                                                               
            //指値  (   limit_price ):  引数の予約返済注文訂正内容.get訂正後単価調整値()==nullの場合：
            //注文訂正詳細.getAfterChangePrice()
            //上記以外の場合：　@null
            if (l_marginChangeSettleContractOrderSpec.getModifiedPriceAdjustValue() == null)
            {
                l_rsvEqOrderUnitParams.setLimitPrice(l_eqTypeContractSettleChangeOrderUnitEntry.getAfterChangePrice());                                      
            }
            else
            {
                l_rsvEqOrderUnitParams.setLimitPrice(null);    
            }
        
            //単価調整値   (   price_adjust_value  ):  引数の予約返済注文訂正内容.get訂正後単価調整値()
            //※nullの場合はnullをセット
            l_rsvEqOrderUnitParams.setPriceAdjustValue(
                l_marginChangeSettleContractOrderSpec.getModifiedPriceAdjustValue());        
        
            //注文失効日付  (   expiration_date ):  引数の予約返済注文訂正内容.get訂正後注文失効日()                                                                  
            l_rsvEqOrderUnitParams.setExpirationDate(
                l_marginChangeSettleContractOrderSpec.getModifiedExpirationDate());
        
            //注文状態    (   order_status    ):  10:発注済（変更注文）                                                                 
            l_rsvEqOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);
        
            //注文単価    (   price   ):  引数の予約返済注文訂正内容.get訂正後計算単価()                                                                      
            l_rsvEqOrderUnitParams.setPrice(
                l_marginChangeSettleContractOrderSpec.getModifiedCalcUnitPrice());
        
            //概算受渡代金  (   estimated_price ):引数の予約返済注文訂正内容.get訂正後概算受渡代金()                                                                      
            l_rsvEqOrderUnitParams.setEstimatedPrice(
                l_marginChangeSettleContractOrderSpec.getModifiedEstimatedPrice());
        
            //注文経路区分  (   order_root_div  ):  ログインセッションより取得してセット     
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);                                                                 
            l_rsvEqOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));        

            //初回注文の注文単位ＩＤ
            Long l_firstOrderUnitId = null;
            if (l_marginChangeSettleContractOrderSpec.isModifiedCarriedOrder())
            {
                l_firstOrderUnitId = new Long(0);
            }
            l_rsvEqOrderUnitParams.setFirstOrderUnitId(l_firstOrderUnitId);

            //更新日付    (   last_updated_timestamp  ):  現在日時（GtlUtils.getSystemTimestamp()）                                                                     
            l_rsvEqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
                
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderUnitParams); //DataNetworkException,DataQueryException                        
        
            //３）　@既存残に対する返済の場合、 
            //※パラメータ.予約返済注文訂正内容.get株式予約返済注文訂正内容詳細().  
            //getAfterChangeSettleContractOrderEntries()の要素数分、更新する。 
            //更新の仕様は、DB更新仕様
            //「（連続）信用返済注文訂正_株式予約建株返済指定情報テーブル仕様.xls」 
            //を参照。
            boolean l_blnIsReverseTrade = 
                l_changingBeforeRsvEqOrderUnit.isReversingTrade();
        
            if (!l_blnIsReverseTrade)
            {
                EqTypeSettleContractOrderEntry[] l_eqTypeSettleContractOrderEntries = 
                    l_marginChangeSettleContractOrderSpec.getChangeOrderUnitEntry().getAfterChangeSettleContractOrderEntries();
                
                int l_intCnt = 0;
                if (l_eqTypeSettleContractOrderEntries != null && 
                l_eqTypeSettleContractOrderEntries.length > 0)
                {
                    l_intCnt = l_eqTypeSettleContractOrderEntries.length;
                }
                
                for (int i = 0; i < l_intCnt; i++)
                {                    
                    String l_strWhere = " order_id = ? and contract_id = ? ";                
                    Object[] l_objs = {
                        new Long(l_changingBeforeRsvEqOrderUnit.getOrderId()), 
                        new Long(l_eqTypeSettleContractOrderEntries[i].getContractId())};
                        
                    Map l_map = new HashMap();    
                    //返済注文数量(quantity):処理対象の要素.getQuantity()
                    l_map.put("quantity", new Double(l_eqTypeSettleContractOrderEntries[i].getQuantity()));
                    
                    //更新日付(last_updated_timestamp): 現在日時（GtlUtils.getSystemTimestamp()）
                    l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());
                    
                    l_queryProcessor.doUpdateAllQuery(
                        RsvEqClosingContractSpecRow.TYPE,
                        l_strWhere,
                        l_objs,
                        l_map); //DataNetworkException,DataQueryException      
                }
            }        
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
                
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //４）　@予約注文履歴を作成する。
        //株式予約注文更新サービス.insert株式予約注文履歴(訂正前予約注文単位.注文ID)をコールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqOrderUnitUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService) Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvEqOrderUnit.getOrderId());        
        
        //５）　@returnする。
        log.exiting(STR_METHOD_NAME);
        return;
    }
    
    /**
     * (submit先物OP訂正予約新規建注文)<BR>
     * （submitIfoChangeOpenContractOrder）<BR>
     * <BR>
     * 先物OP予約注文単位テーブルに、予約注文に対する訂正を反映する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@先物OP予約注文単位テーブルのレコードを更新する。<BR>
     * <BR>
     * 　@　@更新の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）新規建訂正_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.insert予約注文履歴(引数の訂正前予約注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ４）　@returnする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_ifoChangeOrderSpec - (予約注文訂正内容)<BR>
     * 予約注文訂正内容オブジェクト。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_changingBeforeRsvIfoOrderUnit - (訂正前予約注文単位)<BR>
     * 訂正前の予約注文単位。
     * @@throws WEB3BaseException
     */
    public void submitIfoChangeOpenContractOrder(
        SubAccount l_subAccount,
        WEB3ToSuccIfoChangeOpenContractOrderSpec l_ifoChangeOrderSpec,
        String l_strTradingPassword,
        WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoChangeOpenContractOrder(SubAccount, WEB3ToSuccIfoChangeOpenContractOrderSpec," +
            " String, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "補助口座 = null。");
        }

        if (l_ifoChangeOrderSpec == null)
        {
            log.debug("予約注文訂正内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予約注文訂正内容 = null。");
        }

        if (l_changingBeforeRsvIfoOrderUnit == null)
        {
            log.debug("訂正前予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "訂正前予約注文単位 = null。");
        }

        try
        {
            //１）　@取引パスワードをチェックする。
            //注文チェック.validate取引パスワード()をコールする。
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードをチェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードをチェックエラー");
            }

            //２）　@先物OP予約注文単位テーブルのレコードを更新する。
            //更新の仕様は、DB更新仕様
            //「（連続）新規建訂正_先物OP予約注文単位テーブル.xls」を参照。
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(
                (RsvIfoOrderUnitRow)l_changingBeforeRsvIfoOrderUnit.getDataSourceObject());

            //取引者ＩＤ(trader_id):引数の予約注文訂正内容.get扱者().取引者ID
            //※get扱者()の戻り値==nullの場合は、nullをセット
            WEB3GentradeTrader l_trader = l_ifoChangeOrderSpec.getTrader();
            if (l_trader != null)
            {
                l_rsvIfoOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setTraderId(null);
            }

            //注文履歴最終通番(last_order_action_serial_no):（既存値）＋１
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvIfoOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //注文数量(quantity):引数の予約注文訂正内容.getAfterChangeOriginalQuantity
            l_rsvIfoOrderUnitParams.setQuantity(l_ifoChangeOrderSpec.getAfterChangeOriginalQuantity());

            //指値(limit_price):引数の予約注文訂正内容.get訂正後単価調整値()==nullの場合：
            //予約注文訂正内容.getAfterChangePrice()
            //上記以外の場合：　@null
            if (l_ifoChangeOrderSpec.getModifiedPriceAdjustValue() == null)
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(l_ifoChangeOrderSpec.getAfterChangePrice());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(null);
            }

            //単価調整値(price_adjust_value):引数の予約注文訂正内容.get訂正後単価調整値()
            //※nullの場合はnullをセット
            Double l_modifiedPriceAdjustValue = l_ifoChangeOrderSpec.getModifiedPriceAdjustValue();
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_modifiedPriceAdjustValue);

            //注文失効日付(expiration_date):引数の予約注文訂正内容.get訂正後注文失効日()
            l_rsvIfoOrderUnitParams.setExpirationDate(l_ifoChangeOrderSpec.getModifiedExpirationDate());

            //注文状態(order_status):10:発注済（変更注文）
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);

            //注文単価(price):引数の予約注文訂正内容.get訂正後計算単価()
            l_rsvIfoOrderUnitParams.setPrice(l_ifoChangeOrderSpec.getModifiedCalcUnitPrice());

            //概算受渡代金(estimated_price):引数の予約注文訂正内容.get訂正後概算受渡代金()
            l_rsvIfoOrderUnitParams.setEstimatedPrice(l_ifoChangeOrderSpec.getModifiedEstimatedPrice());

            //注文経路区分(order_root_div):ログインセッションより取得してセット
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            l_rsvIfoOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //初回注文の注文単位ＩＤ(first_order_unit_id):引数の予約注文訂正内容.get初回注文の注文単位ID()
            l_rsvIfoOrderUnitParams.setFirstOrderUnitId(l_ifoChangeOrderSpec.getFirstOrderUnitId());

            //夕場前繰越対象フラグ(evening_session_carryover_flag):引数の予約注文訂正内容.get夕場前繰越対象フラグ()
            if (l_ifoChangeOrderSpec.getEveningSessionCarryOverFlag())
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            }
            else
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            }

            //注文期限区分(expiration_date_type):引数の予約注文訂正内容.get注文期限区分()
            l_rsvIfoOrderUnitParams.setExpirationDateType(l_ifoChangeOrderSpec.getExpirationDateType());

            //更新日付(last_updated_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）　@予約注文履歴を作成する。
        //先物OP予約注文更新サービス.insert予約注文履歴(引数の訂正前予約注文単位.注文ID)を
        //コールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvIfoOrderUnit.getOrderId());

        //４）　@returnする。
        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (submit先物OP訂正予約返済注文)<BR>
     * （submitIfoChangeSettleContractOrder）<BR>
     * <BR>
     * 先物OP予約注文単位テーブルに、予約注文に対する訂正を反映する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@先物OP予約注文単位テーブルのレコードを更新する。<BR>
     * <BR>
     * 　@　@更新の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）返済注文訂正_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * ３）　@既存残に対する返済の場合、<BR>
     * 　@（パラメータ.訂正前予約注文単位.is反対売買取引() == false）<BR>
     * 　@先物OP予約建玉返済指定情報テーブルのレコードを更新する。<BR>
     * 　@※パラメータ.予約返済注文訂正内容.getAfterChangeSettleContractEntries()<BR>
     * の要素数分、更新する。<BR>
     * <BR>
     * 　@　@更新の仕様は、DB更新仕様<BR>
     * 　@　@「（連続）返済注文訂正_先物OP予約建玉返済指定情報テーブル仕様.xls」を参照。<BR>
     * <BR>
     * ４）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.insert予約注文履歴(引数の訂正前予約注文単位.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ５）　@returnする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_toSuccIfoChangeSettleContractOrderSpec - (予約返済注文訂正内容)<BR>
     * 予約注文訂正内容オブジェクト。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_changingBeforeRsvIfoOrderUnit - (訂正前予約注文単位)<BR>
     * 訂正前の予約注文単位。
     * @@throws WEB3BaseException
     */
    public void submitIfoChangeSettleContractOrder(
        SubAccount l_subAccount,
        WEB3ToSuccIfoChangeSettleContractOrderSpec l_toSuccIfoChangeSettleContractOrderSpec,
        String l_strTradingPassword,
        WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoChangeSettleContractOrder(SubAccount, WEB3ToSuccIfoChangeOpenContractOrderSpec," +
            " String, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "補助口座 = null。");
        }

        if (l_toSuccIfoChangeSettleContractOrderSpec == null)
        {
            log.debug("予約注文訂正内容 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予約注文訂正内容 = null。");
        }

        if (l_changingBeforeRsvIfoOrderUnit == null)
        {
            log.debug("訂正前予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "訂正前予約注文単位 = null。");
        }

        try
        {
            //１）　@取引パスワードをチェックする。
            //注文チェック.validate取引パスワード()をコールする。
            WEB3GentradeOrderValidator l_orderValidator =
                (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

            OrderValidationResult l_orderValidationResult =
                l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

            if (l_orderValidationResult.getProcessingResult().isFailedResult())
            {
                log.debug("取引パスワードをチェックエラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_orderValidationResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引パスワードをチェックエラー");
            }

            //２）　@先物OP予約注文単位テーブルのレコードを更新する。
            //更新の仕様は、DB更新仕様
            //「（連続）返済注文訂正_先物OP予約注文単位テーブル.xls」を参照。
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(
                (RsvIfoOrderUnitRow)l_changingBeforeRsvIfoOrderUnit.getDataSourceObject());

            //取引者ＩＤ(trader_id):引数の予約注文訂正内容.get扱者().取引者ID
            //※get扱者()の戻り値==nullの場合は、nullをセット
            WEB3GentradeTrader l_trader = l_toSuccIfoChangeSettleContractOrderSpec.getTrader();
            if (l_trader != null)
            {
                l_rsvIfoOrderUnitParams.setTraderId(l_trader.getTraderId());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setTraderId(null);
            }

            //注文履歴最終通番(last_order_action_serial_no):（既存値）＋１
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(
                l_rsvIfoOrderUnitParams.getLastOrderActionSerialNo() + 1);

            //注文数量(quantity):予約返済注文訂正内容.getAfterChangeTotalQuantity()
            l_rsvIfoOrderUnitParams.setQuantity(
                l_toSuccIfoChangeSettleContractOrderSpec.getAfterChangeTotalQuantity());

            //指値(limit_price):引数の予約注文訂正内容.get訂正後単価調整値()==nullの場合：
            //予約注文訂正内容.getAfterChangePrice()
            //上記以外の場合：　@null
            if (l_toSuccIfoChangeSettleContractOrderSpec.getModifiedPriceAdjustValue() == null)
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(l_toSuccIfoChangeSettleContractOrderSpec.getAfterChangePrice());
            }
            else
            {
                l_rsvIfoOrderUnitParams.setLimitPrice(null);
            }

            //単価調整値(price_adjust_value):引数の予約注文訂正内容.get訂正後単価調整値()
            //※nullの場合はnullをセット
            Double l_modifiedPriceAdjustValue =
                l_toSuccIfoChangeSettleContractOrderSpec.getModifiedPriceAdjustValue();
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(l_modifiedPriceAdjustValue);

            //注文失効日付(expiration_date):引数の予約注文訂正内容.get訂正後注文失効日()
            l_rsvIfoOrderUnitParams.setExpirationDate(
                l_toSuccIfoChangeSettleContractOrderSpec.getModifiedExpirationDate());

            //注文状態(order_status):10:発注済（変更注文）
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFIED);

            //注文単価(price):引数の予約注文訂正内容.get訂正後計算単価()
            l_rsvIfoOrderUnitParams.setPrice(l_toSuccIfoChangeSettleContractOrderSpec.getModifiedCalcUnitPrice());

            //概算受渡代金(estimated_price):引数の予約注文訂正内容.get訂正後概算受渡代金()
            l_rsvIfoOrderUnitParams.setEstimatedPrice(
                l_toSuccIfoChangeSettleContractOrderSpec.getModifiedEstimatedPrice());

            //注文経路区分(order_root_div):ログインセッションより取得してセット
            OpLoginSecurityService l_opLoginSecurityService =
                (OpLoginSecurityService)Services.getService(OpLoginSecurityService.class);
            l_rsvIfoOrderUnitParams.setOrderRootDiv(
                l_opLoginSecurityService.getSessionProperty(WEB3SessionAttributeDef.ORDER_ROOT_DIV));

            //初回注文の注文単位ＩＤ(first_order_unit_id):引数の予約注文訂正内容.get初回注文の注文単位ID()
            l_rsvIfoOrderUnitParams.setFirstOrderUnitId(
                l_toSuccIfoChangeSettleContractOrderSpec.getFirstOrderUnitId());

            //夕場前繰越対象フラグ(evening_session_carryover_flag):引数の予約注文訂正内容.get夕場前繰越対象フラグ()
            if (l_toSuccIfoChangeSettleContractOrderSpec.getEveningSessionCarryOverFlag())
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
            }
            else
            {
                l_rsvIfoOrderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
            }

            //注文期限区分(expiration_date_type):引数の予約注文訂正内容.get注文期限区分()
            l_rsvIfoOrderUnitParams.setExpirationDateType(
                l_toSuccIfoChangeSettleContractOrderSpec.getExpirationDateType());

            //更新日付(last_updated_timestamp):現在日時（GtlUtils.getSystemTimestamp()）
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderUnitParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）　@既存残に対する返済の場合、
        //（パラメータ.訂正前予約注文単位.is反対売買取引() == false）
        //先物OP予約建玉返済指定情報テーブルのレコードを更新する。
        if (!l_changingBeforeRsvIfoOrderUnit.isReversingTrade())
        {
            try
            {
                //※パラメータ.予約返済注文訂正内容.getAfterChangeSettleContractEntries()の要素数分、更新する。
                //更新の仕様は、DB更新仕様
                //「（連続）返済注文訂正_先物OP予約建玉返済指定情報テーブル仕様.xls」を参照。
                SettleContractEntry[] l_settleContractEntry =
                    l_toSuccIfoChangeSettleContractOrderSpec.getAfterChangeSettleContractEntries();

                int l_intCnt = 0;
                if (l_settleContractEntry != null && l_settleContractEntry.length > 0)
                {
                    l_intCnt = l_settleContractEntry.length;
                }

                for (int i = 0; i < l_intCnt; i++)
                {
                    String l_strWhere = " order_id = ? and contract_id = ? ";
                    Object[] l_objs = {
                        new Long(l_changingBeforeRsvIfoOrderUnit.getOrderId()),
                        new Long(l_settleContractEntry[i].getContractId())};

                    Map l_map = new HashMap();
                    //返済注文数量(quantity):処理対象の要素.getQuantity()
                    l_map.put("quantity", new Double(l_settleContractEntry[i].getQuantity()));

                    //更新日付(last_updated_timestamp): 現在日時（GtlUtils.getSystemTimestamp()）
                    l_map.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                    l_queryProcessor.doUpdateAllQuery(
                        RsvIfoClosingContractSpecRow.TYPE,
                        l_strWhere,
                        l_objs,
                        l_map);
                }
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        //４）　@予約注文履歴を作成する。
        //先物OP予約注文更新サービス.insert予約注文履歴(引数の訂正前予約注文単位.注文ID)を
        //コールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                    WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(
            l_changingBeforeRsvIfoOrderUnit.getOrderId());

        //５）　@returnする。
        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (submit株式予約注文取消)<BR>
     * （submitEqtypeCancelOrder）<BR>
     * <BR>
     * 引数の株式予約注文単位を取消する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@予約注文を取消する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.cancel予約注文単位(引数の予約注文単位)を<BR>
     * 　@コールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_rsvEqOrderUnit - (予約注文単位)<BR>
     * 株式予約注文単位Implオブジェクト。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433B78F100C2
     */
    public void submitEqtypeCancelOrder(
        SubAccount l_subAccount, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit, 
        String l_strTradingPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitEqtypeCancelOrder(SubAccount, WEB3ToSuccEqTypeOrderUnitImpl, String)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約注文単位 = null。");
        }        
        
        //１）　@取引パスワードをチェックする。
        //注文チェック.validate取引パスワード()をコールする。 
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
    
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);
    
        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワードをチェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワードをチェックエラー");
        }
        
        //２）　@予約注文を取消する。 
        //株式予約注文更新サービス.cancel予約注文単位(引数の予約注文単位)を 
        //コールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqTypeOrderUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService)Services.getService(
                WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqTypeOrderUpdateService.cancelOrderUnit(
            (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject());
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit先物OP予約注文取消)<BR>
     * （submitIfoCancelOrder）<BR>
     * 引数の先物OP予約注文単位を取消する。<BR>
     * <BR>
     * １）　@取引パスワードをチェックする。<BR>
     * <BR>
     * 　@注文チェック.validate取引パスワード()をコールする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜validate取引パスワード()：引数設定仕様＞<BR>
     * <BR>
     * 　@代理入力者：　@null（validate取引パスワード()内で、ログイン情報から取得される）<BR>
     * 　@補助口座：　@引数の補助口座<BR>
     * 　@パスワード：　@引数の取引パスワード<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ２）　@予約注文を取消する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.cancel予約注文単位(引数の予約注文単位)を<BR>
     * 　@コールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_toSuccIfoOrderUnitImpl - (予約注文単位)<BR>
     * 先物OP予約注文単位Implオブジェクト。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。
     * @@throws WEB3BaseException
     */
    public void submitIfoCancelOrder(
        SubAccount l_subAccount,
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl,
        String l_strTradingPassword) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoCancelOrder(SubAccount, WEB3ToSuccIfoOrderUnitImpl, String)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
            WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "補助口座 = null。");
        }

        if (l_toSuccIfoOrderUnitImpl == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予約注文単位 = null。");
        }

        //１）　@取引パスワードをチェックする。
        //注文チェック.validate取引パスワード()をコールする。
        WEB3GentradeOrderValidator l_orderValidator =
            (WEB3GentradeOrderValidator)GtlUtils.getCommonOrderValidator();

        OrderValidationResult l_orderValidationResult =
            l_orderValidator.validateTradingPassword(null, l_subAccount, l_strTradingPassword);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("取引パスワードをチェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取引パスワードをチェックエラー");
        }

        //２）　@予約注文を取消する。
        //先物OP予約注文更新サービス.cancel予約注文単位(引数の予約注文単位)を
        //コールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.cancelOrderUnit(
            (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnitImpl.getDataSourceObject());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate現物注文取消)<BR>
     * 現物株式注文取消発注審査を行う。<BR>
     * <BR>
     * １）　@受付時間チェック・システム取引停止チェックを行う。<BR>
     * <BR>
     * 　@　@取引時間管理.validate注文受付可能()をコールする。<BR>
     * <BR>
     * ２）　@顧客別取引停止チェックを行う。<BR>
     * <BR>
     * 　@　@注文チェック.validate取引可能顧客(引数の補助口座)をコールする。<BR>
     * <BR>
     * ３）　@指定予約注文が取消可能かどうかをチェックする。<BR>
     * <BR>
     * 　@　@パラメータ.予約注文単位.validate取消可能状態()をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_rsvEqOrderUnit - (予約注文単位)<BR>
     * 株式予約注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4340BD450302
     */
    public void validateEqtypeCancelOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateEqtypeCancelOrder(WEB3GentradeSubAccount, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約注文単位 = null。");
        } 
        
        //１）　@受付時間チェック・システム取引停止チェックを行う。
        //取引時間管理.validate注文受付可能()をコールする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //２）　@顧客別取引停止チェックを行う。
        //注文チェック.validate取引可能顧客(引数の補助口座)をコールする。
        WEB3GentradeOrderValidator l_orderValidator = 
            (WEB3GentradeOrderValidator) GtlUtils.getCommonOrderValidator();
    
        OrderValidationResult l_orderValidationResult = 
            l_orderValidator.validateSubAccountForTrading(l_subAccount);

        if (l_orderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("顧客別取引停止をチェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_orderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客別取引停止をチェックエラー");
        }
        
        //３）　@指定予約注文が取消可能かどうかをチェックする。
        //パラメータ.予約注文単位.validate取消可能状態()をコールする。
        l_rsvEqOrderUnit.validateOrderForCancellation();
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate信用注文取消)<BR>
     * 信用取引注文取消発注審査を行う。<BR>
     * <BR>
     * １）　@拡張株式注文マネージャ.validate信用注文()を<BR>
     * 　@　@　@コールする。<BR>
     * <BR>
     * 　@　@　@[validate信用注文()に指定する引数]<BR>
     * 　@　@　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@　@　@弁済区分：　@パラメータ.予約注文単位.弁済区分<BR>
     * <BR>
     * ２）　@パラメータ.予約注文単位.validate取消可能状態()<BR>
     * 　@　@　@をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_rsvEqOrderUnit - (予約注文単位)<BR>
     * 株式予約注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433A34D40271
     */
    public void validateMarginCancelOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " validateMarginCancelOrder(WEB3GentradeSubAccount, WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);    
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約注文単位 = null。");
        } 
        
        //１）　@拡張株式注文マネージャ.validate信用注文()をコールする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
        l_orderManager.validateMarginOrder(l_subAccount, l_rsvEqOrderUnitRow.getRepaymentType());
        
        //２）　@パラメータ.予約注文単位.validate取消可能状態()をコールする。
        l_rsvEqOrderUnit.validateOrderForCancellation();
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    
    /**
     * (validate先物OP注文取消)<BR>
     * 先物OP注文取消発注審査を行う。<BR>
     * <BR>
     * １）　@OP注文マネージャ.validate注文()を<BR>
     * 　@　@　@コールする。<BR>
     * <BR>
     * 　@　@　@[validate注文()に指定する引数]<BR>
     * 　@　@　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@　@　@先物／オプション区分：　@パラメータ.予約注文単位.先物／オプション区分<BR>
     * <BR>
     * ２）　@パラメータ.予約注文単位.validate取消可能状態()<BR>
     * 　@　@　@をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_toSuccIfoOrderUnitImpl - (予約注文単位)<BR>
     * 先物OP予約注文単位オブジェクト
     * @@throws WEB3BaseException
     */
    public void validateIfoCancelOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateIfoCancelOrder(WEB3GentradeSubAccount, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "補助口座 = null。");
        }

        if (l_toSuccIfoOrderUnitImpl == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予約注文単位 = null。");
        }

        //１）　@OP注文マネージャ.validate注文()を
        //コールする。
        //[validate注文()に指定する引数]
        //補助口座：　@パラメータ.補助口座
        //先物／オプション区分：　@パラメータ.予約注文単位.先物／オプション区分
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnitImpl.getDataSourceObject();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        l_orderMgr.validateOrder(l_subAccount, l_rsvIfoOrderUnitRow.getFutureOptionDiv());

        //２）　@パラメータ.予約注文単位.validate取消可能状態()
        //をコールする。
        l_toSuccIfoOrderUnitImpl.validateOrderForCancellation();
    }

    /**
     * (get株式親注文内連番)<BR>
     * 指定の親注文内における子注文の連番を取得する。<BR>
     * （予約注文登録時の、親注文内連番設定に使用）<BR>
     * <BR>
     * １）　@DB検索<BR>
     * 　@　@以下の条件を指定して、【株式予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@------------------------------- <BR>
     * 　@　@＜検索条件＞<BR>
     * <BR>
     * 　@　@　@　@親注文の注文単位ID = 引数.親注文の注文単位ID <BR>
     * <BR>
     * 　@　@※「親注文内連番」で降順ソート指定する。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * ２）　@先頭レコードの（親注文内連番＋１）した結果を、親注文内連番として<BR>
     * 返却する。<BR>
     * 　@　@　@※該当レコードなしの場合は、１を返却する。
     * @@param l_lngParentOrderUnitId - (親注文の注文単位ID)<BR>
     * 親注文の注文単位ID。
     * @@return long
     * @@throws WEB3BaseException
     * @@roseuid 432FAA0D0216
     */
    protected long getEqtypeSerialNoInParent(long l_lngParentOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEqtypeSerialNoInParent(long)";
        log.entering(STR_METHOD_NAME); 
        
        try 
        {
            //１）　@DB検索
            //以下の条件を指定して、【株式予約注文単位テーブル】を検索する。
            //＜検索条件＞
            //親注文の注文単位ID = 引数.親注文の注文単位ID
            //※「親注文内連番」で降順ソート指定する。
            String l_strWhere = " parent_order_unit_id = ? ";                
            Object[] l_objs = {new Long(l_lngParentOrderUnitId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
              
            List l_lisRsvEqOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvEqOrderUnitRow.TYPE, 
                    l_strWhere, 
                    " serial_no_in_parent desc ",
                    null,
                    l_objs);//DataNetworkException,DataQueryException
                    
            //２）　@先頭レコードの（親注文内連番＋１）した結果を、親注文内連番として
            //返却する
            if (l_lisRsvEqOrderUnitRows != null && !l_lisRsvEqOrderUnitRows.isEmpty())
            {
                RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_lisRsvEqOrderUnitRows.get(0);
                
                log.exiting(STR_METHOD_NAME);
                return l_rsvEqOrderUnitRow.getSerialNoInParent() + 1;
            }
            //※該当レコードなしの場合は、１を返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get先物OP親注文内連番)<BR>
     * 指定の親注文内における子注文の連番を取得する。<BR>
     * （予約注文登録時の、親注文内連番設定に使用）<BR>
     * <BR>
     * １）　@DB検索 <BR>
     * 　@　@以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。<BR>
     * <BR>
     * 　@　@-------------------------------<BR>
     * 　@　@＜検索条件＞ <BR>
     * <BR>
     * 　@　@　@　@親注文の注文単位ID = 引数.親注文の注文単位ID<BR>
     * <BR>
     * 　@　@※「親注文内連番」で降順ソート指定する。<BR>
     * 　@　@-------------------------------<BR>
     * <BR>
     * ２）　@先頭レコードの（親注文内連番＋１）した結果を、親注文内連番として返却する。<BR>
     * 　@　@　@※該当レコードなしの場合は、１を返却する。<BR>
     * @@param l_lngParentOrderUnitId - (親注文の注文単位ID)<BR>
     * 親注文の注文単位ID。
     * @@return long
     * @@throws WEB3BaseException
     */
    protected long getIfoSerialNoInParent(long l_lngParentOrderUnitId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getIfoSerialNoInParent(long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１）　@DB検索
            //以下の条件を指定して、【先物OP予約注文単位テーブル】を検索する。
            //＜検索条件＞
            //親注文の注文単位ID = 引数.親注文の注文単位ID
            //※「親注文内連番」で降順ソート指定する。
            String l_strWhere = " parent_order_unit_id = ? ";
            Object[] l_objs = {new Long(l_lngParentOrderUnitId)};

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    l_strWhere,
                    " serial_no_in_parent desc ",
                    null,
                    l_objs);

            //２）　@先頭レコードの（親注文内連番＋１）した結果を、親注文内連番として
            //返却する
            if (l_lisRsvIfoOrderUnitRows != null && !l_lisRsvIfoOrderUnitRows.isEmpty())
            {
                RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0);

                log.exiting(STR_METHOD_NAME);
                return l_rsvIfoOrderUnitRow.getSerialNoInParent() + 1;
            }
            //※該当レコードなしの場合は、１を返却する。
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (set発注済To予約注文単位)<BR>
     * （setOrderedToOrderUnit）<BR>
     * <BR>
     * 指定された予約注文単位オブジェクトを、発注済の状態に更新する。<BR>
     * <BR>
     * １）　@更新対象の予約注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 　@this.getOrderUnit(引数の銘柄タイプ, 注文ID)をコールする。<BR>
     * <BR>
     * ２）　@発注済への更新処理を行う。<BR>
     * <BR>
     * ２－１）　@引数の銘柄タイプ=="株式"の場合、<BR>
     * 　@　@　@this.set発注済To株式予約注文単位( １）で取得した株式予約注文単位)<BR>
     *       をコールする。<BR>
     * <BR>
     * ２－２）　@上記以外の場合、<BR>
     * 　@　@　@this.set発注済To先物OP予約注文単位( １）で取得した先物OP予約注文単位)<BR>
     *       をコールする。
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ。
     * @@param l_lngOrderId - (注文ID)<BR>
     * 終了対象の、予約注文の注文ID。
     * @@throws WEB3BaseException
     * @@roseuid 4328DDD3038E
     */
    public void setOrderedToOrderUnit(ProductTypeEnum l_productType, long l_lngOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setOrderedToOrderUnit(ProductTypeEnum, long)";
        log.entering(STR_METHOD_NAME); 
                
        try
        {
            //１）　@更新対象の予約注文単位オブジェクトを取得する。
            OrderUnit l_orderUnit = this.getOrderUnit(l_productType, l_lngOrderId);
            //NotFoundException
        
            //２）　@発注済への更新処理を行う。
            //２－１）　@引数の銘柄タイプ=="株式"の場合、
            //this.set発注済To株式予約注文単位( １）で取得した株式予約注文単位)
            //をコールする。
            if (ProductTypeEnum.EQUITY.equals(l_productType))
            {
                log.exiting(STR_METHOD_NAME);
                this.setOrderedToEqtypeOrderUnit((WEB3ToSuccEqTypeOrderUnitImpl)l_orderUnit);
            }
            //２－２）　@上記以外の場合、
            //this.set発注済To先物OP予約注文単位( １）で取得した先物OP予約注文単位)
            //をコールする。
            else
            {
                log.exiting(STR_METHOD_NAME);
                this.setOrderedToIfoOrderUnit((WEB3ToSuccIfoOrderUnitImpl)l_orderUnit);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("予約注文単位テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set発注済To株式予約注文単位)<BR>
     * （setOrderedToEqtypeOrderUnit）<BR>
     * <BR>
     * 指定された株式予約注文単位オブジェクトを、発注済の状態に更新する。<BR>
     * <BR>
     * １）　@発注処理により登録された注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 　@EQTYPEの拡張株式注文マネージャ.getOrderUnits(引数の予約注文単位.注文ID)を<BR>
     * コールする。<BR>
     * 　@以降、取得した注文単位オブジェクトの最初の要素を使用する。<BR>
     * <BR>
     * ２）　@発注済への更新処理を行う。<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「連続注文発注（OK）_株式予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@株式予約注文更新サービス.insert株式予約注文履歴<BR>
     * (引数の株式予約注文単位.注文ID)をコールする。
     * @@param l_rsvEqOrderUnit - (株式予約注文単位)<BR>
     * 予約注文単位オブジェクト。
     * @@throws WEB3BaseException
     * @@roseuid 4328E52201D0
     */
    protected void setOrderedToEqtypeOrderUnit(
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setOrderedToEqtypeOrderUnit(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("株式予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "株式予約注文単位 = null。");
        }
        
        //１）　@発注処理により登録された注文単位オブジェクトを取得する。
        //EQTYPEの拡張株式注文マネージャ.getOrderUnits(引数の予約注文単位.注文ID)をコールする。
        //以降、取得した注文単位オブジェクトの最初の要素を使用する。        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_rsvEqOrderUnit.getOrderId()); 
        
        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            String l_strErrorMsg = "注文単位テーブルに該当するデータがありません。OrderId = " + 
                l_rsvEqOrderUnit.getOrderId();
            
            log.error(l_strErrorMsg);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strErrorMsg);
        }
        
        OrderUnit l_orderUnit = l_orderUnits[0];       
        
        //２）　@発注済への更新処理を行う。
        //DB更新仕様
        //「連続注文発注（OK）_株式予約注文単位テーブル.xls」を参照。
        RsvEqOrderUnitParams l_rsvEqOrderParams = 
            new RsvEqOrderUnitParams((RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject());
        
        //注文単位ＩＤ  (   order_unit_id   ):  発注処理により登録された株式注文単位オブジェクト.注文単位ID
        l_rsvEqOrderParams.setOrderUnitId(l_orderUnit.getOrderUnitId());
                                                                             
        //注文履歴最終通番    (   last_order_action_serial_no ):  （既存値）＋１
        l_rsvEqOrderParams.setLastOrderActionSerialNo(
            l_rsvEqOrderParams.getLastOrderActionSerialNo() + 1);
                                                                             
        //注文状態    (   order_status    ):  "3:発注済（新規注文）
        //（OrderStatusEnumにて定義）" 
        l_rsvEqOrderParams.setOrderStatus(OrderStatusEnum.ORDERED);
                                                                             
        //注文有効状態  (   order_open_status   ):  "2:クローズ
        //（OrderOpenStatusEnumにて定義）"          
        l_rsvEqOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
                                                                    
        //失効区分    (   expiration_status   ):  "2:終了
        //（OrderExpirationStatusEnumにて定義）"             
        l_rsvEqOrderParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
                                                                   
        //更新日付    (   last_updated_timestamp  ):  現在日時
        l_rsvEqOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //DataNetworkException,DataQueryException
            
            l_queryProcessor.doUpdateQuery(l_rsvEqOrderParams); 
            //DataNetworkException,DataQueryException       
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //３）　@予約注文履歴を作成する。
        //株式予約注文更新サービス.insert株式予約注文履歴
        //(引数の株式予約注文単位.注文ID)をコールする。
        WEB3ToSuccReservationEqTypeOrderUpdateService l_rsvEqTypeOrderUpdateService = 
            (WEB3ToSuccReservationEqTypeOrderUpdateService)
                Services.getService(WEB3ToSuccReservationEqTypeOrderUpdateService.class);
                
        l_rsvEqTypeOrderUpdateService.insertReserveOrderAction(l_rsvEqOrderUnit.getOrderId());
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set発注済To先物OP予約注文単位)<BR>
     * （setOrderedToIfoOrderUnit）<BR>
     * <BR>
     * 指定された先物OP予約注文単位オブジェクトを、発注済の状態に更新する。<BR>
     * <BR>
     * １）　@発注処理により登録された注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 　@OP注文マネージャ.getOrderUnits(引数の予約注文単位.注文ID)をコールする。<BR>
     * 　@以降、取得した注文単位オブジェクトの最初の要素を使用する。<BR>
     * <BR>
     * ２）　@発注済への更新処理を行う。<BR>
     * <BR>
     * 　@DB更新仕様<BR>
     * 　@「連続注文発注（OK）_先物OP予約注文単位テーブル.xls」<BR>
     * 　@を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.insert予約注文履歴(引数の予約注文単位.注文ID)<BR>
     * 　@をコールする。<BR>
     * @@param l_rsvIfoOrderUnit - (予約注文単位)<BR>
     * 予約注文単位オブジェクト。
     * @@throws WEB3BaseException
     */
    protected void setOrderedToIfoOrderUnit(
        WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setOrderedToIfoOrderUnit(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnit == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "予約注文単位 = null。");
        }

        //１）　@発注処理により登録された注文単位オブジェクトを取得する。
        //OP注文マネージャ.getOrderUnits(引数の予約注文単位.注文ID)をコールする。
        //以降、取得した注文単位オブジェクトの最初の要素を使用する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        OrderUnit[] l_orderUnits = l_orderMgr.getOrderUnits(l_rsvIfoOrderUnit.getOrderId());

        if (l_orderUnits == null || l_orderUnits.length == 0)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        OrderUnit l_orderUnit = l_orderUnits[0];

        //２）　@発注済への更新処理を行う 
        //DB更新仕様 
        //「連続注文発注（OK）_先物OP予約注文単位テーブル.xls」
        //を参照。
        RsvIfoOrderUnitParams l_rsvIfoOrderParams =
            new RsvIfoOrderUnitParams((RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject());

        //注文単位ＩＤ(order_unit_id):発注処理により登録された先物OP注文単位オブジェクト.注文単位ID
        l_rsvIfoOrderParams.setOrderUnitId(l_orderUnit.getOrderUnitId());

        //注文履歴最終通番(last_order_action_serial_no):（既存値）＋１
        l_rsvIfoOrderParams.setLastOrderActionSerialNo(l_rsvIfoOrderParams.getLastOrderActionSerialNo() + 1);

        //注文状態(order_status):3:発注済（新規注文）（OrderStatusEnumにて定義）
        l_rsvIfoOrderParams.setOrderStatus(OrderStatusEnum.ORDERED);

        //注文有効状態(order_open_status):2:クローズ（OrderOpenStatusEnumにて定義）
        l_rsvIfoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        //失効区分(expiration_status):2:終了（OrderExpirationStatusEnumにて定義）
        l_rsvIfoOrderParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);

        //更新日付(last_updated_timestamp):現在日時
        l_rsvIfoOrderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doUpdateQuery(l_rsvIfoOrderParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //３）　@予約注文履歴を作成する。
        //先物OP予約注文更新サービス.insert予約注文履歴(引数の予約注文単位.注文ID)をコールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUpdateService.insertReserveOrderAction(l_rsvIfoOrderUnit.getOrderId());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set予約注文設定To株式親注文)<BR>
     * EQTYPEの親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * １）　@引数の親注文の注文単位より、cloneオブジェクトを作成する。<BR>
     * <BR>
     * ２）　@生成したcloneオブジェクトに、以下のプロパティを再セットする。<BR>
     * <BR>
     * 　@　@　@　@予約注文設定フラグ：　@"設定の可能性あり"<BR>
     * 　@　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ３）　@EQTYPEの拡張株式注文マネージャ.update注文データ()により、<BR>
     * 　@　@　@　@　@親注文の注文単位をupdateする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜update注文データ()：引数設定仕様＞<BR>
     * <BR>
     * 　@注文単位：　@作成した注文単位のcloneオブジェクト <BR>
     * 　@is履歴作成：　@false（履歴を作成しない） <BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ４）　@returnする。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。
     * @@throws WEB3BaseException
     * @@roseuid 432A23490069
     */
    protected void setReserveOrderSettingToEqtypeParentOrder(
        EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setReserveOrderSettingToEqtypeParentOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        //１）　@引数の親注文の注文単位より、cloneオブジェクトを作成する。
        EqtypeOrderUnitParams l_eqOrderUnitParams = 
            new EqtypeOrderUnitParams((EqtypeOrderUnitRow)l_parentOrderUnit.getDataSourceObject());  
            
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        EqTypeOrderUnit l_cloneEqOrderUnit = 
            (EqTypeOrderUnit)l_orderMgr.toOrderUnit(l_eqOrderUnitParams);                      
        
        //２）　@生成したcloneオブジェクトに、以下のプロパティを再セットする。
        //予約注文設定フラグ：　@"設定の可能性あり"
        //更新日付：　@GtlUtils.getSystemTimestamp() 
        l_eqOrderUnitParams.setReserveOrderExistFlag(WEB3ReserveOrderExistFlagDef.SET_POSSIBLE);
        l_eqOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //３）　@EQTYPEの拡張株式注文マネージャ.update注文データ()により、
        //親注文の注文単位をupdateする。            
        l_orderMgr.updateOrderData(l_cloneEqOrderUnit, false);
        
        //４）　@returnする。
        log.exiting(STR_METHOD_NAME); 
        return;        
    }

    /**
     * (set予約注文設定To先物OP親注文)<BR>
     * 親注文の注文単位に、予約注文登録を記録する。<BR>
     * <BR>
     * １）　@引数の親注文の注文単位より、cloneオブジェクトを作成する。<BR>
     * <BR>
     * ２）　@生成したcloneオブジェクトに、以下のプロパティを再セットする。<BR>
     * <BR>
     * 　@　@　@　@予約注文設定フラグ：　@"設定の可能性あり"<BR>
     * 　@　@　@　@更新日付：　@GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ３）　@OP注文マネージャ.update注文データ()により、<BR>
     * 　@　@　@　@　@親注文の注文単位をupdateする。<BR>
     * <BR>
     * 　@-------------------------------------------------------------<BR>
     * 　@＜update注文データ()：引数設定仕様＞<BR>
     * <BR>
     * 　@注文単位：　@作成した注文単位のcloneオブジェクト<BR>
     * 　@is履歴作成：　@false（履歴を作成しない）<BR>
     * 　@-------------------------------------------------------------<BR>
     * <BR>
     * ４）　@returnする。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位<BR>
     * @@throws WEB3BaseException
     */
    public void setReserveOrderSettingToIfoParentOrder(
        IfoOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setReserveOrderSettingToIfoParentOrder(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "親注文の注文単位 = null。");
        }

        //１）　@引数の親注文の注文単位より、cloneオブジェクトを作成する。
        IfoOrderUnitParams l_ifoOrderUnitParams =
            new IfoOrderUnitParams((IfoOrderUnitRow)l_parentOrderUnit.getDataSourceObject());

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderMgr =
            (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

        IfoOrderUnit l_cloneIfoOrderUnit =
            (IfoOrderUnit)l_orderMgr.toOrderUnit(l_ifoOrderUnitParams);

        //２）　@生成したcloneオブジェクトに、以下のプロパティを再セットする。
        //予約注文設定フラグ：　@"設定の可能性あり"
        //更新日付：　@GtlUtils.getSystemTimestamp()
        l_ifoOrderUnitParams.setReserveOrderExistFlag(WEB3ReserveOrderExistFlagDef.SET_POSSIBLE);
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //３）　@OP注文マネージャ.update注文データ()により、
        //親注文の注文単位をupdateする。
        l_orderMgr.updateOrderData(l_cloneIfoOrderUnit, false);

        //４）　@returnする。
        log.exiting(STR_METHOD_NAME);
        return;
    }

    /**
     * (create建株)<BR>
     * 引数の注文単位より建株（仮想）を作成する。<BR>
     * <BR>
     * ※反対売買時に使用する。<BR>
     * 　@上記以外の場合の使用は禁止。<BR>
     * <BR>
     * １）　@建株Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@建株ID = 0<BR>
     * 　@口座ID = 注文単位の同項目<BR>
     * 　@補助口座ID = 注文単位の同項目<BR>
     * 　@市場ID = 注文単位の同項目<BR>
     * 　@元建株数 = 注文単位.注文数量<BR>
     * 　@建株数 = 注文単位.注文数量<BR>
     * 　@元建単価 = 注文単位.注文単価<BR>
     * 　@建単価 = 注文単位.注文単価<BR>
     * 　@建区分 = 注文単位.getSide() == "買"の場合、"買建"<BR>
     * 　@　@以外、"売建"<BR>
     * 　@建日 = 注文単位.発注日<BR>
     * 　@期日 = 株式ポジションマネージャ.get建株期日(建日, 注文単位.弁済期限値)<BR>
     * 　@元建手数料 = (*1)<BR>
     * 　@建手数料 = (*1)<BR>
     * 　@元建手数料消費税 = (*1)<BR>
     * 　@建手数料消費税 = (*1)<BR>
     * 　@名義書換料 = 0<BR>
     * 　@名義書換料消費税 = 0<BR>
     * 　@管理費 = 0<BR>
     * 　@管理費消費税 = 0<BR>
     * 　@順日歩 = 0<BR>
     * 　@順日歩消費税 = 0<BR>
     * 　@逆日歩 = 0<BR>
     * 　@逆日歩消費税 = 0<BR>
     * 　@貸株料 = 0<BR>
     * 　@その他 = 0<BR>
     * 　@保証金率 = 0<BR>
     * 　@現金保証金率 = 0<BR>
     * 　@建株評価損益 = 0<BR>
     * 　@銘柄ID = 注文単位の同項目<BR>
     * 　@銘柄タイプ = 注文単位の同項目<BR>
     * 　@税区分 = 注文単位の同項目<BR>
     * 　@弁済区分 = 注文単位の同項目<BR>
     * 　@弁済期限値 = 注文単位の同項目<BR>
     * 　@作成日付 = GtlUtils.getSystemTimestamp()<BR>
     * 　@更新日付 = GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * 　@(*1)以下の手順にて新規建手数料を取得する。<BR>
     * 　@　@①@手数料オブジェクトを作成する。<BR>
     * 　@　@　@株式計算サービス.create手数料()をコールする。<BR>
     * <BR>
     * 　@　@　@[create手数料()に指定する引数]<BR>
     * 　@　@　@　@注文ID：　@注文単位.注文ID<BR>
     * <BR>
     * 　@　@②作成した手数料オブジェクトに諸経費計算用代金を<BR>
     * 　@　@　@セットする。<BR>
     * 　@　@　@手数料.set諸経費計算用代金()をコールする。<BR>
     * <BR>
     * 　@　@　@[set諸経費計算用代金()に指定する引数]<BR>
     * 　@　@　@　@諸経費計算用代金：　@注文単位.概算受渡代金<BR>
     * <BR>
     * 　@　@③委託手数料を算出する。<BR>
     * 　@　@　@株式計算サービス.calc委託手数料()をコールする。<BR>
     * <BR>
     * 　@　@　@[calc委託手数料()に指定する引数]<BR>
     * 　@　@　@　@手数料：　@作成した手数料オブジェクト<BR>
     * 　@　@　@　@補助口座：　@注文単位.補助口座IDに該当する補助口座<BR>
     * <BR>
     * 　@　@④③の戻り値を使用し、プロパティをセットする。<BR>
     * 　@　@　@元建手数料、建手数料 = ③の戻り値.手数料金額<BR>
     * 　@　@　@元建手数料消費税、建手数料消費税 = 株式計算サービス.calc消費税()<BR>
     * <BR>
     * 　@　@　@[calc消費税()に指定する引数]<BR>
     * 　@　@　@　@金額：　@③の戻り値.手数料金額<BR>
     * 　@　@　@　@基準日：　@注文単位.発注日<BR>
     * 　@　@　@　@補助口座：　@③にて取得した補助口座<BR>
     * <BR>
     * ３）　@建株を作成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@arg0：　@プロパティセットしたインスタンス<BR>
     * <BR>
     * ４）　@作成した建株を返却する。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 4329526402DD
     */
    public WEB3EquityContract createContract(EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createContract(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_parentOrderUnit == null )
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        //１）　@建株Paramsインスタンスを生成する。 
        EqtypeContractParams l_eqContractParams = new EqtypeContractParams();

        //２）　@生成したインスタンスに以下のプロパティをセットする。 
        EqtypeOrderUnitRow l_eqOrderUnitRow = (EqtypeOrderUnitRow)l_parentOrderUnit.getDataSourceObject();
        
        //  建株ID = 0 
        l_eqContractParams.setContractId(0);
        
        //  口座ID = 注文単位の同項目 
        l_eqContractParams.setAccountId(l_parentOrderUnit.getAccountId());
        
        //  補助口座ID = 注文単位の同項目 
        l_eqContractParams.setSubAccountId(l_parentOrderUnit.getSubAccountId());
        
        //  市場ID = 注文単位の同項目 
        l_eqContractParams.setMarketId(l_eqOrderUnitRow.getMarketId());        
        
        //  元建株数 = 注文単位.注文数量 
        l_eqContractParams.setOriginalQuantity(l_parentOrderUnit.getQuantity());
        
        //  建株数 = 注文単位.注文数量 
        l_eqContractParams.setQuantity(l_parentOrderUnit.getQuantity());
        
        //  元建単価 = 注文単位.注文単価 
        l_eqContractParams.setOriginalContractPrice(l_eqOrderUnitRow.getPrice());                
        
        //  建単価 = 注文単位.注文単価 
        l_eqContractParams.setContractPrice(l_eqOrderUnitRow.getPrice());          
        
        //  建区分 = 注文単位.getSide() == "買"の場合、"買建" 
        if (SideEnum.BUY.equals(l_parentOrderUnit.getSide()))
        {
            l_eqContractParams.setContractType(ContractTypeEnum.LONG);      
        }
        //    以外、"売建" 
        else
        {
            l_eqContractParams.setContractType(ContractTypeEnum.SHORT);
        }      
        
        //  建日 = 注文単位.発注日 
        Date l_datBizDate = WEB3DateUtility.getDate(l_eqOrderUnitRow.getBizDate(), "yyyyMMdd");
        l_eqContractParams.setOpenDate(l_datBizDate);
        
        //  期日 = 株式ポジションマネージャ.get建株期日(建日, 注文単位.弁済期限値) 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager) l_tradingModule.getPositionManager();
        Date l_datCloseDate = l_positionManager.getContractCloseDate(
            l_eqContractParams.getOpenDate(), l_eqOrderUnitRow.getRepaymentNum());
        
        l_eqContractParams.setCloseDate(l_datCloseDate);
        
        //  (*1)以下の手順にて新規建手数料を取得する。 
        //    ①@手数料オブジェクトを作成する。 
        //      株式計算サービス.create手数料()をコールする。 
        //
        //      [create手数料()に指定する引数] 
        //        注文ID：　@注文単位.注文ID 
        WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
            (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission = 
            l_eqBizLogicProvider.createCommission(l_parentOrderUnit.getOrderId());        
        
        //    ②作成した手数料オブジェクトに諸経費計算用代金を 
        //      セットする。 
        //      手数料.set諸経費計算用代金()をコールする。 
        //
        //      [set諸経費計算用代金()に指定する引数] 
        //        諸経費計算用代金：　@注文単位.概算受渡代金 
        l_commission.setExpensesCalcAmount(l_eqOrderUnitRow.getEstimatedPrice());

        //    ③委託手数料を算出する。 
        //      株式計算サービス.calc委託手数料()をコールする。 
        //
        //      [calc委託手数料()に指定する引数] 
        //        手数料：　@作成した手数料オブジェクト 
        //        補助口座：　@注文単位.補助口座IDに該当する補助口座 
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_parentOrderUnit.getAccountId(),
                    l_parentOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        l_eqBizLogicProvider.calcCommission(l_commission, l_subAccount);

        //    ④③の戻り値を使用し、プロパティをセットする。 
        //      元建手数料、建手数料 = ③の戻り値.手数料金額 
        //      元建手数料消費税、建手数料消費税 = 株式計算サービス.calc消費税() 
        //
        //      [calc消費税()に指定する引数] 
        //        金額：　@③の戻り値.手数料金額 
        //        基準日：　@注文単位.発注日 
        //        補助口座：　@③にて取得した補助口座         
        
        //  元建手数料 = (*1)
        double l_dblCommission = l_commission.getCommission(); 
        l_eqContractParams.setOriginalSetupFee(l_dblCommission);
        
        //  建手数料 = (*1) 
        l_eqContractParams.setSetupFee(l_dblCommission);
        
        //  元建手数料消費税 = (*1) 
        double l_dblSalesTax = l_eqBizLogicProvider.calcSalesTax(
            l_dblCommission, 
            new Timestamp(l_datBizDate.getTime()), 
            l_subAccount);
        l_eqContractParams.setOriginalSetupFeeTax(l_dblSalesTax);
        
        //  建手数料消費税 = (*1) 
        l_eqContractParams.setSetupFeeTax(l_dblSalesTax);
        
        //  名義書換料 = 0
        l_eqContractParams.setNameTransferFee(0);
         
        //  名義書換料消費税 = 0 
        l_eqContractParams.setNameTransferFeeTax(0);
        
        //  管理費 = 0 
        l_eqContractParams.setManagementFee(0);
        
        //  管理費消費税 = 0 
        l_eqContractParams.setManagementFeeTax(0);
        
        //  順日歩 = 0 
        l_eqContractParams.setInterestFee(0);
        
        //  順日歩消費税 = 0 
        l_eqContractParams.setInterestFeeTax(0);
        
        //  逆日歩 = 0 
        l_eqContractParams.setPayInterestFee(0);
        
        //  逆日歩消費税 = 0 
        l_eqContractParams.setPayInterestFeeTax(0);
        
        //  貸株料 = 0 
        l_eqContractParams.setLoanEquityFee(0);
        
        //  その他 = 0 
        l_eqContractParams.setOther(0);
        
        //  保証金率 = 0 
        l_eqContractParams.setMarginDepositRate(0);
        
        //  現金保証金率 = 0 
        l_eqContractParams.setCashMarginDepositRate(0);
        
        //  建株評価損益 = 0
        l_eqContractParams.setContractAssetProfitLoss(0);         
        
        //  銘柄ID = 注文単位の同項目 
        l_eqContractParams.setProductId(l_eqOrderUnitRow.getProductId());
        
        //  銘柄タイプ = 注文単位の同項目
        l_eqContractParams.setProductType(l_eqOrderUnitRow.getProductType());
         
        //  税区分 = 注文単位の同項目
        l_eqContractParams.setTaxType(l_parentOrderUnit.getTaxType());
         
        //  弁済区分 = 注文単位の同項目
        l_eqContractParams.setRepaymentType(l_eqOrderUnitRow.getRepaymentType());
         
        //  弁済期限値 = 注文単位の同項目 
        if (!l_eqOrderUnitRow.getRepaymentNumIsNull())
        {
            l_eqContractParams.setRepaymentNum(l_eqOrderUnitRow.getRepaymentNum());
        }        
        
        //  作成日付 = GtlUtils.getSystemTimestamp() 
        l_eqContractParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //  更新日付 = GtlUtils.getSystemTimestamp() 
        l_eqContractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //３）　@建株を作成する。 
        //
        //  [コンストラクタに指定する引数] 
        //    arg0：　@プロパティセットしたインスタンス 
        WEB3EquityContract l_eqContract = new WEB3EquityContract(l_eqContractParams);

        //４）　@作成した建株を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_eqContract;        
    }

    /**
     * (create建玉)<BR>
     * 引数の注文単位より建玉（仮想）を作成する。<BR>
     * <BR>
     * ※反対売買時に使用する。<BR>
     * 　@上記以外の場合の使用は禁止。<BR>
     * <BR>
     * １）　@注文単位から先物OP取引銘柄オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@建玉Paramsインスタンスを生成する。<BR>
     * <BR>
     * ３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@建玉ID = 0<BR>
     * 　@口座ID = 注文単位の同項目<BR>
     * 　@補助口座ID = 注文単位の同項目<BR>
     * 　@市場ID = 注文単位の同項目<BR>
     * 　@1単位当り乗数 = １）で取得した先物OP取引銘柄.getUnitSize()<BR>
     * 　@建玉元数量 = 注文単位.注文数量<BR>
     * 　@建玉数量 = 注文単位.注文数量<BR>
     * 　@元建単価 = 注文単位.注文単価<BR>
     * 　@建単価 = 注文単位.注文単価<BR>
     * 　@建区分 = 注文単位.getSide() == "買"の場合、"買建"<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@以外、"売建"<BR>
     * 　@建日 = 注文単位.発注日<BR>
     * 　@期日 = １）で取得した先物OP取引銘柄.getLastTradingDate()<BR>
     * 　@建委託手数料 = (*1)<BR>
     * 　@建委託手数料消費税 = (*1)<BR>
     * 　@管理費 = 0<BR>
     * 　@管理費消費税 = 0<BR>
     * 　@利子 = 0<BR>
     * 　@利子消費税 = 0<BR>
     * 　@銘柄ID = 注文単位の同項目<BR>
     * 　@銘柄タイプ = 注文単位の同項目<BR>
     * 　@作成日付 = GtlUtils.getSystemTimestamp()<BR>
     * 　@更新日付 = GtlUtils.getSystemTimestamp()<BR>
     * 　@受渡日 = 注文単位の同項目<BR>
     * 　@立会区分 = 注文単位の同項目<BR>
     * <BR>
     * <BR>
     * 　@(*1)以下の手順にて新規建手数料を取得する。<BR>
     * 　@　@①@手数料オブジェクトを作成する。<BR>
     * 　@　@　@先物OP計算サービス.create手数料()をコールする。<BR>
     * <BR>
     * 　@　@　@[指定する引数]<BR>
     * 　@　@　@　@注文単位ID：　@注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@②作成した手数料オブジェクトに諸経費計算用代金を<BR>
     * 　@　@　@セットする。<BR>
     * 　@　@　@手数料.set諸経費計算用代金()をコールする。<BR>
     * <BR>
     * 　@　@　@[指定する引数]<BR>
     * 　@　@　@　@諸経費計算用代金：<BR>
     * 　@　@　@　@　@注文単位.注文数量 × 注文単位.注文単価 × １）<BR>
     * 　@　@　@　@　@で取得した先物OP取引銘柄.getUnitSize()<BR>
     * <BR>
     * 　@　@③委託手数料を算出する。<BR>
     * 　@　@　@先物OP計算サービス.calc委託手数料()をコールする。<BR>
     * <BR>
     * 　@　@　@[指定する引数]<BR>
     * 　@　@　@　@手数料：　@作成した手数料オブジェクト<BR>
     * 　@　@　@　@補助口座：　@注文単位.補助口座IDに該当する補助口座<BR>
     * <BR>
     * 　@　@④③の戻り値を使用し、プロパティをセットする。<BR>
     * 　@　@　@建委託手数料 = ③の戻り値.手数料金額<BR>
     * 　@　@　@建委託手数料消費税 = 先物OP計算サービス.calc消費税()<BR>
     * <BR>
     * 　@　@　@[指定する引数]<BR>
     * 　@　@　@　@金額：　@③の戻り値.手数料金額<BR>
     * 　@　@　@　@基準日：　@注文単位.発注日<BR>
     * 　@　@　@　@補助口座：　@③にて取得した補助口座<BR>
     * <BR>
     * ４）　@建玉を作成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@arg0：　@プロパティセットしたインスタンス<BR>
     * <BR>
     * ５）　@作成した建玉を返却する。<BR>
     * @@param l_parentOrderUnit - (注文単位)<BR>
     * 親注文の注文単位オブジェクト。<BR>
     * @@return WEB3IfoContractImpl
     * @@throws WEB3BaseException
     */
    public WEB3IfoContractImpl createIfoContract(IfoOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoContract(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_parentOrderUnit == null )
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "親注文の注文単位 = null。");
        }

        //１）　@注文単位から先物OP取引銘柄オブジェクトを生成する。
        WEB3IfoTradedProductImpl l_ifoTradedProductImpl =
            (WEB3IfoTradedProductImpl)l_parentOrderUnit.getTradedProduct();

        //２）　@建玉Paramsインスタンスを生成する。
        IfoContractParams l_ifoContractParams = new IfoContractParams();
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_parentOrderUnit.getDataSourceObject();

        //３）　@生成したインスタンスに以下のプロパティをセットする。
        //建玉ID = 0
        l_ifoContractParams.setContractId(0L);

        //口座ID = 注文単位の同項目
        l_ifoContractParams.setAccountId(l_parentOrderUnit.getAccountId());

        //補助口座ID = 注文単位の同項目
        l_ifoContractParams.setSubAccountId(l_parentOrderUnit.getSubAccountId());

        //市場ID = 注文単位の同項目
        l_ifoContractParams.setMarketId(l_ifoOrderUnitRow.getMarketId());

        //1単位当り乗数 = １）で取得した先物OP取引銘柄.getUnitSize()
        l_ifoContractParams.setUnitSize(l_ifoTradedProductImpl.getUnitSize());

        //建玉元数量 = 注文単位.注文数量
        l_ifoContractParams.setOriginalQuantity(l_parentOrderUnit.getQuantity());

        //建玉数量 = 注文単位.注文数量
        l_ifoContractParams.setQuantity(l_parentOrderUnit.getQuantity());

        //元建単価 = 注文単位.注文単価
        l_ifoContractParams.setOriginalContractPrice(l_ifoOrderUnitRow.getPrice());

        //建単価 = 注文単位.注文単価
        l_ifoContractParams.setContractPrice(l_ifoOrderUnitRow.getPrice());

        //建区分 = 注文単位.getSide() == "買"の場合、"買建"
        //以外、"売建"
        if (SideEnum.BUY.equals(l_parentOrderUnit.getSide()))
        {
            l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
        }
        else
        {
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        }

        //建日 = 注文単位.発注日
        Date l_datBizDate = WEB3DateUtility.getDate(
            l_ifoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        l_ifoContractParams.setOpenDate(l_datBizDate);

        //期日 = １）で取得した先物OP取引銘柄.getLastTradingDate()
        l_ifoContractParams.setCloseDate(l_ifoTradedProductImpl.getLastTradingDate());

        //(*1)以下の手順にて新規建手数料を取得する。
        //①@手数料オブジェクトを作成する。
        //先物OP計算サービス.create手数料()をコールする。
        //[指定する引数]
         //注文単位ID：　@注文単位.注文単位ID
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoBizLogicProvider l_ifoBizLogicProvider =
            (WEB3IfoBizLogicProvider)l_tradingModule.getBizLogicProvider();
        WEB3GentradeCommission l_commission =
            l_ifoBizLogicProvider.createCommission(l_parentOrderUnit.getOrderUnitId());

        //②作成した手数料オブジェクトに諸経費計算用代金を
        //セットする。
        //手数料.set諸経費計算用代金()をコールする。
        //[指定する引数]
        //諸経費計算用代金：
        //注文単位.注文数量 × 注文単位.注文単価 × １）で取得した先物OP取引銘柄.getUnitSize()
        BigDecimal l_bdQuantity = new BigDecimal("" + l_parentOrderUnit.getQuantity());
        BigDecimal l_bdPrice = new BigDecimal("" + l_ifoOrderUnitRow.getPrice());
        BigDecimal l_bdUnitSize = new BigDecimal("" + l_ifoTradedProductImpl.getUnitSize());
        double l_dblSetupFee = l_bdQuantity.multiply(l_bdPrice).multiply(l_bdUnitSize).doubleValue();
        l_commission.setExpensesCalcAmount(l_dblSetupFee);

        //③委託手数料を算出する。
        //先物OP計算サービス.calc委託手数料()をコールする。
        //[指定する引数]
        //手数料：　@作成した手数料オブジェクト
        //補助口座：　@注文単位.補助口座IDに該当する補助口座
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount =
                l_finApp.getAccountManager().getSubAccount(
                    l_parentOrderUnit.getAccountId(),
                    l_parentOrderUnit.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("補助口座テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        l_ifoBizLogicProvider.calcCommission(l_commission, l_subAccount);

        //④③の戻り値を使用し、プロパティをセットする。
        //建委託手数料 = ③の戻り値.手数料金額
        //建委託手数料消費税 = 先物OP計算サービス.calc消費税()
        //[指定する引数]
        //金額：　@③の戻り値.手数料金額
        //基準日：　@注文単位.発注日
        //補助口座：　@③にて取得した補助口座

        //建委託手数料 = (*1)
        double l_dblCommission = l_commission.getCommission();
        l_ifoContractParams.setSetupFee(l_dblCommission);

        //建委託手数料消費税 = (*1)
        double l_dblSetupFeeTax = l_ifoBizLogicProvider.calcSalesTax(
            l_dblCommission, new Timestamp(l_datBizDate.getTime()), l_subAccount);
        l_ifoContractParams.setSetupFeeTax(l_dblSetupFeeTax);

        //管理費 = 0
        l_ifoContractParams.setManagementFee(0.0d);

        //管理費消費税 = 0
        l_ifoContractParams.setManagementFeeTax(0.0d);

        //利子 = 0
        l_ifoContractParams.setInterestFee(0.0d);

        //利子消費税 = 0
        l_ifoContractParams.setInterestFeeTax(0.0d);

        //銘柄ID = 注文単位の同項目
        l_ifoContractParams.setProductId(l_ifoOrderUnitRow.getProductId());

        //銘柄タイプ = 注文単位の同項目
        l_ifoContractParams.setProductType(l_parentOrderUnit.getProductType());

        //作成日付 = GtlUtils.getSystemTimestamp()
        l_ifoContractParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //更新日付 = GtlUtils.getSystemTimestamp()
        l_ifoContractParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //受渡日 = 注文単位の同項目
        l_ifoContractParams.setDeliveryDate(l_parentOrderUnit.getDeliveryDate());

        //立会区分 = 注文単位の同項目
        l_ifoContractParams.setSessionType(l_ifoOrderUnitRow.getSessionType());

        //４）　@建玉を作成する。
        //[コンストラクタに指定する引数]
        //arg0：　@プロパティセットしたインスタンス
        WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);

        //５）　@作成した建玉を返却する。
        return l_ifoContractImpl;
    }

    /**
     * (create保有資産)<BR>
     * 引数の注文単位より保有資産を作成する。<BR>
     * <BR>
     * ※反対売買時に使用する。<BR>
     * 　@上記以外の場合の使用は禁止。<BR>
     * <BR>
     * １）　@保有資産Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@資産ID = 0<BR>
     * 　@口座ID = 注文単位の同項目<BR>
     * 　@補助口座ID = 注文単位の同項目<BR>
     * 　@銘柄タイプ = 注文単位の同項目<BR>
     * 　@数量 = 注文単位.注文数量<BR>
     * 　@売付不能数量 = 0<BR>
     * 　@数量（簿価単価計算用） = 注文単位.注文数量<BR>
     * 　@簿価（簿価単価計算用） = 0<BR>
     * 　@入力簿価単価 = null<BR>
     * 　@簿価単価入力日時 = null<BR>
     * 　@名義書換料 = 0<BR>
     * 　@名義書換料消費税 = 0<BR>
     * 　@口座管理費 = 0<BR>
     * 　@口座管理費消費税 = 0<BR>
     * 　@銘柄ID = 注文単位の同項目<BR>
     * 　@税区分 = 注文単位の同項目<BR>
     * 　@ミニ株区分 = "DEFAULT（ミニ株でない）"<BR>
     * 　@分配金 = 0<BR>
     * 　@30日未経過残高口数 = 0<BR>
     * 　@作成日付 = GtlUtils.getSystemTimestamp()<BR>
     * 　@更新日付 = GtlUtils.getSystemTimestamp()<BR>
     * <BR>
     * ３）　@保有資産を作成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@arg0：　@プロパティセットしたインスタンス<BR>
     * <BR>
     * ４）　@作成した保有資産を返却する。
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト。
     * @@return WEB3EquityAsset
     * @@throws WEB3BaseException
     * @@roseuid 4329599D009A
     */
    public WEB3EquityAsset createAsset(EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createAsset(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_parentOrderUnit == null)
        {
            log.debug("親注文の注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "親注文の注文単位 = null。");
        }
        
        //１）　@保有資産Paramsインスタンスを生成する。
        AssetParams l_assetParams = new AssetParams();
        
        Product l_product = l_parentOrderUnit.getProduct();
        
        //２）　@生成したインスタンスに以下のプロパティをセットする。 
        //  資産ID = 0 
        l_assetParams.setAssetId(0);
        
        //  口座ID = 注文単位の同項目 
        l_assetParams.setAccountId(l_parentOrderUnit.getAccountId());
        
        //  補助口座ID = 注文単位の同項目 
        l_assetParams.setSubAccountId(l_parentOrderUnit.getSubAccountId());
        
        //  銘柄タイプ = 注文単位の同項目 
        l_assetParams.setProductType(l_product.getProductType());
        
        //  数量 = 注文単位.注文数量 
        l_assetParams.setQuantity(l_parentOrderUnit.getQuantity());
        
        //  売付不能数量 = 0 
        l_assetParams.setQuantityCannotSell(0);
        
        //  数量（簿価単価計算用） = 注文単位.注文数量 
        l_assetParams.setQuantityForBookValue(l_parentOrderUnit.getQuantity());
        
        //  簿価（簿価単価計算用） = 0 
        l_assetParams.setBookValue(0);
        
        //  入力簿価単価 = null         
        //  簿価単価入力日時 = null 
        
        //  名義書換料 = 0 
        l_assetParams.setSetupFee(0);
        
        //  名義書換料消費税 = 0 
        l_assetParams.setSetupFeeTax(0);
        
        //  口座管理費 = 0 
        l_assetParams.setManagementFee(0);
        
        //  口座管理費消費税 = 0 
        l_assetParams.setManagementFeeTax(0);
        
        //  銘柄ID = 注文単位の同項目 
        l_assetParams.setProductId(l_product.getProductId());
        
        //  税区分 = 注文単位の同項目 
        l_assetParams.setTaxType(l_parentOrderUnit.getTaxType());
        
        //  ミニ株区分 = "DEFAULT（ミニ株でない）" 
        l_assetParams.setMiniStockDiv(WEB3MiniStockDivDef.DEFAULT_MINI_STOCK);
        
        //  分配金 = 0 
        l_assetParams.setProfitDistribution(0);
        
        //  30日未経過残高口数 = 0 
        l_assetParams.setCountBeforePenalty(0);
        
        //  作成日付 = GtlUtils.getSystemTimestamp() 
        l_assetParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //  更新日付 = GtlUtils.getSystemTimestamp() 
        l_assetParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //３）　@保有資産を作成する。 
        //
        //  [コンストラクタに指定する引数] 
        //    arg0：　@プロパティセットしたインスタンス 
        WEB3EquityAsset l_eqAsset = new WEB3EquityAsset(l_assetParams);
        
        log.exiting(STR_METHOD_NAME); 
        return l_eqAsset;
    }
    
    /**
     * (create決済建株エントリ)<BR>
     * 決済建株エントリの配列を作成する。<BR>
     * <BR>
     * EqtypeSettleContractOrderEntryインスタンスを<BR>
     * 作成し、そのインスタンスのみを要素とする配列を返却する。<BR>
     * <BR>
     * [コンストラクタに指定する引数]<BR>
     * 　@arg0：　@0(固定)<BR>
     * 　@arg1：　@パラメータ.決済建株明細一覧[0].株数<BR>
     * @@param l_marginCloseMarginContractUnitList - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 43313EE402FF
     */
    public EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_marginCloseMarginContractUnitList) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createClosingContractEntry(WEB3MarginCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME); 
        
        if (l_marginCloseMarginContractUnitList == null || 
            l_marginCloseMarginContractUnitList.length == 0)
        {
            log.debug("決済建株明細一覧 = null or length = 0。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "決済建株明細一覧 = null or length = 0。");
        }
        
        //EqtypeSettleContractOrderEntryインスタンスを 
        //作成し、そのインスタンスのみを要素とする配列を返却する。
        EqTypeSettleContractOrderEntry l_eqTypeSettleContractOrderEntry = new EqTypeSettleContractOrderEntry(
            0, 
            Double.parseDouble(l_marginCloseMarginContractUnitList[0].orderQuantity));
                    
        log.exiting(STR_METHOD_NAME);             
        return new EqTypeSettleContractOrderEntry[]{l_eqTypeSettleContractOrderEntry};
    }

    /**
     * (create返済建玉エントリ)<BR>
     * 返済建玉エントリの配列を作成する。<BR>
     * <BR>
     * SettleContractEntryインスタンスを<BR>
     * <BR>
     * 作成し、そのインスタンスのみを要素とする配列を返却する。<BR>
     * <BR>
     * [コンストラクタに指定する引数]<BR>
     * 　@arg0：　@0(固定)<BR>
     * 　@arg1：　@パラメータ.返済建玉[0].数量<BR>
     * @@param l_CloseMarginContractUnit  - (返済建玉)<BR>
     * 返済建玉オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
     * @@return SettleContractEntry[]
     * @@throws WEB3BaseException
     */
    public SettleContractEntry[] createSettleContractEntry(
        WEB3FuturesOptionsCloseMarginContractUnit[] l_CloseMarginContractUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractEntry(WEB3FuturesOptionsCloseMarginContractUnit[])";
        log.entering(STR_METHOD_NAME);

        if (l_CloseMarginContractUnit == null || l_CloseMarginContractUnit.length == 0)
        {
            log.debug("返済建玉 = null or length = 0。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済建玉 = null or length = 0。");
        }

        //SettleContractEntryインスタンスを
        //作成し、そのインスタンスのみを要素とする配列を返却する。
        SettleContractEntry l_settleContractEntry = new SettleContractEntry(
            0, Double.parseDouble(l_CloseMarginContractUnit[0].contractOrderQuantity));

        log.exiting(STR_METHOD_NAME);
        return new SettleContractEntry[]{l_settleContractEntry};
    }

    /**
     * (create建株明細ByOrder)<BR>
     * 注文に関連した信用取引建株明細（照会用）を配列で取得する。<BR>
     * 指定注文が新規建注文の場合は、nullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続注文）create建株明細ByOrder」参照。<BR>
     * @@param l_rsvEqOrderUnit - (予約注文単位)<BR>
     * 株式予約注文単位オブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 433A4AC202BF
     */
    public WEB3MarginContractUnit[] createContractUnitByOrder(
        WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createContractUnitByOrder(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約注文単位 = null。");
        }
        
        //1.1:新規建注文（予約注文単位.注文カテゴリ == "新規建注文"）の場合
        if (OrderCategEnum.OPEN_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            //処理を終了する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }
            
        //1.2:ArrayList( )
        //信用取引建株明細を格納するArrayListを生成する。
        List l_lisMarginContractUnits = new ArrayList();
        
        //1.3: is反対売買取引( )
        boolean l_blnIsRerverseTrade = l_rsvEqOrderUnit.isReversingTrade();
        
        //1.4:反対売買（is反対売買取引()の戻り値 == true）の場合
        if (l_blnIsRerverseTrade)
        {
            //1.4.1:get親注文の注文単位( )
            EqTypeOrderUnit l_eqOrderUnit = l_rsvEqOrderUnit.getParentOrderUnit();
            
            //1.4.2:create建株明細(注文単位)
            WEB3MarginContractUnit l_marginContractUnit = this.createMarginContractUnit(l_eqOrderUnit);
            
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow =
                (RsvEqOrderUnitRow)l_rsvEqOrderUnit.getDataSourceObject();
            // 予約注文単位.決済順序区分 == "ランダム"の場合
            if (WEB3ClosingOrderDef.RANDOM.equals(l_rsvEqOrderUnitRow.getClosingOrderType()))
            {
                l_marginContractUnit.orderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_rsvEqOrderUnitRow.getQuantity());
            }
            
            //1.4.3:add(arg0 : Object)
            l_lisMarginContractUnits.add(l_marginContractUnit);
        }
        //1.5:(*)反対売買でない場合
        else
        {
            //1.5.1:get株式予約建株返済指定情報一覧( )
            RsvEqClosingContractSpecRow[] l_rsvEqClosingContractSpecRows = 
                l_rsvEqOrderUnit.getContractsToClose();
                
            //1.5.2:get株式予約建株返済指定情報一覧()の戻り値の要素数分Loop処理
            int l_intCnt = 0;
            if (l_rsvEqClosingContractSpecRows != null && 
                l_rsvEqClosingContractSpecRows.length > 0)
            {
                l_intCnt = l_rsvEqClosingContractSpecRows.length;
            }
            
            for (int i = 0; i < l_intCnt; i++)
            {
                RsvEqClosingContractSpecRow l_rsvEqClosingContractSpecRow = 
                    l_rsvEqClosingContractSpecRows[i];
                
                //1.5.2.1:get建株(建株ID : long)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
                WEB3EquityPositionManager l_positionManager =
                    (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
                
                WEB3EquityContract l_contract = null;    
                try
                {
                    l_contract = 
                        (WEB3EquityContract) l_positionManager.getContract(l_rsvEqClosingContractSpecRow.getContractId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("建株テーブルに該当するデータがありません。", l_ex);

                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                
                //1.5.2.2:建株決済済チェック
                //(*)建株決済済チェック
                //get建株()の戻り値.建株数 < 処理対象の要素.返済注文数量の場合、nullを返却して終了する。
                if (l_contract.getQuantity() < l_rsvEqClosingContractSpecRow.getQuantity())
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }
                
                //1.5.2.3:get建代金(数量 : double)
                double l_dblContractAmount = 
                    l_contract.getContractAmount(l_rsvEqClosingContractSpecRow.getQuantity());
                
                //1.5.2.4:getTradedProduct( )
                TradedProduct l_tradedProduct = l_contract.getTradedProduct();
                
                //1.5.2.5:get時価(取引銘柄 : EqTypeTradedProduct)
                WEB3EquityProductManager l_productManager =
                    (WEB3EquityProductManager)l_tradingModule.getProductManager();
                
                double l_dblCalcPrice = 
                    l_productManager.getCurrentPrice((EqTypeTradedProduct) l_tradedProduct);
                
                //1.5.2.6:get評価損益（建株諸経費考慮）(計算単価 : double, 数量 : double)
                double l_dblAppraisalProfitOrLoss = l_contract.getAppraisalProfitOrLossExpenses(
                    l_dblCalcPrice, 
                    l_rsvEqClosingContractSpecRow.getQuantity());
                
                //1.5.2.7:信用取引建株明細( )
                WEB3MarginContractUnit l_marginContractUnit = new WEB3MarginContractUnit();
                
                //1.5.2.8:プロパティセット
                //ID      ＝　@建株.建株ID
                l_marginContractUnit.id = WEB3StringTypeUtility.formatNumber(l_contract.getContractId());
                
                //建日      ＝　@建株.建日
                l_marginContractUnit.openDate = l_contract.getOpenDate();
                
                //建単価     ＝　@建株.建単価
                l_marginContractUnit.contractPrice = 
                    WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());
                
                //建株数     ＝　@建株.建株数
                l_marginContractUnit.contractQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_contract.getQuantity());
                
                //建代金     ＝　@get建代金()の戻り値
                l_marginContractUnit.contractExecPrice = 
                    WEB3StringTypeUtility.formatNumber(l_dblContractAmount);
                
                //評価損益    ＝　@get評価損益（建株諸経費考慮）()の戻り値
                l_marginContractUnit.appraisalProfitLoss = 
                    WEB3StringTypeUtility.formatNumber(l_dblAppraisalProfitOrLoss);
                
                //注文株数    ＝　@処理対象の要素.返済注文数量
                l_marginContractUnit.orderQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_rsvEqClosingContractSpecRow.getQuantity());
                
                //内出来株数   ＝　@null
                l_marginContractUnit.partContQuantity = null;
                
                //決済順位    ＝　@処理対象の要素.返済連番
                l_marginContractUnit.settlePriority = 
                    WEB3StringTypeUtility.formatNumber(l_rsvEqClosingContractSpecRow.getClosingSerialNo());
                
                //1.5.2.9:add(arg0 : Object)
                l_lisMarginContractUnits.add(l_marginContractUnit);
            }           
        }
        
        //1.6:toArray( )
        if (l_lisMarginContractUnits.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        WEB3MarginContractUnit[] l_marginContractUnits = 
            new WEB3MarginContractUnit[l_lisMarginContractUnits.size()];
            
        l_lisMarginContractUnits.toArray(l_marginContractUnits);
            
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }

    /**
     * (create建玉明細ByOrder)<BR>
     * 注文に関連した建玉明細（照会用）を配列で取得する。<BR>
     * 指定注文が新規建注文の場合は、nullを返却する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（連続注文）create建玉明細ByOrder」参照。<BR>
     * @@param l_rsvIfoOrderUnit  - (予約注文単位)<BR>
     * 株式予約注文単位オブジェクト<BR>
     * @@return WEB3FuturesOptionsContractUnit[]
     * @@throws WEB3BaseException
     */
    public WEB3FuturesOptionsContractUnit[] createIfoContractUnitByOrder(
        WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoContractUnitByOrder(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg()))
        {
            //処理を終了する。
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //ArrayList( )
        //建玉明細を格納するArrayListを生成する。
        List l_lisFuturesOptionsContractUnits = new ArrayList();

        //is反対売買取引( )
        boolean l_blnIsRerverseTrade = l_rsvIfoOrderUnit.isReversingTrade();

        if (l_blnIsRerverseTrade)
        {
            //get親注文の注文単位
            IfoOrderUnit l_ifoOrderUnit = l_rsvIfoOrderUnit.getParentOrderUnit();

            //create建玉明細
            WEB3FuturesOptionsContractUnit l_ifoContractUnit = createContractUnit(l_ifoOrderUnit);

            //予約注文単位.決済順序区分 == "ランダム"の場合
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();

            if (WEB3ClosingOrderDef.RANDOM.equals(l_rsvIfoOrderUnitRow.getClosingOrder()))
            {
                //返済数量
                l_ifoContractUnit.contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_rsvIfoOrderUnit.getQuantity());
            }

            l_lisFuturesOptionsContractUnits.add(l_ifoContractUnit);
        }
        //反対売買でない場合
        else
        {
            //get先物OP予約建玉返済指定情報一覧
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows =
                l_rsvIfoOrderUnit.getContractsToClose();

            int l_intCnt = 0;
            if (l_rsvIfoClosingContractSpecRows != null && l_rsvIfoClosingContractSpecRows.length > 0)
            {
                l_intCnt = l_rsvIfoClosingContractSpecRows.length;
            }

            for (int i = 0; i < l_intCnt; i++)
            {
                RsvIfoClosingContractSpecRow l_rsvIfoClosingContractSpecRow =
                    l_rsvIfoClosingContractSpecRows[i];

                //先物OP建玉(建玉ＩＤ)
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoPositionManagerImpl l_positionManager =
                    (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();

                WEB3IfoContractImpl l_contract = null;
                try
                {
                    l_contract =
                        (WEB3IfoContractImpl)l_positionManager.getContract(
                            l_rsvIfoClosingContractSpecRow.getContractId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //処理対象の要素.返済注文数量
                double l_dblQuantity = l_rsvIfoClosingContractSpecRow.getQuantity();

                //先物OP建玉.建玉数量 < 処理対象の要素.返済注文数量の場合、nullを返却して終了する。
                if (l_contract.getQuantity() < l_dblQuantity)
                {
                    log.exiting(STR_METHOD_NAME);
                    return null;
                }

                //get建約定代金
                double l_dblContractAmount =
                    l_contract.getContractExecutedAmount(l_dblQuantity);

                WEB3IfoTradedProductImpl l_ifoTradedProduct = (WEB3IfoTradedProductImpl)l_contract.getTradedProduct();

                //get時価情報(補助口座 : 補助口座)
                WEB3IfoProductQuote l_ifoProductQuote = l_ifoTradedProduct.getCurrentInfo(null);

                //get建手数料(数量)
                double l_dblContractCommission = l_contract.getContractCommission(l_dblQuantity);

                //get建手数料消費税()
                double l_ContractCommissionConsumptionTax =
                    l_contract.getContractCommissionConsumptionTax(l_dblQuantity);

                //get評価損益(返済単価 : double, 数量 : double)
                double l_dblEvaluateIncome = l_contract.getEvaluateIncome(
                    l_ifoProductQuote.getCurrentPrice(),
                    l_dblQuantity);

                BigDecimal l_bdEvaluateIncome = new BigDecimal(l_dblEvaluateIncome + "");
                BigDecimal l_bdContractCommission = new BigDecimal(l_dblContractCommission + "");
                BigDecimal l_bdContractCommissionConsumptionTax = new BigDecimal(l_ContractCommissionConsumptionTax + "");

                //建玉明細
                WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit = new WEB3FuturesOptionsContractUnit();

                //プロパティセット
                //ID = 建玉.建玉ID
                l_futuresOptionsContractUnit.id = WEB3StringTypeUtility.formatNumber(l_contract.getContractId());

                //建日 = 建玉.建日
                l_futuresOptionsContractUnit.openDate = l_contract.getOpenDate();

                //建玉数 = 建玉.建玉数量
                l_futuresOptionsContractUnit.contractQuantity =
                    WEB3StringTypeUtility.formatNumber(l_contract.getQuantity());

                //建単価 = 建玉.建単価
                l_futuresOptionsContractUnit.contractPrice =
                    WEB3StringTypeUtility.formatNumber(l_contract.getContractPrice());

                //建約定金額 = get建約定代金()
                l_futuresOptionsContractUnit.contractExecPrice =
                    WEB3StringTypeUtility.formatNumber(l_dblContractAmount);

                //建手数料
                l_futuresOptionsContractUnit.contractCommission = WEB3StringTypeUtility.formatNumber(
                    l_bdContractCommission.add(l_bdContractCommissionConsumptionTax).doubleValue());

                //損益
                l_futuresOptionsContractUnit.income =
                    WEB3StringTypeUtility.formatNumber(l_dblEvaluateIncome);

                //損益(諸経費込)
                l_futuresOptionsContractUnit.incomeCost = WEB3StringTypeUtility.formatNumber(
                    l_bdEvaluateIncome.subtract(l_bdContractCommission.add(
                        l_bdContractCommissionConsumptionTax)).doubleValue());

                //返済数量
                l_futuresOptionsContractUnit.contractOrderQuantity =
                    WEB3StringTypeUtility.formatNumber(l_dblQuantity);

                //返済約定数量
                l_futuresOptionsContractUnit.contractExecQuantity = null;

                //決済順位
                l_futuresOptionsContractUnit.settlePriority =
                    l_rsvIfoClosingContractSpecRow.getClosingSerialNo() + "";

                //立会区分
                IfoContractRow l_ifoContracRow =
                    (IfoContractRow)l_contract.getDataSourceObject();
                l_futuresOptionsContractUnit.sessionType = l_ifoContracRow.getSessionType();

                l_lisFuturesOptionsContractUnits.add(l_futuresOptionsContractUnit);
            }
        }

        if (l_lisFuturesOptionsContractUnits.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3FuturesOptionsContractUnit[] l_futuresOptionsContractUnits =
            new WEB3FuturesOptionsContractUnit[l_lisFuturesOptionsContractUnits.size()];
        l_lisFuturesOptionsContractUnits.toArray(l_futuresOptionsContractUnits);

        log.exiting(STR_METHOD_NAME);
        return l_futuresOptionsContractUnits;
    }

    /**
     * (create株式注文単位)<BR>
     * 引数の予約注文単位より株式注文単位オブジェクト（仮想）を<BR>
     * 生成する。<BR>
     * <BR>
     * ※予約注文の訂正時に使用する。<BR>
     * <BR>
     * １）　@株式注文単位Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）　@予約注文単位.getDataSourceObject()から<BR>
     * 　@　@　@予約注文単位Rowを生成する。<BR>
     * <BR>
     * ３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@注文単位ID = -1（予約注文未発注の状態を表す。）<BR>
     * 　@口座ID = 予約注文単位の同項目<BR>
     * 　@補助口座ID = 予約注文単位の同項目<BR>
     * 　@部店ID = 予約注文単位の同項目<BR>
     * 　@取引者ID = 予約注文単位の同項目<BR>
     * 　@注文ID = 予約注文単位の同項目<BR>
     * 　@注文種別 = 予約注文単位の同項目<BR>
     * 　@注文カテゴリ = 予約注文単位の同項目<BR>
     * 　@注文履歴最終通番 = 予約注文単位の同項目<BR>
     * 　@約定最終通番 = 0<BR>
     * 　@銘柄タイプ = 予約注文単位の同項目<BR>
     * 　@市場ID = 予約注文単位の同項目<BR>
     * 　@注文数量 = 予約注文単位の同項目<BR>
     * 　@指値 = 予約注文単位の同項目<BR>
     * 　@執行条件 = EqTypeExecutionConditionType.条件なし<BR>
     * 　@値段条件 = "条件なし"<BR>
     * 　@発注条件 = "条件なし"<BR>
     * 　@受渡日 = 引数の予約注文単位.get取引銘柄().受渡日<BR>
     * 　@注文失効日付 = 予約注文単位の同項目<BR>
     * 　@注文状態 = 予約注文単位の同項目<BR>
     * 　@注文有効状態 = 予約注文単位の同項目<BR>
     * 　@失効区分 = 予約注文単位の同項目<BR>
     * 　@税区分 = 予約注文単位の同項目<BR>
     * 　@税区分（現引現渡） = 予約注文単位の同項目<BR>
     * 　@弁済区分 = 予約注文単位の同項目<BR>
     * 　@弁済期限値 = 予約注文単位の同項目<BR>
     *   弁済区分(SONAR) = (*1)
     * 　@　@　@　@　@　@（部店市場弁済別）取扱条件Row.弁済区分(SONAR)
     * 　@発注日 = 予約注文単位の同項目<BR>
     * 　@銘柄ID = 予約注文単位の同項目<BR>
     * 　@注文数量タイプ = QuantityTypeEnum.数量<BR>
     * 　@初回注文の注文チャネル = 予約注文単位の同項目<BR>
     * 　@受注日時 = 予約注文単位の同項目<BR>
     * 　@注文単価 = 予約注文単位の同項目<BR>
     * 　@概算受渡代金 = 予約注文単位の同項目<BR>
     * 　@譲渡益金額 = 予約注文単位の同項目<BR>
     * 　@譲渡益税額 = 予約注文単位の同項目<BR>
     * 　@取引コード(SONAR) = (*2)<BR>
     *   市場コード(SONAR) = (*3)市場Row.市場コード(SONAR)
     * 　@手数料商品コード = (*4)<BR>
     * 　@注文経路区分 = 予約注文単位の同項目<BR>
     * 　@決済順序区分 = 予約注文単位の同項目<BR>
     * 　@初回注文の注文単位ID = 予約注文単位の同項目<BR>
     * 　@作成日付 = 予約注文単位の同項目<BR>
     * 　@更新日付 = 予約注文単位の同項目<BR>
     * <BR>
     * <BR>
     * (*1)弁済区分(SONAR)<BR>
     * 　@予約注文単位.弁済区分≠nullの場合のみ以下処理を行う。<BR>
     * 　@上記以外、nullをセット。<BR>
     * <BR>
     * 　@①@．部店オブジェクトを取得する。<BR>
     * 　@　@　@拡張アカウントマネージャ.get部店()をコールする。<BR>
     * 　@　@　@　@　@[get部店()に設定する引数]<BR>
     * 　@　@　@　@　@arg0：　@注文データ.get部店Id()の戻り値<BR>
     * <BR>
     * 　@②．証券会社オブジェクトを取得する。<BR>
     * 　@　@　@get部店()の戻り値.get証券会社()をコールする。<BR>
     * <BR>
     * 　@③．拡張金融オブジェクトマネージャ.get市場()をコールする。<BR>
     * 　@　@　@　@[get市場()に設定する引数]<BR>
     * 　@　@　@　@予約注文単位Row.get市場Id()<BR>
     * <BR>
     * 　@④．（部店市場弁済別）取扱条件()をコールする。<BR>
     * 　@　@　@　@　@[（部店市場弁済別）取扱条件()に設定する引数]<BR>
     * 　@　@　@　@　@証券会社コード：　@get証券会社().get証券会社コード<BR>
     * 　@　@　@　@　@部店コード：　@get部店().get部店コード<BR>
     * 　@　@　@　@　@市場コード：　@get市場().get市場コード<BR>
     * 　@　@　@　@　@信用取引区分：　@予約注文単位.弁済区分<BR>
     * 　@　@　@　@　@弁済期限値：　@予約注文単位.弁済期限値<BR>
     * 　@⑤．④で取得した<BR>
     * 　@　@（部店市場弁済別）取扱条件オブジェクト.getDataSourceObject()から<BR>
     * 　@　@（部店市場弁済別）取扱条件Rowを生成する。<BR>
     * <BR>
     * (*2)取引コード(SONAR)<BR>
     * 　@・予約注文単位.注文カテゴリ＝現物注文の場合、<BR>
     * 　@　@　@　@取引コード（SONAR）.普通株式をセット<BR>
     * 　@・予約注文単位.注文カテゴリ＝新規建注文の場合、<BR>
     * 　@　@　@　@取引コード（SONAR）.信用建をセット<BR>
     * 　@・予約注文単位.注文カテゴリ＝返済注文の場合、<BR>
     * 　@　@　@　@取引コード（SONAR）.信用返済をセット<BR>
     * 　@・予約注文単位.注文カテゴリ＝現引・現渡注文の場合、<BR>
     * 　@　@　@　@取引コード（SONAR）.現引現渡をセット<BR>
     * 　@※注文カテゴリが上記以外の場合は、例外をthrowする。<BR>
     * <BR>
     * (*3)市場コード(SONAR)<BR>
     * 　@(*1)③で取得した市場オブジェクト.getDataSourceObject()から<BR>
     * 　@市場Rowを生成する。<BR>
     * <BR>
     * (*4)手数料商品コード<BR>
     * 　@予約注文単位.注文カテゴリが"現引・現渡注文"の場合、<BR>
     * 　@　@　@nullをセットする。<BR>
     * 　@上記以外の場合、<BR>
     * 　@　@　@手数料商品コード."上場株式"をセットする。<BR>
     * <BR>
     * ４）　@株式注文単位オブジェクトを作成する。<BR>
     * 　@拡張株式注文マネージャ.toOrderUnit(プロパティセットしたParams)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ５）　@作成した株式注文単位オブジェクトを返却する。<BR>
     * @@param l_rsvEqOrderUnit - (予約注文単位)<BR>
     * 株式予約注文単位Implオブジェクト。<BR>
     * @@return EqTypeOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 433B9E0C0287
     */
    public EqTypeOrderUnit createEqtypeOrderUnit(WEB3ToSuccEqTypeOrderUnitImpl l_rsvEqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createEqtypeOrderUnit(WEB3ToSuccEqTypeOrderUnitImpl)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_rsvEqOrderUnit == null)
        {
            log.debug("予約注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "予約注文単位 = null。");
        }
        
        //１）　@株式注文単位Paramsインスタンスを生成する。 
        EqtypeOrderUnitParams l_eqOrderUnitParams = new EqtypeOrderUnitParams();
        
        RsvEqOrderUnitRow l_rsvEqOrderUnitRow = 
            (RsvEqOrderUnitRow) l_rsvEqOrderUnit.getDataSourceObject();
            
        //　@部店オブジェクトを取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
                
        Branch l_branch = null;                
        try
        {
            l_branch = l_accountManager.getBranch(l_rsvEqOrderUnit.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        //　@証券会社オブジェクトを取得。 
        //　@getBranch()の戻り値.getInstitution()をコールする。 
        Institution l_institution = l_branch.getInstitution();
        
        long l_lngMarketId = l_rsvEqOrderUnitRow.getMarketId();        
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = 
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_lngMarketId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //　@生成したインスタンスに以下のプロパティをセットする。 
        //  注文単位ID = -1（予約注文未発注の状態を表す。） 
        l_eqOrderUnitParams.setOrderUnitId(-1);
        
        //  口座ID = 予約注文単位の同項目 
        l_eqOrderUnitParams.setAccountId(l_rsvEqOrderUnit.getAccountId());
        
        //  補助口座ID = 予約注文単位の同項目 
        l_eqOrderUnitParams.setSubAccountId(l_rsvEqOrderUnit.getSubAccountId());
        
        //  部店ID = 予約注文単位の同項目 
        l_eqOrderUnitParams.setBranchId(l_rsvEqOrderUnit.getBranchId());
        
        //  取引者ID = 予約注文単位の同項目 
        if (!l_rsvEqOrderUnitRow.getTraderIdIsNull())
        {
            l_eqOrderUnitParams.setTraderId(l_rsvEqOrderUnit.getTraderId());
        }
        
        //  注文ID = 予約注文単位の同項目 
        l_eqOrderUnitParams.setOrderId(l_rsvEqOrderUnit.getOrderId());
        
        //  注文種別 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setOrderType(l_rsvEqOrderUnit.getOrderType());
        
        //  注文カテゴリ = 予約注文単位の同項目 
        l_eqOrderUnitParams.setOrderCateg(l_rsvEqOrderUnit.getOrderCateg());
        
        //  注文履歴最終通番 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setLastOrderActionSerialNo(
            l_rsvEqOrderUnitRow.getLastOrderActionSerialNo());
        
        //  約定最終通番 = 0 
        l_eqOrderUnitParams.setLastExecutionSerialNo(0);
        
        //  銘柄タイプ = 予約注文単位の同項目 
        l_eqOrderUnitParams.setProductType(l_rsvEqOrderUnit.getProductType());
        
        //  市場ID = 予約注文単位の同項目 
        if (!l_rsvEqOrderUnitRow.getMarketIdIsNull())
        {
            l_eqOrderUnitParams.setMarketId(l_rsvEqOrderUnitRow.getMarketId());
        }
        
        //  注文数量 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setQuantity(l_rsvEqOrderUnit.getQuantity());
        
        //  指値 = 予約注文単位の同項目 
        if (!l_rsvEqOrderUnitRow.getLimitPriceIsNull())
        {
            l_eqOrderUnitParams.setLimitPrice(l_rsvEqOrderUnit.getLimitPrice());
        }
        
        //  執行条件 = EqTypeExecutionConditionType.条件なし 
        l_eqOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
        
        //  値段条件 = "条件なし" 
        l_eqOrderUnitParams.setPriceConditionType(WEB3PriceConditionDef.DEFAULT);
        
        //  発注条件 = "条件なし" 
        l_eqOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);
        
        //  受渡日 = 引数の予約注文単位.get取引銘柄().受渡日
        TradedProduct l_tradedProduct = l_rsvEqOrderUnit.getTradedProduct();
        if (l_tradedProduct == null)
        {
            log.debug("取引銘柄テーブルに該当するデータがありません。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "取引銘柄テーブルに該当するデータがありません。");
        }
        
        Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        l_eqOrderUnitParams.setDeliveryDate(new Timestamp(l_datDeliveryDate.getTime()));       
        
        //  注文失効日付 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setExpirationDate(l_rsvEqOrderUnitRow.getExpirationDate());
        
        //  注文状態 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setOrderStatus(l_rsvEqOrderUnit.getOrderStatus());
        
        //  注文有効状態 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setOrderOpenStatus(l_rsvEqOrderUnit.getOrderOpenStatus());
        
        //  失効区分 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setExpirationStatus(l_rsvEqOrderUnit.getExpirationStatus());
        
        //  税区分 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setTaxType(l_rsvEqOrderUnit.getTaxType());
        
        //  税区分（現引現渡） = 予約注文単位の同項目 
        l_eqOrderUnitParams.setSwapTaxType(l_rsvEqOrderUnitRow.getSwapTaxType());
        
        //  弁済区分 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setRepaymentType(l_rsvEqOrderUnitRow.getRepaymentType());
        
        //  弁済期限値 = 予約注文単位の同項目 
        if (!l_rsvEqOrderUnitRow.getRepaymentNumIsNull())
        {
            l_eqOrderUnitParams.setRepaymentNum(l_rsvEqOrderUnitRow.getRepaymentNum());
        }
        
        //　@弁済区分(SONAR) = （部店市場弁済別）取扱条件Row.弁済区分(SONAR)
        //　@予約注文単位.弁済区分≠nullの場合のみ以下処理を行う。
        l_eqOrderUnitParams.setSonarRepaymentType(null);
        if (l_rsvEqOrderUnitRow.getRepaymentType() != null)
        {
            //  （部店市場弁済別）取扱条件()をコールする。
            //　@　@　@[（部店市場弁済別）取扱条件()に設定する引数]
            //　@　@　@証券会社コード：　@get証券会社().get証券会社コード
            //　@　@　@部店コード：　@get部店().get部店コード
            //　@　@　@市場コード：　@get市場().get市場コード
            //　@　@　@信用取引区分：　@予約注文単位.弁済区分　@　@　@　@　@
            //　@　@　@弁済期限値：　@予約注文単位.弁済期限値 
            WEB3GentradeBranchMarketRepayDealtCond l_genBranchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_institution.getInstitutionCode(),
                    l_branch.getBranchCode(),
                    l_market.getMarketCode(),
                    l_rsvEqOrderUnitRow.getRepaymentType(),
                    l_rsvEqOrderUnitRow.getRepaymentNum()
                    );
            BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)
                l_genBranchMarketRepayDealtCond.getDataSourceObject();

            l_eqOrderUnitParams.setSonarRepaymentType(
                l_branchMarketRepayDealtCondRow.getSonarRepaymentType());
        }   
        
        //  発注日 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setBizDate(l_rsvEqOrderUnitRow.getBizDate());
        
        //  銘柄ID = 予約注文単位の同項目 
        l_eqOrderUnitParams.setProductId(l_rsvEqOrderUnit.getProduct().getProductId());
        
        //  注文数量タイプ = QuantityTypeEnum.数量
        l_eqOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
         
        //  初回注文の注文チャネル = 予約注文単位の同項目 ]
        l_eqOrderUnitParams.setOrderChanel(l_rsvEqOrderUnitRow.getOrderChanel());
        
        //  受注日時 = 予約注文単位の同項目
        l_eqOrderUnitParams.setReceivedDateTime(l_rsvEqOrderUnitRow.getReceivedDateTime());
         
        //  注文単価 = 予約注文単位の同項目
        if (!l_rsvEqOrderUnitRow.getPriceIsNull())
        {
            l_eqOrderUnitParams.setPrice(l_rsvEqOrderUnitRow.getPrice());
        }        
         
        //  概算受渡代金 = 予約注文単位の同項目
        if (!l_rsvEqOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_eqOrderUnitParams.setEstimatedPrice(l_rsvEqOrderUnitRow.getEstimatedPrice());
        }  
         
        //  譲渡益金額 = 予約注文単位の同項目 
        if (!l_rsvEqOrderUnitRow.getCapitalGainIsNull())
        {
            l_eqOrderUnitParams.setCapitalGain(l_rsvEqOrderUnitRow.getCapitalGain());
        }
        
        //  譲渡益税額 = 予約注文単位の同項目 
        if (!l_rsvEqOrderUnitRow.getCapitalGainTaxIsNull())
        {
            l_eqOrderUnitParams.setCapitalGainTax(l_rsvEqOrderUnitRow.getCapitalGainTax());
        }
        
        //　@取引コード（SONAR）
        String l_strSonarTradedCode;
        if(OrderCategEnum.ASSET.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.MARKET_TRADING;
        }
        else if(OrderCategEnum.OPEN_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
        }
        else if(OrderCategEnum.CLOSE_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
        }
        else if(OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_strSonarTradedCode = 
                WEB3TransactionTypeSONARDef.SWAP_CONTRACT;
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文カテゴリ = " + l_rsvEqOrderUnit.getOrderCateg());
        }
        l_eqOrderUnitParams.setSonarTradedCode(l_strSonarTradedCode);
        
        //　@市場コード(SONAR)
        MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
        l_eqOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());
        
        //　@手数料商品コード        
        if (OrderCategEnum.SWAP_MARGIN.equals(l_rsvEqOrderUnit.getOrderCateg()))
        {
            l_eqOrderUnitParams.setCommProductCode(null);
        }
        else
        {
            l_eqOrderUnitParams.setCommProductCode(
                WEB3CommisionProductCodeDef.LISTING_STOCK);
        }
        
        //  注文経路区分 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setOrderRootDiv(l_rsvEqOrderUnitRow.getOrderRootDiv());
        
        //  決済順序区分 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setClosingOrderType(l_rsvEqOrderUnitRow.getClosingOrderType());
        
        //  初回注文の注文単位ID = 予約注文単位の同項目 
        if (!l_rsvEqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_eqOrderUnitParams.setFirstOrderUnitId(l_rsvEqOrderUnitRow.getFirstOrderUnitId());
        }        
        
        //  作成日付 = 予約注文単位の同項目 
        l_eqOrderUnitParams.setCreatedTimestamp(l_rsvEqOrderUnitRow.getCreatedTimestamp());
        
        //  更新日付 = 予約注文単位の同項目
        l_eqOrderUnitParams.setLastUpdatedTimestamp(l_rsvEqOrderUnitRow.getLastUpdatedTimestamp());
         
        //４）　@株式注文単位オブジェクトを作成する。 
        //  拡張株式注文マネージャ.toOrderUnit(プロパティセットしたParams)を 
        //  コールする。         
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        EqTypeOrderUnit l_eqOrderUnit = (EqTypeOrderUnit) l_orderManager.toOrderUnit(l_eqOrderUnitParams);
        
        //５）　@作成した株式注文単位オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_eqOrderUnit;
    }

    /**
     * (create先物OP注文単位)<BR>
     * 引数の予約注文単位より先物OP注文単位オブジェクト（仮想）を<BR>
     * 生成する。<BR>
     * <BR>
     * ※予約注文の訂正時に使用する。<BR>
     * <BR>
     * １）　@先物OP注文単位Paramsインスタンスを生成する。<BR>
     * <BR>
     * ２）　@予約注文単位.getDataSourceObject()から<BR>
     * 　@　@　@予約注文単位Rowを生成する。<BR>
     * <BR>
     * ３）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@注文単位ID = -1（予約注文未発注の状態を表す。）<BR>
     * 　@口座ID = 予約注文単位の同項目<BR>
     * 　@補助口座ID = 予約注文単位の同項目<BR>
     * 　@部店ID = 予約注文単位の同項目<BR>
     * 　@取引者ID = 予約注文単位の同項目<BR>
     * 　@注文ID = 予約注文単位の同項目<BR>
     * 　@注文種別 = 予約注文単位の同項目<BR>
     * 　@注文カテゴリ = 予約注文単位の同項目<BR>
     * 　@注文履歴最終通番 = 予約注文単位の同項目<BR>
     * 　@約定最終通番 = 0<BR>
     * 　@銘柄タイプ = 予約注文単位の同項目<BR>
     * 　@先物／オプション区分 = 予約注文単位の同項目<BR>
     * 　@市場ID = 予約注文単位の同項目<BR>
     * 　@注文数量 = 予約注文単位の同項目<BR>
     * 　@指値 = 予約注文単位の同項目<BR>
     * 　@執行条件 = IfoOrderExecutionConditionType.条件なし<BR>
     * 　@発注条件 = "条件なし"<BR>
     * 　@受渡日 = 引数の予約注文単位.get取引銘柄.get受渡日()<BR>
     * 　@注文失効日付 = 予約注文単位の同項目<BR>
     * 　@注文状態 = 予約注文単位の同項目<BR>
     * 　@注文有効状態 = 予約注文単位の同項目<BR>
     * 　@失効区分 = 予約注文単位の同項目<BR>
     * 　@税区分 = 予約注文単位の同項目<BR>
     * 　@発注日 = 予約注文単位の同項目<BR>
     * 　@銘柄ID = 予約注文単位の同項目<BR>
     * 　@初回注文の注文チャネル = 予約注文単位の同項目<BR>
     * 　@受注日時 = 予約注文単位の同項目<BR>
     * 　@扱者コード(SONAR) = 予約注文単位の同項目<BR>
     * 　@注文単価 = 予約注文単位の同項目<BR>
     * 　@概算受渡代金 = 予約注文単位の同項目<BR>
     * 　@取引コード(SONAR) = (*1)<BR>
     * 　@市場コード(SONAR) = (*2)市場Row.市場コード(SONAR)<BR>
     * 　@手数料商品コード = (*3)<BR>
     * 　@注文経路区分 = 予約注文単位の同項目<BR>
     * 　@決済順序 = 予約注文単位の同項目<BR>
     * 　@初回注文の注文単位ID = 予約注文単位の同項目<BR>
     * 　@作成日付 = 予約注文単位の同項目<BR>
     * 　@更新日付 = 予約注文単位の同項目<BR>
     * 　@夕場前繰越対象フラグ = 予約注文単位の同項目<BR>
     * 　@立会区分 = 予約注文単位の同項目<BR>
     * 　@日計り区分 = null<BR>
     * <BR>
     * <BR>
     * 　@(*1)取引コード(SONAR)<BR>
     * 　@　@・予約注文単位.注文カテゴリ＝先物新規建注文、又はOP新規建注文の場合、<BR>
     * 　@　@　@　@　@取引コード（SONAR）.信用建をセット<BR>
     * 　@　@・予約注文単位.注文カテゴリ＝先物返済注文、又はOP返済注文<BR>
     * 　@　@　@　@　@取引コード（SONAR）.信用返済をセット<BR>
     * 　@　@※注文カテゴリが上記以外の場合は、例外をthrowする。<BR>
     * 　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag: BUSINESS_ERROR_00653 <BR>
     * <BR>
     * 　@(*2)市場コード(SONAR)<BR>
     * 　@　@拡張金融オブジェクトマネージャ.get市場()をコールする。<BR>
     * 　@　@　@[get市場()に設定する引数]<BR>
     * 　@　@　@予約注文単位Row.get市場Id()<BR>
     * <BR>
     * 　@　@上記で取得した市場オブジェクト.getDataSourceObject()から<BR>
     * 　@　@市場Rowを生成する。<BR>
     * <BR>
     * 　@(*3)手数料商品コード<BR>
     * 　@　@予約注文単位.先物／オプション区分＝先物の場合、<BR>
     * 　@　@　@　@手数料商品コード.株価指数先物をセットする。<BR>
     * 　@　@上記以外の場合、<BR>
     * 　@　@　@　@手数料商品コード.株価指数OPをセットする。<BR>
     * <BR>
     * <BR>
     * ４）　@先物OP注文単位オブジェクトを作成する。<BR>
     * 　@OP注文マネージャ.toOrderUnit(プロパティセットしたParams)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * ５）　@作成した先物OP注文単位オブジェクトを返却する。<BR>
     * @@param l_rsvIfoOrderUnit  - (予約注文単位)<BR>
     * 先物OP予約注文単位Implオブジェクト。<BR>
     * @@return IfoOrderUnit
     * @@throws WEB3BaseException
     */
    public IfoOrderUnit createIfoOrderUnit(
        WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createIfoOrderUnit(WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_rsvIfoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）　@先物OP注文単位Paramsインスタンスを生成する。
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();

        //２）　@予約注文単位.getDataSourceObject()から
        //　@　@　@予約注文単位Rowを生成する。
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = (RsvIfoOrderUnitRow)l_rsvIfoOrderUnit.getDataSourceObject();

        //３）　@生成したインスタンスに以下のプロパティをセットする。
        //　@注文単位ID = -1（予約注文未発注の状態を表す。）
        l_ifoOrderUnitParams.setOrderUnitId(-1);

        //　@口座ID = 予約注文単位の同項目
        l_ifoOrderUnitParams.setAccountId(l_rsvIfoOrderUnit.getAccountId());

        //　@補助口座ID = 予約注文単位の同項目
        l_ifoOrderUnitParams.setSubAccountId(l_rsvIfoOrderUnit.getSubAccountId());

        //　@部店ID = 予約注文単位の同項目
        l_ifoOrderUnitParams.setBranchId(l_rsvIfoOrderUnit.getBranchId());

        //　@取引者ID = 予約注文単位の同項目
        if (!l_rsvIfoOrderUnitRow.getTraderIdIsNull())
        {
            l_ifoOrderUnitParams.setTraderId(l_rsvIfoOrderUnit.getTraderId());
        }

        //　@注文ID = 予約注文単位の同項目
        l_ifoOrderUnitParams.setOrderId(l_rsvIfoOrderUnit.getOrderId());

        //　@注文種別 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setOrderType(l_rsvIfoOrderUnit.getOrderType());

        //　@注文カテゴリ = 予約注文単位の同項目
        l_ifoOrderUnitParams.setOrderCateg(l_rsvIfoOrderUnit.getOrderCateg());

        //　@注文履歴最終通番 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(
            l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());

        //　@約定最終通番 = 0
        l_ifoOrderUnitParams.setLastExecutionSerialNo(0);

        //　@銘柄タイプ = 予約注文単位の同項目
        l_ifoOrderUnitParams.setProductType(l_rsvIfoOrderUnit.getProductType());

        //　@先物／オプション区分 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setFutureOptionDiv(l_rsvIfoOrderUnitRow.getFutureOptionDiv());

        //　@市場ID = 予約注文単位の同項目
        if (!l_rsvIfoOrderUnitRow.getMarketIdIsNull())
        {
            l_ifoOrderUnitParams.setMarketId(l_rsvIfoOrderUnitRow.getMarketId());
        }
        
        //　@注文数量 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setQuantity(l_rsvIfoOrderUnit.getQuantity());

        //　@指値 = 予約注文単位の同項目
        if (!l_rsvIfoOrderUnitRow.getLimitPriceIsNull())
        {
            l_ifoOrderUnitParams.setLimitPrice(l_rsvIfoOrderUnit.getLimitPrice());
        }

        //　@執行条件 = IfoOrderExecutionConditionType.条件なし
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);

        //　@発注条件 = "条件なし"
        l_ifoOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.DEFAULT);

        //　@受渡日 = 引数の予約注文単位.get取引銘柄.get受渡日()
        TradedProduct l_tradedProduct = l_rsvIfoOrderUnit.getTradedProduct();
        if (l_tradedProduct == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません。");
        }
        Date l_datDeliveryDate = l_tradedProduct.getDailyDeliveryDate();
        l_ifoOrderUnitParams.setDeliveryDate(new Timestamp(l_datDeliveryDate.getTime()));

        //　@注文失効日付 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setExpirationDate(l_rsvIfoOrderUnitRow.getExpirationDate());

        //　@注文状態 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setOrderStatus(l_rsvIfoOrderUnit.getOrderStatus());

        //　@注文有効状態 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setOrderOpenStatus(l_rsvIfoOrderUnit.getOrderOpenStatus());

        //　@失効区分 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setExpirationStatus(l_rsvIfoOrderUnit.getExpirationStatus());

        //　@税区分 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setTaxType(l_rsvIfoOrderUnit.getTaxType());

        //　@発注日 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setBizDate(l_rsvIfoOrderUnitRow.getBizDate());

        //　@銘柄ID = 予約注文単位の同項目
        l_ifoOrderUnitParams.setProductId(l_rsvIfoOrderUnit.getProduct().getProductId());

        //　@初回注文の注文チャネル = 予約注文単位の同項目
        l_ifoOrderUnitParams.setOrderChanel(l_rsvIfoOrderUnitRow.getOrderChanel());

        //　@受注日時 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setReceivedDateTime(l_rsvIfoOrderUnitRow.getReceivedDateTime());

        //　@扱者コード(SONAR) = 予約注文単位の同項目
        l_ifoOrderUnitParams.setSonarTraderCode(l_rsvIfoOrderUnitRow.getSonarTraderCode());

        //　@注文単価 = 予約注文単位の同項目
        if (!l_rsvIfoOrderUnitRow.getPriceIsNull())
        {
            l_ifoOrderUnitParams.setPrice(l_rsvIfoOrderUnitRow.getPrice());
        }

        //　@概算受渡代金 = 予約注文単位の同項目
        if (!l_rsvIfoOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_ifoOrderUnitParams.setEstimatedPrice(l_rsvIfoOrderUnitRow.getEstimatedPrice());
        }
        //　@取引コード(SONAR) = (*1)
        String l_strSonarTradedCode = null;

        if (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_rsvIfoOrderUnit.getOrderCateg()))
        {
            //予約注文単位.注文カテゴリ＝先物新規建注文、又はOP新規建注文の場合
            //取引コード（SONAR）.信用建をセット
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
        }
        else if (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_rsvIfoOrderUnit.getOrderCateg())
            || OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_rsvIfoOrderUnit.getOrderCateg()))
        {
            //予約注文単位.注文カテゴリ＝先物返済注文、又はOP返済注文の場合
            //取引コード（SONAR）.信用返済をセット
            l_strSonarTradedCode = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
        }
        else
        {
            //注文カテゴリが上記以外の場合は、例外をthrowする
            log.debug("注文カテゴリの値が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00653,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文カテゴリの値が不正です。");
        }
        l_ifoOrderUnitParams.setSonarTradedCode(l_strSonarTradedCode);

        //　@市場コード(SONAR) = (*2)市場Row.市場コード(SONAR)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        WEB3GentradeMarket l_market = null;
        try
        {
            l_market =
                (WEB3GentradeMarket)l_finObjManager.getMarket(l_rsvIfoOrderUnitRow.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        MarketRow l_marketRow = (MarketRow)l_market.getDataSourceObject();
        l_ifoOrderUnitParams.setSonarMarketCode(l_marketRow.getSonarMarketCode());

        //　@手数料商品コード = (*3)
        if (WEB3FuturesOptionDivDef.FUTURES.equals(l_rsvIfoOrderUnitRow.getFutureOptionDiv()))
        {
            //予約注文単位.先物／オプション区分＝先物の場合
            l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
        }
        else
        {
            //OPの場合
            l_ifoOrderUnitParams.setCommProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
        }

        //　@注文経路区分 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setOrderRootDiv(l_rsvIfoOrderUnitRow.getOrderRootDiv());
        
        //　@決済順序 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setClosingOrder(l_rsvIfoOrderUnitRow.getClosingOrder());

        //　@初回注文の注文単位ID = 予約注文単位の同項目
        if (!l_rsvIfoOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_ifoOrderUnitParams.setFirstOrderUnitId(l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
        }

        //　@作成日付 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setCreatedTimestamp(l_rsvIfoOrderUnitRow.getCreatedTimestamp());
        //　@更新日付 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp());

        //　@夕場前繰越対象フラグ = 予約注文単位の同項目
        l_ifoOrderUnitParams.setEveningSessionCarryoverFlag(
            l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag());

        //　@立会区分 = 予約注文単位の同項目
        l_ifoOrderUnitParams.setSessionType(l_rsvIfoOrderUnitRow.getSessionType());

        //　@日計り区分 = null
        l_ifoOrderUnitParams.setDayTradeType(null);

        //４）　@先物OP注文単位オブジェクトを作成する。
        //　@OP注文マネージャ.toOrderUnit(プロパティセットしたParams)を
        //　@コールする。
        WEB3OptionOrderManagerImpl l_opOrderManager =
            (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

        IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_opOrderManager.toOrderUnit(l_ifoOrderUnitParams);

        //５）　@作成した先物OP注文単位オブジェクトを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_ifoOrderUnit;
    }

    /**
     * (create建株明細)<BR>
     * 引数の注文単位より信用取引建株明細を作成する。<BR>
     * <BR>
     * ※反対売買時に使用する。<BR>
     * <BR>
     * １）　@以下の手順にて信用取引建株明細を作成する。<BR>
     * 　@１－１）　@信用取引建株明細インスタンスを生成する。<BR>
     * <BR>
     * 　@１－２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@※以下の項目以外はnullをセットする。<BR>
     * 　@　@建日 = 注文単位.発注日<BR>
     * 　@　@建株数 = 注文単位.注文数量<BR>
     * <BR>
     * 　@１－３）　@プロパティセットしたインスタンスを返却する。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return WEB3MarginContractUnit
     * @@throws WEB3BaseException
     * @@roseuid 43424F710266
     */
    public WEB3MarginContractUnit createMarginContractUnit(EqTypeOrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnit(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME); 
        
        if (l_orderUnit == null)
        {
            log.debug("注文単位 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文単位 = null。");
        }
        
        //１）　@以下の手順にて信用取引建株明細を作成する。
        //１－１）　@信用取引建株明細インスタンスを生成する。
        WEB3MarginContractUnit l_marginContractUnit = new WEB3MarginContractUnit();
        
        //１－２）　@生成したインスタンスに以下のプロパティをセットする。
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        l_marginContractUnit.openDate = WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        l_marginContractUnit.contractQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        
        //１－３）　@プロパティセットしたインスタンスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnit;
    }

    /**
     * (create建玉明細)<BR>
     * 引数の注文単位より建玉明細を作成する。<BR>
     * <BR>
     * ※反対売買時に使用する。<BR>
     * <BR>
     * １）　@以下の手順にて建玉明細を作成する。<BR>
     * 　@１－１）　@建玉明細インスタンスを生成する。<BR>
     * <BR>
     * 　@１－２）　@生成したインスタンスに以下のプロパティをセットする。<BR>
     * 　@　@　@　@　@　@※以下の項目以外はnullをセットする。<BR>
     * 　@　@建日 = 注文単位.発注日<BR>
     * 　@　@建玉数 = 注文単位.注文数量<BR>
     * <BR>
     * 　@１－３）　@プロパティセットしたインスタンスを返却する。<BR>
     * @@param l_ifoOrderUnit  - (注文単位)<BR>
     * 注文単位オブジェクト。<BR>
     * @@return WEB3FuturesOptionsContractUnit
     * @@throws WEB3BaseException
     */
    public WEB3FuturesOptionsContractUnit createContractUnit(
        IfoOrderUnit l_ifoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnit(IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ifoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //建玉明細インスタンスを生成する
        WEB3FuturesOptionsContractUnit l_futuresOptionsContractUnit =
            new WEB3FuturesOptionsContractUnit();

        //建日 = 注文単位.発注日
        IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_ifoOrderUnit.getDataSourceObject();
        l_futuresOptionsContractUnit.openDate =
            WEB3DateUtility.getDate(l_ifoOrderUnitRow.getBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        //建玉数 = 注文単位.注文数量
        l_futuresOptionsContractUnit.contractQuantity =
            WEB3StringTypeUtility.formatNumber(l_ifoOrderUnit.getQuantity());

        log.exiting(STR_METHOD_NAME);
        return l_futuresOptionsContractUnit;
    }

    /**
     * (is余力チェック実施部店)<BR>
     * 連続注文発注において、余力チェックを実施する部店かどうかを返却する。<BR>
     * <BR>
     * １）　@部店プリファ@レンステーブルを以下の条件で検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@　@部店ID = 引数の補助口座.部店ID And <BR>
     * 　@　@プリファ@レンス名 = プリファ@レンス名.余力チェック実施 And<BR>
     * 　@　@プリファ@レンス名の連番 = 1<BR>
     * <BR>
     * ２）　@検索結果.プリファ@レンスの値 == "余力チェック要"の場合は、trueを返却する。<BR>
     * 　@　@　@以外、falseを返却する。<BR>
     * <BR>
     * 　@　@※検索結果が取得できなかった場合は、<BR>
     * 　@　@※「余力チェックを実施しない」部店と判定し、falseを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCheckTradingPowerBranch(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isCheckTradingPower(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME); 
        
        try
        {
            SubAccountRow l_subAccountRow =
                (SubAccountRow)l_subAccount.getDataSourceObject();
            //１）　@DB検索
            BranchPreferencesRow l_branchReferencesRow = 
                BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(
                    l_subAccountRow.getBranchId(),
                    WEB3BranchPreferencesNameDef.TRIGGERORDER_SUCORDER_CHECKTP,
                    1);

            if ((l_branchReferencesRow != null) &&
                WEB3SucOrderTradingPowerCheckDef.CHECK_TRADING_POWER.equals(
                l_branchReferencesRow.getValue()))
            {
                log.debug("余力チェック要の部店");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.debug("余力チェックなしの部店");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
    
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
        
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (is繰越対象予約注文)<BR>
     * 繰越対象注文であるか判定する。<BR>
     * <BR>
     * １）　@取引時間管理.get立会区分()をコールする。<BR>
     * <BR>
     * ２）　@パラメータ.注文単位 == 先物OP予約注文単位Implかつ、<BR>
     * 　@　@　@　@夕場前注文繰越（get立会区分() == ”夕場”）の場合、<BR>
     * 　@　@　@日中登録した当日限り注文（*1）は繰越対象外注文と判断するため、<BR>
     * 　@　@　@falseを返却する。<BR>
     * <BR>
     * 　@　@　@（*1）日中登録した当日限り注文<BR>
     * 　@　@　@　@パラメータ.先物OP予約注文単位Impl.get注文期限区分() == ”当日限り”かつ、<BR>
     * 　@　@　@　@パラメータ.先物OP予約注文単位Impl.立会区分 == null<BR>
     * <BR>
     * ３）　@上記以外の場合、<BR>
     * 　@　@　@引数.注文単位.注文失効日 <= 業務日付（*2） に該当する注文は、<BR>
     * 　@　@　@繰越対象外注文と判断するため、 false を返却する。<BR>
     * <BR>
     * 　@　@　@（*2） 業務日付は、GtlUtils.getTradingSystem().getBizDate()で取得。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 注文単位<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isCarryoverReserveIfoOrderUnit(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCarryoverReserveIfoOrderUnit(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@取引時間管理.get立会区分()をコールする。
        String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();

        //２）　@パラメータ.注文単位 == 先物OP予約注文単位Implかつ、
        //夕場前注文繰越（get立会区分() == ”夕場”）の場合、
        if ((l_orderUnit instanceof WEB3ToSuccIfoOrderUnitImpl)
            && WEB3SessionTypeDef.EVENING_SESSION.equals(l_strSessionType))
        {
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl = (WEB3ToSuccIfoOrderUnitImpl)l_orderUnit;
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
                (RsvIfoOrderUnitRow)l_ifoOrderUnitImpl.getDataSourceObject();

            //　@パラメータ.先物OP予約注文単位Impl.get注文期限区分() == ”当日限り”かつ
            //パラメータ.先物OP予約注文単位Impl.立会区分 == null
            if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_ifoOrderUnitImpl.getExpirationDateType())
                && l_rsvIfoOrderUnitRow.getSessionType() == null)
            {
                //日中登録した当日限り注文（*1）は繰越対象外注文と判断するため、falseを返却する。
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //３）　@上記以外の場合
        //引数.注文単位.注文失効日 <= 業務日付（*2） に該当する注文は、
        //繰越対象外注文と判断するため、 false を返却する。
        else if (WEB3DateUtility.compareToDay(
            l_orderUnit.getExpirationTimestamp(), GtlUtils.getTradingSystem().getBizDate()) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }

    /**
     * (submit先物OP新規建繰越予約注文)<BR>
     * 繰越後の先物OP新規建の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@先物OP予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「注文繰越_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * ２）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.insert予約注文履歴(パラメータ.注文ID)をコールする。<BR>
     * <BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_carryBeforeReserveIfoOrderUnit - (繰越元予約注文単位)<BR>
     * 繰越元の予約注文単位<BR>
     * @@param l_carryAfterParentIfoOrderUnit - (繰越後の親注文単位)<BR>
     * 繰越後の親注文単位<BR>
     * @@throws WEB3BaseException
     */
    public void submitIfoOpenContractCarryReserveOrder(
        long l_lngOrderId,
        WEB3ToSuccIfoOrderUnitImpl l_carryBeforeReserveIfoOrderUnit,
        IfoOrderUnit l_carryAfterParentIfoOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoOpenContractCarryReserveOrder(long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_carryBeforeReserveIfoOrderUnit == null || l_carryAfterParentIfoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_carryBeforeReserveIfoOrderUnit.getDataSourceObject();
        //先物OP予約注文単位テーブルにレコードを登録する。
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        //登録の仕様は、DB更新仕様
        //「注文繰越_先物OP予約注文単位テーブル.xls」を参照
        //注文単位ＩＤ          null
        l_rsvIfoOrderUnitParams.setOrderUnitId(null);

        //注文ＩＤ 取得した注文ID（自動採番）
        l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

        //注文履歴最終通番     1
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

        //注文状態               1:受付済（新規注文）
        l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

        //注文有効状態          1:オープン
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

        //失効区分            1:オープン
        l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

        //発注日
        l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //注文エラー理由コード     0000：正常
        l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //初回注文の注文ＩＤ
        //繰越元予約注文単位.初回注文の注文ＩＤ = null の場合、繰越元予約注文単位.注文ＩＤ
        //それ以外の場合、繰越元予約注文単位の同項目
        if (l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull())
        {
            l_rsvIfoOrderUnitParams.setFirstOrderId(l_rsvIfoOrderUnitRow.getOrderId());
        }

        //発注エラーコード    null
        l_rsvIfoOrderUnitParams.setOrderErrorCode(null);

        //親注文の注文ＩＤ          引数.繰越後の親注文単位.注文ID
        l_rsvIfoOrderUnitParams.setParentOrderId(l_carryAfterParentIfoOrderUnit.getOrderId());

        //親注文の注文単位ＩＤ      引数.繰越後の親注文単位.注文単位ID
        l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_carryAfterParentIfoOrderUnit.getOrderUnitId());

        //立会区分             取引時間管理.get立会区分()
        l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //作成日付             現在日時（GtlUtils.getSystemTimestamp()）
        l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //更新日付             現在日時（GtlUtils.getSystemTimestamp()）
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）　@予約注文履歴を作成する。
        //先物OP予約注文更新サービス.insert予約注文履歴(パラメータ.注文ID)をコールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService) Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (submit先物OP返済繰越予約注文)<BR>
     * 繰越後の先物OP返済の予約注文を新規登録する。<BR>
     * <BR>
     * １）　@先物OP予約注文単位テーブルにレコードを登録する。<BR>
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「注文繰越_先物OP予約注文単位テーブル.xls」を参照。<BR>
     * <BR>
     * ２）　@既存残に対する返済の場合、<BR>
     * 　@（パラメータ.繰越元予約注文単位.is反対売買取引() == false）<BR>
     * 　@先物OP予約建玉返済指定情報テーブルのレコードを更新する。<BR>
     * 　@※パラメータ.返済建玉エントリの要素数分、更新する。<BR>
     * <BR>
     * 　@　@登録の仕様は、DB更新仕様<BR>
     * 　@　@「注文繰越_予約建玉返済指定情報テーブル.xls」を参照。<BR>
     * <BR>
     * ３）　@予約注文履歴を作成する。<BR>
     * <BR>
     * 　@先物OP予約注文更新サービス.insert予約注文履歴(パラメータ.注文ID)を<BR>
     * 　@コールする。<BR>
     * <BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID<BR>
     * @@param l_carryBeforeReserveIfoOrderUnit - (繰越元予約注文単位)<BR>
     * 繰越元の予約注文単位<BR>
     * @@param l_carryAfterParentIfoOrderUnit - (繰越後の親注文単位)<BR>
     * 繰越後の親注文単位<BR>
     * @@param l_carryAfterParentIfoOrderUnit - (返済建玉エントリ)<BR>
     * 返済建玉エントリ<BR>
     * @@throws WEB3BaseException
     */
    public void submitIfoCloseContractCarryReserveOrder(
        long l_lngOrderId,
        WEB3ToSuccIfoOrderUnitImpl l_carryBeforeReserveIfoOrderUnit,
        IfoOrderUnit l_carryAfterParentIfoOrderUnit,
        SettleContractEntry[] l_settleContractEntrys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitIfoCloseContractCarryReserveOrder(" +
            "long, WEB3ToSuccIfoOrderUnitImpl, IfoOrderUnit, SettleContractEntry[])";
        log.entering(STR_METHOD_NAME);

        if (l_carryBeforeReserveIfoOrderUnit == null || l_carryAfterParentIfoOrderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_carryBeforeReserveIfoOrderUnit.getDataSourceObject();
        //先物OP予約注文単位テーブルにレコードを登録する。
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams(l_rsvIfoOrderUnitRow);

        //登録の仕様は、DB更新仕様
        //「注文繰越_先物OP予約注文単位テーブル.xls」を参照
        //注文単位ＩＤ          null
        l_rsvIfoOrderUnitParams.setOrderUnitId(null);

        //注文ＩＤ 取得した注文ID（自動採番）
        l_rsvIfoOrderUnitParams.setOrderId(l_lngOrderId);

        //注文履歴最終通番     1
        l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);

        //注文状態               1:受付済（新規注文）
        l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);

        //注文有効状態          1:オープン
        l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

        //失効区分            1:オープン
        l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);

        //発注日
        l_rsvIfoOrderUnitParams.setBizDate(WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        //注文エラー理由コード     0000：正常
        l_rsvIfoOrderUnitParams.setErrorReasonCode(WEB3ErrorReasonCodeDef.NORMAL);

        //初回注文の注文ＩＤ
        //繰越元予約注文単位.初回注文の注文ＩＤ = null の場合、繰越元予約注文単位.注文ＩＤ
        //それ以外の場合、繰越元予約注文単位の同項目
        if (l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull())
        {
            l_rsvIfoOrderUnitParams.setFirstOrderId(l_rsvIfoOrderUnitRow.getOrderId());
        }

        //発注エラーコード    null
        l_rsvIfoOrderUnitParams.setOrderErrorCode(null);

        //親注文の注文ＩＤ          引数.繰越後の親注文単位.注文ID
        l_rsvIfoOrderUnitParams.setParentOrderId(l_carryAfterParentIfoOrderUnit.getOrderId());

        //親注文の注文単位ＩＤ      引数.繰越後の親注文単位.注文単位ID
        l_rsvIfoOrderUnitParams.setParentOrderUnitId(l_carryAfterParentIfoOrderUnit.getOrderUnitId());

        //立会区分             取引時間管理.get立会区分()
        l_rsvIfoOrderUnitParams.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

        //作成日付             現在日時（GtlUtils.getSystemTimestamp()）
        l_rsvIfoOrderUnitParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

        //更新日付             現在日時（GtlUtils.getSystemTimestamp()）
        l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_queryProcessor.doInsertQuery(l_rsvIfoOrderUnitParams);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //既存残に対する返済の場合
        //パラメータ.繰越元予約注文単位.is反対売買取引() == false
        if (!l_carryBeforeReserveIfoOrderUnit.isReversingTrade())
        {
            int l_intCnt = 0;
            if (l_settleContractEntrys != null && l_settleContractEntrys.length > 0)
            {
                l_intCnt = l_settleContractEntrys.length;
            }

            //パラメータ.返済建玉エントリの要素数分、更新する。
            for (int i = 0; i < l_intCnt; i++)
            {
                //登録の仕様は、DB更新仕様
                //「注文繰越_予約建玉返済指定情報テーブル.xls」を参照。
                RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                    new RsvIfoClosingContractSpecParams();

                //口座ＩＤ   引数の繰越元予約注文単位.口座ID
                l_rsvIfoClosingContractSpecParams.setAccountId(l_carryBeforeReserveIfoOrderUnit.getAccountId());

                //補助口座ＩＤ     引数の繰越元予約注文単位.補助口座ID
                l_rsvIfoClosingContractSpecParams.setSubAccountId(
                    l_carryBeforeReserveIfoOrderUnit.getSubAccountId());

                //注文ＩＤ         引数の注文ID
                l_rsvIfoClosingContractSpecParams.setOrderId(l_lngOrderId);

                //建玉ＩＤ               引数の返済建玉エントリ[index].getContractId()
                l_rsvIfoClosingContractSpecParams.setContractId(l_settleContractEntrys[i].getContractId());

                //返済連番
                l_rsvIfoClosingContractSpecParams.setClosingSerialNo(i + 1);

                //返済注文数量           引数の返済建玉エントリ[index].getQuantity()
                l_rsvIfoClosingContractSpecParams.setQuantity(l_settleContractEntrys[i].getQuantity());

                //作成日付             現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoClosingContractSpecParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());

                //更新日付             現在日時（GtlUtils.getSystemTimestamp()）
                l_rsvIfoClosingContractSpecParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

                try
                {
                    QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

                    l_queryProcessor.doInsertQuery(l_rsvIfoClosingContractSpecParams);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }
        }

        //３）　@予約注文履歴を作成する。
        //先物OP予約注文更新サービス.insert予約注文履歴(パラメータ.注文ID)を
        //コールする。
        WEB3ToSuccReservationIfoOrderUpdateService l_rsvIfoOrderUnitUpdateService =
            (WEB3ToSuccReservationIfoOrderUpdateService) Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_rsvIfoOrderUnitUpdateService.insertReserveOrderAction(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate夕場まで注文訂正可能)<BR>
     * 夕場まで注文に訂正可能かどうかのチェックを行う。<BR>
     * <BR>
     * －「夕場まで注文」取扱可能会社であること。<BR>
     * －現在の時間帯が夕場以外であること。<BR>
     * <BR>
     * １）　@以下のいずれかに該当する場合は、何もせずにreturnする。<BR>
     * <BR>
     * 　@　@・パラメータ.注文期限区分≠"夕場まで注文"の場合<BR>
     * <BR>
     * 　@　@・パラメータ.予約注文単位.get注文期限区分()=="夕場まで注文"の場合<BR>
     * 　@　@　@※新規注文登録時に審査済みなのでチェック不要。<BR>
     * <BR>
     * ２）　@取扱可能注文条件インスタンスを生成する。<BR>
     * <BR>
     * 　@[コンストラクタに指定する引数]<BR>
     * 　@　@証券会社コード：　@パラメータ.予約注文単位.get部店ID()に該当する部店.証券会社コード<BR>
     * 　@　@銘柄タイプ：　@ProductTypeEnum.”先物オプション”<BR>
     * 　@　@先物／オプション区分：　@パラメータ.予約注文単位.先物／オプション区分<BR>
     * 　@　@信用取引区分：　@"DEFAULT"（固定）<BR>
     * <BR>
     * ３）　@夕場まで注文取扱可能チェックを行う。<BR>
     * <BR>
     * 　@(２）で取得した取扱可能注文条件.is夕場まで注文取扱可能==false)<BR>
     * 　@または<BR>
     * 　@(取引時間管理.is夕場時間帯()==true)<BR>
     * 　@のいずれかにあてはまる場合、<BR>
     * 　@『夕場まで注文は取り扱えません。』の例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag　@: BUSINESS_ERROR_02816<BR>
     * <BR>
     * @@param l_strExpirationDateType - (注文期限区分)<BR>
     * 注文期限区分<BR>
     * @@param l_toSuccIfoOrderUnitImpl - (予約注文単位)<BR>
     * 予約注文単位<BR>
     * @@throws WEB3BaseException
     */
    public void validateEveningSessionOrderPossibleChange(
        String l_strExpirationDateType,
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEveningSessionOrderPossibleChange(String, WEB3ToSuccIfoOrderUnitImpl)";
        log.entering(STR_METHOD_NAME);

        if (l_toSuccIfoOrderUnitImpl == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //・パラメータ.注文期限区分≠"夕場まで注文"の場合
        //・パラメータ.予約注文単位.get注文期限区分()=="夕場まで注文"の場合
        if (!WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(l_strExpirationDateType)
            || WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER.equals(
                l_toSuccIfoOrderUnitImpl.getExpirationDateType()))
        {
            //何もせずにreturnする。
            log.exiting(STR_METHOD_NAME);
            return;
        }

        //パラメータ.予約注文単位.get部店ID()に該当する部店
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        WEB3GentradeBranch l_branch = null;
        try
        {
            l_branch = (WEB3GentradeBranch)l_accountManager.getBranch(l_toSuccIfoOrderUnitImpl.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //予約注文単位Row
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow =
            (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnitImpl.getDataSourceObject();

        //取扱可能注文条件インスタンスを生成する。
        //[コンストラクタに指定する引数]
        //証券会社コード：　@パラメータ.予約注文単位.get部店ID()に該当する部店.証券会社コード
        //銘柄タイプ：　@ProductTypeEnum.”先物オプション”
        //先物／オプション区分：　@パラメータ.予約注文単位.先物／オプション区分
        //信用取引区分：　@"DEFAULT"（固定）
        WEB3GentradeHandlingOrderCond l_handlingOrderCond =
            new WEB3GentradeHandlingOrderCond(
                l_branch.getInstitution().getInstitutionCode(),
                ProductTypeEnum.IFO,
                l_rsvIfoOrderUnitRow.getFutureOptionDiv(),
                WEB3MarginTradingDivDef.DEFAULT);

        //夕場まで注文取扱可能チェックを行う。
        //取得した取扱可能注文条件.is夕場まで注文取扱可能==false)
        //または(取引時間管理.is夕場時間帯()==true)のいずれかにあてはまる場合、
        //『夕場まで注文は取り扱えません。』の例外をスローする。
        if (!l_handlingOrderCond.isEveningSessionOrderPossibleHandling()
            || WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone())
        {
            log.debug("夕場まで注文は取り扱えません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02816,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "夕場まで注文は取り扱えません。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
