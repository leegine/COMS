head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������������ʒm���N�G�X�g(WEB3EquityOrderNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 �R�w�� (���u) �V�K�쐬
Revesion History : 2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�R�R�V
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�������������ʒm���N�G�X�g�j�B<BR>
 * <BR>
 * ��������������M�v���@@���N�G�X�g�f�[�^�N���X
 * @@version 1.0
 */
public class WEB3EquityOrderNotifyRequest extends WEB3BackRequest
{

    /**
     * ���O�o�̓��[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderNotifyRequest.class);

    public static final String PTYPE = "equity_order_notify";

    public static final long serialVersionUID = 200402041600L;

    /**
     * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * @@roseuid 40B40ADD0119
     */
    public WEB3EquityOrderNotifyRequest()
    {

    }

    /**
     * @@return webbroker3.common.message.WEB3BackResponse
     * @@roseuid 40B40ADD0167
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityOrderNotifyResponse(this);
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

        log.debug("���ʃR�[�h�v���t�B�N�X�ꗗ�̃`�F�b�N");
        if (this.orderRequestNumberPrefixGroup == null
            || this.orderRequestNumberPrefixGroup.length == 0)
        {
            throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                            this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
