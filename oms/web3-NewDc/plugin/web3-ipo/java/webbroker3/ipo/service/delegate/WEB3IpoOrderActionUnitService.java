head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.34.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderActionUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO申告履歴明細作成サービスインタフェイス(WEB3IpoOrderActionUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
*/

package webbroker3.ipo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.message.WEB3IPODemandHistoryUnit;

/**
 * IPO申告履歴明細作成サービスインタフェイス
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public interface WEB3IpoOrderActionUnitService extends Service 
{
    
    /**
     * (createIPO申告履歴明細)<BR>
     * IPO申告履歴明細を作成する。
     * @@param l_ipoOrder - (IPO申告)<BR>
     * IPO申告オブジェクト
     * @@return webbroker3.ipo.message.WEB3IpoOrderActionUnit[]
     * @@roseuid 40EE6A70023C
     */
    public WEB3IPODemandHistoryUnit[] createOrderActionUnit(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException;
}
@
