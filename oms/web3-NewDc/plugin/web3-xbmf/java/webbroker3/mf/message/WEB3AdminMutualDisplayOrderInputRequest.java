head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.08.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualDisplayOrderInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信管理者銘柄表示順序登録入力画面リクエスト(WEB3AdminMutualDisplayOrderInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 黄建 (中訊) 新規作成 
Revesion History : 2007/02/25 唐性峰 (中訊) モデル No.543
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (投信管理者銘柄表示順序登録入力画面リクエスト)<BR>
 * 投資信託管理者銘柄表示順序登録入力画面リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3AdminMutualDisplayOrderInputRequest extends WEB3GenRequest 
{
   
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_mutual_display_order_input";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412031015L;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMutualDisplayOrderInputRequest.class);    
  
    /**
     * (投信ソートキー)<BR>
     * <BR>
     * 対象項目:
     *     ”現在表示順”、
     *     ”銘柄コード”、<BR>
     * 　@  ”投信協会銘柄コード”、<BR>
     * 　@  ”投信銘柄カテゴリーコード１”、<BR>
     * 　@  ”投信銘柄カテゴリーコード２”、<BR>
     * 　@  ”投信銘柄カテゴリーコード３”
     */
    public WEB3MutualSortKey[] sortKeys;

    /**
     * (投信・外貨MMF表示区分)<BR>
     * 投信・外貨MMF表示区分<BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分<BR>
     * <BR>
     * 0:投信のみ<BR>
     * 1:外貨MMFのみ<BR>
     * 2:両方<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信管理者銘柄表示順序登録入力画面レスポンスオブジェクトを返却する。
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 4153BB3D0037
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMutualDisplayOrderInputResponse(this);
    }
   
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@投信ソートキーのチェック<BR>
     * 　@１－１)　@this.投信ソートキー＝nullの場合、例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:BUSINESS_ERROR_00231<BR>
     * 　@１－２)　@this.投信ソートキーの要素数＝0であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:BUSINESS_ERROR_00232<BR>
     * 　@１－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。<BR>
     * 　@　@１－３－１）キー項目＝nullの場合、例外をスローする。<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00085<BR>
     * 　@　@１－３－２）キー項目が以下の値のいずれかではない場合、<BR>
     *                例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@  ・”現在表示順”<BR>
     * 　@　@　@　@　@　@　@　@  ・”銘柄コード”<BR>
     * 　@　@　@　@　@　@　@　@  ・”投信協会銘柄コード”<BR>
     * 　@　@　@　@　@　@　@　@  ・”投信銘柄カテゴリーコード１”<BR>
     * 　@　@　@　@　@　@　@　@  ・”投信銘柄カテゴリーコード２”<BR>
     * 　@　@　@　@　@　@　@　@  ・”投信銘柄カテゴリーコード３”<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00086<BR>
     * 　@　@１－３－３）昇順／降順＝nullの場合、例外をスローする。<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00087<BR>
     * 　@　@１－３－４）昇順／降順が以下の値のいずれかではない場合、<BR>
     *                例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@  ・”昇順”<BR>
     * 　@　@　@　@　@　@　@　@  ・”降順”<BR>
     *                class: WEB3BusinessLayerException<BR>
     *                tag:BUSINESS_ERROR_00088
     * @@roseuid 4153C2F30334
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        //１)　@投信ソートキーのチェック 
        //  １－１)　@this.投信ソートキー＝nullの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //１－２)　@this.投信ソートキーの要素数＝0であれば例外をスローする。 
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //１－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //１－３－１）キー項目＝nullの場合、例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].keyItem))
            {
                log.debug("ソートキーのキー項目が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目が未指定です。");
            }
            
            //１－３－２）キー項目が以下の値のいずれかではない場合、例外をスローする。 
            //　@・”現在表示順” 
            //　@・”銘柄コード” 
            //　@・”投信協会銘柄コード” 
            //　@・”投信銘柄カテゴリーコード１” 
            //　@・”投信銘柄カテゴリーコード２” 
            //　@・”投信銘柄カテゴリーコード３”
            if (!(WEB3MFSortkeyItemDef.DISPLAY_ORDER.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.PRODUCT_CODE.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_ASSOC_PRODUCT_CODE.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_1.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_2.equals(
                    this.sortKeys[i].keyItem)
                || WEB3MFSortkeyItemDef.MUTUAL_CATEGORY_CODE_3.equals(
                    this.sortKeys[i].keyItem)))
            {
                log.debug("キー項目に銘柄名、売買区分、注文時間の項目名以外の値が存在しています。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "[現在表示順]、[銘柄コード]、[投信協会銘柄コード]、" +
                    "[投信銘柄カテゴリーコード１]、[投信銘柄カテゴリーコード２]、" +
                    "[投信銘柄カテゴリーコード３]");
            }
            
            //１－３－３）昇順／降順＝nullの場合、例外をスローする。 
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].ascDesc))
            {
                log.debug("昇順／降順が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が未指定です。");
            }
            
            //１－３－４）昇順／降順が以下の値のいずれかではない場合、例外をスローする。 
            //　@・”昇順” 
            //　@・”降順”
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc)
                || WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc)))
            {
                log.debug("昇順／降順は”A：昇順”、”D：降順”の値以外です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順は”A：昇順”、”D：降順”の値以外です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
