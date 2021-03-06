head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.19.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション訂正新規建完了リクエスト(WEB3OptionsOpenMarginChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数オプション訂正新規建完了リクエスト)<BR>
 * 株価指数オプション訂正新規建完了リクエストクラス<BR>                                                                    
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginChangeCompleteRequest extends WEB3OptionsCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_openMarginChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406141551L;

    /**
     * (ＩＤ)<BR>
     * 注文ＩＤ
     */
    public String id;

    /**
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * (確認時単価)<BR>
     * 確認レスポンスで送信した値。
     */
    public String checkPrice;

    /**
     * (確認時発注日)<BR>
     * 確認レスポンスで送信した値。
     */
    public Date checkDate;

    /**
     * @@roseuid 40C0A8E703C8
     */
    public WEB3OptionsOpenMarginChangeCompleteRequest()
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
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ３）　@注文数量チェック<BR>
     * 　@３−１）this.注文数量がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00074<BR>
     * 　@３−２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00075<BR>
     * 　@３−３）this.注文数量が≦０の値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00076<BR>
     * 　@３−４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00077<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40694D1E0241
     */
    public void validate() throws WEB3BaseException
    {
        //スーパークラスのvalidateメソッドを呼び出す
        super.validate();

        //ＩＤチェック<BR>
        //this.ＩＤがnullの値であれば例外をスローする。<BR>
        //class: WEB3BusinessLayerException<BR>
        //tag:   BUSINESS_ERROR_00080<BR>
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }

        //３）　@注文数量チェック<BR>
        //　@３−１）this.注文数量がnullの値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00074<BR>
        if (WEB3StringTypeUtility.isEmpty(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00074, 
                getClass().getName() + "validate",
                "注文数量を入力してください。");
        }

        //　@３−２）this.注文数量が数字以外の値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00075<BR>
        if (!WEB3StringTypeUtility.isNumber(this.opOrderQuantity))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00075, 
                getClass().getName() + "validate",
                "注文数量が数字以外の値である。");
        }

        //　@３−３）this.注文数量が≦０の値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00076<BR>
        if (Long.parseLong(this.opOrderQuantity) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00076, 
                getClass().getName() + "validate",
                "注文数量が0以下の値である。");
        }

        //　@３−４）this.注文数量が５桁を超える値であれば例外をスローする。<BR>
        //  class: WEB3BusinessLayerException<BR>
        //  tag:   BUSINESS_ERROR_00077<BR>
        if (WEB3StringTypeUtility.getByteLength(this.opOrderQuantity) > 5)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00077, 
                getClass().getName() + "validate",
                "注文数量が５桁を超えました。");
        }

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsOpenMarginChangeCompleteResponse(this);
    }

}
@
