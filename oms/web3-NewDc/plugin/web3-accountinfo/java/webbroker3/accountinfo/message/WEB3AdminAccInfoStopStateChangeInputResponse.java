head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoStopStateChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報停止状況変更入力レスポンス(WEB3AdminAccInfoStopStateChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報停止状況変更入力レスポンス)<BR>
 * 管理者お客様情報停止状況変更入力レスポンス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AdminAccInfoStopStateChangeInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_stopStateChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082101L;

    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;
    
    /**
     * (顧客名（漢字））<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;
    
    /**
     * (扱者コード)<BR>
     * 扱者コード
     */
    public String traderCode;
    
    /**
     * (停止情報)<BR>
     * 停止情報<BR>
     */
    public WEB3AccInfoStopInfo stopInfo;

    /**
     * @@roseuid 418F386401A5
     */
    public WEB3AdminAccInfoStopStateChangeInputResponse()
    {

    }

    /**
     * (管理者お客様情報停止状況変更入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoStopStateChangeInputResponse
     * @@roseuid 4166529F0097
     */
    public WEB3AdminAccInfoStopStateChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
