head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundTradedProductForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信取引銘柄ForMock(WEB3MutualFundTradedProductForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/09 徐宏偉 (中訊) 新規作成
*/
package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductRow;

import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信取引銘柄ForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundTradedProductForMock extends WEB3MutualFundTradedProduct
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradedProductForMock.class);

    /**
     * (拡張投信取引銘柄(Mock))<BR>
     * コンストラクタ。<BR>
     * @@param l_tradedProductRow - 取引銘柄Row
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3MutualFundTradedProductForMock(TradedProductRow l_tradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_tradedProductRow);
    }

    /**
     * (拡張投信取引銘柄(Mock))<BR>
     * コンストラクタ。<BR>
     * @@param l_mutualFundTradedProductRow - 投信取引銘柄Row
     * @@throws DataQueryException
     * @@throws DataNetworkException
     */
    public WEB3MutualFundTradedProductForMock(MutualFundTradedProductRow l_mutualFundTradedProductRow)
        throws DataQueryException, DataNetworkException
    {
        super(l_mutualFundTradedProductRow);
    }
}
@
