head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : OฎoIนUnitService(WEB3AdminFeqExecutionEndUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 sp (u) VK์ฌ
                   2005/08/03 ฉ(u) r[     
*/

package webbroker3.feq.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;


/**
 * (OฎoIนUnitService)<BR>
 * OฎoIนUnitServiceC^tFCX<BR>
 * <BR>
 * igUNVฎซFTX_CREATE_NEWj<BR>
 *   
 * @@author sp
 * @@version 1.0
 */
public interface WEB3AdminFeqExecutionEndUnitService extends Service 
{
    
    /**
     * (execoIน)<BR>
     * ฺqPสฬoIน๐ภ{ท้B<BR>
     * @@param l_account - (ฺq)<BR>
     * ฺqIuWFNg<BR>
     * @@param l_strMarketCode - (s๊R[h)<BR>
     * s๊R[h<BR>
     * @@param l_datBizDate - (ญ๚)<BR>
     * ญ๚<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF7D50142
     */
    public void execExecEnd(WEB3GentradeMainAccount l_account, String l_strMarketCode, Date l_datBizDate) 
        throws WEB3BaseException;
}
@
