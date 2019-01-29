head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.55.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULCancelRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金UL中止リクエスト(WEB3AdminAioVirtualAccCashinULCancelRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/09 李小健 (中訊) 新規作成
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
 * (バーチャル口座入金UL中止リクエスト)<BR>
 * バーチャル口座入金UL中止リクエストクラス
 * 
 * @@author 李小健(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULCancelRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAioVirtualAccCashinULCancelRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_aio_virtual_acc_cashin_ul_cancel";    
    
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200605091050L;

    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadID;
    
    /**
     * @@roseuid 43F49A6E0186
     */
    public WEB3AdminAioVirtualAccCashinULCancelRequest()
    {
    }        
    
    /**
     * (createResponseの実装）<BR>
     * <BR>
     * バーチャル口座入金UL中止レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioVirtualAccCashinULCancelResponse(this);                
    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>  
     * １）　@アップロードIDのチェック<BR> 
     * １－１）未入力の場合、例外をスローする。<BR> 
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00973<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BD519702AC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@アップロードIDのチェック 
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.uploadID))
        {
            log.debug("アップロードIDのチェック");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00973,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "アップロードIDが未指定です。");
        }
        log.exiting(STR_METHOD_NAME);
    }   
}
@
