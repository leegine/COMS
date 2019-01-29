head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionEndUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������o���I��UnitServiceImpl(WEB3AdminFeqExecutionEndUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/18 �s�p (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
Revesion History : 2010/01/12 �����F(���u) ���f��No.533 No.538
Revesion History : 2010/09/10 �����C(���u) ���f��No.547
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqExecEndUpdateInterceptor;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqPositionManager;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndUnitService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O�������o���I��UnitServiceImpl)<BR>
 * �O�������o���I��UnitService�����N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionEndUnitServiceImpl implements WEB3AdminFeqExecutionEndUnitService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionEndUnitServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F1000F
     */
    public WEB3AdminFeqExecutionEndUnitServiceImpl() 
    {
     
    }
    
    /**
     * (exec�o���I��)<BR>
     * �ڋq�P�ʂ̏o���I�����������{����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�i�ǁj�o���I���jexec�o���I���v<BR>
     * @@param l_account - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF87C0325
     */
    public void execExecEnd(WEB3GentradeMainAccount l_account, String l_strMarketCode, Date l_datBizDate) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execExecEnd(WEB3GentradeMainAccount, String, Date) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_account == null)
        {
            log.debug("�ڋq�I�u�W�F�N�g�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�ڋq�I�u�W�F�N�g�����݂��Ȃ��B");
        }
        
        //1.1:lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.debug("FinApp�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "FinApp�����݂��Ȃ��B");
        }

        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug("�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
        }
        Institution l_institution = l_account.getInstitution();
        if (l_institution == null)
        {
            log.debug("�،���Ђ����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�،���Ђ����݂��Ȃ��B");
        }
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        
        Branch l_branch = l_account.getBranch();
        if (l_branch == null)
        {
            log.debug("���X�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "���X�����݂��Ȃ��B");
        }
             
        l_accountManager.lockAccount(
            l_strInstitutionCode,
            l_branch.getBranchCode(),
            l_account.getAccountCode());

        //1.2:get�o���I���Ώے����P��(long, String, String, Date)
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        if (l_tradingModule == null)
        {
            log.debug("TradingModule�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "TradingModule�����݂��Ȃ��B");
        }
        WEB3FeqOrderManager l_orderMgr = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderMgr == null)
        {
            log.debug("�O�����������}�l�[�W�������݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�����������}�l�[�W�������݂��Ȃ��B");
        }
        
        WEB3FeqOrderUnit[] l_orderUnits = l_orderMgr.getOrderExecEndObjectOrderUnit(
            new Long(l_account.getAccountId()),
            l_strInstitutionCode,
            l_strMarketCode,
            l_datBizDate);

        //1.3: update�g�����U�N�V����(�����P�ʂh�c�ꗗ : long[], ��� : Date, is�l�b�e�B���O���� : boolean)
        WEB3FeqPositionManager l_positionMgr = 
            (WEB3FeqPositionManager)l_tradingModule.getPositionManager();
        if (l_positionMgr == null)
        {
            log.debug("�O�������|�W�V�����}�l�[�W�������݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�������|�W�V�����}�l�[�W�������݂��Ȃ��B");
        }
            
        //�����P��ID�ꗗ�F�@@get�o���I���Ώے����P��()�̖߂�l��蒊�o���������P��ID�̈ꗗ         
        long[] l_lngOrderUnitIdList = null;
        int l_intCnt = 0;
        
        if (l_orderUnits != null && l_orderUnits.length > 0)
        {
            l_intCnt = l_orderUnits.length;            
            int l_intCnt2 = 0;
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
                
                if (l_orderUnit != null)
                {
                    l_intCnt2++;
                }
            }            
            
            if (l_intCnt2 > 0)
            {
                l_lngOrderUnitIdList = new long[l_intCnt2];
                int l_intNo = 0;
            
                for (int i = 0; i < l_intCnt; i++)
                {
                    WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
                
                    if (l_orderUnit != null)
                    {
                        l_lngOrderUnitIdList[l_intNo] = l_orderUnit.getOrderUnitId();                        
                        l_intNo++;
                    }
                }
            }
        }        

        //is�l�b�e�B���O����
        boolean l_blnIsNetting = false;

        //update�g�����U�N�V����(�����P�ʂh�c�ꗗ : long[], ��� : Date, is�l�b�e�B���O���� : boolean)
        //[����]
        //�����P��ID�ꗗ�F�@@get�o���I���Ώے����P��()�̖߂�l��蒊�o���������P��ID�̈ꗗ
        //����F�@@�p�����[�^.������
        //is�l�b�e�B���O�����F�@@false
        l_positionMgr.updateTransaction(l_lngOrderUnitIdList, l_datBizDate, l_blnIsNetting);
        
        //1.4:(*)get�o���I���Ώے����P��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_intCnt; i++)
        {
            WEB3FeqOrderUnit l_orderUnit = l_orderUnits[i];
            
            if (l_orderUnit != null)
            {
                WEB3GentradeMarket l_market = null;
                boolean l_blnIsDayTradeAdoption = false;
                boolean l_blnIsDayTradeMarket = false;
                try
                {
                    FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
                    l_market = (WEB3GentradeMarket)(l_finApp.getFinObjectManager().getMarket(l_feqOrderUnitRow.getMarketId()));

                    l_blnIsDayTradeAdoption = ((WEB3GentradeInstitution)l_institution).isDayTradeAdoption();
                    l_blnIsDayTradeMarket = l_market.isDayTradeMarket();
                }
                catch (NotFoundException l_ex)
                {
                    log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                //�iis���v�����̗p() == false�@@or�@@is���v��s��() == false�j�@@���� ���t�����iis���t() == true)
                if ((!l_blnIsDayTradeAdoption || !l_blnIsDayTradeMarket) && l_orderUnit.isBuy())
                {
                    //update�ۗL���Y(�����P�� : �O�����������P��)
                    l_positionMgr.updateAsset(l_orderUnit);
                }

                //1.4.2:(*)�o���I���Ώے����`�F�b�N
                //�ȉ��̏����𖞂��������P�ʂ́A�o���I���������s���B
                //  �@@�������������Ă��钍��
                //    (�����P��.�����������t <= �p�����[�^.������)
                //  �A�I�[�v���ł��钍���P��
                //    (�����P��.�����L����� == "�I�[�v��") 
                int l_intFlag = WEB3DateUtility.compareToDay(
                    l_orderUnit.getExpirationTimestamp(), 
                    l_datBizDate);
                
                if (l_intFlag <= 0 && OrderOpenStatusEnum.OPEN.equals(l_orderUnit.getOrderOpenStatus()))
                {                    
                    //1.4.2.1:�O�������o���I���X�V�C�x���g�C���^�Z�v�^( )
                    WEB3FeqExecEndUpdateInterceptor l_execEndUpdatenIterceptor = 
                        new WEB3FeqExecEndUpdateInterceptor();
                    
                    //1.4.2.2:setThreadLocalPersistenceEventInterceptor
                    //(arg0 : FeqOrderManagerPersistenceEventInterceptor)
                    l_orderMgr.setThreadLocalPersistenceEventInterceptor(l_execEndUpdatenIterceptor);
                    
                    //1.4.2.3:expireOrder(arg0 : long)
                    ProcessingResult l_result = l_orderMgr.expireOrder(l_orderUnit.getOrderId());
        
                    if (l_result != null && l_result.isFailedResult())
                    {
                        log.debug("expireOrder�G���[");
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3BusinessLayerException(
                            l_result.getErrorInfo(),
                            this.getClass().getName() + STR_METHOD_NAME,
                            "expireOrder�G���[");
                    }
                }
                //1.4.3:(*)��L�ȊO�̏ꍇ
                else
                {
                    //1.4.3.1:update�o���I������(�O�����������P��)
                    this.updateExecEndOrder(l_orderUnit);
                }
            }                        
        }
        
        //1.5:is�M�p�����J��(String)
        boolean l_blnIsMarginAccountEstablished = 
            l_account.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        
        //1.6:getSubAccount(arg0 : SubAccountTypeEnum)
        WEB3GentradeSubAccount l_subAccount = null;
        //[is�M�p�����J��()�̖߂�l == true�̏ꍇ] 
        try
        {
            if (l_blnIsMarginAccountEstablished)
            {                
                //SubAccountTypeEnum.�����M�p����������Z�b�g�B 
                l_subAccount = (WEB3GentradeSubAccount)l_account.getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);//NotFoundException
            }                
            //[��L�ȊO�̏ꍇ] 
            else
            {                
                //SubAccountTypeEnum.��������������Z�b�g�B
                l_subAccount = (WEB3GentradeSubAccount)l_account.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException
            }
        }
        catch (NotFoundException l_ex)
        {
            log.debug("�⏕����������܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                "�⏕����������܂���B",
                l_ex); 
        }
        
        //1.7:�]�͍Čv�Z(�⏕���� : �⏕����)
        WEB3TPTradingPowerReCalcService l_tPTradingPowerReCalcServiceImpl = 
            (WEB3TPTradingPowerReCalcService)Services.getService(WEB3TPTradingPowerReCalcService.class);
        
        if (l_tPTradingPowerReCalcServiceImpl == null)
        {
            log.debug("�]�͍Čv�Z�T�[�r�XImpl�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                "�]�͍Čv�Z�T�[�r�XImpl�����݂��Ȃ��B");
        }
        
        l_tPTradingPowerReCalcServiceImpl.reCalcTradingPower(l_subAccount);
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (update�o���I������)<BR>
     * �����̒����ɏo���I�������Ȃǂ�ݒ肷��B<BR>
     * <BR>
     * �P�j�@@�X�V�҃R�[�h�̎擾<BR>
     * �@@�Z�b�V������胍�O�C���h�c���擾���A<BR>
     * �@@���O�C���h�c�ɊY������Ǘ��҂��擾����B<BR>
     * �@@�擾�����Ǘ���.�Ǘ��҃R�[�h���X�V�҃R�[�h�Ƃ���B<BR>
     * <BR>
     * �Q�j�@@�o���I�������̏o���I��������update����B<BR>
     * <BR>
     * �@@�Q�|�P�j�@@�ȉ��̏����ɊY������o���I�������̒����P�ʃ��R�[�h��update����B<BR>
     * �@@�@@<����><BR>
     * �@@�@@�@@�����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�o���I���������� = ���ݓ���<BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�X�V�҃R�[�h = �擾�����X�V�҃R�[�h<BR>
     * �@@�@@�@@�����P�ʃ��R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�ȉ��̏����ɊY������o���I�������̒��������́A<BR>
     * �@@�@@�@@�@@�@@�ŏI�������R�[�h�̍X�V�҃R�[�h�A�X�V���t��update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����e�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID�@@����<BR>
     * �@@�@@�����e�[�u��.��������ԍ� = �p�����[�^.�����P��.���������ŏI�ʔ�<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�������R�[�h.�X�V�҃R�[�h = �擾�����X�V�҃R�[�h<BR>
     * �@@�@@�������R�[�h.�X�V���t = ���ݓ���<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�ȉ��̏����ɊY������A�o���I�������̒����i�w�b�_�j��<BR>
     * �@@�@@�X�V�҃R�[�h�A�X�V������update����B<BR>
     * <BR>
     * �@@�@@<����><BR>
     * �@@�@@�����i�w�b�_�j�e�[�u��.����ID = �p�����[�^.�����P��.����ID<BR>
     * <BR>
     * �@@�@@<�X�V���e><BR>
     * �@@�@@�����i�w�b�_�j���R�[�h.�X�V�҃R�[�h = �擾�����X�V�҃R�[�h<BR>
     * �@@�@@�����i�w�b�_�j���R�[�h.�X�V���t = ���ݓ���<BR>
     * @@param l_orderUnit - (�����P��)<BR>
     * �O�����������P�ʃI�u�W�F�N�g<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42C0B613024F
     */
    public void updateExecEndOrder(WEB3FeqOrderUnit l_orderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " updateExecEndOrder(WEB3FeqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderUnit == null)
        {
            log.debug("�����P�ʂ����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "�����P�ʂ����݂��Ȃ��B");
        }
                
        try
        {
            //�P�j�@@�X�V�҃R�[�h�̎擾
            //�Z�b�V������胍�O�C���h�c���擾���A���O�C���h�c�ɊY������Ǘ��҂��擾����B
            //�擾�����Ǘ���.�Ǘ��҃R�[�h���X�V�҃R�[�h�Ƃ���B
            WEB3Administrator l_administrator = 
                WEB3Administrator.getInstanceFromLoginInfo();
            if (l_administrator == null)
            {
                log.debug("�Ǘ��҂����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Ǘ��҂����݂��Ȃ��B");
            }

            String l_strAdministratorCode = l_administrator.getAdministratorCode();
        
            //�Q�j�@@�o���I�������̏o���I��������update����B            
            //�Q�|�P�j�@@�ȉ��̏����ɊY������o���I�������̒����P�ʃ��R�[�h��update����B 
            //      <����> 
            //�@@�@@�@@     �����P�ʃe�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID 
            //
            //�@@�@@     <�X�V���e> 
            //�@@�@@�@@     �����P�ʃ��R�[�h.�o���I������ = ���ݓ��� 
            //�@@�@@�@@     �����P�ʃ��R�[�h.�X�V�҃R�[�h = �擾�����X�V�҃R�[�h 
            //�@@�@@�@@     �����P�ʃ��R�[�h.�X�V���t = ���ݓ��� 
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_orderUnit.getDataSourceObject();
            if (l_orderUnitRow == null)
            {
                log.debug("�����P�ʍs�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�����P�ʍs�����݂��Ȃ��B");
            }
            
            long l_lngOrderUnitId = l_orderUnitRow.getOrderUnitId();
            
            Timestamp l_tsSystemTime = GtlUtils.getSystemTimestamp();
            
            String l_strWhere1 = " order_unit_id = ? ";
            Object[] l_obj1 = {new Long(l_lngOrderUnitId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
            List l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE, 
                l_strWhere1,
                " FOR UPDATE ",
                l_obj1);//DataNetworkException, DataQueryException
            
            if (l_lisOrderUnitRows != null && !l_lisOrderUnitRows.isEmpty())
            {
                int l_intCnt = l_lisOrderUnitRows.size();
                
                if (l_intCnt == 1)
                {
                    FeqOrderUnitRow l_orderUnitRowForUpdate = 
                        (FeqOrderUnitRow)l_lisOrderUnitRows.get(0);
            
                    FeqOrderUnitParams l_orderUnitParamsForUpdate = 
                        new FeqOrderUnitParams(l_orderUnitRowForUpdate);
                
                    l_orderUnitParamsForUpdate.setExecEndTimestamp(l_tsSystemTime);
                    l_orderUnitParamsForUpdate.setLastUpdater(l_strAdministratorCode);
                    l_orderUnitParamsForUpdate.setLastUpdatedTimestamp(l_tsSystemTime);

                    l_queryProcessor.doUpdateQuery(l_orderUnitParamsForUpdate);
                    //DataNetworkException,DataQueryException
                }
                else
                {
                    log.debug("�����P�ʃe�[�u�����f�[�^�s�����G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�����P�ʃe�[�u�����f�[�^�s�����G���[�B");
                }
            }            
            
            //�Q�|�Q�j�@@�ȉ��̏����ɊY������o���I�������̒��������́A
            //�ŏI�������R�[�h�̍X�V�҃R�[�h�A�X�V���t��update����B
            //     <����> 
            //�@@�@@      �����e�[�u��.�����P��ID = �p�����[�^.�����P��.�����P��ID�@@���� 
            //�@@�@@      �����e�[�u��.��������ԍ� = �p�����[�^.�����P��.���������ŏI�ʔ� 
            //�@@�@@ <�X�V���e> 
            //�@@�@@      �������R�[�h.�X�V�҃R�[�h = �擾�����X�V�҃R�[�h 
            //�@@�@@      �������R�[�h.�X�V���t = ���ݓ��� 
            int l_intLastOrderActionSerialNo = l_orderUnitRow.getLastOrderActionSerialNo();
            
            String l_strWhere2 = " order_unit_id = ? and order_action_serial_no = ? ";
            Object[] l_obj2 = {new Long(l_lngOrderUnitId),
                new Integer(l_intLastOrderActionSerialNo)};
                
            List l_lisOrderActionRows = l_queryProcessor.doFindAllQuery(
                FeqOrderActionRow.TYPE, 
                l_strWhere2,
                " FOR UPDATE ",
                l_obj2);//DataNetworkException, DataQueryException
            
            if (l_lisOrderActionRows != null && !l_lisOrderActionRows.isEmpty())
            {                
                int l_intCnt = l_lisOrderActionRows.size();
                
                if (l_intCnt == 1)
                {
                    FeqOrderActionParams l_orderActionParams = new FeqOrderActionParams(
                        (FeqOrderActionRow)l_lisOrderActionRows.get(0));
                    
                    l_orderActionParams.setLastUpdater(l_strAdministratorCode);
                    l_orderActionParams.setLastUpdatedTimestamp(l_tsSystemTime);
                    
                    l_queryProcessor.doUpdateQuery(l_orderActionParams);//DataNetworkException,DataQueryException
                }
                else
                {
                    log.debug("���������e�[�u�����f�[�^�s�����G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "���������e�[�u�����f�[�^�s�����G���[�B");
                }
            }
            
            //�Q�|�R�j�@@�ȉ��̏����ɊY������A�o���I�������̒����i�w�b�_�j�� 
            //�@@�@@      �X�V�҃R�[�h�A�X�V������update����B 
            //�@@�@@          <����> 
            //�@@�@@          �����i�w�b�_�j�e�[�u��.����ID = �p�����[�^.�����P��.����ID 
            //�@@�@@          <�X�V���e> 
            //�@@�@@          �����i�w�b�_�j���R�[�h.�X�V�҃R�[�h = �擾�����X�V�҃R�[�h 
            //�@@�@@          �����i�w�b�_�j���R�[�h.�X�V���t = ���ݓ���             
            String l_strWhere3 = " order_id = ? ";
            Object[] l_obj3 = {new Long(l_orderUnit.getOrderId())};
            
            List l_lisOrderRows = l_queryProcessor.doFindAllQuery(
                FeqOrderRow.TYPE, 
                l_strWhere3,
                " FOR UPDATE ",
                l_obj3);//DataNetworkException, DataQueryException
            
            if (l_lisOrderRows != null && !l_lisOrderRows.isEmpty())
            {
                int l_intCnt = l_lisOrderRows.size();
                
                if (l_intCnt == 1)
                {
                    FeqOrderRow l_ordertRows = 
                        (FeqOrderRow)l_lisOrderRows.get(0);
            
                    FeqOrderParams l_orderParams = 
                        new FeqOrderParams(l_ordertRows);
                
                     l_orderParams.setLastUpdater(l_strAdministratorCode);
                     l_orderParams.setLastUpdatedTimestamp(l_tsSystemTime);
                    
                     l_queryProcessor.doUpdateQuery(l_orderParams);//DataNetworkException,DataQueryException
                }
                else
                {
                    log.debug("�����i�w�b�_�j�e�[�u�����f�[�^�s�����G���[�B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�����i�w�b�_�j�e�[�u�����f�[�^�s�����G���[�B");
                }
            }  
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
