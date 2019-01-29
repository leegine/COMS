head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.47.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTransUnitDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AioTransUnitDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ����� (���u) �V�K�쐬
                 : 2006/11/07 �����q (���u) �d�l�ύX No.679
Revesion History : 2008/09/22 ���u�� (���u) �d�l�ύX No.1000
*/
package webbroker3.aio.define;

/**
 * ���o�����׃��b�Z�[�W�̎���@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����
 * @@version 1.0
 */
public interface WEB3AioTransUnitDef
{
    /**
     * 0�F �o��  
     */
    public static final String CASH_OUT = "0";
    
    /**
     * 1�F ���o�� 
     */
    public static final String DUES_CASH_OUT = "1";

    /**
     * 2�F ��񗿏o�� 
     */
    public static final String INFO_CASN_OUT = "2";

    /**
     * 3�F ���� 
     */
    public static final String CASH_IN = "3";

    /**
     * 4�F �����A��
     */
    public static final String CASH_IN_CONTACT = "4";

    /**
     * 5�F �敨OP�؋����֏o��
     */
    public static final String FUTURE_OP_MARGIN_CASHOUT = "5";
    
    /**
     * 6�F �敨OP�؋����������
     */
    public static final String FUTURE_OP_MARGIN_TO_CASHIN = "6";
    
    /**
     * 7�F �M�p�ۏ؋��֏o��
     */
    public static final String MARGIN_GUARANTEE_CASHOUT = "7";
    
    /**
     * 8�F �M�p�ۏ؋��������
     */
    public static final String MARGIN_GUARANTEE_TO_CASHIN = "8";
    
    /**
     * 9�F FX�ۏ؋��֏o��
     */
    public static final String FX_CASHOUT = "9";
    
    /**
     * 10�F FX�ۏ؋��������
     */
    public static final String FX_TO_CASHIN = "10";
    
    /**
     * 11�F �������������֏o��
     */
    public static final String MIDIUM_TERM_GOV_EQUITY_ACCOUNT_CASHOUT = "11";
    
    /**
     * 12�F �������������������
     */
    public static final String MIDIUM_TERM_GOV_EQUITY_ACCOUNT_TO_CASHIN = "12";
    
    /**
     * 13�F ���̑��o��
     */
    public static final String OTHER_CASHOUT = "13";
    
    /**
     * 14�F ���̑�����
     */
    public static final String OTHER_CASHIN = "14";
    
    /**
     * 15:  �S�ۃ��[���ԍ�
     */
    public static final String SECURITY_LOAN_REPAY = "15";

    /**
     * 16:  �iCFD�����֏o���j
     */
    public static final String CFD_ACCOUNT_CASHOUT = "16";

    /**
     * 17:  �iCFD������������j
     */
    public static final String CFD_ACCOUNT_TO_CASHIN = "17";
}

@
