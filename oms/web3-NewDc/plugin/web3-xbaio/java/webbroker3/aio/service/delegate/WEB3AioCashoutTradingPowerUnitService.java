head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.13.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashoutTradingPowerUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金余力チェックUnitService(WEB3AioCashoutTradingPowerUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/17 黄建 (中訊) 新規作成
                   2004/10/25 周勇 (中訊) レビュー
                   2006/02/21 韋念瓊 (中訊) 仕様変更・モデルNo.498
                   2006/08/28 車進 (中訊) 仕様変更・モデルNo.630、635、645
                   2006/11/15 徐宏偉 (中訊)仕様変更・モデルNo.684
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.common.WEB3BaseException;


/**
 * (出金余力チェックUnitService)<BR>
 * 出金余力チェックUnitServiceインターフェイス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public interface WEB3AioCashoutTradingPowerUnitService extends Service
{
    
    /**
     * 注文の余力チェック処理を行う。
     * @@param l_hostPaymentOrderParams - (出金請求注文キューの行オブジェクト)
     * @@param l_strProcessFlag - (処理フラグ)
     * @@param l_bolTriggerIssueFlag - (トリガ発行フラグ)
     * @@param l_strDbCurrentPriceCheckDiv - (DB時価余力チェック区分)
     * @@throws WEB3BaseException
     */
    public void execute(
        HostPaymentOrderParams l_hostPaymentOrderParams,
        String l_strProcessFlag,
        boolean l_bolTriggerIssueFlag,
        String l_strDbCurrentPriceCheckDiv)
        throws WEB3BaseException;
}
@
