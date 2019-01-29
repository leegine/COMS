head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioEditProductInputUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW�������͖���(WEB3TrialCalcPortfolioEditProductInputUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�ҏW�������͖��ׁj<BR>
 * <BR>
 * �v�Z�T�[�r�X�|�[�g�t�H���I�ҏW�������͖��ׁB<BR>
 * <BR>
 * WEB3TrialCalcPortfolioEditProductInputUnit<BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcPortfolioEditProductInputUnit extends Message
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
     * �i�����j<BR>
     * <BR>
     * orderQuantity<BR>
     */
    public String orderQuantity;

    /**
     * �i���P���j<BR>
     * <BR>
     * ���P���B<BR>
     * �inull�w��j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * buyPrice. (Null can be specified)<BR>
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
     */
    public String marketCode;

    /**
     * �s��R�[�h�̈ꗗ�B<BR>
     * �i1�F�����@@2�F���@@3�F���É��@@6�F�����@@8�F�D�y�@@9�FNNM�@@10�FJASDAQ�j<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Market list<BR>
     * (1 : TOKYO  2 : OSAKA  3 : NAGOYA  6 : FUKUOKA  7 : SAPPORO<BR>
     *  9 : NNM    10 : JASDAQ)<BR>
     * <BR>
     */
    public String[] marketCodeList;

    /**
     * @@roseuid 41C81B22031D
     */
    public WEB3TrialCalcPortfolioEditProductInputUnit()
    {

    }
}
@
