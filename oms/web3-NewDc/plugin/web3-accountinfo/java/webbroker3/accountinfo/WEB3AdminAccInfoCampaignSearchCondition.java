head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCampaignSearchCondition.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �L�����y�[����������(WEB3AdminAccInfoCampaignSearchCondition.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/01 � (���u) �V�K�쐬
Revision History : 2007/02/03 � (���u) ���f��No.173
Revision History : 2007/02/03 � (���u) ���f��No.177
Revision History : 2007/02/03 � (���u) ���f��No.183
Revision History : 2007/02/28 Inomata(SCS)���f��No.207
*/
package webbroker3.accountinfo;

import java.util.Date;

import webbroker3.accountinfo.message.WEB3AccInfoCampaignSearchCondition;
import webbroker3.util.WEB3LogUtility;

/**
 * (�L�����y�[����������)<BR>
 * �L�����y�[�����������I�u�W�F�N�g<BR>
 * <BR>
 * @@author  �
 * @@version 1.0
 */
public class WEB3AdminAccInfoCampaignSearchCondition
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCampaignSearchCondition.class);

    /**
     * �،���ЃR�[�h<BR>
     */
    protected String institutionCode;

    /**
     * ���X�R�[�h<BR>
     */
    protected String branchCode;

    /**
     * �ڋq�R�[�h<BR>
     */
    protected String accountCode;

    /**
     * ���i�R�[�h�z��<BR>
     */
    protected String[] itemCodes;

    /**
     * �L�����y�[������<BR>
     */
    protected String campaignName;

    /**
     * �Ώۓ�<BR>
     */
    protected Date targetDate;

    /**
     * �Ώۊ���From<BR>
     */
    protected String targetPeriodFrom;

    /**
     * �����J�ݓ�From<BR>
     */
    protected Date accountOpenDateFrom;

    /**
     * ������<BR>
     */
    protected String collectRate;

    /**
     * ���҃R�[�h<BR>
     */
    protected String traderCode;

    /**
     * �����J�݋敪<BR>
     */
    protected String accountOpenDiv;

    /**
     * �폜�t���O<BR>
     */
    protected String deleteFlag;

    /**
     * �萔�������L�����y�[������ID<BR>
     */
    protected String commissionCampaignConditionId;

    /**
     * �o�^�^�C�v�̔z��<BR>
     */
    protected String[] registerTypes;

    /**
     * @@roseuid 45C08B50007D
     */
    public WEB3AdminAccInfoCampaignSearchCondition()
    {
        this.resetCampaignCondition();
    }

    /**
     * (reset�L�����y�[����������_all)<BR>
     * �����̏��������s���B<BR>
     * <BR>
     * -this.set�萔�������L�����y�[������ID(null)<BR>
     * <BR>
     * -this.set�،���ЃR�[�h(null)<BR>
     * <BR>
     * -this.set���X�R�[�h(null)<BR>
     * <BR>
     * -this.set�ڋq�R�[�h(null)<BR>
     * <BR>
     * -this.set���i�R�[�h(null)<BR>
     * <BR>
     * -this.set�Ώۓ�(null)<BR>
     * <BR>
     * -this.set�Ώۊ���From(null)<BR>
     * <BR>
     * -this.set�����J��From(null)<BR>
     * <BR>
     * -this.set������(null)<BR>
     * <BR>
     * -this.set���҃R�[�h(null)<BR>
     * <BR>
     * -this.set�����J�݋敪(null)<BR>
     * <BR>
     * -this.set�폜�t���O(null)<BR>
     * <BR>
     * -this.set�L�����y�[������(null)<BR>
     * <BR>
     * -this.set�o�^�^�C�v(null)<BR>
     * <BR>
     * <BR>
     * @@roseuid 45B8356F001C
     */
    public void resetCampaignCondition()
    {
        final String STR_METHOD_NAME = " resetCampaignCondition() ";
        log.entering(STR_METHOD_NAME);

        //this.set�萔�������L�����y�[������ID(null)
        this.setCommissionCampaignConditionId(null);

        //this.set�،���ЃR�[�h(null)
        this.setInstitutionCode(null);

        //this.set���X�R�[�h(null)
        this.setBranchCode(null);

        //this.set�ڋq�R�[�h(null)
        this.setAccountCode(null);

        //this.set���i�R�[�h(null)
        this.setItemCode(null);

        //this.set�Ώۓ�(null)
        this.setTargetDate(null);

        //this.�Ώۊ���From(null)
        this.setTargetPeriodFrom(null);

        //this.set�����J��From(null)
        this.setAccountOpenDateFrom(null);

        //this.set������(null)
        this.setCollectRate(null);

        //this.set���҃R�[�h(null)
        this.setTraderCode(null);

        //this.set�����J�݋敪(null)
        this.setAccountOpenDiv(null);

        //this.set�폜�t���O(null)
        this.setDeleteFlag(null);

        //this.set�L�����y�[������((null)
        this.setCampaignName(null);

        //this.set�o�^�^�C�v(null)
        this.setRegisterType(null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�L�����y�[����������_all)<BR>
     * �����l�̈ꊇ�ݒ���s���B<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[������ID != null�̏ꍇ�A<BR>
     * -this.set�萔�������L�����y�[������ID(�i�����j�萔�������L�����y�[������ID)<BR>
     * <BR>
     * �i�����j�،���ЃR�[�h != null�̏ꍇ�A<BR>
     * -this.set�،���ЃR�[�h(�i�����j�،���ЃR�[�h)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.���X�R�[�h != null�̏ꍇ�A<BR>
     * -this.set���X�R�[�h(�i�����j�萔�������L�����y�[����������.���X�R�[�h)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.�ڋq�R�[�h!= null�̏ꍇ�A<BR>
     * -this.set�ڋq�R�[�h(�i�����j�萔�������L�����y�[����������.�ڋq�R�[�h)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.���i�R�[�h != null�̏ꍇ�A<BR>
     * --this.set���i�R�[�h(�i�����j�萔�������L�����y�[����������.<BR>
     * ���i�R�[�h��v�f�Ɏ������P��String�z��j<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.�Ώۓ� != null�̏ꍇ�A<BR>
     * -this.set�Ώۓ�(�i�����j�萔�������L�����y�[����������.�Ώۓ�)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.������ != null�̏ꍇ�A<BR>
     * -this.set������(�i�����j�萔�������L�����y�[����������.������)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.���҃R�[�h != null�̏ꍇ�A<BR>
     * -this.set���҃R�[�h(�i�����j�萔�������L�����y�[����������.���҃R�[�h)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.�����J�݋敪 != null�̏ꍇ�A<BR>
     * -this.set�����J�݋敪(�i�����j�萔�������L�����y�[����������.�����J�݋敪)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.�폜�t���O != null�̏ꍇ�A<BR>
     * -this.set�폜�t���O(�i�����j�萔�������L�����y�[����������.�폜�t���O)<BR>
     * <BR>
     * �i�����j�萔�������L�����y�[����������.�L�����y�[������ != null�̏ꍇ�A<BR>
     * -this.set�L�����y�[������(�i�����j�萔�������L�����y�[����������.�L�����y�[������)<BR>
     * <BR>
     * �i�����j�o�^�^�C�v != null�̏ꍇ�A<BR>
     * -this.set�o�^�^�C�v(�i�����j�o�^�^�C�v)<BR>
     * <BR>
     * <BR>
     * @@param l_campaignSearchCondition -
     *            �萔�������L�����y�[�����������I�u�W�F�N�g<BR>
     * @@param l_strCampaignSearchConditionId -
     *            �萔�������L�����y�[������ID<BR>
     * @@param l_strInstitutionCode -
     *            �،���ЃR�[�h<BR>
     * @@param l_strRegistTypes -
     *            �o�^�^�C�v�̔z��<BR>
     * @@roseuid 45B7070C007F
     */
    public void setCampaignCondition(WEB3AccInfoCampaignSearchCondition l_campaignSearchCondition,
            String l_strCampaignSearchConditionId,
            String l_strInstitutionCode,
            String[] l_strRegistTypes)
    {
        final String STR_METHOD_NAME = " setCampaignCondition(WEB3AccInfoCampaignSearchCondition, String, String, String) ";
        log.entering(STR_METHOD_NAME);

        //�i�����j�萔�������L�����y�[������ID != null�̏ꍇ�A
        if (l_strCampaignSearchConditionId != null)
        {
            this.setCommissionCampaignConditionId(l_strCampaignSearchConditionId);
        }
        //�i�����j�،���ЃR�[�h != null�̏ꍇ
        if (l_strInstitutionCode != null)
        {
            this.setInstitutionCode(l_strInstitutionCode);
        }

        if (l_campaignSearchCondition != null)
        {
            //�i�����j�萔�������L�����y�[����������.���X�R�[�h != null�̏ꍇ
            if (l_campaignSearchCondition.branchCode != null)
            {
                this.setBranchCode(l_campaignSearchCondition.branchCode);
            }
            //�i�����j�萔�������L�����y�[����������.�ڋq�R�[�h != null�̏ꍇ
            if (l_campaignSearchCondition.accountCode != null)
            {
                this.setAccountCode(l_campaignSearchCondition.accountCode);
            }
            //�i�����j�萔�������L�����y�[����������.���i�R�[�h  != null�̏ꍇ
            if (l_campaignSearchCondition.itemCode != null)
            {
                String[] l_strItemCodes = new String[1];
                l_strItemCodes[0] = l_campaignSearchCondition.itemCode;
                this.setItemCode(l_strItemCodes);
            }
            //�i�����j�萔�������L�����y�[����������.�Ώۓ�  != null�̏ꍇ
            if (l_campaignSearchCondition.targetDate != null)
            {
                this.setTargetDate(l_campaignSearchCondition.targetDate);
            }
            //�i�����j�萔�������L�����y�[����������.������ != null�̏ꍇ
            if (l_campaignSearchCondition.collectRate != null)
            {
                this.setCollectRate(l_campaignSearchCondition.collectRate);
            }
            //�i�����j�萔�������L�����y�[����������.���҃R�[�h  != null�̏ꍇ
            if (l_campaignSearchCondition.traderCode != null)
            {
                this.setTraderCode(l_campaignSearchCondition.traderCode);
            }
            //�i�����j�萔�������L�����y�[����������.�����J�݋敪  != null�̏ꍇ
            if (l_campaignSearchCondition.accountOpenDiv != null)
            {
                this.setAccountOpenDiv(l_campaignSearchCondition.accountOpenDiv);
            }
            //�i�����j�萔�������L�����y�[����������.�폜�t���O != null�̏ꍇ
            if (l_campaignSearchCondition.deleteFlag != null)
            {
                this.setDeleteFlag(l_campaignSearchCondition.deleteFlag);
            }
            //�i�����j�萔�������L�����y�[����������.�L�����y�[������ != null�̏ꍇ
            if (l_campaignSearchCondition.campaignName != null)
            {
                this.setCampaignName(l_campaignSearchCondition.campaignName);
            }
        }

        //�i�����j�o�^�^�C�v  != null�̏ꍇ�A
        if (l_strRegistTypes != null)
        {
            this.setRegisterType(l_strRegistTypes);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�L�����y�[������)<BR>
     * �L�����y�[�����̂̃Z�b�g���s���B<BR>
     * <BR>
     * this.�L�����y�[������ = �i�����j�L�����y�[������<BR>
     * <BR>
     * @@param l_strCampaignName -
     *            �L�����y�[������<BR>
     * @@roseuid 45B80E1B005E
     */
    public void setCampaignName(String l_strCampaignName)
    {
        final String STR_METHOD_NAME = " setCampaignName(String) ";
        log.entering(STR_METHOD_NAME);

        this.campaignName = l_strCampaignName;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���҃R�[�h)<BR>
     * ���҃R�[�h�̃Z�b�g���s���B<BR>
     * <BR>
     * this.���҃R�[�h = �i�����j���҃R�[�h<BR>
     * <BR>
     * @@param l_strTraderCode -
     *            ���҃R�[�h<BR>
     * @@roseuid 45B80E1A0291
     */
    public void setTraderCode(String l_strTraderCode)
    {
        final String STR_METHOD_NAME = " setTraderCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.traderCode = l_strTraderCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�ڋq�R�[�h = �i�����j�ڋq�R�[�h<BR>
     * <BR>
     * @@param l_strAccountCode -
     *            �ڋq�R�[�h<BR>
     * @@roseuid 45B80E1A010A
     */
    public void setAccountCode(String l_strAccountCode)
    {
        final String STR_METHOD_NAME = " setAccountCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.accountCode = l_strAccountCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����J�݋敪)<BR>
     * �����J�݋敪�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�����J�݋敪 = �i�����j�����J�݋敪<BR>
     * <BR>
     * @@param l_strAccountOpenDiv -
     *            �����J�݋敪<BR>
     * @@roseuid 45B80E1A031D
     */
    public void setAccountOpenDiv(String l_strAccountOpenDiv)
    {
        final String STR_METHOD_NAME = " setAccountOpenDiv(String) ";
        log.entering(STR_METHOD_NAME);

        this.accountOpenDiv = l_strAccountOpenDiv;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�폜�t���O)<BR>
     * �폜�t���O�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�폜�t���O = �i�����j�폜�t���O<BR>
     * <BR>
     * @@param l_strDeleteFlag -
     *            �폜�t���O<BR>
     * @@roseuid 45B80E280168
     */
    public void setDeleteFlag(String l_strDeleteFlag)
    {
        final String STR_METHOD_NAME = " setDeleteFlag(String) ";
        log.entering(STR_METHOD_NAME);

        this.deleteFlag = l_strDeleteFlag;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�萔�������L�����y�[������ID)<BR>
     * �萔�������L�����y�[������ID�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�萔�������L�����y�[������ID= �i�����j�萔�������L�����y�[������ID<BR>
     * <BR>
     * @@param l_strCommissionCampaignConditionId -
     *            �萔�������L�����y�[������ID<BR>
     * @@roseuid 45B80E280242
     */
    public void setCommissionCampaignConditionId(String l_strCommissionCampaignConditionId)
    {
        final String STR_METHOD_NAME = " setAccInfoCampaignConditionId(String) ";
        log.entering(STR_METHOD_NAME);

        this.commissionCampaignConditionId = l_strCommissionCampaignConditionId;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���i�R�[�h)<BR>
     * ���i�R�[�h�̃Z�b�g���s���B<BR>
     * <BR>
     * this.���i�R�[�h = �i�����j���i�R�[�h<BR>
     * <BR>
     * @@param l_strItemCodes -
     *            ���i�R�[�h�z��<BR>
     * @@roseuid 45B80E1A03AA
     */
    public void setItemCode(String[] l_strItemCodes)
    {
        final String STR_METHOD_NAME = " setItemCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.itemCodes = l_strItemCodes;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�،���ЃR�[�h= �i�����j�،���ЃR�[�h<BR>
     * <BR>
     * @@param l_strInstitutionCode -
     *            �،���ЃR�[�h<BR>
     * @@roseuid 45B8119B00A4
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        final String STR_METHOD_NAME = " setInstitutionCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.institutionCode = l_strInstitutionCode;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�Ώۓ�)<BR>
     * �Ώۓ��̃Z�b�g���s���B<BR>
     * <BR>
     * this.�Ώۓ�= �i�����j�Ώۓ�<BR>
     * <BR>
     * @@param l_datTargetDate -
     *            �Ώۓ�<BR>
     * @@roseuid 45B80E1B00EB
     */
    public void setTargetDate(Date l_datTargetDate)
    {
        final String STR_METHOD_NAME = " setTargetDate(Date) ";
        log.entering(STR_METHOD_NAME);

        this.targetDate = l_datTargetDate;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�Ώۊ���From)<BR>
     * �Ώۊ���From�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�Ώۊ���From = �i�����j�Ώۊ���From<BR>
     * <BR>
     * @@param l_datTargetPeriodFrom -
     *            �Ώۊ���From<BR>
     * @@roseuid 45B80E1A01D5
     */

    public void setTargetPeriodFrom(String l_datTargetPeriodFrom)
    {
        final String STR_METHOD_NAME = " setTargetPeriodFrom(Date) ";
        log.entering(STR_METHOD_NAME);

        this.targetPeriodFrom = l_datTargetPeriodFrom;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�����J�ݓ�From)<BR>
     * �����J�ݓ�From�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�����J�ݓ�From= �i�����j�����J�ݓ�From<BR>
     * <BR>
     * @@param l_datAccountOpenDateFrom -
     *            �����J�ݓ�From<BR>
     * @@roseuid 45B8115B0065
     */
    public void setAccountOpenDateFrom(Date l_datAccountOpenDateFrom)
    {
        final String STR_METHOD_NAME = " setAccountOpenDateFrom(Date) ";
        log.entering(STR_METHOD_NAME);

        this.accountOpenDateFrom = l_datAccountOpenDateFrom;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set������)<BR>
     * �������̃Z�b�g���s���B<BR>
     * <BR>
     * this.������ = �i�����j������<BR>
     * <BR>
     * @@param l_strCollectRate -
     *            ������<BR>
     * @@roseuid 45B80E1B0177
     */
    public void setCollectRate(String l_strCollectRate)
    {
        final String STR_METHOD_NAME = " setCollectRate(String) ";
        log.entering(STR_METHOD_NAME);

        this.collectRate = l_strCollectRate;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�o�^�^�C�v)<BR>
     * �o�^�^�C�v�̃Z�b�g���s���B<BR>
     * <BR>
     * this.�o�^�^�C�v[] = �i�����j�o�^�^�C�v[]<BR>
     * <BR>
     * @@param l_strRegisterTypes -
     *            �o�^�^�C�v�̔z��<BR>
     * @@roseuid 45BD5855038C
     */
    public void setRegisterType(String[] l_strRegisterTypes)
    {
        final String STR_METHOD_NAME = " setRegisterType(String[]) ";
        log.entering(STR_METHOD_NAME);

        this.registerTypes = l_strRegisterTypes;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h�̃Z�b�g���s���B<BR>
     * <BR>
     * this.���X�R�[�h = �i�����j���X�R�[�h<BR>
     * <BR>
     * @@param l_strBranchCode -
     *            ���X�R�[�h<BR>
     * @@roseuid 45B80DB1001F
     */
    public void setBranchCode(String l_strBranchCode)
    {
        final String STR_METHOD_NAME = " setBranchCode(String) ";
        log.entering(STR_METHOD_NAME);

        this.branchCode = l_strBranchCode;

        log.exiting(STR_METHOD_NAME);
    }
}
@
