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
filename	WEB3FuturesChangeClosingContractServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正返済サービス実装(WEB3FuturesChangeClosingContractServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/26 李媛 (中訊) 新規作成
                 : 2006/08/01 肖志偉 (中訊) 仕様変更No.489
                 : 2006/08/10 肖志偉 (中訊) 仕様変更No.545                  
Revesion History : 2007/06/21 張騰宇(中訊) モデル709 742
Revesion History : 2007/11/20 趙林鵬 (中訊)仕様変更 モデル811
Revesion History : 2007/11/28 趙林鵬 (中訊)Javaソース（基本設計と合っていない実装）007
Revesion History : 2008/03/17 張騰宇(中訊) モデル855
Revesion History : 2008/08/18 安陽(中訊) IFO小数点対応
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
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
import webbroker3.common.define.WEB3StopBasePriceTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3FuturesClientRequestService;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoProductQuote;
import webbroker3.ifo.WEB3IfoSettleContractChangeUpdateInterceptor;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesChangeClosingContractService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (先物訂正返済サービスImpl)<BR>
 * 株価指数先物訂正返済サービス実装クラス
 *
 * @@author 　@李媛
 * @@version 1.0
 */
public class WEB3FuturesChangeClosingContractServiceImpl extends WEB3FuturesClientRequestService implements WEB3FuturesChangeClosingContractService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesChangeClosingContractServiceImpl.class);

    /**
     * @@roseuid 40F7A2CF0177
     */
    public WEB3FuturesChangeClosingContractServiceImpl()
    {

    }

    /**
     * 株価指数先物訂正返済サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()または、<BR>
     * submit注文()メソッドをコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8B0BD01DE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request instanceof WEB3FuturesCloseMarginChangeConfirmRequest)
        {
            WEB3FuturesCloseMarginChangeConfirmRequest l_futureConfirmRequest =
                (WEB3FuturesCloseMarginChangeConfirmRequest)l_request;
            WEB3FuturesCloseMarginChangeConfirmResponse l_futurethisConfirmResponse =
                this.validateOrder(l_futureConfirmRequest);
            log.exiting(STR_METHOD_NAME);
            return l_futurethisConfirmResponse;
        }
        else if (l_request instanceof WEB3FuturesCloseMarginChangeCompleteRequest)
        {
            WEB3FuturesCloseMarginChangeCompleteRequest l_futuresCompleteRequest =
                (WEB3FuturesCloseMarginChangeCompleteRequest)l_request;
            WEB3FuturesCloseMarginChangeCompleteResponse l_futuresCompleteResponse =
                this.submitOrder(l_futuresCompleteRequest);
            log.exiting(STR_METHOD_NAME);
            return l_futuresCompleteResponse;
        }
        else
        {
            log.error(STR_METHOD_NAME + " __Error[入力値が不正です]__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                STR_METHOD_NAME);
        }
    }

    /**
     * (validate注文)<BR>
     * 株価指数先物の訂正返済発注審査を行う。<BR>
     * <BR>
     * 「（先物訂正返済サービス）validate注文」参照。<BR>
     * ==========================================================<BR>
     * シーケンス図(「（先物訂正返済サービス）validate注文」): １.4 getOrderUnits( )<BR>
     * 注文単位.getDateSourceObject().getClosingOrder() == 0（ランダム）<BR>
     * and (リクエストデータ.注文数量 == 0 or リクエストデータ.注文数量 == null)<BR>
     * の場合、例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00180<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)株価指数先物訂正返済確認リクエスト<BR>
     * @@return WEB3FuturesCloseMarginChangeConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8B0BD01FD
     */
    protected WEB3FuturesCloseMarginChangeConfirmResponse validateOrder(WEB3FuturesCloseMarginChangeConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateOrder(WEB3FuturesCloseMarginChangeConfirmRequest)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //1.1 リクエストデータの整合性をチェックする。
            l_request.validate();

            //1.2 注文オブジェクトを取得する。
            long l_lngID = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = new IfoOrderImpl(l_lngID);

            //1.3 注文単位を取得する。 （当該メソッドで取得した配列の0番目の要素）
            OrderUnit[] l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();

            //注文数量を取得する。            
            double l_dblOrderQuantity = 0D;
            //注文単位.getDataSourceObject().getClosingOrder()!=0 and リクエストデータ.注文数量==null or 0
            //例外をスーロする。
            if (!WEB3ClosingOrderDef.RANDOM.equals(l_ifoOrderUnitRow.getClosingOrder()))
            {
                if (l_request.futOrderQuantity == null || "0".equals(l_request.futOrderQuantity))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                        STR_METHOD_NAME);
                }
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }

            //1.4 決済順位でソートし、返済建玉エントリの配列を作成する。
            //  [create返済建玉エントリ()に指定する引数]
            //  注文単位ID： 注文単位.getOrderUnitId()
            //  注文数量： リクエストデータ.注文数量
            //  返済建玉[]： リクエストデータ.返済建玉[]
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            //注文単位IDを取得する。
            long l_lngOrderUnitId  = l_orderUnit.getOrderUnitId();
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngOrderUnitId,
                    l_dblOrderQuantity,
                    l_request.closeMarginContractUnits);

            //1.5 発注日を取得する。
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //OP注文マネージャ
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            //先物OP銘柄
            //(*1)先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl = null;
            try
            {
                l_ifoProductImpl =
                    (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //市場
            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //訂正失効日：
            //　@リクエストデータ.注文有効期限 != nullの場合、OP注文マネージャ.get注文有効期限(
            //リクエストデータ.注文有効期限,先物OP銘柄(*1).銘柄コード,市場.getMarketCode(),”先物”)の戻り値
            //リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate != null)
            {
                l_datChangeExpirationDate = l_optionOrderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            else 
            {
                l_datChangeExpirationDate = WEB3DateUtility.toDay(l_datOrderBizDate);
            }
            
            //発注条件演算子
            String l_strOrderCondOperator = null;
            
            //逆指値基準値
            double l_dblStopOrderPrice = 0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            
            //注文単価を取得する。            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //成行
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice = false;
            }
            else
            {
                //指値
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice = true;
            }
            
            //訂正執行条件
            IfoOrderExecutionConditionType l_ifoOrderExecutionConditionType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);
            
            //（W指値）訂正指値：　@リクエストデータ.W指値用注文単価 。
            double l_dblWLimitPrice = 0D;
           if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
                && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {                
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            else
            {
                l_dblWLimitPrice = 0D;
            } 
            
            //(W指値)執行条件
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前
            //繰越対象フラグ(リクエストデータ.注文期限区分, 注文単位.部店ID)の戻り値
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    l_request.expirationDateType,
                    l_ifoOrderUnitRow.getBranchId());
            //1.6 返済訂正内容を作成する。 
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_ifoOrderUnitRow.getOrderId(),
                    l_ifoOrderUnitRow.getOrderUnitId(),
                    l_dblLimitPrice,
                    l_eqOrderEntry,
                    l_ifoOrderExecutionConditionType,
                    l_datChangeExpirationDate,
                    l_datOrderBizDate,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_request.wlimitEnableStatusDiv,
                    l_request.expirationDateType,
                    l_blnEveningSessionCarryOverFlag);
                       
            //1.7 補助口座を取得する。
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            //1.8 先物返済訂正注文の発注審査を実施する。
            OrderValidationResult l_result =
                l_orderManager.validateFuturesChangeSettleContractOrder(l_subAccount, l_ifoOrderSpec);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.9 建玉の合計数量を取得する。
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            log.debug("建玉の合計数量を取得しました。" + l_dblTotalQuantity);

            //1.10 建玉オブジェクトを取得する。
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);

            //1.11 先物OP取引銘柄オブジェクトを取得する。
            WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();
            log.debug("19. 先物OP取引銘柄オブジェクトを取得しました。銘柄ID = " + l_tradedProduct.getTradedProductId());

            //1.12　@時価情報を取得する｡
            WEB3IfoProductQuote l_currentInfo = l_tradedProduct.getCurrentInfo(null);

            //1.13 手数料オブジェクトを生成する。
            WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();

            //1.14 プロパティセット(*1)
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;
            //手数料.注文チャネルをセット
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            log.debug("手数料.注文チャネル = " + this.getLoginChannel());
            //手数料.証券会社コートをセットする。
            String l_strInstitutionCode = l_subAccountRow.getInstitutionCode();
            l_gentradeCommission.setInstitutionCode(l_strInstitutionCode);
            log.debug("手数料.証券会社コート = " + l_strInstitutionCode);
            //手数料.部店IDをセットする。
            l_gentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            log.debug("手数料.部店ID = " + l_subAccountRow.getBranchId());
            //手数料.発注日をセットする。
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_datOrderBizDate.getTime()));
            log.debug("手数料.発注日 = " + l_datOrderBizDate);
            //手数料.取引コード（SONAR）をセットする。
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            log.debug("手数料.取引コード（SONAR） = " + WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            //手数料.手数料商品コードをセットする。
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("手数料.手数料商品コード = " + WEB3CommisionProductCodeDef.INDEX_FUTURES);
            //手数料.弁済区分をセットする。
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            log.debug("手数料.弁済区分 = " + WEB3PayTypeDef.OTHER);
            //手数料.原注文チャネルをセットする。
            l_gentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            log.debug("手数料.原注文チャネル = " + l_ifoOrderUnitRow.getOrderChanel());
            //手数料.原注文手数料NOをセットする。
            l_gentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            log.debug("手数料.原注文手数料NO = " + l_ifoOrderUnitRow.getCommTblNo());
            //手数料.原注文手数料NO.枝番をセットする。
            l_gentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            log.debug("手数料.原注文手数料NO.枝番 = " + l_ifoOrderUnitRow.getCommTblSubNo());
            //手数料.is指値をセットする。
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("手数料.is指値 = " + l_gentradeCommission.isLimitPrice());
            
            //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());
            
            //手数料.日計り区分 = 注文単位.日計り区分
            l_gentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());
            
            //手数料.数量 = 返済訂正内容.getAfterChangeTotalQuantity()
            l_gentradeCommission.setQuantity(l_dblTotalQuantity);
            
            
            //1.15 売買（SideEnum定義値）を取得する。
            SideEnum l_side = null;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            log.debug("売買（SideEnum定義値）を取得しました。=" + l_side);

            //1.16 合計約定数量を取得する。
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if(Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0;
            }
            
            //1.17 calc訂正時概算決済損益(手数料, double, SubAccount, 先物OP取引銘柄, SettleContractEntry[], double, SideEnum, double, long, boolean)
            //  [calc訂正時概算決済損益()に指定する引数]
            //  手数料：　@手数料オブジェクト
            //  指値：　@リクエストデータ.注文単価
            //  補助口座：　@補助口座オブジェクト
            //  先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
            //  返済建玉エントリ[]：  create返済建玉エントリの戻り値
            //  数量： 返済訂正内容.getAfterChangeTotalQuantity()
            //  売買： （以下のとおり）
            //      注文単位.getSide() = ”買”の場合、”売”
            //      注文単位.getSide() = ”売”の場合、”買”
            //  約定数量：　@注文単位.getExecutedQuantity()
            //  注文単位ID： 注文単位.getOrderUnitId()
            //  isSkip金額チェック：  false
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoResult = null;
            l_ifoResult = l_orderManager.calcChangeEstimateSettlementIncome(
                l_gentradeCommission,
                l_dblLimitPrice,
                l_subAccount,                   
                l_tradedProduct,
                l_eqOrderEntry,
                l_dblTotalQuantity,
                l_side,
                l_dblExecutedQuantity,
                l_lngOrderUnitId,
                false);

            //1.18 （先物訂正返済サービス）validate注文２（参照）
            //1.2 レスポンスデータを生成する。
            WEB3FuturesCloseMarginChangeConfirmResponse l_response =
                (WEB3FuturesCloseMarginChangeConfirmResponse)l_request.createResponse();
            
            //1.3 返済建玉エントリの要素分LOOP
            WEB3FuturesOptionsContractUnit l_contractUnit = null;
            WEB3IfoContractImpl l_ifoContract = null;
            ArrayList l_list = new ArrayList();
            for (int i = 0; i < l_eqOrderEntry.length; i++)
            {
                //1.3.1 建玉明細オブジェクトを生成する。
                l_contractUnit = new WEB3FuturesOptionsContractUnit();

                //1.3.2 建玉IDを取得する。
                long l_lngContractId = l_eqOrderEntry[i].getContractId();

                //1.3.3 返済数量を取得する。
                double l_dblQuantity = l_eqOrderEntry[i].getQuantity();
                if(Double.isNaN(l_dblQuantity))
                {
                    l_dblQuantity = 0;
                }
                
                //1.3.4 先物OP建玉(long)
                l_ifoContract = new WEB3IfoContractImpl(l_lngContractId);

                //1.3.5 建年月日を取得する。
                Date l_datOpenDate = l_ifoContract.getOpenDate();

                //1.3.6 建単価を取得する。
                double l_dblContractPrice = l_ifoContract.getContractPrice();
                if(Double.isNaN(l_dblContractPrice))
                {
                    l_dblContractPrice = 0;
                }
                
                //1.3.7 建約定代金を取得する。
                double l_dblContractExecutedAmount = l_ifoContract.getContractExecutedAmount(l_dblQuantity);
                if(Double.isNaN(l_dblContractExecutedAmount))
                {
                    l_dblContractExecutedAmount = 0;
                }
                
                //建手数料を取得する
                BigDecimal l_bdContractCommission =
                    new BigDecimal(l_ifoContract.getContractCommission(l_dblQuantity, l_lngOrderUnitId) + "");
                BigDecimal l_bdContractCommissionConsumptionTax =
                    new BigDecimal(l_ifoContract.getContractCommissionConsumptionTax(l_dblQuantity, l_lngOrderUnitId) + "");
                l_bdContractCommission = l_bdContractCommission.add(l_bdContractCommissionConsumptionTax);
                double l_dblContractCommission = l_bdContractCommission.doubleValue();

                //時価を取得する
                double l_dblPrice = 0D;
                if(l_currentInfo != null)
                {
                    l_dblPrice = l_currentInfo.getCurrentPrice();
                }

                //1.3.8 評価損益を取得する
                double l_dblIncome = l_ifoContract.getEvaluateIncome(l_dblPrice,l_dblQuantity);
                BigDecimal l_bdIncome = new BigDecimal(l_dblIncome + "");

                //1.3.9 getQuantity()
                double l_dblGetQuantity = l_ifoContract.getQuantity();
                if(Double.isNaN(l_dblGetQuantity))
                {
                    l_dblGetQuantity = 0;
                }
                
                //1.3.10 get返済約定済数量(long)(先物OP建玉::get返済約定済数量)
                //  [引数] 
                //  注文単位ID： 注文単位.注文単位ID 
                double l_dblClosingExecuteContractCnt = l_ifoContract.getClosingExecuteContractCnt(l_orderUnit.getOrderUnitId());

                //1.3.11 (*1)プロパティセット
                l_contractUnit.id                    = String.valueOf(l_lngContractId);
                l_contractUnit.openDate              = WEB3DateUtility.toDay(l_datOpenDate);
                l_contractUnit.contractPrice         = WEB3StringTypeUtility.formatNumber(l_dblContractPrice);
                l_contractUnit.contractQuantity      = WEB3StringTypeUtility.formatNumber(l_dblGetQuantity);
                l_contractUnit.contractExecPrice     = WEB3StringTypeUtility.formatNumber(l_dblContractExecutedAmount);
                l_contractUnit.contractCommission    = WEB3StringTypeUtility.formatNumber(l_dblContractCommission);
                l_contractUnit.income                = WEB3StringTypeUtility.formatNumber(l_dblIncome);
                l_contractUnit.incomeCost            = WEB3StringTypeUtility.formatNumber(l_bdIncome.subtract(l_bdContractCommission).doubleValue());
                l_contractUnit.contractOrderQuantity = WEB3StringTypeUtility.formatNumber(l_dblQuantity);
                l_contractUnit.contractExecQuantity  = WEB3StringTypeUtility.formatNumber(l_dblClosingExecuteContractCnt);
                l_contractUnit.settlePriority        = String.valueOf(i + 1);
                l_contractUnit.sessionType           =
                    ((IfoContractRow)l_ifoContract.getDataSourceObject()).getSessionType();

                l_list.add(l_contractUnit);
            }
            WEB3FuturesOptionsContractUnit[] l_contractUnits = (WEB3FuturesOptionsContractUnit[])
            l_list.toArray(new WEB3FuturesOptionsContractUnit[l_list.size()]);

            //1.4 市場閉局間近の指数を配列で取得する。
            WEB3GentradeBranch l_gentradeBranch =
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch();
            String[] l_strTradeCloseSuspensions =
                WEB3GentradeTradingTimeManagement.getTradeCloseSuspension(
                    l_gentradeBranch,
                    WEB3FuturesOptionDivDef.FUTURES);

            //1.5 概算受渡代金項目にセットされている概算決済損益を取得する。
            double l_EstimateDeliveryAmount = l_ifoResult.getEstimateDeliveryAmount();

            //1.6 get計算単価( )(先物OP概算受渡代金計算結果::get計算単価)
            double l_dblCalcUnitPrice = l_ifoResult.getCalcUnitPrice();
            
            //1.7 get手数料コース( )(先物OP概算受渡代金計算結果::get手数料コース)
            String l_strCommissionCourse = l_ifoResult.getCommissionCourse();

            //1.8 get手数料( )(先物OP概算受渡代金計算結果::get手数料)
            double l_dblCommission = l_ifoResult.getCommission();

            //1.9 get手数料消費税( )(先物OP概算受渡代金計算結果::get手数料消費税)
            double l_dblCommissionTax = l_ifoResult.getCommissionTax();

            //1.10 (*2)プロパティセット
            l_response.contractUnits = l_contractUnits;
            l_response.estimatedSettleIncome = WEB3StringTypeUtility.formatNumber(l_EstimateDeliveryAmount);
            l_response.commissionCourse = l_strCommissionCourse;
            l_response.commission = WEB3StringTypeUtility.formatNumber(l_dblCommission);
            l_response.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblCommissionTax);
            l_response.messageSuspension = l_strTradeCloseSuspensions;
            l_response.checkPrice = WEB3StringTypeUtility.formatNumber(l_dblCalcUnitPrice);
            l_response.checkDate = WEB3DateUtility.toDay(l_datOrderBizDate);
            //レスポンス.注文有効期限 = 返済注文内容.訂正失効日
            l_response.expirationDate = l_ifoOrderSpec.getChangeExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }

    /**
     * (submit注文)<BR>
     * 株価指数先物の訂正返済注文を登録する。<BR>
     * <BR>
     * 「（先物訂正返済サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数先物訂正返済完了リクエスト
     * @@return WEB3FuturesCloseMarginChangeCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 40A8B0BD021C
     */
    protected WEB3FuturesCloseMarginChangeCompleteResponse submitOrder(WEB3FuturesCloseMarginChangeCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME= "submitOrder(WEB3FuturesCloseMarginChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.debug("シーケンス図「（先物訂正返済サービス）submit注文１」参照");
        try
        {
            //1.1 リクエストデータの整合性をチェックする。
            log.debug("1. リクエストデータの整合性をチェックする。");
            l_request.validate();

            //1.2 注文オブジェクトを取得する。
            long l_lngID = Long.parseLong(l_request.id);
            IfoOrderImpl l_ifoOrderImpl = new IfoOrderImpl(l_lngID);
            log.debug("2.注文オブジェクトを取得しました。注文ID = " + l_lngID);

            //1.3 注文単位を取得する。 当該メソッドで取得した配列の0番目の要素
            OrderUnit[] l_orderUnits = l_ifoOrderImpl.getOrderUnits();
            OrderUnit l_orderUnit = l_orderUnits[0];
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow) l_orderUnit.getDataSourceObject();
            log.debug("3.注文単位を取得しました。注文単位ID = " + l_orderUnits[0].getOrderUnitId());

            //1.4 決済順位でソートし、返済建玉エントリの配列を作成する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderManager = null;
            l_orderManager =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            //注文ＩＤ
            long l_lngGetOrderId = l_orderUnit.getOrderId();
            //注文単位ＩＤ
            long l_lngGetOrderUnitId = l_orderUnit.getOrderUnitId();
            //注文数量
            double l_dblOrderQuantity = 0D;
            if (l_request.futOrderQuantity != null)
            {
                l_dblOrderQuantity = Double.parseDouble(l_request.futOrderQuantity);
            }
            //注文単価を取得する。            
            double l_dblLimitPrice = 0D;
            boolean l_blnIsLimitPrice ;
            if (WEB3OrderPriceDivDef.MARKET_PRICE.equals(l_request.orderPriceDiv))
            {
                //成行
                l_dblLimitPrice = 0D;
                l_blnIsLimitPrice = false;
            }
            else
            {
                //指値
                l_dblLimitPrice = Double.parseDouble(l_request.limitPrice);
                l_blnIsLimitPrice = true;
            } 
            SettleContractEntry[] l_eqOrderEntry =
                l_orderManager.createSettleContractEntry(
                    l_lngGetOrderUnitId,                      //注文単位ID = （取得した注文単位.getOrderUnitId()）
                    l_dblOrderQuantity,                       //注文数量 = リクエストデータ.注文数量
                    l_request.closeMarginContractUnits);      //建玉ID[] = リクエストデータ.返済建玉[]
            
            //1.5 発注日を取得する。
            Date l_datOrderBizDate = null;
            if (l_request.checkDate == null)
            {
                l_request.checkDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            }
            l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(l_request.checkDate);
            
            //訂正執行条件
            IfoOrderExecutionConditionType l_ifoOrderExecutionConditionType = 
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.execCondType);

            //OP注文マネージャ
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_orderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();

            //先物OP銘柄
            //(*1)先物OPプロダクトマネージャ.getProduct(注文単位.銘柄ID)にて取得
            WEB3IfoProductManagerImpl l_productMgr =
                (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();

            WEB3IfoProductImpl l_ifoProductImpl = null;
            try
            {
                l_ifoProductImpl =
                    (WEB3IfoProductImpl)l_productMgr.getProduct(l_ifoOrderUnitRow.getProductId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(STR_METHOD_NAME,l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //市場
            Market l_market = l_ifoProductImpl.getPrimaryMarket();

            //訂正失効日：
            //リクエストデータ.注文有効期限 != nullの場合、OP注文マネージャ.get注文有効期限(
            //リクエストデータ.注文有効期限,先物OP銘柄(*1).銘柄コード,市場.getMarketCode(),”先物”)の戻り値
            //リクエストデータ.注文有効期限 == nullの場合、get発注日()の戻り値
            Date l_datChangeExpirationDate = null;
            if (l_request.expirationDate != null)
            {
                l_datChangeExpirationDate = l_orderManagerImpl.getExpirationDate(
                    l_request.expirationDate,
                    l_ifoProductImpl.getProductCode(),
                    l_market.getMarketCode(),
                    WEB3FuturesOptionDivDef.FUTURES);
            }
            else 
            {
                l_datChangeExpirationDate = WEB3DateUtility.toDay(l_datOrderBizDate);
            }
            
            //発注条件演算子
            String l_strOrderCondOperator = null;
            
            //逆指値基準値
            double l_dblStopOrderPrice = 0D;
            
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.stopOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.stopOrderCondPrice);
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_strOrderCondOperator = l_request.wlimitOrderCondOperator;
                l_dblStopOrderPrice = Double.parseDouble(l_request.wlimitOrderCondPrice);
            }
            
            //（W指値）訂正指値：　@リクエストデータ.W指値用注文単価 。
            double l_dblWLimitPrice = 0D;
           if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_request.wLimitOrderPriceDiv)
                && WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {                
                l_dblWLimitPrice = Double.parseDouble(l_request.wLimitPrice);
            }
            else
            {
                l_dblWLimitPrice = 0D;
            }
            
            //(W指値)執行条件
            IfoOrderExecutionConditionType l_wLimitExecCondType =
                WEB3IfoDataAdapter.getExecutionConditionType(l_request.wlimitExecCondType);
            
            //夕場前繰越対象フラグ：　@先物OPデータアダプタ.get夕場前
            //繰越対象フラグ(リクエストデータ.注文期限区分, 注文単位.部店ID)の戻り値
            boolean l_blnEveningSessionCarryOverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlag(
                    l_request.expirationDateType,
                    l_ifoOrderUnitRow.getBranchId());
            //1.6 返済訂正内容を作成する。 
            WEB3IfoChangeSettleContractOrderSpec l_ifoOrderSpec = 
                WEB3IfoChangeSettleContractOrderSpec.createIfoChangeSettleContractOrderSpec(
                    l_lngGetOrderId,
                    l_lngGetOrderUnitId,
                    l_dblLimitPrice,
                    l_eqOrderEntry,
                    l_ifoOrderExecutionConditionType,
                    l_datChangeExpirationDate,
                    l_datOrderBizDate,
                    l_request.orderCondType,
                    l_strOrderCondOperator,
                    WEB3StopBasePriceTypeDef.DEFAULT,
                    l_dblStopOrderPrice,
                    l_dblWLimitPrice,
                    l_wLimitExecCondType,
                    l_request.wlimitEnableStatusDiv,
                    l_request.expirationDateType,
                    l_blnEveningSessionCarryOverFlag);

            //1.7 補助口座を取得する。
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount();

            //1.8 先物返済訂正注文の発注審査を実施する。
            OrderValidationResult l_result =
                l_orderManager.validateFuturesChangeSettleContractOrder(
                    l_subAccount,
                    l_ifoOrderSpec);
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_result.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            //1.9 建玉の合計数量を取得する。
            double l_dblTotalQuantity = l_ifoOrderSpec.getAfterChangeTotalQuantity();
            if(Double.isNaN(l_dblTotalQuantity))
            {
                l_dblTotalQuantity = 0;
            }
            log.debug("9. 建玉の合計数量を取得しました。建玉の合計数量 = " + l_dblTotalQuantity);

            //1.10 建玉オブジェクトを取得する
            long l_lngCloseMarginContractUnits = Long.parseLong(l_request.closeMarginContractUnits[0].id);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_lngCloseMarginContractUnits);
            log.debug("10. 建玉オブジェクトを取得しました。建玉ID = " + l_lngCloseMarginContractUnits);

            //1.11 先物OP取引銘柄オブジェクトを取得する。
            WEB3IfoTradedProductImpl l_tradedProduct = (WEB3IfoTradedProductImpl)l_ifoContractImpl.getTradedProduct();
            log.debug("11. 先物OP取引銘柄オブジェクトを取得しました。銘柄ID = " + l_tradedProduct.getTradedProductId());

            //1.12 手数料オブジェクトを生成する。
            WEB3GentradeCommission l_gentradeCommission = new WEB3GentradeCommission();
            log.debug("12. 手数料オブジェクトを生成しました。。");

            //1.13 プロパティセット(*1)
            SubAccountRow l_subAccountRow =(SubAccountRow) l_subAccount.getDataSourceObject() ;
            log.debug("13. 手数料オブジェクトにアクセサメソッドを使用しプロパティをセットする。");
            //手数料.注文チャネルをセット
            l_gentradeCommission.setOrderChannel(this.getLoginChannel());
            log.debug("手数料.注文チャネル = " + this.getLoginChannel());
            //手数料.証券会社コートをセットする。
            String l_strInstitutionCode = String.valueOf(l_subAccountRow.getInstitutionCode());
            l_gentradeCommission.setInstitutionCode(l_strInstitutionCode);
            log.debug("手数料.証券会社コート = " + l_strInstitutionCode);
            //手数料.部店IDをセットする。
            l_gentradeCommission.setBranchId(l_subAccountRow.getBranchId());
            log.debug("手数料.部店ID = " + l_subAccountRow.getBranchId());
            //手数料.発注日をセットする。
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            l_gentradeCommission.setOrderBizDate(new Timestamp(l_datBizDate.getTime()));
            log.debug("手数料.発注日 = " + l_datOrderBizDate);
            //手数料.取引コード（SONAR）をセットする。
            l_gentradeCommission.setSonarTradedCode(WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            log.debug("手数料.取引コード（SONAR） = " + WEB3TransactionTypeSONARDef.SETTLE_CONTRACT);
            //手数料.手数料商品コードをセットする。
            l_gentradeCommission.setCommissionProductCode(WEB3CommisionProductCodeDef.INDEX_FUTURES);
            log.debug("手数料.手数料商品コード = " + WEB3CommisionProductCodeDef.INDEX_FUTURES);
            //手数料.弁済区分をセットする。
            l_gentradeCommission.setPayType(WEB3PayTypeDef.OTHER);
            log.debug("手数料.弁済区分 = " + WEB3PayTypeDef.OTHER);
            //手数料.原注文チャネルをセットする。
            l_gentradeCommission.setOrgOrderChannel(l_ifoOrderUnitRow.getOrderChanel());
            log.debug("手数料.原注文チャネル = " + l_ifoOrderUnitRow.getOrderChanel());
            //手数料.原注文手数料NOをセットする。
            l_gentradeCommission.setOrgCommissionNo(l_ifoOrderUnitRow.getCommTblNo());
            log.debug("手数料.原注文手数料NO = " + l_ifoOrderUnitRow.getCommTblNo());
            //手数料.原注文手数料NO.枝番をセットする。
            l_gentradeCommission.setOrgCommissionRevNo(l_ifoOrderUnitRow.getCommTblSubNo());
            log.debug("手数料.原注文手数料NO.枝番 = " + l_ifoOrderUnitRow.getCommTblSubNo());
            //手数料.is指値をセットする。
            l_gentradeCommission.setIsLimitPrice(l_blnIsLimitPrice);
            log.debug("手数料.is指値 = " + l_gentradeCommission.isLimitPrice());
            
            //手数料.原資産銘柄コード = 建玉.get銘柄().get原資産銘柄コード(*2)
            l_gentradeCommission.setUnderlyingProductCode(
                ((WEB3IfoProductImpl)l_ifoContractImpl.getProduct()).getUnderlyingProductCode());

            //手数料.日計り区分 = 注文単位.日計り区分
            l_gentradeCommission.setDayTradeType(l_ifoOrderUnitRow.getDayTradeType());

            //手数料.数量 = 返済訂正内容.getAfterChangeTotalQuantity()
            l_gentradeCommission.setQuantity(l_dblTotalQuantity);
            
            //1.14 売買（SideEnum定義値）を取得する。
            SideEnum l_side = null;
            if (SideEnum.BUY.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.SELL;
            }
            else if (SideEnum.SELL.equals(l_orderUnit.getSide()))
            {
                l_side = SideEnum.BUY;
            }
            log.debug("売買（SideEnum定義値）を取得しました。" + l_side);

            //1.15 合計約定数量を取得する。
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if(Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0;
            }
            log.debug("合計約定数量を取得しました。" + l_dblExecutedQuantity);

            //1.16 訂正時の概算決済損益を計算する。
            //  [calc訂正時概算決済損益()に指定する引数]
            //  手数料：　@手数料オブジェクト
            //  指値：　@
            //     リクエストデータ.確認時単価!=nullの場合、リクエストデータ.確認時単価
            //     リクエストデータ.確認時単価==nullの場合、リクエストデータ.注文単価(*1)
            //    （*1　@リクエストデータ.注文単価==nullの場合はゼロをセット。）
            //  補助口座：　@補助口座オブジェクト
            //  先物OP取引銘柄：　@先物OP取引銘柄オブジェクト
            //  返済建玉エントリ[]：   create返済建玉エントリの戻り値
            //  数量： 返済訂正内容.getAfterChangeTotalQuantity()
            //  売買： （以下のとおり）
            //      注文単位.getSide() = ”買”の場合、”売”
            //      注文単位.getSide() = ”売”の場合、”買”
            //  約定数量：　@注文単位.getExecutedQuantity()
            //  注文単位ID： 注文単位.getOrderUnitId()
            //  isSkip金額チェック：  false
            //確認時単価
            double l_dblCheckPrice = 0D;
            if (l_request.checkPrice != null)
            {
                l_dblCheckPrice = Double.parseDouble(l_request.checkPrice);
            }
            else
            {
                l_dblCheckPrice = l_dblLimitPrice;
            }
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoResult = l_orderManager.calcChangeEstimateSettlementIncome(
                l_gentradeCommission,
                l_dblCheckPrice,
                l_subAccount,
                l_tradedProduct,
                l_eqOrderEntry,
                l_dblTotalQuantity, 
                l_side,                         
                l_dblExecutedQuantity,
                l_lngGetOrderUnitId,
                false);                            


            //1.3 先物OP返済訂正更新インタセプタ(返済訂正内容)
            WEB3IfoSettleContractChangeUpdateInterceptor l_ifoUpdateInterceptor =
                new WEB3IfoSettleContractChangeUpdateInterceptor(l_ifoOrderSpec);

            //1.4  (*1) プロパティセット
            //インタセプタ.手数料=手数料オブジェクトをセットする。
            l_ifoUpdateInterceptor.setCommision(l_gentradeCommission);
            //インタセプタ.概算受渡代金計算結果
            l_ifoUpdateInterceptor.setEstimateDeliveryAmountCalcResult(l_ifoResult);
            //インタセプタ.発注条件
            l_ifoUpdateInterceptor.setOrderCond(l_request.orderCondType);
            //インタセプタ.発注条件演算子
            //インタセプタ.逆指値基準値
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.stopOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.stopOrderCondPrice));
            }
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request.orderCondType))
            {
                l_ifoUpdateInterceptor.setOrderCondOperator(l_request.wlimitOrderCondOperator);
                l_ifoUpdateInterceptor.setStopOrderBasePrice(Double.parseDouble(l_request.wlimitOrderCondPrice));
            }
            //インタセプタ.逆指値基準値タイプ
            l_ifoUpdateInterceptor.setStopOrderBasePriceType(WEB3StopBasePriceTypeDef.DEFAULT);
            //インタセプタ.（W指値）訂正指値
            l_ifoUpdateInterceptor.setWLimitPriceChange(l_ifoOrderSpec.getWLimitPriceChange());
            //インタセプタ.取引者ID
            Trader l_trader = this.getTrader();   
            long l_lngTraderId = 0;
            if (l_trader != null)
            {
                l_lngTraderId = l_trader.getTraderId();
            }
            l_ifoUpdateInterceptor.setTraderId(l_lngTraderId);
            
            //インタセプタ.注文経路区分
			OpLoginSecurityService l_opLoginSec =
				(OpLoginSecurityService) Services.getService(
					OpLoginSecurityService.class);
			String l_strOrderRootDiv =
				l_opLoginSec.getSessionProperty(
					WEB3SessionAttributeDef.ORDER_ROOT_DIV);

			l_ifoUpdateInterceptor.setOrderRootDiv(l_strOrderRootDiv);

            //1.5 インタセプタをセットする。
            l_orderManager.setThreadLocalPersistenceEventInterceptor(l_ifoUpdateInterceptor);

            //1.6 返済注文訂正更新処理を行う。
            OrderSubmissionResult l_submissionResult = l_orderManager.submitChangeSettleContractOrder(
                l_subAccount, 
                l_ifoOrderSpec, 
                l_request.password, 
                true);
            if (l_submissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("ProcessingResult() = " + l_submissionResult.getProcessingResult());
                throw new WEB3BusinessLayerException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            //is予約注文確認要(IfoOrderUnit)
            IfoOrderUnit l_ifoOrderUnit = (IfoOrderUnit)l_orderUnit;
            boolean l_blnIsReserveOrderExist = l_orderManager.isReserveOrderExist(l_ifoOrderUnit);

            //予約注文確認要（is予約注文確認要() == true）の場合
            List l_lisGetOpenReserveIfoOrderUnits = null;
            if (l_blnIsReserveOrderExist)
            {
                //get有効予約注文単位一覧(親注文の注文ID : long)
                WEB3ToSuccReservationIfoOrderUpdateService l_ifoOrderUpdateService =
                    (WEB3ToSuccReservationIfoOrderUpdateService)Services.getService(
                        WEB3ToSuccReservationIfoOrderUpdateService.class);
                l_lisGetOpenReserveIfoOrderUnits =
                    l_ifoOrderUpdateService.getOpenReserveIfoOrderUnits(l_ifoOrderUnit.getOrderId());
            }

            //1.7 レスポンスデータの生成
            WEB3GenResponse l_genResponse = l_request.createResponse();
            WEB3FuturesCloseMarginChangeCompleteResponse l_response = (WEB3FuturesCloseMarginChangeCompleteResponse)l_genResponse;

            //1.8 (*2) プロパティセット
            l_response.lastUpdatedTimestamp = GtlUtils.getSystemTimestamp();
            l_response.orderActionId = l_request.id;
            if (l_lisGetOpenReserveIfoOrderUnits != null)
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

        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }
}
@
