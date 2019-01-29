head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeSrcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS口座開設状況変更検索レスポンス(WEB3AdminInformPTSAccOpenStateChangeSrcResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 柴双紅(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * 管理者・PTS口座開設状況変更検索レスポンス<BR>
 * 管理者・PTS口座開設状況変更検索レスポンスクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeSrcResponse extends WEB3GenResponse
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_inform_pts_acc_open_state_change_src";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200802281243L;

    /**
     * @@roseuid 47C522D40200
     */
    public WEB3AdminInformPTSAccOpenStateChangeSrcResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    protected WEB3AdminInformPTSAccOpenStateChangeSrcResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
