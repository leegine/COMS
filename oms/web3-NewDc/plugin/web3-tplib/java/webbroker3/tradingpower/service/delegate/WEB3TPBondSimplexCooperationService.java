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
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : ΒVvNXAgT[rX(WEB3TPBondSimplexCooperationService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/26 ΐz(u) VKμ¬ fNo.276,278,287
*/
package webbroker3.tradingpower.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * (ΒVvNXAgT[rX)<BR>
 * iΒVvNXAgT[rXj<BR>
 *
 * @@author ΐz
 * @@version 1.0
 */
public interface WEB3TPBondSimplexCooperationService extends Service
{
    /**
     * (saveΒtγΰ)<BR>
     * isaveΒtγΰj<BR>
     * <BR>
     * @@param l_lngAccountId - (ϋΐID)<BR>
     * iϋΐIDj<BR>
     * @@param l_dblRestraint - (Βtγΰ)<BR>
     * iΒtγΰj<BR>
     * @@param l_datFinTransactionDate - (gUNV­Άϊ)<BR>
     * igUNV­Άϊj<BR>
     * @@param l_datDeliveryDate - (σnϊ)<BR>
     * iσnϊj<BR>
     * @@param l_strOrderNo - (ΆNo)<BR>
     * iΆNoj<BR>
     * @@throws WEB3BaseException
     */
    public void saveBondBuyAmount(long l_lngAccountId, double l_dblRestraint,
        Date l_datFinTransactionDate, Date l_datDeliveryDate, String l_strOrderNo) throws WEB3BaseException;

    /**
     * (cancelΒtγΰ)<BR>
     * icancelΒtγΰj<BR>
     * <BR>
     * @@param l_strOrderNo - (ΆNo)<BR>
     * iΆNoj<BR>
     * @@throws WEB3BaseException
     */
    public void cancelBondBuyAmount(String l_strOrderNo) throws WEB3BaseException;

    /**
     * (getY]Ώzκ)<BR>
     * igetY]Ώzκj<BR>
     * <BR>
     * @@param l_subAccount - (βϋΐ)<BR>
     * (βϋΐ)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAssetList(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException;

}
@
