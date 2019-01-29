head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.48.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3TaxTypeSpecialDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 税区分(特定口座区分)　@定数定義インタフェイス(WEB3TaxTypeSpecialDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 本郷　@千草(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 税区分(特定口座区分)　@定数定義インタフェイス
 *
 * @@author 本郷千草(SRA)
 * @@version 1.0
 */
public interface WEB3TaxTypeSpecialDef
{
    /**
     * 一般
     */
    public static final String NORMAL = "0";

    /**
     * 特定口座
     */
    public static final String SPECIAL = "1";
    
}
@
