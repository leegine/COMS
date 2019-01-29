head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔���ύX�ڋqCSV (WEB3AdminAccInfoCommissionChangeAccountCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �d�� (���u) �V�K�쐬
*/

package webbroker3.accountinfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�萔���ύX�ڋqCSV)<BR>
 * �萔���ύX�ڋq�_�E�����[�h�ō쐬����CSV�t�@@�C���f�[�^�N���X <BR>
 * 
 * 
 */
public class WEB3AdminAccInfoCommissionChangeAccountCsv extends WEB3GentradeCsvDataModel 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountCsv.class); 
    
    /**
     * (���X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h <BR>
     */
    public static  final String BRANCH_CODE_LABEL = "���X�R�[�h";
    
    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h <BR>
     */
    public static  final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";
    
    /**
     * (���i�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���i�R�[�h�h <BR>
     */
    public static  final String PRODUCT_CODE_LABEL = "���i�R�[�h";
    
    /**
     * (�K�p�J�n�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�K�p�J�n���h <BR>
     */
    public static  final String APPLI_START_DATE_LABEL = "�K�p�J�n��";
    
    /**
     * (�萔��No.���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�萔��No.�h <BR>
     */
    public static  final String COMMISSION_NO_LABEL = "�萔��No.";
    
    /**
     * (���������x��)<BR>
     * �萔��`�v���p�e�B�@@�h�������h <BR>
     */
    public static  final String CHARGE_RATIO_LABEL = "������";
    
    /**
     * (�K�p�I�������x��)<BR>
     * �萔��`�v���p�e�B�@@�h�K�p�I�����h <BR>
     */
    public static  final String APPLI_END_DATE_LABEL = "�K�p�I����";
    
    /**
     * (���i�R�[�h_��ꊔ��)<BR>
     * �萔��`�v���p�e�B�@@���i�R�[�h_��ꊔ��<BR>
     */
    public static  final String PRODUCT_CODE_LISTING_STOCK = "10";
    
    /**
     * (���i�R�[�h_�X������)<BR>
     * �萔��`�v���p�e�B�@@���i�R�[�h_�X������<BR>
     */
    public static  final String PRODUCT_CODE_OTC_STOCK = "11";
    
    /**
     * (�萔���ύX�ڋqCSV)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B <BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B <BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B <BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoCommissionChangeAccountCsv
     * @@roseuid 4146CC7803BB
     */
    public WEB3AdminAccInfoCommissionChangeAccountCsv() 
    {
        super();
        this.createColumnHeader();
        this.createKeyHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂ�<BR>
     * �C���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.���i�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�K�p�J�n�����x�� <BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ <BR>
     * �@@�@@new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�萔��No.���x�� <BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.���������x�� <BR>
     * �@@�J�����ԍ��F5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�K�p�I�������x�� <BR>
     * �@@�J�����ԍ��F 6 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * @@roseuid 4146CB420283
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[7];
        
        //���X�R�[�h        
        l_columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //�ڋq�R�[�h        
        l_columnHeader[1] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //���i�R�[�h
        l_columnHeader[2] = new WEB3GentradeCsvColumnModel(PRODUCT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //�K�p�J�n��
        l_columnHeader[3] = new WEB3GentradeCsvColumnModel(APPLI_START_DATE_LABEL, 3, WEB3GentradeCsvColumnModel.DATETYPE, new SimpleDateFormat("yyyyMMdd"));
        
        //�萔��No.
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(COMMISSION_NO_LABEL, 4, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //������
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(CHARGE_RATIO_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //�K�p�I����
        l_columnHeader[6] = new WEB3GentradeCsvColumnModel(APPLI_END_DATE_LABEL, 6, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        setColumnHeader(l_columnHeader);
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (create�L�[�w�b�_)<BR>
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
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 4146CB48013B
     */
    protected void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeader = new String[1];
        
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");
        
        setKeyHeader(l_strKeyHeader);
        
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
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.���X�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@���X�R�[�h<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strBranchCode - ���X�R�[�h
     * @@roseuid 4146CCB40216
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode) 
    {
        final String STR_METHOD_NAME = " setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(BRANCH_CODE_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strBranchCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    
    /**
     * (set�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�ڋq�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�ڋq�R�[�h<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@roseuid 4146CD17036D
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ACCOUNT_CODE_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strAccountCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set���i�R�[�h)<BR>
     * ���i�R�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.���i�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@���i�R�[�h<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strProductCode - ���i�R�[�h
     * @@roseuid 4146CDAA0216
     */
    public void setProductCode(int l_intLineNumber, String l_strProductCode) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(PRODUCT_CODE_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strProductCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set�K�p�J�n��)<BR>
     * �K�p�J�n�����Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�K�p�J�n�����x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�K�p�J�n��<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_datAppliStartDate - �K�p�J�n��
     * @@roseuid 4146CD4601F6
     */
    public void setAppliStartDate(int l_intLineNumber, Date l_datAppliStartDate) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, Date)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(APPLI_START_DATE_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_datAppliStartDate);
      
        log.exiting(STR_METHOD_NAME);     
    }
     
    
    /**
     * (set�萔��No.)<BR>
     * �萔��No.���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�萔��No.���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�萔��No.<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strCommissionNo - �萔��No.
     * @@roseuid 4146CDD5012B
     */
    public void setCommissionNo(int l_intLineNumber, String l_strCommissionNo) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(COMMISSION_NO_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strCommissionNo);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set������)<BR>
     * ���������Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.���������x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@������<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strChargeRatio - ������
     * @@roseuid 4146CDF00310
     */
    public void setChargeRatio(int l_intLineNumber, String l_strChargeRatio) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(CHARGE_RATIO_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strChargeRatio);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set�K�p�I����)<BR>
     * �K�p�I�������Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�萔���ύX�ڋqCSV.�K�p�I�������x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�K�p�I������"yyyyMMdd"�Ƀt�H�[�}�b�g�������́B<BR>
     * <BR>
     * �@@�@@�A���A�K�p�N���� == ���t�ő�l�iHighValue�F9999/12/31 00�F00�F00�j<BR>
     * �̏ꍇ�́A<BR>
     * �@@�@@�h99999999�h���Z�b�g����B<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_datAppliEndDate - �K�p�I����
     * @@roseuid 4146CD7003BB
     */
    public void setAppliEndDate(int l_intLineNumber, Date l_datAppliEndDate) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(APPLI_END_DATE_LABEL);
        
        //�l�Z�b�g
        Calendar l_calendar = new GregorianCalendar(9999,Calendar.DECEMBER, 31);
        Date l_datAppliEndDates = l_calendar.getTime();
     
        if (WEB3DateUtility.compareToDay(l_datAppliEndDate, l_datAppliEndDates) == 0)
        {
            setValue(l_intLineNumber, l_columnModel, WEB3DateUtility.formatDate(l_datAppliEndDate, "99999999"));
        
        }
        else
        {      
            setValue(l_intLineNumber, l_columnModel, WEB3DateUtility.formatDate(l_datAppliEndDate, "yyyyMMdd")); 
                      
        }  
        log.exiting(STR_METHOD_NAME);
    }
}
@
