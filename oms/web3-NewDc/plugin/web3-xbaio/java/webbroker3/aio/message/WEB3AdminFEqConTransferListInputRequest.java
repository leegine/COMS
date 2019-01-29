head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConTransferListInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株振替一覧条件入力リクエスト(WEB3AdminFEqConTransferListInputRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (外株振替一覧条件入力リクエスト)<BR>
 * 外株振替一覧条件入力リクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConTransferListInputRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_transfer_list_input";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String[] branchCode;
    
    /**
     * @@roseuid 4235559E02DE
     */
    public WEB3AdminFEqConTransferListInputRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConTransferListInputRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）部店コード<BR>
     * <BR>
     *   this.部店コード == null or<BR>
     *   this.部店コード.length == 0<BR>
     * <BR>
     *   の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * @@roseuid 41D0BA3D03AC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード
        //this.部店コード == null or 
        //this.部店コード.length == 0
        //の場合、例外をスローする。
        if(this.branchCode == null || this.branchCode.length ==0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コード == null or 部店コード.length == 0");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株振替一覧条件入力レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConTransferListInputResponse(this);
    }
}
@
