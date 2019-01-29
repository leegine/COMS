head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索一覧リクエストクラス(WEB3AdminInformListRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (連絡情報検索一覧リクエスト)<BR>
 * 連絡情報検索一覧リクエストクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformListRequest extends WEB3AdminInformCommonRequest 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数
     */
    public String pageSize;
    
    /**
     * @@roseuid 41EE625B037A
     */
    public WEB3AdminInformListRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドをコールする。<BR>
     * <BR>
     * ２）要求ページ番号 <BR>
     * <BR>
     *    this.要求ページ番号 == null or <BR>
     * 　@class: WEB3BusinessLayerException<BR> 
     * 　@tag: BUSINESS_ERROR_00089<BR> 
     *    this.要求ページ番号 != 半角数字 <BR>
     * 　@class: WEB3BusinessLayerException<BR> 
     * 　@tag: BUSINESS_ERROR_00090
     *    this.要求ページ番号 <= 0 or <BR>
     * 　@class: WEB3BusinessLayerException<BR> 
     * 　@tag: BUSINESS_ERROR_00616<BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     * <BR>
     * ３）ページ内表示行数 <BR>
     * <BR>
     *    this.ページ内表示行数 == null or <BR>
     * 　@　@class: WEB3BusinessLayerException<BR> 
     * 　@　@tag: BUSINESS_ERROR_00091<BR> 
     *    this.ページ内表示行数 != 半角数字 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR> 
     * 　@　@tag: BUSINESS_ERROR_00092<BR> 
     *    this.ページ内表示行数 <= 0 or <BR>
     * 　@　@class: WEB3BusinessLayerException<BR> 
     * 　@　@tag: BUSINESS_ERROR_00617<BR> 
     * <BR>
     *    の場合、例外をスローする。<BR>
     * @@roseuid 41B93FB7002A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）スーパークラスのvalidate()メソッドをコールする。<BR>
        super.validate();

        //２）要求ページ番号 <BR>
        //  this.要求ページ番号 == nullの場合、例外をスローする。<BR>
        // 　@   class: WEB3BusinessLayerException<BR> 
        //   　@ tag: BUSINESS_ERROR_00089<BR> 
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                getClass().getName() + "validate",
                "要求ページ番号が未指定です。");
        }
        
        //  this.要求ページ番号 != 半角数字の場合、例外をスローする。<BR>
        //    　@class: WEB3BusinessLayerException<BR> 
        //    　@tag: BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                getClass().getName() + "validate",
                "要求ページ番号が数字以外の値です。");
        }

        //  this.要求ページ番号 <= 0の場合、例外をスローする。<BR>
        // 　@   class: WEB3BusinessLayerException<BR> 
        //   　@ tag: BUSINESS_ERROR_00616
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616, 
                getClass().getName() + "validate",
                "要求ページ番号の値が0以下です。");
        }

        //３）ページ内表示行数 <BR>
        //  this.ページ内表示行数 == nullの場合、例外をスローする。<BR>
        //    　@class: WEB3BusinessLayerException<BR> 
        // 　@ 　@tag: BUSINESS_ERROR_00091<BR> 
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                getClass().getName() + "validate",
                "ページ内表示行数の入力が不正です。");
        }

        //  this.ページ内表示行数 != 半角数字の場合、例外をスローする。<BR>
        //  　@　@class: WEB3BusinessLayerException<BR> 
        //  　@　@tag: BUSINESS_ERROR_00092<BR> 
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                getClass().getName() + "validate",
                "ページ内表示行数が数字以外の値です。");
        }

        //  this.ページ内表示行数 <= 0の場合、例外をスローする。<BR>
        //  　@　@class: WEB3BusinessLayerException<BR> 
        //  　@　@tag: BUSINESS_ERROR_00617<BR> 
        if (Double.parseDouble(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617, 
                getClass().getName() + "validate",
                "ページ内表示行数の値が0以下です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformListResponse(this);
    }
}
@
