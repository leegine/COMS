head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.32.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3WeekDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 曜日区分  定数定義インタフェイス(WEB3WeekDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/23　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 曜日区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3WeekDivDef
{

    /**
     *  ０：日曜 
     */
    public static final String SUNDAY = "0";

    /**
     *  １：月曜 
     */
    public static final String MONDAY = "1";
    
    /**
     *  ２：火曜 
     */
    public static final String TUESDAY = "2";
    
    /**
     *  ３：水曜 
     */
    public static final String WEDNESDAY = "3";
    
    /**
     *  ４：木曜 
     */
    public static final String THURSDAY = "4";
    
    /**
     *  ５：金曜 
     */
    public static final String FRIDAY = "5";
    
    /**
     *  ６：土曜
     */
    public static final String SATURDAY = "6";

     
}
@
