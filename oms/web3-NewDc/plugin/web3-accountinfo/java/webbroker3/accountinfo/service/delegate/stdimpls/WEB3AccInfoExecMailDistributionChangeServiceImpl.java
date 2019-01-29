head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoExecMailDistributionChangeServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�XImpl(WEB3AccInfoExecMailDistributionChangeServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���]�� (���u) �V�K�쐬
                   2005/12/26 �A���� (���u) �d�l�ύXNo.075
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
import webbroker3.accountinfo.define.WEB3AccInfoExecDivDef;
import webbroker3.accountinfo.define.WEB3AccInfoProductTypeDivDef;
import webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteRequest;
import webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoExecMailDistributionChangeService;
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
 * (���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�XImpl)<BR>
 * ���q�l�����^����胁�[���z�M�ݒ�ύX�T�[�r�X�����N���X<BR>
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3AccInfoExecMailDistributionChangeServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoExecMailDistributionChangeService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoExecMailDistributionChangeServiceImpl.class);
    /**
     * @@roseuid 418F3A0603B9
     */
    public WEB3AccInfoExecMailDistributionChangeServiceImpl() 
    {
     
    }
    
    /**
     * ���^����胁�[���z�M�ݒ�ύX���������{����B<BR>
     * <BR>
     * �P�j�@@submit�ύX()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 413C016C005B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AccInfoExecMailDistributionChangeCompleteRequest)
        {
            l_response = this.submitChange((WEB3AccInfoExecMailDistributionChangeCompleteRequest)l_request);
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
     * ���^����胁�[���z�M�ݒ��ύX����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u���q�l���i���^����胁�[���z�M�ݒ�ύX�jsubmit�ύX�v�Q�ƁB<BR>
     * @@param l_request - ���q�l�����^����胁�[���z�M�ݒ�ύX�������N�G�X�g�f�[�^�I�u�W�F�N�g
     * 
     * @@return webbroker3.accountinfo.message.WEB3AccInfoExecMailDistributionChangeCompleteResponse
     * @@roseuid 413C004803A7
     */
    protected WEB3AccInfoExecMailDistributionChangeCompleteResponse submitChange(WEB3AccInfoExecMailDistributionChangeCompleteRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "submitChange(WEB3AccInfoExecMailDistributionChangeCompleteRequest)";
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

        //1.5validate����p�X���[�h()
        OrderValidationResult l_validationResult = 
            l_gentradeOrderValidator.validateTradingPassword(
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
        
        //1.6getDataSourceObject( )
        MainAccountParams l_mainAccountParams = 
            new MainAccountParams((MainAccountRow)l_mainAccount.getDataSourceObject());
            
        //�ڋq�}�X�^���X�V
        if (WEB3AccInfoProductTypeDivDef.EQUITY.equals(l_request.productTypeDiv) 
            && WEB3AccInfoExecDivDef.EXEC_MAIL.equals(l_request.execDiv))
        {
            l_mainAccountParams.setEqExeMlFlgLastUpdater(l_mainAccount.getAccountCode());
            l_mainAccountParams.setEqExeMlFlgTimestamp(GtlUtils.getSystemTimestamp());
            if (BooleanEnum.FALSE.equals(l_mainAccountParams.getEquityOrderExeMailFlag()))
            {
                l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.TRUE);    
            }
            else
            {
                l_mainAccountParams.setEquityOrderExeMailFlag(BooleanEnum.FALSE);
            }
            
        }
        if (WEB3AccInfoProductTypeDivDef.EQUITY.equals(l_request.productTypeDiv) 
            && WEB3AccInfoExecDivDef.UNEXEC_MAIL.equals(l_request.execDiv))
        {
            l_mainAccountParams.setEqUnexeMlFlgLastUpdater(l_mainAccount.getAccountCode());
            l_mainAccountParams.setEqUnexeMlFlgTimestamp(GtlUtils.getSystemTimestamp());
            if (BooleanEnum.FALSE.equals(l_mainAccountParams.getEquityOrderUnexecMailFlag()))
            {
                l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.TRUE);    
            }
            else
            {
                l_mainAccountParams.setEquityOrderUnexecMailFlag(BooleanEnum.FALSE);
            }
            
        }
        if (WEB3AccInfoProductTypeDivDef.IFO.equals(l_request.productTypeDiv) 
            && WEB3AccInfoExecDivDef.EXEC_MAIL.equals(l_request.execDiv))
        {
            l_mainAccountParams.setIfoExeMlFlgLastUpdater(l_mainAccount.getAccountCode());
            l_mainAccountParams.setIfoExeMlFlgTimestamp(GtlUtils.getSystemTimestamp());
            if (BooleanEnum.FALSE.equals(l_mainAccountParams.getIfoOrderExecMailFlag()))
            {
                l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.TRUE);    
            }
            else
            {
                l_mainAccountParams.setIfoOrderExecMailFlag(BooleanEnum.FALSE);
            }
            
        }
        if (WEB3AccInfoProductTypeDivDef.IFO.equals(l_request.productTypeDiv) 
            && WEB3AccInfoExecDivDef.UNEXEC_MAIL.equals(l_request.execDiv))
        {
            l_mainAccountParams.setIfoUnexeMlFlgLastUpdater(l_mainAccount.getAccountCode());
            l_mainAccountParams.setIfoUnexeMlFlgTimestamp(GtlUtils.getSystemTimestamp());
            if (BooleanEnum.FALSE.equals(l_mainAccountParams.getIfoOrderUnexecMailFlag()))
            {
                l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.TRUE);    
            }
            else
            {
                l_mainAccountParams.setIfoOrderUnexecMailFlag(BooleanEnum.FALSE);
            }
            
        }
        l_mainAccountParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());
        
        //1.7doUpdateQuery(PrimaryKey, String, Object[], Map)
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
        WEB3AccInfoExecMailDistributionChangeCompleteResponse l_response = 
            (WEB3AccInfoExecMailDistributionChangeCompleteResponse) l_request.createResponse();
            
        log.exiting(STR_METHOD_NAME);        
        return l_response;
    }
}
@
