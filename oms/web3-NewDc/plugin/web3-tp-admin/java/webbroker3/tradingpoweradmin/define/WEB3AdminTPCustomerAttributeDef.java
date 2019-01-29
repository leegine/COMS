head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.37.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPCustomerAttributeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客属性定義インターフェース(WEB3AdminTPCustomerAttributeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/03/04 asano(SCS) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPCustomerAttributeDefインターフェース。
 * 顧客属性を定義する。
 *
 */
public interface WEB3AdminTPCustomerAttributeDef 
{

    /**
     * 0: 現物顧客
     */
    public static final String EQUITY_CUST = "0";

    /**
     * 1: 信用顧客
     */
    public static final String MARGIN_CUST = "1";

}
@
