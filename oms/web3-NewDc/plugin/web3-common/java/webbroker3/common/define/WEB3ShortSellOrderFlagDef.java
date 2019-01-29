head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ShortSellOrderFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 空売フラグ　@定数定義インタフェイス(WEB3ShortSellOrderFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/08 水落(SRA)　@新規作成
*/
package webbroker3.common.define;

/**
 * 空売フラグ　@定数定義インタフェイス
 *                                                                      
 * @@author 水落
 * @@version 1.0
 */
public interface WEB3ShortSellOrderFlagDef
{
    /**
     * ブランク : 対象外
     */
    public static final String EXCEPT_OBJECT = " ";

    /**
     * 5 : 価格規制対象
     */
    public static final String PRICE_RESTRAINT = "5";

}@
