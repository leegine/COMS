head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.42.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderExecInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����(WEB3AdminBondOrderExecInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
Revesion History : 2006/10/08 ���� (���u) �d�l�ύX�E���f��108
Revesion History : 2007/03/09 ����� (���u) �d�l�ύX�E���f��159,161
*/

package webbroker3.bd.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����)<BR>
 * �����N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondOrderExecInfo  extends Message
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderExecInfo.class);

    /**
     * (��萔��)<BR>
     * ��萔��
     */
    public String execFaceAmount;

    /**
     * (���P��)<BR>
     * ���P��
     */
    public String execPrice;

    /**
     * (���בփ��[�g)<BR>
     * ���ב�
     */
    public String execFxRate;

    /**
     * (����)<BR>
     * ��������
     */
    public Date domesticExecutionDate;

    /**
     * (���n����)<BR>
     * ���n����
     */
    public Date foreignExecutionDate;

    /**
     * (��n��)<BR>
     * ������n��
     */
    public Date domesticDeliveryDate;

    /**
     * (���n��n��)<BR>
     * ���n��n��
     */
    public Date foreignDeliveryDate;

    /**
     * (��������i�O�݁j)<BR>
     * ��������i�O�݁j
     */
    public String foreignTradePrice;

    /**
     * (��������i�~�݁j)<BR>
     * ��������i�~�݁j
     */
    public String yenTradePrice;

    /**
     * (�o�ߗ��q�i�O�݁j)<BR>
     * �o�ߗ��q�i�O�݁j
     */
    public String foreignAccruedInterest;

    /**
     * (�o�ߗ��q�i�~�݁j)<BR>
     * �o�ߗ��q�i�~�݁j
     */
    public String yenAccruedInterest;

    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j
     */
    public String foreignDeliveryPrice;

    /**
     * (��n����i�~�݁j)<BR>
     * ��n����i�~�݁j
     */
    public String yenDeliveryPrice;

    /**
     * (�o�ߓ���)<BR>
     * �o�ߓ���
     */
    public String elapsedDays;

    /**
     * (�����)<BR>
     * �����
     */
    public String calcBaseDays;

    /**
     * (�J�X�g�f�B�A��)<BR>
     * �J�X�g�f�B�A��
     */
    public WEB3AdminBondCustodianUnit custodianInfo;

    /**
     * (�������敪)<BR>
     * �������敪<BR>
     * <BR>
     * 0�F�����@@1�F���ρ@@2�F�����
     */
    public String executionState;

    /**
     * (�x���敪�ꗗ)<BR>
     * �x���敪�ꗗ <BR>
     * <BR>
     * 1�F�o�ߗ��q�����m�łȂ��\�� <BR>
     * 2�F��n�������v���Ȃ� <BR>
     * 3�F�]�̓`�F�b�NNG <BR>
     * 4�F�o�ߗ��q�v�Z�s�\ <BR>
     * 5�F�C�O�s�ꂪ�������ɑ΂��Ĕ�c�Ɠ�
     */
    public String[] warningDiv;

    /**
     * (������)<BR>
     * ������
     */
    public Date paymentDate;

    /**
     * (�����)<BR>
     * �R���X�g���N�^
     * @@roseuid 44CB04FC00FD
     */
    public WEB3AdminBondOrderExecInfo()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * <BR>�@@�@@
     * �P)�@@��萔�ʃ`�F�b�N <BR>
     * �@@this.��萔��!=null�̏ꍇ�A�ȉ����`�F�b�N����B <BR>
     * �@@this.��萔�ʂ������P�P���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02635<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02641<BR>
     * <BR>
     * �@@this.��萔�� <= 0�ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02636<BR>
     * <BR>
     * �Q)�@@���P���`�F�b�N <BR>
     * �@@this.���P��!=null�̏ꍇ�A�ȉ����`�F�b�N����B <BR>
     * �@@this.���P���������S���ȓ��i�����S���j�{�����_�{
     * �@@�@@�@@�@@�����U���ȓ��i�����U���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02551<BR>
     * <BR>
     * �@@this.���P�������l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02022<BR>
     * <BR>
     * �@@this.���P�������O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02023<BR>
     * <BR>
     * �R)�@@���בփ��[�g�`�F�b�N <BR>
     * �@@this.���בփ��[�g != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.���בփ��[�g�������R���ȓ��i�����R���j�{�����_�{
     * �@@�@@�@@�@@�����S���ȓ��i�����S���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02037<BR>
     * <BR>
     * �@@this.���בփ��[�g�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02220<BR>
     * <BR>
     * �@@this.���בփ��[�g�����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02196<BR>
     * <BR>
     * �S)�@@�����`�F�b�N <BR>
     * �@@this.���� == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02637<BR>
     * <BR>
     * �T)�@@�������(�O��)�`�F�b�N <BR>
     * �@@this.�������(�O��) != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.�������(�O��)�������P�Q���ȓ��i�����P�Q���j�{�����_�{
     * �@@�@@�@@�@@�����Q���ȓ��i�����Q���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02555<BR>
     * <BR>
     * �@@this.�������(�O��)�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02556<BR>
     * <BR>
     * �@@this.�������(�O��)�����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02557<BR>
     * <BR>
     * �U)�@@�������(�~��)�`�F�b�N <BR>
     * �@@this.�������(�~��) != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.�������(�~��)���P�Q���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02558<BR>
     * <BR>
     * �@@this.�������(�~��)�������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02559<BR>
     * <BR>
     * �@@this.�������(�~��)�����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02560<BR>
     * <BR>
     * �V)�@@�o�ߗ��q(�O��)�`�F�b�N <BR>
     * �@@this.�o�ߗ��q(�O��) != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.�o�ߗ��q(�O��)�������P�Q���ȓ��i�����P�Q���j�{
     * �@@�@@�@@�@@�����_�{�����Q���ȓ��i�����Q���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02561<BR>
     * <BR>
     * �@@this.�o�ߗ��q(�O��)�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02562<BR>
     * <BR>
     * �@@this.�o�ߗ��q(�O��)���O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02563<BR>
     * <BR>
     * �W)�@@�o�ߗ��q(�~��)�`�F�b�N <BR>
     * �@@this.�o�ߗ��q(�~��) != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.�o�ߗ��q(�~��)���P�Q���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02564<BR>
     * <BR>
     * �@@this.�o�ߗ��q(�~��)�������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02565<BR>
     * <BR>
     * �@@this.�o�ߗ��q(�~��)���O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02566<BR>
     * <BR>
     * �X)�@@��n���(�O��)�`�F�b�N <BR>
     * �@@this.��n���(�O��) != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.��n���(�O��)�������P�P���ȓ��i�����P�P���j�{�����_�{
     * �@@�@@�@@�@@�����Q���ȓ��i�����Q���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02567<BR>
     * <BR>
     * �@@this.��n���(�O��)�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02568<BR>
     * <BR>
     * �@@this.��n���(�O��)�����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02569<BR>
     * <BR>
     * �P�O)�@@��n���(�~��)�`�F�b�N <BR>
     * �@@this.��n���(�~��) != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.��n���(�~��)���P�Q���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02570<BR>
     * <BR>
     * �@@this.��n���(�~��)�������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02571<BR>
     * <BR>
     * �@@this.��n���(�~��)�����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02572<BR>
     * <BR>
     * �P�P)�@@�o�ߓ����`�F�b�N <BR>
     * �@@this.�o�ߓ��� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.�o�ߓ������T���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02573<BR>
     * <BR>
     * �@@this.�o�ߓ����������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02574<BR>
     * <BR>
     * �@@this.�o�ߓ������O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02575<BR>
     * <BR>
     * �P�Q)�@@������`�F�b�N <BR>
     * �@@this.����� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �@@this.��������T���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02576<BR>
     * <BR>
     * �@@this.������������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02577<BR>
     * <BR>
     * �@@this.��������O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02578
     * @@throws WEB3BaseException
     * @@roseuid 44C091EE03AC
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P)�@@��萔�ʃ`�F�b�N
        //this.��萔��!=null�̏ꍇ�A�ȉ����`�F�b�N����B
        //this.��萔�ʂ������P�P���ȓ��łȂ��ꍇ�A��O���X���[����B
        if ((this.execFaceAmount != null))
        {
            if(WEB3StringTypeUtility.getIntegerDigits(this.execFaceAmount) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02635,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�z�ʋ��z�̃T�C�Y���s���ł��B");
            }      
            if (!WEB3StringTypeUtility.isInteger(this.execFaceAmount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�z�ʋ��z�������l�ł͂���܂���B");
            }
        }
        //this.��萔�� <= 0�ꍇ�A��O���X���[����B
        //class:�@@WEB3BusinessLayerException
        //tag:�@@�@@BUSINESS_ERROR_02186
        if ((this.execFaceAmount != null) &&
            (Long.parseLong(this.execFaceAmount) <= 0))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z��0�ȉ��̒l�ł��B");
        }

        //�Q)�@@���P���`�F�b�N
        //this.���P��!=null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.execPrice != null)
        {
            //this.���P���������S���ȓ��i�����S���j�{�����_�{
            //�����U���ȓ��i�����U���j�łȂ��ꍇ�A��O���X���[����B
            if((WEB3StringTypeUtility.getIntegerDigits(this.execPrice) > 4) ||
                (WEB3StringTypeUtility.getFractionDigits(this.execPrice) > 6))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���P���͐������S���C�������U���͈̔͊O�ł��B");
            }

            //this.���P�������l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.execPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���P�������l�ȊO�̒l�ł��B");
            }
            //this.���P�������O�̏ꍇ�A��O���X���[����B
            if ((Double.parseDouble(this.execPrice) <= 0))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���P����0�ȉ��̒l�ł��B");
            }
        }
        //�R)�@@���בփ��[�g�`�F�b�N
        //this.���בփ��[�g != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.execFxRate != null)
        {
            //this.���בփ��[�g�������R���ȓ��i�����R���j�{�����_�{
            //�����S���ȓ��i�����S���j�łȂ��ꍇ�A��O���X���[����B
            if ((WEB3StringTypeUtility.getIntegerDigits(this.execFxRate) > 3) ||
                (WEB3StringTypeUtility.getFractionDigits(this.execFxRate) > 4))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02037,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���בփ��[�g�̗L���������A�������R���C�������S���͈̔͊O�ł��B");
            }

            //this.���בփ��[�g�����l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.execFxRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02220,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���בփ��[�g�����l�ȊO�̒l�ł��B");
            }

            //this.���בփ��[�g�����O�̏ꍇ�A��O���X���[����B
            if (Double.parseDouble(this.execFxRate) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02196,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���בփ��[�g��0�ȉ��̒l�ł��B");
            }
        }

        //�S)�@@�����`�F�b�N
        //this.���� == null�̏ꍇ�A��O���X���[����B
        if (this.domesticExecutionDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02637,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���������w��ł��B");
        }

        //�U)�@@�������(�O��)�`�F�b�N
        //this.�������(�O��) != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.foreignTradePrice != null)
        {
            //this.�������(�O��)�������P�Q���ȓ��i�����P�Q���j�{�����_�{
            //�����Q���ȓ��i�����Q���j�łȂ��ꍇ�A��O���X���[����B
            if ((WEB3StringTypeUtility.getIntegerDigits(this.foreignTradePrice) > 12) ||
                (WEB3StringTypeUtility.getFractionDigits(this.foreignTradePrice) > 2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02555,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������i�O�݁j�̗L���������A�������P�Q���C�������Q���͈̔͊O�ł��B");
            }

            //this.�������(�O��)�����l�łȂ��ꍇ�A��O���X���[����B
            if (this.foreignTradePrice != null)
            {
                if (!WEB3StringTypeUtility.isNumber(this.foreignTradePrice))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02556,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "��������i�O�݁j�����l�ȊO�̒l�ł��B");
                }
            }

            //this.�������(�O��)�����O�̏ꍇ�A��O���X���[����B7
            if ((this.foreignTradePrice != null) &&
                (Double.parseDouble(this.foreignTradePrice) <= 0))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02557,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������i�O�݁j��0�ȉ��̒l�ł��B");
            }
        }

        //�V)�@@�������(�~��)�`�F�b�N
        //this.�������(�~��) != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.yenTradePrice != null)
        {
            //this.�������(�~��)���P�Q���ȓ��łȂ��ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.yenTradePrice) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02558,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������i�~�݁j�̃T�C�Y���s���ł��B");
            }

            //this.�������(�~��)�������łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.yenTradePrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02559,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������i�~�݁j�������ȊO�̒l�ł��B");
            }

            //this.�������(�~��)�����O�̏ꍇ�A��O���X���[����B
            if (Long.parseLong(this.yenTradePrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02560,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��������i�~�݁j��0�ȉ��̒l�ł��B");
            }
        }

        //�W)�@@�o�ߗ��q(�O��)�`�F�b�N
        //this.�o�ߗ��q(�O��) != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.foreignAccruedInterest != null)
        {
            //this.�o�ߗ��q(�O��)�������P�Q���ȓ��i�����P�Q���j�{
            //�����_�{�����Q���ȓ��i�����Q���j�łȂ��ꍇ�A��O���X���[����B
            if ((WEB3StringTypeUtility.getIntegerDigits(this.foreignAccruedInterest) > 12) ||
                (WEB3StringTypeUtility.getFractionDigits(this.foreignAccruedInterest) > 2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02561,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߗ��q�i�O�݁j�̗L���������A�������P�Q���C�������Q���͈̔͊O�ł��B");
            }

            //this.�o�ߗ��q(�O��)�����l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(this.foreignAccruedInterest))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02562,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߗ��q�i�O�݁j�����l�ȊO�̒l�ł��B");
            }

            //this.�o�ߗ��q(�O��)���O�̏ꍇ�A��O���X���[����B
            if (Double.parseDouble(this.foreignAccruedInterest) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02563,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߗ��q�i�O�݁j��0��菬�����l�ł��B");
            }
        }

        //�X)�@@�o�ߗ��q(�~��)�`�F�b�N
        //this.�o�ߗ��q(�~��) != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.yenAccruedInterest != null)
        {
            //this.�o�ߗ��q(�~��)���P�Q���ȓ��łȂ��ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.yenAccruedInterest) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02564,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߗ��q�i�~�݁j�̃T�C�Y���s���ł��B");
            }

            //this.�o�ߗ��q(�~��)�������łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.yenAccruedInterest))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02565,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߗ��q�i�~�݁j�������ȊO�̒l�ł��B");
            }

            //this.�o�ߗ��q(�~��)���O�̏ꍇ�A��O���X���[����B
            if (Long.parseLong(this.yenAccruedInterest) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02566,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߗ��q�i�~�݁j��0��菬�����l�ł��B");
            }
        }
        //�P�O)�@@��n���(�O��)�`�F�b�N
        //this.��n���(�O��) != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.foreignDeliveryPrice != null)
        {
            //this.��n���(�O��)�������P�P���ȓ��i�����P�P���j�{�����_�{
            //�����Q���ȓ��i�����Q���j�łȂ��ꍇ�A��O���X���[����B
            if ((WEB3StringTypeUtility.getIntegerDigits(this.foreignDeliveryPrice) > 11) ||
                (WEB3StringTypeUtility.getFractionDigits(this.foreignDeliveryPrice) > 2))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02567,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n����i�O�݁j�̗L���������A�������P�P���C�������Q���͈̔͊O�ł��B");
            }

            //this.��n���(�O��)�����l�łȂ��ꍇ�A��O���X���[����B68
            if (!WEB3StringTypeUtility.isNumber(this.foreignDeliveryPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02568,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n����i�O�݁j�����l�ȊO�̒l�ł��B");
            }

            //this.��n���(�O��)�����O�̏ꍇ�A��O���X���[����B
            if (Double.parseDouble(this.foreignDeliveryPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02569,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n����i�O�݁j��0�ȉ��̒l�ł��B");
            }
        }
        //�P�P)�@@��n���(�~��)�`�F�b�N
        //this.��n���(�~��) != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.yenDeliveryPrice != null)
        {
            //this.��n���(�~��)���P�Q���ȓ��łȂ��ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.yenDeliveryPrice) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02570,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n����i�~�݁j�̃T�C�Y���s���ł��B");
            }

            //this.��n���(�~��)�������łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.yenDeliveryPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02571,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n����i�~�݁j�������ȊO�̒l�ł��B");
            }
            //this.��n���(�~��)�����O�̏ꍇ�A��O���X���[����B
            if (Long.parseLong(this.yenDeliveryPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02572,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "��n����i�~�݁j��0�ȉ��̒l�ł��B");
            }
        }
        //�P�Q)�@@�o�ߓ����`�F�b�N
        //this.�o�ߓ��� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.elapsedDays != null)
        {
            //this.�o�ߓ������T���ȓ��łȂ��ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.elapsedDays) > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02573,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߓ����̃T�C�Y���s���ł��B");
            }

            //this.�o�ߓ����������łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.elapsedDays))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02574,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߓ����������ȊO�̒l�ł��B");
            }

            //this.�o�ߓ������O�̏ꍇ�A��O���X���[����B
            if (Integer.parseInt(this.elapsedDays) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02575,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�o�ߓ�����0��菬�����l�ł��B");
            }
        }
        //�P�R)�@@������`�F�b�N
        //this.����� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.calcBaseDays != null)
        {
            //this.��������T���ȓ��łȂ��ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(this.calcBaseDays) > 5)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02576,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������̃T�C�Y���s���ł��B");
            }

            //this.������������łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isInteger(this.calcBaseDays))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02577,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "������������ȊO�̒l�ł��B");
            }

            //this.��������O�̏ꍇ�A��O���X���[����B
            if (Integer.parseInt(this.calcBaseDays) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02578,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������0��菬�����l�ł��B");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
