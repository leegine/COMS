head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3QualifiedInstInvestorDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 適格機@関投資家区分 定数定義インタフェイス(WEB3QualifiedInstInvestorDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/13　@彭巍 (SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * 適格機@関投資家区分　@定数定義インタフェイス
 *
 * @@author 彭巍(SRA)
 * @@version 1.0
 */
public interface WEB3QualifiedInstInvestorDivDef
{

    /**
     *  0 ： 適格機@関投資家でない　@
     */
    public static final String NO_QUALIFIED_INSTITUTIONAL_INVESTOR = "0";

    /**
     *  1 ： 適格機@関投資家である
     */
    public static final String QUALIFIED_INSTITUTIONAL_INVESTOR = "1";
                          
}
@
