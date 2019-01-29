head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiMarginTaxDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用口座区分(WEB3SrvRegiMarginTaxDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/18 李頴淵(sinocom) 新規作成
*/
package webbroker3.srvregi.define;

/**
 * 信用口座区分
 *
 * @@author 李頴淵
 * @@version 1.0
 */
public interface WEB3SrvRegiMarginTaxDivDef
{
    /**
     * 0：信用口座未開設 　@
     */
    public final static String NOT_OPEN = "0";

    /**
     * 1：開設済み　@
     */
    public final static String OPEN = "1";
}
@
