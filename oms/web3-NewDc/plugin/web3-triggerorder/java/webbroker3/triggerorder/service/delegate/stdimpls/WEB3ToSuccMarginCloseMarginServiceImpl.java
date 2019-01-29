head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.53.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引返済サービスImpl(WEB3ToSuccMarginCloseMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/18 呉　@鈞(中訊) 新規作成
Revesion History : 2007/01/11 齊  珂(中訊) 仕様変更モデル216
Revesion History : 2007/01/17 齊  珂(中訊) 仕様変更モデル222
Revesion History : 2007/08/08 武波(中訊) 仕様変更モデル1192
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginCloseMarginServiceImpl;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPAttentionObjection;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （（連続）信用取引返済サービスImpl)<BR>
 * （連続）信用取引返済サービス実装クラス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginCloseMarginServiceImpl extends 
    WEB3MarginCloseMarginServiceImpl implements WEB3ToSuccMarginCloseMarginService 
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginServiceImpl.class);
    
    /**
     * @@roseuid 436ACF7E01D4
     */
    public WEB3ToSuccMarginCloseMarginServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引返済サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * 以下のメソッドを呼び分ける。<BR>
     * <BR>
     * [（連続）信用取引返済注文確認リクエストの場合]<BR>
     * 　@this.validate注文()をコールする。<BR>
     * [（連続）信用取引返済注文完了リクエストの場合]<BR>
     * 　@this.submit注文()をコールする。<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4332445D0170
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
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginCloseConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginCloseCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * （連続）信用取引返済発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引返済サービス）validate注文」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccMarginCloseConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 433245F10096
     */
    protected WEB3SuccMarginCloseConfirmResponse validateOrder(
        WEB3SuccMarginCloseConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginCloseConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式親注文の注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_request.succCommonInfo.parentOrderId));
        
        //1.3 get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.4 reset市場コード(市場コード : String)
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

        long l_lngMarketId = 0L;
        if (l_toRequestAdaptor.isReversingOrder())
        {
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdaptor.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "テーブルに該当するデータがありません。");
        }
        
        //1.5 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
        
        //1.6 validate連続注文最大設定数(注文単位)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 validate注文
        WEB3SuccMarginCloseConfirmResponse l_response = (
            WEB3SuccMarginCloseConfirmResponse) super.validateOrder(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * （連続）信用取引返済注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引返済サービス）submit注文」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * @@return WEB3SuccMarginCloseCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 433245F10180
     */
    protected WEB3SuccMarginCloseCompleteResponse submitOrder(
        WEB3SuccMarginCloseCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginCloseCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 get株式親注文の注文単位(long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        EqTypeOrderUnit l_orderUnit = l_toOrderManager.getEqtypeParentOrderUnit(
            Long.parseLong(l_request.succCommonInfo.parentOrderId));
        
        //1.3 get補助口座
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_orderUnit.getDataSourceObject();
        
        //1.4 reset市場コード(市場コード : String)
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        long l_lngMarketId = 0L;
        if (l_toRequestAdaptor.isReversingOrder())
        {
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdaptor.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "テーブルに該当するデータがありません。");
        }
        
        //1.5 validate連続注文(補助口座, ProductTypeEnum, String, String, OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount, 
            ProductTypeEnum.EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT, 
            l_request.succCommonInfo.succTradingType, 
            l_orderUnit);
        
        //1.6 validate連続注文最大設定数(注文単位)
        this.validateSuccOrderMaxQuantity(l_orderUnit);
        
        //1.7 submit注文
        WEB3SuccMarginCloseCompleteResponse l_response = (
            WEB3SuccMarginCloseCompleteResponse) super.submitOrder(l_request);
        
        //1.8 notify予約注文登録(long)
        this.notifyRsvOrderRegister(Long.parseLong(l_request.orderId));
 
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * （連続）信用取引返済リクエストアダプタ.create<BR>
     * (引数のリクエスト)をコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3MarginCloseMarginRequestAdapter
     * @@roseuid 433246330067
     */
    protected WEB3MarginCloseMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginRequestAdapter l_marginRequestAdapter = 
            WEB3ToSuccMarginCloseMarginRequestAdapter.create(l_request);

        log.exiting(STR_METHOD_NAME);
        return l_marginRequestAdapter;
    }
    
    /**
     * (create決済建株エントリ)<BR>
     * 決済建株エントリを作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@連続注文マネージャImpl.create決済建株エントリ()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create決済建株エントリ()に指定する引数]<BR>
     * 　@　@決済建株明細一覧：　@生成した決済建株明細一覧(*1)<BR>
     * <BR>
     * 　@(*1)以下のプロパティをセットした信用取引決済建株明細インスタンス<BR>
     * 　@　@のみを要素とする配列<BR>
     * <BR>
     * 　@　@注文株数 として、以下の値を使用する。<BR>
     * 　@　@※求めた注文株数 > リクエストアダプタ.親注文の注文単位.注文数量の場合、<BR>
     *   「注文株数が親注文の注文数量を超過」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag: BUSINESS_ERROR_02290<BR>
     * 　@　@------------------------------------------------------<BR>
     * 　@　@　@リクエストアダプタ.リクエストデータ.決済順序区分≠"ランダム"の場合は、<BR>
     * 　@　@　@　@　@リクエストアダプタ.リクエストデータ.注文株数 を使用。<BR>
     * 　@　@　@リクエストアダプタ.リクエストデータ.決済順序区分=="ランダム"の場合は、<BR>
     * 　@　@　@　@　@リクエストアダプタ.リクエストデータ.決済建株一覧の<BR>
     * 　@　@　@　@　@全要素の注文株数のSUM値を使用。<BR>
     * 　@　@--------------------------------------------------------<BR>
     * <BR>
     * ２）　@１）以外の場合、<BR>
     * 　@super.create決済建株エントリ()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[super.create決済建株エントリ()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_closeMarginContractUnits - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエストデータ）<BR>
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 433246330113
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createClosingContractEntry(" +
            "WEB3MarginCloseMarginContractUnit[], " +
            "WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
     
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries = null;
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        //１）　@反対売買の場合
        //　@（パラメータ.リクエストアダプタ.is反対売買() == true）
        if (l_toRequestAdapter.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            //(*1)以下のプロパティをセットした信用取引決済建株明細インスタンス
            //　@のみを要素とする配列   
            WEB3MarginCloseMarginContractUnit l_contractUnit = new WEB3MarginCloseMarginContractUnit();
            l_contractUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(getOrderQuantityCnt(l_toRequestAdapter));
            
            //　@連続注文マネージャImpl.create決済建株エントリ()をコールし、
            //　@戻り値を返却する。
            l_settleContractOrderEntries = l_toOrderManager.createClosingContractEntry(
                new WEB3MarginCloseMarginContractUnit[]{l_contractUnit});
        }
        //２）　@１）以外の場合、
        //　@super.create決済建株エントリ()をコールし、
        //　@戻り値を返却する。
        else
        {
            l_settleContractOrderEntries =
                super.createClosingContractEntry(l_closeMarginContractUnits, l_requestAdaptor);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }
    
    /**
     * (get概算決済損益代金)<BR>
     * 概算決済損益代金を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合、<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@以下の手順にて信用取引建株明細を作成する。<BR>
     * 　@１－１）　@連続注文マネージャImpl.create建株()を<BR>
     * 　@　@コールし、建株を作成する。<BR>
     * <BR>
     * 　@　@[create建株()に指定する引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.リクエストアダプタ.親注文の注文単位<BR>
     * <BR>
     * 　@１－２）　@拡張株式注文マネージャ.calc概算決済損益代金()をコールし、<BR>
     * 　@　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@　@[calc概算決済損益代金()にセットするパラメータ]<BR>
     * 　@　@　@手数料：　@パラメータの同項目<BR>
     * 　@　@　@指値：　@パラメータの同項目<BR>
     * 　@　@　@補助口座：　@パラメータの同項目<BR>
     * 　@　@　@取引銘柄：　@パラメータの同項目<BR>
     * 　@　@　@決済建株エントリ： 　@パラメータの同項目<BR>
     * 　@　@　@数量：　@パラメータの同項目<BR>
     * 　@　@　@注文単位：　@パラメータの同項目<BR>
     * 　@　@　@今回約定数量：　@パラメータの同項目<BR>
     * 　@　@　@今回約定単価：　@パラメータの同項目<BR>
     * 　@　@　@isSkip金額チェック：　@パラメータの同項目<BR>
     * 　@　@　@建株：　@反対売買の場合、１－１）の戻り値。以外、null。<BR>
     * <BR>
     * ２）　@既存残に対する返済（１）以外）の場合、<BR>
     * 　@super.get概算決済損益代金()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get概算決済損益代金()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_genCommission - (手数料)<BR>
     * 手数料オブジェクト。<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値。<BR>
     * 成行の場合は0をセット。<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_equityTradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。<BR>
     * @@param l_settleContractOrderEntries - (決済建株エントリ)<BR>
     * 決済建株エントリの配列<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 訂正元／約定対象／約定取消対象注文の注文単位オブジェクト<BR>
     * （新規の注文登録時はnullをセット）<BR>
     * @@param l_dblNowExecQuantity - (今回約定数量)<BR>
     * 今回約定数量<BR>
     * （約定／約定取消の場合に編集）<BR>
     * 
     * @@param l_dblNowExecPrice - (今回約定単価)<BR>
     * 今回約定単価<BR>
     * （約定／約定取消の場合に編集）<BR>
     * @@param l_blnIsSkipAmountRange - (isSkip金額チェック)<BR>
     * 計算結果の代金について、妥当性チェックをスキップするかのフラグ。<BR>
     * <BR>
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * チェックを行う場合はfalse、チェックを行わない（スキップする）<BR>
     * 場合はtrueを指定する。<BR>
     * 
     * @@return WEB3EquityRealizedProfitAndLossPrice
     * @@throws WEB3BaseException
     * @@roseuid 43422354027B
     */
    protected WEB3EquityRealizedProfitAndLossPrice getEstimatedRealizedProfitAndLossAmount(
        WEB3GentradeCommission l_genCommission, 
        double l_dblLimitPrice, 
        SubAccount l_subAccount, 
        WEB3EquityTradedProduct l_equityTradedProduct, 
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries, 
        double l_dblQuantity, 
        EqTypeOrderUnit l_orderUnit, 
        double l_dblNowExecQuantity, 
        double l_dblNowExecPrice, 
        boolean l_blnIsSkipAmountRange,
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getEstimatedRealizedProfitAndLossAmount(" +
            " WEB3GentradeCommission, " +
            " double," +
            " SubAccount," +
            " WEB3EquityTradedProduct," +
            " EqTypeSettleContractOrderEntry," +
            " double," +
            " EqTypeOrderUnit," +
            " double," +
            " double," +
            " boolean," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossPrice = null;
        //１）　@反対売買の場合
        if (l_toRequestAdaptor.isReversingOrder())
        {
            //１－１）　@連続注文マネージャImpl.create建株()をコールし、建株を作成する。
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            WEB3EquityContract l_equityContract = l_toOrderManager.createContract(l_toRequestAdaptor.parentOrderUnit);
            
            //１－２）　@拡張株式注文マネージャ.calc概算決済損益代金()をコールし、戻り値を返却する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
            l_profitAndLossPrice = l_orderManager.calcEstimatedRealizedProfitAndLossAmount(
                l_genCommission,
                l_dblLimitPrice,
                (WEB3GentradeSubAccount) l_subAccount,
                l_equityTradedProduct,
                l_settleContractOrderEntries,
                l_dblQuantity,
                l_orderUnit,
                l_dblNowExecQuantity,
                l_dblNowExecPrice,
                l_blnIsSkipAmountRange,
                l_equityContract); 
        }
        else 
        {
            //２）　@既存残に対する返済（１）以外）の場合、 super.get概算決済損益代金()をコールし、戻り値を返却する。
            l_profitAndLossPrice = super.getEstimatedRealizedProfitAndLossAmount(
                l_genCommission, 
                l_dblLimitPrice, 
                l_subAccount, 
                l_equityTradedProduct, 
                l_settleContractOrderEntries, 
                l_dblQuantity, 
                l_orderUnit, 
                l_dblNowExecQuantity, 
                l_dblNowExecPrice, 
                l_blnIsSkipAmountRange,
                l_requestAdaptor);   
        }
        log.exiting(STR_METHOD_NAME);
        return l_profitAndLossPrice;
    }
    
    /**
     * (create建株明細一覧)<BR>
     * 決済建株エントリより信用取引建株明細の一覧を<BR>
     * 作成する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合、<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@以下の手順にて信用取引建株明細を作成する。<BR>
     * 　@１－１）　@連続注文マネージャImpl.create建株明細()を<BR>
     * 　@　@コールする。<BR>
     * <BR>
     * 　@　@[create建株明細()に指定する引数]<BR>
     * 　@　@　@注文単位：　@パラメータ.リクエストアダプタ.親注文の注文単位<BR>
     * <BR>
     * 　@１－２）　@プロパティセットしたインスタンスのみを要素とする<BR>
     * 　@　@配列を生成し、返却する。<BR>
     * <BR>
     * ２）　@既存残に対する返済（１）以外）の場合、<BR>
     * 　@super.create建株明細一覧()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create建株明細一覧()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_settleContractOrderEntries - (決済建株エントリ)<BR>
     * 決済建株エントリの配列<BR>
     * @@param l_dblUnitPrice - (計算単価)<BR>
     * 計算単価<BR>
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4332463301FD
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries, 
        double l_dblUnitPrice, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " createMarginContractUnitList(" +
            " EqTypeSettleContractOrderEntry[]," +
            " double," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        //１）　@反対売買の場合
        if (l_toRequestAdaptor.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            //１－１）　@連続注文マネージャImpl.create建株明細()を　@コールする
            WEB3MarginContractUnit l_marginContractUnit = 
                l_toOrderManager.createMarginContractUnit(l_toRequestAdaptor.parentOrderUnit);
            
            //１－２）　@プロパティセットしたインスタンスのみを要素とする配列を生成し、返却する
            l_marginContractUnits = new WEB3MarginContractUnit[] {l_marginContractUnit};
        }
        else
        {
            // ２） 既存残に対する返済（１）以外）の場合、super.create建株明細一覧()をコールし、戻り値を返却する
            l_marginContractUnits = super.createMarginContractUnitList(
                l_settleContractOrderEntries, l_dblUnitPrice, l_requestAdaptor);
        }
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
    
    /**
     * (validate返済注文)<BR>
     * 信用取引返済注文発注審査を行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@拡張株式注文マネージャ.validate返済注文()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[validate返済注文()に指定する引数]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@信用返済注文内容：　@パラメータ.信用返済注文内容<BR>
     * 　@　@建株：　@パラメータ.リクエストアダプタ.get建株()<BR>
     * <BR>
     * ２）　@１）以外の場合<BR>
     * 　@super.validate返済注文()をコールする。<BR>
     * <BR>
     * 　@[validate返済注文()に指定する引数]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@信用返済注文内容：　@パラメータ.信用返済注文内容<BR>
     * 　@　@リクエストアダプタ：　@パラメータ.リクエストアダプタ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_orderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト<BR>
     * @@param l_requestAdaptor - (リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * @@return EqTypeNewOrderValidationResult
     * @@throws WEB3BaseException
     * @@roseuid 433246330316
     */
    protected EqTypeNewOrderValidationResult validateSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        EqTypeSettleContractOrderSpec l_orderSpec, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateSettleContractOrder(" +
            " WEB3GentradeSubAccount," +
            " EqTypeSettleContractOrderSpec," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdaptor = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        //１）　@反対売買の場合
        if (l_toRequestAdaptor.isReversingOrder())
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
            EqTypeNewOrderValidationResult l_result =
                l_orderManager.validateSettleContractOrder(l_subAccount, l_orderSpec, l_toRequestAdaptor.getContract());

            log.exiting(STR_METHOD_NAME);
            return l_result;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return super.validateSettleContractOrder(l_subAccount, l_orderSpec, l_toRequestAdaptor);
        }
    }
    
    /**
     * (submit返済注文)<BR>
     * 予約注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 連続注文マネージャImpl.submit信用返済新規予約注文()をコールする。<BR>
     * <BR>
     * [submit信用返済新規予約注文()に指定する引数]<BR>
     * 補助口座：　@引数の補助口座<BR>
     * 注文内容：　@引数の信用返済注文内容<BR>
     * 注文ID：　@引数の注文ID<BR>
     * 取引パスワード：　@引数のパスワード<BR>
     * 連続注文取引区分：　@引数の返済リクエストアダプタ.リクエスト.<BR>
     * 連続注文共通情報.連続注文取引区分<BR>
     * 単価調整値：　@引数の返済リクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()<BR>
     * 　@　@※引数の返済リクエストアダプタ.リクエスト.単価調整値情報==nullの場合は、nullをセット<BR>
     * 親注文の注文単位：　@引数の返済リクエストアダプタ.親注文の注文単位<BR>
     * 計算結果：　@引数の計算結果<BR>
     * 建株：　@引数の返済リクエストアダプタ.get建株()<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (信用返済注文内容)<BR>
     * 信用返済注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_commission - (手数料)<BR>
     * 手数料オブジェクト。<BR>
     * @@param l_profitAndLossCalcResult - (計算結果)<BR>
     * 概算決済損益代金計算結果オブジェクト。<BR>
     * @@param l_requestAdaptor - (返済リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433246340047
     */
    protected void submitSettleContractOrder(
        WEB3GentradeSubAccount l_subAccount, 
        WEB3MarginSettleContractOrderSpec l_orderSpec, 
        long l_lngOrderId, 
        String l_strTradingPassword, 
        WEB3GentradeCommission l_commission, 
        WEB3EquityRealizedProfitAndLossPrice l_profitAndLossCalcResult, 
        WEB3MarginCloseMarginRequestAdapter l_requestAdaptor) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitSettleContractOrder(" +
            " WEB3GentradeSubAccount," +
            " WEB3MarginSettleContractOrderSpec," +
            " long," +
            " String," +
            " WEB3GentradeCommission," +
            " WEB3EquityRealizedProfitAndLossPrice," +
            " WEB3MarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3ToSuccMarginCloseMarginRequestAdapter l_closeMarginRequestAdapter = (
            WEB3ToSuccMarginCloseMarginRequestAdapter) l_requestAdaptor;
        
        WEB3GenRequest l_request = l_closeMarginRequestAdapter.request;
        String l_strSuccTradingType = null;
        Double l_priceAdjustValue = null;
       
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) l_request;
            l_strSuccTradingType = l_confirmRequest.succCommonInfo.succTradingType;
            
            if (l_confirmRequest.priceAdjustmentValueInfo != null)
            {
                //引数の返済リクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()
                l_priceAdjustValue = new Double(l_confirmRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue());
            }
        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) l_request;
            l_strSuccTradingType = l_completeRequest.succCommonInfo.succTradingType;
            
            if (l_completeRequest.priceAdjustmentValueInfo != null)
            {
                //引数の返済リクエストアダプタ.リクエスト.単価調整値情報.get単価調整値()
                l_priceAdjustValue = new Double(l_completeRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue());
            }
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        //連続注文マネージャImpl.submit信用返済新規予約注文()をコールする
        l_toOrderManager.submitEqtypeCloseContractNewOrder(
            l_subAccount, 
            l_orderSpec, 
            l_lngOrderId, 
            l_strTradingPassword, 
            l_strSuccTradingType, 
            l_priceAdjustValue,
            l_closeMarginRequestAdapter.parentOrderUnit,
            l_profitAndLossCalcResult,
            l_closeMarginRequestAdapter.getContract());

        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (validate連続注文最大設定数)<BR>
     * 連続注文の最大設定数を超過してしまわないかどうかをチェックする。<BR>
     * <BR>
     * 連続注文マネージャ.validate連続注文最大設定数<BR>
     * (引数の親注文の注文単位)にdelegateする。<BR>
     * @@param l_parentOrderUnit - (親注文の注文単位)<BR>
     * 親注文の注文単位。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 433D01F10355
     */
    protected void validateSuccOrderMaxQuantity(EqTypeOrderUnit l_parentOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateSuccOrderMaxQuantity(EqTypeOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (notify予約注文登録)<BR>
     * 予約注文の登録をルールエンジンサーバに通知する。<BR>
     * <BR>
     * １）　@予約注文単位の取得<BR>
     * 　@連続注文マネージャ.get株式予約注文単位()にて、<BR>
     * 　@予約注文単位を取得する。<BR>
     * <BR>
     * 　@[get株式予約注文単位()に指定する引数]<BR>
     * 　@　@注文ID：　@子注文の注文ID<BR>
     * <BR>
     * ２）　@ルールエンジンに注文の登録を通知する。<BR>
     * 　@拡張株式注文マネージャ.notifyルールエンジンサーバ()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[notifyルールエンジンサーバ()に指定する引数]<BR>
     * 　@　@注文単位：　@１）の戻り値<BR>
     * 　@　@処理：　@NEW_SETTLE_CONTRACT_ORDER<BR>
     * @@param l_lngSubOrderId - (子注文の注文ID)<BR>
     * 子注文の注文ID。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 435EDE190096
     */
    protected void notifyRsvOrderRegister(long l_lngSubOrderId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " notifyRsvOrderRegister(long)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            //１）予約注文単位の取得
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngSubOrderId);
        }
        catch (NotFoundException l_nft)
        {
            log.error("テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nft.getMessage(),
                l_nft);
        }
        
        //２）ルールエンジンに注文の登録を通知する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);

        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager) l_tradingModule.getOrderManager();
        
        l_orderMgr.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SETTLE_CONTRACT_ORDER);
        log.exiting(STR_METHOD_NAME);  
    }
    
    
    /**
     * get注文株数<BR>
     * <BR>
     * 注文株数 として、以下の値を使用する。<BR>
     * ※求めた注文株数 > リクエストアダプタ.親注文の注文単位.注文数量の場合、<BR>
     * 　@　@　@「注文株数が親注文の注文数量を超過」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag: BUSINESS_ERROR_02290<BR>
     * <BR>
     * ----------------------------------------------------------------------<BR>
     * 　@リクエストアダプタ.リクエストデータ.決済順序区分≠"ランダム"の場合は、<BR>
     * 　@　@　@リクエストアダプタ.リクエストデータ.注文株数 を使用。<BR>
     * 　@リクエストアダプタ.リクエストデータ.決済順序区分=="ランダム"の場合は、<BR>
     * 　@　@　@リクエストアダプタ.リクエストデータ.決済建株一覧の全要素の<BR>
     * 　@　@　@注文株数のSUM値を使用。<BR>
     * <BR>
     * ----------------------------------------------------------------------<BR>
     * <BR>
     * @@param l_toRequestAdapter - (リクエストアダプタ)<BR>
     * （連続）信用取引返済リクエストアダプタオブジェクト。<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    private double getOrderQuantityCnt(
        WEB3ToSuccMarginCloseMarginRequestAdapter l_toRequestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantityCnt(WEB3ToSuccMarginCloseMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblOrderCnt = 0;
        WEB3GenRequest l_request = l_toRequestAdapter.request;
        
        String l_strClosingOrder = null;
        String l_strOrderQuantity = null;
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) l_request;
            l_strClosingOrder = l_confirmRequest.closingOrder;
            l_strOrderQuantity = l_confirmRequest.orderQuantity;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;

        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) l_request;
            l_strClosingOrder = l_completeRequest.closingOrder;
            l_strOrderQuantity = l_completeRequest.orderQuantity;
            l_closeMarginContractUnits = l_completeRequest.closeMarginContractUnits;

        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        if (!WEB3ClosingOrderDef.RANDOM.equals(l_strClosingOrder))
        {
            l_dblOrderCnt = Double.parseDouble(l_strOrderQuantity);
        }
        else
        {
            //リクエストアダプタ.リクエストデータ.決済順序区分=="ランダム"の場合は、
            //　@　@リクエストアダプタ.リクエストデータ.決済建株一覧の全要素の注文株数のSUM値を使用。
            int l_intUnitLength = l_closeMarginContractUnits.length;
            for (int i = 0; i < l_intUnitLength; i++)
            {
                l_dblOrderCnt += 
                    Double.parseDouble(l_closeMarginContractUnits[i].orderQuantity);
            }
        }
        
        //※求めた注文株数 > リクエストアダプタ.親注文の注文単位.注文数量の場合、
        //　@「注文株数が親注文の注文数量を超過」の例外をスローする。
        if (l_dblOrderCnt > l_toRequestAdapter.parentOrderUnit.getQuantity())
        {
            log.debug("注文株数が親注文の注文数量を超過しています。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02290,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文株数が親注文の注文数量を超過しています。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblOrderCnt;
    }

    /**
     * (create返済時注意文言)<BR>
     * レスポンスに設定する、返済時注意文言を取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * nullをreturnする。（カラ実装）<BR>
     * <BR>
     * @@param　@l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_updateInterceptor - (信用返済更新インタセプタ)<BR>
     * 信用返済更新インタセプタ
     * @@param l_orderSpec - (返済注文内容)<BR>
     * 返済注文内容
     * @@return WEB3TPAttentionObjection
     */
    protected WEB3TPAttentionObjection createCloseMarginAttentionWording(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginCloseMarginUpdateInterceptor l_updateInterceptor,
        WEB3MarginSettleContractOrderSpec l_orderSpec)

    {
        return null;
    }

    /**
     * (exec余力再計算)<BR>
     * 余力再計算を行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 何もせずにreturnする。（カラ実装）<BR>
     * <BR>
     * @@param　@l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     */
    protected void execReCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
    {

    }

    /**
     * (set単価)<BR>
     * 引数のレスポンス．調整後単価に単価を設定する。 <BR>
     * <BR>
     * １）リクエスト.単価調整値情報≠null（±指値指定）の場合 <BR>
     * 　@　@レスポンス．調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする。 <BR>
     * <BR>
     * ２）上記以外の場合、 <BR>
     * 　@　@何もせずリターンする。 <BR>
     * <BR>
     * @@param l_adapter - (信用取引返済リクエストアダプタ)<BR>
     * 信用取引返済リクエストアダプタ。<BR>
     * @@param l_response - (レスポンス)<BR>
     * レスポンス。<BR>
     * @@throws WEB3BaseException 
     */
    protected void setPrice(WEB3MarginCloseMarginRequestAdapter l_adapter,
        WEB3GenResponse l_response) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setPrice(WEB3MarginCloseMarginRequestAdapter, " 
            + "WEB3GenResponse)";
        log.entering(STR_METHOD_NAME);

        WEB3SuccMarginCloseConfirmRequest l_confirmRequest = null;

        if (l_adapter == null || l_response == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenRequest l_request = l_adapter.request;
        
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            l_confirmRequest = (WEB3SuccMarginCloseConfirmRequest)l_adapter.request;
            
            //１）リクエスト.単価調整値情報≠null（±指値指定）の場合 
            if (l_confirmRequest.priceAdjustmentValueInfo != null)
            {
                WEB3SuccMarginCloseConfirmResponse l_confirmResponse = 
                    (WEB3SuccMarginCloseConfirmResponse)l_response;
                
                //レスポンス．調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする。 
                l_confirmResponse.afterAdjustmentPrice = 
                    WEB3StringTypeUtility.formatNumber(l_adapter.getPrice());
            }
        }

        log.exiting(STR_METHOD_NAME);  
    }
}
@
