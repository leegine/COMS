head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderActionUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\�����𖾍׍쐬�T�[�r�X�����N���X(WEB3IpoOrderActionUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 �A�C��(���u) �V�K�쐬
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>049
*/

package webbroker3.ipo.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.ipo.WEB3IpoBookbuildingOrderActionImpl;
import webbroker3.ipo.WEB3IpoBookbuildingOrderActionCreatedTimestampComparator;
import webbroker3.ipo.WEB3IpoOrderImpl;
import webbroker3.ipo.WEB3IpoProductImpl;
import webbroker3.ipo.data.IpoBookbuildingOrderActionRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3AcceptedResultDivDef;
import webbroker3.ipo.define.WEB3ProcessTypeDef;
import webbroker3.ipo.message.WEB3IPODemandHistoryUnit;
import webbroker3.ipo.service.delegate.WEB3IpoOrderActionUnitService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * IPO�\�����𖾍׍쐬�T�[�r�X�����N���X
 */
public class WEB3IpoOrderActionUnitServiceImpl implements WEB3IpoOrderActionUnitService 
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3IpoOrderActionUnitServiceImpl.class);        
    
    /**
     * @@roseuid 4112F1910016
     */
    public WEB3IpoOrderActionUnitServiceImpl() 
    {
     
    }
    
    /**
     * IPO�\�����𖾍ׂ��쐬����B<BR>
     * <BR>
     * �V�[�P���X�}<BR>
     * �uIPO�i���ʁjcreateIPO�\�����𖾍ׁv�Q�ƁB<BR>
     * @@param l_ipoOrder - (IPO�\��)<BR>
     * IPO�\���I�u�W�F�N�g
     * @@return webbroker3.ipo.message.WEB3IpoOrderActionUnit[]
     * @@roseuid 40EE69AE019F
     */
    public WEB3IPODemandHistoryUnit[] createOrderActionUnit(WEB3IpoOrderImpl l_ipoOrder)  throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createOrderActionUnit(WEB3IpoOrderImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoOrder == null)
        {   
            log.error("parameter is null type");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                   WEB3ErrorCatalog.BUSINESS_ERROR_01980,
                   this.getClass().getName() + STR_METHOD_NAME);                 
        }
        
        //1.1 get�u�b�N�r���f�B���O�\������
        OrderAction[] l_orderActions = l_ipoOrder.getOrderActions();
        
        WEB3IpoBookbuildingOrderActionCreatedTimestampComparator[] l_comparators =
            new WEB3IpoBookbuildingOrderActionCreatedTimestampComparator[1]; 
        l_comparators[0] = new WEB3IpoBookbuildingOrderActionCreatedTimestampComparator(WEB3AscDescDef.ASC);
        WEB3ArraysUtility.sort(l_orderActions, l_comparators);

        //1.2.IPO�\��::getIPO����
         WEB3IpoProductImpl l_product;
         l_product = (WEB3IpoProductImpl) l_ipoOrder.getProduct();
         IpoProductRow l_ipoProductRow = (IpoProductRow)l_product.getDataSourceObject();

        int l_intCount = l_orderActions.length;
        //1.3.ArrayList( )
        ArrayList l_arrayList = new ArrayList(l_intCount);
        
        //newest valid order action index
        int l_intLatestIndex = -1;
        
        //1.4.get�u�b�N�r���f�B���O�\������()�̖߂�l�̊e�v�f����LOOP
        IpoBookbuildingOrderActionRow l_orderActionRow = null;
        for(int i=0; i<l_intCount; i++)
        {
            WEB3IpoBookbuildingOrderActionImpl l_orderAction = 
                (WEB3IpoBookbuildingOrderActionImpl)l_orderActions[i];
            l_orderActionRow = 
                (IpoBookbuildingOrderActionRow)l_orderAction.getDataSourceObject();
             
            //1.4.1 IPO�\�����𖾍�
            WEB3IPODemandHistoryUnit l_ipoOrderActionUnit = new WEB3IPODemandHistoryUnit();
            //1.4.2 �v���p�e�B�Z�b�g
            
            //�\������No
            l_ipoOrderActionUnit.demandHistoryId = 
               Long.toString(l_orderActionRow.getBookbuildingOrderActionId());
            
            //��t����
            l_ipoOrderActionUnit.receptionDate = l_orderActionRow.getCreatedTimestamp();
            
            //IPO�������e�敪
            if(OrderStatusEnum.CANCELLED.equals(l_orderActionRow.getOrderStatus()))
            {
                l_ipoOrderActionUnit.ipoProcessingType = WEB3ProcessTypeDef.CANCEL;
            }
            else if(OrderStatusEnum.MODIFIED.equals(l_orderActionRow.getOrderStatus()))
            {
                l_ipoOrderActionUnit.ipoProcessingType = WEB3ProcessTypeDef.MODIFY;
            }
            else if(OrderStatusEnum.ORDERED.equals(l_orderActionRow.getOrderStatus()))
            {
                l_ipoOrderActionUnit.ipoProcessingType = WEB3ProcessTypeDef.ORDER;
            }

            //�\������
            l_ipoOrderActionUnit.demandQuantity = 
                WEB3StringTypeUtility.formatNumber(l_orderAction.getQuantity());
                
            //�\�����i�敪
            if(l_orderActionRow.getLimitPrice() == 0)
            {
                l_ipoOrderActionUnit.demandPriceDiv = WEB3OrderPriceDivDef.MARKET_PRICE;
            }
            else
            {
                l_ipoOrderActionUnit.demandPriceDiv = WEB3OrderPriceDivDef.LIMIT_PRICE;
            }
            
            //�\�����i
            l_ipoOrderActionUnit.demandPrice = 
                WEB3StringTypeUtility.formatNumber(l_orderActionRow.getLimitPrice());
                
            //IPO��t���ʋ敪
            if(BooleanEnum.TRUE.equals(l_orderActionRow.getDeleteFlag()))
            {
                l_ipoOrderActionUnit.ipoAcceptedResultDiv = WEB3AcceptedResultDivDef.INVALID;
            }
            else if(l_orderActionRow.getDeleteFlag().equals(BooleanEnum.FALSE))
            {
                l_ipoOrderActionUnit.ipoAcceptedResultDiv = WEB3AcceptedResultDivDef.VALID;
                l_intLatestIndex = i;
            }
            
            //�ŐV�L�������t���O
            l_ipoOrderActionUnit.newHistoryFlag = false;
            
            //�\���p�P�ʋ敪
            l_ipoOrderActionUnit.displayUnitDiv = l_ipoProductRow.getIpoUnitDiv(); 
            
            //�������敪
            l_ipoOrderActionUnit.temporaryConditionDiv = l_ipoProductRow.getProvisionalValueDiv();
            
            //1.4.3 add
            l_arrayList.add(l_ipoOrderActionUnit);
        }

        //1.5.toArray
        WEB3IPODemandHistoryUnit[] l_ipoOrderActionUnits = new WEB3IPODemandHistoryUnit[l_intCount];
        l_arrayList.toArray(l_ipoOrderActionUnits);
        
        //1.6.�ŐV�L���t���O�Z�b�g
        if(l_intLatestIndex != -1)
        {
            l_ipoOrderActionUnits[l_intLatestIndex].newHistoryFlag = true;
        }
        
        //1.7.IPO�\�����𖾍�[]
                
        log.exiting(STR_METHOD_NAME);
        return l_ipoOrderActionUnits;
    }
}
@
