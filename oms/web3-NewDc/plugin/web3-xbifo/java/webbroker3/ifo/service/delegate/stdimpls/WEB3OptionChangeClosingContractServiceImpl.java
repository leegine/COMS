head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionChangeClosingContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正返済サービス実装(WEB3OptionChangeClosingContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
              001: 2004/07/19 王暁傑 (中訊) バッグ修正
              002: 2004/07/23 王暁傑 (中訊) WEB3TransactionTypeSONARDefでWEB3IfoTransactionTypeDefを差し替える
              003: 2004/07/28 王暁傑  対応バッグ WEB3_IFO_UT-000039 WEB3_IFO_UT-000040
              004: 2004/07/30 王暁傑 対応バッグ WEB3_IFO_UT-000081、82、83
              005: 2004/07/30 王暁傑 対応バッグ WEB3_IFO_UT-0000120
              006: 2004/08/09 王暁傑 (Sinocom) 対応名称:【WEB3-XBIFO-A-CD-0082】
              007: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
              008: 2004/08/14 王暁傑 対応バグ BUG143
              009: 2004/08/14 王暁傑 対応 【株価指数オプション】ソースコードチェック指摘事項(JP)20040802
              010: 2004/08/15 王暁傑 対応バグ BUG83
              011: 2006/07/14 李　@俊　@(中訊)　@ 仕様変更　@モデル476
Revesion History : 2007/06/08 孫洪江 (中訊) 仕様変更モデルNo.659
Revesion History : 2007/06/21 趙林鵬 (中訊) 仕様変更モデルNo.740
Revesion History : 2007/11/20 何文敏 (中訊) 仕様変更モデルNo.807, No.815, No.819
Revesion History : 2008/04/14 張騰宇(中訊) モデル856
Revesion History : 2008/08/18 劉剣 (中訊) IFO小数点対応
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
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PayTypeDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionClientRequestService;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse;
import webbroker3.ifo.service.delegate.WEB3OptionChangeClosingContractService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (OP訂正返済サービスImpl)<BR>
 * 株価指数オプション訂正返済サービス実装クラス<BR>
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3OptionChangeClosingContractServiceImpl extends WEB3OptionClientRequestService
    implements WEB3OptionChangeClosingContractService
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionChangeClosingContractServiceImpl.class);

    /**
     * @@roseuid 40C0BAF000EA
     */
    public WEB3OptionChangeClosingContractServiceImpl()
    {

    }

    /**
     * 株価指数オプション訂正返済サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、<BR>submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056CCF702FB
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3OptionsCloseMarginChangeConfirmRequest)
        {
            WEB3OptionsCloseMarginChangeConfirmRequest l_confirmRequest =
                (WEB3OptionsCloseMarginChangeConfirmRequest)l_request;
            WEB3OptionsCloseMarginChangeConfirmResponse l_confirmResponse =
                this.validateOrder(l_confirmRequest);

            log.exiting(STR_METHOD_NAME);
            return l_confirmResponse;
        }
        else if (l_request instanceof WEB3OptionsCloseMarginChangeCompleteRequest)
        {
            WEB3OptionsCloseMarginChangeCompleteRequest l_completeRequest =
                (WEB3OptionsCloseMarginChangeCompleteRequest)l_request;
            WEB3OptionsCloseMarginChangeCompleteResponse l_completeResponse =
                this.submitOrder(l_completeRequest);

            log.exiting(STR_METHOD_NAME);
            return l_completeResponse;
        }
        else
        {
            log.error(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
    }

    /**
     * (validate注文)<BR>
     * 株価指数オプションの訂正返済発注審査を行う。<BR>
     * <BR>
     * 「（OP訂正返済サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション訂正返済確認リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056CD2E0193
     */
    protected WEB3OptionsCloseMarginChangeConfirmResponse validateOrder(WEB3OptionsCloseMarginChangeConfirmRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder()";
        log.entering(STR_METHOD_NAME);

        log.debug("シーケンス図「（OP訂正返済サービス）validate注文１」参照");
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
        try
        {
            //1.1 validate()
            l_request.validate();

            //1.3 getOrderUnits( )(IfoOrderImpl::getOrderUnits)
            OrderUnit l_orderUnit = l_orderManager.getOrderUnits(Long.parseLong(l_request.id))[0];

            //1.2 IfoOrderImpl(IfoOrderRow)
            IfoOrderUnitRow l_ifoOrderUnitRow = 
                (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            
            //1.4 create返済建玉エントリ(long, double, 返済建玉[])(OP注文マネージャ::create返済建玉エントリ)
            //  [create返済建玉エントリ()に指定する引数]
            //  注文単位ID = （取得した注文単位.getOrderUnitId()）
            //  注文数量 = リクエストデータ.注文数量
            //  建玉ID[] = リクエストデータ.返済建玉[]
            long l_lngOrderUnitId  = l_orderUnit.getOrderUnitId();
            //  注文単位.getDateSourceObject().getClosingOrder() != 0（ランダム）
            //  and (リクエストデータ.注文数量 == 0 or リクエストデータ.注文数量 == null)
            //  の場合、例外をスローする。
            double l_dblOrderQuantity = 0D;
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                if (l_request.opOrderQuantity == null || Double.parseDouble(l_request.opOrderQuantity) == 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                        STR_METHOD_NAME);
                }
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);
            }
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngOrderUnitId,
                    l_dblOrderQuantity,
                    l_request.closeMarginContractUnits);

            //1.5  get発注日( )(取引時間管理::get発注日)
            Date l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.6 create返済訂正内容(long, long, double, SettleContractEntry[], 
            //      IfoOrderExecutionConditionType, Date, Date, String,
            //      String, String, double, double, IfoOrderExecutionConditionType, 
            //      String, String, boolean)
			//注文ID：　@注文単位.注文ID 
			//注文単位ID：　@注文単位.注文単位ID 
			//訂正指値：　@リクエストデータ.注文単価 
			//返済建玉エントリ[]：　@create返済建玉エントリの戻り値 
			//訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値 

            long l_lngOrderId = l_orderUnit.getOrderId();
            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // 訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
            // OP注文マネージャ
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            Date l_changeExpirationDate = null;
            // リクエストデータ.注文有効期限 != nullの場合
            if (l_request.expirationDate != null)
            {
                // 先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                Market l_market = null; 
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // 先物OP銘柄(*1).銘柄コード
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // 市場.getMarketCode()
                l_market = l_ifoProductImpl.getPrimaryMarket();
                String l_strMarketCode = l_market.getMarketCode();

                // 訂正失効日：
                l_changeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            // リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値
            else
            {
                l_changeExpirationDate = l_orderBizDate;
            }
			//発注条件：　@リクエストデータ.発注条件区分
            String l_strOrderCond = l_request.orderCondType;
			//発注条件演算子：　@ 
			//　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件演算子  
			//　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件演算子 
			//逆指値基準値タイプ：　@ 
			//　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用プレミアム／原資産価格 
			//　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用プレミアム／原資産価格 
			//逆指値基準値：　@ 
			//　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件単価 
			//　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件単価 
			//発注条件演算子：　@ 
            String l_strOrderCondOperator = null;
            //逆指値基準値タイプ：
            String l_strStopOrderBasePriceType = null;
            //逆指値基準値：
            double l_dblStopOrderBasePrice = 0.0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.stopPremium_underlyingAssets;
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {                
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.wlimitPremium_underlyingAssets;
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                }
            }
            
			//（W指値）訂正指値：　@リクエストデータ.W指値用注文単価 
            double l_dblWLimitPriceChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPriceChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
			//（W指値）執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.Ｗ指値用執行条件) 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
			
            //（W指値）有効状態区分：　@リクエストデータ.Ｗ指値用有効状態区分
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
			//訂正後注文期限区分：　@リクエストデータ.注文期限区分                
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象フラグ(
            //リクエストデータ.注文期限区分、注文単位.部店ID)
            boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_orderUnit.getBranchId());
            
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                 WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                       l_lngOrderId,
                       l_lngOrderUnitId,
                       l_dblLimitPrice,
                       l_eqOrderEntry,
                       l_changeExecCondType,
                       l_changeExpirationDate,
                       l_orderBizDate,
                       l_strOrderCond,
                       l_strOrderCondOperator,
                       l_strStopOrderBasePriceType,
                       l_dblStopOrderBasePrice,
                       l_dblWLimitPriceChange,
                       l_wLimitExecCondType,
                       l_strWLimitEnableStatusDiv,
                       l_strExpirationDateType,
                       l_blnEveningSessionCarryoverFlag);

            //1.7 get補助口座( )(OP訂正返済サービスImpl::get補助口座)
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();
            
            //1.8 validate返済訂正注文(SubAccount, IfoChangeSettleContractOrderSpec)(OP注文マネージャ::validate返済訂正注文)
            OrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(
                l_subAccount, 
                l_ifoOrderSpec);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }

            //1.9 getAfterChangeTotalQuantity( )(返済訂正内容::getAfterChangeTotalQuantity)
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            if (Double.isNaN(l_dblTotalQuantity))
            {
                l_dblTotalQuantity = 0D;
            }

            //1.10 先物OP建玉(long)(先物OP建玉::先物OP建玉)
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);

            //1.11 getTradedProduct( )(先物OP建玉::getTradedProduct)
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();

            //1.12　@時価情報を取得する｡
            WEB3IfoProductQuote l_currentInfo = l_ifoTradedProductImpl.getCurrentInfo(null);

            //1.13  手数料( )(手数料::手数料)
            WEB3GentradeCommission l_ifoGentradeCommission = new WEB3GentradeCommission();

            //1.14  プロパティセット(*1)
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;

            l_ifoGentradeCommission.setOrderChannel(this.getLoginChannel());
            String l_strInstitutionCode = String.valueOf(l_subAccountRow.getInstitutionCode());
            l_ifoGentradeCommission.setInstitutionCode(l_strInstitutionCode);
            l_ifoGentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            l_ifoGentradeCommission.setOrderBizDate(new Timestamp(l_orderBizDate.getTime()));
            l_ifoGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            l_ifoGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            //弁済区分をセットする。
            l_ifoGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnIsLimitPrice = false;
            }
            else
            {
                l_blnIsLimitPrice = true;
            }
            l_ifoGentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            l_ifoGentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            l_ifoGentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            l_ifoGentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            
            //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
            l_ifoGentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());
            
            //手数料.日計り区分 = 注文単位.日計り区分
            l_ifoGentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());
            
            //手数料.数量 = 返済訂正内容.getAfterChangeTotalQuantity()
            l_ifoGentradeCommission.setQuantity(l_dblTotalQuantity);

            //1.15 getSide( )(IfoBaseOrderUnitImpl::getSide)
            SideEnum l_side = l_orderUnit.getSide();

            //1.16 getExecutedQuantity( )(IfoBaseOrderUnitImpl::getExecutedQuantity)
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }

            //1.17 getExecutedAmount( )(IfoBaseOrderUnitImpl::getExecutedAmount)
            double l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;
            }

            //1.18 calc訂正時概算受渡代金(手数料, double, SubAccount, 先物OP取引銘柄, double, SideEnum, boolean, double, double, boolean)
            //  (OP注文マネージャ::calc訂正時概算受渡代金)
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
            l_estimateDeliveryAmountCalcResult =
                l_orderManager.calcChangeEstimateDeliveryAmount(
                    l_ifoGentradeCommission,
                    l_dblLimitPrice,
                    (WEB3GentradeSubAccount)l_subAccount,
                    l_ifoTradedProductImpl,
                    l_dblTotalQuantity,
                    l_side,
                    true,
                    l_dblExecutedQuantity,
                    l_dblExecutedAmount,
                    false);
            //1.19 （OP訂正返済サービス）validate注文２（参照）        
            //1.2 createResponse( )(株価指数オプション訂正返済確認リクエスト::createResponse)            
            WEB3OptionsCloseMarginChangeConfirmResponse l_response =
                (WEB3OptionsCloseMarginChangeConfirmResponse)l_request.createResponse();
            
            WEB3FuturesOptionsContractUnit l_contractUnit = null;
            WEB3IfoContractImpl l_ifoContract = null;
            ArrayList l_list = new ArrayList();
            for (int i = 0; i < l_eqOrderEntry.length; i++)
            {
                //1.3 建玉明細( )(建玉明細::建玉明細)
                l_contractUnit = new WEB3FuturesOptionsContractUnit();

                //1.4 getContractId( )(SettleContractEntry::getContractId)
                long l_lngcontractId = l_eqOrderEntry[i].getContractId();

                //1.5 getQuantity( )(SettleContractEntry::getQuantity)
                double l_dblQuantity = l_eqOrderEntry[i].getQuantity();
                if (Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0D;
                }

                //1.6 先物OP建玉(long)(先物OP建玉::先物OP建玉)
                l_ifoContract = new WEB3IfoContractImpl(l_lngcontractId);

                //1.7 getOpenDate( )(先物OP建玉::getOpenDate)
                Date l_datOpenDate = l_ifoContract.getOpenDate();

                //1.8 getContractPrice( )(先物OP建玉::getContractPrice)
                double l_dblContractPrice = l_ifoContract.getContractPrice();
                if (Double.isNaN(l_dblContractPrice))
                {
                    l_dblContractPrice = 0D;
                }

                //1.9 get建約定代金(double)(先物OP建玉::get建約定代金)
                double l_dblContractExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblQuantity);
                if (Double.isNaN(l_dblContractExecutedAmount))
                {
                    l_dblContractExecutedAmount = 0D;
                }
                
                //get建手数料() + get建手数料消費税()
                BigDecimal l_bdContractCommission = new BigDecimal(
                    l_ifoContract.getContractCommission(l_dblQuantity, l_lngOrderUnitId) + "");
                BigDecimal l_bdContractCommissionConsumptionTax = new BigDecimal(
                    l_ifoContract.getContractCommissionConsumptionTax(l_dblQuantity, l_lngOrderUnitId) + "");
                double l_dblContractCommission =
                    l_bdContractCommission.add(l_bdContractCommissionConsumptionTax).doubleValue();

                //時価を取得する。
                double l_dblPrice = 0D;
                if(l_currentInfo != null)
                {
                    l_dblPrice = l_currentInfo.getCurrentPrice();
                }

                //1.10 get評価損益(double, double)(先物OP建玉::get評価損益)
                double l_dblIncome = l_ifoContract.getEvaluateIncome(l_dblPrice,l_dblQuantity);
                if (Double.isNaN(l_dblIncome))
                {
                    l_dblIncome = 0D;
                }

                //1.11  getQuantity( )(先物OP建玉::getQuantity)
                double l_dblGetQuantity = l_ifoContract.getQuantity();
                if (Double.isNaN(l_dblGetQuantity))
                {
                    l_dblGetQuantity = 0D;
                }

                //1.12  get返済約定済数量(long)(先物OP建玉::get返済約定済数量)
                double l_dblClosingExecuteContractCnt = l_ifoContract.getClosingExecuteContractCnt(l_orderUnit.getOrderUnitId());

                //1.13  (*2)プロパティセット
                l_contractUnit.id                    = String.valueOf(l_lngcontractId);
                l_contractUnit.openDate              = WEB3DateUtility.toDay(l_datOpenDate);
                l_contractUnit.contractPrice         = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_contractUnit.contractQuantity      = WEB3StringTypeUtility.formatNumber(l_dblGetQuantity);
                l_contractUnit.contractExecPrice     = WEB3StringTypeUtility.formatNumber(l_dblContractExecutedAmount);
                l_contractUnit.contractCommission    = WEB3StringTypeUtility.formatNumber(l_dblContractCommission);
                l_contractUnit.income                = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                l_contractUnit.incomeCost            = WEB3StringTypeUtility.formatNumber(
                    new BigDecimal(l_dblIncome + "").subtract(new BigDecimal(l_dblContractCommission + "")).doubleValue());
                l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                l_contractUnit.contractExecQuantity  = WEB3StringTypeUtility.formatNumber(l_dblClosingExecuteContractCnt);
                l_contractUnit.settlePriority        = String.valueOf(i + 1);
                //建玉明細.立会区分 = 建玉.立会区分
                l_contractUnit.sessionType =
                    ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();
                
                l_list.add(l_contractUnit);
            }

            WEB3FuturesOptionsContractUnit[] l_ContractUnitArray = (WEB3FuturesOptionsContractUnit[])l_list.toArray(new WEB3FuturesOptionsContractUnit[l_list.size()]);

            //1.14 getProduct( )(先物OP建玉::getProduct)
            l_ifoContractImpl.getProduct();

            //1.15 get市場閉局警告指数(部店, String)(取引時間管理::get市場閉局警告指数)
            WEB3GentradeBranch l_gentradeBranch = null;

            // throws NotFoundException
            l_gentradeBranch = (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();

            String[] l_strWEB3GentradeTradingTimeManagement =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    WEB3FuturesOptionDivDef.OPTION);

            //1.16 get概算受渡代金( )(先物OP概算受渡代金計算結果::get概算受渡代金)
            double l_dblEstimateDeliveryAmount =l_estimateDeliveryAmountCalcResult.getEstimateDeliveryAmount();
            if (Double.isNaN(l_dblEstimateDeliveryAmount))
            {
                l_dblEstimateDeliveryAmount = 0D;
            }
            double l_dblCalcUnitPrice = l_estimateDeliveryAmountCalcResult.getCalcUnitPrice();
            if (Double.isNaN(l_dblCalcUnitPrice))
            {
                l_dblCalcUnitPrice = 0D;
            }

            //1.17 get手数料コース( )(先物OP概算受渡代金計算結果::get手数料コース)
            String l_strCommissionCourse = l_estimateDeliveryAmountCalcResult.getCommissionCourse();
            
            //1.18 get手数料( )(先物OP概算受渡代金計算結果::get手数料)
            double l_dblCommission = l_estimateDeliveryAmountCalcResult.getCommission();

            //1.19 get手数料消費税( )(先物OP概算受渡代金計算結果::get手数料消費税)
            double l_dblCommissionTax = l_estimateDeliveryAmountCalcResult.getCommissionTax();

            //1.20  (*3)プロパティセット
            l_response.contractUnits = l_ContractUnitArray;
            l_response.estimatedPrice = WEB3StringTypeUtility.formatNumber(l_dblEstimateDeliveryAmount);
            l_response.commissionCourse = l_strCommissionCourse;
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_dblCommission);
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblCommissionTax);
            l_response.messageSuspension = l_strWEB3GentradeTradingTimeManagement;
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
            l_response.checkDate = WEB3DateUtility.toDay(l_orderBizDate);
            // レスポンス.注文有効期限 = 返済訂正内容.訂正失効日
            l_response.expirationDate = l_ifoOrderSpec.getChangeExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (submit注文)<BR>
     * 株価指数オプションの訂正返済注文を登録する。<BR>
     * <BR>
     * 「（OP訂正返済サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション訂正返済完了リクエスト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4056CD2E01B3
     */
    protected WEB3OptionsCloseMarginChangeCompleteResponse submitOrder(WEB3OptionsCloseMarginChangeCompleteRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrder()";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        try
        {
            //1.1  validate
            l_request.validate();

            //1.2 IfoOrderImpl
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            OrderUnit l_orderUnit = l_orderManager.getOrderUnits(Long.parseLong(l_request.id))[0];

            //1.3 getOrderUnits
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //1.4 create返済建玉エントリ(long, double, 返済建玉[])(OP注文マネージャ::create返済建玉エントリ)
            //  [create返済建玉エントリ()に指定する引数]
            //  注文単位ID = （取得した注文単位.getOrderUnitId()）
            //  注文数量 = リクエストデータ.注文数量
            //  建玉ID[] = リクエストデータ.返済建玉[]
            long l_lngOrderId      = l_orderUnit.getOrderId();
            long l_lngOrderUnitId  = l_orderUnit.getOrderUnitId();
            double l_dblOrderQuantity = 0D;
            if (l_request.opOrderQuantity != null)
            {
                l_dblOrderQuantity = Double.parseDouble(l_request.opOrderQuantity);                
            }
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngOrderUnitId,
                    l_dblOrderQuantity,
                    l_request.closeMarginContractUnits);

            //1.5  get発注日( )(取引時間管理::get発注日)
            Date l_orderBizDate = null;
            if (l_request.checkDate == null)
            {
                l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_orderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            
            //1.6 create返済訂正内容(long, long, double, SettleContractEntry[], 
            //      IfoOrderExecutionConditionType, Date, Date, String,
            //      String, String, double, double, IfoOrderExecutionConditionType, 
            //      String, String, boolean)
			//注文ID：　@注文単位.注文ID 
			//注文単位ID：　@注文単位.注文単位ID 
			//訂正指値：　@リクエストデータ.注文単価 
			//返済建玉エントリ[]：　@create返済建玉エントリの戻り値 
			//訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値 

            double l_dblLimitPrice = 0.0D;
            if (l_request.limitPrice != null)
            {
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
            }
            
            // 訂正執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.執行条件)の戻り値 
            IfoOrderExecutionConditionType l_changeExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            // OP注文マネージャ
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = 
                (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(
                    ProductTypeEnum.IFO).getOrderManager();

            Date l_changeExpirationDate = null;
            // リクエストデータ.注文有効期限 != nullの場合
            if (l_request.expirationDate != null)
            {
                // 先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
                TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
                WEB3IfoProductManagerImpl l_productMgr =
                    (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
                Product l_product = null;
                Market l_market = null; 
                try
                {
                    l_product = l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_product;
                // 先物OP銘柄(*1).銘柄コード
                String l_strProductCode = l_ifoProductImpl.getProductCode();
                // 市場.getMarketCode()
                l_market = l_ifoProductImpl.getPrimaryMarket();
                String l_strMarketCode = l_market.getMarketCode();

                // 訂正失効日：
                l_changeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_strProductCode,
                    l_strMarketCode,
                    WEB3FuturesOptionDivDef.OPTION);
            }
            // リクエストデータ.注文有効期限 == nullの場合
            else
            {
                l_changeExpirationDate = l_orderBizDate;
            }
			//発注条件：　@リクエストデータ.発注条件区分
            String l_strOrderCond = l_request.orderCondType;
			//発注条件演算子：　@ 
			//　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件演算子  
			//　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件演算子 
			//逆指値基準値タイプ：　@ 
			//　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用プレミアム／原資産価格 
			//　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用プレミアム／原資産価格 
			//逆指値基準値：　@ 
			//　@リクエストデータ.発注条件区分 == ”逆指値”の場合、リクエストデータ.逆指値用発注条件単価 
			//　@リクエストデータ.発注条件区分 == ”W指値”の場合、リクエストデータ.W指値用発注条件単価 
			//発注条件演算子：　@ 
            String l_strOrderCondOperator = null;
            //逆指値基準値タイプ：
            String l_strStopOrderBasePriceType = null;
            //逆指値基準値：
            double l_dblStopOrderBasePrice = 0.0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.stopPremium_underlyingAssets;
                if (l_request.stopOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.stopOrderCondPrice);
                }
            }
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType ))
            {                
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_strStopOrderBasePriceType = l_request.wlimitPremium_underlyingAssets;
                if (l_request.wlimitOrderCondPrice != null)
                {
                    l_dblStopOrderBasePrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
                }
            }
            
			//（W指値）訂正指値：　@リクエストデータ.W指値用注文単価 
            double l_dblWLimitPriceChange = 0.0D;
            if (l_request.wLimitPrice != null)
            {
                l_dblWLimitPriceChange = Double.parseDouble(l_request.wLimitPrice);
            }
            
			//（W指値）執行条件：　@先物OPデータアダプタ.get執行条件(リクエストデータ.Ｗ指値用執行条件) 
            IfoOrderExecutionConditionType l_wLimitExecCondType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
			
            //（W指値）有効状態区分：　@リクエストデータ.Ｗ指値用有効状態区分
            String l_strWLimitEnableStatusDiv = l_request.wlimitEnableStatusDiv;
            
			//訂正後注文期限区分：　@リクエストデータ.注文期限区分                
            String l_strExpirationDateType = l_request.expirationDateType;
            
            //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前繰越対象フラグ(
            //リクエストデータ.注文期限区分、注文単位.部店ID)
            boolean l_blnEveningSessionCarryoverFlag = WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                l_request.expirationDateType,
                l_orderUnit.getBranchId());
            
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                 WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                       l_lngOrderId,
                       l_lngOrderUnitId,
                       l_dblLimitPrice,
                       l_eqOrderEntry,
                       l_changeExecCondType,
                       l_changeExpirationDate,
                       l_orderBizDate,
                       l_strOrderCond,
                       l_strOrderCondOperator,
                       l_strStopOrderBasePriceType,
                       l_dblStopOrderBasePrice,
                       l_dblWLimitPriceChange,
                       l_wLimitExecCondType,
                       l_strWLimitEnableStatusDiv,
                       l_strExpirationDateType,
                       l_blnEveningSessionCarryoverFlag);
            //1.6 get補助口座
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            //1.7 validate返済訂正注文
            OrderValidationResult l_result = l_orderManager.validateChangeSettleContractOrder(l_subAccount,l_ifoOrderSpec);

            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    STR_METHOD_NAME);
            }
            
            //1.8 getAfterChangeTotalQuantity
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            if (Double.isNaN(l_dblTotalQuantity))
            {
                l_dblTotalQuantity = 0D;
            }
            
            //1.9 先物OP建玉
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);

            //1.10 getTradedProduct
            l_ifoContractImpl.getTradedProduct();

            //1.11 手数料
            WEB3GentradeCommission l_ifoGentradeCommission = new WEB3GentradeCommission();

            //1.12 プロパティセット
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;
            l_ifoGentradeCommission.setOrderChannel(this.getLoginChannel());
            l_ifoGentradeCommission.setInstitutionCode(l_subAccountRow.getInstitutionCode());
            l_ifoGentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //手数料.発注日 = 取引時間管理.get発注日()
            l_ifoGentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            l_ifoGentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            l_ifoGentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_OP);
            //弁済区分をセットする。
            l_ifoGentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                l_blnIsLimitPrice = false;
            }
            else
            {
                l_blnIsLimitPrice = true;
            }
            l_ifoGentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            l_ifoGentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            l_ifoGentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            l_ifoGentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            
            //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
            l_ifoGentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());
            
            //手数料.日計り区分 = 注文単位.日計り区分
            l_ifoGentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());
            
            //手数料.数量 = 返済訂正内容.getAfterChangeTotalQuantity()
            l_ifoGentradeCommission.setQuantity(l_dblTotalQuantity);
            
            //1.13 getSide
            SideEnum l_side = l_orderUnit.getSide();

            //1.14 getExecutedQuantity
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }
            
            //1.15 getExecutedAmount
            double l_dblExecutedAmount = l_orderUnit.getExecutedAmount();
            if (Double.isNaN(l_dblExecutedAmount))
            {
                l_dblExecutedAmount = 0D;
            }

            //1.16 calc訂正時概算受渡代金 
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = (WEB3IfoTradedProductImpl)l_orderUnit.getTradedProduct();
            double l_dblPrice = 0D;
            if (l_request.checkPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_request.checkPrice); 
            }
            else
            {
                if (l_request.limitPrice != null)
                {
                    l_dblPrice = Double.parseDouble(l_request.limitPrice); 
                }
            }
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = null;
            l_estimateDeliveryAmountCalcResult = l_orderManager.calcChangeEstimateDeliveryAmount(
                l_ifoGentradeCommission,
                l_dblPrice,
                (WEB3GentradeSubAccount)l_subAccount,
                l_ifoTradedProductImpl,
                l_dblTotalQuantity,
                l_side,
                true,
                l_dblExecutedQuantity,
                l_dblExecutedAmount,
                false);
            
            //1.17 （OP訂正返済サービス）submit注文２（参照）

            //1.3 先物OP返済訂正更新インタセプタ
            WEB3IfoSettleContractChangeUpdateInterceptor l_ifoUpdateInterceptor = 
                new WEB3IfoSettleContractChangeUpdateInterceptor(l_ifoOrderSpec);

            //1.4 (*1)プロパティセット
            l_ifoUpdateInterceptor.setCommision(l_ifoGentradeCommission);
            l_ifoUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_estimateDeliveryAmountCalcResult);
            l_ifoUpdateInterceptor.setOrderCond(l_request.orderCondType);
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePriceType(l_request.stopPremium_underlyingAssets);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePriceType(l_request.wlimitPremium_underlyingAssets);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            l_ifoUpdateInterceptor.setWLimitPriceChange(l_ifoOrderSpec.getWLimitPriceChange());
            Trader l_trader = this.getTrader();   
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_ifoUpdateInterceptor.setTraderId(l_lngTraderId);

			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_ifoUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);

            //1.5 setThreadLocalPersistenceEventInterceptor
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoUpdateInterceptor);

            //1.6 submitChangeSettleContractOrder
            log.debug("submitデータベース 開始");
            OrderSubmissionResult l_orderSubmissionResult =
                l_orderManager.submitChangeSettleContractOrder(
                    l_subAccount, 
                    l_ifoOrderSpec, 
                    l_request.password, 
                    true);
            if (l_orderSubmissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            log.debug("submitデータベース 終了");

            if (l_orderSubmissionResult.getProcessingResult().isSuccessfulResult())
            {
                if (!SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccount.getSubAccountType()))
                {
                    //1.7  余力再計算
                    WEB3TPTradingPowerService l_tradingPowerService = 
                        (WEB3TPTradingPowerService)Services.getService(
                            WEB3TPTradingPowerService.class);
                    l_tradingPowerService.reCalcTradingPower((WEB3GentradeSubAccount)l_subAccount);
                }

                //is予約注文確認要(IfoOrderUnit)
                boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist((IfoOrderUnit)l_orderUnit);

                //予約注文確認要（is予約注文確認要() == true）の場合
                List l_lisOpenReserveIfoOrderUnits = null;
                if (l_blnIsReserveOrderExist)
                {
                    //get有効予約注文単位一覧(親注文の注文ID : long)
                    WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                        (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                            WEB3ToSuccReservationIfoOrderUpdateService.class);
                    l_lisOpenReserveIfoOrderUnits =
                        l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(((IfoOrderUnit)l_orderUnit).getOrderId());
                }

                //1.8  WEB3GenResponse
                WEB3GenResponse l_genResponse = l_request.createResponse();
                WEB3OptionsCloseMarginChangeCompleteResponse l_response = (WEB3OptionsCloseMarginChangeCompleteResponse)l_genResponse;

                //1.9 (*2) プロパティセット
                l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
                l_response.orderActionId = l_request.id;
                if (l_lisOpenReserveIfoOrderUnits != null)
                {
                    l_response.succSettingFlag = true;
                }
                else
                {
                    l_response.succSettingFlag = false;
                }
                log.debug("l_response.lastUpdateTimestamp = " + l_response.lastUpdatedTimestamp);
                log.debug("l_response.orderActionId = "+ l_response.orderActionId);

                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
            else
            {
                log.debug("ProcessingResult() = " + l_orderSubmissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_orderSubmissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }
}
@
