head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.17.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioFinanceAmountRepayUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 融資額返済UnitService (WEB3AioFinanceAmountRepayUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 唐性峰 (中訊) 新規作成                                     
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.PayRequiredAmountParams;
import webbroker3.common.WEB3BaseException;

/**
 * (融資額返済UnitService)<BR>
 * 融資額返済UnitServiceインターフェイス<BR>
 * 
 * @@author 唐性峰(中訊)
 * @@version 1.0
 */
public interface WEB3AioFinanceAmountRepayUnitService extends Service
{
    /**
     * 返済必要額データ更新処理を行う。<BR>
     * @@param l_payRequiredAmountParams - (返済必要額データParams)<BR>
     * 返済必要額データの行オブジェクト <BR>
     * <BR>
     * ※DDLより自動生成<BR>
     * @@throws WEB3BaseException
     */
    public void execute(PayRequiredAmountParams l_payRequiredAmountParams) throws WEB3BaseException;
}
@
