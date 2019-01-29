head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建完了リクエストクラス(WEB3FuturesOpenMarginChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強(中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数先物訂正新規建完了リクエスト)<BR>
 * 株価指数先物訂正新規建完了リクエストクラス
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeCompleteRequest extends WEB3FuturesCommonRequest 
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOpenMarginChangeCompleteRequest.class);   
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211329L;
    /**
     * (注文ＩＤ)
     */
    public String id;
    
    /**
     * (暗証番号)
     */
    public String password;
    
    /**
     * (確認時単価)<BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public String checkPrice;
    
    /**
     * (確認時発注日)<BR>
     * 確認レスポンスで送信した値。<BR>
     */
    public Date checkDate;
    
    /**
     * @@roseuid 40F7AE1102AF
     */
    public WEB3FuturesOpenMarginChangeCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドを呼び出す<BR>
     * <BR>
     * ２）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@注文数量チェック<BR>
     * 　@３－１）this.注文数量がnullの値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00074<BR>
     * 　@３－２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00075<BR>
     * 　@３－３）this.注文数量≦０ の場合、例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00076<BR>
     * 　@３－４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2CEEF005F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidateメソッドを呼び出す
        super.validate();

        //２）ＩＤチェック<BR>
        //this.ＩＤがnullの値であれば例外をスローする。<BR>
        //class: WEB3BusinessLayerException<BR>
        //tag:   BUSINESS_ERROR_00080<BR>
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "ＩＤがnullの値である。");
        }

        //３）　@注文数量チェック<BR>
        //　@３－１）this.注文数量がnullの値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00074<BR>
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量を入力してください。");
        }

        //　@３－２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00075<BR>
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が数字以外の値である。");
        }

        //　@３－３）this.注文数量が≦０の値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00076<BR>
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が0以下の値である。");
        }

        //　@３－４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00077<BR>
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が５桁を超えました。");
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginChangeCompleteResponse(this);
    }
    
}
@
