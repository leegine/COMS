head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccEquitySellOrderInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式売付入力サービスImpl(WEB3ToSuccEquitySellOrderInputServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 鄭海良(中訊) 新規作成
Revesion History : 2007/01/20 肖志偉(中訊) 仕様変更モデルNo.224
Revesion History : 2007/12/17 趙林鵬(中訊) 仕様変更モデルNo.246
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.define.WEB3ExecutionConditionDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityBizLogicProvider;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.message.WEB3EquitySellInputRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquitySellOrderInputServiceImpl;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradePriceCondDef;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccEquitySellInputRequest;
import webbroker3.triggerorder.message.WEB3SuccEquitySellInputResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquitySellOrderInputService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）現物株式売付入力サービスImpl)<BR>
 * （連続）現物株式売付入力サービス実装クラス。
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3ToSuccEquitySellOrderInputServiceImpl extends WEB3EquitySellOrderInputServiceImpl 
    implements WEB3ToSuccEquitySellOrderInputService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccEquitySellOrderInputServiceImpl.class);
    
    /**
     * @@roseuid 4348EC75038A
     */
    public WEB3ToSuccEquitySellOrderInputServiceImpl() 
    {
     
    }
    
    /**
     * （連続）現物株式売付注文入力サービス処理を実施する。<BR>
     * <BR>
     * get入力画面()メソッドをコールする。<BR>
     * @@param l_request - リクエストデータ。
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43265B4E00AA
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
        
        if (l_request instanceof WEB3SuccEquitySellInputRequest)
        {
            l_response = this.getInputScreen((WEB3SuccEquitySellInputRequest) l_request);
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
     * （連続）現物株式売付注文入力画面表示処理を実施する。<BR>
     * <BR>
     * シーケンス図「（（連続）現物株式売付注文入力）get入力画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。
     * 
     * @@return WEB3SuccEquitySellInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 43265B4E00CA
     */
    protected WEB3SuccEquitySellInputResponse getInputScreen(WEB3SuccEquitySellInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getInputScreen(WEB3SuccEquitySellInputRequest )";
        log.entering(STR_METHOD_NAME);

        WEB3SuccEquitySellInputResponse l_response = null;        
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

        //1.3 get補助口座( )
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        if (l_request.marketCode == null)
        {
            // reset市場コード
            WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);
        }
        
        //1.4 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_orderUnit);

        //1.5 validate連続注文最大設定数(OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        
        if (l_request.marketCode == null)
        {
            // reset市場コード
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_request.marketCode);
        }
        
        //1.6  get入力画面(リクエストデータ : 現物株式買付注文入力リクエスト)
        l_response = (WEB3SuccEquitySellInputResponse)super.getSellInputScreen(l_request); //WEB3BaseException

        //1.7 is反対売買取引(String, OrderUnit)
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
            
        //1.8 (*) プロパティセット
        //●異なる値をセットするプロパティ（再セット）
        //値段条件一覧：　@"指定なし"のみをセット。
        l_response.priceCondList = new String[]{WEB3GentradePriceCondDef.DEFAULT};
        
        //執行条件一覧：　@"無条件"のみをセット。
        l_response.execCondList = new String[]{WEB3ExecutionConditionDef.NO_CONDITION};

        //Ｗ指値用執行条件一覧：　@nullをセット。
        l_response.wlimitExecCondList = null;
        
        //口座区分：　@
        // ○反対売買の場合
        //  親注文の注文単位.注文種別=="現物買注文"の場合は、
        //  （親注文の注文単位.税区分）に該当する口座区分のみをセット。
        //  親注文の注文単位.注文種別=="現引注文"の場合は、
        //  （親注文の注文単位.税区分（現引現渡））に該当する口座区分のみをセット。
        //  ※税区分=="一般"の場合は、"一般"。以外、"特定"。
        // ○上記以外の場合
        //  （レスポンス.口座区分）をそのままセット。
        if (l_blnReversingTrade)
        {
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()))
            {
                if (TaxTypeEnum.NORMAL.equals(l_orderUnit.getTaxType()))
                {
                    l_response.taxType = WEB3AccountDivDef.NORMAL;
                }
                else
                {
                    l_response.taxType = WEB3AccountDivDef.SPECIAL;
                }
            }
            else if (OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                EqtypeOrderUnitRow l_row = (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                if (TaxTypeEnum.NORMAL.equals(l_row.getSwapTaxType()))
                {
                    l_response.taxType = WEB3AccountDivDef.NORMAL;
                }
                else
                {
                    l_response.taxType = WEB3AccountDivDef.SPECIAL;
                }
            }
        }
        
        //発注条件区分一覧：　@"指定なし"のみをセット。
        l_response.orderCondTypeList = new String[]{WEB3OrderingConditionDef.DEFAULT};

        //注文株数：　@
        // ○反対売買の場合
        //  （親注文の注文単位.注文数量）をセット。
        // ○上記以外の場合
        //  （レスポンス.注文株数）をそのままセット。
        //概算簿価単価：
        // ○反対売買の場合
        //  nullをセット。
        // ○上記以外の場合
        //  （レスポンス.概算簿価単価）をそのままセット。
        if (l_blnReversingTrade)
        {
            l_response.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnit.getQuantity());
            l_response.estimatedBookPrice = null;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get保有資産)<BR>
     * 保有資産オブジェクトを取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対取引(*1)の場合<BR>
     * 　@　@連続注文マネージャImpl.create保有資産(親注文の注文単位(*2))にdelegateする。<BR>
     * <BR>
     * ２）　@反対取引でない場合<BR>
     * 　@　@株式ポジションマネージャ.getAsset(リクエスト.ID（＝資産ID）)にdelegateする。<BR>
     * <BR>
     * (*1)反対取引<BR>
     * 　@連続注文マネージャImpl.is反対売買取引(<BR>
     * 　@リクエスト.連続注文共通情報.連続注文取引区分, 親注文の注文単位(*2))<BR>
     * 　@==trueの場合は、反対取引。<BR>
     * 　@falseの場合は、反対取引でない。<BR>
     * <BR>
     * (*2)親注文の注文単位<BR>
     * 　@連続注文マネージャImpl.get株式親注文の注文単位<BR>
     * (リクエスト.連続注文共通情報.（親注文）注文ID)で<BR>
     * 　@取得。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。
     * @@return Asset
     * @@throws WEB3BaseException
     * @@roseuid 43295F390264
     */
    protected Asset getAsset(WEB3EquitySellInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getAsset(WEB3EquitySellInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccEquitySellInputRequest l_succRequest = 
            (WEB3SuccEquitySellInputRequest)l_request;
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = 
            Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType, 
            l_orderUnit);
        //１）　@反対取引(*1)の場合
        //　@　@連続注文マネージャImpl.create保有資産(親注文の注文単位(*2))にdelegateする。
        Asset l_asset = null;
        if (l_blnReversingTrade)
        {
            l_asset = l_toOrderManager.createAsset(l_orderUnit);
        }
        else
        {
            //２）　@反対取引でない場合
            //　@　@株式ポジションマネージャ.getAsset(リクエスト.ID（＝資産ID）)にdelegateする。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            try
            {
                l_asset = l_positionManager.getAsset(Long.parseLong(l_succRequest.id));
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_asset;
    }
    
    /**
     * (get概算簿価単価)<BR>
     * 概算簿価単価を取得し返す。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@親注文の反対売買取引かどうかを判定する。<BR>
     * <BR>
     * 　@　@連続注文マネージャImpl.is反対売買取引()==true（反対売買）の場合は、<BR>
     * 　@　@nullを返却する。<BR>
     * <BR>
     * 　@　@-------------------------------------------------------<BR>
     * 　@　@＜is反対売買取引()：引数設定仕様＞<BR>
     * <BR>
     * 　@　@連続注文取引区分：　@引数のリクエスト.連続注文共通情報.連続注文取引区分<BR>
     * 　@　@親注文の注文単位：　@引数のリクエスト.連続注文共通情報.（親注文）注文IDに<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@該当する親注文の注文単位オブジェクト<BR>
     * 　@　@　@　@※連続注文マネージャImpl.get株式親注文の注文単位(（親注文）注文ID)<BR>
     * で取得。<BR>
     * 　@　@-------------------------------------------------------<BR>
     * <BR>
     * ２）　@反対売買でない場合、保有資産オブジェクトを取得する。<BR>
     * <BR>
     * 　@株式ポジションマネージャ.getAsset(引数のリクエスト.ID)をコールする。<BR>
     * <BR>
     * ３）　@株式計算サービス.calc概算簿価単価(保有資産.銘柄ID, get補助口座(), <BR>
     * 保有資産.税区分)をコールし、<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 433A2FFF028D
     */
    protected String getEstimatedBookPrice(WEB3EquitySellInputRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =" getEstimatedBookPrice(WEB3EquitySellInputRequest )";
        log.entering(STR_METHOD_NAME);

        WEB3SuccEquitySellInputRequest l_succRequest = (WEB3SuccEquitySellInputRequest)l_request;

        //１）　@親注文の反対売買取引かどうかを判定する。
        //　@　@連続注文マネージャImpl.is反対売買取引()==true（反対売買）の場合は、
        //　@　@nullを返却する。
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = 
            Long.parseLong(l_succRequest.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = 
            l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        boolean l_blnReversingTrade = l_toOrderManager.isReversingTrade(
            l_succRequest.succCommonInfo.succTradingType, 
            l_orderUnit);
        Asset l_asset = null;
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        if (l_blnReversingTrade)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            //２）　@反対売買でない場合、保有資産オブジェクトを取得する。
            //　@株式ポジションマネージャ.getAsset(引数のリクエスト.ID)をコールする。
            WEB3EquityPositionManager l_positionManager =
                (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
            try
            {
                l_asset = l_positionManager.getAsset(Long.parseLong(l_succRequest.id));
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        //３）　@株式計算サービス.calc概算簿価単価(保有資産.銘柄ID, get補助口座(), 
        //保有資産.税区分)をコールし、
        //　@　@　@戻り値を返却する。
        WEB3EquityBizLogicProvider l_bizLogicProvider =
            (WEB3EquityBizLogicProvider)l_tradingModule.getBizLogicProvider();
        double l_dblEstimatedBookPrice =  l_bizLogicProvider.calcEstimatedBookPrice(
            l_asset.getProduct().getProductId(),
            this.getSubAccount(),
            l_asset.getTaxType());
        String l_strEstimatedBookPrice =
            WEB3StringTypeUtility.formatNumber(l_dblEstimatedBookPrice);
            
        log.exiting(STR_METHOD_NAME);
        return l_strEstimatedBookPrice;
    }

    /**
     * (isPTS口座開設)<BR>
     * 顧客.isPTS口座開設をコールし、結果を返却する <BR>
     * （継承元クラスの同名メソッドのオーバーライド） <BR>
     * <BR>
     * (未実装) <BR>
     * falseを返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isPTSAccountOpen(
        WEB3GentradeMainAccount l_mainAccount)throws WEB3BaseException
    {
        return false;
    }
}
@
