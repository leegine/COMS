head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレスアップロード確認リクエスト(WEB3AdminAccInfoMailAddressUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 尹偉鋒 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者お客様情報メールアドレスアップロード確認リクエスト)<BR>
 * 管理者お客様情報メールアドレスアップロード確認リクエスト<BR>
 * 
 * @@author 尹偉鋒(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadConfirmRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadConfirmRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressUploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603141715L;
    
    /**
     * (アップロードファ@イル)<BR>
     * ｱｯﾌﾟﾛｰﾄﾞするファ@イルを読込み、1行ごとにセットしてString[]で転送する<BR>
     */
    public String[] uploadFile;

    /**
     * @@roseuid 418F3857000F
     */
    public WEB3AdminAccInfoMailAddressUploadConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoMailAddressUploadConfirmResponse(this);
    }
    
    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １）　@アップロードファ@イルのチェック<BR>  
     *   １－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00976<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 4163B1E4039C
     */
    public void validate() throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // １）　@アップロードファ@イルのチェック 
        //   １－１）　@未入力の場合、例外をスローする。
        if (this.uploadFile == null || this.uploadFile.length == 0)
        {  
            log.debug("アップロードファ@イルが未指定です。");
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                this.getClass().getName() + STR_METHOD_NAME, 
                "アップロードファ@イルが未指定です。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
