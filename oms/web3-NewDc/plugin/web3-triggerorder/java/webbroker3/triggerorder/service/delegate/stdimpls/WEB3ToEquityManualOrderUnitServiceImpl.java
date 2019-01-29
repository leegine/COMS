head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToEquityManualOrderUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式手動発注UnitServiceImpl(WEB3ToEquityManualOrderUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/3/6 韋念瓊(中訊) 新規作成
                   2006/08/30 柴雙紅(中訊) 仕様変更モデル165
                   2007/01/11 趙林鵬 (中訊) モデル No.206
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityDataAdapter;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.equity.WEB3EquityProductQuote;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginRepaymentUnit;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.triggerorder.define.WEB3ToManualOrderErrorCodeDef;
import webbroker3.triggerorder.message.WEB3EquityManualUnit;
import webbroker3.triggerorder.message.WEB3ManualCommissionInfoUnit;
import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderUnitService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株式手動発注UnitServiceImpl)<BR>
 * 抽象クラス（abstract）<BR>
 * <BR>
 * @@author 韋念瓊 <BR>
 * @@version 1.0<BR>
 */
public abstract class WEB3ToEquityManualOrderUnitServiceImpl implements WEB3ToEquityManualOrderUnitService 
{
    
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
    	WEB3ToEquityManualOrderUnitServiceImpl.class);

    /**
     * @@roseuid 4349DEE70222
     */
    public WEB3ToEquityManualOrderUnitServiceImpl() 
    {
     
    }
    
    /**
     * 注文1件ごとの手動発注確認処理を行う。 <BR> 
     * <BR>
     * シーケンス図 <BR>
     * 『(株式手動発注Unitサービス)validate』参照。<BR>
     * <BR>
     * @@param l_strOrderId - (注文ID)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    public WEB3EquityManualUnit validate(String l_strOrderId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate(String)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityManualUnit l_web3EquityManualUnit = null; 
            
        //1.1 get注文データ(String)
        //引数は以下の通りに設定する。  
        //注文ID：　@パラメータ.注文ID
        EqTypeOrderUnit l_eqTypeOrderUnit = 
            this.getOrderData(l_strOrderId);
        
        //1.2 （分岐フロー： get注文データ()の戻り値 == nullの場合）
        if (l_eqTypeOrderUnit == null)
        {
            //1.2.1 getエラーUnitレスポンス(String)
            //引数は以下の通りに設定する。  

            //手動発注エラーコード：　@"該当なし" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.NOT_AVAILABLE);
            
            //1.2.2
            return l_web3EquityManualUnit;
        }
        
        //1.3 set取引カレンダコンテキスト(EqTypeOrderUnit)

        //引数は以下の通りに設定する。  
        //注文データ：　@this.get注文データ()の戻り値 
        this.setTradingClendarContext(l_eqTypeOrderUnit);
        
        //1.4 validate( )
        try
        {
            this.validate();
        
            //1.5 getUnitレスポンス(EqTypeOrderUnit)
    
            //引数は以下の通りに設定する。  
            //注文データ：　@this.get注文データ()の戻り値 
            l_web3EquityManualUnit = 
                this.getUnitResponse(l_eqTypeOrderUnit);
        }
        catch(WEB3BaseException l_ex)
        {
            //1.6 (*)処理中に例外が発生した場合
            //getエラーUnitレスポンス(String)
            //引数は以下の通りに設定する。  

            //手動発注エラーコード：　@"その他エラー" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.OTHER);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_web3EquityManualUnit;
    }
    
    /**
     * 注文1件ごとの手動発注完了処理を行う。  <BR>
     * <BR>
     * シーケンス図 <BR>
     * 『(株式手動発注Unitサービス)submit』参照。<BR>
     * <BR>
     * @@param l_strOrderId - (注文ID)<BR>
     * @@param l_submitterLoginId - (発注者ログインID)<BR>
     * @@param l_strNotifyType - (通知経路)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    public WEB3EquityManualUnit submit(
        String l_strOrderId, 
        Long l_submitterLoginId, 
        String l_strNotifyType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submit(String, Long, String)";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityManualUnit l_web3EquityManualUnit = null; 

        //1.1 get注文データ(String)
        
        //引数は以下の通りに設定する。
        //注文ID：　@パラメータ.注文ID 
        
        EqTypeOrderUnit l_eqTypeOrderUnit = 
            this.getOrderData(l_strOrderId);
        
        //1.2 （分岐フロー： get注文データ()の戻り値 == nullの場合）
        if (l_eqTypeOrderUnit == null)
        {
            //1.2.1 getエラーUnitレスポンス(String)
            //引数は以下の通りに設定する。

            //手動発注エラーコード：　@"該当なし" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.NOT_AVAILABLE);
            
            //1.2.2
            return l_web3EquityManualUnit;
        }
        
        //1.3 set取引カレンダコンテキスト(EqTypeOrderUnit)

        //引数は以下の通りに設定する。  
        //注文データ：　@this.get注文データ()の戻り値 
        this.setTradingClendarContext(l_eqTypeOrderUnit);
        
        try
        {
            //1.4 validate()
            this.validate();
   
            //1.5 getUnitレスポンス(EqTypeOrderUnit)
    
            //引数は以下の通りに設定する。  
            //注文データ：　@this.get注文データ()の戻り値 
            l_web3EquityManualUnit = 
                this.getUnitResponse(l_eqTypeOrderUnit);
            
            //ルールエンジンからの通知テーブルへINSERTする。
    
            //以下条件に該当する場合のみ、実施する。
            //　@・株式手動発注Unit.手動発注エラーコードが"正常"
            if (WEB3ToManualOrderErrorCodeDef.NORMAL.equals(
                    l_web3EquityManualUnit.manualOrderErrorCode))
            {    
                //1.6 sendRLSRequest(EqTypeOrderUnit, Long, String)
                //引数は以下の通りに設定する。  
        
                //注文データ：　@this.get注文データ()の戻り値 
                //発注者ログインID：　@パラメータ.発注者ログインID 
                //通知経路：　@パラメータ.通知経路
                
                this.sendRLSRequest(
                    l_eqTypeOrderUnit,
                    l_submitterLoginId, 
                    l_strNotifyType);
            }
        }
        catch(WEB3BaseException l_ex)
        {
            ////1.7 (*)処理中に例外が発生した場合
            //getエラーUnitレスポンス(String)
            //引数は以下の通りに設定する。  

            //手動発注エラーコード：　@"その他エラー" 
            l_web3EquityManualUnit = 
                this.getErrorUnitResponse(WEB3ToManualOrderErrorCodeDef.OTHER);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_web3EquityManualUnit;
    }
    
    /**
     * 株式手動発注のチェックを行う。 <BR>
     * <BR>
     * １）　@注文受付可能チェックを行う。 <BR>
     * 　@　@　@取引時間管理.validate注文受付()をコールする。<BR>
     * <BR>
     * ２）　@市場開局チェックを行う。<BR>
     * 　@　@　@取引時間管理.is市場開局時間帯()をコールする。<BR>
     * <BR>
     * 　@　@２－１）　@is市場開局時間帯()の戻り値が"false"の場合<BR>
     * 　@　@　@　@　@　@　@「システムが受付可能時間外」の例外をthrowする。<BR>
     * <BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@注文受付可能チェックを行う。
        //取引時間管理.validate注文受付()をコールする。
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //２）　@市場開局チェックを行う。
        boolean l_blTradeOpenTime =
                      WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone();

        //２－１）　@is市場開局時間帯()の戻り値がfalseの場合
        if (!l_blTradeOpenTime)
        {
            log.debug(STR_METHOD_NAME + "：受付可能時間外");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00013,
                WEB3GentradeTradingTimeManagement.class.getName()
                    + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get注文データ)
     * <BR>
     * パラメータ．注文IDに該当する注文単位を取得する。 <BR>
     * <BR>
     * １）　@注文単位を取得する。 <BR>
     * 　@　@　@拡張株式注文マネージャ.getOrderUnits()をコールする。 <BR>
     * <BR>
     * 　@　@　@[getOrderUnits()に指定する引数] <BR>
     * 　@　@　@arg0：　@パラメータ.注文ID <BR>
     * <BR>
     * ２）　@１）で取得した注文単位の 要素数 == 0 の場合 <BR>
     * 　@　@　@　@　@nullを返す。 <BR>
     * <BR>
     * 　@　@　@以外の場合、 <BR>
     * 　@　@　@　@　@１）で取得した注文単位の0番目の要素を返す。<BR>
     * <BR>
     * @@param l_strOrderId - (注文ID) <BR>
     * @@return EqTypeOrderUnit <BR>
     * @@roseuid 432175DD01A0
     */
    protected EqTypeOrderUnit getOrderData(String l_strOrderId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderData(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@注文単位を取得する。 
        //　@拡張株式注文マネージャ.getOrderUnits()をコールする。
        //  [getOrderUnits()に指定する引数] 
        //　@ arg0：　@パラメータ.注文ID 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        
        OrderUnit[] l_orderUnits = 
            l_orderManager.getOrderUnits(Long.parseLong(l_strOrderId));
        
        //２）　@１）で取得した注文単位の 要素数 == 0 の場合 
        //　@　@nullを返す。 

        //　@以外の場合、 
        //　@　@　@１）で取得した注文単位の0番目の要素を返す。
        if (l_orderUnits.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            EqTypeOrderUnit l_eqTypeOrderUnit = (EqTypeOrderUnit)l_orderUnits[0];
            
            log.exiting(STR_METHOD_NAME);
            return l_eqTypeOrderUnit;
        }
    }
    
    /**
     * (set取引カレンダコンテキスト)
     * <BR>
     * 取引カレンダが利用するコンテキストを生成する。 <BR>
     * <BR>
     * １）　@部店オブジェクトを取得。 <BR>
     * 　@　@　@拡張アカウントマネージャ.getBranch()をコールする。 <BR>
     * <BR>
     * 　@　@　@[getBranch()に設定する引数] <BR>
     * 　@　@　@arg0：　@注文データ.getBranchId()の戻り値 <BR>
     * <BR>
     * ２）　@証券会社オブジェクトを取得。 <BR>
     * 　@　@　@getBranch()の戻り値.getInstitution()をコールする。 <BR>
     * <BR>
     * ３）　@市場オブジェクトを取得する。<BR>
     * 　@　@３－１）　@注文単位Rowを生成する。<BR>
     * 　@　@　@　@　@　@　@　@注文データ.getDataSourceObject()をコールする。<BR>
     * <BR>
     * 　@　@３－２）　@拡張金融オブジェクトマネージャ.getMarket()をコールする。<BR>
     * <BR>
     *　@　@　@　@　@　@　@　@　@[getMarket()に設定する引数]<BR>
     *　@　@　@　@　@　@　@　@　@注文単位Row.getMarketId()<BR>
     * <BR>
     * ４）　@取引カレンダコンテキストに内容をセットする。    <BR>
     * 　@－パラメータ.注文データの内容より取引時間コンテキストの <BR>
     * 　@　@　@プロパティをセットする。  <BR>
     * <BR>
     * 　@取引カレンダコンテキスト.証券会社コード = <BR>
     * 　@　@　@getInstitution()の戻り値.getInstitutionCode() <BR>
     * 　@取引カレンダコンテキスト.部店コード = <BR>
     * 　@　@　@getBranch()の戻り値.getBranchCode() <BR>
     * 　@取引カレンダコンテキスト.市場コード =  <BR>
     *       getMarket()の戻り値.getMarketCode()<BR> 
     * 　@取引カレンダコンテキスト.受付時間区分 = (*1) <BR>
     * 　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” <BR>
     * 　@取引カレンダコンテキスト.注文受付商品 = (*2) <BR>
     * 　@取引カレンダコンテキスト.注文受付トランザクション = (*3) <BR>
     * <BR>
     * 　@(*1)受付時間区分 <BR>
     * 　@　@　@・注文カテゴリ(注文データ.getOrderCateg())が”現引・現渡注文”の場合、<BR>
     * 　@　@　@　@　@　@”現引・現渡”をセットする。 <BR>
     * 　@　@　@・注文カテゴリが上記以外の場合、 <BR>
     * 　@　@　@　@　@　@”株式・信用”をセットする。 <BR>
     * <BR>
     * 　@(*2)注文受付商品 <BR>
     * 　@　@　@・注文カテゴリ（注文データ.getOrderCateg()）が”現物注文”の場合、<BR> 
     * 　@　@　@　@　@　@”株式”をセットする。 <BR>
     * 　@　@　@・注文カテゴリが上記以外の場合、 <BR>
     * 　@　@　@　@　@　@”信用”をセットする。 <BR>
     * <BR>
     * 　@(*3)注文受付トランザクション <BR>
     * 　@　@　@・注文種別 == （”現物買注文”または”新規買建注文”）の場合、 <BR>
     * 　@　@　@　@　@”買付（新規建買）”をセットする。 <BR>
     * 　@　@　@・注文種別 == （”現物売注文”または”新規売建注文”）の場合、 <BR>
     * 　@　@　@　@　@”売付（新規建売）”をセットする。 <BR>
     * 　@　@　@・注文カテゴリが”返済”の場合、"返済"をセットする。  <BR>
     * 　@　@　@・注文カテゴリが”現引現渡”の場合、"現引現渡"をセットする。  <BR>
     * <BR>
     * 　@－ThreadLocalSystemAttributesRegistry.setAttribute( )にて    <BR>
     * 　@　@　@取引時間コンテキストをセットする。    <BR>
     * 設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH    <BR>
     * <BR>
     * ５）　@受付日時、日付ロールをセットする。    <BR>
     * 　@－取引時間管理.setTimestamp()をコールする。<BR>
     * <BR>
     * @@param l_orderData - (注文データ)<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 432175DD01A0
     */
    protected void setTradingClendarContext(EqTypeOrderUnit l_orderData) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setTradingClendarContext(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        } 

        //１）　@部店オブジェクトを取得。 
        //　@拡張アカウントマネージャ.getBranch()をコールする。
        //　@[getBranch()に設定する引数] 
        //　@arg0：　@注文データ.getBranchId()の戻り値 
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        Branch l_branch = null;
        
        try
        {
            l_branch = l_accountManager.getBranch(l_orderData.getBranchId());           
            
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

        //２）　@証券会社オブジェクトを取得。 
        //　@　@getBranch()の戻り値.getInstitution()をコールする。 
        Institution l_institution = l_branch.getInstitution();
        
        //市場オブジェクトを取得する。
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
            (EqtypeOrderUnitRow)l_orderData.getDataSourceObject();
            
        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
             
        long l_lngMarketId = l_eqtypeOrderUnitRow.getMarketId();        
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

        //４）　@取引カレンダコンテキストに内容をセットする。    
        //－パラメータ.注文データの内容より取引時間コンテキストの 
        //　@　@プロパティをセットする。  
        WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
        
        //　@取引カレンダコンテキスト.証券会社コード = getInstitution()の戻り値.getInstitutionCode() 
        l_context.setInstitutionCode(l_institution.getInstitutionCode());
        
        //　@取引カレンダコンテキスト.部店コード = getBranch()の戻り値.getBranchCode()
        l_context.setBranchCode(l_branch.getBranchCode());
        
        //　@取引カレンダコンテキスト.市場コード = getMarket()の戻り値.getMarketCode() 
        l_context.setMarketCode(l_market.getMarketCode());
        
        //　@取引カレンダコンテキスト.受付時間区分 = (*1) 
        //　@(*1)受付時間区分 
        //　@・注文カテゴリ(注文データ.getOrderCateg())が”現引・現渡注文”の場合、 
        //　@　@　@”現引・現渡”をセットする。 
        
        if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.SWAP);
        }
        //  ・注文カテゴリが上記以外の場合、 
        //　@　@　@”株式・信用”をセットする。
        else
        {
            l_context.setTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
        }      
        
        //　@取引カレンダコンテキスト.銘柄コード = ”0：DEFAULT” 
        l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
        
        //　@取引カレンダコンテキスト.注文受付商品 = (*2) 
        //　@(*2)注文受付商品 
        //　@　@・注文カテゴリ（注文データ.getOrderCateg()）が”現物注文”の場合、 
        //　@　@　@　@”株式”をセットする。 

        if (OrderCategEnum.ASSET.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.STOCK);
        }
        //　@  ・注文カテゴリが上記以外の場合、 
        //　@　@　@　@”信用”をセットする。
        else
        {
            l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.MARGIN);
        }        
        
        //　@取引カレンダコンテキスト.注文受付トランザクション = (*3) 
        
        //　@(*3)注文受付トランザクション 
        //　@・注文種別 == （”現物買注文”または”新規買建注文”）の場合、         
        //　@　@    ”買付（新規建買）”をセットする。 
        if (OrderTypeEnum.EQUITY_BUY.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_LONG_MARGIN);
        }
        
        //  ・注文種別 == （”現物売注文”または”新規売建注文”）の場合、 
        //　@　@    ”売付（新規建売）”をセットする。 
        else if (OrderTypeEnum.EQUITY_SELL.equals(l_orderData.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_orderData.getOrderType()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.OPEN_SHORT_MARGIN);
        }
        
        //  ・注文カテゴリが”返済”の場合、"返済"をセットする。  
        else if (OrderCategEnum.CLOSE_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.CLOSE_MARGIN);
        }
        
        //  ・注文カテゴリが”現引現渡”の場合、"現引現渡"をセットする。 
        else if (OrderCategEnum.SWAP_MARGIN.equals(l_orderData.getOrderCateg()))
        {
            l_context.setOrderAcceptTransaction(
                WEB3OrderAccTransactionDef.SWAP_MARGIN);
        }
        
        //  －ThreadLocalSystemAttributesRegistry.setAttribute( )にて    
        //　@　@取引時間コンテキストをセットする。    
        //    設定キー： 取引時間管理.TRADING_CAL_CONTEXT_PATH    

        //５）　@受付日時、日付ロールをセットする。    
        //　@－取引時間管理.setTimestamp()をコールする。  
        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_context);
    
        //取引時間管理
        WEB3GentradeTradingTimeManagement.setTimestamp();//WEB3BaseException

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create手動発注手数料情報)
     * <BR>
     * 手動発注手数料情報を取得する。 <BR>
     * <BR>
     * シーケンス図 <BR>
     * 『(株式手動発注Unitサービス)create手動発注手数料』 <BR>
     * 参照。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)
     * @@param l_blnIsLimitPrice - (is指値)
     * @@return WEB3ManualCommissionInfoUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3ManualCommissionInfoUnit createManualCommissionInfoUnit(
        EqTypeOrderUnit l_orderUnit,
        boolean l_blnIsLimitPrice
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createManualCommissionInfoUnit(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //　@create手数料(注文単位)

        //引数は以下の通りに設定する。  
        //注文ID：　@パラメータ.注文単位.注文ID
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        
        WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
            (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
        
        EqTypeOrderUnitImpl l_orderUnitImpl = 
            (EqTypeOrderUnitImpl)l_orderUnit;        
        
        WEB3GentradeCommission l_commission = 
            l_eqBizLogicProvider.createCommission(l_orderUnitImpl);
        
        //　@手数料.is指値にパラメータ.is指値を設定する。
        l_commission.setIsLimitPrice(l_blnIsLimitPrice);
        
        //　@calc諸経費計算用代金(注文単位)

        //引数は以下の通りに設定する。

        //注文単位：　@パラメータ.注文単位
        //is指値：　@パラメータ.is指値 
        double l_dblExpensesCalcAmount = 
            this.calcExpensesCalcAmount(l_orderUnit, l_blnIsLimitPrice);
        
        //　@set諸経費計算用代金(諸経費計算用代金 : double)

        //引数は以下の通りに設定する。
        //諸経費計算用代金：　@calc諸経費計算用代金()の戻り値
        l_commission.setExpensesCalcAmount(l_dblExpensesCalcAmount);

        //　@get補助口座(口座ID : , 補助口座ID : )

        //引数は以下の通りに設定する。

        //口座ID：　@パラメータ．注文単位.getAccountId()
        //補助口座ID：　@パラメータ.注文単位.getSubAccountId()        
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        SubAccount l_subAccount = null;
        try
        {
            l_subAccount = l_gentradeAccountManager.getSubAccount(
                l_orderUnit.getAccountId(), l_orderUnit.getSubAccountId());
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
        
        //　@calc委託手数料(手数料 : 手数料, 補助口座 : SubAccount)

        //引数は以下の通りに設定する。
        //手数料：　@手数料 
        //補助口座：　@get補助口座()の戻り値
        l_eqBizLogicProvider.calcCommission(
            l_commission, 
            l_subAccount);
        
        //　@calc消費税(金額 : double, 基準日 : Timestamp, 補助口座 : 補助口座)

        //引数は以下の通りに設定する。  

        //金額：　@手数料.手数料金額   
        //基準日：　@手数料.発注日  
        //補助口座：　@get補助口座()の戻り値
        double l_dblSalesTax = 
            l_eqBizLogicProvider.calcSalesTax(
                l_commission.getCommission(),
                l_commission.getOrderBizDate(),
                l_subAccount);
        
        //　@手動発注手数料情報オブジェクトを生成する。
        WEB3ManualCommissionInfoUnit l_manualCommissionInfoUnit = 
            new WEB3ManualCommissionInfoUnit();

        //　@(*)セットプロパティ
        //手数料コース：　@手数料.get手数料コースコード()の戻り値
        l_manualCommissionInfoUnit.commissionCourse = l_commission.getCommissionCourseDiv();
        
        //手数料：　@手数料.get手数料金額()の戻り値
        l_manualCommissionInfoUnit.commission = WEB3StringTypeUtility.formatNumber(l_commission.getCommission());
        
        //手数料消費税：　@calc消費税()の戻り値
        l_manualCommissionInfoUnit.commissionConsumptionTax = WEB3StringTypeUtility.formatNumber(l_dblSalesTax);
        
        log.exiting(STR_METHOD_NAME);
        return l_manualCommissionInfoUnit;
    }
    
    /**
     * (calc諸経費計算用代金)
     * <BR>
     * 手数料計算の為の諸経費計算用代金を取得する。 <BR>
     * <BR>
     * １）　@以下の条件により分岐し、対応するメソッドをコールする。   <BR>
     * <BR>
     * 　@　@　@パラメータ.注文単位.注文種別が以下のいずれかに該当する場合、<BR> 
     * 　@　@　@　@　@　@・"現物買注文" <BR>
     * 　@　@　@　@　@　@・"新規買建注文" <BR>
     * 　@　@　@　@　@　@・"新規売建注文" <BR>
     * 　@　@　@株式計算サービス.calc拘束売買代金()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[calc拘束売買代金()に設定する引数] <BR>
     * 　@　@　@　@株数：　@パラメータ.注文単位.注文数量 <BR>
     * 　@　@　@　@計算単価：　@パラメータ.注文単位.注文単価 <BR>
     * 　@　@　@　@部店ＩＤ：　@パラメータ.注文単位.部店ID <BR>
     * 　@　@　@　@手数料商品コード：　@"上場株式" <BR>
     * 　@　@　@　@is指値：　@パラメータ.is指値<BR>
     * <BR>
     * <BR>
     * 　@　@　@パラメータ.注文単位.注文種別が以下のいずれかに該当する場合、 <BR>
     * 　@　@　@　@　@　@・"現物売注文" <BR>
     * 　@　@　@　@　@　@・"買建返済注文(売返済)" <BR>
     * 　@　@　@　@　@　@・"売建返済注文(買返済)" <BR>
     * 　@　@　@株式計算サービス.calc売買代金()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[calc売買代金()に設定する引数] <BR>
     * 　@　@　@　@数量：　@パラメータ.注文単位.注文数量 <BR>
     * 　@　@　@　@計算単価：　@パラメータ.注文単位.注文単価 <BR>
     * <BR>
     * <BR>
     * ２）　@１）の戻り値を返却する。 <BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)
     * @@param l_blnIsLimitPrice - (is指値)
     * @@throws WEB3BaseException
     * @@return double
     * @@roseuid 432175DD01A0
     */
    protected double calcExpensesCalcAmount(
        EqTypeOrderUnit l_orderUnit, boolean l_blnIsLimitPrice) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "calcExpensesCalcAmount(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@以下の条件により分岐し、対応するメソッドをコールする。   

        //　@　@パラメータ.注文単位.注文種別が以下のいずれかに該当する場合、 
        //　@　@　@　@　@・"現物買注文" 
        //　@　@　@　@　@・"新規買建注文" 
        //　@　@　@　@　@・"新規売建注文"
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = 
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        
        double l_dblExpensesCalcAmount = 0.0;
        
        if (OrderTypeEnum.EQUITY_BUY.equals(l_eqtypeOrderUnitRow.getOrderType()) ||
            OrderTypeEnum.MARGIN_LONG.equals(l_eqtypeOrderUnitRow.getOrderType()) || 
            OrderTypeEnum.MARGIN_SHORT.equals(l_eqtypeOrderUnitRow.getOrderType()))
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            //株式計算サービス.calc拘束売買代金()をコールする。 
            //　@　@　@[calc拘束売買代金()に設定する引数] 
            //　@　@　@株数：　@パラメータ.注文単位.注文数量 
            //　@　@　@計算単価：　@パラメータ.注文単位.注文単価 
            //　@　@　@部店ＩＤ：　@パラメータ.注文単位.部店ID 
            //　@　@　@手数料商品コード：　@"上場株式"
            //　@　@　@is指値：　@パラメータ.is指値 
            
            l_dblExpensesCalcAmount = 
                l_eqBizLogicProvider.calcRestraintTurnover(
                    l_eqtypeOrderUnitRow.getQuantity(),
                    l_eqtypeOrderUnitRow.getPrice(), 
                    l_eqtypeOrderUnitRow.getBranchId(), 
                    WEB3CommisionProductCodeDef.LISTING_STOCK, 
                    l_blnIsLimitPrice);                
        }        

        //　@　@パラメータ.注文単位.注文種別が以下のいずれかに該当する場合、 
        //　@　@　@　@　@・"現物売注文" 
        //　@　@　@　@　@・"買建返済注文(売返済)" 
        //　@　@　@　@　@・"売建返済注文(買返済)" 
        if (OrderTypeEnum.EQUITY_SELL.equals(l_eqtypeOrderUnitRow.getOrderType()) ||
            OrderTypeEnum.CLOSE_MARGIN_LONG.equals(l_eqtypeOrderUnitRow.getOrderType()) || 
            OrderTypeEnum.CLOSE_MARGIN_SHORT.equals(l_eqtypeOrderUnitRow.getOrderType()))
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            //　@　@株式計算サービス.calc売買代金()をコールする。 

            //　@　@　@[calc売買代金()に設定する引数] 
            //　@　@　@数量：　@パラメータ.注文単位.注文数量 
            //　@　@　@計算単価：　@パラメータ.注文単位.注文単価           
            
            l_dblExpensesCalcAmount = 
                l_eqBizLogicProvider.calcTurnover(
                    l_eqtypeOrderUnitRow.getQuantity(), 
                    l_eqtypeOrderUnitRow.getPrice());
        }

        //２）　@１）の戻り値を返却する。 

        log.exiting(STR_METHOD_NAME);
        return l_dblExpensesCalcAmount;
    }
    
    /**
     * (getUnitレスポンス)
     * <BR>
     * 株式手動発注Unitにプロパティをセットする。 <BR>
     * <BR>
     * １）　@注文データを"株式注文単位型"にキャストする。 <BR>
     * 　@　@　@（※以下、キャストしたオブジェクトを"注文単位"と表記する。） <BR>
     * <BR>
     * ２）　@this.createUnitレスポンス()をコールする。<BR>
     * <BR>
     * ３）　@部店オブジェクトを取得する。 <BR>
     * 　@　@  拡張アカウントマネージャ.getBranch()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[getBranch()に設定する引数] <BR>
     * 　@　@　@　@arg0：　@注文単位.getBranchId() <BR>
     * <BR>
     * ４）　@顧客オブジェクトを取得する。 <BR>
     * 　@　@　@拡張アカウントマネージャ.get顧客()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get顧客()に設定する引数] <BR>
     * 　@　@　@　@arg0：　@注文単位.getAccountId() <BR>
     * <BR>
     * ５）　@補助口座を取得する。 <BR>
     * 　@　@　@拡張アカウントマネージャ.get補助口座()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get補助口座()に設定する引数] <BR>
     * 　@　@　@　@arg0：　@注文単位.getAccountId() <BR>
     * 　@　@　@　@arg1：　@注文単位.getSubAccountId() <BR>
     * <BR>
     * ６）　@市場を取得する。 <BR>
     * 　@　@６－１）　@注文単位Rowを生成する。 <BR>
     * 　@　@　@　@　@　@　@　@注文単位.getDataSourceObject()をコールする。 <BR>
     * <BR>
     * 　@　@６－２）　@拡張金融オブジェクトマネージャ.get市場()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@[get市場()に設定する引数] <BR>
     * 　@　@　@　@　@　@　@　@　@注文単位Row.getMarketId() <BR>
     * <BR>
     * ７）　@株式銘柄を取得する。 <BR>
     * 　@　@　@注文単位.getProduct()をコールする。 <BR>
     * <BR>
     * ８）　@取引銘柄を取得する。 <BR>
     * 　@　@　@注文単位.getTradedProduct()をコールする。 <BR>
     * <BR>
     * ９）　@株式銘柄Rowを生成する。 <BR>
     * 　@　@　@getProduct()の戻り値.getDataSourceObject()コールする。 <BR>
     * <BR>
     * １０）商品区分を取得する。 <BR>
     * 　@　@  株式データアダプタ.get商品区分()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get商品区分()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * １１） 取引区分を取得する。 <BR>
     * 　@　@　@株式データアダプタ.get取引区分()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get取引区分()に設定する引数] <BR>
     * 　@　@　@　@注文種別：　@注文単位.注文種別 <BR>
     * <BR>
     * １２） 執行条件を取得する。 <BR>
     * 　@　@　@拡張株式注文マネージャ.get執行条件(SONAR)()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get執行条件(SONAR)()に設定する引数] <BR>
     * 　@　@　@　@執行条件：　@注文単位.執行条件 <BR>
     * <BR>
     * １３） 出来るまで注文か判定する。 <BR>
     * 　@　@　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。<BR> 
     * <BR>
     * 　@　@　@　@[is出来るまで注文単位()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * １４） 注文状態区分を取得する。 <BR>
     * 　@　@　@株式データアダプタ.get注文状態区分()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get注文状態区分()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * １５） 約定状態区分を取得する。 <BR>
     * 　@　@　@株式データアダプタ.get約定状態区分()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get約定状態区分()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * １６） 時価を取得する。 <BR>
     * 　@　@　@拡張プロダクトマネージャ.get表示用時価情報()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get表示用時価情報()に設定する引数] <BR>
     * 　@　@　@　@取引銘柄：　@getTradedProduct() <BR>
     * 　@　@　@　@補助口座：　@get補助口座() <BR>
     * <BR>
     * １７） 口座区分を取得する。 <BR>
     * 　@　@　@株式データアダプタ.get口座区分()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get口座区分()に設定する引数] <BR>
     * 　@　@　@　@税区分：　@注文単位.税区分 <BR>
     * <BR>
     * １８） 処理状況区分を取得する。 <BR>
     * 　@　@　@株式データアダプタ.get処理状況区分()をコールする。 <BR>
     * <BR>
     * 　@　@　@　@[get処理状況区分()に設定する引数] <BR>
     * 　@　@　@　@注文単位：　@注文単位 <BR>
     * <BR>
     * １９）　@注文単位.注文種別が"現物売付注文"の場合のみ<BR>
     * 　@　@　@概算簿価単価を取得する。<BR>
     * <BR>
     * 　@　@　@株式計算サービス.calc概算簿価単価()をコールする。<BR>
     * 　@　@　@※例外がスローされた場合、"null"をセット。<BR>
     * <BR>
     * 　@　@　@　@[calc概算簿価単価()に設定する引数]<BR>
     * 　@　@　@　@銘柄ID：　@注文単位Row.銘柄ID<BR>
     * 　@　@　@　@補助口座：　@get補助口座()の戻り値<BR>
     * 　@　@　@　@税区分：　@注文単位.税区分<BR>
     * <BR>
     * ２０）　@注文単位.注文種別が"新規買建注文"または"新規売建注文"の場合のみ<BR>
     * 　@　@　@（部店市場弁済別）取扱条件を取得する。<BR>
     * <BR>
     * 　@　@　@（部店市場弁済別）取扱条件()をコールする。<BR>
     * <BR>
     * 　@　@　@　@[（部店市場弁済別）取扱条件()に設定する引数]<BR>
     * 　@　@　@　@証券会社コード：　@get補助口座().証券会社コード<BR>
     * 　@　@　@　@部店コード：　@getBranch()の戻り値.get部店コード<BR>
     * 　@　@　@　@市場コード：　@get市場()の戻り値.get市場コード<BR>
     * 　@　@　@　@弁済区分：　@注文単位Row.弁済区分<BR>
     * 　@　@　@　@弁済期限値：　@注文単位Row.弁済期限値<BR>
     * <BR>
     * ２１）　@注文単位.注文カテゴリが"現物株式"以外の場合のみ、<BR>
     * 　@　@　@　@信用取引弁済オブジェクトを生成する。<BR>
     * 　@　@　@　@※以外は"null"をセット。<BR>
     * <BR>
     * 　@　@　@　@信用取引弁済.弁済区分：　@注文単位Row.弁済区分<BR>
     * 　@　@　@　@信用取引弁済.弁済期限値：　@注文単位Row.弁済期限値<BR>
     * <BR>
     * ２２）　@createUnitレスポンス()で取得した、 <BR>
     * 　@　@　@　@株式手動発注Unitのプロパティをセットする。<BR>
     * <BR>
     * 　@【商品共通プロパティ】 <BR>
     * 　@ID：　@注文単位.注文ID <BR>
     * 　@部店コード：　@getBranch()の戻り値.get部店コード() <BR>
     * 　@顧客コード：　@get顧客()の戻り値.get顧客コード() <BR>
     * 　@市場コード：　@get市場()の戻り値.get市場コード() <BR>
     *   銘柄コード：　@株式銘柄Row.get銘柄コード() <BR>
     * 　@銘柄名：　@株式銘柄Row.get銘柄名() <BR>
     * 　@商品区分：　@get商品区分()の戻り値 <BR>
     * 　@取引区分：　@get取引区分()の戻り値 <BR>
     * 　@執行条件：　@get執行条件(SONAR)()の戻り値 <BR>
     * 　@注文期限区分：　@is出来るまで注文単位()の戻り値がtureの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@"出来るまで注文"をセット。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@以外"当日限り"、をセット。 <BR>
     * 　@注文有効期限：　@is出来るまで注文単位()の戻り値がtureの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@注文単位.注文失効日付をセット。 <BR>
     * 　@注文数量：　@注文単位．注文数量 <BR>
     * 　@注文単価区分：　@注文単位.isMarketOrder()の戻り値がtrueの場合、<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@"成行"をセット。 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@falseの場合は"指値"をセット。 <BR>
     * 　@注文単価：　@注文単価区分が"指値"の場合、注文単位.指値をセット。 <BR>
     * 　@注文状態区分：　@get注文状態区分()の戻り値 <BR>
     * 　@約定状態区分：　@get約定状態区分()の戻り値 <BR>
     * 　@訂正取消区分：　@注文単位.注文訂正・取消区分 <BR>
     * 　@注文時間：　@注文単位.受注日時 <BR>
     * 　@発注日：　@注文単位.発注日 <BR>
     * 　@受渡日：　@注文単位.受渡日 <BR>
     * 　@概算受渡代金：　@注文単位.概算受渡代金 <BR>
     * 　@決済順序：　@注文単位.決済順序区分 <BR>
     * 　@時価区分：　@get表示用時価情報の戻り値.get時価区分() <BR>
     * 　@時価（現在値）：　@get表示用時価情報の戻り値.get時価() <BR>
     * 　@前日比：　@get表示用時価情報の戻り値.get前日比() <BR>
     * 　@取引時間（時価発表時間）：　@get表示用時価情報()の戻り値.get時価発表時間() <BR>
     * <BR>
     * 　@板情報項目として以下を設定する<BR>
     * 　@　@現在値：　@取得した株式銘柄時価情報.get現在値() <BR>
     * 　@　@現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻() <BR>
     * 　@　@現在値区分：　@取得した株式銘柄時価情報.get現在値区分() <BR>
     * 　@　@現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比() <BR>
     * 　@　@出来高：　@取得した株式銘柄時価情報.get出来高()の戻り値をセット <BR>
     * 　@　@出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻() <BR>
     * 　@　@買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分() <BR>
     * 　@　@買気配値：　@取得した株式銘柄時価情報.get買気配値() <BR>
     * 　@　@買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻() <BR>
     * 　@　@売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分() <BR>
     * 　@　@売気配値：　@取得した株式銘柄時価情報.get売気配値() <BR>
     * 　@　@売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻() <BR>
     * 　@　@基準値段：　@取得した株式銘柄時価情報.get基準値段()<BR>
     * <BR>
     *   単価調整値：　@nullをセット。<BR>
     * <BR>
     * 　@口座区分：　@get口座区分()の戻り値 <BR>
     * 　@値段条件：　@注文単位.値段条件 <BR>
     * 　@処理状況区分：　@get処理状況区分()の戻り値 <BR> 
     * 　@概算簿価単価：　@（*1）calc概算簿価単価()の戻り値<BR>
     * 　@金利：　@注文単位.注文種別が"新規買建注文"の場合、<BR>
     * 　@　@　@　@　@（部店市場弁済別）取扱条件.買方金利をセット。<BR>
     * 　@　@　@　@　@注文単位.注文種別が"新規売建注文"の場合、<BR>
     * 　@　@　@　@　@（部店市場弁済別）取扱条件.売方金利をセット。<BR>
     * 　@　@　@　@　@以外、nullをセット。<BR>
     * 　@清算期間：　@（*2）（部店市場弁済別）取扱条件.建株諸経費清算期間<BR>
     * 　@弁済：　@信用取引弁済オブジェクトをセット。<BR>
     * <BR>
     * （*1）注文単位.注文種別が"現物売注文"の場合のみセット。<BR>
     * 　@　@　@以外、nullをセット。<BR>
     * <BR>
     * （*2）以下条件に該当する場合のみセット。<BR>
     * 　@　@　@　@　@・注文単位.注文種別が"新規買建注文"または"新規売建注文"<BR>
     * 　@　@　@　@　@・注文単位Row.弁済区分が"一般信用"<BR>
     * 　@　@　@　@　@・注文単位Row.弁済期限値が"9999999"（＝ALL9）<BR>
     *<BR>
     * ２３）　@プロパティをセットした株式手動発注Unitを返す。 <BR>
     * <BR>
     * @@param l_orderData - (注文データ)
     * @@return WEB3EquityManualUnit
     * @@throws WEB3BaseException
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getUnitResponse(
        EqTypeOrderUnit l_orderData) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getUnitResponse(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderData == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }

        //１）　@注文データを"株式注文単位型"にキャストする。 
        //　@    （※以下、キャストしたオブジェクトを"注文単位"と表記する。） 
        
        EqTypeOrderUnit l_eqTypeOrderUnit = l_orderData;
        
        //２）　@this.createUnitレスポンス()をコールする。
        WEB3EquityManualUnit l_equityManualUnit = this.createUnitResponse();

        //３）　@部店オブジェクトを取得する。 
        //　@　@　@拡張アカウントマネージャ.getBranch()をコールする。 

        //　@　@　@　@[getBranch()に設定する引数] 
        //　@　@　@　@arg0：　@注文単位.getBranchId() 
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        Branch l_branch = null;
        EqtypeOrderUnitRow l_eqtypeOrderUnitRow = null;
        MainAccount l_mainAccount = null;
        SubAccount l_subAccount = null;
        WEB3GentradeMarket l_market = null;
        try
        {
            //NotFoundException
            l_branch = l_accountManager.getBranch(l_eqTypeOrderUnit.getBranchId());
        
            //４）　@顧客オブジェクトを取得する。 
            //　@　@拡張アカウントマネージャ.get顧客()をコールする。 
            //　@　@[get顧客()に設定する引数] 
            //　@　@arg0：　@注文単位.getAccountId() 
            l_mainAccount = 
                l_accountManager.getMainAccount(l_eqTypeOrderUnit.getAccountId());
    
            //５）　@補助口座を取得する。 
            //　@　@　@拡張アカウントマネージャ.get補助口座()をコールする。
            //　@　@[get補助口座()に設定する引数] 
            //　@　@　@　@arg0：　@注文単位.getAccountId() 
            //　@　@    arg1：　@注文単位.getSubAccountId() 
            l_subAccount = l_accountManager.getSubAccount(
                l_eqTypeOrderUnit.getAccountId(),
                l_eqTypeOrderUnit.getSubAccountId());

        
            //６）　@市場を取得する。 
            //　@６－１）　@注文単位Rowを生成する。 
            //　@　@　@　@注文単位.getDataSourceObject()をコールする。 
            l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)
                l_eqTypeOrderUnit.getDataSourceObject();        
    
            
            // ６－２）　@拡張金融オブジェクトマネージャ.get市場()をコールする。 

            //　@　@　@　@　@　@　@[get市場()に設定する引数] 
            //　@　@　@　@　@　@　@注文単位Row.getMarketId()         
            
            WEB3GentradeFinObjectManager l_finObjManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            long l_lngMarketId = l_eqtypeOrderUnitRow.getMarketId();
            
            l_market =
                (WEB3GentradeMarket)l_finObjManager.getMarket(
                    l_lngMarketId);    //NotFoundException
            
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

        //７）　@株式銘柄を取得する。 
        //　@注文単位.getProduct()をコールする。 
        EqTypeProduct l_eqtypeProduct = (EqTypeProduct)
            l_eqTypeOrderUnit.getProduct();

        //８）　@取引銘柄を取得する。 
        //　@注文単位.getTradedProduct()をコールする。 
        EqTypeTradedProduct l_eqTypeTradedProduct = (EqTypeTradedProduct)
            l_eqTypeOrderUnit.getTradedProduct();

        //９）　@株式銘柄Rowを生成する。 
        //　@　@　@getProduct()の戻り値.getDataSourceObject()コールする。 
        EqtypeProductRow l_eqtypeProductRow = (EqtypeProductRow)
            l_eqtypeProduct.getDataSourceObject();

        //１０）　@商品区分を取得する。 
        //　@　@　@株式データアダプタ.get商品区分()をコールする。 

        //　@　@　@　@[get商品区分()に設定する引数] 
        //　@　@　@　@注文単位：　@注文単位
        String l_strProductType = WEB3EquityDataAdapter.getProductType(l_eqTypeOrderUnit);
        
        //１１）　@取引区分を取得する。 
        //　@株式データアダプタ.get取引区分()をコールする。 

        //　@　@[get取引区分()に設定する引数] 
        //　@　@注文種別：　@注文単位.注文種別 
        String l_strTradingType = WEB3EquityDataAdapter.getTradingType(l_eqtypeOrderUnitRow.getOrderType());
        
        //１２）　@執行条件を取得する。 
        //　@拡張株式注文マネージャ.get執行条件(SONAR)()をコールする。 

        //　@　@[get執行条件(SONAR)()に設定する引数] 
        //　@　@執行条件：　@注文単位.執行条件 
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        String l_strExecutionConditionTypeSonar = 
            l_orderManager.getExecutionConditionTypeSonar(
                l_eqtypeOrderUnitRow.getExecutionConditionType());

        //１３）　@出来るまで注文か判定する。 
        //　@　@拡張株式注文マネージャ.is出来るまで注文単位()をコールする。         

        //　@　@[is出来るまで注文単位()に設定する引数] 
        //　@　@注文単位：　@注文単位 
        boolean l_blnIsCarriedOrderUnit = 
            l_orderManager.isCarriedOrderUnit(l_eqTypeOrderUnit);
        
        //１４）　@注文状態区分を取得する。 
        //　@　@　@株式データアダプタ.get注文状態区分()をコールする。 

        //　@　@[get注文状態区分()に設定する引数] 
        //　@　@注文単位：　@注文単位 
        String l_strOrderState =  WEB3EquityDataAdapter.getOrderState(l_eqTypeOrderUnit);
       
        //１５）　@約定状態区分を取得する。 
        //　@株式データアダプタ.get約定状態区分()をコールする。 
 
        //[get約定状態区分()に設定する引数] 
        //　@注文単位：　@注文単位
        String l_strExecType = WEB3EquityDataAdapter.getExecType(l_eqTypeOrderUnit);

        //１６）　@時価を取得する。 
        //拡張プロダクトマネージャ.get表示用時価情報()をコールする。 
 
        //　@[get表示用時価情報()に設定する引数] 
        //　@取引銘柄：　@getTradedProduct() 
        //　@補助口座：　@get補助口座()
        WEB3EquityProductManager l_productManager =  
            (WEB3EquityProductManager)l_tradingModule.getProductManager();
       
        WEB3EquityProductQuote l_equityProductQuote = 
        l_productManager.getDisplayEquityProductQuote(
                l_eqTypeTradedProduct,(WEB3GentradeSubAccount)l_subAccount);         
       
        //１７）　@口座区分を取得する。 
        //株式データアダプタ.get口座区分()をコールする。 

       //　@[get口座区分()に設定する引数] 
       //　@税区分：　@注文単位.税区分 
         String l_strTaxType = WEB3EquityDataAdapter.getTaxType(l_eqTypeOrderUnit.getTaxType());
       
        //１８）　@処理状況区分を取得する。 
        //株式データアダプタ.get処理状況区分()をコールする。 

        //　@[get処理状況区分()に設定する引数] 
        //　@注文単位：　@注文単位 
        String l_strTransactionStatusType =  
        WEB3EquityDataAdapter.getTransactionStatusType(l_eqTypeOrderUnit);

        //１９）　@注文単位.注文種別が"現物売付注文"の場合のみ
        //　@　@　@概算簿価単価を取得する。
        String l_estimatedBookPrice = null;
        
        if (OrderTypeEnum.EQUITY_SELL.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            //　@　@　@株式計算サービス.calc概算簿価単価()をコールする。
            //　@　@　@※例外がスローされた場合、"null"をセット。
            WEB3EquityBizLogicProvider l_eqBizLogicProvider = 
                (WEB3EquityBizLogicProvider) l_tradingModule.getBizLogicProvider();
            
            try
            {
                //　@　@　@　@[calc概算簿価単価()に設定する引数]
                l_estimatedBookPrice =
                    WEB3StringTypeUtility.formatNumber(l_eqBizLogicProvider.calcEstimatedBookPrice(
                        l_eqtypeOrderUnitRow.getProductId(),//注文単位Row.銘柄ID
                        l_subAccount,                       //get補助口座()の戻り値
                        l_eqTypeOrderUnit.getTaxType()));   //注文単位.税区分
            }
            catch(WEB3BaseException l_ex)
            { 
                l_estimatedBookPrice = null;
            }
            
        }

        //２０）　@注文単位.注文種別が"新規買建注文"または"新規売建注文"の場合のみ
        //　@　@　@（部店市場弁済別）取扱条件を取得する。
        BranchMarketRepayDealtCondRow l_branchMarketRepayDealtCondRow = null;
        String l_interestRates = null;
        if (OrderTypeEnum.MARGIN_LONG.equals(l_eqTypeOrderUnit.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            //　@　@　@（部店市場弁済別）取扱条件()をコールする。
            //　@　@　@　@[（部店市場弁済別）取扱条件()に設定する引数]
            WEB3GentradeBranchMarketRepayDealtCond l_genBranchMarketRepayDealtCond = 
                new WEB3GentradeBranchMarketRepayDealtCond(
                    l_subAccount.getInstitution().getInstitutionCode(),//get補助口座().証券会社コード
                    l_branch.getBranchCode(),                          //getBranch()の戻り値.get部店コード
                    l_market.getMarketCode(),                          //get市場()の戻り値.get市場コード
                    l_eqtypeOrderUnitRow.getRepaymentType(),           //注文単位Row.弁済区分
                    l_eqtypeOrderUnitRow.getRepaymentNum()             //注文単位Row.弁済期限値
                );
               
            l_branchMarketRepayDealtCondRow = 
                (BranchMarketRepayDealtCondRow)l_genBranchMarketRepayDealtCond.getDataSourceObject();
        }
        
        //２１）　@注文単位.注文カテゴリが"現物株式"以外の場合のみ、
        //　@　@　@　@信用取引弁済オブジェクトを生成する。
        //　@　@　@　@※以外は"null"をセット。
        //　@　@　@　@信用取引弁済.弁済区分：　@注文単位Row.弁済区分
        //　@　@　@　@信用取引弁済.弁済期限値：　@注文単位Row.弁済期限値
        WEB3MarginRepaymentUnit l_repayment = null;
        if (!OrderCategEnum.ASSET.equals(l_eqTypeOrderUnit.getOrderCateg()))
        {
            l_repayment = new WEB3MarginRepaymentUnit();
            l_repayment.repaymentDiv = l_eqtypeOrderUnitRow.getRepaymentType();
            l_repayment.repaymentTimeLimit = String.valueOf(l_eqtypeOrderUnitRow.getRepaymentNum());
        }
        
        //２２）　@createUnitレスポンス()で取得した、
        //　@　@　@　@株式手動発注Unitのプロパティをセットする。  
        //      　@【商品共通プロパティ】 
        //      　@ID：　@注文単位.注文ID 
        l_equityManualUnit.id = l_eqTypeOrderUnit.getOrderId() + "";
        
        //    部店コード：　@getBranch()の戻り値.get部店コード() 
        l_equityManualUnit.branchCode = l_branch.getBranchCode();
            
        //    顧客コード：　@get顧客()の戻り値.get顧客コード() 
        l_equityManualUnit.accountCode = 
            l_mainAccount.getAccountCode().substring(0, 6);
        
        //    市場コード：　@get市場()の戻り値.get市場コード() 
        l_equityManualUnit.marketCode = l_market.getMarketCode();
        
        //    銘柄コード：　@株式銘柄Row.get銘柄コード()
        l_equityManualUnit.productCode = l_eqtypeProductRow.getProductCode();
        
        //    銘柄名：　@株式銘柄Row.get銘柄名() 
        l_equityManualUnit.productName = l_eqtypeProductRow.getStandardName();
        
        //    商品区分：　@get商品区分()の戻り値 
        l_equityManualUnit.productDiv = l_strProductType;
        
        //    取引区分：　@get取引区分()の戻り値
        l_equityManualUnit.tradingType = l_strTradingType;
        
        //    執行条件：　@get執行条件(SONAR)()の戻り値 
        l_equityManualUnit.execCondType = l_strExecutionConditionTypeSonar;
        
        //    注文期限区分：　@is出来るまで注文単位()の戻り値がtureの場合、
        //        "出来るまで注文"をセット。 
        //        以外"当日限り"、をセット。
        l_equityManualUnit.expirationDateType = l_blnIsCarriedOrderUnit ? 
            WEB3OrderExpirationDateTypeDef.CARRIED_ORDER :
            WEB3OrderExpirationDateTypeDef.DAY_LIMIT;
        
        //    注文有効期限：　@is出来るまで注文単位()の戻り値がtureの場合、
        //        注文単位.注文失効日付をセット。
        if (l_blnIsCarriedOrderUnit == true)
        {
            l_equityManualUnit.expirationDate = l_eqTypeOrderUnit.getExpirationTimestamp();
        }
        
        //   注文数量：　@注文単位．注文数量 
        l_equityManualUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(
            l_eqTypeOrderUnit.getQuantity());
        
        //   注文単価区分：　@注文単位.isMarketOrder()の戻り値がtrueの場合、"成行"をセット。 
        //      　@　@　@　@　@　@　@　@　@　@　@　@falseの場合は"指値"をセット。 
        l_equityManualUnit.orderPriceDiv = l_eqTypeOrderUnit.isMarketOrder() ?
            WEB3OrderPriceDivDef.MARKET_PRICE :
            WEB3OrderPriceDivDef.LIMIT_PRICE;
        
        //   注文単価：　@注文単価区分が"指値"の場合、注文単位.指値をセット。
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_equityManualUnit.orderPriceDiv))
        {
            l_equityManualUnit.limitPrice = WEB3StringTypeUtility.formatNumber(
                l_eqTypeOrderUnit.getLimitPrice());
        }
        
        //   注文状態区分：　@get注文状態区分()の戻り値
        l_equityManualUnit.orderState = l_strOrderState;
        
        //   約定状態区分：　@get約定状態区分()の戻り値 
        l_equityManualUnit.execType = l_strExecType;
        
        //   訂正取消区分：　@注文単位.注文訂正・取消区分
        l_equityManualUnit.changeCancelDiv = 
            l_eqtypeOrderUnitRow.getModifyCancelType();
        
        //   注文時間：　@注文単位.受注日時
        l_equityManualUnit.orderDate = 
            l_eqtypeOrderUnitRow.getReceivedDateTime();
        
        //   発注日：　@注文単位.発注日 
        l_equityManualUnit.orderBizDate =
            WEB3DateUtility.getDate(l_eqtypeOrderUnitRow.getBizDate(), "yyyyMMdd");
        
        //   受渡日：　@注文単位.受渡日 
        l_equityManualUnit.deliveryDate =
            l_eqtypeOrderUnitRow.getDeliveryDate();
        
        //   概算受渡代金：　@注文単位.概算受渡代金 
        l_equityManualUnit.estimatedPrice = WEB3StringTypeUtility.formatNumber(
            l_eqtypeOrderUnitRow.getEstimatedPrice());
        
        //   決済順序：　@注文単位.決済順序区分 
        l_equityManualUnit.closingOrder = l_eqtypeOrderUnitRow.getClosingOrderType();
        
        //   時価区分：　@get表示用時価情報の戻り値.get時価区分()
        l_equityManualUnit.currentPriceDiv = l_equityProductQuote.getQuoteTypeDiv();
        
        //   時価（現在値）：　@get表示用時価情報の戻り値.get時価() 
        l_equityManualUnit.currentPrice = WEB3StringTypeUtility.formatNumber(
            l_equityProductQuote.getQuote());
        
        //   前日比：　@get表示用時価情報の戻り値.get前日比() 
        l_equityManualUnit.comparedPreviousDay = WEB3StringTypeUtility.formatNumber(
            l_equityProductQuote.getComparedPreviousDay());
        
        //   取引時間（時価発表時間）：　@get表示用時価情報()の戻り値.get時価発表時間() 
        l_equityManualUnit.currentPriceTime = l_equityProductQuote.getQuoteTime();

        //板情報項目として以下を設定する
        //現在値：　@取得した株式銘柄時価情報.get現在値()
        l_equityManualUnit.boardCurrentPrice = l_equityProductQuote.getBoardCurrentPrice();

        //現在値時刻：　@取得した株式銘柄時価情報.get現在値時刻()
        l_equityManualUnit.boardCurrentPriceTime = l_equityProductQuote.getBoardCurrentPriceTime();

        //現在値区分：　@取得した株式銘柄時価情報.get現在値区分()
        l_equityManualUnit.boardCurrentPriceDiv = l_equityProductQuote.getBoardCurrentPriceDiv();

        //現在値前日比：　@取得した株式銘柄時価情報.get現在値前日比()
        l_equityManualUnit.boardChange = l_equityProductQuote.getBoardChange();

        //出来高：　@取得した株式銘柄時価情報.get出来高()の戻り値をセット
        l_equityManualUnit.volume = l_equityProductQuote.getVolume();

        //出来高時刻：　@取得した株式銘柄時価情報.get出来高時刻()
        l_equityManualUnit.volumeTime = l_equityProductQuote.getVolumeTime();

        //買気配値タイトル区分：　@取得した株式銘柄時価情報.get買気配値タイトル区分()
        l_equityManualUnit.askPriceTitle = l_equityProductQuote.getAskPriceTitle();

        //買気配値：　@取得した株式銘柄時価情報.get買気配値()
        l_equityManualUnit.askPrice = l_equityProductQuote.getAskPrice();

        //買気配値時刻：　@取得した株式銘柄時価情報.get買気配値時刻()
        l_equityManualUnit.askPriceTime = l_equityProductQuote.getAskPriceTime();

        //売気配値タイトル区分：　@取得した株式銘柄時価情報.get売気配値タイトル区分()
        l_equityManualUnit.bidPriceTitle = l_equityProductQuote.getBidPriceTitle();
 
        //売気配値：　@取得した株式銘柄時価情報.get売気配値()
        l_equityManualUnit.bidPrice = l_equityProductQuote.getBidPrice();

        //売気配値時刻：　@取得した株式銘柄時価情報.get売気配値時刻()
        l_equityManualUnit.bidPriceTime = l_equityProductQuote.getBidPriceTime();

        //基準値段：　@取得した株式銘柄時価情報.get基準値段()
        l_equityManualUnit.basePrice = l_equityProductQuote.getBasePrice();

        //　@　@単価調整値：　@nullをセット。
        l_equityManualUnit.priceAdjustmentValue = null;
        
        //   【株式固有のプロパティ】 
        //    口座区分：　@get口座区分()の戻り値 
        l_equityManualUnit.taxType = l_strTaxType;
        
        //    値段条件：　@注文単位.値段条件 
        l_equityManualUnit.priceCondType = l_eqtypeOrderUnitRow.getPriceConditionType();
        
        //    処理状況区分：　@get処理状況区分()の戻り値 
        l_equityManualUnit.transactionStateType = l_strTransactionStatusType;
        
        //    概算簿価単価：　@（*1）calc概算簿価単価()の戻り値 
        l_equityManualUnit.estimatedBookPrice = l_estimatedBookPrice;
        
        //    金利：　@注文単位.注文種別が"新規買建注文"の場合、
        //　@　@　@　@　@　@（部店市場弁済別）取扱条件.買方金利をセット。
        if (OrderTypeEnum.MARGIN_LONG.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            l_equityManualUnit.interestRates =
                WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getBuyInterestRate());
        }
        //    　@　@　@　@注文単位.注文種別が"新規売建注文"の場合、
        //　@　@　@　@　@　@（部店市場弁済別）取扱条件.売方金利をセット。
        else if (OrderTypeEnum.MARGIN_SHORT.equals(l_eqTypeOrderUnit.getOrderType()))
        {
            l_equityManualUnit.interestRates =
                WEB3StringTypeUtility.formatNumber(l_branchMarketRepayDealtCondRow.getSellInterestRate());
        }
        
        //    清算期間：　@（*2）（部店市場弁済別）取扱条件.建株諸経費清算期間
        //    　@以下の条件に該当する場合のみセット。
        //　@　@　@　@　@　@・注文単位.注文種別が"新規買建注文"または"新規売建注文"
        //　@　@　@　@　@　@・注文単位Row.弁済区分が"一般信用"
        //　@　@　@　@　@　@・注文単位Row.弁済期限値が"9999999"（＝ALL9）
        if ((OrderTypeEnum.MARGIN_LONG.equals(l_eqTypeOrderUnit.getOrderType()) ||
            OrderTypeEnum.MARGIN_SHORT.equals(l_eqTypeOrderUnit.getOrderType())) &&
            WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN.equals(l_eqtypeOrderUnitRow.getRepaymentType()) &&
            l_eqtypeOrderUnitRow.getRepaymentNum() == 9999999)
        {
            l_equityManualUnit.clearUpTerm =
                String.valueOf(l_branchMarketRepayDealtCondRow.getContLiquidateTerm());
        }
        
        //    弁済：　@信用取引弁済オブジェクトをセット。
        l_equityManualUnit.repayment = l_repayment;
        
        //２３）　@プロパティをセットした株式手動発注Unitを返す。
        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }
    
    /**
     * (createUnitレスポンス)
     * <BR>
     * 株式手動発注Unitを生成して返す。<BR>
     * @@return WEB3EquityManualUnit
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit createUnitResponse()
    {
        final String STR_METHOD_NAME = "createUnitResponse()";
        log.entering(STR_METHOD_NAME);
        
        //株式手動発注Unitを生成して返す。
        WEB3EquityManualUnit l_equityManualUnit = new WEB3EquityManualUnit();

        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }

    /**
     * (create建株明細ByOrder)
     * <BR>
     * 引数の注文単位に関連する信用取引建株明細の  <BR>
     * 一覧を作成する。  <BR>
     * <BR>
     * 拡張株式注文マネージャ.create建株明細ByOrder(注文単位．注文単位ID)に  <BR>
     * delegateする。<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)
     * @@return WEB3MarginContractUnit[]
     * @@roseuid 432175DD01A0
     */
    protected WEB3MarginContractUnit[] createContractUnitByOrder(
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createContractUnitByOrder(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "パラメータ値不正。");
        }
        
        //拡張株式注文マネージャ.create建株ByOrder(注文単位．注文単位ID)に  
        //delegateする。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager(); 
        
        WEB3MarginContractUnit[] l_marginContractUnits = 
            l_orderManager.createContractUnitByOrder(l_orderUnit.getOrderUnitId());

        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
    
    /**
     * (getエラーUnitレスポンス)
     * <BR>
     * 引数のエラーコードを株式手動発注Unitにセットし、返す。 <BR>
     * <BR>
     * １）　@株式手動発注Unitを生成する。 <BR>
     * 　@　@　@this.createUnitレスポンス()をコールする。 <BR>
     * <BR>
     * ２）　@１）で生成したインスタンスに以下のプロパティをセットする。 <BR>
     * <BR>
     * 　@　@　@　@手動発注エラーコード ：　@パラメータ.手動発注エラーコード <BR>
     * <BR>
     * 　@　@　@　@※それ以外の項目：　@null<BR>
     * <BR>
     * @@param l_strManualErrorCode - (手動発注エラーコード)
     * @@return WEB3EquityManualUnit
     * @@roseuid 432175DD01A0
     */
    protected WEB3EquityManualUnit getErrorUnitResponse(String l_strManualErrorCode)
    {
        final String STR_METHOD_NAME = "getErrorUnitResponse(String)";
        log.entering(STR_METHOD_NAME);

        //１）　@株式手動発注Unitを生成する。 
        //this.createUnitレスポンス()をコールする。
        WEB3EquityManualUnit l_equityManualUnit = this.createUnitResponse();

        //２）　@１）で生成したインスタンスに以下のプロパティをセットする。 

        //　@　@　@　@手動発注エラーコード ：　@パラメータ.手動発注エラーコード 
        //　@　@　@　@※それ以外の項目：　@null
        l_equityManualUnit.manualOrderErrorCode = l_strManualErrorCode;        
        
        log.exiting(STR_METHOD_NAME);
        return l_equityManualUnit;
    }
    
    /**
     * (sendRLSRequest)
     * 抽象メソッド（abstract）
     * <BR>
     * @@param l_orderData - (注文データ)
     * @@param l_submitterLoginId - (発注者ログインID)
     * @@param l_strNotifyType - (通知経路)
     * @@roseuid 432175DD01A0
     */
    protected abstract void sendRLSRequest(
        EqTypeOrderUnit l_orderData, 
        Long l_submitterLoginId, 
        String l_strNotifyType) throws WEB3BaseException;
}
@
