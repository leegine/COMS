head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionEndExecuteCondUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : O®oI¹À{ð(WEB3FeqExecutionEndExecuteCondUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 sp (u) VKì¬
                 : 2005/08/02 ¤ûU (u) r[
*/

package webbroker3.feq.message;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (O®oI¹À{ð)<BR>
 * O®oI¹À{ðNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3FeqExecutionEndExecuteCondUnit extends Message 
{
    
    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqExecutionEndExecuteCondUnit.class);
        
    /**
     * (sêR[h)<BR>
     * sêR[h
     */
    public String marketCode;
    
    /**
     * (­ú)<BR>
     * æÊÉÄüÍ³ê½­ú
     */
    public Date orderBizDate;
    
    /**
     * (O®oI¹À{ð)<BR>
     * RXgN^
     * @@roseuid 420208EC01F3
     */
    public WEB3FeqExecutionEndExecuteCondUnit() 
    {
     
    }
    
    /**
     * NGXgf[^Ì®«ð`FbN·éB<BR>
     * <BR>
     * Pj@@sêR[h`FbN<BR>
     * @@this.sêR[h == nullÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02045<BR>
     * <BR>
     * Qj@@­ú`FbN<BR>
     * @@Q|Pj@@this.­ú == nullÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_00406<BR>
     * @@Q|Qj@@this.­úªcÆúÅÈ¢êAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02019<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF022017D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //Pj@@sêR[h`FbN
        //this.sêR[h == nullÌêAáOðX[·éB
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02045,
                this.getClass().getName() + STR_METHOD_NAME,
                " sêR[hª¢wèÅ·B"); 
        }
        
        //Qj@@­ú`FbN
        //Q|Pj@@this.­ú == nullÌêAáOðX[·éB
        if (this.orderBizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00406,
                this.getClass().getName() + STR_METHOD_NAME,
                " ­úª¢wèÅ·B"); 
        }

        //Q|Qj@@this.­úªcÆúÅÈ¢êAáOðX[·éB
        //WEB3-XBFEQ-A-CD-0028
        String l_strFlag1 = WEB3GentradeTradingTimeManagement.getBizDateType(
            new Timestamp(this.orderBizDate.getTime()));
        String l_strFlag2 = WEB3GentradeTradingTimeManagement.getFeqBizDateType(
            new Timestamp(this.orderBizDate.getTime()));

        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag1) || 
            WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFlag2))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02019,
                this.getClass().getName() + STR_METHOD_NAME,
                " ­úÍcÆúÅÍ èÜ¹ñB" + this.orderBizDate); 
        }
        
        log.exiting(STR_METHOD_NAME);  
    }
}
@
