head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminPermUnitCreateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ҍ������쐬�T�[�r�XImpl(WEB3AdminMCAdminPermUnitCreateServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/24 ���z (���u) �V�K�쐬 
                 : 2006/08/28 �юu�� (���u) �d�l�ύX ���f��022
*/

package webbroker3.adminmc.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;

import webbroker3.adminmc.WEB3AdminMCAdminType;
import webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.AdminPermissionPK;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��Ҍ������쐬�T�[�r�XImpl)<BR>
 * �Ǘ��Ҍ������쐬�T�[�r�X�����N���X<BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AdminMCAdminPermUnitCreateServiceImpl implements WEB3AdminMCAdminPermUnitCreateService 
{
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminMCAdminPermUnitCreateServiceImpl.class);
    
    /**
     * (update�����\�@@�\�J�e�S��)<BR>
     * �����̓��e�ŁA�Ǘ��Ҍ����e�[�u�����X�V����B<BR>
     * <BR>
     * �P�j�@@�Ǘ��Ҍ����e�[�u������ <BR>
     * �@@�ȉ��̏����ŁA�Ǘ��Ҍ����e�[�u������������B <BR>
     * �@@�擾�����s�I�u�W�F�N�g���w�肵�A�Ǘ��Ҍ���List�i�FArrayList�j�𐶐�����B<BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�������x�� = �������x��<BR>
     * <BR>
     * �Q�j�@@�������x���X�V�iInsert/Update�j<BR>
     * �@@�����\�@@�\�J�e�S��[]�̊e�v�f���ɂQ�|�P�j�`�Q�|�Q�j�����{����B<BR>
     * �@@�i�����\�@@�\�J�e�S�� == null�C�܂��́A�v�f�� == 0�̏ꍇ�́A���������{���Ȃ��j<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�����f�[�^�X�V�iDB Update�j<BR>
     * �@@�@@�����Ώۗv�f.�@@�\�J�e�S���R�[�h�ɊY������s���Ǘ��Ҍ���List�i�FArrayList�j���ɑ��݂���ꍇ�A<BR>
     * �@@�@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�@@�|�Ǘ��Ҍ���List�i�FArrayList�j���AindexOf()�C remove()���\�b�h���g�p���A�Y���v�f���폜����B <BR>
     * �@@�@@�|�����s�����L�̒ʂ�X�V�iDB Update�j����B<BR>
     * <BR>
     * �@@�@@[�Ǘ��Ҍ����e�[�u���X�V���e�iUpdate�j]<BR>
     * �@@�@@�X�V���t���O�F�@@<BR>
     * �@@�@@�@@�|�i�����Ώۗv�f.�X�V���t���O == true�j�̏ꍇ�ABooleanEnum.TRUE<BR>
     * �@@�@@�@@�|�i�����Ώۗv�f.�X�V���t���O == false�j�̏ꍇ�ABooleanEnum.FALSE<BR>
     * �@@�@@�X�V�҃R�[�h�F�@@�Ǘ��҃R�[�h<BR>
     * �@@�@@�X�V�����F�@@���������i�� TradingSystem.getSystemTimestamp()�j<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�V�K�f�[�^�}���iDB Insert�j<BR>
     * �@@�@@�����Ώۗv�f.�@@�\�J�e�S���R�[�h�ɊY������s���Ǘ��Ҍ���List�i�FArrayList�j���ɑ��݂��Ȃ��ꍇ�A<BR>
     * �@@�@@�ȉ��̏��������{����B<BR>
     * <BR>
     * �@@�@@�|���L�̒ʂ�A�V�K�s��}���iDB Insert�j����B<BR>
     * <BR>
     * �@@�@@[�Ǘ��Ҍ����e�[�u���X�V���e�iInsert�j]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�،���ЃR�[�h<BR>
     * �@@�@@�������x���F�@@�������x��<BR>
     * �@@�@@�@@�\�J�e�S���R�[�h�F�@@�����Ώۗv�f.�@@�\�J�e�S���R�[�h<BR>
     * �@@�@@�X�V���t���O�F�@@<BR>
     * �@@�@@�@@�|�i�����Ώۗv�f.�X�V���t���O == true�j�̏ꍇ�ABooleanEnum.TRUE<BR>
     * �@@�@@�@@�|�i�����Ώۗv�f.�X�V���t���O == false�j�̏ꍇ�ABooleanEnum.FALSE<BR>
     * �@@�@@�X�V�҃R�[�h�F�@@�Ǘ��҃R�[�h<BR>
     * �@@�@@�쐬�����F�@@���������i�� TradingSystem.getSystemTimestamp()�j<BR>
     * �@@�@@�X�V�����F�@@���������i�� TradingSystem.getSystemTimestamp()�j<BR>
     * <BR>
     * �R�j�@@�����Ȃ��ɕύX���ꂽ�������x���폜(Delete)<BR>
     * �@@�@@�Ǘ��Ҍ���List�i�FArrayList�j���ɖ������̍s���c���Ă���ꍇ�i�Ǘ��Ҍ���List�i�FArrayList�j.size() != 0�j�A<BR>
     * �@@�@@�c��̍s�͌����Ȃ��ɕύX���ꂽ�Ɣ��f���ADB���폜�iDB Delete�j����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@param l_operatePossibleTransactionCategory - (�����\�@@�\�J�e�S��)<BR>
     * �@@�\�J�e�S���R�[�h�ꗗ<BR>
     * <BR>
     * @@param l_strAdministratorCode - (�Ǘ��҃R�[�h)<BR>
     * �Ǘ��҃R�[�h<BR>
     * @@roseuid 4175FA16006F
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory, 
        String l_strAdministratorCode)
            throws WEB3BaseException 
    {
        String l_strMethodName = 
            "updateOperatePossibleTransactionCategory(" +
            "String l_strInstitutionCode, " +
            "String l_strPermissionLevel, " +
            "WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory, " +
            "String l_strAdministratorCode)";
        log.entering(l_strMethodName);
        
        //�P�j�@@�Ǘ��Ҍ����e�[�u������ 
        //�ȉ��̏����ŁA�Ǘ��Ҍ����e�[�u������������B                 
        //[����] 
        //�،���ЃR�[�h = �،���ЃR�[�h And
        //�������x�� = �������x��
        String l_strWhereSelect = " institution_code = ? and permission_level = ? ";
        Object[] l_bindVarsSelect = {l_strInstitutionCode, l_strPermissionLevel};
        
        //ArrayList
        List l_lisAdminPermission = new ArrayList();
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //�擾�����s�I�u�W�F�N�g���w�肵�A�Ǘ��Ҍ���List�i�FArrayList�j�𐶐�����B
            l_lisAdminPermission = 
                l_queryProcessor.doFindAllQuery(
                    AdminPermissionRow.TYPE,
                    l_strWhereSelect,
                    l_bindVarsSelect);
        }
        catch (DataFindException l_ex)
        {
            log.error("__DataFindException in select__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException in select__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException in select__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        
        if (l_lisAdminPermission == null)
        {
            log.error("Fail when �Ǘ��Ҍ����e�[�u������");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�Q�j�@@�������x���X�V�iInsert/Update�j
        //�����\�@@�\�J�e�S��[]�̊e�v�f���ɂQ�|�P�j�`�Q�|�Q�j�����{����B
        //�i�����\�@@�\�J�e�S�� == null�C�܂��́A�v�f�� == 0�̏ꍇ�́A���������{���Ȃ��j
                
        if (!(l_operatePossibleTransactionCategory == null || l_operatePossibleTransactionCategory.length == 0))
        {
            int l_rcdno = l_operatePossibleTransactionCategory.length;
            for (int i = 0; i < l_rcdno; i++)
            {               
                //get the iterator of the list
                Iterator l_iteratorAdmin = l_lisAdminPermission.iterator();    
                int  l_adminpermissionsize = l_lisAdminPermission.size();
                int  l_flg = 0;
                while (l_iteratorAdmin.hasNext())
                {
                    //�Q�|�P�j�����f�[�^�X�V�iDB Update�j                        
                    //�����Ώۗv�f.�@@�\�J�e�S���R�[�h�ɊY������s���Ǘ��Ҍ���List�i�FArrayList�j���ɑ��݂���ꍇ�A
                    //�ȉ��̏��������{����B                        
                    if (l_operatePossibleTransactionCategory[i].transactionCategory.equals(
                            ((AdminPermissionRow)l_iteratorAdmin.next()).getTransactionCategory()))
                    {                      
                        //[�Ǘ��Ҍ����e�[�u���X�V���e�iUpdate�j] 
                        AdminPermissionPK l_adminPermissionPK = 
                            new AdminPermissionPK(
                                l_strInstitutionCode,
                                l_strPermissionLevel,
                                l_operatePossibleTransactionCategory[i].transactionCategory);
                                
                        //a> �X�V���t���O�F�@@
                        //�i�����Ώۗv�f.�X�V���t���O == true�j�̏ꍇ�ABooleanEnum.TRUE
                        //�i�����Ώۗv�f.�X�V���t���O == false�j�̏ꍇ�ABooleanEnum.FALSE
                        HashMap l_hashMap = new HashMap();
                        
                        if (l_operatePossibleTransactionCategory[i].updatePermissionFlag == true)
                        {
                            l_hashMap.put("update_permission_flag", BooleanEnum.TRUE);        
                        }
                        else
                        {
                            l_hashMap.put("update_permission_flag", BooleanEnum.FALSE);        
                        }
                        
                        //b> �X�V�҃R�[�h�F�Ǘ��҃R�[�h
                        l_hashMap.put("last_updater", l_strAdministratorCode);       
                        
                        //c> �X�V�����F���������i�� TradingSystem.getSystemTimestamp()�j                        
                        //FinApp
                        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);                       
                        //Trading System 
                        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                                               
                        l_hashMap.put("update_timestamp", l_tradingSystem.getSystemTimestamp());       
                        
                        //update database
                        try
                        {
                            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                            
                            l_queryProcessor.doUpdateQuery(l_adminPermissionPK, l_hashMap);
                        }
                        catch (DataFindException l_ex)
                        {
                            log.error("__DataFindException in update__");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                        }
                        catch (DataQueryException l_ex)
                        {
                            log.error("__DataQueryException in update__");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
                        }
                        catch (DataNetworkException l_ex)
                        {
                            log.error("DataNetworkException in update");
                            throw new WEB3SystemLayerException(
                                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                        }  
                        //�Ǘ��Ҍ���List�i�FArrayList�j���AindexOf()�C remove()���\�b�h���g�p���A�Y���v�f���폜����B 
                        //�����s�����L�̒ʂ�X�V�iDB Update�j����B
                        l_iteratorAdmin.remove();      
                        break;                                                                           
                    }
                    else
                    {
                        l_flg++;
                    }
                       
                }  
                if (l_flg == l_adminpermissionsize)
                {
                    //�Q�|�Q�j�V�K�f�[�^�}���iDB Insert�j                        
                    //�����Ώۗv�f.�@@�\�J�e�S���R�[�h�ɊY������s���Ǘ��Ҍ���List�i�FArrayList�j���ɑ��݂��Ȃ��ꍇ�A
                    //�ȉ��̏��������{����B
                    //���L�̒ʂ�A�V�K�s��}���iDB Insert�j����B
                    AdminPermissionParams l_adminPermissionParams =
                        new AdminPermissionParams();
    
                    //[�Ǘ��Ҍ����e�[�u���X�V���e�iInsert�j]
                    //a> �،���ЃR�[�h�F�،���ЃR�[�h
                    l_adminPermissionParams.setInstitutionCode(l_strInstitutionCode);
                    
                    //b> �������x���F�������x��
                    l_adminPermissionParams.setPermissionLevel(l_strPermissionLevel);
                    
                    //c> �@@�\�J�e�S���R�[�h�F�����Ώۗv�f.�@@�\�J�e�S���R�[�h
                    l_adminPermissionParams.setTransactionCategory(
                        l_operatePossibleTransactionCategory[i].transactionCategory);
                    
                    //d> �X�V���t���O�F�@@
                    //�i�����Ώۗv�f.�X�V���t���O == true�j�̏ꍇ�ABooleanEnum.TRUE
                    //�i�����Ώۗv�f.�X�V���t���O == false�j�̏ꍇ�ABooleanEnum.FALSE
                    if (l_operatePossibleTransactionCategory[i].updatePermissionFlag == true)
                    {
                        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);        
                    }
                    else
                    {
                        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.FALSE);        
                    }
                    
                    //e> �X�V�҃R�[�h�F�Ǘ��҃R�[�h
                    l_adminPermissionParams.setLastUpdater(l_strAdministratorCode);
                    
                    //f> �쐬�����F���������i�� TradingSystem.getSystemTimestamp()�j
                    //FinApp
                    FinApp l_finApp = (FinApp)Services.getService(FinApp.class);                       
                    //Trading System 
                    TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
                    
                    l_adminPermissionParams.setCreatedTimestamp(l_tradingSystem.getSystemTimestamp());
                    
                    //g> �X�V�����F���������i�� TradingSystem.getSystemTimestamp()�j 
                    l_adminPermissionParams.setUpdateTimestamp(l_tradingSystem.getSystemTimestamp());    
                    
                    //insert database 
                    try
                    {
                        QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                        
                        l_queryProcessor.doInsertQuery(l_adminPermissionParams);
                    }
                    catch (DataFindException l_ex)
                    {
                        log.error("__DataFindException in insert__");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                            this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                    }
                    catch (DataQueryException l_ex)
                    {
                        log.error("__DataQueryException in insert__");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
                    }
                    catch (DataNetworkException l_ex)
                    {
                        log.error("DataNetworkException in insert__");
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
                    }                                   
                }      
            }  
    
            
        }      
 
        //�R�j�@@�����Ȃ��ɕύX���ꂽ�������x���폜(Delete)
        //�Ǘ��Ҍ���List�i�FArrayList�j���ɖ������̍s���c���Ă���ꍇ�i�Ǘ��Ҍ���List�i�FArrayList�j.size() != 0�j�A
        //�c��̍s�͌����Ȃ��ɕύX���ꂽ�Ɣ��f���ADB���폜�iDB Delete�j����B
        int l_intDeleteSize = 0;
        if (l_lisAdminPermission != null && l_lisAdminPermission.size() != 0)
        {
            l_intDeleteSize = l_lisAdminPermission.size();         
        }
         
        if (l_intDeleteSize != 0)
        {
            try
            {
                //new QueryProcessor 
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        
                //where
                StringBuffer l_sbWhereDelete = new StringBuffer();
                l_sbWhereDelete.append(" institution_code = ? ");
                l_sbWhereDelete.append(" and permission_level = ? ");
                l_sbWhereDelete.append(" and transaction_category = ? ");
        
                //value array
                Object[] l_bindVarsDelete = new Object[3];
                l_bindVarsDelete[0] = l_strInstitutionCode;
                l_bindVarsDelete[1] = l_strPermissionLevel;               

                for (int k = 0; k < l_intDeleteSize; k++)
                {
                    //get the row
                    AdminPermissionRow l_adminPermissionRow = (AdminPermissionRow)l_lisAdminPermission.get(k);
            
                    //value
                    l_bindVarsDelete[2] = l_adminPermissionRow.getTransactionCategory();
            
                    l_queryProcessor.doDeleteAllQuery(
                        AdminPermissionRow.TYPE,
                        l_sbWhereDelete.toString(),
                        l_bindVarsDelete);            
                }            
            }
            catch (DataFindException l_ex)
            {
                log.error("__DataFindException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("__DataQueryException__");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DataNetworkException");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
            }                  
        }                                
        
        log.exiting(l_strMethodName);    
    }
    
    /**
     * (create�����\�@@�\�J�e�S���ꗗ)<BR>
     * �،���ЁC�������x���ɊY������@@�\�J�e�S������z����쐬����B<BR>
     * <BR>
     * �P�j�@@�Ǘ��Ҍ����e�[�u������ <BR>
     * �@@�ȉ��̏����ŁA�Ǘ��Ҍ����e�[�u������������B <BR>
     * <BR>
     * �@@[����] <BR>
     * �@@�،���ЃR�[�h = �،���ЃR�[�h And<BR>
     * �@@�������x�� = �������x�� And<BR>
     * �@@�@@�\�J�e�S���R�[�h in �i�@@�\�J�e�S���R�[�h[0]�C�@@�\�J�e�S���R�[�h[1]�C�C�j<BR>
     * �@@�� �����̋@@�\�J�e�S���R�[�h[]�̗v�f��񋓁B�A���A�i�@@�\�J�e�S���R�[�h[] == null�j�̏ꍇ�͓��Y�������w�肵�Ȃ��B<BR>
     * <BR>
     * �@@[�擾��]<BR>
     * �@@�@@�\�J�e�S���R�[�h�C�����iasc�j<BR>
     * <BR>
     * �Q�j�@@�@@�\�J�e�S�����List�i�FArrayList�j����<BR>
     * �@@ArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�@@�@@�\�J�e�S����񐶐�<BR>
     * �@@�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�Ǘ��Ҍ���Params�j���ɁA�R�|�P�j�`�R�|�Q�j�̏������s���B <BR>
     * <BR>
     * �@@�R�|�P�j�@@�@@�\�J�e�S�����𐶐����A�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���B<BR>
     * �@@�@@�@@�\�J�e�S���R�[�h = �Ǘ��Ҍ����s.�@@�\�J�e�S���R�[�h<BR>
     * �@@�@@�X�V���t���O = (*1)<BR>
     * <BR>
     * �@@�@@(*1) �X�V���t���O<BR>
     * �@@�@@�i�Ǘ��Ҍ����s.�X�V���t���O == BooleanEnum.TRUE�j�̏ꍇ�Atrue<BR>
     * �@@�@@�i�Ǘ��Ҍ����s.�X�V���t���O == BooleanEnum.FALSE�j�̏ꍇ�Afalse<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�@@�\�J�e�S�����List�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����B<BR>
     * �@@�@@�R�|�P�j�Ő��������I�u�W�F�N�g���@@�\�J�e�S�����List�i�FArrayList�j�ɒǉ��iadd�j����B<BR>
     * <BR>
     * �S�j�@@�z��ԋp<BR>
     * �@@�@@�\�J�e�S�����List�i�FArrayList�j��z��ɕϊ��itoArray()�j���A�ԋp����B <BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strPermissionLevel - (�������x��)<BR>
     * �������x��<BR>
     * @@param l_strTransactionCategory - (�@@�\�J�e�S���R�[�h)<BR>
     * �@@�\�J�e�S���R�[�h�̔z��<BR>
     * <BR>
     * �� �w�肵�Ȃ��ꍇ��null<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit[]
     * @@roseuid 4175F793011B
     */
    public WEB3AdminMCTransactionCategoryUnit[] createOperatePossibleTransactionCategoryUnit(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String[] l_strTransactionCategory) 
            throws WEB3BaseException
    {
        String l_strMethodName = 
            "createOperatePossibleTransactionCategoryUnit(" +
            "String l_strInstitutionCode," +
            "String l_strPermissionLevel," +
            "String[] l_strTransactionCategory)";
        log.entering(l_strMethodName);
        
        //�P�j�Ǘ��Ҍ����e�[�u������ 
        //�ȉ��̏����ŁA�Ǘ��Ҍ����e�[�u������������B
        
        //�Ǘ��Ҍ���List����
        List l_lisAdminPermission = new ArrayList();       
        try
        {
            //QueryProcessor
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //[����] 
            //�،���ЃR�[�h = �،���ЃR�[�h And
            //�������x�� = �������x�� And
            //�@@�\�J�e�S���R�[�h in �i�@@�\�J�e�S���R�[�h[0]�C�@@�\�J�e�S���R�[�h[1]�C�C�j
            //�� �����̋@@�\�J�e�S���R�[�h[]�̗v�f��񋓁B�A���A�i�@@�\�J�e�S���R�[�h[] == null�j�̏ꍇ�͓��Y�������w�肵�Ȃ��B                         
            StringBuffer l_strWhereSelect = new StringBuffer();
        
            if (l_strTransactionCategory == null || l_strTransactionCategory.length == 0)
            {               
                //Where
                l_strWhereSelect.append(" institution_code = ? ");
                l_strWhereSelect.append(" and permission_level = ? "); 
            
                //Value
                Object[] l_bindVarsSelect = {l_strInstitutionCode, l_strPermissionLevel};
                
                //[�擾��]
                //�@@�\�J�e�S���R�[�h�C�����iasc�j
                String l_strOrderBySelect = " transaction_category asc ";
            
                //Select
                l_lisAdminPermission = 
                    l_queryProcessor.doFindAllQuery(
                        AdminPermissionRow.TYPE,
                        l_strWhereSelect.toString(),
                        l_strOrderBySelect,
                        null,
                        l_bindVarsSelect);
            }
            else 
            {      
                //
                StringBuffer l_strWhere = new StringBuffer();
                        
                //Where
                l_strWhereSelect.append(" institution_code = ? ");
                l_strWhereSelect.append(" and permission_level = ? ");
                
                //value Array length
                int l_intLength = l_strTransactionCategory.length + 2;
                
                //Value
                Object[] l_bindVars = new Object[l_intLength];
                l_bindVars[0] = l_strInstitutionCode;
                l_bindVars[1] = l_strPermissionLevel;
                
                //[�擾��]
                //�@@�\�J�e�S���R�[�h�C�����iasc�j
                String l_strOrderBy = " transaction_category asc ";
       
                for (int i = 0; i < l_strTransactionCategory.length; i++)
                {
                    //the first one
                    if (l_strWhere.length() == 0)
                    {
                        l_strWhere.append(" and transaction_category in ( ? ");
                    }
                    else 
                    {
                        l_strWhere.append(", ?");
                    }
                    
                    l_bindVars[i + 2] = l_strTransactionCategory[i];                                                              
                }  
                l_strWhere.append(" ) ");  
                
                l_strWhereSelect.append(l_strWhere);               
                
                //Select 
                l_lisAdminPermission = 
                    l_queryProcessor.doFindAllQuery(
                        AdminPermissionRow.TYPE,
                        l_strWhereSelect.toString(),
                        l_strOrderBy,
                        null,
                        l_bindVars);                                                    
            }                       
        }
        catch (DataFindException l_ex)
        {
            log.error("__DataFindException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + l_strMethodName, l_ex.getMessage(), l_ex);
        }                            
        
        //�Q�j�@@�\�J�e�S�����List�i�FArrayList�j����
        //ArrayList�𐶐�����B
        List l_lisCategoryUnit = new ArrayList(); 
        
        //�R�j�@@�\�J�e�S����񐶐�
        //�P�j�Ŏ擾�����e�s�I�u�W�F�N�g�i�F�Ǘ��Ҍ���Params�j���ɁA�R�|�P�j�`�R�|�Q�j�̏������s���B 
        int l_intSize = 0;
        if (l_lisAdminPermission != null && l_lisAdminPermission.size() != 0)
        {
            l_intSize = l_lisAdminPermission.size();    
        }
        
        //�@@�\�J�e�S�����𐶐�
       // WEB3AdminMCTransactionCategoryUnit l_adminMCTransactionCategoryUnit =
       //     new WEB3AdminMCTransactionCategoryUnit();
            
        for (int j = 0; j < l_intSize; j++)
        {
            //�R�|�P�j�@@�\�J�e�S�����𐶐����A�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���B
            WEB3AdminMCTransactionCategoryUnit l_adminMCTransactionCategoryUnit =
                        new WEB3AdminMCTransactionCategoryUnit();
            //get the row
            AdminPermissionRow l_adminPermissionRow = 
                (AdminPermissionRow)l_lisAdminPermission.get(j);
            
            //�@@�\�J�e�S���R�[�h = �Ǘ��Ҍ����s.�@@�\�J�e�S���R�[�h
            l_adminMCTransactionCategoryUnit.transactionCategory = 
                l_adminPermissionRow.getTransactionCategory();
                
            //�X�V���t���O = (*1)
            //(*1) �X�V���t���O
            //�i�Ǘ��Ҍ����s.�X�V���t���O == BooleanEnum.TRUE�j�̏ꍇ�Atrue
            //�i�Ǘ��Ҍ����s.�X�V���t���O == BooleanEnum.FALSE�j�̏ꍇ�Afalse
            if (BooleanEnum.TRUE.equals(l_adminPermissionRow.getUpdatePermissionFlag()))
            {
                l_adminMCTransactionCategoryUnit.updatePermissionFlag = true;        
            }
            else 
            {
                l_adminMCTransactionCategoryUnit.updatePermissionFlag = false;        
            }
        
            //�R�|�Q�j�@@�\�J�e�S�����List�i�FArrayList�j�ɃI�u�W�F�N�g��ǉ�����B
            //�R�|�P�j�Ő��������I�u�W�F�N�g���@@�\�J�e�S�����List�i�FArrayList�j�ɒǉ��iadd�j����B
            l_lisCategoryUnit.add(l_adminMCTransactionCategoryUnit);                                    
        }
        
        //�S�j�z��ԋp<BR>
        //�@@�\�J�e�S�����List�i�FArrayList�j��z��ɕϊ��itoArray()�j���A�ԋp����B 
        WEB3AdminMCTransactionCategoryUnit[] l_web3AdminMCTransactionCategoryUnit =
            new WEB3AdminMCTransactionCategoryUnit[l_lisCategoryUnit.size()];
        l_lisCategoryUnit.toArray(l_web3AdminMCTransactionCategoryUnit);
                  
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCTransactionCategoryUnit;
    }
    
    /**
     * (create�Ǘ��҃^�C�v���)<BR>
     * �Ǘ��҃^�C�v�I�u�W�F�N�g���A�Ǘ��҃^�C�v��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g���쐬����B<BR>
     * <BR>
     * �Ǘ��҃^�C�v���𐶐��C�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���ԋp����B<BR>
     * <BR>
     * �@@�������x���R�[�h = �Ǘ��҃^�C�v.get�������x��()<BR>
     * �@@�������x������ = �Ǘ��҃^�C�v.get�������x������()<BR>
     * �@@DIR�Ǘ��҃t���O = �Ǘ��҃^�C�v.getDIR�Ǘ��҃t���O() <BR>
     * �@@�S���X���t���O = �Ǘ��҃^�C�v.is�S���X����()<BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (�Ǘ��҃^�C�v)<BR>
     * �Ǘ��҃^�C�v<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit
     * @@roseuid 417722A3030D
     */
    public WEB3AdminMCAdminTypeUnit createAdminTypeUnit(WEB3AdminMCAdminType l_adminType) 
    {
        String l_strMethodName = "createAdminTypeUnit(WEB3AdminMCAdminType l_adminType)";
        log.entering(l_strMethodName);
        
        if (l_adminType == null)
        {
            log.error("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //a> �Ǘ��҃^�C�v���𐶐��C�ȉ��̒ʂ�v���p�e�B�Z�b�g���s���ԋp����B
        WEB3AdminMCAdminTypeUnit l_web3AdminMCAdminTypeUnit =
            new WEB3AdminMCAdminTypeUnit();
       
        //b> �������x���R�[�h = �Ǘ��҃^�C�v.get�������x��()
        l_web3AdminMCAdminTypeUnit.permissionLevel = 
            l_adminType.getPermissionLevel();
        
        //c> �������x������ = �Ǘ��҃^�C�v.get�������x������()
        l_web3AdminMCAdminTypeUnit.permissionLevelName =
            l_adminType.getPermissionLevelName();
        
        //d> DIR�Ǘ��҃t���O = �Ǘ��҃^�C�v.getDIR�Ǘ��҃t���O()
        l_web3AdminMCAdminTypeUnit.dirAdminFlag =
            l_adminType.getDIRAdminFlag();
        
        //e>�S���X���t���O = �Ǘ��҃^�C�v.is�S���X����()
        l_web3AdminMCAdminTypeUnit.allBranchPermissionFlag = 
            l_adminType.isAllBranchPermission();
        
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCAdminTypeUnit;
    }
    
    /**
     * (create�Ǘ��҃^�C�v���)<BR>
     * �Ǘ��҃^�C�v�I�u�W�F�N�g�̔z����A�Ǘ��҃^�C�v��񃁃b�Z�[�W�f�[�^�I�u�W�F�N�g�̔z����쐬����B<BR>
     * <BR>
     * �����̊Ǘ��҃^�C�v[]�̊e�v�f���ɁAthis.create�Ǘ��҃^�C�v���(�Ǘ��҃^�C�v)���R�[�����ĊǗ��҃^�C�v�����쐬����B<BR>
     * �߂�l��z��ɂĕԋp����B<BR>
     * <BR>
     * <BR>
     * @@param l_adminType - (�Ǘ��҃^�C�v)<BR>
     * �Ǘ��҃^�C�v�I�u�W�F�N�g�̔z��<BR>
     * <BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminTypeUnit[]
     * @@roseuid 4177493800FA
     */
    public WEB3AdminMCAdminTypeUnit[] createAdminTypeUnit(WEB3AdminMCAdminType[] l_adminType) 
    {
        String l_strMethodName = "createAdminTypeUnit(WEB3AdminMCAdminType[] l_adminType)";
        log.entering(l_strMethodName);
        
        if (l_adminType == null)
        {
            log.error("�p�����[�^�l��NULL����I");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }

        //�����̊Ǘ��҃^�C�v[]�̊e�v�f���ɁAthis.create�Ǘ��҃^�C�v���(�Ǘ��҃^�C�v)���R�[�����ĊǗ��҃^�C�v�����쐬����B
        //�߂�l��z��ɂĕԋp����B
        
        //�Ǘ��҃^�C�v���[]�𐶐�
        WEB3AdminMCAdminTypeUnit[] l_web3AdminMCAdminTypeUnit =
            new WEB3AdminMCAdminTypeUnit[l_adminType.length];
            
        for (int i = 0; i < l_adminType.length; i++)
        {
            l_web3AdminMCAdminTypeUnit[i] =
                this.createAdminTypeUnit(l_adminType[i]);        
        }
        
        log.exiting(l_strMethodName);
        
        return l_web3AdminMCAdminTypeUnit;
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strPermissionLevel
     * @@param l_operatePossibleTransactionCategory
     * @@roseuid 4198640E0222
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit l_operatePossibleTransactionCategory) 
    {
     
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strPermissionLevel
     * @@param l_strTransactionCategory
     * @@return webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit[]
     * @@roseuid 4198640E038A
     */
    public WEB3AdminMCTransactionCategoryUnit[] createOperatePossibleTransactionCategoryList(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        String[] l_strTransactionCategory) 
    {
     return null;
    }
    
    /**
     * @@param l_strInstitutionCode
     * @@param l_strPermissionLevel
     * @@param l_operatePossibleTransactionCategory
     * @@roseuid 4199C22701E4
     */
    public void updateOperatePossibleTransactionCategory(
        String l_strInstitutionCode, 
        String l_strPermissionLevel, 
        WEB3AdminMCTransactionCategoryUnit[] l_operatePossibleTransactionCategory) 
    {
     
    }
}
@
