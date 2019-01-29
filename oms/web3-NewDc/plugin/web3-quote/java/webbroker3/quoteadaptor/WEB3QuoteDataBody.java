head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3QuoteDataBody.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : 時価情報インターフェース(WEB3QuoteDataBody.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 山田　@卓司(FLJ) 新規作成
 *                  : 2005/03/30 山田　@卓司(FLJ) メソッド定義をWEB3QuoteDataに移動
 */
package webbroker3.quoteadaptor;

/**
 * WebBroker3の時価サービスが提供する時価情報のインターフェース<br>
 * <br>
 * 時価サービスによって提供される時価情報を実装する場合は、このインターフェースと、
 * xBlocksで定義された時価情報のインターフェースを実装する必要がある。
 * 
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData,
 *      com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData,
 *      com.fitechlabs.xtrade.plugin.tc.xbbd.BondQuoteData,
 *      com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundQuoteData
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 * @@deprecated 
 */
public interface WEB3QuoteDataBody
{
    
}
@
