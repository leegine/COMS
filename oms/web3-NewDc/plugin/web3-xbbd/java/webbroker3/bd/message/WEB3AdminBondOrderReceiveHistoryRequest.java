head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderReceiveHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��Ғ�����t�����Ɖ�N�G�X�g(WEB3AdminBondOrderReceiveHistoryRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 ���g (���u) �V�K�쐬 �d�l�ύX�E���f��No.216
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��Ғ�����t�����Ɖ�N�G�X�g)<BR>
 * �Ǘ��ҍ��������X�ʒ�����t�����Ɖ�N�G�X�g<BR>
 * <BR>
 * @@author ���g
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryRequest extends WEB3GenRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_receive_history";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231733L;

    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String productID;

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     * �@@"---"�����͂��ꂽ�ꍇ�A�S���X�Ƃ݂Ȃ�<BR>
     */
    public String branchCode;

    /**
     * @@roseuid 46A473FE02EE
     */
    public WEB3AdminBondOrderReceiveHistoryRequest()
    {

    }

    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * �P) ����ID�`�F�b�N<BR>
     * �@@this.����ID==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_02229<BR>
     * �Q) ���X�R�[�h�`�F�b�N<BR>
     * �@@this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@tag:�@@BUSINESS_ERROR_00833<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46834F5A00EB
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //�P) ����ID�`�F�b�N
        //this.����ID==null�̏ꍇ�A��O���X���[����B
        if (this.productID == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }

        //�Q) ���X�R�[�h�`�F�b�N
        //this.���X�R�[�h==null�̏ꍇ�A��O���X���[����B
        if (this.branchCode == null)
        {
            log.debug("���X�R�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                "���X�R�[�h�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (create���X�|���X)<BR>
     * (createResponse����)<BR>
     * <BR>
     * �Ǘ��Ғ�����t�����Ɖ�X�|���X�𐶐����Ԃ�
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondOrderReceiveHistoryResponse(this);
    }
}
@
