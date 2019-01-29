head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecuteEndNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP出来終了通知UnitService(WEB3IfoExecuteEndNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/6/17 艾興 (中訊) 新規作成
Revesion History : 2007/06/08 趙林鵬 (中訊) モデルNo.694
*/
package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (先物OP出来終了通知UnitService)<BR>
 * 先物OP出来終了通知UnitServiceインターフェイス<BR>
 */
public interface WEB3IfoExecuteEndNotifyUnitService extends Service 
{
    
    /**
     * (notify出来終了)<BR>
     * 注文毎の出来終了処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（OP出来終了通知）notify出来終了」参照。<BR>
     * @@param l_orderUnit - (注文単位オブジェクト)<BR>
     * 注文単位オブジェクト<BR>
     * @@param l_strOrderExecutionEndType -(出来終了区分)<BR>
     * 出来終了区分<BR>
     * @@roseuid 408C94210085
     */
    public void notifyExecuteEnd(OrderUnit l_orderUnit, String l_strOrderExecutionEndType)
        throws WEB3BaseException;
    
}
@
