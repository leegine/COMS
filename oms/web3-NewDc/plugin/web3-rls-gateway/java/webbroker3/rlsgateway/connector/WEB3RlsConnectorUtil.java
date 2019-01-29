head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���G���W���ւ̒������M���[�e�B���e�B(WEB3RlsConnectorUtil.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/15 �V�� �h�O(FLJ) �V�K�쐬
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.fin.intellioms.enums.CondOrderType;
import com.fitechlabs.fin.intellioms.enums.OrderType;
import com.fitechlabs.fin.intellioms.enums.Side;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.rlsgateway.define.WEB3OrderTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 *
 * ���[���G���W���ւ̒������M���[�e�B���e�B
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3RlsConnectorUtil
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsConnectorUtil.class);
    
    /**
     * to����Enum <BR>
     * <BR>
     * �����ɉ����Ĕ���Enum�ɕϊ�����B <BR>
     * <BR>
     * @@param int - (�ϊ����̒l)
     * @@return Side
     */
    public static Side toSide(int intValue)
    {
        switch (intValue)
        {
            case WEB3OrderTypeDef.EQUITY_BUY :
            case WEB3OrderTypeDef.MARGIN_LONG :
            case WEB3OrderTypeDef.CLOSE_MARGIN_SHORT :
            case WEB3OrderTypeDef.SWAP_MARGIN_LONG :
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_CLOSE :
                return Side.BUY;

            case WEB3OrderTypeDef.EQUITY_SELL :
            case WEB3OrderTypeDef.MARGIN_SHORT :
            case WEB3OrderTypeDef.CLOSE_MARGIN_LONG :
            case WEB3OrderTypeDef.SWAP_MARGIN_SHORT :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_CLOSE :
                return Side.SELL;

            
            case WEB3OrderTypeDef.UNDEFINED :
            default :
                throw new IllegalArgumentException("toSide: no corresponding number. [intValue=" + intValue + "]");
        }
    }
    
    /**
     * to�������Enum <BR>
     * <BR>
     * �����ɉ����Ē������Enum�ɕϊ�����B <BR>
     * <BR>
     * @@param int - (�ϊ����̒l)
     * @@return OrderType
     */
    public static  OrderType toOrderType(int intValue)
    {
        switch (intValue)
        {
            case WEB3OrderTypeDef.EQUITY_BUY :
            case WEB3OrderTypeDef.EQUITY_SELL :
                return OrderType.EQUITY_CASH;
            
            case WEB3OrderTypeDef.MARGIN_LONG :
            case WEB3OrderTypeDef.MARGIN_SHORT :
            case WEB3OrderTypeDef.CLOSE_MARGIN_LONG :
            case WEB3OrderTypeDef.CLOSE_MARGIN_SHORT :
            case WEB3OrderTypeDef.SWAP_MARGIN_LONG :
            case WEB3OrderTypeDef.SWAP_MARGIN_SHORT :
                return OrderType.EQUITY_MARGIN;
                
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_FUTURES_BUY_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_FUTURES_SELL_TO_CLOSE :
                return OrderType.INDEX_FUTURE;
            
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_BUY_TO_CLOSE :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_OPEN :
            case WEB3OrderTypeDef.IDX_OPTIONS_SELL_TO_CLOSE :
                return OrderType.INDEX_OPTION;

            
            case WEB3OrderTypeDef.UNDEFINED :
            default :
                throw new IllegalArgumentException("toOrderType: no corresponding number. [intValue=" + intValue + "]");
        }
    }
    
    /**
     * to���[���G���W������ID <BR>
     * <BR>
     * �����̖���ID�����[���G���W���Ǘ��̖���ID�ɕϊ�����B <BR>
     * <BR>
     * @@param long - (����ID)
     * @@return long
     */    
    public static long toRlsProductId(long l_productId)
    {
        String l_strRlsProudctId = String.valueOf(l_productId).substring(WEB3RlsConnectorConstants.INSTITUTION_CODE_SIZE);
        return new Long(l_strRlsProudctId).longValue();
    }
    
    /**
     * to���[���G���W���s��ID <BR>
     * <BR>
     * �����̎s��ID�����[���G���W���Ǘ��̎s��ID�ɕϊ�����B <BR>
     * <BR>
     * @@param long - (�s��ID)
     * @@return long
     */        
    public static long toRlsMarketId(long l_marketId)
    {
        String l_strRlsMarketId = String.valueOf(l_marketId).substring(WEB3RlsConnectorConstants.INSTITUTION_CODE_SIZE);
        return new Long(l_strRlsMarketId).longValue();
    }
    
    /**
     * to�����t������ID <BR>
     * <BR>
     * �����ɉ����ď����t������ID�ɕϊ�����B <BR>
     * <BR>
     * @@param ProductTypeEnum - (�����^�C�v)
     * @@param CondOrderType - (�����t�������^�C�v)
     * @@param long - (����ID)
     * @@return long
     */        
    public static long toRlsCondOrderId(ProductTypeEnum l_productType, CondOrderType l_condOrderType, long l_lngOrderId)
    {
        StringBuffer l_sb = new StringBuffer(WEB3RlsConnectorConstants.RLS_COND_ORDER_SIZE); 
        String l_strOderId = String.valueOf(l_lngOrderId);
        int l_intOrderLength = l_strOderId.length();
        
        appendProductType(l_sb, l_productType);

        appendCondOrderType(l_sb, l_condOrderType);
        
        int l_sbLength = l_sb.length();
        
        appendZero(l_sb, WEB3RlsConnectorConstants.RLS_COND_ORDER_SIZE - l_sbLength - l_intOrderLength);
        
        l_sb.append(l_lngOrderId);
        
        return Long.parseLong(l_sb.toString()); 
    }
    
    /**
     * 0�␳ <BR>
     * <BR>
     * �����̃o�b�t�@@������Ɉ����Ŏw�肵������0��␳����B <BR>
     * <BR>
     * @@param StringBuffer - (�o�b�t�@@������)
     * @@param long - (0��ǉ����鐔)
     */        
    private static void appendZero(StringBuffer l_sb, int length)
    {
        for (int i = 0; i < length; i++)
        {
            l_sb.append(0);
        }
    }
    
    /**
     * �����^�C�v�𕶎���ϊ��␳ <BR>
     * <BR>
     * �����̃o�b�t�@@������Ɉ����Ŏw�肵�������^�C�v�̐��l�\���ɕϊ����ĕ␳����B <BR>
     * <BR>
     * @@param StringBuffer - (�o�b�t�@@������)
     * @@param ProductTypeEnum - (�����^�C�v)
     */        
    private static void appendProductType(StringBuffer l_sb, ProductTypeEnum l_productType)
    {
        if(ProductTypeEnum.EQUITY.equals(l_productType))
        {
            l_sb.append(l_productType.intValue());
        }
        else if(ProductTypeEnum.IFO.equals(l_productType))
        {
            l_sb.append(l_productType.intValue());
        }
        else
        {
            throw new IllegalArgumentException("No implementation. [ProductTypeEnum=" + l_productType + "]");            
        }
    }
    
    /**
     * �����t�������^�C�v�𕶎���ϊ��␳ <BR>
     * <BR>
     * �����̃o�b�t�@@������Ɉ����Ŏw�肵�������t�������^�C�v�̐��l�\���ɕϊ����ĕ␳����B <BR>
     * <BR>
     * @@param StringBuffer - (�o�b�t�@@������)
     * @@param CondOrderType - (�����t�������^�C�v)
     */        
    private static void appendCondOrderType(StringBuffer l_sb, CondOrderType l_condOrderType)
    {
        if(CondOrderType.PRICE.equals(l_condOrderType))
        {
            l_sb.append(l_condOrderType.toValue());
        }
        else if(CondOrderType.SIMPLE.equals(l_condOrderType))
        {
            l_sb.append(l_condOrderType.toValue());
        }
        else
        {
            throw new IllegalArgumentException("No implementation. [CondOrderType=" + l_condOrderType + "]");            
        }
    }
    

    /**
     * long�^�̃v���p�e�B���擾����B
     *
     * @@param key �v���p�e�B�E�L�[
     * @@param def �f�t�H���g�l
     * @@return �w�肳�ꂽ�v���p�e�B�̒l
     */
    public static long getProperty(String l_strKey, long l_lngDefaultValue)
    {
        long l_lngReturnValue = l_lngDefaultValue;
        String l_strValue = getProperty(l_strKey);
        if (l_strValue != null)
        {
            try
            {
                l_lngReturnValue = Long.parseLong(l_strValue);
            } catch (NumberFormatException l_nfe)
            {
                log.error("Illegal property name=" + l_strKey +", value=" + l_strValue);
            }
        }
        return l_lngReturnValue;
    }
    
    
    protected static String getProperty(String l_strKey)
    {
        return GtlUtils.getTradingSystem().getPreference(l_strKey);
    }

}
@
