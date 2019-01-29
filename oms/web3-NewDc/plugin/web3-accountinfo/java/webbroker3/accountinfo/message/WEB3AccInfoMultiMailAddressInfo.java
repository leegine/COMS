head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMultiMailAddressInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 複数アドレス情報(WEB3AccInfoMultiMailAddressInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/28 武波 (中訊) 新規作成 仕様変更・モデルNo.217
Revision History : 2007/08/30 武波 (中訊) 仕様変更管理No.221
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ImportantConnectionMailFlagDef;
import webbroker3.common.define.WEB3InformationMail2FlagDef;
import webbroker3.common.define.WEB3OrderExeFlagDef;
import webbroker3.common.define.WEB3OrderUnexeFlagDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (複数アドレス情報)<BR>
 * 複数アドレス情報クラス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccInfoMultiMailAddressInfo extends Message
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMultiMailAddressInfo.class);

    /**
     * (メールアドレス２)<BR>
     * メールアドレス２<BR>
     */
    public String mailAddress2;

    /**
     * (メールアドレス３)<BR>
     * メールアドレス３<BR>
     */
    public String mailAddress3;

    /**
     * (約定通知送信フラグ)<BR>
     * 約定通知送信フラグ<BR>
     * 0：未送信<BR>
     * 1：基本メールアドレス<BR>
     * 2：メールアドレス２<BR>
     * 3：メールアドレス３<BR>
     * 4：全てのメールアドレス<BR>
     */
    public String execMailFlag;

    /**
     * (未約定通知送信フラグ)<BR>
     * 未約定通知送信フラグ<BR>
     * 0：未送信<BR>
     * 1：基本メールアドレス<BR>
     * 2：メールアドレス２<BR>
     * 3：メールアドレス３<BR>
     * 4：全てのメールアドレス<BR>
     */
    public String unExecMailFlag;

    /**
     * (重要連絡メール送信フラグ)<BR>
     * 重要メール送信フラグ<BR>
     * 1：基本メールアドレス<BR>
     * 2：メールアドレス２<BR>
     * 3：メールアドレス３<BR>
     * 4：全てのメールアドレス<BR>
     */
    public String importantMailFlag;

    /**
     * (案内メール２送信フラグ)<BR>
     * 案内メール２送信フラグ<BR>
     * 0：未送信<BR>
     * 1：基本メールアドレス<BR>
     * 2：メールアドレス２<BR>
     * 3：メールアドレス３<BR>
     * 4：全てのメールアドレス<BR>
     */
    public String guidanceMailFlag2;

    /**
     * (メールアドレス２削除フラグ)<BR>
     * メールアドレス２削除フラグ<BR>
     * true：　@削除<BR>
     * false：　@削除でない<BR>
     */
    public boolean mailAddressDelFlag2;

    /**
     * (メールアドレス３削除フラグ)<BR>
     * メールアドレス３削除フラグ<BR>
     * true：　@削除<BR>
     * false：　@削除でない<BR>
     */
    public boolean mailAddressDelFlag3;

    /**
     * (複数アドレス情報)<BR>
     * ディフォルトコンストラクタ<BR>
     */
    public WEB3AccInfoMultiMailAddressInfo()
    {

    }
    /**
     * (validate複数アドレス送信フラグ)<BR>
     * メールアドレス、送信フラグ間の整合性をチェックする。<BR>
     * <BR>
     * <BR>
     * １） 基本メールアドレス有効チェック<BR>
     * 　@　@　@以下、いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@・ （引数）基本メールアドレス == null<BR>
     * 　@　@　@　@・ （引数）基本メールアドレス == ""<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@BUSINESS_ERROR_02892<BR>
     * <BR>
     * <BR>
     * ２） this.約定通知送信フラグの設定値整合性チェック<BR>
     * <BR>
     * 　@２-１） this.約定通知送信フラグの設定値が以下以外の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@[設定値] "0", "1", "2", "3", "4"<BR>
     * 　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02893<BR>
     * <BR>
     * 　@２-２） this.約定通知送信フラグ == "2"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@・ this.メールアドレス２ == null<BR>
     * 　@　@　@　@　@　@　@・ this.メールアドレス２ == ""<BR>
     * 　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02894<BR>
     * <BR>
     * 　@２-３） this.約定通知送信フラグ == "3"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@・ this.メールアドレス３ == null<BR>
     * 　@　@　@　@　@　@　@・ this.メールアドレス３ == ""<BR>
     * 　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02895<BR>
     * <BR>
     * <BR>
     * ３） this.未約定通知送信フラグの設定値整合性チェック<BR>
     * <BR>
     * 　@３-１） this.未約定通知送信フラグの設定値が以下以外の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[設定値] "0", "1", "2", "3", "4"<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02896<BR>
     * <BR>
     * 　@３-２） this.未約定通知送信フラグ == "2"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@・ this.メールアドレス２ == null<BR>
     * 　@　@　@　@　@　@・ this.メールアドレス２ == ""<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02894<BR>
     * <BR>
     * 　@３-３） this.未約定通知送信フラグ == "3"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@・ this.メールアドレス３ == null<BR>
     * 　@　@　@　@　@　@・ this.メールアドレス３ == ""<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02895<BR>
     * <BR>
     * <BR>
     * ４） this.重要連絡メール送信フラグの設定値整合性チェック<BR>
     * <BR>
     * 　@４-１） this.重要連絡メール送信フラグの設定値が以下以外の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@[設定値] "1", "2", "3", "4"<BR>
     * 　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:　@BUSINESS_ERROR_02897<BR>
     * <BR>
     * 　@４-２） this.重要連絡メール送信フラグ == "2"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@・ this.メールアドレス２ == null<BR>
     * 　@　@　@　@　@　@・ this.メールアドレス２ == ""<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02894<BR>
     * <BR>
     * 　@４-３） this.重要連絡メール送信フラグ == "3"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@・ this.メールアドレス３ == null<BR>
     * 　@　@　@　@　@　@・ this.メールアドレス３ == ""<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02895<BR>
     * <BR>
     * <BR>
     * ５） this.案内メール２送信フラグの設定値整合性チェック<BR>
     * <BR>
     * 　@５-１） this.案内メール２送信フラグの設定値が以下以外の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@[設定値] "0", "1", "2", "3", "4"<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02898<BR>
     * <BR>
     * 　@５-２） this.案内メール２送信フラグ == "2"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@・ this.メールアドレス２ == null<BR>
     * 　@　@　@　@　@　@・ this.メールアドレス２ == ""<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02894<BR>
     * <BR>
     * 　@５-３） this.案内メール２送信フラグ == "3"の時、<BR>
     * 　@　@以下いずれかに該当の場合、例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@　@　@・ this.メールアドレス３ == null<BR>
     * 　@　@　@　@　@　@・ this.メールアドレス３ == ""<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02895<BR>
     * @@param l_strBasicMailAddress - (基本メールアドレス)<BR>
     * 基本メールアドレス。<BR>
     * @@throws WEB3BaseException
     */
    public void validateMultiSendMailAddressInfoFlag(
        String l_strBasicMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMultiSendMailAddressInfoFlag(String)";
        log.entering(STR_METHOD_NAME);

        //１） 基本メールアドレス有効チェック
        //以下、いずれかに該当の場合、例外をスローする。
        //・ （引数）基本メールアドレス == null
        //・ （引数）基本メールアドレス == ""
        if (WEB3StringTypeUtility.isEmpty(l_strBasicMailAddress))
        {
            log.debug("基本メールアドレスが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02892,
                this.getClass().getName() + STR_METHOD_NAME,
                "基本メールアドレスが未指定です。");
        }

        //２） this.約定通知送信フラグの設定値整合性チェック
        //２-１） this.約定通知送信フラグの設定値が以下以外の場合、例外をスローする。
        //[設定値] "0", "1", "2", "3", "4"
        if (!(WEB3OrderExeFlagDef.NOT_SEND_MAIL.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.BASE_MAIL_ADDRESS.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.MAIL_ADDRESS_2.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.MAIL_ADDRESS_3.equals(this.execMailFlag)
            || WEB3OrderExeFlagDef.ALL_MAIL_ADDRESS.equals(this.execMailFlag)))
        {
            log.debug("約定通知送信フラグが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02893,
                this.getClass().getName() + STR_METHOD_NAME,
                "約定通知送信フラグが存在しないコード値です。");
        }
        //２-２） this.約定通知送信フラグ == "2"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス２ == null
        //・ this.メールアドレス２ == ""
        if (WEB3OrderExeFlagDef.MAIL_ADDRESS_2.equals(this.execMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("メールアドレス２が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス２が未指定です。");
            }
        }
        //２-３） this.約定通知送信フラグ == "3"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス３ == null
        //・ this.メールアドレス３ == ""
        if (WEB3OrderExeFlagDef.MAIL_ADDRESS_3.equals(this.execMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("メールアドレス３が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス３が未指定です。");
            }
        }

        //３） this.未約定通知送信フラグの設定値整合性チェック
        //３-１） this.未約定通知送信フラグの設定値が以下以外の場合、例外をスローする。
        //[設定値] "0", "1", "2", "3", "4"
        if (!(WEB3OrderUnexeFlagDef.NOT_SEND_MAIL.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.BASE_MAIL_ADDRESS.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.MAIL_ADDRESS_2.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.MAIL_ADDRESS_3.equals(this.unExecMailFlag)
            || WEB3OrderUnexeFlagDef.ALL_MAIL_ADDRESS.equals(this.unExecMailFlag)))
        {
            log.debug("未約定通知送信フラグが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02896,
                this.getClass().getName() + STR_METHOD_NAME,
                "未約定通知送信フラグが存在しないコード値です。");
        }
        //３-２） this.未約定通知送信フラグ == "2"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス２ == null
        //・ this.メールアドレス２ == ""
        if (WEB3OrderUnexeFlagDef.MAIL_ADDRESS_2.equals(this.unExecMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("メールアドレス２が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス２が未指定です。");
            }
        }
        //３-３） this.未約定通知送信フラグ == "3"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス３ == null
        //・ this.メールアドレス３ == ""
        if (WEB3OrderUnexeFlagDef.MAIL_ADDRESS_3.equals(this.unExecMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("メールアドレス３が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス３が未指定です。");
            }
        }

        //４） this.重要連絡メール送信フラグの設定値整合性チェック
        //４-１） this.重要連絡メール送信フラグの設定値が以下以外の場合、例外をスローする。
        //[設定値] "1", "2", "3", "4"
        if (!(WEB3ImportantConnectionMailFlagDef.BASE_MAIL_ADDRESS.equals(this.importantMailFlag)
            || WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_2.equals(this.importantMailFlag)
            || WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_3.equals(this.importantMailFlag)
            || WEB3ImportantConnectionMailFlagDef.ALL_MAIL_ADDRESS.equals(this.importantMailFlag)))
        {
            log.debug("重要連絡メール送信フラグが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02897,
                this.getClass().getName() + STR_METHOD_NAME,
                "重要連絡メール送信フラグが存在しないコード値です。");
        }
        //４-２） this.重要連絡メール送信フラグ == "2"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス２ == null
        //・ this.メールアドレス２ == ""
        if (WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_2.equals(this.importantMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("メールアドレス２が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス２が未指定です。");
            }
        }
        //４-３） this.重要連絡メール送信フラグ  == "3"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス３ == null
        //・ this.メールアドレス３ == ""
        if (WEB3ImportantConnectionMailFlagDef.MAIL_ADDRESS_3.equals(this.importantMailFlag))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("メールアドレス３が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス３が未指定です。");
            }
        }

        //５） this.案内メール２送信フラグの設定値整合性チェック
        //５-１） this.案内メール２送信フラグの設定値が以下以外の場合、例外をスローする。
        //[設定値] "0", "1", "2", "3", "4"
        if (!(WEB3InformationMail2FlagDef.NOT_SEND_MAIL.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.BASE_MAIL_ADDRESS.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.MAIL_ADDRESS_2.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.MAIL_ADDRESS_3.equals(this.guidanceMailFlag2)
            || WEB3InformationMail2FlagDef.ALL_MAIL_ADDRESS.equals(this.guidanceMailFlag2)))
        {
            log.debug("案内メール２送信フラグが存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02898,
                this.getClass().getName() + STR_METHOD_NAME,
                "案内メール２送信フラグが存在しないコード値です。");
        }
        //５-２） this.案内メール２送信フラグ == "2"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス２ == null
        //・ this.メールアドレス２ == ""
        if (WEB3InformationMail2FlagDef.MAIL_ADDRESS_2.equals(this.guidanceMailFlag2))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress2))
            {
                log.debug("メールアドレス２が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02894,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス２が未指定です。");
            }
        }
        //５ -３） this.案内メール２送信フラグ  == "3"の時、以下いずれかに該当の場合、例外をスローする。
        //・ this.メールアドレス３ == null
        //・ this.メールアドレス３ == ""
        if (WEB3InformationMail2FlagDef.MAIL_ADDRESS_3.equals(this.guidanceMailFlag2))
        {
            if (WEB3StringTypeUtility.isEmpty(this.mailAddress3))
            {
                log.debug("メールアドレス３が未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02895,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス３が未指定です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate複数メールアドレス)<BR>
     * メールアドレス２、メールアドレス３に入力値がある場合、<BR>
     * 設定値がメールアドレスとして適切な値かをチェックする。<BR>
     * <BR>
     * <BR>
     * １） メールアドレス２に入力値がある場合、以下処理を行う。<BR>
     * <BR>
     * 　@１-１） this.メールアドレス２削除フラグ == true の場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02899<BR>
     * <BR>
     * 　@１-２） this.メールアドレス２削除フラグ == false の場合、<BR>
     * 　@　@１-２-１） メールアドレスとして適切な値かを判定する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[WEB3StringTypeUtility.isMailAddress() に指定する引数]<BR>
     * 　@　@　@　@　@　@　@l_str： this.メールアドレス２<BR>
     * <BR>
     * 　@　@１-２-２） WEB3StringTypeUtility.isMailAddress()の戻り値が false の場合、<BR>
     * 　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:　@BUSINESS_ERROR_00777<BR>
     * <BR>
     * ２） メールアドレス３に入力値がある場合、以下処理を行う。<BR>
     * <BR>
     * 　@２-１） this.メールアドレス３削除フラグ == true の場合、<BR>
     * 　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@BUSINESS_ERROR_02900<BR>
     * <BR>
     * 　@２-２） this.メールアドレス３削除フラグ == false の場合、<BR>
     * 　@　@２-２-１） メールアドレスとして適切な値かを判定する。<BR>
     * <BR>
     * 　@　@　@　@　@　@[WEB3StringTypeUtility.isMailAddress() に指定する引数]<BR>
     * 　@　@　@　@　@　@　@l_str： this.メールアドレス３<BR>
     * <BR>
     * 　@　@２-２-２） WEB3StringTypeUtility.isMailAddress()の戻り値が false の場合、<BR>
     * 　@　@　@　@　@　@　@　@例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@　@　@tag:　@BUSINESS_ERROR_00777<BR>
     * @@throws WEB3BaseException
     */
    public void validateMultiMailAddressInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateMultiMailAddressInfo()";
        log.entering(STR_METHOD_NAME);

        //１） メールアドレス２に入力値がある場合、以下処理を行う。
        if (!WEB3StringTypeUtility.isEmpty(this.mailAddress2))
        {
            //１-１） this.メールアドレス２削除フラグ == true の場合、
            //例外をスローする。
            if (this.mailAddressDelFlag2)
            {
                log.debug("メールアドレス２削除の場合は、メールアドレス２が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02899,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス２削除の場合は、メールアドレス２が指定不可です。");
            }

            //１-２） this.メールアドレス２削除フラグ == false の場合、
            if (!this.mailAddressDelFlag2)
            {
                //１-２-１） メールアドレスとして適切な値かを判定する。
                //[WEB3StringTypeUtility.isMailAddress() に指定する引数]
                //l_str： this.メールアドレス２
                boolean l_blnIsMailAddress = WEB3StringTypeUtility.isMailAddress(this.mailAddress2);

                //１-２-２） WEB3StringTypeUtility.isMailAddress()の戻り値が false の場合、
                //例外をスローする。
                if (!l_blnIsMailAddress)
                {
                    log.debug("メールアドレスチェックエラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "メールアドレスチェックエラー。");
                }
            }
        }

        //２） メールアドレス３に入力値がある場合、以下処理を行う。
        if (!WEB3StringTypeUtility.isEmpty(this.mailAddress3))
        {
            //２-１） this.メールアドレス３削除フラグ == true の場合、
            //例外をスローする。
            if (this.mailAddressDelFlag3)
            {
                log.debug("メールアドレス３削除の場合は、メールアドレス３が指定不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02900,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "メールアドレス３削除の場合は、メールアドレス３が指定不可です。");
            }

            //２-２） this.メールアドレス３削除フラグ == false の場合、
            if (!this.mailAddressDelFlag3)
            {
                //２-２-１） メールアドレスとして適切な値かを判定する。
                //[WEB3StringTypeUtility.isMailAddress() に指定する引数]
                //l_str： this.メールアドレス３
                boolean l_blnIsMailAddress = WEB3StringTypeUtility.isMailAddress(this.mailAddress3);

                //２-２-２） WEB3StringTypeUtility.isMailAddress()の戻り値が false の場合、
                //例外をスローする。
                if (!l_blnIsMailAddress)
                {
                    log.debug("メールアドレスチェックエラー。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00777,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "メールアドレスチェックエラー。");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
