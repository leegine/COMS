head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.14.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferManagementUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ų\[VVXeęń
File Name        : FXUÖĒUnitServiceC^[tFCX(WEB3AdminFXTransferManagementUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/20 °üķ (u) VKģ¬
*/

package webbroker3.aio.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3Administrator;

/**
 * (FXUÖĒUnitService) <BR>
 * FXUÖĒUnitServiceC^[tFCX
 * 
 * @@author °üķ(u)
 * @@version 1.0
 */
public interface WEB3AdminFXTransferManagementUnitService extends Service
{
    
    /**
     * (submitęĮ) <BR>
     * DBÉĪµÄUÖ¶ĢęĮšs¤B
     * 
     * @@param l_gftTransferStatusParam - GFTUÖóµParamsIuWFNg
     * @@param l_administrator - ĒŅIuWFNg
     * @@param l_strPassword - pX[h
     * @@throws WEB3BaseException
     * @@roseuid 41C68F0702E3
     */
    public void submitCancel(GftTransferStatusParams l_gftTransferStatusParam,
        WEB3Administrator l_administrator, String l_strPassword) throws WEB3BaseException;
}@
