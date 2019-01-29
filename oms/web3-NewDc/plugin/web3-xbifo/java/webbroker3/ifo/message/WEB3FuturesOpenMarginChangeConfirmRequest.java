head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物訂正新規建確認リクエストクラス(WEB3FuturesOpenMarginChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/21 李強(中訊) 新規作成
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株価指数先物訂正新規建確認リクエスト)<BR>
 * 株価指数先物訂正新規建確認リクエストクラス
 * @@author 李強
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginChangeConfirmRequest extends WEB3FuturesCommonRequest 
{
    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOpenMarginChangeConfirmRequest.class);    
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_openMarginChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407211140L;
    /**
     * (注文ＩＤ)
     */
    public String id;
    
    /**
     * @@roseuid 40F7AE11036B
     */
    public WEB3FuturesOpenMarginChangeConfirmRequest() 
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
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@注文数量チェック<BR>
     * 　@３－１）this.注文数量がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00074<BR>
     * 　@３－２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00075<BR>
     * 　@３－３）this.注文数量≦０の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00076<BR>
     * 　@３－４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00077<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2CEB0039B
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
                
        log.entering(STR_METHOD_NAME);
        super.validate();
         
        //ＩＤチェック
        //this.ＩＤがnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("this.ＩＤがnullの値であれば例外をスローする"); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00080,
                STR_METHOD_NAME,
                "ＩＤがnullの値である。"); 
        }
        
        log.debug("this.futOrderQuantity = " + this.futOrderQuantity);
        //注文数量チェック
        //this.注文数量がnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.futOrderQuantity))
        {
            log.debug("注文数量がnullの値の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074,
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量を入力してください。");
        }

        //注文数量チェック
        //this.注文数量が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.futOrderQuantity))
        {
            log.debug("注文数量が数字以外の値の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075,
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が数字以外の値である。");
        }

        //注文数量チェック
        //this.注文数量が≦０の値であれば例外をスローする。
        if (Long.parseLong(this.futOrderQuantity) <= 0)
        {
            log.debug("注文数量が≦０の値の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076,
                getClass().getName() + "." + STR_METHOD_NAME,
                "注文数量が0以下の値である。");
        }

        //注文数量チェック
        //this.注文数量が５桁を超える値であれば例外をスローする。
        if (WEB3StringTypeUtility.getByteLength(this.futOrderQuantity) > 5)
        {
            log.debug("注文数量が５桁を超える値の場合");
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
        return new WEB3FuturesOpenMarginChangeConfirmResponse(this);
    }
}
@
