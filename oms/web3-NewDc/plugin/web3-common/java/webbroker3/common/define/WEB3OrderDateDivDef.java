head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderDateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受注日区分　@定数定義インタフェイス(WEB3OrderDateDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 本郷　@千草(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 受注日区分　@定数定義インタフェイス
 *
 * @@author 本郷千草(SRA)
 * @@version 1.0
 */
public interface WEB3OrderDateDivDef
{
    /**
     * 当日
     */
    public static final String TODAY = "0";
    
    /**
     * 前日
     */
    public static final String YESTERDAY = "1";

}
@
