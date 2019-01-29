head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ReloginFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 再ログインフラグ定義クラス(WEB3ReloginFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/03/31 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 再ログインフラグ定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3ReloginFlagDef
{
    /**
     * 再ログインする。
     */
    public static final String RELOGIN = "1";
    
    /**
     * 再ログインしない（継続する）。
     */
    public static final String CONTINUE = "0";
}@
