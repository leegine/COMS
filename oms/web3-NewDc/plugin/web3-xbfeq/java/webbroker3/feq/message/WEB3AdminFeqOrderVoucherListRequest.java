head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderVoucherListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒO®ú¶`[êNGXg(WEB3AdminFeqOrderVoucherListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (ÇÒO®ú¶`[êNGXg)<BR>
 * ÇÒO®ú¶`[êNGXgNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqOrderVoucherListRequest extends WEB3GenRequest 
{

    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderVoucherListRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderVoucherList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (ðê)<BR>
     * O®ú¶`[êðÌzñ
     */
    public WEB3AdminFeqOrderVoucherListCondUnit[] condList;
    
    /**
     * @@roseuid 42CE39FB0251
     */
    public WEB3AdminFeqOrderVoucherListRequest() 
    {
     
    }
    
    /**
     * NGXgf[^Ì®«ð`FbN·éB<BR>
     * <BR>
     * Pjðê<BR>
     * <BR>
     * P|Pj<BR>
     *    this.ðê == null or<BR>
     *    this.ðê.length() == 0<BR>
     * <BR>
     *    ÌêAusê¢IðvÌáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02045<BR>
     * 
     * <BR>
     * P|QjevfÉÈºÌ`FbNðs¤B<BR>
     * <BR>
     * P|Q|Pj<BR>
     *    ð.sêR[h == null<BR>
     * <BR>
     *    ÌêAusêR[h¢ÝèvÌáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02046<BR>
     * <BR>
     * P|Q|Qj<BR>
     *    ið.­úi©j != null and ð.­úij == null j or<BR>
     *    ið.­úi©j == null and ð.­úij != null j or<BR>
     *    ið.­úi©j != null and ð.­úij != null and <BR> 
     *      ð.­úi©j > ð.­úij j<BR> 
     * <BR>
     *    ÌêAu­úðÝèG[vÌáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02047<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42A0176803E7
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //Pjðê
        //this.ðê == null or this.ðê.length() == 0
        //ÌêAusê¢IðvÌáOðX[·éB
        if (this.condList == null || this.condList.length == 0)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02045,
                this.getClass().getName() + STR_METHOD_NAME,
                " sê¢IðG[B" ); 
        }
        
        //P|QjevfÉÈºÌ`FbNðs¤B
        int l_intCnt = this.condList.length;
        for (int i =0; i < l_intCnt; i ++)
        {
            WEB3AdminFeqOrderVoucherListCondUnit l_unit = this.condList[i];
            if (l_unit != null)
            {
                //P|Q|Pjð.sêR[h == nullÌêAusêR[h¢ÝèvÌáOðX[·éB
                if (WEB3StringTypeUtility.isEmpty(l_unit.marketCode))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02046,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " sêR[h¢ÝèG[B" ); 
                }
                //P|Q|Qjið.­úi©j != null and ð.­úij == null j or
                //ið.­úi©j == null and ð.­úij != null j or
                //ið.­úi©j != null and ð.­úij != null and
                //ð.­úi©j > ð.­úij j
                // ÌêAu­úðÝèG[vÌáOðX[·éB
                if ((l_unit.orderBizDateFrom != null && l_unit.orderBizDateTo == null) ||
                    (l_unit.orderBizDateFrom == null && l_unit.orderBizDateTo != null) ||
                    (l_unit.orderBizDateFrom != null && l_unit.orderBizDateTo != null &&                         
                        WEB3DateUtility.compareToDay(l_unit.orderBizDateFrom, l_unit.orderBizDateTo) > 0))
                {
                    log.debug(getClass().getName() + STR_METHOD_NAME);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02047,
                        this.getClass().getName() + STR_METHOD_NAME,
                        " ­úðÝèG[B" + 
                        l_unit.orderBizDateFrom + "(ð.­úi©j)" + 
                        l_unit.orderBizDateTo + "(ð.­úij)"); 
                }
            }
        }

        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * YNGXgÉÎ·éX|XIuWFNgðÔ·B<BR>
     *<BR>
     * @@return X|XIuWFNg
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqOrderVoucherListResponse(this);
    }
}
@
