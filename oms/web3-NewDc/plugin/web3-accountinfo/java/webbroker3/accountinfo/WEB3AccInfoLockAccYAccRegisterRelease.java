head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoLockAccYAccRegisterRelease.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  ���b�N�qY�q�o�^����(WEB3AccInfoLockAccYAccRegisterRelease)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountinfo.data.HostLockRegistParams;
import webbroker3.accountinfo.data.HostLockRegistRow;
import webbroker3.accountinfo.define.WEB3AccInfoAttributeDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoOrderPermitDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoRegiEraseDivDef;
import webbroker3.accountinfo.define.WEB3MngLockCancelDivDef;
import webbroker3.accountinfo.define.WEB3OrderPermitDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoStopInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3YellowCustomerDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ( ���b�N�qY�q�o�^����)<BR>
 *  ���b�N�qY�q�o�^����<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoLockAccYAccRegisterRelease implements BusinessObject
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoLockAccYAccRegisterRelease.class); 
    
    /**
     * (���b�N�qY�q�o�^�����s�I�u�W�F�N�g)
     *���b�N�qY�q�o�^�����s�I�u�W�F�N�g <BR>
     *<BR>
     *�� ���b�N�qY�q�o�^����Params�N���X��DDL��莩����������B<BR>
     */
    private HostLockRegistParams hostLockRegistParams;
    
    public WEB3AccInfoLockAccYAccRegisterRelease(HostLockRegistParams l_params)
    {
        this.hostLockRegistParams = l_params;
    }
    
    /**
     * �igetDataSourceObject�̎����j <BR>
     * <BR>
     * this.���b�N�ڋq�o�^�s��ԋp����B  <BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return this.hostLockRegistParams;
    }
    
    /**
     * this.���b�N�ڋq�o�^�s���R�s�[���āA<BR>
     * �������e�̕ʃC���X�^���X���쐬����iclone�j�B <BR> 
     * �쐬�����R�s�[�����g��this.���b�N�ڋq�o�^�s�ɃZ�b�g����B<BR> 
     */
    public void createForUpdateParams()
    {
        HostLockRegistParams l_params = new HostLockRegistParams(this.hostLockRegistParams);
        this.hostLockRegistParams = l_params;
    }

    
    /**
     * (get���b�N�qY�q�o�^����)
     * �ڋq�ɊY������ŐV�̃��b�N�qY�q�o�^�������擾����B <BR>  
     * <BR> 
     * �P�j�@@���b�N�qY�q�o�^�����L���[�e�[�u������  <BR> 
     * �@@���b�N�qY�q�o�^�����L���[�e�[�u�����ȉ��̏����Ō������A<BR>  
     * �@@�ŐV�̃��b�N�qY�q�o�^�����s�I�u�W�F�N�g���擾����B <BR> 
     * <BR> 
     * �@@[��������]  <BR> 
     * �@@�f�[�^�R�[�h = �f�[�^�R�[�h <BR> 
     * �@@�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() <BR> 
     * �@@���X�R�[�h = �ڋq.getBranch().getBranchCode() <BR> 
     * �@@�����R�[�h = �ڋq.getAccountCode() <BR> 
     * �@@�X�V���t =�@@�ŐV�̍X�V���t <BR> 
     * <BR> 
     * �Q�j�@@���b�N�qY�q�o�^�����I�u�W�F�N�g�ԋp  <BR> 
     * �@@�擾�����s�I�u�W�F�N�g�ɂ��āA���b�N�qY�q�o�^�����I�u�W�F�N�g�𐶐����ԋp����B<BR>   
     * �@@�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B <BR>  
     */
    public static WEB3AccInfoLockAccYAccRegisterRelease 
        getWEB3AccLockAccYAccRecordRelease(MainAccount l_mainAccount, String l_strDateCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getWEB3AccLockAccYAccRecordRelease(MainAccount l_mainAccount, String l_strDateCode)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_strDateCode == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        List l_lisRecords = null;

        try
        {

            //�P�j�@@���b�N�qY�q�o�^�����L���[�e�[�u������

            //[����]
            //�f�[�^�R�[�h = �f�[�^�R�[�h 
            //�،���ЃR�[�h = �ڋq.getInstitution().getInstitutionCode() 
            //���X�R�[�h = �ڋq.getBranch().getBranchCode()
            //�����R�[�h = �ڋq.getAccountCode()
            //�X�V���t =�@@�ŐV�̍X�V���t 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("request_code = ?");
            l_sbQueryString.append(" and institution_code = ?");
            l_sbQueryString.append(" and branch_code = ?");
            l_sbQueryString.append(" and account_code = ?");

            Object[] l_queryDataContainer = new Object[] {
                l_strDateCode,
                l_mainAccount.getInstitution().getInstitutionCode(),
                "" + l_mainAccount.getBranch().getBranchCode(),
                "" + l_mainAccount.getAccountCode()
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                HostLockRegistRow.TYPE,
                l_sbQueryString.toString(),
                "last_updated_timestamp DESC",
                null,
                l_queryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("�\�����Ȃ��V�X�e���G���[���������܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�s���擾�ł��Ȃ������ꍇ�́Anull��ԋp����B 
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            return null;
        }

        HostLockRegistRow l_hostLockRegistRow = (HostLockRegistRow)l_lisRecords.get(0);
        
        HostLockRegistParams l_params = new HostLockRegistParams(l_hostLockRegistRow);

        log.exiting(STR_METHOD_NAME);
        //�j�@@���b�N�qY�q�o�^�����I�u�W�F�N�g�ԋp
        return new WEB3AccInfoLockAccYAccRegisterRelease(l_params);
    }
    
    /**
     * (saveNew���b�N�ڋq�o�^���� )<BR> 
     * ���b�N�qY�q�o�^�����L���[�e�[�u�����X�V(insert)����B <BR>  
     * <BR> 
     * �P�j ���b�N�qY�q�o�^�����I�u�W�F�N�g�𐶐�����B <BR> 
     * <BR> 
     * �Q�j �X�V�����Z�b�g <BR> 
     * �@@�P�j�̃��b�N�qY�q�o�^�����s�̍��ڂɒl���Z�b�g����B  <BR> 
     * <BR> 
     * �@@DB�X�V�d�l <BR> 
     * �@@�@@��~�󋵕ύX_���b�N�qY�q�o�^�����L���[�e�[�u��.xls <BR> 
     * �@@�@@�u���b�N�qY�q�o�^�����L���[�e�[�u��DB�X�V �i���b�N�q�j�v�V�[�g�Q�� <BR>  
     * �@@���c�a�X�V�d�l�̃��b�N�qY�q�o�^�����I�u�W�F�N�g�͈���.���b�N�qY�q�o�^�����I�u�W�F�N�g���w���B <BR> 
     * <BR> 
     * �R�j DB�X�V  <BR> 
     * �@@�P�j�̃��b�N�qY�q�o�^�����s�I�u�W�F�N�g�̓��e�ŁA���b�N�qY�q�o�^�����L���[�e�[�u�����X�V�iinsert�j����B <BR> 
     * <BR> 
     * �S�j HOST�֑��M <BR> 
     * �@@�P�j�̃I�u�W�F�N�g.���b�N�q�x�q�o�^�������M()�ɂāA���b�N�qY�q�o�^�����L���[��HOST�֑��M����B<BR>   
     */
    public void saveNewLockAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewLockAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_release == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME);
        }
        
        HostLockRegistParams l_params = (HostLockRegistParams)l_release.getDataSourceObject();
        //�P�j ���b�N�qY�q�o�^�����I�u�W�F�N�g�𐶐�����B
        HostLockRegistParams l_hostLokRegistParams = new HostLockRegistParams();
        
        //�Q�j �X�V�����Z�b�g�P�j�̃��b�N�qY�q�o�^�����s�̍��ڂɒl���Z�b�g����B
        //�f�[�^�R�[�h
        l_hostLokRegistParams.setRequestCode(WEB3HostRequestCodeDef.ACCINFO_LOCK_REGIST_CANCEL);
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        
        //�،���ЃR�[�h = �ڋq.�،���ЃR�[�h
        l_hostLokRegistParams.setInstitutionCode(l_strInstitutionCode);
        //���X�R�[�h = �ڋq.���X�R�[�h
        l_hostLokRegistParams.setBranchCode(l_strBranchCode);
        //�����R�[�h = �ڋq.�����R�[�h
        l_hostLokRegistParams.setAccountCode(l_mainAccount.getAccountCode());
        //���҃R�[�h = �u�����N
        l_hostLokRegistParams.setTraderCode("     ");
        WEB3HostReqOrderNumberManageService l_orderNumberManagerService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
        //���ʃR�[�h = ���ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j
        String l_strNewNumber = 
            l_orderNumberManagerService.getNewNumber(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.OTHER);
        l_hostLokRegistParams.setOrderRequestNumber(l_strNewNumber);
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //�Ǘ������J�n = �ڋq.�Ǘ����b�N�����J�n��
        l_hostLokRegistParams.setBeforeMngLockStartDate(l_mainAccountRow.getMngLockOffStartDate());
        //�Ǘ������I�� = �ڋq.�Ǘ����b�N�����I����
        l_hostLokRegistParams.setBeforeMngLockEndDate(l_mainAccountRow.getMngLockOffEndDate());
        
        //�x�X���b�N = �ڋq.�x�X���b�N
        l_hostLokRegistParams.setBeforeBranchLock(l_mainAccountRow.getBranchLock());
        //�����F�� = �ڋq.�����F��
        l_hostLokRegistParams.setBeforeOrderPermission(l_mainAccountRow.getOrderPermission());
        //�o�^���R = �ڋq.��~�󋵓o�^���R
        l_hostLokRegistParams.setBeforeLockRegistReason(l_mainAccountRow.getLockRegistrationReason());
        //Y�q = null;
        l_hostLokRegistParams.setBeforeYellowCustomer(null);
        //�Ǘ������敪 = ���b�N�qY�q�o�^�����I�u�W�F�N�g.�Ǘ������敪
        l_hostLokRegistParams.setMngLockCancelDiv(l_params.getMngLockCancelDiv());
        //�Ǘ������J�n = ���b�N�qY�q�o�^�����I�u�W�F�N�g.�Ǘ������J�n
        l_hostLokRegistParams.setMngLockCancelStartDate(l_params.getMngLockCancelStartDate());
        //�Ǘ������I�� = ���b�N�qY�q�o�^�����I�u�W�F�N�g.�Ǘ������I��
        l_hostLokRegistParams.setMngLockCancelEndDate(l_params.getMngLockCancelEndDate());
        //�x�X���b�N = ���b�N�qY�q�o�^�����I�u�W�F�N�g.�x�X���b�N
        l_hostLokRegistParams.setBranchLock(l_params.getBranchLock());
        //�����F�� = ���b�N�qY�q�o�^�����I�u�W�F�N�g.�����F��
        l_hostLokRegistParams.setOrderPermission(l_params.getOrderPermission());
        //�o�^���R = ���b�N�qY�q�o�^�����I�u�W�F�N�g.�o�^���R
        l_hostLokRegistParams.setLockRegistrationReason(l_params.getLockRegistrationReason());
        //���� = null
        l_hostLokRegistParams.setAttribute(null);
        //�o�^�����敪 = null
        l_hostLokRegistParams.setRegiEraseDiv(null);
        //�����敪 = 0�F������
        l_hostLokRegistParams.setStatus(WEB3StatusDef.NOT_DEAL);
        //�쐬���t = ��������
        l_hostLokRegistParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t = ��������
        l_hostLokRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�R�j DB�X�V  
        //�P�j�̃��b�N�qY�q�o�^�����s�I�u�W�F�N�g�̓��e�ŁA
        //���b�N�qY�q�o�^�����L���[�e�[�u�����X�V�iinsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostLokRegistParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        /*
         * �S�j HOST�֑��M 
         * �@@�P�j�̃I�u�W�F�N�g.���b�N�q�x�q�o�^�������M()�ɂāA
         * ���b�N�qY�q�o�^�����L���[��HOST�֑��M����B
         */
		WEB3AccInfoLockAccYAccRegisterRelease l_lockAccYAccRegisterRelease = new WEB3AccInfoLockAccYAccRegisterRelease(l_hostLokRegistParams);
        l_lockAccYAccRegisterRelease.lockAccYAccRegisterSend();
    }
    
    /**
     * (saveNewY�q�o�^����)<BR> 
     * ���b�N�qY�q�o�^�����L���[�e�[�u�����X�V(insert)����B<BR>  
     * <BR> 
     * �P�j ���b�N�qY�q�o�^�����I�u�W�F�N�g�𐶐�����B<BR> 
     * <BR> 
     * �Q�j �X�V�����Z�b�g<BR> 
     * �@@�P�j�̃��b�N�qY�q�o�^�����s�̍��ڂɒl���Z�b�g����B <BR> 
     * <BR> 
     * �@@DB�X�V�d�l<BR> 
     *    ��~�󋵕ύX_���b�N�qY�q�o�^�����L���[�e�[�u��.xls<BR> 
     *    �u���b�N�qY�q�o�^�����L���[�e�[�u��DB�X�V(Y�q�j�v�V�[�g�Q�� <BR> 
     * �@@���c�a�X�V�d�l�̃��b�N�qY�q�o�^�����I�u�W�F�N�g�͈���.���b�N�qY�q�o�^�����I�u�W�F�N�g���w���B<BR> 
     * <BR> 
     * �R�j DB�X�V <BR> 
     * �@@�P�j�̃��b�N�qY�q�o�^�����s�I�u�W�F�N�g�̓��e�ŁA���b�N�qY�q�o�^�����L���[�e�[�u�����X�V�iinsert�j����B<BR> 
     * <BR> 
     * �S�j HOST�֑��M<BR> 
     * �@@�P�j�̃I�u�W�F�N�g.���b�N�q�x�q�o�^�������M()�ɂāA���b�N�qY�q�o�^�����L���[��HOST�֑��M����B<BR> 
     */
    public void saveNewYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoLockAccYAccRegisterRelease l_release)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_release == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME);
        }
        HostLockRegistParams l_params = (HostLockRegistParams)l_release.getDataSourceObject();
        //�P�j ���b�N�qY�q�o�^�����I�u�W�F�N�g�𐶐�����B
        HostLockRegistParams l_hostLokRegistParams = new HostLockRegistParams();
        
        //�Q�j �X�V�����Z�b�g�P�j�̃��b�N�qY�q�o�^�����s�̍��ڂɒl���Z�b�g����B
        //�f�[�^�R�[�h
        l_hostLokRegistParams.setRequestCode(WEB3HostRequestCodeDef.ACCINFO_YELLOW_REGIST_CANCEL);
        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        
        //�،���ЃR�[�h = �ڋq.�،���ЃR�[�h
        l_hostLokRegistParams.setInstitutionCode(l_strInstitutionCode);
        //���X�R�[�h = �ڋq.���X�R�[�h
        l_hostLokRegistParams.setBranchCode(l_strBranchCode);
        //�����R�[�h = �ڋq.�����R�[�h
        l_hostLokRegistParams.setAccountCode(l_mainAccount.getAccountCode());
        //���҃R�[�h = �u�����N
        l_hostLokRegistParams.setTraderCode("     ");
        WEB3HostReqOrderNumberManageService l_orderNumberManagerService = 
            (WEB3HostReqOrderNumberManageService)Services.getService(WEB3HostReqOrderNumberManageService.class);
        //���ʃR�[�h = ���ʃR�[�h�̔ԃT�[�r�X.get�V�K���ʃR�[�h�i�j
        String l_strNewNumber = 
            l_orderNumberManagerService.getNewNumber(l_strInstitutionCode, l_strBranchCode, ProductTypeEnum.OTHER);
        l_hostLokRegistParams.setOrderRequestNumber(l_strNewNumber);
        
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        //�Ǘ������J�n = null
        l_hostLokRegistParams.setBeforeMngLockStartDate(null);
        //�Ǘ������I�� = null
        l_hostLokRegistParams.setBeforeMngLockEndDate(null);
        
        //�x�X���b�N = null
        l_hostLokRegistParams.setBeforeBranchLock(null);
        //�����F�� = null
        l_hostLokRegistParams.setBeforeOrderPermission(null);
        //�o�^���R = null
        l_hostLokRegistParams.setBeforeLockRegistReason(null);
        //Y�q = �ڋq.Y�q�敪;
        l_hostLokRegistParams.setBeforeYellowCustomer(l_mainAccountRow.getYellowCustomer());
        //�Ǘ������敪 = null
        l_hostLokRegistParams.setMngLockCancelDiv(null);
        //�Ǘ������J�n = null
        l_hostLokRegistParams.setMngLockCancelStartDate(null);
        //�Ǘ������I�� = null
        l_hostLokRegistParams.setMngLockCancelEndDate(null);
        //�x�X���b�N = null
        l_hostLokRegistParams.setBranchLock(null);
        //�����F�� = null
        l_hostLokRegistParams.setOrderPermission(null);
        //�o�^���R = null
        l_hostLokRegistParams.setLockRegistrationReason(null);
        //���� = ���b�N�qY�q�o�^�����I�u�W�F�N�g.����
        l_hostLokRegistParams.setAttribute(l_params.getAttribute());
        //�o�^�����敪 = ���b�N�qY�q�o�^�����I�u�W�F�N�g.�o�^�����敪
        l_hostLokRegistParams.setRegiEraseDiv(l_params.getRegiEraseDiv());
        //�����敪 = 0�F������
        l_hostLokRegistParams.setStatus(WEB3StatusDef.NOT_DEAL);
        //�쐬���t = ��������
        l_hostLokRegistParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
        //�X�V���t = ��������
        l_hostLokRegistParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //�R�j DB�X�V  <BR> 
        //�P�j�̃��b�N�qY�q�o�^�����s�I�u�W�F�N�g�̓��e�ŁA
        //���b�N�qY�q�o�^�����L���[�e�[�u�����X�V�iinsert�j����B
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(l_hostLokRegistParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        /*
         * �S�j HOST�֑��M <BR> 
         * �@@�P�j�̃I�u�W�F�N�g.���b�N�q�x�q�o�^�������M()�ɂāA
         * ���b�N�qY�q�o�^�����L���[��HOST�֑��M����B<BR> 
         */
		WEB3AccInfoLockAccYAccRegisterRelease l_lockAccYAccRegisterRelease = new WEB3AccInfoLockAccYAccRegisterRelease(l_hostLokRegistParams);
		l_lockAccYAccRegisterRelease.lockAccYAccRegisterSend();
    }    
    
    /**
     * (���b�N�qY�q�o�^�������M )<BR> 
     * ���b�N�qY�q�o�^�����L���[��HOST�֑��M����B<BR>  
     * <BR>
     * �V�[�P���X�}  <BR>
     * �u���b�N�qY�q�o�^�������M�v�Q�ƁB  <BR>
     */
    public void lockAccYAccRegisterSend() throws WEB3BaseException
    {
        //�s��J�ǎ��ԑтł��邩�𔻒肷��B 
        boolean l_blnIsMarketTrigger = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT);
        //is�g���K���s�i�j�̖߂�l==true�̏ꍇ�AMQ�g���K�����{����B
        String l_strInstitutionCode = null;
        String l_strRequestCode = null;
        String l_strUserData = null;
        if (l_blnIsMarketTrigger)
        {
            //�،���ЃR�[�h���擾����B
            l_strInstitutionCode = this.getInstitutionCode();
            //�f�[�^�R�[�h���擾����B
            l_strRequestCode = this.getRequestCode() + "T";
            //���[�U�f�[�^���擾����B
            l_strUserData = this.getUserData();

            /*
             * MQ�g���K�p�����[�^�𐶐�����B
             * 
             * [�R���X�g���N�^�̈���] 
             * �،���ЃR�[�h�F�@@get�،���ЃR�[�h() 
             * �f�[�^�R�[�h�F�@@get�f�[�^�R�[�h() 
             * ���[�U�f�[�^�F�@@get���[�U�f�[�^() 
             */
            WEB3MQMessageSpec l_messageSpec =
                new WEB3MQMessageSpec(
                    l_strInstitutionCode,
                    l_strRequestCode,
                    l_strUserData);
            /*
             * MQ�g���K�����{����B 
             * [send()�Ɏw�肷�����] 
             * MQ���b�Z�[�W���e�F�@@�i���������I�u�W�F�N�g�j
             */
            WEB3MQGatewayService l_gateWayService =
                (WEB3MQGatewayService) Services.getService(
                    WEB3MQGatewayService.class);
            l_gateWayService.send(l_messageSpec);
        }
    }
    
    /**
     * (get�����敪)<BR> 
     * �����敪���擾����B <BR> 
     * <BR> 
     * this.���b�N�q�x�q�o�^�����s.�����敪��ԋp����B <BR> 
     */
    public String getStatus()
    {
        return hostLockRegistParams.getStatus();
    }
    
    /**
     * (c��eateChange���b�N�qY�q�o�^����)<BR> 
     * �P�j�s�I�u�W�F�N�g�̐���  <BR> 
     * �@@�@@���b�N�qY�q�o�^����Params�I�u�W�F�N�g�𐶐�����B<BR>   
     * <BR> 
     * �@@�����b�N�qY�q�o�^����Params��DDL��莩����������B  <BR> 
     * <BR> 
     * �Q�j�v���p�e�B�Z�b�g <BR> 
     * �@@�@@��~���̈ȉ��̍��ڂɂ��āA�ڋq�s�i�ڋq.getDataSourceObject()�j�̑Ή����ڂ̒l�Ɣ�r���A <BR> 
     * �@@�@@�ύX�����������ڂ́A�P�j�Ő����������b�N�qY�q�o�^����Params�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B <BR> 
     * <BR> 
     * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�Ǘ������敪 = ��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� And�A<BR>  
     * �@@�@@�@@�@@�@@   ��~���.�Ǘ����b�N�����J�n�� == null�@@�@@And�A <BR> 
     * �@@�@@�@@�@@�@@�@@ ��~���.�Ǘ����b�N�����I���� == null�@@�̏ꍇ�A <BR> 
     * �@@�@@�@@�@@�@@�@@ 0�F���o�^ ���Z�b�g����B <BR> 
     * <BR> 
     *             ��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� And�A<BR>  
     * �@@�@@�@@�@@�@@�@@ ��~���.�Ǘ����b�N�����J�n�� != null�@@�@@And�A <BR> 
     * �@@�@@�@@�@@�@@�@@ ��~���.�Ǘ����b�N�����I���� != null�@@�̏ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@�@@ 1�F�o�^ ���Z�b�g����B<BR>  
     * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�Ǘ������J�n = ��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� �̏ꍇ�A <BR> 
     * �@@�@@�@@�@@�@@    ��~���.�Ǘ����b�N�����J�n�����Z�b�g����B <BR> 
     * �@@<BR> 
     * ���b�N�qY�q�o�^����Params.�i���M�p�j�Ǘ������I�� = ��~���.�Ǘ����b�N�����I���� != �ڋq�s.�Ǘ����b�N�����I���� �̏ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@   ��~���.�Ǘ����b�N�����I�������Z�b�g����B <BR> 
     * <BR> 
     * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�x�X���b�N = ��~���.�x�X���b�N�敪 != �ڋq�s.�x�X���b�N�̏ꍇ�A <BR> 
     * �@@�@@�@@�@@�@@   ��~���.�x�X���b�N�敪���Z�b�g����B<BR>  
     * <BR> 
     * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�����F�� = ��~���.�����F�敪 != �ڋq�s.�����F�� �̏ꍇ�A <BR> 
     * �@@�@@�@@�@@�@@   ��~���.�����F�敪(*1)���Z�b�g����B<BR>  
     * <BR> 
     * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�o�^���R = ��~���.��~�󋵓o�^���R != �ڋq�s.��~�󋵓o�^���R �̏ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@   ��~���.��~�󋵓o�^���R���Z�b�g����B <BR> 
     * <BR> 
     *   ���b�N�qY�q�o�^����Params.�i���M�p�j���� = ��~���.�x�q�敪 != �ڋq�s.Y�q�敪 �̏ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@   8�FY�q ���Z�b�g����B<BR>  
     *   ���b�N�qY�q�o�^����Params.�i���M�p�j�o�^�����敪 = ��~���.�x�q�敪 != �ڋq�s.Y�q�敪 �̏ꍇ�A<BR>  
     * �@@�@@�@@�@@�@@   ��~���.�x�q�敪(*2)���Z�b�g����B <BR> 
     * <BR> 
     *  (*1) ��~���.�����F�敪==0:�F�@@�̏ꍇ�A�P�F�F�@@���Z�b�g����B <BR> 
     *        ��~���.�����F�敪==1:��F�@@�̏ꍇ�A0�F��F�@@���Z�b�g����B<BR>  
     * <BR> 
     *  (*2) ��~���.Y�q�敪 == 1:Y�q�@@�̏ꍇ�A�P�F�o�^�@@���Z�b�g����B <BR> 
     *        ��~���.Y�q�敪 == 0:Y�q�łȂ��@@�̏ꍇ�A9�F�����@@���Z�b�g����B <BR> 
     * <BR> 
     * �R�j���b�N�qY�q�o�^�����I�u�W�F�N�g�ԋp  <BR> 
     * �@@�v���p�e�B���Z�b�g�����s�I�u�W�F�N�g���w�肵�A���b�N�qY�q�o�^�����I�u�W�F�N�g�𐶐����ԋp����B  <BR> 

     */
    public static WEB3AccInfoLockAccYAccRegisterRelease createChangeLockAccYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoStopInfo l_stopInfo)
    {
        final String STR_METHOD_NAME = "c��eateChangeLockAccYAccRegisterRelease(MainAccount l_mainAccount, WEB3AccInfoStopInfo l_stopInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null || l_stopInfo == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        /*
         * �P�j�s�I�u�W�F�N�g�̐��� 
         * �@@�@@���b�N�qY�q�o�^����Params�I�u�W�F�N�g�𐶐�����B 
         * 
         * �@@�����b�N�qY�q�o�^����Params��DDL��莩����������B 
         */
        HostLockRegistParams l_hostLokRegistParams = new HostLockRegistParams();
        
        /*�Q�j�v���p�e�B�Z�b�g 
         * �@@�@@��~���̈ȉ��̍��ڂɂ��āA�ڋq�s�i�ڋq.getDataSourceObject()�j�̑Ή����ڂ̒l�Ɣ�r���A 
         * �@@�@@�ύX�����������ڂ́A�P�j�Ő����������b�N�qY�q�o�^����Params�I�u�W�F�N�g�Ƀv���p�e�B���Z�b�g����B
         * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�Ǘ������敪 = ��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� And�A  
         * �@@�@@�@@�@@�@@   ��~���.�Ǘ����b�N�����J�n�� == null�@@�@@And�A  
         * �@@�@@�@@�@@�@@�@@ ��~���.�Ǘ����b�N�����I���� == null�@@�̏ꍇ�A  
         * �@@�@@�@@�@@�@@�@@ 0�F���o�^ ���Z�b�g����B 
         */
        MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelStartDate, l_mainAccountRow.getMngLockOffStartDate()) != 0
            && l_stopInfo.mngLockCancelStartDate == null 
            && l_stopInfo.mngLockCancelEndDate == null)
        {
            l_hostLokRegistParams.setMngLockCancelDiv(WEB3MngLockCancelDivDef.NOTREGISTER);
        }
        
        /*
         *��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� And�A
         * �@@�@@�@@�@@�@@�@@ ��~���.�Ǘ����b�N�����J�n�� != null�@@�@@And�A  
         * �@@�@@�@@�@@�@@�@@ ��~���.�Ǘ����b�N�����I���� != null�@@�̏ꍇ�A
         * �@@�@@�@@�@@�@@�@@ 1�F�o�^ ���Z�b�g����B 
         */
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelStartDate, l_mainAccountRow.getMngLockOffStartDate()) != 0
            && l_stopInfo.mngLockCancelStartDate != null 
            && l_stopInfo.mngLockCancelEndDate != null)
        {
            l_hostLokRegistParams.setMngLockCancelDiv(WEB3MngLockCancelDivDef.REGISTER);
        }
        
        /*
         * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�Ǘ������J�n = 
         *   ��~���.�Ǘ����b�N�����J�n�� != �ڋq�s.�Ǘ����b�N�����J�n�� �̏ꍇ�A 
         * �@@�@@�@@�@@��~���.�Ǘ����b�N�����J�n�����Z�b�g����B 
         */       
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelStartDate, l_mainAccountRow.getMngLockOffStartDate()) != 0)
        {
            l_hostLokRegistParams.setMngLockCancelStartDate(l_stopInfo.mngLockCancelStartDate);
        }
        else
        {
            l_hostLokRegistParams.setMngLockCancelStartDate(null);
        }
        
        /*
         * ���b�N�qY�q�o�^����Params.�i���M�p�j
         * �Ǘ������I�� = ��~���.�Ǘ����b�N�����I���� != �ڋq�s.�Ǘ����b�N�����I���� �̏ꍇ�A
         * �@@�@@�@@��~���.�Ǘ����b�N�����I�������Z�b�g����B
         */
        if (WEB3DateUtility.compare(l_stopInfo.mngLockCancelEndDate, l_mainAccountRow.getMngLockOffEndDate()) != 0)
        {
            l_hostLokRegistParams.setMngLockCancelEndDate(l_stopInfo.mngLockCancelEndDate);
        }
        else
        {
            l_hostLokRegistParams.setMngLockCancelEndDate(null);
        }
        
        /*
         * �@@���b�N�qY�q�o�^����Params.�i���M�p�j�x�X���b�N = 
         *   ��~���.�x�X���b�N�敪 != �ڋq�s.�x�X���b�N�̏ꍇ�A  
         * �@@�@@�@@��~���.�x�X���b�N�敪���Z�b�g����B
         */
        if (!l_stopInfo.branchLockDiv.equals(l_mainAccountRow.getBranchLock()))
        {
            l_hostLokRegistParams.setBranchLock(l_stopInfo.branchLockDiv);
        }
        else
        {
            l_hostLokRegistParams.setBranchLock(null);
        }
        
        /*
         * ���b�N�qY�q�o�^����Params.�i���M�p�j�����F�� = 
         * ��~���.�����F�敪 != �ڋq�s.�����F�� �̏ꍇ�A 
         * �@@�@@�@@��~���.�����F�敪(*1)���Z�b�g����B
         * (*1) ��~���.�����F�敪==0:�F�@@�̏ꍇ�A�P�F�F�@@���Z�b�g����B 
         *      ��~���.�����F�敪==1:��F�@@�̏ꍇ�A0�F��F�@@���Z�b�g����B
         */
        if (!l_stopInfo.orderPermitDiv.equals(l_mainAccountRow.getOrderPermission()))
        {
            if (WEB3OrderPermitDivDef.AUTHORIZATION.equals(l_stopInfo.orderPermitDiv))
            {
                l_hostLokRegistParams.setOrderPermission(WEB3AccInfoOrderPermitDivDef.AUTHORIZATION);
            }
            else
            {
                l_hostLokRegistParams.setOrderPermission(WEB3AccInfoOrderPermitDivDef.NOT_AUTHORIZATION);
            }

        }
        else
        {
            l_hostLokRegistParams.setOrderPermission(null);
        }
        
        /*
         * ���b�N�qY�q�o�^����Params.�i���M�p�j�o�^���R = ��~���.��~�󋵓o�^���R 
         *  != �ڋq�s.��~�󋵓o�^���R �̏ꍇ�A
         * �@@�@@��~���.��~�󋵓o�^���R���Z�b�g����B 
         */
        if (l_stopInfo.stopStateRegistReason != null)
        {
            if (!l_stopInfo.stopStateRegistReason.equals(l_mainAccountRow.getLockRegistrationReason()))
            {
                l_hostLokRegistParams.setLockRegistrationReason(l_stopInfo.stopStateRegistReason);
            }
            else
            {
                l_hostLokRegistParams.setLockRegistrationReason(null);
            }
        }
        else
        {
            l_hostLokRegistParams.setLockRegistrationReason(null);
        }

        /*
         * ���b�N�qY�q�o�^����Params.�i���M�p�j���� = 
         *  ��~���.�x�q�敪 != �ڋq�s.Y�q�敪 �̏ꍇ�A 
         * �@@�@@8�FY�q ���Z�b�g����B
         */
        if (!l_stopInfo.yellowAccountDiv.equals(l_mainAccountRow.getYellowCustomer()))
        {
            l_hostLokRegistParams.setAttribute(WEB3AccInfoAttributeDivDef.YELLOW_CUSTOMER);
        }
        else
        {
            l_hostLokRegistParams.setAttribute(null);
        }
        
        /*
         * ���b�N�qY�q�o�^����Params.�i���M�p�j�o�^�����敪 = 
         *  ��~���.�x�q�敪 != �ڋq�s.Y�q�敪 �̏ꍇ�A
         * �@@�@@�@@��~���.�x�q�敪(*2)���Z�b�g����B 
         * (*2) ��~���.Y�q�敪 == 1:Y�q�@@�̏ꍇ�A�P�F�o�^�@@���Z�b�g����B 
`        *       ��~���.Y�q�敪 == 0:Y�q�łȂ��@@�̏ꍇ�A9�F�����@@���Z�b�g����B 
         */
        if (!l_stopInfo.yellowAccountDiv.equals(l_mainAccountRow.getYellowCustomer()))
        {
            if (WEB3YellowCustomerDef.YELLOW_CUSTOMER.equals(l_stopInfo.yellowAccountDiv))
            {
                l_hostLokRegistParams.setRegiEraseDiv(WEB3AccInfoRegiEraseDivDef.REGISTER);
            }
            else
            {
                l_hostLokRegistParams.setRegiEraseDiv(WEB3AccInfoRegiEraseDivDef.ERASION);
            }
            
        }
        else
        {
            l_hostLokRegistParams.setRegiEraseDiv(null);
        }
        return new WEB3AccInfoLockAccYAccRegisterRelease(l_hostLokRegistParams);
    }
    
    /**
     * (get�،���ЃR�[�h)<BR> 
     * �،���ЃR�[�h���擾����B <BR>  
     * <BR> 
     * this.���b�N�qY�q�o�^����.�،���ЃR�[�h��ԋp����B<BR>   
     */
    public String getInstitutionCode()
    {
        return hostLockRegistParams.getInstitutionCode();
    }
    
    /**
     * (get�f�[�^�R�[�h)<BR> 
     * �f�[�^�R�[�h���擾����B <BR> 
     * <BR> 
     * this.���b�N�qY�q�o�^����.�f�[�^�R�[�h��ԋp����B <BR> 
     */
    public String getRequestCode()
    {
        return hostLockRegistParams.getRequestCode();
    }
    
    /**
     * (get���[�U�f�[�^)<BR> 
     * ���[�U�f�[�^�̈�ɃZ�b�g���鍀�ڂ��擾����B<BR>   
     * <BR> 
     * null��ԋp����B  <BR> 
     */
    public String getUserData()
    {
        return null;
    }
}
@
