head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeAccountManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���A�J�E���g�}�l�[�W��(WEB3GentradeAccountManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 �{���@@�瑐(SRA) �V�K�쐬
Revesion History : 2004/02/09 ����@@���j(SRA) ����
Revesion History : 2004/08/25 羐� (���u) �ύX RuntimeSystemException --> WEB3BaseRuntimeException
Revesion History : 2004/10/09 �Г� (���u) getMainAccount(String ,String)���C�� 
Revesion History : 2004/11/22 羐� (���u) is���Z���i�̔��@@����()���\�b�h���폜
Revesion History : 2006/06/14 ������ (���u) �d�l�ύX�E���f��No.196��Ή�
*/
package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AccountManagerImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AccountProductOrderStopParams;
import webbroker3.gentrade.data.AccountProductOrderStopRow;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3LogUtility;

/**
 * �ڋq�ɑ΂��鑀���\�����܂��B<BR>
 * xTrade��AccountManager���g�������N���X�B<BR>
 *<BR>
 * @@author �@@(SRA)
 * @@version 1.0
 * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.AccountManagerImpl
 */
public class WEB3GentradeAccountManager extends AccountManagerImpl
{

    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeMainAccount.class);

    /**
     * get���X<BR>
     *<BR> 
     * �w�肵���،���ЃR�[�h�A���X�R�[�h�ɊY�����镔�X���c�a��茟�����A<BR>
     * ���X�I�u�W�F�N�g��ԋp����B<BR>
     *<BR> 
     * @@param l_strInstitutionCode �،���ЃR�[�h
     * @@param l_strBranchCode ���X�R�[�h
     * @@throws NotFoundException
     * @@return WEB3GentradeBranch
     * @@roseuid 400CEB880171
     */
    public WEB3GentradeBranch getWeb3GenBranch(
        String l_strInstitutionCode,
        String l_strBranchCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getWeb3GenBranch(String, String)";
        log.entering(STR_METHOD_NAME);

        Institution l_institution = super.getInstitution(l_strInstitutionCode);

        log.exiting(STR_METHOD_NAME);
        return (WEB3GentradeBranch) this.getBranch(
            l_institution,
            l_strBranchCode);
    }

    /**
     * get�ڋq<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * (public MainAccount getMainAccount(long accountId))<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public MainAccount getMainAccount(long l_lngAccountId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMainAccount(long)";
        
        try
        {
            return new WEB3GentradeMainAccount(l_lngAccountId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No MainAccount could be found with id : " + l_lngAccountId);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting MainAccount for Id:" + l_lngAccountId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }

    }

    /**
     * get�ڋq<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * getMainAccount(long l_lngInstitutionId, long l_lngBranchId, String l_strAccountCode)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     * 
     */
    public MainAccount getMainAccount(
        long l_lngInstitutionId,
        long l_lngBranchId,
        String l_strAccountCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMainAccount(long, long, String)";
        
        try
        {
            return new WEB3GentradeMainAccount(
                l_lngInstitutionId,
                l_lngBranchId,
                l_strAccountCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No MainAccount could be found with instId,branchId,accountCode: "
                    + l_lngInstitutionId
                    + ","
                    + l_lngBranchId
                    + ","
                    + l_strAccountCode);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting MainAccount for inst id:"
                    + l_lngInstitutionId
                    + ", branchId: "
                    + l_lngBranchId
                    + ", accountCode :"
                    + l_strAccountCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get�ڋq<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * getMainAccount(long l_lngInstitutionId, String l_strBranchCode, String l_strAccountCode)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public MainAccount getMainAccount(
        long l_lngInstitutionId,
        String l_strBranchCode,
        String l_strAccountCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getMainAccount(long, String, String)";
        
        try
        {
            return new WEB3GentradeMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No MainAccount could be found with instId,branchCode,accountCode: "
                    + l_lngInstitutionId
                    + ","
                    + l_strBranchCode
                    + ","
                    + l_strAccountCode);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting MainAccount for inst id:"
                    + l_lngInstitutionId
                    + ", branchCode: "
                    + l_strBranchCode
                    + ", accountCode :"
                    + l_strAccountCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }
    
    /**
     * get�ڋq<BR>
     * �igetMainAccount�̃I�[�o�[���[�h�j<BR>
     *  <BR>
     * �ڋq�}�X�^�e�[�u�����A�����̏،���ЃR�[�h�C���X�R�[�h�C<BR>
     * �����R�[�h���ɊY������ڋq�I�u�W�F�N�g���擾���ԋp����B<BR>
     * �������擾�ł����ꍇ�́A�f�[�^�s�����̗�O���X���[����B<BR>
     *  <BR>
     * ���@@�����R�[�h <BR> 
     * �@@�i�����R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B <BR>
     *  <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_strBranchCode - ���X�R�[�h
     * @@param l_strAccountCode - �����R�[�h
     * @@throws WEB3BaseException
     */
    public WEB3GentradeMainAccount getMainAccount(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccount(String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRecords;
        try
        {
            
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();

            if(l_strAccountCode == null)
            {
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����R�[�h = null");
            }            

            if(l_strAccountCode.length() == 6)
            {
                //�i�����R�[�h.length() == 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B                
                l_lisRecords =
                    l_QueryProcessor.doFindAllQuery(
                        MainAccountRow.TYPE,
                        "institution_code = ? and branch_code = ? and substr(account_code ,0 ,6) = ?",
                        new Object[] { l_strInstitutionCode, l_strBranchCode, l_strAccountCode });
            }
            else
            {
                l_lisRecords =
                    l_QueryProcessor.doFindAllQuery(
                        MainAccountRow.TYPE,
                        "institution_code = ? and branch_code = ? and account_code = ?",
                        new Object[] { l_strInstitutionCode, l_strBranchCode, l_strAccountCode });
            }
        }
        catch (DataException de)
        {
            log.error(STR_METHOD_NAME, de);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        if (l_lisRecords.size() == 0)
        {
            // �i�����R�[�h.length() == 6�j�̏ꍇ�́AWEB3BusinessLayerException���X���[����B                                  
            if(l_strAccountCode.length() == 6)                          
            {                           
                throw new WEB3BusinessLayerException(                       
                    WEB3ErrorCatalog.BUSINESS_ERROR_01987,                  
                    this.getClass().getName() + "." + STR_METHOD_NAME,                  
                    "�ڋq�}�X�^�e�[�u���Ōڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ");                   
            }
            else
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ڋq�}�X�^�e�[�u���Ōڋq�I�u�W�F�N�g���擾�ł��Ȃ��ꍇ");
            }
        }
        else if (l_lisRecords.size() != 1)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ڋq�}�X�^�e�[�u���ɕ������擾�ł����ꍇ");
        }
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_lisRecords.get(0);
        
        log.exiting(STR_METHOD_NAME);
        return new WEB3GentradeMainAccount(l_mainAccountRow);
    }
    

    /**
     * get�⏕����<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * getSubAccount(long l_lngAccountId, long l_lngSubAccountId)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public SubAccount getSubAccount(
        long l_lngAccountId,
        long l_lngSubAccountId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getSubAccount(long, long)";
        
        try
        {
            return new WEB3GentradeSubAccount(
                l_lngAccountId,
                l_lngSubAccountId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "SubAccount not found for accountId= "
                    + l_lngAccountId
                    + ", SubAccount Id : "
                    + l_lngSubAccountId);
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting SubAccount for accountId:"
                    + l_lngAccountId
                    + ", subAccountId:"
                    + l_lngSubAccountId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get�⏕����<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * getSubAccount(long l_lngAccountId, SubAccountTypeEnum l_subAccountType)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public SubAccount getSubAccount(
        long l_lngAccountId,
        SubAccountTypeEnum l_subAccountType)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getSubAccount(long, SubAccountTypeEnum)";
        
        try
        {
            SubAccountRow row =
                SubAccountDao.findRowByAccountIdSubAccountType(
                    l_lngAccountId,
                    l_subAccountType);
            if (row != null)
            {
                return new WEB3GentradeSubAccount(row);
            }
            else
            {
                throw new NotFoundException(
                    "SubAccount not found for accountId= "
                        + l_lngAccountId
                        + ", SubAccountType : "
                        + l_subAccountType);
            }
        }
        catch (DataException de)
        {
            String msg =
                "Exception while getting SubAccount for accountId:"
                    + l_lngAccountId
                    + ", SubAccountType :"
                    + l_subAccountType;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get�،����<BR>
     *<BR>
     * super Class[AccountManagerImpl]��<BR>
     * getInstitution(long institutionId)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public Institution getInstitution(long l_lngInstitutionId)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getInstitution(long)";
        
        try
        {
            return new WEB3GentradeInstitution(l_lngInstitutionId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No institution   object found with the  id : "
                    + l_lngInstitutionId);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting institution  Object for  id : "
                    + l_lngInstitutionId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get�،����<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * getMainAccount(String l_strInstitutionCode)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public Institution getInstitution(String l_strInstitutionCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getInstitution(String)";
        
        try
        {
            return new WEB3GentradeInstitution(l_strInstitutionCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No Institution   object found with the  code : "
                    + l_strInstitutionCode);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting Institution  Object for  code : "
                    + l_strInstitutionCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get���X<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * getBranch(long branchId)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public Branch getBranch(long l_lngBranchId) throws NotFoundException
    {
        final String STR_METHOD_NAME = "getBranch(long)";
        
        try
        {
            return new WEB3GentradeBranch(l_lngBranchId);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No branch   object found with the  id : " + l_lngBranchId);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting Branch  Object for  id : " + l_lngBranchId;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * get���X<BR>
     *<BR> 
     * super Class[AccountManagerImpl]��<BR>
     * getBranch(Institution l_institution, String l_strBranchCode)<BR>
     * ���\�b�h�̃I�[�o�[���C�h<BR>
     * @@author ����@@���j(SRA)
     * @@throws NotFoundException
     *
     */
    public Branch getBranch(Institution l_institution, String l_strBranchCode)
        throws NotFoundException
    {
        final String STR_METHOD_NAME = "getBranch(Institution, String)";
        
        try
        {
            return new WEB3GentradeBranch(l_institution, l_strBranchCode);
        }
        catch (DataFindException dfe)
        {
            throw new NotFoundException(
                "No branch   object found with the  code : " + l_strBranchCode);
        }
        catch (DataException de)
        {
            String msg =
                "Error while getting Branch  Object for  code : "
                    + l_strBranchCode;
            log.error(msg,de);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (lock����) <BR>
     *<BR> 
     * ���菈���̏������ɁA <BR>
     * �O���v���Z�X����̃f�[�^�A�N�Z�X��h�~���邽�߂� <BR>
     * ���������b�N����B  <BR>
     * �����̏��������h�c���擾���A�擾�������������b�N����B <BR> 
     * �V�[�P���X�} �u�i�����jlock�ڋq�v�Q�ƁB <BR> 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strBranchCode - (���X�R�[�h)
     * @@param l_strAccountCode - (�����R�[�h)
     * @@return void
     * @@throws WEB3BaseException
     */
    public void lockAccount(
        String l_strInstitutionCode,
        String l_strBranchCode,
        String l_strAccountCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "lockAccount(String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        Institution l_institution = null;
        long l_lngInstitutionId = 0;
        MainAccount l_mainAccount = null;
        
        try
        {
            //NotFoundException���X���[
            l_institution = this.getInstitution(l_strInstitutionCode);
            
            l_lngInstitutionId = l_institution.getInstitutionId();
            l_mainAccount = getMainAccount(
                l_lngInstitutionId,
                l_strBranchCode,
                l_strAccountCode
                );
            
            //ResourceBusyException���X���[
            l_mainAccount.serializeOperationsWithWait();
        }
        catch(NotFoundException nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                nfe.getMessage(),
                nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�ڋq�����ʎ����~) <BR>
     * <BR>
     *�����ɊY������ڋq�����ʎ����~Params��ԋp����B<BR>
     * <BR>
     * �P�jDB����<BR>
     * �ȉ��̏����ɊY������f�[�^���擾����B<BR>
     *  <BR>
     * [����] <BR>
     *   �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h�@@����<BR>
     *   ���XID = �p�����[�^.���XID�@@����<BR>
     *   ����ID = �p�����[�^.����ID�@@����<BR>
     *   ����ID = �p�����[�^.����ID�@@����<BR>
     *   �K�p�J�n�N���� = �p�����[�^.�L������From <BR>
     *  <BR>
     * �擾�ł��Ȃ������ꍇ�́Anull��ԋp����B<BR>
     *  <BR>
     * �Q�j�擾�����������ʂ�ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_lngBranchId - (���XID)
     * @@param l_lngAccountId - (����ID)
     * @@param l_lngProductId - (����ID)
     * @@param l_dateFrom - (�L������From)
     * @@return �ڋq�����ʎ����~Params
     * @@throws WEB3SystemLayerException
     */
    public AccountProductOrderStopParams getAccountProductOrderStop(
        String l_strInstitutionCode,
        long l_lngBranchId,
        long l_lngAccountId,
        long l_lngProductId,
        Date l_dateFrom)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getAccountProductOrderStop(String, long, long, long, Date)";
            
        //�P�jDB����
        //  �،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h�@@����
        //  ���XID = �p�����[�^.���XID�@@����
        //  ����ID = �p�����[�^.����ID�@@����
        //  ����ID = �p�����[�^.����ID�@@����
        //  �K�p�J�n�N���� = �p�����[�^.�L������From
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" institution_code = ? ");
        l_sbWhere.append(" and branch_id = ? ");
        l_sbWhere.append(" and account_id = ? ");
        l_sbWhere.append(" and product_id = ? ");
        l_sbWhere.append(" and apply_start_date = ? ");
        Object[] l_obWhere = new Object[]
        {
            l_strInstitutionCode,
            new Long(l_lngBranchId),
            new Long(l_lngAccountId),
            new Long(l_lngProductId),
            l_dateFrom
        };
        List l_lstRecords;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                AccountProductOrderStopRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        int l_intSize = l_lstRecords.size();
        if(l_intSize  == 0)
        {
            return null;
        }
        else if(l_intSize == 1)
        {
            AccountProductOrderStopRow l_accountProductOrderStopRow = 
                (AccountProductOrderStopRow)l_lstRecords.get(0);
            return new AccountProductOrderStopParams(l_accountProductOrderStopRow);
        }
        else
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������� = " + l_intSize);
        }
        
    }
    
    /**
     * (insert�ڋq�����ʎ����~) <BR>
     * <BR>
     * �ڋq�����ʎ����~�e�[�u���Ɉꌏ�f�[�^��o�^����B<BR>
     *  <BR>
     * �P�jQueryProcessor.doInsertQuery()���R�[������B<BR>
     * [doInsertQuery()�ɃZ�b�g����p�����[�^] <BR>
     *  arg0�F�@@�p�����[�^.�ڋq�����ʎ����~Params<BR>
     * <BR>
     * @@param l_accountProductOrderStopParams - (�ڋq�����ʎ����~Params)
     * @@return void
     * @@throws WEB3SystemLayerException
     */
    public void insertAccountProductOrderStop(AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "insertAccountProductOrderStop(AccountProductOrderStopParams)";
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_accountProductOrderStopParams);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }
    
    /**
     * (get�ڋq�����ʎ����~�ꗗ) <BR>
     * <BR>
     * �����̏����ɊY������ڋq�����ʎ����~Params��<BR>
     * �ꗗ��ԋp����B<BR>
     *  <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     *  <BR>
     * [doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     *  arg0�F�@@"�ڋq�����ʎ����~�e�[�u��"(account_product_order_stop)<BR>
     *  arg1�F�@@�p�����[�^.�������������� <BR>
     *  arg2�F�@@�p�����[�^.�\�[�g����<BR>
     *  arg3�F�@@null<BR>
     *  arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     *  <BR>
     * �������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     *  <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B<BR>
     *  <BR>
     * @@param l_strWhere - (��������������) <BR>
     * @@param l_bindVars - (���������f�[�^�R���e�i) <BR>
     * @@param l_strOrderBy - (�\�[�g����) <BR>
     *    �� �w�肵�Ȃ��ꍇnull <BR>
     * @@return List
     * @@throws WEB3SystemLayerException
     */
    public List getAccountProductOrderStopList(
        String l_strWhere,
        Object[] l_bindVars,
        String l_strOrderBy)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "getAccountProductOrderStopList(String, Object[], String)";
        log.entering(STR_METHOD_NAME);
        
        List l_lstRows;    
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRows =
                l_queryProcessor.doFindAllQuery(
                    AccountProductOrderStopRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_bindVars);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
        
        int l_intSize = l_lstRows.size();
        if(l_intSize == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        List l_lstParams = new ArrayList(); 
        for(int i = 0; i < l_intSize; i++)
        {
            AccountProductOrderStopRow l_accountProductOrderStopRow =
                (AccountProductOrderStopRow)l_lstRows.get(i);
            l_lstParams.add(new AccountProductOrderStopParams(l_accountProductOrderStopRow));
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lstParams;
    }
    
    /**
     * (update�ڋq�����ʎ����~) <BR>
     * <BR>
     * �ڋq�����ʎ����~�e�[�u�����X�V����B<BR>
     *  <BR>
     * �P�jQueryProcessor.doUpdateQuery()���R�[������B<BR>
     *  <BR>
     * [doUpdateQuery()�ɃZ�b�g����p�����[�^]<BR>
     *  arg0�F�@@�p�����[�^.�ڋq�����ʎ����~Params<BR>
     *  <BR>
     * @@param l_accountProductOrderStopParams - (�ڋq�����ʎ����~Params)
     * @@return void
     * @@throws WEB3SystemLayerException
     */
    public void updateAccountProductOrderStop(AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "updateAccountProductOrderStop(AccountProductOrderStopParams)";
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_accountProductOrderStopParams);
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }
    
    /**
     * (delete�ڋq�����ʎ����~) <BR>
     * <BR>
     * �ڋq�����ʎ����~�e�[�u���̃f�[�^���폜����B<BR>
     *  <BR>
     * �P�jQueryProcessor.doDeleteAllQuery()���R�[������B<BR>
     *  <BR>
     * [doDeleteAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     *  arg0�F�@@�p�����[�^.�ڋq�����ʎ����~Params<BR>
     *  <BR>
     * @@param l_accountProductOrderStopParams - (�ڋq�����ʎ����~Params)
     * @@return void
     * @@throws WEB3SystemLayerException
     */
    public void deleteAccountProductOrderStop(AccountProductOrderStopParams l_accountProductOrderStopParams)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = 
            "deleteAccountProductOrderStop(AccountProductOrderStopParams)";
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteQuery(l_accountProductOrderStopParams.getPrimaryKey());
        }
        catch(DataException de)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                de.getMessage(),
                de);
        }
    }

    /**
     * (get�ڋq�i�S���X���j) <BR>
     * <BR>
     * �ڋq�}�X�^�i�S���X���j�e�[�u�����A<BR>
     * �����̏،���ЃR�[�h�C���X�R�[�h�C�ڋq�R�[�h<BR>
     * ���ɊY������ڋq�i�S���X���j�I�u�W�F�N�g���擾���ԋp����B<BR> 
     * <BR>
     * ���@@�ڋq�R�[�h <BR>
     * �@@�i�ڋq�R�[�h.length() > 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B<BR>
     *  <BR>
     * @@param l_strCompCode - (�،���ЃR�[�h)
     * @@param l_strBrCode - (���X�R�[�h)
     * @@param l_strCustCode - (�ڋq�R�[�h)
     * @@return MainAccountAllRow
     * @@throws WEB3BaseException
     */
    public MainAccountAllRow getMainAccountAll(
        String l_strCompCode,
        String l_strBrCode,
        String l_strCustCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccountAllRow(String, String, String)";
        log.entering(STR_METHOD_NAME);
            
        //�ڋq�}�X�^�i�S���X���j�e�[�u�����A 
        //�����̏،���ЃR�[�h�C���X�R�[�h�C�ڋq�R�[�h���ɊY������ڋq�i�S���X���j�I�u�W�F�N�g���擾���ԋp����B 
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append(" comp_code = ? ");
        l_sbWhere.append(" and br_code = ? ");
        l_sbWhere.append(" and cust_code = ? ");

        //���@@�ڋq�R�[�h�i�ڋq�R�[�h.length() > 6�j�̏ꍇ�́A�ŏ���6byte�̂ݔ�r����B 
        if (l_strCustCode != null && l_strCustCode.length() > 6)
        {
            l_strCustCode = l_strCustCode.substring(0, 6);
        }

        Object[] l_obWhere = new Object[]
        {
            l_strCompCode,
            l_strBrCode,
            l_strCustCode
        };

        List l_lstRecords = null;
        try
        { 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lstRecords = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhere.toString(),
                l_obWhere);
        }
        catch(DataException l_dexp)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dexp.getMessage(),
                l_dexp);
        }

        if (l_lstRecords != null && l_lstRecords.size() != 0)
        {
            log.exiting(STR_METHOD_NAME);
            return (MainAccountAllRow) l_lstRecords.get(0);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
    }
}
@
