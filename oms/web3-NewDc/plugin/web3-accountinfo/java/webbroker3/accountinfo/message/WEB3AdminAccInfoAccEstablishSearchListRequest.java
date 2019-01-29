head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishSearchListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧リクエスト(WEB3AdminAccInfoAccEstablishSearchListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09  何文敏(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccountTypeEnum;

import webbroker3.accountinfo.define.WEB3AccInfoDataContentDef;
import webbroker3.accountinfo.define.WEB3AccInfoKeyItemDef;
import webbroker3.accountinfo.define.WEB3AccInfoLoginLockDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧リクエスト)<BR>
 * 管理者お客様情報新規口座開設・口座移管・ログインロック顧客検索一覧リクエスト<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishSearchListRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccEstablishSearchListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_info_acc_establish_search_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610082163L;

    /**
     * @@roseuid 418F39EF0290
     */
    public WEB3AdminAccInfoAccEstablishSearchListRequest()
    {

    }

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (扱者コード)<BR>
     * 扱者コード<BR>
     */
    public String traderCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;

    /**
     * (口座種別)<BR>
     * 口座種別<BR>
     * <BR>
     * 0：　@全て<BR>
     * 1：　@個人客<BR>
     * 2：　@法@人客<BR>
     */
    public String accountTypeCode;

    /**
     * (データ内容番号)<BR>
     * データ内容番号 <BR>
     * <BR>
     * 00：　@データ内容未選択<BR>
     * 01：　@新規口座開設案内用データ<BR>
     * 02：　@振込みカード用データ<BR>
     * 03：　@口座移管案内用データ<BR>
     */
    public String dataContentDiv;

    /**
     * (口座開設日（自）)<BR>
     * 口座開設日（自）<BR>
     */
    public Date accountOpenDateFrom;

    /**
     * (口座開設日（至）)<BR>
     * 口座開設日（至）<BR>
     */
    public Date accountOpenDateTo;

    /**
     * (ログインロック区分)<BR>
     * ログインロック区分<BR>
     * <BR>
     * 0：　@全て<BR>
     * 1：　@ログインロック客<BR>
     */
    public String loginLockDiv;

    /**
     * (ソートキー)<BR>
     * お客様情報ソートキー[]<BR>
     * 対象項目：部店コード、扱者コード、顧客コード、口座開設日<BR>
     */
    public WEB3AccInfoSortKey[] sortKeys;

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
     * (MAX処理件数)<BR>
     * MAX処理件数<BR>
     */
    public String maxCount;

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １） 「部店コード」チェック<BR>
     * １－１） 部店コードの要素数 = 0 or 部店コード = null の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_1757<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00833<BR>
     * １－２） 各要素に数字以外の文字がある場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_01729<BR>
     * <BR>
     * ２） 「扱者コード」チェック<BR>
     * ２－１） 数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_01913<BR>
     * <BR>
     * ３） 「顧客コード 」チェック<BR>
     * ３－１） 数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * ４） 「顧客名（カナ）」チェック<BR>
     * ４－１） 全角以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_01691<BR>
     * <BR>
     * ５） 「口座種別」チェック<BR>
     * ５－１） 口座種別 != （0、1、2）の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_02668<BR>
     * <BR>
     * ６） 「データ内容番号」チェック<BR>
     * ６－１） データ内容番号 != （00、01、02、03）の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_02669<BR>
     * <BR>
     * ７） 「ログインロック区分」チェック<BR>
     * ７－１） ログインロック区分 != （0、1）の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_02670<BR>
     * <BR>
     * ８） 「要求ページ番号」チェック<BR>
     * ８－１） 要求ページ番号 = null or 要求ページ番号 <= 0 の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00089<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00616<BR>
     * ８－２） 数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * ９）ページ内表示行数チェック<BR>
     * ９－１） ページ内表示行数 = null or ページ内表示行数 <= 0 の場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_02224<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00617<BR>
     * ９－２） 数字以外の文字が含まれる場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * １０）「ソートキー」チェック<BR>
     * １０－１） ソートキーが未入力lの場合、例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00231<BR>
     * １０－２） （ソートキーの要素数 == 0）の場合、 例外をスローする。<BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00232<BR>
     * １０－３） ソートキーの要素数分、下記のチェックを繰り返して行う。<BR>
     * 　@　@１０－３－１） ソートキー.validate()をコールする。<BR>
     * 　@　@１０－３－２） ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・部店コード <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・扱者コード <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・顧客コード <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@・口座開設日 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@ 　@　@　@tag:   BUSINESS_ERROR_00086<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4163B2130031
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // １） 「部店コード」チェック
        // １－１） 部店コードの要素数 = 0 or 部店コード = null の場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("「部店コード」 = " + this.branchCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName()  + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        if (this.branchCode.length == 0)
        {
            //例外
            log.debug("「部店コード」 = " + this.branchCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName()  + STR_METHOD_NAME,
                "部店コードの要素数が０です。");
        }

        //１－２） 各要素に数字以外の文字がある場合、例外をスローする。
        for (int i = 0; i < this.branchCode.length; i ++)
        {
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                log.debug("「部店コード」 = " + this.branchCode[i]);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "部店コードが数値以外の値です。");
            }
        }

        // ２） 「扱者コード」チェック
        // ２－１） 数字以外の文字が含まれる場合、例外をスローする。
        if (this.traderCode != null && !WEB3StringTypeUtility.isDigit(this.traderCode))
        {
            log.debug("「扱者コード」 = " + this.traderCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01913,
                this.getClass().getName()  + STR_METHOD_NAME,
                "扱者コード（文字列）の文字種が不正です。");
        }

        // ３） 「顧客コード 」チェック
        // ３－１） 数字以外の文字が含まれる場合、例外をスローする。
        if (this.accountCode != null && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("「顧客コード 」 = " + this.accountCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName()  + STR_METHOD_NAME,
                "顧客コードの値が数字以外の値です。");
        }

        // ４） 「顧客名（カナ）」チェック
        // ４－１） 全角以外の文字が含まれる場合、例外をスローする。
        if (this.accountNameKana != null && !WEB3StringTypeUtility.isMulti(this.accountNameKana))
        {
            log.debug("「顧客名（カナ）」 = " + this.accountNameKana);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01691,
                this.getClass().getName()  + STR_METHOD_NAME,
                "顧客名が全角文字ではありません。");
        }

        // ５） 「口座種別」チェック
        // ５－１） 口座種別 != （0、1、2）の場合、例外をスローする。
        String l_strOther = MainAccountTypeEnum.OTHER.intValue() + "";
        String l_strIndividual = MainAccountTypeEnum.INDIVIDUAL_ACCOUNT.intValue() + "";
        String l_strCorporate = MainAccountTypeEnum.JOINT_OWNERSHIP.intValue() + "";
        if (!l_strOther.equals(this.accountTypeCode)
            && !l_strIndividual.equals(this.accountTypeCode)
            && !l_strCorporate.equals(this.accountTypeCode))
        {
            log.debug("「口座種別」 = " + this.accountTypeCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02668,
                this.getClass().getName()  + STR_METHOD_NAME,
                "口座種別が存在しないコード値です。");
        }

        // ６） 「データ内容番号」チェック
        // ６－１） データ内容番号 != （00、01、02、03）の場合、例外をスローする。
        if (!WEB3AccInfoDataContentDef.DATA_CONTENT_NOT_SELECT.equals(this.dataContentDiv)
                && !WEB3AccInfoDataContentDef.NEW_ACC_OPEN_GUIDANCE_DATA.equals(this.dataContentDiv)
                && !WEB3AccInfoDataContentDef.TRANSFER_CARD_DATA.equals(this.dataContentDiv)
                && !WEB3AccInfoDataContentDef.ACCOUNT_TRANSFER_GUIDANCE_DATA.equals(this.dataContentDiv))
        {
            log.debug("「データ内容」 = " + this.dataContentDiv);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02669,
                this.getClass().getName()  + STR_METHOD_NAME,
                "データ内容が存在しないコード値です。");
        }

        // ７） 「ログインロック区分」チェック
        // ７－１） ログインロック区分 != （0、1）の場合、例外をスローする。
        if (!WEB3AccInfoLoginLockDivDef.ALL.equals(this.loginLockDiv)
                && !WEB3AccInfoLoginLockDivDef.LOGIN_LOCK_ACCOUNT.equals(this.loginLockDiv))
        {
            log.debug("「ログインロック区分」 = " + this.loginLockDiv);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02670,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ログインロック区分が存在しないコード値です。");
        }

        // ８） 「要求ページ番号」チェック
        // ８－１） 要求ページ番号 = null の場合、例外をスローする。
        if (this.pageIndex == null)
        {
            log.debug("「要求ページ番号」 = " + this.pageIndex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName()  + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }

        // ８－２） 数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("「要求ページ番号」 = " + this.pageIndex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        // 要求ページ番号 <= 0 の場合、例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("「要求ページ番号」 = " + this.pageIndex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName()  + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }

        // ９）ページ内表示行数チェック
        // ９－１） ページ内表示行数 = null 例外をスローする。
        if (this.pageSize == null)
        {
            log.debug("「ページ内表示行数チ」 = " + this.pageSize);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }

        // ９－２） 数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("「ページ内表示行数チ」 = " + this.pageSize);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }

        // ページ内表示行数 <= 0 の場合、例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("「ページ内表示行数チ」 = " + this.pageSize);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        // １０）「ソートキー」チェック

        //１０－１） ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("「ソートキー」 = " + this.sortKeys);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        // １０－２） （ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("「ソートキー」 = " + this.sortKeys);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName()  + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }

        // １０－３） ソートキーの要素数分、下記のチェックを繰り返して行う。
        // 　@　@１０－３－１） ソートキー.validate()をコールする。
        //　@ 　@１０－３－２） ソートキー.キー項目が下記の項目名以外の場合、 例外をスローする。
        //　@　@ 　@　@　@　@　@　@　@　@　@・部店コード
        //　@　@　@ 　@　@　@　@　@　@　@　@・扱者コード
        //　@　@　@　@ 　@　@　@　@　@　@　@・顧客コード
        //　@　@　@　@　@ 　@　@　@　@　@　@・口座開設日
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            this.sortKeys[i].validate();

            if (!WEB3AccInfoKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.TRADER_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AccInfoKeyItemDef.ACCOUNTOPENDATE.equals(this.sortKeys[i].keyItem))
            {
                log.debug("「ソートキー」 = " + this.sortKeys);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName()  + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }
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
        return new WEB3AdminAccInfoAccEstablishSearchListResponse(this);
    }
}
@
