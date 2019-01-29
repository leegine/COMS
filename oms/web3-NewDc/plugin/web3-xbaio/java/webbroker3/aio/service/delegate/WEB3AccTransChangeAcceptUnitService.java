head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AccTransChangeAcceptUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替受付UnitService(WEB3AccTransChangeAcceptUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 于美麗 (中訊) 新規作成
                   2004/10/22 王蘭芬(中訊) レビュー                                       
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (振替受付UnitService)<BR>
 * 振替受付UnitServiceインターフェイス<BR>
 * 
 * @@author 于美麗(中訊)
 * @@version 1.0
 */
public interface WEB3AccTransChangeAcceptUnitService extends Service
{

    /**
     * 振替請求受付DB更新処理を行う。
     * @@param l_request - (注文単位オブジェクト)
     * @@param l_strErrorCode - (エラーコード)
     * @@param l_strAcceptDiv - (受付通知区分)
     * @@throws WEB3BaseException
     * @@roseuid 413C2198004F
     */
    public void execute(
        AioOrderUnit l_orderUnit,
        String l_strErrorCode,
        String l_strAcceptDiv)
        throws WEB3BaseException;
}
@
