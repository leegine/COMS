head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginClosingStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式決済状態区分(WEB3MarginClosingStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/25 呉艶飛(中訊) 新規作成
*/
package webbroker3.equity.define;

/**
 * 決済状態区分
 *                                                                     
 * @@author wu-yanfei
 * @@version 1.0
 */
public interface WEB3MarginClosingStatusTypeDef
{
    /**
     * 0：決済済
     */
    public static final String SETTLEMENT_END = "0";
    
    /**
     * 1：未決済
     */
    public static final String HAVE_NOT_SETTLED = "1";

    /**
     * 2：決済中
     */
    public static final String SETTLING = "2";
}
@
