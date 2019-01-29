head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投注文受付UnitService (WEB3RuitoOrderAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  周勇 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate;
import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;
/**
 * (累投注文受付UnitService)<BR>
 * 累投注文受付１件サービスインタフェース<BR>
 * <BR>
 * 注文受付１件ごとの受付処理を実施する。<BR>
 */
public interface WEB3RuitoOrderAcceptUnitService extends Service
{
    /**
     * 累投注文受付完了処理をおこなう。<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F1DC0147
     */
    public void notifyOrderAcceptComplete(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException;
    /**
     * 累投注文受付失敗処理をおこなう。<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@param l_ruitoAcceptDecisionInterceptor - 累投受付確定インタセプタ<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4088F1DC0166
     */
    public void notifyOrderAcceptFail(
        RuitoOrderUnit l_ruitoOrderUnit,
        WEB3RuitoAcceptedDecisionInterceptor l_ruitoAcceptDecisionInterceptor)
        throws WEB3BaseException;
}
@
