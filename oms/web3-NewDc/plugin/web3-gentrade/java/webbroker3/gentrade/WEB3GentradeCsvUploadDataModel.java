head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeCsvUploadDataModel.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : CSV�t�@@�C���f�[�^���f��(WEB3GentradeCsvUploadDataModel.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/07/28 �� ��(���u) �V�K�쐬
Revesion History    : 2005/07/08 �� ��(���u) saveAllUploadStop()��ǉ�
*/

package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;             
import com.fitechlabs.xtrade.kernel.data.DataQueryException;              
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AdministratorUploadDao;
import webbroker3.gentrade.data.AdministratorUploadParams;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.gentrade.data.AdministratorUploadTempParams;
import webbroker3.gentrade.data.AdministratorUploadTempRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �iCsvUploadDataModel�j<BR>
 * CSV�t�@@�C���f�[�^���f���N���X�B<BR>
 * CSV�A�b�v���[�h�ɂĎg�p����B<BR>
 * <BR>
 * �g�p�O�����<BR>
 * �@@�E�T�[�r�X�C���^�Z�v�^�ɂāA������ԊǗ��I�u�W�F�N�g��ThreadLocal <BR>
 *     �Ɋi�[���Ă��邱�ƁB<BR>
 * �@@�E�t�@@�C���t�H�[�}�b�g�͈ȉ��̒ʂ�Ƃ���B<BR>
 * <BR>
 * [CSV�t�@@�C���t�H�[�}�b�g�ɂ���] <BR>
 * 1�s�ځiindex=0�j�̓L�[�w�b�_�ƔF������B<BR>
 * 3�s�ځiindex=2�j�ȍ~�𖾍׍s�ƔF������B<BR>
 * <BR>
 * --- CSV upload file sample---------------<BR>
 * 2004/06/21 16:00:03,86010,(��)��a�،��O���[�v�{��<BR>
 * 625,2512211,���c�@@�N��,5000<BR>
 * 624,2412339,�X�c�@@����,5500<BR>
 * 610,2110991,�V���@@�M��,2000<BR>
 * 610,2121400,�H��@@�P��,2500<BR>
 * --- CSV upload file sample---------------<BR>
 * <BR>
 */
public abstract class WEB3GentradeCsvUploadDataModel
    extends WEB3GentradeCsvDataModel
{

    /**
     * ���O�o�̓I�u�W�F�N�g�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeCsvUploadDataModel.class);
        
    /**
     * (�A�b�v���[�h�h�c) <BR>
     * <BR>
     * ���@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u����PK<BR>
     */
    protected long administratorUploadId;

    /**
     * (is�J�����w�b�_�s�o��) <BR>
     * false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40F5048102D7
     */
    public boolean isColumnHeadOutput()
    {
        //false��ԋp����B
        return false;
    }

    /**
     * (get�A�b�v���[�h�t�@@�C���h�c) <BR>
     * �A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c��<BR>
     * �i�[���镶����<BR>
     * @@return java.lang.String
     * @@roseuid 40F2030100DC
     */
    public abstract String getUploadFileId();

    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v��ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 40F38DD7006C
     */
    public abstract ProductTypeEnum getProductType();
    
    /**
     * (get�،���ЃR�[�h) <BR>
     * ThreadLocal���،���ЃR�[�h���擾����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * �@@�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH <BR>
     * �@@�@@)<BR>
     * �ɂāA������ԃR���e�L�X�g���擾����B <BR>
     * <BR>
     * ������ԃR���e�L�X�g.get�،���ЃR�[�h()��ԋp����B<BR>
     * @@return java.lang.String
     * @@roseuid 40F3CEE60190
     */
    public String getInstitutionCode()
    {
        final String STR_METHOD_NAME = "getInstitutionCode()";
        log.entering(STR_METHOD_NAME);
        
        //������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //������ԃR���e�L�X�g.get�،���ЃR�[�h()��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_clendarContext.getInstitutionCode();
    }

    /**
     * (get���X�R�[�h) <BR>
     * ThreadLocal��蕔�X�R�[�h���擾����B<BR>
     * <BR>
     * ThreadLocalSystemAttributesRegistry.getAttribute( <BR>
     * �@@�@@������ԊǗ�.TRADING_CAL_CONTEXT_PATH  <BR>
     * �@@�@@) <BR>
     * �ɂāA������ԃR���e�L�X�g���擾����B <BR>
     * <BR>
     * ������ԃR���e�L�X�g.get���X�R�[�h()��ԋp����B<BR>
     * @@return java.lang.String
     * @@roseuid 40F3CF6A0123
     */
    public String getBranchCode()
    {
        final String STR_METHOD_NAME = "getBranchCode()";
        log.entering(STR_METHOD_NAME);
        
        //������ԃR���e�L�X�g�̎擾
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
        
        //������ԃR���e�L�X�g.get���X�R�[�h()��ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_clendarContext.getBranchCode();
    }

    /**
     * (validate�����A�b�v���[�h) <BR>
     * ���v���Z�X���N�����łȂ������`�F�b�N����B<BR>
     * �i�،���ЁC���X�C�����^�C�v�ɂē����A�b�v���[�h���֎~����j<BR>
     * <BR>
     * �ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v����������B<BR>
     * <BR>
     * [����]<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�،���ЃR�[�h = <BR>
     * this.get�،���ЃR�[�h()�@@And <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.���X�R�[�h = this.get���X�R�[�h() And <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�����^�C�v = this.get�����^�C�v() And <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�I������ = null <BR>
     * <BR>
     * ���@@�i�A�b�v���[�h�h�c == null�j�̏ꍇ <BR>
     * �@@�|�s���擾�ł���΁A���̃A�b�v���[�h�v���Z�X���N�����ł���� <BR>
     * ���f���A��O���X���[����B<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80024<BR>
     * <BR>
     * ���@@�i�A�b�v���[�h�h�c != null�j�̏ꍇ <BR>
     * �@@�|�ʃv���Z�X�̍s(*1)���擾�ł���΁A<BR>
     *      ���̃A�b�v���[�h�v���Z�X���N�����ł���Ɣ��f���A<BR>
     *      ��O���X���[����B<BR>
     * class    : WEB3SystemLayerException<BR>
     * tag      : SYSTEM_ERROR_80024<BR>
     * <BR>
     * �@@�@@(*1) �ʃv���Z�X�̍s�̔���<BR>
     * �@@�@@�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�h�c != 
     * �A�b�v���[�h�h�c<BR>
     * @@param l_administratorUploadId - �A�b�v���[�h�h�c<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F390E702ED
     */
    public void validateSameTimeUpload(Long l_administratorUploadId)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateSameTimeUpload(Long)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords;
        String l_strInstitutionCode = null;
        String l_strBranchCode = null;
        ProductTypeEnum l_productTypeEnum = null;
                    
        try
        {
            l_strInstitutionCode = this.getInstitutionCode();
            l_strBranchCode = this.getBranchCode();
            l_productTypeEnum = this.getProductType();

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //�،���ЃR�[�h
            l_sbWhere.append(" and branch_code = ? "); //���XID
            l_sbWhere.append(" and product_type = ? "); //�����^�C�v
            l_sbWhere.append(" and upload_end_timestamp is null "); //�A�b�v���[�h�I������

            Object[] l_objAdministratorUploadWhere =
                { l_strInstitutionCode, //�،���ЃR�[�h
                l_strBranchCode, //���XID
                l_productTypeEnum //�����^�C�v
            };

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        //�i�A�b�v���[�h�h�c == null�j�̏ꍇ
        if(l_administratorUploadId == null)
        {
            if (l_lisRecords.size() > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01969,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        else//�i�A�b�v���[�h�h�c != null�j�̏ꍇ
        {
            if(l_lisRecords.size() == 0)
            {
                return;
            }
            if (l_lisRecords.size() > 1)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�������F ���� > 1");
            }
            //get �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�h�c
            AdministratorUploadRow l_row = (AdministratorUploadRow)l_lisRecords.get(0);
            long l_lngAdministratorUploadId = l_row.getAdministratorUploadId();
            //�ʃv���Z�X�̍s�̔���
            if (l_administratorUploadId.longValue() != l_lngAdministratorUploadId)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01969,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
        }
   
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (save�A�b�v���[�h�J�n) <BR>
     * �A�b�v���[�h�e�[�u���ɐV�K�s��}�����A�A�b�v���[�h�h�c��ԋp����B<BR>
     * <BR>
     * �P�j�@@�s�I�u�W�F�N�g����<BR>
     * �@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��Params�𐶐����A<BR>
     *   NotNull���ڈȊO�̊e���ڂ�Null�ŏ���������B<BR>
     * <BR>
     * �@@���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��Params�N���X��<BR>
     *   DDL��莩����������B<BR>
     * <BR>
     * �Q�j�@@�ȉ��̒ʂ�A�s�I�u�W�F�N�g�ɒl���Z�b�g����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�h�c = �i�V�K�̔ԁj(*1)<BR>
     * �@@�،���ЃR�[�h = this.get�،���ЃR�[�h()<BR>
     * �@@���X�R�[�h = this.get���X�R�[�h()<BR>
     * �@@�����^�C�v = this.get�����^�C�v()<BR>
     * �@@�A�b�v���[�h�t�@@�C���h�c = this.get�A�b�v���[�h�t�@@�C���h�c()<BR>
     * �@@�A�b�v���[�h�J�n���� = GtlUtils.getSystemTimestamp()<BR>
     * �@@�X�V�҃R�[�h = �X�V�҃R�[�h<BR>
     * �@@�f�[�^�L�[ = ����.�f�[�^�L�[<BR>
     * �@@���l�P = ����.���l�P<BR>
     * �@@���l�Q = ����.���l�Q<BR>
     * �@@���l�R = ����.���l�R<BR>
     * <BR>
     * �@@(*1)�@@�A�b�v���[�h�h�c�V�K�̔�<BR>
     * �@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��DAO.newPkValue()���\�b�h<BR>
     *   �ɂĎ擾����B<BR>
     * �@@���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��DAO�N���X��<BR>
     *   DDL��莩����������B<BR>
     * <BR>
     * �R�j�@@TransactionCallback����<BR>
     * �@@�A�b�v���[�hTransactionCallback�N���X�𐶐����A<BR>
     *   �Q�j�ō쐬�����s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �S�j�@@DB-insert<BR>
     * �@@QueryProcessor.doTransaction()�ɂ�DB-insert�����s����B<BR>
     * <BR>
     * �@@[doTransaction()�Ɏw�肷�����]<BR>
     * �@@�g�����U�N�V���������F�@@TX_CREATE_NEW <BR>
     * �@@�g�����U�N�V�����R�[���o�b�N�F�@@�S�j�Ő�������TransactionCallback<BR>
     * <BR>
     * �T�j�@@�A�b�v���[�h�h�c�Z�b�g<BR>
     * �@@this.�A�b�v���[�h�h�c�ɐV�K�̔Ԃ����A�b�v���[�h�h�c(*1)���Z�b�g����B<BR>
     * @@param l_lngUploadKey - �f�[�^�L�[ <BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.�f�[�^�L�[�ɍX�V������e�B<BR>
     * 
     * @@param l_strNote1 - ���l�P <BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.���l�P�ɍX�V������e�B<BR>
     * 
     * @@param l_strNote2 - ���l�Q <BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.���l�Q�ɍX�V������e�B<BR>
     * 
     * @@param l_strNote3 - ���l�R <BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.���l�R�ɍX�V������e�B<BR>
     * 
     * @@param l_strLastUpdater - �X�V�҃R�[�h <BR>
     * 
     * @@return long
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F204100205
     */
    public long saveUpLoadStart(
        long l_lngUploadKey,
        String l_strNote1,
        String l_strNote2,
        String l_strNote3,
        String l_strLastUpdater)
        throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = "saveUpLoadStart(long,String,String,String,String)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�P�j�@@�s�I�u�W�F�N�g����
            //�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��Params�𐶐����A
            //NotNull���ڈȊO�̊e���ڂ�Null�ŏ���������B
            AdministratorUploadParams l_administratorUploadParms = 
                new AdministratorUploadParams();
            
            //�Q�j�@@�ȉ��̒ʂ�A�s�I�u�W�F�N�g�ɒl���Z�b�g����B
            
            //* �@@�A�b�v���[�h�h�c = �i�V�K�̔ԁj
            long l_lngNewPkValue = AdministratorUploadDao.newPkValue();
            l_administratorUploadParms.setAdministratorUploadId(l_lngNewPkValue);
            
            //* �@@�،���ЃR�[�h = this.get�،���ЃR�[�h()
            l_administratorUploadParms.setInstitutionCode(this.getInstitutionCode());
            
            //* �@@���X�R�[�h = this.get���X�R�[�h()
            l_administratorUploadParms.setBranchCode(this.getBranchCode());
            
            //* �@@�����^�C�v = this.get�����^�C�v()
            l_administratorUploadParms.setProductType(this.getProductType());
            
            //* �@@�A�b�v���[�h�t�@@�C���h�c = this.get�A�b�v���[�h�t�@@�C���h�c()
            l_administratorUploadParms.setUploadFileId(this.getUploadFileId());
            
            //* �@@�A�b�v���[�h�J�n���� = GtlUtils.getSystemTimestamp()
            Timestamp l_tsUploadStartTimestamp = GtlUtils.getSystemTimestamp();
            l_administratorUploadParms.setUploadStartTimestamp(l_tsUploadStartTimestamp);
            
            //* �@@�A�b�v���[�h�I������ = null
            l_administratorUploadParms.setUploadEndTimestamp(null);
            
            //* �@@���b�Z�[�W�R�[�h = null
            l_administratorUploadParms.setMessageCode(null);
            
            //* �@@�A�b�v���[�h���� = null
            l_administratorUploadParms.setUploadCount(null);
            
            //* �@@�X�V�҃R�[�h = �X�V�҃R�[�h
            l_administratorUploadParms.setLastUpdater(l_strLastUpdater);
            
            //* �@@�f�[�^�L�[ = ����.�f�[�^�L�[
            l_administratorUploadParms.setUploadKey(l_lngUploadKey);
            
            //* �@@���l�P = ����.���l�P
            l_administratorUploadParms.setNote1(l_strNote1);
            //* �@@���l�Q = ����.���l�Q
            l_administratorUploadParms.setNote2(l_strNote2);
            //* �@@���l�R = ����.���l�R
            l_administratorUploadParms.setNote3(l_strNote3);
        
            //�R�j�@@TransactionCallback����
            UploadTransactionCallback l_uploadTransactionCallback = 
                new UploadTransactionCallback();
            //�Q�j�ō쐬�����s�I�u�W�F�N�g���v���p�e�B�ɃZ�b�g����B
            l_uploadTransactionCallback.administratorUploadParams = l_administratorUploadParms;
            
            //�S�j�@@DB-insert
            //QueryProcessor.doTransaction()�ɂ�DB-insert�����s����B
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_uploadTransactionCallback
                );
            
            //�T�j�@@�A�b�v���[�h�h�c�Z�b�g
            this.administratorUploadId = l_lngNewPkValue;
            
            //�A�b�v���[�h�h�c��ԋp����B
            log.exiting(STR_METHOD_NAME);
            return administratorUploadId;
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (save�A�b�v���[�h�I��) <BR>
     * �w��̃A�b�v���[�h�s�ɃA�b�v���[�h�I�������X�V����B<BR>
     * <BR>
     * �@@this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���<BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�I������ = GtlUtils.getSystemTimestamp() <BR>
     * �@@�A�b�v���[�h���� = this.get���׍s��() <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F20AFB01A7
     */
    public void saveUploadEnd() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadEnd()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());
            
            if(l_administratorUploadRow == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);

            //�A�b�v���[�h�I������ = GtlUtils.getSystemTimestamp()
            //�A�b�v���[�h���� = this.get���׍s��()
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);
            l_administratorUploadParams.setUploadCount(this.getRowCount());
            
            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (save�A�b�v���[�h�G���[) <BR>
     * �Y���A�b�v���[�h�s�ɃA�b�v���[�h�G���[�����X�V����B<BR>
     * <BR>
     * �@@this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���<BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�I������ = GtlUtils.getSystemTimestamp()<BR>
     * �@@���b�Z�[�W�R�[�h = ErrorInfo.getErrorCode()<BR>
     * �@@�A�b�v���[�h���� = 0<BR>
     * @@param l_errorInfo - �G���[��� <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F4DE0803C2
     */
    public void saveUploadError(ErrorInfo l_errorInfo) throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadError(ErrorInfo)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());
            
            if(l_administratorUploadRow == null)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);
                
            //�A�b�v���[�h�I������ = GtlUtils.getSystemTimestamp()
            //���b�Z�[�W�R�[�h = ErrorInfo.getErrorCode()
            //�A�b�v���[�h���� = 0
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);
            l_administratorUploadParams.setMessageCode(l_errorInfo.getErrorCode());
            l_administratorUploadParams.setUploadCount(0);
            
            UploadErrorTransactionCallback l_uploadErrorTransactionCallback = 
                new UploadErrorTransactionCallback();
            l_uploadErrorTransactionCallback.administratorUploadParams = l_administratorUploadParams;
            //QueryProcessor.doTransaction()�ɂ�DB-insert�����s����B
            QueryProcessor l_queryProcessor =
                Processors.getDefaultProcessor();
            l_queryProcessor.doTransaction(
                QueryProcessor.TX_CREATE_NEW,
                l_uploadErrorTransactionCallback
                );
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (save�A�b�v���[�h���~) <BR>
     * �Y���A�b�v���[�h�s�ɃA�b�v���[�h���~���X�V����B<BR>
     * <BR>
     * �@@this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���<BR>
     * �ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�I������ = GtlUtils.getSystemTimestamp()<BR>
     * �@@�A�b�v���[�h���� = 0<BR>
     * <BR>
     *  ���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F77D980057
     */
    public void saveUploadStop() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadStop()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //this.get�A�b�v���[�h�h�c()�ɊY������s�ɂ���
            //�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B
            AdministratorUploadRow l_administratorUploadRow = 
                (AdministratorUploadRow)
                AdministratorUploadDao.findRowByAdministratorUploadId(this.getAdministratorUploadId());
            
            //�Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B
            if(l_administratorUploadRow == null)
            {
                return;
            }
            
            AdministratorUploadParams l_administratorUploadParams = 
                new AdministratorUploadParams(l_administratorUploadRow);

            //�A�b�v���[�h�I������ = GtlUtils.getSystemTimestamp()
            //�A�b�v���[�h���� = 0
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            l_administratorUploadParams.setUploadEndTimestamp(l_tsSystemTime);
            l_administratorUploadParams.setUploadCount(0);
            
            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_administratorUploadParams);
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (save�A�b�v���[�hTemp) <BR>
     * �A�b�v���[�h�t�@@�C���s��<BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u���ɍX�V����B<BR>
     * <BR>
     * this.getCSV�t�@@�C���s()�ɂāACSV�t�@@�C���s[]���擾����B<BR>
     * �擾����CSV�t�@@�C���s[]�e�v�f�ɂ��āA���̒ʂ�<BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u����<BR>
     * �s��}���iinsert�j����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�h�c = this.get�A�b�v���[�h�h�c()<BR>
     * �@@�s�ԍ� = index<BR>
     * �@@CSV�s = CSV�t�@@�C���s[index]<BR>
     * �@@�X�V���� = TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * this.get���׍s��()��ԋp����B<BR>
     * @@return int
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F216DE00AD
     */
    public int saveUploadTemp() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveUploadTemp()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //�A�b�v���[�h�t�@@�C���s��
            //�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u���ɍX�V����B
            String[] l_csvFileLines = this.getCsvFileLines();
            int l_intLength = l_csvFileLines.length;
            for (int i = 0; i < l_intLength; i++)
            {
                AdministratorUploadTempParams l_administratorUploadTempParms = 
                    new AdministratorUploadTempParams();
                //�A�b�v���[�h�h�c = this.get�A�b�v���[�h�h�c()
                l_administratorUploadTempParms.setAdministratorUploadId(this.getAdministratorUploadId());
                //�s�ԍ� = index
                l_administratorUploadTempParms.setLineNumber(i);
                //CSV�s = CSV�t�@@�C���s[index]
                l_administratorUploadTempParms.setCsvLineValue(l_csvFileLines[i]);
                //�X�V���� = TradingSystem.getSystemTimestamp()
                // Timestamp�ݒ�
                FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                TradingSystem l_tradingSys = l_finApp.getTradingSystem();
                java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
                l_administratorUploadTempParms.setUpdateTimestamp(l_processTime);

                l_queryProcessor.doInsertQuery(l_administratorUploadTempParms);
            }
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME);
        return this.getRowCount();
    }

    /**
     * (delete�A�b�v���[�hTemp) <BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����s���폜����B<BR>
     * <BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�s��<BR>
     *   �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����폜�idelete�j����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�h�c = this.get�A�b�v���[�h�h�c()<BR>
     * <BR>
     *  ���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F77CE10326
     */
    public void deleteUploadTemp() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "deleteUploadTemp()";
        log.entering(STR_METHOD_NAME);
        
        try
        {        
            //�A�b�v���[�h�h�c = this.get�A�b�v���[�h�h�c()
            Long l_lngAdministratorUploadId = new Long(this.getAdministratorUploadId());

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" administrator_upload_id = ? ");    //�A�b�v���[�h�h�c

            Object[] l_objAdministratorUploadTempWhere = { 
                l_lngAdministratorUploadId    //�A�b�v���[�h�h�c
                }; 
            
            //�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����s���폜����B
            //���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_sbWhere.toString(),
                l_objAdministratorUploadTempWhere
            );
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        log.exiting(STR_METHOD_NAME); 
    }

    /**
     * (setDataFrom�A�b�v���[�hTemp) <BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u����<BR>
     * �w��A�b�v���[�h�h�c�̃f�[�^���v���p�e�B�ɂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�e���|�����e�[�u���Ǎ�<BR>
     * �@@�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����ȉ��̏����Ō������A<BR>
     * �s�ԍ����Ɂi�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params[]���擾����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�h�c = ����.�A�b�v���[�h�h�c<BR>
     * <BR>
     * �@@���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����Params<BR>
     * �@@DDL�ɂĎ�����������s�I�u�W�F�N�g<BR>
     * <BR>
     * �Q�j�@@�L�[�w�b�_�s�̃Z�b�g<BR>
     * �@@this.set�L�[�w�b�_()�ɂăL�[�w�b�_���Z�b�g����B<BR>
     * �@@���P�j�œǂݍ��񂾍s�I�u�W�F�N�g�z���index = 0�̗v�f<BR>
     *   ���L�[�w�b�_�s�Ɣ��肷��<BR>
     * <BR>
     * �@@[set�L�[�w�b�_()�Ɏw�肷�����]<BR>
     * �@@�L�[�w�b�_�F�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����<BR>
     *   Params[0].split(��؂蕶��)<BR>
     * <BR>
     * �R�j�@@���׍s�̃Z�b�g<BR>
     * �@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����<BR>
     *   Params[]��2�Ԗځiindex = 1�j�ȍ~�̗v�f�ɂ��āA<BR>
     *   this.add���׍s()�ɂĖ��׍s�ɒǉ�����B�@@<BR>
     * <BR>
     * �@@[add���׍s()�Ɏw�肷�����]<BR>
     * �@@���׍s������F�@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|����<BR>
     *   Params[index].CSV�s<BR>
     * <BR>
     * �S�j�@@�A�b�v���[�h�h�c�Z�b�g<BR>
     * this.�A�b�v���[�h�h�c�Ɉ���.�A�b�v���[�h�h�c���Z�b�g����B<BR>
     * �@@
     * @@param l_lngUploadId - �A�b�v���[�h�h�c<BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F4E9A303C2
     */
    public void setDataFromUploadTemp(long l_lngUploadId)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords;
        Long l_lngUpID = new Long(l_lngUploadId);
        String l_strOrderBy = "line_number ";
        
        //�P�j�@@�e���|�����e�[�u���Ǎ�
        try
        {
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" administrator_upload_id = ? ");    //�A�b�v���[�h�h�c
            
            Object[] l_objAdministratorUploadTempWhere = { 
                l_lngUpID    //�A�b�v���[�h�h�c
                }; 

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                AdministratorUploadTempRow.TYPE,
                l_sbWhere.toString(),
                l_strOrderBy,
                null,
                l_objAdministratorUploadTempWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        if (l_lisRecords.size() < 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����e�[�u�������F ���� < 1");
        }

        AdministratorUploadTempParams l_administratorUploadTempParamsTmp;

        //�Q�j�@@�L�[�w�b�_�s�̃Z�b�g
        l_administratorUploadTempParamsTmp =
            (AdministratorUploadTempParams) l_lisRecords.get(0);
        this.setKeyHeader(
            l_administratorUploadTempParamsTmp.csv_line_value.split(
                WEB3GentradeCsvDataModel.regex));

        //�R�j�@@���׍s�̃Z�b�g
        int l_intSize = l_lisRecords.size();
        for (int i = 1; i < l_intSize; i++)
        {
            l_administratorUploadTempParamsTmp =
                (AdministratorUploadTempParams) l_lisRecords.get(i);
            this.addRow(l_administratorUploadTempParamsTmp.getCsvLineValue());
        }
        
        //�S�j�@@�A�b�v���[�h�h�c�Z�b�g
        //this.�A�b�v���[�h�h�c�Ɉ���.�A�b�v���[�h�h�c���Z�b�g����
        this.administratorUploadId = l_lngUploadId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�A�b�v���[�h����) <BR>
     * ���Y�A�b�v���[�h�t�@@�C���Ɋ֘A����A�b�v���[�h���������ׂĎ擾����B<BR>
     * <BR>
     * �ȉ��̏����Łu�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v���������A<BR>
     * �擾�����s�I�u�W�F�N�g�̔z���ԋp����B<BR>
     * <BR>
     * [����]<BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�،���ЃR�[�h = <BR>
     *      this.get�،���ЃR�[�h() <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c = <BR>
     *      this.get�A�b�v���[�h�t�@@�C���h�c() <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�����^�C�v = this.get�����^�C�v() <BR>
     * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�f�[�^�L�[ = ����.�f�[�^�L�[ <BR>
     *  <BR>
     *  [�擾��]<BR>
     *  �A�b�v���[�h�J�n�����@@�~���i�Fdesc�j<BR>
     * @@param l_lngUploadKey - �f�[�^�L�[ <BR>
     *   �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.�f�[�^�L�[�ɍX�V������e�B<BR>
     * @@return �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s[] <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F214CC01E5
     */
    public AdministratorUploadRow[] getUploadAction(long l_lngUploadKey)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getUploadAction(long)";
        log.entering(STR_METHOD_NAME);

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
            l_sbWhere.append(" and upload_file_id = ? ");              //�A�b�v���[�h�t�@@�C���h�c
            l_sbWhere.append(" and product_type = ? ");            //�����^�C�v
            l_sbWhere.append(" and upload_key = ? ");              //�f�[�^�L�[

            Object[] l_objAdministratorUploadWhere = { 
                l_strInstitutionCode, //�،���ЃR�[�h
                l_strUploadFileId,    //���XID
                l_productTypeEnum,    //���i�^�C�v
                l_lngUpKey            //�f�[�^�L�[
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
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        int l_intSize  = l_lisRecords.size();
        AdministratorUploadRow[] l_administratorUploadRows = 
            new AdministratorUploadRow[l_intSize];    
        for (int i = 0; i < l_intSize; i++)
        {
            l_administratorUploadRows[i] = (AdministratorUploadRow)l_lisRecords.get(i);
        }

        log.exiting(STR_METHOD_NAME);
        return l_administratorUploadRows;
    }

    /**
     * (get�ŐV�A�b�v���[�h����) <BR>
     * ���Y�A�b�v���[�h�t�@@�C���Ɋ֘A���钼�߂̃A�b�v���[�h�������擾����B<BR>
     * <BR>
     * �@@this.get�A�b�v���[�h����()�ɂāA���Y�A�b�v���[�h�t�@@�C����<BR>
     *   �֘A����i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���s�����ׂĎ擾����B<BR>
     * <BR>
     * �@@[get�A�b�v���[�h����()�Ɏw�肷�����]<BR>
     * �@@�f�[�^�L�[�F�@@�����̃f�[�^�L�[<BR>
     * <BR>
     * �@@�擾�����s�̂����A�A�b�v���[�h�J�n������<BR>
     *   ���ݓ���(*1)�̒��߂ł���s��ԋp����B<BR>
     * <BR>
     * (*1) GtlUtils.getSystemTimestamp()<BR>
     * @@param l_lngUploadKey - �f�[�^�L�[ <BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�Ǘ��e�[�u���v.�f�[�^�L�[�ɍX�V������e�B<BR>
     * 
     * @@return �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F2155C0253
     */
    public AdministratorUploadRow getLatestUploadAction(long l_lngUploadKey)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getLatestUploadAction(long)";
        log.entering(STR_METHOD_NAME);
        
        int l_intReturnRowNum = 0;
        
        //this.get�A�b�v���[�h����()�ɂāA���Y�A�b�v���[�h�t�@@�C����
        //�֘A����i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���s�����ׂĎ擾����B
        AdministratorUploadRow[] l_rows = this.getUploadAction(l_lngUploadKey);
        if(l_rows == null || l_rows.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�擾�����s�̂����A�A�b�v���[�h�J�n������
        //���ݓ���(*1)�̒��߂ł���s��ԋp����B(*1) GtlUtils.getSystemTimestamp()
        long l_lngCurrentTimeMillis = GtlUtils.getSystemTimestamp().getTime();
        long[] l_temps = new long[l_rows.length];
        for (int i = 0; i < l_rows.length; i++)
        {
            l_temps[i] = Math.abs(l_rows[i].getUploadStartTimestamp().getTime() - l_lngCurrentTimeMillis);
        }
        
        //�������擾����
        long[] l_orderByAscLongs = this.getOrderByAsc(l_temps);
        
        for (int j = 0; j < l_rows.length; j++)
        {
            if (Math.abs(l_rows[j].getUploadStartTimestamp().getTime() - l_lngCurrentTimeMillis) == l_orderByAscLongs[0])
            {
                l_intReturnRowNum = j;
                break;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_rows[l_intReturnRowNum];
    }

    /**
     * (get�A�b�v���[�h�h�c) <BR>
     * this.�A�b�v���[�h�h�c��ԋp����B<BR>
     * @@return long
     * @@roseuid 40F5333501FD
     */
    public long getAdministratorUploadId()
    {
        //this.�A�b�v���[�h�h�c��ԋp����B
        return this.administratorUploadId;
    }

    /**
     * (getOrderByAsc) <BR>
     * ������ԋp����B<BR>
     * @@return long[]
     * @@roseuid 40F5333501FD
     */
    private long[] getOrderByAsc(long[] l_lngTemp)
    {
        final String STR_METHOD_NAME = "getOrderByAsc(long[])";
        log.entering(STR_METHOD_NAME);

        long[] l_lngOrderTemp = l_lngTemp;

        int l_intJLoop = 0;
        int l_intILoop = 0;
        boolean l_isDone = false;
        long l_lngTp = 0;
        int l_intLength = l_lngOrderTemp.length;
        
        while ((l_intJLoop < l_intLength) && (!l_isDone))
        {
            l_isDone = true;
            int l_intRange = l_intLength - l_intJLoop - 1;
            
            for (l_intILoop = 0; l_intILoop < l_intRange; l_intILoop++)
            {
                if (l_lngOrderTemp[l_intILoop] > l_lngOrderTemp[l_intILoop + 1])
                {
                    l_isDone = false;
                    l_lngTp = l_lngOrderTemp[l_intILoop];
                    l_lngOrderTemp[l_intILoop] = l_lngOrderTemp[l_intILoop + 1];
                    l_lngOrderTemp[l_intILoop + 1] = l_lngTp;
                }
            }
            l_intJLoop++;
        }

        log.exiting(STR_METHOD_NAME);
        
        //������ԋp����B
        return l_lngOrderTemp;
    }


    /**
     * (�A�b�v���[�hTransactionCallback) <BR>
     * <BR>
     * �A�b�v���[�h�e�[�u���ɍs���쐬����TransactionCallback�N���X<BR>
     */
    public class UploadTransactionCallback implements TransactionCallback
    {

        /**
         * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s<BR>
         * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�s�I�u�W�F�N�g<BR>
         * <BR>
         * ���@@�i�Ǘ��ҋ��ʁj�A�b�v���[�hParams�N���X��DDL�ɂĎ�����������B<BR>
         */
        public AdministratorUploadParams administratorUploadParams;

        /**
         * @@roseuid 4107644702DE
         */
        public UploadTransactionCallback()
        {

        }

        /**
         * �i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���ɍs��insert����B<BR>
         * <BR>
         * this.�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s �̓��e��DB�ɍs��}���iinsert�j����B<BR>
         * null��ԋp����B<BR>
         * @@return Object
         * @@throws com.fitechlabs.xtrade.kernel.data.DataNetworkException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataQueryException
         * @@throws com.fitechlabs.xtrade.kernel.data.DataCallbackException
         * @@roseuid 40F209CF01F5
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            AdministratorUploadParams l_administratorUploadParams = 
                this.administratorUploadParams;

            //this.�i�Ǘ��ҋ��ʁj�A�b�v���[�h�s �̓��e��DB�ɍs��}���iinsert�j����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doInsertQuery(l_administratorUploadParams);

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    private class UploadErrorTransactionCallback implements TransactionCallback
    {
        public AdministratorUploadParams administratorUploadParams;

        public UploadErrorTransactionCallback()
        {
        }
        
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            AdministratorUploadParams l_administratorUploadParams = 
                this.administratorUploadParams;

            //�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_queryProcessor.doUpdateQuery(l_administratorUploadParams);

            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }

    /**
     * (saveAll�A�b�v���[�h���~) <BR>
     * �w��̃t�@@�C���^�C�v�̍s���ׂĂɃA�b�v���[�h�I�������X�V����B<BR>
     * <BR>
     * �P�j�@@�A�b�v���[�h�h�c�擾<BR>
     * �@@�ȉ��̏����ɊY������s�̃A�b�v���[�h�h�c�����ׂĎ擾����B<BR>
     * �@@�Y���s���Ȃ��ꍇ�A�������I������B�ireturn�j<BR>
     * <BR>
     * [����]<BR>
     * �،���ЃR�[�h = this.get�،���ЃR�[�h()<BR>
     * ���X�R�[�h = this.get���X�R�[�h()<BR>
     * �����^�C�v = this.get�����^�C�v()<BR>
     * �A�b�v���[�h�t�@@�C��ID = this.get�A�b�v���[�h�t�@@�C���h�c()<BR>
     * �A�b�v���[�h�I������ = null<BR> 
     * <BR>
     * �Q�j�@@�A�b�v���[�h�e�[�u���X�V<BR>
     * �@@�P�j�Ŏ擾�����s�ɂ��āA<BR>
     * �@@�ȉ��̒ʂ�A�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u�����X�V����B <BR>
     * <BR>
     * �@@�A�b�v���[�h�I������ = System.currentTimeMillis()<BR>
     *   �A�b�v���[�h���� = 0<BR>
     * <BR>
     * �R�j�@@�A�b�v���[�h�e���|�����폜<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�s��<BR>
     * �u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����폜�idelete�j����B<BR>
     * <BR>
     * �@@�A�b�v���[�h�h�c in �i�P�j�Ŏ擾�����A�b�v���[�h�h�c�j<BR>
     * <BR>
     * ���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B<BR>
     * <BR>
     * @@throws WEB3SystemLayerException
     * @@roseuid 40F2155C0253
     */
    public void saveAllUploadStop() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "saveAllUploadStop()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�A�b�v���[�h�h�c�擾
        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strBranchCode = this.getBranchCode();
        ProductTypeEnum l_enumProductType = this.getProductType();
        String l_strUploadFileId = this.getUploadFileId();

        List l_lisRecords;
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objAdministratorUploadWhere = {
            l_strInstitutionCode,   //�،���ЃR�[�h
            l_strBranchCode,        //���X�R�[�h
            l_enumProductType,      //�����^�C�v
            l_strUploadFileId       //�A�b�v���[�h�t�@@�C���h�c
            };

        //�ȉ��̏����ɊY������s�̃A�b�v���[�h�h�c�����ׂĎ擾����B
        //�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u���v������
        try
        {
            l_sbWhere.append(" institution_code = ? ");            //�،���ЃR�[�h
            l_sbWhere.append(" and branch_code = ? ");             //���X�R�[�h
            l_sbWhere.append(" and product_type = ? ");            //�����^�C�v
            l_sbWhere.append(" and upload_file_id = ? ");          //�A�b�v���[�h�t�@@�C���h�c
            l_sbWhere.append(" and upload_end_timestamp is null ");//�A�b�v���[�h�I������

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords =
                l_queryProcessor.doFindAllQuery(
                    AdministratorUploadRow.TYPE,
                    l_sbWhere.toString(),
                    l_objAdministratorUploadWhere);
        }
        catch (DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //�Y���s���Ȃ��ꍇ�A�������I������B
        if ((l_lisRecords == null) || l_lisRecords.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return ;
        }

        int l_intSize  = l_lisRecords.size();

        //�Q�j�@@�A�b�v���[�h�e�[�u���X�V
        Map l_map = new Hashtable();
        //�A�b�v���[�h�I������ = System.currentTimeMillis()
        l_map.put("upload_end_timestamp", new Date(System.currentTimeMillis()));
        //�A�b�v���[�h���� = 0
        l_map.put("upload_count", new Integer(0));
        
        try
        {        
            QueryProcessor l_queryProcessor =
                 Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateAllQuery(
                AdministratorUploadParams.TYPE,
                l_sbWhere.toString(),
                l_objAdministratorUploadWhere,
                l_map);
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        //�R�j�@@�A�b�v���[�h�e���|�����폜
        try
        {
            long l_lngAdministratorUploadId;
            Long l_upLoadId;
            StringBuffer l_delWhere = new StringBuffer();
            l_delWhere.append(" administrator_upload_id = ? ");    //�A�b�v���[�h�h�c

            for (int i = 0; i< l_intSize; i++)
            {
                Object l_Obj = l_lisRecords.get(i);
                
                //���Y���f�[�^���Ȃ��Ă��A��O����ʂɃX���[���Ȃ��B
                if (l_Obj == null)
                    continue;

                l_lngAdministratorUploadId =
                     ((AdministratorUploadRow)l_Obj).getAdministratorUploadId();
                l_upLoadId = new Long(l_lngAdministratorUploadId);

                Object[] l_objAdministratorUploadTempWhere = {
                    l_upLoadId    //�A�b�v���[�h�h�c
                    };
                QueryProcessor l_delQueryProcessor = 
                    Processors.getDefaultProcessor();
                //�u�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e���|�����v�e�[�u�����s���폜����B
                l_delQueryProcessor.doDeleteAllQuery(
                    AdministratorUploadTempRow.TYPE,
                    l_delWhere.toString(),
                    l_objAdministratorUploadTempWhere
                );
            }
        }
        catch (DataException de)
        {
            log.debug(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
