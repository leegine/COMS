head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.18.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioCashinCooperationNotifyUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携通知一件サービス(WEB3AioCashinCooperationNotifyUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 黄建(中訊) 新規作成
*/

package webbroker3.aio.service.delegate;

import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * 入金連携通知一件サービス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3AioCashinCooperationNotifyUnitService extends Service 
{
    /**
     * (notify入金連携)<BR>
     * 入金通知を１件処理する。<BR>
     * 入金通知の注文登録する。<BR>
     * シーケンス図 <BR>
     * 「(入金連携通知一件サービスImpl).notify入金連携」 参照 <BR>
     * <BR>
     * @@param l_bankDepositNotifyParams -  入金通知Params <BR>
     * @@param l_mainAccount -  顧客 <BR>
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 40BEF93003E7
     */
    public void notifyCashinCooperation(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException;
}
@
