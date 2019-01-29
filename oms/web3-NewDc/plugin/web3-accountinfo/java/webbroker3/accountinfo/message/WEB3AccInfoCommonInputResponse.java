head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommonInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報共通入力レスポンス(WEB3AccInfoCommonInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報共通入力レスポンス)<BR>
 * お客様情報共通入力レスポンス
 * <BR>
 * ※以下の画面で共通に使用する。<BR>
 * 　@－メールアドレス変更入力処理<BR>
 * 　@－案内メール配信設定入力／確認処理<BR>
 * 　@－約定／未約定メール配信設定変更入力／確認処理<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoCommonInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_commonInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082163L;

    /**
     * @@roseuid 418F39EF036B
     */
    public WEB3AccInfoCommonInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccInfoCommonInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
