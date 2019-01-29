head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioBondOnPaymentCooperationUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券出金連携UnitService(WEB3AioBondOnPaymentCooperationUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/17 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.WEB3AioBondOnPaymentInfo;
import webbroker3.common.WEB3BaseException;

/**
 * (債券出金連携UnitService)<BR>
 * 債券出金連携UnitServiceインターフェイス<BR>
 * <BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3AioBondOnPaymentCooperationUnitService extends Service
{
    /**
     * (submit注文)<BR>
     * 債券からの注文の登録を行う。<BR>
     * @@param l_bondCashOutInfo - (債券出金情報)<BR>
     * 債券出金情報オブジェクト<BR>
     * @@throws WEB3BaseException
     */
    public void submitOrder(WEB3AioBondOnPaymentInfo l_bondCashOutInfo)
        throws WEB3BaseException;
}
@
