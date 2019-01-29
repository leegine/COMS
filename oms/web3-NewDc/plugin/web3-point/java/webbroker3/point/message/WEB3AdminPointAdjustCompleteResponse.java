head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.57.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointAdjustCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント調整完了レスポンス(WEB3AdminPointAdjustCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (ポイント調整完了レスポンス)<BR>
 * ポイント調整完了レスポンスクラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointAdjustCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_adjustComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410150129L;
    
    /**
     * @@roseuid 41D1254E000F
     */
    public WEB3AdminPointAdjustCompleteResponse() 
    {
     
    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminPointAdjustCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
