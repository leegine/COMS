head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeOpenMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正新規建リクエストアダプタクラス(WEB3MarginChangeOpenMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 盧法@旭(中訊) 新規作成
Revesion History : 2004/12/15 中尾寿彦(SRA) 残案件対応による修正
Revesion History : 2005/01/05 岡村   (SRA) JavaDoc修正
Revesion History : 2006/11/28 趙林鵬 (中訊) モデル No.1009
Revesion History : 2007/06/13 何文敏 (中訊) モデル 1170
*/

package webbroker3.equity.service.delegate;

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
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeCompleteRequest;
import webbroker3.equity.message.WEB3MarginOpenMarginChangeConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProduct;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引訂正新規建リクエストアダプタクラス）。
 * @@author 法@旭
 * @@version 1.0
 */
public class WEB3MarginChangeOpenMarginRequestAdapter 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginChangeOpenMarginRequestAdapter.class);
    /**
     * (リクエストデータ)
     */
    private WEB3GenRequest request;
    
    /**
     * @@roseuid 4142B6790229
     */
    private WEB3MarginChangeOpenMarginRequestAdapter() 
    {
     
    }
    
    /**
     * 信用取引訂正新規建リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成する。<BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、<BR>
     * 本メソッドによってインスタンス化するように制限する）<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト<BR>
     * @@return WEB3MarginOpenMarginChangeRequestAdapter<BR>
     * @@roseuid 40E2409502FD
     */
    public static WEB3MarginChangeOpenMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = "WEB3MarginChangeOpenMarginRequestAdapter create(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        if(l_request == null)
        {
            throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               "WEB3MarginChangeOpenMarginRequestAdapter" + "." + STR_METHOD_NAME);
        }
        WEB3MarginChangeOpenMarginRequestAdapter  l_adapter = new WEB3MarginChangeOpenMarginRequestAdapter();
        l_adapter.request = l_request;
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }
    
    /**
     * (get訂正後注文株数)<BR>
     * リクエストデータ.注文株数より、AP層で使用する訂正後注文株数を返却する。<BR>
     * <BR>
     * リクエストデータ.注文株数＝nullの場合<BR>
     * 　@0を返却する。<BR>
     * <BR>
     * 以外、リクエストデータ.注文株数のdouble値を返却する。<BR>
     * @@return double<BR>
     * @@roseuid 40E24095031C
     */
    public double getModifiedOrderQuantity() 
    {
        final String STR_METHOD_NAME = "getModifiedOrderQuantity";
        log.entering(STR_METHOD_NAME);
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //リクエストデータ.注文株数＝nullの場合
            if (l_request1.orderQuantity == null)
            {
                return 0;   
            }
            //以外、リクエストデータ.注文株数のdouble値を返却する
            else
            {
                return Double.parseDouble(l_request1.orderQuantity);
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //リクエストデータ.注文株数＝nullの場合
            if (l_request2.orderQuantity == null)
            {
                return 0;   
            }
            //以外、リクエストデータ.注文株数のdouble値を返却する
            else
            {
                return Double.parseDouble(l_request2.orderQuantity);
            }
        }
        log.exiting(STR_METHOD_NAME);   
        return 0;
    }
    
    /**
     * (get訂正後執行条件)<BR>
     * リクエストデータ.執行条件より、<BR>
     * AP層で使用する訂正後執行条件（EqTypeExecutionConditionType）を返却する。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get執行条件(リクエストデータ.執行条件)にdelegateする。<BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E24095030C
     */
    public EqTypeExecutionConditionType getModifiedExecutionConditionType() throws WEB3BaseException
    {
        final String  STR_METHOD_NAME = "getModifiedExecutionConditionType() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
        (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        String l_strExecCondType = null;
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            l_strExecCondType = l_request1.execCondType;
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;
            l_strExecCondType = l_request2.execCondType;
        }
        log.exiting(STR_METHOD_NAME);
        return l_orderManager.getExecutionConditionType(l_strExecCondType);
    }
    
    /**
     * (get訂正後注文失効日)<BR>
     * リクエストデータ.注文有効期限より、<BR>
     * AP層で使用する訂正後注文失効日を返却する。<BR>
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
     * 　@　@　@　@　@　@［get注文有効期限の引数設定］<BR>
     * 　@　@　@　@　@　@　@注文有効期限：　@リクエストデータ.注文有効期限<BR>
     * 　@　@　@　@　@　@　@銘柄コード：　@(*)株式銘柄.銘柄コード<BR>
     * 　@　@　@　@　@　@　@市場コード：　@(*)市場.市場コード<BR>
     * <BR>
     * (*)　@２－１で取得した注文単位オブジェクト配列の0番目の要素より取得<BR>
     * <BR>
     * @@return Date<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E24B1101C4
     */
    public Date getModifiedExpirationDate() throws WEB3BaseException 
    {
        final String  STR_METHOD_NAME = "getModifiedExpirationDate() ";
        log.entering(STR_METHOD_NAME);

        Date l_datExpirationDate;
        long l_lngOrderId;
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_confirmRequest =
                (WEB3MarginOpenMarginChangeConfirmRequest)request;
            l_datExpirationDate = l_confirmRequest.expirationDate;
            l_lngOrderId =  Long.parseLong(l_confirmRequest.id);
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_completeRequest =
                (WEB3MarginOpenMarginChangeCompleteRequest)request;
            l_datExpirationDate = l_completeRequest.expirationDate;
            l_lngOrderId =  Long.parseLong(l_completeRequest.id);
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

        if(l_datExpirationDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3GentradeTradingTimeManagement.getOrderBizDate();
        }
        //以外、リクエストデータ.注文有効期限を返却する
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
            EqtypeOrderUnitRow l_orderUnitRow=(EqtypeOrderUnitRow)l_orderUnits[0].getDataSourceObject();
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
     * (get訂正後逆指値基準値)<BR>
     * リクエストデータ.発注条件より、AP層で使用する訂正後逆指値基準値を返却する。<BR>
     * <BR>
     * リクエスト.発注条件区分＝”指定なし”の場合<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * リクエストデータ.発注条件 ＝ ”逆指値”の場合<BR>
     * 　@リクエストデータ.逆指値用発注条件単価を返却する。<BR>
     * <BR>
     * リクエストデータ.発注条件 ＝ ”W指値”の場合<BR>
     * 　@リクエストデータ.W指値用発注条件単価を返却する。<BR>
     * @@return double<BR>
     * @@roseuid 40E240C90147
     */
    public double getModifiedStopOrderPrice() 
    {
        final String  STR_METHOD_NAME = "getModifiedStopOrderPrice() ";
        log.entering(STR_METHOD_NAME);
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //リクエストデータ.注文株数＝nullの場合
            if(WEB3OrderingConditionDef.DEFAULT.equals(l_request1.orderCondType))
            {
                return 0.0D;                  
            }
            //リクエストデータ.発注条件 ＝ ”逆指値”の場合<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //逆指値用発注条件単価を返却する
                return Double.parseDouble(l_request1.stopOrderCondPrice);   
            }
            //リクエストデータ.発注条件 ＝ ”W指値”の場合
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //W指値用発注条件単価を返却する。
                return Double.parseDouble(l_request1.wlimitOrderCondPrice);   
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //リクエストデータ.注文株数＝nullの場合
            if(WEB3OrderingConditionDef.DEFAULT.equals(l_request2.orderCondType))
            {
                return 0.0D;                   
            }
            //リクエストデータ.発注条件 ＝ ”逆指値”の場合<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //逆指値用発注条件単価を返却する
                return Double.parseDouble(l_request2.stopOrderCondPrice);   
            }
            //リクエストデータ.発注条件 ＝ ”W指値”の場合
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //W指値用発注条件単価を返却する。
                return Double.parseDouble(l_request2.wlimitOrderCondPrice);   
            }
        }
        log.exiting(STR_METHOD_NAME);
        return 0.0D;
    }
    
    /**
     * (get訂正後発注条件演算子)<BR>
     * リクエストデータ.発注条件より、
     * AP層で使用する訂正後発注条件演算子を返却する。<BR>
     * <BR>
     * リクエスト.発注条件区分＝”指定なし”の場合<BR>
     * 　@nullを返却する。<BR>
     * <BR>
     * リクエストデータ.発注条件 ＝ ”逆指値”の場合<BR>
     * 　@リクエストデータ.逆指値用発注条件演算子を返却する。<BR>
     * <BR>
     * リクエストデータ.発注条件 ＝ ”W指値”の場合<BR>
     * 　@リクエストデータ.W指値用発注条件演算子を返却する。<BR>
     * @@return String<BR>
     * @@roseuid 40E241B9029F
     */
    public String getModifiedOrderCondOperator() 
    {
        final String  STR_METHOD_NAME = "getModifiedOrderCondOperator() ";
        log.entering(STR_METHOD_NAME);
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //リクエスト.発注条件区分＝”指定なし”の場合
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_request1.orderCondType))
            {
                //return null
                return null;   
            }
            //リクエストデータ.発注条件 ＝ ”逆指値”の場合<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //逆指値用発注条件演算子を返却する
                return l_request1.stopOrderCondOperator;   
            }
            //リクエストデータ.発注条件 ＝ ”W指値”の場合
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request1.orderCondType))
            {
                //W指値用発注条件演算子を返却する。
                return l_request1.wlimitOrderCondOperator;   
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //リクエスト.発注条件区分＝”指定なし”の場合
            if (WEB3OrderingConditionDef.DEFAULT.equals(l_request2.orderCondType))
            {
                //return null
                return null;   
            }
            //リクエストデータ.発注条件 ＝ ”逆指値”の場合<
            else if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //逆指値用発注条件演算子を返却する
                return l_request2.stopOrderCondOperator;   
            }
            //リクエストデータ.発注条件 ＝ ”W指値”の場合
            else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_request2.orderCondType))
            {
                //W指値用発注条件演算子を返却する。
                return l_request2.wlimitOrderCondOperator;   
            }
        }
        log.exiting(STR_METHOD_NAME);    
        return null;
    }
    
    /**
     * (is出来るまで注文)<BR>
     * リクエストデータ.注文期限区分より、
     * 訂正入力内容が「出来るまで注文」かどうかを判定する。<BR>
     * <BR>
     * リクエストデータ.注文期限区分＝”出来るまで注文”の場合はtrueを、<BR>
     * リクエストデータ.注文期限区分＝”当日限り”の場合はfalseを返却する。<BR>
     * @@return boolean<BR>
     * @@roseuid 40E4E7E7005A
     */
    public boolean isCarriedOrder() 
    {
        final String  STR_METHOD_NAME = "isCarriedOrder()";
        log.entering(STR_METHOD_NAME); 
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request1 = (WEB3MarginOpenMarginChangeConfirmRequest)request;
            //リクエストデータ.注文期限区分＝”出来るまで注文”の場合はtrue
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request1.expirationDateType))
            {
                return true;   
            }
            //リクエストデータ.注文期限区分＝”当日限り”の場合はfalseを返却する。
            else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request1.expirationDateType))
            {
                return false;
            }
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request2 = (WEB3MarginOpenMarginChangeCompleteRequest)request;    
            //リクエストデータ.注文期限区分＝”出来るまで注文”の場合はtrue
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_request2.expirationDateType))
            {
                return true;   
            }
            //リクエストデータ.注文期限区分＝”当日限り”の場合はfalseを返却する。
            else if (WEB3OrderExpirationDateTypeDef.DAY_LIMIT.equals(l_request2.expirationDateType))
            {
                return false;
            }
        } 
        log.exiting(STR_METHOD_NAME);   
        return false;       
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
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            WEB3MarginOpenMarginChangeConfirmRequest l_request =
                (WEB3MarginOpenMarginChangeConfirmRequest)request;

            l_strWlimitExecCondType = l_request.wlimitExecCondType;
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            WEB3MarginOpenMarginChangeCompleteRequest l_request =
                (WEB3MarginOpenMarginChangeCompleteRequest)request;

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
        if (request instanceof WEB3MarginOpenMarginChangeConfirmRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginChangeConfirmRequest)request).expirationDate;
        }
        else if (request instanceof WEB3MarginOpenMarginChangeCompleteRequest)
        {
            l_datExpirationDate = ((WEB3MarginOpenMarginChangeCompleteRequest)request).expirationDate;
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
            // this.get訂正後注文失効日()の戻り値を返却する。
            l_datExpirationDate = this.getModifiedExpirationDate();

            log.exiting(STR_METHOD_NAME);
            return l_datExpirationDate;
        }
    }
}
@
