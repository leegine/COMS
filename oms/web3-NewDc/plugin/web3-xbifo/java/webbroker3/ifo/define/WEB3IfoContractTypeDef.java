head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoContractTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AcceptStatusDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10  li-yingyuan(sinocom)　@新規作成
*/
package webbroker3.ifo.define;

/**
 * 建区分
 *                                                                     
 * @@author li yingyuan
 * @@version 1.0
 */
public interface WEB3IfoContractTypeDef
{
    /**
     * 1：買建
     */
    public static final String OPEN_BUY = "1";

    /**
     * 2：売建
     */
    public static final String OPEN_SELL = "2";
    
    /**
     * 1：買付
     */
    public static final String BUY = "01";
    
    /**
     * 2：売付
     */
    public static final String Sell = "02";

}

@
