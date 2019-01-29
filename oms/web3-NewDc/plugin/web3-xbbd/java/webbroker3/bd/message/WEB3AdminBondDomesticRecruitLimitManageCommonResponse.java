head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.36.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticRecruitLimitManageCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券部店別応募枠管理共通レスポンス(WEB3AdminBondDomesticRecruitLimitManageCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.215
*/
package webbroker3.bd.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者国内債券部店別応募枠管理共通レスポンス)<BR>
 * 管理者国内債券部店別応募枠管理共通レスポンス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondDomesticRecruitLimitManageCommonResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_recruit_limit_manage_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231842L;

    /**
     * (国内債券部店別応募枠情報)<BR>
     * 国内債券部店別応募枠情報<BR>
     * <BR>
     * 国内債券部店別応募枠情報のリスト<BR>
     */
    public WEB3BondDomesticBranchRecruitLimitInfo[] bondDomesticBranchRecruitLimitInfo;

    /**
     * @@roseuid 46A473FE0186
     */
    public WEB3AdminBondDomesticRecruitLimitManageCommonResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondDomesticRecruitLimitManageCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
