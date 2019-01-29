head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPSearchAdvanceCustomerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ۏ؋��ێ�������/���֋������ڋq�����T�[�r�XImpl�N���X(WEB3AdminTPSearchAdvanceCustomerServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/02/08 asano(SCS) �V�K�쐬
Revision History : 2008/10/20 ������(���u) ���f��No.037
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.ArrayListPage;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.data.TpCalcResultEquityDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailDao;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailDao;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.define.WEB3TPDepositDivDef;
import webbroker3.tradingpoweradmin.data.RequisitionAccountEquityRow;
import webbroker3.tradingpoweradmin.data.RequisitionAccountMarginRow;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPCustomerAttributeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPMarkToMarketEndDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPProcessManagementIdDivDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerDetailUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchInputResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerSearchListResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceCustomerUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPAdvanceDetailUnit;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPSearchAdvanceCustomerService;
import webbroker3.util.WEB3LogUtility;

/**
 * �ۏ؋��ێ�������/���֋������ڋq�����T�[�r�XImpl�N���X
 */
public class WEB3AdminTPSearchAdvanceCustomerServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPSearchAdvanceCustomerService
{
    /**
     * (���O)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPSearchAdvanceCustomerServiceImpl.class);

    /**
     * (�f�o�b�Oison)
     */
    private static boolean DBG = log.ison();
    
    /**
     * (�R���X�g���N�^)
     */
    public WEB3AdminTPSearchAdvanceCustomerServiceImpl() 
    {    
    }
   
    /**
     * (exetute)<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uexecute�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
   		throws WEB3BaseException
   	{
        final String METHOD_NAME = "execute";

        log.entering(METHOD_NAME);
      
        //���X�|���X
        WEB3GenResponse l_response = null;

        //�ۏ؋��ێ�������/���֋������ڋq�������͉�ʕ\��
        if(l_request instanceof WEB3AdminTPAdvanceCustomerSearchInputRequest)
        {
            l_response =  getAdvancCustomerInput( (WEB3AdminTPAdvanceCustomerSearchInputRequest)l_request);           
        }
        else
        //�ۏ؋��ێ�������/���֋������ڋq�����ꗗ��ʕ\��
		if(l_request instanceof WEB3AdminTPAdvanceCustomerSearchListRequest)
		{
		    l_response =  getAdvanceCustomerList((WEB3AdminTPAdvanceCustomerSearchListRequest)l_request);           
		}
		else
        //�ۏ؋��ێ�������/���֋������ڋq��ʕ\��
        if(l_request instanceof WEB3AdminTPAdvanceCustomerDetailRequest)
        {
          l_response =  this.getAdvanceCustomerDetail((WEB3AdminTPAdvanceCustomerDetailRequest)l_request);           
        }
        else
		{
		    //�\�����ʃG���[
		    throw new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80002, METHOD_NAME, new IllegalArgumentException());           
		}
        
		log.exiting(METHOD_NAME);
		return l_response;
      
   	}

    /**
     * (get�ۏ؋��ێ�������/���֋������ڋq����)<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�ۏ؋��ێ�������/���֋������ڋq���́v�Q�ƁB<BR>
     * <BR>
     * @@param l_request 
     * @@return WEB3AdminTPAdvanceCustomerSearchInputResponse
     */
    protected WEB3AdminTPAdvanceCustomerSearchInputResponse getAdvancCustomerInput(WEB3AdminTPAdvanceCustomerSearchInputRequest l_request)
     throws WEB3BaseException 
    {
        final String METHOD_NAME = "getAdvancCustomerInput";
        log.entering(METHOD_NAME);

         //�Ǘ���
         WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
         //�Ǘ��Ҍ����`�F�b�N
         l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
         
         
         //�l�􂢏I���敪��擾
         String[] l_markToMarketEndDiv = getMarkToMarketEndDiv( l_admin );
         
         //���X�|���X
         WEB3AdminTPAdvanceCustomerSearchInputResponse l_response = (WEB3AdminTPAdvanceCustomerSearchInputResponse)l_request.createResponse();
         
         //�v���p�e�B�Z�b�g
         l_response.markToMarketEndDiv = l_markToMarketEndDiv;//�l�􂢏I���敪
         
         //���X�|���X�ԋp
         return l_response;
    }

    /**
     * (get�ۏ؋��ێ�������/���֋������ڋq�ꗗ)<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uget�ۏ؋��ێ�������/���֋������ڋq�ꗗ�v�Q�ƁB<BR>
     * <BR>
     * @@param l_request 
     * @@return WEB3AdminTPAdvanceCustomerSearchListResponse
     */
    protected WEB3AdminTPAdvanceCustomerSearchListResponse getAdvanceCustomerList(WEB3AdminTPAdvanceCustomerSearchListRequest l_request)
     throws WEB3BaseException 
    {
        final String METHOD_NAME = "getAdvanceCustomerList";
        log.entering(METHOD_NAME);

        //���N�G�X�g�̑����Ó����`�F�b�N
        l_request.validate();
        
         //�Ǘ���
         WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
         
         //�Ǘ��Ҍ����`�F�b�N
         l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
                  
        //�l�􂢏I���敪��擾
        String[] l_markToMarketEndDiv = getMarkToMarketEndDiv( l_admin );

         //�ۏ؋��ێ�������/���֋������ڋq���X�g
         List l_list = new ArrayList();
         
         //����R�[�h���X�g
         String[] branchCode = l_request.branchCode;
         
         //���X�R�[�h�������Ƀ\�[�g
         Arrays.sort( branchCode );
         
         int l_missCount = 0;
         for(int i=0; i<branchCode.length; i++ )
         {
             //���X�����`�F�b�N
             l_admin.validateBranchPermission( branchCode[ i ] );   
             
             //�l�􂢋敪�`�F�b�N
             try
             {
                 this.validateToMarketEndDiv(l_admin.getInstitutionCode(), branchCode[i], l_request.markToMarketDiv);                 
             }
             catch(WEB3BaseException se)
             {
                 l_missCount++;
                 if(l_missCount == branchCode.length)
                 {
                     throw se;
                 }                 
             }
             
         }
         
         ListPage l_lisRows = null; 
         WEB3AdminTPAdvanceCustomerUnit l_unit = null;
         
         //�����ڋq
         if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST ) )
         {
             //get���������ڋqParams�ꗗ<�����ڋq>
             l_lisRows = this.getRequisitionAccountEquityParamsList(l_admin, l_request);
             
             for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
             {
                 RequisitionAccountEquityRow l_row = (RequisitionAccountEquityRow)l_iter.next();
                 //�ۏ؋��ێ�������/���֋������ڋq���j�b�g
                 //get�ۏ؋��ێ�������/���֋������ڋq<�����ڋq>
                 l_unit = getAdvanceCustomerEquity(l_row);
                 if( l_unit != null )
                 {
                     l_list.add( l_unit );
                 }
             }                        
             
         }
         //�M�p�ڋq
         else
         {
             //get���������ڋqParams�ꗗ<�M�p�ڋq>
             l_lisRows = this.getRequisitionAccountMarginParamsList(l_admin, l_request);

             for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
             {
                 RequisitionAccountMarginRow l_row = (RequisitionAccountMarginRow)l_iter.next();

                 //�ۏ؋��ێ�������/���֋������ڋq���j�b�g
                 //get�ۏ؋��ێ�������/���֋������ڋq<�����ڋq>
                 l_unit = this.getAdvanceCustomerMargin(l_row);
                 if( l_unit != null )
                 {
                     l_list.add( l_unit );
                 }

             }
         }                        
        
         //���X�|���X
         WEB3AdminTPAdvanceCustomerSearchListResponse l_response = (WEB3AdminTPAdvanceCustomerSearchListResponse)l_request.createResponse();
                  
         //�v���p�e�B�Z�b�g
         l_response.markToMarketEndDiv = l_markToMarketEndDiv;//�l�􂢏I���敪
         l_response.adminAdvanceCustomerUnits = (WEB3AdminTPAdvanceCustomerUnit[])l_list.toArray(new WEB3AdminTPAdvanceCustomerUnit[l_list.size()]);//���֋��ڋq�ꗗ
         if(l_lisRows != null)
         {
             l_response.totalPages   = format(l_lisRows.totalPages());//���y�[�W��
             l_response.totalRecords = format(l_lisRows.totalSize());//�����R�[�h��
             l_response.pageIndex    = format(l_lisRows.pageNumber() + 1);//�\���y�[�W�ԍ�(index+1)
             
         }
         else
         {
             l_response.pageIndex    = l_request.pageIndex;
             l_response.totalPages   = "0";//���y�[�W��
             l_response.totalRecords = "0";//�����R�[�h��
         }
         
         log.exiting(METHOD_NAME);
         return l_response;             
         
    }
    
    /**
     * (get���������ڋq�ꗗ<�����ڋq>)<BR>
     * <BR>
     * ���������ڋq<�����ڋq>�e�[�u����茟���B<BR>
     * <BR>
     * @@param �،����ID
     * @@param ���X�R�[�h
     * @@param �ڋq�R�[�h
     * @@return List �ڋqParams���X�g
     */
    protected ListPage getRequisitionAccountEquityParamsList(WEB3Administrator l_admin, WEB3AdminTPAdvanceCustomerSearchListRequest l_request)
    	throws WEB3BaseException
    {
        final String METHOD_NAME = "getRequisitionAccountEquityParamsList";

        //��ʂ��痈��y�[�W�̃C���f�b�N�X�͂P����n�܂�l
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);
        
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //�Ǘ��ҏ����̏،����
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        log.debug("l_request.branchCode.length" + l_request.branchCode.length);
        
        //�����쐬
        //�،���ЃR�[�h
        l_sbWhere.append("institution_code = ? " );
        l_lisBindVars.add(l_institution.getInstitutionCode());
        
        //���X�R�[�h
        for(int i = 0; i < l_request.branchCode.length; i++)
        {
            if(i > 0)
            {
                l_sbWhere.append(", ");
            }
            else
            {
                l_sbWhere.append("and branch_code in (");
            }
            l_sbWhere.append("?");                    
            l_lisBindVars.add(l_request.branchCode[i]);
        }
        if(l_request.branchCode.length > 0)
            l_sbWhere.append(")");  

        //�ڋq�R�[�h
        if(l_request.customerCode != null)
        {
            int l_count = 0;
            for(int i = 0; i < l_request.branchCode.length; i++)
            {
                try
                {
                    MainAccount l_mainAccount = 
                        ((WEB3GentradeAccountManager)GtlUtils.getAccountManager()).getMainAccount(l_institution.getInstitutionCode(), l_request.branchCode[i], l_request.customerCode);
                    
                    log.debug("account=" + l_mainAccount.toString());
                    
                    if(l_count > 0)
                    {
                        l_sbWhere.append(",?");                                                
                    }
                    else
                    {
                        l_sbWhere.append(" and account_code in (?");                        
                    }
                    l_lisBindVars.add(l_mainAccount.getAccountCode());                    
                    l_count++;
                }
                catch(WEB3BaseException e)
                {
                    //�P�����ڋq��������Ȃ������ꍇ
                    //��̃��X�g�y�[�W��Ԃ�
                    if(l_count == 0)
                    {
                        log.debug("account not found. institutionCode=" + l_institution.getInstitutionCode() + "branchCode=" + l_request.branchCode[i] + "customerCode=" + l_request.customerCode);
                        return new ArrayListPage(Collections.EMPTY_LIST, l_pageSize, l_pageIndex);
                    }
                }
            }
            if(l_count > 0)
            {
                l_sbWhere.append(")");
            }
        }
        
        final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("branch_code, account_code asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(RequisitionAccountEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars);
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(RequisitionAccountEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex);
            }
            
        }
        catch(NumberFormatException ne)
        {
            log.error(ne.getMessage(), ne);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME, ne.getMessage());            
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
        }
    }

    /**
     * (get���������ڋq�ꗗ<�M�p�ڋq>)<BR>
     * <BR>
     * ���������ڋq<�M�p�ڋq>�e�[�u����茟���B<BR>
     * <BR>
     * @@param �،����ID
     * @@param ���X�R�[�h
     * @@param �ڋq�R�[�h
     * @@return List �ڋqParams���X�g
     */
    protected ListPage getRequisitionAccountMarginParamsList(WEB3Administrator l_admin, WEB3AdminTPAdvanceCustomerSearchListRequest l_request)
    	throws WEB3BaseException
    {
        final String METHOD_NAME = "getRequisitionAccountMarginParamsList";
        
        //��ʂ��痈��y�[�W�̃C���f�b�N�X�͂P����n�܂�l
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
        
        //�Ǘ��ҏ����̏،����
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        log.debug("l_request.branchCode.length" + l_request.branchCode.length);
        
        //�����쐬
        //�،���ЃR�[�h
        l_sbWhere.append("institution_code = ? " );
        l_lisBindVars.add(l_institution.getInstitutionCode());
        
        int l_count = 0;
        //���X�R�[�h
        for(int i = 0; i < l_request.branchCode.length; i++)
        {
            if(i > 0)
            {
                l_sbWhere.append(", ");
            }
            else
            {
                l_sbWhere.append("and branch_code in (");
            }
            l_sbWhere.append("?");                    
            l_lisBindVars.add(l_request.branchCode[i]);
        }
        if(l_request.branchCode.length > 0)
            l_sbWhere.append(")");  

        //�ڋq�R�[�h
        if(l_request.customerCode != null)
        {
            for(int i = 0; i < l_request.branchCode.length; i++)
            {
                try
                {
                    MainAccount l_mainAccount = 
                        ((WEB3GentradeAccountManager)GtlUtils.getAccountManager()).getMainAccount(l_institution.getInstitutionCode(), l_request.branchCode[i], l_request.customerCode);
                    
                    log.debug("account=" + l_mainAccount.toString());
                    
                    if(l_count > 0)
                    {
                        l_sbWhere.append(",?");                                                
                    }
                    else
                    {
                        l_sbWhere.append(" and account_code in (?");                        
                    }
                    l_lisBindVars.add(l_mainAccount.getAccountCode());                    
                    l_count++;
                }
                catch(WEB3BaseException e)
                {
                    //�P�����ڋq��������Ȃ������ꍇ
                    //��̃��X�g�y�[�W��Ԃ�
                    if(l_count == 0)
                    {
                        log.debug("account not found. institutionCode=" + l_institution.getInstitutionCode() + "branchCode=" + l_request.branchCode[i] + "customerCode=" + l_request.customerCode);
                        return new ArrayListPage(Collections.EMPTY_LIST, l_pageSize, l_pageIndex);
                    }
                }
            }
            if(l_count > 0)
            {
                l_sbWhere.append(")");
            }
        }
        
        //�l�􂢋敪
        if(l_request.markToMarketDiv != null)
        {
            l_sbWhere.append(" and mark_to_market_div = ?");
            l_lisBindVars.add(l_request.markToMarketDiv);
            
        }
        
        final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("branch_code, account_code asc");        
        try
        {                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(RequisitionAccountMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars);
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(RequisitionAccountMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex);
            }
            
        }
        catch(NumberFormatException ne)
        {
            log.error(ne.getMessage(), ne);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME, ne.getMessage());            
        }
        catch(DataException de)
        {
            log.error(de.getMessage(), de);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
        }

    }
    
   /**
    * (get�ۏ؋��ێ�������/���֋������ڋq�ڍ�)<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �uget�ۏ؋��ێ�������/���֋������ڋq�ڍׁv�Q�ƁB<BR>
    * <BR>
    * @@param l_request 
    * @@return WEB3AdminTPAdvanceCustomerDetailResponse
    */
   protected WEB3AdminTPAdvanceCustomerDetailResponse getAdvanceCustomerDetail(WEB3AdminTPAdvanceCustomerDetailRequest l_request) 
        throws WEB3BaseException
   {
       
       final String METHOD_NAME = "getAdvanceCustomerDetail";
       log.entering(METHOD_NAME);

        //���N�G�X�g�̑����Ó����`�F�b�N
        l_request.validate();

       //�Ǘ���
       WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //�Ǘ��Ҍ����`�F�b�N
        l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

        //���֋����׃��j�b�g�ꗗ(T+0�`T+5)
          WEB3AdminTPAdvanceDetailUnit[] l_detailUnits = new WEB3AdminTPAdvanceDetailUnit[ 6 ];
        
        //�����ڋq
        if(l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST))
        {
            //�]�͌v�Z����ID�擾
            long l_lngCalcResultId = Long.parseLong( l_request.calcResultId );            
            TpCalcResultEquityRow l_tpCalcResultEquityRow = this.getTpCalcResultEquityRow(l_lngCalcResultId);
            TpCalcResultEquityDetailRow l_tpCalcResultEquityDetailRow = this.getTpCalcResultEquityDetailRow(l_lngCalcResultId);
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)GtlUtils.getAccountManager().getSubAccount(l_tpCalcResultEquityRow.getAccountId(), SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);                
            }
            catch(NotFoundException ne)
            {
                log.error(ne.getMessage(), ne);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, ne.getMessage());
            }

            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            //���Y�]�͏��<�����ڋq>�擾
            WEB3TPTradingPowerCalcEquity l_calcEquity = l_service.getTradingPowerCalcEquity(l_subAccount, l_tpCalcResultEquityRow, l_tpCalcResultEquityDetailRow);

            //T+0�`T+5
            for(int i=0; i<=5; i++  )
            {
                //�a����c��
                double l_dblAccountBalance = l_calcEquity.getAccountBalance( i );
                //�������ϑ��
                double l_dblTodayExecutedAmount = l_calcEquity.getTodayExecutedAmount( i );                
                //�����������
                double l_dblTodayUnexecutedAmount = l_calcEquity.getTodayUnexecutedAmount( i );
                //���̑��S����
                double l_dblOtherRestraint = l_calcEquity.getOtherRestraint( i );
                //���v��S����
                double l_dblDayTradeRestraint = l_calcEquity.getDayTradeRestraint( i );
                //�a�莑�Y�]���z
                double l_dblTrustSecurityAsset = l_calcEquity.getTrustSecurityAsset( i );
                
                //�����q���c
                double l_dblRealAccountBalance = l_dblAccountBalance - l_dblTodayExecutedAmount
                                                - l_dblTodayUnexecutedAmount - l_dblOtherRestraint;
                                                
                //���֋�
                double l_dblDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance, 0d ) );
                
                //���ʗ��֋�
                double l_dblSpecialDebitAmount = 0d;
                if( l_dblDayTradeRestraint == 0d )
                {
                    l_dblSpecialDebitAmount = 0d;
                }
                else
                {
                    l_dblSpecialDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance - l_dblDayTradeRestraint, 0d ) );
                }
                
                //���֋����׃��j�b�g
                WEB3AdminTPAdvanceDetailUnit l_detailUnit = new WEB3AdminTPAdvanceDetailUnit();
            
                //�v���p�e�C�Z�b�g
                l_detailUnit.accountBalance        = format( l_dblAccountBalance - l_dblTodayExecutedAmount );//�a���
                l_detailUnit.todayUnexecutedAmount = format( l_dblTodayUnexecutedAmount );//�����[����
                l_detailUnit.dayTradeRestraint     = format( l_dblDayTradeRestraint );//���v��S����
                l_detailUnit.otherRestraint        = format( l_dblOtherRestraint );//���̑��S����
                l_detailUnit.securityAsset         = format( l_dblTrustSecurityAsset );//�a��،��]���z
                l_detailUnit.realAccountBalance    = format( l_dblRealAccountBalance );//�����q����c
                l_detailUnit.debitAmount           = format( l_dblDebitAmount );//���֋�
                l_detailUnit.specialDebitAmount    = format( l_dblSpecialDebitAmount );//���ʗ��֋�
            
                //�ꗗ�ɃZ�b�g
                l_detailUnits[ i ] = l_detailUnit;

                log.debug("���֋������ڋq�ڍ�<����>[" + i + "]=" + l_detailUnits[ i ]);
                
            }
   
        }
        //�M�p�ڋq
        else
        {
            //�]�͌v�Z����ID�擾
            long l_lngCalcResultId = Long.parseLong( l_request.calcResultId );            
            TpCalcResultMarginRow l_tpCalcResultMarginRow = this.getTpCalcResultMarginRow(l_lngCalcResultId);
            TpCalcResultMarginDetailRow l_tpCalcResultMarginDetailRow = this.getTpCalcResultMarginDetailRow(l_lngCalcResultId);
            
            log.debug("�擾�����]�͌v�Z����Params=" + l_tpCalcResultMarginRow);
            log.debug("�擾�����]�͌v�Z���ʏڍ�Params=" + l_tpCalcResultMarginDetailRow);
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = (WEB3GentradeSubAccount)GtlUtils.getAccountManager().getSubAccount(l_tpCalcResultMarginRow.getAccountId(), SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);                
            }
            catch(NotFoundException ne)
            {
                log.error(ne.getMessage(), ne);
                throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, ne.getMessage());
            }
            
            
            //����]�̓T�[�r�X
            WEB3TPTradingPowerService l_service = (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);
            
            //���Y�]�͏��<�M�p�ڋq>�擾
            WEB3TPTradingPowerCalcMargin l_calcMargin = l_service.getTradingPowerCalcMargin(l_subAccount, l_tpCalcResultMarginRow, l_tpCalcResultMarginDetailRow);

            log.debug("�擾�������Y�]�͏��<�M�p�ڋq>=" + l_calcMargin);
            
            //�]�͌v�Z�����擾
            WEB3TPCalcCondition l_condition = l_calcMargin.getCalcCondition();

            //��p�ቺ���擾
            String l_strSubstituteValuationDropRate = l_request.substituteValuationDropRate;
            BigDecimal l_dbSubstituteValuationDropRate = null;
            //��p�ቺ�����w�肳��Ă��Ȃ��ꍇ�A�[���Ƃ���
            if( l_strSubstituteValuationDropRate == null || l_strSubstituteValuationDropRate.equals("") )
            {
                l_dbSubstituteValuationDropRate = BigDecimal.valueOf(0);
            }
            else
            {
                l_dbSubstituteValuationDropRate = new BigDecimal( l_strSubstituteValuationDropRate );
        
            }            
            log.debug("��p�ቺ��=" + l_dbSubstituteValuationDropRate);

            //�ۏ؋��ێ���
            int l_intMarginMentenanceRate = l_condition.getMarginMentenanceRate();
            log.debug("�ۏ؋��ێ���=" + l_intMarginMentenanceRate);

            //�Œ�K�v�ۏ؋�
            double l_dblMinMarginDeposit = l_condition.getMinMarginDeposit();
            log.debug("�Œ�K�v�ۏ؋�=" + l_dblMinMarginDeposit);

            //�@@��Œ�K�v�ۏ؋�
            double l_dblLegalMinMarginDeposit = l_condition.getLegalMinMarginDeposit();
            log.debug("�@@��Œ�K�v�ۏ؋�=" + l_dblLegalMinMarginDeposit);
                    
            //T+0�`T+5
            for(int i=0; i<=5; i++ )
            {
                //�a����c��
                double l_dblAccountBalance = l_calcMargin.getAccountBalance( i );
                log.debug("�a����c��=" + l_dblAccountBalance);

                //�������ϑ��
                double l_dblTodayExecutedAmount = l_calcMargin.getTodayExecutedAmount( i );                
                log.debug("�������ϑ��=" + l_dblTodayExecutedAmount);

                //�����������
                double l_dblTodayUnexecutedAmount = l_calcMargin.getTodayUnexecutedAmount( i );
                log.debug("�����������=" + l_dblTodayUnexecutedAmount);

                //���̑��S����
                double l_dblOtherRestraint = l_calcMargin.getOtherRestraint( i );
                log.debug("���̑��S����=" + l_dblOtherRestraint);

                //���v��S����
                double l_dblDayTradeRestraint = l_calcMargin.getDayTradeRestraint( i );
                log.debug("���v��S����=" + l_dblDayTradeRestraint);

                //�����ۏ؋�
                double l_dbMarginAccountBalance = l_calcMargin.calcMarginAccountBalance( i );
                log.debug("�����ۏ؋�=" + l_dbMarginAccountBalance);

                //��p�،��]���z
                double l_dblSubstituteSecurityAsset = l_calcMargin.getSubstituteSecurityAsset( i );
                log.debug("��p�،��]���z=" + l_dblSubstituteSecurityAsset);

                //�����ό��ʕ]�����v
                double l_dblContractAssetProfitLoss = l_calcMargin.getContractAssetProfitLoss( i );
                log.debug("�����ό��ʕ]�����v=" + l_dblContractAssetProfitLoss);

                //���ʏ��o��
                double l_dblContractTotalCost = l_calcMargin.getContractTotalCost(i);
                log.debug("���ʏ��o��=" + l_dblContractTotalCost);
                
                //����n���ʌ��ϑ�
                double l_dblUndeliContractLoss = l_calcMargin.getUndeliContractLoss( i );
                log.debug("����n���ʌ��ϑ�=" + l_dblUndeliContractLoss);

                //����ۏ؋�
                double l_dblReceiptMarginDeposit = l_calcMargin.calcReceiptMarginDeposit( i );
                log.debug("����ۏ؋�=" + l_dblReceiptMarginDeposit);

                //�����K�v�ۏ؋�
                double l_dblCashMarginDeposit = l_calcMargin.getCashMarginDeposit( i );
                log.debug("�����K�v�ۏ؋�=" + l_dblCashMarginDeposit);

                //�����ό��ʑ��
                double l_dblContractAmount = l_calcMargin.getContractAmount( i );
                log.debug("�����ό��ʑ��=" + l_dblContractAmount);

                //�Ǐؗ]��
                double l_dblMarginCallPower = l_calcMargin.calcMarginCallPower( i );
                log.debug("�Ǐؗ]��=" + l_dblMarginCallPower);
                
                //�ۏ؋��a����=[����ۏ؋�(n) / �����ό��ʑ��]
                BigDecimal l_bdContractAmount = new BigDecimal( l_dblContractAmount );
                                
                double l_dblMarginDepositRate = 0.0d;
                if(l_dblReceiptMarginDeposit != 0.0d && l_dblContractAmount != 0.0d)
                {
                    BigDecimal l_bdMarginDepositRate = new BigDecimal(l_dblReceiptMarginDeposit / l_dblContractAmount * 100);
                    l_dblMarginDepositRate = l_bdMarginDepositRate.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                }
                log.debug("�ۏ؋��a����=" + l_dblMarginDepositRate);
                
                             
                //�����q���c
                double l_dblRealAccountBalance = l_dblAccountBalance - l_dblTodayExecutedAmount
                                                - l_dblTodayUnexecutedAmount - l_dblOtherRestraint;                                
                log.debug("�����q���c=" + l_dblRealAccountBalance);

                //���֋�
                double l_dblDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance, 0d ) );
                log.debug("���֋�=" + l_dblDebitAmount);
               
                //���ʗ��֋�
                double l_dblSpecialDebitAmount = 0d;
                if( l_dblDayTradeRestraint == 0d )
                {
                    l_dblSpecialDebitAmount = 0d;
                }
                else
                {
                    l_dblSpecialDebitAmount = Math.abs( Math.min( l_dblRealAccountBalance - l_dblDayTradeRestraint, 0d ) );
                }
                log.debug("���ʗ��֋�=" + l_dblSpecialDebitAmount);
                
                //�ۏ؋��ێ��K�v�z
                BigDecimal l_bdMarginMentenanceRate = new BigDecimal( l_intMarginMentenanceRate );
                BigDecimal l_bdMarginMaintenanceAmount = l_bdContractAmount.multiply( l_bdMarginMentenanceRate ).divide( BigDecimal.valueOf(100), 0, BigDecimal.ROUND_FLOOR );
                double l_dblMarginMaintenanceAmount = l_bdMarginMaintenanceAmount.doubleValue();
                log.debug("�ۏ؋��ێ��K�v�z=" + l_dblMarginMaintenanceAmount);
                
                //�ۏ؋������z
                double l_dblMarginClaimedAmount = Math.min( l_dblMarginCallPower, 0);
                log.debug("�ۏ؋������z=" + l_dblMarginClaimedAmount);

                //�ڋq����c���s����
                double l_dblAccountBalanceShortfall = Math.min( l_dblMarginClaimedAmount, Math.abs( Math.min( l_dblRealAccountBalance - l_dbMarginAccountBalance, 0) ) );
                log.debug("�ڋq����c���s����=" + l_dblAccountBalanceShortfall);
                
                //�����ۏ؋��s����
                double l_dblCashMarginShortfall = l_dblMarginClaimedAmount - l_dblAccountBalanceShortfall;
                log.debug("�����ۏ؋��s����=" + l_dblCashMarginShortfall);
                
                //��p�،��]���z<��p�]���ቺ���l��>
                BigDecimal l_bdSubstituteSecurityAsset = new BigDecimal( l_dblSubstituteSecurityAsset );
                BigDecimal SubstituteSecurityAssetIncDropRate
                     = l_bdSubstituteSecurityAsset.multiply( BigDecimal.valueOf(100).subtract( l_dbSubstituteValuationDropRate ) ).divide( BigDecimal.valueOf(100), 1, BigDecimal.ROUND_FLOOR);
                double l_dblSubstituteSecurityAssetIncDropRate = SubstituteSecurityAssetIncDropRate.doubleValue();
                log.debug("��p�،��]���z<��p�]���ቺ���l��>=" + l_dblSubstituteSecurityAssetIncDropRate);
                
                //�����ۏ؋�<��p�ቺ���l��>
                double l_dblPaidMarginDepositIncDropRate = l_dbMarginAccountBalance + l_dblSubstituteSecurityAssetIncDropRate;
                log.debug("�����ۏ؋�<��p�ቺ���l��>=" + l_dblPaidMarginDepositIncDropRate);
                
                //����ۏ؋�<��p�]���ቺ���l��>
                double l_dblReceiptMarginDepositIncDropRate = l_dblPaidMarginDepositIncDropRate + Math.min( l_dblContractAssetProfitLoss, 0d ) - l_dblContractTotalCost - l_dblUndeliContractLoss;
                log.debug("����ۏ؋�<��p�]���ቺ���l��>=" + l_dblReceiptMarginDepositIncDropRate);
                
                //�Ǐؗ]��<��p�]���ቺ���l��>
                double l_dblMarginCallPowerIncDropRate = Math.min( l_dblPaidMarginDepositIncDropRate - l_dblMinMarginDeposit,
                                                                    l_dblReceiptMarginDepositIncDropRate - Math.max( l_dblMarginMaintenanceAmount, l_dblLegalMinMarginDeposit) );
                log.debug("�Ǐؗ]��<��p�]���ቺ���l��>=" + l_dblMarginCallPowerIncDropRate);
                
                //�ۏ؋��a����<��p�]���ቺ���l��>                
                double l_dblMarginDepositRateIncDropRate = 0.0d;
                if(l_dblContractAmount != 0.0d && l_dblReceiptMarginDepositIncDropRate != 0.0d)
                {
                    BigDecimal l_bdMarginDepositRateIncDropRate = new BigDecimal(l_dblReceiptMarginDepositIncDropRate / l_dblContractAmount * 100);
                    l_dblMarginDepositRateIncDropRate = l_bdMarginDepositRateIncDropRate.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                }
                log.debug("�ۏ؋��a����<��p�]���ቺ���l��>=" + l_dblMarginDepositRateIncDropRate);

                //�ۏ؋������z<��p�]���ቺ���l��>
                double l_dblMarginClaimedAmountIncDropRate = Math.min( l_dblMarginCallPowerIncDropRate, 0 );
                log.debug("�ۏ؋������z<��p�]���ቺ���l��>=" + l_dblMarginClaimedAmountIncDropRate);

                //�ڋq����c���s���� = Min( �ۏ؋������z<��p�]���ቺ���l��>, Abs( Min(�����q���c - �����ۏ؋�, 0) ) )
                double l_dblAccountBalanceShortfallIncDropRate = Math.min( l_dblMarginClaimedAmountIncDropRate, Math.abs( Math.min( l_dblRealAccountBalance - l_dbMarginAccountBalance, 0) ) );
                log.debug("�ڋq����c���s����=" + l_dblAccountBalanceShortfallIncDropRate);
                
                //�����ۏ؋��s���� = �ۏ؋������z<��p�]���ቺ���l��> ? �ڋq����c���s����<��p�]���ቺ���l��>
                double l_dblCashMarginShortfallIncDropRate = l_dblMarginClaimedAmountIncDropRate - l_dblAccountBalanceShortfallIncDropRate;
                log.debug("�����ۏ؋��s����=" + l_dblCashMarginShortfallIncDropRate);
                
                //���֋����׃��j�b�g
                WEB3AdminTPAdvanceDetailUnit l_detailUnit = new WEB3AdminTPAdvanceDetailUnit();
            
                //�v���p�e�C�Z�b�g
                l_detailUnit.realAccountBalance = format( l_dblRealAccountBalance );//�����q���c
                l_detailUnit.cashDeposit = format( l_dblAccountBalance );//�����ۏ؋�
                l_detailUnit.todayUnexecutedAmount = format( l_dblTodayUnexecutedAmount );//�����[����
                l_detailUnit.dayTradeRestraint = format( l_dblDayTradeRestraint );//���v��S����
                l_detailUnit.otherRestraint = format( l_dblOtherRestraint );//���̑��S����
                l_detailUnit.marginAccountBalance = format( l_dblAccountBalance - l_dblTodayExecutedAmount - l_dblTodayUnexecutedAmount - l_dblOtherRestraint );//�g�p�\�����ۏ؋�
                l_detailUnit.securityAsset = format( l_dblSubstituteSecurityAsset );//��p�،��]���z
                l_detailUnit.contractAssetProfitLoss = format( l_dblContractAssetProfitLoss );//�����ό��ʕ]�����v
                l_detailUnit.contractTotalCost = format( l_dblContractTotalCost );//���ʏ��o��
                l_detailUnit.undeliContractLoss = format( l_dblUndeliContractLoss );//����n���ʌ��ϑ�
                l_detailUnit.receiptMarginDeposit = format( l_dblReceiptMarginDeposit );//����ۏ؋�
                l_detailUnit.marginMaintenanceAmount = format( l_dblMarginMaintenanceAmount );//�ۏ؋��ێ��K�v�z
                l_detailUnit.cashMarginDeposit = format( l_dblCashMarginDeposit );//�����K�v�ۏ؋�
                l_detailUnit.marginCallPower = format( l_dblMarginCallPower );//�Ǐؗ]��
                l_detailUnit.contractAmount = format( l_dblContractAmount );//���ʑ��
                l_detailUnit.marginDepositRate = format( l_dblMarginDepositRate );//�ۏ؋��a����
                l_detailUnit.debitAmount = format( l_dblDebitAmount );//���֋�
                l_detailUnit.specialDebitAmount = format( l_dblSpecialDebitAmount );//���ʗ��֋�
                l_detailUnit.marginClaimedAmount = format( l_dblMarginClaimedAmount );//�ۏ؋������z
                l_detailUnit.accountBalanceShortfall = format( l_dblAccountBalanceShortfall );//�ڋq����c���s����
                l_detailUnit.cashMarginShortfall = format( l_dblCashMarginShortfall );//�����ۏ؋��s����
                l_detailUnit.substituteSecurityAssetIncDropRate = format( l_dblSubstituteSecurityAssetIncDropRate );//��p�،��]���z<��p�]���ቺ���l��>
                l_detailUnit.receiptMarginDepositIncDropRate = format( l_dblReceiptMarginDepositIncDropRate );//����ۏ؋�<��p�]���ቺ���l��>
                l_detailUnit.marginCallPowerIncDropRate = format( l_dblMarginCallPowerIncDropRate );//�Ǐؗ]��<��p�]���ቺ���l��>
                l_detailUnit.marginDepositRateIncDropRate = format( l_dblMarginDepositRateIncDropRate );//�ۏ؋��a����<��p�]���ቺ���l��>
                l_detailUnit.accountBalanceShortfallIncDropRate = format( l_dblAccountBalanceShortfallIncDropRate );//�ڋq����c���s����<��p�]���ቺ���l��>
                l_detailUnit.cashMarginBalanceShortfallIncDropRate = format( l_dblCashMarginShortfallIncDropRate );//�����ۏ؋��s����<��p�]���ቺ���l��>
                l_detailUnit.marginClaimedAmountIncDropRate = format( l_dblMarginClaimedAmountIncDropRate );//�ۏ؋������z<��p�]���ቺ���l��>
                           
                //�ꗗ�ɃZ�b�g
                l_detailUnits[ i ] = l_detailUnit;
                
                log.debug("���֋������ڋq�ڍ�<�M�p>[" + i + "]=" + l_detailUnits[ i ]);
            
            }
               
        }
        
        //���X�|���X
        WEB3AdminTPAdvanceCustomerDetailResponse l_response = (WEB3AdminTPAdvanceCustomerDetailResponse)l_request.createResponse();
       
        //�v���p�e�B�Z�b�g
       l_response.advanceCustomerDetailUnits = l_detailUnits;
       
        //���X�|���X�ԋp
        log.exiting(METHOD_NAME);
        return l_response;                      
        
   }
   
   /**
    * (get���֋��ڋq<�����ڋq>)<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �uget���֋��ڋq<�����ڋq>�v�Q�ƁB<BR>
    * <BR>
    * @@param ����ID
    * @@return �ۏ؋��ێ�������/���֋������ڋq���j�b�g
    */
   protected WEB3AdminTPAdvanceCustomerUnit getAdvanceCustomerEquity(RequisitionAccountEquityRow l_requisitionRow)
   throws WEB3BaseException
   {
       //�ڋq���׈ꗗ
       WEB3AdminTPAdvanceCustomerDetailUnit[] l_detailUnits
            = new WEB3AdminTPAdvanceCustomerDetailUnit[ 6 ];

       //�v���p�e�B�Z�b�g
       //T+0
       l_detailUnits[0] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[0].realAccountBalance = format(l_requisitionRow.getRealAccountBalance0());//�����q��c
       l_detailUnits[0].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset0());//�a��،��]���z
       l_detailUnits[0].debitAmount        = format(l_requisitionRow.getDebitAmount0());//���֋�
       l_detailUnits[0].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount0());//���ʗ��֋�
       
       //T+1
       l_detailUnits[1] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[1].realAccountBalance = format(l_requisitionRow.getRealAccountBalance1());//�����q��c
       l_detailUnits[1].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset1());//�a��،��]���z
       l_detailUnits[1].debitAmount        = format(l_requisitionRow.getDebitAmount1());//���֋�
       l_detailUnits[1].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount1());//���ʗ��֋�
              
       //T+2
       l_detailUnits[2] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[2].realAccountBalance = format(l_requisitionRow.getRealAccountBalance2());//�����q��c
       l_detailUnits[2].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset2());//�a��،��]���z
       l_detailUnits[2].debitAmount        = format(l_requisitionRow.getDebitAmount2());//���֋�
       l_detailUnits[2].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount2());//���ʗ��֋�

       //T+3
       l_detailUnits[3] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[3].realAccountBalance = format(l_requisitionRow.getRealAccountBalance3());//�����q��c
       l_detailUnits[3].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset3());//�a��،��]���z
       l_detailUnits[3].debitAmount        = format(l_requisitionRow.getDebitAmount3());//���֋�
       l_detailUnits[3].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount3());//���ʗ��֋�

       //T+4
       l_detailUnits[4] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[4].realAccountBalance = format(l_requisitionRow.getRealAccountBalance4());//�����q��c
       l_detailUnits[4].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset4());//�a��،��]���z
       l_detailUnits[4].debitAmount        = format(l_requisitionRow.getDebitAmount4());//���֋�
       l_detailUnits[4].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount4());//���ʗ��֋�

       //T+5
       l_detailUnits[5] = new WEB3AdminTPAdvanceCustomerDetailUnit();
       l_detailUnits[5].realAccountBalance = format(l_requisitionRow.getRealAccountBalance5());//�����q��c
       l_detailUnits[5].trustSecurityAsset = format(l_requisitionRow.getSecurityAsset5());//�a��،��]���z
       l_detailUnits[5].debitAmount        = format(l_requisitionRow.getDebitAmount5());//���֋�
       l_detailUnits[5].specialDebitAmount = format(l_requisitionRow.getSpecialDebitAmount5());//���ʗ��֋�
       
       //���֋��ڋq���j�b�g
       WEB3AdminTPAdvanceCustomerUnit l_unit = new WEB3AdminTPAdvanceCustomerUnit();
       
       //�v���p�e�B�Z�b�g
       l_unit.calcResultId = String.valueOf(l_requisitionRow.getCalcResultEquityId());//�]�͌v�Z����ID
       l_unit.branchCode   = l_requisitionRow.getBranchCode();//���X�R�[�h
       l_unit.accountCode  = l_requisitionRow.getAccountCode();//�ڋq�R�[�h
       l_unit.accountName  = l_requisitionRow.getFamilyName();//�ڋq��
       l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();       
       l_unit.depositDiv = l_requisitionRow.getAssetEvaluationDiv();//�a��،��ڋq�敪
       l_unit.advanceCustomerDetailUnits = l_detailUnits;//���֋��ڋq���׈ꗗ

       log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
       log.debug("�ϊ��������֋��ڋq���j�b�g=" + l_unit.toString());
       
       return l_unit;     
       
   }
   
   /**
    * (get�ۏ؋��ێ�������/���֋������ڋq<�M�p�ڋq>)<BR>
    * <BR>
    * @@param ����ID
    * @@param �l�􂢋敪
    * @@param ��p�]���ቺ��
    * @@return �ۏ؋��ێ�������/���֋������ڋq���j�b�g
    */
   protected WEB3AdminTPAdvanceCustomerUnit getAdvanceCustomerMargin(RequisitionAccountMarginRow l_requisitionRow)
   throws WEB3BaseException
   {
       //�ڋq���׈ꗗ
       WEB3AdminTPAdvanceCustomerDetailUnit[] l_detailUnits
            = new WEB3AdminTPAdvanceCustomerDetailUnit[ 6 ];
       
       //�v���p�e�B�Z�b�g
       //���֋��ڋq���׃��j�b�g
       //T+0
       l_detailUnits[0] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[0].debitAmount             = format(l_requisitionRow.getDebitAmount0());//���֋�
       l_detailUnits[0].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount0());//���ʗ��֋�
       l_detailUnits[0].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit0());//����ۏ؋�
       l_detailUnits[0].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount0());//�ۏ؋��ێ��K�v�z
       l_detailUnits[0].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate0());//�ۏ؋��a����
       l_detailUnits[0].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount0());//�ۏ؋������z

       //T+1
       l_detailUnits[1] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[1].debitAmount             = format(l_requisitionRow.getDebitAmount1());//���֋�
       l_detailUnits[1].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount1());//���ʗ��֋�
       l_detailUnits[1].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit1());//����ۏ؋�
       l_detailUnits[1].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount1());//�ۏ؋��ێ��K�v�z
       l_detailUnits[1].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate1());//�ۏ؋��a����
       l_detailUnits[1].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount1());//�ۏ؋������z

       //T+2
       l_detailUnits[2] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[2].debitAmount             = format(l_requisitionRow.getDebitAmount2());//���֋�
       l_detailUnits[2].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount2());//���ʗ��֋�
       l_detailUnits[2].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit2());//����ۏ؋�
       l_detailUnits[2].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount2());//�ۏ؋��ێ��K�v�z
       l_detailUnits[2].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate2());//�ۏ؋��a����
       l_detailUnits[2].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount2());//�ۏ؋������z

       //T+3
       l_detailUnits[3] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[3].debitAmount             = format(l_requisitionRow.getDebitAmount3());//���֋�
       l_detailUnits[3].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount3());//���ʗ��֋�
       l_detailUnits[3].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit3());//����ۏ؋�
       l_detailUnits[3].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount3());//�ۏ؋��ێ��K�v�z
       l_detailUnits[3].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate3());//�ۏ؋��a����
       l_detailUnits[3].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount3());//�ۏ؋������z

       //T+4
       l_detailUnits[4] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[4].debitAmount             = format(l_requisitionRow.getDebitAmount4());//���֋�
       l_detailUnits[4].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount4());//���ʗ��֋�
       l_detailUnits[4].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit4());//����ۏ؋�
       l_detailUnits[4].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount4());//�ۏ؋��ێ��K�v�z
       l_detailUnits[4].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate4());//�ۏ؋��a����
       l_detailUnits[4].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount4());//�ۏ؋������z

       //T+5
       l_detailUnits[5] = new WEB3AdminTPAdvanceCustomerDetailUnit();           
       l_detailUnits[5].debitAmount             = format(l_requisitionRow.getDebitAmount5());//���֋�
       l_detailUnits[5].specialDebitAmount      = format(l_requisitionRow.getSpecialDebitAmount5());//���ʗ��֋�
       l_detailUnits[5].receiptMarginDeposit    = format(l_requisitionRow.getReceiptMarginDeposit5());//����ۏ؋�
       l_detailUnits[5].marginMaintenanceAmount = format(l_requisitionRow.getMarginMaintenanceAmount5());//�ۏ؋��ێ��K�v�z
       l_detailUnits[5].marginDepositRate       = format(l_requisitionRow.getMarginDepositRate5());//�ۏ؋��a����
       l_detailUnits[5].marginClaimedAmount     = format(l_requisitionRow.getMarginClaimedAmount5());//�ۏ؋������z
       
       //�ڋq���j�b�g
       WEB3AdminTPAdvanceCustomerUnit l_unit = new WEB3AdminTPAdvanceCustomerUnit();
       
       //�v���p�e�B�Z�b�g
       l_unit.calcResultId = String.valueOf(l_requisitionRow.getCalcResultMarginId());//�]�͌v�Z����ID
       l_unit.branchCode   = l_requisitionRow.getBranchCode();//���X�R�[�h
       l_unit.accountCode  = l_requisitionRow.getAccountCode();//�ڋq�R�[�h
       l_unit.accountName  = l_requisitionRow.getFamilyName();//�ڋq��
       l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();//���҃R�[�h       
       l_unit.depositDiv   = WEB3TPDepositDivDef.NOT_DEPOSIT_CUSTOMER;//�a��،��ڋq�敪
       l_unit.advanceCustomerDetailUnits = l_detailUnits;//���֋��ڋq���׈ꗗ
       
       log.debug("RequisitionAccountMarginRow=" + l_requisitionRow.toString());       
       log.debug("�ϊ������ڋq���j�b�g=" + l_unit.toString());
       
       return l_unit;     
       
   }
   

   /**
    * (get�l�􂢏I���敪)<BR>
    * <BR>
    * �V�[�P���X�}<BR>
    * �uget�l�􂢏I���敪�v�Q�ƁB<BR>
    * <BR>
    * @@param l_admin �Ǘ���
    * @@return String[] �l�􂢏I���敪
    * @@throws WEB3BaseException
    */   
   protected String[] getMarkToMarketEndDiv(WEB3Administrator l_admin) throws WEB3BaseException
   {
       //�،���ЃR�[�h
       String l_strInstitutionCode = l_admin.getInstitutionCode();
       
       //������l�􂢏I��count
       int l_intCount1;
       String l_strWhere1 = " process_id in (?, ?) and institution_code = ? and status = ? ";
       Object[] l_bindVars1 = {
                                WEB3AdminTPProcessManagementIdDivDef.NORMAL_END,//0002�F������l�􂢏I��
                                WEB3AdminTPProcessManagementIdDivDef.PROMPT_REPORT_NORMAL_END,//0004:���������l�􂢏I��
                                l_strInstitutionCode,
                                WEB3StatusDef.DEALT//1�F�����ς�
                              };              
       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_intCount1 = l_qp.doGetCountQuery( ProcessManagementRow.TYPE, l_strWhere1, l_bindVars1);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                        getClass().getName() + "getMarkToMarketEndDiv" );
       }

       //�O�����l�􂢏I��count
       int l_intCount2;
       String l_strWhere2 = " process_id = ? and institution_code = ? and status = ? ";
       Object[] l_bindVars2 = {
                                WEB3AdminTPProcessManagementIdDivDef.MORNING_CLOSED_END,//0003�F�O������l�􂢏I��
                                l_strInstitutionCode,
                                WEB3StatusDef.DEALT//1�F�����ς�
                              };              
       try
       {
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_intCount2 = l_qp.doGetCountQuery( ProcessManagementRow.TYPE, l_strWhere2, l_bindVars2);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                        getClass().getName() + "getMarkToMarketEndDiv" );
       }
       
       //�l�􂢏I���敪
       String[] l_strMarkToMarketEndDiv = null;
       
       if( l_intCount1 > 0 && l_intCount2 > 0 )
       {
           l_strMarkToMarketEndDiv = new String[2];
           l_strMarkToMarketEndDiv[0] = WEB3AdminTPMarkToMarketEndDivDef.NORMAL;//�����
           l_strMarkToMarketEndDiv[1] = WEB3AdminTPMarkToMarketEndDivDef.MORNING_CLOSED;//�O����
           log.debug("������O��������");

       }
       else
       if( l_intCount1 > 0 && l_intCount2 == 0 )
       {
           l_strMarkToMarketEndDiv = new String[1];
           l_strMarkToMarketEndDiv[0] = WEB3AdminTPMarkToMarketEndDivDef.NORMAL;//�����
           log.debug("��������");
       }
       else
       if( l_intCount1 == 0 && l_intCount2 > 0 )
       {
           l_strMarkToMarketEndDiv = new String[1];
           l_strMarkToMarketEndDiv[0] = WEB3AdminTPMarkToMarketEndDivDef.MORNING_CLOSED;//�O����
           log.debug("�O��������");
       }
       else
       {
           l_strMarkToMarketEndDiv = null;
           log.debug("�ꒆ");
       }
       
       
       return l_strMarkToMarketEndDiv;
   }
   
   /**
    * get�]�͌v�Z����<�����ڋq>
    * @@param l_epCalcResultEquityId�@@�]�͌v�Z����ID
    * @@return TpCalcResultEquityRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultEquityRow getTpCalcResultEquityRow(long l_epCalcResultEquityId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultEquityRow(long l_epCalcResultEquityId)";
       
       try
       {
           return TpCalcResultEquityDao.findRowByPk(l_epCalcResultEquityId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

   /**
    * get�]�͌v�Z���ʏڍ�<�����ڋq>
    * @@param l_epCalcResultEquityId�@@�]�͌v�Z����ID
    * @@return TpCalcResultEquityRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultEquityDetailRow getTpCalcResultEquityDetailRow(long l_epCalcResultEquityId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultEquityDetailRow(long l_epCalcResultEquityId)";
       
       try
       {
           return TpCalcResultEquityDetailDao.findRowByPk(l_epCalcResultEquityId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }
   
   /**
    * get�]�͌v�Z����<�M�p�ڋq>
    * @@param l_epCalcResultEquityId�@@�]�͌v�Z����ID
    * @@return TpCalcResultMarginRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultMarginRow getTpCalcResultMarginRow(long l_tpCalcResultMarginId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultMarginRow(long l_tpCalcResultMarginId)";
       
       try
       {
           return TpCalcResultMarginDao.findRowByPk(l_tpCalcResultMarginId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }

   /**
    * get�]�͌v�Z���ʏڍ�<�M�p�ڋq>
    * @@param l_tpCalcResultMarginId�@@�]�͌v�Z����ID
    * @@return TpCalcResultMarginRow
    * @@throws WEB3BaseException
    */
   protected TpCalcResultMarginDetailRow getTpCalcResultMarginDetailRow(long l_tpCalcResultMarginId)
   throws WEB3BaseException 
   {
       final String METHOD_NAME = "getTpCalcResultMarginDetailRow(long l_tpCalcResultMarginId)";
       
       try
       {
           return TpCalcResultMarginDetailDao.findRowByPk(l_tpCalcResultMarginId);
       }
       catch(DataException de)
       {
           log.error(de.getMessage(), de);
           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
       }
   }
   
   public void validateToMarketEndDiv(String l_institutionCode, String l_branchCode, String l_markToMarketEndDiv)
   throws WEB3BaseException
   {
       final String METHOD_NAME = "validateToMarketEndDiv";
       
	   int l_count = 0;
       try
       {
           String l_strWhere = null;
           Object[] l_bindVars;
           if(WEB3AdminTPMarkToMarketEndDivDef.MORNING_CLOSED.equals(l_markToMarketEndDiv))
           {       
       	       l_strWhere = "process_id = ? and institution_code = ? and branch_code = ?";
       	       l_bindVars = new Object[3];
       	       l_bindVars[0] = WEB3AdminTPProcessManagementIdDivDef.MORNING_CLOSED_END;
       	       l_bindVars[1] = l_institutionCode;
    	       l_bindVars[2] = l_branchCode;
           }
           
           else if(WEB3AdminTPMarkToMarketEndDivDef.NORMAL.equals(l_markToMarketEndDiv))
           {   
               int missCount = 0;
       	       l_strWhere = "process_id in (?, ?) and institution_code = ? and branch_code = ?";
       	       l_bindVars = new Object[4];
    	       l_bindVars[0] = WEB3AdminTPProcessManagementIdDivDef.NORMAL_END;
    	       l_bindVars[1] = WEB3AdminTPProcessManagementIdDivDef.PROMPT_REPORT_NORMAL_END;
    	       l_bindVars[2] = l_institutionCode;
    	       l_bindVars[3] = l_branchCode;
               
           }
           else
           {
               throw new WEB3SystemLayerException(
                       WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME);                           
           }
           
           QueryProcessor l_qp = Processors.getDefaultProcessor();
           l_count = l_qp.doGetCountQuery(ProcessManagementRow.TYPE, l_strWhere, l_bindVars);
                      
       }
   	   catch(DataException e)
   	   {
           throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME);            
   	   }
       
   	   if(l_count < 1)
   	   {
   	       throw new WEB3SystemLayerException(
   	               WEB3ErrorCatalog.SYSTEM_ERROR_80017, METHOD_NAME);               	       
   	   }


   }
   
   
   
   
   /**
    * double�^��String�`���Ƀt�H�[�}�b�g����B<BR>
    * <BR>
    * @@param param
    * @@return String
    */
   private String format(double l_param)
   {       
       NumberFormat l_nf = NumberFormat.getInstance();
       l_nf.setGroupingUsed( false );
       return l_nf.format( l_param ); 
   }
   
}
@
