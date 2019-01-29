head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.46.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiOfferingDivAppendDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込区分(WEB3SrvRegiOfferingDivAppendDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 李頴淵(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * 申込区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiOfferingDivAppendDef
{

    /**
     * 0：不要　@　@
     */
    public final static String NOT_REQUIRE = "0";

    /**
     * 1：要　@
     */
    public final static String REQUIRE = "1";
    
    /**
     * 2：全て　@　@
     */
    public final static String EVERYTHING  = "2";
    
}@
