head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.52.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A���ݒ�ꗗ�T�[�r�XImpl(WEB3ToSuccSettingListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 ������(���u) �V�K�쐬
Revesion History : 2006/11/24 ���r(���u)�@@ ���f�� No.185 No.203
Revesion History : 2007/01/17 ���G��(���u)�@@���f�� No.223
Revesion History : 2007/12/17 ��іQ(���u) �d�l�ύX���f��No.247
Revesion History : 2008/03/20 ��іQ(���u) �d�l�ύX���f��No.258, 288
Revesion History : 2008/04/07 ��іQ(���u) �d�l�ύX���f��No.313
Revesion History : 2008/04/08 �g�E�N�|(���u) QA UT-0011
Revesion History : 2008/04/18 ��іQ(���u) �d�l�ύX���f��327, QA FT-0021, 329
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ReserveOrderExistFlagDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketDealtCond;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.triggerorder.WEB3ToSuccClientRequestService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccKeyItemDef;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.message.WEB3SuccProductInfo;
import webbroker3.triggerorder.message.WEB3SuccSettingListRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingListResponse;
import webbroker3.triggerorder.message.WEB3SuccSortKey;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingListService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.triggerorder.util.WEB3TriggerOrderUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;

/**
 * (�A���ݒ�ꗗ�T�[�r�XImpl)<BR>
 * �A���ݒ�ꗗ�T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToSuccSettingListServiceImpl extends WEB3ToSuccClientRequestService implements WEB3ToSuccSettingListService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingListServiceImpl.class);
    
    /**
     * @@roseuid 4348E3D0002E
     */
    public WEB3ToSuccSettingListServiceImpl() 
    {
     
    }
    
    /**
     * �A���ݒ�ꗗ�T�[�r�X�������s���B<BR>
     * <BR>
     * this.get�ꗗ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431CFFA10032
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        WEB3GenResponse l_response = null;

        if (l_request instanceof WEB3SuccSettingListRequest)
        {
            l_response =
                this.getListScreen( (WEB3SuccSettingListRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �A���ݒ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�A���ݒ�ꗗ�jget�ꗗ��ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �A���ݒ�ꗗ���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccSettingListResponse
     * @@roseuid 431CFF9E038D
     */
    protected WEB3SuccSettingListResponse getListScreen(WEB3SuccSettingListRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        //1.2�V�X�e�����ً}��~���A�o�b�`���ł��邩�`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3���b�Z�[�W (*)���N�G�X�g�f�[�^.���i�敪�ꗗ���\�[�g����B
        String[] l_strTypeLists = l_request.commodityTypeList;
        
        String l_strAscDesc = l_request.sortKeys[0].ascDesc;
        
        String[] l_strCommodityTypeLists = new String[l_strTypeLists.length];
        if (WEB3AscDescDef.ASC.equals(l_strAscDesc))
        {
            Arrays.sort(l_strTypeLists); 
            l_strCommodityTypeLists = l_strTypeLists;
        }
        else
        {
            Arrays.sort(l_strTypeLists); 
            for (int i = 0; i < l_strTypeLists.length; i++)
            {
                l_strCommodityTypeLists[i] = l_strTypeLists[l_strTypeLists.length -1 - i];
            }
        }
        
        List l_lisOptionProductInfo = new ArrayList();
        List l_lisFutureProductInfo = new ArrayList();
        //1.4�����f�[�^���i�[����ArrayList�𐶐�����B
        List l_lisOrderData = new ArrayList();
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        //1.5���b�Z�[�W (*)�\�[�g�������i�敪�̗v�f����Loop����
        WEB3GentradeSubAccount l_subAccount = null;
        for (int i = 0; i < l_strCommodityTypeLists.length; i++)
        {
            String l_strCommodityType = l_strCommodityTypeLists[i];
            String l_strfuturesOptionDiv = null;
            ProductTypeEnum l_productType = null;
            try
            {
                //1.5.1�⏕�������擾����B
                l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount(l_strCommodityTypeLists[i]);
                //�،���ЃR�[�h�F�@@get����()�̖߂�l.�،���ЃR�[�h 
                String l_strInstitution = l_subAccount.getInstitution().getInstitutionCode();
                
 
                //�����^�C�v�F 
                //�@@[���i�敪 == ("��������" or "�M�p���")�̏ꍇ] 
                //�@@�@@ProductTypeEnum.���� 
                //�@@[��L�ȊO�̏ꍇ] 
                //�@@ProductTypeEnum.�敨�I�v�V����         

                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
                {
                    l_productType = ProductTypeEnum.EQUITY;
                }
                else
                {
                    l_productType = ProductTypeEnum.IFO;
                }
                
                //�敨�^�I�v�V�����敪�F 
                // �@@[���i�敪 == "�敨"�̏ꍇ] 
                // �@@�@@"�敨" 
                //�@@[���i�敪 == "�I�v�V����"�̏ꍇ] 
                // �@@�@@"�I�v�V����" 
                //�@@[��L�ȊO�̏ꍇ] 
                // �@@�@@"DEFAULT"

                if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType))
                {
                    l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
                }
                else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
                {
                    l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.OPTION;
                }
                else
                {
                    l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
                }
                //1.5.2�A���������戵�\�ł��邩�`�F�b�N����B
                l_orderManager.validateSuccOrderHandling(l_strInstitution, l_productType, l_strfuturesOptionDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(l_ex.getErrorMessage());
                continue;
            }
            
            //1.5.3�\�[�g�������쐬����B
            String l_strSortCond = this.createSortCond(l_strCommodityType, l_request.sortKeys);
            
            List l_lisOrderUnit = new ArrayList();

            //1.5.4(*)���i�敪 == ("��������" or "�M�p���")�̏ꍇ
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
            {
                //1.5.4.1����������������쐬����B
                //(�����R�[�h�v���_�E�����쐬����ׁA���������͍ŏ����Ƃ���)
                String l_strQueryString = this.createQueryString(l_strCommodityType, l_request.productCode, l_request.orderBizDate, l_subAccount);
                //1.5.4.2���������f�[�^�R���e�i���쐬����B
                String[] l_strQueryContainer = 
                    this.createQueryContainer(
                        (WEB3GentradeInstitution)l_subAccount.getInstitution(), 
                        l_subAccount, 
                        l_strCommodityType, 
                        l_request.productCode, 
                        l_request.orderBizDate);
                //1.5.4.3Eqtype�̒����P�ʈꗗ���擾����B
                l_lisOrderUnit = 
                    l_dataGettingService.getOrderUnitList(l_subAccount, ProductTypeEnum.EQUITY, l_strQueryString, l_strQueryContainer, l_strSortCond);
                if (l_lisOrderUnit == null || l_lisOrderUnit.isEmpty())
                {
                    continue;
                }
            }
            //1.5.5(*)���i�敪 == ("�敨" or "�I�v�V����")�̏ꍇ
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType) 
                || WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
            {
                //1.5.5.1����������������쐬����B
                //(�����R�[�h�v���_�E�����쐬����ׁA���������͍ŏ����Ƃ���)
                String l_strQueryString = this.createQueryString(l_strCommodityType, null,null, l_subAccount);
                //1.5.5.2���������f�[�^�R���e�i���쐬����B(�����R�[�h�v���_�E�����쐬����ׁA���������͍ŏ����Ƃ���)

                String[] l_strQueryContainer = 
                    this.createQueryContainer(
                        (WEB3GentradeInstitution)l_subAccount.getInstitution(), 
                        l_subAccount, 
                        l_strCommodityType, 
                        null, 
                        null);
                
                //1.5.5.3Ifo�̒����P�ʈꗗ���擾����B
                List l_lisOrderUnitList = 
                    l_dataGettingService.getOrderUnitList(l_subAccount, ProductTypeEnum.IFO, l_strQueryString, l_strQueryContainer, l_strSortCond);
                
                if (l_lisOrderUnitList == null || l_lisOrderUnitList.isEmpty())
                {
                    continue;
                }
                
                //1.5.5.4�敨 or �I�v�V�����̖��������쐬����B���敨�ƃI�v�V�����Ƃŕʂ̕ϐ����`���A���ʂ�ʁX�ɕێ����邱�ƁB
                WEB3SuccProductInfo[] l_productInfo = this.createIfoProductInfo(l_lisOrderUnitList);
                
                if (l_productInfo != null)
                {
                    if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
                    {
                        for (int j = 0; j < l_productInfo.length; j++)
                        {
                            l_lisOptionProductInfo.add(l_productInfo[j]);
                        }
                    }
                    else
                    {
                        for (int j = 0; j < l_productInfo.length; j++)
                        {
                            l_lisFutureProductInfo.add(l_productInfo[j]);
                        }
                    }
                }
                String l_datBizdate = null;
                
                if (l_request.orderBizDate != null)
                {
                    l_datBizdate = WEB3DateUtility.formatDate(l_request.orderBizDate, "yyyyMMdd");
                }
               
                //1.5.5.5���͂��ꂽ���������ɊY������Ifo�����P�ʂ̈ꗗ���擾����B
                l_lisOrderUnit = 
                    this.getDisplayObjIfoOrderUnit(l_lisOrderUnitList, (WEB3GentradeInstitution)l_subAccount.getInstitution(), l_request.productCode, l_datBizdate);
                
                if (l_lisOrderUnit == null || l_lisOrderUnit.isEmpty())
                {
                    continue;
                }
            }

            l_lisOrderData.addAll(l_lisOrderUnit);
        }
         //�Ɩ����t���A���X�|���X.�������ꗗ�ɃZ�b�g����
        Date l_datOrderBizdate = GtlUtils.getTradingSystem().getBizDate();

        Collection l_lstorderBizDate = new ArrayList();
        l_lstorderBizDate.add(l_datOrderBizdate);
        
        boolean l_blnIsEqtype = false;
        for (int i = 0; i < l_strCommodityTypeLists.length; i++)
        {	
            //���N�G�X�g�f�[�^.���i�敪�ꗗ��"��������" or "�M�p���"���܂܂��ꍇ
            if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityTypeLists[i]) 
                || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityTypeLists[i]))
            {
            	l_blnIsEqtype = true;
            }
            //���i�敪 == ("�敨" or "�I�v�V����")�̏ꍇ
            else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityTypeLists[i])
                || WEB3CommodityDivDef.OPTION.equals(l_strCommodityTypeLists[i]))
            {
                String l_strFuturesOptionDiv = null;
                //�敨�^�I�v�V�����敪: ���i�敪�̗v�f
                if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityTypeLists[i]))
                {
                    l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
                }
                else
                {
                    l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.OPTION;
                }

                // get�i���X�w���ʁj�戵����
                //�،���ЃR�[�h�F this.get����()�̖߂�l.getBranch().getInstitution().�،���ЃR�[�h
                //���X�R�[�h�F�@@this.get����()�̖߂�l.getBranch().���X�R�[�h
                //�敨�^�I�v�V�����敪�F�@@���i�敪�̗v�f
                WEB3GentradeBranchIndexDealtCond[] l_branchIndexes =
                    WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(
                        this.getMainAccount().getBranch().getInstitution().getInstitutionCode(),
                        this.getMainAccount().getBranch().getBranchCode(),
                        l_strFuturesOptionDiv);

                // reset�s��R�[�h(�s��R�[�h : String)
                //�s��R�[�h�F�@@"DEFAULT"
                WEB3GentradeTradingTimeManagement.resetMarketCode(WEB3MarketCodeDef.DEFAULT);

                // reset��t���ԋ敪(��t���ԋ敪 : String)
                //��t���ԋ敪�F�@@"�����w���敨OP"
                WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);

                //�i���X�w���ʁj�戵�����v�f���Ƃ�Loop����
                for (int j = 0; j < l_branchIndexes.length; j++)
                {
                    // reset�����R�[�h(�����R�[�h : String)
                    //�����R�[�h�F�@@�i���X�w���ʁj�戵����.�����Y�����R�[�h
                    WEB3GentradeTradingTimeManagement.resetProductCode(
                        l_branchIndexes[j].getUnderlyingProductCode());

                    //get������( )
                    Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

                    //�擾�����戵�����v�f���̔������ɁA���X�|���X.�������ꗗ�Ɋ܂܂�Ă��Ȃ����t������ꍇ��
                    //���X�|���X.�������ꗗ�ɒǉ�����B
                    if (!l_lstorderBizDate.contains(l_datBizDate))
                    {
                        l_lstorderBizDate.add(l_datBizDate);
                    }
                }
            }
        }
        if(l_blnIsEqtype)
        {
            //get�戵�\�s��(���X : ���X, �����^�C�v : ProductTypeEnum) 
            //[����]
            // ���X�F�@@this.get����()�̖߂�l.getBranch()
            // �����^�C�v�F�@@"����"
            String[] l_strMarketList = 
            	WEB3GentradeBranchMarketDealtCond.getHandlingPossibleMarket(
                	(WEB3GentradeBranch)this.getMainAccount().getBranch(),
					ProductTypeEnum.EQUITY);
            //reset��t���ԋ敪
            //[����] 
			// ��t���ԋ敪�F�@@"�����E�M�p" 
            WEB3GentradeTradingTimeManagement.resetTradingTimeType(WEB3TradingTimeTypeDef.EQUITY);
            
            //(*)get�戵�\�s��()�̖߂�l�̗v�f����Loop����
            for (int i = 0; i < l_strMarketList.length; i++ )
            {
            	//reset�s��R�[�h(�s��R�[�h : String)
            	//[����] 
            	//�s��R�[�h�F�@@�s��R�[�h�ꗗ.�s��R�[�h
            	WEB3GentradeTradingTimeManagement.resetMarketCode(l_strMarketList[i]);
            	
            	//get������( )
            	Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            	
            	//�擾�����s�ꖈ�̔������ɁA���X�|���X.�������ꗗ�Ɋ܂܂�Ă��Ȃ����t������ꍇ�́A
            	//���X�|���X.�������ꗗ�ɒǉ�����B
                if (!l_lstorderBizDate.contains(l_datBizDate))
                {
                    l_lstorderBizDate.add(l_datBizDate);
                }
            }
        }
        int l_intListSize = l_lstorderBizDate.size();
        Date[] l_orderBizDates= new Date[l_intListSize];
        l_lstorderBizDate.toArray(l_orderBizDates);
        
        //1.6(*)��������ArrayList.size() == 0�̏ꍇ
        if (l_lisOrderData.size() == 0)
        {
            //1.6.1���X�|���X�f�[�^�𐶐�����B
            WEB3SuccSettingListResponse l_response = (WEB3SuccSettingListResponse)l_request.createResponse();
            
            l_response.orderBizDateList = l_orderBizDates;
            
            return l_response;
        }
        
        //1.7�\�񒍕��̈ꗗ���擾����B
        //����ID�F�@@get�⏕����()�̖߂�l.����ID�B
        //���i�敪�ꗗ�F�@@���N�G�X�g�f�[�^.���i�敪�ꗗ
        Hashtable l_hasOrderUnit =
            l_dataGettingService.getRsvOrderUnitList(
                l_subAccount.getAccountId(),
                l_request.commodityTypeList);
        //1.8WEB3PageIndexInfo�C���X�^���X�𐶐�����B
        WEB3PageIndexInfo l_info = new WEB3PageIndexInfo(l_lisOrderData, Integer.parseInt(l_request.pageIndex), Integer.parseInt(l_request.pageSize));
        //1.9��ʕ\���Ώۂ�OrderUnit�̔z��𐶐�����B
        Object[] l_objReturned = l_info.getArrayReturned(OrderUnit.class);
        //1.10�A���������ׂ��i�[����ArrayList�𐶐�����B
        List l_lisOrderUnit = new ArrayList();
        //1.11���b�Z�[�W (*)getArrayReturned()�̖߂�l�̗v�f����Loop����
        
        if (l_objReturned != null)
        {
            for (int i = 0; i < l_objReturned.length; i++)
            {
                //1.11.1�A���������׃C���X�^���X�𐶐�����B
                WEB3SuccOrderUnit l_orderUnit = new WEB3SuccOrderUnit();
                
                OrderUnit l_unit = (OrderUnit)l_objReturned[i];
                //1.11.2�A���������ׂɃv���p�e�B���Z�b�g����B 
                l_dataGettingService.createSuccOrderUnit(l_orderUnit, l_unit, true);
                //1.11.3���b�Z�[�W (*)�O�񒍕��ɐݒ肳��Ă���\�񒍕��̈ꗗ���擾����B
                ProductTypeEnum l_productType = l_unit.getProduct().getProductType(); 
                
                String l_strKey = WEB3TriggerOrderUtility.createKey(l_unit.getOrderId(), l_productType);
                List l_lisrsvOrderUnit = (ArrayList)l_hasOrderUnit.get(l_strKey); 
				if (l_lisrsvOrderUnit != null)
				{
					//1.11.4���b�Z�[�W (*)�\�񒍕�(=get()�̖߂�l)���擾�ł����ꍇ
					//�\�񒍕����ׂ��쐬����B
                    //�O�񒍕����ׁF�@@���������A����������
                    //�\�񒍕�Row�ꗗ�F�@@�擾�����\�񒍕��̈ꗗ
                    //is�\�t���O�ݒ�F�@@true�i�ݒ肷��j
                    if (l_lisrsvOrderUnit.get(0) instanceof RsvEqOrderUnitRow)
                    {
                        RsvEqOrderUnitRow[] l_preOrderUnit = new RsvEqOrderUnitRow[l_lisrsvOrderUnit.size()];
                        l_lisrsvOrderUnit.toArray(l_preOrderUnit);
                        l_dataGettingService.createRsvOrderUnit(l_orderUnit, l_preOrderUnit, true);
                    }
                    else if (l_lisrsvOrderUnit.get(0) instanceof RsvIfoOrderUnitRow)
                    {
                        RsvIfoOrderUnitRow[] l_preOrderUnit = new RsvIfoOrderUnitRow[l_lisrsvOrderUnit.size()];
                        l_lisrsvOrderUnit.toArray(l_preOrderUnit);
                        l_dataGettingService.createRsvOrderUnit(l_orderUnit, l_preOrderUnit, true);
                    }
				}
					//1.11.5ArrayList�ɐ��������A���������ׂ�ǉ�����B 
				l_lisOrderUnit.add(l_orderUnit);
            }                      
        }
        
        WEB3SuccOrderUnit[] l_orderUnits = new WEB3SuccOrderUnit[l_lisOrderUnit.size()];
        //1.12�A���������ׂ̔z��𐶐�����B
        l_lisOrderUnit.toArray(l_orderUnits);
        //1.13���X�|���X�f�[�^�𐶐�����B
        WEB3SuccSettingListResponse l_response = (WEB3SuccSettingListResponse)l_request.createResponse();
        //1.14���b�Z�[�W (*)�v���p�e�B�Z�b�g
        //�������ꗗ�@@    ���@@�����c�Ɠ�(*1)�A �����c�Ɠ���v�f�Ƃ���z��
        l_response.orderBizDateList = l_orderBizDates;

        l_response.orderUnitsList = l_orderUnits;
        //�\���y�[�W�ԍ�   ���@@WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = "" + l_info.getPageIndex();
        //���y�[�W�� ���@@WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = "" + l_info.getTotalPages();
        //�����R�[�h��    ���@@WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = "" + l_info.getTotalRecords();
        //�敨�������    ���@@�敨��create�敨OP�������()��   
        if (!l_lisFutureProductInfo.isEmpty())
        {
            WEB3SuccProductInfo[] l_succProductFuturesInfo = new WEB3SuccProductInfo[l_lisFutureProductInfo.size()];
            l_lisFutureProductInfo.toArray(l_succProductFuturesInfo);        
            l_response.futuresProductInfo = l_succProductFuturesInfo;
        }
        else
        {
            l_response.futuresProductInfo = null;
        }        
        //�I�v�V����������� ���@@�I�v�V������create�敨OP�������()�̖߂�l
        if (!l_lisOptionProductInfo.isEmpty())
        {
            WEB3SuccProductInfo[] l_succProductOptionInfo = new WEB3SuccProductInfo[l_lisOptionProductInfo.size()];
            l_lisOptionProductInfo.toArray(l_succProductOptionInfo);        
            l_response.optionsProductInfo = l_succProductOptionInfo;
        }
        else
        {
            l_response.optionsProductInfo = null;
        }        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * �e�����P�ʃe�[�u������������ׂ́A<BR>
     * �����������쐬����B<BR>
     * <BR>
     * �� �敨�I�v�V�����̓p�����[�^.�������y�уp�����[�^.�����R�[�h��<BR>
     * �� �i�����ݒ�Łj���null���ݒ肳���B<BR>
     * <BR>
     * �P�j�@@�������������쐬����B<BR>
     * �@@[�p�����[�^.������ != null�̏ꍇ]<BR>
     * �@@�@@�������������� = "biz_date = ? "<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�������������� = "biz_date >= ? "<BR>
     * <BR>
     * �@@�� �p�����[�^.���i�敪���A"�敨"or"�I�v�V����"�̏ꍇ�A<BR>
     * �@@�� ��������������̐擪��"and "��ݒ肷��B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@����������ǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_id = ? "<BR>
     * <BR>
     * �R�j�@@�e���i���Ƃ̏�����ǉ�����B<BR>
     * �@@�p�����[�^.���i�敪���A<BR>
     * �@@["��������"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_type in (?, ?) "<BR>
     * <BR>
     * �@@["�M�p����̏ꍇ]<BR>
     * �@@�@@�������������� += "and order_categ in (?, ?, ?) "<BR>
     * <BR>
     * �@@["�敨" or "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�������������� += "and future_option_div = ? "<BR>
     * <BR>
     * �S�j�@@�\���ΏۂƂȂ钍���̏�����ǉ�����B<BR>
     * �@@[����]<BR>
     * �@@�@@(�����L����� = "�I�[�v��" or<BR>
     * �@@�@@ (�����L����� = "�N���[�Y" and <BR>
     * �@@�@@�@@�\�񒍕��ݒ�t���O = "�ݒ�̉\������"))<BR>
     * �@@�@@and �A�������̔��s�ɂ��쐬���ꂽ�����łȂ��B<BR>
     * <BR>
     * �@@�������������� += "and (order_open_status = ? or "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(order_open_status = ? and "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "reserve_order_exist_flag = ?)) "<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "and order_id not in " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[�p�����[�^.���i�敪��"��������" or "�M�p���"�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rsv_eq_order_unit " <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@[��L�ȊO�̏ꍇ] <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "(select order_id from rsv_ifo_order_unit " <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@+ "where account_id =?)" <BR>
     * <BR>
     * �T�j�@@�s�������ǉ�����B <BR>
     * �@@�i���������ΏۊO�Ƃ���s����Z�b�g�j <BR>
     * <BR>
     * �@@�������������� += "and market_id not in (?, ?, �E�E�E�E�E�E) "�i*1�j <BR>
     * <BR>
     * �i*1�j�h?�h�̐��́A�i���X�s��ʁEPTS�j�戵����.get�戵�\�s��()�Ŏ擾���� <BR>
     * �@@�z��̗v�f�����ݒ肷��B  <BR>
     * <BR>
     * �@@�@@[get�戵�\�s��()�̈����ݒ�]  <BR>
     * �@@�@@���X�F�@@�p�����[�^.�⏕����.get����X()�̖߂�l  <BR>
     * �@@�@@�����^�C�v�F�@@"����" <BR>
     * <BR>
     * �U�j�@@�쐬�������������������ԋp����B<BR>
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕����<BR>
     * @@return String
     * @@roseuid 43263A0B0366
     */
    protected String createQueryString(
            String l_strCommodityDiv,
            String l_strProductCode,
            Date l_datBizDate,
            WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "createQueryString(String l_strCommodityDiv, String l_strProductCode, Date l_datBizDate, WEB3GentradeSubAccount l_subAccount) ";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        StringBuffer l_sbQueryString = new StringBuffer();

        //�@@�� �p�����[�^.���i�敪���A"�敨"or"�I�v�V����"�̏ꍇ�A
        //  �� ��������������̐擪��"and "��ݒ肷��B
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv)
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            // �P�j�@@�������������쐬����B
            // �@@[�p�����[�^.������ != null�̏ꍇ]
            // �@@�@@�������������� = "biz_date = ? "
            if (l_datBizDate != null)
            {
                l_sbQueryString.append(" and biz_date = ? ");
            }
            //[��L�ȊO�̏ꍇ]
            //�������������� = "biz_date >= ? "
            else
            {
                l_sbQueryString.append(" and biz_date >= ? ");
            }
        }
        else
        {
            // �P�j�@@�������������쐬����B
            // �@@[�p�����[�^.������ != null�̏ꍇ]
            // �@@�@@�������������� = "biz_date = ? "
            if (l_datBizDate != null)
            {
                l_sbQueryString.append("biz_date = ? ");
            }
            //[��L�ȊO�̏ꍇ]
            //�������������� = "biz_date >= ? "
            else
            {
                l_sbQueryString.append("biz_date >= ? ");
            }
        }

        /*
         * �Q�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A
         * �@@����������ǉ�����B
         * �@@�������������� += "and product_id = ? "
         */
        if (l_strProductCode != null)
        {
            l_sbQueryString.append("and product_id = ? ");
        }
        /*
         * �R�j�@@�e���i���Ƃ̏�����ǉ�����B
         * �@@�p�����[�^.���i�敪���A
         * �@@["��������"�̏ꍇ]
         * �@@�@@�������������� += "and order_type in (?, ?) "
         */
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("and order_type in (?, ?) ");
        }
        
        // �@@["�M�p����̏ꍇ]
        // �@@�@@�������������� += "and order_categ in (?, ?, ?) "
        if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("and order_categ in (?, ?, ?) ");
        }
        
        // �@@["�敨" or "�I�v�V����"�̏ꍇ]
        //�������������� += "and future_option_div = ? "
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv) 
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("and future_option_div = ? ");
        }
        
        //�S�j�@@�\���ΏۂƂȂ钍���̏�����ǉ�����B
        l_sbQueryString.append("and (order_open_status = ? or " 
                + "(order_open_status = ? and " 
                + "reserve_order_exist_flag = ?))");

        //+ "and order_id not in "
        l_sbQueryString.append("and order_id not in");

        //[�p�����[�^.���i�敪��"��������" or "�M�p���"�̏ꍇ] + "(select order_id from rsv_eq_order_unit "
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("(select order_id from rsv_eq_order_unit ");
        }
        //[��L�ȊO�̏ꍇ]+ "(select order_id from rsv_ifo_order_unit "
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv) 
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_sbQueryString.append("(select order_id from rsv_ifo_order_unit ");
        }
        //+ "where account_id =?)"
        l_sbQueryString.append("where account_id =?)");

        //�T�j�@@�s�������ǉ�����B
        String[] l_strMarketCodes = 
            WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(l_subAccount.getWeb3GenBranch(), ProductTypeEnum.EQUITY);

        int l_intMarketCodesCnt = l_strMarketCodes.length;

        if (l_intMarketCodesCnt > 0)
        {
            StringBuffer l_sbQueryMarketCodes = new StringBuffer();
 
            for (int i = 0; i < l_intMarketCodesCnt; i++)
            {
                if (l_sbQueryMarketCodes.length() != 0)
                {
                    l_sbQueryMarketCodes.append(", ");
                }
                l_sbQueryMarketCodes.append("?");

            }
            l_sbQueryString.append(" and market_id not in (" + l_sbQueryMarketCodes.toString() + ")");
        }

        //�U�j�@@�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_sbQueryString.toString();
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * �e�����P�ʃe�[�u������������ׂ�<BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@������������ǉ�����B<BR>
     * �@@[�p�����[�^.������ != null�̏ꍇ]<BR>
     * �@@�@@�p�����[�^.��������ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@[��L�ȊO�̏ꍇ]<BR>
     * �@@�@@�Ɩ����t��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�����R�[�h�ɊY���������ID��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�������́A�A�������f�[�^�擾�T�[�r�XImpl.get����()�ɂĎ擾����B<BR>
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЁF�@@�p�����[�^.�،����<BR>
     * �@@�@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * �@@�@@�@@���i�敪�F�@@�p�����[�^.���i�敪<BR>
     * <BR>
     * �S�j�@@�e���i���Ƃ̏�����ArrayList�ɒǉ�����B<BR>
     * �@@�p�����[�^.���i�敪���A<BR>
     * �@@["��������"�̏ꍇ]<BR>
     * �@@�@@�EOrderTypeEnum.����������<BR>
     * �@@�@@�EOrderTypeEnum.����������<BR>
     * <BR>
     * �@@["�M�p����̏ꍇ]<BR>
     * �@@�@@�EOrderCategEnum.�V�K������<BR>
     * �@@�@@�EOrderCategEnum.�ԍϒ���<BR>
     * �@@�@@�EOrderCategEnum.�������n����<BR>
     * <BR>
     * �@@["�敨"�̏ꍇ]<BR>
     * �@@�@@�E"�敨"�i�敨�I�v�V�����敪�j<BR>
     * <BR>
     * �@@["�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�E"�I�v�V����"�i�敨�I�v�V�����敪�j<BR>
     * <BR>
     * �T�j�@@�\���ΏۂƂȂ钍���̏�����ArrayList�ɒǉ�����B<BR>
     * �@@�@@"�I�[�v��"(�����L�����)<BR>
     * �@@�A"�N���[�Y"(�����L�����)<BR>
     * �@@�B"�ݒ�̉\������"(�\�񒍕��ݒ�t���O)<BR>
     * �@@�C�p�����[�^.�⏕����.����ID<BR>
     * <BR>
     * �U�j�@@�s�������ǉ�����B <BR>
     * �@@�@@�i���������ΏۊO�Ƃ���s����擾����B�j <BR>
     * <BR>
     * �@@�@@�U�|�P�j�@@�i���X�s��ʁEPTS�j�戵����..get�戵�\�s��()���R�[������B <BR>
     * <BR>
     * �@@�@@�@@[get�戵�\�s��()�̈����ݒ�] <BR>
     * �@@�@@�@@���X�F�@@�p�����[�^.�⏕����.get����X()�̖߂�l <BR>
     * �@@�@@�@@�����^�C�v�F�@@"����" <BR>
     * <BR>
     * �@@�@@�U�|�Q�j�@@�U�|�P�j�Ŏ擾�ł����s��R�[�h�̔z��v�f�����A�ȉ����J��Ԃ��B <BR>
     * <BR>
     * �@@�g�����Z�I�u�W�F�N�g�}�l�[�W��.get�s��().getMarketId()�̖߂�l��ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@[get�s��()�̈����ݒ�] <BR>
     * �@@�@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،����.getInstitutionCode()�̖߂�l <BR>
     * �@@�@@�@@�s��R�[�h�F�@@�U�|�P�j�̖߂�l�i�s��R�[�h�z��j�̗v�f <BR>
     * <BR>
     * �V�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * �⏕�����I�u�W�F�N�g<BR>
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_datBizDate - (������)<BR>
     * ������<BR>
     * @@return String[]
     * @@roseuid 43263A0B0375
     */
    protected String[] createQueryContainer(
        WEB3GentradeInstitution l_institution, 
        WEB3GentradeSubAccount l_subAccount, 
        String l_strCommodityDiv, 
        String l_strProductCode, 
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createQueryString(String l_strCommodityDiv, String l_strProductCode, Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@ArrayList�𐶐�����B
        List l_lisQueryContainer = new ArrayList();
        
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        
        /*
         * �Q�j�@@������������ǉ�����B
         * �@@[�p�����[�^.������ != null�̏ꍇ]
         * �@@�@@�p�����[�^.��������ArrayList�ɒǉ�����B
         */
        if (l_datBizDate != null)
        {
            l_lisQueryContainer.add(WEB3DateUtility.formatDate((l_datBizDate), "yyyyMMdd"));
        }
        //[��L�ȊO�̏ꍇ]�Ɩ����t(*1)��ArrayList�ɒǉ�����B
        else
        {
            l_lisQueryContainer.add(WEB3DateUtility.formatDate((GtlUtils.getTradingSystem().getBizDate()), "yyyyMMdd"));
        }
        //�R�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A�����R�[�h�ɊY���������ID��ArrayList�ɒǉ�����B
        if (l_strProductCode != null)
        {
            Product l_product = l_dataGettingService.getProduct(l_institution, l_strProductCode, l_strCommodityDiv);
            l_lisQueryContainer.add(new Long(l_product.getProductId()).toString());
        }
        
        /*
         * �S�j�@@�e���i���Ƃ̏�����ArrayList�ɒǉ�����B
         * �@@�p�����[�^.���i�敪���A<BR>
         * �@@["��������"�̏ꍇ]<BR>
         * �@@�@@�EOrderTypeEnum.����������<BR>
         * �@@�@@�EOrderTypeEnum.����������<BR>
         */
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(Integer.toString(OrderTypeEnum.EQUITY_BUY.intValue()));
            l_lisQueryContainer.add(Integer.toString(OrderTypeEnum.EQUITY_SELL.intValue()));
        }
        
        /*
         * �@@["�M�p����̏ꍇ]<BR>
         * �@@�@@�EOrderCategEnum.�V�K������<BR>
         * �@@�@@�EOrderCategEnum.�ԍϒ���<BR>
         * �@@�@@�EOrderCategEnum.�������n����<BR>
         */
        if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(Integer.toString(OrderCategEnum.OPEN_MARGIN.intValue()));
            l_lisQueryContainer.add(Integer.toString(OrderCategEnum.CLOSE_MARGIN.intValue()));
            l_lisQueryContainer.add(Integer.toString(OrderCategEnum.SWAP_MARGIN.intValue()));
        }
        
        // �@@["�敨"�̏ꍇ]�E"�敨"�i�敨�I�v�V�����敪�j
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(WEB3FuturesOptionDivDef.FUTURES);
        }
        //["�I�v�V����"�̏ꍇ]<BR>�E"�I�v�V����"�i�敨�I�v�V�����敪�j
        if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_lisQueryContainer.add(WEB3FuturesOptionDivDef.OPTION);
        }
        
        /*
         * �T�j�@@�\���ΏۂƂȂ钍���̏�����ArrayList�ɒǉ�����B
         * �@@�@@"�I�[�v��"(�����L�����)
         * �@@�A"�N���[�Y"(�����L�����)
         * �@@�B"�ݒ�̉\������"(�\�񒍕��ݒ�t���O)
         * �@@�C�p�����[�^.�⏕����.����ID
         */
        l_lisQueryContainer.add(Integer.toString(OrderOpenStatusEnum.OPEN.intValue()));
        l_lisQueryContainer.add(Integer.toString(OrderOpenStatusEnum.CLOSED.intValue()));        
        l_lisQueryContainer.add(WEB3ReserveOrderExistFlagDef.SET_POSSIBLE);        
        l_lisQueryContainer.add(new Long(l_subAccount.getAccountId()).toString());
        
        // �U�j�@@�s�������ǉ�����B �i���������ΏۊO�Ƃ���s����擾����B�j
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //�،���ЃR�[�h
        String l_strInstitutionCode = l_institution.getInstitutionCode();
        //�s��R�[�h
        String[] l_strMarketCodes =
            WEB3GentradeBranchMarketPTSDealtCond.getHandlingPossibleMarket(
                l_subAccount.getWeb3GenBranch(),
                ProductTypeEnum.EQUITY);

        for(int i = 0; i < l_strMarketCodes.length; i++)
        {
            try
            {
                long l_lngMarketId =
                    l_finObjManager.getMarket(l_strInstitutionCode, l_strMarketCodes[i]).getMarketId();
                l_lisQueryContainer.add("" + l_lngMarketId);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }

        //�V�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strReturn = new String[l_lisQueryContainer.size()];
        l_lisQueryContainer.toArray(l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �e�����P�ʃe�[�u���̌������ʂ̃\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�\�[�g�L�[.�v�f�� == 1�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����A�Ή�����e�[�u���̗񕨗����A<BR>
     * �@@�@@�����^�~���w���t�����Z�b�g����B<BR>
     * �@@�@@���J�nindex = 1�Ƃ���B<BR>
     * �@@�@@�@@(0�Ԗڂɂ�"���i�敪"���i�[����Ă����)<BR>
     * <BR>
     * �@@�@@�E�L�[���ڂƃe�[�u���̗񖼏̂Ƃ̑Ή��͈ȉ��̒ʂ�<BR>
     * �@@�@@�@@���L�[���ڕ�����i�V���{�����j�́A���b�Z�[�W��`�����Q��<BR>
     * �@@�@@�@@���e�[�u���̗񕨗����́A�e�[�u�����C�A�E�g���Q��<BR>
     * <BR>
     * �@@�@@�@@�@@�ϊ��O�@@�@@�@@�@@�@@�@@�@@�@@�@@�ϊ���<BR>
     * -------------�@@�@@�@@�@@-----------------------------<BR>
     * �@@�@@�@@�@@�E�����R�[�h �@@�@@�@@�@@�@@ �F�����P�ʃe�[�u���D����ID<BR>
     * �@@�@@�@@�@@�E�s��R�[�h �@@�@@�@@�@@�@@ �F�����P�ʃe�[�u���D�s��ID<BR>
     * �@@�@@�@@�@@�E�����敪(*2)�@@ �@@�@@ �F�����P�ʃe�[�u���D�ŋ敪<BR>
     * �@@�@@�@@�@@�E����敪�@@�@@�@@�@@�@@�@@ �F�����P�ʃe�[�u���D�������<BR>
     * �@@�@@�@@�@@�E�ٍϋ敪(*2)�@@�@@�@@  �F�����P�ʃe�[�u���D�ٍϋ敪<BR>
     * �@@�@@�@@�@@�E�l�i����(*2)�@@�@@ �@@ �F�����P�ʃe�[�u���D�l�i����<BR>
     * �@@�@@�@@�@@�E���s����(*2) �@@�@@�@@ �F�����P�ʃe�[�u���D���s����<BR>
     * �@@�@@�@@�@@�E���������敪(*2)  �F(*1)<BR>
     * �@@�@@�@@�@@�E�������� �@@�@@�@@�@@�@@  �F(*3)<BR>
     * �@@�@@�@@�@@�E������ �@@�@@�@@�@@�@@�@@�@@ �F�����P�ʃe�[�u���D������<BR>
     * �@@�@@�@@�@@�E�����L������ �@@�@@  �F�����P�ʃe�[�u���D�����������t<BR>
     * <BR>
     * �@@(*1) nvl�i�����P�ʃe�[�u��.����������, �����P�ʃe�[�u��.���������j��ݒ肷��B<BR>
     * �@@�@@�@@�������P�ʃe�[�u��.�����������ɒl���ݒ肳��Ă���ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@���������������ƂɃ\�[�g����<BR>
     * �@@�@@�@@�@@�����������ɒl���ݒ肳��Ă��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�����P�ʃe�[�u��.�������������ƂɃ\�[�g����<BR>
     * <BR>
     * �@@(*2) �p�����[�^.���i�敪 == "��������" or "�M�p���"�̏ꍇ�̂ݍ쐬<BR>
     * <BR>
     * �@@(*3) �p�����[�^.���i�敪 == "��������" or "�M�p���"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�����P�ʃe�[�u���D�쐬���t��ݒ�B<BR>
     * �@@      �p�����[�^.���i�敪 == "�敨" or "�I�v�V����"�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�����P�ʃe�[�u���D�󒍓�����ݒ�B<BR>
     * <BR>
     * �@@�E�����^�~���w��́A�\�[�g�L�[.�����^�~�� �w��ɏ]���ݒ�<BR>
     * <BR>
     * �R�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt������B<BR>
     * <BR>
     * �S�j�@@�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@param l_sortKey - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43263A0B0385
     */
    protected String createSortCond(String l_strCommodityDiv, WEB3SuccSortKey[] l_sortKey) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSortCond(String l_strCommodityDiv, WEB3SuccSortKey l_sortKey)  ";
        log.entering(STR_METHOD_NAME);
        
        if (l_sortKey == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�@@�p�����[�^.�\�[�g�L�[.�v�f�� == 1�̏ꍇ�Anull��ԋp����B
        if (l_sortKey.length == 1)
        {
            return null;
        }
        StringBuffer l_strReturn = new StringBuffer();
        String l_strEqualsReturn = null;

        //�Ԋ҂̒l�̐ݒ�        
        for (int i = 1; i < l_sortKey.length; i++)
        {
            log.debug(" �L�[����" + i + " = "+ l_sortKey[i].keyItem);
            log.debug(" �����^�~��" + i + " = "+ l_sortKey[i].keyItem);
            //�����R�[�h �@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D����ID
            if(WEB3ToSuccKeyItemDef.PRODUCT_CODE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("product_id");
            }
            //�E�s��R�[�h �@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�s��ID
            else if(WEB3ToSuccKeyItemDef.MARKET_CODE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("market_id");
            }
            //�����敪(*)�@@ �@@�@@�@@�F�����P�ʃe�[�u���D�ŋ敪
            else if(WEB3ToSuccKeyItemDef.TAX_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("tax_type");
                }
            }
            //����敪�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�������
            else if(WEB3ToSuccKeyItemDef.TRADING_TYPE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("order_type");
            }
            //�ٍϋ敪(*)�@@�@@�@@�@@ �F�����P�ʃe�[�u���D�ٍϋ敪
            else if(WEB3ToSuccKeyItemDef.REPAYMENT_DIV.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("repayment_type");
                }
            }
            //�E�l�i����(*)�@@�@@�@@�@@�F�����P�ʃe�[�u���D�l�i����
            else if(WEB3ToSuccKeyItemDef.PRICE_COND_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv) 
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("price_condition_type");
                }
            }
            //�E���s���� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D���s����
            else if(WEB3ToSuccKeyItemDef.EXEC_COND_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("execution_condition_type");
                }
            }
            //�E���������敪�@@�@@�@@�F
            //�@@�@@�@@(*1) nvl�i�����P�ʃe�[�u��.����������, �����P�ʃe�[�u��.���������j��ݒ肷��B
            else if(WEB3ToSuccKeyItemDef.ORDER_COND_TYPE.equals(l_sortKey[i].keyItem))
            {
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("nvl(org_order_condition_type, order_condition_type)");
                }
            }
            //�E�������� �@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D�쐬���t
            else if(WEB3ToSuccKeyItemDef.ORDER_DATE.equals(l_sortKey[i].keyItem))
            {
                //�p�����[�^.���i�敪 == "��������" or "�M�p���"�̏ꍇ�A
                //�����P�ʃe�[�u���D�쐬���t��ݒ�
                if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("created_timestamp");
                }
                //�p�����[�^.���i�敪 == "�敨" or "�I�v�V����"�̏ꍇ
                //�����P�ʃe�[�u���D�󒍓�����ݒ�B
                else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv)
                    || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
                {
                    l_strReturn.append("received_date_time");
                }
            }
            //�E������ �@@�@@�@@�@@�@@�@@�@@�F�����P�ʃe�[�u���D������
            else if (WEB3ToSuccKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKey[i].keyItem)) 
            {
                l_strReturn.append("biz_date");
            }
            //�E�����L������ �@@�@@�@@�F�����P�ʃe�[�u���D�����������t
            else if(WEB3ToSuccKeyItemDef.EXPIRATION_DATE.equals(l_sortKey[i].keyItem))
            {
                l_strReturn.append("expiration_date");
            }
            else
            {
                continue;
            }

            if (l_strReturn.length() != 0)
            {
                if (!l_strReturn.toString().equals(l_strEqualsReturn))
                {
                    l_strReturn.append(" ");
                    if(WEB3AscDescDef.ASC.equals(l_sortKey[i].ascDesc))
                    {
                        l_strReturn.append("ASC");
                    }
                    else
                    {
                        l_strReturn.append("DESC");
                    }

                    l_strReturn.append(" , ");
                }
                l_strEqualsReturn = l_strReturn.toString();
            }
        }
        
        //�R�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt������B
        l_strReturn.append("last_updated_timestamp");
        l_strReturn.append(" ");
        l_strReturn.append("ASC");
        
        //�S�j�@@�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strReturn.toString();
    }
    
    /**
     * (get�\���Ώ�Ifo�����P��)<BR>
     * �����̏����ɊY������Ifo�����P�ʂ�List��<BR>
     * �ԋp����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�����R�[�h == null And<BR>
     * �@@�p�����[�^.������ == null�̏ꍇ�A<BR>
     * �@@�p�����[�^.�����P�ʈꗗ��ԋp���A�I������B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@����ID���擾����B�@@<BR>
     * �@@���敨OP�v���_�N�g�}�l�[�W��.get����()�ɂĐ敨OP�������擾����B<BR>
     * <BR>
     * �@@�@@[get����()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�،���ЁF�@@�p�����[�^.�،����<BR>
     * �@@�@@�@@�����R�[�h�F�@@�p�����[�^.�����R�[�h<BR>
     * <BR>
     * �R�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �S�j�@@�p�����[�^.�����P�ʈꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�S�|�P�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A<BR>
     * �@@�@@�����Ώۂ̗v�f(=IfoOrderUnit).����ID != �擾��������ID�̏ꍇ�A<BR>
     * �@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�p�����[�^.������ != null�̏ꍇ�A<BR>
     * �@@�@@�����Ώۂ̗v�f(=IfoOrderUnit).������ != �p�����[�^.�������̏ꍇ�A<BR>
     * �@@�@@���̗v�f�֏������ڍs����B(continue;)<BR>
     * �@@�@@���p�����[�^.�������́AYYYYMMDD�`���Ƀt�H�[�}�b�g���邱�ƁB<BR>
     * <BR>
     * �@@�S�|�R�j�@@��������ArrayList�ɏ����Ώۂ̗v�f��ǉ�����B<BR>
     * <BR>
     * �T�j�@@��������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B<BR>
     * �@@�@@�@@�ȊO�A��������ArrayList��ԋp����B<BR>
     * @@param l_lisOrderUnitList - (�����P�ʈꗗ)<BR>
     * �����P�ʈꗗ)<BR>
     * @@param l_institution - (�،����)<BR>
     * �،���ЃI�u�W�F�N�g<BR>
     * @@param l_strProductCode - (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * @@param l_strBizDate - (������)<BR>
     * ������<BR>
     * @@return List
     * @@roseuid 431EC66202DD
     */
    protected List getDisplayObjIfoOrderUnit(
        List l_lisOrderUnitList, 
        WEB3GentradeInstitution l_institution, 
        String l_strProductCode, 
        String l_strBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDisplayObjIfoOrderUnit(List l_lisOrderUnitList, WEB3GentradeInstitution l_institution, String l_strProductCode, String l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
        /*
         * �P�j�@@�p�����[�^.�����R�[�h == null And
         * �@@�p�����[�^.������ == null�̏ꍇ�A
         * �@@�p�����[�^.�����P�ʈꗗ��ԋp���A�I������B
         */
        if (l_strProductCode == null && l_strBizDate == null)
        {
            return l_lisOrderUnitList;
        }
        
        //�Q�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A����ID���擾����B
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        long l_lngProductId = 0;
        if (l_strProductCode != null)
        {
            try
            {
                //�敨OP�v���_�N�g�}�l�[�W��.get����()�ɂĐ敨OP�������擾����B
                WEB3IfoProductImpl l_ifoProductImpl = l_ifoProductManagerImpl.getIfoProduct(l_institution, l_strProductCode);
                l_lngProductId = l_ifoProductImpl.getProductId(); 
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }            
        }
        //�R�j�@@ArrayList�𐶐�����B
        List l_lisReturn = new ArrayList();
        //�S�j�@@�p�����[�^.�����P�ʈꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_lisOrderUnitList.size(); i++)
        {
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_lisOrderUnitList.get(i);
            IfoOrderUnitRow l_row = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            /*
             * �S�|�P�j�@@�p�����[�^.�����R�[�h != null�̏ꍇ�A
             * �@@�@@�����Ώۂ̗v�f(=IfoOrderUnit).����ID != �擾��������ID�̏ꍇ�A
             * �@@�@@���̗v�f�֏������ڍs����B(continue;)
             */
            if (l_strProductCode != null)
            {
                if (l_lngProductId != l_row.getProductId())
                {
                    continue;
                }
            }
            
            /*
             * �S�|�Q�j�@@�p�����[�^.������ != null�̏ꍇ�A
             * �@@�@@�����Ώۂ̗v�f(=IfoOrderUnit).������ != �p�����[�^.�������̏ꍇ�A
             * �@@�@@���̗v�f�֏������ڍs����B(continue;)
             * �@@�@@���p�����[�^.�������́AYYYYMMDD�`���Ƀt�H�[�}�b�g���邱��
             */
            if (l_strBizDate != null)
            {
                if (!l_row.getBizDate().equals(l_strBizDate))
                {
                    continue;
                }
            }
            
            //�S�|�R�j�@@��������ArrayList�ɏ����Ώۂ̗v�f��ǉ�����B
            l_lisReturn.add(l_orderUnit);
        }

        
        //�T�j�@@��������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B
        //�ȊO�A��������ArrayList��ԋp����B
        if (l_lisReturn.isEmpty())
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.exiting(STR_METHOD_NAME);
        return l_lisReturn;
    }
    
    /**
     * (create�敨OP�������)<BR>
     * �����̒����P�ʈꗗ���A<BR>
     * �敨OP���������쐬���A�ԋp����B<BR>
     * <BR>
     * �P�j�@@TreeMap�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.�����P�ʈꗗ�̗v�f�����A<BR>
     * �@@�ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�@@�����Ώۂ̗v�f(=IfoOrderUnit)������ID���擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@TreeMap.get(����ID) != null�̏ꍇ�A<BR>
     * �@@�@@���̗v�f�֏������ڍs����B(continue)<BR>
     * <BR>
     * �@@�Q�|�R�j�@@�敨OP�������擾����B<BR>
     * �@@�@@�敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID)���R�[������B<BR>
     * <BR>
     * �@@�Q�|�S�j�@@�A�������������C���X�^���X�𐶐����A<BR>
     * �@@�@@�ȉ��̃v���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@�@@���������C���X�^���X.�����R�[�h = �敨OP����.�����R�[�h<BR>
     * �@@�@@���������C���X�^���X.������ = �敨OP����.������<BR>
     * <BR>
     * �@@�Q�|�T�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��TreeMap�ɒǉ�����B<BR>
     * �@@�@@TreeMap.put()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@�@@[put()�Ɏw�肷�����]<BR>
     * �@@�@@�@@key�F�@@����ID<BR>
     * �@@�@@�@@value�F�@@�v���p�e�B�Z�b�g�����C���X�^���X<BR>
     * <BR>
     * �R�j�@@��������TreeMap.values().toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_lisOrderUnitList - (�����P�ʈꗗ)<BR>
     * �����P�ʈꗗ<BR>
     * @@return WEB3SuccProductInfo[]
     * @@roseuid 431ECD8A029E
     */
    protected WEB3SuccProductInfo[] createIfoProductInfo(List l_lisOrderUnitList) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createIfoProductInfo(List l_lisOrderUnitList) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisOrderUnitList == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3ToSuccSettingListServiceImpl.class.getName() + STR_METHOD_NAME,
                "[l_lisOrderUnitList] = " + l_lisOrderUnitList
                );
        }
        //�P�j�@@TreeMap�𐶐�����B
        Map l_mapReturn = new TreeMap();
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        
        WEB3IfoProductManagerImpl l_ifoProductManagerImpl = 
            (WEB3IfoProductManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getProductManager();
        //�j�@@�p�����[�^.�����P�ʈꗗ�̗v�f�����A�ȉ��̏������J��Ԃ��B
        for (int i = 0; i <l_lisOrderUnitList.size(); i++)
        {
            WEB3SuccProductInfo l_productInfo = new WEB3SuccProductInfo();
            //�Q�|�P�j�@@�����Ώۂ̗v�f(=IfoOrderUnit)������ID���擾����B
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_lisOrderUnitList.get(i);
            long l_lngProductId = l_orderUnit.getProduct().getProductId();
            
            //�Q�|�Q�j�@@TreeMap.get(����ID) != null�̏ꍇ�A���̗v�f�֏������ڍs����B(continue)
            if (l_mapReturn.get(new Long(l_lngProductId)) != null)
            {
                continue;
            }
            //�Q�|�R�j�@@�敨OP�������擾����B�敨OP�v���_�N�g�}�l�[�W��.getProduct(����ID)���R�[������
            try
            {
                WEB3IfoProductImpl l_ifoProductImpl = (WEB3IfoProductImpl)l_ifoProductManagerImpl.getProduct(l_lngProductId);
                //�Q�|�S�j�@@�A�������������C���X�^���X�𐶐����A�ȉ��̃v���p�e�B���Z�b�g����B
                //���������C���X�^���X.�����R�[�h = �敨OP����.�����R�[�h
                l_productInfo.productCode = l_ifoProductImpl.getProductCode();
                //���������C���X�^���X.������ = �敨OP����.������
                l_productInfo.productName = l_ifoProductImpl.getStandardName();
                
                //�Q�|�T�j�@@�v���p�e�B�Z�b�g�����C���X�^���X��TreeMap�ɒǉ�����B
                l_mapReturn.put(new Long(l_lngProductId), l_productInfo);
            }
            catch (NotFoundException l_nfe)
            {
                log.error(l_nfe.getMessage(),l_nfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe);
            }
        }
        //�R�j�@@��������TreeMap.values().toArray()�̖߂�l��ԋp����B
        WEB3SuccProductInfo[] l_productInfo = new WEB3SuccProductInfo[l_mapReturn.size()];
        l_mapReturn.values().toArray(l_productInfo);        
        return l_productInfo;
    }
}
@
