head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExchangeRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������ב֓o�^�������N�G�X�g(WEB3AdminFeqExchangeRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/03 �A�C��(���u) ���r���[       
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��ҊO�������ב֓o�^�������N�G�X�g)<BR>
 * �Ǘ��ҊO�������ב֓o�^�������N�G�X�g�N���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExchangeRegistCompleteRequest extends WEB3GenRequest 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminFeqExchangeRegistCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_exchangeRegistComplete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;   
    
    /**
     * (�ב֏��ꗗ)<BR>
     * �O�������ב֏��̔z��<BR>
     * <BR>
     * �����͂��������ʉ݁E���[�g�ɂ��Ă̏��݂̂��Z�b�g�����B
     */
    public WEB3FeqExchangeUnit[] exchangeList;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 42CE3A00000F
     */
    public WEB3AdminFeqExchangeRegistCompleteRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@�ב֏��ꗗ[] == null or �ב֏��ꗗ[].length == 0�̏ꍇ�A��O���X���[����B<BR>
     * �@@    class: WEB3BusinessLayerException<BR>
     * �@@    tag:   BUSINESS_ERROR_02195<BR>
     * <BR>
     * �Q�j�@@�ב֏��ꗗ[]�̊e�v�f���ɁA�ב֏��ꗗ.validate()���R�[������B<BR>
     * �ב֏��ꗗ.validate()���R�[������B
     * @@throws WEB3BaseException
     * @@roseuid 42BA5DEC001E
     */
    public void validate() 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ב֏��ꗗ[] == null or �ב֏��ꗗ[].length == 0�̏ꍇ�A��O���X���[����B
        if (this.exchangeList == null || this.exchangeList.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02195,
                getClass().getName() + STR_METHOD_NAME,
                "�ב֏��ꗗ[] == null or �ב֏��ꗗ[].length == 0");
        }
        
        int l_intCnt = this.exchangeList.length;
        
        for (int i = 0; i < l_intCnt; i++) 
        {
            //�Q�j�@@�ב֏��ꗗ[]�̊e�v�f���ɁA�ב֏��ꗗ.validate()���R�[������B
            this.exchangeList[i].validate();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqExchangeRegistCompleteResponse(this);
    }
}
@
