head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossCalcInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v�v�Z���̓��X�|���X(WEB3TrialCalcProfitLossCalcInputResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X���v�v�Z���̓��X�|���X�j<BR>
 * <BR>
 * �v�Z�T�[�r�X���v�v�Z�T�[�r�X�i���͉�ʕ\���j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcProfitLossCalcInputResponse<BR>
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossCalcInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_profitloss_calcinput";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501101150L;

    /**
     * @@roseuid 41C817A3011A
     */
    public WEB3TrialCalcProfitLossCalcInputResponse()
    {

    }

    /**
     * Constructor with WEB3TrialCalcProfitLossCalcInputRequest argument
     * @@param l_request WEB3TrialCalcProfitLossCalcInputRequest
     */
    public WEB3TrialCalcProfitLossCalcInputResponse
                (WEB3TrialCalcProfitLossCalcInputRequest l_request)
    {
        super(l_request);
    }
}
@
