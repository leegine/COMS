head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.25.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3TraderAccountInfosSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : CCオペレータ対象顧客一覧ソートキークラス(WEB3TraderAccountInfosSortKey.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/07/23 周墨洋 (中訊) 新規作成・モデルNo.039
*/

package webbroker3.login.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (CCオペレータ対象顧客一覧ソートキー)<BR>
 * CCオペレータ対象顧客一覧ソートキークラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3TraderAccountInfosSortKey extends Message
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3TraderAccountInfosSortKey.class);

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名<BR>
     * をキー項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     */
    public String keyItem;

    /**
     * (昇順/降順)<BR>
     * A：昇順<BR>
     * D：降順<BR>
     */
    public String ascDesc;

    /**
     * (CCオペレータ対象顧客一覧ソートキー)<BR>
     * コンストラクタ<BR>
     */
    public WEB3TraderAccountInfosSortKey()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * <BR>
     * １）　@キー項目チェック<BR>
     * 　@１－１）　@this.キー項目 == nullの場合、例外をスローする。<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）　@昇順／降順チェック<BR>
     * 　@２－１）　@this.昇順／降順 == nullの場合、 例外をスローする。<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_00087<BR>
     * 　@２－２）　@this.昇順／降順が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@・"昇順"<BR>
     * 　@　@　@・"降順"<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@キー項目チェック
        //１－１）　@this.キー項目 == nullの場合、例外をスローする
        if (this.keyItem == null)
        {
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }

        //２）　@昇順／降順チェック
        //２－１）　@this.昇順／降順 == nullの場合、 例外をスローする。
        if (this.ascDesc == null)
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }

        //２－２）　@this.昇順／降順が以下の値以外の場合、例外をスローする。
        //・"昇順"
        //・"降順"
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc) && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + STR_METHOD_NAME,
                "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
