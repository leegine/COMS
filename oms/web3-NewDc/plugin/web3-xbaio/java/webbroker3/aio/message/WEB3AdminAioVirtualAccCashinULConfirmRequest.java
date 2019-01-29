head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.03.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioVirtualAccCashinULConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : バーチャル口座入金UL確認リクエスト(WEB3AdminAioVirtualAccCashinULConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/9 李小健 (中訊) 新規作成
                 : 2006/06/12 呉艶飛 (中訊) モデルNo.591
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (バーチャル口座入金UL確認リクエスト)<BR>
 * バーチャル口座入金UL確認リクエストクラス<BR>
 * 
 * @@author 李小健(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioVirtualAccCashinULConfirmRequest extends WEB3GenRequest 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    public static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(
            WEB3AdminAioVirtualAccCashinULConfirmRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_virtual_acc_cashin_ul_confirm";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200605091400L;
    
    /**
     * (アップロードファ@イル)<BR>
     * アップロードファ@イル
     */
    public String[] uploadFile;
    
    /**
     * (銀行コード)<BR>
     * 銀行コード
     */
    public String financialInstitutionCode;
    
    /**
     * @@roseuid 4158EB64017C
     */
    public WEB3AdminAioVirtualAccCashinULConfirmRequest() 
    {
    }
    
    /**
     * (createResponseの実装）<BR>
     * <BR>
     * バーチャル口座入金UL確認レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioVirtualAccCashinULConfirmResponse(this);
    }
    
    /**
     * リクエストデータの整合性チェックを行う。<BR>  
     * １） アップロードファ@イルチェック<BR> 
     * １－１）未入力の場合、例外をスローする。<BR> 
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00976<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41BD519702AC
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@アップロードファ@イルのチェック 
        //１－１）　@未入力の場合、例外をスローする。
        if (this.uploadFile == null || this.uploadFile.length == 0)
        {            
            log.debug("アップロードファ@イルのチェック");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "");
        }
        log.exiting(STR_METHOD_NAME);
    }        
}
@
