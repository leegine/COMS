head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.06.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ApplyAcceptDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 申込受付区分(WEB3ApplyAcceptDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/21 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 申込受付区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3ApplyAcceptDivDef
{
    /**
     * 0: 申込み時 
     */
    public final static String APPLYING = "0";

    /**
     * 1: 証券会社受付済み　@　@
     */
    public final static String INSTITUTION_ACCEPTED = "1";
}@
