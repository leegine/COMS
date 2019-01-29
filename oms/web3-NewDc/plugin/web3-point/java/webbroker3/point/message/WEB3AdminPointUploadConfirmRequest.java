head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointUploadConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : アップロード確認リクエスト(WEB3AdminPointUploadConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (アップロード確認リクエスト)<BR>
 * アップロード確認リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointUploadConfirmRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_uploadConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290015L;
    
    /**
     * (アップロードファ@イル)<BR>
     * 入力されたアップロードファ@イル<BR>
     * （CSVファ@イルの行の配列）<BR>
     */
    public String[] uploadFile;
    
    /**
     * @@roseuid 41D1254F02AF
     */
    public WEB3AdminPointUploadConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1254F02BF
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointUploadConfirmResponse(this);
    }
}
@
