head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqUploadErrCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ĺa¤ Ř\[VVXećń
File Name        : ÇŇOŽŻąŻĚßŰ°ÄŢ´×°đŽšNGXg(WEB3AdminFeqUploadErrCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 sp (u) VKěŹ
                 : 2005/08/02 ¤űU (u) r[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqErrorCancelTargetDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (ÇŇOŽŻąŻĚßŰ°ÄŢ´×°đŽšNGXg)<BR>
 * ÇŇOŽŻąŻĚßŰ°ÄŢ´×°đŽšNGXg
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqUploadErrCancelCompleteRequest extends WEB3GenRequest 
{

    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqUploadErrCancelCompleteRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_uploadErrCancelComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (G[đÎŰ@@\ćŞ)<BR>
     * G[đÎŰ@@\ćŞ<BR>
     * <BR>
     * 1F@@śót<BR>
     * 2F@@ńč
     */
    public String errorCancelTargetDiv;
    
    /**
     * (ĂŘÔ)<BR>
     * ĂŘÔ
     */
    public String password;
    
    /**
     * @@roseuid 42CE3A0300BB
     */
    public WEB3AdminFeqUploadErrCancelCompleteRequest() 
    {
     
    }
    
    /**
     * NGXgf[^đ`FbNˇéB<BR>
     * <BR>
     * Pj@@G[đÎŰ@@\ćŞ<BR>
     * @@P|Pj@@˘üÍĚęAáOđX[ˇéB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02048<BR>
     * @@P|Qj@@LřČR[hlĹČ˘ęAáOđX[ˇéB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02049<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42BBB4840108
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //Pj@@G[đÎŰ@@\ćŞ
        //P|Pj@@˘üÍĚęAáOđX[ˇéB
        if (WEB3StringTypeUtility.isEmpty(this.errorCancelTargetDiv))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02048,
                this.getClass().getName() + STR_METHOD_NAME,
                " G[đÎŰ@@\ćŞŞ˘üÍĹˇB"); 
        }
        //P|Qj@@LřČR[hlĹČ˘ęAáOđX[ˇéB
        if (!(WEB3FeqErrorCancelTargetDivDef.ORDER_ACCEPT.equals(this.errorCancelTargetDiv) || 
            WEB3FeqErrorCancelTargetDivDef.EXECUTED.equals(this.errorCancelTargetDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02049,
                this.getClass().getName() + STR_METHOD_NAME,
                "G[đÎŰ@@\ćŞŞsłČlĹˇ:" + this.errorCancelTargetDiv); 
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }

    /**
     * YNGXgÉÎˇéX|XIuWFNgđÔˇB<BR>
     *<BR>
     * @@return X|XIuWFNg
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqUploadErrCancelCompleteResponse(this);
    }
}
@
