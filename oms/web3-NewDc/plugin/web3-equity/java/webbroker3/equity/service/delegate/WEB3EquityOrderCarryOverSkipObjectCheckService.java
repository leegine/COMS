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
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ®¶JzXLbvÁ¿ÊmJzÎÛ`FbNT[rX
                       (WEB3EquityOrderCarryOverSkipObjectCheckService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/31 ç¾­ (u) VKì¬
                   2004/12/13 öõF(SRA) cÄÎÉæéC³
                   2005/01/06 ªºa¾(SRA) JavaDocC³
*/

package webbroker3.equity.service.delegate;

import webbroker3.common.WEB3SystemLayerException;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

/**
 * i®¶JzXLbvÁ¿ÊmJzÎÛ`FbNT[rXjB
 * @@version 1.0
 */
public interface WEB3EquityOrderCarryOverSkipObjectCheckService extends Service
{

    /**
     * (isJz¶PÊ)<BR>
     * @@param l_orderUnit - ¶PÊIuWFNgB
     * @@return boolean
     * @@roseuid 406A5226021E
     */
    public boolean isCarryOverOrderUnit(OrderUnit l_orderUnit)
        throws WEB3SystemLayerException;

}
@
