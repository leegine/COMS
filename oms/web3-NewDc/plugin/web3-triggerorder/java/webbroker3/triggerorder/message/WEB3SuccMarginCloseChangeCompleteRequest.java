head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.40.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccMarginCloseChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引注文訂正返済完了リクエストクラス(WEB3SuccMarginCloseChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 鄭徳懇(中訊) 新規作成
                 : 2007/01/10 徐宏偉(中訊) モデル214
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3MarginCloseMarginChangeCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (（連続）信用取引注文訂正返済完了リクエストクラス)<BR>
 * （連続）信用取引注文訂正返済完了リクエストクラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0 
 */
public class WEB3SuccMarginCloseChangeCompleteRequest extends WEB3MarginCloseMarginChangeCompleteRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccMarginCloseChangeCompleteRequest.class);
     
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_marginCloseChangeComplete";
    
    /**
     * (確認時概算受渡代金)<BR>
     * 確認時概算受渡代金<BR>
     * <BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public String estimatedPrice;
    
    /**
     * (単価調整値情報)<BR>
     * 単価調整値情報。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public WEB3SuccPriceAdjustmentValueInfo priceAdjustmentValueInfo;

    /**
     * (調整後単価)<BR>
     * 調整後単価。<BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 4369CC4002AF
     */
    public WEB3SuccMarginCloseChangeCompleteRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccMarginCloseChangeCompleteResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@確認時概算受渡代金チェック<BR>
     * 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、 <BR>
     * 　@例外をスローする。 <BR>
     * <BR>
     * 　@  ・null <BR>
     * 　@　@・数字以外 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_02292<BR>
     * ２）　@連続注文単価調整値情報チェック<BR>
     * 　@２－１）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。<BR>
     * <BR>
     * 　@２－２）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の<BR>
     * 　@　@　@　@　@　@例外をthrowする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_02254<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 433CEAEB01AF
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@確認時概算受渡代金チェック
        //　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、 
        // 　@例外をスローする。 
        if ((this.estimatedPrice == null) || (!WEB3StringTypeUtility.isInteger(this.estimatedPrice)))
        {
            log.debug("確認時概算受渡代金の値が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02292,
                getClass().getName() + "." + STR_METHOD_NAME,
                "確認時概算受渡代金の値が不正です。");
        }
        
        // ２）　@連続注文単価調整値情報チェック
        // 　@２－１）　@連続注文単価調整値情報≠nullの場合、
        // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();
            
            //  ２－２）　@連続注文単価調整値情報≠nullの場合、
            // 　@　@　@　@　@ 注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            // 　@　@　@　@　@ 例外をthrowする。
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("単価調整値と注文単価区分の指定が不整合です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "単価調整値と注文単価区分の指定が不整合です。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateAT既存残取引)<BR>
     * 反対取引指定時の、当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （連続注文用のメソッド）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドをコールする。<BR>
     * <BR>
     * ２）　@連続注文・注文条件チェック<BR>
     * 　@スーパークラスのvalidate連続注文メソッドをコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 436567260268
     */
    public void validateATExistingRemainderTrading() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@スーパークラスのvalidateメソッドをコールする。
        super.validate();
        
        // ２）　@連続注文・注文条件チェック
        super.validateSuccOrder();
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
