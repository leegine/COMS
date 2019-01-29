head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品登録完了リクエスト(WEB3AdminPointPremiumRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (景品登録完了リクエスト)<BR>
 * 景品登録完了リクエストクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumRegistCompleteRequest extends WEB3AdminPointPremiumRegistCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_premiumRegistComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412291447L;
    
    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D12548030D
     */
    public WEB3AdminPointPremiumRegistCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1232302FD
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointPremiumRegistCompleteResponse(this);
    }
}
@
