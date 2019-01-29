head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.08.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAccountConnectionRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ڋq�A�����N�G�X�g(WEB3PvInfoAccountConnectionRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/20 ���O�B(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�ڋq�A�����N�G�X�g)<BR>
 * �ڋq�A�����N�G�X�g�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoAccountConnectionRequest extends WEB3GenRequest
{

    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoAccountConnectionRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_accountConnection";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;

    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;

    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�v���y�[�W�ԍ��`�F�b�N <BR>
     * �@@�P�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * �@@�P�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * �@@�P�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * �Q�j�y�[�W���\���s���`�F�b�N <BR>
     * �@@�Q�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * �@@�Q�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>�@@
     * �@@�Q�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A <BR>
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * @@roseuid 41452AB302AE
     */
    public void validate() throws WEB3BusinessLayerException
    {

        String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�v���y�[�W�ԍ��`�F�b�N
        log.debug("�P�j�@@�v���y�[�W�ԍ��`�F�b�N: ENTER");

        //�P�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B
        log.debug("�P�|�P�j�@@�v���y�[�W�ԍ�null�`�F�b�N: ENTER");
        if (this.pageIndex == null)
        {
            log.error("�v���y�[�W�ԍ���null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("�P�|�P�j�@@�v���y�[�W�ԍ�null�`�F�b�N: END");

        //�P�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        log.debug("�P�|�Q�j�@@�v���y�[�W�ԍ�number�`�F�b�N: ENTER");
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            log.error("�v���y�[�W�ԍ��������ȊO�̃G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("�P�|�Q�j�@@�v���y�[�W�ԍ�number�`�F�b�N: END");

        //�P�|�R�jthis.�v���y�[�W�ԍ� <= 0�ł������ꍇ�A�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B
        log.debug("�P�|�R�j�@@�v���y�[�W�ԍ�value�`�F�b�N: ENTER");
        double l_dblPageIndex = Double.parseDouble(this.pageIndex);
        if (l_dblPageIndex <= 0)
        {
            log.error("�v���y�[�W�ԍ�value��0�ȉ�");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("�P�|�R�j�@@�v���y�[�W�ԍ�value�`�F�b�N: END");

        log.debug("�P�j�@@�v���y�[�W�ԍ��`�F�b�N: END");

        //�Q�j�y�[�W���\���s���`�F�b�N
        log.debug("�Q�j�@@�y�[�W���\���s���`�F�b�N: ENTER");

        //�Q�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A�u�y�[�W���\���s����null�v�̗�O���X���[����B
        log.debug("�Q�|�P�j�@@�y�[�W���\���s��null�`�F�b�N: ENTER");
        if (this.pageSize ==null )
        {
            log.error("�y�[�W���\���s��null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("�Q�|�P�j�@@�y�[�W���\���s��null�`�F�b�N: END");

        //�Q�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        log.debug("�Q�|�Q�j�@@�y�[�W���\���s��number�`�F�b�N: ENTER");
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            log.error("�y�[�W���\���s���𐔎��ȊO�̃G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("�Q�|�Q�j�@@�y�[�W���\���s��number�`�F�b�N: END");

        //�Q�|�R�jthis.�y�[�W���\���s�� <= 0�ł������ꍇ�A�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        log.debug("�Q�|�R�j�@@�y�[�W���\���s��value�`�F�b�N: ENTER");
        double l_dbPageSize = Double.parseDouble(this.pageSize);
        if (l_dbPageSize <= 0)
        {
            log.error("�y�[�W���\���s��value�G���[");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                getClass().getName() + STR_METHOD_NAME);
        }
        log.debug("�Q�|�R�j�@@�y�[�W���\���s��value�`�F�b�N: END");

        log.debug("�Q�j�@@�y�[�W���\���s���`�F�b�N: END");

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 417343990167
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3PvInfoAccountConnectionResponse(this);
    }
}
@
