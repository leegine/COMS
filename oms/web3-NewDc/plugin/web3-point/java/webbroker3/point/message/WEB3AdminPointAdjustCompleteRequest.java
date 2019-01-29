head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointAdjustCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント調整完了リクエスト(WEB3AdminPointAdjustCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント調整完了リクエスト)<BR>
 * ポイント調整完了リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointAdjustCompleteRequest extends WEB3AdminPointManageCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_adjustComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290001L;
    
    /**
     * (調整ポイント)<BR>
     * 入力された調整ポイント<BR>
     */
    public String adjustPoint;
    
    /**
     * (確認時調整前利用可能ポイント)<BR>
     * 確認時に算出した調整前の利用可能ポイント<BR>
     */
    public String beforeAvailablePoint;
    
    /**
     * (暗証番号)<BR>
     * 入力された暗証番号<BR>
     */
    public String password;
    
    /**
     * @@roseuid 41D1254D02BF
     */
    public WEB3AdminPointAdjustCompleteRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125480167
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointAdjustCompleteResponse(this);
    }

}
@
