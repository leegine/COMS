head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplyInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込入力レスポンス(WEB3AdminInformAccSwElecDeliApplyInpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 孫洪江 (中訊) 新規作成 仕様変更モデル097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・口座切替・電子交付申込入力レスポンス)<BR>
 * 管理者・口座切替・電子交付申込入力レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplyInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707190908L;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode;

    /**
     * (顧客名)<BR>
     */
    public String accountName;

    /**
     * (変更前情報)<BR>
     * 口座切替・電子交付申込情報<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo beforeInfo;

    /**
     * (変更後情報)<BR>
     * 口座切替・電子交付申込情報<BR>
     */
    public WEB3AdminInformAccSwitchElecDeliApplyInfo changedInfo;

    public WEB3AdminInformAccSwElecDeliApplyInpResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminInformAccSwElecDeliApplyInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
