head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.33.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MiniStockDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ミニ株区分 定数定義インタフェイス(WEB3MiniStockDivDef.java)
Author Name      : Daiwa Institute of Research          
Revesion History : 2004/09/06　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * ミニ株区分 @@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3MiniStockDivDef
{

    /**
     *  0 ： DEFAULT（ミニ株でない）　@
     */
    public static final String DEFAULT_MINI_STOCK = "0";

    /**
     *  1 ： ミニ株
     */
    public static final String MINI_STOCK = "1";
                          
}

@
