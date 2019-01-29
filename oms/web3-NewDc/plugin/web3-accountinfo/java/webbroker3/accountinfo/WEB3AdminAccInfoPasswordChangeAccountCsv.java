head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordChangeAccountCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ïؔԍ��ύX�ڋqCSV(WEB3AdminAccInfoPasswordChangeAccountCsv)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 ����� (���u) �V�K�쐬
*/

package webbroker3.accountinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ïؔԍ��ύX�ڋqCSV)<BR>
 * �Ïؔԍ��ύX�ڋqCSV<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordChangeAccountCsv extends WEB3GentradeCsvDataModel 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoPasswordChangeAccountCsv.class); 
    
    /**
     * (�،���ЃR�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�،���ЃR�[�h�h <BR>
     */
    public static  final String INSTITUTION_CODE_LABEL = "�،���ЃR�[�h";
    
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
     * (�ڋq�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ڋq���h <BR>
     */
    public static  final String ACCOUNT_NAME_LABEL = "�ڋq��";
    
    /**
     * (�Ïؔԍ��X�V�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ïؔԍ��X�V���h <BR>
     */
    public static  final String PASSWORD_UPDATED_DATE_LABEL = "�Ïؔԍ��X�V��";
    
    /**
     * (�Ïؔԍ��X�V�҃R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�Ïؔԍ��X�V�҃R�[�h�h <BR>
     */
    public static  final String PASSWORD_UPDATER_CODE_LABEL = "�Ïؔԍ��X�V�҃R�[�h";
    
    /**
     * (�Ïؔԍ��ύX�ڋqCSV)<BR>
     * �R���X�g���N�^�B <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B <BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B <BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B <BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoPasswordChangeAccountCsv
     * @@roseuid 416B89D800E6
     */
    public WEB3AdminAccInfoPasswordChangeAccountCsv() 
    {
        createKeyHeader();      //�L�[�w�b�_�����쐬����B
        createColumnHeader();   //�J�����w�b�_�����쐬����B
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()<BR>
     * �ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�،���ЃR�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�ڋq�����x�� <BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�Ïؔԍ��X�V�����x�� <BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ <BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�Ïؔԍ��X�V�҃R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * @@roseuid 416B89D800C7
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[6];

        //�،���ЃR�[�h        
        l_columnHeader[0] = new WEB3GentradeCsvColumnModel(INSTITUTION_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //���X�R�[�h
        l_columnHeader[1] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //�ڋq�R�[�h
        l_columnHeader[2] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //�ڋq��
        l_columnHeader[3] = new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //�p�X���[�h�X�V��
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(PASSWORD_UPDATED_DATE_LABEL, 4, WEB3GentradeCsvColumnModel.TIMESTAMPTYPE, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));        
        
        //�p�X���[�h�X�V�҃R�[�h
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(PASSWORD_UPDATER_CODE_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        setColumnHeader(l_columnHeader);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B <BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X<BR>
     * �ɃZ�b�g����B <BR>
     * <BR>
     * [�L�[�w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B <BR>
     * <BR>
     * (*1) ���ݓ��� <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 416B89D800C8
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
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�،���ЃR�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�،���ЃR�[�h<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@roseuid 416B89D800C9
     */
    public void setInstitutionCode(int l_intLineNumber, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(INSTITUTION_CODE_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strInstitutionCode);
      
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
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.���X�R�[�h���x�� <BR>
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
     * @@roseuid 416B89D800D6
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
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�ڋq�R�[�h���x�� <BR>
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
     * @@roseuid 416B89D800D9
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
     * (set�ڋq��)<BR>
     * �ڋq�����Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�ڋq�����x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�ڋq��<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strAccountName - �ڋq��
     * @@roseuid 416B89D800DC
     */
    public void setAccountName(int l_intLineNumber, String l_strAccountName) 
    {
        final String STR_METHOD_NAME = " setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ACCOUNT_NAME_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strAccountName);
      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�Ïؔԍ��X�V��)<BR>
     * �Ïؔԍ��X�V�����Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�p�X���[�h�X�V�����x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�Ïؔԍ��X�V��<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_datPasswordUpdatedDate - �Ïؔԍ��X�V��
     * @@roseuid 416B89D800DF
     */
    public void setPasswordUpdatedDate(int l_intLineNumber, Date l_datPasswordUpdatedDate) 
    {
        final String STR_METHOD_NAME = " setPasswordUpdatedDate(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(PASSWORD_UPDATED_DATE_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_datPasswordUpdatedDate);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set�Ïؔԍ��X�V�҃R�[�h)<BR>
     * �Ïؔԍ��X�V�҃R�[�h���Z�b�g����B <BR>
     * <BR>
     * �P�j�@@�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@�Ïؔԍ��ύX�ڋqCSV.�p�X���[�h�X�V�҃R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f�� <BR>
     * �@@�l�F�@@�Ïؔԍ��X�V�҃R�[�h<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strPasswordUpdaterCode - �Ïؔԍ��X�V�҃R�[�h
     * @@roseuid 416B89D800E2
     */
    public void setPasswordUpdaterCode(int l_intLineNumber, String l_strPasswordUpdaterCode) 
    {
        final String STR_METHOD_NAME = " setPasswordUpdaterCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(PASSWORD_UPDATER_CODE_LABEL);
        
        //�l�Z�b�g
        setValue(l_intLineNumber, l_columnModel, l_strPasswordUpdaterCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
}
@
