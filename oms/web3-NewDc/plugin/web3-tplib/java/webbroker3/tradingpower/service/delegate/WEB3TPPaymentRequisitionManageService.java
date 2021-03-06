head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentRequisitionManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : üà¿ÇT[rX(WEB3TPPaymentRequisitionManageService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 Ð±ì (u) dlÏXf309,337
*/
package webbroker3.tradingpower.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

/**
 * (üà¿ÇT[rX)<BR>
 * (üà¿ÇT[rX)<BR>
 * <BR>
 * @@author Ð±ì
 * @@version 1.0
 */
public interface WEB3TPPaymentRequisitionManageService extends Service
{
    /**
     * (gets«à­¶óµ)<BR>
     * <BR>
     * @@param l_mainAccount - (Úq)<BR>
     * (Úq)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 487E928D02D7
     */
    public String getLackCashOccurSituation(MainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (getÇØ­¶óµ)<BR>
     * <BR>
     * @@param l_mainAccount - (Úq)<BR>
     * (Úq)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 487EA526027C
     */
    public String getAdditionalMarginSituation(MainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (getÇØ­¶óµ)<BR>
     * <BR>
     * @@param l_mainAccount - (Úq)<BR>
     * (Úq)<BR>
     * @@param l_blnAdditionalMargin - (ÇØ­¶l¶tO)<BR>
     * (ÇØ­¶l¶tO)<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 487EA526027C
     */
    public String getAdditionalMarginSituation(MainAccount l_mainAccount, boolean l_blnAdditionalMargin)
        throws WEB3BaseException;

    /**
     * (getüà¿ÚqÚ×îñ)<BR>
     * <BR>
     * @@param l_mainAccount - (Úq)<BR>
     * (Úq)<BR>
     * @@return WEB3TPPaymentRequisitionAccountDetailInfo
     * @@throws WEB3BaseException
     * @@roseuid 4871D08303CE
     */
    public WEB3TPPaymentRequisitionAccountDetailInfo getPaymentRequisitionAccountDetailInfo(
        MainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (gets«à­¶îñ)<BR>
     * <BR>
     * @@param l_mainAccount - (Úq)<BR>
     * (Úq)<BR>
     * @@return WEB3TPShortfallGenerationInfo
     * @@throws WEB3BaseException
     * @@roseuid 4871D08303E0
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(
        MainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (getÇØ­¶îñ)<BR>
     * <BR>
     * @@param l_mainAccount - (Úq)<BR>
     * (Úq)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@throws WEB3BaseException
     * @@roseuid 4872C602034B
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(
        MainAccount l_mainAccount) throws WEB3BaseException;

    /**
     * (getÇØ­¶îñ)<BR>
     * <BR>
     * @@param l_mainAccount - (Úq)<BR>
     * (Úq)<BR>
     * @@param l_blnAdditionalMargin - (ÇØ­¶l¶tO)<BR>
     * (ÇØ­¶l¶tO)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@throws WEB3BaseException
     * @@roseuid 4872C602034B
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(
        MainAccount l_mainAccount, boolean l_blnAdditionalMargin) throws WEB3BaseException;
}
@
