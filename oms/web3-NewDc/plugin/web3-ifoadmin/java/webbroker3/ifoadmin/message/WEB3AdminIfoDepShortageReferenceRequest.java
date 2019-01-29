head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・証拠金不足状況照会リクエスト(WEB3AdminIfoDepShortageReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27 李玉玲(中訊) 新規作成 モデルNo.004
*/
package webbroker3.ifoadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifoadmin.define.WEB3AdminIfoUnCancelDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・証拠金不足状況照会リクエスト)<BR>
 * 管理者・証拠金不足状況照会リクエストクラス<BR>
 * <BR>
 * @@author 李玉玲(中訊)
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageReferenceRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_ifo_dep_shortage_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200902271150L;

    /**
     * (検索日付)<BR>
     * 検索日付<BR>
     * <BR>
     */
    public Date searchDate;

    /**
     * (部店コード一覧)<BR>
     * 部店コードの配列 <BR>
     * <BR>
     * ※部店コード未入力時は、PR層で保持している <BR>
     * 　@取扱可能部店コード一覧がセットされる。 <BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode = null;

    /**
     * (未解消客区分)<BR>
     * 未解消客区分<BR>
     * <BR>
     * 0：未解消客<BR>
     * 1：不足発生全顧客<BR>
     */
    public String uncancelDiv = "0";

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号 <BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
     * <BR>
     * ※ダウンロード機@能からコールされた場合は、 <BR>
     * 　@ダウンロードページ番号となる。 <BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数 <BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     * <BR>
     * ※ダウンロード機@能からコールされた場合は、 <BR>
     * 　@ダウンロード件数となる。 <BR>
     */
    public String pageSize;

    /**
     * (証拠金不足状況ソートキー)<BR>
     * 証拠金不足状況ソートキー <BR>
     */
    public WEB3IfoDepShortageSortKey[] sortKeys;

    /**
     * @@roseuid 49A7485403B9
     */
    public WEB3AdminIfoDepShortageReferenceRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@検索日付チェック <BR>
     * 　@１－１）　@this.検索日付== nullの場合、 <BR>
     * 　@　@「検索日付がnull」の例外をスローする。<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03154<BR>
     * <BR>
     * ２）　@部店コードチェック <BR>
     * 　@２－１）　@this.部店コード一覧 == nullの場合、 <BR>
     * 　@　@「部店コードがnull」の例外をスローする。 <BR>
     * 　@　@（BUSINESS_ERROR_01429）<BR>
     * <BR>
     * 　@２－２）　@this.部店コード一覧の要素数分以下の処理を行う。 <BR>
     * 　@　@２－２－１）　@this.部店コードが以下の条件に該当する場合、 <BR>
     * 　@　@　@「部店コードエラー」の例外をスローする。<BR>
     * 　@　@　@　@・部店コード != 数字 <BR>
     * 　@　@　@　@・部店コード.length != 3 <BR>
     * 　@　@　@（BUSINESS_ERROR_00779） <BR>
     * <BR>
     * ３）　@顧客コードチェック <BR>
     * 　@this.顧客コード != nullの場合、以下のチェックを行う。 <BR>
     * 　@３－１）　@this.顧客コードが以下の条件に該当する場合、 <BR>
     * 　@　@「顧客コードエラー」の例外をスローする。 <BR>
     * 　@　@　@・顧客コード != 数字 <BR>
     * 　@　@　@・顧客コード.length != 6<BR>
     * 　@　@（BUSINESS_ERROR_00780） <BR>
     * <BR>
     * ４）　@未解消客区分チェック <BR>
     * 　@４－１）　@this.未解消客区分== nullの場合、 <BR>
     * 　@　@「未解消客区分がnull」の例外をスローする。 <BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03155<BR>
     * <BR>
     * 　@４－２）　@this.未解消客区分に下記の項目以外が設定されていたら、 <BR>
     * 　@　@「未解消客区分が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@・"0：未解消客 "<BR>
     * 　@　@　@・"1：不足発生全顧客 "<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag　@:　@BUSINESS_ERROR_03156<BR>
     * <BR>
     * ５）　@ソートキーチェック <BR>
     * 　@５－１）　@this.ソートキーが未入力の場合、 <BR>
     * 　@　@「ソートキーがnull」の例外をスローする。 <BR>
     * 　@　@（BUSINESS_ERROR_00231）<BR>
     * <BR>
     * 　@５－２）　@this.ソートキーの要素数分以下の処理を繰り返す。 <BR>
     * 　@　@５－２－１）　@ソートキー.validate()をコールする。 <BR>
     * <BR>
     * ６）　@要求ページ番号チェック <BR>
     * 　@６－１）　@this.要求ページ番号 == nullであった場合、 <BR>
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。 <BR>
     * 　@　@　@（BUSINESS_ERROR_00089）<BR>
     * <BR>
     * 　@６－２）　@this.要求ページ番号が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 <BR>
     * 　@　@　@（BUSINESS_ERROR_00090）<BR>
     * <BR>
     * 　@６－３）　@this.要求ページ番号 <= 0であった場合、 <BR>
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。 <BR>
     * 　@　@　@（BUSINESS_ERROR_00616）<BR>
     * <BR>
     * ７）　@ページ内表示行数チェック <BR>
     * 　@７－１）　@this.ページ内表示行数 == nullであった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
     * 　@　@　@（BUSINESS_ERROR_00091）<BR>
     * <BR>
     * 　@７－２）　@this.ページ内表示行数が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     * 　@ 　@　@（BUSINESS_ERROR_00092）<BR>
     * <BR>
     * 　@７－３）　@this.ページ内表示行数 <= 0であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。 <BR>
     * 　@　@　@　@（BUSINESS_ERROR_00617）<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 498FCDDB0122
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@検索日付チェック
        // 　@１－１）　@this.検索日付== nullの場合、
        // 　@　@「検索日付がnull」の例外をスローする。
        if (this.searchDate == null)
        {
            log.debug("検索日付がnull。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03154,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "検索日付がnull。");
        }

        // ２）　@部店コードチェック
        // 　@２－１）　@this.部店コード一覧 == nullの場合、
        // 　@　@「部店コードがnull」の例外をスローする。
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            log.debug("部店コード一覧が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード一覧が未指定です。");
        }

        // 　@２－２）　@this.部店コード一覧の要素数分以下の処理を行う。
        // 　@　@２－２－１）　@this.部店コードが以下の条件に該当する場合、
        // 　@　@　@「部店コードエラー」の例外をスローする。
        // 　@　@　@　@・部店コード != 数字
        // 　@　@　@　@・部店コード.length != 3
        int l_intBranchCodeCnt = this.branchCode.length;
        for (int i = 0; i < l_intBranchCodeCnt; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i])
                || this.branchCode[i].length() != 3)
            {
                log.debug("部店コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの入力が不正です。");
            }
        }

        // ３）　@顧客コードチェック
        // 　@this.顧客コード != nullの場合、以下のチェックを行う。
        // 　@３－１）　@this.顧客コードが以下の条件に該当する場合、
        // 　@　@「顧客コードエラー」の例外をスローする。
        // 　@　@　@・顧客コード != 数字
        // 　@　@　@・顧客コード.length != 6
        if (this.accountCode != null)
        {
            if (!WEB3StringTypeUtility.isDigit(this.accountCode)
                || this.accountCode.length() != 6)
            {
                log.debug("顧客コードの入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの入力が不正です。");
            }
        }

        // ４）　@未解消客区分チェック
        // 　@４－１）　@this.未解消客区分== nullの場合、
        // 　@　@「未解消客区分がnull」の例外をスローする。
        if (this.uncancelDiv == null)
        {
            log.debug("未解消客区分がnull。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03155,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "未解消客区分がnull。");
        }

        // 　@４－２）　@this.未解消客区分に下記の項目以外が設定されていたら、
        // 　@　@「未解消客区分が未定義の値」の例外をスローする。
        // 　@　@　@・"0：未解消客 "
        // 　@　@　@・"1：不足発生全顧客 "
        if (!WEB3AdminIfoUnCancelDivDef.UN_CANCEL.equals(this.uncancelDiv)
            && !WEB3AdminIfoUnCancelDivDef.SHORT_GENERATION_ALL_ACCOUNT.equals(this.uncancelDiv))
        {
            log.debug("未解消客区分が未定義の値。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03156,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "未解消客区分が未定義の値。");
        }

        // ５）　@ソートキーチェック
        // 　@５－１）　@this.ソートキーが未入力の場合、
        // 　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null || this.sortKeys.length == 0)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        // 　@５－２）　@this.ソートキーの要素数分以下の処理を繰り返す。
        // 　@　@５－２－１）　@ソートキー.validate()をコールする。
        int l_intSortKeysLength = this.sortKeys.length;
        for (int i = 0; i < l_intSortKeysLength; i++)
        {
            this.sortKeys[i].validate();
        }

        // ６）　@要求ページ番号チェック
        // 　@６－１）　@this.要求ページ番号 == nullであった場合、
        // 　@　@　@　@「要求ページ番号がnull」の例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        // 　@６－２）　@this.要求ページ番号が数字以外の値であった場合、
        // 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }

        // 　@６－３）　@this.要求ページ番号 <= 0であった場合、
        // 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        // ７）　@ページ内表示行数チェック
        // 　@７－１）　@this.ページ内表示行数 == nullであった場合、
        // 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }

        // 　@７－２）　@this.ページ内表示行数が数字以外の値であった場合、
        // 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        // 　@７－３）　@this.ページ内表示行数 <= 0であった場合、
        // 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
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

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminIfoDepShortageReferenceResponse(this);
    }
}
@
