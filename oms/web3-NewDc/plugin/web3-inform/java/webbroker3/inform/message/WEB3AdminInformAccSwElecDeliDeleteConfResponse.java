head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliDeleteConfResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込取消確認レスポンス(WEB3AdminInformAccSwElecDeliDeleteConfResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/19 趙林鵬(中訊) 新規作成 モデルNo.110
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・口座切替・電子交付申込取消確認レスポンス)<BR>
 * 管理者・口座切替・電子交付申込取消確認レスポンス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminInformAccSwElecDeliDeleteConfResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_acc_sw_elec_deli_delete_conf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200709191030L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;

    /**
     * (変更後情報)<BR>
     * 変更後情報<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo;

    /**
     * 管理者・口座切替・電子交付申込取消確認レスポンス<BR>
     */
    public WEB3AdminInformAccSwElecDeliDeleteConfResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminInformAccSwElecDeliDeleteConfResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
