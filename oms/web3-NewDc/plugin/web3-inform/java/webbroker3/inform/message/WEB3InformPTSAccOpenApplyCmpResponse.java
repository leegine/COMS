head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS口座開設申込完了レスポンス(WEB3InformPTSAccOpenApplyCmpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 謝旋 (中訊) 新規作成 モデルNo.123
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (PTS口座開設申込完了レスポンス)<BR>
 * PTS口座開設申込完了レスポンス
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final  String PTYPE = "inform_pts_acc_open_apply_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181638L;

    /**
     * @@roseuid 47B9271A0147
     */
    public WEB3InformPTSAccOpenApplyCmpResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ
     */
    protected WEB3InformPTSAccOpenApplyCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
