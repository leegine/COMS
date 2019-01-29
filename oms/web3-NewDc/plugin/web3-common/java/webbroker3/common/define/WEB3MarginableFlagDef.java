head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.37.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginableFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 買信用可否フラグ 定数定義インタフェイス(WEB3MarginableFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 買信用可否フラグ　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3MarginableFlagDef
{

    /**
     *  0 : FALSE
     */
    public static final String FALSE = "0";

    /**
     *  1 : TRUE
     */
    public static final String TRUE = "1";

}

@
