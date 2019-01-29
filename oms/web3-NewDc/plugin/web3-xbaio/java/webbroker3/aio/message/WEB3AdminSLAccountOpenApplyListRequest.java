head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.52.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminSLAccountOpenApplyListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者証券担保ローン申込顧客一覧リクエスト(WEB3AdminSLAccountOpenApplyListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/07 何文敏 (中訊) 新規作成 仕様変更・モデルNo.756
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3ApplyStateDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者証券担保ローン申込顧客一覧リクエスト)<BR>
 * 管理者証券担保ローン申込顧客一覧リクエスト<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminSLAccountOpenApplyListRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminSLAccountOpenApplyListRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_sl_account_open_apply_list";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709071051L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (ストックローン口座番号)<BR>
     * ストックローン口座番号<BR>
     */
    public String stockLoanAccount;

    /**
     * (申込状況)<BR>
     * 申込状況<BR>
     * <BR>
     * 0：申込 1：開設 2：成約 3：解約 4：閉鎖<BR>
     */
    public String applyStatus;

    /**
     * (申込日From)<BR>
     * 申込日From<BR>
     */
    public Date applyDateFrom;

    /**
     * (申込日To)<BR>
     * 申込日To<BR>
     */
    public Date applyDateTo;

    /**
     * (開設日From)<BR>
     * 開設日From<BR>
     */
    public Date accountOpenDateFrom;

    /**
     * (開設日To)<BR>
     * 開設日To<BR>
     */
    public Date accountOpenDateTo;

    /**
     * (成約日From)<BR>
     * 成約日From<BR>
     */
    public Date executeDateFrom;

    /**
     * (成約日To)<BR>
     * 成約日To<BR>
     */
    public Date executeDateTo;

    /**
     * (解約日From)<BR>
     * 解約日From<BR>
     */
    public Date cancelDateFrom;

    /**
     * (解約日To)<BR>
     * 解約日To<BR>
     */
    public Date cancelDateTo;

    /**
     * (閉鎖日From)<BR>
     * 閉鎖日From<BR>
     */
    public Date closeDateFrom;

    /**
     * (閉鎖日To)<BR>
     * 閉鎖日To<BR>
     */
    public Date closeDateTo;

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
     */
    public WEB3SLSortKey[] sortKeys;

    /**
     * @@roseuid 46E0BE47036B
     */
    public WEB3AdminSLAccountOpenApplyListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * 1)　@部店コードのチェック<BR>
     * 　@1-1)　@this.部店コード !=null であり、且つ桁数!=3桁の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00834<BR>
     * 　@1-2)　@半角数字以外の文字が入力されている場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_01729<BR>
     * <BR>
     * 2)　@顧客コードのチェック<BR>
     * 　@2-1)　@this.顧客コード !=null であり、且つ桁数!=6桁の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00836<BR>
     * 　@2-2)　@半角数字以外の文字が入力されている場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_01043<BR>
     * <BR>
     * 3)　@ストックローン口座番号のチェック<BR>
     * 　@3-1)　@this.ストックローン口座番号 !=nullであり、<BR>
     * 　@　@　@且つ桁数が11桁以上の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_02902<BR>
     * 　@3-2)　@半角数字以外の文字が入力されている場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_02903<BR>
     * <BR>
     * 4)　@申込状況のチェック<BR>
     * 　@this.申込状況 !=null であり、以下の値以外の場合、例外をスローする。<BR>
     * 　@　@　@0：（申込）<BR>
     * 　@　@　@1：（開設）<BR>
     * 　@　@　@2：（成約）<BR>
     * 　@　@　@3：（解約）<BR>
     * 　@　@　@4：（閉鎖）<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_02904<BR>
     * <BR>
     * 5)　@申込日日付整合性チェック<BR>
     * 　@5-1)　@this.申込日From !=null 且つ <BR>
     *  　@　@this.申込日To!=nullの場合以下のチェックを行う。<BR>
     * 　@5-2)　@this.申込日From ＞ this.申込日To の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_01762<BR>
     * <BR>
     * 6)　@開設日日付整合性チェック<BR>
     * 　@6-1)　@this.開設日From !=null 且つ <BR>
     * 　@　@　@this.開設日To!=nullの場合以下のチェックを行う。<BR>
     * 　@6-2)　@this.開設日From ＞ this.開設日To の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_01328<BR>
     * <BR>
     * 7)　@成約日日付整合性チェック<BR>
     * 　@7-1)　@this.成約日From !=null 且つ <BR>
     * 　@　@　@this.成約日To!=nullの場合以下のチェックを行う。<BR>
     * 　@7-2)　@this.成約日From ＞ this.成約日To の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_02905<BR>
     * <BR>
     * 8)　@解約日日付整合性チェック<BR>
     * 　@8-1)　@this.解約日From !=null 且つ <BR>
     * 　@　@　@this.解約日To!=nullの場合以下のチェックを行う。<BR>
     * 　@8-2)　@this.解約日From ＞ this.解約日To の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_02906<BR>
     * <BR>
     * 9)　@閉鎖日日付整合性チェック<BR>
     * 　@9-1)　@this.閉鎖日From !=null 且つ <BR>
     * 　@　@　@this.閉鎖日To!=nullの場合以下のチェックを行う。<BR>
     * 　@9-2)　@this.閉鎖日From ＞ this.閉鎖日To の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_02907<BR>
     * 10)　@担保ローンソートキーのチェック<BR>
     * 　@10-1)　@this.担保ローンソートキー==nullの場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00231<BR>
     * 　@10-2)　@this.担保ローンソートキーの要素数==0の場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00232<BR>
     * 　@10-3)　@this.担保ローンソートキーの要素数分、以下を繰り返す。<BR>
     * 　@　@10-3-1)　@this.担保ローンソートキー.キー項目==nullの場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00085<BR>
     * 　@　@10-3-2)　@this.担保ローンソートキー.昇順／降順==nullの場合、例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00087<BR>
     * 　@　@10-3-3)　@this.担保ローンソートキー.昇順／降順が以下の値以外だった場合、<BR>
     * 例外をスローする。<BR>
     * 　@　@　@"A:昇順"<BR>
     * 　@　@　@"D:降順"<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00088<BR>
     * <BR>
     * 11)　@要求ページ番号チェック<BR>
     * 　@11-1)　@this.要求ページ番号==nullの値であれば例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00089<BR>
     * 　@11-2)　@this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 12)　@ページ内表示行数チェック<BR>
     * 　@12-1)　@this.ページ内表示行数==nullの値であれば例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00091<BR>
     * 　@12-2)　@this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     * 　@class:　@WEB3BusinessLayerException<BR>
     * 　@tag　@:　@BUSINESS_ERROR_00092<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46CE630F0358
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.exiting(STR_METHOD_NAME);

        // 1) 部店コードのチェック
        // 1-1) this.部店コード !=null であり、且つ桁数!=3桁の場合、例外をスローする。
        if (this.branchCode != null && this.branchCode.length() != 3)
        {
            log.debug("部店コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードのサイズが不正です。");
        }
        // 1-2) 半角数字以外の文字が入力されている場合、例外をスローする。
        if (this.branchCode != null && !WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("部店コードが数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが数値以外の値です。");
        }
        // 2-1) this.顧客コード !=null であり、且つ桁数!=6桁の場合、例外をスローする。
        if (this.accountCode != null && this.accountCode.length() != 6)
        {
            log.debug("顧客コードのサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードのサイズが不正です。");
        }
        //  2-2) 半角数字以外の文字が入力されている場合、例外をスローする。
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("顧客コードの値が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }
        // 3-1) this.ストックローン口座番号 !=null であり、且つ桁数が11桁以上の場合、例外をスローする。
        if (this.stockLoanAccount != null && this.stockLoanAccount.length() >= 11)
        {
            log.debug("ストックローン口座番号のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02902,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ストックローン口座番号のサイズが不正です。");
        }
        // 3-2) 半角数字以外の文字が入力されている場合、例外をスローする。
        if (this.stockLoanAccount != null && !WEB3StringTypeUtility.isDigit(this.stockLoanAccount))
        {
            log.debug("ストックローン口座番号が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02903,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ストックローン口座番号が半角数字以外の値です。");
        }
        // this.申込状況 !=null であり、以下の値以外の場合、例外をスローする。
        // 0：（申込）
        // 1：（開設）
        // 2：（成約）
        // 3：（解約）
        // 4：（閉鎖）
        if (this.applyStatus != null &&
            !WEB3ApplyStateDivDef.APPLY.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.ACCOUNT_OPEN.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.EXECUTE.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.CANCEL.equals(this.applyStatus) &&
            !WEB3ApplyStateDivDef.CLOSE.equals(this.applyStatus))
        {
            log.debug("申込状況の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02904,
                getClass().getName() + "." + STR_METHOD_NAME,
                "申込状況の値が存在しないコード値です。");
        }
        //  5-1) this.申込日From !=null 且つ this.申込日To !=nullの場合以下のチェックを行う。
        if (this.applyDateFrom != null && this.applyDateTo != null)
        {
            // 5-2) this.申込日From ＞ this.申込日To の場合、例外をスローする。
            if (WEB3DateUtility.compareToDay(this.applyDateFrom, this.applyDateTo) > 0)
            {
                log.debug("申込日（自）は申込日（至）より大きいです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01762,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "申込日（自）は申込日（至）より大きいです。");
            }
        }
        // 6-1) this.開設日From !=null 且つ this.開設日To !=nullの場合以下のチェックを行う。
        if (this.accountOpenDateFrom != null && this.accountOpenDateTo != null)
        {
            // 6-2) this.開設日From ＞ this.開設日To の場合、例外をスローする。
            if (WEB3DateUtility.compareToDay(this.accountOpenDateFrom, this.accountOpenDateTo) > 0)
            {
                log.debug("口座開設日（自）は口座開設日（至）より大きいです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01328,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "口座開設日（自）は口座開設日（至）より大きいです。");
            }
        }
        // 7-1) this.成約日From !=null 且つ this.成約日To !=nullの場合以下のチェックを行う。
        if (this.executeDateFrom != null && this.executeDateTo != null)
        {
            // 7-2) this.成約日From ＞ this.成約日To の場合、例外をスローする。
            if (WEB3DateUtility.compareToDay(this.executeDateFrom, this.executeDateTo) > 0)
            {
                log.debug("成約日Fromは成約日Toより大きいです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02905,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "成約日Fromは成約日Toより大きいです。");
            }
        }
        // 8-1) this.解約日From !=null 且つ this.解約日To !=nullの場合以下のチェックを行う。
        if (this.cancelDateFrom != null && this.cancelDateTo != null)
        {
            // 8-2) this.解約日From ＞ this.解約日To の場合、例外をスローする。
            if (WEB3DateUtility.compareToDay(this.cancelDateFrom, this.cancelDateTo) > 0)
            {
                log.debug("解約日Fromは解約日Toより大きいです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02906,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "解約日Fromは解約日Toより大きいです。");
            }
        }
        // 9-1) this.閉鎖日From !=null 且つ this.閉鎖日To !=nullの場合以下のチェックを行う。
        if (this.closeDateFrom != null && this.closeDateTo != null)
        {
            // 9-2) this.閉鎖日From ＞ this.閉鎖日To の場合、例外をスローする。
            if (WEB3DateUtility.compareToDay(this.closeDateFrom, this.closeDateTo) > 0)
            {
                log.debug("閉鎖日Fromは閉鎖日Toより大きいです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02907,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "閉鎖日Fromは閉鎖日Toより大きいです。");
            }
        }
        // 10-1) this.担保ローンソートキー==nullの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        // 10-2) this.担保ローンソートキーの要素数==0の場合、例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }

        int l_intLength = this.sortKeys.length;
        for (int i = 0; i < l_intLength; i++)
        {
            // 10-3-1) this.担保ローンソートキー.キー項目==nullの場合、例外をスローする。
            if (this.sortKeys[i].keyItem == null)
            {
                log.debug("ソートキーのキー項目が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目が未指定です。");
            }
            // 10-3-2) this.担保ローンソートキー.昇順／降順==nullの場合、例外をスローする。
            if (this.sortKeys[i].ascDesc == null)
            {
                log.debug("昇順／降順が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が未指定です。");
            }
            // 10-3-3) this.担保ローンソートキー.昇順／降順が以下の値以外だった場合、例外をスローする。
            // "A:昇順"
            // "D:降順"
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) ||
                    WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc)))
            {
                log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        // 11-1) this.要求ページ番号==nullの値であれば例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        //  11-2) this.要求ページ番号が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        // 12-1) this.ページ内表示行数==nullの値であれば例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        // 12-2) this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト<BR>
     * @@roseuid 46CE83B20329
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSLAccountOpenApplyListResponse(this);
    }
}
@
