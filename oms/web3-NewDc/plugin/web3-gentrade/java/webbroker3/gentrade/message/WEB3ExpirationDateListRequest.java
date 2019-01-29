head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExpirationDateListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����L�������擾���N�G�X�g(WEB3ExpirationDateListRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 ���n(���u) �V�K�쐬���f��319
*/

package webbroker3.gentrade.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�����L�������擾���N�G�X�g)<BR>
 * �����L�������擾���N�G�X�g�N���X<BR>
 *
 * @@author ���n
 * @@version 1.0
 */
public class WEB3ExpirationDateListRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ExpirationDateListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "expiration_date_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802141629L;

    /**
     * (���i�敪)<BR>
     * ���i�敪�i1:�������� 2:�M�p��� 3:�敨 4:�I�v�V�����j<BR>
     */
    public String commodityType;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;

    /**
     * (�w�����)<BR>
     * �w����ʁi�����Y�����R�[�h�j<BR>
     */
    public String targetProductCode;

    /**
     * @@roseuid 47B3E13100DA
     */
    public WEB3ExpirationDateListRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@���i�敪�`�F�b�N <BR>
     * �@@�P�|�P�jthis.���i�敪��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���i�敪��null�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_02182<BR>
     * <BR>
     * �@@�P�|�Q�jthis.���i�敪��null�A <BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u���i�敪������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E��������<BR>
     * �@@�@@�@@�@@�E�M�p���<BR>
     * �@@�@@�@@�@@�E�敨<BR>
     * �@@�@@�@@�@@�E�I�v�V���� <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_01068<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�`�F�b�N <BR>
     * �@@�Q�|�P�jthis.�s��R�[�h��null�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_00443<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�s��R�[�h��null�A <BR>
     * �@@�@@�@@�@@�@@�����L�̒l�ȊO�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B <BR>
     * �@@�@@�@@�@@�E���� <BR>
     * �@@�@@�@@�@@�E��� <BR>
     * �@@�@@�@@�@@�E���É� <BR>
     * �@@�@@�@@�@@�E���� <BR>
     * �@@�@@�@@�@@�E�D�y <BR>
     * �@@�@@�@@�@@�ENNM <BR>
     * �@@�@@�@@�@@�EJASDAQ <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_00608<BR>
     * <BR>
     * �R�j�@@�w����ʃ`�F�b�N <BR>
     * �@@�R�|�P�jthis.���i�敪���h���������h�܂��́h�M�p����h�̏ꍇ�A <BR>
     * �@@�@@�@@�@@�@@���Athis.�w����ʁ�null�̏ꍇ�A��O���X���[����B <BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@tag:�@@BUSINESS_ERROR_03018<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2C1F50227
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //���i�敪�`�F�b�N
        //this.���i�敪��null�̏ꍇ
        //�u���i�敪��null�v�̗�O���X���[����B
        if (this.commodityType == null)
        {
            log.debug("���i�敪�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02182,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�敪�����w��ł��B");
        }

        //this.���i�敪��null�A
        //�����L�̒l�ȊO�̏ꍇ�A
        //�u���i�敪������`�̒l�v�̗�O���X���[����
        //  �E��������
        //�@@�E�M�p���
        //�@@�E�敨
        //�@@�E�I�v�V����
        if (this.commodityType != null
            && !(WEB3CommodityDivDef.EQUITY.equals(this.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(this.commodityType)
            || WEB3CommodityDivDef.FUTURE.equals(this.commodityType)
            || WEB3CommodityDivDef.OPTION.equals(this.commodityType)))
        {
            log.debug("���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�敪�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�s��R�[�h�`�F�b�N
        //this.�s��R�[�h��null�̏ꍇ�A
        //�u�s��R�[�h��null�v�̗�O���X���[����
        if (this.marketCode == null)
        {
            log.debug("�s��R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�s��R�[�h�����w��ł��B");
        }

        //this.�s��R�[�h��null�A
        //�����L�̒l�ȊO�̏ꍇ�A
        //�u�s��R�[�h������`�̒l�v�̗�O���X���[����B
        //�E����
        //�E���
        //�E���É�
        //�E����
        //�E�D�y
        //�ENNM
        //�EJASDAQ
        if (this.marketCode != null
            && !(WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
            || WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
            || WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
            || WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
            || WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
            || WEB3MarketCodeDef.NNM.equals(this.marketCode)
            || WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)))
        {
            log.debug("�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00608,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�s��R�[�h�����݂��Ȃ��R�[�h�l�ł��B");
        }
        //�w����ʃ`�F�b�N
        //this.���i�敪���h���������h�܂��́h�M�p����h�̏ꍇ�A
        //���Athis.�w����ʁ�null�̏ꍇ�A��O���X���[����
        if ((WEB3CommodityDivDef.EQUITY.equals(this.commodityType)
            || WEB3CommodityDivDef.MARGIN.equals(this.commodityType))
            && this.targetProductCode != null)
        {
            log.debug("���i�敪���g���������h�܂��́h�M�p����h�̏ꍇ�A�w����ʂ��w��s�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���i�敪���g���������h�܂��́h�M�p����h�̏ꍇ�A�w����ʂ��w��s�ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3ExpirationDateListResponse(this);
    }

}
@
