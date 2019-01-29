head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListInqCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS申込客一覧問合せ検索条件クラス(WEB3AdminInformPTSAccountListInqCondition.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者・PTS申込客一覧問合せ検索条件クラス)<BR>
 * 管理者・PTS申込客一覧問合せ検索条件クラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListInqCondition extends Message
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccountListInqCondition.class);

    /**
     * (連絡種別)<BR>
     * 連絡種別
     */
    public String informType;

    /**
     * (部店コードの配列)<BR>
     * 部店コードの配列
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;

    /**
     * (申込区分)<BR>
     * 申込区分
     * <BR>
     * 0：未開設<BR>
     * 1：開設<BR>
     */
    public String ptsAccOpenDiv;

    /**
     * (申込日時（自）)<BR>
     * 申込日時（自）
     */
    public Date applyDateFrom;

    /**
     * (申込日時（至）)<BR>
     * 申込日時（至）
     */
    public Date applyDateTo;

    /**
     * @@roseuid 47C522D403C5
     */
    public WEB3AdminInformPTSAccountListInqCondition()
    {

    }

    /**
     * 整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@連絡種別のチェック<BR>
     * <BR>
     * 　@連絡種別 == null の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_01817<BR>
     * <BR>
     * ２）　@部店コードのチェック<BR>
     * <BR>
     * ２－１）<BR>
     * <BR>
     * 　@部店コード == null の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_01429<BR>
     * 　@部店コード.length() == 0 の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_02175<BR>
     * <BR>
     * ２－２）　@部店コードの各要素についてチェックする。<BR>
     * <BR>
     * 　@部店コード != 半角数字 の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_01729<BR>
     * 　@部店コード.length() != 3 の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00834<BR>
     * <BR>
     * ３）　@顧客コードのチェック<BR>
     * <BR>
     * ３－１）　@顧客コード != null  の場合、以下のチェックを行う。<BR>
     * <BR>
     * 　@顧客コード != 半角数字 の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_01043<BR>
     * 　@顧客コード.length() > 6 の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_00836<BR>
     * <BR>
     * ４）　@申込区分のチェック<BR>
     * <BR>
     * ４－１）　@申込区分 != null  の場合、以下のチェックを行う。<BR>
     * <BR>
     * 　@申込区分 != 半角数字 の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_03042<BR>
     * <BR>
     * ５）　@申込日時のチェック<BR>
     * <BR>
     * ５－１）　@申込日時（自） != null and 申込日時（至） != null  の場合、<BR>
     * 　@以下のチェックを行う。<BR>
     * <BR>
     * 　@申込日時（自） > 申込日時（至） の場合、例外をthrowする。<BR>
     * 　@　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@　@:　@BUSINESS_ERROR_03041<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47BA660C01D8
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@連絡種別のチェック
        //連絡種別 == null の場合、例外をthrowする。
        if (this.informType == null)
        {
            log.debug("連絡種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連絡種別が未指定です。");
        }

        //２）　@部店コードのチェック
        //２－１）部店コード == null の場合、例外をthrowする。
        if (this.branchCode == null)
        {
            log.debug("部店コード一覧が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01429,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード一覧が未指定です。");
        }

        //部店コード.length() == 0 の場合、例外をthrowする。
        if (this.branchCode.length == 0)
        {
            log.debug("部店コードの要素数が0です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02175,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が0です。");
        }

        //２－２）　@部店コードの各要素についてチェックする。
        for (int i = 0; i < this.branchCode.length; i++)
        {
            //部店コード != 半角数字 の場合、例外をthrowする。
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("部店コードが数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }

            //部店コード.length() != 3 の場合、例外をthrowする。
            if (this.branchCode[i].length() != 3)
            {
                log.debug("部店コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードのサイズが不正です。");
            }
        }

        //３）　@顧客コードのチェック
        //３－１）　@顧客コード != null  の場合、以下のチェックを行う。
        if (this.accountCode != null)
        {
            //顧客コード != 半角数字 の場合、例外をthrowする。
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("顧客コードの値が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの値が数字以外の値です。");
            }

            //顧客コード.length() > 6 の場合、例外をthrowする。
            if (this.accountCode.length() > 6)
            {
                log.debug("顧客コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードのサイズが不正です。");
            }
        }

        //４）　@申込区分のチェック
        //４－１）　@申込区分 != null  の場合、以下のチェックを行う。
        if (this.ptsAccOpenDiv != null)
        {
            //申込区分 != 半角数字 の場合、例外をthrowする。
            if (!WEB3StringTypeUtility.isDigit(this.ptsAccOpenDiv))
            {
                log.debug("申込区分は半角数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03042,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込区分は半角数字以外の値です。");
            }
        }

        //５）　@申込日時のチェック
        //５－１）　@申込日時（自） != null and
        //申込日時（至） != null  の場合、以下のチェックを行う。
        if (this.applyDateFrom != null && this.applyDateTo != null)
        {
            //申込日時（自） > 申込日時（至） の場合、例外をthrowする。
            if (WEB3DateUtility.compare(this.applyDateFrom, this.applyDateTo) > 0)
            {
                log.debug("申込日時（自）は申込日時（至） より大きいです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03041,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "申込日時（自）は申込日時（至） より大きいです。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
