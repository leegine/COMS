head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3EqTypeQuoteData.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (株)大和総研 証券ソリューションシステム第二部
 * File Name        : 株式の時価情報クラス(WEB3EquityQuoteData.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeQuoteData;

/**
 * <p>
 * 株式の時価情報<br>
 * 時価サービスによって提供される株式の時価情報のインターフェース
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3EqTypeQuoteData
    extends EqTypeQuoteData, WEB3QuoteData
{

}
@
