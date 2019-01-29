head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPAdddepositGenerationInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǐؔ������(WEB3TPAdddepositGenerationInfo.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/15 �И��� (���u) �V�K�쐬 �d�l�ύX���f��307,315,317
*/
package webbroker3.tradingpower;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǐؔ������)<BR>
 * (�Ǐؔ������)<BR>
 * <BR>
 * �Ǐؔ��������i�[����N���X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public class WEB3TPAdddepositGenerationInfo
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPAdddepositGenerationInfo.class);

    /**
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * false�F�ۏ؋������U�֑O<BR>
     * true �F�ۏ؋������U�֌�<BR>
     */
    protected boolean depositAutoTransferDivFlag = false;

    /**
     * (�Ǐ؏��)<BR>
     * (�Ǐ؏��)<BR>
     */
    protected WEB3TPAdddepositInfo adddepositInfo = null;

    /**
     * @@roseuid 48F5856301D4
     */
    public WEB3TPAdddepositGenerationInfo()
    {

    }

    /**
     * (get�ۏ؋������U�֌㔻��t���O)<BR>
     * (get�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * this.�ۏ؋������U�֌㔻��t���O��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 48C76BD801AC
     */
    public boolean getDepositAutoTransferDivFlag()
    {
        return this.depositAutoTransferDivFlag;
    }

    /**
     * (set�ۏ؋������U�֌㔻��t���O)<BR>
     * (set�ۏ؋������U�֌㔻��t���O)<BR>
     * <BR>
     * ����.�ۏ؋������U�֌㔻��t���O��this.�ۏ؋������U�֌㔻��t���O�ɃZ�b�g����B<BR>
     * @@param l_blnIsDepositAutoTransferDivFlag - (�ۏ؋������U�֌㔻��t���O)<BR>
     * (�ۏ؋������U�֌㔻��t���O)<BR>
     * @@roseuid 48C76BD801BC
     */
    public void setDepositAutoTransferDivFlag(boolean l_blnIsDepositAutoTransferDivFlag)
    {
        this.depositAutoTransferDivFlag = l_blnIsDepositAutoTransferDivFlag;
    }

    /**
     * (get�Ǐ؏��)<BR>
     * (get�Ǐ؏��)<BR>
     * <BR>
     * this.�Ǐ؏���ԋp����B<BR>
     * @@return WEB3TPAdddepositInfo
     * @@roseuid 48C76D5F00DC
     */
    public WEB3TPAdddepositInfo getAdddepositInfo()
    {
        return this.adddepositInfo;
    }

    /**
     * (set�Ǐ؏��)<BR>
     * (set�Ǐ؏��)<BR>
     * <BR>
     * ����.�Ǐ؏���this.�Ǐ؏��ɃZ�b�g����B<BR>
     * @@param l_adddepositInfo - (�Ǐ؏��)<BR>
     * (�Ǐ؏��)<BR>
     * @@roseuid 48C76D5F00FB
     */
    public void setAdddepositInfo(WEB3TPAdddepositInfo l_adddepositInfo)
    {
        this.adddepositInfo = l_adddepositInfo;
    }

    /**
     * (create�Ǐؔ������)<BR>
     * (static���\�b�h)(create�Ǐؔ������)<BR>
     * <BR>
     * �Ǐؔ������C���X�^���X���쐬���� <BR>
     * <BR>
     * ���V�[�P���X�}�u(�Ǐؔ������)create�Ǐؔ������v�Q��<BR>
     * @@param l_paymentRequisitionManagement - (���������Ǘ�)<BR>
     * (���������Ǘ�)<BR>
     * @@return WEB3TPAdddepositGenerationInfo
     * @@roseuid 48EC5C3202E4
     * @@throws WEB3BaseException
     */
    public static WEB3TPAdddepositGenerationInfo createAdddepositGenerationInfo(
        WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createAdddepositGenerationInfo(WEB3TPPaymentRequisitionManagement)";
        log.entering(STR_METHOD_NAME);

        if (l_paymentRequisitionManagement == null)
        {
            log.debug("�p�����[�^�l�s���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                "WEB3TPAdddepositGenerationInfo." + STR_METHOD_NAME,
                "�p�����[�^�l�s���B");
        }

        //�Ǐؔ������𐶐�����B
        WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo = new WEB3TPAdddepositGenerationInfo();

        //�ۏ؋������U�֌㔻��t���O���擾����B
        boolean l_blnDepositAutoTransferDivFlag =
            l_paymentRequisitionManagement.isDepositAutoTransferDivFlag();

        //set�ۏ؋������U�֌㔻��t���O
        //�擾�����ۏ؋������U�֌㔻��t���O���Z�b�g����B
        //[����]
        //�ۏ؋������U�֌㔻��t���O�F�@@is�ۏ؋������U�֌㔻��t���O()�̖߂�l
        l_adddepositGenerationInfo.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);

        //(create��񐅏��Ǐ؏��)
        //��񐅏��Ǐ؏����擾����B
        //[����]
        //�E���������Ǘ��F�@@����.���������Ǘ�
        WEB3TPSecondAdddepositInfo l_secondAdddepositInfo =
            WEB3TPSecondAdddepositInfo.createSecondAdddepositInfo(l_paymentRequisitionManagement);

        //(set�Ǐ؏��)
        //����������񐅏��Ǐ؏����Z�b�g����B
        //[����]
        //�E�Ǐ؏��F�@@create��񐅏��Ǐ؏��()�̖߂�l
        l_adddepositGenerationInfo.setAdddepositInfo(l_secondAdddepositInfo);

        //create��񐅏��Ǐ؏��()�̖߂�l == NULL
        if (l_secondAdddepositInfo == null)
        {
            //(create��ꐅ���Ǐ؏��)
            //��ꐅ���Ǐ؏����擾����B
            //[����]
            //�E���������Ǘ��F�@@����.���������Ǘ�
            WEB3TPFirstAdddepositInfo l_firstAdddepositInfo =
                WEB3TPFirstAdddepositInfo.createFirstAdddepositInfo(l_paymentRequisitionManagement);

            //(set�Ǐ؏��)
            //����������ꐅ���Ǐ؏����Z�b�g����B
            //[����]
            //�E�Ǐ؏��F�@@create��ꐅ���Ǐ؏��()�̖߂�l
            l_adddepositGenerationInfo.setAdddepositInfo(l_firstAdddepositInfo);
        }

        log.exiting(STR_METHOD_NAME);
        return l_adddepositGenerationInfo;
    }
}
@
