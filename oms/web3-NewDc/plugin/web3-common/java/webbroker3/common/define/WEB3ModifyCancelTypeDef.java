head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.53.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ModifyCancelTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ¶ù³EæÁæªèè`NX(WEB3ModifyCancelTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24  üææ@@(sinocom) VKì¬
Revesion History : 2006/07/12 hC (u)yæ¨IvVzdlÏXEcaCAEgNo.043
*/
package webbroker3.common.define;

/**
 * ¶ù³EæÁæªèè`NX
 *
 * @@author üææ(sinocom)
 * @@version 1.0
 */
public interface WEB3ModifyCancelTypeDef
{

    /**
     * úl<BR>
     */
    public final static String INITIAL_VALUE = "0";

    /**
     * æÁ<BR>
     */
    public final static String CANCELING = "1";
    
    /**
     * êæÁ®¹<BR>
     */
    public final static String PART_CANCELED = "2";
    
    /**
     * SæÁ®¹<BR>
     */
    public final static String CANCELED = "3";
    
    /**
     * æÁ¸s<BR>
     */
    public final static String CANCEL_ERROR = "4";
    
    /**
     * ù³<BR>
     */
    public final static String CHANGING = "5";
    
    /**
     * êù³®¹<BR>
     */
    public final static String PARTIALLY_CHANGED = "6";                    
    
    /**
     * Sù³®¹<BR>
     */
    public final static String CHANGED = "7";    
    
    /**
     * ù³¸s<BR>
     */
    public final static String CHANGE_ERROR = "8";    
    
    /**
     * G[<BR>
     */
    public final static String ERROR = "9";            
    
    /**
     * A:Wwl¶ØÖ<BR>
     */
    public final static String W_LIMIT_TRANSFERING = "A";
    
    /**
     * B:Wwl¶êØÖ®¹<BR>
     */
    public final static String W_LIMIT_PARTIALLY_TRANSFERED = "B";                    
    
    /**
     * C:Wwl¶SØÖ®¹<BR>
     */
    public final static String W_LIMIT_TRANSFERED = "C";    
    
    /**
     * D:Wwl¶ØÖ¸s<BR>
     */
    public final static String W_LIMIT_TRANSFER_ERROR = "D";    
}
@
