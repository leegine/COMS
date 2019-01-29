head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.22.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioVirtualAccCashinULCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�[�`������������CSV (WEB3AdminAioVirtualAccCashinULCsv)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/5/9 ���� (���u) �V�K�쐬      
Revesion History : 2006/5/9 ���� (���u) ���f��No.555 
Revesion History : 2006/5/16 ���� (���u) ���f��No.583       
Revesion History : 2006/5/19 �R�c���a (SCS) ���f��No.585
Revesion History : 2006/5/20 �R�c���a (SCS) ���f��No.586
Revesion History : 2006/5/22 ��؍� (SCS) ���f��No.588
Revesion History : 2006/06/12 ������ (���u) ���f��No.591,594
Revesion History : 2006/06/22 �R�c���a (SCS) ���f��No.596,597
Revesion History : 2006/07/21 ���G�� (���u) ���f��No.606
Revesion History : 2006/08/04 ꎉ� (���u) ���f��No.611
Revesion History : 2006/08/17 �������I (SCS) �����̖��No.004
Revesion History : 2006/08/31 �R�c���a (SCS) ���f��No.638
Revesion History : 2006/10/17 �Ԑi (���u) ���f��No.659
Revesion History : 2006/10/20 �Ԑi (���u) ���f��No.672
Revesion History : 2007/01/08 �Ԑi (���u) ���f��No.689
Revesion History : 2007/01/10 �����q (���u) ���f��No.690
Revesion History : 2009/02/05 �đo�g (���u) ���f��No.1094,No.1104
*/

package webbroker3.aio;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.aio.define.WEB3AdminFXAccountCodeDef;
import webbroker3.aio.define.WEB3AdminFXBankBranchCodeDef;
import webbroker3.aio.define.WEB3AdminFXBankCodeDef;
import webbroker3.aio.define.WEB3AdminFXDataDivDef;
import webbroker3.aio.define.WEB3AdminFXPersonCodeDef;
import webbroker3.aio.define.WEB3AdminFXTypeCodeDef;
import webbroker3.aio.define.WEB3AdminFXUploadNoteOneDef;
import webbroker3.aio.message.WEB3AdminAioVirtualAccCashinULConfirmRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CancelDivDef;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3DaemonTriggerTypeDef;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempDao;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�o�[�`������������CSV)<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminAioVirtualAccCashinULCsv extends WEB3GentradeCsvUploadDataModel
{
   
    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioVirtualAccCashinULCsv.class);
    
    /**
     * (�A�b�v���[�h�t�@@�C��ID)<BR>
     */
    public String UPLOAD_FILEID = "�o�[�`������������";
   
    /**
     * (�A�b�v���[�h���~)<BR>
     * �A�b�v���[�h���~�p�R�����g<BR>
     */
    public String UPLOAD_STOP = "���~";
   
    /**
     * (��s�R�[�h)<BR>
     */
    public String bankCode;
   
    /**
     * (�����ԍ�)<BR>
     */
    public String accountCode;
   
    /**
     * (�a�����)<BR>
     */
    public String depositBankAccountType;
   
    /**
     * (�w�b�_�[���R�[�h����)<BR>
     * �w�b�_�[���R�[�h���J�E���g���邽�߂̕ϐ�<BR>
     */
    public int headerRecordCount = 0;
   
    /**
     * (�f�[�^���R�[�h����)<BR>
     * �f�[�^���R�[�h���J�E���g���邽�߂̕ϐ�<BR>
     */
    public int dataRecordCount = 0;
   
    /**
     * (�g���[���[���R�[�h����)<BR>
     * �g���[���[���R�[�h���J�E���g���邽�߂̕ϐ�<BR>
     */
    public int trailerRecordCount = 0;
   
    /**
     * (�G���h���R�[�h����)<BR>
     * �G���h���R�[�h���J�E���g���邽�߂̕ϐ�<BR>
     */
    public int endRecordCount = 0;
   
    /**
     * (�g�[�^������)<BR>
     * �S���R�[�h�����J�E���g���邽�߂̕ϐ�<BR>
     */
    public int totalCount = 0;
   
    /**
     * (�ǂݔ�΂������R�[�h����)<BR>
     */
    public int skipReadRecordCount = 0;
   
    /**
     * (�`�F�b�N�p�f�[�^���R�[�h����)<BR>
     */
    public int checkDataRecordCount = 0;
   
    /**
     * (�l�`�F�b�N)<BR>
     * validate�ŃG���[�����������ꍇ�̃`�F�b�N���e�\���p<BR>
     */
    private String VALUE_CHECK = "�l�`�F�b�N";
   
    /**
     * (���l�`�F�b�N)<BR>
     * validate�ŃG���[�����������ꍇ�̃`�F�b�N���e�\���p<BR>
     */
    private String NUMBER_CHECK = "���l�`�F�b�N";
   
    /**
     * (�����`�F�b�N)<BR>
     * validate�ŃG���[�����������ꍇ�̃`�F�b�N���e�\���p<BR>
     */
    private String LENGTH_CHECK = "�����`�F�b�N";
   
    /**
     * (���t�`�F�b�N)<BR>
     * validate�ŃG���[�����������ꍇ�̃`�F�b�N���e�\���p<BR>
     */
    private String DATE_CHECK = "���t�`�F�b�N";
    
    /**
     * (���z�`�F�b�N)<BR>
     * validate�ŃG���[�����������ꍇ�̃`�F�b�N���e�\���p<BR>
     */
    private String AMOUNT_CHECK = "���z�`�F�b�N";
   
    /**
     * (�o�[�`������������CSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * @@roseuid 4456FBD80078
     */
    public WEB3AdminAioVirtualAccCashinULCsv () 
    {
               
    }
   
    /**
     * (�o�[�`������������CSV)<BR>
     * �R���X�g���N�^ <BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B <BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_lngUploadID - (�A�b�v���[�hID)
     * @@roseuid 4456FB6A02CD
     */
    public WEB3AdminAioVirtualAccCashinULCsv (long l_lngUploadId) 
    {
        final String STR_METHOD_NAME = 
            " WEB3AdminAioVirtualAccCashinULCsv (long l_lngUploadId) ";
        log.entering (STR_METHOD_NAME );
        
        //�|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B
        super.administratorUploadId = l_lngUploadId;       
             
        log.exiting (STR_METHOD_NAME);
    }
   
    /**
     * (check�f�[�^�敪)<BR>
     * ����.�f�[�^�敪���`�F�b�N���A�ȉ��̏������s���B <BR>
     * <BR>
     * �@@�P�j�g�[�^��������1�����C���N�������g����B <BR>
     * <BR>
     * �@@�Q�|�P�j�f�[�^�敪��1�̏ꍇ �F this.�w�b�_�[���R�[�h������1�����C���N�������g���A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"1"��Ԃ��B <BR>
     * �@@�Q�|�Q�j�f�[�^�敪��2�̏ꍇ �F this.�f�[�^���R�[�h������1�����C���N�������g���A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@this.�`�F�b�N�p�f�[�^���R�[�h������1�����C���N�������g���A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"2"��Ԃ��B <BR>
     * �@@�Q�|�R�j�f�[�^�敪��8�̏ꍇ �F this.�g���[���[���R�[�h������1�����C���N�������g���A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"0"��Ԃ���B <BR>
     * �@@�Q�|�S�j�f�[�^�敪��9�̏ꍇ �F this.�G���h���R�[�h������1�����C���N�������g���A <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"9"��Ԃ��B <BR>
     * �@@�Q�|�T�j�f�[�^�敪����L�̂��̈ȊO�̏ꍇ �F �ԋp�l"0"��Ԃ���B<BR>
     * @@param l_strDataDiv - (�f�[�^�敪)
     * @@return int
     * @@roseuid 445C07810100
     */
    public int checkDataDiv (String l_strDataDiv) 
    {
        final String STR_METHOD_NAME = " checkDataDiv (String l_strDataDiv) ";
        log.entering (STR_METHOD_NAME );    
        
        //�P�j�g�[�^��������1�����C���N�������g����B       
        totalCount ++;
        
        //�Q�|�P�j�f�[�^�敪��1�̏ꍇ �F this.�w�b�_�[���R�[�h������1�����C���N�������g���A
        // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"1"��Ԃ��B
        if (WEB3AdminFXDataDivDef.HEADER_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.headerRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 1;
        }
        
        //�Q�|�Q�j�f�[�^�敪��2�̏ꍇ �F this.�f�[�^���R�[�h������1�����C���N�������g���A
        //                             this.�`�F�b�N�p�f�[�^���R�[�h������1�����C���N�������g���A
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"2"��Ԃ��B
        else if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.dataRecordCount ++;
            this.checkDataRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 2;
        }
        
        //�Q�|�R�j�f�[�^�敪��8�̏ꍇ �F this.�g���[���[���R�[�h������1�����C���N�������g���A
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"0"��Ԃ��B
        else if (WEB3AdminFXDataDivDef.TRAILER_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.trailerRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 0;
        }
        
        //�Q�|�S�j�f�[�^�敪��9�̏ꍇ �F this.�G���h���R�[�h������1�����C���N�������g���A
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�ԋp�l"9"��Ԃ��B 
        else if (WEB3AdminFXDataDivDef.END_RECORD_COUNT.equals(l_strDataDiv))
        {
            this.endRecordCount ++;
            
            log.exiting (STR_METHOD_NAME);
            return 9;
        }
        
        //�Q�|�T�j�f�[�^�敪����L�̂��̈ȊO�̏ꍇ �F �ԋp�l"0"��Ԃ���B
        else
        {
            log.exiting (STR_METHOD_NAME);
            return 0;
        }
    }
   
    /**
     * (validate�w�b�_�[���R�[�h)<BR>
     * �w�b�_�[���R�[�h�̃`�F�b�N���s���B <BR>
     * ��O�����������ꍇ�́A��O�̒ǉ�������Ɉȉ����Z�b�g����B <BR>
     * �`�F�b�N���e + "," + �Ώۍ��� + "," + �Ώےl + "," + this.�g�[�^������ <BR>
     * �iBUSINESS_ERROR_02437�j <BR>
     * <BR>
     * �@@�P�j ���R�[�h���擾����B <BR>
     * �@@�@@�E���R�[�h�@@=�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[����.�s�ԍ�] <BR>
     * <BR>
     * �@@�Q�j ��s���ʃ`�F�b�N <BR>
     * �@@�@@�@@2)-1 ���R�[�h�����`�F�b�N <BR>
     * �@@�@@�@@�@@�@@�E���R�[�h�������擾���A200���ȊO�̏ꍇ�̓G���[�B<BR> 
     * �@@�@@�@@�@@�@@�E�`�F�b�N���e��"�����`�F�b�N"�A�Ώۍ��ڂ�"���R�[�h" <BR>
     * �@@�@@�@@�@@�@@�i���Ώےl�ɂ́A�`�F�b�N�Ώۂ̃��R�[�h��ݒ肷��j <BR>
     * <BR>
     * �@@�@@�@@2)-2 ��ʃR�[�h�`�F�b�N <BR>
     * �@@�@@�@@�@@�@@�E���R�[�h2�`3���ځisubstring(1, 3)�j���擾���A<BR> 
     *          �u01�F�U�������ʒm�v�ȊO�̏ꍇ�̓G���[�B <BR>
     * �@@�@@�@@�@@�@@�E�`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"��ʃR�[�h"<BR> 
     * <BR>
     * �@@�@@�@@2)-3 ��s�R�[�h�`�F�b�N <BR>
     * �@@�@@�@@�@@�@@�E���R�[�h23�`26���ځisubstring(22, 26)�j���擾���A<BR> 
     *          �u���N�G�X�g�f�[�^.��s�R�[�h�v�ȊO�̏ꍇ�̓G���[�B <BR>
     * �@@�@@�@@�@@�@@�E�`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"��s�R�[�h" <BR>
     * <BR>
     * �@@�@@�@@2)-5 �����ԍ��`�F�b�N  <BR>
     * �@@�@@�@@�@@�@@�E���R�[�h61�`67���ځisubstring(60, 67)�j���擾���A���l�ȊO�̏ꍇ�̓G���[�B<BR> 
     * �@@�@@�@@�@@�@@�E�������A��O�Ƃ��āA�I�[���X�y�[�X�̏ꍇ�̓`�F�b�N�����Ȃ��B<BR> 
     * �@@�@@�@@�@@�@@�E�`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"�����ԍ�" <BR>
     * <BR>
     * �@@�R�j ��s�ʃ`�F�b�N <BR>
     * �@@�@@�@@3)-1 ���N�G�X�g�f�[�^.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@�@@ �a����ڃ`�F�b�N <BR>
     * �@@�@@�@@�@@�@@�E���R�[�h60���ځisubstring(59, 60)�j���擾���A <BR>
     * �@@�@@�@@�@@�@@�@@�u1�F���ʗa���v�A�u2�F�����a���v�A�u4�F���~�a���v�ȊO�̏ꍇ�̓G���[�B<BR> 
     * �@@�@@�@@�@@�@@�E�`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"�a�����" <BR>
     * <BR>
     * �@@�@@�@@3)-2 ���N�G�X�g�f�[�^.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ <BR>
     * <BR>
     * �@@�@@�@@�@@�@@ �a����ڃ`�F�b�N <BR>
     * �@@�@@�@@�@@�@@�E���R�[�h60���ځisubstring(59, 60)�j���擾���A <BR>
     * �@@�@@�@@�@@�@@�@@�u1�F���ʗa���v�A�u2�F�����a���v�ȊO�̏ꍇ�̓G���[�B <BR>
     * �@@�@@�@@�@@�@@�E�`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"�a�����" <BR>
     * @@param  l_intLineNumber - (�s�ԍ�)
     * @@param  l_request - (�o�[�`������������UL�m�F���N�G�X�g)
     * @@throws WEB3BaseException
     * @@roseuid 445C09E00207
     */
    public void validateHeaderRecord(int l_intLineNumber, WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = " validateHeaderRecord(int, WEB3AdminAioVirtualAccCashinULConfirmRequest) ";
        log.entering (STR_METHOD_NAME );       
        
        //�P�j���R�[�h���擾����B
        //  ���R�[�h�@@=�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[����.�s�ԍ�]
        String l_strRecord = l_request.uploadFile[l_intLineNumber];
        
        //�Q�j ��s���ʃ`�F�b�N 
        //2)-1���R�[�h�����`�F�b�N 
        //�@@  ���R�[�h�������擾���A200���ȊO�̏ꍇ�̓G���[�B
        //�@@�@@�`�F�b�N���e��"�����`�F�b�N"�A�Ώۍ��ڂ�"���R�[�h"
        if (l_strRecord.length() != 200)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException (
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                LENGTH_CHECK + "," + "���R�[�h" + "," + l_strRecord + "," + this.totalCount);
        }
        
        //2)-2 ��ʃR�[�h�`�F�b�N 
        // ���R�[�h2�`3���ځisubstring(1, 3)�j���擾���A 
        //�u01�F�U�������ʒm�v�ȊO�̏ꍇ�̓G���[�B 
        // �`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"��ʃR�[�h" 
        if (!WEB3AdminFXTypeCodeDef.TRANSFER_CASHIN_NOTIFY.equals(l_strRecord.substring(1, 3)))
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                VALUE_CHECK + "," + "��ʃR�[�h" + "," + l_strRecord.substring(1, 3) + "," + this.totalCount);
        }
        
        //2)-3 ��s�R�[�h�`�F�b�N 
        //  ���R�[�h23�`26���ځisubstring(22, 26)�j���擾���A 
        //  ���N�G�X�g�f�[�^.��s�R�[�h�v�ȊO�̏ꍇ�̓G���[�B
        //  �`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"��s�R�[�h"
        if (l_request.financialInstitutionCode != null)
        {
            if (!l_request.financialInstitutionCode.equals(l_strRecord.substring(22, 26)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    VALUE_CHECK + "," + "��s�R�[�h" + "," + l_strRecord.substring(22, 26) + "," + this.totalCount);
            }
        }

        
        //2)-5 �����ԍ��`�F�b�N  
        //  ���R�[�h61�`67���ڂ��擾���A���l�ȊO�̏ꍇ�̓G���[�B 
        //  �������A��O�Ƃ��āA�I�[���X�y�[�X�̏ꍇ�̓`�F�b�N�����Ȃ��B�@@ 
        //  �`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"�����ԍ�"
        if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(60, 67))
            && !("".equals(l_strRecord.substring(60, 67).trim())))
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                NUMBER_CHECK + "," + "�����ԍ�" + "," + l_strRecord.substring(60, 67) + "," + this.totalCount);                     
        }      

        //�R�j ��s�ʃ`�F�b�N 
        //3)-1 ���N�G�X�g�f�[�^.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ 
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_request.financialInstitutionCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_request.financialInstitutionCode))
        {                   
            //�@@ �a����ڃ`�F�b�N 
            //�E���R�[�h60���ځisubstring(59, 60)�j���擾���A
            //�u1�F���ʗa���v�A�u2�F�����a���v�A�u4�F���~�a���v�ȊO�̏ꍇ�̓G���[�B 
            //�E�`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"�a�����" 
            if (!WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strRecord.substring(59, 60)) 
                && !WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strRecord.substring(59, 60))
                && !WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strRecord.substring(59, 60)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    VALUE_CHECK + "," + "�a�����" + "," + l_strRecord.substring(59, 60) + "," + this.totalCount);
            }

        }

        //(3)-2 ���N�G�X�g�f�[�^.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ 
        else if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_request.financialInstitutionCode))
        {
            //�@@ �a����ڃ`�F�b�N 
            //�@@�@@�E���R�[�h60���ځisubstring(59, 60)�j���擾���A 
            //�@@�@@�@@�u1�F���ʗa���v�A�u2�F�����a���v�ȊO�̏ꍇ�̓G���[�B 
            //�@@�@@�E�`�F�b�N���e��"�l�`�F�b�N"�A�Ώۍ��ڂ�"�a�����" 
            if (!WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strRecord.substring(59, 60)) 
                && !WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strRecord.substring(59, 60)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    VALUE_CHECK + "," + "�a�����" + "," + l_strRecord.substring(59, 60) + "," + this.totalCount);
            }
        }
           
    
        log.exiting(STR_METHOD_NAME);   
    }
    
    /**
     * (validate�f�[�^���R�[�h)<BR>
     * �f�[�^���R�[�h�̃`�F�b�N���s���B <BR>
     * ��O�����������ꍇ�́A��O�̒ǉ�������Ɉȉ����Z�b�g����B<BR> 
     * �`�F�b�N���e + "," + �Ώۍ��� + "," + �Ώےl + "," + this.�g�[�^������ <BR>
     * �iBUSINESS_ERROR_02437�j <BR>
     * <BR>
     * �@@�P�j ���R�[�h���擾����B<BR> 
     * �@@�@@���R�[�h�@@=�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[����.�s�ԍ�] <BR>
     * <BR>
     * �@@�Q�j ��s���ʃ`�F�b�N <BR>
     * �@@�@@�@@2)-1 ���R�[�h�����`�F�b�N <BR>
     * �@@�@@�@@�@@�E���R�[�h�������擾���A200���ȊO�̏ꍇ�̓G���[�B<BR> 
     * �@@�@@�@@�@@�E�`�F�b�N���e��"�����`�F�b�N"�A�Ώۍ��ڂ�"���R�[�h" <BR>
     * �@@�@@�@@�@@�i���Ώےl�ɂ́A�`�F�b�N�Ώۂ̃��R�[�h��ݒ肷��j<BR> 
     * <BR>
     * �@@�@@�@@2)-2 ����� �L�����t�`�F�b�N  <BR>
     * �@@�@@�@@�@@�E����� �� ���R�[�h8�`13���ځisubstring(7, 13)�j���擾<BR>
     * �@@�@@�@@�@@�E�u�N��.getJapEraDiv(�����)�v�ɂĔN�����擾<BR>
     * �@@�@@�@@�@@�E�u�N��.toDate(�擾�����N�� , �����)�v�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@��null�̏ꍇ�̓G���[�B<BR>
     * �@@�@@�@@�@@�E�`�F�b�N���e��"���t�`�F�b�N"�A�Ώۍ��ڂ�"�����"<BR>
     * <BR>
     * �@@�@@�@@2)-3 �N�Z�� �L�����t�`�F�b�N <BR>
     * �@@�@@�@@�@@�E�N�Z�� �� ���R�[�h14�`19���ځisubstring(13, 19)�j���擾<BR>
     * �@@�@@�@@�@@�E�u�N��.getJapEraDiv(�N�Z��)�v�ɂĔN�����擾<BR>
     * �@@�@@�@@�@@�E�u�N��.toDate(�擾�����N�� , �N�Z��)�v�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@�@@��null�̏ꍇ�̓G���[�B<BR>
     * �@@�@@�@@�@@�E�`�F�b�N���e��"���t�`�F�b�N"�A�Ώۍ��ڂ�"�N�Z��"<BR>
     * <BR>
     * �@@�R�j ��s�ʃ`�F�b�N <BR>
     * �@@�@@�@@3)-1 ���N�G�X�g�f�[�^.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ <BR>
     *          �E���z�i1�j�� ���R�[�h20�`29���ځisubstring(19, 29)�j���擾  <BR>
     *          �E���z�i2�j�� ���R�[�h129�`140���ځisubstring(128, 140)�j���擾  <BR>
     * <BR>
     *          �@@���z�i1�j�`�F�b�N   <BR>    
     *          �E�u���z�i1�j�v�����l�ȊO�̏ꍇ�̓G���[�B    <BR>   
     *          �E�`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"���z�i1�j"  <BR>
     * <BR>          
     * �@@�@@�@@�@@�A���z�i2�j�`�F�b�N  <BR>
     *          �E�u���z�i2�j�v�����l�ȊO�̏ꍇ�̓G���[�B <BR>
     *          �E�`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"���z�i2�j" <BR>
     * <BR>          
     * �@@�@@�@@  �B���z0�~�ȉ��`�F�b�N  <BR>
     *          �E�u���z�i1�j�v���u���z�i2�j�v���A0�ȉ��̏ꍇ�̓G���[�B <BR>
     *          �E�`�F�b�N���e��"���z�`�F�b�N"�A�Ώۍ��ڂ�"���z" <BR>
     * <BR>               
     *        3)-2 ���N�G�X�g�f�[�^.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ<BR>
     *          �E���z �� ���R�[�h20�`29���ځisubstring(19, 29)�j���擾  <BR>
     *  <BR>           
     *          �@@���z�`�F�b�N  <BR>
     *          �E�u���z�v�����l�ȊO�̏ꍇ�̓G���[�B <BR>
     *          �E�`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"���z"  <BR>
     *  <BR>           
     *          �A���z0�~�ȉ��`�F�b�N  <BR>
     *          �E�u���z�v��0�ȉ��̏ꍇ�̓G���[�B  <BR>
     *          �E�`�F�b�N���e��"���z�`�F�b�N"�A�Ώۍ��ڂ�"���z" <BR>
     * @@param  l_intLineNumber - (�s�ԍ�)
     * @@param  l_request - (�o�[�`������������UL�m�F���N�G�X�g)
     * @@throws WEB3BaseException
     * @@roseuid 445C0A020293
     */
    public void validateDataRecord(
        int l_intLineNumber, 
        WEB3AdminAioVirtualAccCashinULConfirmRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDataRecord(" +
            "int, WEB3AdminAioVirtualAccCashinULConfirmRequest) ";
        log.entering(STR_METHOD_NAME );   

        //�P�j���R�[�h���擾����B
        //  ���R�[�h�@@=�@@���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[����.�s�ԍ�]
        String l_strRecord = l_request.uploadFile[l_intLineNumber];
        
        //�Q�j ��s���ʃ`�F�b�N 
        //2)-1���R�[�h�����`�F�b�N
        //�@@  ���R�[�h�������擾���A200���ȊO�̏ꍇ�̓G���[�B
        //�@@�@@�`�F�b�N���e��"�����`�F�b�N"�A�Ώۍ��ڂ�"���R�[�h"
        if (l_strRecord.length() != 200)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                LENGTH_CHECK + "," + "���R�[�h" + "," + l_strRecord + "," + this.totalCount);
        }
        
        //2)-2 ����� �L�����t�`�F�b�N 
        //�@@�@@�E����� �� ���R�[�h8�`13���ځisubstring(7, 13)�j���擾
        //�@@�@@�E�u�N��.getJapEraDiv(�����)�v�ɂĔN�����擾
        //�@@�@@�E�u�N��.toDate(�擾�����N�� , �����)�v�̖߂�l��null�̏ꍇ�̓G���[�B
        //�@@�@@�E�`�F�b�N���e��"���t�`�F�b�N"�A�Ώۍ��ڂ�"�����"
        String l_strSettlementDate = l_strRecord.substring(7, 13);
        String l_strSettlementJapEraDiv = WEB3GentradeEra.getJapEraDiv(l_strSettlementDate);
        if (WEB3GentradeEra.toDate(l_strSettlementJapEraDiv, l_strSettlementDate) == null)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                DATE_CHECK + "," + "�����" + "," + l_strRecord.substring(7, 13) + "," + this.totalCount);
        }
        
        //2)-3 �N�Z�� �L�����t�`�F�b�N
        //�@@�@@�E�N�Z�� �� ���R�[�h14�`19���ځisubstring(13, 19)�j���擾
        //�@@�@@�E�u�N��.getJapEraDiv(�N�Z��)�v�ɂĔN�����擾
        //�@@�@@�E�u�N��.toDate(�擾�����N�� , �N�Z��)�v�̖߂�l��null�̏ꍇ�̓G���[�B
        //�@@�@@�E�`�F�b�N���e��"���t�`�F�b�N"�A�Ώۍ��ڂ�"�N�Z��"
        String l_strStartDay = l_strRecord.substring(13, 19);
        String l_strStartDayJapEraDiv = WEB3GentradeEra.getJapEraDiv(l_strStartDay);
        if (WEB3GentradeEra.toDate(l_strStartDayJapEraDiv, l_strStartDay) == null)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                DATE_CHECK + "," + "�N�Z��" + "," + l_strRecord.substring(13, 19) + "," + this.totalCount);
        }
            
        //�@@�R�j ��s�ʃ`�F�b�N 
        //      3)-1 ���N�G�X�g�f�[�^.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ
        if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_request.financialInstitutionCode))
        {   
            // ���z�i1�j�� ���R�[�h20�`29���ځisubstring(19, 29)�j���擾
            // �@@���z�i1�j�`�F�b�N   
            //   �E�u���z�i1�j�v�����l�ȊO�̏ꍇ�̓G���[�B      
            //   �E�`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"���z�i1�j"  
            if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(19, 29)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    NUMBER_CHECK + "," + "���z(1)" + "," + l_strRecord.substring(19, 29) + "," + this.totalCount);
            } 
            
            // ���z�i2�j�� ���R�[�h129�`140���ځisubstring(128, 140)�j���擾 
            //�@@�A���z�i2�j�`�F�b�N 
            //  �E�u���z�i2�j�v�����l�ȊO�̏ꍇ�̓G���[�B 
            //  �E�`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"���z�i2�j" 
            if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(128, 140)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    NUMBER_CHECK + "," + "���z(2)" + "," + l_strRecord.substring(128, 140) + "," + this.totalCount);
            }
            
            //  �B���z0�~�ȉ��`�F�b�N  
            //   �E�u���z�i1�j�v���u���z�i2�j�v���A0�ȉ��̏ꍇ�̓G���[�B 
            //   �E�`�F�b�N���e��"���z�`�F�b�N"�A�Ώۍ��ڂ�"���z" 
            if (Double.parseDouble(l_strRecord.substring(19, 29)) <= 0 
                && Double.parseDouble(l_strRecord.substring(128, 140)) <= 0)
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    AMOUNT_CHECK + "," + "���z" + ","+ 
                    l_strRecord.substring(19, 29) + "," +
                    l_strRecord.substring(128, 140) + "," + 
                    this.totalCount);
            }   
        } 
        //    3)-2 ���N�G�X�g�f�[�^.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_request.financialInstitutionCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_request.financialInstitutionCode))
        {     
            //���z �� ���R�[�h20�`29���ځisubstring(19, 29)�j���擾
            //�@@���z�`�F�b�N  
            //  �E�u���z�v�����l�ȊO�̏ꍇ�̓G���[�B 
            //  �E�`�F�b�N���e��"���l�`�F�b�N"�A�Ώۍ��ڂ�"���z"  
            if (!WEB3StringTypeUtility.isNumber(l_strRecord.substring(19, 29)))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    NUMBER_CHECK + "," + "���z" + "," + l_strRecord.substring(19, 29) + "," + this.totalCount);
            }    
            
            //   �A���z0�~�ȉ��`�F�b�N  
            //    �E�u���z�v��0�ȉ��̏ꍇ�̓G���[�B  
            //    �E�`�F�b�N���e��"���z�`�F�b�N"�A�Ώۍ��ڂ�"���z"
            if (Double.parseDouble(l_strRecord.substring(19, 29)) <= 0)
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02437,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    AMOUNT_CHECK + "," + "���z" + "," + l_strRecord.substring(19, 29) + "," + this.totalCount);
            }   
        } 
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (calc�ǂݔ�΂�����)<BR>
     * �ǂݔ�΂������R�[�h�������v�Z����B<BR>
     * <BR>
     * �ǂݔ�΂������R�[�h���� = this.�g�[�^������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ - this.�w�b�_���R�[�h����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ - this.�f�[�^���R�[�h����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ - this.�g���[���[���R�[�h����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@ - this.�G���h���R�[�h���� <BR>
     * @@roseuid 445C0B690345
     */
    public void calcSkipReadCount() 
    {
        final String STR_METHOD_NAME = " calcSkipReadCount() ";
        log.entering(STR_METHOD_NAME ); 
        
        skipReadRecordCount = this.totalCount 
            - this.headerRecordCount 
            - this.dataRecordCount
            - this.trailerRecordCount 
            - this.endRecordCount;
                
        log.exiting(STR_METHOD_NAME);  
    }
   
    /**
     * (check�f�[�^���R�[�h����)<BR>
     * �f�[�^���R�[�h�����̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�P�jthis.�`�F�b�N�p�f�[�^���R�[�h������1���ȏ゠�邩�`�F�b�N���s���B<BR>
     * �@@�@@�@@this.�`�F�b�N�p�f�[�^���R�[�h������0���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�iBUSINESS_ERROR_02438�j<BR>
     * <BR>
     * �@@�Q�jthis.�`�F�b�N�p�f�[�^���R�[�h�������������i=0�j�ɂ���B<BR>
     * @@throws WEB3BaseException
     * @@roseuid 445C0F490294
     */
    public void checkDataRecordCount() throws WEB3BaseException
    { 
        final String STR_METHOD_NAME = 
            " checkDataRecordCount() ";
        log.entering(STR_METHOD_NAME ); 
        
        //�P�jthis.�`�F�b�N�p�f�[�^���R�[�h������1���ȏ゠�邩�`�F�b�N���s���B
        //�@@�@@this.�`�F�b�N�p�f�[�^���R�[�h������0���̏ꍇ�A��O���X���[����B
        //�@@ �iBUSINESS_ERROR_02438�j
        if (this.checkDataRecordCount == 0)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02438,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�f�[�^���R�[�h������0��");
        }
        
        //�Q�jthis.�`�F�b�N�p�f�[�^���R�[�h�������������i=0�j�ɂ���B
        checkDataRecordCount = 0;
        
        log.exiting(STR_METHOD_NAME);    
    }
   
    /**
     * (check��������)<BR>
     * ���̋@@�\�ɂē����������s���Ă��邩�`�F�b�N������B<BR>
     * <BR>
     * �@@�P�j�f���[�����g���K�[�e�[�u�����u�����^�C�v = O�F�����ʒm�v�̏����Ō�������B<BR>
     * <BR>
     * �@@�Q�j�擾�������R�[�h���ɂāu������� = 0�FAP���ғ��v�ȊO��<BR>
     * �@@�@@  ���R�[�h�ł���ꍇ�A��O���X���[����B<BR>
     * �@@�@@�iBUSINESS_ERROR_02439�j<BR>
     *<BR>�@@�@@�@@
     * @@throws WEB3BaseException 
     * @@roseuid 445C26CD0023
     */
    public void checkCashinTransaction() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " checkCashinTransaction() ";
        log.entering(STR_METHOD_NAME );  
        
        //�P�j�f���[�����g���K�[�e�[�u�����u�����^�C�v = O�F�����ʒm�v�̏����Ō�������B
        String l_strWhere = "trigger_type = ?";
        Object[] l_objValue = {WEB3DaemonTriggerTypeDef.ORIX_CASHIN_NOTICE};
                
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisRows.size() == 0 || l_lisRows == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�[�h���擾�ł��܂���ł���");
        }
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            //�Q�j�擾�������R�[�h���ɂāu������� = 0�FAP���ғ��v�ȊO��
            //�@@  ���R�[�h�ł���ꍇ�A��O���X���[����B
            //�@@�@@�iBUSINESS_ERROR_02439�j
            DaemonTriggerRow l_daemonTriggerRow = (DaemonTriggerRow)l_lisRows.get(i);  
            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams(l_daemonTriggerRow);
            
            if (!WEB3DaemonTriggerStatusDef.THREAD_IDLE.equals(l_daemonTriggerParams.getTriggerStatus()))
            {
                log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02439,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������� = 0�FAP���ғ��ȊO");
            }
        }
                
        log.exiting(STR_METHOD_NAME);      
    }
   
    /**
     * (stop�����f�[����)<BR>
     * �f�[�����g���K�[�e�[�u���̒�~���s���B<BR>
     * <BR>
     * �@@�P�j�f�[�����g���K�[�e�[�u�����u�����^�C�v = O�F�����ʒm�v�Ō�������B<BR>
     * �@@�Q�j�擾�������R�[�h�́u������ԁv��1�FAP�������ɍX�V����B<BR>
     * �@@�R�jcommit���s���B<BR>
     * @@throws WEB3BaseException 
     * @@throws NotFoundException 
     * @@roseuid 445C2BAA0109
     */
    public void stopCashinDaemon() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " stopCashinDaemon() ";
        log.entering(STR_METHOD_NAME );    
        
        //�P�j�f�[�����g���K�[�e�[�u�����u�����^�C�v = O�F�����ʒm�v�Ō�������B
        String l_strWhere = "trigger_type = ?";
        Object[] l_objValue = {WEB3DaemonTriggerTypeDef.ORIX_CASHIN_NOTICE};
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisRows.size() == 0 || l_lisRows == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�[�h���擾�ł��܂���ł���");
        }
        
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            //�Q�j�擾�������R�[�h�́u������ԁv��1�FAP�������ɍX�V����B
            DaemonTriggerRow l_daemonTriggerRow = (DaemonTriggerRow)l_lisRows.get(i);
            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams(l_daemonTriggerRow);
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            try
            {
                Processors.getDefaultProcessor().doUpdateQuery(l_daemonTriggerParams);
            }
            catch(DataException l_ex)
            {
                log.error("__DataException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        
        log.exiting(STR_METHOD_NAME); 
    }
   
    /**
     * (start�����f�[����)<BR>
     * �f�[�����g���K�[�e�[�u���̊J�n���s���B<BR>
     * <BR>
     * �@@�P�j�f�[�����g���K�[�e�[�u�����u�����^�C�v = O�F�����ʒm�v�Ō�������B<BR>
     * �@@�Q�j�擾�������R�[�h�́u������ԁv��0�FAP���ғ��ɍX�V����B<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 445C2CA402B1
     */
    public void startCashinDaemon() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " startCashinDaemon() ";
        log.entering(STR_METHOD_NAME ); 
        
        //�P�j�f�[�����g���K�[�e�[�u�����u�����^�C�v = O�F�����ʒm�v�Ō�������B
        String l_strWhere = "trigger_type = ?";
        Object[] l_objValue = {WEB3DaemonTriggerTypeDef.ORIX_CASHIN_NOTICE};
        List l_lisRows = null;
        
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    DaemonTriggerRow.TYPE,
                    l_strWhere,
                    null,
                    l_objValue);
        }
        catch (DataException l_ex)
        {
            log.error("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisRows.size() == 0 || l_lisRows == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�[�h���擾�ł��܂���ł���");
        }
        
        for (int i = 0; i < l_lisRows.size(); i++)
        {
            //�Q�j�擾�������R�[�h�́u������ԁv��0�FAP�������ɍX�V����B
            DaemonTriggerRow l_daemonTriggerRow = (DaemonTriggerRow)l_lisRows.get(i);
            DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams(l_daemonTriggerRow);
            l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
            l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            try
            {
                Processors.getDefaultProcessor().doUpdateQuery(l_daemonTriggerParams);
            }
            catch (DataException l_ex)
            {
                log.error("__DataException__", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
               
        log.exiting(STR_METHOD_NAME);  
    }
   
    /**
     * (set�w�b�_�[���R�[�h)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A��s�R�[�h�A�����ԍ��A�a����ʂ�ێ�����B<BR>
     * <BR>
     * [����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�j��s�R�[�h�擾<BR>
     * �@@�@@���R�[�h23�`26���ځisubstring(22, 26)�j���擾���A<BR>
     * �@@�@@this.��s�R�[�h�ɃZ�b�g����B<BR>
     * <BR>
     * �@@�Q�j�a����ڎ擾<BR>
     * �@@�@@���R�[�h60���ځisubstring(59, 60)�j���擾���A<BR>
     * �@@�@@this.�a����ڂɃZ�b�g����B<BR>
     * <BR>
     * �@@�R�j�����ԍ��擾<BR>
     * �@@�@@�R-1�j���R�[�h61�`67���ځisubstring(60, 67)�j���擾����<BR>
     * �@@�@@�R-�Q�j�I�[���X�y�[�X�̏ꍇ�A"0000000"���Z�b�g����B<BR>
     * �@@�@@�R-�R�jthis.�����ԍ��ɃZ�b�g����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@throws WEB3BaseException 
     * @@roseuid 445C366E0299
     */
    public void setHeaderRecord(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setHeaderRecord(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );  
        
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strAccountCode = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        
        //�P�j��s�R�[�h�擾
        //���R�[�h23�`26���ځisubstring(22, 26)�j���擾���A
        //this.��s�R�[�h�ɃZ�b�g����B
        this.bankCode = l_strCsvLineValue.substring(22, 26);
        
        //�Q�j�a����ڎ擾<BR>
        // �@@ ���R�[�h60���ځisubstring(59, 60)�j���擾���A
        //�@@�@@this.�a����ڂɃZ�b�g����B
        this.depositBankAccountType = l_strCsvLineValue.substring(59, 60); 
        
        //�R�j�����ԍ��擾
        //  �R-1�j���R�[�h61�`67���ځisubstring(60, 67)�j���擾����
        l_strAccountCode = l_strCsvLineValue.substring(60, 67);
        
        //  �R-�Q�j�I�[���X�y�[�X�̏ꍇ�A"0000000"���Z�b�g����B
        if ("".equals(l_strAccountCode.trim()))
        {
            l_strAccountCode = WEB3AdminFXAccountCodeDef.ACCOUNT_CODE_0000000;
        }
        
        //�R-�R�jthis.�����ԍ��ɃZ�b�g����B
        this.accountCode = l_strAccountCode;
               
        log.exiting(STR_METHOD_NAME);     
    }
   
    /**
     * (get��s�x�X�R�[�h)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A��s�x�X�R�[�h���擾����B<BR>
     * <BR>
     * ���R�[�h�F�@@���׍s[����.�s�ԍ�]<BR>
     * <BR>
     * �P�j��s�ʏ���<BR> 
     * <BR>
     * �@@�P-�P�j����.��s�R�[�h = �u0149(�É�)�v�̏ꍇ<BR> 
     * �@@�P-�P-�P�j���R�[�h��130�`132���ځisubstring(129, 132)�j��ԋp����B<BR> 
     * <BR>
     * �@@�P-�Q�j����.��s�R�[�h = �u0149(�É�)�v�ȊO�̏ꍇ<BR> 
     * �@@�P-�Q-�P�j���R�[�h��40�`42���ځisubstring(39, 42)�j��ԋp����B<BR> 
     * <BR>
     * �@@�������A�I�[���X�y�[�X�̏ꍇ�A"000"��ԋp����B<BR>�@@�@@
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@param l_strBankCode - (��s�R�[�h)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C2CE003B5
     */
    public String getBankBranchCode(int l_intLineNumber, String l_strBankCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBankBranchCode(int l_intLineNumber, " + 
            "String l_strBankCode)";
        log.entering(STR_METHOD_NAME ); 
      
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strBankBranchCode = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }      
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�s�ԍ��ɑΉ����郌�R�[�h���擾���A��s�x�X�R�[�h���擾����B
          l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();

        // �P-�P�j����.��s�R�[�h = �u0149(�É�)�v�̏ꍇ 
        // �P-�P-�P�j���R�[�h��130�`132���ځisubstring(129, 132)�j��ԋp����B
          if (WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
          {
              l_strBankBranchCode = l_strCsvLineValue.substring(129, 132);
          }
         // �P-�Q�j����.��s�R�[�h = �u0149(�É�)�v�ȊO�̏ꍇ 
         // �P-�Q-�P�j���R�[�h��40�`42���ځisubstring(39, 42)�j��ԋp����B 
          else
          {
              //���R�[�h��40�`42���ځisubstring(39, 42)�j���擾����B
              l_strBankBranchCode = l_strCsvLineValue.substring(39, 42);
          }

        //�I�[���X�y�[�X�̏ꍇ�A"000"���Z�b�g����B
        if ("".equals(l_strBankBranchCode.trim()))
        {
            l_strBankBranchCode = WEB3AdminFXBankBranchCodeDef.BANK_BRANCH_CODE_000;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBankBranchCode;
    }

    /**
     * (get�����)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A��������擾����B<BR>
     * <BR>
     * [����]<BR> 
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@���R�[�h��8�`13���ځisubstring(7, 13)�j���擾����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3A850336
     */
    public String getSettlementDate(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSettlementDate(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strSettlementDate = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        //���R�[�h��8�`13���ځisubstring(7, 13)�j���擾����B
        l_strSettlementDate = l_strCsvLineValue.substring(7, 13);
               
        log.exiting(STR_METHOD_NAME);
        return l_strSettlementDate;
    }
   
    /**
     * (get�N�Z��)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A�N�Z�����擾����B<BR>
     * <BR>
     * [����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@���R�[�h��14�`19���ځisubstring(13, 19)�j���擾����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3AFF0117
     */
    public String getBaseDate(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBaseDate(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
        
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strBaseDate = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        //���R�[�h��14�`19���ځisubstring(13, 19)�j���擾����B
        l_strBaseDate = l_strCsvLineValue.substring(13, 19);
               
        log.exiting(STR_METHOD_NAME);
        return l_strBaseDate;
    }
   
    /**
     * (get���z)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A���z���擾����B<BR> 
     * <BR>
     * ���R�[�h�F�@@���׍s[����.�s�ԍ�] <BR>
     * <BR>
     * �P�j��s�ʏ��� <BR>
     * <BR>
     * �@@�P�j����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ <BR>
     * �@@�P-�P�j�@@���R�[�h��20�`29���ځisubstring(19, 29)�j���擾����B <BR>
     * <BR>
     * �@@�P-�Q�j�@@�Œ蒷�ׁ̈A10���S�Ăɕ����������Ă���̂ŁA <BR>
     * �@@�@@�@@�@@�@@���l�ƔF���ł���`���ɕϊ�����B <BR>
     * �@@�@@�@@�@@�@@�i���̌��ɂ��Ă���]����"0"����菜���j<BR>
     * �@@�@@�@@�@@�@@�i���z�i�P�j�̎擾�j <BR>
     * <BR>
     * �@@�P-�R-�P�j�@@���R�[�h��129�`140���ځisubstring(128, 140)�j���擾����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�i���z�i�Q�j�j�̎擾 <BR>
     * <BR>
     * �@@�P-�R-�Q�j�@@�Œ蒷�ׁ̈A12���S�Ăɕ����������Ă���̂ŁA <BR>
     * �@@�@@�@@�@@�@@�@@�@@���l�ƔF���ł���`���ɕϊ�����B <BR>
     * �@@�@@�@@�@@�@@�@@�@@�i���̌��ɂ��Ă���]����"0"����菜���j <BR>
     * <BR>
     * �@@�P-�R-�R�j�@@�P-�Q�j�Ŏ擾�������z��0�̏ꍇ�A�P-�R-�Q�j�Ŏ擾�������z�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�ԋp����B <BR>
     * <BR>
     * �@@�P-�R-�S�j�@@�P-�Q�j�Ŏ擾�������z��0�ȊO�̏ꍇ�A�P-�Q�j�Ŏ擾�������z�� 
     *�@@�@@�@@�@@�@@�@@�@@�ԋp����B <BR>
     * <BR>
     * �@@�Q�j����.��s�R�[�h = �u0001(�݂���)�v�ȊO�̏ꍇ <BR>
     * �@@�Q-�P�j�@@���R�[�h��20�`29���ځisubstring(19, 29)�j���擾����B <BR>
     * <BR>
     * �@@�Q-�Q�j�@@�Œ蒷�ׁ̈A10���S�Ăɕ����������Ă���̂ŁA <BR>
     * �@@�@@�@@�@@�@@���l�ƔF���ł���`���ɕϊ�����B <BR>
     * �@@�@@�@@�@@�@@�i���̌��ɂ��Ă���]����"0"����菜���j <BR>
     * <BR>
     * �@@�Q-�R�j�@@�Q-�Q�j�Ŏ擾�����l��ԋp����B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@param l_strBankCode - (��s�R�[�h)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3B590288
     */
    public String getAmount(int l_intLineNumber, String l_strBankCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAmount(int, String) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strAmount = null;
        double l_dblAmount = 0.0;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        
        //�P�j����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ 
        //�P-�P�j�@@���R�[�h��20�`29���ځisubstring(19, 29)�j���擾����B 
        if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            l_strAmount = l_strCsvLineValue.substring(19, 29);
        
            //�P-�Q�j�@@�Œ蒷�ׁ̈A10���S�Ăɕ����������Ă���̂ŁA 
            //     ���l�ƔF���ł���`���ɕϊ�����B 
            //     �i���̌��ɂ��Ă���]����"0"����菜���j 
            //     �i���z�i�P�j�̎擾�j 
            
            double l_dblAmount1 = Double.parseDouble(l_strAmount);         
        
            //�P-�R-�P�j�@@���R�[�h��129�`140���ځisubstring(128, 140)�j���擾����B 
            //�@@�@@�@@�i���z�i�Q�j�j�̎擾 
            
            l_strAmount = l_strCsvLineValue.substring(128, 140);
            
            //�P-�R-�Q�j�@@�Œ蒷�ׁ̈A12���S�Ăɕ����������Ă���̂ŁA 
            //      ���l�ƔF���ł���`���ɕϊ�����B 
            //      �i���̌��ɂ��Ă���]����"0"����菜���j 
            double l_dblAmount2 = Double.parseDouble(l_strAmount);
            
            //�P-�R-�R�j�@@�P-�Q�j�Ŏ擾�������z��0�̏ꍇ�A�P-�R-�Q�j�Ŏ擾�������z�� 
            //  �ԋp����B 
            if(l_dblAmount1 == 0) {
                l_dblAmount = l_dblAmount2;

            //�P-�R-�S�j�@@�P-�Q�j�Ŏ擾�������z��0�ȊO�̏ꍇ�A�P-�Q�j�Ŏ擾�������z�� 
            //�ԋp����B
            } else {
                l_dblAmount = l_dblAmount1;
            }

        }
        //�Q�j����.��s�R�[�h = �u0001(�݂���)�v�ȊO�̏ꍇ 
        else
        {
            //�Q-�P�j�@@���R�[�h��20�`29���ځisubstring(19, 29)�j���擾����B 
            l_strAmount = l_strCsvLineValue.substring(19, 29);
            
            //�Q-�Q�j�@@�Œ蒷�ׁ̈A10���S�Ăɕ����������Ă���̂ŁA 
            //      ���l�ƔF���ł���`���ɕϊ�����B 
            //      �i���̌��ɂ��Ă���]����"0"����菜���j
            //�Q-�R�j�@@�Q-�Q�j�Ŏ擾�����l��ԋp����B
            
            l_dblAmount = Double.parseDouble(l_strAmount);                                 
        }
        
        log.exiting(STR_METHOD_NAME);
        return WEB3StringTypeUtility.formatNumber(l_dblAmount);     
    }
    
    /**
     * (get�˗��l�R�[�h)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A�˗��l�R�[�h���擾����B<BR>
     * <BR>
     * ���R�[�h�F�@@���׍s[����.�s�ԍ�] <BR>
     * <BR>
     * �P�j��s�ʏ���<BR> 
     * <BR>
     * �@@�P-�P�j����.��s�R�[�h = �u0149(�É�)�v�̏ꍇ<BR> 
     * �@@�P-�P-�P�j���R�[�h��133�`139���ځisubstring(132, 139)�j��ԋp����B<BR> 
     * <BR>
     * �@@�P-�Q�j����.��s�R�[�h = �u0149(�É�)�v�ȊO�̏ꍇ<BR> 
     * �@@�P-�Q-�P�j���R�[�h��43�`49���ځisubstring(42, 49)�j��ԋp����B<BR> 
     * <BR>
     * �@@�������A�I�[���X�y�[�X�̏ꍇ�A"0000000"��ԋp����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@param l_strBankCode - (��s�R�[�h)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3C420337
     */
    public String getPersonCode(int l_intLineNumber, String l_strFinancialInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPersonCode(int l_intLineNumber, " + 
            "String l_strBankCode) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strPersonCode = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        // �P-�P�j����.��s�R�[�h = �u0149(�É�)�v�̏ꍇ 
        // �P-�P-�P�j���R�[�h��133�`139���ځisubstring(132, 139)�j��ԋp����B 
        if (WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strFinancialInstitutionCode))
        {
            l_strPersonCode = l_strCsvLineValue.substring(132, 139);
        }
        // �P-�Q�j����.��s�R�[�h = �u0149(�É�)�v�ȊO�̏ꍇ 
        // �P-�Q-�P�j���R�[�h��43�`49���ځisubstring(42, 49)�j��ԋp����B 
        else
        {
            l_strPersonCode = l_strCsvLineValue.substring(42, 49);
        }

      //�I�[���X�y�[�X�̏ꍇ�A"0000000"���Z�b�g����B
      if ("".equals(l_strPersonCode.trim()))
      {
          l_strPersonCode = WEB3AdminFXPersonCodeDef.PERSON_CODE_0000000;
      }

        log.exiting(STR_METHOD_NAME);
        return l_strPersonCode;
    }
   
    /**
     * (get�˗��l��)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A�˗��l�����擾����B<BR> 
     * <BR> 
     * ���R�[�h�F�@@���׍s[����.�s�ԍ�] <BR> 
     * <BR> 
     * �P�j��s�ʏ���<BR>
     * <BR>
     * �P-�P�j����.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ<BR>
     *    �E���R�[�h��50�`97���ځisubstring(49, 97)�j���擾����B<BR>
     * <BR>
     * �P-�Q�j����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ<BR>
     *    �E���R�[�h��61�`97���ځisubstring(60, 97)�j���擾����B<BR>
     *    ���݂��ق̏ꍇ�A�˗��l���ɂ́u�˗��l�R�[�h(10)+�X�y�[�X(1)+�˗��l��(37)�v�ƂȂ��Ă����<BR>
     * <BR>
     * �Q�j��s���ʏ���<BR>
     * <BR>
     * �Q-�P�j�Œ蒷�ׁ̈A�擾���������S�Ăɕ����������Ă���̂ŁA�E�̌��ɂ��Ă���]���ȋ󔒂���菜���B<BR>
     * <BR>
     * �Q-�Q�j�P-�Q�̏����Ŏ擾����������S�p�ϊ����A�ԋp����B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@param l_strBankCode - (��s�R�[�h) 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C46FA0353
     */
    public String getPersonName(int l_intLineNumber, String l_strBankCode) 
         throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getPersonName(int, String) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strPersonName = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
                
        //�P�j��s�ʏ��� 

        //�P-�P�j����.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ 
        //   �E���R�[�h��50�`97���ځisubstring(49, 97)�j���擾����B
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_strBankCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
        {
            l_strPersonName = l_strCsvLineValue.substring(49, 97);
        }
        
        //�P-�Q�j����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ 
        //   �E���R�[�h��61�`97���ځisubstring(60, 97)�j���擾����B 
        //   ���݂��ق̏ꍇ�A�˗��l���ɂ́u�˗��l�R�[�h(10)+�X�y�[�X(1)+�˗��l��(37)�v�ƂȂ��Ă����
        else if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            l_strPersonName = l_strCsvLineValue.substring(60, 97);
        }       

        //�Q�j��s���ʏ��� 

        //�Q-�P�j�Œ蒷�ׁ̈A�擾���������S�Ăɕ����������Ă���̂ŁA�E�̌��ɂ��Ă���]���ȋ󔒂���菜���B
        l_strPersonName = l_strPersonName.trim();
        
        //�Q-�Q�j�P-�Q�̏����Ŏ擾����������S�p�ϊ����A�ԋp����B
        String l_strPersonNameRen = WEB3StringTypeUtility.toWbyteKana(l_strPersonName);
               
        log.exiting(STR_METHOD_NAME);
        return l_strPersonNameRen;
    }
   
    /**
     * (get�d����s��)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A�d����s�����擾����B<BR>
     * <BR>
     * [����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�j���R�[�h��98�`112���ځisubstring(97, 112)�j���擾����B<BR>
     * <BR>
     * �@@�Q�j�Œ蒷�ׁ̈A15���S�Ăɕ����������Ă���̂ŁA<BR>
     * �@@�@@�@@�E�̌��ɂ��Ă���]���ȋ󔒂���菜���B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3D0F0063
     */
    public String getDeliveredBankName(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDeliveredBankName(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strDeliveredBankName = null;

        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        l_strDeliveredBankName = l_strCsvLineValue.substring(97, 112);
               
        log.exiting(STR_METHOD_NAME);
        return l_strDeliveredBankName.trim();
    }
   
    /**
     * (get�d���X��)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���A�d���X�����擾����B<BR>
     * <BR>
     * [����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�j���R�[�h��113�`127���ځisubstring(112, 127)�j���擾����B<BR>
     * <BR>
     * �@@�Q�j�Œ蒷�ׁ̈A15���S�Ăɕ����������Ă���̂ŁA<BR>
     * �@@�@@�@@�E�̌��ɂ��Ă���]���ȋ󔒂���菜���B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3DC903A9
     */
    public String getDeliveredBranchName(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDeliveredBranchName(int l_intLineNumber) ";
        log.entering(STR_METHOD_NAME );
       
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strDeliveredBranchName = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        l_strDeliveredBranchName = l_strCsvLineValue.substring(112, 127);
                
        log.exiting(STR_METHOD_NAME);
        return l_strDeliveredBranchName.trim();
    }
   
    /**
     * (getEDI���)<BR>
     * �s�ԍ��ɑΉ����郌�R�[�h���擾���AEDI�����擾����B <BR>
     * <BR>
     * ���R�[�h�F�@@���׍s[����.�s�ԍ�] <BR>
     * <BR>
     * �P�j��s�ʏ��� <BR>
     * <BR>
     * �@@�P-�P�j����.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@���R�[�h��129�`148���ځisubstring(128, 148)�j���擾����B <BR>
     * <BR>
     * �@@�P-�Q�j����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@���R�[�h��153�`172���ځisubstring(152, 172)�j���擾����B <BR>
     * <BR>
     * �Q�j��s���ʏ��� <BR>
     * �@@�Q-�P�j�Œ蒷�ׁ̈A20���S�Ăɕ����������Ă���̂ŁA�P�j�Ŏ擾�����f�[�^��<BR> 
     * �@@�@@�@@�@@�E�̌��ɂ��Ă���]���ȋ󔒂���菜���A�ԋp����B<BR>
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)
     * @@param l_strBankCode - (��s�R�[�h)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 445C3E0D0109
     */
    public String getEDIInfo(int l_intLineNumber, String l_strBankCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getEDIInfo(int, String) ";
        log.entering(STR_METHOD_NAME);
                    
        AdministratorUploadTempRow  administratorUploadTempRow = null;
        String l_strCsvLineValue = null;
        String l_strEDIInfo = null;
        
        try
        {
            administratorUploadTempRow = 
                AdministratorUploadTempDao.findRowByAdministratorUploadIdLineNumber(
                    super.administratorUploadId, l_intLineNumber);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }               
        
        if (administratorUploadTempRow == null)
        {
            log.debug("(�Ǘ��ҋ���)����۰�������؂̃��R�[�h���擾���Ȃ�");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
      
        l_strCsvLineValue = administratorUploadTempRow.getCsvLineValue();
        
        //�P�j��s�ʏ��� 

        //�P-�P�j����.��s�R�[�h = �u0005(�O�H����UFJ)�v�܂��́u0149(�É�)�v�̏ꍇ 
        //     ���R�[�h��129�`148���ځisubstring(128, 148)�j���擾����B
        if (WEB3AdminFXBankCodeDef.TOKYO_MITSUBISHI_BANK.equals(l_strBankCode) || 
            WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
        {
            l_strEDIInfo = l_strCsvLineValue.substring(128, 148);
        }
        
        //�P-�Q�j����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ 
        //�@@�@@�@@���R�[�h��153�`172���ځisubstring(152, 172)�j���擾����B 
        else if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            l_strEDIInfo = l_strCsvLineValue.substring(152, 172);
        }       

        //�Q�j��s���ʏ��� 
        //�Q-�P�j�Œ蒷�ׁ̈A20���S�Ăɕ����������Ă���̂ŁA�P�j�Ŏ擾�����f�[�^�� 
        //�@@�@@�@@�E�̌��ɂ��Ă���]���ȋ󔒂���菜���A�ԋp����B                    
        log.exiting(STR_METHOD_NAME);
        return l_strEDIInfo.trim();
    }

    /**
     * (get�A�b�v���[�h�t�@@�C��ID)
     * �o�[�`�������������A�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>  
     *<BR>
     *���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����<BR>
     *@@return String
     */
    public String getUploadFileId()
    {
        final String STR_METHOD_NAME = " getUploadFileId() ";
        log.entering(STR_METHOD_NAME );
        
        log.exiting(STR_METHOD_NAME);
        return this.UPLOAD_FILEID;      
    }

    /**
     * (get�����^�C�v)<BR>
     * ProductTypeEnum.���̑� ��ԋp����B
     * @@return ProductTypeEnum
     */
    public ProductTypeEnum getProductType() 
    {
        final String STR_METHOD_NAME = " getProductType() ";
        log.entering(STR_METHOD_NAME );
        
        log.exiting(STR_METHOD_NAME);
        return ProductTypeEnum.OTHER;
    }

    /**
     * (setDataListFrom�A�b�v���[�hTemp)<BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u���̎w��A�b�v���[�h�h�c�̃f�[�^���v���p�e�B�ɂ��Z�b�g����B <BR>
     *<BR>
     * �P�j�@@�e���|�����e�[�u���Ǎ� <BR>
     *  �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����ȉ��̏����Ō������A<BR> 
     *  �s�ԍ����Ɂi�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[]���擾����B<BR> 
     *<BR>
     *   �A�b�v���[�h�h�c = ����.�A�b�v���[�h�h�c <BR>
     *<BR>   
     *   ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params <BR>
     *   DDL�ɂĎ�����������s�I�u�W�F�N�g <BR>
     *<BR>   
     *<BR>
     *  �Q�j�@@���׍s�̃Z�b�g <BR>
     *   �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[]�̗v�f�ɂ��āAthis.���׍s�ɒǉ�����B<BR>�@@ 
     *<BR>   
     *  �R�j�@@�A�b�v���[�h�h�c�Z�b�g <BR>
     *  �@@this.�A�b�v���[�h�h�c�Ɉ���.�A�b�v���[�h�h�c���Z�b�g����B<BR> 
     *@@param l_lngUploadId - (�A�b�v���[�h�h�c)
     *@@throws WEB3SystemLayerException 
     */
    public void setDataFromUploadTemp(long l_lngUploadId) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords;        
        String l_strOrderBy = "line_number ";
         
        //�P�j�@@�e���|�����e�[�u���Ǎ�
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" administrator_upload_id = ? ");    
             
            Object[] l_objAdministratorUploadTempWhere = { 
                new Long(l_lngUploadId)
                }; 

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objAdministratorUploadTempWhere);
        }
        catch (DataException l_dex)
        {
            log.error("__DataException__", l_dex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }

        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���R�[�h���擾�ł��܂���ł���");
        }

        AdministratorUploadTempParams l_administratorUploadTempParams;
        
        //�Q�j�@@���׍s�̃Z�b�g
        int l_intSize = l_lisRecords.size();
        for (int i = 0; i < l_intSize; i++)
        {
            AdministratorUploadTempRow l_administratorUploadTempRow = (AdministratorUploadTempRow)l_lisRecords.get(i);
            l_administratorUploadTempParams = new AdministratorUploadTempParams(l_administratorUploadTempRow);
            this.rows.add(l_administratorUploadTempParams.getCsvLineValue());
        }
         
        //�R�j�@@�A�b�v���[�h�h�c�Z�b�g
        //this.�A�b�v���[�h�h�c�Ɉ���.�A�b�v���[�h�h�c���Z�b�g����
        this.administratorUploadId = l_lngUploadId;
        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (save�A�b�v���[�h�t�@@�C��)<BR>
     * �A�b�v���[�h�t�@@�C���s���u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u���ɍX�V����B<BR>  
     * <BR>
     * ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[].length�ɂāA�t�@@�C���s���iindex�j���擾����B<BR>  
     * �擾�������N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[]�e�v�f�ɂ��āA���̒ʂ� <BR> 
     *�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u���ɍs��}���iinsert�j����B<BR> 
     * <BR>
     * �@@�A�b�v���[�h�h�c = this.get�A�b�v���[�h�h�c() <BR> 
     * �@@�s�ԍ� = index  <BR>
     * �@@CSV�s = ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[index] <BR> 
     * �@@�X�V���� = TradingSystem.getSystemTimestamp()<BR>  
     * @@param  l_strRequestDataUploadFiles - (���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[])
     * @@throws WEB3BaseException 
     */
    public void saveUploadFile(String[] l_strRequestDataUploadFiles) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveUploadFile(String[])";
        log.entering(STR_METHOD_NAME);
        
        //���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[].length�ɂāA�t�@@�C���s���iindex�j���擾����B
        int l_intFileLength = l_strRequestDataUploadFiles.length;
        
        int l_intIndex = -1;
        
        AdministratorUploadTempParams l_uploadTempParams = new AdministratorUploadTempParams();
        for(int i = 0; i < l_intFileLength; i++)
        {
            if(l_strRequestDataUploadFiles[i] == null || "".equals(l_strRequestDataUploadFiles[i]))
            {
                continue;
            }

            //�A�b�v���[�h�h�c = this.get�A�b�v���[�h�h�c()
            l_uploadTempParams.setAdministratorUploadId(this.getAdministratorUploadId());
            
            //CSV�s = ���N�G�X�g�f�[�^.�A�b�v���[�h�t�@@�C��[index]
            l_uploadTempParams.setCsvLineValue(l_strRequestDataUploadFiles[i]);
            
            l_intIndex ++;
            
            //�s�ԍ� = index
            l_uploadTempParams.setLineNumber(l_intIndex);
            
            //�X�V���� = TradingSystem.getSystemTimestamp()
            l_uploadTempParams.setUpdateTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            try
            {
                //�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u���ɍX�V����B
                WEB3DataAccessUtility.insertRow(l_uploadTempParams);
            }
            catch(DataException l_dex)
            {
                log.error("__DataException__", l_dex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dex.getMessage(),
                    l_dex);
            }
        }
         
        log.exiting(STR_METHOD_NAME);
    }
     
    /**
     * (get���׍s)<BR>
     * �e�N���X�̑����@@���׍s�FStirng[]���擾����ׂ̃��\�b�h <BR>
     * <BR>
     *  ��this.���׍s���擾���A�ԋp����B<BR>
     */
    public String[] getRows()
    {
        final String STR_METHOD_NAME = "getRows()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strRows = new String[rows.size()];
        rows.toArray(l_strRows);

        log.exiting(STR_METHOD_NAME);
        return l_strRows;
    }
    
    /**
     * (save�A�b�v���[�h���~)<BR>
     * �Y���A�b�v���[�h�s�ɃA�b�v���[�h���~���X�V����B <BR>
     *�i�����̃f�[�^���f��CSV.save�A�b�v���[�h���~()�̃I�[�o�[���C�h�j <BR>
     * <BR>
     * this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ��� <BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B <BR>
     * <BR>
     *�@@�A�b�v���[�h�I������ = System.currentTimeMillis() <BR>
     *�@@�A�b�v���[�h���� = 0 <BR>
     *�@@���l�P = "���~" <BR>
     *<BR>
     * ���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B<BR>
     * @@throws WEB3SystemLayerException 
     */
    public void saveUploadStop() throws WEB3SystemLayerException
    {
        
        final String STR_METHOD_NAME = "saveUploadStop()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            // this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
            // �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            
            AdministratorUploadRow l_administratorUploadRow = (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());             
        
            if(l_administratorUploadRow == null)
            {
                //���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B
                log.exiting(STR_METHOD_NAME);
                return;
            }
           
            AdministratorUploadParams l_uploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);
            
            //�A�b�v���[�h�I������ = System.currentTimeMillis()
            l_uploadParams.setUploadEndTimestamp(new Timestamp(System.currentTimeMillis()));
            
            //�A�b�v���[�h���� = 0
            l_uploadParams.setUploadCount(0);
            
            //���l�P = "���~"
            l_uploadParams.setNote1(WEB3AdminFXUploadNoteOneDef.UPLOAD_TERMINATE);
     
        
            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            Processors.getDefaultProcessor().doUpdateQuery(l_uploadParams);
        }
        catch (DataException l_ex)
        {
            log.debug("__DataException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
          
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is�ǂݔ�΂����R�[�h)<BR>
     *���R�[�h�̃`�F�b�N���s���A�����Ώۂ����ʂ���B<BR> 
     *<BR>
     *���R�[�h�F�@@����.���׍s[����.�s�ԍ�] <BR>
     *<BR>
     *�P�j��s���ʃ`�F�b�N <BR>
     *�@@�@@�E���L1-�@@��1-�A�̏����ɍ��v�����ꍇ�A"false"��ԋp����B <BR>
     *�@@�@@�@@�@@1-�@@�f�[�^�敪�i���R�[�h1�`1���ځisubstring(0, 1)�j�j���A<BR>
     *�@@�@@�@@�@@�@@"2�F�f�[�^�E���R�[�h"�B <BR>
     *�@@�@@�@@�@@1-�A����敪�i���R�[�h128���ځisubstring(127, 128)�j�j���A<BR>
     *�@@�@@�@@�@@�@@"1�F�����"�B <BR>
     *<BR>
     *�Q�j��s�ʃ`�F�b�N <BR>
     *�@@�Q-1�j ����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ <BR>
     *<BR>
     *�@@�Q-1�j-1 �I�����C�������`�F�b�N <BR>
     *�@@�@@�E���L2-�@@��2-�A�̏����ɍ��v�����ꍇ�A"false"��ԋp����B <BR>
     *�@@�@@�@@�@@2-�@@�f�[�^�敪�i���R�[�h1�`1���ځisubstring(0, 1)�j�j���A<BR>
     *�@@�@@�@@�@@�@@"2�F�f�[�^�E���R�[�h"�B<BR> 
     *�@@�@@�@@�@@2-�A�U���˗��l�R�[�h�i���R�[�h40�`49���ځisubstring(39, 49)�j�j���A<BR>
     *�@@�@@�@@�@@�@@"ALL�X�y�[�X"�B <BR>
     *<BR>
     *�@@�Q-�Q�j ����.��s�R�[�h = �u0149(�É�)�v�̏ꍇ <BR>
     *<BR>
     *�@@�Q-�Q�j-1 �˗��l�R�[�h�`�F�b�N<BR>
     *�@@�@@�E���L2-�B��2-�C�̏����ɍ��v�����ꍇ�A"false"��ԋp����B<BR>
     *�@@�@@�@@�@@2-�B�f�[�^�敪�i���R�[�h1�`1���ځisubstring(0, 1)�j�j���A"2�F�f�[�^�E���R�[�h"�B<BR>
     *�@@�@@�@@�@@2-�C�˗��l�R�[�h�i���R�[�h133�`139���ځisubstring(132, 139)�j���A"ALL�X�y�[�X"�@@���́@@"ALL 0" �B<BR>
     *<BR>
     *�@@�Q-�R�j����.��s�R�[�h = �i�u0001(�݂���)�v�A���� �u0149(�É�)�v�j�ȊO�̏ꍇ <BR>
     *<BR>
     *�@@�Q-�R�j-1 �`�F�b�N���� <BR>
     *<BR>
     *<BR>
     *�R�j��L��s���ʃ`�F�b�N�A��s�ʃ`�F�b�N�ɓ��Ă͂܂�Ȃ��ꍇ�Atrue��ԋp����B<BR>
     *<BR>
     *��"false"�������ꍇ�ɂ́A[this.�g�[�^������]��1�����C���N�������g����B<BR> 
     *<BR>  
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �o�[�`�����A�b�v���[�h�t�@@�C���̍s�ԍ�<BR>
     * @@param l_strBankCode - (��s�R�[�h)<BR>
     * ��s�R�[�h<BR>
     * @@param l_strRowCounts - (���׍s)<BR>
     * ���׍s�i�A�b�v���[�h�t�@@�C���j<BR>
     * @@throws WEB3BaseException 
     */
    public boolean isSkipReadRecord(
        int l_intLineNumber, 
        String l_strBankCode, 
        String[] l_strRowCounts) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isSkipReadRecord(int, String, String[])";
        log.entering(STR_METHOD_NAME);
                
        if (l_strRowCounts == null)
        {
            log.debug("�p�����[�^�l��NULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //���R�[�h�F�@@����.���׍s[����.�s�ԍ�]
        String l_strRecordData = l_strRowCounts[l_intLineNumber];
     
        //�P�j��s���ʃ`�F�b�N
        //�E���L1-�@@��1-�A�̏����ɍ��v�����ꍇ�A"false"��ԋp����B 
        //�@@�@@�@@�@@1-�@@�f�[�^�敪�i���R�[�h1�`1���ځisubstring(0, 1)�j�j���A2�F�f�[�^�E���R�[�h"�B 
        //�@@�@@�@@�@@1-�A����敪�i���R�[�h128���ځisubstring(127, 128)�j�j���A"1�F�����"�B
        if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strRecordData.substring(0, 1)) 
            && WEB3CancelDivDef.CANCEL.equals(l_strRecordData.substring(127, 128)))
        {
            this.totalCount ++;
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //�Q�j��s�ʃ`�F�b�N 
        //�@@�Q-1�j ����.��s�R�[�h = �u0001(�݂���)�v�̏ꍇ 
        //�@@�Q-1�j-1 �I�����C�������`�F�b�N 
        //�@@�@@�E���L2-�@@��2-�A�̏����ɍ��v�����ꍇ�A"false"��ԋp����B
        //�@@�@@�@@�@@2-�@@�f�[�^�敪�i���R�[�h1�`1���ځisubstring(0, 1)�j�j���A
        //       "2�F�f�[�^�E���R�[�h"�B 
        //�@@�@@�@@�@@2-�A�U���˗��l�R�[�h�i���R�[�h40�`49���ځisubstring(39, 49)�j�j���A
        //       "ALL�X�y�[�X"�B
        if (WEB3AdminFXBankCodeDef.MIZUHO.equals(l_strBankCode))
        {
            if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strRecordData.substring(0, 1))
                && "".equals(l_strRecordData.substring(39, 49).trim()))
            {
            
                this.totalCount ++;
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //�Q-�Q�j ����.��s�R�[�h = �u0149(�É�)�v�̏ꍇ
        else if (WEB3AdminFXBankCodeDef.SHIZUOKA_BANK.equals(l_strBankCode))
        {
            //�Q-�Q�j-1 �˗��l�R�[�h�`�F�b�N
            //���L2-�B��2-�C�̏����ɍ��v�����ꍇ�A"false"��ԋp����B
            //2-�B�f�[�^�敪�i���R�[�h1�`1���ځisubstring(0, 1)�j�j���A"2�F�f�[�^�E���R�[�h"�B
            //2-�C�˗��l�R�[�h�i���R�[�h133�`139���ځisubstring(132, 139)�j���A"ALL�X�y�[�X"�@@���́@@"ALL 0" �B
            String l_strPerson = l_strRecordData.substring(132, 139);

            if (WEB3AdminFXDataDivDef.DATA_RECORD_COUNT.equals(l_strRecordData.substring(0, 1)) &&
                ("".equals(l_strPerson.trim()) || "".equals(l_strPerson.replaceAll("0",""))))
            {
                log.debug("�˗��l�R�[�h��ALL �X�y�[�X����ALL 0");
                this.totalCount ++;
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }

        //�@@�R�j��L��s���ʃ`�F�b�N�A��s�ʃ`�F�b�N�ɓ��Ă͂܂�Ȃ��ꍇ�Atrue��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
