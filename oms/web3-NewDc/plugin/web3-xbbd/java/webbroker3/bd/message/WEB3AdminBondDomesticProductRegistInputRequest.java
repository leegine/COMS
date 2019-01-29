head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.57.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҍ����������o�^���̓��N�G�X�g(WEB3AdminBondDomesticProductRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/09 ���n�m (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҍ����������o�^���̓��N�G�X�g)<BR>
 * �Ǘ��ҍ����������o�^���̓��N�G�X�g<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistInputRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_regist_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20070709100000L;

    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String productId;

    /**
     * @@roseuid 4691C5EC0075
     */
    public WEB3AdminBondDomesticProductRegistInputRequest()
    {

    }

    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j����ID�`�F�b�N<BR>
     * �@@�@@����ID == null �̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@�@@BUSINESS_ERROR_02229<BR>
     * @@roseuid 466371BD0249
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P�j����ID�`�F�b�N
        //�@@�@@����ID == null �̏ꍇ�A��O���X���[����B
        if (this.productId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
         return new WEB3AdminBondDomesticProductRegistInputResponse(this);
    }

}
@
