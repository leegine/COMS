head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecNotifyMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����o���ʒm���C�����N�G�X�g(WEB3EquityExecNotifyMainRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01 �������F (SRA) �V�K�쐬
                   2005/03/09 �� (FLJ) �X���b�hNo�ǉ�
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����o���ʒm���C�����N�G�X�g�j�B<br>
 * <br>
 * �����o���ʒm���C�����N�G�X�g�N���X
 * @@author �������F
 * @@version 1.0
 */
public class WEB3EquityExecNotifyMainRequest extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_execNotifyMain";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412010000L;

    /**
     * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * �X���b�hNo<BR>
     */
    public Long threadNo;

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecNotifyMainRequest.class);

    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3EquityExecNotifyMainRequest()
    {
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     * <BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityExecNotifyMainResponse(this);
    }

    /**
     * �ivalidate�j<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���ʃR�[�h�v���t�B�N�X�ꗗ�̃`�F�b�N<BR>
     * �@@this.���ʃR�[�h�v���t�B�N�X�ꗗ��null�܂��͗v�f����0�̏ꍇ�A<BR>
     * �@@�u���ʃR�[�h�v���t�B�N�X�ꗗ�̎w��Ȃ��v�̗�O��throw����B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (this.orderRequestNumberPrefixGroup == null ||
            this.orderRequestNumberPrefixGroup.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        if (this.threadNo == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
