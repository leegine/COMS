head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3IfoQuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物・オプションの時価情報クラス(WEB3IfoQuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/10 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoQuoteData;

/**
 * <p>
 * 株価指数先物・オプションの時価情報<br>
 * 時価サービスによって提供される株価指数先物・オプションの時価情報のインターフェース
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoQuoteData extends IfoQuoteData, WEB3QuoteData
{

}
@
