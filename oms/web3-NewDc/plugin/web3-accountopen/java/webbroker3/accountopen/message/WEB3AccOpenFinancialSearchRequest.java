head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinancialSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設金融機@関検索リクエスト(WEB3AccOpenFinancialSearchRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (口座開設金融機@関検索リクエスト)<BR>
 * 口座開設金融機@関検索リクエスト<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenFinancialSearchRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_financialSearch";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081615L;

    /**
     * (先頭文字)<BR>
     * 先頭文字<BR>
     * <BR>
     * ※ 半角カナにて指定<BR>
     * ※ 指定がない場合はすべて<BR>
     */
    public String headCharacter;

    /**
     * @@roseuid 41B45E7C034B
     */
    public WEB3AccOpenFinancialSearchRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenFinancialSearchResponse(this);
    }
}
@
