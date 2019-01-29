head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会レスポンス(WEB3AdminForcedSettleReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・強制決済注文照会レスポンス)<BR>
 * 管理者・強制決済注文照会レスポンスクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleReferenceResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * (総ページ数)<BR>
     * 総ページ数<BR>
     */
    public String totalPages = "0";
    
    /**
     * (総レコード数)<BR>
     * 総レコード数<BR>
     */
    public String totalRecords = "0";
    
    /**
     * (表示ページ番号)<BR>
     * 表示ページ番号<BR>
     */
    public String pageIndex = "0";
    
    /**
     * (強制決済注文一覧)<BR>
     * 強制決済注文一覧<BR>
     */
    public WEB3AdminForcedSettleTemporaryOrderUnit[] forcedSettleTemporaryOrderList;
    
    /**
     * @@roseuid 462CA42802A4
     */
    public WEB3AdminForcedSettleReferenceResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminForcedSettleReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
