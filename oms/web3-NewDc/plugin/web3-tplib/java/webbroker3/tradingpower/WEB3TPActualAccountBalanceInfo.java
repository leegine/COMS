head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.56.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPActualAccountBalanceInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 使用可能現金情報(ActualAccountBalanceInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/22 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower;

/**
 * (使用可能現金情報)
 */
public class WEB3TPActualAccountBalanceInfo
{

    /**
     * （差金決済買付可能額）
     */
    public double settlementBuyAmount;

    /**
     * （指定日）
     */
    public int specifiedPoint;

    /**
     * (コンストラクタ)
     */
    public WEB3TPActualAccountBalanceInfo()
    {
    }
}
@
