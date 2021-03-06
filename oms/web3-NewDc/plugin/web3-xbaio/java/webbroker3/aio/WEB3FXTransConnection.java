head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTransConnection.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()εa€ Ψ\[VVXeζρ
File Name        : UΦΪ±(WEB3FXTransConnection.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/09/16 £«F (u) VKμ¬ dlΟXf1195
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (UΦΪ±)<BR>
 * UΦΪ±<BR>
 * <BR>
 * @@author £«F
 * @@version 1.0
 */
public interface WEB3FXTransConnection extends Service
{
    /**
     * (updateGFTUΦσ΅)<BR>
     * σMΚπGFTUΦσ΅e[uΜf[^Ι½f·ιB <BR>
     * @@param l_strInstitutionCode - (ΨοΠR[h)<BR>
     * ΨοΠR[h<BR>
     * @@param l_strBranchCode - (XR[h)<BR>
     * XR[h<BR>
     * @@param l_strRequestNumber - (―ΚR[h)<BR>
     * ―ΚR[h<BR>
     * @@param l_strResultCode - (σtΚR[h)<BR>
     * σtΚR[h<BR>
     * @@throws WEB3BaseException
     */
    public void updateGFTTransferStatus(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strResultCode)
        throws WEB3BaseException;

    /**
     * (doUΦΐs)<BR>
     * UΦΐsπs€B <BR>
     * @@param l_compFxConditionParams  - (οΠΚFXVXeπParams)<BR>
     * οΠΚFXVXeπParams<BR>
     * @@param l_compFxConditionParams - (GFTΛdΆΎΧ)<BR>
     * GFTΛdΆΎΧ<BR>
     * @@return WEB3FXGftResultNoticeTelegramUnit
     * @@throws WEB3BaseException
     */
    public WEB3FXGftResultNoticeTelegramUnit doTransfer(
        CompFxConditionParams l_compFxConditionParams, WEB3FXGftAskingTelegramUnit l_fXGftAskingTelegramUnit)
            throws WEB3BaseException;
}
@
