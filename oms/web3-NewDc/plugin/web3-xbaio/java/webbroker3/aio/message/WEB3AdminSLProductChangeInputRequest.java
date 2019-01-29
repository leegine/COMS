head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLProductChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 担保銘柄登録変更入力リクエスト(WEB3AdminSLProductChangeInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/14 トウ鋒鋼(中訊) 新規作成 仕様変更モデル760
Revision History : 2007/10/10 孫洪江(中訊) 仕様変更モデル801 モデル802
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (担保銘柄登録変更入力リクエスト)<BR>
 * 担保銘柄登録変更入力リクエストクラス<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3AdminSLProductChangeInputRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLProductChangeInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_sl_product_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709141040L;

    /**
     * (担保銘柄検索キー)<BR>
     * 担保銘柄検索キー<BR>
     */
    public WEB3SLProductSearchConditions searchConditions;

    /**
     * @@roseuid 46E89085002B
     */
    public WEB3AdminSLProductChangeInputRequest()
    {

    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@リクエスト.担保銘柄検索キーがnullの場合、例外をスロー<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_02917<BR>
     * <BR>
     * ２）　@リクエスト.担保銘柄検索キー.適用期間fromがnullの場合、例外をスロー<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag  : BUSINESS_ERROR_01444<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46DF671B039F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@リクエスト.担保銘柄検索キーがnullの場合、例外をスロー
        if (this.searchConditions == null)
        {
            log.debug("担保銘柄検索キーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02917,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "担保銘柄検索キーが未指定です。");
        }

        //２）　@リクエスト.担保銘柄検索キー.適用期間fromがnullの場合、例外をスロー
        if (this.searchConditions.targetPeriodFrom == null)
        {
            log.debug("日付未入力エラー(適用期間From)");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01444,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "日付未入力エラー(適用期間From)");
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
        return new WEB3AdminSLProductChangeInputResponse(this);
    }
}
@
