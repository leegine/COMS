head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformAccSwElecDeliApplySrcRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・口座切替・電子交付申込検索リクエスト(WEB3AdminInformAccSwElecDeliApplySrcRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/18 孫洪江 (中訊) 新規作成 仕様変更モデル097
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・口座切替・電子交付申込検索リクエスト)<BR>
 * 管理者・口座切替・電子交付申込検索リクエストクラス<BR>
 *
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3AdminInformAccSwElecDeliApplySrcRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_inform_acc_sw_elec_deli_apply_src";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200707182050L;

    public WEB3AdminInformAccSwElecDeliApplySrcRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformAccSwElecDeliApplySrcResponse(this);
    }
}
@
