head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCloseMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正返済リクエストアダプタ
                 : (WEB3MarginChangeCloseMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/30 li-songfeng (中訊) 新規作成
Revesion History : 2004/12/16 中尾寿彦(SRA) 残案件対応に伴う修正
Revesion History : 2005/01/05 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/27 趙林鵬 (中訊) モデル No.1021
Revesion History : 2007/06/14 何文敏 (中訊) モデル 1172
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正返済リクエストアダプタ）。<BR>
 * <BR>
 * 信用取引訂正返済リクエストアダプタクラス
 * @@version 1.0
 */
public class WEB3MarginChangeCloseMarginRequestAdapter 
{ 
   /**
    * (ログユーティリティ)<BR>
    */
   private static WEB3LogUtility log =
      WEB3LogUtility.getInstance(WEB3MarginChangeCloseMarginRequestAdapter.class);

    
    /**
     * (リクエストデータ)
     */
    private WEB3GenRequest request;
    
    /**
     * @@roseuid 4142B67A011C
     */
    private WEB3MarginChangeCloseMarginRequestAdapter() 
    {
     
    }
    
    /**
     * 信用取引訂正返済リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成する。<BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、<BR>
     * 本メソッドによってインスタンス化するように制限する）<BR>
     * @@param l_request - リクエストデータオブジェクト<BR>
     * @@return WEB3MarginCloseMarginChangeRequestAdapter<BR>
     * @@roseuid 40C7EAE700D9
     */
    public static WEB3MarginChangeCloseMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME =
              "WEB3MarginChangeCloseMarginRequestAdapter create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        // １）　@本インスタンスを生成する。
        WEB3MarginChangeCloseMarginRequestAdapter l_adapter = 
              new WEB3MarginChangeCloseMarginRequestAdapter();
        
        // ２）　@生成したインスタンスに引数のリクエストデータをセットする。
        l_adapter.request = l_request;
        
        log.exiting(STR_METHOD_NAME);
        // ３）　@インスタンスを返却する。
        return l_adapter;
    }
    
    /**
     * (get執行条件)<BR>
     * リクエストデータ.執行条件より、<BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.執行条件)にdelegateする。<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40C7EAE700DB
     */
    public EqTypeExecutionConditionType getExecutionCondition() throws WEB3BaseException
    {
        final String  STR_METHOD_NAME = "getExecutionCondition()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        String l_strExecCondType = null;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_request = (WEB3MarginCloseMarginChangeConfirmRequest)request;
            l_strExecCondType = l_request.execCondType;
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            WEB3MarginCloseMarginChangeCompleteRequest l_request = (WEB3MarginCloseMarginChangeCompleteRequest)request;
            l_strExecCondType = l_request.execCondType;
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderManager.getExecutionConditionType(l_strExecCondType);
    }
    
    /**
     * (get注文株数)<BR>
     * リクエストデータ.注文株数より、AP層で使用する注文株数を返却する。<BR>
     * <BR>
     * リクエストデータ.注文株数＝nullの場合、0を返却する。<BR>
     * 以外、リクエストデータ.注文株数のdouble値を返却する。<BR>
     * @@return double<BR>
     * @@roseuid 40C7EAE700DC
     */
    public double getOrderQuantity() 
    {
        final String STR_METHOD_NAME = "getOrderQuantity()";
        log.entering(STR_METHOD_NAME);
       
        String l_strChangeQuantity = null;            //訂正後株数
        if (this.request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            l_strChangeQuantity =
            ((WEB3MarginCloseMarginChangeConfirmRequest)this.request).orderQuantity;
        }
        else if (this.request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            l_strChangeQuantity =
            ((WEB3MarginCloseMarginChangeCompleteRequest)this.request).orderQuantity;
        }
        else
        {
            log.error(
               "__訂正は未対応です(2004/10/10)__",
                       new WEB3BaseException(
                           WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                           this.getClass().getName() + "getOrderQuantity"));
        }
        if (l_strChangeQuantity == null)
        {
            return 0.0D;
        }

        log.exiting(STR_METHOD_NAME);
        return Double.parseDouble(l_strChangeQuantity);
    }
    
    /**
     * (is出来るまで注文)<BR>
     * リクエストデータ.注文期限区分より、<BR>
     * 訂正入力内容が「出来るまで注文」かどうかを判定する。<BR>
     * <BR>
     * リクエストデータ.注文期限区分＝”出来るまで注文”の場合はtrueを、<BR>
     * リクエストデータ.注文期限区分＝”当日限り”の場合はfalseを返却する。<BR>
     * @@return boolean<BR>
     * @@roseuid 40C7EBD5030C
     */
    public boolean isCarriedOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isCarriedOrder()";
        log.entering(STR_METHOD_NAME);
        String l_expirationDateType = null;
        boolean l_isCarriedOrder = false;
        if (this.request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            l_expirationDateType = ((WEB3MarginCloseMarginChangeConfirmRequest)this.request).expirationDateType;
        }
        else if (this.request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
       {
           l_expirationDateType =
           ((WEB3MarginCloseMarginChangeCompleteRequest) this.request).expirationDateType;
       }
       else
       {
           log.error(
              "__訂正は未対応です(2004/10/10)__",
                      new WEB3BaseException(
                          WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                          this.getClass().getName() + "isCarriedOrder"));
           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + "isCarriedOrder");
       }
       if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_expirationDateType))
       {
           log.exiting(STR_METHOD_NAME);
           l_isCarriedOrder = true;
       }
       if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_expirationDateType))
       {
           log.exiting(STR_METHOD_NAME);
           l_isCarriedOrder = false;
       }
       log.exiting(STR_METHOD_NAME);
       return l_isCarriedOrder;
    }

    /**
     * (get訂正後（W指値）執行条件)<BR>
     * リクエストデータ.W指値用執行条件より、<BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.Ｗ指値用執行条件)にdelegateする。<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@throws WEB3BaseException <BR>
     * @@roseuid 40C7EBD5030C
     */
    public EqTypeExecutionConditionType getModifiedWLimitExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getModifiedWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        String l_strWlimitExecCondType = null;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_request =
                (WEB3MarginCloseMarginChangeConfirmRequest)request;

            l_strWlimitExecCondType = l_request.wlimitExecCondType;
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            WEB3MarginCloseMarginChangeCompleteRequest l_request =
                (WEB3MarginCloseMarginChangeCompleteRequest)request;

            l_strWlimitExecCondType = l_request.wlimitExecCondType;
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

        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_orderManager.getExecutionConditionType(l_strWlimitExecCondType);

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeExecutionConditionType;
    }

    /**
     * (get訂正後注文失効日)<BR>
     * リクエストデータ.注文有効期限より、AP層で使用する訂正後注文失効日を返却する。<BR>
     * <BR>
     * １）　@リクエストデータ.注文有効期限＝nullの場合<BR>
     * 　@　@　@(当日限りの注文の場合)<BR>
     * 　@　@　@　@get発注日()の戻り値を返却する。<BR>
     * <BR>
     * ２）　@上記以外の場合<BR>
     * <BR>
     * 　@２－１）　@拡張株式注文マネージャ.getOrderUnits()をコールし、<BR>
     * 　@　@　@　@　@　@注文単位オブジェクトの配列を取得する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[getOrderUnitsの引数設定]<BR>
     * 　@　@　@　@　@　@　@注文ID：　@リクエストデータ.ＩＤ<BR>
     * <BR>
     * 　@２－２）　@拡張株式注文マネージャ.get注文有効期限()をコールし、<BR>
     * 　@　@　@　@　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@　@　@　@　@　@［get注文有効期限の引数設定］<BR>
     * 　@　@　@　@　@　@　@注文有効期限：　@リクエストデータ.注文有効期限<BR>
     * 　@　@　@　@　@　@　@銘柄コード：　@(*)株式銘柄.銘柄コード<BR>
     * 　@　@　@　@　@　@　@市場コード：　@(*)市場.市場コード<BR>
     * <BR>
     * (*)　@２－１で取得した注文単位オブジェクト配列の0番目の要素より取得<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getModifiedExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getModifiedExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate;
        long l_lngOrderId;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            WEB3MarginCloseMarginChangeConfirmRequest l_confirmRequest =
                (WEB3MarginCloseMarginChangeConfirmRequest)request;
            l_datExpirationDate = l_confirmRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_confirmRequest.id);
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            WEB3MarginCloseMarginChangeCompleteRequest l_completeRequest =
                (WEB3MarginCloseMarginChangeCompleteRequest)request;
            l_datExpirationDate = l_completeRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_completeRequest.id);
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

        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        //上記以外の場合
        else
        {
            // 拡張株式注文マネージャ
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            // 　@２－１）拡張株式注文マネージャ.getOrderUnits()
            OrderUnit[] l_orderUnits = l_orderManager.getOrderUnits(l_lngOrderId);

            EqtypeOrderUnitRow l_orderUnitRow = (EqtypeOrderUnitRow)l_orderUnits[0].getDataSourceObject();
            ProductManager l_productManager = l_tradingModule.getProductManager();
            WEB3EquityProduct l_product = null;
            long l_lngMarketId = l_orderUnitRow.getMarketId();
            Market l_market = null;
            try
            {
                l_product =
                    (WEB3EquityProduct)l_productManager.getProduct(
                        l_orderUnitRow.getProductId());
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
            // 拡張株式注文マネージャ.get注文有効期限()をコールし、戻り値を返却する。
            Date l_datReturnExpirationDate = l_orderManager.getExpirationDate(
                l_datExpirationDate, l_strProductCode, l_strMarketCode);

            log.exiting(STR_METHOD_NAME);
            return l_datReturnExpirationDate;
        }
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
     * 　@　@　@this.get訂正後注文失効日()の戻り値を返却する。<BR>
     * <BR>
     * @@return Date
     * @@throws WEB3BaseException
     */
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate = null;
        if (request instanceof WEB3MarginCloseMarginChangeConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginChangeConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3MarginCloseMarginChangeCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginCloseMarginChangeCompleteRequest)request).expirationDate;
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

        if (l_datExpirationDate == null)
        {
            // リクエストデータ.注文有効期限＝nullの場合
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            // this.get訂正後注文失効日()の戻り値を返却する。
            l_datExpirationDate = this.getModifiedExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
