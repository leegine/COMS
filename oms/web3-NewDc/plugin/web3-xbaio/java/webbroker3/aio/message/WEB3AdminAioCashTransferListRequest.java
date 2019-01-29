head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.08.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金一覧結果リクエスト(WEB3AdminAioCashTransferListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/03 何文敏 (中訊) 新規作成　@仕様変更モデル NO.693 NO.700
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AdminAioCashStatusDef;
import webbroker3.aio.define.WEB3AdminAioOrderTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (入出金一覧結果リクエスト)<BR>
 * 入出金一覧結果リクエストクラス<BR>
 *
 * @@author 何文敏 (中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashTransferListRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_aio_cash_transfer_list";

    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashTransferListRequest.class);

    /**
     * (部店コード)<BR>
     * 画面にて入力された部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 画面にて入力された顧客コード<BR>
     */
    public String accountCode;

    /**
     * (受渡日)<BR>
     * 画面にて入力された受渡日<BR>
     */
    public Date deliveryDate;

    /**
     * (注文種別)<BR>
     * 画面にて選択された注文種別<BR>
     * <BR>
     * －－－－－－－－－－－－－－－－－－－－－－－－－<BR>
     * 000： 全て<BR>
     * ＜入金＞<BR>
     * 100： 入金全て<BR>
     * 101： SONAR入金<BR>
     * 102： バーチャル入金<BR>
     * 103： ネット入金<BR>
     * 104： 振替(株先証拠金から預り金)<BR>
     * 105： 為替保証金振替(為替保証金から預り金)<BR>
     * 106： その他振替(Xから預り金)<BR>
     * ＜出金＞<BR>
     * 200： 出金全て<BR>
     * 201： 出金<BR>
     * 202： 振替(預り金から株先証拠金)<BR>
     * 203： 為替保証金振替(預り金から為替保証金)<BR>
     * 204： その他振替(預り金からX)<BR>
     * －－－－－－－－－－－－－－－－－－－－－－－－－<BR>
     * <BR>
     */
    public String orderType;

    /**
     * (ステータス)<BR>
     * 画面にて選択されたステータス<BR>
     * <BR>
     * －－－－－<BR>
     * 0： 全て<BR>
     * 1： 完了<BR>
     * 2： 未処理<BR>
     * 9： エラー<BR>
     * －－－－－<BR>
     */
    public String cashinoutStatus;

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
     * @@roseuid 45C3F158001F
     */
    public WEB3AdminAioCashTransferListRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １） 「部店コード」チェック <BR>
     * １－１） 部店コードの要素数 = 0 or 部店コード = null <BR>
     * の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01757<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00833<BR>
     * １－２） 各要素に数字以外の文字がある場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２） 「顧客コード 」チェック <BR>
     * 　@・顧客コード != null の場合、以下のチェックを行う。<BR>
     * ２－１） 数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01043<BR>
     * ２－２） ６桁以外の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00836<BR>
     * ２－３） 部店コードの要素数 != 1 の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02728<BR>
     * <BR>
     * ３） 「受渡日」チェック <BR>
     * ３－１） 受渡日 = null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01079<BR>
     * <BR>
     * ４） 「注文種別」チェック <BR>
     * ４－１） 注文種別 != （000、100、101、102、103、104、105、106、200、201、202、203、204）の場<BR>
     * 合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_01439<BR>
     * <BR>
     * ５） 「ステータス」チェック  <BR>
     * ５－１） ステータス != （0、1、2、9）の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00890<BR>
     * <BR>
     * ６） 「要求ページ番号」チェック <BR>
     * ６－１） 要求ページ番号 = null or 要求ページ番号 <= 0
     * の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00089<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00616<BR>
     * ６－２） 数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * ７） 「ページ内表示行数」チェック <BR>
     * ７－１） ページ内表示行数 = null or ページ内表示行数 <= 0
     * の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_02224<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00091<BR>
     * ７－２） 数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * @@roseuid 45B6F80500FE
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １－１） 部店コードの要素数 = 0 or 部店コード = null の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("部店コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }

        if (this.branchCode.length == 0)
        {
            log.debug("部店コードの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が０です。");
        }

        // 各要素に数字以外の文字がある場合、例外をスローする。
        for (int i = 0; i < this.branchCode.length; i++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("部店コードが数値以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }
        }

        // 顧客コード != null の場合、以下のチェックを行う。
        if (this.accountCode != null)
        {
            // ２－１） 数字以外の文字が含まれる場合、例外をスローする。00811
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.debug("顧客コードの値が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードの値が数字以外の値です。");
            }

            // ２－２） ６桁以外の場合、例外をスローする。
            if (this.accountCode.length() != 6)
            {
                log.debug("顧客コードのサイズが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "顧客コードのサイズが不正です。");
            }

            // ２－３） 部店コードの要素数 != 1 の場合、例外をスローする。
            if (this.branchCode.length != 1)
            {
                log.debug("部店コードの要素数が1以外です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02728,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "部店コードの要素数が1以外です。");
            }
        }

        // ３） 「受渡日」チェック
        // ３－１） 受渡日 = null の場合、例外をスローする。
        if (this.deliveryDate == null)
        {
            log.debug("受渡日が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01079,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "受渡日が未指定です。");
        }

        // ４－１） 注文種別 != （000、100、101、102、103、104、105、106、200、201、202、203、204）の場合、例外をスローする。
        if (!WEB3AdminAioOrderTypeDef.ALL.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_ALL.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_FX_GUARANTY_TO_DESPOSIT.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_OTHER_X_TO_ACCOUNT_BALANCE.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHIN_TRANSFER_DEPOSIT_TO_MARGIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_ALL.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_FX_GUARANTY_TO_DESPOSIT.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_OTHER_X_TO_ACCOUNT_BALANCE.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.CASHOUT_TRANSFER_DEPOSIT_TO_MARGIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.NET_CASHIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.SONAR_CASHIN.equals(this.orderType)
            && !WEB3AdminAioOrderTypeDef.VIRTUAL_CASHIN.equals(this.orderType))
        {
            log.debug("注文種別の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01439,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文種別の値が存在しないコード値です。");
        }

        // ５－１） ステータス != （0、1、2、9）の場合、例外をスローする。
        if (!WEB3AdminAioCashStatusDef.ALL.equals(this.cashinoutStatus)
            && !WEB3AdminAioCashStatusDef.COMPLETE.equals(this.cashinoutStatus)
            && !WEB3AdminAioCashStatusDef.ERROR.equals(this.cashinoutStatus)
            && !WEB3AdminAioCashStatusDef.NOT_TRANSACTION.equals(this.cashinoutStatus))
        {
            log.debug("ステータスが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00890,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ステータスが存在しないコード値です。");
        }

        // ６－１） 要求ページ番号 = null の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        //６－２） 数字以外の文字が含まれる場合、例外をスローする。
        else if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        //要求ページ番号 <= 0 の場合、例外をスローする。
        else if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        // ページ内表示行数 = null の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        // 数字以外の文字が含まれる場合、例外をスローする。
        else if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        // ページ内表示行数 <= 0 の場合、例外をスローする。
        else if (Integer.parseInt(this.pageSize) <= 0)
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
     * @@return WEB3GenResponse
     * @@roseuid 4158EB660189
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAioCashTransferListResponse(this);
    }
}
@
