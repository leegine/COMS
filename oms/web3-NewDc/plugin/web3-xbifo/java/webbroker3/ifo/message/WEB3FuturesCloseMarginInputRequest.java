head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.13.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済入力画面リクエスト(WEB3FuturesCloseMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株価指数先物返済入力画面リクエスト)<BR>
 * 株価指数先物返済入力画面リクエストクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginInputRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191544L;

    /**
     * (ID)<BR>
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
     * (注文数量)<BR>
     */
    public String futOrderQuantity;

    /**
     * @@roseuid 40F7AE16038A
     */
    public WEB3FuturesCloseMarginInputRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ＩＤチェック<BR>
     * 　@this.ＩＤがnullの値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * ２）　@ソートキーチェック<BR>
     * 　@２－１）this.ソートキーがnullの値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00231<BR>
     * 　@２－２）this.ソートキーの要素数が０であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00232<BR>
     * 　@２－３）this.ソートキーの要素数分繰り返してチェックを行う。<BR>
     * 　@　@２－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
     *                     class: WEB3BusinessLayerException<BR>
     *                     tag:   BUSINESS_ERROR_00085<BR>
     * 　@　@２－３－２）ソートキー.キー項目に以下の項目名以外の値が<BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@・建日<BR>
     * 　@　@　@・建単価<BR>
     *               class: WEB3BusinessLayerException<BR>
     *               tag:   BUSINESS_ERROR_00277<BR>
     * 　@　@２－３－３）ソートキー.昇順／降順<BR>
     *                  がnullの値であれば例外をスローする。<BR>
     *               class: WEB3BusinessLayerException<BR>
     *               tag:   BUSINESS_ERROR_00318<BR>
     * 　@　@２－３－４）ソートキー.昇順／降順<BR>
     *                  が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     *               class: WEB3BusinessLayerException<BR>
     *               tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2FFFE006E
     */
    public void validate() throws WEB3BaseException
    {
        //当リクエストデータの整合性チェックを行う（ただし、当クラス内で簡潔する簡易チェックのみとする。
        if (this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                this.getClass().getName() + "validate",
                "ＩＤがnullの値である。");
        }
        
        //２）　@ソートキーチェック
        if (futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                this.getClass().getName() + "validate",
                "ソートキーがnullの値である。");
        }
        
        //２－２）this.ソートキーの要素数が０であれば例外をスローする。
        if (futOpSortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + "validate",
                "ソートキーの要素数が０である。");
        }
        
        //２－３）this.ソートキーの要素数分繰り返してチェックを行う。
        int l_intObjectItemLength = futOpSortKeys.length;
        for (int i = 0; i < l_intObjectItemLength; i++)
        {
            //２－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                    this.getClass().getName() + "validate",
                    "キー項目がnullの値である。");
            }
            
            //２－３－２）ソートキー.キー項目に以下の項目名以外の値が存在したら例外をスローする。
            if (!WEB3IfoKeyItemDef.OPEN_DATE.equals(futOpSortKeys[i].keyItem) 
                && !WEB3IfoKeyItemDef.CONTRACT_PRICE.equals(futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    this.getClass().getName() + "validate",
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
            
            //２－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                    this.getClass().getName() + "validate",
                    "昇順／降順が未指定です。");
            }
            
            //２－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
            if (!WEB3AscDescDef.ASC.equals(futOpSortKeys[i].ascDesc) 
                && !webbroker3.common.define.WEB3AscDescDef.DESC.equals(futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    this.getClass().getName() + "validate",
                    "ソートキー.昇順／降順が”A：昇順””D：降順”以外の値である");
            }
        }
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE1603A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesCloseMarginInputResponse(this);
    }
}
@
