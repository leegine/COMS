head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式簿価単価登録確認リクエスト(WEB3FeqBookPriceConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/16 柴双紅(中訊) 新規作成 モデルNo.373
*/
package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式簿価単価登録確認リクエスト)<BR>
 * 外国株式簿価単価登録確認リクエスト<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3FeqBookPriceConfirmRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqBookPriceConfirmRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "feq_book_price_confirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200801151156L;

    public WEB3FeqBookPriceConfirmRequest()
    {

    }

    /**
     * (保有資産ID)<BR>
     * 保有資産ID<BR>
     */
    public String assetId;

    /**
     * (計算用入力簿価金額)<BR>
     * 計算用入力簿価金額<BR>
     */
    public String aftBookAmount;

    /**
     * (残高株数)<BR>
     * 残高株数<BR>
     */
    public String balanceQuantity;

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）保有資産IDチェック<BR>
     * 　@１－１）保有資産IDがnullの場合、<BR>
     * 　@　@「保有資産IDが未入力」の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_01919<BR>
     * <BR>
     * ２）入力項目チェック<BR>
     * 　@２－１）<BR>
     * 　@　@計算用入力簿価金額 == null の場合、<BR>
     * 　@　@「必須項目未入力」の例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02980<BR>
     * <BR>
     * 　@２－２）計算用入力簿価金額チェック
     * 　@　@以下の条件に該当する場合、「計算用入力簿価金額が不正な値」の例外をスローする。<BR>
     * 　@　@　@・計算用入力簿価金額 != 数値<BR>
     * 　@　@　@・計算用入力簿価金額 < 0<BR>
     * 　@　@　@・計算用入力簿価金額の桁数 >= 12桁<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02981<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //保有資産IDがnullの場合
        //「保有資産IDが未入力」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.assetId))
        {
            log.debug("保有資産IDが未入力");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "保有資産IDが未指定(null)です。");
        }

        //計算用入力簿価金額 == null の場合、
        //「必須項目未入力」の例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.aftBookAmount))
        {
            log.debug("必須項目未入力");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02980,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "必須項目未入力。");
        }

        //計算用入力簿価金額チェック
        //以下の条件に該当する場合、「計算用入力簿価金額が不正な値」の例外をスローする。
        //　@・計算用入力簿価金額 != 数値
        //　@・計算用入力簿価金額 < 0
        //　@・計算用入力簿価金額の桁数 >= 12桁
        if (!WEB3StringTypeUtility.isNumber(this.aftBookAmount)
            || Double.parseDouble(this.aftBookAmount) < 0
            || WEB3StringTypeUtility.getNubmerLength(this.aftBookAmount) >= 12)
        {
            log.debug("計算用入力簿価金額が不正な値");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02981,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "計算用入力簿価金額が不正な値。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqBookPriceConfirmResponse(this);
    }

}
@
