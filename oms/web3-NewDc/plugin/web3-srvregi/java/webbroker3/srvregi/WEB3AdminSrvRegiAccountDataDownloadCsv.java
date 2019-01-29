head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�f�[�^�_�E�����[�hCSV(WEB3AdminSrvRegiAccountDataDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
Revesion History : 2007/04/06 ꎉ�  (���u) �d�l�ύX���f�� 235
Revesion History : 2007/04/09 ꎉ�  (���u) �d�l�ύX���f�� 236
Revesion History : 2007/04/12 CInomata(SCS) �d�l�ύX���f�� 237
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�ڋq�f�[�^�_�E�����[�hCSV)<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataDownloadCsv extends WEB3GentradeCsvDataModel 
{
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataDownloadCsv.class);
    
    /**
     * (�\���o�^ID���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�\���o�^ID�h <BR>
     */
    public static  String REGIST_ID_LABEL = "�\���o�^ID";
    
    /**
     * (�،���ЃR�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�،���ЃR�[�h�h <BR>
     */
    public static  String INSTITUTION_CODE_LABEL = "�،���ЃR�[�h";
    
    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h <BR>
     */
    public static  String BRANCH_CODE_LABEL = "���X�R�[�h";
    
    /**
     * (�T�[�r�X�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�T�[�r�X�敪�h <BR>
     */
    public static  String SRV_DIV_LABEL = "�T�[�r�X�敪";
    
    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h <BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";
    
    /**
     * (�ڋq�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq���h <BR>
     */
    public static  String ACCOUNT_NAME_LABEL = "�ڋq��";
    
    /**
     * (�o�^�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@"�o�^�敪"<BR>
     */
    public static  String PAYMENT_DIV_LABEL = "�o�^�敪";
    
    /**
     * (�\�����I�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@"�\�����I�敪"<BR>
     */
    public static  String APPLI_LOT_DIV_LABEL = "�\�����I�敪";
    
    /**
     * (�\�������x��)<BR>
     * �萔��`�v���p�e�B�@@"�\����"<BR>
     */
    public static  String APPLI_DATE_LABEL = "�\����";
    
    /**
     * (�K�p�J�n�����x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p�J�n��"<BR>
     */
    public static  String APPLI_START_DATE_LABEL = "�K�p�J�n��";
    
    /**
     * (�K�p�I�������x��)<BR>
     * �萔��`�v���p�e�B�@@"�K�p�I����"<BR>
     */
    public static  String APPLI_END_DATE_LABEL = "�K�p�I����";
    
    /**
     * (���p�������x��)<BR>
     * �萔��`�v���p�e�B�@@"���p����"<BR>
     */
    public static  String USE_AMT_LABEL = "���p����";
    
    /**
     * (�o���]�̓��x��)<BR>
     * �萔��`�v���p�e�B�@@"�o���]��"<BR>
     */
    public static  String PAYMENT_POWER_LABEL = "�o���]��";
    
    /**
     * (�o�������x��)<BR>
     * �萔��`�v���p�e�B�@@"�o����"<BR>
     */
    public static  String PAYMENT_DATE_LABEL = "�o����";
    
    /**
     * (�ڋq�f�[�^CSV)
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B <BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B <BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B <BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B <BR>
     * @@roseuid 4104E458009C
     */
    public WEB3AdminSrvRegiAccountDataDownloadCsv() 
    {
        super();
        createKeyHeader();
        createColumnHeader(); 
    }
    
    /**
     * (create�J�����w�b�_())<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�\���o�^ID���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�،���ЃR�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�T�[�r�X�敪���x��<BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�ڋq�����x�� <BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�\�����I�敪���x�� <BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�\�������x�� <BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�K�p�J�n�����x�� <BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�K�p�I�������x�� <BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�o�^�敪���x�� <BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.���p�������x�� <BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 12<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�o���]�̓��x�� <BR>
     * �@@�J�����ԍ��F 12<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 13<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�o�������x�� <BR>
     * �@@�J�����ԍ��F 13<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t���� <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")<BR>
     * @@roseuid 4104DB280158
     */
    private void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 14;
        
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //index 0
        l_models[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.REGIST_ID_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 1
        l_models[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.INSTITUTION_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 2
        l_models[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.BRANCH_CODE_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 3
        l_models[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.SRV_DIV_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 4
        l_models[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.ACCOUNT_CODE_LABEL,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 5
        l_models[5] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.ACCOUNT_NAME_LABEL,
                5,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 6
        l_models[6] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_LOT_DIV_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 7
        l_models[7] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_DATE_LABEL,
                7,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));

        //index 8
        l_models[8] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_START_DATE_LABEL,
                8,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));

        //index 9
        l_models[9] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_END_DATE_LABEL,
                9,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));

        //index 10
        l_models[10] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DIV_LABEL,
                10,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 11
        l_models[11] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.USE_AMT_LABEL,
                11,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        //index 12
        l_models[12] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_POWER_LABEL,
                12,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        //index 13
        l_models[13] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DATE_LABEL,
                13,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));
        
        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME); 
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
     * (set�\���o�^�h�c)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�\���o�^ID���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�����̐\���o�^ID <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strRegistId - (�\���o�^ID)<BR>
     * �\���o�^ID���w�肷��B<BR>
     * @@roseuid 41201E740154
     */
    public void setRegistId(int l_intLineNumber, String l_strRegistId) 
    {
        final String STR_METHOD_NAME = " setRegistId(int , String ) ";
        log.entering(STR_METHOD_NAME );
        
        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.REGIST_ID_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_strRegistId);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�،���ЃR�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�����̏،���ЃR�[�h <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@roseuid 4104D9BF00BC
     */
    public void setInstitutionCode(int l_intLineNumber, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int , String )";
        log.entering(STR_METHOD_NAME );
       
        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.INSTITUTION_CODE_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_strInstitutionCode);
               
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.���X�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�T�[�r�X�\���o�^�e�[�u���̕��X�R�[�h <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h���w�肷��B<BR>
     * @@roseuid 4104D9F3035B
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode) 
    {
        final String STR_METHOD_NAME = " setBranchCode(int , String )";
        log.entering(STR_METHOD_NAME );
       
        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.BRANCH_CODE_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_strBranchCode);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�T�[�r�X�敪���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�T�[�r�X�\���o�^�e�[�u���̃T�[�r�X�敪<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪���w�肷��B<BR>
     * @@roseuid 4104DA000196
     */
    public void setSrvDiv(int l_intLineNumber, String l_strSrvDiv) 
    {
        final String STR_METHOD_NAME = " setSrvDiv(int , String )";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.SRV_DIV_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_strSrvDiv);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�ڋq)<BR>
     * �ڋq�R�[�h�A�ڋq�����Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g�擾 <BR>
     * �@@�����R�[�h�ɊY������ڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �@@�@@�B �ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�A�J�E���g�}�l�[�W��.get�ڋq�i�،���ЃR�[�h�A���X�R�[�h�A�����R�[�h�j<BR>
     * <BR>
     * �Q�j�@@�i�ڋq�R�[�h�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�ڋq�R�[�h���x�� <BR>
     * <BR>
     * �R�j�@@�i�ڋq�R�[�h�j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�擾�����ڋq�I�u�W�F�N�g.get�\���ڋq�R�[�h()�̖߂�l<BR>
     * <BR>
     * �S�j�@@�i�ڋq���j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�ڋq�����x�� <BR>
     * <BR>
     * �T�j�@@�i�ڋq���j�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�S�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�ڋq.get�ڋq�\����() <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h���w�肷��B<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h���w�肷��B<BR>
     * @@roseuid 4104DA1D0129
     */
    public void setAccount(int l_intLineNumber, String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccount(int , String , String , String )";
        log.entering(STR_METHOD_NAME );

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //�P�j�@@�ڋq�I�u�W�F�N�g�擾
            //�B �ڋq�I�u�W�F�N�g���擾����B
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode, 
                l_strAccountCode);//NotFoundException

            //�Q�j�@@�i�ڋq�R�[�h�j�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(ACCOUNT_CODE_LABEL);
            
            //�R�j�@@�i�ڋq�R�[�h�j�l�Z�b�g
            this.setValue(l_intLineNumber,
                l_model,
                l_mainAccount.getDisplayAccountCode());
            
            //�S�j�@@�i�ڋq���j�J�������f���擾
            l_model =
                this.getColumnModel(ACCOUNT_NAME_LABEL);
                
            //�T�j�@@�i�ڋq���j�l�Z�b�g
            this.setValue(l_intLineNumber,
                l_model,
                l_mainAccount.getDisplayAccountName());
                
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�o�^�敪)<BR>
     * �o�^�敪���Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�o�^�敪���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�o�^�敪 <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * �o�^�敪���w�肷��B<BR>
     * @@roseuid 4104DA62001F
     */
    public void setPaymentDiv(int l_intLineNumber, String l_strPaymentDiv) 
    {
        final String STR_METHOD_NAME = " setPaymentDiv(int , String )";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DIV_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_strPaymentDiv);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�\�����I�敪)<BR>
     * �\���E���I�敪���Z�b�g����B<BR>
     * �@@�\���E���I�敪���Z�b�g����B<BR>
     * �@@<BR>
     * �@@�P�j�@@�J�������f���擾 <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * �@@<BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@�@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�\�����I�敪���x�� <BR>
     * �@@<BR>
     * �@@�Q�j�\�����I�敪�̔���<BR>
     * �@@�@@this.set���ڒl()�ɓn���\�����I�敪�𔻒肷��B<BR>
     * �@@�@@�|����.����敪���g����h�̏ꍇ�A�\�����I�敪���g����h�Ɣ��肷��<BR>
     * �@@<BR>
     * �@@�@@�|����.�\�����I�敪���g�������I�h && <BR>
     * �@@�@@�@@����.�������I��������������ݓ��t�̏ꍇ�A�\�����I�敪���g�\���h�Ɣ��肷��B<BR>
     * �@@<BR>
     * �@@�@@�|����.�\�����I�敪���g�������I�h && <BR>
     * �@@�@@�@@����.�������I��������������ݓ��t�̏ꍇ�A�\�����I�敪���g���I�i�{�\���j�h�Ɣ��肷��B<BR>
     * �@@<BR>
     * �@@�@@�|��L�ȊO�̏ꍇ�A����.�\�����I�敪�̂܂܂Ƃ���B<BR>
     * �@@<BR>
     * �@@�R�j�@@�l�Z�b�g <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * �@@<BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�@@�l�F�@@�Q�j�Ŕ��肵���\�����I�敪<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * �\�����I�敪���w�肷��B<BR>
     * @@param l_strCancelDiv - (����敪)<BR>
     * ����敪���w�肷��B<BR>
     * @@param l_datCancelLimitDate - (�������I���������)<BR>
     * �������I������������w�肷��B<BR>
     * @@roseuid 41064353010C
     */
    public void setAppliLotDiv(
    	int l_intLineNumber,
    	String l_strAppliLotDiv,
    	String l_strCancelDiv,
    	Date l_datCancelLimitDate) 
    {
        final String STR_METHOD_NAME = " setAppliLotDiv(int , String ) ";
        log.entering(STR_METHOD_NAME );

		String l_strSetAppliLotDiv;

        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_LOT_DIV_LABEL);

        //�Q�j�\�����I�敪�̔���
        // ���ݓ��t�擾
        Date l_currentTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

		// this.set���ڒl()�ɓn���\�����I�敪�𔻒肷��B
		// �|����.����敪���g����h�̏ꍇ�A�\�����I�敪���g����h�Ɣ��肷��
		if (l_strCancelDiv.equals(WEB3SrvRegiCancelDivDef.CANCEL))
		{
			l_strSetAppliLotDiv = WEB3SrvRegiAppliLotDivDef.CANCEL;
		}
		// �|����.�\�����I�敪���g�������I�h && 
		// 	 ����.�������I��������������ݓ��t�̏ꍇ�A�\�����I�敪���g�\���h�Ɣ��肷��B
		else if((l_strAppliLotDiv.equals(WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION)) &&
				(l_datCancelLimitDate.compareTo(l_currentTimestamp) > 0))
		{
			l_strSetAppliLotDiv = WEB3SrvRegiAppliLotDivDef.APPLI;
		}
		// �|����.�\�����I�敪���g�������I�h && 
		// 	 ����.�������I��������������ݓ��t�̏ꍇ�A�\�����I�敪���g���I�i�{�\���j�h�Ɣ��肷��B
		else if((l_strAppliLotDiv.equals(WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION)) &&
				(l_datCancelLimitDate.compareTo(l_currentTimestamp) <= 0))
		{
			l_strSetAppliLotDiv = WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI;
		}
		// �|��L�ȊO�̏ꍇ�A����.�\�����I�敪�̂܂܂Ƃ���B
		else
		{
			l_strSetAppliLotDiv = l_strAppliLotDiv;
		}

        //�R�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_strSetAppliLotDiv);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�\����)<BR>
     * �\�������Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�\�������x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�\���� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_datAppliDate - (�\����)<BR>
     * �\�������w�肷��B<BR>
     * @@roseuid 4104DA830252
     */
    public void setAppliDate(int l_intLineNumber, Date l_datAppliDate) 
    {
        final String STR_METHOD_NAME = " setAppliDate(int , Date ) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_DATE_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_datAppliDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�K�p�J�n��)<BR>
     * �K�p�J�n�����Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�K�p�J�n�����x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�K�p�J�n�� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_datAppliStartDate - (�K�p�J�n��)<BR>
     * �K�p�J�n�����w�肷��B<BR>
     * @@roseuid 4104DA94006D
     */
    public void setAppliStartDate(int l_intLineNumber, Date l_datAppliStartDate) 
    {
        final String STR_METHOD_NAME = " setAppliStartDate(int , Date )";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_START_DATE_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_datAppliStartDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�K�p�I����)<BR>
     * �K�p�I�������Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�K�p�I�������x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�K�p�I���� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_datAppliEndDate - (�K�p�I����)<BR>
     * �K�p�I�������w�肷��B<BR>
     * @@roseuid 4104DAA0001F
     */
    public void setAppliEndDate(int l_intLineNumber, Date l_datAppliEndDate) 
    {
        final String STR_METHOD_NAME = " setAppliEndDate(int , Date )";
        log.entering(STR_METHOD_NAME );
        
        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_END_DATE_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_datAppliEndDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set���p����)<BR>
     * ���p�������Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.���p�������x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@���p���� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * ���p�������w�肷��B<BR>
     * @@roseuid 4104DAAB0223
     */
	public void setUseAmt(int l_intLineNumber, double l_dblUseAmt)
    {
        final String STR_METHOD_NAME = " setUseAmt(int , double )";
        log.entering(STR_METHOD_NAME );
        
        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.USE_AMT_LABEL);
        
	//��Q�Ή� NO_2116
		//�Q�j�@@�l�Z�b�g
		String l_strUseAmt = new String(WEB3StringTypeUtility.formatNumber(l_dblUseAmt));
        
        this.setValue(l_intLineNumber, l_model, l_strUseAmt);
        log.debug("�yl_strUseAmt�z = "+l_strUseAmt);
                
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�o���]��)<BR>
     * �o���]�͂��Z�b�g����B<BR>
     * <BR>
     *   �P-�P�j �|�ڋq�I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     *       [�g���A�J�E���g�}�l�[�W��.get�ڋq�̈���] <BR>
     *          �،���ЃR�[�h=�T�[�r�X�\���o�^Params[index].get�،���ЃR�[�h()<BR>
     *          ���X�R�[�h=�T�[�r�X�\���o�^Params[index].get���X�R�[�h()<BR>  
     *          �����R�[�h�T�[�r�X�\���o�^Params[index].get�����R�[�h()<BR>  
     * <BR>
     *   �P-�Q�j �|�⏕�����I�u�W�F�N�g�̎擾�B<BR> 
     * <BR>
     *       [�ڋq�I�u�W�F�N�g.getSubAccount�̈���]<BR> 
     *          �⏕�����^�C�v="������������i�a����j"]<BR> 
     * <BR>
     *   �P-�R�j �|�ڋq�f�[�^�_�E�����[�hTransactionCallback�𐶐�<BR> 
     *       [�ڋq�f�[�^�_�E�����[�hTransactionCallback()�Ɏw�肷�����]<BR>  
     *          �⏕�����F �ڋq�I�u�W�F�N�g.getSubAccount()�̖߂�l<BR>  
     *          ���n���F   ������ԊǗ�.get������()�̖߂�l<BR>
     * <BR>
     *   �P-�S�j �ڋq�f�[�^�_�E�����[�hTransactionCallBack�����s<BR>  
     *       [doTransaction()�Ɏw�肷�����]<BR>  
     *          arg0�F QueryProcessor.TX_CREATE_NEW<BR> 
     *          arg1�F �ڋq�f�[�^�_�E�����[�hTransactionCallBack<BR> 
     * <BR>
     * �Q�j�@@�J�������f���擾<BR> 
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>  
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�o���]�̓��x��<BR> 
     * <BR>
     * �R�j�@@�l�Z�b�g<BR> 
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>  
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>  
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>  
     * �@@�l�F�@@�擾�����o���]��<BR> 
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���w�肷��B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h���w�肷��B<BR>
     * @@param l_strAccountCode - (�����R�[�h)<BR>
     * �����R�[�h���w�肷��B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4104DAB7005E
     */
    public void setPaymentPower(
        int l_intLineNumber, 
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setPaymentPower(int , String , String , String )";
        log.entering(STR_METHOD_NAME );
        
        try
        {
            Object l_obj = null;

            //�P�j�@@�o���]�͂��擾����
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //�|�g���A�J�E���g�}�l�[�W��.get�ڋq( )����ڋq�I�u�W�F�N�g���擾�B
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode, 
                l_strAccountCode);//WEB3SystemLayerException
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException

            Date l_bizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

            try
            {
                //DefaultProcessor���擾����B
                QueryProcessor l_qp = Processors.getDefaultProcessor();

                //�ڋq�f�[�^�_�E�����[�hTransactionCallback�N���X�𐶐�����B
                WEB3AdminSrvRegiAccountDataTransactionCallback l_callBack =
                    new WEB3AdminSrvRegiAccountDataTransactionCallback(l_subAccount, l_bizDate);

                //�ڋq�f�[�^�_�E�����[�hTransactionCallBack�����s����B
                //[doTransaction()�Ɏw�肷�����]
                //arg0�F QueryProcessor.TX_CREATE_NEW
                //arg1�F �ڋq�f�[�^�_�E�����[�hTransactionCallBack
                l_obj = l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callBack);
            }
            catch (DataException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DATE_LABEL);

            Double l_dblObj = (Double)l_obj;
            String l_strPaymentPower = WEB3StringTypeUtility.formatNumber(l_dblObj.doubleValue());

            log.debug("�yl_strPaymentPower�z = " + l_strPaymentPower);

            //2�j�@@�J�������f���擾<BR>
            l_model =
                this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_POWER_LABEL);

            //3�j�@@�l�Z�b�g
            this.setValue(l_intLineNumber, l_model, l_strPaymentPower);
        }
        catch (NotFoundException l_e)
        {
            WEB3BaseException l_webe = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            log.error("�o�^�ڋq�G���[.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�o����)<BR>
     * �o�������Z�b�g����B<BR>
     * <BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�ڋq�f�[�^CSV.�o�������x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�o���� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ����w�肷��B<BR>
     * @@param l_datPaymentDate - (�o����)<BR>
     * �o�������w�肷��B<BR>
     * @@roseuid 4104DACB02BF
     */
    public void setPaymentDate(int l_intLineNumber, Date l_datPaymentDate) 
    {
        final String STR_METHOD_NAME = " setPaymentDate(int , Date )";
        log.entering(STR_METHOD_NAME );
        
        //�P�j�@@�J�������f���擾<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DATE_LABEL);

        //�Q�j�@@�l�Z�b�g <BR>
        this.setValue(l_intLineNumber, l_model, l_datPaymentDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (�ڋq�f�[�^�_�E�����[�hTransactionCallback)<BR>
     * �ڋq�f�[�^�_�E�����[�hTransactionCallback<BR>
     */
    private class WEB3AdminSrvRegiAccountDataTransactionCallback
        implements TransactionCallback
    {
        /**
         * �⏕����<BR>
         * (�⏕�����I�u�W�F�N�g)<BR>
         */
        private SubAccount subAccount;

        /**
         * ��n��<BR>
         * (��n��)<BR>
         */
        private Date deliveryDate;

        /**
         * (�ڋq�f�[�^�_�E�����[�hTransactionCallback)<BR>
         * <BR>
         * �R���X�g���N�^�B<BR>
         * <BR>
         * this.�⏕���� = �i�����j�⏕���� <BR>
         * this.��n�� = �i�����j��n��<BR>
         * @@param l_subAccount - (�⏕����)<BR>
         * �⏕�����I�u�W�F�N�g<BR>
         * @@param l_datDeliveryDate - (��n��)<BR>
         * ��n��<BR>
         */
        public WEB3AdminSrvRegiAccountDataTransactionCallback(
            SubAccount l_subAccount,
            Date l_datDeliveryDate)
        {
            this.subAccount = l_subAccount;
            this.deliveryDate = l_datDeliveryDate;
        }

        /**
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * ����]�̓T�[�r�XImpl.get�o���\�z()���R�[�����A�o���]�͂��擾����B<BR>
         * <BR>
         * �P�j ����]�̓T�[�r�XImpl.get�o���\�z�`0�␳�L��`(�⏕����,��n��)���R�[������B<BR>
         * <BR>
         *     [get�o���\�z�`�␳�L��`()�Ɏw�肷�����]<BR>
         *           �⏕�����Fthis.�⏕����<BR>
         *           ��n���Fthis.��n��<BR>
         * <BR>
         * �Q�j �P�j�̖߂�l��Object�ɕϊ����ԋp����B<BR>
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            double l_dblPaymentTradingPower = 0D;

            //�P�j ����]�̓T�[�r�XImpl.get�o���\�z�`0�␳�L��`(�⏕����,��n��)���R�[������B
            //    [get�o���\�z�`�␳�L��`()�Ɏw�肷�����]
            //      �⏕�����Fthis.�⏕����
            //      ��n���Fthis.��n��
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            try
            {
                l_dblPaymentTradingPower =
                    l_tradingPowerService.getPaymentTradingPower(
                        (WEB3GentradeSubAccount)this.subAccount,
                        this.deliveryDate);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex.getErrorInfo());
            }

            //�Q�j �P�j�̖߂�l��Object�ɕϊ����ԋp����B
            Double l_paymentTradingPower = new Double(l_dblPaymentTradingPower);

            log.exiting(STR_METHOD_NAME);
            return l_paymentTradingPower;
        }
    }
}
@
