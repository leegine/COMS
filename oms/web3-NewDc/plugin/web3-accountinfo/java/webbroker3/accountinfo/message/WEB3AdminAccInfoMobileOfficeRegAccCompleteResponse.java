head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了レスポンス(WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/14 周捷 (中訊) 新規作成 モデルNo.153
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了レスポンス)<BR>
 *
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegAccComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200612141400L;

    /**
     * (管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストデータ<BR>
     */
    public WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
