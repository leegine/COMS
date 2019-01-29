head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderReceiveHistoryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文受付履歴照会レスポンス(WEB3AdminBondOrderReceiveHistoryResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.216
*/
package webbroker3.bd.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者注文受付履歴照会レスポンス)<BR>
 * 管理者注文受付履歴照会レスポンス<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_receive_history";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231733L;

    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     */
    public String productName;

    /**
     * (応募開始日)<BR>
     * 応募開始日<BR>
     */
    public Date recruitStartDate;

    /**
     * (応募終了日)<BR>
     * 応募終了日<BR>
     */
    public Date recruitEndDate;

    /**
     * (注文受付履歴)<BR>
     * 注文受付履歴<BR>
     */
    public WEB3BondOrderAcceptHistoryUnit[] orderAcceptHistory;

    /**
     * (国内債券部店別応募枠情報)<BR>
     * 国内債券部店別応募枠情報<BR>
     */
    public WEB3BondDomesticBranchRecruitLimitInfo bondDomesticBranchRecruitLimitInfo;

    /**
     * (管理者注文受付履歴照会レスポンス)<BR>
     * コンストラクタ<BR>
     * @@roseuid 4683527D0330
     */
    public WEB3AdminBondOrderReceiveHistoryResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminBondOrderReceiveHistoryResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
