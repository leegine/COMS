head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderTypeDivisionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文状況区分  定数定義インタフェイス(WEB3EquityOrderTypeDivisionDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.equity.define;

/**
 * 注文状況区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3EquityOrderTypeDivisionDef
{

    /**
     * 0：注文中 
     */
    public static final String ORDRING = "0";

    /**
     *  1：約定済み 
     */
    public static final String EXECUTED = "1";
    
    /**
     *  2：執行済 
     */
    public static final String ORDERED = "2";
    
    /**
     *  3：取消済
     */
    public static final String CANCELED = "3";
    
    /**
     *  4：指定なし
     */
    public static final String DESIGNATE = "4";
     
}
@
