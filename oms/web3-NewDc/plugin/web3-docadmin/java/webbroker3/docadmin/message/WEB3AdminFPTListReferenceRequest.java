head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTListReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者金商法@交付閲覧一覧照会リクエスト(WEB3AdminFPTListReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 武波 (中訊) 新規作成
Revision History : 2007/10/15 武波 (中訊) モデルNo.005
Revision History : 2007/10/16 Inomata (SCS) モデルNo.007
Revision History : 2008/01/25 周墨洋 (中訊) 仕様変更・モデルNo.022
*/

package webbroker3.docadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者金商法@交付閲覧一覧照会リクエスト)<BR>
 * 管理者金商法@交付閲覧一覧照会リクエストクラス<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminFPTListReferenceRequest extends WEB3GenRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_fpt_list_reference";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200709291429L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTListReferenceRequest.class);

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String acceptCode;

    /**
     * (書面交付日from)<BR>
     * 書面交付日from<BR>
     */
    public Date docuDeliDateFrom;

    /**
     * (書面交付日to)<BR>
     * 書面交付日to<BR>
     */
    public Date docuDeliDateTo;

    /**
     * (書面区分管理一覧)<BR>
     * 書面区分管理一覧<BR>
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;

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
     * (ソートキー)<BR>
     * ソートキー<BR>
     * <BR>
     * キー項目は以下のとおり<BR>
     * <BR>
     * ・部店コード<BR>
     * ・顧客コード<BR>
     * ・書面交付日<BR>
     */
    public WEB3AdminFPTSortKey[] sortKeys;

    /**
     * @@roseuid 46FDDD370138
     */
    public WEB3AdminFPTListReferenceRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTListReferenceResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * <BR>
     * １） 部店コードチェック<BR>
     * <BR>
     * １-１）<BR>
     *    this.部店コード == null or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00833<BR>
     *    this.部店コード.length() == 0<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01757<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * １－２）部店コードの各要素についてチェックする。<BR>
     * <BR>
     *    部店コード != 半角数字 or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01729<BR>
     *    部店コード.length() != 3<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00834<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * <BR>
     * ２） 顧客コードチェック<BR>
     * <BR>
     * リクエスト.顧客コード != null and<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00835<BR>
     * リクエスト.顧客コードが半角数字以外 または<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_01043<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@6桁以外<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00836<BR>
     * <BR>
     * <BR>
     * ３）　@書面交付日大小チェック<BR>
     * 　@４－１）　@リクエスト.書面交付日from != null and<BR>
     * 　@　@リクエスト.書面交付日to !=null の時、<BR>
     * 　@　@　@　@　@　@リクエスト.書面交付日from > リクエスト.書面交付日to<BR>
     * 　@　@　@　@　@　@の場合、例外をスロー<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02947<BR>
     * <BR>
     * <BR>
     * ４） 要求ページ番号チェック<BR>
     * <BR>
     * リクエスト.要求ページ番号 = null or<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00089<BR>
     * リクエスト.要求ページ番号が数字以外 or<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00090<BR>
     * リクエスト.要求ページ番号 <= 0<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * <BR>
     * ５）ページ内表示行数チェック<BR>
     * <BR>
     * リクエスト.ページ内表示行数 = null or<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_02224<BR>
     * リクエスト.ページ内表示行数が数字以外 or<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00092<BR>
     * リクエスト.ページ内表示行数 <= 0<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * <BR>
     * ６）ソートキーチェック<BR>
     * <BR>
     * リクエストデータ.ソートキー = null or<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00231<BR>
     * リクエストデータ.ソートキー.length() = 0<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag　@:　@BUSINESS_ERROR_00232<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46F202FF00F5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

		//１）部店コード<BR>
		//１－１）<BR>
		//this.部店コード == nullの場合、例外をスローする。<BR>

		if (this.branchCode == null)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00833,
				getClass().getName() + "validate",
				"部店コードが未指定です。");
		}

		//this.部店コード.length() == 0の場合、例外をスローする。<BR>

		if (this.branchCode.length == 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01757,
				getClass().getName() + "validate",
				"部店コードの要素数が０です。");
		}

		int l_intBranchCodeLth = this.branchCode.length;
		for (int i = 0; i < l_intBranchCodeLth; i++)
		{
			// １－２）部店コードの各要素についてチェックする。<BR>
			//  部店コード != 半角数字 の場合、例外をスローする。<BR>

			if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01729,
					getClass().getName() + "validate",
					"部店コードの値が数値以外の値です。");
			}

			//  部店コード.length() != 3の場合、例外をスローする。<BR>

			if (this.branchCode[i].length() != 3)
			{
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00834,
					getClass().getName() + "validate",
					"部店コードのサイズが不正です。");
			}
		}

        //２） 顧客コードチェック
        //リクエスト.顧客コード != null and
        //リクエスト.顧客コードが半角数字以外 または6桁以外
        if (this.acceptCode != null)
        {
           if (!WEB3StringTypeUtility.isDigit(this.acceptCode))
           {
                log.debug("顧客コードの値が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの値が数字以外の値です。");
           }

           else if (this.acceptCode.length() != 6)
           {
                log.debug("顧客コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードのサイズが不正です。");
           }
        }

        //３）書面交付日大小チェック
        //３－１）　@リクエスト.書面交付日from != null and  リクエスト.書面交付日to != null の時
        //リクエスト.書面交付日from > リクエスト.書面交付日toの場合例外をスロー
        if (this.docuDeliDateFrom != null && this.docuDeliDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.docuDeliDateFrom, this.docuDeliDateTo) > 0)
            {
                log.debug("書面交付日From/To整合性エラー。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02947,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面交付日From/To整合性エラー。");
            }
        }

        //４） 要求ページ番号チェック
        //リクエスト.要求ページ番号 = null or
        //リクエスト.要求ページ番号が数字以外 or
        //リクエスト.要求ページ番号 <= 0 の場合、例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        else if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        else if ((Integer.parseInt(this.pageIndex) <= 0))
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        //５）ページ内表示行数チェック
        //リクエスト.ページ内表示行数 = null or
        //リクエスト.ページ内表示行数が数字以外 or
        //リクエスト.ページ内表示行数 <= 0の場合、例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        else if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        else if ((Integer.parseInt(this.pageSize) <= 0))
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        //６）ソートキーチェック
        //リクエストデータ.ソートキー = null or
        //リクエストデータ.ソートキー.length() = 0 の場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        else if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);

            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
