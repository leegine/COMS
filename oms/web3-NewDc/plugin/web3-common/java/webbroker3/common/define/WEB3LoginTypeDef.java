head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3LoginTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログインタイプ値定義クラス(WEB3LoginTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/26 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * ログインタイプ値定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3LoginTypeDef
{
    /**
     * 通常
     */
    public static final String NORMAL = "0";
    
    /**
     * パスワード変更の為のログイン
     */
    public static final String PWDCHANGE = "1";
}@
