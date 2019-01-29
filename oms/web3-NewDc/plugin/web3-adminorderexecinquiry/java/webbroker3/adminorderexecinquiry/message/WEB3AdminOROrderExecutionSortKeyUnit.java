head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.43.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminOROrderExecutionSortKeyUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文約定照会ソートキー (WEB3AdminOROrderExecutionSortKeyUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;

import webbroker3.util.WEB3LogUtility;

/**
 * (注文約定照会ソートキー)<BR>
 * <BR>
 * 注文約定照会ソートキークラス<BR>
 * <BR>
 * WEB3AdminOROrderExecutionSortKeyUnit<BR>
 * <BR>
 * @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOROrderExecutionSortKeyUnit extends Message
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOROrderExecutionSortKeyUnit.class);

    /**
     * (キー項目)<BR>
     * <BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名を<BR>
     * キー項目とする。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * keyItem<BR>
     * <BR>
     * keyItem is a symbol name in a response class for a request using this class.<BR>
     *
     * <BR>
     * Refer to the description of a request definition using this class about the
     * items<BR>
     * <BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * <BR>
     * A：昇順　@D：降順<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * ascDesc<BR>
     * A: Def.ASC　@D: Def.DESC<BR>
     * <BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 4212FB2C0344
     */
    public WEB3AdminOROrderExecutionSortKeyUnit()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * ３）this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”A：昇順”
     * 　@　@　@・”D：降順”<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * 1)If this.keyItem＝null,<BR>
     * 　@　@Throw the exception "sortKeys.keyItem is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * 2)If this.ascDesc＝null,<BR>
     * 　@　@Throw the exception "sortKeys is null"<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * 3)If this.ascDesc contains values other than the followings,<BR>
     * 　@　@Throw the exception "sortKeys.ascDesc has an undefined value"<BR>
     * 　@　@　@・A: Def.ASC
     * 　@　@　@・D: Def.DESC<BR>
     * <BR>
     * class : WEB3BusinessLayerException<BR>
     * tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 41A599F7038B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 1-1 If this.keyItem＝null Throw the exception
        if (this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 2-1 If this.ascDesc＝null Throw the exception
        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 3-1 if ascDesc is not any of Def value, throw Exception.
        if ((!WEB3AscDescDef.ASC.equals(this.ascDesc))
            && (!WEB3AscDescDef.DESC.equals(this.ascDesc)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                this.ascDesc.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
