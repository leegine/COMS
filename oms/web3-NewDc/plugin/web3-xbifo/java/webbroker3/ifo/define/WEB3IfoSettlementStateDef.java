head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoSettlementStateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : IfoΟσΤζͺ(WEB3IfoSettlementStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 £Π (u) VKμ¬
*/
package webbroker3.ifo.define;

/**
 * ΟσΤζͺ
 *                                                                     
 * @@author zhang wei
 * @@version 1.0
 */
public interface WEB3IfoSettlementStateDef

{
    /**
     * nullFwθΘ΅
     */
//  UTBUGiWEB3_IFO_UT-000086jπΞ@@START@@20040730   Q   
    //public static final String NOT_DESIGNATION = "null"; 
    public static final String NOT_DESIGNATION = null;
//  UTBUGiWEB3_IFO_UT-000086jπΞ@@START@@20040730   Q        

    /**
     * 0FΟΟ
     */
    public static final String SETTLEMENT_END = "0";
    
    /**
     * 1F’Ο
     */
    public static final String HAVE_NOT_SETTLED = "1";

    /**
     * 2FΟ
     */
    public static final String SETTLING = "2";
}@
