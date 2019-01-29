head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.04.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3WeekNoDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 週番号  定数定義インタフェイス(WEB3WeekNoDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 週番号　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3WeekNoDef
{

    /**
     * ０：全て 
     */
    public static final String EVERYTHING = "0";

    /**
     *  １：第１週 
     */
    public static final String THE_FIRST_WEEK = "1";
    
    /**
     *  ２：第２週 
     */
    public static final String THE_SECOND_WEEK = "2";
    
    /**
     *  ３：第３週 
     */
    public static final String THE_THIRD_WEEK = "3";
    
    /**
     *  ４：第４週
     */
    public static final String THE_FOURTH_WEEK = "4";
    
    /**
     *  ５：第５週
     */
    public static final String THE_FIFTH_WEEK = "5";
}
@
