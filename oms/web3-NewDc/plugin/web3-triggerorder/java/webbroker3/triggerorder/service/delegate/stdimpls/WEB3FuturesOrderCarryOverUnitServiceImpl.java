head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3FuturesOrderCarryOverUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文繰越１件サービス実装クラス(WEB3FuturesOrderCarryOverUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/20 艾興 (中訊) 新規作成
                 : 2006/8/15 郭英 (中訊) 仕様変更 モデル507
Revesion History : 2007/6/30 孟亜南 (中訊) 仕様変更 モデル756 762
Revesion History : 2007/7/17 趙林鵬 (中訊) モデルNo.776
Revesion History : 2008/04/11 趙林鵬 (中訊) モデルNo.277,278
Revesion History : 2008/04/24 趙林鵬 (中訊) モデルNo.340
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoClosingContractSpec;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoContractSettleOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3IfoOrderCarryOverUpdateInterceptor;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderCarryOverService;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物注文繰越UnitServiceImpl)<BR>
 * 先物注文繰越１件サービス実装クラス<BR>
 * <BR>
 * １件ごとの注文繰越処理を実施する。<BR>
 * <BR>
 * Plugin時に自動トランザクションTransactionalInterceptor<BR>
 * (TransactionalInterceptor.TX_JOIN_EXISTING)を指定する。<BR>
 * @@author  王暁傑
 * @@version 1.0
 */
public class WEB3FuturesOrderCarryOverUnitServiceImpl
    implements WEB3FuturesOrderCarryOverUnitService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOrderCarryOverUnitServiceImpl.class);

    /**
     * @@roseuid 40F7A2C60157
     */
    public WEB3FuturesOrderCarryOverUnitServiceImpl()
    {

    }
    /**
     * (create新規建翌日注文)<BR>
     * 翌日注文（新規建）を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文繰越）create新規建翌日注文」参照。<BR>
     * <BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧
     * @@roseuid 40A88B46039C
     */
    public void createOpenContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createOpenContractNextOrder(OrderUnit, List)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            //先物注文マネージャ
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();
            //is繰越対象注文(IfoOrderUnit)
            boolean l_blnIsCarryoverOrder = l_orderManager.isCarryoverOrder((IfoOrderUnit)l_orderUnit);

            // (*)繰越対象外注文（is繰越対象注文 == false）の場合
            if (!l_blnIsCarryoverOrder)
            {
                // update繰越元注文()
                //  [update繰越元注文()に指定する引数]
                //  注文単位：　@注文単位
                //  注文エラー理由コード：　@（その他エラー）
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.OTHRE_ERROR);

                // return
                return;
            }

            // 先物OP銘柄を取得する。
            Product l_product = l_orderUnit.getProduct();

            // is繰越スキップ銘柄
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            long l_lngTradedProductId = l_product.getProductId();
            long l_lngMarketId = ((IfoOrderUnitRow)l_orderUnit.getDataSourceObject()).getMarketId();

            WEB3IfoTradedProductImpl l_tradedProduct = null;
            try
            {
                l_tradedProduct = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    l_lngTradedProductId,
                    l_lngMarketId);
            }
            catch (NotFoundException l_ex)
            {
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
                return;
            }
            boolean l_blnIsCarryOverSkipProduct = l_tradedProduct.isCarryOverSkipProduct();

            // (*1) 注文繰越スキップ銘柄（is繰越スキップ銘柄() == true）の場合
            if (l_blnIsCarryOverSkipProduct == true)
            {
                // update繰越元注文()
                //  [update繰越元注文()に指定する引数]
                //  注文単位：　@注文単位
                //  注文エラー理由コード：　@（注文繰越スキップ銘柄エラー）
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);

                // return
                return;
            }

            // getAccountId()
            long l_lngAccountId = l_orderUnit.getAccountId();

            // getSubAccountId()
            long l_lngSubAccountId = l_orderUnit.getSubAccountId();

            // get補助口座()(拡張アカウントマネージャ::get補助口座)
            SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(
                l_lngAccountId,
                l_lngSubAccountId);

            // getTraderId()
            long l_lngTraderId = l_orderUnit.getTraderId();

            // getSide()
            SideEnum l_side = l_orderUnit.getSide();

            // getQuantity( )(OrderUnit::getQuantity)
            double l_dblQuantity = l_orderUnit.getQuantity();
            if (Double.isNaN(l_dblQuantity))
            {
                l_dblQuantity = 0D;
            }

            // getExecutedQuantity( )(OrderUnit::getExecutedQuantity)
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            // getLimitPrice( )(OrderUnit::getLimitPrice)
            double l_dblLimitPrice = l_orderUnit.getLimitPrice();
            if (Double.isNaN(l_dblLimitPrice))
            {
                l_dblLimitPrice = 0D;
            }

            // 新規建注文内容を生成する。
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // 扱者：　@取得した扱者ＩＤに該当する扱者
            Trader l_trader = null;
            if (l_lngTraderId != 0)
            {
                l_trader = l_objectManager.getTrader(l_lngTraderId);
            }

            // 市場コード：　@注文単位.市場ＩＤに該当する市場の市場コード
            Market l_market = l_objectManager.getMarket(l_orderUnitRow.getMarketId());
            String l_strMarketCode = l_market.getMarketCode();

            // 執行条件：　@注文単位.執行条件
            IfoOrderExecutionConditionType l_orderExecutionConditionType =
                l_orderUnitRow.getExecutionConditionType();

            // 注文失効日：　@注文単位.注文失効日
            Date l_datExpirationDate = l_orderUnitRow.getExpirationDate();

            // 発注条件：　@注文単位.発注条件
            String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();

            //逆指値基準値：　@注文単位.逆指値基準値 
            double l_blnStopOrderPrice = l_orderUnitRow.getStopOrderPrice();
            
            //（W指値）訂正指値：　@注文単位.（W指値）訂正指値
            double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
            if (Double.isNaN(l_dblWLimitPrice))
            {
                l_dblWLimitPrice = 0D;
            }
                                    
            //（W指値）執行条件：　@注文単位.（W指値）執行条件 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                l_orderUnitRow.getWLimitExecCondType();
            
			// 初回注文の注文単位ID
			Long l_longFirstOrderUnitId = null;
			// 初回繰越の場合
			if (l_orderUnitRow.getFirstOrderUnitId() == 0 || 
				l_orderUnitRow.getFirstOrderUnitIdIsNull())
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getOrderUnitId());
			}
			// 以外
			else
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getFirstOrderUnitId());
			}

            //夕場前繰越対象フラグ
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr((IfoOrderUnit)l_orderUnit);

            //注文期限区分：先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strExpirationDateType =
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

            // create新規建注文内容
            WEB3IfoOpenContractOrderSpec l_openContractOrderSpec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_trader,
                    (SideEnum.BUY.equals(l_side)),
                    l_strMarketCode,
                    (WEB3IfoProductImpl)l_product,
                    (l_dblQuantity - l_dblExecutedQuantity),
                    l_dblLimitPrice,
                    l_orderExecutionConditionType,
                    l_datExpirationDate,
                    l_strOrderConditionType,
                    l_blnStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_longFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            // validate新規建注文(補助口座 : SubAccount, 先物OP新規建注文内容 : IfoOpenContractOrderSpec
            NewOrderValidationResult l_validationResult =
                l_orderManager.validateFuturesOpenContractOrder(
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_openContractOrderSpec);

            //  getProcessingResult( )
            ProcessingResult l_processingResult = l_validationResult.getProcessingResult();

            //  isFailedResult( )(ProcessingResult::isFailedResult)
            boolean l_blnIsFailedResult = l_processingResult.isFailedResult();

            //  (*2) 発注審査で失敗した場合（isFailedResult() == true）
            if (l_blnIsFailedResult)
            {
                // getErrorInfo( )
                ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
                String l_strErrorReasonCode = null;

                //エラーコードからエラー理由コードに変換する。
                if (WEB3ErrorCatalog.BUSINESS_ERROR_00148.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00031.equals(l_errorInfo))
                {
                    //値幅、呼値チェックでエラーになった場合
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00004.equals(l_errorInfo))
                {
                    //銘柄が売買停止の場合
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00735.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00003.equals(l_errorInfo))
                {
                    //該当銘柄が指定市場で取引できない場合
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
                }
                else
                {
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
                }

                // update繰越元注文()
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    l_strErrorReasonCode);

                // return
                return;
            }

            //  手数料( )(手数料::手数料)
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            // プロパティセット
            //  手数料.注文チャネル = 注文単位.初回注文の注文チャネル
            //  手数料.証券会社ID = 注文単位.証券会社ＩＤ
            //  手数料.部店ID = 注文単位.部店ＩＤ
            //  手数料.発注日 = 取引時間管理.get発注日()
            //  手数料.取引コード(SONAR) = ”51：建”
            //  手数料.手数料商品コード = ”50：先物”
            //  手数料.弁済区分 = ”00：その他”
            //  手数料.is指値 = 新規建注文内容.isLimitOrder()
            //  手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            //  手数料.数量 = 新規建注文内容.getQuantity()
            String l_strOrderChannel = l_orderUnitRow.getOrderChanel();
            String l_lngInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            long l_lngBranchId = l_orderUnit.getBranchId();
            Timestamp l_orderBizDate = new Timestamp(
                WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.OPEN_CONTRACT;
            String l_strBookValueIndexOp = WEB3CommisionProductCodeDef.INDEX_FUTURES;

            l_commission.setOrderChannel(l_strOrderChannel);
            l_commission.setInstitutionCode(l_lngInstitutionCode);
            l_commission.setBranchId(l_lngBranchId);
            l_commission.setOrderBizDate(l_orderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strBookValueIndexOp);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_openContractOrderSpec.isLimitOrder());
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_product).getUnderlyingProductCode());
            
            l_commission.setQuantity(l_openContractOrderSpec.getQuantity());

            // calc概算建代金(手数料, double, SubAccount, 先物OP取引銘柄, double, boolean)
            //  (先物注文マネージャ::calc概算建代金)
            //  calc概算建代金()に指定する引数]
            //  手数料：　@手数料オブジェクト
            //  計算単価：　@注文単位.getLimitPrice()
            //  補助口座：　@補助口座オブジェクト
            //  先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
            //  数量： 新規建注文内容.getQuantity()
            //  isSkip金額チェック：　@true
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult =
                l_orderManager.calcEstimatePrice(
                    l_commission,
                    l_dblLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_tradedProduct,
                    l_openContractOrderSpec.getQuantity(),
                    true);

            // 先物OP注文繰越更新インタセプタ(IfoOrderUnitParams, String)
            //  (先物OP注文繰越更新インタセプタ::先物OP注文繰越更新インタセプタ)
            //  [コンストラクタの引数]
            //  (繰越元)注文単位Params：　@注文単位.getDataSourceObject()の戻り値
            //  注文エラー理由コード：　@”正常”
            //36.インタセプタをセットする。
            WEB3IfoOrderCarryOverUpdateInterceptor l_updateInterceptor =
                new WEB3IfoOrderCarryOverUpdateInterceptor(
                    (IfoOrderUnitParams)l_orderUnit.getDataSourceObject(),
                    WEB3ErrorReasonCodeDef.NORMAL);

            // set手数料(手数料)(先物OP注文繰越更新インタセプタ::set手数料)
            //  [set手数料()に指定する引数]
            //  手数料：　@通常手数料オブジェクト
            l_updateInterceptor.setCommissionFee(l_commission);

            // set概算受渡代金計算結果(先物OP概算受渡代金計算結果)(先物OP注文繰越更新インタセプタ::set概算受渡代金計算結果)
            //  [set概算受渡代金計算結果()に指定する引数]
            //  概算受渡代金計算結果：　@calc概算建代金()の戻り値
            // （※ 注文単位.getLimitPrice()にて計算した結果を使用）
            l_updateInterceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

            //set立会区分
            l_updateInterceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

            // validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //  [引数]
            //  補助口座： 補助口座
            //  注文内容インタセプタ： 先物OP注文繰越更新インタセプタを要素とした配列
            //  注文内容： 新規建注文内容を要素とした配列
            //  注文種別： 注文単位.注文種別
            //  余力更新フラグ： true
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(
                    WEB3TPTradingPowerService.class);
            WEB3IfoOrderCarryOverUpdateInterceptor[] l_interceptor = {l_updateInterceptor};
            WEB3IfoOpenContractOrderSpec[] l_orderSpec = {l_openContractOrderSpec};
            WEB3TPTradingPowerResult l_tradingPowerResult = l_tradingPowerService.validateTradingPower(
                (WEB3GentradeSubAccount)l_subAccount,
                l_interceptor,
                l_orderSpec,
                l_orderUnit.getOrderType(),
                true);

            //(*5) 証拠金チェックでエラーになった場合
            // 取引余力結果.判定フラグ==false の場合、update繰越元注文()を実行後、処理を終了する。
            if(!l_tradingPowerResult.isResultFlg())
            {
                // update繰越元注文(OrderUnit, String)(先物注文繰越UnitServiceImpl::update繰越元注文)
                //  [update繰越元注文()に指定する引数]
                //  注文単位：　@注文単位
                //  注文エラー理由コード：
                // （株価指数オプション残高不足エラー）
                //  ※ 注文エラー理由コードについては
                //  DB更新仕様
                //  先物注文繰越_注文単位テーブル仕様.xls
                // 「（注文繰越補足）注文エラー理由コード」シート参照。
                //  取引余力サービス.validate取引余力()==falseの場合。
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR);

                // return
                return;
            }

            // setThreadLocalPersistenceEventInterceptor(IfoOrderManagerPersistenceEventInterceptor)(先物注文マネージャ::setThreadLocalPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_updateInterceptor);

            // createNewOrderId( )(先物注文マネージャ::createNewOrderId)
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            // submitOpenContractOrder(SubAccount, IfoOpenContractOrderSpec, long, String, boolean)(先物注文マネージャ::submitOpenContractOrder)
            //  [submitOpenContractOrder()に指定する引数]
            //  補助口座：　@補助口座
            //  新規建注文内容：　@新規建注文内容
            //  注文ＩＤ：　@先物注文マネージャ.createNewOrderId()
            //  取引パスワード： 口座.取引パスワード
            //  isSkip発注審査：　@true
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
            OrderSubmissionResult l_result = l_orderManager.submitOpenContractOrder(
                l_subAccount,
                l_openContractOrderSpec,
                l_lngNewOrderId,
                l_mainAccount.getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //繰越対象の予約注文が存在する場合（パラメータ.予約注文単位一覧≠null）
            if (l_lisRsvIfoOrderUnits != null)
            {
                //getOrderUnits(arg0 : long)
                //注文ID：　@createNewOrderId()の戻り値
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngNewOrderId);

                //exec連続注文繰越(IfoOrderUnit, IfoOrderUnit, List)
                WEB3ToSuccIfoOrderCarryOverService l_service =
                    (WEB3ToSuccIfoOrderCarryOverService)Services.getService(
                        WEB3ToSuccIfoOrderCarryOverService.class);

                l_service.execToSuccOrderCarryOver(
                    (IfoOrderUnit)l_orderUnit,
                    (IfoOrderUnit)l_orderUnits[0],
                    l_lisRsvIfoOrderUnits);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create返済翌日注文)<BR>
     * 翌日注文（返済）を作成する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文繰越）create返済翌日注文」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@param l_lisRsvIfoOrderUnits - (予約注文単位一覧)<BR>
     * 予約注文単位一覧 
     * @@roseuid 40A88B4603AC
     */
    public void createSettleContractNextOrder(OrderUnit l_orderUnit, List l_lisRsvIfoOrderUnits)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSettleContractNextOrder(OrderUnit, List)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3BaseException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod = l_finApp.getTradingModule(ProductTypeEnum.IFO);

            //先物注文マネージャ
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            //is繰越対象注文(IfoOrderUnit)
            boolean l_blnIsCarryoverOrder = l_orderManager.isCarryoverOrder((IfoOrderUnit)l_orderUnit);
            
            if (!l_blnIsCarryoverOrder)
            {
                // update繰越元注文()
                //  [update繰越元注文()に指定する引数]
                //  注文単位：　@注文単位
                //  注文エラー理由コード：　@（その他エラー）
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.OTHRE_ERROR);

                // return
                return;
            }

            // 先物OP銘柄を取得する。
            Product l_product = l_orderUnit.getProduct();

            // is繰越スキップ銘柄
            WEB3IfoProductManagerImpl l_productManager =
                (WEB3IfoProductManagerImpl)l_tradingMod.getProductManager();
            long l_lngTradedProductId = l_product.getProductId();
            long l_lngMarketId = ((IfoOrderUnitRow)l_orderUnit.getDataSourceObject()).getMarketId();

            WEB3IfoTradedProductImpl l_isTradedProductImpl = null;
            try
            {
                l_isTradedProductImpl = (WEB3IfoTradedProductImpl)l_productManager.getTradedProduct(
                    l_lngTradedProductId,
                    l_lngMarketId);
            }
            catch (NotFoundException l_ex)
            {
                this.updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR);
                return;
            }
            boolean l_blnIsCarryOverSkipProduct = l_isTradedProductImpl.isCarryOverSkipProduct();

            // (*1) 注文繰越スキップ銘柄（is繰越スキップ銘柄() == true）の場合
            if (l_blnIsCarryOverSkipProduct == true)
            {
                // update繰越元注文(OrderUnit, String
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.CARRY_OVER_SKIP_PRODUCT_ERROR);

                // return
                return;
            }

            //  getAccountId()
            long l_lngAccountId = l_orderUnit.getAccountId();

            // getSubAccountId()
            long l_lngSubAccountId = l_orderUnit.getSubAccountId();

            // get補助口座()(拡張アカウントマネージャ::get補助口座)
            SubAccount l_subAccount = l_finApp.getAccountManager().getSubAccount(
                l_lngAccountId,
                l_lngSubAccountId);

            // getTraderId()
            long l_lngTraderId = l_orderUnit.getTraderId();


            // getLimitPrice()
            double l_dblLimitPrice = l_orderUnit.getLimitPrice();
            if (Double.isNaN(l_dblLimitPrice))
            {
                l_dblLimitPrice = 0D;
            }

            //  getContractsToClose( )(IfoContractSettleOrderUnitImpl::getContractsToClose)
            IfoContractSettleOrderUnit l_contractSettleOrderUnit =
                (IfoContractSettleOrderUnit)l_orderUnit;
            IfoClosingContractSpec[] l_contractSpecs = l_contractSettleOrderUnit.getContractsToClose();

            //  (*) 返済指定情報要素毎のLOOP処理
            WEB3IfoPositionManagerImpl l_positionManager =
                (WEB3IfoPositionManagerImpl)l_tradingMod.getPositionManager();
            int l_intContractSpecsLength = l_contractSpecs.length;
            List l_lstArray = new ArrayList();
            try
            {
                for (int i = 0; i < l_intContractSpecsLength; i++)
                {
                    double l_dblExecutedQuantity =
                        l_contractSpecs[i].getExecutedQuantity();
                    if (Double.isNaN(l_dblExecutedQuantity))
                    {
                        l_dblExecutedQuantity = 0D;
                    }
                    double l_dblQuantity = 
                        l_contractSpecs[i].getQuantity() - l_dblExecutedQuantity;
                    long l_lngContractId = l_contractSpecs[i].getContractId();
                    WEB3IfoContractImpl l_contract =
                        (WEB3IfoContractImpl)l_positionManager.getContract(l_lngContractId);
                    double l_dblBalance =
                        l_contract.getQuantity() - l_contract.getLockedQuantity();
                    if (l_dblQuantity > l_dblBalance)
                    {
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00299,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                    }
                    
                    if (l_dblQuantity > 0D)
                    {
                        l_lstArray.add(
                            new SettleContractEntry(l_lngContractId, l_dblQuantity));
                    }
                }
            }
            catch (WEB3BusinessLayerException l_wbe)
            {
                log.error(l_wbe.getMessage(), l_wbe);
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    WEB3ErrorReasonCodeDef.OPEN_INTERSET_SHORT_ERROR);
                return;
            }
            SettleContractEntry[] l_settleContractEntry =
                new SettleContractEntry[l_lstArray.size()];
            l_lstArray.toArray(l_settleContractEntry);

            // 返済注文内容を生成する。
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            // 扱者：　@取得した扱者ＩＤに該当する扱者
            WEB3GentradeTrader l_trader = null;
            if (l_lngTraderId != 0)
            {
                l_trader = (WEB3GentradeTrader)l_objectManager.getTrader(l_lngTraderId);
            }

            //執行条件
            IfoOrderExecutionConditionType l_orderExecutionConditionType = l_orderUnitRow.getExecutionConditionType();

            //注文失効日
            Date l_datExpirationDate = l_orderUnitRow.getExpirationDate();

            //発注条件
            String l_strOrderConditionType = l_orderUnitRow.getOrderConditionType();

            //逆指値基準値：　@注文単位.逆指値基準値 
            double l_blnStopOrderPrice = l_orderUnitRow.getStopOrderPrice();
            
            //(W指値)訂正指値
            double l_dblWLimitPrice = l_orderUnitRow.getWLimitPrice();
            if (Double.isNaN(l_dblWLimitPrice))
            {
                l_dblWLimitPrice = 0D;
            }
            
            //（W指値）執行条件：　@注文単位.（W指値）執行条件 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                l_orderUnitRow.getWLimitExecCondType();
            
			// 初回注文の注文単位ID
			Long l_longFirstOrderUnitId = null;
			// 初回繰越の場合
			if (l_orderUnitRow.getFirstOrderUnitId() == 0 || 
				l_orderUnitRow.getFirstOrderUnitIdIsNull())
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getOrderUnitId());
			}
			// 以外
			else
			{
				l_longFirstOrderUnitId = new Long(l_orderUnitRow.getFirstOrderUnitId());
			}

            //夕場前繰越対象フラグ
            boolean l_blnEveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr((IfoOrderUnit)l_orderUnit);

            //注文期限区分：先物OPデータアダプタ.get注文期限区分(注文単位)
            String l_strExpirationDateType =
                WEB3IfoDataAdapter.getExpirationDateType((IfoOrderUnit)l_orderUnit);

            // create返済注文内容
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                    l_subAccount.getInstitution().getInstitutionCode(),
                    l_trader,
                    l_dblLimitPrice,
                    l_orderExecutionConditionType,
                    l_datExpirationDate,
                    l_settleContractEntry,
                    l_strOrderConditionType,
                    l_blnStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_strExpirationDateType,
                    l_longFirstOrderUnitId,
                    l_blnEveningSessionCarryoverFlag);

            // validate先物返済注文(SubAccount, IfoSettleContractOrderSpec)(先物注文マネージャ::validate先物返済注文)
            //  [validate先物返済注文()に指定する引数]
            //  補助口座：　@get補助口座()の戻り値
            //  返済注文内容：　@（生成した返済注文内容オブジェクト）
            NewOrderValidationResult l_newOrderValidationResult = l_orderManager.validateFuturesSettleContractOrder(
                (WEB3GentradeSubAccount)l_subAccount,
                l_settleContractOrderSpec);

            // getProcessingResult()
            ProcessingResult l_processingResult = l_newOrderValidationResult.getProcessingResult();

            // isFailedResult( )(ProcessingResult::isFailedResult)
            boolean l_blnIsFailedResult = l_processingResult.isFailedResult();

            // (*2) 発注審査で失敗した場合（isFailedResult() == true）
            if (l_blnIsFailedResult)
            {
                // getErrorInfo( )
                ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
                String l_strErrorReasonCode = null;

                //エラーコードからエラー理由コードに変換する。
                if (WEB3ErrorCatalog.BUSINESS_ERROR_00148.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00031.equals(l_errorInfo))
                {
                    //値幅、呼値チェックでエラーになった場合
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00004.equals(l_errorInfo))
                {
                    //銘柄が売買停止の場合
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
                }
                else if (WEB3ErrorCatalog.BUSINESS_ERROR_00735.equals(l_errorInfo)
                    || WEB3ErrorCatalog.BUSINESS_ERROR_00003.equals(l_errorInfo))
                {
                    //該当銘柄が指定市場で取引できない場合
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
                }
                else
                {
                    l_strErrorReasonCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
                }

                // update繰越元注文(OrderUnit, String)(先物注文繰越UnitServiceImpl::update繰越元注文)
                //  [update繰越元注文()に指定する引数]
                //  注文単位：　@注文単位
                //  注文エラー理由コード：
                //　@getErrorInfo()で取得したエラー情報より判定し、該当する注文エラー理由コードをセットする。
                updateCarryOverOriginOrder(
                    l_orderUnit,
                    l_strErrorReasonCode);

                // return
                return;
            }

            // 手数料オブジェクトを生成する。
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            // (*3) プロパティセット
            //  手数料.注文チャネル = 注文単位.初回注文の注文チャネル
            //  手数料.証券会社ID = 注文単位.証券会社ＩＤ
            //  手数料.部店ID = 注文単位.部店ＩＤ
            //  手数料.発注日 = 取引時間管理.get発注日()
            //  手数料.取引コード(SONAR) = ”52：返済”
            //  手数料.手数料商品コード = ”50：先物”
            //  手数料.弁済区分 = ”00：その他”
            //  手数料.is指値 = 返済注文内容.isLimitOrder()
            //  手数料.原資産銘柄コード = 先物OP銘柄.get原資産銘柄コード()
            //  手数料.日計り区分 = null
            //  手数料.数量 = 返済注文内容.getTotalQuantity()
            String l_strOrderChannel = l_orderUnitRow.getOrderChanel();
            String l_lngInstitutionCode = l_subAccount.getInstitution().getInstitutionCode();
            long l_lngBranchId = l_orderUnit.getBranchId();
            Timestamp l_orderBizDate = new Timestamp(
                WEB3GentradeTradingTimeManagement.getOrderBizDate().getTime());
            String l_strOpenContract = WEB3TransactionTypeSONARDef.SETTLE_CONTRACT;
            String l_strBookValueIndexOp = WEB3CommisionProductCodeDef.INDEX_FUTURES;

            l_commission.setOrderChannel(l_strOrderChannel);
            l_commission.setInstitutionCode(l_lngInstitutionCode);
            l_commission.setBranchId(l_lngBranchId);
            l_commission.setOrderBizDate(l_orderBizDate);
            l_commission.setSonarTradedCode(l_strOpenContract);
            l_commission.setCommissionProductCode(l_strBookValueIndexOp);
            l_commission.setPayType(WEB3PayTypeDef.OTHER);
            l_commission.setIsLimitPrice(l_settleContractOrderSpec.isLimitOrder());            
            l_commission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_product).getUnderlyingProductCode());

            l_commission.setDayTradeType(null);

            l_commission.setQuantity(l_settleContractOrderSpec.getTotalQuantity());
            
            // calc概算決済損益
            //  [calc概算決済損益()に指定する引数]
            //  手数料：　@手数料オブジェクト
            //  指値：　@注文単位.getLimitPrice()
            //  補助口座：　@補助口座オブジェクト
            //  先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
            //  返済建玉エントリ[]： 生成した返済建玉エントリ
            //  数量： 返済注文内容.getTotalQuantity()
            //  売買： （以下のとおり）
            //  注文単位.getSide()＝SideEnum.BUY(買)（=売建買返済）の場合、”売”をセット。
            //  注文単位.getSide()＝SideEnum.SELL(売)（=買建売返済）の場合、”買”をセット。
            //  isSkip金額チェック：　@true
            SideEnum l_sideEnum = null;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_sideEnum = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_sideEnum = SideEnum.BUY;
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_amountCalcResult =
                l_orderManager.calcEstimateSettlementIncome(
                    l_commission,
                    l_dblLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_isTradedProductImpl,
                    l_settleContractEntry,
                    l_settleContractOrderSpec.getTotalQuantity(),
                    l_sideEnum,
                    true);

            // 先物OP注文繰越更新インタセプタ(IfoOrderUnitParams, String)(先物OP注文繰越更新インタセプタ::先物OP注文繰越更新インタセプタ)
            //  [コンストラクタの引数]
            //  (繰越元)注文単位Params：　@注文単位.getDataSourceObject()の戻り値
            //  注文エラー理由コード：　@”正常”
            WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
                new WEB3IfoOrderCarryOverUpdateInterceptor(
                    (IfoOrderUnitParams)l_orderUnit.getDataSourceObject(),
                    WEB3ErrorReasonCodeDef.NORMAL);

            // set手数料(手数料)(先物OP注文繰越更新インタセプタ::set手数料)
            l_interceptor.setCommissionFee(l_commission);

            // set概算受渡代金計算結果(先物OP概算受渡代金計算結果)
            l_interceptor.setEstimateDeliveryAmountCalcResult(l_amountCalcResult);

            //set立会区分
            l_interceptor.setSessionType(WEB3GentradeTradingTimeManagement.getSessionType());

            // setThreadLocalPersistenceEventInterceptor(IfoOrderManagerPersistenceEventInterceptor)(先物注文マネージャ::setThreadLocalPersistenceEventInterceptor)
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

            // createNewOrderId( )(先物注文マネージャ::createNewOrderId)
            long l_lngNewOrderId = l_orderManager.createNewOrderId();

            // submitSettleContractOrder(SubAccount, IfoSettleContractOrderSpec, long, String, boolean)(先物注文マネージャ::submitSettleContractOrder)
            // [submitSettleContractOrder()に指定する引数]
            // 補助口座：　@補助口座
            // 返済注文内容：　@返済注文内容
            // 注文ＩＤ：　@先物注文マネージャ.createNewOrderId()
            // 取引パスワード： 口座.取引パスワード
            // isSkip発注審査：　@true
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
            OrderSubmissionResult l_result = l_orderManager.submitSettleContractOrder(
                l_subAccount,
                l_settleContractOrderSpec,
                l_lngNewOrderId,
                l_mainAccount.getTradingPassword(),
                true);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.error(l_result.getProcessingResult().toString());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //繰越対象の予約注文が存在する場合（パラメータ.予約注文単位一覧≠null）
            if (l_lisRsvIfoOrderUnits != null)
            {
                //getOrderUnits(arg0 : long)
                //注文ID：　@createNewOrderId()の戻り値
                OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngNewOrderId);

                //exec連続注文繰越(IfoOrderUnit, IfoOrderUnit, List)
                WEB3ToSuccIfoOrderCarryOverService l_service =
                    (WEB3ToSuccIfoOrderCarryOverService)Services.getService(
                        WEB3ToSuccIfoOrderCarryOverService.class);

                l_service.execToSuccOrderCarryOver(
                    (IfoOrderUnit)l_orderUnit,
                    (IfoOrderUnit)l_orderUnits[0],
                    l_lisRsvIfoOrderUnits);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update繰越元注文)<BR>
     * 繰越元注文を更新する。<BR>
     * <BR>
     * １）　@以下の条件に該当する繰越元注文の注文単位レコードをupdateする。<BR>
     * 　@<条件> <BR>
     *     注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID <BR>
     * 　@<更新内容> <BR>
     * 　@　@注文単位レコード.注文エラー理由コード = パラメータ.注文エラー理由コード <BR>
     * 　@　@注文単位レコード.更新日付 = 現在日時 <BR>
     * ２）　@以下の条件に該当する繰越元注文の注文履歴の、 <BR>
     * 　@　@　@最終履歴レコードの注文エラー理由コード をupdateする。 <BR>
     * 　@<条件> <BR>
     * 　@　@履歴テーブル.注文単位ID　@＝　@パラメータ.注文単位.注文単位ID　@and <BR>
     * 　@　@履歴テーブル.注文履歴番号　@＝　@パラメータ.注文単位.注文履歴最終通番 <BR>
     * 　@<更新内容> <BR>
     * 　@　@履歴レコード.注文エラー理由コード　@＝　@パラメータ.注文エラー理由コード <BR>
     * 　@　@履歴レコード.更新日付　@＝　@現在日時 <BR>
     * ３）　@以下の条件に該当する、繰越元注文の注文（ヘッダ）の更新日時をupdateする。<BR>
     * 　@<条件> <BR>
     * 　@　@注文（ヘッダ）テーブル.注文ID　@＝　@パラメータ.注文単位.注文ID <BR>
     * 　@<更新内容> <BR>
     * 　@　@注文（ヘッダ）レコード.更新日付　@＝　@現在日時 <BR>
     * @@param l_orderUnit - （繰越元）注文単位オブジェクト<BR>
     * @@param l_strOrderErrorReasonCode - 注文エラー理由コード<BR>
     * @@roseuid 40A88B4603CB
     */
    public void updateCarryOverOriginOrder(
        OrderUnit l_orderUnit,
        String l_strOrderErrorReasonCode)
        throws DataQueryException, DataNetworkException, IllegalStateException
    {
        final String STR_METHOD_NAME = "updateCarryOverOriginOrder()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        // １）　@以下の条件に該当する繰越元注文の注文単位レコードをupdateする。<BR>
        // 　@<条件> <BR>
        //     注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID <BR>
        // 　@<更新内容> <BR>
        // 　@　@注文単位レコード.注文エラー理由コード = パラメータ.注文エラー理由コード <BR>
        // 　@　@注文単位レコード.更新日付 = 現在日時 <BR>
        StringBuffer l_sbWhereOrder = new StringBuffer();
        l_sbWhereOrder.append(" order_unit_id = ? ");

        Object[] l_objWhereOrder = {
            String.valueOf(l_orderUnit.getOrderUnitId())};

        //注文単位テーブルの結果リスト
        List l_lisSearchResultOrder =
            l_queryProcessor.doFindAllQuery(
                IfoOrderUnitRow.TYPE,
                l_sbWhereOrder.toString(),
                null,
                "FOR UPDATE",
                l_objWhereOrder);

        int l_intNumOrder = l_lisSearchResultOrder.size();
        if (l_intNumOrder > 0)
        {
            for (int i=0; i<l_intNumOrder; i++)
            {
                IfoOrderUnitParams l_paramsOrder = new IfoOrderUnitParams((IfoOrderUnitRow)l_lisSearchResultOrder.get(i));
                l_paramsOrder.setErrorReasonCode(l_strOrderErrorReasonCode);
                l_paramsOrder.setLastUpdatedTimestamp( l_finApp.getTradingSystem().getSystemTimestamp());
                l_queryProcessor.doUpdateQuery(l_paramsOrder);
            }
        }

        // ２）　@以下の条件に該当する繰越元注文の注文履歴の、 <BR>
        //     　@最終履歴レコードの注文エラー理由コード をupdateする。 <BR>
        //   <条件> <BR>
        //     履歴テーブル.注文単位ID　@＝　@パラメータ.注文単位.注文単位ID　@and <BR>
        //     履歴テーブル.注文履歴番号　@＝　@パラメータ.注文単位.注文履歴最終通番 <BR>
        //   <更新内容> <BR>
        //     履歴レコード.注文エラー理由コード　@＝　@パラメータ.注文エラー理由コード <BR>
        //     履歴レコード.更新日付　@＝　@現在日時 <BR>
        StringBuffer l_sbWhereAction = new StringBuffer();
        l_sbWhereAction.append(" order_unit_id = ? ");
        l_sbWhereAction.append(" and order_action_serial_no = ? ");

        Object[] l_objWhereAction = {
            String.valueOf(l_orderUnit.getOrderUnitId()),
            String.valueOf(l_orderUnitRow.getLastOrderActionSerialNo())};

        //履歴テーブルの結果リスト
        List l_lisSearchResultAction =
            l_queryProcessor.doFindAllQuery(
                IfoOrderActionRow.TYPE,
                l_sbWhereAction.toString(),
                null,
                "FOR UPDATE",
                l_objWhereAction);

        int l_intNumAction = l_lisSearchResultAction.size();
        if (l_intNumAction > 0)
        {
            for (int i=0; i<l_intNumAction; i++)
            {
                IfoOrderActionParams l_paramsAction = new IfoOrderActionParams((IfoOrderActionRow)l_lisSearchResultAction.get(i));
                l_paramsAction.setErrorReasonCode(l_strOrderErrorReasonCode);
                l_paramsAction.setLastUpdatedTimestamp( l_finApp.getTradingSystem().getSystemTimestamp());
                l_queryProcessor.doUpdateQuery(l_paramsAction);
            }
        }

        // ３）　@以下の条件に該当する、繰越元注文の注文（ヘッダ）の更新日時をupdateする。<BR>
        // 　@<条件> <BR>
        //     注文（ヘッダ）テーブル.注文ID　@＝　@パラメータ.注文単位.注文ID <BR>
        //   <更新内容> <BR>
        //   　@注文（ヘッダ）レコード.更新日付　@＝　@現在日時 <BR>
        IfoOrderRow l_orderRow = IfoOrderDao.findRowByOrderId(l_orderUnit.getOrderId());
        IfoOrderParams l_paramsHead = new IfoOrderParams(l_orderRow);
        l_paramsHead.setLastUpdatedTimestamp( l_finApp.getTradingSystem().getSystemTimestamp());
        l_queryProcessor.doUpdateQuery(l_paramsHead);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (expire繰越元注文)<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（先物注文繰越）expire繰越元注文」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト<BR>
     * @@return void
     * @@roseuid 40A85D25035D
     */
    public void expireCarryOverOriginOrder(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "expireCarryOverOrder()";
        log.entering(STR_METHOD_NAME);

        //1.1  getDataSourceObject( )(OrderUnit::getDataSourceObject)
        IfoOrderUnitParams l_orderUnitParams = (IfoOrderUnitParams)l_orderUnit.getDataSourceObject();

        //1.2 先物OP注文繰越更新インタセプタ(IfoOrderUnitParams, String)(先物OP注文繰越更新インタセプタ::先物OP注文繰越更新インタセプタ)
        //  [コンストラクタの引数]
        //  (繰越元)注文単位Params：　@（取得した行オブジェクト）
        //  注文エラー理由コード：　@0000（正常）
        WEB3IfoOrderCarryOverUpdateInterceptor l_interceptor =
            new WEB3IfoOrderCarryOverUpdateInterceptor(
                l_orderUnitParams,
                WEB3ErrorReasonCodeDef.NORMAL);

        //1.3 setThreadLocalPersistenceEventInterceptor(IfoOrderManagerPersistenceEventInterceptor)
        //  (先物注文マネージャ::setThreadLocalPersistenceEventInterceptor)
        //  [setThreadLocalPersistenceInterceptor()に指定する引数]
        //  インタセプタ：　@（生成した先物OP注文繰越更新インタセプタ）
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3FuturesOrderManagerImpl l_orderManager =
            (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        l_orderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);

        //1.4 getOrderId( )(OrderUnit::getOrderId)
        long l_lngOrderId = l_orderUnit.getOrderId();

        //1.5 expireOrder(long)(先物注文マネージャ::expireOrder)
        //  [expireOrder()に指定する引数]
        //  注文ＩＤ：　@注文単位.getOrderId()
        ProcessingResult l_processingResult = l_orderManager.expireOrder(l_lngOrderId);
        if (l_processingResult.isFailedResult())
        {
            ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
            WEB3BaseException l_baseException =
                new WEB3BaseException(
                    l_errorInfo,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_errorInfo.getErrorMessage());
            throw l_baseException;
        }

        //expireAll予約注文単位(long)
        //注文ＩＤ：　@注文単位.getOrderId()
        WEB3ToSuccReservationIfoOrderUpdateService l_service =
            (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                WEB3ToSuccReservationIfoOrderUpdateService.class);

        l_service.expireAllOrderUnit(l_lngOrderId);

        log.exiting(STR_METHOD_NAME);
    }
}
@
