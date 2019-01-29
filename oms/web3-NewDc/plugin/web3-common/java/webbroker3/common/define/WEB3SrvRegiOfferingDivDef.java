head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.26.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SrvRegiOfferingDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込区分(WEB3SrvRegiOfferingDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * サービスマスターテーブル.申込区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3SrvRegiOfferingDivDef
{

    /**
     * 0：不要　@　@
     */
    public final static String NOT_REQUIRE = "0";

    /**
     * 1：要　@
     */
    public final static String REQUIRE = "1";
    
}@
