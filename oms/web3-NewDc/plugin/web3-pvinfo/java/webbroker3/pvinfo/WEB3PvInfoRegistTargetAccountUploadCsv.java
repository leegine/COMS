head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoRegistTargetAccountUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�^�Ώیڋq�A�b�v���[�hCSV(WEB3PvInfoRegistTargetAccountUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revision History : 2007/06/27  �Ӑ� (���u) �d�l�ύX���f��No.078,No.080
*/
package webbroker3.pvinfo;

import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o�^�Ώیڋq�A�b�v���[�hCSV)<BR>
 * �o�^�Ώیڋq�A�b�v���[�hCSV<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoRegistTargetAccountUploadCsv extends WEB3GentradeCsvUploadDataModel
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoRegistTargetAccountUploadCsv.class);

    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    public static final String BRANCH_CODE_LABEL = "���X�R�[�h";

    /**
     * (�ڋq�R�[�h���x��)<BR>
     * �ڋq�R�[�h���x��<BR>
     */
    public static final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";

    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     * �o�^�Ώیڋq�A�b�v���[�h<BR>
     */
    public String uploadFileId = "�o�^�Ώیڋq�A�b�v���[�h";

    /**
     * (�o�^�Ώیڋq�A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_lngUploadId - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoRegistTargetAccountUploadCsv
     * @@roseuid 4160A87100A7
     */
    public WEB3PvInfoRegistTargetAccountUploadCsv(long l_lngUploadId)
    {
        //�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B
        this.administratorUploadId = l_lngUploadId;
        //this.create�J�����w�b�_()���R�[������B 
        createColumnHeader();
    }

    /**
     * (�o�^�Ώیڋq�A�b�v���[�hCSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B <BR>
     * @@return webbroker3.pvinfo.WEB3PvInfoRegistTargetAccountUploadCsv
     * @@roseuid 4160A84A0069
     */
    public WEB3PvInfoRegistTargetAccountUploadCsv()
    {
        //this.create�J�����w�b�_()���R�[������B 
        createColumnHeader();
    }

    /**
     * (get�A�b�v���[�h�t�@@�C��ID)<BR>
     * �o�^�Ώیڋq�A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B <BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     * @@return String
     * @@roseuid 4160A80F03A5
     */
    public String getUploadFileId()
    {
        //�o�^�Ώیڋq�A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B
        return this.uploadFileId;
    }

    /**
     * (get�����^�C�v)<BR>
     * ProductTypeEnum.���̑���ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 4160B03401D0
     */
    public ProductTypeEnum getProductType()
    {
        //ProductTypeEnum.���̑���ԋp����B
        return ProductTypeEnum.OTHER;
    }

    /**
     * (create�J�����w�b�_)<BR>
     * �ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂ�<BR>
     * �C���X�^���X�ɃZ�b�g����B <BR>
     * 
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o�^�Ώیڋq�A�b�v���[�hCSV.���X�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1 <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�o�^�Ώیڋq�A�b�v���[�hCSV.�ڋq�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * @@roseuid 4160A8C50105
     */
    protected void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        //[�J�����w�b�_�z��]
        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[2];
        columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[1] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        setColumnHeader(columnHeader);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�o�^�Ώیڋq�A�b�v���[�hCSV.�ڋq�R�[�h���x��)��<BR>
     * �߂�l�B <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 4160A9EE0114
     */
    public String getAccountCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME);

        //this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(ACCOUNT_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
    }

    /**
     * (get���X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�o�^�Ώیڋq�A�b�v���[�hCSV.���X�R�[�h���x��)��<BR>
     * �߂�l�B <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 4160A9EE0134
     */
    public String getBranchCode(int l_intLineNo)
    {
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);

        //�s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(BRANCH_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);

        return l_strValue;
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
     * �@@�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>         
     * �@@�P�|�Q�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B<BR>
     *        
     * <BR>
     * �Q�j�@@�ڋq�R�[�h�̃`�F�b�N<BR>
     * �@@get�ڋq�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�ڋq�R�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>         
     * �@@�Q�|�Q�j�@@��������6byte�łȂ��ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01058<BR>
     * <BR>
     * ���X���[�����O�́A�S�āu�ڋq�t�@@�C�����e�G���[�v�̗�O�B<BR>
     * @@param l_lngLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 4160AA4103B4
     */
    public void validateDetailsLine(long l_lngLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailsLine(long)";
        log.entering(STR_METHOD_NAME);

        log.debug("�s�ԍ�: " + l_lngLineNo);
        
        //�P�j�@@���X�R�[�h�̃`�F�b�N                
        String l_strBranchCode = (String)this.getBranchCode((int) l_lngLineNo);
        boolean l_blnDigitBranchCode = WEB3StringTypeUtility.isDigit(l_strBranchCode);
        int l_intBranchCode = WEB3StringTypeUtility.getByteLength(l_strBranchCode);
        
        //�P�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        //�P�|�Q�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B        
        if(!l_blnDigitBranchCode || l_intBranchCode != 3)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01058,
                getClass().getName() + "." + STR_METHOD_NAME,
                WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());                
        }
        
        //�Q�j�@@�ڋq�R�[�h�̃`�F�b�N
        String l_strAccountCode = (String)this.getAccountCode((int) l_lngLineNo);
        boolean l_blnDigitAccountCode = WEB3StringTypeUtility.isDigit(l_strAccountCode);
        int l_intAccountCode = WEB3StringTypeUtility.getByteLength(l_strAccountCode);
        
        //�Q�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        //�Q�|�Q�j�@@��������6byte�łȂ��ꍇ�́A��O���X���[����B        
        if(!l_blnDigitAccountCode || l_intAccountCode != 6)
        {
            log.error(getClass().getName() + "." + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01058,
                getClass().getName() + "." + STR_METHOD_NAME,
                WEB3ErrorCatalog.BUSINESS_ERROR_01058.getErrorMessage());                
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setDataFrom�A�b�v���[�hTemp)<BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u����<BR>
     * �@@�@@�w��A�b�v���[�h�h�c�̃f�[�^���v���p�e�B�ɂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�e���|�����e�[�u���Ǎ� <BR>
     * �@@�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����ȉ��Ō������A <BR>
     * �s�ԍ����Ɂi�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[]���擾����B <BR>
     * <BR>
     * �@@[��������] <BR>
     * �@@�A�b�v���[�h�h�c = ����.�A�b�v���[�h�h�c <BR>
     * <BR>
     * �@@[�\�[�g����] <BR>
     * �@@CSV�s <BR>
     * <BR>
     * �@@���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params <BR>
     * �@@DDL�ɂĎ�����������s�I�u�W�F�N�g <BR>
     * <BR>
     * �Q�j�@@�L�[�w�b�_�s�̃Z�b�g <BR>
     * �@@this.set�L�[�w�b�_()�ɂăL�[�w�b�_���Z�b�g����B <BR>
     * �@@���P�j�œǂݍ��񂾍s�I�u�W�F�N�g�z���index = 0��<BR>
     * �@@�@@�@@�@@�v�f���L�[�w�b�_�s�Ɣ��肷�� <BR>
     * <BR>
     * �@@[set�L�[�w�b�_()�Ɏw�肷�����] <BR>
     * �@@�L�[�w�b�_�F<BR>
     * �@@�@@�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[0].split(��؂蕶��) <BR>
     * <BR>
     * �R�j�@@���׍s�̃Z�b�g <BR>
     * �@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[]��2�Ԗځiindex = 1�j<BR>
     * �@@�@@�@@�@@�ȍ~�̗v�f�ɂ��āAthis.add���׍s()�ɂĖ��׍s�ɒǉ�����B�@@ <BR>
     * <BR>
     * �@@[add���׍s()�Ɏw�肷�����] <BR>
     * �@@���׍s������F<BR>
     * �@@�@@�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[index].CSV�s <BR>
     * <BR>
     * �S�j�@@�A�b�v���[�h�h�c�Z�b�g <BR>
     * �@@this.�A�b�v���[�h�h�c�Ɉ���.�A�b�v���[�h�h�c���Z�b�g����B<BR>
     * <BR>
     * @@param l_lngUploadId - �A�b�v���[�h�h�c
     * @@throws WEB3SystemLayerException
     */
    public void setDataFromUploadTemp(long l_lngUploadId) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = " setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords;
        Long l_upID = new Long(l_lngUploadId);

        try
        {
            //�P�j�@@�e���|�����e�[�u���Ǎ�
            //�@@�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����ȉ��Ō������A
            //�s�ԍ����Ɂi�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[]���擾����B
            //�@@[��������]
            //�@@�A�b�v���[�h�h�c = ����.�A�b�v���[�h�h�c
            //�@@[�\�[�g����]
            //�@@CSV�s
            String l_strQueryString = " administrator_upload_id = ?";

            Object[] l_objWhereValue = {l_upID};

            String l_strSortCondition = " csv_line_value ";

            QueryProcessor l_qp = Processors.getDefaultProcessor();
            l_lisRecords = l_qp.doFindAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_strQueryString,
                l_strSortCondition,
                null,
                l_objWhereValue);
        }
        catch (DataException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords.isEmpty())
        {
            log.debug("�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����e�[�u�������F ���� < 1");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����e�[�u�������F ���� < 1");
        }

        AdministratorUploadTempParams l_administratorUploadTempParams;

        //�Q�j�@@�L�[�w�b�_�s�̃Z�b�g
        //�@@this.set�L�[�w�b�_()�ɂăL�[�w�b�_���Z�b�g����B
        //�@@���P�j�œǂݍ��񂾍s�I�u�W�F�N�g�z���index = 0�̗v�f���L�[�w�b�_�s�Ɣ��肷��
        //�@@[set�L�[�w�b�_()�Ɏw�肷�����]
        //�@@�L�[�w�b�_�F�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[0].split(��؂蕶��)
        l_administratorUploadTempParams =
            new AdministratorUploadTempParams((AdministratorUploadTempRow)l_lisRecords.get(0));

        String[] l_strCsvLineValues =
            l_administratorUploadTempParams.getCsvLineValue().split(WEB3GentradeCsvDataModel.regex);
        this.setKeyHeader(l_strCsvLineValues);

        //�R�j�@@���׍s�̃Z�b�g
        //�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[]��2�Ԗځiindex = 1)
        //    �ȍ~�̗v�f�ɂ��āAthis.add���׍s()�ɂĖ��׍s�ɒǉ�����B
        //�@@[add���׍s()�Ɏw�肷�����]
        //�@@���׍s������F�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[index].CSV�s
        int l_intSize = l_lisRecords.size();
        for (int i = 1; i < l_intSize; i++)
        {
            l_administratorUploadTempParams =
                (AdministratorUploadTempParams)l_lisRecords.get(i);
            this.addRow(l_administratorUploadTempParams.getCsvLineValue());
        }

        //�S�j�@@�A�b�v���[�h�h�c�Z�b�g
        //�@@this.�A�b�v���[�h�h�c�Ɉ���.�A�b�v���[�h�h�c���Z�b�g����B
        this.administratorUploadId = l_lngUploadId;

        log.exiting(STR_METHOD_NAME);
    }
}
@
