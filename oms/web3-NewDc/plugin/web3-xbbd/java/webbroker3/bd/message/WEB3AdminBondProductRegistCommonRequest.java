head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondProductRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ������o�^���ʃ��N�G�X�g(WEB3AdminBondProductRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
                     2006/10/08 ���� (���u) �d�l�ύX�E���f��106
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҍ������o�^���ʃ��N�G�X�g)<BR>
 * �Ǘ��ҍ������o�^���ʃ��N�G�X�g�N���X
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondProductRegistCommonRequest extends WEB3GenRequest
{
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_product_regist_common";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200608171100L;
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondProductRegistCommonRequest.class);

    /**
     * (�����R�[�h(WEB3))<BR>
     * �����R�[�h(WEB3)
     */
    public String productCode;

    /**
     * (�������X�V���)<BR>
     * �������X�V���
     */
    public WEB3AdminBondProductUpdateInfo updateInfo;

    /**
     * @@roseuid 44E3363B03D8
     */
    public WEB3AdminBondProductRegistCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�������`�F�b�N<BR>
     * this.�������X�V���.������ != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �E64�o�C�g�ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00441<BR>�@@�@@
     * <BR>
     * �Q�j���t�P���`�F�b�N<BR>
     * this.�������X�V���.���t�P�� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.���t�P���������S���ȓ��i�����S���j<BR>
     * �@@�@@�@@�@@�{�����_�{����6���ȓ��i����6���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02551<BR>
     * <BR>
     * �Ethis.�������X�V���.���t�P�������p�����łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01391<BR>
     * <BR>
     * �Ethis.�������X�V���.���t�P���������O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01392<BR>
     * <BR>
     * �R�j���p�P���A�`�F�b�N<BR>
     * this.�������X�V���.���p�P�� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.���p�P���������S���ȓ��i�����S���j<BR>
     * �@@�@@�@@�@@�{�����_�{����6���ȓ��i����6���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02551<BR>
     * <BR>
     * �Ethis.�������X�V���.���p�P�������p�����łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01391<BR>
     * <BR>
     * �Ethis.�������X�V���.���p�P���������O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_01392<BR>
     * <BR>
     * �S�j�\���P�ʃ`�F�b�N<BR>
     * this.�������X�V���.�\���P�� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.�\���P�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02584<BR>
     * <BR>
     * �Ethis.�������X�V���.�\���P�ʂ������łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02585<BR>
     * <BR>
     * �Ethis.�������X�V���.�\���P�ʁ����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02586<BR>
     * <BR>
     * �T�j�Œ�z�ʃ`�F�b�N<BR>
     * this.�������X�V���.�Œ�z�� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.�Œ�z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02587<BR>
     * <BR>
     * �Ethis.�������X�V���.�Œ�z�ʂ������ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02588<BR>
     * <BR>
     * �Ethis.�������X�V���.�Œ�z�ʂ����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02589<BR>
     * <BR>
     * �U�j�ō��z�ʃ`�F�b�N<BR>
     * this.�������X�V���.�ō��z�� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.�ō��z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02590<BR>
     * <BR>
     * �Ethis.�������X�V���.�ō��z�ʂ������ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02591<BR>
     * <BR>
     * �Ethis.�������X�V���.�ō��z�ʂ����O�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02592<BR>
     *  <BR>
     * �V�j���t��n���ړ������`�F�b�N<BR>
     * this.�������X�V���.���t��n���ړ����� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.���t��n���ړ��������P���ȓ��ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02593<BR>
     * <BR>
     * �Ethis.�������X�V���.���t��n���ړ������������ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02594<BR>
     * <BR>
     * �Ethis.�������X�V���.���t��n���ړ������������@@�O�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02595<BR>
     * <BR>
     * �W�j���p��n���ړ������`�F�b�N<BR>
     * this.�������X�V���.���p��n���ړ����� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.���p��n���ړ��������P���ȓ��ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02596<BR>
     * <BR>
     * �Ethis.�������X�V���.���p��n���ړ������������ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02597<BR>
     * <BR>
     * �Ethis.�������X�V���.���p��n���ړ������������@@�O�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02598<BR>
     * <BR>
     * �X)����萔�����`�F�b�N<BR>
     * this.�������X�V���.����萔���� != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.����萔���������Q���ȓ��i�����Q���j<BR>
     * �@@�@@�@@�@@�{�����_�{�����T���ȓ��i�����T���j�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02599<BR>
     * <BR>
     * �Ethis.�������X�V���.����萔���������p�����ł͂Ȃ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02600<BR>
     * <BR>
     * �Ethis.�������X�V���.����萔����������萔�����@@�����@@�O�@@�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02601<BR>
     * <BR>
     * �P�O)�������g�`�F�b�N<BR>
     * this.�������X�V���.�������g != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.�������g��12���ȓ��ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02602<BR>
     * <BR>
     * �Ethis.�������X�V���.�������g�������ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02603<BR>
     * <BR>
     * �Ethis.�������X�V���.�������g�������@@�O�@@�ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02604<BR>
     * <BR>
     * �P�P)�d�����̈בփ��[�g�`�F�b�N <BR>
     * this.�������X�V���.�d�����̈בփ��[�g != null�̏ꍇ�A�ȉ����`�F�b�N����B<BR>
     * �Ethis.�������X�V���.�d�����̈בփ��[�g�������R���ȓ��i<BR>
     * �����R���j�{�����_�{�����S���ȓ��i�����S���j�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02663<BR>
     * <BR>
     * �Ethis.�������X�V���.�d�����̈בփ��[�g�����l�łȂ��ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02664<BR>
     * <BR>
     * �Ethis.�������X�V���.�d�����̈בփ��[�g�����O�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02665<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D68B0E0128
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�������`�F�b�N
        //this.�������X�V���.������ != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�E64�o�C�g�ȓ��łȂ��ꍇ�A��O���X���[����B
        if ((this.updateInfo.productName != null) &&
            (WEB3StringTypeUtility.getByteLength(this.updateInfo.productName) > 64))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00441,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�������̃T�C�Y���s���ł��B");
        }

        //�Q�j���t�P���`�F�b�N
        //this.�������X�V���.���t�P�� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.���t�P���������S���ȓ��i�����S���j
        //�{�����_�{����6���ȓ��i����6���j�łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.buyPrice != null)
        {
            if ((WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.buyPrice) > 4) ||
                    (WEB3StringTypeUtility.getFractionDigits(this.updateInfo.buyPrice) > 6))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P���̗L���������������S���C�������U���͈̔͊O�ł��B");
            }
        }

        //�Ethis.�������X�V���.���t�P�������p�����łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.buyPrice != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.updateInfo.buyPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P���������ȊO�ł��B");
            }
        }

        //�Ethis.�������X�V���.���t�P���������O�̏ꍇ�A��O���X���[����B
        if (this.updateInfo.buyPrice != null)
        {
            if (Double.parseDouble(this.updateInfo.buyPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01392,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P����0�ȉ��ł��B");
            }
        }

        //�R�j���p�P���A�`�F�b�N
        //this.�������X�V���.���p�P�� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.���p�P���������S���ȓ��i�����S���j
        //�{�����_�{����6���ȓ��i����6���j�łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.sellPrice != null)
        {
            if ((WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.sellPrice) > 4) ||
                    (WEB3StringTypeUtility.getFractionDigits(this.updateInfo.sellPrice) > 6))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02551,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P���͐������S���C�������U���͈̔͊O�ł��B");
            }
        }
        //�Ethis.�������X�V���.���p�P�������p�����łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.sellPrice != null)
        {
            if (!WEB3StringTypeUtility.isSingle(this.updateInfo.sellPrice))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01391,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P���������ȊO�ł��B");
            }
        }

        //�Ethis.�������X�V���.���p�P���������O�̏ꍇ�A��O���X���[����B
        if (this.updateInfo.sellPrice != null)
        {
            if (Double.parseDouble(this.updateInfo.sellPrice) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01392,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�P����0�ȉ��ł��B");
            }
        }

        //�S�j�\���P�ʃ`�F�b�N
        //this.�������X�V���.�\���P�� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.�\���P�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.tradeUnit != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.tradeUnit) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02584,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\���P�ʂ̃T�C�Y���s���ł��B");
            }
        }

        //�Ethis.�������X�V���.�\���P�ʂ������łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.tradeUnit != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.tradeUnit))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02585,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\���P�ʂ������ȊO�̒l�ł��B");
            }
        }

        //�Ethis.�������X�V���.�\���P�ʁ����O�̏ꍇ�A��O���X���[����B
        if (this.updateInfo.tradeUnit != null)
        {
            if (Long.parseLong(this.updateInfo.tradeUnit) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02586,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\���P�ʂ�0�ȉ��̒l�ł��B");
            }
        }

        //�T�j�Œ�z�ʃ`�F�b�N<BR>
        //this.�������X�V���.�Œ�z�� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.�Œ�z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.minFaceAmount != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.minFaceAmount) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02587,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�z�ʂ̃T�C�Y���s���ł��B");
            }
        }

        //�Ethis.�������X�V���.�Œ�z�ʂ������ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.minFaceAmount != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.minFaceAmount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02588,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�z�ʂ������ȊO�̒l�ł��B");
            }
        }

        //�Ethis.�������X�V���.�Œ�z�ʂ����O�̏ꍇ�A��O���X���[����B
        if (this.updateInfo.minFaceAmount != null)
        {
            if (Long.parseLong(this.updateInfo.minFaceAmount) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02589,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�Œ�z�ʂ�0��菬�����l�ł��B");
            }
        }

        //�U�j�ō��z�ʃ`�F�b�N
        //this.�������X�V���.�ō��z�� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.�ō��z�ʂ��P�P���ȓ��łȂ��ꍇ�A��O���X���[����B
        if (this.updateInfo.maxFaceAmount != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.maxFaceAmount) > 11)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02590,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ō��z�ʂ̃T�C�Y���s���ł��B");
            }
        }

        //�Ethis.�������X�V���.�ō��z�ʂ������ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.maxFaceAmount != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.maxFaceAmount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02591,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ō��z�ʂ������ȊO�̒l�ł��B");
            }
        }

        //�Ethis.�������X�V���.�ō��z�ʂ����O�̏ꍇ�A��O���X���[����B
        if (this.updateInfo.maxFaceAmount != null)
        {
            if (Long.parseLong(this.updateInfo.maxFaceAmount) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02592,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�ō��z�ʂ�0��菬�����l�ł��B");
            }
        }

        //�V�j���t��n���ړ������`�F�b�N
        //this.�������X�V���.���t��n���ړ����� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.���t��n���ړ��������P���ȓ��ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.buyDeliveryMove != null)
        {
            if (WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.buyDeliveryMove) > 1)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02593,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���t��n���w��l�̃T�C�Y���s���ł��B");
            }
        }

        //�Ethis.�������X�V���.���t��n���ړ������������ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.buyDeliveryMove != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.buyDeliveryMove))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02594,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���t��n���w��l�������ȊO�̒l�ł��B");
            }
        }

        //�Ethis.�������X�V���.���t��n���ړ������������@@�O�ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.buyDeliveryMove != null)
        {
            if (Integer.parseInt(this.updateInfo.buyDeliveryMove) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02595,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���t��n���w��l���O��菬�����ł��B");
            }
        }

        //�W�j���p��n���ړ������`�F�b�N<BR>
        //this.�������X�V���.���p��n���ړ����� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.���p��n���ړ��������P���ȓ��ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.sellDeliveryMove != null)
        {
            if (WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.sellDeliveryMove) > 1)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02596,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���p��n���w��l�̃T�C�Y���s���ł��B");
            }
        }

        //�Ethis.�������X�V���.���p��n���ړ������������ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.sellDeliveryMove != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.sellDeliveryMove))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02597,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���p��n���w��l�������ȊO�̒l�ł��B");
            }
        }

        //�Ethis.�������X�V���.���p��n���ړ������������@@�O�ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.sellDeliveryMove != null)
        {
            if (Integer.parseInt(this.updateInfo.sellDeliveryMove) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02598,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "���p��n���w��l���O��菬�����ł��B");
            }
        }

        //�X)����萔�����`�F�b�N
        //this.�������X�V���.����萔���� != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.����萔���������Q���ȓ��i�����Q���j
        //�{�����_�{�����T���ȓ��i�����T���j�ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.mediatorCommissionRate != null)
        {
            if ((WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.mediatorCommissionRate) > 2) ||
                    (WEB3StringTypeUtility.getFractionDigits(this.updateInfo.mediatorCommissionRate) > 5))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02599,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����萔�����̗L���������A�������Q���C�������T���͈̔͊O�ł��B");
            }
        }

        //�Ethis.�������X�V���.����萔���������p�����ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.mediatorCommissionRate != null)
        {
            if (!WEB3StringTypeUtility.isNumber(this.updateInfo.mediatorCommissionRate))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02600,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����萔���������l�ȊO�̒l�ł��B");
            }
        }

        //�Ethis.�������X�V���.����萔����������萔�����@@�����@@�O�@@�ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.mediatorCommissionRate != null)
        {
            if (Double.parseDouble(this.updateInfo.mediatorCommissionRate) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02601,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "����萔�������O��菬�����ł��B");
            }
        }

        //�P�O)�������g�`�F�b�N
        //this.�������X�V���.�������g != null�̏ꍇ�A�ȉ����`�F�b�N����B
        //�Ethis.�������X�V���.�������g��12���ȓ��ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.autoExecLimit != null)
        {
            if (WEB3StringTypeUtility.getByteLength(this.updateInfo.autoExecLimit) > 12)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02602,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������g�̃T�C�Y���s���ł��B");
            }
        }
        //�Ethis.�������X�V���.�������g�������ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.autoExecLimit != null)
        {
            if (!WEB3StringTypeUtility.isInteger(this.updateInfo.autoExecLimit))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02603,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������g�������ȊO�̒l�ł��B");
            }
        }

        //�Ethis.�������X�V���.�������g�������@@�O�@@�ł͂Ȃ��ꍇ�A��O���X���[����B
        if (this.updateInfo.autoExecLimit != null)
        {
            if (Long.parseLong(this.updateInfo.autoExecLimit) < 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02604,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�������g��0��菬�����l�ł��B");
            }
        }
        //�P�P)�d�����̈בփ��[�g�`�F�b�N
        //this.�������X�V���.�d�����̈בփ��[�g != null�̏ꍇ�A�ȉ����`�F�b�N����B
        if (this.updateInfo.fxRateAtStock != null)
        {
            //�Ethis.�������X�V���.�d�����̈בփ��[�g�����l�łȂ��ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isNumber(updateInfo.fxRateAtStock))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02664,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d�����̈בփ��[�g�����l�ȊO�̒l�ł��B");
            }
            //�Ethis.�������X�V���.�d�����̈בփ��[�g�����O�̏ꍇ�A��O���X���[����B
            if (Double.parseDouble(this.updateInfo.fxRateAtStock) <= 0)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02665,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d�����̈בփ��[�g��0�ȉ��̒l�ł��B");
            }

            //�Ethis.�������X�V���.�d�����̈בփ��[�g�������R���ȓ�
            //�i�����R���j�{�����_�{�����S���ȓ��i�����S���j�łȂ��ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.getIntegerDigits(this.updateInfo.fxRateAtStock) > 3 ||
                WEB3StringTypeUtility.getFractionDigits(this.updateInfo.fxRateAtStock) > 4)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02663,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�d�����̈בփ��[�g�̗L���������A�������R���C�������S���͈̔͊O�ł��B");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �������o�^�������N�G�X�g�𐶐����Ԃ��B
     * @@return WEB3GenResponse
     * @@roseuid 44B620020375
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
