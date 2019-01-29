head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報口座情報(WEB3AccInfoAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
Revesion History : 2005/12/23 鄭徳懇 (中訊) 仕様変更No.073
Revesion History : 2006/02/03 王維（日本中訊）仕様変更No.085
Revesion History : 2006/10/30 趙林鵬 (中訊) モデル134
Revesion History : 2007/03/08 吉麗ナ (中訊) 仕様変更管理No.208
Revesion History : 2007/07/13 武波 (中訊) 仕様変更管理No.219
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (お客様情報口座情報)<BR>
 * お客様情報口座情報メッセージ<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoAccountInfo extends Message
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoAccountInfo.class);

    /**
     * (金融機@関名称)<BR>
     * 金融機@関名称<BR>
     */
    public String financialInstitutionName;

    /**
     * (支店コード)<BR>
     * 支店コード<BR>
     */
    public String financialBranchCode;

    /**
     * (支店名)<BR>
     * 支店名<BR>
     */
    public String financialBranchName;

    /**
     * (口座種類区分)<BR>
     * 口座種類区分<BR>
     * <BR>
     * 1：普通<BR>
     * 2：当座<BR>
     */
    public String financialAccountType;

    /**
     * (口座種類名)<BR>
     * 口座種類名<BR>
     * <BR>
     * ※”普通預金”，”当座預金”等の文字列<BR>
     */
    public String financialAccountTypeName;

    /**
     * (口座番号)<BR>
     * 口座番号<BR>
     */
    public String financialAccountCode;

    /**
     * (口座名義人)<BR>
     * 口座名義人<BR>
     */
    public String financialAccountName;

    /**
     * (振込先登録区分)<BR>
     * 振込先登録区分<BR>
     * <BR>
     * 0:未登録　@1:登録済　@9:該当データなし<BR>
     */
    public String bankAccountRegi;

    /**
     * (金融機@関コード)<BR>
     * 金融機@関コード<BR>
     */
    public String financialInstitutionCode;

    /**
     * (通貨コード)<BR>
     * 通貨コード<BR>
     */
    public String currencyCode;

    /**
     * (振替区分)<BR>
     * 振替区分<BR>
     * <BR>
     * 1:銀行振込　@5:郵便振込　@<BR>
     */
    public String transferDiv;

    /**
     * (振込先区分)<BR>
     * 振込先区分<BR>
     * ○振替区分1の場合<BR>
     * 　@1：同行同店無料<BR>
     * 　@2：同行<BR>
     * 　@4：他行<BR>
     * 　@5：同行同店有料<BR>
     * 　@9：その他<BR>
     * ○振替区分5の場合<BR>
     * 　@5：無料<BR>
     */
    public String transferAccountDiv;

    /**
     * (取扱区分)<BR>
     * 取扱区分<BR>
     * 1：電信<BR>
     * 2：文書<BR>
     */
    public String tradeHandleDiv;

    /**
     * @@roseuid 418F39EE004E
     */
    public WEB3AccInfoAccountInfo()
    {

    }

    /**
     * (set口座種類名)<BR>
     * 預金区分（※）を口座種類名に変更してセットする。<BR>
     * <BR>
     * ※振込先金融機@関テーブル.預金区分<BR>
     * <BR>
     * －（預金区分 == 1：普通預金）の場合、<BR>
     * ”普通預金”をthis.口座種類名にセットする。<BR>
     * －（預金区分 == 2：当座預金）の場合、<BR>
     * ”当座預金”をthis.口座種類名にセットする。<BR>
     * －（預金区分 == 3：その他）の場合、<BR>
     * ”その他”をthis.口座種類名にセットする。<BR>
     * －（預金区分 == 4：貯蓄預金）の場合、<BR>
     * ”貯蓄預金”をthis.口座種類名にセットする。<BR>
     * <BR>
     * @@param l_strFinSaveDiv - 預金区分
     *
     * 1：普通預金　@2：当座預金　@3：その他　@4：貯蓄預金
     *
     * ※振込先金融機@関テーブル.預金区分
     * @@roseuid 415CD497003C
     */
    public void setFinancialAccountTypeName(String l_strFinSaveDiv)
    {
        final String STR_METHOD_NAME = " setFinancialAccountTypeName(String)";
        log.entering(STR_METHOD_NAME);


        if (WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strFinSaveDiv))
        {
            //（預金区分 == 1：普通預金）の場合、”普通預金”をthis.口座種類名にセットする。
            this.financialAccountTypeName = "普通預金";
        }
        else if (WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strFinSaveDiv))
        {
            //（預金区分 == 2：当座預金）の場合、”当座預金”をthis.口座種類名にセットする。
            this.financialAccountTypeName = "当座預金";
        }
        else if (WEB3FinSaveDivDef.OTHER.equals(l_strFinSaveDiv))
        {
            //（預金区分 == 3：その他）の場合、”その他”をthis.口座種類名にセットする。
            this.financialAccountTypeName = "その他";
        }
        else if (WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strFinSaveDiv))
        {
            //（預金区分 == 4：貯蓄預金）の場合、”貯蓄預金”をthis.口座種類名にセットする。
            this.financialAccountTypeName = "貯蓄預金";
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@金融機@関コードのチェック<BR>
     * 　@　@１－１）　@未入力の場合は、例外をスローする。<BR>
     * 　@　@１－２）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02341<BR>
     * 　@　@１－３）　@文字数が4byteより大きい場合は、例外をスローする<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02342<BR>
     * <BR>
     * ２）　@金融機@関名称のチェック<BR>
     * 　@２－１）　@全角文字（double byte charactor）<BR>
     * 以外が含まれる場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01097<BR>
     * 　@２－２）　@文字数が16（32byte）より大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01098<BR>
     * <BR>
     * ３）　@支店コードのチェック<BR>
     * 　@３－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01099<BR>
     * 　@３－２）　@文字数が3byteでない場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01100<BR>
     * <BR>
     * ４）　@支店名のチェック<BR>
     * 　@４－１）　@全角文字（double byte charactor）以外が含まれる場合は、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01101<BR>
     * 　@４－２）　@文字数が16（32byte）より大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01102<BR>
     * <BR>
     * ５）　@口座種類名のチェック<BR>
     * 　@５－１）　@全角文字（double byte charactor）以外が含まれる場合は、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01103<BR>
     * 　@５－２）　@文字数が5（10byte）より大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01104<BR>
     * <BR>
     * ６）　@口座番号のチェック<BR>
     * 　@６－１）　@半角数字以外が含まれる場合は、例外をスローする。<BR>
     * 　@　@　@　@　@（ただし、null値は除く）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01105<BR>
     * 　@６－２）　@文字数が7byteより大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01106<BR>
     * <BR>
     * ７）　@口座名義人のチェック<BR>
     * 　@７－１）　@全角文字（double byte charactor）以外が含まれる場合は、<BR>
     * 例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01107<BR>
     * 　@７－２）　@文字数が20（40byte）より大きい場合は、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01108<BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CDA300210
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //  １）　@金融機@関コードのチェック

        //  １－１）　@未入力の場合は、例外をスローする。
        if (this.financialInstitutionCode == null || this.financialInstitutionCode.length() == 0 )
        {
            log.debug("銀行コードが未入力です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02346,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銀行コードが未入力です。");
        }

        //  １－２）　@半角数字以外が含まれる場合は、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.financialInstitutionCode))
        {
            log.debug("金融機@關コードが数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02341,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "金融機@關コードが数字以外の値です。");
        }

        //  １－３）　@文字数が4byteより大きい場合は、例外をスローする
        if (WEB3StringTypeUtility.getByteLength(this.financialInstitutionCode) > 4)
        {
            log.debug("金融機@關コードの文字数が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02342,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "金融機@關コードの文字数が不正です。");
        }

        //２）　@金融機@関名称のチェック

        if (this.financialInstitutionName != null)
        {
            //２－１）　@全角文字（double byte charactor）以外が含まれる場合は、例外をスローする。
            if (!WEB3StringTypeUtility.isMulti(this.financialInstitutionName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01097,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "金融機@関名称全角文字（double byte charactor）以外が含まれる");
            }

            //２－２）　@文字数が16（32byte）より大きい場合は、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.financialInstitutionName) > 32)
            {
                log.debug("[金融機@関名称の文字数] = " + WEB3StringTypeUtility.getByteLength(this.financialInstitutionName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01098,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "金融機@関名称文字数が16（32byte）より大きい場合");
            }
        }

        //３）　@支店コードのチェック
        if (this.financialBranchCode != null)
        {
            //３－１）　@支店コードは、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.financialBranchCode))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01099,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "支店コード支店コード");
            }

            //３－２）　@文字数が3byteでない場合は、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.financialBranchCode) != 3)
            {
                log.debug("[支店コードの文字数] = " + WEB3StringTypeUtility.getByteLength(this.financialBranchCode));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01100,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "支店コード文字数が3byteでない");
            }
        }

        //４）　@支店名のチェック
        if (this.financialBranchName != null)
        {
            //４－１）　@全角文字（double byte charactor）以外が含まれる場合は、例外をスローする。
            if (!WEB3StringTypeUtility.isMulti(this.financialBranchName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01101,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "支店名全角文字（double byte charactor）以外が含まれる");
            }

            //４－２）　@文字数が16（32byte）より大きい場合は、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.financialBranchName) > 32)
            {
                log.debug("[支店名の文字数] = " + WEB3StringTypeUtility.getByteLength(this.financialBranchName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01102,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "支店名文字数が16（32byte）より大きい場合");
            }
        }

        //５）　@口座種類名のチェック
        if (this.financialAccountTypeName != null)
        {
            //５－１）　@全角文字（double byte charactor）以外が含まれる場合は、例外をスローする。
            if (!WEB3StringTypeUtility.isMulti(this.financialAccountTypeName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01103,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "口座種類名全角文字（double byte charactor）以外が含まれる");
            }

            //５－２）　@文字数が5（10byte）より大きい場合は、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.financialAccountTypeName) > 10)
            {
                log.debug("[口座種類名の文字数] = " + WEB3StringTypeUtility.getByteLength(this.financialAccountTypeName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01104,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "口座種類名文字数が5（10byte）より大きい");
            }
        }

        //６）　@口座番号のチェック
        if (this.financialAccountCode != null)
        {
            //６－１）　@半角数字以外が含まれる場合は、例外をスローする。
            //（ただし、null値は除く）
            if (!WEB3StringTypeUtility.isDigit(this.financialAccountCode))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01105,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "口座番号半角数字以外が含まれる");
            }

            //６－２）　@文字数が7byteより大きい場合は、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.financialAccountCode) > 7)
            {
                log.debug("[口座番号の文字数] = " + WEB3StringTypeUtility.getByteLength(this.financialAccountCode));
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01106,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "口座番号文字数が7byteより大きい");
            }
        }

        //７）　@口座名義人のチェック
        if (this.financialAccountName != null)
        {
            //７－１）　@全角文字（double byte charactor）以外が含まれる場合は、例外をスローする。
            if (!WEB3StringTypeUtility.isMulti(this.financialAccountName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01107,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "口座名義人全角文字（double byte charactor）以外が含まれる");
            }

            //７－２）　@文字数が20（40byte）より大きい場合は、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(this.financialAccountName) > 40)
            {
                log.debug("[口座名義人の文字数] = " + WEB3StringTypeUtility.getByteLength(this.financialAccountName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01108,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "口座名義人文字数が20（40byte）より大きい");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
