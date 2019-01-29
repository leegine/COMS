head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityPTSExecEndNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (PTS)�����o���I���ʒm���N�G�X�g(WEB3EquityPTSExecEndNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/23 ��іQ(���u) �V�K�쐬 ���f��No.1286
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * ((PTS)�����o���I���ʒm���N�G�X�g)<BR>
 * (PTS)�����o���I���ʒm���N�G�X�g�N���X<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3EquityPTSExecEndNotifyRequest extends WEB3BackRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSExecEndNotifyRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_pts_exec_end_notify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 20080123101000L;

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * (From����ID)<BR>
     * From����ID<BR>
     */
    public long rangeFrom;

    /**
     * (To����ID)<BR>
     * To����ID<BR>
     */
    public long rangeTo;

    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;

    /**
     * @@roseuid 462CA4250276
     */
    public WEB3EquityPTSExecEndNotifyRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N<BR>
     * �@@this.�،���ЃR�[�h��null�̏ꍇ�A<BR>
     * �@@�u�،���ЃR�[�h��null�v�̗�O��throw����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag  :�@@BUSINESS_ERROR_00827<BR>
     * <BR>
     * �Q�j�@@�s��R�[�h�`�F�b�N<BR>
     * �@@this.�s��R�[�h��null�̏ꍇ�A<BR>
     * �@@�@@�u�s��R�[�h��null�v�̗�O���X���[����B<BR>
     * �@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@tag  :�@@BUSINESS_ERROR_00443<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46075A5D03A3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //this.�،���ЃR�[�h��null�̏ꍇ
        //�u�،���ЃR�[�h��null�v�̗�O��throw����B
        if (this.institutionCode == null)
        {
            log.debug("�،���ЃR�[�h��null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�،���ЃR�[�h�����w��ł��B");
        }

        //this.�s��R�[�h��null�̏ꍇ
        //�u�s��R�[�h��null�v�̗�O���X���[����B
        if (this.marketCode == null)
        {
            log.debug("�s��R�[�h��null");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�s��R�[�h�����w��ł��B");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityPTSExecEndNotifyResponse(this);
    }
}
@
