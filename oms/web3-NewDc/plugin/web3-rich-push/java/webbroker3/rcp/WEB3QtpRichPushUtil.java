head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : QTP���b�`�N���C�A���g�v�b�V���c�[��: (WEB3QtpRichPushUtil.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/04 ��(FTL) �V�K�쐬
 */

package webbroker3.rcp;

import java.sql.Timestamp;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpExcutionInformUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * ���̃N���X�̃C���X�^���X�������X���b�h�g���Ă��܂�<br/>
 * final�ȊO�̑����̒ǉ��v����<br/>
 * 
 * @@author ��(FTL)
 * 
 */
public class WEB3QtpRichPushUtil
{
    /**
     * ���O�o�̓��[�e�B���e�B
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QtpRichPushUtil.class);

    /** ���t�t�H�[�}�b�g�̒萔��`:yyyyMMdd */
    private static final String DATE_FORMAT = "yyyyMMdd";

    /** �����t�H�[�}�b�g�̒萔��`�FyyyyMMddHHmmss */
    private static final String DATETIME_FORMAT = "yyyyMMddHHmmss";

    private static final String QTP_RICH_PUSH_SEND_FTAG = "qtp.rich.push.send.ftag.";

    /**
     * ���[�f�[�^���@@�ꌏ���ʒm�f�[�^�ۑ��@@�I�u�W�F�N�g�̐���<br/>
     * ����ꍇ�͋��ʍ��ڂ�ݒ肵�߂��A����ȊO�̏ꍇ�́uNULL�v�Ŗ߂�<br/>
     * ���ʍ���:<br/>
     * <ul>
     * <li>setSrlnum (�ʒm�d���ʔ�)</li>
     * <li>setSid (���M��h�c)</li>
     * <li>setTm (�d����������)</li>
     * <li>setTlgNtcNmsgFtagFatt (�t���[����[���Ή�])</li>
     * </ul>
     * 
     * @@param l_dataRow
     *            ���[�f�[�^
     * @@return
     */
    public WEB3QtpExcutionInformUnit createWEB3QtpExcutionInformUnit( Row l_row )
    {
        final String STR_METHOD_NAME = "createWEB3QtpExcutionInformUnit( Row l_row )";
        log.entering(STR_METHOD_NAME);

        if (l_row == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3QtpExcutionInformUnit l_pushObj = null;
        StringBuffer l_tlgNtcNmsgFtagFatt = new StringBuffer(1024);

        // �����M�p������t�ʒm
        if (l_row instanceof QtpRichPushEqOrderacceptRow)
        {
            QtpRichPushEqOrderacceptRow l_dataRow = (QtpRichPushEqOrderacceptRow) l_row;
            // ����������t�i�M�p�������n�ȊO�j 00
            if (WEB3RichPushDataTypeDef.EQUITY_ORDER_ACCEPT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // �s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // ������t����
                    .append("odacr=")
                    .append((null == l_dataRow.getAcceptStatus()) ? "" : l_dataRow.getAcceptStatus())
                    .append(",")
                    // �G���[�R�[�h
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")
                    // �����o�H�敪
                    .append("odrdv=")
                    .append((null == l_dataRow.getSubmitOrderRouteDiv()) ? "" : l_dataRow.getSubmitOrderRouteDiv())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή������M�p��t�ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �����M�p��������ʒm
        else if (l_row instanceof QtpRichPushEqChangecancelRow)
        {
            QtpRichPushEqChangecancelRow l_dataRow = (QtpRichPushEqChangecancelRow) l_row;
            // ������������ʒm 02
            if (WEB3RichPushDataTypeDef.EQTYPE_CHANGE_CANCEL.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // �s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // �����㐔��
                    .append("acqt=")
                    .append((l_dataRow.getModifiedQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedQuantity()))
                    .append(",")
                    // ������w�l
                    .append("aclpr=")
                    .append((l_dataRow.getModifiedLimitPriceIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedLimitPrice()))
                    .append(",")
                    // �����㎷�s����(SONAR)
                    .append("acectps=")
                    .append((null == l_dataRow.getModifiedExecutionType()) ? "" : l_dataRow.getModifiedExecutionType())
                    .append(",")
                    // ������l�i����(SONAR)
                    .append("acprctps=")
                    .append((null == l_dataRow.getModifiedPriceConditionType()) ? "" : l_dataRow.getModifiedPriceConditionType())
                    .append(",")
                    // �������ʃR�[�h
                    .append("crcd=")
                    .append((null == l_dataRow.getModifiedResult()) ? "" : l_dataRow.getModifiedResult())
                    .append(",")
                    // ��������ʒm�敪
                    .append("ccpdv=")
                    .append((null == l_dataRow.getCanmodReceiptType()) ? "" : l_dataRow.getCanmodReceiptType())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή������M�p��������ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �����M�p�o���ʒm
        else if (l_row instanceof QtpRichPushEquityContRow)
        {
            QtpRichPushEquityContRow l_dataRow = (QtpRichPushEquityContRow) l_row;
            // �����o���ʒm 03
            if (WEB3RichPushDataTypeDef.EQTYPE_CONT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // �s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // ��萔��
                    .append("eqt=")
                    .append((l_dataRow.getExecQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecQuantity()))
                    .append(",")
                    // ���P��
                    .append("epr=")
                    .append((l_dataRow.getExecPriceIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecPrice()))
                    .append(",")
                    // ������
                    .append("exet=")
                    .append((null == l_dataRow.getExecTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getExecTimestamp(), DATETIME_FORMAT))
                    .append(",")
                    // �o���ʒm�敪
                    .append("cpdv=")
                    .append((null == l_dataRow.getDealedType()) ? "" : l_dataRow.getDealedType())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή������M�p�o���ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �����M�p�����ʒm
        else if (l_row instanceof QtpRichPushEquityLapseRow)
        {
            QtpRichPushEquityLapseRow l_dataRow = (QtpRichPushEquityLapseRow) l_row;
            // ���������ʒm 04
            if (WEB3RichPushDataTypeDef.EQTYPE_LAPSE.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // �s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // ��萔��
                    .append("eqt=")
                    .append((l_dataRow.getExecutedQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecutedQuantity()))
                    .append(",")
                    // �������R�R�[�h
                    .append("lrcd=")
                    .append((null == l_dataRow.getReasonCode()) ? "" : l_dataRow.getReasonCode())
                    .append(",")
                    // �����ʒm�敪
                    .append("lpdv=")
                    .append((null == l_dataRow.getCloseNotifyType()) ? "" : l_dataRow.getCloseNotifyType())
                    .append(",")
                    // �G���[�R�[�h
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή������M�p�����ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �M�p�������n������t�ʒm
        else if (l_row instanceof QtpRichPushSwOrderacceptRow)
        {
            QtpRichPushSwOrderacceptRow l_dataRow = (QtpRichPushSwOrderacceptRow) l_row;
            // �����M�p�������n��t 01
            if (WEB3RichPushDataTypeDef.SWAP_ORDER_ACCEPT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // �s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // ������t����
                    .append("odacr=")
                    .append((null == l_dataRow.getAcceptStatus()) ? "" : l_dataRow.getAcceptStatus())
                    .append(",")
                    // �G���[�R�[�h
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή��M�p�������n������t�ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �敨�n�o������t�ʒm
        else if (l_row instanceof QtpRichPushIfoOrderacceptRow)
        {
            QtpRichPushIfoOrderacceptRow l_dataRow = (QtpRichPushIfoOrderacceptRow) l_row;
            // �敨OP������t�ʒm 10
            if (WEB3RichPushDataTypeDef.IFO_ORDER_ACCEPT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // ���敪
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // ����s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // �w����ʁi�����Y�����R�[�h�j
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // ����
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // �I�v�V�������i�敪
                    .append("pdtp=")
                    .append(getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // �s�g���i
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // ������t����
                    .append("odacr=")
                    .append((null == l_dataRow.getAcceptStatus()) ? "" : l_dataRow.getAcceptStatus())
                    .append(",")
                    // �G���[�R�[�h
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή��敨�n�o������t�ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �敨�n�o��������ʒm
        else if (l_row instanceof QtpRichPushIfoChangecancelRow)
        {
            QtpRichPushIfoChangecancelRow l_dataRow = (QtpRichPushIfoChangecancelRow) l_row;
            // �敨OP��������ʒm 12
            if (WEB3RichPushDataTypeDef.IFO_CHANGE_CANCEL.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // ���敪
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // ����s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // �w����ʁi�����Y�����R�[�h�j
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // ����
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // �I�v�V�������i�敪
                    .append("pdtp=")
                    .append(getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // �s�g���i
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // �����㐔��
                    .append("acqt=")
                    .append(WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedQuantity()))
                    .append(",")
                    // ������w�l
                    .append("aclpr=")
                    .append(WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedLimitPrice()))
                    .append(",")
                    // �����㎷�s����
                    .append("acectp=")
                    .append((null == l_dataRow.getModifiedExecutionType()) ? "" : l_dataRow.getModifiedExecutionType())
                    .append(",")
                    // �������ʃR�[�h
                    .append("crcd=")
                    .append((null == l_dataRow.getModifiedResult()) ? "" : l_dataRow.getModifiedResult())
                    .append(",")
                    // ��������ʒm�敪
                    .append("ccpdv=")
                    .append((null == l_dataRow.getCanmodReceiptType()) ? "" : l_dataRow.getCanmodReceiptType())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή��敨�n�o��������ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �敨�n�o�o���ʒm
        else if (l_row instanceof QtpRichPushIfoContRow)
        {
            QtpRichPushIfoContRow l_dataRow = (QtpRichPushIfoContRow) l_row;
            // �敨OP�o���ʒm 13
            if (WEB3RichPushDataTypeDef.IFO_CONT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // ���敪
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // ����s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // �w����ʁi�����Y�����R�[�h�j
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // ����
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // �I�v�V�������i�敪
                    .append("pdtp=")
                    .append(getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // �s�g���i
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // ��萔��
                    .append("eqt=")
                    .append((l_dataRow.getExecQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecQuantity()))
                    .append(",")
                    // ���P��
                    .append("epr=")
                    .append((l_dataRow.getExecPriceIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecPrice()))
                    .append(",")
                    // ������
                    .append("exet=")
                    .append((null == l_dataRow.getExecTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getExecTimestamp(), DATETIME_FORMAT))
                    .append(",")
                    // �o���ʒm�敪
                    .append("cpdv=")
                    .append((null == l_dataRow.getDealedType()) ? "" : l_dataRow.getDealedType())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή��敨�n�o�o���ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �敨�n�o�����ʒm
        else if (l_row instanceof QtpRichPushIfoLapseRow)
        {
            QtpRichPushIfoLapseRow l_dataRow = (QtpRichPushIfoLapseRow) l_row;
            // �敨OP�����ʒm 14
            if (WEB3RichPushDataTypeDef.IFO_LAPSE.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // �f�[�^�R�[�h
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // ��ЃR�[�h
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // ���X�R�[�h
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // �ڋq�R�[�h
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // �I�y���[�^�[�R�[�h
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // �����h�c
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // �����R�[�h
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // ���敪
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // ����s��R�[�h
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // �w����ʁi�����Y�����R�[�h�j
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // ����
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // �I�v�V�������i�敪
                    .append("pdtp=")
                    .append((null == l_dataRow.getDerivativeType()) ? "" : getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // �s�g���i
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // ��萔��
                    .append("eqt=")
                    .append((l_dataRow.getExecutedQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecutedQuantity()))
                    .append(",")
                    // �������R�R�[�h
                    .append("lrcd=")
                    .append((null == l_dataRow.getReasonCode()) ? "" : l_dataRow.getReasonCode())
                    .append(",")
                    // �����ʒm�敪
                    .append("lpdv=")
                    .append((null == l_dataRow.getCloseNotifyType()) ? "" : l_dataRow.getCloseNotifyType())
                    .append(",")
                    // �G���[�R�[�h
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // �����敪
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // �쐬���t
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �X�V���t
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // �V���A���ԍ�
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // ���Ή��敨�n�o�����ʒm�f�[�^
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // �v�b�V���f�[�^�쐬�ł��Ȃ��ꍇ�A����Row�f�[�^������
        else
        {
            log.error("unknown data, qtp push skip." + l_row);
        }

        log.exiting(STR_METHOD_NAME);
        return l_pushObj;

    }

    /**
     * ���敪���擾����<br/>
     * 
     * @@param l_orderType
     *            �������
     * @@return
     */
    private String getContp( OrderTypeEnum l_orderType )
    {
        final String STR_METHOD_NAME = "getContp(OrderTypeEnum l_orderType)";
        log.entering(STR_METHOD_NAME);

        String l_strContp = "";
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType)
                || OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
        // 605�FOP�V�K���������@@�@@�@@�@@�@@�@@�A601�F�敨�V�K��������
        // 608�FOP�������ԍϒ����i���ԍρj�A604�F�敨�������ԍϒ����i���ԍρj
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType)
                || OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
        // 606�FOP�V�K���������@@�@@�@@�@@�@@�@@�A602�F�敨�V�K��������
        // 607�FOP�������ԍϒ����i���ԍρj�A603�F�敨�������ԍϒ����i���ԍρj
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strContp;
    }

    /**
     * �I�v�V�������i�敪���擾����<br/>
     * 
     * @@param l_derivativeType
     *            �敨�I�v�V�������i
     * @@return
     */
    private String getPdtp( IfoDerivativeTypeEnum l_derivativeType )
    {
        final String STR_METHOD_NAME = "getPdtp( IfoDerivativeTypeEnum l_derivativeType )";
        log.entering(STR_METHOD_NAME);

        String l_strPdtp = "";
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_derivativeType))
        {
            l_strPdtp = WEB3IfoProductTypeDef.CALL_OPTIONS;
        }
        else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_derivativeType))
        {
            l_strPdtp = WEB3IfoProductTypeDef.PUT_OPTIONS;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPdtp;
    }

    /**
     * �s�g���i���擾����<br/>
     * 
     * @@param l_derivativeType
     *            �敨�I�v�V�������i
     * @@param l_strikePrice
     * @@return
     */
    private String getSpr( IfoDerivativeTypeEnum l_derivativeType, double l_strikePrice )
    {
        final String STR_METHOD_NAME = "getSpr( IfoDerivativeTypeEnum l_derivativeType, double l_strikePrice )";
        log.entering(STR_METHOD_NAME);

        String l_strSpr = "";
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_derivativeType) || IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_derivativeType))
        {
            l_strSpr = WEB3StringTypeUtility.formatNumber(l_strikePrice);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSpr;
    }

    /**
     * �t���[�^�O�����`�F�b�N
     * 
     * @@param l_institutionCode
     * @@return �t���[�^�O���K�v�̏ꍇ�̂݁uTRUE�v
     */
    private boolean needFtag(String l_institutionCode)
    {
        if("TRUE".equalsIgnoreCase(
            GtlUtils.getTradingSystem().getPreference(QTP_RICH_PUSH_SEND_FTAG + l_institutionCode)
            ))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
@
