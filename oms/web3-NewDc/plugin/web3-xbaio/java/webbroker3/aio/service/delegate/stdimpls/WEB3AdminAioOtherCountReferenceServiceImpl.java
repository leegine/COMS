head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.30.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���̑������Ɖ�T�[�r�XImpl(WEB3AdminAioOtherCountReferenceServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/7/7 ��O�� (���u) �V�K�쐬   
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.data.OtherOrderExecutedCountRow;
import webbroker3.aio.define.WEB3AioOtherOrderProductDivDef;
import webbroker3.aio.define.WEB3AioOtherOrderRecordDivDef;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceInputRequest;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceInputResponse;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceRequest;
import webbroker3.aio.message.WEB3AdminAioOtherCountReferenceResponse;
import webbroker3.aio.message.WEB3AioDailyOrderCountUnit;
import webbroker3.aio.message.WEB3AioMonthlyOrderCountUnit;
import webbroker3.aio.message.WEB3AioOtherCountInfomationUnit;
import webbroker3.aio.message.WEB3AioOtherCountReferenceUnit;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioOtherCountReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���̑������Ɖ�T�[�r�XImpl)<BR>
 * ���̑������Ɖ�T�[�r�X�����N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioOtherCountReferenceService 
{
    /**
     * ���O���[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioOtherCountReferenceServiceImpl.class);  
    
    /**
     * @@roseuid 423562E500BB
     */
    public WEB3AdminAioOtherCountReferenceServiceImpl() 
    {
     
    }
    
    /**
     * ���̑������Ɖ�����s���B<BR>
     * <BR>
     * ���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̃��\�b�h���R�[������B<BR>
     * <BR>
     *   get���͉��() <BR>
     *   get�����Ɖ���()<BR>
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41E37DBE03B4
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        String l_strMethodName = "execute(WEB3GenRequest l_request)";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        WEB3GenResponse l_response;
        
        //���N�G�X�g�f�[�^�̌^�ɂ��A�ȉ��̏������R�[������B
        //get���͉��()
        //get�����Ɖ���()

        if (l_request instanceof WEB3AdminAioOtherCountReferenceInputRequest)
        {
            l_response = 
                getInputScreen((WEB3AdminAioOtherCountReferenceInputRequest)l_request);   
        }
        else if (l_request instanceof WEB3AdminAioOtherCountReferenceRequest)
        {
            l_response =
                getCountReferenceScreen((WEB3AdminAioOtherCountReferenceRequest)l_request);
        }
        else
        {
            log.debug("error in get necessory request");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }    

    /**
     * (get���͉��)<BR>
     * ���͉�ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���̑������Ɖ�T�[�r�X)get���͉�ʁv�Q��
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminAioOtherCountReferenceInputResponse
     * @@roseuid 421ADE7F03B2
     */
    protected WEB3AdminAioOtherCountReferenceInputResponse getInputScreen(
        WEB3AdminAioOtherCountReferenceInputRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = "getInputScreen(" +
                "WEB3AdminAioOtherCountReferenceInputRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 getInstanceFrom���O�C�����()
        WEB3Administrator l_administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 validate����(String, boolean) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���̑������Ɖ� 
        //is�X�V�F�@@false(�X�V�Ȃ�) 
        //�� �@@�\�J�e�S���R�[�h�́A
        //DB���C�A�E�g�u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_administrator.validateAuthority(
            WEB3TransactionCategoryDef.OTHER_ORDER_COUNT_MANAGEMENT, 
            false);
        
        //1.3 �Ǘ���.�،���ЃR�[�h���擾����B        
        String l_strInstitutionCode = l_administrator.getInstitutionCode();
        
        //1.4 get��g���ϋ@@�֖���(String) 
        //��g���ϋ@@�֖��ׂ̔z����擾����B 
        //�m�����n 
        //�،���ЃR�[�h�F get�،���ЃR�[�h()�̖߂�l
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                WEB3AioMultiBankSettleControlService.class);     
        
        WEB3AioSettleInstitutionUnit[] l_aioSelectSettleInstitutionUnits = 
            l_aioMultiBankSettleControlService.getAffiliatedPaySchemeDetails(
                l_strInstitutionCode);
        
        //1.5 �،���Ж��̏��i�敪���擾���ԋp����B 
        //[����] 
        //�،���ЃR�[�h�F�@@get�،���ЃR�[�h()�̖߂�l
        String[] l_strCommodityDivs = this.getCommodityDiv(l_strInstitutionCode);
        
        //1.6 createResponse()
        WEB3AdminAioOtherCountReferenceInputResponse l_response =
            (WEB3AdminAioOtherCountReferenceInputResponse)l_request.createResponse();
        
        //1.7  �v���p�e�B�Z�b�g
        //(*) �ȉ��̂Ƃ���ɁA�v���p�e�B���Z�b�g����B        
        //���X�|���X.���i�ꗗ = get���i�敪()�̖߂�l
        l_response.commodityDivList = l_strCommodityDivs;
        
        //���X�|���X.���ϋ@@�֏�� = get��g���ϋ@@�֖���()�̖߂�l        
        l_response.settleInstitutionUnits = l_aioSelectSettleInstitutionUnits;       
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (get�����Ɖ���)<BR>
     * �����Ɖ��ʎ擾�������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u(���̑������Ɖ�T�[�r�X)get�����Ɖ��ʁv�Q��
     * <BR>
     * @@param l_request - ���N�G�X�g�f�[�^
     * 
     * @@return WEB3AdminAioOtherCountReferenceResponse
     * @@roseuid 41E38B5E02AA
     */
    protected WEB3AdminAioOtherCountReferenceResponse getCountReferenceScreen(
        WEB3AdminAioOtherCountReferenceRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = "getCountReferenceScreen(" +
                "WEB3AdminAioOtherCountReferenceRequest l_request)";
        log.entering(l_strMethodName);
        
        //1.1 validate()
        //���N�G�X�g�f�[�^�̐��������`�F�b�N����B 
        l_request.validate();
        
        //1.2 getInstanceFrom���O�C�����( ) 
        //���O�C����񂩂�Ǘ��҃C���X�^���X���擾����B
        WEB3Administrator l_web3Administrator = 
            WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate����(String, boolean) �Ǘ��Ҍ����̃`�F�b�N���s���B 
        //[����] 
        //�@@�\�J�e�S���R�[�h�F�@@�@@�\�J�e�S���R�[�h.���̑������Ǘ� 
        //is�X�V�F�@@false(�X�V�Ȃ�) 
        //�� �@@�\�J�e�S���R�[�h�́ADB���C�A�E�g
        // �u�Ǘ��Ҍ����e�[�u��.xls#�i�⑫�����j�@@�\�J�e�S���R�[�h�ꗗ�v�Q�ƁB
        l_web3Administrator.validateAuthority(
            WEB3TransactionCategoryDef.OTHER_ORDER_COUNT_MANAGEMENT,
            false);
        
        //1.4 validate���X����(String[]) ���X�����̃`�F�b�N���s���B 
        //[����] 
        //���X�R�[�h�F ���N�G�X�g�f�[�^.���X�R�[�h 
        l_web3Administrator.validateBranchPermission(l_request.branchCodeList);
        
        //1.5 get�،���ЃR�[�h( ) �Ǘ���.�،���ЃR�[�h���擾����B 
        String l_strInstitutionCode = 
            l_web3Administrator.getInstitution().getInstitutionCode();
        
        //1.6 ����������������쐬����B 
        //[����] 
        //���N�G�X�g�f�[�^�F�@@���̑������Ɖ�N�G�X�g 
        String l_strWhereClause = this.createQueryString(l_request);
        
        //1.7 ���������f�[�^�R���e�i���쐬����B 
        //[����] 
        //�،���ЃR�[�h�Fget�،���ЃR�[�h()�̖߂�l 
        //���N�G�X�g�f�[�^�F���̑������Ɖ�N�G�X�g
        Object l_bindVars[] = this.createQueryContainer(
            l_strInstitutionCode, 
            l_request);
        
        //1.8 �\�[�g�������쐬����B
        String l_strSortCond = this.createSortCond();
        
        //1.9 ���̑��p���������f�[�^���擾����B 
        //[����] 
        //��������������F�@@create��������������()�̖߂�l 
        //���������f�[�^�R���e�i�F�@@create���������f�[�^�R���e�i()�̖߂�l 
        //�\�[�g�����F�@@create�\�[�g����()�̖߂�l
        List l_lisOtherOrderCount = 
            this.getOtherCountReferenceUnit(
                l_strWhereClause, 
                l_bindVars, 
                l_strSortCond);
        
        // ���̑��p���������f�[�^�����݂��Ȃ��ꍇ�Anull��ԋp����
        if (l_lisOtherOrderCount == null)
        {
			// ���X�|���X�f�[�^�𐶐�����B
			WEB3AdminAioOtherCountReferenceResponse l_response = 
				(WEB3AdminAioOtherCountReferenceResponse)l_request.createResponse();        
			//���X�|���X�f�[�^.���̑������Ɖ���@@���@@null
			l_response.otherCountReferenceUnits = null;

			return l_response;
        }
        
        //1.10 ���̑��p���������f�[�^���i�[����ArrayList�𐶐�����B(�������חp)
        List l_lisDailyDetail = new ArrayList();
        
        //1.11 ���̑��p���������f�[�^���i�[����ArrayList�𐶐�����B(�������v�p)
        List l_lisMonthlyCount = new ArrayList();
        
        //1.12 �������׏W�v�p�G���A�𐶐�������������        
        //�E������ = ���̑��p���������Ɖ�Params.������(YYYYMMDD)
        //�E�����P = 0
        //�E�����Q = 0
        //�E�����R = 0
        OtherOrderExecutedCountRow l_initOtherOrderCountRow = 
            (OtherOrderExecutedCountRow) l_lisOtherOrderCount.get(0);
        Date l_datBizDate = WEB3DateUtility.getDate(
            l_initOtherOrderCountRow.getBizDate(), "yyyyMMdd");
        long l_lngDailyBuyOrderCount = 0;
        long l_lngDailySellOrderCount = 0;
        long l_lngDailyExecuteCount = 0;
        
        //1.13 �������v�W�v�p�G���A�𐶐�������������
        //�E�N�� = ���̑��p���������Ɖ�Params.������(YYYYMM)
        //�E�����P = 0
        //�E�����Q = 0
        //�E�����R = 0
        Date l_datInitRowBizDate = WEB3DateUtility.getDate(
            l_initOtherOrderCountRow.getBizDate(), "yyyyMMdd");
        
        String l_strYearMonth = WEB3DateUtility.formatDate(
                l_datInitRowBizDate, "yyyyMM");
        
        long l_lngMonthlyBuyOrderCount = 0;
        long l_lngMonthlySellOrderCount = 0;
        long l_lngMonthlyExecuteCount = 0;
        
        OtherOrderExecutedCountRow l_otherOrderCountRow = null;
            
        //1.14 �擾�������R�[�h����Loop���������{����
        for (int i = 0; i < l_lisOtherOrderCount.size(); i++)
        {
            l_otherOrderCountRow = 
                (OtherOrderExecutedCountRow) l_lisOtherOrderCount.get(i);
            
            Date l_datOtherCountBizDate = WEB3DateUtility.getDate(
                    l_otherOrderCountRow.getBizDate(), "yyyyMMdd");
            
            String l_strYyyyMmBizdate = WEB3DateUtility.formatDate(
                    l_datOtherCountBizDate, "yyyyMM");
            
            //1.14.1  if �������׏W�v�G���A.������ == ���̑������Ɖ�Params.�������̏ꍇ
            if (WEB3DateUtility.compareToDay( 
                    l_datBizDate, l_datOtherCountBizDate) == 0)
            {
                //1.14.1.1 �������ׂ̌����W�v���s��
                //�E�������׏W�v�G���A.������(�ύX����)
                //�E�������׏W�v�G���A.�����P = �������׏W�v�G���A.�����P + 
                //     Long.parseLong(���̑��p���������Ɖ�Params.�����P)
                l_lngDailyBuyOrderCount += 
                        l_otherOrderCountRow.getBuyOrderCount();
                
                //�E�������׏W�v�G���A.�����Q = �������׏W�v�G���A.�����Q + 
                //     Long.parseLong(���̑��p���������Ɖ�Params.�����Q)                
                l_lngDailySellOrderCount += 
                        l_otherOrderCountRow.getSellOrderCount();
                
                //�E�������׏W�v�G���A.�����R = �������׏W�v�G���A.�����R + 
                //     Long.parseLong(���̑��p���������Ɖ�Params.�����Q)
                l_lngDailyExecuteCount += 
                        l_otherOrderCountRow.getExecuteCount();  
            }
            //1.14.2 if �������׏W�v�G���A.������ != ���̑������Ɖ�Params.�������̏ꍇ
            else
            {
                //1.14.2.1 ���̑��������C���X�^���X�𐶐�����B(�������חp)
                WEB3AioOtherCountInfomationUnit l_dailyOtherCountInfoUnit = 
                    new WEB3AioOtherCountInfomationUnit();
                
                //1.14.2.2 (*)�v���p�e�B�Z�b�g
                //(*)�ȉ��̒ʂ�ɁA�v���p�e�B�ɃZ�b�g����B
                //���̑��������.������ = �������׏W�v�G���A.������(YYYYMMDD)
                //���̑��������.�����P = �������׏W�v�G���A.�����P
                //���̑��������.�����Q = �������׏W�v�G���A.�����Q
                //���̑��������.�����R = �������׏W�v�G���A.�����R
                l_dailyOtherCountInfoUnit.orderBizDate = 
                    WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
                l_dailyOtherCountInfoUnit.buyOrderCount = l_lngDailyBuyOrderCount + "";
                if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
                    l_dailyOtherCountInfoUnit.sellOrderCount = l_lngDailySellOrderCount + "";
                    l_dailyOtherCountInfoUnit.executeCount = l_lngDailyExecuteCount + "";
                }
                //1.14.2.3 �u(1)�������חp�v���X�g�ɁA���̑���������ǉ�����B 
                //[����] 
                //���̑��������I�u�W�F�N�g 
                l_lisDailyDetail.add(l_dailyOtherCountInfoUnit);
                
                //1.14.2.4 �������׏W�v�G���A�����̑��p���������Ɖ�Params�̒l�ŏ���������
                //�������׏W�v�G���A�̏�����
                //�E�������׏W�v�G���A.������ = ���̑��p���������Ɖ�Params.������(YYYYMMDD)
                //�E�������׏W�v�G���A.�����P = Long.parseLong(���̑��p���������Ɖ�Params.�����P)
                //�E�������׏W�v�G���A.�����Q = Long.parseLong(���̑��p���������Ɖ�Params.�����Q)
                //�E�������׏W�v�G���A.�����R = Long.parseLong(���̑��p���������Ɖ�Params.�����R)
                l_datBizDate = l_datOtherCountBizDate;
                l_lngDailyBuyOrderCount = 
                        l_otherOrderCountRow.getBuyOrderCount();
                l_lngDailySellOrderCount = 
                        l_otherOrderCountRow.getSellOrderCount();
                l_lngDailyExecuteCount = 
                        l_otherOrderCountRow.getExecuteCount();                
            }
            
            //1.14.3 if �������v�W�v�G���A.�N�� == ���̑��p���������Ɖ�Params.������(YYYYMM)�̏ꍇ
            if (l_strYearMonth.equals(l_strYyyyMmBizdate))
            {
                //1.14.3.1 �������v�̌����W�v���s��
                //�������v�W�v
                //�E�������v�W�v�G���A.�N��(�ύX����)
                //�E�������v�W�v�G���A.�����P = �������v�W�v�G���A.�����P + 
                //     Long.parseLong(���̑��p���������Ɖ�Params.�����P)
                //�E�������v�W�v�G���A.�����Q = �������v�W�v�G���A.�����Q + 
                //     Long.parseLong(���̑��p���������Ɖ�Params.�����Q)
                //�E�������v�W�v�G���A.�����R = �������v�W�v�G���A.�����R + 
                //     Long.parseLong(���̑��p���������Ɖ�Params.�����R)
                l_lngMonthlyBuyOrderCount += 
                        l_otherOrderCountRow.getBuyOrderCount();
                
                l_lngMonthlySellOrderCount += 
                        l_otherOrderCountRow.getSellOrderCount();
                
                l_lngMonthlyExecuteCount += 
                        l_otherOrderCountRow.getExecuteCount();
            }
            //1.14.4 if �������v�W�v�G���A.�N�� != ���̑��p���������Ɖ�Params.������(YYYYMM)�̏ꍇ
            else
            {
                //1.14.4.1 ���̑��������C���X�^���X�𐶐�����B(�������v�p)
                WEB3AioOtherCountInfomationUnit l_monthlyOtherCountInfoUnit = 
                    new WEB3AioOtherCountInfomationUnit();
                
                //1.14.4.2 (*)�v���p�e�B�Z�b�g
                //(*)�ȉ��̒ʂ�ɁA�v���p�e�B�ɃZ�b�g����B
                //���̑��������.������ = �W�v�G���A.�N��(YYYYMM)
                //���̑��������.�����P = �W�v�G���A.�����P.toString()
                //���̑��������.�����Q = �W�v�G���A.�����Q.toString()
                //���̑��������.�����R = �W�v�G���A.�����R.toString()
                l_monthlyOtherCountInfoUnit.orderBizDate = l_strYearMonth;
                l_monthlyOtherCountInfoUnit.buyOrderCount = l_lngMonthlyBuyOrderCount + "";
				if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
                    l_monthlyOtherCountInfoUnit.sellOrderCount = l_lngMonthlySellOrderCount + "";
                    l_monthlyOtherCountInfoUnit.executeCount = l_lngMonthlyExecuteCount + "";
				}
                //1.14.4.3 �u(2)�������v�p�v���X�g�ɁA���̑���������ǉ�����B 
                //[����] 
                //���̑��������I�u�W�F�N�g 
                l_lisMonthlyCount.add(l_monthlyOtherCountInfoUnit);
                
                //1.14.4.4 �������v�W�v�G���A�����̑��p���������Ɖ�Params�̒l�ŏ���������
                //�������v�W�v�G���A�̏�����
                //�E�N�� = ���̑��p���������Ɖ�Params.������(YYYYMM)
                //�E�����P = Long.parseLong(���̑��p���������Ɖ�Params.�����P)
                //�E�����Q = Long.parseLong(���̑��p���������Ɖ�Params.�����Q)
                //�E�����R = Long.parseLong(���̑��p���������Ɖ�Params.�����R)
                l_strYearMonth = l_strYyyyMmBizdate;
                l_lngMonthlyBuyOrderCount = 
                        l_otherOrderCountRow.getBuyOrderCount();
                l_lngMonthlySellOrderCount = 
                        l_otherOrderCountRow.getSellOrderCount();
                l_lngMonthlyExecuteCount = 
                        l_otherOrderCountRow.getExecuteCount();
            }
        }
        
        //�W�v�ΏۍŏI������������
        //1.15 ���̑��������C���X�^���X�𐶐�����B(�W�v�ΏۍŏI���������̓������חp)
        WEB3AioOtherCountInfomationUnit l_lastDailyOtherCountInfoUnit = 
            new WEB3AioOtherCountInfomationUnit();
        
        //1.16 (*)�v���p�e�B�Z�b�g
        //(*)�ȉ��̒ʂ�ɁA�v���p�e�B�ɃZ�b�g����B
        //���̑��������.������ = �������׏W�v�G���A.������(YYYYMMDD)
        //���̑��������.�����P = �������׏W�v�G���A.�����P
        //���̑��������.�����Q = �������׏W�v�G���A.�����Q
        //���̑��������.�����R = �������׏W�v�G���A.�����R
        l_lastDailyOtherCountInfoUnit.orderBizDate = 
            WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd");
        l_lastDailyOtherCountInfoUnit.buyOrderCount = l_lngDailyBuyOrderCount + "";
		if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
            l_lastDailyOtherCountInfoUnit.sellOrderCount = l_lngDailySellOrderCount + "";
            l_lastDailyOtherCountInfoUnit.executeCount = l_lngDailyExecuteCount + "";
		}
        //1.17 �u(1)�������חp�v���X�g�ɁA���̑���������ǉ�����B 
        //[����] 
        //���̑��������I�u�W�F�N�g
        l_lisDailyDetail.add(l_lastDailyOtherCountInfoUnit);
        
        //�W�v�ΏۍŏI��������
        //1.18 ���̑��������C���X�^���X�𐶐�����B(�W�v�ΏۍŏI�����̌������v�p)
        WEB3AioOtherCountInfomationUnit l_lastMonthlyOtherCountInfoUnit = 
            new WEB3AioOtherCountInfomationUnit();
        
        //1.19 (*)�v���p�e�B�Z�b�g
        //(*)�ȉ��̒ʂ�ɁA�v���p�e�B�ɃZ�b�g����B
        //���̑��������.������ = �W�v�G���A.�N��(YYYYMM)
        //���̑��������.�����P = �W�v�G���A.�����P.toString()
        //���̑��������.�����Q = �W�v�G���A.�����Q.toString()
        //���̑��������.�����R = �W�v�G���A.�����R.toString()
        l_lastMonthlyOtherCountInfoUnit.orderBizDate = l_strYearMonth;
        l_lastMonthlyOtherCountInfoUnit.buyOrderCount = l_lngMonthlyBuyOrderCount + "";
		if (!WEB3AioOtherOrderProductDivDef.ONLINE_CASHIN.equals(l_request.commodityDiv)){
            l_lastMonthlyOtherCountInfoUnit.sellOrderCount = l_lngMonthlySellOrderCount + "";
            l_lastMonthlyOtherCountInfoUnit.executeCount = l_lngMonthlyExecuteCount + "";
		}
        //1.20 �u(2)�������v�p�v���X�g�ɁA���̑���������ǉ�����B 
        //[����] 
        //���̑��������I�u�W�F�N�g 
        l_lisMonthlyCount.add(l_lastMonthlyOtherCountInfoUnit);
        
        //1.21 ���X�g����z����擾����B
        WEB3AioOtherCountInfomationUnit[] l_dailyOtherCountInfomationUnits = 
            new WEB3AioOtherCountInfomationUnit[l_lisDailyDetail.size()];
        l_lisDailyDetail.toArray(l_dailyOtherCountInfomationUnits);
        
        //1.22 ���X�g����z����擾����B
        WEB3AioOtherCountInfomationUnit[] l_monthlyOtherCountInfomationUnits = 
            new WEB3AioOtherCountInfomationUnit[l_lisMonthlyCount.size()];
        l_lisMonthlyCount.toArray(l_monthlyOtherCountInfomationUnits);
        
        //1.23 �������ʌ������̃C���X�^���X�𐶐�����B 
        WEB3AioDailyOrderCountUnit l_dailyOrderCountUnit = 
            new WEB3AioDailyOrderCountUnit();
        
        //1.24 (*)�v���p�e�B�Z�b�g
        //(*)�ȉ��̒ʂ�ɁA�v���p�e�B�ɃZ�b�g����B
        //�������ʌ������.���̑��������@@���@@toArray()�̖߂�l((1)�������חp)
        l_dailyOrderCountUnit.otherCountInfomationUnits = 
            l_dailyOtherCountInfomationUnits;
        
        //1.25 �������ʌ������C���X�^���X�𐶐�����B
        WEB3AioMonthlyOrderCountUnit l_monthlyOrderCountUnit = 
            new WEB3AioMonthlyOrderCountUnit();
        
        //1.26 (*)�v���p�e�B�Z�b�g
        //(*)�ȉ��̒ʂ�ɁA�v���p�e�B�ɃZ�b�g����B
        //�������ʌ������.���̑��������@@���@@toArray()�̖߂�l((2)�������v�p)
        l_monthlyOrderCountUnit.otherCountInfomationUnits = 
            l_monthlyOtherCountInfomationUnits;
        
        //1.27 ���̑������Ɖ���̃C���X�^���X�𐶐�����B
        WEB3AioOtherCountReferenceUnit l_otherCountReferenceUnit = 
            new WEB3AioOtherCountReferenceUnit();
        
        //1.28 (*)�v���p�e�B�Z�b�g
        //(*)�ȉ��̒ʂ�ɁA�v���p�e�B���Z�b�g����B
        //���̑������Ɖ�.�������ʌ������@@���@@�������ʌ������
        l_otherCountReferenceUnit.monthlyOrderCountUnits = l_monthlyOrderCountUnit;
        
        //���̑������Ɖ�.�������ʌ������@@���@@�������ʌ������
        l_otherCountReferenceUnit.dailyOrderCountUnits = l_dailyOrderCountUnit;
                
        //1.29 ���X�|���X�f�[�^�𐶐�����B
        WEB3AdminAioOtherCountReferenceResponse l_response = 
            (WEB3AdminAioOtherCountReferenceResponse)l_request.createResponse();
        
        //1.30 (*)�v���p�e�B�Z�b�g
        //(*)���X�|���X�f�[�^�ɁA�v���p�e�B���Z�b�g����B
        //���X�|���X�f�[�^.���̑������Ɖ���@@���@@���̑������Ɖ���
        l_response.otherCountReferenceUnits = l_otherCountReferenceUnit;
        
        log.exiting(l_strMethodName);
        
        return l_response;
    }
    
    /**
     * (create��������������)<BR>
     * ���̑��p���������e�[�u������f�[�^���擾����ۂ̏����𐶐�����B <BR>
     * <BR>
     *�P�j��̕�����𐶐����� <BR>
     *<BR>
     *�Q�j�،���ЃR�[�h <BR>
     *<BR>
     *" institution_code = ?"���P�j�̕�����ɒǉ�����B <BR>
     *<BR>
     *�R�j���X�R�[�h <BR>
     *<BR>
     *�R�|�P�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() == 1 �̏ꍇ <BR>
     *<BR>
     *" and branch_code = ?"���P�j�̕�����ɒǉ�����B <BR>
     *<BR>
     *�R�|�Q�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() > 1 �̏ꍇ <BR>
     *<BR>
     *" and (branch_code= ? or branch_code= ? or ... or branch_code= ?)"<BR>
     *  ���P�j�̕�����ɒǉ�����B <BR>
     *<BR>
     *��" branch_code = ?"�̐��́A���X�R�[�h�̗v�f���Ɠ��� <BR>
     *<BR>
     *�S�j���R�[�h�敪 <BR>
     *<BR>
     *" and record_div = ?"���P�j�̕�����ɒǉ�����B <BR>
     *<BR>
     *�T�j���i�敪 <BR>
     *<BR>
     *" and product_div = ?"���P�j�̕�����ɒǉ�����B <BR>
     *<BR>
     *�U�j���ϋ@@��ID <BR>
     *<BR>
     *����.���N�G�X�g�f�[�^.���ϋ@@��ID != null�@@�̏ꍇ <BR>
     *" and pay_scheme_id = ?"���P�j�̕�����ɒǉ�����B <BR>
     *<BR>
     *�V�j������ <BR>
     *<BR>
     *" and biz_date >= ?"���P�j�̕�����ɒǉ�����B <BR>
     *<BR>
     *�W�j�������ԋp����<BR>
     *
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@return String
     * @@roseuid 41E38B5E02BA
     */
    protected String createQueryString(
            WEB3AdminAioOtherCountReferenceRequest l_request) 
            throws WEB3BaseException
    {
        String l_strMethodName = "createQueryString(" +
                "WEB3AdminAioOtherCountReferenceRequest l_request)";
        log.entering(l_strMethodName);
        
        //�P�j��̕�����𐶐����� 
        StringBuffer l_strBuffer = new StringBuffer();
        
        //�Q�j�،���ЃR�[�h 
        //" institution_code = ?"���P�j�̕�����ɒǉ�����B 
        l_strBuffer.append(" institution_code = ? ");
        
        //�R�j���X�R�[�h 
        //�R�|�P�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() == 1 �̏ꍇ 
        //" and branch_code = ?"���P�j�̕�����ɒǉ�����B 
        StringBuffer l_strBufferBranch = new StringBuffer();
        l_strBufferBranch.append(" and branch_code in ( ? ");    
        
        //�R�|�Q�j����.���N�G�X�g�f�[�^.���X�R�[�h.length() > 1 �̏ꍇ 
        //" and (branch_code= ? or branch_code= ? or ... 
        //  or branch_code= ?)"���P�j�̕�����ɒǉ�����B 
        //��" branch_code = ?"�̐��́A���X�R�[�h�̗v�f���Ɠ��� 
        
        for (int i = 1; i < l_request.branchCodeList.length; i++)
        {
            l_strBufferBranch.append(", ? ");
        }        
        l_strBufferBranch.append(")");
        
        l_strBuffer.append(l_strBufferBranch);
        
        //�S�j���R�[�h�敪 
        //" and record_div = ?"���P�j�̕�����ɒǉ�����B
        l_strBuffer.append(" and record_div = ? ");

        //�T�j���i�敪 
        //" and product_div = ?"���P�j�̕�����ɒǉ�����B
        l_strBuffer.append(" and product_div = ? ");
        
        //�U�j���ϋ@@��ID 
        //����.���N�G�X�g�f�[�^.���ϋ@@��ID != null�@@�̏ꍇ 
        //" and pay_scheme_id = ?"���P�j�̕�����ɒǉ�����B 
        if(!WEB3StringTypeUtility.isEmpty(l_request.paySchemeId))
        {
            l_strBuffer.append(" and pay_scheme_id = ? ");
        }
        
        //�V�j������ 
        //" and biz_date >= ?"���P�j�̕�����ɒǉ�����B 
        l_strBuffer.append(" and biz_date >= ? ");
        
        //�W�j�������ԋp����        
        log.exiting(l_strMethodName);
        return l_strBuffer.toString();
    }
    
    /**
     * (create���������f�[�^�R���e�i )<BR>
     * ���̑��p���������e�[�u������f�[�^���擾����ۂ̏����̃f�[�^�R���e�i�𐶐�����B<BR> 
     * <BR>
     * �P�j���ArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�j�،���ЃR�[�h <BR>
     * <BR>
     * ����.�،���ЃR�[�h���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �R�j���X�R�[�h <BR>
     * <BR>
     * ����.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �S�j���R�[�h�敪 <BR>
     * <BR>
     * �h0�h���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �T�j���i�敪 <BR>
     * <BR>
     * ����.���N�G�X�g�f�[�^.���i�敪���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �U�j���ϋ@@��ID <BR>
     * <BR>
     * ����.���N�G�X�g�f�[�^.���ϋ@@��ID != null�@@�̏ꍇ <BR>
     * ����.���N�G�X�g�f�[�^.���ϋ@@��ID���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �V�j������ <BR>
     * <BR>
     * ������(*1)���P�j��List�ɒǉ�����B <BR>
     * <BR>
     * �W�jList����z����擾���āA�ԋp����B <BR>
     * <BR>
     * (*1)������ <BR>
     * TradingSystem.getBizDate()�ɂċƖ����t���擾 <BR>
     * ������ = (�Ɩ����t - 2����)�̔N��(YYYYMM) + �h01�h <BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     * @@param l_request - ���N�G�X�g�f�[�^
     * @@param l_strStatusDiv - �X�e�[�^�X�敪
     * 
     * @@return String[]
     * @@roseuid 41E5E3FB01F0
     */
    protected String[] createQueryContainer(
            String l_strInstitutionCode, 
            WEB3AdminAioOtherCountReferenceRequest l_request) 
    {
        String l_strMethodName = 
            "createQueryContainer(String l_strInstitutionCode, " +
            "WEB3AdminAioOtherCountReferenceRequest l_request";
        log.entering(l_strMethodName);
        
        if (l_request == null)
        {
            log.debug("�p�����[�^Null�o���Ȃ�");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //�P�j���ArrayList�𐶐�����B
        List l_lisValue = new ArrayList();
        
        //�Q�j�،���ЃR�[�h 
        //����.�،���ЃR�[�h���P�j��List�ɒǉ�����B 
        l_lisValue.add(l_strInstitutionCode);
        
        //�R�j���X�R�[�h 
        //����.���N�G�X�g�f�[�^.���X�R�[�h�̊e�v�f���P�j��List�ɒǉ�����B
        for (int i = 0; i < l_request.branchCodeList.length; i++)
        {
            l_lisValue.add(l_request.branchCodeList[i]);
        }
        
        //�S�j���R�[�h�敪 
        //�h0�h���P�j��List�ɒǉ�����B       
        l_lisValue.add(WEB3AioOtherOrderRecordDivDef.DAILY_DETAIL);       
        
        //�T�j���i�敪 
        //����.���N�G�X�g�f�[�^.���i�敪���P�j��List�ɒǉ�����B       
        l_lisValue.add(l_request.commodityDiv);        
        
        //�U�j���ϋ@@��ID 
        //����.���N�G�X�g�f�[�^.���ϋ@@��ID != null�@@�̏ꍇ 
        //����.���N�G�X�g�f�[�^.���ϋ@@��ID���P�j��List�ɒǉ�����B 
        if (!WEB3StringTypeUtility.isEmpty(l_request.paySchemeId))
        {
            l_lisValue.add(l_request.paySchemeId);
        }
        
        //�V�j������ 
        //������(*1)���P�j��List�ɒǉ�����B 
        //(*1)������ 
        //TradingSystem.getBizDate()�ɂċƖ����t���擾 
        //������ = (�Ɩ����t - 2����)�̔N��(YYYYMM) + �h01�h 
        String l_strBusinessDate = 
            WEB3DateUtility.formatDate(GtlUtils.getTradingSystem().getBizDate(),"yyyyMM");
        
        int l_intBizDate = 
            Integer.parseInt(l_strBusinessDate.substring(0, 4)) * 12 + 
            Integer.parseInt(l_strBusinessDate.substring(4, 6)) - 2;
        
        String l_strBizDate = 
            WEB3StringTypeUtility.formatNumber((int)(l_intBizDate / 12)) + 
            WEB3StringTypeUtility.formatNumber(l_intBizDate % 12, 2) + "01";
             
        l_lisValue.add(l_strBizDate);
        
        //�W�jList����z����擾���āA�ԋp����B
        String[] l_strValue = new String[l_lisValue.size()];
        l_lisValue.toArray(l_strValue);
        
        log.exiting(l_strMethodName);
        
        return l_strValue;
    }
    
    /**
     * (get���̑��p���������ꗗ)<BR>
     * ���͉�ʎ擾�������s���B<BR><BR>
     * <BR>
     * �����̏����ɊY�����邻�̑��p��������Params�̈ꗗ��ԋp����B<BR> 
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR> 
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@arg0�F�@@���̑��p��������Row.TYPE <BR>
     * �@@�@@arg1�F�@@����.�������������� <BR>
     * �@@�@@arg2�F�@@����.�\�[�g���� <BR>
     * �@@�@@arg3�F�@@null <BR>
     * �@@�@@arg4�F�@@����.���������f�[�^�R���e�i <BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �R�j�Q�j�̌������ʂ�ԋp����B <BR>
     * 
     * @@param l_strQueryString - ��������������
     * @@param l_queryContainer - ���������f�[�^�R���e�i
     * @@param l_strSortCond - �\�[�g����
     * 
     * @@return List
     * @@roseuid 421ADE7F03B2
     */
    protected List getOtherCountReferenceUnit(
            String l_strQueryString, 
            Object[] l_queryContainer, 
            String l_strSortCond) 
            throws WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "getOtherCountReferenceUnit(String l_strQueryString," +  
            "Object[] l_queryContainer, String l_strSortCond)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRows  = null;
        try
        {   
            //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
            //arg0�F�@@���̑��p��������Row.TYPE 
            //arg1�F�@@����.�������������� 
            //arg2�F�@@����.�\�[�g���� 
            //arg3�F�@@null 
            //arg4�F�@@����.���������f�[�^�R���e�i 
            l_lisRows  = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrderExecutedCountRow.TYPE, 
                    l_strQueryString, 
                    l_strSortCond, 
                    null, 
                    l_queryContainer); 
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
        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisRows.size() == 0)
        {
            return null;
        }
        
        //�Q�j�P�j�̌������ʂ�ԋp����B        
        log.exiting(STR_METHOD_NAME);        
        return l_lisRows;
    }
    
    /**
     * (create�\�[�g����)<BR>
     * �\�[�g�������쐬����B <BR>
     * <BR>
     * �P�j�e�[�u���񕨗������A�ȉ��̃\�[�g������\���\�[�g������������쐬����B <BR>
     * <BR>
     * ���̑��p���������e�[�u��.�������@@���� <BR>
     * <BR>
     * �Q�j�쐬�����\�[�g�����������ԋp����B <BR>
     * 
     * @@return String
     * @@roseuid 421ADE7F03B2
     */
    protected String createSortCond() 
    {
        String l_strMethodName = "createSortCond()";
        log.entering(l_strMethodName);        
        
        String l_strSort = new String();
        
        //�P�j�e�[�u���񕨗������A�ȉ��̃\�[�g������\���\�[�g������������쐬����B 
        //���̑��p���������e�[�u��.�������@@���� 
        l_strSort = " biz_date ";
        
        //�Q�j�쐬�����\�[�g�����������ԋp����B
        log.exiting(l_strMethodName);
        
        return l_strSort;
    }
    
    /**
     * (get���i�敪)<BR>
     * �،���Ж��̏��i�敪���擾���ԋp����B <BR>
     * <BR>
     * �P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B<BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^] <BR>
     * �@@�@@arg0�F�@@���̑��p��������Row.TYPE <BR>
     * �@@�@@arg1�F�@@�h institution_code = ?�h <BR>
     * �@@�@@arg2�F�@@�h product_div�h <BR>
     * �@@�@@arg3�F�@@null <BR>
     * �@@�@@arg4�F�@@Object[]{����.�،���ЃR�[�h} <BR>
     * <BR>
     * �@@�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B <BR>
     * 
     * �Q�j���i�敪���A�u4:�M�p�ۏ؋�HULFT���M�v�A<BR>
     *    �y�яd�����R�[�h���揜���Ĕz��ɃZ�b�g����B <BR>
     * <BR>
     * �Q�|�P�jArrayList�𐶐�����B <BR>
     * <BR>
     * �Q�|�Q�j1���R�[�h�ڂ̍s�I�u�W�F�N�g.get���i�敪�̒l���i�[����G���A�𐶐����A<BR>
     * �l��ޔ�����B <BR>
     * �@@�@@�@@�@@(1���R�[�h�ڂ̍s�I�u�W�F�N�g.get���i�敪 = �h4�h�̏ꍇ��2���R�[�h�ڈȍ~<BR>
     * �̍s�I�u�W�F�N�g.get���i�敪 != �h4�h�̒l��ޔ�����) <BR>
     * <BR>
     * �Q�|�R�j�ޔ��������i�敪��List�ɒǉ�����B <BR>
     * <BR>
     * �Q�|�S�j�Q�|�Q�j�ȍ~�̃��R�[�h����Loop���������{���ȉ��̏������s���B <BR>
     * <BR>
     * �@@�@@�@@�ޔ��G���A�̏��i�敪 != �s�I�u�W�F�N�g.get���i�敪�A<BR>
     *          ���s�I�u�W�F�N�g.get���i�敪 != �h4�h�̏ꍇ�A <BR>
     * �@@�@@�@@���i�敪��List�ɒǉ����A<BR>
     *          �ޔ��G���A�ɍs�I�u�W�F�N�g.get���i�敪�̒l��������B<BR> 
     * <BR>
     * �Q�|�T�jList����z����擾����B <BR>
     * <BR>�@@�@@�@@�@@ 
     * �R�j�擾�����z���ԋp����B<BR>
     * 
     * @@param l_strInstitutionCode - �،���ЃR�[�h
     *
     * @@return String[]
     * @@roseuid 421ADE7F03B2
     */
    protected String[] getCommodityDiv(String l_strInstitutionCode) 
    throws WEB3BaseException
    {
        String STR_METHOD_NAME = "getCommodityDiv(String l_strInstitutionCode)";
        log.entering(STR_METHOD_NAME);
        
        List l_lisRows  = null;
        try
        {   
            //�P�jQueryProcessor.doFindAllQuery()���\�b�h���R�[������B
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^] 
            //arg0�F�@@���̑��p��������Row.TYPE 
            //arg1�F�@@�h institution_code = ?�h 
            //arg2�F�@@�h product_div�h 
            //arg3�F�@@null 
            //arg4�F�@@Object[]{����.�،���ЃR�[�h} 

            Object[] l_bindVars = {l_strInstitutionCode};
            l_lisRows  = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    OtherOrderExecutedCountRow.TYPE, 
                    " institution_code = ?", 
                    " product_div", 
                    null, 
                    l_bindVars ); 
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
        //�������ʂ��擾�ł��Ȃ������ꍇ�Anull��ԋp����B
        if (l_lisRows == null || l_lisRows.size() == 0)
        {
            return null;
        }

        //�Q�j���i�敪���A�u4:�M�p�ۏ؋�HULFT���M�v�A�y�яd�����R�[�h���揜���Ĕz��ɃZ�b�g����B 
        //�Q�|�P�jArrayList�𐶐�����B 
        List l_lisProductDiv = new ArrayList();
        
        //�Q�|�Q�j1���R�[�h�ڂ̍s�I�u�W�F�N�g.get���i�敪�̒l���i�[����G���A�𐶐����A�l��ޔ�����B 
        //�@@(1���R�[�h�ڂ̍s�I�u�W�F�N�g.get���i�敪 = �h4�h�̏ꍇ��
        //  2���R�[�h�ڈȍ~�̍s�I�u�W�F�N�g.get���i�敪 != �h4�h�̒l��ޔ�����) 

        OtherOrderExecutedCountRow l_otherOrderCountRow = 
            (OtherOrderExecutedCountRow) l_lisRows.get(0);
        String l_strProductDiv = l_otherOrderCountRow.getProductDiv();
        
        //�Q�|�R�j�ޔ��������i�敪��List�ɒǉ�����B
        if (!WEB3AioOtherOrderProductDivDef.MARGIN_GUARANTEE_HULFT.equals(
                l_strProductDiv))
        {
            l_lisProductDiv.add(l_strProductDiv);
        }
            
        for (int i = 1; i < l_lisRows.size(); i++)
        {
            l_otherOrderCountRow = (OtherOrderExecutedCountRow) l_lisRows.get(i);
            //�ޔ��G���A�̏��i�敪 != �s�I�u�W�F�N�g.get���i�敪�A
            //���s�I�u�W�F�N�g.get���i�敪 != �h4�h�̏ꍇ�A
            if (!l_strProductDiv.equals(l_otherOrderCountRow.getProductDiv()) && 
                    !WEB3AioOtherOrderProductDivDef.MARGIN_GUARANTEE_HULFT.equals(
                            l_otherOrderCountRow.getProductDiv()))
            {
                //���i�敪��List�ɒǉ����A�ޔ��G���A�ɍs�I�u�W�F�N�g.get���i�敪�̒l��������B 
                l_lisProductDiv.add(l_otherOrderCountRow.getProductDiv());
                l_strProductDiv = l_otherOrderCountRow.getProductDiv();
            }
        }
        //�Q�|�T�jList����z����擾����B
        String[] l_strCommodityDivs =  new String[l_lisProductDiv.size()];
        l_lisProductDiv.toArray(l_strCommodityDivs);
        
        //�R�j�擾�����z���ԋp����B
        log.exiting(STR_METHOD_NAME);        
        return l_strCommodityDivs;
    }
}
@
