head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointUploadCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード完了リクエスト(WEB3AdminPointUploadCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (アップロード完了リクエスト)<BR>
 * アップロード完了リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointUploadCompleteRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_uploadComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290018L;
    
    /**
     * (アップロードID)<BR>
     * アップロードID<BR>
     */
    public String uploadId;
    
    /**
     * (暗証番号)<BR>
     * 入力された暗証番号<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D12550004E
     */
    public WEB3AdminPointUploadCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12550006D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointUploadCompleteResponse(this);
    }
}
@
