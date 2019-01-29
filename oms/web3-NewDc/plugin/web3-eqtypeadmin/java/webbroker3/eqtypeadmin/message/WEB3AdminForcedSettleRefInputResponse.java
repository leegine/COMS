head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済注文照会入力レスポンス(WEB3AdminForcedSettleRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成
*/

package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・強制決済注文照会入力レスポンス)<BR>
 * 管理者・強制決済注文照会入力レスポンスクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleRefInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_ref_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;
    
    /**
     * (発注日一覧)<BR>
     * 発注日一覧<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (決済期日一覧)<BR>
     * 決済期日一覧<BR>
     */
    public Date[] settleTimeLimitList;
    
    /**
     * (市場コード一覧)<BR>
     * 市場コード一覧<BR>
     */
    public String[] marketCodeList;
    
    /**
     * (注文エラー理由コード一覧)<BR>
     * 注文エラー理由コード一覧<BR>
     * 以下の値で構成される配列<BR>
     * <BR>
     * 0005：　@建株残高不足エラー<BR>
     * 0006：　@売買停止銘柄エラー<BR>
     * 0016：　@決済期日到来済エラー<BR>
     * 0017：　@現引・現渡注文登録済エラー<BR>
     * 9001：　@その他エラー<BR>
     */
    public String[] errorReason;
    
    /**
     * (強制決済理由一覧)<BR>
     * 強制決済理由一覧<BR>
     */
    public WEB3AdminForcedSettleReasonUnit[] forcedSettleReasonList;
    
    /**
     * @@roseuid 462CA429013D
     */
    public WEB3AdminForcedSettleRefInputResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminForcedSettleRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
