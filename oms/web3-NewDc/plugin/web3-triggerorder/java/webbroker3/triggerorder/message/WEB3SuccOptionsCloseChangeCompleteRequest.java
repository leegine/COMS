head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.49.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccOptionsCloseChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）株価指数オプション訂正返済完了リクエスト(WEB3SuccOptionsCloseChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/14 金シュ (中訊) 新規作成 モデル No.263,287,305
Revision History : 2008/04/14 楊夫志 (中訊) 仕様変更 モデル307
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.message.WEB3OptionsCloseMarginChangeCompleteRequest;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (（連続）株価指数オプション訂正返済完了リクエスト)<BR>
 * （連続）株価指数オプション訂正返済完了リクエストクラス<BR>
 * <BR>
 * @@author 金シュ
 * @@version 1.0
 */
public class WEB3SuccOptionsCloseChangeCompleteRequest extends WEB3OptionsCloseMarginChangeCompleteRequest
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SuccOptionsCloseChangeCompleteRequest.class);

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200803141428L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "succ_options_close_change_complete";

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
     * 調整後単価。 <BR>
     * ±指値が指定された場合のみセット。<BR>
     */
    public String afterAdjustmentPrice;

    /**
     * @@roseuid 47D9F2C90134
     */
    public WEB3SuccOptionsCloseChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SuccOptionsCloseChangeCompleteResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@確認時概算受渡代金チェック<BR>
     * 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、<BR>
     * 　@例外をスローする。<BR>
     * <BR>
     * 　@　@・null  <BR>
     * 　@　@・数字以外  <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@ tag: BUSINESS_ERROR_02292<BR>
     * <BR>
     * ２）　@連続注文単価調整値情報チェック<BR>
     * 　@２－１）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。<BR>
     * <BR>
     * 　@２－２）　@連続注文単価調整値情報≠nullの場合、<BR>
     * 　@　@　@　@　@　@注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の<BR>
     * 　@　@　@　@　@　@例外をthrowする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@ tag: BUSINESS_ERROR_02254<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B1069C0290
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //1）　@確認時概算受渡代金チェック
        // 　@this.確認時概算受渡代金の値が以下のいずれかに該当する場合は、
        // 　@例外をthrowする。
        // 　@　@・null
        // 　@　@・数字以外
        if (this.estimatedPrice == null || !WEB3StringTypeUtility.isInteger(this.estimatedPrice))
        {
            log.debug("確認時概算受渡代金の値が不正です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02292,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "確認時概算受渡代金の値が不正です。");
        }

        //2）　@連続注文単価調整値情報チェック
        // 　@２－１）　@連続注文単価調整値情報≠nullの場合、
        // 　@　@　@　@　@　@連続注文単価調整値情報.validate()をコールする。
        if (this.priceAdjustmentValueInfo != null)
        {
            this.priceAdjustmentValueInfo.validate();

            //２－２）　@連続注文単価調整値情報≠nullの場合、
            //        注文単価区分≠"成行"の場合は「単価調整値と注文単価区分の指定が不整合」の
            //        例外をthrowする。
            if (!WEB3OrderPriceDivDef.MARKET_PRICE.equals(this.orderPriceDiv))
            {
                log.debug("単価調整値と注文単価区分の指定が不整合です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02254,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "単価調整値と注文単価区分の指定が不整合です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validateAT既存残取引)<BR>
     * 反対取引以外の場合、当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * （連続注文用のメソッド）<BR>
     * <BR>
     * １）　@スーパークラスのvalidateメソッドをコールする。<BR>
     * <BR>
     * ２）　@連続注文・注文条件チェック<BR>
     * 　@スーパークラスのvalidate連続注文メソッドをコールする。<BR>
     * @@roseuid 47B1069F0119
     */
    public void validateATExistingRemainderTrading() throws WEB3BaseException
    {
        //１）　@スーパークラスのvalidateメソッドをコールする。
        super.validate();

        //２）　@連続注文・注文条件チェック
        // 　@スーパークラスのvalidate連続注文メソッドをコールする。
        super.validateSuccOrder();

    }
}
@
