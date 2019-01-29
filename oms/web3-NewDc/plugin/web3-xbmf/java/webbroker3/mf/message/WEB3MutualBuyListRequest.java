head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.01.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualBuyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託買付一覧照会リクエストクラス(WEB3MutualBuyListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/03 周勇 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー 
Revesion History : 2005/10/21 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2006/09/19 周捷 (中訊) 仕様変更・モデル482
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル535
*/
package webbroker3.mf.message;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFReferenceDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
/**
 * 投資信託買付一覧照会リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualBuyListRequest extends WEB3GenRequest
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_buy_list";

    /**
     * (照会区分)<BR>
     * 照会区分 <BR>
     * <BR>
     * 0： 買付一覧 <BR>
     * 1： 目論見書郵送請求一覧<BR> 
     * 2： 買付のみ <BR>
     * 3： 募集のみ<BR> 
     * <BR>
     * ※0： 買付一覧の場合、買付/募集とも照会の対象とする。 <BR>
     */
    public String referenceType;
    
    /**
     * 投信銘柄カテゴリーコード<BR>
     * <BR>
     * (nullの場合は指定無しとする)<BR>
     */
    public String categoryCode;
    
    /**
     * 要求ページ番号<BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     * <BR>
     * (※ページングを行わない場合、"1"固定とする。)<BR>
     */
    public String pageIndex;
    
    /**
     * ページ内表示行数<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     * <BR>
     * (※ページングを行わない場合、最大値となる数値を指定する)
     */
    public String pageSize;

    /**
     * (投信･外貨MMF表示区分)<BR>
     * 投信･外貨MMF表示区分 <BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分 <BR>
     * <BR>
     * 0:投信のみ <BR>
     * 1:外貨MMFのみ <BR>
     * 2:両方 <BR>
     * <BR>
     * ※nullの場合、「0:投信のみ」とする<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408091539L;
    
    /**
     * (投資信託買付一覧照会リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A879240065
     */
    public WEB3MutualBuyListRequest()
    {
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信買付一覧照会レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A878D10288
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualBuyListResponse(this);
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualBuyListRequest.class);
        
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@要求ページ番号チェック<BR>
     * 　@１－１）　@this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00089 <BR>
     * 　@１－２）　@this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * ２）　@ページ内表示行数チェック<BR>
     * 　@２－１）　@this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00091 <BR>
     * 　@２－２）　@this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * ３）　@照会区分チェック <BR>
     * 　@以下の値以外の場合、例外をスローする。 <BR>
     * <BR>
     *    ”買付一覧”、<BR>
     *    ”目論見書郵送請求一覧” <BR>
     *    ”買付のみ” <BR>
     *    ”募集のみ” <BR>
     * <BR>
     * @@roseuid 40A878C60249
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１－１）　@this.要求ページ番号がnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //１－２）　@this.要求ページ番号が数字以外の値であれば例外をスローする
        try
        {
            Double.parseDouble(this.pageIndex);
        }
        catch (NumberFormatException l_ex)
        {
            log.error("要求ページ番号が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //２－１）　@this.ページ内表示行数がnullの値であれば例外をスローする
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        
        //２－２）　@this.ページ内表示行数が数字以外の値であれば例外をスローする
        try
        {
            Double.parseDouble(this.pageSize);
        }
        catch (NumberFormatException l_ex)
        {
            log.error("ページ内表示行数が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        //３）　@照会区分チェック 
        //　@以下の値以外の場合、例外をスローする。 
        //”買付一覧”、”目論見書郵送請求一覧” ”買付のみ” ”募集のみ” 

        if (!WEB3MFReferenceDivDef.BUY_REFERENCE.equals(this.referenceType) && 
            !WEB3MFReferenceDivDef.BOOK_REFERENCE.equals(this.referenceType) &&
            !WEB3MFReferenceDivDef.BUY.equals(this.referenceType) &&
            !WEB3MFReferenceDivDef.RECRUIT.equals(this.referenceType))
        {
            log.debug("照会区分の値が存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分の値が存在しないコード値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
