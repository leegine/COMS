head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsIndividualCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション個別返済一覧画面表示リクエスト(WEB3OptionsＩndividualCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張威 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数オプション個別返済一覧画面表示リクエスト)<BR>
 * 株価指数オプション個別返済一覧画面表示リクエストクラス<BR>
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionsIndividualCloseMarginListRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsIndividualCloseMarginListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_individualCloseMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111735L;

    /**
     * (株価指数先物オプションソートキー)<BR>
     * 建日の降順を指定
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (ID)<BR>
     * 配列の各値には建玉IDを設定
     */
    public String[] id;
    
    /**
     * @@roseuid 40C0A8E701F4
     */
    public WEB3OptionsIndividualCloseMarginListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR> 
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@ソートキーチェック <BR>
     * 　@１－１）this.株価指数先物オプションソートキーが<BR> 
     * 　@　@　@　@nullの値であれば例外をスローする。<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00231<BR>
     * 　@１－２）this.株価指数先物オプションソートキーの要素数が <BR>
     * 　@　@　@　@０であれば例外をスローする。<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00232<BR>
     * 　@１－３）this.株価指数先物オプションソートキーの要素数分<BR> 
     * 　@　@　@　@繰り返してチェックを行う。 <BR>
     * 　@　@１－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。 <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00085<BR>
     * 　@　@１－３－２）ソートキー.キー項目に以下の項目名以外の値が <BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。 <BR>
     * 　@　@　@・建日<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00281<BR>    
     * 　@　@１－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00087<BR>
     * 　@　@１－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@・”A:昇順 D：降順”<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * ２）　@ＩＤチェック<BR>
     * 　@２－１）this.ＩＤがnullの値であれば例外をスローする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     *   ２－２）this.ＩＤの要素数が０であれば例外をスローする。
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00282<BR>    
     * @@throws WEB3BaseException
     * @@roseuid 4083574602F8
     */
    public void validate() throws WEB3BaseException
    {
        //１）　@ソートキーチェック 
        log.debug("１）　@ソートキーチェック: ENTER");
        //　@１－１）this.株価指数先物オプションソートキーが
        //　@　@　@　@nullの値であれば例外をスローする。
        if (this.futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "validate",
                "ソートキーがnullの値である。");
        }
        //　@１－２）this.株価指数先物オプションソートキーの要素数が
        //　@　@　@　@０であれば例外をスローする。
        else if (this.futOpSortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + "validate",
                "ソートキーの要素数が０である。");
        }
        //　@１－３）this.株価指数先物オプションソートキーの要素数分
        //　@　@　@　@繰り返してチェックを行う。 
        else
        {
            log.debug("futOpSortKeys.length: ENTER");
            int l_intOpSortKeysLength = this.futOpSortKeys.length;
            log.debug("futOpSortKeys.length: END");
            for (int i = 0; i < l_intOpSortKeysLength; i++)
            {
                log.debug("LOOP: ENTER " + (i+1));
                //　@　@１－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。 
                if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].keyItem))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                        getClass().getName() + "validate",
                        "キー項目がnullの値である。");
                }
                //　@　@１－３－２）ソートキー.キー項目に以下の項目名以外の値が
                //　@　@　@　@　@　@　@存在したら例外をスローする。
                //　@　@　@・建日
                else if (!WEB3IfoKeyItemDef.OPEN_DATE.equals(this.futOpSortKeys[i].keyItem))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        getClass().getName() + "validate",
                        "ソートキーのキー項目の値が存在しないコード値です。");
                }
                //　@　@１－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
                else if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].ascDesc))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                        getClass().getName() + "validate",
                        "昇順／降順がnullの値である。");
                }
                //　@　@１－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
                //　@　@　@・”A:昇順 D：降順”
                else if (!WEB3AscDescDef.DESC.equals(this.futOpSortKeys[i].ascDesc) && !WEB3AscDescDef.ASC.equals(this.futOpSortKeys[i].ascDesc))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                        getClass().getName() + "validate",
                        "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
                }
                log.debug("LOOP: END " + (i+1));
            }
        }
        log.debug("１）　@ソートキーチェック: END");

        //２）　@ＩＤチェック
        log.debug(": ENTER");
        //　@２－１）this.ＩＤがnullの値であれば例外をスローする。
        if (this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }
        //　@２－２）this.ＩＤの要素数が０であれば例外をスローする。
        else if (this.id.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00282,
                getClass().getName() + "validate",
                "IDの要素数が0である。");
        }
        log.debug(": END");
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsIndividualCloseMarginListResponse(this);
    }
}
@
