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
filename	WEB3MarginSwapMarginAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����������n��t���N�G�X�g(WEB3MarginSwapMarginAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/16 ������ (���u) �V�K�쐬
                   2004/12/06 �����a��(SRA) �c�Č��Ή� �m��.�R�R�W
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
 * �i�M�p����������n��t���N�G�X�g�j�B<br>
 * <br>
 * �M�p����������n��t���N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginSwapMarginAcceptRequest extends WEB3BackRequest

{
    /**
     * <p>�iPTYPE�j�B</p>
     */
    public static final String PTYPE = "margin_swapMarginAccept";

    /**
     * <p>�iSerialVersionUID�j�B</p>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * <p>�i�M�p����������n��t���N�G�X�g�j�B</p>
     * <p>�R���X�g���N�^�B</p>
     */
    public WEB3MarginSwapMarginAcceptRequest() 
    {
    }

    /**
     * <p>�i���ʃR�[�h�v���t�B�N�X�ꗗ�j�B</p>
     * <p>���ʃR�[�h�v���t�B�N�X�ꗗ�B</p>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * <p>�i���O�o�̓��[�e�B���e�B�j�B</p>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginAcceptRequest.class);
      
    /**
     * <p>�icreate���X�|���X�j�B</p>
     * <p>�M�p����������n��t���X�|���X�𐶐����ĕԂ��B</p>
     * @@return �M�p����������n��t���X�|���X
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MarginSwapMarginAcceptResponse(this);
    }
    
    /**
     * <p>�ivalidate�j�B</p>
     * <p>�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<br>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<br>
     * <br>
     * �P�j�@@���ʃR�[�h�v���t�B�N�X�ꗗ�̃`�F�b�N<br>
     * �@@this.���ʃR�[�h�v���t�B�N�X�ꗗ��null�܂��͗v�f����0�̏ꍇ�A<br>
     * �@@�u���ʃR�[�h�v���t�B�N�X�ꗗ�̎w��Ȃ��v�̗�O��throw����B<br>
     * �@@�@@<class> WEB3BusinessLayerException<br>
     * �@@�@@<tag>�@@ WEB3ErrorCatalog.BUSINESS_ERROR_01291</p>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        if(this.orderRequestNumberPrefixGroup == null || this.orderRequestNumberPrefixGroup.length == 0)
        {
            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01291,
                                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
