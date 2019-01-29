head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoDepShortageDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����s���󋵃_�E�����[�hCSV(WEB3AdminIfoDepShortageDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/02/27�@@����(���u) �V�K�쐬 ���f��No.006
Revision History : 2009/04/10�@@�����F(���u) ���f��No.017
*/
package webbroker3.ifoadmin;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoCurNonPayAmtDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoExistFlagDef;
import webbroker3.ifoadmin.define.WEB3AdminIfoKeyHeaderDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�؋����s���󋵃_�E�����[�hCSV )<BR>
 * �؋����s���󋵃_�E�����[�hCSV �N���X<BR>
 * <BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3AdminIfoDepShortageDownloadCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIfoDepShortageDownloadCsv.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    public String branchCodeLabel = "���X";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    public String accountCodeLabel = "�ڋq";

    /**
     * (�ڋq�����x��)<BR>
     * �ڋq�����x��<BR>
     */
    public String accountNameLabel = "�ڋq��";

    /**
     * (�����z���x��)<BR>
     * �����z���x��<BR>
     */
    public String claimAmountLabel = "�����z";

    /**
     * (���ݖ������z���x��)<BR>
     * ���ݖ������z���x��<BR>
     */
    public String curNonPayAmtLabel = "���݂̖��������z";

    /**
     * (���ݏ؋������v�z���x��)<BR>
     * ���ݏ؋������v�z���x��<BR>
     */
    public String curIfoDepositNecessaryAmtLabel = "���݂̏؋������v�z";

    /**
     * (���ʗL���t���O���x��)<BR>
     * ���ʗL���t���O���x��<BR>
     */
    public String contractExistFlagLabel = "���ʗL���iOP���������j";

    /**
     * (�����L���t���O���x��)<BR>
     * �����L���t���O���x��<BR>
     */
    public String orderExistFlagLabel = "�����L���iOP���������j";

    /**
     * (�؋����s���󋵃_�E�����[�hCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B <BR>
     * <BR>
     * �P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B <BR>
     * <BR>
     * �Q�jcreate�L�[�w�b�_(����.�������t)���R�[�����A�L�[�w�b�_�����쐬����B <BR>
     * <BR>
     * �R�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B <BR>
     * @@param l_datOccurDate - (�������t)<BR>
     * �������t<BR>
     * @@roseuid 499B72830062
     */
    public WEB3AdminIfoDepShortageDownloadCsv(Date l_datOccurDate)
    {
        super();
        this.createKeyHeader(l_datOccurDate);
        this.createColumnHeader();
    }

    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@�uCSV�o�͓��v�̕�����B <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B <BR>
     * <BR>
     * (*1) ���ݓ��� <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@�u�������t�v�̕�����B <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@����.�������t ��"yyyy/MM/dd "�̌`����format����������B <BR>
     * @@param l_datOccurDate - (�������t)<BR>
     * �������t<BR>
     * @@roseuid 499B72F90070
     */
    protected void createKeyHeader(Date l_datOccurDate)
    {
        final String STR_METHOD_NAME = "createKeyHeader(Date)";
        log.entering(STR_METHOD_NAME);

        //�ȉ��̒ʂ蕶����̔z��𐶐����A
        //[�L�[�w�b�_�z��]
        String[] l_strKeyHeaders = new String[4];
        //�|�@@index = 0
        //�@@�uCSV�o�͓��v�̕�����B
        l_strKeyHeaders[0] = WEB3AdminIfoKeyHeaderDef.CSV_OUTPUT_DATE;

        //�|�@@index = 1
        //�@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B
        l_strKeyHeaders[1] = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), WEB3GentradeTimeDef.TIME_FORMAT_YMDHMS);

        //�|�@@index = 2
        //�@@�u�������t�v�̕�����B
        l_strKeyHeaders[2] = WEB3AdminIfoKeyHeaderDef.OCCUR_DATE;

        //�|�@@index = 3
        //�@@����.�������t ��"yyyy/MM/dd "�̌`����format����������B
        l_strKeyHeaders[3] = WEB3DateUtility.formatDate(
            l_datOccurDate, WEB3GentradeTimeDef.DATE_SPLIT_YMD);

        //set�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        this.setKeyHeader(l_strKeyHeaders);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̂Ƃ����CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@���X�R�[�h <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���X�R�[�h���x�� <BR>
     * �@@�@@�J�����ԍ��F 0 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�R�[�h���x�� <BR>
     * �@@�@@�J�����ԍ��F 1 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �R�j�@@�ڋq�� <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�����x�� <BR>
     * �@@�@@�J�����ԍ��F 2 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �S�j�@@�����z <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����z���x�� <BR>
     * �@@�@@�J�����ԍ��F 3 <BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null <BR>
     * <BR>
     * �T�j�@@���ݖ������z <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݖ������z���x�� <BR>
     * �@@�@@�J�����ԍ��F 4<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �U�j�@@���ݏ؋������v�z <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݏ؋������v�z���x�� <BR>
     * �@@�@@�J�����ԍ��F 5<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * <BR>
     * �V�j�@@���ʗL���t���O <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ʗL���t���O���x�� <BR>
     * �@@�@@�J�����ԍ��F�@@6<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �W�j�@@�����L���t���O <BR>
     * <BR>
     * �@@[CSV�J�������f���̃R���X�g���N�^�̈���] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����L���t���O���x�� <BR>
     * �@@�@@�J�����ԍ��F�@@7<BR>
     * �@@�@@���ڌ^�F CSV�J�������f��.���ڌ^_������ <BR>
     * �@@�@@���t�t�H�[�}�b�g�F null<BR>
     * @@roseuid 499B732F0225
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //���X�R�[�h
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���X�R�[�h���x��
        //�J�����ԍ��F 0
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.branchCodeLabel,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�ڋq�R�[�h
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�R�[�h���x��
        //�J�����ԍ��F 1
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.accountCodeLabel,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�ڋq��
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�����x��
        //�J�����ԍ��F 2
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.accountNameLabel,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�����z
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����z���x��
        //�J�����ԍ��F 3
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.claimAmountLabel,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //���ݖ������z
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݖ������z���x��
        //�J�����ԍ��F 4
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.curNonPayAmtLabel,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //���ݏ؋������v�z
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݏ؋������v�z���x��
        //�J�����ԍ��F 5
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.curIfoDepositNecessaryAmtLabel,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //���ʗL���t���O
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ʗL���t���O���x��
        //�J�����ԍ��F�@@6
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.contractExistFlagLabel,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�����L���t���O
        //[CSV�J�������f���̃R���X�g���N�^�̈���]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����L���t���O���x��
        //�J�����ԍ��F�@@7
        //���ڌ^�F CSV�J�������f��.���ڌ^_������
        //���t�t�H�[�}�b�g�F null
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.orderExistFlagLabel,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.���X�R�[�h <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@roseuid 499B75FF02BD
     */
    public void setBranchCode(int l_intLineNo, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���X�R�[�h���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.branchCodeLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.���X�R�[�h
        this.setValue(l_intLineNo, l_csvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�R�[�h)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�ڋq�R�[�h�̍�6byte<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@roseuid 499B766B03D3
     */
    public void setAccountCode(int l_intLineNo, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�R�[�h���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.accountCodeLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�ڋq�R�[�h�̍�6byte
        String l_strValue = l_strAccountCode.substring(0, 6);
        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq��)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�ڋq��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strAccountName - (�ڋq��)<BR>
     * �ڋq��<BR>
     * @@roseuid 499B76BF0151
     */
    public void setAccountName(int l_intLineNo, String l_strAccountName)
    {
        final String STR_METHOD_NAME = "setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�ڋq�����x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.accountNameLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�ڋq��
        this.setValue(l_intLineNo, l_csvColumnModel, l_strAccountName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����z)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����z���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.�����z<BR>
     * <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strClaimAmount - (�����z)<BR>
     * �����z<BR>
     * @@roseuid 499B774D01DB
     */
    public void setClaimAmount(int l_intLineNo, String l_strClaimAmount)
    {
        final String STR_METHOD_NAME = "setClaimAmount(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����z���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.claimAmountLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.�����z
        this.setValue(l_intLineNo, l_csvColumnModel, l_strClaimAmount);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���ݖ������z)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݖ������z���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.���ݖ������z���� null �̏ꍇ] <BR>
     * �@@�@@�@@�@@"-" <BR>
     * �@@�@@�@@[����.���ݖ������z! �� null �̏ꍇ] <BR>
     * �@@�@@�@@�@@����.���ݖ������z<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strCurNonPayAmt - (���ݖ������z)<BR>
     * ���ݖ������z<BR>
     * @@roseuid 499B86B60378
     */
    public void setCurNonPayAmt(int l_intLineNo, String l_strCurNonPayAmt)
    {
        final String STR_METHOD_NAME = "setCurNonPayAmt(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݖ������z���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.curNonPayAmtLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F
        //[����.���ݖ������z���� null �̏ꍇ] "-"
        //[����.���ݖ������z! �� null �̏ꍇ] ����.���ݖ������z
        String l_strValue = null;
        if (l_strCurNonPayAmt == null)
        {
            l_strValue = WEB3AdminIfoCurNonPayAmtDef.EMPTY;
        }
        else
        {
            l_strValue = l_strCurNonPayAmt;
        }

        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���ݏ؋������v�z)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݏ؋������v�z���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F ����.���ݏ؋������v�z<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strCurIfoDepositNecessaryAmt - (���ݏ؋������v�z)<BR>
     * ���ݏ؋������v�z<BR>
     * @@roseuid 499B87130135
     */
    public void setCurIfoDepositNecessaryAmt(int l_intLineNo, String l_strCurIfoDepositNecessaryAmt)
    {
        final String STR_METHOD_NAME = "setCurIfoDepositNecessaryAmt(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ݏ؋������v�z���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.curIfoDepositNecessaryAmtLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F ����.���ݏ؋������v�z���x��
        this.setValue(l_intLineNo, l_csvColumnModel, l_strCurIfoDepositNecessaryAmt);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���ʗL���t���O)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ʗL���t���O���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.���ʗL���t���O����true�̏ꍇ] <BR>
     * �@@�@@�@@�@@"�L" <BR>
     * �@@�@@�@@[����.���ʗL���t���O����false�̏ꍇ] <BR>
     * �@@�@@�@@�@@"��"<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_blnContractExistFlag - (���ʗL���t���O)<BR>
     * ���ʗL���t���O<BR>
     * @@roseuid 499B87550164
     */
    public void setContractExistFlag(int l_intLineNo, boolean l_blnContractExistFlag)
    {
        final String STR_METHOD_NAME = "setContractExistFlag(int, boolean)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.���ʗL���t���O���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.contractExistFlagLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F
        //[����.���ʗL���t���O����true�̏ꍇ] "�L"
        //[����.���ʗL���t���O����false�̏ꍇ] "��"
        String l_strValue = null;
        if (l_blnContractExistFlag)
        {
            l_strValue = WEB3AdminIfoExistFlagDef.EXIST;
        }
        else
        {
            l_strValue = WEB3AdminIfoExistFlagDef.NO;
        }
        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����L���t���O)<BR>
     * �P�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����L���t���O���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g <BR>
     * <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�@@�s�ԍ��F �����̍s�ԍ� <BR>
     * �@@�@@�J�����F �P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F <BR>
     * �@@�@@�@@[����.�����L���t���O����true�̏ꍇ] <BR>
     * �@@�@@�@@�@@"�L" <BR>
     * �@@�@@�@@[����.�����L���t���O����false�̏ꍇ] <BR>
     * �@@�@@�@@�@@"��"<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_blnOrderExistFlag - (�����L���t���O)<BR>
     * �����L���t���O<BR>
     * @@roseuid 499B87A703B5
     */
    public void setOrderExistFlag(int l_intLineNo, boolean l_blnOrderExistFlag)
    {
        final String STR_METHOD_NAME = "setOrderExistFlag(int, boolean)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        //this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        //[����]
        //���ڃ��x���F �؋����s���󋵃_�E�����[�hCSV.�����L���t���O���x��
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(this.orderExistFlagLabel);

        //�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[����]
        //�s�ԍ��F �����̍s�ԍ�
        //�J�����F �P�j�Ŏ擾�����J�������f��
        //�l�F
        //[����.�����L���t���O����true�̏ꍇ] "�L"
        //[����.�����L���t���O����false�̏ꍇ] "��"
        String l_strValue = null;
        if (l_blnOrderExistFlag)
        {
            l_strValue = WEB3AdminIfoExistFlagDef.EXIST;
        }
        else
        {
            l_strValue = WEB3AdminIfoExistFlagDef.NO;
        }
        this.setValue(l_intLineNo, l_csvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }
}@
