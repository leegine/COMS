head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.11.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccEstablishSearchInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : VKûÀJÝEûÀÚÇEOCbNÚqîñ(WEB3AccInfoAccEstablishSearchInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  ½¶q(u) VKì¬
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (VKûÀJÝEûÀÚÇEOCbNÚqîñ)<BR>
 * VKûÀJÝEûÀÚÇEOCbNÚqîñ<BR>
 * @@author ½¶q
 * @@version 1.0
 */

public class WEB3AccInfoAccEstablishSearchInfo extends Message
{
    /**
     * (XR[h)<BR>
     * XR[h<BR>
     */
    public String branchCode;

    /**
     * (µÒR[h)<BR>
     * µÒR[h<BR>
     */
    public String traderCode;

    /**
     * (ÚqR[h)<BR>
     * ÚqR[h<BR>
     */
    public String accountCode;

    /**
     * (ûÀíÊ)<BR>
     * ûÀíÊ<BR>
     * <BR>
     * 1F@@Âlq<BR>
     * 2F@@@@lq<BR>
     */
    public String accountTypeCode;

    /**
     * (Úq¼i¿j)<BR>
     * Úq¼i¿j<BR>
     */
    public String accountName;

    /**
     * (Úq¼iJi))<BR>
     * Úq¼iJi)<BR>
     */
    public String accountNameKana;

    /**
     * (ûÀJÝú)<BR>
     * ûÀJÝú<BR>
     */
    public Date accountOpenDate;

    /**
     * (üàæâs)<BR>
     * üàæâs<BR>
     */
    public String payFinancialInstitution;

    /**
     * (âsxX¼)<BR>
     * âsxX¼<BR>
     */
    public String financialBranchName;

    /**
     * (ÈÚ)<BR>
     * ÈÚ<BR>
     */
    public String item;

    /**
     * (âsÔ)<BR>
     * âsÔ<BR>
     */
    public String financialInstitutionNumber;

    /**
     * (âsxXÔ)<BR>
     * âsxXÔ<BR>
     */
    public String financialBranchCode;

    /**
     * (âsûÀÔ)<BR>
     * âsûÀÔ<BR>
     */
    public String financialAccountCode;

    /**
     * (ÚqZiXÖÔj)<BR>
     * ÚqZiXÖÔj<BR>
     */
    public String zipCode;

    /**
     * (ÚqZiZ1))<BR>
     * ÚqZiZ1j<BR>
     */
    public String address1;

    /**
     * (ÚqZiZ2j)<BR>
     * ÚqZiZ2j<BR>
     */
    public String address2;

    /**
     * (ÚqZiZ3j)<BR>
     * ÚqZiZ3j<BR>
     */
    public String address3;

    /**
     * (OCbNtO )<BR>
     * OCbNtO <BR>
     * <BR>
     * trueF@@OCbN<BR>
     * falseF@@OCbNð<BR>
     */
    public boolean loginLockFlag;

    /**
     * (OCG[ñ)<BR>
     * OCG[ñ<BR>
     */
    public String loginErrorCount;
}
@
