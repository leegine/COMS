head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.19.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioOnPaymentCooperationUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : oเAgUnitService(WEB3AioOnPaymentCooperationUnitService)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/16 ่่Oเ๙ (u) VK์ฌ   
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (oเAgUnitService) <BR>
 * oเAgUnitServiceC^[tFCX<BR>
 * 
 * @@author ่่Oเ๙(u)
 * @@version 1.0
 */
public interface WEB3AioOnPaymentCooperationUnitService extends Service
{
    /**
     * oเAg๐sค
     * 
     * @@param l_aioOrderUnits - ถPสIuWFNg[ ]
     * @@return WEB3GenResponse
     * @@roseuid 41BCF2CC0279
     */
    public void execute(AioOrderUnit[] l_aioOrderUnits) throws WEB3BaseException;
}@
