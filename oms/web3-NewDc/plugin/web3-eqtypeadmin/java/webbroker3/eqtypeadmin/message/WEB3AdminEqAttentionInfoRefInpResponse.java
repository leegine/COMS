head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEqAttentionInfoRefInpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・注意情報照会入力レスポンス(WEB3AdminEqAttentionInfoRefInpResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/30 孟亞南 (中訊) 新規作成 モデルNo.217
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (管理者・注意情報照会入力レスポンス)<BR>
 * 管理者・注意情報照会入力レスポンスクラス<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminEqAttentionInfoRefInpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_eq_attention_info_ref_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812301404L;

    /**
     * (情報発生日時From)<BR>
     * 情報発生日時From<BR>
     */
    public String infoOccuredDateFrom;

    /**
     * @@roseuid 49588AF100CB
     */
    public WEB3AdminEqAttentionInfoRefInpResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminEqAttentionInfoRefInpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
