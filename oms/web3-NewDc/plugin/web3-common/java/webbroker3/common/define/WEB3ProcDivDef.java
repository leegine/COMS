head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ProcDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理区分(WEB3ProcDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/21 郭英(sinocom) 新規作成
*/
package webbroker3.common.define;

/**
 * 処理区分
 *
 * @@author 郭英
 * @@version 1.0
 */
public interface WEB3ProcDivDef
{
    /**
     * 0:    未処理 
     */
    public final static String NOT_PROCESSED = "0";

    /**
     * 1:    計算処理済み　@
     */
    public final static String PROCESSED = "1";
}
@
