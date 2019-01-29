head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v���׏Ɖ�N�G�X�g(WEB3PLSProfitLossSpecsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  �͌d��(���u) �V�K�쐬
                 : 2006/10/19  ��іQ (���u) ���f��056
                 : 2006/12/15  �����q (���u) ���f��068
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.tradehistory.define.WEB3PlsBvsDisplayTermDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryProductDivDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTransactionDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���v���׏Ɖ�N�G�X�g)<BR>
 * ���v���׏Ɖ�N�G�X�g�N���X<BR>
 *
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_profitLossSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;

    /**
     * (�\������)<BR>
     * <BR>
     * 0�F�@@�O�������ȍ~(DEFAULT)<BR>
     * 1�F�@@1������<BR>
     * 2�F�@@1�T�ԕ�<BR>
     * 3�F�@@�O��1����<BR>
     */
    public String displayTerm;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;

    /**
     * (�\������From)<BR>
     * �\������From<BR>
     * (YYYYMMDD)<BR>
     */
    public String listStartDate;

    /**
     * (�\������To)<BR>
     * �\������To<BR>
     * (YYYYMMDD)<BR>
     */
    public String listEndDate;

    /**
     * (���i�敪)<BR>
     * ���i�敪<BR>
     * �@@A�F�@@�S���i<BR>
     * �@@B�F�@@�����E�M�p<BR>
     * �@@C�F�@@����<BR>
     * �@@D�F�@@�M�p<BR>
     * �@@L�F�@@��<BR>
     * �@@H�F�@@���M<BR>
     * �@@K�F�@@�O������<BR>
     * �@@I�F�@@���o��<BR>
     */
    public String commodityType;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * �@@null�F�@@2�����\��<BR>
     * �@@01�F�@@18�����\��<BR>
     */
    public String transactionDiv;

    /**
     * (�\�[�g�L�[)
     * ���v���׃\�[�g�L�[[]
     */
    public WEB3PLSProfitLossSpecsSortKeyUnit[] sortKeys;

    /**
     * @@roseuid 418877BC00AB
     */
    public WEB3PLSProfitLossSpecsRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j 2�����\���`�F�b�N<BR>
     * �@@��this.�����敪 == null�̏ꍇ�Ɏ��{����B<BR>
     * <BR>
     * �P�|�P�j�@@�\�����ԃ`�F�b�N<BR>
     * �@@�P�|�P�|�P�jthis.�\������ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�����Ԃ�null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01082              <BR>
     * <BR>
     * �@@�P�|�P�|�Q�jthis.�\�����Ԃ��ȉ��Ɏ����l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�\�����Ԃ�����`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�E"0�F�O�������ȍ~(DEFAULT)"<BR>
     * �@@�@@�@@�@@�@@�@@�E"1�F1������"<BR>
     * �@@�@@�@@�@@�@@�@@�E"2�F1�T�ԕ�"<BR>
     * �@@�@@�@@�@@�@@�@@�E"3�F�O��1����"<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_01083              <BR>
     * <BR>
     * �Q�j 18�����\���`�F�b�N <BR>
     * �@@��this.�����敪 == 01�̏ꍇ�Ɏ��{����B<BR>
     * �@@�Q�|�P�j �\������From�ETo�`�F�b�N<BR>
     * �@@�@@�Ethis.�\������From != null ���� this.�\������To != null�̏ꍇ�́A<BR>
     * �@@�@@�@@�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�P�j this.�\������From��Date�^�ɕϊ����A�G���[�����������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01065<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�Q�j this.�\������To��Date�^�ɕϊ����A�G���[�����������ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01066<BR>
     * <BR>
     * �@@�@@�Q�|�P�|�R�j this.�\������From > this.�\������To�ł���ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�\������(��)(��)�������G���[�v�̗�O���X���[����B<BR>
     *            class          :  WEB3BusinessLayerException<BR>
     *            tag             : BUSINESS_ERROR_01051<BR>
     * <BR>
     *  �@@�Q�|�Q�j �����R�[�h�`�F�b�N <BR>
     *  �@@�@@�Ethis.�����R�[�h != null�̏ꍇ�́A�ȉ��̃`�F�b�N���s��<BR>
     *  <BR>
     *  �@@�@@�Q�|�Q�|�P�j this.�����R�[�h���ȉ��̏����ɊY������ꍇ�́A<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�Ethis.�����R�[�h.length() != 4�� ���� 5��<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_00439<BR>
     * <BR>
     *  �@@�@@�Q�|�Q�|�Q�j this.���i�敪���ȉ��ɊY�����Ȃ��ꍇ�A<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�u���i�������G���[�v�̗�O���X���[����B<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E �hA�F�@@�S���i�h<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E �hB�F�@@�����E�M�p�h<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E �hC�F�@@�����h<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E �hD�F�@@�M�p�h<BR>
     *  �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E �hK�F�@@�O�������h<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01068<BR>
     * <BR>
     * �@@�Q�|�R�j ���i�敪�`�F�b�N<BR>
     * �@@�@@�Ethis.���i�敪 != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�P�j this.���i�敪���ȉ��Ɏ����l�̂��Â�ɂ��Y�����Ȃ��ꍇ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u���i�������G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@A�F�@@�S���i<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@B�F�@@�����E�M�p<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@C�F�@@����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@D�F�@@�M�p<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@L�F�@@��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@H�F�@@���M<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@K�F�@@�O������<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�E�@@I�F�@@���o��<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_01068<BR>
     * <BR>
     * �@@�Q�|�S�j �\�[�g�L�[�`�F�b�N<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�P�j this.�\�[�g�L�[ == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_00231<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�Q�j this.�\�[�g�L�[.�v�f�� == 0�������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException<BR>
     *            tag            : BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�@@�Q�|�S�|�R�j this.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�E�\�[�g�L�[.validate()���R�[������B<BR>
     * <BR>
     * <BR>
     * �R�j ���ʃ`�F�b�N<BR>
     *  <BR>
     * �R�j�v���y�[�W�ԍ��`�F�b�N<BR>
     * �@@�R�|�P�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00089              <BR>
     * <BR>
     * �@@�R�|�P�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00090              <BR>
     * <BR>
     * �@@�R�|�P�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00616              <BR>
     * <BR>
     * �R�|�Q�j�y�[�W���\���s���`�F�b�N<BR>
     * �@@�R�|�Q�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00091              <BR>
     * <BR>
     * �@@�R�|�Q�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00092              <BR>
     * <BR>�@@
     * �@@�R�|�Q�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A<BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     *            class         :  WEB3BusinessLayerException           <BR>
     *            tag            :  BUSINESS_ERROR_00617              <BR>
     * <BR>
     * @@roseuid 416CDE4C00C2
     */
    public void validate() throws  WEB3BaseException
    {
        final  String  STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //    �P�j 2�����\���`�F�b�N
        //   �@@��this.�����敪 == null�̏ꍇ�Ɏ��{����.
        if (this.transactionDiv == null)
        {
            // �@@�P�|�P�|�P�jthis.�\������ == null�̏ꍇ�A
            // �@@�@@�@@�@@�@@�u�\�����Ԃ�null�v�̗�O���X���[����B
            if (this.displayTerm == null)
            {
                log.error("�\�����Ԃ�null�̒l�̃G���[ �B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01082,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����Ԃ����w��ł��B");
            }

            // �@@�P�|�P�|�Q�jthis.�\�����Ԃ��ȉ��Ɏ����l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A
            // �@@�@@�@@�@@�@@�u�\�����Ԃ�����`�̒l�v�̗�O���X���[����B
            // �@@�@@�@@�@@�@@�@@�E"0�F�O�������ȍ~(DEFAULT)"
            // �@@�@@�@@�@@�@@�@@�E"1�F1������"
            // �@@�@@�@@�@@�@@�@@�E"2�F1�T�ԕ�"
            // �@@�@@�@@�@@�@@�@@�E"3�F�O��1����"
            if (!WEB3PlsBvsDisplayTermDef.DEFAULT.equals(this.displayTerm) &&
                !WEB3PlsBvsDisplayTermDef.ONE_MONTH.equals(this.displayTerm) &&
                !WEB3PlsBvsDisplayTermDef.ONE_WEEK.equals(this.displayTerm) &&
                !WEB3PlsBvsDisplayTermDef.THE_PREVIOUS_DAY.equals(this.displayTerm))
            {
                log.error("�\�����Ԃ�����`�̒l�̃G���[ �B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01083,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�����Ԃ����݂��Ȃ��R�[�h�l�ł��B");
            }
        }

        //    �Q�j 18�����\���`�F�b�N
        //   �@@��this.�����敪 == 01�̏ꍇ�Ɏ��{����B
        if (WEB3TradeHistoryTransactionDivDef.EIGHTEEN_MONTH_DISPLAY.equals(this.transactionDiv))
        {
            //   �@@�Q�|�P�j �\������From�ETo�`�F�b�N
            //  �@@�@@�Ethis.�\������From != null ���� this.�\������To != null�̏ꍇ��
            //  �@@�@@�@@�ȉ��̃`�F�b�N���s���B
            if (this.listStartDate != null && this.listEndDate != null)
            {
                //�Q�|�P�|�P�jthis.�\������From��Date�^�ɕϊ����A�G���[�����������ꍇ�́A
                //   �@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B
                if (!(WEB3StringTypeUtility.isDateStr(this.listStartDate, "yyyyMMdd")))
                {
                    log.error(" �\������(��)���t�G���[ �B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01065,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");
                }

                //�Q�|�P�|�Q�jthis.�\������To��Date�^�ɕϊ����A�G���[�����������ꍇ�́A
                // �@@�@@�@@�u�\������(��)���t�G���[�v�̗�O���X���[����B
                if (!(WEB3StringTypeUtility.isDateStr(this.listEndDate, "yyyyMMdd")))
                {
                    log.error(" �\������(��)���t�G���[ �B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01066,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�\�����ԁi���j���t�t�H�[�}�b�g�G���[�B");
                }

                //�Q�|�P�|�R�jthis.�\������From > this.�\������To�ł���ꍇ�́A
                // �@@�@@�@@�u�\������(��)(��)�������G���[�v�̗�O���X���[����B
                Date l_datListStartDate = WEB3DateUtility.getDate(this.listStartDate, "yyyyMMdd");
                Date l_datListEndDate = WEB3DateUtility.getDate(this.listEndDate, "yyyyMMdd");
                if (WEB3DateUtility.compareToDay(l_datListStartDate,l_datListEndDate) > 0)
                {
                    log.error(" �\������(��)(��)�������G���[ �B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01051,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�\�����ԁi���j�͕\�����ԁi���j���傫���ł��B");
                }
            }

            //   �@@�Q�|�Q�j �����R�[�h�`�F�b�N
            //  �@@�@@�Ethis.�����R�[�h != null�̏ꍇ�́A�ȉ��̃`�F�b�N���s���B
            if (this.productCode != null)
            {
                //�Q�|�Q�|�P�jthis.�����R�[�h���ȉ��̏����ɊY������ꍇ�́A
                // �@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B
                // �@@�@@�@@�Ethis.�����R�[�h.length() != 4�� ���� 5��
                if (this.productCode.length() != 4
                    && this.productCode.length() != 5)
                {
                    log.error(" �����R�[�h�G���[ �B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "�����R�[�h�̃T�C�Y���s���ł��B");
                }

                //�Q�|�Q�|�Q�jthis.���i�敪���ȉ��ɊY�����Ȃ��ꍇ�A
                // �@@�@@�@@�u���i�������G���[�v�̗�O���X���[����B
                // �@@�@@�@@�E�hA:�S���i�h
                // �@@�@@�@@�E�hB:�����E�M�p�h
                // �@@�@@�@@�E�hC:�����h
                // �@@�@@�@@�E�hD:�M�p�h
                // �@@�@@�@@�E�hK:�O�������h
                if (!WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.FOREIGN.equals(this.commodityType))
                {
                    log.error(" ���i�������G���[ �B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
                }
            }

            //   �@@�Q�|�R�j ���i�敪�`�F�b�N
            //  �@@�@@�Ethis.���i�敪 != null �̏ꍇ�́A�ȉ��̃`�F�b�N���s���B
            if (this.commodityType != null)
            {
                //�Q�|�R�|�P�j this.���i�敪���ȉ��Ɏ����l�̂��Â�ɂ��Y�����Ȃ��ꍇ�A
                //   �u���i�������G���[�v�̗�O���X���[����.
                //  �E�@@A�F�@@�S���i
                //  �E�@@B�F�@@�����E�M�p
                //  �E�@@C�F�@@����
                //  �E�@@D�F�@@�M�p
                //  �E�@@L�F�@@��
                //  �E�@@H�F�@@���M
                //  �E�@@K�F�@@�O������
                //  �E�@@I�F�@@���o��
                if (!WEB3TradeHistoryProductDivDef.ALL_PRODUCT.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY_MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.EQUITY.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.MARGIN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.BOND.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.MUTUAL_FUND_RUITO.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.FOREIGN.equals(this.commodityType)
                    && !WEB3TradeHistoryProductDivDef.AIO.equals(this.commodityType))
                {
                    log.error(" ���i�������G���[ �B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
                }
            }

            // �Q�|�S�|�P�j this.�\�[�g�L�[ == null�ł������ꍇ�A
            //�@@�@@�@@�@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B
            if (this.sortKeys == null)
            {
                log.debug("�\�[�g�L�[��null�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�����w��ł��B" + this.sortKeys);
            }

            // �Q�|�S�|�Q�j this.�\�[�g�L�[.�v�f�� == 0�������ꍇ�A
            //�@@�@@�@@�@@�u�\�[�g�L�[.�v�f����0�v�̗�O���X���[����B
            if (this.sortKeys.length == 0)
            {
                log.debug("�\�[�g�L�[.�v�f����0�B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�\�[�g�L�[�̗v�f�����O�ł��B" + this.sortKeys.length);
            }

            // �Q�|�S�|�R�j this.�\�[�g�L�[�̑S�v�f�ɑ΂��ĉ��L�̃`�F�b�N���s���B
            //�@@�@@�@@�@@�E�\�[�g�L�[.validate()���R�[������B
            for (int i = 0; i < this.sortKeys.length; i++)
            {
                this.sortKeys[i].validate();
            }
        }

        // �R�|�P�j�v���y�[�W�ԍ��`�F�b�N
        // �@@�R�|�P�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        if (this.pageIndex == null)
        {
             //��O
             log.error(" �v���y�[�W�ԍ���null �B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�v���y�[�W�ԍ������w��ł��B");
        }

        // �@@�R�|�P�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error(" �v���y�[�W�ԍ��������ȊO �B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }

        // �@@�R�|�P�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
             log.error(" �v���y�[�W�ԍ���0�ȉ� �B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }

        // �R�|�Q�j�y�[�W���\���s���`�F�b�N
        // �@@�R�|�Q�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B
        if (this.pageSize == null)
        {
             log.error(" �y�[�W���\���s����null �B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�y�[�W���\���s���̓��͂��s���ł��B");
        }

        // �@@�R�|�Q�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
             log.error(" �y�[�W���\���s���������ȊO �B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }

        // �@@�R�|�Q�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A
        // �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
             log.error("�y�[�W���\���s����0�ȉ� �B");
             log.exiting(STR_METHOD_NAME);
             throw new WEB3BaseException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 418877BC00CB
     */
    public WEB3GenResponse createResponse()
    {
     return new WEB3PLSProfitLossSpecsResponse(this);
    }
}
@
