head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒO®¶ñèÆïUnit(WEB3AdminORFeqOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 ACÇ(u) VKì¬
                 : 2005/08/02 sp(u) r[
*/

package webbroker3.adminorderexecinquiry.message;


/**
 * (ÇÒO®¶ñèÆïUnit)
 * ÇÒO®¶ñèÆïUnitNX
 *
 * @@author ACÇ
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon 
{
    
    /**
     * (`[No)<BR>
     * `[No<BR>
     */
    public String orderNumber;
    
    /**
     * (Á¿R[h)<BR>
     * Á¿R[h<BR>
     */
    public String productCode;
    
    /**
     * (»nÁ¿R[h)<BR>
     * »nÁ¿R[h<BR>
     */
    public String localProductCode;
    
    /**
     * (sêR[h)<BR>
     * sêR[h<BR>
     */
    public String marketCode;
    
    /**
     * (ûÀæª)<BR>
     * 0FêÊ@@1FÁè<BR>
     */
    public String taxType = null;
    
    /**
     * (^pR[h)<BR>
     * ^pR[h<BR>
     */
    public String managementCode;
    
    /**
     * (Ïæª)<BR>
     * Ïæª<BR>
     * <BR>
     * 0F~Ý<BR>
     * 1FOÝ<BR>
     */
    public String settleDiv;
    
    /**
     * (ÊÝR[h)<BR>
     * ÊÝR[h<BR>
     */
    public String currencyCode;
    
    /**
     * (ónãàiOÝj)<BR>
     * ónãàiOÝj<BR>
     */
    public String foreignDeliveryPrice = null;
    
    /**
     * (XVÒR[h)<BR>
     * XVÒR[h<BR>
     */
    public String updaterCode;
    
    /**
     * (ÇÒO®¶ñèÆïUnit)<BR>
     * RXgN^<BR>
     * @@roseuid 42A69449009D
     */
    public WEB3AdminORFeqOrderExecutionRefUnit() 
    {
     
    }
}
@
