head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引返済入力サービス実装クラス(WEB3ToSuccMarginCloseMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16　@鄭徳懇(中訊) 新規作成
Revesion History : 2007/01/20 肖志偉(中訊) 仕様変更モデルNo.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.message.WEB3MarginCloseMarginInputRequest;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （（連続）信用取引返済入力サービスImpl)<BR>
 * （連続）信用取引返済入力サービス実装クラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3ToSuccMarginCloseMarginInputServiceImpl extends WEB3MarginCloseMarginInputServiceImpl 
    implements WEB3ToSuccMarginCloseMarginInputService 
{
   
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginInputServiceImpl.class);

    /**
     * @@roseuid 436ACF7F01F4
     */
    public WEB3ToSuccMarginCloseMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引返済入力画面表示処理を実施する。<BR>
     * <BR>
     * this.get返済入力画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 432947DE0108
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
        // this.get返済入力画面()をコールする。
        if (l_request instanceof WEB3SuccMarginCloseInputRequest)
        {
            l_response = this.getCloseMarginInputScreen((WEB3SuccMarginCloseInputRequest) l_request);
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
     * (get返済入力画面)<BR>
     * （連続）信用取引返済入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引返済入力）get返済入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引返済注文入力リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginCloseInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4329488600E9
     */
    protected WEB3SuccMarginCloseInputResponse getCloseMarginInputScreen(WEB3SuccMarginCloseInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCloseMarginInputScreen(WEB3SuccMarginCloseInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式親注文の注文単位(（親注文）注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //1.3 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //1.4 reset市場コード(市場コード : String)
        WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

        //1.5 validate連続注文(補助口座 : 補助口座, 
        //銘柄タイプ : ProductTypeEnum, 先物／オプション区分 : String, 
        //  連続注文取引区分 : String, 親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //1.6 validate連続注文最大設定数(OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 get返済入力画面(リクエストデータ : 信用取引返済注文入力リクエスト)
        WEB3SuccMarginCloseInputResponse l_response = (WEB3SuccMarginCloseInputResponse) 
            super.getCloseMarginInputScreen(l_request);
        
        //1.8 is反対売買取引(連続注文取引区分 : String, 親注文の注文単位 : OrderUnit)
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //1.9 （(*) プロパティセット）
        //「（連続）信用取引返済注文入力レスポンス」にのみ存在するプロパティをセット
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
        
        //値段条件一覧：　@"指定なし"のみをセット。
        l_response.priceCondList = new String[]{WEB3PriceConditionDef.DEFAULT};
        
        //執行条件一覧：　@"無条件"のみをセット。
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};

        //Ｗ指値用執行条件一覧：　@nullをセット。
        l_response.wlimitExecCondList = null;

        //発注条件区分一覧：　@"指定なし"のみをセット。
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get建株)<BR>
     * 建株を取得し返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@パラメータ.リクエストデータを<BR>
     * 　@（連続）信用取引返済注文入力リクエストにキャストする。<BR>
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
     * ４）　@既存残に対する返済（３）以外）の場合、<BR>
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
     * 信用取引返済注文入力リクエストオブジェクト<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 432948B100D9
     */
    protected WEB3EquityContract getContract(WEB3MarginCloseMarginInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract(WEB3MarginCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);

        WEB3EquityContract l_contract = null;
        
        //1）  パラメータ.リクエストデータを
        //    （連続）信用取引返済注文入力リクエストにキャストする。
        WEB3SuccMarginCloseInputRequest l_succRequest = (WEB3SuccMarginCloseInputRequest) l_request;
        
        //２）　@親注文の注文単位を取得する。
        // 　@  連続注文マネージャImpl.get株式親注文の注文単位()をコールする。
        // 　@  [get株式親注文の注文単位()に指定する引数]
        //　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        // 　@ 連続注文マネージャImpl.is反対売買取引()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);
        
        //３）　@反対売買(*1)の場合
        //      連続注文マネージャImpl.create建株()をコールし,戻り値を返却する。
        if (l_blnIsReversingTrade)
        {
            l_contract = l_toOrderManager.createContract(l_orderUnit);
        }
        //４）　@既存残に対する返済（３）以外）の場合、
        // 　@   super.get建株()をコールし、戻り値を返却する。
        else 
        {
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
     * 　@（連続）信用取引返済注文入力リクエストにキャストする。<BR>
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
     * ４）　@既存残に対する返済（３）以外）の場合、<BR>
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
     * 信用取引返済注文入力リクエストオブジェクト<BR>
     * @@param l_dblCurrentPrice - (時価)<BR>
     * 時価<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 432948B10127
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        WEB3MarginCloseMarginInputRequest l_request, double l_dblCurrentPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(WEB3MarginCloseMarginInputRequest, double)";
        log.entering(STR_METHOD_NAME);

        WEB3MarginContractUnit[] l_contractUnits = null;
        
        //１）　@パラメータ.リクエストデータを
        //    （連続）信用取引返済注文入力リクエストにキャストする。
        WEB3SuccMarginCloseInputRequest l_succRequest = (WEB3SuccMarginCloseInputRequest) l_request;
        
        //２）　@親注文の注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //(*1)連続注文マネージャImpl.is反対売買取引()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);

        //３）　@反対売買(*1)の場合
        if (l_blnIsReversingTrade)
        {
            //３－１）　@連続注文マネージャImpl.create建株明細()をコールする。
            WEB3MarginContractUnit l_contractUnit = l_toOrderManager.createMarginContractUnit(l_orderUnit);
            
            //３－２）　@プロパティセットしたインスタンスのみ
            //        を要素とする配列を生成し、返却する。
            l_contractUnits = new WEB3MarginContractUnit[]{l_contractUnit};
        }
        //４）既存残に対する返済（３）以外）の場合、
        // 　@ super.create建株明細一覧()をコールし、
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
     * 　@（連続）信用取引返済注文入力リクエストにキャストする。<BR>
     * <BR>
     * ２）　@親注文の注文単位を取得する。<BR>
     * 　@連続注文マネージャImpl.get株式親注文の注文単位()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[get株式親注文の注文単位()に指定する引数]<BR>
     * 　@　@（親注文）注文ID：　@１）で取得したリクエスト.連続注文共通情報の同項目<BR>
     * <BR>
     * ３）　@反対売買(*1)の場合、<BR>
     * 　@個別返済のみなのでソート不要である為、<BR>
     * 　@処理を終了する。<BR>
     * <BR>
     * ４）　@既存残に対する返済（３）以外）の場合、<BR>
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
     * @@throws WEB3BaseException
     * 信用取引返済注文入力リクエストオブジェクト<BR>
     * @@roseuid 4337C6020338
     */
    protected void sortMarginContractUnitList(
        WEB3MarginContractUnit[] l_contractUnits, WEB3MarginCloseMarginInputRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sortMarginContractUnitList(WEB3MarginContractUnit[], " +
            "WEB3MarginCloseMarginInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@パラメータ.リクエストデータを
        //     連続）信用取引返済注文入力リクエストにキャストする。
        WEB3SuccMarginCloseInputRequest l_succRequest = (WEB3SuccMarginCloseInputRequest) l_request;
     
        //２）　@親注文の注文単位を取得する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
       
        //３）(*1)連続注文マネージャImpl.is反対売買取引()
        boolean l_blnIsReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType,
            l_orderUnit);
    
        //４）　@既存残に対する返済（３）以外）の場合、
        //　@    super.sort建株明細一覧()をコールする。
        if (!l_blnIsReversingTrade)
        {
            super.sortMarginContractUnitList(l_contractUnits, l_request);
        }

        log.exiting(STR_METHOD_NAME); 
    }
}
@
