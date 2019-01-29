head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3PointApplyCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント申込完了リクエスト(WEB3PointApplyCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント申込完了リクエスト)<BR>
 * ポイント申込完了リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3PointApplyCompleteRequest extends WEB3PointApplyCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "point_applyComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290009L;
    
    /**
     * (暗証番号)<BR>
     * 画面にて入力された暗証番号<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D12551000F
     */
    public WEB3PointApplyCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125480167
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3PointApplyCompleteResponse(this);
    }

}
@
