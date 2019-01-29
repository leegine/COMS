head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.54.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPTradingPowerReCalcService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力再計算サービス(WEB3TPTradingPowerReCalcService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/25 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * (余力再計算サービス)<BR>
 */
public interface WEB3TPTradingPowerReCalcService extends Service
{
    /**
     * (余力再計算)<BR>
     * 余力再計算を実施し、引数で指定された顧客の余力状態を最新にする。<BR>
     * <BR>
     * ※シーケンス図「(余力再計算サービス)余力再計算」参照<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)
     */
    public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;
}
@
