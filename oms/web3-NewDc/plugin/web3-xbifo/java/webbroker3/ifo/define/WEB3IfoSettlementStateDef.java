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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Ifo決済状態区分(WEB3IfoSettlementStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 張威 (中訊) 新規作成
*/
package webbroker3.ifo.define;

/**
 * 決済状態区分
 *                                                                     
 * @@author zhang wei
 * @@version 1.0
 */
public interface WEB3IfoSettlementStateDef

{
    /**
     * null：指定なし
     */
//  UTBUG（WEB3_IFO_UT-000086）を対応　@START　@20040730   李媛   
    //public static final String NOT_DESIGNATION = "null"; 
    public static final String NOT_DESIGNATION = null;
//  UTBUG（WEB3_IFO_UT-000086）を対応　@START　@20040730   李媛        

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
