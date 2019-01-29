head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.05.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���q�l���������(WEB3AccInfoAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 ����� (���u) �V�K�쐬
Revesion History : 2005/12/23 �A���� (���u) �d�l�ύXNo.073
Revesion History : 2006/02/03 ���ہi���{���u�j�d�l�ύXNo.085
Revesion History : 2006/10/30 ��іQ (���u) ���f��134
Revesion History : 2007/03/08 �g��i (���u) �d�l�ύX�Ǘ�No.208
Revesion History : 2007/07/13 ���g (���u) �d�l�ύX�Ǘ�No.219
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���q�l���������)<BR>
 * ���q�l��������񃁃b�Z�[�W<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3AccInfoAccountInfo extends Message
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoAccountInfo.class);

    /**
     * (���Z�@@�֖���)<BR>
     * ���Z�@@�֖���<BR>
     */
    public String financialInstitutionName;

    /**
     * (�x�X�R�[�h)<BR>
     * �x�X�R�[�h<BR>
     */
    public String financialBranchCode;

    /**
     * (�x�X��)<BR>
     * �x�X��<BR>
     */
    public String financialBranchName;

    /**
     * (������ދ敪)<BR>
     * ������ދ敪<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F����<BR>
     */
    public String financialAccountType;

    /**
     * (������ޖ�)<BR>
     * ������ޖ�<BR>
     * <BR>
     * ���h���ʗa���h�C�h�����a���h���̕�����<BR>
     */
    public String financialAccountTypeName;

    /**
     * (�����ԍ�)<BR>
     * �����ԍ�<BR>
     */
    public String financialAccountCode;

    /**
     * (�������`�l)<BR>
     * �������`�l<BR>
     */
    public String financialAccountName;

    /**
     * (�U����o�^�敪)<BR>
     * �U����o�^�敪<BR>
     * <BR>
     * 0:���o�^�@@1:�o�^�ρ@@9:�Y���f�[�^�Ȃ�<BR>
     */
    public String bankAccountRegi;

    /**
     * (���Z�@@�փR�[�h)<BR>
     * ���Z�@@�փR�[�h<BR>
     */
    public String financialInstitutionCode;

    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;

    /**
     * (�U�֋敪)<BR>
     * �U�֋敪<BR>
     * <BR>
     * 1:��s�U���@@5:�X�֐U���@@<BR>
     */
    public String transferDiv;

    /**
     * (�U����敪)<BR>
     * �U����敪<BR>
     * ���U�֋敪1�̏ꍇ<BR>
     * �@@1�F���s���X����<BR>
     * �@@2�F���s<BR>
     * �@@4�F���s<BR>
     * �@@5�F���s���X�L��<BR>
     * �@@9�F���̑�<BR>
     * ���U�֋敪5�̏ꍇ<BR>
     * �@@5�F����<BR>
     */
    public String transferAccountDiv;

    /**
     * (�戵�敪)<BR>
     * �戵�敪<BR>
     * 1�F�d�M<BR>
     * 2�F����<BR>
     */
    public String tradeHandleDiv;

    /**
     * @@roseuid 418F39EE004E
     */
    public WEB3AccInfoAccountInfo()
    {

    }

    /**
     * (set������ޖ�)<BR>
     * �a���敪�i���j��������ޖ��ɕύX���ăZ�b�g����B<BR>
     * <BR>
     * ���U������Z�@@�փe�[�u��.�a���敪<BR>
     * <BR>
     * �|�i�a���敪 == 1�F���ʗa���j�̏ꍇ�A<BR>
     * �h���ʗa���h��this.������ޖ��ɃZ�b�g����B<BR>
     * �|�i�a���敪 == 2�F�����a���j�̏ꍇ�A<BR>
     * �h�����a���h��this.������ޖ��ɃZ�b�g����B<BR>
     * �|�i�a���敪 == 3�F���̑��j�̏ꍇ�A<BR>
     * �h���̑��h��this.������ޖ��ɃZ�b�g����B<BR>
     * �|�i�a���敪 == 4�F���~�a���j�̏ꍇ�A<BR>
     * �h���~�a���h��this.������ޖ��ɃZ�b�g����B<BR>
     * <BR>
     * @@param l_strFinSaveDiv - �a���敪
     *
     * 1�F���ʗa���@@2�F�����a���@@3�F���̑��@@4�F���~�a��
     *
     * ���U������Z�@@�փe�[�u��.�a���敪
     * @@roseuid 415CD497003C
     */
    public void setFinancialAccountTypeName(String l_strFinSaveDiv)
    {
        final String STR_METHOD_NAME = " setFinancialAccountTypeName(String)";
        log.entering(STR_METHOD_NAME);


        if (WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strFinSaveDiv))
        {
            //�i�a���敪 == 1�F���ʗa���j�̏ꍇ�A�h���ʗa���h��this.������ޖ��ɃZ�b�g����B
            this.financialAccountTypeName = "���ʗa��";
        }
        else if (WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strFinSaveDiv))
        {
            //�i�a���敪 == 2�F�����a���j�̏ꍇ�A�h�����a���h��this.������ޖ��ɃZ�b�g����B
            this.financialAccountTypeName = "�����a��";
        }
        else if (WEB3FinSaveDivDef.OTHER.equals(l_strFinSaveDiv))
        {
            //�i�a���敪 == 3�F���̑��j�̏ꍇ�A�h���̑��h��this.������ޖ��ɃZ�b�g����B
            this.financialAccountTypeName = "���̑�";
        }
        else if (WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strFinSaveDiv))
        {
            //�i�a���敪 == 4�F���~�a���j�̏ꍇ�A�h���~�a���h��this.������ޖ��ɃZ�b�g����B
            this.financialAccountTypeName = "���~�a��";
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * <BR>
     * �P�j�@@���Z�@@�փR�[�h�̃`�F�b�N<BR>
     * �@@�@@�P�|�P�j�@@�����͂̏ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02341<BR>
     * �@@�@@�P�|�R�j�@@��������4byte���傫���ꍇ�́A��O���X���[����<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02342<BR>
     * <BR>
     * �Q�j�@@���Z�@@�֖��̂̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@�S�p�����idouble byte charactor�j<BR>
     * �ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01097<BR>
     * �@@�Q�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01098<BR>
     * <BR>
     * �R�j�@@�x�X�R�[�h�̃`�F�b�N<BR>
     * �@@�R�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01099<BR>
     * �@@�R�|�Q�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01100<BR>
     * <BR>
     * �S�j�@@�x�X���̃`�F�b�N<BR>
     * �@@�S�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01101<BR>
     * �@@�S�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01102<BR>
     * <BR>
     * �T�j�@@������ޖ��̃`�F�b�N<BR>
     * �@@�T�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01103<BR>
     * �@@�T�|�Q�j�@@��������5�i10byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01104<BR>
     * <BR>
     * �U�j�@@�����ԍ��̃`�F�b�N<BR>
     * �@@�U�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�i�������Anull�l�͏����j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01105<BR>
     * �@@�U�|�Q�j�@@��������7byte���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01106<BR>
     * <BR>
     * �V�j�@@�������`�l�̃`�F�b�N<BR>
     * �@@�V�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A<BR>
     * ��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01107<BR>
     * �@@�V�|�Q�j�@@��������20�i40byte�j���傫���ꍇ�́A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01108<BR>
     * @@throws WEB3BaseException
     * @@roseuid 415CDA300210
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //  �P�j�@@���Z�@@�փR�[�h�̃`�F�b�N

        //  �P�|�P�j�@@�����͂̏ꍇ�́A��O���X���[����B
        if (this.financialInstitutionCode == null || this.financialInstitutionCode.length() == 0 )
        {
            log.debug("��s�R�[�h�������͂ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02346,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "��s�R�[�h�������͂ł��B");
        }

        //  �P�|�Q�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.financialInstitutionCode))
        {
            log.debug("���Z�@@萃R�[�h�������ȊO�̒l�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02341,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Z�@@萃R�[�h�������ȊO�̒l�ł��B");
        }

        //  �P�|�R�j�@@��������4byte���傫���ꍇ�́A��O���X���[����
        if (WEB3StringTypeUtility.getByteLength(this.financialInstitutionCode) > 4)
        {
            log.debug("���Z�@@萃R�[�h�̕��������s���ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02342,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���Z�@@萃R�[�h�̕��������s���ł��B");
        }

        //�Q�j�@@���Z�@@�֖��̂̃`�F�b�N

        if (this.financialInstitutionName != null)
        {
            //�Q�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����B
            if (!WEB3StringTypeUtility.isMulti(this.financialInstitutionName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01097,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���Z�@@�֖��̑S�p�����idouble byte charactor�j�ȊO���܂܂��");
            }

            //�Q�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.financialInstitutionName) > 32)
            {
                log.debug("[���Z�@@�֖��̂̕�����] = " + WEB3StringTypeUtility.getByteLength(this.financialInstitutionName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01098,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "���Z�@@�֖��̕�������16�i32byte�j���傫���ꍇ");
            }
        }

        //�R�j�@@�x�X�R�[�h�̃`�F�b�N
        if (this.financialBranchCode != null)
        {
            //�R�|�P�j�@@�x�X�R�[�h�́A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.financialBranchCode))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01099,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�x�X�R�[�h�x�X�R�[�h");
            }

            //�R�|�Q�j�@@��������3byte�łȂ��ꍇ�́A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.financialBranchCode) != 3)
            {
                log.debug("[�x�X�R�[�h�̕�����] = " + WEB3StringTypeUtility.getByteLength(this.financialBranchCode));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01100,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�x�X�R�[�h��������3byte�łȂ�");
            }
        }

        //�S�j�@@�x�X���̃`�F�b�N
        if (this.financialBranchName != null)
        {
            //�S�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����B
            if (!WEB3StringTypeUtility.isMulti(this.financialBranchName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01101,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�x�X���S�p�����idouble byte charactor�j�ȊO���܂܂��");
            }

            //�S�|�Q�j�@@��������16�i32byte�j���傫���ꍇ�́A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.financialBranchName) > 32)
            {
                log.debug("[�x�X���̕�����] = " + WEB3StringTypeUtility.getByteLength(this.financialBranchName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01102,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�x�X����������16�i32byte�j���傫���ꍇ");
            }
        }

        //�T�j�@@������ޖ��̃`�F�b�N
        if (this.financialAccountTypeName != null)
        {
            //�T�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����B
            if (!WEB3StringTypeUtility.isMulti(this.financialAccountTypeName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01103,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "������ޖ��S�p�����idouble byte charactor�j�ȊO���܂܂��");
            }

            //�T�|�Q�j�@@��������5�i10byte�j���傫���ꍇ�́A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.financialAccountTypeName) > 10)
            {
                log.debug("[������ޖ��̕�����] = " + WEB3StringTypeUtility.getByteLength(this.financialAccountTypeName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01104,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "������ޖ���������5�i10byte�j���傫��");
            }
        }

        //�U�j�@@�����ԍ��̃`�F�b�N
        if (this.financialAccountCode != null)
        {
            //�U�|�P�j�@@���p�����ȊO���܂܂��ꍇ�́A��O���X���[����B
            //�i�������Anull�l�͏����j
            if (!WEB3StringTypeUtility.isDigit(this.financialAccountCode))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01105,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ԍ����p�����ȊO���܂܂��");
            }

            //�U�|�Q�j�@@��������7byte���傫���ꍇ�́A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.financialAccountCode) > 7)
            {
                log.debug("[�����ԍ��̕�����] = " + WEB3StringTypeUtility.getByteLength(this.financialAccountCode));
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01106,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�����ԍ���������7byte���傫��");
            }
        }

        //�V�j�@@�������`�l�̃`�F�b�N
        if (this.financialAccountName != null)
        {
            //�V�|�P�j�@@�S�p�����idouble byte charactor�j�ȊO���܂܂��ꍇ�́A��O���X���[����B
            if (!WEB3StringTypeUtility.isMulti(this.financialAccountName))
            {
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01107,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�������`�l�S�p�����idouble byte charactor�j�ȊO���܂܂��");
            }

            //�V�|�Q�j�@@��������20�i40byte�j���傫���ꍇ�́A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.financialAccountName) > 40)
            {
                log.debug("[�������`�l�̕�����] = " + WEB3StringTypeUtility.getByteLength(this.financialAccountName));
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01108,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "�������`�l��������20�i40byte�j���傫��");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
