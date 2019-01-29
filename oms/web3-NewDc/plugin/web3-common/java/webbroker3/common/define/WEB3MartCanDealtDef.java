head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.56.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MartCanDealtDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取扱可否 定数定義インタフェイス(WEB3MartCanDealtDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/12 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 取扱可否　@定数定義インタフェイス
 *
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3MartCanDealtDef
{

    /**
     * 0 : 取扱不可
     */
    public static final String DEAL_DISABLED = "0";

    /**
     * 1 : 取扱可能
     */
    public static final String DEAL_ENABLE = "1";
}
@
