head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAssetUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®ÛLÁ¿îñ(WEB3FeqAssetUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 © (u) VKì¬
                 : 2005/08/01 sp(u) r[  
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (O®ÛLÁ¿îñ)<BR>
 * O®ÛLÁ¿îñNX<BR>
 * 
 * @@author ©(u)
 * @@version 1.0
 */

public class WEB3FeqAssetUnit extends Message 
{
    
    /**
     * (ÛLYID)<BR>
     * ÛLYID<BR>
     */
    public String assetId;
    
    /**
     * (sêR[h)<BR>
     * sêR[h<BR>
     */
    public String marketCode;
    
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
     * (Á¿¼)<BR>
     * Á¿¼<BR>
     */
    public String productName;
    
    /**
     * (ÁèûÀæª)<BR>
     * ÁèûÀæª<BR>
     * <BR>
     * 0FêÊ<BR>
     * 1FÁè<BR>
     */
    public String taxType;
    
    /**
     * (ÊÝR[h)<BR>
     * ÊÝR[h<BR>
     */
    public String currencyCode;
    
    /**
     * (tÂ\Ê)<BR>
     * tÂ\Ê<BR>
     */
    public String sellPossQuantity;
    
    /**
     * (¶Ê)<BR>
     * ¶Ê<BR>
     */
    public String orderedQuantity;
    
    /**
     * (tÂ\tO)<BR>
     * tÂ\tO<BR>
     * <BR>
     * trueFtÂ\<BR>
     * falseFtsÂ<BR>
     */
    public boolean sellPossFlag;
    
    /**
     * (O®ÛLÁ¿îñ)<BR>
     * RXgN^<BR>
     * @@roseuid 42074EE801A3
     */
    public WEB3FeqAssetUnit() 
    {
     
    }
}
@
