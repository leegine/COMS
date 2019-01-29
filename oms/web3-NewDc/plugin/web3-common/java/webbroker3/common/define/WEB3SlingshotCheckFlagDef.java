head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.30.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SlingshotCheckFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : スリングショット利用可能チェック有無フラグ定義クラス(WEB3SlingshotCheckFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/14 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * スリングショット利用可能チェック有無フラグ定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3SlingshotCheckFlagDef
{
    /**
     * チェックなし
     */
    public static final String DISABLED = "0";
    
    /**
     * チェックあり
     */
    public static final String ENABLED = "1";
}
@
