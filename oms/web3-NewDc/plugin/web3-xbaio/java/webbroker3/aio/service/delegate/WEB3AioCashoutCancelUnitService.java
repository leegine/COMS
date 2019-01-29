head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.20.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutCancelUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金取消UnitService(WEB3AioCashoutCancelUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 周勇 (中訊) 新規作成                                      
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import webbroker3.common.WEB3BaseException;


/**
 * (出金取消UnitService)<BR>
 * 出金取消UnitServiceインターフェイス
 */
public interface WEB3AioCashoutCancelUnitService extends Service 
{
    
    /**
     * 出金取消処理を行う。
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param String - パスワード
     * @@roseuid 4191D98E022B
     */
    public void execute(AioOrderUnit l_orderUnit,String l_strAdminPassword) throws WEB3BaseException;
}
@
