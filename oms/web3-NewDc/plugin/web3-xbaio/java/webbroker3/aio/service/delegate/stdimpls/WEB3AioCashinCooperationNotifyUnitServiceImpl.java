head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.35.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����A�g�ʒm�ꌏ�T�[�r�XImpl(WEB3AioCashinCooperationNotifyUnitServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 ����(���u) �V�K�쐬
Revesion History : 2006/08/31 �Ԑi (���u) ���f��No.627
Revesion History : 2006/09/18 ���G�� (���u)���f��No.648, No.649
Revesion History : 2006/11/14 ���G�� (���u)  �c�a�X�V�d�l�@@No.131
Revesion History : 2006/11/14 ���G�� (���u)  �c�a�X�V�d�l�@@No.132
Revesion History : 2007/03/12 �����q (���u)  ���f��No.713
Revesion History : 2007/07/12 �Ј��� (���u)  ���f��No.735
Revesion History : 2007/07/28 �Ј���(���u) �d�l�ύX���f��746
Revesion History : 2007/08/06 �����F(���u) �d�l�ύX���f��750
Revesion History : 2009/02/06 �đo�g (���u) ���f��No.1096,No.1105,No.1106,No.1107
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;

import webbroker3.aio.WEB3AioCashinCooperationOrderUpdateInterceptor;
import webbroker3.aio.WEB3AioNewOrderSpec;
import webbroker3.aio.WEB3AioOrderManager;
import webbroker3.aio.data.BankDepositErrorHistoryDao;
import webbroker3.aio.data.BankDepositErrorHistoryParams;
import webbroker3.aio.data.BankDepositNotifyDao;
import webbroker3.aio.data.BankDepositNotifyParams;
import webbroker3.aio.data.BankDepositNotifyRow;
import webbroker3.aio.define.WEB3AioErrorCommentDef;
import webbroker3.aio.service.delegate.WEB3AioCashinCooperationNotifyUnitService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3CompanyFormationDivDef;
import webbroker3.common.define.WEB3ErrorCommentDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3UpdatePersonDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3HostReqOrderNumberManageService;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �����A�g�ʒm�ꌏ�T�[�r�XImpl<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyUnitServiceImpl
    implements WEB3AioCashinCooperationNotifyUnitService
{
    /**
     *  ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioCashinCooperationNotifyUnitServiceImpl.class);
    
    /**
     * (notify�����A�g)<BR>
     * �����ʒm�̒����o�^����B<BR>
     * <BR>
     * �V�[�P���X�}�u(�����A�g�ʒm�ꌏ�T�[�r�XImpl).notify�����A�g�v �Q��<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - �����ʒmParams<BR>
     * @@param l_mainAccount  �ڋq <BR>
     * @@throws WEB3BaseException
     * @@throws DataQueryException
     * @@throws DataNetworkException
     * @@throws DataFindException
     * @@roseuid 40BEFA0600BA
     */
    public void notifyCashinCooperation(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "notifyCashinCooperation(BankDepositNotifyParams, WEB3GentradeMainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bankDepositNotifyParams == null || l_mainAccount == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^Null�o���Ȃ��B");     
        }

        //toDate(japaneseEra : String, dateString : String)
        //�����N�Z����a����񂩂琼����t�ɕϊ�����B
        //�����F
        //�N�� = �N��.getJapEraDiv(�����ʒmParams.�����N�Z��)�̖߂�l
        //���t = �����ʒmParams.�����N�Z��
        String l_strDepositDataBaseDate =
            l_bankDepositNotifyParams.getDepositDataBaseDate();
        Date l_datDepositDataBaseDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositDataBaseDate),
            l_strDepositDataBaseDate);

        //���ʂ�null�̏ꍇ
        if (l_datDepositDataBaseDate == null)
        {
            //���Y�����ʒm���R�[�h���X�V
            Map l_mapSpac = new HashMap();
            //[�X�V���e]
            //�����敪 = "�G���["
            l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);

            //�G���[�R�����g = "�����N�Z���G���["
            l_mapSpac.put("deposit_error_comment",
               WEB3AioErrorCommentDef.DEPOSITDATA_BASEDATE_ERROR);

            //�G���[�����ŏI�ʔ�
            String l_strLastErrorHistorySerialNo =
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
            l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

            //update�����ʒm(�����ʒmParams, Map)
            this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);

            //�����ʒm�����G���[�����e�[�u����1���ǉ�����B
            l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo());

            //�����敪 = "�G���["
            l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);

            //�G���[�R�����g = "�����N�Z���G���["
            l_bankDepositNotifyParams.setDepositErrorComment(
                WEB3AioErrorCommentDef.DEPOSITDATA_BASEDATE_ERROR);

            //insert�����ʒm�����G���[����(�����ʒmParams)
            this.insertDepositErrorHistory(l_bankDepositNotifyParams);

            log.exiting(STR_METHOD_NAME);
            return;
        }

        //toDate(japaneseEra : String, dateString : String)
        //�������a����񂩂琼����t�ɕϊ�����B
        //�����F
        //�N�� = �N��.getJapEraDiv(�����ʒmParams.�����)�̖߂�l
        //���t = �����ʒmParams.�����
        String l_strDepositDataAccountDate =
            l_bankDepositNotifyParams.getDepositDataAccountDate();
        Date l_datDepositDataAccountDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositDataAccountDate),
            l_strDepositDataAccountDate);

        //���ʂ�null�̏ꍇ
        if (l_datDepositDataAccountDate == null)
        {
            //���Y�����ʒm���R�[�h���X�V
            Map l_mapSpac = new HashMap();
            //[�X�V���e]
            //�����敪 = "�G���["
            l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);

            //�G���[�R�����g = "������G���["
            l_mapSpac.put("deposit_error_comment",
               WEB3AioErrorCommentDef.SETTLEMENTDATE_ERROR);

            //�G���[�����ŏI�ʔ�
            String l_strLastErrorHistorySerialNo =
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
            l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

            //update�����ʒm(�����ʒmParams, Map)
            this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);

            //�����ʒm�����G���[�����e�[�u����1���ǉ�����B
            l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo());

            //�����敪 = "�G���["
            l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);

            //�G���[�R�����g = "������G���["
            l_bankDepositNotifyParams.setDepositErrorComment(
                WEB3AioErrorCommentDef.SETTLEMENTDATE_ERROR);

            //insert�����ʒm�����G���[����(�����ʒmParams)
            this.insertDepositErrorHistory(l_bankDepositNotifyParams);

            log.exiting(STR_METHOD_NAME);
            return;
        }

        //1.1 �����ʒmParams.���X�R�[�h != null ����
        //�����ʒmParams.�ڋq�R�[�h != null�̏ꍇ
        //���񂹃`�F�b�N���s�킸������o�^����B
        //�P�jsubmit����
        //�Q�j���Y�����ʒm���R�[�h���ȉ��̂悤�ɍX�V����B
        //[�X�V���e]
        //�����敪 = ������
        //���X�R�[�h = �ڋq�}�X�^.���X�R�[�h
        //�ڋq�R�[�h = �ڋq�}�X�^.�ڋq�R�[�h
        if (WEB3StringTypeUtility.isNotEmpty(l_bankDepositNotifyParams.getBranchCode()) &&
            WEB3StringTypeUtility.isNotEmpty(l_bankDepositNotifyParams.getAccountCode()))
        {    
            //�����ʒmParams.�ʉ݃R�[�h != null �̏ꍇ�A�A�ȉ��̏��������s
            if (l_bankDepositNotifyParams.getCurrencyCode() != null)
            {
                //1.1.1.1 �O�݃R�[�h�̑Ó������`�F�b�N����B 
                //[����] 
                //�،���ЃR�[�h�F �����ʒmParams.get�،���ЃR�[�h 
                //�ʉ݃R�[�h�F �����ʒmParams.get�ʉ݃R�[�h
                boolean l_blnForeignCode = this.isForeignCurrencyCode(
                    l_bankDepositNotifyParams.getInstitutionCode(), 
                    l_bankDepositNotifyParams.getCurrencyCode());
                
                //1.1.1.2 false�̏ꍇ
                if (!l_blnForeignCode)
                {
                    //1.1.1.2.1  update�����ʒm(�����ʒmParams, Map)
                    //[�X�V���e]
                    //�����敪 = "�G���["
                    //�G���[�R�����g = "�O�݃R�[�h�s�����G���["
                    Map l_mapSpac = new HashMap();

                    l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);
                    l_mapSpac.put("deposit_error_comment", WEB3AioErrorCommentDef.FOREIGN_ERROR);
                    
                    //�G���[�����ŏI�ʔ�
                    String l_strLastErrorHistorySerialNo = 
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
                    l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

                    this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
                    
                    l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo());
                    
                    l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                    
                    l_bankDepositNotifyParams.setDepositErrorComment(
                        WEB3AioErrorCommentDef.FOREIGN_ERROR);
                    
                    //1.1.1.2.2   insert�����ʒm�����G���[����(�����ʒmParams)
                    this.insertDepositErrorHistory(l_bankDepositNotifyParams);
                    
                    //1.1.1.2.3
                    return;
                }

                double l_dblSellExecRate = 0;
                // �i���ʁj�ʉ�(�،���ЃR�[�h : String, �ʉ݃R�[�h : String)
                // [����]
                // �،���ЃR�[�h�F�@@�����ʒmParams.�،���ЃR�[�h
                // �ʉ݃R�[�h�F�@@�����ʒmParams.�ʉ݃R�[�h
                WEB3GentradeCurrency l_genCurrency = WEB3GentradeCurrency.genCurrency(
                        l_bankDepositNotifyParams.getInstitutionCode(), 
                        l_bankDepositNotifyParams.getCurrencyCode());
                
                // get���t���בփ��[�g( )
                // ���t���בփ��[�g���擾����B
                l_dblSellExecRate = l_genCurrency.getSellExecRate();
                
                // is���팅��
                // [����]
                // ������z�F�����ʒmParams.������z
                // �בփ��[�g�F get���t���בփ��[�g()�̖߂�l
                boolean l_blnIsNormalLength =
                    this.isNormalLength(
                        l_bankDepositNotifyParams.getDepositDataDepositAmount(), l_dblSellExecRate);
                if (!l_blnIsNormalLength)
                {
                    //update�����ʒm(�����ʒmParams, Map)
                    //[�X�V���e]
                    //�����敪 = "�G���["
                    //�G���[�R�����g = "�~���Z�z�����G���["
                    Map l_mapSpac = new HashMap();

                    l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);
                    l_mapSpac.put("deposit_error_comment", WEB3AioErrorCommentDef.CONVER_AMOUNT_LENGTH_ERROR);
                    
                    //�G���[�����ŏI�ʔ�
                    String l_strLastErrorHistorySerialNo = 
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
                    l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);
                    this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);

                    l_bankDepositNotifyParams.setLastErrorHistorySerialNo(
                        l_bankDepositNotifyParams.getLastErrorHistorySerialNo());
                    l_bankDepositNotifyParams.setStatus(WEB3StatusDef.DATA_ERROR);
                    l_bankDepositNotifyParams.setDepositErrorComment(
                        WEB3AioErrorCommentDef.CONVER_AMOUNT_LENGTH_ERROR);
                    
                    // insert�����ʒm�����G���[����(�����ʒmParams)
                    this.insertDepositErrorHistory(l_bankDepositNotifyParams);

                    return;
                }
                
                
            }

            //1.1.2 submit����(�����ʒmParams, �ڋq)
            this.submitOrder(l_bankDepositNotifyParams, l_mainAccount);
            
            //1.1.3 update�����ʒm(�����ʒmParams, Map)
            Map l_mapSpac = new HashMap();
            l_mapSpac.put("status", WEB3StatusDef.DEALT);
            l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
            l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
            //�G���[�����ŏI�ʔ�
            String l_strLastErrorHistorySerialNo = 
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
            l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

            this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
        }
        
        //1.2 ��L(1.1)�ȊO
        else
        {
            //1.2.1 check�ڋq��(�����ʒmParams, �ڋq)
            boolean l_blnIsAccountName = 
                this.checkAccountName(l_bankDepositNotifyParams, l_mainAccount);
            
            //1.2.2 true�̏ꍇ
            if (l_blnIsAccountName)
            {   
                //1.2.2.1 submit����(�����ʒmParams, �ڋq)
                //����OK�̏ꍇ
                //�����ʒm�̒����o�^����B
                //�P�jsubmit����
                this.submitOrder(l_bankDepositNotifyParams, l_mainAccount);
                
                //1.2.2.2 update�����ʒm(�����ʒmParams, Map)
                //�Q�j���Y�����ʒm���R�[�h���ȉ��̂悤�ɍX�V����B
                //[�X�V���e]
                //�����敪 = ������
                //���X�R�[�h = �ڋq�}�X�^.���X�R�[�h
                //�ڋq�R�[�h = �ڋq�}�X�^.�ڋq�R�[�h
                Map l_mapSpac = new HashMap();
                l_mapSpac.put("status", WEB3StatusDef.DEALT);
                l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
                l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
                //�G���[�����ŏI�ʔ�
                String l_strLastErrorHistorySerialNo = 
                    l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + "";
                l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);

                this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
            }
            else
            {
                //1.2.3 false�̏ꍇ
                //���񂹃G���[�̏ꍇ
                //���Y�����ʒm���R�[�h���X�V���A
                //�����ʒm�����G���[�����e�[�u����1���ǉ�����B
                //[�X�V���e]
                //�����敪 = "�G���["
                //�G���[�R�����g = "���񂹃G���["
                //���X�R�[�h = �ڋq�}�X�^.���X�R�[�h
                //�ڋq�R�[�h = �ڋq�}�X�^.�ڋq�R�[�h
                //1.2.3.1  update�����ʒm(�����ʒmParams, Map)
                Map l_mapSpac = new HashMap();
                l_mapSpac.put("status", WEB3StatusDef.DATA_ERROR);
                l_mapSpac.put("deposit_error_comment", WEB3ErrorCommentDef.DEPOSIT_ERROR_COMMENT);
                l_mapSpac.put("branch_code", l_mainAccount.getBranch().getBranchCode());
                l_mapSpac.put("account_code", l_mainAccount.getAccountCode());
                //�G���[�����ŏI�ʔ�
                String l_strLastErrorHistorySerialNo = 
                    l_bankDepositNotifyParams.getLastErrorHistorySerialNo() + 1 + "";
                l_mapSpac.put("last_error_history_serial_no", l_strLastErrorHistorySerialNo);
                this.updateDepositNotify(l_bankDepositNotifyParams, l_mapSpac);
                
                BankDepositNotifyRow l_bankDepositNotifyRow = null;
                try
                {
                    l_bankDepositNotifyRow =
                        BankDepositNotifyDao.findRowByPk(
                            l_bankDepositNotifyParams.getBankDepositNotifyId(),
                            l_bankDepositNotifyParams.getInstitutionCode(),
                            l_bankDepositNotifyParams.getDataLoadDiv());
                }
                catch (DataFindException l_ex)
                {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
                }
                catch (DataQueryException l_ex)
                {
                    log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                } 
                l_bankDepositNotifyParams =  
                    new BankDepositNotifyParams(l_bankDepositNotifyRow);

                //1.2.3.2   insert�����ʒm�����G���[����(�����ʒmParams)
                this.insertDepositErrorHistory(l_bankDepositNotifyParams);
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (submit����)<BR>
     * �����ʒm�̒����o�^����B<BR>
     * <BR>
     * �V�[�P���X�}�u�i�����A�g�ʒm�ꌏ�T�[�r�XImpl�jsubmit�����v �Q��<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - �����ʒmParams<BR>
     * @@param l_mainAccount  �ڋq <BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void submitOrder(
        BankDepositNotifyParams l_bankDepositNotifyParams,
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "submitOrder(BankDepositNotifyParams, WEB3GentradeMainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 createNewOrderId( )
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3AioOrderManager l_aioOrderManager =
            (WEB3AioOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.AIO).getOrderManager(); 
        long l_lngNewOrderId = l_aioOrderManager.createNewOrderId();
        log.debug("l_AioOrderManager.createNewOrderId()====" + l_lngNewOrderId);
        
        //1.2 getInstitution( )
        Institution l_institution = l_mainAccount.getInstitution();
            
        //1.3 get���iID(Institution)
        long l_lngProductId = l_aioOrderManager.getProductId(l_institution);
        
        //1.4 get������( )
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        
        //1.5 toDate(japaneseEra : String, dateString : String)
        //�����ʒm�N�Z����a����񂩂琼����t�ɕϊ�����B
        //�����F
        //�N�� = �N��.getJapEraDiv(�����ʒm���R�[�h.�����f�[�^�����N�Z��)�̖߂�l
        //���t = �����ʒm���R�[�h.�����f�[�^�����N�Z��
        String l_strDepositDataBaseDate = l_bankDepositNotifyParams.getDepositDataBaseDate();
        Date l_datChangeDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositDataBaseDate),
            l_strDepositDataBaseDate);
        
        //1.6 get��n��(Date, Date)
        Date l_datDeliveryDate = 
            this.getDeliveryDate(l_datChangeDate, l_datBizDate);
        
        //1.7 ���o���������e
        //���� = null
        //������� = ����
        //�U�փ^�C�v = ����
        //���iID = get���iID()�̖߂�l
        //���z = Double.parseDouble(�����ʒm���R�[�h.�������z)
        //�L�q = null
        //�U�֗\��� = ������i��1�j�@@�����������c�Ɠ��̏ꍇ�́A���c�Ɠ�
        //���ϋ@@��ID = null
        //����ID = get����ID()�̖߂�l
        
        double l_dblDepositDataDepositAmount = 0.0D;
        if (l_bankDepositNotifyParams.getDepositDataDepositAmount() != null)
        {
            l_dblDepositDataDepositAmount = 
                Double.parseDouble(
                    l_bankDepositNotifyParams.getDepositDataDepositAmount());
        }
        
        //����� = �����ʒmParams.������𐼗���t�ɕϊ������l
        //�@@[�N��.toDate�̈���]
        //�@@�@@�N�� = �N��.getJapEraDiv(�����ʒmParams.�����)�̖߂�l
        //�@@�@@�a����� = �����ʒmParams.�����
        String l_strDepositData =
            l_bankDepositNotifyParams.getDepositDataAccountDate();
        Date l_datDepositDataAccountDate = WEB3GentradeEra.toDate(
            WEB3GentradeEra.getJapEraDiv(l_strDepositData),
            l_strDepositData);
        String l_strDepositDataAccountDate = 
            WEB3GentradeTradingTimeManagement.getBizDateType(
                new Timestamp(l_datDepositDataAccountDate.getTime()));
        
        //���������c�Ɠ��̏ꍇ
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strDepositDataAccountDate))
        {
            //���c�Ɠ�
            l_datDepositDataAccountDate = new WEB3GentradeBizDate(new Timestamp(
                l_datDepositDataAccountDate.getTime())).roll(1);
        }
        
        WEB3AioNewOrderSpec l_aioNewOrderSpec = new WEB3AioNewOrderSpec(
            null,
            OrderTypeEnum.CASH_IN,
            AssetTransferTypeEnum.CASH_IN,
            l_lngProductId,
            l_dblDepositDataDepositAmount,
            null,
            l_datDepositDataAccountDate,
            null,
            new Long(l_lngNewOrderId));
        
        //1.8 get�V�K���ʃR�[�h
        WEB3HostReqOrderNumberManageService l_hostReqOrderNumberManageService =
            (WEB3HostReqOrderNumberManageService)Services.getService(
                WEB3HostReqOrderNumberManageService.class); 
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        String l_strInstitutionCode = 
            l_mainAccount.getInstitution().getInstitutionCode();
        String l_strNewNumber = 
            l_hostReqOrderNumberManageService.getNewNumber(
                l_strInstitutionCode, 
                l_strBranchCode, 
                ProductTypeEnum.CASH);
        log.debug("�V�K�̎��ʃR�[�h = " + l_strNewNumber);
        log.debug("InstitutionCode = " + l_strInstitutionCode);
        log.debug("BranchCode = " + l_strBranchCode);
        
        double l_dblSellExecRate = 0;
        //1.9 �����A�g�����X�V�C���^�Z�v�^(���o���������e)
        WEB3AioCashinCooperationOrderUpdateInterceptor l_interceptor = 
            new WEB3AioCashinCooperationOrderUpdateInterceptor(l_aioNewOrderSpec);

        //1.10 �����ʒmParams.�ʉ݃R�[�h != null �̏ꍇ�A�ȉ��̏��������s
        if (l_bankDepositNotifyParams.getCurrencyCode() != null)
        {
            // 1.10.1�i���ʁj�ʉ݂𐶐�����B 
            //[����] 
            //�،���ЃR�[�h�F�@@�����ʒmParams.�،���ЃR�[�h 
            //�ʉ݃R�[�h�F�@@�����ʒmParams.�ʉ݃R�[�h
            WEB3GentradeCurrency l_genCurrency = WEB3GentradeCurrency.genCurrency(
                l_bankDepositNotifyParams.getInstitutionCode(), 
                l_bankDepositNotifyParams.getCurrencyCode());
            //1.10.2 get���t���בփ��[�g( )
            l_dblSellExecRate = l_genCurrency.getSellExecRate();
        }
        
        //1.11 (*)�v���p�e�B�Z�b�g
        //�ȉ��̂悤�Ƀv���p�e�B�l���Z�b�g����B
        //���ʃR�[�h = get���ʃR�[�h()�̖߂�l
        l_interceptor.setOrderRequestNumber(l_strNewNumber);
        //������ = get��n��()
        l_interceptor.setOrderBizDate(l_datDeliveryDate);
        //��n�� = get��n��()
        l_interceptor.setDeliveryDate(l_datDeliveryDate);
        //�����o�H = �����A�g
        l_interceptor.setBizChannel(WEB3OrderRootDivDef.CASH_IN_COOPERATION);
        //�U�֋L�q = ���Z�@@�փR�[�h
        String l_strBackSchemeCode = "";
        //(���Z�@@�փR�[�h = �����ʒm���R�[�h.��s�R�[�h  + 
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getBankCode();
        //�����ʒm���R�[�h.��s�x�X�R�[�h +
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getBankBranchCode();  
        //�����ʒm���R�[�h.�����f�[�^������� +
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getDepositDataBankAccountType(); 
        //�����ʒm���R�[�h.�����ԍ�)
        l_strBackSchemeCode = 
            l_strBackSchemeCode + l_bankDepositNotifyParams.getBankAccountNo();
        l_interceptor.setDescription(l_strBackSchemeCode);
        
        //�ʉ݃R�[�h = �����ʒmParams.�ʉ݃R�[�h
        l_interceptor.setCurrencyCode(l_bankDepositNotifyParams.getCurrencyCode());
        
        //�����ʒmParams.�ʉ݃R�[�h ��null�̏ꍇ�A
        //���o�����z�i�~���Z�z�j = null
        if (l_bankDepositNotifyParams.getCurrencyCode() == null)
        {
            l_interceptor.setConvertAmount(null);
        } 
        else
        {
            //�����ʒmParams.�ʉ݃R�[�h  != null�̏ꍇ�A
            //���o�����z�i�~���Z�z�j = get���t���בփ��[�g()�̖߂�l * �����ʒmParams.������z�̏����������J�b�g�����l
            BigDecimal l_bdlDepositDataAepositAmount = 
                new BigDecimal(l_bankDepositNotifyParams.getDepositDataDepositAmount());
            BigDecimal l_bddSellExeCRate = 
                new BigDecimal(String.valueOf(l_dblSellExecRate));
            l_bdlDepositDataAepositAmount = 
                l_bddSellExeCRate.multiply(l_bdlDepositDataAepositAmount);
            long l_lngDepositDataAepositAmount = l_bdlDepositDataAepositAmount.longValue();
            l_interceptor.setConvertAmount(
                new Double(l_lngDepositDataAepositAmount));
        }

        //1.12 setThreadLocalPersistenceEventInterceptor(arg0 : AioOrderManagerPersistenceEventInterceptor)
        l_aioOrderManager.setThreadLocalPersistenceEventInterceptor(l_interceptor);
        
        //1.13 getSubAccount(arg0 : SubAccountTypeEnum)
        try
        {
            SubAccount l_subAccount = 
                l_mainAccount.getSubAccount(
                    SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            //1.14 decrypt(l_str : String)
            WEB3Crypt l_web3Crypt = new WEB3Crypt();
            String l_strDecrypt = l_web3Crypt.decrypt(l_mainAccount.getTradingPassword());

            //1.15 submitNewOrder
            //[����]
            // �⏕�����F�@@�⏕�����I�u�W�F�N�g
            // ���i�^�C�v�F�@@5�i�����j
            // ���o���������e�F�@@���o���������e�I�u�W�F�N�g
            // �����h�c�F�@@createNewOrderId()�̖߂�l
            // �p�X���[�h�F�@@�ڋq.getTradingPassword()�̖߂�l��WEB3C��������.decrypt()�ŕ�����������
            // isSkip�����R���F�@@true   
            OrderSubmissionResult l_submissionResult =
                l_aioOrderManager.submitNewOrder(
                    l_subAccount,
                    ProductTypeEnum.CASH,
                    l_aioNewOrderSpec,
                    l_lngNewOrderId,
                    l_strDecrypt,
                    true);
            if (l_submissionResult.getProcessingResult().isFailedResult())
            {
                log.debug("�����o�^�������s�ł���");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    l_submissionResult.getProcessingResult().getErrorInfo(),
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }

            // is�M�p�����J��(�ٍϋ敪 : String)
            boolean l_blnIsMarginAccountEstablished =
                l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

            if (l_blnIsMarginAccountEstablished)
            {
                // �ڋq���M�p�������J�݂��Ă���iis�M�p�����J��()==TRUE�j�ꍇ
                WEB3MarginTransferService l_service =
                    (WEB3MarginTransferService)Services.getService(
                    WEB3MarginTransferService.class);

                //submit�ۏ؋��U��(�ڋq, Date, double, String,Trader)
                l_service.submitMarginTransfer(
                    l_mainAccount,
                    l_datDeliveryDate,
                    l_dblDepositDataDepositAmount,
                    l_strDecrypt,
                    null);
            }
            
            //1.16 �]�͍Čv�Z(�⏕���� : �⏕����)
            WEB3TPTradingPowerService l_service =
                (WEB3TPTradingPowerService) Services.getService(
                    WEB3TPTradingPowerService.class);
            WEB3GentradeSubAccount l_gentradeSubAccount =
                (WEB3GentradeSubAccount)l_subAccount;
            l_service.reCalcTradingPower(l_gentradeSubAccount);

            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂���", l_ex);
            log.exiting(STR_METHOD_NAME);  
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
    }
    
    /**
     * (insert�����ʒm�����G���[����)<BR>
     * �����ʒm�����G���[�����e�[�u����1���ǉ�����B<BR>
     * <BR>
     * <DB�X�V�d�l�Q��> <BR>
     * ��s�����ʒm_�����ʒm�����G���[�����e�[�u��.xls<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - �����ʒmParams<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void insertDepositErrorHistory(BankDepositNotifyParams l_bankDepositNotifyParams) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "insertDepositErrorHistory(BankDepositNotifyParams l_bankDepositNotifyParams) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //��s�����ʒm�����G���[����Params
            BankDepositErrorHistoryParams l_bankDepositErrorHistoryParams = 
                new BankDepositErrorHistoryParams();
            
            //1)��s�����ʒm�����G���[�����e�[�u��ID
            l_bankDepositErrorHistoryParams.setBankDepositErrorHistoryId(
                BankDepositErrorHistoryDao.newPkValue());
            
            //2)��s�����ʒm�e�[�u��ID
            l_bankDepositErrorHistoryParams.setBankDepositNotifyId(
                l_bankDepositNotifyParams.getBankDepositNotifyId());
            
            //3)����ԍ�
            l_bankDepositErrorHistoryParams.setSerialNo(
                l_bankDepositNotifyParams.getLastErrorHistorySerialNo());
            
            //4)�����G���[�R�����g
            l_bankDepositErrorHistoryParams.setDepositErrorComment(
                l_bankDepositNotifyParams.getDepositErrorComment());
            
            //5)���l
            l_bankDepositErrorHistoryParams.setRemark(
                l_bankDepositNotifyParams.getRemark());
            
            //6)�X�V�S����
            l_bankDepositErrorHistoryParams.setUpdatePerson(WEB3UpdatePersonDef.SYSTEM);
            
            //7)�쐬���t
            l_bankDepositErrorHistoryParams.setCreatedTimestamp(
                GtlUtils.getSystemTimestamp());
            
            //8)�X�V���t
            l_bankDepositErrorHistoryParams.setLastUpdatedTimestamp(
                GtlUtils.getSystemTimestamp());
            
            //9)�،���ЃR�[�h = ��s�����ʒm�e�[�u��.�،���ЃR�[�h
            l_bankDepositErrorHistoryParams.setInstitutionCode(
                l_bankDepositNotifyParams.getInstitutionCode());

            //10)�f�[�^�捞�敪 = ��s�����ʒm�e�[�u��.�f�[�^�捞�敪
            l_bankDepositErrorHistoryParams.setDataLoadDiv(
                l_bankDepositNotifyParams.getDataLoadDiv());
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doInsertQuery(l_bankDepositErrorHistoryParams);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (update�����ʒm)<BR>
     * ���Y�����ʒm���R�[�h���X�V����B<BR>
     * <BR>
     * <DB�X�V�d�l�Q��> <BR>
     * ��s�����ʒm_�����ʒm�e�[�u��.xls<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - �����ʒmParams<BR>
     * @@param l_mapSpac - Map �X�V���e<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected void updateDepositNotify(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        Map l_mapSpac) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "updateDepositNotify(BankDepositNotifyParams, Map) ";
        log.entering(STR_METHOD_NAME);

        try
        {
            //�X�V�S����
            l_mapSpac.put("update_person", "system");
            //�X�V���t
            l_mapSpac.put("last_updated_timestamp", GtlUtils.getSystemTimestamp());

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                l_bankDepositNotifyParams.getPrimaryKey(),
                l_mapSpac);            
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataQueryException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("DB�ւ̃A�N�Z�X�Ɏ��s���܂���: ", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }  
    }
    
    /**
     * (get��n��)<BR>
     * ��n�����擾����B<BR>
     * <BR>
     * �P�j����.�����N�Z�� <= ����.������ �̏ꍇ <BR>
     * ��n���Ƃ��āA����.��������ԋp����B <BR>
     * �Q�j����.�����N�Z�� > ����.������ �̏ꍇ <BR>
     * ��n���Ƃ��āA����.�����N�Z����ԋp����B<BR> 
     *  ������.�����N�Z������c�Ɠ��̏ꍇ�́A���̗��c�Ɠ���ԋp����B<BR>
     * <BR>
     * @@param l_datDepositBaseDate - �����N�Z�� <BR>
     * @@param l_datBizDate - ������  <BR>
     * @@return  Date 
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected Date getDeliveryDate(Date l_datDepositBaseDate, Date l_datBizDate) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getDeliveryDate(Date l_datDepositBaseDate, Date l_datBizDate) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����.�����N�Z�� <= ����.������ �̏ꍇ 
        if(WEB3DateUtility.compareToDay(l_datDepositBaseDate, l_datBizDate) <= 0)
        {
            // ��n���Ƃ��āA����.��������ԋp����B 
            log.exiting(STR_METHOD_NAME);
            return l_datBizDate;
        }
        
        //�Q�j����.�����N�Z�� > ����.������ �̏ꍇ 
        else
        {
            String l_strDepositBaseDateType = 
                WEB3GentradeTradingTimeManagement.getBizDateType(
                    new Timestamp(l_datDepositBaseDate.getTime()));
            log.debug("�����N�Z���̉c�Ɠ��敪 = " + l_strDepositBaseDateType);
            
            //������.�����N�Z������c�Ɠ��̏ꍇ�́A���̗��c�Ɠ���ԋp����B
            if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strDepositBaseDateType))
            {
                Date l_datRreturnDate = 
                    new WEB3GentradeBizDate(
                        new Timestamp(
                            l_datDepositBaseDate.getTime())).roll(1);
                log.exiting(STR_METHOD_NAME);
                return l_datRreturnDate;
            }
            else
            {
                //��n���Ƃ��āA����.�����N�Z����ԋp����B 
                log.exiting(STR_METHOD_NAME);
                return l_datDepositBaseDate;
            }
        }
    }
    
    /**
     * (check�ڋq��)<BR>
     * ���񂹃`�F�b�N���s���B<BR>
     * <BR>
     * �ڋq�}�X�^���R�[�h.���O�i�c���P�j <BR>
     *  = �����ʒm���R�[�h.�����f�[�^�˗��l�� <BR>
     * <BR>
     * ���ǂ����`�F�b�N����B<BR>
     * <BR>
     * �P�j�s�v���������O <BR>
     * (*�p�^�[��) <BR>
     * �@@�l�ݗ��`�ԋ敪 = {"�i�J", "�J�j", "�i��", "���j", "�i�V", "�V�j"} <BR>
     * �S�p�X�y�[�X<BR>
     * ���p�X�y�[�X <BR>
     * <BR>
     * �Q�j�����ʒm���R�[�h.�����f�[�^�˗��l���� <BR>
     * �啶�����������݂̏ꍇ�͑啶���ϊ�����B<BR>
     * <BR>
     * <BR>
     * �R�j��L�����㕶���񂪍��v����ꍇtrue�A���v���Ȃ��ꍇfalse��Ԃ��B<BR>
     * <BR>
     * @@param l_bankDepositNotifyParams - �����ʒmParams <BR>
     * @@param l_mainAccount - �ڋq<BR>
     * @@return  boolean  
     * @@throws WEB3BaseException
     * @@roseuid 40BEFA0600BA
     */
    protected boolean checkAccountName(
        BankDepositNotifyParams l_bankDepositNotifyParams, 
        WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "checkAccountName(BankDepositNotifyParams, WEB3GentradeMainAccount) ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�s�v���������O 
        //�ڋq�}�X�^���R�[�h.���O�i�c���P�j���擾����B  
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow)l_mainAccount.getDataSourceObject();
        String l_strFamilyNameAlt1 = 
            this.getCheckAccountName(l_mainAccountRow.getFamilyNameAlt1());
        
        //�����ʒm���R�[�h.�����f�[�^�˗��l�����擾����B 
        if (l_bankDepositNotifyParams.getDepositDataTransPersonName() == null)
        {
            return false;
        }

        String l_strDepositDataTransPersonName = 
            this.getCheckAccountName(
                l_bankDepositNotifyParams.getDepositDataTransPersonName());
        
        //�Q�j�����ʒm���R�[�h.�����f�[�^�˗��l���� 
        //�啶�����������݂̏ꍇ�͑啶���ϊ�����B
        l_strDepositDataTransPersonName =
            WEB3StringTypeUtility.toUpperWbyteKana(
                l_strDepositDataTransPersonName);

        //�ڋq�}�X�^���R�[�h.���O�i�c���P�j 
        //= �����ʒm���R�[�h.�����f�[�^�˗��l�� 
        //�R�j��L�����㕶���񂪍��v����ꍇtrue�A���v���Ȃ��ꍇfalse��Ԃ��B
        if (l_strFamilyNameAlt1.compareTo(l_strDepositDataTransPersonName) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
 
    /**
     * (is�O�݃R�[�h)<BR>
     * �O�݃R�[�h�̑Ó������`�F�b�N����B <BR>
     * <BR>
     * �P�j�@@�i���ʁj�ʉ݁i����:�،���ЃR�[�h , ����:�O�݃R�[�h�j �ŁA���R�[�h�� <BR>
     *        �����ł��Ȃ��ꍇ�Afalse��ԋp�B����ȊO�́Atrue��ԋp <BR>
     * <BR>
     * @@param l_strInstitutionCode - �،���ЃR�[�h <BR>
     * @@param l_strForeignCurrencyCode - �O�݃R�[�h<BR>
     * @@return  boolean  
     * @@roseuid 40BEFA0600BA
     */
    protected boolean isForeignCurrencyCode(
        String l_strInstitutionCode, 
        String l_strForeignCurrencyCode)
    {
        final String STR_METHOD_NAME =
            "isForeignCurrencyCode(String, String) ";
        log.entering(STR_METHOD_NAME);
        
        //�O�݃R�[�h�̑Ó������`�F�b�N����B 
        //�P�j�@@�i���ʁj�ʉ݁i����:�،���ЃR�[�h , ����:�O�݃R�[�h�j �ŁA���R�[�h�� 
        //�@@�@@�����ł��Ȃ��ꍇ�Afalse��ԋp�B����ȊO�́Atrue��ԋp
        
        try
        {
            WEB3GentradeCurrency.genCurrency(
                l_strInstitutionCode, 
                l_strForeignCurrencyCode);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }    
    
    private String getCheckAccountName(String l_strCheckAccountName)
    {
        //�S�p�X�y�[�X 
        //���p�X�y�[�X 
        String l_strReturnValue = l_strCheckAccountName.trim();
        l_strReturnValue = l_strReturnValue.replaceAll(" ", "");
        l_strReturnValue = l_strReturnValue.replaceAll("�@@", "");

        //(*�p�^�[��) 
        //�@@�l�ݗ��`�ԋ敪 = {"�i�J", "�J�j", "�i��", "���j", "�i�V", "�V�j"} 
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[0], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[1], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[2], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[3], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[4], "");
        l_strReturnValue =
            l_strReturnValue.replaceAll(
                WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_LIST[5], "");
        l_strReturnValue = l_strReturnValue.trim();
        return l_strReturnValue;
    }
    
    /**
     * (is���팅��)<BR>
     * �v�Z�������o�����z�i�~���Z�z�j�̌�����13���ȏ�<BR>
     * �̏ꍇ�́Afalse�A����ȊO�́Atrue��ԋp����B<BR>
     * <BR>
     * �P�j�@@����:������z * ����:�בփ��[�g�̌v�Z����<BR>
     * <BR>
     * �Q�j�@@�P�j�̌v�Z���ʂ̐����������P�R���ȏ�̏ꍇ�Afalse�A����ȊOtrue ��ԋp<BR>
     * <BR>
     * @@return  boolean<BR>
     * @@param - (������z)<BR>
     * ������z
     * @@param - (�בփ��[�g)<BR>
     * �בփ��[�g
     */
    public boolean isNormalLength(String l_strDepositDataDepositAmount, double l_dbSellExecRate)
    {
        final String STR_METHOD_NAME =
            "isNormalLength(String l_strDepositDataDepositAmount, double l_dbSellExecRate)";
        log.entering(STR_METHOD_NAME);

        // �P�j�@@����:������z * ����:�בփ��[�g�̌v�Z����
        BigDecimal l_bdDepositDataDepositAmount = new BigDecimal(l_strDepositDataDepositAmount);
        String l_strSellExecRate = WEB3StringTypeUtility.formatNumber(l_dbSellExecRate);
        BigDecimal l_bdSellExecRate = new BigDecimal(l_strSellExecRate);
        BigDecimal l_bdResult = l_bdDepositDataDepositAmount.multiply(l_bdSellExecRate);

        long l_lngResult = l_bdResult.longValue();
        String l_strIntValue = l_lngResult + "";
        int l_intLength = WEB3StringTypeUtility.getByteLength(l_strIntValue);
        // �Q�j�@@�P�j�̌v�Z���ʂ̐����������P�R���ȏ�̏ꍇ�Afalse�A����ȊOtrue ��ԋp
        if (l_intLength >= 13)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        log.exiting(STR_METHOD_NAME);
        return true;
    }
}
@
