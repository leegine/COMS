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
filename	WEB3IfoSettlementStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : IfoÏóÔæª(WEB3IfoSettlementStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/06 ¤Å (u) VKì¬
*/
package webbroker3.ifo.define;

/**
 * ÏóÔæª
 *                                                                     
 * @@author ¤Å
 * @@version 1.0
 */
public interface WEB3IfoSettlementStatusDef

{
    /**
     * -2FVKñèæÁ
     */
    public static final int OPENCONTRACT_EXECUTE_CANCLE = -2;  

    /**
     * -1FOúÈOÏÏ
     */
    public static final int BEFORE_YESTODAY_SETTLED = -1;
    
    /**
     * 0FÏÏ
     */
    public static final int SETTLED = 0;    
    
    /**
     * 1F¢Ï
     */
    public static final int UNSETTLED = 1;    
    
    /**
     * 2FÏ
     */
    public static final int SETTLING = 2;

    /**
     * 3F¢ÏÆÏ
     */
    public static final int UNSETTLED_SETTLING = 3;

    /**
     * 4FÏÏÆ¢Ï
     */
    public static final int SETTLED_UNSETTLED = 4;
    
    /**
     * 5FÏÏÆ¢ÏÆÏ
     */
    public static final int SETTLED_UNSETTLED_SETTLING = 5;
    
    /**
     * 6FÏÏÆÏ
     */
    public static final int SETTLED_SETTLING = 6;
}@
