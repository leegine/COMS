head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenApplyInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設申込入力レスポンス(WEB3AccOpenApplyInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
Revesion History : 2009/08/10 張騰宇(中訊) 仕様変更 モデル162
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (口座開設申込入力レスポンス)<BR>
 * 口座開設申込入力レスポンス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenApplyInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_applyInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081617L;

    /**
     * (姓)<BR>
     * 顧客姓（漢字）<BR>
     */
    public String accountFamilyName;

    /**
     * (名)<BR>
     * 顧客名（漢字）<BR>
     */
    public String accountName;

    /**
     * (メールアドレス)<BR>
     * メールアドレス<BR>
     */
    public String mailAddress;

    /**
     * (仲介業扱者コード)<BR>
     * 仲介業扱者コード<BR>
     */
    public String brokerageCode;

    /**
     * (口座区分)<BR>
     * 口座区分 <BR>
     * <BR>
     * 0：個人アカウント <BR>
     * 1：法@人アカウント<BR>
     */
    public String accountType;

    /**
     * (リンク元判別コード)<BR>
     * リンク元判別コード<BR>
     */
    public String linkCode;

    /**
     * @@roseuid 41B45E7C0128
     */
    public WEB3AccOpenApplyInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccOpenApplyInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
