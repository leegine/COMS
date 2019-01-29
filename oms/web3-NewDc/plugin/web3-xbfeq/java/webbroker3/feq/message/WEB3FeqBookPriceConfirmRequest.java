head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.23.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBookPriceConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������뉿�P���o�^�m�F���N�G�X�g(WEB3FeqBookPriceConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/16 �đo�g(���u) �V�K�쐬 ���f��No.373
*/
package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������뉿�P���o�^�m�F���N�G�X�g)<BR>
 * �O�������뉿�P���o�^�m�F���N�G�X�g<BR>
 * <BR>
 * @@author �đo�g
 * @@version 1.0
 */
public class WEB3FeqBookPriceConfirmRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqBookPriceConfirmRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "feq_book_price_confirm";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200801151156L;

    public WEB3FeqBookPriceConfirmRequest()
    {

    }

    /**
     * (�ۗL���YID)<BR>
     * �ۗL���YID<BR>
     */
    public String assetId;

    /**
     * (�v�Z�p���͕뉿���z)<BR>
     * �v�Z�p���͕뉿���z<BR>
     */
    public String aftBookAmount;

    /**
     * (�c������)<BR>
     * �c������<BR>
     */
    public String balanceQuantity;

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�ۗL���YID�`�F�b�N<BR>
     * �@@�P�|�P�j�ۗL���YID��null�̏ꍇ�A<BR>
     * �@@�@@�u�ۗL���YID�������́v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_01919<BR>
     * <BR>
     * �Q�j���͍��ڃ`�F�b�N<BR>
     * �@@�Q�|�P�j<BR>
     * �@@�@@�v�Z�p���͕뉿���z == null �̏ꍇ�A<BR>
     * �@@�@@�u�K�{���ږ����́v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02980<BR>
     * <BR>
     * �@@�Q�|�Q�j�v�Z�p���͕뉿���z�`�F�b�N
     * �@@�@@�ȉ��̏����ɊY������ꍇ�A�u�v�Z�p���͕뉿���z���s���Ȓl�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�v�Z�p���͕뉿���z != ���l<BR>
     * �@@�@@�@@�E�v�Z�p���͕뉿���z < 0<BR>
     * �@@�@@�@@�E�v�Z�p���͕뉿���z�̌��� >= 12��<BR>
     * �@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag  : BUSINESS_ERROR_02981<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�ۗL���YID��null�̏ꍇ
        //�u�ۗL���YID�������́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.assetId))
        {
            log.debug("�ۗL���YID��������");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���YID�����w��(null)�ł��B");
        }

        //�v�Z�p���͕뉿���z == null �̏ꍇ�A
        //�u�K�{���ږ����́v�̗�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.aftBookAmount))
        {
            log.debug("�K�{���ږ�����");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02980,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�K�{���ږ����́B");
        }

        //�v�Z�p���͕뉿���z�`�F�b�N
        //�ȉ��̏����ɊY������ꍇ�A�u�v�Z�p���͕뉿���z���s���Ȓl�v�̗�O���X���[����B
        //�@@�E�v�Z�p���͕뉿���z != ���l
        //�@@�E�v�Z�p���͕뉿���z < 0
        //�@@�E�v�Z�p���͕뉿���z�̌��� >= 12��
        if (!WEB3StringTypeUtility.isNumber(this.aftBookAmount)
            || Double.parseDouble(this.aftBookAmount) < 0
            || WEB3StringTypeUtility.getNubmerLength(this.aftBookAmount) >= 12)
        {
            log.debug("�v�Z�p���͕뉿���z���s���Ȓl");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02981,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v�Z�p���͕뉿���z���s���Ȓl�B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqBookPriceConfirmResponse(this);
    }

}
@
