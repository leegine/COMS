head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : オンライン入金共通リクエスト(WEB3AioCashinCommonRequest)
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
 * (オンライン入金共通リクエスト)<BR>
 * オンライン入金共通リクエストクラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinCommonRequest extends WEB3GenRequest 
{        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200409291950L;       
    
    /**
     * (決済機@関ID)<BR>
     * 画面にて選択された決済機@関のID<BR>
     */
    public String paySchemeId;
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinCommonRequest.class);
    
    /**
     * @@roseuid 4158E9B6021E
     */
    public WEB3AioCashinCommonRequest() 
    {
     
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 4158E9B60232
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinCommonResponse(this);
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）決済機@関IDチェック<BR>
     *   リクエストデータ.決済機@関ID = null or<BR>
     *   リクエストデータ.決済機@関ID.length() != 11 or<BR>
     *   リクエストデータ.決済機@関ID.startsWith("ComOndebi") = false<BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00767<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40E51CBA01B8
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）決済機@関IDチェック
        //リクエストデータ.決済機@関ID = null
        if(WEB3StringTypeUtility.isEmpty(this.paySchemeId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.決済機@関ID = null"); 
        }
        
        //リクエストデータ.決済機@関ID.startsWith("ComOndebi") = false        
        if(this.paySchemeId.startsWith("ComOndebi") == false)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.決済機@関ID.startsWith(ComOndebi) == false"); 
        }
        
        //リクエストデータ.決済機@関ID.length() != 11
        if(this.paySchemeId.length() != 11)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00767,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "リクエストデータ.決済機@関ID.length() != 11, " +
                "リクエストデータ.決済機@関ID.length() = " + this.paySchemeId.length()); 
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
