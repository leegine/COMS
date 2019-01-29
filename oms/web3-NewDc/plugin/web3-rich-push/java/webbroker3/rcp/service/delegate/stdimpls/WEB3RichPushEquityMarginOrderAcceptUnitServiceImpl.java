head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V������������t�ʒm�T�[�r�X����(WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcEquityMarginOrderAcceptPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.data.RichPushEquityOrderAcceptRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���b�`�N���C�A���g�f�[�^�v�b�V������������t�ʒm�T�[�r�X�T�[�r�X����
 * @@author ��
 * @@version 1.0
 */
public class WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushEquityMarginOrderAcceptUnitService
{
    /** ���t�t�H�[�}�b�g�̒萔��`:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl.class);

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
        List l_equityOrderAcceptList
            = l_perService.getEquityOrderAcceptRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 ���b�`�N���C�A���g�f�[�^�v�b�V��
        if (l_equityOrderAcceptList.size() > 0)
        {

            //�R���e�N�X�g�֕ۑ�
            saveToContext(l_equityOrderAcceptList);
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
            //log.debug("RichPushRow:" + l_row);
            //����������t�ʒm�̏ꍇ
            if (l_row instanceof RichPushEquityOrderAcceptRow)
            {
                RichPushEquityOrderAcceptRow l_acceptRow = (RichPushEquityOrderAcceptRow)
                    l_row;
                //����������t�ʒm
                if (WEB3RichPushDataTypeDef.EQUITY_ORDER_ACCEPT.equals(l_acceptRow.
                    getType()))
                {
                    WEB3RcEquityMarginOrderAcceptPush l_pushObj = this.convert( (
                        RichPushEquityOrderAcceptRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //���Ή�������t�ʒm�f�[�^
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" + l_acceptRow.getType());
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
     * @@param l_dataRow RichPushEquityOrderAcceptRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginOrderAcceptPush
     */
    private WEB3RcEquityMarginOrderAcceptPush convert(
        RichPushEquityOrderAcceptRow l_dataRow)
    {
        WEB3RcEquityMarginOrderAcceptPush l_rcEquityMarginOrderAcceptPush = new
            WEB3RcEquityMarginOrderAcceptPush();

        //�V���A�ԍ�
        l_rcEquityMarginOrderAcceptPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));
        //�f�[�^�R�[�h
        l_rcEquityMarginOrderAcceptPush.setDcd(l_dataRow.getRequestCode());
        //��ЃR�[�h
        l_rcEquityMarginOrderAcceptPush.setAa_icd(l_dataRow.getInstitutionCode());
        //���X�R�[�h
        l_rcEquityMarginOrderAcceptPush.setAa_bcd(l_dataRow.getBranchCode());
        //�ڋq�R�[�h
        l_rcEquityMarginOrderAcceptPush.setAa_accd(l_dataRow.getAccountCode());
        //�I�y���[�^�[�R�[�h
        l_rcEquityMarginOrderAcceptPush.setOptcd(l_dataRow.getTraderCode());
        //����ID
        l_rcEquityMarginOrderAcceptPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //������t����
        l_rcEquityMarginOrderAcceptPush.setOdacr(l_dataRow.getAcceptStatus());
        //�G���[�R�[�h
        l_rcEquityMarginOrderAcceptPush.setErrcd(l_dataRow.getErrorMessage());
        //�����o�H
        l_rcEquityMarginOrderAcceptPush.setOdrdv(l_dataRow.getSubmitOrderRouteDiv());
        //�����敪
        l_rcEquityMarginOrderAcceptPush.setTrstp(l_dataRow.getStatus());
        //�s��R�[�h
        l_rcEquityMarginOrderAcceptPush.setMcd(l_dataRow.getMarketCode());
        //�����R�[�h
        l_rcEquityMarginOrderAcceptPush.setPdc(l_dataRow.getProductCode());
        //�쐬���t
        l_rcEquityMarginOrderAcceptPush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //�X�V���t
        l_rcEquityMarginOrderAcceptPush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));

        return l_rcEquityMarginOrderAcceptPush;
    }

}
@
