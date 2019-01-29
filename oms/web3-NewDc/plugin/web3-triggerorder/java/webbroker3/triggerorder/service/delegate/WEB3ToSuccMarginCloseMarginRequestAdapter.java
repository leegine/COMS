head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.02.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginCloseMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引返済リクエストアダプタ(WEB3ToSuccMarginCloseMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/18　@呉　@鈞(中訊) 新規作成
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
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.service.delegate.WEB3MarginCloseMarginRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginCloseConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * （（連続）信用取引返済リクエストアダプタ)<BR>
 * （連続）信用取引返済リクエストアダプタクラス<BR>
 * 
 * @@author 呉　@鈞(中訊)
 * @@version 1.0
 */
public class WEB3ToSuccMarginCloseMarginRequestAdapter extends WEB3MarginCloseMarginRequestAdapter 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginCloseMarginRequestAdapter.class);
    
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
     * ※概算受渡代金計算結果.get計算単価()で取得する。<BR>
     */
    public double price;

    /**
     * @@roseuid 436ACF6900EA
     */
    public WEB3ToSuccMarginCloseMarginRequestAdapter() 
    {
     
    }
    
    /**
     * （連続）信用取引返済リクエストアダプタインスタンスを生成する。<BR>
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
     * @@return WEB3MarginCloseMarginRequestAdapter
     * @@roseuid 433246D203A3
     */
    public static WEB3MarginCloseMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@本インスタンスを生成しする。
        WEB3ToSuccMarginCloseMarginRequestAdapter l_adapter = new WEB3ToSuccMarginCloseMarginRequestAdapter();
 
        //2)　@親注文の注文単位オブジェクトを取得する。
        //　@　@連続注文マネージャImpl.get株式親注文の注文単位(
        //　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) l_request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) l_request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginCloseMarginRequestAdapter" + "." + STR_METHOD_NAME, 
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
     * 　@※親注文が成行注文の場合、時価を基準とした執行単価を計算しているため、<BR>
     * 　@※処理中の時価の変動を考慮し、執行単価を記憶しておく。<BR>
     * <BR>
     * 　@---------------------------------------------------------<BR>
     * 　@＜get株式予約注文執行単価()：引数設定仕様＞<BR>
     * <BR>
     * 　@親注文の注文単位：　@親注文の注文単位<BR>
     * 　@指値：　@super.get単価()の戻り値<BR>
     * 　@単価調整値：　@リクエストデータ.単価調整値情報==nullの場合は、null。<BR>
     * 　@　@　@　@　@　@　@　@　@　@以外、リクエストデータ.単価調整値情報.get単価調整値()。<BR>
     * 　@株式取引銘柄：　@親注文の注文単位.getTradedProduct()<BR>
     * 　@---------------------------------------------------------<BR>
     * <BR>
     * 　@１－２）this.単価を返却する。 <BR>
     * <BR>
     * ２）　@完了リクエストの場合<BR>
     * <BR>
     * 　@リクエスト.単価調整値情報==null（指値／成行）の場合は、<BR>
     * super.get単価()の戻り値を返却する。 <BR>
     * 　@リクエスト.単価調整値情報≠null（±指値指定）の場合は、<BR>
     * リクエスト.調整後単価を返却する。 <BR>
     * 　@※調整後単価がnullの場合は、「執行単価がnull」の例外をthrowする。 
     * 　@ 　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@ 　@ 　@tag:   BUSINESS_ERROR_02707<BR>
     * 　@※調整後単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。<BR>
     * 　@ 　@ 　@class: WEB3BusinessLayerException<BR>
     * 　@ 　@ 　@tag:   BUSINESS_ERROR_02298<BR>
     * @@return double
     * @@roseuid 433246D203C2
     */
    public double getPrice() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPrice()";
        log.entering(STR_METHOD_NAME);

        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        if (super.request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) super.request;
            if (this.price == 0)
            {
                Double l_priceAdjustValue = null;
                if (l_confirmRequest.priceAdjustmentValueInfo != null)
                {
                    l_priceAdjustValue = new Double(
                        l_confirmRequest.priceAdjustmentValueInfo.getPriceAdjustmentValue());
                }  
                  
                this.price = l_orderManager.getReserveEqtypeOrderExecPrice(
                    this.parentOrderUnit,
                    super.getPrice(),
                    l_priceAdjustValue,
                    (WEB3EquityTradedProduct) this.parentOrderUnit.getTradedProduct());
            }
            log.exiting(STR_METHOD_NAME); 
            return this.price;
        }
        else if (super.request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) super.request;
            
            if (l_completeRequest.priceAdjustmentValueInfo == null)
            {
                log.exiting(STR_METHOD_NAME); 
                // super.get単価()の戻り値を返却する
                return super.getPrice();
            }      
            else
            {
                //　@リクエスト.単価調整値情報≠null（±指値指定）の場合は、リクエスト.調整後単価を返却する。
                //  ※調整後単価がnullの場合は、「執行単価がnull」の例外をthrowする。 
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
                "WEB3ToSuccMarginCloseMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
    }
    
    /**
     * (get建株)<BR>
     * 建株オブジェクトを返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@this.is反対売買() == trueの場合、<BR>
     * 　@連続注文マネージャImpl.create建株()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create建株()に指定する引数]<BR>
     * 　@　@注文単位：　@this.親注文の注文単位<BR>
     * 　@　@<BR>
     * ２）　@既存残に対する返済（１）以外）の場合、<BR>
     * 　@super.get建株()をコールし、戻り値を返却する。<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 433246D203E1
     */
    public WEB3EquityContract getContract() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getContract()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        
        //1) this.is反対売買() == trueの場合、
        // 　@連続注文マネージャImpl.create建株()をコールし、
        // 　@戻り値を返却する。
        WEB3EquityContract l_equityContract = null;
        if (this.isReversingOrder())
        {
            l_equityContract = l_orderManager.createContract(this.parentOrderUnit);
        }
        else 
        {
            //2) 　@既存残に対する返済（１）以外）の場合、
            // 　@super.get建株()をコールし、戻り値を返却する。
            l_equityContract = super.getContract();
        }
        log.exiting(STR_METHOD_NAME);
        return l_equityContract;
    }
    
    /**
     * (is反対売買)<BR>
     * 反対売買かどうか判別する。<BR>
     * <BR>
     * リクエストデータ.連続注文共通情報.連続注文取引区分=="信用返済（前提注文）"の場合は、<BR>
     * trueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4332486402B8
     */
    public boolean isReversingOrder() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " isReversingOrder()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (super.request instanceof WEB3SuccMarginCloseConfirmRequest)
        {
            WEB3SuccMarginCloseConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginCloseConfirmRequest) super.request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (super.request instanceof WEB3SuccMarginCloseCompleteRequest)
        {
            WEB3SuccMarginCloseCompleteRequest l_completeRequest = 
                (WEB3SuccMarginCloseCompleteRequest) super.request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginCloseMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        if (WEB3ReserveOrderTradingTypeDef.SETTLE_CONTRACT_ASSUMPTION_ORDER.equals(
            l_succCommonInfo.succTradingType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
