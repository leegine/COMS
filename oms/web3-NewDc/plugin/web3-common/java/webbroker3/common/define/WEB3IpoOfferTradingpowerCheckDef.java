head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3IpoOfferTradingpowerCheckDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO購入申込余力チェック定数定義インタフェイス(WEB3IpoOfferTradingpowerCheckDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/20 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * IPO購入申込余力チェック 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3IpoOfferTradingpowerCheckDef
{
    /**
     * 0：チェックしない
     */
    public final static String DEFAULT = "0";

    /**
     * 1：チェックする
     */
    public final static String CHECK = "1";
}
@
