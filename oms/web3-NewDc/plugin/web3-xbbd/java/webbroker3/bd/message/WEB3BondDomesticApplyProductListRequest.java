head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDomesticApplyProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 国内債券応募銘柄一覧リクエスト(WEB3BondDomesticApplyProductListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 モデルNo.227
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (国内債券応募銘柄一覧リクエスト)<BR>
 * 国内債券応募銘柄一覧リクエスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3BondDomesticApplyProductListRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondDomesticApplyProductListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_domestic_apply_product_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;

    /**
     * (債券区分)<BR>
     * 0:全部<BR>
     * 1:個人向け国債以外<BR>
     * 2:個人向け国債のみ<BR>
     * <BR>
     */
    public String bondDiv;

    /**
     * @@roseuid 46A473FE00CB
     */
    public WEB3BondDomesticApplyProductListRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 国内債券応募銘柄一覧レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondDomesticApplyProductListResponse(this);
    }

    /**
     * 当リクエストデータの整合性をチェックする。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@要求ページ番号チェック<BR>
     * 　@１－１）　@要求ページ番号==nullの場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_00089<BR>
     * 　@１－２）　@要求ページ番号が数字以外の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * ２）　@ページ内表示行数チェック<BR>
     * 　@２－１）　@ページ内表示行数==nullの場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02224<BR>
     * 　@２－２）　@ページ内表示行数が数字以外の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException
     * @@roseuid 466640CB0136
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@要求ページ番号チェック
        //１－１）　@要求ページ番号==nullの場合、例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //１－２）　@要求ページ番号が数字以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //２）　@ページ内表示行数チェック
        //２－１）　@ページ内表示行数==nullの場合、例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        //２－２）　@ページ内表示行数が数字以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
