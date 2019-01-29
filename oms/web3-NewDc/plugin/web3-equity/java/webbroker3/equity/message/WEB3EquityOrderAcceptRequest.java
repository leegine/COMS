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
filename	WEB3EquityOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ����������t���N�G�X�g�N���X(WEB3EquityOrderAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/22 �^�� (���u) �V�K�쐬
                   2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�R�R�O
                   2004/12/21 �����a��(SRA) JavaDoc�C��
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
 * �i����������t���N�G�X�g�j�B</br>
 * <br>
 * ����������t���N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3EquityOrderAcceptRequest extends WEB3BackRequest
{

    /**
     * <p>�i�|�������t�B�b�N�^�C�v�j�B</p>
     */
    public static final String PTYPE = "equity_order_accept";

    /**
     * <p>�i�V���A���o�[�W����UID�j�B</p>
     */
    public static final long serialVersionUID = 200402241330L;

    /**
     * <p>�i���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
     * <p>���ʃR�[�h�v���t�B�N�X�ꗗ�B</p>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * �X���b�hNo<BR>
     */
    public Long threadNo;

    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityOrderAcceptRequest.class);

    /**
     * <p>�i����������t���N�G�X�g�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3EquityOrderAcceptRequest()
    {
        super();
    }

    /**
     * <p>�icreate���X�|���X�j�B</p>
     * <p>����������t���X�|���X�𐶐����ĕԂ��B</p>
     * @@return ����������t���X�|���X
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityOrderAcceptResponse(this);
    }

    /**
     * <p>�ivalidate�j�B</p>
     * <p>�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���ʃR�[�h�v���t�B�N�X�ꗗ�̃`�F�b�N<BR>
     * �@@this.���ʃR�[�h�v���t�B�N�X�ꗗ��null�܂��͗v�f����0�̏ꍇ�A<BR>
     * �@@�u���ʃR�[�h�v���t�B�N�X�ꗗ�̎w��Ȃ��v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_01291</p>
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
