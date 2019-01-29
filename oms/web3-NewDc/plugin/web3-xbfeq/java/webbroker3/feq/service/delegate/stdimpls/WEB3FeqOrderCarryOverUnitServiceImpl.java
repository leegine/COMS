head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文繰越UnitServiceImpl(WEB3FeqOrderCarryOverUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー       
Revesion History : 2007/07/13 柴双紅　@モデルNo.353
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderDao;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.feq.WEB3FeqAmountCalcResult;
import webbroker3.feq.WEB3FeqBizLogicProvider;
import webbroker3.feq.WEB3FeqNewOrderSpec;
import webbroker3.feq.WEB3FeqOrderCarryOverUpdateInterceptor;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式注文繰越UnitServiceImpl)<BR>
 * 外国株式注文繰越UnitService実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqOrderCarryOverUnitServiceImpl implements 
    WEB3FeqOrderCarryOverUnitService 
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverUnitServiceImpl.class);  
    
    /**
     * @@roseuid 42CE39F60251
     */
    public WEB3FeqOrderCarryOverUnitServiceImpl() 
    {
     
    }
    
    /**
     * (exec注文繰越)<BR>
     * 顧客単位で注文繰越処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文繰越）exec注文繰越」参照。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 42B8A39B0394
     */
    public void execOrderCarryOver(WEB3GentradeMainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "execOrderCarryOver(WEB3GentradeMainAccount l_mainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.1 lock口座(String, String, String)(拡張アカウントマネージャ::lock口座)
        //口座をロックする。 
        //[引数] 
        //証券会社コード：　@パラメータ.顧客.証券会社コード 
        //部店コード：　@パラメータ.顧客.部店コード 
        //口座コード：　@パラメータ.顧客.口座コード
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                
        //拡張アカウントマネージャ取得する    
        WEB3GentradeAccountManager l_genAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        l_genAccountManager.lockAccount(
            l_mainAccount.getInstitution().getInstitutionCode(), 
            l_mainAccount.getBranch().getBranchCode(), 
            l_mainAccount.getAccountCode());
        
        //1.2 get繰越対象注文単位(Long, String)
        //繰越対象となる注文単位を取得する。 
        //[引数] 
        //口座ID：　@パラメータ.顧客.口座ID 
        //証券会社コード：　@パラメータ.顧客.証券会社コード
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        WEB3FeqOrderUnit[] l_web3FeqOrderUnits = 
            l_feqOrderManager.getCarryOverOrderUnit(
                new Long(l_mainAccount.getAccountId()), 
                l_mainAccount.getInstitution().getInstitutionCode());
        
        int l_intLength = 0;
        
        if (l_web3FeqOrderUnits != null)
        {
            l_intLength = l_web3FeqOrderUnits.length;
        }
        
        //1.3 (*)get繰越対象注文単位()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_intLength; i++)
        {
            //1.3.1 外国株式注文繰越更新イベントインタセプタを生成する。 
            //[引数] 
            //繰越元注文単位行：　@null 
            //金額計算結果：　@null 
            //計算単価：　@0
            WEB3FeqOrderCarryOverUpdateInterceptor l_orderCarryUpdInterceptor = 
                new WEB3FeqOrderCarryOverUpdateInterceptor(
                    null, null, 0);
            
            //1.3.2 setThreadLocalPersistenceEventInterceptor()
            //ThreadLocalにインタセプタをセットする。 
            //[引数] 
            //arg0：　@生成したインタセプタ
            l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(
                l_orderCarryUpdInterceptor);
            
            //1.3.3 expireOrder(arg0 : long)
            //繰越元注文失効処理を行う。 
            //[引数] 
            //arg0：　@処理対象の注文単位.注文ID
            ProcessingResult l_processingResult = 
                l_feqOrderManager.expireOrder(l_web3FeqOrderUnits[i].getOrderId());
            
            if (l_processingResult.isFailedResult())
            {
                log.debug("繰越元注文失効エラー");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_processingResult.getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "繰越元注文失効エラー");
            }            
        }
        
        //1.4 (*)get繰越対象注文単位()の戻り値の要素数分Loop処理
        for (int i = 0; i < l_intLength; i++)
        {     
            WEB3FeqOrderUnit l_feqOrderUnit = (WEB3FeqOrderUnit)
                l_feqOrderManager.getOrderUnitByOrderId(
                    l_web3FeqOrderUnits[i].getOrderId());
            
            //1.4.1 insert繰越注文(外国株式注文単位)
            //繰越した新規注文を作成、登録する。 
            //[引数] 
            //注文単位：　@処理対象の注文単位
            try
            {
                this.insertOrderCarryOver(l_feqOrderUnit);
            }
            //1.4.2 (*)insert繰越注文にて例外がスローされた場合
            catch( WEB3BaseException l_ex)
            {                
                log.debug(STR_METHOD_NAME, l_ex);
                log.debug("error order_unit_id = [" + l_feqOrderUnit.getOrderUnitId() + "]");
                ErrorInfo l_errorInfo = l_ex.getErrorInfo();
                
                //error_tagが"SYSTEM_ERROR"で始まる場合は、WEB3SystemLayerExceptionをスローする。
                if (l_errorInfo.getErrorTag().startsWith("SYSTEM_ERROR"))
                {
                    throw new WEB3SystemLayerException(
                        l_errorInfo,
                        l_ex.getErrorMethod(),
                        l_ex.getMessage(),
                        l_ex.getException());
                }
                
                //1.4.2.1 get注文エラー理由コード(ErrorInfo)
                //エラー情報に対応する注文エラー理由コードを取得する。 
                //[引数] 
                //エラー情報：　@catchした例外から取得したErrorInfo
                String l_strErrorCode = 
                    this.getErrorCode(l_ex.getErrorInfo());
                
                //1.4.2.2 update繰越元注文(外国株式注文単位, String)
                //繰越元注文の注文エラー理由コードなどをupdateする。 
                //[引数] 
                //注文単位：　@処理対象の注文単位 
                //注文エラー理由コード：　@get注文エラー理由コード()の戻り値
                this.updateOrderCarryOverBase(l_feqOrderUnit, l_strErrorCode);
            }
        }
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (insert繰越注文)<BR>
     * 繰越した新規注文を作成し、登録する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（注文繰越）insert繰越注文」参照。<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 注文繰越 」(注文繰越）insert繰越注文 )<BR>
     * 　@　@:  1.8validate取引余力<BR> 
     * 　@　@戻り値の取引余力結果.判定フラグ == falseの場合、<BR>
     * 　@　@例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_01306<BR>
     * ==========================================================<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクト
     * @@throws WEB3BaseException
     * @@roseuid 42B8A5060123
     */
    protected void insertOrderCarryOver(WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "insertOrderCarryOver(WEB3FeqOrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqOrderUnitParams l_feqOrderUnitParams = 
            new FeqOrderUnitParams(
                (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
        
        //1.1 (*)パラメータ.注文単位.取引者ID != nullの場合
        Trader l_trader = null;
        if (!l_feqOrderUnitParams.getTraderIdIsNull())
        {
            //1.1.1 扱者を取得する。 
            //[引数] 
            //arg0：　@注文単位.取引者ID            
            FinObjectManager l_finObjectManager = GtlUtils.getFinObjectManager();
            try
            {
                l_trader = l_finObjectManager.getTrader(
                    l_feqOrderUnit.getTraderId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(" テーブルに該当するデータがありません: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //1.2 補助口座を取得する。
        SubAccount l_subAccount = l_feqOrderUnit.getSubAccount();
        
        //1.3  is買付( )(外国株式注文単位::is買付)
        //買付注文かどうか判別する。
        boolean l_blnIsBuy = l_feqOrderUnit.isBuy();
        
        //1.4 外株銘柄を取得する。
        FeqProduct l_feqProduct = (FeqProduct)l_feqOrderUnit.getProduct();
        if (l_feqProduct == null)
        {
            log.debug("Error in 外株銘柄を取得する");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.5 市場を取得する。
        Market l_market = l_feqOrderUnit.getMarket();
        
        //1.6 reset市場コード(String)(取引時間管理::reset市場コード)
        //取引カレンダコンテキストの市場コードを再セットする。 
        //[引数] 
        //市場コード：　@get市場()の戻り値.市場コード
        WEB3GentradeTradingTimeManagement.resetMarketCode(
            l_market.getMarketCode());
        
        //1.7 create新規注文内容() 新規建注文内容を作成する。 
        //[引数] 
        //ノートアンカー参照                
        double l_dblOrderQuantity = 
            l_feqOrderUnitParams.getQuantity() - 
                l_feqOrderUnitParams.getExecutedQuantity();
        
        Long l_lngFirstOrderUnitId = null;
        if (l_feqOrderUnitParams.getFirstOrderUnitId() == 0)
        {
            l_lngFirstOrderUnitId = new Long(
                l_feqOrderUnitParams.getOrderUnitId());
        }
        else
        {
            l_lngFirstOrderUnitId = new Long(
                l_feqOrderUnitParams.getFirstOrderUnitId());
        }
        
        WEB3FeqNewOrderSpec l_feqNewOrderSpec = 
            WEB3FeqNewOrderSpec.createNewOrderSpec(
                l_feqOrderUnitParams.getInstitutionCode(),
                (WEB3GentradeTrader)l_trader,
                l_blnIsBuy, 
                l_feqProduct.getProductCode(), 
                l_market.getMarketCode(), 
                l_dblOrderQuantity, 
                l_feqOrderUnitParams.getLimitPrice(), 
                l_feqOrderUnitParams.getExecutionConditionType(), 
                l_feqOrderUnitParams.getExpirationDate(), 
                l_feqOrderUnitParams.getTaxType(), 
                l_feqOrderUnitParams.getCurrencyCode(), 
                l_feqOrderUnitParams.getOrderConditionType(), 
                l_feqOrderUnitParams.getWLimitPrice(), 
                l_feqOrderUnitParams.getSettleDiv(), 
                l_lngFirstOrderUnitId);
            
        //1.8 validate新規注文(SubAccount, ProductTypeEnum, NewOrderSpec)
        //新規注文発注審査を行う。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //銘柄タイプ：　@ProductTypeEnum.外国株式 
        //注文内容：　@create新規注文内容()の戻り値
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        NewOrderValidationResult l_newOrderValidationResult = 
            l_feqOrderManager.validateNewOrder(
                l_subAccount, 
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec);
        
        if (l_newOrderValidationResult.getProcessingResult().isFailedResult())
        {
            log.debug("発注審査チェックエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                l_newOrderValidationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注審査チェックエラー");
        }
        
        //1.9 取引銘柄を取得する。
        WEB3FeqTradedProduct l_web3FeqTradedProduct = (WEB3FeqTradedProduct)
            l_feqOrderUnit.getTradedProduct();
        
        //1.10 指値注文かどうか判別する。
        boolean l_blnIsLimitPrice = l_feqOrderUnit.isLimitOrder();
        
        //1.11 get計算用注文単価()
        //計算用の注文単価を取得する。 
        //[引数] 
        //取引銘柄：　@getTradedProduct()の戻り値 
        //部店： 補助口座.get取引店()の戻り値 
        //注文単価区分： 
        //　@[is指値()の戻り値 == trueの場合] "指値"をセット。 
        //　@[上記以外の場合] 　@               "成行"をセット。 
        //注文単価：　@注文単位.指値
        //訂正単価：　@注文単位.（Ｗ指値）訂正指値 
        //is買付：　@is買付()の戻り値
        
        String l_strOrderPriceDiv = null;
        if (l_blnIsLimitPrice)
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
        }
        else
        {
            l_strOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
        }
        
        double l_dblUnitPrice = 
            l_feqOrderManager.getUnitPrice(
                l_web3FeqTradedProduct, 
                (WEB3GentradeBranch)l_subAccount.getMainAccount().getBranch(), 
                l_strOrderPriceDiv, 
                l_feqOrderUnitParams.getLimitPrice(), 
                l_feqOrderUnitParams.getWLimitPrice(), 
                l_blnIsBuy);
        
        //1.12 calc売買代金(double, double)(外国株式計算サービス::calc売買代金)
        //売買代金（外貨）を算出する。 
        //[引数] 
        //株数：　@新規注文内容.getQuantity() 
        //単価：　@get計算用注文単価()の戻り値
        WEB3FeqBizLogicProvider l_feqBizLogicProvider = 
            (WEB3FeqBizLogicProvider)l_tradingModule.getBizLogicProvider();
        
        double l_dblExecutionAmount = 
            l_feqBizLogicProvider.calcExecutionAmount(
                l_feqNewOrderSpec.getQuantity(), 
                l_dblUnitPrice);

        //1.13 get通貨( )
        WEB3GentradeCurrency l_genCurrency = l_feqOrderUnit.getCurrency();
        
        //calc売買代金(double, double)の結果を、当該通貨の小数点桁数で丸める
        BigDecimal l_bdTradePriceFc = new BigDecimal(l_dblExecutionAmount);
        int l_intDecimalPlace = l_genCurrency.getScale();
        l_bdTradePriceFc = l_bdTradePriceFc.setScale(l_intDecimalPlace, BigDecimal.ROUND_HALF_EVEN);
        l_dblExecutionAmount = l_bdTradePriceFc.doubleValue();
        log.debug("売買代金の丸めた結果 ＝ "+ l_dblExecutionAmount);
        
        double l_dblBaseFxRate = 0.0D;
        
        //1.14 (*)買付注文(is買付()の戻り値 == true)の場合
        if (l_blnIsBuy)
        {
            //1.14.1  get買付基準為替レート( ) 買付基準為替レートを取得する。
            l_dblBaseFxRate = l_genCurrency.getBuyBaseRate();
        }
        //1.15 (*)売付注文(is買付()の戻り値 == false)の場合
        else
        {
            //1.15.1 get売付基準為替レート( ) 売付基準為替レートを取得する。
            l_dblBaseFxRate = l_genCurrency.getSellBaseRate();
        }
        
        //1.6 calc外国株式金額() 各種金額の計算を行う。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //外国株式銘柄：　@getProduct()の戻り値 
        //市場：　@get市場()の戻り値 
        //基準日：　@新規注文内容.get発注日() 
        //売買代金（外貨）：　@calc売買代金()の戻り値 
        //為替レート：　@取得した基準為替レート 
        //is買付：　@is買付()の戻り値 
        //is約定計算：　@false 
        //is指値：　@is指値()の戻り値
        //注文チャネル：　@パラメータ.注文単位.初回注文の注文チャネル
        WEB3FeqAmountCalcResult l_feqAmountCalcResult = 
            l_feqBizLogicProvider.calcFeqAmount(
                (WEB3GentradeSubAccount)l_subAccount, 
                (WEB3FeqProduct)l_feqProduct, 
                (WEB3GentradeMarket)l_market, 
                l_feqNewOrderSpec.getBizDate(), 
                l_feqNewOrderSpec.getBizDate(),
                l_dblExecutionAmount, 
                l_dblBaseFxRate,
                l_blnIsBuy, 
                false, 
                l_blnIsLimitPrice, 
                l_feqOrderUnitParams.getOrderChanel());
        
        //1.7 外国株式注文繰越更新イベントインタセプタ()
        //外国株式注文更新イベントインタセプタを生成する。 
        //[引数] 
        //繰越元注文単位行：　@注文単位 
        //金額計算結果：　@calc外国株式金額()の戻り値 
        //計算単価：　@get計算用注文単価()の戻り値
        WEB3FeqOrderCarryOverUpdateInterceptor l_orderUpdateInterceptor = 
            new WEB3FeqOrderCarryOverUpdateInterceptor(
                l_feqOrderUnitParams, 
                l_feqAmountCalcResult, 
                l_dblUnitPrice);
        
        //1.8 validate取引余力() 取引余力チェックを行う。 
        //[引数] 
        //補助口座：　@get補助口座()の戻り値 
        //注文内容インタセプタ：　@生成した更新イベントインタセプタ 
        //注文内容：　@新規注文内容 
        //注文種別：　@注文単位.注文種別 
        //余力更新フラグ：　@true(完了時)
        //注文内容インタセプタの配列            
        if (l_blnIsBuy)
        {
            WEB3FeqOrderCarryOverUpdateInterceptor[] l_orderUpdateInterceptors = 
                {l_orderUpdateInterceptor};
                
            //注文内容の配列
            WEB3FeqNewOrderSpec[] l_feqNewOrderSpecs = 
                {l_feqNewOrderSpec}; 
            
            WEB3TPTradingPowerService l_tpTradingPowerService = 
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_tpTradingPowerService.validateTradingPower(
                    (WEB3GentradeSubAccount)l_subAccount, 
                    l_orderUpdateInterceptors, 
                    l_feqNewOrderSpecs, 
                    l_feqOrderUnitParams.getOrderType(), 
                    true);
            
            if (l_tPTradingPowerResult == null)
            {
                log.debug("予期しないシステムエラーが発生しました。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //戻り値の取引余力結果.判定フラグ == false の場合、例外をスローする。
            if (!l_tPTradingPowerResult.isResultFlg())
            {
                log.debug("取引余力チェックエラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引余力チェックエラー。");
            }
        }

        //1.19 ThreadLocalに更新イベントインタセプタをセットする。 
        //[引数] 
        //arg0：　@生成した更新イベントインタセプタ
        l_feqOrderManager.setThreadLocalPersistenceEventInterceptor(
            l_orderUpdateInterceptor);
        
        //1.20 注文IDを新規採番する。
        long l_lngNewOrderId = l_feqOrderManager.createNewOrderId();
        
        //1.21 submitNewOrder()
        //新規注文をDBに登録する。 
        //[引数] 
        //arg0：　@get補助口座()の戻り値 
        //arg1：　@ProductTypeEnum.外国株式 
        //arg2：　@新規注文内容 
        //arg3：　@createNewOrderId()の戻り値 
        //arg4：　@get補助口座()の戻り値.getMainAccount().getTradingPassword()の値
        //       をdecriptした値 
        //arg5：　@true(スキップ)
        WEB3Crypt l_web3Crypt = new WEB3Crypt();
        MainAccount l_mainAccount = l_subAccount.getMainAccount();
        String l_strPassword = l_web3Crypt.decrypt(
            l_mainAccount.getTradingPassword());
        
        OrderSubmissionResult l_submitNewOrderResult =
            l_feqOrderManager.submitNewOrder(
                l_subAccount,
                ProductTypeEnum.FOREIGN_EQUITY, 
                l_feqNewOrderSpec,
                l_lngNewOrderId, 
                l_strPassword,
                true);

        if (l_submitNewOrderResult.getProcessingResult().isFailedResult())
        {
            log.debug("Error in submitNewOrder" +
                l_submitNewOrderResult.getProcessingResult().getErrorInfo());
            throw new WEB3SystemLayerException(
                l_submitNewOrderResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (update繰越元注文)<BR>
     * 繰越元注文単位の注文エラー理由コードなどをupdateする。<BR>
     * <BR>
     * １）　@繰越元注文の注文エラー理由コードをupdateする。<BR>
     * <BR>
     * 　@１－１）　@以下の条件に該当する繰越元注文の注文単位レコードをupdateする。<BR>
     * 　@　@<条件><BR>
     * 　@　@　@注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@　@注文単位レコード.注文エラー理由コード = パラメータ.注文エラー理由コード<BR>
     * 　@　@　@注文単位レコード.更新日付 = 現在日時<BR>
     * <BR>
     * 　@１－２）　@以下の条件に該当する繰越元注文の注文履歴の、<BR>
     * 　@　@　@　@　@最終履歴レコードの注文エラー理由コード をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@履歴テーブル.注文単位ID = パラメータ.注文単位.注文単位ID　@かつ<BR>
     * 　@　@履歴テーブル.注文履歴番号 = パラメータ.注文単位.注文履歴最終通番<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@履歴レコード.注文エラー理由コード = パラメータ.注文エラー理由コード<BR>
     * 　@　@履歴レコード.更新日付 = 現在日時<BR>
     * <BR>
     * 　@１－３）　@以下の条件に該当する、<BR>
     * 繰越元注文の注文（ヘッダ）の更新日時をupdateする。<BR>
     * <BR>
     * 　@　@<条件><BR>
     * 　@　@注文（ヘッダ）テーブル.注文ID = パラメータ.注文単位.注文ID<BR>
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@注文（ヘッダ）レコード.更新日付 = 現在日時<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 外国株式注文単位オブジェクトBR>
     * @@param l_strOrderErrorCode - (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B938910311
     */
    protected void updateOrderCarryOverBase(
        WEB3FeqOrderUnit l_feqOrderUnit, String l_strOrderErrorCode) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderCarryOverBase(" +
            "WEB3FeqOrderUnit, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqOrderUnit == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            //１）　@繰越元注文の注文エラー理由コードをupdateする。
            //１－１）　@以下の条件に該当する繰越元注文の注文単位レコードをupdateする。 
            //<条件> 
            //　@注文単位テーブル.注文単位ID = パラメータ.注文単位.注文単位ID 
            FeqOrderUnitParams l_feqOrderUnitParams = 
                new FeqOrderUnitParams(
                    (FeqOrderUnitRow) l_feqOrderUnit.getDataSourceObject());
            
            //<更新内容> 
            //　@注文単位レコード.注文エラー理由コード = パラメータ.注文エラー理由コード 
            l_feqOrderUnitParams.setErrorReasonCode(l_strOrderErrorCode);
            
            //　@注文単位レコード.更新日付 = 現在日時 
            l_feqOrderUnitParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());
           
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_feqOrderUnitParams);
            
            //１－２）　@以下の条件に該当する繰越元注文の注文履歴の、 
            //最終履歴レコードの注文エラー理由コード をupdateする。 
            //<条件> 
            //履歴テーブル.注文単位ID = パラメータ.注文単位.注文単位ID　@かつ 
            //履歴テーブル.注文履歴番号 = パラメータ.注文単位.注文履歴最終通番 
            String l_strWhereClause = 
                "order_unit_id = ? and order_action_serial_no = ?";
            
            Object l_bindVars[] = {
                new Long(l_feqOrderUnitParams.getOrderUnitId()), 
                new Integer(l_feqOrderUnitParams.getLastOrderActionSerialNo())};
        
            List l_lisOrderActionRows = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqOrderActionRow.TYPE,
                    l_strWhereClause,                    
                    null,
                    l_bindVars);    
           
            if (!l_lisOrderActionRows.isEmpty() && l_lisOrderActionRows.size() == 1)
            {
                FeqOrderActionRow l_feqOrderActionRow = 
                    (FeqOrderActionRow)l_lisOrderActionRows.get(0);
                
                FeqOrderActionParams l_feqOrderActionParams = 
                    new FeqOrderActionParams(l_feqOrderActionRow); 
                
                //<更新内容> 
                //履歴レコード.注文エラー理由コード = パラメータ.注文エラー理由コード 
                l_feqOrderActionParams.setErrorReasonCode(l_strOrderErrorCode);
                
                //履歴レコード.更新日付 = 現在日時 
                l_feqOrderActionParams.setLastUpdatedTimestamp(
                    GtlUtils.getSystemTimestamp());        
               
                l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_feqOrderActionParams); 
            }
            
            //１－３）　@以下の条件に該当する、繰越元注文の注文（ヘッダ）の更新日時をupdateする。 
            //<条件> 
            //注文（ヘッダ）テーブル.注文ID = パラメータ.注文単位.注文ID
            FeqOrderRow l_feqOrderRow = 
                FeqOrderDao.findRowByOrderId(l_feqOrderUnitParams.getOrderId());
           
            FeqOrderParams l_feqOrderParams = new FeqOrderParams(l_feqOrderRow);
            
            //<更新内容> 
            //注文（ヘッダ）レコード.更新日付 = 現在日時 
            l_feqOrderParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());
        
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_feqOrderParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get注文エラー理由コード)<BR>
     * 引数のエラー情報に対応する注文エラー理由コードを<BR>
     * 返却する。<BR>
     * <BR>
     * ※返却される注文エラー理由コードについては、<BR>
     * 　@DBレイアウト「外株注文単位テーブル仕様.xls#（注文単位テーブル補足）<BR>
     * 注文エラー理由コード」参照。<BR>
     * <BR>
     * １）パラメータ.エラー情報により、注文エラー理由コードを決定する。 <BR>
     * <BR>
     * 　@[パラメータ.エラー情報 == "値幅エラー"の場合]<BR>
     * 　@(外国株式発注審査個別チェック.validate値幅()からスローされた例外の場合)<BR>
     * 　@　@注文エラー理由コード = "0001：値幅エラー"<BR>
     * <BR>
     * 　@[パラメータ.エラー情報 == "預り金不足"の場合]<BR>
     * 　@(validate取引余力()の結果、スローされた例外の場合)<BR>
     * 　@　@注文エラー理由コード = "0002：預り金不足エラー"<BR>
     * <BR>
     * 　@[パラメータ.エラー情報 == "株式残高不足エラー"の場合]<BR>
     * 　@(外国株式発注審査個別チェック.validate売付可能数量()からスローされた<BR>
     * 例外の場合)<BR>
     * 　@　@注文エラー理由コード = "0003：株式残高不足エラー"<BR>
     * <BR>
     * 　@[パラメータ.エラー情報 == "売買停止銘柄エラー"の場合]<BR>
     * 　@(外国株式発注審査個別チェック.validate取引銘柄からスローされた<BR>
     *  　@取引規制の例外の場合)<BR>
     * 　@　@注文エラー理由コード = "0006：売買停止銘柄エラー"<BR>
     * <BR>
     * 　@[パラメータ.エラー情報 == "市場変更銘柄エラー"の場合]<BR>
     * 　@(外国株式発注審査個別チェック.validate取引銘柄()内の<BR>
     * 　@　@super.validateTradedProduct()にてスローされた例外の場合)<BR>
     * 　@　@※取引銘柄が取得できなかった場合または該当取引銘柄が非上場の場合<BR>
     * 　@　@注文エラー理由コード = "0007：市場変更銘柄エラー"<BR>
     * <BR>
     * 　@[パラメータ.エラー情報 == "特定口座開設エラー"の場合]<BR>
     * 　@(外国株式発注審査個別チェック.validate特定口座開設()からスローされた例外の場合)<BR>
     * 　@　@注文エラー理由コード = "0010：特定口座エラー"<BR>
     * <BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@注文エラー理由コード = "9001：その他エラー"<BR>
     * <BR>
     * ２）　@決定した注文エラー理由コードを返却する。<BR>
     * @@param l_errorInfo - (エラー情報)<BR>
     * エラー情報オブジェクト
     * @@return String
     * @@roseuid 42BA5BE00354
     */
    protected String getErrorCode(ErrorInfo l_errorInfo) 
    {
        final String STR_METHOD_NAME = 
            "getErrorCode(ErrorInfo l_errorInfo)";
        log.entering(STR_METHOD_NAME);
        
        String l_strErrorCode = null;
        //１）パラメータ.エラー情報により、注文エラー理由コードを決定する。 
       
        //[パラメータ.エラー情報 == "値幅エラー"の場合] 
        //(外国株式発注審査個別チェック.validate値幅()からスローされた例外の場合) 
        //注文エラー理由コード = "0001：値幅エラー" 
        if (WEB3ErrorCatalog.BUSINESS_ERROR_00031.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.PRICE_RANGE_ERROR;
        }

        //[パラメータ.エラー情報 == "預り金不足"の場合] 
        //(validate取引余力()の結果、スローされた例外の場合) 
        //注文エラー理由コード = "0002：預り金不足エラー" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.equals(l_errorInfo))
        {
            l_strErrorCode = 
                WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        
        //[パラメータ.エラー情報 == "株式残高不足エラー"の場合] 
        //(外国株式発注審査個別チェック.validate売付可能数量()からスローされた例外の場合) 
        //注文エラー理由コード = "0003：株式残高不足エラー" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02109.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.POSITION_SHORT_ERROR;
        }
        
        //[パラメータ.エラー情報 == "売買停止銘柄エラー"の場合] 
        //(外国株式発注審査個別チェック.validate取引銘柄からスローされた 
        //取引規制の例外の場合) 
        //注文エラー理由コード = "0006：売買停止銘柄エラー" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02087.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.TRADE_STOP_PRODUCT_ERROR;
        }

        //[パラメータ.エラー情報 == "市場変更銘柄エラー"の場合] 
        //(外国株式発注審査個別チェック.validate取引銘柄()内の 
        //super.validateTradedProduct()にてスローされた例外の場合) 
        //※取引銘柄が取得できなかった場合または該当取引銘柄が非上場の場合
        //注文エラー理由コード = "0007：市場変更銘柄エラー" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02088.equals(l_errorInfo) ||
            WEB3ErrorCatalog.BUSINESS_ERROR_02089.equals(l_errorInfo) )
        {
            l_strErrorCode = 
                WEB3ErrorReasonCodeDef.MARKET_CHANGE_PRODUCT_ERROR;
        }
        
        //[パラメータ.エラー情報 == "特定口座開設エラー"の場合] 
        //(外国株式発注審査個別チェック.validate特定口座開設()からスローされた例外の場合) 
        //注文エラー理由コード = "0010：特定口座エラー" 
        else if (WEB3ErrorCatalog.BUSINESS_ERROR_02096.equals(l_errorInfo))
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.SPEC_ACCOUNT_ERROR;
        }
        
        //[上記以外の場合] 
        //注文エラー理由コード = "9001：その他エラー" 
        else
        {
            l_strErrorCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }       
        
        //２）　@決定した注文エラー理由コードを返却する。        
        log.exiting(STR_METHOD_NAME);
        return l_strErrorCode;
    }
}
@
