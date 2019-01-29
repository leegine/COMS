head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.56.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理確認リクエスト(WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.215
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者国内債券部店別応募枠管理確認リクエスト)<BR>
 * 管理者国内債券部店別応募枠管理確認リクエスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageConfirmRequest
    extends WEB3AdminBondDomesticRecruitLimitManageCommonRequest
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_recruit_limit_manage_confirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231848L;

    /**
     * (管理者国内債券部店別応募枠管理確認リクエスト)<BR>
     * コンストラクタ
     * @@roseuid 4684ABAF0033
     */
    public WEB3AdminBondDomesticRecruitLimitManageConfirmRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者国内債券部店別応募枠管理確認レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondDomesticRecruitLimitManageConfirmResponse(this);
    }
}
@
