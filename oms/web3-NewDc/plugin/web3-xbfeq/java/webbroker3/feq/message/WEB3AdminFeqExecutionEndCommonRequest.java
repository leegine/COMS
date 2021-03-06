head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.28.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÇÒO®oI¹¤ÊNGXg(WEB3AdminFeqExecutionEndCommonRequest.java)
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
import webbroker3.util.WEB3LogUtility;


/**
 * (ÇÒO®oI¹¤ÊNGXg)<BR>
 * ÇÒO®oI¹¤ÊNGXgNX
 *   
 * @@author sp
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionEndCommonRequest extends WEB3GenRequest 
{
    
    /**
     * OoÍ[eBeBB<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndCommonRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionEndCommon";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;   
    
    /**
     * (oI¹À{ê)<BR>
     * O®oI¹À{ðÌzñ
     */
    public WEB3FeqExecutionEndExecuteCondUnit[] executionEndExecuteCondList;
    
    /**
     * @@roseuid 42CE39FE0213
     */
    public WEB3AdminFeqExecutionEndCommonRequest() 
    {
     
    }
    
    /**
     * NGXgf[^Ì®«ð`FbN·éB<BR>
     * <BR>
     * Pj@@this.oI¹À{ê == nullÌêAáOðX[·éB<BR>
     * @@class: WEB3BusinessLayerException<BR>
     * @@tag:   BUSINESS_ERROR_02031<BR>
     * <BR>
     * Qj@@this.oI¹À{êÌvfªAÈºÌðÀ{·éB<BR>
     * @@Q|PjÎÛÌvf.validate()ðR[·éB
     * @@throws WEB3BaseException
     * @@roseuid 42AFEFD002A7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //Pj@@this.oI¹À{ê == nullÌêAáOðX[·éB
        if (this.executionEndExecuteCondList == null || 
            this.executionEndExecuteCondList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02031,
                this.getClass().getName() + STR_METHOD_NAME,
                " oI¹À{êª¶ÝµÜ¹ñB"); 
        }
        
        //Qj@@this.oI¹À{êÌvfªAÈºÌðÀ{·éB
        //Q|PjÎÛÌvf.validate()ðR[·éB
        int l_intCnt = this.executionEndExecuteCondList.length;
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3FeqExecutionEndExecuteCondUnit l_unit = executionEndExecuteCondList[i];
            if (l_unit != null)
            {
                l_unit.validate();
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
        return null;
    }
}
@
