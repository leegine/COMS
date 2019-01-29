head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS申込客一覧問合せ検索結果リクエストクラス(WEB3AdminInformPTSAccountListResultRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・PTS申込客一覧問合せ検索結果リクエストクラス)<BR>
 * 管理者・PTS申込客一覧問合せ検索結果リクエストクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListResultRequest extends WEB3GenRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListResultRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_pts_account_list_result";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802281637L;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;

    /**
     * (ページ内表示桁数)<BR>
     * ページ内表示桁数
     */
    public String pageSize;

    /**
     * (ソートキー)<BR>
     * ソートキー
     */
    public WEB3AdminInformPTSAccountListInqSortKey[] sortKeys;

    /**
     * (検索条件)<BR>
     * 検索条件
     */
    public WEB3AdminInformPTSAccountListInqCondition searchCondition;

    /**
     * @@roseuid 47C522D40338
     */
    public WEB3AdminInformPTSAccountListResultRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformPTSAccountListResultResponse(this);
    }

    /**
     * 整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@検索条件<BR>
     * <BR>
     * 　@１－１）　@検索条件がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00945<BR>
     * <BR>
     * <BR>
     * 　@１－２）　@検索条件.validate()をコールする。<BR>
     * <BR>
     * ２）　@ソートキー<BR>
     * <BR>
     * 　@２－１）　@ソートキーがnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@２－２）　@ソートキーの各要素について、ソートキー.validate()をコールする。<BR>
     * <BR>
     * ３）　@要求ページ番号<BR>
     * <BR>
     * 　@３－１）　@要求ページ番号がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@３－２）　@要求ページ番号が以下の場合、例外をthrowする。<BR>
     * <BR>
     * 　@　@要求ページ番号 != 半角数字 or<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@　@要求ページ番号 <= 0<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）ページ内表示桁数<BR>
     * <BR>
     * 　@４－１）　@ページ内表示桁数がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@４－２）　@ページ内表示桁数が以下の場合、例外をthrowする。<BR>
     * <BR>
     * 　@　@ページ内表示行数 != 半角数字 or<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@　@ページ内表示行数 <= 0<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B5435403E0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@検索条件
        //検索条件がnullの場合、例外をthrowする
        if (this.searchCondition == null)
        {
            log.debug("検索条件が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00945,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "検索条件が未指定です。");
        }

        //検索条件.validate()をコールする。
        this.searchCondition.validate();

        //２）　@ソートキー
        //ソートキーがnullの場合、例外をthrowする
        if (sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        //ソートキーの各要素について、ソートキー.validate()をコールする
        for (int i = 0; i < sortKeys.length; i++)
        {
            sortKeys[i].validate();
        }

        //３）　@要求ページ番号
        //要求ページ番号がnullの場合、例外をthrowする
        if (pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        //要求ページ番号が以下の場合、例外をthrowする。
        //要求ページ番号 != 半角数字
        if (!WEB3StringTypeUtility.isDigit(pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        //要求ページ番号 <= 0
        if (Integer.parseInt(pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //４）ページ内表示桁数
        //ページ内表示桁数がnullの場合、例外をthrowする。
        if (pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        //ページ内表示桁数が以下の場合、例外をthrowする。
        //ページ内表示行数 != 半角数字
        if (!WEB3StringTypeUtility.isDigit(pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        //ページ内表示行数 <= 0
        if (Integer.parseInt(pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
