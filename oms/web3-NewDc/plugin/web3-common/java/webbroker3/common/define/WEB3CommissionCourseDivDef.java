head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CommissionCourseDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔���R�[�X�R�[�h(WEB3CommissionCourseDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/05 ����� (���u) �V�K�쐬
Revision History : 2008/08/20 ��іQ(���u) �c�a���C�A�E�gNo.640
*/

package webbroker3.common.define;

/**
 * �萔���R�[�X�R�[�h �萔��`�C���^�t�F�C�X
 *
 * @@author �����
 * @@version 1.0
 */
public interface WEB3CommissionCourseDivDef
{

    /**
     * 02�F�藦�萔���i�X�^���_�[�h�j<BR>
     * �i����1���������{�M�p1���������@@�����e���̂݁j<BR>
     */
    public final static String FIXED_RATE_COMMISSION_STANDARD  = "02";   

    /**
     * 03�F��������v<BR>
     * �i����1�����v�{�M�p1�����v�@@�����e���̂݁j<BR>
     */
    public final static String EXECUTED_TURNOVER_COUNT = "03";

    /**
     * 04�F����
     */
    public final static String EXECUTED_TIMES = "04";

    /**
     * 05�F�����z��
     */
    public final static String FIXED_AMOUNT = "05";
    
    /**
     * 06�F���z�{�b�N�X
     */
    public final static String SMALL_AMOUNT_BOX = "06";

    /**
     * 07�F�@@����1�����v�{�M�p1��������
     */
    public final static String EQUITY_ONE_DAY_TOTAL_ADD_MARGIN_ONE_DAY_ORDER = "07";
    
    /**
     * 08�F�@@����1���������{�M�p1�����v
     */
    public final static String EQUITY_ONE_DAY_ORDER_ADD_MARGIN_ONE_DAY_TOTAL = "08";
    
    /**
     * 16�F���z�{�b�N�X�i�L�����y�[���j
     */
    public final static String SMALL_AMOUNT_BOX_CAMPAIGN = "16";

    /**
     * 99�F�@@��L�ȊO�i���e���E���̂݁j
     */
    public final static String OTHER = "99";
}@
