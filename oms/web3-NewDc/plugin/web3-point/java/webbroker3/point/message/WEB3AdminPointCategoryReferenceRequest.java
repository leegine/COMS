head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー一覧リクエスト(WEB3AdminPointCategoryReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/

package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (カテゴリー一覧リクエスト)<BR>
 * カテゴリー一覧リクエストクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290112L;
    
    /**
     * @@roseuid 41D12322007D
     */
    public WEB3AdminPointCategoryReferenceRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D12322008C
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointCategoryReferenceResponse(this);
    }
}
@
