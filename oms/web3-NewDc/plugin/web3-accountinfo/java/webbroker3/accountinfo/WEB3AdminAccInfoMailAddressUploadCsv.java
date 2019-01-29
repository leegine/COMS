head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���A�h���X�A�b�v���[�hCSV(WEB3AdminAccInfoMailAddressUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/14 ������ (���u) �V�K�쐬
                   2006/03/24 ������ (���u) ���f��No.102  
                   2006/04/03 ���� (���u) ���f��No.103 
*/

package webbroker3.accountinfo;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.accountinfo.define.WEB3AccInfoDeleteDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoMailFlagDef;
import webbroker3.accountinfo.message.WEB3AdminAccInfoMailAddressUploadConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���[���A�h���X�A�b�v���[�hCSV)<BR>
 * ���[���A�h���X�A�b�v���[�hCSV<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoMailAddressUploadCsv.class);
    
    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * �A�b�v���[�h�t�@@�C���h�c_���[���A�h���X�A�b�v���[�h <BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c��<BR>
     * �i�[���镶���� <BR>
     */
    public static final String UPLOAD_FILEID = "���[���A�h���X�A�b�v���[�h";
    
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
     * (���[���A�h���X���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���[���A�h���X�h <BR>
     */
    public static  final String MAIL_ADDRESS_LABEL = "���[���A�h���X";

    /**
     * (�ē����[�����M�t���O���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�ē����[�����M�t���O�h <BR>
     */
    public static  final String INFORMATION_MAIL_FLAG_LABEL = "�ē����[�����M�t���O";

    /**
     * (�폜�敪)<BR>
     * �萔��`�v���p�e�B�@@�h�폜�敪�h<BR>
     * 0�F���[���A�h���X�A�ē����[�����M�t���O�X�V<BR>
     * 1�F���[���A�h���X�A�ē����[�����M�t���O�폜<BR>
     */
    public static  final String DELETE_DIV = "�폜�敪";

    /**
     * (���E�s��)<BR>
     * �������E����<BR>
     */
    public static  final Integer LIMIT_LINES = new Integer(500);
    
    /**
     * (�A�b�v���[�h���~)<BR>
     * �A�b�v���[�h���~�p�R�����g<BR>
     */
    public static  final String UPLOAD_TERMINATE = "���~";
    
    /**
     * (���[���A�h���X�A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^<BR>  
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B<BR>
     * @@roseuid 4147E7B6026C
     */
    public WEB3AdminAccInfoMailAddressUploadCsv() 
    {
        final String STR_METHOD_NAME = "WEB3AdminAccInfoMailAddressUploadCsv()";
        log.entering(STR_METHOD_NAME);
        
        //this.create�J�����w�b�_()���R�[������B
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (���[���A�h���X�A�b�v���[�hCSV)<BR>
     * �A�b�v���[�h�f�[�^���f���𐶐�����B<BR>  
     * <BR>
     * �����~�̂Ƃ��̂ݎg�p�B<BR> 
     * [�R���X�g���N�^�̈���]  <BR>
     * �A�b�v���[�h�h�c�F�@@���N�G�X�g�f�[�^.�A�b�v���[�h�h�c<BR>
     * @@param l_lngUploadId - �A�b�v���[�hID
     * @@roseuid 4147E7B6026C
     */
    public WEB3AdminAccInfoMailAddressUploadCsv(long l_lngUploadId) 
    {
        super.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR> 
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0 <BR> 
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>  
     * �@@���ڃ��x���F�@@���[���A�h���XCSV.���X�R�[�h���x��<BR> 
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR> 
     * �@@���t�t�H�[�}�b�g�F�@@null <BR> 
     * <BR>
     * �|�@@index = 1 <BR> 
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>  
     * �@@���ڃ��x���F�@@���[���A�h���XCSV.�ڋq�R�[�h���x��<BR>  
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>  
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>  
     * <BR>
     * �|�@@index = 2 <BR> 
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>  
     * �@@���ڃ��x���F�@@���[���A�h���XCSV.���[���A�h���X���x��<BR>  
     * �@@�J�����ԍ��F 2 <BR> 
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>  
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>  
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>  
     * �@@���ڃ��x���F�@@���[���A�h���XCSV.�ē����[�����M�t���O���x��<BR>  
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>  
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>  
     * <BR>
     * �|�@@index = 4 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>  
     * �@@���ڃ��x���F�@@���[���A�h���XCSV.�폜�敪���x��<BR>  
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>  
     * �@@���t�t�H�[�}�b�g�F�@@null<BR> 
     * <BR>
     * @@roseuid 4147E7B6024D
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 5;
        columnHeader = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
                
        //index = 0
        // [CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@���[���A�h���XCSV.���X�R�[�h���x��
        // �@@�J�����ԍ��F 0 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null 
        columnHeader[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.BRANCH_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =1
        // [CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@���[���A�h���XCSV.�ڋq�R�[�h���x��
        // �@@�J�����ԍ��F 1 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        columnHeader[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.ACCOUNT_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =2
        // [CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@���[���A�h���XCSV.���[���A�h���X���x��
        // �@@�J�����ԍ��F 2 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        columnHeader[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.MAIL_ADDRESS_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =3
        // [CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@���[���A�h���XCSV.�ē����[�����M�t���O���x��
        // �@@�J�����ԍ��F 3 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        columnHeader[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.INFORMATION_MAIL_FLAG_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //index =4
        // [CSV�J�������f�� �R���X�g���N�^�̈���]
        // �@@���ڃ��x���F�@@���[���A�h���XCSV.�폜�敪���x��
        // �@@�J�����ԍ��F 4 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������
        // �@@���t�t�H�[�}�b�g�F�@@null
        columnHeader[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAccInfoMailAddressUploadCsv.DELETE_DIV,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B<BR>  
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>  
     * �s�ԍ��F�@@�s�ԍ� <BR> 
     * �J�����F�@@get�J�������f��(���[���A�h���X�A�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l�B<BR> 
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strAccountCode = (String)this.getValue(
            l_intLineNumber, 
            this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.ACCOUNT_CODE_LABEL));
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }

    /**
     * (get���X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B<BR>  
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B<BR>  
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>  
     * �s�ԍ��F�@@�s�ԍ� <BR> 
     * �J�����F�@@get�J�������f��(���[���A�h���X�A�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l�B<BR> 
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getBranchCode(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strBranchCode = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.BRANCH_CODE_LABEL)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * (get���[���A�h���X)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̃��[���A�h���X���擾����B<BR>  
     * <BR>
     * this.get���ڒl()�ɂă��[���A�h���X���擾���ԋp����B<BR>  
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(���[���A�h���X�A�b�v���[�hCSV.���[���A�h���X���x��)�̖߂�l�B<BR> 
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getMailAddress(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getMailAddress(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strMailAddress = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.MAIL_ADDRESS_LABEL)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strMailAddress;
    }
    
    /**
     * (get�ē����[�����M�t���O)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̈ē����[�����M�t���O���擾����B<BR>  
     * <BR>
     * this.get���ڒl()�ɂĈē����[�����M�t���O���擾���ԋp����B<BR>  
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR> 
     * �s�ԍ��F�@@�s�ԍ�  <BR>
     * �J�����F�@@get�J�������f��(���[���A�h���X�A�b�v���[�hCSV.�ē����[�����M�t���O���x��)�̖߂�l�B<BR> 
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getInformationMailFlag(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getInformationMailFlag(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strInnerMailSendFlag = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.INFORMATION_MAIL_FLAG_LABEL)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strInnerMailSendFlag;
    }
    
    /**
     * (get�폜�敪)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̍폜�敪���擾����B<BR>  
     * <BR>
     * this.get���ڒl()�ɂč폜�敪���擾���ԋp����B<BR>  
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(���[���A�h���X�A�b�v���[�hCSV.�폜�敪���x��)�̖߂�l�B<BR> 
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getDeleteDiv(int l_intLineNumber) 
    {
        final String STR_METHOD_NAME = " getDeleteDiv(int)";
        log.entering(STR_METHOD_NAME );
        
        String l_strDeleteDiv = (String)this.getValue(
                l_intLineNumber, 
                this.getColumnModel(WEB3AdminAccInfoMailAddressUploadCsv.DELETE_DIV)); 
        
        log.exiting(STR_METHOD_NAME);
        return l_strDeleteDiv;
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C��ID)<BR>
     * ���[���A�h���X�A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR> 
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     * <BR>
     * @@return String
     * @@roseuid 4147E7B6025D
     */
    public String getUploadFileId() 
    {

        //���[���A�h���X�A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B
    	return WEB3AdminAccInfoMailAddressUploadCsv.UPLOAD_FILEID;
    }
    
    /**
     * (check���׍s��)<BR>
     * ���׍s���̃`�F�b�N���s���B<BR>  
     * <BR>
     * ���N�G�X�g�f�[�^.uploadFile.length�Ŏ擾�������׍s�����`�F�b�N����B<BR> 
     * <BR>
     * ���E�����������׍s�����傫���ꍇ�A��O���X���[����B<BR>  
     * <BR>
     * ��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B<BR> 
     * �w���R�[�h�������������E�l�𒴂��Ă��܂��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02418�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02418<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
     */
    public void checkDetailLines(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " checkDetailLines(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        if (l_request instanceof WEB3AdminAccInfoMailAddressUploadConfirmRequest)
        {
            String[] uploadFile = 
                ((WEB3AdminAccInfoMailAddressUploadConfirmRequest)l_request).uploadFile;
            if (uploadFile.length > LIMIT_LINES.intValue())
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("���R�[�h�������������E�l�𒴂��Ă��܂��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02418,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���R�[�h�������������E�l�𒴂��Ă��܂��B");
            }            
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate�d���ڋq)<BR>
     * �d���ڋq���ǉ�����Ă��Ȃ����`�F�b�N���s���B<BR>  
     * <BR>
     * get���X�R�[�h�i�s�ԍ��j�Cget�ڋq�R�[�h(�s�ԍ�)�ɂāA�w��s�ԍ��̕��X�R�[�h�C�ڋq�R�[�h���擾����B<BR>  
     * �擾�������X�R�[�h�C�ڋq�R�[�h�Ǝw��s�ԍ����O�̖��׍s�̕��X�R�[�h�C�ڋq�R�[�h���r����B<BR> 
     * �����l�̍s�i���X�R�[�h == ���X�R�[�h && �ڋq�R�[�h == �ڋq�R�[�h�j�����݂���ꍇ�́A<BR>
     * �ڋq���d�����ēo�^����Ă���Ɣ��肵�A��O���X���[����B<BR>  
     * <BR>
     * ��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B<BR> 
     * �w�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�x�i�G���[�R�[�h�FBUSINESS_ERROR_00517�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
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
                log.exiting(STR_METHOD_NAME);  
                log.debug("�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    getClass().getName() + STR_METHOD_NAME,
                    "�d���ڋq�`�F�b�N�G���[�i�����l�̍s�����݂���j�B");                
            }
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR> 
     * <BR>
     * �P�j�@@���X�R�[�h�̃`�F�b�N <BR>
     * �@@get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR> 
     * <BR>
     * �@@[get���X�R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�|�P�j�@@���X�R�[�h���擾�ł��Ȃ��ꍇ�iget���X�R�[�h() == null�j�A<BR> 
     * �@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * �@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�P�|�R�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B <BR>
     * �@@�P�|�S�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B <BR>
     * �@@�@@�@@�@@�@@�@@�w���X�R�[�h�E�ڋq�R�[�h���s���ł��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02414�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02414<BR>
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N <BR>
     * �@@get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR> 
     * <BR>
     * �@@[get�ڋq�R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�Q�|�P�j�@@�ڋq�R�[�h���擾�ł��Ȃ��ꍇ�iget�ڋq�R�[�h() == null�j�A<BR> 
     *             ��O���X���[����B<BR> 
     * �@@�Q�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR> 
     * �@@�Q�|�R�j�@@��������6byte�łȂ��ꍇ�́A��O���X���[����B <BR>
     * �@@�Q�|�S�j�@@this.get�ڋq()���R�[������B���ڋq�R�[�h�`�F�b�N <BR>
     * �@@�@@[get�ڋq()�Ɏw�肷�����] <BR>
     * �@@�@@�s�ԍ��F�@@�s�ԍ� <BR>
     * �@@�@@�،���ЃR�[�h�F�@@�،���ЃR�[�h <BR>
     * <BR>
     * �@@�Q�|�T�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B <BR>
     * �@@�@@�@@�@@�@@�@@�w���X�R�[�h�E�ڋq�R�[�h���s���ł��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02414�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02414<BR>
     * <BR>
     * �R�j�@@�폜�敪�̃`�F�b�N <BR>
     * �@@get�폜�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR> 
     * <BR>
     * �@@[get�폜�敪()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�R�|�P�j�@@�폜�敪���擾�ł��Ȃ��ꍇ�iget�폜�敪() == null�j�A<BR> 
     *             ��O���X���[����B <BR>
     * �@@�R�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�R�|�R�j�@@��������1byte���傫���ꍇ�́A��O���X���[����B<BR> 
     * �@@�R�|�S�j�@@�擾�����������w0�x�A�w1�x�ȊO�̏ꍇ�́A��O���X���[����B<BR> 
     * �@@�R�|�T�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\�L����B <BR>
     * �@@�@@�@@�@@�@@�@@�w�폜�敪���s���ł��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02417�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02417<BR> 
     * <BR>
     * �S�j�@@�X�V�Ώۃ`�F�b�N <BR>
     * <BR>
     * �@@get�폜�敪()�ɂāA�擾�����l���w0�x�̏ꍇ�ȉ��̃`�F�b�N���s���B<BR> 
     * <BR>
     * �@@get���[���A�h���X()�ɂāA�w��s�ԍ��̃f�[�^���擾����B<BR> 
     * �@@get�ē����[�����M�t���O()�ɂāA�w��s�ԍ��̃f�[�^���擾����B<BR> 
     * <BR>
     * �@@[get���[���A�h���X()�Aget�ē����[�����M�t���O()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�S�|�P�j�@@���[���A�h���X�A�ē����[�����M�t���O���Ɏ擾�ł��Ȃ��ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�iget���[���A�h���X() == null && get�ē����[�����M�t���O() == null�j�A<BR> 
     * �@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     * �@@�S�|�Q�j�@@���[���A�h���X���擾�ł����ꍇ�iget���[���A�h���X() != null�j�A<BR> 
     * �@@�@@�@@�@@�@@�@@�ȉ��̏������s���B <BR>
     * �@@�S�|�Q�|�P�j�@@�V�X�e�������N���X.isMailAddress <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�i�����񂪁A���[���A�h���X�ɓK�؂ȕ���(*1)�ō\������Ă��邩���`�F�b�N����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�K�؂ł���ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@(*1) ���[���A�h���X�ɓK�؂ȕ��� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�ȉ��̔��p�������g�p�\�ō\������A�f@@�f���P�܂܂�Ă��邱�ƁB<BR> 
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!#$&|^_.()-=~[ ]+?/* <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�܂��A�f@@�f�͐擪�����C���������ł͂Ȃ����ƁB�j <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@���s���A�ԋp�l��false�̏ꍇ�A��O���X���[����B <BR>
     * <BR>
     * �@@�S�|�Q�|�Q�j�@@��������100byte���傫���ꍇ�́A��O���X���[����B<BR> 
     * �@@�S�|�Q�|�R�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�w���[���A�h���X���s���ł��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02415�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02415<BR>  
     * <BR>
     * �@@�S�|�R�j�@@�ē����[�����M�t���O���擾�ł����ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@�iget�ē����[�����M�t���O() != null�j�A�ȉ��̏������s���B<BR> 
     * �@@�S�|�R�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     * �@@�S�|�R�|�Q�j�@@��������1byte���傫���ꍇ�́A��O���X���[����B <BR>
     * �@@�S�|�R�|�R�j�@@�擾�����������w0�x�A�w1�x�ȊO�̏ꍇ�́A��O���X���[����B<BR> 
     * �@@�S�|�R�|�S�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�w�ē����[�����M�t���O���s���ł��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02416�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02416<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
     */
    public void validateDetailLine(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(int l_intLineNumber, String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        //get���X�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strBranchCode = this.getBranchCode(l_intLineNumber);
        
        //�P�|�P�j�@@���X�R�[�h���擾�ł��Ȃ��ꍇ�iget���X�R�[�h() == null�j�A��O���X���[����
        if(WEB3StringTypeUtility.isEmpty(l_strBranchCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }
        
        //�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if(!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }
        
        //�P�|�R�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(l_strBranchCode) != 3)
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }
        
        //get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s��
        String l_strAccountCode = this.getAccountCode(l_intLineNumber);
        
        //�Q�|�P�j�@@�ڋq�R�[�h���擾�ł��Ȃ��ꍇ�iget�ڋq�R�[�h() == null�j�A��O���X���[����
        if(WEB3StringTypeUtility.isEmpty(l_strAccountCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }
        
        //�Q�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if(!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }
        
        //�Q�|�R�j�@@��������6byte�łȂ��ꍇ�́A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(l_strAccountCode) != 6)
        {            
            log.exiting(STR_METHOD_NAME);
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }

        //�Q�|�S�j�@@this.get�ڋq()���R�[������B���ڋq�R�[�h�`�F�b�N
        //�@@�@@[get�ڋq()�Ɏw�肷�����] 
        // �@@�@@�s�ԍ��F�@@�s�ԍ� 
        //�@@�@@�،���ЃR�[�h�F�@@�،���ЃR�[�h 
        try
        {
            this.getAccount(l_intLineNumber, l_strInstitutionCode);
        }
        catch (WEB3BaseException l_ex)
        {
        // �@@�Q�|�T�j�@@��O���X���[�����ꍇ�A�ȉ��̃��b�Z�[�W��\������B 
        //�@@�@@�@@�@@�@@�@@�w���X�R�[�h�E�ڋq�R�[�h���s���ł��B�x�i�G���[�R�[�h�FBUSINESS_ERROR_02414�j
        //         class: WEB3BusinessLayerException
        //         tag:   BUSINESS_ERROR_02414            
            log.exiting(STR_METHOD_NAME);
            log.error("���X�R�[�h�E�ڋq�R�[�h���s���ł��B",l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
        }
        
        //get�폜�敪()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        String l_strDeleteDiv = this.getDeleteDiv(l_intLineNumber);
        
        //�R�|�P�j�@@�폜�敪���擾�ł��Ȃ��ꍇ�iget�폜�敪() == null�j�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_strDeleteDiv))
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�폜�敪���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�폜�敪���s���ł��B");
        }
        
        //�R�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (!WEB3StringTypeUtility.isSingle(l_strDeleteDiv))
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�폜�敪���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�폜�敪���s���ł��B");
        }
        
        //�R�|�R�j�@@��������1byte���傫���ꍇ�́A��O���X���[����B
        if(WEB3StringTypeUtility.getByteLength(l_strDeleteDiv) > 1)
        {            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�폜�敪���s���ł��B");
        }
        
        //�R�|�S�j�@@�擾�����������w0�x�A�w1�x�ȊO�̏ꍇ�́A��O���X���[����B
        if (!WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_UPDATE.equals(l_strDeleteDiv)
            && !WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_DELETE.equals(l_strDeleteDiv))
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("�폜�敪���s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02417,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�폜�敪���s���ł��B");
        }
        
        //get�폜�敪()�ɂāA�擾�����l���w0�x�̏ꍇ�ȉ��̃`�F�b�N���s���B
        if (WEB3AccInfoDeleteDivDef.INFORMATION_MAIL_FLAG_UPDATE.equals(l_strDeleteDiv))
        {
            //get���[���A�h���X()�ɂāA�w��s�ԍ��̃f�[�^���擾����B
            String l_strMailAddress = this.getMailAddress(l_intLineNumber);           
            //get�ē����[�����M�t���O()�ɂāA�w��s�ԍ��̃f�[�^���擾����B
            String l_strInnerMailSendFlag = this.getInformationMailFlag(l_intLineNumber);
            
            //�S�|�P�j�@@���[���A�h���X�A�ē����[�����M�t���O���Ɏ擾�ł��Ȃ��ꍇ 
            //�iget���[���A�h���X() == null && get�ē����[�����M�t���O() == null�j�A
            //��O���X���[����B
            if (WEB3StringTypeUtility.isEmpty(l_strMailAddress) && 
                WEB3StringTypeUtility.isEmpty(l_strInnerMailSendFlag))
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("���[���A�h���X���s���ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���[���A�h���X���s���ł��B");
            }
            
            //�S�|�Q�j�@@���[���A�h���X���擾�ł����ꍇ�iget���[���A�h���X() != null�j�A�ȉ��̏������s���B
            if (!WEB3StringTypeUtility.isEmpty(l_strMailAddress))
            {
                //�S�|�Q�|�P�j�@@�V�X�e�������N���X.isMailAddress 
                //�����񂪁A���[���A�h���X�ɓK�؂ȕ���(*1)�ō\������Ă��邩���`�F�b�N����B
                //�K�؂ł���ꍇ��true���A�����łȂ��ꍇ��false��Ԃ��B
                if (!WEB3StringTypeUtility.isMailAddress(l_strMailAddress))
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("���[���A�h���X���s���ł��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���[���A�h���X���s���ł��B");
                }
                
                //�S�|�Q�|�Q�j�@@��������100byte���傫���ꍇ�́A��O���X���[����B
                if (WEB3StringTypeUtility.getByteLength(l_strMailAddress) > 100)
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("���[���A�h���X���s���ł��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02415,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���[���A�h���X���s���ł��B");
                }
            }
            
            //�S�|�R�j�@@�ē����[�����M�t���O���擾�ł����ꍇ�iget�ē����[�����M�t���O() != null�j�A
            //�ȉ��̏������s���B
            if (!WEB3StringTypeUtility.isEmpty(l_strInnerMailSendFlag))
            {
                //�S�|�R�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
                if (!WEB3StringTypeUtility.isSingle(l_strInnerMailSendFlag))
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("�ē����[�����M�t���O���s���ł��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02416,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ē����[�����M�t���O���s���ł��B");
                }
                
                //�S�|�R�|�Q�j�@@��������1byte���傫���ꍇ�́A��O���X���[����B
                if (WEB3StringTypeUtility.getByteLength(l_strInnerMailSendFlag) > 1)
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("�ē����[�����M�t���O���s���ł��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02416,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ē����[�����M�t���O���s���ł��B");
                }
                
                //�S�|�R�|�R�j�@@�擾�����������w0�x�A�w1�x�ȊO�̏ꍇ�́A��O���X���[����B
                if (!WEB3AccInfoMailFlagDef.FALSE.equals(l_strInnerMailSendFlag) 
                    && !WEB3AccInfoMailFlagDef.TRUE.equals(l_strInnerMailSendFlag))
                {
                    log.exiting(STR_METHOD_NAME);
                    log.debug("�ē����[�����M�t���O���s���ł��B");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02416,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�ē����[�����M�t���O���s���ł��B");
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�ڋq)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�I�u�W�F�N�g���擾����B<BR> 
     * <BR>
     * �A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾���ԋp����B<BR> 
     * <BR>
     * [get�ڋq()�Ɏw�肷�����] <BR>
     * �،���ЃR�[�h�F�@@�،���ЃR�[�h <BR>
     * ���X�R�[�h�F�@@this.get���X�R�[�h(�s�ԍ�)<BR>
     * �����R�[�h�F�@@this.get�ڋq�R�[�h(�s�ԍ�)<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ�
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@return MainAccount - �ڋq
     * @@throws WEB3BaseException
     * @@roseuid 4147E7B6025D
     */
    public MainAccount getAccount(int l_intLineNumber, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getMainAccount(int l_intLineNumber, String l_strInstitutionCode)";
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
     * (get�A�b�v���[�h�ŐV����)<BR>
     * ���Y�A�b�v���[�h�t�@@�C���Ɋ֘A����A�b�v���[�h�ŐV�������擾����B<BR> 
     * <BR>
     * �ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v���������A<BR> 
     * �擾�����s�I�u�W�F�N�g��ԋp����B <BR>
     * ���R�[�h���Ȃ��ꍇ��null��ԋp����B <BR>
     * <BR>
     * [����] <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�،���ЃR�[�h = this.get�،���ЃR�[�h() <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c = this.get�A�b�v���[�h�t�@@�C���h�c()<BR> 
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�����^�C�v = this.get�����^�C�v() <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�f�[�^�L�[ = ����.�f�[�^�L�[ <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.���l�P != "���~" <BR>
     *   �����l�P���r���鎞�A���ڂ�NULL�̏ꍇ�A�������"NULL"�� <BR>
     *      �u�������Ă����r����B(SQL���Fnvl(note1,'NULL') != '���~') <BR>
     * <BR>
     * [�擾��] <BR>
     * �A�b�v���[�h�J�n�����@@�~���i�Fdesc�j<BR>
     * <BR>
     * @@param l_lngUploadKey - �f�[�^�L�[
     * @@return AdministratorUploadRow -�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s
     * @@roseuid 4147E7B6025D
     */
    public AdministratorUploadRow getUploadNewHistory(long l_lngUploadKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getUploadNewHistory(long)";
        log.entering(STR_METHOD_NAME );
        List l_lisRecords;
        String l_strInstitutionCode = null;          //�،���ЃR�[�h
        String l_strUploadFileId = null;             //�A�b�v���[�h�t�@@�C���h�c
        ProductTypeEnum l_productTypeEnum = null;    //�����^�C�v
        
        Long l_lngUpKey = new Long(l_lngUploadKey);  //����.�f�[�^�L�[
              
        //�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v������
        try
        {
            l_strInstitutionCode = this.getInstitutionCode();
            l_strUploadFileId = this.getUploadFileId();
            l_productTypeEnum = this.getProductType();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");            //�،���ЃR�[�h
            l_sbWhere.append(" and upload_file_id = ? ");          //�A�b�v���[�h�t�@@�C���h�c
            l_sbWhere.append(" and product_type = ? ");            //�����^�C�v
            l_sbWhere.append(" and upload_key = ? ");
            l_sbWhere.append(" and nvl(note1,'NULL') != ? ");
            
            //�f�[�^�L�[
            Object[] l_objAdministratorUploadWhere = { 
                l_strInstitutionCode, //this.get�،���ЃR�[�h()
                l_strUploadFileId,    //this.get�A�b�v���[�h�t�@@�C���h�c()
                l_productTypeEnum,    //�����^�C�v = this.get�����^�C�v()
                l_lngUpKey,           //�f�[�^�L�[
                UPLOAD_TERMINATE      //"���~"  
                }; 

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    "upload_start_timestamp desc",
                    null,
                    l_objAdministratorUploadWhere);
        }
        catch (DataException de)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug(de.getMessage(), de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //���R�[�h���Ȃ��ꍇ��null��ԋp����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�擾�����s�I�u�W�F�N�g��ԋp����B
        AdministratorUploadRow l_uploadRow = 
            (AdministratorUploadRow)l_lisRecords.get(0); 
        
        log.exiting(STR_METHOD_NAME);
        return l_uploadRow;
    }
    
    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v.���̑���ԋp����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 42C4E176008F
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.OTHER;
    }
    
    /**
     * (update�A�b�v���[�h���~)<BR>
     * �Y���A�b�v���[�h�s�ɃA�b�v���[�h���~���X�V����B<BR> 
     * <BR>
     * this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ��� <BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B<BR> 
     * <BR>
     * �@@�A�b�v���[�h�I������ = System.currentTimeMillis()<BR> 
     * �@@�A�b�v���[�h���� = 0 <BR>
     * �@@���l�P = "���~" <BR>
     * <BR>
     * ���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B<BR>
     * @@throws WEB3BaseException
     */
    public void updateUploadEnd() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateUploadEnd()";
        log.entering(STR_METHOD_NAME);
        
        AdministratorUploadRow l_administratorUploadRow = null;
        try
        {
            //this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            l_administratorUploadRow = (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(
                	this.getAdministratorUploadId());
            
            if(l_administratorUploadRow == null)
            {
                log.exiting(STR_METHOD_NAME);
                return;
            }    
        }
        catch (DataException l_exp)
        {
            log.debug(l_exp.getMessage(), l_exp);
        	
        	log.exiting(STR_METHOD_NAME);
            return;
        }
            
        AdministratorUploadParams l_administratorUploadParams = 
            new AdministratorUploadParams(l_administratorUploadRow);

        //�A�b�v���[�h�I������ = System.currentTimeMillis()
        Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
        l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);

        ///�A�b�v���[�h���� = 0
        l_administratorUploadParams.setUploadCount(0);

        //���l�P = "���~"
        l_administratorUploadParams.setNote1(WEB3AdminAccInfoMailAddressUploadCsv.UPLOAD_TERMINATE);

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException l_exp)
        {
            log.error(l_exp.getMessage(), l_exp);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.getMessage(),
                l_exp);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
