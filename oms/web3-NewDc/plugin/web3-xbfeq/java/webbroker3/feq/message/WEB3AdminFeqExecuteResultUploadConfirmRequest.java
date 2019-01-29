head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.31.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecuteResultUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式約定結果アップロード確認リクエスト(WEB3AdminFeqExecuteResultUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者外国株式約定結果アップロード確認リクエスト)<BR>
 * 管理者外国株式約定結果アップロード確認リクエストクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqExecuteResultUploadConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminFeqExecuteResultUploadConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executeResultUploadConfirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;    
    
    /**
     * (アップロードファ@イル)<BR>
     * アップロードファ@イル
     */
    public String[] uploadFileList;
    
    /**
     * @@roseuid 42CE39FA0138
     */
    public WEB3AdminFeqExecuteResultUploadConfirmRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@アップロードファ@イルのチェック <BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00976<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429E940C0218
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@アップロードファ@イルのチェック 
        // 　@１－１）　@未入力の場合、例外をスローする。
        if (this.uploadFileList == null || this.uploadFileList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                getClass().getName() + STR_METHOD_NAME,
                "アップロードファ@イルが未入力です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqExecuteResultUploadConfirmResponse(this);
    }
}
@
