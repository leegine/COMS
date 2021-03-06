head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptCancelUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®¶ótæÁîñ(WEB3FeqOrderAcceptCancelUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (O®¶ótæÁîñ)<BR>
 * O®¶ótæÁ¾×NX<BR>
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptCancelUnit extends Message 
{
    
    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqOrderAcceptCancelUnit.class);
        
    /**
     * (¶ID)<BR>
     * ¶ID
     */
    public String orderId;
    
    /**
     * (ÏXãótæª)<BR>
     * ÏXãótæª<BR>
     * <BR>
     * 01F¶ótÏ<BR>
     * 02F¶ótG[<BR>
     * 03F¶ótÏæÁ<BR>
     * <BR>
     * 11Fù³Ï<BR>
     * 12Fù³G[<BR>
     * <BR>
     * 21FæÁÏ<BR>
     * 22FæÁG[<BR>
     * <BR>
     * 31Fo¸<BR>
     */
    public String aftChangeAcceptDiv;
    
    /**
     * (O®¶ótæÁîñ)<BR>
     * RXgN^
     * @@roseuid 4201F2E0017C
     */
    public WEB3FeqOrderAcceptCancelUnit() 
    {
     
    }
    
    /**
     * NGXgf[^Ì`FbNðs¤B<BR>
     * <BR>
     * Pj@@¶hcÌ`FbN<BR>
     * @@P|Pj@@¢üÍÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * Qj@@ÏXãæªÌ`FbN<BR>
     * @@¦üÍª éêÌÝ<BR>
     * @@Q|Pj@@s³ÈR[hlÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02020<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A55527015E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //Pj@@¶hcÌ`FbN
        //P|Pj@@¢üÍÌêAáOðX[·éB
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                " ¶IDª¢wèÅ·B"); 
        }
        
        //Qj@@ÏXãæªÌ`FbN
        //¦üÍª éêÌÝ
        if (!WEB3StringTypeUtility.isEmpty(this.aftChangeAcceptDiv))
        {
            //Q|Pj@@s³ÈR[hlÌêAáOðX[·éB
            if (!(WEB3FeqAcceptTypeDef.CANCEL.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGED.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(this.aftChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(this.aftChangeAcceptDiv) || 
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(this.aftChangeAcceptDiv)))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02020,
                    this.getClass().getName() + STR_METHOD_NAME,
                    " üÍ³ê½ÏXãæªªs³ÈR[hlÅ·:" + this.aftChangeAcceptDiv); 
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
