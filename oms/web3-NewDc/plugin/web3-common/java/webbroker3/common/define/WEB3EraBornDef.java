head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.42.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EraBornDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 年号（生年月日）定数定義クラス(WEB3EraBornDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 年号（生年月日）定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3EraBornDef
{
    /**
     * 明治
     */
    public static final String MEIJI = "1";
    
    /**
     * 大正
     */
    public static final String TAISYO = "2";
    
    /**
     * 昭和
     */
    public static final String SYOWA = "3";
    
    /**
     * 平成
     */
    public static final String HEISEI = "4";
    
    /**
     * 不明
     */
    public static final String UNKNOWN = "9";
}@
