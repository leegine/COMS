head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ꗗCSV(WEB3HistoryTradeHistoryListCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/30 ������(���u) �V�K�쐬
                   2006/01/23 ���u��(���{���u) �d�l�ύX�E���f��042
                   2006/01/23 ���u��(���{���u) �d�l�ύX�E���f��043
*/

package webbroker3.tradehistory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.data.CodeTranslationRow;
import webbroker3.tradehistory.define.WEB3PlsBvsProductCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryRemarkCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTradeCodeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (��������ꗗCSV)<BR>
 * ��������ꗗCSV<BR>
 *
 * @@author ������
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryListCSV extends WEB3GentradeCsvDataModel 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3HistoryTradeHistoryListCSV.class);
    
    /**
     * (��n�����x��)<BR>
     * ��n�����x��<BR>
     */
    private static  String DELIVERY_DATE_LABEL = "��n��";
    
    /**
     * (�������x��)<BR>
     * �������x��<BR>
     */
    private static  String EXECUTION_DATE_LABEL = "����";
    
    /**
     * (���i�R�[�h���̃��x��)<BR>
     * ���i�R�[�h���̃��x��<BR>
     */
    private static  String COMMODITY_NAME_LABEL = "���i";
    
    /**
     * (�����R�[�h���x��)<BR>
     * �����R�[�h���x��<BR>
     */
    private static  String PRODUCT_CODE_LABEL = "�����R�[�h";
    
    /**
     * (�����E�v�����x��)<BR>
     * �����E�v�����x��<BR>
     */
    private static  String PRODUCT_NAME_LABEL = "�����E�v";
    
    /**
     * (�����敪���̃��x��)<BR>
     * �����敪���̃��x��<BR>
     */
    private static  String ACCOUNT_TYPE_LABEL = "����";
    
    /**
     * (�|��E�v�����x��)<BR>
     * �|��E�v�����x��<BR>
     */
    private static  String REMARK_NAME_LABEL = "���";
    
    /**
     * (���ʃ��x��)<BR>
     * ���ʃ��x��<BR>
     */
    private static  String QUANTITY_LABEL = "����";
    
    /**
     * (�P�����x��)<BR>
     * �P�����x��<BR>
     */
    private static  String PRICE_LABEL = "�P��";
    
    /**
     * (�ʉݒP�ʖ��̃��x��)<BR>
     * �ʉݒP�ʖ��̃��x��<BR>
     */
    private static  String MONETARY_UNIT_LABEL = "�ʉ�";
    
    /**
     * (��n���z���x��)<BR>
     * ��n���z���x��<BR>
     */
    private static  String NET_AMOUNT_LABEL = "��n���z";
    
    /**
     * (���n���v���x��)<BR>
     * ���n���v���x��<BR>
     */
    private static  String CAPITAL_GAIN_LABEL = "���n���v";
    
    /**
     * (�����c�����x��)<BR>
     * �����c�����x��<BR>
     */
    private static String ACCOUNT_BALANCE_LABEL = "�����c��";
    
    /**
     * (����_��ʕ\��)<BR>
     * ����_��ʕ\��<BR>
     */
    private static String ACCOUNT_NORMAL_LABEL = "���";
    
    /**
     * (����_����\��)<BR>
     * ����_����\��<BR>
     */
    private static String ACCOUNT_SPECIAL_LABEL = "����";

    /**
     * (wk�ʉݒP��)<BR>
     * wk�ʉݒP��<BR>
     * "T0"�i�~�j<BR>
     */
    private static String WK_MONETARY_UNIT_TO = "T0";

    /**
     * (wk�R�[�h���)<BR>
     * wk�R�[�h���<BR>
     * "th_repdv"�i�ٍϋ敪�j<BR>
     */
    private static String WK_CODE_TYPE_REPDV = "th_repdv";

    /**
     * (wk�R�[�h���)<BR>
     * wk�R�[�h���<BR>
     * "th_cmcd"�i���i�R�[�h�j<BR>
     */
    private static String WK_CODE_TYPE_CMCD = "th_cmcd";

    /**
     * (�R�[�h���)<BR>
     * �R�[�h���<BR>
     * "th_exchg"<BR>
     */
    private static String CODE_TYPE_EXCHG = "th_exchg";

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@roseuid 429FE01C0243
     */
    public WEB3HistoryTradeHistoryListCSV() 
    {
        super();
        this.createKeyHeader();
        this.createColumnHeader();     
    }
    
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
     * �@@���ڃ��x���F�@@��������ꗗCSV.��n�����x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t�^<BR>
     * �@@���t�t�H�[�}�b�g�F<BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd")<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�������x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t�^<BR>
     * �@@���t�t�H�[�}�b�g�F<BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd")<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.���i�R�[�h���̃��x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�����R�[�h���x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�����E�v�����x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�����敪���̃��x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�|��E�v�����x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.���ʃ��x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�P�����x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�ʉݒP�ʃ��x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.��n���z���x��<BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.���n���v���x��<BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@��������ꗗCSV.�����c�����x��<BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * @@roseuid 429FE01C0252
     */
    public void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        final int COLUMN_MAX = 13;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //�|�@@index = 0
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.��n�����x��
        //  �J�����ԍ��F 0
        //  ���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t�^
        //  ���t�t�H�[�}�b�g�F
        //  new SimpleDateFormat("yyyy/MM/dd")
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.DELIVERY_DATE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //�|�@@index = 1
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�������x��
        //  �J�����ԍ��F 1
        //  ���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t�^
        //  ���t�t�H�[�}�b�g�F
        //  new SimpleDateFormat("yyyy/MM/dd")
        l_models[1] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.EXECUTION_DATE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyy/MM/dd"));

        //�|�@@index = 2
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.���i�R�[�h���̃��x��
        //  �J�����ԍ��F 2
        //  ���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //  ���t�t�H�[�}�b�g�F�@@null
        l_models[2] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.COMMODITY_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 3
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�����R�[�h���x��
        //  �J�����ԍ��F 3
        //  ���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //  ���t�t�H�[�}�b�g�F�@@null
        l_models[3] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRODUCT_CODE_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�|�@@index = 4
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�����E�v�����x��
        //  �J�����ԍ��F 4
        //  ���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //  ���t�t�H�[�}�b�g�F�@@null
        l_models[4] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRODUCT_NAME_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 5
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�����敪���̃��x��
        //  �J�����ԍ��F 5
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        l_models[5] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.ACCOUNT_TYPE_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 6
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�|��E�v�����x��
        //�@@�J�����ԍ��F 6
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        l_models[6] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.REMARK_NAME_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 7
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.���ʃ��x��
        //  �J�����ԍ��F 7
        //  ���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //  ���t�t�H�[�}�b�g�F�@@null
        l_models[7] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.QUANTITY_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 8
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�P�����x��
        //�@@�J�����ԍ��F 8
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        l_models[8] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRICE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 9
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�ʉݒP�ʃ��x��
        //�@@�J�����ԍ��F 9
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        l_models[9] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.MONETARY_UNIT_LABEL,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 10
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.��n���z���x��
        //  �J�����ԍ��F 10
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        l_models[10] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.NET_AMOUNT_LABEL,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 11
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.���n���v���x��
        //�@@�J�����ԍ��F 11
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        l_models[11] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.CAPITAL_GAIN_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�|�@@index = 12
        //[CSV�J�������f�� �R���X�g���N�^�̈���]
        //  ���ڃ��x���F�@@��������ꗗCSV.�����c�����x��
        //�@@�J�����ԍ��F 12
        //�@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        //�@@���t�t�H�[�}�b�g�F�@@null
        l_models[12] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.ACCOUNT_BALANCE_LABEL,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        this.setColumnHeader(l_models);
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
     * @@roseuid 429FE01C0253
     */
    public void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()"; 
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        final int COLUMN_MAX = 1;
        String[] l_strKeyHeader = new String[COLUMN_MAX];
        
        //�|�@@index = 0
        //���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(
            GtlUtils.getTradingSystem().getSystemTimestamp(),
            "yyyy/MM/dd HH:mm:ss");
        
        this.setKeyHeader(l_strKeyHeader);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set��n��)<BR>
     * ��n�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.��n�����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�p�����[�^.��n��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��<BR>
     * @@roseuid 429FE01C0253
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = " setDeliveryDate(int, Date)"; 
        log.entering(STR_METHOD_NAME);  

        //�P�j�@@�J�������f���擾 
        // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.DELIVERY_DATE_LABEL);

        //�Q�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_datDeliveryDate);

        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (set����)<BR>
     * �������Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.�������x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�p�����[�^.����<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_datExecutionDate - (����)<BR>
     * ����<BR>
     * @@roseuid 42C4E176008F
     */
    public void setExecutionDate(int l_intLineNumber, Date l_datExecutionDate) 
    {
        final String STR_METHOD_NAME = " setExecutionDate(int, Date)"; 
        log.entering(STR_METHOD_NAME);  

        //�P�j�@@�J�������f���擾 
        // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.EXECUTION_DATE_LABEL);

        //�Q�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_datExecutionDate);

        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (set���i�R�[�h����)<BR>
     * ���i�R�[�h���̂��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.���i�R�[�h���̃��x��<BR>
     * <BR>
     * �Q�j�@@���������l�̐ݒ�<BR>
     * <BR>
     * �@@�i�P�j�@@���i�R�[�h<BR>
     * �@@�@@�@@�E�p�����[�^.���i�R�[�h��null�̂Ƃ�<BR>
     * �@@�@@�@@�@@wk���i�R�[�h��"99"�i���K�j���Z�b�g����<BR>
     * �@@�@@�@@�E��L�ȊO<BR>
     * �@@�@@�@@�@@wk���i�R�[�h�Ƀp�����[�^.���i�R�[�h���Z�b�g����<BR>
     * <BR>
     * �@@�i�Q�j�@@�R�[�h���<BR>
     * �@@�@@�@@�Ewk���i�R�[�h��11�F�M�p���A�p�����[�^.�ٍϋ敪��null�łȂ��Ƃ�<BR>
     * �@@�@@�@@�@@wk�R�[�h��ʂɌŒ蕶��"th_repdv"�i�ٍϋ敪�j���Z�b�g����<BR>
     * �@@�@@�@@�E��L�ȊO<BR>
     * �@@�@@�@@�@@wk�R�[�h��ʂɌŒ蕶��"th_cmcd"�i���i�R�[�h�j���Z�b�g����<BR>
     * <BR>
     * �@@�i�R�j�@@�R�[�h<BR>
     * �@@�@@�@@�Ewk���i�R�[�h��11�F�M�p���A�p�����[�^.�ٍϋ敪��null�łȂ��Ƃ�<BR>
     * �@@�@@�@@�@@wk�R�[�h�Ƀp�����[�^.�ٍϋ敪���Z�b�g����<BR>
     * �@@�@@�@@�E��L�ȊO<BR>
     * �@@�@@�@@�@@wk�R�[�h��wk���i�R�[�h���Z�b�g����<BR>
     * <BR>
     * �R�j�@@�\�����b�Z�[�W�̎擾<BR>
     * <BR>
     * �@@QueryProcessor.doFindAllQuery()���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�R�[�h�|��e�[�u��(code_translation)<BR>
     * �@@arg1�F�@@�I�v�V���i��������(*1�j<BR>
     * �@@arg2�F�@@�I�u�W�F�N�g�z��i*2�j<BR>
     * <BR>
     * �@@���擾�ł��Ȃ��ꍇ�Anull��ԋp����<BR>
     * <BR>
     * �@@�i*1�j�I�v�V���i��������<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̕�������쐬����B<BR>
     * �@@�@@�@@�@@"code_type = ?"<BR>
     * �@@�@@�@@�{" and institution_code = ?"<BR>
     * �@@�@@�@@�{" and code = ?"<BR>
     * <BR>
     * �@@�i*2�j�I�u�W�F�N�g�z��<BR>
     * 
     * �@@�@@�@@�ȉ��̏���ArrayList���쐬����B<BR>
     * �@@�@@�@@�Ewk�R�[�h���<BR>
     * �@@�@@�@@�E�p�����[�^.��ЃR�[�h<BR>
     * �@@�@@�@@�Ewk�R�[�h<BR>
     * <BR>
     * �S�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�R�j�Ŏ擾�����\�����b�Z�[�W<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strInstitutionCode - (��ЃR�[�h)<BR>
     * ��ЃR�[�h<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * @@param l_strRepaymentType - (�ٍϋ敪)<BR>
     * �ٍϋ敪<BR>
     * @@roseuid 42C4E176008F
     */
    public void setCommodityCode(
        int l_intLineNumber,
        String l_strInstitutionCode,
        String l_strCommodityCode,
        String l_strRepaymentType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setCommodityCode(int, String, String, String)"; 
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾 
        // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.COMMODITY_NAME_LABEL);

        //�Q�j�@@���������l�̐ݒ�
        //  �i�P�j�@@���i�R�[�h<BR>
        //�@@�@@�E�p�����[�^.���i�R�[�h��null�̂Ƃ�<BR>
        //�@@�@@�@@wk���i�R�[�h��"99"�i���K�j���Z�b�g����<BR>
        //  �@@�E��L�ȊO<BR>
        //  �@@�@@wk���i�R�[�h�Ƀp�����[�^.���i�R�[�h���Z�b�g����<BR>
        String l_strWKCommodityCode = null;
        if (l_strCommodityCode == null)
        {
            l_strWKCommodityCode = WEB3PlsBvsProductCodeDef.MONEY;
        }
        else
        {
            l_strWKCommodityCode = l_strCommodityCode;
        }

        // �i�Q�j�@@�R�[�h���<BR>
        //�@@�@@�Ewk���i�R�[�h��11�F�M�p���A�p�����[�^.�ٍϋ敪��null�łȂ��Ƃ�<BR>
        //�@@�@@�@@wk�R�[�h��ʂɌŒ蕶��"th_repdv"�i�ٍϋ敪�j���Z�b�g����<BR>
        //�@@�@@�E��L�ȊO<BR>
        //�@@�@@�@@wk�R�[�h��ʂɌŒ蕶��"th_cmcd"�i���i�R�[�h�j���Z�b�g����<BR>
        // �i�R�j�@@�R�[�h<BR>
        //�@@�@@�Ewk���i�R�[�h��11�F�M�p���A�p�����[�^.�ٍϋ敪��null�łȂ��Ƃ�<BR>
        //�@@�@@�@@wk�R�[�h�Ƀp�����[�^.�ٍϋ敪���Z�b�g����<BR>
        //�@@�@@�E��L�ȊO<BR>
        //�@@�@@�@@wk�R�[�h��wk���i�R�[�h���Z�b�g����<BR>
        String l_strWKCodeType = null;
        String l_strWKCode = null;
        if (WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strWKCommodityCode)
            && l_strRepaymentType != null)
        {
            l_strWKCodeType = WEB3HistoryTradeHistoryListCSV.WK_CODE_TYPE_REPDV;
            l_strWKCode = l_strRepaymentType;
        }
        else
        {
            l_strWKCodeType = WEB3HistoryTradeHistoryListCSV.WK_CODE_TYPE_CMCD;
            l_strWKCode = l_strWKCommodityCode;
        }

        //�R�j�@@�\�����b�Z�[�W�̎擾
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" code_type = ? ");
        l_strWhere.append(" and institution_code = ? ");
        l_strWhere.append(" and code = ? ");
        
        Object[] l_objWhere = {
            l_strWKCodeType,
            l_strInstitutionCode,
            l_strWKCode};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_strWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //���擾�ł��Ȃ��ꍇ�Anull��ԋp����
        String l_strMessage = null;
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            l_strMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strMessage = l_translationRow.getDisplayMessage();
        }

        //�S�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strMessage);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set����)<BR>
     * �����R�[�h�Ɩ����E�v�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���i�R�[�h��10�F�����A11�F�M�p�A40�F�O��<BR>
     *      ���p�����[�^.�����R�[�h!= null�̂Ƃ�<BR>
     * <BR>
     * �@@�i�P�j�@@�����R�[�h�̎擾<BR>
     * <BR>
     * �@@�@@�@@�E�p�����[�^.���i�R�[�h��10�F�����A11�F�M�p<BR>
     * �@@�@@�@@�@@- �p�����[�^.�����R�[�h��5���ڂ�0�̂Ƃ�<BR>
     *          wk�����R�[�h�Ƀp�����[�^.�����R�[�h�̏�4�����Z�b�g<BR>
     * �@@�@@�@@�@@- ��L�ȊO<BR>
     *          wk�����R�[�h�Ƀp�����[�^.�����R�[�h�̏�5�����Z�b�g<BR>
     * �@@�@@�@@�E��L�ȊO<BR>
     * �@@�@@�@@�@@wk�����R�[�h�Ƀp�����[�^.�����R�[�h�̏�5�����Z�b�g<BR>
     * <BR>
     * �@@�i�Q�j�@@�����R�[�h�̃J�������f���擾<BR>
     * �@@�@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@�@@�@@�@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�@@���ڃ��x���F�@@�������CSV.�����R�[�h���x��<BR>
     * <BR>
     * �@@�i�R�j�@@�����R�[�h�̒l�Z�b�g<BR>
     * �@@�@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�@@�@@�J�����F�@@�i�Q�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�@@�l�F�i�P�j�Ŏ擾���������R�[�h�iwk�����R�[�h�j<BR>
     * <BR>
     * �Q�j�@@�����E�v���̃J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.�����E�v�����x��<BR>
     * <BR>
     * �R�j�@@�����E�v���̒l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�p�����[�^.�����E�v�����Z�b�g<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strProductName - (�����E�v��)<BR>
     * �����E�v��<BR>
     * @@roseuid 42C4E176008F
     */
    public void setProduct(
        int l_intLineNumber,
        String l_strCommodityCode,
        String l_strProductCode,
        String l_strProductName) 
    {
        final String STR_METHOD_NAME = " setProduct(int, String, String, String)"; 
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�p�����[�^.���i�R�[�h��10�F�����A11�F�M�p�A40�F�O��<BR>
        //       ���p�����[�^.�����R�[�h!= null�̂�<BR>
        WEB3GentradeCsvColumnModel l_columnModel = null;
        if ((WEB3PlsBvsProductCodeDef.EQUITY.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.FOREIGN_STOCK.equals(l_strCommodityCode))
            && l_strProductCode != null)
        {
            // �@@�i�P�j�@@�����R�[�h�̎擾<BR>
            // �@@�@@�@@�E	- �p�����[�^.�����R�[�h��5���ڂ�0�̂Ƃ�<BR>
            //	          wk�����R�[�h�Ƀp�����[�^.�����R�[�h�̏�4�����Z�b�g<BR>
            //	        - ��L�ȊO<BR>
            //            wk�����R�[�h�Ƀp�����[�^.�����R�[�h�̏�5�����Z�b�g<BR>
            // �@@�@@�@@�E��L�ȊO<BR>
            // �@@�@@�@@�@@wk�����R�[�h�Ƀp�����[�^.�����R�[�h�̏�5�����Z�b�g<BR>
            String l_strWKProductCode = null;
            if (WEB3PlsBvsProductCodeDef.EQUITY.equals(l_strCommodityCode)
                || WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strCommodityCode))
            {
            	if (l_strProductCode.charAt(4) == '0')
            	{
					l_strWKProductCode = l_strProductCode.substring(0, 4);
            	}
            	else
            	{
					l_strWKProductCode = l_strProductCode.substring(0, 5);
            	}                
            }
            else
            {
                l_strWKProductCode = l_strProductCode.substring(0, 5);
            }

            // �@@�i�Q�j�@@�����R�[�h�̃J�������f���擾<BR>
            // �@@�@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
            // �@@�@@�@@�@@[get�J�������f��()�Ɏw�肷�����]<BR>
            // �@@�@@�@@�@@���ڃ��x���F�@@�������CSV.�����R�[�h���x��<BR>
            l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.PRODUCT_CODE_LABEL);

            // �@@�i�R�j�@@�����R�[�h�̒l�Z�b�g<BR>
            // �@@�@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
            // �@@�@@�@@[set���ڒl()�Ɏw�肷�����]<BR>
            // �@@�@@�@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
            // �@@�@@�@@�J�����F�@@�i�Q�j�Ŏ擾�����J�������f��<BR>
            // �@@�@@�@@�l�F�i�P�j�Ŏ擾���������R�[�h�iwk�����R�[�h�j<BR>
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strWKProductCode);
        }

        // �Q�j�@@�����E�v���̃J�������f���擾<BR>
        // �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
        // �@@[get�J�������f��()�Ɏw�肷�����]<BR>
        // �@@���ڃ��x���F�@@�������CSV.�����E�v�����x��<BR>
        l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRODUCT_NAME_LABEL);

        // �R�j�@@�����E�v���̒l�Z�b�g<BR>
        // �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
        // �@@[set���ڒl()�Ɏw�肷�����]<BR>
        // �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
        // �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>
        // �@@�l�F�p�����[�^.�����E�v�����Z�b�g<BR>
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strProductName);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set�����敪����)<BR>
     * �����敪���̂��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���i�R�[�h��<BR>
     * �@@�@@�@@�@@10�F�����A11�F�M�p�A30�F���A40�F�O���A20�F���M�A21�F�O��<BR>
     * �@@�@@�@@���A�p�����[�^.�E�v�R�[�h�����������iA206���AA213�j�ȊO�̂Ƃ�<BR>
     *      ���A<BR>
     *        �p�����[�^.���i�R�[�h��<BR>
     * �@@�@@�@@�@@  20�F���M�A21�F�O��<BR>
     *        ���A�p�����[�^.����R�[�h��A3<BR>
     *        ���A�p�����[�^.�E�v�R�[�h��E103�ȊO�̂Ƃ�<BR>
     * <BR>
     * �@@�i�P�j�@@�����敪���̂̎擾<BR>
     * <BR>
     * �@@�@@�@@�E�p�����[�^.�����敪��0�F��ʂ̂Ƃ�<BR>
     * �@@�@@�@@�@@wk�����敪���̂Ɏ�������ꗗCSV.����_��ʕ\�����Z�b�g<BR>
     * �@@�@@�@@�E�p�����[�^.�����敪��1�F����̂Ƃ�<BR>
     * �@@�@@�@@�@@�@@wk�����敪���̂Ɏ�������ꗗCSV.����_����\�����Z�b�g<BR>
     * <BR>
     * �@@�i�Q�j�@@�J�������f���擾<BR>
     * �@@�@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@�@@�@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���ڃ��x���F�@@�������CSV.�����敪���̃��x��<BR>
     * <BR>
     * �@@�i�R�j�@@�l�Z�b�g<BR>
     * �@@�@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�@@�@@�J�����F�@@�i�Q�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�@@�l�F�i�P�j�Ŏ擾���������敪���́iwk�����敪���́j<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * @@param l_strAccountType - (�����敪)<BR>
     * �����敪<BR>
     * @@param l_strRemarkCode - (�E�v�R�[�h)<BR>
     * �E�v�R�[�h<BR>
     * @@param l_strTradeCode - (����R�[�h)<BR>
     * ����R�[�h<BR>
     * @@roseuid 42C4E176008F
     */
    public void setAccountType(
        int l_intLineNumber,
        String l_strCommodityCode,
        String l_strAccountType,
        String l_strRemarkCode,
        String l_strTradeCode)
    {
        final String STR_METHOD_NAME = " setAccountType(int, String, String, String, String)"; 
        log.entering(STR_METHOD_NAME);

        // �P�j�@@�p�����[�^.���i�R�[�h��<BR>
        //�@@�@@�@@10�F�����A11�F�M�p�A30�F���A40�F�O���A20�F���M�A21�F�O��<BR>
        //�@@�@@�@@���A�p�����[�^.�E�v�R�[�h�����������iA206���AA213�j�ȊO�̂Ƃ�<BR>
        //      ���A�p�����[�^.���i�R�[�h��20�F���M�A21�F�O��<BR>
        //            ���A�p�����[�^.����R�[�h��A3<BR>
        //            ���A�p�����[�^.�E�v�R�[�h��E103�ȊO�̂Ƃ�<BR>
        if ((WEB3PlsBvsProductCodeDef.EQUITY.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.BOND.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.FOREIGN_STOCK.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.MUTUAL.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.FOREIGN_MUTUAL.equals(l_strCommodityCode))
            && !WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A206.equals(l_strRemarkCode)
            && !WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A213.equals(l_strRemarkCode)
            && !((WEB3PlsBvsProductCodeDef.MUTUAL.equals(l_strCommodityCode) 
                  || WEB3PlsBvsProductCodeDef.FOREIGN_MUTUAL.equals(l_strCommodityCode))
                  && WEB3TradeHistoryTradeCodeDef.TRADE_CODE_A3.equals(l_strTradeCode)
                  && WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_E103.equals(l_strRemarkCode)))
        {
            //�@@�i�P�j�@@�����敪���̂̎擾<BR>
            // �@@�@@�@@�E�p�����[�^.�����敪��0�F��ʂ̂Ƃ�<BR>
            // �@@�@@�@@�@@wk�����敪���̂Ɏ�������ꗗCSV.����_��ʕ\�����Z�b�g<BR>
            // �@@�@@�@@�E�p�����[�^.�����敪��1�F����̂Ƃ�<BR>
            // �@@�@@�@@�@@�@@wk�����敪���̂Ɏ�������ꗗCSV.����_����\�����Z�b�g<BR>
            String l_strWKAccountType = null;
            if (WEB3AccountDivDef.NORMAL.equals(l_strAccountType))
            {
                l_strWKAccountType = WEB3HistoryTradeHistoryListCSV.ACCOUNT_NORMAL_LABEL;
            }
            else if (WEB3AccountDivDef.SPECIAL.equals(l_strAccountType))
            {
                l_strWKAccountType = WEB3HistoryTradeHistoryListCSV.ACCOUNT_SPECIAL_LABEL;
            }

            // �@@�i�Q�j�@@�����R�[�h�̃J�������f���擾<BR>
            // �@@�@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
            // �@@�@@�@@�@@[get�J�������f��()�Ɏw�肷�����]<BR>
            // �@@�@@�@@�@@���ڃ��x���F�@@�������CSV.�����敪���̃��x��<BR>
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.ACCOUNT_TYPE_LABEL);

            // �@@�i�R�j�@@�����R�[�h�̒l�Z�b�g<BR>
            // �@@�@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
            // �@@�@@�@@[set���ڒl()�Ɏw�肷�����]<BR>
            // �@@�@@�@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
            // �@@�@@�@@�J�����F�@@�i�Q�j�Ŏ擾�����J�������f��<BR>
            // �@@�@@�@@�l�F�i�P�j�Ŏ擾���������敪���́iwk�����敪���́j<BR>
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strWKAccountType);
        }

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set�|��E�v��)<BR>
     * �|��E�v�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.���i�R�[�h��99�F���K�ȊO�̂Ƃ�<BR>
     * <BR>
     * �@@�i�P�j�@@�J�������f���擾<BR>
     * �@@�@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@�@@�@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���ڃ��x���F�@@�������CSV.�|��E�v�����x��<BR>
     * <BR>
     * �@@�i�Q�j�@@�l�Z�b�g<BR>
     * �@@�@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�@@�@@�J�����F�@@�i�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�@@�l�F�p�����[�^.�|��E�v��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strCommodityCode - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * @@param l_strRemarkName - (�|��E�v��)<BR>
     * �|��E�v��<BR>
     * @@roseuid 42C4E176008F
     */
    public void setRemarkName(
        int l_intLineNumber,
        String l_strCommodityCode,
        String l_strRemarkName)
    {
        final String STR_METHOD_NAME = " setRemarkName(int, String, String)"; 
        log.entering(STR_METHOD_NAME);

        //�P)�@@�p�����[�^.���i�R�[�h��99�F���K�ȊO�̂Ƃ�
        if (!WEB3PlsBvsProductCodeDef.MONEY.equals(l_strCommodityCode))
        {
            //(�P�j�@@�J�������f���擾 
            // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.REMARK_NAME_LABEL);

            //(�Q�j�@@�l�Z�b�g
            //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strRemarkName);
        }

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set����)<BR>
     * ���ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.����>0�̂Ƃ�<BR>
     * �@@�i�P�j�@@�J�������f���擾<BR>
     * �@@  this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@  [get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@  ���ڃ��x���F�@@�������CSV.���ʃ��x��<BR>
     * <BR>
     * �@@�i�Q�j�@@�l�Z�b�g<BR>
     * �@@  this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@  [set���ڒl()�Ɏw�肷�����]<BR>
     * �@@  �s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@  �J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@  �l�F�p�����[�^.����<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strQuantity - (����)<BR>
     * ����<BR>
     * @@roseuid 42C4E176008F
     */
    public void setQuantity(
        int l_intLineNumber,
        String l_strQuantity)
    {
        final String STR_METHOD_NAME = " setQuantity(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //�P�j�@@�p�����[�^.����>0�̂Ƃ�<BR>
        if (l_strQuantity != null 
             && Double.parseDouble(l_strQuantity) > 0)
		{
            //�i�P�j�@@�J�������f���擾 
            // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.QUANTITY_LABEL);

            //�i�Q�j�@@�l�Z�b�g
            //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strQuantity);			
		}

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set�P��)<BR>
     * �P�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�P��>0�̂Ƃ�<BR>
     * <BR>
     * �@@�i�P�j�@@�J�������f���擾<BR>
     * �@@�@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@�@@�@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@���ڃ��x���F�@@�������CSV.�P�����x��<BR>
     * <BR>
     * �@@�i�Q�j�@@�l�Z�b�g<BR>
     * �@@�@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@�@@�@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�@@�@@�J�����F�i�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�@@�@@�l�F�p�����[�^.�P��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strPrice - (�P��)<BR>
     * �P��<BR>
     * @@roseuid 42C4E176008F
     */
    public void setPrice(
        int l_intLineNumber,
        String l_strPrice)
    {
        final String STR_METHOD_NAME = " setPrice(int, String)"; 
        log.entering(STR_METHOD_NAME);

        //�P)�@@�p�����[�^.�P��>0�̂Ƃ�
        if (l_strPrice != null 
            && Double.parseDouble(l_strPrice) > 0)
        {
            //(�P�j�@@�J�������f���擾 
            // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.PRICE_LABEL);

            //(�Q�j�@@�l�Z�b�g
            //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strPrice);
        }

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set�ʉݒP�ʖ���)<BR>
     * �ʉݒP�ʖ��̂��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.�ʉݒP�ʖ��̃��x��<BR>
     * <BR>
     * �Q�j�@@���������l�̐ݒ�<BR>
     * <BR>
     * �@@�i�P�j�@@�ʉݒP��<BR>
     * �@@�@@�@@�E�p�����[�^.�ʉݒP�ʂ��X�y�[�Xor�X�y�[�X�~2�̂Ƃ�<BR>
     * �@@�@@�@@�@@wk�ʉݒP�ʂ�"T0"�i�~�j���Z�b�g����<BR>
     * �@@�@@�@@�E��L�ȊO<BR>
     * �@@�@@�@@�@@wk�ʉݒP�ʂɃp�����[�^.�ʉݒP�ʂ��Z�b�g����<BR>
     * <BR>
     * �R�j�@@�\�����b�Z�[�W�̎擾<BR>
     * <BR>
     * �@@QueryProcessor.doFindAllQuery()���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@arg0�F�@@�R�[�h�|��e�[�u��(code_translation)<BR>
     * �@@arg1�F�@@�I�v�V���i��������(*1�j<BR>
     * �@@arg2�F�@@�I�u�W�F�N�g�z��i*2�j<BR>
     * <BR>
     * �@@���擾�ł��Ȃ��ꍇ�Anull��ԋp����<BR>
     * <BR>
     * �@@�i*1�j�I�v�V���i��������<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̕�������쐬����B<BR>
     * �@@�@@�@@�@@"code_type = ?"<BR>
     * �@@�@@�@@�{" and institution_code = ?"<BR>
     * �@@�@@�@@�{" and code = ?"<BR>
     * <BR>
     * �@@�i*2�j�I�u�W�F�N�g�z��<BR>
     * <BR>
     * �@@�@@�@@�ȉ��̏���ArrayList���쐬����B<BR>
     * �@@�@@�@@�E"th_exchg"<BR>
     * �@@�@@�@@�E�p�����[�^.��ЃR�[�h<BR>
     * �@@�@@�@@�Ewk�ʉݒP��<BR>
     * <BR>
     * �S�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�R�j�Ŏ擾�����\�����b�Z�[�W<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strInstitutionCode - (��ЃR�[�h)<BR>
     * ��ЃR�[�h<BR> 
     * @@param l_strMonetaryUnit - (�ʉݒP��)<BR>
     * �ʉݒP��<BR> 
     * @@roseuid 42C4E176008F
     */
    public void setMonetaryUnit(
        int l_intLineNumber,
        String l_strInstitutionCode,
        String l_strMonetaryUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setMonetaryUnit(int, String, String)"; 
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�J�������f���擾 
        // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.MONETARY_UNIT_LABEL);

        //�Q�j�@@���������l�̐ݒ�
        //  �i�P�j�@@�ʉݒP��
        //      �E�p�����[�^.�ʉݒP�ʂ��X�y�[�Xor�X�y�[�X�~2�̂Ƃ�
        //        wk�ʉݒP�ʂ�"T0"�i�~�j���Z�b�g����
        //      �E��L�ȊO
        //        wk�ʉݒP�ʂɃp�����[�^.�ʉݒP�ʂ��Z�b�g����
        String l_strWKMonetaryUnit = null;
        if (" ".equals(l_strMonetaryUnit) || "  ".equals(l_strMonetaryUnit))
        {
            l_strWKMonetaryUnit = WEB3HistoryTradeHistoryListCSV.WK_MONETARY_UNIT_TO;
        }
        else
        {
            l_strWKMonetaryUnit = l_strMonetaryUnit;
        }

        //�R�j�@@�\�����b�Z�[�W�̎擾
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" code_type = ? ");
        l_strWhere.append(" and institution_code = ? ");
        l_strWhere.append(" and code = ? ");
        
        Object[] l_objWhere = {
            WEB3HistoryTradeHistoryListCSV.CODE_TYPE_EXCHG,
            l_strInstitutionCode,
            l_strWKMonetaryUnit};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_strWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //���擾�ł��Ȃ��ꍇ�Anull��ԋp����
        String l_strMessage = null;
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            l_strMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strMessage = l_translationRow.getDisplayMessage();
        }

        //�S�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strMessage);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set��n���z)<BR>
     * ��n���z���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.��n���z���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�p�����[�^.��n���z<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strNetAmount - (��n���z)<BR>
     * ��n���z<BR>
     * @@roseuid 42C4E176008F
     */
    public void setNetAmount(
        int l_intLineNumber,
        String l_strNetAmount)
    {
        final String STR_METHOD_NAME = " setNetAmount(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //�P�j�@@�J�������f���擾 
        // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.NET_AMOUNT_LABEL);

        //�Q�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strNetAmount);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set���n���v)<BR>
     * ���n���v���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.���n���v���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�p�����[�^.���n���v<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strCapitalGain - (���n���v)<BR>
     * ���n���v<BR>
     * @@roseuid 42C4E176008F
     */
    public void setCapitalGain(
        int l_intLineNumber,
        String l_strCapitalGain)
    {
        final String STR_METHOD_NAME = " setCapitalGain(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //�P�j�@@�J�������f���擾 
        // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.CAPITAL_GAIN_LABEL);

        //�Q�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strCapitalGain);

        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (set�����c��)<BR>
     * �����c�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�������CSV.�����c�����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�p�����[�^.�����c��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strAccountBalance - (�����c��)<BR>
     * �����c��<BR>
     * @@roseuid 42C4E176008F
     */
    public void setAccountBalance(
        int l_intLineNumber,
        String l_strAccountBalance)
    {
        final String STR_METHOD_NAME = " setAccountBalance(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //�P�j�@@�J�������f���擾 
        // this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.ACCOUNT_BALANCE_LABEL);

        //�Q�j�@@�l�Z�b�g
        //�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strAccountBalance);

        log.exiting(STR_METHOD_NAME);  
    }
}
@
