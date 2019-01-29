head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityPTSInputExecCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・株式（PTS）出来入力完了リクエスト（WEB3AdminEquityPTSInputExecCompleteRequest.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル173
 */
package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・株式(PTS)出来入力完了リクエスト)<BR>
 * 管理者・株式(PTS)出来入力完了リクエストクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputExecCompleteRequest extends WEB3AdminEquityPTSInputCancelExecCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_exec_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801221708L;

    /**
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * (管理者・株式（PTS）出来入力完了リクエスト)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 4795A0F80311
     */
    public WEB3AdminEquityPTSInputExecCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEquityPTSInputExecCompleteResponse(this);
    }
}
@
