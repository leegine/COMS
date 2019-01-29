head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcBreakEvenPointResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v����_�v�Z���X�|���X(WEB3TrialCalcBreakEvenPointResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X���v����_�v�Z���X�|���X�j<BR>
 * <BR>
 * �v�Z�T�[�r�X���v����_�v�Z�T�[�r�X�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcBreakEvenPointResponse<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcBreakEvenPointResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_breakevenpoint";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 2005033001100L;

    /**
     * �i����_�ƂȂ锄�P���j<BR>
     * <BR>
     * breakEvenPoint<BR>
     */
    public String breakEvenPoint;

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
     * <BR>
     */
    public String commissionCourse;

    /**
     * �i�����v�j<BR>
     * <BR>
     * dealingProfit<BR>
     */
    public String dealingProfit;

    /**
     * @@roseuid 41C815800291
     */
    public WEB3TrialCalcBreakEvenPointResponse()
    {

    }
    /**
     *
     * @@param l_request WEB3TrialCalcBreakEvenPointRequest
     */
    public WEB3TrialCalcBreakEvenPointResponse(WEB3TrialCalcBreakEvenPointRequest l_request)
    {
        super(l_request);
    }
}
@
