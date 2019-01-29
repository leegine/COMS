head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqOrderAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������������t����F�؃T�[�r�XImpl(WEB3AdminFeqOrderAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �s�p (���u) �V�K�쐬
Revesion History : 2005/08/03 ����(���u) ���r���[ 
Revesion History : 2006/09/18 �����(���u) �d�l�ύX�E���f��244     
Revesion History : 2006/12/19 ���G��(���u) ���f���@@No.315�Ή�
Revesion History : 2006/12/19 ���G��(���u) ���f���@@�c�a�X�V�d�l 079
Revesion History : 2006/12/19 ���G��(���u) ���f���@@�c�a�X�V�d�l 080
Revesion History : 2007/01/16 �����(���u) ���f���@@No.334�Ή�
Revesion History : 2007/02/25 ꎉ�  (���u) ���f���@@No.345�Ή�
Revesion History : 2009/08/03 �Ԑi(���u)   ���f���@@No.510  No.511�Ή�
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.define.WEB3FeqAcceptDivDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqExecuteGroup;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelInputResponse;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest;
import webbroker3.feq.message.WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse;
import webbroker3.feq.message.WEB3FeqOrderAcceptCancelUnit;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptService;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqCommonMessageCreatedService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.util.WEB3FeqLogUtility;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO������������t����F�؃T�[�r�XImpl)<BR>
 * �Ǘ��ҊO������������t����F�؃T�[�r�X�����N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqOrderAcceptServiceImpl implements WEB3AdminFeqOrderAcceptService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptServiceImpl.class);
        
    /**
     * @@roseuid 42CE39F2006D
     */
    public WEB3AdminFeqOrderAcceptServiceImpl() 
    {
     
    }
    
    /**
     * �O�����������i��������j��t���͏��������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get�������()<BR>
     * �|get���͉��()<BR>
     * �|submit��t()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 429FEB91032D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest)
        {
            //get�������()
            l_response = 
                this.getSearchScreen((WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptCancelInputRequest)
        {
            //get���͉��()
            l_response = 
                this.getInputScreen((WEB3AdminFeqOrderAcceptCancelInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqOrderAcceptCancelCompleteRequest)
        {
            //submit��t()
            l_response = 
                this.submitAccept((WEB3AdminFeqOrderAcceptCancelCompleteRequest)l_request);
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
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
     * (get�������)<BR>
     * ������ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)��t�F�؁jget������ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptSearchCondInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FEC2600FA
     */
    protected WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse getSearchScreen(
        WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSearchScreen(WEB3AdminFeqOrderAcceptSearchCondInputRequest) ";
        log.entering(STR_METHOD_NAME);

        //1.1:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.2:validate����(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.3:get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�戵�\�s��(�،���ЃR�[�h : String, �����^�C�v : ProductTypeEnum)
        //�戵�\�Ȏs��R�[�h�̈ꗗ���擾����B
        //[����]
        //�،���ЁF�@@get�،���ЃR�[�h()�̖߂�l
        //�����^�C�v�F�@@ProductTypeEnum.�O������
        String[] l_strMarkets =
            WEB3GentradeFeqBranchMarketDealtCond.getHandlingPossibleMarket(
                l_strInstitutionCode,
                ProductTypeEnum.FOREIGN_EQUITY);
        
        int l_intCnt = 0;

        if (l_strMarkets != null && l_strMarkets.length > 0)
        {
            l_intCnt = l_strMarkets.length;
        }

        log.debug("HandlingPossibleMarket length ==== " + l_intCnt);
        
        //��̃��X�g�𐶐�����
        //�i��s�꒼���p�j
        List l_lisMarketCode1 = new ArrayList();

        //��̃��X�g�𐶐�����
        //�i�s�꒼���p�j
        List l_lisMarketCode2 = new ArrayList();

        //get�戵�\�s��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_intCnt; i++)
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

        //1.5:createResponse( )
        WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse l_response = 
           (WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse)l_request.createResponse();

        //1.6:(*) �v���p�e�B�Z�b�g
        //�������F�@@�����iTradingSystem.getSystemTimestamp()�̓��t�����j
        l_response.orderBizDate = WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp());

        //�s��R�[�h�ꗗ[]�F�@@ArrayList�̔�s�꒼���p�A�s�꒼���p�̏��Őݒ�
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

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * ���͉�ʂ̕\���ɕK�v�ȃf�[�^���擾����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)��t�F�؁jget���͉�ʁv �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91034C
     */
    protected WEB3AdminFeqOrderAcceptCancelInputResponse getInputScreen(WEB3AdminFeqOrderAcceptCancelInputRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqOrderAcceptInputRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.3:validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)(
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:get�،����( )
        WEB3GentradeInstitution l_institution = (WEB3GentradeInstitution)l_admin.getInstitution();

        //get�^�p�R�[�h
        //�V���́u�^�p�R�[�h�v��������擾����B
        //[get�^�p�R�[�h()�Ɏw�肷�����]
        //�،���ЃR�[�h�F�،����.get�،���ЃR�[�h( )�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService) Services.getService(
            WEB3FeqOrderEmpCodeGettingService.class);

        String l_strManagementCode = l_feqOrderEmpCodeGettingService.getEmpCode(
            l_institution.getInstitutionCode(), l_request.managementCode);
 
        //1.5:get�����P��(�،����, �Ǘ��ҊO������������t����F�ؓ��̓��N�G�X�g)
        WEB3FeqOrderUnit[] l_orderUnits = this.getOrderUnits(l_institution, l_request, l_strManagementCode);

        //1.6:WEB3PageIndexInfo(l_objs�i=get�����P��()�̖߂�l�j : 
        //Object[], l_intRequestPageIndex : int, l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageInfo = new WEB3PageIndexInfo(
            l_orderUnits, 
            Integer.parseInt(l_request.pageIndex), 
            Integer.parseInt(l_request.pageSize));
        
        //1.7:getArrayReturned( )
        Object[] l_objArrayReturns = l_pageInfo.getArrayReturned();

        //1.8:(*) �\���Ώۃy�[�W�ɊY������I�u�W�F�N�g�v�f��LOOP����
        WEB3AdminFeqExecuteGroup[] l_executeGroups = null;
        
        if (l_objArrayReturns != null && l_objArrayReturns.length > 0)
        {
            int l_intCnt = l_objArrayReturns.length;
            l_executeGroups = new WEB3AdminFeqExecuteGroup[l_intCnt];
            
            for (int i = 0; i < l_intCnt; i++)
            {
                //1.8.1: �O�������������ׁi�Ǘ��ҁj( )
                l_executeGroups[i] = new WEB3AdminFeqExecuteGroup();
                
                //1.8.2:create�O�������������ׁi�Ǘ��ҁj(�O�������������ׁi�Ǘ��ҁj, �O�����������P��)
                WEB3FeqCommonMessageCreatedService l_comMessCreatedService = 
                    new WEB3FeqCommonMessageCreatedServiceImpl();
                
                l_comMessCreatedService.createAdminFeqExecuteGroup(l_executeGroups[i], (WEB3FeqOrderUnit)l_objArrayReturns[i]);
            }
        }
        
        //1.9:getTotalPages( )
        int l_intTotalPage = l_pageInfo.getTotalPages();
        
        //1.10:getTotalRecords( )
        int l_intTotalRecord = l_pageInfo.getTotalRecords();
        
        //1.11:getPageIndex( )
        int l_intPageIndex = l_pageInfo.getPageIndex();
        
        //1.12:createResponse( )
        WEB3AdminFeqOrderAcceptCancelInputResponse l_response = 
           (WEB3AdminFeqOrderAcceptCancelInputResponse)l_request.createResponse();
           
        //1.13:(*) �v���p�e�B�Z�b�g
        //(*) �v���p�e�B�Z�b�g
        //���X�|���X�f�[�^�v���p�e�B�Ɉȉ��̒ʂ�l���Z�b�g����B
        //�����ꗗ[]�F�@@���������O�������������ׁi�Ǘ��ҁj[]]
        l_response.executeGroups = l_executeGroups;
        
        //�\���y�[�W�ԍ��F�@@getPageIndex()
        l_response.pageIndex = Integer.toString(l_intPageIndex);
        
        //���y�[�W���F�@@getTotalPages()
        l_response.totalPages = Integer.toString(l_intTotalPage);
        
        //�����R�[�h���F�@@getTotalRecords()
        l_response.totalRecords = Integer.toString(l_intTotalRecord);
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit��t)<BR>
     * �����i��������j��t���͊����������s���B<BR>
     * <BR>
     * �V�[�P���X�}�u�i(��)��t�F�؁jsubmit��t�v �Q�ƁB<BR>
     * ========================================================<BR>
     * �@@�@@:  1.7 ���͍s�ɃG���[������ꍇ�i�G���[�^�p�R�[�hList.size() != 0�j�A<BR>
     * �@@�@@��O���X���[����<BR> 
     * �@@�@@���͍s�ɃG���[������ꍇ�i�G���[�^�p�R�[�hList.size() != 0�j�A<BR>
     * �@@�@@��O���X���[����<BR> 
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:  BUSINESS_ERROR_02169<BR>
     * ========================================================<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqOrderAcceptCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 429FEB91036B
     */
    protected WEB3AdminFeqOrderAcceptCancelCompleteResponse submitAccept(
        WEB3AdminFeqOrderAcceptCancelCompleteRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " submitAccept(WEB3AdminFeqOrderAcceptCompleteRequest) ";
        log.entering(STR_METHOD_NAME);
       
        //1.1: validate( )
        l_request.validate();
        
        //1.2:getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();        
        
        //1.3:validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, true);
        
        //1.4:ArrayList()
        List l_lisOrderEmpCodes = new ArrayList();

        //1.5:(*) ���N�G�X�g�f�[�^.������t����ꗗ[]�e�v�f����LOOP����
        if (l_request.orderAcceptCancelList != null && l_request.orderAcceptCancelList.length > 0)
        {
            int l_intCnt = l_request.orderAcceptCancelList.length;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            WEB3FeqOrderManager l_orderMgr = 
                (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
            
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderAcceptCancelUnit l_orderAcceptCancelUnit = l_request.orderAcceptCancelList[i];
                
                //1.5.1:(*) �i������t����ꗗ[index].�ύX���t�敪 == null�j�̏ꍇ�Acontinue;
                if (l_orderAcceptCancelUnit.aftChangeAcceptDiv == null)
                {
                    continue;
                }
                
                //1.5.2:get�����P��ByOrderId(long)
                WEB3FeqOrderUnit l_orderUnit = 
                    (WEB3FeqOrderUnit)l_orderMgr.getOrderUnitByOrderId(Long.parseLong(l_orderAcceptCancelUnit.orderId));
                
                //1.5.3:is�o���I��( )
                boolean l_blnIsExecEnd = l_orderUnit.isExecEnd();
                
                //1.5.4:(*) �o���I����iis�o���I��() == true�j
                if (l_blnIsExecEnd)
                {
                    //1.5.4.1:get�^�p�R�[�h( )
                    String l_strOrderEmpCode = l_orderUnit.getOrderEmpCode();
                    
                    //1.5.4.2:add(arg0�i=�^�p�R�[�h�j
                    l_lisOrderEmpCodes.add(l_strOrderEmpCode);                    
                }
                
                //1.5.5:is������t�\(String)
                boolean l_blnIsOrderAcceptPoss = 
                    l_orderUnit.isOrderAcceptPoss(l_orderAcceptCancelUnit.aftChangeAcceptDiv);
                    
                //1.5.6:(*) ��t�s�iis������t�\() == false�j�̏ꍇ
                if (!l_blnIsOrderAcceptPoss)
                {
                    //1.5.6.1:get�^�p�R�[�h( )
                    String l_strOrderEmpCode = l_orderUnit.getOrderEmpCode();
                    
                    //1.5.6.2:add(arg0�i=�^�p�R�[�h�j
                    l_lisOrderEmpCodes.add(l_strOrderEmpCode);    
                }
            }
        }
        
        //1.6:size()
        int l_intListSize = l_lisOrderEmpCodes.size();
            
        //1.7:(*) ���͍s�ɃG���[������ꍇ�i�G���[�^�p�R�[�hList.size() != 0�j�A��O���X���[����
        if (l_intListSize != 0)
        {
            //����O�̒ǉ�������
            //�iWEB3BaseException.errorMessage�j
            // �ɉ^�p�R�[�hList�̓��e���Z�b�g����B
            // �h�^�p�R�[�h�C�^�p�R�[�h�C�C�C�h
            StringBuffer l_sbErrorMessage = new StringBuffer();
            
            for (int i = 0; i < l_intListSize; i++)
            {
                l_sbErrorMessage.append(l_lisOrderEmpCodes.get(i) + ", "); 
            }
            
            String l_strErrorMessage = l_sbErrorMessage.toString();
            l_strErrorMessage = l_strErrorMessage.substring(0, l_strErrorMessage.length() - 2);
            
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);                        
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02169,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strErrorMessage);
        }
        
        //1.8:(*) ���N�G�X�g�f�[�^.������t����ꗗ[]�e�v�f����LOOP����
        if (l_request.orderAcceptCancelList != null && l_request.orderAcceptCancelList.length > 0)
        {
            int l_intCnt = l_request.orderAcceptCancelList.length;
            log.debug("���N�G�X�g�f�[�^.������t����ꗗ[]�̗v�f�� = " + l_intCnt);
                         
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3FeqOrderAcceptCancelUnit l_orderAcceptCancelUnit = l_request.orderAcceptCancelList[i];
                    
                //1.8.1:(*) �i������t����ꗗ[index].�ύX���t�敪 == null�j�̏ꍇ�Acontinue;
                if (l_orderAcceptCancelUnit.aftChangeAcceptDiv == null)
                {
                    continue;
                }
                
                log.debug("������t����ꗗ[ " + i + "].�ύX���t�敪 = " + l_orderAcceptCancelUnit.aftChangeAcceptDiv);

                //get�s�ꃌ�X�|���X���b�Z�[�W
                //�s��R�[���o�b�N�T�[�r�X�̈����𐶐�����B
                //[get�s�ꃌ�X�|���X���b�Z�[�W()�Ɏw�肷�����]
                //�@@����ID�F
                //�@@�@@���N�G�X�g�f�[�^.������t����ꗗ[index].����ID
                //�@@�ύX���t�敪�F
                //�@@�@@���N�G�X�g�f�[�^.������t����ꗗ[index].�ύX���t�敪
                WEB3FeqAcceptUpdateService l_feqAcceptUpdateService =
                    (WEB3FeqAcceptUpdateService)Services.getService(
                        WEB3FeqAcceptUpdateService.class);
                MarketResponseMessage l_marketResponseMessage =
                    l_feqAcceptUpdateService.getMarketResponseMessage(
                        Long.parseLong(l_orderAcceptCancelUnit.orderId),
                        l_orderAcceptCancelUnit.aftChangeAcceptDiv);

                //update��t(MarketResponseMessage)
                //��t�X�V�������s���B
                //[update��t()�Ɏw�肷�����]
                //�@@arg0�F
                //�@@�@@get�s�ꃌ�X�|���X���b�Z�[�W()�̖߂�l
                l_feqAcceptUpdateService.updateAccept(l_marketResponseMessage);
            }
        }

        //1.9:createResponse( )
        WEB3AdminFeqOrderAcceptCancelCompleteResponse l_response = 
           (WEB3AdminFeqOrderAcceptCancelCompleteResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�����P��)<BR>
     * �����ɓ��Ă͂܂钍���P�ʂ��w��̏��ԂɎ擾����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�Ŏw�肳�ꂽ�����ɂĊO�����������P�ʃe�[�u������������B<BR>
     * �擾�s�I�u�W�F�N�g�ɂĊO�����������P�ʂ𐶐����A�z��ŕԋp����B<BR>
     * �Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02165<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�O�����������P��.�،���ЃR�[�h = �،����.getInstitutionCode() And<BR>
     * �@@�O�����������P��.���X�h�c = ���X�h�c(*1) And ���w�肪����ꍇ�̂�<BR>
     * �@@�O�����������P��.�����h�c = �����h�c(*2) And ���w�肪����ꍇ�̂�<BR>
     * �@@�O�����������P��.������  = ���N�G�X�g�f�[�^.������ And ���w�肪����ꍇ�̂�<BR>
     * �@@�O�����������P��.�^�p�R�[�h = ����.�^�p�R�[�h And <BR>
     * ���w�肪����ꍇ�̂�<BR>
     * �@@�O�����������P��.�s��h�c = �s��h�c(*3) And ���w�肪����ꍇ�̂�<BR>
     * �@@�O�����������P��.�����h�c = �����h�c(*4) And ���w�肪����ꍇ�̂�<BR>
     * �@@�O�������P��.�o���I���������� = null And<BR>
     * �@@�O�����������P��.������� in (*5)<BR>
     *   �O�����������P��.�s�ꂩ��m�F�ς̐��� = null �������́@@not null
     *   �i��t�敪 == 0�F������t���̏ꍇ null�A��t�敪 == 2�F������t�ς̏ꍇ not null�j
     * <BR>
     * �@@(*1)�@@���X�h�c<BR>
     * �@@�@@�@@�A�J�E���g�}�l�[�W��.getBranch().getBranchId()�ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@�@@[getBranch()�Ɏw�肷�����]<BR>
     * �@@�@@�@@institution�F�@@�،����<BR>
     * �@@�@@�@@branchCode�F�@@���N�G�X�g�f�[�^.���X�R�[�h<BR>
     * <BR>
     * �@@(*2)�@@�����h�c<BR>
     * �@@�@@�A�J�E���g�}�l�[�W��.get�ڋq().getAccountId()�ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@�@@[get�ڋq()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�،����.getInstitutionCode()<BR>
     * �@@�@@�@@���X�R�[�h�F�@@���N�G�X�g�f�[�^.���X�R�[�h<BR>
     * �@@�@@�@@�����R�[�h�F�@@���N�G�X�g�f�[�^.�ڋq�R�[�h<BR>
     * <BR>
     * �@@(*3)�@@�s��h�c<BR>
     * �@@�@@�@@���Z�I�u�W�F�N�g�}�l�[�W��.getMarket().getMarketId()�ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@�@@[getMarket()�Ɏw�肷�����]<BR>
     * �@@�@@�@@institution�F�@@�،����<BR>
     * �@@�@@�@@marketCode�F�@@���N�G�X�g�f�[�^.�s��R�[�h<BR>
     * <BR>
     * �@@(*4)�@@�����h�c<BR>
     * �@@�@@�@@�O�������v���_�N�g�}�l�[�W��.get�O����������().getProductId()�ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@�@@[get�O����������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЁF�@@�،����<BR>
     * �@@�@@�@@�����R�[�h�F�@@���N�G�X�g�f�[�^.�����R�[�h<BR>
     * <BR>
     * �@@(*5)�@@���N�G�X�g�f�[�^.��t�敪�ɂĎw�肳�ꂽ�R�[�h�l�ɑΉ�����ȉ��̒l<BR>
     * �@@�@@�i��t�敪 == 0�F������t���j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@OrderStatusEnum.2:�������i�V�K�����j�C10:�����ρi�ύX�����j<BR>
     * �@@�@@�i��t�敪 == 1�F����������j�̏ꍇ�A<BR>
     * �@@�@@�@@�@@OrderStatusEnum.8:�������i�ύX�����j�C13:�������i��������j<BR>
     * �@@�@@�i��t�敪 == 2�F������t�ρj�̏ꍇ�A<BR>
     * �@@�@@�@@�@@OrderStatusEnum.3:�����ρi�V�K�����j�C10:�����ρi�ύX�����j�C<BR>
     *                        14:�����ρi��������j<BR>
     * �@@�@@��t�敪�̎w�肪�Ȃ��i��t�敪 == null�j�ꍇ�A�ȉ��̂��ׂāB<BR>
     * �@@�@@�@@OrderStatusEnum.2:�������i�V�K�����j�C3:�����ρi�V�K�����j�C<BR>
     * �@@�@@�@@8:�������i�ύX�����j�C10:�����ρi�ύX�����j�C11:�������s�i�ύX�����j�C<BR>
     * �@@�@@�@@13:�������i��������j�C14:�����ρi��������j�C15:�������s�i��������j<BR>
     * <BR>
     * �@@[�擾��]<BR>
     * �@@�i���N�G�X�g�f�[�^.�\�[�g�L�[�̎w��̒ʂ�j<BR>
     * <BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == �^�p�R�[�h�j�̏ꍇ�A�O�����������P��.�^�p�R�[�h<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == �����ԍ��j�̏ꍇ�A�O�����������P��.�����h�c<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == ���X�R�[�h�j�̏ꍇ�A�O�����������P��.���X�h�c��<BR>
     * 3�`5byte��<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == �ڋq�R�[�h�j�̏ꍇ�A�O�����������P��.�����h�c��<BR>
     * 9�`14byte��<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == ��������敪�j�̏ꍇ�A�O�����������P��.�ŋ敪<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == �������ԁj�̏ꍇ�A�O�����������P��.�󒍓���<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == ���ϋ敪�j�̏ꍇ�A�O�����������P��.���ϋ敪<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == �s��R�[�h�j�̏ꍇ�A�O�����������P��.�s��h�c<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == �����R�[�h�j�̏ꍇ�A�O�����������P��.�����h�c<BR>
     * �@@�|�i�\�[�g�L�[.�L�[���� == �����敪�j�̏ꍇ�A�O�����������P��.�������<BR>
     *   �|�i�\�[�g�L�[.�L�[���� == �������j�̏ꍇ�A�O�����������P��.������<BR>
     * <BR>
     * �@@�� ���X�h�c -> �،���Ё{���X<BR>
     * �@@�� �s��h�c -> �،���Ё{�s��<BR>
     * �@@�� �����h�c -> �،���Ё{���i�{�����R�[�h<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_strManagementCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 42A53C86037F
     */
    private WEB3FeqOrderUnit[] getOrderUnits(
        WEB3GentradeInstitution l_institution, 
        WEB3AdminFeqOrderAcceptCancelInputRequest l_request,
        String l_strManagementCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getOrderUnits(WEB3GentradeInstitution,"
            + " WEB3AdminFeqOrderAcceptInputRequest, String) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_institution == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "�،���Ђ����w��(null)�ł��B");
        }
        

        //���N�G�X�g�f�[�^�Ŏw�肳�ꂽ�����ɂĊO�����������P�ʃe�[�u������������B            
        //�O�����������P��.�،���ЃR�[�h = �،����.getInstitutionCode() And 
        String l_strWhere = " institution_code = ? and ";
        
        List l_lisObjs = new ArrayList();
        l_lisObjs.add(l_institution.getInstitutionCode());
        
        //�O�����������P��.���X�h�c = ���X�h�c(*1) And ���w�肪����ꍇ�̂�
        //(*1)�@@���X�h�c 
        //�A�J�E���g�}�l�[�W��.getBranch().getBranchId()�ɂĎ擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        log.debug("���N�G�X�g�f�[�^.���X�R�[�h = " + l_request.branchCode);
        log.debug("���N�G�X�g�f�[�^.�ڋq�R�[�h = " + l_request.accountCode);
        log.debug("���N�G�X�g�f�[�^.�s��R�[�h = " + l_request.marketCode);
        log.debug("���N�G�X�g�f�[�^.�����R�[�h = " + l_request.productCode);
        log.debug("���N�G�X�g�f�[�^.��t�敪 = " + l_request.acceptDiv);
        log.debug("���N�G�X�g�f�[�^.������ = " + l_request.orderBizDate);
        
        if (!WEB3StringTypeUtility.isEmpty(l_request.branchCode))
        {
            try
            {
                Branch l_branch = l_accountMgr.getBranch(l_institution, l_request.branchCode);//NotFoundException

                long l_lngBranchId = l_branch.getBranchId();

                l_strWhere += "branch_id = ? and ";

                l_lisObjs.add(new Long(l_lngBranchId));
            }
            catch (NotFoundException l_ex)
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�Y�����X�f�[�^�Ȃ�");
            }                
        }
        
        //(*2)�@@�����h�c 
        //�A�J�E���g�}�l�[�W��.get�ڋq().getAccountId()�ɂĎ擾����B
        if (!(WEB3StringTypeUtility.isEmpty(l_request.branchCode) || 
            WEB3StringTypeUtility.isEmpty(l_request.accountCode)))
        {
            WEB3GentradeMainAccount l_mainAccount = l_accountMgr.getMainAccount(
                l_institution.getInstitutionCode(),
                l_request.branchCode,
                l_request.accountCode);
                
            long l_lngAccountId = l_mainAccount.getAccountId();
            
            l_strWhere += "account_id = ? and ";
            
            l_lisObjs.add(new Long(l_lngAccountId));
        }
                
        //�O�����������P��.������  = ���N�G�X�g�f�[�^.������ And ���w�肪����ꍇ�̂� 
        if (l_request.orderBizDate != null)
        {
            l_strWhere += "biz_date = ? and ";
            
            l_lisObjs.add(WEB3DateUtility.formatDate(l_request.orderBizDate, "yyyyMMdd"));
        }
        
        //�O�����������P��.�^�p�R�[�h =  ����.�^�p�R�[�h And ���w�肪����ꍇ�̂� 
        if (!WEB3StringTypeUtility.isEmpty(l_strManagementCode))
        {
            l_strWhere += "order_emp_code = ? and ";
            
            l_lisObjs.add(l_strManagementCode);
        }
        
        //�O�����������P��.�s��h�c = �s��h�c(*3) And ���w�肪����ꍇ�̂� 
        //(*3)�@@�s��h�c 
        //���Z�I�u�W�F�N�g�}�l�[�W��.getMarket().getMarketId()�ɂĎ擾����B 
        if (!WEB3StringTypeUtility.isEmpty(l_request.marketCode))
        {
            WEB3GentradeFinObjectManager l_finObjMgr = (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();
            
            try
            {
                Market l_market = l_finObjMgr.getMarket(
                    l_institution.getInstitutionCode(),
                    l_request.marketCode);//NotFoundException                    
                                
                long l_lngMarketId = l_market.getMarketId();
            
                l_strWhere += "market_id = ? and ";
            
                l_lisObjs.add(new Long(l_lngMarketId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }                                  
        }

        //�O�����������P��.�����h�c = �����h�c(*4) And ���w�肪����ꍇ�̂� 
        //(*4)�@@�����h�c 
        //�O�������v���_�N�g�}�l�[�W��.get�O����������().getProductId()�ɂĎ擾����B 
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        
        if (!WEB3StringTypeUtility.isEmpty(l_request.productCode))
        {                
            WEB3FeqProductManager l_productMgr = (WEB3FeqProductManager)l_tradingModule.getProductManager();
            try
            {
                FeqProduct l_product = 
                    l_productMgr.getFeqProduct(l_institution, l_request.productCode);//NotFoundException
            
                long l_lngProductId = l_product.getProductId();
            
                l_strWhere += "product_id = ? and ";
            
                l_lisObjs.add(new Long(l_lngProductId));
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }                   
        }
        
        //�O�������P��.�o���I���������� = null And 
        l_strWhere += "exec_end_timestamp is null and ";
            
        //�O�����������P��.������� in (*5) 
        //WEB3-XBFEQ-A-CD-0062
        //(*5)�@@���N�G�X�g�f�[�^.��t�敪�ɂĎw�肳�ꂽ�R�[�h�l�ɑΉ�����ȉ��̒l
        if (l_request.acceptDiv != null && l_request.acceptDiv.length > 0)
        {
            String l_strWhereOrderStatus = "";
            
            int l_intAcceptDivCnt = l_request.acceptDiv.length;
            
            List l_lisAcceptDivs = new ArrayList();
            
            for (int i = 0; i < l_intAcceptDivCnt; i++)
            {
                String l_strAcceptDiv = l_request.acceptDiv[i];
                
                if (!l_lisAcceptDivs.contains(l_strAcceptDiv))
                {
                    l_lisAcceptDivs.add(l_strAcceptDiv);
                    
                    if (l_strWhere.trim().endsWith("?"))
                    {
                        l_strWhere += ",";
                    }
                    
                    //�i��t�敪 == 0�F������t���j�̏ꍇ�A 
                    //OrderStatusEnum.2:�������i�V�K�����j�C10:�����ρi�ύX�����A�ǒ��j
                    if (WEB3FeqAcceptDivDef.ORDER_ACCEPTING.equals(l_strAcceptDiv))
                    {
			            l_strWhereOrderStatus += " (order_status in (";
                        l_strWhereOrderStatus += "?, ? ";
                        l_strWhereOrderStatus += ") and confirmed_quantity is null)";
                        l_lisObjs.add(OrderStatusEnum.ORDERING);
                        l_lisObjs.add(OrderStatusEnum.MODIFIED);
                    }
                    //�i��t�敪 == 1�F����������j�̏ꍇ�A 
                    // OrderStatusEnum.8:�������i�ύX�����j�C13:�������i��������j
                    else if (WEB3FeqAcceptDivDef.CHANGING_CANCELING.equals(l_strAcceptDiv))
                    {
                        if (l_strWhereOrderStatus.equals(""))
                        {
				            l_strWhereOrderStatus += " (order_status in (";
	                        l_strWhereOrderStatus += "?, ?))";
                        }
                        else
                        {
				            l_strWhereOrderStatus += "or (order_status in (";
	                        l_strWhereOrderStatus += "?, ?))";
                        }
                        l_lisObjs.add(OrderStatusEnum.MODIFYING);
                        l_lisObjs.add(OrderStatusEnum.CANCELLING);
                    }            
                    //�i��t�敪 == 2�F������t�ρj�̏ꍇ�A 
                    // OrderStatusEnum.3:�����ρi�V�K�����j�C10:�����ρi�ύX�����j�C14:�����ρi��������j 
                    else if (WEB3FeqAcceptDivDef.ORDER_ACCEPTED.equals(l_strAcceptDiv))
                    {
                        if (l_strWhereOrderStatus.equals(""))
                        {
				            l_strWhereOrderStatus += " (order_status in (";
	                        l_strWhereOrderStatus += "?, ?, ?";
	                        l_strWhereOrderStatus += ") and confirmed_quantity is not null)";
                        }
                        else
                        {
				            l_strWhereOrderStatus += "or (order_status in (";
	                        l_strWhereOrderStatus += "?, ?, ?";
	                        l_strWhereOrderStatus += ") and confirmed_quantity is not null)";
                        }
                        l_lisObjs.add(OrderStatusEnum.ORDERED);
                        l_lisObjs.add(OrderStatusEnum.MODIFIED);
                        l_lisObjs.add(OrderStatusEnum.CANCELLED);
                    }
                    else
                    {
                        log.exiting(STR_METHOD_NAME);
                        throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                            this.getClass().getName() + STR_METHOD_NAME,
                            "��t�敪�̒l���s���̂ł��B");
                    }
                }                    
            }
            
            l_strWhere += "(" + l_strWhereOrderStatus + ")";
        }
        // ��t�敪�̎w�肪�Ȃ��i��t�敪 == null�j�ꍇ�A�ȉ��̂��ׂāB 
        //OrderStatusEnum.2:�������i�V�K�����j�C3:�����ρi�V�K�����j�C 
        //8:�������i�ύX�����j�C10:�����ρi�ύX�����j�C11:�������s�i�ύX�����j�C 
        //13:�������i��������j�C14:�����ρi��������j�C15:�������s�i��������j 
        else
        {
            l_strWhere += "order_status in (?, ?, ?, ?, ?, ?, ?, ?) ";
            l_lisObjs.add(OrderStatusEnum.ORDERING);
            l_lisObjs.add(OrderStatusEnum.ORDERED);
            l_lisObjs.add(OrderStatusEnum.MODIFYING);
            l_lisObjs.add(OrderStatusEnum.MODIFIED);
            l_lisObjs.add(OrderStatusEnum.NOT_MODIFIED);
            l_lisObjs.add(OrderStatusEnum.CANCELLING);
            l_lisObjs.add(OrderStatusEnum.CANCELLED);
            l_lisObjs.add(OrderStatusEnum.NOT_CANCELLED);
        }
        
        Object[] l_objs = new Object[l_lisObjs.size()];
        l_lisObjs.toArray(l_objs);

        //[�擾��] 
        //�i���N�G�X�g�f�[�^.�\�[�g�L�[�̎w��̒ʂ�j 
        //�|�i�\�[�g�L�[.�L�[���� == �^�p�R�[�h�j�̏ꍇ�A�O�����������P��.�^�p�R�[�h 
        //�|�i�\�[�g�L�[.�L�[���� == �����ԍ��j�̏ꍇ�A�O�����������P��.�����h�c 
        //�|�i�\�[�g�L�[.�L�[���� == ���X�R�[�h�j�̏ꍇ�A�O�����������P��.���X�h�c��3�`5byte�� 
        //�|�i�\�[�g�L�[.�L�[���� == �ڋq�R�[�h�j�̏ꍇ�A�O�����������P��.�����h�c��9�`14byte�� 
        //�|�i�\�[�g�L�[.�L�[���� == ��������敪�j�̏ꍇ�A�O�����������P��.�ŋ敪 
        //�|�i�\�[�g�L�[.�L�[���� == �������ԁj�̏ꍇ�A�O�����������P��.�󒍓��� 
        //�|�i�\�[�g�L�[.�L�[���� == ���ϋ敪�j�̏ꍇ�A�O�����������P��.���ϋ敪 
        //�|�i�\�[�g�L�[.�L�[���� == �s��R�[�h�j�̏ꍇ�A�O�����������P��.�s��h�c 
        //�|�i�\�[�g�L�[.�L�[���� == �����R�[�h�j�̏ꍇ�A�O�����������P��.�����h�c 
        //�|�i�\�[�g�L�[.�L�[���� == �����敪�j�̏ꍇ�A�O�����������P��.������� 
        //�|�i�\�[�g�L�[.�L�[���� == �������j�̏ꍇ�A�O�����������P��.������
        StringBuffer l_sbOrderBy = new StringBuffer();            
        
        if (l_request.sortKeys != null && l_request.sortKeys.length > 0)
        {
            int l_intCnt = l_request.sortKeys.length;
            
            List l_lisKeyItems = new ArrayList();
            for (int i = 0; i < l_intCnt; i++)
            {
                WEB3ForeignSortKey l_sortKey = l_request.sortKeys[i];
                if (l_sortKey != null)
                {
                    String l_strKeyItem = l_sortKey.keyItem;
                    
                    log.debug("���� @@@@ i = " + i + "@@@@");
                    log.debug("�\�[�g�L�[.�L�[���� = " + l_strKeyItem);
                    
                    if (!l_lisKeyItems.contains(l_strKeyItem))
                    {
                        l_lisKeyItems.add(l_strKeyItem);
                        
                        if (WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_strKeyItem))
                        {                            
                            l_sbOrderBy.append(" order_emp_code ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.ORDER_NO.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" order_id ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" SubStr(branch_id, 3, 3) ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" SubStr(account_id, 9, 6) ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" tax_type ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" received_date_time ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" settle_div ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" market_id ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" product_id ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" order_type ");
                        }
                        else if (WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(l_strKeyItem))
                        {
                            l_sbOrderBy.append(" biz_date ");
                        }
                        
                        if (WEB3AscDescDef.ASC.equals(l_sortKey.ascDesc))
                        {
                            l_sbOrderBy.append(" ASC ");
                        }
                        else if (WEB3AscDescDef.DESC.equals(l_sortKey.ascDesc))
                        {
                            l_sbOrderBy.append(" DESC ");
                        }
                        
                        l_sbOrderBy.append(" , ");
                    }                        
                }                                                            
            }
        }
        
        String l_strOrderBy = null;
        if (l_sbOrderBy.length() > 0)
        {
            l_strOrderBy = l_sbOrderBy.toString();
            l_strOrderBy = l_strOrderBy.substring(0, l_strOrderBy.length() - 2) + " ";
        }
        
        log.debug("SQL��: " + WEB3FeqLogUtility.getQueryString(l_strWhere, l_strOrderBy, null, l_objs));            
            
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException 
            List l_lisOrderUnitRows = l_queryProcessor.doFindAllQuery(
                FeqOrderUnitRow.TYPE, 
                l_strWhere,
                l_strOrderBy,
                null,
                l_objs);//DataNetworkException, DataQueryException                            
            
            //�擾�s�I�u�W�F�N�g�ɂĊO�����������P�ʂ𐶐����A�z��ŕԋp����B
            WEB3FeqOrderUnit[] l_orderUnits = null;
            
            if (l_lisOrderUnitRows != null && !l_lisOrderUnitRows.isEmpty())
            {
                int l_intRowsSize = l_lisOrderUnitRows.size();
                l_orderUnits = new WEB3FeqOrderUnit[l_intRowsSize];
                
                WEB3FeqOrderManager l_orderMgr = 
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
                
                for (int i = 0; i < l_intRowsSize; i++)
                {
                    FeqOrderUnitRow l_orderUnitRow = (FeqOrderUnitRow)l_lisOrderUnitRows.get(i);
                    l_orderUnits[i] = (WEB3FeqOrderUnit)l_orderMgr.toOrderUnit(l_orderUnitRow);
                    
                }
                
                log.exiting(STR_METHOD_NAME);
                return l_orderUnits;
            }
            //�Y���f�[�^���Ȃ��ꍇ�A��O���X���[����B
            else
            {
                log.debug(getClass().getName() + STR_METHOD_NAME);
                log.exiting(STR_METHOD_NAME);                        
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                    this.getClass().getName() + STR_METHOD_NAME, 
                    "�O�������������擾�ł��܂���B");
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
    }
}
@
