head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.26.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashoutTradingPowerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���]�̓`�F�b�N�T�[�r�XImpl(WEB3AioCashoutTradingPowerServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/19 ���� (���u) �V�K�쐬
Revesion History : 2004/10/25 ���E(���u) ���r���[
Revesion History : 2004/12/17 ���E (���u) �c�Ή�
Revesion History : 2006/08/28 �Ԑi (���u) �d�l�ύX�E���f��No.630�A645
Revesion History : 2006/11/15 ���G�� (���u)�d�l�ύX�E���f��No.684
Revesion History : 2007/03/28 �����q (���u)�d�l�ύX�E���f��No.718
Revesion History : 2007/04/09 �����q (���u)�d�l�ύX�E���f��No.720
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import webbroker3.aio.data.HostPaymentOrderParams;
import webbroker3.aio.data.HostPaymentOrderRow;
import webbroker3.aio.define.WEB3AioProcessFlagDef;
import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckRequest;
import webbroker3.aio.message.WEB3AioCashoutTradingPowerCheckResponse;
import webbroker3.aio.service.delegate.WEB3AioCashOutOrderTriggerIssueUnitService;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerService;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AioHostStatusDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3PaymentApplyTriggerDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (�o���]�̓`�F�b�N�T�[�r�XImpl)<BR>
 * �o���]�̓`�F�b�N�T�[�r�X�����N���X
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3AioCashoutTradingPowerServiceImpl 
    implements WEB3AioCashoutTradingPowerService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashoutTradingPowerServiceImpl.class);
    
    /**
     * �o���]�̓`�F�b�N�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�o���]�̓`�F�b�N�jexecute�v �Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return WEB3BackResponse
     * @@roseuid 41294DB20312
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3BackRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        WEB3AioCashoutTradingPowerCheckRequest l_checkRequest = null;
        if (!(l_request instanceof WEB3AioCashoutTradingPowerCheckRequest))
        {
            log.debug(
                "���N�G�X�g�f�[�^��" +
                "WEB3AioCashoutTradingPowerCheckRequest�ȊO�ł���, but is " + l_request.getClass().getName());
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else
        {
            l_checkRequest = (WEB3AioCashoutTradingPowerCheckRequest) l_request;
        }
        
        // 1.1)�擾�����̕�����𐶐�����B 
        //[����] 
        //�����t���O�F ���N�G�X�g�f�[�^.�����t���O
        String l_strCharacter =
            this.createGetCondCharacterString(l_checkRequest.processFlag);
        log.debug("�擾�����̕�����𐶐����� = " + l_strCharacter);    
        
        // 1.2)�擾�����ɐݒ肷��l�̔z��𐶐�����B
        //[����] 
        //���N�G�X�g�f�[�^�F ���N�G�X�g�f�[�^ 
        Object[] l_objGetCondDataContainer = 
            this.createGetCondDataContainer(l_checkRequest);
        
        try
        {
            // 1.3)�N�G���v���Z�b�T���擾����B
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
        
            // 1.4)�o�����������L���[�e�[�u������A���R�[�h���擾����B
            //�m�����n 
            //Row�^�C�v�F �o�����������L���[Row.TYPE 
            //Where�F create�擾����������()�̖߂�l 
            //orderBy�F "branch_code desc, account_code desc" 
            //condition�F null 
            //���X�g�F create�擾�����f�[�^�R���e�i()�̖߂�l
            List l_lisHostPaymentOrderRows = 
                l_queryProcessor.doFindAllQuery(
                    HostPaymentOrderRow.TYPE,
                    l_strCharacter,
                    "branch_code desc, account_code desc",
                    null,
                    l_objGetCondDataContainer);
            int l_intSize = 0;
            if (l_lisHostPaymentOrderRows != null && !l_lisHostPaymentOrderRows.isEmpty())
            {
                l_intSize = l_lisHostPaymentOrderRows.size();
            }

            //���o���E���o�Ƀ��N�G�X�g���M�T�[�r�X
            WEB3AioMarketRequestSenderServiceImpl 
                l_marketRequestSenderServiceImpl = 
                    (WEB3AioMarketRequestSenderServiceImpl) GtlUtils.getTradingModule(
                        ProductTypeEnum.AIO).getMarketAdapter().getMarketRequestSenderServce();
            
            // 1.5)is�]�̓`�F�b�N��g���K���s(String)
            boolean l_blnIsTpCheckedTrigger = 
                this.isTradingPowerCheckedTriggerIssue(
                l_checkRequest.institutionCode);

            //1.6.getDB�����]�̓`�F�b�N�敪(String)
            //�u�،���Ѓe�[�u��.DB�����]�̓`�F�b�N�敪�v���擾����B
            //[����]
            //���N�G�X�g�f�[�^.�،���ЃR�[�h
            String l_strDbCurrentPriceCheckDiv =
                this.getDbCurrentPriceCheckDiv(l_checkRequest.institutionCode);
            
            // get�g���K���s����( )
            // �V�X�e���v���t�@@�����X���A�o���]�̓`�F�b�N��̃g���K���s���� ���擾����B 
            int l_intTriggerIssueNumber = this.getTriggerIssueNumber();

            int l_intCount = 0;
            // 1.7)�擾�������R�[�h����Loop����
            for(int i=0; i < l_intSize; i++)
            {
                HostPaymentOrderRow l_hostPaymentOrderRow = 
                    (HostPaymentOrderRow) l_lisHostPaymentOrderRows.get(i);
                
                HostPaymentOrderParams l_hostPaymentOrderParams = 
                    new HostPaymentOrderParams(l_hostPaymentOrderRow);
                
                l_intCount++;
                // 1.7.1)execute(�o�����������L���[Params, String, boolean, String)
                //�����̗]�̓`�F�b�N�������s���B
                //[����]
                //�o�����������L���[�F �擾�����o�����������L���[Params
                //�����t���O�F ���N�G�X�g�f�[�^.�����t���O
                //�g���K���s�t���O�F is�]�̓`�F�b�N��g���K���s���\�b�h�Ŏ擾�����l
                //DB�����]�̓`�F�b�N�敪�F getDB�����]�̓`�F�b�N�敪���\�b�h�Ŏ擾�����l
                try
                {
                    WEB3AioCashoutTradingPowerUnitService l_aioCashoutTradingPowerUnitService =
                        (WEB3AioCashoutTradingPowerUnitService) Services.getService(
                            WEB3AioCashoutTradingPowerUnitService.class);
                    
                    l_aioCashoutTradingPowerUnitService.execute(
                        l_hostPaymentOrderParams,
                        l_checkRequest.processFlag, 
                        l_blnIsTpCheckedTrigger,
                        l_strDbCurrentPriceCheckDiv);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error("�����̗]�̓`�F�b�N�ɂăG���[���������܂����B");
                    continue;
                } 
                // ���N�G�X�g�f�[�^.�����t���O != '0'�i�S���f�[�^�����j �̏ꍇ�A���{
                if (!WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_checkRequest.processFlag))
                {
                    // Loop�J�E���g�� >= get�g���K���s����()�̏ꍇ�A�ȉ��̏��������s
                    if (l_intCount >= l_intTriggerIssueNumber)
                    {
                        // execute
                        WEB3AioCashOutOrderTriggerIssueUnitService l_cashOutOrderTriggerIssueUnitService =
                            (WEB3AioCashOutOrderTriggerIssueUnitService)Services.getService(
                                WEB3AioCashOutOrderTriggerIssueUnitService.class);
                        l_cashOutOrderTriggerIssueUnitService.execute(l_checkRequest.institutionCode);

                        l_intCount = 0;
                    }
                }
            }

            // ���N�G�X�g�f�[�^.�����t���O != '0'�i�S���f�[�^�����j �̏ꍇ�A���{
            // �g���K���s(String, String)
            // �،���ЃR�[�h�F ���N�G�X�g�f�[�^.�،���ЃR�[�h
            // �f�[�^�R�[�h�F "GI801T"
            if (!WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_checkRequest.processFlag))
            {
                // execute
                WEB3AioCashOutOrderTriggerIssueUnitService l_cashOutOrderTriggerIssueUnitService =
                    (WEB3AioCashOutOrderTriggerIssueUnitService)Services.getService(
                        WEB3AioCashOutOrderTriggerIssueUnitService.class);
                l_cashOutOrderTriggerIssueUnitService.execute(l_checkRequest.institutionCode);
//                l_marketRequestSenderServiceImpl.submitTrigger(
//                        l_checkRequest.institutionCode, 
//                        WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER + "T");
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
        //�o���]�̓`�F�b�N���X�|���X
        WEB3AioCashoutTradingPowerCheckResponse 
            l_aioCashoutTradingPowerCheckResponse = 
                (WEB3AioCashoutTradingPowerCheckResponse) l_checkRequest.createResponse(); 

        //���������o���]�̓`�F�b�N���X�|���X��Ԃ��B
        log.exiting(STR_METHOD_NAME);
        return l_aioCashoutTradingPowerCheckResponse;
    }
    
    /**
     * (create�擾����������)<BR>
     * ���N�G�X�g�f�[�^����A�f�[�^�擾����������𐶐�����B<BR>
     * <BR>
     * �P�j��̕�����𐶐�����B<BR>
     * <BR>
     * �Q�j�f�[�^�R�[�h��������<BR>
     * <BR>
     *   ����������F "request_code='GI801'"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�j�،���ЃR�[�h��������<BR>
     * <BR>
     *   ����������F " and institution_code=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�j�U���\�����������<BR>
     * <BR>
     *   ����.�����t���O != '0'�i�S���f�[�^�����j �̏ꍇ<BR>
     * <BR>
     *   ����������F " and est_transfer_date=?"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �T�j�����敪��������<BR>
     * <BR>
     *   ����������F " and status='3'"<BR>
     * <BR>
     *   ��L��������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �U�j���������������ԋp����B<BR>
     * @@param l_strProcessFlag - (�����t���O)
     * @@return String
     * @@roseuid 4129523A0227
     */
    protected String createGetCondCharacterString(String l_strProcessFlag) 
    {
        final String STR_METHOD_NAME = 
            "createGetCondCharacterString(String l_strProcessFlag)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strProcessFlag == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j��̕�����𐶐�����B         
        String l_strCreateCharacter = "";
        
        //�Q�j�f�[�^�R�[�h�������� 
        //����������F "request_code='GI801'" 
        //��L��������P�j�̕�����̖����ɒǉ�����B     
        l_strCreateCharacter = 
            l_strCreateCharacter + " request_code = ? "; 
        
        //�R�j�،���ЃR�[�h�������� 
        //����������F " and institution_code=?" 
        //��L��������P�j�̕�����̖����ɒǉ�����B
        l_strCreateCharacter = 
            l_strCreateCharacter + " and institution_code = ? ";      
        
        //�S�j�U���\����������� 
        //����.�����t���O != '0'�i�S���f�[�^�����j �̏ꍇ 
        //����������F " and est_transfer_date=?"
        //��L��������P�j�̕�����̖����ɒǉ�����B
        if (!WEB3AioProcessFlagDef.ALL_DATE_PROCESS.equals(l_strProcessFlag))
        {
            l_strCreateCharacter = 
                l_strCreateCharacter + " and to_char(est_transfer_date, 'yyyyMMdd') = ? ";              
        }
        else
        {
            l_strCreateCharacter = l_strCreateCharacter + "";
        }
              
        //�T�j�����敪�������� 
        //����������F " and status='3'" 
        //��L��������P�j�̕�����̖����ɒǉ�����B
        l_strCreateCharacter = l_strCreateCharacter +  " and status = ? ";
        
        
        //�U�j���������������ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strCreateCharacter;
    }
    
    /**
     * (create�擾�����f�[�^�R���e�i)<BR>
     * ���N�G�X�g�f�[�^����A�擾�����̃f�[�^���X�g�𐶐�����B<BR>
     * <BR>
     * �P�j���ArrayList�𐶐�����B<BR>
     * <BR>
     * �Q�j�،���ЃR�[�h<BR>
     * <BR>
     *   ����.���N�G�X�g�f�[�^.�،���ЃR�[�h���P�j�̃��X�g�ɒǉ�����B<BR>
     * <BR>
     * �R�j�U���\���<BR>
     * <BR>
     * �R�|�P�j����.���N�G�X�g�f�[�^.�����t���O = '1'�i�����U�����f�[�^�����j �̏ꍇ <BR> 
     * <BR>
     * �R�|�P�|�P�j���ݎ����i�V�X�e���^�C���X�^���v�j���擾����B<BR>
     * <BR>
     * �R�|�P�|�Q�j�擾�������ݎ����̓��t�������P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �R�|�Q�j����.���N�G�X�g�f�[�^.�����t���O = '2'�i�����U�����f�[�^�����j �̏ꍇ<BR>
     * <BR>
     * �R�|�Q�|�P�j���ݎ����i�V�X�e���^�C���X�^���v�j���擾����B<BR>
     * <BR>
     * �R�|�Q�|�Q�j�擾�������ݎ����̗��c�Ɠ����Z�o����B<BR>
     * <BR>
     * �R�|�Q�|�R�j�擾�������c�Ɠ����t���P�j�̕�����̖����ɒǉ�����B<BR>
     * <BR>
     * �S�j���X�g����z����擾���A�ԋp����B<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)
     * @@return Object[]
     * @@throws WEB3BaseException
     * @@roseuid 4129542D0053
     */
    protected Object[] createGetCondDataContainer(WEB3AioCashoutTradingPowerCheckRequest l_request)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "createGetCondDataContainer(WEB3AioCashoutTradingPowerCheckRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
                
        //�P�j���ArrayList�𐶐�����B 
        List l_lisCreateGetCondDataContainer = new ArrayList();
        
        //�f�[�^�R�[�h
        l_lisCreateGetCondDataContainer.add(WEB3HostRequestCodeDef.AIO_CASH_OUT_REQUEST_ORDER);
        
        //�Q�j�،���ЃR�[�h 
        //����.���N�G�X�g�f�[�^.�،���ЃR�[�h���P�j�̃��X�g�ɒǉ�����B 
        l_lisCreateGetCondDataContainer.add(l_request.institutionCode);
       
        
        //�R�j�U���\���
        //�R�|�P�j����.���N�G�X�g�f�[�^.�����t���O = '1'�i�����U�����f�[�^�����j �̏ꍇ
        //�R�|�P�|�P�j���ݎ����i�V�X�e���^�C���X�^���v�j���擾����B
        //�R�|�P�|�Q�j�擾�������ݎ����̓��t�������P�j�̕�����̖����ɒǉ�����B
        if(WEB3AioProcessFlagDef.DAY_BIZ_TRANSFER_PROCESS.equals(l_request.processFlag))
        {
            Date l_returnDate = GtlUtils.getSystemTimestamp();
            l_lisCreateGetCondDataContainer.add(
                WEB3DateUtility.formatDate(l_returnDate, "yyyyMMdd"));   
        }
        
        //�R�|�Q�j����.���N�G�X�g�f�[�^.�����t���O = '2'�i�����U�����f�[�^�����j �̏ꍇ 
        //�R�|�Q�|�P�j���ݎ����i�V�X�e���^�C���X�^���v�j���擾����B
        if (WEB3AioProcessFlagDef.NEXT_DATE_TRANSFER_PROCESS.equals(l_request.processFlag))
        {
            //�R�|�Q�|�Q�j�擾�������ݎ����̗��c�Ɠ����Z�o����B
            //�R�|�Q�|�R�j�擾�������c�Ɠ����t���P�j�̕�����̖����ɒǉ�����B
            Date l_returnDate = new WEB3GentradeBizDate(
                new Timestamp(GtlUtils.getSystemTimestamp().getTime())).roll(1);
            l_lisCreateGetCondDataContainer.add(
                WEB3DateUtility.formatDate(l_returnDate, "yyyyMMdd"));
        }
        
        //�����敪
        l_lisCreateGetCondDataContainer.add(WEB3AioHostStatusDef.NOT_DEAL);
        
        //�S�j���X�g����z����擾���A�ԋp����
        Object[] l_objCreateGetCondDataContainer = 
            new Object[l_lisCreateGetCondDataContainer.size()];
        l_lisCreateGetCondDataContainer.toArray(l_objCreateGetCondDataContainer);
        
        log.exiting(STR_METHOD_NAME);
        return l_objCreateGetCondDataContainer;
    }
    
    /**
     * (is�]�̓`�F�b�N��g���K���s)<BR>
     * �Y������،���Ђ��]�̓`�F�b�N��Ƀg���K���s������Ƃ���<BR>
     * �ݒ�ɂȂ��Ă��邩<BR>�ǂ����ƃ`�F�b�N����B<BR>
     * <BR>
     * �P�j�،���Ѓe�[�u���̃��R�[�h���擾����B<BR>
     * <BR>
     *   ���X�i ����.���XID �j.getInstitution().getDataSourceObject()<BR>
     * <BR>
     * �Q�j�،����Params.�o���\���g���K���s = <BR>
     * �@@�@@"1"�i�^�C�}�[���{�i�]�̓`�F�b�N����j�j�̏ꍇ�́Atrue��Ԃ��B<BR>
     * �@@�@@����ȊO�̏ꍇ�́Afalse��Ԃ��B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@return boolean
     * @@throws WEB3BaseException
     */
    private boolean isTradingPowerCheckedTriggerIssue(String l_strInstitutionCode) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "isTradingPowerCheckedTriggerIssue(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strInstitutionCode == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ��B");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�P�j�،���Ѓe�[�u���̃��R�[�h���擾����B       
        InstitutionRow l_institutionRow = null;
        try
        {
            l_institutionRow = InstitutionDao.findRowByInstitutionCode(l_strInstitutionCode);
            
            //�Q�j�،����Params.�o���\���g���K���s = "1"
            //�i�^�C�}�[���{�i�]�̓`�F�b�N����j�j�̏ꍇ�́Atrue��Ԃ��B 
            //����ȊO�̏ꍇ�́Afalse��Ԃ��B
            if( l_institutionRow != null && WEB3PaymentApplyTriggerDef.ENFORCEMENT_CHECK.equals(
                l_institutionRow.getPaymentApplyTrigger()))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            log.exiting(STR_METHOD_NAME);
            return false;
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

    /**
     * (getDB�����]�̓`�F�b�N�敪)<BR>
     * "DB�����]�̓`�F�b�N�敪"���擾���ԋp����B<BR>
     * <BR>
     * �P�j �w�،���Ѓe�[�u���x���ȉ������Ō����B<BR>
     * �@@�@@�i�����R�[�h���擾�o���Ȃ������ꍇ�͗�O���X���[����j<BR>
     * <BR>
     * �@@�@@�@@[����]<BR>
     * �@@�@@�@@�،���Ѓe�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h<BR>
     * <BR>
     * �Q�j 1�j�̌������ʂ��A"DB�����]�̓`�F�b�N�敪"���擾���ԋp����B<BR>
     * <BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)
     * @@return String
     * @@throws WEB3BaseException
     */
    private String getDbCurrentPriceCheckDiv(String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDbCurrentPriceCheckDiv(String)";
        log.entering(STR_METHOD_NAME);

        //"DB�����]�̓`�F�b�N�敪"���擾���ԋp����
        //�P�j �w�،���Ѓe�[�u���x���ȉ������Ō����B
        //�@@�@@�i�����R�[�h���擾�o���Ȃ������ꍇ�͗�O���X���[����j
        //�@@�@@�@@[����]
        //�@@�@@�@@�،���Ѓe�[�u��.�،���ЃR�[�h = ����.�،���ЃR�[�h
        String l_strDbCurrentPriceCheckDiv = null;
        try
        {
            InstitutionRow l_institutionRow =
                (InstitutionRow)InstitutionDao.findRowByInstitutionCode(l_strInstitutionCode);
            if (l_institutionRow == null)
            {
                log.debug("�e�[�u���ɊY������f�[�^������܂���B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            l_strDbCurrentPriceCheckDiv = l_institutionRow.getDbCurrentPriceCheckDiv();
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //�Q�j 1�j�̌������ʂ��A"DB�����]�̓`�F�b�N�敪"���擾���ԋp����B
        log.exiting(STR_METHOD_NAME);
        return l_strDbCurrentPriceCheckDiv;
    }

    /**
     * (get�g���K���s����)<BR>
     * �V�X�e���v���t�@@�����X���A�o���]�̓`�F�b�N��̃g���K���s����<BR>
     * ���擾����B<BR>
     * <BR>
     * �P�j�@@�ȉ��̏����ŁA�V�X�e���v���t�@@�����X�e�[�u������������B<BR>
     * <BR>
     * [��������]<BR>
     * ���́i���ϐ����j = PAY_TRIGGER_ORDER_MAX_COUNT<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�o���������R�[�h�g���K�[���s����Max�l�j<BR>
     * <BR>
     * �Q�j�@@�擾�������R�[�h����Avalue ��ԋp����B<BR>
     *      ���R�[�h���擾�ł��Ȃ������ꍇ�A99999��ԋp����B<BR>
     */
    private int getTriggerIssueNumber() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTriggerIssueNumber()"; 
        log.entering(STR_METHOD_NAME);
        
        String l_preference = WEB3SystemPreferencesNameDef.PAY_TRIGGER_ORDER_MAX_COUNT;
        // �ȉ��̏����ŁA�V�X�e���v���t�@@�����X�e�[�u������������B
        // [��������]
        // ���́i���ϐ����j = PAY_TRIGGER_ORDER_MAX.COUNT
        SystemPreferencesRow l_systemPreferencerow;
        try
        {
            l_systemPreferencerow = (SystemPreferencesRow)SystemPreferencesDao.findRowByName(l_preference);
            if (l_systemPreferencerow != null)
            {
                int l_intValue = Integer.parseInt(l_systemPreferencerow.getValue());
                return l_intValue;
            }
        }
        catch (DataQueryException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), 
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_ex.getMessage(), 
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
        return 99999;
    }
}
@
