head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.59.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinancialBranchSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設支店検索リクエスト(WEB3AccOpenFinancialBranchSearchRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (口座開設支店検索リクエスト)<BR>
 * 口座開設支店検索リクエスト<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenFinancialBranchSearchRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_financialBranchSearch";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081616L;

    /**
     * (銀行名)<BR>
     * 銀行名<BR>
     */
    public String financialInstitutionName;

    /**
     * (先頭文字)<BR>
     * 先頭文字<BR>
     * <BR>
     * ※ 半角カナにて指定<BR>
     * ※ 指定がない場合はすべて<BR>
     */
    public String headCharacter;

    /**
     * @@roseuid 41B45E7D00AB
     */
    public WEB3AccOpenFinancialBranchSearchRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenFinancialBranchSearchResponse(this);
    }
}
@
