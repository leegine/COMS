head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecuteService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()ๅaค ุ\[VVXeๆ๑
File Name        : ยฉฎ๑่T[rX(WEB3BondAutoExecuteService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 ๊ฯ (u) VK์ฌ
*/

package webbroker3.bd.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (ยฉฎ๑่T[rX)<BR>
 * ยฉฎ๑่T[rXC^[tF[X<BR>
 * 
 * @@author ๊ฯ(u)
 * @@version 1.0 
 */
public interface WEB3BondAutoExecuteService extends WEB3BackBusinessService
{
    /**
     * ยฉฎ๑่๐ภ{ท้B<BR>
     * @@param l_request - (NGXg)<BR>
     * NGXg<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
    
    /**
     * (execฉฎ๑่Forฺq)<BR>
     * execฉฎ๑่Forฺq <BR>
     * @@param l_mainAccount - (ฺq)<BR>
     * ฺq<BR>
     * @@throws WEB3BaseException
     */
    public void execAutoExecuteForAccount(MainAccount l_mainAccount) throws WEB3BaseException;
}
@
