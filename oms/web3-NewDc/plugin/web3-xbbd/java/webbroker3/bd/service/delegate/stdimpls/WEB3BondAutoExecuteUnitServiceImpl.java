head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecuteUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券自動約定UnitサービスImpl(WEB3BondAutoExecuteUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 徐大方 (中訊) 新規作成
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.ordersubmitter.io.BondChangeOrderSpec;

import webbroker3.bd.WEB3AdminBondOrderAcceptUpdateInterceptor;
import webbroker3.bd.WEB3BondBizLogicProvider;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondExecuteUpdateInterceptor;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券自動約定UnitサービスImpl)<BR>
 * 債券自動約定Unitサービス実装クラス
 * 
 * @@author 徐大方
 * @@version 1.0
 */

public class WEB3BondAutoExecuteUnitServiceImpl implements WEB3BondAutoExecuteUnitService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondAutoExecuteUnitServiceImpl.class);
    
    /**
     * @@roseuid 44E3362F0242
     */
    public WEB3BondAutoExecuteUnitServiceImpl() 
    {
     
    }

    /**
     * (notify自動約定)<BR>
     * 自動約定処理をする<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（債券自動約定Unitサービス）notify自動約定」参照。<BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    public void notifyAutoExecute(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "notifyAutoExecute(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
    
        //1.1 update自動約定(拡張債券注文単位)
        try 
        {
            this.updateAutoExecute(l_bondOrderUnit);
        }
        //1.2 update自動約定で例外がスローされた場合
        catch(WEB3BaseException l_ex)
        {
            //1.2.1 error_tagが"SYSTEM_ERROR"で始まる場合は、WEB3SystemLayerExceptionをスローする。
            if (l_ex.getErrorInfo().getErrorTag().startsWith("SYSTEM_ERROR"))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_ex.getErrorInfo(),
                    l_ex.getErrorMethod(),
                    l_ex.getMessage(),
                    l_ex.getException());
            }
            
            //1.2.2 to注文エラー理由コード(ErrorInfo)
            //注文エラー理由コードを取得 
            //[引数] 
            //エラー情報：catchした例外から取得したErrorInfo
            String l_strErrorReasonCode = 
                this.toOrderErrorReasonCode(l_ex.getErrorInfo());
            
            //1.2.3 updateエラー発生注文(債券注文単位, String)
            //注文エラー理由コードを更新する 
            //[引数] 
            //債券注文単位：引数.債券注文単位 
            //注文エラー理由コード：to注文エラー理由コード
            this.updateErrorOrder(l_bondOrderUnit, l_strErrorReasonCode);
        } 
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update自動約定)<BR>
     * 自動約定処理をする<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（債券自動約定Unitサービス）update自動約定」参照。 <BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    protected void updateAutoExecute(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateAutoExecute(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
    
        //1.1 get発注日()
        //発注日を取得する
        Date l_datBizDate = 
            WEB3DateUtility.getDate(
                l_bondOrderUnit.getBizDate() , "yyyyMMdd");
        
        //1.2 get債券注文種別判定()
        //債券注文種別判定を取得
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge = 
            l_bondOrderUnit.getBondOrderTypeJudge();
        
        //1.3 get決済区分()
        //決済区分を取得
        String l_strSettlementDiv = l_bondOrderUnit.getSettlementDiv();
        
        //1.4 getBranchId()
        //部店IDを取得
        long l_lngBranchId = l_bondOrderUnit.getBranchId();
        
        //1.5 getBranch(arg0 : long)
        //部店を取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_web3GentradeAccountManager.getBranch(l_lngBranchId);
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
        
        //1.6 get債券銘柄(long)
        //債券銘柄を取得
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondProductManager l_productManager = 
            (WEB3BondProductManager) l_tradingModule.getProductManager();
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct)l_productManager.getBondProduct(l_bondOrderUnit.getProductId());
        
        //1.7 create債券約定日情報(java.util.Date, 債券注文種別判定, 債券銘柄, String, Branch)
        //債券約定日情報を生成する 
        //[引数] 
        //発注日：get発注日 
        //債券注文種別判定：生成した債券注文種別判定 
        //債券銘柄：get債券銘柄 
        //決済区分：get債券注文単位By注文ID.get決済区分 
        //部店：get部店
        WEB3BondOrderManager l_bondOrderManager = 
            (WEB3BondOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getOrderManager();
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo = 
            l_bondOrderManager.createBondExecutionDateInfo(
                l_datBizDate, 
                l_bondOrderTypeJudge, 
                l_bondProduct,
                l_strSettlementDiv,
                l_branch);
        
        //1.8 is外貨建()
        //外貨建か判定する
        boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
        
        //1.9 is外貨建＝＝trueの場合
        BigDecimal l_bdFxRate = null;
        if (l_blnIsForeignCurrency)
        {
            //1.9.1 get為替レート(債券銘柄, 債券注文種別判定, String, BigDecimal, boolean)
            //為替レートを取得する。 
            //[引数] 
            //債券銘柄：get債券銘柄 
            //債券注文種別判定：生成した債券注文種別判定 
            //決済区分：get決済区分 
            //入力為替レート：　@0 
            //is約定計算：true
            l_bdFxRate = l_bondOrderManager.getFxRate(
                l_bondProduct, 
                l_bondOrderTypeJudge, 
                l_strSettlementDiv, 
                new BigDecimal("0"),
                true);
        }
        
        //1.10 getQuantity()
        //数量を取得
        BondOrderUnitRow l_bondOrderUnitRow = 
            (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
       
        BigDecimal l_bdQuantity = null;
        double l_dblQuantity = 0D;
        if (l_bondOrderUnitRow.getQuantityIsSet())
        {
            l_bdQuantity = new BigDecimal(String.valueOf(l_bondOrderUnit.getQuantity()));
            l_dblQuantity = l_bondOrderUnit.getQuantity();
        }   
        
        //1.11 getLimitPrice()
        //指値を取得
        BigDecimal l_bdLimitPrice = null;
        double l_dblLimitPrice = 0D;
        if (!l_bondOrderUnitRow.getLimitPriceIsNull())
        {
            l_bdLimitPrice = new BigDecimal(String.valueOf(l_bondOrderUnit.getLimitPrice()));
            l_dblLimitPrice = l_bondOrderUnit.getLimitPrice();
        }
        
        //1.12 calc受渡代金(債券注文種別判定, BigDecimal, BigDecimal, BigDecimal, 債券銘柄, 債券約定日情報)
        //債券受渡代金計算結果オブジェクトを生成する 
        //[引数] 
        //債券注文種別判定：生成した債券注文種別判定 
        //数量：getQuantity 
        //注文単価：getLimitPrice 
        //為替レート：get為替レート（※is外貨建()の戻り値 == falseの場合、nullをセットする。） 
        //債券銘柄：get債券銘柄 
        //債券約定日情報：生成した債券約定日情報 
        WEB3BondBizLogicProvider l_bizLogicProvider = 
            (WEB3BondBizLogicProvider) l_finApp.getTradingModule(
                ProductTypeEnum.BOND).getBizLogicProvider();
        
        WEB3BondEstimatedPriceCalcResult l_priceCalcResult =
            l_bizLogicProvider.calcEstimatedPrice(
                l_bondOrderTypeJudge, 
                l_bdQuantity,
                l_bdLimitPrice,
                l_bdFxRate,
                l_bondProduct,
                l_bondExecuteDateInfo);
        
        //1.13 債券約定更新インタセプタ()
        //インタセプタを生成する
        WEB3BondExecuteUpdateInterceptor l_bondExecuteUpdateInterceptor = 
            new WEB3BondExecuteUpdateInterceptor();
        
        //1.14 プロパティセット
        //生成したインタセプタに以下のプロパティをセットする。  
        //債券約定日情報：create約定日情報 
        //債券受渡代金計算結果：calc受渡代金 
        //債券銘柄：get債券銘柄 
        l_bondExecuteUpdateInterceptor.setBondExecuteDateInfo(l_bondExecuteDateInfo);
        l_bondExecuteUpdateInterceptor.setBondEstimatedPriceCalcResult(l_priceCalcResult);
        l_bondExecuteUpdateInterceptor.setBondProduct(l_bondProduct);
        
        //1.15 債券注文種別判定.is売却注文( )＝＝false　@かつ　@get決済区分＝＝円貨の場合
        if (!l_bondOrderTypeJudge.isSellOrder() && 
            WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(l_strSettlementDiv))
        {
            //1.15.1  get補助口座()
            //補助口座を取得する 
            //[引数] 
            //口座ID：債券注文単位.get口座ID 
            //補助口座ID：債券注文単位.get補助口座ID
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = 
                    (WEB3GentradeSubAccount)l_web3GentradeAccountManager.getSubAccount(
                        l_bondOrderUnit.getAccountId(), 
                        l_bondOrderUnit.getSubAccountId());
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
            
            //1.15.2 BondChangeOrderSpec(arg0 : long, arg1 : long, arg2 : double, arg3 : double)
            //債券変更注文内容オブジェクトを生成 
            //[引数] 
            //注文ID：債券注文単位.get注文ID 
            //注文単位ID：債券注文単位,get注文単位ID 
            //数量：getQuantity 
            //単価：getLimitPrice 
            BondChangeOrderSpec l_changeOrderSpec = 
                new BondChangeOrderSpec(
                    l_bondOrderUnit.getOrderId(),
                    l_bondOrderUnit.getOrderUnitId(),
                    l_dblQuantity,
                    l_dblLimitPrice);
            
            //1.15.3 validate取引余力(補助口座 : 補助口座, 注文内容インタセプタ : Object[], 
            //注文内容 : Object[], 注文種別 : OrderTypeEnum, 余力更新フラグ : boolean)
            //余力チェック 
            //[validate取引余力()の引数] 
            //補助口座：get補助口座 
            //注文内容インタセプタ：債券約定更新インタセプタ 
            //注文内容：BondChangeOrderSpec 
            //注文種別：OrderTypeEnum.債券買注文 
            //余力更新フラグ：true
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            Object[] l_objExecuteUpdateInterceptor = new Object[]{l_bondExecuteUpdateInterceptor};
            Object[] l_objChangeOrderSpecs = new Object[]{l_changeOrderSpec};
            WEB3TPTradingPowerResult l_tPTradingPowerResult = 
                l_service.validateTradingPower(
                    l_subAccount, 
                    l_objExecuteUpdateInterceptor, 
                    l_objChangeOrderSpecs, 
                    OrderTypeEnum.BOND_BUY, 
                    true);
            
            //1.15.4 is判定フラグ()
            //余力結果を判定する
            if (l_tPTradingPowerResult != null)
            {
                boolean l_blnIsResultFlg = l_tPTradingPowerResult.isResultFlg();
                
                //1.15.5 is判定フラグ＝＝falseの場合、例外をスローする。
                if (!l_blnIsResultFlg)
                {
                    log.debug("取引余力チェックエラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01306,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "取引余力チェックエラー。");
                }
            }
        }     
        //1.16 債券管理者注文受付更新インタセプタ()
        //インタセプタを生成する
        WEB3AdminBondOrderAcceptUpdateInterceptor l_orderAcceptUpdateInterceptor = 
            new WEB3AdminBondOrderAcceptUpdateInterceptor();
        
        //1.17 accept新規注文(long, 債券管理者デフォルトインタセプタ)
        //新規注文受付をする 
        //[引数]  
        //注文ID： 債券注文単位.get注文ID 
        //債券管理者デフォルトインタセプタ： 債券管理者注文受付更新インタセプタ
        WEB3AdminBondExecuteNotifyService l_notifyService = 
            (WEB3AdminBondExecuteNotifyService) Services.getService(
                WEB3AdminBondExecuteNotifyService.class);
        l_notifyService.acceptNewOrder(
            l_bondOrderUnit.getOrderId(), 
            l_orderAcceptUpdateInterceptor);
        
        //1.18.notify約定(BondOrderUnit, 債券管理者デフォルトインタセプタ)
        //約定処理をする 
        //[引数]  
        //　@債券注文単位： get債券注文単位By注文ID 
        //　@債券管理者デフォルトインタセプタ： 債券約定更新インタセプタ
        l_bondOrderUnit = l_bondOrderManager.getBondOrderUnitByOrderId(
            l_bondOrderUnit.getOrderId());
        
        l_notifyService.notifyExecute(
            l_bondOrderUnit, l_bondExecuteUpdateInterceptor);         
          
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * (to注文エラー理由コード)<BR>
     * 引数のエラー情報に対応する注文エラー理由コードを返却する。<BR>
     * <BR>  
     * ※返却される注文エラー理由コードについては、 <BR>
     * 　@DBレイアウト「債券注文単位テーブル仕様.xls#（注文単位テーブル補足）注文エラー理由コード」参照。<BR> 
     * <BR>
     * １）パラメータ.エラー情報により、注文エラー理由コードを決定する。<BR>
     * <BR>
     * 　@[パラメータ.エラー情報 == "預り金不足"の場合]<BR>  
     * 　@(validate取引余力()の結果、スローされた例外の場合)<BR>  
     * 　@　@注文エラー理由コード = "預り金不足エラー" <BR> 
     * <BR>
     * 　@[上記以外の場合]<BR>  
     * 　@　@注文エラー理由コード = "その他エラー"<BR>  
     * <BR>
     * ２）　@決定した注文エラー理由コードを返却する。<BR> 
     * @@param l_errorInfo - (エラー情報)<BR>
     * エラー情報<BR>
     * @@roseuid 44CB3777025E
     */
    protected String toOrderErrorReasonCode(ErrorInfo  l_errorInfo) 
    {
        final String STR_METHOD_NAME = "toOrderErrorReasonCode(ErrorInfo)";
        log.entering(STR_METHOD_NAME);
        
        String l_strErrorCode = null;
        
        //[パラメータ.エラー情報 == "預り金不足"の場合]
        if (WEB3ErrorCatalog.BUSINESS_ERROR_01306.equals(l_errorInfo))
        {
            //注文エラー理由コード = "預り金不足エラー"
            l_strErrorCode = WEB3ErrorReasonCodeDef.DEPOSIT_MONEY_SHORT_ERROR;
        }
        //[上記以外の場合]
        else
        {
            //注文エラー理由コード = "その他エラー"
            l_strErrorCode = WEB3ErrorReasonCodeDef.OTHRE_ERROR;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strErrorCode;
    }
    
    
    /**
     * (updateエラー発生注文)<BR>
     * エラー発生した注文単位の注文エラー理由コードなどをupdateする。<BR>
     * <BR>  
     * １）　@エラー発生した注文の注文エラー理由コードをupdateする。<BR>
     * <BR>
     * 　@１－１）　@エラー発生した注文の注文単位レコードをupdateする。 <BR>
     * 　@　@１－１－１）債券注文単位.getDataSourceObject()から債券注文単位Paramsを取得する。<BR> 
     * 　@　@１－１－２）注文単位のクローンを作成する。new 債券注文単位Params(上記で取得した債券注文単位Params)<BR>
     * 　@　@１－１－３）注文単位のクローンを元に、注文単位レコードをupdateする。<BR> 
     * 　@　@　@　@<更新内容><BR>  
     * 　@　@　@　@　@注文エラー理由コード = 引数.注文エラー理由コード <BR> 
     * 　@　@　@　@　@更新日付 = 現在日時  <BR>
     * <BR>
     * 　@１－２）　@以下の条件に該当する、エラー発生した注文の注文（ヘッダ）の更新日時をupdateする。<BR>  
     * <BR>
     * 　@　@<条件> <BR>
     * 　@　@注文（ヘッダ）テーブル.注文ID = 注文単位のクローン.get注文ID <BR> 
     * <BR>
     * 　@　@<更新内容><BR>
     * 　@　@注文（ヘッダ）テーブル.更新日付 = 現在日時  <BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@param l_strOrderErrorReasonCode - (注文エラー理由コード)<BR>
     * 注文エラー理由コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44CB3777025E
     */
    protected void updateErrorOrder(BondOrderUnit l_bondOrderUnit,
        String l_strOrderErrorReasonCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "updateErrorOrder(BondOrderUnit String)";
        log.entering(STR_METHOD_NAME);
    
        //１）　@エラー発生した注文の注文エラー理由コードをupdateする。
        //１－１）　@エラー発生した注文の注文単位レコードをupdateする。
        //１－１－１）債券注文単位.getDataSourceObject()から債券注文単位Paramsを取得する。
        BondOrderUnitRow l_orderUnitRow = 
            (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
    
        //１－１－２）注文単位のクローンを作成する。new 債券注文単位Params(上記で取得した債券注文単位Params)
        BondOrderUnitParams l_bondOrderUnitParams = 
            new BondOrderUnitParams(l_orderUnitRow);
        
        //１－１－３）注文単位のクローンを元に、注文単位レコードをupdateする。
        //<更新内容>
        //注文エラー理由コード = 引数.注文エラー理由コード
        //更新日付 = 現在日時
        l_bondOrderUnitParams.setErrorReasonCode(l_strOrderErrorReasonCode);
        l_bondOrderUnitParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_bondOrderUnitParams);
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
               
        //１－２）　@以下の条件に該当する、エラー発生した注文の注文（ヘッダ）の更新日時をupdateする。
        //<条件>
        //注文（ヘッダ）テーブル.注文ID = 注文単位のクローン.get注文ID
        //<更新内容>
        //注文（ヘッダ）テーブル.更新日付 = 現在日時
        try
        {
            String l_strWhere = " order_id = ? ";
            Object[] l_objCont = {new Long(l_bondOrderUnit.getOrderId())};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();        
            List l_lisOrderRows = l_queryProcessor.doFindAllQuery(
                BondOrderRow.TYPE, 
                l_strWhere,
                l_objCont);
            
            if (l_lisOrderRows != null && !l_lisOrderRows.isEmpty())
            {
                int l_intCnt = l_lisOrderRows.size();
                
                if (l_intCnt == 1)
                {
                    BondOrderRow l_ordertRows = 
                        (BondOrderRow)l_lisOrderRows.get(0);
            
                    BondOrderParams l_orderParams = 
                        new BondOrderParams(l_ordertRows);
                
                     l_orderParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                    
                     l_queryProcessor.doUpdateQuery(l_orderParams);
                }
                else
                {
                    log.debug("注文（ヘッダ）テーブルがデータ不整合エラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "データ不整合エラー。");
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
        log.exiting(STR_METHOD_NAME);
    }
}
@
