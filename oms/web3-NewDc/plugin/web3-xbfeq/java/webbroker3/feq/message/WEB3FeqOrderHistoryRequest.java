head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������藚�����N�G�X�g(WEB3FeqOrderHistoryRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�O������������藚�����N�G�X�g)<BR>
 * �O������������藚�����N�G�X�g�N���X<BR>
 * 
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqOrderHistoryRequest extends WEB3GenRequest 
{
   /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderHistoryRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_orderHistory";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId;
    
    /**
     * @@roseuid 42CE3A090203
     */
    public WEB3FeqOrderHistoryRequest() 
    {
     
    }
 
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�@@����ID�̃`�F�b�N<BR>
     * �@@�P�|�P�j�@@this.����ID == null�̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00600<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 42A43A030294
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j����ID�̃`�F�b�N
        //�P�|�P�j�@@this.����ID == null�̏ꍇ�A��O���X���[����B
        if (this.orderId == null)
        {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                "����ID�����w��ł��B" + this.orderId);
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
        return new WEB3FeqOrderHistoryResponse(this);
    }
}
@
