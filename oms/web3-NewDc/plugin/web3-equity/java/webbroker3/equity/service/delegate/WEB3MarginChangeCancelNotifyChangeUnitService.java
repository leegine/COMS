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
filename	WEB3MarginChangeCancelNotifyChangeUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : Mpๆ๘๙ณๆมสm๙ณ๊T[rX(WEB3MarginChangeCancelNotifyChangeUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 ไป (u) VK์ฌ
                   2004/12/14 ๖๕F(SRA) cฤฮษๆ้Cณ
                   2005/01/05 ชบaพ(SRA) JavaDocCณ
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;

/**
 * iMpๆ๘๙ณๆมสm๙ณ๊T[rXjB<BR>
 * <BR>
 * igUNVฎซFTransactionalInterceptor.TX_CREATE_NEWj
 * @@author ไป
 * @@version 1.0
 */
public interface WEB3MarginChangeCancelNotifyChangeUnitService extends Service 
{
    
    /**
     * (notify๙ณ)<BR>
     * ถ๙ณสm๐ภ{ท้B
     * @@param l_hostEqtypeOrderClmdReceiptParams - (ฎ๙ณๆมสmL[Params)
     * @@param l_orderUnit - (ถPส)
     * @@throws WEB3BaseException
     */
    public void notifyChange(
        HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams,
        EqTypeOrderUnit l_orderUnit)
        throws WEB3BaseException;
}
@
