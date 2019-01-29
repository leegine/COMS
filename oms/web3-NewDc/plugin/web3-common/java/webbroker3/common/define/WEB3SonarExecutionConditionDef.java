head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.24.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SonarExecutionConditionDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : SONAR執行条件　@定数定義インタフェイス(WEB3ExecutionCondition)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 本郷　@千草(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * SONAR執行条件　@定数定義インタフェイス
 *
 * @@author 本郷千草(SRA)
 * @@version 1.0
 */
public interface WEB3SonarExecutionConditionDef
{
    /**
     * 無条件
     */
    public static final String UNCONDITIONDNESS = "1";
    
    /**
     * 出合
     */
    public static final String COME_TO_TERMS = "2";
    
    /**
     * 寄付
     */
    public static final String AT_MARKET_OPEN = "3";
    
    /**
     * 引け
     */
    public static final String AT_MARKET_CLOSE = "4";
    
    /**
     * 出来ずば引成(不成)
     */
    public static final String NO_EXECUTED_MARKET_ORDER = "7";

}
@
