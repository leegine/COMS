head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引現引現渡サービスImpl(WEB3ToSuccMarginSwapMarginServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 沈迪(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

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
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.WEB3MarginSwapContractOrderSpec;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.message.WEB3MarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginRequestAdapter;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginServiceImpl;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginRequestAdapter;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引現引現渡サービスImpl)<BR>
 * （連続）信用取引現引現渡サービス実装クラス。<BR>
 * 
 * @@author 沈迪
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginServiceImpl extends WEB3MarginSwapMarginServiceImpl
    implements WEB3ToSuccMarginSwapMarginService 
{
    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginServiceImpl.class);
    
    /**
     * @@roseuid 436ACF75003E
     */
    public WEB3ToSuccMarginSwapMarginServiceImpl() 
    {
     
    }
    
    /**
     * （連続）信用取引現引現渡サービス処理を実施する。<BR>
     * <BR>
     * リクエストデータの型により、validate注文()メソッド、<BR>
     * submit注文()メソッドのいずれかをコールする。<BR>
     * @@param l_request - リクエストデータ<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4343697B0189
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
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            l_response = this.validateOrder((WEB3SuccMarginSwapConfirmRequest) l_request);
        }
        else if (l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            l_response = this.submitOrder((WEB3SuccMarginSwapCompleteRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate注文)<BR>
     * （連続）信用取引現引現渡発注審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引現引現渡サービス）validate注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3SuccMarginSwapConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 4343697B01A8
     */
    protected WEB3SuccMarginSwapConfirmResponse validateOrder(WEB3SuccMarginSwapConfirmRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateOrder(WEB3SuccMarginSwapConfirmRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //get株式親注文の注文単位(（親注文）注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_parentOrderUnit = l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //reset市場コード(市場コード : String)
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter =
            (WEB3ToSuccMarginSwapMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        long l_lngMarketId = 0L;
        if (l_toRequestAdapter.isReversingOrder())
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_parentOrderUnit.getDataSourceObject();
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdapter.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("市場テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "市場テーブルに該当するデータがありません。");
        }
        
        //validate連続注文(補助口座 : 補助口座, 銘柄タイプ : ProductTypeEnum,
        //        先物／オプション区分 : String, 連続注文取引区分 : String,
        //        親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_parentOrderUnit);
        
        //validate連続注文最大設定数(親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        //validate注文(リクエストデータ : 信用取引現引現渡注文確認リクエスト)
        WEB3SuccMarginSwapConfirmResponse l_response = 
            (WEB3SuccMarginSwapConfirmResponse) super.validateOrder(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit注文)<BR>
     * （連続）信用取引現引現渡注文登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（連続）信用取引現引現渡サービス）submit注文」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3SuccMarginSwapCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 4343697B01C7
     */
    protected WEB3SuccMarginSwapCompleteResponse submitOrder(WEB3SuccMarginSwapCompleteRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " submitOrder(WEB3SuccMarginSwapCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //validate()
        l_request.validate();
        
        //get株式親注文の注文単位(（親注文）注文ID : long)
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_request.succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_parentOrderUnit =
            l_toOrderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //get補助口座()
        WEB3GentradeSubAccount l_subAccount = this.getSubAccount();
        
        //reset市場コード(市場コード : String)
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter =
            (WEB3ToSuccMarginSwapMarginRequestAdapter) this.createRequestAdapter(l_request);
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        
        long l_lngMarketId = 0L;
        if (l_toRequestAdapter.isReversingOrder())
        {
            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow) l_parentOrderUnit.getDataSourceObject();
            l_lngMarketId = l_orderUnitRow.getMarketId();
        }
        else
        {
            l_lngMarketId = l_toRequestAdapter.getContract().getMarketId();
        }
        
        try
        {
            Market l_market = l_objectManager.getMarket(l_lngMarketId);
            String l_strMarketCode = l_market.getMarketCode();
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketCode);
        }
        catch (NotFoundException l_ex)
        {
            log.debug("市場テーブルに該当するデータがありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "市場テーブルに該当するデータがありません。");
        }
        
        //validate連続注文(補助口座 : 補助口座, 銘柄タイプ : ProductTypeEnum,
        //        先物／オプション区分 : String, 連続注文取引区分 : String,
        //        親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrder(
            l_subAccount,
            ProductTypeEnum.EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            l_request.succCommonInfo.succTradingType,
            l_parentOrderUnit);
        
        //validate連続注文最大設定数(親注文の注文単位 : OrderUnit)
        l_toOrderManager.validateSuccOrderMaxQuantity(l_parentOrderUnit);
        
        //submit注文(リクエストデータ : 信用取引現引現渡注文完了リクエスト)
        WEB3SuccMarginSwapCompleteResponse l_response = 
            (WEB3SuccMarginSwapCompleteResponse) super.submitOrder(l_request);
        
        //get株式予約注文単位(注文ID : long)
        long l_lngOrderId = Long.parseLong(l_request.orderId);
        WEB3ToSuccEqTypeOrderUnitImpl l_orderUnit = null;
        try
        {
            l_orderUnit = l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId); 
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "株式予約注文単位テーブルに該当するデータがありません。「注文ID:" + l_lngOrderId + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        //notifyルールエンジンサーバ(注文単位 : EqTypeOrderUnit,
        //        処理 : OrderManagerPersistenceContext)
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        l_orderManager.notifyRLS(l_orderUnit, OrderManagerPersistenceContext.NEW_SWAP_CONTRACT_ORDER);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (createリクエストアダプタ)<BR>
     * リクエストアダプタのインスタンスを生成する。<BR>
     * <BR>
     * （連続）信用取引現引現渡リクエストアダプタ.create(引数のリクエスト)を<BR>
     * コールする。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータ。<BR>
     * @@return WEB3MarginSwapMarginRequestAdapter
     * @@roseuid 4344BEA203C4
     */
    protected WEB3MarginSwapMarginRequestAdapter createRequestAdapter(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " createRequestAdapter(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginSwapMarginRequestAdapter l_adapter =
            WEB3ToSuccMarginSwapMarginRequestAdapter.create(l_request);
        
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
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
     * 　@　@　@「注文株数が親注文の注文数量を超過」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@　@tag: BUSINESS_ERROR_02290<BR>
     * 　@　@<BR>
     * ----------------------------------------------------------------------<BR>
     * 　@　@　@リクエストアダプタ.リクエストデータ.決済順序区分≠"ランダム"の場合は、<BR>
     * 　@　@　@　@　@リクエストアダプタ.リクエストデータ.注文株数 を使用。<BR>
     * 　@　@　@リクエストアダプタ.リクエストデータ.決済順序区分=="ランダム"の場合は、<BR>
     * 　@　@　@　@　@リクエストアダプタ.リクエストデータ.決済建株一覧の全要素の<BR>
     * 　@　@　@　@　@注文株数のSUM値を使用。<BR>
     * 　@　@<BR>
     * ----------------------------------------------------------------------<BR>
     * <BR>
     * ２）　@１）以外の場合、<BR>
     * 　@super.create決済建株エントリ()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[super.create決済建株エントリ()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_closeMarginContractUnits - (決済建株明細一覧)<BR>
     * 信用取引決済建株オブジェクトの配列。<BR>
     * （リクエスト）<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * （連続）信用取引現引現渡リクエストアダプタオブジェクト。<BR>
     * @@return EqTypeSettleContractOrderEntry[]
     * @@throws WEB3BaseException
     * @@roseuid 4344DF250078
     */
    protected EqTypeSettleContractOrderEntry[] createClosingContractEntry(
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits, 
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createClosingContractEntry(WEB3MarginCloseMarginContractUnit[], WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntries = null;
        
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        
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
                super.createClosingContractEntry(l_closeMarginContractUnits, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_settleContractOrderEntries;
    }
    
    /**
     * (get概算受渡代金（現引現渡）)<BR>
     * 概算受渡代金（現引現渡）を取得する。<BR>
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
     * 　@１－２）　@拡張株式注文マネージャ.calc概算受渡代金（現引現渡）()を<BR>
     * 　@　@　@　@　@　@コールし、戻り値を返却する。<BR>
     * <BR>
     * 　@　@[calc概算受渡代金（現引現渡）()にセットするパラメータ]<BR>
     * 　@　@　@決済建株エントリ： 　@パラメータの同項目<BR>
     * 　@　@　@数量：　@パラメータの同項目<BR>
     * 　@　@　@注文単位：　@null（固定）<BR>
     * 　@　@　@建株：　@反対売買の場合、１－１）の戻り値。以外、null。<BR>
     * <BR>
     * ２）　@既存残に対する現引現渡（１）以外）の場合、<BR>
     * 　@super.get概算受渡代金（現引現渡）()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[get概算受渡代金（現引現渡）()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_entries - (決済建株エントリの配列)<BR>
     * 決済建株エントリの配列<BR>
     * @@param l_dblQuantity -(数量)<BR> 
     * 数量<BR>
     * @@param l_requestAdapter - (信用取引現引現渡リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタ。<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 4344E010026C
     */
    protected double getEstimatedSwapPrice(
        EqTypeSettleContractOrderEntry[] l_entries,
        double l_dblQuantity,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getEstimatedSwapPrice(EqTypeSettleContractOrderEntry[], double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblEstimatedSwapPrice = 0.0;
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        boolean l_blnIsReversingOrder = l_toRequestAdapter.isReversingOrder();
        
        //１）　@反対売買の場合、
        if (l_blnIsReversingOrder)
        {
            //１－１）　@連続注文マネージャImpl.create建株()を
            //　@コールし、建株を作成する。
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            WEB3EquityContract l_contract = l_toOrderManager.createContract(l_toRequestAdapter.parentOrderUnit);
            
            //１－２）　@拡張株式注文マネージャ.calc概算受渡代金（現引現渡）()をコールし、
            //　@戻り値を返却する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager) l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            l_dblEstimatedSwapPrice = l_orderManager.calcEstimatedSwapPrice(
                l_entries,
                l_dblQuantity,
                null,
                l_contract);
        }
        
        //２）　@既存残に対する現引現渡（１）以外）の場合、
        //　@super.get概算受渡代金（現引現渡）()をコールし、
        //　@戻り値を返却する。
        else
        {
            l_dblEstimatedSwapPrice = super.getEstimatedSwapPrice(l_entries, l_dblQuantity, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblEstimatedSwapPrice;
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
     * ２）　@既存残に対する現引現渡（１）以外）の場合、<BR>
     * 　@super.create建株明細一覧()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create建株明細一覧()に指定する引数]<BR>
     * 　@　@本メソッドの引数をそのまま設定。<BR>
     * @@param l_settleContractOrderEntrys - (決済建株エントリ)<BR>
     * 決済建株エントリの配列。<BR>
     * @@param l_dblUnitPrice - (時価)<BR>
     * 時価。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * （連続）信用取引現引現渡リクエストアダプタオブジェクト。<BR>
     * @@return webbroker3.equity.message.WEB3MarginContractUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 4344E1BE020E
     */
    protected WEB3MarginContractUnit[] createMarginContractUnitList(
        EqTypeSettleContractOrderEntry[] l_settleContractOrderEntrys,
        double l_dblUnitPrice,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createMarginContractUnitList(EqTypeSettleContractOrderEntry[]," + 
            " double, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginContractUnit[] l_marginContractUnits = null;
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        boolean l_blnIsReversingOrder = l_toRequestAdapter.isReversingOrder();
        
        //１）　@反対売買の場合、
        if (l_blnIsReversingOrder)
        {
            //１－１）　@連続注文マネージャImpl.create建株明細()を
            //　@コールする。
            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            WEB3MarginContractUnit l_marginContractUnit =
                l_toOrderManager.createMarginContractUnit(l_toRequestAdapter.parentOrderUnit);
            
            //１－２）　@プロパティセットしたインスタンスのみを要素とする
            //　@配列を生成し、返却する。
            l_marginContractUnits = new WEB3MarginContractUnit[]{l_marginContractUnit};
        }
        //２）　@既存残に対する現引現渡（１）以外）の場合、
        //　@super.create建株明細一覧()をコールし、
        //　@戻り値を返却する。
        else
        {
            l_marginContractUnits = 
                super.createMarginContractUnitList(l_settleContractOrderEntrys, l_dblUnitPrice, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_marginContractUnits;
    }
    
    /**
     * (validate現引現渡注文)<BR>
     * 信用取引現引現渡注文発注審査を行う。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@反対売買の場合<BR>
     * 　@（パラメータ.リクエストアダプタ.is反対売買() == true）<BR>
     * 　@拡張株式注文マネージャ.validate現引現渡注文()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[validate現引現渡注文()に指定する引数]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@信用現引現渡注文内容：　@パラメータ.信用現引現渡注文内容<BR>
     * 　@　@建株：　@パラメータ.リクエストアダプタ.get建株()<BR>
     * <BR>
     * ２）　@１）以外の場合<BR>
     * 　@super.validate現引現渡注文()をコールする。<BR>
     * <BR>
     * 　@[validate現引現渡注文()に指定する引数]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * 　@　@信用現引現渡注文内容：　@パラメータ.信用現引現渡注文内容<BR>
     * 　@　@リクエストアダプタ：　@パラメータ.リクエストアダプタ<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_orderSpec - (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタオブジェクト。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4344E22D003A
     */
    protected void validateSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateSwapContractOrder(WEB3GentradeSubAccount," +
            " WEB3MarginSwapContractOrderSpec, WEB3MarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        boolean l_blnIsReversingOrder = l_toRequestAdapter.isReversingOrder();
        
        //１）　@反対売買の場合
        if (l_blnIsReversingOrder)
        {
            //拡張株式注文マネージャ.validate現引現渡注文()
            //をコールする。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            EqTypeNewOrderValidationResult l_result =
                l_orderManager.validateSwapContractOrder(l_subAccount, l_orderSpec, l_toRequestAdapter.getContract());
            if (l_result.getProcessingResult().isFailedResult())
            {
                log.debug("拡張株式注文マネージャ.validate現引現渡注文()が失敗の場合。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    l_result.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "拡張株式注文マネージャ.validate現引現渡注文()が失敗の場合。");
            }
        }
        //２）　@１）以外の場合
        //　@super.validate現引現渡注文()をコールする。
        else
        {
            super.validateSwapContractOrder(l_subAccount, l_orderSpec, l_requestAdapter);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit現引現渡注文)<BR>
     * 現引現渡の予約注文を登録する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * 連続注文マネージャImpl.submit信用現引現渡新規予約注文()をコールする。<BR>
     * <BR>
     * [submit信用現引現渡新規予約注文()に指定する引数]<BR>
     * 補助口座：　@引数の補助口座<BR>
     * 注文内容：　@引数の信用現引現渡注文内容<BR>
     * 注文ID：　@引数の注文ID<BR>
     * 取引パスワード：　@引数のパスワード<BR>
     * 連続注文取引区分：　@引数のリクエストアダプタ.リクエスト.<BR>
     * 　@　@　@　@　@　@　@　@　@　@連続注文共通情報.連続注文取引区分<BR>
     * 親注文の注文単位：　@引数のリクエストアダプタ.親注文の注文単位<BR>
     * 概算受渡代金：　@引数の概算受渡代金<BR>
     * 譲渡益金額：　@引数の譲渡益金額<BR>
     * 譲渡益税額：　@引数の譲渡益税額<BR>
     * 建株：　@引数のリクエストアダプタ.get建株()<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。<BR>
     * @@param l_lngOrderId - (注文ID)<BR>
     * 注文ID。<BR>
     * @@param l_strTradingPassword - (取引パスワード)<BR>
     * 取引パスワード。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * （連続）信用取引現引現渡リクエストアダプタオブジェクト。<BR>
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。<BR>
     * @@param l_dblCapitalGain - (譲渡益金額)<BR>
     * 譲渡益金額。<BR>
     * @@param l_dblCapitalGainTax - (譲渡益税額)<BR>
     * 譲渡益税額。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4344E34B000B
     */
    protected void submitSwapContractOrder(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        long l_lngOrderId, String l_strTradingPassword,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " submitSwapContractOrder(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec," +
            " long, String, WEB3MarginSwapMarginRequestAdapter, double, double, double)";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        
        WEB3ToSuccOrderManagerImpl l_toOrderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3GenRequest l_request = l_toRequestAdapter.request;
        String l_strSuccTradingType = null;
        
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) l_request;
            l_strSuccTradingType = l_confirmRequest.succCommonInfo.succTradingType;
        }
        else if (l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) l_request;
            l_strSuccTradingType = l_completeRequest.succCommonInfo.succTradingType;
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
        
        //連続注文マネージャImpl.submit信用現引現渡新規予約注文()をコールする。
        l_toOrderManager.submitEqtypeSwapContractNewOrder(
            l_subAccount,
            l_orderSpec,
            l_lngOrderId,
            l_strTradingPassword,
            l_strSuccTradingType,
            l_toRequestAdapter.parentOrderUnit,
            l_dblEstimatedPrice,
            l_dblCapitalGain,
            l_dblCapitalGainTax,
            l_toRequestAdapter.getContract());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate取引余力)<BR>
     * 取引余力をチェックし、取引余力結果オブジェクトを返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@建株オブジェクトを取得する。<BR>
     * <BR>
     * 　@　@引数のリクエストアダプタ.get建株()により取得。<BR>
     * <BR>
     * ２）　@取得した建株.isLong()==false（現渡）の場合は、<BR>
     * 　@　@何もせずにreturnする。（nullを返却）<BR>
     * <BR>
     * ３）　@取得した建株.isLong()==true（現引）の場合は、<BR>
     * 　@　@余力チェックを実施する部店(*3)の場合のみ、<BR>
     * 　@　@現引可能額(*1)と概算受渡代金(*2)を比較し、<BR>
     * 　@　@（概算受渡代金(*2) > 現引可能額(*1)）の場合は、<BR>
     * 　@　@「取引余力不足」の例外をthrowする。<BR>
     * 　@　@class: WEB3BusinessLayerException <BR>
     * 　@　@tag: BUSINESS_ERROR_00855<BR>
     * <BR>
     * 　@　@以外、nullを返却する。<BR>
     * <BR>
     * (*1)買付可能額<BR>
     * 取引余力サービス.get信用現引可能額～連続注文～(引数の<BR>
     * 補助口座, 引数の取引銘柄.受渡日)の<BR>
     * 戻り値を、現引可能額とする。<BR>
     * <BR>
     * (*2)概算受渡代金<BR>
     * 引数の概算受渡代金×（－１）を使用。<BR>
     * <BR>
     * (*3)余力チェックを実施する部店<BR>
     * 連続注文マネージャImpl.is余力チェック実施部店()==trueの場合は、<BR>
     * 余力チェックを実施する部店であると判定する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト。<BR>
     * @@param l_orderSpec - (信用現引現渡注文内容)<BR>
     * 信用現引現渡注文内容オブジェクト。<BR>
     * @@param l_tradedProduct - (取引銘柄)<BR>
     * 取引銘柄オブジェクト。<BR>
     * @@param l_requestAdapter - (リクエストアダプタ)<BR>
     * 信用取引現引現渡リクエストアダプタオブジェクト。<BR>
     * @@param l_dblEstimatedPrice - (概算受渡代金)<BR>
     * 概算受渡代金。<BR>
     * @@param l_dblCapitalGain - (譲渡益金額)<BR>
     * 譲渡益金額。<BR>
     * @@param l_dblCapitalGainTax - (譲渡益税額)<BR>
     * 譲渡益税額。<BR>
     * @@param l_blnUpdateFlg - (余力更新フラグ)<BR>
     * 余力更新フラグ。<BR>
     * （false：　@確認時、true：　@完了時）<BR>
     * @@return WEB3TPTradingPowerResult
     * @@throws WEB3BaseException
     * @@roseuid 4344EB53002A
     */
    protected WEB3TPTradingPowerResult validateTradingPower(
        WEB3GentradeSubAccount l_subAccount,
        WEB3MarginSwapContractOrderSpec l_orderSpec,
        WEB3EquityTradedProduct l_tradedProduct,
        WEB3MarginSwapMarginRequestAdapter l_requestAdapter,
        double l_dblEstimatedPrice,
        double l_dblCapitalGain,
        double l_dblCapitalGainTax,
        boolean l_blnUpdateFlg) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " validateTradingPower(WEB3GentradeSubAccount, WEB3MarginSwapContractOrderSpec," +
            " WEB3EquityTradedProduct, WEB3MarginSwapMarginRequestAdapter, double, double, double, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@建株オブジェクトを取得する。
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter = 
            (WEB3ToSuccMarginSwapMarginRequestAdapter) l_requestAdapter;
        WEB3EquityContract l_equityContract = l_toRequestAdapter.getContract();
        
        //３）　@取得した建株.isLong()==true（現引）の場合は、
        if (l_equityContract.isLong())
        {
            WEB3ToSuccOrderManagerImpl l_toOrderManager =
            (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            if (l_toOrderManager.isCheckTradingPowerBranch(l_subAccount) == false)
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            //(*1)現引可能額
            WEB3TPTradingPowerService l_tradingPowerService
                = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            double l_dblSuccActualReceiptTradingPower = l_tradingPowerService.getSuccActualReceiptTradingPower(
                l_subAccount, l_tradedProduct.getDailyDeliveryDate());
            
            //現引可能額(*1)と概算受渡代金(*2)を比較し、
            //（概算受渡代金(*2) > 現引可能額(*1)）の場合は、
            //「取引余力不足」の例外をthrowする。
            if (l_dblEstimatedPrice * (-1) > l_dblSuccActualReceiptTradingPower)
            {
                log.debug("取引余力不足エラー。（信用現引可能額不足）");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00855,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "取引余力不足エラー。（信用現引可能額不足）");
            }
        }
        log.exiting(STR_METHOD_NAME);
        return null;
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
     * （連続）信用取引現引現渡リクエストアダプタオブジェクト。<BR>
     * @@return double
     * @@throws WEB3BaseException
     */
    private double getOrderQuantityCnt(
        WEB3ToSuccMarginSwapMarginRequestAdapter l_toRequestAdapter) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderQuantityCnt(WEB3ToSuccMarginSwapMarginRequestAdapter)";
        log.entering(STR_METHOD_NAME);
        
        double l_dblOrderCnt = 0;
        WEB3GenRequest l_request = l_toRequestAdapter.request;
        
        String l_strClosingOrder = null;
        String l_strOrderQuantity = null;
        WEB3MarginCloseMarginContractUnit[] l_closeMarginContractUnits = null;
        
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) l_request;
            l_strClosingOrder = l_confirmRequest.closingOrder;
            l_strOrderQuantity = l_confirmRequest.orderQuantity;
            l_closeMarginContractUnits = l_confirmRequest.closeMarginContractUnits;

        }
        else if(l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) l_request;
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
}
@
