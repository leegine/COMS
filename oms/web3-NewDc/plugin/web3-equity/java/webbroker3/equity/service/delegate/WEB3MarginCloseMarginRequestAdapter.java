head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginCloseMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引返済リクエストアダプタクラス(WEB3MarginCloseMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 呉艶飛 (中訊) 新規作成
Revesion History : 2004/12/09 森川   (SRA)  残案件対応
Revesion History : 2005/01/05 岡村   (SRA) JavaDoc修正
Revesion History : 2006/11/27 柴双紅(中訊) モデル1010
Revesion History : 2007/06/14 何文敏(中訊) モデル1171
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引返済リクエストアダプタ）。<BR>
 * <BR>
 * 信用取引返済リクエストアダプタクラス。
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3MarginCloseMarginRequestAdapter 
{
    
    /**
     * (ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginCloseMarginRequestAdapter.class);
    
    /**
     * (リクエストデータ)。<BR>
     */
    public WEB3GenRequest request;
    
    /**
     * (コンストラクタ)。 
     */
    protected WEB3MarginCloseMarginRequestAdapter()
    {
    }

    /**
     * (create)。<BR>
     * <BR>
     * 信用取引返済リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成する。<BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、<BR>
     * 本メソッドによってインスタンス化するように制限する）<BR>
     * <BR>
     * @@param l_request - リクエストデータオブジェクト<BR>
     * @@return WEB3MarginCloseMarginRequestAdapter<BR>
     * @@roseuid 40B70E3E028F
     */
    public static WEB3MarginCloseMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MarginCloseMarginRequestAdapter l_adapter = new WEB3MarginCloseMarginRequestAdapter();
        l_adapter.request = l_request;
        log.exiting(STR_METHOD_NAME);
         return l_adapter;
    }
    
    /**
     * (get執行条件)。<BR>
     * <BR>
     * リクエストデータ.執行条件より、AP層で使用する執行条件（<BR>
     * EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * リクエストデータ.執行条件より、 <BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR> 
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.執行条件)にdelegateする。<BR>
     * <BR> 
     * @@return EqTypeExecutionConditionType<BR>
     * @@roseuid 40B70E3E0291
     */
    public EqTypeExecutionConditionType getExecCondType() 
    {
        final String STR_METHOD_NAME = " getExecCondType( )";
        log.entering(STR_METHOD_NAME);
        
        String l_executionCondition = null;
        
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_executionCondition = ((WEB3MarginCloseMarginConfirmRequest)this.request).execCondType;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_executionCondition = ((WEB3MarginCloseMarginCompleteRequest)this.request).execCondType;
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager = 
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
        EqTypeExecutionConditionType l_EqTypeExecutionConditionType;
        try {
            l_EqTypeExecutionConditionType = l_orderManager.getExecutionConditionType(l_executionCondition);
        }
        catch (WEB3BaseException l_wbe) {
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00019, STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_EqTypeExecutionConditionType;
    }
    
    /**
     * (get注文株数)。<BR>
     * <BR>
     * リクエストデータ.決済順序区分、およびリクエストデータ.注文株数より、<BR>
     * AP層で使用する注文株数を返却する。<BR>
     * <BR>
     * １）　@リクエストデータ.決済順序区分が”ランダム”の場合、0を返却する。<BR>
     * <BR>
     * ２）　@リクエストデータ.決済順序区分が”ランダム”以外の場合、<BR>
     * 　@　@　@リクエストデータ.注文株数のdouble値を返却する。<BR>
     * <BR>
     * @@return double<BR>
     * @@roseuid 40BED92C014F
     */
    public double getOrderQuantity() 
    {
        final String STR_METHOD_NAME = " getOrderQuantity( )";
        log.entering(STR_METHOD_NAME);
        WEB3MarginCloseMarginConfirmRequest l_confirmRequest = null;
        WEB3MarginCloseMarginCompleteRequest l_completeRequest = null;
        double l_dblReturn = 0D;
        if (request instanceof WEB3MarginCloseMarginConfirmRequest) //validate注文
        {
            l_confirmRequest = (WEB3MarginCloseMarginConfirmRequest) request;
            if (WEB3ClosingOrderDef.RANDOM.equals(l_confirmRequest.closingOrder))
            {
                l_dblReturn = 0D;
            }
            else
            {
                l_dblReturn = Double.parseDouble(l_confirmRequest.orderQuantity);
            }

        }
        else if (request instanceof WEB3MarginCloseMarginCompleteRequest) //submitOrder注文
        {
            l_completeRequest = (WEB3MarginCloseMarginCompleteRequest) request;
            if (WEB3ClosingOrderDef.RANDOM.equals(l_completeRequest.closingOrder))
            {
                l_dblReturn = 0D;
            }
            else
            {
                l_dblReturn = Double.parseDouble(l_completeRequest.orderQuantity);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblReturn;
    }
    
    /**
     * (get単価)<BR>
     * 単価をリクエストオブジェクトより取得する。<BR>
     * <BR>
     * リクエストデータ.注文単価区分=="指値"の場合は、<BR>
     * リクエストデータ.注文単価を返却する。<BR>
     * <BR>
     * リクエストデータ.注文単価区分=="成行"の場合は、<BR>
     * 0を返却する。<BR>
     * @@return double
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPrice()";
        log.entering(STR_METHOD_NAME);
        
        String l_strOrderPriceDiv = null; 
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginCloseMarginConfirmRequest)this.request).orderPriceDiv;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_strOrderPriceDiv =
                ((WEB3MarginCloseMarginCompleteRequest)this.request).orderPriceDiv;
        }
        
        double l_dblPrice = 0.0D;
        if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_strOrderPriceDiv))
        {
            String l_strLimitPrice = null; 
            if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginCloseMarginConfirmRequest)this.request).limitPrice;
            }
            else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
            {
                l_strLimitPrice =
                    ((WEB3MarginCloseMarginCompleteRequest)this.request).limitPrice;
            }
            if (l_strLimitPrice != null)
            {
                l_dblPrice = Double.parseDouble(l_strLimitPrice);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
    
    /**
     * (get建株)<BR>
     * 建株オブジェクトを返却する。<BR>
     * <BR>
     * リクエスト.決済建株明細一覧[0].IDに該当する建株を<BR>
     * 取得し、返却する。<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    public WEB3EquityContract getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);
        

        String l_strId = null;
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_strId =
                ((WEB3MarginCloseMarginConfirmRequest)this.request).closeMarginContractUnits[0].id;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_strId =
                ((WEB3MarginCloseMarginCompleteRequest)this.request).closeMarginContractUnits[0].id;
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract =
                (WEB3EquityContract)l_positionManager.getContract(
                    Long.parseLong(l_strId));
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }

    /**
     * (get（Ｗ指値）執行条件)<BR>
     * リクエストデータ.執行条件より、<BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.（Ｗ指値）執行条件)にdelegateする。<BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getWLimitExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        String l_strWlimitExecCondType = null;
        if (this.request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3MarginCloseMarginConfirmRequest)this.request).wlimitExecCondType;
        }
        else if (this.request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3MarginCloseMarginCompleteRequest)this.request).wlimitExecCondType;
        }
        else
        {
            log.error("パラメータ.リクエストデータの型が不正です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
        EqTypeExecutionConditionType l_eqyTypeExecutionConditonType =
            l_orderManager.getExecutionConditionType(l_strWlimitExecCondType);

        log.exiting(STR_METHOD_NAME);
        return l_eqyTypeExecutionConditonType;
    }

    /**
     * (get注文有効期限)<BR>
     * 注文有効期限を取得する。<BR>
     * <BR>
     * １）　@リクエストデータ.注文有効期限＝nullの場合<BR>
     * 　@　@　@(当日限りの注文の場合)<BR>
     * 　@　@　@　@nullを返却する。<BR>
     * <BR>
     * ２）　@上記以外の場合<BR>
     * 　@　@拡張株式注文マネージャ.get注文有効期限()をコールし、<BR>
     * 　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@　@[get注文有効期限の引数設定]<BR>
     * 　@　@　@注文有効期限：　@リクエストデータ.注文有効期限<BR>
     * 　@　@　@銘柄コード：　@this.get建株()で取得した建株.銘柄IDに該当する銘柄コード<BR>
     * 　@　@　@市場コード：　@this.get建株()で取得した建株.市場IDに該当する市場コード<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        // 注文有効期限：　@リクエストデータ.注文有効期限
        Date l_datExpirationDate = null;
        if (request instanceof WEB3MarginCloseMarginConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginConfirmRequest)request).expirationDate;

        }
        else if (request instanceof WEB3MarginCloseMarginCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginCompleteRequest)request).expirationDate;
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

        // リクエストデータ.注文有効期限＝nullの場合
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // 上記以外の場合
            // 銘柄コード：　@this.get建株()で取得した建株.銘柄IDに該当する銘柄コード
            EqtypeContractRow l_eqtypeContractRow =
                (EqtypeContractRow)this.getContract().getDataSourceObject();
            long l_lngProductId = l_eqtypeContractRow.getProductId();
            long l_lngMarketId = l_eqtypeContractRow.getMarketId();
            // 拡張株式注文マネージャ.get注文有効期限()をコールし、戻り値を返却する。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            ProductManager l_productManager = l_tradingModule.getProductManager();
            WEB3EquityProduct l_product = null;
            Market l_market = null;
            try
            {
                l_product =
                    (WEB3EquityProduct)l_productManager.getProduct(l_lngProductId);
                l_market = (Market)l_finApp.getFinObjectManager().getMarket(l_lngMarketId);
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // 銘柄コード：　@(*)株式銘柄.銘柄コード
            String l_strProductCode = l_product.getProductCode();
            // 市場コード：　@(*)市場.市場コード
            String l_strMarketCode = l_market.getMarketCode();
            l_datExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
