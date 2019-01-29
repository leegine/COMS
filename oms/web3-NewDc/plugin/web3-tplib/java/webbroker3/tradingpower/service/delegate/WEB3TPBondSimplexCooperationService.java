head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPBondSimplexCooperationService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券シンプレクス連携サービス(WEB3TPBondSimplexCooperationService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/26 安陽(中訊) 新規作成 モデルNo.276,278,287
*/
package webbroker3.tradingpower.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * (債券シンプレクス連携サービス)<BR>
 * （債券シンプレクス連携サービス）<BR>
 *
 * @@author 安陽
 * @@version 1.0
 */
public interface WEB3TPBondSimplexCooperationService extends Service
{
    /**
     * (save債券買付代金)<BR>
     * （save債券買付代金）<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * （口座ID）<BR>
     * @@param l_dblRestraint - (債券買付代金)<BR>
     * （債券買付代金）<BR>
     * @@param l_datFinTransactionDate - (トランザクション発生日)<BR>
     * （トランザクション発生日）<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * （受渡日）<BR>
     * @@param l_strOrderNo - (注文No)<BR>
     * （注文No）<BR>
     * @@throws WEB3BaseException
     */
    public void saveBondBuyAmount(long l_lngAccountId, double l_dblRestraint,
        Date l_datFinTransactionDate, Date l_datDeliveryDate, String l_strOrderNo) throws WEB3BaseException;

    /**
     * (cancel債券買付代金)<BR>
     * （cancel債券買付代金）<BR>
     * <BR>
     * @@param l_strOrderNo - (注文No)<BR>
     * （注文No）<BR>
     * @@throws WEB3BaseException
     */
    public void cancelBondBuyAmount(String l_strOrderNo) throws WEB3BaseException;

    /**
     * (get資産評価額一覧)<BR>
     * （get資産評価額一覧）<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * (補助口座)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAssetList(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException;

}
@
