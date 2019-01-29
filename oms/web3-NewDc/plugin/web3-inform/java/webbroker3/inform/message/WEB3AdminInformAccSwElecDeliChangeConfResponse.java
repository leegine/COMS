head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.50.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliChangeConfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込変更確認レスポンス（WEB3AdminInformAccSwElecDeliChangeConfResponse.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 トウ鋒鋼 (中訊) 新規作成　@仕様変更モデル110
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・口座切替・電子交付申込変更確認レスポンス)<BR>
 * 管理者・口座切替・電子交付申込変更確認レスポンス<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliChangeConfResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_acc_sw_elec_deli_change_conf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709191117L;

    /**
     * @@roseuid 46F0900C0353
     */
    public WEB3AdminInformAccSwElecDeliChangeConfResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminInformAccSwElecDeliChangeConfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
