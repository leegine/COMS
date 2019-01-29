head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoBalanceReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�c���Ɖ�T�[�r�X�����N���X(WEB3IfoBalanceReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬 
                 : 2006/7/24 ����(���u)�@@�d�l�ύX���f��526�A537    
                 : 2006/8/11 �s�p(���u)�@@�d�l�ύX���f��544     
Revesion History : 2007/06/29 ���^�] (���u) �d�l�ύX���f��No.752
Revesion History : 2008/08/20 ���� (���u) IFO�����_�Ή�
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchIndexDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.ifo.WEB3IfoPositionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3DivisionTypeDef;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.ifo.message.WEB3FuturesOptionsBalRefTotalParIndexUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceComparator;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalRequest;
import webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsDetailUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsProductCodeNameUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsSortKey;
import webbroker3.ifo.message.WEB3OptionsOpenDateComparator;
import webbroker3.ifo.message.WEB3OptionsSessionTypeComparator;
import webbroker3.ifo.service.delegate.WEB3IfoBalanceReferenceService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

/**
 * (�敨OP�c���Ɖ�T�[�r�XImpl)<BR>
 * �敨OP�c���Ɖ�T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoBalanceReferenceServiceImpl extends WEB3ClientRequestService implements WEB3IfoBalanceReferenceService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoBalanceReferenceServiceImpl.class);
        
   
    /**
     * �����w���敨/�I�v�V�����c���Ɖ�����s���B<BR>
     * <BR>
     * this.get�c���Ɖ�()���\�b�h���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3BaseException
     * @@roseuid 41AAC9ED0015
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3FuturesOptionsBalanceReferenceRequest) 
        {
           l_response = getBalanceReference((WEB3FuturesOptionsBalanceReferenceRequest)l_request);
        }
        else if (l_request instanceof WEB3FuturesOptionsBalanceReferenceTotalRequest)
        {
            l_response = getBalanceTotal((WEB3FuturesOptionsBalanceReferenceTotalRequest)l_request);
        }
        else
        {
            log.error(STR_METHOD_NAME + "�p�����[�^�^�C�v�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80018, STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);

        return l_response;
   }
   
    /**
     * (get�c���Ɖ�)<BR>
     * �����w���敨/�I�v�V�����c���Ɖ�����s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�敨OP�c���Ɖ�)get�c���Ɖ�v<BR>
     * �Q��<BR>
     * @@param l_request - �敨OP�c���Ɖ�N�G�X�g�I�u�W�F�N�g
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceResponse
     * @@roseuid 41AACA0802F3
     */
    protected WEB3FuturesOptionsBalanceReferenceResponse getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceReference(WEB3FuturesOptionsBalanceReferenceRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        //�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount(l_request.fuOpDiv);
        //�V�X�e��������~(�o�b�`���A�ً}��~��)�`�F�b�N�����{����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
       
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManager = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        
        WEB3IfoProductImpl l_ifoProductImpl = null;
        Institution l_institution = l_subAccount.getInstitution();

        //���N�G�X�g�f�[�^�ɖ����R�[�h���ݒ肳��Ă���ꍇ
        if (l_request.productCode != null)
        {
             //�����I�u�W�F�N�g���擾����B�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
             try
             {
                 l_ifoProductImpl = l_productManager.getIfoProduct(l_institution, l_request.productCode);
             }
             catch (NotFoundException e)
             {
                 throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                     getClass().getName() + "." + STR_METHOD_NAME);
             }
        }
        
        //���N�G�X�g�f�[�^�ɖ������荀��(����s��A�w����ʁA����)���ݒ肳��Ă���ꍇ
        if ((l_request.marketCode != null)
            && (l_request.targetProductCode != null)
            && (l_request.delivaryMonth != null))
        {
            if(WEB3FuturesOptionDivDef.FUTURES.equals(l_request.fuOpDiv))
            {
                //�����I�u�W�F�N�g���擾����B�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
                try
                {
                    l_ifoProductImpl = l_productManager.getIfoProduct(
                        l_institution,
                        l_request.targetProductCode,
                        l_request.delivaryMonth,
                        IfoDerivativeTypeEnum.FUTURES,
                        0,
                        WEB3DivisionTypeDef.DIVISION_DEFAULT,
                        l_request.marketCode);
                }
                catch (NotFoundException e)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            else if (WEB3FuturesOptionDivDef.OPTION.equals(l_request.fuOpDiv))
            {
                IfoDerivativeTypeEnum l_ifoDerivativeType = null;
                if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_request.opProductType))
                {
                    l_ifoDerivativeType = IfoDerivativeTypeEnum.CALL_OPTIONS;
                }
                else if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_request.opProductType))
                {
                    l_ifoDerivativeType = IfoDerivativeTypeEnum.PUT_OPTIONS;
                }
                else
                {
                    //�I�v�V�������i�敪�����݂��Ȃ��l
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00270,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }

                //�����I�u�W�F�N�g���擾����B�Y��������������݂��Ȃ��ꍇ�͗�O���X���[����B
                try
                {
                l_ifoProductImpl = l_productManager.getIfoProduct(
                    l_institution,
                    l_request.targetProductCode,
                    l_request.delivaryMonth,
                    l_ifoDerivativeType,
                    Double.parseDouble(l_request.strikePrice),
                    WEB3DivisionTypeDef.DIVISION_DEFAULT,
                    l_request.marketCode);
                }
                catch (NotFoundException e)
                {
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                        getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        
        //�w������̕ێ�����S���ʂ̖����R�[�h�Ɩ������̈ꗗ���擾����B
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        WEB3FuturesOptionsProductCodeNameUnit[] l_productCodeNameUnits = l_positionManager.createProductCodeNameFromContract(l_subAccount,true,l_request.fuOpDiv);
        //���X�|���X�f�[�^�𐶐�����B
        WEB3FuturesOptionsBalanceReferenceResponse l_response = (WEB3FuturesOptionsBalanceReferenceResponse)l_request.createResponse();
        //(*)create�����R�[�h����from����()�̖߂�l != null�̏ꍇ
        if (l_productCodeNameUnits != null)
        {
            //����������������쐬����B
            String l_strQuery = this.createQueryString(l_ifoProductImpl);
            //���������f�[�^�R���e�i���쐬����B
            String[] l_strContainer = this.createQueryContainer(l_ifoProductImpl);  
            //�c���Ɖ��ʂɕ\������c���Ɖ�ׂ̈ꗗ���쐬����B
            WEB3FuturesOptionsDetailUnit[] l_detailUnit = l_positionManager.createIfoBalanceReferenceDetailUnit(l_subAccount,l_request.fuOpDiv,l_request.settlementState,l_strQuery,l_strContainer);
            //�c���Ɖ�ׂ��\�[�g����B
            this.sortBalanceReferenceDetailUnit(l_detailUnit,l_request.sortKeys);
            
            //(1)�Y���f�[�^�Ȃ��̏ꍇ(create�����R�[�h����from����()�̖߂�l��null�@@�܂��́@@create�c���Ɖ��()�̖߂�l��null�̏ꍇ)
            if (l_productCodeNameUnits == null || l_detailUnit == null )
            {
                //���X�|���X.�c���Ɖ��          
                l_response.balanceReference = null;
                //create�����R�[�h����from����()�̖߂�l
                l_response.futOpProductCodeNames = l_productCodeNameUnits;
                return l_response;          
            }
            //(2)(1)�ȊO�̏ꍇ
            else
            {
                
                //create�����R�[�h����from����()�̖߂�l
                l_response.futOpProductCodeNames = l_productCodeNameUnits;
                //���X�|���X.�c���Ɖ��  
                WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_detailUnit, Integer.parseInt(l_request.pageIndex),Integer.parseInt(l_request.pageSize));
                l_response.balanceReference = (WEB3FuturesOptionsDetailUnit[])l_pageIndexInfo.getArrayReturned(WEB3FuturesOptionsDetailUnit.class);
                //���X�|���X.���y�[�W��
                l_response.totalPages = "" + l_pageIndexInfo.getTotalPages();
                //���X�|���X.�����R�[�h��
                l_response.totalRecords = "" + l_pageIndexInfo.getTotalRecords();
                //���X�|���X.�\���y�[�W�ԍ� 
                l_response.pageIndex = "" + l_pageIndexInfo.getPageIndex();                                                       
            }
                       
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�c�����v)<BR>
     * �����w���敨/�I�v�V�����c�����v�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(�敨OP�c���Ɖ�)get�c�����v�v<BR>
     * �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �敨OP�c���Ɖ�c�����v���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return webbroker3.ifo.message.WEB3FuturesOptionsBalanceReferenceTotalResponse
     * @@roseuid 41AD2DE80020
     */
    protected WEB3FuturesOptionsBalanceReferenceTotalResponse getBalanceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBalanceTotal(WEB3FuturesOptionsBalanceReferenceTotalRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1.���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        //1.2.�⏕�������擾����B
        WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)this.getSubAccount(l_request.fuOpDiv);
        //1.3.�V�X�e��������~(�o�b�`���A�ً}��~��)�`�F�b�N�����{����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        //1.4.���X�|���X�f�[�^�𐶐�����B
        WEB3FuturesOptionsBalanceReferenceTotalResponse l_response = (WEB3FuturesOptionsBalanceReferenceTotalResponse)l_request.createResponse();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3IfoProductManagerImpl l_productManagerImpl = (WEB3IfoProductManagerImpl)l_tradingModule.getProductManager();
        WEB3IfoPositionManagerImpl l_positionManager = (WEB3IfoPositionManagerImpl)l_tradingModule.getPositionManager();
        //1.5.�c���Ɖ��ʂɕ\������c���Ɖ�ׂ̈ꗗ���쐬����B
        WEB3FuturesOptionsDetailUnit[] l_detailUnit = l_positionManager.createIfoBalanceReferenceDetailUnit(l_subAccount,l_request.fuOpDiv,null,null,null);
        //(*)create�敨OP�c���Ɖ��()�̖߂�l�̗v�f����Loop����
        
        int l_intDetailUnitLength = 0;
        if (l_detailUnit != null)
        {
            l_intDetailUnitLength = l_detailUnit.length;
        }
        //�����ʑ�����
        double l_dblBuyQuantity = 0;
        //�����ʕ]�����v���v
        double l_dblBuyIncome = 0;
        //�����ʕ]�����v���v(���o��j
        double l_dblBuyIncomeCost = 0;
        //�����ʎ������z
        double l_dblBuyCurrentPrice = 0;
        
        //�v�b�g�����ʑ�����
        double l_dblPutBuyQuantity = 0;
        //�v�b�g�����ʕ]�����v���v
        double l_dblPutBuyIncome = 0; 
        //�v�b�g�����ʕ]�����v���v(���o�)
        double l_dblPutBuyIncomeCost = 0; 
        //�v�b�g�����ʎ������z
        double l_dblPutBuyCurrentPrice = 0;
        
        //�R�[�������ʑ�����
        double l_dblCallBuyQuantity = 0;
        //�R�[�������ʕ]�����v���v
        double l_dblCallBuyIncome = 0; 
        //�R�[�������ʕ]�����v���v(���o�)
        double l_dblCallBuyIncomeCost = 0; 
        //�R�[�������ʎ������z
        double l_dblCallBuyCurrentPrice = 0;
        
        //�����ʑ�����
        double l_dblSellQuantity = 0;
        //�����ʕ]�����v���v
        double l_dblSellIncome = 0;
        //�����ʕ]�����v���v(���o�)
        double l_dblSellIncomeCost = 0;
        //�����ʎ������z
        double l_dblSellCurrentPrice = 0;
        
        //�v�b�g�����ʑ�����
        double l_dblPutSellQuantity = 0;
        //�v�b�g�����ʕ]�����v���v
        double l_dblPutSellIncome = 0; 
        //�v�b�g�����ʕ]�����v���v(���o�)
        double l_dblPutSellIncomeCost = 0; 
        //�v�b�g�����ʎ������z
        double l_dblPutSellCurrentPrice = 0;
        
        //�R�[�������ʑ�����
        double l_dblCallSellQuantity = 0;
        //�R�[�������ʕ]�����v���v
        double l_dblCallSellIncome = 0; 
        //�R�[�������ʕ]�����v���v(���o�)
        double l_dblCallSellIncomeCost = 0; 
        //�R�[�������ʎ������z
        double l_dblCallSellCurrentPrice = 0;

        BigDecimal l_bdBuyIncome = new BigDecimal(l_dblBuyIncome + "");
        BigDecimal l_bdBuyIncomeCost = new BigDecimal(l_dblBuyIncomeCost + "");
        BigDecimal l_bdBuyCurrentPrice = new BigDecimal(l_dblBuyCurrentPrice + "");
        BigDecimal l_bdPutBuyIncome = new BigDecimal(l_dblPutBuyIncome + "");
        BigDecimal l_bdPutBuyIncomeCost = new BigDecimal(l_dblPutBuyIncomeCost + "");
        BigDecimal l_bdPutBuyCurrentPrice = new BigDecimal(l_dblPutBuyCurrentPrice + "");
        BigDecimal l_bdCallBuyIncome = new BigDecimal(l_dblCallBuyIncome + "");
        BigDecimal l_bdCallBuyIncomeCost = new BigDecimal(l_dblCallBuyIncomeCost + "");
        BigDecimal l_bdCallBuyCurrentPrice = new BigDecimal(l_dblCallBuyCurrentPrice + "");
        BigDecimal l_bdSellIncome = new BigDecimal(l_dblSellIncome + "");
        BigDecimal l_bdSellIncomeCost = new BigDecimal(l_dblSellIncomeCost + "");
        BigDecimal l_bdSellCurrentPrice = new BigDecimal(l_dblSellCurrentPrice + "");
        BigDecimal l_bdPutSellIncome = new BigDecimal(l_dblPutSellIncome + "");
        BigDecimal l_bdPutSellIncomeCost = new BigDecimal(l_dblPutSellIncomeCost + "");
        BigDecimal l_bdPutSellCurrentPrice = new BigDecimal(l_dblPutSellCurrentPrice + "");
        BigDecimal l_bdCallSellIncome = new BigDecimal(l_dblCallSellIncome + "");
        BigDecimal l_bdCallSellIncomeCost = new BigDecimal(l_dblCallSellIncomeCost + "");
        BigDecimal l_bdCallSellCurrentPrice = new BigDecimal(l_dblCallSellCurrentPrice + "");

        //1.6.HashMap( )
        HashMap l_hsmUnitSize = new HashMap();
        HashMap l_hsmUnit = new HashMap();
        
        //1.7.get�i���X�w���ʁj�戵�����ꗗ(�،���ЃR�[�h : String, ���X�R�[�h : String, �敨�^�I�v�V�����敪 : String)
        //(���X�w����)�戵�����I�u�W�F�N�g���擾����  
        //[����]  
        //�،���ЃR�[�h�F  
        //�،���� = �⏕����.get�،����()  
        //�،���ЃR�[�h = �،����.get�،���ЃR�[�h()  
        //���X�R�[�h�F�@@�⏕�����Dget�戵�X()�DgetBranchCode  
        //�敨�^�I�v�V�����敪�F�@@���N�G�X�g.�敨�^�I�v�V�����敪
        WEB3GentradeBranch l_branch = (WEB3GentradeBranch) l_subAccount.getMainAccount().getBranch();
        WEB3GentradeBranchIndexDealtCond[] l_branchIndexeFutures =
            WEB3GentradeBranchIndexDealtCond.getHandlingCondBranchIndexList(
        		l_subAccount.getInstitution().getInstitutionCode(),
        		l_branch.getBranchCode(),
        		l_request.fuOpDiv);
        
        if (l_branchIndexeFutures == null || l_branchIndexeFutures.length == 0)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + "." + STR_METHOD_NAME,
                "(���X�w����)�戵�����I�u�W�F�N�g���擾�ł��܂���");
        }

        int l_intBranchIndexeFutures = l_branchIndexeFutures.length;
        
        //1.8.�擾�����S�Ă�(���X�w����)�戵�����I�u�W�F�N�g�ɑ΂����[�v����
        for (int i = 0; i < l_intBranchIndexeFutures; i++)
        {
        	boolean l_blnRep = false;
        	//1.8.1.(���X�w����)�戵�����I�u�W�F�N�g.�����Y�����R�[�h�ŏd�����Ȃ��ꍇ
        	for (int j = 0; j < i; j++)
        	{
        		String l_strTargetProductCode = l_branchIndexeFutures[i].getUnderlyingProductCode();
        		if (l_strTargetProductCode.equals(l_branchIndexeFutures[j].getUnderlyingProductCode()))
        		{
        			l_blnRep = true;
        		}
        	}
        	
        	if (!l_blnRep)
        	{
        		//1.8.1.1�w���ʎc�����v()
        		WEB3FuturesOptionsBalRefTotalParIndexUnit l_balRefTotalParIndexUnit = 
        			new WEB3FuturesOptionsBalRefTotalParIndexUnit();
        		l_balRefTotalParIndexUnit.targetProductCode = l_branchIndexeFutures[i].getUnderlyingProductCode();
        		//1.8.1.2�v���p�e�B�Z�b�g
        		l_hsmUnit.put(
                    l_branchIndexeFutures[i].getUnderlyingProductCode(), 
    				l_balRefTotalParIndexUnit);
        	}
        }
        
        //1.9.(*)create�敨OP�c���Ɖ��()�̖߂�l�̗v�f����Loop����
        for (int i = 0; i < l_intDetailUnitLength; i++)
        {
            //�w���搔���X�g����w���搔���擾����B
            Double l_dblUnitSize = null;
            Object l_strUnitSize = l_hsmUnitSize.get(l_detailUnit[i].productCode + l_detailUnit[i].marketCode);
            if (l_strUnitSize == null)
            {
                //����J�����_�R���e�L�X�g�̖����R�[�h���ăZ�b�g����B
                WEB3GentradeTradingTimeManagement.resetProductCode(l_detailUnit[i].targetProductCode);
                WEB3IfoTradedProductImpl l_tradedProductImpl = null;
                try
                {
                    //����������擾����B
                    l_tradedProductImpl = 
                        l_productManagerImpl.getIfoTradedProduct(
                            l_subAccount.getInstitution(),
                            l_detailUnit[i].productCode,
                            l_detailUnit[i].marketCode);
                }
                catch (NotFoundException l_nfex)
                {
                    log.error(STR_METHOD_NAME, l_nfex);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                        STR_METHOD_NAME);
                }
                IfoTradedProductRow l_tradedProduceRow = 
                    (IfoTradedProductRow)l_tradedProductImpl.getDataSourceObject();
                //�w���搔 = get�������()�̖߂�l.1�P�ʓ���搔 �Ƃ���
                l_dblUnitSize =  new Double(l_tradedProduceRow.getUnitSize());
                
                //�w���搔�i�[���X�g�Ɏw���搔��ǉ�����B
                l_hsmUnitSize.put((l_detailUnit[i].productCode + l_detailUnit[i].marketCode),
                    l_dblUnitSize);
            }
            else
            {
                l_dblUnitSize = new Double(l_strUnitSize.toString());
            }
            BigDecimal l_bdUnitSize = new BigDecimal(l_dblUnitSize.doubleValue() + "");
            //�����Ώۂ̎c���Ɖ��.���� == null�@@
            //�������Ώۂ̎c���Ɖ��.���v == null�@@
            //��������sy�w�̎c���Ɖ��.���v(���o�) == null�̏ꍇ�A
            if (l_detailUnit[i].currentPrice == null 
            	&& l_detailUnit[i].income == null
            	&& l_detailUnit[i].incomeCost == null)
            {
                l_detailUnit[i].currentPrice = "0";
                l_detailUnit[i].income = "0";
                l_detailUnit[i].incomeCost = "0";
            }
            
            WEB3FuturesOptionsBalRefTotalParIndexUnit l_totalParIndexUnit = 
            	(WEB3FuturesOptionsBalRefTotalParIndexUnit) l_hsmUnit.get(l_detailUnit[i].targetProductCode); 
            
            //(*)�����Ώۂ̎c���Ɖ��.���敪 == "����"�̏ꍇ
            if (WEB3IfoContractTypeDef.OPEN_BUY.equals(l_detailUnit[i].contractType))
            {
                //���X�|���X�f�[�^.�����ʑ����� += �����Ώۂ̎c���Ɖ��.������
                l_dblBuyQuantity += 
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                //���X�|���X�f�[�^.�����ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                l_bdBuyIncome = l_bdBuyIncome.add(new BigDecimal(l_detailUnit[i].income));
                //���X�|���X�f�[�^.�����ʕ]�����v���v(���o�) 
                //+= �����Ώۂ̎c���Ɖ��.���v(���o�)
                l_bdBuyIncomeCost = l_bdBuyIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                //���X�|���X�f�[�^.�����ʎ������z += 
                //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                l_bdBuyCurrentPrice = l_bdBuyCurrentPrice.add(
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                        new BigDecimal(l_detailUnit[i].currentPrice)));
                
                if (l_totalParIndexUnit != null)
                {
	                //�w���ʎc�����v.�����ʑ����� += �����Ώۂ̎c���Ɖ��.������
	                l_totalParIndexUnit.buyTotalQuantity = 
	                	WEB3StringTypeUtility.formatNumber(
	            			Double.parseDouble(l_totalParIndexUnit.buyTotalQuantity) 
	                		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                
	                //�w���ʎc�����v.�����ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
	                l_totalParIndexUnit.buyAssetProfitLoss =
	                	WEB3StringTypeUtility.formatNumber(
	            			new BigDecimal(l_totalParIndexUnit.buyAssetProfitLoss).add(
	                		    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                
	                //�w���ʎc�����v.�����ʕ]�����v���v(���o�) 
	                //+= �����Ώۂ̎c���Ɖ��.���v(���o�)
	                l_totalParIndexUnit.buyAssetProfitLossCost =
	                	WEB3StringTypeUtility.formatNumber(
	            			new BigDecimal(l_totalParIndexUnit.buyAssetProfitLossCost).add(
	                		    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                //�w���ʎc�����v.�����ʎ������z += 
	                //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                    l_totalParIndexUnit.buyCurrentPrice =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.buyCurrentPrice).add(
                                new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                    new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                }

            
                //(*)�����Ώۂ̎c���Ɖ��.�I�v�V�������i�敪 = "�v�b�g"�̏ꍇ
                if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //���X�|���X�f�[�^.�v�b�g�����ʑ����� += �����Ώۂ̎c���Ɖ��.������
                    l_dblPutBuyQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //���X�|���X�f�[�^.�v�b�g�����ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                    l_bdPutBuyIncome = l_bdPutBuyIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //���X�|���X�f�[�^.�v�b�g�����ʕ]�����v���v(���o�) 
                    //+= �����Ώۂ̎c���Ɖ��.���v(���o�)
                    l_bdPutBuyIncomeCost = l_bdPutBuyIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //���X�|���X�f�[�^.�v�b�g�����ʎ������z += 
                    //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                    l_bdPutBuyCurrentPrice = l_bdPutBuyCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //�w���ʎc�����v.�v�b�g�����ʑ����� += �����Ώۂ̎c���Ɖ��.������
	                    l_totalParIndexUnit.putBuyTotalQuantity =
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.putBuyTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                        
	                    //�w���ʎc�����v.�v�b�g�����ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                        l_totalParIndexUnit.putBuyAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putBuyAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //�w���ʎc�����v.�v�b�g�����ʕ]�����v���v(���o�) 
	                    //+= �����Ώۂ̎c���Ɖ��.���v(���o�)
                        l_totalParIndexUnit.putBuyAssetProfitLossCost =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putBuyAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //�w���ʎc�����v.�v�b�g�����ʎ������z 
	                    //+= �����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                        l_totalParIndexUnit.putBuyCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putBuyCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                    
                }
                //(*)�����Ώۂ̎c���Ɖ��.�I�v�V�������i�敪 = "�R�[��"�̏ꍇ 
                else if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //���X�|���X�f�[�^.�R�[�������ʑ����� += �����Ώۂ̎c���Ɖ��.������
                    l_dblCallBuyQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //���X�|���X�f�[�^.�R�[�������ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                    l_bdCallBuyIncome = l_bdCallBuyIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //���X�|���X�f�[�^.�R�[�������ʕ]�����v���v(���o�) 
                    //+= �����Ώۂ̎c���Ɖ��.���v(���o�)
                    l_bdCallBuyIncomeCost = l_bdCallBuyIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //���X�|���X�f�[�^.�R�[�������ʎ������z
                    //+= �����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                    l_bdCallBuyCurrentPrice = l_bdCallBuyCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //�w���ʎc�����v.�R�[�������ʑ����� += �����Ώۂ̎c���Ɖ��.������
	                    l_totalParIndexUnit.callBuyTotalQuantity = 
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.callBuyTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                        
	                    //�w���ʎc�����v.�R�[�������ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                        l_totalParIndexUnit.callBuyAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callBuyAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //�w���ʎc�����v.�R�[�������ʕ]�����v���v(���o�) 
	                    //+= �����Ώۂ̎c���Ɖ��.���v(���o�)
                        l_totalParIndexUnit.callBuyAssetProfitLossCost =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callBuyAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //�w���ʎc�����v.�R�[�������ʎ������z 
	                    //+= �����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����   
                        l_totalParIndexUnit.callBuyCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callBuyCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                } 
            }
            //(*)�����Ώۂ̎c���Ɖ��.���敪 == "����"�̏ꍇ
            if (WEB3IfoContractTypeDef.OPEN_SELL.equals(l_detailUnit[i].contractType))
            {
                //���X�|���X�f�[�^.�����ʑ����� += �����Ώۂ̎c���Ɖ��.������
                l_dblSellQuantity += 
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                //���X�|���X�f�[�^.�����ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                l_bdSellIncome = l_bdSellIncome.add(new BigDecimal(l_detailUnit[i].income));
                //���X�|���X�f�[�^.�����ʕ]�����v���v(���o�) += 
                //�����Ώۂ̎c���Ɖ��.���v(���o�)
                l_bdSellIncomeCost = l_bdSellIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                //���X�|���X�f�[�^.�����ʎ������z += 
                //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                l_bdSellCurrentPrice = l_bdSellCurrentPrice.add(
                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                        new BigDecimal(l_detailUnit[i].currentPrice)));

                if (l_totalParIndexUnit != null)
                {
	                //�w���ʎc�����v.�����ʑ����� += �����Ώۂ̎c���Ɖ��.������
	                l_totalParIndexUnit.sellTotalQuantity = 
	                	WEB3StringTypeUtility.formatNumber(
	            			Double.parseDouble(l_totalParIndexUnit.sellTotalQuantity) 
	                		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                    
	                //�w���ʎc�����v.�����ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                    l_totalParIndexUnit.sellAssetProfitLoss =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.sellAssetProfitLoss).add(
                                new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                    
	                //�w���ʎc�����v.�����ʕ]�����v���v(���o�) += 
	                //�����Ώۂ̎c���Ɖ��.���v(���o�)
                    l_totalParIndexUnit.sellAssetProfitLossCost =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.sellAssetProfitLossCost).add(
                                new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                //�w���ʎc�����v.�����ʎ������z += 
	                //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����            
                    l_totalParIndexUnit.sellCurrentPrice =
                        WEB3StringTypeUtility.formatNumber(
                            new BigDecimal(l_totalParIndexUnit.sellCurrentPrice).add(
                                new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                    new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                }
            
                //(*)�����Ώۂ̎c���Ɖ��.�I�v�V�������i�敪 = "�v�b�g"�̏ꍇ
                if (WEB3IfoProductTypeDef.PUT_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //���X�|���X�f�[�^.�v�b�g�����ʑ����� += 
                    //�����Ώۂ̎c���Ɖ��.������
                    l_dblPutSellQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //���X�|���X�f�[�^.�v�b�g�����ʕ]�����v���v += 
                    //�����Ώۂ̎c���Ɖ��.���v
                    l_bdPutSellIncome = l_bdPutSellIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //���X�|���X�f�[�^.�v�b�g�����ʕ]�����v���v(���o�) += 
                    //�����Ώۂ̎c���Ɖ��.���v(���o�)
                    l_bdPutSellIncomeCost = l_bdPutSellIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //���X�|���X�f�[�^.�v�b�g�����ʎ������z += 
                    //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                    l_bdPutSellCurrentPrice = l_bdPutSellCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //�w���ʎc�����v.�v�b�g�����ʑ����� += 
	                    //�����Ώۂ̎c���Ɖ��.������
	                    l_totalParIndexUnit.putSellTotalQuantity = 
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.putSellTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue());
	                        
	                    //�w���ʎc�����v.�v�b�g�����ʕ]�����v���v += 
	                    //�����Ώۂ̎c���Ɖ��.���v
                        l_totalParIndexUnit.putSellAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putSellAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //�w���ʎc�����v.�v�b�g�����ʕ]�����v���v(���o�) += 
	                    //�����Ώۂ̎c���Ɖ��.���v(���o�)
                        l_totalParIndexUnit.putSellAssetProfitLossCost =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putSellAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //�w���ʎc�����v.�v�b�g�����ʎ������z += 
	                    //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����                       
                        l_totalParIndexUnit.putSellCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.putSellCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                }
                //(*)�����Ώۂ̎c���Ɖ��.�I�v�V�������i�敪 = "�R�[��"�̏ꍇ 
                else if (WEB3IfoProductTypeDef.CALL_OPTIONS.equals(l_detailUnit[i].opProductType))
                {
                    //���X�|���X�f�[�^.�R�[�������ʑ����� += �����Ώۂ̎c���Ɖ��.������
                    l_dblCallSellQuantity += 
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue();
                    //���X�|���X�f�[�^.�R�[�������ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                    l_bdCallSellIncome = l_bdCallSellIncome.add(new BigDecimal(l_detailUnit[i].income));
                    //���X�|���X�f�[�^.�R�[�������ʕ]�����v���v(���o�) += 
                    //�����Ώۂ̎c���Ɖ��.���v(���o�)
                    l_bdCallSellIncomeCost = l_bdCallSellIncomeCost.add(new BigDecimal(l_detailUnit[i].incomeCost));
                
                    //���X�|���X�f�[�^.�R�[�������ʎ������z += 
                    //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                    l_bdCallSellCurrentPrice = l_bdCallSellCurrentPrice.add(
                        new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                            new BigDecimal(l_detailUnit[i].currentPrice)));

                    if (l_totalParIndexUnit != null)
                    {
	                    //�w���ʎc�����v.�R�[�������ʑ����� += �����Ώۂ̎c���Ɖ��.������
	                    l_totalParIndexUnit.callSellTotalQuantity = 
	                    	WEB3StringTypeUtility.formatNumber(
	                			Double.parseDouble(l_totalParIndexUnit.callSellTotalQuantity) 
	                    		+ new BigDecimal(l_detailUnit[i].contractOrderQuantity).doubleValue()); 
	                       
	                    //�w���ʎc�����v.�R�[�������ʕ]�����v���v += �����Ώۂ̎c���Ɖ��.���v
                        l_totalParIndexUnit.callSellAssetProfitLoss =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callSellAssetProfitLoss).add(
                                    new BigDecimal(l_detailUnit[i].income)).doubleValue());
	                        
	                    //�w���ʎc�����v.�R�[�������ʕ]�����v���v(���o�) += 
	                    //�����Ώۂ̎c���Ɖ��.���v(���o�)
                        l_totalParIndexUnit.callSellAssetProfitLossCost = 
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callSellAssetProfitLossCost).add(
                                    new BigDecimal(l_detailUnit[i].incomeCost)).doubleValue());
	                
	                    //�w���ʎc�����v.�R�[�������ʎ������z += 
	                    //�����Ώۂ̎c���Ɖ��.������ *�擾�����w���搔 *�����Ώۂ̎c���Ɖ��.����
                        l_totalParIndexUnit.callSellCurrentPrice =
                            WEB3StringTypeUtility.formatNumber(
                                new BigDecimal(l_totalParIndexUnit.callSellCurrentPrice).add(
                                    new BigDecimal(l_detailUnit[i].contractOrderQuantity).multiply(l_bdUnitSize).multiply(
                                        new BigDecimal(l_detailUnit[i].currentPrice))).doubleValue());
                    }
                }               
            }       
            if (l_totalParIndexUnit != null)
            {
	            l_hsmUnit.put(l_totalParIndexUnit.targetProductCode, l_totalParIndexUnit);    
            }
        }

        l_response.buyCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyCurrentPrice.doubleValue());
        l_response.buyAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncome.doubleValue());
        l_response.buyAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncomeCost.doubleValue());
        l_response.buyTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyQuantity);
        
        l_response.putBuyCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdPutBuyCurrentPrice.doubleValue());
        l_response.putBuyAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdPutBuyIncome.doubleValue());
        l_response.putBuyAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdPutBuyIncomeCost.doubleValue());
        l_response.putBuyTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblPutBuyQuantity);
        
        l_response.callBuyCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCallBuyCurrentPrice.doubleValue());
        l_response.callBuyAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdCallBuyIncome.doubleValue());
        l_response.callBuyAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdCallBuyIncomeCost.doubleValue());
        l_response.callBuyTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblCallBuyQuantity);
        
        l_response.sellCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdSellCurrentPrice.doubleValue());
        l_response.sellAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdSellIncome.doubleValue());
        l_response.sellAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdSellIncomeCost.doubleValue());
        l_response.sellTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblSellQuantity);
        
        l_response.putSellCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdPutSellCurrentPrice.doubleValue());
        l_response.putSellAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdPutSellIncome.doubleValue());
        l_response.putSellAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdPutSellIncomeCost.doubleValue());
        l_response.putSellTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblPutSellQuantity);
        
        l_response.callSellCurrentPrice = 
            WEB3StringTypeUtility.formatNumber(l_bdCallSellCurrentPrice.doubleValue());
        l_response.callSellAssetProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdCallSellIncome.doubleValue());
        l_response.callSellAssetProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdCallSellIncomeCost.doubleValue());
        l_response.callSellTotalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblCallSellQuantity);
        
        //���X�|���X�f�[�^.���ʑ����� = ���X�|���X�f�[�^.�����ʑ�����
        //+ ���X�|���X�f�[�^.�����ʑ�����
        l_response.totalQuantity = 
            WEB3StringTypeUtility.formatNumber(l_dblBuyQuantity + l_dblSellQuantity);
        //���X�|���X�f�[�^.�]�����v���v = ���X�|���X�f�[�^.�����ʕ]�����v���v
        //+ ���X�|���X�f�[�^.�����ʕ]�����v���v
        l_response.appraisalProfitLoss = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncome.add(l_bdSellIncome).doubleValue());
        //���X�|���X�f�[�^.�]�����v���v(���o�) = ���X�|���X�f�[�^.�����ʕ]�����v���v(���o�)
        //+ ���X�|���X�f�[�^.�����ʕ]�����v���v(���o�)
        l_response.appraisalProfitLossCost = 
            WEB3StringTypeUtility.formatNumber(l_bdBuyIncomeCost.add(l_bdSellIncomeCost).doubleValue());
        
        Collection l_clnUnit = l_hsmUnit.values();
        WEB3FuturesOptionsBalRefTotalParIndexUnit[] l_totalParIndexUnits = null;
        int l_intUnitSize = 0;
        if (l_clnUnit != null && !l_clnUnit.isEmpty())
        {
        	l_intUnitSize = l_clnUnit.size();
        	l_totalParIndexUnits =
        		new WEB3FuturesOptionsBalRefTotalParIndexUnit[l_intUnitSize];
        	l_clnUnit.toArray(l_totalParIndexUnits);
        }
        //�w���ʎc�����v.�����ʑ�����
        double l_dblUnitBuyTotalQuantity = 0;
        //�w���ʎc�����v.�����ʑ�����     
        double l_dblUnitSellTotalQuantity = 0;
        //�w���ʎc�����v.���ʑ�����
        double l_dblUnitTotalQuantity = 0;
        //�w���ʎc�����v.�]�����v���v
        double l_dblUnitAppraisalProfitLoss = 0;     
        //�w���ʎc�����v.�]�����v���v(���o�)
        double l_dblUnitAppraisalProfitLossCost = 0;
        for (int k = 0; k < l_intUnitSize; k++)
        {
            //�w���ʎc�����v.���ʑ����� = �w���ʎc�����v.�����ʑ�����
            //+ �w���ʎc�����v.�����ʑ�����
        	l_dblUnitBuyTotalQuantity = new BigDecimal(l_totalParIndexUnits[k].buyTotalQuantity).doubleValue();
        	l_dblUnitSellTotalQuantity = new BigDecimal(l_totalParIndexUnits[k].sellTotalQuantity).doubleValue();
        	l_dblUnitTotalQuantity = l_dblUnitBuyTotalQuantity + l_dblUnitSellTotalQuantity;
        	l_totalParIndexUnits[k].totalQuantity = WEB3StringTypeUtility.formatNumber(l_dblUnitTotalQuantity);
        	
            //�w���ʎc�����v.�]�����v���v = �w���ʎc�����v.�����ʕ]�����v���v
            //+ �w���ʎc�����v.�����ʕ]�����v���v
            BigDecimal l_bdUnitBuyAssetProfitLoss = new BigDecimal(l_totalParIndexUnits[k].buyAssetProfitLoss);
            BigDecimal l_bdUnitSellAssetProfitLoss = new BigDecimal(l_totalParIndexUnits[k].sellAssetProfitLoss);
            l_dblUnitAppraisalProfitLoss = l_bdUnitBuyAssetProfitLoss.add(l_bdUnitSellAssetProfitLoss).doubleValue();
            l_totalParIndexUnits[k].appraisalProfitLoss = WEB3StringTypeUtility.formatNumber(l_dblUnitAppraisalProfitLoss);
            
            //�w���ʎc�����v.�]�����v���v(���o�) = �w���ʎc�����v.�����ʕ]�����v���v(���o�)
            //+ �w���ʎc�����v.�����ʕ]�����v���v(���o�)
            BigDecimal l_bdUnitBuyAssetProfitLossCost = new BigDecimal(l_totalParIndexUnits[k].buyAssetProfitLossCost);
            BigDecimal l_bdUnitSellAssetProfitLossCost = new BigDecimal(l_totalParIndexUnits[k].sellAssetProfitLossCost);
            l_dblUnitAppraisalProfitLossCost =
                l_bdUnitBuyAssetProfitLossCost.add(l_bdUnitSellAssetProfitLossCost).doubleValue();
            l_totalParIndexUnits[k].appraisalProfitLossCost = 
            	WEB3StringTypeUtility.formatNumber(l_dblUnitAppraisalProfitLossCost);
        }
        l_response.futuresOptionsBalRefTotalParIndexUnits = l_totalParIndexUnits;
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get�⏕����)<BR>
     * �����̐敨�^�I�v�V�����敪�ɊY������⏕�������擾����B<BR>
     * <BR>
     * �P�j�@@�p�����[�^.�敨�^�I�v�V�����敪�ɂ��A�⏕�������擾����B<BR>
     * �@@[�p�����[�^.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ]<BR>
     * �@@�@@this.get�⏕����()���\�b�h���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@[get�⏕����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�⏕�����^�C�v�F�@@SubAccountTypeEnum.�؋�������<BR>
     * <BR>
     * �@@[�p�����[�^.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�@@�@@���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B<BR>
     * �@@�@@�A�@@����ID�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�B�@@�ڋq�I�u�W�F�N�g.getOP��������^�C�v()���\�b�h���R�[������B<BR>
     * �@@�@@�C�@@this.get�⏕����()���\�b�h���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * �@@�@@�@@[get�⏕����()�ɃZ�b�g����p�����[�^]<BR>
     * �@@�@@�@@�@@�⏕�����^�C�v�F�@@�B�̖߂�l<BR>
     * @@param l_strFuOpDiv - �敨�^�I�v�V�����敪<BR>
     * <BR>
     * 1�F�@@�敨<BR>
     * 2�F�@@�I�v�V����<BR>
     * @@return WEB3GentradeSubAccount
     * @@roseuid 41AAD19402C4
     */
    protected WEB3GentradeSubAccount getSubAccount(String l_strFuOpDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);
        //�P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B
        if (l_strFuOpDiv == null)
        {
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, STR_METHOD_NAME);
        }
        OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService) Services.getService(
                OpLoginSecurityService.class);
        //�P�j���O�C���Z�L�����e�B�T�[�r�X�������h�c���擾����B        
        long l_lngAccountId = l_opLoginSec.getAccountId();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        
        SubAccount  l_subAccount = null;
        try
        {
            //�A�@@����ID�ɊY������ڋq�I�u�W�F�N�g���擾����B
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);

            //�S�j�A�J�E���g�}�l�[�W��.getSubAccount(�⏕�����^�C�v)�ɂāA�Y���ڋq�̎w���I�v�V��������p�⏕�����I�u�W�F�N�g���擾����B
            //[�p�����[�^.�敨�^�I�v�V�����敪 == "�I�v�V����"�̏ꍇ]
            if (WEB3FuturesOptionDivDef.OPTION.equals(l_strFuOpDiv))
            {
                //�R�j�ڋq.getOP��������^�C�v()�ɂ��Y���ڋq�̕⏕�����^�C�v���擾����B
                SubAccountTypeEnum l_subAccountType = l_mainAccount.getOpSubAccountType();
                l_subAccount = l_mainAccount.getSubAccount(l_subAccountType);
            }
            //[�p�����[�^.�敨�^�I�v�V�����敪 == "�敨"�̏ꍇ]
            else if (WEB3FuturesOptionDivDef.FUTURES.equals(l_strFuOpDiv))
            {
                if (!l_mainAccount.isIfoAccountOpen(l_strFuOpDiv))
                {
                    log.debug(this.getClass().getName() + STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_00284);
                    //��O���X���[����
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00284, this.getClass().getName() + STR_METHOD_NAME);
                }
                l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            }
           
        }
        catch (NotFoundException l_nfe)
        {
            log.error(STR_METHOD_NAME, l_nfe);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,this.getClass().getName()+  "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);        
        return (WEB3GentradeSubAccount)l_subAccount;
    }
    
    /**
     * (create��������������)<BR>
     * ���N�G�X�g�f�[�^�����ƂɁA���������i<BR>
     * where�ȉ��w��̕�����j���쐬����B<BR>
     * <BR>
     * �P�j�p�����[�^.�敨OP���� == null�̏ꍇ�A<BR>
     * �@@null��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�ȊO�̏ꍇ�A�ȉ��̏������s���B<BR>
     * �@@�P�|�P�j��������������C���X�^���X(�FString)�𐶐�<BR>
     * �@@�P�|�Q�j����ID����������������ɒǉ�����B<BR>
     * <BR>
     * �@@�@@�@@�������������� += " and product_id = ? "<BR>
     * <BR>
     * �@@�P�|�R�j�쐬�������������������ԋp����B<BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return String
     * @@roseuid 41AAE01002D4
     */
    protected String createQueryString(WEB3IfoProductImpl l_ifoProduct) 
    {
        final String STR_METHOD_NAME = "createQueryString(String l_strProductCode) ";
        log.entering(STR_METHOD_NAME);
 
        //�p�����[�^.�����R�[�h��NULL�̏ꍇ�ANULL��ԋp����
        if (l_ifoProduct == null)
        {
            return null;
        }
 
        String l_strQueryString = " and product_id = ? ";
 
        log.exiting(STR_METHOD_NAME);
 
        return l_strQueryString;
    }
    
    /**
     * (create���������f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A���������i<BR>
     * where�ȉ��w��̕�����j��"?"�ɃZ�b�g����A<BR>
     * �p�����[�^���X�g���쐬����B<BR>
     * <BR>
     * �P�j�p�����[�^.�敨OP���� == null�̏ꍇ�Anull��ԋp����B<BR>
     * <BR>
     * �Q�j�P�j�ȊO�̏ꍇ�A�ȉ��̏��������{����B<BR>
     * �@@�Q�|�P�j����ID(*1)��0�Ԗڂ̗v�f�Ƃ��镶����z��𐶐�����B<BR>
     * �@@�Q�|�Q�j��������������z���ԋp����B<BR>
     * <BR>
     * (*1)�p�����[�^.�敨OP����.get����ID()�̖߂�l<BR>
     * <BR>
     * @@param l_ifoProduct - �敨OP�����I�u�W�F�N�g
     * @@return String[]
     * @@roseuid 41AAE01002F3
     */
     protected String[] createQueryContainer(WEB3IfoProductImpl l_ifoProduct)
     {
         final String STR_METHOD_NAME = "createQueryContainer(WEB3IfoProductImpl)";
         log.entering(STR_METHOD_NAME);
  
         //�p�����[�^.�敨OP������NULL�̏ꍇ�ANULL��ԋp����
         if (l_ifoProduct == null)
         {
             return null;
         }
         //�Q�|�P�j����ID��0�Ԗڂ̗v�f�Ƃ��镶����z��𐶐�����B
         String[] l_strQueryContainer = {Long.toString(l_ifoProduct.getProductId())};

         
         log.exiting(STR_METHOD_NAME);
         //�Q�|�Q�j��������������z���ԋp����B
         return l_strQueryContainer;
     }
    
    /**
     * (sort�c���Ɖ��)<BR>
     * �w�肳�ꂽ�\�[�g�L�[�A���~���Ɋ�Â��Ė��ׂ̃\�[�g���s���B<BR>
     * <BR>
     * �P�j�p�����[�^.�c���Ɖ�� == null�̏ꍇ�A<BR>
     * �@@�������I������B<BR>
     * <BR>
     * �Q�jArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�p�����[�^.�\�[�g�L�[�̗v�f�����ȉ��̏������J��Ԃ��B<BR>
     * �@@�R�|�P�j�\�[�g�L�[.�L�[���ڂ̒l�ɑΉ������r���ڂ�<BR>
     *       Comparator�𐶐����AArrayList�ɒǉ�����B <BR>
     * <BR>
     * �@@�@@�@@�@@�敨OP�c���Ɖ�Comparator�𐶐�����B<BR>
     * <BR>
     * �@@�@@�@@�@@[�R���X�g���N�^�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@�@@�@@�@@orderBy�F �\�[�g�L�[.�����^�~��<BR>
     * �@@�@@�@@�@@�@@��r���ځF�@@�\�[�g�L�[.�L�[����(*1)<BR>
     * <BR>
     * �@@�@@�@@�AArrayList�ɐ�������Comparator��ǉ�����B<BR>
     * <BR>
     * �S�jWEB3ArraysUtility.sort()���\�b�h���R�[������B <BR>
     * <BR>
     * �@@[sort()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@obj�F�@@�p�����[�^.�c���Ɖ��<BR>
     * �@@�@@com�F�@@��������ArrayList.toArray()�̖߂�l<BR>
     * <BR>
     * <BR>
     * (*1)�L�[���ڂ������̏ꍇ�A<BR>
     * �@@�@@����Comparator�A����敪Comparator�𐶐�����B<BR>
     * �@@�@@����敪Comparator��������orderBy�ɂ͌���Comparator��<BR>
     * �@@�@@�ݒ肳�ꂽ�l��ݒ肷�邱�ƁB<BR>
     * @@param l_balanceReferenceDetailUnit - �敨OP�c���Ɖ�ׂ̔z��
     * @@param l_sortKeys - �����w���I�v�V�����\�[�g�L�[�̔z��
     * @@roseuid 41AAE0100303
     */
     protected void sortBalanceReferenceDetailUnit(WEB3FuturesOptionsDetailUnit[] l_balanceReferenceDetailUnit, WEB3FuturesOptionsSortKey[] l_sortKeys) 
     {
         final String STR_METHOD_NAME = " sortBalanceReferenceDetailUnit(WEB3FuturesOptionsDetailUnit[] l_balanceReferenceDetailUnit, WEB3FuturesOptionsSortKey[] l_sortKeys)";
         log.entering(STR_METHOD_NAME);
  
         List l_lstComparators = new ArrayList();
         int l_intSortKeys = 0;
         if (l_sortKeys != null)
         {
             l_intSortKeys = l_sortKeys.length;
         }
  
         for (int i = 0; i < l_intSortKeys; i++)
         {
             String l_strKeyItem = l_sortKeys[i].keyItem;
             String l_strAscDesc = l_sortKeys[i].ascDesc;
  
             if (WEB3IfoKeyItemDef.OPEN_DATE.equals(l_strKeyItem))
             {
                 Comparator l_opOpenDateCom = new WEB3OptionsOpenDateComparator(l_strAscDesc);
                 Comparator l_opSessionTypeCom = new WEB3OptionsSessionTypeComparator(l_strAscDesc);
                 l_lstComparators.add(l_opOpenDateCom);
                 l_lstComparators.add(l_opSessionTypeCom);
             }

             Comparator l_com = new WEB3FuturesOptionsBalanceReferenceComparator(l_strAscDesc,l_strKeyItem);
             l_lstComparators.add(l_com);
         }
  
         Comparator[] l_comparators = new Comparator[l_lstComparators.size()];
         l_lstComparators.toArray(l_comparators);
  
         WEB3ArraysUtility.sort(l_balanceReferenceDetailUnit, l_comparators);
         log.exiting(STR_METHOD_NAME);
     }
}
@
