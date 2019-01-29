head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSecurityTransferNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替通知UnitServiceインターフェイス(WEB3AioSecurityTransferNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 王蘭芬(中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;


/**
 * (証券振替通知UnitService)<BR>
 * 証券振替通知UnitServiceインターフェイス
 * 
 * @@author 王蘭芬(中訊)
 * @@version 1.0
 */
public interface WEB3AioSecurityTransferNotifyUnitService extends Service 
{
    
    /**
     * 証券振替通知処理を行う。
     * @@param l_aioOrderUnit - 注文単位オブジェクトの配列
     * @@param l_errorCode - エラーコード
     * @@param l_acceptNotifyDiv - 受付通知区分
     * @@roseuid 415792E003BA
     */
    public void execute(AioOrderUnit[] l_aioOrderUnit, String l_errorCode, String l_acceptNotifyDiv)
        throws WEB3BaseException;
}
@
