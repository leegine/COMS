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
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ฎถJzXLbvมฟสm๊T[rX(WEB3EquityOrderCarryOverSkipPartService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ็พญ (u) VK์ฌ
                   2004/12/13 ๖๕F(SRA) cฤฮษๆ้XV
                   2005/01/06 ชบaพ(SRA) JavaDocCณ
*/

package webbroker3.equity.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.data.HostEquityCarryoverSkipParams;

/**
 * iฎถJzXLbvมฟสm๊T[rXjB<BR>
 * <BR>
 * igUNVฎซFTransactionalInterceptor.TX_CREATE_NEWj
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipUnitService extends Service
{

    /**
     * (ถJzสXV)<BR>
     * XLbvมฟฬo^^o^มสmเeA<BR>
     * yัถJzฬภs๓ตiข^ฯjษๆ่A<BR>
     * KvศJzสฬXV๐sคB<BR>
     * @@param l_hostEquityCarryoverSkipParams 
     * ถJzXLbvมฟสmL[Params<BR>
     * @@param l_listOrderexecutionEndParams 
     * @@throws WEB3BaseException
     * @@roseuid 4137CDDA02D6
     */
    public void updateOrderCarryOverResult(HostEquityCarryoverSkipParams l_hostEquityCarryoverSkipParams,List l_listOrderexecutionEndParams )
        throws WEB3BaseException;
}
@
