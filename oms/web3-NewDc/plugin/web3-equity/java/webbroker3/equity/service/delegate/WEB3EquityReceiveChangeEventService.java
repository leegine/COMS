head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityReceiveChangeEventService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ปจฎ๙ณๆมสm๙ณ๊T[rX(WEB3EquityReceiveChangeEventService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/24 j (u) VK์ฌ
                   2004/12/14 ๖๕F(SRA) cฤฮษๆ้Cณ
                   2005/01/06 ชบaพ(SRA) JavaDocCณ
*/
package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;

/**
 * iปจฎ๙ณๆมสm๙ณ๊T[rXjB<BR>
 * <BR>
 * igUNVฎซFTransactionalInterceptor.TX_CREATE_NEWj
 * @@version 1.0
 */
public interface WEB3EquityReceiveChangeEventService extends Service
{

    /**
     * (notify๙ณ)<BR>
     * ๙ณ๐ภsท้B<BR>
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
