head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminFXTransferOrderUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�U�֒����A�b�v���[�hCSV(WEB3AdminFXTransferOrderUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/24 �A����(���u) �V�K�쐬
                 : 2006/03/02 �A���� (���u) �d�l�ύX�E���f��511
                 : 2006/03/07 �A���� (���u) �d�l�ύX�E���f��517
                 : 2006/04/07 �R��(SRA)    �d�l�ύX�E���f��525                                                
                 : 2006/04/20 ���_�O (���u) ��Q�Ǘ��[�EU02825                                               
Revesion History : 2008/09/05 ���u�� (���u) �d�l�ύX�E���f��965,966,967,968,969,977,978
*/
package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.data.CompFxConditionRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�U�֒����A�b�v���[�hCSV)<BR>
 * FX�U�֒����A�b�v���[�hCSV<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3AdminFXTransferOrderUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFXTransferOrderUploadCsv.class);
    
    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * �A�b�v���[�h�t�@@�C��ID <BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c��<BR>
     * �i�[���镶���� <BR>
     */
    public String UPLOAD_FILEID = "FX�U�֒����A�b�v���[�h";
    
    /**
     * (�Q���҃R�[�h���x��)<BR>
     * �Q���҃R�[�h���x��<BR>
     */
    public String PARTICIPANT_CODE_LABEL = "�Q���҃R�[�h";
    
    /**
     * (���p�҃R�[�h���x��)<BR>
     * ���p�҃R�[�h���x��<BR>
     */
    public String USER_CODE_LABEL = "���p�҃R�[�h";
    
    /**
     * (�ȖڃR�[�h���x��)<BR>
     * �ȖڃR�[�h���x�� <BR>
     */
    public String SUBJECT_CODE_LABEL = "�ȖڃR�[�h";
    
    /**
     * (�o���z���x��)<BR>
     * �o���z���x�� <BR>
     */
    public String CASH_OUT_AMOUNT_LABEL = "�o���z";
    
    /**
     * (�o�������x��)<BR>
     * �o�������x�� <BR>
     */
    public String PAYMENT_DATE_LABEL = "�o����";
    
    /**
     * (���o���ԍ����x��)<BR>
     * ���o���ԍ����x�� <BR>
     */
    public String CASH_IN_OUT_NUMBER_LABEL = "���o���ԍ�";
    
    /**
     * (FX�U�֒����A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B <BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_lngUploadId - (�A�b�v���[�hID)<BR>
     * @@roseuid 43C6159801DA
     */
    public WEB3AdminFXTransferOrderUploadCsv(long l_lngUploadId) 
    {
        super.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (FX�U�֒����A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B <BR>
     * @@roseuid 43C6156F039F
     */
    public WEB3AdminFXTransferOrderUploadCsv() 
    {
        this.createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()��<BR>
     * �ăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@FX�U�֒���CSV.�Q���҃R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@FX�U�֒���CSV.���p�҃R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@FX�U�֒���CSV.�ȖڃR�[�h���x��<BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@FX�U�֒���CSV.�o���z���x��<BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@FX�U�֒���CSV.�o�������x��<BR>
     * �@@�J�����ԍ��F 4 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * �|�@@index = 5 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@FX�U�֒���CSV.���o���ԍ����x��<BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * @@roseuid 43C6166D0192
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        //CSV�J�������f���̔z��𐶐���
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[6];
        
        //�|�@@index = 0 
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���] 
        // �@@���ڃ��x���F�@@FX�U�֒���CSV.�Q���҃R�[�h���x�� 
        // �@@�J�����ԍ��F 0 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ 
        // �@@���t�t�H�[�}�b�g�F�@@null 
        l_columnModel[0] = 
            new WEB3GentradeCsvColumnModel(
                this.PARTICIPANT_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 1
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���] 
        // �@@���ڃ��x���F�@@FX�U�֒���CSV.���p�҃R�[�h���x��
        // �@@�J�����ԍ��F 1 <BR>
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ 
        //�@@ ���t�t�H�[�}�b�g�F�@@null
        l_columnModel[1] = 
            new WEB3GentradeCsvColumnModel(
                this.USER_CODE_LABEL,
                1, 
                WEB3GentradeCsvColumnModel.STRINGTYPE, 
                null);

        // �|�@@index = 2 
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���] 
        // �@@���ڃ��x���F�@@FX�U�֒���CSV.�ȖڃR�[�h���x��
        // �@@�J�����ԍ��F 2 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ 
        // �@@���t�t�H�[�}�b�g�F�@@null 
        l_columnModel[2] = 
            new WEB3GentradeCsvColumnModel(
                this.SUBJECT_CODE_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // �|�@@index = 3 
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���] 
        // �@@���ڃ��x���F�@@FX�U�֒���CSV.�o���z���x��
        // �@@�J�����ԍ��F 3 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j
        // �@@���t�t�H�[�}�b�g�F�@@null 
        l_columnModel[3] = 
            new WEB3GentradeCsvColumnModel(
                this.CASH_OUT_AMOUNT_LABEL,
                3,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        // �|�@@index = 4 
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���] 
        // �@@���ڃ��x���F�@@FX�U�֒���CSV.�o�������x��
        //�@@ �J�����ԍ��F 4 
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t
        // �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyyMMdd")
        l_columnModel[4] = 
            new WEB3GentradeCsvColumnModel(
                this.PAYMENT_DATE_LABEL,
                4,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat("yyyyMMdd"));

        // �|�@@index = 5 
        // �@@[CSV�J�������f�� �R���X�g���N�^�̈���] 
        // �@@���ڃ��x���F�@@FX�U�֒���CSV.���o���ԍ����x��
        // �@@�J�����ԍ��F 5 <BR>
        // �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ 
        // �@@���t�t�H�[�}�b�g�F�@@null 
        l_columnModel[5] = 
            new WEB3GentradeCsvColumnModel(
                this.CASH_IN_OUT_NUMBER_LABEL,
                5,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        //set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B
        this.setColumnHeader(l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@���p�҃R�[�h�̃`�F�b�N<BR>
     * �@@this.get���p�҃R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get���p�҃R�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �@@�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02367<BR>
     * �@@�P�|�Q�j�@@��������8byte�łȂ��ꍇ�́A��O���X���[����B<BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02368<BR>
     * �Q�j�@@�ڋq�̑��݃`�F�b�N <BR>
     * �@@this.get�ڋq()���R�[������B<BR>
     * <BR>
     * �@@[get�ڋq()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * <BR>
     * �R�j�@@�ȖڃR�[�h�̃`�F�b�N <BR>
     * �@@this.get�ȖڃR�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get�ȖڃR�[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ� <BR>
     * <BR>
     * �@@�R�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02382<BR>
     * �@@�R�|�Q�j�@@��������1byte�łȂ��ꍇ�́A��O���X���[����B <BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02383<BR>
     * <BR>
     * �S�j�@@�o���z�̃`�F�b�N <BR>
     * �@@this.get�o���z()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get�o���z()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ� <BR>
     * <BR>
     * �@@�S�|�P�j�@@�o���z��Null�̏ꍇ�A��O���X���[����B        <BR>
     *                class: WEB3SystemLayerException<BR>
     *                tag  : SYSTEM_ERROR_80023
     * �@@�S�|�Q�j�@@������9�����傫���ꍇ�́A��O���X���[����B  <BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02413<BR>
     * <BR>
     * �T�j�@@���o���ԍ��̃`�F�b�N <BR>
     * �@@this.get���o���ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���o���ԍ�()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ� <BR>
     * <BR>
     * �@@�T�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B <BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02388<BR>
     * <BR>
     * �@@�T�|�Q�j�@@��������9byte���傫���ꍇ�́A��O���X���[����B <BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02389<BR>
     * <BR>
     * �U�j�@@�o�����̃`�F�b�N <BR>
     *                ���׍s�����񂩂�o�������擾���Avalidate�o����(�o���� : String)���R�[������B<BR>                
     *                class: WEB3SystemLayerException<BR>
     *                tag  : SYSTEM_ERROR_80023  <BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@param l_rowString     - (���׍s������)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43CC891E00B5
     */
    public void validateDetailsLine(int l_intLineNumber, String l_rowString) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���p�҃R�[�h�̃`�F�b�N
        //this.get���p�҃R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        String l_strUserCode = this.getUserCode(l_intLineNumber);
        
        //�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(l_strUserCode))
        {
            log.debug("���p�҃R�[�h�̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02367,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���p�҃R�[�h�̒l�����p�����ȊO�̒l�ł��B");
        }
        
        //�P�|�Q�j�@@��������8byte�łȂ��ꍇ�́A��O���X���[����B
        if (l_strUserCode.getBytes().length != 8)
        {
            log.debug("���p�҃R�[�h�̒l��8byte�������ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02368,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���p�҃R�[�h�̒l��8byte�������ł͂���܂���B");
        }
        
        //�Q�j�@@�ڋq�̑��݃`�F�b�N 
        //this.get�ڋq()���R�[������B
        this.getMainAccount(l_intLineNumber);
        
        //�R�j�@@�ȖڃR�[�h�̃`�F�b�N 
        //this.get�ȖڃR�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        String l_strSubjectCode = this.getSubjectCode(l_intLineNumber);
        
        //�R�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(l_strSubjectCode))
        {
            log.debug("�ȖڃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02382,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ȖڃR�[�h�̒l�����p�����ȊO�̒l�ł��B");
        }
            
        //�R�|�Q�j�@@��������1byte�łȂ��ꍇ�́A��O���X���[����B 
        if (l_strSubjectCode.getBytes().length != 1)
        {
            log.debug("�ȖڃR�[�h�̒l��1byte�������ł͂���܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02383,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ȖڃR�[�h�̒l��1byte�������ł͂���܂���B");
        }
        
        //�S�j�@@�o���z�̃`�F�b�N 
        //this.get�o���z()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B                
        //		�S�|�P�j�@@�o���z��Null�̏ꍇ�A��O���X���[����B
		String l_strCashOutAmt = null;
        try
        {
			l_strCashOutAmt = WEB3StringTypeUtility.formatNumber(this.getCashOutAmt(l_intLineNumber));
        }
		catch(Exception e)
        {
            log.debug("�o���z�̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02384,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�o���z�̒l�����p�����ȊO�̒l�ł��B");
	    }
        
        //     �S�|�Q�j�@@������9�����傫���ꍇ�́A��O���X���[����B
        if (l_strCashOutAmt.length() > 9)
        {
            log.debug("�o���z�̌�����9�����傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02413,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�o���z�̌�����9�����傫���ł��B");
        }
        
        //�T�j�@@���o���ԍ��̃`�F�b�N 
        //this.get���o���ԍ�()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        String l_strCashInOutNumber = this.getCashInOutNumber(l_intLineNumber);
        
        //�T�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B 
        if (!WEB3StringTypeUtility.isDigit(l_strCashInOutNumber))
        {
            log.debug("���o���ԍ��̒l�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02388,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���o���ԍ��̒l�����p�����ȊO�̒l�ł��B");
        }
            
        //�T�|�Q�j�@@��������9byte���傫���ꍇ�́A��O���X���[����B 
        if (l_strCashInOutNumber.getBytes().length > 9)
        {
            log.debug("���o���ԍ��̒l��9byte���傫���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02389,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���o���ԍ��̒l��9byte���傫���ł��B");
        }
        
		//�U�j�@@�o�����̃`�F�b�N <BR>
		// ���׍s�����񂩂�o�������擾���Avalidate�o����(�o���� : String)���R�[������B<BR>
		String[] token = l_rowString.split(regex);		
		String l_strPaymentDate = token[getColumnModel(PAYMENT_DATE_LABEL).getColumnNumber()];
		this.validatePaymentDate(l_strPaymentDate);
		        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�Q���҃R�[�h)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s�̎Q���҃R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĎQ���҃R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(FX�U�֒����A�b�v���[�hCSV.�Q���҃R�[�h���x��)<BR>
     * �̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43CC8D7F028A
     */
    public String getParticipantCode(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĎQ���҃R�[�h���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(this.PARTICIPANT_CODE_LABEL));
    }
    
    /**
     * (get���p�҃R�[�h)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s�̗��p�҃R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂė��p�҃R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(FX�U�֒����A�b�v���[�hCSV.���p�҃R�[�h���x��)<BR>
     * �̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43CC8E12026B
     */
    public String getUserCode(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂė��p�҃R�[�h���擾���ԋp����B
        return (String)this.getValue(l_intLineNumber, this.getColumnModel(this.USER_CODE_LABEL));
    }
    
    /**
     * (get�ȖڃR�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̉ȖڃR�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĉȖڃR�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(FX�U�֒����A�b�v���[�hCSV.�ȖڃR�[�h���x��) <BR>
     * �̖߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43CC8E5100F4
     */
    public String getSubjectCode(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂĉȖڃR�[�h���擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(this.SUBJECT_CODE_LABEL));
    }
    
    /**
     * (get�o���z)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏o���z���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂďo���z���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(FX�U�֒����A�b�v���[�hCSV.�o���z���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 43CC8E790067
     */
    public double getCashOutAmt(int l_intLineNumber) throws WEB3BaseException
    {
        return ((Double) this.getValue(l_intLineNumber, this.getColumnModel(this.CASH_OUT_AMOUNT_LABEL))).doubleValue();
    }
    
    /**
     * (get�o����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̏o�������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂďo�������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(FX�U�֒����A�b�v���[�hCSV.�o�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@throws WEB3BaseException
     * @@return Date
     * @@roseuid 43CC8EA80299
     */
    public Date getPaymentDate(int l_intLineNumber) throws WEB3BaseException
    {
        return (Date) this.getValue(l_intLineNumber, this.getColumnModel(this.PAYMENT_DATE_LABEL));
    }
    
    /**
     * (get���o���ԍ�)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓��o���ԍ����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂē��o���ԍ����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(FX�U�֒����A�b�v���[�hCSV.���o���ԍ����x��)��<BR>
     * �@@�@@�@@�@@�@@�߂�l�B <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43CC8ECC022C
     */
    public String getCashInOutNumber(int l_intLineNumber) 
    {
        //this.get���ڒl()�ɂē��o���ԍ����擾���ԋp����B
        return (String) this.getValue(l_intLineNumber, this.getColumnModel(this.CASH_IN_OUT_NUMBER_LABEL));
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h�i6���j���擾����B <BR>
     * <BR>
     * this.get���p�҃R�[�h()�ɂė��p�҃R�[�h���擾�����3���ڈȍ~��ԋp����B <BR>
     * <BR>
     * [get���p�҃R�[�h()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@����.�s�ԍ� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return String
     * @@roseuid 43CC8F0403B3
     */
    public String getMainAccountCode(int l_intLineNumber) 
    {
        return this.getUserCode(l_intLineNumber).substring(2);
    }
    
    /**
     * (get�ڋq)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s�̌ڋq�I�u�W�F�N�g���擾����B <BR>
     * <BR>
     * �g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾���ԋp����B <BR>
     * <BR>
     * [get�ڋq()�Ɏw�肷�����] <BR>
     * �،���ЃR�[�h�F�@@this.get�،���ЃR�[�h()<BR>
     * ���X�R�[�h�F�@@this.get���X�R�[�h(����.�s�ԍ�)<BR>
     * �����R�[�h�F�@@this.get�ڋq�R�[�h(����.�s�ԍ�)<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@return WEB3GentradeMainAccount
     * @@throws WEB3BaseException
     * @@roseuid 43CC8FC80057
     */
    public WEB3GentradeMainAccount getMainAccount(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMainAccount(int)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        //�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾���ԋp����B
        WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
            this.getInstitutionCode(),
            this.getBranchCode(l_intLineNumber),
            this.getMainAccountCode(l_intLineNumber));
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccount;
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B <BR>
     * <BR>
     * �P�j this.getFX���O�C��ID������()�ɂ�FX���O�C��ID���������擾����B<BR>
     * �@@[getFX���O�C��ID������()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ� <BR>
     * <BR>
     * �Q�j �ȉ��̏����̂��Ƃɉ�Е�FX�V�X�e�������e�[�u�����擾����B<BR>
     * <BR>
     * �@@�E�،���ЃR�[�h==this.get�،���ЃR�[�h()<BR>
     * �@@�EFX���O�C��ID������.substring(1)==�P�j�Ŏ擾����FX���O�C��ID������<BR>
     * <BR>
     * �R�j ��Е�FX�V�X�e������Param.���X�R�[�h��ԋp����B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43CC929D00C5
     */
    public String getBranchCode(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j this.getFX���O�C��ID������()�ɂ�FX���O�C��ID���������擾����B
        String l_strFXHeadOfLoginId = this.getFXHeadOfLoginId(l_intLineNumber);
        
        //�Q�j �ȉ��̏����̂��Ƃɉ�Е�FX�V�X�e�������e�[�u�����擾����B
        //�@@�E�،���ЃR�[�h==this.get�،���ЃR�[�h()
        //  �EFX���O�C��ID������.substring(1)==�P�j�Ŏ擾����FX���O�C��ID������
        String l_strWhere = " institution_code = ? and substr(fx_head_of_login_id, 2) = ? ";
        Object[] l_objValues = {this.getInstitutionCode(), l_strFXHeadOfLoginId};
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    CompFxConditionRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValues);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." +STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�A�N�Z�X�G���[", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if (l_lisRows == null || l_lisRows.size() == 0)
        {
            log.debug("���X�R�[�h�E�ڋq�R�[�h���s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02414,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���X�R�[�h�E�ڋq�R�[�h���s���ł��B" + " " +
                    this.getUserCode(l_intLineNumber) + " " + 
                    this.getCashInOutNumber(l_intLineNumber));
        }

        //�R�j ��Е�FX�V�X�e������Param.���X�R�[�h��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return ((CompFxConditionRow) l_lisRows.get(0)).getBranchCode();
    }
    
    /**
     * (validate�d������)<BR>
     * �d���������ǉ�����Ă��Ȃ����`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���ׂ̃`�F�b�N<BR>
     * <BR>
     * �@@�P�|�P�j�@@this.get���p�҃R�[�h(����.�s�ԍ�)�A<BR>
     * �@@�@@�@@�@@�@@�@@this.get���o���ԍ�(����.�s�ԍ�)�ɂāA<BR>
     * �@@�@@�@@�@@�@@�@@�w��s�ԍ��̗��p�҃R�[�h�A���o���ԍ����擾����B <BR>
     * <BR>
     * �@@�P�|�Q�j�@@�擾�������p�҃R�[�h�A���o���ԍ��ƁA<BR>
     * �@@�@@�@@�@@�@@�@@�w��s�ԍ����O�̖��׍s�̗��p�҃R�[�h�A���o���ԍ����r����B <BR>
     * �@@�@@�@@�@@�@@�@@�����l�̍s�i�w��s.���p�҃R�[�h == ������O�̍s.���p�҃R�[�h<BR>
     * �@@�@@�@@�@@�@@�@@&& �w��s.���o���ԍ� == ������O�̍s.���o���ԍ��j<BR>
     * �@@�@@�@@�@@�@@�@@�����݂���ꍇ�́A �������d�����ēo�^����Ă���Ɣ��肵�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02390<BR>
     * <BR>
     * �Q�j�@@GFT�U�֏󋵃e�[�u���̃`�F�b�N<BR>
     * <BR>
     * �@@�Q�|�P�j�@@GFT�U�֏󋵃e�[�u�����擾����B<BR>
     * <BR>
     * �@@FX�f�[�^����T�[�r�XImpl.getGFT�U�֏�()���R�[������B<BR>
     * <BR>
     * �@@[getGFT�U�֏�()�Ɏw�肷�����]<BR>
     * �@@�،���ЃR�[�h = this.get�،���ЃR�[�h()<BR>
     * �@@���X�R�[�h = this.get���X�R�[�h(����.�s�ԍ�)<BR>
     * �@@�ڋq�R�[�h = this.get�ڋq�R�[�h(����.�s�ԍ�)<BR>
     * �@@���o���ԍ� = this.get���o���ԍ�(����.�s�ԍ�)<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@���R�[�h�����݂���ꍇ�A�������d�����Ă���Ɣ��肵�A<BR>
     * �@@�@@�@@�@@�@@�@@��O���X���[����B <BR>
     *       �@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *       �@@�@@�@@�@@�@@tag  : BUSINESS_ERROR_02391<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * @@throws WEB3BaseException
     * @@roseuid 43CF1864025A
     */
    public void validateDuplicateOrder(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateOrder(int)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���ׂ̃`�F�b�N
        //�P�|�P�j�@@this.get���p�҃R�[�h(����.�s�ԍ�)�A
        //this.get���o���ԍ�(����.�s�ԍ�)�ɂāA
        //�w��s�ԍ��̗��p�҃R�[�h�A���o���ԍ����擾����B
        String l_strUserCode = this.getUserCode(l_intLineNumber);
        String l_strCashInOutNumber = this.getCashInOutNumber(l_intLineNumber);
        
        //�P�|�Q�j�@@�擾�������p�҃R�[�h�A���o���ԍ��ƁA
        //�@@�w��s�ԍ����O�̖��׍s�̗��p�҃R�[�h�A���o���ԍ����r����B
        //  �����l�̍s�i�w��s.���p�҃R�[�h == ������O�̍s.���p�҃R�[�h &&
        //  �w��s.���o���ԍ� == ������O�̍s.���o���ԍ��j
        //  �����݂���ꍇ�́A �������d�����ēo�^����Ă���Ɣ��肵�A
        //  ��O���X���[����B 
        if (l_intLineNumber > 0 
            && l_strUserCode.equals(this.getUserCode(l_intLineNumber - 1))
            && l_strCashInOutNumber.equals(this.getCashInOutNumber(l_intLineNumber - 1)))
        {
            log.debug("�擾�����f�[�^�����݂���ꍇ�A�������d�����ēo�^����Ă��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02390,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�擾�����f�[�^�����݂���ꍇ�A�������d�����ēo�^����Ă��܂��B");
        }
        
        //�Q�j�@@GFT�U�֏󋵃e�[�u���̃`�F�b�N
               //�Q�|�P�j�@@GFT�U�֏󋵃e�[�u�����擾����B
               // FX�f�[�^����T�[�r�XImpl.getGFT�U�֏�()���R�[������B
               // [getGFT�U�֏�()�Ɏw�肷�����]
               // �،���ЃR�[�h = this.get�،���ЃR�[�h()
               // ���X�R�[�h = this.get���X�R�[�h(����.�s�ԍ�)
               // �ڋq�R�[�h = this.get�ڋq�R�[�h(����.�s�ԍ�)
               // ���o���ԍ� = this.get���o���ԍ�(����.�s�ԍ�)
        WEB3FXDataControlService l_dataControlService = 
            (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
        GftTransferStatusParams l_params = 
            l_dataControlService.getGFTTransferStatus(
                this.getInstitutionCode(),
                this.getBranchCode(l_intLineNumber),
                this.getMainAccountCode(l_intLineNumber),
                this.getCashInOutNumber(l_intLineNumber));
        
        //�Q�|�Q�j�@@���R�[�h�����݂���ꍇ�A�������d�����Ă���Ɣ��肵�A
        //        ��O���X���[����B
        if (l_params != null)
        {
            log.debug("���I���R�[�h�����݂��܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02391,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���I���R�[�h�����݂��܂��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getFX���O�C��ID������)<BR>
     * ����.�s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B <BR>
     * <BR>
     * �P�j this.get���p�҃R�[�h()�ɂė��p�҃R�[�h���擾���A�O2�����擾����B <BR>
     * <BR>
     * �@@[get���p�҃R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@����.�s�ԍ�<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 43E2F24A03E7
     */
    public String getFXHeadOfLoginId(int l_intLineNumber) 
    {
        //�P�jthis.get���p�҃R�[�h()�ɂė��p�҃R�[�h���擾���A�O2�����擾����B
        return this.getUserCode(l_intLineNumber).substring(0, 2);
    }
    
    /**
     * (get�����^�C�v)<BR>
     * ProductTypeEnum.���� ��ԋp����B<BR>
     * @@return ProductTypeEnum
     * @@roseuid 43EFF41C0343
     */
    public ProductTypeEnum getProductType() 
    {
        //ProductTypeEnum.���� ��ԋp����B
        return ProductTypeEnum.CASH;
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * this.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c��<BR>
     * �i�[���镶����<BR>
     * @@return String
     * @@roseuid 43E0675E029D
     */
    public String getUploadFileId() 
    {
        return this.UPLOAD_FILEID;
    }
    
	/**
	 * (validate�o����)<BR>
	 * �o�����̃t�H�[�}�b�g�`�F�b�N���s���B<BR>
	 * <BR>
     * @@param l_strPaymentDate - (�o����)<BR>
     * @@throws WEB3BaseException
	 */
	private void validatePaymentDate(String l_strPaymentDate) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = " validatePaymentDate(String)";
		log.entering(STR_METHOD_NAME);

        //�P�j�o������Null�`�F�b�N
        //�Q�j�o�����̂W���`�F�b�N		
        //�R�j�o�����̔��p�����`�F�b�N
        if (!WEB3StringTypeUtility.isDigit(l_strPaymentDate)
            || l_strPaymentDate.length() != 8)
        {
			log.debug("�o�����̎w��Ɍ�肪����܂��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01835,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�o�����̎w��Ɍ�肪����܂��B");
        }		
		
		//�S�j�s���ȓ��t�̃`�F�b�N
		int l_intYear  = Integer.parseInt(l_strPaymentDate.substring(0, 4));
		int l_intMonth = Integer.parseInt(l_strPaymentDate.substring(4, 6)) - 1;
		int l_intDay   = Integer.parseInt(l_strPaymentDate.substring(6, 8));
        
        Calendar cal = new GregorianCalendar();
        cal.set( l_intYear , l_intMonth , l_intDay );
          
		if (cal.get(Calendar.YEAR) != l_intYear 
			|| cal.get(Calendar.MONTH) != l_intMonth
			|| cal.get(Calendar.DATE) != l_intDay) {
		    
			log.debug("�o�����̎w��Ɍ�肪����܂��B");
			log.exiting(STR_METHOD_NAME);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_01835,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"�o�����̎w��Ɍ�肪����܂��B");
		}

		log.exiting(STR_METHOD_NAME);
	}
}
@
