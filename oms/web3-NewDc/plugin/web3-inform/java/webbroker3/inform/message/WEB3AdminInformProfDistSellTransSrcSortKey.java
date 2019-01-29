head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先一覧ソートキークラス(WEB3AdminInformProfDistSellTransSrcSortKey.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.045
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
 * (利金・分配金・売却代金振込先一覧ソートキークラス)<BR>
 * 利金・分配金・売却代金振込先一覧ソートキークラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcSortKey extends Message
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminInformProfDistSellTransSrcSortKey.class);

    /**
     * (キー項目)<BR>
     * キー項目
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * A：　@昇順<BR>
     * D：　@降順
     */
    public String ascDesc;

    /**
     * @@roseuid 46559376000E
     */
    public WEB3AdminInformProfDistSellTransSrcSortKey()
    {

    }

    /**
     * 整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）キー項目<BR>
     * <BR>
     * 　@１－１）キー項目がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00085<BR>
     * <BR>
     * 　@１－２）キー項目に以下の項目以外が設定されている場合、例外をthrowする。<BR>
     * <BR>
     * 　@　@・部店コード<BR>
     * 　@　@・扱者コード<BR>
     * 　@　@・顧客コード<BR>
     * 　@　@・銘柄コード<BR>
     * 　@　@・登録日<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ２）昇順／降順<BR>
     * <BR>
     * 　@２－１）昇順／降順がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00087<BR>
     * <BR>
     * 　@２－２）昇順／降順に以下の値以外が設定されている場合、例外をthrowする。<BR>
     * <BR>
     * 　@　@・A：昇順<BR>
     * 　@　@・D：降順<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 461ADE8202DE
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １－１）キー項目がnullの場合、例外をthrowする。
        if (this.keyItem == null)
        {
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }

        // １－２）キー項目に以下の項目以外が設定されている場合、例外をthrowする。
        // ・部店コード ・扱者コード ・顧客コード ・銘柄コード ・登録日
        if (!WEB3InformKeyItemDef.BRANCH_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.TRADER_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.ACCOUNT_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.PRODUCT_CODE.equals(this.keyItem)
            && !WEB3InformKeyItemDef.REGIST_DATE.equals(this.keyItem))
        {
            log.debug("ソートキーのキー項目の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                getClass().getName() + STR_METHOD_NAME,
                "ソートキーのキー項目の値が存在しないコード値です。");
        }

        //  ２－１）昇順／降順がnullの場合、例外をthrowする。<BR>
        if (this.ascDesc == null)
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                getClass().getName() + "validate",
                "昇順／降順が未指定です。");
        }

        // ２－２）昇順／降順に以下の値以外が設定されている場合、例外をthrowする。
        // A：昇順
        // D：降順
        if (!WEB3AscDescDef.ASC.equals(this.ascDesc)
            && !WEB3AscDescDef.DESC.equals(this.ascDesc))
        {
            log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                getClass().getName() + "validate",
                "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
