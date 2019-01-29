head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.05.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginOpenMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引新規建リクエストアダプタ(WEB3ToSuccMarginOpenMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08　@呉　@鈞(中訊) 新規作成
Revesion History : 2007/01/11  齊  珂(中訊) 仕様変更モデル215
Revesion History : 2007/01/17  徐宏偉(中訊) モデル221
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.service.delegate.WEB3MarginOpenMarginRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginOpenConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引新規建リクエストアダプタ)<BR>
 * （連続）信用取引新規建リクエストアダプタクラス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginOpenMarginRequestAdapter extends WEB3MarginOpenMarginRequestAdapter
{
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginOpenMarginRequestAdapter.class);
    
    /**
     * (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト<BR>
     */
    public EqTypeOrderUnit parentOrderUnit;
    
    /**
     * (単価)<BR>
     * 単価。<BR>
     * （指値／0（＝成行）／執行単価（±指値））<BR>
     * ※成行注文の場合の概算受渡代金計算に使用した時価は、<BR>
     * ※信用新規建内容.get計算単価()で取得する。<BR>
     */
    public double price;
 
    /**
     * @@roseuid 436ACF650203
     */
    public WEB3ToSuccMarginOpenMarginRequestAdapter() 
    {
     
    }
    
    /**
     * （連続）信用取引新規建リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成しする。<BR>
     * ２）　@親注文の注文単位オブジェクトを取得する。<BR>
     * 　@　@連続注文マネージャ.get株式親注文の注文単位(<BR>
     * 　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。<BR>
     * <BR>
     * ３）　@生成したインスタンスに、引数のリクエスト、<BR>
     * 　@　@及び取得した親注文の注文単位オブジェクトをセットする。<BR>
     * 　@　@プロパティの単価には0をセットする。<BR>
     * <BR>
     * ４）　@インスタンスを返却する。<BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト<BR>
     * @@return WEB3MarginOpenMarginRequestAdapter
     * @@roseuid 432FEC6F02A2
     */
    public static WEB3MarginOpenMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@本インスタンスを生成しする。
        WEB3ToSuccMarginOpenMarginRequestAdapter l_adapter = new WEB3ToSuccMarginOpenMarginRequestAdapter();
        
        //2)　@親注文の注文単位オブジェクトを取得する。
        //　@　@連続注文マネージャImpl.get株式親注文の注文単位(
        //　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            WEB3SuccMarginOpenConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginOpenConfirmRequest) l_request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            WEB3SuccMarginOpenCompleteRequest l_completeRequest = 
                (WEB3SuccMarginOpenCompleteRequest) l_request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginOpenMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //３）　@生成したインスタンスに、引数のリクエストデータ、
        //　@　@及び取得した親注文の注文単位オブジェクトをセットする。
        l_adapter.request = l_request;
        l_adapter.parentOrderUnit = l_orderUnit;
        l_adapter.price = 0;
        
        //４）　@インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);   
        return l_adapter;
    }
    
    /**
     * (get単価)<BR>
     * 単価をリクエストオブジェクトより取得する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@確認リクエストの場合<BR>
     * <BR>
     * 　@this.単価 == 0の場合は、 <BR>
     * 　@連続注文マネージャ.get株式予約注文執行単価()をコールし、<BR>
     * 　@戻り値をthis.単価にセットする。<BR>
     * 　@※親注文が成行注文の場合、時価を基準とした執行単価を<BR>
     * 計算しているため、<BR>
     * 　@※処理中の時価の変動を考慮し、執行単価を記憶しておく。<BR>
     * <BR>
     * 　@---------------------------------------------------------<BR>
     * 　@＜get株式予約注文執行単価()：引数設定仕様＞<BR>
     * <BR>
     * 　@親注文の注文単位：　@親注文の注文単位<BR>
     * 　@指値：　@super.get単価()の戻り値<BR>
     * 　@単価調整値：　@リクエストデータ.単価調整値情報==nullの場合は、null。<BR>
     * 　@　@　@　@　@　@　@　@　@　@以外、リクエストデータ.単価調整値情報.get単価調整値()。<BR>
     * <BR>
     * 　@株式取引銘柄：　@親注文の注文単位.getTradedProduct()<BR>
     * 　@---------------------------------------------------------<BR>
     * <BR>
     * 　@１－２）this.単価を返却する。 <BR>
     * <BR>
     * ２）　@完了リクエストの場合<BR>
     * <BR>
     * 　@リクエスト.単価調整値情報==null（指値／成行）の場合は、super.get単価()<BR>
     * の戻り値を返却する。<BR>
     * 　@リクエスト.単価調整値情報≠null（±指値指定）の場合は、<BR>
     * リクエスト.調整後単価を返却する。<BR>
     * 　@※調整後単価がnullの場合は、「執行単価がnull」の例外をthrowする。<BR> 
     * 　@ 　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@ 　@ 　@tag:   BUSINESS_ERROR_02707<BR>
     *   ※確認時単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。<BR>
     * 　@ 　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@ 　@ 　@tag:   BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@roseuid 432FEC6F032F
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPrice()";
        log.entering(STR_METHOD_NAME);
        
        //1) 確認リクエストの場合
        // 　@this.単価 == 0の場合は、
        // 　@連続注文マネージャ.get株式予約注文執行単価()をコールし、
        // 　@戻り値をthis.単価にセットする。
        // 　@※親注文が成行注文の場合、時価を基準とした執行単価を
        // 計算しているため、
        // 　@※処理中の時価の変動を考慮し、執行単価を記憶しておく。
        // 　@＜get株式予約注文執行単価()：引数設定仕様＞
        // 　@親注文の注文単位：　@親注文の注文単位
        // 　@指値：　@super.get単価()の戻り値
        // 　@単価調整値：　@リクエストデータ.単価調整値情報==nullの場合は、null。
        // 　@　@　@　@　@　@　@　@　@　@以外、リクエストデータ.単価調整値情報.get単価調整値()。
        // 　@株式取引銘柄：　@親注文の注文単位.getTradedProduct()
        // 　@---------------------------------------------------------
        // 　@this.単価を返却する。
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        if (super.request instanceof WEB3SuccMarginOpenConfirmRequest)
        {
            WEB3SuccMarginOpenConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginOpenConfirmRequest) super.request;
            
                if (this.price == 0)
                {
                    if (l_confirmRequest.priceAdjustmentValueInfo == null)
                    {
                        this.price = l_orderManager.getReserveEqtypeOrderExecPrice(
                            this.parentOrderUnit,
                            super.getPrice(),
                            null,
                            (WEB3EquityTradedProduct) this.parentOrderUnit.getTradedProduct());
                    }  
                    else 
                    {
                        this.price = l_orderManager.getReserveEqtypeOrderExecPrice(
                            this.parentOrderUnit,
                            super.getPrice(),
                            new Double(l_confirmRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue()),
                            (WEB3EquityTradedProduct) this.parentOrderUnit.getTradedProduct()); 
                    }
                }
                log.exiting(STR_METHOD_NAME); 
                return this.price;
        }
        else if (super.request instanceof WEB3SuccMarginOpenCompleteRequest)
        {
            WEB3SuccMarginOpenCompleteRequest l_completeRequest = (WEB3SuccMarginOpenCompleteRequest) super.request;
            
            if (l_completeRequest.priceAdjustmentValueInfo == null)
            {
                log.exiting(STR_METHOD_NAME); 
                return super.getPrice();
            }
            else 
            {
                if (l_completeRequest.afterAdjustmentPrice == null)
                {
                    log.debug("執行単価がnull。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02707,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "執行単価がnull。"); 
                }
                
                double l_dblAfterAdjustmentPrice = 
                    Double.parseDouble(l_completeRequest.afterAdjustmentPrice);
                
                if (l_dblAfterAdjustmentPrice <= 0)
                {
                    log.debug("執行単価が0以下。 " + l_dblAfterAdjustmentPrice);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02298,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "執行単価が0以下。 " + l_dblAfterAdjustmentPrice);
                }            
                log.exiting(STR_METHOD_NAME); 
                //リクエスト.調整後単価を返却する
                return l_dblAfterAdjustmentPrice;
            }
        }
        else 
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
    }
}
@
