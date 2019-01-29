head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListInqSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS申込客一覧問合せソートキークラス(WEB3AdminInformPTSAccountListInqSortKey.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・PTS申込客一覧問合せソートキークラス)<BR>
 * 管理者・PTS申込客一覧問合せソートキークラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListInqSortKey extends Message
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListInqSortKey.class);

    /**
     * (キー項目)<BR>
     * キー項目
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * 昇順／降順
     * <BR>
     * A： 昇順<BR>
     * D： 降順<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 47C522D4028D
     */
    public WEB3AdminInformPTSAccountListInqSortKey()
    {

    }

    /**
     * 整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@キー項目<BR>
     * <BR>
     * 　@１－１）　@キー項目がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00085<BR>
     * <BR>
     * 　@１－２）　@キー項目に以下の項目以外が設定されている場合、例外をthrowする。<BR>
     * 　@　@・部店コード<BR>
     * 　@　@・顧客コード<BR>
     * 　@　@・申込日時<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ２）　@昇順／降順<BR>
     * <BR>
     * 　@２－１）　@昇順／降順がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00087<BR>
     * <BR>
     * 　@２－２）　@昇順／降順に以下の値以外が設定されている場合、例外をthrowする。<BR>
     * 　@　@・A：昇順<BR>
     * 　@　@・D：降順<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B541FC037C
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@キー項目
        //１－１）　@キー項目がnullの場合、例外をthrowする。
        if (this.keyItem == null)
        {
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }

        //１－２）　@キー項目に以下の項目以外が設定されている場合、例外をthrowする。
        //　@　@・部店コード
        //　@　@・顧客コード
        //　@　@・申込日時
        if (!WEB3InformKeyItemDef.BRANCH_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.ACCOUNT_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.APPLY_DATE.equals(this.keyItem))
        {
            log.debug("ソートキーのキー項目の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目の値が存在しないコード値です。");
        }

        //２）　@昇順／降順
        //２－１）　@昇順／降順がnullの場合、例外をthrowする。
        if (this.ascDesc == null)
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }

        //２－２）　@昇順／降順に以下の値以外が設定されている場合、例外をthrowする。
        //　@・A：昇順
        //　@・D：降順
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc)
            && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
