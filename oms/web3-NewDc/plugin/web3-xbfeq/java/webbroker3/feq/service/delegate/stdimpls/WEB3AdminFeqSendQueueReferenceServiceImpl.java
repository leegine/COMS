head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.39.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqSendQueueReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�XImpl (WEB3AdminFeqSendQueueReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 ꎉ� (���u) �V�K�쐬    
Revesion History : 2006/11/20 ����� (���u) ���f�� No.303
Revesion History : 2006/12/11 ꎉ�   (���u) ���f�� No.310
Revesion History : 2007/01/15 ����� (���u) ���f�� No.331
Revesion History : 2007/02/02 ������ (���u) ���f�� No.340
Revesion History : 2007/02/07 ������ (���u) ���f�� No.343
Revesion History : 2007/02/25 ꎉ�   (���u) ���f�� No.346
Revesion History : 2008/02/02 ���g   (���u) ���f�� No.391,No.392,No.394
Revesion History : 2009/08/03 ���g   (���u) ���f�� No.503
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqOrderManager;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceInputResponse;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceRequest;
import webbroker3.feq.message.WEB3AdminFeqSendQueueReferenceResponse;
import webbroker3.feq.message.WEB3FeqSendQueueInfo;
import webbroker3.feq.message.WEB3ForeignSortKey;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.slebase.data.SleSendQParams;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.slebase.enums.SleSendqProcStatusEnum;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�XImpl )<BR>
 * �Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X�����N���X<BR>
 *   
 * @@author ꎉ�
 * @@version 1.0
 */
public class WEB3AdminFeqSendQueueReferenceServiceImpl implements WEB3AdminFeqSendQueueReferenceService
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqSendQueueReferenceServiceImpl.class);  
    
    /** ���t�t�H�[�}�b�g�̒萔��`:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * �Ǘ��ҊO������SEND�L���[�Ɖ�������{����B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɑΉ����郁�\�b�h���R�[������B<BR>
     * <BR>
     * �|get���͉��()<BR>
     * �|get�ꗗ���()<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 429FEB91032D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("���N�G�X�g�����w��(null)�ł��B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���N�G�X�g�����w��(null)�ł��B");
        }
        
        WEB3GenResponse l_response;
        
        //get���͉��
        if (l_request instanceof WEB3AdminFeqSendQueueReferenceInputRequest)
        {
            l_response = 
                this.getInputScreen(
                    (WEB3AdminFeqSendQueueReferenceInputRequest)l_request);   
        }
        
        //get�ꗗ���
        else if (l_request instanceof WEB3AdminFeqSendQueueReferenceRequest)
        {
            l_response =
                this.getListScreen(
                    (WEB3AdminFeqSendQueueReferenceRequest)l_request);
        }
        else
        {
            log.debug("�p�����[�^�^�C�v�s���B");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get���͉��)<BR>
     * �Ǘ��ҊO������SEND�L���[�Ɖ���͉�ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X)get���͉�ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqSendQueueReferenceInputResponse getInputScreen(
    	WEB3AdminFeqSendQueueReferenceInputRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AdminFeqSendQueueReferenceInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 getInstanceFrom���O�C�����( )
        //���O�C�������Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        
        //1.2 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException

        //get�s��A���O���s��(�،���ЃR�[�h : String)
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        WEB3GentradeFinObjectManager l_finObjectMgr =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        Market[] l_markets = l_finObjectMgr.getLinkFeqMarkets(l_admin.getInstitutionCode());
        //1.3 createResponse( )
        WEB3AdminFeqSendQueueReferenceInputResponse l_response = 
            (WEB3AdminFeqSendQueueReferenceInputResponse)l_request.createResponse();
        
        //1.4 �v���p�e�B�Z�b�g
        List l_lisTransactionDiv = new ArrayList();
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.TODO.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.PROCESSED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.BAT_PROCED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.PREPARE_PROCESSED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.NOT_PROCESSED.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.SKIP_PROCESSING_LOCAL.intValue() + "");
        l_lisTransactionDiv.add(SleSendqProcStatusEnum.SKIP_PROCESSING_ERROR.intValue() + "");
        
        l_response.transactionDivList = new String[l_lisTransactionDiv.size()];
        
        l_lisTransactionDiv.toArray(l_response.transactionDivList);
        
        List l_lisBizDateList = new ArrayList();
        //�Ɩ����t
        Date l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
        //�O���c�Ɠ�
        Date l_datpreBizDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(-1);
        //�O�X���c�Ɠ�
        Date l_datpreBizDate2 = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(-2);
        //�O�X�X���c�Ɠ�
        Date l_datpreBizDate3 = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(-3);
        //���c�Ɠ�
        Date l_datNextBizDate = new WEB3GentradeBizDate(
            new Timestamp(l_datBizDate.getTime())).roll(1);

        l_lisBizDateList.add(l_datpreBizDate3);
        l_lisBizDateList.add(l_datpreBizDate2);
        l_lisBizDateList.add(l_datpreBizDate);
        l_lisBizDateList.add(l_datBizDate);
        l_lisBizDateList.add(l_datNextBizDate);

        l_response.orderDateList = new Date[l_lisBizDateList.size()];
        
        l_lisBizDateList.toArray(l_response.orderDateList);

        //�s��R�[�h�ꗗ:get�s��A���O���s��̖߂�l.getMarketCode()
        String[] l_strMarkerCodes = new String[l_markets.length];
        for (int i = 0; i < l_strMarkerCodes.length; i++)
        {
            l_strMarkerCodes[i] = l_markets[i].getMarketCode();
        }
        l_response.marketList = l_strMarkerCodes;
        //return ���X�|���X�f�[�^
	    return l_response;
    }
    
    /**
     * (get�ꗗ���)<BR>
     * �Ǘ��ҊO������SEND�L���[�Ɖ�ꗗ��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�Ǘ��ҊO������SEND�L���[�Ɖ�T�[�r�X)get�ꗗ��ʁv�Q�� <BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * ���N�G�X�g�f�[�^<BR>
     * 
     * @@return WEB3AdminFeqSendQueueReferenceResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected WEB3AdminFeqSendQueueReferenceResponse getListScreen(
    	WEB3AdminFeqSendQueueReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminFeqSendQueueReferenceResponse)";
        log.entering(STR_METHOD_NAME);

        List l_lisSendQueueRefList = null;
        String l_lngProductCode = "";
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFrom���O�C�����()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        
        //1.3 validate����(�@@�\�J�e�S���R�[�h : String, is�X�V : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_ORDER_EXEC_MANAGE, false);//WEB3BaseException
        
        //1.4 get�،���ЃR�[�h
        String l_strInstitutionCode = l_admin.getInstitutionCode();

        //get�^�p�R�[�h(�،���ЃR�[�h : String, �^�p�R�[�h : String)
        //�،���ЃR�[�h�F get�،���ЃR�[�h�i�j�̖߂�l
        //�^�p�R�[�h�F���N�G�X�g�f�[�^.�^�p�R�[�h
        WEB3FeqOrderEmpCodeGettingService l_feqOrderEmpCodeGettingService =
            (WEB3FeqOrderEmpCodeGettingService)Services.getService(
                WEB3FeqOrderEmpCodeGettingService.class);
        String l_strEmpCode =
            l_feqOrderEmpCodeGettingService.getEmpCode(l_strInstitutionCode, l_request.managementCode);

        //1.5 create��������������
		//�����敪�F ���N�G�X�g.�����敪 
		//�^�p�R�[�h�F ���N�G�X�g.�^�p�R�[�h 
		//���X�R�[�h�F ���N�G�X�g.���X�R�[�h 
		//�ڋq�R�[�h�F ���N�G�X�g.�ڋq�R�[�h 
        //���[�����M�����t���O�F ���N�G�X�g.���[�����M�����t���O
        //�s��R�[�h�F���N�G�X�g.�s��R�[�h
        String l_strQueryString = this.createQueryString(
        	l_request.transactionDiv,
        	l_request.managementCode,
        	l_request.branchCode,
        	l_request.accountCode,
        	l_request.sendMailDateFlag,
            l_request.marketCode);
        
        //1.6 create���������f�[�^�R���e�i
		// �،���ЃR�[�h�F �擾�����،���ЃR�[�h 
		// �����敪�F ���N�G�X�g.�����敪 
		// �^�p�R�[�h�F get�^�p�R�[�h�i�j�̖߂�l 
        // ������: ���N�G�X�g.������
		// ���X�R�[�h�F ���N�G�X�g.���X�R�[�h 
		// �ڋq�R�[�h�F ���N�G�X�g.�ڋq�R�[�h
        //�s��R�[�h�F���N�G�X�g.�s��R�[�h
        Object[] l_objDataContainer = this.createQueryDataContainer(
        	l_strInstitutionCode,
        	l_request.transactionDiv,
            l_strEmpCode,
            l_request.orderDate,
        	l_request.branchCode,
        	l_request.accountCode,
            l_request.marketCode);
        
        //1.7 create�\�[�g����
        //�\�[�g�L�[�F ���N�G�X�g.�\�[�g�L�[
        String l_strSortKey = this.createSortCond(l_request.sortKeys);
        
        //1.8 doFindAllQuery	
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
	        l_lisSendQueueRefList = l_queryProcessor.doFindAllQuery(
	        	SleSendQRow.TYPE, 
	        	l_strQueryString,
	            l_strSortKey,
	            null,
	            l_objDataContainer);
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
        
        // 1.9 createResponse
        WEB3AdminFeqSendQueueReferenceResponse l_response = 
            (WEB3AdminFeqSendQueueReferenceResponse)l_request.createResponse();

        // 1.10���R�[�h���擾�ł����ꍇ
        if (l_lisSendQueueRefList != null && !l_lisSendQueueRefList.isEmpty())
        {
        	//1.10.1 WEB3PageIndexInfo
            WEB3PageIndexInfo l_pageInfo = new WEB3PageIndexInfo(
            	l_lisSendQueueRefList, 
                Integer.parseInt(l_request.pageIndex), 
                Integer.parseInt(l_request.pageSize));
            
            //1.10.2 getArrayReturned( )            
            SleSendQParams[] l_sleSendQParams = 
                (SleSendQParams[])l_pageInfo.getArrayReturned(SleSendQParams.class);
            
            List l_lisSendQueueInfo = new ArrayList();
            
            //1.10.3 �擾�����\���Ώۂ�(�O�������)SEND_Q�e�[�u��Params�̔z��̌������A�������J��Ԃ�
            int l_intCnt = 0;
            if (l_sleSendQParams != null && l_sleSendQParams.length > 0)
            {
                l_intCnt = l_sleSendQParams.length;
            }    
        	for (int i = 0; i < l_intCnt; i++)
            {
                //1.10.3.1 �O������SEND�L���[���
        		WEB3FeqSendQueueInfo l_sendQueueInfo = new WEB3FeqSendQueueInfo();
                
                //1.10.3.2 
                //�ڋq�R�[�h
                if (l_sleSendQParams[i].getAccountCodeIsSet())
                {
                    l_sendQueueInfo.accountCode = l_sleSendQParams[i].getAccountCode().substring(0, 6);
                }
                
                //�����O�w�l
                if (!l_sleSendQParams[i].getChangeLimitPriceIsNull())
                {
                    l_sendQueueInfo.beforeOrderPrice = 
                    	WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getChangeLimitPrice());
                }
                
                //�����O����
                if (!l_sleSendQParams[i].getChangeQuantityIsNull())
                {
                    l_sendQueueInfo.beforeOrderQuantity = 
                    	WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getChangeQuantity());
                }
                
                //���X�R�[�h
                if (l_sleSendQParams[i].getBranchCodeIsSet())
                {
                    l_sendQueueInfo.branchCode = l_sleSendQParams[i].getBranchCode();
                }
                
                //�쐬���t
                l_sendQueueInfo.createTimeStamp = l_sleSendQParams[i].getCreatedTimestamp();
                
                //�^�p�R�[�h
                if (l_sleSendQParams[i].getOrderEmpCodeIsSet())
                {
                	l_sendQueueInfo.managementCode = l_sleSendQParams[i].getOrderEmpCode();
                }
                
                //�I�y���[�^�^�C�v
                if (l_sleSendQParams[i].getOpTypeIsSet())
                {

                    l_sendQueueInfo.operatorType = l_sleSendQParams[i].getOpType().intValue() + "";
                }
                
                //������
                if (l_sleSendQParams[i].getBizDateIsSet())
                {
                	l_sendQueueInfo.orderBizDate = l_sleSendQParams[i].getBizDate();
                }
                
                //�w�l
                if (!l_sleSendQParams[i].getLimitPriceIsNull())
                {
                	l_sendQueueInfo.orderPrice = 
                		WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getLimitPrice());
                }
                
                //��������
                if (!l_sleSendQParams[i].getQuantityIsNull())
                {
                	l_sendQueueInfo.orderQuantity = 
                		WEB3StringTypeUtility.formatNumber(l_sleSendQParams[i].getQuantity());
                }
                
                //�������
                if (l_sleSendQParams[i].getOrderTypeIsSet())
                {
                	l_sendQueueInfo.orderType = l_sleSendQParams[i].getOrderType().intValue() + "";
                }

                //����ID
                if (!l_sleSendQParams[i].getOrderIdIsNull())
                {
                	l_sendQueueInfo.orderId = l_sleSendQParams[i].getOrderId() + "";
                }

                //�����R�[�h
                long l_lngOrderUnitId = l_sleSendQParams[i].getOrderUnitId(); 

                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
                TradingModule l_tradingModule =
                    l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

                WEB3FeqOrderManager l_feqOrderManager =
                    (WEB3FeqOrderManager)l_tradingModule.getOrderManager();

                try
                {
                    WEB3FeqOrderUnit l_orderUnit =
                        (WEB3FeqOrderUnit)l_feqOrderManager.getOrderUnit(l_lngOrderUnitId);
                    WEB3FeqProduct l_product = (WEB3FeqProduct)l_orderUnit.getProduct();
                    l_lngProductCode = l_product.getProductCode();
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                l_sendQueueInfo.productCode = l_lngProductCode;
                
                //�L���[ID
                if (l_sleSendQParams[i].getQueueIdIsSet())
                {
                	l_sendQueueInfo.queueId = l_sleSendQParams[i].getQueueId() + "";
                }
                
                //���[�����M����
                l_sendQueueInfo.sendMailDate = l_sleSendQParams[i].getSendProcessDateTime();

                //�����敪
                if (l_sleSendQParams[i].getStatusIsSet())
                {
                	l_sendQueueInfo.transactionDiv = l_sleSendQParams[i].getStatus().intValue() + "";
                }
                               
                //�X�V���t
                if (l_sleSendQParams[i].getLastUpdatedTimestampIsSet())
                {
                	l_sendQueueInfo.updateTimeStamp = 
                		l_sleSendQParams[i].getLastUpdatedTimestamp();
                }

                //���ʃR�[�h
                l_sendQueueInfo.requestNumber = l_sleSendQParams[i].getOrderRequestNumber();

                //�s��R�[�h
                l_sendQueueInfo.marketCode = l_sleSendQParams[i].getMarketCode();

                l_lisSendQueueInfo.add(l_sendQueueInfo);
            }
            WEB3FeqSendQueueInfo[] l_sendQueueInfos = new WEB3FeqSendQueueInfo[l_lisSendQueueInfo.size()];
            l_lisSendQueueInfo.toArray(l_sendQueueInfos);
            
            //1.10.4:getTotalPages( )
            int l_intTotalPages = l_pageInfo.getTotalPages();
            
            //1.10.5:getTotalRecords( )
            int l_intTotalRecords = l_pageInfo.getTotalRecords();
            
            //1.10.6:getPageIndex( )
            int l_intPageIndex = l_pageInfo.getPageIndex();
        	
            l_response.feqSendQueueInfoList = l_sendQueueInfos;
            l_response.totalPages = l_intTotalPages + "";
            l_response.totalRecords = l_intTotalRecords + "";
            l_response.pageIndex = l_intPageIndex + "";
        }
        //1.11���R�[�h���擾�ł��Ȃ������ꍇ
        else
        {
        	l_response.feqSendQueueInfoList = null;
            l_response.totalPages = "0";
            l_response.totalRecords = "0";
            l_response.pageIndex = "0";
        }
        log.exiting(STR_METHOD_NAME);
	    return l_response;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬����B<BR>
     * <BR>
     * �P�j�\�[�g����������(�FString)���쐬����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�Q�|�P�j�\�[�g�L�[.�L�[���ڂ�Ή�����񕨗����ɕϊ����A<BR>
     * �@@�@@�쐬�����\�[�g����������ɒǉ�����B <BR>
     * <BR>
     * �@@�E�u�^�p�R�[�h�v�@@���@@(�O�������)SEND_Q�e�[�u��.�^�p�R�[�h <BR>
     * �@@�E�u���X�R�[�h�v�@@���@@(�O�������)SEND_Q�e�[�u��.���X�R�[�h<BR>
     * �@@�E�u�ڋq�R�[�h�v�@@���@@substr((�O�������)SEND_Q�e�[�u��.�A�J�E���gID, 9, 6) <BR>
     * �@@�E�u�����敪�v�@@���@@(�O�������)SEND_Q�e�[�u��.�����敪<BR>
     *�@@ �E�u�쐬���t�v�@@���@@(�O�������)SEND_Q�e�[�u��.�쐬���t<BR>
     * �@@�E�u�X�V���t�v�@@���@@(�O�������)SEND_Q�e�[�u��.�X�V���t<BR>
     * <BR>
     * �@@�Q�|�Q�j�\�[�g�L�[.�����^�~���ɑΉ�����擾����<BR>
     * �@@�@@(asc or desc)���\�[�g����������ɒǉ�����B<BR>
     * <BR>
     * �R�j�\�[�g���������ɁA(�O�������)SEND_Q�e�[�u��.�X�V���t�������w��ŕt��<BR>
     * �@@���L�[���ڂɁu�X�V���t�v���w�肳��Ă���ꍇ�͕t�����Ȃ��B<BR>
     * <BR>
     * �S�j�쐬�����\�[�g�����������ԋp����B�@@�@@
     * @@param l_sortKeys - (�\�[�g�L�[)
     * �\�[�g�L�[
     * 
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42BBB6760108
     */
    protected String createSortCond(WEB3ForeignSortKey[] l_sortKeys) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createSortCond(" +
        "WEB3ForeignSortKey[])";
        log.entering(STR_METHOD_NAME);
        
        StringBuffer l_strSortKeys = new StringBuffer();
        boolean l_blnDateStatus = true;
        
        for (int i = 0; i < l_sortKeys.length; i++)
        {
        	//�^�p�R�[�h
            if (WEB3FeqSortKeyItemNameDef.ORDER_EMP_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" order_emp_code ASC ");
                }
                else
                {
                    l_strSortKeys.append(" order_emp_code DESC ");
                }
            }
            //���X�R�[�h
            else if (WEB3FeqSortKeyItemNameDef.BRANCH_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" branch_code ASC ");
                }
                else
                {
                    l_strSortKeys.append(" branch_code DESC ");
                }
            }
            //�ڋq�R�[�h
            else if (WEB3FeqSortKeyItemNameDef.ACCOUNT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" substr(account_id, 9, 6) ASC ");
                }
                else
                {
                    l_strSortKeys.append(" substr(account_id, 9, 6) DESC ");
                }
            }
            //�����敪
            else if (WEB3FeqSortKeyItemNameDef.TRANSACTION_DIV.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" status ASC ");
                }
                else
                {
                    l_strSortKeys.append(" status DESC ");
                }
            }
            //�쐬���t
            else if (WEB3FeqSortKeyItemNameDef.CREATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" created_timestamp ASC ");
                }
                else
                {
                    l_strSortKeys.append(" created_timestamp DESC ");
                }
            }
            //�X�V���t
            else if (WEB3FeqSortKeyItemNameDef.LAST_UPDATED_TIMESTAMP.equals(l_sortKeys[i].keyItem))
            {
                l_blnDateStatus = false;
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortKeys.append(" last_updated_timestamp ASC ");
                }
                else
                {
                    l_strSortKeys.append(" last_updated_timestamp DESC ");
                }
            }
            if (i < l_sortKeys.length - 1)
            {
                l_strSortKeys.append(" , ");
            }
        }
        if (l_blnDateStatus)
        {
        	l_strSortKeys.append(" , last_updated_timestamp ASC ");
        }
        log.exiting(STR_METHOD_NAME);
        return l_strSortKeys.toString();
    }
    
    
    /**
     * (create��������������)<BR>
     * ����������������쐬����B <BR>
     * <BR>
     * �P�j��������������C���X�^���X(�FString)�𐶐�����B<BR>
     * <BR>
     * �Q�j�p�����[�^.�s��R�[�h != null �̏ꍇ�A<BR>
     * �s��R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �������������� += "market_code = ?  and "<BR>
     * <BR>
     * �R�j�،���ЃR�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "institution_code = ? "<BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A<BR>
     * �@@�����敪����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and status = ? "<BR>
     * <BR>
     * �T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A<BR>
     * �@@�^�p�R�[�h����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and order_emp_code = ? "<BR>
     * <BR>
     * �U�j����������������������ɒǉ�����B<BR>
     * �@@�������������� += "and biz_date = ? "<BR> 
     * <BR>
     * �V�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A<BR>
     * �@@���X�R�[�h����������������ɒǉ�����B<BR> 
     * <BR>
     * �W�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A<BR>
     * �@@�ڋq�R�[�h��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and substr(account_code, 0, 6) = ? "<BR>
     * <BR>
     * �X�j�p�����[�^.���[�����M�����t���O == true�̏ꍇ�A <BR>
     * �@@���[�����M��������������������ɒǉ�����B<BR>
     * <BR>
     * �@@�������������� += "and send_process_date_time is null "<BR>
     * <BR>
     * �P�O�j�쐬�������������������ԋp����B<BR>
     * @@param l_strStatus - (�����敪)
     * �����敪
     * @@param l_strOrderEmpCode - (�^�p�R�[�h)
     * �^�p�R�[�h
     * @@param l_strBranchCode - (���X�R�[�h)
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * �ڋq�R�[�h
     * @@param l_blnMailSendProcessDateFlag - (���[�����M�����t���O)
     * �h�d�q���[�����M���� is null�h�����������ɒǉ�����ꍇ�ɂ�true���A <BR>
     * �����łȂ��ꍇ��false��ݒ肷��B<BR>
     * @@param l_strMarketCode - (�s��R�[�h)
     * �s��R�[�h
     * @@return String
     * @@roseuid 42A80C63000C
     */
    protected String createQueryString(
    	String l_strStatus,
    	String l_strOrderEmpCode,
    	String l_strBranchCode,
    	String l_strAccountCode,
    	boolean l_blnMailSendProcessDateFlag,
        String l_strMarketCode)
    {
        final String STR_METHOD_NAME = 
            "createQueryString(String, String, String, String, boolean, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j��������������C���X�^���X(�FString)�𐶐�����B 
        String l_strQueryString = "";

        //�Q�j�p�����[�^.�s��R�[�h != null �̏ꍇ�A
        //�s��R�[�h����������������ɒǉ�����B
        //�������������� += "market_code = ?  and "
        if (l_strMarketCode != null)
        {
            l_strQueryString = l_strQueryString + " market_code = ?  and ";
        }

        //�R�j�،���ЃR�[�h����������������ɒǉ�����
        l_strQueryString = l_strQueryString + " institution_code = ? ";
        
        //�S�j�p�����[�^.�����敪 != null�̏ꍇ�A 
        //�����敪����������������ɒǉ�����B
        if (l_strStatus != null)
        {
        	l_strQueryString = l_strQueryString + " and status = ? ";
        }
        
		//�T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A 
		//�^�p�R�[�h����������������ɒǉ�����B 
        if (l_strOrderEmpCode != null)
        {
        	l_strQueryString = l_strQueryString + " and order_emp_code = ? ";
        }
        
        //�U�j����������������������ɒǉ�����B
        //�������������� += "and biz_date = ? "
        l_strQueryString = l_strQueryString + " and biz_date = ? ";
        
        //�V�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A 
        //���X�R�[�h����������������ɒǉ�����B 
        if (l_strBranchCode != null)
        {
        	l_strQueryString = l_strQueryString + " and branch_code = ? ";
        }
        
        //�W�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A 
        //�ڋq�R�[�h��������������������ɒǉ�����B 
        if (l_strAccountCode != null)
        {
        	l_strQueryString = l_strQueryString + " and substr(account_code, 0, 6) = ? ";
        }
        
        //�X�j�p�����[�^.���[�����M�����t���O == true�̏ꍇ�A 
        //���[�����M��������������������ɒǉ�����B 
        if (l_blnMailSendProcessDateFlag)
        {
        	l_strQueryString = l_strQueryString + " and send_process_date_time is null ";
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���������f�[�^�R���e�i���쐬����B <BR>
     * <BR>
     * �P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�p�����^.�s��R�[�h != null �̏ꍇ�A<BR>
     * �p�����[�^.�s��R�[�h�𐶐�����ArrayList�ɒǉ�����B<BR>
     * �R�j�p�����^.�،���ЃR�[�h�𐶐�����ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �S�j�p�����[�^.�����敪 != null�̏ꍇ�A <BR>
     * �@@�p�����[�^.�����敪�𐶐�����ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A<BR> 
     * �@@�p�����[�^.�^�p�R�[�h�𐶐�����ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �U�|�P�j�p�����[�^.������ != null�̏ꍇ�A <BR>
     * �@@�p�����[�^.�������𐶐�����ArrayList�ɒǉ�����B <BR>
     * �U�|�Q�j��L�ȊO <BR>
     * �@@�Ɩ����t�𐶐�����ArrayList�ɒǉ�����B <BR>
     * �V�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A <BR>
     * �@@�p�����[�^.���X�R�[�h�𐶐�����ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �W�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A <BR>
     * �@@�p�����[�^.�ڋq�R�[�h�𐶐�����ArrayList�ɒǉ�����B <BR>
     * <BR>
     * �X�j��������ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * �،���ЃR�[�h
     * @@param l_strStatus - (�����敪)
     * �����敪
     * @@param l_strOrderEmpCode - (�^�p�R�[�h)
     * �^�p�R�[�h
     * @@param l_datBizDate - (������)
     * ������
     * @@param l_strBranchCode - (���X�R�[�h)
     * ���X�R�[�h
     * @@param l_strAccountCode - (�ڋq�R�[�h)
     * �ڋq�R�[�h
     * @@param l_strMarketCode - (�s��R�[�h)
     * �s��R�[�h
     * @@return Object[]
     * @@roseuid 42A80C63000C
     */
    protected Object[] createQueryDataContainer(
        String l_strInstitutionCode, 
        String l_strStatus, 
        String l_strOrderEmpCode, 
        Date l_datBizDate,
        String l_strBranchCode, 
        String l_strAccountCode,
        String l_strMarketCode) throws WEB3BaseException
	{
        final String STR_METHOD_NAME = 
            "createQueryDataContainer(String, String, String, Date, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�jArrayList�𐶐�����B 
        ArrayList l_arrayList = new ArrayList();

        //�Q�j�p�����^.�s��R�[�h != null �̏ꍇ�A
        //�p�����[�^.�s��R�[�h�𐶐�����ArrayList�ɒǉ�����B
        if (l_strMarketCode != null)
        {
            l_arrayList.add(l_strMarketCode);
        }

        //�R�j�p�����^.�،���ЃR�[�h�𐶐�����ArrayList�ɒǉ�����B 
        l_arrayList.add(l_strInstitutionCode);
        
        //�S�j�p�����[�^.�����敪 != null�̏ꍇ�A 
        //�p�����[�^.�����敪�𐶐�����ArrayList�ɒǉ�����B 
        if (l_strStatus != null)
        {
            l_arrayList.add(l_strStatus);
        }
        
        //�T�j�p�����[�^.�^�p�R�[�h != null�̏ꍇ�A 
        //�p�����[�^.�^�p�R�[�h�𐶐�����ArrayList�ɒǉ�����
        if (l_strOrderEmpCode != null)
        {
            l_arrayList.add(l_strOrderEmpCode);
        }
        
        //�U�|�P�j�p�����[�^.������ != null�̏ꍇ�A
        //�p�����[�^.�������𐶐�����ArrayList�ɒǉ�����B
        //�U�|�Q�j��L�ȊO
        //�Ɩ����t�𐶐�����ArrayList�ɒǉ�����B
        if (l_datBizDate != null)
        {
            l_arrayList.add(WEB3DateUtility.formatDate(l_datBizDate, YYYYMMDD));
        }
        else
        {
            l_datBizDate = GtlUtils.getTradingSystem().getBizDate();
            l_arrayList.add(WEB3DateUtility.formatDate(l_datBizDate, YYYYMMDD));
        }
        
        //7�j�p�����[�^.���X�R�[�h != null�̏ꍇ�A 
        //�p�����[�^.���X�R�[�h�𐶐�����ArrayList�ɒǉ�����B 
        if (l_strBranchCode != null)
        {
            l_arrayList.add(l_strBranchCode);
        }
        
        //�W�j�p�����[�^.�ڋq�R�[�h != null�̏ꍇ�A 
        //�p�����[�^.�ڋq�R�[�h�𐶐�����ArrayList�ɒǉ�����B 
        if (l_strAccountCode != null)
        {
            l_arrayList.add(l_strAccountCode);
        }
        
        //�X�j��������ArrayList.toArray()�̖߂�l��ԋp����B
        Object[] l_strQueryContainers = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_strQueryContainers);
        
        log.exiting(STR_METHOD_NAME);
        return l_strQueryContainers;
	}
    
    
    
}
@
