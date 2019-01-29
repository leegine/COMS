head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioSonarCashTransForeignUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR入出金（外貨）UnitService(WEB3AioSonarCashTransForeignUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/28 徐宏偉 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.HostForeignCashTransferParams;
import webbroker3.common.WEB3BaseException;

/**
 * ( SONAR入出金（外貨）UnitService)<BR>
 * SONAR入出金（外貨）UnitServiceインターフェイス<BR>
 *<BR>
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3AioSonarCashTransForeignUnitService extends Service
{
    /**
     * (submit注文)<BR>
     * SONARからの注文の登録を行い、新規注文の注文IDを返却する。<BR>
     * @@param l_hostForeignCashTransferParams - (外貨入出金Paramsオブジェクト)<BR>
     * 外貨入出金Paramsオブジェクト<BR>
     * @@throws WEB3BaseException
     * @@return long
     */
    public long submitOrder(HostForeignCashTransferParams l_hostForeignCashTransferParams)
        throws WEB3BaseException;
}
@
