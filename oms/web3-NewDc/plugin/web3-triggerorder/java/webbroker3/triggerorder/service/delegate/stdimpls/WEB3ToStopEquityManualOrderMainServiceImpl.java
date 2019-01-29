head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToStopEquityManualOrderMainServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式逆指値注文手動発注メインサービスImpl(WEB3ToStopEquityManualOrderMainServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06 李俊(中訊) 新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.triggerorder.service.delegate.WEB3ToEquityManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * (株式逆指値注文手動発注メインサービスImpl)<BR>
 * 株式逆指値注文手動発注メインサービス実装クラス。<BR>
 * 
 * @@author 李俊(中訊) <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToStopEquityManualOrderMainServiceImpl 
    extends WEB3ToEquityManualOrderMainServiceImpl 
    implements WEB3ToStopEquityManualOrderMainService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToStopEquityManualOrderMainServiceImpl.class);
    
    /**
     * @@roseuid 436ACF770186
     */
    public WEB3ToStopEquityManualOrderMainServiceImpl() 
    {
     
    }
    
    /**
     * (getUnitService)<BR>
     * 株式逆指値注文手動発注Unitサービスを返す。<BR>
     * @@return WEB3ToEquityManualOrderUnitService
     */
    protected WEB3ToEquityManualOrderUnitService getUnitService() 
    {
        final String STR_METHOD_NAME = "getUnitService()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToStopEquityManualOrderUnitService l_toStopEquityManualOrderUnitService = 
            (WEB3ToStopEquityManualOrderUnitService)
                Services.getService(WEB3ToStopEquityManualOrderUnitService.class);
        
        log.exiting(STR_METHOD_NAME);
        return l_toStopEquityManualOrderUnitService;
    }
    
    
}
@
