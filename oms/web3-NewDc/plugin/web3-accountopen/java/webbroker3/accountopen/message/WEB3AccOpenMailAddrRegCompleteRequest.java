head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddrRegCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録完了リクエスト(WEB3AccOpenMailAddrRegCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 張騰宇(中訊) 新規作成 モデル 162
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設メールアドレス登録完了リクエスト)<BR>
 * 口座開設メールアドレス登録完了リクエスト<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccOpenMailAddrRegCompleteRequest extends WEB3GenRequest
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddrRegCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_mail_addr_reg_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908101118L;

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (メールアドレス)<BR>
     * メールアドレス
     */
    public String mailAddress;

    /**
     * (姓)<BR>
     * 顧客姓（漢字）<BR>
     */
    public String accountFamilyName;

    /**
     * (名)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;

    /**
     * (仲介業扱者コード)<BR>
     * 仲介業扱者コード<BR>
     */
    public String brokerageCode;

    /**
     * (口座区分)<BR>
     * 口座区分 <BR>
     * <BR>
     * 0：個人アカウント <BR>
     * 1：法@人アカウント<BR>
     */
    public String accountType;

    /**
     * (リンク元判別コード)<BR>
     * リンク元判別コード<BR>
     */
    public String linkCode;

    /**
    *
     */
    public WEB3AccOpenMailAddrRegCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenMailAddrRegCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * １）　@証券会社コードのチェック <BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02775<BR>
     * <BR>
     * ２）　@部店コードのチェック <BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02174<BR>
     * <BR>
     * ３）　@メールアドレスのチェック <BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_01700<BR>
     * <BR>
     * ４）　@姓のチェック <BR>
     * 　@４－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_03167<BR>
     * <BR>
     * ５）　@口座区分のチェック <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00604<BR>
     * 　@５－２）　@「0：個人アカウント、1：法@人アカウント」以外の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_00605<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //証券会社コードのチェック
        //未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            log.debug("証券会社コードがnull。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02775,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードがnull。");
        }

        //部店コードのチェック
        //未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }

        //メールアドレスのチェック
        //未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.mailAddress))
        {
            log.debug("メールアドレスが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01700,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "メールアドレスが未指定です。");
        }

        //姓のチェック
        //未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.accountFamilyName))
        {
            log.debug("姓が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03167,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "姓が未入力です。");
        }

        //口座区分のチェック
        //未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.accountType))
        {
            log.debug("口座区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00604,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "口座区分が未指定です。");
        }
        else
        {
            //「0：個人アカウント、1：法@人アカウント」以外の場合、例外をスローする。
            if (!WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(this.accountType)
                && !WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(this.accountType))
            {
                log.debug("口座区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00605,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "口座区分が存在しないコード値です。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
