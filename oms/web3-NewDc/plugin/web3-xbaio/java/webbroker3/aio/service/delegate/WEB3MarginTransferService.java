head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.12.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3MarginTransferService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : ÛØàÖÌUÖT[rX(WEB3MarginTransferService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/12 £«F (u) VKì¬ dlÏXf736 739
Revision History : 2007/07/28 Ðì (u) dlÏXf741
*/
package webbroker3.aio.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (ÛØàÖÌUÖT[rX)<BR>
 * @@author £«F
 * @@version 1.0
 */
public interface WEB3MarginTransferService extends Service
{
    /**
     * (submitÛØàUÖ)<BR>
     * ÛØàÖÌUÖT[rXðs¤B<BR>
     * @@param l_mainAccount - (Úq)<BR>
     * Úq<BR>
     * @@param l_datDeliveryDate - (ónú)<BR>
     * ónú<BR>
     * @@param l_dblCashinAmt - (üàz)<BR>
     * üàz<BR>
     * @@param l_strPassword - (ÃØÔ)<BR>
     * ÃØÔ<BR>
     * @@param l_trader - (ãüÍÒ)<BR>
     * ãüÍÒ
     * @@throws WEB3BaseException
     */
    public void submitMarginTransfer(
        WEB3GentradeMainAccount l_mainAccount,
        Date l_datDeliveryDate,
        double l_dblCashinAmt,
        String l_strPassword,
        Trader l_trader) throws WEB3BaseException;
}
@
