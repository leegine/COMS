head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccEquityChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）現物株式注文訂正完了リクエスト(WEB3SuccEquityChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/7  魏馨(中訊) 新規作成
                 : 2007/01/10 徐宏偉(中訊) モデル214
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (（連続）現物株式注文訂正完了リクエスト)<BR>
 * （連続）現物株式注文訂正完了リクエスト。<BR>
 * 
 * @@ author 魏馨 <BR>
 * @@ version 1.0 <BR>
 */
public class WEB3SuccEquityChangeCompleteRequest extends WEB3EquityChangeCompleteRequest 
{
    /**
     * (ログ出力ユーティリティ)。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccEquityChangeCompleteRequest.class); 

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511041000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_equityChangeComplete";
    
    /**
     * (確認時の概算受渡代金)<BR>
     * 確認時の概算受渡代金<BR>
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
     * @@roseuid 4369CC82002E
     */
    public WEB3SuccEquityChangeCompleteRequest() 
    {
     
    }

    /**
     * レスポンスデータを作成する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 40602AEA033F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccEquityChangeCompleteResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@確認時概算受渡代金チェック<BR>   
     * 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、 <BR>
     * 　@例外をスローする。 <BR>
     * <BR>
     * 　@  ・null <BR>
     * 　@　@・数字以外 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_02292<BR>
     * <BR>
     * ３）　@連続注文単価調整値情報チェック<BR>
     * 　@３－１）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。<BR>
     * <BR>
     * 　@３－２）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の<BR>
     * 　@　@　@　@　@　@例外をthrowする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_02254<BR>
     * <BR>
     * ４）　@連続注文・注文条件チェック<BR>
     * 　@super.validate連続注文()をコールする。<BR>
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4337BF4A00ED
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME ="validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@super.validate()をコールする。
        super.validate();
                
        // ２）　@確認時概算受渡代金チェック
        if ((this.estimatedPrice == null) || (!WEB3StringTypeUtility.isInteger(this.estimatedPrice)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02292,
            this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // ３）　@連続注文単価調整値情報チェック
            // ３－１）　@連続注文単価調整値情報≠nullの場合、
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();
            // ３－２）　@連続注文単価調整値情報≠nullの場合、注文単価区分≠"成行"の場合
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                this.getClass().getName() + "." + STR_METHOD_NAME);                        
            }
        }
        
        // ４）　@連続注文・注文条件チェック
        super.validateSuccOrder();
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
