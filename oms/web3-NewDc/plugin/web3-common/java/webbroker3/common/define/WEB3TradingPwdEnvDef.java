head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.27.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TradingPwdEnvDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引パスワード設定 定数定義インタフェイス(WEB3TradingPwdEnvDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 張麗維 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * ログインタイプ属性.取引パスワード設定 定数定義インタフェイス
 *
 * @@author 張麗維
 * @@version 1.0
 */
public interface WEB3TradingPwdEnvDef
{
    /**
      * 0：DEFAULT（取引パスワード項目を使用しない）
      */
    public static final String DEFAULT = "0";
    
    /**
      * 1：取引パスワード使用
      */
    public static final String USE_TRADING_PWD = "1";

}
@
