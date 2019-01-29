head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ClosingOrderDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3ClosingOrderDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 決済順序　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ClosingOrderDef
{
    /**
     * 0 : ランダム
     */
    public static final String RANDOM = "0";

    /**
     * 1 : 単価益順
     */
    public static final String UNIT_PRICE_PROFIT = "1";

    /**
     * 2 : 単価損順
     */
    public static final String UNIT_PRICE_LOSS = "2";

    /**
     * 3 : 建日順
     */
    public static final String OPEN_DATE = "3";

}
@
