head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者問合せ管理お問合せ詳細レスポンス(WEB3AdminFaqDetailsResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者問合せ管理お問合せ詳細レスポンス)<BR>
 * 管理者問合せ管理お問合せ詳細レスポンス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminFaqDetailsResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_faq_details";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171302L;
    
    /**
     * (問合せ情報)<BR>
     * 問合せ情報<BR>
     */
    public WEB3FaqInfo faqInfo;

    /**
     * @@roseuid 41C25C0901E4
     */
    public WEB3AdminFaqDetailsResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminFaqDetailsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
