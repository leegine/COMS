head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFXTransferListConditionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・FX振替一覧条件入力リクエスト(WEB3AdminFXTransferListConditionRequest)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・FX振替一覧条件入力リクエスト) <BR>
 * 管理者・FX振替一覧条件入力リクエストクラス
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AdminFXTransferListConditionRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_fx_transfer_list_condition";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171456L;

    /**
     * (部店コード一覧) <BR>
     * 部店コードの一覧
     */
    public String[] branchCodeList;

    /**
     * @@roseuid 41E7904B0213
     */
    public WEB3AdminFXTransferListConditionRequest()
    {
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferListConditionRequest.class);

    /**
     * (validate) <BR>
     * リクエストデータのチェックを行う。 <BR>
     * <BR>
     * １）部店コード <BR>
     * <BR>
     * this.部店コード == null or <BR>
     * this.部店コード.length() == 0 <BR>
     * <BR>
     * の場合、例外をスローする。 <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00833 <BR>
     * <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01757 <BR>
     * <BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 41C2667E01F3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）部店コード 
        //this.部店コード == null or 
        //this.部店コード.length() == 0 
        //の場合、例外をスローする。 
        if (this.branchCodeList == null)
        {
            log.debug("部店コードが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }
        if (this.branchCodeList.length == 0)
        {
            log.debug("部店コードの要素数が０です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの要素数が０です。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 管理者・FX振替一覧条件入力レスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904B029F
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFXTransferListConditionResponse(this);
    }
}@
