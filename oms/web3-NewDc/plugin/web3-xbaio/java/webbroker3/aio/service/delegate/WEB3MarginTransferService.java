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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金への振替サービス(WEB3MarginTransferService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/12 張騰宇 (中訊) 新規作成 仕様変更モデル736 739
Revision History : 2007/07/28 孟亜南 (中訊) 仕様変更モデル741
*/
package webbroker3.aio.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeMainAccount;

/**
 * (保証金への振替サービス)<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public interface WEB3MarginTransferService extends Service
{
    /**
     * (submit保証金振替)<BR>
     * 保証金への振替サービス処理を行う。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_dblCashinAmt - (入金額)<BR>
     * 入金額<BR>
     * @@param l_strPassword - (暗証番号)<BR>
     * 暗証番号<BR>
     * @@param l_trader - (代理入力者)<BR>
     * 代理入力者
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
