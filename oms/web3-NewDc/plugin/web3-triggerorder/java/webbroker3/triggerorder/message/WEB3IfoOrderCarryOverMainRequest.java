head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�����J�z���C�����N�G�X�g(WEB3IfoOrderCarryOverMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/6/21 ���^�] (���u) �V�K�쐬 ���f�� 669
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�敨OP�����J�z���C�����N�G�X�g)<BR>
 * �敨OP�����J�z���C�����N�G�X�g�N���X<BR>
 * @@author ���^�]
 * @@version 1.0
 */
public class WEB3IfoOrderCarryOverMainRequest extends WEB3BackRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOrderCarryOverMainRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "ifo_order_carryover_main";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200706211717L;

    /**
     * (�،���ЃR�[�h)<BR>
     */
    public String institutionCode;

    /**
     * (From����ID)<BR>
     */
    public long rangeFrom;

    /**
     * (To����ID)<BR>
     */
    public long rangeTo;

    public WEB3IfoOrderCarryOverMainRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N<BR>
     * �@@�@@this.�،���ЃR�[�h��null�̏ꍇ�A��O��throw����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00827<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B
        // �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j
        //�P�j�@@�،���ЃR�[�h�`�F�b�N
        // �@@�@@this.�،���ЃR�[�h��null�̏ꍇ�A��O��throw����B
        if (this.institutionCode == null)
        {
            log.debug("�،���ЃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3IfoOrderCarryOverMainResponse(this);
    }
}
@
