head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplySrcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込検索レスポンス(WEB3AdminInformAccSwElecDeliApplySrcResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/19 孫洪江 (中訊) 新規作成 仕様変更モデル097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・口座切替・電子交付申込検索レスポンス)<BR>
 * 管理者・口座切替・電子交付申込検索レスポンスクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplySrcResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_src";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707190838L;

    public WEB3AdminInformAccSwElecDeliApplySrcResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminInformAccSwElecDeliApplySrcResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
