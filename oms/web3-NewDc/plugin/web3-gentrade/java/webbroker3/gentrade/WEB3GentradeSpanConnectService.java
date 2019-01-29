head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSpanConnectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SPAN接続サービス(WEB3GentradeSpanConnectService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * SPAN接続サービスインターフェイス
 */
public interface WEB3GentradeSpanConnectService extends Service
{

    /**
     * (callSPAN証拠金) <BR>
     * SPAN証拠金を返却する。 <BR>
     * @@param l_genSubAccount - 補助口座オブジェクト
     * @@param l_totalContractSpecs
     * @@return double
     * @@roseuid 411C229000DA
     */
    public double callSpanIfoDeposit(
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3GentradeTotalContractSpec[] l_totalContractSpecs);
}
@
