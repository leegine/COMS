head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : Oฎมฟพื(WEB3FeqProductUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ฉ (u) VK์ฌ
                 : 2005/08/01 sp(u) r[   
*/

package webbroker3.feq.message;

import java.util.Date;

/**
 * (Oฎมฟพื)<BR>
 * OฎมฟพืNX<BR>
 * 
 * @@author ฉ(u)
 * @@version 1.0
 */

public class WEB3FeqProductUnit extends WEB3FeqProductCodeNameUnit 
{
    
    /**
     * (tย\)<BR>
     * tย\<BR>
     * <BR>
     * trueF@@ย\<BR>
     * falseF@@sย<BR>
     */
    public boolean buyPossFlag;
    
    /**
     * (tย\)<BR>
     * tย\<BR>
     * <BR>
     * trueF@@ย\<BR>
     * falseF@@sย<BR>
     */
    public boolean sellPossFlag;
    
    /**
     * (ปnมฟR[h)<BR>
     * ปnมฟR[h<BR>
     */
    public String localProductCode;
    
    /**
     * (tPส)<BR>
     * tPส<BR>
     */
    public String buyUnit;
    
    /**
     * (ลแtPส)<BR>
     * ลแtPส<BR>
     */
    public String minBuyUnit;
    
    /**
     * (tPส)<BR>
     * tPส<BR>
     */
    public String sellUnit;
    
    /**
     * (ลแtPส)<BR>
     * ลแtPส<BR>
     */
    public String minSellUnit;
    
    /**
     * (s๊R[h)<BR>
     * s๊R[h<BR>
     */
    public String marketCode;
    
    /**
     * (ใ๊o^๚)<BR>
     * ใ๊o^๚<BR>
     */
    public Date listedDate;
    
    /**
     * (ใ๊p~๚)<BR>
     * ใ๊p~๚<BR>
     */
    public Date unlistedDate;
    
    /**
     * (Oฎมฟพื)<BR>
     * RXgN^<BR>
     * @@roseuid 4200C778027B
     */
    public WEB3FeqProductUnit() 
    {
     
    }
}
@
