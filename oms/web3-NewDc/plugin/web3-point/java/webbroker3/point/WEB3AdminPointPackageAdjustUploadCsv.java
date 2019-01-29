head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.00.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPackageAdjustUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �|�C���g�ꊇ�����A�b�v���[�hCSV(WEB3AdminPointPackageAdjustUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 �A�C��(���u) �V�K�쐬
*/
package webbroker3.point;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�|�C���g�ꊇ�����A�b�v���[�hCSV)<BR>
 * �|�C���g�ꊇ�����A�b�v���[�hCSV�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminPointPackageAdjustUploadCsv extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointPackageAdjustUploadCsv.class);
    
    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    public static  String BRANCH_CODE_LABEL = "���X�R�[�h";
    
    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";
    
    /**
     * (�����|�C���g���x��)<BR>
     * �����|�C���g���x��<BR>
     */
    public static  String ADJUST_POINT_LABEL = "�����|�C���g";
    
    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * �A�b�v���[�h�t�@@�C��ID<BR>
     */
    public String uploadFileId = "�|�C���g�ꊇ�����A�b�v���[�h";
    
    /**
     * (�|�C���g�ꊇ�����A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �P�j�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�jthis.create�J�����w�b�_()���R�[������B <BR>
     * @@param l_uploadId - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     * @@roseuid 4199E2C70213
     */
    public WEB3AdminPointPackageAdjustUploadCsv(long l_uploadId) 
    {
        final String STR_METHOD_NAME = " WEB3AdminPointPackageAdjustUploadCsv(long )";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B
        this.administratorUploadId = l_uploadId;
        
        //�Q�jthis.create�J�����w�b�_()���R�[������B 
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (�|�C���g�ꊇ�����A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * this.create�J�����w�b�_()���R�[������B <BR>
     * @@roseuid 4198917B028C
     */
    public WEB3AdminPointPackageAdjustUploadCsv() 
    {
        final String STR_METHOD_NAME = " WEB3AdminPointPackageAdjustUploadCsv()";
        log.entering(STR_METHOD_NAME);
        
        // this.create�J�����w�b�_()���R�[������B 
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * this.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * @@return String
     * @@roseuid 41995DD602FA
     */
    public String getUploadFileId() 
    {
        return this.uploadFileId;
    }
    
    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v��ԋp����B<BR>
     * <BR>
     * ProductTypeEnum.���̑���ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 41995D6E00F6
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.OTHER;
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̂Ƃ����CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂ�<BR>�C���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * ��index = 0 �i���X�R�[�h�j<BR>
     *    [CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F �|�C���g�ꊇ�����A�b�v���[�hCSV.���X�R�[�h���x��<BR>
     *    �J�����ԍ��F 0<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * ��index = 1 �i�ڋq�R�[�h�j<BR>
     *    [CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F �|�C���g�ꊇ�����A�b�v���[�hCSV.�ڋq�R�[�h���x��<BR>
     *    �J�����ԍ��F 1<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * ��index = 2 �i�����|�C���g�j<BR>
     *    [CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F �|�C���g�ꊇ�����A�b�v���[�hCSV.�����|�C���g���x��<BR>
     *    �J�����ԍ��F 2<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���l�ilong�j<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * @@roseuid 419AB06A0120
     */
    private void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        final int COLUMN_MAX = 3;
        
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        //index 0        
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminPointPackageAdjustUploadCsv.BRANCH_CODE_LABEL, 
            0, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 1        
        l_models[1] = new WEB3GentradeCsvColumnModel(
        WEB3AdminPointPackageAdjustUploadCsv.ACCOUNT_CODE_LABEL, 
            1, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 2        
        l_models[2] = new WEB3GentradeCsvColumnModel(
        WEB3AdminPointPackageAdjustUploadCsv.ADJUST_POINT_LABEL, 
            2, 
            WEB3GentradeCsvColumnModel.LONGTYPE,
            null);
            
        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F �s�ԍ�<BR>
     * �J�����F get�J�������f��(�|�C���g�ꊇ�����A�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@return String
     * @@roseuid 419AB7F903CF
     */
    public String getBranchCode(int l_intLineNumber) 
    {
        String l_strBranchCode = null;
        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(BRANCH_CODE_LABEL));
        if (l_obj != null)
        {
            l_strBranchCode = l_obj.toString();
        }
        return l_strBranchCode;
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F �s�ԍ�<BR>
     * �J�����F get�J�������f��(�|�C���g�ꊇ�����A�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@return String
     * @@roseuid 419AB8A802F4
     */
    public String getAccountCode(int l_intLineNumber) 
    {
        String l_strAccountCode = null;
        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(ACCOUNT_CODE_LABEL));
        if (l_obj != null)
        {
            l_strAccountCode = l_obj.toString();
        }  
        return l_strAccountCode;    
    }
    
    /**
     * (get�����|�C���g)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̒����|�C���g���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĒ����|�C���g���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F �s�ԍ�<BR>
     * �J�����F get�J�������f��(�|�C���g�ꊇ�����A�b�v���[�hCSV.�����|�C���g���x��)�̖߂�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@return long
     * @@roseuid 419AB8CC0229
     */
    public long getAdjustPoint(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAdjustPoint(int)";
        log.entering(STR_METHOD_NAME);
        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(ADJUST_POINT_LABEL));
        if (l_obj == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01719,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        long l_lngAdjustPoint = Long.parseLong(l_obj.toString());
        
        log.exiting(STR_METHOD_NAME);
        
        return l_lngAdjustPoint;
    }
    
    /**
     * (validate�d���ڋq)<BR>
     * �d���ڋq���ǉ�����Ă��Ȃ����`�F�b�N���s���B <BR>
     * <BR>
     * get�ڋq�R�[�h(�s�ԍ�)�ɂāA�w��s�ԍ��̌ڋq�R�[�h���擾����B <BR>
     * �擾�����ڋq�R�[�h�Ǝw��s�ԍ����O�̖��׍s�̌ڋq�R�[�h���r���A <BR>
     * �����l�����݂���ꍇ�́A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 419ACF5F03B0
     */
    public void validateDuplicateAccount(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateAccount(int)";
        log.entering(STR_METHOD_NAME);
        
        if (l_intLineNumber > 0)
        {
            String l_strAccountCode1 = this.getAccountCode(l_intLineNumber);
            for (int i = 0; i < l_intLineNumber; i++)
            {
                log.debug("loop couont:" + i);
                String l_strAccountCode2 = this.getAccountCode(i);
                if ((l_strAccountCode1 == null && l_strAccountCode2 == null) || 
                    (l_strAccountCode1 != null && l_strAccountCode1.equals(l_strAccountCode2)))
                {
                    String l_strMessage = "�d���ڋq! line Number1:" + (l_intLineNumber - 1) + "line Number2:" + l_intLineNumber;
                    log.debug(l_strMessage);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                } 
            }
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�����A�b�v���[�h)<BR>
     * ���v���Z�X�̃A�b�v���[�h�������N�����łȂ������`�F�b�N����B<BR>
     * <BR>
     * this.validate�����A�b�v���[�h�i�A�b�v���[�hID�j���R�[������B<BR>
     * <BR>
     * [validate�����A�b�v���[�h()�ɃZ�b�g�������]<BR>
     * �A�b�v���[�hID�F this.�A�b�v���[�hID<BR>
     * @@roseuid 419D4D0601F5
     */
    public void validateSameTimeUpload() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSameTimeUpload()";
        log.entering(STR_METHOD_NAME);
        
        this.validateSameTimeUpload(new Long(this.administratorUploadId));
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (setDataFrom�A�b�v���[�hTemp)<BR>
     * �A�b�v���[�h�e���|�����e�[�u���̃f�[�^���v���p�e�B�ɂ��Z�b�g����B<BR>
     * <BR>
     * this.setDataFrom�A�b�v���[�hTemp�i�A�b�v���[�hID�j���R�[������B<BR>
     * <BR>
     * setDataFrom�A�b�v���[�hTemp()�ɃZ�b�g�������]<BR>
     * �A�b�v���[�hID�F this.�A�b�v���[�hID<BR>
     * @@roseuid 419D4DA20050
     */
    public void setDataFromUploadTemp() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setDataFromUploadTemp()";
        log.entering(STR_METHOD_NAME);
        
        this.setDataFromUploadTemp(administratorUploadId);//WEB3SystemLayerException
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate���X�R�[�h)<BR>
     * ���X�R�[�h�̃`�F�b�N���s���B<BR>
     * <BR>
     *    ����.���X�R�[�h.length() != 3 or<BR>
     *    ����.���X�R�[�h.length() != ����<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01729<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * @@roseuid 41A68940023D
     */
    public void validateBranchCode(String l_strBranchCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBranchCode(String )";
        log.entering(STR_METHOD_NAME);
        
        if (l_strBranchCode == null || WEB3StringTypeUtility.getByteLength(l_strBranchCode.trim()) != 3)
        {
            String l_strMessage = "���X�R�[�h.length() != 3";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
            
        if (!WEB3StringTypeUtility.isDigit(l_strBranchCode))
        {
            String l_strMessage = "���X�R�[�h != ����";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (validate�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�̃`�F�b�N���s���B<BR>
     * <BR>
     *    ����.�ڋq�R�[�h.length() != 6 or<BR>
     *    ����.�ڋq�R�[�h.length() != ����<BR>
     * <BR>
     * �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     * @@roseuid 41A689E002CA
     */
    public void validateAccountCode(String l_strAccountCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateAccountCode(String )";
        log.entering(STR_METHOD_NAME);
        
        if (l_strAccountCode == null || WEB3StringTypeUtility.getByteLength(l_strAccountCode.trim()) != 6)
        {
            String l_strMessage = "�ڋq�R�[�h.length() != 6";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
            
        if (!WEB3StringTypeUtility.isDigit(l_strAccountCode))
        {
            String l_strMessage = "�ڋq�R�[�h != ����";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
}
@
