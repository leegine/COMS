head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3IndexQuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 各種指数の時価情報クラス(WEB3IndexQuoteData.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/14 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData;

/**
 * 各種指数の時価情報<br>
 * 時価サービスによって提供される各種指数の時価情報を表すインターフェース
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IndexQuoteData extends QuoteData, WEB3QuoteData
{
    
    /**
     * 指数の種類を取得する。
     * 
     * @@return 指数の種類
     */
    public IndexType getIndexType();

}
@
