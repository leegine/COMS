head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.53.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー登録共通リクエスト(WEB3AdminPointCategoryRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (カテゴリー登録共通リクエスト)<BR>
 * カテゴリー登録共通リクエストクラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryRegistCommonRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_categoryRegistCommon";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412290111L;
    
    /**
     * (カテゴリー名)<BR>
     * カテゴリーの名称<BR>
     */
    public String categoryName;
    
    /**
     * (カテゴリー概要)<BR>
     * カテゴリーの概要<BR>
     */
    public String categoryOutline;
    
    /**
     * @@roseuid 41D1232203B9
     */
    public WEB3AdminPointCategoryRegistCommonRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D1232203C8
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
