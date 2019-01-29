head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotBuyListFewCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���I���ʁE�w���\����FewCSV(WEB3AdminIpoLotBuyListFewCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/09 �����q (���u) �V�K�쐬 �d�l�ύXNo.162

*/
package webbroker3.ipo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3IpoOrderAcceptStatusDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3LotResultDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���I���ʁE�w���\����FewCSV)<BR>
 * ���I���ʁE�w���\���󋵃_�E�����[�h�ō쐬����CSV�t�@@�C���f�[�^�N���X<BR>
 * �i���҃R�[�h�A�M�p�敪�A���J���i�A���I�ԍ��Ȃ��j<BR>
 *
 * @@author �����q
 * @@version 1.0
 */

public class WEB3AdminIpoLotBuyListFewCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotBuyListFewCsv.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h<BR>
     */
    public static final String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h<BR>
     */
    public static final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�ڋq�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq���h<BR>
     */
    public static final String  ACCOUNT_NAME_LABEL = "�ڋq��";

    /**
     * (���I���ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h���I���ʁh<BR>
     */
    public static final String LOT_RESULT_LABEL = "���I����";

    /**
     * (���I���ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h���I���ʁh<BR>
     */
    public static final String ELECTED_QUANTITY_LABEL = "���I����";

    /**
     * (�w���\�����ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�w���\�����ʁh<BR>
     */
    public static final String APPLICATION_QUANTITY_LABEL = "�w���\������";

    /**
     * (�w���\���^���ޓ������x��)<BR>
     * �萔��`�v���p�e�B�@@�h�w���\���^���ޓ����h<BR>
     */
    public static final String OFFERING_TIMESTAMP_LABEL = "�w���\���^���ޓ���";

    /**
     * (�w���\����ԃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�w���\����ԁh<BR>
     */
    public static final String OFFER_STATE_LABEL = "�w���\�����";

    /**
     * (��t��ԃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h��t��ԁh<BR>
     */
    public static final String ACCEPT_STATUS_LABEL = "��t���";

    /**
     * (�D�揇�ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@�h�D�揇�ʁh<BR>
     */
    public static final String SUBSTITUTE_PRIORITY = "�D�揇��";

    /**
     * (���I����_���I�\��)<BR>
     * �萔��`�v���p�e�B�@@���I����_���I�\��<BR>
     */
    public static final String LOT_RESULT_PRIZE_INDICATION = "���I";

    /**
     * (���I����_�⌇�\��)<BR>
     * �萔��`�v���p�e�B�@@���I����_�⌇�\��<BR>
     */
    public static final String LOT_RESULT_WAITING_INDICATION = "�⌇";

    /**
     * (���I����_�⌇���I�\��)<BR>
     * �萔��`�v���p�e�B�@@���I����_�⌇���I�\��<BR>
     */
    public static final String LOT_RESULT_WAITING_PRIZE_INDICATION = "�⌇���I";

    /**
     * (���I����_�⌇���I�\��)<BR>
     * �萔��`�v���p�e�B�@@���I����_�⌇���I�\��<BR>
     */
    public static final String LOT_RESULT_WAITING_REJECTED_INDICATION = "�⌇���I";

    /**
     * (���I����_���I�\��)<BR>
     * �萔��`�v���p�e�B�@@���I����_���I�\��<BR>
     */
    public static final String LOT_RESULT_REJECTED_INDICATION = "���I";

    /**
     * (�w���\�����_�\���\��)<BR>
     * �萔��`�v���p�e�B�@@�w���\�����_�\���\��<BR>
     */
    public static final String OFFER_STATE_APPLICATION_INDICATION = "�\��";

    /**
     * (�w���\�����_���ޕ\��)<BR>
     * �萔��`�v���p�e�B�@@�w���\�����_���ޕ\��<BR>
     */
    public static final String OFFER_STATE_CANCEL_INDICATION = "����";

    /**
     * (�w���\�����_�Ȃ��\��)<BR>
     * �萔��`�v���p�e�B�@@�w���\�����_�Ȃ��\��<BR>
     */
    public static final String OFFER_STATE_NO_INDICATION = "----";

    /**
     * (��t���_��t���\��)<BR>
     * �萔��`�v���p�e�B�@@��t���_��t���\��<BR>
     */
    public static final String RECEIVE_STATE_RECEIVE_INDICATION = "��t��";

    /**
     * (��t���_��t�ϕ\��)<BR>
     * �萔��`�v���p�e�B�@@��t���_��t�ϕ\��<BR>
     */
    public static final String RECEIVE_STATE_RECEIVE_END_INDICATION = "��t��";

    /**
     * (��t���_�Ȃ��\��)<BR>
     * �萔��`�v���p�e�B�@@��t���_�Ȃ��\��<BR>
     */
    public static final String RECEIVE_STATE_NO_INDICATION = "----";

    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�ڋq�����x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV���I���ʃ��x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV���I���ʃ��x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\�����ʃ��x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV���\���^���ޓ������x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����<BR>
     * �@@���t�t�H�[�}�b�g�F<BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\����ԃ��x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV��t��ԃ��x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�D�揇�ʃ��x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�ilong�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 10;
        this.columnHeader = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //  �|�@@index = 0
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV.���X�R�[�h���x��
        //�@@�J�����ԍ��F 0
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[0] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.BRANCH_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //  �|�@@index = 1
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�ڋq�R�[�h���x��
        // �@@�J�����ԍ��F 1
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[1] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ACCOUNT_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 2
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�ڋq�����x��
        // �@@�J�����ԍ��F 2
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[2] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ACCOUNT_NAME_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 3
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV���I���ʃ��x��
        // �@@�J�����ԍ��F 3
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[3] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.LOT_RESULT_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 4
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV���I���ʃ��x��
        // �@@�J�����ԍ��F 4
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[4] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ELECTED_QUANTITY_LABEL,
                4,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        // �|�@@index = 5
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\�����ʃ��x��
        // �@@�J�����ԍ��F 5
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[5] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.APPLICATION_QUANTITY_LABEL,
                5,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        // �|�@@index = 6
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\���^���ޓ������x��
        // �@@�J�����ԍ��F 6
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����
        // �@@���t�t�H�[�}�b�g�F
        // �@@�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") 
        SimpleDateFormat l_simpleDateFormat =
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.columnHeader[6] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.OFFERING_TIMESTAMP_LABEL,
                6,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                l_simpleDateFormat);

        // �|�@@index = 7
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\����ԃ��x��
        // �@@�J�����ԍ��F 7
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[7] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.OFFER_STATE_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 8
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV��t��ԃ��x��
        // �@@�J�����ԍ��F 8
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[8] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ACCEPT_STATUS_LABEL,
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 9
        //  [CSV�J�������f�� �R���X�g���N�^�̈���]
        //�@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�D�揇�ʃ��x��
        // �@@�J�����ԍ��F 9
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�ilong�j
        // �@@���t�t�H�[�}�b�g�F�@@null
        this.columnHeader[9] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.SUBSTITUTE_PRIORITY,
                9,
                WEB3GentradeCsvColumnModel.LONGTYPE,
                null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@IPO����.�����R�[�h<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@IPO����.������<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g<BR>
     */
    private void createKeyHeader(WEB3IpoProductImpl l_ipoProduct)
    {
        final String STR_METHOD_NAME = " createKeysHeader()";
        log.entering(STR_METHOD_NAME);

        // �ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        String[] l_strKeyHeader = new String[3];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();

        // �|�@@index = 0
        // �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B
        // �|�@@index = 1
        // �@@IPO����.�����R�[�h
        // �|�@@index = 2
        //�@@IPO����.������
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(l_processTime, "yyyy/MM/dd HH:mm:ss");
        l_strKeyHeader[1] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        l_strKeyHeader[2] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getStandardName();

        setKeyHeader(l_strKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (���I���ʁE�w���\����FewCSV)<BR>
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�|create�L�[�w�b�_(IPO����)���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO����)<BR>
     * IPO�����I�u�W�F�N�g
     */
    public WEB3AdminIpoLotBuyListFewCsv(WEB3IpoProductImpl l_ipoProduct)
    {
        super();
        createKeyHeader(l_ipoProduct);
        createColumnHeader();
    }

    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@���X�h�c�ɊY�����镔�X�I�u�W�F�N�g.getBranchCode()<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_lngBranchID - (���X�h�c)<BR>
     * ���X�h�c
     * @@throws WEB3BaseException
     */
    public void setBranchCode(int l_intLineNumber, long l_lngBranchID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setBranchCode()";
        log.entering(STR_METHOD_NAME);
        // �J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BRANCH_CODE_LABEL);

        // �Q�j�@@�l�Z�b�g
        // this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        // [set���ڒl()�Ɏw�肷�����]
        // �s�ԍ��F�@@�����̍s�ԍ�
        // �J�����F�@@�P�j�Ŏ擾�����J�������f��
        // �l�F�@@���X�h�c�ɊY�����镔�X�I�u�W�F�N�g.getBranchCode()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        try
        {
            l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchID);
            String l_strValue = l_branch.getBranchCode();
            this.setValue(l_intLineNumber, l_csvDataModel, l_strValue);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq)<BR>
     * �ڋq�R�[�h�A�ڋq�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g�擾<BR>
     * �@@�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�i�ڋq�R�[�h�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�ڋq�R�[�h���x��<BR>
     * <BR>
     * �R�j�@@�i�ڋq�R�[�h�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�ڋq.getAccountCode()�̍�6byte<BR>
     * <BR>
     * �S�j�@@�i�ڋq���j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�ڋq�����x��<BR>
     * <BR>
     * �T�j�@@�i�ڋq���j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�S�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�ڋq.get�ڋq�\����()<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_lngAccountId - (�����h�c)<BR>
     * �����h�c
     * @@throws WEB3BaseException
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccount()";
        log.entering(STR_METHOD_NAME);

        try
        {
            // �P�j�@@�ڋq�I�u�W�F�N�g�擾
            // �����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            MainAccount l_mainAccount =
                l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();

            // �Q�j�@@�i�ڋq�R�[�h�j�J�������f���擾
            // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
            WEB3GentradeCsvColumnModel l_csvDataModelAccountCode =
                this.getColumnModel(ACCOUNT_CODE_LABEL);

            // �R�j�@@�i�ڋq�R�[�h�j�l�Z�b�g
            // this.set���ڒl()�ɂč��ڒl���Z�b�g����B
            String l_strAccountCode = l_mainAccount.getAccountCode();
            this.setValue(l_intLineNumber,
                l_csvDataModelAccountCode,
                l_strAccountCode.substring(0, 6));

            // �S�j�@@�i�ڋq���j�J�������f���擾
            //�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName =
                this.getColumnModel(ACCOUNT_NAME_LABEL);

            // �T�j�@@�i�ڋq���j�l�Z�b�g
            //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
            String l_strAccountName = l_mainAccountRow.getFamilyName();
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strAccountName);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���I����)<BR>
     * ���I���ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV��t���ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F<BR>
     * �@@�@@�i���I���� == �h���I�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV���I����_���I�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h�⌇�h && ���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV���I����_�⌇���I�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h�⌇�h && ���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV���I����_�⌇���I�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h�⌇�h && ���I���ʁi�J��j == �hDEFAULT�i�����I�j�h�j �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV���I����_�⌇�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h���I�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV���I����_���I�\���B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * <BR>
     * @@param l_strLotResult - (���I����)<BR>
     * ���I����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����I�j<BR>
     * 1�F�@@���I<BR>
     * 2�F�@@�⌇<BR>
     * 3�F�@@���I<BR>
     * @@param l_strLotResultRetry - (���I���ʁi�J��j)<BR>
     * ���I���ʁi�J��j<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����I�j<BR>
     * 1�F�@@���I<BR>
     * 3�F�@@���I<BR>
     */
    public void setLotResult(
        int l_intLineNumber,
        String l_strLotResult,
        String l_strLotResultRetry)
    {
        final String STR_METHOD_NAME = " setLotResult()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        //  this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LOT_RESULT_LABEL);

        // �Q�j�@@�l�Z�b�g
        // this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        String l_strResult = null;

        //�i���I���� == �h���I�h�j�̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV���I����_���I�\���B
        if (WEB3LotResultDivDef.ELECTION.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_PRIZE_INDICATION;
        }
        // ���I���� == �h�⌇�h && ���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV���I����_�⌇���I�\���B
        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_strLotResult)
            && WEB3LotResultDivDef.ELECTION.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_PRIZE_INDICATION;
        }
        // �i���I���� == �h�⌇�h && ���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV���I����_�⌇���I�\���B
        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_strLotResult)
                && WEB3LotResultDivDef.DEFEAT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_REJECTED_INDICATION;
        }
        // (���I���� == �h�⌇�h && ���I���ʁi�J��j == �hDEFAULT�i�����I�j�h�j �̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV���I����_�⌇�\���B
        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_strLotResult)
                && WEB3LotResultDivDef.DEFAULT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_INDICATION;
        }
        // �i���I���� == �h���I�h�j�̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV���I����_���I�\���B
        else if (WEB3LotResultDivDef.DEFEAT.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_REJECTED_INDICATION;
        }

        // �Q�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNumber, l_csvDataModel, l_strResult);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���I����)<BR>
     * ���I���ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV���I���ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@���I����<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_dblElectedQuantity - (���I����)<BR>
     * ���I����
     */
    public void setElectedQuantity(int l_intLineNumber, double l_dblElectedQuantity)
    {
        final String STR_METHOD_NAME = " setElectedQuantity()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        //  this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(ELECTED_QUANTITY_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblElectedQuantity));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�w���\������)<BR>
     * �w���\�����ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\�����ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�w���\������<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * <BR>
     * @@param l_dblApplicationQuantity - (�w���\������)<BR>
     * �w���\������
     */
    public void setApplicationQuantity(int l_intLineNumber, double l_dblApplicationQuantity)
    {
        final String STR_METHOD_NAME = " setApplicationQuantity()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        //  this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(APPLICATION_QUANTITY_LABEL);

        // �Q�j�@@�l�Z�b�g
        // this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblApplicationQuantity));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�w���\���^���ޓ���)<BR>
     * �w���\���^���ޓ������Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\���^���ޓ������x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�w���\���^���ޓ��� <BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * @@param l_datOfferingTimestamp - (�w���\���^���ޓ���)<BR>
     * �w���\���^���ޓ���
     */
    public void setOfferingTimestamp(
        int l_intLineNumber,
        Date l_datOfferingTimestamp)
    {
        final String STR_METHOD_NAME = " setOfferingTimestamp()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        //  this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(OFFERING_TIMESTAMP_LABEL);

        // �Q�j�@@�l�Z�b�g
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNumber, l_csvDataModel, l_datOfferingTimestamp);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�w���\�����)<BR>
     * �w���\����Ԃ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�w���\����ԃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F<BR>
     * �@@�@@�i�w���\���敪 == �hDEFAULT�i�����l�j�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV�w���\�����_�Ȃ��\���B<BR>
     * <BR>
     * �@@�@@�i�w���\���敪 == �h�w���\���h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV�w���\�����_�\���\���B<BR>
     * <BR>
     * �@@�@@�i�w���\���敪 == �h���ށh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV�w���\�����_���ޕ\���B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * <BR>
     * @@param l_strOfferDiv - (�w���\���敪)<BR>
     * �w���\���敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����l�j<BR>
     * 1�F�@@�w���\��<BR>
     * 2�F�@@����<BR>
     */
    public void setOfferStatus(int l_intLineNumber, String l_strOfferDiv)
    {
        final String STR_METHOD_NAME = " setOfferStatus()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(OFFER_STATE_LABEL);

        // (�w���\���敪 == �hDEFAULT�i�����l�j�h�j�̏ꍇ�A
        // �@@�|���I���ʁE�w���\����FewCSV�w���\�����_�Ȃ��\���B
        String l_strOfferStatus = null;
        if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
        {
            l_strOfferStatus = OFFER_STATE_NO_INDICATION;
        }
        // �i�w���\���敪 == �h�w���\���h�j�̏ꍇ�A
        //�@@�@@�|���I���ʁE�w���\����FewCSV�w���\�����_�\���\���B
        else if (WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_strOfferDiv))
        {
            l_strOfferStatus = OFFER_STATE_APPLICATION_INDICATION;
        }
        // �i�w���\���敪 == �h���ށh�j�̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV�w���\�����_���ޕ\���B
        else if (WEB3OfferingDivDef.REFUSAL.equals(l_strOfferDiv))
        {
            l_strOfferStatus = OFFER_STATE_CANCEL_INDICATION;
        }

        // �Q�j�@@�l�Z�b�g
        // this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNumber, l_csvDataModel, l_strOfferStatus);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set��t���)<BR>
     * ��t��Ԃ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV��t��ԃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F<BR>
     * �@@�@@�i��t��� == �hDEFAULT�i�����l�j�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV��t���_�Ȃ��\���B<BR>
     * <BR>
     * �@@�@@�i��t��� == �hSONAR���M�ρh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV��t���_��t���\���B<BR>
     * <BR>
     * �@@�@@�i��t��� == �hSONAR���f�ρh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����FewCSV��t���_��t�ϕ\���B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B
     * <BR>
     * @@param l_strAcceptStatus - (��t���)<BR>
     * ��t���<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����l�j<BR>
     * 1�F�@@SONAR���M��<BR>
     * 2�F�@@SONAR���f��<BR>
     */
    public void setAcceptStatus(int l_intLineNumber, String l_strAcceptStatus)
    {
        final String STR_METHOD_NAME = "setAcceptStatus()";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(ACCEPT_STATUS_LABEL);

        String l_strResult = null;
        // �i��t��� == �hDEFAULT�i�����l�j�h�j�̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV��t���_�Ȃ��\���B
        if (WEB3IpoOrderAcceptStatusDef.DEFAULT.equals(l_strAcceptStatus))
        {
            l_strResult = RECEIVE_STATE_NO_INDICATION;
        }
        // �i��t��� == �hSONAR���M�ρh�j�̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV��t���_��t���\���B
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_MAIL_SENDED.equals(l_strAcceptStatus))
        {
            l_strResult = RECEIVE_STATE_RECEIVE_INDICATION;
        }
        // �i��t��� == �hSONAR���f�ρh�j�̏ꍇ�A
        //�@@�|���I���ʁE�w���\����FewCSV��t���_��t�ϕ\���B
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_REFLECTED.equals(l_strAcceptStatus))
        {
            l_strResult = RECEIVE_STATE_RECEIVE_END_INDICATION;
        }

        // �Q�j�@@�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(l_intLineNumber, l_csvDataModel, l_strResult);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�D�揇��)<BR>
     * �D�揇�ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����FewCSV�D�揇�ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�D�揇��<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_lngSubstitutePriority - (�D�揇��)<BR>
     * �D�揇��
     */
    public void setSubstitutePriority(int l_intLineNumber, long l_lngSubstitutePriority)
    {
        final String STR_METHOD_NAME = " setSubstitutePriority()";
        log.entering(STR_METHOD_NAME);

        //  �P�j�@@�J�������f���擾
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(SUBSTITUTE_PRIORITY);

        // �Q�j�@@�l�Z�b�g<BR>
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        Long l_lonSubstitutePriority = new Long(l_lngSubstitutePriority);
        this.setValue(l_intLineNumber, l_csvDataModel, l_lonSubstitutePriority);

        log.exiting(STR_METHOD_NAME);
    }
}
@
