head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverSkipUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式注文繰越スキップ銘柄通知一件サービス(WEB3EquityOrderCarryOverSkipPartService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 鄒政 (中訊) 新規作成
                   2004/12/13 中尾寿彦(SRA) 残案件対応による更新
                   2005/01/06 岡村和明(SRA) JavaDoc修正
*/

package webbroker3.equity.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEquityCarryoverSkipParams;

/**
 * （株式注文繰越スキップ銘柄通知一件サービス）。<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipUnitService extends Service
{

    /**
     * (注文繰越結果更新)<BR>
     * スキップ銘柄の登録／登録抹消通知内容、<BR>
     * 及び注文繰越処理の実行状況（未処理／処理済）により、<BR>
     * 必要な繰越処理結果の更新を行う。<BR>
     * @@param l_hostEquityCarryoverSkipParams 
     * 注文繰越スキップ銘柄通知キューParams<BR>
     * @@param l_listOrderexecutionEndParams 
     * @@throws WEB3BaseException
     * @@roseuid 4137CDDA02D6
     */
    public void updateOrderCarryOverResult(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams,List l_listOrderexecutionEndParams )
        throws WEB3BaseException;
}
@
