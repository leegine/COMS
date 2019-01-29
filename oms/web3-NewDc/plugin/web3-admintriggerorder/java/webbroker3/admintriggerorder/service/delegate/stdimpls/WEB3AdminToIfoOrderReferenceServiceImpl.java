head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToIfoOrderReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�XImpl(WEB3AdminToIfoOrderReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/15 杊��](���u) �V�K�쐬
Revesion History : 2006/7/5 ���G�� (���u) �d�l�ύX���f��073
Revesion History : 2006/7/10 �юu�� (���u) �d�l�ύX���f��077
                 : 2006/8/25 �юu�� (���u) �d�l�ύX���f��071,075,082,088
                 : 2006/11/10  �đo�g(���u) �d�l�ύX���f��No.106
                 : 2006/12/04  ������(���u)�@@���f��No.069
Revesion History : 2007/06/30  �Ј���(���u) �d�l�ύX���f��No.129
Revesion History : 2007/07/13  �юu��(���u) �d�l�ύXDBLayoutNo.004
Revesion History : 2009/03/04 ���� (���u) ���f��No.130,131
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.data.AdmintoIfoOrderUnitRow;
import webbroker3.admintriggerorder.define.WEB3AdminToIfoKeyItemDef;
import webbroker3.admintriggerorder.define.WEB3AdminToIfoTaxTypeDef;
import webbroker3.admintriggerorder.define.WEB3AdminToTickMatchDivDef;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefRefResponse;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToOrderRefSortKey;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToIfoOrderReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3DealtDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3MarginTradingDivDef;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.define.WEB3TriggerOrderTypeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeHandlingOrderCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.ifo.WEB3IfoDataAdapter;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.rlsgateway.data.RlsConOrderHitNotifyParams;
import webbroker3.rlsgateway.data.RlsOrderMissParams;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�XImpl)<BR>
 * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X�����N���X<BR>
 *   
 * @@author 杊��]
 * @@version 1.0
 */
public class WEB3AdminToIfoOrderReferenceServiceImpl 
    extends WEB3ClientRequestService 
    implements WEB3AdminToIfoOrderReferenceService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminToIfoOrderReferenceServiceImpl.class);

    /**
     * @@roseuid 43F1BBDD003E
     */
    public WEB3AdminToIfoOrderReferenceServiceImpl() 
    {
     
    }
    
    /**
     * �敨OP�����Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A<BR> 
     * �ȉ��̃��\�b�h���Ăѕ�����B<BR> 
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��N�G�X�g�̏ꍇ<BR> 
     * �@@this.get���͉��()���R�[������B<BR> 
     * <BR>
     * ���g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g�̏ꍇ<BR> 
     * �@@this.get�Ɖ���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E3558700E3
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�l�s���B");
        }
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminToIfoOrderRefInpRequest)
        {
            l_response = this.getInputScreen((WEB3AdminToIfoOrderRefInpRequest) l_request);
        }
        else if (l_request instanceof WEB3AdminToIfoOrderRefRefRequest)
        {   
            l_response = this.getReferenceScreen((WEB3AdminToIfoOrderRefRefRequest) l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�p�����[�^�^�C�v�s���B");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �敨OP�����Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X)get���͉�ʁv<BR>
     * �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ���̓��N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToIfoOrderRefInpResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E6BB5E020D
     */
    protected WEB3AdminToIfoOrderRefInpResponse getInputScreen(
        WEB3AdminToIfoOrderRefInpRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminToIfoOrderRefInpRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�敨OP(�g���K�[�����Ɖ�) 
        //is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_INQUIRY, false);
        
        //1.4 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.5 ArrayList( )
        List l_lisDates = new ArrayList();

        //�w����ʈꗗ�쐬�p
        Map l_targetProductCodeMap = new TreeMap();

        //1.6 (*)�Ɩ����t�̑O�c�Ɠ��A�Ɩ����t��ǉ�����B
        //      �Ɩ����t�F GtlUtils.getTradingSystem().getBizDate()
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        WEB3GentradeBizDate l_bizDateCalcUtil = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
        Timestamp l_tsDevidendRightDate = l_bizDateCalcUtil.roll(-1);
        l_lisDates.add(WEB3DateUtility.toDay(l_tsDevidendRightDate));
        l_lisDates.add(WEB3DateUtility.toDay(l_datBizDate));
        
        //1.7 get�i���X�w���ʁj�戵����(�،���ЃR�[�h : String)
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexDealtConds = 
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndex(l_strInstitutionCode);
        
        //1.8 (*)get�i���X�w���ʁj�戵����()�̖߂�l�̗v�f����Loop����
        int l_intCondsLength = l_branchIndexDealtConds.length;
        int l_intLisDatesLength = l_lisDates.size();
        boolean l_blnFlag = false;
        for (int i = 0; i < l_intCondsLength; i++ )
        {
            //1.8.1 reset�����R�[�h(�����R�[�h : String)
            WEB3GentradeTradingTimeManagement.resetProductCode(l_branchIndexDealtConds[i].getUnderlyingProductCode());
         
            //1.8.2 get������( )
            Date l_datOrderBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

            //1.8.3 (*)�擾������������ArrayList�Ɋ܂܂�Ă��Ȃ��ꍇ�́A�ǉ�����
            //         �s��ǌ�̎w�������݂���ꍇ�A���������̔��������܂߂�B
            l_blnFlag = false;
            for (int j = 0; j < l_intLisDatesLength; j++)
            {
                if (WEB3DateUtility.compareToDay((Date) (l_lisDates.get(j)), l_datOrderBizDate) == 0)
                {
                    l_blnFlag = true;
                    break;
                }
            }
            
            if (!l_blnFlag)
            {
                l_lisDates.add(WEB3DateUtility.toDay(l_datOrderBizDate));
                l_intLisDatesLength++;
            }

            String l_targetProductCode = l_branchIndexDealtConds[i].getUnderlyingProductCode();

            //�w����ʈꗗ�쐬
            l_targetProductCodeMap.put(l_targetProductCode,l_targetProductCode);
        }
        
        //1.9 toArray( )
        Date[] l_datBizDates = new Date[l_lisDates.size()];
        l_lisDates.toArray(l_datBizDates);
        
        //1.10 is���i���{(�،���ЃR�[�h : String, ���X�R�[�h : String[], ���i�敪 : String)
        boolean l_blnIsProductExecOption =
            WEB3AdminToDataManager.isProductExec(
                l_strInstitutionCode,
                l_request.branchCode,
                WEB3CommodityDivDef.OPTION);
        
        String[] l_strOrderCondTypeList = null;
        
        //1.11 �i����t���[�F �I�v�V�������{��Ёiis���i���{()��true�j�̏ꍇ�j
        if (l_blnIsProductExecOption)
        {
            //1.11.1 get�戵�\����������ʈꗗ�iPR�w�j(�،���ЃR�[�h : String, ���i�敪 : String)
            l_strOrderCondTypeList = 
                this.getValidTriggerOrderTypeListByPr(
                    l_strInstitutionCode, WEB3CommodityDivDef.OPTION);
        }
        
        //1.12 is���i���{(�،���ЃR�[�h : String, ���X�R�[�h : String[], ���i�敪 : String)
        boolean l_blnIsProductExecFuture =
            WEB3AdminToDataManager.isProductExec(
                l_strInstitutionCode,
                l_request.branchCode,
                WEB3CommodityDivDef.FUTURE);

        String[] l_strOrderCondTypeListByPrs = null;
        
        //1.13 �i����t���[�F �敨���{��Ёiis���i���{()��true�j�̏ꍇ�j
        if (l_blnIsProductExecFuture)
        {
            //1.13.1 get�戵�\����������ʈꗗ�iPR�w�j(�،���ЃR�[�h : String, ���i�敪 : String)
            l_strOrderCondTypeListByPrs = 
                this.getValidTriggerOrderTypeListByPr(
                    l_strInstitutionCode, WEB3CommodityDivDef.FUTURE);
        }
        
        //1.14 createResponse( )
        WEB3AdminToIfoOrderRefInpResponse l_response = 
            (WEB3AdminToIfoOrderRefInpResponse) l_request.createResponse();
        
        //1.15 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //�������ꗗ   ���@@��������ArrayList.toArray()
        l_response.orderBizDateList = l_datBizDates;
        
        //����������ʈꗗ    �� �@@(*1)get�戵�\����������ʈꗗ(PR�w)()�̖߂�l
        //(*1)�敨�E�I�v�V���������̏���������ʈꗗ���擾���Ă���ꍇ�́A
        //�擾�����ꗗ�̂����Aunique�Ȓl�݂̂̔z����Z�b�g����B
        //���i�����{�A�܂��́A�戵�\���������Ȃ��̏ꍇ�́Anull���Z�b�g����B
        List l_lisOrderConds = new ArrayList();
        int l_intFlag = 1;
        if (l_strOrderCondTypeListByPrs != null)
        {
            if (l_strOrderCondTypeList == null)
            {
                l_strOrderCondTypeList = l_strOrderCondTypeListByPrs;
            }
            else
            {
                l_intFlag = 2;
                int l_intLength = l_strOrderCondTypeList.length;
                int l_intLengthByPr = l_strOrderCondTypeListByPrs.length;
                for (int j = 0; j < l_intLength; j++)
                {
                    l_lisOrderConds.add(l_strOrderCondTypeList[j]);
                }
                
                for (int i = 0; i < l_intLengthByPr; i++)
                {
                    boolean l_blnListFlag = false;
                    for (int j = 0; j < l_intLength; j++)
                    {
                        
                        if (l_strOrderCondTypeListByPrs[i].equals(l_strOrderCondTypeList[j]))
                        {
                            l_blnListFlag = true;
                            break;
                        }
                    }
                    
                    if (!l_blnListFlag)
                    {
                        l_lisOrderConds.add(l_strOrderCondTypeListByPrs[i]);
                    }
                }
            }
        }
        
        if (l_intFlag == 1)
        {
            l_response.triggerOrderTypeList = l_strOrderCondTypeList;
        }
        else
        {
            String[] l_strCondTypeList = new String[l_lisOrderConds.size()];
            l_lisOrderConds.toArray(l_strCondTypeList);
            l_response.triggerOrderTypeList = l_strCondTypeList;
        }

        //get�i���X�w���ʁj�戵�����̂����A
        //unique�Ȏw���i�����Y�����R�[�h�j�݂̂̔z����Z�b�g����
        if(!l_targetProductCodeMap.isEmpty())
        {
            String l_strTargetProductCodeList[] = new String[l_targetProductCodeMap.size()];

            l_targetProductCodeMap.keySet().toArray(l_strTargetProductCodeList);

            l_response.targetProductList = l_strTargetProductCodeList;
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�Ɖ���)<BR>
     * �敨OP�����Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X)get�Ɖ��ʁv<BR>
     * �Q�ƁB<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToIfoOrderRefRefResponse
     * @@throws WEB3BaseException
     * @@roseuid 43E6BB5E022C
     */
    protected WEB3AdminToIfoOrderRefRefResponse getReferenceScreen(
        WEB3AdminToIfoOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminToIfoOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.�敨OP(�g���K�[�����Ɖ�) 
        //is�X�V�F�@@false(�X�V�Ȃ�) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_INQUIRY, false);

        //1.4 validate���X����(���X�R�[�h : String[])
        l_admin.validateBranchPermission(l_request.branchCode);
        
        //1.5 get�،���ЃR�[�h( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 create���ʌ�������������(�،���ЃR�[�h : String,
        //    ���N�G�X�g�f�[�^ : �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g)
        String l_strCommonQueryString = WEB3AdminToDataManager.createCommonQueryString(
            l_strInstitutionCode, l_request);
        
        // 1.8.1 / 1.14.1 /1.16.1 /1.20  createResponse( )
        WEB3AdminToIfoOrderRefRefResponse l_response =
            (WEB3AdminToIfoOrderRefRefResponse) l_request.createResponse();
        
        // 1.8.2 / 1.14.2 /1.16.2  �v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //���y�[�W��              �� 0
        l_response.totalPages = "0";
        //�����R�[�h��            �� 0
        l_response.totalRecords = "0";
        //�\���y�[�W�ԍ�          �� 0
        l_response.pageIndex = "0";
        //�敨OP�����Ɖ�Unit�ꗗ  �� 0
        l_response.ifoOrderList = null;
        
        //1.7 get����ID(String, String, String, String, String)
        //[����] 
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l 
        //�w����ʁF�@@���N�G�X�g�f�[�^.�w����� 
        //�����F�@@���N�G�X�g�f�[�^.���� 
        //�s�g���i�F�@@���N�G�X�g�f�[�^.�s�g���i 
        //�I�v�V�������i�敪�F�@@���N�G�X�g�f�[�^.�I�v�V�������i�敪
        String[] l_strProductIds = null;
        try 
        {
            l_strProductIds =
                this.getProductIdList(
                    l_strInstitutionCode,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    l_request.strikePrice,
                    l_request.opProductType);
        }
        //1.8 ����t���[�F get����ID�ꗗ()����O��throw�����ꍇ�j
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }

        //1.9 create��������������(�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g, String[])
        String l_strQueryString = this.createQueryString(l_request, l_strProductIds);
        
        //1.10 create���ʌ��������f�[�^�R���e�i(�،���ЃR�[�h : String,
        //���N�G�X�g�f�[�^ : �g���K�[�����Ǘ��ҁE�����Ɖ�ʃ��N�G�X�g, �����^�C�v : ProductTypeEnum)
        String[] l_strDataContainers = 
            WEB3AdminToDataManager.createCommonQueryDataContainer(
                l_strInstitutionCode, 
                l_request,
                ProductTypeEnum.IFO);
        
        //1.11 create���������f�[�^�R���e�i(�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g, String[])
        String[] l_strQueryDataContainers = this.createQueryDataContainer(l_request, l_strProductIds);
        
        //1.12 create�\�[�g����(�����Ɖ�\�[�g�L�[[])
        //[����] 
        //�\�[�g�L�[�F�@@���N�G�X�g�f�[�^.�\�[�g�L�[
        String l_strSortCondition = this.createSortCond(l_request.sortKeys);
        
        //1.13 get�����P�ʈꗗ(String, String[], String)
        //[get�����P�ʈꗗ()�̈����ݒ�]
        //��������������F
        //�@@create���ʌ�������������()�̖߂�l�{create��������������()�̖߂�l
        //���������f�[�^�R���e�i�F
        //  create���ʌ��������f�[�^�R���e�i()�̖߂�l�{create���������f�[�^�R���e�i()�̖߂�l
        //�\�[�g�����F
        //�@@create�\�[�g����()�̖߂�l
        int l_intDcLength = l_strDataContainers.length;
        int l_intQdcLength = l_strQueryDataContainers.length;
        String[] l_strContainers = new String[l_intDcLength + l_intQdcLength];
        List l_lisContainers = new ArrayList();
        for (int i = 0; i < l_intDcLength; i++)
        {
            l_lisContainers.add(l_strDataContainers[i]);
        }
        
        for (int i = 0; i < l_intQdcLength; i++)
        {
            l_lisContainers.add(l_strQueryDataContainers[i]);
        }
        
        l_lisContainers.toArray(l_strContainers);
        
        IfoOrderUnitParams[] l_orderUnitList = this.getOrderUnitList(
            l_strCommonQueryString + l_strQueryString,
            l_strContainers,
            l_strSortCondition);

        //1.14 �i����t���[�F get�����P�ʈꗗ()�̖߂�l��null�̏ꍇ�j
        if (null == l_orderUnitList)
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.15 remove�J�z�������P��(�����P��Params�ꗗ : IfoOrderUnitParams[])
        //[����] 
        //�����P��Params�ꗗ�F�@@get�����P�ʈꗗ()�̖߂�l
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManagerImpl =
            (WEB3OptionOrderManagerImpl) l_tradingModule.getOrderManager();

        IfoOrderUnitParams[] l_orderUnitParams = l_orderManagerImpl.removeCarryOverOriginalOrderUnit(l_orderUnitList);
        int l_intLength = l_orderUnitParams.length;
        IfoOrderUnit[] l_orderUnits = new IfoOrderUnit[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            l_orderUnits[i] = (IfoOrderUnit) l_orderManagerImpl.toOrderUnit(l_orderUnitParams[i]);
        }
        
        //1.16 �i����t���[�F remove�J�z�������P��()�̖߂�l��null�̏ꍇ�j
        if (null == l_orderUnitParams)
        {
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        
        //1.17 WEB3PageIndexInfo(l_objs : �_���r���[::java::lang::Object[], 
        //l_intRequestPageIndex : l_intRequestPageSize : int)
        WEB3PageIndexInfo l_pageIndexInfo = 
            new WEB3PageIndexInfo(
                l_orderUnits,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));
        
        //1.18 getArrayReturned(l_classType : Class)
        IfoOrderUnit[] l_ifoOrderUnits = (IfoOrderUnit[]) l_pageIndexInfo.getArrayReturned(IfoOrderUnit.class);
        
        //1.19 create�敨OP�����Ɖ�Unit�ꗗ(IfoOrderUnit[], String)
        WEB3AdminToIfoOrderRefUnit[] l_refUnits =
            this.createIfoOrderRefUnitList(l_ifoOrderUnits, l_request);
        
        //1.21 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�Ƀv���p�e�B���Z�b�g����B
        //���y�[�W��       �� WEB3PageIndexInfo.getTotalPages()
        l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
        
        //�����R�[�h��      �� WEB3PageIndexInfo.getTotalRecords()
        l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());

        //�\���y�[�W�ԍ�     �� WEB3PageIndexInfo.getPageIndex()
        l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex());
        
        //�敨OP�����Ɖ�Unit�ꗗ  �� create�敨OP�����Ɖ�Unit�ꗗ()�̖߂�l
        l_response.ifoOrderList = l_refUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get����ID�ꗗ)<BR>
     * �����̏����ɊY������敨OP����.����ID�̈ꗗ�ԋp����B<BR>
     * <BR>
     * �P�j �ȉ��̃p�����[�^���S��null�̏ꍇ�́Anull��ԋp����B<BR>
     * �@@�E����<BR>
     * �@@�E�s�g���i<BR>
     * �@@�E�I�v�V�������i�敪<BR>
     * <BR>
     * �Q�j DB����<BR>
     * �@@�ȉ��̏����Ő敨OP�����e�[�u������������B<BR>
     * <BR>
     * �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ����<BR>
     * �@@�����Y�����R�[�h(*1) = �p�����[�^.�w����� ����<BR>
     * �@@����(*1) = �p�����[�^.���� ����<BR>
     * �@@�s�g���i(*1) = �p�����[�^.�s�g���i ����<BR>
     * �@@�敨�I�v�V�������i(*1) = �p�����[�^.�I�v�V�������i�敪<BR>
     * <BR>
     * �@@(*1)�Ή�����p�����[�^��null�̏ꍇ�́A���������ɉ����Ȃ��B<BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v��<BR>
     * �@@��O���X���[����B<BR>
     * class: WEB3BusinessLayerException<BR>
     * tag  : BUSINESS_ERROR_01037<BR>
     * <BR>
     * �R�j �������ʂ̑S�v�f�������ID���擾���A<BR>
     * �@@����ID�̔z��𐶐����ĕԋp����B <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strTargetProductCode - (�w�����)<BR>
     * �w�����<BR>
     * @@param l_strDelivaryMonth - (����)<BR>
     * ����<BR>
     * @@param l_strStrikePrice - (�s�g���i)<BR>
     * �s�g���i<BR>
     * @@param l_strOpProductType - (�I�v�V�������i�敪)<BR>
     * �I�v�V�������i�敪<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 43E87A150154
     */
    protected String[] getProductIdList(
        String l_strInstitutionCode,
        String l_strTargetProductCode,
        String l_strDelivaryMonth,
        String l_strStrikePrice,
        String l_strOpProductType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getProductId(String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j �ȉ��̃p�����[�^���S��null�̏ꍇ�́Anull��ԋp����B
        //     �E����
        //     �E�s�g���i
        //     �E�I�v�V�������i�敪
        if (null == l_strDelivaryMonth
            && null == l_strStrikePrice
            && null == l_strOpProductType)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�Q�j DB����
        //   �ȉ��̏����Ő敨OP�����e�[�u������������B
        //   �@@�،���ЃR�[�h = �p�����[�^.�،���ЃR�[�h ����
        //   �@@�����Y�����R�[�h(*1) = �p�����[�^.�w����� ����
        //   �@@����(*1) = �p�����[�^.���� ����
        //   �@@�s�g���i(*1) = �p�����[�^.�s�g���i ����
        //   �@@�敨�I�v�V�������i(*1) = �p�����[�^.�I�v�V�������i�敪
        //  �@@(*1)�Ή�����p�����[�^��null�̏ꍇ�́A���������ɉ����Ȃ��B
        String l_strWhere = " institution_code = ? ";

        ArrayList l_lisConditions = new ArrayList();
        l_lisConditions.add(l_strInstitutionCode);

        if (null != l_strTargetProductCode)
        {
            l_strWhere += " and underlying_product_code = ? ";
            l_lisConditions.add(l_strTargetProductCode);
        }

        if (null != l_strDelivaryMonth)
        {
            l_strWhere += " and month_of_delivery = ? ";
            l_lisConditions.add(l_strDelivaryMonth);
        }
        
        if (null != l_strStrikePrice)
        {
            l_strWhere += " and strike_price = ? ";
            l_lisConditions.add(l_strStrikePrice);
        }

        if (null != l_strOpProductType)
        {
            l_strWhere += " and derivative_type = ?";
            //�p�����[�^.�I�v�V�������i�敪��"�v�b�g�I�v�V����"�̏ꍇ�AIfoDelivativeTypeEnum.�v�b�g�I�v�V����
            if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_strOpProductType))
            {
                l_lisConditions.add(String.valueOf(IfoDerivativeTypeEnum.PUT_OPTIONS.intValue()));
            }
            //�p�����[�^.�I�v�V�������i�敪��"�R�[���I�v�V����"�̏ꍇ�AIfoDelivativeTypeEnum.�R�[���I�v�V����
            else if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_strOpProductType))
            {
                l_lisConditions.add(String.valueOf(IfoDerivativeTypeEnum.CALL_OPTIONS.intValue()));
            }
        }
        
        String[] l_strConditions = new String[l_lisConditions.size()];
        l_lisConditions.toArray(l_strConditions);
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                IfoProductRow.TYPE,
                l_strWhere,
                l_strConditions);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }

        //�������ʂ��擾�ł��Ȃ������ꍇ�A�u�Y���f�[�^�Ȃ��v�̗�O���X���[����B
        if (null == l_lisReturns || l_lisReturns.size() == 0)
        {
            log.debug("�����ɊY������f�[�^�����݂��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�����ɊY������f�[�^�����݂��Ȃ��B");
        }

        //�R�j �������ʂ̑S�v�f�������ID���擾���A
        // ����ID�̔z��𐶐����ĕԋp����B
        IfoProductRow[] l_rows = new IfoProductRow[l_lisReturns.size()];
        l_lisReturns.toArray(l_rows);
        
        int l_intSize = l_lisReturns.size();
        String[] l_strs = new String[l_intSize];
        for (int i = 0; i < l_intSize; i++)
        {
            l_strs[i] = String.valueOf(l_rows[i].getProductId());
        }
        log.exiting(STR_METHOD_NAME);
        return l_strs;
    }
    
    /**
     * (create��������������)<BR>
     * ����������������쐬����B<BR>
     * <BR>
     * �P�j�@@��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.���i�敪��null�̏ꍇ�A<BR>
     * �@@���i�敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and future_option_div = ? "<BR>
     * <BR>
     * �R�j�@@�p�����[�^.����ID�ꗗ��null�̏ꍇ�A<BR>
     * �@@����ID����������������ɒǉ�����B<BR>
     * �@@�p�����[�^.����ID�ꗗ�̗v�f����"?"��ǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_id in (?,?,,,)" <BR>
     * <BR>
     * �@@�p�����[�^.����ID�ꗗ��null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����ʁ�null�@@�̏ꍇ�A<BR>
     * �@@�w����ʂ���������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and product_id like '?' "<BR>
     * <BR>
     * �S�j�@@�쐬�������������������ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_strProductIds - (����ID�ꗗ)<BR>
     * ����ID<BR>
     * @@return String
     * @@roseuid 43E87DDF03B5
     */
    protected String createQueryString(WEB3AdminToIfoOrderRefRefRequest l_request, String[] l_strProductIds) 
    {
        final String STR_METHOD_NAME = " createQueryString(WEB3AdminToIfoOrderRefRefRequest, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@��������������C���X�^���X(�FString)�𐶐�����B
        String l_strQueryString = new String();
        
        //�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.���i�敪��null�̏ꍇ�A���i�敪����������������ɒǉ�����B
        //�������������� += "and future_option_div = ? "
        if (null != l_request.productDiv)
        {
            l_strQueryString += "and future_option_div = ? ";
        }
        
        //�R�j�@@�p�����[�^.����ID�ꗗ��null�̏ꍇ�A ����ID����������������ɒǉ�����B
        //�p�����[�^.����ID�ꗗ�̗v�f����"?"��ǉ�����B
        //�������������� += "and product_id in (?,?,,,)"
        if (null != l_strProductIds)
        {
            l_strQueryString += "and product_id in (? ";
            int l_intSize = l_strProductIds.length;
            for (int i = 0; i < l_intSize; i++)
            {
                if (i != 0)
                {
                    l_strQueryString += ",?";
                }
            }
            l_strQueryString += ")";
        }

        //�p�����[�^.����ID�ꗗ��null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����ʁ�null�@@�̏ꍇ�A
        //�w����ʂ���������������ɒǉ�����B
        //�������������� += "and product_id like '?' "
        if (l_strProductIds == null && l_request.targetProductCode != null)
        {
            l_strQueryString += "and product_id like ? ";
        }

        //�S�j�@@�쐬�������������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i���쐬����B<BR>
     * <BR>
     * �P�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.���i�敪��null�̏ꍇ�A<BR>
     * �@@���i�𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     *  �@@[�p�����[�^.���i�敪��"�敨"�̏ꍇ]<BR>
     * �@@�@@�E"�敨"�i�敨�^�I�v�V�����敪�j<BR>
     * �@@[�p�����[�^.���i�敪��"�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�E"�I�v�V����"�i�敨�^�I�v�V�����敪�j<BR>
     * <BR>
     * �R�j�@@�p�����[�^.����ID�ꗗ��null�̏ꍇ�A<BR>
     * �@@����ID�ꗗ�̑S�v�f�𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �@@�p�����[�^.����ID�ꗗ��null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����ʁ�null�̏ꍇ�A<BR>
     * �@@'%' + �p�����[�^.���N�G�X�g�f�[�^.�w����ʂ̉��Q���𐶐�����ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * @@param l_strProductIds - (����ID�ꗗ)<BR>
     * ����ID<BR>
     * @@return String[]
     * @@roseuid 43E8859E00FE
     */
    protected String[] createQueryDataContainer(WEB3AdminToIfoOrderRefRefRequest l_request, String[] l_strProductIds) 
    {
        final String STR_METHOD_NAME = " createQueryDataContainer(WEB3AdminToIfoOrderRefRefRequest, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@ArrayList�𐶐�����B
        ArrayList l_lisQueryDataContainers = new ArrayList();
        
        //�Q�j�@@�p�����[�^.���N�G�X�g�f�[�^.���i�敪��null�̏ꍇ�A���i�𔻕ʂ�������𐶐�����ArrayList�ɒǉ�����B
        //[�p�����[�^.���i�敪��"�敨"�̏ꍇ]�E"�敨"�i�敨�^�I�v�V�����敪�j
        //[�p�����[�^.���i�敪��"�I�v�V����"�̏ꍇ]�E"�I�v�V����"�i�敨�^�I�v�V�����敪�j
        if (null != l_request.productDiv)
        {    
            if (WEB3CommodityDivDef.FUTURE.equals(l_request.productDiv))
            {
                l_lisQueryDataContainers.add(WEB3FuturesOptionDivDef.FUTURES);
            }
            else if (WEB3CommodityDivDef.OPTION.equals(l_request.productDiv))
            {
                l_lisQueryDataContainers.add(WEB3FuturesOptionDivDef.OPTION);
            }
        }
        
        //�R�j�@@�p�����[�^.����ID�ꗗ��null�̏ꍇ�A����ID�ꗗ�̑S�v�f�𐶐�����ArrayList�ɒǉ�����B
        if (null != l_strProductIds)
        {
            int l_intSize = l_strProductIds.length;
            for (int i = 0; i < l_intSize; i++)
            {
                l_lisQueryDataContainers.add(l_strProductIds[i]);
            }
        }

        //�p�����[�^.����ID�ꗗ��null�@@���@@�p�����[�^.���N�G�X�g�f�[�^.�w����ʁ�null�̏ꍇ�A
        //'%' + �p�����[�^.���N�G�X�g�f�[�^.�w����ʂ̉��Q���𐶐�����ArrayList�ɒǉ�����B
        if (l_strProductIds == null && l_request.targetProductCode != null)
        {
            String l_strValue = l_request.targetProductCode.substring(2, 4);
            l_lisQueryDataContainers.add("%" + l_strValue);
        }

        //�S�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strContainers = new String[l_lisQueryDataContainers.size()];
        l_lisQueryDataContainers.toArray(l_strContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strContainers;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�\�[�g�L�[��null�̏ꍇ�A<BR>
     * �@@�@@"����ID ����"�̃\�[�g������ԋp����B<BR>
     * <BR>
     * �Q�j�@@�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�E�u���X�R�[�h�v�@@���@@�敨OP�����P��.���XID<BR>
     * �@@�@@�E�u�ڋq�R�[�h�v�@@���@@substr(�敨OP�����P��.����ID, 9, 6)<BR>
     * �@@�@@�E�u�����R�[�h�v�@@���@@�敨OP�����P��.����ID<BR>
     * �@@�@@�E�u�s��R�[�h�v�@@���@@�敨OP�����P��.�s��ID<BR>
     * �@@�@@�E�u�����敪�v�@@���@@substr(�敨OP�����P��.�⏕����ID, 13, 2)<BR>
     * �@@�@@�E�u���i�敪�v�@@���@@�敨OP�����P��.�敨�^�I�v�V�����敪<BR>
     * �@@�@@�E�u����敪�v�@@���@@�敨OP�����P��.�������<BR>
     * �@@�@@�E�u���s�����v�@@���@@�敨OP�����P��.���s����<BR>
     * �@@�@@�E�u�����L�������v�@@���@@�敨OP�����P��.�����������t<BR>
     * �@@�@@�E�u�������ԁv�@@���@@�敨OP�����P��.�󒍓���<BR>
     * �@@�@@�E�u�������v�@@���@@�敨OP�����P��.������<BR>
     * �@@�@@�E�u��n���v�@@���@@�敨OP�����P��.��n��<BR>
     * <BR>
     * �@@�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �S�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��<BR>
     * <BR>
     * �T�j�@@�쐬�����\�[�g�����������ԋp����B<BR>
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�̔z��<BR>
     * @@return String
     * @@roseuid 43E886C70246
     */
    protected String createSortCond(WEB3AdminToOrderRefSortKey[] l_sortKeys) 
    {
        final String STR_METHOD_NAME = " createSortCondition(WEB3AdminToOrderRefSortKey[])";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�p�����[�^.�\�[�g�L�[��null�̏ꍇ�A"����ID ����"�̃\�[�g������ԋp����B
        if (null == l_sortKeys)
        {
            log.exiting(STR_METHOD_NAME);
            return " product_id asc ";
        }

        //�Q�j�@@�\�[�g����������(�FString)���쐬����B
        StringBuffer l_strBuf = new StringBuffer();
        
        //�R�j�@@�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B
        for (int i = 0; i < l_sortKeys.length; i++)
        {
            //�R�|�P�j�@@�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A�쐬�����\�[�g����������ɒǉ�����B
            //�E�u���X�R�[�h�v�@@���@@�敨OP�����P��.���XID
            if (WEB3AdminToIfoKeyItemDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("branch_id");
            }
            //�E�u�ڋq�R�[�h�v�@@���@@substr(�敨OP�����P��.����ID, 9, 6)
            else if (WEB3AdminToIfoKeyItemDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("substr(account_id, 9, 6)");
            }
            //�E�u�����R�[�h�v�@@���@@�敨OP�����P��.����ID
            else if (WEB3AdminToIfoKeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("product_id");
            }
            //�E�u�s��R�[�h�v�@@���@@�敨OP�����P��.�s��ID
            else if (WEB3AdminToIfoKeyItemDef.MARKET_CODE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("market_id");
            }
            //�E�u�����敪�v�@@���@@substr(�敨OP�����P��.�⏕����ID, 13, 2)
            else if (WEB3AdminToIfoKeyItemDef.TAX_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("substr(sub_account_id, 13, 2)");
            }
            //�E�u���i�敪�v�@@���@@�敨OP�����P��.�敨�^�I�v�V�����敪
            else if (WEB3AdminToIfoKeyItemDef.PRODUCT_DIV.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("future_option_div");
            }
            //�E�u����敪�v�@@���@@�敨OP�����P��.�������
            else if (WEB3AdminToIfoKeyItemDef.TRADING_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("order_type");
            }
            //�E�u���s�����v�@@���@@�敨OP�����P��.���s����
            else if (WEB3AdminToIfoKeyItemDef.EXEC_COND_TYPE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("execution_condition_type");
            }
            //�E�u�����L�������v�@@���@@�敨OP�����P��.�����������t
            else if (WEB3AdminToIfoKeyItemDef.EXPIRATION_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("expiration_date");
            }
            //�E�u�������ԁv�@@���@@�敨OP�����P��.�󒍓���
            else if (WEB3AdminToIfoKeyItemDef.ORDER_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("received_date_time");
            }
            //�E�u�������v�@@���@@�敨OP�����P��.������
            else if (WEB3AdminToIfoKeyItemDef.ORDER_BIZ_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("biz_date");
            }
            //�E�u��n���v�@@���@@�敨OP�����P��.��n��
            else if (WEB3AdminToIfoKeyItemDef.DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                l_strBuf.append("delivery_date");
            }
            else
            {
                continue;
            }
         
            //�R�|�Q�j�@@�\�[�g�L�[.�����^�~���ɑΉ�����擾����(asc or desc)���\�[�g����������ɒǉ�����B
            if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
            {
                l_strBuf.append(" asc,");
            }
            else
            {
                l_strBuf.append(" desc,");
            }
        }     

        //�S�j�@@�\�[�g���������ɁA�����P�ʃe�[�u��.�X�V���t�������w��ŕt��
        l_strBuf.append(" last_updated_timestamp");
        l_strBuf.append(" asc");
        
        //�T�j�@@�쐬�����\�[�g�����������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strBuf.toString();
    }
    
    /**
     * (get�����P�ʈꗗ)<BR>
     * �����̏����ɊY�����钍���P�ʂ̈ꗗ��ԋp����B<BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@arg0�F�@@�敨OP�����P��ViewRow(AdmintoIfoOrderUnitRow).TYPE<BR>
     * �@@�@@arg1�F�@@�p�����[�^.��������������<BR>
     * �@@�@@arg2�F�@@�p�����[�^.�\�[�g����<BR>
     * �@@�@@arg3�F�@@null<BR>
     * �@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i<BR>
     * <BR>
     * �Q�j�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �R�j�������ʂ�List����IfoOrderUnitParams[]�𐶐����ĕԋp����B<BR>
     * @@param l_strQueryString - (��������������)<BR>
     * ��������������<BR>
     * @@param l_strQueryDataContainer - (���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i<BR>
     * @@param l_strSortCond - (�\�[�g����)<BR>
     * �\�[�g����<BR>
     * @@return IfoOrderUnitParams[]
     * @@throws WEB3BaseException
     * @@roseuid 43E95FBE03E3
     */
    protected IfoOrderUnitParams[] getOrderUnitList(
        String l_strQueryString, 
        String[] l_strQueryDataContainer,
        String l_strSortCond) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnitList(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
        //  �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]
        //  �@@arg0�F�@@�敨OP�����P��ViewRow(AdmintoIfoOrderUnitRow).TYPE 
        //�@@�@@arg1�F�@@�p�����[�^.��������������
        //�@@�@@arg2�F�@@�p�����[�^.�\�[�g����
        //�@@�@@arg3�F�@@null
        //�@@�@@arg4�F�@@�p�����[�^.���������f�[�^�R���e�i
        List l_lisReturns = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns = l_queryProcessor.doFindAllQuery(
                AdmintoIfoOrderUnitRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_strQueryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        //�Q�j�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (null == l_lisReturns || l_lisReturns.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        //�R�j�������ʂ�List����IfoOrderUnitParams[]�𐶐����ĕԋp����B
        IfoOrderUnitParams[] l_orderUnitParams = new IfoOrderUnitParams[l_lisReturns.size()];
        for (int i = 0; i < l_lisReturns.size(); i++)
            l_orderUnitParams[i] = toIfoOrderUnitParams((AdmintoIfoOrderUnitRow)l_lisReturns.get(i));
        
        log.exiting(STR_METHOD_NAME);
        return l_orderUnitParams;
    }
    
    /**
     * (create�敨OP�����Ɖ�Unit�ꗗ)<BR>
     * �����̒����P�ʈꗗ���A�敨OP�������Ɖ�Unit�̈ꗗ��<BR>
     * �쐬���A�ԋp����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�T�[�r�X)create�敨OP�����Ɖ�Unit�ꗗ�v<BR>
     * �Q�ƁB<BR>
     * @@param l_orderUnitList - (�����P�ʈꗗ)<BR>
     * �敨OP�����P�ʂ̔z��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3AdminToIfoOrderRefUnit[]
     * @@throws WEB3BaseException
     * @@roseuid 43E971E7020E
     */
    protected WEB3AdminToIfoOrderRefUnit[] createIfoOrderRefUnitList(
        IfoOrderUnit[] l_orderUnitList,
        WEB3AdminToIfoOrderRefRefRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " createIfoOrderRefUnitList(IfoOrderUnit[], WEB3AdminToIfoOrderRefRefRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnitList == null || l_request == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //1.1 ArrayList( )
        List l_lisOrderUnits = new ArrayList();
        
        //1.2 (*)�p�����[�^.�����P�ʈꗗ�v�f����Loop����
        int l_intLength = l_orderUnitList.length;
        for (int i = 0; i < l_intLength; i++)
        {
            //1.2.1 getOrderId( )
            long l_lngOrderId = l_orderUnitList[i].getOrderId();
            
            //1.2.2 getBranchId( )
            long l_lngBranchId = l_orderUnitList[i].getBranchId();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            WEB3GentradeFinObjectManager l_finObjectManager =
                (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow) l_orderUnitList[i].getDataSourceObject();

            //1.2.4 getAccountId( )
            long l_lngAccountId = l_orderUnitList[i].getAccountId();

            WEB3GentradeBranch l_branch = null;
            WEB3GentradeMainAccount l_mainAccount = null;
            WEB3GentradeMarket l_market = null;
            try
            {
                //1.2.3 getBranch(arg0 : long)
                l_branch = (WEB3GentradeBranch) l_accountManager.getBranch(l_lngBranchId);
                //1.2.5 getMainAccount(arg0 : long)
                l_mainAccount = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(l_lngAccountId);
                l_market = (WEB3GentradeMarket) l_finObjectManager.getMarket(l_orderUnitRow.getMarketId());

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
            
            //1.2.6 getOP��������^�C�v( )
            SubAccountTypeEnum l_subAccountTypeEnum = l_mainAccount.getOpSubAccountType();
            
            //1.2.7 get�\���ڋq�R�[�h( )
            String l_strDisplayAccountCode = l_mainAccount.getDisplayAccountCode();
            
            //1.2.8 getProduct( )
            IfoProduct l_product = (IfoProduct) l_orderUnitList[i].getProduct();

            //1.2.9 reset�����R�[�h()
            WEB3GentradeTradingTimeManagement.resetProductCode(l_product.getUnderlyingProductCode());

            //1.2.10 is�蓮�����\(IfoOrderUnit)
            TradingModule l_tradeModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl =
                (WEB3OptionOrderManagerImpl)l_tradeModule.getOrderManager();
            boolean l_blnisManualOrderPossible =
                l_optionOrderManagerImpl.isManualOrderPossible(l_orderUnitList[i]);

            //1.2.11 get���i�敪(IfoOrderUnit)
            String l_strProductDiv = WEB3IfoDataAdapter.getCommodityDiv(l_orderUnitList[i]);
            
            //1.2.12 get����敪(������� : OrderTypeEnum)
            String l_strTradingType = WEB3IfoDataAdapter.getTradingType(
                l_orderUnitList[i].getOrderType());
            
            //1.2.13 get������ԋ敪(�����P�� : OrderUnit)
            String l_strOrderState = WEB3IfoDataAdapter.getOrderStatusType(l_orderUnitList[i]);
            
            //1.2.14 get���s����(PR�w)(���s���� : IfoOrderExecutionConditionType)
            String l_strExecCond = WEB3IfoDataAdapter.getExecutionCondByPr(
                l_orderUnitList[i].getExecutionConditionType());
            
            //1.2.15 get����ԋ敪(�����P�� : OrderUnit)
            String l_strExecType = WEB3IfoDataAdapter.getExecStatusType(l_orderUnitList[i]);
            
            //1.2.16 isMarketOrder( )
            boolean l_blnIsMarketOrder = l_orderUnitList[i].isMarketOrder();

            //1.2.17 get�����󋵋敪(�����P�� : OrderUnit, ����������� : String)
            String l_strTriggerOrderState = WEB3IfoDataAdapter.getTriggerOrderStatusType(
                    l_orderUnitList[i], l_request.triggerOrderType);

            //1.2.18 get�v�w�l�p�L����ԋ敪(�����P�� : IfoOrderUnit)
            String l_strWLimitEnableStatusDiv =
                WEB3IfoDataAdapter.getWLimitEnableStatusDiv(l_orderUnitList[i]);

            //1.2.19 get�v�w�l�p�֑ؑO�����P��(�����P�� : IfoOrderUnit)
            String l_strWLimitBefSwitchPrice =
                WEB3IfoDataAdapter.getWLimitBefSwitchPrice(l_orderUnitList[i]);

            //1.2.20 get�v�w�l�p�֑ؑO���s����(�����P�� : IfoOrderUnit)
            String l_strWLimitBefSwitchExecCondType =
                WEB3IfoDataAdapter.getWLimitBefSwitchExecCondType(l_orderUnitList[i]);

            //1.2.21 get���[���G���W������̒ʒm(�����P�� : OrderUnit, ����������� : String,
            //       �����^�C�v : ProductTypeEnum)
            //  �����P�ʁF�@@�����P��
            //  ����������ʁF�@@�p�����[�^.���N�G�X�g�f�[�^.�����������
            //  �����^�C�v�F�@@ProductTypeEnum.�敨�I�v�V����
            RlsConOrderHitNotifyParams l_notifyParams =
                WEB3AdminToDataManager.getRlsConOrderHitNotify(
                    l_orderUnitList[i], l_request.triggerOrderType, ProductTypeEnum.IFO);

            //1.2.22 get�������s����()
            //[����]
            //�����P�ʁF�@@�����P��
            //����������ʁF�@@�p�����[�^.���N�G�X�g�f�[�^.�����������
            //�����^�C�v�F�@@ProductTypeEnum.�敨�I�v�V����
            //���s�敪�F�@@�p�����[�^.���N�G�X�g�f�[�^.���ݒl�ƍ��敪(*)
            //(*)���ݒl�ƍ��敪��null�A���A
            //�@@���ݒl�ƍ��敪��"�S�ẴG���["�̏ꍇ�̂ݐݒ�B
            String l_strMissType = null;
            String l_strTickMatchDiv = l_request.tickMatchDiv;
            if (l_strTickMatchDiv != null
                && !WEB3AdminToTickMatchDivDef.ALL_ERROR.equals(l_strTickMatchDiv))
            {
                l_strMissType = l_strTickMatchDiv;
            }

            RlsOrderMissParams l_rlsOrderMissParams = 
                WEB3AdminToDataManager.getRlsOrderMiss(
                    l_orderUnitList[i], 
                    l_request.triggerOrderType, 
                    ProductTypeEnum.IFO, 
                    l_strMissType);
            
            //1.2.23 �g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�Unit( )
            //�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�Unit�C���X�^���X�𐶐�����B
            WEB3AdminToIfoOrderRefUnit l_execRefUnit = new WEB3AdminToIfoOrderRefUnit();
            
            //1.2.24 (*)�v���p�e�B�Z�b�g
            //(*)�g���K�[�����Ǘ��ҁE�敨OP�����Ɖ�Unit�C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B
            //---------- ���i���ʃv���p�e�B --------------------
            //ID      �� �����P��.����ID
            l_execRefUnit.id = String.valueOf(l_lngOrderId);
            
            //�����������  �� �p�����[�^.�����������
            l_execRefUnit.triggerOrderType = l_request.triggerOrderType;

            //�����������Z�q �� �敨OP�f�[�^�A�_�v�^.get�����������Z�q�i�����P�ʁj�̖߂�l
            l_execRefUnit.condOperator = WEB3IfoDataAdapter.getOrderCondOperator(
        		l_orderUnitList[i]);
            
            //���������P��  ��  �敨OP�f�[�^�A�_�v�^.get�t�w�l��l�i�����P�ʁj�̖߂�l
            l_execRefUnit.orderCondPrice = WEB3IfoDataAdapter.getStopOrderPrice(
        		l_orderUnitList[i]);
            
            //���������P���敪 �� �敨OP�f�[�^�A�_�v�^.get����������l�^�C�v�i�����P�ʁj�̖߂�l    
            l_execRefUnit.orderCondPriceDiv = WEB3IfoDataAdapter.getStopPriceType(
        		l_orderUnitList[i]);

            //W�w�l�p�����P���敪�@@   �� �敨OP�f�[�^�A�_�v�^.getW�w�l�p�����P���敪�i�����P�ʁj�̖߂�l
            l_execRefUnit.wLimitOrderPriceDiv = WEB3IfoDataAdapter.getWLimitOrderPriceDiv(
                l_orderUnitList[i]);

            //W�w�l�p�����P��      �� (*1)�敨OP�f�[�^�A�_�v�^.getW�w�l�p�����P���i�����P�ʁj�̖߂�l
            if (WEB3OrderPriceDivDef.LIMIT_PRICE.equals(l_execRefUnit.wLimitOrderPriceDiv))
            {
                l_execRefUnit.wLimitPrice = WEB3IfoDataAdapter.getWLimitOrderPrice(
                    l_orderUnitList[i]);
            }

            //W�w�l�p���s����      �� �敨OP�f�[�^�A�_�v�^.getW�w�l�p���s����(�����P��)�̖߂�l
            l_execRefUnit.wlimitExecCondType = WEB3IfoDataAdapter.getWLimitExecCondType(
                l_orderUnitList[i]);

            //W�w�l�p�����L����ԋ敪  �� �敨OP�f�[�^�A�_�v�^.getW�w�l�p�����L����ԋ敪()�̖߂�l
            l_execRefUnit.wlimitEnableStatusDiv = l_strWLimitEnableStatusDiv;

            //W�w�l�p�֑ؑO�����P��   �� �敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO�����P��()�̖߂�l
            l_execRefUnit.wlimitBefChgLimitPrice = l_strWLimitBefSwitchPrice;

            //W�w�l�p�֑ؑO���s����   �� �敨OP�f�[�^�A�_�v�^.getW�w�l�p�֑ؑO���s����()�̖߂�l
            l_execRefUnit.wlimitBefChgExecCondType = l_strWLimitBefSwitchExecCondType;

            //���X�R�[�h       �� getBranch()�̖߂�l.���X�R�[�h
            l_execRefUnit.branchCode = l_branch.getBranchCode();
            
            //�ڋq�R�[�h       �� getMainAccount()�̖߂�l.get�\���ڋq�R�[�h()
            l_execRefUnit.accountCode = l_strDisplayAccountCode;
            
            //�s��R�[�h       �� �����P��.�s��ID�ɊY������s��.�s��R�[�h
            l_execRefUnit.marketCode = l_market.getMarketCode();
            
            //������     �� getProduct()�̖߂�l.������
            IfoProductRow l_productRow = (IfoProductRow) l_product.getDataSourceObject();
            l_execRefUnit.productName = l_productRow.getStandardName();
            
            //���i�敪        �� get���i�敪()�̖߂�l
            l_execRefUnit.productDiv = l_strProductDiv;
            
            //����敪        �� get����敪()�̖߂�l
            l_execRefUnit.tradingType = l_strTradingType;
            
            //���s����        �� get���s����(PR�w)()�̖߂�l
            l_execRefUnit.execCondType = l_strExecCond;
            
            //�����L������  �� �@@�@@�敨OP�f�[�^�A�_�v�^.get���������敪(�����P�ʁj
            //��"�o����܂Œ���"�̏ꍇ�̂݁A�����P��.�����������t���Z�b�g�B�ȊO�Anull���Z�b�g�B
            String l_strExpirationDateType = WEB3IfoDataAdapter.getExpirationDateType(l_orderUnitList[i]);
            if (WEB3OrderExpirationDateTypeDef.CARRIED_ORDER.equals(l_strExpirationDateType))
            {
                l_execRefUnit.expirationDate = l_orderUnitRow.getExpirationDate();
            }
            else
            {
                l_execRefUnit.expirationDate = null;
            }
            
            //��������        �� �����P��.��������
            l_execRefUnit.orderQuantity = WEB3StringTypeUtility.formatNumber(l_orderUnitList[i].getQuantity());
            
            //�����P���敪  �� isMarketOrder()�̖߂�l��true�̏ꍇ�A"���s"���Z�b�g�Bfalse�̏ꍇ�A"�w�l"���Z�b�g�B
            if (l_blnIsMarketOrder)
            {
                l_execRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_execRefUnit.orderPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;

                //�����P��        �� �����P���敪��"�w�l"�̏ꍇ�A�����P��.�w�l���Z�b�g�B
                l_execRefUnit.limitPrice = WEB3StringTypeUtility.formatNumber(l_orderUnitList[i].getLimitPrice());
            }
            
            //������ԋ敪  �� get������ԋ敪()�̖߂�l
            l_execRefUnit.orderState = l_strOrderState;
            
            //����ԋ敪  �� get����ԋ敪()�̖߂�l
            l_execRefUnit.execType = l_strExecType;
            
            //��������敪  �� �����P��.���������E����敪
            l_execRefUnit.changeCancelDiv = l_orderUnitRow.getModifyCancelType();
            
            //��������        �� �����P��.�󒍓���
            l_execRefUnit.orderDate = l_orderUnitRow.getReceivedDateTime();
            
            //������     �� �����P��.������
            l_execRefUnit.orderBizDate = WEB3DateUtility.getDate(l_orderUnitRow.getBizDate(), "yyyyMMdd");
            
            //��n��     �� �����P��.��n��
            l_execRefUnit.deliveryDate = l_orderUnitRow.getDeliveryDate();
            
            //�����󋵋敪  �� get�����󋵋敪()�̖߂�l
            l_execRefUnit.triggerOrderState = l_strTriggerOrderState;
            
            //(*2)get���[���G���W������̒ʒm()�̖߂�l��null�̏ꍇ�Z�b�g
            if (null != l_notifyParams)
            {
                //��������M����    �� (*2)���[���G���W������̒ʒmParams.tick�q�b�g�^�C���X�^���v
                l_execRefUnit.currentPriceInfoAcceptTime = l_notifyParams.getHitTickTimestamp();
                //�g���K�[�N������    �� (*2)���[���G���W������̒ʒmParams.���[���G���W���t�@@�C�A�^�C���X�^���v
                l_execRefUnit.triggerStartTime = l_notifyParams.getRlsHitTimestamp();
                //������������  �� (*2)���[���G���W������̒ʒmParams.���������^�C���X�^���v
                l_execRefUnit.orderCompleteTime = l_notifyParams.getOrderSubmitTimestamp();
            }

            //���ݒl�ƍ��敪       �� get�������s����()�̖߂�l��null�̏ꍇ�A"����"�B�ȊO�A�������s����Params.���s�敪
            if (l_rlsOrderMissParams == null)
            {
                l_execRefUnit.tickMatchDiv = WEB3AdminToTickMatchDivDef.NORMAL;
            }
            else
            {
                l_execRefUnit.tickMatchDiv = l_rlsOrderMissParams.getMissType();

                //���ݒl�\�z����       �� (*4)�������s����Params.tick�q�b�g�^�C���X�^���v
                l_execRefUnit.tickExpectTime = l_rlsOrderMissParams.getHitTickTimestamp();
            }

            //�蓮�����\�t���O   �� OP�����}�l�[�W��.is�蓮�����\()�̖߂�l
            l_execRefUnit.manualFlag = l_blnisManualOrderPossible;

            //�[��O�����J�z�Ώۃt���O �� �敨OP�f�[�^�A�_�v�^.get�[��O�J�z�Ώۃt���O�iPR�w�j(�����P��)
            l_execRefUnit.eveningSessionCarryoverFlag =
                WEB3IfoDataAdapter.getEveningSessionCarryOverFlagPr(l_orderUnitList[i]);

            //����敪 �� �����P��.����敪
            l_execRefUnit.sessionType = l_orderUnitRow.getSessionType();

            //---------- �敨�I�v�V���������v���p�e�B --------------------
            //�����敪     �� getOP��������^�C�v()�̖߂�l��
            //�@@�@@         SubAccountTypeEnum.������������̏ꍇ�F"�I�v�V������������"���Z�b�g
            //�@@�@@         SubAccountTypeEnum.�����I�v�V�����������(�敨�؋���)�̏ꍇ�F"�敨�I�v�V��������"���Z�b�g
            if (SubAccountTypeEnum.EQUITY_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_execRefUnit.taxType = WEB3AdminToIfoTaxTypeDef.OPTION_BUY_TAX;
            }
            else if (SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT.equals(l_subAccountTypeEnum))
            {
                l_execRefUnit.taxType = WEB3AdminToIfoTaxTypeDef.FUTURE_OPTION_TAX;
            }
            
            //�w�����     �� getProduct()�̖߂�l.�����Y�����R�[�h
            l_execRefUnit.targetProductCode = l_productRow.getUnderlyingProductCode();

            //����       �� getProduct()�̖߂�l.����
            l_execRefUnit.delivaryMonth = l_productRow.getMonthOfDelivery();
            
            //(*3)�����P��.�敨�^�I�v�V�����敪��"�I�v�V����"�̏ꍇ�Z�b�g
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_orderUnitRow.getFutureOptionDiv()))
            {
                //�s�g���i     �� (*3)getProduct()�̖߂�l.�s�g���i       
                l_execRefUnit.strikePrice = WEB3StringTypeUtility.formatNumber(l_productRow.getStrikePrice());

                //�I�v�V�������i�敪    �� (*3)getProduct()�̖߂�l.�敨�I�v�V�������i�敪��
                //"�R�[���I�v�V����"�̏ꍇ�F"�R�[���I�v�V����"�A
                //"�v�b�g�I�v�V����"�̏ꍇ�F"�v�b�g�I�v�V����"
                if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_execRefUnit.opProductType = WEB3IfoProductTypeDef.CALL_OPTIONS;
                }
                else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_productRow.getDerivativeType()))
                {
                    l_execRefUnit.opProductType = WEB3IfoProductTypeDef.PUT_OPTIONS;
                }
            }
            
            //1.2.19 add(arg0 : Object)
            l_lisOrderUnits.add(l_execRefUnit);
        }
        
        //1.3 toArray( )
        WEB3AdminToIfoOrderRefUnit[] l_refUnits = new WEB3AdminToIfoOrderRefUnit[l_lisOrderUnits.size()];
        l_lisOrderUnits.toArray(l_refUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_refUnits;
    }
    
    /**
     * (get�戵�\����������ʈꗗ�iPR�w�j)<BR>
     * �Y����ЁE���X�E���i�Ŏ戵�\�ȏ����������擾���A<BR>
     * PR�w�Ŏg�p�������������ʂ̈ꗗ��ԋp����B<BR>
     * �戵�\�ȏ������������݂��Ȃ��ꍇ��null��ԋp����B<BR>
     * <BR>
     * �P�j�@@�戵�\���������擾<BR>
     * <BR>
     * �@@�Y�����i�̎戵�\���������I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �@@[����]<BR>
     * �@@�@@�،���ЃR�[�h�F�@@�p�����[�^.�،���ЃR�[�h<BR>
     * �@@�@@�敨�^�I�v�V�����敪�F�@@<BR>
     * �@@�@@�@@- �p�����[�^.���i�敪��"�I�v�V����"�̏ꍇ�A"�I�v�V����"<BR>
     * �@@�@@�@@- �p�����[�^.���i�敪��"�敨"�̏ꍇ�A"�敨"<BR>
     * �@@�@@�����^�C�v�F�@@ProductTypeEnum.�敨�I�v�V����<BR>
     * �@@�@@�M�p����敪�F�@@"DEFUALT"<BR>
     * <BR>
     * �Q�j�@@�戵�\��������Row�̎擾<BR>
     * <BR>
     * �@@�P�j�Ŏ擾�����戵��������.getDataSourceObject()���R�[������B<BR>
     * <BR>
     * �R�j�@@ArrayList���쐬����B<BR>
     * <BR>
     * �S�j�@@�戵��������Row.�i���������j�t�w�l��"�戵�\"�̏ꍇ�A<BR>
     * �@@�@@�@@"�t�w�l"��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �T�j�@@�戵��������Row.�i���������jW�w�l��"�戵�\"�̏ꍇ�A<BR>
     * �@@�@@�@@"W�w�l"��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �U�j�@@�戵��������Row.�A��������"�戵�\"�̏ꍇ�A<BR>
     * �@@�@@�@@"�A������"��ArrayList�ɒǉ�����B<BR>
     * <BR>
     * �V�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * �@@�@@�@@��toArray()�̖߂�l.length��0�̏ꍇ�Anull��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@param l_strProductType - (���i�敪)<BR>
     * ���i�敪<BR> 
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 43E7FC1A00DF
     */
    protected String[] getValidTriggerOrderTypeListByPr(
        String l_strInstitutionCode,
        String l_strProductType) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getValidTriggerOrderTypeListByPr(String, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�戵�\���������擾
        String l_strFutureOptionDiv = null;
        ProductTypeEnum l_productType = null;
        
        //�p�����[�^.���i�敪��"�I�v�V����"�̏ꍇ�A"�I�v�V����"
        //�p�����[�^.���i�敪��"�敨"�̏ꍇ�A"�敨"
        if (WEB3CommodityDivDef.OPTION.equals(l_strProductType))
        {
            l_strFutureOptionDiv = WEB3FuturesOptionDivDef.OPTION;
        }
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strProductType))
        {
            l_strFutureOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
        }

        //�����^�C�v�F�@@ProductTypeEnum.�敨�I�v�V����
        l_productType = ProductTypeEnum.IFO;

        //�Y�����i�̎戵�\���������I�u�W�F�N�g���擾����B
        WEB3GentradeHandlingOrderCond l_handlingOrderCond = 
            new WEB3GentradeHandlingOrderCond(
                l_strInstitutionCode,
                l_productType,
                l_strFutureOptionDiv,
                WEB3MarginTradingDivDef.DEFAULT);
        
        //�Q�j�@@�戵�\��������Row�̎擾
        //  �P�j�Ŏ擾�����戵��������.getDataSourceObject()���R�[������B
        EnableOrderConditionRow l_condRow = (EnableOrderConditionRow) l_handlingOrderCond.getDataSourceObject();
        
        //�R�j�@@ArrayList���쐬����B
        List l_lisOrderConds = new ArrayList();
        
        //�S�j�@@�戵��������Row.�i���������j�t�w�l��"�戵�\"�̏ꍇ�A"�t�w�l"��ArrayList�ɒǉ�����B
        if (WEB3DealtDef.CAN_DEALT.equals(l_condRow.getStopOrder()))
        {
            l_lisOrderConds.add(WEB3TriggerOrderTypeDef.STOP);
        }
        
        //�T�j�@@�戵��������Row.�i���������jW�w�l��"�戵�\"�̏ꍇ�A"W�w�l"��ArrayList�ɒǉ�����B
        if (WEB3DealtDef.CAN_DEALT.equals(l_condRow.getWLimitOrder()))
        {
            l_lisOrderConds.add(WEB3TriggerOrderTypeDef.W_LlIMIT);
        }
        
        //�U�j�@@�戵��������Row.�A��������"�戵�\"�̏ꍇ�A"�A������"��ArrayList�ɒǉ�����B
        if (WEB3DealtDef.CAN_DEALT.equals(l_condRow.getChainOrder()))
        {
            l_lisOrderConds.add(WEB3TriggerOrderTypeDef.SUCC);
        }
        
        //�V�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
        String[] l_strOrderConds = new String[l_lisOrderConds.size()];
        l_lisOrderConds.toArray(l_strOrderConds);
        
        //��toArray()�̖߂�l.length��0�̏ꍇ�Anull��ԋp����B
        if (0 == l_strOrderConds.length)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strOrderConds;
    }

    
    /**
     * (AdmintoIfoOrderUnitRow ���@@IfoOrderUnitParams �ɓ]������)<BR>
     * AdmintoIfoOrderUnitRow ��  IfoOrderUnitParams �ɓ]������<BR>   
     * <BR>
     * @@param p_row - (AdmintoIfoOrderUnitRow)<BR>
     * @@return IfoOrderUnitParams
     */    
    private IfoOrderUnitParams toIfoOrderUnitParams( AdmintoIfoOrderUnitRow p_row )
    {
        IfoOrderUnitParams l_params = new IfoOrderUnitParams();
        l_params.setOrderUnitId(p_row.getOrderUnitId());
        l_params.setAccountId(p_row.getAccountId());
        l_params.setSubAccountId(p_row.getSubAccountId());
        l_params.setBranchId(p_row.getBranchId());
        if ( !p_row.getTraderIdIsNull() )
            l_params.setTraderId(new Long( p_row.getTraderId() ));    
        l_params.setOrderId(p_row.getOrderId());
        l_params.setOrderType(p_row.getOrderType());
        l_params.setOrderCateg(p_row.getOrderCateg());
        l_params.setLastOrderActionSerialNo(p_row.getLastOrderActionSerialNo());
        l_params.setLastExecutionSerialNo(p_row.getLastExecutionSerialNo());
        l_params.setProductType(p_row.getProductType());
        l_params.setFutureOptionDiv(p_row.getFutureOptionDiv());
        if ( !p_row.getMarketIdIsNull() )
            l_params.setMarketId(new Long( p_row.getMarketId() ));
        l_params.setQuantity(p_row.getQuantity());
        if ( !p_row.getLimitPriceIsNull() )
            l_params.setLimitPrice(new Double( p_row.getLimitPrice() ));
        l_params.setExecutionConditionType(p_row.getExecutionConditionType());
        l_params.setOrderConditionType(p_row.getOrderConditionType());
        l_params.setOrderCondOperator(p_row.getOrderCondOperator());
        l_params.setStopPriceType(p_row.getStopPriceType());
        if ( !p_row.getStopOrderPriceIsNull() )
            l_params.setStopOrderPrice(new Double( p_row.getStopOrderPrice() ));
        if ( !p_row.getWLimitPriceIsNull() )
        	l_params.setWLimitPrice(new Double( p_row.getWLimitPrice() ));
        l_params.setDeliveryDate(p_row.getDeliveryDate());
        l_params.setExpirationDate(p_row.getExpirationDate());
        if ( !p_row.getConfirmedQuantityIsNull() )
        	l_params.setConfirmedQuantity(new Double( p_row.getConfirmedQuantity() ));
        if ( !p_row.getConfirmedPriceIsNull() )
        	l_params.setConfirmedPrice(new Double( p_row.getConfirmedPrice() ));
        if ( !p_row.getExecutedQuantityIsNull() )
        	l_params.setExecutedQuantity(new Double( p_row.getExecutedQuantity() ));
        if ( !p_row.getExecutedAmountIsNull() )
        	l_params.setExecutedAmount(new Double( p_row.getExecutedAmount() ));
        l_params.setOrderStatus(p_row.getOrderStatus());
        l_params.setOrderOpenStatus(p_row.getOrderOpenStatus());
        l_params.setExpirationStatus(p_row.getExpirationStatus());
        l_params.setTaxType(p_row.getTaxType());
        l_params.setBizDate(p_row.getBizDate());
        l_params.setProductId(p_row.getProductId());
        l_params.setOrderChanel(p_row.getOrderChanel());
        l_params.setReceivedDateTime(p_row.getReceivedDateTime());
        l_params.setVoucherNo(p_row.getVoucherNo());
        l_params.setCommTblNo(p_row.getCommTblNo());
        l_params.setCommTblSubNo(p_row.getCommTblSubNo());
        l_params.setSonarTradedCode(p_row.getSonarTraderCode());
        if ( !p_row.getPriceIsNull() )
        	l_params.setPrice(new Double( p_row.getPrice() ));
        l_params.setOrderRequestNumber(p_row.getOrderRequestNumber());
        if ( !p_row.getEstimatedPriceIsNull() )
            l_params.setEstimatedPrice(new Double( p_row.getEstimatedPrice() ));
        l_params.setSonarTradedCode(p_row.getSonarTradedCode());
        l_params.setSonarMarketCode(p_row.getSonarMarketCode());
        l_params.setCommProductCode(p_row.getCommProductCode());
        l_params.setModifyCancelType(p_row.getModifyCancelType());
        l_params.setOrderRootDiv(p_row.getOrderRootDiv());
        if ( !p_row.getConfirmedOrderPriceIsNull() )
        	l_params.setConfirmedOrderPrice(new Double( p_row.getConfirmedOrderPrice() ));
        if ( !p_row.getConfirmedEstimatedPriceIsNull() )
        	l_params.setConfirmedEstimatedPrice(new Double( p_row.getConfirmedEstimatedPrice() ));
        l_params.setConfirmedExecConditionType(p_row.getConfirmedExecConditionType());
        l_params.setClosingOrder( p_row.getClosingOrder());
        l_params.setErrorReasonCode(p_row.getErrorReasonCode());
        l_params.setRequestType(p_row.getRequestType());
        if ( !p_row.getFirstOrderUnitIdIsNull() )
            l_params.setFirstOrderUnitId(new Long( p_row.getFirstOrderUnitId() ));
        l_params.setCreatedTimestamp(p_row.getCreatedTimestamp());
        l_params.setLastUpdatedTimestamp(p_row.getLastUpdatedTimestamp());
        l_params.setOrgOrderConditionType(p_row.getOrgOrderConditionType());
        l_params.setOrgOrderCondOperator(p_row.getOrgOrderCondOperator());
        l_params.setOrgStopPriceType(p_row.getOrgStopPriceType());
        if ( !p_row.getOrgStopOrderPriceIsNull() )
        	  l_params.setOrgStopOrderPrice(new Double( p_row.getOrgStopOrderPrice() ));
        if ( !p_row.getOrgWLimitPriceIsNull() )
            l_params.setOrgWLimitPrice(new Double( p_row.getOrgWLimitPrice() ));
        l_params.setOrgWLimitExecCondType( p_row.getOrgWLimitExecCondType());
        	  l_params.setWLimitExecCondType( p_row.getWLimitExecCondType());
        if ( !p_row.getWLimitBeforeLimitPriceIsNull() )
         	  l_params.setWLimitBeforeLimitPrice(new Double( p_row.getWLimitBeforeLimitPrice() ));
        l_params.setWLimitBeforeExecCondType( p_row.getWLimitBeforeExecCondType());
        l_params.setSubmitOrderDelayFlag(p_row.getSubmitOrderDelayFlag());
        l_params.setDayTradeType(p_row.getDayTradeType());
        l_params.setEveningSessionCarryoverFlag(p_row.getEveningSessionCarryoverFlag());
        l_params.setSessionType(p_row.getSessionType());
        return l_params;
      
      
    }

}
@
