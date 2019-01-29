head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.36.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用管理者顧客データアップロード確認リクエスト(WEB3AdminSrvRegiUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 鄭海良(中訊) 新規作成
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用管理者顧客データアップロード確認リクエスト)<BR>
 * サービス利用管理者アップロード確認リクエストクラス<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiUploadConfirmRequest extends WEB3GenRequest 
{

    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminSrvRegiUploadConfirmRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_uploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151639L;
    
    /**
     * (サービス区分)
     */
    public String serviceDiv;
    
    /**
     * (アップロードファ@イル)
     */
    public String[] lines;
    
    /**
     * (サービス利用管理者アップロード確認リクエスト)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 41007F390222
     */
    public WEB3AdminSrvRegiUploadConfirmRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * サービス利用管理者アップロード確認レスポンスを生成して返却する。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 41007F670212
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiUploadConfirmResponse();
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * 1) サービス区分のチェック<BR>
     * 　@this.サービス区分==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     * <BR>
     * 2) アップロードファ@イルのチェック<BR>
     * 　@this.アップロードファ@イル==nullの場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00976<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41007F670222
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //1) サービス区分のチェック<BR>
        if(this.serviceDiv == null || "".equals(this.serviceDiv))
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("サービス区分エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        //U00871 BEGIN...        
        if (this.serviceDiv.length() != 2)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00954,
                getClass().getName() + STR_METHOD_NAME); 
        }
        //U00871 END.        

        //2) アップロードファ@イルのチェック
        if(this.lines == null || this.lines.length == 0)
        {
            WEB3BaseException l_e = 
                new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00976,
                    this.getClass().getName() + STR_METHOD_NAME);
            log.debug("アップロードファ@イルエラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
