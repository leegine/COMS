head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFirstAdditionalInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��ꐅ���Ǐ؏��(WEB3AdminTPFirstAdditionalInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (��ꐅ���Ǐ؏��)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPFirstAdditionalInfo extends Message
{

    /**
     * (�o�ߓ���)<BR>
     */
    public String firstDepositPassDay = "0";

    /**
     * (�L���o�ߓ���)<BR>
     */
    public String firstDepositPassDayValid = "0";

    /**
     * (������)<BR>
     */
    public Date firstDepositOccurredDate = null;

    /**
     * (�ۏ؋���)<BR>
     */
    public String firstMarginDepositRate = "0";

    /**
     * (�ۏ؋��ێ���)<BR>
     */
    public String firstDepositRate = "0";

    /**
     * (�Ǐ؋��z)<BR>
     */
    public String firstDepositAmount = "0";

    /**
     * (�Ǐ،��ϕK�v�z)<BR>
     */
    public String firstSettlement = "0";

    /**
     * (�ۏ؋�����)<BR>
     */
    public String firstMarginDepositInDe = "0";

    /**
     * (�ۏ؋�����(�������z))<BR>
     */
    public String firstMarginDepositInDeExpect = "0";

    /**
     * (���ύό���)<BR>
     */
    public String firstSettledContract = "0";

    /**
     * (���������z)<BR>
     */
    public String firstUncancelAmt = "0";

    /**
     * (���������ϕK�v�z)<BR>
     */
    public String firstUncancelSettleRequiredAmt = "0";

    /**
     * @@roseuid 48EC703400C4
     */
    public WEB3AdminTPFirstAdditionalInfo()
    {

    }
}
@
