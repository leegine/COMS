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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÏóÔæª(WEB3EquitySettlementStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/21 ¤Å (u) VKì¬
*/
package webbroker3.equity.define;

/**
 * ÏóÔæª
 *                                                                     
 * @@author wang xiaojie
 * @@version 1.0
 */
public interface WEB3EquitySettlementStateDef

{
    /**
     * nullFwèÈµ
     */
    public static final String NOT_DESIGNATION = null;      

    /**
     * 0FÏÏ
     */
    public static final String SETTLEMENT_END = "0";
    
    /**
     * 1F¢Ï
     */
    public static final String HAVE_NOT_SETTLED = "1";

    /**
     * 2FÏ
     */
    public static final String SETTLING = "2";
}@
