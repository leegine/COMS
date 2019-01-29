head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.06.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ������T�[�r�XImpl�N���X(WEB3AdminTPPaymentRequisitionManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/03/13 �{�{ �ē�(SCS) �V�K�쐬
				   2006/03/27 �{�{ �ē�(SCS) ���������y�у\�[�g�����̒ǉ�
Revision History : 2008/10/20 �����i���u�j���f��No.032
*/
package webbroker3.tradingpoweradmin.service.delegate.stdimpls;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.fitechlabs.dbind.ArrayListPage;
import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.dbind.RowType;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageSearchResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryRequest;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryResponse;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageDetailUnit;
import webbroker3.tradingpoweradmin.message.WEB3AdminTPPaymentRequisitionManageHistoryUnit;
import webbroker3.tradingpoweradmin.service.delegate.WEB3AdminTPPaymentRequisitionManageService;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityRow;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionEquityParams;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginRow;
import webbroker3.tradingpoweradmin.data.PaymentRequisitionMarginParams;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPCustomerAttributeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPTransactionCategoryCodeDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPPaymentRequisitionManageDef;
import webbroker3.util.WEB3LogUtility;

/**
 * ���������Ǘ������T�[�r�XImpl
 */
public class WEB3AdminTPPaymentRequisitionManageServiceImpl extends WEB3ClientRequestService implements WEB3AdminTPPaymentRequisitionManageService 
{
    
    /**
     * (���O)
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionManageServiceImpl.class);

    /**
     * @@roseuid 4412A98E0083
     */
    public WEB3AdminTPPaymentRequisitionManageServiceImpl() 
    {
     
    }
    
    /**
     * (get���������Ǘ��ڋqParams�ꗗ<�����ڋq>)<BR>
     * <BR>
     * ���������Ǘ��ꗗ����������ׂ̃p�����[�^��<BR>
     * ���������Ǘ��e�[�u��(����)�̌������s���B<BR>
     * <BR>
     * �ȉ��̏����œ��������Ǘ��e�[�u���i�����j���猟�����s���B<BR>
     * �ڋq�R�[�h == null �̏ꍇ�͓����������������Ă���ڋq���擾����B<BR>
     * <BR>
     * �E�v�Z�� = �Ɩ����t�iTradingSystemImpl.getBizDate()�j<BR>
     * <BR>
     * �E�،���ЃR�[�h = ���������Ǘ��ꗗ���N�G�X�g.��ЃR�[�h<BR>
     * <BR>
     * �E���X�R�[�h = ���������Ǘ��ꗗ���N�G�X�g.���X�R�[�h<BR>
     * <BR>
     * �E�����R�[�h like ���������Ǘ��ꗗ���N�G�X�g.�ڋq�R�[�h + "%"<BR>
     * �i���ڋq�R�[�h != null �̏ꍇ�j<BR>
     * <BR>
     * �E(���������z(T+0) > 0 or ���������z(T+1) > 0 or ���������z(T+2) > 0 or �c���i����+0���j < 0)<BR>
     * �i���ڋq�R�[�h == null �̏ꍇ�j<BR>
     * <BR>
     * �E���҃R�[�h = ���������Ǘ��ꗗ���N�G�X�g.���҃R�[�h<BR>
     * �i�����҃R�[�h != null �̏ꍇ�j<BR>
	 * <BR>
     * @@param WEB3Administrator - �Ǘ���
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - ���������Ǘ��ꗗ���N�G�X�g
     * @@return ListPage - �������ʃf�[�^
     * @@roseuid 44057950005F
     */
    public ListPage getPaymentRequisitionManageParamsListEquity(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageSearchRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsListEquity";

        //��ʂ��痈��y�[�W�̃C���f�b�N�X�͂P����n�܂�l
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

        //�Ɩ����t���擾����B
        Date l_bizDate0 = tradingSystem.getBizDate();

        //�Ǘ��ҏ����̏،����
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //�����쐬
        //�v�Z��
        l_sbWhere.append(" calc_date = ? ");
        l_lisBindVars.add(l_bizDate0);

        //��ЃR�[�h
        l_sbWhere.append(" and institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //���X�R�[�h
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //�ڋq�R�[�h
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%' ");
            l_lisBindVars.add(l_request.accountCode);
        }

        //���҃R�[�h
        if(l_request.traderCode != null)
        {
            l_sbWhere.append(" and   sonar_trader_code = ? ");
            l_lisBindVars.add(l_request.traderCode);
        }

        //�ڋq�R�[�h�����������Ɋ܂܂�Ă��Ȃ��ꍇ�͓����������������Ă���ڋq�̂ݕ\������B
        if(l_request.accountCode == null)
        {
	        l_sbWhere.append(" and (payment_requisition_amount_0 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_1 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_2 > 0 ");
	        l_sbWhere.append(" or cash_balance0 < 0 )");
        }

    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("substr(account_id,9,6) asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(PaymentRequisitionEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionEquityParams.TYPE});
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(PaymentRequisitionEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex,new RowType[] {PaymentRequisitionEquityParams.TYPE});
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
     * (get���������Ǘ��ڋqParams�ꗗ<�M�p�ڋq>)<BR>
     * <BR>
     * ���������Ǘ��e�[�u��(�M�p)�̌������s���B<BR>
     * <BR>
     * �ȉ��̏����œ��������Ǘ��e�[�u�����猟���B<BR>
     * �ڋq�R�[�h == null �̏ꍇ�͓����������������Ă���ڋq���擾����B<BR>
     * �E�v�̎w��[20%�������]�A[30%�������]���w�肳��Ă����ꍇ�A<BR>
     * �E�v�Ɉ�v����ڋq��\������B<BR>
     * �i���ڋq�w��̏ꍇ�͓����������������Ă��Ȃ��Ă��\�����邪�A<BR>
     * �E�v���w�肳��Ă���ꍇ�͓E�v�Ɉ�v���Ȃ��ƕ\������Ȃ��B�j<BR>
     * <BR>
     * �E�v�Z�� = �Ɩ����t�iTradingSystemImpl.getBizDate()�j<BR>
     * <BR>
     * �E�،���ЃR�[�h = ���������Ǘ��ꗗ���N�G�X�g.��ЃR�[�h<BR>
     * <BR>
     * �E���X�R�[�h = ���������Ǘ��ꗗ���N�G�X�g.���X�R�[�h<BR>
     * <BR>
     * �E�����R�[�h like ���������Ǘ��ꗗ���N�G�X�g.�ڋq�R�[�h + "%"<BR>
     * �i���ڋq�R�[�h != null �̏ꍇ�j<BR>
     * <BR>
     * �E(���������z(T+0) > 0 or ���������z(T+1) > 0 or ���������z(T+2) > 0<BR>
     * �@@or �c���i����+0���j < 0)<BR>
     * �i���ڋq�R�[�h == null �̏ꍇ�j<BR>
     * <BR>
     * �E���҃R�[�h = ���������Ǘ��ꗗ���N�G�X�g.���҃R�[�h<BR>
     * �i�����҃R�[�h != null �̏ꍇ�j<BR>
     * <BR>
     * �E20%���ꔭ���� >= ���������Ǘ��ꗗ���N�G�X�g.�E�v����<BR>
     * �i�����������Ǘ��ꗗ���N�G�X�g.�E�v == 1�i20%����j &&<BR>
     * �@@���������Ǘ��ꗗ���N�G�X�g.�E�v���� == 3�i20%����w��ő�����j�̏ꍇ�j<BR>
     * <BR>
     * �E20%���ꔭ���� > ���������Ǘ��ꗗ���N�G�X�g.�E�v����<BR>
     * �i�����������Ǘ��ꗗ���N�G�X�g.�E�v == 1�i20%����j &&<BR>
     * �@@���������Ǘ��ꗗ���N�G�X�g.�E�v���� == 0�i�E�v�������ׂāj�̏ꍇ�j<BR>
     * <BR>
     * �E20%���ꔭ���� = ���������Ǘ��ꗗ���N�G�X�g.�E�v����<BR>
     * �i�����������Ǘ��ꗗ���N�G�X�g.�E�v == 1�i20%����j &&<BR>
     * �@@���������Ǘ��ꗗ���N�G�X�g.�E�v���� != (0 or 3)�̏ꍇ�j<BR>
     * <BR>
     * �E30%���ꔭ���� >= ���������Ǘ��ꗗ���N�G�X�g.�E�v����<BR>
     * �i�����������Ǘ��ꗗ���N�G�X�g.�E�v == 2�i30%����j &&<BR>
     * �@@���������Ǘ��ꗗ���N�G�X�g.�E�v���� == 8�i30%����w��ő�����j�̏ꍇ�j<BR>
     * <BR>
     * �E30%���ꔭ���� > ���������Ǘ��ꗗ���N�G�X�g.�E�v����<BR>
     * �i�����������Ǘ��ꗗ���N�G�X�g.�E�v == 2�i30%����j &&<BR>
     * �@@���������Ǘ��ꗗ���N�G�X�g.�E�v���� == 0�i�E�v�������ׂāj�̏ꍇ�j<BR>
     * <BR>
     * �E30%���ꔭ���� = ���������Ǘ��ꗗ���N�G�X�g.�E�v����<BR>
     * �i�����������Ǘ��ꗗ���N�G�X�g.�E�v == 2�i30%����j &&<BR>
     * �@@���������Ǘ��ꗗ���N�G�X�g.�E�v���� != (0 or 8)�̏ꍇ�j<BR>
     * <BR>
     * @@param WEB3Administrator - �Ǘ���
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - ���������Ǘ��ꗗ���N�G�X�g
     * @@return ListPage - �������ʃf�[�^
     * @@roseuid 4405B4FA031E
     */
    public ListPage getPaymentRequisitionManageParamsListMargin(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageSearchRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsListMargin";

        //��ʂ��痈��y�[�W�̃C���f�b�N�X�͂P����n�܂�l
        final int l_pageIndex = Integer.parseInt(l_request.pageIndex) -1;
        final int l_pageSize = Integer.parseInt(l_request.pageSize);

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        TradingSystemImpl tradingSystem = (TradingSystemImpl)GtlUtils.getTradingSystem();

        //�Ɩ����t���擾����B
        Date l_bizDate0 = tradingSystem.getBizDate();

        //�Ǘ��ҏ����̏،����
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //�����쐬
        //�v�Z��
        l_sbWhere.append(" calc_date = ? ");
        l_lisBindVars.add(l_bizDate0);

        //��ЃR�[�h
        l_sbWhere.append(" and institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //���X�R�[�h
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //�ڋq�R�[�h
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%' ");
            l_lisBindVars.add(l_request.accountCode);
        }

        //���҃R�[�h
        if(l_request.traderCode != null)
        {
            l_sbWhere.append(" and   sonar_trader_code = ? ");
            l_lisBindVars.add(l_request.traderCode);
        }

        //�E�v���w��Ȃ��ł͂Ȃ��ꍇ������ǉ�����B
        if(l_request.summary != WEB3AdminTPPaymentRequisitionManageDef.notSpecify)
        {
        	//���������̓K�p��20%���ꂪ�w�肳�ꂽ�ꍇ
        	if(l_request.summary.equals(WEB3AdminTPPaymentRequisitionManageDef.break20))
        	{
        		//�E�v�����̎w�肪20%����̎w��ő���������ꍇ
        		if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.max20SpecifyDay))
        		{
                	l_sbWhere.append(" and   BREAK20ELAPSED_DAYS >= ? ");
        		}
        		else if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.allSpecifyDay))
        		{
        			l_sbWhere.append(" and   BREAK20ELAPSED_DAYS > ? ");
        		}
        		else
        		{
                	l_sbWhere.append(" and   BREAK20ELAPSED_DAYS = ? ");
        		}
                l_lisBindVars.add(l_request.summaryDays);
        	}
        	//���������̓K�p��30%���ꂪ�w�肳�ꂽ�ꍇ
        	if(l_request.summary.equals(WEB3AdminTPPaymentRequisitionManageDef.break30))
        	{
        		//�E�v�����̎w�肪30%����̎w��ő���������ꍇ
        		if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.max30SpecifyDay))
        		{
                	l_sbWhere.append(" and   BREAK30ELAPSED_DAYS >= ? ");
        		}
        		else if(l_request.summaryDays.equals(WEB3AdminTPPaymentRequisitionManageDef.allSpecifyDay))
        		{
                	l_sbWhere.append(" and   BREAK30ELAPSED_DAYS > ? ");
        		}
        		else
        		{
                	l_sbWhere.append(" and   BREAK30ELAPSED_DAYS = ? ");
        		}
                l_lisBindVars.add(l_request.summaryDays);
        	}
        }

        //�ڋq�R�[�h�����������Ɋ܂܂�Ă��Ȃ��ꍇ�͓�����������������
        if(l_request.accountCode == null)
        {
	        l_sbWhere.append(" and (payment_requisition_amount_0 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_1 > 0 ");
	        l_sbWhere.append(" or payment_requisition_amount_2 > 0 ");
	        l_sbWhere.append(" or cash_balance0 < 0 )");
        }
        
    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("substr(account_id,9,6) asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            if(l_pageSize <= 0 || l_pageIndex < 0)
            {
                List results = l_qp.doFindAllQuery(PaymentRequisitionMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionMarginParams.TYPE});
                int size = results.size();
                return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
                
            } else
            {
                return l_qp.doFindAllQuery(PaymentRequisitionMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars, l_pageSize, l_pageIndex,new RowType[] {PaymentRequisitionMarginParams.TYPE});
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
     * (get���������Ǘ��ڋqParams����<�����ڋq>)
     * <BR>
     * �ȉ��̏����œ��������Ǘ��e�[�u�����猟���ڋq�̑S�f�[�^�������B
     * <BR>
     * [����]
     * �Ǘ���
     * ���������Ǘ��������N�G�X�g
     * <BR>
     * ���X�R�[�h�A�ڋq�R�[�h���ڋq�擾
     * �ڋqID = (�ڋq.�ڋqID)
     * @@param WEB3Administrator - �Ǘ���
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - ���������Ǘ��������N�G�X�g
     * @@return ListPage - �������ʃf�[�^
     * @@roseuid 44072C6F017A
     */
    public ListPage getPaymentRequisitionManageParamsHistoryEquity(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsHistoryEquity";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        //�Ǘ��ҏ����̏،����
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //�����쐬
        //��ЃR�[�h�A���X�R�[�h
        l_sbWhere.append(" account_id in ( ");
        l_sbWhere.append(" select account_id ");
        l_sbWhere.append(" from main_account ");
        l_sbWhere.append(" where institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //���X�R�[�h
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //�ڋq�R�[�h
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%'");
            l_lisBindVars.add(l_request.accountCode);
        }

        l_sbWhere.append(") ");

    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("calc_date asc");        
        try
        {
                        
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List results = l_qp.doFindAllQuery(PaymentRequisitionEquityRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionEquityParams.TYPE});
            int size = results.size();
            return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
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
    /**
     * (get���������Ǘ��ڋqParams����<�M�p�ڋq>)
     * <BR>
     * �ȉ��̏����œ��������Ǘ��e�[�u�����猟���ڋq�̑S�f�[�^�������B
     * <BR>
     * [����]
     * �Ǘ���
     * ���������Ǘ��������N�G�X�g
     * <BR>
     * ���X�R�[�h�A�ڋq�R�[�h���ڋq�擾
     * �ڋqID = (�ڋq.�ڋqID)
     * @@param WEB3Administrator - �Ǘ���
     * @@param WEB3AdminTPPaymentRequisitionManageHistoryRequest - ���������Ǘ��������N�G�X�g
     * @@return ListPage - �������ʃf�[�^
     * @@roseuid 44072C780216
     */
    public ListPage getPaymentRequisitionManageParamsHistoryMargin(WEB3Administrator l_admin, WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request)
	throws WEB3BaseException
    {
        final String METHOD_NAME = "getPaymentRequisitionManageParamsHistoryMargin";

        StringBuffer l_sbWhere = new StringBuffer();
        List l_lisBindVars = new ArrayList();

        //�Ǘ��ҏ����̏،����
        Institution l_institution = l_admin.getInstitution();
        
        log.debug("l_institution=" + l_institution.getInstitutionId());
        
        //�����쐬
        //��ЃR�[�h�A���X�R�[�h
        l_sbWhere.append(" account_id in ( ");
        l_sbWhere.append(" select account_id ");
        l_sbWhere.append(" from main_account ");
        l_sbWhere.append(" where institution_code = ? ");
        l_lisBindVars.add(l_request.institutionCode);

        //���X�R�[�h
        if(l_request.branchCode != null)
        {
            l_sbWhere.append(" and   branch_code = ? ");
            l_lisBindVars.add(l_request.branchCode);
        }
        
        //�ڋq�R�[�h
        if(l_request.accountCode != null)
        {
            l_sbWhere.append(" and   account_code like ? || '%' ");
            l_lisBindVars.add(l_request.accountCode);
        }

        l_sbWhere.append(") ");

    	final String l_strWhere = l_sbWhere.toString();
        final Object[] l_bindVars = l_lisBindVars.toArray();

        log.debug("l_strWhere=" + l_strWhere);
        for(int i = 0; i < l_bindVars.length; i++)
        {
            log.debug("l_bindVars[" + i + "]" + l_bindVars[i]);
        }
        
        String l_strOrderby = new String("calc_date asc");        
        try
        {
            final QueryProcessor l_qp = Processors.getDefaultProcessor();
            
            List results = l_qp.doFindAllQuery(PaymentRequisitionMarginRow.TYPE, l_strWhere, l_strOrderby, null, l_bindVars,new RowType[] {PaymentRequisitionMarginParams.TYPE});
            int size = results.size();
            return new ArrayListPage(results, size != 0 ? size : 1, 0, size);
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
     * (get���������Ǘ��ڋq�ꗗ<�����ڋq>)<BR>
     * <BR>
     * ���������Ǘ������ꗗParams���擾�����f�[�^����Ƀ��X�|���X�̍쐬���s���B
     * <BR>
     * [����]
     * ���������Ǘ������ꗗParams
     * <BR>
     * @@param l_request - ���������Ǘ������ꗗParams
     * @@return WEB3AdminTPPaymentRequisitionManageSearchResponse - ��������
     * @@roseuid 440579950205
     */
    protected WEB3AdminTPPaymentRequisitionManageSearchResponse getPaymentRequisitionManageList(WEB3AdminTPPaymentRequisitionManageSearchRequest l_request) 
    throws WEB3BaseException 
    {
        final String METHOD_NAME = "getPaymentRequisitionManageList";
        log.entering(METHOD_NAME);

        //���N�G�X�g�̑����Ó����`�F�b�N
        l_request.validate();

        //�Ǘ���
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N
        l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

        //���������ڋq���X�g
        List l_list = new ArrayList();

        ListPage l_lisRows = null; 
        WEB3AdminTPPaymentRequisitionManageUnit l_unit = null;

        //�����ڋq
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST ) )
        {
            //get���������ڋqParams�ꗗ<�����ڋq>
            l_lisRows = this.getPaymentRequisitionManageParamsListEquity(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionEquityRow l_row = (PaymentRequisitionEquityRow)l_iter.next();
                //���������Ǘ��ꗗ���j�b�g
                //get���������Ǘ��ꗗ<�����ڋq>
                l_unit = getPaymentRequisitionManageAccountListEquity(l_row);
                if( l_unit != null )
                {
                    l_list.add( l_unit );
                }
            }                        
        }
        
        //�M�p�ڋq
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.MARGIN_CUST ) )
        {
            //get���������ڋqParams�ꗗ<�M�p�ڋq>
            l_lisRows = this.getPaymentRequisitionManageParamsListMargin(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionMarginRow l_row = (PaymentRequisitionMarginRow)l_iter.next();
                //���������Ǘ��ꗗ���j�b�g
                //get���������Ǘ��ꗗ<�M�p�ڋq>
                l_unit = getPaymentRequisitionManageAccountListMargin(l_row);
                if( l_unit != null )
                {
                    l_list.add( l_unit );
                }
            }                        
        }
        
        //���X�|���X
        WEB3AdminTPPaymentRequisitionManageSearchResponse l_response = (WEB3AdminTPPaymentRequisitionManageSearchResponse)l_request.createResponse();
        l_response.manageUnits = (WEB3AdminTPPaymentRequisitionManageUnit[])l_list.toArray(new WEB3AdminTPPaymentRequisitionManageUnit[l_list.size()]);//���������Ǘ��ꗗ

        //�v���p�e�B�Z�b�g
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

        //���X�|���X�ԋp
        return l_response;
}
    
    /**
     * (get���������Ǘ��ڋq�ꗗ<�M�p�ڋq>)<BR>
     * <BR>
     * ���������Ǘ��M�p�ꗗParams���擾�����f�[�^����Ƀ��X�|���X�̍쐬���s���B
     * <BR>
     * [����]
     * ���������Ǘ��M�p�ꗗParams
     * <BR>
     * @@param l_request - ���������Ǘ��M�p�ꗗParams
     * @@return WEB3AdminTPPaymentRequisitionManageHistoryResponse - ��������
     * @@roseuid 440579950205
     */
    protected WEB3AdminTPPaymentRequisitionManageHistoryResponse getPaymentRequisitionManageHistory(WEB3AdminTPPaymentRequisitionManageHistoryRequest l_request) 
    throws WEB3BaseException 
    {
        final String METHOD_NAME = "getPaymentRequisitionManageHistory";
        log.entering(METHOD_NAME);

        //���X�R�[�h
        String branchCode = null;
        //�ڋq�R�[�h
        String accountCode = null;
        //�ڋq��
        String accountName = null;
        
        //���N�G�X�g�̑����Ó����`�F�b�N
        l_request.validate();

        //�Ǘ���
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();

        //�Ǘ��Ҍ����`�F�b�N
        l_admin.validateAuthority(WEB3AdminTPTransactionCategoryCodeDef.TRADINGPOWER_ADMIN, false);

        //���������ڋq���X�g
        List l_list = new ArrayList();

        ListPage l_lisRows = null; 
        WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit = null;

        //�����ڋq
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.EQUITY_CUST ) )
        {
            //get���������ڋqParams����<�����ڋq>
            l_lisRows = this.getPaymentRequisitionManageParamsHistoryEquity(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionEquityRow l_row = (PaymentRequisitionEquityRow)l_iter.next();
                //���������Ǘ��ꗗ���j�b�g
                //get���������Ǘ�����<�����ڋq>
                l_unit = getPaymentRequisitionManageAccountHistoryEquity(l_row);
                if( l_unit != null )
                {
                    branchCode   = format(l_row.getAccountId()).substring(5,8);//���X�R�[�h
                    accountCode   = format(l_row.getAccountId()).substring(8,14);//�ڋq�R�[�h
                    accountName   = l_row.getFamilyName();//�ڋq��
                    l_list.add( l_unit );
                }
            }                        
        }
        
        //�M�p�ڋq
        if( l_request.customerAttribute.equals( WEB3AdminTPCustomerAttributeDef.MARGIN_CUST ) )
        {
            //get���������ڋqParams����<�M�p�ڋq>
            l_lisRows = this.getPaymentRequisitionManageParamsHistoryMargin(l_admin, l_request);

            for(Iterator l_iter = l_lisRows.iterator(); l_iter.hasNext();)
            {
            	PaymentRequisitionMarginRow l_row = (PaymentRequisitionMarginRow)l_iter.next();
                //���������Ǘ��ꗗ���j�b�g
                //get���������Ǘ�����<�M�p�ڋq>
                l_unit = getPaymentRequisitionManageAccountHistoryMargin(l_row);
                if( l_unit != null )
                {
                    branchCode   = format(l_row.getAccountId()).substring(5,8);//���X�R�[�h
                    accountCode   = format(l_row.getAccountId()).substring(8,14);//�ڋq�R�[�h
                    accountName   = l_row.getFamilyName();//�ڋq��
                    l_list.add( l_unit );
                }
            }                        
        }        
        //���X�|���X
        WEB3AdminTPPaymentRequisitionManageHistoryResponse l_response = (WEB3AdminTPPaymentRequisitionManageHistoryResponse)l_request.createResponse();
        l_response.branchCode = branchCode;
        l_response.accountCode = accountCode;
        l_response.accountName = accountName;
        l_response.historyUnits = (WEB3AdminTPPaymentRequisitionManageHistoryUnit[])l_list.toArray(new WEB3AdminTPPaymentRequisitionManageHistoryUnit[l_list.size()]);//���������Ǘ�����

        //���X�|���X�ԋp
        return l_response;
    }
    
    /**
     * get���������Ǘ��ڋq�ꗗ<�����ڋq>
     * @@param �ڋq
     * @@param PaymentRequisitionEquityRow
     * @@return WEB3AdminTPPaymentRequisitionManageUnit
     * @@roseuid 4405BECE00DC
     */
    public WEB3AdminTPPaymentRequisitionManageUnit getPaymentRequisitionManageAccountListEquity(PaymentRequisitionEquityRow l_requisitionRow)
    throws WEB3BaseException
    {
    	//�]�͒�~�敪
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //���������Ǘ��ڍ�
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //�v���p�e�B�Z�b�g
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//�����敪
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//�a���
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR�q���c
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//���v��S����
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//���̑��S����

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//�����敪
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//�a���
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR�q���c
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//���v��S����
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//���̑��S����

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//�����敪
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//�a���
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR�q���c
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//���v��S����
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//���̑��S����

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//�����敪
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//�a���
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR�q���c
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//���v��S����
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//���̑��S����

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//�����敪
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//�a���
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR�q���c
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//���v��S����
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//���̑��S����

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//�����敪
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//�a���
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR�q���c
        l_detailUnits[5].dayTradeRestraint = null;//���v��S����
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//���̑��S����

        //���֋��ڋq���j�b�g
        WEB3AdminTPPaymentRequisitionManageUnit l_unit = new WEB3AdminTPPaymentRequisitionManageUnit();

        //�v���p�e�B�Z�b�g
        l_unit.branchCode   = format(l_requisitionRow.getAccountId()).substring(5,8);//���X�R�[�h
        l_unit.accountCode   = format(l_requisitionRow.getAccountId()).substring(8,14);//�ڋq�R�[�h
        l_unit.accountName   = l_requisitionRow.getFamilyName();//�ڋq��
        l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();//���҃R�[�h
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//�ڋq����
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//�����~�敪
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getIfoOpenPositionStop();//�敨OP�V�K���]�͋敪
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getPaymentStop();//�o���]�͋敪
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getOtherTradingStop();//���̑����i���t�]�͋敪
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.manageDetails = l_detailUnits;//���������Ǘ��ڍ�
        log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
        log.debug("�ϊ��������֋��ڋq���j�b�g=" + l_unit.toString());

        return l_unit;
    }
    
    /**
     * get���������Ǘ��ڋq�ꗗ<�M�p�ڋq>
     * @@param �ڋq
     * @@param PaymentRequisitionMarginRow
     * @@return WEB3AdminTPPaymentRequisitionManageUnit
     * @@roseuid 4406F324017A
     */
    public WEB3AdminTPPaymentRequisitionManageUnit getPaymentRequisitionManageAccountListMargin(PaymentRequisitionMarginRow l_requisitionRow) 
    {
    	//�]�͒�~�敪
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //���������Ǘ��ڍ�
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //�v���p�e�B�Z�b�g
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//�����敪
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//�a���
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR�q���c
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//���v��S����
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//���̑��S����
        l_detailUnits[0].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit0());//�ϑ��ۏ؋�
        l_detailUnits[0].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit0());//����ۏ؋��c
        l_detailUnits[0].marginDeposit = format(l_requisitionRow.getMarginDeposit0());//�K�v�ۏ؋�
        l_detailUnits[0].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit0());//�������K�v�ۏ؋�
        l_detailUnits[0].contractAmount = format(l_requisitionRow.getContractAmount0());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate0IsNull() == false)
        {
        	l_detailUnits[0].marginDepositRate = format(l_requisitionRow.getMarginDepositRate0());//�ۏ؋��a����
        }

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//�����敪
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//�a���
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR�q���c
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//���v��S����
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//���̑��S����
        l_detailUnits[1].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit1());//�ϑ��ۏ؋�
        l_detailUnits[1].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit1());//����ۏ؋��c
        l_detailUnits[1].marginDeposit = format(l_requisitionRow.getMarginDeposit1());//�K�v�ۏ؋�
        l_detailUnits[1].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit1());//�������K�v�ۏ؋�
        l_detailUnits[1].contractAmount = format(l_requisitionRow.getContractAmount1());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate1IsNull() == false)
        {
        	l_detailUnits[1].marginDepositRate = format(l_requisitionRow.getMarginDepositRate1());//�ۏ؋��a����
        }

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//�����敪
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//�a���
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR�q���c
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//���v��S����
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//���̑��S����
        l_detailUnits[2].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit2());//�ϑ��ۏ؋�
        l_detailUnits[2].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit2());//����ۏ؋��c
        l_detailUnits[2].marginDeposit = format(l_requisitionRow.getMarginDeposit2());//�K�v�ۏ؋�
        l_detailUnits[2].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit2());//�������K�v�ۏ؋�
        l_detailUnits[2].contractAmount = format(l_requisitionRow.getContractAmount2());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate2IsNull() == false)
        {
        	l_detailUnits[2].marginDepositRate = format(l_requisitionRow.getMarginDepositRate2());//�ۏ؋��a����
        }

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//�����敪
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//�a���
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR�q���c
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//���v��S����
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//���̑��S����
        l_detailUnits[3].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit3());//�ϑ��ۏ؋�
        l_detailUnits[3].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit3());//����ۏ؋��c
        l_detailUnits[3].marginDeposit = format(l_requisitionRow.getMarginDeposit3());//�K�v�ۏ؋�
        l_detailUnits[3].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit3());//�������K�v�ۏ؋�
        l_detailUnits[3].contractAmount = format(l_requisitionRow.getContractAmount3());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate3IsNull() == false)
        {
        	l_detailUnits[3].marginDepositRate = format(l_requisitionRow.getMarginDepositRate3());//�ۏ؋��a����
        }

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//�����敪
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//�a���
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR�q���c
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//���v��S����
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//���̑��S����
        l_detailUnits[4].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit4());//�ϑ��ۏ؋�
        l_detailUnits[4].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit4());//����ۏ؋��c
        l_detailUnits[4].marginDeposit = format(l_requisitionRow.getMarginDeposit4());//�K�v�ۏ؋�
        l_detailUnits[4].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit4());//�������K�v�ۏ؋�
        l_detailUnits[4].contractAmount = format(l_requisitionRow.getContractAmount4());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate4IsNull() == false)
        {
        	l_detailUnits[4].marginDepositRate = format(l_requisitionRow.getMarginDepositRate4());//�ۏ؋��a����
        }

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//�����敪
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//�a���
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR�q���c
        l_detailUnits[5].dayTradeRestraint = null;//���v��S����
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//���̑��S����
        l_detailUnits[5].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit5());//�ϑ��ۏ؋�
        l_detailUnits[5].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit5());//����ۏ؋��c
        l_detailUnits[5].marginDeposit = format(l_requisitionRow.getMarginDeposit5());//�K�v�ۏ؋�
        l_detailUnits[5].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit5());//�������K�v�ۏ؋�
        l_detailUnits[5].contractAmount = format(l_requisitionRow.getContractAmount5());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate5IsNull() == false)
        {
        	l_detailUnits[5].marginDepositRate = format(l_requisitionRow.getMarginDepositRate5());//�ۏ؋��a����
        }

        //���֋��ڋq���j�b�g
        WEB3AdminTPPaymentRequisitionManageUnit l_unit = new WEB3AdminTPPaymentRequisitionManageUnit();

        //�v���p�e�B�Z�b�g
        l_unit.branchCode   = format(l_requisitionRow.getAccountId()).substring(5,8);//���X�R�[�h
        l_unit.accountCode   = format(l_requisitionRow.getAccountId()).substring(8,14);//�ڋq�R�[�h
        l_unit.accountName   = l_requisitionRow.getFamilyName();//�ڋq��
        l_unit.traderCode   = l_requisitionRow.getSonarTraderCode();//���҃R�[�h
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//�ڋq����
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//�����~�敪
        }
        if(!l_requisitionRow.getMarginOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getMarginOpenPositionStop();//�M�p�V�K���]�͋敪
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getIfoOpenPositionStop();//�敨OP�V�K���]�͋敪
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getPaymentStop();//�o���]�͋敪
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[4] = l_requisitionRow.getOtherTradingStop();//���̑����i���t�]�͋敪
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.break20Day   = l_requisitionRow.getBreak20day();//20%���ꔭ����
        l_unit.break20ElapsedDays   = l_requisitionRow.getBreak20elapsedDays();//20%����o�ߓ���
        l_unit.break30Day   = l_requisitionRow.getBreak30day();//30%���ꔭ����
        l_unit.break30ElapsedDays   = l_requisitionRow.getBreak30elapsedDays();//30%����o�ߓ���
        l_unit.manageDetails = l_detailUnits;//���������Ǘ��ڍ�
        log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
        log.debug("�ϊ��������֋��ڋq���j�b�g=" + l_unit.toString());

        return l_unit;
    }
    
    /**
     * get���������Ǘ��ڋq����<�����ڋq>
     * @@param PaymentRequisitionEquityRow
     * @@return WEB3AdminTPPaymentRequisitionManageHistoryUnit
     * @@roseuid 44072D160293
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit getPaymentRequisitionManageAccountHistoryEquity(PaymentRequisitionEquityRow l_requisitionRow) 
    {
    	//�]�͒�~�敪
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //���������Ǘ��ڍ�
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //�v���p�e�B�Z�b�g
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//�����敪
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//�a���
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR�q���c
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//���v��S����
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//���̑��S����

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//�����敪
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//�a���
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR�q���c
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//���v��S����
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//���̑��S����

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//�����敪
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//�a���
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR�q���c
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//���v��S����
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//���̑��S����

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//�����敪
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//�a���
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR�q���c
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//���v��S����
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//���̑��S����

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//�����敪
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//�a���
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR�q���c
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//���v��S����
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//���̑��S����

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//�����敪
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//�a���
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR�q���c
        l_detailUnits[5].dayTradeRestraint = null;//���v��S����
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//���̑��S����

        //���֋��ڋq���j�b�g
        WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit = new WEB3AdminTPPaymentRequisitionManageHistoryUnit();

        //�v���p�e�B�Z�b�g
        l_unit.bizDate = l_requisitionRow.getCalcDate();//���t
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//�ڋq����
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//�����~�敪
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getIfoOpenPositionStop();//�敨OP�V�K���]�͋敪
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getPaymentStop();//�o���]�͋敪
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getOtherTradingStop();//���̑����i���t�]�͋敪
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.manageDetails = l_detailUnits;//���������Ǘ��ڍ�
        log.debug("RequisitionAccountEquityRow=" + l_requisitionRow.toString());       
        log.debug("�ϊ��������֋��ڋq���j�b�g=" + l_unit.toString());

        return l_unit;
    }
    
    /**
     * get���������Ǘ��ڋq����<�M�p�ڋq>
     * @@param PaymentRequisitionMarginRow
     * @@return WEB3AdminTPPaymentRequisitionManageHistoryUnit
     * @@roseuid 44072D2301D7
     */
    public WEB3AdminTPPaymentRequisitionManageHistoryUnit getPaymentRequisitionManageAccountHistoryMargin(PaymentRequisitionMarginRow l_requisitionRow) 
    {
    	//�]�͒�~�敪
        String[] l_TradingPowerStop = null;
        l_TradingPowerStop = new String[5];

        //���������Ǘ��ڍ�
    	WEB3AdminTPPaymentRequisitionManageDetailUnit[] l_detailUnits
             = new WEB3AdminTPPaymentRequisitionManageDetailUnit[ 6 ];

        //�v���p�e�B�Z�b�g
        //T+0
        l_detailUnits[0] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[0].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[0].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount0());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision0IsNull() == false)
        {
        	l_detailUnits[0].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision0());//�����敪
        }
        l_detailUnits[0].accountBalance = format(l_requisitionRow.getAccountBalance0());//�a���
        l_detailUnits[0].sonarAccountBalance = format(l_requisitionRow.getCashBalance0());//SONAR�q���c
        l_detailUnits[0].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint0());//���v��S����
        l_detailUnits[0].otherRestraint = format(l_requisitionRow.getOtherRestraint0());//���̑��S����
        l_detailUnits[0].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit0());//�ϑ��ۏ؋�
        l_detailUnits[0].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit0());//����ۏ؋��c
        l_detailUnits[0].marginDeposit = format(l_requisitionRow.getMarginDeposit0());//�K�v�ۏ؋�
        l_detailUnits[0].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit0());//�������K�v�ۏ؋�
        l_detailUnits[0].contractAmount = format(l_requisitionRow.getContractAmount0());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate0IsNull() == false)
        {
        	l_detailUnits[0].marginDepositRate = format(l_requisitionRow.getMarginDepositRate0());//�ۏ؋��a����
        }

        //T+1
        l_detailUnits[1] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[1].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[1].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount1());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision1IsNull() == false)
        {
        	l_detailUnits[1].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision1());//�����敪
        }
        l_detailUnits[1].accountBalance = format(l_requisitionRow.getAccountBalance1());//�a���
        l_detailUnits[1].sonarAccountBalance = format(l_requisitionRow.getCashBalance1());//SONAR�q���c
        l_detailUnits[1].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint1());//���v��S����
        l_detailUnits[1].otherRestraint = format(l_requisitionRow.getOtherRestraint1());//���̑��S����
        l_detailUnits[1].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit1());//�ϑ��ۏ؋�
        l_detailUnits[1].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit1());//����ۏ؋��c
        l_detailUnits[1].marginDeposit = format(l_requisitionRow.getMarginDeposit1());//�K�v�ۏ؋�
        l_detailUnits[1].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit1());//�������K�v�ۏ؋�
        l_detailUnits[1].contractAmount = format(l_requisitionRow.getContractAmount1());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate1IsNull() == false)
        {
        	l_detailUnits[1].marginDepositRate = format(l_requisitionRow.getMarginDepositRate1());//�ۏ؋��a����
        }

        //T+2
        l_detailUnits[2] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[2].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[2].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount2());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision2IsNull() == false)
        {
        	l_detailUnits[2].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision2());//�����敪
        }
        l_detailUnits[2].accountBalance = format(l_requisitionRow.getAccountBalance2());//�a���
        l_detailUnits[2].sonarAccountBalance = format(l_requisitionRow.getCashBalance2());//SONAR�q���c
        l_detailUnits[2].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint2());//���v��S����
        l_detailUnits[2].otherRestraint = format(l_requisitionRow.getOtherRestraint2());//���̑��S����
        l_detailUnits[2].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit2());//�ϑ��ۏ؋�
        l_detailUnits[2].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit2());//����ۏ؋��c
        l_detailUnits[2].marginDeposit = format(l_requisitionRow.getMarginDeposit2());//�K�v�ۏ؋�
        l_detailUnits[2].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit2());//�������K�v�ۏ؋�
        l_detailUnits[2].contractAmount = format(l_requisitionRow.getContractAmount2());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate2IsNull() == false)
        {
        	l_detailUnits[2].marginDepositRate = format(l_requisitionRow.getMarginDepositRate2());//�ۏ؋��a����
        }

        //T+3
        l_detailUnits[3] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[3].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[3].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount3());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision3IsNull() == false)
        {
        	l_detailUnits[3].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision3());//�����敪
        }
        l_detailUnits[3].accountBalance = format(l_requisitionRow.getAccountBalance3());//�a���
        l_detailUnits[3].sonarAccountBalance = format(l_requisitionRow.getCashBalance3());//SONAR�q���c
        l_detailUnits[3].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint3());//���v��S����
        l_detailUnits[3].otherRestraint = format(l_requisitionRow.getOtherRestraint3());//���̑��S����
        l_detailUnits[3].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit3());//�ϑ��ۏ؋�
        l_detailUnits[3].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit3());//����ۏ؋��c
        l_detailUnits[3].marginDeposit = format(l_requisitionRow.getMarginDeposit3());//�K�v�ۏ؋�
        l_detailUnits[3].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit3());//�������K�v�ۏ؋�
        l_detailUnits[3].contractAmount = format(l_requisitionRow.getContractAmount3());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate3IsNull() == false)
        {
        	l_detailUnits[3].marginDepositRate = format(l_requisitionRow.getMarginDepositRate3());//�ۏ؋��a����
        }

        //T+4
        l_detailUnits[4] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[4].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[4].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount4());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision4IsNull() == false)
        {
        	l_detailUnits[4].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision4());//�����敪
        }
        l_detailUnits[4].accountBalance = format(l_requisitionRow.getAccountBalance4());//�a���
        l_detailUnits[4].sonarAccountBalance = format(l_requisitionRow.getCashBalance4());//SONAR�q���c
        l_detailUnits[4].dayTradeRestraint = format(l_requisitionRow.getDayTradeRestraint4());//���v��S����
        l_detailUnits[4].otherRestraint = format(l_requisitionRow.getOtherRestraint4());//���̑��S����
        l_detailUnits[4].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit4());//�ϑ��ۏ؋�
        l_detailUnits[4].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit4());//����ۏ؋��c
        l_detailUnits[4].marginDeposit = format(l_requisitionRow.getMarginDeposit4());//�K�v�ۏ؋�
        l_detailUnits[4].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit4());//�������K�v�ۏ؋�
        l_detailUnits[4].contractAmount = format(l_requisitionRow.getContractAmount4());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate4IsNull() == false)
        {
        	l_detailUnits[4].marginDepositRate = format(l_requisitionRow.getMarginDepositRate4());//�ۏ؋��a����
        }

        //T+5
        l_detailUnits[5] = new WEB3AdminTPPaymentRequisitionManageDetailUnit();
        l_detailUnits[5].bizDate = l_requisitionRow.getCalcDate();//�v�Z��
        l_detailUnits[5].paymentRequisitionAmount = format(l_requisitionRow.getPaymentRequisitionAmount5());//���������z
        if(l_requisitionRow.getPaymentRequisitionDivision5IsNull() == false)
        {
        	l_detailUnits[5].paymentRequisitionDivision = format(l_requisitionRow.getPaymentRequisitionDivision5());//�����敪
        }
        l_detailUnits[5].accountBalance = format(l_requisitionRow.getAccountBalance5());//�a���
        l_detailUnits[5].sonarAccountBalance = format(l_requisitionRow.getCashBalance5());//SONAR�q���c
        l_detailUnits[5].dayTradeRestraint = null;//���v��S����
        l_detailUnits[5].otherRestraint = format(l_requisitionRow.getOtherRestraint5());//���̑��S����
        l_detailUnits[5].paidMarginDeposit = format(l_requisitionRow.getPaidMarginDeposit5());//�ϑ��ۏ؋�
        l_detailUnits[5].receiptMarginDepositRest = format(l_requisitionRow.getReceiptMarginDeposit5());//����ۏ؋��c
        l_detailUnits[5].marginDeposit = format(l_requisitionRow.getMarginDeposit5());//�K�v�ۏ؋�
        l_detailUnits[5].cashMarginDeposit = format(l_requisitionRow.getCashMarginDeposit5());//�������K�v�ۏ؋�
        l_detailUnits[5].contractAmount = format(l_requisitionRow.getContractAmount5());//���ʑ��
        if(l_requisitionRow.getMarginDepositRate5IsNull() == false)
        {
        	l_detailUnits[5].marginDepositRate = format(l_requisitionRow.getMarginDepositRate5());//�ۏ؋��a����
        }

        //���֋��ڋq���j�b�g
        WEB3AdminTPPaymentRequisitionManageHistoryUnit l_unit = new WEB3AdminTPPaymentRequisitionManageHistoryUnit();

        //�v���p�e�B�Z�b�g
        l_unit.bizDate = l_requisitionRow.getCalcDate();//���t
        l_unit.attribute   = l_requisitionRow.getAccountAttribute();//�ڋq����
        if(!l_requisitionRow.getTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[0] = l_requisitionRow.getTradingStop();//�����~�敪
        }
        if(!l_requisitionRow.getMarginOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[1] = l_requisitionRow.getMarginOpenPositionStop();//�M�p�V�K���]�͋敪
        }
        if(!l_requisitionRow.getIfoOpenPositionStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[2] = l_requisitionRow.getIfoOpenPositionStop();//�敨OP�V�K���]�͋敪
        }
        if(!l_requisitionRow.getPaymentStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[3] = l_requisitionRow.getPaymentStop();//�o���]�͋敪
        }
        if(!l_requisitionRow.getOtherTradingStop().equals(WEB3AdminTPPaymentRequisitionManageDef.DEFAULT))
        {
        	l_TradingPowerStop[4] = l_requisitionRow.getOtherTradingStop();//���̑����i���t�]�͋敪
        }
        l_unit.tradingPowerStop = l_TradingPowerStop;
        l_unit.break20Day = l_requisitionRow.getBreak20day();//20%���ꔭ����
        l_unit.break20ElapsedDays   = l_requisitionRow.getBreak20elapsedDays();//20%����o�ߓ���
        l_unit.break30Day   = l_requisitionRow.getBreak30day();//30%���ꔭ����
        l_unit.break30ElapsedDays   = l_requisitionRow.getBreak30elapsedDays();//30%����o�ߓ���
        l_unit.manageDetails = l_detailUnits;//���������Ǘ��ڍ�
        log.debug("RequisitionAccountMarginRow=" + l_requisitionRow.toString());       
        log.debug("�ϊ��������֋��ڋq���j�b�g=" + l_unit.toString());

        return l_unit;
    }

    /**
     * (exetute)<BR>
     * <BR>
     * �����ŗ^����ꂽ���N�G�X�g����ɋƖ��������s���A�������ʂ����X�|���X�ɐݒ肵�Ă�Ԃ��B<BR>
     * <BR>
     * �V�[�P���X�}�uexecute�v�Q�ƁB<BR>
     *  <BR>
     * @@param l_request - ���N�G�X�g
     * @@return �������ʂ��ݒ肳�ꂽ���X�|���X
     */
	public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException {

		
		final String METHOD_NAME = "execute";

        log.entering(METHOD_NAME);
      
        //���X�|���X
        WEB3GenResponse l_response = null;

        //���������Ǘ��ꗗ��ʕ\��
        if(l_request instanceof WEB3AdminTPPaymentRequisitionManageSearchRequest)
        {
            l_response =  getPaymentRequisitionManageList( (WEB3AdminTPPaymentRequisitionManageSearchRequest)l_request);           
        }
		else if(l_request instanceof WEB3AdminTPPaymentRequisitionManageHistoryRequest)
        //���������Ǘ�������ʕ\��
        {
            l_response =  getPaymentRequisitionManageHistory((WEB3AdminTPPaymentRequisitionManageHistoryRequest)l_request);           
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
