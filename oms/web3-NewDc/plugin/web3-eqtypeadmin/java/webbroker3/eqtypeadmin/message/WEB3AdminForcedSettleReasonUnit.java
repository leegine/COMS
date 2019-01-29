head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleReasonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 強制決済理由情報(WEB3AdminForcedSettleReasonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 趙林鵬 (中訊) 新規作成
Revesion History : 2007/07/24 何文敏 (中訊) 新規作成　@仕様変更モデルNo.159
Revesion History : 2008/01/17 孟亞南 (中訊) 仕様変更モデルNo.181
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (強制決済理由情報)<BR>
 * 強制決済理由情報クラス<BR>
 * <BR>
 * ※強制決済を行う基準となる設定情報<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleReasonUnit extends Message
{
    /**
     * (強制決済理由)<BR>
     * 強制決済理由<BR>
     * <BR>
     * 0：　@決済期日到来<BR>
     * 1：　@保証金維持率割（オンライン開始前・軽度）<BR>
     * 2：　@保証金維持率割（オンライン開始前・重度）<BR>
     * 3：　@保証金維持率割（場間）<BR>
     * 4：  保証金維持率割（オンライン開始前・法@定）<BR>
     * 9：　@手動強制決済<BR>
     */
    public String forcedSettleReason;
    
    /**
     * (保証金維持率)<BR>
     * 保証金維持率<BR>
     */
    public String marginMaintenanceRate;
    
    /**
     * (追証経過日数上限)<BR>
     * 追証経過日数上限<BR>
     */
    public String additionalElapsedDaysUpperLimit;
    
    /**
     * @@roseuid 462CA42801BA
     */
    public WEB3AdminForcedSettleReasonUnit()
    {
     
    }
}
@
