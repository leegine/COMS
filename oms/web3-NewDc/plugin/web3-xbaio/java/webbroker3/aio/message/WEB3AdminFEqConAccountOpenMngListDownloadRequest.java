head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外株口座開設管理一覧ダウンロードリクエスト(WEB3AdminFEqConAccountOpenMngListDownloadRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 周勇(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (外株口座開設管理一覧ダウンロードリクエスト)<BR>
 * 外株口座開設管理一覧ダウンロードリクエストクラス
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngListDownloadRequest extends WEB3AdminFEqConAccountOpenMngListCommonRequest 
{ 
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list_download";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (ダウンロード件数)<BR>
     * ダウンロード件数
     */
    public String downloadNumber;
    
    /**
     * @@roseuid 423552EB0177
     */
    public WEB3AdminFEqConAccountOpenMngListDownloadRequest() 
    {
     
    }
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngListDownloadRequest.class);
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）スーパークラスのvalidate()メソッドをコールする。<BR>
     * <BR>
     * ２）ダウンロード件数<BR>
     * <BR>
     *   this.ダウンロード件数 == null or<BR>
     *   this.ダウンロード件数 <= 0<BR>
     * <BR>
     *   の場合、例外をスローする。
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01950<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01951<BR>
     * <BR>
     * @@roseuid 41F8D53300A3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）スーパークラスのvalidate()メソッドをコールする。 
        super.validate();
        
        //２）ダウンロード件数
        //this.ダウンロード件数 == null or 
        //this.ダウンロード件数 <= 0
        //の場合、例外をスローする。
        if(WEB3StringTypeUtility.isEmpty(this.downloadNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01950,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ダウンロード件数が未指定です。");
        }
        
        if(Double.parseDouble(this.downloadNumber) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01951,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ダウンロード件数[" + this.downloadNumber + "]。");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 外株口座開設管理一覧ダウンロードレスポンスオブジェクトを返却する。
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountOpenMngListDownloadResponse(this);
    }
}
@
