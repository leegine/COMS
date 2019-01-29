head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.31.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushFuOpContUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QTP���b�`�N���C�A���g�v�b�V���敨OP�o���ʒm�T�[�r�X����(WEB3QtpRichPushFuOpContUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/10 ��(FLJ) �V�K�쐬
                 : 2009/06/03 ��(FTL) ���Ή�
*/

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.rcp.WEB3QtpRichPushUtil;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContRow;
import webbroker3.rcp.define.WEB3QtpRichPushIfoProductCodeDef;
import webbroker3.rcp.define.WEB3QtpRichPushQtpCommandParamDef;
import webbroker3.rcp.define.WEB3QtpRichPushTextDef;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager;
import webbroker3.util.WEB3LogUtility;

/**
 * QTP���b�`�N���C�A���g�v�b�V���敨OP�o���ʒm�T�[�r�X����
 * 
 * @@author ��(FLJ)
 * @@version 1.0
 */
public class WEB3QtpRichPushFuOpContUnitServiceImpl extends WEB3QtpRichPushUnitServiceImpl implements WEB3QtpRichPushFuOpContUnitService
{

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QtpRichPushFuOpContUnitServiceImpl.class);

    private final WEB3QtpRichPushUtil web3QtpRichPushUtil = new WEB3QtpRichPushUtil();

    /**
     * QTP���b�`�N���C�A���g�f�[�^�v�b�V��<br/>
     * �敨�n�o�����o���ʒm�f�[�^<br/>
     * 
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     */
    public void push(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "push(long l_lngFromAccountId, long l_lngToAccountId)";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTP���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        WEB3QtpRichPushPersistentDataManager l_perService = (WEB3QtpRichPushPersistentDataManager) Services
                .getService(WEB3QtpRichPushPersistentDataManager.class);
        List l_ifoContList = l_perService.getQtpIfoContRichPushData(l_lngFromAccountId, l_lngToAccountId);

        // 1.2 QTP���b�`�N���C�A���g�f�[�^�v�b�V��
        if (l_ifoContList.size() > 0)
        {
            //�R���e�N�X�g�֕ۑ�
            saveToContext(l_ifoContList);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���b�`�N���C�A���g�v�b�V���f�[�^��XML�֕ϊ�
     * 
     * @@param l_dataRows
     *            List
     * @@return String
     */
    public WEB3QtpExcutionInformUnit createRichPushXmlMessage(Row l_dataRow)
    {
        final String STR_METHOD_NAME = "createRichPushXmlMessage()";
        log.entering(STR_METHOD_NAME);

        WEB3QtpExcutionInformUnit l_pushObj = null;

        log.debug("RichPushRow:" + l_dataRow);
        //�敨OP�o���ʒm�̏ꍇ
        if (l_dataRow instanceof QtpRichPushIfoContRow)
        {
            QtpRichPushIfoContRow l_contRow = (QtpRichPushIfoContRow) l_dataRow;
            //�敨OP�o���ʒm
            if (WEB3RichPushDataTypeDef.IFO_CONT.equals(l_contRow.getType()))
            {
                
                l_pushObj =
                    web3QtpRichPushUtil.createWEB3QtpExcutionInformUnit(l_dataRow);

                if (l_pushObj != null)
                {
                    l_pushObj.setUrl(getUrlFromOrderType(l_contRow.getOrderType()));
                    l_pushObj.setTitle(WEB3QtpRichPushTextDef.TITLE_EXECUTION);

                    String l_productCode = getQTPProductCode(l_contRow);

//                    l_pushObj.setQcode(l_productCode + "/" + getQtpMarketCodeFromMarket(l_contRow.getMarketCode()));
//                    l_pushObj.setQcodeName(l_contRow.getProductName() + "(" + l_productCode + "/"
//                        + getQtpMarketNameFromMarket(l_contRow.getMarketCode()) + ")");
                    //���敨�I�v�V����������R�[�h�̎w�肵�܂���
                    l_pushObj.setUrlParams("");
                    l_pushObj.setQcodeParams(WEB3QtpRichPushQtpCommandParamDef.QUOTE_PREFIX + l_productCode + WEB3QtpRichPushQtpCommandParamDef.IFO_DRAG);
                    l_pushObj.setQcodeName(l_contRow.getProductName() + "(" + l_productCode + ")");
                    //���敨�I�v�V����������R�[�h�̎w�肵�܂���
                    l_pushObj.setOrderTypeName(getOrderTypeNameFromOrderType(l_contRow.getOrderType()) + WEB3QtpRichPushTextDef.PRICE_TITLE);
                    l_pushObj.setPrc(formatNumber(l_contRow.getExecPrice()) + WEB3QtpRichPushTextDef.PRICE_UNIT);
                    l_pushObj.setQuantityText(WEB3QtpRichPushTextDef.QUANTITY_TITLE);
                    l_pushObj.setVol(formatNumber(l_contRow.getExecQuantity()) + WEB3QtpRichPushTextDef.QUANTITY_UNIT_IFO);
                    l_pushObj.setLnkText(WEB3QtpRichPushTextDef.LINK_TITLE);
                }
            }
            //���Ή��敨OP�o���ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_contRow.getType());
            }
        }

        if (l_pushObj == null)
        {
            // ���Ή��f�[�^
            log.error("unsupported RowType:" + (l_dataRow == null ? "null" : l_dataRow.getRowType().toString()));
        }
        else
        {
            log.debug("Push Object created:" + l_pushObj);
        }

        boolean l_isValid = isAllValid(l_pushObj);
        log.exiting(STR_METHOD_NAME);
        return (l_isValid)?l_pushObj:null;
    }
    
    /**
     * QTP�����R�[�h���擾
     * 
     * @@param l_row
     * @@return
     */
    private String getQTPProductCode(QtpRichPushIfoContRow l_row)
    {
        final String STR_METHOD_NAME = "getQTPProductCode(QtpRichPushIfoContRow)";
        log.entering(STR_METHOD_NAME);
        String l_result = "";
        String l_pcd = l_row.getUnderlyingProductCode();  //�����Y�����R�[�h
        String l_mod = l_row.getMonthOfDelivery();  //����
        OrderTypeEnum l_oType = l_row.getOrderType(); //�������

        if(l_mod !=null && l_mod.length() == 6)
        {
            l_mod = l_mod.substring(2,6);
            
            //�敨�̏ꍇ
            if (OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN == l_oType || OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN == l_oType
                    || OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE == l_oType || OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE == l_oType)
            {
                if (WEB3QtpRichPushIfoProductCodeDef.WEB3_NIKKEI_225.equals(l_pcd))
                {
                    l_result = WEB3QtpRichPushIfoProductCodeDef.QTP_NIKKEI_225 + "." + l_mod;
                }
                else if (WEB3QtpRichPushIfoProductCodeDef.WEB3_NIKKEI_MINI_225.equals(l_pcd))
                {
                    l_result = WEB3QtpRichPushIfoProductCodeDef.QTP_NIKKEI_MINI_225 + "." + l_mod;
                }
                else if (WEB3QtpRichPushIfoProductCodeDef.WEB3_NIKKEI_300.equals(l_pcd))
                {
                    l_result = WEB3QtpRichPushIfoProductCodeDef.QTP_NIKKEI_300 + "." + l_mod;
                }
                else if (WEB3QtpRichPushIfoProductCodeDef.WEB3_TOPIX.equals(l_pcd))
                {
                    l_result = WEB3QtpRichPushIfoProductCodeDef.QTP_TOPIX + "." + l_mod;
                }
            }
            //�I�v�V�����̏ꍇ
            else if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN == l_oType || OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN == l_oType
                    || OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE == l_oType || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE == l_oType)
            {
                if (WEB3QtpRichPushIfoProductCodeDef.WEB3_NIKKEI_225.equals(l_pcd))
                {
                    String l_sPrice = String.valueOf(l_row.getStrikePrice());
                    String l_z = null;
                    IfoDerivativeTypeEnum l_derivativeType = l_row.getDerivativeType();
                    if(IfoDerivativeTypeEnum.CALL_OPTIONS == l_derivativeType)
                    {
                        l_z = WEB3QtpRichPushIfoProductCodeDef.QTP_OPTION_DIV_CALL;
                    }
                    else if(IfoDerivativeTypeEnum.PUT_OPTIONS == l_derivativeType)
                    {
                        l_z = WEB3QtpRichPushIfoProductCodeDef.QTP_OPTION_DIV_PUT;                       
                    }
                    
                    if(l_sPrice.length() >3 && l_z != null)
                    {
                        l_sPrice = l_sPrice.substring(0,3);
                        
                        l_result = new StringBuffer(WEB3QtpRichPushIfoProductCodeDef.QTP_NIKKEI_225).append("#").append(l_sPrice).append(".").append(l_mod).append("*").append(l_z).toString();
                    }
                }
            }
        }
        
        if(l_result.length() < 1 )
        {
            log.warn("Can't create QTP Product Code from QtpRichPushIfoContRow."+l_row);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

}@
