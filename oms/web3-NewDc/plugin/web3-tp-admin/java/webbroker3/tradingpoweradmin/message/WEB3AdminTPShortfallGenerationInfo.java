head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.43.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPShortfallGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �s�����������(WEB3AdminTPShortfallGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 �И��� (���u) �V�K�쐬 ���f��No.027
*/
package webbroker3.tradingpoweradmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�s�����������)<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3AdminTPShortfallGenerationInfo extends Message
{

    /**
     * (����(T+0))<BR>
     */
    public Date closeDate0 = null;

    /**
     * (����(T+1))<BR>
     */
    public Date closeDate1 = null;

    /**
     * (����(T+2))<BR>
     */
    public Date closeDate2 = null;

    /**
     * (����(T+3))<BR>
     */
    public Date closeDate3 = null;

    /**
     * (����(T+4))<BR>
     */
    public Date closeDate4 = null;

    /**
     * (����(T+5))<BR>
     */
    public Date closeDate5 = null;

    /**
     * (�K�v�����z(T+0))<BR>
     */
    public String requiredPayAmt0 = "0";

    /**
     * (�K�v�����z(T+1))<BR>
     */
    public String requiredPayAmt1 = "0";

    /**
     * (�K�v�����z(T+2))<BR>
     */
    public String requiredPayAmt2 = "0";

    /**
     * (�K�v�����z(T+3))<BR>
     */
    public String requiredPayAmt3 = "0";

    /**
     * (�K�v�����z(T+4))<BR>
     */
    public String requiredPayAmt4 = "0";

    /**
     * (�K�v�����z(T+5))<BR>
     */
    public String requiredPayAmt5 = "0";

    /**
     * (���Z�z(T+0))<BR>
     */
    public String adjustedAmt0 = "0";

    /**
     * (���Z�z(T+1))<BR>
     */
    public String adjustedAmt1 = "0";

    /**
     * (���v��S����(T+0))<BR>
     */
    public String dayTradeRestraint0 = "0";

    /**
     * (���v��S����(T+1))<BR>
     */
    public String dayTradeRestraint1 = "0";

    /**
     * (�ۏ؋�����̐U�֊z(T+0))<BR>
     */
    public String transferFromMarginDeposit0 = "0";

    /**
     * (�ۏ؋�����̐U�֊z(T+1))<BR>
     */
    public String transferFromMarginDeposit1 = "0";

    /**
     * @@roseuid 48EC7033019F
     */
    public WEB3AdminTPShortfallGenerationInfo()
    {

    }
}
@
