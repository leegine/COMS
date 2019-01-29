head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderCarryOverServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����J�z�T�[�r�XImpl(WEB3EquityOrderCarryOverServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/02 �������F(SRA) �V�K�쐬
Revesion History : 2008/01/23 �g�E�N�|(���u) �d�l�ύX�@@���f��No.1247�A1290
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.dbind.ArrayListPage;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderManager;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DateRangeQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PaginationQueryParamsSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultSortKeySpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.equity.WEB3EquityCarryOverCloseInterceptor;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.message.WEB3EquityOrderCarryOverRequest;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverService;
import webbroker3.equity.service.delegate.WEB3EquityOrderCarryOverUnitService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;

/**
 * �i�����J�z�T�[�r�XImpl�j�B<BR>
 * <BR>
 * �����J�z�T�[�r�X�����N���X
 * @@version 1.0
 */
public class WEB3EquityOrderCarryOverServiceImpl
    implements WEB3EquityOrderCarryOverService
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderCarryOverServiceImpl.class);

    /**
     * @@roseuid 40B2A2870009
     */
    public WEB3EquityOrderCarryOverServiceImpl()
    {

    }

    /**
     * �����J�z�T�[�r�X���������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�����J�z�j�����J�z�v�Q�ƁB<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3BackResponse<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF8F011B
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        WEB3EquityOrderCarryOverRequest l_carryOverRequest;
        if (l_request instanceof WEB3EquityOrderCarryOverRequest)
        {
            l_carryOverRequest = (WEB3EquityOrderCarryOverRequest)l_request;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.debug("���N�G�X�g�f�[�^.�،���ЃR�[�h = " + l_carryOverRequest.institutionCode);
        
        // validate()
        l_carryOverRequest.validate();
        
        // �I�����C�����s����.set������()
		WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
			OnlineTransactionCallback l_onTransactionCallback =
				new OnlineTransactionCallback(
					l_carryOverRequest.institutionCode,
					l_carryOverRequest.rangeFrom,
					l_carryOverRequest.rangeTo);
			l_onlineRunStatus =
				(WEB3GentradeOnlineRunStatus)l_queryProcessor.doTransaction(
				QueryProcessor.TX_CREATE_NEW,
				l_onTransactionCallback);
		}
		catch (DataCallbackException l_dce)
		{
			WEB3BaseException l_wbe = (WEB3BaseException)l_dce.getDetails();
			throw l_wbe;
		}
		catch (DataException l_de)
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_de.getMessage(),
				l_de);
		}

        //getInstitution()
        Institution l_institution = null;
        try
        {
            l_institution = l_accountManager.getInstitution(l_carryOverRequest.institutionCode);
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
                    
        //get�����Ώیڋq�ꗗ()
        WEB3GentradeMainAccount[] l_mainAccounts =
            getMainAccounts(
                l_institution,
                l_carryOverRequest.rangeFrom,
                l_carryOverRequest.rangeTo);
        
        //�擾�����ڋq�I�u�W�F�N�g�����ALoop�B�ڋq����commit�^rollback����B
        boolean isCarryOverAllAccountsSuccess = true;
        WEB3EquityOrderCarryOverService l_orderCarryOverService =
            (WEB3EquityOrderCarryOverService)Services.getService(WEB3EquityOrderCarryOverService.class);
        int l_mainAccountsLen = 0;
        if (l_mainAccounts != null)
        {
            l_mainAccountsLen = l_mainAccounts.length;
        }
        log.debug("l_mainAccountsLen = " + l_mainAccountsLen);
        for (int i = 0;i < l_mainAccountsLen;i++)
        {
            try
            {
                //�ڋq�P�ʌJ�z���s()
                log.debug("�ڋq�P�ʌJ�z���s");
                l_orderCarryOverService.execCarryOverForAccount(l_mainAccounts[i]);
            }
            catch (Exception l_exp)
            {
                log.error("�ڋq�P�ʌJ�z�G���[���� :����ID[" + l_mainAccounts[i].getAccountId() + "]");
                log.error(STR_METHOD_NAME, l_exp);
                isCarryOverAllAccountsSuccess = false;
            }
        }
        log.debug("isCarryOverAllAccountsSuccess = " + isCarryOverAllAccountsSuccess);
        
        //�I�����C�����s���ʃe�[�u��update
		String l_strStatus;
        if (isCarryOverAllAccountsSuccess == true)
        {
			l_strStatus = WEB3RunStatusDivDef.DEALED;
        }
        else
        {
			l_strStatus = WEB3RunStatusDivDef.ERROR;
        }
		l_onlineRunStatus.updateRunStatusDiv(l_strStatus);
        
        log.exiting(STR_METHOD_NAME);
        return l_carryOverRequest.createResponse();
    }

    /**
     * (�ڋq�P�ʌJ�z���s)<BR>
     * �����J�z�������s���B<BR>
     * <BR>
     * �����̃V�[�P���X�́A<BR>
     * �V�[�P���X�}�u�i�����J�z�j�ڋq�P�ʌJ�z���s�v���Q�ƁB<BR>
     * @@param l_mainAccount - �ڋq<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4137CF8F0248
     */
    public void execCarryOverForAccount(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execCarryOverForAccount(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        // 1.1. lock����()
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            l_accountManager.lockAccount(
                l_mainAccount.getInstitution().getInstitutionCode(),
                l_mainAccount.getBranch().getBranchCode(),
                l_mainAccount.getAccountCode());
        }
        catch (WEB3BaseException l_wbe)
        {
            if (l_wbe.getErrorInfo() == WEB3ErrorCatalog.SYSTEM_ERROR_80076)
            {
                log.error(
                    "�������b�N�Ɏ��s���܂����B�F " +
                    "�،���ЃR�[�h=[" + l_mainAccount.getInstitution().getInstitutionCode() + "], " +
                    "���X�R�[�h=[" + l_mainAccount.getBranch().getBranchCode() + "], " +
                    "�ڋq�R�[�h=[" + l_mainAccount.getAccountCode() + "]");
            }
            throw l_wbe;
        }
        
        //1.2. get�L�������P��()
        ListPage l_lstOrderUnits = getCarryOverOrderUnit(l_mainAccount);        
        int l_intSize = 0;
        if (l_lstOrderUnits != null)
        {
            l_intSize = l_lstOrderUnits.size();
        }
        log.debug("�L�������P��(�ڋq)���F[" + l_intSize + "]");
        
        EqTypeOrderManager l_orderMgr =
            (EqTypeOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
            
        //1.3. get�L�������P��( )�Ŏ擾�����I�u�W�F�N�g�����ALoop�B
        for (int i = 0;i < l_intSize;i++)
        {
            log.debug("get�L�������P��( )�Ŏ擾�����I�u�W�F�N�g�����ALoop  Start");
            //1.3.1. ���������J�z�����C���^�Z�v�^()
            WEB3EquityCarryOverCloseInterceptor l_carrayOverCloseInterceptor =
                new WEB3EquityCarryOverCloseInterceptor(
                    WEB3ErrorReasonCodeDef.NORMAL);
            
            //1.3.2. setThreadLocalPersistenceEventInterceptor()
            l_orderMgr.setThreadLocalPersistenceEventInterceptor(
                l_carrayOverCloseInterceptor);
            
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_lstOrderUnits.get(i);
            
            //1.3.3. expireOrder()
            ProcessingResult l_processingResult = l_orderMgr.expireOrder(l_orderUnit.getOrderId());
            if (l_processingResult.isFailedResult())
            {
                ErrorInfo l_errorInfo = l_processingResult.getErrorInfo();
                WEB3BaseException l_baseException =
                    new WEB3BaseException(
                        l_errorInfo,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_errorInfo.getErrorMessage());
                throw l_baseException;
            }
            log.debug("get�L�������P��( )�Ŏ擾�����I�u�W�F�N�g�����ALoop  End");
        }
        
        //1.4. get�L�������P��( )�Ŏ擾�����I�u�W�F�N�g�����ALoop�B
        WEB3EquityOrderCarryOverUnitService l_orderCarrayOverUnitService =
            (WEB3EquityOrderCarryOverUnitService)Services.getService(
                WEB3EquityOrderCarryOverUnitService.class);
        Timestamp l_tsBizDate = new Timestamp(GtlUtils.getTradingSystem().getBizDate().getTime());
        for (int i = 0;i < l_intSize;i++)
        {
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_lstOrderUnits.get(i);
            if (l_orderUnit.getExpirationTimestamp().compareTo(l_tsBizDate) <= 0)
            {
                continue;
            }
            //1.4.1. insert�J�z����()
            log.debug("insert�J�z����(�����P��) orderUnitId = " + l_orderUnit.getOrderUnitId());
            log.debug("insert�J�z����(�����P��)���s");
            l_orderCarrayOverUnitService.insertCarryOverOrder(l_orderUnit);
        }
        
        //1.5. getSubAccount()
		WEB3GentradeSubAccount l_subAccount;
        SubAccountTypeEnum l_subAccountType;
        if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
			l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
        }
        else
        {
			l_subAccountType = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
        }
        
        try 
        {
			l_subAccount = 
				(WEB3GentradeSubAccount)l_accountManager.getSubAccount(
					l_mainAccount.getAccountId(), 
					l_subAccountType);
		} 
		catch (NotFoundException e) 
		{
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				getClass().getName() + "." + STR_METHOD_NAME,
				e.getMessage(),
				e);
		}
		
		//1.6. �]�͍Čv�Z()
		WEB3TPTradingPowerService l_tpTradingPowerService = 
			(WEB3TPTradingPowerService)Services.getService(
				WEB3TPTradingPowerService.class);
		l_tpTradingPowerService.reCalcTradingPower(l_subAccount);
        		
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get�L�������P��)<BR>
     * �����Ŏw�肵���ڋq�́A�L���Ȓ����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �P�j�@@���Y�ڋq�́A���E�M�p�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �P�|�P�j�@@���Y�ڋq�́A"�����������"�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * �@@�@@�@@�@@�@@�i�g�����������}�l�[�W��.getOpenOrderUnits( )���R�[���j<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��getOpenOrderUnits( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̌ڋq.getSubAccount(SubAccountTypeEnum."�����������"<BR>
     * �@@�@@�@@�@@�@@�@@�iEQUITY_SUB_ACCOUNT�j)�̖߂�l�I�u�W�F�N�g�@@<BR>
     * �@@�@@�@@�����^�C�v�F�@@ProductTypeEnum."����"�iEQUITY�j�@@<BR>
     * �@@�@@�@@���t�͈́F�@@�S�āiALL_DATE_RANGES�j<BR>
     * �@@�@@�@@�y�[�W�͈́F�@@�S�y�[�W�iALL_PAGES�j<BR>
     * �@@�@@�@@�\�[�g�L�[�F�@@�����P��.�󒍓���<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �P�|�Q�j�@@���Y�ڋq���M�p�q�ł��邩�ǂ������`�F�b�N����B<BR>
     * <BR>
     * �@@�@@�@@�����̌ڋq.is�M�p�����J��("0�FDEFAULT")���R�[������B<BR>
     * <BR>
     * �P�|�R�j�@@�ڋq.is�M�p�����J��("0�FDEFAULT")==true�̏ꍇ�̂݁A<BR>
     * �@@�@@�@@�@@�@@���Y�ڋq�́A"�M�p�������"�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * �@@�@@�@@�@@�@@�i�g�����������}�l�[�W��.getOpenOrderUnits( )���R�[���j<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@��getOpenOrderUnits( )�F�����ݒ�d�l��<BR>
     * <BR>
     * �@@�@@�@@�⏕�����F�@@�����̌ڋq.getSubAccount(SubAccountTypeEnum."�M�p�������"<BR>
     * �@@�@@�@@�@@�@@�@@�iEQUITY_MARGIN_SUB_ACCOUNT�j)�̖߂�l�I�u�W�F�N�g�@@<BR>
     * �@@�@@�@@�����^�C�v�F�@@ProductTypeEnum."����"�iEQUITY�j�@@<BR>
     * �@@�@@�@@���t�͈́F�@@�S�āiALL_DATE_RANGES�j<BR>
     * �@@�@@�@@�y�[�W�͈́F�@@�S�y�[�W�iALL_PAGES�j<BR>
     * �@@�@@�@@�\�[�g�L�[�F�@@�����P��.�󒍓���<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �P�|�P�j�y�тP�|�R�j�̎擾����ListPage�𗼕��Ƃ��g�p����B<BR>
     * <BR>
     * �Q�j�@@�J�z�ΏۂƂȂ�s��ID�̃��X�g���쐬����B<BR>
     * <BR>
     * �Q�|�P�j�i���X�s��ʁj�戵����.get�i���X�s��ʁj�戵����()���R�[�����āA <BR>
     * �@@�@@�@@�戵�����I�u�W�F�N�g�̔z����擾����B<BR>
     * <BR>
     * �@@�@@�@@��get�i���X�s��ʁj�戵����()�ɃZ�b�g���������<BR>
     * �@@�@@�@@�@@���X:�@@�����̌ڋq.getBranch()<BR>
     * <BR>
     * �Q�|�Q�j�擾�����戵�����I�u�W�F�N�g�̑S�v�f�ɂ��Ĉȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�i���X�s��ʁj�戵����.get�s��R�[�h()�ɂ��s��R�[�h���擾����B<BR>
     * �@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[�����A<BR>
     * �@@�@@�@@�擾�����s��I�u�W�F�N�g�̎s��ID�����X�g�ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@��get�s��()�ɃZ�b�g��������� <BR>
     * �@@�@@�@@�@@�،���ЃR�[�h:�@@�����̌ڋq.getInstitution().getInstitutionCode()<BR>
     * �@@�@@�@@�@@�s��R�[�h:�@@�i���X�s��ʁj�戵����.get�s��R�[�h()<BR>
     * <BR>
     * �R�j�@@�J�z�ΏۂƂȂ�Ȃ������P�ʃI�u�W�F�N�g�����O����B<BR>
     * <BR>
     * �R�|�P�j�@@�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g�ɑ΂��A<BR>
     * �@@�@@�@@�s��ǌ�ɓo�^�����V�K�����łȂ����ǂ����̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�s��ǌ�ɓo�^�����V�K�����Ɣ��肵���ꍇ�́A<BR>
     * �@@�@@�@@�P�j�̎擾���ʂ��瓖�Y�����P�ʃI�u�W�F�N�g���폜����B<BR>
     * <BR>
     * �@@�@@�@@���s��ǌ�ɓo�^�����V�K�����̃`�F�b�N��<BR>
     * �@@�@@�@@�����P��.������ > �Ɩ����t(*1) �̏ꍇ�́A<BR>
     * �@@�@@�@@�s��ǌ�ɓo�^�����V�K�����ł���Ɣ��肷��B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A�s��ǌ�ɓo�^�����V�K�����łȂ��Ɣ��肷��B<BR>
     * <BR>
     * �R�|�Q�j�@@�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g�ɑ΂��A<BR>
     * �@@�@@�@@�~�j�������łȂ����ǂ����̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�~�j�������Ɣ��肵���ꍇ�́A�J�z�ΏۂƂ͂Ȃ�Ȃ����߁A<BR>
     * �@@�@@�@@�P�j�̎擾���ʂ��瓖�Y�����P�ʃI�u�W�F�N�g���폜����B<BR>
     * <BR>
     * �@@�@@�@@���~�j�������̃`�F�b�N��<BR>
     * �@@�@@�@@�����P��.������� == �i"�����~�j��������" or "�����~�j��������"�j�̏ꍇ�́A<BR>
     * �@@�@@�@@�~�j�������ł���Ɣ��肷��B<BR>
     * �@@�@@�@@��L�ȊO�̏ꍇ�́A�~�j�������łȂ��Ɣ��肷��B<BR>
     * <BR>
     * �R�|�R�j�@@�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g�ɑ΂��A<BR>
     * �@@�@@�@@�J�z�Ώێs�ꂩ�ǂ����̃`�F�b�N���s���B <BR>
     * �@@�@@�@@�����P��.�s��ID���@@�Q�j�ō쐬�����J�z�ΏۂƂȂ�s��ID���X�g�Ɋ܂܂�Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�P�j�̎擾���ʂ��瓖�Y�����P�ʃI�u�W�F�N�g���폜����B<BR>
     * <BR>
     * �S�j�@@�쐬����ListPage��Ԃ��B�Y���f�[�^�Ȃ��̏ꍇ��null��Ԃ��B<BR>
     * <BR>
     * (*1)�Ɩ����t�́AGtlUtils.getTradingSystem( ).getBizDate( ) �Ŏ擾����B<BR>
     * <BR>
     * @@param l_mainAccount - �ڋq<BR>
     * @@return ListPage
     * @@roseuid 4137CF8F02AC
     */
    public ListPage getCarryOverOrderUnit(WEB3GentradeMainAccount l_mainAccount)
        throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME =
            "getCarryOverOrderUnit(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
        
        List l_lisOrderUnit = new ArrayList();
        try
        {
            // �P�j�@@���Y�ڋq�́A���E�M�p�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
            // �P�|�P�j���Y�ڋq�́A"�����������"�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            DefaultSortKeySpec l_sortKeySpec = new DefaultSortKeySpec("received_date_time");
            ListPage l_lstEquityOrderUnit =
                l_orderMgr.getOpenOrderUnits(
                    l_subAccount,
                    ProductTypeEnum.EQUITY,
                    DateRangeQueryParamsSpec.ALL_DATE_RANGES,
                    PaginationQueryParamsSpec.ALL_PAGES,
                    l_sortKeySpec);
            log.debug("l_lstEquityOrderUnit.size() = " + l_lstEquityOrderUnit.size());
            l_lisOrderUnit.addAll(l_lstEquityOrderUnit);
            
            // �P�|�Q�j�@@���Y�ڋq���M�p�q�ł��邩�ǂ������`�F�b�N����B
            if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                // �P�|�R�j�@@�ڋq.is�M�p�����J��("0�FDEFAULT")==true�̏ꍇ��
                // �@@�@@�@@�@@�@@���Y�ڋq�́A"�M�p�������"�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B
                l_subAccount =
                    l_mainAccount.getSubAccount(
                        SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
                ListPage l_lstMarginOrderUnit =
                    l_orderMgr.getOpenOrderUnits(
                        l_subAccount,
                        ProductTypeEnum.EQUITY,
                        DateRangeQueryParamsSpec.ALL_DATE_RANGES,
                        PaginationQueryParamsSpec.ALL_PAGES,
                        l_sortKeySpec);
                log.debug("l_lstMarginOrderUnit.size() = " + l_lstMarginOrderUnit.size());
                // �P�|�P�j�y�тP�|�R�j�̎擾����ListPage�𗼕��Ƃ��g�p����B
                l_lisOrderUnit.addAll(l_lstMarginOrderUnit);
            }

            //�Q�j�@@�J�z�ΏۂƂȂ�s��ID�̃��X�g���쐬����B
            //�Q�|�P�j�i���X�s��ʁj�戵����.get�i���X�s��ʁj�戵����()���R�[�����āA
            //�戵�����I�u�W�F�N�g�̔z����擾����B
            WEB3GentradeBranchMarketDealtCond[] l_branchMarketDealtConds =
                WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(
                    (WEB3GentradeBranch)l_mainAccount.getBranch());

            //�g�����Z�I�u�W�F�N�g�}�l�[�W�����擾����B
            WEB3GentradeFinObjectManager l_objectManager =
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

            int l_intLength = l_branchMarketDealtConds.length;
            String l_strMarketCode = null;
            String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
            Market l_market = null;
            List l_lisMarketIds = new ArrayList();
            //�Q�|�Q�j�擾�����戵�����I�u�W�F�N�g�̑S�v�f�ɂ��Ĉȉ��̏������s���B
            for (int i = 0; i < l_intLength; i++)
            {
                //�i���X�s��ʁj�戵����.get�s��R�[�h()�ɂ��s��R�[�h���擾����B
                l_strMarketCode = l_branchMarketDealtConds[i].getMarketCode();

                //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[����
                l_market = l_objectManager.getMarket(l_strInstitutionCode, l_strMarketCode);

                //�擾�����s��I�u�W�F�N�g�̎s��ID�����X�g�ɒǉ�����B
                l_lisMarketIds.add(new Long(l_market.getMarketId()));
            }

            // �R�|�P�j�@@�P�j�Ŏ擾���������P�ʃI�u�W�F�N�g�ɑ΂��A�s��ǌ�ɓo�^�����V�K�����łȂ����ǂ�����
            // �@@�@@�@@�`�F�b�N���s���B
            // �@@�@@�@@�s��ǌ�ɓo�^�����V�K�����Ɣ��肵���ꍇ�́A�J�z�ΏۂƂ͂Ȃ�Ȃ����߁A
            // �@@�@@�@@�P�j�̎擾���ʂ��瓖�Y�����P�ʃI�u�W�F�N�g���폜����B
            log.debug("�폜�O:l_lisOrderUnit.size() = " + l_lisOrderUnit.size());
            Iterator l_orderUnitIterator = l_lisOrderUnit.listIterator();
            Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            String l_strBizDate = GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBizDate);
            while (l_orderUnitIterator.hasNext())
            {
                EqTypeOrderUnit l_orderUnit =
                    (EqTypeOrderUnit)l_orderUnitIterator.next();
                EqtypeOrderUnitRow l_orderUnitRow =
                    (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
                // �s��ǌ�ɓo�^�����V�K�����̃`�F�b�N
                if (l_orderUnitRow.getBizDate().compareTo(l_strBizDate) > 0)
                {
                    l_orderUnitIterator.remove();
                }
                // �~�j�������̃`�F�b�N
                else if (OrderTypeEnum.MINI_STOCK_BUY.equals(l_orderUnitRow.getOrderType()) ||
                          OrderTypeEnum.MINI_STOCK_SELL.equals(l_orderUnitRow.getOrderType()))
                {
                    l_orderUnitIterator.remove();
                }
                //�����P��.�s��ID���@@�Q�j�ō쐬�����J�z�ΏۂƂȂ�s��ID���X�g�Ɋ܂܂�Ȃ��ꍇ
                else if (!l_lisMarketIds.contains(new Long(l_orderUnitRow.getMarketId())))
                {
                    l_orderUnitIterator.remove();
                }
            }
            log.debug("�폜��:l_lisOrderUnit.size() = " + l_lisOrderUnit.size());
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        // �S�j�@@�쐬����ListPage��Ԃ��B�Y���f�[�^�Ȃ��̏ꍇ��null��Ԃ�
        if (l_lisOrderUnit.isEmpty())
        {
            return null;
        }
        else
        {
            return new ArrayListPage(
                l_lisOrderUnit,
                PaginationQueryParamsSpec.ALL_PAGES.getPageSize(),
                PaginationQueryParamsSpec.ALL_PAGES.getPageNumber());
        }
    }

    /**
     * (get�����Ώیڋq�ꗗ)<BR>
     * <BR>
     * �L�����������ڋq�̈ꗗ���쐬���Ԃ��B<BR>
     * <BR>
     * �P�j�@@�s��ID�ꗗ���쐬����B  <BR>
     * <BR>
     * �@@�@@�P�|�P�j�i���X�s��ʁj�戵����.get�i���X�s��ʁj�戵����( )���R�[�����āA<BR>
     * �@@�@@�@@�@@�@@�戵�����I�u�W�F�N�g�̔z����擾����B  <BR>
     * <BR>
     * �@@�@@�@@�@@�@@��get�i���X�s��ʁj�戵����( )�ɃZ�b�g���������  <BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h:�@@�v���p�e�B�̏،���ЃR�[�h  <BR>
     * <BR>
     * �@@�@@�P�|�Q�j�擾�����戵�����I�u�W�F�N�g�̑S�v�f�ɂ��Ĉȉ��̏������s���B<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�i���X�s��ʁj�戵����.get�s��R�[�h( )�ɂ��s��R�[�h���擾����B<BR>
     * �@@�@@�@@�@@�@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��( )���R�[�����A  <BR>
     * �@@�@@�@@�@@�@@�擾�����s��I�u�W�F�N�g�̎s��ID�����X�g�ɒǉ�����B  <BR>
     * �@@�@@�@@�@@�@@���������A���Ɏs��ID�����X�g�ɑ��݂���ꍇ�͒ǉ����Ȃ��B <BR>
     * <BR>
     * �@@�@@�@@�@@�@@[get�s��( )�̈����ݒ�] <BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h:�@@�v���p�e�B�̏،���ЃR�[�h   <BR>
     * �@@�@@�@@�@@�@@�@@�s��R�[�h:�@@�i���X�s��ʁj�戵����.get�s��R�[�h( )<BR>
     * <BR>
     * �Q�j�@@���E�M�p�̗L�������̒����P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * <BR>
     * �@@�@@�@@�N�G���v���Z�b�T���g�p���A�ȉ��̏����ɍ��v���钍���P�ʃI�u�W�F�N�g��S�Ď擾����B<BR>
     * �@@�@@�@@�i����ID�����w��j<BR>
     * �@@�@@�@@�������������́A�L���Ȍ����E�M�p�������擾����B<BR>
     * <BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * �@@�@@�@@�����o������<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@���XID in �����̏،����.getBranches( )�̖߂�l�̂����ꂩ<BR>
     * �@@�@@�@@���@@�����^�C�v == "����"<BR>
     * �@@�@@�@@���@@������� != �i"�����~�j��������"�A"�����~�j��������"�j<BR>
     * �@@�@@�@@���@@�����L����� == "�I�[�v��"<BR>
     * �@@�@@�@@���@@������From����ID <= ����ID<BR>
     * �@@�@@�@@���@@����ID <= ������To����ID<BR>
     * �@@�@@�@@���@@������ == �Ɩ����t(*1)<BR>
     * �@@�@@�@@���@@�s��ID in �P�j�Ŏ擾�����s��ID�ꗗ<BR>
     * <BR>
     * �@@�@@�@@(*1)�Ɩ����t�́AGtlUtils.getTradingSystem( ).getBizDate( ) �Ŏ擾����B<BR>
     * �@@�@@�@@----------------------------------------------------------<BR>
     * <BR>
     * �R�j�@@�ڋq�I�u�W�F�N�g�̈ꗗ���쐬����B<BR>
     * <BR>
     * �@@�@@�@@�܂��A�Q�j�Ŏ擾���������P�ʃI�u�W�F�N�g.����ID�̈�ӂȈꗗ���쐬����B<BR>
     * �@@�@@�@@�e����ID���ɁA�Y������ڋq�I�u�W�F�N�g���擾���A<BR>
     * �@@�@@�@@�ԋp�p�̌ڋq�I�u�W�F�N�g�̈ꗗ�ɒǉ����Ă����B<BR>
     * <BR>
     * �S�j�@@�쐬�����ڋq�I�u�W�F�N�g�̈ꗗ��ԋp����B<BR>
     * �@@�@@�@@���Y���f�[�^�Ȃ�����null��ԋp����B<BR>
     * <BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g�B
     * @@param l_lngRangeFrom - (From����ID)<BR>
     * From����ID�B
     * @@param l_lngRangeTo - (To����ID)<BR>
     * To����ID�B
     * @@return WEB3GentradeMainAccount[]
     * @@throws WEB3BaseException
     */
    public WEB3GentradeMainAccount[] getMainAccounts(
        Institution l_institution,
        long l_lngRangeFrom,
        long l_lngRangeTo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMainAccounts(Institution, long, long)";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        //�戵�����I�u�W�F�N�g�̔z����擾����B
        WEB3GentradeBranchMarketDealtCond[] l_branchMarketDealtConds =
            WEB3GentradeBranchMarketDealtCond.getHandlingCondBranchMarket(
                l_institution.getInstitutionCode());

        //�g�����Z�I�u�W�F�N�g�}�l�[�W�����擾����B
        WEB3GentradeFinObjectManager l_objectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        int l_intLength = l_branchMarketDealtConds.length;
        String l_strMarketCode = null;
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        Market l_market = null;
        List l_lisMarketIds = new ArrayList();
        //�擾�����戵�����I�u�W�F�N�g�̑S�v�f�ɂ��Ĉȉ��̏������s���B
        for (int i = 0; i < l_intLength; i++)
        {
            //�i���X�s��ʁj�戵����.get�s��R�[�h()�ɂ��s��R�[�h���擾����B
            l_strMarketCode = l_branchMarketDealtConds[i].getMarketCode();

            //�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()���R�[����
            try
            {
                l_market = l_objectManager.getMarket(l_strInstitutionCode, l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //�擾�����s��I�u�W�F�N�g�̎s��ID�����X�g�ɒǉ�����B
            //���������A���Ɏs��ID�����X�g�ɑ��݂���ꍇ�͒ǉ����Ȃ��B
            if (!l_lisMarketIds.contains(new Long(l_market.getMarketId())))
            {
                l_lisMarketIds.add(new Long(l_market.getMarketId()));
            }
        }

        WEB3GentradeMainAccount[] l_accounts = null;
        
        StringBuffer l_sbWhere = new StringBuffer();
        Branch[] l_branchs = l_institution.getBranches();
        if (l_branchs != null && l_branchs.length > 0)
        {
            l_sbWhere.append("branch_id in (" + l_branchs[0].getBranchId());
            for (int i = 1;i < l_branchs.length;i++)
            {
                l_sbWhere.append("," + l_branchs[i].getBranchId());
            }
            l_sbWhere.append(") and ");
        }
        l_sbWhere.append("product_type = ?");
        l_sbWhere.append(" and order_type not in (?,?)");
        l_sbWhere.append(" and order_open_status = ?");
        l_sbWhere.append(" and ? <= account_id");
        l_sbWhere.append(" and account_id <= ?");
        l_sbWhere.append(" and biz_date = ?");
        if (!l_lisMarketIds.isEmpty())
        {
            l_sbWhere.append(" and market_id in (");
            l_sbWhere.append(((Long)l_lisMarketIds.get(0)).longValue());
            for (int i = 1; i < l_lisMarketIds.size(); i++)
            {
                l_sbWhere.append("," + ((Long)l_lisMarketIds.get(i)).longValue());
            }
            l_sbWhere.append(")");
        }
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        String l_strBizDate =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_datBizDate);
        Object[] l_objWhere =
        {
            ProductTypeEnum.EQUITY,
            OrderTypeEnum.MINI_STOCK_BUY,
            OrderTypeEnum.MINI_STOCK_SELL,
            OrderOpenStatusEnum.OPEN,
            new Long(l_lngRangeFrom),
            new Long(l_lngRangeTo),
            l_strBizDate
        };
        
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            List l_lisRecord =
                l_QueryProcessor.doFindAllQuery(
                    EqtypeOrderUnitRow.TYPE,
                    l_sbWhere.toString(),
                    "account_id asc",
                    null,
                    l_objWhere);
            if (l_lisRecord != null && !l_lisRecord.isEmpty())
            {
                long l_accountId = 0L;
                int l_intSize = l_lisRecord.size();
                List l_lisAccount = new ArrayList();
                for (int i = 0;i < l_intSize;i++)
                {
                    EqtypeOrderUnitRow l_orderUnitRow =
                        (EqtypeOrderUnitRow)l_lisRecord.get(i);
                    if (l_accountId != l_orderUnitRow.getAccountId())
                    {
                        l_accountId = l_orderUnitRow.getAccountId();
                        WEB3GentradeMainAccount l_account =
                            new WEB3GentradeMainAccount(l_accountId);
                        l_lisAccount.add(l_account);
                    }
                }
                l_accounts = new WEB3GentradeMainAccount[l_lisAccount.size()];
                l_lisAccount.toArray(l_accounts);
            }
        }
        catch (DataException l_dex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dex.getMessage(),
                l_dex);
        }
        log.exiting(STR_METHOD_NAME);
        return l_accounts;
    }
    
    /**
     * (�I�����C�����s����TransactionCallback)<BR>
     * <BR>
     * �g�����U�N�V�������������{��������N���X�B<BR>
     * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
     */
    private class OnlineTransactionCallback
        implements TransactionCallback
    {
		/**
		 * (�،���ЃR�[�h)�B<BR>
		 * �J�z�Ώۂ̏،���ЃR�[�h�B<BR>
		 */
		private String institutionCode;
    
		/**
		 * (From����ID)�B<BR>
		 * From����ID�B<BR>
		 */
		private long rangeFrom;
    
		/**
		 * (To����ID)�B<BR>
		 * To����ID�B<BR>
		 */
		private long rangeTo;
		
		/**
		 * (�I�����C�����s����TransactionCallback)�B<BR>
		 * �R���X�g���N�^�B<BR>
		 * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
		 * �،���ЃR�[�h�B<BR>
		 * @@param l_lngRangeFrom - (From����ID)<BR>
		 * From����ID�B<BR>
		 * @@param l_lngRangeTo - (To����ID)<BR>
		 * To����ID�B<BR>
		 */
		public OnlineTransactionCallback(
			String l_strInstitutionCode,
			long l_lngRangeFrom,
			long l_lngRangeTo)
		{
			institutionCode = l_strInstitutionCode;
			rangeFrom = l_lngRangeFrom;
			rangeTo = l_lngRangeTo;
		}
        
        /**
         * (process)<BR>
         * <BR>
         * �g�����U�N�V�������������{����B<BR>
         * <BR>
         * �I�����C�����s���ʃe�[�u����"������"�ݒ���s���B<BR>
         * <BR>
         * @@return Object
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         */
		public Object process()
			throws DataNetworkException, DataQueryException, DataCallbackException
		{
			final String STR_METHOD_NAME = "OnlineTransactionCallback.process()";
			log.entering(STR_METHOD_NAME);

			WEB3GentradeOnlineRunStatus l_onlineRunStatus = null;
			try
			{
				l_onlineRunStatus = WEB3GentradeOnlineRunStatus.setDealing(
					this.institutionCode, ProductTypeEnum.EQUITY,
					WEB3FuturesOptionDivDef.DEFAULT, WEB3OnlineServiceDiv.ORDER_CARRY_OVER,
					this.rangeFrom, this.rangeTo);
			}
			catch (WEB3BaseException l_be)
			{
				log.exiting(STR_METHOD_NAME);
				throw new DataCallbackException(l_be.getMessage(), l_be);
			}
			log.exiting(STR_METHOD_NAME);
			return l_onlineRunStatus;
		}
    }
}
@
