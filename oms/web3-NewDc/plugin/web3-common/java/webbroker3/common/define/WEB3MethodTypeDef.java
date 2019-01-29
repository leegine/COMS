head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.40.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MethodTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MethodTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/28 li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 仕法@区分　@定数定義インタフェイス
 *                                                                      
 * @@author li-yingyuan
 * @@version 1.0
 */
public interface WEB3MethodTypeDef
{
    /**
     * 00：本券なし・(優)なし　@
     */
    public static final String NO_ISSUE_TICKET_NO_PREFERENTIAL_SECURITIES = "00";

    /**
     * 01：本券なし・(優)あり 
     */
    public static final String NO_ISSUE_TICKET_PREFERENTIAL_SECURITIES = "01";
    
    /**
     * 10：本券あり・(優)なし　@
     */
    public static final String ISSUE_TICKET_NO_PREFERENTIAL_SECURITIES = "10";
    
    /**
     * 11：本券あり・(優)あり
     */
    public static final String ISSUE_TICKET_PREFERENTIAL_SECURITIES = "11";

}
@
