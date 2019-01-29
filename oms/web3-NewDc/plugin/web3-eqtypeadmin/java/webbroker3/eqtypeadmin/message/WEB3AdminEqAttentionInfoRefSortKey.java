head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注意情報照会ソートキー(WEB3AdminEqAttentionInfoRefSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 孟亞南 (中訊) 新規作成 モデルNo.217
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.eqtypeadmin.define.WEB3AdminEquitySortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (注意情報照会ソートキー)<BR>
 * 注意情報照会ソートキークラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefSortKey extends Message
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEqAttentionInfoRefSortKey.class);

    /**
     * (キー項目)<BR>
     * キー項目<BR>
     * <BR>
     * ・"注意情報種別"<BR>
     * ・"注意情報区分コード"<BR>
     * ・"情報発生日時"<BR>
     * ・"銘柄コード"<BR>
     * ・"市場コード"<BR>
     * ※第二ソートキーは「情報発生日時」。<BR>
     * <BR>
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
     * @@roseuid 49588AEF02BF
     */
    public WEB3AdminEqAttentionInfoRefSortKey()
    {

    }

    /**
     * １）this.キー項目 == nullの場合、 <BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２） this.キー項目に下記の項目以外が<BR>
     * 　@設定されていたら、「ソートキー.キー項目が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@　@　@・注意情報種別<BR>
     * 　@　@　@　@　@　@・注意情報区分コード<BR>
     * 　@　@　@　@　@　@・情報発生日時<BR>
     * 　@　@　@　@　@　@・銘柄コード<BR>
     * 　@　@　@　@　@　@・市場コード<BR>
     * 　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:　@　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ３）this.昇順／降順＝nullの場合、 <BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00087<BR>
     * <BR>
     * ４）this.昇順／降順が下記の項目以外の場合、 <BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順” <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@roseuid 4941FFC80263
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）this.キー項目 == nullの場合、
        //ソートキー.キー項目がnull」の例外をスローする。
        if (this.keyItem == null)
        {
            log.debug("ソートキーのキー項目が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目が未指定です。");
        }

        //２） this.キー項目に下記の項目以外が
        // 　@設定されていたら、
        // 　@「ソートキー.キー項目が未定義の値」の例外をスローする。
        if (this.keyItem != null)
        {
            if (!WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_TYPE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.ATTENTION_INFO_DIV_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.INFO_OCCURED_DATE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.PRODUCT_CODE.equals(this.keyItem)
                && !WEB3AdminEquitySortKeyItemNameDef.MARKET_CODE.equals(this.keyItem))
            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
        }

        //３）this.昇順／降順＝nullの場合、
        //「ソートキー.昇順／降順がnull」の例外をスローする。
        if (this.ascDesc == null)
        {
            log.debug("昇順／降順が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "昇順／降順が未指定です。");
        }

        //４）this.昇順／降順が下記の項目以外の場合、
        //「ソートキー.昇順／降順が未定義の値」の例外をスローする。
        if (this.ascDesc != null)
        {
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
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
