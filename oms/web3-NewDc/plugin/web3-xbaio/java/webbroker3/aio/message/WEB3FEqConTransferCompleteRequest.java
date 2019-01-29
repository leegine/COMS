head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座への振替完了リクエスト(WEB3FEqConTransferCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外株口座への振替完了リクエスト)<BR>
 * 外株口座への振替完了リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3FEqConTransferCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (振替金額)<BR>
     * 振替金額
     */
    public String changeAmt;
    
    /**
     * (確認時発注日)<BR>
     * 確認時発注日
     */
    public Date checkDate;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 42354D78036B
     */
    public WEB3FEqConTransferCompleteRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCompleteRequest.class);

    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）振替金額<BR>
     * <BR>
     *   this.振替金額 == null or<BR>
     *   this.振替金額 <= 0 or<BR>
     *   this.振替金額.length >= 10<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00759<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00809<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00810<BR>
     * <BR>
     * ２）確認時発注日<BR>
     * <BR>
     *   this.確認時発注日 == null<BR>
     * <BR>
     *   の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * @@roseuid 41E3701B0172
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）振替金額
        //this.振替金額 == null or 
        //this.振替金額 <= 0 or 
        //this.振替金額.length >= 10
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.changeAmt))
        {
            log.debug("振替金額が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00759,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振替金額が未指定です。");
        }
        
        if(Double.parseDouble(this.changeAmt) <= 0)
        {
            log.debug("振替金額 <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00809,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振替金額 <= 0");
            
        }
        
        if(this.changeAmt.length() >= 10)
        {
            log.debug("振替金額.length >= 10");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00810,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "振替金額.length >= 10");
        }
        
        //２）確認時発注日 
        //this.確認時発注日 == null
        //の場合、例外をスローする。 
        if(this.checkDate == null)
        {
            log.debug("確認時発注日 == null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "確認時発注日 == null");
        
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座への振替完了レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FEqConTransferCompleteResponse(this);
    }
}
@
