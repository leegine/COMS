head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoAccEstablishDownLoadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �V�K�����J�݃_�E�����[�hCSV(WEB3AdminAccInfoAccEstablishDownLoadCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/09 �����q (���u) �V�K�쐬
                 : 2006/11/24 ���� (���u) ���f�� No.147�ANo.148
*/

package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV)<BR>
 * �V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�t�@@�C���_�E�����[�h�ō쐬����CSV�t�@@�C���f�[�^�N���X <BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */

public class WEB3AdminAccInfoAccEstablishDownLoadCSV extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoAccEstablishDownLoadCSV.class);

    /**
     * (�f�[�^���e�ԍ����x��)<BR>
     * �f�[�^���e�ԍ����x��<BR>
     */
    public static  final String dataContentDiv = "�f�[�^���e�ԍ�";

    /**
     * (��ЃR�[�h)<BR>
     * ��ЃR�[�h<BR>
     */
    public static  final String institutionCode = "��ЃR�[�h";

    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    public static  final String branchCode = "���X�R�[�h";

    /**
     * (���҃R�[�h���x��)<BR>
     * ���҃R�[�h���x��<BR>
     */
    public static  final String tradeCode = "���҃R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    public static  final String accountCode = "�ڋq�R�[�h";

    /**
     * (������ʃ��x��)<BR>
     * ������ʃ��x��<BR>
     */
    public static  final String accountType = "�������";

    /**
     * (�ڋq���i�����j���x��)<BR>
     * �ڋq���i�����j���x��<BR>
     */
    public static  final String accountName = "�ڋq���i�����j";

    /**
     * (�ڋq���i�J�i�j���x��)<BR>
     * �ڋq���i�J�i�j���x��<BR>
     */
    public static  final String accountNameKana = "�ڋq���i�J�i�j";

    /**
     * (�����J�ݓ����x��)<BR>
     * �����J�ݓ����x��<BR>
     */
    public static  final String accountOpenDate = "�����J�ݓ�";

    /**
     * (�������s���x��)<BR>
     * �������s���x��<BR>
     */
    public static  final String payFinancialInstitution = "�������s";

    /**
     * (��s�x�X�����x��)<BR>
     * ��s�x�X�����x��<BR>
     */
    public static  final String financialBranchName = "��s�x�X��";

    /**
     * (�Ȗڃ��x��)<BR>
     * �Ȗڃ��x��<BR>
     */
    public static  final String item = "�Ȗ�";

    /**
     * (��s�ԍ����x��)<BR>
     * ��s�ԍ����x��<BR>
     */
    public static  final String financialInstitutionNumber = "��s�ԍ�";

    /**
     * (��s�x�X�ԍ����x��)<BR>
     * ��s�x�X�ԍ����x��<BR>
     */
    public static  final String financialBranchCode = "��s�x�X�ԍ�";

    /**
     * (��s�����ԍ����x��)<BR>
     * ��s�����ԍ����x��<BR>
     */
    public static  final String financialAccountCode = "��s�����ԍ�";

    /**
     * (�ڋq�Z���i�X�֔ԍ��j���x��)<BR>
     * �ڋq�Z���i�X�֔ԍ��j���x��<BR>
     */
    public static  final String zipCode = "�ڋq�Z���i�X�֔ԍ��j";

    /**
     * (�ڋq�Z���i�Z��1�j���x��)<BR>
     * �ڋq�Z���i�Z��1�j���x��<BR>
     */
    public static  final String address1 = "�ڋq�Z���i�Z��1�j";

    /**
     * (�ڋq�Z���i�Z��2�j���x��)<BR>
     * �ڋq�Z���i�Z��2�j���x��<BR>
     */
    public static  final String address2 = "�ڋq�Z���i�Z��2�j";

    /**
     * (�ڋq�Z���i�Z��3�j���x��)<BR>
     * �ڋq�Z���i�Z��3�j���x��<BR>
     */
    public static  final String address3 = "�ڋq�Z���i�Z��3�j";
    
    /**
     * (�d�b�ԍ����x��)<BR>
     * �d�b�ԍ����x��<BR>
     */
    public static  final String telephone = "�d�b�ԍ�";
    
    /**
     * (���X�����x��)<BR>
     * ���X�����x��<BR>
     */
    public static  final String branchName = "���X��";

    /**
     * (�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV ())<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�E super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�E create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�E create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public  WEB3AdminAccInfoAccEstablishDownLoadCSV()
    {
        //�C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B
        super();
        this.createKeysHeader();
        this.createColumnHeader();
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�f�[�^���e�ԍ����x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��ЃR�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���҃R�[�h���x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.������ʃ��x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�����j���x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�J�i�j���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�����J�ݓ����x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/����/dd") <BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�������s���x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�����x��<BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�Ȗڃ��x��<BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�ԍ����x��<BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 13<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�ԍ����x��<BR>
     * �@@�J�����ԍ��F 13<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 14<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�����ԍ����x��<BR>
     * �@@�J�����ԍ��F 14<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 15<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�X�֔ԍ��j���x��<BR>
     * �@@�J�����ԍ��F 15<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 16<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��1�j���x��<BR>
     * �@@�J�����ԍ��F 16<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 17<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��2�j���x��<BR>
     * �@@�J�����ԍ��F 17<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 18<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��3�j���x��<BR>
     * �@@�J�����ԍ��F 18<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 19<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�d�b�ԍ����x��<BR>
     * �@@�J�����ԍ��F 19<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 20<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���<BR>
     * �@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�����x��<BR>
     * �@@�J�����ԍ��F 20<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 21;
        this.columnHeader = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        //[�J�����w�b�_�z��]
        //�|�@@index = 0
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�f�[�^���e�ԍ����x��
        //�@@�J�����ԍ��F 0
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.dataContentDiv,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 1
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��ЃR�[�h���x��
        //�@@�J�����ԍ��F 1
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[1] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.institutionCode,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 2
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�R�[�h���x��
        //�@@�J�����ԍ��F 2
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[2] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.branchCode,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 3
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���҃R�[�h���x��
        //�@@�J�����ԍ��F 3
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[3] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.tradeCode,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 4
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�R�[�h���x��
        //�@@�J�����ԍ��F 4
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[4] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountCode,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 5
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.������ʃ��x��
        //�@@�J�����ԍ��F 5
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[5] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountType,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 6
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�����j���x��
        //�@@�J�����ԍ��F 6
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[6] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountName,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 7
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�J�i�j���x��
        //�@@�J�����ԍ��F 7
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[7] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountNameKana,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 8
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�����J�ݓ����x��
        //�@@�J�����ԍ��F 8
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����
        //�@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/����/dd")
        SimpleDateFormat l_dataFormat = new SimpleDateFormat("yyyy/����/dd");
        this.columnHeader[8] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.accountOpenDate,
            8,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            l_dataFormat);

        //�|�@@index = 9
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�������s���x��
        //�@@�J�����ԍ��F 9
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[9] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.payFinancialInstitution,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 10
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�����x��
        //�@@�J�����ԍ��F 10
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[10] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchName,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 11
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�Ȗڃ��x��
        //�@@�J�����ԍ��F 11
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[11] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.item,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 12
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�ԍ����x��
        //�@@�J�����ԍ��F 12
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[12] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialInstitutionNumber,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 13
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�ԍ����x��
        //�@@�J�����ԍ��F 13
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[13] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchCode,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 14
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�����ԍ����x��
        //�@@�J�����ԍ��F 14
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[14] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.financialAccountCode,
            14,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 15
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�X�֔ԍ��j���x��
        //�@@�J�����ԍ��F 15
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[15] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.zipCode,
            15,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 16
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��1�j���x��
        //�@@�J�����ԍ��F 16
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[16] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.address1,
            16,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 17
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��2�j���x��
        //�@@�J�����ԍ��F 17
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[17] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.address2,
            17,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 18
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��3�j���x��
        //�@@�J�����ԍ��F 18
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[18] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.address3,
            18,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //�|�@@index = 19
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�d�b�ԍ����x��
        //�@@�J�����ԍ��F 19
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[19] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.telephone,
            19,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //�|�@@index = 20
        //�@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�����x��
        //�@@�J�����ԍ��F 20
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[20] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAccInfoAccEstablishDownLoadCSV.branchName,
            20,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@���ݓ���(*1)��"yyyy/MM/dd HH:mm:ss"�̌`����format����������B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void createKeysHeader()
    {
        final String STR_METHOD_NAME = " createKeysHeader()";
        log.entering(STR_METHOD_NAME);

        String[] l_strSetKeyHeader = new String[1];
        Timestamp l_systemTimestamp = GtlUtils.getSystemTimestamp();

        //���ݓ���(*1)��"yyyy/MM/dd HH:mm:ss"�̌`����format����������B
        l_strSetKeyHeader[0] =
            WEB3DateUtility.formatDate(l_systemTimestamp, "yyyy/MM/dd HH:mm:ss");

        //�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        this.setKeyHeader(l_strSetKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�f�[�^���e�ԍ�)<BR>
     * �u�f�[�^���e�ԍ��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�f�[�^���e�ԍ����x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�f�[�^���e�ԍ�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strDataContentDiv - (�f�[�^���e�ԍ�)<BR>
     * �f�[�^���e�ԍ�
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setDataContentDiv(int l_intLineNumber, String l_strDataContentDiv)
    {
        final String STR_METHOD_NAME = " setDataContentDiv(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�f�[�^���e�ԍ����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.dataContentDiv);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�f�[�^���e�ԍ�
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strDataContentDiv);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��ЃR�[�h)<BR>
     * �u��ЃR�[�h�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��ЃR�[�h���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.��ЃR�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setInstitutionCode(int l_intLineNumber, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��ЃR�[�h���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.institutionCode);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�،���ЃR�[�h
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strInstitutionCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * �u���X�R�[�h�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�R�[�h���e�ԍ����x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.���X�R�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = " setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�R�[�h���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.branchCode);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.���X�R�[�h
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���҃R�[�h)<BR>
     * �u���҃R�[�h�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���҃R�[�h���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.���҃R�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strTraderCode - (���҃R�[�h)<BR>
     * ���҃R�[�h
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setTraderCode(int l_intLineNumber, String l_strTraderCode)
    {
        final String STR_METHOD_NAME = " setTraderCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E���O�C�����b�N�ڋq�_�E�����[�hCSV.���҃R�[�h���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.tradeCode);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.���҃R�[�h
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strTraderCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�R�[�h)<BR>
     * �u�ڋq�R�[�h �v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�R�[�h ���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *�@@�@@  �l�F�@@�@@�@@����.�����R�[�h��ҏW�����l�i*1) <BR>
     * <BR>
     *�i*1�j �o�͗p�Ɂu����.�ڋq�R�[�h�v��ҏW<BR>
     *�@@�E����.�����R�[�h�i7���j��1���ځ`6���ڂ𔲏o���B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�R�[�h���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountCode);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�����R�[�h��ҏW�����l�i*1�j
        //�i*1�j �o�͗p�Ɂu����.�ڋq�R�[�h�v��ҏW
        //�E����.�����R�[�h�i7���j��1���ځ`6���ڂ𔲏o���B
        String l_strNewAccountCode = null;
        if (l_strAccountCode != null)
        {
            l_strNewAccountCode = l_strAccountCode.substring(0, 6);
        }

        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strNewAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������)<BR>
     * �u������ʁv���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.������ʃ��x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�����^�C�v<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strAccountType - (�����^�C�v)<BR>
     * �����^�C�v
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountTypeCode(int l_intLineNumber, String l_strAccountType)
    {
        final String STR_METHOD_NAME = " setAccountTypeCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.������ʃ��x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountType);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�����^�C�v
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAccountType);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq��(�����j)<BR>
     * �u�ڋq��(�����j�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�����j���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.���O(�c��)<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFamilyName - (���O(�c���j)<BR>
     * ���O(�c���j
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountName(int l_intLineNumber, String l_strFamilyName)
    {
        final String STR_METHOD_NAME = " setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�����j���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountName);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.���O(�c���j
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFamilyName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq��(�J�i))<BR>
     * �u�ڋq��(�J�i)�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�J�i)���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.���O�i�c���P�j<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFamilyNameAlt1 - ���O�i�c���P�j<BR>
     * ���O�i�c���P�j
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountNameKana(int l_intLineNumber, String l_strFamilyNameAlt1)
    {
        final String STR_METHOD_NAME = " setAccountNameKana(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq��(�J�i�j���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountNameKana);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.���O�i�c���P�j
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFamilyNameAlt1);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����J�ݓ�)<BR>
     * �u�����J�ݓ��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�����J�ݓ����x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�����o�^��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strAccountOpenDate - (�����o�^��)<BR>
     * �����o�^��
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAccountOpenDate(int l_intLineNumber, String l_strAccountOpenDate)
    {
        final String STR_METHOD_NAME = " setAccountOpenDate(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�����J�ݓ����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.accountOpenDate);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�����o�^��
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAccountOpenDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�������s)<BR>
     * �u�������s�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�������s���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.��s��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFinInstitutionName - (��s��)<BR>
     * ��s��
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setPayFinancialInstitution(int l_intLineNumber, String l_strFinInstitutionName)
    {
        final String STR_METHOD_NAME = " setPayFinancialInstitution(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�������s���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.payFinancialInstitution);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.��s��
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinInstitutionName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��s�x�X��)<BR>
     * �u��s�x�X���v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�����x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�x�X��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFinancialBranchName - (�x�X��)<BR>
     * �x�X��
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialBranchName(int l_intLineNumber, String l_strFinancialBranchName)
    {
        final String STR_METHOD_NAME = " setFinancialBranchName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchName);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�x�X��
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinancialBranchName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�Ȗ�)<BR>
     * �u�Ȗځv���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�Ȗڃ��x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.������ޖ�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strItem - (�Ȗ�)<BR>
     * �Ȗ�
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setItem(int l_intLineNumber, String l_strItem)
    {
        final String STR_METHOD_NAME = " setItem(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�Ȗڃ��x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.item);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.������ޖ�
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strItem);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��s�ԍ�)<BR>
     * �u��s�ԍ��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�ԍ����x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.��s�R�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFinInstitutionCode - (��s�R�[�h)<BR>
     * ��s�R�[�h
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialInstitutionNumber(int l_intLineNumber, String l_strFinInstitutionCode)
    {
        final String STR_METHOD_NAME = " setFinancialInstitutionNumber(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�ԍ����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialInstitutionNumber);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.��s�R�[�h
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinInstitutionCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��s�x�X�ԍ�)<BR>
     * �u��s�x�X�ԍ��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�ԍ����x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�x�X�R�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFinancialBranchCode - (�x�X�R�[�h)<BR>
     * �x�X�R�[�h
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialBranchCode(int l_intLineNumber, String l_strFinancialBranchCode)
    {
        final String STR_METHOD_NAME = " setFinancialBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�x�X�ԍ����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialBranchCode);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�x�X�R�[�h
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinancialBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��s�����ԍ�)<BR>
     * �u��s�����ԍ��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�����ԍ����x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�����ԍ�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFinancialAccountCode - (�����ԍ�)<BR>
     * �����ԍ�
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setFinancialAccountCode(int l_intLineNumber, String l_strFinancialAccountCode)
    {
        final String STR_METHOD_NAME = " setFinancialAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.��s�����ԍ����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.financialAccountCode);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�����ԍ�
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strFinancialAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�Z���i�X�֔ԍ��j)<BR>
     * �u�ڋq�Z���i�X�֔ԍ��j�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�X�֔ԍ��j���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�X�֔ԍ�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strZipCode - (�X�֔ԍ�)<BR>
     * �X�֔ԍ�
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setZipCode(int l_intLineNumber, String l_strZipCode)
    {
        final String STR_METHOD_NAME = " setZipCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�X�֔ԍ��j���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.zipCode);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�X�֔ԍ�
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strZipCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�Z���i�Z��1�j)<BR>
     * �u�ڋq�Z���i�Z��1�j�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��1�j���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�Z��1<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strAddressLine1 - (�Z��1)<BR>
     * �Z��1
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAddress1(int l_intLineNumber, String l_strAddressLine1)
    {
        final String STR_METHOD_NAME = " setAddress1(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��1�j���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.address1);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�Z��1
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAddressLine1);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�Z���i�Z��2�j)<BR>
     * �u�ڋq�Z���i�Z��2�j�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��2�j���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�Z��2<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strAddressLine2 - (�Z��2)<BR>
     * �Z��2
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAddress2(int l_intLineNumber, String l_strAddressLine2)
    {
        final String STR_METHOD_NAME = " setAddress2(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��2�j���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.address2);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�Z��2
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAddressLine2);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�Z���i�Z��3�j)<BR>
     * �u�ڋq�Z���i�Z��3�j�v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *  �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     *  �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��3�j���x��<BR>
     * <BR>
     *  �Q�j�@@�l�Z�b�g<BR>
     *  �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *  �@@[set���ڒl()�Ɏw�肷�����]<BR>
     *  �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     *  �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     *  �@@�@@�l�F�@@����.�Z��3<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strAddressLine3 - (�Z��3)<BR>
     * �Z��3
     * <BR>
     * @@roseuid 4147E7B6025D
     */
    public void setAddress3(int l_intLineNumber, String l_strAddressLine3)
    {
        final String STR_METHOD_NAME = " setAddress3(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�ڋq�Z���i�Z��3�j���x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.address3);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�Z��3
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strAddressLine3);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�d�b�ԍ�)<BR>
     * �u�d�b�ԍ��v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�d�b�ԍ����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F�@@�@@�@@����.�d�b�ԍ�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strTelephone - (�d�b�ԍ�)<BR>
     * �d�b�ԍ�
     */
    public void setTelephone(int l_intLineNumber, String l_strTelephone)
    {
        final String STR_METHOD_NAME = " setTelephone(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.�d�b�ԍ����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.telephone);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.�d�b�ԍ�
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strTelephone);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���X��)<BR>
     * �u���X���v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@�@@���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�l�F�@@�@@�@@����.���X��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strBranchName - (���X��)<BR>
     * ���X��
     */
    public void setBranchName(int l_intLineNumber, String l_strBranchName)
    {
        final String STR_METHOD_NAME = " setBranchName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F�@@�V�K�����J�݁E�����ڊǁE���O�C�����b�N�ڋq�_�E�����[�hCSV.���X�����x��
        WEB3GentradeCsvColumnModel l_tradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAccInfoAccEstablishDownLoadCSV.branchName);

        //�Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]
        //�s�ԍ��F�@@����.�s�ԍ�
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��
        //�l�F�@@����.���X��
        this.setValue(l_intLineNumber, l_tradeCsvColumnModel, l_strBranchName);

        log.exiting(STR_METHOD_NAME);
    }
}
@
