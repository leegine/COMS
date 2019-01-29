head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushSwapOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�@@���b�`�N���C�A���g�v�b�V�������������n��t�T�[�r�X����(WEB3RichPushSwapOrderAcceptUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 ��(FLJ) �V�K�쐬
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcSwapOrderAcceptPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.data.RichPushSwapOrderAcceptRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * ���b�`�N���C�A���g�v�b�V�������������n��t�T�[�r�X����
 * @@author ��
 * @@version 1.0
 */
public class WEB3RichPushSwapOrderAcceptUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushSwapOrderAcceptUnitService
{
    /** ���t�t�H�[�}�b�g�̒萔��`:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushSwapOrderAcceptUnitServiceImpl.class);

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
        List l_equitySwapOrderAcceptList
            = l_perService.getSwapOrderAcceptRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 ���b�`�N���C�A���g�f�[�^�v�b�V��
        if (l_equitySwapOrderAcceptList.size() > 0)
        {

            //�R���e�N�X�g�֕ۑ�
            saveToContext(l_equitySwapOrderAcceptList);
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
            //�����M�p�������n��t�̏ꍇ
            if (l_row instanceof RichPushSwapOrderAcceptRow)
            {
                RichPushSwapOrderAcceptRow l_AcceptRow = (RichPushSwapOrderAcceptRow)
                    l_row;
                //����������t(�M�p�������n�ȊO)
                if (WEB3RichPushDataTypeDef.SWAP_ORDER_ACCEPT.equals(l_AcceptRow.
                    getType()))
                {
                    WEB3RcSwapOrderAcceptPush l_pushObj = this.convert( (
                        RichPushSwapOrderAcceptRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //���Ή�������t�f�[�^
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" + l_AcceptRow.getType());
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
     * @@param l_dataRow RichPushSwapOrderAcceptRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginOrderAcceptPush
     */
    private WEB3RcSwapOrderAcceptPush convert(
        RichPushSwapOrderAcceptRow l_dataRow)
    {
        WEB3RcSwapOrderAcceptPush l_rcSwapOrderAcceptPush = new
            WEB3RcSwapOrderAcceptPush();

        //�V���A�ԍ�
        l_rcSwapOrderAcceptPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));
        //�f�[�^�R�[�h
        l_rcSwapOrderAcceptPush.setDcd(l_dataRow.getRequestCode());
        //��ЃR�[�h
        l_rcSwapOrderAcceptPush.setAa_icd(l_dataRow.getInstitutionCode());
        //���X�R�[�h
        l_rcSwapOrderAcceptPush.setAa_bcd(l_dataRow.getBranchCode());
        //�ڋq�R�[�h
        l_rcSwapOrderAcceptPush.setAa_accd(l_dataRow.getAccountCode());
        //�I�y���[�^�[�R�[�h
        l_rcSwapOrderAcceptPush.setOptcd(l_dataRow.getTraderCode());
        //����ID
        l_rcSwapOrderAcceptPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //������t����
        l_rcSwapOrderAcceptPush.setOdacr(l_dataRow.getAcceptStatus());
        //�G���[�R�[�h
        l_rcSwapOrderAcceptPush.setErrcd(l_dataRow.getErrorMessage());

        //�����o�H
        //l_rcSwapOrderAcceptPush.setOdrdv(l_dataRow.getSubmitOrderRouteDiv());

        //�s��R�[�h
        l_rcSwapOrderAcceptPush.setMcd(l_dataRow.getMarketCode());
        //�����R�[�h
        l_rcSwapOrderAcceptPush.setPdc(l_dataRow.getProductCode());

        //�����敪
        l_rcSwapOrderAcceptPush.setTrstp(l_dataRow.getStatus());
        //�쐬���t
        l_rcSwapOrderAcceptPush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //�X�V���t
        l_rcSwapOrderAcceptPush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));

        return l_rcSwapOrderAcceptPush;
    }

}
@
