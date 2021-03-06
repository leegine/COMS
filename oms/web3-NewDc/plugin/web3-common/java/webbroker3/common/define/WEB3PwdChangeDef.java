head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.58.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3PwdChangeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : パスワード変更有無フラグ定数定義クラス(WEB3PwdChangeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * パスワード変更有無フラグ定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3PwdChangeDef
{
    /**
     * 変更不要
     */
    public static final String UNNECESSARY = "0";
    
    /**
     * 変更必要
     */
    public static final String REQUIRED = "1";
}@
