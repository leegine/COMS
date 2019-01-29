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
filename	WEB3RuitoCancelAcceptedUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投取消受付UnitService  (WEB3RuitoCancelAcceptedUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14  王艶芳 (中訊) 新規作成
*/
package webbroker3.xbruito.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.xbruito.WEB3RuitoAcceptedDecisionInterceptor;

/**
 * 累投取消受付１件サービスインタフェース<BR>
 * <BR>
 * 注文取消１件ごとの受付処理を実施する。<BR>
 */
public interface WEB3RuitoCancelAcceptedUnitService extends Service 
{
   
   /**
    * 累投取消受付完了処理をおこなう。<BR>
    * @@param l_ruitoOrderUnit - 累投注文単位 <BR>
    * @@param l_ruitoAcceptedDecisionInterceptor - 累投受付確定インタセプタ<BR>
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 408887A103B8
    */
    public void notifyCancelAcceptedComplete(RuitoOrderUnit l_ruitoOrderUnit,
                                           WEB3RuitoAcceptedDecisionInterceptor 
                                           l_ruitoAcceptedDecisionInterceptor) 
                                           throws WEB3BaseException;
   
   /**
    * 累投取消受付失敗処理をおこなう。<BR>
    * @@param l_ruitoOrderUnit - 累投注文単位 <BR>
    * @@param l_ruitoAcceptedDecisionInterceptor - 累投受付確定インタセプタ<BR>
    * @@throws webbroker3.common.WEB3BaseException
    * @@roseuid 408887A103BA
    */
    public void notifyCancelAcceptedFail(RuitoOrderUnit l_ruitoOrderUnit, 
                                        WEB3RuitoAcceptedDecisionInterceptor 
                                        l_ruitoAcceptedDecisionInterceptor) 
                                        throws WEB3BaseException;
}
@
