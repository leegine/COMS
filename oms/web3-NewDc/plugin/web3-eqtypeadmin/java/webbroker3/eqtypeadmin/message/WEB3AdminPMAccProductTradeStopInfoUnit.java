head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminPMAccProductTradeStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�����ʎ����~���N���X (WEB3AdminPMAccProductTradeStopInfoUnit.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * �i�ڋq�����ʎ����~���N���X�j<BR>
 * <BR>
 * �ڋq�����ʎ����~���N���X<BR>
 * <BR>
 * WEB3AdminPMAccProductTradeStopInfoUnit<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminPMAccProductTradeStopInfoUnit extends Message
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMAccProductTradeStopInfoUnit.class);

    /**
     * �i���X�R�[�h�j<BR>
     * <BR>
     * ���X�R�[�h<BR>
     * <BR>
     * branchCode<BR>
     * <BR>
     */
    public String branchCode;

    /**
     * �i�ڋq�R�[�h�j<BR>
     * <BR>
     * �ڋq�R�[�h<BR>
     * <BR>
     * accountCode<BR>
     * <BR>
     */
    public String accountCode;

    /**
     * �i�ڋq���j<BR>
     * <BR>
     * �ڋq��<BR>
     * <BR>
     * ���ꗗ�@@�\�ɂĎg�p�B<BR>
     * <BR>
     * accountName<BR>
     * <BR>
     * ��It is used for list functions<BR>
     * <BR>
     */
    public String accountName = null;

    /**
     * �i�����R�[�h�j<BR>
     * <BR>
     * �����R�[�h<BR>
     * <BR>
     * ���w��Ȃ��̏ꍇ��null���Z�b�g�B<BR>
     * <BR>
     * productCode<BR>
     * <BR>
     * ��Set null if no specification<BR>
     * <BR>
     */
    public String productCode;

    /**
     * �i�������j<BR>
     * <BR>
     * ������<BR>
     * <BR>
     * ���ꗗ�@@�\�ɂĎg�p�B
     * <BR>
     * productName<BR>
     * <BR>
     * It is used for list functions<BR>
     * <BR>
     */
    public String productName = null;

    /**
     * �i�L������From�j<BR>
     * <BR>
     * �L������From<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * expirationStartDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String expirationStartDate = null;

    /**
     * �i�L������To�j<BR>
     * <BR>
     * �L������To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * expirationEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     */
    public String expirationEndDate = null;

    /**
     * �i�ύX��̗L������To�j<BR>
     * <BR>
     * �ύX��̗L������To<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ���ύX�@@�\�ɂĎg�p�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftChangeExpirationEndDate<BR>
     * (YYYYMMDD)<BR>
     * <BR>
     * ��It is used for change functions<BR>
     * <BR>
     */
    public String aftChangeExpirationEndDate = null;

    /**
     * �i���R�j<BR>
     * <BR>
     * ���R<BR>
     * <BR>
     * reason<BR>
     * <BR>
     */
    public String reason = null;

    /**
     * �i�ύX�㗝�R�j<BR>
     * <BR>
     * �ύX��̗��R<BR>
     * <BR>
     * ���ύX�@@�\�ɂĎg�p�B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * aftChangeReason<BR>
     * <BR>
     * ��It is used for change functions<BR>
     * <BR>
     */
    public String aftChangeReason = null;

    /**
     * �i�ڋq�����~���ꗗ�j
     */
    public WEB3AdminPMAccTradeStopInfoUnit[] accTradeStopInfoList;

    /**
     * @@roseuid 41FD931001D4
     */
    public WEB3AdminPMAccProductTradeStopInfoUnit()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j���X�R�[�h�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���X�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     * �@@�P�|�Q�jths.���X�R�[�h���ȉ��̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���X�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�Ethis.���X�R�[�h.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * �Q�j�ڋq�R�[�h�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.�ڋq�R�[�h == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�R�[�h��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * �@@�Q�|�Q�jths.�ڋq�R�[�h���ȉ��̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�Ethis.�ڋq�R�[�h.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * �R�j�����R�[�h�`�F�b�N<BR>
     * �@@this.�����R�[�h != null�̏ꍇ�A�ȉ��`�F�b�N���s���B<BR>
     * �@@�R�|�P�jthis.�����R�[�h���ȉ��̂��Âꂩ�ɊY������ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�����R�[�h�G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h != ���l<BR>
     * �@@�@@�@@�@@�@@�Ethis.�����R�[�h.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * �S�j�L������From�`�F�b�N<BR>
     * �@@this.�L������From != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�S�|�P�jthis.�L������From�����t�^�ɕϊ��ł��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�L������From�G���[(���݂��Ȃ����t)�v�̗�O��<BR>
     * �@@�@@�@@�@@�X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * �T�j�L������To�`�F�b�N <BR>
     * �@@this.�L������To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�T�|�P�jthis.�L������To�����t�^�ɕϊ��ł��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�L������To�G���[(���݂��Ȃ����t)�v�̗�O��<BR>
     * �@@�@@�@@�@@�X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * �U�j�ύX��L������To�`�F�b�N <BR>
     * �@@this.�ύX��L������To != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�U�|�P�jthis.�L������To�����t�^�ɕϊ��ł��Ȃ��ꍇ�́A<BR>
     * �@@�@@�@@�@@�u�L������To�G���[(���݂��Ȃ����t)�v�̗�O��<BR>
     * �@@�@@�@@�@@�X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * �V�j�L������From/To�������`�F�b�N<BR>
     * �@@this.�L������From != null�@@���@@this.�L������To != null�̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�V�|�P�jthis.�L������From > this.�L������To�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�L�������������G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@��Date�^�ɕϊ����Ĕ�r���邱�ƁB<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * �W�j�L������From/�ύX��L������To�������`�F�b�N<BR>
     * �@@this.�L������From != null�@@���@@this.�ύX��L������To != null�̏ꍇ�A<BR>
     * �@@�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�W�|�P�jthis.�L������From > this.�ύX��L������To�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�L�������������G���[�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�@@��Date�^�ɕϊ����Ĕ�r���邱�ƁB<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * �X�j���R�`�F�b�N<BR>
     * �@@this.���R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�X�|�P�jthis.���R.length > 50�ł���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͗��R�G���[(��������)�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * �P�O�j�ύX�㗝�R�`�F�b�N<BR>
     * �@@this.�ύX�㗝�R != null�̏ꍇ�A�ȉ��̃`�F�b�N���s���B<BR>
     * �@@�P�O�|�P�jthis.�ύX�㗝�R.length > 50�ł���ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u���͗��R�G���[(��������)�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>      
     * �P�P�j�ڋq�����~���ꗗ�`�F�b�N<BR>
     * �@@�P�P�|�P�jthis.�ڋq�����~���ꗗ == null�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�����~���ꗗ��null�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01436<BR>
     * <BR>
     * �@@�P�P�|�Q�jthis.�ڋq�����~���ꗗ.length == 0�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�@@�u�ڋq�����~���ꗗ�̗v�f����0�v�̗�O���X���[����B<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01437<BR>
     * <BR>
     * �@@�P�P�|�R�jthis.�ڋq�����~���ꗗ.length���ȉ��̏������J��Ԃ��B<BR>
     * �@@�@@�@@�@@�P�P�|�R�|�P�j�ڋq�����~���.validate()���R�[������B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * Check l_request<BR>
     * (However, it is assumed only when the simple check concluded in this class).
     * <BR>
     * <BR>
     * 1)branchCode check<BR>
     *  1-1) If this.branchCode == null.<BR>
     *         Throw the exception of "branchCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00833<BR>
     * <BR>
     *  1-2) If this.branchCode meets with the following conditions,<BR>
     *         Throw the exception "branchCode error"<BR>
     *         �Ethis.branchCode  != numerical value<BR>
     *         �Ethis.branchCode.length != 3<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00779<BR>
     * <BR>
     * 2)accountCode check<BR>
     * �@@2-1) If this.accountCode == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "accountCode is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00835<BR>
     * <BR>
     * �@@2-2)If this.accountCode meets with one of the followings<BR>
     * �@@�@@�@@�@@�@@Throw the exception "accountCode error"<BR>
     * �@@�@@�@@�@@�@@�Ethis.accountCode != numerical value<BR>
     * �@@�@@�@@�@@�@@�Ethis.accountCode.length != 6<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_00780<BR>
     * <BR>
     * 3)productCode check<BR>
     * Check the followings if this.productCode  != null<BR>
     *  3-1)  If this.productCode meets with the following conditions,<BR>
     *         Throw the exception "productCode error"<BR>
     *         �Ethis.productCode != numerical value<BR>
     *         �Ethis.productCode.length != 5<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01067<BR>
     * <BR>
     * 4)expirationStartDate check<BR>
     * �@@Check the followings if this.expirationStartDate != null<BR>
     *  4-1) If it is unable to change this.expirationStartDate to Date type,<BR>
     *         Throw the exception "expirationStartDate error (date doesn't exist)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01431<BR>
     * <BR>
     * 5)expirationEndDate <BR>
     * �@@Check the followings if this.expirationEndDate != null<BR>
     * �@@5-1)If it is unable to change this.expirationEndDate to Date type,<BR>
     * �@@�@@�@@�@@Throw the exception "expirationEndDate error (date doesn't exist)<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * 6)aftChangeExpirationEndDate <BR>
     * �@@Check the followings if this.aftChangeExpirationEndDate != null<BR>
     * �@@6-1)If it is unable to change this.aftChangeExpirationEndDate to Date type,<BR>
     * �@@�@@�@@�@@Throw the exception "expirationEndDate error (date doesn't exist)<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01433<BR>
     * <BR>
     * 7)expirationStartDate/expirationEndDate<BR>
     * �@@If this.expirationStartDate != null and this.expirationEndDate != null,<BR>
     * �@@Check the followings<BR>
     * �@@7-1) If this.expirationStartDate > this.expirationEndDate<BR>
     * �@@�@@�@@�@@�@@Throw the exception "expiration correspondence error"<BR>
     * �@@�@@�@@�@@�@@��Compare them after changing them to Date type<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * 8)expirationStartDate/aftChangeExpirationEndDate<BR>
     * �@@If this.expirationStartDate != null and this.aftChangeExpirationEndDate != null,<BR>
     * �@@Check the followings<BR>
     * �@@8-1) If this.expirationStartDate > this.aftChangeExpirationEndDate<BR>
     * �@@�@@�@@�@@�@@Throw the exception "expiration correspondence error"<BR>
     * �@@�@@�@@�@@�@@��Compare them after changing them to Date type<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01434<BR>
     * <BR>
     * 9)reason check<BR>
     * �@@Check the followings if this.reason != null<BR>
     * �@@9-1) If this.reason.length > 50,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "input reason error(digits excess)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * 10)aftChangeReason check<BR>
     * �@@Check the followings if this.aftChangeReason != null<BR>
     * �@@10-1) If this.aftChangeReason.length > 50,<BR>
     * �@@�@@�@@�@@�@@Throw the exception "input aftChangeReason error(digits excess)"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01435<BR>
     * <BR>
     * 11)accTradeStopInfoList check<BR>
     * �@@11-1) If this.accTradeStopInfoList == null<BR>
     * �@@�@@�@@�@@�@@Throw the exception "accTradeStopInfoList is null"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01436<BR>
     * <BR>
     * �@@11-2) If this.accTradeStopInfoList.length == 0,<BR>
     * �@@�@@�@@�@@�@@Throw the excetion "Elements of accTradeStopInfoList is 0"<BR>
     * <BR>
     *            class : WEB3BusinessLayerException<BR>
     *            tag : BUSINESS_ERROR_01437<BR>
     * <BR>
     * �@@11-3)Loop the process for as many times as this.accTradeStopInfoList.length<BR>
     * �@@�@@�@@�@@11-3-1)Call WEB3AdminPMAccTradeStopInfoUnit.validate()<BR>
     * <BR>
     * @@roseuid 4185F33E00BE
     * @@throws WEB3BusinessLayerException WEB3BusinessLayerException
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        final String DATE_FORMAT = "yyyyMMdd";
        log.entering(STR_METHOD_NAME);
        WEB3AdminPMAccTradeStopInfoUnit l_pMAccTradeStopInfoUnit =
            new WEB3AdminPMAccTradeStopInfoUnit();
        int l_branchCodeNumLength = WEB3StringTypeUtility.getByteLength(this.branchCode);
        int l_accountCodeNumLength = WEB3StringTypeUtility.getByteLength(this.accountCode);
        int l_productCodeNumLength = WEB3StringTypeUtility.getByteLength(this.productCode);
        int l_reasonNumLength = WEB3StringTypeUtility.getByteLength(this.reason);
        int l_aftChangeReasonNumLength = WEB3StringTypeUtility.getByteLength(this.aftChangeReason);
        int l_intaccTradeStopInfoListlength = 0;
        final int l_intThree = 3;
        final int l_intSix = 6;
        final int l_intFive = 5;
        final int l_intFifty = 50;

        // 1-1 branchCode = null, throw Exception.
        if (branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 1-2 if branchCode is not numeric OR its length not equal to 3,
            // throw exception
            if ((!WEB3StringTypeUtility.isNumber(branchCode))
                || (l_branchCodeNumLength != l_intThree))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 2-1 if accountCode is null throw Exception
        if (accountCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            // 2-2 if accountCode is not numeric OR its length not equal to 6, throw exception
            if ((!WEB3StringTypeUtility.isNumber(accountCode))
                || (l_accountCodeNumLength != l_intSix))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 3-1 if productCode is not numeric OR its length not equal to 5,
        // throw exception
        if (productCode != null)
        {
            if ((!WEB3StringTypeUtility.isNumber(productCode))
                || (l_productCodeNumLength != l_intFive))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 4-1 if expirationStartDate can' be formated to Date Type throw Exception.
        if (expirationStartDate != null
            && !WEB3StringTypeUtility.isDateStr(expirationStartDate, DATE_FORMAT))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01431,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 5-1 if expirationEndDate cant be formated to date type then throw Exception
        if (expirationEndDate != null
            && !WEB3StringTypeUtility.isDateStr(expirationEndDate, DATE_FORMAT))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        // 6-1 if aftChangeExpirationEndDate cant be formated to date type then throw Exception
        if (aftChangeExpirationEndDate != null
            && !WEB3StringTypeUtility.isDateStr(aftChangeExpirationEndDate, DATE_FORMAT))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01433,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        // 7-1 if expirationStartDate & expirationEndDate are not null
        if ((expirationStartDate != null) && (expirationEndDate != null))
        {
            Date l_expirationStartDate = WEB3DateUtility.getDate(expirationStartDate, DATE_FORMAT);
            Date l_expirationEndDate = WEB3DateUtility.getDate(expirationEndDate, DATE_FORMAT);

            int l_resultcompare =
                (WEB3DateUtility.compare(l_expirationStartDate, l_expirationEndDate));

            // if l_resultcompare > 0 1 throw Exception
            if (l_resultcompare > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // 8-1 if expirationStartDate & aftChangeExpirationEndDate are not null
        if ((expirationStartDate != null) && (aftChangeExpirationEndDate != null))
        {
            Date l_expirationStartDate = WEB3DateUtility.getDate(expirationStartDate, DATE_FORMAT);
            Date l_aftChgexpirationEndDate = WEB3DateUtility.getDate(aftChangeExpirationEndDate, DATE_FORMAT);

            int l_resultcompare =
                (WEB3DateUtility.compare(l_expirationStartDate, l_aftChgexpirationEndDate));

            // if l_resultcompare > 0 throw Exception
            if (l_resultcompare > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01434,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 9-1 if length of reason not equal to 50 , throw Exception.
        if (reason != null)
        {
            if (l_reasonNumLength > l_intFifty)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        // 10-1 if length of aftChangeReason not equal to 50 , throw Exception.
        if (aftChangeReason != null)
        {
            if (l_aftChangeReasonNumLength > l_intFifty)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01435,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }

        // 11-1 if accTradeStopInfoList is null throw Exception
        if (accTradeStopInfoList == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01436,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        } else
        {
            l_intaccTradeStopInfoListlength = accTradeStopInfoList.length;
            // 11-2 if accTradeStopInfoList.length = 0 then throw Exception.
            if (l_intaccTradeStopInfoListlength == 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01437,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            } else
            {
                // 11-3 Loop the process for as many times as this.accTradeStopInfoList.length<BR>
                for (int i = 0; i < l_intaccTradeStopInfoListlength; i++)
                {
                    l_pMAccTradeStopInfoUnit = this.accTradeStopInfoList[i];
                    // 11-3-1 Call branchTradingStatusList.validate()
                    l_pMAccTradeStopInfoUnit.validate();
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
