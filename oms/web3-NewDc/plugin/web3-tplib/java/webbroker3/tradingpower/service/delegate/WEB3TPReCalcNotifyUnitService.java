head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPReCalcNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算通知一件サービス(WEB3TPReCalcNotifyUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/24 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ResourceBusyException;

import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.tradingpower.data.TpCalcResultExecNotifyParams;

/**
 * (余力計算通知一件サービス)
 */
public interface WEB3TPReCalcNotifyUnitService extends Service
{

    /**
     * (notify余力再計算)
     * @@param l_subAccount - (補助口座)
     * @@param 余力再計算キューParams - (余力再計算キューParams)
     * @@roseuid 41F456590372
     */
    public void notifyReCalc(
        WEB3GentradeSubAccount l_subAccount,
        TpCalcResultExecNotifyParams l_params)
        throws ResourceBusyException, DataQueryException, DataNetworkException;
}
@
