head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccTradeStopSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客銘柄別取引停止一覧ソートキー (WEB3AdminPMAccTradeStopSortKey.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/22 中尾寿彦(SRA) 新規作成
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;

import webbroker3.util.WEB3LogUtility;

import webbroker3.eqtypeadmin.define.WEB3AdminKeyItemDef;

/**
 * (顧客銘柄別取引停止一覧ソートキー)<BR>
 * <BR>
 * 顧客銘柄別取引停止一覧ソートキークラス<BR>
 * <BR>
 * WEB3AdminPMAccTradeStopSortKey<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccTradeStopSortKey extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccTradeStopSortKey.class);

    /**
     * （キー項目）<BR>
     * <BR>
     * キー項目<BR>
     * <BR>
     * ・"部店コード"<BR>
     * ・"顧客コード"<BR>
     * ・"銘柄コード"<BR>
     * ・"有効期限From"<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * keyItem<BR>
     * <BR>
     * ・"Def.BRANCH_CODE"<BR>
     * ・"Def.ACCOUNT_CODE"<BR>
     * ・"Def.PRODUCT_CODE"<BR>
     * ・"Def.TERM_FROM"<BR>
     * <BR>
     */
    public String keyItem;

    /**
     * （昇順／降順）<BR>
     * <BR>
     * 昇順／降順<BR>
     * <BR>
     * A：　@昇順<BR>
     * D：　@降順<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * ascDesc<BR>
     * <BR>
     * A: Def.ASC<BR>
     * D: Def.DESC<BR>
     * <BR>
     */

    public String ascDesc;

    /**
     * @@roseuid 41FD9301033C
     */
    public WEB3AdminPMAccTradeStopSortKey()
    {

    }

    /**
     * １）this.キー項目＝nullの場合、 <BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）this.キー項目に下記の項目以外が <BR>
     * 　@　@設定されていたら、 <BR>
     * 　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。 <BR>
     * 　@　@・"部店コード"<BR>
     * 　@　@・"顧客コード"<BR>
     * 　@　@・"銘柄コード"<BR>
     * 　@　@・"有効期限From"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * ３）this.昇順／降順＝nullの場合、 <BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。 <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * ４）this.昇順／降順が下記の項目以外の場合、 <BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順” <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * 1) If this.keyItem＝null<BR>
     * 　@　@Throw the exception "sortKeys.keyItem is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * 2)If one of the following items is not set into this.keyItem<BR>
     * 　@　@Throw the exception "sortKeys.keyItem is an undefined value"<BR>
     *   ・"Def.BRANCH_CODE"<BR>
     *   ・"Def.ACCOUNT_CODE"<BR>
     *   ・"Def.PRODUCT_CODE"<BR>
     *   ・"Def.TERM_FROM"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00086<BR>
     * <BR>
     * 3) If this.ascDesc＝null<BR>
     * 　@　@Throw the exception "sortKeys.ascDesc is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * ４） If this.ascDesc is neither of the following items <BR>
     * 　@　@Throw the exception "sortKeys.ascDesc is an undefined value"<BR>
     *   ・”A: Def.ASC<BR>”
     *   ・”D: Def.DESC” <BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@roseuid 4198886F02F5
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 if keyitem is null throw Exception.
        if (keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-1 if key item is not set to Def values, throw Exception.
            if (!(WEB3AdminKeyItemDef.BRANCH_CODE.equals(keyItem))
                && !(WEB3AdminKeyItemDef.ACCOUNT_CODE.equals(keyItem))
                && !(WEB3AdminKeyItemDef.PRODUCT_CODE.equals(keyItem))
                && !(WEB3AdminKeyItemDef.TERM_FROM.equals(keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if ascdes is not equal to null, throw Exception.
        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 4-1 if ascDesc is not of def, throw Exception
            if (!(WEB3AscDescDef.ASC.equals(ascDesc)) && (!(WEB3AscDescDef.DESC.equals(ascDesc))))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
