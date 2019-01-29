head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済入力画面リクエストクラス(WEB3OptionsCloseMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoKeyItemDef;

/**
 * (株価指数オプション返済入力画面リクエスト)<BR>
 * 株価指数オプション返済入力画面リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginInputRequest extends WEB3GenRequest
{

     /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginInput";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406112110L;

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3OptionsCloseMarginInputRequest.class);

    /**
    * 建玉ID
    */
    public String[] id;

    /**
    * (ソートキー)<BR>
    * 対象項目：建年月日、建単価<BR>
    * <BR>
    * 建玉詳細表示用ソートキー<BR>
    * デフォルトは建年月日の昇順<BR>
    */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
    * 注文数量
    */
    public String opOrderQuantity;

    /**
    * @@roseuid 40C0A8F0036B
    */
    public WEB3OptionsCloseMarginInputRequest()
    {

    }

   /**
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
    * <BR>
    * １）　@ＩＤチェック<BR>
    * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00080<BR>
    * <BR>
    * ２）　@ソートキーチェック<BR>
    * 　@２－１）this.ソートキーがnullの値であれば例外をスローする。<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00231<BR>
    * 　@２－２）this.ソートキーの要素数が０であれば例外をスローする。<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00232<BR>
    * 　@２－３）this.ソートキーの要素数分繰り返してチェックを行う。<BR>
    * 　@　@２－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00085<BR>
    * 　@２－３－２）ソートキー.キー項目に以下の項目名以外の値が<BR>
    * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
    * 　@　@　@・建日<BR>
    * 　@　@　@・建単価<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00277<BR>
    * 　@　@２－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00087<BR>
    * 　@　@２－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。<BR>
    * 　@　@　@・”A：昇順”<BR>
    * 　@　@　@・”D：降順”<BR>
    *           class: WEB3BusinessLayerException<BR>
    *           tag:   BUSINESS_ERROR_00088<BR>
    * @@throws WEB3BaseException
    * @@roseuid 406A7B7B02BE
    */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //当リクエストデータの整合性チェックを行う（ただし、当クラス内で完結する簡易チェックのみとする。
        if (this.id == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }

        //２）　@ソートキーチェック
        if (futOpSortKeys == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "validate",
                "ソートキーがnullの値である。");
        }

        //２－２）this.ソートキーの要素数が０であれば例外をスローする。
        if (futOpSortKeys.length == 0)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "validate",
                "ソートキーの要素数が０である。");
        }

        //２－３）this.ソートキーの要素数分繰り返してチェックを行う。
        int l_intObjectItemLength = futOpSortKeys.length;
        for (int i = 0; i<l_intObjectItemLength; i++)
        {
            //２－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].keyItem))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "validate",
                    "キー項目がnullの値である。");
            }

            //２－３－２）ソートキー.キー項目に以下の項目名以外の値が存在したら例外をスローする。
            if (!WEB3IfoKeyItemDef.OPEN_DATE.equals(futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(futOpSortKeys[i].keyItem))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "validate",
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }

            //２－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].ascDesc))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "validate",
                    "昇順／降順が未指定です。");
            }
            //２－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
            if (!WEB3AscDescDef.ASC.equals(futOpSortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(futOpSortKeys[i].ascDesc))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "validate",
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }

            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginInputResponse(this);
    }
}
@
