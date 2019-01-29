head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquitySettlementStateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済状態区分(WEB3EquitySettlementStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/21 王暁傑 (中訊) 新規作成
*/
package webbroker3.equity.define;

/**
 * 決済状態区分
 *                                                                     
 * @@author wang xiaojie
 * @@version 1.0
 */
public interface WEB3EquitySettlementStateDef

{
    /**
     * null：指定なし
     */
    public static final String NOT_DESIGNATION = null;      

    /**
     * 0：決済済
     */
    public static final String SETTLEMENT_END = "0";
    
    /**
     * 1：未決済
     */
    public static final String HAVE_NOT_SETTLED = "1";

    /**
     * 2：決済中
     */
    public static final String SETTLING = "2";
}@
