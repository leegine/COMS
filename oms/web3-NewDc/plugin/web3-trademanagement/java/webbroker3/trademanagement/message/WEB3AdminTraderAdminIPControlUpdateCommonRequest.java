head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE���O�C������IP�X�V���ʃ��N�G�X�g(WEB3AdminTraderAdminIPControlUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 �����F (���u) �V�K�쐬 ���f��004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�Ǘ��ҁE���O�C������IP�X�V���ʃ��N�G�X�g)<BR>
 * �Ǘ��ҁE���O�C������IP�X�V���ʃ��N�G�X�g�N���X�B<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlUpdateCommonRequest extends WEB3GenRequest
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlUpdateCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_update_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221738L;

    /**
     * (���O�C������ID)<BR>
     * ���O�C������ID<BR>
     */
    public String denyLoginID;

    /**
     * @@roseuid 48D75CD70369
     */
    public WEB3AdminTraderAdminIPControlUpdateCommonRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���O�C������ID�`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.���O�C������ID == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_03116<BR>
     * �@@�P�|�Q�j�@@this.���O�C������ID�����p�����ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class : WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag : BUSINESS_ERROR_03117<BR>
     * @@roseuid 48C0C5880198
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@���O�C������ID�`�F�b�N
        //�@@�P�|�P�j�@@this.���O�C������ID == null�̏ꍇ�A��O���X���[����B
        if (this.denyLoginID == null)
        {
            log.debug("���O�C������ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03116,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���O�C������ID�����w��ł��B");
        }

        //�@@�P�|�Q�j�@@this.���O�C������ID�����p�����ȊO�̏ꍇ�A��O���X���[����B
        if (!WEB3StringTypeUtility.isDigit(this.denyLoginID))
        {
            log.debug("���O�C������ID�����p�����ȊO�̒l�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03117,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���O�C������ID�����p�����ȊO�̒l�ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlUpdateCommonResponse(this);
    }
}
@
