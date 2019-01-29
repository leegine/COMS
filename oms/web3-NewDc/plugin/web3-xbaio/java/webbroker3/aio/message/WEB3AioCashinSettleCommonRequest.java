head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinSettleCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : マルチバンク決済共通リクエスト(WEB3AioCashinSettleCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 周勇 (中訊) 新規作成              
                   2004/10/22 黄建 (中訊) レビュー         
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
 * (マルチバンク決済共通リクエスト)<BR>
 * マルチバンク決済共通リクエストクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinSettleCommonRequest extends WEB3GenRequest 
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200409291947L; 
   
    /**
     * (AP層セッションID)<BR>
     * 決済PFからリダイレクトされたAP層セッションID<BR>
     * （WEB3にて独自に設定している項目）<BR>
     */
    public String apSessionId;
    
    /**
     * (証券会社コード)<BR>
     * 決済PFからリダイレクトされた証券会社コード<BR>
     * （WEB3にて独自に設定している項目）<BR>
     */
    public String institutionCode;
    
    /**
     * (部店コード)<BR>
     * 決済PFからリダイレクトされた部店コード<BR>
     * （WEB3にて独自に設定している項目）<BR>
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 決済PFからリダイレクトされた顧客コード<BR>
     * （WEB3にて独自に設定している項目）<BR>
     * <BR>
     */
    public String accountCode;
    
    /**
     * (識別コード)<BR>
     * 決済PFからリダイレクトされた識別コード<BR>
     * （WEB3にて独自に設定している項目）<BR>
     */
    public String orderRequestNumber;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinSettleCommonRequest.class);
    
    /**
     * デフォルトコンストラク
     * @@roseuid 4158EB33022F
     */
    public WEB3AioCashinSettleCommonRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158EB330258
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * ２）AP層セッションIDチェック<BR>
     *   リクエストデータ.AP層セッションID = null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00769<BR>
     * <BR>
     * <BR>
     * ３）証券会社コードチェック<BR>
     *   リクエストデータ.証券会社コード = null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00827<BR>
     * <BR>
     * <BR>
     * ４）部店コードチェック<BR>
     *   リクエストデータ.部店コード = null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00779<BR>
     * <BR>
     * <BR>
     * ５）顧客コードチェック<BR>
     *   リクエストデータ.顧客コード = null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * <BR>
     * <BR>
     * ６）識別コードチェック<BR>
     *   リクエストデータ.識別コード = null  の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00829<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40EA3BE101CE
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //２）AP層セッションIDチェック 
        //リクエストデータ.AP層セッションID = null 
        if(WEB3StringTypeUtility.isEmpty(this.apSessionId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00769,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.AP層セッションID = null");
        }
        
        //３）証券会社コードチェック 
        //リクエストデータ.証券会社コード = null 
        if(WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.証券会社コード = null");
        }
        
        //４）部店コードチェック 
        //リクエストデータ.部店コード = null 
        if(WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.部店コード = null");
        }
        //５）顧客コードチェック 
        //リクエストデータ.顧客コード = null 
        if(WEB3StringTypeUtility.isEmpty(this.accountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.顧客コード = null");
        }
        //６）識別コードチェック 
        //リクエストデータ.識別コード = null 
        if(WEB3StringTypeUtility.isEmpty(this.orderRequestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.識別コード = null");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
