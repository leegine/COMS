head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.50.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccAdditionalContentsSelectServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ǉ����e�I���T�[�r�XImpl(WEB3ToSuccAdditionalContentsSelectServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 ������(���u) �V�K�쐬
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.triggerorder.WEB3ToSuccClientRequestService;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccAdditionalContentSelectRequest;
import webbroker3.triggerorder.message.WEB3SuccAdditionalContentSelectResponse;
import webbroker3.triggerorder.message.WEB3SuccTradingInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccAdditionalContentsSelectService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3Toolkit;

/**
 * (�ǉ����e�I���T�[�r�XImpl)<BR>
 * �ǉ����e�I���T�[�r�X�����N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3ToSuccAdditionalContentsSelectServiceImpl extends WEB3ToSuccClientRequestService implements WEB3ToSuccAdditionalContentsSelectService 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccAdditionalContentsSelectServiceImpl.class);
    
    /**
     * @@roseuid 4348E3640128
     */
    public WEB3ToSuccAdditionalContentsSelectServiceImpl() 
    {
     
    }
    
    /**
     * �ǉ����e�I���T�[�r�X�������s���B<BR>
     * <BR>
     * this.get�I�����()���R�[������B<BR>
     * @@param l_request - ���N�G�X�g
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D17640356
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

        if (l_request instanceof WEB3SuccAdditionalContentSelectRequest)
        {
            l_response =
                this.getSelectScreen( (WEB3SuccAdditionalContentSelectRequest) l_request);
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
     * (get�I�����)<BR>
     * �ǉ����e�I����ʕ\���������s���B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �u�i�ǉ����e�I���jget�I����ʁv�Q��<BR>
     * @@param l_request - (���N�G�X�g�f�[�^)<BR>
     * �ǉ����e�I�����N�G�X�g�I�u�W�F�N�g<BR>
     * @@return WEB3SuccAdditionalContentSelectResponse
     * @@throws WEB3BaseException
     * @@roseuid 431D17CD0039
     */
    protected WEB3SuccAdditionalContentSelectResponse getSelectScreen(WEB3SuccAdditionalContentSelectRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        //1.1���N�G�X�g�f�[�^�̐��������`�F�b�N����B
        l_request.validate();
        //1.2�V�X�e�����ً}��~���A�o�b�`���ł��邩�`�F�b�N����B
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //1.3�ڋq���擾����B
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)this.getMainAccount();
        //1.4�A���������戵�\�ł��邩�`�F�b�N����B 
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        //�،���ЃR�[�h�F�@@get����()�̖߂�l.�،���ЃR�[�h 
        String l_strInstitution = l_mainAccount.getInstitution().getInstitutionCode();
        
        String l_strCommodityType = l_request.commodityType;
        //�����^�C�v�F 
        //�@@[���i�敪 == ("��������" or "�M�p���")�̏ꍇ] 
        //�@@�@@ProductTypeEnum.���� 
        //�@@[��L�ȊO�̏ꍇ] 
        //�@@ProductTypeEnum.�敨�I�v�V����         
        ProductTypeEnum l_productType = null;
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityType) 
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityType))
        {
            l_productType = ProductTypeEnum.EQUITY;
        }
        else
        {
            l_productType = ProductTypeEnum.IFO;
        }
        
        //�敨�^�I�v�V�����敪�F 
        // �@@[���i�敪 == "�敨"�̏ꍇ] 
        // �@@�@@"�敨" 
        //�@@[���i�敪 == "�I�v�V����"�̏ꍇ] 
        // �@@�@@"�I�v�V����" 
        //�@@[��L�ȊO�̏ꍇ] 
        // �@@�@@"DEFAULT"
        String l_strfuturesOptionDiv = null;
        if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityType))
        {
            l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.FUTURES;
        }
        else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityType))
        {
            l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.OPTION;
        }
        else
        {
            l_strfuturesOptionDiv = WEB3FuturesOptionDivDef.DEFAULT;
        }

        l_orderManager.validateSuccOrderHandling(l_strInstitution, l_productType, l_strfuturesOptionDiv);
        
        //1.5�����P�ʂ��擾����B 
        WEB3ToSuccDataGettingService l_dataGettingService = 
            (WEB3ToSuccDataGettingService)Services.getService(WEB3ToSuccDataGettingService.class);
        OrderUnit l_orderUnit = 
            l_dataGettingService.getOrderUnit(Long.parseLong(l_request.parentOrderId), l_strCommodityType);
        
        //1.6�g���K�[�������ݒ�\�Ȓ������ǂ����`�F�b�N����B 
        l_orderManager.validateTriggerOrderSettingToParentOrder(l_orderUnit);
        // �g���K�[�������ǉ��ݒ�\���ǂ����`�F�b�N����B
        l_orderManager.validateSuccOrderMaxQuantity(l_orderUnit);
        //1.7�A������������̈ꗗ���쐬����B 
        WEB3SuccTradingInfo[] l_tradingInfos = 
            this.createSuccOrderTradedInfoList(l_mainAccount, l_orderUnit.getOrderType(), l_strCommodityType);
        
        //1.8���X�|���X�f�[�^�𐶐�����B
        WEB3SuccAdditionalContentSelectResponse l_reponse = 
            (WEB3SuccAdditionalContentSelectResponse)l_request.createResponse();
        
        //1.9���b�Z�[�W (*)�v���p�e�B�Z�b�g
        l_reponse.parentOrderId = l_request.parentOrderId;
        l_reponse.succTradingList = l_tradingInfos;
        
        log.exiting(STR_METHOD_NAME);
        return l_reponse;
    }
    
    /**
     * (create�A������������ꗗ)<BR>
     * �ڋq���I���\�Ȏ�����̈ꗗ���쐬����B<BR>
     * <BR>
     * �P�j�@@���N�G�X�g�f�[�^.���i�敪�ɂ��<BR>
     * �@@�����Ώۂ̏��i�敪�ꗗ�����肷��B<BR>
     * <BR>
     * �@@���N�G�X�g�f�[�^.���i�敪���A<BR>
     * �@@["��������" or "�M�p���"�̏ꍇ]<BR>
     * �@@�@@�����Ώۂ̏��i�敪�ꗗ = "��������", "�M�p���"<BR>
     * <BR>
     * �@@["�敨" or "�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�����Ώۂ̏��i�敪�ꗗ = "�敨", "�I�v�V����"<BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B<BR>
     * <BR>
     * �R�j�@@�P�j�ɂČ��肵�������Ώۂ̏��i�敪�ꗗ��<BR>
     * �@@�v�f�����A�ȉ��̏������s���B<BR>
     * �@@�R�|�P�j�@@�A������������C���X�^���X�𐶐�����B<BR>
     * �@@�R�|�Q�j�@@���������A������������Ƀv���p�e�B���Z�b�g����B<BR>
     * <BR>
     * �@@���i�敪 = �����Ώۂ̏��i�敪<BR>
     * �@@�A����������敪�ꗗ = <BR>
     * �@@�@@�ȉ��̗����\�b�h�̖߂�l��v�f�Ƃ���z��<BR>
     * �@@�@@�@@this.get���Δ������()<BR>
     * �@@�@@�A�A�������}�l�[�W��Impl.get�A����������ꗗ()<BR>
     * �@@�@@���@@�A�A�̖߂�l�Ƃ�null�̏ꍇ�A���̗v�f�֏������ڍs����B<BR>
     * �@@�@@�@@(continue)<BR>
     * <BR>
     * �@@�@@[get���Δ������()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@������ʁF�@@�p�����[�^.�������<BR>
     * �@@�@@�@@���i�敪�F�@@�����Ώۂ̏��i�敪<BR>
     * <BR>
     * �@@�@@[get�A����������ꗗ()�Ɏw�肷�����]<BR>
     * �@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq<BR>
     * �@@�@@�@@������ʁF�@@�p�����[�^.�������<BR>
     * �@@�@@�@@���i�敪�ꗗ�F�@@�����Ώۂ̏��i�敪�݂̂�<BR>
     * �@@�@@�@@�@@�v�f�Ƃ���z��<BR>
     * <BR>
     * �@@�R�|�R�j�@@��������ArrayList�Ƀv���p�e�B�Z�b�g����<BR>
     * �@@�@@�C���X�^���X��ǉ�����B<BR>
     * <BR>
     * �S�j�@@ArrayList.toArray()�̖߂�l��ԋp����B<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_orderType - (�������)<BR>
     * �������<BR>
     * @@param l_strCommodityDiv - (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * 
     * @@return WEB3SuccTradingInfo[]
     * @@throws WEB3BaseException
     * @@roseuid 432658740097
     */
    protected WEB3SuccTradingInfo[] createSuccOrderTradedInfoList(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSuccOrderTradedInfoList(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv)";
        log.entering(STR_METHOD_NAME);
        
        if (l_mainAccount == null)
        {
            log.error("�p�����[�^Null�o���Ȃ��B");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3ToSuccAdditionalContentsSelectServiceImpl.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }
        
        //�P�j�@@���N�G�X�g�f�[�^.���i�敪�ɂ�菈���Ώۂ̏��i�敪�ꗗ�����肷��B
        //���N�G�X�g�f�[�^.���i�敪���A
        //* �@@["��������" or "�M�p���"�̏ꍇ]
        //* �@@�@@�����Ώۂ̏��i�敪�ꗗ = "��������", "�M�p���"
        String[] l_strPorductDivList = new String[2];
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv)
            || WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            l_strPorductDivList[0] = WEB3CommodityDivDef.EQUITY;
            l_strPorductDivList[1] = WEB3CommodityDivDef.MARGIN;
        }

        ///["�敨" or "�I�v�V����"�̏ꍇ]
        //�����Ώۂ̏��i�敪�ꗗ = "�敨", "�I�v�V����"
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv)
            || WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            l_strPorductDivList[0] = WEB3CommodityDivDef.FUTURE;
            l_strPorductDivList[1] = WEB3CommodityDivDef.OPTION;
        }
        
        //�Q�j�@@ArrayList�𐶐�����B
        List l_lisTradingInfo = new ArrayList(); 
        
        if (l_strPorductDivList != null)
        {
            //�R�j�@@�P�j�ɂČ��肵�������Ώۂ̏��i�敪�ꗗ�̗v�f�����A�ȉ��̏������s���B
            for (int i = 0; i <l_strPorductDivList.length; i ++)
            {
                //�R�|�P�j�@@�A������������C���X�^���X�𐶐�����B
                WEB3SuccTradingInfo l_tradingInfo = new WEB3SuccTradingInfo();
                
                //�R�|�Q�j�@@���������A������������Ƀv���p�e�B���Z�b�g����B
                /*
                 * ���i�敪 = �����Ώۂ̏��i�敪
                 * �@@�A����������敪�ꗗ = 
                 * �@@�@@�ȉ��̗����\�b�h�̖߂�l��v�f�Ƃ���z��
                 * �@@�@@�@@this.get���Δ������()
                 * �@@�@@�A�A�������}�l�[�W��Impl.get�A����������ꗗ()
                 */
                String[] l_strTrades = 
                    this.getReversingTrade(l_mainAccount, l_orderType, l_strPorductDivList[i]);
                
                WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
                String[] l_strTargetProductDiv = {l_strPorductDivList[i]};
                String [] l_strTradeLists = 
                    l_orderManager.getSuccOrderTradeList(l_mainAccount, l_orderType, l_strTargetProductDiv);
                
                //���@@�A�A�̖߂�l�Ƃ�null�̏ꍇ�A���̗v�f�֏������ڍs����B
                //(continue)
                if (l_strTrades == null && l_strTradeLists == null)
                {
                    continue;
                }
                
                l_tradingInfo.commodityType = l_strPorductDivList[i];
                //�R�|�R�j�@@��������ArrayList�Ƀv���p�e�B�Z�b�g�����C���X�^���X��ǉ�����B
                Object[] l_objSuccTradingType =  WEB3Toolkit.merge(l_strTrades,l_strTradeLists);
                
                if (l_objSuccTradingType != null)
                {
                    String[] l_strSuccTradingType = new String[l_objSuccTradingType.length];
                    for (int j = 0; j < l_objSuccTradingType.length; j++)
                    {
                        l_strSuccTradingType[j] = l_objSuccTradingType[j].toString();
                    }
                    
                    l_tradingInfo.succTradingTypeList = l_strSuccTradingType;
                    l_lisTradingInfo.add(l_tradingInfo);
                }

                                
            }
        }
        //�S�j�@@ArrayList.toArray()�̖߂�l��ԋp����B
        WEB3SuccTradingInfo[] l_tradingInfos = new WEB3SuccTradingInfo[l_lisTradingInfo.size()];
        l_lisTradingInfo.toArray(l_tradingInfos);
        
        log.exiting(STR_METHOD_NAME);
        return l_tradingInfos;       

    }
    
    /**
     * (get���Δ������)<BR>
     * �����̒�����ʁA���i�敪�ɊY�����锽�Δ�������� <BR>
     * �ԋp����B <BR>
     * <BR>
     * �P�j�@@���Δ�������敪�ꗗ�̎擾 <BR>
     * �@@�A�������}�l�[�W��Impl.get���Δ������()���R�[������B <BR>
     * <BR>
     * �@@[get���Δ������()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�ڋq�F�@@�p�����[�^.�ڋq <BR>
     * �@@�@@�@@������ʁF�@@�p�����[�^.������� <BR>
     * <BR>
     * �@@�߂�l��null�̏ꍇ�Anull��ԋp���A�I������B <BR>
     * <BR>
     * <BR>
     * �Q�j�@@ArrayList�𐶐�����B <BR>
     * <BR>
     * �R�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B <BR>
     * �@@�R?�P�j�@@�p�����[�^.���i�敪 == <BR>
     * �@@�@@�A�������f�[�^�擾�T�[�r�XImpl.get���i�敪()�̏ꍇ�A <BR>
     * �@@�@@��������ArrayList�ɏ����Ώۂ̗v�f��ǉ�����B <BR>
     * <BR>
     * �@@�@@[get���i�敪()�Ɏw�肷�����] <BR>
     * �@@�@@�@@�A����������敪�F�@@�����Ώۂ̗v�f <BR>
     * <BR>
     * �S�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B <BR>
     * �@@�@@�@@����������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * �ڋq�I�u�W�F�N�g<BR>
     * @@param l_orderType - (�������)<BR>
     * �������
     * @@param l_lisReturnTrades - (�A����������敪)<BR>
     * �A����������敪<BR>
     * 
     * @@return String[]
     * @@throws WEB3BaseException
     * @@roseuid 4327D5650261
     */
    protected String[] getReversingTrade(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "getReversingTrade(WEB3GentradeMainAccount l_mainAccount, OrderTypeEnum l_orderType, String l_strCommodityDiv)";
        log.entering(STR_METHOD_NAME);
        
        //1�j�@@���Δ�������敪�ꗗ�̎擾
        WEB3ToSuccOrderManagerImpl l_orderManager = (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
        
        //�@@�A�������}�l�[�W��Impl.get���Δ������()���R�[������B
        String[] l_strTrades = l_orderManager.getReversingTrades(l_mainAccount, l_orderType);
        
        //�߂�l��null�̏ꍇ�Anull��ԋp���A�I������B
        if(l_strTrades == null)
        {
            return null;        	
        }

		//�Q�j�@@ArrayList�𐶐�����B
		List l_lisReturnTrades = new ArrayList();

        //�R�j�@@�P�j�̖߂�l�̗v�f�����A�ȉ��̏������J��Ԃ��B
		for(int i = 0; i < l_strTrades.length; i++)
		{

            //�R?�P�j�@@�p�����[�^.���i�敪 == �A�������f�[�^�擾�T�[�r�XImpl.get���i�敪()�̏ꍇ�A
            //��������ArrayList�ɏ����Ώۂ̗v�f��ǉ�����B <BR>
			WEB3ToSuccDataGettingServiceImpl toSuccDataGettingService = new WEB3ToSuccDataGettingServiceImpl();
			if(l_strCommodityDiv == toSuccDataGettingService.getCommodityDiv(l_strTrades[i]))
			{
				l_lisReturnTrades.add(l_strTrades[i]);
			}
		}

		String[] l_strReturnTrades = new String[l_lisReturnTrades.size()];
		l_lisReturnTrades.toArray(l_strReturnTrades);

        //��������ArrayList.size() == 0�̏ꍇ�Anull��ԋp����B
		if(l_lisReturnTrades.size() == 0)
		{
			return null;
		}
		
		//�S�j�@@��������ArrayList.toArray()�̖߂�l��ԋp����B
		return l_strReturnTrades;

    }
}
@
