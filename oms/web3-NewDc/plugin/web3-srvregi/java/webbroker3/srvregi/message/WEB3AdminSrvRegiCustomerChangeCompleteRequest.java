head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.33.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiCustomerChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������N�G�X�g(WEB3AdminSrvRegiCustomerChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 ���w�� �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AppliLotDivDef;
import webbroker3.common.define.WEB3ConditionsValueDivDef;
import webbroker3.common.define.WEB3PaymentDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX�������N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������N�G�X�g�N���X<BR>
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3AdminSrvRegiCustomerChangeCompleteRequest extends WEB3GenRequest
{

    /**
     * Logger
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiCustomerChangeCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_customerChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151418L;

    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;

    /**
     * (�ύX�ڋq�ꗗ)
     */
    public WEB3AdminSrvRegiCustomerChangeGroup[] chgCustomerList;

    /**
     * (�Ïؔԍ�)
     */
    public String password;

    /**
     * (�T�[�r�X���p�Ǘ��Ҍڋq�ύX�������N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F2330C0232
     */
    public WEB3AdminSrvRegiCustomerChangeCompleteRequest()
    {

    }

    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��Ҍڋq�ύX�������X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F2330C0241
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminSrvRegiCustomerChangeCompleteResponse(this);
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00758<BR>
     *  1-2) this.�T�[�r�X�敪�̌���!=2���ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00831<BR>
     * <BR>
     * 2) �ύX�ڋq�ꗗ�̃`�F�b�N<BR>
     * �@@this.�ύX�ڋq�ꗗ�̌������A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�inull�̏ꍇ�A�܂��͗v�f����0�̏ꍇ�A��O���X���[����B�j<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00893<BR>
     *  2-1) this.�ύX�ڋq�ꗗ.�\���o�^ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00832<BR>
     *  2-2) this.�ύX�ڋq�ꗗ.���X�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     *  2-3) this.�ύX�ڋq�ꗗ.���X�R�[�h�̌�����3���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00834<BR>
     *  2-4) this.�ύX�ڋq�ꗗ.�ڋq�R�[�h��null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     *  2-5) this.�ύX�ڋq�ꗗ.�ڋq�R�[�h�̌�����6���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     *  2-6) this.�ύX�ڋq�ꗗ.�K�p�J�n����null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     *  2-7) this.�ύX�ڋq�ꗗ.�K�p�I������null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00838<BR>
     *  2-8) this.�ύX�ڋq�ꗗ.�K�p�J�n����this.�ύX�ڋq�ꗗ.�K�p�I�����̏ꍇ�A<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00839<BR>
     * ��O���X���[�B<BR>
     *  2-9) this.�ύX�ڋq�ꗗ.�\����==null�ł���A<BR>
     * �@@�@@����this.�ύX�ڋq�ꗗ.�\������this.�ύX�ڋq�ꗗ.�K�p�J�n���̏ꍇ�A<BR>
     * ��O���X���[�B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00840<BR>
     *  2-10) this.�ύX�ڋq�ꗗ.�o�^�敪=null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01834<BR>
     *  2-11) this.�ύX�ڋq�ꗗ.�o�^�敪!=null�ł���A���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * �@@�@@�@@"�L��"<BR>
     * �@@�@@�@@"����"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00841<BR>
     *  2-12) this.�ύX�ڋq�ꗗ.���p����!=null�ł���A�����l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00842<BR>
     *  2-13) this.�ύX�ڋq�ꗗ.���p����!=null�ł���A����this.�ύX�ڋq�ꗗ.���p������<BR>
     * �@@�@@������9���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00843<BR>
     *  2-14) this.�ύX�ڋq�ꗗ.���I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@"��"<BR>
     * �@@�@@�@@"�L"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00844<BR>
     *  2-15) this.�ύX�ڋq�ꗗ.�\�����I�敪==null�̏ꍇ�A<BR>
     * �@@�@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00845<BR>
     *  2-16) this.�ύX�ڋq�ꗗ.���I�敪="��"�̏ꍇ�A<BR>
     *       this.�ύX�ڋq�ꗗ.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     *       ��O���X���[����B<BR>
     *        "���p" <BR>
     *        "�{�\��" <BR>
     *        "���"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     *  2-17) this.�ύX�ڋq�ꗗ.���I�敪="�L"�̏ꍇ�A<BR>
     *        this.�ύX�ڋq�ꗗ.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A<BR>
     *        ��O���X���[����B<BR>
     *        "�{�\��" <BR>
     *        "���I"<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01022<BR>
     *  2-18) this.���I�敪!="��"�ł���A����this.�\����==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00847<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F2330C0261
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //1) �T�[�r�X�敪�̃`�F�b�N
        //1-1) this.�T�[�r�X�敪==null�̏ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00758,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //1-2) this.�T�[�r�X�敪�̌���!=2���ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00831,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("�T�[�r�X�敪�̌���.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;
        }

        //2) �ύX�ڋq�ꗗ�̃`�F�b�N
        if (this.chgCustomerList == null || this.chgCustomerList.length == 0)
        {
            WEB3BaseException l_e = new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00893,
                getClass().getName() + STR_METHOD_NAME);
            log.debug("�ύX�ڋq�ꗗ.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_e;

        }
        else
        {
            int l_intArrayLengh = this.chgCustomerList.length;
            for (int i = 0; i< l_intArrayLengh; i++)
            {
                //2-1) this.�ύX�ڋq�ꗗ.�\���o�^ID==null�̏ꍇ�A��O���X���[����B
                if (this.chgCustomerList[i].applyRegId == null || "".equals(this.chgCustomerList[i].applyRegId.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00832,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�\���o�^ID.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-2) this.�ύX�ڋq�ꗗ.���X�R�[�h==null�̏ꍇ�A��O���X���[����B
                if (this.chgCustomerList[i].branchCode == null || "".equals(this.chgCustomerList[i].branchCode.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.���X�R�[�h.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-3) this.�ύX�ڋq�ꗗ.���X�R�[�h�̌�����3���̏ꍇ�A��O���X���[����B
                //U00871 start
                if (this.chgCustomerList[i].branchCode.length() != 3)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00834,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.���X�R�[�h�̌���.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                //U00871 end

                //2-4) this.�ύX�ڋq�ꗗ.�ڋq�R�[�h==null�̏ꍇ�A��O���X���[����B
                if (this.chgCustomerList[i].accountCode == null || "".equals(this.chgCustomerList[i].accountCode.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�ڋq�R�[�h.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-5) this.�ύX�ڋq�ꗗ.�ڋq�R�[�h�̌�����6���̏ꍇ�A��O���X���[����B
                //U00871 start
                if (this.chgCustomerList[i].accountCode.length() != 6)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�ڋq�R�[�h�̌���.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
                //U00871 end

                //2-6) this.�ύX�ڋq�ꗗ.�K�p�J�n��==null�̏ꍇ�A��O���X���[����B
                if (this.chgCustomerList[i].trialStartDate == null)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�K�p�J�n��.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-7) this.�ύX�ڋq�ꗗ.�K�p�I����==null�̏ꍇ�A��O���X���[����B
                if (this.chgCustomerList[i].trialEndDate == null)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00838,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�K�p�I����.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-8) this.�ύX�ڋq�ꗗ.�K�p�J�n����this.�ύX�ڋq�ꗗ.�K�p�I�����̏ꍇ�A��O���X���[�B
                if (WEB3DateUtility.compareToSecond(this.chgCustomerList[i].trialStartDate,
                    this.chgCustomerList[i].trialEndDate) > 0)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00839,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�K�p�J�n�����ύX�ڋq�ꗗ.�K�p�I����.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-9) this.�ύX�ڋq�ꗗ.�\����!=null�ł���A
                //����this.�ύX�ڋq�ꗗ.�\������this.�ύX�ڋq�ꗗ.�K�p�J�n���̏ꍇ�A��O���X���[�B
                if (this.chgCustomerList[i].applyDate != null &&
                    WEB3DateUtility.compareToSecond(this.chgCustomerList[i].applyDate,
                        this.chgCustomerList[i].trialStartDate) > 0)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00840,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�\�������ύX�ڋq�ꗗ.�K�p�J�n��.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-10) this.�ύX�ڋq�ꗗ.�o�^�敪=null�̏ꍇ�A��O���X���[����B
                if(this.chgCustomerList[i].registDiv == null || "".equals(this.chgCustomerList[i].registDiv.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01834,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("this.�ύX�ڋq�ꗗ.�o�^�敪=null�̏ꍇ.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-11) this.�ύX�ڋq�ꗗ.�o�^�敪!=null�ł���A
                //���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
                //"�L��" "����"
                if (this.chgCustomerList[i].registDiv != null &&
                    !"".equals(this.chgCustomerList[i].registDiv.trim())
                    && !WEB3PaymentDivDef.CHARGE.equals(this.chgCustomerList[i].registDiv)
                    && !WEB3PaymentDivDef.FREE.equals(this.chgCustomerList[i].registDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00841,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.�o�^�敪.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-12) this.�ύX�ڋq�ꗗ.���p����!=null�ł���A�����l�ȊO�̏ꍇ�A��O���X���[����B<BR>
                if (this.chgCustomerList[i].chargeAmt != null &&
                    !"".equals(this.chgCustomerList[i].chargeAmt.trim())
                    && !WEB3StringTypeUtility.isNumber(this.chgCustomerList[i].chargeAmt))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00842,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.���p����.", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                // 2-13) this.�ύX�ڋq�ꗗ.���p����!=null�ł���A����this.�ύX�ڋq�ꗗ.���p������
                //������9���̏ꍇ�A��O���X���[����B
                if (this.chgCustomerList[i].chargeAmt != null &&
                    !"".equals(this.chgCustomerList[i].chargeAmt.trim())
                    && this.chgCustomerList[i].chargeAmt.length() > 9)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00843,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.���p����. �ύX�ڋq�ꗗ.���p�����̌�����9��", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-14) this.�ύX�ڋq�ꗗ.���I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
                //"��"
                //"�L"
                if (!WEB3ConditionsValueDivDef.HAVE.equals(this.chgCustomerList[i].lotteryDiv)
                    && !WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.chgCustomerList[i].lotteryDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00844,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.���I�敪", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-15) this.�ύX�ڋq�ꗗ.�\�����I�敪==null�̏ꍇ�A��O���X���[����B
                if (this.chgCustomerList[i].applyLotteryDiv == null || "".equals(this.chgCustomerList[i].applyLotteryDiv.trim()))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00845,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("�ύX�ڋq�ꗗ.���I�敪 �ύX�ڋq�ꗗ.�\�����I�敪", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-16) this.�ύX�ڋq�ꗗ.���I�敪="��"�̏ꍇ�Athis.�ύX�ڋq�ꗗ.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
                // "���p"
                // "�{�\��"
                // "���"
                if (WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.chgCustomerList[i].lotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.TRIAL_APPLI.equals(this.chgCustomerList[i].applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.chgCustomerList[i].applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.CANCEL.equals(this.chgCustomerList[i].applyLotteryDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("���I�� �\�����I�敪 ���p,�{�\��,����@@�ȊO", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                 //2-17) this.�ύX�ڋq�ꗗ.���I�敪="�L"�̏ꍇ�Athis.�ύX�ڋq�ꗗ.�\�����I�敪���ȉ��̒l�ȊO�̏ꍇ�A��O���X���[����B
                 // "�{�\��"
                 // "���I"
                if (WEB3ConditionsValueDivDef.HAVE.equals(this.chgCustomerList[i].lotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI.equals(this.chgCustomerList[i].applyLotteryDiv)
                    && !WEB3SrvRegiAppliLotDivDef.DEFEAT.equals(this.chgCustomerList[i].applyLotteryDiv))
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01022,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("���I�L �\�����I�敪 �{�\��,���I�@@�ȊO", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }

                //2-18) this.���I�敪!="��"�ł���A����this.�\����==null�̏ꍇ�A��O���X���[����B
                if (!WEB3ConditionsValueDivDef.HAVE_NOT.equals(this.chgCustomerList[i].lotteryDiv)
                    && this.chgCustomerList[i].applyDate == null)
                {
                    WEB3BaseException l_e = new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00847,
                        getClass().getName() + STR_METHOD_NAME);
                    log.debug("���I�敪 �\����", l_e);
                    log.exiting(STR_METHOD_NAME);
                    throw l_e;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
