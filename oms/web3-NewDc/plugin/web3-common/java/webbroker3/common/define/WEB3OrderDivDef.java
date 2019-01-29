head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.44.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金区分  定数定義インタフェイス(WEB3OrderDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/22　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 入出金区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3OrderDivDef
{

    /**
     *  1：出金  
     */
    public static final String CASHOUT = "1";

    /**
     *  2：入金
     */
    public static final String CASHIN = "2";
}
@
