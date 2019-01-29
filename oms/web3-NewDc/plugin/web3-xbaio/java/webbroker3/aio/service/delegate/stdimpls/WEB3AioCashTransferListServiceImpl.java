head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.27.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashTransferListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o���ꗗ�T�[�r�X�����N���X(WEB3AioCashTransferListServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 ���E (���u) �V�K�쐬
Revesion History : 2004/10/25 ���z (���u) ���r���[
Revesion History : 2005/11/17 ���r (���u) �t�B�f���e�B�Ή�                        
Revesion History : 2006/06/05 ��؁iSCS�j �d�l�ύX No.589�EDB�X�V�d�l 091
Revesion History : 2006/07/12 ������ (���u) �d�l�ύX No.595,No.598
Revesion History : 2006/07/18 ��O�� (���u) �d�l�ύX No.605
Revesion History : 2006/08/01 ���(SCS)�@@���f��No.610�Ή�
Revesion History : 2006/11/07 �����q (���u) �d�l�ύX No.679
Revesion History : 2007/05/09 �����q (���u) �d�l�ύX No.723 No.725
Revesion History : 2007/09/21 �đo�g (���u) �d�l�ύX No.765
Revesion History : 2008/05/19 �đo�g (���u) �d�l�ύX No.848�ANo.869�ANo.878�ANo.884
Revesion History : 2008/09/22 ���u�� (���u) �d�l�ύX No.1000,1045,1075
Revesion History : 2009/03/11 ���u�� (���u) �d�l�ύX No.1117,1146
Revesion History : 2009/04/16 �đo�g (���u) �d�l�ύX No.1158
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import webbroker3.aio.WEB3AioCashOutStatusUtility;
import webbroker3.aio.WEB3AioFEqConTransStatusUtility;
import webbroker3.aio.WEB3AioFXTransStatusUtility;
import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.WEB3FXDataControlService;
import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankCashTransferStatusRow;
import webbroker3.aio.data.DepositInformRow;
import webbroker3.aio.data.GftTransferStatusParams;
import webbroker3.aio.data.GftTransferStatusRow;
import webbroker3.aio.data.HostPaymentAcceptRow;
import webbroker3.aio.data.UwgTransferStatusRow;
import webbroker3.aio.data.HostTransferAcceptDao;
import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.aio.define.WEB3AioDescriptionDef;
import webbroker3.aio.define.WEB3AioTransUnitDef;
import webbroker3.aio.message.WEB3AioAcceptDateComparator;
import webbroker3.aio.message.WEB3AioCashTransUnit;
import webbroker3.aio.message.WEB3AioCashTransferListResponse;
import webbroker3.aio.message.WEB3AioDealComparator;
import webbroker3.aio.service.delegate.WEB3AioCashTransferListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioPaymentApplicationDivDef;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FxTransferMasterRemarkCodeDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusFlagDef;
import webbroker3.common.define.WEB3RemarkCodeDef;
import webbroker3.common.define.WEB3ResultStatusFlagDef;
import webbroker3.common.define.WEB3StartStatusFlgDef;
import webbroker3.common.define.WEB3TransactionStatusDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���o���ꗗ�T�[�r�XImpl)<BR>
 * ���o���ꗗ�T�[�r�X�����N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AioCashTransferListServiceImpl extends WEB3ClientRequestService
    implements WEB3AioCashTransferListService 
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransferListServiceImpl.class);
    
    /**
     * ���o���ꗗ�T�[�r�X�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o���ꗗ�j�ꗗ��ʕ\���f�[�^�擾�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4100F30A0261
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.1)������t�\���ǂ����̃`�F�b�N���s���B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        // 1.2)�⏕�������擾����B 
        //[����] 
        //�⏕�����^�C�v�F 1�i�a��������j
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        // 1.3)�����P�ʃe�[�u��������o�����ׂ��擾����B
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g
        List l_listCashTransferFromOrderUnit = this.getCashTransferFromOrderUnit(l_subAccount);
        
        // 1.4)�����A���e�[�u��������o�����ׂ��擾����B
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g
        List l_LisCashTransferFromCashinNotice = 
            this.getCashTransferFromCashinNotice(l_subAccount);
        
        // 1.5)���Z�@@�֘A�g���o���󋵃e�[�u��������o�����ׂ��擾����B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        List l_listCashTransferFromCashTransferStatus = 
            this.getCashTransferFromCashTransferStatus(l_subAccount);
        
        // 1.6) GFT�U�֏󋵃e�[�u��������o�����ׂ��擾����B 
        //[����] 
        //�⏕�����F �⏕�����I�u�W�F�N�g 
        List l_listCashTransferFromGftTransferStatus = 
            this.getCashTransferFromGftTransferStatus(l_subAccount);
        
        // 1.7)get���o������From�����P��()�Ŏ擾����ArrayList�̖����ɁA���X�g��ǉ�����B
        //[����] 
        //arg0�F get���o������From�����A��()�̖߂�l
        l_listCashTransferFromOrderUnit.addAll(l_LisCashTransferFromCashinNotice);
        
        // 1.8)get���o������From�����P��()�Ŏ擾����ArrayList�̖����ɁA���X�g��ǉ�����B
        //[����] 
        //arg0�F get���o������From���o����()�̖߂�l
        l_listCashTransferFromOrderUnit.addAll(l_listCashTransferFromCashTransferStatus);
        
        // 1.9) get���o������From�����P��()�Ŏ擾����ArrayList�̖����ɁA���X�g��ǉ�����B 
        //[����] 
        //arg0�F get���o������FromGFT�U�֏�()�̖߂�l 
        l_listCashTransferFromOrderUnit.addAll(l_listCashTransferFromGftTransferStatus);
        
        // 1.10)���o�����ׂ̔z����擾����
        WEB3AioCashTransUnit [] l_aioCashTransUnit = 
            new WEB3AioCashTransUnit[l_listCashTransferFromOrderUnit.size()];
        l_listCashTransferFromOrderUnit.toArray(l_aioCashTransUnit);
        
        // 1.11)ArrayList()
        List l_lisDate = new Vector();
        
        // 1.12)��t����Comparator�C���X�^���X�𐶐�����B 
        //[����] 
        //orderBy�F "D"�i�~���j
        WEB3AioAcceptDateComparator l_aioAcceptDateComparator =
            new WEB3AioAcceptDateComparator(WEB3AscDescDef.DESC);
        
        // 1.13)��t����Comparator�I�u�W�F�N�g��ArrayList�ɒǉ�����B
        //[����] 
        //arb0�F ��t����Comparator�I�u�W�F�N�g
        
        l_lisDate.add(l_aioAcceptDateComparator);
        
        // 1.14)���Comparator�C���X�^���X�𐶐�����B
        //[����] 
        //orderBy�F "A"�i�����j 
        WEB3AioDealComparator l_aioDealComparator =
            new WEB3AioDealComparator(WEB3AscDescDef.ASC);
        
        // 1.15)���Comparator�I�u�W�F�N�g��ArrayList�ɒǉ�����B
        //[����] 
         //arb0�F ���Comparator�I�u�W�F�N�g 
        l_lisDate.add(l_aioDealComparator);
        
        // 1.16)Comparator�̔z����擾����B
        Comparator[] l_comparators = new Comparator[l_lisDate.size()];
        l_lisDate.toArray(l_comparators);
        
        // 1.17)���o�����ׁm�n��Comparator�ɏ]���āA�\�[�g����B
        //�m�����n 
        //obj�F ���o�����׃I�u�W�F�N�g�̔z�� 
        //com�F Comparator�I�u�W�F�N�g�̔z�� 
        WEB3ArraysUtility.sort(l_aioCashTransUnit, l_comparators);
                
        // 1.18)���X�|���X�f�[�^�𐶐�����B 
        WEB3AioCashTransferListResponse l_aioCashTransferListResponse = 
            (WEB3AioCashTransferListResponse)l_request.createResponse();
        
        // 1.19)�i*�j �v���p�e�B�Z�b�g
        //(*) �ȉ��̂悤�ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X.���o���ꗗ = ���o�����ׁm�n
        l_aioCashTransferListResponse.cashTransUnits = l_aioCashTransUnit; 
                
        log.exiting(STR_METHOD_NAME);
        return l_aioCashTransferListResponse;
        
    }
    
    /**
     * (get������)<BR>
     * �����P�ʂ̒�����ԁA��������敪�A�����P�ʂɊ֘A����<BR>
     * ��t�L���[�e�[�u���̏����敪<BR>
     * ���璍���󋵂𔻒肵�A�ԋp����B<BR>
     * <BR>
     * �P�j��t�L���[�e�[�u�����擾����B<BR>
     * <BR>
     * �P�|�P�j�����P��Params.������� = 1001�i�o�������j and<BR>
     *         �����P��Params.�����o�H�敪 != 9�iHOST�j �̏ꍇ<BR>
     * <BR>
     *    [�Ώۃe�[�u��]<BR>
     *    �o��������t�L���[�e�[�u�� <BR>
     * <BR>
     *    [��������]<BR>
     *    �f�[�^�R�[�h = "GI80A"<BR>
     *    �،���ЃR�[�h = <BR>
     * ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR>
     *    ���X�R�[�h = ����.�⏕����.get����X().getBranchCode()�̖߂�l<BR>
     *    �ڋq�R�[�h = ����.�⏕����.getMainAccou��t().getAccountCode()<BR>
     * �̖߂�l<BR>
     *    ���ʃR�[�h = �����P��Params.���ʃR�[�h<BR>
     * <BR>
     * �P�|�Q�j�i�����P��Params.�����J�e�S�� = 13�i�U�ցj or <BR>
     * �����P��Params.�����J�e�S�� = 15�i�ב֕ۏ؋��U�ցj�j and <BR>
     * �����P��Params.�����o�H�敪 != 9�iHOST�j �̏ꍇ <BR>
     * <BR>
     * [�Ώۃe�[�u��] <BR>
     * �U�֐�����t�L���[�e�[�u�� <BR>
     * <BR>
     * [��������] <BR>
     * �f�[�^�R�[�h = "GI80F" <BR>
     * �،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l <BR>
     * ���X�R�[�h = ����.�⏕����.get����X().getBranchCode()�̖߂�l <BR>
     * �ڋq�R�[�h = ����.�⏕����.getMainAccou��t().getAccountCode()�̖߂�l <BR>
     * ���ʃR�[�h = �����P��Params.���ʃR�[�h <BR>
     * <BR>
     * �Q�j�Y���������ב֕ۏ؋��U�ւ������͊O�������U�ւ̏ꍇ�A�U�֏󋵃e�[�u�����擾����B<BR> 
     * <BR>
     * �Q�|�P�j�����P��Params.�����J�e�S�� = 15�i�ב֕ۏ؋��U�ցj �̏ꍇ <BR>
     * <BR>
     * [�Ώۃe�[�u��] <BR>
     * GFT�U�֏󋵃e�[�u�� <BR>
     * <BR>
     * [��������] <BR>
     * �،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR> 
     * ���X�R�[�h = ����.�⏕����.get����X().getBranchCode()�̖߂�l <BR>
     * �ڋq�R�[�h = ����.�⏕����.getMainAccou��t().getAccountCode()�̖߂�l <BR>
     * ���ʃR�[�h = �����P��Params.���ʃR�[�h <BR>
     * <BR>
     * �Q�|�Q�j�����P��Params.�����J�e�S�� = 16�i�O�������U�ցj �̏ꍇ <BR>
     * <BR>
     * [�Ώۃe�[�u��] <BR>
     * UWG�U�֏󋵃e�[�u�� <BR>
     * <BR>
     * [��������] <BR>
     * �،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l<BR> 
     * ���X�R�[�h = ����.�⏕����.get����X().getBranchCode()�̖߂�l <BR>
     * �ڋq�R�[�h = ����.�⏕����.getMainAccou��t().getAccountCode()�̖߂�l <BR>
     * ���ʃR�[�h = �����P��Params.���ʃR�[�h <BR>
     * <BR>
     * �R�j�ȉ��̍��ڂ��璍���󋵂𔻒肵�A�ԋp����B <BR>
     * <BR>
     * �����P��Params.������� <BR>
     * �����P��Params.��������敪 <BR>
     * ��t�L���[�e�[�u��.�����敪 �i�� �P�j�̏����Ŏ擾�������́j <BR>
     * <BR>
     * ���ב֕ۏ؋��U��or�O�������U�ւ̏ꍇ�́A�ȉ��̍��ڂ��l������B <BR>
     * �U�֏󋵃e�[�u��.�U�֏󋵋敪 <BR>
     * �U�֏󋵃e�[�u��.����M�敪 <BR>
     * �U�֏󋵃e�[�u��.��t���ʃR�[�h <BR>
     * <BR>
     * �ڍׂ́A�u���o���X�e�[�^�X�\���\.xls�v�̊e�V�[�g�Q�� <BR>
     * <BR>
     * ��2005/02/14���݁A�O�������U�ւɂ��Ă̏�LEXCEL�t�@@�C���̓��e�����m��� <BR>
     * �ӏ�����B���̉ӏ��ɂ��ẮA���e���m���ɑΉ��B <BR>
     * <BR>
     * @@param l_subAcccount - (�⏕�����I�u�W�F�N�g)
     * @@param l_orderUnitRow - (�����P��Row)
     * @@return String
     * @@throws WEB3SystemLayerException<BR>
     * @@roseuid 41060B51008C
     */
    protected String getOrderStatus(SubAccount l_subAccount, AioOrderUnitRow l_orderUnitRow) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getOrderStatus(SubAccount l_subAcccount, AioOrderUnitParams l_orderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_orderUnitRow == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j��t�L���[�e�[�u�����擾����B 
        //�P�|�P�j�����P��Params.������� = 1001�i�o�������j and  
        //�����P��Params.�����o�H�敪 != 9�iHOST�j �̏ꍇ
        //[�Ώۃe�[�u��] 
        //�o��������t�L���[�e�[�u��
        //[��������] 
        //�f�[�^�R�[�h = "GI80A" 
        //�،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���X�R�[�h = ����.�⏕����.get����X().getBranchCode()�̖߂�l 
        //�ڋq�R�[�h = ����.�⏕����.getMainAccou��t().getAccountCode()�̖߂�l 
        //���ʃR�[�h = �����P��Params.���ʃR�[�h
        List l_lisHostCashTransOrderAcceptRows = null;
        HostPaymentAcceptRow l_hostPaymentAcceptRow = null;
        
        if(!WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv())
            && OrderTypeEnum.CASH_OUT.equals(l_orderUnitRow.getOrderType()))
        {
            String l_strWhere = "request_code = ? and institution_code = ? " +
               " and branch_code = ? and account_code = ? and order_request_number = ?";
            
            Object[] l_objValues = {WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ACCEPT,
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_orderUnitRow.getOrderRequestNumber()};
                        
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
        
                l_lisHostCashTransOrderAcceptRows =
                    l_queryProcessor.doFindAllQuery(
                            HostPaymentAcceptRow.TYPE, 
                            l_strWhere, 
                            l_objValues);
                
                int l_intHostCashTransOrderAcceptRowsLength = 
                    l_lisHostCashTransOrderAcceptRows.size();
                
                if(l_intHostCashTransOrderAcceptRowsLength > 1)
                {
                    log.debug("l_lisHostCashTransOrderAcceptRows.size() > 1");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if(l_intHostCashTransOrderAcceptRowsLength == 1)
                {
                    l_hostPaymentAcceptRow = 
                        (HostPaymentAcceptRow)l_lisHostCashTransOrderAcceptRows.get(0);
                }
            }
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
        }
        
        //�P�|�Q�j�i�����P��Params.�����J�e�S�� = 13�i�U�ցj or 
        //�����P��Params.�����J�e�S�� = 15�i�ב֕ۏ؋��U�ցj�j and 
        //�����P��Params.�����o�H�敪 != 9�iHOST�j �̏ꍇ 
        //[�Ώۃe�[�u��] 
        //�U�֐�����t�L���[�e�[�u�� 
        //[��������] 
        //�f�[�^�R�[�h = "GI80F" 
        //�،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���X�R�[�h = ����.�⏕����.get����X().getBranchCode()�̖߂�l 
        //�ڋq�R�[�h = ����.�⏕����.getMainAccou��t().getAccountCode()�̖߂�l 
        //���ʃR�[�h = �����P��Params.���ʃR�[�h 
        List l_lisHostTransferAcceptRows = null;
        HostTransferAcceptRow l_hostTransferAcceptRow = null;
        
        if ((OrderCategEnum.CASH_TRANSFER.equals(l_orderUnitRow.getOrderCateg())
            || OrderCategEnum.FX.equals(l_orderUnitRow.getOrderCateg()))
            && !WEB3OrderRootDivDef.HOST.equals(l_orderUnitRow.getOrderRootDiv()))
        {
            String l_strWhere = "request_code = ? and institution_code = ? " +
               "and branch_code = ? and account_code = ? and order_request_number = ?";
            
            Object[] l_objValues = {
                WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ACCEPT,
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_orderUnitRow.getOrderRequestNumber()};
                        
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
        
                l_lisHostTransferAcceptRows =
                    l_queryProcessor.doFindAllQuery(
                            HostTransferAcceptRow.TYPE, 
                            l_strWhere, 
                            l_objValues);
                
                int l_intHostTransferAcceptRowsLength = 
                    l_lisHostTransferAcceptRows.size();
                
                if(l_intHostTransferAcceptRowsLength > 1)
                {
                    log.debug("l_lisHostTransferOrderRows.size() > 1");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if(l_intHostTransferAcceptRowsLength == 1)
                {
                    l_hostTransferAcceptRow = 
                        (HostTransferAcceptRow)l_lisHostTransferAcceptRows.get(0);
                }
            }
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
        }
        //�ڍׂ́A�u���o���X�e�[�^�X�\���\.xls�v�̊e�V�[�g�Q�� 
        
        //�����P�ʃe�[�u��.�������
        OrderStatusEnum l_orderStatusEnum = l_orderUnitRow.getOrderStatus();  
        String l_strOrderStatus = l_orderStatusEnum.intValue() + "";  
        
        //�����P�ʃe�[�u��.��������敪
        String l_strCancelType= l_orderUnitRow.getCancelType();
        
        //��t�L���[�e�[�u��.�����敪 �i�� �P�j�̏����Ŏ擾�������́j 
        String l_strStatus = null;
		if(l_hostPaymentAcceptRow != null)
        {
            l_strStatus = l_hostPaymentAcceptRow.getStatus();
        }
        else if(l_hostTransferAcceptRow != null)
        {
            l_strStatus = l_hostTransferAcceptRow.getStatus();
        }
        log.debug("l_strStatus = " + l_strStatus);

        //�Q�j�Y���������ב֕ۏ؋��U�ւ������͊O�������U�ւ̏ꍇ�A�U�֏󋵃e�[�u�����擾����B 
        //�Q�|�P�j�����P��Params.�����J�e�S�� = 15�i�ב֕ۏ؋��U�ցj �̏ꍇ 
        //[�Ώۃe�[�u��] 
        //GFT�U�֏󋵃e�[�u�� 
        //[��������] 
        //�،���ЃR�[�h = ����.�⏕����.getInstitution().getInstitutionCode()�̖߂�l 
        //���X�R�[�h = ����.�⏕����.get����X().getBranchCode()�̖߂�l 
        //�ڋq�R�[�h = ����.�⏕����.getMainAccou��t().getAccountCode()�̖߂�l 
        //���ʃR�[�h = �����P��Params.���ʃR�[�h 
        
        if (OrderCategEnum.FX.equals(l_orderUnitRow.getOrderCateg()))            
        {
            List l_lisGftTransferStatusRows = null;
            GftTransferStatusRow l_gftTransferStatusRow = null;

            String l_strWhere = "institution_code = ? and branch_code = ? " +
               "and account_code = ? and order_request_number = ?";
            
            Object[] l_objValues = {
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                l_orderUnitRow.getOrderRequestNumber()};
                        
            QueryProcessor l_queryProcessor = null;
            try
            {
                l_queryProcessor = Processors.getDefaultProcessor();
        
                l_lisGftTransferStatusRows =
                    l_queryProcessor.doFindAllQuery(
                            GftTransferStatusRow.TYPE, 
                            l_strWhere, 
                            l_objValues);
                if (l_lisGftTransferStatusRows == null || l_lisGftTransferStatusRows.size() == 0)
                {
                    log.debug("GFT�U�֏󋵃e�[�u������A���R�[�h���擾���Ȃ� ! order_request_number =  "
                            +  l_orderUnitRow.getOrderRequestNumber());
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
                int l_intGftTransferStatusRowsLength = 
                    l_lisGftTransferStatusRows.size();
                
                if(l_intGftTransferStatusRowsLength > 1)
                {
                    log.debug("l_lisHostTransferOrderRows.size() > 1");
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80004,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                else if(l_intGftTransferStatusRowsLength == 1)
                {
                    l_gftTransferStatusRow = 
                        (GftTransferStatusRow)l_lisGftTransferStatusRows.get(0);
                }
            }
            catch (DataException l_ex)
            {
                log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }            
            //���ב֕ۏ؋��U��or�O�������U�ւ̏ꍇ�́A�ȉ��̍��ڂ��l������B 
            //�U�֏󋵃e�[�u��.�U�֏󋵋敪 
            //�U�֏󋵃e�[�u��.����M�敪 
            //�U�֏󋵃e�[�u��.��t���ʃR�[�h 
            //�ڍׂ́A�u���o���X�e�[�^�X�\���\.xls�v�̊e�V�[�g�Q��
            String l_strTransferStatusDiv = l_gftTransferStatusRow.getTransferStatusDiv();
            String l_strSendRcvDiv = l_gftTransferStatusRow.getSendRcvDiv();
            String l_strResultCode = l_gftTransferStatusRow.getResultCode();
            
            WEB3AioFXTransStatusUtility l_aioFxStatusUtils = 
                    new WEB3AioFXTransStatusUtility();
            String l_result = l_aioFxStatusUtils.getResult(
                    l_strTransferStatusDiv, 
                    l_strSendRcvDiv, 
                    l_strResultCode, 
                    l_strOrderStatus, 
                    l_strCancelType, 
                    l_strStatus);

            return l_result;
        }
        //�Q�|�Q�j�����P��Params.�����J�e�S�� = 16�i�O�������U�ցj �̏ꍇ 
        if (OrderCategEnum.FEQ_TRANSFER.equals(l_orderUnitRow.getOrderCateg()))            
        {
			List l_lisUwgTransferStatusRows = null;
			UwgTransferStatusRow l_uwgTransferStatusRow = null;

			String l_strWhere = "institution_code = ? and branch_code = ? " +
			   "and account_code = ? and order_request_number = ?";
            
			Object[] l_objValues = {
				l_subAccount.getInstitution().getInstitutionCode(),
				l_subAccount.getMainAccount().getBranch().getBranchCode(),
				l_subAccount.getMainAccount().getAccountCode(),
				l_orderUnitRow.getOrderRequestNumber()};
                        
			QueryProcessor l_queryProcessor = null;
			try
			{
				l_queryProcessor = Processors.getDefaultProcessor();
        
				l_lisUwgTransferStatusRows =
					l_queryProcessor.doFindAllQuery(
							UwgTransferStatusRow.TYPE, 
							l_strWhere, 
							l_objValues);
                
                if (l_lisUwgTransferStatusRows == null || l_lisUwgTransferStatusRows.size() == 0)
                {
                    log.debug("UWG�U�֏󋵃e�[�u������A���R�[�h���擾���Ȃ� !");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
                
				int l_intUwgTransferStatusRowsLength = 
					l_lisUwgTransferStatusRows.size();
                
				if(l_intUwgTransferStatusRowsLength > 1)
				{
					log.debug("l_lisUwgTransferStatusRows.size() > 1");
					throw new WEB3BaseRuntimeException(
						WEB3ErrorCatalog.SYSTEM_ERROR_80004,
						this.getClass().getName() + "." + STR_METHOD_NAME);
				}
				else if(l_intUwgTransferStatusRowsLength == 1)
				{
					l_uwgTransferStatusRow = 
						(UwgTransferStatusRow)l_lisUwgTransferStatusRows.get(0);
				}
			}
			catch (DataException l_ex)
			{
				log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					l_ex.getMessage(),
					l_ex);
			}            
			//���ב֕ۏ؋��U��or�O�������U�ւ̏ꍇ�́A�ȉ��̍��ڂ��l������B 
			//�U�֏󋵃e�[�u��.�U�֏󋵋敪 
			//�U�֏󋵃e�[�u��.����M�敪 
			//�U�֏󋵃e�[�u��.��t���ʃR�[�h 
			//�ڍׂ́A�u���o���X�e�[�^�X�\���\.xls�v�̊e�V�[�g�Q��
			String l_strTransferStatusDiv = l_uwgTransferStatusRow.getTransferStatusDiv();
			String l_strSendRcvDiv = l_uwgTransferStatusRow.getSendRcvDiv();
			String l_strResultCode = l_uwgTransferStatusRow.getResultCode();
            
			WEB3AioFEqConTransStatusUtility l_aioFeqConStatusUtils = 
					new WEB3AioFEqConTransStatusUtility();
			String l_result = l_aioFeqConStatusUtils.getResult(
					l_strTransferStatusDiv, 
					l_strSendRcvDiv, 
					l_strResultCode, 
					l_strOrderStatus, 
					l_strCancelType);

			return l_result;
        }
        else
        {
            WEB3AioCashOutStatusUtility l_aioStatusUtils = 
                new WEB3AioCashOutStatusUtility();
            String l_result = l_aioStatusUtils.getStatus(
                    l_strOrderStatus, 
                    l_strCancelType, 
                    l_strStatus);
            return l_result;
        }
    }
    
    /**
     * (get���o������From�����P��)<BR>
     * �����P�ʃe�[�u������A���o�����ׂ̃��X�g���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o���ꗗ�jget���o������From�����P�ʁv �Q��<BR>
     * @@param l_subAccount - (�⏕�����I�u�W�F�N�g)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 410625CA03D2
     */
    protected List getCashTransferFromOrderUnit(SubAccount l_subAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromOrderUnit(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.3)ArrayList�̃C���X�^���X�𐶐�����B
        List l_lisaioCashTransUnits = new Vector();
        
        try
        {
            // 1.1)�N�G���v���Z�b�T���擾����
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // 1.2)doFindAllQuery(Row�^�C�v : RowType, Where : String, ���X�g : Object[])
            //�����P�ʃe�[�u������A�ȉ��̏����̃��R�[�h���擾����B 
            //[����] 
            //����ID = �⏕����.getAccountId()�̖߂�l 
            //�⏕����ID = �⏕����.getSubAccountId()�̖߂�l 
            //�����J�e�S�� != 14�i�،��U�ցj
            //�����^�C�v != 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j
            //�����^�C�v != 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j
			//�����o�H�敪 != 'B'(�ۏ؋������U�փo�b�`)
			//�o���\���敪 != 'A0'(�o�C�L���O)
            //�U�֋L�q != 'cashout_gross'�i�o���A�gGross�j and
            //�E�v�R�[�h != '71'�i����؋����F���؁j and
            //�E�v�R�[�h != '72'�i����؋����F��؁j
            //�i������ >= �V�X�e���^�C���X�^���v�̓��t���� 
            //or 
            //��t�����̓��t���� = �V�X�e���^�C���X�^���v�̓��t����
            //or
            //��n�� >= �V�X�e���v���t�@@�����X.�Ɩ����t�j 
            //[����] 
            //Row�^�C�v�F AioOrderUnitRow.TYPE 
            //Where�F "account_id = ? and sub_account_id = ? and order_categ <> ? and order_type<> ? and order_type <> ? "+
            //"and order_root_div <> ? and (payment_application_div is null or payment_application_div <> ?)  and "+ 
            //"( description is null or description <> ? ) and ( remark_code is null or ( remark_code <> ? and remark_code <> ? ) ) and "+
            //"(biz_date >= ? or to_char(received_date_time,'YYYYMMDD') = ? or to_char(delivery_date,'YYYYMMDD') >= ?)"; 
            //���X�g�F �ȉ��̍��ڂ̃��X�g 
            //�⏕����.getAccountId()�̖߂�l 
            //�⏕����.getSubAccountId()�̖߂�l 
            //�����J�e�S��.�،��U��
            //�����^�C�v.�U�֒���(�a�������M�p�ۏ؋�)
            //�����^�C�v.�U�֒���(�M�p�ۏ؋�����a���)
			//�����o�H�敪.�ۏ؋������U�փo�b�` 
			//�o���\���敪.�o�C�L���O 
            //�U�֋L�q.�o���A�gGross
            //�E�v�R�[�h.'71'�i����؋����F����)
            //�E�v�R�[�h.'72'�i����؋����F���) 
            //�V�X�e���^�C���X�^���v�̓��t���� 
            //�V�X�e���^�C���X�^���v�̓��t����
            //�V�X�e���v���t�@@�����X.�Ɩ����t 

			String l_strWhere = "account_id = ? and sub_account_id = ? and order_categ <> ? and order_type<>? and order_type<>? " +
				"and order_root_div<>? and (payment_application_div is null or payment_application_div<>?)  and "+
                "(description is null or description <> ?) and (remark_code is null or (remark_code <> ? and remark_code <> ?)) and " +
                "(biz_date >= ? or to_char(received_date_time,'YYYYMMDD') = ? or to_char(delivery_date,'YYYYMMDD') >= ?)";
            
            Object[] l_objValues = new Object[]{
                Long.toString(l_subAccount.getAccountId()),
                String.valueOf(l_subAccount.getSubAccountId()),
                OrderCategEnum.ASSET_TRANSFER, 
                OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE,
                OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT,
				WEB3OrderRootDivDef.DEPOSIT_AUTO_TRANSFER_BATCH,
				WEB3AioPaymentApplicationDivDef.BUY_KING,
                WEB3AioDescriptionDef.CASHOUT_GROSS,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO,
                WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_OSAKA,
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
				WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
				WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMMdd")
                };            

            List l_lisRows =
                l_queryProcessor.doFindAllQuery(AioOrderUnitRow.TYPE, l_strWhere, l_objValues);
            
            int l_intOrderUnitLength = l_lisRows.size();
            
            // 1.4)�擾���������P��Params����Loop����
            for(int i = 0; i < l_intOrderUnitLength; i++)
            {                
                AioOrderUnitRow l_aioOrderUnitRow = (AioOrderUnitRow) l_lisRows.get(i); 
                AioOrderUnitParams l_aioOrderUnitParams = 
                    new AioOrderUnitParams(l_aioOrderUnitRow);

                // 1.4.1)���o�����ׂ̃C���X�^���X�𐶐�����
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //1.4.2 ����敪���擾����B 
                //[����] 
                //�����P��Params�F �����P��Params�I�u�W�F�N�g 
                String l_strTradingType = this.getTradingType(l_aioOrderUnitParams);
                
                //1.4.3 ���l�ɐݒ肷�鍀�ڂ��擾����B 
                //[����] 
                //�����P��Params�F �����P��Params�I�u�W�F�N�g
                String l_strRemark = this.getRemark(l_aioOrderUnitParams);

                // 1.4.4)�v���p�e�B�Z�b�g
                //(*2) �ȉ��̂悤�Ƀv���p�e�B���Z�b�g����B
                //���o������.��t���t = �����P��Params.��t����
                l_aioCashTransUnit.receptionDate = l_aioOrderUnitRow.getReceivedDateTime();
                
                //���o������.��� = get����敪()�̖߂�l
                l_aioCashTransUnit.tradingType = l_strTradingType;
                
                //���o������.���Z�@@�֖� = null
                l_aioCashTransUnit.paySchemeName = null;
                
                //���o������.���o�����z = �����P��Params.��������
                l_aioCashTransUnit.cashTransAmt = 
                    WEB3StringTypeUtility.formatNumber(l_aioOrderUnitRow.getQuantity());
                
                //���o������.��n�\��� = �����P��Params.��n��
                l_aioCashTransUnit.deliveryScheduledDate = l_aioOrderUnitRow.getDeliveryDate();
                
                //���o������..com�f�r�b�g���ώ���ԍ� = �����P��Params..com�f�r�b�g���ώ���ԍ�
                String l_strComDebitNumber = l_aioOrderUnitRow.getComDebitNumber();
                l_aioCashTransUnit.comDebitNumber = l_strComDebitNumber;
                
                //���o������.���l = get���l()�̖߂�l
                l_aioCashTransUnit.ioRemark = l_strRemark;

                //���o������.�ʉ݃R�[�h = �����P��Params.�ʉ݃R�[�h
                l_aioCashTransUnit.currencyCode = l_aioOrderUnitRow.getCurrencyCode();

                // 1.4.3)���o������..com�f�r�b�g���ώ���ԍ� != null �i�I�����C�������j�̏ꍇ
                WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
                    (WEB3AioMultiBankSettleControlService) Services.getService(
                        WEB3AioMultiBankSettleControlService.class);
                
                if(l_strComDebitNumber != null)
                {
                    // 1.4.3.1)�I�����C�������Ŏg�p�������ϋ@@�ւ̖��̂��擾����B 
                    //[����] 
                    //�⏕�����F �⏕�����I�u�W�F�N�g 
                    //���ʃR�[�h�F �����P��Params.���ʃR�[�h   
                    String l_strOrderRequestNumber = l_aioOrderUnitRow.getOrderRequestNumber();
                    String l_strFinInstitutionName = 
                        l_aioMultiBankSettleControlService.getFinInstitutionName(
                            l_subAccount,
                            l_strOrderRequestNumber);

                    // 1.4.3.2)�I�����C�����o���̏󋵂��擾����B
                    //[����] 
                    //�⏕�����F �⏕�����I�u�W�F�N�g 
                    //���ʃR�[�h�F �����P��Params.���ʃR�[�h 
                    //������ԁF �����P��Params.������� 
                    //��������敪�F �����P��Params.��������敪
                    String l_strCashTransSituation = 
                        l_aioMultiBankSettleControlService.getCashTransSituation(
                            l_subAccount,
                            l_strOrderRequestNumber,
                            l_aioOrderUnitRow.getOrderStatus(), 
                            l_aioOrderUnitRow.getCancelType());
                    
                    // 1.4.3.3)�v���p�e�B�Z�b�g
                    //�ȉ��̂悤�Ƀv���p�e�B���Z�b�g����B
                    //���o������.���Z�@@�֖� = get���Z�@@�֖�()�̖߂�l
                    l_aioCashTransUnit.paySchemeName = l_strFinInstitutionName;
                    
                    //���o������.������ = get���o����()�̖߂�l
                    l_aioCashTransUnit.payStatus = l_strCashTransSituation;
                    
                }
                
                // 1.4.4)���o������..com�f�r�b�g���ώ���ԍ� = null �i�I�����C�������ȊO�j�̏ꍇ
                if(l_strComDebitNumber == null)
                {
                    // 1.4.4.1)�����󋵂𔻒肷��B
                    //[����] 
                    //�⏕�����F �⏕�����I�u�W�F�N�g 
                    //�����P��Params�F �����P��Params�I�u�W�F�N�g
                    String l_strOrderStatus = this.getOrderStatus(l_subAccount, l_aioOrderUnitRow);
                    log.debug("l_strOrderStatus = " + l_strOrderStatus);
                    
                    // 1.4.4.1)�v���p�e�B�Z�b�g
                    //�ȉ��̂悤�Ƀv���p�e�B���Z�b�g����B
                    //���o������.������ = get������()�̖߂�l
                    l_aioCashTransUnit.payStatus = l_strOrderStatus;                   
                }
                
                // 1.4.4)ArrayList�ɗv�f��ǉ�����B
                //[����] 
                //arg0�F ���o�����׃I�u�W�F�N�g
                l_lisaioCashTransUnits.add(l_aioCashTransUnit);
            }   
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisaioCashTransUnits;
    }
    
    /**
     * (get���o������From�����A��)<BR>
     * �����A���e�[�u������A���o�����ׂ̃��X�g���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o���ꗗ�jget���o������From�����A���v �Q��<BR>
     * @@param l_subAccount - (�⏕�����I�u�W�F�N�g)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 4106FE4B0397
     */
    protected List getCashTransferFromCashinNotice(SubAccount l_subAccount) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromCashinNotice(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 1.3)ArrayList�̃C���X�^���X�𐶐�����B 
        List l_lisAioCashTransUnit = new Vector();
        
        try
        {
            // 1.1)�N�G���v���Z�b�T���擾����B 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // 1.2)�����A���e�[�u������A�ȉ��̏����̃��R�[�h���擾����B     
            //[����] 
            //�،���ЃR�[�h = �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            //���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l 
            //�ڋq�R�[�h = �⏕����.getMainAccount().getAccountCode()�̖߂�l 
            //(�U���� >= �V�X�e���^�C���X�^���v�̓��t���� or 
            //��Ɠ����̓��t���� = �V�X�e���^�C���X�^���v�̓��t�����j
            
            //[����] 
            //Row�^�C�v�F �����A��Row.TYPE 
            //Where�F "institution_code=? and branch_code=? and account_code=? and (transfer_date>=? or to_char(worked_timestamp)=?)" 
            //���X�g�F �ȉ��̍��ڂ̃��X�g 
            //�⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            //�⏕����.get����X().getBranchCode()�̖߂�l 
            //�⏕����.getMainAccount().getAccountCode()�̖߂�l 
            //�V�X�e���^�C���X�^���v�̓��t���� 
            //�V�X�e���^�C���X�^���v�̓��t���� 
            String l_strWhere = "institution_code = ? and branch_code = ? and account_code = ? " +
            "and (transfer_date >= ? or to_char(worked_timestamp,'YYYYMMDD') = ? )";
            
            Object[] l_objValues = new Object[]{
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd")};
            
            List l_lisRows =
                l_queryProcessor.doFindAllQuery(DepositInformRow.TYPE, l_strWhere, l_objValues);
            int l_intDepositInform = l_lisRows.size();
            
            // 1.4)�擾���������A��Params����Loop����
            
            
            for(int i=0; i<l_intDepositInform; i++)
            {
                DepositInformRow l_depositInformRow = (DepositInformRow) l_lisRows.get(i); 
                
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //(*2)�ȉ��̂悤�Ƀv���p�e�B���Z�b�g����
                //���o������.��t���t = �����A��Params.��Ɠ���
                l_aioCashTransUnit.receptionDate = l_depositInformRow.getWorkedTimestamp();
                
                //���o������.��� = 4�i�����A���j
                l_aioCashTransUnit.tradingType = WEB3AioTransUnitDef.CASH_IN_CONTACT;
                
                //���o������.���Z�@@�֖� = null
                l_aioCashTransUnit.paySchemeName = null;
                
                //���o������.���o�����z = �����A��Params.�����z
                l_aioCashTransUnit.cashTransAmt = String.valueOf(l_depositInformRow.getAmount());
                
                //���o������.��n�\��� = �����A��Params.�U����
                l_aioCashTransUnit.deliveryScheduledDate = l_depositInformRow.getTransferDate();
                
                //���o������..com�f�r�b�g���ώ���ԍ� = null
                l_aioCashTransUnit.comDebitNumber = null;
                
                //���o������.���l = null
                l_aioCashTransUnit.ioRemark = null;
                
                //���o������.������ = null
                l_aioCashTransUnit.payStatus = null;

                //���o������.�ʉ݃R�[�h = null
                l_aioCashTransUnit.currencyCode = null;

                //ArrayList�ɗv�f��ǉ�����B
                //[����] 
                //arg0�F ���o�����׃I�u�W�F�N�g
                l_lisAioCashTransUnit.add(l_aioCashTransUnit);
            }
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAioCashTransUnit;
    }
    
    /**
     * (get���o������From���o����)<BR>
     * ���Z�@@�֘A�g���o���󋵃e�[�u������A���o�����ׂ̃��X�g���擾����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i���o���ꗗ�jget���o������From���o���󋵁v �Q��<BR>
     * @@param l_subAccount - (�⏕�����I�u�W�F�N�g)
     * @@return List
     * @@throws WEB3BaseException
     * @@roseuid 410704B402BC
     */
    protected List getCashTransferFromCashTransferStatus(SubAccount l_subAccount)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromCashTransferStatus(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
        
        if(l_subAccount == null)
        {
            log.debug("SubAccount is null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
       
        try
        {
            // 1.1)�N�G���v���Z�b�T���擾����B 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            //=======remian zhou-yong NO.2 ��Q�[U00613 begin ========
            
            // 1.2) ���Z�@@�֘A�g���o���󋵃e�[�u������A�ȉ��̏����̃��R�[�h���擾����B 
            // 
            // [����] 
            // �،���ЃR�[�h = �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            // ���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l 
            // �ڋq�R�[�h = �⏕����.getMainAccount().getAccountCode()�̖߂�l 
            // ��n�\��� >= �V�X�e���^�C���X�^���v�̓��t���� 
            // [����] 
            // Row�^�C�v�F ���Z�@@�֘A�g���o����Row.TYPE 
            // Where�F "institution_code=? and branch_code=? and account_code=? and delivery_scheduled_date>=?" 
            // ���X�g�F �ȉ��̍��ڂ̃��X�g 
            // �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            // �⏕����.get����X().getBranchCode()�̖߂�l 
            // �⏕����.getMainAccount().getAccountCode()�̖߂�l 
            // �V�X�e���^�C���X�^���v�̓��t����
            
            String l_strWhere = "institution_code = ? and branch_code = ? and account_code = ? " +
                "and delivery_scheduled_date >= ? ";
                

            Object[] l_objValues = {
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd")
                };
                
            List l_lisRows =
                l_queryProcessor.doFindAllQuery(BankCashTransferStatusRow.TYPE, l_strWhere, l_objValues);
            
            //1.3) ArrayList( )
            List l_lisAioCashTransUnit = new Vector();

            //1.4) (*1) �擾�������Z�@@�֘A�g���o����Params����Loop����
            for(int i = 0; i < l_lisRows.size(); i++)
            {
                BankCashTransferStatusParams l_bankCashTransferStatusParams = 
                    (BankCashTransferStatusParams)l_lisRows.get(i);
                
                //1.4.1) 
                //(*2)���Z�@@�֘A�g���o����Params.�����敪 = 1�iOK�j and
                //���Z�@@�֘A�g���o����Params.����FLAG�i�����j = 2�i�������M�j and
                //���Z�@@�֘A�g���o����Params.����FLAG�i���ϊJ�n�j = 2�i�������M�j and
                //���Z�@@�֘A�g���o����Params.����FLAG�i���ό��ʁj in (1, 2, 8, 9, A)
                //�̏ꍇ�A���{
                if(WEB3TransactionStatusDef.OK.equals(l_bankCashTransferStatusParams.getTransactionStatus())
                    && WEB3OrderStatusFlagDef.RESPONSE_SEND.equals(l_bankCashTransferStatusParams.getOrderStatusFlag())
                    && WEB3StartStatusFlgDef.RESPONSE_SEND.equals(l_bankCashTransferStatusParams.getStartStatusFlag())
                    && (WEB3ResultStatusFlagDef.NOTIFY_RECEIPT.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.RESPONSE_SEND.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.REMAINING_CALCULATION_FAIL.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.REMAINING_CALCULATION_COMPLETE.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        || WEB3ResultStatusFlagDef.SETTLEMENT_RESEND_PROCESS_COMPLETE.equals(l_bankCashTransferStatusParams.getResultStatusFlag())
                        )
                   )
                {
                    //1.4.1.1) get�����P��(String, String, String, String, SubAccountTypeEnum)
                    //�A�C�e���̒�`
                    //�����P�ʂ��擾����B
                    //[����] 
                    //�،���ЃR�[�h�F ���Z�@@�֘A�g���o����Params.�،���ЃR�[�h 
                    //���X�R�[�h�F ���Z�@@�֘A�g���o����Params.���X�R�[�h 
                    //�ڋq�R�[�h�F ���Z�@@�֘A�g���o����Params.�ڋq�R�[�h 
                    //���ʃR�[�h�F ���Z�@@�֘A�g���o����Params.���ʃR�[�h 
                    //�⏕�����^�C�v�F 1�i�a��������j 
                    FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
                    WEB3AioOrderManager l_aioOrderManager = 
                        (WEB3AioOrderManager) l_finApp.getTradingModule(
                            ProductTypeEnum.AIO).getOrderManager();
                    
                    AioOrderUnit l_aioOrderUnit = null;
                    try
                    {
                        l_aioOrderUnit = l_aioOrderManager.getOrderUnit(
                            l_bankCashTransferStatusParams.getInstitutionCode(),
                            l_bankCashTransferStatusParams.getBranchCode(),
                            l_bankCashTransferStatusParams.getAccountCode(),
                            l_bankCashTransferStatusParams.getOrderRequestNumber(),
                            SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                    }
                    catch (NotFoundException l_ex)
                    {
                        log.debug("�����P�ʂ��擾�ł��Ȃ��I", l_ex);
                    }
                    //�����P�ʂ��擾�ł����ꍇ�́A�ȍ~�̏����̓X�L�b�v����B
                    //�iget���o������From�����P��()�ł̏����ΏۂƂȂ邽�߁j
                    if(l_aioOrderUnit != null)
                    {
                        continue;
                    }
                }
                
                //1.4.2) ���o������()
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //1.4.3) get���Z�@@�֖�(SubAccount, String)
                //�A�C�e���̒�`
                //�I�����C�������Ŏg�p�������ϋ@@�ւ̖��̂��擾����B
                //[����] 
                //�⏕�����F �⏕�����I�u�W�F�N�g 
                //���ʃR�[�h�F ���Z�@@�֘A�g���o����Params.���ʃR�[�h
                WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService
                = (WEB3AioMultiBankSettleControlService)Services.getService(
                    WEB3AioMultiBankSettleControlService.class);
                
                String l_strFinInstitutionName = l_aioMultiBankSettleControlService.getFinInstitutionName(
                    l_subAccount, l_bankCashTransferStatusParams.getOrderRequestNumber());
                
                //1.4.4) get���o����(SubAccount, String, OrderStatusEnum, String)
                //�A�C�e���̒�`
                //�I�����C�����o���̏󋵂��擾����B
                //[����] 
                //�⏕�����F �⏕�����I�u�W�F�N�g 
                //���ʃR�[�h�F ���Z�@@�֘A�g���o����Params.���ʃR�[�h 
                //������ԁF null 
                //��������敪�F null
                String l_strCashTransSituation = l_aioMultiBankSettleControlService.getCashTransSituation(
                    l_subAccount,
                    l_bankCashTransferStatusParams.getOrderRequestNumber(),
                    null,
                    null);

                //1.4.5)  (*3) �v���p�e�B�Z�b�g
                //(*3) �ȉ��̂悤�Ƀv���p�e�B���Z�b�g����B
                //���o������.��t���t = ���Z�@@�֘A�g���o����Params.��������
                l_aioCashTransUnit.receptionDate = l_bankCashTransferStatusParams.getOrderDateTime();
                
                //���o������.��� = 3�i�����j
                l_aioCashTransUnit.tradingType = WEB3AioTransUnitDef.CASH_IN;
                
                //���o������.���Z�@@�֖� = get���Z�@@�֖�()�̖߂�l
                l_aioCashTransUnit.paySchemeName = l_strFinInstitutionName;
                
                //���o������.���o�����z = ���Z�@@�֘A�g���o����Params.���z
                l_aioCashTransUnit.cashTransAmt = String.valueOf(l_bankCashTransferStatusParams.getAmount());
                
                //���o������.��n�\��� = ���Z�@@�֘A�g���o����Params.��n�\���
                l_aioCashTransUnit.deliveryScheduledDate = l_bankCashTransferStatusParams.getDeliveryScheduledDate();
                
                //���o������..com�f�r�b�g���ώ���ԍ� = ���Z�@@�֘A�g���o����Params..com�f�r�b�g���ώ���ԍ�
                l_aioCashTransUnit.comDebitNumber  = l_bankCashTransferStatusParams.getCenterPayId();
                
                //���o������.���l = null
                l_aioCashTransUnit.ioRemark = null;
                
                //���o������.������ = get���o����()�̖߂�l
                l_aioCashTransUnit.payStatus  = l_strCashTransSituation;  

                //���o������.�ʉ݃R�[�h = null
                l_aioCashTransUnit.currencyCode = null;

                // 1.4.6)ArrayList�ɗv�f��ǉ�����B
                //[����] 
                //arg0�F ���o�����׃I�u�W�F�N�g
                l_lisAioCashTransUnit.add(l_aioCashTransUnit);
                
                //=======remian zhou-yong NO.2 ��Q�[U00613 end ========
            }
            log.exiting(STR_METHOD_NAME);
            return l_lisAioCashTransUnit;
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    /**
     * (get����敪)<BR>
     * �����œn���ꂽ�����P�ʂ������敪�𔻒肵�A�ԋp����B <BR>
     * <BR>
     * �P�j�����P��Params.������� == 1001�i�o�������j �̏ꍇ <BR>
     * <BR>
     * �P�|�P�j <BR>
     *  �����P��Params.�o���\���敪 == null or <BR>
     *  �����P��Params.�o���\���敪 == "mf"�i���M���j <BR>
     *  �̏ꍇ�A0�i�o���j ��ԋp����B <BR>
     * <BR>
     * �P�|�Q�j<BR> 
     * �����P��Params.�o���\���敪 == "33"<BR> 
     *  �̏ꍇ�A1�i���o���j ��ԋp����B<BR>
     * <BR>
     * �P�|�R�j��L�̂P�|�P�j�A�P�|�Q�j�ȊO�̏ꍇ�A2�i��񗿏o���j ��ԋp����B<BR>
     * <BR>
     * �Q�j�����P��Params.������� = 1002�i���������j �̏ꍇ�A3�i�����j ��ԋp����B <BR>
     * <BR>
     * �R�j�����P��Params.������� = 1007�i�U�֒����i�a������犔��؋����j�j �̏ꍇ�A<BR>
     *      5�i�敨OP�؋����֏o���j ��ԋp����B <BR>
     * <BR>
     * �S�j�����P��Params.������� = 1008�i�U�֒����i����؋�������a����j�j �̏ꍇ�A<BR>
     *      6�i�敨OP�؋�����������j ��ԋp����B <BR>
     * <BR>
     * �T�j�����P��Params.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j �̏ꍇ�A<BR>
     *      7�i�M�p�ۏ؋��֏o���j ��ԋp����B <BR>
     * <BR>
     * �U�j�����P��Params.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j �̏ꍇ�A<BR>
     *      8�i�M�p�ۏ؋���������j ��ԋp����B <BR>
     * <BR>
     * �V�j�����P��Params.������� = 1011�i�U�֒����i�a�������ב֕ۏ؋��j�j<BR>
     * �܂��́A�����P��Params.������� = 1021�iCFD�U�֒����i�a�������CFD�����j�j�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@����ID�F �����P��Params.����ID<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@GFT�U�֏󋵂��擾����B<BR>
     * �@@�@@�@@�@@�@@�@@FX�f�[�^����T�[�r�X.Impl.getGFT�U�֏�()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F �ڋq.getInstitution.getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@���X�R�[�h�F �ڋq.getBranch.getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@���ʃR�[�h�F �����P��Params.���ʃR�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@GFT�U�֏�Params.���o���ꗗ����敪��ԋp����B<BR>
     * �@@�@@�@@�@@�@@�@@��getGFT�U�֏�()�̖߂�l��null�̏ꍇ�A9�iFX�ۏ؋��֏o���j ��ԋp����B<BR>
     * <BR>
     * �W�j�����P��Params.������� = 1012�i�U�֒����i�ב֕ۏ؋�����a����j�j<BR>
     * �܂��́A�����P��Params.������� = 1022�iCFD�U�֒����iCFD��������a����j�j�̏ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�ڋq�I�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@����ID�F �����P��Params.����ID<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@GFT�U�֏󋵂��擾����B<BR>
     * �@@�@@�@@�@@�@@�@@FX�f�[�^����T�[�r�X.Impl.getGFT�U�֏�()���R�[������B<BR>
     * �@@�@@�@@�@@�@@�@@[����]<BR>
     * �@@�@@�@@�@@�@@�@@�،���ЃR�[�h�F �ڋq.getInstitution.getInstitutionCode()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@���X�R�[�h�F �ڋq.getBranch.getBranchCode()�̖߂�l<BR>
     * �@@�@@�@@�@@�@@�@@���ʃR�[�h�F �����P��Params.���ʃR�[�h<BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@GFT�U�֏�Params.���o���ꗗ����敪��ԋp����B<BR>
     * �@@�@@�@@�@@�@@�@@��getGFT�U�֏�()�̖߂�l��null�̏ꍇ�A10�iFX�ۏ؋���������j ��ԋp����B<BR>
     * <BR>
     * �X�j�����P��Params.������� = 1013�i�U�֒����i�a�������O�����������j�j �̏ꍇ�A<BR>
     *      11�i�������������֏o���j ��ԋp����B <BR>
     * <BR>
     * �P�O�j�����P��Params.������� = 1014�i�U�֒����i�O��������������a����j�j �̏ꍇ�A<BR>
     *      12�i��������������������j ��ԋp����B <BR>
     * <BR>
     * �P�P�j�����P��Params.������� = 1017�i���̑��U�֒����i�a�������X�j�j �̏ꍇ�A<BR>
     *      13�i���̑��o���j ��ԋp����B <BR>
     * <BR>
     * �P�Q�j�����P��Params.������� = 1018�i���̑��U�֒����iX����a����j�j �̏ꍇ�A<BR>
     *      14�i���̑������j ��ԋp����B <BR>
     * <BR>
     * �P�R�j�����P��Params.������� = 1019�i�U�֒���(�a��������؋�)�j �̏ꍇ�A�܂���<BR>
     * �@@�@@�@@�����P��Params.������� = 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j<BR>
     * �@@�@@�@@�̏ꍇ�A15�i�S�ۃ��[���ԍρj ��ԋp����B<BR>
     * <BR>
     * @@param l_aioOrderUnitParams - (�����P��Params�I�u�W�F�N�g)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 410704B402BC
     */
    public String getTradingType(AioOrderUnitParams l_aioOrderUnitParams)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getTradingType(AioOrderUnitParams l_aioOrderUnitParams)";
        log.entering(STR_METHOD_NAME);
        
        if(l_aioOrderUnitParams == null)
        {
            log.debug("�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        String l_strTradingType = null;

        //�g���A�J�E���g�}�l�[�W���擾����
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_gentradeAccountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();

        //�P�j�����P��Params.������� == 1001�i�o�������j �̏ꍇ         
        if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitParams.getOrderType()))
        {
            //�P�|�P�j   �����P��Params.�o���\���敪 == null or 
            //�����P��Params.�o���\���敪 == "mf"�i���M���j 
            //�̏ꍇ�A0�i�o���j ��ԋp����B 
            if (l_aioOrderUnitParams.getPaymentApplicationDiv() == null ||
                WEB3AioPaymentApplicationDivDef.MF.equals(
                    l_aioOrderUnitParams.getPaymentApplicationDiv()))
            {
                l_strTradingType = WEB3AioTransUnitDef.CASH_OUT;
            }
            //�P�|�Q�j  �����P��Params.�o���\���敪 == "33" 
            //�̏ꍇ�A1�i���o���j ��ԋp����B
            else if (WEB3AioPaymentApplicationDivDef.MEMBERSHIP_FEE.equals(
                    l_aioOrderUnitParams.getPaymentApplicationDiv()))
            {
                l_strTradingType = WEB3AioTransUnitDef.DUES_CASH_OUT;
            }
            //�P�|�R�j��L�̂P�|�P�j�A�P�|�Q�j�ȊO�̏ꍇ�A2�i��񗿏o���j ��ԋp����B
            else
            {
                l_strTradingType = WEB3AioTransUnitDef.INFO_CASN_OUT;
            }
        }        
        //�Q�j�����P��Params.������� = 1002�i���������j �̏ꍇ�A3�i�����j ��ԋp����B 
        else if (OrderTypeEnum.CASH_IN.equals(l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.CASH_IN;
        }
        //�R�j�����P��Params.������� = 1007�i�U�֒����i�a������犔��؋����j�j �̏ꍇ�A
        //      5�i�敨OP�؋����֏o���j ��ԋp����B 
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.FUTURE_OP_MARGIN_CASHOUT;
        }
        //�S�j�����P��Params.������� = 1008�i�U�֒����i����؋�������a����j�j �̏ꍇ�A
        //      6�i�敨OP�؋�����������j ��ԋp����B 
        else if (OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.FUTURE_OP_MARGIN_TO_CASHIN;
        }
        //�T�j�����P��Params.������� = 1005�i�U�֒����i�a�������M�p�ۏ؋��j�j �̏ꍇ�A
        //      7�i�M�p�ۏ؋��֏o���j ��ԋp����B 
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MARGIN_GUARANTEE_CASHOUT;
        }
        //�U�j�����P��Params.������� = 1006�i�U�֒����i�M�p�ۏ؋�����a����j�j �̏ꍇ�A
        //      8�i�M�p�ۏ؋���������j ��ԋp����B 
        else if (OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MARGIN_GUARANTEE_TO_CASHIN;
        }

        //�V�j�����P��Params.������� = 1011�i�U�֒����i�a�������ב֕ۏ؋��j�j
        //�܂��́A�����P��Params.������� = 1021�iCFD�U�֒����i�a�������CFD�����j�j�̏ꍇ
        //�W�j�����P��Params.������� = 1012�i�U�֒����i�ב֕ۏ؋�����a����j�j
        //�܂��́A�����P��Params.������� = 1022�iCFD�U�֒����iCFD��������a����j�j�̏ꍇ
        else if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
            l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(
                l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.equals(
                l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            //�ڋq�I�u�W�F�N�g���擾����B
            //�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B
            //[����]
            //����ID�F �����P��Params.����ID
            MainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = l_gentradeAccountManager.getMainAccount(
                    l_aioOrderUnitParams.getAccountId());
            }
            catch (NotFoundException l_ex)
            {
                log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            //GFT�U�֏󋵂��擾����B
            //FX�f�[�^����T�[�r�X.Impl.getGFT�U�֏�()���R�[������B
            //[����]
            //�،���ЃR�[�h�F �ڋq.getInstitution.getInstitutionCode()�̖߂�l
            //���X�R�[�h�F �ڋq.getBranch.getBranchCode()�̖߂�l
            //���ʃR�[�h�F �����P��Params.���ʃR�[�h
            WEB3FXDataControlService l_fxDataControlService =
                (WEB3FXDataControlService)Services.getService(WEB3FXDataControlService.class);
            GftTransferStatusParams l_gftTransferStatusParams =
                l_fxDataControlService.getGFTTransferStatus(
                    l_mainAccount.getInstitution().getInstitutionCode(),
                    l_mainAccount.getBranch().getBranchCode(),
                    l_aioOrderUnitParams.getOrderRequestNumber());

            //GFT�U�֏�Params.���o���ꗗ����敪��ԋp����B
            if (l_gftTransferStatusParams != null)
            {
                l_strTradingType = l_gftTransferStatusParams.getIoListTradeDiv();
            }
            //��getGFT�U�֏�()�̖߂�l��null�̏ꍇ
            else
            {
                //�����P��Params.������� = 1011�i�U�֒����i�a�������ב֕ۏ؋��j�j
                //�܂��́A�����P��Params.������� = 1021�iCFD�U�֒����i�a�������CFD�����j�j�̏ꍇ
                //9�iFX�ۏ؋��֏o���j ��ԋp����B
                if (OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.equals(
                    l_aioOrderUnitParams.getOrderType())
                    || OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT.equals(
                        l_aioOrderUnitParams.getOrderType()))
                {
                    l_strTradingType = WEB3AioTransUnitDef.FX_CASHOUT;
                }
                else
                {
                    //�����P��Params.������� = 1012�i�U�֒����i�ב֕ۏ؋�����a����j�j
                    //�܂��́A�����P��Params.������� = 1022�iCFD�U�֒����iCFD��������a����j�j�̏ꍇ
                    //10�iFX�ۏ؋���������j ��ԋp����B
                    l_strTradingType = WEB3AioTransUnitDef.FX_TO_CASHIN;
                }
            }
        }

        //�X�j�����P��Params.������� = 1013�i�U�֒����i�a�������O�����������j�j �̏ꍇ�A
        //      11�i�������������֏o���j ��ԋp����B 
        else if (OrderTypeEnum.TRANSFER_TO_FEQ.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MIDIUM_TERM_GOV_EQUITY_ACCOUNT_CASHOUT;
        }
        //�P�O�j�����P��Params.������� = 1014�i�U�֒����i�O��������������a����j�j �̏ꍇ�A
        //      12�i��������������������j ��ԋp����B 
        else if (OrderTypeEnum.TRANSFER_FROM_FEQ.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.MIDIUM_TERM_GOV_EQUITY_ACCOUNT_TO_CASHIN;
        }
        //�P�P�j�����P��Params.������� = 1017�i���̑��U�֒����i�a�������X�j�j �̏ꍇ�A
        //      13�i���̑��o���j ��ԋp����B 
        else if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.OTHER_CASHOUT;
        }
        //�P�Q�j�����P��Params.������� = 1018�i���̑��U�֒����iX����a����j�j �̏ꍇ�A
        //      14�i���̑������j ��ԋp����B
        else if (OrderTypeEnum.FROM_OTHER_TRANSFER.equals(
                l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.OTHER_CASHIN;
        }
        // �P�R�j�����P��Params.������� = 1019�i�U�֒���(�a��������؋�)�j �̏ꍇ�A�܂���
        //�@@�����P��Params.������� = 1020�i�U�֒����i�a���������I���b�N�X�N���W�b�g�j�j  �̏ꍇ
        //  15�i�S�ۃ��[���ԍρj ��ԋp����B
        else if (OrderTypeEnum.FROM_DEPOSIT_AMOUNT_DSK.equals(
            l_aioOrderUnitParams.getOrderType())
            || OrderTypeEnum.TO_ORIX_CREDIT.equals(l_aioOrderUnitParams.getOrderType()))
        {
            l_strTradingType = WEB3AioTransUnitDef.SECURITY_LOAN_REPAY;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strTradingType;
    }
    
   /**
    * (get���l)
    * �����œn���ꂽ�����P�ʂ�����l�ɐݒ肷�鍀�ڂ𔻒肵�A�ԋp����B <BR>
    * <BR>
    * �P�j�����P��Params.������� == 1001�i�o�������j �̏ꍇ <BR>
    * <BR>
    * �����P��Params.�o���\���敪 ��ԋp����B <BR>
    * <BR>
    * �Q�j�����P��Params.�����J�e�S�� = 15�i�ב֕ۏ؋��U�ցj �̏ꍇ <BR>
    * <BR>
    * �Q�|�P�j�ڋq�I�u�W�F�N�g���擾����B <BR>
    * <BR>
    * �g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B <BR>
    * <BR>
    * [����] <BR>
    * ����ID�F �����P��Params.����ID <BR>
    * <BR>
    * �Q�|�Q�jGFT�U�֏󋵂��擾����B <BR>
    * <BR>
    * FX�f�[�^����T�[�r�X.Impl.getGFT�U�֏�()���R�[������B <BR>
    * <BR>
    * [����] <BR>
    * �،���ЃR�[�h�F �ڋq.getInstitution.getInstitutionCode()�̖߂�l <BR>
    * ���X�R�[�h�F �ڋq.getBranch.getBranchCode()�̖߂�l <BR>
    * ���ʃR�[�h�F �����P��Params.���ʃR�[�h <BR>
    * <BR>
    * GFT�U�֏�Params.�R�[�X�敪��ԋp����B<BR>
    * ��getGFT�U�֏�()�̖߂�l��null�̏ꍇ�́Anull��ԋp����B<BR>
    * <BR>
    * �R�j�����P��Params.������� == 1017 or 1018�i���̑��U�֒����j �̏ꍇ <BR>
    * <BR>
    * �����P��Params.�U�֋L�q ��ԋp����B <BR>
    * <BR>
    * �������P��Params.�U�֋L�q == 98(�E�v�C�ӓ��͌p��)�̏ꍇ�A99�i���̑��j���Z�b�g����B <BR>
    * <BR>
    * �S�j��L�̏����ȊO�̏ꍇ <BR>
    * <BR>
    * null ��ԋp����B <BR>
    * <BR>
    * @@param l_aioOrderUnitParams - (�����P��Params�I�u�W�F�N�g)
    * @@return String
    * @@throws WEB3BaseException
    * @@roseuid 410704B402BC
    */
   public String getRemark(AioOrderUnitParams l_aioOrderUnitParams)
       throws WEB3BaseException 
   {
       final String STR_METHOD_NAME =
           "getRemark(AioOrderUnitParams l_aioOrderUnitParams)";
       log.entering(STR_METHOD_NAME);
       
       if(l_aioOrderUnitParams == null)
       {
           log.debug("�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + "." + STR_METHOD_NAME);
       }
       
       //�P�j�����P��Params.������� == 1001�i�o�������j �̏ꍇ 
       //�����P��Params.�o���\���敪 ��ԋp����B 
       if (OrderTypeEnum.CASH_OUT.equals(l_aioOrderUnitParams.getOrderType()))
       {
           log.debug("�����P��Params.������� == 1001�i�o�������j �̏ꍇ");
           return l_aioOrderUnitParams.getPaymentApplicationDiv();
       }
       
       //�Q�j�����P��Params.�����J�e�S�� = 15�i�ב֕ۏ؋��U�ցj �̏ꍇ 
       //�Q�|�P�j�ڋq�I�u�W�F�N�g���擾����B 
       //�g���A�J�E���g�}�l�[�W��.get�ڋq()���R�[������B 
       //[����] 
       //����ID�F �����P��Params.����ID
       FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
       WEB3GentradeAccountManager l_web3GentradeAccountManager =
           (WEB3GentradeAccountManager) l_finApp.getAccountManager();
       if (OrderCategEnum.FX.equals(l_aioOrderUnitParams.getOrderCateg()))
       {
           log.debug("�����P��Params.�����J�e�S�� = 15�i�ב֕ۏ؋��U�ցj �̏ꍇs");
           MainAccount l_mainAccount = null;
           try
           {
               l_mainAccount = 
                   l_web3GentradeAccountManager.getMainAccount(
                           l_aioOrderUnitParams.getAccountId());
               
           }
           catch (NotFoundException l_ex)
           {
               log.debug("__NotFoundException__", l_ex);
               //��O���X���[����
               log.exiting(STR_METHOD_NAME);
               throw new WEB3SystemLayerException(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                   getClass().getName() + "." + STR_METHOD_NAME,
                   l_ex.getMessage(),
                   l_ex);
           }        
           //�Q�|�Q�jGFT�U�֏󋵂��擾����B 
           //FX�f�[�^����T�[�r�X.Impl.getGFT�U�֏�()���R�[������B 
           //[����] 
           //�،���ЃR�[�h�F �ڋq.getInstitution.getInstitutionCode()�̖߂�l 
           //���X�R�[�h�F �ڋq.getBranch.getBranchCode()�̖߂�l 
           //���ʃR�[�h�F �����P��Params.���ʃR�[�h 
           
           // FX�f�[�^����T�[�r�X���擾
           WEB3FXDataControlService l_fxDataControlService = 
               (WEB3FXDataControlService) Services.getService(WEB3FXDataControlService.class);
           GftTransferStatusParams l_gftTransferStatusParams = 
           l_fxDataControlService.getGFTTransferStatus(
                   l_mainAccount.getInstitution().getInstitutionCode(), 
                   l_mainAccount.getBranch().getBranchCode(),  
                   l_aioOrderUnitParams.getOrderRequestNumber());

           //GFT�U�֏�Params.�R�[�X�敪��ԋp����B
           //��getGFT�U�֏�()�̖߂�l��null�̏ꍇ�́Anull��ԋp����B
           if (l_gftTransferStatusParams != null)
           {
               log.exiting(STR_METHOD_NAME);
               return l_gftTransferStatusParams.getCourseDiv();
           }

           log.exiting(STR_METHOD_NAME);
           return null;
       }  
       //�R�j�����P��Params.������� == 1017 or 1018�i���̑��U�֒����j �̏ꍇ 
       //�����P��Params.�U�֋L�q ��ԋp����B 
       //�������P��Params.�U�֋L�q == 98(�E�v�C�ӓ��͌p��)�̏ꍇ�A99�i���̑��j���Z�b�g����B 
       if (OrderTypeEnum.TO_OTHER_TRANSFER.equals(l_aioOrderUnitParams.getOrderType()) ||
           OrderTypeEnum.FROM_OTHER_TRANSFER.equals(l_aioOrderUnitParams.getOrderType()))
       {
           log.debug("�����P��Params.������� == 1017 or 1018�i���̑��U�֒����j �̏ꍇ");
           if (WEB3RemarkCodeDef.REMARK_INPUT_REQUEST_CONTINUE_THREE.equals(l_aioOrderUnitParams.getDescription()))
           {                   
               log.debug("�����P��Params.�U�֋L�q == 98(�E�v�C�ӓ��͌p��)�̏ꍇ");
               return WEB3RemarkCodeDef.BLANK;
           }
           else
           {
               log.debug("�����P��Params.�U�֋L�q" + l_aioOrderUnitParams.getDescription());
               return l_aioOrderUnitParams.getDescription();
           }
       }
       log.exiting(STR_METHOD_NAME);
       return null;
   }
   /**
    * (get���o������FromGFT�U�֏�)
    * GFT�U�֏󋵃e�[�u������A���o�����ׂ̃��X�g���擾����B <BR>
    * <BR>
    * �V�[�P���X�} <BR>
    * �u�i���o���ꗗ�jget���o������FromGFT�U�֏󋵁v �Q�� <BR>
    * <BR>
    * @@param l_subAccount - (�⏕�����I�u�W�F�N�g)
    * @@return List
    * @@throws WEB3BaseException
    * @@roseuid 410704B402BC
    */
    public List getCashTransferFromGftTransferStatus(SubAccount l_subAccount)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            "getCashTransferFromGftTransferStatus(SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
      
        if(l_subAccount == null)
        {
            log.debug("�Y���p�����[�^��Null�l�͐ݒ�ł��܂���B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }       

        // 1.3 ArrayList�̃C���X�^���X�𐶐�����B 
        List l_lisAioCashTransUnit = new Vector();
        
        try
        {
            // 1.1 �N�G���v���Z�b�T���擾����B 
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            // 1.2 GFT�U�֏󋵃e�[�u������A�ȉ��̏����̃��R�[�h���擾����B 
            //[����] 
            //�،���ЃR�[�h = �⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            //���X�R�[�h = �⏕����.get����X().getBranchCode()�̖߂�l 
            //�ڋq�R�[�h = �⏕����.getMainAccount().getAccountCode()�̖߂�l 
            //��n�\��� >= �V�X�e���^�C���X�^���v�̓��t���� 
            //[����] 
            //Row�^�C�v�F GFT�U�֏�Row.TYPE 
            //Where�F "institution_code=? and branch_code=? and account_code=? and transfer_date>=?" 
            //���X�g�F �ȉ��̍��ڂ̃��X�g 
            //�⏕����.getInstitution().getInstitutionCode()�̖߂�l 
            //�⏕����.get����X().getBranchCode()�̖߂�l 
            //�⏕����.getMainAccount().getAccountCode()�̖߂�l 
            //�V�X�e���^�C���X�^���v�̓��t���� 

            String l_strWhere = "institution_code = ? and branch_code = ? " +
                    "and account_code = ? and transfer_date >= ? ";
            
            Object[] l_objValues = new Object[]{
                l_subAccount.getInstitution().getInstitutionCode(),
                l_subAccount.getMainAccount().getBranch().getBranchCode(),
                l_subAccount.getMainAccount().getAccountCode(),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd")
                };
            
            List l_lisRows =
                l_queryProcessor.doFindAllQuery(
                        GftTransferStatusRow.TYPE, 
                        l_strWhere, 
                        l_objValues);
            
            int l_intGftTransferSize = l_lisRows.size();
            log.debug("l_intGftTransferSize = " + l_intGftTransferSize);
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3AioOrderManager l_web3AioOrderMgr =
                (WEB3AioOrderManager) l_finApp.getTradingModule(
                    ProductTypeEnum.AIO).getOrderManager();
            
            //1.4 �擾����GFT�U�֏�Params����Loop����
                        
            for(int i = 0; i < l_intGftTransferSize; i++)
            {
                GftTransferStatusRow l_gftTransferStatusRow = 
                    (GftTransferStatusRow) l_lisRows.get(i); 
                                
                log.debug("GFT�U�֏�Row = " + l_gftTransferStatusRow);
                //1.4.1 �����P�ʂ��擾����B 
                //[����] 
                //�،���ЃR�[�h�F GFT�U�֏�Params.�،���ЃR�[�h 
                //���X�R�[�h�F GFT�U�֏�Params.���X�R�[�h 
                //�ڋq�R�[�h�F GFT�U�֏�Params.�ڋq�R�[�h 
                //���ʃR�[�h�F GFT�U�֏�Params.���ʃR�[�h 
                //�⏕�����^�C�v�F 1�i�a��������j 
                boolean l_blnNotFoundOrderUnit = false;
                AioOrderUnitRow l_aioOrderUnitRow = null;
                String l_strTransferStatus = null;
                WEB3AioFXTransStatusUtility l_aioFxStatusUtil = new WEB3AioFXTransStatusUtility();
                String l_Status = null;

                try
                {
                    l_aioOrderUnitRow = this.getOrderUnitRow(
                        l_gftTransferStatusRow.getInstitutionCode(), 
                        l_gftTransferStatusRow.getBranchCode(), 
                        l_gftTransferStatusRow.getAccountCode(), 
                        l_gftTransferStatusRow.getOrderRequestNumber(), 
                        SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
                }
                catch (NotFoundException l_ex)
                {
                    log.debug("__NotFoundException__" + l_ex);
                    l_blnNotFoundOrderUnit = true;

                } catch (WEB3BusinessLayerException be) {
                    throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00739,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
                }

                //�����P��.�E�v�R�[�h��72�i����؋����F���)�܂���71�i����؋����F����)�̏ꍇ�A
                //�������X�L�b�v���Ȃ��B
                if (l_aioOrderUnitRow != null
                    && !WEB3StringTypeUtility.isEmpty(
                        l_aioOrderUnitRow.getRemarkCode()))
                {

                    if (WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_OSAKA.equals(
                        l_aioOrderUnitRow.getRemarkCode())
                        || WEB3FxTransferMasterRemarkCodeDef.TOCK_FUTURES_MARGIN_TOKYO.equals(
                            l_aioOrderUnitRow.getRemarkCode()))
                    {
                        l_blnNotFoundOrderUnit = true;

                        //�U�֏󋵂��擾����ׁA�U�փL���[�e�[�u�����擾����B
                        //(PK:�،���ЃR�[�h�A���X�R�[�h�A�ڋq�R�[�h�A���ʃR�[�h�j
                        HostTransferAcceptRow l_HostTransferAcceptRow = null;

                        try {

                            l_HostTransferAcceptRow =
                                HostTransferAcceptDao.findRowByPk(l_subAccount.getInstitution().getInstitutionCode(),
                                                                  l_subAccount.getMainAccount().getBranch().getBranchCode(),
                                                                  l_subAccount.getMainAccount().getAccountCode(),
                                                                  l_gftTransferStatusRow.getOrderRequestNumber());

                            //DB����擾�ł����ꍇ�A�����敪���`�F�b�N���A9�F�G���[�̏ꍇ�̓Z�b�g����B�i����ȊO��DefaultStatus.OTHER�j
                            if (l_HostTransferAcceptRow != null && !WEB3StringTypeUtility.isEmpty(l_HostTransferAcceptRow.getStatus())) {

                                l_Status = l_HostTransferAcceptRow.getStatus();

                            } else {
                                l_Status = null;
                            }

                        } catch (DataNetworkException l_dqex) {
                            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���:", l_dqex);
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3SystemLayerException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                            this.getClass().getName() + "." + STR_METHOD_NAME,
                            l_dqex.getMessage(),
                            l_dqex);

                        //DB�Ɉ���������Ȃ������ꍇ�A��t�L���[�e�[�u���̒l��DefaultStatus.OTHER���Z�b�g����B
                        } catch (DataQueryException l_ex) {
                            log.debug("��t�L���[TBL�f�[�^�����B " + l_ex);
                            l_Status = null;

                        }

                        //get�����󋵃��b�Z�[�W���s��

                        l_strTransferStatus = l_aioFxStatusUtil.getResult(
                            l_gftTransferStatusRow.getTransferStatusDiv(),
                            l_gftTransferStatusRow.getSendRcvDiv(),
                            l_gftTransferStatusRow.getResultCode(),
                            l_aioOrderUnitRow.getOrderStatus().intValue() + "",
                            l_aioOrderUnitRow.getCancelType(),
                            l_Status);
                            log.debug("�U�֏�Result = " + l_strTransferStatus);

                    }

                }

                log.debug("l_blnNotFoundOrderUnit = " + l_blnNotFoundOrderUnit);
                if (!l_blnNotFoundOrderUnit)
                {
                    continue;
                }


                //1.4.2 ���o�����ׂ̃C���X�^���X�𐶐�����B 
                WEB3AioCashTransUnit l_aioCashTransUnit = new WEB3AioCashTransUnit();
                
                //1.4.3 �U�֏󋵂��擾����B 
                //[����] 
                //�U�֏󋵋敪�F GFT�U�֏�Params.�U�֏󋵋敪 
                //����M�敪�F GFT�U�֏�Params.����M�敪 
                //��t���ʃR�[�h�F GFT�U�֏�Params.��t���ʃR�[�h 

                if (WEB3StringTypeUtility.isEmpty(l_strTransferStatus)) {
                    l_strTransferStatus = 
                        this.getTransferStatus(
                            l_gftTransferStatusRow.getTransferStatusDiv(), 
                            l_gftTransferStatusRow.getSendRcvDiv(), 
                            l_gftTransferStatusRow.getResultCode());

                    log.debug("GFT�U�֏�Params.�U�֏󋵋敪 = " + l_gftTransferStatusRow.getTransferStatusDiv());
                    log.debug("GFT�U�֏�Params.����M�敪  = " + l_gftTransferStatusRow.getSendRcvDiv());
                    log.debug("GFT�U�֏�Params.��t���ʃR�[�h = " + l_gftTransferStatusRow.getResultCode());

                    log.debug("�U�֏� = " + l_strTransferStatus);

                }

                //1.4.4 (*2) �v���p�e�B�Z�b�g
                //(*2) �ȉ��̂悤�Ƀv���p�e�B���Z�b�g����B

                //���o������.��t���t = GFT�U�֏�Params.�쐬���t
                l_aioCashTransUnit.receptionDate = 
                    l_gftTransferStatusRow.getCreatedTimestamp();

                //���o������.��� = GFT�U�֏�Params.���o���ꗗ����敪
                l_aioCashTransUnit.tradingType =
                    l_gftTransferStatusRow.getIoListTradeDiv();
                
                //���o������.���Z�@@�֖� = null
                l_aioCashTransUnit.paySchemeName = null;
                
                //���o������.���o�����z = GFT�U�֏�Params.���z
                l_aioCashTransUnit.cashTransAmt = l_gftTransferStatusRow.getAmount() + "";
                
                //���o������.��n�\��� = GFT�U�֏�Params.��n�\���
                l_aioCashTransUnit.deliveryScheduledDate = 
                    WEB3DateUtility.getDate(
                            l_gftTransferStatusRow.getTransferDate(), "yyyyMMdd");
                
                //���o������..com�f�r�b�g���ώ���ԍ� = null
                l_aioCashTransUnit.comDebitNumber = null;
                
                //���o������.���l = GFT�U�֏�Params.�R�[�X�敪
                l_aioCashTransUnit.ioRemark =
                    l_gftTransferStatusRow.getCourseDiv();
                log.debug("���o������.���l = " + l_aioCashTransUnit.ioRemark);
                
                //���o������.������ = get�U�֏�()�̖߂�l
                l_aioCashTransUnit.payStatus = l_strTransferStatus;
                log.debug("���o������.������ = " + l_aioCashTransUnit.payStatus);

                //���o������.�ʉ݃R�[�h = null
                l_aioCashTransUnit.currencyCode = null;

                //ArrayList�ɗv�f��ǉ�����B
                //[����] 
                //arg0�F ���o�����׃I�u�W�F�N�g
                l_lisAioCashTransUnit.add(l_aioCashTransUnit);
            }
        }
        catch (DataException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisAioCashTransUnit;
    }
    
    /**
     * (get�U�֏�)
     * �����œn���ꂽGFT�U�֏󋵂̍��ڂ���U�֏󋵂𔻒肵�A���茋�ʂ�ԋp����B<BR> 
     * <BR>
     * �ڍׂ́A�u���o���X�e�[�^�X�\���\.xls�v�̃V�[�g�u�ב֕ۏ؋��A�g(2)�v�Q�� <BR>
     * <BR>
     * @@param l_strTransferStatusDiv - (�U�֏󋵋敪)
     * @@param l_strSendRcvDiv - (����M�敪)
     * @@param l_strResultCode - (��t���ʃR�[�h)
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 410704B402BC
     */
     public String getTransferStatus(
             String l_strTransferStatusDiv, 
             String l_strSendRcvDiv, 
             String l_strResultCode)
         throws WEB3BaseException 
     {
         final String STR_METHOD_NAME =
             "getTransferStatus(String l_strTransferStatusDiv, " + 
             "String l_strSendRcvDiv, String l_strResultCode)";
         log.entering(STR_METHOD_NAME);
         
         WEB3AioFXTransStatusUtility l_aioFxStatusUtil = new WEB3AioFXTransStatusUtility();
         String l_strTransferStatus = l_aioFxStatusUtil.getResult(
                 l_strTransferStatusDiv, 
                 l_strSendRcvDiv, 
                 l_strResultCode, 
                 null, 
                 null, 
                 null);
         log.debug("�U�֏�Result = " + l_strTransferStatus);
         
         log.exiting(STR_METHOD_NAME);
         return l_strTransferStatus;
     }

     /**
      * (get�����P��)<BR>
      * �����P�ʃI�u�W�F�N�g���擾����B<BR>
      * <BR>
      * �P�j�ȉ��̃I�u�W�F�N�g���擾����B<BR>
      * 
      * �،���ЃI�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getInstitutiion(����.�،���ЃR�[�h)<BR>
      * ���X�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.get���X(����.�،���ЃR�[�h, ����.���X�R�[�h)<BR>
      * �����i�ڋq�j�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getMainAccount(�،���ЃI�u�W�F�N�g, ���X�I�u�W�F�N�g, 
      * ����.�ڋq�R�[�h)<BR>
      * �⏕�����I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getSubAccount(����.����ID, ����.�⏕�����^�C�v)<BR>
      * <BR>
      * �Q�j�����P�ʃI�u�W�F�N�g���擾����B<BR>
      * <BR>
      * [��������]<BR>
      * ����ID = ����.����ID<BR>
      * �⏕����ID = �⏕����.�⏕����ID<BR>
      * ���XID = ���X.���XID<BR>
      * �����^�C�v = ProductTypeEnum.CASH<BR>
      * ���ʃR�[�h = ����.���ʃR�[�h<BR>
      * <BR>
      * �Y���s�����݂����ꍇ�A�����s��v�����ꍇ�͗�O���X���[����B<BR>
      * <BR>
      *         class: WEB3BusinessLayerException<BR>
      *         tag:   BUSINESS_ERROR_00739<BR>
      * <BR>
      * @@param l_strInstitutionCode - (�،���ЃR�[�h)
      * @@param l_strBranchCode - (���X�R�[�h)
      * @@param l_strAccountCode - (�ڋq�R�[�h)
      * @@param l_strOrderRequestNumber - (���ʃR�[�h)
      * @@param l_subAccountType - (�⏕�����^�C�v)
      * @@return AioOrderUnit
      * @@throws NotFoundException, WEB3BaseException
      * @@roseuid 
      */
     public AioOrderUnitRow getOrderUnitRow(
         String l_strInstitutionCode, 
         String l_strBranchCode, 
         String l_strAccountCode, 
         String l_strOrderRequestNumber, 
         SubAccountTypeEnum l_subAccountType)        
         throws WEB3BaseException, NotFoundException
     {
         String STR_METHOD_NAME = "getOrderUnit(" + 
             "String l_strBranchCode, String l_strAccountCode, " + 
             "String l_strOrderRequestNumber, SubAccountTypeEnum l_subAccountType)";
             
         log.entering(STR_METHOD_NAME);        
         
         //�P�j�ȉ��̃I�u�W�F�N�g���擾����B
         FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
         
         //�g���A�J�E���g�}�l�[�W���擾����    
         WEB3GentradeAccountManager l_web3GentradeAccountManager =
             (WEB3GentradeAccountManager) l_finApp.getAccountManager();
         
         MainAccount l_mainAccount = null;
         SubAccount l_subAccount = null;
         Branch l_branch = null;
         AioOrderUnitRow l_aioOrderUnitRow = null;

         try
         {        
             //Throw NotFoundException
             //�،���ЃI�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getInstitutiion(����.�،���ЃR�[�h) 
             Institution l_institution = l_web3GentradeAccountManager.getInstitution(
                 l_strInstitutionCode);
             log.debug("�،����.get�،����Code() = " + l_institution.getInstitutionCode());
             
             //���X�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.get���X(�،���ЃI�u�W�F�N�g, ����.���X�R�[�h) 
             l_branch = l_web3GentradeAccountManager.getBranch(
                 l_institution, l_strBranchCode);
             log.debug("���X.get���XCode() = " + l_branch.getBranchCode());
             
             //�����i�ڋq�j�I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getMainAccount
             //(�،���ЃI�u�W�F�N�g, ���X�I�u�W�F�N�g, ����.�ڋq�R�[�h) 
             l_mainAccount = l_web3GentradeAccountManager.getMainAccount(
                 l_institution, l_branch, l_strAccountCode);
                 
             log.debug("�����i�ڋq�j�I�u�W�F�N�g.get����Code = " + l_mainAccount.getAccountId());
             
             //�⏕�����I�u�W�F�N�g = �g���A�J�E���g�}�l�[�W��.getSubAccount(����.����ID, ����.�⏕�����^�C�v) 
             l_subAccount = l_web3GentradeAccountManager.getSubAccount(
                 l_mainAccount.getAccountId(), l_subAccountType);
             
             log.debug("�⏕����.get�⏕����Id() = " + l_subAccount.getSubAccountId());
         }
         catch (NotFoundException l_ex)
         {
             log.error("__NotFoundException__", l_ex);
             //��O���X���[����
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                 getClass().getName() + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         try
         {
             //�Q�j�����P�ʃI�u�W�F�N�g���擾����B
             List l_lisRows = null;
             String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                 "and branch_id = ? and product_type = ? and order_request_number = ?";
             
             //����ID = ����.����ID
             long l_lngAccountId = l_mainAccount.getAccountId();
             
             //�⏕����ID = �⏕����.�⏕����ID
             long l_lngSubAccountId = l_subAccount.getSubAccountId();
             
             //���XID = ���X.���XID
             long l_lngBranchId = l_branch.getBranchId();           
             
             log.debug("����ID  = " + l_lngAccountId);
             log.debug("�⏕����ID  = " + l_lngSubAccountId);
             log.debug("���XID  = " + l_lngBranchId);
             log.debug("�����^�C�v  = " + ProductTypeEnum.CASH.intValue());
             log.debug("���ʃR�[�h  = " + l_strOrderRequestNumber);
             
             Object l_bindVars[] = {
                 new Long(l_lngAccountId),
                 new Long(l_lngSubAccountId),
                 new Long(l_lngBranchId),
                 ProductTypeEnum.CASH, 
                 l_strOrderRequestNumber };
             
             l_lisRows =
                 Processors.getDefaultProcessor().doFindAllQuery(
                     AioOrderUnitRow.TYPE,
                     l_strWhereClause,
                     null,
                     l_bindVars);
             
             //�Y���s�����݂����ꍇ�A�����s��v�����ꍇ�͗�O���X���[����B
             if (l_lisRows.size() > 1)
             {                
                 log.debug("�Y�����钍����񂪕���������܂����B");
                 throw new WEB3BusinessLayerException(
                     WEB3ErrorCatalog.BUSINESS_ERROR_00739,
                     this.getClass().getName() + "." + STR_METHOD_NAME,
                     "�Y�����钍����񂪕���������܂����Bsize = " + l_lisRows.size());
             }
             else if (l_lisRows.size() == 1)
             {
                 //�����P�ʃI�u�W�F�N�g���擾����B
                 l_aioOrderUnitRow = (AioOrderUnitRow)l_lisRows.get(0);            
                 log.debug("�����P��Row : " + l_aioOrderUnitRow);
     
                 log.exiting(STR_METHOD_NAME);
             }
             else 
             {
                 log.debug("�f�[�^�s�����G���[");
                 //��O���X���[����
                 throw new NotFoundException("�����P�ʃI�u�W�F�N�g���擾�ł��Ȃ�");
             }
             return l_aioOrderUnitRow;
         }
         catch (DataQueryException l_ex)
         {
             log.error("__DataQueryException__", l_ex);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
         catch (DataNetworkException l_ex)
         {
             log.error("__DataNetworkException__", l_ex);
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 l_ex.getMessage(),
                 l_ex);
         }
     }
}
@
