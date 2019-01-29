head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理ステータス更新入力リクエスト(WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
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
 * (外株口座開設管理ステータス更新入力リクエスト)<BR>
 * 外株口座開設管理ステータス更新入力リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_status_upd_input";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;
    
    /**
     * @@roseuid 423552E802EE
     */
    public WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngStatusUpdInputRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *   this.部店コード == null<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）識別コード<BR>
     *   this.識別コード == null<BR>
     * <BR>
     *   の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00829<BR>
     * <BR>
     * @@roseuid 41E5FD010146
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード
        //this.部店コード == null
        //の場合、例外をスローする。
        if(WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        
        //２）識別コード 
        //this.識別コード == null
        //の場合、例外をスローする。 
        if(WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "識別コードが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座開設管理ステータス更新入力レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountOpenMngStatusUpdInputResponse(this);
    }
}
@
