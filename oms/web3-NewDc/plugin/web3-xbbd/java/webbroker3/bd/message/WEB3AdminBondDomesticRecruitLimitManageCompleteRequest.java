head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理完了リクエスト(WEB3AdminBondDomesticRecruitLimitManageCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.215
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者国内債券部店別応募枠管理完了リクエスト)<BR>
 * 管理者国内債券部店別応募枠管理完了リクエスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageCompleteRequest
    extends WEB3AdminBondDomesticRecruitLimitManageCommonRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_recruit_limit_manage_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231848L;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (管理者国内債券部店別応募枠管理完了リクエスト)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4684B20C031F
     */
    public WEB3AdminBondDomesticRecruitLimitManageCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）スーパークラスのvalidate()を呼び出す。<BR>
     * <BR>
     * ２）暗証番号チェック<BR>
     * 　@　@暗証番号 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_01090<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4684B2480159
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１）スーパークラスのvalidate()を呼び出す。
        super.validate();

        //２）暗証番号チェック
        //暗証番号 == null の場合、例外をスローする。
        if (this.password == null)
        {
            log.debug("暗証番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + STR_METHOD_NAME,
                "暗証番号が未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者国内債券部店別応募枠管理完了レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondDomesticRecruitLimitManageCompleteResponse(this);
    }
}
@
