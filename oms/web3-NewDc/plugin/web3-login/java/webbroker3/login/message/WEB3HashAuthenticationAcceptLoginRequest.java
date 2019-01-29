head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.26.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3HashAuthenticationAcceptLoginRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : ハッシュ認証ログインリクエストクラス(WEB3HashAuthenticationAcceptLoginRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2006/06/20 栄イ(中訊)
*/

package webbroker3.login.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (ハッシュ認証ログインリクエスト)<BR>
 * ハッシュ認証ログインリクエストクラス<BR>
 *<BR> 
 * @@author      栄イ(中訊)
 * @@version     1.00
 */
public class WEB3HashAuthenticationAcceptLoginRequest extends WEB3GenRequest 
{
	/**
	 * TAGNAME<BR>
	 */
	public static final String TAGNAME = "request";

	/**
	 * PTYPE<BR>
	 */
	public static final String PTYPE   = "web3_hash_auth_login";

	/**
	 * SerialVersionUID<BR>
	 */
	public final static long serialVersionUID = 200606201800L;

	/**
	 * (会社コード)<BR>
	 */
	public String institutionCode;

	/**
	 * (部店コード)<BR>
	 */
	public String branchCode;

	/**
	 * (顧客コード)<BR>
	 * 顧客コード（ログイン時の口座番号）<BR>
	 */
	public String acceptCode;

	/**
	 * (xTradeユーザ名)<BR>
	 * 入力された顧客コードをxTradeログインユーザ名に変換した値<BR>
	 */
	public String xTradeUsername;

	/**
	 * (注文チャネル)<BR>
	 * 注文チャネル<BR>
	 */
	public String orderChannel;

	/**
	 * (注文経路区分)<BR>
	 * 注文経路区分<BR>
	 */
	public String orderRootDiv;

	/**
	 * (顧客ID)<BR>
	 * Login時のaccountID<BR>
	 */
	public String account_id;

	/**
	 * (IPアドレス)<BR>
	 */
	public String ipAddress;

	/**
	 * (認証用ハッシュ値)<BR>
	 * (SHA1コード):  40桁の文字・数字列<BR>
	 */
	public String hstr;

	/**
	 * (GUID)<BR>
	 * 唯一の標識の文字列　@　@　@32桁の英数字<BR>
	 */
	public String guid;

	/**
	 * (作成日時)<BR>
	 * (西暦日時分秒):  フォーマット：YYYYMMDDHHNNSS<BR>
	 */
	public Date createDate;

	/**
	 * デフォルトコンストラクタ。<BR>
	 * @@roseuid 403EF0E80267
	 */
	public WEB3HashAuthenticationAcceptLoginRequest() 
	{

	}

	/**
	 * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
	 * <BR>
	 * @@return レスポンスオブジェクト
	 * @@roseuid 403EF0440277
	 */
	public WEB3GenResponse createResponse()
	{
		return new WEB3HashAuthenticationAcceptLoginResponse(this);
	}
}
@
