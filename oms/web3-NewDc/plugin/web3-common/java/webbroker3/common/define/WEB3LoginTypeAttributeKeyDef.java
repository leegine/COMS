head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginTypeAttributeKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログインタイプ属性名定義クラス(WEB3LoginTypeAttributeKeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/17 菊地(SRA) 新規作成
Revesion History : 2007/06/13 栄イ(中訊) ログイン仕様変更管理台帳・ＤＢレイアウトNo013
Revesion History : 2007/06/29 栄イ(中訊) ログイン仕様変更管理台帳・ＤＢレイアウトNo015
*/
package webbroker3.common.define;

/**
 * ログインタイプ属性名定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3LoginTypeAttributeKeyDef
{
    /**
     * セキュリティ・レベル
     */
    public static final String SECURITY_LEVEL = "SECURITY_LEVEL";
    
    /**
     * 取引パスワードセキュリティ・レベル
     */
    public static final String TRADING_PWD_SECURITY_LEVEL = "TRADING_PWD_SECURITY_LEVEL";
    
    /**
     * 取引パスワード設定
     */
    public static final String TRADING_PWD_ENV = "TRADING_PWD_ENV";
    
    /**
     * パスワード最小長
     */
    public static final String PASSWORD_MIN_LENGTH = "PASSWORD_MIN_LENGTH";
    
    /**
     * パスワード最大長
     */
    public static final String PASSWORD_MAX_LENGTH = "PASSWORD_MAX_LENGTH";
    
    /**
     * パスワードチェック方式
     */
    public static final String PASSWORD_CHECK_MODE = "PASSWORD_CHECK_MODE";
    
    /**
     * ログインエラー回数
     */
    public static final String LOGIN_ERROR_MAX = "LOGIN_ERROR_MAX";
    
    /**
     * 初回パスワード変更実施フラグ
     */
    public static final String PWDCHANGE_FIRST_FLAG = "PWDCHANGE_FIRST_FLAG";
    
    /**
     * 一定期間経過後パスワード変更実施フラグ
     */
    public static final String PWDCHANGE_INTERVAL_FLAG = "PWDCHANGE_INTERVAL_FLAG";
    
    /**
     * パスワード有効期間
     */
    public static final String PASSWORD_INTERVAL = "PASSWORD_INTERVAL";
    
    /**
     * パスワード変更時再ログイン実施フラグ
     */
    public static final String PWDCHANGE_RELOGIN_FLAG = "PWDCHANGE_RELOGIN_FLAG";
    
    /**
     * 顧客成りすまし時パスワードチェック有無
     */
    public static final String SETACCOUNT_PWDCHECK_FLAG = "SETACCOUNT_PWDCHECK_FLAG";

    /**
     * お客様識別番号チェック要否
     */
    public static final String DISCRIMINATION_CD_CHECK = "DISCRIMINATION_CD_CHECK_";
}@
