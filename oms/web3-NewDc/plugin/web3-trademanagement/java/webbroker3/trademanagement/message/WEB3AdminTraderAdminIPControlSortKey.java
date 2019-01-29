head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP一覧ソートキー(WEB3AdminTraderAdminIPControlSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.trademanagement.define.WEB3AdminTMKeyItemDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・ログイン拒否IP一覧ソートキー)<BR>
 * 管理者・ログイン拒否IP一覧ソートキークラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlSortKey extends Message
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlSortKey.class);

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * 昇順／降順<BR>
     * <BR>
     * A：　@昇順<BR>
     * D：　@降順<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 48D75CD703E6
     */
    public WEB3AdminTraderAdminIPControlSortKey()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@キー項目<BR>
     * <BR>
     * 　@１－１）　@this.キー項目がnullの場合、例外をthrowする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * 　@１－２）　@this.キー項目に以下の項目以外が設定されている場合、例外をthrowする。<BR>
     * 　@　@・IPアドレス<BR>
     * 　@　@・適用開始日時<BR>
     * 　@　@・ステータス<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * ２）　@昇順／降順<BR>
     * <BR>
     * 　@２－１）　@this.昇順／降順がnullの場合、例外をthrowする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * 　@２－２）　@this.昇順／降順に以下の値以外が設定されている場合、例外をthrowする。 <BR>
     * 　@　@・A：昇順<BR>
     * 　@　@・D：降順<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_00088<BR>
     * @@roseuid 48BE53390315
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@キー項目
        //　@１－１）　@this.キー項目がnullの場合、例外をthrowする。
        if (this.keyItem == null)
        {
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }

        //　@１－２）　@this.キー項目に以下の項目以外が設定されている場合、例外をthrowする。
        //　@　@・IPアドレス
        //　@　@・適用開始日時
        //　@　@・ステータス
        if (!WEB3AdminTMKeyItemDef.IP_ADDRESS.equals(this.keyItem)
            && !WEB3AdminTMKeyItemDef.START_DATE.equals(this.keyItem)
            && !WEB3AdminTMKeyItemDef.STATUS.equals(this.keyItem))
        {
            log.debug("ソートキーのキー項目の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目の値が存在しないコード値です。");
        }

        //２）　@昇順／降順
        //
        //　@２－１）　@this.昇順／降順がnullの場合、例外をthrowする。
        if (this.ascDesc == null)
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }

        //　@２－２）　@this.昇順／降順に以下の値以外が設定されている場合、例外をthrowする。
        //　@　@・A：昇順
        //　@　@・D：降順
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
