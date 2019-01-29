head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginSwapOrderNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n�����ʒm���N�G�X�g�N���X(WEB3MarginSwapOrderNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 �X�� (SRA) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����������n�����ʒm���N�G�X�g�j�B<BR>
 * <BR>
 * �M�p����������n�����ʒm���N�G�X�g�N���X�B
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyRequest extends WEB3BackRequest 
{
    /**
     * (PTYPE)�B<BR>
     */
    public static final String PTYPE = "margin_swapOrderNotify";


    /**
     * (SerialVersionUID)�B<BR>
     */
    public static final long serialVersionUID = 200412241500L;


    /**
     * (���ʃR�[�h�v���t�B�N�X�ꗗ)�B<BR>
     * <BR>
     * ���ʃR�[�h�v���t�B�N�X�ꗗ�B<BR>
     */
    public String[] orderRequestNumberPrefixGroup;


    /**
     * (���O�o�̓��[�e�B���e�B)�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyRequest.class);


    /**
     * (�R���X�g���N�^)�B<BR>
     */
    public WEB3MarginSwapOrderNotifyRequest() 
    {
    }


    /**
     * (create���X�|���X)�B<BR>
     * <BR>
     * �M�p����������n�����ʒm���X�|���X�𐶐����ĕԂ��B<BR>
     * @@return �M�p����������n�����ʒm���X�|���X<BR>
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MarginSwapOrderNotifyResponse(this);
    }


    /**
     * (validate)�B<BR>
     * <BR>
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j�@@���ʃR�[�h�v���t�B�N�X�ꗗ�̃`�F�b�N <BR>
     * �@@this.���ʃR�[�h�v���t�B�N�X�ꗗ��null�܂��͗v�f����0�̏ꍇ�A <BR>
     * �@@�u���ʃR�[�h�v���t�B�N�X�ꗗ�̎w��Ȃ��v�̗�O��throw����B <BR>
     * �@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@tag:�@@ WEB3ErrorCatalog.BUSINESS_ERROR_01291<BR>
     * @@throws WEB3BaseException
     * <BR>
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
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
