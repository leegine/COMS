head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.54.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketMakeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : マーケットメイカ　@定数定義インタフェイス(WEB3MarketMakeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/09 和田　@友一(SRA) 新規作成
*/
package webbroker3.common.define;

/**
 * マーケットメイカ　@定数定義インタフェイス。
 *
 * @@author 和田　@友一(SRA)
 * @@version 1.0
 */
public interface WEB3MarketMakeDef
{
    /**
     * マーケットメイカでない
     */
    public static final long NOT_MARKET_MAKER = 0;
    
    /**
     * マーケットメイカである
     */
    public static final long MARKET_MAKER = 1;
}
@
