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
filename	WEB3GentradeCommissionRate.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料率(WEB3GentradeCommissionRate.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/14 李綱 (中訊) 新規作成
*/
package webbroker3.gentrade;

/**
 * (手数料率)<BR>
 *
 * 各種手数料率を表現する。<BR>
 */
public class WEB3GentradeCommissionRate
{

    /**
     * (証券会社コード)<BR>
     */
    private String institutionCode;

    /**
     * (手数料率)<BR>
     */
    private double commissionRate;

    /**
     * (注文タイプ)<BR>
     */
    private String orderType;

    /**
     * @@roseuid 40A86A5E0172
     */
    public WEB3GentradeCommissionRate()
    {

    }

    /**
     * (get手数料)<BR>
     * <BR>
     * 手数料を取得します。<BR>
     * @@return webbroker3.equity.WEB3EquityCommission
     * @@roseuid 3FFBD2B40389
     */
    public WEB3GentradeCommission getCommission()
    {
        return null;
    }
}
@
