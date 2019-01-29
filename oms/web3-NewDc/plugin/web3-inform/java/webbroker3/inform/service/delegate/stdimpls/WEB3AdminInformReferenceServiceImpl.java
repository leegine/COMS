head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.46.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �A����񌟍��T�[�r�X�����N���X(WEB3AdminInformReferenceServiceImpl.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 ������(���u) �쐬
Revesion History    : 2007/05/14 �Ӑ�(���u) ���f���ENo.036-No.41,���f��No.44
Revesion History    : 2007/05/22 �Ӑ�(���u) ���f���ENo.047-No.53
*/

package webbroker3.inform.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeUtils;
import webbroker3.inform.WEB3AdminInformDownLoadCSV;
import webbroker3.inform.data.VariousInformDao;
import webbroker3.inform.data.VariousInformParams;
import webbroker3.inform.data.VariousInformRow;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.inform.message.WEB3AdminInformCommonRequest;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDetailResponse;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadResponse;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformInputResponse;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.inform.message.WEB3AdminInformListResponse;
import webbroker3.inform.message.WEB3InformDetailHeaderInfoUnit;
import webbroker3.inform.message.WEB3InformSortKey;
import webbroker3.inform.service.delegate.WEB3AdminInformReferenceService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�A����񌟍��T�[�r�XImpl)<BR>
 * �A����񌟍��T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3AdminInformReferenceServiceImpl implements WEB3AdminInformReferenceService 
{
    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformReferenceServiceImpl.class);
    
    /**
     * @@roseuid 41EE632C0157
     */
    public WEB3AdminInformReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �A����񌟍��T�[�r�X�������s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * <BR>
     *    get���͉��()<BR>
     *    get�ꗗ���()<BR>
     *    get�ڍ׉��()<BR>
     *    get�_�E�����[�h�t�@@�C��()<BR>
     * <BR>
     * ��L���\�b�h���R�[������B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return WEB3GenResponse
     * @@roseuid 41BD82B2014F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute()";
        log.entering(STR_METHOD_NAME);

        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminInformInputRequest)
        {
            // ���͉��
            l_response = getInformInputDisplay((WEB3AdminInformInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformListRequest)
        {
            // �ꗗ���
            l_response = getInformListDisplay((WEB3AdminInformListRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformDetailRequest)
        {
            // �ڍ׉��
            l_response = getInformDetailDisplay((WEB3AdminInformDetailRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminInformDownLoadRequest)
        {
            // �_�E�����[�h�t�@@�C��
            l_response = getDownLoadFile((WEB3AdminInformDownLoadRequest)l_request);
        }
        else
        {
            log.error("�p�����[�^�^�C�v�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A����񌟍��jget���͉�ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.inform.message.WEB3AdminInformInputResponse
     * @@roseuid 41BD83410297
     */
    protected WEB3AdminInformInputResponse getInformInputDisplay(WEB3AdminInformInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getInformInputDisplay()";
        log.entering(STR_METHOD_NAME);

        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N���s���B
        //[����]
        //  �@@�\�J�e�S���R�[�h�F "A0303"
        //  is�X�V�F false
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //���X�|���X�f�[�^�𐶐�����B 
        WEB3AdminInformInputResponse l_response = (WEB3AdminInformInputResponse)l_request.createResponse();

        Timestamp l_tsCurrentDate = GtlUtils.getSystemTimestamp();

        //���X�|���X.�O�c�Ɠ� = ���ݓ����̑O�c�Ɠ����t
        l_response.previousBizDate = WEB3GentradeUtils.getBizDate(l_tsCurrentDate, -1);

        //���X�|���X.���� = ���ݓ����̓��t
        l_response.previousDate = WEB3DateUtility.toDay(l_tsCurrentDate);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �ꗗ��ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A����񌟍��jget�ꗗ��ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@return webbroker3.inform.message.WEB3AdminInformListResponse
     * @@roseuid 41BD839100F2
     */
    protected WEB3AdminInformListResponse getInformListDisplay(WEB3AdminInformListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformListDisplay()";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̃`�F�b�N���s���B 
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N���s���B
        //[����]
        //  �@@�\�J�e�S���R�[�h�F "A0303"
        //  is�X�V�F false 
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //���X�������`�F�b�N����B
        l_administrator.validateBranchPermission(l_request.branchCode);

        //create�擾����������(WEB3AdminInformCommonRequest)
        //  (�A����񌟍��T�[�r�XImpl::create�擾����������)
        String l_strQueryString = this.createGetCondString(l_request);

        //create�擾�����f�[�^�R���e�i(String, WEB3AdminInformCommonRequest)
        //  (�A����񌟍��T�[�r�XImpl::create�擾�����f�[�^�R���e�i)
        Object[] l_queryContainer = this.createGetCondDataContainer(
            l_administrator.getInstitutionCode(),
            l_request);

        //create�\�[�g����������(�A����񌟍��\�[�g�L�[[])
        //  (�A����񌟍��T�[�r�XImpl::create�\�[�g����������)
        String l_strSortCond = this.createSortCondString(l_request.sortKeys);

        //�y�[�W���\���s��
        int l_intPageSize = Integer.parseInt(l_request.pageSize);     
    
        //�v���y�[�W�ԍ�
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);  

        List l_lisRecords = null;
        try
        {
            //getDefaultProcessor( )(Processors::getDefaultProcessor)
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
    
            //doFindAllQuery(RowType, String, String, String, Object[], int, int)
            //  (QueryProcessorStdImpl::doFindAllQuery)
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
        }        

        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_lisRecords,
            l_intPageIndex, 
            l_intPageSize);

        List l_lisSelectVariousInformRows = l_pageIndexInfo.getListReturned();
        int l_intSize = l_lisSelectVariousInformRows.size();
        log.debug("l_intSize = " + l_intSize);
        
        List l_lisInfoUnit = new ArrayList();
        for (int i = 0; i < l_intSize; i++)
        {
            //�e��A���ڍ׏��C���X�^���X�𐶐�����B 
            WEB3InformDetailHeaderInfoUnit l_infoUnit = new WEB3InformDetailHeaderInfoUnit((VariousInformParams)l_lisSelectVariousInformRows.get(i));
            
            //���X�g�Ɋe��A���ڍ׏��I�u�W�F�N�g��ǉ�����B
            l_lisInfoUnit.add(i, l_infoUnit);
        }

        //toArray()
        WEB3InformDetailHeaderInfoUnit[] l_informInfoUnit = new WEB3InformDetailHeaderInfoUnit[l_intSize];
        l_lisInfoUnit.toArray(l_informInfoUnit);

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformListResponse l_response = (WEB3AdminInformListResponse)l_request.createResponse();

        //���X�|���X.�A����� = �e��A���ڍ׏��I�u�W�F�N�g�̔z��
        l_response.informInfoDetailUnit = l_informInfoUnit;

        //���X�|���X.�\���y�[�W�ԍ� = pageNumber()�̖߂�l
        l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();
        log.debug("�\���y�[�W�ԍ�:" + l_pageIndexInfo.getPageIndex());

        //���X�|���X.���y�[�W�� = totalPages()�̖߂�l
        l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
        log.debug("���y�[�W��:" + l_pageIndexInfo.getTotalPages());

        //���X�|���X.�����R�[�h�� = totalSize()�̖߂�l
        l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
        log.debug("�����R�[�h��:" + l_pageIndexInfo.getTotalRecords());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�ڍ׉��)<BR>
     * �ڍ׉�ʂ̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A����񌟍��jget�ڍ׉�ʁv �Q�ƁB
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.inform.message.WEB3AdminInformDetailResponse
     * @@roseuid 41BD83AF00E2
     */
    protected WEB3AdminInformDetailResponse getInformDetailDisplay(WEB3AdminInformDetailRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformDetailDisplay()";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̃`�F�b�N���s���B 
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N���s���B
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //���X�������`�F�b�N����B
        l_administrator.validateBranchPermission(l_request.branchCode);

        try
        {
            //(*1) DAO�ɂāA�e��A���e�[�u�����烌�R�[�h���擾����B
            VariousInformRow l_informRow = VariousInformDao.findRowByInstitutionCodeInformDivRequestNumberBranchCode(
                l_administrator.getInstitutionCode(),
                l_request.informType,
                l_request.requestNumber,
                l_request.branchCode);
                
            VariousInformParams l_informParams = null;
            if (l_informRow != null)
            {
                l_informParams = new VariousInformParams(l_informRow);            
            }
            else
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME);                        
            }

            //�e��A���ڍ׏��C���X�^���X�𐶐�����B
            WEB3InformDetailHeaderInfoUnit l_infoUnit = new WEB3InformDetailHeaderInfoUnit(l_informParams);

            //���X�|���X�f�[�^�𐶐�����B
            WEB3AdminInformDetailResponse l_response = (WEB3AdminInformDetailResponse)l_request.createResponse();

            //���X�|���X.�A����� = �e��A���ڍ׏��I�u�W�F�N�g
            l_response.informInfoDetailUnit = l_infoUnit;

            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (get�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C���̎擾���s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A����񌟍��jget�_�E�����[�h�t�@@�C���v �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^
     * @@return webbroker3.inform.message.WEB3AdminInformDownLoadResponse
     * @@roseuid 41BD83B10259
     */
    protected WEB3AdminInformDownLoadResponse getDownLoadFile(WEB3AdminInformDownLoadRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getInformDetailDisplay()";
        log.entering(STR_METHOD_NAME);

        //���N�G�X�g�f�[�^�̃`�F�b�N���s���B 
        l_request.validate();
        
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_administrator = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N���s���B
        l_administrator.validateAuthority(WEB3TransactionCategoryDef.INFORM, false);

        //���X�������`�F�b�N����B
        l_administrator.validateBranchPermission(l_request.branchCode);

        List l_lisCsvFileLines = new ArrayList();

        int l_intBranchCodeLth = 0;
        if (l_request.branchCode != null)
        {
            l_intBranchCodeLth = l_request.branchCode.length;
        }
        log.debug("���X�R�[�h�̒��x�F" + l_intBranchCodeLth);

        //�}�[�W�����z��̗v�f��
        int l_intMergyCnt = 0;

        //create�擾����������(WEB3AdminInformCommonRequest)
        //  (�A����񌟍��T�[�r�XImpl::create�擾����������)
        String l_strQueryString = this.createGetCondString(l_request);

        //create�擾�����f�[�^�R���e�i(String, WEB3AdminInformCommonRequest)
        //  (�A����񌟍��T�[�r�XImpl::create�擾�����f�[�^�R���e�i)
        Object[] l_queryContainer = this.createGetCondDataContainer(
            l_administrator.getInstitutionCode(),
            l_request);

        //edit�\�[�g����(�A����񌟍��\�[�g�L�[[])(�A����񌟍��T�[�r�XImpl::edit�\�[�g����)
        WEB3InformSortKey[] l_informSortKeys = this.editSortCond(l_request.sortKeys);

        //create�\�[�g����������(�A����񌟍��\�[�g�L�[[])
        //  (�A����񌟍��T�[�r�XImpl::create�\�[�g����������)
        String l_strSortCond = this.createSortCondString(l_informSortKeys);

        //�A�����_�E�����[�hCSV(�،���ЃR�[�h, ���X�R�[�h, �A�����, is�J�����w�b�_�s�o��)
        //�،���ЃR�[�h�F �Ǘ���.get�،���ЃR�[�h()�̖߂�l
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h�̗v�f
        //�A����ʁF ���N�G�X�g�f�[�^.�A�����
        //is�J�����w�b�_�s�o�́F true
        WEB3AdminInformDownLoadCSV l_downLoadCSV = null;
        l_downLoadCSV = new WEB3AdminInformDownLoadCSV(
            l_administrator.getInstitutionCode(),
            l_request.branchCode[0],
            l_request.informType,
            true);

        List l_lisRecords = null;
        try
        {
            //getDefaultProcessor( )(Processors::getDefaultProcessor)
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //doFindAllQuery(RowType, String, String, String, Object[], int, int)(
            //  (QueryProcessorStdImpl::doFindAllQuery)
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                VariousInformRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.debug("�e�[�u���ɊY������f�[�^������܂���B");
        }
    
        int l_intSize = 0;
        if (l_lisRecords != null)
        {
            l_intSize = l_lisRecords.size();
        }

        log.debug("�e�������A�����������R�[�h���F" + l_intSize);

        l_intMergyCnt = l_intMergyCnt + l_intSize;

        //(*2)�擾�����e��A���e�[�u���̃��R�[�h����Loop����
        for (int j = 0; j < l_intSize; j++)
        {
            //add���׍s()
            l_downLoadCSV.addRow();
            
            log.debug("�e�������A���ڒl���Z�b�g�B�� " + j + " ���R�[�h!");
            //set���ڒl(int, �e��A��Params)(�A�����_�E�����[�hCSV::set���ڒl)
            l_downLoadCSV.setItemValue(j, (VariousInformParams)l_lisRecords.get(j));
        }

        log.debug("�e�������ACSV�t�@@�C���s�𐶐�!");
        //getCSV�t�@@�C���s()(�A�����_�E�����[�hCSV::getCSV�t�@@�C���s)
        String[] l_strCsvFileLinesDetail = l_downLoadCSV.getCsvFileLines();
        if (l_strCsvFileLinesDetail != null &&l_strCsvFileLinesDetail.length > 0)
        {
            log.debug("CSV�t�@@�C���s:" + l_strCsvFileLinesDetail.length);
            for (int k = 0; k < l_strCsvFileLinesDetail.length; k++)
            {
            	if (! "".equals(l_strCsvFileLinesDetail[k].trim()))
                	l_lisCsvFileLines.add(l_strCsvFileLinesDetail[k]);
            }
        }

        //getMAX��������( )(�A�����_�E�����[�hCSV::getMAX��������)
        int l_intMaxDealNumber = l_downLoadCSV.getMaxDealNumber();

        //(*3)
        //�P�j���X�R�[�h���ɐ������ꂽCSV�t�@@�C���s��1�̔z��Ƀ}�[�W����B
        String[] l_strCsvFileLines = new String[l_lisCsvFileLines.size()];
        l_lisCsvFileLines.toArray(l_strCsvFileLines);

        //�Q�j�}�[�W�����z��̗v�f�� == 0�A��O���X���[����B
        if (l_intMergyCnt == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01870,
                this.getClass().getName() + STR_METHOD_NAME);                        
        }

        //�}�[�W�����z��̗v�f�� > getMAX��������()�̖߂�l �̏ꍇ�A��O���X���[����B
        if (l_intMergyCnt > l_intMaxDealNumber)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01871,
                this.getClass().getName() + STR_METHOD_NAME);                        
        }

        //���X�|���X�f�[�^�𐶐�����B
        WEB3AdminInformDownLoadResponse l_response = (WEB3AdminInformDownLoadResponse)l_request.createResponse();

        //���X�|���X.�_�E�����[�h�t�@@�C�� = (*3)�Ń}�[�W�����z��
        l_response.downloadFile = l_strCsvFileLines;

        //���X�|���X.���ݓ��� = ���ݎ���
        l_response.currentDate = GtlUtils.getSystemTimestamp();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�擾����������)<BR>
     * �擾�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�A�����<BR>
     * <BR>
     *    "inform_div=? and institution_code=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j���X�R�[�h<BR>
     * <BR>
     * �R�|�P�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() == 1 �̏ꍇ<BR>
     * <BR>
     *    " and branch_code=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() > 1 �̏ꍇ<BR>
     * <BR>
     *    " and (branch_code=? or branch_code=? or ... or branch_code=?)" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    ��"branch_code=?"�̐��́A����.���N�G�X�g�f�[�^.���X�R�[�h�̗v�f���Ɠ����ɂȂ�B<BR>
     * <BR>
     * �S�j���ʃR�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.���ʃR�[�h != null �̏ꍇ<BR>
     * <BR>
     *    " and request_number like '?%'" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �T�j�ڋq�R�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�ڋq�R�[�h != null �̏ꍇ<BR>
     * <BR>
     *    " and account_code like '?%'" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �U�j�ڋq��<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�ڋq�� != null �̏ꍇ<BR>
     * <BR>
     *    " and account_name like '%?%'" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �V�j��t����<BR>
     * <BR>
     * �V�|�P�j����.���N�G�X�g�f�[�^.��t�����i���j != null �̏ꍇ<BR>
     * <BR>
     *    " and created_timestamp>=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �V�|�Q�j����.���N�G�X�g�f�[�^.��t�����i���j != null �̏ꍇ<BR>
     * <BR>
     *    " and created_timestamp<?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �W�j�����R�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�����R�[�h != null �̏ꍇ<BR>
     * <BR>
     *    " and fund_code =?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �X�j���҃R�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.���҃R�[�h != null �̏ꍇ<BR>
     * <BR>
     *    " and sonar_trader_code =?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �P�O�j�`�[�쐬��<BR>
     * <BR>
     * �P�O�|�P�j����.���N�G�X�g�f�[�^.�`�[�쐬�� != null �̏ꍇ<BR>
     * <BR>
     * �P�O�[�P�[�P�j����.���N�G�X�g�f�[�^.�`�[�쐬��.length == 1 �̏ꍇ<BR>
     * <BR>
     *    " and status=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �P�O�|�P�|�Q�j����.���N�G�X�g�f�[�^.�`�[�쐬��.length > 1 �̏ꍇ<BR>
     * <BR>
     *    " and (status =? or status =? or ... or status =?)" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *   ��"status =?"�̐��́A����.���N�G�X�g�f�[�^.�`�[�쐬�󋵂̗v�f���Ɠ����ɂȂ�B<BR>
     * <BR>
     * �P�P�j�������ꂽ�������ԋp����B<BR>
     * @@param l_request - (�A����񌟍����ʃ��N�G�X�g)<BR>
     * @@return String
     * @@roseuid 41BE56DB0001
     */
    protected String createGetCondString(WEB3AdminInformCommonRequest l_request)
    {
        final String STR_METHOD_NAME = "createGetCondString()";
        log.entering(STR_METHOD_NAME);

        //�P�j��̕�����𐶐�����B<BR>
        StringBuffer l_sbQueryString = new StringBuffer();

        //�Q�j�A�����<BR>
        //  "inform_div=? and institution_code = ?" ���P�j�̕�����ɒǉ�����B<BR>
        l_sbQueryString.append("inform_div = ? and institution_code = ?");

        //�R�j���X�R�[�h<BR>
        // �R�|�P�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() == 1 �̏ꍇ<BR>
        //    " and branch_code=?" ���P�j�̕�����ɒǉ�����B<BR>
        if (l_request.branchCode.length == 1)
        {
            l_sbQueryString.append(" and branch_code = ?");
        }

        // �R�|�Q�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() > 1 �̏ꍇ<BR>
        //    " and (branch_code=? or branch_code=? or ... or branch_code=?)" ���P�j�̕�����ɒǉ�����B<BR>
        //    ��"branch_code=?"�̐��́A����.��������.���X�R�[�h�̗v�f���Ɠ����ɂȂ�B<BR>
        if (l_request.branchCode.length > 1)
        {
            l_sbQueryString.append(" and (branch_code = ?");

            for (int i = 1; i < l_request.branchCode.length; i++)
            {
                l_sbQueryString.append(" or branch_code = ?");
            }

            l_sbQueryString.append(")");
        }

        //�S�j���ʃR�[�h<BR>
        //   ����.���N�G�X�g�f�[�^.���ʃR�[�h != null �̏ꍇ<BR>
        //   " and request_number like '?%'" ���P�j�̕�����ɒǉ�����B<BR>
        if (l_request.requestNumber != null)
        {
            l_sbQueryString.append(" and request_number like ? || '%'");
        }

        //�T�j�ڋq�R�[�h<BR>
        //   ����.���N�G�X�g�f�[�^.�ڋq�R�[�h != null �̏ꍇ<BR>
        //   " and account_code like '?%'" ���P�j�̕�����ɒǉ�����B<BR>
        if (l_request.accountCode != null)
        {
            l_sbQueryString.append(" and account_code like ? || '%'");
        }

        //�U�j�ڋq��<BR>
        //   ����.���N�G�X�g�f�[�^.�ڋq�� != null �̏ꍇ<BR>
        //   " and account_name like '%?%'" ���P�j�̕�����ɒǉ�����B<BR>
        if (l_request.accountName != null)
        {
            l_sbQueryString.append(" and account_name like '%' || ? || '%'");
        }

        //�V�j��t����<BR>
        // �V�|�P�j����.���N�G�X�g�f�[�^.��t�����i���j != null �̏ꍇ<BR>
        //   " and created_timestamp>=?" ���P�j�̕�����ɒǉ�����B<BR>
        if (l_request.receptionDateFrom != null)
        {
            l_sbQueryString.append(" and created_timestamp >= ?");
        }

        // �V�|�Q�j����.���N�G�X�g�f�[�^.��t�����i���j != null �̏ꍇ<BR>
        //   " and created_timestamp<?" ���P�j�̕�����ɒǉ�����B<BR>
        if (l_request.receptionDateTo != null)
        {
            l_sbQueryString.append(" and created_timestamp < ?");
        }

        //�W�j�����R�[�h
        //����.���N�G�X�g�f�[�^.�����R�[�h != null �̏ꍇ
        //" and fund_code =?" ���P�j�̕�����ɒǉ�����B
        if (l_request.productCode != null)
        {
            l_sbQueryString.append(" and fund_code = ?");
        }

        //�X�j���҃R�[�h
        //����.���N�G�X�g�f�[�^.���҃R�[�h != null �̏ꍇ
        //" and sonar_trader_code =?" ���P�j�̕�����ɒǉ�����B
        if (l_request.traderCode != null)
        {
            l_sbQueryString.append(" and sonar_trader_code = ?");
        }

        //�P�O�j�`�[�쐬��
        //�P�O�|�P�j����.���N�G�X�g�f�[�^.�`�[�쐬�� != null �̏ꍇ
        //�P�O�[�P�[�P�j����.���N�G�X�g�f�[�^.�`�[�쐬��.length == 1 �̏ꍇ
        //" and status=?" ���P�j�̕�����ɒǉ�����B
        //�P�O�|�P�|�Q�j����.���N�G�X�g�f�[�^.�`�[�쐬��.length > 1 �̏ꍇ
        //" and (status =? or status =? or ... or status =?)" ���P�j�̕�����ɒǉ�����B
        //��"status =?"�̐��́A����.���N�G�X�g�f�[�^.�`�[�쐬�󋵂̗v�f���Ɠ����ɂȂ�B
        if (l_request.voucherInfoList != null)
        {
            if (l_request.voucherInfoList.length == 1)
            {
                l_sbQueryString.append(" and status = ?");
            }
            else if (l_request.voucherInfoList.length > 1)
            {
                l_sbQueryString.append(" and (status = ?");

                for (int i = 1; i < l_request.voucherInfoList.length; i++)
                {
                    l_sbQueryString.append(" or status = ?");
                }

                l_sbQueryString.append(")");
            }
        }

        //�P�P�j�������ꂽ�������ԋp����B<BR>
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }

    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�A�����<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�A����� ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j�،���ЃR�[�h<BR>
     *    ����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j���X�R�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j���ʃR�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.���ʃR�[�h != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.���ʃR�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j�ڋq�R�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�ڋq�R�[�h != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�ڋq�R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �V�j�ڋq��<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�ڋq�� != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�ڋq�� ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �W�j��t����<BR>
     * <BR>
     * �W�|�P�j<BR>
     *    ����.���N�G�X�g�f�[�^.��t�����i���j != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.��t�����i���j ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �W�|�Q�j<BR>
     *    ����.���N�G�X�g�f�[�^.��t�����i���j != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.��t�����i���j ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �X�j�����R�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�����R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �P�O�j���҃R�[�h<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.���҃R�[�h != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.���҃R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �P�P�j�`�[�쐬��<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�`�[�쐬�� != null�̏ꍇ<BR>
     * <BR>
     *    ����.���N�G�X�g�f�[�^.�`�[�쐬�󋵂̊e�v�f���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �P�Q�j�������ꂽList����z����擾���A�ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_request - (�A����񌟍����ʃ��N�G�X�g)<BR>
     * @@return Object[]
     * @@roseuid 41BE56EC00CD
     */
    protected Object[] createGetCondDataContainer(
        String l_strInstitutionCode,
        WEB3AdminInformCommonRequest l_request)
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer()";
        log.entering(STR_METHOD_NAME);

        //�P�j���ArrayList�𐶐�����B<BR>
        List l_lstQueryContainer = new ArrayList();

        //�Q�j�A�����<BR>
        //   ����.�A����� ���P�j��List�ɒǉ�����B<BR>
        l_lstQueryContainer.add(l_request.informType);

        //�R�j�،���ЃR�[�h<BR>
        //   ����.�،���ЃR�[�h ���P�j��List�ɒǉ�����B<BR>
        l_lstQueryContainer.add(l_strInstitutionCode);

        //�S�j���X�R�[�h<BR>
        //   ����.���X�R�[�h�̊e�v�f ���P�j��List�ɒǉ�����B<BR>
        if (l_request.branchCode.length > 0)
        {
            for (int i = 0; i < l_request.branchCode.length; i++)
            {
                l_lstQueryContainer.add(l_request.branchCode[i]);
            }
        }

        //�T�j���ʃR�[�h<BR>
        //   ����.���ʃR�[�h != null�̏ꍇ<BR>
        //   ����.���ʃR�[�h ���P�j��List�ɒǉ�����B<BR>
        if (l_request.requestNumber != null)
        {
            l_lstQueryContainer.add(l_request.requestNumber);
        }

        //�U�j�ڋq�R�[�h<BR>
        //   ����.�ڋq�R�[�h != null�̏ꍇ<BR>
        //   ����.�ڋq�R�[�h ���P�j��List�ɒǉ�����B<BR>
        if (l_request.accountCode != null)
        {
            l_lstQueryContainer.add(l_request.accountCode);
        }

        //�V�j�ڋq��<BR>
        //   ����.�ڋq�� != null�̏ꍇ<BR>
        //   ����.�ڋq�� ���P�j��List�ɒǉ�����B<BR>
        if (l_request.accountName != null)
        {
            l_lstQueryContainer.add(l_request.accountName);
        }

        //�W�j��t����<BR>
        // �W�|�P�j<BR>
        //    ����.��t�����i���j != null�̏ꍇ<BR>
        //    ����.��t�����i���j ���P�j��List�ɒǉ�����B<BR>
        if (l_request.receptionDateFrom != null)
        {
            l_lstQueryContainer.add(l_request.receptionDateFrom);
        }

        //�W�|�Q�j<BR>
        //   ����.��t�����i���j != null�̏ꍇ<BR>
        //   ����.��t�����i���j ���P�j��List�ɒǉ�����B<BR>
        if (l_request.receptionDateTo != null)
        {
            l_lstQueryContainer.add(l_request.receptionDateTo);
        }

        //�X�j�����R�[�h
        //����.���N�G�X�g�f�[�^.�����R�[�h != null�̏ꍇ
        //����.���N�G�X�g�f�[�^.�����R�[�h ���P�j��List�ɒǉ�����B
        if (l_request.productCode != null)
        {
            l_lstQueryContainer.add(l_request.productCode);
        }

        //�P�O�j���҃R�[�h
        //����.���N�G�X�g�f�[�^.���҃R�[�h != null�̏ꍇ
        //����.���N�G�X�g�f�[�^.���҃R�[�h ���P�j��List�ɒǉ�����B
        if (l_request.traderCode != null)
        {
            l_lstQueryContainer.add(l_request.traderCode);
        }

        //�P�P�j�`�[�쐬��
        //����.���N�G�X�g�f�[�^.�`�[�쐬�� != null�̏ꍇ
        //����.���N�G�X�g�f�[�^.�`�[�쐬�󋵂̊e�v�f���P�j��List�ɒǉ�����B
        if (l_request.voucherInfoList != null)
        {
            for (int i = 0; i < l_request.voucherInfoList.length; i++)
            {
                l_lstQueryContainer.add(l_request.voucherInfoList[i]);
            }
        }

        //�P�Q�j�������ꂽList����z����擾���A�ԋp����B<BR>
        Object[] l_queryContainer = new Object[l_lstQueryContainer.size()];
        l_lstQueryContainer.toArray(l_queryContainer);

        log.exiting(STR_METHOD_NAME);
        return l_queryContainer;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �擾�f�[�^�̃\�[�g�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�\�[�g�L�[�z��̊e�v�f���Ɉȉ��̏������s���B�iLoop�����j<BR>
     * <BR>
     * �Q�|�P�j�L�[���� == �h���ʃR�[�h�h �̏ꍇ<BR>
     * <BR>
     *    �E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     *        "request_number"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    �E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     *        "request_number desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�Q�j�L�[���� == �h���X�R�[�h�h �̏ꍇ<BR>
     * <BR>
     *    �E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     *        "branch_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    �E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     *        "branch_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�R�j�L�[���� == �h�ڋq�R�[�h�h �̏ꍇ<BR>
     * <BR>
     *    �E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     *        "account_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    �E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     *        "account_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�S�j�L�[���� == �h��t�����h �̏ꍇ<BR>
     * <BR>
     *    �E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     *        "created_timestamp"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    �E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     *        "created_timestamp desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�T�j�Y���̗v�f���z����̍Ō�̗v�f�ł͂Ȃ��ꍇ<BR>
     * <BR>
     *    ", "���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j�������ꂽ�������ԋp����B
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[
     * @@return String
     * @@roseuid 41BE56F7032E
     */
    protected String createSortCondString(WEB3InformSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = "createSortCondString()";
        log.entering(STR_METHOD_NAME);

        //�P�j��̕�����𐶐�����B<BR>
        StringBuffer l_sbSortCond = new StringBuffer();
        
        //�Q�j�\�[�g�L�[�z��̊e�v�f���Ɉȉ��̏������s���B�iLoop�����j<BR>
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            String l_strSubCond = null;
            if (WEB3InformKeyItemDef.REQUEST_NUMBER.equals(l_sortKeys[i].keyItem))
            {
                //�Q�|�P�j�L�[���� == �h���ʃR�[�h�h �̏ꍇ<BR>
                l_strSubCond = "request_number";
            }
            else if (WEB3InformKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�Q�|�Q�j�L�[���� == �h���X�R�[�h�h �̏ꍇ<BR>
                l_strSubCond = "branch_code";
            }
            else if (WEB3InformKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                //�Q�|�R�j�L�[���� == �h�ڋq�R�[�h�h �̏ꍇ<BR>
                l_strSubCond = "account_code";
            }
            else if (WEB3InformKeyItemDef.ACCEPT_TIME.equals(l_sortKeys[i].keyItem))
            {
                //�Q�|�S�j�L�[���� == �h��t�����h �̏ꍇ<BR>
                l_strSubCond = "created_timestamp";
            }
            else
            {
                continue;
            }
            
            if (l_sbSortCond.length() != 0)
            {
                l_sbSortCond.append(", ");
            }
            l_sbSortCond.append(l_strSubCond);
            
            if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_sbSortCond.append(" desc");
            }
            else
            {
                l_sbSortCond.append(" asc");
            }
        }

        //�R�j�������ꂽ�������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbSortCond.toString();
    }

    /**
     * ���X�R�[�h���\�[�g�����̐擪�ɒǉ�����ׁA<BR>
     * �\�[�g������ҏW����B<BR>
     * <BR>
     * �P�j�A����񌟍��\�[�g�L�[�̔z��𐶐�����B<BR>
     * <BR>
     * �@@�E�v�f���F ����.�\�[�g�L�[�̗v�f�� + 1<BR>
     * <BR>
     * �Q�j�P�j�̃\�[�g�L�[�̐擪�v�f�ɕ��X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �@@�E�\�[�g�L�[.�L�[���� = ���X�R�[�h<BR>
     * �@@�E�\�[�g�L�[.����/�~�� = A�F����<BR>
     * <BR>
     * �R�j�P�j�̃\�[�g�L�[�̎c��̗v�f�Ɉ���.�\�[�g�L�[�̊e�v�f��������B<BR>
     * <BR>
     * �S�j���������\�[�g�L�[�z���ԋp����B<BR> 
     * @@param l_sortKeys -- �A����񌟍��\�[�g�L�[[]<BR>
     * @@return l_informSortKeys -- �A����񌟍��\�[�g�L�[[]<BR>
     */
    public WEB3InformSortKey[] editSortCond(WEB3InformSortKey[] l_sortKeys)
    {
        if (l_sortKeys == null)
        {
            return null;
        }

        //�P�j�A����񌟍��\�[�g�L�[�̔z��𐶐�����B 
       //�@@�E�v�f���F ����.�\�[�g�L�[�̗v�f�� + 1 
        WEB3InformSortKey[] l_informSortKeys = new WEB3InformSortKey[l_sortKeys.length + 1];

        //�Q�j�P�j�̃\�[�g�L�[�̐擪�v�f�ɕ��X�R�[�h���Z�b�g����B 
       //�@@�E�\�[�g�L�[.�L�[���� = ���X�R�[�h 
       //�@@�E�\�[�g�L�[.����/�~�� = A�F����
       WEB3InformSortKey l_informSortKey = new WEB3InformSortKey();
       l_informSortKey.keyItem = WEB3InformKeyItemDef.BRANCH_CODE;
       l_informSortKey.ascDesc = WEB3AscDescDef.ASC;
       l_informSortKeys[0] = l_informSortKey;

       //�R�j�P�j�̃\�[�g�L�[�̎c��̗v�f�Ɉ���.�\�[�g�L�[�̊e�v�f��������B
       for (int i = 0; i < l_sortKeys.length; i++)
       {
           l_informSortKeys[i + 1] = l_sortKeys[i];
       }

       return l_informSortKeys;
    }
}
@
