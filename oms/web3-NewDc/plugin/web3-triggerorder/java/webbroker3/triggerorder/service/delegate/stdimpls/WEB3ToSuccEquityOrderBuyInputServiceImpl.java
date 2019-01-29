head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquityOrderBuyInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式買付注文入力サービスImpl(WEB3ToSuccEquityOrderBuyInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 鄭海良(中訊) 新規作成
Revesion History : 2007/01/20 肖志偉(中訊) 仕様変更モデルNo.224
Revesion History : 2007/12/17 趙林鵬(中訊) 仕様変更モデルNo.245
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderBuyInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquityBuyInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderBuyInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）現物株式買付注文入力サービスImpl)<BR>
 * （連続）現物株式買付注文入力サービス実装クラス。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquityOrderBuyInputServiceImpl extends WEB3EquityOrderBuyInputServiceImpl 
    implements WEB3ToSuccEquityOrderBuyInputService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquityOrderBuyInputServiceImpl.class);
    
    /**
     * @@roseuid 4348EC6C0119
     */
    public WEB3ToSuccEquityOrderBuyInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）現物株式買付注文入力サービス処理を実施する。<BR>
     * <BR>
     * get入力画面()メソッドをコールする。<BR>
     * @@param l_request - ()<BR>
     * リクエストデータ。
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431C28A80160
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3SuccEquityBuyInputRequest)
        {
            l_response = this.getInputScreen((WEB3SuccEquityBuyInputRequest) l_request);
        }
        else
        {
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
     * (get入力画面)<BR>
     * （連続）現物株式買付注文入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）現物株式買付注文入力）get入力画面」参照。<BR>
     *  ======================================================== <BR>
     *  シーケンス図「（（連続）現物株式買付注文入力）get入力画面」 <BR> 
     *  1.4 反対売買取引の場合（is反対売買取引()==true）、リクエストデータ <BR> 
     *    のプロパティをチェックする <BR> 
     *    以下のいずれかに該当する場合は、例外をthrowする。 <BR> 
     *   class: WEB3BusinessLayerException <BR>
     *   tag: BUSINESS_ERROR_02250 <BR>
     *  ========================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * 
     * @@return WEB3SuccEquityBuyInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 431C28A8019E
     */
    protected WEB3SuccEquityBuyInputResponse getInputScreen(WEB3SuccEquityBuyInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getInputScreen(WEB3SuccEquityBuyInputRequest )";
        log.entering(STR_METHOD_NAME);

        WEB3SuccEquityBuyInputResponse l_response = null;
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式親注文の注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        if (l_orderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                "該当データなし");
        }
        
        //1.3 is反対売買取引(String, OrderUnit)
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
        
        //1.4 反対売買取引の場合（is反対売買取引()==true）、リクエストデータのプロパティをチェックする
        if (l_blnReversingTrade)
        {
            EqTypeProduct l_eqtypeProduct = (EqTypeProduct)l_orderUnit.getProduct();
            if (l_eqtypeProduct == null || !l_request.productCode.equals(l_eqtypeProduct.getProductCode()))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02250,
                    getClass().getName() + STR_METHOD_NAME,
                    "反対取引時の銘柄指定が、親注文と不整合です。");
            }
        }
        
        //1.5 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        if (l_request.marketCode == null)
        {
	        // reset市場コード
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
        }
        
        //1.6 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);
            
        //1.7 validate連続注文最大設定数(OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        if (l_request.marketCode == null)
        {
            // reset市場コード
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);
        }
        
        //1.8  get入力画面(リクエストデータ : 現物株式買付注文入力リクエスト)
        l_response = (WEB3SuccEquityBuyInputResponse)super.getBuyInputScreen(l_request); //WEB3BaseException
        
        //1.9 (*) プロパティセット
        //-----------------------------------------------------------------
        //●「（連続）現物株式買付注文入力レスポンス」にのみ存在するプロパティ
        //注文株数：　@
        //○反対売買の場合
        //（親注文の注文単位.注文数量）をセット。
        //○上記以外の場合
        //nullをセット。
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
        }
        else
        {
            l_response.orderQuantity = null;
        }
        
        //-----------------------------------------------------------------
        //●異なる値をセットするプロパティ（再セット）
        //
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
     * (get買付可能額)<BR>
     * 買付可能額を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 取引余力サービス.get株式買付可能額～連続注文～(引数の補助口座, null, null)を<BR>
     * コールする。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4326A9580118
     */
    protected double getEquityTradingPower(WEB3GentradeSubAccount l_subAccount) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =" getEquityTradingPower(WEB3GentradeSubAccount )";
        log.entering(STR_METHOD_NAME);

        // 取引余力サービス.get株式買付可能額～連続注文～(引数の補助口座, null, null)を
        // コールする。
        WEB3TPTradingPowerService l_trdingPowerService = 
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
        double l_dblBuyPower = l_trdingPowerService.getSuccEquityTradingPower(
            l_subAccount,
            null,
            null);

        log.exiting(STR_METHOD_NAME);
        return l_dblBuyPower;
    }

    /**
     * (isPTS口座開設)<BR>
     * 顧客.isPTS口座開設をコールし、結果を返却する <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * (未実装) <BR>
     * falseを返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@return boolean
     */
    protected boolean isPTSAccountOpen(WEB3GentradeMainAccount l_mainAccount)
    {
        return false;
    }
}
@
