head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FxMrfAccountCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : FX：MRF口座開設チェック定数定義インタフェイス(WEB3FxMrfAccountCheckDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 凌建平(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * FX：MRF口座開設チェック定数定義インタフェイス
 *
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3FxMrfAccountCheckDef
{

    /**
     * 0：チェック要  　@　@　@　@　@  　@　@
     */
    public final static String DEFAULT = "0";

    /**
     * 1：チェック不要　@
     */
    public final static String NO_CHECK = "1";  
} @
