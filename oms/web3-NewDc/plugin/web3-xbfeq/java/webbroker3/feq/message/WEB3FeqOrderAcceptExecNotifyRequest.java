head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptExecNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������t�o���ʒm���N�G�X�g(WEB3FeqOrderAcceptExecNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/13 �����q(���u) �V�K�쐬
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������t�o���ʒm���N�G�X�g)<BR>
 * �O������������t�o���ʒm���N�G�X�g�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3FeqOrderAcceptExecNotifyRequest extends WEB3BackRequest
{  
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "feq_order_accept_exec_notify";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3FeqOrderAcceptExecNotifyRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqOrderAcceptExecNotifyRequest.class);
    
    /**
     * (From����ID)<BR>
     * From����ID<BR>
     */
    public Long fromAccountId;
    
    /**
     * (To����ID)<BR>
     * To����ID<BR>
     */
    public Long toAccountId; 
    
    /**
     * (�X���b�hNo)<BR>
     * �X���b�hNo<BR>
     */
    public Long threadNo;   
    
    /**
     *  �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     *  �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�@@From����ID��To����ID�̃`�F�b�N <BR>
     *  this.From����ID==null�܂���this.To����ID==null�̏ꍇ�A<BR> 
     * �u�h�c�����w��ł��v�̗�O��throw����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_00080<BR>
     * <BR>
     * �Q�j�@@�X���b�hNo==null�̏ꍇ�A�u�X���b�h�ԍ��̎w��Ȃ��v�̗�O��throw����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_01974<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // �P�j�@@From����ID��To����ID�̃`�F�b�N
        // this.From����ID==null�܂���this.To����ID==null�̏ꍇ�A
        //�u�h�c�����w��ł��v�̗�O��throw����B
        if (this.fromAccountId == null  ||
            this.toAccountId == null)
        {
            log.debug("�h�c�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                this.getClass().getName() + STR_METHOD_NAME,
                "�h�c�����w��ł��B");
        }
        
        // �Q�j�@@�X���b�hNo==null�̏ꍇ�A�u�X���b�h�ԍ��̎w��Ȃ��v�̗�O��throw����B
        if (this.threadNo == null)
        {
            log.debug("�X���b�h�ԍ��̎w��Ȃ��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01974,
                this.getClass().getName() + STR_METHOD_NAME,
                "�X���b�h�ԍ��̎w��Ȃ��B" + this.threadNo);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3FeqOrderAcceptExecNotifyResponse(this);
    }
}
@
