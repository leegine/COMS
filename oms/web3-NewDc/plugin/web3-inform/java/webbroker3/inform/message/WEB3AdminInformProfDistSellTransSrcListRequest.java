head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistSellTransSrcListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 利金・分配金・売却代金振込先一覧リクエストクラス(WEB3AdminInformProfDistSellTransSrcListRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/05/24 謝旋(中訊) 新規作成 モデルNo.045
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (利金・分配金・売却代金振込先一覧リクエストクラス)<BR>
 * 利金・分配金・売却代金振込先一覧リクエストクラス<BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformProfDistSellTransSrcListRequest extends WEB3GenRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_sell_trans_src_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200705242212L;

    /**
     * (検索条件)<BR>
     * 検索条件
     */
    public WEB3AdminInformProfDistSellTransSrcCondition searchCondition;

    /**
     * (ソートキー)<BR>
     * ソートキーの配列
     */
    public WEB3AdminInformProfDistSellTransSrcSortKey[] sortKeys;

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
     * @@roseuid 4655937502FC
     */
    public WEB3AdminInformProfDistSellTransSrcListRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistSellTransSrcListResponse(this);
    }

    /**
     * 整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）検索条件<BR>
     * <BR>
     * 　@１－１）検索条件がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00945<BR>
     * <BR>
     * 　@１－２）検索条件.validate()をコールする。<BR>
     * <BR>
     * ２）ソートキー<BR>
     * <BR>
     * 　@２－１）ソートキーがnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@２－２）ソートキーの各要素について、ソートキー.validate()をコールする。<BR>
     * <BR>
     * ３）要求ページ番号<BR>
     * <BR>
     * 　@３－１）要求ページ番号がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@３－２）要求ページ番号が以下の場合、例外をthrowする。<BR>
     * <BR>
     * 　@　@要求ページ番号 != 半角数字 or<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00090<BR>
     * 　@　@要求ページ番号 <= 0<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）ページ内表示桁数<BR>
     * <BR>
     * 　@４－１）ページ内表示桁数がnullの場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@４－２）ページ内表示桁数が以下の場合、例外をthrowする。<BR>
     * <BR>
     * 　@　@ページ内表示行数 != 半角数字 or<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00092<BR>
     * 　@　@ページ内表示行数 <= 0<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 461AE34D006A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １－１）検索条件がnullの場合、例外をthrowする。
        if (this.searchCondition == null)
        {
            log.debug("検索条件が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00945,
                this.getClass().getName() + STR_METHOD_NAME,
                "検索条件が未指定です。");
        }
        else
        {
            // １－２）検索条件.validate()をコールする。
        	this.searchCondition.validate();
        }

        // ２－１）ソートキーがnullの場合、例外をthrowする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        // ２－２）ソートキーの各要素について、ソートキー.validate()をコールする。
        int l_intSortKeysLth = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeysLth; i++)
        {
            this.sortKeys[i].validate();
        }

        // 要求ページ番号がnullの場合、例外をthrowする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        // ３－２）要求ページ番号が以下の場合、例外をthrowする。
        // 要求ページ番号 != 半角数字 or 要求ページ番号 <= 0
        try
        {
            int l_intPageIndex= Integer.parseInt(this.pageIndex);
            if(l_intPageIndex <= 0)
            {
                // 要求ページ番号が０以下の場合
                log.debug("要求ページ番号の値が0以下です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + ".validate()");
            }
        } catch(NumberFormatException e)
        {
            // 要求ページ番号が数字以外の場合
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + ".validate()");
        }

        // ４－１）ページ内表示桁数がnullの場合、例外をthrowする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        // ページ内表示桁数が以下の場合、例外をthrowする。
        // ページ内表示行数 != 半角数字 or ページ内表示行数 <= 0
        try
        {
            int l_intPageSize= Integer.parseInt(this.pageSize);
            if(l_intPageSize <= 0)
            {
                // 要求ページ番号が０以下の場合
                log.debug("ページ内表示行数の値が0以下です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                    this.getClass().getName() + ".validate()");
            }
        } catch(NumberFormatException e)
        {
            // 要求ページ番号が数字以外の場合
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + ".validate()");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
