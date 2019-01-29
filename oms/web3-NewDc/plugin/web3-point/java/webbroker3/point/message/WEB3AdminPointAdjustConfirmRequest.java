head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.58.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointAdjustConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント調整確認リクエスト(WEB3AdminPointAdjustConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント調整確認リクエスト)<BR>
 * ポイント調整確認リクエストクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointAdjustConfirmRequest extends WEB3AdminPointManageCommonRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_adjustConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410150128L;
    
    /**
     * (調整ポイント)<BR>
     * 入力された調整ポイント<BR>
     */
    public String adjustPoint;
    
    /**
     * @@roseuid 41D1254D0251
     */
    public WEB3AdminPointAdjustConfirmRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125480167
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointAdjustConfirmResponse(this);
    }

}
@
