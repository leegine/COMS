head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputCancelExecCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・株式(PTS)出来入力取消共通レスポンス（WEB3AdminEquityPTSInputCancelExecCommonResponse.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル172
 */
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式(PTS)出来入力取消共通レスポンス)<BR>
 * 管理者・株式(PTS)出来入力取消共通レスポンスクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputCancelExecCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_cancel_exec_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801221700L;

    /**
     * (注文詳細)<BR>
     */
    public WEB3AdminEquityPTSOrderDetailUnit orderDetail;

    /**
     * (約定履歴)<BR>
     */
    public WEB3AdminEquityPTSExecHistory[] execHistories = null;

    /**
     *
     * (管理者・株式(PTS)出来入力取消共通レスポンス)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 4795A0F80236
     */
    public WEB3AdminEquityPTSInputCancelExecCommonResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminEquityPTSInputCancelExecCommonResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
