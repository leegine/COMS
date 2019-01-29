head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込入力リクエスト(WEB3AccOpenApplyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
Revesion History : 2009/08/10 張騰宇(中訊) 仕様変更 モデル162
Revesion History : 2009/09/02 張騰宇(中訊) 仕様変更 モデル204
*/

package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AccOpenAdminDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (口座開設申込入力リクエスト)<BR>
 * 口座開設申込入力リクエスト<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenApplyInputRequest extends WEB3GenRequest
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AccOpenApplyInputRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081617L;

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
     * (メールアドレス登録ID)<BR>
     * メールアドレス登録ID<BR>
     */
    public String mailAddressID;

    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;

    /**
     * (管理者区分)<BR>
     * 管理者区分 <BR>
     * <BR>
     * 0：管理者、扱者以外 <BR>
     * 1：管理者、扱者 <BR>
     * null：0と同じ<BR>
     */
    public String adminDiv;

    /**
     * @@roseuid 41B45E7C00BB
     */
    public WEB3AccOpenApplyInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenApplyInputResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@証券会社コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00827<BR>
     * <BR>
     * ２）　@部店コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ３）　@管理者区分のチェック <BR>
     * 　@３－１）　@管理者区分　@！＝　@null、且つ管理者区分が「0、1」以外の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03182<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419C932603BC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //* １）　@証券会社コードのチェック
        //* 　@１－１）　@未入力の場合、例外をスローする。
        if (this.institutionCode == null || "".equals(this.institutionCode.trim()))
        {
            log.debug("証券会社コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + STR_METHOD_NAME,
                "証券会社コード = null.");
        }
        
        //* ２）　@部店コードのチェック
        //* 　@２－１）　@未入力の場合、例外をスローする。
        if (this.branchCode == null || "".equals(this.branchCode.trim()))
        {
            log.debug("部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }

        //３）　@管理者区分のチェック
        //       　@３－１）　@管理者区分　@！＝　@null、且つ管理者区分が「0、1」以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isEmpty(this.adminDiv)
            && !WEB3AccOpenAdminDivDef.NOT_ADMIN.equals(this.adminDiv)
            && !WEB3AccOpenAdminDivDef.ADMIN.equals(this.adminDiv))
        {
            log.debug("管理者区分存在しないコード値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03182,
                this.getClass().getName() + STR_METHOD_NAME,
                "管理者区分存在しないコード値です。");
        }

        log.exiting(STR_METHOD_NAME);        
    }
}
@
