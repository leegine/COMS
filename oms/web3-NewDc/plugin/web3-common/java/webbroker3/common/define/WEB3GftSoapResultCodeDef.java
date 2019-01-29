head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GftSoapResultCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付結果コード（SOAP）定数定義インタフェイス(WEB3GftSoapResultCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/05/07 佐藤(SCS) 新規作成
Revesion History : 2008/05/23 三島(SCS) 
Revesion History : 2009/10/14 張騰宇 (中訊) 仕様変更・モデル1238
*/

package webbroker3.common.define;

/**
 * 受付結果コード（SOAP）定数定義インタフェイス
 *
 * @@author 佐藤(SCS)
 * @@version 1.0
 */
public interface WEB3GftSoapResultCodeDef                
{
	/**
     *  0: No Error Code
     *  エラーコードなし
     */
    public static final String  NO_ERROR_CODE = "0";
	
	/**
     *  100: Done
     *  正常終了
     */
    public static final String  NORMAL = "100";
    
    /**
     *  101: In progress
     *  処理中
     */
    public static final String  IN_PROGRESS = "101";
    
    /**
     *  200: Missed parameter
     *  パラメータ不正
     */
    public static final String  MISSED_PARAMETER = "200";
    
    /**
     *  200 に対応する受付結果詳細コード
     *  2001: Accounts
     */
    public static final String  MISSED_PARAMETER_ACCOUNTS = "2001";
    
    /**
     *  200 に対応する受付結果詳細コード
     *  2002: MRP profile associated with label
     */
    public static final String  MISSED_PARAMETER_MRP_PROFILE = "2002";
    
    /**
     *  200 に対応する受付結果詳細コード
     *  2003: Base currency
     */
    public static final String  MISSED_PARAMETER_BASE_CURRENCY = "2003";
    
    /**
     *  200 に対応する受付結果詳細コード
     *  2004: Lot size
     */
    public static final String  MISSED_PARAMETER_LOT_SIZE = "2004";
    
    /**
     *  200 に対応する受付結果詳細コード
     *  2005: Groups
     */
    public static final String  MISSED_PARAMETER_GROUPS = "2005";
    
    /**
     *  200 に対応する受付結果詳細コード
     *  2006: Login
     */
    public static final String  MISSED_PARAMETER_LOGIN = "2006";
    
    /**
     *  200 に対応する受付結果詳細コード
     *  2007: Password
     */
    public static final String  MISSED_PARAMETER_PASSWORD = "2007";
    
    /**
     *  201: Wrong parameter value
     *  パラメータ不正
     */
    public static final String  WRONG_PARAMETER_VALUE = "201";
    
    /**
     *  201 に対応する受付結果詳細コード
     *  2011: There is no such account template: <template id>
     */
    public static final String  WRONG_PARAMETER_ACCOUNT_TEMPLATE = "2011";
    
    /**
     *  201 に対応する受付結果詳細コード
     *  2012: There is no such user template: <template id>
     */
    public static final String  WRONG_PARAMETER_USER = "2012";
    
    /**
     *  201 に対応する受付結果詳細コード
     *  2013: Amount is too small: <amount>
     */
    public static final String  AMOUNT_IS_TOO_SMALL = "2013";
    
    /**
     *  201 に対応する受付結果詳細コード
     *  2014: Amount is more than the real withdrawable cash: amount = <amount>, cash = <cash>
     */
    public static final String  LACK_OF_BALANCE = "2014";
    
    /**
     *  201 に対応する受付結果詳細コード
     *  2015: Duplicate login
     */
    public static final String  DUPLICATE_LOGIN = "2015";
    
    /**
     *  202: Restriction violation
     *  規約違反（ユーザ起因エラー）
     */
    public static final String  RESTRICTION_VIORATION = "202";
    
    /**
     *  202 に対応する受付結果詳細コード
     *  2021: Trade permissions: <business rule description>
     */
    public static final String  VIORATE_TRADE_PERMISSIONS = "2021";
    
    /**
     *  202 に対応する受付結果詳細コード
     *  2022: User must be included at least in one group
     */
    public static final String  NOT_INCLUDED_IN_ONE_GROUP = "2022";
    
    /** 
     *  250: Mandatory parameter is missed
     *  必須項目未入力
     */
    public static final String  MANDATORY_PARAM_MISSED_ERROR = "250";
    
    /**
     *  251: Duplicate command registration <command id>
     *  二重処理エラー
     */
    public static final String  DUPLICATE_COMMAND_ERROR = "251";
    
    /**
     *  252: Entity does not exist
     *  証拠金口座が存在しない
     */
    public static final String  NO_ENTITY_ERROR = "252";
    
    /**
     *  252 に対応する受付結果詳細コード
     *  2521: User with login: <login>
     */
    public static final String  USER_ERROR_OF_ENTITY = "2521";
    
    /**
     *  252 に対応する受付結果詳細コード
     *  2522: Group: <group>
     */
    public static final String  GROUP_ERROR_OF_ENTITY = "2522";
    
    /**
     *  252 に対応する受付結果詳細コード
     *  2523: Account: <account id>
     */
    public static final String  ACCOUNT_ERROR_OF_ENTITY = "2523";
    
    /**
     *  252 に対応する受付結果詳細コード
     *  2524: Currency: <currency>
     */
    public static final String  CURRENCY_ERROR_OF_ENTITY = "2524";
    
    /**
     *  253: Restriction violation
     *  規約違反
     */
    public static final String  RESTRICTION_ERROR = "253";
    
    /**
     *  253 に対応する受付結果詳細コード
     *  2531: Command of this type is forbidden
     */
    public static final String  FORBIDDEN_COMMAND = "2531";
    
    /**
     *  253 に対応する受付結果詳細コード
     *  2532: Disabling functionality is forbidden
     */
    public static final String  FORBIDDEN_DISABLING = "2532";
    
    /**
     *  300: Internal service error. Please, contact support.
     *  ホストシステムに起因するエラー
     */
    public static final String  INTERNAL_SERVICE_ERROR = "300";
    
    /**
     *  350: Internal service error. Please, contact support.
     *  ホストシステムに起因するエラー
     */
    public static final String  SERVICE_ERROR = "350";
    
    /**
     *  9991: 接続エラー（システムエラー）
     */
    public static final String  CONNECT_ERROR = "9991";
    
    /**
     *  9992: 接続タイムアウトエラー（システムエラー）
     */
    public static final String  CONNECT_TIME_OUT_ERROR = "9992";
    
    /**
     *  9993: GFTインターナルエラー（システムエラー）
     */
    public static final String  GFT_INTERNAL_ERROR = "9993";
    
    /**
     *  9994: ハッシュ認証エラー
     */
    public static final String  HASH_AUTH_ERROR = "9994";

    /**
     *  9995: SOAP接続確認エラー
     */
    public static final String  SOAP_CONN_CONFIRM_ERROR = "9995";
}
@
