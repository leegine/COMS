head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.41.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioTradingModuleForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュールForMock(WEB3AioTradingModuleForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/29 齊珂 (中訊) 新規作成
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.xbaio.stdimpls.AioMarketAdapterImplForMock;

/**
 * 拡張取引モジュールForMock
 *
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AioTradingModuleForMock extends WEB3AioTradingModule
{
    /**
     * コンストラクタ。<BR>
     * トランザクションマネージャにWEB3IfoFinTransactionManagerImplForMarkを<BR>
     * 設定する。<BR>
     */
    public WEB3AioTradingModuleForMock()
    {
        super();
        super.m_marketAdapter = new AioMarketAdapterImplForMock();
    }
}
@
