head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.31.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushEquityMarginLapseUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : QTP���b�`�N���C�A���g�v�b�V�������M�p�����ʒm�T�[�r�X����(WEB3QtpRichPushEquityMarginLapseUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2009/06/02 ��(FTL) �V�K�쐬
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.WEB3QtpRichPushUtil;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager;
import webbroker3.util.WEB3LogUtility;

/**
 * QTP���b�`�N���C�A���g�f�[�^�v�b�V�����������ʒm�T�[�r�X����<br/>
 * 
 * @@author ��(FTL)
 * @@version 1.0
 */
public class WEB3QtpRichPushEquityMarginLapseUnitServiceImpl
    extends WEB3QtpRichPushUnitServiceImpl
    implements WEB3QtpRichPushEquityMarginLapseUnitService
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushEquityMarginLapseUnitServiceImpl.class);

    private final WEB3QtpRichPushUtil web3QtpRichPushUtil = new WEB3QtpRichPushUtil();

    /**
     * QTP���b�`�N���C�A���g�f�[�^�v�b�V��<br/>
     * �����M�p�����ʒm�f�[�^<br/>
     * 
     * @@param l_lngFromAccountId
     *            �����ڋq�h�c
     * @@param l_lngToAccountId
     *            ����ڋq�h�c
     */
    public void push( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "push( long l_lngFromAccountId, long l_lngToAccountId )";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTP���b�`�N���C�A���g�v�b�V���f�[�^�ǂݍ���
        WEB3QtpRichPushPersistentDataManager l_perService =
            (WEB3QtpRichPushPersistentDataManager) Services.getService(WEB3QtpRichPushPersistentDataManager.class);
        List l_equityMarginLapseList =
            l_perService.getQtpEquityLapseRichPushData(l_lngFromAccountId, l_lngToAccountId);

        // 1.2 QTP���b�`�N���C�A���g�f�[�^�v�b�V��
        if (l_equityMarginLapseList.size() > 0)
        {
            // �R���e�N�X�g�֕ۑ�
            saveToContext(l_equityMarginLapseList);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * QTP���b�`�N���C�A���g�v�b�V���f�[�^��XML�ϊ��p�I�u�W�F�N�g�֕ϊ�<br/>
     * �����M�p�����ʒm�f�[�^<br/>
     * 
     * @@param l_dataRows
     *            ���[�f�[�^
     * @@return WEB3QtpExcutionInformUnit
     */
    public WEB3QtpExcutionInformUnit createRichPushXmlMessage( Row l_dataRow )
    {
        final String STR_METHOD_NAME = "createRichPushXmlMessage()";
        log.entering(STR_METHOD_NAME);

        log.debug("RichPushRow:" + l_dataRow);

        WEB3QtpExcutionInformUnit l_pushObj =
            web3QtpRichPushUtil.createWEB3QtpExcutionInformUnit(l_dataRow);

        if (l_pushObj == null)
        {
            // ���Ή��f�[�^
            log.error("unsupported RowType:" + (l_dataRow == null ? "null" : l_dataRow.getRowType().toString()));
        }
        else
        {
            log.debug("Push Object created:" + l_pushObj);
        }

        boolean l_isValid = isCommonValid(l_pushObj);
        log.exiting(STR_METHOD_NAME);
        return (l_isValid)?l_pushObj:null;
    }

}
@
