head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込確認レスポンス(WEB3AccInfoEquityCommissionCourseChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報株式委託手数料コース変更申込確認レスポンス)<BR>
 * お客様情報株式委託手数料コース変更申込確認レスポンス<BR>
 * @@author 彭巍
 * @@version 1.0  
 */
public class WEB3AccInfoEquityCommissionCourseChangeConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082159L;

    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;

    /**
     * @@roseuid 418F39F1029F
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmResponse()
    {

    }

    /**
     * (お客様情報株式委託手数料コース変更申込確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>     
     * @@roseuid 41368DD00219
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
