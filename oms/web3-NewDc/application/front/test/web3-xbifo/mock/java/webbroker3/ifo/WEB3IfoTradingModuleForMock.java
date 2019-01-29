head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoTradingModuleForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張取引モジュールラスForMock(WEB3IfoTradingModuleForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/02 徐宏偉 (中訊) 新規作成
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketAdapterImplForMock;

/**
 * 拡張取引モジュールラスForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3IfoTradingModuleForMock extends WEB3IfoTradingModule
{
    /**
     * コンストラクタ。<BR>
     * トランザクションマネージャにWEB3IfoFinTransactionManagerImplForMarkを<BR>
     * 設定する。<BR>
     */
    public WEB3IfoTradingModuleForMock()
    {
        super();
        super.m_marketAdapter = new IfoMarketAdapterImplForMock();
        super.m_finTranManager = new WEB3IfoFinTransactionManagerImplForMock();
    }
}
@
