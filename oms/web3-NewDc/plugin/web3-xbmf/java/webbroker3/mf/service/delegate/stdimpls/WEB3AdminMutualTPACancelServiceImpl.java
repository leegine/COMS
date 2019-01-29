head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.42.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒�������T�[�r�XImpl(WEB3AdminMutualTPACancelServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ����  (���u) �V�K�쐬
*/

package webbroker3.mf.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mf.WEB3MutualFundProduct;
import webbroker3.mf.WEB3MutualFundProductManager;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelCompleteResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListRequest;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListResponse;
import webbroker3.mf.message.WEB3AdminMutualTPACancelListUnit;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;

/**
 * ���M�Ǘ��җ]�͒�������T�[�r�XImpl
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AdminMutualTPACancelServiceImpl 
	implements WEB3AdminMutualTPACancelService
{
    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminMutualTPACancelServiceImpl.class);
    
    /**
     * �����M���]�͒���������������{����B <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̂����ꂩ�̃��\�b�h���R�[������B <BR>
     * �����M�Ǘ��җ]�͒�������ꗗ���N�G�X�g�̏ꍇ  <BR>
     * 	this.search�]�͒������( )  <BR>
     * �����M�Ǘ��җ]�͒�������������N�G�X�g�̏ꍇ  <BR>
     * �@@this.submit�]�͒������( ) <BR>
     * <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
    	throws WEB3BaseException
    {
        String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminMutualTPACancelListRequest)
        {
            //this.search�]�͒������( )
            l_response = 
                this.searchTPACancel(
                    (WEB3AdminMutualTPACancelListRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminMutualTPACancelCompleteRequest)
        {
            //this.submit�]�͒������( )
            l_response = 
                this.submitTPACancel(
                    (WEB3AdminMutualTPACancelCompleteRequest) l_request);
        }
        else
        {
            log.debug(" �p�����[�^�l���s������I");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;    
    }
    
    /**
     * (search�]�͒������)<BR>
     * <BR>
     * �����M���]�͒�������ꗗ�������s���B<BR>
     * �V�[�P���X�}�u�i���M�j�]�͒�������ꗗ�v�Q��<BR>
     *<BR>
     *==========================================================<BR>
     * �V�[�P���X�}�u�i���M�j�]�͒�������ꗗ�v: <BR>
     * 1.4((get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h :String)<BR>
     * �ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[�B<BR>
     *�w�Y������ڋq�����݂��܂���B�x<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01035<BR>
     *==========================================================<BR>
     * @@param l_request - (���M�Ǘ��җ]�͒�������ꗗ���N�G�X�g)
     * @@return WEB3AdminMutualTPACancelListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    protected WEB3AdminMutualTPACancelListResponse searchTPACancel(
        WEB3AdminMutualTPACancelListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "searchTPACancel(WEB3AdminMutualTPACancelListRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST, false);
        
        //1.4 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
		//[����] 
		//�،���ЃR�[�h�F �Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h() 
		//���X�R�[�h�F �Ǘ��҃I�u�W�F�N�g.get���X�R�[�h() 
		//�����R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {        
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(
                    l_admin.getInstitutionCode(), 
                    l_admin.getBranchCode(), 
                    l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //�ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[�B
            //�w�Y������ڋq�����݂��܂���B�x
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.5  is�M�p�����J��(�ٍϋ敪 : String)
		boolean l_blnisMarginAccountEstablished =
			l_mainAccount.isMarginAccountEstablished(
			    WEB3GentradeRepaymentDivDef.DEFAULT);
		
        //1.6 getSubAccount(arg0 : SubAccountTypeEnum)
		//[����]   
		//�@@�⏕�����^�C�v�F    
		//is�M�p�����J��=true �̏ꍇ�ASubAccountTypeEnum.�����M�p��������i�ۏ؋��j    
		//is�M�p�����J��=false �̏ꍇ�ASubAccountTypeEnum.������������i�a����j
		SubAccount l_subAccount = null;
		try
		{
			if (l_blnisMarginAccountEstablished)
			{
	            l_subAccount = 
	                l_mainAccount.getSubAccount(
	                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
			}
			else
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
			}
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
		
        //1.7 �ȉ��̏�����蓊�M�����P�ʂ̈ꗗ���擾����
		//[��������]
		//����ID�F �擾�����⏕�����I�u�W�F�N�g.getAccountId( )
		//�⏕����ID�F �擾�����⏕�����I�u�W�F�N.getSubAccountId( )
		//�����^�C�v�F �h3�F�����M���h
		//�����L����ԁF �h1:�I�[�v���h
		//�����o�H�敪�F �h9�FHOST�h
		//���ʃR�[�h�F NULL
		//[�\�[�g����]
		//��1�\�[�g�L�[�F ����(����)
		//��2�\�[�g�L�[�F ����ID(����)
        List l_lisRowsMfOrderUnit = new ArrayList();
        String l_strWhere = 
            "account_id = ? and sub_account_id = ? and product_type = ? " +
            "and order_open_status = ?  and order_root_div = ? and order_request_number is null";
        
        Object[] l_bindVars = {
            new Long(l_subAccount.getAccountId()), 
            new Long(l_subAccount.getSubAccountId()),
            ProductTypeEnum.MUTUAL_FUND,
            OrderOpenStatusEnum.OPEN,
            WEB3OrderRootDivDef.HOST}; 
        String l_strSortKey = " exec_date, product_id ";
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRowsMfOrderUnit = l_queryProcessor.doFindAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_strSortKey,
                null, 
                l_bindVars);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);    
        }
        
        int l_intSize = 0;
        if (l_lisRowsMfOrderUnit != null && !l_lisRowsMfOrderUnit.isEmpty())
        {
            l_intSize = l_lisRowsMfOrderUnit.size();
        }

        //1.8 ���M�����P�ʃ��X�g�̌������J��Ԃ��A���M�Ǘ��җ]�͒�������ꗗ�s�̔z����쐬����
        List l_lisMfTPACancelListUnit = new ArrayList();
        for (int i = 0; i < l_intSize; i++)
        {
            MutualFundOrderUnitParams l_mfOrderUnitParams = 
                (MutualFundOrderUnitParams) l_lisRowsMfOrderUnit.get(i);
            
            //1.8.1 ���M�Ǘ��җ]�͒�������ꗗ�s( )
            WEB3AdminMutualTPACancelListUnit l_tpaCancelListUnit = 
                new WEB3AdminMutualTPACancelListUnit();
            
            WEB3MutualFundProductManager l_mutualFundProductManager = 
                (WEB3MutualFundProductManager) l_finApp.getTradingModule(
                    ProductTypeEnum.MUTUAL_FUND).getProductManager();
            WEB3MutualFundProduct l_mutualFundProduct =  null;
            try
            {
               l_mutualFundProduct = 
                    (WEB3MutualFundProduct) l_mutualFundProductManager.getProduct(
                        l_mfOrderUnitParams.getProductId());
            }
    		catch (NotFoundException l_ex)
    		{
    			log.error("__NotFoundException__", l_ex);
    			log.exiting(STR_METHOD_NAME);
    			throw new WEB3BusinessLayerException(
    				WEB3ErrorCatalog.BUSINESS_ERROR_00377,
    				this.getClass().getName() + STR_METHOD_NAME,
    				l_ex.getMessage(),
    				l_ex);
    		}    
            
            //1.8.2 ���v���p�e�B�E�Z�b�g��
    		//[���M�Ǘ��җ]�͒�������ꗗ�s�ɐݒ肷��l] 
    		//����ID �@@�@@= ���M�����P��.get����ID() 
            l_tpaCancelListUnit.orderId = l_mfOrderUnitParams.getOrderId() + "";
    		//�����R�[�h = ���M�����P��.getProduct().get�����R�[�h() 
            l_tpaCancelListUnit.mutualProductCode = 
                l_mutualFundProduct.getProductCode();
    		//�������@@�@@ = ���M�����P��.getProduct().get������() 
            l_tpaCancelListUnit.mutualProductName = 
                l_mutualFundProduct.getMutualProductName();
    		//���Z���z  = ���M�����P��.get��������() 
            l_tpaCancelListUnit.settlePrice = 
                WEB3StringTypeUtility.formatNumber(
                    l_mfOrderUnitParams.getQuantity()); 
    		//������     = ���M�����P��.get������() 
            l_tpaCancelListUnit.orderBizDate = 
                WEB3DateUtility.getDate(
                    l_mfOrderUnitParams.getBizDate(), "yyyyMMdd");
    		//����     = ���M�����P��.get����() 
            l_tpaCancelListUnit.executionTimestamp = 
                l_mfOrderUnitParams.getExecDate();
            //��n��     = ���M�����P��.get��n��() 
            l_tpaCancelListUnit.deliveryDate = 
                l_mfOrderUnitParams.getDeliveryDate();
            l_lisMfTPACancelListUnit.add(l_tpaCancelListUnit);
        }

        //1.9 createResponse( )
        WEB3AdminMutualTPACancelListResponse l_response = 
            (WEB3AdminMutualTPACancelListResponse) l_request.createResponse();
        
        //1.10 ���v���p�e�B�E�Z�b�g��
		//[���M�Ǘ��җ]�͒�������ꗗ���X�|���X�ɐݒ肷��l] 
		//�]�͒�������ꗗ�F �쐬�������M�Ǘ��җ]�͒�������ꗗ�s�̔z��
        WEB3AdminMutualTPACancelListUnit[] l_cancelUnits = 
            new WEB3AdminMutualTPACancelListUnit[l_lisMfTPACancelListUnit.size()];
        l_lisMfTPACancelListUnit.toArray(l_cancelUnits);
        l_response.cancelList = l_cancelUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit�]�͒������)<BR>
     * <BR>
     * �����M���]�͒�����������������s���B<BR>
     * �V�[�P���X�}�u�i���M�j�]�͒�����������v�Q��<BR>
     * @@param l_request - (���M�Ǘ��җ]�͒�������������N�G�X�g)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4153F8F00140
     */
    protected WEB3AdminMutualTPACancelCompleteResponse submitTPACancel(
        WEB3AdminMutualTPACancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "submitTPACancel(WEB3AdminMutualTPACancelCompleteRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        //���O�C�������A�Ǘ��҃I�u�W�F�N�g���擾����B 
        WEB3Administrator l_admin = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(
            WEB3TransactionCategoryDef.ADMIN_MUTUAL_TRADING_POWER_ADJUST, true);
        
        //1.4 validate����p�X���[�h(�p�X���[�h : String)
        l_admin.validateTradingPassword(l_request.password);
        
        //1.5 get�ڋq(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        //�m�����n  
		//�،���ЃR�[�h�F �Ǘ��҃I�u�W�F�N�g.get�،���ЃR�[�h()�̖߂�l  
		//���X�R�[�h�F �Ǘ��҃I�u�W�F�N�g.get���X�R�[�h()�̖߂�l  
		//�����R�[�h�F ���N�G�X�g�f�[�^.�ڋq�R�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accMgr = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        WEB3GentradeMainAccount l_mainAccount = null;
        try
        {        
            l_mainAccount = (WEB3GentradeMainAccount)
                l_accMgr.getMainAccount(
                    l_admin.getInstitutionCode(), 
                    l_admin.getBranchCode(), 
                    l_request.accountCode);
        }
        catch(WEB3BaseException l_ex)
        {
            //�ڋq�I�u�W�F�N�g���擾�ł��Ȃ������ꍇ�A��O���X���[�B
            //�w�Y������ڋq�����݂��܂���B�x
            log.error("�Y������ڋq�����݂��܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01035,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //1.6  lock����(�،���ЃR�[�h : String, ���X�R�[�h : String, �����R�[�h : String)
        l_accMgr.lockAccount(
            l_admin.getInstitutionCode(), 
            l_admin.getBranchCode(), 
            l_mainAccount.getAccountCode());
        
        //1.7 is�M�p�����J��(�ٍϋ敪 : String)
		boolean l_blnisMarginAccountEstablished =
			l_mainAccount.isMarginAccountEstablished(
			    WEB3GentradeRepaymentDivDef.DEFAULT);
		
        //1.8 getSubAccount(arg0 : SubAccountTypeEnum)
		//�⏕�����I�u�W�F�N�g���擾����B  
		//[����]   
		//�⏕�����^�C�v�F    
		//is�M�p�����J��=true �̏ꍇ�ASubAccountTypeEnum.�����M�p��������i�ۏ؋��j    
		//is�M�p�����J��=false �̏ꍇ�ASubAccountTypeEnum.������������i�a����j 
		SubAccount l_subAccount = null;
		try
		{
			if (l_blnisMarginAccountEstablished)
			{
	            l_subAccount = 
	                l_mainAccount.getSubAccount(
	                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
			}
			else
			{
				l_subAccount = 
				    l_mainAccount.getSubAccount(
				        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
			}
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
        
        //1.9 (*)��������������̍쐬
        String l_strWhere = "order_id = ?";
        //1.10 (*)���������f�[�^�R���e�i�̍쐬
        Object[] l_objWhereValues = { l_request.orderId };       
        try
        {
            //1.11 getDefaultProcessor()
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //1.12 ���M���������e�[�u���̃��R�[�h���폜����B
            l_processor.doDeleteAllQuery(
                MutualFundOrderActionRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            //1.13 ���M�����P�ʃe�[�u���̃��R�[�h���폜����B
            l_processor.doDeleteAllQuery(
                MutualFundOrderUnitRow.TYPE,
                l_strWhere,
                l_objWhereValues);
            //1.14 ���M�����e�[�u���̃��R�[�h���폜����B
            l_processor.doDeleteAllQuery(
                MutualFundOrderRow.TYPE,
                l_strWhere,
                l_objWhereValues);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);  
        }
        
        //1.15 �]�͍Čv�Z(�⏕���� : �⏕����)
		WEB3GentradeSubAccount l_genSubAccount = (WEB3GentradeSubAccount) l_subAccount;
        WEB3TPTradingPowerService l_tpTradingPowerService = 
            (WEB3TPTradingPowerService) Services.getService(
                WEB3TPTradingPowerService.class);
        l_tpTradingPowerService.reCalcTradingPower(l_genSubAccount);
        
        //1.16 createResponse( )
        WEB3AdminMutualTPACancelCompleteResponse l_response = 
            (WEB3AdminMutualTPACancelCompleteResponse) l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }  
}
@
