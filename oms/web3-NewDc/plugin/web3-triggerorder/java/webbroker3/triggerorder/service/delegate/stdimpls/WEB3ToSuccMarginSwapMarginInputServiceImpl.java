head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引現引現渡入力サービスImpl(WEB3ToSuccMarginSwapMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/8 譚漢江(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.message.WEB3MarginSwapMarginInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引現引現渡入力サービスImpl)<BR>
 * （連続）信用取引現引現渡入力サービス実装クラス。<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginInputServiceImpl 
    extends WEB3MarginSwapMarginInputServiceImpl
    implements WEB3ToSuccMarginSwapMarginInputService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginInputServiceImpl.class);

    /**
     * @@roseuid 436ACF760157
     */
    public WEB3ToSuccMarginSwapMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引現引現渡入力画面表示処理を実施する。<BR>
     * <BR>
     * this.get現引現渡入力画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4340E2BB03AE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccMarginSwapInputRequest)
        {
            l_response = this.getSwapMarginInputScreen((WEB3SuccMarginSwapInputRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get現引現渡入力画面)<BR>
     * （連続）信用取引現引現渡入力画面表示サービスを実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引現引現渡入力サービス）get現引現渡入力画面」<BR>
     * 参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccMarginSwapInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 43424D140379
     */
    protected WEB3SuccMarginSwapInputResponse getSwapMarginInputScreen(WEB3SuccMarginSwapInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSwapMarginInputScreen(WEB3SuccMarginSwapInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式親注文の注文単位(（親注文）注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "予期しないシステムエラーが発生しました。");
        }
        
        //1.3 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4 reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //1.5 validate連続注文(補助口座 : 補助口座, 銘柄タイプ : ProductTypeEnum, 先物／オプション区分 : String, 
        //  連続注文取引区分 : String, 親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //1.6 validate連続注文最大設定数(親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 get現引現渡入力画面(リクエストデータ : 信用取引現引現渡注文入力リクエスト)
        WEB3SuccMarginSwapInputResponse l_response = (WEB3SuccMarginSwapInputResponse) 
            super.getSwapMarginInputScreen(l_request);
        
        //1.8 is反対売買取引(連続注文取引区分 : String, 親注文の注文単位 : OrderUnit)
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //1.9 （(*) プロパティセット）
        //「（連続）信用取引現引現渡注文入力レスポンス」にのみ存在するプロパティ
        //注文株数：　@
        //○連続注文マネージャImpl.is反対売買取引()==trueの場合
        //（親注文の注文単位.注文数量）をセット。
        //○連続注文マネージャImpl.is反対売買取引()==falseの場合
        //  nullをセット。
        if (l_blnIsReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        }
        else 
        {
            l_response.orderQuantity = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get建株)<BR>
     * 建株を取得し返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータを<BR>
     * 　@（連続）信用取引現引現渡注文入力リクエストにキャストする。<BR>
     * <BR>
     * ２）　@親注文の注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式親注文の注文単位()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get株式親注文の注文単位()に指定する引数]<BR>
     * 　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * <BR>
     * ３）　@反対売買(*1)の場合、<BR>
     * 　@連続注文マネージャImpl.create建株()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create建株()に指定する引数]<BR>
     * 　@　@注文単位：　@２）で取得した親注文の注文単位<BR>
     * 　@　@<BR>
     * ４）　@既存残に対する決済（３）以外）の場合、<BR>
     * 　@super.get建株()をコールし、戻り値を返却する。<BR>
     * <BR>
     * 　@[get建株()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * <BR>
     * (*1)連続注文マネージャImpl.is反対売買取引() == true。<BR>
     * 　@[is反対売買取引()に指定する引数]<BR>
     * 　@　@連続注文取引区分：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * 　@　@親注文の注文単位：　@２）で取得した親注文の注文単位<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引現引現渡注文入力リクエストオブジェクト。<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 43424DBB0146
     */
    protected WEB3EquityContract getContract(WEB3MarginSwapMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract(WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityContract l_contract = null;
        
        //１）　@パラメータ.リクエストデータを（連続）信用取引現引現渡注文入力リクエストにキャストする。
        WEB3SuccMarginSwapInputRequest l_succRequest = (WEB3SuccMarginSwapInputRequest) l_request;
        
        //２）　@親注文の注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "予期しないシステムエラーが発生しました。");
        }
        
        //(*1)連続注文マネージャImpl.is反対売買取引()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //３）　@反対売買(*1)の場合
        if (l_blnIsReversingTrade)
        {
            //連続注文マネージャImpl.create建株()をコールし,戻り値を返却する。
            l_contract = l_toOrderManager.createContract(l_orderUnit);
        }
        //４）　@既存残に対する決済（３）以外）の場合
        else 
        {
            //super.get建株()をコールし、戻り値を返却する。
            l_contract = super.getContract(l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
    
    /**
     * (create建株明細一覧)<BR>
     * リクエストデータより信用取引建株明細の一覧を<BR>
     * 作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータを<BR>
     * 　@（連続）信用取引現引現渡注文入力リクエストにキャストする。<BR>
     * <BR>
     * ２）　@親注文の注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式親注文の注文単位()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get株式親注文の注文単位()に指定する引数]<BR>
     * 　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * <BR>
     * ３）　@反対売買(*1)の場合、<BR>
     * 　@以下の手順にて信用取引建株明細を作成する。<BR>
     * 　@３－１）　@連続注文マネージャImpl.create建株明細()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[create建株明細()に指定する引数]<BR>
     * 　@　@　@注文単位：　@２）で取得した親注文の注文単位<BR>
     * <BR>
     * 　@３－２）　@プロパティセットしたインスタンスのみを要素とする<BR>
     * 　@　@配列を生成し、返却する。<BR>
     * <BR>
     * ４）　@既存残に対する現引現渡（３）以外）の場合、<BR>
     * 　@super.create建株明細一覧()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create建株明細一覧()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * <BR>
     * (*1)連続注文マネージャImpl.is反対売買取引() == true。<BR>
     * 　@[is反対売買取引()に指定する引数]<BR>
     * 　@　@連続注文取引区分：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * 　@　@親注文の注文単位：　@２）で取得した親注文の注文単位<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引現引現渡注文入力リクエストオブジェクト<BR>
     * @@param l_dblCurrentPrice - (時価)<BR>
     * 時価<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 43424DC50202
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        WEB3MarginSwapMarginInputRequest l_request, 
        double l_dblCurrentPrice) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginContractUnit[] l_contractUnits = null;
        
        //１）　@パラメータ.リクエストデータを（連続）信用取引現引現渡注文入力リクエストにキャストする。
        WEB3SuccMarginSwapInputRequest l_succRequest = (WEB3SuccMarginSwapInputRequest) l_request;

        //２）　@親注文の注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "予期しないシステムエラーが発生しました。");
        }

        //(*1)連続注文マネージャImpl.is反対売買取引()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);

        //３）　@反対売買(*1)の場合
        if (l_blnIsReversingTrade)
        {
            //３－１）　@連続注文マネージャImpl.create建株明細()をコールする。
            WEB3MarginContractUnit l_contractUnit = l_toOrderManager.createMarginContractUnit(l_orderUnit);
            
            //３－２）　@プロパティセットしたインスタンスのみを要素とする配列を生成し、返却する。
            l_contractUnits = new WEB3MarginContractUnit[]{l_contractUnit};
        }
        //４）　@既存残に対する現引現渡（３）以外）の場合
        else 
        {
            l_contractUnits = super.createMarginContractUnitList(l_request, l_dblCurrentPrice);
        }

        log.exiting(STR_METHOD_NAME);
        return l_contractUnits;
    }
    
    /**
     * (sort建株明細一覧)<BR>
     * 引数の建株明細一覧をソートする。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータを<BR>
     * 　@（連続）信用取引現引現渡注文入力リクエストにキャストする。<BR>
     * <BR>
     * ２）　@親注文の注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式親注文の注文単位()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get株式親注文の注文単位()に指定する引数]<BR>
     * 　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * <BR>
     * ３）　@反対売買(*1)の場合、<BR>
     * 　@個別決済のみなのでソート不要である為、<BR>
     * 　@処理を終了する。<BR>
     * <BR>
     * ４）　@既存残に対する現引現渡（３）以外）の場合、<BR>
     * 　@super.sort建株明細一覧()をコールする。<BR>
     * <BR>
     * 　@[sort建株明細一覧()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * <BR>
     * (*1)連続注文マネージャImpl.is反対売買取引() == true。<BR>
     * 　@[is反対売買取引()に指定する引数]<BR>
     * 　@　@連続注文取引区分：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * 　@　@親注文の注文単位：　@２）で取得した親注文の注文単位<BR>
     * @@param l_contractUnits - (建株明細一覧)<BR>
     * 信用取引建株明細の配列<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 信用取引現引現渡注文入力リクエストオブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43424DC50240
     */
    protected void sortMarginContractUnitList(
        WEB3MarginContractUnit[] l_contractUnits, 
        WEB3MarginSwapMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sortMarginContractUnitList(WEB3MarginContractUnit[], " +
            "WEB3MarginSwapMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@パラメータ.リクエストデータを（連続）信用取引現引現渡注文入力リクエストにキャストする。
        WEB3SuccMarginSwapInputRequest l_succRequest = (WEB3SuccMarginSwapInputRequest) l_request;
     
        //２）　@親注文の注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.debug("予期しないシステムエラーが発生しました。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "予期しないシステムエラーが発生しました。");
        }

        //(*1)連続注文マネージャImpl.is反対売買取引()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);

        //４）　@既存残に対する現引現渡（反対売買false）以外）の場合
        if (!l_blnIsReversingTrade)
        {
            super.sortMarginContractUnitList(l_contractUnits, l_request);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get現引可能額)<BR>
     * 現引可能額を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@引数の建株.isLong()==false（not現引）の場合は、nullを返却する。<BR>
     * 　@　@　@以外、以下の処理を行う。<BR>
     * <BR>
     * ２）　@取引余力サービス.get信用現引可能額～連続注文～(引数の補助口座, null)の<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_contract - (建株)<BR>
     * 建株オブジェクト。<BR>
     * @@return Double
     * @@throws WEB3BaseException
     * @@roseuid 43435A40006F
     */
    protected Double getActualReceiptTradingPower(WEB3GentradeSubAccount l_subAccount, WEB3EquityContract l_contract)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getActualReceiptTradingPower(WEB3GentradeSubAccount, WEB3EquityContract)";
        log.entering(STR_METHOD_NAME);
        
        //引数の建株.isLong()==false（not現引）の場合は、nullを返却する。
        if (!l_contract.isLong())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //取引余力サービス.get信用現引可能額～連続注文～(引数の補助口座, null)の戻り値を返却する。
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblSuccActualReceiptTradingPower =
            l_tradingPowerService.getSuccActualReceiptTradingPower(l_subAccount, null);

        log.exiting(STR_METHOD_NAME);
        return new Double(l_dblSuccActualReceiptTradingPower);
    }
}
@
