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
filename	WEB3MarginChangeCancelNotifyCancelUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引訂正取消通知取消一件サービス(WEB3MarginChangeCancelNotifyCancelUnitService.)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 艾興 (中訊) 新規作成
                   2004/12/10 中尾寿彦(SRA) 残案件対応による修正
                   2005/01/05 岡村和明(SRA) JavaDoc修正
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;

/**
 * （信用取引訂正取消通知取消一件サービスインタフェース）。<BR>
 * <BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_CREATE_NEW）
 * @@author 艾興
 * @@version 1.0
 */
public interface WEB3MarginChangeCancelNotifyCancelUnitService extends Service 
{
    
    /**
     * (notify取消)<BR>
     * 注文取消通知処理を実施する。<BR>
     * <BR>
     * @@param l_hostEqtypeOrderClmdReceiptParams - (株式訂正取消通知キューParams)
     * @@param l_orderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@return String
     */
    public String notifyCancel(
        HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@
