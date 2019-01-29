head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPFindPaymentRequisitionServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������T�[�r�XImpl�N���X(WEB3AdminTPFindPaymentRequisitionServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/01/06 �x�� �a��(FLJ) �V�K�쐬
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionRow;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPFindPaymentRequisitionResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisition;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPFindPaymentRequisitionService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;

/**
 * �������������T�[�r�XImpl�N���X
 */
public class WEB3AdminTPFindPaymentRequisitionServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPFindPaymentRequisitionService
{
    /**
     * ���O�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPFindPaymentRequisitionServiceImpl.class);

    /**
     * @@roseuid 41DE22CE018C
     */
    public WEB3AdminTPFindPaymentRequisitionServiceImpl()
    {
    }

    /**
    * �������������������s���B
    *
    * �����������������N�G�X�g�̏ꍇ
    * �@@this.get���������ڋq��������()���\�b�h���R�[������B
    * @@param l_request - ���N�G�X�g
    * @@return webbroker3.common.message.WEB3GenResponse
    * @@roseuid 41C11FB5031A
    */
    public WEB3GenResponse execute(WEB3GenRequest l_request)
   		throws WEB3BaseException
   	{
        final String METHOD_NAME = "execute(WEB3GenRequest)";

        log.entering(METHOD_NAME);

        WEB3GenResponse l_response = null;

		if(l_request instanceof WEB3AdminTPFindPaymentRequisitionRequest)
		{
		    l_response =  this.findPaymentRequisitions((WEB3AdminTPFindPaymentRequisitionRequest)l_request);
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
    * �]�͐���@@�\�����������s���B
    *
    * �P�j�ȉ����`�F�b�N����B
    * �E�Ǘ��Ҍ���
    * �E�Ǘ��ҕ��X����
    * �E���ԃ`�F�b�N
    *
    * �Q�jthis.get��������Params�ꗗ()���Ă�.
    *
    * �R�j�]�͋@@�\���䌟�����ʃ��X�|���X���쐬��
    * �v���p�e�B�ɒl���Z�b�g����B
    *
    * �S�j���X�|���X��ԋp����B
    * @@param l_request - ���������������N�G�X�g
    * @@return WEB3AdminTPFindPaymentRequisitionResponse
    * @@roseuid 41C11F8A02FB
    */
   protected WEB3AdminTPFindPaymentRequisitionResponse findPaymentRequisitions(WEB3AdminTPFindPaymentRequisitionRequest l_request)
   {
       final String METHOD_NAME = "findPaymentRequisitions(WEB3AdminTPFindPaymentRequisitionRequest l_request)";

       WEB3AdminTPFindPaymentRequisitionResponse l_response = new WEB3AdminTPFindPaymentRequisitionResponse();

       try
       {
           //�P�j���N�G�X�g���͍��ڃ`�F�b�N�B
           l_request.validate();

           //�Q�j�ȉ����`�F�b�N����B
           //�E	�Ǘ��Ҍ���
           WEB3Administrator l_admin;
           l_admin = WEB3Administrator.getInstanceFromLoginInfo();
           l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);
           //�E�Ǘ��ҕ��X����
           for(int i = 0; i < l_request.branchCodes.length; i++)
           {
               l_admin.validateBranchPermission(l_request.branchCodes[i]);
           }

           //�Q�jthis.get��������Params�ꗗ()���Ă�
           List l_rows = this.getPaymentRequisitionParamsList(l_request, l_admin);

           //�R�j�]�͋@@�\���䌟�����ʃ��X�|���X���쐬��
           //�v���p�e�B�ɒl���Z�b�g����B
           if((l_rows == null) || (l_rows.size() < 1))
           {
               l_response.paymentRequisitions = new WEB3AdminTPPaymentRequisition[0];
           }
           else
           {
               WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();

               int l_intSize = l_rows.size();
	             l_response.paymentRequisitions = new WEB3AdminTPPaymentRequisition[l_intSize];
	             for(int i = 0; i < l_intSize; i++)
	             {
	                 PaymentRequisitionRow l_row = (PaymentRequisitionRow)l_rows.get(i);
	                 WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_row.getAccountId());
	                 WEB3AdminTPPaymentRequisition l_paymentRequisition = new WEB3AdminTPPaymentRequisition();
	                 l_paymentRequisition.branchCode = l_account.getBranch().getBranchCode();
	                 l_paymentRequisition.accountCode = l_account.getDisplayAccountCode();
	                 l_paymentRequisition.accountName = l_account.getDisplayAccountName();
	                 l_paymentRequisition.occurredDate = l_row.getOccurredDate();
                     l_paymentRequisition.paymentRequisitionDivision = l_row.getPaymentRequisitionDivision();
                     l_paymentRequisition.requisitionStatus = l_row.getRequisitionStatus();
                     l_paymentRequisition.paymentRequisitionAmount = l_row.getPaymentRequisitionAmount();
                     l_paymentRequisition.calclationSource = l_row.getCalclationSource();
	                 l_response.paymentRequisitions[i] = l_paymentRequisition;
	             }
           }

       }
       catch(Exception e)
       {
           //�J�X�^�}�C�Y��`�ς݃G���[�̏ꍇ
           if(e instanceof WEB3BaseException)
           {
               l_response.errorInfo = ((WEB3BaseException)e).getErrorInfo();
           }
           else
           {
               //�\�����ʃG���[
               l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
           }

           log.error(l_request, l_response.errorInfo.error_message, l_response.errorInfo, e);

       }
       return l_response;
   }

   /**
    * ���������e�[�u������ȉ��̏����Ō����������ʂ�ԋp����B
    *
    *�����F
    *[�������Ԏw��(�����J�n�� != null && �����I���� != null)�̏ꍇ]
    *�E �����J�n���@@=< ������ =< �����I����
    *
    *[���������敪�w��(���������敪 != null)�̏ꍇ]
    *�E ���������敪 in (���N�G�X�g.���������敪)
    *|[���ы敪�w��(���ы敪 != null)�̏ꍇ]
    *�E���ы敪 in (���N�G�X�g.���ы敪)
    *[�v�Z���敪�w��([�v�Z���敪 != null)�̏ꍇ]
    *�E[�v�Z���敪 in (���N�G�X�g.[�v�Z���敪)
    *[�ڋq�R�[�h != null�̏ꍇ]
    *�E����ID = get�ڋq()�̖߂�l.get����ID()
    *[���X�R�[�h != null�̏ꍇ]
    *�E���XID = get���X()�̖߂�l.get���XID()
    * @@param l_request - ���������������N�G�X�g
    * @@param l_admin - �Ǘ���
    * @@return List
    * @@roseuid 41BD557803D0
    */
    protected List getPaymentRequisitionParamsList(WEB3AdminTPFindPaymentRequisitionRequest l_request, WEB3Administrator l_admin)
     throws WEB3BaseException
	 {
        final String METHOD_NAME = "getPaymentRequisitionParamsList(WEB3AdminTPFindPaymentRequisitionRequest l_request, WEB3Administrator l_admin)";
        
        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();
	
        WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)GtlUtils.getAccountManager();
	
        //�ڋq�R�[�h != null�̂Ƃ�
        if(l_request.accountCode != null)
        {
            String l_strInstCode = l_admin.getInstitutionCode();
	
            //�P�ł��ڋq�����������ΐ���
            //�S��������Ȃ������ꍇ�G���[
            int l_intMissCount = 0;
            int l_intBranchLength = l_request.branchCodes.length;
	
            for(int i = 0; i < l_intBranchLength; i++)
            {
                try
                {           		
		           WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_accMgr.getMainAccount(l_strInstCode, l_request.branchCodes[i], l_request.accountCode);
		           if(i == 0)
		           {
			           l_sbWhere.append("account_id in (?");		               
		           }
		           else
		           {
			           l_sbWhere.append(", ?");		               
		           }
		           l_lisBindVars.add(new Long(l_account.getAccountId()));
                }
                //�ڋq�̑��݃`�F�b�N
                catch(WEB3BaseException e)
                {
                    log.debug("account not found. institution_code = " + l_strInstCode + " branch_code=" + l_request.branchCodes[i] + " account_code=" + l_request.accountCode);
					l_intMissCount++;
					log.debug("miss count=" + l_intMissCount + " branch length=" + l_intBranchLength);
					if(l_intMissCount >= l_intBranchLength)
					{
			   				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01987, METHOD_NAME);
					}
				}
            }
            if(l_sbWhere.length() > 0)
            {
                l_sbWhere.append(")");           	
            }
        }

        // [�������Ԏw��(�����J�n�� != null && �����I���� != null)�̏ꍇ]
	   // �E �����J�n���@@=< ������ =< �����I����
	   if((l_request.startDate != null) && (l_request.endDate != null))
	   {
			if(l_sbWhere.length() > 0)
			{
				l_sbWhere.append(" and ");
			}
			l_sbWhere.append("(occurred_date >= ? and occurred_date <= ?)");
			l_lisBindVars.add(l_request.startDate);
			l_lisBindVars.add(l_request.endDate);
	   }
	    
	   //[���������敪�w��(���������敪 != null)�̏ꍇ]
	   //�E ���������敪 in (���N�G�X�g.���������敪)
	   if(l_request.paymentRequisitionDivisions != null)
	   {
	       if(l_sbWhere.length() > 0)
	       {
	           l_sbWhere.append(" and ");
		   }
	       l_sbWhere.append("payment_requisition_division in (");
		   for(int i = 0; i < l_request.paymentRequisitionDivisions.length; i++)
		   {
		       if(i > 0)
		       {
		           l_sbWhere.append(",");
		       }
		       l_sbWhere.append("?");
		       l_lisBindVars.add(l_request.paymentRequisitionDivisions[i]);
		   }
		   if(l_sbWhere.length() > 0)
		   {
		       l_sbWhere.append(")");
	       }
	   }
	
	   //[���ы敪�w��(���ы敪 != null)�̏ꍇ]
	   //�E���ы敪 in (���N�G�X�g.���ы敪)
	   if(l_request.requisitionStatuses != null)
	   {
	       if(l_sbWhere.length() > 0)
	       {
	           l_sbWhere.append(" and ");
	       }
	       l_sbWhere.append("requisition_status in (");
		   for(int i = 0; i < l_request.requisitionStatuses.length; i++)
		   {
		       if(i > 0)
		       {
		           l_sbWhere.append(",");
		       }
		       l_sbWhere.append("?");
		       l_lisBindVars.add(l_request.requisitionStatuses[i]);
		   }
		   if(l_sbWhere.length() > 0)
		   {
		       l_sbWhere.append(")");
	       }
	   }
	
	   //[�v�Z���敪�w��([�v�Z���敪 != null)�̏ꍇ]
	   //�E[�v�Z���敪 in (���N�G�X�g.[�v�Z���敪)
	   if(l_request.calclationSources != null)
	   {
	       if(l_sbWhere.length() > 0)
	       {
	           l_sbWhere.append(" and ");
	       }
		   l_sbWhere.append("calclation_source in (");
		   for(int i = 0; i < l_request.calclationSources.length; i++)
		   {
		       if(i > 0)
		       {
		           l_sbWhere.append(",");
		       }
		       l_sbWhere.append("?");
		       l_lisBindVars.add(l_request.calclationSources[i]);
		   }
		   if(l_sbWhere.length() > 0)
		   {
		       l_sbWhere.append(")");
		       }
		   }
	
		   //[.���X�R�[�h  != null�̏ꍇ]
		   //�E���XID = get���X().get���XID()�̖߂�l
		   if(l_request.branchCodes != null)
		   {
		       if(l_sbWhere.length() > 0)
		       {
		           l_sbWhere.append(" and ");
		       }
		   	   l_sbWhere.append("branch_id in (");
			   for(int i = 0; i < l_request.branchCodes.length; i++)
			   {
			       long l_lngBranchId;
			       try
			       {
			           l_lngBranchId = l_accMgr.getBranch(l_admin.getInstitution(), l_request.branchCodes[i]).getBranchId();
			           if(i > 0)
			           {
			               l_sbWhere.append(",");
			           }
			           l_sbWhere.append("?");
			           l_lisBindVars.add(new Long(l_lngBranchId));
			       }
			       catch(NotFoundException nfe)
			       {
			           //�Y�����X�Ȃ�
			           throw new WEB3BusinessLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, METHOD_NAME);
			       }
			
			   }
			   if(l_sbWhere.length() > 0)
			   {
			       l_sbWhere.append(")");
			   }
		   }
	
		   final String l_strWhere = l_sbWhere.toString();
		   final Object[] l_bindVars = l_lisBindVars.toArray();
		   log.debug("l_strWhere=" + l_strWhere);
		   log.debug("l_bindVars=" + l_bindVars);
		
		       try
		       {
		           QueryProcessor l_qp = Processors.getDefaultProcessor();
		           return l_qp.doFindAllQuery(PaymentRequisitionRow.TYPE, l_strWhere, l_bindVars);
		       }
		       catch(DataException de)
		       {
		           log.error(de.getMessage(), de);
		           throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80003, METHOD_NAME, de.getMessage());
		       }
		   }

}
@
