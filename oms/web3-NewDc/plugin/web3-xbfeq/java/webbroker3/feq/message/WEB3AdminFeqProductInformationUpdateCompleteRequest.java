head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.25.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInformationUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者外国株式銘柄情報更新完了リクエスト(WEB3AdminFeqProductInformationUpdateCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 鄭海良(中訊) 新規作成
                 : 2005/08/02 郭英(中訊) レビュー
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者外国株式銘柄情報更新完了リクエスト)<BR>
 * 管理者外国株式銘柄情報更新完了リクエストクラス
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqProductInformationUpdateCompleteRequest 
    extends WEB3AdminFeqProductInfomationUpdateCommonRequest 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productInformationUpdateComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 42CE39FA037A
     */
    public WEB3AdminFeqProductInformationUpdateCompleteRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqProductInformationUpdateCompleteResponse(this);
    }
}
@
