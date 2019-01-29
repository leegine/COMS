head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioDisplayProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�\����������(WEB3TrialCalcPortfolioDisplayProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�\���������ׁj<BR>
 * <BR>
 * WEB3TrialCalcPortfolioDisplayProductUnit<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�\���������ׁB<BR>
 * @@author umadevi
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioDisplayProductUnit extends Message
{
    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * productCode<BR>
     */
    public String productCode;

    /**
     * �i�������j<BR>
     * <BR>
     * productName<BR>
     */
    public String productName;

    /**
     * �i�s��R�[�h�j<BR>
     * <BR>
     * �s��R�[�h�B<BR>
     * �i1�F�����@@2�F���@@3�F���É��@@6�F�����@@8�F�D�y�@@9�FNNM�@@10�FJASDAQ�j<BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code.<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  8 : SAPPORO<BR>
     *  9 : NNM   10 : JASDAQ)<BR>
     */
    public String marketCode;

    /**
     * �i�����j<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

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
     * <BR>currentPrice<BR>
     * (Only set when current price is acquired.�i���N�G�X�g.���P����null�j)<BR>
     */
    public Date currentPriceTime;

    /**
     * �i���t�P���j<BR>
     * <BR>
     * buyPrice<BR>
     */
    public String buyPrice;

    /**
     * �i���t����j<BR>
     * <BR>
     * buyAmount<BR>
     */
    public String buyAmount;

    /**
     * �i�]���P���j<BR>
     * <BR>
     * evaluationPrice<BR>
     */
    public String evaluationPrice;

    /**
     * �i�]���z�j<BR>
     * <BR>
     * appraisalPrice<BR>
     */
    public String appraisalPrice;

    /**
     * �i�]�����v�j<BR>
     * <BR>
     * appraisalProfitLoss<BR>
     */
    public String appraisalProfitLoss;

    /**
     * �i�]�����v���j<BR>
     * <BR>
     * appraisalProfitLossRate(percent)<BR>
     * <BR>
     * �]�����v���B�i���P�ʁj<BR>
     */
    public String appraisalProfitLossRate;

    /**
     * �i���́^����敪�j<BR>
     * <BR>
     * ���́^����敪�B<BR>
     * �i1�F���́@@2�F����j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * inputCapitalGainDiv<BR>
     * (1 : Input  2 : Specific)<BR>
     * <BR>
     */
    public String inputCapitalGainDiv;

    /**
     * �i�G���[�R�[�h�j<BR>
     * <BR>
     * �G���[�R�[�h�B<BR>
     * �i0001�F�w��s��͔���<BR>
     * �@@0002�F�戵�s��Ȃ�<BR>
     * �@@0003�F�D��s��ꗗ�Ɏ戵�s��Ȃ�<BR>
     * �@@0004�F�����擾�G���[<BR>
     * �@@0005�F�ϑ��萔���擾�G���[<BR>
     * �@@0006�G�Y�������͑��݂��Ȃ�<BR>
     * �@@0007�G�Y����������͑��݂��Ȃ�<BR>
     * �@@1001�F�����������ȊO<BR>
     * �@@1002�F������0�ȉ�<BR>
     * �@@1003�F�����̌�����8���𒴉�<BR>
     * �@@1004�F���P���������ȊO<BR>
     * �@@1005�F���P����0�ȉ�<BR>
     * �@@1006�F���P���̌�����8���𒴉�<BR>
     * �@@1007�F�]���P���������ȊO<BR>
     * �@@1008�F�]���P����0�ȉ�<BR>
     * �@@1009�F�]���P���̌�����8���𒴉�<BR>
     * �@@2001�F�w�肪�d���i�����R�[�h�A�s��R�[�h�A���P��������̖��ׂ���j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Error code<BR>
     * (0001 : Designated market is non-listing.<BR>
     *  0002 : No handling market<BR>
     *  0003 : No handling market in the priority market list. <BR>
     *  0004 : Get price acquisition error<BR>
     *  0005 : Brokerage acquisition error<BR>
     *  0006 : The corresponding product doesn't exist.<BR>
     *  0007 : The corresponding dealings product doesn't exist. <BR>
     *  1001 : orderQuantity is not a number<BR>
     *  1002 : orderQuantity is 0 or less<BR>
     *  1003 : orderQuantity has more than eight digits<BR>
     *  1004 : buyPrice is not a number<BR>
     *  1005 : buyPrice is 0 or less<BR>
     *  1006 : buyPrice has more than eight digits<BR>
     *  1007 : evaluationPrice is not a number<BR>
     *  1008 : evaluationPrice is 0 or less<BR>
     *  1009 : evaluationPrice has more than eight digits<BR>
     *  2001 : Specification overlaps�iproductCode�AmarketCode�AbuyPrice  The same
     * details exist. �j<BR>
     */
    public String errorCode;

    /**
     * @@roseuid 41C81B1A003F
     */
    public WEB3TrialCalcPortfolioDisplayProductUnit()
    {

    }
}
@
