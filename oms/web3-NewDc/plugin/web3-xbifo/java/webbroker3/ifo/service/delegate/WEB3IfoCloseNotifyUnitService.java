head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoCloseNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP失効通知UnitService(WEB3IfoCloseNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/14 盧法@旭 (中訊) 新規作成
*/

package webbroker3.ifo.service.delegate;

import webbroker3.common.WEB3BaseException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * (先物OP失効通知UnitService)<BR>
 * 先物OP失効通知１件サービスインタフェイス<BR>
 * <BR>
 * １件ごとの失効通知処理を実施する。<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public interface WEB3IfoCloseNotifyUnitService extends Service 
{
    
    /**
     * (notify失効)<BR>
     * 失効処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP失効通知）notify失効」参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_dblExecutionQuantity - 約定数量
     * @@param l_strCloseReasonCode - 失効理由コード
     * @@throws WEB3BaseException
     * @@param l_strCloseNotifyType - 失効通知区分<BR>
     * <BR>
     * 1：失効<BR>
     * 2：失効取消<BR>
     * @@roseuid 408C9931026A
     */
    public String notifyClose(OrderUnit l_orderUnit, double l_dblExecutionQuantity, String l_strCloseReasonCode, String l_strCloseNotifyType) throws WEB3BaseException;
}
@
