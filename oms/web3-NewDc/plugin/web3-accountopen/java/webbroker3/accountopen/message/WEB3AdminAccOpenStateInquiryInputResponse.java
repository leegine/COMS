head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenStateInquiryInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設状況問合せ入力レスポンス(WEB3AdminAccOpenStateInquiryInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
                   2006/09/11 柴雙紅 (中訊) モデルNo.102
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設状況問合せ入力レスポンス)<BR>
 * 管理者口座開設状況問合せ入力レスポンス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
 
public class WEB3AdminAccOpenStateInquiryInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_stateInquiryInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081606L;
    
    /**
     * (専用振込先口座情報一覧)<BR>
     * 専用振込先口座情報一覧<BR>
     */
    public WEB3AccOpenExclusiveAccountInfo[] exclusiveAccountInfoList;

    /**
     * @@roseuid 41B45E76002E
     */
    public WEB3AdminAccOpenStateInquiryInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenStateInquiryInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
