head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.59.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistributionChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報案内メール配信設定変更完了リクエスト(WEB3AccInfoMailDistributionChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
*/
package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報案内メール配信設定変更完了リクエスト)<BR>
 * お客様情報案内メール配信設定変更完了リクエスト<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoMailDistributionChangeCompleteRequest extends WEB3GenRequest
{
    /**         
     * ログ出力ユーティリティ。         
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailDistributionChangeCompleteRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mailDistributionChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082157L;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 418F39F40157
     */
    public WEB3AccInfoMailDistributionChangeCompleteRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoMailDistributionChangeCompleteResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 413C1F9F0211
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
