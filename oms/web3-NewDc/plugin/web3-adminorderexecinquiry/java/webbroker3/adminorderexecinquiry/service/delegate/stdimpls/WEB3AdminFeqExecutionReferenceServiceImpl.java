head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.22.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminFeqExecutionReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������������Ɖ�T�[�r�XImpl(WEB3AdminFeqExecutionReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
Revesion History : 2005/08/03 ��O��(���u) ���r���[
Revesion History : 2007/02/26 �����(���u)�d�l�ύX���f��No.092
Revesion History : 2008/01/23 �đo�g(���u) �O���������f��No.372
Revesion History : 2008/10/02 ���V(SRA) �y�Ǘ��Ғ������Ɖ�z�d�l�ύX�Ǘ��䒠�i���f���jNo.131
Revesion History : 2009/08/03 ���g(���u) �y�Ǘ��Ғ������Ɖ�z�d�l�ύX�Ǘ��䒠�i���f���jNo.134,No.135
Revesion History : 2009/08/03 ���g(���u) �y�Ǘ��Ғ������Ɖ�z����No.002
*/

package webbroker3.adminorderexecinquiry.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.adminorderexecinquiry.WEB3AdminOrderExecuteDataManager;
import webbroker3.adminorderexecinquiry.WEB3AdminProductExecutionInfo;
import webbroker3.adminorderexecinquiry.define.WEB3AdminFeqSortKeyDef;
import webbroker3.adminorderexecinquiry.define.WEB3AdminProductDivDef;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORDetailDispInfoUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecuteListResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqExecutionUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefInputResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceRequest;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefReferenceResponse;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORFeqOrderExecutionRefUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminOROrderExecutionSortKeyUnit;
import webbroker3.adminorderexecinquiry.message.WEB3AdminORTradingProductUnit;
import webbroker3.adminorderexecinquiry.service.delegate.WEB3AdminFeqExecutionReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqFinTransactionManager;
import webbroker3.feq.WEB3FeqOrderExecution;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO�������������Ɖ�T�[�r�XImpl)<BR>
 * �Ǘ��ҊO�������������Ɖ�T�[�r�X�����N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionReferenceServiceImpl implements WEB3AdminFeqExecutionReferenceService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqExecutionReferenceServiceImpl.class);
    
    /**
     * @@roseuid 42D1C8EA02EE
     */
    public WEB3AdminFeqExecutionReferenceServiceImpl() 
    {
     
    }
       
    /**
     * �Ǘ��ҊO�������������Ɖ�T�[�r�X���s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR>
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^���A<BR>
     * [�Ǘ��ҁE�O�������������Ɖ���̓��N�G�X�g�̏ꍇ]<BR>
     * �@@this.get���͉��()���R�[������B<BR>
     * <BR>
     * [�Ǘ��ҁE�O�������������Ɖ�N�G�X�g�̏ꍇ]<BR>
     * �@@this.get�Ɖ���()���R�[������B<BR>
     * <BR>
     * [�Ǘ��ҁE�O�������o�����͈ꗗ���N�G�X�g�̏ꍇ]<BR>
     * �@@this.get�o�����͈ꗗ()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A67E4D02D0
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminORFeqOrderExecutionRefInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminORFeqOrderExecutionRefInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminORFeqOrderExecutionRefReferenceRequest)
        {
            //get�Ɖ���()
            l_response = 
                this.getReferenceScreen((WEB3AdminORFeqOrderExecutionRefReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminORFeqExecuteListRequest)
        {
            //get�o�����͈ꗗ
            l_response = 
                this.getExecInputList((WEB3AdminORFeqExecuteListRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �Ǘ��ҊO�������������Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO�������������Ɖ�T�[�r�X)get���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�O�������������Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A67E9F0020
     */
    protected WEB3AdminORFeqOrderExecutionRefInputResponse getInputScreen(
        WEB3AdminORFeqOrderExecutionRefInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminORFeqOrderExecutionRefInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException

        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 get���i���{���(�،���ЃR�[�h : String, ���X�R�[�h�ꗗ : String[])
        WEB3AdminOrderExecuteDataManager l_orderExecuteDataManager = new WEB3AdminOrderExecuteDataManager();
        
        WEB3AdminProductExecutionInfo l_productExecutionInfo = null;
        try
        {
            //WEB3SystemLayerException,NotFoundException,DataFindException,DataQueryException,DataNetworkException
            l_productExecutionInfo = l_orderExecuteDataManager.getProductExecutionInfo(
                l_strInstitutionCode, 
                l_request.branchCode);
        }
        catch (DataFindException l_ex)
        {
            String l_strMessage = "���i���{��񂪑��݂��Ȃ��B";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "���i���{��񂪑��݂��Ȃ��B";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage,
                l_ex);
        }
        
        //1.6 create�戵���i�ꗗ(���i���{���)
        WEB3AdminORTradingProductUnit[] l_tradingProductUnits = 
            this.createTradeProductList(l_productExecutionInfo);//WEB3BaseException
        
        //1.7 get�戵�\�s��(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum)
        String[] l_strMarkets = WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
            l_strInstitutionCode, 
            ProductTypeEnum.FOREIGN_EQUITY);//WEB3SystemLayerException

        //ArrayList( )
        ArrayList l_lisMarketCode1 = new ArrayList();

        //ArrayList( )
        ArrayList l_lisMarketCode2 = new ArrayList();

        for (int i = 0; i < l_strMarkets.length; i++)
        {
            String l_strMarketCode = l_strMarkets[i];
            MarketRow l_marketRow = null;
            try
            {
                l_marketRow =
                    MarketDao.findRowByInstitutionCodeMarketCode(l_strInstitutionCode, l_strMarketCode);

                if (l_marketRow == null)
                {
                    log.debug("market not found with InstitutionCode, MarketCode:"
                        + l_strInstitutionCode + ", " + l_strMarketCode);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "�����ɊY������s�ꂪ�݂���܂���ł����B");
                }
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }

            //�s��p�v���t�@@�����X�e�[�u�����Y�����郌�R�[�h���擾����B
            //�s��ID�F�@@get�戵�\�s��()�̖߂�l�̊Y���v�f�i=�s��R�[�h�j�ɊY������s��.�s��ID
            //�v���t�@@�����X���ږ��F�@@"feq.sle.broker"
            //���ږ��A�ԁF�@@1
            try
            {
                MarketPreferencesDao.findRowByPk(
                    l_marketRow.getMarketId(),
                    WEB3MarketPreferencesNameDef.FEQ_SLE_BROKER,
                    1);

                //����t���[�F�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂���ꍇ
                //���X�g�i�s�꒼���p�j�Ɏs��R�[�h��ǉ�����B
                if (!l_lisMarketCode2.contains(l_strMarketCode))
                {
                    l_lisMarketCode2.add(l_strMarketCode);
                }
                log.debug("�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂���ꍇ�A"
                + "���X�g�i�s�꒼���p�j�Ɏs��R�[�h��ǉ�����");
            }
            catch(DataFindException l_ex)
            {
                //����t���[�F�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂��Ȃ��ꍇ
                //���X�g�i��s�꒼���p�j�Ɏs��R�[�h��ǉ�����B
                if (!l_lisMarketCode1.contains(l_strMarketCode))
                {
                    l_lisMarketCode1.add(l_strMarketCode);
                }

                log.debug("�s��p�v���t�@@�����X�e�[�u���ɊY�����R�[�h�����݂��Ȃ��ꍇ�A"
                    + "���X�g�i��s�꒼���p�j�Ɏs��R�[�h��ǉ�����B");
            }
            catch (DataQueryException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "DB�ւ̃A�N�Z�X�Ɏ��s���܂����B",
                    l_ex);
            }
        }

        //1.8 ArrayList( )
        ArrayList l_bizDateList = new ArrayList();
        
        //1.9 (*)�Ɩ����t�̑O�c�Ɠ��A�Ɩ����t��ǉ�����B
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        Timestamp l_tsBizDate = new Timestamp(l_datBizDate.getTime());
        
        //�O�c�Ɠ�        
        for (int i = 0; i < l_strMarkets.length; i++)
        {
            WEB3GentradeBizDate l_gentradeBizDate = 
                new WEB3GentradeBizDate(
                    new Timestamp(l_tsBizDate.getTime()));
                    
            Date l_datPreBizDate = l_gentradeBizDate.calcFeqBizDate(
                l_strInstitutionCode, 
                l_strMarkets[i], 
                l_tsBizDate, 
                -1);
                
            boolean l_blnIsContain = false;
            for (int j = 0; j < l_bizDateList.size(); j++)
            {
                if (WEB3DateUtility.compareToDay(l_datPreBizDate, (Date)l_bizDateList.get(j)) == 0)
                {
                    l_blnIsContain = true;
                    break;
                }
            }
                
            //�d�����Ȃ��l�̂�ArrayList�ɒǉ����ĉ������B
            if (!l_blnIsContain)
            {
                l_bizDateList.add(l_datPreBizDate);
            }                
        }

        //�Ɩ����t
        l_bizDateList.add(l_datBizDate);

        //1.10 (*)get�戵�\�s��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_strMarkets.length; i++)
        {
            //1.10.1 reset�s��R�[�h(�s��R�[�h : String)
            WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarkets[i]);
            
            //1.10.2 get������( )
            Date l_datBizDateTemp = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            
            //1.10.3 (*)get������()�̖߂�l��ArrayList�ɑ��݂��Ȃ��ꍇ�́A�ǉ�����B
            boolean l_blnExist = false;
            for (int j = 0; j < l_bizDateList.size(); j++)
            {
                if (WEB3DateUtility.compareToDay(l_datBizDateTemp, (Date)l_bizDateList.get(j)) == 0)
                {
                    l_blnExist = true;
                    break;
                }
            }
            if (!l_blnExist)
            {
                l_bizDateList.add(l_datBizDateTemp);                                
            }            
        }
        
        //1.11 �戵�\��������(
        //    �،���ЃR�[�h : String, 
        //    �����^�C�v : ProductTypeEnum, 
        //    �敨�^�I�v�V�����敪 : String, 
        //    �M�p����敪 : String)
        WEB3GentradeHandlingOrderCond l_orderCond = new WEB3GentradeHandlingOrderCond(
            l_strInstitutionCode, 
            ProductTypeEnum.FOREIGN_EQUITY,
            WEB3FuturesOptionDivDef.DEFAULT,
            WEB3MarginTradingDivDef.DEFAULT);//WEB3SystemLayerException
            
        //1.12 �戵�\���s�����擾( )
        String[] l_strHandlingPossibleExecConds = l_orderCond.getHandlingPossibleExecCond();

        //1.13 �戵�\���������敪�擾( )
        String[] l_strHandlingPossibleExpirationDateTypes = 
            l_orderCond.getHandlingPossibleExpirationDateType();

        //1.14 �戵�\���������擾( )
        String[] l_strHandlingPossibleOrderConds = l_orderCond.getHandlingPossibleOrderCond();
        
        //1.15 createResponse( )
        WEB3AdminORFeqOrderExecutionRefInputResponse l_response = 
            (WEB3AdminORFeqOrderExecutionRefInputResponse)l_request.createResponse();

        //1.16 �v���p�e�B�Z�b�g
        //�������ꗗ
        Date[] l_datBizDateList = new Date[l_bizDateList.size()];
        l_bizDateList.toArray(l_datBizDateList);
        for (int i = 0; i < l_datBizDateList.length; i++)
        {
            l_datBizDateList[i] = WEB3DateUtility.toDay(l_datBizDateList[i]);
        }
        l_response.orderBizDateList = l_datBizDateList;

        //�s��R�[�h�ꗗ
        List l_lisMarketCodes = new ArrayList();
        for (int i = 0; i < l_lisMarketCode1.size(); i++)
        {
            l_lisMarketCodes.add(l_lisMarketCode1.get(i));
        }

        for (int j = 0; j < l_lisMarketCode2.size(); j++)
        {
            l_lisMarketCodes.add(l_lisMarketCode2.get(j));
        }

        String[] l_strMarketCodes = new String[l_lisMarketCodes.size()];
        l_lisMarketCodes.toArray(l_strMarketCodes);

        l_response.marketList = l_strMarketCodes;
        
        //���s�����ꗗ
        l_response.execCondTypeList = l_strHandlingPossibleExecConds;
        
        //���������敪�ꗗ
        l_response.expirationDateTypeList = l_strHandlingPossibleExpirationDateTypes;
        
        //���������ꗗ
        l_response.orderCondTypeList = l_strHandlingPossibleOrderConds;
        
        //�戵���i�ꗗ  
        l_response.tradingProductList = l_tradingProductUnits;
        
        //�����o�H�敪�ꗗ
        l_response.orderRootDivList = new String[]{
            WEB3OrderRootDivDef.CALLCENTER, 
            WEB3OrderRootDivDef.PC,
            WEB3OrderRootDivDef.SLINGSHOT,
            WEB3OrderRootDivDef.I_MODE,
            WEB3OrderRootDivDef.VODAFONE,
            WEB3OrderRootDivDef.AU,
            WEB3OrderRootDivDef.HOST};
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�Ɖ���)<BR>
     * �Ǘ��ҊO�������������Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO�������������Ɖ�T�[�r�X)get�Ɖ��ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�O�������������Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 42A67E9F003F
     */
    protected WEB3AdminORFeqOrderExecutionRefReferenceResponse getReferenceScreen(
        WEB3AdminORFeqOrderExecutionRefReferenceRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getReferenceScreen(WEB3AdminORFeqOrderExecutionRefReferenceRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�^�p�R�[�h(�،���ЃR�[�h : String, �^�p�R�[�h : String)
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.5 create���ʌ�������������(�،���ЃR�[�h : String, ���N�G�X�g�f�[�^ : �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g)
        WEB3AdminOrderExecuteDataManager l_dataManager = 
            new WEB3AdminOrderExecuteDataManager();
        String l_strCommonQueryString = 
            l_dataManager.createCommonQueryString(l_strInstitutionCode, l_request);
        
        //1.6 create��������������(String, String, String, String, String, String, boolean)
        String l_strQueryString = this.createQueryString(
            l_request.productCode,
            l_request.marketCode,
            l_request.taxType,
            l_request.execCondType,
            l_request.managementCode,
            l_request.updaterCode,
            false);
            
        //1.7 create���ʌ��������f�[�^�R���e�i(�،���ЃR�[�h : String, ���N�G�X�g�f�[�^ : �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g)
        String[] l_strCommonQueryDataContainer = null;
        try
        {
            l_strCommonQueryDataContainer = 
                l_dataManager.createCommonQueryDataContainer(l_strInstitutionCode, l_request);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            throw l_ex;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        
        //1.8 create���������f�[�^�R���e�i(String, String, String, String, String, String, String, boolean)
        String[] l_strQueryContainer = this.createQueryContainer(
            l_strInstitutionCode, 
            l_request.productCode,
            l_request.marketCode,
            l_request.taxType,
            l_request.execCondType,
            l_strEmpCode,
            l_request.updaterCode,
            false);
            
        //1.9 create�\�[�g����(�������Ɖ�\�[�g�L�[[])
        String l_strSortCond = this.createSortCond(l_request.sortKeys);

        //1.10 get�����P�ʈꗗ(String, String[], String)
        int l_intCommonLen = 0;
        if (l_strCommonQueryDataContainer != null)
        {
            l_intCommonLen = l_strCommonQueryDataContainer.length; 
        }
        int l_intSpecialLen = 0;
        if (l_strQueryContainer != null)
        {
            l_intSpecialLen = l_strQueryContainer.length;
        }
        String[] l_strQueryContainerAll =
            new String[l_intCommonLen + l_intSpecialLen];
        for (int i = 0; i < l_intCommonLen; i++)
        {
            l_strQueryContainerAll[i] = l_strCommonQueryDataContainer[i];
        }
        for (int i = 0; i < l_intSpecialLen; i++)
        {
            l_strQueryContainerAll[i + l_intCommonLen] = l_strQueryContainer[i];
        }
        WEB3FeqOrderUnit[] l_orderUnits = this.getOrderUnitList(
            l_strCommonQueryString + l_strQueryString,
            l_strQueryContainerAll,
            l_strSortCond);
        
        //1.11 remove�J�z�������P��(�����P�ʈꗗ : �O�����������P��[])
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "�O�����������}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderUnit[] l_removedOrderUnits = 
            l_orderManager.removeCarryOverOriginOrder(l_orderUnits);
        
        //1.12 (*)remove�J�z�������P��()�̖߂�l != null�̏ꍇ�A�ȉ��̏��������{����B
        WEB3AdminORFeqOrderExecutionRefUnit[] l_feqOrderExecInquiryUnits = null;
        WEB3AdminORDetailDispInfoUnit[] l_dispInfoUnits = null;
        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;
        int l_intPageIndexResponse = 0;
        if (l_removedOrderUnits != null && l_removedOrderUnits.length != 0)
        {
            // If this.pageIndex is not a numerical value, throw Exception.
            if (!WEB3StringTypeUtility.isNumber(l_request.pageIndex))
            {
                String l_strMessage = "�v���y�[�W�ԍ��������ȊO�̒l�ł��u" + l_request.pageIndex + "�v�B";
                log.debug(l_strMessage);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }

            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);

            // If l_request.Size is not a numerical value, throw Exception.
            if (!WEB3StringTypeUtility.isNumber(l_request.pageSize))
            {
                String l_strMessage = "�y�[�W���\���s���������ȊO�̒l�ł��u" + l_request.pageSize + "�v�B";
                log.debug(l_strMessage);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                    this.getClass().getName() +  STR_METHOD_NAME,
                    l_strMessage);
            }
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            //1.12.1 WEB3PageIndexInfo(arg0 : �_���r���[::java::lang::Object[], arg1 : int, arg2 : int)
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
                l_removedOrderUnits,
                l_intPageIndex,
                l_intPageSize);
                
            //1.12.2 getArrayReturned(arg0 : Class)
            WEB3FeqOrderUnit[] l_arrayReturneds = 
                (WEB3FeqOrderUnit[])l_pageIndexInfo.getArrayReturned(WEB3FeqOrderUnit.class);
            
            //1.12.3 create�O�������������Ɖ�Unit�ꗗ(�O�����������P��[])
            l_feqOrderExecInquiryUnits = 
                this.createFeqOrderExecInquiryUnitList(l_arrayReturneds);
                
            //1.12.4 create�ڍ׉�ʏ��ꗗ(�Ǘ��Ғ������Ɖ��Unit[])
            l_dispInfoUnits = l_dataManager.createExecuteDetailsInfoList(
                l_feqOrderExecInquiryUnits);//WEB3BaseException
            
            //1.12.5 getTotalPages( )
            l_intTotalPages = l_pageIndexInfo.getTotalPages();
            
            //1.12.6 getTotalRecords( )
            l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
            
            //1.12.7 getPageIndex( )
            l_intPageIndexResponse = l_pageIndexInfo.getPageIndex();
        }
        
        //1.13 get�،����( )
        WEB3GentradeInstitution l_institution = 
            (WEB3GentradeInstitution)l_admin.getInstitution();
        
        //1.14  get�����J�z�����敪(�����^�C�v : ProductTypeEnum, �敨�^�I�v�V�����敪 : String)
        String l_strgetCarryoverEndType = l_institution.getCarryoverEndType(
            ProductTypeEnum.FOREIGN_EQUITY, 
            WEB3FuturesOptionDivDef.DEFAULT);//WEB3SystemLayerException
        
        //1.15 createResponse( )
        WEB3AdminORFeqOrderExecutionRefReferenceResponse l_response = 
            (WEB3AdminORFeqOrderExecutionRefReferenceResponse)l_request.createResponse();
            
        //1.16 �v���p�e�B�Z�b�g
        //�o���I���^�����J�z�����敪
        l_response.carryoverEndType = l_strgetCarryoverEndType;
        
        //���y�[�W��
        l_response.totalPages = l_intTotalPages + "";
        
        //�����R�[�h��
        l_response.totalRecords = l_intTotalRecords + "";
        
        //�\���y�[�W�ԍ�
        l_response.pageIndex = l_intPageIndexResponse + "";
        
        //�ڍ׉�ʏ��ꗗ
        l_response.detailDispInfoList = l_dispInfoUnits;
        
        //�Ǘ��ҊO�������������Ɖ�Unit�ꗗ
        l_response.feqOrderExecuteList = l_feqOrderExecInquiryUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�o�����͈ꗗ)<BR>
     * �Ǘ��ҊO�������o�����͈ꗗ�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO�������������Ɖ�T�[�r�X)get�o�����͈ꗗ�v�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �Ǘ��ҁE�O�������o�����͈ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminORFeqExecuteListResponse
     * @@throws WEB3BaseException
     * @@roseuid 42AD07A5002D
     */
    protected WEB3AdminORFeqExecuteListResponse getExecInputList(
        WEB3AdminORFeqExecuteListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " getExecInputList(WEB3AdminORFeqExecuteListRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1,2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "�Ǘ��҃��̃O�C����񂪑��݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }

        //1.3 validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException
        
        //1.4 validate���X����(���X�R�[�h : String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3SystemLayerException    
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�^�p�R�[�h(�،���ЃR�[�h : String, �^�p�R�[�h : String)
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.6 create���ʌ�������������(String, �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g)
        WEB3AdminOrderExecuteDataManager l_dataManager = new WEB3AdminOrderExecuteDataManager();
        String l_strCommonQueryString = 
            l_dataManager.createCommonQueryString(l_strInstitutionCode, l_request);

        //1.7 create��������������(String, String, String, String, String, String, boolean)
        String l_strQueryString = this.createQueryString(
            l_request.productCode,
            l_request.marketCode,
            null,
            null,
            l_request.managementCode,
            l_request.updaterCode,
            true);
            
        //1.8 create���ʌ��������f�[�^�R���e�i(�،���ЃR�[�h : String, ���N�G�X�g�f�[�^ : �Ǘ��ҁE�������Ɖ�ʃ��N�G�X�g)
        String[] l_strCommonQueryDataContainers = null;
        try
        {
            l_strCommonQueryDataContainers = 
                l_dataManager.createCommonQueryDataContainer(l_strInstitutionCode, l_request);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage());
            throw l_ex;
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        
        //1.9 create���������f�[�^�R���e�i(String, String, String, String, String, String, String, boolean)
        String[] l_strQueryContainer = this.createQueryContainer(
            l_strInstitutionCode, 
            l_request.productCode,
            l_request.marketCode,
            null,
            null,
            l_strEmpCode,
            l_request.updaterCode,
            true);

        //1.10 create�\�[�g�����i�o�����͈ꗗ�j(�������Ɖ�\�[�g�L�[[])
        String l_strSortCond = this.createSortCondExecInputList(l_request.sortKeys);
        
        //1.11 get���ꗗ(String, String[], String)
        int l_intCommonLen = 0;
        if (l_strCommonQueryDataContainers != null)
        {
            l_intCommonLen = l_strCommonQueryDataContainers.length; 
        }
        int l_intSpecialLen = 0;
        if (l_strQueryContainer != null)
        {
            l_intSpecialLen = l_strQueryContainer.length;
        }
        String[] l_strQueryContainerAll =
            new String[l_intCommonLen + l_intSpecialLen];
        for (int i = 0; i < l_intCommonLen; i++)
        {
            l_strQueryContainerAll[i] = l_strCommonQueryDataContainers[i];
        }
        for (int i = 0; i < l_intSpecialLen; i++)
        {
            l_strQueryContainerAll[i + l_intCommonLen] = l_strQueryContainer[i];
        }
        WEB3FeqOrderExecution[] l_orderExecutions= this.getExecList(
            l_strCommonQueryString + l_strQueryString,
            l_strQueryContainerAll,
            l_strSortCond);
            
        //1.12 get���ꗗ�̖߂�l != null�̏ꍇ�A�ȉ��̏��������{����B
        WEB3AdminORFeqExecutionUnit[] l_executionRefUnits = null;
        int l_intTotalPages = 0;
        int l_intTotalRecords = 0;
        int l_intPageIndexResponse = 0;
        if (l_orderExecutions != null && l_orderExecutions.length != 0)
        {
            //1.12.1 WEB3PageIndexInfo(arg0 : �_���r���[::java::lang::Object[], arg1 : int, arg2 : int)
            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);
            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
                l_orderExecutions,
                l_intPageIndex,
                l_intPageSize);

            //1.12.2 getArrayReturned(arg0 : Class)
            WEB3FeqOrderExecution[] l_arrayReturneds = 
                (WEB3FeqOrderExecution[])l_pageIndexInfo.getArrayReturned(WEB3FeqOrderExecution.class);
                
            //1.12.3 create�o�����͖��׈ꗗ(�O���������[])
            l_executionRefUnits = 
                this.createExecInputDetailList(l_arrayReturneds);    
            
            //1.12.5 getTotalPages( )
            l_intTotalPages = l_pageIndexInfo.getTotalPages();
            
            //1.12.6 getTotalRecords( )
            l_intTotalRecords = l_pageIndexInfo.getTotalRecords();
            
            //1.12.7 getPageIndex( )
            l_intPageIndexResponse = l_pageIndexInfo.getPageIndex();

        }
        else
        {
            log.debug("�Y������f�[�^�����݂��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Y������f�[�^�����݂��܂���B");

        }
        
        //1.13 createResponse( )
        WEB3AdminORFeqExecuteListResponse l_response = 
            (WEB3AdminORFeqExecuteListResponse)l_request.createResponse();
        
        //1.14 �v���p�e�B�Z�b�g
        //���y�[�W��
        l_response.totalPages = l_intTotalPages + "";
        
        //�����R�[�h��
        l_response.totalRecords = l_intTotalRecords + "";
        
        //�\���y�[�W�ԍ�
        l_response.pageIndex = l_intPageIndexResponse + "";

        //���׈ꗗ
        l_response.executionList = l_executionRefUnits;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create�戵���i�ꗗ)<BR>
     * �����̏��i���{���ɂ��戵�\�ȏ��i�Ǝ���̑g�ݍ��킹���쐬���A<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�O�������̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B<BR>
     * �@@�����Ώۂ̎���敪(*1)�ɂ��āA�ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�戵���i�C���X�^���X�𐶐�����B<BR>
     * �@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * �@@�@@���i�敪 = "�O������"<BR>
     * �@@�@@����敪 = �����Ώۂ̎���敪<BR>
     * �@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �@@(*1)�O�������̏����Ώێ���敪<BR>
     * �@@�@@�E"�O���������t"<BR>
     * �@@�@@�E"�O���������t"<BR>
     * <BR>
     * �R�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param productExecInfo - (���i���{���)<BR>
     * ���i���{���I�u�W�F�N�g<BR>
     * @@return �戵���i[]
     * @@throws WEB3BaseException
     * @@roseuid 42A689DA0214
     */
    protected WEB3AdminORTradingProductUnit[] createTradeProductList(
        WEB3AdminProductExecutionInfo l_productExecInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createTradeProductList(WEB3AdminProductExecutionInfo )";
        log.entering(STR_METHOD_NAME);

        //�P�jArrayList�𐶐�����B
        ArrayList l_arrayList = new ArrayList();

        //�Q�j�O�������̏��i�敪�Ǝ���敪�̑g�ݍ��킹���쐬����B
        //�@@�����Ώۂ̎���敪(*1)�ɂ��āA�ȉ��̏������J��Ԃ��B
        //�@@�Q�|�P�j�戵���i�C���X�^���X�𐶐�����B
        WEB3AdminORTradingProductUnit l_productUnit = new WEB3AdminORTradingProductUnit();
    
        //�@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //�@@�@@���i�敪 = "�O������"
        //�@@�@@����敪 = �����Ώۂ̎���敪
        l_productUnit.productDiv = WEB3AdminProductDivDef.FEQ;
        l_productUnit.tradingType = OrderTypeEnum.FEQ_BUY.intValue() + "";
        
        //�@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
        l_arrayList.add(l_productUnit);

        //�@@�Q�|�P�j�戵���i�C���X�^���X�𐶐�����B
        l_productUnit = new WEB3AdminORTradingProductUnit();
    
        //�@@�Q�|�Q�j���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
        //�@@�@@���i�敪 = "�O������"
        //�@@�@@����敪 = �����Ώۂ̎���敪
        l_productUnit = new WEB3AdminORTradingProductUnit();
        l_productUnit.productDiv = WEB3AdminProductDivDef.FEQ;
        l_productUnit.tradingType = OrderTypeEnum.FEQ_SELL.intValue() + "";

        //�@@�Q�|�R�j��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
        l_arrayList.add(l_productUnit);
        
        //�R�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3AdminORTradingProductUnit[] l_productUnits = 
            new WEB3AdminORTradingProductUnit[l_arrayList.size()];
        l_arrayList.toArray(l_productUnits);
        
        log.exiting(STR_METHOD_NAME);        
        return l_productUnits;
    }
    
    /**
     * (create��������������)<BR>
     * (createQueryString)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@����ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_id = ? "<BR>
     * <BR>
     * �R�j�p�����[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@�s��ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and market_id = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�ŋ敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����敪 == "���"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and tax_type = ? "<BR>
     * �@@[�p�����[�^.�����敪 == "����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and tax_type in (?, ?) "<BR>
     * <BR>
     * �T�j�p�����[�^.���s���� != null�̏ꍇ�A<BR>
     * �@@���s��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and execution_condition_type = ? "<BR>
     * <BR>
     * �U�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A<BR>
     * �@@�^�p�R�[�h��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and order_emp_code "<BR>
     * <BR>
     * �V�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A<BR>
     * �@@�X�V�҃R�[�h��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and last_updater = ? "<BR>
     * <BR>
     * �W�j�p�����[�^.is���폜�̂� == true�̏ꍇ�A<BR>
     * �@@�폜�t���O��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and (delete_flag is null or delete_flag = ?) "<BR>
     * <BR>
     * �X�j�쐬�������������������ԋp����B<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_strTaxType - (�����敪)<BR>
     * �����敪<BR>
     * @@param l_strExecCond - (���s����)<BR>
     * ���s����<BR>
     * @@param l_strEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_blnIsOnlyUndeleted - (is���폜�̂�)<BR>
     * ���폜�̃f�[�^�̂ݒ��o���邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�폜�A���폜�𒊏o<BR>
     * true�F�@@���폜�̂ݒ��o<BR>
     * @@return String
     * @@roseuid 42A68D460168
     */
    protected String createQueryString(
        String l_strProductCode, 
        String l_strMarketCode, 
        String l_strTaxType, 
        String l_strExecCond, 
        String l_strEmpCode, 
        String l_strUpdaterCode, 
        boolean l_blnIsOnlyUndeleted) 
    {
        final String STR_METHOD_NAME = 
            " createQueryString(String, String, String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);

        //�P�j��������������C���X�^���X(�FString)�𐶐�����B
        String l_strQueryString = "";
        
        //�Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A
        //�@@����ID����������������ɒǉ�����B
        //�@@�������������� += "and product_id = ? "
        if (l_strProductCode != null)
        {
            l_strQueryString += " and product_id = ? ";
        }

        //�R�j�p�����[�^.�s��R�[�h != null�̏ꍇ�A
        //�@@�s��ID����������������ɒǉ�����B
        //�@@�������������� += "and market_id = ? "
        if (l_strMarketCode != null)
        {
            l_strQueryString += " and market_id = ? ";
        }

        //�S�j�p�����[�^.�����敪 != null�̏ꍇ�A
        //�@@�ŋ敪����������������ɒǉ�����B
        //�@@[�p�����[�^.�����敪 == "���"�̏ꍇ]
        //�@@�@@�������������� += "and tax_type = ? "
        //�@@[�p�����[�^.�����敪 == "����"�̏ꍇ]
        //�@@�@@�������������� += "and tax_type in (?, ?) "
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
        {
            l_strQueryString += " and tax_type = ? ";
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType))
        {
            l_strQueryString += " and tax_type in (?, ?) ";
        }

        //�T�j�p�����[�^.���s���� != null�̏ꍇ�A
        //�@@���s��������������������ɒǉ�����B
        //�@@�������������� += "and execution_condition_type = ? "
        if (l_strExecCond != null)
        {
            l_strQueryString += " and execution_condition_type = ? ";
        }

        //�U�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A
        //�@@�^�p�R�[�h��������������������ɒǉ�����B
        //�@@�������������� += "and order_emp_code "
        if (l_strEmpCode != null)
        {
            l_strQueryString += " and order_emp_code = ? ";
        }

        //�V�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A
        //�@@�X�V�҃R�[�h��������������������ɒǉ�����B
        //�@@�������������� += "and last_updater = ? "
        if (l_strUpdaterCode != null)
        {
            l_strQueryString += " and last_updater = ? ";
        }

        //�W�j�p�����[�^.is���폜�̂� == true�̏ꍇ�A
        //�@@�폜�t���O��������������������ɒǉ�����B
        //�@@�������������� += "and (delete_flag = null or delete_flag = ?) "
        if (l_blnIsOnlyUndeleted)
        {
            l_strQueryString += " and (delete_flag is null or delete_flag = ?) ";
        }
        
        log.exiting(STR_METHOD_NAME);        
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * (createQueryDataContainer)<BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�jArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@����ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@������ID�́A�O�������v���_�N�g�}�l�[�W��.<BR>
     * �@@get�O����������()�ɂĎ擾�����O����������.����ID���Z�b�g�B<BR>
     * <BR>
     * �@@[get�O����������()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�،���ЁF�@@�g���A�J�E���g�}�l�[�W��.<BR>
     * �@@�@@�@@getInstitution(�p�����[�^.�،���ЃR�[�h)�ɂĎ擾�����،����<BR>
     * �@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * <BR>
     * �R�j�p�����[�^.�s��R�[�h != null�̏ꍇ�A<BR>
     * �@@�s��ID�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@���s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂ�<BR>
     * �@@�@@�擾�����s��.�s��ID���Z�b�g�B<BR>
     * <BR>
     * �@@�@@[get�s��()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h<BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�ȉ��̏����ɂ��A�ŋ敪�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[�p�����[�^.�����敪 == "���"�̏ꍇ]<BR>
     * �@@�@@�ȉ��̒l��ǉ�<BR>
     * �@@�@@�ETaxTypeEnum.���<BR>
     * <BR>
     * �@@[�p�����[�^.�����敪 == "����"�̏ꍇ]<BR>
     * �@@�@@�ȉ��̒l��ǉ�<BR>
     * �@@�@@�ETaxTypeEnum.����<BR>
     * �@@�@@�ETaxTypeEnum.���肩���򒥎�<BR>
     * <BR>
     * �T�j�p�����[�^.���s���� != null�̏ꍇ�A<BR>
     * �@@���s�����𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�����s�����́A�O�����������}�l�[�W��.get���s����()�ɂĎ擾����B<BR>
     * <BR>
     * �@@[get���s����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@���s�����F�@@�p�����[�^.���s����<BR>
     * <BR>
     * �U�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�^�p�R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �V�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�X�V�҃R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �W�j�p�����[�^.is���폜�̂� == true�̏ꍇ�A<BR>
     * �@@"0"�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �X�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strMarketCode - (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * @@param l_strTaxType - (�����敪)<BR>
     * �����敪<BR>
     * @@param l_strExecCond - (���s����)<BR>
     * ���s����<BR>
     * @@param l_strEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@param l_strUpdaterCode - (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     * @@param l_blnIsOnlyUndeleted - (is���폜�̂�)<BR>
     * ���폜�̃f�[�^�̂ݒ��o���邩�ǂ����̃t���O<BR>
     * <BR>
     * false�F�@@�폜�A���폜�𒊏o<BR>
     * true�F�@@���폜�̂ݒ��o<BR>
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 42A68D46016F
     */
    protected String[] createQueryContainer(
        String l_strInstitutionCode, 
        String l_strProductCode, 
        String l_strMarketCode, 
        String l_strTaxType, 
        String l_strExecCond, 
        String l_strEmpCode, 
        String l_strUpdaterCode, 
        boolean l_blnIsOnlyUndeleted) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            " createQueryContainer(String, String, String, String, String, String, String, boolean)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jArrayList�𐶐�����B
        ArrayList l_arrayList = new ArrayList();
        
        //�Q�j�p�����[�^.�����R�[�h != null�̏ꍇ�A
        //�@@����ID�𐶐�����ArrayList�ɒǉ�����B
        //�@@������ID�́A�O�������v���_�N�g�}�l�[�W��.
        //�@@get�O����������()�ɂĎ擾�����O����������.����ID���Z�b�g�B
        //�@@[get�O����������()�ɃZ�b�g����p�����[�^]
        //�@@�@@�،���ЁF�@@�g���A�J�E���g�}�l�[�W��.
        //�@@�@@�@@getInstitution(�p�����[�^.�،���ЃR�[�h)�ɂĎ擾�����،����
        //�@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
        }
        Institution l_institution;
        try
        {
            l_institution = l_accountManager.getInstitution(l_strInstitutionCode);//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "�،���Ђ����݂��Ȃ��B[�،����code = " + l_strInstitutionCode + " ]";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
    
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "�O�������v���_�N�g�}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_strProductCode != null)
        {
            
            FeqProduct l_feqProduct = null;
            try
            {
                l_feqProduct = l_productManager.getFeqProduct(l_institution, l_strProductCode);
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                    + l_strProductCode + "�v";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage,
                    l_ex);
            }
            if (l_feqProduct == null)
            {
                String l_strMessage = "�O���������������݂��Ȃ��B�����R�[�h�u" 
                    + l_strProductCode + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            l_arrayList.add(l_feqProduct.getProductId() + "");
        }

        //�R�j�p�����[�^.�s��R�[�h != null�̏ꍇ�A
        //�@@�s��ID�𐶐�����ArrayList�ɒǉ�����B
        //�@@���s��ID�́A�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��()�ɂ�
        //�@@�@@�擾�����s��.�s��ID���Z�b�g�B
        //�@@�@@[get�s��()�ɃZ�b�g����p�����[�^]
        //�@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h
        //�@@�@@�@@�s��R�[�h�F�@@�p�����[�^.�s��R�[�h
        WEB3GentradeFinObjectManager l_finObjManager = 
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
        if (l_finObjManager == null)
        {
            String l_strMessage = "�g�����Z�I�u�W�F�N�g�}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_strMarketCode != null)
        {
            Market l_market = null;
            try
            {
                l_market = l_finObjManager.getMarket(l_strInstitutionCode, l_strMarketCode);
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "�s�ꂪ���݂��Ȃ��B" 
                    + "�،���ЃR�[�h = �u " + l_strInstitutionCode 
                    + "�v�A�s��R�[�h =�u" + l_strMarketCode 
                    + "�v";
                log.error(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            l_arrayList.add(l_market.getMarketId() + "");
        }
        
        //�S�j�p�����[�^.�����敪 != null�̏ꍇ�A
        //�@@�ȉ��̏����ɂ��A�ŋ敪�𐶐�����ArrayList�ɒǉ�����B
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strTaxType))
        {
            //�@@[�p�����[�^.�����敪 == "���"�̏ꍇ]
            //�@@�@@�ȉ��̒l��ǉ�
            //�@@�@@�ETaxTypeEnum.���
            l_arrayList.add(TaxTypeEnum.NORMAL.intValue() + "");
        }
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strTaxType))
        {
            //�@@[�p�����[�^.�����敪 == "����"�̏ꍇ]
            //�@@�@@�ȉ��̒l��ǉ�
            //�@@�@@�ETaxTypeEnum.����
            //�@@�@@�ETaxTypeEnum.���肩���򒥎�
            l_arrayList.add(TaxTypeEnum.SPECIAL.intValue() + "");
            l_arrayList.add(TaxTypeEnum.SPECIAL_WITHHOLD.intValue() + "");
        }
        
        //�T�j�p�����[�^.���s���� != null�̏ꍇ�A
        //�@@���s�����𐶐�����ArrayList�ɒǉ�����B
        //�@@�����s�����́A�O�����������}�l�[�W��.get���s����()�ɂĎ擾����B
        //�@@[get���s����()�ɃZ�b�g����p�����[�^]
        //�@@�@@���s�����F�@@�p�����[�^.���s����
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "�O�����������}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        if (l_strExecCond != null)
        {
            l_arrayList.add(l_orderManager.getExecutionCondition(l_strExecCond).intValue() + "");
        }
        
        //�U�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A
        //�@@�p�����[�^.�^�p�R�[�h�𐶐�����ArrayList�ɒǉ�����B
        if (l_strEmpCode != null)
        {
            l_arrayList.add(l_strEmpCode);
        }
        
        //�V�j�p�����[�^.�X�V�҃R�[�h != null�̏ꍇ�A
        //�@@�p�����[�^.�X�V�҃R�[�h�𐶐�����ArrayList�ɒǉ�����B
        if (l_strUpdaterCode != null)
        {
            l_arrayList.add(l_strUpdaterCode);
        }
        
        //�W�j�p�����[�^.is���폜�̂� == true�̏ꍇ�A
        //�@@"0"�𐶐�����ArrayList�ɒǉ�����B
        if (l_blnIsOnlyUndeleted)
        {
            l_arrayList.add("0");
        }
        
        //�X�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_objects = 
            new String[l_arrayList.size()];
        l_arrayList.toArray(l_objects);
        
        log.exiting(STR_METHOD_NAME);
        return l_objects;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * (createSortCond)<BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u����ID�v�@@�@@�@@���@@�O�����������P��.����ID<BR>
     * �@@�@@�E�u���X�R�[�h�v�@@���@@�O�����������P��.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@substr(�O�����������P��.����ID, 9, 6)<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@�O�����������P��.����ID<BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@�O�����������P��.�s��ID<BR>
     * �@@�@@�E�u�����敪�v�@@���@@�O�����������P��.�ŋ敪<BR>
     * �@@�@@�E�u����敪�v�@@���@@�O�����������P��.�������<BR>
     * �@@�@@�E�u���ϋ敪�v�@@���@@�O�����������P��.���ϋ敪<BR>
     * �@@�@@�E�u�������ԁv�@@���@@�O�����������P��.�󒍓���<BR>
     * �@@�@@�E�u�������v�@@���@@�O�����������P��.������<BR>
     * �@@�@@�E�u���s�����v�@@���@@�O�����������P��.���s����<BR>
     * �@@�@@�E�u���������v�@@���@@�O�����������P��.�����������t<BR>
     * �@@�@@�E�u���������v�@@���@@�O�����������P��.��������<BR>
     * �@@�@@�E�u��n���v�@@���@@�O�����������P��.��n��<BR>
     * �@@�@@�E�u�^�p�R�[�h�v�@@���@@�O�����������P��.�^�p�R�[�h<BR>
     * <BR>
     * �@@�Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * �S�j�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 42A68D460178
     */
    protected String createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = 
            " createSortCond(WEB3AdminOROrderExecutionSortKeyUnit[] )";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�[�g����������(�FString)���쐬����B
        String l_strSortCond = "";
        
        //�Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        int l_intCount = 0; 
        if (l_sortKeys != null)
        {
            l_intCount = l_sortKeys.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //�@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A
            //�@@�@@�쐬�����\�[�g����������ɒǉ�����B
            //�@@�@@�E�u����ID�v�@@�@@�@@���@@�O�����������P��.����ID
            //�@@�@@�E�u���X�R�[�h�v�@@���@@�O�����������P��.���XID
            //�@@�@@�E�u�ڋq�R�[�h�v�@@���@@substr(�O�����������P��.����ID, 9, 6)
            //�@@�@@�E�u�����R�[�h�v�@@���@@�O�����������P��.����ID
            //�@@�@@�E�u�s��R�[�h�v�@@���@@�O�����������P��.�s��ID
            //�@@�@@�E�u�����敪�v�@@���@@�O�����������P��.�ŋ敪
            //�@@�@@�E�u����敪�v�@@���@@�O�����������P��.�������
            //�@@�@@�E�u���ϋ敪�v�@@���@@�O�����������P��.���ϋ敪
            //�@@�@@�E�u�������ԁv�@@���@@�O�����������P��.�󒍓���
            //�@@�@@�E�u�������v�@@���@@�O�����������P��.������
            //�@@�@@�E�u���s�����v�@@���@@�O�����������P��.���s����
            //�@@�@@�E�u���������v�@@���@@�O�����������P��.�����������t
            //�@@�@@�E�u���������v�@@���@@�O�����������P��.��������
            //�@@�@@�E�u��n���v�@@���@@�O�����������P��.��n��
            //�@@�@@�E�u�^�p�R�[�h�v�@@���@@�O�����������P��.�^�p�R�[�h
            if (i != 0)
            {
                l_strSortCond += " , ";
            }
            
            String l_strKeyItem = l_sortKeys[i].keyItem;
            if (WEB3AdminFeqSortKeyDef.ORDER_ID.equals(l_strKeyItem))
            {
                l_strSortCond += " order_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " branch_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " substr(account_id, 9, 6) ";
            }
            else if (WEB3AdminFeqSortKeyDef.PRODUCT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " product_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.MARKET_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " market_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.TAX_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " tax_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.TRADING_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.SETTLE_DIV.equals(l_strKeyItem))
            {
                l_strSortCond += " settle_div ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_START_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " received_date_time ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_BIZ_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " biz_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.EXECCOND_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " execution_condition_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.EXPIRATION_DATE_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " expiration_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_COND_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_condition_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.DELIVERY_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " delivery_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.MANAGEMENT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_emp_code ";
            }
        
            //�@@�Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����
            //�@@�@@(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " asc ";
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " desc ";
            }
        }
        
        //�R�j�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��
        if (l_intCount != 0)
        {
            l_strSortCond += " , ";
        }
        l_strSortCond += " last_updated_timestamp asc ";
        
        //�S�j�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
    
    /**
     * (get�����P�ʈꗗ)<BR>
     * �����̏����ɊY�����钍���P�ʂ̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�O�����������P��Row.TYPE<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�j�������ʂ��A�O�����������P�ʂ̔z��𐶐����A�ԋp����B<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A68D46017A
     */
    protected WEB3FeqOrderUnit[] getOrderUnitList(
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getOrderUnitList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //�@@�@@arg0�F�@@�O�����������P��Row.TYPE
        //�@@�@@arg1�F�@@�p�����[�^.��������������
        //�@@�@@arg2�F�@@�p�����[�^.�\�[�g����
        //�@@�@@arg3�F�@@null
        //�@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (IllegalStateException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            return null;
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //�R�j�������ʂ��A�O�����������P�ʂ̔z��𐶐����A�ԋp����B
        int l_intCount = l_lisRecords.size();
        WEB3FeqOrderUnit[] l_orderUnits = new WEB3FeqOrderUnit[l_intCount];
        for (int i = 0; i < l_intCount; i++)
        {
            FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisRecords.get(i);
            l_orderUnits[i] = (WEB3FeqOrderUnit)l_orderManager.toOrderUnit(l_orderUnitRow);
        }
        
        
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnits;
    }
    
    /**
     * (create�O�������������Ɖ�Unit�ꗗ)<BR>
     * �����̒����P�ʈꗗ���A�O�������������Ɖ�Unit�̈ꗗ��<BR>
     * �쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO�������������Ɖ�T�[�r�X)create�O�������������Ɖ�Unit�ꗗ�v<BR>
     * �Q��<BR>
     * @@param l_orderUnits - (�����P�ʈꗗ)<BR>
     * �O�����������P�ʃI�u�W�F�N�g�̔z��<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefUnit
     * @@throws WEB3BaseException
     * @@roseuid 42A6924C0272
     */
    protected WEB3AdminORFeqOrderExecutionRefUnit[] createFeqOrderExecInquiryUnitList(
        WEB3FeqOrderUnit[] l_orderUnits) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createFeqOrderExecInquiryUnitList(WEB3FeqOrderUnit )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 ArrayList( )
        ArrayList l_arrayList = new ArrayList();
        
        //1.2 �p�����[�^.�����P�ʈꗗ�̗v�f����Loop����
        int l_intCount = 0;
        if (l_orderUnits != null)
        {
            l_intCount = l_orderUnits.length;
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "FinApp�����݂��Ȃ��B");
        }

        WEB3GentradeFinObjectManager l_gentradeFinObjectManager = 
            (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();        
        if (l_gentradeFinObjectManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "�g�����Z�I�u�W�F�N�g�}�l�[�W�������݂��Ȃ��B");
        }
        
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "�O�����������}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3AdminOrderExecuteDataManager l_dataManager = new WEB3AdminOrderExecuteDataManager();
        for (int i = 0; i < l_intCount; i++)
        {
            //1.2.1 getProduct( )
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnits[i].getProduct();
            
            //1.2.2 isMarketOrder( )
            boolean l_blnIsMarketOrder = l_orderUnits[i].isMarketOrder();
            
            //1.2.3 get���X�R�[�h( )
            String l_strBranchCode = l_orderUnits[i].getBranchCode();
            
            //1.2.4 get�ڋq( )
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_orderUnits[i].getMainAccount();
            
            //1.2.5 get�s��( )
            Market l_market = l_orderUnits[i].getMarket();
            
            //1.2.6 get������ԋ敪( )
            String l_strOrderStatusDiv = l_orderUnits[i].getOrderStatusDiv();
            
            //1.2.7 get����ԋ敪( )
            String l_strExecStatusDiv = l_orderUnits[i].getExecStatusDiv();
            
            //1.2.8 get�^�p�R�[�h( )
            String l_strOrderEmpCode = l_orderUnits[i].getOrderEmpCode();
            
            //1.2.9 getTrader(arg0 : long)
            Trader l_trader = null;
            FeqOrderUnitRow l_row = (FeqOrderUnitRow)l_orderUnits[i].getDataSourceObject();
            if (!l_row.getTraderIdIsNull())
            {
                try
                {
                    l_trader = l_gentradeFinObjectManager.getTrader(l_orderUnits[i].getTraderId());
                }
                catch (NotFoundException l_ex)
                {
                    String l_strMessage = "Trader�������݂��Ȃ��BTraderId = �u" 
                        + l_orderUnits[i].getTraderId() + "�v";
                    log.error(l_strMessage, l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage,
                        l_ex);
                }
            }
            
            //1.2.10 get����敪(OrderTypeEnum)
            String l_strTradingType = l_dataManager.getTradingType(l_orderUnits[i].getOrderType());
            
            //1.2.11 get���s�����iSONAR�j(���s���� : String)
            String l_strExecutionConditionTypeSonar = l_orderManager.getExecutionConditionTypeSonar(
                l_orderUnits[i].getExecutionConditionType().intValue() + "");//WEB3BusinessLayerException

            //1.2.12 is�o����܂Œ����P��(�����P�� : FeqOrderUnit)
            boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(l_orderUnits[i]);
            
            //1.2.13 �Ǘ��ҊO�������������Ɖ�Unit( )
            WEB3AdminORFeqOrderExecutionRefUnit l_feqOrderExecutionRefUnit = 
                new WEB3AdminORFeqOrderExecutionRefUnit();
                
            //1.2.14 �v���p�e�B�Z�b�g
            //********** ���ʕ��� **********
            //ID     ���@@�����P��.����ID
            l_feqOrderExecutionRefUnit.id = l_orderUnits[i].getOrderId() + "";
            
            //���X�R�[�h  ���@@get���X�R�[�h()�̖߂�l
            l_feqOrderExecutionRefUnit.branchCode = l_strBranchCode;
            
            //�ڋq�R�[�h  ���@@get�ڋq()�̖߂�l.get�\���ڋq�R�[�h()
            l_feqOrderExecutionRefUnit.accountCode = l_mainAccount.getDisplayAccountCode();
            
            //���i�敪   ���@@"�O������"
            l_feqOrderExecutionRefUnit.productDiv = WEB3AdminProductDivDef.FEQ;
            
            //����敪   ���@@get����敪()�̖߂�l���Z�b�g�B
            l_feqOrderExecutionRefUnit.tradingType = l_strTradingType;
            
            //��������   ���@@�����P��.��������
            l_feqOrderExecutionRefUnit.orderQuantity = 
                WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getQuantity());
            
            //�����P���敪 ���@@isMarketOrder()�̖߂�l == true�̏ꍇ�A"���s"���Z�b�g�B
            //�@@�@@�@@                    false�̏ꍇ�A"�w�l"���Z�b�g�B
            if (l_blnIsMarketOrder)
            {
                l_feqOrderExecutionRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;

                //�����P��   ���@@�����P���敪 ==�@@"�w�l"�̏ꍇ�A�����P��.�w�l���Z�b�g�B
            }
            else
            {
                l_feqOrderExecutionRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                l_feqOrderExecutionRefUnit.orderPrice = 
                    WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getLimitPrice());
            }
            
            //�T�Z��n��� ���@@(*3)
            // �����P��.get����ԋ敪() == "�ꕔ����" or "�S������"�̏ꍇ�A�ȉ��̒l���Z�b�g����B
            // �@@�T�Z��n���    ���@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����(�����P��)�ɂĎ擾����
            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�g�����U�N�V����.��n����̍��v�l
            WEB3FeqFinTransactionManager l_finTransaction = 
                (WEB3FeqFinTransactionManager)l_tradingModule.getFinTransactionManager();
            if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_strExecStatusDiv) ||
                WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_strExecStatusDiv))
            {
                double l_dblNetAmount = l_finTransaction.getNetAmount(l_orderUnits[i]);
                //���������̏ꍇ�A�f�~(-1)�f���Ă���Z�b�g
                if(l_orderUnits[i].isBuy()){
                    l_feqOrderExecutionRefUnit.estimatedPrice = 
                                        WEB3StringTypeUtility.formatNumber(l_dblNetAmount * (-1));
                }else{
                    l_feqOrderExecutionRefUnit.estimatedPrice = 
                                        WEB3StringTypeUtility.formatNumber(l_dblNetAmount);
                }
            }
                
            //�����`���l�� ���@@�����P��.���񒍕��̒����`���l��
            l_feqOrderExecutionRefUnit.orderChannel = l_row.getOrderChanel();
            
            //������ԋ敪 ���@@get������ԋ敪()�̖߂�l
            l_feqOrderExecutionRefUnit.orderState = l_strOrderStatusDiv;
            
            //��������   ���@@�����P��.�󒍓���
            l_feqOrderExecutionRefUnit.orderDate = l_row.getReceivedDateTime();
             
            //������        ���@@�����P��.������
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            l_feqOrderExecutionRefUnit.orderBizDate = 
                WEB3DateUtility.toDay(l_datBizDate);
            
            //���s����   ���@@get���s�����iSONAR�j()�̖߂�l
            l_feqOrderExecutionRefUnit.execCondType = l_strExecutionConditionTypeSonar;
            
            //�����L������ ���@@is�o����܂Œ����P��()�̖߂�l == true�̏ꍇ�A�����P��.�����������t���Z�b�g�B
            if (l_blnIsCarriedOrderUnit)
            {
                l_feqOrderExecutionRefUnit.expirationDate =
                    WEB3DateUtility.toDay(l_row.getExpirationDate()); 
            }
            
            //���������敪 ���@@�����P��.��������
            l_feqOrderExecutionRefUnit.orderCondType = 
                l_row.getOrderConditionType();
                
            if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_row.getOrderConditionType())
                || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_row.getOrderConditionType()))
            {
                //���������P�� ���@@(*1)
                if (!l_row.getStopOrderPriceIsNull())
                {
                    l_feqOrderExecutionRefUnit.orderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_row.getStopOrderPrice());                
                }
                //�����������Z�q    ���@@(*1)
                l_feqOrderExecutionRefUnit.condOperator = l_row.getOrderCondOperator();
            }
            
            if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_row.getOrderConditionType()))
            {
                l_feqOrderExecutionRefUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
                if (!l_row.getWLimitPriceIsNull() && l_row.getWLimitPrice() != 0)
                {
                    //W�w�l�p�����P��   ���@@(*2)
                    l_feqOrderExecutionRefUnit.wlimitOrderCondPrice = 
                        WEB3StringTypeUtility.formatNumber(l_row.getWLimitPrice());
                }
                else
                {
                    //W�w�l�p�����P���敪 ���@@(*2)
                    l_feqOrderExecutionRefUnit.wLimitOrderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
                }
            }
            
            //��萔��   ���@@(*3)
            if (!l_orderUnits[i].isUnexecuted())
            {
                l_feqOrderExecutionRefUnit.execQuantity = 
                    WEB3StringTypeUtility.formatNumber(l_orderUnits[i].getExecutedQuantity());
            } 
            
            //���P��   ���@@(*3)
            if (!l_orderUnits[i].isUnexecuted())
            {

               //�����P��.���v�����z(�O��) �^ �����P��.��萔��
                BigDecimal l_bdExecutedAmount = new BigDecimal(
                    String.valueOf(l_orderUnits[i].getExecutedAmount()));
                BigDecimal l_bdExecutedQuantity = new BigDecimal(
                    String.valueOf(l_orderUnits[i].getExecutedQuantity()));
                BigDecimal l_bdExecPriceTemp =
                    l_bdExecutedAmount.divide(l_bdExecutedQuantity, 6, BigDecimal.ROUND_HALF_EVEN);

                l_feqOrderExecutionRefUnit.execPrice = 
                    WEB3StringTypeUtility.formatNumber(l_bdExecPriceTemp.doubleValue());

            }
            
            //���҃R�[�h  ���@@�����P��.�����ID != null�̏ꍇ�A�����ID�ɊY�����鈵��.���҃R�[�h���Z�b�g�B
            if (l_trader != null)
            {
                l_feqOrderExecutionRefUnit.traderCode = l_trader.getTraderCode();
            }
            
            //����ԋ敪 ���@@get����ԋ敪()�̖߂�l
            l_feqOrderExecutionRefUnit.execType = l_strExecStatusDiv;
            
            //��������敪 ���@@�����P��.���������E����敪
            l_feqOrderExecutionRefUnit.changeCancelDiv = l_row.getModifyCancelType();
            
            //�����o�H�敪 ���@@�����P��.�����o�H�敪
            l_feqOrderExecutionRefUnit.orderRootDiv = l_row.getOrderRootDiv();
            
            //��n��        ���@@�����P��.��n��
            l_feqOrderExecutionRefUnit.deliveryDate = 
                WEB3DateUtility.toDay(l_orderUnits[i].getDeliveryDate());
            
            //********** Feq�������� **********
            //�`�[No       ���@@�����P��.�`�[No
            l_feqOrderExecutionRefUnit.orderNumber = l_row.getVoucherNo();
            
            //�����R�[�h  ���@@getProduct()�̖߂�l.�����R�[�h
            l_feqOrderExecutionRefUnit.productCode = l_product.getProductCode();
            
            //���n�����R�[�h    ���@@getProduct()�̖߂�l.���n�����R�[�h
            l_feqOrderExecutionRefUnit.localProductCode = l_product.getOffshoreProductCode();
            
            //�s��R�[�h  ���@@get�s��()�̖߂�l.�s��R�[�h
            l_feqOrderExecutionRefUnit.marketCode = l_market.getMarketCode();
            
            //�����敪   ���@@�����P��.�ŋ敪 == "���"�̏ꍇ�A"���"���Z�b�g�B"����"�̏ꍇ�A"����"���Z�b�g�B
            TaxTypeEnum l_taxType = l_orderUnits[i].getTaxType();
            if (TaxTypeEnum.NORMAL.equals(l_taxType))
            {
                l_feqOrderExecutionRefUnit.taxType = WEB3TaxTypeSpecialDef.NORMAL;
            }
            else if (TaxTypeEnum.SPECIAL.equals(l_taxType))
            {
                l_feqOrderExecutionRefUnit.taxType = WEB3TaxTypeSpecialDef.SPECIAL;
            }

            //�^�p�R�[�h  ���@@get�^�p�R�[�h()�̖߂�l
            l_feqOrderExecutionRefUnit.managementCode = l_strOrderEmpCode;
            
            //���ϋ敪   ���@@�����P��.���ϋ敪
            l_feqOrderExecutionRefUnit.settleDiv = l_row.getSettleDiv();
            
            //�ʉ݃R�[�h  ���@@�����P��.�ʉ݃R�[�h
            l_feqOrderExecutionRefUnit.currencyCode = l_row.getCurrencyCode();
            
            //��n����i�O�݁j   ���@@(*3)
            // �@@�����P��.get����ԋ敪() == "�ꕔ����" or "�S������"�̏ꍇ�A�ȉ��̒l���Z�b�g����B
            // ��n����i�O�݁j    ���@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����(�����P��)�ɂĎ擾����
            // �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�g�����U�N�V����.��n����i�O�݁j�̍��v�l
            if (WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(l_strExecStatusDiv) ||
                WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(l_strExecStatusDiv))
            {
                double l_dblNetAmountFc = l_finTransaction.getNetAmountFc(l_orderUnits[i]);
                //���������̏ꍇ�A�f�~(-1)�f���Ă���Z�b�g
                if(l_orderUnits[i].isBuy()){
                    l_feqOrderExecutionRefUnit.foreignDeliveryPrice = 
                                WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc * (-1));
                }else{
                    l_feqOrderExecutionRefUnit.foreignDeliveryPrice = 
                                WEB3StringTypeUtility.formatNumber(l_dblNetAmountFc);
                }
            }            
            
            //�X�V�҃R�[�h ���@@�����P��.�X�V�҃R�[�h
            l_feqOrderExecutionRefUnit.updaterCode = l_row.getLastUpdater();
            
            //(*1)
            //�����P��.�������� == ("�t�w�l" or "W�w�l")�̏ꍇ�A�ȉ��̒l���Z�b�g�B
            //�@@�@@            ���������P��    ���@@�����P��.�t�w�l��l
            //�@@�@@            �����������Z�q   ���@@�����P��.�����������Z�q
            //
            //(*2)
            //�����P��.�������� == "W�w�l"�̏ꍇ�A�ȉ��̒l���Z�b�g�B
            //W�w�l�p�����P���敪    ���@@W�w�l�p�����P�� == 0�̏ꍇ�A"���s"���Z�b�g�B
            //�@@�@@�@@                        �ȊO�A"�w�l"���Z�b�g�B
            //W�w�l�p�����P��  ���@@�����P��.(W�w�l)�����w�l
            //�@@�@@�@@                        �������P��.(W�w�l)�����w�l == 0�̏ꍇ�Anull���Z�b�g�B
            //
            //(*3)
            //��肪���Ă���ꍇ(�����P��.isUnExecuted() == false)�A�ȉ��̒l���Z�b�g�B
            //�T�Z��n���    ���@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����(�����P��)�ɂĎ擾����
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@            �g�����U�N�V����.��n����̍��v�l
            //��n����i�O�݁j  ���@@�O�������g�����U�N�V�����}�l�[�W��.get�g�����U�N�V����(�����P��)�ɂĎ擾����
            //�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@            �g�����U�N�V����.��n����i�O�݁j�̍��v�l
            //��萔��  ���@@�����P��.��萔��
            //���P��  ���@@�����P��.���v�����z / �����P��.��萔��(�~�����͎l�̌ܓ�)
            
            //1.2.15 add(arg0 : Object)
            l_arrayList.add(l_feqOrderExecutionRefUnit);
        }
        
        //1.3 toArray( )
        WEB3AdminORFeqOrderExecutionRefUnit[] l_feqOrderExecutionRefUnits = 
            new WEB3AdminORFeqOrderExecutionRefUnit[l_arrayList.size()];
        l_arrayList.toArray(l_feqOrderExecutionRefUnits);

        log.exiting(STR_METHOD_NAME);
        return l_feqOrderExecutionRefUnits;
    }
    
    /**
     * (create�\�[�g�����i�o�����͈ꗗ�j)<BR>
     * �o�����͈ꗗ�p�̃\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u����ID�v�@@���@@�O���������.����ID<BR>
     * �@@�@@�E�u�^�p�R�[�h�v�@@���@@�O���������.�^�p�R�[�h<BR>
     * �@@�@@�E�u���X�R�[�h�v�@@���@@�O���������.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@substr(�O���������.����ID, 9, 6)<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@�O���������.����ID<BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@�O���������.�s��ID<BR>
     * �@@�@@�E�u���ʃR�[�h�v�@@���@@�O���������.���ʃR�[�h<BR>
     * �@@�@@�E�u���ԍ��v�@@���@@�O���������.���ʔ�<BR>
     * �@@�@@�E�u����敪�v�@@���@@�O���������.�������<BR>
     * �@@�@@�E�u�������v�@@���@@�O���������.������<BR>
     * �@@�@@�E�u��n���v�@@���@@�O���������.���n��n��<BR>
     * <BR>
     * �@@�Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j�\�[�g���������ɁA���e�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * �S�j�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@roseuid 42AD09470201
     */
    protected String createSortCondExecInputList(WEB3AdminOROrderExecutionSortKeyUnit[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = 
            " createSortCondExecInputList(WEB3AdminOROrderExecutionSortKeyUnit )";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�[�g����������(�FString)���쐬����B
        String l_strSortCond = "";
        
        //�Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        int l_intCount = 0; 
        if (l_sortKeys != null)
        {
            l_intCount = l_sortKeys.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //�@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A
            //�@@�@@�쐬�����\�[�g����������ɒǉ�����B
            //�@@�@@�E�u����ID�v�@@���@@�O���������.����ID
            //�@@�@@�E�u�^�p�R�[�h�v�@@���@@�O���������.�^�p�R�[�h
            //�@@�@@�E�u���X�R�[�h�v�@@���@@�O���������.���XID
            //�@@�@@�E�u�ڋq�R�[�h�v�@@���@@substr(�O���������.����ID, 9, 6)
            //�@@�@@�E�u�����R�[�h�v�@@���@@�O���������.����ID
            //�@@�@@�E�u�s��R�[�h�v�@@���@@�O���������.�s��ID
            //�@@�@@�E�u���ʃR�[�h�v�@@���@@�O���������.���ʃR�[�h
            //�@@�@@�E�u���ԍ��v�@@���@@�O���������.���ʔ�
            //�@@�@@�E�u����敪�v�@@���@@�O���������.�������
            //�@@�@@�E�u�������v�@@���@@�O���������.������
            //�@@�@@�E�u��n���v�@@���@@�O���������.���n��n��
            if (i != 0)
            {
                l_strSortCond += " , ";
            }
            
            String l_strKeyItem = l_sortKeys[i].keyItem;
            if (WEB3AdminFeqSortKeyDef.ORDER_ID.equals(l_strKeyItem))
            {
                l_strSortCond += " order_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.MANAGEMENT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_emp_code ";
            }
            else if (WEB3AdminFeqSortKeyDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " branch_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " substr(account_id, 9, 6) ";
            }
            else if (WEB3AdminFeqSortKeyDef.PRODUCT_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " product_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.MARKET_CODE.equals(l_strKeyItem))
            {
                l_strSortCond += " market_id ";
            }
            else if (WEB3AdminFeqSortKeyDef.REQUEST_NUMBER.equals(l_strKeyItem))
            {
                l_strSortCond += " order_request_number ";
            }
            else if (WEB3AdminFeqSortKeyDef.EXEC_NO.equals(l_strKeyItem))
            {
                l_strSortCond += " exec_serial_no ";
            }
            else if (WEB3AdminFeqSortKeyDef.TRADING_TYPE.equals(l_strKeyItem))
            {
                l_strSortCond += " order_type ";
            }
            else if (WEB3AdminFeqSortKeyDef.ORDER_BIZ_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " biz_date ";
            }
            else if (WEB3AdminFeqSortKeyDef.DELIVERY_DATE.equals(l_strKeyItem))
            {
                l_strSortCond += " f_delivery_date ";
            }

            //�@@�Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����
            //�@@�@@(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " asc ";
            }
            else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
            {
                l_strSortCond += " desc ";
            }
        }

        
        //�R�j�\�[�g���������ɁA���e�[�u��.�X�V���t�������w��ŕt��
        if (l_intCount != 0)
        {
            l_strSortCond += " , ";
        }
        l_strSortCond += " last_updated_timestamp asc ";
        
        //�S�j�쐬�����\�[�g�����������ԋp����B
        
        log.exiting(STR_METHOD_NAME);
        return l_strSortCond;
    }
    
    /**
     * (get���ꗗ)<BR>
     * �����̏����ɊY��������̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�O���������Row.TYPE<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�j�������ʂ��A�O���������̔z��𐶐����A�ԋp����B<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryContainers - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return �O���������
     * @@throws WEB3BaseException
     * @@roseuid 42AD0925005B
     */
    protected WEB3FeqOrderExecution[] getExecList(
        String l_strQueryString, 
        String[] l_strQueryContainers, 
        String l_strSortCond) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getExecList(String, String[], String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //�@@�@@arg0�F�@@�O���������Row.TYPE
        //�@@�@@arg1�F�@@�p�����[�^.��������������
        //�@@�@@arg2�F�@@�p�����[�^.�\�[�g����
        //�@@�@@arg3�F�@@null
        //�@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i
        List l_lisRecords = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                FeqOrderExecutionRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryContainers);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (IllegalStateException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //�@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            return null;
        }
        
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

        //�R�j�������ʂ��A�O���������̔z��𐶐����A�ԋp����B
        int l_intCount = l_lisRecords.size();
        WEB3FeqOrderExecution[] l_orderExcutions = new WEB3FeqOrderExecution[l_intCount];
        for (int i = 0; i < l_intCount; i++)
        {
            WEB3FeqOrderExecution l_orderExcution = (WEB3FeqOrderExecution)
                l_orderManager.toOrderExecution((FeqOrderExecutionRow)l_lisRecords.get(i));
            l_orderExcutions[i] = l_orderExcution;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_orderExcutions;
    }
    
    /**
     * (create�o�����͖��׈ꗗ)<BR>
     * �����̖��ꗗ���A�O�������o�����͖��ׂ̈ꗗ��<BR>
     * �쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�@@�O�������o�����͖��׃C���X�^���X�𐶐�����B<BR>
     * �@@�Q�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@ID�@@���@@���.����ID<BR>
     * �@@�@@�^�p�R�[�h�@@���@@���.�^�p�R�[�h<BR>
     * �@@�@@���ʃR�[�h�@@���@@���.���ʃR�[�h<BR>
     * �@@�@@���X�R�[�h�@@���@@���.���XID�ɊY�����镔�X�R�[�h<BR>
     * �@@�@@�ڋq�R�[�h�@@���@@���.����ID�ɊY������ڋq.get�\���ڋq�R�[�h()<BR>
     * �@@�@@�����R�[�h�@@���@@���.����ID�ɊY����������R�[�h<BR>
     * �@@�@@���n�����R�[�h�@@���@@���.����ID�ɊY�����錻�n�����R�[�h<BR>
     * �@@�@@����敪�@@���@@�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get����敪<BR>
     * (���.�������)<BR>
     * �@@�@@�s��R�[�h�@@���@@���.�s��ID�ɊY������s��R�[�h<BR>
     * �@@�@@�������@@���@@���.������<BR>
     * �@@�@@���ԍ��@@���@@���.���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g����������B<BR>
     * �@@�@@���P���@@���@@���.���P��<BR>
     * �@@�@@��萔�ʁ@@���@@���.��萔��<BR>
     * �@@�@@���בց@@���@@���.�בփ��[�g<BR>
     * �@@�@@�����@@���@@���.����<BR>
     * �@@�@@��n���@@���@@���.��n��<BR>
     * �@@�@@���n��n���@@���@@���.���n��n��<BR>
     * �@@�@@�X�V�҃R�[�h�@@���@@���.�X�V�҃R�[�h<BR>
     * <BR>
     * �@@�Q�|�R�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_execs - (���ꗗ)<BR>
     * �O���������I�u�W�F�N�g�̔z��<BR>
     * @@return WEB3AdminORFeqOrderExecutionRefUnit
     * @@throws WEB3BaseException
     */
    protected WEB3AdminORFeqExecutionUnit[] createExecInputDetailList(
        WEB3FeqOrderExecution[] l_orderExecution) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " createExecInputDetailList(WEB3FeqOrderExecution[])";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@ArrayList�𐶐�����B
        ArrayList l_arrayList = new ArrayList();
        
        //�Q�j�@@�p�����[�^.���ꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B
        int l_intCount = 0;
        if (l_orderExecution != null)
        {
            l_intCount = l_orderExecution.length;
        }
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        if (l_accountManager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                 this.getClass().getName() + STR_METHOD_NAME,
                "�g���A�J�E���g�}�l�[�W�������݂��Ȃ��B");
        }
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "�O�������v���_�N�g�}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        for (int i = 0; i < l_intCount; i++)
        {
            //�@@�Q�|�P�j�@@�O�������o�����͖��׃C���X�^���X�𐶐�����B
            WEB3AdminORFeqExecutionUnit l_feqExecutionUnit = new WEB3AdminORFeqExecutionUnit();            
            
            FeqOrderExecutionRow l_row = 
                (FeqOrderExecutionRow)l_orderExecution[i].getDataSourceObject(); 
            //�@@�Q�|�Q�j�@@���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B        
            //�@@�@@ID�@@���@@���.����ID
            l_feqExecutionUnit.id = l_orderExecution[i].getOrderId() + "";
            
            //�@@�@@�^�p�R�[�h�@@���@@���.�^�p�R�[�h
            l_feqExecutionUnit.managementCode = l_row.getOrderEmpCode();
                        
            //�@@�@@���ʃR�[�h�@@���@@���.���ʃR�[�h
            l_feqExecutionUnit.requestNumber = l_row.getOrderRequestNumber();
            
            //�@@�@@���X�R�[�h�@@���@@���.���XID�ɊY�����镔�X�R�[�h
            WEB3GentradeBranch l_branch = null;
            try
            {
                l_branch = 
                    (WEB3GentradeBranch) l_accountManager.getBranch(l_orderExecution[i].getBranchId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "���X�����݂��Ȃ��B";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.branchCode = l_branch.getBranchCode();
            
            //�@@�@@�ڋq�R�[�h�@@���@@���.����ID�ɊY������ڋq.get�\���ڋq�R�[�h()
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = (WEB3GentradeMainAccount) 
                    l_accountManager.getMainAccount(l_orderExecution[i].getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "�ڋq�����݂��Ȃ��B";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.accountCode = l_mainAccount.getDisplayAccountCode();
            
            //�@@�@@�����R�[�h�@@���@@���.����ID�ɊY����������R�[�h
            WEB3FeqProduct l_product = null;
            try
            {
                l_product = 
                    (WEB3FeqProduct) l_productManager.getProduct(l_orderExecution[i].getProductId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "�O���������������݂��Ȃ��B";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.productCode = l_product.getProductCode();
            
            //�@@�@@���n�����R�[�h�@@���@@���.����ID�ɊY�����錻�n�����R�[�h
            l_feqExecutionUnit.localProductCode = l_product.getOffshoreProductCode();
            
            //�@@�@@����敪�@@���@@�Ǘ��Ғ������Ɖ�f�[�^�}�l�[�W��.get����敪
            //(���.�������)
            WEB3AdminOrderExecuteDataManager l_dataManager = new WEB3AdminOrderExecuteDataManager();
            l_feqExecutionUnit.tradingType = 
                l_dataManager.getTradingType(l_orderExecution[i].getOrderType());
            
            //�@@�@@�s��R�[�h�@@���@@���.�s��ID�ɊY������s��R�[�h
            WEB3GentradeFinObjectManager l_finObjManager = 
                (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            if (l_finObjManager == null)
            {
                String l_strMessage = "�g�����Z�I�u�W�F�N�g�}�l�[�W�������݂��Ȃ��B";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            Market l_market = null; 
            try
            {
                l_market = l_finObjManager.getMarket(l_orderExecution[i].getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                String l_strMessage = "�s�ꂪ���݂��Ȃ��B";
                log.error(l_strMessage, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    l_strMessage,
                    l_ex);
            }
            l_feqExecutionUnit.marketCode = l_market.getMarketCode();
            
            //�@@�@@�������@@���@@���.������
            Date l_datBizDate = WEB3DateUtility.getDate(l_row.getBizDate(), "yyyyMMdd");
            l_feqExecutionUnit.orderBizDate = WEB3DateUtility.toDay(l_datBizDate);
            
            //�@@�@@���ԍ��@@���@@���.���ʔԂ��Apattern="000"�Ńt�H�[�}�b�g����������B
            l_feqExecutionUnit.execNo = 
                WEB3StringTypeUtility.formatNumber(l_row.getExecSerialNo(), 3);
            
            //�@@�@@���P���@@���@@���.���P��
            if (!l_row.getExecPriceIsNull())
            {
                l_feqExecutionUnit.execPrice = 
                    WEB3StringTypeUtility.formatNumber(l_row.getExecPrice());            
            }
            
            //�@@�@@��萔�ʁ@@���@@���.��萔��
            l_feqExecutionUnit.execQuantity = 
                WEB3StringTypeUtility.formatNumber(l_row.getExecQuantity());
            
            //�@@�@@���בց@@���@@���.�בփ��[�g
            l_feqExecutionUnit.execExchangeRate = 
                WEB3StringTypeUtility.formatNumber(l_row.getFxRate());
            
            //�@@�@@�����@@���@@���.����
            l_feqExecutionUnit.executionDate = l_row.getExecTimestamp();
            
            //�@@�@@��n���@@���@@���.��n��
            l_feqExecutionUnit.deliveryDate = WEB3DateUtility.toDay(l_row.getDeliveryDate());
            
            //�@@�@@���n��n���@@���@@���.���n��n��
            l_feqExecutionUnit.localDeliveryDate = WEB3DateUtility.toDay(l_row.getFDeliveryDate());
            
            //�@@�@@�X�V�҃R�[�h�@@���@@���.�X�V�҃R�[�h
            l_feqExecutionUnit.updaterCode = l_row.getLastUpdater();
        
            //�@@�Q�|�R�j�@@ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
            l_arrayList.add(l_feqExecutionUnit);
        }
        
        //�R�j�@@ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3AdminORFeqExecutionUnit[] l_feqExecutionUnit = 
            new WEB3AdminORFeqExecutionUnit[l_arrayList.size()];
        l_arrayList.toArray(l_feqExecutionUnit);
        
        log.exiting(STR_METHOD_NAME);
        return l_feqExecutionUnit;
    }
}
@
