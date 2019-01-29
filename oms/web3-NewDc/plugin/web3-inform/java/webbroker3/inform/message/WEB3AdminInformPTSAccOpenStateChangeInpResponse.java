head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS口座開設状況変更入力レスポンス(WEB3AdminInformPTSAccOpenStateChangeInpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 柴双紅(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者・PTS口座開設状況変更入力レスポンス<BR>
 * 管理者・PTS口座開設状況変更入力レスポンスクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeInpResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_inform_pts_acc_open_state_change_inp";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200802281454L;

    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;

    /**
     * (変更前申込区分)<BR>
     * 変更前申込区分<BR>
     * <BR>
     * 0：未開設<BR>
     * 1：開設<BR>
     */
    public String beforePtsAccOpenDiv;

    /**
     * @@roseuid 47C522D4025E
     */
    public WEB3AdminInformPTSAccOpenStateChangeInpResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    protected WEB3AdminInformPTSAccOpenStateChangeInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
