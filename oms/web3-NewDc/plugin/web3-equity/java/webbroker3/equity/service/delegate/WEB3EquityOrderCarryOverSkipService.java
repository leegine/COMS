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
filename	WEB3EquityOrderCarryOverSkipService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ฎถJzXLbvมฟสmT[rX(WEB3EquityOrderCarryOverSkipService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ็พญ (u) VK์ฌ
                   2004/12/13 ๖๕F(SRA) cฤฮษๆ้Cณ
                   2005/01/06 ชบaพ(SRA) JavaDocCณ
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.service.delegate.WEB3BackBusinessService;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.WEB3BaseException;

/**
 * iฎถJzXLbvมฟสmT[rXC^tF[XjB<BR>
 * <BR>
 * igUNVฎซFTransactionalInterceptor.TX_CREATE_NEWj
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipService
    extends WEB3BackBusinessService
{

    /**
     * ฎถJzXLbvมฟสmT[rX๐ภ{ท้<BR>
     * @@param l_request - NGXg
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 4056DE2802DC
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException;

}
@
