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
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ZzิฯUnitService (WEB3AioFinanceAmountRepayUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ซ๔ (u) VK์ฌ                                     
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.PayRequiredAmountParams;
import webbroker3.common.WEB3BaseException;

/**
 * (ZzิฯUnitService)<BR>
 * ZzิฯUnitServiceC^[tFCX<BR>
 * 
 * @@author ซ๔(u)
 * @@version 1.0
 */
public interface WEB3AioFinanceAmountRepayUnitService extends Service
{
    /**
     * ิฯKvzf[^XV๐sคB<BR>
     * @@param l_payRequiredAmountParams - (ิฯKvzf[^Params)<BR>
     * ิฯKvzf[^ฬsIuWFNg <BR>
     * <BR>
     * ฆDDLๆ่ฉฎถฌ<BR>
     * @@throws WEB3BaseException
     */
    public void execute(PayRequiredAmountParams l_payRequiredAmountParams) throws WEB3BaseException;
}
@
