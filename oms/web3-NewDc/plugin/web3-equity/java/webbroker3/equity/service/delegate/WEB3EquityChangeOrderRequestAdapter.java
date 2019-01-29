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
filename	WEB3EquityChangeOrderRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文訂正リクエストアダプタ(WEB3EquityChangeOrderRequestAdapter)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/12 周玲玲(中訊) 新規作成
Revesion History : 2004/12/15 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/06 岡村和明(SRA) JavaDoc修正
Revesion History : 2006/11/03 張騰宇(中訊) モデル 1002
Revesion History : 2007/06/14 何文敏 (中訊) モデル 1174
Revesion History : 2007/12/19 張騰宇(中訊) モデル 1249
Revesion History : 2008/02/13 トウ鋒鋼 (中訊) 仕様変更モデルNo.1299
*/
package webbroker3.equity.service.delegate;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式注文訂正リクエストアダプタ）。<BR>
 * <BR>
 * 画面に依存した処理をラップするアダプタクラス。<BR>
 * <BR>
 * 当該クラスは、各証券会社の画面用件によって、<BR>
 * 複数作成されることを前提とする。
 * @@version 1.0
 */
public class WEB3EquityChangeOrderRequestAdapter
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityChangeOrderRequestAdapter.class);

    /**
     * (リクエストデータ)<BR>
     * <BR>
     * リクエストオブジェクト<BR>
     */
    public WEB3GenRequest requestData;

    /**
     * @@roseuid 409EFFD00060
     */
    protected WEB3EquityChangeOrderRequestAdapter()
    {

    }

    /**
     * (create)<BR>
     * 株式注文訂正リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成しする。<BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprotectedとし、<BR>
     * 本メソッドによってインスタンス化するように制限する）<BR>
     * @@param リクエスト - リクエストデータオブジェクト
     * @@return 株式注文リクエストアダプタ
     * @@roseuid 4021C7780319
     */
    public static WEB3EquityChangeOrderRequestAdapter create(WEB3GenRequest l_request)
    {
        final String STR_METHOD_NAME =
             "WEB3EquityChangeOrderRequestAdapter create(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        // 本インスタンスを生成しする
        WEB3EquityChangeOrderRequestAdapter l_adapter =
            new WEB3EquityChangeOrderRequestAdapter();

        // 生成したインスタンスに引数のリクエストデータをセットする
        if (l_request instanceof WEB3EquityChangeConfirmRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeConfirmRequest)l_request;
        }
        else if (l_request instanceof WEB3EquityChangeCompleteRequest)
        {
            l_adapter.requestData = (WEB3EquityChangeCompleteRequest)l_request;
        }

        log.exiting(STR_METHOD_NAME);
        // インスタンスを返却する
        return l_adapter;
    }

    /**
     * (get執行条件)<BR>
     * <BR>
     * 画面の執行条件より、<BR>
     * AP層で使用する訂正後の執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.執行条件)にdelegateする。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType
     * @@roseuid 4021C778031C
     */
    public EqTypeExecutionConditionType getExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecCondType()";
        log.entering(STR_METHOD_NAME);

        String l_strExecCondType = null;
        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strExecCondType =
                ((WEB3EquityChangeConfirmRequest)this.requestData).execCondType;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strExecCondType =
                ((WEB3EquityChangeCompleteRequest)this.requestData).execCondType;
        }
        else
        {
            log.error("訂正は未対応です。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        return l_orderManager.getExecutionConditionType(l_strExecCondType);
    }

    /**
     * (get訂正後株数)<BR>
     * <BR>
     * 訂正後の株数をリクエストオブジェクトより取得する。<BR>
     * <BR>
     * リクエストデータ.注文株数を返却する。<BR>
     * @@return double
     * @@roseuid 4021DD0C0173
     */
    public double getRequestOrderQuantity()
    {
        final String STR_METHOD_NAME = "getRequestOrderQuantity()";
        log.entering(STR_METHOD_NAME);

        String l_strChangeQuantity = "0";            //訂正後株数

        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strChangeQuantity =
                ((WEB3EquityChangeConfirmRequest) this.requestData).orderQuantity;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strChangeQuantity =
                ((WEB3EquityChangeCompleteRequest) this.requestData).orderQuantity;
        }
        else
        {
            log.error(
                "__訂正は未対応です(2004/03/25)__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "getChangeOrderQuantity"));
        }

        BigDecimal l_decChangeQuantity = new BigDecimal(l_strChangeQuantity);

        log.exiting(STR_METHOD_NAME);
        return l_decChangeQuantity.doubleValue();
    }

    /**
     * (get訂正後単価)<BR>
     * <BR>
     * 訂正後の単価をリクエストオブジェクトより取得する。<BR>
     * <BR>
     * リクエスト.注文単価区分=="指値"の場合は、
     * リクエストデータ.注文単価を返却する。<BR> 
     * <BR>
     * リクエスト.注文単価区分=="成行"の場合は、 <BR>
     * 0を返却する。 <BR>
     * @@return double
     * @@roseuid 4021DD1600A8
     */
    public double getRequestLimitPrice()
    {
        final String STR_METHOD_NAME = "getRequestLimitPrice()";
        log.entering(STR_METHOD_NAME);

        String l_strChangeLimitPrice = "0";               //訂正後単価

        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strChangeLimitPrice =
                ((WEB3EquityChangeConfirmRequest) this.requestData).limitPrice;
            if (l_strChangeLimitPrice == null
                || l_strChangeLimitPrice.trim().equals(""))
            {
                l_strChangeLimitPrice = "0";
            }
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strChangeLimitPrice =
                ((WEB3EquityChangeCompleteRequest) this.requestData).limitPrice;
            if (l_strChangeLimitPrice == null
                || l_strChangeLimitPrice.trim().equals(""))
            {
                l_strChangeLimitPrice = "0";
            }
        }
        else
        {
            log.error(
                "__訂正は未対応です(2004/03/25)__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "getRequestLimitPrice"));
        }

        BigDecimal l_decChangeLimitPrice = new BigDecimal(l_strChangeLimitPrice);

        log.exiting(STR_METHOD_NAME);
        return l_decChangeLimitPrice.doubleValue();
    }

    /**
     * (get注文ID)<BR>
     * <BR>
     * 訂正対象の注文ＩＤをリクエストオブジェクトより取得する。<BR>
     * <BR>
     * リクエストデータ.注文ＩＤを返却する。<BR>
     * @@return long
     * @@roseuid 4021DD300154
     */
    public long getRequestOrderId()
    {
        final String STR_METHOD_NAME = "getRequestOrderId()";
        log.entering(STR_METHOD_NAME);
      
        if (requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            log.exiting(STR_METHOD_NAME);
            return new Long(((WEB3EquityChangeCompleteRequest) requestData).id).longValue();
        }
        if (requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            String l_id = ((WEB3EquityChangeConfirmRequest) requestData).id;

            long l_value = Long.parseLong(l_id);

            log.exiting(STR_METHOD_NAME);
            return l_value;
        }
        else
        {
            log.error("__訂正は未対応です(2004/03/25)__",
                new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, //予期しないシステムエラーが発生しました
            this.getClass().getName() + "getRequestOrderId"));
        }

        log.exiting(STR_METHOD_NAME);
        return -1L;
    }

    /**
     * (is出来るまで注文)<BR>
     * <BR>
     * 訂正入力内容が「出来るまで注文」かどうかを判定する。<BR>
     * <BR>
     * ・リクエストデータ.注文期限区分＝”出来るまで注文”の場合はtrueを、<BR>
     * 　@リクエストデータ.注文期限区分＝”当日限り”の場合はfalseを、それぞれ返却する。<BR>
     * @@return boolean
     * @@roseuid 4074B97501C4
     */
    public boolean isOrderUntilDeadLine() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isOrderUntilDeadLine()";
        log.entering(STR_METHOD_NAME);
               
        // リクエストデータ.注文期限区分
        String l_strExpirationDateType = this.getExpirationDateType();

        // リクエストデータ.注文期限区分＝”出来るまで注文”の場合はtrue
        if (l_strExpirationDateType.equals(WEB3OrderExpirationDateTypeDef.CARRIED_ORDER))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        // リクエストデータ.注文期限区分＝”当日限り”の場合はfalse
        else if (l_strExpirationDateType.equals(WEB3OrderExpirationDateTypeDef.DAY_LIMIT))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00170,
                getClass().getName() + "." + STR_METHOD_NAME
                );
        }
    }

    /**
     * (get注文期限区分)<BR>
     * <BR>
     * 訂正対象の注文期限区分をリクエストオブジェクトより取得する。<BR>
     * <BR>
     * リクエストデータ.注文期限区分を返却する。<BR>
     * @@return String
     */
    public String getExpirationDateType()
    {
        final String STR_METHOD_NAME = "getExpirationDateType()";
        log.entering(STR_METHOD_NAME);
             
        String l_strExpirationDateType = null;            

        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strExpirationDateType =
                ((WEB3EquityChangeConfirmRequest) this.requestData).expirationDateType;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strExpirationDateType =
                ((WEB3EquityChangeCompleteRequest) this.requestData).expirationDateType;
        }
        else
        {
            log.error(
                "__訂正は未対応です(2004/03/25)__",
                new WEB3BaseException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + "getExpirationDateType"));
        }
       
        log.exiting(STR_METHOD_NAME);
        return l_strExpirationDateType;
    }

   /**
    * リクエストデータより執行条件を取得する。<BR>
    *<BR>
    * @@return 執行条件
    */
   public String getRequestExecCondType()
   {
       final String STR_METHOD_NAME = "getRequestExecCondType()";
       log.entering(STR_METHOD_NAME);

       if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
       {
            log.exiting(STR_METHOD_NAME);
            return ((WEB3EquityChangeConfirmRequest)this.requestData).execCondType;
       }
       else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
       {
            log.exiting(STR_METHOD_NAME);
            return ((WEB3EquityChangeCompleteRequest)this.requestData).execCondType;
       }
       else
       {
            log.error("__訂正は未対応です(2004/03/25)__",
               new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                   this.getClass().getName() + "getRequestExecCondType"));
       }

       log.exiting(STR_METHOD_NAME);
       return null;
    }
    
    /**
     * (get訂正後注文失効日)<BR>
     * <BR>
     * リクエストデータ.注文有効期限より、<BR>
     * AP層で使用する訂正後注文失効日を返却する。<BR>
     * <BR>
     * １）　@リクエストデータ.注文有効期限＝nullの場合
     * (当日限りの注文の場合)<BR>
     * 　@get発注日()の戻り値を返却する。<BR>
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
    public Date getExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate;
        long l_lngOrderId;
        if (requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            WEB3EquityChangeConfirmRequest l_equityChangeConfirmRequest =
                (WEB3EquityChangeConfirmRequest)requestData;
            l_datExpirationDate = l_equityChangeConfirmRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_equityChangeConfirmRequest.id);
        }
        else if (requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            WEB3EquityChangeCompleteRequest l_equityChangeCompleteRequest =
                (WEB3EquityChangeCompleteRequest)requestData;
            l_datExpirationDate = l_equityChangeCompleteRequest.expirationDate;
            l_lngOrderId = Long.parseLong(l_equityChangeCompleteRequest.id);
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

        //リクエストデータ.注文有効期限＝nullの場合
        if (l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        else
        {
            // 拡張株式注文マネージャ
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            //拡張株式注文マネージャ.getOrderUnits()
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
                        new Long(l_orderUnitRow.getProductId()).longValue());
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
     * (get（W指値）執行条件)<BR>
     * 画面の執行条件より、 <BR>
     * AP層で使用する執行条件（EqTypeExecutionConditionType）を返却する。 <BR>
     *<BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.（Ｗ指値）執行条件)にdelegateする。<BR>
     * @@return EqTypeExecutionConditionType
     * @@throws WEB3BaseException
     */
    public EqTypeExecutionConditionType getWLimitExecCondType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWLimitExecCondType()";
        log.entering(STR_METHOD_NAME);

        String l_strWlimitExecCondType = null;
        if (this.requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3EquityChangeConfirmRequest)this.requestData).wlimitExecCondType;
        }
        else if (this.requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_strWlimitExecCondType =
                ((WEB3EquityChangeCompleteRequest)this.requestData).wlimitExecCondType;
        }
        else
        {
            log.error("訂正は未対応です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();

        EqTypeExecutionConditionType l_eqTypeExecutionConditionType =
            l_orderManager.getExecutionConditionType(l_strWlimitExecCondType);

        log.exiting(STR_METHOD_NAME);
        return l_eqTypeExecutionConditionType;
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
    public Date getOrderExpirationDate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderExpirationDate()";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate = null;
        if (requestData instanceof WEB3EquityChangeConfirmRequest)
        {
            l_datExpirationDate = ((WEB3EquityChangeConfirmRequest)requestData).expirationDate;
        }
        else if (requestData instanceof WEB3EquityChangeCompleteRequest)
        {
            l_datExpirationDate = ((WEB3EquityChangeCompleteRequest)requestData).expirationDate;
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
            l_datExpirationDate = this.getExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
