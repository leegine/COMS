head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSwTargetListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投信乗換先銘柄一覧リクエストクラス(WEB3MutualSwTargetListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 韋念瓊 (中訊) 新規作成                
*/

package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投信乗換先銘柄一覧リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3MutualSwTargetListRequest extends WEB3MutualCommonRequest
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSwTargetListRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sw_target_list";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200510181028L;

    /**
     * (資産ID)<BR>
     * 乗換元の資産ID <BR>
     */
    public String id;

    /**
     * (投信銘柄カテゴリーコード)<BR>
     * 投信銘柄カテゴリーコード <BR>
     * (nullの場合は指定無しとする)<BR>
     */
    public String categoryCode;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号 <BR>
     * 表示させたいページ位置を指定 <BR>
     * ※先頭ページを"1"とする <BR>
     * <BR>
     * (※ページングを行わない場合、"1"固定とする。)<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数 <BR>
     * 1ページ内に表示させたい行数を指定 <BR>
     * <BR>
     * (※ページングを行わない場合、最大値となる数値を指定する)<BR>
     */
    public String pageSize;    
    
    /**
     * (投信乗換先銘柄一覧リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A8A1C4018D
     */
    public WEB3MutualSwTargetListRequest()
    {

    }

    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 投信乗換先銘柄一覧レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8A1CE00E1
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3MutualSwTargetListResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）資産IDチェック <BR>
     * <BR>
     * this.資産ID==nullの場合、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00080 <BR>
     * <BR>
     * ２）要求ページ番号チェック <BR>
     * <BR>
     * ２－１）this.要求ページ番号==nullの場合、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00089 <BR>
     * <BR>
     * ２－２）this.要求ページ番号が数字以外の値であれば例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * ３）ページ内表示行数チェック <BR>
     * <BR>
     * ３－１）this.ページ内表示行数==nullの場合、例外をスローする。 <BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00091 <BR>
     * <BR>
     * ３－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * @@roseuid 40A8A74B0220
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）資産IDチェック 
        //this.資産ID==nullの場合、例外をスローする。
        
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            log.debug("投信資産ＩＤが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "投信資産ＩＤが未指定です。");
        }
        
        //２）要求ページ番号チェック 
        //２－１）this.要求ページ番号==nullの場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //２－２）this.要求ページ番号が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //３）ページ内表示行数チェック 
        //３－１）this.ページ内表示行数==nullの場合、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        
        //３－２this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
