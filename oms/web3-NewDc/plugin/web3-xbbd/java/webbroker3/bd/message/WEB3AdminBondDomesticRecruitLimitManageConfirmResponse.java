head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理確認レスポンス(WEB3AdminBondDomesticRecruitLimitManageConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.215
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * (管理者国内債券部店別応募枠管理確認レスポンス)<BR>
 * 管理者国内債券部店別応募枠管理確認レスポンス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageConfirmResponse
    extends WEB3AdminBondDomesticRecruitLimitManageCommonResponse
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
     * (管理者国内債券部店別応募枠管理確認レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4684CBB90189
     */
    public WEB3AdminBondDomesticRecruitLimitManageConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondDomesticRecruitLimitManageConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
