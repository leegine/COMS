head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.01.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OrderAccProductDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������t���i �萔��`�C���^�t�F�C�X(WEB3OrderAccProductDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)�@@�V�K�쐬
Revesion History : 2004/12/17 �X��    (SRA)    �@@�萔�ǉ�
Revesion History : 2006/04/27 ������(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo368
Revesion History : 2006/10/19 �h�C(���u) ���ʎd�l�ύX�Ǘ��䒠�E�c�a���C�A�E�gNo436
Revesion History : 2008/9/24 ������ (���u) �y���ʁz�d�l�ύX�Ǘ��䒠 �c�a���C�A�E�gNo.653
*/
package webbroker3.common.define;

/**
 * ������t���i(������t�X�e�C�^�X�e�[�u��)�@@�萔��`�C���^�t�F�C�X
 *                                                                      
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3OrderAccProductDef
{
    /**
     * 01 : ����
     */
    public static final String STOCK = "01";

    /**
     * 02 : �����~�j����
     */
    public static final String MINI_STOCK = "02";

    /**
     * 03 : �M�p���
     */
    public static final String MARGIN = "03";

    /**
     * 04 : �O����
     */
    public static final String FOREIGN_STOCK = "04";

    /**
     * 05 : �敨
     */
    public static final String FUTURE = "05";

    /**
     * 06 : �I�v�V����
     */
    public static final String OPTION = "06";

    /**
     * 07 : �����M��
     */
    public static final String MUTUAL_FUND = "07";

    /**
     * 08 : �ݐϓ���
     */
    public static final String MONTHLY_INVESTMENT_PLAN = "08";

    /**
     * 09 : �M�p�ۏ؋��ւ̐U��
     */
    public static final String MARGIN_GUARANTEE_MONEY_TRANSFER = "09";

    /**
     * 10 : �M�p�ۏ؋�����̐U��
     */
    public static final String MARGIN_GUARANTEE_MONEY_TRANSFER_FROM = "10";

    /**
     * 11 : �،��U�ցA�o�ɒʒm
     */
    public static final String TRASUT_SUBSTITUTION_CHANGE = "11";

    /**
     * 12 : ��OP�ۏ؋��ւ̐U��
     */
    public static final String FUTURE_OP_GUARANTEE_MONEY_CHANGE = "12";

    /**
     * 13 : ��OP�؋�������̐U��
     */
    public static final String FUTURE_OP_GUARANTEE_MONEY_CHANGE_FROM = "13";

    /**
     * 14 : ���o��
     */
    public static final String PAYMENT = "14";

    /**
     * 20 : IPO
     */
    public static final String IPO = "20";

    /**
     * 21 : �T�[�r�X���p
     */
    public static final String SRV_REGI = "21";

    /**
     * 22 : �ڋq�T�[�r�X
     */
    public static final String ACCOUNT_SERVICE = "22";

    /**
     * 23 : �ב֕ۏ؋�
     */
    public static final String EXCHANGE_GUARANTEE = "23";

    /**
     * 24 : �|�C���g�V�X�e��
     */
    public static final String POINT_SYSTEM = "24";

    /**
     * 25 : ����O����
     */
    public static final String SALES_OUTSIDE_MARKET = "25";

    /**
     * 26 : �A���Ǘ�
     */
    public static final String INFORM_MANAGEMENT = "26"; 
    
    /**
     * 27 : �O�������U�֘A�g
     */
    public static final String FEQ_CON = "27";
    
    /**
     * 28 : ��
     */
    public static final String BOND = "28";
    
    /**
     * 29 : �V���O���T�C���I��
     */
    public static final String SINGLE_SIGN_ON = "29";
    
    /**
     * 30�F�A������
     */
    public static final String RESERVE_ORDER = "30";
    
    /**
     * 31: �؋�������
     */
    public static final String DEPOSIT_TRANSITION = "31";

    /**
     * 31: �����ʒm�ď���
     */
    public static final String CASHIN_NOTICE_REDEALING = "31";

    /**
     * 32�F�]��
     */
    public static final String TRADING_POWER = "32";

    /**
     * 33�F�R���v���C�A���X
     */
    public static final String COMPLIANCE = "33";

    /**
     * 40�FCFD
     */
    public static final String CFD = "40";
}
@
