head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.12.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionCustomerSearchCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������ڋq����CSV(WEB3AdminTPPaymentRequisitionCustomerSearchCSV.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/06 ���z(���u) �V�K�쐬 ���f��No.027
Revision History : 2008/10/10 ���z(���u) �d�l�ύX ���f��No.028 029
*/

package webbroker3.tradingpoweradmin;

import webbroker3.common.define.WEB3AccountAttributeDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPAccountAttributeDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (���������ڋq����CSV)<BR>
 * <BR>
 * @@author ���z
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionCustomerSearchCSV extends WEB3GentradeCsvDataModel
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionCustomerSearchCSV.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@"���X�R�[�h"<BR>
     */
    public String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@"�ڋq�R�[�h"<BR>
     */
    public String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�ڋq�����x��)<BR>
     * �萔��`�v���p�e�B�@@"�ڋq��"<BR>
     */
    public String FAMILY_NAME_LABEL = "�ڋq��";

    /**
     * (���҃R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@"���҃R�[�h"<BR>
     */
    public String TRADER_CODE_LABEL = "���҃R�[�h";

    /**
     * (�������x��)<BR>
     * �萔��`�v���p�e�B�@@"����"<BR>
     */
    public String ATTRIBUTE_LABEL = "����";

    /**
     * (���֋����x��)<BR>
     * �萔��`�v���p�e�B�@@"���֋�"<BR>
     */
    public String DEBIT_AMOUNT_LABEL = "���֋�";

    /**
     * (���ʗ��֋����x��)<BR>
     * �萔��`�v���p�e�B�@@"���ʗ��֋�"<BR>
     */
    public String SPECIAL_DEBIT_AMOUNT_LABEL = "���ʗ��֋�";

    /**
     * (�K�v�����z���x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�v�����z"<BR>
     */
    public String REQUIRED_PAY_AMT_LABEL = "�K�v�����z";

    /**
     * (��ꐅ���Ǐ؋��z���x��)<BR>
     * �萔��`�v���p�e�B�@@"30������Ǐ؋��z"<BR>
     */
    public String FIRST_DEPOSIT_AMOUNT_LABEL = "30������Ǐ؋��z";

    /**
     * (��ꐅ���Ǐ،o�ߓ������x��)<BR>
     * �萔��`�v���p�e�B�@@"30������Ǐ،o�ߓ���"<BR>
     */
    public String FIRST_DEPOSIT_PASS_DAY_LABEL = "30������Ǐ،o�ߓ���";

    /**
     * (��񐅏��Ǐؐ����i1�j���x��)<BR>
     * �萔��`�v���p�e�B�@@"20������Ǐؐ����i1�j"<BR>
     */
    public String SECOND_DEPOSIT_1_LABEL = "20������Ǐؐ����i1�j";

    /**
     * (��񐅏��Ǐؐ����i2�j���x��)<BR>
     * �萔��`�v���p�e�B�@@"20������Ǐؐ����i2�j"<BR>
     */
    public String SECOND_DEPOSIT_2_LABEL = "20������Ǐؐ����i2�j";

    /**
     * (��񐅏��Ǐؖ��������x��)<BR>
     * �萔��`�v���p�e�B�@@"20������Ǐؖ�����"<BR>
     */
    public String SECOND_DEPOSIT_NON_PAY_LABEL = "20������Ǐؖ�����";

    /**
     * (���������ڋq����CSV)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@roseuid 48D201D603BB
     */
    public WEB3AdminTPPaymentRequisitionCustomerSearchCSV()
    {
        super();
        createColumnHeader();
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * �@@�@@�@@set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�ڋq�����x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���҃R�[�h���x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�������x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���֋����x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���ʗ��֋����x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�K�v�����z���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��ꐅ���Ǐ؋��z���x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��ꐅ���Ǐ،o�ߓ������x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��񐅏��Ǐؐ���(1)���x��<BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��񐅏��Ǐؐ���(2)���x��<BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��񐅏��Ǐؖ��������x��<BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * @@roseuid 48D200EF0148
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[13];

        //���X�R�[�h
        l_columnHeader[0] = new WEB3GentradeCsvColumnModel(
            BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�ڋq�R�[�h
        l_columnHeader[1] = new WEB3GentradeCsvColumnModel(
            ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�ڋq��
        l_columnHeader[2] = new WEB3GentradeCsvColumnModel(
            FAMILY_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //���҃R�[�h
        l_columnHeader[3] = new WEB3GentradeCsvColumnModel(
            TRADER_CODE_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //����
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(
            ATTRIBUTE_LABEL, 4, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //���֋�
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(
            DEBIT_AMOUNT_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //���ʗ��֋�
        l_columnHeader[6] = new WEB3GentradeCsvColumnModel(
            SPECIAL_DEBIT_AMOUNT_LABEL, 6, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //�K�v�����z
        l_columnHeader[7] = new WEB3GentradeCsvColumnModel(
            REQUIRED_PAY_AMT_LABEL, 7, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //��ꐅ���Ǐ؋��z
        l_columnHeader[8] = new WEB3GentradeCsvColumnModel(
            FIRST_DEPOSIT_AMOUNT_LABEL, 8, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //��ꐅ���Ǐ،o�ߓ���
        l_columnHeader[9] = new WEB3GentradeCsvColumnModel(
            FIRST_DEPOSIT_PASS_DAY_LABEL, 9, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //��񐅏��Ǐؐ����i1�j
        l_columnHeader[10] = new WEB3GentradeCsvColumnModel(
            SECOND_DEPOSIT_1_LABEL, 10, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //��񐅏��Ǐؐ����i2�j
        l_columnHeader[11] = new WEB3GentradeCsvColumnModel(
            SECOND_DEPOSIT_2_LABEL, 11, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //��񐅏��Ǐؖ�����
        l_columnHeader[12] = new WEB3GentradeCsvColumnModel(
            SECOND_DEPOSIT_NON_PAY_LABEL, 12, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        setColumnHeader(l_columnHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * �u���X�R�[�h�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j���X�R�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@roseuid 48D2187F03AE
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(BRANCH_CODE_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�R�[�h)<BR>
     * �u�ڋq�R�[�h�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�ڋq�R�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j�ڋq�R�[�h�̉��P�����������U���̒l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@roseuid 48D219E40262
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ACCOUNT_CODE_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strAccountCode.substring(0, 6));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq��)<BR>
     * �u�ڋq���v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�ڋq�����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j�ڋq��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strFamilyName - (�ڋq��)<BR>
     * @@roseuid 48D219EC03AB
     */
    public void setFamilyName(int l_intLineNumber, String l_strFamilyName)
    {
        final String STR_METHOD_NAME = " setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(FAMILY_NAME_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strFamilyName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���҃R�[�h)<BR>
     * �u���҃R�[�h�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���҃R�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j���҃R�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strTraderCode - (���҃R�[�h)<BR>
     * @@roseuid 48D219ED0011
     */
    public void setTraderCode(int l_intLineNumber, String l_strTraderCode)
    {
        final String STR_METHOD_NAME = "setTraderCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(TRADER_CODE_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strTraderCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set����)<BR>
     * �u�����v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�������x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F���������Ɉȉ��̏����Ŏ擾������������Z�b�g����B<BR>
     * �@@�@@�i�����j�ڋq���� = 0 �̏ꍇ�@@"�����i�O�����j"���Z�b�g�B<BR>
     * �@@�@@�i�����j�ڋq���� = 1 �̏ꍇ�@@"�����i�]�����j"���Z�b�g�B<BR>
     * �@@�@@�i�����j�ڋq���� = 2 �̏ꍇ�@@"�M�p"���Z�b�g�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strAccountAttribute - (�ڋq����)<BR>
     * @@roseuid 48D219F5031F
     */
    public void setAttribute(int l_intLineNumber, String l_strAccountAttribute)
    {
        final String STR_METHOD_NAME = "setAttribute(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ATTRIBUTE_LABEL);

        Object l_objValue = null;

        //�i�����j�ڋq���� = 0 �̏ꍇ
        if (WEB3AccountAttributeDef.EQUITY_NOT_ASSET_EVAL.equals(l_strAccountAttribute))
        {
            //"�����i�O�����j"���Z�b�g
            l_objValue = WEB3AdminTPAccountAttributeDef.EQUITY_NOT_ASSET_EVAL;
        }
        //�i�����j�ڋq���� = 1 �̏ꍇ
        else if (WEB3AccountAttributeDef.EQUITY_ASSET_EVAL.equals(l_strAccountAttribute))
        {
            //"�����i�]�����j"���Z�b�g
            l_objValue = WEB3AdminTPAccountAttributeDef.EQUITY_ASSET_EVAL;
        }
        //�i�����j�ڋq���� = 2 �̏ꍇ
        else if (WEB3AccountAttributeDef.MARGIN.equals(l_strAccountAttribute))
        {
            //"�M�p"���Z�b�g
            l_objValue = WEB3AdminTPAccountAttributeDef.MARGIN;
        }

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_objValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���֋�)<BR>
     * �u���֋��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���֋����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j���֋�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strDebitAmount - (���֋�)<BR>
     * @@roseuid 48D219F5035E
     */
    public void setDebitAmount(int l_intLineNumber, String l_strDebitAmount)
    {
        final String STR_METHOD_NAME = "setDebitAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(DEBIT_AMOUNT_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strDebitAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���ʗ��֋�)<BR>
     * �u���ʗ��֋��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.���ʗ��֋����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j���ʗ��֋�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strSpecialDebitAmount - (���ʗ��֋�)<BR>
     * @@roseuid 48D21B4F0349
     */
    public void setSpecialDebitAmount(int l_intLineNumber, String l_strSpecialDebitAmount)
    {
        final String STR_METHOD_NAME = "setSpecialDebitAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SPECIAL_DEBIT_AMOUNT_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strSpecialDebitAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�K�v�����z)<BR>
     * �u�K�v�����z�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.�K�v�����z���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j�a����s���z(T+0)<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strLackAccountBalance - (�a����s���z(T+0))<BR>
     * @@roseuid 48D219F5039C
     */
    public void setRequiredPayAmt(int l_intLineNumber, String l_strLackAccountBalance)
    {
        final String STR_METHOD_NAME = "setRequiredPayAmt(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(REQUIRED_PAY_AMT_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strLackAccountBalance);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��ꐅ���Ǐ؋��z)<BR>
     * �u��ꐅ���Ǐ؋��z�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��ꐅ���Ǐ؋��z���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j��ꐅ���Ǐ؋��z<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strFirstDepositAmount - (��ꐅ���Ǐ؋��z)<BR>
     * @@roseuid 48D219F503CB
     */
    public void setFirstDepositAmount(int l_intLineNumber, String l_strFirstDepositAmount)
    {
        final String STR_METHOD_NAME = "setFirstDepositAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(FIRST_DEPOSIT_AMOUNT_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strFirstDepositAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��ꐅ���Ǐ،o�ߓ���)<BR>
     * �u��ꐅ���Ǐ،o�ߓ����v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��ꐅ���Ǐ،o�ߓ������x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j��ꐅ���Ǐ،o�ߓ���<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strFirstDepositPassDay - (��ꐅ���Ǐ،o�ߓ���)<BR>
     * @@roseuid 48D21C57034E
     */
    public void setFirstDepositPassDay(int l_intLineNumber, String l_strFirstDepositPassDay)
    {
        final String STR_METHOD_NAME = "setFirstDepositPassDay(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(FIRST_DEPOSIT_PASS_DAY_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strFirstDepositPassDay);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��񐅏��Ǐؐ����i1�j)<BR>
     * �u��񐅏��Ǐؐ����i1�j�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��񐅏��Ǐؐ����i1�j���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j��񐅏��Ǐؐ����i1�j<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strSecondDeposit1 - (��񐅏��Ǐؐ����i1�j)<BR>
     * @@roseuid 48D21C59008F
     */
    public void setSecondDeposit1(int l_intLineNumber, String l_strSecondDeposit1)
    {
        final String STR_METHOD_NAME = "setSecondDeposit1(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SECOND_DEPOSIT_1_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strSecondDeposit1);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��񐅏��Ǐؐ����i2�j)<BR>
     * �u��񐅏��Ǐؐ����i2�j�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��񐅏��Ǐؐ����i2�j���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j��񐅏��Ǐؐ����i2�j<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strSecondDeposit2 - (��񐅏��Ǐؐ����i2�j)<BR>
     * @@roseuid 48D21C930084
     */
    public void setSecondDeposit2(int l_intLineNumber, String l_strSecondDeposit2)
    {
        final String STR_METHOD_NAME = "setSecondDeposit2(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SECOND_DEPOSIT_2_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strSecondDeposit2);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��񐅏��Ǐؖ�����)<BR>
     * �u��񐅏��Ǐؖ������v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���������ڋq����CSV.��񐅏��Ǐؖ��������x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�i�����j�s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�i�����j��񐅏��Ǐؖ�����<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_strSecondDepositNonPay - (��񐅏��Ǐؖ�����)<BR>
     * @@roseuid 48D21C9402C6
     */
    public void setSecondDepositNonPay(int l_intLineNumber, String l_strSecondDepositNonPay)
    {
        final String STR_METHOD_NAME = "setSecondDepositNonPay(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(SECOND_DEPOSIT_NON_PAY_LABEL);

        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strSecondDepositNonPay);

        log.exiting(STR_METHOD_NAME);
    }
}
@
