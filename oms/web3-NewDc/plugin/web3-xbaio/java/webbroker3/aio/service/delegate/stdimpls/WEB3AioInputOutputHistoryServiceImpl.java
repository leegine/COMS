head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputHistoryServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�ɗ����T�[�r�XImpl(WEB3AioInputOutputHistoryServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 䈋� (���u) �V�K�쐬
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.aio.data.SecReceiptDeliveryActionRow;
import webbroker3.aio.define.WEB3AioInputOutputSortkeyItemDef;
import webbroker3.aio.define.WEB3InformInputOutputGroupDef;
import webbroker3.aio.define.WEB3InformProductGroupDef;
import webbroker3.aio.message.WEB3AioHistoryUnit;
import webbroker3.aio.message.WEB3AioInputOutputHistoryListRequest;
import webbroker3.aio.message.WEB3AioInputOutputHistoryListResponse;
import webbroker3.aio.message.WEB3AioSortKeyUnit;
import webbroker3.aio.service.delegate.WEB3AioInputOutputHistoryService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���o�ɗ����T�[�r�XImpl)<BR>
 * ���o�ɗ����T�[�r�X�����N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryServiceImpl
    extends WEB3ClientRequestService
    implements WEB3AioInputOutputHistoryService
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        WEB3AioInputOutputHistoryServiceImpl.class);

    /**
     * @@roseuid 41EC855C0251
     */
    public WEB3AioInputOutputHistoryServiceImpl()
    {

    }

    /**
     * ���o�ɗ����T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o�ɗ����j������ʕ\���f�[�^�擾�v �Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41B7B6D901A1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        WEB3AioInputOutputHistoryListRequest l_listRequest = (WEB3AioInputOutputHistoryListRequest)l_request;
        try
        {
            //1.1validate( )
            l_listRequest.validate();
            //1.2validate������t�\( )(
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            
            //get�⏕����(�⏕�����^�C�v : SubAccountTypeEnum)
            // [����]
            //   �⏕�����^�C�v�F �h�a��������h
            SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.3create�擾����������(String, String, String)(
            //���i�O���[�v�F ���N�G�X�g�f�[�^.���i�O���[�v 
            //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h 
            //�o���O���[�v�F ���N�G�X�g�f�[�^.�o���O���[�v 
            String l_strGetCondString = this.createGetCondCharacterString(l_listRequest.productGroup,
                l_listRequest.productCode,
                l_listRequest.inputOutputGroup);
                
            //1.4���N�G�X�g�f�[�^.�\�����ԁi���j != null or (*1)-�@@ != null �̏ꍇ�A���{
            //   *1)���N�G�X�g�f�[�^.�\�����ԁi���j == null and ���N�G�X�g�f�[�^.�\�����ԁi���j == null �̏ꍇ
            //      �P�j���o�ɗ����e�[�u���̊Y���ڋq�̃��R�[�h�̂������߂̎�n��(=�@@)�ƁA
            //          ����1�����O�̓��t(=�A)���擾����B
            //      �Q�j�\�����ԁi���j = �A�A�\�����ԁi���j = �@@�Ƃ���B
            SecReceiptDeliveryActionRow l_actionRow = null;
            Date l_datDisplayStartDate = null;
            Date l_datDisplayEndDate = null;
            Date l_datFrom = null;
            Date l_datTo = null;
            WEB3AioHistoryUnit[] l_historyUnit = null;
            int l_intPageNumber = 0;
            int l_intTotalPages = 0;
            int l_intTotalSize = 0;
            QueryProcessor l_QueryProcessor = null; 
            OpLoginSecurityService l_opLoginSec = (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
            long l_lngAccountId = l_opLoginSec.getAccountId();
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_acc = l_accMgr.getMainAccount(l_lngAccountId);
            Object[] l_obj = new Object[3];
            l_obj[0] = new String(l_acc.getAccountCode().substring(0, 6));
            l_obj[1] = new String(l_acc.getInstitution().getInstitutionCode());
            l_obj[2] = new String(l_acc.getBranch().getBranchCode());

            l_QueryProcessor = Processors.getDefaultProcessor();
            List l_listSecReceiptDeliveryAction= l_QueryProcessor.doFindAllQuery(
                SecReceiptDeliveryActionRow.TYPE,
                " account_code like ? || '%' " +
                "and institution_code = ? " +
                "and branch_code = ? " +
                "and delivery_date is not null " +
                "and product_group is not null " +
                "and sec_receipt_delivery_group is not null " +
                "and io_group is not null",
                "delivery_date DESC nulls last ",
                null,
                l_obj);
            if (l_listSecReceiptDeliveryAction != null && l_listSecReceiptDeliveryAction.size() > 0)
            {
                l_actionRow = (SecReceiptDeliveryActionRow)l_listSecReceiptDeliveryAction.get(0); 
            }
            else
            {
                WEB3AioInputOutputHistoryListResponse l_response 
                    = (WEB3AioInputOutputHistoryListResponse)l_listRequest.createResponse();
                l_response.HistoryUnits = new WEB3AioHistoryUnit[0];
                l_response.pageIndex = "0";
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
                return l_response;
            }


            boolean l_blnResult = (l_actionRow.getDeliveryDate() != null);
            Calendar l_calendar = new GregorianCalendar();
            l_calendar.setTime(l_actionRow.getDeliveryDate());

            l_calendar.add(Calendar.MONTH,-1);
            l_datFrom = l_calendar.getTime();
            l_datTo = WEB3DateUtility.toDay(l_actionRow.getDeliveryDate());
            
            if ((l_listRequest.displayStartDate != null) || l_blnResult)
            {
                //1.4.1create�擾�����f�[�^�R���e�i(Date, Date, String, String, String)(
                //�\�����ԁi���j�F �i�ȉ��̂Ƃ���j
                //  ���N�G�X�g�f�[�^.�\�����ԁi���j != null �̏ꍇ�A���N�G�X�g�f�[�^.�\�����ԁi���j
                //  ���N�G�X�g�f�[�^.�\�����ԁi���j == null �̏ꍇ�A(*1)-�A
                //�\�����ԁi���j�F �i�ȉ��̂Ƃ���j
                //  ���N�G�X�g�f�[�^.�\�����ԁi���j != null �̏ꍇ�A���N�G�X�g�f�[�^.�\�����ԁi���j
                //  ���N�G�X�g�f�[�^.�\�����ԁi���j == null �̏ꍇ�A(*1)-�@@
                //���i�O���[�v�F ���N�G�X�g�f�[�^.���i�O���[�v
                //�����R�[�h�F ���N�G�X�g�f�[�^.�����R�[�h
                //�o���O���[�v�F ���N�G�X�g�f�[�^.�o���O���[�v

                if (l_listRequest.displayStartDate != null)
                {
                    l_datDisplayStartDate = l_listRequest.displayStartDate;
                }
                else
                {
                    l_datDisplayStartDate = l_datFrom;
                }
                if (l_listRequest.displayEndDate != null)
                {
                    l_datDisplayEndDate = l_listRequest.displayEndDate;
                }
                else
                {
                    l_datDisplayEndDate = l_datTo;
                }
                Object[] l_objCondDataContainer = this.createGetCondDataContainer(
                    l_subAccount,
                    l_datDisplayStartDate,
                    l_datDisplayEndDate,
                    l_listRequest.productGroup,
                    l_listRequest.productCode,
                    l_listRequest.inputOutputGroup);

                //1.4.2create�\�[�g����������(���o�ɗ����\�[�g�L�[[])(
                //�\�[�g�L�[�F ���N�G�X�g�f�[�^.�\�[�g�L�[
                String l_strSortCond = this.createSortCondString(l_listRequest.sortKeys);
                //1.4.3 getDefaultProcessor( )(
                //l_QueryProcessor
                //1.4.4 doFindAllQuery(RowType, String, String, String, Object[], int, int)(
                //Row�^�C�v�F ���o�ɗ���Row.TYPE
                //Where�F create�擾����������()�̖߂�l
                //orderBy�F create�\�[�g����������()�̖߂�l
                //condition�F null
                //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l
                //�y�[�W�T�C�Y�F ���N�G�X�g�f�[�^.�y�[�W���\���s��
                //�y�[�W�ԍ��F ���N�G�X�g�f�[�^.�v���y�[�W�ԍ� - 1
                ListPage l_listAction= l_QueryProcessor.doFindAllQuery(
                    SecReceiptDeliveryActionRow.TYPE,
                    l_strGetCondString,
                    l_strSortCond,
                    null,
                    l_objCondDataContainer,
                    Integer.parseInt(l_listRequest.pageSize),
                    (Integer.parseInt(l_listRequest.pageIndex) - 1));
                //1.4.5ArrayList( )
                List l_list = new ArrayList();
                //1.4.6�擾�������R�[�h����Loop����
                for (int i = 0; i < l_listAction.size(); i++)
                {
                    WEB3AioHistoryUnit l_aioHistoryUnit = new WEB3AioHistoryUnit();
                    SecReceiptDeliveryActionRow l_row = (SecReceiptDeliveryActionRow)l_listAction.get(i);
                    //1.4.6.1���o�ɗ��𖾍�( )(
                    //���o�ɗ��𖾍�.��n�� = ���o�ɗ���Params.��n��
                    l_aioHistoryUnit.deliveryDate = WEB3DateUtility.toDay(l_row.getDeliveryDate());
                    //���o�ɗ��𖾍�.���i�O���[�v = ���o�ɗ���Params.���i�O���[�v
                    l_aioHistoryUnit.productGroup = l_row.getProductGroup();
                    //���o�ɗ��𖾍�.�����R�[�h = ���o�ɗ���Params.�����R�[�h
                    l_aioHistoryUnit.productCode = l_row.getProductCode();
                    //���o�ɗ��𖾍�.������ = ���o�ɗ���Params.������
                    l_aioHistoryUnit.productName = l_row.getProductName();
                    //���o�ɗ��𖾍�.�����敪 = ���o�ɗ���Params.��������敪
                    l_aioHistoryUnit.taxType = l_row.getSpecificAccountDiv();
                    //���o�ɗ��𖾍�.���o�ɃO���[�v = ���o�ɗ���Params.���o�ɃO���[�v
                    l_aioHistoryUnit.inputOutputDetailGroup = l_row.getSecReceiptDeliveryGroup();
                    //���o�ɗ��𖾍�.���� = ���o�ɗ���Params.����
                    l_aioHistoryUnit.quantity = Long.toString(l_row.getQuantity());
                    //���o�ɗ��𖾍�.���ʒP�� = ���o�ɗ���Params.���ʒP��
                    l_aioHistoryUnit.quantityUnit = l_row.getQuantityUnit();
                    //���o�ɗ��𖾍�.�P�� = ���o�ɗ���Params.���P��
                    if (l_row.getExecutedPriceIsNull())
                    {
                        l_aioHistoryUnit.price = null;               
                    }
                    else
                    {
                        l_aioHistoryUnit.price = WEB3StringTypeUtility.formatNumber(l_row.getExecutedPrice());
               
                    }
                    //1.4.6.2�v���p�e�B�Z�b�g
                    //1.4.6.3add(arg0 : Object)
                    l_list.add(l_aioHistoryUnit);

                }
                //1.4.7toArray( )
                l_historyUnit = new WEB3AioHistoryUnit[l_list.size()];
                l_list.toArray(l_historyUnit);
                //1.4.8pageNumber( )(
                if (l_listAction.totalSize() != 0)
                {
                    l_intPageNumber = l_listAction.pageNumber() + 1;
                }
                //1.4.9totalPages( )(
                l_intTotalPages = l_listAction.totalPages();
                //1.4.10totalSize( )(
                l_intTotalSize = l_listAction.totalSize();
            }

            //1.5createResponse( 
            WEB3AioInputOutputHistoryListResponse l_response 
                = (WEB3AioInputOutputHistoryListResponse)l_listRequest.createResponse();
            //1.6�v���p�e�B�Z�b�g
            //���X�|���X.�\�����ԁi���j = �i�ȉ��̂Ƃ���j
            //   ���N�G�X�g.�\�����ԁi���j == null and (*1)-�A != null �̏ꍇ�A(*1)-�A
            //   ����ȊO�̏ꍇ�A���N�G�X�g.�\�����ԁi���j
            if ((l_listRequest.displayStartDate == null) && (l_datFrom != null))
            {
                l_response.displayStartDate = l_datFrom; 
            }
            else
            {
                l_response.displayStartDate = l_listRequest.displayStartDate;
            }

            //���X�|���X.�\�����ԁi���j = �i�ȉ��̂Ƃ���j
            //   ���N�G�X�g.�\�����ԁi���j == null and (*1)-�@@ != null �̏ꍇ�A(*1)-�@@
            //   ����ȊO�̏ꍇ�A���N�G�X�g.�\�����ԁi���j
            if ((l_listRequest.displayEndDate == null) && (l_datTo != null))
            {
                l_response.displayEndDate = l_datTo;
            }
            else
            {
                l_response.displayEndDate = l_listRequest.displayEndDate;
            }
            //���X�|���X.�\�����ԁi���j != null and ���X�|���X.�\�����ԁi���j != null �̏ꍇ
            //   ���X�|���X.���o�ɗ����ꗗ = ���o�ɗ��𖾍ׂ̔z��
            //   ���X�|���X.�\���y�[�W�ԍ� = pageNumber()�̖߂�l
            //   ���X�|���X.���y�[�W�� = totalPages()�̖߂�l
            //   ���X�|���X.�����R�[�h�� = totalSize()�̖߂�l
            
            if ((l_response.displayStartDate != null) && (l_response.displayEndDate != null))
            {
                l_response.HistoryUnits = l_historyUnit;
                l_response.pageIndex = Integer.toString(l_intPageNumber);
                l_response.totalPages = Integer.toString(l_intTotalPages);
                l_response.totalRecords = Integer.toString(l_intTotalSize);
            }
            //���X�|���X.�\�����ԁi���j == null and ���X�|���X.�\�����ԁi���j == null �̏ꍇ
            //   ���X�|���X.���o�ɗ����ꗗ = �v�f��0�̔z��
            //   ���X�|���X.�\���y�[�W�ԍ� = 0
            //   ���X�|���X.���y�[�W�� = 0
            //   ���X�|���X.�����R�[�h�� = 0
            if ((l_response.displayEndDate == null) && (l_response.displayStartDate == null))
            {
                l_response.HistoryUnits = new WEB3AioHistoryUnit[0];
                l_response.pageIndex = "0";
                l_response.totalPages = "0";
                l_response.totalRecords = "0";
            }
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        catch(NotFoundException l_ex)
        {
            log.error("__an notFoundexpected error__ ",l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch(DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        catch(DataNetworkException l_ex)
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

    /**
     * (create�擾����������)<BR>
     * �擾�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j�ȉ��̕�����𐶐�����B<BR>
     * <BR>
     *   "institution_code=? and branch_code=? and account_code like ?" <BR>
     * <BR>
     * �Q�j��n��<BR>
     * <BR>
     *    " and delivery_date>=? and delivery_date<=?" <BR>
     *    ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j���i�O���[�v<BR>
     * <BR>
     * �R�|�P�j����.���i�O���[�v == "Z"�i�S���i�j�̏ꍇ<BR>
     * <BR>
     *    " and product_group!=null" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j����.���i�O���[�v != "Z"�i�S���i�j�̏ꍇ<BR>
     * <BR>
     *    " and product_group=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �S�j�����R�[�h<BR>
     * <BR>
     *    ����.�����R�[�h != null�̏ꍇ<BR>
     * <BR>
     *    " and substr(product_code, 5, 5)=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �T�j�o���O���[�v<BR>
     * <BR>
     * �T�|�P�j����.�o���O���[�v == "Z"�i���o�Ɂj�̏ꍇ<BR>
     * <BR>
     *    " and io_group!=null" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �T�|�Q�j����.�o���O���[�v != "Z"�i���o�Ɂj�̏ꍇ<BR>
     * <BR>
     *    " and io_group=?" ���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �U�j���o�ɃO���[�v<BR>
     *<BR>
     *   " and sec_receipt_delivery_group!=null" ���P�j�̕�����ɒǉ�����B<BR>
     * �V�j�������ꂽ�������ԋp����B
     * @@param l_strProductGroup - ���i�O���[�v
     * 
     * @@param l_strProductCode - �����R�[�h
     * 
     * @@param l_strInputOutputGroup - �o���O���[�v
     * @@return String
     * @@roseuid 41B7BCD103D4
     */
    protected String createGetCondCharacterString(
        String l_strProductGroup,
        String l_strProductCode,
        String l_strInputOutputGroup)
    {
        final String STR_METHOD_NAME = "createGetCondCharacterString";
        log.entering(STR_METHOD_NAME);

        //��̕�����𐶐�����B
        StringBuffer l_strCreateString = new StringBuffer();

        // �P�j�ȉ��̕�����𐶐�����B
        l_strCreateString.append("institution_code = ? and branch_code = ? and account_code like ? || '%'");
        
        // �Q�j��n��
        //    "delivery_date>=? and delivery_date<=?"���P�j�̕�����ɒǉ�����B
        l_strCreateString.append(" and delivery_date >= ? and delivery_date <= ?");

        // �R�j���i�O���[�v
        // �R�|�P�j����.���i�O���[�v == "Z"�i�S���i�j�̏ꍇ
        //    " and product_group!=null" ���P�j�̕�����ɒǉ�����B
        if (WEB3InformProductGroupDef.ALL_PRODUCT.equals(l_strProductGroup))
        {
            l_strCreateString.append(" and product_group is not null"); 
        }
        // �R�|�Q�j����.���i�O���[�v != "Z"�i�S���i�j�̏ꍇ
        //    " and product_group=?" ���P�j�̕�����ɒǉ�����B
        else
        {
            l_strCreateString.append(" and product_group = ?");
        }

        // �S�j�����R�[�h 
        //    ����.�����R�[�h != null�̏ꍇ 
        //    " and substr(product_code, 5, 5)=?" ���P�j�̕�����ɒǉ�����B
        if (l_strProductCode != null)
        {
            l_strCreateString.append(" and substr(product_code, 5, 5) = ?");
        }
        
        //�T�j�o���O���[�v 
        // �T�|�P�j����.�o���O���[�v == "Z"�i���o�Ɂj�̏ꍇ
        //    " and io_group!=null" ���P�j�̕�����ɒǉ�����B
        if (WEB3InformInputOutputGroupDef.IN_OUT_BASE.equals(l_strInputOutputGroup)) 
        {
            l_strCreateString.append(" and io_group is not null");
        }
        // �T�|�Q�j����.�o���O���[�v != "Z"�i���o�Ɂj�̏ꍇ 
        //    " and io_group=?" ���P�j�̕�����ɒǉ�����B
        else
        {
            l_strCreateString.append(" and io_group = ?");             
        }
        //�U�j���o�ɃO���[�v
        //   " and sec_receipt_delivery_group!=null" ���P�j�̕�����ɒǉ�����B
        l_strCreateString.append(" and sec_receipt_delivery_group is not null");

        // 7�j�������ꂽ�������ԋp����B
        log.debug(l_strCreateString.toString());

        log.exiting(STR_METHOD_NAME);
        return l_strCreateString.toString();
    }

    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * �擾�����ɃZ�b�g����l�̔z��𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h<BR>
     *    �⏕����.getInstitution().getInstitutionCode()<BR>
     *    �⏕����.get����X().getBranchCode()<BR>
     *    �⏕����.getMainAccount().getAccountCode()�̍�6byte <BR>
     *    ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �R�j��n��<BR>
     * <BR>
     *    ����.�\�����ԁi���j�A����.�\�����ԁi���j ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �S�j���i�O���[�v<BR>
     * <BR>
     *    ����.���i�O���[�v != "Z"�i�S���i�j�̏ꍇ<BR>
     * <BR>
     *    ����.���i�O���[�v ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �T�j�����R�[�h<BR>
     * <BR>
     *    ����.�����R�[�h != null�̏ꍇ<BR>
     * <BR>
     *    ����.�����R�[�h ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �U�j�o���O���[�v<BR>
     * <BR>
     *    ����.�o���O���[�v != "Z"�i���o�Ɂj�̏ꍇ<BR>
     * <BR>
     *    ����.�o���O���[�v ���P�j��List�ɒǉ�����B<BR>
     * <BR>
     * �V�j�������ꂽList����z����擾���A�ԋp����B
     * @@param l_subAccount - �⏕����
     * @@param l_datDisplayStartDate - �\�����ԁi���j
     * @@param l_datDisplayEndDate - �\�����ԁi���j
     * @@param l_strProductGroup - ���i�O���[�v
     * @@param l_strProductCode - �����R�[�h
     * @@param l_strInputOutputGroup - �o���O���[�v
     * @@return Object[]
     * @@roseuid 41B7BCE8027C
     */
    protected Object[] createGetCondDataContainer(
        SubAccount l_subAccount,
        Date l_datDisplayStartDate,
        Date l_datDisplayEndDate,
        String l_strProductGroup,
        String l_strProductCode,
        String l_strInputOutputGroup)
    {
        final String STR_METHOD_NAME = "createGetCondDataContainer()";
        log.entering(STR_METHOD_NAME);

        // �P�j���ArrayList�𐶐�����B
        ArrayList l_list = new ArrayList();

        // �Q�j�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h<BR>
        //     �⏕����.getInstitution().getInstitutionCode()<BR>
        //     �⏕����.get����X().getBranchCode()<BR>
        //     �⏕����.getMainAccount().getAccountCode()�̍�6byte <BR>
        //     ���P�j��List�ɒǉ�����B<BR>
        l_list.add(l_subAccount.getInstitution().getInstitutionCode());
        l_list.add(l_subAccount.getMainAccount().getBranch().getBranchCode());
        l_list.add(l_subAccount.getMainAccount().getAccountCode().substring(0, 6));

        // �R�j��n��
        //    ����.�\�����ԁi���j�A����.�\�����ԁi���j ���P�j��List�ɒǉ�����B
        l_list.add(l_datDisplayStartDate);
        l_list.add(l_datDisplayEndDate);
        
        // �S�j���i�O���[�v
        //    ����.���i�O���[�v != "Z"�i�S���i�j�̏ꍇ
        //    ����.���i�O���[�v ���P�j��List�ɒǉ�����B
        if (!WEB3InformProductGroupDef.ALL_PRODUCT.equals(l_strProductGroup))
        {
            l_list.add(l_strProductGroup);
        }
        
        // �T�j�����R�[�h
        //    ����.�����R�[�h != null�̏ꍇ 
        //    ����.�����R�[�h ���P�j��List�ɒǉ�����B
        if (l_strProductCode != null)
        {
            l_list.add(l_strProductCode);
        }

        // �U�j�o���O���[�v
        //    ����.�o���O���[�v != "Z"�i���o�Ɂj�̏ꍇ
        //    ����.�o���O���[�v ���P�j��List�ɒǉ�����B
        if (!WEB3InformInputOutputGroupDef.IN_OUT_BASE.equals(l_strInputOutputGroup))
        {
            l_list.add(l_strInputOutputGroup);
        }

        // �V�j�������ꂽList����z����擾���A�ԋp����B
        log.exiting(STR_METHOD_NAME);
        Object[] l_obj = new Object[l_list.size()];
        l_list.toArray(l_obj);
        return l_obj;
    }

    /**
     * (create�\�[�g����������)<BR>
     * �擾�f�[�^�̃\�[�g�����̕�����𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�\�[�g�L�[�z��̊e�v�f���Ɉȉ��̏������s���B<BR>
     * <BR>
     * �Q�|�P�j�L�[���� == �h��n���h �̏ꍇ<BR>
     * <BR>
     *    �E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     *        "delivery_date"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    �E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     *        "delivery_date desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�Q�j�L�[���� == �h���i�O���[�v�h �̏ꍇ<BR>
     * <BR>
     *    �E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     *        "product_group"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    �E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     *        "product_group desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�R�j�L�[���� == �h�����R�[�h�h �̏ꍇ<BR>
     * <BR>
     *    �E����/�~�� == "A"�i�����j �̏ꍇ<BR>
     * <BR>
     *        "product_code"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     *    �E����/�~�� == "D"�i�~���j �̏ꍇ<BR>
     * <BR>
     *        "product_code desc"���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �Q�|�S�j�Y���̗v�f���z����̍Ō�̗v�f�ł͂Ȃ��ꍇ<BR>
     * <BR>
     *    ", "���P�j�̕�����ɒǉ�����B<BR>
     * <BR>
     * �R�j�������ꂽ�������ԋp����B
     * @@param l_sortKeys - (�\�[�g�L�[)<BR>
     * �\�[�g�L�[�̔z��
     * 
     * @@return String
     * @@roseuid 41B7BD0601D0
     */
    protected String createSortCondString(WEB3AioSortKeyUnit[] l_sortKeys)
    {
        final String STR_METHOD_NAME = "createSortCondString(WEB3AioSortKeyUnit[] sortKeys)";
        log.entering(STR_METHOD_NAME);
        // �P�j��̕�����𐶐�����B
        StringBuffer l_strSortString = new StringBuffer();
        // �Q�j�\�[�g�L�[�z��̊e�v�f���Ɉȉ��̏������s���B
        int l_intLength = 0;
        if (l_sortKeys != null)
        {
            l_intLength = l_sortKeys.length;
        }
        for (int i = 0;i < l_intLength;i++ )
        {
            // �Q�|�P�j�L�[���� == �h��n���h �̏ꍇ
            // 
            //    �E����/�~�� == "A"�i�����j �̏ꍇ
            // 
            //        "delivery_date"���P�j�̕�����ɒǉ�����B
            if (WEB3AioInputOutputSortkeyItemDef.DELIVERY_DATE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("delivery_date");
                }
                // 
                //    �E����/�~�� == "D"�i�~���j �̏ꍇ
                // 
                //        "delivery_date desc"���P�j�̕�����ɒǉ�����B
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("delivery_date desc");
                }
            }

            // �Q�|�Q�j�L�[���� == �h���i�O���[�v�h �̏ꍇ
            // 
            //    �E����/�~�� == "A"�i�����j �̏ꍇ
            // 
            //        "product_group"���P�j�̕�����ɒǉ�����B
            // 
            //    �E����/�~�� == "D"�i�~���j �̏ꍇ
            // 
            //        "product_group desc"���P�j�̕�����ɒǉ�����B
            else if (WEB3AioInputOutputSortkeyItemDef.PRODUCT_GROUP.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_group");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_group desc");
                }
            }
            // 
            // �Q�|�R�j�L�[���� == �h�����R�[�h�h �̏ꍇ
            // 
            //    �E����/�~�� == "A"�i�����j �̏ꍇ
            // 
            //        "product_code"���P�j�̕�����ɒǉ�����B
            // 
            //    �E����/�~�� == "D"�i�~���j �̏ꍇ
            // 
            //        "product_code desc"���P�j�̕�����ɒǉ�����B
            // 
            else if (WEB3AioInputOutputSortkeyItemDef.PRODUCT_CODE.equals(l_sortKeys[i].keyItem))
            {
                if (WEB3AscDescDef.ASC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_code");
                }
                else if (WEB3AscDescDef.DESC.equals(l_sortKeys[i].ascDesc))
                {
                    l_strSortString.append("product_code desc");
                }
                
            }
            // �Q�|�S�j�Y���̗v�f���z����̍Ō�̗v�f�ł͂Ȃ��ꍇ
            // 
            //    ", "���P�j�̕�����ɒǉ�����B
            // 
            if (i < (l_intLength - 1))
            {
                l_strSortString.append(",");
            }
        }            
        // �R�j�������ꂽ�������ԋp����B
        log.debug(l_strSortString.toString());
        log.exiting(STR_METHOD_NAME);
        return l_strSortString.toString();
    }
}
@
