head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSpanConnectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SPAN接続サービスImpl(WEB3GentradeSpanConnectServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24 鄒政 (中訊) 新規作成
*/
package webbroker3.gentrade;

/**
 * (SPAN接続サービスImpl)<BR>
 * SPAN接続サービス実装クラス<BR>
 */
public class WEB3GentradeSpanConnectServiceImpl
    implements WEB3GentradeSpanConnectService
{

    /**
     * @@roseuid 41528DFE0333
     */
    public WEB3GentradeSpanConnectServiceImpl()
    {

    }

    /**
     * (callSPAN証拠金)
     * SPAN証拠金を返却する。
     * 
     *** SPAN I/F詳細不明のため、未定(8/13) ***
     * @@param l_genSubAccount - 補助口座オブジェクト
     * @@param l_totalContractSpecs - 指定日の建玉集計の一覧
     * @@return double
     * @@roseuid 411C2330030C
     */
    public double callSpanIfoDeposit(
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3GentradeTotalContractSpec[] l_totalContractSpecs)
    {
        return 0;
    }
}
@
