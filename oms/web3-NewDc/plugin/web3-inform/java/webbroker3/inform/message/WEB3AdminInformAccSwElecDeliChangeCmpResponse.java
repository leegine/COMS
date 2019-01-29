head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliChangeCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込変更完了レスポンス（WEB3AdminInformAccSwElecDeliChangeCmpResponse.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 トウ鋒鋼 (中訊) 新規作成　@仕様変更モデル110
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・口座切替・電子交付申込変更完了レスポンス)<BR>
 * 管理者・口座切替・電子交付申込変更完了レスポンス<BR>
 * <BR>
 * @@author トウ鋒鋼
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliChangeCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_acc_sw_elec_deli_change_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709191123L;

    /**
     * (日付情報)<BR>
     * 日付情報<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliAppDtInfo dateInfo;

    /**
     * @@roseuid 46F0900C03D0
     */
    public WEB3AdminInformAccSwElecDeliChangeCmpResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminInformAccSwElecDeliChangeCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
