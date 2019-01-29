head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.00.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAdminCashTransferListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
File Name        : “üo‹àˆê——–¾×(WEB3AioAdminCashTransferListUnit)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 ‰½•¶•q (’†u) V‹Kì¬@@d—l•ÏXƒ‚ƒfƒ‹ NO.693
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (“üo‹àˆê——–¾×)<BR>
 * “üo‹àˆê——–¾×ƒNƒ‰ƒX<BR>
 * 
 * @@author ‰½•¶•q
 * @@version 1.0
 */
public class WEB3AioAdminCashTransferListUnit extends Message
{
    /**
     * serialVersionUID<BR>
     */
    private static final long serialVersionUID = 200702051000L;

    /**
     * (’•¶í•Ê)<BR>
     * ’•¶í•Ê<BR>
     * <BR>
     * |||||||||||||||||||||||||<BR>
     * ƒ“ü‹à„<BR>
     * 101F SONAR“ü‹à<BR>
     * 102F ƒo[ƒ`ƒƒƒ‹“ü‹à<BR>
     * 103F ƒlƒbƒg“ü‹à<BR>
     * 104F U‘Ö(Š”æØ‹’‹à‚©‚ç—a‚è‹à)<BR>
     * 105F ˆ×‘Ö•ÛØ‹àU‘Ö(ˆ×‘Ö•ÛØ‹à‚©‚ç—a‚è‹à)<BR>
     * 106F ‚»‚Ì‘¼U‘Ö(X‚©‚ç—a‚è‹à)<BR>
     * ƒo‹à„<BR>
     * 201F o‹à<BR>
     * 202F U‘Ö(—a‚è‹à‚©‚çŠ”æØ‹’‹à)<BR>
     * 203F ˆ×‘Ö•ÛØ‹àU‘Ö(—a‚è‹à‚©‚çˆ×‘Ö•ÛØ‹à)<BR>
     * 204F ‚»‚Ì‘¼U‘Ö(—a‚è‹à‚©‚çX)<BR>
     * |||||||||||||||||||||||||<BR>
     */
    public String orderType;

    /**
     * (ó“n“ú)<BR>
     * ’•¶‚Ìó“n“ú<BR>
     */
    public Date deliveryDate;

    /**
     * (•”“XƒR[ƒh)<BR>
     * •”“XƒR[ƒh<BR>
     */
    public String branchCode;

    /**
     * (ŒÚ‹qƒR[ƒh)<BR>
     * ŒÚ‹qƒR[ƒh<BR>
     */
    public String accountCode;

    /**
     * (ŒÚ‹q–¼)<BR>
     * ŒÚ‹q–¼<BR>
     */
    public String accountName;

    /**
     * (’•¶“ú)<BR>
     * ’•¶“ú<BR>
     */
    public Date orderDate;

    /**
     * (ƒXƒe[ƒ^ƒX)<BR>
     * ƒXƒe[ƒ^ƒX<BR>
     * <BR>
     * |||||<BR>
     * 1F Š®—¹<BR>
     * 2F –¢ˆ—<BR>
     * 9F ƒGƒ‰[<BR>
     * |||||<BR>
     */
    public String cashinoutStatus;

    /**
     * (“ü‹à‹àŠz)<BR>
     * “ü‹à‹àŠz<BR>
     */
    public String cashinAmt;

    /**
     * (o‹à‹àŠz)<BR>
     * o‹à‹àŠz<BR>
     */
    public String cashoutAmt;

    /**
     * (“ü—ÍŒo˜H)<BR>
     * “ü—ÍŒo˜H<BR>
     */
    public String orderRoutDiv;

    /**
     * (“ü—ÍÒ)<BR>
     * “ü—ÍÒ<BR>
     */
    public String operatorCode;

    /**
     * (‹âsƒR[ƒh)<BR>
     * ‹âsƒR[ƒh<BR>
     */
    public String financialInstitutionCode;

    /**
     * (x“XƒR[ƒh)<BR>
     * x“XƒR[ƒh<BR>
     */
    public String financialBranchCode;

    /**
     * (ŒûÀí•Ê)<BR>
     * ŒûÀí•Ê<BR>
     */
    public String accountTypeCode;

    /**
     * (ŒûÀ”Ô†)<BR>
     * ŒûÀ”Ô†<BR>
     */
    public String financialAccountCode;

    /**
     * @@roseuid 45C3F15701A5
     */
    public WEB3AioAdminCashTransferListUnit() 
    {
        
    }
}
@
