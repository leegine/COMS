head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.31.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushEquityMarginChangeCancelUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V��������������ʒm�T�[�r�X����(WEB3RichPushEquityMarginChangeCancelUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/15 ���r(���u) �V�K�쐬
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcEquityMarginChangeCancelPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import webbroker3.rcp.data.RichPushEquityChangeCancelRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ���b�`�N���C�A���g�f�[�^�v�b�V��������������ʒm�T�[�r�X����
 * @@author ���r(���u)
 * @@version 1.0
 */
public class WEB3RichPushEquityMarginChangeCancelUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushEquityMarginChangeCancelUnitService
{
    /** ���t�t�H�[�}�b�g�̒萔��`:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushEquityMarginChangeCancelUnitServiceImpl.class);

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
        List l_equityMarginChangeCancelList
            = l_perService.getEquityChangeCancelRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

		// 1.2 ���b�`�N���C�A���g�f�[�^�v�b�V��
		if (l_equityMarginChangeCancelList.size() > 0)
		{

			//�R���e�N�X�g�֕ۑ�
			saveToContext(l_equityMarginChangeCancelList);

		}

    }

    /**
     * ���b�`�N���C�A���g�v�b�V���f�[�^��XML�֕ϊ�
     *
     * @@param l_dataRows List
     * @@param l_historyTbl Map
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
            //������������ʒm�̏ꍇ
            if (l_row instanceof RichPushEquityChangeCancelRow)
            {
                RichPushEquityChangeCancelRow l_changeCancelRow = (
                    RichPushEquityChangeCancelRow)
                    l_row;
                //������������ʒm
                if (WEB3RichPushDataTypeDef.EQTYPE_CHANGE_CANCEL.equals(
                    l_changeCancelRow.getType()))
                {
                    WEB3RcEquityMarginChangeCancelPush l_pushObj = this.convert( (
                        RichPushEquityChangeCancelRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //���Ή�������������ʒm�f�[�^
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" +
                              l_changeCancelRow.getType());
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
     * @@param l_dataRow RichPushEquityChangeCancelRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginChangeCancelPush
     */
    private WEB3RcEquityMarginChangeCancelPush convert(
        RichPushEquityChangeCancelRow l_dataRow)
    {
        WEB3RcEquityMarginChangeCancelPush l_rcEquityMarginChangeCancelPush = new
            WEB3RcEquityMarginChangeCancelPush();

        //�f�[�^�R�[�h
        l_rcEquityMarginChangeCancelPush.setDcd(l_dataRow.getRequestCode());
        //��ЃR�[�h
        l_rcEquityMarginChangeCancelPush.setAa_icd(l_dataRow.getInstitutionCode());
        //���X�R�[�h
        l_rcEquityMarginChangeCancelPush.setAa_bcd(l_dataRow.getBranchCode());
        //�ڋq�R�[�h
        l_rcEquityMarginChangeCancelPush.setAa_accd(l_dataRow.getAccountCode());
        //�I�y���[�^�[�R�[�h
        l_rcEquityMarginChangeCancelPush.setOptcd(l_dataRow.getTraderCode());
        //����ID
        l_rcEquityMarginChangeCancelPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //�����R�[�h
        l_rcEquityMarginChangeCancelPush.setPdc(l_dataRow.getProductCode());
        //�s��R�[�h
        l_rcEquityMarginChangeCancelPush.setMcd(l_dataRow.getMarketCode());
        //�����㐔��
        l_rcEquityMarginChangeCancelPush.setAcqt(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getModifiedQuantity()));
        //������w�l
        l_rcEquityMarginChangeCancelPush.setAclpr(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getModifiedLimitPrice()));
        //�����㎷�s�����iSONAR�j
        l_rcEquityMarginChangeCancelPush.setAcectps(l_dataRow.getModifiedExecutionType());
        //������l�i�����iSONAR�j
        l_rcEquityMarginChangeCancelPush.setAcprctps(l_dataRow.
            getModifiedPriceConditionType());
        //�������ʃR�[�h
        l_rcEquityMarginChangeCancelPush.setCrcd(l_dataRow.getModifiedResult());
        //��������ʒm�敪
        l_rcEquityMarginChangeCancelPush.setCcpdv(l_dataRow.getCanmodReceiptType());
        //�����敪
        l_rcEquityMarginChangeCancelPush.setTrstp(l_dataRow.getStatus());
        //�쐬���t
        l_rcEquityMarginChangeCancelPush.setCrdt(WEB3DateUtility.formatDate(
            l_dataRow.getCreatedTimestamp(), YYYYMMDD));
        //�X�V���t
        l_rcEquityMarginChangeCancelPush.setUpdt(WEB3DateUtility.formatDate(
            l_dataRow.getLastUpdatedTimestamp(), YYYYMMDD));
        //�V���A���ԍ�
        l_rcEquityMarginChangeCancelPush.setSerlnum(String.valueOf(l_dataRow.
            getSerlnum()));

        return l_rcEquityMarginChangeCancelPush;
    }

}
@
