head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.33.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPFirstAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ꐅ���Ǐ؏��(WEB3TPFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 �����F�i���u�j�V�K�쐬 ���f��No.312
*/
package webbroker3.tradingpower.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (��ꐅ���Ǐ؏��) <BR>
 * (��ꐅ���Ǐ؏��)<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3TPFirstAdditionalInfo extends Message
{
    /**
     * (�o�ߓ���)<BR>
     */
    public String firstDepositPassDay;

    /**
     * (�L���o�ߓ���)<BR>
     */
    public String firstDepositPassDayValid;

    /**
     * (������)<BR>
     */
    public Date firstDepositOccurredDate;

    /**
     * (�ۏ؋���)<BR>
     */
    public String firstMarginDepositRate;

    /**
     * (�ۏ؋��ێ���)<BR>
     */
    public String firstDepositRate;

    /**
     * (�Ǐ؋��z)<BR>
     */
    public String firstDepositAmount;

    /**
     * (�Ǐ،��ϕK�v�z)<BR>
     */
    public String firstSettlement;

    /**
     * (�ۏ؋�����)<BR>
     */
    public String firstMarginDepositInDe;

    /**
     * (�ۏ؋�����(�������z))<BR>
     */
    public String firstMarginDepositInDeExpect;

    /**
     * (���ύό���)<BR>
     */
    public String firstSettledContract;

    /**
     * (���������z)<BR>
     */
    public String firstUncancelAmt;

    /**
     * (���������ϕK�v�z)<BR>
     */
    public String firstUncancelSettleRequiredAmt;

    /**
     * @@roseuid 48EC703400C1
     */
    public WEB3TPFirstAdditionalInfo()
    {

    }
}
@
