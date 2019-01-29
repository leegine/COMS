head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.59.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SexDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 性別定数定義クラス(WEB3SexDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/10 菊地(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 性別定数定義クラス
 *
 * @@author 菊地(SRA)
 * @@version 1.0
 */
public interface WEB3SexDef
{
    /**
     * 法@人
     */
    public static final String CORPORATE = "0";
    
    /**
     * 男性
     */
    public static final String MALE = "1";
    
    /**
     * 女性
     */
    public static final String WOMAN = "2";
    
    /**
     * 不明
     */
    public static final String UNKNOW = "9";
}@
