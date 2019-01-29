head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ハッシュ認証ログインレスポンスクラス(WEB3HashAuthenticationAcceptLoginResponse.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/06/20 栄イ(中訊)
*/

package webbroker3.login.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ServiceImpState;

/**
 * (ハッシュ認証ログインレスポンス)<BR>
 * ハッシュ認証ログインレスポンスクラス<BR>
 * <BR> 
 * @@author      栄イ(中訊)
 * @@version     1.00
 */
public class WEB3HashAuthenticationAcceptLoginResponse extends WEB3GenResponse
{
	/**
	 * TAGNAME
	 */
	public static final String TAGNAME = "response";

	/**
	 * PTYPE
	 */
	public static final String PTYPE = "web3_hash_auth_login";

	/**
	 * SerialVersionUID
	 */
	public final static long serialVersionUID = 200602201800L;

	/**
	 * (xTradeセッションID)<BR>
	 */
	public String xTradeSessionID;

	/**
	 * (サービス実施状態)<BR>
	 */
	public WEB3ServiceImpState serviceImpState;

	/**
	 * (会社情報)<BR>
	 */
	public WEB3InstitutionInfo institutionInfo;

	/**
	 * (顧客情報)<BR>
	 */
	public WEB3AcceptInfo acceptInfo;

	/**
	 * (先頭画面ID)<BR>
	 * ユーザ指定の先頭画面を表すID<BR>
	 */
	public String topPageID;

	/**
	 * (部店情報)<BR>
	 */
	public WEB3BranchInfo branchInfo;

	/**
	 * デフォルトコンストラクタ。<BR>
	 * @@roseuid 403EF12C017D
	 */
	public WEB3HashAuthenticationAcceptLoginResponse()
	{

	}

	/**
	 * コンストラクタ。<BR>
	 * 引数で与えられたリクエストオブジェクトを基にレスポンスオブジェクトを生成する。<BR>
	 * @@param l_request
	 * @@roseuid 403EF19C015E
	 */
	public WEB3HashAuthenticationAcceptLoginResponse(WEB3GenRequest l_request)
	{
		super(l_request);
	}
}
@
