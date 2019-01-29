head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiOtherOrgIdDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���A�gID�Ɖ��޳�۰��CSV(WEB3AdminSrvRegiOtherOrgIdDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/19 ���n�m (���u) �V�K�쐬�E���f��No.336
Revesion History : 2008/04/03 �|���@@�M�� (SCS) QTP�A�g�Ή��icreate�L�[�w�b�_()�ǉ��j
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;


/**
 * (�O���A�gID�Ɖ��޳�۰��CSV)<BR>
 * �O���A�gID�Ɖ��޳�۰��CSV<BR>
 * <BR>
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AdminSrvRegiOtherOrgIdDownloadCsv
    extends WEB3GentradeCsvDataModel
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiOtherOrgIdDownloadCsv.class);

    /**
     * (�ʔԃ��x��)<BR>
     * �萔��`�v���p�e�B�@@"�ʔ�"<BR>
     */
    public static String SEQUENCE_NUMBER_LABEL = "�ʔ�";

    /**
     * (ID���x��)<BR>
     * �萔��`�v���p�e�B�@@"ID"<BR>
     */
    public static String ID_LABEL = "ID";

    /**
     * (�X�e�[�^�X���x��)<BR>
     * �萔��`�v���p�e�B�@@"�X�e�[�^�X"<BR>
     */
    public static String STATUS_LABEL = "�X�e�[�^�X";

    /**
     * (�،���ЃR�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�،���ЃR�[�h�h<BR>
     */
    public static String INSTITUTION_CODE_LABEL = "�،���ЃR�[�h";

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h<BR>
     */
    public static String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�����R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����R�[�h�h<BR>
     */
    public static String ACCOUNT_CODE_LABEL = "�����R�[�h";

    /**
     * (�K�p����From���x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p����From"<BR>
     */
    public static String APPLI_START_DATE_LABEL = "�K�p����From";

    /**
     * (�K�p����To���x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p����To"<BR>
     */
    public static String APPLI_END_DATE_LABEL = "�K�p����To";

    /**
     * (�O���A�gID�Ɖ��޳�۰��CSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@roseuid 47BA96C402DD
     */
    public WEB3AdminSrvRegiOtherOrgIdDownloadCsv()
    {
        // �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B
        // �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B
        // �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B
        this.createKeyHeader();
        this.createColumnHeader();
    }

    /**
     * (create�L�[�w�b�_())<BR>
     * �L�[�w�b�_���Z�b�g����B <BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�L�[�w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B <BR>
     * <BR>
     * (*1) ���ݓ��� <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * @@roseuid 4104DBE602EE
     */
    private void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME );
        
        Timestamp l_tsSysDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        String l_strCurrentTime = WEB3DateUtility.formatDate(l_tsSysDate, "yyyy/MM/dd HH:mm:ss");
        String[] l_strKeyHeaders = {l_strCurrentTime};
        
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * �@@set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�،���ЃR�[�h���x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�����R�[�h���x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����From���x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����To���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * <BR>
     * @@roseuid 47BA969401F6
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        // �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A
        // �@@set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_gentradeCsvColumnModel =
            new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        int l_intIndex = 0;

        // [�J�����w�b�_�z��]
        // �|�@@index = 0
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��
        // �@@�J�����ԍ��F 0
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.SEQUENCE_NUMBER_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 1
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��
        // �@@�J�����ԍ��F 1
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ID_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 2
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��
        // �@@�J�����ԍ��F 2
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.STATUS_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 3
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�،���ЃR�[�h���x��
        // �@@�J�����ԍ��F 3
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.INSTITUTION_CODE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 4
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.���X�R�[�h���x��
        // �@@�J�����ԍ��F 4
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.BRANCH_CODE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 5
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�����R�[�h���x��
        // �@@�J�����ԍ��F 5
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ACCOUNT_CODE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 6
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����From���x��
        // �@@�J�����ԍ��F 6
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_START_DATE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 7
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����To���x��
        // �@@�J�����ԍ��F 7
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        l_intIndex = ++l_intIndex;
        l_gentradeCsvColumnModel[l_intIndex] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_END_DATE_LABEL,
                l_intIndex,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        this.setColumnHeader(l_gentradeCsvColumnModel);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ʔ�)<BR>
     * �ʔԂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̒ʔ�<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_lngSequenceNumber - (�ʔ�)<BR>
     * �ʔԂ��w�肷��B<BR>
     * @@roseuid 47BA9AA802D8
     */
    public void setSequenceNumber(int l_intLineNo, String l_strSequenceNumber)
    {
        final String STR_METHOD_NAME = "setSequenceNumber(int, long)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�ʔԃ��x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.SEQUENCE_NUMBER_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̒ʔ�
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strSequenceNumber);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setID)<BR>
     * ID���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u����ID<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strId - (ID)<BR>
     * ID���w�肷��B<BR>
     * @@roseuid 47BA9AB3012B
     */
    public void setId(int l_intLineNo, String l_strId)
    {
        final String STR_METHOD_NAME = "setId(int, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.ID���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ID_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u����ID
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strId);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�X�e�[�^�X)<BR>
     * �X�e�[�^�X���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̃X�e�[�^�X<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strStatus - (�X�e�[�^�X)<BR>
     * �X�e�[�^�X���w�肷��B<BR>
     * @@roseuid 47BA9ABC0356
     */
    public void setStatus(int l_intLineNo, String l_strStatus)
    {
        final String STR_METHOD_NAME = "setStatus(int, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�X�e�[�^�X���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.STATUS_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̃X�e�[�^�X
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strStatus);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�،���ЃR�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̏،���ЃR�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@roseuid 47BA96940215
     */
    public void setInstitutionCode(int l_intLineNo, String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = "setInstitutionCode(int, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�،���ЃR�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.INSTITUTION_CODE_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̏،���ЃR�[�h
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strInstitutionCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̕��X�R�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h���w�肷��B<BR>
     * @@roseuid 47BA96940224
     */
    public void setBranchCode(int l_intLineNo, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.���X�R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.BRANCH_CODE_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̕��X�R�[�h
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����R�[�h)<BR>
     * �����R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�����R�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̌����R�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h���w�肷��B<BR>
     * @@roseuid 47BA96940253
     */
    public void setAccountCode(int l_intLineNo, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�����R�[�h���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.ACCOUNT_CODE_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̌����R�[�h
        this.setValue(l_intLineNo, l_gentradeCsvColumnModel, l_strAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�K�p����From)<BR>
     * �K�p����From���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����From���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̓K�p����From<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_datAppliStartDate - (�K�p����From)<BR>
     * �K�p����From���w�肷��B<BR>
     * @@roseuid 47BA96940272
     */
    public void setAppliStartDate(int l_intLineNo, Date l_datAppliStartDate)
    {
        final String STR_METHOD_NAME = "setAppliStartDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����From���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_START_DATE_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̓K�p����From
        this.setValue(
            l_intLineNo,
            l_gentradeCsvColumnModel,
            WEB3DateUtility.formatDate(
                l_datAppliStartDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�K�p����To)<BR>
     * �K�p����To���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����To���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̓K�p����To<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_datAppliEndDate - (�K�p����To)<BR>
     * �K�p����To���w�肷��B<BR>
     * @@roseuid 47BA96940282
     */
    public void setAppliEndDate(int l_intLineNo, Date l_datAppliEndDate)
    {
        final String STR_METHOD_NAME = "setAppliEndDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        // �@@[get�J�������f��()�Ɏw�肷�����]
        // �@@���ڃ��x���F�@@�O���A�gID�Ɖ��޳�۰��CSV.�K�p����To���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(
                WEB3AdminSrvRegiOtherOrgIdDownloadCsv.APPLI_END_DATE_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // �@@[set���ڒl()�Ɏw�肷�����]
        // �@@�s�ԍ��F�@@�����̍s�ԍ�
        // �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �@@�l�F�@@�O���A�g���Ǘ��e�[�u���̓K�p����To
        this.setValue(
            l_intLineNo,
            l_gentradeCsvColumnModel,
            WEB3DateUtility.formatDate(
                l_datAppliEndDate,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD));

        log.exiting(STR_METHOD_NAME);
    }

}
@
