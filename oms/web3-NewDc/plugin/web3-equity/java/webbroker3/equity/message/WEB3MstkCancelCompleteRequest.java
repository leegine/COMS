head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文取消完了リクエスト(WEB3MstkCancelCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 カク寛新 (中訊) 新規作成
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式ミニ投資注文取消完了リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資注文取消完了リクエストクラス<
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3MstkCancelCompleteRequest extends WEB3GenRequest 
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkCancelCompleteRequest.class);
            
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "mstk_cancelComplete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101054L;
    
    /**
     * (ID)<BR>
     * 取消対象データの注文ＩＤ<BR>
     */
    public String id;
    
    /**
     * (暗証番号)<BR>
     */
    public String password;
    
    /**
     * (確認時発注日)<BR>
     */
    public Date checkDate;
    
    /**
     * @@roseuid 4167B04D030D
     */
    public WEB3MstkCancelCompleteRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@確認時発注日チェック<BR>
     * 　@　@　@　@this.確認時発注＝nullであった場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * ２）　@　@ＩＤチェック<BR>
     * 　@　@　@　@this.ＩＤ＝nullであった場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * @@roseuid 4112F38D0150
     */
    public void validate() throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);        
        
        //１）　@確認時発注日チェック
        if(this.checkDate == null)
        {
            
            log.error("確認時発注＝nullであった場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                    this.getClass().getName() + "validate()");
                         
        }
        
        //２）　@　@ＩＤチェック
        else if(this.id == null)
        {
            
            log.error("ＩＤ＝nullであった場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                    this.getClass().getName() + "validate()");
            
        } 
        log.exiting(STR_METHOD_NAME);   
        
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B04D032B
     */
    public WEB3GenResponse createResponse() 
    {
        
        return new WEB3MstkCancelCompleteResponse(this);
        
    }
}
@
