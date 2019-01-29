head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替取消完了リクエスト(WEB3FEqConTransferCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株口座への振替取消完了リクエスト)<BR>
 * 外株口座への振替取消完了リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferCancelCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_cancel_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (注文ID)<BR>
     * 取消対象の注文ID
     */
    public String orderId;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 4235526E0271
     */
    public WEB3FEqConTransferCancelCompleteRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCancelCompleteRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）注文ID<BR>
     * <BR>
     *   this.注文ID == null<BR>
     * <BR>
     *   の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00600<BR>
     * <BR>
     * @@roseuid 41E3708D00C6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）注文ID
        //this.注文ID == null
        //の場合、例外をスローする。
        if(WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            log.debug("注文ID == null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文ID == null");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座への振替取消完了レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FEqConTransferCancelCompleteResponse(this);
    }
}
@
