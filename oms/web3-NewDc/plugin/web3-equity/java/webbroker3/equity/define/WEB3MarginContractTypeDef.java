head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginContractTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3MarginContractTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/29  wu-yanfei(sinocom)　@新規作成
*/
package webbroker3.equity.define;

/**
 * 建区分
 *                                                                     
 * @@author wu-yanfei
 * @@version 1.0
 */
public interface WEB3MarginContractTypeDef
{
    /**
     * 1：買建
     */
    public static final String OPEN_BUY = "1";

    /**
     * 2：売建
     */
    public static final String OPEN_SELL = "2";
}
@
