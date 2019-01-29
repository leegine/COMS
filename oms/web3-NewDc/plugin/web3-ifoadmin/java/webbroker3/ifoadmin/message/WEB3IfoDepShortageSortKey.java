head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IfoDepShortageSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金不足状況ソートキー(WEB3IfoDepShortageSortKey.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (証拠金不足状況ソートキー)<BR>
 * 証拠金不足状況ソートキークラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3IfoDepShortageSortKey extends Message
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepShortageSortKey.class);

    /**
     * (キー項目)<BR>
     * キー項目 <BR>
     * <BR>
     * ※使用するキー項目はvalidate()メソッド内にて定義。<BR>
     * <BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * 昇順／降順 <BR>
     * <BR>
     * A：　@昇順 <BR>
     * D：　@降順<BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 49A748560177
     */
    public WEB3IfoDepShortageSortKey()
    {

    }

    /**
     * １）this.キー項目 == nullの場合、 <BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２） this.キー項目に下記の項目以外が <BR>
     * 　@設定されていたら、 <BR>
     * 　@「ソートキー.キー項目が未定義の値」の例外をスローする。 <BR>
     * 　@　@・"部店コード" <BR>
     * 　@　@・"顧客コード" <BR>
     * 　@　@・"請求額" <BR>
     * 　@　@・"現在未入金額" <BR>
     * 　@　@・"現在証拠金所要額" <BR>
     * 　@　@・"建玉有無フラグ" <BR>
     * 　@　@・"注文有無フラグ" <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_00086<BR>
     * 　@　@<BR>
     * ３）this.昇順／降順＝nullの場合、 <BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_00087<BR>
     * <BR>
     * ４）this.昇順／降順が下記の項目以外の場合、 <BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順” <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4990E07D01CF
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
        if (!WEB3AdminIfoSortKeyItemNameDef.BRANCH_CODE.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.ACCOUNT_CODE.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CLAIM_AMOUNT.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CUR_NON_PAY_AMT.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CUR_IFO_DEPOSIT_NECESSARY_AMT.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.CONTRACT_EXIST_FLAG.equals(this.keyItem)
            && !WEB3AdminIfoSortKeyItemNameDef.ORDER_EXIST_FLAG.equals(this.keyItem))
        {
            log.debug("ソートキーのキー項目の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーのキー項目の値が存在しないコード値です。");
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
