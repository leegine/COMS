head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者立会外分売ソートキー(WEB3AdminOffFloorSortKey.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;

/**
 * (管理者立会外分売ソートキー)<BR>
 * <BR>
 * 管理者立会外分売ソートキー。<BR>
 * <BR>
 * WEB3AdminOffFloorSortKey<BR>
 * <BR>
 *  @@author Arpan
 * @@version 1.0
 */
public class WEB3AdminOffFloorSortKey extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminOffFloorSortKey.class);

    /**
     * (キー項目)<BR>
     * <BR>
     * 当クラスを利用したリクエスト対してのレスポンスクラス中のシンボル名をキー項目とす
     * る。<BR>
     * 対象項目については、当クラスを利用したリクエスト定義中の記述を参照。<BR>
     * <BR>
     * ----<English>-------------<BR>
     * <BR>
     * keyItem<BR>
     * <BR>
     * Assume that the symbol names in the response class corresponding to the request
     * class using this class are keyItem<BR>
     * Refer to the description of the request using this class about the key items<BR>
     * <BR>
     */
    public String keyItem;

    /**
     * (昇順／降順)<BR>
     * <BR>
     * A：昇順　@D：降順<BR>
     * <BR>
     * A: Def.ASC D: Def.DESC<BR>
     * <BR>
     */
    public String ascDesc;

    /**
     * @@roseuid 421AE2F10254
     */
    public WEB3AdminOffFloorSortKey()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@this.キー項目＝nullの場合、<BR>
     * 　@　@「ソートキー.キー項目がnull」の例外をスローする。<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * ２）　@this.昇順／降順＝nullの場合、<BR>
     * 　@　@「ソートキー.昇順／降順がnull」の例外をスローする。<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * ３）　@this.昇順／降順が下記の項目以外の場合、<BR>
     * 　@　@「ソートキー.昇順／降順が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00325<BR>
     * <BR>
     * ------<English>----------------<BR>
     * <BR>
     * Check the correspondence in this class<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)If this.keyItem＝null<BR>
     * 　@　@Throw the exception "sortKeys.keyItem is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00085<BR>
     * <BR>
     * 2)If this.ascDesc＝null,<BR>
     * 　@　@Throw the exception "sortKeys.ascDesc is null"<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00087<BR>
     * <BR>
     * 3)If this.ascDesc has an item other than the following items,<BR>
     * 　@　@Throw the exception "sortKeys.ascDesc has an undefined value"<BR>
     * 　@　@　@・A: Def.ASC<BR>
     * 　@　@　@・D: Def.DESC<BR>
     * <BR>
     *               class : WEB3BusinessLayerException<BR>
     *               tag : BUSINESS_ERROR_00088<BR>
     * <BR>
     * @@throws WEB3BaseException WEB3BaseException
     * システム共通（web3-common）.(web3)システム実装クラス_common.WEB3BaseException
     * @@roseuid 41BD04290105
     */
    public void validate() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //1-1 if keyItem=null throw exception
        if (this.keyItem == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //   2-1 if ascDesc=null throw exception
        if (this.ascDesc == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            /*
             * 3-1 If this.ascDesc has an item other than the following items  Throw the exception
             * A: Def.ASC
             * D: Def.DESC
             */
            if ((!WEB3AscDescDef.ASC.equals(ascDesc)) && (!WEB3AscDescDef.DESC.equals(ascDesc)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
