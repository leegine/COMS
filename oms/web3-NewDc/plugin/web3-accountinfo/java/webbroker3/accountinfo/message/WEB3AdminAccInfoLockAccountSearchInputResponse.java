head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLockAccountSearchInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報ロック顧客問合せ一覧入力レスポンス(WEB3AdminAccInfoLockAccountSearchInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 呉艶飛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報ロック顧客問合せ一覧入力レスポンス)<BR>
 * 管理者お客様情報ロック顧客問合せ一覧入力レスポンス<BR>
 * <BR>
 * @@author 呉艶飛<BR>
 * @@version 1.0<BR>
 */
public class WEB3AdminAccInfoLockAccountSearchInputResponse extends WEB3GenResponse 
{
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_lockAccountSearchInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200509191350L;
    
    /**
     * (登録日（自）)<BR>
     * 登録日（自）<BR>
     * 
     */
    public Date registDateFrom;
    
    /**
     * (登録日（至）)<BR>
     * 登録日（至）<BR>
     * 
     */
    public Date registDateTo;
    
    public WEB3AdminAccInfoLockAccountSearchInputResponse()
    {

    }

    /**
     * (管理者お客様情報ロック顧客問合せ一覧入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLockAccountSearchInputResponse
     * @@roseuid 416657DA0191
     */
    public WEB3AdminAccInfoLockAccountSearchInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
