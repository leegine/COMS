head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMobileOfficeRegistInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報携帯番号・勤務先情報変更入力レスポンス(WEB3AdminAccInfoMobileOfficeRegistInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 カク寛新 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報携帯番号・勤務先情報変更入力レスポンス)<BR>
 * 管理者お客様情報携帯番号・勤務先情報変更入力レスポンス<BR>
 * @@author カク寛新
 * @@version 1.0 
 */
public class WEB3AdminAccInfoMobileOfficeRegistInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mobileOfficeRegistInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082109L;

    /**
     * (口座タイプ)<BR>
     * 口座タイプ<BR>
     * <BR>
     * 0：その他<BR>
     * 1：個人アカウント<BR>
     * 2：共用アカウント<BR>
     * 3：法@人アカウント<BR>
     */
    public String accountType;
    
    /**
     * (申込状況区分)<BR>
     * 申込状況区分<BR>
     * <BR>
     * 0：　@判定待ち<BR>
     * 1：　@判定待ち（確認中）<BR>
     * 2：　@判定済み<BR>
     */
    public String applyStateDiv;

    /**
     * (変更後情報)<BR>
     * 変更後情報<BR>
     */    
    public WEB3AccInfoMobileOfficeInfo changedMobileOfficeInfo;
    
    /**
     * (変更前情報)<BR>
     * 変更前情報<BR>
     */
    public WEB3AccInfoMobileOfficeInfo preMobileOfficeInfo;
    
    /**
     * @@roseuid 418F385902DE
     */
    public WEB3AdminAccInfoMobileOfficeRegistInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoMobileOfficeRegistInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
