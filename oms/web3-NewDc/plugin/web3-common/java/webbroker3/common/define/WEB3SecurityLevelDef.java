head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.02.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SecurityLevelDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : セキュリティ・レベル定義クラス(WEB3SecurityLevelDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/04/26 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * セキュリティ・レベル定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3SecurityLevelDef
{
    /**
     * 低
     */
    public static final String LOW = "10";
    
    /**
     * やや低
     */
    public static final String LITTLE_LOW = "20";
    
    /**
     * 普通
     */
    public static final String MIDDLE = "30";
    
    /**
     * やや高
     */
    public static final String LITTLE_HIGH = "40";
    
    /**
     * 高
     */
    public static final String HIGH = "50";
}@
