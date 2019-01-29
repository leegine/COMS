head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引新規建入力サービスImpl(WEB3ToSuccMarginOpenMarginInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/8 譚漢江(中訊) 新規作成
Revesion History : 2007/01/20 肖志偉(中訊) 仕様変更モデルNo.224
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
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
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOpenMarginInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenInputRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引新規建入力サービスImpl)<BR>
 * （連続）信用取引新規建入力サービス実装クラス<BR>
 *   
 * @@author 譚漢江
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginInputServiceImpl 
    extends WEB3MarginOpenMarginInputServiceImpl 
    implements WEB3ToSuccMarginOpenMarginInputService 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginInputServiceImpl.class);

    /**
     * @@roseuid 436ACF790232
     */
    public WEB3ToSuccMarginOpenMarginInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引新規建入力サービス処理を実施する。<BR>
     * <BR>
     * this.get入力画面()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4328FE2B01F3
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

        if (l_request instanceof WEB3SuccMarginOpenInputRequest)
        {
            l_response = this.getOpenMarginInputScreen((WEB3SuccMarginOpenInputRequest) l_request);
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
     * (get新規建入力画面)<BR>
     * （連続）信用取引新規建入力画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引新規建入力）get新規建入力画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * （連続）信用取引新規建入力リクエストオブジェクト<BR>
     * @@return WEB3SuccMarginOpenInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 4328FF9D0270
     */
    protected WEB3SuccMarginOpenInputResponse getOpenMarginInputScreen(WEB3SuccMarginOpenInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOpenMarginInputScreen(WEB3SuccMarginOpenInputRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式親注文の注文単位(（親注文）注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
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
        // 市場コード：　@リクエストデータ.市場コード == nullの場合、"その他"をセット。以外、処理を行わない。
        if (null == l_request.marketCode)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
        }

        //1.5 validate連続注文(補助口座 : 補助口座, 銘柄タイプ : ProductTypeEnum, 先物／オプション区分 : String, 
        //  連続注文取引区分 : String, 親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrder(l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);

        //1.6 validate連続注文最大設定数(親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        if (null == l_request.marketCode)
        {
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);
        }
        
        //1.7 get新規建入力画面(リクエストデータ : 信用取引新規建注文入力リクエスト)
        WEB3SuccMarginOpenInputResponse l_response = (WEB3SuccMarginOpenInputResponse) 
            super.getOpenMarginInputScreen(l_request);
        
        //1.8 is反対売買取引(String, OrderUnit)
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
     * (get新規建可能額)<BR>
     * 新規建可能額を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 取引余力サービス.get信用新規建可能額～連続注文～<BR>
     * (補助口座, null)をコールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4337DBDC0195
     */
    protected double getMarginTradingPower(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMarginTradingPower(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //取引余力サービス.get信用新規建可能額～連続注文～(引数の補助口座, null)の戻り値を返却する。
        WEB3TPTradingPowerService l_tradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(WEB3TPTradingPowerService.class);
        double l_dblSuccMarginTradingPower =
            l_tradingPowerService.getSuccMarginTradingPower(l_subAccount, null);

        log.exiting(STR_METHOD_NAME);
        return l_dblSuccMarginTradingPower;
    }
}
@
