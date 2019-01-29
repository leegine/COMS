head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPPaymentRequisitionManageServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������Ǘ��T�[�r�XImpl(WEB3TPPaymentRequisitionManageServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/20 ������ (���u) �d�l�ύX���f��309,310,318,337,339,341
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionManagement;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.define.WEB3AdditionalGenerationStateDivDef;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.tradingpower.define.WEB3TPShortfallGenerationStateDivDef;
import webbroker3.tradingpower.service.delegate.WEB3TPPaymentRequisitionManageService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;

/**
 * (���������Ǘ��T�[�r�XImpl)<BR>
 * (���������Ǘ��T�[�r�XImpl)<BR>
 * <BR>
 * ���������Ǘ��C���^�[�t�F�C�X�̎����N���X<BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3TPPaymentRequisitionManageServiceImpl implements WEB3TPPaymentRequisitionManageService
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionManageServiceImpl.class);

    /**
     * @@roseuid 48F5856500AB
     */
    public WEB3TPPaymentRequisitionManageServiceImpl()
    {

    }

    /**
     * (get�s����������)<BR>
     * (get�s����������)<BR>
     * <BR>
     * �s�������������Ă��邩������s���A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ��T�[�r�X)get�s���������󋵁v�Q��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@return String
     * @@roseuid 487E92A7012
     * @@throws WEB3BaseException
     */
    public String getLackCashOccurSituation(MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getLackCashOccurSituation(MainAccount)";
        log.entering(STR_METHOD_NAME);

        //���������Ǘ��I�u�W�F�N�g�𐶐�����B
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //�s�����̔������ʂ��擾����B
        boolean l_blnIsShortfallGeneration =
            l_paymentRequisitionManagement.isShortfallGeneration();

        if(l_blnIsShortfallGeneration)
        {
            //get�M�p��������t���O
            String l_strMarginEquityFlag =
                l_paymentRequisitionManagement.getMarginEquityJudgeFlag();

            //(*)����t���|
            //get�M�p��������t���O()�̖߂�l�@@==�@@"1"(�M�p�ڋq) �̏ꍇ
            if (WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT.equals(l_strMarginEquityFlag))
            {
                //�h2�h(�s��������<�M�p�ڋq>)��ԋp
                log.exiting(STR_METHOD_NAME);
                return WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_ACCOUNT;
            }

            //(*)����t���|
            //get�M�p��������t���O()�̖߂�l�@@==�@@"0"(�����ڋq) �̏ꍇ
            if (WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT.equals(l_strMarginEquityFlag))
            {
                //�h1�h(�s��������<�����ڋq>)��ԋp
                log.exiting(STR_METHOD_NAME);
                return WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_ACCOUNT;
            }
        }

        //�h0�h(�s����������)��ԋp
        log.exiting(STR_METHOD_NAME);
        return WEB3TPShortfallGenerationStateDivDef.SHORTFALL_NOT_GENERATION;
    }

    /**
     * (get�Ǐؔ�����)<BR>
     * (get�Ǐؔ�����)<BR>
     * <BR>
     * �Ǐ؂��������Ă��邩������s���A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * �P�j�@@get�Ǐؔ����󋵁i�j���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * [����]<BR>
     * �E�ڋq�F�@@����.�ڋq<BR>
     * �E�����Ǐؔ����t���O�F�@@false<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@return String
     * @@roseuid 487EA526027C
     */
    public String getAdditionalMarginSituation(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        //�P�j�@@get�Ǐؔ����󋵁i�j���R�[�����A�߂�l��ԋp����B
        return this.getAdditionalMarginSituation(l_mainAccount, false);
    }

    /**
     * (get�Ǐؔ�����)<BR>
     * (get�Ǐؔ�����)<BR>
     * <BR>
     * �Ǐ؂��������Ă��邩������s���A���茋�ʂ�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ��T�[�r�X)get�Ǐؔ����󋵁v�Q��<BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F((���������Ǘ��T�[�r�X)get�Ǐؔ�����) <BR>
     * ��̈ʒu�F((*)����t���[is�M�p�����J��()�̖߂�l = FALSE(�����ڋq)�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (�����Ǐؔ����l���t���O)<BR>
     * (�����Ǐؔ����l���t���O)<BR>
     * @@return String
     * @@roseuid 487EA526027C
     * @@throws WEB3BaseException
     */
    public String getAdditionalMarginSituation(
        MainAccount l_mainAccount, boolean l_blnFirstOpenAdddepositFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdditionalMarginSituation(MainAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        //true�F�@@�J�ݍ�
        //false�F�@@���J��
        //[����]
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountEstablished =
            l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)����t���[
        //is�M�p�����J��()�̖߂�l = false(�����ڋq)
        if (!l_blnIsMarginAccountEstablished)
        {
            log.debug("�M�p�����J�݂Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�M�p�����J�݂Ȃ��B");
        }

        //���������Ǘ��I�u�W�F�N�g�𐶐�����B
        //[����]
        //�ڋq�F�@@����.�ڋq
        //�����Ǐؔ����l���t���O�F�@@����.�����Ǐؔ����l���t���O
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(
                l_gentradeMainAccount, l_blnFirstOpenAdddepositFlag);

        //��񐅏��Ǐ؂̔������ʂ��擾����B
        boolean l_blnIsSecondAdddeposit = l_paymentRequisitionManagement.isSecondAdddeposit();

        //(*)����t���|
        //is��񐅏��Ǐؔ���( )�̖߂�l�@@==�@@TRUE �̏ꍇ
        if(l_blnIsSecondAdddeposit)
        {
            //�h2�h(��񐅏��Ǐؔ���)��ԋp
            log.exiting(STR_METHOD_NAME);
            return WEB3AdditionalGenerationStateDivDef.SECOND_ADDITIONAL_GENERATION;
        }

        //��ꐅ���Ǐ؂̔������ʂ��擾����B
        boolean l_blnIsFirstAdddeposit = l_paymentRequisitionManagement.isFirstAdddeposit();

        //(*)����t���|
        //is��ꐅ���Ǐؔ���( )�̖߂�l�@@==�@@TRUE �̏ꍇ
        if (l_blnIsFirstAdddeposit)
        {
            //�h1�h(��ꐅ���Ǐؔ���)��ԋp
            log.exiting(STR_METHOD_NAME);
            return WEB3AdditionalGenerationStateDivDef.FIRST_ADDITIONAL_GENERATION;
        }

        //�h0�h(�Ǐؖ�����)��ԋp
        log.exiting(STR_METHOD_NAME);
        return WEB3AdditionalGenerationStateDivDef.ADDITIONAL_NOT_GENERATION;
    }

    /**
     * (get���������ڋq�ڍ׏��)<BR>
     * (get���������ڋq�ڍ׏��)<BR>
     * <BR>
     * ���������ڋq�ڍ׏��N���X�𐶐����A�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ��T�[�r�X)get���������ڋq�ڍ׏��v�Q��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@return WEB3TPPaymentRequisitionAccountDetailInfo
     * @@roseuid 4872C6020347
     * @@throws WEB3BaseException
     */
    public WEB3TPPaymentRequisitionAccountDetailInfo getPaymentRequisitionAccountDetailInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPaymentRequisitionAccountDetailInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);

        //���������Ǘ��I�u�W�F�N�g�𐶐�����B
        //[����]
        //�ڋq�F�@@����.�ڋq
        //�����Ǐؔ����l���t���O�F�@@true
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(
                l_mainAccount, true);

        //���������ڋq�ڍ׏��𐶐�����B
        WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
            WEB3TPPaymentRequisitionAccountDetailInfo.createPaymentRequisitionAccountDetailInfo(
                l_paymentRequisitionManagement);

        log.exiting(STR_METHOD_NAME);
        return l_paymentRequisitionAccountDetailInfo;
    }

    /**
     * (get�s�����������)<BR>
     * (get�s�����������)<BR>
     * <BR>
     * �s�����������N���X�𐶐����A�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ��T�[�r�X)get�s�����������v�Q��<BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@return WEB3TPShortfallGenerationInfo
     * @@roseuid 4872C6020343
     * @@throws WEB3BaseException
     */
    public WEB3TPShortfallOccurInfo getShortfallGenerationInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getShortfallGenerationInfo(MainAccount)";
        log.entering(STR_METHOD_NAME);

        //���������Ǘ��I�u�W�F�N�g�𐶐�����B
        //[����]
        //�ڋq�F�@@����.�ڋq
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(l_mainAccount);

        //�s�����������𐶐�����B
        //[����]
        //�E���������Ǘ��F�@@create���������Ǘ�()�̖߂�l
        WEB3TPShortfallOccurInfo l_shortfallGenerationInfo =
            WEB3TPShortfallOccurInfo.createShortfallGenerationInfo(l_paymentRequisitionManagement);

        log.exiting(STR_METHOD_NAME);
        return l_shortfallGenerationInfo;
    }

    /**
     * (get�Ǐؔ������)<BR>
     * (get�Ǐؔ������)<BR>
     * <BR>
     * �Ǐؔ������N���X�𐶐����A�ԋp����B<BR>
     * <BR>
     * �P�j�@@get�Ǐؔ������i�j���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * [����] <BR>
     * �E�ڋq�F�@@����.�ڋq  <BR>
     * �E�����Ǐؔ����l���t���O�F�@@false <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 4872C602034B
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(
        MainAccount l_mainAccount) throws WEB3BaseException
    {
        //�P�j�@@get�Ǐؔ������i�j���R�[�����A�߂�l��ԋp����B
        return this.getAdddepositGenerationInfo(l_mainAccount, false);
    }

    /**
     * (get�Ǐؔ������)<BR>
     * (get�Ǐؔ������)<BR>
     * <BR>
     * �Ǐؔ������N���X�𐶐����A�ԋp����B<BR>
     * <BR>
     * ���V�[�P���X�}�u(���������Ǘ��T�[�r�X)get�Ǐؔ������v�Q��<BR>
     * ======================================================== <BR>
     * �V�[�P���X�} �F((���������Ǘ��T�[�r�X)get�Ǐؔ������) <BR>
     * ��̈ʒu�F((*)����t���[is�M�p�����J��()�̖߂�l = FALSE(�����ڋq)�̏ꍇ)<BR>
     * class: WEB3BusinessLayerException <BR>
     * �@@tag: BUSINESS_ERROR_02887 <BR>
     * ========================================================== <BR>
     * @@param l_mainAccount - (�ڋq)<BR>
     * (�ڋq)<BR>
     * @@param l_blnFirstOpenAdddepositFlag - (�����Ǐؔ����l���t���O)<BR>
     * (�����Ǐؔ����l���t���O)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 4872C602034B
     * @@throws WEB3BaseException
     */
    public WEB3TPAdddepositGenerationInfo getAdddepositGenerationInfo(
        MainAccount l_mainAccount, boolean l_blnFirstOpenAdddepositFlag)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAdddepositGenerationInfo(MainAccount, boolean)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)l_mainAccount;

        //�M�p�������J�݂��Ă��邩�ǂ����̔��ʂ��s���B
        //true�F�@@�J�ݍ�
        //false�F�@@���J��
        //[����]
        //�ٍϋ敪�F�@@0(�w��Ȃ�)
        boolean l_blnIsMarginAccountEstablished =
            l_gentradeMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //(*)����t���[
        //is�M�p�����J��()�̖߂�l == false(�����ڋq)
        if (!l_blnIsMarginAccountEstablished)
        {
            log.debug("�M�p�����J�݂Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02887,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�M�p�����J�݂Ȃ��B");
        }

        //���������Ǘ��I�u�W�F�N�g�𐶐�����B
        //[����]
        //�ڋq�F�@@����.�ڋq
        //�����Ǐؔ����l���t���O�F�@@����.�����Ǐؔ����l���t���O
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
            WEB3TPPaymentRequisitionManagement.createPaymentRequisitionManagement(
                l_gentradeMainAccount, l_blnFirstOpenAdddepositFlag);

        //�Ǐؔ������𐶐�����B
        //[����]
        //���������Ǘ��F�@@create���������Ǘ�()�̖߂�l
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
            WEB3TPAdddepositGenerationInfo.createAdddepositGenerationInfo(l_paymentRequisitionManagement);

        //�Ǐؔ�������ԋp
        log.exiting(STR_METHOD_NAME);
        return l_adddepositGenerationInfo;
    }
}
@
