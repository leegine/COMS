head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondAutoExecRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������胊�N�G�X�g(WEB3BondAutoExecRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 ����� (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (��������胊�N�G�X�g)<BR>
 * ��������胊�N�G�X�g�N���X<BR>
 *
 * @@author �����(���u)
 * @@version 1.0
 */
public class WEB3BondAutoExecRequest extends WEB3BackRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondAutoExecRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "bond_auto_execution";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200610051109L;

    /**
     *(�،���ЃR�[�h)<BR>
     *�،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (from����ID)<BR>
     * from����ID<BR>
     */
    public long fromAccountId;

    /**
     * (to����ID)<BR>
     * to����ID<BR>
     */
    public long toAccountId;

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N <BR>
     * this.�،���ЃR�[�h==null�̏ꍇ�A<BR>
     * �u�،���ЃR�[�h�����w��ł��B�v�̗�O��throw����B<BR>
     *�@@�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@�@@tag:   BUSINESS_ERROR_00827<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        if(this.institutionCode == null)
        {
            log.debug("�،���ЃR�[�h�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ��������胊�N�G�X�g
     */
    public WEB3BondAutoExecRequest()
    {

    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3BondAutoExecResponse(this);
    }

}
@
