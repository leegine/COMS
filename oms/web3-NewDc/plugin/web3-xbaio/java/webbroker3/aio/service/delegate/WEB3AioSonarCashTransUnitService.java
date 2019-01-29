head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金UnitService(WEB3AioSonarCashTransUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/10 黄建 (中訊) 新規作成
                   2004/10/26 屈陽 (中訊) レビュー
*/


package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.HostCashTransferParams;
import webbroker3.common.WEB3BaseException;

/**
 * (SONAR入出金UnitService)<BR>
 * SONAR入出金UnitServiceインターフェイス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public interface WEB3AioSonarCashTransUnitService extends Service 
{
    
    /**
     * (submit注文)<BR>
     * SONARからの注文の登録を行い、新規注文の注文IDを返却する。<BR>
     * @@param l_hostCashTransferParams - (入出金Params)<BR>
     * 入出金Paramsオブジェクト<BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@return long
     * @@roseuid 41009B090109
     */
    public long submitOrder(HostCashTransferParams l_hostCashTransferParams)
        throws WEB3BaseException;
}
@
