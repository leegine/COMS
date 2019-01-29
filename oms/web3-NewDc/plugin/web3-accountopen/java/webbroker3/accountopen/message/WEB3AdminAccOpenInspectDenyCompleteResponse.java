head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenInspectDenyCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設審査否認完了レスポンス(WEB3AdminAccOpenInspectDenyCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/16 張秋穎 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者口座開設審査否認完了レスポンス)<BR>
 * 管理者口座開設審査否認完了レスポンス
 * 
 * @@author 張秋穎
 * @@version 1.0
 */
public class WEB3AdminAccOpenInspectDenyCompleteResponse extends WEB3GenResponse
{

	/**
     * PTYPE
     */
    public static final String PTYPE = "admin_AccOpen_inspectDenyComplete";
    
    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200606151150L;
    
    /**
     * (口座開設審査情報)<BR>
     */
    public WEB3AccOpenInspectInfo[] accopenInspectList;
    
    /**
     * @@roseuid 44912C100138
     */
    public WEB3AdminAccOpenInspectDenyCompleteResponse() 
    {
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenInspectDenyCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
