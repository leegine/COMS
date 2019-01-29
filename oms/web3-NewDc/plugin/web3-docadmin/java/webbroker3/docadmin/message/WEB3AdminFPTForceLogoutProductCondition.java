head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFPTForceLogoutProductCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者 書面未承諾 強制ログアウト対象銘柄条件(WEB3AdminFPTForceLogoutProductCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;


/**
 * 管理者 書面未承諾 強制ログアウト対象銘柄条件
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3AdminFPTForceLogoutProductCondition extends Message
{
	
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFPTForceLogoutProductCondition.class);
    
    /**
     * 書面種類コード一覧
     */
    public String[] documentCatCodeArr;
    
    /**
     * 書面区分一覧
     */
    public String[] documentDivArr;
    
    /**
     * 電子鳩銘柄コード一覧
     */
    public String[] batoProductCodeArr;
    
    /**
     * @@roseuid 47DF46770121
     */
    public WEB3AdminFPTForceLogoutProductCondition() 
    {
     
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。 
     * （ただし、当クラス内で完結する簡易チェックのみとする。） 
     * 
     * １）書面種類コード一覧 ==null 又は サイズ＜1 の場合、
     * 　@例外（BUSINESS_ERROR_03013）をスローする。
     * 
     * ２）書面区分一覧 ==null 又は サイズ＜1 の場合、
     * 　@例外（BUSINESS_ERROR_02948）をスローする。
     * 
     * ３）電子鳩銘柄コード一覧 ==null 又は サイズ＜1 の場合、
     * 　@例外（BUSINESS_ERROR_03009）をスローする。
     * @@roseuid 47CF784F01C1
     */
    public void validate()  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）書面種類コード一覧 ==null 又は サイズ＜1 の場合、
        // 　@例外（BUSINESS_ERROR_03013）をスローする。
        if (this.documentCatCodeArr == null || this.documentCatCodeArr.length <1 ) 
        {
            log.debug("書面種類コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03013,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面種類コードが未指定です。");
        }
        
        //２）書面区分一覧 ==null 又は サイズ＜1 の場合、
        //　@例外（BUSINESS_ERROR_02948）をスローする。
        if (this.documentDivArr == null || this.documentDivArr.length < 1) 
        {
            log.debug("書面区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02948,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "書面区分が未指定です。");
        }
        
        //３）電子鳩銘柄コード一覧 ==null 又は サイズ＜1 の場合、
        //　@例外（BUSINESS_ERROR_03009）をスローする。 
        if (this.batoProductCodeArr == null || this.batoProductCodeArr.length <1 ) 
        {
            log.debug("電子鳩銘柄コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03009,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子鳩銘柄コードが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
