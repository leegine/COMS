head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.00.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualDisplayOrderGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : MÇÒÁ¿\¦o^ês(WEB3MutualDisplayOrderGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 © (u) VKì¬ 
*/

package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (MÇÒÁ¿\¦o^ês)<BR>
 * MõÇÒÁ¿\¦o^êsf[^NX
 * 
 * @@author ©(u)
 * @@version 1.0 
 */

public class WEB3MutualDisplayOrderGroup extends Message 
{   
    /**
     * (\¦)<BR>
     *  \¦
     */
    public String displayOrder;
    
    /**
     * (Á¿R[h)<BR>
     *  Á¿R[h
     */
    public String mutualProductCode;
    
    /**
     * (M¦ïÁ¿R[h)<BR>
     *  M¦ïÁ¿R[h
     */
    public String mutualAssocProductCode;
    
    /**
     * (Á¿¼)<BR>
     *  Á¿¼
     */
    public String mutualProductName;
    
    /**
     * (MÁ¿JeS[R[hP)<BR>
     *  MÁ¿JeS[R[hP
     */
    public String categoryCode1;
    
    /**
     * (MÁ¿JeS[¼ÌP)<BR>
     *  MÁ¿JeS[¼ÌP
     */
    public String categoryName1;
    
    /**
     * (MÁ¿JeS[R[hQ)<BR>
     *  MÁ¿JeS[R[hQ
     */
    public String categoryCode2;
    
    /**
     * (MÁ¿JeS[¼ÌQ)<BR>
     *  MÁ¿JeS[¼ÌQ
     */
    public String categoryName2;
    
    /**
     * (MÁ¿JeS[R[hR)<BR>
     *  MÁ¿JeS[R[hR
     */
    public String categoryCode3;
    
    /**
     * (MÁ¿JeS[¼ÌR)<BR>
     *  MÁ¿JeS[¼ÌR
     */
    public String categoryName3;
    
    /**
     * (¶ót÷ØÔ)<BR>
     *  ¶ót÷ØÔ<BR>
     *  "HHFMM" (24Ô`®Ån³êéj
     */
    public String orderCloseTime;
    
    /**
     * (MÇÒÁ¿\¦o^ês)<BR>
     * ftHgRXgN^
     * @@roseuid 4153BBC9019E
     */
    public WEB3MutualDisplayOrderGroup()
    {
    }
}
@
