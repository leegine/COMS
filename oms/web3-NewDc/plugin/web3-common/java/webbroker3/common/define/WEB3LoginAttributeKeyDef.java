head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginAttributeKeyDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログイン属性キー定数定義クラス(WEB3LoginAttributeKeyDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/26 菊地(SRA) 新規作成
Revesion History : 2007/06/13 栄イ(中訊) ログイン仕様変更管理台帳・ＤＢレイアウトNo014
Revesion History : 2007/06/29 栄イ(中訊) ログイン仕様変更管理台帳・ＤＢレイアウトNo016
*/
package webbroker3.common.define;

/**
 * ログイン属性キー定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3LoginAttributeKeyDef
{
    /**
     * 復号化可能初期パスワード
     */
    public static final String INITIAL_PASSWORD = "WEB3_ENCRYPTED_INITIAL_PASSWORD";
    
    /**
     * 復号化可能パスワード
     */
    public static final String PASSWORD = "WEB3_ENCRYPTED_PASSWORD";
    
    /**
     * １世代前パスワード
     */
    public static final String PASSWORD_OLD1 = "PASSWORD_OLD1";
    
    /**
     * ２世代前パスワード
     */
    public static final String PASSWORD_OLD2 = "PASSWORD_OLD2";
    
    /**
     * ３世代前パスワード
     */
    public static final String PASSWORD_OLD3 = "PASSWORD_OLD3";
    
    /**
     * １世代前取引パスワード
     */
    public static final String TRADING_PASSWORD_OLD1 = "TRADING_PASSWORD_OLD1";
    
    /**
     * ２世代前取引パスワード
     */
    public static final String TRADING_PASSWORD_OLD2 = "TRADING_PASSWORD_OLD2";
    
    /**
     * ３世代前取引パスワード
     */
    public static final String TRADING_PASSWORD_OLD3 = "TRADING_PASSWORD_OLD3";
    
    /**
     * 最終ログイン時刻
     */
    public static final String LAST_LOGIN = "LAST_LOGIN_TIME";
    
    /**
     * 前回パスワード更新者コード
     */
    public static final String LAST_PASSWORDCHANGE_UPDATER = "LAST_PASSWORDCHANGE_UPDATER";
    
    /**
     * 前回パスワード変更日付
     */
    public static final String LAST_PWDCHANGE = "LAST_PASSWORDCHANGE_DATE";

    /**
     * 復号化可能お客様識別番号
     */
    public static final String WEB3_ENCRYPTED_DISCRIMINATION_CD = "WEB3_ENCRYPTED_DISCRIMINATION_CD";
}@
