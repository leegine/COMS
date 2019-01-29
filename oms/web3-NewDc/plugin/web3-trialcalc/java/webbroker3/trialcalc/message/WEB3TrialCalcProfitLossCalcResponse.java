head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossCalcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v�v�Z���X�|���X(WEB3TrialCalcProfitLossCalcResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X���v�v�Z���X�|���X�j<BR>
 * <BR>
 * �v�Z�T�[�r�X���v�v�Z�T�[�r�X�i���v�v�Z���s�j�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcProfitLossCalcResponse
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcProfitLossCalcResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_profitloss_calc";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501101150L;

    /**
     * �i���萔���j<BR>
     * <BR>
     * buyCommission<BR>
     */
    public String buyCommission;

    /**
     * �i���萔������Łj<BR>
     * <BR>
     * buyCommissionTax<BR>
     */
    public String buyCommissionTax;

    /**
     * �i���萔���j<BR>
     * <BR>
     * sellCommission<BR>
     */
    public String sellCommission;

    /**
     * �i���萔������Łj<BR>
     * <BR>
     * sellCommissionTax<BR>
     */
    public String sellCommissionTax;

    /**
     * �i�萔���R�[�X�j<BR>
     * <BR>
     * �萔���R�[�X�B<BR>
     * �i02�F��z�萔��(�X�^���_�[�h)�@@03�F��������v�@@04�F����<BR>
     * �@@05�F�����z���@@12�F��z�萔��(�n�C�p�[�{�b�N�X)�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * commissionCourse<BR>
     * (02 : Fixed commission(standard)  03 : Contract price total<BR>
     *  04 : Contract frequency             05 : One day fixed charge system<BR>
     *  12 : Fixed commission(hyper box))<BR>
     */
    public String commissionCourse;

    /**
     * �i���v�j<BR>
     * <BR>
     * prolossAmount<BR>
     */
    public String prolossAmount;

    /**
     * @@roseuid 41C817AE0010
     */
    public WEB3TrialCalcProfitLossCalcResponse()
    {

    }

    /**
     *
     * @@param l_request WEB3TrialCalcProfitLossCalcRequest
     */
    public WEB3TrialCalcProfitLossCalcResponse(WEB3TrialCalcProfitLossCalcRequest l_request)
    {
        super(l_request);
    }
}
@
