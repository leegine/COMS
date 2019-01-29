head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.51.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccSettingContentsConfirmServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݒ���e�m�F�T�[�r�XImpl(WEB3ToSuccSettingContentsConfirmServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/1�V ���@@�r(���u) �V�K�쐬
Revesion History : 2008/04/14 ��іQ (���u) ���f��No.295
Revesion History : 2008/05/08 ��іQ (���u) ���f��No.346
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccClientRequestService;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccSettingContentConfirmResponse;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingContentsConfirmService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (�ݒ���e�m�F�T�[�r�XImpl)<BR>
 * �ݒ���e�m�F�T�[�r�X�����N���X<BR>
 * @@author ���r <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccSettingContentsConfirmServiceImpl extends WEB3ToSuccClientRequestService implements WEB3ToSuccSettingContentsConfirmService 
{
    /**
     * �i���O�o�̓��[�e�B���e�B�j�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccSettingContentsConfirmServiceImpl.class);   
    /**
     * @@roseuid 4348E2FC00EA
     */
    public WEB3ToSuccSettingContentsConfirmServiceImpl() 
    {
     
    }
    
    /**
     * �ݒ���e�m�F�T�[�r�X�������s���B<BR>
     * <BR>
     * this.get�m�F���()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D18D00153
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException  
    {

         final String STR_METHOD_NAME =
             "execute(WEB3GenRequest l_request)";
         log.entering(STR_METHOD_NAME);

         if (l_request == null)
         {
             log.error("�p�����[�^.���N�G�X�g�f�[�^��null�ł��B");
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 getClass().getName() + "." + STR_METHOD_NAME);
         }

         WEB3GenResponse l_response = null;

         if (l_request instanceof WEB3SuccSettingContentConfirmRequest)
         {
             l_response =
                this.getConfirmScreen((WEB3SuccSettingContentConfirmRequest)l_request);
         }
         else
         {
             log.error("�p�����[�^.���N�G�X�g�f�[�^�̌^���s���ł��B");
             throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                 getClass().getName() + "." + STR_METHOD_NAME);
         }

         log.exiting(STR_METHOD_NAME);
         return l_response;
     
    }
    
    /**
     * (get�m�F���)<BR>
     * �ݒ���e�m�F��ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ݒ���e�m�F�jget�m�F��ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ݒ���e�m�F���N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccSettingContentConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D191E02AA
     */
    protected WEB3SuccSettingContentConfirmResponse getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getConfirmScreen(WEB3SuccSettingContentConfirmRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        //1.1.validate
        l_request.validate();
        
        //1.2.validate������t�\
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.3 get�⏕����
        SubAccount l_subAccount = this.getSubAccount(l_request.commodityType);
        
        //1.4 validate�A�������戵�\
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();        
        //�،���ЃR�[�h
        String l_strInstitution = l_subAccount.getInstitution().getInstitutionCode();
        //�����^�C�v�F 
        //�@@[���i�敪 == ("��������" or "�M�p���")�̏ꍇ] 
        //�@@�@@ProductTypeEnum.���� 
        //�@@[��L�ȊO�̏ꍇ] 
        //�@@ProductTypeEnum.�敨�I�v�V���� 
        ProductTypeEnum  l_productType = null;
        //���i�敪
        String l_strCommodityType = l_request.commodityType;
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType) 
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
        {
            l_productType = ProductTypeEnum.EQUITY;
        }
        else
        {
            l_productType = ProductTypeEnum.IFO;
        }
        // �敨�^�I�v�V�����敪�F 
        // [���i�敪 == "�敨"�̏ꍇ] 
        // "�敨" 
        // [���i�敪 == "�I�v�V����"�̏ꍇ] 
        // "�I�v�V����" 
        // [��L�ȊO�̏ꍇ] 
        //�@@"DEFAULT"
        String l_strFuturesOptionDiv = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType))
        {
        	l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
        {
        	l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.OPTION;
        }
        else
        {
        	l_strFuturesOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
        }
        
        l_orderManager.validateSuccOrderHandling(l_strInstitution, l_productType, l_strFuturesOptionDiv);

        //1.5 �����P�ʂ��擾����B 
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        OrderUnit l_orderUnit = 
            l_dataGettingService.getOrderUnit(Long.parseLong(l_request.parentOrderId), l_strCommodityType);

        long l_lngOrderId = l_orderUnit.getOrderId();
        //���N�G�X�g�f�[�^.���i�敪 == ("��������" or "�M�p���")�̏ꍇ
        EqTypeOrderUnit[] l_succEqTypeOrderUnits = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType)
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
        {
            //get�L�������q�����P�ʈꗗ
            try 
            {
                l_succEqTypeOrderUnits = l_orderManager.getOpenReserveEqtypeOrderUnits(l_lngOrderId);
            } 
            catch(NotFoundException l_nfe)
            {
                log.error("�L�������q�����P�ʈꗗ���擾�ł��܂���B", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����ID:[" + l_lngOrderId + "]�ɊY������L�������q�����P�ʈꗗ���擾�ł��܂���B",
                    l_nfe);
            } 
        }
        //���N�G�X�g�f�[�^.���i�敪 == ("�敨" or "�I�v�V����")
        WEB3ToSuccIfoOrderUnitImpl[] l_toSuccIfoOrderUnits = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType)
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
        {
            // get�L���敨OP�q�����P�ʈꗗ(long)
            l_toSuccIfoOrderUnits = l_orderManager.getOpenReserveIfoOrderUnits(l_lngOrderId); 
        } 

		//1.7�A���������׃C���X�^���X�𐶐�����B
        WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
        
        //1.8 create�A����������
        l_dataGettingService.createSuccOrderUnit(l_succOrderUnit, l_orderUnit, false);
        
        //1.9 create�\�񒍕�����
        //get�L�������q�����P�ʈꗗ(�e�����̒���ID)�̖߂�l��null�ȊO�@@���A
        //get�L�������q�����P�ʈꗗ(�e�����̒���ID)�̗v�f����0�ȊO�̏ꍇ
        if (l_succEqTypeOrderUnits != null && l_succEqTypeOrderUnits.length != 0)
        {
            RsvEqOrderUnitRow[] l_rsvEqOrderUnitRows = new RsvEqOrderUnitRow[l_succEqTypeOrderUnits.length];
            for (int i = 0; i < l_succEqTypeOrderUnits.length; i++)
            {
                l_rsvEqOrderUnitRows[i] = (RsvEqOrderUnitRow)l_succEqTypeOrderUnits[i].getDataSourceObject();
            }
            l_dataGettingService.createRsvOrderUnit(l_succOrderUnit, l_rsvEqOrderUnitRows, false);
        }

        //get�L���敨OP�q�����P�ʈꗗ(�e�����̒���ID)�̖߂�l��null�ȊO�@@���A
        //get�L���敨OP�q�����P�ʈꗗ(�e�����̒���ID)�̗v�f����0�ȊO�̏ꍇ
        else if (l_toSuccIfoOrderUnits != null && l_toSuccIfoOrderUnits.length != 0)
        {
            RsvIfoOrderUnitRow[] l_rsvIfoOrderUnitRows = new RsvIfoOrderUnitRow[l_toSuccIfoOrderUnits.length];
            for (int i = 0; i < l_toSuccIfoOrderUnits.length; i++)
            {
                l_rsvIfoOrderUnitRows[i] = (RsvIfoOrderUnitRow)l_toSuccIfoOrderUnits[i].getDataSourceObject();
            }
            l_dataGettingService.createRsvOrderUnit(l_succOrderUnit, l_rsvIfoOrderUnitRows, false);
        }

        WEB3SuccSettingContentConfirmResponse l_response = (WEB3SuccSettingContentConfirmResponse)l_request.createResponse();
        l_response.orderUnit = l_succOrderUnit;
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
        
    }
}
@
