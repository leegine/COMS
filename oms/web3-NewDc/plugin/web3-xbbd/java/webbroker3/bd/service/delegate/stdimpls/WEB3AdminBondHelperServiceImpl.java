head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondHelperServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者ヘルパーサービスImpl(WEB3AdminBondHelperServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 齊珂(中訊) 新規作成      
Revesion History : 2006/10/16 張騰宇 (中訊) モデルNo.106.108.111.118.126.128
Revesion History : 2007/03/09 徐大方 (中訊) 仕様変更・モデル159
Revesion History : 2007/07/25 劉立峰 (中訊) 仕様変更・モデル238
Revesion History : 2007/07/25 劉立峰 (中訊) 仕様変更・モデル246
Revesion History : 2009/07/24 武波 (中訊) 仕様変更・モデル261
*/

package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.WEB3BondBranchCondition;
import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderManager;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.WEB3BondProductManager;
import webbroker3.bd.data.BondBranchConditionDao;
import webbroker3.bd.data.BondBranchConditionRow;
import webbroker3.bd.data.CustodianRow;
import webbroker3.bd.define.WEB3BondCancelDivDef;
import webbroker3.bd.define.WEB3BondExecuteChangeButtonDivDef;
import webbroker3.bd.define.WEB3BondLockReleaseButtonDivDef;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.bd.message.WEB3AdminBondAccountInfo;
import webbroker3.bd.message.WEB3AdminBondCustodianUnit;
import webbroker3.bd.message.WEB3AdminBondFxRateInfo;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.bd.message.WEB3AdminBondProductInfo;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BondOrderLockUseDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3PaymentDateDetDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券管理者ヘルパーサービスImpl)<BR>
 * 債券管理者ヘルパーサービスImplクラス
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminBondHelperServiceImpl implements WEB3AdminBondHelperService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminBondHelperServiceImpl.class);
    /**
     * @@roseuid 44E336290251
     */
    
    public WEB3AdminBondHelperServiceImpl() 
    {
     
    }
    
    /**
     * (to顧客情報)<BR>
     * 引数より顧客情報を作成する<BR>
     * <BR>
     * １）引数.債券注文単位より口座IDを取得<BR>
     * <BR>
     * ２）拡張アカウントマネージャ.get顧客()をコールして、顧客オブジェクトを取得<BR>
     * 　@　@[引数]<BR>
     * 　@　@口座ID：取得した口座ID<BR>
     * <BR>
     * ３）this.to顧客情報(顧客)を呼ぶ <BR>
     * [引数] <BR>
     * 顧客：２）で取得した顧客オブジェクト <BR>
     * <BR>
     * ４）顧客情報オブジェクトを返却 <BR>
     * @@param l_orderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CAFF090399
     */
    public WEB3AdminBondAccountInfo toAccountInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " toAccountInfo(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //１）引数.債券注文単位より口座IDを取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {
            //２）拡張アカウントマネージャ.get顧客()をコールして、顧客オブジェクトを取得
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_web3GentradeAccountManager.getMainAccount(
                    l_orderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error("__error in 拡張アカウントマネージャから顧客を取得__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
      
        // ３）this.to顧客情報(顧客)を呼ぶ 
        WEB3AdminBondAccountInfo  l_adminBondAccountInfo = 
            this.toAccountInfo(l_mainAccount);
        
        // ４）顧客情報オブジェクトを返却
        return l_adminBondAccountInfo;
    }
    
    /**
     * (to注文情報)<BR>
     * 引数より注文情報を作成する<BR>
     * <BR>
     * １）注文情報オブジェクトを生成<BR>
     * <BR>    
     * ２）生成した注文情報オブジェクトに下記のプロパティをセットする<BR>
     * <BR>
     * 　@注文種別      =引数.債券注文単位.get注文種別()<BR>
     * 　@取引            =引数.債券注文単位.get取引()<BR>
     * 　@決済区分      =引数.債券注文単位.get決済区分()<BR>
     * 　@注文数量      =引数.債券注文単位.get数量() <BR>
     *   単価            =引数.債券注文単位.get指値()<BR> 
     * <BR>
     * 　@-為替レート設定<BR>
     * 　@債券プロダクトマネージャ.get債券銘柄<BR>
     * 　@　@(引数.債券注文単位.get銘柄ID).is外貨建==trueの場合<BR>
     *      引数.債券注文単位.約定為替レート != NULLの場合<BR>
     * 　@　@ 為替レート = 引数.注文単位.get約定為替レート() <BR>
     *      上記ではなく、引数.債券注文単位.基準為替レート != NULLの場合 <BR>
     * 　@　@ 為替レート = 引数.注文単位.get基準為替レート()  <BR>
     * <BR>
     * 　@税区分   =引数.債券注文単位.ge税区分()<BR>
     * <BR>
     * ３）生成した注文情報オブジェクトを返す。<BR>
     * @@param l_orderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondOrderInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CAFDD80134
     */
    public WEB3AdminBondOrderInfo toOrderInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toOrderInfo(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //１）注文情報オブジェクトを生成
        WEB3AdminBondOrderInfo l_adminBondOrderInfo = 
            new WEB3AdminBondOrderInfo();
        
        //２）生成した注文情報オブジェクトに下記のプロパティをセットする
        //   注文種別      =引数.債券注文単位.get注文種別()
        l_adminBondOrderInfo.tradingType = l_orderUnit.getOrderType().intValue() + "";
        
        //   取引      =引数.債券注文単位.get取引()
        l_adminBondOrderInfo.dealType = l_orderUnit.getDealType();
        
        //   決済区分   =引数.債券注文単位.get決済区分()
        l_adminBondOrderInfo.settleDiv = l_orderUnit.getSettlementDiv();
        
        // 注文数量   =引数.債券注文単位.get数量()  
        l_adminBondOrderInfo.orderQuantity = 
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        
        //　@単価      =引数.債券注文単位.get指値() 
        l_adminBondOrderInfo.price = 
            WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
        
        //債券プロダクトマネージャ.get債券銘柄(引数.債券注文単位.get銘柄ID).is外貨建==trueの場合 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
         (WEB3BondProductManager) l_finApp.getTradingModule(
             ProductTypeEnum.BOND).getProductManager();
        
        long l_lngProductId = l_orderUnit.getProductId();
        
        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_orderUnit.getDataSourceObject();

        if(l_bondOrderUnitRow != null)
        {    
            //債券プロダクトマネージャ.get債券銘柄(引数.債券注文単位.get銘柄ID).is外貨建==trueの場合
            WEB3BondProduct l_bondProduct = (WEB3BondProduct) l_bondProductManager.getBondProduct(l_lngProductId);
            if (l_bondProduct != null)
            {
                boolean l_blnIsForeignCurrency = l_bondProduct.isForeignCurrency();
                
                if (l_blnIsForeignCurrency)
                {
                    //引数.債券注文単位.get約定為替レートIsNull == falseの場合
                    if (!l_bondOrderUnitRow.getExecFxRateIsNull())
                    {
                        //為替レート = 引数.注文単位.get約定為替レート()
                        l_adminBondOrderInfo.fxRate =
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecFxRate());
                    }

                    //引数.債券注文単位.get基準為替レートIsNull == falseの場合
                    else if (!l_bondOrderUnitRow.getBaseFxRateIsNull())
                    {
                        //上記ではなく、引数.債券注文単位.基準為替レート != NULLの場合
                        l_adminBondOrderInfo.fxRate =
                            WEB3StringTypeUtility.formatNumber(l_orderUnit.getBaseFxRate());
                    }
                }
            }
       }
        
        //税区分   =引数.債券注文単位.get税区分()
        l_adminBondOrderInfo.taxType = l_orderUnit.getTaxType().intValue() + "";
        
        //３）生成した注文情報オブジェクトを返す。
        log.exiting(STR_METHOD_NAME);
        return l_adminBondOrderInfo;
    }
    
    /**
     * (to約定情報)<BR>
     * 引数より約定情報を作成する<BR>
     * <BR>
     * １）注文約定情報オブジェクトを生成<BR>
     * <BR>
     * ２）債券銘柄を取得する。<BR>
     * 　@　@債券プロダクトマネージャ.get債券銘柄(銘柄ID)<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@　@銘柄ID：債券注文単位.get銘柄ID()<BR>
     * <BR>
     * ３）発注日を取得する。<BR>
     * 　@　@　@取引時間管理.get発注日()<BR>
     * <BR>
     * ４）生成した約定情報オブジェクトに下記のプロパティをセットする<BR>
     * ※各項目がNULLでない場合のみ、セットする。 <BR>
     * 売買代金(外貨) ＝ 債券注文単位.get売買代金(外貨)()<BR>
     * 売買代金(円貨) ＝ 債券注文単位.get売買代金(円貨)()<BR>
     * 経過利子(外貨) ＝ 債券注文単位.get経過利子(外貨)()<BR>
     * 経過利子(円貨) ＝ 債券注文単位.get経過利子(円貨)()<BR>
     * 受渡代金(外貨) ＝ 債券注文単位.get受渡代金(外貨)()<BR>
     * 受渡代金(円貨) ＝ 債券注文単位.get受渡代金(円貨)()<BR>
     * 経過日数　@　@　@　@ ＝ 債券注文単位.get経過日数()<BR>
     * 基準日数 　@　@　@　@＝ 債券注文単位.get基準日数()<BR>
     * 注文約定区分   ＝ 債券注文単位.get注文約定区分()<BR>
     * <BR>
     * ５）カストディアンの設定<BR>
     * 　@・CustodianRowオブジェクトを取得する<BR>
     * 　@　@債券データマネージャサービス.getカストディアン(証券会社, カストディアンコード)<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@証券会社：拡張アカウントマネージャ.get部店（債券注文単位.get部店ID）.get証券会社()<BR>
     * 　@　@　@カストディアンコード：債券注文単位.getカストディアンコード<BR>
     * 　@・カストディアンに値をセットする<BR>
     * 　@　@カストディアン＝債券管理者ヘルパーサービス.toカストディアン<BR>(CustodianRow)<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@CustodianRow：取得したCustodianRow<BR>
     * <BR>
     * ６）約定数量、約定単価の設定<BR>
     * <BR>
     * 　@６－１）注文約定区分 == 未約定の場合<BR>
     * 　@　@　@　@　@　@約定数量 　@　@　@＝　@債券注文単位.get数量() <BR>
     * 　@　@　@　@　@　@約定単価 　@　@　@＝ 債券注文単位.get指値()<BR>
     * <BR>
     * 　@６－２）注文約定区分 == 約定済の場合<BR>
     * 　@　@　@　@　@　@約定数量　@　@　@ ＝　@債券注文単位.get約定数量() <BR>
     * 　@　@　@　@　@　@約定単価 　@　@　@＝ 債券注文単位.get約定単価() <BR>
     * <BR>
     * ７）約定為替レート、約定日、受渡日、現地約定日、現地受渡日、入金日の設定<BR>
     * <BR>
     * 　@７－１）注文約定区分 == 未約定 かつ 債券注文単位.債券タイプ == 外国債券 の場合<BR>
     * <BR>
     * 　@　@①@債券銘柄.is外貨建＝＝trueの場合<BR>
     * 　@　@　@　@　@約定為替レート＝債券注文マネージャ.get為替レート(債券銘柄, 注文種別判定, 決済区分,<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@入力為替レート, is約定計算)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@債券銘柄：債券銘柄<BR>
     * 　@　@　@　@　@　@　@　@債券注文種別判定：債券注文単位.get債券注文種別判定<BR>
     * 　@　@　@　@　@　@　@　@決済区分：債券注文単位.get決済区分<BR>
     * 　@　@　@　@　@　@　@　@入力為替レート：　@0<BR>
     * 　@　@　@　@　@　@　@　@is約定計算：　@true<BR>
     * <BR>
     * 　@　@②約定日　@　@　@　@　@ ＝ 債券銘柄.get約定日(発注日)<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@発注日＝取得した発注日<BR>
     * <BR>
     * 　@　@③受渡日 　@　@　@　@　@＝ 債券銘柄.get受渡日(約定日, 債券注文種別判定, 決済区分, 部店)<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@約定日＝取得した約定日<BR>
     * 　@　@　@　@　@　@債券注文種別判定＝債券注文単位.get債券注文種別判定<BR>
     * <BR>
     * 　@　@④債券銘柄.is外国債券＝＝trueの場合<BR>
     * 　@　@　@　@　@現地約定日 　@　@＝ 債券銘柄.get現地約定日(現地発注日)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@現地発注日＝債券銘柄.get現地発注日(発注日)<BR>
     * 　@　@　@　@　@<BR>
     * 　@　@　@　@　@現地受渡日 　@　@＝ 債券銘柄.get現地受渡日(現地約定日, 債券注文種別判定)<BR>
     * 　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@現地約定日＝取得した現地約定日<BR>
     * 　@　@　@　@　@　@　@　@債券注文種別判定＝債券注文単位.get債券注文種別判定<BR>
     * <BR>
     * 　@　@⑤入金日 　@　@　@　@　@＝ 債券銘柄.get入金日(約定日, 債券注文種別判定, 決済区分, 部店) <BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@約定日＝取得した約定日<BR>
     * 　@　@　@　@　@　@債券注文種別判定＝債券注文単位.get債券注文種別判定<BR>
     * 　@　@　@　@　@　@決済区分＝債券注文単位.get決済区分<BR>
     * 　@　@　@　@　@　@部店＝拡張アカウントマネージャ.get部店（債券注文単位.get部店ID）<BR>
     * <BR>
     * 　@７－２）注文約定区分 == 約定済 または 債券タイプ ≠ 外国債券 の場合<BR>
     * <BR>
     * 　@　@　@　@　@　@約定為替レート ＝ 債券注文単位.get約定為替レート() <BR>
     * 　@　@　@　@　@　@約定日　@　@　@　@　@ ＝ 債券注文単位.get約定日()<BR>
     * 　@　@　@　@　@　@現地約定日 　@　@＝ 債券注文単位.get現地約定日()<BR>
     * 　@　@　@　@　@　@受渡日 　@　@　@　@　@＝ 債券注文単位.get受渡日() <BR>
     * 　@　@　@　@　@　@現地受渡日 　@　@＝ 債券注文単位.get現地受渡日()<BR>
     * 　@　@　@　@　@　@入金日　@　@　@ 　@　@＝ 債券注文単位.get入金日()<BR>
     * <BR>
     * ８）生成した約定情報オブジェクトを返却。<BR>
     * <BR>
     * @@param l_orderUnit - (債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CAFDD80163
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toOrderExecInfo(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //１）注文約定情報オブジェクトを生成
        WEB3AdminBondOrderExecInfo l_adminBondOrderExecInfo = 
            new WEB3AdminBondOrderExecInfo();      

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3BondProductManager l_bondProductManager = 
         (WEB3BondProductManager) l_finApp.getTradingModule(
             ProductTypeEnum.BOND).getProductManager();
        
        //２）債券銘柄を取得する。 
        WEB3BondProduct l_bondProduct = 
            (WEB3BondProduct) l_bondProductManager.getBondProduct(l_orderUnit.getProduct().getProductId());
        
        
        log.debug("getProductCode=====" +l_bondProduct.getProductCode());
        log.debug("getProductId=====" +l_bondProduct.getProductId());

        if (l_bondProduct == null)
        {
            log.debug("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //３）発注日を取得する。 
        //　@　@　@取引時間管理.get発注日() 
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        log.debug("l_datBizDate===" + l_datBizDate.getTime());
        
        //４）生成した約定情報オブジェクトに下記のプロパティをセットする 
        //※各項目がNULLでない場合のみ、セットする。 
        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        //売買代金(外貨) ＝ 債券注文単位.get売買代金(外貨)() 
        if (!l_bondOrderUnitRow.getForeignTradingPriceIsNull())
        {
            l_adminBondOrderExecInfo.foreignTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignTradingPrice());
        }
        
        //売買代金(円貨) ＝ 債券注文単位.get売買代金(円貨)() 
        if (!l_bondOrderUnitRow.getTradingPriceIsNull())
        {
            l_adminBondOrderExecInfo.yenTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getTradingPrice());
        }
        
        //経過利子(外貨) ＝ 債券注文単位.get経過利子(外貨)()
        if (!l_bondOrderUnitRow.getForeignAccruedInterestIsNull())
        {
            l_adminBondOrderExecInfo.foreignAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignAccruedInterest());
        }
        
        //経過利子(円貨) ＝ 債券注文単位.get経過利子(円貨)() 
        if (!l_bondOrderUnitRow.getAccruedInterestIsNull())
        {
            l_adminBondOrderExecInfo.yenAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getAccruedInterest());
        }
        
        //受渡代金(外貨) ＝ 債券注文単位.get受渡代金(外貨)() 
        if (!l_bondOrderUnitRow.getForeignEstimatedPriceIsNull())
        {
            l_adminBondOrderExecInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getForeignEstimatedPrice());
        }
        
        //受渡代金(円貨) ＝ 債券注文単位.get受渡代金(円貨)() 
        if (!l_bondOrderUnitRow.getEstimatedPriceIsNull())
        {
            l_adminBondOrderExecInfo.yenDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getEstimatedPrice());
        }
        
        //経過日数　@　@　@　@ ＝ 債券注文単位.get経過日数() 
        if (!l_bondOrderUnitRow.getElapsedDaysIsNull())
        {
            l_adminBondOrderExecInfo.elapsedDays = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getElapsedDays());
        }
        
        //基準日数 　@　@　@　@＝ 債券注文単位.get基準日数() 
        if (!l_bondOrderUnitRow.getCalcBaseDaysIsNull())
        {
            l_adminBondOrderExecInfo.calcBaseDays = 
                WEB3StringTypeUtility.formatNumber(l_orderUnit.getCalcBaseDays());
        }
        
        //注文約定区分   ＝ 債券注文単位.get注文約定区分() 
        if (l_bondOrderUnitRow.getOrderExecStatusIsSet())
        {
            l_adminBondOrderExecInfo.executionState = l_orderUnit.getOrderExecStatus();
        }

        
        //CustodianRowオブジェクトを取得する 
        WEB3BondDataManagerService l_bondDataManagerService = 
            (WEB3BondDataManagerService) Services.getService(WEB3BondDataManagerService.class);
        WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = null;
        if (l_orderUnit.getCustodianCode() != null)
        {
            CustodianRow l_custodianRow = l_bondDataManagerService.getCustodian(
                l_bondProduct.getInstitution(), 
                l_orderUnit.getCustodianCode());
            //カストディアンに値をセットする 
            l_adminBondCustodianUnit = 
                this.toCustodian(l_custodianRow);
        }
        
        if (l_adminBondCustodianUnit != null)
        {
            l_adminBondOrderExecInfo.custodianInfo = l_adminBondCustodianUnit;
        }
        
        if (l_bondOrderUnitRow.getOrderExecStatusIsSet())
        {    
            //注文約定区分＝＝未約定の場合
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {
                //約定数量 　@　@　@＝　@債券注文単位.get数量()
                if (l_bondOrderUnitRow.getQuantityIsSet())
                {
                    l_adminBondOrderExecInfo.execFaceAmount =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
                }
                //約定単価 　@　@　@＝ 債券注文単位.get指値()
                if (!l_bondOrderUnitRow.getLimitPriceIsNull())
                {
                    l_adminBondOrderExecInfo.execPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getLimitPrice());
                }
                //債券注文単位.債券タイプ == 外国債券 の場合
                if(BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
                {
                    //債券銘柄.is外貨建＝＝trueの場合
                    if (l_bondProduct.isForeignCurrency())
                    {
                        WEB3BondOrderManager l_bondOrderManager =
                            (WEB3BondOrderManager)l_finApp.getTradingModule(
                                ProductTypeEnum.BOND).getOrderManager();
                        //　@　@約定為替レート＝債券注文マネージャ.get為替レート(債券銘柄,
                        // 注文種別判定, 決済区分, 入力為替レート, is約定計算)
                        //　@　@[引数]
                        //　@　@　@債券銘柄：債券銘柄
                        //　@　@　@債券注文種別判定：債券注文単位.get債券注文種別判定
                        //　@　@　@決済区分：債券注文単位.get決済区分
                        //　@　@　@入力為替レート：　@0
                        //　@　@　@is約定計算：　@true
                        BigDecimal l_bdExecFxRate =
                            l_bondOrderManager.getFxRate(
                                l_bondProduct,
                                l_orderUnit.getBondOrderTypeJudge(),
                                l_orderUnit.getSettlementDiv(),
                                new BigDecimal("0"),
                                true);
                         l_adminBondOrderExecInfo.execFxRate =
                             WEB3StringTypeUtility.formatNumber(l_bdExecFxRate.doubleValue());

                    }

                    //  約定日　@＝ 債券銘柄.get約定日(発注日)
                    Date l_datExecDate = l_bondProduct.getExecDate(l_datBizDate);
                    if (l_datExecDate != null)
                    {
                        l_adminBondOrderExecInfo.domesticExecutionDate = l_datExecDate;
                    }

                    // 受渡日   ＝ 債券銘柄.get受渡日(約定日, 債券注文種別判定)
                    Date l_datDeliveryDate = l_bondProduct.getDeliveryDate(
                        l_adminBondOrderExecInfo.domesticExecutionDate,
                        l_orderUnit.getBondOrderTypeJudge());
                    if (l_datDeliveryDate != null)
                    {
                        l_adminBondOrderExecInfo.domesticDeliveryDate = l_datDeliveryDate;
                    }

                    // 債券銘柄.is外国債券＝＝trueの場合
                    if (l_bondProduct.isForeignBond())
                    {
                        //現地発注日＝債券銘柄.get現地発注日(発注日)
                        Date l_datForeignBizDate = l_bondProduct.getForeignBizDate(l_datBizDate);

                        //現地約定日 　@　@＝ 債券銘柄.get現地約定日(現地発注日)
                        if (l_datForeignBizDate != null)
                        {
                        l_adminBondOrderExecInfo.foreignExecutionDate =
                            l_bondProduct.getForeignExecDate(
                                l_bondProduct.getForeignExecDate(l_datForeignBizDate));
                        }


                        //現地受渡日 　@　@＝ 債券銘柄.get現地受渡日(現地約定日, 債券注文種別判定)
                        Date l_datForeignDeliveryDate = l_bondProduct.getForeignDeliveryDate(
                            l_adminBondOrderExecInfo.foreignExecutionDate,
                            l_orderUnit.getBondOrderTypeJudge());

                        if (l_datForeignDeliveryDate != null)
                        {
                            l_adminBondOrderExecInfo.foreignDeliveryDate = l_datForeignDeliveryDate;
                        }
                    }

                    WEB3GentradeAccountManager l_web3GentradeAccountManager =
                        (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                    Branch l_branch = null;
                    try
                    {
                        l_branch = l_web3GentradeAccountManager.getBranch(l_orderUnit.getBranchId());
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.debug("テーブルに該当するデータがありません。", l_ex);
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_ex.getMessage(),
                            l_ex);
                    }
                    //入金日 ＝ 債券銘柄.get入金日(約定日, 債券注文種別判定, 決済区分, 部店)
                    l_adminBondOrderExecInfo.paymentDate =
                        l_bondProduct.getPaymentDate(
                            l_adminBondOrderExecInfo.domesticExecutionDate,
                            l_orderUnit.getBondOrderTypeJudge(),
                            l_orderUnit.getSettlementDiv(),
                            l_branch);

                }
            }

            //注文約定区分＝＝約定済の場合
            if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {

                //約定数量 　@　@　@＝　@債券注文単位.get約定数量()
                if (!l_bondOrderUnitRow.getExecutedQuantityIsNull())
                {
                    l_adminBondOrderExecInfo.execFaceAmount =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedQuantity());
                }

                //約定単価 　@　@　@＝ 債券注文単位.get約定単価()
                if (!l_bondOrderUnitRow.getExecutedPriceIsNull())
                {
                    l_adminBondOrderExecInfo.execPrice =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecutedPrice());
                }
            }
            //注文約定区分 == 約定済 または 債券タイプ ≠ 外国債券 の場合
            if(WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()) ||
                !BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
            {
                //約定為替レート ＝ 債券注文単位.get約定為替レート()
                if (!l_bondOrderUnitRow.getExecFxRateIsNull())
                {
                    l_adminBondOrderExecInfo.execFxRate =
                        WEB3StringTypeUtility.formatNumber(l_orderUnit.getExecFxRate());
                }

                //約定日　@　@　@　@　@ ＝ 債券注文単位.get約定日()
                if (l_orderUnit.getExecDate() != null)
                {
                    l_adminBondOrderExecInfo.domesticExecutionDate = l_orderUnit.getExecDate();
                }

                //現地約定日 　@　@＝ 債券注文単位.get現地約定日()
                if (l_orderUnit.getForeignExecDate() != null)
                {
                    l_adminBondOrderExecInfo.foreignExecutionDate = l_orderUnit.getForeignExecDate();
                }

                //受渡日 　@　@　@　@　@＝ 債券注文単位.get受渡日()
                l_adminBondOrderExecInfo.domesticDeliveryDate = l_orderUnit.getDeliveryDate();

                //現地受渡日 　@　@＝ 債券注文単位.get現地受渡日()
                if (l_orderUnit.getForeignDeliveryDate() != null)
                {
                    l_adminBondOrderExecInfo.foreignDeliveryDate = l_orderUnit.getForeignDeliveryDate();
                }

                //入金日　@　@　@ 　@　@＝ 債券注文単位.get入金日()
                l_adminBondOrderExecInfo.paymentDate = l_orderUnit.getPaymentDate();
            }
        }
        //５）生成した約定情報オブジェクトを返却。
        log.exiting(STR_METHOD_NAME);
        return l_adminBondOrderExecInfo;
    }
    
    /**
     * (to約定情報)<BR>
     * 引数より約定情報を作成する<BR>
     * <BR>
     * １）注文約定情報オブジェクトを生成<BR>
     * <BR>
     * ２）生成した約定情報オブジェクトに下記のプロパティをセットする<BR>
     * <BR>
     * 約定数量=債券受渡代金計算結果.get数量()<BR>
     * 約定単価=債券受渡代金計算結果.get単価()<BR>
     * 約定為替レート=債券受渡代金計算結果.get為替レート()<BR>
     * <BR>
     * 約定日=債券約定日情報.get約定日()<BR>
     * 現地約定日=債券約定日情報.get現地約定日()<BR>
     * 受渡日=債券約定日情報.get受渡日()<BR>
     * 現地受渡日=債券約定日情報.get現地受渡日()<BR>
     * 入金日=債券約定日情報.get入金日() <BR>
     * <BR>
     * 売買代金（外貨）=債券受渡代金計算結果.get売買代金（外貨）<BR>
     * 売買代金（円貨）=債券受渡代金計算結果.get売買代金（円貨）<BR>
     * 経過利子（外貨）=債券受渡代金計算結果.get経過利子（外貨）<BR>
     * 経過利子（円貨）=債券受渡代金計算結果.get経過利子（円貨）<BR>
     * 受渡代金（外貨）=債券受渡代金計算結果.get受渡代金（外貨）<BR>
     * 受渡代金（円貨）=債券受渡代金計算結果.get受渡代金（円貨）<BR>
     * 経過日数=債券受渡代金計算結果.get経過日数()<BR>
     * 基準日数=債券受渡代金計算結果.get基準日数()<BR>
     * <BR>
     * カストディアン=カストディアン<BR>
     * <BR>
     * 注文約定区分=拡張債券注文単位.get注文約定区分()<BR>
     * (拡張債券注文単位がnullの場合何もセットしない。)<BR>
     * <BR>
     * 警告区分一覧=債券受渡代金計算結果.get警告区分一覧()<BR>
     * をStringの配列に変換したもの
     * @@param l_executeDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報
     * @@param l_calcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果
     * @@param l_custodianUnit - (カストディアン)<BR>
     * カストディアン
     * @@param l_orderUnit - (拡張債券注文単位)<BR>
     * 債券注文単位
     * @@return webbroker3.bd.message.WEB3AdminBondOrderExecInfo
     * @@throws WEB3BaseException
     * @@roseuid 44DAD1D40282
     */
    public WEB3AdminBondOrderExecInfo toOrderExecInfo(
        WEB3BondExecuteDateInfo l_executeDateInfo, 
        WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondCustodianUnit l_custodianUnit, 
        WEB3BondOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "toOrderExecInfo(WEB3BondExecuteDateInfo, "+
            "WEB3BondEstimatedPriceCalcResult, "+
            "WEB3AdminBondCustodianUnit, "+
            "WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_executeDateInfo == null || l_calcResult == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
                
        //１）注文約定情報オブジェクトを生成
        WEB3AdminBondOrderExecInfo l_adminBondOrderExecInfo = 
            new WEB3AdminBondOrderExecInfo();      
               
        //２）生成した約定情報オブジェクトに下記のプロパティをセットする 
        //約定数量=債券受渡代金計算結果.get数量() 
        if (l_calcResult.getQuantity() != null)
        {
            l_adminBondOrderExecInfo.execFaceAmount = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getQuantity().doubleValue());
        }
        
        //約定単価=債券受渡代金計算結果.get単価()
        if (l_calcResult.getPrice() != null)
        {
            l_adminBondOrderExecInfo.execPrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getPrice().doubleValue());
        }

        //約定為替レート=債券受渡代金計算結果.get為替レート() 
        if (l_calcResult.getFxRate() != null)
        {
            l_adminBondOrderExecInfo.execFxRate = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getFxRate().doubleValue());
        }
        
        //約定日=債券約定日情報.get約定日() 
        l_adminBondOrderExecInfo.domesticExecutionDate = l_executeDateInfo.getExecuteDate();
        
        //現地約定日=債券約定日情報.get現地約定日() 
        l_adminBondOrderExecInfo.foreignExecutionDate = l_executeDateInfo.getForeignExecuteDate();
        
        //受渡日=債券約定日情報.get受渡日() 
        l_adminBondOrderExecInfo.domesticDeliveryDate = l_executeDateInfo.getDeliveryDate();
        
        //現地受渡日=債券約定日情報.get現地受渡日() 
        l_adminBondOrderExecInfo.foreignDeliveryDate = l_executeDateInfo.getForeignDeliveryDate();
        
        //入金日=債券約定日情報.get入金日() 
        l_adminBondOrderExecInfo.paymentDate = l_executeDateInfo.getPaymentDate();
        
        //売買代金（外貨）=債券受渡代金計算結果.get売買代金（外貨）
        if (l_calcResult.getForeignTradePrice() != null)
        {
            l_adminBondOrderExecInfo.foreignTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getForeignTradePrice().doubleValue());
        }
        
        //売買代金（円貨）=債券受渡代金計算結果.get売買代金（円貨） 
        if (l_calcResult.getTradingPrice() != null)
        {
            l_adminBondOrderExecInfo.yenTradePrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getTradingPrice().doubleValue());
        }
        
        //経過利子（外貨）=債券受渡代金計算結果.get経過利子（外貨） 
        if (l_calcResult.getForeignAccruedInterest() != null)
        {
            l_adminBondOrderExecInfo.foreignAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getForeignAccruedInterest().doubleValue()); 
        }
        
        //経過利子（円貨）=債券受渡代金計算結果.get経過利子（円貨） 
        if (l_calcResult.getAccruedInterest() != null)
        {
            l_adminBondOrderExecInfo.yenAccruedInterest = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getAccruedInterest().doubleValue());
        }
        
        //受渡代金（外貨）=債券受渡代金計算結果.get受渡代金（外貨）
        if (l_calcResult.getForeignEstimatedPrice() != null)
        {
            l_adminBondOrderExecInfo.foreignDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getForeignEstimatedPrice().doubleValue()); 
        }
        
        //受渡代金（円貨）=債券受渡代金計算結果.get受渡代金（円貨） 
        if (l_calcResult.getEstimatedPrice() != null)
        {
            l_adminBondOrderExecInfo.yenDeliveryPrice = 
                WEB3StringTypeUtility.formatNumber(l_calcResult.getEstimatedPrice().doubleValue());
        }

        //経過日数=債券受渡代金計算結果.get経過日数() 
        if (l_calcResult.getElapsedDays() != null)
        {
            l_adminBondOrderExecInfo.elapsedDays = l_calcResult.getElapsedDays().intValue() + "";
        }

        //基準日数=債券受渡代金計算結果.get基準日数() 
        if ( l_calcResult.getCalcBaseDays() != null)
        {
            l_adminBondOrderExecInfo.calcBaseDays = l_calcResult.getCalcBaseDays().intValue() + "";
        }

        //カストディアン=カストディアン 
        l_adminBondOrderExecInfo.custodianInfo = l_custodianUnit;

        //拡張債券注文単位がnullの場合何もセットしない。
        //注文約定区分=拡張債券注文単位.get注文約定区分() 
        if ( l_orderUnit != null)
        {        
            l_adminBondOrderExecInfo.executionState = l_orderUnit.getOrderExecStatus();
        }
        
        //警告区分一覧=債券受渡代金計算結果.get警告区分一覧()をStringの配列に変換したもの
        if (l_calcResult.getWarningDivList() != null && 
            !l_calcResult.getWarningDivList().isEmpty())
        {
            String[] l_strWarningDiv = new String[l_calcResult.getWarningDivList().size()]; 
            l_calcResult.getWarningDivList().toArray(l_strWarningDiv);
            l_adminBondOrderExecInfo.warningDiv = l_strWarningDiv;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_adminBondOrderExecInfo;
    }
    
    /**
     * (to銘柄情報)<BR>
     * to銘柄情報<BR>
     * <BR>
     * シーケンス図「to銘柄情報」参照
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄
     * @@return webbroker3.bd.message.WEB3AdminBondProductInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CB02CC0154
     */
    public WEB3AdminBondProductInfo toProductInfo(WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toProductInfo(WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        WEB3AdminBondFxRateInfo l_adminBondFxRateInfo = null;
        
        //1.1 is外貨建( )＝＝trueの場合
        if (l_bondProduct.isForeignCurrency())
        {
            
            //1.2.1 get通貨()
            WEB3GentradeCurrency l_currency = l_bondProduct.getCurrency();
            
            if (l_currency != null)
            {
                //1.2.2 為替レートオブジェクトを生成
                l_adminBondFxRateInfo = new WEB3AdminBondFxRateInfo();
                
                //1.2.3 プロパティセット
                //買付基準為替=get通貨.get買付基準為替レート() 
                double l_dblCurrentBuyBaseRate = l_currency.getBuyBaseRate();
                l_adminBondFxRateInfo.buyBaseFx = 
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentBuyBaseRate);
                
                //売却基準為替=get通貨.get売付基準為替レート() 
                double l_dblCurrentSellBaseRate = l_currency.getSellBaseRate();
                l_adminBondFxRateInfo.sellBaseFx = 
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentSellBaseRate);
                
                //買付約定為替=get通貨.get買付約定為替レート() 
                double l_dblCurrentBuyExecRate = l_currency.getBuyExecRate();
                l_adminBondFxRateInfo.buyExecFx = 
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentBuyExecRate);
                
                //売却約定為替=get通貨.get売付約定為替レート()
                double l_dblCurrentSellExecRate = l_currency.getSellExecRate();       
                l_adminBondFxRateInfo.sellExecFx =
                    WEB3StringTypeUtility.formatNumber(l_dblCurrentSellExecRate);
            }       
        }
        
        WEB3BondDataManagerService l_bondDataManagerService = 
            (WEB3BondDataManagerService) Services.getService(WEB3BondDataManagerService.class);
        
        //1.3 getカストディアン
        WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = null;
        if (l_bondProduct.getCustodianCode() != null)
        {
            CustodianRow l_custodianRow = l_bondDataManagerService.getCustodian(
                l_bondProduct.getInstitution(), 
                l_bondProduct.getCustodianCode());
            //1.4 toカストディアン
            l_adminBondCustodianUnit = this.toCustodian(l_custodianRow);
        }

        
        BondProductRow l_bondProductRow = (BondProductRow)l_bondProduct.getDataSourceObject();
        
        //1.5 銘柄情報オブジェクトを生成
        WEB3AdminBondProductInfo l_adminBondProductInfo = new WEB3AdminBondProductInfo();
        
        // 銘柄コード(WEB3)= 債券銘柄.get銘柄コード(WEB3) 
        l_adminBondProductInfo.productCode = l_bondProduct.getProductCode();
        
        //銘柄名=債券銘柄.get銘柄名() 
        l_adminBondProductInfo.productName = l_bondProduct.getProductName();
        
        //買付単価=債券銘柄.get買付単価() 
        if (!l_bondProductRow.getBuyPriceIsNull())
        {
            l_adminBondProductInfo.buyPrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyPrice());
        }
        
        //売却単価=債券銘柄.get売却単価() 
        if (!l_bondProductRow.getSellPriceIsNull())
        {
            l_adminBondProductInfo.sellPrice = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getSellPrice());
        }
        
        //利率=債券銘柄.getCoupon() 
        l_adminBondProductInfo.coupon = 
            WEB3StringTypeUtility.formatNumber(l_bondProduct.getCoupon());
        
        //通貨コード=債券銘柄.get通貨コード()
        l_adminBondProductInfo.currencyCode = l_bondProduct.getCurrencyCode();
        
        //発行日=債券銘柄.getIssueDate() 
        l_adminBondProductInfo.issueDate = l_bondProduct.getIssueDate();
        
        //年間利払回数=債券銘柄.getYearlyInterestPayments()
        l_adminBondProductInfo.yearlyInterestPayments = l_bondProduct.getYearlyInterestPayments() + "";
        
        //利払日1=債券銘柄.get利払日1() 
        l_adminBondProductInfo.interestPaymentDay1 = l_bondProduct.getInterestPaymentDay1();
        
        //利払日2=債券銘柄.get利払日2() 
        l_adminBondProductInfo.interestPaymentDay2 = l_bondProduct.getInterestPaymentDay2();
        
        //償還日=債券銘柄.getMaturityDate() 
        l_adminBondProductInfo.maturityDate = l_bondProduct.getMaturityDate();
        
        //カストディアン = toカストディアン
        l_adminBondProductInfo.custodianInfo = l_adminBondCustodianUnit;
        
        //為替レート = 生成した為替レートオブジェクト 
        l_adminBondProductInfo.fxRateInfo = l_adminBondFxRateInfo; 
        
        //仕入時の為替レート=債券銘柄.get仕入時の為替レート 
        if (!l_bondProductRow.getBuyingFxRateIsNull())
        {
            l_adminBondProductInfo.fxRateAtStock = 
                WEB3StringTypeUtility.formatNumber(l_bondProduct.getBuyingFxRate());
        }

        log.exiting(STR_METHOD_NAME);
        return l_adminBondProductInfo;
    }
    
    /**
     * (get補助口座)<BR>
     * get補助口座<BR>
     * <BR>
     * １）　@拡張アカウントマネージャ.get顧客（証券会社コード、部店コード、顧客コード）を呼ぶ<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@証券会社コード：引数.証券会社コード<BR>
     * 　@　@　@部店コード　@　@　@：引数.部店コード<BR>
     * 　@　@　@顧客コード　@　@　@：引数.顧客コード<BR>
     * 　@　@※顧客が存在しない場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01035<BR>
     * <BR>
     * ２）　@当該客が信用客かどうか判定する。 <BR>
     * <BR>
     * 　@　@　@顧客.is信用口座開設("0：指定なし")==trueの場合は信用客である。 <BR>
     * 　@　@　@以外、非信用客である。 <BR>
     * <BR>
     * ３）　@拡張アカウントマネージャ.getSubAccount( )にて、<BR>
     * 該当顧客の補助口座オブジェクトを取得する。 <BR>
     * 　@[getSubAccount( )の引数] <BR>
     * 　@信用客の場合：　@SubAccountTypeEnum.信用取引口座（EQUITY_MARGIN_SUB_ACCOUNT） <BR>
     * 　@非信用客の場合：　@SubAccountTypeEnum.株式取引口座（EQUITY_SUB_ACCOUNT）<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード
     * @@param l_strAccountCode - (顧客コード)<BR>
     * 顧客コード
     * @@return SubAccount
     * @@throws WEB3BaseException
     * @@roseuid 44CB073C0124
     */
    public SubAccount getSubAccount(String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getSubAccount(String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        SubAccount  l_subAccount = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        
        //１）　@拡張アカウントマネージャ.get顧客（証券会社コード、部店コード、顧客コード）を呼ぶ 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();      

        try
        {
            l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);
        }
        catch (WEB3BaseException l_ex)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当する顧客が存在しません。");
        }
        
        //２）　@当該客が信用客かどうか判定する。  
        boolean l_blnFlag = l_mainAccount.isMarginAccountEstablished(
            WEB3GentradeRepaymentDivDef.DEFAULT);  
        log.debug("l_blnFlag=======" + l_blnFlag);
        try
        {
            if(l_blnFlag)
            {
                //　@信用客の場合：　@
                //SubAccountTypeEnum.信用取引口座（EQUITY_MARGIN_SUB_ACCOUNT）  
                l_subAccount = l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            }
            else
            {
                //　@非信用客の場合：　@
                //SubAccountTypeEnum.株式取引口座（EQUITY_SUB_ACCOUNT）  
                l_subAccount = l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
             }
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
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
    
    /**
     * (reset受渡代金)<BR>
     * reset受渡代金<BR>
     * <BR>
     * 債券受渡代金計算結果に約定情報の対応項目をセットする。<BR>
     * <BR>
     * １）約定情報.売買代金（円貨）！＝nullの場合、<BR>
     * 　@債券受渡代金計算結果.売買代金（円貨）＝約定情報.売買代金（円貨）
     * <BR>
     * ２）約定情報.経過利子(円貨) ！＝nullの場合、<BR>
     * 　@債券受渡代金計算結果.経過利子(円貨)＝約定情報.経過利子(円貨)
     * <BR>
     * ３）債券受渡代金計算結果.calc受渡代金（円貨）をコールし、受渡代金（円貨）を取得する。 <BR>
     * <BR>
     * ４）約定情報.受渡代金(円貨) != null の場合 <BR>
     *    ４－１）約定情報.受渡代金(円貨)  != ３）で取得した受渡代金（円貨）の場合 <BR>
     * 　@          債券受渡代金計算結果.add警告区分(警告区分)をコールする。<BR>
     *             [引数]警告区分：受渡代金が一致しない<BR>
     *    ４－２）債券受渡代金計算結果.受渡代金(円貨)＝約定情報.受渡代金(円貨)<BR>
     * <BR>
     * ５）債券銘柄.is外貨建＝＝trueの場合<BR>
     *    ５－１）約定情報.売買代金（外貨）！＝nullの場合、<BR>
     *             債券受渡代金計算結果.売買代金（外貨）＝約定情報.売買代金（外貨）<BR>
     * <BR>
     *    ５－２）約定情報.経過利子(外貨) ！＝nullの場合、<BR>
     *             債券受渡代金計算結果.経過利子(外貨)＝約定情報.経過利子(外貨)<BR>
     *<BR>
     *    ５－３）債券受渡代金計算結果.calc受渡代金（外貨）をコールし、受渡代金（外貨）を取得する。<BR>
     * <BR>
     *    ５－４）約定情報.受渡代金(外貨) != null の場合<BR>
     *         ５－４－１）約定情報.受渡代金(外貨)  !=  ５－３）で取得した受渡代金（外貨）の場合<BR>
     *                       債券受渡代金計算結果.add警告区分(警告区分)をコールする。<BR>
     *                       [引数]警告区分：受渡代金が一致しない <BR>
     *         ５－４－２）債券受渡代金計算結果.受渡代金(外貨)＝約定情報.受渡代金(外貨)<BR>
     *<BR>        
     * ６）約定情報.経過日数！＝nullの場合、<BR>
     *  　@　@債券受渡代金計算結果.経過日数＝約定情報.経過日数<BR>
     *<BR>
     * ７）約定情報.基準日数！＝nullの場合、<BR>
     *  　@債券受渡代金計算結果.基準日数＝約定情報.基準日数<BR>
     *<BR>
     * 8）債券受渡代金計算結果を返す<BR>    
     * @@param l_calcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果
     * @@param l_orderExecInfo - (約定情報)<BR>
     * 約定情報
     * @@param l_product - (債券銘柄)<BR>
     * 債券銘柄
     * 
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@throws WEB3BaseException
     * @@roseuid 44CB197501AE
     */
    public WEB3BondEstimatedPriceCalcResult resetEstimatedPrice(
            WEB3BondEstimatedPriceCalcResult l_calcResult, 
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondProduct l_product) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "resetEstimatedPrice(WEB3BondEstimatedPriceCalcResult, "+
            "WEB3AdminBondOrderExecInfo, "+
            "WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderExecInfo == null || l_calcResult == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //１）約定情報.売買代金（円貨）！＝nullの場合、 
        //    債券受渡代金計算結果.売買代金（円貨）＝約定情報.売買代金（円貨） 
        if (l_orderExecInfo.yenTradePrice != null)
        {
            BigDecimal l_bdYenTradePrice = 
                new BigDecimal(l_orderExecInfo.yenTradePrice);
            l_calcResult.setTradingPrice(l_bdYenTradePrice);
        }
        
        //２）約定情報.経過利子(円貨) ！＝nullの場合、 
        //  　@債券受渡代金計算結果.経過利子(円貨)＝約定情報.経過利子(円貨) 
        if (l_orderExecInfo.yenAccruedInterest != null)
        {
            BigDecimal l_bdYenAccruedInterest = 
                new BigDecimal(l_orderExecInfo.yenAccruedInterest);
            l_calcResult.setAccruedInterest(l_bdYenAccruedInterest);
        }
        
        //３）債券受渡代金計算結果.calc受渡代金（円貨）をコールし、受渡代金（円貨）を取得する。
        BigDecimal l_bdEstimatedPrice = l_calcResult.calcEstimatedPrice();
        
        
        //４）約定情報.受渡代金(円貨) != null の場合
        if (l_orderExecInfo.yenDeliveryPrice != null)
        {
            //４－１）約定情報.受渡代金(円貨)  != ３）で取得した受渡代金（円貨）の場合 
            //債券受渡代金計算結果.add警告区分(警告区分)をコールする。 
            BigDecimal l_bdYenDeliveryPrice = new BigDecimal(l_orderExecInfo.yenDeliveryPrice);
            
            if (l_bdYenDeliveryPrice.compareTo(l_bdEstimatedPrice) != 0)
            {
                //　@[引数]警告区分：受渡代金が一致しない 
                l_calcResult.addWarningDiv(WEB3BondWarningDivDef.ESTIMATED_PRICE_DIFFERENCE);  
            }     
            //４－２）債券受渡代金計算結果.受渡代金(円貨)＝約定情報.受渡代金(円貨)
            l_calcResult.setEstimatedPrice(l_bdYenDeliveryPrice);   
        }
        
        //５）債券銘柄.is外貨建＝＝trueの場合 
        if (l_product.isForeignCurrency())
        {
            //５－１）約定情報.売買代金（外貨）！＝nullの場合、
            if (l_orderExecInfo.foreignTradePrice != null)
            {
                //債券受渡代金計算結果.売買代金（外貨）＝約定情報.売買代金（外貨） 
                l_calcResult.setForeignTradePrice(new BigDecimal(l_orderExecInfo.foreignTradePrice));
            }
            
            //５－２）約定情報.経過利子(外貨) ！＝nullの場合、 
            if (l_orderExecInfo.foreignAccruedInterest != null)
            {
                //債券受渡代金計算結果.売買代金（外貨）＝約定情報.売買代金（外貨） 
                l_calcResult.setForeignAccruedInterest(new BigDecimal(l_orderExecInfo.foreignAccruedInterest));
            }
            
            //５－３）債券受渡代金計算結果.calc受渡代金（外貨）をコールし、受渡代金（外貨）を取得する。 
            BigDecimal l_bdForeignEstimatedPrice = l_calcResult.calcForeignEstimatedPrice();
            
            //５－４）約定情報.受渡代金(外貨) != null の場合 
            if (l_orderExecInfo.foreignDeliveryPrice != null)
            {
                BigDecimal l_bdForeignDeliveryPrice = 
                    new BigDecimal(l_orderExecInfo.foreignDeliveryPrice);
                //５－４－１）約定情報.受渡代金(外貨)  !=  ５－３）で取得した受渡代金（外貨）の場合 
                //債券受渡代金計算結果.add警告区分(警告区分)をコールする。 
                if (l_bdForeignDeliveryPrice.compareTo(l_bdForeignEstimatedPrice) != 0)
                {
                    //　@[引数]警告区分：受渡代金が一致しない
                    l_calcResult.addWarningDiv(WEB3BondWarningDivDef.ESTIMATED_PRICE_DIFFERENCE); 
                }    
                //５－４－２）債券受渡代金計算結果.受渡代金(外貨)＝約定情報.受渡代金(外貨)   
                l_calcResult.setForeignEstimatedPrice(l_bdForeignDeliveryPrice);
            }
        }
        
        //６）約定情報.経過日数！＝nullの場合、 
        //  債券受渡代金計算結果.経過日数＝約定情報.経過日数 
        if (l_orderExecInfo.elapsedDays != null)
        {
            l_calcResult.setElapsedDays(Integer.valueOf(l_orderExecInfo.elapsedDays));
        }
        
        //７）約定情報.基準日数！＝nullの場合、 
        //債券受渡代金計算結果.基準日数＝約定情報.基準日数
        if (l_orderExecInfo.calcBaseDays != null)
        {
            l_calcResult.setCalcBaseDays(Integer.valueOf(l_orderExecInfo.calcBaseDays));
        }
        
        //8）債券受渡代金計算結果を返す
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }
    
    /**
     * (reset約定日情報)<BR>
     * reset約定日情報<BR>
     * <BR>
     * １）引数.債券約定日情報.約定日＝引数.約定情報.約定日<BR>
     * <BR>
     * ２）引数.約定情報.現地約定日　@!= nullの場合<BR>
     * 　@　@引数.債券約定日情報.現地約定日＝引数.約定情報.現地約定日<BR>
     * <BR>
     * ３）引数債券注文種別判定.is応募 == false の場合<BR>
     * 　@　@３－１）引数.約定情報.受渡日　@== null の場合、<BR>
     * 　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01079<BR>
     * <BR>
     * 　@　@３－２）それ以外の場合、<BR>
     * 　@　@　@　@　@　@　@引数.債券約定日情報.受渡日＝引数.約定情報.受渡日<BR>
     * <BR>
     * ４）引数.約定情報.現地受渡日　@!= nullの場合、<BR>
     * 　@　@引数.債券約定日情報.現地受渡日＝引数.約定情報.現地受渡日<BR>
     * <BR>
     * ５）引数.約定情報.入金日　@!= nullの場合、<BR>
     * 　@　@引数.債券約定日情報.入金日＝引数.約定情報.入金日<BR>
     * <BR>
     * ６）引数.約定情報.入金日　@== nullの場合、<BR>
     * 　@６－１）債券部店別条件を生成する。
     * 　@　@　@　@　@[引数]
     * 　@　@　@　@　@　@部店ID：引数.部店.部店ID
     * 　@６－２）（債券部店別条件.get入金日設定区分＝＝'約定日基準'<BR>
     * 　@　@　@　@　@または　@債券部店別条件.get入金日設定区分＝＝'登録日基準'）<BR>
     * 　@　@　@　@　@かつ　@引数.債券注文種別判定.is応募＝＝true　@の場合、<BR>
     * 　@　@　@　@　@　@引数.債券約定日情報.入金日＝引数.債券銘柄.get入金日<BR>
     * 　@　@　@　@　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@　@　@約定日　@　@　@　@　@　@　@　@：　@引数.債券約定日情報.約定日<BR>
     * 　@　@　@　@　@　@　@　@　@債券注文種別判定：　@引数.債券注文種別判定<BR>
     * 　@　@　@　@　@　@　@　@　@決済区分　@　@　@　@　@　@：　@引数.決済区分<BR>
     * 　@　@　@　@　@　@　@　@　@部店　@　@　@　@　@　@　@　@　@：　@引数.部店<BR>
     * 　@６－３）上記以外の場合、
     * 　@　@　@　@　@　@引数.債券約定日情報.入金日＝引数.債券約定日情報.受渡日
     * ７）債券約定日情報を返す。
     * @@param l_orderExecInfo - (約定情報)<BR>
     * 約定情報
     * @@param l_executeDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_strSettleDiv - (決済区分)<BR>
     * 決済区分
     * @@param l_branch - (部店)<BR>
     * 部店
     * @@return webbroker3.bd.WEB3BondExecuteDateInfo
     * @@throws WEB3BaseException
     * @@roseuid 44CB13390090
     */
    public WEB3BondExecuteDateInfo resetExecuteDateInfo(
        WEB3AdminBondOrderExecInfo l_orderExecInfo, 
        WEB3BondExecuteDateInfo l_executeDateInfo,
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        WEB3BondProduct l_bondProduct, String l_strSettleDiv, Branch l_branch) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "resetExecuteDateInfo(WEB3AdminBondOrderExecInfo, "+
            "WEB3BondExecuteDateInfo, " +
            "WEB3BondOrderTypeJudge, " +
            "WEB3BondProduct, " +
            "String, " +
            "Branch ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderExecInfo == null || l_executeDateInfo == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //１）引数.債券約定日情報.約定日＝引数.約定情報.約定日 
        l_executeDateInfo.setExecuteDate(l_orderExecInfo.domesticExecutionDate);
        
        //２）引数.債券約定日情報.現地約定日＝引数.約定情報.現地約定日 
        if (l_orderExecInfo.foreignExecutionDate != null)
        {
            l_executeDateInfo.setForeignExecuteDate(l_orderExecInfo.foreignExecutionDate);
        }

        //３）引数債券注文種別判定.is応募 == false の場合
        //３－１）引数.約定情報.受渡日　@== null の場合、例外をスローする。
        //３－２）それ以外の場合、引数.債券約定日情報.受渡日＝引数.約定情報.受渡日
        if (!l_bondOrderTypeJudge.isRecruitOrder())
        {
            if (l_orderExecInfo.domesticDeliveryDate == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "受渡日が未指定です。");
            }
            else
            {
                l_executeDateInfo.setDeliveryDate(l_orderExecInfo.domesticDeliveryDate);
            }
        }

        //４）引数.債券約定日情報.現地受渡日＝引数.約定情報.現地受渡日 
        if (l_orderExecInfo.foreignDeliveryDate != null)
        {
            l_executeDateInfo.setForeignDeliveryDate(l_orderExecInfo.foreignDeliveryDate);
        }
        
        //５）引数.約定情報.入金日　@!= nullの場合、 
        //引数.債券約定日情報.入金日＝引数.約定情報.入金日
        Date l_datPaymentDate = null;
        if (l_orderExecInfo.paymentDate != null)
        {
            l_datPaymentDate = l_orderExecInfo.paymentDate;
        }
        //６）引数.約定情報.入金日　@== nullの場合、 
        else
        {
            //６－１）債券部店別条件を生成する。
            //[引数]
            //部店ID：引数.部店.部店ID
            WEB3BondBranchCondition l_branchCondition =
                new WEB3BondBranchCondition(l_branch.getBranchId());
            
            //６－２）（債券部店別条件.get入金日設定区分＝＝'約定日基準'
            //または　@債券部店別条件.get入金日設定区分＝＝'登録日基準'）
            //かつ　@引数.債券注文種別判定.is応募＝＝true　@の場合、
            //引数.債券約定日情報.入金日＝引数.債券銘柄.get入金日
            //[引数]
            //約定日：引数.債券約定日情報.約定日
            //債券注文種別判定：引数.債券注文種別判定
            //決済区分：引数.決済区分
            //部店：引数.部店
            if ((WEB3PaymentDateDetDivDef.EXECUTE_DATE_BASE.equals(l_branchCondition.getPaymentDateSetDiv())
                || WEB3PaymentDateDetDivDef.REGIST_DATE_BASE.equals(l_branchCondition.getPaymentDateSetDiv()))
                && l_bondOrderTypeJudge.isRecruitOrder())
            {
                l_datPaymentDate = l_bondProduct.getPaymentDate(l_executeDateInfo.getExecuteDate(),
                                                                l_bondOrderTypeJudge,
                                                                l_strSettleDiv,
                                                                l_branch);
            }
            //６－３）上記以外の場合、
            //引数.債券約定日情報.入金日＝引数.債券約定日情報.受渡日
            else
            {
                l_datPaymentDate = l_executeDateInfo.getDeliveryDate();
            }
        }
        l_executeDateInfo.setPaymentDate(l_datPaymentDate);
     
        
        //７）債券約定日情報を返す。
        log.exiting(STR_METHOD_NAME);
        return l_executeDateInfo;
    }
    
    /**
     * (get注文ロック解除ボタン区分)<BR>
     * get注文ロック解除ボタン区分<BR>
     * <BR>
     * 注文ロック解除ボタン区分を決定し、該当する値を返す。<BR>
     * <BR>
     * １）戻り値の変数に「非表示」をセットする。<BR>
     * <BR>
     * ２）債券部店別条件を生成する。<BR>
     * 　@　@[引数]<BR>
     * 　@　@　@部店ID：注文単位.get部店ID<BR>
     * <BR>
     * ３）債券部店別条件.get注文ロック使用区分＝＝'注文ロック区分を使用する'の場合<BR>
     * 　@３－１）注文単位.注文約定区分＝＝未約定<BR>
     * 　@　@　@　@　@　@　@　@　@かつ<BR>
     * 　@　@　@　@　@　@　@　@注文単位.注文ロック区分＝＝ロック解除中の場合、<BR>
     * 　@　@　@　@　@　@　@　@戻り値の変数に「ロックボタン」をセットする。<BR>
     * 　@３－２）注文単位.注文約定区分＝＝未約定<BR>
     * 　@　@　@　@　@　@　@　@　@かつ<BR>
     * 　@　@　@　@　@　@　@　@注文単位.注文ロック区分＝＝ロック中の場合、<BR>
     * 　@　@　@　@　@　@　@　@戻り値の変数に「解除ボタン」をセットする。<BR>
     * <BR>
     * ４）戻り値を返す。
     * @@param l_orderUnit - (注文単位)<BR>
     * 拡張債券注文単位
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D31F2D020D
     */
    public String getOrderLockButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getOrderLockCancelButtonDiv(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
                
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //2) 債券部店別条件を生成する。
        BondBranchConditionRow l_bondBranchConditionRow = null;
        
        String l_strLockButton = WEB3BondLockReleaseButtonDivDef.DISPLAY_NO;

        try
        {
            l_bondBranchConditionRow = 
                BondBranchConditionDao.findRowByPk(l_orderUnit.getBranchId());
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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
        
        String l_strOrderLockUseDiv = "";
        if (l_bondBranchConditionRow != null)
        {
            l_strOrderLockUseDiv = l_bondBranchConditionRow.getOrderLockUseDiv();
        }
        
        //３）債券部店別条件.get注文ロック使用区分＝＝'注文ロック区分を使用する'の場合
        if (WEB3BondOrderLockUseDivDef.EXCEPT.equals(l_strOrderLockUseDiv))
        {
            // ３－１）注文単位.注文約定区分＝＝未約定 
            //注文単位.注文ロック区分＝＝ロック解除中の場合、 
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()) && 
                WEB3LockStatusDef.RELEASING.equals(l_orderUnit.getLockStatus()))
            {
                l_strLockButton = WEB3BondLockReleaseButtonDivDef.LOCK_BUTTON;
            }
            
            //  ３－２）注文単位.注文約定区分＝＝未約定 
            //注文単位.注文ロック区分＝＝ロック中の場合、 
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()) && 
                WEB3LockStatusDef.LOCKING.equals(l_orderUnit.getLockStatus()))
            {
                l_strLockButton = WEB3BondLockReleaseButtonDivDef.RELEAS_BUTTON;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strLockButton;
    }
    
    /**
     * (get約定変更ボタン区分)<BR>
     * get約定変更ボタン区分<BR>
     * <BR>
     * 約定変更ボタン区分を決定し、該当する値を返す。<BR>
     * <BR>
     * １）注文単位.債券タイプ == 外国債券 の場合、以下の処理を行なう。<BR>
     * 　@　@１－１）注文単位.注文約定区分＝＝未約定の場合、「約定ボタン」を返す。<BR>
     * <BR>
     * 　@　@１－２）注文単位.注文約定区分＝＝約定済の場合、「変更ボタン」を返す。<BR>
     * <BR>
     * 　@　@１－３）上記以外の場合、「非表示」を返す。<BR>
     * <BR>
     * ２）注文単位.債券タイプ ≠ 外国債券 の場合、「非表示」を返す。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 拡張債券注文単位
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D3248F0352
     */
    public String getExecuteChangButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getExecuteChangButtonDiv(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        String l_strExecuteChangButtonDiv = null;

        //１）注文単位.債券タイプ == 外国債券 の場合、以下の処理を行なう。
        if (BondTypeEnum.FOREIGN_BOND.equals(l_orderUnit.getBondType()))
        {
            //１）注文単位.注文約定区分＝＝未約定の場合、「約定ボタン」を返す。 
            if (WEB3BondOrderExecStatusDef.UNEXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {
                l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.EXECUTE_BUTTON;
            }
            
            //２）注文単位.注文約定区分＝＝約定済の場合、「変更ボタン」を返す。 
            else if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_orderUnit.getOrderExecStatus()))
            {
                l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.CHANGE_BUTTON;
            }
            else
            {
                //３）上記以外の場合、「非表示」を返す。
                l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.DISPLAY_NO;
            }
        }
        else
        {
            //２）注文単位.債券タイプ ≠ 外国債券 の場合、「非表示」を返す。
            l_strExecuteChangButtonDiv = WEB3BondExecuteChangeButtonDivDef.DISPLAY_NO;
        }
        log.exiting(STR_METHOD_NAME);
        return l_strExecuteChangButtonDiv;
    }
    
    /**
     * (get取消ボタン区分)<BR>
     * get取消ボタン区分<BR>
     * <BR>
     * 取消ボタン区分を決定し、該当する値を返す。<BR>
     * <BR>
     * １）注文単位.注文約定区分！＝取消済の場合、「取消ボタン」を返す。<BR>
     * <BR>
     * ２）上記以外の場合、「非表示」を返す。
     * @@param l_orderUnit - (注文単位)<BR>
     * 拡張債券注文単位
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 44D325C8030E
     */
    public String getCancelButtonDiv(WEB3BondOrderUnit l_orderUnit) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getCancelButtonDiv(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        String l_strOrderExecStatus = "";
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
       
        l_strOrderExecStatus = l_orderUnit.getOrderExecStatus();
   
        String l_strLockButton;
        //１）注文単位.注文約定区分！＝取消済の場合、「取消ボタン」を返す。
        if (!WEB3BondOrderExecStatusDef.CANCELED.equals(l_strOrderExecStatus))
        {
            l_strLockButton = WEB3BondCancelDivDef.CANCEL_BUTTON;
        }
        
        //２）上記以外の場合、「非表示」を返す。
        else
        {
            l_strLockButton = WEB3BondCancelDivDef.DISPLAY_NO;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strLockButton;
    }
    
    /**
     * (toカストディアン一覧)<BR>
     * toカストディアン一覧<BR>
     * <BR>
     * １）戻り値Listを生成する。<BR>
     * <BR>
     * ２）引数.カストディアンリストの要素数分Loopする。<BR>
     * 　@２－１）カストディアンリストの要素をカストディアンRowにキャストする。<BR>
     * 　@２－２）this.toカストディアンを呼ぶ<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@カストディアンRow：カストディアンRow<BR>
     * 　@２－３）取得したカストディアンを戻り値Listに追加する。<BR>
     * <BR>
     * ３）戻り値Listを返す
     * @@param l_lisCustodian - (カストディアンリスト)<BR>
     * カストディアンリスト<BR>
     * <BR>
     *  CustodianRowのList
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 44D93BA302DE
     */
    public List toCustodianList(List l_lisCustodian) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toCustodianList(List)";
        log.entering(STR_METHOD_NAME);
        
        int l_intSize = 0;
        if (l_lisCustodian != null)
        {
            l_intSize = l_lisCustodian.size();
        }
        //１）戻り値Listを生成する。 
        List l_lisCustodianList = new ArrayList();
        
        //２）引数.カストディアンリストの要素数分Loopする。 
        for (int i = 0; i < l_intSize; i++)
        {
            //２－１）カストディアンリストの要素をカストディアンRowにキャストする。
            CustodianRow l_custodianRow = (CustodianRow) l_lisCustodian.get(i);
            
            //２－２）this.toカストディアンを呼ぶ 
            WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = this.toCustodian(l_custodianRow);
            
            //２－３）取得したカストディアンを戻り値Listに追加する。 
            l_lisCustodianList.add(l_adminBondCustodianUnit);           
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisCustodianList;
    }
    
    /**
     * (toカストディアン)<BR>
     * toカストディアン<BR>
     * <BR>
     * １）カストディアンを生成する。<BR>
     * <BR>
     * ２）カストディアン.カストディアンコード＝引数.カストディアンRow.getカストディアンコード<BR>
     * 　@　@カストディアン.カストディアン名　@　@＝引数.カストディアンRow.getカストディアン名<BR>
     * <BR>
     * ３）カストディアンを返す
     * @@param l_row - (カストディアンRow)<BR>
     * カストディアンRow
     * 
     * @@return WEB3AdminBondCustodianUnit
     * @@throws WEB3BaseException
     * @@roseuid 44D948D801E4
     */
    public WEB3AdminBondCustodianUnit toCustodian(CustodianRow l_row) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "toCustodian(CustodianRow)";
        log.entering(STR_METHOD_NAME);

        //１）カストディアンを生成する。 
        WEB3AdminBondCustodianUnit l_adminBondCustodianUnit = 
            new WEB3AdminBondCustodianUnit();
        
        //カストディアン.カストディアンコード＝引数.カストディアンRow.getカストディアンコード 
        if (l_row != null)
        {
            l_adminBondCustodianUnit.custodianCode = l_row.getCustodianCode();
            
            //カストディアン.カストディアン名　@　@＝引数.カストディアンRow.getカストディアン名 
            l_adminBondCustodianUnit.custodianName = l_row.getCustodianName();
        }
        
        //３）カストディアンを返す
        log.exiting(STR_METHOD_NAME);
        return l_adminBondCustodianUnit;
    }
    
    
    /**
     * (to顧客情報)<BR>
     * 引数より顧客情報を作成する <BR>
     *  <BR>
     * １）顧客情報オブジェクトを生成  <BR>
     *  <BR>
     * ２）顧客情報オブジェクトに下記のプロパティをセット  <BR>
     *     部店コード：引数.顧客.getBranch().get部店コード()  <BR>
     *     顧客コード：引数.顧客.get表示顧客コード() <BR>
     *     顧客名：引数.顧客.get顧客表示名()  <BR>
     *  <BR>    
     * ３）顧客情報オブジェクトを返却 <BR> 
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客
     * @@return WEB3AdminBondAccountInfo
     * @@throws WEB3BaseException
     * @@roseuid 44D94A2D01B5
     */
    public WEB3AdminBondAccountInfo toAccountInfo(MainAccount l_mainAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "toAccountInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }   
        
        //１）顧客情報オブジェクトを生成
        WEB3AdminBondAccountInfo l_adminBondAccountInfo = 
            new WEB3AdminBondAccountInfo(); 

        //２）顧客情報オブジェクトに下記のプロパティをセット
        //   部店コード：引数.顧客.getBranch().get部店コード()
        l_adminBondAccountInfo.branchCode = 
            l_mainAccount.getBranch().getBranchCode();
        
        //顧客コード：引数.顧客.get表示顧客コード()
        MainAccountRow l_row = (MainAccountRow)l_mainAccount.getDataSourceObject();
        WEB3GentradeMainAccount l_gentradeMainAccount = new WEB3GentradeMainAccount(l_row);
        
        l_adminBondAccountInfo.accountCode = l_gentradeMainAccount.getDisplayAccountCode();      

        //顧客名：引数.顧客.get顧客表示名()
        l_adminBondAccountInfo.accountName = l_gentradeMainAccount.getDisplayAccountName();
        
        // ３）顧客情報オブジェクトを返却
        log.exiting(STR_METHOD_NAME);
        return l_adminBondAccountInfo;
    }
}@
