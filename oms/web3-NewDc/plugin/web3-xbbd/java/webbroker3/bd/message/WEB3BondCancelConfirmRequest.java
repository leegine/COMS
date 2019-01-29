head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondCancelConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券取消確認リクエスト(WEB3BondCancelConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 趙林鵬 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券取消確認リクエスト)<BR>
 * 債券取消確認リクエスト<BR>
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondCancelConfirmRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondCancelConfirmRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_cancel_confirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;
    
    /**
     * (注文ID)<BR>
     * 注文ID<BR>
     */
    public String orderId;
    
    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondCancelConfirmRequest()
    {
        
    }
    
    /**
     * 当クラスの整合性チェックを行う<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>  
     * <BR>
     * １）注文IDチェック <BR>
     * 　@　@注文ID == null の場合、例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00600<BR> 
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）注文IDチェック 
        //注文ID==nullの場合、例外をスローする。 
        if (this.orderId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
 
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3BondCancelConfirmResponse(this);
    }
}
@
