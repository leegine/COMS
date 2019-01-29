head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メールアドレス情報（WEB3AccInfoMailAddressInfoUnit.java）
Author Name      : Daiwa Institute of Research
Revision History : 2010/02/21 武波 (中訊) 新規作成 モデルNo.257
*/
package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AddressDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (メールアドレス情報)<BR>
 * メールアドレス情報メッセージ<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressInfoUnit extends Message
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccInfoMailAddressInfoUnit.class);

    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;

    /**
     * (メールアドレス番号)<BR>
     * メールアドレス番号<BR>
     */
    public String mailAddressNo;

    /**
     * (メールアドレス区分)<BR>
     * メールアドレス区分<BR>
     * <BR>
     * 1:PCメールアドレス<BR>
     * 2:携帯メールアドレス<BR>
     */
    public String mailAddressDiv;

    /**
     * (メールアドレス情報)<BR>
     * ディフォルトコンストラクタ
     */
    public WEB3AccInfoMailAddressInfoUnit()
    {

    }

    /**
     * (validateメールアドレス情報)
     * メールアドレス情報に入力値がある場合、設定値チェックを行う。<BR>
     * <BR>
     * １）　@メールアドレスチェック<BR>
     * <BR>
     * 　@１-１）this.メールアドレスに入力値がある場合、以下処理を行う。<BR>
     * <BR>
     * 　@　@　@メールアドレスとして適切な値かを判定する。<BR>
     * メールアドレス情報.メールアドレス区分== 「2:携帯メールアドレス」の場合、以下処理を実施する。<BR>
     * 　@　@　@[WEB3StringTypeUtility.isMailAddress() に指定する引数]<BR>
     * 　@　@　@l_str： this.メールアドレス<BR>
     * <BR>
     * 　@　@　@WEB3StringTypeUtility.isMailAddress()の戻り値が false の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02415<BR>
     * <BR>
     * ２）　@メールアドレス番号チェック<BR>
     * 　@２－１）this.メールアドレス番号 == nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03190<BR>
     * 　@２－２）this.メールアドレス番号が数字以外の値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03191<BR>
     * 　@２－３）this.メールアドレス番号≦０の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03192<BR>
     * <BR>
     * ３）　@メールアドレス区分チェック<BR>
     * 　@３－１）this.メールアドレス区分 == nullの場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03193<BR>
     * @@throws WEB3BaseException
     */
    public void validateMailAddressInfo() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMailAddressInfo()";
        log.entering(STR_METHOD_NAME);

        //１）　@メールアドレスチェック
        //１-１）this.メールアドレスに入力値がある場合、以下処理を行う。
        if (!WEB3StringTypeUtility.isEmpty(this.mailAddress))
        {
            //メールアドレス情報.メールアドレス区分== 「2:携帯メールアドレス」の場合、以下処理を実施する。
            if (WEB3AddressDivDef.MOBILE_MAIL_ADDRESS.equals(this.mailAddressDiv))
            {
                if (!WEB3StringTypeUtility.isMailAddress(this.mailAddress))
                {
                    log.debug("メールアドレスが不正です。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        "メールアドレスが不正です。"); 
                }
            }
        }

        //２）　@メールアドレス番号チェック
        //２－１）this.メールアドレス番号 == nullの場合、例外をスローする。
        if (this.mailAddressNo == null)
        {
            log.debug("メールアドレス番号が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03190,
                getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレス番号が未入力です。"); 
        }

        //２－２）this.メールアドレス番号が数字以外の値の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.mailAddressNo))
        {
            log.debug("メールアドレス番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03191,
                getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレス番号が数字以外の値です。"); 
        }

        //２－３）this.メールアドレス番号≦０の場合、例外をスローする。
        if (Integer.parseInt(this.mailAddressNo) <= 0)
        {
            log.debug("メールアドレス番号の入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03192,
                getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレス番号の入力が不正です。"); 
        }

        //３）　@メールアドレス区分チェック
        //３－１）this.メールアドレス区分 == nullの場合、例外をスローする。
        if (this.mailAddressDiv == null)
        {
            log.debug("メールアドレス区分が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03193,
                getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレス区分が未入力です。"); 
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
