head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesIndividualCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物個別返済一覧画面表示リクエスト(WEB3FuturesIndividualCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (株価指数先物個別返済一覧画面表示リクエスト)<BR>
 * <BR>
 * 株価指数先物個別返済一覧画面表示リクエストクラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesIndividualCloseMarginListRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_individualCloseMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191445L;

    /**
     * (株価指数先物オプションソートキー)<BR>
     * <BR>
     * 建日の降順を指定<BR>
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (ID)<BR>
     * <BR>
     * 配列の各値には建玉IDを設定<BR>
     */
    public String[] id;

    /**
     * @@roseuid 40F7AE1002FD
     */
    public WEB3FuturesIndividualCloseMarginListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ソートキーチェック<BR>
     * 　@１－１）this.株価指数先物オプションソートキーが<BR>
     * 　@　@　@　@nullの値であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * 　@１－２）this.株価指数先物オプションソートキーの要素数が<BR>
     * 　@　@　@　@０であれば例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * 　@１－３）this.株価指数先物オプションソートキーの要素数分<BR>
     * 　@　@　@　@繰り返してチェックを行う。<BR>
     * 　@　@１－３－１）株価指数先物オプションソートキー.キー項目 ＝ <BR>
     *                  null の場合、例外をスローする。<BR>
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00085<BR>
     * 　@　@１－３－２）株価指数先物オプションソートキー.キー項目 ≠ <BR>
     *                "建日" の場合、例外をスローする。<BR>
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00317<BR>
     * 　@　@１－３－３）株価指数先物オプションソートキー.昇順／降順 ＝ <BR>
     *                 null の場合、例外をスローする。<BR>
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00318<BR>
     * 　@　@１－３－４）株価指数先物オプションソートキー.昇順／降順 ≠ <BR>
     *                 "D" の場合、例外をスローする。<BR>
     *                  ・D：降順<BR>
     *                    A：昇順
     *                  class: WEB3BusinessLayerException<BR>
     *                  tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * ２）　@ＩＤチェック<BR>
     * 　@２－１）this.ＩＤがnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00080<BR>
     * 　@２－２）this.ＩＤの要素数が０であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00282<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2F4540214
     */
    public void validate() throws WEB3BaseException
    {
        //１）　@ソートキーチェック 
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
        if (this.futOpSortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                getClass().getName() + "validate",
                "ソートキーの要素数が０である。");
        }
        
        //　@１－３）this.株価指数先物オプションソートキーの要素数分
        //　@　@　@　@繰り返してチェックを行う。 
        int l_intOpSortKeysLength = this.futOpSortKeys.length;
        for (int i = 0; i < l_intOpSortKeysLength; i++)
        {
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
            if (!WEB3IfoKeyItemDef.OPEN_DATE.equals(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    getClass().getName() + "validate",
                    "株価指数先物オプションソートキー.キー項目 ≠  「建日」である");
            }
            
            //　@　@１－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                    getClass().getName() + "validate",
                    "昇順／降順が未指定です。");
            }
            
            //　@　@１－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
            //　@　@　@・”D：降順”
            //          A：昇順
            if (!WEB3AscDescDef.DESC.equals(this.futOpSortKeys[i].ascDesc) && !WEB3AscDescDef.ASC.equals(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    getClass().getName() + "validate",
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        //２）　@ＩＤチェック
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
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE10031C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesIndividualCloseMarginListResponse(this);

    }
}
@
