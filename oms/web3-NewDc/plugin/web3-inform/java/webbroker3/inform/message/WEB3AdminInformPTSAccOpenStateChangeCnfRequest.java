head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeCnfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS口座開設状況変更確認リクエスト(WEB3AdminInformPTSAccOpenStateChangeCnfRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 柴双紅(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * 管理者・PTS口座開設状況変更確認リクエスト<BR>
 * 管理者・PTS口座開設状況変更確認リクエストクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeCnfRequest
    extends WEB3AdminInformPTSAccOpenStateChangeCommonRequest
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_inform_pts_acc_open_state_change_cnf";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200802281520L;

    /**
     * @@roseuid 47C522D400D7
     */
    public WEB3AdminInformPTSAccOpenStateChangeCnfRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformPTSAccOpenStateChangeCnfResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B4F68E01D0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //super.validate()をコールする。
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
