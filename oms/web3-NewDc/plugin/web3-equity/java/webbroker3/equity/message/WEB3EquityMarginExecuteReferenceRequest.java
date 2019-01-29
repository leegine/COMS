head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityMarginExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����E�M�p�������Ɖ�N�G�X�g(WEB3EquityMarginExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/10 �֔�(���u) �V�K�쐬
Revesion History : 2007/10/16 ����(���u) �d�l�ύX���f��1200
Revesion History : 2007/12/17 ����(���u) �d�l�ύX���f��1232
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityExecStatusTypeDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityProductDivDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����E�M�p�������Ɖ�N�G�X�g)<BR>
 * �����E�M�p�������Ɖ�N�G�X�g�N���X<BR>
 * @@author  �֔�
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceRequest extends WEB3GenRequest
{

    /**
     * ���O�o�́B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_margin_execute_reference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200701101127L;

    /**
     * (�Ɖ�敪)<BR>
     * 0�F �������Ɖ�<BR>
     * 1�F ��������ꗗ<BR>
     * <BR>
     * �� null �s��<BR>
     */
    public String referenceType;

    /**
     * (���i�敪)<BR>
     * 0�F ���������A�M�p��� ���ׂ�<BR>
     * 1�F ��������<BR>
     * 2�F �M�p���<BR>
     * <BR>
     * �� null �s��<BR>
     */
    public String productDiv;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     * �� null �̏ꍇ�A�w��Ȃ�<BR>
     */
    public String productCode;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * �� null �̏ꍇ�A�w��Ȃ�<BR>
     */
    public String marketCode;

    /**
     * (����ԋ敪)<BR>
     * 0�F �����<BR>
     * 1�F �ꕔ����<BR>
     * 2�F �S������<BR>
     * null�F �w��Ȃ�<BR>
     */
    public String execType;

    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     * �� null �̏ꍇ�A�w��Ȃ�<BR>
     */
    public Date orderBizDate;

    /**
     * (���������敪)<BR>
     * ���������敪<BR>
     * <BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;

    /**
     * (�\�[�g�L�[)<BR>
     * �����E�M�p����\�[�g�L�[<BR>
     * <BR>
     * �Ώۍ��ځF
     * �����R�[�h�A�����敪�A�s��R�[�h�A����敪�A�l�i�����A���s<BR>
     * �����A���������A�������ԁA�������A���������A�ٍϋ敪�A�ٍϊ����l<BR>
     */
    public WEB3EquityMarginSortKey[] sortKeys;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     * <BR>
     * �\�����������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     * <BR>
     * �P�y�[�W���ɕ\�����������s�����w��<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 45A33C7A032C
     */
    public WEB3EquityMarginExecuteReferenceRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�Ɖ�敪�`�F�b�N<BR>
     * <BR>
     * �P�|�P�j<BR>
     *     this.�Ɖ�敪 �� null �̏ꍇ�A�u�Ɖ�敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00081<BR>
     * �P�|�Q�j<BR>
     * <BR>
     * this.�Ɖ�敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A�u�Ɖ�敪��<BR>
     * ����`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     *     �E�h�������Ɖ�h<BR>
     *     �E�h��������ꗗ�h<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00082<BR>
     * �Q�j���i�敪�`�F�b�N<BR>
     * <BR>
     * �Q�|�P�j<BR>
     *     this.���i�敪 �� null �̏ꍇ�A�u���i�敪��null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_02182<BR>
     * �Q�|�Q�j<BR>
     * <BR>
     * this.���i�敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A�u���i�敪��<BR>
     * ����`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     *     �E�h���������A�M�p��� ���ׂāh<BR>
     *     �E�h���������h<BR>
     *     �E�h�M�p����h<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_01068<BR>
     * �R�j����ԋ敪�`�F�b�N<BR>
     * <BR>
     * this.����ԋ敪 �� null and<BR>
     * this.����ԋ敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A<BR>
     * �u����ԋ敪������`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     *     �E�h�����h<BR>
     *     �E�h�ꕔ�����h<BR>
     *     �E�h�S�������h<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00626<BR>
     * �S�j�\�[�g�L�[�`�F�b�N<BR>
     * <BR>
     * �S�|�P�j<BR>
     *     this.�\�[�g�L�[ �� null �̏ꍇ�A�u�\�[�g�L�[��null�v<BR>
     *     �̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00231<BR>
     * �S�|�Q�j<BR>
     *     this.�\�[�g�L�[.�v�f�� �� 0<BR>
     * �̏ꍇ�A�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00232<BR>
     * �S�|�R�j<BR>
     *     this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B<BR>
     * <BR>
     * �S�|�R�|�P�j<BR>
     *     �\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * �S�|�R�|�Q�j<BR>
     *     �\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO���ݒ肳��Ă���ꍇ�A<BR>
     *     �u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     *     �E�h�����R�[�h�h<BR>
     *     �E�h�����敪�h<BR>
     *     �E�h�s��R�[�h�h<BR>
     *     �E�h����敪�h<BR>
     *     �E�h�l�i�����h<BR>
     *     �E�h���s�����h<BR>
     *     �E�h���������h<BR>
     *     �E�h�������ԁh<BR>
     *     �E�h�������h<BR>
     *     �E�h���������h<BR>
     *     �E�h�ٍϋ敪�h<BR>
     *     �E�h�ٍϊ����l�h<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00086<BR>
     * �T�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * <BR>
     * �T�|�P�j<BR>
     *     this.�v���y�[�W�ԍ� �� null<BR>
     * �̏ꍇ�A�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00089<BR>
     * �T�|�Q�j<BR>
     * <BR>
     * this.�v���y�[�W�ԍ��������ȊO�̒l�̏ꍇ�A�u�v���y�[�W�ԍ�������<BR>
     * �ȊO�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00090<BR>
     * �T�|�R�j<BR>
     *     this.�v���y�[�W�ԍ� �� 0<BR>
     * �̏ꍇ�A�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00616<BR>
     * �U�j�y�[�W���\���s���`�F�b�N<BR>
     * <BR>
     * �U�|�P�j<BR>
     *     this.�y�[�W���\���s�� �� null<BR>
     * �̏ꍇ�A�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00091<BR>
     * �U�|�Q�j<BR>
     * <BR>
     * this.�y�[�W���\���s���������ȊO�̒l�̏ꍇ�A�u�y�[�W���\���s������<BR>
     * ���ȊO�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00092<BR>
     * �U�|�R�j<BR>
     *     this.�y�[�W���\���s�� �� 0<BR>
     * �̏ꍇ�A�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00617<BR>
     * �V�j�s��R�[�h�`�F�b�N<BR>
     * <BR>
     * this.�s��R�[�h �� null and<BR>
     * ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     *     �E�h�����h<BR>
     *     �E�h���h<BR>
     *     �E�h���É��h<BR>
     *     �E�h�����h<BR>
     *     �E�h�D�y�h<BR>
     *     �E�hNNM�h<BR>
     *     �E�hJASDAQ�h<BR>
     *     �E�hJNX-PTS�h<BR>
     * <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * �W�j���������敪 �� null and ���L�̒l�ȊO�̏ꍇ�A<BR>
     * �u���������敪������`�̒l�v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�E�h�w��Ȃ��h <BR>
     * �@@�E�h�t�w�l�h<BR>
     * �@@�E�hW�w�l�h<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:   BUSINESS_ERROR_00212<BR>
     * <BR>
     * @@throws webbroker3.equity.common.WEB3BaseException
     * @@roseuid 455C352701B0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " .validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�Ɖ�敪�`�F�b�N
        //�P�|�P�j
        //    this.�Ɖ�敪 �� null �̏ꍇ�A�u�Ɖ�敪��null�v�̗�O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00081
        if (WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            log.debug("�Ɖ�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ɖ�敪 = " + this.referenceType);
        }

        //�P�|�Q�j
        //this.�Ɖ�敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A�u�Ɖ�敪������`�̒l�v�̗�O���X���[����B
        //�E�h�������Ɖ�h
        //�E�h��������ꗗ�h
        //class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00082
        if (!WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(this.referenceType) &&
            !WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(this.referenceType))
        {
            log.debug("�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + STR_METHOD_NAME,
                "�Ɖ�敪 = " + this.referenceType);
        }

        //�Q�j���i�敪�`�F�b�N
        //�Q�|�P�j
        //    this.���i�敪 �� null �̏ꍇ�A�u���i�敪��null�v�̗�O���X���[����B
        // �@@ class: WEB3BusinessLayerException
        // �@@ tag:   BUSINESS_ERROR_02182
        if (WEB3StringTypeUtility.isEmpty(this.productDiv))
        {
            log.debug("���i�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02182,
                this.getClass().getName() + STR_METHOD_NAME,
                "���i�敪 = " + this.productDiv);
        }

        //�Q�|�Q�j
        //this.���i�敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A�u���i�敪������`�̒l�v�̗�O���X���[����B
        //    �E�h���������A�M�p��� ���ׂāh
        //    �E�h���������h
        //    �E�h�M�p����h
        //     class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_01068
        if (!WEB3EquityProductDivDef.EQUITY_MARGIN.equals(this.productDiv) &&
            !WEB3EquityProductDivDef.EQUITY.equals(this.productDiv) &&
            !WEB3EquityProductDivDef.MARGIN.equals(this.productDiv))
        {
            log.debug("���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                this.getClass().getName() + STR_METHOD_NAME,
                "���i�敪 = " + this.productDiv);
        }

        //�R�j����ԋ敪�`�F�b�N
        //this.����ԋ敪 �� null and this.����ԋ敪�����L�̒l�ȊO���ݒ肳��Ă���ꍇ�A
        //�u����ԋ敪������`�̒l�v�̗�O���X���[����B
        //    �E�h�����h
        //    �E�h�ꕔ�����h
        //    �E�h�S�������h
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00626
        if (!WEB3StringTypeUtility.isEmpty(this.execType) &&
            !WEB3EquityExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType) &&
            !WEB3EquityExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType) &&
            !WEB3EquityExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType))
        {
            log.debug("����ԋ敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00626,
                this.getClass().getName() + STR_METHOD_NAME,
                "����ԋ敪 = " + this.execType);
        }

        //�S�j�\�[�g�L�[�`�F�b�N
        //�S�|�P�j
        //    this.�\�[�g�L�[ �� null �̏ꍇ�A�u�\�[�g�L�[��null�v�̗�O���X���[����B
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00231
        if (this.sortKeys == null)
        {
            log.debug("�\�[�g�L�[�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[ = " + this.sortKeys);
        }

        //�S�|�Q�j
        //this.�\�[�g�L�[.�v�f�� �� 0 �̏ꍇ�A�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            log.debug("�\�[�g�L�[�̗v�f�����O�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "�\�[�g�L�[ = " + this.sortKeys);
        }

        //�S�|�R�j
        //this.�\�[�g�L�[�̑S�v�f�ɑ΂��āA���L�̃`�F�b�N���s���B
        int l_intSortKeyLength = sortKeys.length;
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //�S�|�R�|�P�j
            //�\�[�g�L�[.validate()���R�[������B
            sortKeys[i].validate();
            //�S�|�R�|�Q�j
            //     �\�[�g�L�[.�L�[���ڂɉ��L�̍��ڈȊO���ݒ肳��Ă���ꍇ�A
            //     �u�\�[�g�L�[.�L�[���ڂ�����`�̒l�v�̗�O���X���[����B
            //     �E�h�����R�[�h�h
            //     �E�h�����敪�h
            //     �E�h�s��R�[�h�h
            //     �E�h����敪�h
            //     �E�h�l�i�����h
            //     �E�h���s�����h
            //     �E�h���������h
            //     �E�h�������ԁh
            //     �E�h�������h
            //     �E�h���������h
            //     �E�h�ٍϋ敪�h
            //     �E�h�ٍϊ����l�h
            if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.TRADEMARKET.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.TRADETYPE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.PRICE_COND.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.EXECUTE_COND.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.SEND_COND.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.ORDER_TIME.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.SEND_DATE.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.ORDER_TIMELIMIT.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.REPAYMENT_DIV.equals(sortKeys[i].keyItem) &&
                !WEB3EquityKeyItemDef.REPAYMENTNUM.equals(sortKeys[i].keyItem))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
                }
            }

        //�T�j�v���y�[�W�ԍ��`�F�b�N
        //
        //�T�|�P�j
        //    this.�v���y�[�W�ԍ� �� null �̏ꍇ�A�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        //
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00089
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ� = " + this.pageIndex);
        }

        //�T�|�Q�j
        //this.�v���y�[�W�ԍ��������ȊO�̒l�̏ꍇ�A�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        //
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.debug("�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ� = " + this.pageIndex);
        }

        //�T�|�R�j
        //this.�v���y�[�W�ԍ� �� 0 �̏ꍇ�A�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        //
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00616
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("�v���y�[�W�ԍ���0�ȉ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "�v���y�[�W�ԍ� = " + this.pageIndex);
        }

        //�U�j�y�[�W���\���s���`�F�b�N
        //�U�|�P�j
        //    this.�y�[�W���\���s�� �� null �̏ꍇ�A�u�y�[�W���\���s����null�v�̗�O���X���[����B
        //
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00091
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("�y�[�W���\���s���̓��͂��s���ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s�� = " + this.pageSize);
        }

        //�U�|�Q�j
        //this.�y�[�W���\���s���������ȊO�̒l�̏ꍇ�A�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        //
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.debug("�y�[�W���\���s���������ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s�� = " + this.pageSize);
        }

        //�U�|�R�j
        //this.�y�[�W���\���s�� �� 0 �̏ꍇ�A�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        //
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00617
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("�y�[�W���\���s����0�ȉ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "�y�[�W���\���s�� = " + this.pageSize);
        }

        //�V�j�s��R�[�h�`�F�b�N
        //this.�s��R�[�h �� null and ���L�̒l�ȊO�̏ꍇ�A�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //    �E�h�����h
        //    �E�h���h
        //    �E�h���É��h
        //    �E�h�����h
        //    �E�h�D�y�h
        //    �E�hNNM�h
        //    �E�hJASDAQ�h
        //    �E�hJNX-PTS�h
        //
        // �@@�@@class: WEB3BusinessLayerException
        // �@@�@@tag:   BUSINESS_ERROR_00608
        if (this.marketCode != null)
        {
            if (!(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                || WEB3MarketCodeDef.NNM.equals(this.marketCode)
                || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                || WEB3MarketCodeDef.JNX_PTS.equals(this.marketCode)))
            {
                log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�s��R�[�h = " + this.marketCode);
            }
        }

        // �W�j���������敪 �� null and ���L�̒l�ȊO�̏ꍇ�A�A�u���������敪������`�̒l�v�̗�O���X���[����B
        if (this.orderCondType != null)
        {
            if (!(WEB3OrderingConditionDef.DEFAULT.equals(this.orderCondType)
                || WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(this.orderCondType)
                || WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(this.orderCondType)))
            {
                log.debug("���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���������敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityMarginExecuteReferenceResponse(this);
    }
}
@
