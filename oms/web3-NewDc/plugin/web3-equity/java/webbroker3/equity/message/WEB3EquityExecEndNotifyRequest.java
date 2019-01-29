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
filename	WEB3EquityExecEndNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o���I���ʒm���N�G�X�g�N���X(WEB3EquityExecEndNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/05/10 �A�C��(���u) �V�K�쐬
                   2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�R�R�T
                   2004/12/21 �����a��(SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����o���I���ʒm���N�G�X�g�j�B<br>
 * <br>
 * �����o���I���ʒm���N�G�X�g�N���X
 * @@version 1.0
 */
public class WEB3EquityExecEndNotifyRequest extends WEB3BackRequest
{

    /**
     * <p>�iserialVersionUID�j�B</p>
     */
    public static final long serialVersionUID = 200405211030L;

    /**
     * <p>�iPTYPE�j�B</p>
     */
    public static final String PTYPE = "equity_exec_end_notify";

    /**
     * <p>(�،���ЃR�[�h)�B</p>
     * <p>�J�z�Ώۂ̏،���ЃR�[�h�B</p>
     */
    public String institutionCode;
    
    /**
     * <p>(From����ID)�B</p>
     * <p>From����ID�B</p>
     */
    public long rangeFrom;
    
    /**
     * <p>(To����ID)�B</p>
     * <p>To����ID�B</p>
     */
    public long rangeTo;
    
    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecEndNotifyRequest.class);
    
    /**
     * <p>�i�����o���I���ʒm���N�G�X�g�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3EquityExecEndNotifyRequest()
    {

    }

    /**
     * <p>�icreate���X�|���X�j�B</p>
     * <p>�����o���I���ʒm���X�|���X�𐶐����ĕԂ��B</p>
     * @@return �����o���I���ʒm���X�|���X
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3EquityExecEndNotifyResponse(this);
    }
    
    /**
     * <p>�ivalidate�j�B</p>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�،���ЃR�[�h�`�F�b�N<BR>
     * �@@this.�،���ЃR�[�h��null�̏ꍇ�A<BR>
     * �@@�u�،���ЃR�[�h��null�v�̗�O��throw����B<BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_00827</p>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        if (institutionCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
