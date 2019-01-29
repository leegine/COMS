head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPDepositDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 預り証券顧客区分定義クラス(WEB3TPDepositDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 asano(SCS) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 * 預り証券顧客区分定数クラス
 */
public interface WEB3TPDepositDivDef
{

    /**
     * 預り証券顧客でない
     */ 
    public static final String NOT_DEPOSIT_CUSTOMER = "0";

    /**
     * 預り証券顧客
     */    
    public static final String DEPOSIT_CUSTOMER = "1";
    
}
@
