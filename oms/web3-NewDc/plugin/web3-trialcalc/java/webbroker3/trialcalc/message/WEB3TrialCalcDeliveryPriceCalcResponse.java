head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcDeliveryPriceCalcResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X��n����v�Z���X�|���X(WEB3TrialCalcDeliveryPriceCalcResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X��n����v�Z���X�|���X�j<BR>
 * <BR>
 * �v�Z�T�[�r�X��n����v�Z�T�[�r�X�̃��X�|���X�f�[�^�B<BR>
 * <BR>
 * WEB3TrialCalcDeliveryPriceCalcResponse<BR>
 * @@author saravanan
 * @@version 1.0
 */
public class WEB3TrialCalcDeliveryPriceCalcResponse extends WEB3GenResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_estimatedamount_calc";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

    /**
     * �i�������j<BR>
     * <BR>
     * productName<BR>
     */
    public String productName;

    /**
     * �i�����擾�敪�j<BR>
     * <BR>
     * �����擾�敪�B<BR>
     * �i1�F�����@@2�F�O���I�l�@@3�F�����I�l�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPriceGetDiv<BR>
     * (1 : Current price  2 : Last close price  3 : Today close price)<BR>
     * <BR>
     */
    public String currentPriceGetDiv;

    /**
     * �i�����j<BR>
     * <BR>
     * �����B<BR>
     * �i�����擾���i���N�G�X�g.���P����null�j�̂݃Z�b�g�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPrice<BR>
     * (Only set when current price is acquired.�i���N�G�X�g.���P����null�j)<BR>
     */
    public String currentPrice;

    /**
     * �i������ԁi�������\���ԁj�j<BR>
     * <BR>
     * ������ԁi�������\���ԁj�B<BR>
     * �i�����擾���i���N�G�X�g.���P����null�j�̂݃Z�b�g�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * currentPrice<BR>
     * (Only set when current price is acquired.�i���N�G�X�g.���P����null�j)<BR>
     * <BR>
     */
    public Date currentPriceTime;

    /**
     * �i�s��R�[�h�j<BR>
     * <BR>
     * �s��R�[�h�B<BR>
     * �i1�F�����@@2�F���@@3�F���É��@@6�F�����@@8�F�D�y�@@9�FNNM�@@10�FJASDAQ�j<BR>
     * �i�s��R�[�h�w��Ȃ����̂݃Z�b�g�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code.<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM   10 : JASDAQ)<BR>
     * <BR>
     * <BR>
     */
    public String marketCode;

    /**
     * �i�萔���j<BR>
     * <BR>
     * commission<BR>
     */
    public String commission;

    /**
     * �i�萔������Łj<BR>
     * <BR>
     * commissionTax<BR>
     */
    public String commissionTax;

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
     * �i��n����j<BR>
     * <BR>
     * deliveryPrice<BR>
     */
    public String deliveryPrice;

    /**
     * @@roseuid 41C813C80252
     */
    public WEB3TrialCalcDeliveryPriceCalcResponse()
    {
    }

    /**
     *
     * @@param l_request of WEB3TrialCalcDeliveryPriceCalcRequest
     */
    public WEB3TrialCalcDeliveryPriceCalcResponse(WEB3TrialCalcDeliveryPriceCalcRequest l_request)
    {
        super(l_request);
    }
}
@
