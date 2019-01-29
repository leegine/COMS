head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金連携UnitService(WEB3AioOnPaymentCooperationUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (出金連携UnitService) <BR>
 * 出金連携UnitServiceインターフェイス<BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public interface WEB3AioOnPaymentCooperationUnitService extends Service
{
    /**
     * 出金連携処理を行う
     * 
     * @@param l_aioOrderUnits - 注文単位オブジェクト[ ]
     * @@return WEB3GenResponse
     * @@roseuid 41BCF2CC0279
     */
    public void execute(AioOrderUnit[] l_aioOrderUnits) throws WEB3BaseException;
}@
