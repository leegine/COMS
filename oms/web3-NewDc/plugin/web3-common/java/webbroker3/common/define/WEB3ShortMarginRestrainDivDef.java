head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.46.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ShortMarginRestrainDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 空売り規制実施形態区分  定数定義インタフェイス(WEB3ShortMarginRestrainDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07　@中尾寿彦 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 空売り規制実施形態区分 定数定義インタフェイス<BR>
 * <BR>
 * @@author 中尾寿彦(SRA)
 * @@version 1.0
 */
public interface WEB3ShortMarginRestrainDivDef
{

    /**
     *  0：注文不可<BR>
     */
    public static final String DISABLE_ORDER = "0";

    /**
     *  1：注文可<BR>
     */
    public static final String ENABLE_ORDER = "1";
}
@
