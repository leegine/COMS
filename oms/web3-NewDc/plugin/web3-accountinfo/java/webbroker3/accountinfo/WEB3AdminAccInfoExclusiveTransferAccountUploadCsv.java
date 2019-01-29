head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��p�U��������A�b�v���[�hCSV(WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 �J�N���V (���u) �V�K�쐬
                   2005/12/26 �A���� (���u) �d�l�ύXNo.074
                   2006/02/03 ���ہi���{���u�j�d�l�ύXNo.085
*/

package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.FinInstitutionBankRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

  
/**
 * (��p�U��������A�b�v���[�hCSV)<BR>
 * ��p�U��������A�b�v���[�hCSV<BR>
 * @@author �J�N���V
 * @@version 1.0 
 */
public class WEB3AdminAccInfoExclusiveTransferAccountUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.class);
    
    
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
     * (��s�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h��s���h <BR>
     */
    public static  final String FIN_INSTITUTION_NAME_LABEL = "��s��";
    
    /**
     * (�x�X�����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�x�X���h <BR>
     */
    public static  final String FIN_BRANCH_NAME_LABEL = "�x�X��";
    
    /**
     * (�x�X�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�x�X�R�[�h�h <BR>
     */
    public static  final String FIN_BRANCH_CODE_LABEL = "�x�X�R�[�h";
    
    /**
     * (������ޖ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h������ޖ��h <BR>
     */
    public static  final String FIN_ACCOUNT_TYPE_NAME_LABEL = "������ޖ�";
    
    /**
     * (�����ԍ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h�����ԍ��h <BR>
     */
    public static  final String FIN_ACCOUNT_NO_LABEL = "�����ԍ�";
    
    /**
     * (�������`�l���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�������`�l�h <BR>
     */
    public static  final String FIN_ACCOUNT_NAME_LABEL = "�������`�l";
    
    /**
     * (�A�b�v���[�h�t�@@�C���h�c)<BR>
     * �A�b�v���[�h�t�@@�C���h�c_��p�U��������A�b�v���[�h <BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     */
    public static  String uploadFileId = "��p�U��������A�b�v���[�h";
    
    /**
     * (��s�R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h��s�R�[�h�h <BR>
     */
    public static  final String FIN_INSTITUTION_CODE_LABEL = "��s�R�[�h";
    
    /**
     * (��p�U��������A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoExclusiveTransferAccountUploadCsv
     * @@roseuid 415B9051037F
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCsv() 
    {
        
        this.createColumnHeader();
    }
    
    /**
     * (��p�U��������A�b�v���[�hCSV)<BR>
     * <BR>
     * �R���X�g���N�^ <BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B <BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_lngUploadId - �A�b�v���[�hID
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoExclusiveTransferAccountUploadCsv
     * @@roseuid 415B90300237
     */
    public WEB3AdminAccInfoExclusiveTransferAccountUploadCsv(long l_lngUploadId) 
    {
        
        this.administratorUploadId = l_lngUploadId;
        this.createColumnHeader();
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * ��p�U��������A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     * @@return String
     * @@roseuid 415B8E170091
     */
    public String getUploadFileId() 
    {
        
        return uploadFileId;
    }
    
    /**
     * (get�����^�C�v)<BR>
     * ProductTypeEnum.���̑� ��ԋp����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 415B8F84018B
     */
    public ProductTypeEnum getProductType() 
    {
        
        return ProductTypeEnum.OTHER;
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
     * �@@���ڃ��x���F�@@��p�U�������CSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.��s�����x�� <BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.�x�X�����x�� <BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.�x�X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.������ޖ����x�� <BR>
     * �@@�J�����ԍ��F5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.�����ԍ����x�� <BR>
     * �@@�J�����ԍ��F 6 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.�������`�l���x�� <BR>
     * �@@�J�����ԍ��F 7 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@ null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@��p�U�������CSV.��s�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 8 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * @@roseuid 415B90700208
     */
    protected void createColumnHeader() 
    {
        
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 9;
        
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //index = 0
        l_models[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.BRANCH_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 1
        l_models[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.ACCOUNT_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null); 
        //index = 2
        l_models[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_INSTITUTION_NAME_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 3
        l_models[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_BRANCH_NAME_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 4
        l_models[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_BRANCH_CODE_LABEL,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 5
        l_models[5] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_ACCOUNT_TYPE_NAME_LABEL,
                5,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 6
        l_models[6] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_ACCOUNT_NO_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 7
        l_models[7] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_ACCOUNT_NAME_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        //index = 8
        l_models[8] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoExclusiveTransferAccountUploadCsv.FIN_INSTITUTION_CODE_LABEL,
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
                
        this.setColumnHeader(l_models);        
        log.exiting(STR_METHOD_NAME); 
     
    }
    
    /**
     * (get�ڋq)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾���ԋp����B<BR>
     * <BR>
     * [get�ڋq()�Ɏw�肷�����]<BR>
     * �،���ЃR�[�h�F�@@�،���ЃR�[�h<BR>
     * ���X�R�[�h�F�@@this.get���X�R�[�h(�s�ԍ�)<BR>
     * �����R�[�h�F�@@this.get�ڋq�R�[�h(�s�ԍ�)<BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@return WEB3GentradeMainAccount
     * @@roseuid 415BE81E02A5
     */
    public WEB3GentradeMainAccount getMainAccount(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " getMainAccount(int l_intLineNumber, String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME );
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
        WEB3GentradeMainAccount l_mainAccount = 
            l_accManager.getMainAccount(
                l_strInstitutionCode,
                this.getBranchCode(l_intLineNumber),
                this.getAccountCode(l_intLineNumber));
                
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B91240295
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        
        String l_strAccountCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(ACCOUNT_CODE_LABEL)
                );
        return l_strAccountCode;
    }
    
    /**
     * (get���X�R�[�h) <BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B915C013D
     */
    public String getBranchCode(int l_intLineNumber) 
    {
        
        String l_strBranchCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(BRANCH_CODE_LABEL)); 
        return l_strBranchCode;
    }
    
    /**
     * (get��s��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋�s�����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂċ�s�����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.��s�����x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B91730276
     */
    public String getFinInstitutionName(int l_intLineNumber) 
    {
        String l_strFinInstitutionName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_INSTITUTION_NAME_LABEL)); 
        return l_strFinInstitutionName;
    }
    
    /**
     * (get�x�X��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎x�X�����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĎx�X�����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.�x�X�����x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B919B0034
     */
    public String getFinBranchName(int l_intLineNumber) 
    {
        
        String l_strFinBranchName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_BRANCH_NAME_LABEL)); 
        return l_strFinBranchName;
    }
    
    /**
     * (get�x�X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̎x�X�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĎx�X�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.�x�X�R�[�h���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B91C30208
     */
    public String getFinBranchCode(int l_intLineNumber) 
    {
        
        String l_strFinBranchCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_BRANCH_CODE_LABEL)); 
        return l_strFinBranchCode;
    }
    
    /**
     * (get������ޖ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌�����ޖ����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČ�����ޖ����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.������ޖ����x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B91DF018B
     */
    public String getFinAccountTypeName(int l_intLineNumber) 
    {
        
        String l_strFinAccountTypeName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_ACCOUNT_TYPE_NAME_LABEL)); 
        return l_strFinAccountTypeName;
    }
    
    /**
     * (get�����ԍ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌����ԍ����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČ����ԍ����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.�����ԍ����x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B92090295
     */
    public String getFinAccountNo(int l_intLineNumber) 
    {
        
        String l_strFinAccountNo = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_ACCOUNT_NO_LABEL)); 
        return l_strFinAccountNo;
    }
    
    /**
     * (get�������`�l)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌������`�l���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČ������`�l���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(��p�U��������A�b�v���[�hCSV.�������`�l���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B92310266
     */
    public String getFinAccountName(int l_intLineNumber) 
    {
        
        String l_strFinAccountName = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_ACCOUNT_NAME_LABEL)); 
        return l_strFinAccountName;
    }
    
    /**
     * (get��s�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̋�s�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂċ�s�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f�� <BR>
     * �@@�@@�@@�@@�@@(��p�U��������A�b�v���[�hCSV.��s�R�[�h���x��)�̖߂�l�B <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 415B92310267
     */
    public String getFinInstitutionCode(int l_intLineNumber) 
    {
        
        String l_strFinInstitutionCode = (String) this.getValue(
                l_intLineNumber, 
                this.getColumnModel(FIN_INSTITUTION_CODE_LABEL));
        return l_strFinInstitutionCode;
    }
    
    /**
     * (validate�d���ڋq)<BR>
     * �d���ڋq���ǉ�����Ă��Ȃ����`�F�b�N���s���B <BR>
     * <BR>
     * get���X�R�[�h�i�s�ԍ��j�Cget�ڋq�R�[�h(�s�ԍ�)�ɂāA<BR>
     * �w��s�ԍ��̕��X�R�[�h�C�ڋq�R�[�h���擾����B <BR>
     * �擾�������X�R�[�h�C�ڋq�R�[�h�Ǝw��s�ԍ����O�̖��׍s�̕��X�R�[�h�C<BR>
     * �ڋq�R�[�h���r����B<BR>
     * �����l�̍s�i���X�R�[�h == ���X�R�[�h && �ڋq�R�[�h == �ڋq�R�[�h�j�����݂���ꍇ�́A<BR>
     * �ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * 
     * @@param l_intLineNumber - �s�ԍ�
     * @@throws WEB3BaseException
     * @@roseuid 415B92CF02A5
     */
    public void validateDuplicateAccount(int l_intLineNumber) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateDuplicateAccount(int l_intLineNumber)";
        log.entering(STR_METHOD_NAME);
        
        //get�ڋq�R�[�h(�ԍ�)�ɂāA�w��s�ԍ��̌ڋq�R�[�h���擾����
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);
        for(int i = 0; i < l_intLineNumber; i++)
        {        
                
            String l_strAccCode = getAccountCode(i);
            String l_strBraCode = getBranchCode(i);
            if(l_strAccountCode.equals(l_strAccCode)
                && l_strBranchCode.equals(l_strBraCode))
            {
                
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    getClass().getName() + STR_METHOD_NAME,
                    " �擾�����ڋq�R�[�h�Ǝw��s�ԍ����O�̖��׍s�̌ڋq�R�[�h���r���A�����l�����݂���");                
            }
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N<BR>
     * �@@get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���X�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�P�|�P�j�@@���X�R�[�h���擾�ł��Ȃ��ꍇ�iget���X�R�[�h() == null�j�A<BR>
     * ��O���X���[����B<BR>
     * �@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�P�|�R�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�ڋq�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�ڋq�R�[�h���擾�ł��Ȃ��ꍇ�iget�ڋq�R�[�h() == null�j�A<BR>
     * ��O���X���[����B<BR>
     * �@@�Q�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�Q�|�R�j�@@��������6byte�łȂ��ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �R�j�@@��s���̃`�F�b�N<BR>
     * �@@get��s��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get��s��()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�R�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     * �@@�R�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �S�j�@@�x�X���̃`�F�b�N<BR>
     * �@@get�x�X��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�x�X��()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�S�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     * �@@�S�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �T�j�@@�x�X�R�[�h�̃`�F�b�N<BR>
     * �@@get�x�X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�x�X�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�T�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�T�|�Q�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �U�j�@@������ޖ��̃`�F�b�N<BR>
     * �@@get������ޖ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get������ޖ�()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�U�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     * �@@�U�|�Q�j�@@��������5�i10byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �V�j�@@�����ԍ��̃`�F�b�N<BR>
     * �@@get�����ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�����ԍ�()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�V�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�V�|�Q�j�@@��������7byte���傫���ꍇ�́A��O���X���[����B<BR>
     * <BR>
     * �W�j�@@�������`�l�̃`�F�b�N<BR>
     * �@@get�������`�l()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�������`�l()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�W�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     * �@@�W�|�Q�j�@@��������20�i40byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     * 
     * =================================================================== <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01170<BR>
     * ���X���[�����O�́A�S�āu��p�U��������t�@@�C�����e�G���[�v�̗�O�B
     * =================================================================== <BR>
     * 
     * �@@�X�|�P�j�@@�����͂̏ꍇ�́A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * �@@�X�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * �@@�X�|�R�j�@@��������4byte���傫���ꍇ�́A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * �@@�X�|�S�j�@@���Z�@@�փe�[�u���Ƀf�[�^���݂̃`�F�b�N<BR>
     * �@@�@@�@@isExist���Z�@@�ցi�j�ɂāA���݃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�@@[isExist���Z�@@��()�Ɏw�肷�����]<BR>
     * �@@�@@�@@��s�R�[�h�F�X�j�Ŏ擾������s�R�[�h<BR>
     * �@@�@@�@@�x�X�R�[�h�F�T�j�Ŏ擾�����x�X�R�[�h<BR>
     * <BR>
     * �@@�@@�@@�X�|�S�|�P�j�@@���݂��Ȃ��ifalse�j�̏ꍇ�́A��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag:   BUSINESS_ERROR_01170<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@throws WEB3BaseException
     * @@roseuid 415B942C00D0
     */
    public void validateDetailsLine(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " validateDetailsLine(int l_intLineNumber, String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        //get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);
        
        //�P�|�P�j�@@���X�R�[�h���擾�ł��Ȃ��ꍇ�iget���X�R�[�h() == null�j�A��O���X���[����
        if(l_strBranchCode == null || "".equals(l_strBranchCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[");
        }
        
        //�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if(!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[");
        }
        
        //�P�|�R�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(l_strBranchCode) != 3)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[");
        }
        
        //get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);
        
        //�Q�|�P�j�@@�ڋq�R�[�h���擾�ł��Ȃ��ꍇ�iget�ڋq�R�[�h() == null�j�A��O���X���[����
        if(l_strAccountCode == null || "".equals(l_strAccountCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[");
        }
        
        //�Q�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if(!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[");
        }
        
        //�Q�|�R�j�@@��������6byte�łȂ��ꍇ�́A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(l_strAccountCode) != 6)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[");
        }
        
        //get��s��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��        
        String l_strFinInstitutionName = this.getFinInstitutionName(l_intLineNumber);
        if (l_strFinInstitutionName != null && !l_strFinInstitutionName.equals(""))
        {
            //�R�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����
            if(!WEB3StringTypeUtility.isMulti(l_strFinInstitutionName))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
        
            //�R�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����
            if(WEB3StringTypeUtility.getByteLength(l_strFinInstitutionName) > 32)
            {
            
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
        }
        
        //get�x�X��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strFinBranchName = this.getFinBranchName(l_intLineNumber);
        if (l_strFinBranchName != null && !l_strFinBranchName.equals(""))
        {
            //4-1) �S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����
            if(!WEB3StringTypeUtility.isMulti(l_strFinBranchName))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
            
            //�S�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����
            if(WEB3StringTypeUtility.getByteLength(l_strFinBranchName) > 32)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
        }
        
        //get�x�X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strFinBranchCode = this.getFinBranchCode(l_intLineNumber);
        if (l_strFinBranchCode != null && !l_strFinBranchCode.equals(""))
        {
            //5-1) ���p�����ȊO���܂܂��ꍇ�́A��O���X���[����
            if(!WEB3StringTypeUtility.isDigit(l_strFinBranchCode))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
            
            //�T�|�Q�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����
            if(WEB3StringTypeUtility.getByteLength(l_strFinBranchCode) != 3)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
        }
        
        //get������ޖ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strFinAccountTypeName = this.getFinAccountTypeName(l_intLineNumber);
        if (l_strFinAccountTypeName != null && !l_strFinAccountTypeName.equals(""))
        {
            //6-1) �S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����
            if(!WEB3StringTypeUtility.isMulti(l_strFinAccountTypeName))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
            
            //�U�|�Q�j�@@��������5�i10byte�j���傫���ꍇ�́A��O���X���[����
            if(WEB3StringTypeUtility.getByteLength(l_strFinAccountTypeName) > 10)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
        }
        
        //get�����ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strFinAccountNo = this.getFinAccountNo(l_intLineNumber);
        if (l_strFinAccountNo != null && !l_strFinAccountNo.equals(""))
        {
            //�V�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����
            if(!WEB3StringTypeUtility.isDigit(l_strFinAccountNo))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
    
            //7-2) ��������7byte���傫���ꍇ�́A��O���X���[����
            if(WEB3StringTypeUtility.getByteLength(l_strFinAccountNo) > 7)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
        }
        
        //get�������`�l()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��]
        String l_strFinAccountName = this.getFinAccountName(l_intLineNumber);
        if (l_strFinAccountName != null && !l_strFinAccountName.equals(""))
        {
            //�W�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����
            if(!WEB3StringTypeUtility.isMulti(l_strFinAccountName))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
            
            //�W�|�Q�j�@@��������20�i40byte�j���傫���ꍇ�́A��O���X���[����
            if(WEB3StringTypeUtility.getByteLength(l_strFinAccountName) > 40)
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    " ��p�U��������t�@@�C�����e�G���[");
            }
        }
        
        //get��s�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        String l_strFinInstitutionCode = this.getFinInstitutionCode(l_intLineNumber);
        
        // �X�|�P�j�@@�����͂̏ꍇ�́A��O���X���[����B 
        if (l_strFinInstitutionCode == null || l_strFinInstitutionCode.length() == 0) 
        {
			log.debug(STR_METHOD_NAME);
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01170, 
				this.getClass().getName() + "." + STR_METHOD_NAME,
				" ��p�U��������t�@@�C�����e�G���[�B");
        }
        
        // �X�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if(!WEB3StringTypeUtility.isDigit(l_strFinInstitutionCode))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[�B");
        }
            
        // �X�|�R�j�@@��������4byte���傫���ꍇ�́A��O���X���[����B 
        if(WEB3StringTypeUtility.getByteLength(l_strFinInstitutionCode) > 4)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[�B");
        }
            
        // �X�|�S�j�@@���Z�@@�փe�[�u���Ƀf�[�^���݂̃`�F�b�N 
        if(!isExistFinInstitution(l_strFinInstitutionCode, l_strFinBranchCode))
        {
            //�X�|�S�|�P�j�@@���݂��Ȃ��ifalse�j�̏ꍇ�́A��O���X���[����B
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01170,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                " ��p�U��������t�@@�C�����e�G���[�B");
        }
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (isExist���Z�@@��)<BR>
     * ���Z�@@�փe�[�u���Ɋ����s�����݂��邩�𔻒肷��B<BR>
     * <BR>
     * �P�j�@@���Z�@@�ցi��s�j�}�X�^���A�w��̋�s�R�[�h�Ǝx�X�R�[�h�� <BR>
     * �@@�@@�@@�Y������s���擾����B <BR>
     * �Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B<BR>
     * @@param l_strFinInstitutionCode - ��s�R�[�h
     * @@param l_strBranchCode - �x�X�R�[�h
     * @@return boolean
     * @@throws WEB3BaseException
     */
    protected boolean isExistFinInstitution(String l_strFinInstitutionCode, String l_strBranchCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isExistFinInstitution(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���Z�@@�ցi��s�j�}�X�^���A�w��̋�s�R�[�h�Ǝx�X�R�[�h�ɊY������s���擾����B
        FinInstitutionBankRow l_finInstitutionBankRow = null;
        try
        {
            l_finInstitutionBankRow = FinInstitutionBankDao.findRowByPk(
                l_strFinInstitutionCode,
                l_strBranchCode);
            
            //�Q�j�@@�s���擾�ł����true�A�擾�ł��Ȃ����false��ԋp����B
            if (l_finInstitutionBankRow != null) 
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
        catch (DataFindException l_ex)
        {
            //��O���X���[
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        catch (DataException l_ex)
        {
            //��O���X���[
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B");
        }
    }
}
@
