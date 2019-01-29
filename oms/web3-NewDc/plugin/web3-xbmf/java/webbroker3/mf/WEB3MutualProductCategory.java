head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualProductCategory.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�����J�e�S���[(WEB3MutualProductCategory)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/03 ���� (���u) �V�K�쐬 
Revesion History : 2008/04/29 ���u�� (���u) �d�l�ύX���f��No.598
*/

package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�����J�e�S���[)<BR>
 * ���M�����J�e�S���[�@@�G���e�B�e�B�N���X
 * 
 * @@author ����(���u)
 * @@version 1.0 
 */

public class WEB3MutualProductCategory 
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualProductCategory.class);
    
    /**
     * (���M�����J�e�S���[�s)<BR>
     * ���M�����J�e�S���[�s
     * @@roseuid 415402CD02A3
     */
    private MutualFundProductCategoryParams mutualFundProductCategoryRow;
    
    /**
     * (���M�����J�e�S���[)<BR>
     *  �R���X�g���N�^ <BR>
     */
    public WEB3MutualProductCategory()
    {
        
    }
    
    /**
     * (���M�����J�e�S���[)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * ���I�u�W�F�N�g�𐶐����A ����.���M�����J�e�S���[Row��<BR>
     * this.���M�����J�e�S���[�s�ɃZ�b�g����B
     * @@param l_mutualFundProductCategoryRow - (���M�����J�e�S���[�s)<BR>
     * ���M�����J�e�S���[Row�I�u�W�F�N�g
     * @@roseuid 415402CD02A3
     */
    public WEB3MutualProductCategory(MutualFundProductCategoryRow l_mutualFundProductCategoryRow)
    {
        this.mutualFundProductCategoryRow = 
            new MutualFundProductCategoryParams(l_mutualFundProductCategoryRow);
    }
    
    /**
     * �igetDataSourceObject�̎����j<BR> 
     * <BR>
     * this.���M�����J�e�S���[�s��ԋp����B
     * @@return Object
     * @@roseuid 4153DC980279
     */
    public Object getDataSourceObject()
    {
        return this.mutualFundProductCategoryRow;
    }
    
    /**
     * (createForUpdateParams)
     * �X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B <BR>
     * <BR>
     * �@@this.���M�����J�e�S���[�s���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬����iclone�j�B <BR>
     * �쐬�����R�s�[�����g��this.���M�����J�e�S���[�s�ɃZ�b�g����B
     * @@roseuid 4153DCBB01FC
     */
    public void createForUpdateParams()
    {
        //�X�V�s�pParams�̃N���[���s�𐶐����ĕԋp����B
        MutualFundProductCategoryParams l_params = 
            new MutualFundProductCategoryParams();
        l_params.setCategoryCode(this.mutualFundProductCategoryRow.getCategoryCode()) ;
        l_params.setCategoryName(this.mutualFundProductCategoryRow.getCategoryName());
        l_params.setCreatedTimestamp(this.mutualFundProductCategoryRow.getCreatedTimestamp());       
        l_params.setInstitutionCode(this.mutualFundProductCategoryRow.getInstitutionCode());
        l_params.setLastUpdatedTimestamp(this.mutualFundProductCategoryRow.getLastUpdatedTimestamp());
        l_params.setLastUpdater(this.mutualFundProductCategoryRow.getLastUpdater());
        l_params.setParentCategoryCode(this.mutualFundProductCategoryRow.getParentCategoryCode());
        
        //this.���M�����J�e�S���[�s���R�s�[���āA�������e�̕ʃC���X�^���X���쐬����iclone�j�B
        //�쐬�����R�s�[�����g��this.���M�����J�e�S���[�s�ɃZ�b�g����B
        this.mutualFundProductCategoryRow = l_params;
    }
    
    /**
     * (createNew���M�����J�e�S���[)<BR>
     * ���M�����J�e�S���[�I�u�W�F�N�g�𐶐����ĕԋp����B<BR>
     * <BR>
     * �P�j���M�����J�e�S���[Params�I�u�W�F�N�g�𐶐����A<BR>
     * �@@this.���M�����J�e�S���[�s�ɃZ�b�g����B<BR>
     * <BR>
     * �Q�jthis.���M�����J�e�S���[�s.set�،���ЃR�[�h( )���R�[������B<BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h=����.�،���ЃR�[�h<BR>
     * <BR>
     * �R�jthis.set�J�e�S���[�R�[�h( )���R�[������B<BR>
     * [����]<BR>
     * �@@�J�e�S���[�R�[�h=����.�J�e�S���[�R�[�h<BR>
     * <BR>
     * �S�jthis.set�J�e�S���[����( )���R�[������B<BR>
     * [����]<BR>
     * �@@�J�e�S���[����=����.�J�e�S���[����
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@param l_strCategoryCode - (�J�e�S���[�R�[�h)
     * @@param l_strCategoryName - (�J�e�S���[����)
     * @@roseuid 4153E78F0056
     */
    public void createNewMutualProductCategory(
        String l_strInstitutionCode,
        String l_strCategoryCode,
        String l_strCategoryName)
    {
        final String STR_METHOD_NAME =
            "createNewMutualProductCategory(String l_strInstitutionCode,"  +
            " String l_strCategoryCode, String l_strCategoryName)";
        log.entering(STR_METHOD_NAME);

        //�P�j���M�����J�e�S���[Params�I�u�W�F�N�g�𐶐����A 
        //  this.���M�����J�e�S���[�s�ɃZ�b�g����B
        MutualFundProductCategoryParams 
            l_mutualFundProductCategoryParams = 
                new MutualFundProductCategoryParams();
        this.mutualFundProductCategoryRow = l_mutualFundProductCategoryParams;
        
        //�Q�jthis.���M�����J�e�S���[�s.set�،���ЃR�[�h( )���R�[������B 
        //  [����] 
        //  �،���ЃR�[�h=����.�،���ЃR�[�h 
        this.mutualFundProductCategoryRow.setInstitutionCode(l_strInstitutionCode);
        
        //�R�jthis.set�J�e�S���[�R�[�h( )���R�[������B 
        //  [����] 
        //  �J�e�S���[�R�[�h=����.�J�e�S���[�R�[�h 
        this.mutualFundProductCategoryRow.setCategoryCode(l_strCategoryCode);
                
        //�S�jthis.set�J�e�S���[����( )���R�[������B 
        //  [����] 
        //  �J�e�S���[����=����.�J�e�S���[����
        l_mutualFundProductCategoryParams.setCategoryName(l_strCategoryName);   
        log.exiting(STR_METHOD_NAME) ;
    }
    
    /**
     * (saveNew���M�����J�e�S���[)<BR>
     * this.���M�����J�e�S���[�s�̓��e�ŁA�f�[�^�x�[�X���X�V����B<BR>
     * �iInsert)<BR>
     * <BR>
     * �P�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B <BR>
     * �@@�ŏI�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( ) <BR>
     * �@@�쐬���t=<BR>
     * GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l <BR>
     * <BR>
     * �@@�X�V���t=<BR>
     * GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l <BR>
     * <BR>
     * �Q�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�̓��e�ŁA <BR>
     * �@@���M�����J�e�S���[�e�[�u�����X�V�iInsert�j����B
     * @@throws WEB3BaseException
     * @@roseuid 4153E90802F6
     */
    public void saveNewMutualProductCategory() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "saveNewMutualProductCategory";
        log.entering(STR_METHOD_NAME);
        
        //�P�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B 
        //    �ŏI�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( )
        //�ŏI�X�V�҃R�[�h
        String l_strAdministratorCode = null;
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_strAdministratorCode = l_admin.getAdministratorCode(); 

        //�ŏI�X�V�҃R�[�h���Z�b�g����B 
        this.mutualFundProductCategoryRow.setLastUpdater(l_strAdministratorCode);
        
        //�쐬���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l
        this.mutualFundProductCategoryRow.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        
        //�X�V���t=GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l     
        this.mutualFundProductCategoryRow.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        
        //�Q�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�̓��e�ŁA 
        //�@@  ���M�����J�e�S���[�e�[�u�����X�V�iInsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(this.mutualFundProductCategoryRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error("���M�����J�e�S���[�e�[�u�����X�V�iInsert�j����....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("���M�����J�e�S���[�e�[�u�����X�V�iInsert�j����....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (save���M�����J�e�S���[)<BR>
     * this.���M�����J�e�S���[�s�̓��e�ŁA�f�[�^�x�[�X���X�V����B<BR>
     * �iUpdate)<BR>
     * <BR>
     * �P�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B<BR>
     * �@@�ŏI�X�V�҃R�[�h=<BR>
     * �Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h( ) <BR>
     * �@@�X�V���t =<BR>
     * GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l <BR>
     * <BR>
     * �Q�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�̓��e�ŁA<BR>
     * �@@���M�����J�e�S���[�e�[�u�����X�V(Update)����B
     * @@throws WEB3BaseException
     * @@roseuid 4153E865023A
     */
    public void saveMutualProductCategory() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "saveMutualProductCategory()";
        log.entering(STR_METHOD_NAME);
        
        //�P�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�ɁA�ȉ��̒l���Z�b�g����B 
        //    �ŏI�X�V�҃R�[�h=�Ǘ���.getInstanceFrom���O�C�����( ).get�Ǘ��҃R�[�h()
        String l_strAdministratorCode = null;
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_strAdministratorCode = l_admin.getAdministratorCode();  
            
        //�ŏI�X�V�҃R�[�h���Z�b�g����B 
        this.mutualFundProductCategoryRow.setLastUpdater(l_strAdministratorCode);
        
        //�X�V���t = GtlUtils.getTradingSystem( ).getSystemTimestamp( )�̖߂�l 
        this.mutualFundProductCategoryRow.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());
        
        //�Q�jthis.���M�����J�e�S���[�s�I�u�W�F�N�g�̓��e�ŁA 
        //    ���M�����J�e�S���[�e�[�u�����X�V(Update)����B
      
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(this.mutualFundProductCategoryRow);
        }
        catch (DataQueryException l_ex)
        {
            log.error("���M�����J�e�S���[�e�[�u�����X�V(Update)����.....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("���M�����J�e�S���[�e�[�u�����X�V(Update)����.....", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h��Ԃ��B <BR>
     * <BR>
     * this.���M�����J�e�S���[�s.get�،���ЃR�[�h( )�̖߂�l��Ԃ��B
     * @@return String
     * @@roseuid 4153DD6B0315
     */
    public String getInstitutionCode()
    {
        return this.mutualFundProductCategoryRow.getInstitutionCode();
    }
   
    /**
     * (set�J�e�S���[�R�[�h)<BR>
     * �J�e�S���[�R�[�h�̐ݒ���s���B <BR>
     * <BR>
     * 1) this.���M�����J�e�S���[�s.set�J�e�S���[�R�[�h( )���R�[������B <BR>
     * [����] <BR>
     * �@@�J�e�S���[�R�[�h=����.�J�e�S���[�R�[�h
     * @@param l_strCategoryCode - (�J�e�S���[�R�[�h)
     * @@roseuid 4153E5CE0298
     */
    public void setCategoryCode(String l_strCategoryCode)
    {
        this.mutualFundProductCategoryRow.setCategoryCode(l_strCategoryCode);
    }
    
    /**
     * (get�J�e�S���[�R�[�h)<BR>
     * �J�e�S���[�R�[�h��Ԃ��B <BR>
     * <BR>
     * this.���M�����J�e�S���[�s.get�J�e�S���[�R�[�h( )�̖߂�l��Ԃ��B
     * @@return String
     * @@roseuid 4153E59A02D6
     */
    public String getCategoryCode()
    {
        return this.mutualFundProductCategoryRow.getCategoryCode();
    }
    
    /**
     * (set�J�e�S���[����)<BR>
     * �J�e�S���[���̂̐ݒ���s���B <BR>
     * <BR>
     * 1) this.���M�����J�e�S���[�s.set�J�e�S���[����( )���R�[������B <BR>
     * [����] <BR>
     * �@@�J�e�S���[����=����.�J�e�S���[����
     * @@param l_strCategoryName - (�J�e�S���[����)
     * @@roseuid 4153E61803E0
     */
    public void setCategoryName(String l_strCategoryName)
    {
        this.mutualFundProductCategoryRow.setCategoryName(l_strCategoryName);
    }
    
    /**
     * (get�J�e�S���[����)<BR>
     * �J�e�S���[���̂�Ԃ��B <BR>
     * <BR>
     * this.���M�����J�e�S���[�s.get�J�e�S���[����( )�̖߂�l��Ԃ��B
     * @@return String
     * @@roseuid 4153E6190027
     */
    public String getCategoryName()
    {
        return this.mutualFundProductCategoryRow.getCategoryName();
    }
    
    /**
     * (set�e�J�e�S���[�R�[�h)<BR>
     * �e�J�e�S���[�R�[�h�̐ݒ���s���B <BR>
     * <BR>
     * 1) this.���M�����J�e�S���[�s.set�e�J�e�S���[�R�[�h( )���R�[������B <BR>
     * [����] <BR>
     * �@@�e�J�e�S���[�R�[�h=����.�e�J�e�S���[�R�[�h
     * @@param l_strParentCategoryCode - (�e�J�e�S���[�R�[�h)
     * @@roseuid 4153E6420259
     */
    public void setParentCategoryCode(String l_strParentCategoryCode)
    {
        this.mutualFundProductCategoryRow.setParentCategoryCode(l_strParentCategoryCode);
    }
    
    /**
     * (get�e�J�e�S���[�R�[�h)<BR>
     * �e�J�e�S���[�R�[�h��Ԃ��B <BR>
     * <BR>
     * this.���M�����J�e�S���[�s.get�e�J�e�S���[�R�[�h( )�̖߂�l��Ԃ��B
     * @@return String
     * @@roseuid 4153E6420269
     */
    public String getParentCategoryCode()
    {
        return this.mutualFundProductCategoryRow.getParentCategoryCode();
    }

    /**
     * (delete���M�����J�e�S���[)<BR>
     * �ȉ��������ɁA���Y���R�[�h�𓊐M�����J�e�S���[�e�[�u�����폜����B<BR>
     * <BR>
     * [�폜����]<BR>
     * �،���ЃR�[�h = �����D�،���ЃR�[�h and<BR>
     * �J�e�S���[�R�[�h = �����D�J�e�S���[�R�[�h<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strCategoryCode - (�J�e�S���[�R�[�h)<BR>
     * �J�e�S���[�R�[�h<BR>
     * @@throws WEB3BaseException
     */
    public void deleteMutualProductCategory(
        String l_strInstitutionCode,
        String l_strCategoryCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteMutualProductCategory(String, String)";
        log.entering(STR_METHOD_NAME);

        //�ȉ��������ɁA���Y���R�[�h�𓊐M�����J�e�S���[�e�[�u�����폜����B
        //[�폜����]
        //�،���ЃR�[�h = �����D�،���ЃR�[�h and
        //�J�e�S���[�R�[�h = �����D�J�e�S���[�R�[�h
        String l_strQueryString = " institution_code = ? and category_code = ? ";

        Object[] l_objQueryDataContainers = {l_strInstitutionCode, l_strCategoryCode};
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doDeleteAllQuery(
                MutualFundProductCategoryRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainers);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
