head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioErrorProductUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�G���[��������(WEB3TrialCalcPortfolioErrorProductUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�G���[�������ׁj<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�G���[�������ׁB<BR>
 * <BR>
 * WEB3TrialCalcPortfolioErrorProductUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 * <BR>
 */
public class WEB3TrialCalcPortfolioErrorProductUnit extends Message
{
    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * Product Code<BR>
     */
    public String productCode;

    /**
     * �i�������j<BR>
     * <BR>
     * Product Name<BR>
     */
    public String productName;

    /**
     * �i�����j<BR>
     * <BR>
     * Order Quantity<BR>
     */
    public String orderQuantity;

    /**
     * �i���P���j<BR>
     * �inull�w��j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Buy price.(Null can be specified.)<BR>
     * <BR>
     */
    public String buyPrice;

    /**
     * �s��R�[�h�B<BR>
     * �i1�F�����@@2�F���@@3�F���É��@@6�F�����@@8�F�D�y�@@9�FNNM�@@10�FJASDAQ�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market code<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  7 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     * <BR>
     */
    public String marketCode;

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
     * @@roseuid 41C81B36009D
     */
    public WEB3TrialCalcPortfolioErrorProductUnit()
    {

    }
}
@
