head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.42.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : ()åa¤ Ø\[VVXeæñ
File Name        : üà¿Úqõêjbg(WEB3AdminTPPaymentRequisitionListUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 Ð±ì (u) VKì¬ fNo.027
Revision History : 2008/10/28 £«F (u) dlÏXfNo.042
*/
package webbroker3.tradingpoweradmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (üà¿Úqõêjbg)<BR>
 * <BR>
 * @@author Ð±ì
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionListUnit extends Message
{

    /**
     * (XR[h)<BR>
     */
    public String branchCode;

    /**
     * (ÚqR[h)<BR>
     */
    public String accountCode;

    /**
     * (Úq¼)<BR>
     */
    public String accountName;

    /**
     * (µÒR[h)<BR>
     */
    public String traderCode;

    /**
     * (®«)<BR>
     */
    public String attribute;

    /**
     * (§Öà)<BR>
     */
    public String debitAmount;

    /**
     * (ÁÊ§Öà)<BR>
     */
    public String specialDebitAmount;

    /**
     * (Kvüàz)<BR>
     */
    public String requiredPayAmt;

    /**
     * (æêÇØàz)<BR>
     */
    public String firstDepositAmount;

    /**
     * (æêÇØoßú)<BR>
     */
    public String firstDepositPassDay;

    /**
     * (æñÇØ¿(1))<BR>
     */
    public String secondDeposit1;

    /**
     * (æñÇØ¿(2))<BR>
     */
    public String secondDeposit2;

    /**
     * (æñÇØ¿¢üà)<BR>
     */
    public String secondDepositNonPay;

    /**
     * @@roseuid 48EC703302F7
     */
    public WEB3AdminTPPaymentRequisitionListUnit()
    {

    }
}
@
