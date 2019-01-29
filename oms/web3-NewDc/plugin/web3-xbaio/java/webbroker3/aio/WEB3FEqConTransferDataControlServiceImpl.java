head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FEqConTransferDataControlServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���U�֘A�g�f�[�^����T�[�r�XImpl�N���X(WEB3FEqConTransferDataControlServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/21 ��O�� (���u) �V�K�쐬       
*/

package webbroker3.aio;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.aio.data.FeqAccountParams;
import webbroker3.aio.data.FeqAccountRow;
import webbroker3.aio.data.UwgAccountOpenStatusParams;
import webbroker3.aio.data.UwgAccountOpenStatusRow;
import webbroker3.aio.data.UwgTransferStatusParams;
import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.define.WEB3AioQuestionAnswerDef;
import webbroker3.aio.message.WEB3FEqConAccountOpenQuestionInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountOpenStatusDivDef;
import webbroker3.common.define.WEB3AioAccountDivDef;
import webbroker3.common.define.WEB3FeqFirstTransferFlagDef;
import webbroker3.common.define.WEB3FeqTransOperationDivDef;
import webbroker3.common.define.WEB3QuestionDivDef;
import webbroker3.common.define.WEB3SendRcvDivDef;
import webbroker3.common.define.WEB3TransferStatusDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.QuestionAnswerParams;
import webbroker3.gentrade.data.QuestionAnswerRow;
import webbroker3.gentrade.data.QuestionParams;
import webbroker3.gentrade.data.QuestionRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O���U�֘A�g�f�[�^����T�[�r�XImpl)<BR>
 * �O���U�֘A�g�f�[�^����T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferDataControlServiceImpl implements WEB3FEqConTransferDataControlService 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferDataControlServiceImpl.class);
    
    /**
     * @@roseuid 423563670271
     */
    public WEB3FEqConTransferDataControlServiceImpl() 
    {
     
    }
    
    /**
     * (get�O�������ڋq)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�ɊY������<BR>
     * �O�������ڋqParams���擾����B<BR>
     * <BR>
     * �P�j�O�������ڋq�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�ڋq�R�[�h = ����.�ڋq�R�[�h<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41E4D1CC03CA
     */
    public FeqAccountParams getFeqAccountByAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) 
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getFeqAccountByAccountCode(" +
            "String l_strInstitutionCode, String l_strBranchCode," + 
            "String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�O�������ڋq�e�[�u�����ȉ��̏����Ō�������B 
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@�ڋq�R�[�h = ����.�ڋq�R�[�h 
        List l_lisFeqAccountRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? and account_code = ?";                 
            
            Object l_bindVars[] = {
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode,
                    };   
            
            l_lisFeqAccountRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        
        if(l_lisFeqAccountRows.size() == 0)
        {
            throw new NotFoundException("�O�������ڋq�e�[�u�����擾�ł��܂���ł���");
        }
        
        if(l_lisFeqAccountRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�������ڋq�e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B 
        FeqAccountParams l_feqAccountParams = (FeqAccountParams)l_lisFeqAccountRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_feqAccountParams;     
    }
    
    /**
     * (get�O�������ڋq)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A�O�������ԍ��ɊY������<BR>
     * �O�������ڋqParams���擾����B<BR>
     * <BR>
     * �P�j�O�������ڋq�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@�O�����������ԍ� = ����.�O�������ԍ�<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strFeqAccountCode - �O�����������ԍ�
     * 
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41EF67C00229
     */
    public FeqAccountParams getFeqAccountByFeqAccountCode(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strFeqAccountCode) 
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getFeqAccountByFeqAccountCode(" +
            "String l_strInstitutionCode, String l_strBranchCode," + 
            "String l_strFeqAccountCode)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�O�������ڋq�e�[�u�����ȉ��̏����Ō�������B 
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@�O�����������ԍ� = ����.�O�������ԍ� 

        List l_lisFeqAccountRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? and feq_account_code = ?";                 
            
            Object l_bindVars[] = {
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strFeqAccountCode,
                    };   
            
            l_lisFeqAccountRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }       
        
        if(l_lisFeqAccountRows.size() == 0)
        {
            throw new NotFoundException("�O�������ڋq�e�[�u�����擾�ł��܂���ł���");
        }
        
        if(l_lisFeqAccountRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�������ڋq�e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B 
        FeqAccountParams l_feqAccountParams = (FeqAccountParams)l_lisFeqAccountRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_feqAccountParams;
    }
    
    /**
     * (get�O�������ڋq)<BR>
     * �����̊O�������ڋqID�ɊY������O�������ڋqParams���擾����B<BR>
     * <BR>
     * �P�j�O�������ڋq�e�[�u�����ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�O�������ڋqID = ����.�O�������ڋqID<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B
     * @@param l_strFeqAccountId - �O�������ڋqID
     * 
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 41E61DD203D6
     */
    public FeqAccountParams getFeqAccountByAccountId(String l_strFeqAccountId) 
        throws WEB3BaseException, NotFoundException
    {
        String STR_METHOD_NAME = "getFeqAccountByAccountId(" +
                "String l_strFeqAccountId)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�O�������ڋq�e�[�u�����ȉ��̏����Ō�������B 
        //[����] 
        //�O�������ڋqID = ����.�O�������ڋqID  
    
        List l_lisFeqAccountRows = null;
        try
        {           
            String l_strWhereClause = "feq_account_id = ?";
            
            Object l_bindVars[] = { new Long(l_strFeqAccountId) };
            
            l_lisFeqAccountRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    FeqAccountRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }        
        if(l_lisFeqAccountRows.size() == 0)
        {
            throw new NotFoundException("�O�������ڋq�e�[�u�����擾�ł��܂���ł���");
        }
        
        if(l_lisFeqAccountRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�O�������ڋq�e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B 
        FeqAccountParams l_feqAccountParams = (FeqAccountParams)l_lisFeqAccountRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_feqAccountParams;
    }
    
    /**
     * (get����)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�Ɉ�v����<BR>
     * ����Params��ԋp����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@����e�[�u��(question)���ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@����敪 = "�O�������U��"<BR>
     * <BR>
     * �Q�j�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B<BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@return QuestionParams[]
     * @@roseuid 41E4B6BE02D0
     */
    public QuestionParams[] getQuestion(
            String l_strInstitutionCode, 
            String l_strBranchCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getQuestion(" +
            "String l_strFeqAccountId)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�@@����e�[�u��(question)���ȉ��̏����Ō�������B 
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@����敪 = "�O�������U��" 
        List l_lisQuestionRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? and question_div = ?";                 
            String l_strOrderBy = "to_number(question_no)";
            
            Object l_bindVars[] = { 
                    l_strInstitutionCode,
                    l_strBranchCode, 
                    WEB3QuestionDivDef.FEQ_TRANS};   
            
            l_lisQuestionRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionRow.TYPE, 
                    l_strWhereClause, 
                    l_strOrderBy, 
                    null, 
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        QuestionParams[] l_questionParams = null;
        //�Q�j�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B 
        if(l_lisQuestionRows.size() > 0)
        {
            l_questionParams = 
                new QuestionParams[l_lisQuestionRows.size()]; 
            l_lisQuestionRows.toArray(l_questionParams);   
            log.exiting(STR_METHOD_NAME);
            return l_questionParams;        
        }
        //���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B 
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (get�����)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h�Ɉ�v����<BR>
     * �����Params��ԋp����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@����񓚃e�[�u��(question_answer)���ȉ��̏����Ō�������B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@����敪 = "�O�������U��"<BR>
     * �@@���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �Q�j�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B<BR>
     * �@@���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return QuestionAnswerParams[]
     * @@roseuid 41E4B6BE02DF
     */
    public QuestionAnswerParams[] getQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getQuestionAnswer(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�@@����񓚃e�[�u��(question_answer)���ȉ��̏����Ō�������B 
        //�@@[����] 
        //�@@�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //�@@���X�R�[�h = ����.���X�R�[�h 
        //�@@����敪 = "�O�������U��" 
        //�@@���ʃR�[�h = ����.���ʃR�[�h 

        List l_lisQuestionAnswerRows = null;
        try
        {           
            String l_strWhereClause = 
                "institution_code = ? and branch_code = ? " +
                "and question_div = ? and order_request_number =?";                 
            String l_strOrderBy = "question_no";
            
            Object l_bindVars[] = { 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    WEB3QuestionDivDef.FEQ_TRANS, 
                    l_strRequestNumber 
                    };
            
            l_lisQuestionAnswerRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    QuestionAnswerRow.TYPE, 
                    l_strWhereClause, 
                    l_strOrderBy, 
                    null, 
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        QuestionAnswerParams[] l_questionAnswerParams = null;
        //�Q�j�������ʂ��u����ԍ��v���ڂ̏����Ń\�[�g���A�ԋp����B 
        if(l_lisQuestionAnswerRows.size() > 0)
        {
            l_questionAnswerParams = 
                new QuestionAnswerParams[l_lisQuestionAnswerRows.size()]; 
            l_lisQuestionAnswerRows.toArray(l_questionAnswerParams);   
            log.exiting(STR_METHOD_NAME);
            return l_questionAnswerParams;        
        }
        //���������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B 
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
    
    /**
     * (getUWG�����J�ݏ�)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h��<BR>
     * �Y������UWG�����J�ݏ�Params��ԋp����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�ȉ��̏����ŁAUWG�����J�ݏ󋵃e�[�u������������B<BR>
     * <BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return UwgAccountOpenStatusParams
     * @@roseuid 41E4B6BE02FE
     */
    public UwgAccountOpenStatusParams getUwgAccountOpenStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getUwgAccountOpenStatus(" + 
            "String l_strInstitutionCode, String l_strBranchCode, " + 
            "String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�ȉ��̏����ŁAUWG�����J�ݏ󋵃e�[�u������������B 
        //�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //���X�R�[�h = ����.���X�R�[�h 
        //���ʃR�[�h = ����.���ʃR�[�h         
    
        List l_lisRows = null;
        try
        {           
            String l_strWhereClause = "institution_code = ? and " +
                    "branch_code = ? and order_request_number = ?";                 
            
            Object l_bindVars[] = { 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strRequestNumber
                    };   
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgAccountOpenStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B        
        if (l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "UWG�����J�ݏ󋵃e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B 
        UwgAccountOpenStatusParams l_uwgAccountOpenStatusParams = 
            (UwgAccountOpenStatusParams)l_lisRows.get(0);
        log.exiting(STR_METHOD_NAME);
        return l_uwgAccountOpenStatusParams;
    }
    
    /**
     * (getUWG�����J�ݏ�)<BR>
     * �����̏����ɊY������UWG�����J�ݏ�Params��<BR>
     * �ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@rowType�F�@@UWG�����J�ݏ�Row.TYPE<BR>
     * �@@�@@where�F�@@�p�����[�^.��������������<BR>
     * �@@�@@orderBy�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@conditions�F�@@null<BR>
     * �@@�@@bindVars�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * @@return UwgAccountOpenStatusParams[]
     * @@roseuid 41E4B6BE030E
     */
    public UwgAccountOpenStatusParams[] getUwgAccountOpenStatus(
            String l_strQueryString, 
            String[] l_queryContainer, 
            String l_strSortCond) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getUwgAccountOpenStatus(" +
                "String l_strQueryString, String[] l_queryContainer, " + 
                "String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B 
        //[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
        //rowType�F�@@UWG�����J�ݏ�Row.TYPE 
        //where�F�@@�p�����[�^.�������������� 
        //orderBy�F�@@�p�����[�^.�\�[�g���� 
        //conditions�F�@@null 
        //bindVars�F�@@�p�����[�^.���������f�[�^�R���e�i        
    
        List l_lisRows = null;
        try
        {
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgAccountOpenStatusRow.TYPE,
                    l_strQueryString,
                    l_strSortCond, 
                    null,
                    l_queryContainer);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }              

        UwgAccountOpenStatusParams[] l_uwgAccountOpenStatusParams = null;
        //�Q�j�������ʂ�ԋp����B 
        if(l_lisRows.size() > 0)
        {
            l_uwgAccountOpenStatusParams = 
                new UwgAccountOpenStatusParams[l_lisRows.size()]; 
            
            l_lisRows.toArray(l_uwgAccountOpenStatusParams);   
            
            log.exiting(STR_METHOD_NAME);
            return l_uwgAccountOpenStatusParams;        
        }
        //���������ʂ��擾�ł��Ȃ������ꍇ�A�v�f����0�̔z�񂪕ԋp����B
        else
        {
            log.exiting(STR_METHOD_NAME);
            l_uwgAccountOpenStatusParams = new UwgAccountOpenStatusParams[0];
            return l_uwgAccountOpenStatusParams;
        }      
    }
    
    /**
     * (getUWG�U�֏�)<BR>
     * �����̏،���ЃR�[�h�A���X�R�[�h�A���ʃR�[�h��<BR>
     * �Y������UWG�U�֏�Params��ԋp����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �@@�ȉ��̏����ŁAUWG�U�֏󋵃e�[�u������������B<BR>
     * <BR>
     * �@@�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h = ����.���X�R�[�h<BR>
     * �@@���ʃR�[�h = ����.���ʃR�[�h<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�������ʂ�ԋp����B
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@return UwgTransferStatusParams
     * @@roseuid 41E4B6BE032D
     */
    public UwgTransferStatusParams getUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getUwgTransferStatus(" +
                "String l_strInstitutionCode, String l_strBranchCode, " + 
                "String l_strRequestNumber)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jDB���� 
        //�ȉ��̏����ŁAUWG�U�֏󋵃e�[�u������������B 
        //�،���ЃR�[�h = ����.�،���ЃR�[�h 
        //���X�R�[�h = ����.���X�R�[�h 
        //���ʃR�[�h = ����.���ʃR�[�h       
    
        List l_lisRows = null;
        try
        {
            String l_strWhereClause = "institution_code = ? and " +
            "branch_code = ? and order_request_number = ?";                 
    
            Object l_bindVars[] = { 
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strRequestNumber
                    };   
            
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    UwgTransferStatusRow.TYPE,
                    l_strWhereClause,
                    null,
                    l_bindVars);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }      
        
        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B        
        if (l_lisRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        if(l_lisRows.size() > 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "UWG�U�֏󋵃e�[�u���ɕ�����");
        }
        
        //�Q�j�������ʂ�ԋp����B 
        UwgTransferStatusParams l_uwgTransferStatusParams = 
            (UwgTransferStatusParams)l_lisRows.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return l_uwgTransferStatusParams;
    }
    
    /**
     * (insert�O�������ڋq)<BR>
     * UWG�����J�ݏ�Params�̓��e���A<BR>
     * �O�������ڋq�e�[�u���ɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�O�������J�݊Ǘ�_�O�������ڋq�e�[�u��DB�X�V�d�l.xls�v
     * @@param l_uwgAccOpenStatusParams - UWG�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B6BE035C
     */
    public void insertFeqAccount(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdaterCode)  throws WEB3BaseException
    {
        String STR_METHOD_NAME ="insertFeqAccount(" +
                "UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, " +
                "String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);

        if(l_uwgAccOpenStatusParams == null )
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        FeqAccountParams l_feqAccountParams = new FeqAccountParams();
        
        //1) �O�������ڋqID = �O���U�֘A�g�f�[�^����T�[�r�XImpl.get�V�K�O���ڋqID(
        //      this.�،���ЃR�[�h, this.���X�R�[�h,this.�ڋq�R�[�h)
        String l_strFeqAccountID =  this.getNewFeqAccountId(
                l_uwgAccOpenStatusParams.getInstitutionCode(),
                l_uwgAccOpenStatusParams.getBranchCode(), 
                l_uwgAccOpenStatusParams.getAccountCode());
            
        l_feqAccountParams.setFeqAccountId(Long.parseLong(l_strFeqAccountID));
        
        //2) �،���ЃR�[�h = UWG�����J�ݏ�Params.�،���ЃR�[�h
        l_feqAccountParams.setInstitutionCode(l_uwgAccOpenStatusParams.getInstitutionCode());
        
        //3) ���X�R�[�h = UWG�����J�ݏ�Params.���X�R�[�h
        l_feqAccountParams.setBranchCode(l_uwgAccOpenStatusParams.getBranchCode());
        
        //4) �ڋq�R�[�h = UWG�����J�ݏ�Params.�ڋq�R�[�h
        l_feqAccountParams.setAccountCode(l_uwgAccOpenStatusParams.getAccountCode());
        
        //5) ���O�i���j = UWG�����J�ݏ�Params.���O�i���j
        l_feqAccountParams.setLastName(l_uwgAccOpenStatusParams.getLastName());
        
        //6) ���O�i���j= UWG�����J�ݏ�Params.���O�i���j
        l_feqAccountParams.setFirstName(l_uwgAccOpenStatusParams.getFirstName());
                   
        //7) �O�����������ԍ� = UWG�����J�ݏ�Params.�O�����������ԍ�
        l_feqAccountParams.setFeqAccountCode(l_uwgAccOpenStatusParams.getFeqAccountCode());
        
        //8) �����J�݋敪 = 1�F�J�ݍ�
        l_feqAccountParams.setAccountOpenDiv(WEB3AioAccountDivDef.OPEN_COMPLETE);
        
        //9) ����U�փt���O = 0�F�����{
        l_feqAccountParams.setFirstTransferFlag(WEB3FeqFirstTransferFlagDef.NOT_TRANSFER);
        
        //10) �X�V�҃R�[�h = �Ǘ���.get�Ǘ��҃R�[�h()
        l_feqAccountParams.setLastUpdater(l_strUpdaterCode);
        
        //11) �쐬���t = ���ݎ���
        l_feqAccountParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //12) �X�V���t = ���ݎ���
        l_feqAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {
            //insert FX�ڋq�e�[�u��
            WEB3DataAccessUtility.insertRow(l_feqAccountParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (insert�����)<BR>
     * �O�������J�ݎ�����̓��e��<BR>
     * ����񓚃e�[�u��(question_answer)�ɍs��insert���s���B<BR>
     * ������.������ꗗ�̗v�f������Loop�������s���A<BR>
     *    �v�f���Ƃɍs��insert���s���B<BR>
     * <BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�O�������J��_����񓚃e�[�u��.xls�v
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_feqAccOpenQuestionInfo - �O�������J�ݎ�����̈ꗗ
     * @@roseuid 41E4B6BF0001
     */
    public void insertQuestionAnswer(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strRequestNumber, 
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "insertQuestionAnswer(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strRequestNumber, " +
            "WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)";
        log.entering(STR_METHOD_NAME);        
        
        if (l_feqAccOpenQuestionInfo == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        try
        {
            for (int i = 0 ; i < l_feqAccOpenQuestionInfo.length; i++)
            {
                QuestionAnswerParams l_questionAnswerParams = new QuestionAnswerParams();
                
                //1) �،���ЃR�[�h : ����.�،���ЃR�[�h
                l_questionAnswerParams.setInstitutionCode(l_strInstitutionCode);
                
                //2) ���X�R�[�h : ����.���X�R�[�h
                l_questionAnswerParams.setBranchCode(l_strBranchCode);
                
                //3) ����敪 : 0002�F�O�������U��
                l_questionAnswerParams.setQuestionDiv(WEB3QuestionDivDef.FEQ_TRANS);
                
                //4) ���ʃR�[ : ����.���ʃR�[�h
                l_questionAnswerParams.setOrderRequestNumber(l_strRequestNumber);
                
                //5) ����ԍ� : ����.������ꗗ[index].����ԍ�                
                log.debug("����.������ꗗ[index].����ԍ�" + 
                        l_feqAccOpenQuestionInfo[i].questionNumber);
                
                l_questionAnswerParams.setQuestionNo(l_feqAccOpenQuestionInfo[i].questionNumber);
                
                //6) ����� : ����.������ꗗ[index].�����
                log.debug("����.������ꗗ[index].�����" + 
                        l_feqAccOpenQuestionInfo[i].questionAnswer);
                l_questionAnswerParams.setQuestionAnswer(l_feqAccOpenQuestionInfo[i].questionAnswer);
                
                //7) �X�V�҃R�[�h : null
                l_questionAnswerParams.setLastUpdater(null);
                
                //8) �쐬���t : ���ݎ���
                l_questionAnswerParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //9) �X�V���t : ���ݎ���
                l_questionAnswerParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
                //insert ����񓚃e�[�u��
                WEB3DataAccessUtility.insertRow(l_questionAnswerParams);
            }    
            
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (insertUWG�����J�ݏ�)<BR>
     * UWG�����J�ݏ󋵃e�[�u���ɍs��insert���s���B<BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�O�������J��_UWG�����J�ݏ󋵃e�[�u��.xls�v
     * @@param l_mainAccount - �ڋq�I�u�W�F�N�g
     * @@param l_strPassword - UWG�p�p�X���[�h
     * @@param l_strOrderRequestNumber - ���ʃR�[�h
     * @@roseuid 41E4B6BF0030
     */
    public void insertUwgAccountOpenStatus(
            MainAccount l_mainAccount, 
            String l_strPassword, 
            String l_strOrderRequestNumber) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertUwgAccountOpenStatus(" +
                "MainAccount l_mainAccount, String l_strPassword)";
        log.entering(STR_METHOD_NAME);
        
        if(l_mainAccount == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        UwgAccountOpenStatusParams l_uwgAccountOpenStatusParams = new UwgAccountOpenStatusParams();

        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        //1) �،���ЃR�[�h : ����.�ڋq.�،���ЃR�[�h
        l_uwgAccountOpenStatusParams.setInstitutionCode(l_mainAccountRow.getInstitutionCode());
        
        //2) ���X�R�[�h : ����.�ڋq.���X�R�[�h
        l_uwgAccountOpenStatusParams.setBranchCode(l_mainAccount.getBranch().getBranchCode());
        
        //3) �ڋq�R�[�h : ����.�ڋq.�ڋq�R�[�h
        l_uwgAccountOpenStatusParams.setAccountCode(l_mainAccountRow.getAccountCode());
        
        //4) ���ʃR�[�h : ����.���ʃR�[�h
        l_uwgAccountOpenStatusParams.setOrderRequestNumber(l_strOrderRequestNumber);
        log.debug("���ʃR�[�h :" + l_uwgAccountOpenStatusParams.getOrderRequestNumber());
        
        //5) ���O�i���j : ����.�ڋq.���O�i�c���j
        l_uwgAccountOpenStatusParams.setLastName(l_mainAccountRow.getFamilyName());
        
        //6) ���O�i���j : null
        l_uwgAccountOpenStatusParams.setFirstName(null);
        
        //7) ���[���A�h���X : ����.�ڋq.email�A�h���X
        l_uwgAccountOpenStatusParams.setMailAddress(l_mainAccountRow.getEmailAddress());
        
        //8) �O�����������ԍ� : ����.�ڋq.�ڋq�R�[�h�̏�6��
        l_uwgAccountOpenStatusParams.setFeqAccountCode(l_mainAccount.getAccountCode().substring(0,6));
        log.debug("�O�����������ԍ� :" + l_uwgAccountOpenStatusParams.getFeqAccountCode());

		//9) �p�X���[�h : ����.�p�X���[�h���O���U�֘A�g�f�[�^����T�[�r�XImpl.mask�p�X���[�h()�ɂĕϊ���������
		l_uwgAccountOpenStatusParams.setPassword(this.maskPassword(l_strPassword));
		log.debug("�p�X���[�h :" + this.maskPassword(l_strPassword));
        
        //10) �����J�ݏ󋵋敪 = 0�F�����J�ݒ�
        l_uwgAccountOpenStatusParams.setAccountOpenStatusDiv(WEB3AccountOpenStatusDivDef.ACCOUNT_OPENING);
             
        //11) ����M�敪 : 0�F�����M
        l_uwgAccountOpenStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.NOT_SEND);
        
        //12) ��t���ʃR�[�h : null
        l_uwgAccountOpenStatusParams.setResultCode(null);
        
        //13) �G���[���R�R�[�h : null
        l_uwgAccountOpenStatusParams.setErrorReasonCode(null);
        
        //14) �X�V�҃R�[�h : null
        l_uwgAccountOpenStatusParams.setLastUpdater(null);
        
        //15) �쐬���t : ���ݎ���
        l_uwgAccountOpenStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //16) �X�V���t : ���ݎ���
        l_uwgAccountOpenStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        {            
            //insert GFT�����J�ݏ󋵃e�[�u��
            WEB3DataAccessUtility.insertRow(l_uwgAccountOpenStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (insertUWG�U�֏�)<BR>
     * UWG�U�֏󋵃e�[�u���ɍs��insert���s���B<BR>
     * <BR>
     * �P�j�O�������ڋq�I�u�W�F�N�g�̎擾<BR>
     * <BR>
     *   this.get�O�������ڋq()���R�[������B<BR>
     * <BR>
     *   [����]<BR>
     *   �،���ЃR�[�h�F ����.�،���ЃR�[�h<BR>
     *   ���X�R�[�h�F ����.���X�R�[�h<BR>
     *   �ڋq�R�[�h�F ����.�ڋq�R�[�h<BR>
     * <BR>
     * �Q�jDB insert<BR>
     * �}������s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �y��Trade�z�⑫����.DB�X�V <BR>
     * �u�O�������ւ̐U��_UWG�U�֏󋵃e�[�u��.xls�v
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strTransferDate - ��n�\���
     * @@param l_strRequestNumber - ���ʃR�[�h
     * @@param l_strMrgTrnRequestNumber - �M�p�U�֗p���ʃR�[�h
     * @@param l_strTransferAmount - �U�֋��z
     * ���M�p��������̋����U�ւ��s��Ȃ��ꍇ�Anull
     * @@roseuid 41E4F8D0010A
     */
    public void insertUwgTransferStatus(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strTransferDate, 
            String l_strRequestNumber, 
            String l_strMrgTrnRequestNumber, 
            String l_strTransferAmount) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "insertUwgTransferStatus(" +
            "String l_strInstitutionCode, String l_strBranchCode" + 
            "String l_strAccountCode, String l_strTransferDate, " + 
            "String l_strRequestNumber, String l_strMrgTrnRequestNumber";        
        log.entering(STR_METHOD_NAME);
        
        FeqAccountParams l_feqAccountParams = null;
        try
        {
            l_feqAccountParams = this.getFeqAccountByAccountCode(
                l_strInstitutionCode, 
                l_strBranchCode, 
                l_strAccountCode);
        }
        catch (NotFoundException l_ex)
        {        
            log.debug("__NotFoundException__", l_ex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.debug("�O�������ڋqParams = " + l_feqAccountParams);
        
        UwgTransferStatusParams l_uwgTransferStatusParams = new UwgTransferStatusParams();

        //1) �،���ЃR�[�h : ����.�،���ЃR�[�h
        l_uwgTransferStatusParams.setInstitutionCode(l_strInstitutionCode);
        
        //2) ���X�R�[�h : ����.���X�R�[�h
        l_uwgTransferStatusParams.setBranchCode(l_strBranchCode);
        
        //3) �ڋq�R�[�h : ����.�ڋq�R�[�h
        l_uwgTransferStatusParams.setAccountCode(l_strAccountCode);
        
        //4) ���ʃR�[�h : ����.���ʃR�[�h
        l_uwgTransferStatusParams.setOrderRequestNumber(l_strRequestNumber);
        
        //5) �����敪 : 01�F�،���������O������������
        l_uwgTransferStatusParams.setOperationDiv(WEB3FeqTransOperationDivDef.TRANSFER_TO_FEQ);
        
        //6) �O�����������ԍ� : �O�������ڋq.�O�����������ԍ�
        l_uwgTransferStatusParams.setFeqAccountCode(l_feqAccountParams.getFeqAccountCode());
        
        //7) ���z : ����.�U�֋��z
        log.debug("����.�U�֋��z = " + l_strTransferAmount);
        l_uwgTransferStatusParams.setAmount(Long.parseLong(l_strTransferAmount));
           
        //8) ��n�\��� : ����.��n�\���
        log.debug("����.��n�\��� = " + l_strTransferDate);
        l_uwgTransferStatusParams.setTransferDate(l_strTransferDate);
        
        //9) �U�֏󋵋敪 = 0�F���ϒ�
        l_uwgTransferStatusParams.setTransferStatusDiv(WEB3TransferStatusDivDef.PROCESSING);
        
        //10) ����M�敪 : 0�F�����M
        l_uwgTransferStatusParams.setSendRcvDiv(WEB3SendRcvDivDef.NOT_SEND);
        
        //11) ��t���ʃR�[�h : null
        l_uwgTransferStatusParams.setResultCode(null);
        
        //12) �G���[���R�R�[�h : null
        l_uwgTransferStatusParams.setErrorReasonCode(null);
        
        //13) �������ԁi���M�j : null
        l_uwgTransferStatusParams.setSendTime(null);
        
        //14) �������ԁi��M�j : null
        l_uwgTransferStatusParams.setReceiveTime(null);
        
        //15) �M�p�U�֗p���ʃR�[�h�j : ����.�M�p�U�֗p���ʃR�[�h
        log.debug("����.�M�p�U�֗p���ʃR�[�h = " + l_strMrgTrnRequestNumber);
        l_uwgTransferStatusParams.setMrgTrnOrderRequestNumber(l_strMrgTrnRequestNumber);
        
        //16) ����U�֋敪 : �O�������ڋq.����U�փt���O==�h�����{�h�̏ꍇ�A1�F����U��
        //                 �O�������ڋq.����U�փt���O==�h���{�ς݁h�̏ꍇ�A0�F���̑�
        if (WEB3FeqFirstTransferFlagDef.NOT_TRANSFER.equals(
                l_feqAccountParams.getFirstTransferFlag()))
        {
            log.debug("�O�������ڋq.����U�փt���O==�h�����{�h�̏ꍇ");
            l_uwgTransferStatusParams.setFirstTransferDiv(
                    WEB3FeqFirstTransferFlagDef.TRANSFERRED);
        }
        else
        {
            log.debug("�O�������ڋq.����U�փt���O==�h���{�ς݁h�̏ꍇ");
            l_uwgTransferStatusParams.setFirstTransferDiv(
                    WEB3FeqFirstTransferFlagDef.NOT_TRANSFER);
        }
        
        //17) �X�V�҃R�[�h : null
        l_uwgTransferStatusParams.setLastUpdater(null);
        
        //18) �쐬���t : ���ݎ���
        l_uwgTransferStatusParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //19) �X�V���t : ���ݎ���
        l_uwgTransferStatusParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        try
        {
            //insert UWG�U�֏󋵃e�[�u��
            WEB3DataAccessUtility.insertRow(l_uwgTransferStatusParams);
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (update�O�������ڋq)<BR>
     * �X�V������J�݋敪�̒l�ŊO�������ڋq�e�[�u�����X�V����B <BR>
     * <BR>
     * �P�j�O�������ڋqParams�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�p�����[�^.�O�������ڋqParam��<BR>
     * �@@�v���p�e�B���R�s�[����B<BR>
     * <BR>
     * �R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B<BR>
     * �@@�X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�u�O�������Ǘ�_�O�������ڋq�e�[�u��DB�X�V�d�l.xls�v<BR>
     * <BR>
     * �S�j�O�������ڋq��update<BR>
     *  WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[updateRow()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X
     * �@@
     * @@param l_feqAccountParams - �O�������ڋqParam�I�u�W�F�N�g
     * @@param l_strUpdatedAccOpenDiv - �X�V������J�݋敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B6BF009D
     */
    public void updateFeqAccount(
            FeqAccountParams l_feqAccountParams, 
            String l_strUpdatedAccOpenDiv, 
            String l_strUpdaterCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFeqAccount(FeqAccountParams l_feqAccountParams, " + 
            "String l_strUpdatedAccOpenDiv,String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_feqAccountParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�O�������ڋqParams�C���X�^���X�𐶐�����B       
        //�Q�j���������C���X�^���X�ɁA�p�����[�^.�O�������ڋqParam�̃v���p�e�B���R�s�[����B
        FeqAccountParams l_feqAccountParamsForUpd = new FeqAccountParams(l_feqAccountParams);
        
        //�R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B 
        // �X�V����s�̓��e�͉��L���Q�ƁB 
        //�y��Trade�z�⑫����.DB�X�V 
        //�u�O�������Ǘ�_�O�������ڋq�e�[�u��DB�X�V�d�l.xls�v
        
        //�����J�݋敪 = ���N�G�X�g�f�[�^.�X�V������J�ݏ󋵋敪("1�F�J�ݍ�" or "9�F����")
        l_feqAccountParamsForUpd.setAccountOpenDiv(l_strUpdatedAccOpenDiv);
        log.debug("�����J�݋敪 = " + l_strUpdatedAccOpenDiv);
        
        //�X�V�҃R�[�h = �Ǘ���.get�Ǘ��҃R�[�h()
        l_feqAccountParamsForUpd.setLastUpdater(l_strUpdaterCode);
        log.debug("�X�V�҃R�[�h = " + l_strUpdaterCode);
        
        //�X�V���t = ���ݎ���
        l_feqAccountParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
        try
        {            
            //�S�j�O�������ڋq��update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X 
            WEB3DataAccessUtility.updateRow(l_feqAccountParamsForUpd);        
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (updateUWG�����J�ݏ�)<BR>
     * �X�V��̒l��UWG�����J�ݏ󋵃e�[�u�����X�V����B <BR>
     * <BR>
     * �P�jUWG�����J�ݏ�Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�p�����[�^.UWG�����J�ݏ�Param��<BR>
     * �@@�v���p�e�B���R�s�[����B<BR>
     * <BR>
     * �R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B<BR>
     * �@@�X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �@@�@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�@@�u�O�������J�݊Ǘ�_UWG�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v<BR>
     * <BR>
     * �S�jUWG�����J�ݏ󋵂�update<BR>
     *  WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[updateRow()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X
     * �@@
     * @@param l_uwgAccOpenStatusParams - UWG�����J�ݏ�Params�I�u�W�F�N�g
     * @@param l_strUpdatedStatusDiv - �X�V��X�e�[�^�X�敪
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B6BF00DC
     */
    public void updateUwgAccountOpenStatus(
            UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, 
            String l_strUpdatedStatusDiv, 
            String l_strUpdaterCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateUwgAccountOpenStatus(" +
            "UwgAccountOpenStatusParams l_uwgAccOpenStatusParams, " +
            "String l_strUpdatedStatusDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        if(l_uwgAccOpenStatusParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jUWG�����J�ݏ�Params�C���X�^���X�𐶐�����B 
        //�Q�j���������C���X�^���X�ɁA�p�����[�^.UWG�����J�ݏ�Param�� 
        //  �v���p�e�B���R�s�[����B         
        UwgAccountOpenStatusParams l_uwgAccOpenStatusParamsForUpd = 
            new UwgAccountOpenStatusParams(l_uwgAccOpenStatusParams);
            
        //�R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B 
        //�X�V����s�̓��e�͉��L���Q�ƁB 
        //�@@�y��Trade�z�⑫����.DB�X�V 
        //�@@�u�O�������J�݊Ǘ�_UWG�����J�ݏ󋵃e�[�u��DB�X�V�d�l.xls�v 

        //�����J�ݏ󋵋敪 = ����.�X�V��X�e�[�^�X�敪
        log.debug("�����J�ݏ󋵋敪 = " + l_strUpdatedStatusDiv);
        l_uwgAccOpenStatusParamsForUpd.setAccountOpenStatusDiv(l_strUpdatedStatusDiv);

        //�X�V�҃R�[�h = �Ǘ���.get�Ǘ��҃R�[�h()
        log.debug("�X�V�҃R�[�h = " + l_strUpdaterCode);
        l_uwgAccOpenStatusParamsForUpd.setLastUpdater(l_strUpdaterCode);
        
        //�X�V���t = ���ݎ���
        l_uwgAccOpenStatusParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
        try
        { 
            //�S�jUWG�����J�ݏ󋵂�update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //[updateRow()�ɃZ�b�g����p�����[�^] 
            //l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X 
            
            WEB3DataAccessUtility.updateRow(l_uwgAccOpenStatusParamsForUpd);            
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (updateUWG�U�֏�)<BR>
     * ������UWG�U�֏�Params��UWG�U�֏󋵃e�[�u�����X�V����B <BR>
     * <BR>
     * �P�jUWG�U�֏�Params�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�j���������C���X�^���X�ɁA�p�����[�^.UWG�U�֏�Param��<BR>
     * �@@�v���p�e�B���R�s�[����B<BR>
     * <BR>
     * �R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B<BR>
     * �@@�X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �@@�@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�@@�u�O�������ւ̐U�֎��_UWG�U�֏󋵃e�[�u��DB�X�V�d�l.xls�v<BR>
     * <BR>
     * �S�jUWG�U�֏󋵂�update<BR>
     *  WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[updateRow()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X
     * @@param l_uwgTransferStatusParams - UWG�U�֏�Params�I�u�W�F�N�g
     * @@param l_strUpdatedTransferStatusDiv - �X�V��U�֏󋵋敪
     * @@roseuid 41ECE0880270
     */
    public void updateUwgTransferStatus(
            UwgTransferStatusParams l_uwgTransferStatusParams, 
            String l_strUpdatedTransferStatusDiv) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateUwgTransferStatus(" + 
            "UwgTransferStatusParams l_uwgTransferStatusParams, " + 
            "String l_strUpdatedTransferStatusDiv)";
        log.entering(STR_METHOD_NAME);
        
        if(l_uwgTransferStatusParams == null)
        {
            log.debug(" �p�����[�^�l��NULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�jUWG�U�֏�Params�C���X�^���X�𐶐�����B 
        //�Q�j���������C���X�^���X�ɁA�p�����[�^.UWG�U�֏�Param�� 
        //  �v���p�e�B���R�s�[����B 
     
        UwgTransferStatusParams l_uwgTransferStatusParamsForUpd = 
            new UwgTransferStatusParams(l_uwgTransferStatusParams);
            
        //�R�j���������C���X�^���X�ɍX�V��̃f�[�^���Z�b�g����B 
        // �X�V����s�̓��e�͉��L���Q�ƁB 
        //�y��Trade�z�⑫����.DB�X�V 
        //�u�O�������ւ̐U�֎��_UWG�U�֏󋵃e�[�u��DB�X�V�d�l.xls�v 

        //�U�֏󋵋敪 = ����.�X�V��U�֏󋵋敪
        l_uwgTransferStatusParamsForUpd.setTransferStatusDiv(l_strUpdatedTransferStatusDiv);
               
        //�X�V���t = ���ݎ���
        l_uwgTransferStatusParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
                
        try
        { 
            //�S�jUWG�U�֏󋵂�update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //[updateRow()�ɃZ�b�g����p�����[�^] 
            //  l_row�F�@@�X�V��̃f�[�^���Z�b�g�����C���X�^���X 
            
            WEB3DataAccessUtility.updateRow(l_uwgTransferStatusParamsForUpd);            
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (update�O�������J�݋敪)<BR>
     * �ڋq�}�X�^�[�e�[�u���̊O���،������J�݋敪��update����B<BR>
     * <BR>
     * �P�j�ڋq�̎擾<BR>
     * �@@�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@<BR>
     * �@@[�����̐ݒ�]<BR>
     * �@@�،���ЃR�[�h�F�@@����.�،���ЃR�[�h<BR>
     * �@@���X�R�[�h�F�@@����.���X�R�[�h<BR>
     * �@@�����R�[�h�F�@@����.�ڋq�R�[�h<BR>
     * <BR>
     * �@@�擾�����ڋq.getDataSourceObject()�ɂ��ڋqParams���擾����B<BR>
     * <BR>
     * �R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B<BR>
     * <BR>
     * �@@�X�V����s�̓��e�͉��L���Q�ƁB<BR>
     * <BR>
     * �@@�y��Trade�z�⑫����.DB�X�V <BR>
     * �@@�u�O�������J�݊Ǘ�_�ڋq�}�X�^�[.xls�v�Q��<BR>
     * <BR>
     * �S�j�ڋq��update<BR>
     *  WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[updateRow()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@param l_strUpdatedFeqAccOpenDiv - �X�V��O�������J�݋敪
     * 
     * 0�F�J��
     * 1�F���J��
     * 
     * @@param l_strUpdaterCode - �X�V�҃R�[�h
     * @@roseuid 41E4B6BF0159
     */
    public void updateFeqAccountOpenDiv(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode, 
            String l_strUpdatedFeqAccOpenDiv, 
            String l_strUpdaterCode) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "updateFeqAccountOpenDiv(String l_strInstitutionCode, " + 
            "String l_strBranchCode, String l_strAccountCode, " + 
            "String l_strUpdatedFeqAccOpenDiv, String l_strUpdaterCode)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�ڋq�̎擾 
        //�g���A�J�E���g�}�l�[�W��.get�ڋq()�ɂČڋq�I�u�W�F�N�g���擾����B 
        //[�����̐ݒ�] 
        //�،���ЃR�[�h�F�@@����.�،���ЃR�[�h 
        //���X�R�[�h�F�@@����.���X�R�[�h 
        //�����R�[�h�F�@@����.�ڋq�R�[�h 
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_web3GentradeAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        MainAccount l_mainAccount = 
            l_web3GentradeAccountManager.getMainAccount(
                    l_strInstitutionCode, 
                    l_strBranchCode, 
                    l_strAccountCode);
        
        //�擾�����ڋq.getDataSourceObject()�ɂ��ڋqParams���擾����B 
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        
        MainAccountParams l_mainAccountParamsForUpd = 
            new MainAccountParams(l_mainAccountRow);
            
        //�R�j���������C���X�^���X�ɁA�X�V��̃f�[�^���Z�b�g����B 
        // �X�V����s�̓��e�͉��L���Q�ƁB 
        //�y��Trade�z�⑫����.DB�X�V 
        //�u�O�������J�݊Ǘ�_�ڋq�}�X�^�[.xls�v�Q�� 
        
        //�O�������A�g�����J�݋敪 = ����.�X�V��O�������J�݋敪
        log.debug("�O�������A�g�����J�݋敪 = " + l_strUpdatedFeqAccOpenDiv);
        l_mainAccountParamsForUpd.setFeqConAccOpenDiv(l_strUpdatedFeqAccOpenDiv);
        
        //�O�������A�g�����J�݋敪�X�V�҃R�[�h = ����.�X�V�҃R�[�h
        log.debug("�O�������A�g�����J�݋敪�X�V�҃R�[�h = " + l_strUpdaterCode);
        l_mainAccountParamsForUpd.setFeqConAccOpenDivUpdater(l_strUpdaterCode);
        
        //�O�������A�g�����J�݋敪�X�V���� = ���ݎ���
        l_mainAccountParamsForUpd.setFeqConAccOpenTimestamp(GtlUtils.getSystemTimestamp());
              
        //�X�V���� = ���ݎ���
        l_mainAccountParamsForUpd.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        try
        { 
            //�S�j�ڋq��update 
            //WEB3DataAccessUtility.updateRow()���\�b�h���R�[������B 
            //[updateRow()�ɃZ�b�g����p�����[�^] 
            //�@@l_row�F�@@�R�j�ɂčX�V��̃f�[�^���Z�b�g�����C���X�^���X  
            
            WEB3DataAccessUtility.updateRow(l_mainAccountParamsForUpd);            
        }
        catch (DataException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
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
     * (validate�O�������J�ݎ���)<BR>
     * �O�������J�ݎ���ɑ΂���񓚂̐��������`�F�b�N����B<BR>
     * <BR>
     * ����.������ꗗ�̗v�f���Ƃ�Loop�����ɂāA<BR>
     * �ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �O�������J�ݎ�����.����񓚁��h1�F���Ӂh�̏ꍇ�A��O��thorw����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01949<BR>
     * <BR>
     * @@param l_feqAccOpenQuestionInfo - �O�������J�ݎ�����̈ꗗ
     * @@roseuid 41E4B6BF01E5
     */
    public void validateFeqAccountOpenQuestion(
            WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo) 
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateFeqAccountOpenQuestion(" + 
            "WEB3FEqConAccountOpenQuestionInfo[] l_feqAccOpenQuestionInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_feqAccOpenQuestionInfo == null)
        {
            log.debug("�p�����[�^�l��NULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //����.������ꗗ�̗v�f���Ƃ�Loop�����ɂāA 
        //�ȉ��̃`�F�b�N���s���B 
        //�O�������J�ݎ�����.����񓚁��h1�F���Ӂh�̏ꍇ�A��O��throw����B
        
        for (int i = 0; i < l_feqAccOpenQuestionInfo.length; i++)
        {
            if (!WEB3AioQuestionAnswerDef.AGREE.equals(
                    l_feqAccOpenQuestionInfo[i].questionAnswer))
            {
                log.debug("�O�������J�ݎ�����.����񓚁��h1�F���Ӂh�̏ꍇ");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01949,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���ӂ���ĂȂ����₪����܂�");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�V�K�O�������ڋqID)<BR>
     * �O�������ڋqID��t�Ԃ��ĕԋp����B<BR>
     * <BR>
     * �O�������ڋqID�̕t�ԃ��[����<BR>
     * �،����ID + ���X�R�[�h + �ڋq�R�[�h(*1)�Ƃ���B<BR>
     * <BR>
     * �،����ID + ����.���X�R�[�h + ����.�ڋq�R�[�h��<BR>
     * ������A�������l��ԋp����B<BR>
     * <BR>
     * (*1)����.�ڋq�R�[�h == 7���̏ꍇ�A<BR>
     * �@@����.�ڋq�R�[�h�̐擪����6���ڂ܂ł��g�p����
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �ڋq�R�[�h
     * @@return java.lang.String
     * @@roseuid 41E633900108
     */
    public String getNewFeqAccountId(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "getNewFeqAccountId(String l_strInstitutionCode, " +
            "String l_strBranchCode, String l_strAccountCode)";
        log.entering(STR_METHOD_NAME);
        
		//�g���A�J�E���g�}�l�[�W���擾����   
		FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
		WEB3GentradeAccountManager l_accountManager =
			(WEB3GentradeAccountManager) l_finApp.getAccountManager();

		//�،����ID���擾����
		String l_strInstituionId = null;
		try{
			Institution l_Instituion = l_accountManager.getInstitution(l_strInstitutionCode);
			l_strInstituionId = String.valueOf(l_Instituion.getInstitutionId());
		}
		catch (NotFoundException l_ex) 
		{
			log.error("__NotFoundException__");
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		
        //�O�������ڋqID�̕t�ԃ��[���� 
        //�،����ID + ���X�R�[�h + �ڋq�R�[�h�Ƃ���B 
        
        String l_strAccountCodeNew = l_strAccountCode;
 
        String l_strNewFeqAccountId = l_strInstituionId + 
            l_strBranchCode + l_strAccountCodeNew;
        
        log.debug("�V�K�O�������ڋqID = " + l_strNewFeqAccountId);
        log.exiting(STR_METHOD_NAME);
        return l_strNewFeqAccountId;
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strBranchCode
     * @@param l_strAccountCode
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 423563680128
     */
    public FeqAccountParams getFeqAccount(
            String l_strInstitutionCode, 
            String l_strBranchCode, 
            String l_strAccountCode) 
    {
     return null;
    }
    
    /**
     * @@param l_strFeqAccountId
     * @@return webbroker3.aio.data.FeqAccountParams
     * @@roseuid 4235636A02FD
     */
    public FeqAccountParams getFeqAccount(String l_strFeqAccountId) 
    {
     return null;
    }
    
	/**
	 * (mask�p�X���[�h) <BR>
	 * �p�X���[�h�Ƀ}�X�N��������B <BR>
	 * <BR>
	 * �P�j��̕�����i��A�Ƃ���B�j�𐶐�����B<BR>
	 * �Q�j�ȉ��̂Ƃ���ɁA����.�p�X���[�h�̔z���u�������āA<BR>
	 *      �P�j�̕�����i��A�j�ɃZ�b�g����B<BR>
	 * <BR>
	 * �EA[0] = ����.�p�X���[�h[1] <BR>
	 * �EA[1] = ����.�p�X���[�h[4] <BR>
	 *   A[2] = ����.�p�X���[�h[7] <BR>
	 *   A[3] = ����.�p�X���[�h[6] <BR>
	 *   A[4] = ����.�p�X���[�h[3] <BR>
	 *   A[5] = ����.�p�X���[�h[0] <BR>
	 *   A[6] = ����.�p�X���[�h[2] <BR>
	 *   A[7] = ����.�p�X���[�h[5] <BR>
	 * <BR>
	 * �Q�jA�̕������ԋp����B<BR>
	 * 
	 * @@param l_strMaskPassword  String
	 * @@return String
	 * @@throws WEB3BaseException
	 * @@roseuid 42F8156E0361
	 */
	protected String maskPassword(String l_strMaskPassword)
	   throws WEB3BaseException
	{
	   final String STR_METHOD_NAME = "maskPassword(String[] l_strMaskPassword) ";
	   log.entering(STR_METHOD_NAME);
        
	   if (l_strMaskPassword == null)
	   {
		   log.exiting(STR_METHOD_NAME);
		   return null;
	   }

	   String l_strPasswordModified = l_strMaskPassword;
	   if (l_strMaskPassword.length() < 8)
	   {
		   for (int i = 8; i > l_strMaskPassword.length(); i --)
		   {
			   l_strPasswordModified = l_strPasswordModified + " ";
		   }
	   }
       
	   //�P�j��̕�����i��A�Ƃ���B�j�𐶐�����B
	   StringBuffer A = new StringBuffer();
        
	   //�Q�j�ȉ��̂Ƃ���ɁA����.�p�X���[�h�̔z���u�������āA
	   //�P�j�̕�����i��A�j�ɃZ�b�g����B
	   //A[0] = ����.�p�X���[�h[1] 
	   //A[1] = ����.�p�X���[�h[4] 
	   //A[2] = ����.�p�X���[�h[7]
	   //A[3] = ����.�p�X���[�h[6] 
	   //A[4] = ����.�p�X���[�h[3] 
	   //A[5] = ����.�p�X���[�h[0] 
	   //A[6] = ����.�p�X���[�h[2] 
	   //A[7] = ����.�p�X���[�h[5] 
	   A.append(l_strPasswordModified.charAt(1));
	   A.append(l_strPasswordModified.charAt(4));
	   A.append(l_strPasswordModified.charAt(7));
	   A.append(l_strPasswordModified.charAt(6));
	   A.append(l_strPasswordModified.charAt(3));
	   A.append(l_strPasswordModified.charAt(0));
	   A.append(l_strPasswordModified.charAt(2));
	   A.append(l_strPasswordModified.charAt(5));
       
	   if (l_strMaskPassword.length() > 8)
	   {
		   A.append(l_strMaskPassword.substring(8, l_strMaskPassword.length()));
	   }
	   log.exiting(STR_METHOD_NAME);
	   return A.toString();
	}

}
@
