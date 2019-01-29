head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.29.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ErrorReasonCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3ErrorReasonCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 li-yunfeng(sinocom)�@@�V�K�쐬
Revesion History : 2004/10/09 �Г�(sinocom)�@@��`�ǉ�
Revesion History : 2006/5/25 ������ (���u) �C���^�[�t�F�C�X�\���ENo079
Revesion History : 2006/07/12 �h�C (���u)�y�敨�I�v�V�����z�d�l�ύX�E�c�a���C�A�E�gNo.046
Revesion History : 2007/01/26 �h�C (���u)�y�敨�I�v�V�����z�d�l�ύX�E�c�a���C�A�E�gNo.067
Revesion History : 2007/04/23 �h�C (���u)�y�����z�d�l�ύX�E�c�a���C�A�E�gNo.138
*/
package webbroker3.common.define;

/**
 * �����G���[���R�R�[�h�@@�萔��`�C���^�t�F�C�X
 *
 * @@author li-yunfeng
 * @@version 1.0
 */
public interface WEB3ErrorReasonCodeDef
{
    /**
     * 0000 : ����
     */
    public static final String NORMAL = "0000";

    /**
     * 1001 : ��t�G���[
     */
    public static final String ACCEPT_ERROR = "1001";

    /**
     * 1002 : �����G���[
     */
    public static final String CHANGE_ERROR = "1002";

    /**
     * 1003 : ����G���[
     */
    public static final String CANCEL_ERROR = "1003";

    /**
     * 1004 : �ؑփG���[
     */
    public static final String TRANSFER_ERROR = "1004";

    /**
     * 0001 : �l���G���[
     */
    public static final String PRICE_RANGE_ERROR = "0001";

    /**
     * 0002 : �a����s���G���[
     */
    public static final String DEPOSIT_MONEY_SHORT_ERROR = "0002";

    /**
     * 0003 : �c���s���G���[�i�����A�w���I�v�V�����j
     */
    public static final String POSITION_SHORT_ERROR = "0003";

    /**
     * 0004 : �ۏ؋��s���G���[
     */
    public static final String GUARANTY_MONEY_SHORT_ERROR = "0004";

    /**
     * 0005 : �����c���s���G���[
     */
    public static final String OPEN_INTERSET_SHORT_ERROR = "0005";

    /**
     * 0006 : ������~�����G���[
     */
    public static final String TRADE_STOP_PRODUCT_ERROR = "0006";

    /**
     * 0007 : �s��ύX�����G���[
     */
    public static final String MARKET_CHANGE_PRODUCT_ERROR = "0007";

    /**
     * 0008 : ���t�]�̓G���[
     */
    public static final String TRADE_POWER_ERROR = "0008";

    /**
     * 0009 : ���t�\���ʃG���[
     */
    public static final String SELL_QUANTITY_ERROR = "0009";

    /**
     * 0010 : ��������G���[
     */
    public static final String SPEC_ACCOUNT_ERROR = "0010";

    /**
     * 0011 : �o����~�����G���[
     */
    public static final String CARRY_OVER_SKIP_PRODUCT_ERROR = "0011";

    /**
     * 0012 : ��K���`�F�b�N�G���[
     */
    public static final String MARGIN_SEC_ERROR = "0012";

    /**
     * 0013�F�������G���[
     */
    public static final String BIZ_DATE_ERROR = "0013";

    /**
     * 0014�F�Ēl�`�F�b�N�G���[
     */
    public static final String TICK_VALUE_ERROR = "0014";

    /**
     * 0015�F�󔄂�`�F�b�N�G���[
     */
    public static final String SHORT_SELLING_ERROR = "0015";

    /**
     * 0016�F���ϊ��������σG���[
     */
    public static final String SETTLEDAY_CAME_ERROR = "0016";

    /**
     * 0017�F�����E���n�����o�^�σG���[
     */
    public static final String SWAP_MARGIN_REGISTED_ERROR = "0017";

    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * 1400 : �f�h�W�O�P'�ȊO�̂c�`�s�` �b�n�c�d �d�q�q
     */
    public static final String NOT_GI801_DATA_CODE_ERROR = "1400";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * 2200 : ���X�R�[�h�Y���Ȃ��d�q�q
     */
    public static final String NO_BRANCH_CODE_ERROR = "2200";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * 1200 : �ڋq�o�^�t�@@�C�� �c�h�r�j �d�q�q
     *        �c���E�����`�[�t�@@�C�� �c�h�r�j �d�q�q
     *        �ڋq���Z�t�@@�C�� �c�h�r�j �d�q�q
     *        �����U�֓o�^�t�@@�C�� �c�h�r�j �d�q�q
     *        �A���T�[�����`�[�t�@@�C�� �c�h�r�j �d�q�q
     */
    public static final String FILE_DISK_ERROR = "1200";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * 9500 : �c���Ȃ� �d�q�q
     */
    public static final String NO_BALANCE_ERROR = "9500";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * C100 : �c���I�[�o�[ �d�q�q
     */
    public static final String BALANCE_OVER_ERROR = "C100";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * FFFF : �a�菑������`�F�b�N�ɂ�鋑�ۓd�� �d�q�q
     */
    public static final String TELEGRAM_REJECT_ERROR = "FFFF";
    
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * FF69 : ���b�N�q �d�q�q
     */
    public static final String LOCK_CUSTOMER_ERROR = "FF69";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * D600 : �U���� �d�q�q
     */
    public static final String DEPOSIT_FROM_ERROR = "D600";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * 9100 : �n�`�W�W�O�x�X�O �̂j�d�x �b�n�c�d �d�q�q
     */
    public static final String QA880Y90_KEY_CODE_ERROR = "9100";
 
    /**
     * (���o�������P�ʃe�[�u���̒����G���[���R�R�[�h)
     * FF4E : �q�Q�Q�P �q�d�o�n�q�s �� �[�� �d�q�q
     */
    public static final String R221_REPORT_END_ERROR = "FF4E";

    /**
      * 9001 : ���̑��G���[
      */
    public static final String OTHRE_ERROR = "9001";

    /**
     * W001�F���Ǘ��Ҏ蓮������
     */
    public static final String EQUITY_ADMIN_MANUAL_EXPIRED = "W001";

    /**
     * W002�F�g���K�[�����Ǘ��Ҏ蓮������
     */
    public static final String TRIGGER_ADMIN_MANUAL_EXPIRED = "W002";

    /**
     * W004�F�敨OP�Ǘ��Ҏ蓮������
     */
    public static final String FUTURE_OP_ADMIN_MANUAL_EXPIRED = "W004";
}
@
