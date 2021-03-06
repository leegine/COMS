head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptCancelInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒO®¶ótæÁFØüÍNGXg(WEB3AdminFeqOrderAcceptCancelInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
Revesion History : 2009/08/03 Ôi(u)   f@@No.506Î
*/
package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (ÇÒO®¶ótæÁFØüÍNGXg)<BR>
 * ÇÒO®¶ótæÁFØüÍNGXgNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptCancelInputRequest extends WEB3GenRequest 
{

    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptCancelInputRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_orderAcceptInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (XR[h)<BR>
     * XR[h
     */
    public String branchCode;
    
    /**
     * (ÚqR[h)<BR>
     * ÚqR[h<BR>
     * <BR>
     * ¦¢wèÌêÍAnullB
     */
    public String accountCode;
    
    /**
     * (­ú)<BR>
     * ­ú<BR>
     * <BR>
     * ¦¢wèÌêÍAnullB
     */
    public Date orderBizDate;
    
    /**
     * (^pR[h)<BR>
     * ^pR[h<BR>
     * <BR>
     * ¦¢wèÌêÍAnullB
     */
    public String managementCode;
    
    /**
     * (sêR[h)<BR>
     * sêR[h<BR>
     * <BR>
     * ¦¢wèÌêÍAnullB
     */
    public String marketCode;
    
    /**
     * (Á¿R[h)<BR>
     * Á¿R[h<BR>
     * <BR>
     * ¦¢wèÌêÍAnullB
     */
    public String productCode;
    
    /**
     * (ótæª)<BR>
     * ótæª<BR>
     * <BR>
     * 0F¶ót<BR>
     * 1Fù³æÁ<BR>
     * 2F¶ótÏ<BR>
     * <BR>
     * ¦¢wèÌêÍAnullB
     */
    public String[] acceptDiv;
    
    /**
     * (vy[WÔ)<BR>
     * vy[WÔ
     */
    public String pageIndex;
    
    /**
     * (y[Wà\¦s)<BR>
     * y[Wà\¦s
     */
    public String pageSize;
    
    /**
     * (\[gL[)<BR>
     * O®\[gL[
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * @@roseuid 42CE39FB033C
     */
    public WEB3AdminFeqOrderAcceptCancelInputRequest() 
    {
     
    }
    
    /**
     * NGXgf[^Ì®«ð`FbN·éB <BR>
     * <BR>
     * Pj@@XR[hÌ`FbN <BR>
     * @@¦üÍª éêÌÝÈºÌ`FbNðs¤B <BR>
     * @@P|Pj@@¼p3byteÌÅÈ¢êAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00834<BR>
     * <BR>
     * Qj@@ÚqR[hÌ`FbN <BR>
     * @@¦üÍª éêÌÝÈºÌ`FbNðs¤B <BR>
     * @@Q|Pj@@ª6ÅÈ¢êAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00836<BR>
     * @@Q|Qj@@ÈOÌ¶ªÜÜêéêAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_01043<BR>
     * @@Q|Rj@@XR[hÉüÍªÈ¢êAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00833<BR>
     * iÚqR[hwè<BR>ÌÝAXR[hK{j<BR>
     * <BR>
     * Rj@@^pR[hÌ`FbN<BR>
     * @@¦üÍª éêÌÝÈºÌ`FbNðs¤B <BR>
     * @@R|Pj@@5Ì¼pÅÈ¢êAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_03163<BR>
     * <BR>
     * Sj@@vy[WÔ`FbN <BR>
     * @@S|Pj@@¢üÍÌêA vy[WÔÉhPhðZbg·éB <BR>
     * @@S|Qj@@ÈOÌ¶ªÜÜêéêAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00090<BR>
     * @@S|Rj@@0ÈºÌêAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * Tj@@y[Wà\¦s`FbN <BR>
     * @@T|Pj@@¢üÍÌêAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00091<BR>
     * @@T|Qj@@ÈOÌ¶ªÜÜêéêAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00092<BR>
     * @@T|Rj@@0ÈºÌêAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * Uj@@\[gL[Ì`FbN <BR>
     * @@U|Pj@@\[gL[ª¢üÍlÌêAáOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00231<BR>
     * @@U|Qj@@i\[gL[Ìvf == 0jÌêA áOðX[·éB <BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00232<BR>
     * @@U|Rj@@\[gL[ÌvfªAºLÌ`FbNðJèÔµÄs¤B <BR>
     * @@@@@@U|R|Pj@@\[gL[.validate()ðR[·éB <BR>
     * @@@@@@U|R|Qj@@\[gL[.L[ÚªºLÌÚ¼ÈOÌêA<BR>
     *  @@@@áOðX[·éB <BR>
     * @@@@class: WEB3BusinessLayerException<BR>
     * @@@@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * @@@@@@@@ O®¶¾×.^pR[h <BR>
     * @@@@@@@@ O®¶¾×.¶Ô<BR>
     * @@@@@@@@ O®¶¾×.XR[h<BR>
     * @@@@@@@@ O®¶¾×.ÚqR[h<BR>
     * @@@@@@@@ O®¶¾×.ÁèûÀæª<BR>
     * @@@@@@@@ O®¶¾×.¶Ô<BR>
     * @@@@@@@@ O®¶¾×.Ïæª<BR>
     * @@@@@@@@ O®¶¾×.sêR[h<BR>
     * @@@@@@@@ O®¶¾×.Á¿R[h<BR>
     * @@@@@@@@ O®¶¾×.æª<BR>
     *          O®¶¾×.­ú
     * @@throws WEB3BaseException
     * @@roseuid 42A5382D0227
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //Pj@@XR[hÌ`FbN  
        // ¦üÍª éêÌÝÈºÌ`FbNðs¤B          
        if (!WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            //P|Pj@@¼p3byteÌÅÈ¢êAáOðX[·éB
            int l_intCnt = WEB3StringTypeUtility.getByteLength(this.branchCode);
            boolean l_blnFlag= WEB3StringTypeUtility.isDigit(this.branchCode);
            if (l_intCnt != 3 || !l_blnFlag)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "XR[hÌTCYªs³Å·:" + this.branchCode); 
            }
        }
        
        //Qj@@ÚqR[hÌ`FbN  
        //¦üÍª éêÌÝÈºÌ`FbNðs¤B
        if (!WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            //Q|Pj@@ª6ÅÈ¢êAáOðX[·éB
            if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ÚqR[hÌTCYªs³Å·:" + this.accountCode); 
            }
            
            //Q|Qj@@ÈOÌ¶ªÜÜêéêAáOðX[·éB
            if (!WEB3StringTypeUtility.isDigit(this.accountCode))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ÚqR[hÌlªÈOÌlÅ·:" + this.accountCode); 
            }
            
            //Q|Rj@@XR[hÉüÍªÈ¢êAáOðX[·éB
            //iÚqR[hwèÌÝAXR[hK{j
            if (WEB3StringTypeUtility.isEmpty(this.branchCode)) 
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "XR[hª¢wèÅ·B" ); 
            }            
        }
        
        //Rj@@^pR[hÌ`FbN 
        //¦üÍª éêÌÝÈºÌ`FbNðs¤B
        if (!WEB3StringTypeUtility.isEmpty(this.managementCode))
        {
            //R|Pj@@5Ì¼pÅÈ¢êAáOðX[·éB
            int l_intCnt = this.managementCode.length();
            boolean l_blnFlag= WEB3StringTypeUtility.isDigit(this.managementCode);
            if (l_intCnt != 5 || !l_blnFlag)
            {
                log.debug("^pR[hª5Ì¼pÅÍ èÜ¹ñB");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03163,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "^pR[hª5Ì¼pÅÍ èÜ¹ñB" + this.managementCode); 
            }
        }

        //Sj@@vy[WÔ`FbN  
        //S|Pj@@¢üÍÌêA vy[WÔÉhPhðZbg·éB
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }

        //S|Qj@@ÈOÌ¶ªÜÜêéêAáOðX[·éB
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "vy[WÔªÈOÌlÅ·:" + this.pageIndex); 
        }

        //S|Rj@@0ÈºÌêAáOðX[·éB
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "vy[WÔÌlª0ÈºÅ·:" + this.pageIndex); 
        }
        
        //Tj@@y[Wà\¦s`FbN  
        //T|Pj@@¢üÍÌêAáOðX[·éB 
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "y[Wà\¦sª¢üÍÅ·B"); 
        }
        
        //T|Qj@@ÈOÌ¶ªÜÜêéêAáOðX[·éB
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "y[Wà\¦sªÈOÌlÅ·:" + this.pageSize); 
        }
        
        //T|Rj@@0ÈºÌêAáOðX[·éB
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "y[Wà\¦sÌlª0ÈºÅ·:" + this.pageSize); 
        } 

        //Uj@@\[gL[Ì`FbN  
        //U|Pj@@\[gL[ª¢üÍlÌêAáOðX[·éB
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "\[gL[ª¢wèÅ·B"); 
        }
        
        //U|Qj@@i\[gL[Ìvf == 0jÌêA áOðX[·éB
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "\[gL[ÌvfªOÅ·B"); 
        }
        
        //U|Rj@@\[gL[ÌvfªAºLÌ`FbNðJèÔµÄs¤B
        int l_intCnt = this.sortKeys.length;
        for (int i = 0; i < l_intCnt; i ++)
        {
            WEB3ForeignSortKey l_key = sortKeys[i];
            if (l_key != null)
            {
                //U|R|Pj@@\[gL[.validate()ðR[·éB
                l_key.validate();
                
                //U|R|Qj@@\[gL[.L[ÚªºLÌÚ¼ÈOÌêA áOðX[·éB
                //      O®¶¾×.^pR[h  
                //      O®¶¾×.¶Ô 
                //      O®¶¾×.XR[h 
                //      O®¶¾×.ÚqR[h 
                //      O®¶¾×.ÁèûÀæª 
                //      O®¶¾×.¶Ô 
                //      O®¶¾×.Ïæª 
                //      O®¶¾×.sêR[h 
                //      O®¶¾×.Á¿R[h 
                //      O®¶¾×.æª 
                //      O®¶¾×.­ú
                if (!(WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ORDER_NO.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(l_key.keyItem) ||
                    WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(l_key.keyItem)))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "\[gL[ÌL[ÚÌlª¶ÝµÈ¢R[hlÅ·:" + l_key.keyItem); 
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
        return new WEB3AdminFeqOrderAcceptCancelInputResponse(this);
    }
}
@
