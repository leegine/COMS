head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.16;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�����������J�z�T�[�r�XImpl(WEB3FeqOrderCarryOverServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ��O�� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[       
Revesion History : 2007/07/09 �đo�g�@@���f��No.352
*/

package webbroker3.feq.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarryoverEndTypeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OrderexecutionEndTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.message.WEB3FeqOrderTransferRequest;
import webbroker3.feq.message.WEB3FeqOrderTransferResponse;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverService;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverUnitService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.OrderexecutionEndDao;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�O�����������J�z�T�[�r�XImpl)<BR>
 * �O�����������J�z�T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3FeqOrderCarryOverServiceImpl implements WEB3FeqOrderCarryOverService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqOrderCarryOverServiceImpl.class); 
     
    /**
     * @@roseuid 42CE39F60196
     */
    public WEB3FeqOrderCarryOverServiceImpl() 
    {
     
    }
    
    /**
     * �O�����������J�z�T�[�r�X���s���B<BR>
     * <BR>
     * this.submit�����J�z()���R�[������B<BR>
     * @@param l_request - (���N�G�X�g)<BR>
     * ���N�G�X�g
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B8A99403A4
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3BackResponse l_response;
        
        //this.submit�����J�z()���R�[������B

        if (l_request instanceof WEB3FeqOrderTransferRequest)
        {
            l_response = 
                submitOrderCarryOver((WEB3FeqOrderTransferRequest)l_request);   
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
    
    /**
     * (submit�����J�z)<BR>
     * �O�����������J�z�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�z�jsubmit�����J�z�v�Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �O�����������J�z���N�G�X�g�I�u�W�F�N�g
     * @@return WEB3FeqOrderTransferResponse
     * @@throws WEB3BaseException
     * @@roseuid 42B8A7E60385
     */
    protected WEB3FeqOrderTransferResponse submitOrderCarryOver(
        WEB3FeqOrderTransferRequest l_request) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "submitOrderCarryOver(" + 
            "WEB3FeqOrderTransferRequest l_request) ";
        log.entering(STR_METHOD_NAME);        
        
        //1.1 validate()
        l_request.validate();
        
        //1.2 getInstitution() �،���Ђ��擾����B 
        //[����] 
        //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�،���ЃR�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        //�g���A�J�E���g�}�l�[�W���擾����    
        WEB3GentradeAccountManager l_genAccountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeInstitution l_institution = null;
        try
        {
            l_institution = (WEB3GentradeInstitution)
                l_genAccountManager.getInstitution(l_request.institutionCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.3 get�����J�z�����敪(ProductTypeEnum, String)(�،����::get�����J�z�����敪)
        //�����J�z�����敪���擾����B 
        //[����] 
        //�����^�C�v�F�@@ProductTypeEnum.�O������ 
        //�敨�^�I�v�V�����敪�F�@@"0�FDEFAULT"
        String l_strCarryoverEndType = 
            l_institution.getCarryoverEndType(
                ProductTypeEnum.FOREIGN_EQUITY, 
                WEB3FuturesOptionDivDef.DEFAULT);
        
        //(*)�����J�z�\�`�F�b�N
        //1.4 (*)get�����J�z�����敪�̖߂�l���ȉ��̏�����
        //    �����ꂩ�ɊY������ꍇ�A�������I������B
        //    �������I���̗��R�́Alog.info()�ɂă��O�o�͂��邱�ƁB
        //    (1)�o���I�����������ς̏ꍇ(�߂�l == null�̏ꍇ)
        //    (2)���ɒ����J�z�T�[�r�X���N���ς̏ꍇ
        //�@@   (�߂�l == "2�F�����J�zAP�ďo��"�̏ꍇ)
        WEB3FeqOrderTransferResponse l_response = 
            (WEB3FeqOrderTransferResponse) l_request.createResponse();
        
        if (l_strCarryoverEndType == null || 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP.equals(l_strCarryoverEndType))
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.5 update�����J�z�����敪(String, String)
        //�o���I���e�[�u���̒����J�z�����敪��"�����J�zAP�ďo��"��update����B 
        //[����] 
        //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�،���ЃR�[�h 
        //�����J�z�����敪�F�@@"�����J�zAP�ďo��"
        this.updateOrderCarryOverStatus(
            l_request.institutionCode, 
            WEB3CarryoverEndTypeDef.CALL_CARRYOVER_AP);
        
        //1.6 get�����Ώیڋq�ꗗ(String)
        //�J�z�����ΏۂƂȂ�ڋq�̈ꗗ���擾����B 
        //[����] 
        //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�،���ЃR�[�h
        WEB3GentradeMainAccount[] l_mainAccounts = 
            this.getMainAccounts(l_request.institutionCode);
        
        boolean l_blnException = false;
        
        //1.7 (*)get�����Ώیڋq�ꗗ()�̖߂�l�̗v�f����Loop����
        
        WEB3FeqOrderCarryOverUnitService l_orderCarryOverUnitService =
            (WEB3FeqOrderCarryOverUnitService) Services.getService(
                WEB3FeqOrderCarryOverUnitService.class);  
        
        int l_intLength = 0;
        if (l_mainAccounts != null)
        {
            l_intLength = l_mainAccounts.length;
        }
        for (int i = 0; i < l_intLength; i++)
        {
            //1.7.1 exec�����J�z(�ڋq)
            //�ڋq�P�ʂŒ����J�z�������s���B 
            //[����] 
            //�ڋq�F�@@�����Ώۂ̌ڋq            
            try
            {
                //�ڋq�P�ʂ�commit���s���B
                //���V�X�e���G���[�����������ꍇ�́A���̌ڋq�̂�rollback���s���A
                //���̌ڋq�֏������ڍs����B(continue)
                l_orderCarryOverUnitService.execOrderCarryOver(l_mainAccounts[i]);            
            }
            catch(WEB3BaseException l_ex)
            {
                log.debug("__an WEB3BaseException ", l_ex);
                l_blnException = true;
            }
        }
        
        //1.8 update�����J�z�����敪(String, String)
        //�o���I���e�[�u���̒����J�z�����敪��update����B 
        //[����] 
        //�،���ЃR�[�h�F�@@���N�G�X�g�f�[�^.�،���ЃR�[�h 
        //�����J�z�����敪�F 
        //�@@[exec�����J�z()�ɂĈꌏ����O���X���[����Ȃ������ꍇ] 
        //�@@�@@"1�F������"���Z�b�g 
        // [��L�ȊO] 
        //�@@�@@"9�F�G���["���Z�b�g
        
        String l_strOrderCarryOverStatus = null;
        
        if (!l_blnException)
        {
            l_strOrderCarryOverStatus = WEB3StatusDef.DEALT;
        }
        else
        {
            l_strOrderCarryOverStatus = WEB3StatusDef.DATA_ERROR;
        }
        this.updateOrderCarryOverStatus(
            l_request.institutionCode,
            l_strOrderCarryOverStatus);

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����Ώیڋq�ꗗ)<BR>
     * �o���I�������ΏۂƂȂ钍����ێ�����<BR>
     * �ڋq�̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�j�@@�����P�ʌ���<BR>
     * �@@�O�����������}�l�[�W��.get�J�z�Ώے����P��()���R�[������B<BR>
     * <BR>
     * �@@[get�J�z�Ώے����P��()�Ɏw�肷�����]<BR>
     * �@@�@@����ID�F�@@null<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * <BR>
     * �@@null���ԋp���ꂽ�ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�ڋq�I�u�W�F�N�g�쐬<BR>
     * �@@�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B<BR>
     * �@@�Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A<BR>
     * �@@�@@�z��Ƃ��ĕԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     * @@roseuid 42B8B30A00A6
     */
    protected WEB3GentradeMainAccount[] getMainAccounts(
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccounts(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����P�ʌ��� 
        //�O�����������}�l�[�W��.get�J�z�Ώے����P��()���R�[������B 
        //[get�J�z�Ώے����P��()�Ɏw�肷�����] 
        //�@@����ID�F�@@null 
        //�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h 

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        WEB3FeqOrderManager l_feqOrderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        
        WEB3FeqOrderUnit[] l_feqOrderUnits = 
            l_feqOrderManager.getCarryOverOrderUnit(null, l_strInstitutionCode);
        
        //null���ԋp���ꂽ�ꍇ�Anull��ԋp����B 
        if (l_feqOrderUnits == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�j�@@�ڋq�I�u�W�F�N�g�쐬 
        //�Q�|�P�j�P�j�̌������ʂɂ��āA���j�[�N�Ȍ���ID�̈ꗗ���쐬����B
        int l_intLength = l_feqOrderUnits.length;
        String[] l_strAccountIds = new String[l_intLength]; 
        
        for (int i = 0; i < l_intLength; i++)
        {
            l_strAccountIds[i] = l_feqOrderUnits[i].getAccountId() + "";
        }
        Object[] l_objAccountIds = WEB3Toolkit.toUnique(l_strAccountIds);
        
        WEB3GentradeMainAccount[] l_mainAccounts = null;
        
        if (l_objAccountIds != null)
        {
            WEB3GentradeAccountManager l_genAccountManager =
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            
            l_mainAccounts = 
                new WEB3GentradeMainAccount[l_objAccountIds.length];        
            
            for (int i = 0; i < l_objAccountIds.length; i++)
            {
                try
                {
                    l_mainAccounts[i] = (WEB3GentradeMainAccount)
                        l_genAccountManager.getMainAccount(
                            Long.parseLong((String)l_objAccountIds[i]));
                }
                catch (NotFoundException l_ex)
                {
                    log.error(" �e�[�u���ɊY������f�[�^������܂���: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),l_ex);
                }
            }   
        }
        
        //�Q�|�Q�j�Q�|�P�j�ɂč쐬��������ID�̈ꗗ���A�ڋq�I�u�W�F�N�g���쐬���A 
        //�@@�z��Ƃ��ĕԋp����B         
        
        log.exiting(STR_METHOD_NAME);
        return l_mainAccounts;
    }
    
    /**
     * (update�����J�z�����敪)<BR>
     * �o���I���e�[�u��.�����J�z�����敪��<BR>
     * update���s���B<BR>
     * <BR>
     * �ȉ��̏����ɊY������o���I���e�[�u����<BR>
     * ���R�[�h���X�V����B<BR>
     * <BR>
     * [����]<BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And<BR>
     * �@@�����^�C�v = ProductTypeEnum.�O������ And<BR>
     * �@@�敨�^�I�v�V�����敪 = "DEFAULT" And<BR>
     * �@@�o���I���敪 = "DEFAULT"<BR>
     * <BR>
     * [�X�V�l]<BR>
     * �@@�����J�z�����敪 = �p�����[�^.�����J�z�����敪<BR>
     * �@@�X�V���t = ���ݎ���<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h
     * @@param l_strOrderCarryOverStatus - (�����J�z�����敪)<BR>
     * �����J�z�����敪<BR>
     * <BR>
     * 0�F�@@������<BR>
     * 1�F�@@������<BR>
     * 2�F�@@�����J�zAP�ďo��<BR>
     * 9�F�@@�G���[<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B8B43201CF
     */
    protected void updateOrderCarryOverStatus(
        String l_strInstitutionCode, 
        String l_strOrderCarryOverStatus) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateOrderCarryOverStatus(" +
            "String l_strInstitutionCode, String l_strOrderCarryOverStatus)";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̏����ɊY������o���I���e�[�u���� 
        //���R�[�h���X�V����B 

        //[����] 
        //�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h And 
        //�����^�C�v = ProductTypeEnum.�O������ And 
        //�敨�^�I�v�V�����敪 = "DEFAULT" 
        //�o���I���敪 = "DEFAULT"
        OrderexecutionEndRow l_orderExecEndRow = null;
        try
        {
            l_orderExecEndRow =                 
                OrderexecutionEndDao.findRowByInstitutionCodeProductTypeFutureOptionDivOrderexecutionEndType(
                    l_strInstitutionCode, 
                    ProductTypeEnum.FOREIGN_EQUITY, 
                    WEB3FuturesOptionDivDef.DEFAULT,
                    WEB3OrderexecutionEndTypeDef.DEFAULT);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DataQueryException", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);            
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DataNetworkException", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }   
        
        if (l_orderExecEndRow == null)
        {
            log.debug("Error in �o���I���e�[�u�� record not found");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        OrderexecutionEndParams l_orderexecEndParams = 
            new OrderexecutionEndParams(l_orderExecEndRow); 
        
        //[�X�V�l] 
        //�����J�z�����敪 = �p�����[�^.�����J�z�����敪 
        //�X�V���t = ���ݎ���
        l_orderexecEndParams.setCarryoverEndType(l_strOrderCarryOverStatus);
        l_orderexecEndParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_orderexecEndParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂��� ", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex
            );
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
