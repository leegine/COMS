head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.21.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailDistributionChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���ē����[���z�M�ݒ�ύX�T�[�r�XImpl(WEB3AccInfoMailDistributionChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
*/
package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoMailDistributionChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoMailDistributionChangeCompleteResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailDistributionChangeService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (���q�l���ē����[���z�M�ݒ�ύX�T�[�r�XImpl)<BR>
 * ���q�l���ē����[���z�M�ݒ�ύX�T�[�r�X�����N���X<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoMailDistributionChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoMailDistributionChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoMailDistributionChangeServiceImpl.class);
    /**
     * @@roseuid 418F39FF01B5
     */
    public WEB3AccInfoMailDistributionChangeServiceImpl() 
    {
     
    }
    
    /**
     * �ē����[���z�M�ݒ�ύX���������{����B<BR>
     * <BR>
     * �P�j�@@submit�ύX()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C1EA300C8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AccInfoMailDistributionChangeCompleteRequest)
        {
            l_response = this.submitChange((WEB3AccInfoMailDistributionChangeCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + STR_METHOD_NAME
                );            
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�ύX)<BR>
     * �ē����[���z�M�ݒ��ύX����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i�ē����[���z�M�ݒ�ύX�jsubmit�ύX�v�Q�ƁB<BR>
     * @@param l_request - ���q�l���ē����[���z�M�ݒ�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailDistributionChangeCompleteResponse
     * @@roseuid 413C1EA300D8
     */
    protected WEB3AccInfoMailDistributionChangeCompleteResponse submitChange(WEB3AccInfoMailDistributionChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AccInfoMailDistributionChangeCompleteRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1validate( )
        l_request.validate();
        
        //1.2validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        //1.3get�ڋq( )
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        //1.4getCommonOrderValidator( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeOrderValidator l_gentradeOrderValidator = (WEB3GentradeOrderValidator)l_finApp.getCommonOrderValidator();

        //1.5validate����p�X���[�h(�ڋq, String)
        OrderValidationResult l_validationResult = l_gentradeOrderValidator.validateTradingPassword(
            getTrader(),
            getSubAccount(), 
            l_request.password
            );
        if (l_validationResult.getProcessingResult().isFailedResult())
        {
            log.debug("�`�F�b�N�G���[�̏ꍇ�͂��O���X���[����");
            throw new WEB3BusinessLayerException(
                l_validationResult.getProcessingResult().getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //1.8getDataSourceObject( )
        MainAccountParams l_mainAccountParams = 
            new MainAccountParams((MainAccountRow)l_mainAccount.getDataSourceObject());
        //1.9doUpdateQuery(PrimaryKey, String, Object[], Map)
        if (BooleanEnum.FALSE.equals(l_mainAccountParams.getInformationMailFlag()))
        {
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.TRUE);
        }
        else
        {
            l_mainAccountParams.setInformationMailFlag(BooleanEnum.FALSE);
        }
        l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //�ē����[�����M�t���O�X�V�҃R�[�h = �ڋq�}�X�^�e�[�u��.�ڋq�R�[�h
        l_mainAccountParams.setInfMailFlgLastUpdater(l_mainAccount.getAccountCode());

        //�ē����[�����M�t���O�X�V���� = ���������@@��TradingSystem.getSystemTimestamp() 
        l_mainAccountParams.setInfMailFlgUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        QueryProcessor l_queryProcessor;
        try 
        {
            l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_mainAccountParams);
        } 
        catch (DataFindException l_e) 
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }  
        catch (DataQueryException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        } 
        catch (DataNetworkException l_e) 
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        //1.8createResponse()
        WEB3AccInfoMailDistributionChangeCompleteResponse l_response = 
            (WEB3AccInfoMailDistributionChangeCompleteResponse) l_request.createResponse();
        log.exiting(STR_METHOD_NAME);    
        return l_response;
    }
}
@
