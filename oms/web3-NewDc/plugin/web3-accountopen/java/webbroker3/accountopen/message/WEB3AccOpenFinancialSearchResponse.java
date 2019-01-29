head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinancialSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設金融機@関検索レスポンス(WEB3AccOpenFinancialSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (口座開設金融機@関検索レスポンス)<BR>
 * 口座開設金融機@関検索レスポンス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenFinancialSearchResponse extends WEB3GenResponse
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
     * (銀行名)<BR>
     * 銀行名（漢字）の配列<BR>
     */
    public String[] financialInstitutionName;

    /**
     * (銀行名（カナ）)<BR>
     * 銀行名（カナ）の配列<BR>
     */
    public String[] financialInstitutionNameKana;

    /**
     * (銀行コード)<BR>
     * 銀行コードの配列<BR>
     */
    public String[] financialInstitutionCode;

    /**
     * @@roseuid 41B45E7C03A9
     */
    public WEB3AccOpenFinancialSearchResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccOpenFinancialSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
