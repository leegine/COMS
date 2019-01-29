head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.04.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenFinancialBranchSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設支店検索レスポンス(WEB3AccOpenFinancialBranchSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 張学剛 新規作成
*/
package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (口座開設支店検索レスポンス)<BR>
 * 口座開設支店検索レスポンス<BR>
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AccOpenFinancialBranchSearchResponse extends WEB3GenResponse
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
     * (支店名)<BR>
     * 支店名（漢字）の配列<BR>
     */
    public String[] financialBranchName;

    /**
     * (支店名（カナ）)<BR>
     * 支店名（カナ）の配列<BR>
     */
    public String[] financialBranchNameKana;

    /**
     * (支店コード)<BR>
     * 支店コードの配列<BR>
     */
    public String[] financialBranchCode;

    /**
     * @@roseuid 41B45E7D003E
     */
    public WEB3AccOpenFinancialBranchSearchResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccOpenFinancialBranchSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
