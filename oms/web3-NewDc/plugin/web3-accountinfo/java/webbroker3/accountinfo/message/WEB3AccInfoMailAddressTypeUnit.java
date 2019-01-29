head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressTypeUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール種別情報（WEB3AccInfoMailAddressTypeUnit.java）
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/21 武波 (中訊) 新規作成 モデルNo.257
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.common.define.WEB3MailAssortmentDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (メール種別情報)<BR>
 * メール種別情報メッセージ<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressTypeUnit extends Message
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressTypeUnit.class);

    /**
     * (メールアドレス番号)<BR>
     * メールアドレス番号<BR>
     */
    public String mailAddressNo;

    /**
     * (メール種別番号)<BR>
     * メール種別番号<BR>
     * <BR>
     * 1:株式約定メール<BR>
     * 2:株式未約定メール<BR>
     * 3:先OP約定メール<BR>
     * 4:先OP未約定メール<BR>
     * 5:重要メール<BR>
     * 6:案内メール<BR>
     * 7:電子交付メール<BR>
     */
    public String mailAddressTypeNo;

    /**
     * (メール種別情報)<BR>
     * ディフォルトコンストラクタ<BR>
     */
    public WEB3AccInfoMailAddressTypeUnit()
    {

    }

    /**
     * (validateメール種別情報)<BR>
     * メール種別情報に入力値がある場合、設定値チェックを行う。<BR>
     * <BR>
     * １）　@メールアドレス番号チェック<BR>
     * 　@this.メールアドレス番号 != nullの場合、以下処理を実施する。<BR>
     * 　@１－１）this.メールアドレス番号が数字以外の値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03191<BR>
     * 　@１－２）this.メールアドレス番号≦０の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03192<BR>
     * 　@１－３）this.メールアドレス番号はメールアドレス情報[].メールアドレス番号に含まれていない場合、<BR>
     * 　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03194<BR>
     * <BR>
     * ２）　@メール種別番号チェック<BR>
     * 　@２－１）this.メール種別番号 == nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03195<BR>
     * 　@２－２）this.メール種別番号が以下の値以外の値の場合例外をスローする。<BR>
     * 　@　@　@　@　@・”1:株式約定メール”<BR>
     * 　@　@　@　@　@・”2:株式未約定メール”<BR>
     * 　@　@　@　@　@・”3:先OP約定メール”<BR>
     * 　@　@　@　@　@・”4:先OP未約定メール”<BR>
     * 　@　@　@　@　@・”5:重要メール”<BR>
     * 　@　@　@　@　@・”6:案内メール”<BR>
     * 　@　@　@　@　@・”7:電子交付メール”<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03196<BR>
     * <BR>
     * ３）　@メール種別番号・メールアドレスチェック<BR>
     * 　@リクエストデータ.複数アドレスリスト.メールアドレス情報の要素数分Loop処理を行う。<BR>
     * <BR>
     * 　@３－１）this.メールアドレス番号 != null　@且つ<BR>
     * 　@this.メールアドレス番号==メールアドレス情報.メールアドレス番号　@且つ<BR>
     * 　@メールアドレス情報[i].メールアドレス == nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03197<BR>
     * <BR>
     * 　@３－２）this.メール種別番号 == 7:電子交付メールの場合、以下処理を実施する。<BR>
     * 　@　@３－２－１）this.メールアドレス番号 == nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03198<BR>
     * 　@　@３－２－２）　@this.メールアドレス番号==メールアドレス情報.メールアドレス番号　@且つ<BR>
     * 　@　@メールアドレス情報[i].メールアドレス区分が「1:PCメールアドレス」以外の値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_03198<BR>
     * @@param l_mailAddressInfoList - (メールアドレス情報)<BR>
     * メールアドレス情報<BR>
     * @@throws WEB3BaseException
     */
    public void validateMailAddressTypeInfo(
        WEB3AccInfoMailAddressInfoUnit[] l_mailAddressInfoList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMailAddressInfo(WEB3AccInfoMailAddressInfoUnit[])";
        log.entering(STR_METHOD_NAME);

        // １）　@メールアドレス番号チェック
        //this.メールアドレス番号 != nullの場合、以下処理を実施する。
        if (this.mailAddressNo != null)
        {
            //１－１）this.メールアドレス番号が数字以外の値の場合、例外をスローする。
            if (!WEB3StringTypeUtility.isDigit(this.mailAddressNo))
            {
                log.debug("メールアドレス番号が数字以外の値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03191,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "メールアドレス番号が数字以外の値です。"); 
            }

            //１－２）this.メールアドレス番号≦０の場合、例外をスローする。
            if (Integer.parseInt(this.mailAddressNo) <= 0)
            {
                log.debug("メールアドレス番号の入力が不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03192,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "メールアドレス番号の入力が不正です。"); 
            }

            //１－３）this.メールアドレス番号はメールアドレス情報[].メールアドレス番号
            //に含まれていない場合、例外をスローする。
            int l_intlength = 0;
            if (l_mailAddressInfoList != null)
            {
                l_intlength = l_mailAddressInfoList.length;
            }
            boolean l_blnflag = false;
            for (int i = 0; i < l_intlength; i++)
            {
                if (this.mailAddressNo.equals(l_mailAddressInfoList[i].mailAddressNo))
                {
                    l_blnflag = true;
                }
            }
            if (!l_blnflag)
            {
                log.debug("メールアドレス番号がメールアドレス情報に存在しません。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03194,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "メールアドレス番号がメールアドレス情報に存在しません。"); 
            }
        }

        //２）　@メール種別番号チェック
        //２－１）this.メール種別番号 == nullの場合、例外をスローする。 
        if (this.mailAddressTypeNo == null)
        {
            log.debug("メール種別番号が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03195,
                getClass().getName() + "." + STR_METHOD_NAME,
                "メール種別番号が未入力です。"); 
        }

        //　@２－２）this.メール種別番号が以下の値以外の値の場合例外をスローする。
        //　@　@　@　@　@・”1:株式約定メール”
        //　@　@　@　@　@・”2:株式未約定メール”
        //　@　@　@　@　@・”3:先OP約定メール”
        //　@　@　@　@　@・”4:先OP未約定メール”
        //　@　@　@　@　@・”5:重要メール”
        //　@　@　@　@　@・”6:案内メール”
        //　@　@　@　@　@・”7:電子交付メール” 
        if (!WEB3MailAssortmentDivDef.EQUITY_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.EQUITY_NOT_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.FUTURES_OPTION_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.FUTURES_OPTION_NOT_ORDER_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.IMPORTANT_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.GUIDE_MAIL.equals(this.mailAddressTypeNo)
            && !WEB3MailAssortmentDivDef.ELECTRONIC_DELIVER_MAIL.equals(this.mailAddressTypeNo))
        {
            log.debug("メール種別番号の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03196,
                getClass().getName() + "." + STR_METHOD_NAME,
                "メール種別番号の入力が不正です。"); 
        }

        //３）　@メール種別番号・メールアドレスチェック
        //　@リクエストデータ.複数アドレスリスト.メールアドレス情報の要素数分Loop処理を行う。
        int l_intLength = 0;
        if (l_mailAddressInfoList != null);
        {
            l_intLength = l_mailAddressInfoList.length;
        }
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3AccInfoMailAddressInfoUnit l_mailAddressInfo = l_mailAddressInfoList[i];
            //３－１）this.メールアドレス番号 != null　@且つ
            //　@this.メールアドレス番号==メールアドレス情報.メールアドレス番号　@且つ
            //　@メールアドレス情報[i].メールアドレス == nullの場合、例外をスローする。
            if (this.mailAddressNo != null && this.mailAddressNo.equals(l_mailAddressInfo.mailAddressNo)
                && l_mailAddressInfo.mailAddress == null)
            {
                log.debug("選択したメールアドレスが未入力です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03197,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "選択したメールアドレスが未入力です。"); 
            }

            //３－２）this.メール種別番号 == 7:電子交付メールの場合、以下処理を実施する。
            if (WEB3MailAssortmentDivDef.ELECTRONIC_DELIVER_MAIL.equals(this.mailAddressTypeNo))
            {
                //３－２－１）this.メールアドレス番号 == nullの場合、例外をスローする。
                if (this.mailAddressNo == null)
                {
                    log.debug("電子交付メールはPCメールアドレスのみに指定可能です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03198,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "電子交付メールはPCメールアドレスのみに指定可能です。"); 
                }

                //　@　@３－２－２）　@this.メールアドレス番号==メールアドレス情報.メールアドレス番号　@且つ
                //　@　@メールアドレス情報[i].メールアドレス区分が「1:PCメールアドレス」以外の値の場合、例外をスローする。
                if (this.mailAddressNo.equals(l_mailAddressInfo.mailAddressNo)
                    && !WEB3AddressDivDef.PC_MAIL_ADDRESS.equals(l_mailAddressInfo.mailAddressDiv))
                {
                    log.debug("電子交付メールはPCメールアドレスのみに指定可能です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_03198,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "電子交付メールはPCメールアドレスのみに指定可能です。"); 
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
