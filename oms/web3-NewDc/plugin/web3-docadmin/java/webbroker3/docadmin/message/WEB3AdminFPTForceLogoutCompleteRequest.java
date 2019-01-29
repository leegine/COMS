head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・書面未承諾 強制ログアウト完了リクエスト(WEB3AdminFPTForceLogoutCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 許(FLJ) 新規作成
*/
package webbroker3.docadmin.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * 管理者 書面未承諾 強制ログアウト完了リクエスト
 * <BR>
 * @@author Kyo
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_fpt_force_logout_complete";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200803181606L;
    
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutCompleteRequest.class);
    
    /**
     * 書面区分管理情報一覧
     */
    public WEB3FPTDocumentDivAdminInfoUnit[] documentDivList;
    
    /**
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 47DF467703B1
     */
    public WEB3AdminFPTForceLogoutCompleteRequest() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 
     * （ただし、当クラス内で完結する簡易チェックのみとする。） 
     * １） 書面区分管理一覧チェック(書面区分管理一覧配列全要素に対してチェックを行う) 
     * 
     *   １-１） this.書面区分管理情報一覧 == null 
     * の場合、例外（BUSINESS_ERROR_03007）をスローする。
     * 
     *   １-２） this.書面区分管理情報一覧[index].書面種類コード == (null or "") 
     * の場合、例外（BUSINESS_ERROR_03013）をスローする。
     *   １-３） this.書面区分管理情報一覧[index].書面種類コードが半角数字以外 
     * の場合、例外（BUSINESS_ERROR_02997）をスローする。
     *   １-４） this.書面区分管理情報一覧[index].書面種類コードが4桁以上 
     * の場合、例外（BUSINESS_ERROR_02997）をスローする。
     * @@roseuid 47CF76AE015B
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        if (this.documentDivList == null) 
        {
            log.debug("書面区分管理情報が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03007,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面区分管理情報が未指定です。");
        }
        for(int i=0;i<this.documentDivList.length;i++)
        {
            String l_documentCategory = this.documentDivList[i].documentCategory;
            if (l_documentCategory == null || l_documentCategory.equals(""))
            {
                log.debug("書面種類コードが未指定です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが未指定です。");
            }
            if ((!WEB3StringTypeUtility.isNumber(l_documentCategory))
                 || (WEB3StringTypeUtility.getByteLength(l_documentCategory) >= 4))
            {
                log.debug("書面種類コードが不正です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02997,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "書面種類コードが不正です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /* (非 Javadoc)
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFPTForceLogoutCompleteResponse(this);
    }
}
@
