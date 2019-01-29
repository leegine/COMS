head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipObjectCheckService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越スキップ銘柄通知繰越対象チェックサービス
                       (WEB3EquityOrderCarryOverSkipObjectCheckService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/31 鄒政 (中訊) 新規作成
                   2004/12/13 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3SystemLayerException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * （株式注文繰越スキップ銘柄通知繰越対象チェックサービス）。
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipObjectCheckService extends Service
{

    /**
     * (is繰越注文単位)<BR>
     * @@param l_orderUnit - 注文単位オブジェクト。
     * @@return boolean
     * @@roseuid 406A5226021E
     */
    public boolean isCarryOverOrderUnit(OrderUnit l_orderUnit)
        throws WEB3SystemLayerException;

}
@
