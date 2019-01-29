head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToWLimitEquityManualSwitchMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式W指値注文手動切替メインサービスImpl(WEB3ToWLimitEquityManualSwitchMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/20 齊珂(中訊) 新規作成 (モデル）No.184
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式W指値注文手動切替メインサービスImpl)<BR>
 *
 * @@author 齊珂<BR>
 * @@version 1.0<BR>
 */
public class WEB3ToWLimitEquityManualSwitchMainServiceImpl
    extends WEB3ToEquityManualOrderMainServiceImpl
    implements WEB3ToWLimitEquityManualSwitchMainService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToWLimitEquityManualSwitchMainServiceImpl.class);

    /**
     * コンストラクタ<BR>
     */
    public WEB3ToWLimitEquityManualSwitchMainServiceImpl()
    {

    }

    /**
     * 株式W指値注文手動切替Unitサービスを返す。<BR>
     * <BR>
     * @@return WEB3ToEquityManualOrderUnitService<BR>
     */
    protected WEB3ToEquityManualOrderUnitService getUnitService()
    {
        final String STR_METHOD_NAME = "getUnitService()";
        log.entering(STR_METHOD_NAME);

        WEB3ToWLimitEquityManualSwitchUnitService l_toEquityManualOrderUnitService =
            (WEB3ToWLimitEquityManualSwitchUnitService)
                Services.getService(WEB3ToWLimitEquityManualSwitchUnitService.class);

        log.exiting(STR_METHOD_NAME);
        return l_toEquityManualOrderUnitService;
    }
}
@
