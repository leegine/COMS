head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushFuOpContUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V���敨OP�o���ʒm�T�[�r�X����(WEB3RichPushFuOpContUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/23 ���r(���u) �V�K�쐬
*/

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcFuOpContPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.rcp.data.RichPushIfoContRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ���b�`�N���C�A���g�v�b�V���敨OP�o���ʒm�T�[�r�X����
 * @@author ���r(���u)
 * @@version 1.0
 */
public class WEB3RichPushFuOpContUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushFuOpContUnitService
{
    /** ���t�t�H�[�}�b�g�̒萔��`:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushFuOpContUnitServiceImpl.class);

    /**
     * ���b�`�N���C�A���g�f�[�^�v�b�V��
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     */
    public void push(long l_lngFromAccountId, long l_lngToAccountId) throws
        DataQueryException, DataNetworkException
    {

        // 1.1. ���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        WEB3RichPushPersistentDataManager l_perService =
            (WEB3RichPushPersistentDataManager) Services.getService(
            WEB3RichPushPersistentDataManager.class);
        List l_ifoContList
            = l_perService.getIfoContRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 ���b�`�N���C�A���g�f�[�^�v�b�V��
        if (l_ifoContList.size() > 0)
        {

            //�R���e�N�X�g�֕ۑ�
            saveToContext(l_ifoContList);

        }

    }

    /**
     * ���b�`�N���C�A���g�v�b�V���f�[�^��XML�֕ϊ�
     *
     * @@param l_dataRows List
     * @@return String
     */
    public String createRichPushXmlMessage(
        List l_dataRows)
    {
        final String STR_METHOD_NAME = "createRichPushXmlMessage()";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_strbuff = new StringBuffer();

        log.debug("���b�`�N���C�A���g�v�b�V���f�[�^XML�֕ϊ��F���� = " + l_dataRows.size());
        for (int i = 0; i < l_dataRows.size(); i++)
        {
            log.debug("���b�`�N���C�A���g�v�b�V���f�[�^XML�֕ϊ��FLoop���� i = " + i);
            Row l_row = (Row) l_dataRows.get(i);
            log.debug("RichPushRow:" + l_row);
            //�敨OP�o���ʒm�̏ꍇ
            if (l_row instanceof RichPushIfoContRow)
            {
                RichPushIfoContRow l_contRow =
                    (RichPushIfoContRow) l_row;
                //�敨OP�o���ʒm
                if (WEB3RichPushDataTypeDef.IFO_CONT.equals(
                    l_contRow.getType()))
                {
                    WEB3RcFuOpContPush l_pushObj = this.convert( (
                        RichPushIfoContRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //���Ή��敨OP�o���ʒm�f�[�^
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" + l_contRow.getType());
                }
            }
            //���Ή��f�[�^
            else
            {
                log.error("unsupported RowType:" + l_row.getRowType());
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strbuff.toString();

    }

    /**
     * �v�b�V���f�[�^�I�u�W�F�N�g�ϊ�
     *
     * @@param l_dataRow RichPushIfoContRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcFuOpContPush
     */
    private WEB3RcFuOpContPush convert(
        RichPushIfoContRow l_dataRow)
    {
        WEB3RcFuOpContPush l_rcFuOpContPush = new
            WEB3RcFuOpContPush();

        //�f�[�^�R�[�h
        l_rcFuOpContPush.setDcd(l_dataRow.getRequestCode());
        //��ЃR�[�h
        l_rcFuOpContPush.setAa_icd(l_dataRow.getInstitutionCode());
        //���X�R�[�h
        l_rcFuOpContPush.setAa_bcd(l_dataRow.getBranchCode());
        //�ڋq�R�[�h
        l_rcFuOpContPush.setAa_accd(l_dataRow.getAccountCode());
        //�I�y���[�^�[�R�[�h
        l_rcFuOpContPush.setOptcd(l_dataRow.getTraderCode());
        //����ID
        l_rcFuOpContPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //�����R�[�h
        l_rcFuOpContPush.setPdc(l_dataRow.getProductCode());
        //���敪
        OrderTypeEnum l_orderType = l_dataRow.getOrderType() ;
        String l_strContp = null;
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
            OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) ||
            OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType) ||
            OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
                //605�FOP�V�K���������@@�@@�@@�@@�@@�@@�A601�F�敨�V�K��������
                //608�FOP�������ԍϒ����i���ԍρj�A604�F�敨�������ԍϒ����i���ԍρj
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
                //606�FOP�V�K���������@@�@@�@@�@@�@@�@@�A602�F�敨�V�K��������
                //607�FOP�������ԍϒ����i���ԍρj�A603�F�敨�������ԍϒ����i���ԍρj
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_SELL;
        }
        l_rcFuOpContPush.setContp(l_strContp);
        //����s��
        l_rcFuOpContPush.setMcd(l_dataRow.getMarketCode());
        //�w����ʁi�����Y�����R�[�h�j
        l_rcFuOpContPush.setTpd(l_dataRow.getUnderlyingProductCode());
        //����
        l_rcFuOpContPush.setDm(l_dataRow.getMonthOfDelivery());
        //�I�v�V�������i�敪
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpContPush.setPdtp(WEB3IfoProductTypeDef.CALL_OPTIONS);
        }
        else if ( IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpContPush.setPdtp(WEB3IfoProductTypeDef.PUT_OPTIONS);
        }
        //�s�g���i
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_dataRow.getDerivativeType()) ||
            IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpContPush.setSpr(WEB3StringTypeUtility.formatNumber(
                l_dataRow.getStrikePrice()));
        }
        //��萔��
        l_rcFuOpContPush.setEqt(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getExecQuantity()));
        //���P��
        l_rcFuOpContPush.setEpr(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getExecPrice()));
        //������
        l_rcFuOpContPush.setExet(WEB3DateUtility.formatDate(l_dataRow.
            getExecTimestamp(), "yyyyMMddHHmmss"));
        //�o���ʒm�敪
        l_rcFuOpContPush.setCpdv(l_dataRow.getDealedType());
        //�����敪
        l_rcFuOpContPush.setTrstp(l_dataRow.getStatus());
        //�쐬���t
        l_rcFuOpContPush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //�X�V���t
        l_rcFuOpContPush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));
        //�V���A���ԍ�
        l_rcFuOpContPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));

        return l_rcFuOpContPush;
    }


}
@
