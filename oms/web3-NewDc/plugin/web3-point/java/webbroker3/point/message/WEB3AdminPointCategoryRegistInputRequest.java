head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.55.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー登録入力リクエスト(WEB3AdminPointCategoryRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (カテゴリー登録入力リクエスト)<BR>
 * カテゴリー登録入力リクエストクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290106L;
    
    /**
     * @@roseuid 41D1232202EE
     */
    public WEB3AdminPointCategoryRegistInputRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12322030D
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointCategoryRegistInputResponse(this);
    }
}
@
