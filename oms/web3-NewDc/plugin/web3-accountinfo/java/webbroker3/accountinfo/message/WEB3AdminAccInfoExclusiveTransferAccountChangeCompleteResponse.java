head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報専用振込先口座変更完了ﾚｽﾎﾟﾝｽ(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
                   2006/09/11 車進　@ (中訊) 仕様変更管理No.123
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報専用振込先口座変更完了ﾚｽﾎﾟﾝｽ)<BR>
 * 管理者お客様情報専用振込先口座変更完了ﾚｽﾎﾟﾝｽ<BR>
 * @@author カク寛新
 * @@version 1.0
 */
public class WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_exclusiveTransferAccountChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082141L;
    
    /**
     * (口座番号)<BR>
     * 自動採番取得済み口座番号<BR>
     */
    public String accountCode;    

    /**
     * @@roseuid 418F38660242
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
