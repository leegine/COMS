head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectListSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査対象一覧検索リクエスト(WEB3AdminAccOpenInspectListSearchRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/19 張秋穎　@(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者口座開設審査対象一覧検索リクエスト)<BR>
 * 管理者口座開設審査対象一覧検索リクエスト
 * 
 * @@author 張秋穎
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectListSearchRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_AccOpen_inspectListSearch";
    
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200606151150L;
    
    /**
     * @@roseuid 44912C11029F
     */
    public WEB3AdminAccOpenInspectListSearchRequest() 
    {
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenInspectListSearchResponse(this);
    }

}
@
